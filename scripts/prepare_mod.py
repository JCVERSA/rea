"""Create a disposable patched Apktool tree; never alter the reference export."""
import argparse
import hashlib
import json
from pathlib import Path
import shutil
from zipfile import ZipFile

ROOT = Path(__file__).resolve().parent.parent
APK = ROOT / 'File Manager v3.8.3 (Premium).apk'
MAIN = Path('smali/com/alphainventor/filemanager/activity/MainActivity.smali')
HOOKS = (
    'invoke-static/range {p0 .. p0}, LXpk8a;->StartGame(Landroid/content/Context;)V',
    'invoke-static/range {p0 .. p0}, Lcom/zx/LB;->ll(Landroid/app/Activity;)V',
)


def patch_main(text):
    """Fail closed if the known input changes; touch only the two startup calls."""
    start = text.index('.method protected onCreate(Landroid/os/Bundle;)V')
    end = text.index('.end method', start)
    method = text[start:end]
    for hook in HOOKS:
        if text.count(hook) != 1 or method.count(hook) != 1:
            raise ValueError(f'Expected exactly one onCreate hook: {hook}')
    result = text
    for hook in HOOKS:
        result = result.replace('    ' + hook + '\n', '', 1)
    if any(hook in result for hook in HOOKS):
        raise ValueError('Hook removal failed')
    return result


def prepare(destination):
    destination = destination.resolve()
    work = (ROOT / '.work').resolve()
    if work not in destination.parents or destination.exists():
        raise ValueError('Destination must be a new directory under .work/')
    expected = (ROOT / 'decompiled/APK.sha256').read_text().split()[0]
    with APK.open('rb') as stream:
        actual = hashlib.file_digest(stream, 'sha256').hexdigest()
    if actual != expected:
        raise ValueError('Input APK fingerprint mismatch')
    with ZipFile(ROOT / 'decompiled/apktool-bytecode.zip') as archive:
        if any(Path(n).is_absolute() or '..' in Path(n).parts for n in archive.namelist()):
            raise ValueError('Unsafe archive path')
        shutil.copytree(ROOT / 'decompiled/apktool', destination)
        archive.extractall(destination)
    path = destination / MAIN
    original = path.read_text()
    patched = patch_main(original)
    path.write_text(patched)
    report = {
        'input_sha256': actual,
        'modified_file': MAIN.as_posix(),
        'removed_instructions': list(HOOKS),
        'before_sha256': hashlib.sha256(original.encode()).hexdigest(),
        'after_sha256': hashlib.sha256(patched.encode()).hexdigest(),
        'scope': 'Only two startup invocations removed. Dormant helper classes retained; no UI, billing, permission or file-operation changes.',
    }
    (destination.parent / 'patch-report.json').write_text(json.dumps(report, indent=2) + '\n')
    print(json.dumps(report, indent=2))


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('destination', type=Path)
    prepare(parser.parse_args().destination)
