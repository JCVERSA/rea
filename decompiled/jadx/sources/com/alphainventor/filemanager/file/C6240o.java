package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1962f;
import ax.p070e3.C1966j;
import ax.p086f3.C2017x;
import ax.p086f3.d0;
import ax.p086f3.i0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import com.alphainventor.filemanager.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6240o implements InterfaceC6228c {
    private static final Logger m = Logger.getLogger("FileManager.FileOperator");
    AbstractC6238m a;
    final String c;
    final Context d;
    final AtomicInteger b = new AtomicInteger(0);
    String e = HttpUrl.FRAGMENT_ENCODE_SET;
    String f = HttpUrl.FRAGMENT_ENCODE_SET;
    String g = HttpUrl.FRAGMENT_ENCODE_SET;
    String h = HttpUrl.FRAGMENT_ENCODE_SET;
    String i = HttpUrl.FRAGMENT_ENCODE_SET;
    String j = HttpUrl.FRAGMENT_ENCODE_SET;
    String k = HttpUrl.FRAGMENT_ENCODE_SET;
    String l = HttpUrl.FRAGMENT_ENCODE_SET;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$a */
    class a implements InterfaceC6228c.a {
        final /* synthetic */ CountDownLatch X;
        final /* synthetic */ AtomicBoolean q;

        a(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch) {
            this.q = atomicBoolean;
            this.X = countDownLatch;
        }

        @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
        public void J() {
        }

        @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
        public void d0(boolean z, Object obj) {
            this.q.set(z);
            this.X.countDown();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$b */
    public static class b extends ax.p406z3.q<Long, Long, Long> {
        C6240o h;

        public b(C6240o c6240o) {
            super(ax.z3.q.e.HIGH);
            this.h = c6240o;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            this.h.T();
            return null;
        }
    }

    public C6240o(Context context, AbstractC6238m abstractC6238m) {
        String string;
        this.d = context;
        this.a = abstractC6238m;
        try {
            string = context.getString(R.string.copy_postfix);
        } catch (IndexOutOfBoundsException unused) {
            string = " - Copy";
        }
        this.c = string;
    }

    private boolean I() {
        return ax.W2.h.m0(B());
    }

    private boolean J(AbstractC6239n abstractC6239n) {
        return ax.W2.h.m0(abstractC6239n.S());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void T() {
        if (this.b.get() > 0) {
            this.b.getAndDecrement();
            if (this.b.get() == 0) {
                this.a.b();
                ax.p038c3.b.k().d(B());
            }
        }
    }

    private void d(C6240o c6240o, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) throws C1957a, C1966j {
        boolean z;
        String strE = abstractC6239n.E();
        AbstractC6239n abstractC6239nN = ax.p086f3.B.n(c6240o, abstractC6239n);
        Uri uriO0 = null;
        try {
            if (abstractC6239n.p() == 0) {
                c6240o.q1(abstractC6239n);
            } else {
                if (abstractC6239nN.o()) {
                    c6240o.q1(abstractC6239nN);
                    abstractC6239nN = c6240o.a1(abstractC6239nN.E());
                }
                c6240o.i1(abstractC6239n, abstractC6239nN, null, null);
            }
        } catch (C1966j e) {
            e.printStackTrace();
        }
        AbstractC6239n abstractC6239nA1 = c6240o.a1(strE);
        if (abstractC6239nA1.o()) {
            ax.p302sb.c.h().g().d("MoveTempToDst 1").h(abstractC6239nA1.Q().H()).i();
            try {
                c6240o.q1(abstractC6239nA1);
                z = true;
            } catch (C1966j unused) {
                z = false;
            }
            AbstractC6239n abstractC6239nA2 = c6240o.a1(strE);
            if (abstractC6239nA2.o()) {
                if (abstractC6239nA2.Q() == ax.U2.f.u0 && ax.p006a3.Q.V1()) {
                    try {
                        uriO0 = ((C6249y) abstractC6239nA2).O0();
                    } catch (ax.p070e3.r unused2) {
                    }
                    ax.p302sb.c.h().g().d("MoveTempToDst 2-1").h(abstractC6239nA2.Q().H() + ":" + strE + ":" + abstractC6239nA2.p() + ":" + z + ":" + uriO0).i();
                } else {
                    ax.p302sb.c.h().g().d("MoveTempToDst 2-2").h(abstractC6239nA2.Q().H() + ":" + strE + ":" + z).i();
                }
                throw new C1966j("Could not delete and overwrite");
            }
            abstractC6239nA1 = abstractC6239nA2;
        }
        c6240o.i1(abstractC6239n2, abstractC6239nA1, null, null);
    }

    private void l(C6240o c6240o, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) throws C1957a, C1966j {
        boolean z;
        String strE = abstractC6239n.E();
        c6240o.q1(abstractC6239n);
        AbstractC6239n abstractC6239nA1 = c6240o.a1(strE);
        Uri uriO0 = null;
        if (abstractC6239nA1.o()) {
            ax.p302sb.c.h().g().d("MoveTempToDst 1").h(abstractC6239nA1.Q().H()).i();
            try {
                c6240o.q1(abstractC6239nA1);
                z = true;
            } catch (C1966j unused) {
                z = false;
            }
            AbstractC6239n abstractC6239nA2 = c6240o.a1(strE);
            if (abstractC6239nA2.o()) {
                if (abstractC6239nA2.Q() == ax.U2.f.u0 && ax.p006a3.Q.V1()) {
                    try {
                        uriO0 = ((C6249y) abstractC6239nA2).O0();
                    } catch (ax.p070e3.r unused2) {
                    }
                    ax.p302sb.c.h().g().d("MoveTempToDst 2-1").h(abstractC6239nA2.Q().H() + ":" + strE + ":" + abstractC6239nA2.p() + ":" + z + ":" + uriO0).i();
                } else {
                    ax.p302sb.c.h().g().d("MoveTempToDst 2-2").h(abstractC6239nA2.Q().H() + ":" + strE + ":" + z).i();
                }
                throw new C1966j("Could not delete and overwrite");
            }
            abstractC6239nA1 = abstractC6239nA2;
        }
        c6240o.i1(abstractC6239n2, abstractC6239nA1, null, null);
    }

    public ax.U2.f A() {
        return this.a.u();
    }

    public ax.p086f3.K B() {
        return this.a.v();
    }

    public synchronized int C() {
        return this.b.get();
    }

    public String D() {
        return this.a.r();
    }

    public k0 E() throws C1966j {
        return this.a.y();
    }

    public AbstractC6239n F(AbstractC6239n abstractC6239n, boolean z) throws C1966j {
        int iLastIndexOf;
        C2461b.c(A() == abstractC6239n.Q());
        if (!abstractC6239n.o()) {
            return abstractC6239n;
        }
        String strB = abstractC6239n.B();
        String strU = abstractC6239n.U();
        boolean zIsDirectory = abstractC6239n.isDirectory();
        String strSubstring = HttpUrl.FRAGMENT_ENCODE_SET;
        if (!zIsDirectory && (iLastIndexOf = strB.lastIndexOf(46)) > 0) {
            String strSubstring2 = strB.substring(0, iLastIndexOf);
            strSubstring = strB.substring(iLastIndexOf);
            strB = strSubstring2;
        }
        int i = 2;
        if (z) {
            String strQ = d0.Q(strU, strB + this.c);
            AbstractC6239n abstractC6239nA1 = a1(strQ + strSubstring);
            while (abstractC6239nA1.o()) {
                abstractC6239nA1 = a1(strQ + " (" + i + ")" + strSubstring);
                i++;
            }
            return abstractC6239nA1;
        }
        String strQ2 = d0.Q(strU, strB);
        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append(strQ2);
            sb.append(" (");
            int i2 = i + 1;
            sb.append(i);
            sb.append(")");
            sb.append(strSubstring);
            AbstractC6239n abstractC6239nA2 = a1(sb.toString());
            if (!abstractC6239nA2.o()) {
                return abstractC6239nA2;
            }
            i = i2;
        }
    }

    public boolean G() {
        if (u() instanceof C6248x) {
            return ax.p006a3.Q.a0() ? ((C6248x) u()).P0() : ((C6248x) u()).O0();
        }
        return false;
    }

    public boolean H() {
        return this.a.D();
    }

    public boolean K(AbstractC6239n abstractC6239n) {
        if (c0(abstractC6239n)) {
            return ax.p038c3.b.k().f(abstractC6239n);
        }
        return false;
    }

    public boolean L() {
        return this.a.G();
    }

    public boolean M(AbstractC6239n abstractC6239n) {
        return this.a.I(abstractC6239n);
    }

    public List<AbstractC6239n> N(AbstractC6239n abstractC6239n) throws C1966j {
        return d1(abstractC6239n, AbstractC6238m.f.NONE);
    }

    public List<AbstractC6239n> O(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar, boolean z, boolean z2) throws C1966j {
        List<String> listI1;
        if (K(abstractC6239n)) {
            List<AbstractC6239n> listG = ax.p038c3.b.k().g(abstractC6239n);
            return (z && listG != null && ax.U2.f.e0(A())) ? ((C6245u) this.a).h2(abstractC6239n, listG, true) : listG;
        }
        List<AbstractC6239n> listD1 = d1(abstractC6239n, fVar);
        if (z2 && listD1 != null && (u() instanceof C6248x)) {
            AbstractC6239n abstractC6239nR0 = C6248x.r0(listD1);
            if (abstractC6239nR0 != null && !abstractC6239nR0.isDirectory() && (listI1 = ((C6248x) u()).I1(abstractC6239nR0)) != null) {
                for (String str : listI1) {
                    for (AbstractC6239n abstractC6239n2 : listD1) {
                        if (str.equals(abstractC6239n2.B())) {
                            ((C6249y) abstractC6239n2).m1(C6249y.b.HIDDEN_DOTHIDDEN);
                        }
                    }
                }
            }
            for (AbstractC6239n abstractC6239n3 : listD1) {
                String strF = ax.p038c3.g.h().f(abstractC6239n3);
                if (strF != null) {
                    ((C6249y) abstractC6239n3).m1(ax.p038c3.g.c(strF));
                }
            }
        }
        return listD1;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x011c  */
    /* JADX WARN: Code duplicated, block: B:74:0x011f A[Catch: j -> 0x0071, a -> 0x0074, TRY_LEAVE, TryCatch #11 {a -> 0x0074, j -> 0x0071, blocks: (B:18:0x004b, B:20:0x0055, B:22:0x005b, B:63:0x0104, B:65:0x010a, B:74:0x011f, B:66:0x010e, B:67:0x0115, B:27:0x0069, B:32:0x0077, B:33:0x007c), top: B:120:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:88:0x0136  */
    /* JADX WARN: Code duplicated, block: B:92:0x0148 A[Catch: j -> 0x014b, TRY_LEAVE, TryCatch #6 {j -> 0x014b, blocks: (B:90:0x013a, B:92:0x0148), top: B:107:0x013a }] */
    /* JADX WARN: Code duplicated, block: B:97:0x015c A[Catch: j -> 0x015f, TRY_LEAVE, TryCatch #1 {j -> 0x015f, blocks: (B:95:0x014e, B:97:0x015c), top: B:103:0x014e }] */
    public void P(AbstractC6239n abstractC6239n, C6240o c6240o, AbstractC6239n abstractC6239n2, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        AbstractC6239n abstractC6239nZ;
        boolean z2;
        boolean z3;
        AbstractC6239n abstractC6239n3;
        C6240o c6240o2 = c6240o;
        boolean z4 = true;
        C2461b.c(A() == abstractC6239n.Q());
        C2461b.a(abstractC6239n.isDirectory());
        if (!abstractC6239n.o()) {
            ax.p302sb.c.h().g().d("MVEX").h(A().H()).i();
            throw new ax.p070e3.t("Move source file not found");
        }
        AbstractC6239n abstractC6239nA1 = c6240o2.a1(abstractC6239n2.U());
        if (!d0.E(abstractC6239nA1) && !abstractC6239nA1.o() && !c6240o2.k(abstractC6239nA1, false)) {
            throw new C1966j("Create target folder failed");
        }
        AbstractC6239n abstractC6239n4 = null;
        try {
            boolean zE = e(abstractC6239n, abstractC6239n2);
            if (!abstractC6239n2.o()) {
                abstractC6239nZ = abstractC6239n2;
                z2 = false;
                z3 = false;
            } else {
                if (abstractC6239n2.isDirectory()) {
                    throw new ax.p070e3.u();
                }
                if (!c6240o2.W() || zE) {
                    abstractC6239nZ = ax.p086f3.B.z(c6240o, abstractC6239n2);
                    z2 = false;
                } else {
                    abstractC6239nZ = null;
                    z2 = true;
                }
                z3 = true;
            }
            try {
                if (zE) {
                    try {
                        i1(abstractC6239n, abstractC6239nZ, interfaceC2462c, iVar);
                        abstractC6239n3 = abstractC6239nZ;
                        z4 = false;
                        if (!z3 && !z2) {
                            AbstractC6239n abstractC6239nA2 = c6240o2.a1(abstractC6239n3.E());
                            if (!abstractC6239nA2.o()) {
                                throw new ax.p070e3.t("tmp file not exists");
                            }
                            l(c6240o2, abstractC6239n2, abstractC6239nA2);
                        }
                        if (z4) {
                            q1(abstractC6239n);
                            return;
                        }
                        return;
                    } catch (C1966j e) {
                        e = e;
                        abstractC6239n4 = abstractC6239nZ;
                        e.printStackTrace();
                        if (abstractC6239n4 != null) {
                            try {
                                if (a1(abstractC6239n.E()).o()) {
                                    c6240o2.q1(abstractC6239n4);
                                }
                            } catch (C1966j unused) {
                            }
                        }
                        throw e;
                    }
                }
                try {
                    if (abstractC6239n.d0()) {
                        try {
                            r(abstractC6239n);
                        } catch (C1966j unused2) {
                        }
                    }
                    if (z2) {
                        try {
                            c6240o2.a0(abstractC6239n2, x(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), z, interfaceC2462c, iVar);
                            c6240o2 = c6240o;
                            abstractC6239n3 = abstractC6239nZ;
                        } catch (C1957a e2) {
                            e = e2;
                            c6240o2 = c6240o;
                            abstractC6239n4 = abstractC6239nZ;
                            if (abstractC6239n4 != null) {
                                try {
                                    if (a1(abstractC6239n.E()).o()) {
                                        c6240o2.q1(abstractC6239n4);
                                    }
                                } catch (C1966j unused3) {
                                }
                            }
                            throw e;
                        } catch (C1966j e3) {
                            e = e3;
                            c6240o2 = c6240o;
                            abstractC6239n4 = abstractC6239nZ;
                            e.printStackTrace();
                            if (abstractC6239n4 != null) {
                                if (a1(abstractC6239n.E()).o()) {
                                    c6240o2.q1(abstractC6239n4);
                                }
                            }
                            throw e;
                        }
                    } else {
                        try {
                            c6240o2 = c6240o;
                            abstractC6239n3 = abstractC6239nZ;
                            try {
                                c6240o2.l1(abstractC6239n3, x(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), z, interfaceC2462c, iVar);
                            } catch (C1957a e4) {
                                e = e4;
                                abstractC6239n4 = abstractC6239n3;
                                if (abstractC6239n4 != null) {
                                    if (a1(abstractC6239n.E()).o()) {
                                        c6240o2.q1(abstractC6239n4);
                                    }
                                }
                                throw e;
                            } catch (C1966j e5) {
                                e = e5;
                                abstractC6239n4 = abstractC6239n3;
                                e.printStackTrace();
                                if (abstractC6239n4 != null) {
                                    if (a1(abstractC6239n.E()).o()) {
                                        c6240o2.q1(abstractC6239n4);
                                    }
                                }
                                throw e;
                            }
                        } catch (C1957a e6) {
                            e = e6;
                            c6240o2 = c6240o;
                            abstractC6239n3 = abstractC6239nZ;
                            abstractC6239n4 = abstractC6239n3;
                            if (abstractC6239n4 != null) {
                                if (a1(abstractC6239n.E()).o()) {
                                    c6240o2.q1(abstractC6239n4);
                                }
                            }
                            throw e;
                        } catch (C1966j e7) {
                            e = e7;
                            c6240o2 = c6240o;
                            abstractC6239n3 = abstractC6239nZ;
                            abstractC6239n4 = abstractC6239n3;
                            e.printStackTrace();
                            if (abstractC6239n4 != null) {
                                if (a1(abstractC6239n.E()).o()) {
                                    c6240o2.q1(abstractC6239n4);
                                }
                            }
                            throw e;
                        }
                    }
                    if (!z3) {
                    }
                    if (z4) {
                        q1(abstractC6239n);
                        return;
                    }
                    return;
                } catch (C1957a e8) {
                    e = e8;
                } catch (C1966j e9) {
                    e = e9;
                }
            } catch (C1957a e10) {
                e = e10;
            }
        } catch (C1957a e11) {
            e = e11;
        } catch (C1966j e12) {
            e = e12;
        }
        e.printStackTrace();
        if (abstractC6239n4 != null && !(e instanceof C1962f)) {
            if (a1(abstractC6239n.E()).o()) {
                c6240o2.q1(abstractC6239n4);
            }
        }
        throw e;
    }

    public boolean Q() {
        return this.a.K();
    }

    public ax.p262q3.a R(String str, int i) throws C1966j {
        return this.a.M(str, i);
    }

    public synchronized void S(boolean z) {
        try {
            if (this.b.get() == 0) {
                m.severe("OPEROATOR RELEASED MORE THAN RETAINED!!!!!");
                ax.p302sb.c.h().d("REL").k().h(A().H() + ":" + z()).i();
            } else {
                this.l = this.k;
                this.k = this.j;
                this.j = this.i;
                this.i = "(" + this.b.get() + ") " + ax.U2.a.i();
            }
            if (k1()) {
                new b(this).i(new Long[0]);
            } else {
                T();
                if (z && (u() instanceof C6248x)) {
                    C6248x c6248x = (C6248x) u();
                    if (c6248x.P0()) {
                        ax.p006a3.Q.a0();
                        c6248x.o0();
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void U(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        if (abstractC6239n2.o()) {
            throw new C1962f(abstractC6239n.isDirectory() != abstractC6239n2.isDirectory());
        }
        i1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
    }

    public synchronized void V() {
        this.b.getAndIncrement();
        this.h = this.g;
        this.g = this.f;
        this.f = this.e;
        this.e = "(" + this.b.get() + ") " + ax.U2.a.i();
    }

    public boolean W() {
        return this.a.O();
    }

    public boolean X() {
        return this.a.P();
    }

    public boolean Y() {
        return this.a.Q();
    }

    public boolean Z() {
        return this.a instanceof i0;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.a.a();
    }

    public void a0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        ax.p038c3.b.k().p(B(), abstractC6239n.U());
        ax.p038c3.b.k().e(abstractC6239n);
        this.a.R(abstractC6239n, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
        if (J(abstractC6239n)) {
            ax.W2.h.S(abstractC6239n).b(abstractC6239n);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        if (d0.B(str)) {
            return this.a.a1(str);
        }
        ax.p302sb.c.h().g().b("GFI!!!").k().h(A().H() + ":" + str).i();
        throw new C1966j("Not normalzied path in getFileInfo param");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        this.a.b();
    }

    /* JADX WARN: Code duplicated, block: B:75:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:? A[SYNTHETIC] */
    public void b0(AbstractC6239n abstractC6239n, C6240o c6240o, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C6240o c6240o2;
        C1957a c1957a;
        AbstractC6239n abstractC6239nZ;
        boolean z;
        AbstractC6239n abstractC6239nZ2;
        AbstractC6239n abstractC6239nA1 = null;
        try {
            boolean z2 = false;
            if (abstractC6239n2.o()) {
                try {
                    if (c6240o.W() || c6240o.X()) {
                        abstractC6239nZ = null;
                        z = true;
                        z2 = true;
                    } else {
                        abstractC6239nZ = ax.p086f3.B.z(c6240o, abstractC6239n2);
                        z = true;
                    }
                } catch (C1957a e) {
                    c1957a = e;
                    c6240o2 = c6240o;
                }
            } else {
                abstractC6239nZ = abstractC6239n2;
                z = false;
            }
            if (z2) {
                try {
                    c6240o.W();
                    try {
                        try {
                            c6240o.a0(abstractC6239n2, x(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), true, interfaceC2462c, iVar);
                            return;
                        } catch (ax.p070e3.z e2) {
                            e = e2;
                            abstractC6239nZ2 = ax.p086f3.B.z(c6240o, abstractC6239n2);
                            try {
                                c6240o.l1(abstractC6239nZ2, x(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), true, interfaceC2462c, iVar);
                                throw e;
                            } catch (C1957a e3) {
                                c1957a = e3;
                                c6240o2 = c6240o;
                            }
                        }
                    } catch (ax.p070e3.z e4) {
                        e = e4;
                    }
                } catch (C1957a e5) {
                    c1957a = e5;
                    c6240o2 = c6240o;
                    abstractC6239nA1 = abstractC6239nZ;
                }
            } else {
                try {
                    abstractC6239nZ2 = abstractC6239nZ;
                    try {
                        c6240o.l1(abstractC6239nZ2, x(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), true, interfaceC2462c, iVar);
                        c6240o2 = c6240o;
                        if (z) {
                            try {
                                abstractC6239nA1 = c6240o2.a1(abstractC6239nZ2.E());
                                try {
                                    if (!abstractC6239nA1.o()) {
                                        throw new ax.p070e3.t("tmp file not exists");
                                    }
                                    if (abstractC6239n.p() == 0) {
                                        d(c6240o2, abstractC6239n2, abstractC6239nA1);
                                        return;
                                    } else {
                                        l(c6240o2, abstractC6239n2, abstractC6239nA1);
                                        return;
                                    }
                                } catch (C1957a e6) {
                                    e = e6;
                                    c1957a = e;
                                    if (abstractC6239nA1 == null) {
                                        throw c1957a;
                                    }
                                    try {
                                        c6240o2.q1(abstractC6239nA1);
                                        throw c1957a;
                                    } catch (C1966j unused) {
                                        throw c1957a;
                                    }
                                }
                            } catch (C1957a e7) {
                                e = e7;
                                c1957a = e;
                                abstractC6239nA1 = abstractC6239nZ2;
                                if (abstractC6239nA1 == null) {
                                    throw c1957a;
                                }
                                c6240o2.q1(abstractC6239nA1);
                                throw c1957a;
                            }
                        }
                        return;
                    } catch (C1957a e8) {
                        e = e8;
                        c6240o2 = c6240o;
                    }
                } catch (C1957a e9) {
                    e = e9;
                    c6240o2 = c6240o;
                    abstractC6239nZ2 = abstractC6239nZ;
                }
            }
            abstractC6239nA1 = abstractC6239nZ2;
        } catch (C1957a e10) {
            e = e10;
            c6240o2 = c6240o;
        }
        if (abstractC6239nA1 == null) {
            throw c1957a;
        }
        c6240o2.q1(abstractC6239nA1);
        throw c1957a;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) throws IOException {
        return this.a.b1(str, str2, str3);
    }

    public boolean c0(AbstractC6239n abstractC6239n) {
        return ax.U2.f.D0(A(), abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return this.a.c1(abstractC6239n);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0023 A[Catch: j -> 0x0027, a -> 0x002a, TRY_ENTER, TryCatch #4 {a -> 0x002a, j -> 0x0027, blocks: (B:17:0x0023, B:23:0x002c, B:25:0x0040), top: B:40:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:23:0x002c A[Catch: j -> 0x0027, a -> 0x002a, TryCatch #4 {a -> 0x002a, j -> 0x0027, blocks: (B:17:0x0023, B:23:0x002c, B:25:0x0040), top: B:40:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0040 A[Catch: j -> 0x0027, a -> 0x002a, TRY_LEAVE, TryCatch #4 {a -> 0x002a, j -> 0x0027, blocks: (B:17:0x0023, B:23:0x002c, B:25:0x0040), top: B:40:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x004e A[Catch: j -> 0x001a, a -> 0x001c, TryCatch #5 {a -> 0x001c, j -> 0x001a, blocks: (B:3:0x0001, B:5:0x0008, B:26:0x0048, B:28:0x004e, B:30:0x0052, B:31:0x0059, B:8:0x0012), top: B:39:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0052 A[Catch: j -> 0x001a, a -> 0x001c, TryCatch #5 {a -> 0x001c, j -> 0x001a, blocks: (B:3:0x0001, B:5:0x0008, B:26:0x0048, B:28:0x004e, B:30:0x0052, B:31:0x0059, B:8:0x0012), top: B:39:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x005a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:37:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void d0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        AbstractC6239n abstractC6239nZ;
        boolean z2;
        boolean z3;
        AbstractC6239n abstractC6239nA1;
        AbstractC6239n abstractC6239n2 = null;
        try {
            try {
                if (abstractC6239n.o()) {
                    z3 = true;
                    if (W()) {
                        abstractC6239nZ = null;
                        z2 = true;
                    } else {
                        abstractC6239nZ = ax.p086f3.B.z(this, abstractC6239n);
                        z2 = true;
                    }
                    if (z3) {
                        a0(abstractC6239n, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
                        return;
                    }
                    l1(abstractC6239nZ, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
                    if (z2) {
                        abstractC6239nA1 = a1(abstractC6239nZ.E());
                        if (abstractC6239nA1.o()) {
                            throw new ax.p070e3.t("tmp file not exists");
                        }
                        l(this, abstractC6239n, abstractC6239nA1);
                        return;
                    }
                    return;
                }
                abstractC6239nZ = abstractC6239n;
                z2 = false;
                if (z3) {
                    a0(abstractC6239n, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
                    return;
                }
                l1(abstractC6239nZ, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
                if (z2) {
                    abstractC6239nA1 = a1(abstractC6239nZ.E());
                    if (abstractC6239nA1.o()) {
                        throw new ax.p070e3.t("tmp file not exists");
                    }
                    l(this, abstractC6239n, abstractC6239nA1);
                    return;
                }
                return;
            } catch (C1957a e) {
                e = e;
                abstractC6239n2 = abstractC6239nZ;
                if (abstractC6239n2 != null) {
                    try {
                        q1(abstractC6239n2);
                    } catch (C1966j unused) {
                    }
                }
                throw e;
            } catch (C1966j e2) {
                e = e2;
                abstractC6239n2 = abstractC6239nZ;
                if (abstractC6239n2 != null) {
                    q1(abstractC6239n2);
                }
                throw e;
            }
            z3 = false;
        } catch (C1957a e3) {
            e = e3;
        } catch (C1966j e4) {
            e = e4;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (abstractC6239n.isDirectory()) {
            List<AbstractC6239n> listD1 = this.a.d1(abstractC6239n, fVar);
            if (c0(abstractC6239n)) {
                ax.p038c3.b.k().m(abstractC6239n, listD1);
            }
            return listD1;
        }
        ax.p302sb.c.h().g().d("FOLICH!!!").k().h(A().H() + ":" + abstractC6239n.o()).i();
        throw new C1966j("list children : fileinfo is not directory");
    }

    public boolean e(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        if ((ax.p086f3.B.P(abstractC6239n) && ax.p086f3.B.P(abstractC6239n2)) || ax.p086f3.B.N(abstractC6239n, abstractC6239n2)) {
            return r1(abstractC6239n, abstractC6239n2);
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return this.a.e1(str, str2);
    }

    public void f(String str) {
        String path = Uri.parse(str).getPath();
        if (path == null) {
            return;
        }
        this.a.f(path);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        return this.a.f1(abstractC6239n);
    }

    public void g() {
        this.a.g();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        long jX = J(abstractC6239n) ? ax.W2.h.S(abstractC6239n).X(abstractC6239n) : 0L;
        try {
            this.a.g1(abstractC6239n);
            ax.p038c3.b.k().s(B(), abstractC6239n.E());
            ax.p038c3.b.k().p(B(), abstractC6239n.U());
            ax.p038c3.b.k().e(abstractC6239n);
            if (J(abstractC6239n)) {
                ax.W2.h.S(abstractC6239n).r(abstractC6239n, jX);
            }
        } catch (Throwable th) {
            ax.p038c3.b.k().s(B(), abstractC6239n.E());
            ax.p038c3.b.k().p(B(), abstractC6239n.U());
            ax.p038c3.b.k().e(abstractC6239n);
            throw th;
        }
    }

    public void h(InterfaceC6228c.a aVar) {
        this.a.h(aVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        return this.a.h1(abstractC6239n, j);
    }

    public boolean i(long j) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        h(new a(atomicBoolean, countDownLatch));
        if (j == 0) {
            j = 20000;
        }
        try {
            countDownLatch.await(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        return atomicBoolean.get();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.c(A() == abstractC6239n.Q());
        if (!ax.p086f3.B.P(abstractC6239n)) {
            C2461b.c(abstractC6239n.Q() == abstractC6239n2.Q());
        }
        if (!abstractC6239n.o()) {
            ax.p302sb.c.h().g().d("MV2").k().h(A().H()).i();
            throw new ax.p070e3.t("Source file not exists");
        }
        if (abstractC6239n2.o()) {
            ax.p302sb.c.h().g().d("MV1").k().h(A().H()).i();
            throw new C1962f(abstractC6239n.isDirectory() != abstractC6239n2.isDirectory());
        }
        long jX = J(abstractC6239n) ? ax.W2.h.S(abstractC6239n).X(abstractC6239n) : 0L;
        try {
            this.a.i1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
            if (J(abstractC6239n)) {
                ax.W2.h.S(abstractC6239n).r(abstractC6239n, jX);
            }
            if (J(abstractC6239n2)) {
                try {
                    AbstractC6239n abstractC6239nA1 = abstractC6239n.S() != abstractC6239n2.S() ? C2017x.e(abstractC6239n2.S()).a1(abstractC6239n2.E()) : a1(abstractC6239n2.E());
                    ax.W2.h.S(abstractC6239nA1).b(abstractC6239nA1);
                } catch (C1966j unused) {
                }
            }
        } finally {
            ax.p038c3.b.k().t(abstractC6239n);
            ax.p038c3.b.k().p(abstractC6239n.S(), abstractC6239n.U());
            ax.p038c3.b.k().p(abstractC6239n2.S(), abstractC6239n2.U());
            ax.p038c3.b.k().e(abstractC6239n);
            ax.p038c3.b.k().e(abstractC6239n2);
        }
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 2891. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public void j(com.alphainventor.filemanager.file.AbstractC6239n r17, com.alphainventor.filemanager.file.C6240o r18, com.alphainventor.filemanager.file.AbstractC6239n r19, boolean r20, boolean r21, boolean r22, ax.p406z3.InterfaceC2462c r23, ax.p182l3.i r24) throws ax.p070e3.C1957a, ax.p070e3.C1966j {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alphainventor.filemanager.file.C6240o.j(com.alphainventor.filemanager.file.n, com.alphainventor.filemanager.file.o, com.alphainventor.filemanager.file.n, boolean, boolean, boolean, ax.z3.c, ax.l3.i):void");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        this.a.j1(activity, fragment, aVar);
    }

    public boolean k(AbstractC6239n abstractC6239n, boolean z) {
        try {
            if (z) {
                if (k(abstractC6239n, false)) {
                    return true;
                }
                String strU = abstractC6239n.U();
                if (d0.a.equals(strU)) {
                    return false;
                }
                AbstractC6239n abstractC6239nA1 = a1(strU);
                return !abstractC6239nA1.o() && k(abstractC6239nA1, true) && k(abstractC6239n, false);
            }
            boolean zN1 = this.a.n1(abstractC6239n);
            if (zN1) {
                ax.p038c3.b.k().p(B(), abstractC6239n.U());
                ax.p038c3.b.k().e(abstractC6239n);
                if (I()) {
                    ax.W2.h.R(B()).b(a1(abstractC6239n.E()));
                }
            }
            return zN1;
        } catch (C1966j unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return this.a.k1();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        ax.p038c3.b.k().p(B(), abstractC6239n.U());
        ax.p038c3.b.k().e(abstractC6239n);
        this.a.l1(abstractC6239n, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
        if (J(abstractC6239n)) {
            ax.W2.h.S(abstractC6239n).b(abstractC6239n);
        }
    }

    public void m(AbstractC6239n abstractC6239n) throws C1966j {
        if (p1()) {
            g1(abstractC6239n);
            return;
        }
        if (!abstractC6239n.isDirectory()) {
            q1(abstractC6239n);
            return;
        }
        Iterator<AbstractC6239n> it = N(abstractC6239n).iterator();
        while (it.hasNext()) {
            m(it.next());
        }
        q1(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n.isDirectory());
        if (abstractC6239n2.o()) {
            ax.p302sb.c.h().d("CP1").k().h(A().H()).i();
            throw new C1966j("Target is aleady exist");
        }
        if (!abstractC6239n.o()) {
            ax.p302sb.c.h().g().d("CP2").k().h(A().H()).i();
            throw new ax.p070e3.t("Source is not exist");
        }
        ax.p038c3.b.k().p(abstractC6239n2.S(), abstractC6239n2.U());
        ax.p038c3.b.k().e(abstractC6239n2);
        this.a.m1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
        if (J(abstractC6239n2)) {
            ax.W2.h.S(abstractC6239n2).b(abstractC6239n2);
        }
    }

    public void n(ax.p182l3.i iVar) {
        if (u() instanceof C6248x) {
            ((C6248x) u()).m0(iVar);
        }
    }

    public void o(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        this.a.m(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        boolean zO1 = this.a.o1(abstractC6239n);
        if (zO1) {
            ax.p038c3.b.k().p(B(), abstractC6239n.U());
            ax.p038c3.b.k().e(abstractC6239n);
        }
        return zO1;
    }

    public void p(AbstractC6239n abstractC6239n, File file, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C6240o c6240oF = C2017x.f(file);
        AbstractC6239n abstractC6239nA1 = c6240oF.a1(file.getAbsolutePath());
        if (abstractC6239nA1.o()) {
            c6240oF.q1(abstractC6239nA1);
            abstractC6239nA1 = c6240oF.a1(file.getAbsolutePath());
        }
        j(abstractC6239n, c6240oF, abstractC6239nA1, System.currentTimeMillis() <= abstractC6239n.q(), false, false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return this.a.p1();
    }

    public void q(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        List<AbstractC6239n> listG = ax.p038c3.b.k().g(abstractC6239n);
        if (listG == null) {
            listG = d1(abstractC6239n, fVar);
        }
        abstractC6239n.e0(listG.size());
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        long jP = (!J(abstractC6239n) || abstractC6239n.isDirectory()) ? 0L : abstractC6239n.p();
        try {
            this.a.q1(abstractC6239n);
            ax.p038c3.b.k().s(B(), abstractC6239n.E());
            ax.p038c3.b.k().p(B(), abstractC6239n.U());
            ax.p038c3.b.k().e(abstractC6239n);
            if (J(abstractC6239n)) {
                ax.W2.h.S(abstractC6239n).r(abstractC6239n, jP);
            }
        } catch (Throwable th) {
            ax.p038c3.b.k().s(B(), abstractC6239n.E());
            ax.p038c3.b.k().p(B(), abstractC6239n.U());
            ax.p038c3.b.k().e(abstractC6239n);
            throw th;
        }
    }

    public void r(AbstractC6239n abstractC6239n) throws C1966j {
        this.a.n(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return this.a.r1(abstractC6239n, abstractC6239n2);
    }

    public void s(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        abstractC6239n.e0(d1(abstractC6239n, fVar).size());
    }

    public Context t() {
        return this.d;
    }

    public AbstractC6238m u() {
        return this.a;
    }

    public long v() throws C1966j {
        return this.a.q();
    }

    public String w() {
        return this.a.r();
    }

    public ax.p086f3.G x(AbstractC6239n abstractC6239n) {
        return this.a.s(abstractC6239n);
    }

    public int y() {
        return this.a.t();
    }

    public String z() {
        return this.i + ":" + this.j + ":" + this.k + ":" + this.l + " - " + this.e + ":" + this.f + ":" + this.g + ":" + this.h;
    }
}
