"""Export complete generated references, keeping dependency file counts out of Git."""
from pathlib import Path
from zipfile import ZipFile, ZIP_DEFLATED
import hashlib
import json
import shutil
import xml.etree.ElementTree as ET

root = Path(__file__).resolve().parent.parent
work = root / '.work/base-analysis'
out = root / 'decompiled'
out.mkdir(exist_ok=True)
apk = root / 'base.apk'
with apk.open('rb') as stream:
    digest = hashlib.file_digest(stream, 'sha256').hexdigest()
(out / 'APK.sha256').write_text(f'{digest}  base.apk\n')
manifest = ET.parse(work / 'apktool/AndroidManifest.xml').getroot()
package = manifest.get('package')
if not package:
    raise ValueError('Manifest package missing')
java_files = sorted((work / 'jadx/sources').rglob('*.java'))
if not java_files:
    raise ValueError('No JADX sources produced')
for name, directory in [('all-java-sources.zip', work / 'jadx'), ('complete-apktool.zip', work / 'apktool')]:
    with ZipFile(out / name, 'w', ZIP_DEFLATED) as archive:
        for path in sorted(directory.rglob('*')):
            if path.is_file():
                archive.write(path, path.relative_to(directory))
# Expand resources and package-specific Java; all other code remains in the ZIPs.
expanded = out / 'apktool'
expanded.mkdir(exist_ok=True)
for name in ('res', 'assets'):
    source = work / 'apktool' / name
    if source.exists():
        shutil.copytree(source, expanded / name, dirs_exist_ok=True)
for name in ('AndroidManifest.xml', 'apktool.yml'):
    shutil.copy2(work / 'apktool' / name, expanded / name)
app = work / 'jadx/sources' / package.replace('.', '/')
if app.exists():
    shutil.copytree(app, out / 'jadx/sources' / package.replace('.', '/'), dirs_exist_ok=True)
for name in ('jadx.log', 'apktool.log', 'jadx-exit-code.txt'):
    shutil.copy2(work / name, out / name)
with ZipFile(apk) as archive:
    inventory = {
        'filename': 'base.apk', 'bytes': apk.stat().st_size, 'sha256': digest,
        'package': package, 'java_files_total': len(java_files),
        'java_files_in_package': len(list(app.rglob('*.java'))) if app.exists() else 0,
        'jadx_exit_code': int((work / 'jadx-exit-code.txt').read_text()),
        'dex_files': [n for n in archive.namelist() if n.endswith('.dex')],
        'native_libraries': [n for n in archive.namelist() if n.startswith('lib/')],
    }
(out / 'index.json').write_text(json.dumps(inventory, indent=2) + '\n')
print(json.dumps(inventory, indent=2))
