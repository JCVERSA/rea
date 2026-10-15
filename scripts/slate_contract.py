"""DEX round-trip normalization; keep executable code/types/access/constants intact."""
import re
from media_grant_patch import normalized_methods


def normalized_class(data):
    # dexlib drops trailing encoded default values. Restrict equivalence to
    # typed static false/null, never true/nonzero/non-null/access/type changes.
    def defaults(match):
        prefix, typ, value = match.groups()
        if (typ == 'Z' and value == 'false') or (typ.startswith(('L','[')) and value == 'null'):
            return prefix + typ
        return match[0]
    text=re.sub(r'^(\.field (?=[^\n]*\bstatic\b)[^\n]*?:)(\S+) = ([^\n]*)$',
                defaults,data.decode(),flags=re.M)
    data=text.encode()
    if b'\n.method ' in data: return normalized_methods(data)
    text='\n'.join(l.strip() for l in data.decode().splitlines()
        if l.strip() and not l.strip().startswith(('#','.source ')))
    if data.count(b'.class ')!=1 or data.count(b'.super ')!=1:
        raise ValueError('Invalid methodless class')
    return {'__class_contract__':text}
