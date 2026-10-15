"""Fixed-size original ARSC/AXML value overlay: no linking, strings or ID remapping.
Supports only this fingerprinted APK's dense/simple color entries and dimension values.
Unknown structures fail closed; this is NOT a general Android resource compiler.
"""
from pathlib import Path
from zipfile import ZipFile
import hashlib
import json
import struct
import sys
import xml.etree.ElementTree as ET

ROOT = Path(__file__).resolve().parent.parent
BASE_SHA = '0f7dbe666b6be1c1f99f1b324c65899ebbca6cc56fe55d3809bc7684242b762b'
# Each pair: default/light, night. References resolve using Android's existing qualifiers.
COLORS = {
 'main_background_light': ('fcfaff', 'fcfaff'),
 'main_background_dark': ('000000', '000000'),
 'main_background': ('@main_background_light', '@main_background_dark'),
 'main_primary_text': ('1d1924', 'f7f2fc'),
 'main_secondary_text': ('635c6b', 'c0b7c9'),
 'primary': ('4a3374', '0c0b10'), 'primary_dark': ('2e1a57', '000000'),
 'accent_color': ('6550a5', 'd9c7ff'), 'accent_background': ('eaddff', '4a3374'),
 'cardfragment_background': ('@main_background', '@main_background'),
 'cardview_background': ('fffbff', '17141e'),
 'nav_background': ('fffbff', '0c0b10'),
 'nav_label_background': ('f1ecf5', '17141e'),
 'nav_primary_text_default': ('@main_primary_text', '@main_primary_text'),
 'nav_secondary_text': ('@main_secondary_text', '@main_secondary_text'),
 'nav_tab_background': ('@nav_background', '@nav_background'),
 'nav_tab_separator': ('e6dfec', '211b2d'),
 'desktop2_tab_background': ('@nav_background', '@nav_background'),
 'desktop2_tab_background_border': ('@nav_tab_separator', '@nav_tab_separator'),
 'desktop2_tab_text_selected': ('@accent_color', '@accent_color'),
 'desktop2_tab_text_normal': ('@main_secondary_text', '@main_secondary_text'),
 'desktop2_tab_text_indicator': ('@accent_color', '@accent_color'),
 'desktop2_carousel_background_color': ('@primary', '@primary'),
 'desktop2_carousel_foreground_color': ('ffffff', 'ffffff'),
}
# Match preexisting dimension attributes only. Keep every view, ID, reference and action.
RULES = {
 'res/layout/desktop2_item.xml': {('paddingTop',5):12, ('paddingBottom',5):12, ('layout_margin',2):6},
 'res/layout/desktop_item.xml': {('paddingTop',5):10, ('paddingBottom',5):10, ('layout_margin',2):6},
 'res/layout/desktop_item_new.xml': {('cardCornerRadius',2):18, ('contentPaddingTop',4):12, ('paddingTop',5):10, ('paddingBottom',5):10},
 'res/layout/nav_fixed_list_item.xml': {('paddingLeft',2):8, ('paddingTop',2):8, ('paddingRight',2):8, ('paddingBottom',2):8, ('minHeight',52):60, ('layout_margin',4):6, ('layout_marginLeft',4):8, ('layout_marginRight',6):12},
 'res/layout/fragment_desktop.xml': {('verticalSpacing',5):12},
 'res/layout/fragment_desktop2.xml': {('verticalSpacing',5):12, ('tabIndicatorHeight',4):3},
}


def u16(b, p): return struct.unpack_from('<H', b, p)[0]
def u32(b, p): return struct.unpack_from('<I', b, p)[0]


def chunks(b, start, end):
    p = start
    while p < end:
        if p + 8 > end: raise ValueError('Truncated chunk')
        kind, header, size = struct.unpack_from('<HHI', b, p)
        if not (8 <= header <= size and p + size <= end): raise ValueError('Invalid chunk bounds')
        yield p, kind, header, size
        p += size
    if p != end: raise ValueError('Misaligned chunk end')


def ids():
    with ZipFile(ROOT / 'decompiled/complete-apktool.zip') as z:
        public = ET.fromstring(z.read('res/values/public.xml'))
    return {e.get('name'): int(e.get('id'),16) for e in public if e.get('type') == 'color'}


def table_overlay(original):
    b = bytearray(original); names = ids(); by_id = {v:k for k,v in names.items()}
    if u16(b,0) != 2 or u32(b,4) != len(b): raise ValueError('Unexpected ARSC root')
    changes = []; found = set()
    for package, kind, header, size in chunks(b, u16(b,2),len(b)):
        if kind != 0x200: continue
        if header != 288 or u32(b,package+284) != 0: raise ValueError('Unexpected package/ID offset')
        package_id = u32(b,package+8)
        for p, t, h, s in chunks(b, package+header, package+size):
            if t != 0x201: continue
            type_id = b[p+8]
            # Only color type; all other data stays byte-for-byte original.
            if not any((x>>16)&255 == type_id and x>>24 == package_id for x in names.values()): continue
            if b[p+9] != 0 or u32(b,p+20) < 32: raise ValueError('Unsupported color encoding')
            count, entry_start = u32(b,p+12), u32(b,p+16)
            if h + count*4 > entry_start or entry_start > s: raise ValueError('Bad entry offsets')
            night = (b[p+20+29] & 0x30) == 0x20
            for i in range(count):
                name = by_id.get(package_id<<24 | type_id<<16 | i)
                if name not in COLORS: continue
                off = u32(b,p+h+i*4)
                if off == 0xffffffff: continue
                e = p+entry_start+off
                if e+16 > p+s or u16(b,e) != 8 or u16(b,e+2)&1: raise ValueError('Non-simple color')
                v = e+8
                if u16(b,v) != 8 or b[v+2] != 0: raise ValueError('Unexpected color value')
                target = COLORS[name][night]
                typ, value = (1,names[target[1:]]) if target.startswith('@') else (0x1c,int('ff'+target,16))
                old = bytes(b[v:v+8]); b[v+3] = typ; struct.pack_into('<I',b,v+4,value)
                changes.append({'name':name, 'night':night, 'offset':v, 'before':old.hex(), 'after':bytes(b[v:v+8]).hex()})
                found.add(name)
    if found != set(COLORS): raise ValueError('Missing palette colors: '+str(set(COLORS)-found))
    if len(b) != len(original): raise ValueError('Table size changed')
    return bytes(b), changes


def string_pool(b,p):
    count, flags, start = u32(b,p+8),u32(b,p+16),u32(b,p+20)
    header = u16(b,p+2)
    def length(pos, utf8):
        n = b[pos] if utf8 else u16(b,pos); step = 1 if utf8 else 2; high = 0x80 if utf8 else 0x8000
        if n & high: return ((n&(high-1))<<(7 if utf8 else 16)) | (b[pos+step] if utf8 else u16(b,pos+step)), pos+2*step
        return n,pos+step
    result=[]
    for i in range(count):
        pos=p+start+u32(b,p+header+4*i)
        n,pos=length(pos,bool(flags&0x100))
        if flags&0x100:
            n,pos=length(pos,True); result.append(bytes(b[pos:pos+n]).decode('utf-8'))
        else: result.append(bytes(b[pos:pos+2*n]).decode('utf-16le'))
    return result


def dimension(data):
    signed = struct.unpack('<i',struct.pack('<I',data & 0xffffff00))[0]
    return signed * [1/256,1/32768,1/8388608,1/2147483648][(data>>4)&3]


def xml_overlay(original, rules):
    b=bytearray(original); strings=None; changes=[]; found=set()
    if u16(b,0)!=3 or u32(b,4)!=len(b): raise ValueError('Unexpected binary XML')
    for p,t,h,s in chunks(b,u16(b,2),len(b)):
        if t==1: strings=string_pool(b,p)
        if t!=0x102: continue
        if strings is None or h!=16: raise ValueError('Unexpected XML node')
        a=p+h+u16(b,p+h+8); stride=u16(b,p+h+10); count=u16(b,p+h+12)
        if stride!=20 or a+stride*count>p+s: raise ValueError('Unexpected attributes')
        for i in range(count):
            at=a+i*stride; name=strings[u32(b,at+4)]
            if b[at+15]!=5: continue  # TYPE_DIMENSION only
            old=u32(b,at+16)
            key=(name,dimension(old))
            if key not in rules: continue
            if old&15 != 1 or u32(b,at+8)!=0xffffffff: raise ValueError('Expected dp without raw string')
            new=(rules[key]<<8)|1
            struct.pack_into('<I',b,at+16,new); found.add(key)
            changes.append({'element':strings[u32(b,p+h+4)],'attribute':name,'offset':at+16,'before':old,'after':new,'dp':rules[key]})
    if found!=set(rules): raise ValueError('Missing XML dimensions: '+str(set(rules)-found))
    if len(b)!=len(original): raise ValueError('XML size changed')
    return bytes(b),changes


def expected_resources():
    if hashlib.sha256((ROOT/'base.apk').read_bytes()).hexdigest()!=BASE_SHA: raise ValueError('Wrong base APK')
    with ZipFile(ROOT/'base.apk') as z:
        replacements={}; report={}
        replacements['resources.arsc'], report['colors']=table_overlay(z.read('resources.arsc'))
        for name,rules in RULES.items(): replacements[name],report[name]=xml_overlay(z.read(name),rules)
    return replacements,report


def apply():
    replacements,report=expected_resources()
    apk=ROOT/'.work/witness/unsigned.apk'; out=apk.with_name('slate-overlay.apk')
    with ZipFile(ROOT/'base.apk') as base, ZipFile(apk) as src, ZipFile(out,'w') as dst:
        if len(src.namelist())!=len(set(src.namelist())): raise ValueError('Duplicate ZIP entries')
        for entry in src.infolist():
            n=entry.filename
            if n=='resources.arsc' or n.startswith('res/'):
                if src.read(n)!=base.read(n): raise ValueError('Apply overlay only after original resources restored')
            dst.writestr(entry,replacements.get(n,src.read(n)))
    out.replace(apk)
    report['recipe']='fixed-size original compiled value overlay; no resource linking or ID changes'
    report['material3_status']='SwiftSlate-inspired palette/spacing/radius only; existing Views/widgets retained, not a full Material3 component migration'
    (ROOT/'.work/witness/reports/slate-overlay.json').write_text(json.dumps(report,indent=2)+'\n')


if __name__=='__main__':
    if sys.argv[1:]!=['apply']: raise SystemExit('Usage: slate_resources.py apply')
    apply()
