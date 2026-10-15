#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p analysis/rea
export REA_JADX_MCP_JAR="$PWD/.tools/jadx-headless-mcp.jar"
export REA_APKTOOL_COMMAND="$PWD/.tools/apktool"
export REA_JADX_HEAP_MIB=2048
export REA_JADX_ACTIVE_PROCESSOR_COUNT=2
rea="$PWD/.tools/rea-client/node_modules/.bin/rea"
# Record failures explicitly; source export remains useful if an optional REA lane fails.
run() {
  local name="$1"; shift
  local status=0
  "$rea" "$@" --format json --full-output > "analysis/rea/$name.json" 2> "analysis/rea/$name.stderr.log" || status=$?
  printf '%s\n' "$status" > "analysis/rea/$name.exit-code.txt"
}
run apktool-client inspect-apktool-client
run package inspect-android-package "$PWD/base.apk"
run resources decode-android-resources "$PWD/base.apk" --no-strings
run main-classes search-android-classes "$PWD/base.apk" MainActivity
# Discover class identity from the decoded manifest, not a previous APK's namespace.
main=$(python3 - <<'PY'
import xml.etree.ElementTree as ET
r=ET.parse('decompiled/apktool/AndroidManifest.xml').getroot()
a='{http://schemas.android.com/apk/res/android}'
for e in r.findall('application/activity'):
    if e.get(a+'name','').endswith('.MainActivity'):
        name=e.get(a+'name'); print(r.get('package')+name if name.startswith('.') else name); break
PY
)
if [[ -n "$main" ]]; then
  run main-oncreate inspect-android-method "$PWD/base.apk" "$main" onCreate
  run main-references trace-android-references "$PWD/base.apk" "$main" --method-name onCreate
fi
node --version > analysis/rea/node-version.txt
"$rea" --version > analysis/rea/rea-version.txt
