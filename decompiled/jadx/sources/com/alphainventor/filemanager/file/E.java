package com.alphainventor.filemanager.file;

import android.net.Uri;
import android.os.Build;
import ax.I1.C0710a;
import ax.I1.C0713d;
import ax.I1.C0714e;
import ax.I1.C0718i;
import ax.J1.C0735p;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.V;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class E extends C5845t {
    public static Uri A;
    private static final Object z = new Object();
    private static HashSet<ax.J1.F> B = new HashSet<>();
    private static final Object C = new Object();

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.p338y1.f.values().length];
            a = iArr;
            try {
                iArr[ax.p338y1.f.r0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.p338y1.f.q0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class b extends ax.p045d2.n<Void, Void, Void> {
        long h;
        ax.J1.F i;
        String j;

        /* synthetic */ b(long j, ax.J1.F f, String str, a aVar) {
            this(j, f, str);
        }

        private void w(C5839m c5839m, String str, AbstractC5838l abstractC5838l) throws C0718i {
            Stack stack = new Stack();
            stack.push(abstractC5838l);
            while (stack.size() > 0) {
                AbstractC5838l abstractC5838l2 = (AbstractC5838l) stack.pop();
                if (abstractC5838l2.n()) {
                    for (AbstractC5838l abstractC5838l3 : c5839m.g1(abstractC5838l2)) {
                        if (E.c2(abstractC5838l3.w())) {
                            if (!abstractC5838l3.C().equals(str) && abstractC5838l3.q() < this.h) {
                                c5839m.l(abstractC5838l3);
                            }
                        } else if (abstractC5838l3.isDirectory()) {
                            stack.push(abstractC5838l3);
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            try {
                C5839m c5839mD = ax.J1.r.d(this.i);
                w(c5839mD, E.T1(this.i), c5839mD.V0(this.j));
            } catch (C0718i e) {
                e.printStackTrace();
            }
            synchronized (E.C) {
                E.B.remove(this.i);
            }
            return null;
        }

        private b(long j, ax.J1.F f, String str) {
            super(ax.d2.n.f.LOW);
            this.h = j;
            this.i = f;
            this.j = str;
        }
    }

    public static class c {
        String a;
        String b;
        String c;

        public c(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        boolean a() {
            String str;
            return (this.c == null || this.a == null || (str = this.b) == null || !V.y(str) || !V.y(this.c)) ? false : true;
        }
    }

    public static void N1(ax.J1.F f) {
        if (f.b() != 0) {
            O1(ax.A1.h.o(f.b()));
            return;
        }
        O1(ax.J1.F.e);
        if (ax.G1.i.D().l0()) {
            O1(ax.J1.F.f);
        }
    }

    private static void O1(ax.J1.F f) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            C5839m c5839mD = ax.J1.r.d(f);
            AbstractC5838l abstractC5838lV0 = c5839mD.V0(T1(f));
            if (abstractC5838lV0.n()) {
                for (AbstractC5838l abstractC5838l : c5839mD.g1(abstractC5838lV0)) {
                    String strW = abstractC5838l.w();
                    if ("dirs".equals(strW) || "files".equals(strW)) {
                        if (abstractC5838l.isDirectory()) {
                            Iterator<AbstractC5838l> it = c5839mD.g1(abstractC5838l).iterator();
                            while (it.hasNext()) {
                                c5839mD.l(it.next());
                            }
                        } else {
                            c5839mD.l(abstractC5838l);
                        }
                    } else if (!".nomedia".equals(strW)) {
                        c5839mD.l(abstractC5838l);
                    }
                }
                ax.A1.h.O(f).q0();
                ax.A1.h.W().q0();
                if (f.d() == ax.p338y1.f.r0 && !c5839mD.X0(abstractC5838lV0)) {
                    synchronized (C) {
                        try {
                            if (!B.contains(f)) {
                                new b(jCurrentTimeMillis, f, f.e(), null).i(new Void[0]);
                                B.add(f);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return;
                }
                if (f.d() == ax.p338y1.f.q0 && ax.E1.P.B()) {
                    synchronized (C) {
                        try {
                            if (!B.contains(f)) {
                                new b(jCurrentTimeMillis, f, V.L(f.e(), "/Android/obb"), null).i(new Void[0]);
                                B.add(f);
                            }
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                    return;
                }
                return;
                e.printStackTrace();
            }
        } catch (C0718i e) {
            e.printStackTrace();
        }
    }

    public static void P1(C5839m c5839m, ax.J1.F f, boolean z2) throws C0718i {
        String strT1 = T1(f);
        u uVar = (u) c5839m.V0(strT1);
        if (uVar.n()) {
            if (z2) {
                ax.p221pa.c.h().g().c("CREATE RECYCLE BIN REPORT DIR EXIST").h(strT1).i();
            }
        } else {
            if (!c5839m.j(uVar, false)) {
                throw new C0718i("Could not create Recycle Bin folder!");
            }
            if (z2) {
                ax.p221pa.c.h().g().c("CREATE RECYCLE BIN FOLDER").h(strT1 + ",writable:" + ax.G1.i.D().d0(f) + ",useDocumentFile:" + uVar.X0() + ",loc:" + uVar.P().H() + ",location:" + f.toString() + ",baseloc:" + uVar.v0().toString()).i();
            }
        }
        AbstractC5838l abstractC5838lV0 = c5839m.V0(V.M(strT1, ".nomedia"));
        if (!abstractC5838lV0.n()) {
            c5839m.i1(abstractC5838lV0);
        }
        AbstractC5838l abstractC5838lV1 = c5839m.V0(V.M(strT1, "dirs"));
        if (!abstractC5838lV1.n()) {
            c5839m.j(abstractC5838lV1, false);
        }
        AbstractC5838l abstractC5838lV2 = c5839m.V0(V.M(strT1, "files"));
        if (abstractC5838lV2.n()) {
            return;
        }
        c5839m.j(abstractC5838lV2, false);
    }

    private static ax.J1.F Q1(u uVar) throws C0718i {
        ax.J1.F fV0 = uVar.v0();
        int i = a.a[fV0.d().ordinal()];
        if (i == 1 || i == 2) {
            return fV0;
        }
        ax.p221pa.c.h().g().b("Invalid Recycle Bin Base location").k().h("loc:" + uVar.R().toString() + ",base:" + fV0.toString()).i();
        throw new C0718i("Invalid base location");
    }

    public static long R1(C5839m c5839m, AbstractC5838l abstractC5838l, Map<String, C5831e.a> map) {
        if (abstractC5838l.isDirectory()) {
            return (map == null || !map.containsKey(abstractC5838l.Q())) ? S1(c5839m, abstractC5838l) : map.get(abstractC5838l.Q()).c;
        }
        return abstractC5838l.o();
    }

    private static long S1(C5839m c5839m, AbstractC5838l abstractC5838l) {
        Stack stack = new Stack();
        stack.push(abstractC5838l);
        long jO = 0;
        while (!stack.isEmpty()) {
            AbstractC5838l abstractC5838l2 = (AbstractC5838l) stack.pop();
            if (abstractC5838l2.isDirectory()) {
                try {
                    Iterator<AbstractC5838l> it = c5839m.g1(abstractC5838l2).iterator();
                    while (it.hasNext()) {
                        stack.push(it.next());
                    }
                } catch (C0718i e) {
                    e.printStackTrace();
                }
            } else {
                jO += abstractC5838l2.o();
            }
        }
        return jO;
    }

    public static String T1(ax.J1.F f) {
        if (!V.y(f.e())) {
            ax.p221pa.c.h().g().b("Not normalized root path in recycle bin :" + f.e()).i();
        }
        return V.M(f.e(), ".$recycle_bin$");
    }

    private static boolean W1(AbstractC5838l abstractC5838l) {
        if (abstractC5838l.isDirectory()) {
            return false;
        }
        String[] strArrSplit = abstractC5838l.w().split("_");
        if (strArrSplit.length < 4 || strArrSplit[0].length() != 1) {
            return false;
        }
        try {
            Long.valueOf(strArrSplit[1]);
            Long.valueOf(strArrSplit[2]);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean X1(AbstractC5838l abstractC5838l) {
        if (!ax.E1.P.q() || !C0740v.H(abstractC5838l)) {
            return false;
        }
        u uVar = (u) abstractC5838l;
        if (ax.p338y1.f.h0(uVar.P())) {
            return u.O0(uVar.w0());
        }
        return false;
    }

    public static boolean Y1(AbstractC5838l abstractC5838l) {
        return (abstractC5838l instanceof F) && ((F) abstractC5838l).r1();
    }

    public static boolean Z1(AbstractC5838l abstractC5838l) {
        return ax.E1.P.B() && C5845t.I0(abstractC5838l);
    }

    public static boolean a2(AbstractC5838l abstractC5838l) {
        if (ax.E1.P.B() && C5845t.I0(abstractC5838l)) {
            return true;
        }
        return ax.E1.P.L() && C5845t.H0(abstractC5838l);
    }

    public static boolean b2(AbstractC5838l abstractC5838l) {
        if (C0740v.H(abstractC5838l)) {
            return c2(abstractC5838l.w());
        }
        return false;
    }

    public static boolean c2(String str) {
        return str.startsWith(".$recycle_bin$");
    }

    private boolean d2(AbstractC5838l abstractC5838l) {
        return b2(abstractC5838l) || abstractC5838l.C().contains(".$recycle_bin$");
    }

    private void e2(List<AbstractC5838l> list, ax.J1.F f) {
        List<AbstractC5838l> listG1;
        AbstractC5838l abstractC5838lM0 = m0(T1(f), f);
        if (abstractC5838lM0.n()) {
            try {
                listG1 = super.g1(abstractC5838lM0);
            } catch (C0718i unused) {
                listG1 = null;
            }
            if (listG1 == null) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (AbstractC5838l abstractC5838l : listG1) {
                if (!abstractC5838l.isDirectory() && W1(abstractC5838l)) {
                    synchronized (z) {
                        try {
                            F f2 = new F(this, (u) abstractC5838l, f, true);
                            if (f2.q1() != null) {
                                AbstractC5838l abstractC5838lM1 = m0(f2.q1(), f2.v0());
                                if (!d2(abstractC5838lM1)) {
                                    ax.p221pa.c.h().g().b("INVALID RECYCLED FILE").h("location:" + f.toString() + "," + abstractC5838lM1.C()).i();
                                } else if (abstractC5838lM1.n()) {
                                    list.add(f2);
                                } else if (f2.q() < jCurrentTimeMillis - 3600000) {
                                    ax.p221pa.c.h().g().c("RECYCLED FILE DELETED").h("location:" + f.toString()).i();
                                    l1(abstractC5838l);
                                } else {
                                    ax.p221pa.c.h().g().c("RECYCLED FILE SKIPPED").h("location:" + f.toString()).i();
                                }
                            } else if (abstractC5838l.q() < jCurrentTimeMillis - 3600000) {
                                ax.p221pa.c.h().g().c("RECYCLED FILE PATH == null 1-1").h("location:" + f.toString() + ",size:" + abstractC5838l.o() + ",exists:" + abstractC5838l.n() + "," + (jCurrentTimeMillis - abstractC5838l.q())).i();
                                try {
                                    l1(abstractC5838l);
                                } catch (C0718i unused2) {
                                }
                            } else {
                                ax.p221pa.c.h().g().c("RECYCLED FILE PATH == null 1-2").h("location:" + f.toString() + ",size:" + abstractC5838l.o() + ",exists:" + abstractC5838l.n() + "," + (jCurrentTimeMillis - abstractC5838l.q())).i();
                            }
                        } catch (C0718i e) {
                            if (e.getMessage() != null && e.getMessage().startsWith("Invalid RecycleBin Index")) {
                                if (abstractC5838l.q() < jCurrentTimeMillis - 3600000) {
                                    ax.p221pa.c.h().g().c("RECYCLED FILE PATH == null 2-1").h("location:" + f.toString() + ",size:" + abstractC5838l.o() + ",exists:" + abstractC5838l.n() + "," + (jCurrentTimeMillis - abstractC5838l.q())).i();
                                    try {
                                        l1(abstractC5838l);
                                    } catch (C0718i unused3) {
                                    }
                                } else {
                                    ax.p221pa.c.h().g().c("RECYCLED FILE PATH == null 2-2").h("location:" + f.toString() + ",size:" + abstractC5838l.o() + ",exists:" + abstractC5838l.n() + "," + (jCurrentTimeMillis - abstractC5838l.q())).i();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static c f2(AbstractC5838l abstractC5838l, ax.J1.F f) throws Throwable {
        C5839m c5839mD = ax.J1.r.d(f);
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(c5839mD.d1(abstractC5838l, 0L)));
                try {
                    String line = bufferedReader2.readLine();
                    String line2 = bufferedReader2.readLine();
                    String line3 = bufferedReader2.readLine();
                    if (line == null && line3 != null) {
                        bufferedReader2.close();
                        bufferedReader2 = new BufferedReader(new InputStreamReader(c5839mD.d1(abstractC5838l, 0L)));
                        line = bufferedReader2.readLine();
                        line2 = bufferedReader2.readLine();
                        line3 = bufferedReader2.readLine();
                    }
                    bufferedReader = bufferedReader2;
                    if (line3 == null || line == null) {
                        long jCurrentTimeMillis = System.currentTimeMillis() - abstractC5838l.q();
                        boolean z2 = jCurrentTimeMillis < 5000 && jCurrentTimeMillis >= 0;
                        if (f != null) {
                            ax.p221pa.c.h().g().d("LOAD RECYCLE INDEX").k().h("exists:" + abstractC5838l.n() + "just mod :" + z2 + ",1:" + line + ",2:" + line2 + ",3:" + line3 + ",base:" + f.toString()).i();
                        } else {
                            ax.p221pa.c.h().g().d("LOAD RECYCLE INDEX").k().h("exists:" + abstractC5838l.n() + "just mod :" + z2 + ",1:" + line + ",2:" + line2 + ",3:" + line3).i();
                        }
                    }
                    c cVar = new c(line, line2, line3);
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    return cVar;
                } catch (IOException e) {
                    e = e;
                    bufferedReader = bufferedReader2;
                    throw new C0718i(e);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void g2(C5839m c5839m, AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        if (!abstractC5838l2.n()) {
            if (c5839m.X0(abstractC5838l) || abstractC5838l.S().equals(abstractC5838l2.S())) {
                c5839m.k1(abstractC5838l, abstractC5838l2, cVar, iVar);
                return;
            } else {
                if (!c5839m.j(abstractC5838l2, true)) {
                    throw new C0718i("Create folder failed in move directory");
                }
                abstractC5838l2 = c5839m.V0(abstractC5838l2.C());
            }
        }
        if (!abstractC5838l2.isDirectory()) {
            throw new ax.I1.t();
        }
        for (AbstractC5838l abstractC5838l3 : c5839m.g1(abstractC5838l)) {
            AbstractC5838l abstractC5838lV0 = c5839m.V0(V.M(abstractC5838l2.C(), abstractC5838l3.w()));
            if (abstractC5838l3.isDirectory()) {
                g2(c5839m, abstractC5838l3, abstractC5838lV0, cVar, iVar);
            } else {
                c5839m.M(abstractC5838l3, c5839m, abstractC5838lV0, true, cVar, iVar);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0149 A[Catch: all -> 0x00d4, TRY_ENTER, TryCatch #3 {, blocks: (B:23:0x005f, B:25:0x0088, B:27:0x00c0, B:29:0x00c4, B:30:0x00c7, B:41:0x00db, B:43:0x00e1, B:47:0x00f3, B:48:0x011b, B:49:0x0123, B:50:0x012b, B:51:0x0133, B:58:0x0149, B:60:0x014f, B:61:0x0152, B:63:0x0158, B:64:0x015f, B:65:0x0160, B:66:0x0214, B:68:0x0216, B:69:0x024c), top: B:80:0x005f, inners: #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0158 A[Catch: all -> 0x00d4, TryCatch #3 {, blocks: (B:23:0x005f, B:25:0x0088, B:27:0x00c0, B:29:0x00c4, B:30:0x00c7, B:41:0x00db, B:43:0x00e1, B:47:0x00f3, B:48:0x011b, B:49:0x0123, B:50:0x012b, B:51:0x0133, B:58:0x0149, B:60:0x014f, B:61:0x0152, B:63:0x0158, B:64:0x015f, B:65:0x0160, B:66:0x0214, B:68:0x0216, B:69:0x024c), top: B:80:0x005f, inners: #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0160 A[Catch: all -> 0x00d4, TryCatch #3 {, blocks: (B:23:0x005f, B:25:0x0088, B:27:0x00c0, B:29:0x00c4, B:30:0x00c7, B:41:0x00db, B:43:0x00e1, B:47:0x00f3, B:48:0x011b, B:49:0x0123, B:50:0x012b, B:51:0x0133, B:58:0x0149, B:60:0x014f, B:61:0x0152, B:63:0x0158, B:64:0x015f, B:65:0x0160, B:66:0x0214, B:68:0x0216, B:69:0x024c), top: B:80:0x005f, inners: #4, #5 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:65:0x0160, please report this as an issue */
    public static String h2(C5839m c5839m, AbstractC5838l abstractC5838l, long j) throws C0718i, C0710a {
        String strS;
        String strM;
        AbstractC5838l abstractC5838lV0;
        String strM2;
        AbstractC5838l abstractC5838lV1;
        Boolean boolValueOf;
        Boolean boolValueOf2;
        Boolean boolValueOf3;
        Boolean boolValueOf4;
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zIsDirectory = abstractC5838l.isDirectory();
        String str = zIsDirectory ? "D" : "F";
        String strT1 = T1(Q1((u) abstractC5838l));
        String strC = abstractC5838l.C();
        boolean zX0 = c5839m.X0(abstractC5838l);
        boolean zZ1 = Z1(abstractC5838l);
        int i = 0;
        if (!zX0 || zZ1) {
            strS = abstractC5838l.S();
        } else {
            strS = zIsDirectory ? V.M(strT1, "dirs") : V.M(strT1, "files");
            AbstractC5838l abstractC5838lV2 = c5839m.V0(strS);
            if (!abstractC5838lV2.n() && !c5839m.j(abstractC5838lV2, false)) {
                throw new C0718i("Could not create recycle bin taret parent!");
            }
        }
        synchronized (z) {
            int i2 = 0;
            while (true) {
                StringBuilder sb = new StringBuilder();
                sb.append(".$recycle_bin$_");
                sb.append(jCurrentTimeMillis);
                sb.append("_");
                int i3 = i2 + 1;
                sb.append(i2);
                strM = V.M(strS, sb.toString());
                abstractC5838lV0 = c5839m.V0(strM);
                if (!abstractC5838lV0.n()) {
                    break;
                }
                i2 = i3;
            }
            while (true) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("_");
                sb2.append(jCurrentTimeMillis);
                sb2.append("_");
                sb2.append(j);
                long j2 = jCurrentTimeMillis;
                sb2.append("_");
                int i4 = i + 1;
                sb2.append(i);
                strM2 = V.M(strT1, sb2.toString());
                abstractC5838lV1 = c5839m.V0(strM2);
                if (!abstractC5838lV1.n()) {
                    try {
                        break;
                    } catch (C0718i e) {
                        AbstractC5838l abstractC5838lV3 = c5839m.V0(strT1);
                        ax.p221pa.c.h().g().d("FAILED TO WRITE RECYCLEBIN INDEX").m(e).h(strT1 + ", exists=" + abstractC5838lV3.n()).i();
                        throw e;
                    }
                }
                i = i4;
                jCurrentTimeMillis = j2;
            }
            k2(c5839m, abstractC5838lV1, strC, strM);
            Boolean boolValueOf5 = null;
            try {
                c5839m.k1(abstractC5838l, abstractC5838lV0, null, null);
            } catch (C0718i e2) {
                String string = HttpUrl.FRAGMENT_ENCODE_SET;
                try {
                    if (C0740v.H(abstractC5838l)) {
                        string = ((u) abstractC5838l).v0().toString();
                    }
                    abstractC5838lV1 = c5839m.V0(strM2);
                    AbstractC5838l abstractC5838lV4 = c5839m.V0(abstractC5838l.C());
                    AbstractC5838l abstractC5838lV5 = c5839m.V0(abstractC5838lV0.C());
                    AbstractC5838l abstractC5838lV6 = c5839m.V0(V.r(abstractC5838lV0.C()));
                    boolValueOf = Boolean.valueOf(abstractC5838lV4.n());
                    try {
                        boolValueOf2 = Boolean.valueOf(abstractC5838lV5.n());
                        try {
                            boolValueOf3 = Boolean.valueOf(abstractC5838lV6.n());
                            try {
                                boolValueOf4 = Boolean.valueOf(abstractC5838lV1.n());
                                try {
                                    boolValueOf5 = Boolean.valueOf(Z1(abstractC5838l));
                                } catch (Exception unused) {
                                }
                            } catch (Exception unused2) {
                                boolValueOf4 = null;
                            }
                        } catch (Exception unused3) {
                            boolValueOf3 = null;
                            boolValueOf4 = boolValueOf3;
                            if (boolValueOf2 != null) {
                                c5839m.l1(abstractC5838lV1);
                            }
                            if (X1(abstractC5838l)) {
                                throw new C0713d("Failed to move to recyclebin", e2);
                            }
                            ax.p221pa.c.h().g().d("FAILED MOVE TO RECYCLE BIN AFTER WRITING INDEX FILE").m(e2).h("from:" + abstractC5838l.P().H() + "," + ((u) abstractC5838l).v0().toString() + ",to:" + abstractC5838lV0.P().H() + "," + ((u) abstractC5838lV0).v0().toString() + ",base:" + string + ",src:" + boolValueOf + ",dst:" + boolValueOf2 + ",index:" + boolValueOf4 + ",parent:" + boolValueOf3 + ",sdk:" + Build.VERSION.SDK_INT + ",ext:" + abstractC5838l.v() + ",limited:" + boolValueOf5).i();
                            throw new C0718i("Failed to move to recyclebin", e2);
                        }
                    } catch (Exception unused4) {
                        boolValueOf2 = null;
                        boolValueOf3 = boolValueOf2;
                        boolValueOf4 = boolValueOf3;
                        if (boolValueOf2 != null) {
                            c5839m.l1(abstractC5838lV1);
                        }
                        if (X1(abstractC5838l)) {
                            throw new C0713d("Failed to move to recyclebin", e2);
                        }
                        ax.p221pa.c.h().g().d("FAILED MOVE TO RECYCLE BIN AFTER WRITING INDEX FILE").m(e2).h("from:" + abstractC5838l.P().H() + "," + ((u) abstractC5838l).v0().toString() + ",to:" + abstractC5838lV0.P().H() + "," + ((u) abstractC5838lV0).v0().toString() + ",base:" + string + ",src:" + boolValueOf + ",dst:" + boolValueOf2 + ",index:" + boolValueOf4 + ",parent:" + boolValueOf3 + ",sdk:" + Build.VERSION.SDK_INT + ",ext:" + abstractC5838l.v() + ",limited:" + boolValueOf5).i();
                        throw new C0718i("Failed to move to recyclebin", e2);
                    }
                } catch (Exception unused5) {
                    boolValueOf = null;
                    boolValueOf2 = null;
                }
                if (boolValueOf2 != null && !boolValueOf2.booleanValue()) {
                    c5839m.l1(abstractC5838lV1);
                }
                if (X1(abstractC5838l)) {
                    throw new C0713d("Failed to move to recyclebin", e2);
                }
                ax.p221pa.c.h().g().d("FAILED MOVE TO RECYCLE BIN AFTER WRITING INDEX FILE").m(e2).h("from:" + abstractC5838l.P().H() + "," + ((u) abstractC5838l).v0().toString() + ",to:" + abstractC5838lV0.P().H() + "," + ((u) abstractC5838lV0).v0().toString() + ",base:" + string + ",src:" + boolValueOf + ",dst:" + boolValueOf2 + ",index:" + boolValueOf4 + ",parent:" + boolValueOf3 + ",sdk:" + Build.VERSION.SDK_INT + ",ext:" + abstractC5838l.v() + ",limited:" + boolValueOf5).i();
                throw new C0718i("Failed to move to recyclebin", e2);
            }
        }
        if (zIsDirectory && !zX0) {
            i2(c5839m, strT1, strC, strM);
        }
        return abstractC5838lV1.C();
    }

    static void i2(C5839m c5839m, String str, String str2, String str3) {
        try {
            List<AbstractC5838l> listG1 = c5839m.g1(c5839m.V0(str));
            if (listG1 == null) {
                return;
            }
            Iterator<AbstractC5838l> it = listG1.iterator();
            while (it.hasNext()) {
                u uVar = (u) it.next();
                if (!uVar.isDirectory() && W1(uVar)) {
                    try {
                        synchronized (z) {
                            try {
                                c cVarF2 = f2(uVar, uVar.v0());
                                String str4 = cVarF2.c;
                                if (str4 != null) {
                                    if (!V.y(str4)) {
                                        ax.p221pa.c.h().g().b("!! INVALID RECYCLED FILE PATH !!").h("path : " + cVarF2.c).i();
                                    } else if (V.E(str2, cVarF2.c)) {
                                        String strL = V.L(str3, V.n(str2, cVarF2.c));
                                        c5839m.l1(uVar);
                                        k2(c5839m, uVar, cVarF2.b, strL);
                                    }
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    } catch (C0718i e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (C0718i e2) {
            ax.p221pa.c.h().d("CANT GET RECYCLE BIN INDEX DIR").h(e2.getMessage()).i();
            e2.printStackTrace();
        }
    }

    public static void j2(C5839m c5839m, F f, ax.D1.h.f fVar, ax.p045d2.c cVar) throws C0718i, C0710a {
        AbstractC5838l abstractC5838lD;
        AbstractC5838l abstractC5838lV0 = c5839m.V0(f.q1());
        AbstractC5838l abstractC5838lV1 = c5839m.V0(f.p1());
        AbstractC5838l abstractC5838lV2 = c5839m.V0(f.o1());
        if (!abstractC5838lV0.n()) {
            c5839m.l1(abstractC5838lV2);
            throw new C0718i("Recycled file does not exist");
        }
        if (!abstractC5838lV1.n()) {
            abstractC5838lD = abstractC5838lV1;
        } else if (fVar == ax.D1.h.f.RENAME) {
            abstractC5838lD = c5839m.D(abstractC5838lV1, false);
        } else {
            if (fVar != ax.D1.h.f.OVERWRITE) {
                throw new C0714e(abstractC5838lV1.isDirectory() != abstractC5838lV0.isDirectory());
            }
            abstractC5838lD = abstractC5838lV1;
        }
        AbstractC5838l abstractC5838lV3 = c5839m.V0(abstractC5838lD.S());
        if (!abstractC5838lV3.n() && !c5839m.j(abstractC5838lV3, true)) {
            throw new C0718i("Create parent failed");
        }
        boolean zIsDirectory = f.isDirectory();
        if (zIsDirectory) {
            g2(c5839m, abstractC5838lV0, abstractC5838lD, cVar, null);
        } else {
            c5839m.M(abstractC5838lV0, c5839m, abstractC5838lD, true, cVar, null);
        }
        synchronized (z) {
            c5839m.l1(abstractC5838lV2);
        }
        if (!zIsDirectory || c5839m.X0(f)) {
            return;
        }
        i2(c5839m, T1(Q1(f)), f.q1(), f.p1());
    }

    private static void k2(C5839m c5839m, AbstractC5838l abstractC5838l, String str, String str2) throws C0718i {
        byte[] bytes = ("1\n" + str + "\n" + str2).getBytes(Charset.forName("UTF-8"));
        try {
            ((C5845t) c5839m.t()).c1(abstractC5838l, C0744z.g(bytes), "text/plain", bytes.length, null, null, true, null, null);
        } catch (C0710a unused) {
        }
    }

    public F U1(AbstractC5838l abstractC5838l) throws C0718i {
        F f;
        if (!W1(abstractC5838l)) {
            throw new C0718i("invalid recycle bin index path");
        }
        synchronized (z) {
            f = new F(this, (u) abstractC5838l, null, true);
        }
        return f;
    }

    public F V1(String str) throws C0718i {
        return U1(V0(str));
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        AbstractC5838l abstractC5838lV0 = V0(str2);
        if (!W1(abstractC5838lV0)) {
            return super.W0(str, str2, str3);
        }
        try {
            F fU1 = U1(abstractC5838lV0);
            String strQ1 = fU1.q1();
            String strF = V.f(fU1.p1());
            String strF2 = C0735p.f(strF);
            if (strF2 == null) {
                strF2 = "application/octet-stream";
            }
            return C0(strQ1, strF, strF2);
        } catch (C0718i unused) {
            return ax.p003a2.b.n();
        }
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        AbstractC5838l abstractC5838lV0 = V0(str2);
        if (!W1(abstractC5838lV0)) {
            return super.Y0(str, str2);
        }
        try {
            F fU1 = U1(abstractC5838lV0);
            String strQ1 = fU1.q1();
            String strF = C0735p.f(V.f(fU1.p1()));
            if (strF == null) {
                strF = "application/octet-stream";
            }
            return B0(str, strQ1, strF);
        } catch (C0718i unused) {
            return -1;
        }
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (abstractC5838l == null) {
            return null;
        }
        return Y1(abstractC5838l) ? C0740v.P(abstractC5838l) : C0740v.O(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!V.B(abstractC5838l)) {
            return super.g1(abstractC5838l);
        }
        ArrayList arrayList = new ArrayList();
        if (q() == 0) {
            e2(arrayList, ax.J1.F.e);
            if (ax.G1.i.D().l0()) {
                e2(arrayList, ax.J1.F.f);
            }
        } else {
            e2(arrayList, ax.A1.h.o(q()));
        }
        return arrayList;
    }
}
