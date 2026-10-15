package com.alphainventor.filemanager.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import ax.I1.C0710a;
import ax.I1.C0718i;
import ax.J1.C0733n;
import ax.J1.C0735p;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.EnumC0738t;
import ax.J1.V;
import ax.J1.X;
import ax.J1.b0;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC5837k implements InterfaceC5828b {
    private static boolean g = false;
    private Context a;
    private ax.J1.F b;
    private Set<String> c = Collections.synchronizedSet(new HashSet());
    private Set<String> d = Collections.synchronizedSet(new HashSet());
    private final Object e = new Object();
    private ReentrantLock f = new ReentrantLock();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$a */
    class a implements ax.p045d2.c {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // ax.p045d2.c
        public boolean isCancelled() {
            return !AbstractC5837k.this.d.contains(this.a);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$b */
    class b implements ax.p045d2.c {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // ax.p045d2.c
        public boolean isCancelled() {
            return !AbstractC5837k.this.d.contains(this.a);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$c */
    protected static class c extends ax.p045d2.n<Long, Long, Long> {
        AbstractC5837k h;

        public c(AbstractC5837k abstractC5837k) {
            super(ax.d2.n.f.HIGH);
            this.h = abstractC5837k;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            if (!this.h.a()) {
                return null;
            }
            this.h.b();
            return null;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$d */
    protected class d extends ax.J1.A {
        AbstractC5838l a;

        d(AbstractC5838l abstractC5838l) {
            this.a = abstractC5838l;
        }

        @Override // ax.J1.A
        public boolean a() {
            return true;
        }

        @Override // ax.J1.A
        public InputStream c(long j) throws C0718i {
            return AbstractC5837k.this.d1(this.a, j);
        }

        public ax.J1.F d() {
            return AbstractC5837k.this.s();
        }
    }

    protected AbstractC5837k() {
    }

    public static void C(Context context) {
        if (g || context == null || !ax.p045d2.x.g0(context)) {
            return;
        }
        g = true;
    }

    private InputStream w(String str) {
        try {
            AbstractC5838l abstractC5838lV0 = V0(str);
            File fileY = abstractC5838lV0.Y();
            if (C0740v.z(fileY, abstractC5838lV0)) {
                ax.G1.q.b().e(fileY);
            } else {
                this.d.add(str);
                try {
                    C0744z.h(d1(abstractC5838lV0, 0L), fileY, abstractC5838lV0.o(), new b(str));
                    ax.G1.q.b().c(fileY);
                    ax.G1.q.b().a();
                } finally {
                    this.d.remove(str);
                }
            }
            return new FileInputStream(fileY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String x(AbstractC5838l abstractC5838l) {
        return C0740v.Q(abstractC5838l);
    }

    private InputStream y(MediaDataSource mediaDataSource) {
        Bitmap bitmapH = ax.p045d2.x.h(mediaDataSource, 512);
        if (bitmapH == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
        bitmapH.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private InputStream z(String str) throws C0710a {
        try {
            AbstractC5838l abstractC5838lV0 = V0(str);
            this.d.add(str);
            a aVar = new a(str);
            ax.E1.P.f1();
            InputStream inputStreamY = y(new X(this, abstractC5838lV0, aVar));
            if (inputStreamY == null && aVar.isCancelled()) {
                throw new C0710a();
            }
            this.d.remove(str);
            return inputStreamY;
        } catch (C0718i unused) {
            this.d.remove(str);
            return null;
        } catch (Throwable th) {
            this.d.remove(str);
            throw th;
        }
    }

    public boolean A() {
        return false;
    }

    public void B(Context context, ax.J1.F f) {
        this.a = context;
        this.b = f;
    }

    public boolean D() {
        return false;
    }

    protected boolean E(AbstractC5838l abstractC5838l) {
        EnumC0738t enumC0738tE = abstractC5838l.E();
        if (EnumC0738t.IMAGE != enumC0738tE) {
            return EnumC0738t.VIDEO == enumC0738tE && ax.E1.P.k0();
        }
        String strV = abstractC5838l.v();
        if (!C0739u.L(strV) || "dng".equals(strV)) {
            return true;
        }
        return ax.p003a2.c.l(n(), C0740v.Q(abstractC5838l)) != null;
    }

    public boolean F(AbstractC5838l abstractC5838l) {
        return true;
    }

    public boolean G() {
        return false;
    }

    public ax.U1.a H(String str, int i) throws C0718i {
        ax.p045d2.b.g("Proxy file is not supported");
        throw new ax.I1.s("Proxy file is not supported");
    }

    public boolean I() {
        return false;
    }

    public boolean J() {
        return false;
    }

    public boolean K() {
        return true;
    }

    public void L(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.f();
        throw new C0718i("Not supported");
    }

    public void e(String str) {
        this.d.remove(str);
    }

    public void f() {
        this.c.clear();
    }

    public void g(InterfaceC5828b.a aVar) {
        e1(null, null, aVar);
    }

    protected void h() {
        this.f.lock();
    }

    protected void i() {
        this.f.unlock();
    }

    protected boolean j(AbstractC5838l abstractC5838l) {
        if (abstractC5838l.n()) {
            return false;
        }
        try {
            c1(abstractC5838l, C0744z.g(new byte[0]), abstractC5838l.s(), 0L, null, null, true, null, null);
            return true;
        } catch (C0710a | C0718i e) {
            e.printStackTrace();
            return false;
        }
    }

    abstract void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i;

    public void l(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p221pa.c.h().g().b("FILL FILE SIZE").h("Loation:" + abstractC5838l.P().H()).i();
        throw new C0718i("Not supported");
    }

    void m(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) {
        List<AbstractC5838l> listG1;
        Stack stack = new Stack();
        stack.push(abstractC5838l);
        while (!stack.isEmpty()) {
            AbstractC5838l abstractC5838l2 = (AbstractC5838l) stack.pop();
            if (cVar != null && cVar.isCancelled()) {
                return;
            }
            try {
                if (ax.p338y1.f.B0(abstractC5838l2.P(), abstractC5838l2)) {
                    listG1 = ax.G1.b.k().g(abstractC5838l2);
                    if (listG1 == null) {
                        listG1 = g1(abstractC5838l2);
                        ax.G1.b.k().m(abstractC5838l2, listG1);
                    }
                } else {
                    listG1 = g1(abstractC5838l2);
                }
                if (listG1 == null) {
                    return;
                }
                List<AbstractC5838l> listE = C0733n.e(listG1, C0733n.b("Search"));
                boolean zH = C0740v.H(abstractC5838l2);
                List<AbstractC5838l> listE2 = C0740v.e(listE, str, z, zH);
                for (AbstractC5838l abstractC5838l3 : C0740v.e(listE, null, z, zH)) {
                    if (abstractC5838l3.isDirectory()) {
                        stack.push(abstractC5838l3);
                    }
                }
                hVar.l0(listE2, stack.isEmpty());
            } catch (C0718i e) {
                e.printStackTrace();
            }
        }
    }

    public Context n() {
        return this.a;
    }

    public String o() {
        return this.b.e();
    }

    public ax.J1.A p(AbstractC5838l abstractC5838l) {
        return new d(abstractC5838l);
    }

    public int q() {
        return this.b.b();
    }

    public ax.p338y1.f r() {
        return this.b.d();
    }

    public ax.J1.F s() {
        return this.b;
    }

    protected InputStream t(String str, String str2) {
        InputStream inputStreamZ;
        if (!a() || this.c.contains(str2)) {
            return null;
        }
        String strK = V.k(str2);
        EnumC0738t enumC0738tE = C0739u.e(strK);
        try {
            if (enumC0738tE == EnumC0738t.IMAGE) {
                inputStreamZ = w(str2);
            } else {
                if (enumC0738tE != EnumC0738t.VIDEO) {
                    String strG = C0735p.g(strK, HttpUrl.FRAGMENT_ENCODE_SET);
                    if (!C0739u.E(strG) && !C0739u.O(strG)) {
                        ax.p045d2.b.f();
                    }
                } else if (ax.E1.P.k0()) {
                    inputStreamZ = z(str2);
                }
                inputStreamZ = null;
            }
            if (inputStreamZ == null) {
                this.c.add(str2);
            }
            return inputStreamZ;
        } catch (C0710a unused) {
            return null;
        }
    }

    public String u() {
        return this.b.e();
    }

    public b0 v() throws C0718i {
        return null;
    }
}
