"""AUD-04b: typed directory/media grants with compiled, reviewable Java policy."""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
import re
import sys
import nextcloud_tls_patch as tls
import http_loopback_patch as http

ROOT = Path(__file__).resolve().parent.parent
ENTRY = 'smali/com/alphainventor/filemanager/service/b.smali'
SHA256 = '947873dfd171b182548a8339f7adc216a69bac3e38920caa22ff3e0d4f18fe33'
HELPER = 'smali_classes3/rea/security/MediaGrant.smali'
GENERATED = '.work/media-grant/decoded/smali/rea/security/MediaGrant.smali'


def patch_bytes(data):
    if hashlib.sha256(data).hexdigest() != SHA256:
        raise ValueError('Unexpected manager source; refusing blind/repeated patch')
    # The directory is still selected by the original V.t parent operation.
    directory = b'''    invoke-static {p1}, Lax/J1/V;->t(Ljava/lang/String;)Ljava/lang/String;

    .line 2
    .line 3
    .line 4
    move-result-object p1'''
    typed_directory = directory + b'''\n
    invoke-static {p1}, Lrea/security/MediaGrant;->directory(Ljava/lang/String;)Ljava/lang/String;
    move-result-object p1'''
    replacements = [
        (directory, typed_directory),
        (b'invoke-static {v1}, Lax/J1/V;->p(Ljava/lang/String;)Ljava/lang/String;',
         b'invoke-static {v1}, Lrea/security/MediaGrant;->media(Ljava/lang/String;)Ljava/lang/String;'),
        (b'invoke-static {p1}, Lax/J1/V;->p(Ljava/lang/String;)Ljava/lang/String;',
         b'invoke-static {p1}, Lrea/security/MediaGrant;->media(Ljava/lang/String;)Ljava/lang/String;'),
        (b'invoke-virtual {p1, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z',
         b'invoke-static {p1, v1}, Lrea/security/MediaGrant;->allows(Ljava/lang/String;Ljava/lang/String;)Z')]
    for old, new in replacements:
        if data.count(old) != 1:
            raise ValueError('Expected exactly one registration/predicate anchor')
        data = data.replace(old, new, 1)
    return data


def normalized_methods(data):
    methods = re.findall(r'^\.method [\s\S]*?^\.end method', data.decode(), re.M)
    result = {}
    for method in methods:
        labels = {}
        lines = [l.strip() for l in method.splitlines() if l.strip()
                 and not l.strip().startswith(('.line ', '#'))]
        text = '\n'.join(lines)
        text = re.sub(r'(?<!\S):[A-Za-z_][A-Za-z_0-9]*',
                      lambda m: labels.setdefault(m[0], ':label' + str(len(labels))), text)
        if lines[0] in result:
            raise ValueError('Duplicate method')
        result[lines[0]] = text
    if not result:
        raise ValueError('Missing methods')
    outside = re.sub(r'^\.method [\s\S]*?^\.end method', '', data.decode(), flags=re.M)
    result['__class_contract__'] = '\n'.join(l.strip() for l in outside.splitlines()
        if l.strip() and not l.strip().startswith(('#', '.source ')))
    return result


def apply():
    tree = ROOT / '.work/witness/tree'
    generated = ROOT / GENERATED
    if not generated.is_file():
        raise ValueError('Compile/test the Java grant policy first')
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
        names = {n for n in reference.namelist() if n.endswith('.smali')}
        if names != {str(p.relative_to(tree)) for p in tree.rglob('*.smali')}:
            raise ValueError('Unexpected pre-grant class set')
        for name in names:
            expected = reference.read(name)
            if name == tls.ENTRY:
                expected = tls.patch_bytes(expected)
            elif name == http.ENTRY:
                expected = http.patch_bytes(expected)
            if (tree / name).read_bytes() != expected:
                raise ValueError('Unexpected pre-grant delta: ' + name)
        manager = patch_bytes(reference.read(ENTRY))
    helpers = list(generated.parents[3].rglob('*.smali'))
    if helpers != [generated]:
        raise ValueError('Expected exactly one compiled helper class')
    helper = generated.read_bytes()
    if b'.class public final Lrea/security/MediaGrant;' not in helper:
        raise ValueError('Unexpected helper class descriptor')
    (tree / ENTRY).write_bytes(manager)
    (tree / HELPER).parent.mkdir(parents=True, exist_ok=True)
    (tree / HELPER).write_bytes(helper)
    reports = ROOT / '.work/witness/reports'
    (reports / 'grant-patch-source.json').write_text(json.dumps({
        'variant': 'tls-http-grants-v1', 'changed_original_classes': [tls.ENTRY, http.ENTRY, ENTRY],
        'added_class': HELPER, 'manager_before_sha256': SHA256,
        'manager_after_sha256': hashlib.sha256(manager).hexdigest(),
        'helper_smali_sha256': hashlib.sha256(helper).hexdigest(),
        'policy_java_sha256': hashlib.sha256((ROOT / 'patches/java/rea/security/MediaGrant.java').read_bytes()).hexdigest(),
        'other_original_smali_equal_before_assembly': True,
        'remaining': 'Same-device clients, grant lifecycle, symlinks/backend canonicalization, full player/NAS tests.'
    }, indent=2) + '\n')


def verify_roundtrip(source, roundtrip):
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as reference:
        expected = patch_bytes(reference.read(ENTRY))
        baseline_names = {n for n in reference.namelist() if n.endswith('.smali')}
    for tree in [source, roundtrip]:
        names = {str(p.relative_to(tree)) for p in tree.rglob('*.smali')}
        if names != baseline_names | {HELPER}:
            raise ValueError('Unexpected added, moved or missing classes')
    if (source / ENTRY).read_bytes() != expected:
        raise ValueError('Unexpected prepared manager patch')
    for name, expected_data in [(ENTRY, expected), (HELPER, (ROOT / GENERATED).read_bytes())]:
        wanted = normalized_methods(expected_data)
        actual_methods = normalized_methods((roundtrip / name).read_bytes())
        if wanted != actual_methods:
            changes = {key: {'expected': wanted.get(key), 'actual': actual_methods.get(key)}
                       for key in wanted.keys() | actual_methods.keys()
                       if wanted.get(key) != actual_methods.get(key)}
            (ROOT / '.work/witness/reports/grant-method-diff.json').write_text(
                json.dumps({'entry': name, 'changes': changes}, indent=2) + '\n')
            raise ValueError('Assembled grant manager/policy methods differ: ' + name)
    return {'typed_grants': True, 'manager_and_policy_methods_match': True,
            'added_class': HELPER, 'subtitle_family_explicit': True,
            'directory_lexical_boundary_checked': True,
            'backend_symlink_canonicalization_checked': False, 'grant_revocation_fixed': False}


if __name__ == '__main__':
    if sys.argv[1:] != ['apply']:
        raise SystemExit('Usage: python scripts/media_grant_patch.py apply')
    apply()
