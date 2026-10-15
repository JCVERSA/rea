"""Guarded cumulative AUD-06(partial),08,10,11 patches. No schema/credential migration."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
import sys
import nextcloud_tls_patch as tls
import http_loopback_patch as http
import media_grant_patch as grants

ROOT = Path(__file__).resolve().parent.parent
FTP = 'smali/com/alphainventor/filemanager/service/a.smali'
READER = 'smali/com/alphainventor/filemanager/service/FtpServerService$b.smali'
ZIP = 'smali/ax/f2/c.smali'
PROVIDER = 'smali/com/alphainventor/filemanager/provider/MyFileProvider.smali'
HASHES = {
    FTP: 'a0d9ccaed200e5ce0a3b27293210129e0716f302377891e82d81895bb713b488',
    READER: '338d3b53182d26ac92997460d8d75e3c692c4e2b81c02bb56b273a3d53cb53ee',
    ZIP: 'c5bacf9a2202b58d91606a05644291feb7ac3be154cdaa3e1e74f884a4a0b5ea',
    PROVIDER: '949655e48877ebdb85b43498ac0d618d1b20d479cc454b1f5893fe7d54b2c662',
}
HELPERS = {'smali_classes3/rea/security/' + name + '.smali':
           '.work/safety/decoded/smali/rea/security/' + name + '.smali'
           for name in ['Safety', 'ControlReader']}


def once(data, old, new):
    old, new = old.encode(), new.encode()
    if data.count(old) != 1:
        raise ValueError('Ambiguous/missing safety patch anchor: ' + old.decode())
    return data.replace(old, new, 1)


def patch_bytes(name, data):
    if name == tls.ENTRY:
        # This class already has the TLS patch, never reintroduce proceed().
        with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as z:
            expected = tls.patch_bytes(z.read(name))
        if data != expected:
            raise ValueError('Expected exact prior TLS callback patch')
        return once(data,
            'invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;',
            'const-string v2, "[redacted]"\n    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;')
    if name not in HASHES or hashlib.sha256(data).hexdigest() != HASHES[name]:
        raise ValueError('Unexpected safety source: ' + name)
    if name == FTP:
        # Preserve move-object/from16 v17,v4 and the command itself in v2.
        return once(data,
            'invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;',
            'const-string v4, "[redacted]"\n    invoke-virtual {v9, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;')
    if name == ZIP:
        return once(data,
            'invoke-virtual {v7, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z',
            'invoke-static {v7, v0}, Lrea/security/Safety;->isInside(Ljava/lang/String;Ljava/lang/String;)Z')
    if name == PROVIDER:
        return once(data,
            'invoke-virtual {p1}, Ljava/util/concurrent/CountDownLatch;->await()V',
            'invoke-static {p1}, Lrea/security/Safety;->awaitMetadata(Ljava/util/concurrent/CountDownLatch;)Z\n    move-result p3\n    if-eqz p3, :cond_9')
    if name == READER:
        data = once(data, 'new-instance v3, Ljava/io/BufferedReader;',
                          'new-instance v3, Lrea/security/ControlReader;')
        return once(data,
            'invoke-direct {v3, v4, v5}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;I)V',
            'invoke-direct {v3, v4, v5}, Lrea/security/ControlReader;-><init>(Ljava/io/Reader;I)V')


def expected_original(name, data):
    if name == tls.ENTRY: data = tls.patch_bytes(data)
    if name == http.ENTRY: data = http.patch_bytes(data)
    if name == grants.ENTRY: data = grants.patch_bytes(data)
    if name in HASHES or name == tls.ENTRY: data = patch_bytes(name, data)
    return data


def apply():
    tree = ROOT / '.work/witness/tree'
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as z:
        names = {n for n in z.namelist() if n.endswith('.smali')}
        actual = {p.relative_to(tree).as_posix() for p in tree.rglob('*.smali')}
        if actual != names | {grants.HELPER}:
            raise ValueError('Unexpected pre-safety class set')
        staged = {}
        for name in names:
            before = z.read(name)
            if name == tls.ENTRY: before = tls.patch_bytes(before)
            if name == http.ENTRY: before = http.patch_bytes(before)
            if name == grants.ENTRY: before = grants.patch_bytes(before)
            if (tree / name).read_bytes() != before:
                raise ValueError('Unexpected pre-safety class delta: ' + name)
            if name in HASHES or name == tls.ENTRY:
                staged[name] = patch_bytes(name, before)
    if (tree / grants.HELPER).read_bytes() != (ROOT / grants.GENERATED).read_bytes():
        raise ValueError('Unexpected grant policy')
    generated = ROOT / '.work/safety/decoded/smali'
    if {p.relative_to(generated).as_posix() for p in generated.rglob('*.smali')} != {
            'rea/security/Safety.smali', 'rea/security/ControlReader.smali'}:
        raise ValueError('Unexpected compiled safety helper set')
    for target, source in HELPERS.items(): staged[target] = (ROOT / source).read_bytes()
    for name, data in staged.items():
        (tree / name).parent.mkdir(parents=True, exist_ok=True)
        (tree / name).write_bytes(data)
    (ROOT / '.work/witness/reports/safety-source.json').write_text(json.dumps({
        'changed_or_added': {n: hashlib.sha256(d).hexdigest() for n, d in staged.items()},
        'metadata_deadline_ms': 30000, 'ftp_control_line_max_chars': 8192,
        'limits': 'Metadata worker not cancelled. No HTTP/FTP concurrency quota, local app authentication, symlink policy or credential/database migration.'
    }, indent=2) + '\n')


if __name__ == '__main__':
    if sys.argv[1:] != ['apply']: raise SystemExit('Usage: safety_patch.py apply')
    apply()
