"""Regression tests for the narrow startup-hook patch (standard library only)."""
import unittest
from zipfile import ZipFile
from prepare_mod import ROOT, MAIN, HOOKS, patch_main


class PatchTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        with ZipFile(ROOT / 'decompiled/apktool-bytecode.zip') as archive:
            cls.original = archive.read(MAIN.as_posix()).decode()

    def test_only_expected_instructions_removed(self):
        patched = patch_main(self.original)
        expected = self.original
        for hook in HOOKS:
            expected = expected.replace('    ' + hook + '\n', '', 1)
        self.assertEqual(patched, expected)
        self.assertEqual(len(self.original.splitlines()) - len(patched.splitlines()), 2)

    def test_patch_is_not_silently_applied_twice(self):
        with self.assertRaises(ValueError):
            patch_main(patch_main(self.original))

    def test_missing_or_duplicate_hook_is_rejected(self):
        for hook in HOOKS:
            with self.assertRaises(ValueError):
                patch_main(self.original.replace(hook, 'nop'))
            with self.assertRaises(ValueError):
                patch_main(self.original + '\n    ' + hook + '\n')

    def test_no_additional_external_hook_callers(self):
        # Any additional invocation outside the injected helper layer needs review.
        callers = []
        with ZipFile(ROOT / 'decompiled/apktool-bytecode.zip') as archive:
            for name in archive.namelist():
                if not name.endswith('.smali'):
                    continue
                if name.startswith('smali_classes5/com/zx/') or name.startswith('smali_classes5/Xpk8a'):
                    continue
                for line in archive.read(name).decode().splitlines():
                    if 'invoke-' in line and ('LXpk8a;' in line or 'Lcom/zx/LB;' in line):
                        callers.append((name, line.strip()))
        self.assertCountEqual(callers, [(MAIN.as_posix(), hook) for hook in HOOKS])


if __name__ == '__main__':
    unittest.main()
