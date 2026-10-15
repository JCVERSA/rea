"""Source patch guards only; JVM/Android policy suites run separately in CI."""
from zipfile import ZipFile
import unittest
from media_grant_patch import ROOT, ENTRY, patch_bytes, normalized_methods


class MediaGrantPatchTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as z:
            cls.original = z.read(ENTRY)
        cls.patched = patch_bytes(cls.original)

    def test_only_expected_methods_changed(self):
        before, after = normalized_methods(self.original), normalized_methods(self.patched)
        changed = {name for name in before if before[name] != after[name]}
        self.assertEqual(changed, {'.method public a(Ljava/lang/String;)Z',
            '.method public b(Ljava/util/List;)V',
            '.method public c(Lcom/alphainventor/filemanager/file/l;)Z',
            '.method public j(Ljava/lang/String;)Z'})
        self.assertEqual(set(before), set(after))

    def test_typed_full_file_registrations(self):
        self.assertEqual(self.patched.count(b'->media(Ljava/lang/String;)Ljava/lang/String;'), 2)
        self.assertEqual(self.patched.count(b'->directory(Ljava/lang/String;)Ljava/lang/String;'), 1)
        self.assertNotIn(b'Lax/J1/V;->p(Ljava/lang/String;)', self.patched)
        self.assertIn(b'Lax/J1/V;->t(Ljava/lang/String;)', self.patched)
        self.assertIn(b'->allows(Ljava/lang/String;Ljava/lang/String;)Z', self.patched)

    def test_bad_or_repeated_source_rejected(self):
        for data in [self.original + b'\n', self.patched]:
            with self.assertRaises(ValueError):
                patch_bytes(data)

    def test_normalizer_keeps_changed_opcodes(self):
        self.assertNotEqual(normalized_methods(self.patched),
                            normalized_methods(self.patched.replace(b'->allows(', b'->other(')))

    def test_normalizer_keeps_class_contract(self):
        self.assertNotEqual(normalized_methods(self.patched),
                            normalized_methods(self.patched.replace(b'.super Ljava/lang/Object;', b'.super Lother;')))


if __name__ == '__main__':
    unittest.main()
