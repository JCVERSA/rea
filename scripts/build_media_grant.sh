#!/usr/bin/env bash
set -euo pipefail
out=.work/media-grant
[[ ! -e "$out" ]] || { echo 'Use a fresh media grant compiler workspace' >&2; exit 1; }
mkdir -p "$out/classes" "$out/dex" .work/witness/reports
javac --release 8 -d "$out/classes" patches/java/rea/security/MediaGrant.java tests/java/MediaGrantTest.java
java -cp "$out/classes" MediaGrantTest | tee .work/witness/reports/grant-jvm-tests.txt
"$ANDROID_HOME/build-tools/35.0.0/d8" --lib "$ANDROID_HOME/platforms/android-31/android.jar" \
  --min-api 21 --output "$out/dex" "$out/classes/rea/security/MediaGrant.class"
python3 - <<'PY'
from zipfile import ZipFile
# A disposable decode container, never an installable/user-delivered APK.
with ZipFile('base.apk') as original, ZipFile('.work/media-grant/helper.apk', 'w') as helper:
    for name in ['AndroidManifest.xml', 'resources.arsc']:
        helper.writestr(name, original.read(name))
    helper.write('.work/media-grant/dex/classes.dex', 'classes.dex')
PY
java -jar .tools/apktool.jar d -r "$out/helper.apk" -o "$out/decoded"
