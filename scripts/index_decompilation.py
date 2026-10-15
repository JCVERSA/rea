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
# Root packages vary across APKs: explicitly include only this app namespace.
(root / '.gitignore').write_text(
    'apktool/smali*/\napktool/lib/\napktool/unknown/\napktool/original/\n'
    'jadx/sources/*\n!jadx/sources/' + app_prefix.split('/')[0] + '/\n'
)
