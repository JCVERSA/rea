#!/usr/bin/env bash
set -euo pipefail
out=.work/safety-probe
mkdir -p "$out/classes" "$out/dex"
python3 - <<'PY'
import sys
from pathlib import Path
sys.path.insert(0,'scripts')
import slate_resources as s
names=list(s.COLORS);ids=s.ids()
def color(name,night,seen=()):
    if name in seen:raise ValueError('Cyclic palette')
    x=s.COLORS[name][night]
    return color(x[1:],night,seen+(name,)) if x.startswith('@') else int('ff'+x,16)
source='package rea.test;\npublic final class PaletteIds {\n'
source+='public static final String[] NAMES={'+','.join('"'+n+'"' for n in names)+'};\n'
for name,values in [('IDS',[ids[n] for n in names]),('LIGHT',[color(n,False) for n in names]),('NIGHT',[color(n,True) for n in names])]:
    source+='public static final int[] '+name+'={'+','.join(hex(v) for v in values)+'};\n'
Path('.work/safety-probe/PaletteIds.java').write_text(source+'}\n')
PY
sdk="$ANDROID_HOME/platforms/android-31/android.jar"
javac -source 8 -target 8 -cp "$sdk:.work/safety/classes" -d "$out/classes" tests/java/SafetyTest.java tests/android/SafetyProbe.java "$out/PaletteIds.java"
[[ ! -e "$out/classes/rea/security/Safety.class" && ! -e "$out/classes/rea/security/ControlReader.class" ]]
mapfile -t classes < <(find "$out/classes" -name '*.class' -print)
"$ANDROID_HOME/build-tools/35.0.0/d8" --lib "$sdk" --classpath .work/safety/classes --min-api 21 --output "$out/dex" "${classes[@]}"
python3 - <<'PY'
from pathlib import Path
from zipfile import ZipFile
with ZipFile('.work/safety-probe/probe.jar','w') as z:
    for p in Path('.work/safety-probe/dex').glob('*.dex'):z.write(p,p.name)
PY
