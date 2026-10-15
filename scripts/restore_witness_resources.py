"""Code-only witness: retain original compiled resources, never silently discard a UI patch."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json

ROOT = Path(__file__).resolve().parent.parent
work = ROOT / '.work/witness'
apk = work / 'unsigned.apk'
replacement = work / 'preserved-resources.apk'
with (ROOT / 'base.apk').open('rb') as stream:
    assert hashlib.file_digest(stream, 'sha256').hexdigest() == '0f7dbe666b6be1c1f99f1b324c65899ebbca6cc56fe55d3809bc7684242b762b'

# This recipe is strictly for untouched resources/manifest. Refuse future XML edits
# instead of silently throwing them away when restoring the binary originals.
with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
    names = {n for n in reference.namelist() if n.startswith('res/') and not n.endswith('/')}
    actual = {str(p.relative_to(work / 'tree')) for p in (work / 'tree/res').rglob('*') if p.is_file()}
    assert names == actual, 'Resource tree changed; code-only witness recipe is not suitable'
    for name in names | {'AndroidManifest.xml'}:
        assert (work / 'tree' / name).read_bytes() == reference.read(name), f'Refusing resource/manifest patch: {name}'

with ZipFile(ROOT / 'base.apk') as original, ZipFile(apk) as assembled, ZipFile(replacement, 'w') as output:
    assert len(assembled.namelist()) == len(set(assembled.namelist())), 'Duplicate assembled ZIP entries'
    for entry in assembled.infolist():
        if entry.filename != 'resources.arsc' and not entry.filename.startswith('res/'):
            output.writestr(entry, assembled.read(entry))
    entries = [e for e in original.infolist() if e.filename == 'resources.arsc' or e.filename.startswith('res/')]
    assert any(e.filename == 'resources.arsc' for e in entries)
    for entry in entries:
        output.writestr(entry, original.read(entry))
replacement.replace(apk)
(work / 'reports').mkdir(exist_ok=True)
(work / 'reports/resource-recipe.json').write_text(json.dumps({
    'mode': 'code-only-reassembly-with-original-compiled-resources',
    'restored_entries': len(entries),
    'reason': 'Full resource compilation remaps 20 generated Material drawable IDs; not accepted for the unchanged witness.',
    'limits': 'Does not validate the resource recompilation path required for a future UI change.'
}, indent=2) + '\n')
