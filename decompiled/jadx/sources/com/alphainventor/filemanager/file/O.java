package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0715f;
import ax.I1.C0718i;
import ax.J1.C0740v;
import ax.J1.EnumC0738t;
import ax.J1.V;
import ax.J1.b0;
import com.alphainventor.filemanager.R;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class O extends AbstractC5837k {
    private ax.Q3.e h;

    private static class a extends ax.p045d2.n<String, Void, Boolean> {
        Context h;
        InterfaceC5828b.a i;
        O j;
        String k;

        a(Context context, O o, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.h = context;
            this.i = aVar;
            this.j = o;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) {
            String string;
            try {
                ax.p338y1.l.i().e(this.h);
                ax.Q3.e eVarK = ax.p338y1.l.i().k();
                this.j.P(eVarK);
                return eVarK == null ? Boolean.FALSE : Boolean.TRUE;
            } catch (ax.I1.A unused) {
                this.k = this.h.getString(R.string.storage_device_not_recognized);
                return Boolean.FALSE;
            } catch (ax.I1.E e) {
                if (e.a() == 7) {
                    Context context = this.h;
                    string = context.getString(R.string.not_supported_filesystem, context.getString(R.string.ntfs_exfat_hpfs));
                } else {
                    string = null;
                }
                this.k = string;
                return Boolean.FALSE;
            } catch (C0718i unused2) {
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.i != null) {
                if (bool.booleanValue()) {
                    this.i.f0(true, null);
                } else {
                    this.i.f0(false, this.k);
                }
            }
        }
    }

    private boolean M(AbstractC5838l abstractC5838l, boolean z) {
        ax.Q3.e eVarN;
        try {
            if (!abstractC5838l.n() && (eVarN = N(abstractC5838l.S())) != null) {
                String strW = abstractC5838l.w();
                if (z) {
                    eVarN.q(strW);
                    return true;
                }
                eVarN.w0(strW).close();
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

    private ax.Q3.e N(String str) throws IOException {
        ax.p045d2.b.c(V.y(str));
        ax.Q3.e eVar = this.h;
        if (eVar == null) {
            return null;
        }
        return V.A(s(), str) ? eVar : eVar.O0(str.substring(1));
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public P V0(String str) throws C0718i {
        try {
            return V.A(s(), str) ? new P(this, str, this.h) : new P(this, str, N(str));
        } catch (IOException e) {
            e.printStackTrace();
            if (ax.p338y1.l.i().a(n())) {
                throw new C0718i(e);
            }
            throw new ax.I1.F(e);
        } catch (IllegalArgumentException e2) {
            throw new C0718i(e2);
        }
    }

    void P(ax.Q3.e eVar) {
        this.h = eVar;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        if (!a()) {
            return null;
        }
        try {
            return d1(V0(str2), 0L);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (EnumC0738t.IMAGE != abstractC5838l.E()) {
            return null;
        }
        return C0740v.Q(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.h != null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        ax.p338y1.l.i().d();
        P(null);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p045d2.b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l.n());
        InputStream inputStream = null;
        try {
            try {
                ax.Q3.e eVarN = N(abstractC5838l.S());
                if (eVarN == null) {
                    throw new ax.I1.s();
                }
                ax.Q3.e eVarW0 = eVarN.w0(abstractC5838l.w());
                byte[] bArr = new byte[8192];
                InputStream inputStreamB = a2.b();
                long j2 = 0;
                while (true) {
                    int i = inputStreamB.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    eVarW0.p(j2, ByteBuffer.wrap(bArr, 0, i));
                    j2 += (long) i;
                    if (iVar != null) {
                        iVar.a(j2, j);
                    }
                }
                if (l != null && l.longValue() > 0) {
                    try {
                        eVarW0.W0(l.longValue());
                    } catch (Exception unused) {
                    }
                }
                eVarW0.close();
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
                ax.p221pa.c.h().g().d("!!USB writeFile 3").h(e3.getMessage()).i();
            } else if (ax.p338y1.l.i().a(n())) {
                ax.p221pa.c.h().g().d("!!USB writeFile 1 : could not write").h(e3.getMessage()).i();
            }
            if (!ax.p338y1.l.i().a(n())) {
                throw new ax.I1.F(e3);
            }
            throw new C0718i(e3);
        } catch (ArrayIndexOutOfBoundsException e4) {
            e = e4;
            throw new C0718i(e);
        } catch (IllegalArgumentException e5) {
            e = e5;
            throw new C0718i(e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        try {
            ax.Q3.e eVarN = N(abstractC5838l.C());
            if (eVarN == null) {
                throw new C0718i("UsbFile is null");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new ax.Q3.f(eVarN));
            if (j != 0) {
                bufferedInputStream.skip(j);
            }
            return bufferedInputStream;
        } catch (IOException e) {
            if (ax.p338y1.l.i().a(n())) {
                throw new C0718i(e);
            }
            throw new ax.I1.F(e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            throw new C0718i(e);
        } catch (UnsupportedOperationException e3) {
            e = e3;
            throw new C0718i(e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        if (aVar != null) {
            aVar.N();
        }
        try {
            new a(n(), this, aVar).h(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        try {
            try {
                ArrayList arrayList = new ArrayList();
                ax.Q3.e eVarN = N(abstractC5838l.C());
                if (eVarN == null) {
                    throw new ax.I1.s();
                }
                if (!eVarN.isDirectory()) {
                    throw new C0718i("This is not directory");
                }
                ax.Q3.e[] eVarArrK0 = eVarN.K0();
                if (eVarArrK0 != null) {
                    for (ax.Q3.e eVar : eVarArrK0) {
                        String name = eVar.getName();
                        if (!TextUtils.isEmpty(name) && V.x(name)) {
                            P p = new P(this, V.M(abstractC5838l.C(), name), eVar);
                            if (eVar.isDirectory()) {
                                try {
                                    p.b0(eVar.i0().length);
                                } catch (IOException unused) {
                                }
                            }
                            arrayList.add(p);
                        } else if (TextUtils.isEmpty(name)) {
                            ax.p221pa.c.h().g().d("USB CHILD NAME 1").h("name:" + name + ":lfn:" + eVar.c1() + ":short:" + eVar.C()).i();
                        } else {
                            ax.p221pa.c.h().g().d("USB CHILD NAME 2").h("name:" + name + ":lfn:" + eVar.c1() + ":short:" + eVar.C()).i();
                        }
                    }
                }
                return arrayList;
            } catch (IllegalArgumentException e) {
                ax.p221pa.c.h().b("USB illegalargument").m(e).i();
                throw new C0718i(e);
            }
        } catch (com.github.mjdev.libaums.a e2) {
            throw new C0715f(e2);
        } catch (IOException e3) {
            throw new C0718i(e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        return M(abstractC5838l, true);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        return M(abstractC5838l, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        m(abstractC5838l, str, z, hVar, cVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        try {
            ax.Q3.e eVarN = N(abstractC5838l.C());
            if (eVarN == null) {
                throw new C0718i("Cannot get source usb file");
            }
            long jE = eVarN.e();
            String strS = abstractC5838l.S();
            String strS2 = abstractC5838l2.S();
            if (strS.equals(strS2)) {
                eVarN.G0(abstractC5838l2.w());
            } else {
                ax.Q3.e eVarN2 = N(strS2);
                if (eVarN2 == null) {
                    throw new C0718i("Target parent does not exist");
                }
                if (!abstractC5838l.w().equals(abstractC5838l2.w())) {
                    eVarN.G0(abstractC5838l2.w());
                }
                eVarN.O(eVarN2);
            }
            if (iVar != null) {
                iVar.a(jE, jE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new C0718i(e);
        } catch (IllegalArgumentException e2) {
            throw new C0718i(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            ax.Q3.e eVarN = N(abstractC5838l.C());
            if (eVarN == null) {
                throw new C0718i("USBFile is null");
            }
            eVarN.delete();
        } catch (com.github.mjdev.libaums.a e) {
            e.printStackTrace();
            throw new C0718i(e);
        } catch (IOException e2) {
            e2.printStackTrace();
            if (!ax.p338y1.l.i().a(n())) {
                throw new ax.I1.F(e2);
            }
            ax.p221pa.c.h().g().d("Usb delete 1").h(e2.getMessage()).i();
            throw new C0718i(e2);
        } catch (IllegalArgumentException e3) {
            ax.p221pa.c.h().g().d("USB IllegalArgumentException?").m(e3).i();
            throw new C0718i(e3);
        } catch (IllegalStateException e4) {
            ax.p221pa.c.h().g().d("DELETE USB ROOT?").h(abstractC5838l.C()).i();
            throw new C0718i(e4);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        return new b0(ax.p338y1.l.i().h(), ax.p338y1.l.i().j(), 0);
    }
}
