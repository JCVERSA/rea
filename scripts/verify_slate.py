"""Independent cumulative verifier. Old code-only recipes remain strict/unchanged."""
from pathlib import Path
from zipfile import ZipFile
import json
import xml.etree.ElementTree as ET
from provider_contract import inspect_provider
import safety_patch as safety
import slate_resources as slate
from media_grant_patch import HELPER, GENERATED
from slate_contract import normalized_class

ROOT=Path(__file__).resolve().parent.parent
work=ROOT/'.work/witness'; source=work/'tree'; rebuilt=work/'roundtrip'; reports=work/'reports'
report={'passed':False,'variant':'slate-safety-v1','limits':'No full feature, real account, Tecno ARM32 or Android7–15 compatibility claim. Existing Views; Material3-inspired tokens, not a full component migration.'}
try:
    replacements,plan=slate.expected_resources()
    apk=ROOT/(work/'diagnostic/path.txt').read_text().strip()
    with ZipFile(ROOT/'base.apk') as original, ZipFile(apk) as actual:
        assert len(actual.namelist())==len(set(actual.namelist())), 'Duplicate assembled entries'
        resource_names=lambda z:{n for n in z.namelist() if n=='resources.arsc' or n.startswith('res/')}
        assert resource_names(original)==resource_names(actual), 'Resource names changed'
        assert all(actual.read(n)==replacements.get(n,original.read(n)) for n in resource_names(original)), 'Unexpected compiled resource delta'
        report['exact_resource_overlay_equal']=True
        assert original.read('AndroidManifest.xml')==actual.read('AndroidManifest.xml'), 'Binary manifest changed'
        report['binary_manifest_equal']=True
        for n in original.namelist():
            if n.startswith(('assets/','lib/')): assert original.read(n)==actual.read(n), 'Asset/native delta: '+n
        report['assets_and_native_libraries_equal']=True
    def public(tree): return {(e.get('type'),e.get('name'),e.get('id')) for e in ET.parse(tree/'res/values/public.xml').getroot()}
    assert public(source)==public(rebuilt), 'Public IDs remapped'
    report['public_resource_ids_equal']=True; report['public_resource_id_count']=len(public(source))
    assert ET.canonicalize(from_file=source/'AndroidManifest.xml',strip_text=True)==ET.canonicalize(from_file=rebuilt/'AndroidManifest.xml',strip_text=True), 'Decoded manifest changed'
    contract=inspect_provider(source/'AndroidManifest.xml')
    assert contract==inspect_provider(rebuilt/'AndroidManifest.xml') and contract['effective_grant_expected_android12'] is True
    report['provider_contract_equal']=True
    helpers={HELPER:GENERATED, **safety.HELPERS}
    with ZipFile(ROOT/'decompiled/complete-apktool.zip') as z:
        names={n for n in z.namelist() if n.endswith('.smali')}
        expected={n:safety.expected_original(n,z.read(n)) for n in names}
    expected.update({n:(ROOT/p).read_bytes() for n,p in helpers.items()})
    for tree in [source,rebuilt]:
        assert {p.relative_to(tree).as_posix() for p in tree.rglob('*.smali')}==set(expected), 'Unexpected class set'
    differences=[]
    for n,data in sorted(expected.items()):
        assert (source/n).read_bytes()==data, 'Unexpected prepared source: '+n
        wanted=normalized_class(data); got=normalized_class((rebuilt/n).read_bytes())
        if wanted!=got:
            differences.append({'entry':n,'methods':[k for k in wanted.keys()|got.keys() if wanted.get(k)!=got.get(k)]})
            if len(differences)==1:
                (reports/'first-method-diff.json').write_text(json.dumps({'entry':n,'changes':{k:{'expected':wanted.get(k),'actual':got.get(k)} for k in wanted.keys()|got.keys() if wanted.get(k)!=got.get(k)}},indent=2)+'\n')
    report['method_differences']=differences
    assert not differences, 'Unexpected assembled method/class contract differences'
    report['all_normalized_class_methods_equal']=True
    report['class_count']=len(expected)
    report['palette_entries']=len(plan['colors']); report['layout_dimension_values']=sum(len(plan[k]) for k in slate.RULES)
    report['new_install_theme_default_system']=True
    report['passed']=True
except Exception as error:
    report['error']=f'{type(error).__name__}: {error}'; raise
finally:
    (reports/'slate-roundtrip.json').write_text(json.dumps(report,indent=2)+'\n');print(json.dumps(report,indent=2))
