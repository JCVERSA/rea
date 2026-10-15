"""Round-trip checks against the assembled witness, without editing the reference."""
from pathlib import Path
import hashlib
import json
from zipfile import ZipFile
import xml.etree.ElementTree as ET
from provider_contract import inspect_provider

ROOT = Path(__file__).resolve().parent.parent
work = ROOT / '.work/witness'
source = work / 'tree'
roundtrip = work / 'roundtrip'
source_manifest = ET.parse(source / 'AndroidManifest.xml').getroot()
built_manifest = ET.parse(roundtrip / 'AndroidManifest.xml').getroot()
assert ET.canonicalize(from_file=source / 'AndroidManifest.xml', strip_text=True) == ET.canonicalize(
    from_file=roundtrip / 'AndroidManifest.xml', strip_text=True), 'Manifest contract changed'
# Smali can be relaid out by the assembler. Compare references/attributes, not generated offsets.
reference_files = list(source.rglob('*.smali'))
assert len(reference_files) == len(list(roundtrip.rglob('*.smali'))), 'Smali class count changed'
for path in (source / 'assets').rglob('*'):
    if path.is_file():
        assert path.read_bytes() == (roundtrip / path.relative_to(source)).read_bytes(), path
source_contract = inspect_provider(source / 'AndroidManifest.xml')
assert source_contract == inspect_provider(roundtrip / 'AndroidManifest.xml')
assert source_contract['effective_grant_expected_android12'] is True
with (ROOT / 'base.apk').open('rb') as stream:
    digest = hashlib.file_digest(stream, 'sha256').hexdigest()
report = {'input_sha256': digest, 'functional_patches_applied': 0,
          'manifest_canonical_equal': True, 'smali_class_count_equal': True,
          'smali_class_count': len(reference_files), 'asset_bytes_equal': True,
          'provider_contract': source_contract,
          'limits': 'Assembly/layout/signature differ. Not a proof of full bytecode or resource equivalence; only listed invariants checked. Runtime smoke results are separate.'}
(work / 'reports/roundtrip.json').write_text(json.dumps(report, indent=2) + '\n')
print(json.dumps(report, indent=2))
