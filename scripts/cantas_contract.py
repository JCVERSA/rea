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
with ZipFile(apk) as z:
    assert not any(n.startswith('lib/') and not n.endswith('/') for n in z.namelist()), 'Unexpected native ABI dependency'
print('PASS Cantas independent ID, min24/target35, no INTERNET, no native ABI library')
