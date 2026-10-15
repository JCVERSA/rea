"""Check rebuilt decoded APK; complements apksigner and zipalign verification."""
from pathlib import Path
import sys
import xml.etree.ElementTree as ET
from prepare_mod import ROOT, MAIN, HOOKS

prepared, rebuilt = map(Path, sys.argv[1:])
reference = ROOT / 'decompiled/apktool'
android = '{http://schemas.android.com/apk/res/android}'
a = ET.parse(reference / 'AndroidManifest.xml').getroot()
b = ET.parse(rebuilt / 'AndroidManifest.xml').getroot()
assert a.get('package') == b.get('package'), 'Package changed'


def surface(manifest):
    # Compare permission requests and exposed app component contracts.
    result = []
    for element in manifest.iter():
        if element.tag.startswith('uses-permission') or element.tag in {
            'activity', 'activity-alias', 'service', 'provider', 'receiver'
        }:
            result.append((element.tag, tuple(sorted(element.attrib.items()))))
    return sorted(result)


assert surface(a) == surface(b), 'Permissions/component contract changed'
for name in ('settings.xml', 'network_security_config.xml'):
    relative = Path('res/xml') / name
    assert ET.canonicalize(from_file=reference / relative, strip_text=True) == ET.canonicalize(
        from_file=rebuilt / relative, strip_text=True), f'{name} changed'
main = (rebuilt / MAIN).read_text()
for hook in HOOKS:
    target = hook.split(', ', 1)[1]
    assert target not in main, f'Hook remains: {target}'
start = main.index('.method protected onCreate(Landroid/os/Bundle;)V')
end = main.index('.end method', start)
method = main[start:end]
assert method.count('->onCreate(Landroid/os/Bundle;)V') == 1, 'Superclass startup missing/duplicated'
assert '->setContentView(I)V' in method, 'Main UI initialization missing'
assert main.count('.method ') == (prepared / MAIN).read_text().count('.method '), 'Method count changed'
print('PASS: rebuilt APK retains package, permission and component contracts')
print('PASS: settings and network-security configuration unchanged')
print('PASS: both startup hooks absent, superclass and main UI initialization retained')
print('UNVERIFIED: device startup, storage operations, integrations, Android 7–15 runtime behavior')
