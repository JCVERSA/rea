"""Capture actual activity day/night and drawer on a guarded disposable emulator.
No personal files/accounts; no network enabled, no UI item/file actions clicked.
"""
from pathlib import Path
import json
import re
import subprocess
import time
import xml.etree.ElementTree as ET

ROOT=Path(__file__).resolve().parent.parent;work=ROOT/'.work/witness';reports=work/'reports'
package='com.alphainventor.filemanager';component=package+'/com.alphainventor.filemanager.activity.MainActivity'

def command(args,check=True):
    r=subprocess.run(args,capture_output=True,timeout=120)
    if check and r.returncode:raise RuntimeError('Probe command failed: '+str(args[:4])+': '+r.stderr.decode(errors='replace'))
    return r
lines=command(['adb','devices']).stdout.decode().splitlines()[1:]
devices=[l.split()[0] for l in lines if '\tdevice' in l]
assert len(devices)==1 and devices[0].startswith('emulator-'), 'Refusing real/ambiguous device'
serial=devices[0]
def adb(*args,check=True):return command(['adb','-s',serial,*args],check)
assert adb('shell','getprop','ro.kernel.qemu').stdout.strip()==b'1'
assert adb('shell','id','-u').stdout.strip()==b'0'
uid=re.search(r'\buserId=(\d+)',adb('shell','dumpsys','package',package).stdout.decode())
assert uid
# The preceding smoke deliberately blocked this installed app UID. Check both families.
for binary in ['iptables','ip6tables']:
    rules=adb('shell',binary,'-S','OUTPUT').stdout.decode()
    assert re.search(r'--uid-owner '+uid[1]+r' .*?-j REJECT',rules), 'App UID must remain network blocked'
remote='/data/local/tmp/rea-slate-ui';adb('shell','mkdir','-p',remote)
result={'passed':False,'captures':[],'scope':'Actual activity captures on isolated Android12 x86_64 emulator, 2GB. Not visual approval, Tecno performance, cloud/file operations or full Material3 migration.'}
try:
    # Synthetic emulator permission setup only. Never run these commands on a phone.
    adb('shell','appops','set',package,'MANAGE_EXTERNAL_STORAGE','allow')
    for permission in ['android.permission.READ_EXTERNAL_STORAGE','android.permission.WRITE_EXTERNAL_STORAGE']:
        adb('shell','pm','grant',package,permission,check=False)
    size=adb('shell','wm','size').stdout.decode();matches=re.findall(r'(\d+)x(\d+)',size)
    width,height=map(int,matches[-1])
    for mode in ['no','yes']:
        adb('shell','cmd','uimode','night',mode)
        adb('shell','am','force-stop',package);adb('logcat','-c')
        launch=adb('shell','am','start','-W','-n',component).stdout.decode()
        assert 'Status: ok' in launch
        time.sleep(3)
        for view in ['home','navigation']:
            if view=='navigation':
                adb('shell','input','swipe','1',str(height//2),str(width*3//4),str(height//2),'400');time.sleep(1)
            adb('shell','uiautomator','dump',remote+'/screen.xml')
            tree=adb('shell','cat',remote+'/screen.xml').stdout
            nodes=ET.fromstring(tree).findall('.//node')
            ids={n.get('resource-id','') for n in nodes}
            # Gate rendering of actual application views, not a system settings/splash screen.
            assert any(x.startswith(package+':id/') for x in ids), 'No application view rendered'
            label=('dark' if mode=='yes' else 'light')+'-'+view
            (reports/(label+'.xml')).write_bytes(tree)
            screenshot=adb('exec-out','screencap','-p').stdout
            assert screenshot.startswith(b'\x89PNG\r\n\x1a\n')
            (reports/(label+'.png')).write_bytes(screenshot)
            result['captures'].append({'label':label,'application_view_ids':sorted(x for x in ids if x.startswith(package+':id/'))})
        assert adb('shell','pidof',package,check=False).stdout.strip(), 'Activity process died'
        crash=adb('logcat','-b','crash','-d','--uid='+uid[1]).stdout.decode(errors='replace')
        assert 'FATAL EXCEPTION' not in crash, 'Activity crashed while rendering'
    result['passed']=True
except Exception as error:
    result['error']=f'{type(error).__name__}: {error}';raise
finally:
    adb('shell','am','force-stop',package,check=False)
    adb('shell','cmd','uimode','night','no',check=False)
    adb('shell','rm','-rf',remote,check=False)  # Only fixed emulator scratch, guarded above.
    (reports/'slate-ui-runtime.json').write_text(json.dumps(result,indent=2)+'\n');print(json.dumps(result,indent=2))
