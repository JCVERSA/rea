#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
[[ -f base.apk ]] || { echo 'Missing base.apk' >&2; exit 1; }
work=.work/base-analysis
[[ ! -e "$work" ]] || { echo 'Use a fresh workspace; existing analysis is not overwritten' >&2; exit 1; }
mkdir -p "$work"
java -Xmx2g -jar .tools/apktool.jar d base.apk -o "$work/apktool" > "$work/apktool.log" 2>&1
set +e
.tools/jadx/bin/jadx -j 2 --show-bad-code --no-res -d "$work/jadx" base.apk > "$work/jadx.log" 2>&1
status=$?
set -e
printf '%s\n' "$status" > "$work/jadx-exit-code.txt"
python3 scripts/export_base.py
