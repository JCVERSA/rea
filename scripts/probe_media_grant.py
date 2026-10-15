"""Exercise actual grant manager and policy classes on a disposable emulator only.
Synthetic URI strings only; no user files or network endpoints.
"""
from pathlib import Path
import json
import subprocess

ROOT = Path(__file__).resolve().parent.parent
work = ROOT / '.work/witness'


def command(args, check=True, timeout=120):
    r = subprocess.run(args, capture_output=True, text=True, timeout=timeout)
    if check and r.returncode:
        raise RuntimeError(f'Command failed: {args[:3]}: {r.stdout} {r.stderr}')
    return r


lines = command(['adb', 'devices']).stdout.splitlines()[1:]
devices = [line.split()[0] for line in lines if '\tdevice' in line]
assert len(devices) == 1 and devices[0].startswith('emulator-'), 'Refusing real/ambiguous device'
serial = devices[0]


def adb(*args, check=True):
    return command(['adb', '-s', serial, *args], check)


assert adb('shell', 'getprop', 'ro.kernel.qemu').stdout.strip() == '1'
assert adb('shell', 'id', '-u').stdout.strip() == '0', 'Root required on disposable emulator'
remote = '/data/local/tmp/rea-grant-probe'
report = {'passed': False, 'emulator_serial': serial, 'results': [],
          'scope': 'Actual APK manager a/j and compiled policy in root app_process with a minimal ContextWrapper. Synthetic URI strings, no file content/backend operations; b/c registration methods checked in assembled code, not exercised with real file objects.'}
rules = []
try:
    # Preserve existing ADB transport, reject new non-loopback egress while loading APK classes.
    for binary in ['iptables', 'ip6tables']:
        rule = ['!', '-o', 'lo', '-m', 'conntrack', '--ctstate', 'NEW', '-j', 'REJECT']
        adb('shell', binary, '-I', 'OUTPUT', '1', *rule)
        rules.append((binary, rule))
    report['new_non_loopback_egress_rejected_ipv4_ipv6'] = True
    adb('shell', 'mkdir', '-p', remote)
    adb('push', str(ROOT / '.work/grant-probe/probe.jar'), remote + '/probe.jar')
    patched = ROOT / (work / 'diagnostic/path.txt').read_text().strip()
    for label, apk in [('original', ROOT / 'base.apk'), ('patched', patched)]:
        adb('push', str(apk), remote + '/target.apk')
        execution = adb('shell', 'env', 'CLASSPATH=' + remote + '/probe.jar:' + remote + '/target.apk',
                        'app_process', '/system/bin', 'rea.test.MediaGrantProbe', label, check=False)
        result = {'target': label, 'exit_code': execution.returncode,
                  'stdout': execution.stdout, 'stderr': execution.stderr}
        report['results'].append(result)
        payloads = [line.split('REA_GRANT_RESULT=', 1)[1] for line in execution.stdout.splitlines()
                    if line.startswith('REA_GRANT_RESULT=')]
        if payloads:
            result['probe'] = json.loads(payloads[-1])
        assert execution.returncode == 0 and result.get('probe', {}).get('passed'), 'Actual grant manager probe failed'
    report['passed'] = True
except Exception as error:
    report['error'] = f'{type(error).__name__}: {error}'
    raise
finally:
    for binary, rule in reversed(rules):
        adb('shell', binary, '-D', 'OUTPUT', *rule, check=False)
    adb('shell', 'rm', '-rf', remote, check=False)  # Guarded emulator-only scratch directory.
    (work / 'reports/grant-runtime.json').write_text(json.dumps(report, indent=2) + '\n')
    print(json.dumps(report, indent=2))
