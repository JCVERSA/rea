#!/usr/bin/env bash
set -euo pipefail
sdk="$ANDROID_HOME/platforms/android-31/android.jar"
out=.work/http-probe
mkdir -p "$out/classes" "$out/dex"
javac -source 8 -target 8 -cp "$sdk" -d "$out/classes" tests/android/MediaHttpProbe.java
mapfile -t classes < <(find "$out/classes" -name '*.class' -print)
"$ANDROID_HOME/build-tools/35.0.0/d8" --lib "$sdk" --min-api 21 --output "$out/dex" "${classes[@]}"
python3 - <<'PY'
from pathlib import Path
from zipfile import ZipFile
with ZipFile('.work/http-probe/probe.jar', 'w') as z:
    for p in Path('.work/http-probe/dex').glob('*.dex'):
        z.write(p, p.name)
PY
