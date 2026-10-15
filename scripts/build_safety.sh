#!/usr/bin/env bash
set -euo pipefail
out=.work/safety
[[ ! -e "$out" ]] || { echo 'Use a fresh safety workspace' >&2; exit 1; }
mkdir -p "$out/classes" "$out/dex" .work/witness/reports
javac --release 8 -d "$out/classes" patches/java/rea/security/Safety.java patches/java/rea/security/ControlReader.java tests/java/SafetyTest.java
java -cp "$out/classes" SafetyTest | tee .work/witness/reports/safety-jvm-tests.txt
"$ANDROID_HOME/build-tools/35.0.0/d8" --lib "$ANDROID_HOME/platforms/android-31/android.jar" --min-api 21 --output "$out/dex" "$out/classes/rea/security/"*.class
python3 - <<'PY'
from zipfile import ZipFile
with ZipFile('base.apk') as src, ZipFile('.work/safety/helper.apk', 'w') as out:
    for n in ['AndroidManifest.xml', 'resources.arsc']: out.writestr(n, src.read(n))
    out.write('.work/safety/dex/classes.dex', 'classes.dex')
PY
java -jar .tools/apktool.jar d -r "$out/helper.apk" -o "$out/decoded"
