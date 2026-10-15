package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1963g;
import ax.p070e3.C1966j;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import com.alphainventor.filemanager.R;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class U extends AbstractC6238m {
    private ax.p216n5.e h;

    private static class a extends ax.p406z3.q<String, Void, Boolean> {
        Context h;
        InterfaceC6228c.a i;
        U j;
        String k;

        a(Context context, U u, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.h = context;
            this.i = aVar;
            this.j = u;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) {
            String string;
            try {
                ax.U2.o.i().e(this.h);
                ax.p216n5.e eVarK = ax.U2.o.i().k();
                this.j.V(eVarK);
                return eVarK == null ? Boolean.FALSE : Boolean.TRUE;
            } catch (ax.p070e3.C unused) {
                this.k = this.h.getString(R.string.storage_device_not_recognized);
                return Boolean.FALSE;
            } catch (ax.p070e3.G e) {
                if (e.a() == 7) {
                    Context context = this.h;
                    string = context.getString(R.string.not_supported_filesystem, context.getString(R.string.ntfs_exfat_hpfs));
                } else {
                    string = null;
                }
                this.k = string;
                return Boolean.FALSE;
            } catch (C1966j unused2) {
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.i != null) {
                if (bool.booleanValue()) {
                    this.i.d0(true, null);
                } else {
                    this.i.d0(false, this.k);
                }
            }
        }
    }

    private boolean S(AbstractC6239n abstractC6239n, boolean z) {
        ax.p216n5.e eVarT;
        try {
            if (!abstractC6239n.o() && (eVarT = T(abstractC6239n.U())) != null) {
                String strB = abstractC6239n.B();
                if (z) {
                    eVarT.o(strB);
                    return true;
                }
                eVarT.z0(strB).close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        }
        return false;
    }

    private ax.p216n5.e T(String str) throws IOException {
        C2461b.c(d0.B(str));
        ax.p216n5.e eVar = this.h;
        if (eVar == null) {
            return null;
        }
        return d0.D(v(), str) ? eVar : eVar.P0(str.substring(1));
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public V a1(String str) throws C1966j {
        try {
            return d0.D(v(), str) ? new V(this, str, this.h) : new V(this, str, T(str));
        } catch (IOException e) {
            e.printStackTrace();
            if (ax.U2.o.i().a(p())) {
                throw new C1966j(e);
            }
            throw new ax.p070e3.H(e);
        } catch (IllegalArgumentException e2) {
            throw new C1966j(e2);
        }
    }

    void V(ax.p216n5.e eVar) {
        this.h = eVar;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.h != null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        ax.U2.o.i().d();
        V(null);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        if (!a()) {
            return null;
        }
        try {
            return h1(a1(str2), 0L);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        C2461b.c(abstractC6239n.isDirectory());
        try {
            try {
                ArrayList arrayList = new ArrayList();
                ax.p216n5.e eVarT = T(abstractC6239n.E());
                if (eVarT == null) {
                    throw new ax.p070e3.t();
                }
                if (!eVarT.isDirectory()) {
                    throw new C1966j("This is not directory");
                }
                ax.p216n5.e[] eVarArrK0 = eVarT.K0();
                if (eVarArrK0 != null) {
                    for (ax.p216n5.e eVar : eVarArrK0) {
                        String name = eVar.getName();
                        if (!TextUtils.isEmpty(name) && d0.A(name)) {
                            V v = new V(this, d0.Q(abstractC6239n.E(), name), eVar);
                            if (eVar.isDirectory()) {
                                try {
                                    v.e0(eVar.l0().length);
                                } catch (IOException unused) {
                                }
                            }
                            arrayList.add(v);
                        } else if (TextUtils.isEmpty(name)) {
                            ax.p302sb.c.h().g().d("USB CHILD NAME 1").h("name:" + name + ":lfn:" + eVar.f1() + ":short:" + eVar.w()).i();
                        } else {
                            ax.p302sb.c.h().g().d("USB CHILD NAME 2").h("name:" + name + ":lfn:" + eVar.f1() + ":short:" + eVar.w()).i();
                        }
                    }
                }
                return arrayList;
            } catch (IllegalArgumentException e) {
                ax.p302sb.c.h().b("USB illegalargument").m(e).i();
                throw new C1966j(e);
            }
        } catch (com.github.mjdev.libaums.a e2) {
            throw new C1963g(e2);
        } catch (IOException e3) {
            throw new C1966j(e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (EnumC2019z.IMAGE != abstractC6239n.G()) {
            return null;
        }
        return ax.p086f3.B.Y(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        C2461b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        try {
            ax.p216n5.e eVarT = T(abstractC6239n.E());
            if (eVarT == null) {
                throw new C1966j("UsbFile is null");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new ax.p216n5.f(eVarT));
            if (j == 0) {
                return bufferedInputStream;
            }
            bufferedInputStream.skip(j);
            return bufferedInputStream;
        } catch (IOException e) {
            if (ax.U2.o.i().a(p())) {
                throw new C1966j(e);
            }
            throw new ax.p070e3.H(e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            throw new C1966j(e);
        } catch (UnsupportedOperationException e3) {
            e = e3;
            throw new C1966j(e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        try {
            ax.p216n5.e eVarT = T(abstractC6239n.E());
            if (eVarT == null) {
                throw new C1966j("Cannot get source usb file");
            }
            long length = eVarT.getLength();
            String strU = abstractC6239n.U();
            String strU2 = abstractC6239n2.U();
            if (strU.equals(strU2)) {
                eVarT.G0(abstractC6239n2.B());
            } else {
                ax.p216n5.e eVarT2 = T(strU2);
                if (eVarT2 == null) {
                    throw new C1966j("Target parent does not exist");
                }
                if (!abstractC6239n.B().equals(abstractC6239n2.B())) {
                    eVarT.G0(abstractC6239n2.B());
                }
                eVarT.o0(eVarT2);
            }
            if (iVar != null) {
                iVar.a(length, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new C1966j(e);
        } catch (IllegalArgumentException e2) {
            throw new C1966j(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        if (aVar != null) {
            aVar.J();
        }
        try {
            new a(p(), this, aVar).h(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n.o());
        InputStream inputStream = null;
        try {
            try {
                ax.p216n5.e eVarT = T(abstractC6239n.U());
                if (eVarT == null) {
                    throw new ax.p070e3.t();
                }
                ax.p216n5.e eVarZ0 = eVarT.z0(abstractC6239n.B());
                byte[] bArr = new byte[8192];
                InputStream inputStreamB = g.b();
                long j2 = 0;
                while (true) {
                    int i = inputStreamB.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    eVarZ0.n(j2, ByteBuffer.wrap(bArr, 0, i));
                    j2 += (long) i;
                    if (iVar != null) {
                        iVar.a(j2, j);
                    }
                }
                if (l != null && l.longValue() > 0) {
                    try {
                        eVarZ0.a1(l.longValue());
                    } catch (Exception unused) {
                    }
                }
                eVarZ0.close();
                try {
                    inputStreamB.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            if (e3.getMessage() == null || !e3.getMessage().startsWith("Could not write")) {
                ax.p302sb.c.h().g().d("!!USB writeFile 3").h(e3.getMessage()).i();
            } else if (ax.U2.o.i().a(p())) {
                ax.p302sb.c.h().g().d("!!USB writeFile 1 : could not write").h(e3.getMessage()).i();
            }
            if (!ax.U2.o.i().a(p())) {
                throw new ax.p070e3.H(e3);
            }
            throw new C1966j(e3);
        } catch (ArrayIndexOutOfBoundsException e4) {
            e = e4;
            throw new C1966j(e);
        } catch (IllegalArgumentException e5) {
            e = e5;
            throw new C1966j(e);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        l1(abstractC6239n2, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        return S(abstractC6239n, true);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return S(abstractC6239n, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            ax.p216n5.e eVarT = T(abstractC6239n.E());
            if (eVarT == null) {
                throw new C1966j("USBFile is null");
            }
            eVarT.delete();
        } catch (com.github.mjdev.libaums.a e) {
            e.printStackTrace();
            throw new C1966j(e);
        } catch (IOException e2) {
            e2.printStackTrace();
            if (!ax.U2.o.i().a(p())) {
                throw new ax.p070e3.H(e2);
            }
            ax.p302sb.c.h().g().d("Usb delete 1").h(e2.getMessage()).i();
            throw new C1966j(e2);
        } catch (IllegalArgumentException e3) {
            ax.p302sb.c.h().g().d("USB IllegalArgumentException?").m(e3).i();
            throw new C1966j(e3);
        } catch (IllegalStateException e4) {
            ax.p302sb.c.h().g().d("DELETE USB ROOT?").h(abstractC6239n.E()).i();
            throw new C1966j(e4);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        return new k0(ax.U2.o.i().h(), ax.U2.o.i().j(), 0);
    }
}
