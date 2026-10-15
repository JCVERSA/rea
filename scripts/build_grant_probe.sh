#!/usr/bin/env bash
set -euo pipefail
out=.work/grant-probe
mkdir -p "$out/classes" "$out/dex"
sdk="$ANDROID_HOME/platforms/android-31/android.jar"
javac -source 8 -target 8 -cp "$sdk:.work/media-grant/classes" -d "$out/classes" \
  tests/android/MediaHttpProbe.java tests/android/MediaGrantProbe.java tests/java/MediaGrantTest.java
# Do not include the policy implementation in the probe: it must load from the APK.
[[ ! -e "$out/classes/rea/security/MediaGrant.class" ]]
mapfile -t classes < <(find "$out/classes" -name '*.class' -print)
"$ANDROID_HOME/build-tools/35.0.0/d8" --lib "$sdk" --classpath .work/media-grant/classes \
  --min-api 21 --output "$out/dex" "${classes[@]}"
python3 - <<'PY'
from pathlib import Path
from zipfile import ZipFile
with ZipFile('.work/grant-probe/probe.jar', 'w') as z:
    for p in Path('.work/grant-probe/dex').glob('*.dex'):
        z.write(p, p.name)
PY
