"""Validate repository exports without executing the APK or needing Android tools."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
import xml.etree.ElementTree as ET

root = Path(__file__).resolve().parent.parent
output = root / 'decompiled'
apk = root / 'File Manager v3.8.3 (Premium).apk'
expected = (output / 'APK.sha256').read_text().split()[0]
with apk.open('rb') as stream:
    assert hashlib.file_digest(stream, 'sha256').hexdigest() == expected, 'APK fingerprint mismatch'
index = json.loads((output / 'index.json').read_text())
for name in ('all-java-sources.zip', 'apktool-bytecode.zip'):
    with ZipFile(output / name) as archive:
        assert archive.testzip() is None, f'Corrupt archive: {name}'
        assert all(not Path(n).is_absolute() and '..' not in Path(n).parts for n in archive.namelist())
        if name == 'all-java-sources.zip':
            assert len(archive.namelist()) == index['java_files_total']
            assert 'sources/defpackage/Xpk8a.java' in archive.namelist()
        else:
            assert any(n.startswith('smali/') for n in archive.namelist())
        print(f'{name}: {len(archive.namelist())} entries, CRC OK')
xml_files = list((output / 'apktool').rglob('*.xml'))
for path in xml_files:
    ET.parse(path)
manifest = ET.parse(output / 'apktool/AndroidManifest.xml').getroot()
assert manifest.get('package') == index['package']
print(f'{len(xml_files)} XML files parsed; package and APK fingerprint match')
print(f"JADX exit {index['jadx_exit_code']} retained as a known limitation")
