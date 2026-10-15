"""Read-only APK audit inventories; never execute application code or expose secrets."""
from pathlib import Path
from zipfile import ZipFile
from collections import defaultdict
import base64
import hashlib
import json
import re
import struct
import subprocess
import tempfile
import xml.etree.ElementTree as ET

ROOT = Path(__file__).resolve().parent.parent
ANDROID = '{http://schemas.android.com/apk/res/android}'
AOSP_TEST_CERT = 'a40da80a59d170caa950cf15c18c454d47a39b26989d8b640ecd745ba71bf5dc'


def raw_manifest_elements(data):
    """Read binary XML attributes, retaining raw boolean type/data (no APK execution)."""
    u16 = lambda p: struct.unpack_from('<H', data, p)[0]
    u32 = lambda p: struct.unpack_from('<I', data, p)[0]
    if len(data) < 8 or u16(0) != 3 or u32(4) != len(data):
        raise ValueError('Invalid binary XML header')
    strings, elements = [], []
    p = u16(2)
    while p < len(data):
        kind, header, size = u16(p), u16(p + 2), u32(p + 4)
        if size < header or size < 8 or p + size > len(data):
            raise ValueError('Invalid binary XML chunk')
        if kind == 1:
            count, flags, start = u32(p + 8), u32(p + 16), p + u32(p + 20)
            def length(q, utf8):
                if utf8:
                    a = data[q]
                    return (((a & 127) << 8) | data[q + 1], q + 2) if a & 128 else (a, q + 1)
                a = u16(q)
                return (((a & 32767) << 16) | u16(q + 2), q + 4) if a & 32768 else (a, q + 2)
            for i in range(count):
                q = start + u32(p + header + 4 * i)
                if flags & 256:
                    _, q = length(q, True)
                    n, q = length(q, True)
                    strings.append(data[q:q + n].decode('utf-8'))
                else:
                    n, q = length(q, False)
                    strings.append(data[q:q + 2 * n].decode('utf-16le'))
        elif kind == 0x102:
            name, attrs = strings[u32(p + 20)], {}
            start = p + 16 + u16(p + 24)
            for i in range(u16(p + 28)):
                q = start + i * u16(p + 26)
                attr, typ, value = strings[u32(q + 4)], data[q + 15], u32(q + 16)
                attrs[attr] = {'type': typ, 'data': value, 'value': strings[value] if typ == 3 else value}
            if name == 'provider' and 'MyFileProvider' in str(attrs.get('name', {}).get('value')):
                elements.append({'tag': name, 'attributes': attrs})
        p += size
    return elements


def jar_sections(data):
    sections = []
    for block in data.replace(b'\r\n', b'\n').split(b'\n\n'):
        lines = []
        for line in block.splitlines():
            if line.startswith(b' ') and lines:
                lines[-1] += line[1:]
            else:
                lines.append(line)
        fields = dict(line.decode().split(': ', 1) for line in lines if b': ' in line)
        if fields:
            sections.append(fields)
    return sections


def signing_certificate_inventory(apk):
    """Verify CMS/SF and listed v1 content hashes, not Android v2/v3 verification."""
    with ZipFile(apk) as z, tempfile.TemporaryDirectory() as tmp:
        rsa = z.read('META-INF/BNDLTOOL.RSA')
        sf = z.read('META-INF/BNDLTOOL.SF')
        mf = z.read('META-INF/MANIFEST.MF')
        path = Path(tmp)
        (path / 'signer.rsa').write_bytes(rsa)
        (path / 'signer.sf').write_bytes(sf)
        subprocess.run(['openssl', 'pkcs7', '-inform', 'DER', '-in', str(path / 'signer.rsa'), '-print_certs', '-out', str(path / 'cert.pem')], check=True, capture_output=True)
        cert = subprocess.check_output(['openssl', 'x509', '-in', str(path / 'cert.pem'), '-outform', 'DER'])
        fingerprint = hashlib.sha256(cert).hexdigest()
        cms = subprocess.run(['openssl', 'cms', '-verify', '-binary', '-inform', 'DER', '-in', str(path / 'signer.rsa'), '-content', str(path / 'signer.sf'), '-noverify', '-out', str(path / 'discard')], capture_output=True)
        mf_ok = base64.b64encode(hashlib.sha256(mf).digest()).decode() == jar_sections(sf)[0].get('SHA-256-Digest-Manifest')
        mismatches, checked = [], 0
        for section in jar_sections(mf):
            if 'Name' in section:
                name = section['Name']
                expected = section.get('SHA-256-Digest')
                if expected is None:
                    raise ValueError('Unexpected v1 digest algorithm')
                checked += 1
                if base64.b64encode(hashlib.sha256(z.read(name)).digest()).decode() != expected:
                    mismatches.append(name)
        return {'certificate_sha256': fingerprint, 'matches_aosp_public_test_certificate': fingerprint == AOSP_TEST_CERT,
                'v1_cms_signature_verified': cms.returncode == 0, 'sf_whole_manifest_digest_matches': mf_ok,
                'listed_entry_digests_checked': checked, 'listed_entry_digest_mismatches': mismatches,
                'scope': 'CMS/SF and hashes of JAR-listed entries only; not full Android apksigner verification, not a trusted certificate chain, no v2/v3 verification',
                'reference': 'https://github.com/aosp-mirror/platform_build/blob/main/target/product/security/testkey.x509.pem'}


def inventory():
    output = ROOT / 'analysis/audit'
    output.mkdir(parents=True, exist_ok=True)
    apk = ROOT / 'base.apk'
    with apk.open('rb') as stream:
        digest = hashlib.file_digest(stream, 'sha256').hexdigest()
    expected = json.loads((ROOT / 'decompiled/index.json').read_text())['sha256']
    if digest != expected:
        raise ValueError('APK/reference mismatch')
    def save(name, value):
        (output / name).write_text(json.dumps({'input_sha256': digest, **value}, indent=2) + '\n')
    manifest = ET.parse(ROOT / 'decompiled/apktool/AndroidManifest.xml').getroot()
    components = []
    for el in manifest.find('application'):
        if el.tag in {'activity', 'activity-alias', 'provider', 'service', 'receiver'}:
            attrs = {k.replace(ANDROID, ''): v for k, v in el.attrib.items()}
            components.append({'kind': el.tag, 'attributes': attrs, 'intent_filters': [
                {'actions': [a.get(ANDROID + 'name') for a in f.findall('action')],
                 'schemes': [a.get(ANDROID + 'scheme') for a in f.findall('data') if a.get(ANDROID + 'scheme')]}
                for f in el.findall('intent-filter')]})
    save('manifest.json', {'components': components, 'permissions': [dict(e.attrib) for e in manifest if e.tag.startswith('uses-permission')]})
    with ZipFile(apk) as z:
        save('raw-provider-attributes.json', {'elements': raw_manifest_elements(z.read('AndroidManifest.xml'))})
        dependencies = []
        for name in z.namelist():
            if name.endswith('.properties'):
                text = z.read(name).decode('utf-8', errors='replace')
                props = dict(line.split('=', 1) for line in text.splitlines() if '=' in line and not line.startswith('#'))
                if 'version' in props and 'client' in props:
                    dependencies.append({'file': name, 'client': props['client'], 'reported_version': props['version']})
        save('dependency-metadata.json', {'scope': 'Self-reported APK properties, not a resolved Gradle SBOM or CVE assessment', 'items': dependencies})
    save('signing.json', signing_certificate_inventory(apk))
    domains = defaultdict(set)
    source_count = 0
    with ZipFile(ROOT / 'decompiled/all-java-sources.zip') as archive:
        for name in archive.namelist():
            if not name.endswith('.java'):
                continue
            source_count += 1
            text = archive.read(name).decode()
            for host in re.findall(r'https?://([A-Za-z0-9.-]+)', text):
                if '.' in host and not host.endswith('.'):
                    domains[host.lower()].add(name)
    save('domain-inventory.json', {'scope': 'Hosts found in recovered Java literals (including dependencies/examples); not observed traffic or active endpoints. No paths/query strings/credentials exported.',
        'sources_scanned': source_count, 'domains': [{'host': h, 'source_file_count': len(v), 'example_sources': sorted(v)[:3]} for h, v in sorted(domains.items())]})
    print('Read-only audit inventories saved:', output.relative_to(ROOT))


if __name__ == '__main__':
    inventory()
