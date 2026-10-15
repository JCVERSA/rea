"""Restore an untouched decoded tree for the no-functional-change witness."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
from provider_contract import inspect_provider

ROOT = Path(__file__).resolve().parent.parent
out = ROOT / '.work/witness/tree'
if out.exists():
    raise ValueError('Use a fresh witness workspace')
with (ROOT / 'base.apk').open('rb') as stream:
    digest = hashlib.file_digest(stream, 'sha256').hexdigest()
index = json.loads((ROOT / 'decompiled/index.json').read_text())
if digest != index['sha256']:
    raise ValueError('APK fingerprint mismatch')
with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as archive:
    if archive.testzip() is not None:
        raise ValueError('Corrupt reference ZIP')
    for name in archive.namelist():
        if Path(name).is_absolute() or '..' in Path(name).parts:
            raise ValueError('Unsafe archive path')
    with ZipFile(ROOT / "base.apk") as original:
        if archive.read("original/AndroidManifest.xml") != original.read("AndroidManifest.xml"):
            raise ValueError("Original binary manifest mismatch")
    archive.extractall(out)
print(json.dumps({'input_sha256': digest, 'functional_patches_applied': 0,
                  'provider': inspect_provider(out / 'AndroidManifest.xml')}, indent=2))
