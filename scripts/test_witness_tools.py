"""Synthetic fixtures only: verifier gates and failure-path key cleanup, not Android tests."""
import json
import os
from pathlib import Path
import shutil
import subprocess
import sys
import tempfile
import unittest
from zipfile import ZipFile

ROOT = Path(__file__).resolve().parent.parent
MANIFEST = '''<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="example">
<application><provider android:name="com.alphainventor.filemanager.provider.MyFileProvider" android:grantUriPermissions="false" android:exported="false"><grant-uri-permission android:pathPrefix="/"/></provider></application></manifest>'''
PUBLIC = '<resources><public type="string" name="key" id="0x7f010001"/></resources>'


class WitnessToolsTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.root = Path(self.temp.name)
        scripts = self.root / 'scripts'
        scripts.mkdir()
        for name in ['verify_witness.py', 'provider_contract.py', 'sign_witness.sh']:
            shutil.copyfile(ROOT / 'scripts' / name, scripts / name)
        self.work = self.root / '.work/witness'
        for tree in ['tree', 'roundtrip']:
            folder = self.work / tree
            (folder / 'res/values').mkdir(parents=True)
            (folder / 'res/values/public.xml').write_text(PUBLIC)
            (folder / 'AndroidManifest.xml').write_text(MANIFEST)
            (folder / 'assets').mkdir()
            (folder / 'assets/example').write_bytes(b'unchanged')
        (self.work / 'diagnostic').mkdir()
        (self.work / 'diagnostic/path.txt').write_text('.work/witness/assembled.apk')
        for apk in [self.root / 'base.apk', self.work / 'assembled.apk']:
            with ZipFile(apk, 'w') as z:
                z.writestr('AndroidManifest.xml', MANIFEST)
                z.writestr('resources.arsc', b'fixture-table')

    def verify(self, expected):
        result = subprocess.run([sys.executable, 'scripts/verify_witness.py'], cwd=self.root,
                                text=True, capture_output=True)
        self.assertEqual(result.returncode == 0, expected, result.stdout + result.stderr)
        report = json.loads((self.work / 'reports/roundtrip.json').read_text())
        self.assertEqual(report['passed'], expected)
        return report

    def test_equal_fixture(self):
        self.verify(True)

    def test_manifest_change_preserves_diagnostic_and_fails(self):
        p = self.work / 'roundtrip/AndroidManifest.xml'
        p.write_text(MANIFEST.replace('package="example"', 'package="changed"'))
        self.assertFalse(self.verify(False)['manifest_canonical_equal'])
        self.assertIn('package="changed"', (self.work / 'reports/manifest.diff').read_text())

    def test_binary_manifest_gate_not_just_decoded_xml(self):
        with ZipFile(self.work / 'assembled.apk', 'w') as z:
            z.writestr('AndroidManifest.xml', b'different bytes')
        self.assertFalse(self.verify(False)['binary_manifest_equal'])

    def test_compiled_resource_change_fails(self):
        with ZipFile(self.work / 'assembled.apk', 'w') as z:
            z.writestr('AndroidManifest.xml', MANIFEST)
            z.writestr('resources.arsc', b'changed-table')
        self.assertFalse(self.verify(False)['compiled_resource_bytes_equal'])

    def test_resource_id_remap_fails(self):
        (self.work / 'roundtrip/res/values/public.xml').write_text(PUBLIC.replace('0x7f010001', '0x7f010002'))
        self.assertFalse(self.verify(False)['public_resource_ids_equal'])

    def test_asset_change_fails(self):
        (self.work / 'roundtrip/assets/example').write_bytes(b'changed')
        self.assertFalse(self.verify(False)['asset_bytes_equal'])

    def test_bad_private_key_encoding_leaves_no_key_file(self):
        tools = self.root / 'sdk/build-tools/35.0.0'
        tools.mkdir(parents=True)
        fake = tools / 'zipalign'
        fake.write_text('#!/bin/bash\ncp "${@: -2:1}" "${@: -1}"\n')
        fake.chmod(0o700)
        (self.work / 'unsigned.apk').write_bytes(b'fixture')
        runner = self.root / 'runner'
        runner.mkdir()
        env = dict(os.environ, ANDROID_HOME=str(self.root / 'sdk'), RUNNER_TEMP=str(runner),
                   MOD_SIGNING_KEYSTORE_B64='invalid!base64', MOD_SIGNING_PASSWORD='fixture-only')
        result = subprocess.run(['bash', 'scripts/sign_witness.sh'], cwd=self.root,
                                env=env, text=True, capture_output=True)
        self.assertNotEqual(result.returncode, 0)
        self.assertEqual(list(runner.iterdir()), [])
        self.assertNotIn('fixture-only', result.stdout + result.stderr)


if __name__ == '__main__':
    unittest.main()
