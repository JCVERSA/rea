package com.alphainventor.filemanager.file;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import ax.p070e3.C1966j;
import ax.p086f3.C2017x;
import ax.p086f3.d0;
import ax.p406z3.C2461b;
import j$.util.DesugarCollections;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6247w extends ax.p406z3.q<Void, Void, Boolean> {
    private static final Logger o = Logger.getLogger("FileManager.LibraryScanTask");
    private static HashSet<String> p = new HashSet<>();
    private static HashSet<String> q = new HashSet<>();
    private static HashSet<String> r = new HashSet<>();
    private static HashSet<String> s = new HashSet<>();
    static C6247w t;
    static final Object u;
    private static int v;
    private HashMap<ax.U2.f, f> h;
    private HashMap<String, Boolean> i;
    private final List<d> j;
    private Context k;
    private boolean l;
    private boolean m;
    private boolean n;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.w$a */
    class a implements Comparator<AbstractC6239n> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
            return abstractC6239n2.E().compareTo(abstractC6239n.E());
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.w$b */
    class b implements Comparator<AbstractC6239n> {
        b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
            return abstractC6239n.E().compareTo(abstractC6239n2.E());
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.w$c */
    static class c {
        String a;
        Set<String> b = new HashSet();
        ax.p086f3.K c;
        C6240o d;
        File e;
        HashMap<String, List<A>> f;
        ArrayList<AbstractC6239n> g;
        boolean h;

        c(ax.p086f3.K k, String str, File file, boolean z) {
            this.c = k;
            this.d = C2017x.e(k);
            this.a = str;
            this.e = file;
            this.h = z;
        }

        void a(AbstractC6239n abstractC6239n) {
            this.g.add(abstractC6239n);
        }

        void b() {
            ArrayList<AbstractC6239n> arrayList = this.g;
            if (arrayList != null) {
                arrayList.clear();
                this.g = null;
            }
            c();
        }

        void c() {
            HashMap<String, List<A>> map = this.f;
            if (map != null) {
                map.clear();
                this.f = null;
            }
        }

        List<A> d(String str) {
            return this.f.get(str);
        }

        void e() {
            this.f = new HashMap<>();
            this.g = new ArrayList<>();
        }

        boolean f() {
            return this.f != null;
        }

        boolean g() {
            return this.g != null;
        }

        void h(String str, List<A> list) {
            this.f.put(str, list);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.w$d */
    public interface d {
        void a(boolean z);

        void b(HashMap<ax.U2.f, f> map);
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.w$e */
    public static class e {
        public ax.p086f3.K a;
        public C6249y c;
        public Boolean d;
        public String h;
        public boolean b = false;
        public long e = 0;
        public int f = 0;
        public long g = 0;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.w$f */
    public static class f {
        public HashMap<String, e> a = new HashMap<>();
        public long b = 0;
        public int c = 0;
        public long d = 0;
        public long e = 0;
    }

    static {
        p.add("/Android/data/com.utorrent.client/files/Download");
        p.add("/Android/data/com.bittorrent.client/files/Download");
        p.add("/Android/data/com.android.chrome/files/Download");
        p.add("/Android/data/org.telegram.messenger/files/Telegram");
        q.add("mobiletmoney.txt");
        q.add("log.txt");
        q.add("log");
        r.add("/Android/media/com.google.android.gm/Notifications");
        r.add("/Android/media/com.google.android.talk/Notifications");
        r.add("/Android/media/com.google.android.talk/Ringtones");
        s.add("/Documents/Notebloc");
        u = new Object();
    }

    public C6247w(Context context, boolean z) {
        super(ax.z3.q.e.LOW);
        this.j = DesugarCollections.synchronizedList(new ArrayList());
        this.k = context.getApplicationContext();
        this.m = z;
    }

    private static void A(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        file.delete();
    }

    public static C6247w C(Context context, d dVar) {
        try {
            return D(context.getApplicationContext(), dVar, false);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    private static C6247w D(Context context, d dVar, boolean z) {
        C6247w c6247w;
        synchronized (u) {
            try {
                C6247w c6247w2 = t;
                if (c6247w2 == null || c6247w2.m() == ax.z3.q.f.FINISHED) {
                    o.fine("Execute scan task");
                    C6247w c6247w3 = new C6247w(context, z);
                    t = c6247w3;
                    c6247w3.i(new Void[0]);
                }
                if (dVar != null) {
                    t.w(dVar);
                }
                c6247w = t;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c6247w;
    }

    private boolean E(AbstractC6239n abstractC6239n) {
        return q.contains(abstractC6239n.B().toLowerCase());
    }

    private boolean F(AbstractC6239n abstractC6239n) {
        String lowerCase = abstractC6239n.B().toLowerCase();
        String strF = d0.f(lowerCase);
        if ("log".equals(strF) || lowerCase.endsWith("_log.txt") || lowerCase.endsWith("_logs.txt")) {
            return true;
        }
        return (lowerCase.startsWith("filelog") && "txt".equals(strF)) || q.contains(lowerCase);
    }

    public static Set<String> G() {
        return r;
    }

    static File H(Context context, boolean z) {
        File fileQ = ax.U2.e.q(context);
        if (fileQ != null) {
            return z ? new File(fileQ.getAbsolutePath(), "scanfile.full") : new File(fileQ.getAbsolutePath(), "scanfile.fast");
        }
        throw new IllegalStateException("Can not create index file");
    }

    static File I(Context context) {
        File fileQ = ax.U2.e.q(context);
        if (fileQ != null) {
            return new File(fileQ.getAbsolutePath(), "scanfile_new.full");
        }
        throw new IllegalStateException("Can not create index file");
    }

    static File J(Context context, boolean z) {
        File fileQ = ax.U2.e.q(context);
        if (fileQ != null) {
            return z ? new File(fileQ.getAbsolutePath(), "scanfile_sd.full") : new File(fileQ.getAbsolutePath(), "scanfile_sd.fast");
        }
        throw new IllegalStateException("Can not create index file");
    }

    private void K(c cVar, AbstractC6239n abstractC6239n, BufferedWriter bufferedWriter, ax.p038c3.i iVar) throws IOException {
        List<AbstractC6239n> listT;
        String[] strArrSplit;
        String strDecode;
        int iCompareTo;
        if (((C6249y) abstractC6239n).y0()) {
            try {
                listT = T(cVar, abstractC6239n, true);
            } catch (C1966j unused) {
                listT = null;
            } catch (OutOfMemoryError unused2) {
                ax.p302sb.c.h().g().d("SCAN: listFiles OUT OF MEMORY 2").i();
                listT = null;
            }
            if (listT == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (AbstractC6239n abstractC6239n2 : listT) {
                if (!abstractC6239n2.isDirectory()) {
                    arrayList2.add(abstractC6239n2);
                } else if (!d0.C(abstractC6239n2.B()) && !abstractC6239n.E().equals(abstractC6239n2.E())) {
                    arrayList.add(abstractC6239n2);
                }
            }
            Collections.sort(arrayList, new b());
            e0(cVar, abstractC6239n, arrayList2, bufferedWriter);
            if (arrayList.size() == 0) {
                if (d0.B(abstractC6239n.E())) {
                    z(abstractC6239n, iVar);
                    return;
                }
                ax.p302sb.c.h().g().b("INVALID LIBRARYSCAN DIR ABSOULTE PATH").h("dir:" + abstractC6239n.E()).i();
                return;
            }
            String strC = iVar.c();
            int i = 0;
            while (i < arrayList.size()) {
                AbstractC6239n abstractC6239n3 = (AbstractC6239n) arrayList.get(i);
                String strE = abstractC6239n3.E();
                if (!J.k2(abstractC6239n3)) {
                    if (strC == null) {
                        iCompareTo = -1;
                        strArrSplit = null;
                        strDecode = null;
                    } else {
                        strArrSplit = strC.split("\u0000");
                        strDecode = Uri.decode(strArrSplit[0]);
                        iCompareTo = strE.compareTo(strDecode);
                    }
                    if (iCompareTo == 0) {
                        if (ax.p006a3.Q.h1() && abstractC6239n3.q() == 0) {
                            ((C6249y) abstractC6239n3).x1();
                            abstractC6239n3.q();
                        }
                        if (Long.parseLong(strArrSplit[1]) == abstractC6239n3.q()) {
                            bufferedWriter.write(strC + "\n");
                            X(cVar, strDecode, (C6249y) abstractC6239n3, strArrSplit);
                            iVar.a();
                            N(cVar, strDecode, bufferedWriter, iVar);
                            strC = iVar.c();
                        } else {
                            iVar.a();
                            K(cVar, abstractC6239n3, bufferedWriter, iVar);
                            strC = iVar.c();
                        }
                    } else if (iCompareTo < 0) {
                        M(cVar, abstractC6239n3, bufferedWriter);
                    } else {
                        iVar.a();
                        strC = iVar.c();
                    }
                }
                i++;
            }
            z(abstractC6239n, iVar);
        }
    }

    private void L(c cVar, Stack<AbstractC6239n> stack, Writer writer) throws IOException {
        List<AbstractC6239n> listT;
        AbstractC6239n abstractC6239nPop = stack.pop();
        if (cVar.b.contains(abstractC6239nPop.E()) || J.l2(abstractC6239nPop.B())) {
            return;
        }
        try {
            listT = T(cVar, abstractC6239nPop, writer != null);
        } catch (C1966j unused) {
            listT = null;
        } catch (OutOfMemoryError unused2) {
            ax.p302sb.c.h().g().d("SCAN: listFiles OUT OF MEMORY").i();
            listT = null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (listT == null) {
            e0(cVar, abstractC6239nPop, arrayList2, writer);
            return;
        }
        for (int i = 0; i < listT.size(); i++) {
            AbstractC6239n abstractC6239n = listT.get(i);
            if (!Q(cVar, abstractC6239n)) {
                arrayList2.add(abstractC6239n);
            } else if (!d0.C(abstractC6239n.B())) {
                arrayList.add(abstractC6239n);
            }
        }
        e0(cVar, abstractC6239nPop, arrayList2, writer);
        Collections.sort(arrayList, new a());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            stack.push((AbstractC6239n) arrayList.get(i2));
        }
    }

    private void M(c cVar, AbstractC6239n abstractC6239n, Writer writer) throws IOException {
        Stack<AbstractC6239n> stack = new Stack<>();
        stack.push(abstractC6239n);
        while (!stack.isEmpty()) {
            L(cVar, stack, writer);
        }
    }

    private void N(c cVar, String str, BufferedWriter bufferedWriter, ax.p038c3.i iVar) throws IOException {
        Stack<String> stack = new Stack<>();
        stack.push(str);
        while (!stack.isEmpty()) {
            O(cVar, stack, bufferedWriter, iVar);
        }
    }

    private void O(c cVar, Stack<String> stack, BufferedWriter bufferedWriter, ax.p038c3.i iVar) throws IOException {
        String strPop = stack.pop();
        while (true) {
            String strC = iVar.c();
            if (strC == null) {
                return;
            }
            String[] strArrSplit = strC.split("\u0000");
            String strDecode = Uri.decode(strArrSplit[0]);
            if (!d0.B(strDecode)) {
                ax.p302sb.c.h().g().d("ISCD").h(strDecode + ":" + strC.length() + ":" + strC).i();
                iVar.a();
            } else {
                if (!d0.r(strDecode).equals(strPop) && strPop != null) {
                    return;
                }
                try {
                    C6249y c6249y = (C6249y) cVar.d.a1(strDecode);
                    if (c6249y.q() == Long.parseLong(strArrSplit[1])) {
                        bufferedWriter.write(strC + "\n");
                        X(cVar, strDecode, c6249y, strArrSplit);
                        iVar.a();
                        stack.push(strPop);
                        stack.push(strDecode);
                        return;
                    }
                    iVar.a();
                    K(cVar, c6249y, bufferedWriter, iVar);
                } catch (C1966j e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:45:0x00e6 A[Catch: IOException -> 0x00e9, TRY_LEAVE, TryCatch #0 {IOException -> 0x00e9, blocks: (B:43:0x00e1, B:45:0x00e6), top: B:49:0x00e1 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void P(c cVar, AbstractC6239n abstractC6239n) throws Throwable {
        BufferedWriter bufferedWriter;
        String line;
        BufferedReader bufferedReader = null;
        try {
            if (!abstractC6239n.isDirectory()) {
                String str = "location:" + cVar.c + "," + abstractC6239n.E() + "," + cVar.c.e() + "," + abstractC6239n.o();
                if (abstractC6239n.o()) {
                    ax.p302sb.c.h().g().b("SCAN ROOT IS NOT DIR").h(str).i();
                }
            }
            File file = cVar.e;
            File file2 = new File(cVar.e.getAbsolutePath() + ".tmp");
            BufferedReader bufferedReader2 = file.exists() ? new BufferedReader(new FileReader(file)) : null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file2));
                if (bufferedReader2 != null) {
                    try {
                        line = bufferedReader2.readLine();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                                if (bufferedWriter != null) {
                                    bufferedWriter.close();
                                }
                            } catch (IOException unused) {
                                throw th;
                            }
                        } else if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw th;
                    }
                } else {
                    line = null;
                }
                if (!"6".equals(line) && bufferedReader2 != null) {
                    bufferedReader2.close();
                    try {
                        A(file);
                        bufferedReader2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                        } else if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw th;
                    }
                }
                bufferedWriter.write("6\n");
                if (bufferedReader2 != null) {
                    bufferedReader2.readLine();
                }
                K(cVar, abstractC6239n, bufferedWriter, new ax.p038c3.i(bufferedReader2));
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                    bufferedReader2 = null;
                }
                bufferedWriter.close();
                A(file);
                file2.renameTo(file);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused2) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter = null;
        }
    }

    private boolean Q(c cVar, AbstractC6239n abstractC6239n) {
        if (!cVar.h) {
            String strB = abstractC6239n.B();
            if (strB.length() > 4 && (strB.charAt(strB.length() - 3) == '.' || strB.charAt(strB.length() - 4) == '.')) {
                return false;
            }
        }
        return abstractC6239n.isDirectory();
    }

    private boolean R(Context context) {
        File fileH = H(context, true);
        File fileJ = J(context, true);
        boolean z = fileH.exists() && fileH.length() > 0;
        boolean z2 = fileJ.exists() && fileJ.length() > 0;
        if (ax.p038c3.j.F().u0()) {
            return z && z2;
        }
        return z;
    }

    public static boolean S(String str) {
        if (p.contains(str)) {
            return true;
        }
        return p.contains(d0.q(str, 4));
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0064  */
    private List<AbstractC6239n> T(c cVar, AbstractC6239n abstractC6239n, boolean z) throws C1966j {
        boolean z2;
        if (ax.p006a3.Q.h1() && z) {
            String strE = abstractC6239n.E();
            if (cVar.a.equals(strE)) {
                z2 = false;
            } else {
                if (!cVar.f()) {
                    x(cVar, false);
                }
                List<A> listD = cVar.d(strE);
                if (listD == null || listD.size() <= 0) {
                    z2 = false;
                } else {
                    if (!s.contains(abstractC6239n.X())) {
                        ArrayList arrayList = new ArrayList();
                        C6248x c6248x = (C6248x) cVar.d.u();
                        for (int i = 0; i < listD.size(); i++) {
                            arrayList.add(c6248x.i0(cVar.c, listD.get(i)));
                        }
                        return arrayList;
                    }
                    z2 = true;
                }
            }
        } else {
            z2 = false;
        }
        if (!((C6249y) abstractC6239n).x0() || !abstractC6239n.isDirectory()) {
            return null;
        }
        List<AbstractC6239n> listN = cVar.d.N(abstractC6239n);
        if (z2 && listN != null) {
            HashSet hashSet = new HashSet();
            List<A> listD2 = cVar.d(abstractC6239n.E());
            if (listD2 != null) {
                Iterator<A> it = listD2.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().a);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            long jC = ax.p086f3.O.c();
            int iA = 5000;
            for (AbstractC6239n abstractC6239n2 : listN) {
                if (!abstractC6239n2.g() && !abstractC6239n2.isDirectory() && !hashSet.contains(abstractC6239n2.E())) {
                    A a2 = new A(abstractC6239n2.E(), abstractC6239n2.isDirectory(), abstractC6239n2.q(), null, abstractC6239n2.q(), abstractC6239n2.p());
                    if (listD2 == null) {
                        listD2 = new ArrayList<>();
                        cVar.h(abstractC6239n.E(), listD2);
                    }
                    listD2.add(a2);
                    if (abstractC6239n2.q() >= jC) {
                        cVar.a(abstractC6239n2);
                    }
                    if (iA < 196608) {
                        ax.p310t3.f fVar = new ax.p310t3.f(abstractC6239n2.E(), abstractC6239n2.Q().J(), abstractC6239n2.M(), abstractC6239n2.q(), false, false, false, false, false, false);
                        arrayList2.add(fVar);
                        iA += fVar.a();
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                C6248x.P1(this.k, arrayList2, false, iA);
            }
        }
        return listN;
    }

    public static void W(Context context) {
        try {
            A(H(context, false));
            A(H(context, true));
            A(J(context, false));
            A(J(context, true));
            A(I(context));
        } catch (IllegalStateException unused) {
        }
    }

    private void X(c cVar, String str, C6249y c6249y, String[] strArr) {
        Boolean boolValueOf = null;
        for (int i = 2; i < strArr.length; i++) {
            String str2 = strArr[i];
            String[] strArrSplit = str2.split("/");
            if (i != 2 || strArrSplit.length >= 2) {
                if (i != 3 || strArrSplit.length >= 2) {
                    f fVar = this.h.get(ax.U2.f.valueOf(strArrSplit[0]));
                    if (fVar == null) {
                        ax.p302sb.c.h().g().b("NULL SCANINFO").h("loc:" + str2).i();
                    } else {
                        e eVar = new e();
                        eVar.f = Integer.valueOf(strArrSplit[1]).intValue();
                        eVar.e = Long.valueOf(strArrSplit[2]).longValue();
                        eVar.g = Long.valueOf(strArrSplit[3]).longValue();
                        eVar.h = Uri.decode(strArrSplit[4]);
                        eVar.b = false;
                        eVar.a = cVar.c;
                        eVar.c = c6249y;
                        eVar.d = boolValueOf;
                        fVar.a.put(c6249y.E(), eVar);
                    }
                } else if ("true".equalsIgnoreCase(str2) || "false".equalsIgnoreCase(str2)) {
                    this.i.put(str, Boolean.valueOf(str2));
                } else {
                    "null".equalsIgnoreCase(str2);
                }
            } else if ("true".equalsIgnoreCase(str2) || "false".equalsIgnoreCase(str2)) {
                boolValueOf = Boolean.valueOf(str2);
            } else if ("null".equalsIgnoreCase(str2)) {
                boolValueOf = null;
            }
        }
    }

    private void Y(String str, boolean z, ArrayList<AbstractC6239n> arrayList) {
        c cVar = new c(ax.p086f3.K.e, str, H(this.k, z), z);
        cVar.b.add(str + "/Android/data");
        cVar.b.add(str + "/.localcache");
        cVar.b.add(str + "/Android/media/com.alphainventor.filemanager/.localcache");
        try {
            b0(cVar, cVar.d.a1(cVar.a), arrayList);
        } catch (C1966j unused) {
            C2461b.f();
        }
    }

    private void Z(HashMap<String, C6246v> map, List<AbstractC6239n> list) {
        File fileI = I(this.k);
        C6240o c6240oD = C2017x.d(ax.U2.f.K0, 0);
        c6240oD.V();
        ax.p086f3.O o2 = new ax.p086f3.O((B) c6240oD.u());
        try {
            o2.a(fileI, map, list);
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                A(fileI);
                o2.a(fileI, map, list);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            ax.p302sb.c.h().d("NEWSCAN1:").m(e4).i();
            A(fileI);
            o2.a(fileI, map, list);
        }
        c6240oD.S(false);
    }

    private void a0(String str, boolean z, ArrayList<AbstractC6239n> arrayList) {
        c cVar = new c(ax.p086f3.K.f, str, J(this.k, z), z);
        cVar.b.add(str + "/Android/data");
        try {
            b0(cVar, cVar.d.a1(cVar.a), arrayList);
        } catch (C1966j unused) {
            C2461b.f();
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0068  */
    /* JADX WARN: Code duplicated, block: B:57:0x0182 A[DONT_GENERATE] */
    private void b0(c cVar, AbstractC6239n abstractC6239n, ArrayList<AbstractC6239n> arrayList) {
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                P(cVar, abstractC6239n);
                                if (!ax.p006a3.Q.L() || cVar.c != ax.p086f3.K.f) {
                                    d0(cVar, abstractC6239n);
                                }
                                if (ax.p006a3.Q.h1()) {
                                    cVar.c();
                                }
                            } catch (Throwable th) {
                                if (ax.p006a3.Q.h1()) {
                                    cVar.c();
                                }
                                throw th;
                            }
                        } catch (StackOverflowError e2) {
                            e2.printStackTrace();
                            ax.p302sb.c.h().d("SCANSTACK!!!").m(e2).h("location:" + cVar.c.toString()).i();
                            if (ax.p006a3.Q.h1()) {
                                cVar.c();
                            }
                            o.severe("Retry Incremental Scan.");
                            try {
                                cVar.b();
                                A(cVar.e);
                                P(cVar, abstractC6239n);
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                o.severe("Incremental Scan Failed.");
                            } catch (IndexOutOfBoundsException e4) {
                                e4.printStackTrace();
                                ax.p302sb.c.h().d("OOB2").m(e4).h("location:" + cVar.c.toString()).i();
                                o.severe("Incremental Scan Failed.");
                            } finally {
                                if (ax.p006a3.Q.h1()) {
                                    cVar.c();
                                }
                            }
                        }
                    } catch (IndexOutOfBoundsException e5) {
                        e5.printStackTrace();
                        ax.p302sb.c.h().d("LST1:").m(e5).h("location:" + cVar.c.toString()).i();
                        if (ax.p006a3.Q.h1()) {
                            cVar.c();
                        }
                        o.severe("Retry Incremental Scan.");
                        cVar.b();
                        A(cVar.e);
                        P(cVar, abstractC6239n);
                    }
                } catch (IllegalArgumentException e6) {
                    e6.printStackTrace();
                    ax.p302sb.c.h().d("LST4").m(e6).h("location:" + cVar.c.toString()).i();
                    if (ax.p006a3.Q.h1()) {
                        cVar.c();
                    }
                    o.severe("Retry Incremental Scan.");
                    cVar.b();
                    A(cVar.e);
                    P(cVar, abstractC6239n);
                }
            } catch (NumberFormatException e7) {
                e7.printStackTrace();
                ax.p302sb.c.h().d("LST3").m(e7).h("location:" + cVar.c.toString()).i();
                if (ax.p006a3.Q.h1()) {
                    cVar.c();
                }
                o.severe("Retry Incremental Scan.");
                cVar.b();
                A(cVar.e);
                P(cVar, abstractC6239n);
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            if (ax.p006a3.Q.h1()) {
                cVar.c();
            }
            o.severe("Retry Incremental Scan.");
            cVar.b();
            A(cVar.e);
            P(cVar, abstractC6239n);
        } catch (IllegalStateException e9) {
            e9.printStackTrace();
            if (TextUtils.isEmpty(e9.getMessage())) {
                ax.p302sb.c.h().d("LST2:").m(e9).h("location:" + cVar.c.toString()).i();
            } else {
                ax.p302sb.c.h().d("LST2-2").h(e9.getMessage()).i();
            }
            if (ax.p006a3.Q.h1()) {
                cVar.c();
            }
            o.severe("Retry Incremental Scan.");
            cVar.b();
            A(cVar.e);
            P(cVar, abstractC6239n);
        }
        if (ax.p006a3.Q.h1()) {
            if (!cVar.g()) {
                x(cVar, true);
            }
            ArrayList<AbstractC6239n> arrayList2 = cVar.g;
            if (arrayList2 != null) {
                arrayList.addAll(arrayList2);
            } else {
                C2461b.f();
            }
            cVar.b();
        }
    }

    private void c0(ax.p086f3.K k, f fVar, HashMap<String, C6246v> map, HashMap<String, C6249y.b> map2, HashMap<String, Set<String>> map3) {
        boolean z;
        String strF1;
        C6240o c6240oE = C2017x.e(k);
        c6240oE.V();
        boolean z2 = false;
        try {
            AbstractC6239n abstractC6239nA1 = c6240oE.a1(k.e());
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(fVar.a.values());
            C6245u c6245u = (C6245u) c6240oE.u();
            ax.p038c3.b.k().a(arrayList2.size());
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                e eVar = (e) obj;
                C6246v c6246v = (C6246v) c6245u.h0(eVar.c.G0(), eVar.a, z2);
                if (eVar.c.isDirectory()) {
                    long j = eVar.g;
                    if (j != 0) {
                        c6246v.J1(j);
                        c6246v.K1(eVar.h);
                        c6246v.x1();
                    }
                    c6246v.e0(eVar.f);
                    c6246v.m1(C6246v.G1(c6246v, eVar, this.i.get(eVar.c.U()), map2, map3));
                    if (eVar.b && (strF1 = c6240oE.f1(c6246v)) != null) {
                        ax.p358w3.d.B(this.k, strF1);
                    }
                }
                if (!c6246v.g()) {
                    fVar.b += eVar.e;
                    fVar.c += eVar.f;
                    if (ax.p086f3.K.e.equals(c6246v.C0())) {
                        fVar.d += eVar.e;
                    } else {
                        fVar.e += eVar.e;
                    }
                    map.put(eVar.c.E(), c6246v);
                }
                c6246v.t1();
                if (c6246v.o()) {
                    arrayList.add(c6246v);
                }
                abstractC6239nA1 = abstractC6239nA1;
                z2 = false;
            }
            ax.p038c3.b.k().m(abstractC6239nA1, arrayList);
            arrayList2.clear();
            z = false;
        } catch (C1966j e2) {
            e2.printStackTrace();
            z = false;
        } finally {
            c6240oE.S(false);
        }
    }

    private void d0(c cVar, AbstractC6239n abstractC6239n) {
        Iterator<String> it = p.iterator();
        while (it.hasNext()) {
            try {
                AbstractC6239n abstractC6239nA1 = cVar.d.a1(d0.P(abstractC6239n.E(), it.next()));
                if (abstractC6239nA1.o()) {
                    try {
                        M(cVar, abstractC6239nA1, null);
                    } catch (IOException unused) {
                    }
                }
            } catch (C1966j e2) {
                e2.printStackTrace();
                C2461b.e("whiltelist scan failed");
            }
        }
    }

    private void e0(c cVar, AbstractC6239n abstractC6239n, List<AbstractC6239n> list, Writer writer) throws IOException {
        e eVar;
        HashMap map = new HashMap();
        Boolean boolValueOf = null;
        Boolean boolValueOf2 = null;
        for (AbstractC6239n abstractC6239n2 : list) {
            String strB = abstractC6239n2.B();
            ax.U2.f fVarL = ax.p086f3.A.l(ax.p086f3.A.g(d0.f(strB)));
            if (fVarL != ax.U2.f.H0 || (!F(abstractC6239n2) && !E(abstractC6239n))) {
                if (fVarL != null) {
                    if (map.containsKey(fVarL)) {
                        eVar = (e) map.get(fVarL);
                    } else {
                        eVar = new e();
                        map.put(fVarL, eVar);
                        eVar.c = (C6249y) abstractC6239n;
                        eVar.a = cVar.c;
                    }
                    try {
                        eVar.e += abstractC6239n2.p();
                        eVar.f++;
                        long jQ = abstractC6239n2.q();
                        if (eVar.g < jQ && !abstractC6239n2.g()) {
                            eVar.g = jQ;
                            eVar.h = abstractC6239n2.B();
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                } else if (".nomedia".equals(strB)) {
                    boolValueOf = Boolean.TRUE;
                } else if (".hidden".equals(strB)) {
                    boolValueOf2 = Boolean.TRUE;
                }
            }
        }
        if (ax.p006a3.Q.h1()) {
            try {
                boolValueOf = Boolean.valueOf(((C6249y) cVar.d.a1(d0.Q(abstractC6239n.E(), ".nomedia"))).y0());
                boolValueOf2 = Boolean.valueOf(((C6249y) cVar.d.a1(d0.Q(abstractC6239n.E(), ".hidden"))).y0());
            } catch (C1966j e2) {
                e2.printStackTrace();
                C2461b.f();
            }
        } else {
            if (boolValueOf == null) {
                boolValueOf = Boolean.FALSE;
            }
            if (boolValueOf2 == null) {
                boolValueOf2 = Boolean.FALSE;
            }
        }
        if (boolValueOf2 != null) {
            this.i.put(abstractC6239n.E(), boolValueOf2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Uri.encode(abstractC6239n.E()));
        stringBuffer.append("\u0000");
        stringBuffer.append(String.valueOf(abstractC6239n.q()));
        stringBuffer.append("\u0000");
        if (boolValueOf == null) {
            stringBuffer.append("null");
        } else {
            stringBuffer.append(String.valueOf(boolValueOf));
        }
        stringBuffer.append("\u0000");
        if (boolValueOf2 == null) {
            stringBuffer.append("null");
        } else {
            stringBuffer.append(String.valueOf(boolValueOf2));
        }
        for (ax.U2.f fVar : map.keySet()) {
            e eVar2 = (e) map.get(fVar);
            stringBuffer.append("\u0000");
            stringBuffer.append(fVar.name());
            stringBuffer.append("/");
            stringBuffer.append(eVar2.f);
            stringBuffer.append("/");
            stringBuffer.append(eVar2.e);
            stringBuffer.append("/");
            stringBuffer.append(eVar2.g);
            stringBuffer.append("/");
            stringBuffer.append(Uri.encode(eVar2.h));
            eVar2.b = true;
            this.h.get(fVar).a.put(abstractC6239n.E(), eVar2);
            eVar2.d = boolValueOf;
        }
        stringBuffer.append("\n");
        if (writer != null) {
            writer.write(stringBuffer.toString());
        }
    }

    private void x(c cVar, boolean z) {
        System.currentTimeMillis();
        try {
            cVar.e();
            long jC = ax.p086f3.O.c();
            String strD = cVar.d.D();
            C6248x c6248x = (C6248x) cVar.d.u();
            C6249y c6249y = (C6249y) c6248x.a1(strD);
            if (!c6249y.isDirectory()) {
                if (ax.p006a3.Q.A0() && !ax.p406z3.o.c()) {
                    return;
                }
                if (!"/sdcard".equals(strD) && !"/storage/emulated/0".equals(strD)) {
                    ax.p302sb.c.h().g().b("LIBRARY ROOT IS NOT DIRECTORY").h("rootPath: " + strD + "," + cVar.c).i();
                    return;
                }
            }
            List<A> listZ0 = c6248x.Z0(c6249y, true, z ? jC : 0L, 0L);
            if (listZ0 != null) {
                for (int i = 0; i < listZ0.size(); i++) {
                    A a2 = listZ0.get(i);
                    if (!z) {
                        String strR = d0.r(a2.a);
                        if (d0.B(strR)) {
                            List<A> listD = cVar.d(strR);
                            if (listD == null) {
                                listD = new ArrayList<>();
                                cVar.h(strR, listD);
                            }
                            listD.add(a2);
                        }
                    }
                    if (a2.c >= jC) {
                        cVar.a(c6248x.i0(c6249y.C0(), a2));
                    }
                }
            }
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            C2461b.e("build media store cache");
        } catch (Throwable th) {
            throw th;
        }
    }

    private static void y(C6247w c6247w) {
        synchronized (u) {
            try {
                if (c6247w == t) {
                    t = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void z(AbstractC6239n abstractC6239n, ax.p038c3.i iVar) throws IOException {
        while (true) {
            String strC = iVar.c();
            if (strC == null) {
                return;
            }
            String strDecode = Uri.decode(strC.split("\u0000")[0]);
            if (d0.B(strDecode) && !d0.I(abstractC6239n.E(), strDecode)) {
                return;
            } else {
                iVar.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p406z3.q
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public Boolean g(Void... voidArr) {
        Throwable th;
        try {
            if (this.m) {
                o.fine("Full scan start");
                this.l = true;
            } else {
                try {
                    if (R(this.k)) {
                        o.fine("Full scan start");
                        this.l = true;
                    } else {
                        o.fine("Fast scan start");
                        this.l = false;
                    }
                } catch (IllegalArgumentException | IllegalStateException e2) {
                    th = e2;
                }
            }
            boolean zE = ax.p406z3.o.e(this.k);
            this.n = zE;
            if (!zE) {
                return Boolean.FALSE;
            }
            if (ax.p006a3.Q.d1() && v == 0) {
                v = ((ActivityManager) this.k.getApplicationContext().getSystemService("activity")).getMemoryClass();
                ax.p038c3.b.k().x(v);
            }
            ArrayList<AbstractC6239n> arrayList = new ArrayList<>();
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strO = ax.p038c3.j.F().O();
            String strU = ax.p038c3.j.F().U();
            Y(strO, this.l, arrayList);
            Logger logger = o;
            logger.fine("scan time main : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (ax.p038c3.j.F().u0() && strU != null && !strU.startsWith(strO)) {
                a0(strU, this.l, arrayList);
                logger.fine("scan time sd : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            }
            HashMap<String, C6246v> map = new HashMap<>();
            HashMap<String, C6249y.b> map2 = new HashMap<>();
            HashMap<String, Set<String>> map3 = new HashMap<>();
            for (ax.p086f3.K k : ax.p086f3.K.c()) {
                if (isCancelled()) {
                    return Boolean.FALSE;
                }
                f fVar = this.h.get(k.d());
                HashMap<String, e> map4 = fVar.a;
                try {
                    c0(k, fVar, map, map2, map3);
                    map4.clear();
                } catch (IllegalArgumentException e3) {
                    e = e3;
                } catch (IllegalStateException e4) {
                    e = e4;
                }
            }
            Logger logger2 = o;
            logger2.fine("scan time library : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            v(new Void[0]);
            Z(map, arrayList);
            arrayList.clear();
            logger2.fine("scan time new files : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            return Boolean.TRUE;
        } catch (IllegalArgumentException e5) {
            e = e5;
        } catch (IllegalStateException e6) {
            e = e6;
        }
        th = e;
        ax.p302sb.c.h().g().d("LibraryScan Failed").m(th).i();
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p406z3.q
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public void q(Boolean bool) {
        synchronized (this.j) {
            try {
                Iterator<d> it = this.j.iterator();
                while (it.hasNext()) {
                    it.next().a(bool.booleanValue());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        y(this);
        if (this.l || !this.n) {
            return;
        }
        D(this.k, null, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p406z3.q
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public void s(Void... voidArr) {
        synchronized (this.j) {
            try {
                Iterator<d> it = this.j.iterator();
                while (it.hasNext()) {
                    it.next().b(this.h);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // ax.p406z3.q
    protected void o() {
        synchronized (this.j) {
            try {
                Iterator<d> it = this.j.iterator();
                while (it.hasNext()) {
                    it.next().a(false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        y(this);
    }

    @Override // ax.p406z3.q
    protected void r() {
        this.h = new HashMap<>();
        this.i = new HashMap<>();
        Iterator<ax.p086f3.K> it = ax.p086f3.K.c().iterator();
        while (it.hasNext()) {
            this.h.put(it.next().d(), new f());
        }
    }

    public void w(d dVar) {
        if (dVar != null) {
            this.j.add(dVar);
        }
    }
}
