"""Prevent the audit's parent-attribute-only false positive from recurring."""
from io import StringIO
from pathlib import Path
import unittest
from provider_contract import inspect_provider

ROOT = Path(__file__).resolve().parent.parent


def manifest(parent='false', child=''):
    return StringIO('<manifest xmlns:android="http://schemas.android.com/apk/res/android"><application>'
                    '<provider android:name="com.alphainventor.filemanager.provider.MyFileProvider" '
                    'android:exported="false" android:grantUriPermissions="' + parent + '">'
                    + child + '</provider></application></manifest>')


class ProviderContractTests(unittest.TestCase):
    def test_real_manifest_has_child_grant(self):
        result = inspect_provider(ROOT / 'decompiled/apktool/AndroidManifest.xml')
        self.assertFalse(result['declared_grant_uri_permissions'])
        self.assertEqual(result['grant_uri_permission_patterns'], [{'pathPrefix': '/'}])
        self.assertTrue(result['effective_grant_expected_android12'])
        self.assertEqual(result['exported'], 'false')

    def test_false_parent_without_child(self):
        self.assertFalse(inspect_provider(manifest())['effective_grant_expected_android12'])

    def test_child_overrides_false_parent(self):
        result = inspect_provider(manifest(child='<grant-uri-permission android:pathPrefix="/"/>'))
        self.assertTrue(result['effective_grant_expected_android12'])

    def test_parent_true(self):
        self.assertTrue(inspect_provider(manifest(parent='true'))['effective_grant_expected_android12'])

    def test_invalid_empty_child_not_enough(self):
        self.assertFalse(inspect_provider(manifest(child='<grant-uri-permission/>'))['effective_grant_expected_android12'])


if __name__ == '__main__':
    unittest.main()
