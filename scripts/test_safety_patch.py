import unittest
from zipfile import ZipFile
import safety_patch as s
from media_grant_patch import normalized_methods

class SafetyPatchTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        with ZipFile(s.ROOT/'decompiled/complete-apktool.zip') as z:
            cls.original={n:z.read(n) for n in s.HASHES}
            cls.original[s.tls.ENTRY]=s.tls.patch_bytes(z.read(s.tls.ENTRY))
    def test_fail_closed_or_repeated(self):
        for n,b in self.original.items():
            with self.assertRaises(ValueError):s.patch_bytes(n,b+b'\n')
            with self.assertRaises(ValueError):s.patch_bytes(n,s.patch_bytes(n,b))
    def test_each_patch_changes_one_method_only(self):
        for n,b in self.original.items():
            before=normalized_methods(b);after=normalized_methods(s.patch_bytes(n,b))
            self.assertEqual(set(before),set(after))
            self.assertEqual(sum(before[k]!=after[k] for k in before),1,n)
    def test_sensitive_arguments_not_appended(self):
        ftp=s.patch_bytes(s.FTP,self.original[s.FTP])
        self.assertIn(b'move-object/from16 v17, v4',ftp)
        self.assertNotIn(b'invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append',ftp)
        cloud=s.patch_bytes(s.tls.ENTRY,self.original[s.tls.ENTRY])
        self.assertNotIn(b'invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append',cloud)
        self.assertIn(b'SslErrorHandler;->cancel()V',cloud)
        self.assertIn(b'invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith',cloud)
    def test_metadata_timeout_uses_existing_empty_cursor(self):
        p=s.patch_bytes(s.PROVIDER,self.original[s.PROVIDER])
        self.assertNotIn(b'CountDownLatch;->await()V',p)
        self.assertIn(b'move-result p3\n    if-eqz p3, :cond_9',p)
        self.assertIn(b':cond_9\n    invoke-direct {p0, p2}',p)
    def test_saved_theme_getter_remains_and_only_default_changes(self):
        b=self.original[s.THEME]; p=s.patch_bytes(s.THEME,b)
        self.assertEqual(p.replace(b'const/4 v0, -0x1',b'const/4 v0, 0x1',1),b)
        self.assertIn(b'SharedPreferences;->getString',p)

    def test_reader_only_ftp_control_constructor(self):
        p=s.patch_bytes(s.READER,self.original[s.READER])
        self.assertEqual(p.count(b'new-instance v3, Lrea/security/ControlReader;'),1)
        self.assertIn(b'Ljava/io/BufferedReader;->readLine()',p)
        self.assertIn(b'Ljava/io/BufferedReader;->close()',p)

if __name__=='__main__':unittest.main()
