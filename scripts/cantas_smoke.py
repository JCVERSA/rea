"""Disposable-emulator-only Cantas instrumentation and synthetic folder browsing."""
from pathlib import Path
import json
import re
import subprocess
import time
import xml.etree.ElementTree as ET

ROOT=Path(__file__).resolve().parent.parent
reports=ROOT/'.work/cantas-reports';reports.mkdir(parents=True,exist_ok=True)
def command(args,check=True):
    r=subprocess.run(args,capture_output=True,timeout=180)
    if check and r.returncode:raise RuntimeError(str(args[:4])+': '+r.stdout.decode(errors='replace')+r.stderr.decode(errors='replace'))
    return r
lines=command(['adb','devices']).stdout.decode().splitlines()[1:]
serials=[l.split()[0] for l in lines if '\tdevice' in l]
assert len(serials)==1 and serials[0].startswith('emulator-'), 'Refusing real/ambiguous device'
serial=serials[0]
def adb(*args,check=True):return command(['adb','-s',serial,*args],check)
assert adb('shell','getprop','ro.kernel.qemu').stdout.strip()==b'1'
package='app.cantas.jcversa';component=package+'/.MainActivity'
files=list((ROOT/'.work/cantas-apks').rglob('*.apk'))
main=next(p for p in files if p.name=='app-debug.apk');test=next(p for p in files if p.name=='app-debug-androidTest.apk')
report={'passed':False,'api':adb('shell','getprop','ro.build.version.sdk').stdout.decode().strip(),
        'scope':'Fresh disposable emulator, synthetic fixture only. SAF uses a test-only provider; not physical SD/USB, Tecno/cloud/full feature validation.'}
fixture='/sdcard/Download/Cantas-CI-fixture'
owned_fixture=False
try:
    adb('install','--no-streaming',str(main));adb('install','--no-streaming',str(test))
    try:
        run=adb('shell','am','instrument','-w',package+'.test/androidx.test.runner.AndroidJUnitRunner',check=False)
    except subprocess.TimeoutExpired as error:
        (reports/'instrumentation.txt').write_bytes((error.stdout or b'') + (error.stderr or b''))
        (reports/'instrumentation-logcat.txt').write_bytes(adb('logcat','-d','-t','1200',check=False).stdout)
        raise
    result=run.stdout.decode(errors='replace')+run.stderr.decode(errors='replace')
    (reports/'instrumentation.txt').write_text(result)
    if run.returncode!=0 or 'OK (15 tests)' not in result or 'FAILURES' in result:
        for diagnostic in ('theme-failure-window.txt','theme-failure.png','copy-failure-window.txt','copy-failure.png'):
            data=adb('exec-out','run-as',package,'cat','cache/'+diagnostic,check=False)
            if data.returncode==0:(reports/diagnostic).write_bytes(data.stdout)
        (reports/'instrumentation-logcat.txt').write_bytes(adb('logcat','-d','-t','1200',check=False).stdout)
        raise AssertionError(result)
    for capture in ('alpha04-selection.png','alpha04-create.png','alpha05-copy.png','alpha05-collision.png'):
        (reports/capture).write_bytes(adb('exec-out','run-as',package,'cat','cache/'+capture).stdout)
    # Only synthetic emulator fixtures are touched. Runtime browsing smoke below does not invoke copy.
    assert adb('shell','test','-e',fixture,check=False).returncode!=0, 'Fresh scratch required'
    adb('shell','mkdir','-p',fixture+'/Nested')
    owned_fixture=True
    adb('shell','sh','-c',"'printf synthetic > /sdcard/Download/Cantas-CI-fixture/sample.txt'")
    if int(report['api'])>=30:adb('shell','appops','set',package,'MANAGE_EXTERNAL_STORAGE','allow')
    else:adb('shell','pm','grant',package,'android.permission.READ_EXTERNAL_STORAGE')
    adb('shell','am','force-stop',package);adb('logcat','-c')
    adb('shell','am','start','-W','-n',component);time.sleep(2)
    remote='/data/local/tmp/cantas-ui.xml'
    def nodes():
        adb('shell','uiautomator','dump',remote)
        return ET.fromstring(adb('shell','cat',remote).stdout).findall('.//node')
    def tap_text(text):
        node=None
        for attempt in range(5):
            node=next((n for n in nodes() if n.get('text')==text),None)
            if node is not None:break
            adb('shell','input','swipe','180','510','180','220','300');time.sleep(.4)
        assert node is not None,'Missing UI text: '+text
        x1,y1,x2,y2=map(int,re.findall(r'\d+',node.get('bounds')))
        adb('shell','input','tap',str((x1+x2)//2),str((y1+y2)//2));time.sleep(1)
    (reports/'home-light.png').write_bytes(adb('exec-out','screencap','-p').stdout)
    tap_text('Downloads');tap_text('Cantas-CI-fixture')
    assert any(n.get('text')=='sample.txt' for n in nodes()),'Synthetic file not displayed'
    (reports/'files.png').write_bytes(adb('exec-out','screencap','-p').stdout)
    tap_text('Nested')
    assert any('No visible files' in n.get('text','') for n in nodes()),'Empty folder not rendered'
    adb('shell','input','keyevent','KEYCODE_BACK');time.sleep(1)
    assert any(n.get('text')=='sample.txt' for n in nodes()),'Parent navigation failed'
    tap_text('Settings');tap_text('AMOLED');tap_text('Home')
    (reports/'home-dark.png').write_bytes(adb('exec-out','screencap','-p').stdout)
    assert adb('shell','pidof',package,check=False).stdout.strip(),'Process died'
    crash=adb('logcat','-b','crash','-d').stdout.decode(errors='replace')
    (reports/'crash.txt').write_text(crash)
    assert 'FATAL EXCEPTION' not in crash,crash
    report.update(passed=True,instrumentation=True,synthetic_browsing=True,back_navigation=True)
except Exception as error:
    report['error']=str(error);raise
finally:
    adb('shell','am','force-stop',package,check=False)
    if owned_fixture:adb('shell','rm','-rf',fixture,check=False) # Emulator-only synthetic directory, guarded above.
    (reports/'runtime.json').write_text(json.dumps(report,indent=2)+'\n')
