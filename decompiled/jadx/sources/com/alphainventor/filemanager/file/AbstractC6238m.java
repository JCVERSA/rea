package com.alphainventor.filemanager.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.C2011q;
import ax.p086f3.C2015v;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p086f3.f0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import j$.util.DesugarCollections;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC6238m implements InterfaceC6228c {
    private static boolean g;
    private Context a;
    private ax.p086f3.K b;
    private Set<String> c = DesugarCollections.synchronizedSet(new HashSet());
    private Set<String> d = DesugarCollections.synchronizedSet(new HashSet());
    private final Object e = new Object();
    private ReentrantLock f = new ReentrantLock();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$a */
    class a implements InterfaceC2462c {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // ax.p406z3.InterfaceC2462c
        public boolean isCancelled() {
            return !AbstractC6238m.this.d.contains(this.a);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$b */
    class b implements InterfaceC2462c {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // ax.p406z3.InterfaceC2462c
        public boolean isCancelled() {
            return !AbstractC6238m.this.d.contains(this.a);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$c */
    class c implements Runnable {
        final /* synthetic */ boolean X;
        final /* synthetic */ Object Y;
        final /* synthetic */ InterfaceC6228c.a q;

        c(InterfaceC6228c.a aVar, boolean z, Object obj) {
            this.q = aVar;
            this.X = z;
            this.Y = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.q.d0(this.X, this.Y);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$d */
    protected static class d extends ax.p406z3.q<Long, Long, Long> {
        AbstractC6238m h;

        public d(AbstractC6238m abstractC6238m) {
            super(ax.z3.q.e.HIGH);
            this.h = abstractC6238m;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            if (!this.h.a()) {
                return null;
            }
            this.h.b();
            return null;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$e */
    protected class e extends ax.p086f3.G {
        AbstractC6239n a;

        e(AbstractC6239n abstractC6239n) {
            this.a = abstractC6239n;
        }

        @Override // ax.p086f3.G
        public boolean a() {
            return true;
        }

        @Override // ax.p086f3.G
        public InputStream c(long j) throws C1966j {
            return AbstractC6238m.this.h1(this.a, j);
        }

        @Override // ax.p086f3.G
        public long d() {
            return this.a.p();
        }

        public ax.p086f3.K e() {
            return AbstractC6238m.this.v();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$f */
    public enum f {
        NONE,
        USE_MEDIA_STORE
    }

    protected AbstractC6238m() {
    }

    public static String A(AbstractC6239n abstractC6239n) {
        return ax.p086f3.B.Y(abstractC6239n);
    }

    private InputStream B(MediaDataSource mediaDataSource) {
        Bitmap bitmapH = ax.p406z3.B.h(mediaDataSource, 512);
        if (bitmapH == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
        bitmapH.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private InputStream C(String str) throws C1957a {
        try {
            AbstractC6239n abstractC6239nA1 = a1(str);
            this.d.add(str);
            a aVar = new a(str);
            ax.p006a3.Q.t1();
            InputStream inputStreamB = B(new f0(this, abstractC6239nA1, aVar));
            if (inputStreamB == null && aVar.isCancelled()) {
                throw new C1957a();
            }
            this.d.remove(str);
            return inputStreamB;
        } catch (C1966j unused) {
            this.d.remove(str);
            return null;
        } catch (Throwable th) {
            this.d.remove(str);
            throw th;
        }
    }

    public static void F(Context context) {
        if (g || context == null || !ax.p406z3.B.g0(context)) {
            return;
        }
        g = true;
    }

    public static boolean k(Collection<AbstractC6239n> collection, AbstractC6239n abstractC6239n) {
        Iterator<AbstractC6239n> it = collection.iterator();
        while (it.hasNext()) {
            if (abstractC6239n.E().equals(it.next().E())) {
                return true;
            }
        }
        return false;
    }

    private InputStream z(String str) {
        try {
            AbstractC6239n abstractC6239nA1 = a1(str);
            File fileA0 = abstractC6239nA1.a0();
            if (ax.p086f3.B.G(fileA0, abstractC6239nA1)) {
                ax.p038c3.t.b().e(fileA0);
            } else {
                this.d.add(str);
                try {
                    ax.p086f3.F.f(h1(abstractC6239nA1, 0L), fileA0, abstractC6239nA1.p(), new b(str));
                    ax.p038c3.t.b().c(fileA0);
                    ax.p038c3.t.b().a();
                } finally {
                    this.d.remove(str);
                }
            }
            return new FileInputStream(fileA0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean D() {
        return false;
    }

    public void E(Context context, ax.p086f3.K k) {
        this.a = context;
        this.b = k;
    }

    public boolean G() {
        return false;
    }

    protected boolean H(AbstractC6239n abstractC6239n) {
        EnumC2019z enumC2019zG = abstractC6239n.G();
        if (EnumC2019z.IMAGE != enumC2019zG) {
            return EnumC2019z.VIDEO == enumC2019zG && ax.p006a3.Q.r0();
        }
        String strY = abstractC6239n.y();
        if (!ax.p086f3.A.O(strY) || "dng".equals(strY)) {
            return true;
        }
        return ax.p358w3.d.l(p(), ax.p086f3.B.Y(abstractC6239n)) != null;
    }

    public boolean I(AbstractC6239n abstractC6239n) {
        return true;
    }

    public List<AbstractC6239n> J(AbstractC6239n abstractC6239n) throws C1966j {
        return d1(abstractC6239n, f.NONE);
    }

    public boolean K() {
        return false;
    }

    protected void L(InterfaceC6228c.a aVar, boolean z, Object obj) {
        if (aVar == null) {
            return;
        }
        N(new c(aVar, z, obj));
    }

    public ax.p262q3.a M(String str, int i) throws C1966j {
        C2461b.g("Proxy file is not supported");
        throw new ax.p070e3.t("Proxy file is not supported");
    }

    protected void N(Runnable runnable) {
        ax.p406z3.B.a0(runnable);
    }

    public boolean O() {
        return false;
    }

    public boolean P() {
        return false;
    }

    public boolean Q() {
        return true;
    }

    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.f();
        throw new C1966j("Not supported");
    }

    public void f(String str) {
        this.d.remove(str);
    }

    public void g() {
        this.c.clear();
    }

    public void h(InterfaceC6228c.a aVar) {
        j1(null, null, aVar);
    }

    protected void i() {
        this.f.lock();
    }

    protected void j() {
        this.f.unlock();
    }

    protected boolean l(AbstractC6239n abstractC6239n) {
        if (abstractC6239n.o()) {
            return false;
        }
        try {
            l1(abstractC6239n, ax.p086f3.F.e(new byte[0]), abstractC6239n.t(), 0L, null, null, true, null, null);
            return true;
        } catch (C1957a | C1966j e2) {
            e2.printStackTrace();
            return false;
        }
    }

    abstract void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j;

    public void n(AbstractC6239n abstractC6239n) throws C1966j {
        ax.p302sb.c.h().g().b("FILL FILE SIZE").h("Loation:" + abstractC6239n.Q().H()).i();
        throw new C1966j("Not supported");
    }

    void o(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) {
        List<AbstractC6239n> listJ;
        Stack stack = new Stack();
        stack.push(abstractC6239n);
        while (!stack.isEmpty()) {
            AbstractC6239n abstractC6239n2 = (AbstractC6239n) stack.pop();
            if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                return;
            }
            try {
                if (ax.U2.f.D0(abstractC6239n2.Q(), abstractC6239n2)) {
                    listJ = ax.p038c3.b.k().g(abstractC6239n2);
                    if (listJ == null) {
                        listJ = J(abstractC6239n2);
                        ax.p038c3.b.k().m(abstractC6239n2, listJ);
                    }
                } else {
                    listJ = J(abstractC6239n2);
                }
                if (listJ == null) {
                    return;
                }
                List<AbstractC6239n> listG = C2011q.g(listJ, C2011q.c("Search"));
                boolean zP = ax.p086f3.B.P(abstractC6239n2);
                List<AbstractC6239n> listH = ax.p086f3.B.h(listG, str, z, zP);
                if (z2) {
                    for (AbstractC6239n abstractC6239n3 : ax.p086f3.B.h(listG, null, z, zP)) {
                        if (abstractC6239n3.isDirectory() && !J.k2(abstractC6239n2)) {
                            stack.push(abstractC6239n3);
                        }
                    }
                }
                boolean zIsEmpty = stack.isEmpty();
                if ((listH != null && !listH.isEmpty()) || zIsEmpty) {
                    hVar.i0(listH, zIsEmpty);
                }
            } catch (C1966j e2) {
                e2.printStackTrace();
            }
        }
    }

    public Context p() {
        return this.a;
    }

    public long q() throws C1966j {
        k0 k0VarY;
        if (!G() || (k0VarY = y()) == null || k0VarY.b == 0) {
            return -1L;
        }
        long j = k0VarY.d;
        if (j >= 0) {
            return j;
        }
        return -1L;
    }

    public String r() {
        return this.b.e();
    }

    public ax.p086f3.G s(AbstractC6239n abstractC6239n) {
        return new e(abstractC6239n);
    }

    public int t() {
        return this.b.b();
    }

    public ax.U2.f u() {
        return this.b.d();
    }

    public ax.p086f3.K v() {
        return this.b;
    }

    protected InputStream w(String str, String str2) {
        InputStream inputStreamC;
        if (!a() || this.c.contains(str2)) {
            return null;
        }
        String strK = d0.k(str2);
        EnumC2019z enumC2019zG = ax.p086f3.A.g(strK);
        try {
            if (enumC2019zG != EnumC2019z.IMAGE) {
                if (enumC2019zG != EnumC2019z.VIDEO) {
                    String strG = C2015v.g(strK, HttpUrl.FRAGMENT_ENCODE_SET);
                    if (!ax.p086f3.A.I(strG) && !ax.p086f3.A.U(strG)) {
                        C2461b.f();
                    }
                } else if (ax.p006a3.Q.r0()) {
                    inputStreamC = C(str2);
                }
                inputStreamC = null;
            } else if (ax.p086f3.A.S(strK)) {
                inputStreamC = ax.p358w3.c.e(z(str2), (int) (ax.p406z3.B.s(p()) * 128.0f));
                if (inputStreamC == null) {
                    inputStreamC = ax.p358w3.b.n();
                }
            } else {
                inputStreamC = z(str2);
            }
            if (inputStreamC == null) {
                this.c.add(str2);
            }
            return inputStreamC;
        } catch (C1957a unused) {
            return null;
        }
    }

    public String x() {
        return this.b.e();
    }

    public k0 y() throws C1966j {
        return null;
    }
}
