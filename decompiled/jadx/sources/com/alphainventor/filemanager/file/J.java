package com.alphainventor.filemanager.file;

import android.net.Uri;
import android.os.Build;
import ax.p070e3.C1957a;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1966j;
import ax.p086f3.C2015v;
import ax.p086f3.C2017x;
import ax.p086f3.d0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
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
public class J extends C6248x {
    public static Uri A;
    private static final Object z = new Object();
    private static HashSet<ax.p086f3.K> B = new HashSet<>();
    private static final Object C = new Object();

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.U2.f.values().length];
            a = iArr;
            try {
                iArr[ax.U2.f.u0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.U2.f.t0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class b extends ax.p406z3.q<Void, Void, Void> {
        long h;
        ax.p086f3.K i;
        String j;

        private b(long j, ax.p086f3.K k, String str) {
            super(ax.z3.q.e.LOW);
            this.h = j;
            this.i = k;
            this.j = str;
        }

        /* synthetic */ b(long j, ax.p086f3.K k, String str, a aVar) {
            this(j, k, str);
        }

        private void w(C6240o c6240o, String str, AbstractC6239n abstractC6239n) throws C1966j {
            Stack stack = new Stack();
            stack.push(abstractC6239n);
            while (stack.size() > 0) {
                AbstractC6239n abstractC6239n2 = (AbstractC6239n) stack.pop();
                if (abstractC6239n2.o()) {
                    for (AbstractC6239n abstractC6239n3 : c6240o.N(abstractC6239n2)) {
                        if (J.l2(abstractC6239n3.B())) {
                            if (!abstractC6239n3.E().equals(str) && abstractC6239n3.q() < this.h) {
                                c6240o.m(abstractC6239n3);
                            }
                        } else if (abstractC6239n3.isDirectory()) {
                            stack.push(abstractC6239n3);
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            try {
                C6240o c6240oE = C2017x.e(this.i);
                w(c6240oE, J.c2(this.i), c6240oE.a1(this.j));
            } catch (C1966j e) {
                e.printStackTrace();
            }
            synchronized (J.C) {
                J.B.remove(this.i);
            }
            return null;
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
            return (this.c == null || this.a == null || (str = this.b) == null || !d0.B(str) || !d0.B(this.c)) ? false : true;
        }
    }

    public static void W1(ax.p086f3.K k) {
        if (k.b() != 0) {
            X1(ax.W2.h.p(k.b()));
            return;
        }
        X1(ax.p086f3.K.e);
        if (ax.p038c3.j.F().u0()) {
            X1(ax.p086f3.K.f);
        }
    }

    private static void X1(ax.p086f3.K k) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            C6240o c6240oE = C2017x.e(k);
            AbstractC6239n abstractC6239nA1 = c6240oE.a1(c2(k));
            if (abstractC6239nA1.o()) {
                for (AbstractC6239n abstractC6239n : c6240oE.N(abstractC6239nA1)) {
                    String strB = abstractC6239n.B();
                    if ("dirs".equals(strB) || "files".equals(strB)) {
                        if (abstractC6239n.isDirectory()) {
                            Iterator<AbstractC6239n> it = c6240oE.N(abstractC6239n).iterator();
                            while (it.hasNext()) {
                                c6240oE.m(it.next());
                            }
                        } else {
                            c6240oE.m(abstractC6239n);
                        }
                    } else if (!".nomedia".equals(strB)) {
                        c6240oE.m(abstractC6239n);
                    }
                }
                ax.W2.h.R(k).u0();
                ax.W2.h.Z().u0();
                if (k.d() == ax.U2.f.u0 && !c6240oE.c1(abstractC6239nA1)) {
                    synchronized (C) {
                        try {
                            if (!B.contains(k)) {
                                new b(jCurrentTimeMillis, k, k.e(), null).i(new Void[0]);
                                B.add(k);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return;
                }
                if (k.d() == ax.U2.f.t0 && ax.p006a3.Q.B()) {
                    synchronized (C) {
                        try {
                            if (!B.contains(k)) {
                                new b(jCurrentTimeMillis, k, d0.P(k.e(), "/Android/obb"), null).i(new Void[0]);
                                B.add(k);
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
        } catch (C1966j e) {
            e.printStackTrace();
        }
    }

    public static void Y1(ax.p086f3.K k, boolean z2) throws C1966j {
        String strC2 = c2(k);
        C6240o c6240oE = C2017x.e(k);
        C6249y c6249y = (C6249y) c6240oE.a1(strC2);
        if (c6249y.o()) {
            if (z2) {
                ax.p302sb.c.h().g().c("CREATE RECYCLE BIN REPORT DIR EXIST").h(strC2).i();
            }
        } else {
            if (!c6240oE.k(c6249y, false)) {
                throw new C1966j("Could not create Recycle Bin folder!");
            }
            if (z2) {
                ax.p302sb.c.h().g().c("CREATE RECYCLE BIN FOLDER").h(strC2 + ",writable:" + ax.p038c3.j.F().k0(k) + ",useDocumentFile:" + c6249y.j0() + ",loc:" + c6249y.Q().H() + ",location:" + k.toString() + ",baseloc:" + c6249y.C0().toString()).i();
            }
        }
        AbstractC6239n abstractC6239nA1 = c6240oE.a1(d0.Q(strC2, ".nomedia"));
        if (!abstractC6239nA1.o()) {
            c6240oE.o1(abstractC6239nA1);
        }
        AbstractC6239n abstractC6239nA2 = c6240oE.a1(d0.Q(strC2, "dirs"));
        if (!abstractC6239nA2.o()) {
            c6240oE.k(abstractC6239nA2, false);
        }
        AbstractC6239n abstractC6239nA3 = c6240oE.a1(d0.Q(strC2, "files"));
        if (abstractC6239nA3.o()) {
            return;
        }
        c6240oE.k(abstractC6239nA3, false);
    }

    private static ax.p086f3.K Z1(C6249y c6249y) throws C1966j {
        ax.p086f3.K kC0 = c6249y.C0();
        int i = a.a[kC0.d().ordinal()];
        if (i == 1 || i == 2) {
            return kC0;
        }
        ax.p302sb.c.h().g().b("Invalid Recycle Bin Base location").k().h("loc:" + c6249y.S().toString() + ",base:" + kC0.toString()).i();
        throw new C1966j("Invalid base location");
    }

    public static long a2(C6240o c6240o, AbstractC6239n abstractC6239n, Map<String, C6231f.a> map) {
        if (abstractC6239n.isDirectory()) {
            return (map == null || !map.containsKey(abstractC6239n.R())) ? b2(c6240o, abstractC6239n) : map.get(abstractC6239n.R()).c;
        }
        return abstractC6239n.p();
    }

    private static long b2(C6240o c6240o, AbstractC6239n abstractC6239n) {
        Stack stack = new Stack();
        stack.push(abstractC6239n);
        long jP = 0;
        while (!stack.isEmpty()) {
            AbstractC6239n abstractC6239n2 = (AbstractC6239n) stack.pop();
            if (abstractC6239n2.isDirectory()) {
                try {
                    Iterator<AbstractC6239n> it = c6240o.N(abstractC6239n2).iterator();
                    while (it.hasNext()) {
                        stack.push(it.next());
                    }
                } catch (C1966j e) {
                    e.printStackTrace();
                }
            } else {
                jP += abstractC6239n2.p();
            }
        }
        return jP;
    }

    public static String c2(ax.p086f3.K k) {
        if (!d0.B(k.e())) {
            ax.p302sb.c.h().g().b("Not normalized root path in recycle bin :" + k.e()).i();
        }
        return d0.Q(k.e(), ".$recycle_bin$");
    }

    private static boolean f2(AbstractC6239n abstractC6239n) {
        if (abstractC6239n.isDirectory()) {
            return false;
        }
        String[] strArrSplit = abstractC6239n.B().split("_");
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

    public static boolean g2(AbstractC6239n abstractC6239n) {
        if (!ax.p006a3.Q.q() || !ax.p086f3.B.P(abstractC6239n)) {
            return false;
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        if (ax.U2.f.i0(c6249y.Q())) {
            return C6249y.a1(c6249y.D0());
        }
        return false;
    }

    public static boolean h2(AbstractC6239n abstractC6239n) {
        return (abstractC6239n instanceof K) && ((K) abstractC6239n).E1();
    }

    public static boolean i2(AbstractC6239n abstractC6239n) {
        return ax.p006a3.Q.B() && C6248x.R0(abstractC6239n);
    }

    public static boolean j2(AbstractC6239n abstractC6239n) {
        if (ax.p006a3.Q.B() && C6248x.R0(abstractC6239n)) {
            return true;
        }
        return ax.p006a3.Q.L() && C6248x.Q0(abstractC6239n);
    }

    public static boolean k2(AbstractC6239n abstractC6239n) {
        if (!ax.p086f3.B.P(abstractC6239n)) {
            return false;
        }
        if (m2(abstractC6239n.X())) {
            return true;
        }
        return l2(abstractC6239n.B());
    }

    public static boolean l2(String str) {
        return str.startsWith(".$recycle_bin$");
    }

    public static boolean m2(String str) {
        return str != null && d0.H("/.$recycle_bin$", str);
    }

    private boolean n2(AbstractC6239n abstractC6239n) {
        return k2(abstractC6239n) || abstractC6239n.E().contains(".$recycle_bin$");
    }

    private void o2(List<AbstractC6239n> list, ax.p086f3.K k) {
        List<AbstractC6239n> listD1;
        AbstractC6239n abstractC6239nT0 = t0(c2(k), k);
        if (abstractC6239nT0.o()) {
            try {
                listD1 = super.d1(abstractC6239nT0, AbstractC6238m.f.NONE);
            } catch (C1966j unused) {
                listD1 = null;
            }
            if (listD1 == null) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (AbstractC6239n abstractC6239n : listD1) {
                if (!abstractC6239n.isDirectory() && f2(abstractC6239n)) {
                    synchronized (z) {
                        try {
                            K k2 = new K(this, (C6249y) abstractC6239n, k, true);
                            if (k2.D1() != null) {
                                AbstractC6239n abstractC6239nT1 = t0(k2.D1(), k2.C0());
                                if (!n2(abstractC6239nT1)) {
                                    ax.p302sb.c.h().g().b("INVALID RECYCLED FILE").h("location:" + k.toString() + "," + abstractC6239nT1.E()).i();
                                } else if (abstractC6239nT1.o()) {
                                    list.add(k2);
                                } else if (k2.q() < jCurrentTimeMillis - 3600000) {
                                    ax.p302sb.c.h().g().c("RECYCLED FILE DELETED").h("location:" + k.toString()).i();
                                    q1(abstractC6239n);
                                } else {
                                    ax.p302sb.c.h().g().c("RECYCLED FILE SKIPPED").h("location:" + k.toString()).i();
                                }
                            } else if (abstractC6239n.q() < jCurrentTimeMillis - 3600000) {
                                ax.p302sb.c.h().g().c("RECYCLED FILE PATH == null 1-1").h("location:" + k.toString() + ",size:" + abstractC6239n.p() + ",exists:" + abstractC6239n.o() + "," + (jCurrentTimeMillis - abstractC6239n.q())).i();
                                try {
                                    q1(abstractC6239n);
                                } catch (C1966j unused2) {
                                }
                            } else {
                                ax.p302sb.c.h().g().c("RECYCLED FILE PATH == null 1-2").h("location:" + k.toString() + ",size:" + abstractC6239n.p() + ",exists:" + abstractC6239n.o() + "," + (jCurrentTimeMillis - abstractC6239n.q())).i();
                            }
                        } catch (C1966j e) {
                            if (e.getMessage() != null && e.getMessage().startsWith("Invalid RecycleBin Index")) {
                                if (abstractC6239n.q() < jCurrentTimeMillis - 3600000) {
                                    ax.p302sb.c.h().g().c("RECYCLED FILE PATH == null 2-1").h("location:" + k.toString() + ",size:" + abstractC6239n.p() + ",exists:" + abstractC6239n.o() + "," + (jCurrentTimeMillis - abstractC6239n.q())).i();
                                    try {
                                        q1(abstractC6239n);
                                    } catch (C1966j unused3) {
                                    }
                                } else {
                                    ax.p302sb.c.h().g().c("RECYCLED FILE PATH == null 2-2").h("location:" + k.toString() + ",size:" + abstractC6239n.p() + ",exists:" + abstractC6239n.o() + "," + (jCurrentTimeMillis - abstractC6239n.q())).i();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static c p2(AbstractC6239n abstractC6239n, ax.p086f3.K k) throws Throwable {
        C6240o c6240oE = C2017x.e(k);
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(c6240oE.h1(abstractC6239n, 0L)));
                try {
                    String line = bufferedReader2.readLine();
                    String line2 = bufferedReader2.readLine();
                    String line3 = bufferedReader2.readLine();
                    if (line == null && line3 != null) {
                        bufferedReader2.close();
                        bufferedReader2 = new BufferedReader(new InputStreamReader(c6240oE.h1(abstractC6239n, 0L)));
                        line = bufferedReader2.readLine();
                        line2 = bufferedReader2.readLine();
                        line3 = bufferedReader2.readLine();
                    }
                    bufferedReader = bufferedReader2;
                    if (line3 == null || line == null) {
                        long jCurrentTimeMillis = System.currentTimeMillis() - abstractC6239n.q();
                        boolean z2 = jCurrentTimeMillis < 5000 && jCurrentTimeMillis >= 0;
                        if (k != null) {
                            ax.p302sb.c.h().g().d("LOAD RECYCLE INDEX").k().h("exists:" + abstractC6239n.o() + "just mod :" + z2 + ",1:" + line + ",2:" + line2 + ",3:" + line3 + ",base:" + k.toString()).i();
                        } else {
                            ax.p302sb.c.h().g().d("LOAD RECYCLE INDEX").k().h("exists:" + abstractC6239n.o() + "just mod :" + z2 + ",1:" + line + ",2:" + line2 + ",3:" + line3).i();
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
                    throw new C1966j(e);
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

    private static void q2(C6240o c6240o, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C6240o c6240o2;
        InterfaceC2462c interfaceC2462c2;
        ax.p182l3.i iVar2;
        C2461b.c(abstractC6239n.isDirectory());
        if (!abstractC6239n2.o()) {
            if (c6240o.c1(abstractC6239n) || abstractC6239n.U().equals(abstractC6239n2.U())) {
                c6240o.i1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
                return;
            } else {
                if (!c6240o.k(abstractC6239n2, true)) {
                    throw new C1966j("Create folder failed in move directory");
                }
                abstractC6239n2 = c6240o.a1(abstractC6239n2.E());
            }
        }
        if (!abstractC6239n2.isDirectory()) {
            throw new ax.p070e3.u();
        }
        for (AbstractC6239n abstractC6239n3 : c6240o.N(abstractC6239n)) {
            AbstractC6239n abstractC6239nA1 = c6240o.a1(d0.Q(abstractC6239n2.E(), abstractC6239n3.B()));
            if (abstractC6239n3.isDirectory()) {
                q2(c6240o, abstractC6239n3, abstractC6239nA1, interfaceC2462c, iVar);
                c6240o2 = c6240o;
                interfaceC2462c2 = interfaceC2462c;
                iVar2 = iVar;
            } else {
                c6240o2 = c6240o;
                interfaceC2462c2 = interfaceC2462c;
                iVar2 = iVar;
                c6240o2.P(abstractC6239n3, c6240o, abstractC6239nA1, true, interfaceC2462c2, iVar2);
            }
            c6240o = c6240o2;
            interfaceC2462c = interfaceC2462c2;
            iVar = iVar2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:57:0x0148 A[Catch: all -> 0x00d4, TRY_ENTER, TryCatch #3 {, blocks: (B:23:0x005f, B:25:0x0088, B:27:0x00c0, B:29:0x00c4, B:30:0x00c7, B:40:0x00da, B:42:0x00e0, B:46:0x00f2, B:47:0x011a, B:48:0x0122, B:49:0x012a, B:50:0x0132, B:57:0x0148, B:59:0x014e, B:60:0x0151, B:62:0x0157, B:63:0x015e, B:64:0x015f, B:65:0x0213, B:67:0x0215, B:68:0x024a), top: B:79:0x005f, inners: #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x0157 A[Catch: all -> 0x00d4, TryCatch #3 {, blocks: (B:23:0x005f, B:25:0x0088, B:27:0x00c0, B:29:0x00c4, B:30:0x00c7, B:40:0x00da, B:42:0x00e0, B:46:0x00f2, B:47:0x011a, B:48:0x0122, B:49:0x012a, B:50:0x0132, B:57:0x0148, B:59:0x014e, B:60:0x0151, B:62:0x0157, B:63:0x015e, B:64:0x015f, B:65:0x0213, B:67:0x0215, B:68:0x024a), top: B:79:0x005f, inners: #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x015f A[Catch: all -> 0x00d4, TryCatch #3 {, blocks: (B:23:0x005f, B:25:0x0088, B:27:0x00c0, B:29:0x00c4, B:30:0x00c7, B:40:0x00da, B:42:0x00e0, B:46:0x00f2, B:47:0x011a, B:48:0x0122, B:49:0x012a, B:50:0x0132, B:57:0x0148, B:59:0x014e, B:60:0x0151, B:62:0x0157, B:63:0x015e, B:64:0x015f, B:65:0x0213, B:67:0x0215, B:68:0x024a), top: B:79:0x005f, inners: #5, #6 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:64:0x015f, please report this as an issue */
    public static String r2(C6240o c6240o, AbstractC6239n abstractC6239n, long j) throws C1957a, C1966j {
        String strU;
        String strQ;
        AbstractC6239n abstractC6239nA1;
        boolean z2;
        String strQ2;
        AbstractC6239n abstractC6239nA2;
        Boolean boolValueOf;
        Boolean boolValueOf2;
        Boolean boolValueOf3;
        Boolean boolValueOf4;
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zIsDirectory = abstractC6239n.isDirectory();
        String str = zIsDirectory ? "D" : "F";
        String strC2 = c2(Z1((C6249y) abstractC6239n));
        String strE = abstractC6239n.E();
        boolean zC1 = c6240o.c1(abstractC6239n);
        boolean zI2 = i2(abstractC6239n);
        int i = 0;
        if (!zC1 || zI2) {
            strU = abstractC6239n.U();
        } else {
            strU = zIsDirectory ? d0.Q(strC2, "dirs") : d0.Q(strC2, "files");
            AbstractC6239n abstractC6239nA3 = c6240o.a1(strU);
            if (!abstractC6239nA3.o() && !c6240o.k(abstractC6239nA3, false)) {
                throw new C1966j("Could not create recycle bin taret parent!");
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
                strQ = d0.Q(strU, sb.toString());
                abstractC6239nA1 = c6240o.a1(strQ);
                if (!abstractC6239nA1.o()) {
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
                z2 = zIsDirectory;
                sb2.append("_");
                int i4 = i + 1;
                sb2.append(i);
                strQ2 = d0.Q(strC2, sb2.toString());
                abstractC6239nA2 = c6240o.a1(strQ2);
                if (!abstractC6239nA2.o()) {
                    try {
                        break;
                    } catch (C1966j e) {
                        AbstractC6239n abstractC6239nA4 = c6240o.a1(strC2);
                        ax.p302sb.c.h().g().d("FAILED TO WRITE RECYCLEBIN INDEX").m(e).h(strC2 + ", exists=" + abstractC6239nA4.o()).i();
                        throw e;
                    }
                }
                i = i4;
                zIsDirectory = z2;
            }
            u2(c6240o, abstractC6239nA2, strE, strQ);
            Boolean boolValueOf5 = null;
            try {
                c6240o.i1(abstractC6239n, abstractC6239nA1, null, null);
            } catch (C1966j e2) {
                String string = HttpUrl.FRAGMENT_ENCODE_SET;
                try {
                    if (ax.p086f3.B.P(abstractC6239n)) {
                        string = ((C6249y) abstractC6239n).C0().toString();
                    }
                    abstractC6239nA2 = c6240o.a1(strQ2);
                    AbstractC6239n abstractC6239nA5 = c6240o.a1(abstractC6239n.E());
                    AbstractC6239n abstractC6239nA6 = c6240o.a1(abstractC6239nA1.E());
                    AbstractC6239n abstractC6239nA7 = c6240o.a1(d0.r(abstractC6239nA1.E()));
                    boolValueOf = Boolean.valueOf(abstractC6239nA5.o());
                    try {
                        boolValueOf2 = Boolean.valueOf(abstractC6239nA6.o());
                        try {
                            boolValueOf3 = Boolean.valueOf(abstractC6239nA7.o());
                            try {
                                boolValueOf4 = Boolean.valueOf(abstractC6239nA2.o());
                                try {
                                    boolValueOf5 = Boolean.valueOf(i2(abstractC6239n));
                                } catch (Exception unused) {
                                }
                            } catch (Exception unused2) {
                                boolValueOf4 = null;
                            }
                        } catch (Exception unused3) {
                            boolValueOf3 = null;
                            boolValueOf4 = boolValueOf3;
                            if (boolValueOf2 != null && !boolValueOf2.booleanValue()) {
                                c6240o.q1(abstractC6239nA2);
                            }
                            if (g2(abstractC6239n)) {
                                throw new C1961e("Failed to move to recyclebin", e2);
                            }
                            ax.p302sb.c.h().g().d("FAILED MOVE TO RECYCLE BIN AFTER WRITING INDEX FILE").m(e2).h("from:" + abstractC6239n.Q().H() + "," + ((C6249y) abstractC6239n).C0().toString() + ",to:" + abstractC6239nA1.Q().H() + "," + ((C6249y) abstractC6239nA1).C0().toString() + ",base:" + string + ",src:" + boolValueOf + ",dst:" + boolValueOf2 + ",index:" + boolValueOf4 + ",parent:" + boolValueOf3 + ",sdk:" + Build.VERSION.SDK_INT + ",ext:" + abstractC6239n.y() + ",limited:" + boolValueOf5).i();
                            throw new C1966j("Failed to move to recyclebin", e2);
                        }
                    } catch (Exception unused4) {
                        boolValueOf2 = null;
                        boolValueOf3 = boolValueOf2;
                        boolValueOf4 = boolValueOf3;
                        if (boolValueOf2 != null) {
                            c6240o.q1(abstractC6239nA2);
                        }
                        if (g2(abstractC6239n)) {
                            throw new C1961e("Failed to move to recyclebin", e2);
                        }
                        ax.p302sb.c.h().g().d("FAILED MOVE TO RECYCLE BIN AFTER WRITING INDEX FILE").m(e2).h("from:" + abstractC6239n.Q().H() + "," + ((C6249y) abstractC6239n).C0().toString() + ",to:" + abstractC6239nA1.Q().H() + "," + ((C6249y) abstractC6239nA1).C0().toString() + ",base:" + string + ",src:" + boolValueOf + ",dst:" + boolValueOf2 + ",index:" + boolValueOf4 + ",parent:" + boolValueOf3 + ",sdk:" + Build.VERSION.SDK_INT + ",ext:" + abstractC6239n.y() + ",limited:" + boolValueOf5).i();
                        throw new C1966j("Failed to move to recyclebin", e2);
                    }
                } catch (Exception unused5) {
                    boolValueOf = null;
                    boolValueOf2 = null;
                }
                if (boolValueOf2 != null) {
                    c6240o.q1(abstractC6239nA2);
                }
                if (g2(abstractC6239n)) {
                    throw new C1961e("Failed to move to recyclebin", e2);
                }
                ax.p302sb.c.h().g().d("FAILED MOVE TO RECYCLE BIN AFTER WRITING INDEX FILE").m(e2).h("from:" + abstractC6239n.Q().H() + "," + ((C6249y) abstractC6239n).C0().toString() + ",to:" + abstractC6239nA1.Q().H() + "," + ((C6249y) abstractC6239nA1).C0().toString() + ",base:" + string + ",src:" + boolValueOf + ",dst:" + boolValueOf2 + ",index:" + boolValueOf4 + ",parent:" + boolValueOf3 + ",sdk:" + Build.VERSION.SDK_INT + ",ext:" + abstractC6239n.y() + ",limited:" + boolValueOf5).i();
                throw new C1966j("Failed to move to recyclebin", e2);
            }
        }
        if (z2 && !zC1) {
            s2(c6240o, strC2, strE, strQ);
        }
        return abstractC6239nA2.E();
    }

    static void s2(C6240o c6240o, String str, String str2, String str3) {
        try {
            List<AbstractC6239n> listN = c6240o.N(c6240o.a1(str));
            if (listN == null) {
                return;
            }
            Iterator<AbstractC6239n> it = listN.iterator();
            while (it.hasNext()) {
                C6249y c6249y = (C6249y) it.next();
                if (!c6249y.isDirectory() && f2(c6249y)) {
                    try {
                        synchronized (z) {
                            try {
                                c cVarP2 = p2(c6249y, c6249y.C0());
                                String str4 = cVarP2.c;
                                if (str4 != null) {
                                    if (!d0.B(str4)) {
                                        ax.p302sb.c.h().g().b("!! INVALID RECYCLED FILE PATH !!").h("path : " + cVarP2.c).i();
                                    } else if (d0.I(str2, cVarP2.c)) {
                                        String strP = d0.P(str3, d0.n(str2, cVarP2.c));
                                        c6240o.q1(c6249y);
                                        u2(c6240o, c6249y, cVarP2.b, strP);
                                    }
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    } catch (C1966j e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (C1966j e2) {
            ax.p302sb.c.h().d("CANT GET RECYCLE BIN INDEX DIR").h(e2.getMessage()).i();
            e2.printStackTrace();
        }
    }

    public static void t2(C6240o c6240o, K k, ax.Z2.j.f fVar, InterfaceC2462c interfaceC2462c) throws C1957a, C1966j {
        C6240o c6240o2;
        AbstractC6239n abstractC6239nA1 = c6240o.a1(k.D1());
        AbstractC6239n abstractC6239nA2 = c6240o.a1(k.C1());
        AbstractC6239n abstractC6239nA3 = c6240o.a1(k.B1());
        if (!abstractC6239nA1.o()) {
            c6240o.q1(abstractC6239nA3);
            throw new C1966j("Recycled file does not exist");
        }
        if (abstractC6239nA2.o()) {
            if (fVar == ax.Z2.j.f.RENAME) {
                abstractC6239nA2 = c6240o.F(abstractC6239nA2, false);
            } else if (fVar != ax.Z2.j.f.OVERWRITE) {
                throw new C1962f(abstractC6239nA2.isDirectory() != abstractC6239nA1.isDirectory());
            }
        }
        AbstractC6239n abstractC6239n = abstractC6239nA2;
        AbstractC6239n abstractC6239nA4 = c6240o.a1(abstractC6239n.U());
        if (!abstractC6239nA4.o() && !c6240o.k(abstractC6239nA4, true)) {
            throw new C1966j("Create parent failed");
        }
        boolean zIsDirectory = k.isDirectory();
        if (zIsDirectory) {
            q2(c6240o, abstractC6239nA1, abstractC6239n, interfaceC2462c, null);
            c6240o2 = c6240o;
        } else {
            c6240o2 = c6240o;
            c6240o2.P(abstractC6239nA1, c6240o, abstractC6239n, true, interfaceC2462c, null);
        }
        synchronized (z) {
            c6240o2.q1(abstractC6239nA3);
        }
        if (!zIsDirectory || c6240o2.c1(k)) {
            return;
        }
        s2(c6240o2, c2(Z1(k)), k.D1(), k.C1());
    }

    private static void u2(C6240o c6240o, AbstractC6239n abstractC6239n, String str, String str2) throws C1966j {
        byte[] bytes = ("1\n" + str + "\n" + str2).getBytes(Charset.forName("UTF-8"));
        try {
            ((C6248x) c6240o.u()).l1(abstractC6239n, ax.p086f3.F.e(bytes), "text/plain", bytes.length, null, null, true, null, null);
        } catch (C1957a unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        AbstractC6239n abstractC6239nA1 = a1(str2);
        if (!f2(abstractC6239nA1)) {
            return super.b1(str, str2, str3);
        }
        try {
            K kD2 = d2(abstractC6239nA1);
            String strD1 = kD2.D1();
            String strF = d0.f(kD2.C1());
            String strF2 = C2015v.f(strF);
            if (strF2 == null) {
                strF2 = "application/octet-stream";
            }
            return L0(strD1, strF, strF2);
        } catch (C1966j unused) {
            return ax.p358w3.b.n();
        }
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!d0.E(abstractC6239n)) {
            return super.d1(abstractC6239n, fVar);
        }
        ArrayList arrayList = new ArrayList();
        if (t() != 0) {
            o2(arrayList, ax.W2.h.p(t()));
            return arrayList;
        }
        o2(arrayList, ax.p086f3.K.e);
        if (ax.p038c3.j.F().u0()) {
            try {
                o2(arrayList, ax.p086f3.K.f);
                return arrayList;
            } catch (IllegalArgumentException e) {
                ax.U2.d.c("recyclebin sdcard root", e);
            }
        }
        return arrayList;
    }

    public K d2(AbstractC6239n abstractC6239n) throws C1966j {
        K k;
        if (!f2(abstractC6239n)) {
            throw new C1966j("invalid recycle bin index path");
        }
        synchronized (z) {
            k = new K(this, (C6249y) abstractC6239n, null, true);
        }
        return k;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        AbstractC6239n abstractC6239nA1 = a1(str2);
        if (!f2(abstractC6239nA1)) {
            return super.e1(str, str2);
        }
        try {
            K kD2 = d2(abstractC6239nA1);
            String strD1 = kD2.D1();
            String strF = C2015v.f(d0.f(kD2.C1()));
            if (strF == null) {
                strF = "application/octet-stream";
            }
            return K0(str, strD1, strF);
        } catch (C1966j unused) {
            return -1;
        }
    }

    public K e2(String str) throws C1966j {
        return d2(a1(str));
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return null;
        }
        return h2(abstractC6239n) ? ax.p086f3.B.X(abstractC6239n) : ax.p086f3.B.W(abstractC6239n);
    }
}
