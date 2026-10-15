import unittest
from slate_contract import normalized_class as n

BASE=b'.class public Ltest;\n.super Ljava/lang/Object;\n.field private static a:Z = false\n.field static b:Ljava/lang/String; = null\n'
class SlateContractTests(unittest.TestCase):
    def test_marker_contract_included(self):
        self.assertEqual(n(BASE),n(BASE.replace(b' = false',b'').replace(b' = null',b'')))
        self.assertNotEqual(n(BASE),n(BASE.replace(b'.class public',b'.class private')))
    def test_nondefault_not_ignored(self):
        for before,after in [(b'false',b'true'),(b'null',b'"secret"'),(b'a:Z',b'a:I'),(b'private static',b'public static'),(b' = false',b' = 0x1')]:
            self.assertNotEqual(n(BASE),n(BASE.replace(before,after)))
    def test_nonstatic_false_not_ignored(self):
        text=BASE.replace(b'private static a',b'private a')
        self.assertNotEqual(n(text),n(text.replace(b' = false',b'')))
    def test_code_change_not_ignored(self):
        text=BASE+b'.method public a()Z\n.locals 1\nconst/4 v0, 0x0\nreturn v0\n.end method\n'
        self.assertNotEqual(n(text),n(text.replace(b'const/4 v0, 0x0',b'const/4 v0, 0x1')))
    def test_malformed_methodless_rejected(self):
        with self.assertRaises(ValueError):n(b'')

if __name__=='__main__':unittest.main()
