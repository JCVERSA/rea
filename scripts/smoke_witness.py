"""Destructive emulator-only install/start probes; never use on a real device."""
from pathlib import Path
import json
import re
import subprocess
import time

ROOT = Path(__file__).resolve().parent.parent
work = ROOT / '.work/witness'
package = 'com.alphainventor.filemanager'
component = package + '/com.alphainventor.filemanager.activity.MainActivity'


def adb(*args, check=True):
    r = subprocess.run(['adb', *args], text=True, capture_output=True, timeout=120)
    if check and r.returncode:
        raise RuntimeError(f'adb {args[0]} failed: {r.stdout} {r.stderr}')
    return r


# Restrict all mutations to one disposable Android Emulator, not a USB/real phone.
devices = [line.split()[0] for line in adb('devices').stdout.splitlines()[1:] if '\tdevice' in line]
assert len(devices) == 1 and devices[0].startswith('emulator-'), 'Refusing non-emulator/ambiguous target'
assert adb('shell', 'getprop', 'ro.kernel.qemu').stdout.strip() == '1'
adb('root')
adb('wait-for-device')
assert adb('shell', 'id', '-u').stdout.strip() == '0', 'Root needed for network isolation; refusing to launch'
context = {key: adb('shell', 'getprop', key).stdout.strip() for key in
           ['ro.build.version.sdk', 'ro.product.cpu.abilist', 'ro.kernel.qemu']}
results = []
try:
    for name, apk in [('original', ROOT / 'base.apk'),
                      ('rebuilt', ROOT / (work / 'diagnostic/path.txt').read_text().strip())]:
        adb('uninstall', package, check=False)
        install = adb('install', '--no-streaming', str(apk), check=False)
        result = {'target': name, 'install_exit': install.returncode,
                  'install_output': install.stdout + install.stderr, 'started': False}
        results.append(result)
        if install.returncode or 'Success' not in install.stdout:
            continue
        details = adb('shell', 'dumpsys', 'package', package).stdout
        uid = re.search(r'\buserId=(\d+)', details)
        assert uid, 'Cannot determine package UID for isolation'
        # Block IPv4 and IPv6 network access for this app UID BEFORE executing it.
        for binary in ['iptables', 'ip6tables']:
            adb('shell', binary, '-I', 'OUTPUT', '1', '-m', 'owner', '--uid-owner', uid[1], '-j', 'REJECT')
        result['app_uid_outbound_blocked_ipv4_ipv6'] = True
        adb('logcat', '-c')
        launch = adb('shell', 'am', 'start', '-W', '-n', component, check=False)
        result['launch_output'] = launch.stdout + launch.stderr
        time.sleep(6)  # Allow asynchronous startup crashes to surface; not a benchmark.
        result['process_alive_after_6_seconds'] = bool(adb('shell', 'pidof', package, check=False).stdout.strip())
        crash = adb('logcat', '-b', 'crash', '-d', '--uid=' + uid[1], check=False)
        result['crash_log'] = crash.stdout + crash.stderr
        result['started'] = (launch.returncode == 0 and 'Status: ok' in launch.stdout
                             and result['process_alive_after_6_seconds']
                             and 'FATAL EXCEPTION' not in crash.stdout)
        adb('shell', 'am', 'force-stop', package)
finally:
    report = {'environment': context, 'results': results,
              'scope': 'Isolated emulator install/start smoke only. App UID network rejected. Not Tecno/ARM32 validation, no file/cloud/performance tests.'}
    (work / 'reports/emulator.json').write_text(json.dumps(report, indent=2) + '\n')
    print(json.dumps(report, indent=2))
assert len(results) == 2 and all(r['started'] for r in results), 'Original or rebuilt install/start smoke failed'
