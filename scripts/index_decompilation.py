"""Index decoded output and keep oversized/generated dependency trees out of Git."""
from pathlib import Path
import json
import xml.etree.ElementTree as ET

root = Path('decompiled')
manifest = ET.parse(root / 'apktool/AndroidManifest.xml').getroot()
package = manifest.attrib['package']
sources = root / 'jadx/sources'
all_java = list(sources.rglob('*.java'))
app_prefix = package.replace('.', '/')
app_java = [p for p in all_java if p.relative_to(sources).as_posix().startswith(app_prefix + '/')]
(root / 'index.json').write_text(json.dumps({
    'package': package,
    'java_files_total': len(all_java),
    'java_files_in_package': len(app_java),
    'jadx_exit_code': int((root / 'jadx-exit-code.txt').read_text()),
}, indent=2) + '\n')
# Keep the app top-level namespace expanded, including bundled com/ libraries.
(root / '.gitignore').write_text(
    'apktool/smali*/\napktool/lib/\napktool/unknown/\napktool/original/\n'
    'jadx/sources/*\n!jadx/sources/' + app_prefix.split('/')[0] + '/\n'
)
# All sources, including obfuscated ax/ and default-package helpers, are kept
# in a compact archive so the checkout stays below practical file-count limits.
from zipfile import ZipFile, ZIP_DEFLATED
with ZipFile(root / 'all-java-sources.zip', 'w', ZIP_DEFLATED) as archive:
    for path in sorted(all_java):
        archive.write(path, path.relative_to(root / 'jadx'))
with ZipFile(root / 'apktool-bytecode.zip', 'w', ZIP_DEFLATED) as archive:
    for path in sorted((root / 'apktool').rglob('*')):
        if path.is_file() and (path.relative_to(root / 'apktool').parts[0].startswith('smali')
                               or path.relative_to(root / 'apktool').parts[0] in {'lib', 'unknown', 'original'}):
            archive.write(path, path.relative_to(root / 'apktool'))
