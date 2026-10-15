"""Static source/round-trip guards, not live TLS/WebView integration tests."""
import unittest
from zipfile import ZipFile
from nextcloud_tls_patch import ROOT, ENTRY, OLD, NEW, method, patch_bytes, verify_callback


class NextcloudTlsPatchTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as z:
            cls.original = z.read(ENTRY)
        cls.patched = patch_bytes(cls.original)

    def test_exact_one_call_delta_and_no_collateral_bytes(self):
        self.assertEqual(self.patched.replace(NEW, OLD, 1), self.original)
        self.assertEqual(self.original.count(OLD), 1)
        self.assertEqual(self.patched.count(OLD), 0)
        self.assertEqual(self.patched.count(NEW), 1)

    def test_other_methods_unchanged(self):
        self.assertEqual(self.original.replace(method(self.original).encode(), b''),
                         self.patched.replace(method(self.patched).encode(), b''))

    def test_unexpected_source_rejected(self):
        with self.assertRaises(ValueError):
            patch_bytes(self.original + b'\n')

    def test_reapplication_rejected(self):
        with self.assertRaises(ValueError):
            patch_bytes(self.patched)

    def test_expected_callback_accepted(self):
        verify_callback(self.patched, self.patched)
        self.assertIn('invoke-super {p0, p1, p2, p3}', method(self.patched))

    def test_reverted_callback_rejected(self):
        with self.assertRaises(ValueError):
            verify_callback(self.patched, self.original)

    def test_assembler_debug_line_and_label_changes_allowed(self):
        altered = self.patched.replace(b'.line 27', b'.line 270').replace(b':cond_0', b':cond_a')
        verify_callback(self.patched, altered)

    def test_changed_register_or_type_not_hidden_by_normalization(self):
        for altered in [self.patched.replace(b'{p2}, Landroid/webkit/SslErrorHandler;->cancel',
                                             b'{p1}, Landroid/webkit/SslErrorHandler;->cancel'),
                        self.patched.replace(b'a:Lax/S1/b;', b'a:Lbad/S1/b;')]:
            with self.assertRaises(ValueError):
                verify_callback(self.patched, altered)


if __name__ == '__main__':
    unittest.main()
