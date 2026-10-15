"""Static regression guards; real listener probes are separate Android tests."""
import unittest
from zipfile import ZipFile
from http_loopback_patch import ROOT, ENTRY, OLD, NEW, constructor, patch_bytes, verify_constructor


class HttpLoopbackPatchTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as z:
            cls.original = z.read(ENTRY)
        cls.patched = patch_bytes(cls.original)

    def test_exact_block_delta(self):
        self.assertEqual(self.patched.replace(NEW, OLD, 1), self.original)
        self.assertIn('const-string v0, "127.0.0.1"', constructor(self.patched))
        self.assertIn('invoke-direct {p0, v0, p2}', constructor(self.patched))

    def test_all_other_methods_unchanged(self):
        self.assertEqual(self.original.replace(constructor(self.original).encode(), b''),
                         self.patched.replace(constructor(self.patched).encode(), b''))

    def test_reapplication_rejected(self):
        with self.assertRaises(ValueError):
            patch_bytes(self.patched)

    def test_unexpected_source_rejected(self):
        with self.assertRaises(ValueError):
            patch_bytes(self.original + b'\n')

    def test_expected_constructor_accepted(self):
        verify_constructor(self.patched, self.patched)

    def test_debug_lines_ignored(self):
        verify_constructor(self.patched, self.patched.replace(b'.line 1\n', b'.line 100\n'))

    def test_wildcard_or_wrong_register_rejected(self):
        for altered in [self.original, self.patched.replace(b'127.0.0.1', b'0.0.0.0'),
                        self.patched.replace(b'{p0, v0, p2}', b'{p0, p1, p2}')]:
            with self.assertRaises(ValueError):
                verify_constructor(self.patched, altered)


if __name__ == '__main__':
    unittest.main()
