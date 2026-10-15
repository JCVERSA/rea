"""AUD-02: one guarded smali call replacement; no global trust override."""
from pathlib import Path
import hashlib
import json
import re
import sys
from zipfile import ZipFile

ROOT = Path(__file__).resolve().parent.parent
ENTRY = 'smali/ax/S1/b$f.smali'
SHA256 = 'e3e399ea09ec6bb546a774dbc02f079201dadb6039ddef2aeba97c6ab1f1477e'
METHOD = '.method public onReceivedSslError(Landroid/webkit/WebView;Landroid/webkit/SslErrorHandler;Landroid/net/http/SslError;)V'
OLD = b'invoke-virtual {p2}, Landroid/webkit/SslErrorHandler;->proceed()V'
NEW = b'invoke-virtual {p2}, Landroid/webkit/SslErrorHandler;->cancel()V'


def method(data):
    text = data.decode('utf-8')
    if text.count(METHOD) != 1:
        raise ValueError('Expected exactly one SSL callback')
    start = text.index(METHOD)
    return text[start:text.index('.end method', start) + len('.end method')]


def patch_bytes(data):
    if hashlib.sha256(data).hexdigest() != SHA256:
        raise ValueError('Unexpected source class; refusing a blind or repeated patch')
    body = method(data).encode()
    if body.count(OLD) != 1 or data.count(OLD) != 1:
        raise ValueError('Expected exactly one known proceed call')
    return data.replace(OLD, NEW, 1)


def instructions(data):
    # Ignore debug line numbers and assembler-generated label names, not opcodes.
    labels = {}
    text = '\n'.join(line.strip() for line in method(data).splitlines()
                     if line.strip() and not line.strip().startswith('.line '))
    return re.sub(r'(?<!\S):[A-Za-z_][A-Za-z_0-9]*',
                  lambda m: labels.setdefault(m[0], ':label' + str(len(labels))), text)


def verify_callback(expected, actual):
    if instructions(expected) != instructions(actual):
        raise ValueError('Assembled SSL callback differs from the exact expected patch')
    body = method(actual)
    if 'SslErrorHandler;->proceed()V' in body or body.count('SslErrorHandler;->cancel()V') != 1:
        raise ValueError('SSL callback does not cancel exactly once')


def apply():
    tree = ROOT / '.work/witness/tree'
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
        original = reference.read(ENTRY)
        patched = patch_bytes(original)
        # Establish the complete smali delta before assembly, not only the target call.
        names = {n for n in reference.namelist() if n.endswith('.smali')}
        actual = {str(p.relative_to(tree)) for p in tree.rglob('*.smali')}
        if names != actual:
            raise ValueError('Unexpected source class set')
        for name in names:
            if (tree / name).read_bytes() != reference.read(name):
                raise ValueError('Unexpected pre-patch source difference: ' + name)
        (tree / ENTRY).write_bytes(patched)
    reports = ROOT / '.work/witness/reports'
    reports.mkdir(exist_ok=True)
    (reports / 'tls-patch-source.json').write_text(json.dumps({
        'patch': 'nextcloud-tls-v1', 'finding': 'AUD-02', 'changed_files': [ENTRY],
        'before_sha256': SHA256, 'after_sha256': hashlib.sha256(patched).hexdigest(),
        'change': 'SslErrorHandler.proceed() -> cancel() in Nextcloud SSL-error branch only',
        'all_other_smali_equal_before_assembly': True,
        'runtime_tls_handshake_tests': 'NOT RUN; emulator startup smoke is not a certificate test'
    }, indent=2) + '\n')


def verify_roundtrip(source, roundtrip):
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
        expected = patch_bytes(reference.read(ENTRY))
    if (source / ENTRY).read_bytes() != expected:
        raise ValueError('Prepared reference is not the exact one-call TLS patch')
    verify_callback(expected, (roundtrip / ENTRY).read_bytes())
    return {'patch': 'nextcloud-tls-v1', 'callback_matches_expected_instructions': True,
            'nextcloud_ssl_error_cancels': True, 'other_branch_super_call_preserved': True,
            'runtime_valid_invalid_certificate_tests': 'NOT RUN'}


if __name__ == '__main__':
    if sys.argv[1:] != ['apply']:
        raise SystemExit('Usage: python scripts/nextcloud_tls_patch.py apply')
    apply()
