import unittest
from pathlib import Path
from slate_screen import pixel

ROOT=Path(__file__).resolve().parent.parent
class ScreenTests(unittest.TestCase):
    def test_real_screencap_day_surface(self):
        p=ROOT/'analysis/slate-safety/38674060896/light-home.png'
        self.assertEqual(pixel(p.read_bytes(),1,320),(252,250,255))
    def test_previous_dark_label_not_dark_is_detected(self):
        p=ROOT/'analysis/slate-safety/38674060896/dark-home.png'
        self.assertNotEqual(pixel(p.read_bytes(),1,320),(0,0,0))
    def test_real_navigation_surface(self):
        p=ROOT/'analysis/slate-safety/38674060896/light-navigation.png'
        self.assertEqual(pixel(p.read_bytes(),80,480),(255,251,255))
    def test_bad_png_and_bounds_rejected(self):
        with self.assertRaises(ValueError):pixel(b'invalid',0,0)
        p=ROOT/'analysis/slate-safety/38674060896/light-home.png'
        with self.assertRaises(ValueError):pixel(p.read_bytes(),-1,0)

if __name__=='__main__':unittest.main()
