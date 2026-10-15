"""Verify base APK exports without executing application code."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
import xml.etree.ElementTree as ET

root = Path(__file__).resolve().parent.parent
out = root / 'decompiled'
index = json.loads((out / 'index.json').read_text())
with (root / 'base.apk').open('rb') as stream:
    assert hashlib.file_digest(stream, 'sha256').hexdigest() == index['sha256']
for name in ('all-java-sources.zip', 'complete-apktool.zip'):
    with ZipFile(out / name) as archive:
        assert archive.testzip() is None, name
        assert all(not Path(n).is_absolute() and '..' not in Path(n).parts for n in archive.namelist())
        if name == 'all-java-sources.zip':
            assert sum(n.endswith('.java') for n in archive.namelist()) == index['java_files_total']
        else:
            assert 'AndroidManifest.xml' in archive.namelist()
            assert any(n.startswith('smali/') for n in archive.namelist())
        print(name, len(archive.namelist()), 'entries, CRC PASS')
xml = list((out / 'apktool').rglob('*.xml'))
for path in xml:
    ET.parse(path)
assert ET.parse(out / 'apktool/AndroidManifest.xml').getroot().get('package') == index['package']
print('Fingerprint, source count, package and', len(xml), 'XML files: PASS')
print('JADX exit:', index['jadx_exit_code'], '(recorded, not hidden)')
rea = root / 'analysis/rea'
for path in sorted(rea.glob('*.exit-code.txt')):
    print('REA', path.stem, 'exit', path.read_text().strip())
