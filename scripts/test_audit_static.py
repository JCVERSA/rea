"""Audit evidence regression tests — not Android runtime or exploit tests."""
import hashlib
import json
import unittest
from zipfile import ZipFile
from audit_static import ROOT, jar_sections, raw_manifest_elements


class AuditEvidenceTests(unittest.TestCase):
    def test_input_fingerprint_unchanged(self):
        with (ROOT / 'base.apk').open('rb') as stream:
            self.assertEqual(hashlib.file_digest(stream, 'sha256').hexdigest(),
                             '0f7dbe666b6be1c1f99f1b324c65899ebbca6cc56fe55d3809bc7684242b762b')

    def test_raw_manifest_provider_boolean_is_zero(self):
        with ZipFile(ROOT / 'base.apk') as z:
            result = raw_manifest_elements(z.read('AndroidManifest.xml'))
        self.assertEqual(len(result), 1)
        flag = result[0]['attributes']['grantUriPermissions']
        self.assertEqual((flag['type'], flag['data']), (0x12, 0))

    def test_inventory_retains_child_uri_grants(self):
        data = json.loads((ROOT / "analysis/audit/manifest.json").read_text())
        provider = next(c for c in data["components"] if c["attributes"].get("name") == "com.alphainventor.filemanager.provider.MyFileProvider")
        self.assertEqual(provider["attributes"]["grantUriPermissions"], "false")
        self.assertIn({"pathPrefix": "/"}, provider["uri_grants"])

    def test_invalid_manifest_header_rejected(self):
        with self.assertRaises(ValueError):
            raw_manifest_elements(b'not axml')

    def test_jar_continuation_lines(self):
        self.assertEqual(jar_sections(b'Name: abc\r\n def\r\nSHA-256-Digest: x\r\n\r\n'),
                         [{'Name': 'abcdef', 'SHA-256-Digest': 'x'}])

    def test_recorded_signing_checks(self):
        result = json.loads((ROOT / 'analysis/audit/signing.json').read_text())
        self.assertTrue(result['v1_cms_signature_verified'])
        self.assertTrue(result['sf_whole_manifest_digest_matches'])
        self.assertTrue(result['matches_aosp_public_test_certificate'])
        self.assertEqual(result['listed_entry_digests_checked'], 1647)
        self.assertEqual(result['listed_entry_digest_mismatches'], [])

    def test_evidence_anchors_match_source_archive(self):
        evidence = json.loads((ROOT / 'analysis/audit/source-evidence.json').read_text())
        with ZipFile(ROOT / 'decompiled/all-java-sources.zip') as z:
            for item in evidence.values():
                data = z.read(item['entry'])
                self.assertEqual(hashlib.sha256(data).hexdigest(), item['source_sha256'])
                lines = data.decode().splitlines()
                for hit in item['matches']:
                    self.assertEqual(lines[hit['line'] - 1].strip(), hit['text'])

    def test_prefix_predicate_model_not_file_boundary(self):
        # Models the observed startsWith predicate only, no HTTP/device exploit.
        allowed_prefix = 'local://0/storage/movies/clip'
        self.assertTrue((allowed_prefix + '-private.mp4').startswith(allowed_prefix))
        self.assertNotEqual(allowed_prefix + '-private.mp4', allowed_prefix)

    def test_packaged_asset_guard_model_lacks_separator(self):
        # Trusted packaged-asset extractor, not proof of external ZIP reachability.
        root = '/data/user/0/example/files/media'
        sibling = '/data/user/0/example/files/media-other/test.so'
        self.assertTrue(sibling.startswith(root))
        self.assertFalse(sibling.startswith(root + '/'))


if __name__ == '__main__':
    unittest.main()
