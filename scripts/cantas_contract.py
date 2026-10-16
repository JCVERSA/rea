"""APK-level identity/permission checks; requires Android SDK aapt, no execution."""
import os
from pathlib import Path
import subprocess
import sys
from zipfile import ZipFile
apk=Path(sys.argv[1])
aapt=Path(os.environ['ANDROID_HOME'])/'build-tools/35.0.0/aapt'
badging=subprocess.check_output([str(aapt),'dump','badging',str(apk)],text=True)
permissions=subprocess.check_output([str(aapt),'dump','permissions',str(apk)],text=True)
assert "name='app.cantas.jcversa'" in badging
assert "sdkVersion:'24'" in badging
assert "targetSdkVersion:'35'" in badging
assert 'android.permission.INTERNET' not in permissions
assert 'com.alphainventor' not in permissions
manifest=subprocess.check_output([str(aapt),'dump','xmltree',str(apk),'AndroidManifest.xml'],text=True)
assert 'TreeFixtureProvider' not in manifest and 'test.treefixture' not in manifest, 'Test provider leaked into delivered APK'
with ZipFile(apk) as z:
    native = [n for n in z.namelist() if n.startswith('lib/') and not n.endswith('/')]
    libraries = {n.rsplit('/',1)[1] for n in native}
    for name in libraries:
        assert 'lib/armeabi-v7a/' + name in native, 'Missing ARM32 variant: ' + name
    print('Native entries:',native)
print('PASS Cantas independent ID, min24/target35, no INTERNET; ARM32 variants checked for packaged native libraries')
