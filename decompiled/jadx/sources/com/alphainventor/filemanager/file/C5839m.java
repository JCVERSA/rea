package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0714e;
import ax.I1.C0718i;
import ax.J1.C0740v;
import ax.J1.V;
import ax.J1.b0;
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

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5839m implements InterfaceC5828b {
    private static final Logger m = Logger.getLogger("FileManager.FileOperator");
    AbstractC5837k a;
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

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$a */
    class a implements InterfaceC5828b.a {
        final /* synthetic */ CountDownLatch X;
        final /* synthetic */ AtomicBoolean q;

        a(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch) {
            this.q = atomicBoolean;
            this.X = countDownLatch;
        }

        @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
        public void N() {
        }

        @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
        public void f0(boolean z, Object obj) {
            this.q.set(z);
            this.X.countDown();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.m$b */
    public static class b extends ax.p045d2.n<Long, Long, Long> {
        C5839m h;

        public b(C5839m c5839m) {
            super(ax.d2.n.f.HIGH);
            this.h = c5839m;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            this.h.Q();
            return null;
        }
    }

    public C5839m(Context context, AbstractC5837k abstractC5837k) {
        String string;
        this.d = context;
        this.a = abstractC5837k;
        try {
            string = context.getString(R.string.copy_postfix);
        } catch (IndexOutOfBoundsException unused) {
            string = " - Copy";
        }
        this.c = string;
    }

    private boolean G() {
        return ax.A1.h.i0(z());
    }

    private boolean H(AbstractC5838l abstractC5838l) {
        return ax.A1.h.i0(abstractC5838l.R());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void Q() {
        if (this.b.get() > 0) {
            this.b.getAndDecrement();
            if (this.b.get() == 0) {
                this.a.b();
                ax.G1.b.k().d(z());
            }
        }
    }

    private void k(C5839m c5839m, AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) throws C0718i, C0710a {
        boolean z;
        String strC = abstractC5838l.C();
        c5839m.l1(abstractC5838l);
        AbstractC5838l abstractC5838lV0 = c5839m.V0(strC);
        Uri uriD0 = null;
        if (abstractC5838lV0.n()) {
            ax.p221pa.c.h().g().d("MoveTempToDst 1").h(abstractC5838lV0.P().H()).i();
            try {
                c5839m.l1(abstractC5838lV0);
                z = true;
            } catch (C0718i unused) {
                z = false;
            }
            AbstractC5838l abstractC5838lV1 = c5839m.V0(strC);
            if (abstractC5838lV1.n()) {
                if (abstractC5838lV1.P() == ax.p338y1.f.r0 && ax.E1.P.F1()) {
                    try {
                        uriD0 = ((u) abstractC5838lV1).D0();
                    } catch (ax.I1.q unused2) {
                    }
                    ax.p221pa.c.h().g().d("MoveTempToDst 2-1").h(abstractC5838lV1.P().H() + ":" + strC + ":" + abstractC5838lV1.o() + ":" + z + ":" + uriD0).i();
                } else {
                    ax.p221pa.c.h().g().d("MoveTempToDst 2-2").h(abstractC5838lV1.P().H() + ":" + strC + ":" + z).i();
                }
                throw new C0718i("Could not delete and overwrite");
            }
            abstractC5838lV0 = abstractC5838lV1;
        }
        c5839m.k1(abstractC5838l2, abstractC5838lV0, null, null);
    }

    public synchronized int A() {
        return this.b.get();
    }

    public String B() {
        return this.a.o();
    }

    public b0 C() throws C0718i {
        return this.a.v();
    }

    public AbstractC5838l D(AbstractC5838l abstractC5838l, boolean z) throws C0718i {
        int iLastIndexOf;
        ax.p045d2.b.c(y() == abstractC5838l.P());
        if (abstractC5838l.n()) {
            String strW = abstractC5838l.w();
            String strS = abstractC5838l.S();
            boolean zIsDirectory = abstractC5838l.isDirectory();
            String strSubstring = HttpUrl.FRAGMENT_ENCODE_SET;
            if (!zIsDirectory && (iLastIndexOf = strW.lastIndexOf(46)) > 0) {
                String strSubstring2 = strW.substring(0, iLastIndexOf);
                strSubstring = strW.substring(iLastIndexOf);
                strW = strSubstring2;
            }
            int i = 2;
            if (z) {
                String strM = V.M(strS, strW + this.c);
                abstractC5838l = V0(strM + strSubstring);
                int i2 = 2;
                while (abstractC5838l.n()) {
                    abstractC5838l = V0(strM + " (" + i2 + ")" + strSubstring);
                    i2++;
                }
            } else {
                String strM2 = V.M(strS, strW);
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(strM2);
                    sb.append(" (");
                    int i3 = i + 1;
                    sb.append(i);
                    sb.append(")");
                    sb.append(strSubstring);
                    abstractC5838l = V0(sb.toString());
                    if (!abstractC5838l.n()) {
                        break;
                    }
                    i = i3;
                }
            }
        }
        return abstractC5838l;
    }

    public boolean E() {
        if (t() instanceof C5845t) {
            return ax.E1.P.V() ? ((C5845t) t()).G0() : ((C5845t) t()).F0();
        }
        return false;
    }

    public boolean F() {
        return this.a.A();
    }

    public boolean I(AbstractC5838l abstractC5838l) {
        if (Y(abstractC5838l)) {
            return ax.G1.b.k().f(abstractC5838l);
        }
        return false;
    }

    public boolean J() {
        return this.a.D();
    }

    public boolean K(AbstractC5838l abstractC5838l) {
        return this.a.F(abstractC5838l);
    }

    public List<AbstractC5838l> L(AbstractC5838l abstractC5838l, boolean z, boolean z2) throws C0718i {
        List<AbstractC5838l> listG1;
        List<String> listZ1;
        if (I(abstractC5838l)) {
            listG1 = ax.G1.b.k().g(abstractC5838l);
            if (z && listG1 != null && ax.p338y1.f.d0(y())) {
                Iterator<AbstractC5838l> it = listG1.iterator();
                while (it.hasNext()) {
                    r rVar = (r) it.next();
                    if (rVar.m0()) {
                        ((C5843q) t()).U1(rVar);
                    }
                }
            }
        } else {
            listG1 = g1(abstractC5838l);
            if (z2 && listG1 != null && (t() instanceof C5845t)) {
                AbstractC5838l abstractC5838lK0 = C5845t.k0(listG1);
                if (abstractC5838lK0 != null && !abstractC5838lK0.isDirectory() && (listZ1 = ((C5845t) t()).z1(abstractC5838lK0)) != null) {
                    for (String str : listZ1) {
                        for (AbstractC5838l abstractC5838l2 : listG1) {
                            if (str.equals(abstractC5838l2.w())) {
                                ((u) abstractC5838l2).a1(u.b.HIDDEN_DOTHIDDEN);
                            }
                        }
                    }
                }
                for (AbstractC5838l abstractC5838l3 : listG1) {
                    String strF = ax.G1.f.h().f(abstractC5838l3);
                    if (strF != null) {
                        ((u) abstractC5838l3).a1(ax.G1.f.c(strF));
                    }
                }
            }
        }
        return listG1;
    }

    /* JADX WARN: Code duplicated, block: B:80:0x0146 A[Catch: i -> 0x0149, TRY_LEAVE, TryCatch #5 {i -> 0x0149, blocks: (B:78:0x0138, B:80:0x0146), top: B:95:0x0138 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x015a A[Catch: i -> 0x015d, TRY_LEAVE, TryCatch #9 {i -> 0x015d, blocks: (B:83:0x014c, B:85:0x015a), top: B:98:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:98:0x014c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void M(AbstractC5838l abstractC5838l, C5839m c5839m, AbstractC5838l abstractC5838l2, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        AbstractC5838l abstractC5838lT;
        boolean z2;
        boolean z3;
        AbstractC5838l abstractC5838l3;
        boolean z4 = true;
        ax.p045d2.b.c(y() == abstractC5838l.P());
        ax.p045d2.b.a(abstractC5838l.isDirectory());
        if (!abstractC5838l.n()) {
            ax.p221pa.c.h().g().d("MVEX").h(y().H()).i();
            throw new ax.I1.s("Move source file not found");
        }
        AbstractC5838l abstractC5838lV0 = c5839m.V0(abstractC5838l2.S());
        if (!V.B(abstractC5838lV0) && !abstractC5838lV0.n() && !c5839m.j(abstractC5838lV0, false)) {
            throw new C0718i("Create target folder failed");
        }
        AbstractC5838l abstractC5838l4 = null;
        try {
            boolean zD = d(abstractC5838l, abstractC5838l2);
            if (!abstractC5838l2.n()) {
                abstractC5838lT = abstractC5838l2;
                z2 = false;
                z3 = false;
            } else {
                if (abstractC5838l2.isDirectory()) {
                    throw new ax.I1.t();
                }
                if (!c5839m.T() || zD) {
                    abstractC5838lT = C0740v.t(c5839m, abstractC5838l2);
                    z2 = false;
                } else {
                    abstractC5838lT = null;
                    z2 = true;
                }
                z3 = true;
            }
            try {
                try {
                    if (zD) {
                        try {
                            k1(abstractC5838l, abstractC5838lT, cVar, iVar);
                            abstractC5838l3 = abstractC5838lT;
                            z4 = false;
                        } catch (C0718i e) {
                            e = e;
                            abstractC5838l4 = abstractC5838lT;
                            e.printStackTrace();
                            if (abstractC5838l4 != null && !(e instanceof C0714e)) {
                                try {
                                    if (V0(abstractC5838l.C()).n()) {
                                        c5839m.l1(abstractC5838l4);
                                    }
                                } catch (C0718i unused) {
                                }
                            }
                            throw e;
                        }
                    } else {
                        try {
                            if (abstractC5838l.a0()) {
                                try {
                                    q(abstractC5838l);
                                } catch (C0718i unused2) {
                                }
                            }
                            if (z2) {
                                abstractC5838l3 = abstractC5838lT;
                                c5839m.W(abstractC5838l2, v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), z, cVar, iVar);
                            } else {
                                abstractC5838l3 = abstractC5838lT;
                                c5839m.c1(abstractC5838l3, v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), z, cVar, iVar);
                            }
                        } catch (C0710a e2) {
                            e = e2;
                            abstractC5838l3 = abstractC5838lT;
                            abstractC5838l4 = abstractC5838l3;
                            if (abstractC5838l4 != null) {
                                try {
                                    if (V0(abstractC5838l.C()).n()) {
                                        c5839m.l1(abstractC5838l4);
                                    }
                                } catch (C0718i unused3) {
                                }
                            }
                            throw e;
                        } catch (C0718i e3) {
                            e = e3;
                            abstractC5838l3 = abstractC5838lT;
                            abstractC5838l4 = abstractC5838l3;
                            e.printStackTrace();
                            if (abstractC5838l4 != null) {
                                if (V0(abstractC5838l.C()).n()) {
                                    c5839m.l1(abstractC5838l4);
                                }
                            }
                            throw e;
                        }
                    }
                    if (z3 && !z2) {
                        AbstractC5838l abstractC5838lV1 = c5839m.V0(abstractC5838l3.C());
                        if (!abstractC5838lV1.n()) {
                            throw new ax.I1.s("tmp file not exists");
                        }
                        k(c5839m, abstractC5838l2, abstractC5838lV1);
                    }
                    if (z4) {
                        l1(abstractC5838l);
                    }
                } catch (C0710a e4) {
                    e = e4;
                    abstractC5838l4 = abstractC5838lT;
                    if (abstractC5838l4 != null) {
                        if (V0(abstractC5838l.C()).n()) {
                            c5839m.l1(abstractC5838l4);
                        }
                    }
                    throw e;
                }
            } catch (C0710a e5) {
                e = e5;
            } catch (C0718i e6) {
                e = e6;
            }
        } catch (C0710a e7) {
            e = e7;
        } catch (C0718i e8) {
            e = e8;
        }
    }

    public boolean N() {
        return this.a.G();
    }

    public ax.U1.a O(String str, int i) throws C0718i {
        return this.a.H(str, i);
    }

    public synchronized void P(boolean z) {
        try {
            if (this.b.get() == 0) {
                m.severe("OPEROATOR RELEASED MORE THAN RETAINED!!!!!");
                ax.p221pa.c.h().d("REL").k().h(y().H() + ":" + x()).i();
            } else {
                this.l = this.k;
                this.k = this.j;
                this.j = this.i;
                this.i = "(" + this.b.get() + ") " + ax.p338y1.a.i();
            }
            if (f1()) {
                new b(this).i(new Long[0]);
            } else {
                Q();
                if (z && (t() instanceof C5845t)) {
                    C5845t c5845t = (C5845t) t();
                    if (c5845t.G0()) {
                        ax.E1.P.V();
                        c5845t.h0();
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void R(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        if (abstractC5838l2.n()) {
            throw new C0714e(abstractC5838l.isDirectory() != abstractC5838l2.isDirectory());
        }
        k1(abstractC5838l, abstractC5838l2, cVar, iVar);
    }

    public synchronized void S() {
        this.b.getAndIncrement();
        this.h = this.g;
        this.g = this.f;
        this.f = this.e;
        this.e = "(" + this.b.get() + ") " + ax.p338y1.a.i();
    }

    public boolean T() {
        return this.a.I();
    }

    public boolean U() {
        return this.a.J();
    }

    public boolean V() {
        return this.a.K();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        if (V.y(str)) {
            return this.a.V0(str);
        }
        ax.p221pa.c.h().g().b("GFI!!!").k().h(y().H() + ":" + str).i();
        throw new C0718i("Not normalzied path in getFileInfo param");
    }

    public void W(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.G1.b.k().p(z(), abstractC5838l.S());
        ax.G1.b.k().e(abstractC5838l);
        this.a.L(abstractC5838l, a2, str, j, l, c5840n, z, cVar, iVar);
        if (H(abstractC5838l)) {
            ax.A1.h.P(abstractC5838l).a(abstractC5838l);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) throws IOException {
        return this.a.W0(str, str2, str3);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void X(AbstractC5838l abstractC5838l, C5839m c5839m, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        AbstractC5838l abstractC5838lT;
        boolean z;
        AbstractC5838l abstractC5838lV0 = null;
        try {
            boolean z2 = false;
            if (!abstractC5838l2.n()) {
                abstractC5838lT = abstractC5838l2;
                z = false;
            } else if (c5839m.T() || c5839m.U()) {
                abstractC5838lT = null;
                z = true;
                z2 = true;
            } else {
                abstractC5838lT = C0740v.t(c5839m, abstractC5838l2);
                z = true;
            }
            try {
                if (z2) {
                    try {
                        c5839m.W(abstractC5838l2, v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), true, cVar, iVar);
                    } catch (ax.I1.x e) {
                        c5839m.c1(C0740v.t(c5839m, abstractC5838l2), v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), true, cVar, iVar);
                        throw e;
                    }
                } else {
                    c5839m.c1(abstractC5838lT, v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), true, cVar, iVar);
                    if (z) {
                        abstractC5838lV0 = c5839m.V0(abstractC5838lT.C());
                        try {
                            if (!abstractC5838lV0.n()) {
                                throw new ax.I1.s("tmp file not exists");
                            }
                            k(c5839m, abstractC5838l2, abstractC5838lV0);
                        } catch (C0710a e2) {
                            e = e2;
                            if (abstractC5838lV0 != null) {
                                try {
                                    c5839m.l1(abstractC5838lV0);
                                } catch (C0718i unused) {
                                }
                            }
                            throw e;
                        }
                    }
                }
            } catch (C0710a e3) {
                e = e3;
                abstractC5838lV0 = abstractC5838lT;
                if (abstractC5838lV0 != null) {
                    c5839m.l1(abstractC5838lV0);
                }
                throw e;
            }
        } catch (C0710a e4) {
            e = e4;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return this.a.X0(abstractC5838l);
    }

    public boolean Y(AbstractC5838l abstractC5838l) {
        return ax.p338y1.f.B0(y(), abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return this.a.Y0(str, str2);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void Z(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        AbstractC5838l abstractC5838lT;
        boolean z2;
        AbstractC5838l abstractC5838l2 = null;
        try {
            boolean z3 = false;
            if (!abstractC5838l.n()) {
                abstractC5838lT = abstractC5838l;
                z2 = false;
            } else if (T()) {
                abstractC5838lT = null;
                z2 = true;
                z3 = true;
            } else {
                abstractC5838lT = C0740v.t(this, abstractC5838l);
                z2 = true;
            }
            try {
                if (z3) {
                    W(abstractC5838l, a2, str, j, l, c5840n, z, cVar, iVar);
                } else {
                    c1(abstractC5838lT, a2, str, j, l, c5840n, z, cVar, iVar);
                    if (z2) {
                        AbstractC5838l abstractC5838lV0 = V0(abstractC5838lT.C());
                        if (!abstractC5838lV0.n()) {
                            throw new ax.I1.s("tmp file not exists");
                        }
                        k(this, abstractC5838l, abstractC5838lV0);
                    }
                }
            } catch (C0710a e) {
                e = e;
                abstractC5838l2 = abstractC5838lT;
                if (abstractC5838l2 != null) {
                    try {
                        l1(abstractC5838l2);
                    } catch (C0718i unused) {
                    }
                }
                throw e;
            } catch (C0718i e2) {
                e = e2;
                abstractC5838l2 = abstractC5838lT;
                if (abstractC5838l2 != null) {
                    l1(abstractC5838l2);
                }
                throw e;
            }
        } catch (C0710a e3) {
            e = e3;
        } catch (C0718i e4) {
            e = e4;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        return this.a.Z0(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.a.a();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l.isDirectory());
        if (abstractC5838l2.n()) {
            ax.p221pa.c.h().d("CP1").k().h(y().H()).i();
            throw new C0718i("Target is aleady exist");
        }
        if (!abstractC5838l.n()) {
            ax.p221pa.c.h().g().d("CP2").k().h(y().H()).i();
            throw new ax.I1.s("Source is not exist");
        }
        ax.G1.b.k().p(abstractC5838l2.R(), abstractC5838l2.S());
        ax.G1.b.k().e(abstractC5838l2);
        this.a.a1(abstractC5838l, abstractC5838l2, cVar, iVar);
        if (H(abstractC5838l2)) {
            ax.A1.h.P(abstractC5838l2).a(abstractC5838l2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        this.a.b();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        long jU = H(abstractC5838l) ? ax.A1.h.P(abstractC5838l).U(abstractC5838l) : 0L;
        try {
            this.a.b1(abstractC5838l);
            ax.G1.b.k().s(z(), abstractC5838l.C());
            ax.G1.b.k().p(z(), abstractC5838l.S());
            ax.G1.b.k().e(abstractC5838l);
            if (H(abstractC5838l)) {
                ax.A1.h.P(abstractC5838l).q(abstractC5838l, jU);
            }
        } catch (Throwable th) {
            ax.G1.b.k().s(z(), abstractC5838l.C());
            ax.G1.b.k().p(z(), abstractC5838l.S());
            ax.G1.b.k().e(abstractC5838l);
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.G1.b.k().p(z(), abstractC5838l.S());
        ax.G1.b.k().e(abstractC5838l);
        this.a.c1(abstractC5838l, a2, str, j, l, c5840n, z, cVar, iVar);
        if (H(abstractC5838l)) {
            ax.A1.h.P(abstractC5838l).a(abstractC5838l);
        }
    }

    public boolean d(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        if (C0740v.H(abstractC5838l) && C0740v.H(abstractC5838l2)) {
            return m1(abstractC5838l, abstractC5838l2);
        }
        if (C0740v.F(abstractC5838l, abstractC5838l2)) {
            return m1(abstractC5838l, abstractC5838l2);
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        return this.a.d1(abstractC5838l, j);
    }

    public void e(String str) {
        String path = Uri.parse(str).getPath();
        if (path == null) {
            return;
        }
        this.a.e(path);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        this.a.e1(activity, fragment, aVar);
    }

    public void f() {
        this.a.f();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return this.a.f1();
    }

    public void g(InterfaceC5828b.a aVar) {
        this.a.g(aVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (abstractC5838l.isDirectory()) {
            List<AbstractC5838l> listG1 = this.a.g1(abstractC5838l);
            if (Y(abstractC5838l)) {
                ax.G1.b.k().m(abstractC5838l, listG1);
            }
            return listG1;
        }
        ax.p221pa.c.h().g().d("FOLICH!!!").k().h(y().H() + ":" + abstractC5838l.n()).i();
        throw new C0718i("list children : fileinfo is not directory");
    }

    public boolean h(long j) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        g(new a(atomicBoolean, countDownLatch));
        if (j == 0) {
            j = 20000;
        }
        try {
            countDownLatch.await(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        return atomicBoolean.get();
    }

    /* JADX WARN: Code duplicated, block: B:65:0x00fa A[Catch: i -> 0x00fd, TRY_LEAVE, TryCatch #10 {i -> 0x00fd, blocks: (B:63:0x00ec, B:65:0x00fa), top: B:80:0x00ec }] */
    /* JADX WARN: Code duplicated, block: B:80:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void i(AbstractC5838l abstractC5838l, C5839m c5839m, AbstractC5838l abstractC5838l2, boolean z, boolean z2, boolean z3, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        AbstractC5838l abstractC5838lV0;
        AbstractC5838l abstractC5838lT;
        boolean z4;
        boolean z5;
        AbstractC5838l abstractC5838l3;
        ax.p045d2.b.c(y() == abstractC5838l.P());
        ax.p045d2.b.a(abstractC5838l.isDirectory());
        AbstractC5838l abstractC5838l4 = null;
        AbstractC5838l abstractC5838l5 = null;
        try {
            boolean zF = C0740v.F(abstractC5838l, abstractC5838l2);
            if (abstractC5838l2.n()) {
                if (!c5839m.T() || zF) {
                    abstractC5838lT = C0740v.t(c5839m, abstractC5838l2);
                    z4 = false;
                } else {
                    abstractC5838lT = null;
                    z4 = true;
                }
                z5 = true;
            } else {
                abstractC5838lT = abstractC5838l2;
                z4 = false;
                z5 = false;
            }
            try {
                try {
                    try {
                        if (zF) {
                            a1(abstractC5838l, abstractC5838lT, cVar, iVar);
                            abstractC5838l3 = abstractC5838lT;
                        } else {
                            Long lValueOf = z ? Long.valueOf(abstractC5838l.q()) : null;
                            C5840n c5840nB = z2 ? abstractC5838l.B() : null;
                            try {
                                if (abstractC5838l.a0()) {
                                    try {
                                        q(abstractC5838l);
                                    } catch (C0718i unused) {
                                    }
                                }
                                if (z4) {
                                    abstractC5838l3 = abstractC5838lT;
                                    c5839m.W(abstractC5838l2, v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), lValueOf, c5840nB, z3, cVar, iVar);
                                } else {
                                    abstractC5838l3 = abstractC5838lT;
                                    c5839m.c1(abstractC5838l3, v(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), lValueOf, c5840nB, z3, cVar, iVar);
                                }
                            } catch (C0710a e) {
                                e = e;
                                abstractC5838l3 = abstractC5838lT;
                            } catch (C0718i e2) {
                                e = e2;
                                abstractC5838l3 = abstractC5838lT;
                                abstractC5838l4 = abstractC5838l3;
                                if (abstractC5838l4 != null) {
                                    try {
                                        abstractC5838lV0 = c5839m.V0(abstractC5838l4.C());
                                        if (abstractC5838lV0.n()) {
                                            c5839m.l1(abstractC5838lV0);
                                        }
                                    } catch (C0718i unused2) {
                                    }
                                }
                                e.printStackTrace();
                                throw e;
                            }
                        }
                        if (!z5 || z4) {
                            return;
                        }
                        AbstractC5838l abstractC5838lV1 = c5839m.V0(abstractC5838l3.C());
                        if (!abstractC5838lV1.n()) {
                            throw new ax.I1.s("tmp file not exists");
                        }
                        k(c5839m, abstractC5838l2, abstractC5838lV1);
                        return;
                    } catch (C0710a e3) {
                        e = e3;
                        abstractC5838l5 = abstractC5838lT;
                    }
                } catch (C0718i e4) {
                    e = e4;
                    abstractC5838l4 = abstractC5838lT;
                    if (abstractC5838l4 != null) {
                        abstractC5838lV0 = c5839m.V0(abstractC5838l4.C());
                        if (abstractC5838lV0.n()) {
                            c5839m.l1(abstractC5838lV0);
                        }
                    }
                    e.printStackTrace();
                    throw e;
                }
            } catch (C0710a e5) {
                e = e5;
            } catch (C0718i e6) {
                e = e6;
            }
            abstractC5838l5 = abstractC5838l3;
        } catch (C0710a e7) {
            e = e7;
        } catch (C0718i e8) {
            e = e8;
        }
        if (abstractC5838l5 != null) {
            try {
                AbstractC5838l abstractC5838lV2 = c5839m.V0(abstractC5838l5.C());
                if (abstractC5838lV2.n()) {
                    c5839m.l1(abstractC5838lV2);
                }
            } catch (C0718i unused3) {
            }
        }
        throw e;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        boolean zI1 = this.a.i1(abstractC5838l);
        if (zI1) {
            ax.G1.b.k().p(z(), abstractC5838l.S());
            ax.G1.b.k().e(abstractC5838l);
        }
        return zI1;
    }

    public boolean j(AbstractC5838l abstractC5838l, boolean z) {
        try {
            if (z) {
                if (j(abstractC5838l, false)) {
                    return true;
                }
                String strS = abstractC5838l.S();
                if (V.a.equals(strS)) {
                    return false;
                }
                AbstractC5838l abstractC5838lV0 = V0(strS);
                return !abstractC5838lV0.n() && j(abstractC5838lV0, true) && j(abstractC5838l, false);
            }
            boolean zH1 = this.a.h1(abstractC5838l);
            if (zH1) {
                ax.G1.b.k().p(z(), abstractC5838l.S());
                ax.G1.b.k().e(abstractC5838l);
                if (G()) {
                    ax.A1.h.O(z()).a(V0(abstractC5838l.C()));
                }
            }
            return zH1;
        } catch (C0718i unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return this.a.j1();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.c(y() == abstractC5838l.P());
        if (!C0740v.H(abstractC5838l)) {
            ax.p045d2.b.c(abstractC5838l.P() == abstractC5838l2.P());
        }
        if (!abstractC5838l.n()) {
            ax.p221pa.c.h().g().d("MV2").k().h(y().H()).i();
            throw new ax.I1.s("Source file not exists");
        }
        if (abstractC5838l2.n()) {
            ax.p221pa.c.h().g().d("MV1").k().h(y().H()).i();
            throw new C0714e(abstractC5838l.isDirectory() != abstractC5838l2.isDirectory());
        }
        long jU = H(abstractC5838l) ? ax.A1.h.P(abstractC5838l).U(abstractC5838l) : 0L;
        try {
            this.a.k1(abstractC5838l, abstractC5838l2, cVar, iVar);
            if (H(abstractC5838l)) {
                ax.A1.h.P(abstractC5838l).q(abstractC5838l, jU);
            }
            if (H(abstractC5838l2)) {
                try {
                    AbstractC5838l abstractC5838lV0 = abstractC5838l.R() != abstractC5838l2.R() ? ax.J1.r.d(abstractC5838l2.R()).V0(abstractC5838l2.C()) : V0(abstractC5838l2.C());
                    ax.A1.h.P(abstractC5838lV0).a(abstractC5838lV0);
                } catch (C0718i unused) {
                }
            }
        } finally {
            ax.G1.b.k().t(abstractC5838l);
            ax.G1.b.k().p(abstractC5838l.R(), abstractC5838l.S());
            ax.G1.b.k().p(abstractC5838l2.R(), abstractC5838l2.S());
            ax.G1.b.k().e(abstractC5838l);
            ax.G1.b.k().e(abstractC5838l2);
        }
    }

    public void l(AbstractC5838l abstractC5838l) throws C0718i {
        if (j1()) {
            b1(abstractC5838l);
            return;
        }
        if (!abstractC5838l.isDirectory()) {
            l1(abstractC5838l);
            return;
        }
        Iterator<AbstractC5838l> it = g1(abstractC5838l).iterator();
        while (it.hasNext()) {
            l(it.next());
        }
        l1(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        long jO = (!H(abstractC5838l) || abstractC5838l.isDirectory()) ? 0L : abstractC5838l.o();
        try {
            this.a.l1(abstractC5838l);
            ax.G1.b.k().s(z(), abstractC5838l.C());
            ax.G1.b.k().p(z(), abstractC5838l.S());
            ax.G1.b.k().e(abstractC5838l);
            if (H(abstractC5838l)) {
                ax.A1.h.P(abstractC5838l).q(abstractC5838l, jO);
            }
        } catch (Throwable th) {
            ax.G1.b.k().s(z(), abstractC5838l.C());
            ax.G1.b.k().p(z(), abstractC5838l.S());
            ax.G1.b.k().e(abstractC5838l);
            throw th;
        }
    }

    public void m(ax.P1.i iVar) {
        if (t() instanceof C5845t) {
            ((C5845t) t()).f0(iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return this.a.m1(abstractC5838l, abstractC5838l2);
    }

    public void n(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        this.a.k(abstractC5838l, str, z, hVar, cVar);
    }

    public void o(AbstractC5838l abstractC5838l, File file, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        AbstractC5838l abstractC5838lV0;
        C5839m c5839mF = ax.J1.r.f(file);
        AbstractC5838l abstractC5838lV1 = c5839mF.V0(file.getAbsolutePath());
        if (abstractC5838lV1.n()) {
            c5839mF.l1(abstractC5838lV1);
            abstractC5838lV0 = c5839mF.V0(file.getAbsolutePath());
        } else {
            abstractC5838lV0 = abstractC5838lV1;
        }
        i(abstractC5838l, c5839mF, abstractC5838lV0, System.currentTimeMillis() <= abstractC5838l.q(), false, false, cVar, iVar);
    }

    public void p(AbstractC5838l abstractC5838l) throws C0718i {
        List<AbstractC5838l> listG = ax.G1.b.k().g(abstractC5838l);
        if (listG == null) {
            listG = g1(abstractC5838l);
        }
        abstractC5838l.b0(listG.size());
    }

    public void q(AbstractC5838l abstractC5838l) throws C0718i {
        this.a.l(abstractC5838l);
    }

    public void r(AbstractC5838l abstractC5838l) throws C0718i {
        abstractC5838l.b0(g1(abstractC5838l).size());
    }

    public Context s() {
        return this.d;
    }

    public AbstractC5837k t() {
        return this.a;
    }

    public String u() {
        return this.a.o();
    }

    public ax.J1.A v(AbstractC5838l abstractC5838l) {
        return this.a.p(abstractC5838l);
    }

    public int w() {
        return this.a.q();
    }

    public String x() {
        return this.i + ":" + this.j + ":" + this.k + ":" + this.l + " - " + this.e + ":" + this.f + ":" + this.g + ":" + this.h;
    }

    public ax.p338y1.f y() {
        return this.a.r();
    }

    public ax.J1.F z() {
        return this.a.s();
    }
}
