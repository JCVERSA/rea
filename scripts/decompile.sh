#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
apk='File Manager v3.8.3 (Premium).apk'
[[ -f "$apk" ]] || { echo "Missing APK: $apk" >&2; exit 1; }
mkdir -p .work decompiled
sha256sum "$apk" > decompiled/APK.sha256
# Full smali is retained locally/in the artifact, not in Git (size/file-count).
java -Xmx2g -jar .tools/apktool.jar d -f "$apk" -o decompiled/apktool > .work/apktool.log 2>&1
# JADX can return nonzero for individual methods while producing useful output.
set +e
.tools/jadx/bin/jadx -j 2 --show-bad-code --no-res -d decompiled/jadx "$apk" > .work/jadx.log 2>&1
status=$?
set -e
printf '%s\n' "$status" > decompiled/jadx-exit-code.txt
[[ -d decompiled/jadx/sources ]] || { cat .work/jadx.log; exit 1; }
cp .work/jadx.log decompiled/jadx-diagnostics.log
# Retain application sources in Git; dependency sources remain in the artifact.
python3 scripts/index_decompilation.py
