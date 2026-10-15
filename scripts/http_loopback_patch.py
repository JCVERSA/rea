"""AUD-04a: bind only the media bridge to IPv4 loopback; retain TLS patch."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
import sys
import nextcloud_tls_patch as tls

ROOT = Path(__file__).resolve().parent.parent
ENTRY = 'smali/ax/X1/b.smali'
SHA256 = 'a56b1aa43276558c1a9e698ea55be079dcc6ee130b0d5f2aa0f3d5b6d6c36de9'
METHOD = '.method public constructor <init>(Lcom/alphainventor/filemanager/service/b;I)V'
OLD = '''    .locals 0

    .line 1
    invoke-direct {p0, p2}, Lax/Ba/a;-><init>(I)V'''.encode()
NEW = '''    .locals 1

    .line 1
    const-string v0, "127.0.0.1"

    invoke-direct {p0, v0, p2}, Lax/Ba/a;-><init>(Ljava/lang/String;I)V'''.encode()


def constructor(data):
    text = data.decode()
    if text.count(METHOD) != 1:
        raise ValueError('Expected exactly one media server constructor')
    start = text.index(METHOD)
    return text[start:text.index('.end method', start) + len('.end method')]


def patch_bytes(data):
    if hashlib.sha256(data).hexdigest() != SHA256:
        raise ValueError('Unexpected HTTP class; refusing a blind or repeated patch')
    if constructor(data).encode().count(OLD) != 1 or data.count(OLD) != 1:
        raise ValueError('Expected exact one-argument super-constructor block')
    return data.replace(OLD, NEW, 1)


def verify_constructor(expected, actual):
    def instructions(data):
        return '\n'.join(line.strip() for line in constructor(data).splitlines()
                         if line.strip() and not line.strip().startswith('.line '))
    if instructions(expected) != instructions(actual):
        raise ValueError('HTTP constructor differs from the exact expected loopback patch')


def apply():
    tree = ROOT / '.work/witness/tree'
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
        names = {n for n in reference.namelist() if n.endswith('.smali')}
        if names != {str(p.relative_to(tree)) for p in tree.rglob('*.smali')}:
            raise ValueError('Unexpected source class set')
        for name in names:
            original = reference.read(name)
            expected = tls.patch_bytes(original) if name == tls.ENTRY else original
            if (tree / name).read_bytes() != expected:
                raise ValueError('Unexpected pre-HTTP delta (TLS patch required): ' + name)
        patched = patch_bytes(reference.read(ENTRY))
        (tree / ENTRY).write_bytes(patched)
    reports = ROOT / '.work/witness/reports'
    reports.mkdir(exist_ok=True)
    (reports / 'http-patch-source.json').write_text(json.dumps({
        'patch': 'tls-http-loopback-v1', 'finding': 'AUD-04a (partial)',
        'changed_files_cumulative': [tls.ENTRY, ENTRY], 'before_sha256': SHA256,
        'after_sha256': hashlib.sha256(patched).hexdigest(),
        'change': 'Media bridge super(host, port), host 127.0.0.1; constructor locals 0 -> 1',
        'all_other_smali_equal_before_assembly': True,
        'unchanged': ['media HTTP Range/ETag/HEAD code', 'path allowlist', 'FTP/LAN sharing implementation'],
        'remaining': 'Prefix authorization, stale grants, local-app access and LAN/casting compatibility need separate work/tests.'
    }, indent=2) + '\n')


def verify_roundtrip(source, roundtrip):
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
        expected = patch_bytes(reference.read(ENTRY))
    if (source / ENTRY).read_bytes() != expected:
        raise ValueError('Prepared HTTP source is not the exact expected patch')
    verify_constructor(expected, (roundtrip / ENTRY).read_bytes())
    return {'patch': 'AUD-04a', 'constructor_matches_expected': True,
            'bind_host': '127.0.0.1', 'prefix_authorization_fixed': False,
            'full_media_or_lan_functional_suite': 'NOT RUN'}


if __name__ == '__main__':
    if sys.argv[1:] != ['apply']:
        raise SystemExit('Usage: python scripts/http_loopback_patch.py apply')
    apply()
