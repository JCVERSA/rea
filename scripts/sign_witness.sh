#!/usr/bin/env bash
set -euo pipefail
umask 077
key=""
trap 'if [[ -n "$key" ]]; then rm -f -- "$key"; fi; unset WITNESS_PASSWORD MOD_SIGNING_KEYSTORE_B64 MOD_SIGNING_PASSWORD' EXIT
mkdir -p .work/witness/diagnostic .work/witness/delivery .work/witness/reports
bt="$ANDROID_HOME/build-tools/35.0.0"
"$bt/zipalign" -P 16 -f 4 .work/witness/unsigned.apk .work/witness/delivery/base-witness-unsigned.apk
if [[ -n "${MOD_SIGNING_KEYSTORE_B64:-}" && -n "${MOD_SIGNING_PASSWORD:-}" ]]; then
  key="$RUNNER_TEMP/witness-signing.p12"
  printf '%s' "$MOD_SIGNING_KEYSTORE_B64" | base64 --decode > "$key"
  chmod 600 "$key"
  export WITNESS_PASSWORD="$MOD_SIGNING_PASSWORD"
  output=.work/witness/delivery/base-witness-signed.apk
  printf 'stable-private-secret\n' > .work/witness/reports/signing-mode.txt
elif [[ -n "${MOD_SIGNING_KEYSTORE_B64:-}${MOD_SIGNING_PASSWORD:-}" ]]; then
  echo 'Incomplete signing configuration: provide both required secrets' >&2
  exit 1
else
  # DIAGNOSTIC ONLY. Neither APK nor key is uploaded. Not a replacement for a stable user key.
  export WITNESS_PASSWORD="$(openssl rand -hex 24)"
  echo "::add-mask::$WITNESS_PASSWORD"
  key="$RUNNER_TEMP/witness-diagnostic-only.p12"
  keytool -genkeypair -keystore "$key" -storetype PKCS12 -storepass:env WITNESS_PASSWORD \
    -keypass:env WITNESS_PASSWORD -alias mod -keyalg RSA -keysize 3072 -validity 365 \
    -dname 'CN=CI Diagnostic Only - Not For Delivery' > /dev/null 2>&1
  output=.work/witness/diagnostic/base-witness-test.apk
  printf 'ephemeral-ci-diagnostic-only; stable signing NOT CONFIGURED; no installable APK delivered\n' > .work/witness/reports/signing-mode.txt
fi
"$bt/apksigner" sign --ks "$key" --ks-key-alias mod --ks-pass env:WITNESS_PASSWORD \
  --key-pass env:WITNESS_PASSWORD --min-sdk-version 21 --v4-signing-enabled false \
  --out "$output" .work/witness/delivery/base-witness-unsigned.apk
"$bt/apksigner" verify --verbose --print-certs "$output" > .work/witness/reports/witness-signature.txt
"$bt/apksigner" verify --verbose --print-certs base.apk > .work/witness/reports/original-signature.txt
"$bt/zipalign" -c -P 16 -v 4 "$output" > .work/witness/reports/alignment.txt
sha256sum base.apk .work/witness/delivery/*.apk > .work/witness/reports/SHA256SUMS
printf '%s\n' "$output" > .work/witness/diagnostic/path.txt
