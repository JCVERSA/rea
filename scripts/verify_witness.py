"""Round-trip checks, preserving diagnostic evidence even on a failed invariant."""
from pathlib import Path
import difflib
import hashlib
import json
from zipfile import ZipFile
import xml.etree.ElementTree as ET
from provider_contract import inspect_provider

ROOT = Path(__file__).resolve().parent.parent
work = ROOT / '.work/witness'
source = work / 'tree'
roundtrip = work / 'roundtrip'
reports = work / 'reports'
reports.mkdir(parents=True, exist_ok=True)
report = {'functional_patches_applied': 0, 'passed': False,
          'limits': 'Assembly/layout/signature differ. Not a proof of full bytecode or resource equivalence; only listed invariants checked. Runtime smoke results are separate.'}
try:
    with (ROOT / 'base.apk').open('rb') as stream:
        report['input_sha256'] = hashlib.file_digest(stream, 'sha256').hexdigest()
    apk = ROOT / (work / 'diagnostic/path.txt').read_text().strip()
    with ZipFile(ROOT / 'base.apk') as original, ZipFile(apk) as assembled:
        report['binary_manifest_equal'] = original.read('AndroidManifest.xml') == assembled.read('AndroidManifest.xml')
        def resource_names(z):
            return {n for n in z.namelist() if n == 'resources.arsc' or n.startswith('res/')}
        original_names, assembled_names = resource_names(original), resource_names(assembled)
        report['compiled_resource_bytes_equal'] = (original_names == assembled_names and all(
            original.read(n) == assembled.read(n) for n in original_names))
    def public_resources(directory):
        return {(e.get('type'), e.get('name'), e.get('id'))
                for e in ET.parse(directory / 'res/values/public.xml').getroot()}
    old_ids, new_ids = public_resources(source), public_resources(roundtrip)
    report['public_resource_ids_equal'] = old_ids == new_ids
    report['reference_resource_id_count'] = len(old_ids)
    report['rebuilt_resource_id_count'] = len(new_ids)
    report['changed_resource_ids'] = {'missing': sorted(old_ids - new_ids), 'added': sorted(new_ids - old_ids)}
    reference = ET.canonicalize(from_file=source / 'AndroidManifest.xml', strip_text=True)
    rebuilt = ET.canonicalize(from_file=roundtrip / 'AndroidManifest.xml', strip_text=True)
    report['manifest_canonical_equal'] = reference == rebuilt
    if reference != rebuilt:
        # Split XML elements for a readable diff, not a different comparison rule.
        diff = difflib.unified_diff(reference.replace('><', '>\n<').splitlines(True),
                                    rebuilt.replace('><', '>\n<').splitlines(True),
                                    fromfile='reference-manifest', tofile='rebuilt-manifest')
        (reports / 'manifest.diff').write_text(''.join(diff))
    reference_files = list(source.rglob('*.smali'))
    rebuilt_files = list(roundtrip.rglob('*.smali'))
    report['smali_class_count'] = len(reference_files)
    report['rebuilt_smali_class_count'] = len(rebuilt_files)
    report['smali_class_count_equal'] = len(reference_files) == len(rebuilt_files)
    asset_changes = []
    for path in (source / 'assets').rglob('*'):
        if path.is_file():
            other = roundtrip / path.relative_to(source)
            if not other.is_file() or path.read_bytes() != other.read_bytes():
                asset_changes.append(str(path.relative_to(source)))
    report['changed_or_missing_assets'] = asset_changes
    report['asset_bytes_equal'] = not asset_changes
    contract = inspect_provider(source / 'AndroidManifest.xml')
    report['provider_contract'] = contract
    report['provider_contract_equal'] = contract == inspect_provider(roundtrip / 'AndroidManifest.xml')
    report['passed'] = all(report[key] for key in [
        'binary_manifest_equal', 'compiled_resource_bytes_equal', 'public_resource_ids_equal', 'manifest_canonical_equal', 'smali_class_count_equal', 'asset_bytes_equal', 'provider_contract_equal'
    ]) and contract['effective_grant_expected_android12'] is True
    assert report['passed'], 'Round-trip invariant failed; see roundtrip.json and manifest.diff'
except Exception as error:
    report['error'] = f'{type(error).__name__}: {error}'
    raise
finally:
    (reports / 'roundtrip.json').write_text(json.dumps(report, indent=2) + '\n')
    print(json.dumps(report, indent=2))
