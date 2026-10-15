"""Supplement static export with nested ELF and exact known-hook inventories."""
from pathlib import Path
from zipfile import ZipFile, BadZipFile
import hashlib
import io
import json
import struct

root = Path(__file__).resolve().parent.parent
apk = root / 'base.apk'
out = root / 'analysis'
out.mkdir(exist_ok=True)
with apk.open('rb') as stream:
    digest = hashlib.file_digest(stream, 'sha256').hexdigest()
libraries = []
with ZipFile(apk) as outer:
    for name in outer.namelist():
        if not name.startswith('assets/') or not name.endswith('.zip'):
            continue
        if outer.getinfo(name).file_size > 128 * 1024 * 1024:
            raise ValueError('Nested ZIP exceeds inspection budget')
        try:
            nested = ZipFile(io.BytesIO(outer.read(name)))
        except BadZipFile:
            continue
        with nested:
            if sum(e.file_size for e in nested.infolist()) > 256 * 1024 * 1024:
                raise ValueError('Expanded nested ZIP exceeds inspection budget')
            for entry in nested.namelist():
                if not entry.endswith('.so'):
                    continue
                data = nested.read(entry)
                if len(data) < 20 or data[:4] != b'\x7fELF' or data[4] not in (1, 2) or data[5] not in (1, 2):
                    raise ValueError(f'Invalid ELF header: {entry}')
                libraries.append({
                    'path': name + '!/' + entry, 'bytes': len(data),
                    'sha256': hashlib.sha256(data).hexdigest(),
                    'elf_class': {1: 32, 2: 64}[data[4]],
                    'elf_machine': struct.unpack_from('<H' if data[5] == 1 else '>H', data, 18)[0],
                })
(out / 'nested-native.json').write_text(json.dumps({
    'input_sha256': digest, 'method': 'ZIP inventory and ELF headers only; not execution/disassembly',
    'libraries': libraries,
}, indent=2) + '\n')
terms = ('liteapks', 'update.9mod.com', 'Xpk8a', 'com.zx.LB')
hits = []
with ZipFile(root / 'decompiled/all-java-sources.zip') as archive:
    for name in archive.namelist():
        if not name.endswith('.java'):
            continue
        text = archive.read(name).decode()
        found = [t for t in terms if t.lower() in text.lower()]
        if found:
            hits.append({'path': name, 'terms': found})
(out / 'known-hook-scan.json').write_text(json.dumps({
    'input_sha256': digest, 'scope': 'Case-insensitive exact strings in recovered Java only; not an authenticity/malware verdict',
    'terms': terms, 'hits': hits,
}, indent=2) + '\n')
print('Nested ELF libraries:', len(libraries), '; known-hook Java hits:', len(hits))
