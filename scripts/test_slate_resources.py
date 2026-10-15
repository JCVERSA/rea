import unittest
from zipfile import ZipFile
import slate_resources as s

class SlateResourceTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):cls.replacements,cls.plan=s.expected_resources()
    def test_only_fixed_value_bytes_change(self):
        with ZipFile(s.ROOT/'base.apk') as z:
            for name,new in self.replacements.items():
                old=z.read(name);allowed=set()
                if name=='resources.arsc':
                    for c in self.plan['colors']:allowed.update(range(c['offset']+3,c['offset']+8))
                else:
                    for c in self.plan[name]:allowed.update(range(c['offset'],c['offset']+4))
                self.assertEqual(len(old),len(new))
                self.assertTrue({i for i,(a,b) in enumerate(zip(old,new)) if a!=b}<=allowed,name)
                self.assertNotEqual(old,new,name)
    def test_expected_plan_counts_and_night_background(self):
        self.assertEqual(len(self.plan['colors']),40)
        self.assertEqual(sum(len(v) for k,v in self.plan.items() if k!='colors'),25)
        c=[x for x in self.plan['colors'] if x['name']=='main_background_dark']
        self.assertEqual(len(c),1)
        self.assertEqual(c[0]['after'][-8:],'000000ff')
    def test_missing_rules_rejected(self):
        with ZipFile(s.ROOT/'base.apk') as z:
            with self.assertRaises(ValueError):s.xml_overlay(z.read(next(iter(s.RULES))),{('unknown',2):3})
    def test_bad_xml_or_table_rejected(self):
        for bad in [b'', b'bad', b'\x00'*100]:
            with self.assertRaises((ValueError,IndexError,s.struct.error)):s.table_overlay(bad)
            with self.assertRaises((ValueError,IndexError,s.struct.error)):s.xml_overlay(bad,{})
    def test_dimension_round_trip(self):
        for n in [2,3,6,8,10,12,18,60]:self.assertEqual(s.dimension((n<<8)|1),n)

if __name__=='__main__':unittest.main()
