package com.alphainventor.filemanager.file;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import ax.I1.C0718i;
import ax.J1.C0739u;
import ax.J1.V;
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

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5844s extends ax.p045d2.n<Void, Void, Boolean> {
    private static final Logger o = Logger.getLogger("FileManager.LibraryScanTask");
    private static HashSet<String> p = new HashSet<>();
    private static HashSet<String> q = new HashSet<>();
    private static HashSet<String> r = new HashSet<>();
    private static HashSet<String> s = new HashSet<>();
    static C5844s t;
    static final Object u;
    private static int v;
    private HashMap<ax.p338y1.f, f> h;
    private HashMap<String, Boolean> i;
    private final List<d> j;
    private Context k;
    private boolean l;
    private boolean m;
    private boolean n;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.s$a */
    class a implements Comparator<AbstractC5838l> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
            return abstractC5838l2.C().compareTo(abstractC5838l.C());
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.s$b */
    class b implements Comparator<AbstractC5838l> {
        b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
            return abstractC5838l.C().compareTo(abstractC5838l2.C());
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.s$c */
    static class c {
        String a;
        Set<String> b = new HashSet();
        ax.J1.F c;
        C5839m d;
        File e;
        HashMap<String, List<w>> f;
        ArrayList<AbstractC5838l> g;
        boolean h;

        c(ax.J1.F f, String str, File file, boolean z) {
            this.c = f;
            this.d = ax.J1.r.d(f);
            this.a = str;
            this.e = file;
            this.h = z;
        }

        void a(AbstractC5838l abstractC5838l) {
            this.g.add(abstractC5838l);
        }

        void b() {
            ArrayList<AbstractC5838l> arrayList = this.g;
            if (arrayList != null) {
                arrayList.clear();
                this.g = null;
            }
            c();
        }

        void c() {
            HashMap<String, List<w>> map = this.f;
            if (map != null) {
                map.clear();
                this.f = null;
            }
        }

        List<w> d(String str) {
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

        void h(String str, List<w> list) {
            this.f.put(str, list);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.s$d */
    public interface d {
        void a(boolean z);

        void b(HashMap<ax.p338y1.f, f> map);
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.s$e */
    public static class e {
        public ax.J1.F a;
        public u c;
        public Boolean d;
        public String h;
        public boolean b = false;
        public long e = 0;
        public int f = 0;
        public long g = 0;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.s$f */
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

    public C5844s(Context context, boolean z) {
        super(ax.d2.n.f.LOW);
        this.j = Collections.synchronizedList(new ArrayList());
        this.k = context.getApplicationContext();
        this.m = z;
    }

    private static void A(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        file.delete();
    }

    public static C5844s C(Context context, d dVar) {
        try {
            return D(context.getApplicationContext(), dVar, false);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    private static C5844s D(Context context, d dVar, boolean z) {
        C5844s c5844s;
        synchronized (u) {
            try {
                C5844s c5844s2 = t;
                if (c5844s2 == null || c5844s2.m() == ax.d2.n.g.FINISHED) {
                    o.fine("Execute scan task");
                    C5844s c5844s3 = new C5844s(context, z);
                    t = c5844s3;
                    c5844s3.i(new Void[0]);
                }
                if (dVar != null) {
                    t.w(dVar);
                }
                c5844s = t;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c5844s;
    }

    private boolean E(AbstractC5838l abstractC5838l) {
        return q.contains(abstractC5838l.w().toLowerCase());
    }

    private boolean F(AbstractC5838l abstractC5838l) {
        String lowerCase = abstractC5838l.w().toLowerCase();
        String strF = V.f(lowerCase);
        if ("log".equals(strF) || lowerCase.endsWith("_log.txt") || lowerCase.endsWith("_logs.txt")) {
            return true;
        }
        return (lowerCase.startsWith("filelog") && "txt".equals(strF)) || q.contains(lowerCase);
    }

    public static Set<String> G() {
        return r;
    }

    static File H(Context context, boolean z) {
        File fileQ = ax.p338y1.e.q(context);
        if (fileQ != null) {
            return z ? new File(fileQ.getAbsolutePath(), "scanfile.full") : new File(fileQ.getAbsolutePath(), "scanfile.fast");
        }
        throw new IllegalStateException("Can not create index file");
    }

    static File I(Context context) {
        File fileQ = ax.p338y1.e.q(context);
        if (fileQ != null) {
            return new File(fileQ.getAbsolutePath(), "scanfile_new.full");
        }
        throw new IllegalStateException("Can not create index file");
    }

    static File J(Context context, boolean z) {
        File fileQ = ax.p338y1.e.q(context);
        if (fileQ != null) {
            return z ? new File(fileQ.getAbsolutePath(), "scanfile_sd.full") : new File(fileQ.getAbsolutePath(), "scanfile_sd.fast");
        }
        throw new IllegalStateException("Can not create index file");
    }

    private void K(c cVar, AbstractC5838l abstractC5838l, BufferedWriter bufferedWriter, ax.G1.h hVar) throws IOException {
        List<AbstractC5838l> listT;
        String[] strArrSplit;
        String strDecode;
        int iCompareTo;
        if (((u) abstractC5838l).r0()) {
            try {
                listT = T(cVar, abstractC5838l, true);
            } catch (C0718i unused) {
                listT = null;
            } catch (OutOfMemoryError unused2) {
                ax.p221pa.c.h().g().d("SCAN: listFiles OUT OF MEMORY 2").i();
                listT = null;
            }
            if (listT == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (AbstractC5838l abstractC5838l2 : listT) {
                if (!abstractC5838l2.isDirectory()) {
                    arrayList2.add(abstractC5838l2);
                } else if (!V.z(abstractC5838l2.w()) && !abstractC5838l.C().equals(abstractC5838l2.C())) {
                    arrayList.add(abstractC5838l2);
                }
            }
            Collections.sort(arrayList, new b());
            e0(cVar, abstractC5838l, arrayList2, bufferedWriter);
            if (arrayList.size() == 0) {
                if (V.y(abstractC5838l.C())) {
                    z(abstractC5838l, hVar);
                    return;
                }
                ax.p221pa.c.h().g().b("INVALID LIBRARYSCAN DIR ABSOULTE PATH").h("dir:" + abstractC5838l.C()).i();
                return;
            }
            String strC = hVar.c();
            int i = 0;
            while (i < arrayList.size()) {
                AbstractC5838l abstractC5838l3 = (AbstractC5838l) arrayList.get(i);
                String strC2 = abstractC5838l3.C();
                if (!E.b2(abstractC5838l3)) {
                    if (strC == null) {
                        iCompareTo = -1;
                        strArrSplit = null;
                        strDecode = null;
                    } else {
                        strArrSplit = strC.split("\u0000");
                        strDecode = Uri.decode(strArrSplit[0]);
                        iCompareTo = strC2.compareTo(strDecode);
                    }
                    if (iCompareTo == 0) {
                        if (ax.E1.P.W0() && abstractC5838l3.q() == 0) {
                            ((u) abstractC5838l3).k1();
                            abstractC5838l3.q();
                        }
                        if (Long.parseLong(strArrSplit[1]) == abstractC5838l3.q()) {
                            bufferedWriter.write(strC + "\n");
                            X(cVar, strDecode, strArrSplit);
                            hVar.a();
                            N(cVar, strDecode, bufferedWriter, hVar);
                            strC = hVar.c();
                        } else {
                            hVar.a();
                            K(cVar, abstractC5838l3, bufferedWriter, hVar);
                            strC = hVar.c();
                        }
                    } else if (iCompareTo < 0) {
                        M(cVar, abstractC5838l3, bufferedWriter);
                    } else {
                        hVar.a();
                        strC = hVar.c();
                    }
                }
                i++;
            }
            z(abstractC5838l, hVar);
        }
    }

    private void L(c cVar, Stack<AbstractC5838l> stack, Writer writer) throws IOException {
        List<AbstractC5838l> listT;
        AbstractC5838l abstractC5838lPop = stack.pop();
        if (cVar.b.contains(abstractC5838lPop.C()) || E.c2(abstractC5838lPop.w())) {
            return;
        }
        try {
            listT = T(cVar, abstractC5838lPop, writer != null);
        } catch (C0718i unused) {
            listT = null;
        } catch (OutOfMemoryError unused2) {
            ax.p221pa.c.h().g().d("SCAN: listFiles OUT OF MEMORY").i();
            listT = null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (listT == null) {
            e0(cVar, abstractC5838lPop, arrayList2, writer);
            return;
        }
        for (int i = 0; i < listT.size(); i++) {
            AbstractC5838l abstractC5838l = listT.get(i);
            if (!Q(cVar, abstractC5838l)) {
                arrayList2.add(abstractC5838l);
            } else if (!V.z(abstractC5838l.w())) {
                arrayList.add(abstractC5838l);
            }
        }
        e0(cVar, abstractC5838lPop, arrayList2, writer);
        Collections.sort(arrayList, new a());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            stack.push((AbstractC5838l) arrayList.get(i2));
        }
    }

    private void M(c cVar, AbstractC5838l abstractC5838l, Writer writer) throws IOException {
        Stack<AbstractC5838l> stack = new Stack<>();
        stack.push(abstractC5838l);
        while (!stack.isEmpty()) {
            L(cVar, stack, writer);
        }
    }

    private void N(c cVar, String str, BufferedWriter bufferedWriter, ax.G1.h hVar) throws IOException {
        Stack<String> stack = new Stack<>();
        stack.push(str);
        while (!stack.isEmpty()) {
            O(cVar, stack, bufferedWriter, hVar);
        }
    }

    private void O(c cVar, Stack<String> stack, BufferedWriter bufferedWriter, ax.G1.h hVar) throws IOException {
        String strPop = stack.pop();
        while (true) {
            String strC = hVar.c();
            if (strC == null) {
                return;
            }
            String[] strArrSplit = strC.split("\u0000");
            String strDecode = Uri.decode(strArrSplit[0]);
            if (!V.y(strDecode)) {
                ax.p221pa.c.h().g().d("ISCD").h(strDecode + ":" + strC.length() + ":" + strC).i();
                hVar.a();
            } else {
                if (!V.r(strDecode).equals(strPop) && strPop != null) {
                    return;
                }
                try {
                    AbstractC5838l abstractC5838lV0 = cVar.d.V0(strDecode);
                    if (abstractC5838lV0.q() == Long.parseLong(strArrSplit[1])) {
                        bufferedWriter.write(strC + "\n");
                        X(cVar, strDecode, strArrSplit);
                        hVar.a();
                        stack.push(strPop);
                        stack.push(strDecode);
                        return;
                    }
                    hVar.a();
                    K(cVar, abstractC5838lV0, bufferedWriter, hVar);
                } catch (C0718i e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:45:0x00e6 A[Catch: IOException -> 0x00e9, TRY_LEAVE, TryCatch #0 {IOException -> 0x00e9, blocks: (B:43:0x00e1, B:45:0x00e6), top: B:49:0x00e1 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void P(c cVar, AbstractC5838l abstractC5838l) throws Throwable {
        BufferedWriter bufferedWriter;
        String line;
        BufferedReader bufferedReader = null;
        try {
            if (!abstractC5838l.isDirectory()) {
                String str = "location:" + cVar.c + "," + abstractC5838l.C() + "," + cVar.c.e() + "," + abstractC5838l.n();
                if (abstractC5838l.n()) {
                    ax.p221pa.c.h().g().b("SCAN ROOT IS NOT DIR").h(str).i();
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
                K(cVar, abstractC5838l, bufferedWriter, new ax.G1.h(bufferedReader2));
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

    private boolean Q(c cVar, AbstractC5838l abstractC5838l) {
        if (!cVar.h) {
            String strW = abstractC5838l.w();
            if (strW.length() > 4 && (strW.charAt(strW.length() - 3) == '.' || strW.charAt(strW.length() - 4) == '.')) {
                return false;
            }
        }
        return abstractC5838l.isDirectory();
    }

    private boolean R(Context context) {
        File fileH = H(context, true);
        File fileJ = J(context, true);
        boolean z = fileH.exists() && fileH.length() > 0;
        boolean z2 = fileJ.exists() && fileJ.length() > 0;
        if (ax.G1.i.D().l0()) {
            return z && z2;
        }
        return z;
    }

    public static boolean S(String str) {
        if (p.contains(str)) {
            return true;
        }
        return p.contains(V.q(str, 4));
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0064  */
    private List<AbstractC5838l> T(c cVar, AbstractC5838l abstractC5838l, boolean z) throws C0718i {
        boolean z2;
        if (ax.E1.P.W0() && z) {
            String strC = abstractC5838l.C();
            if (cVar.a.equals(strC)) {
                z2 = false;
            } else {
                if (!cVar.f()) {
                    x(cVar, false);
                }
                List<w> listD = cVar.d(strC);
                if (listD == null || listD.size() <= 0) {
                    z2 = false;
                } else {
                    if (!s.contains(abstractC5838l.V())) {
                        ArrayList arrayList = new ArrayList();
                        C5845t c5845t = (C5845t) cVar.d.t();
                        for (int i = 0; i < listD.size(); i++) {
                            arrayList.add(c5845t.b0(cVar.c, listD.get(i)));
                        }
                        return arrayList;
                    }
                    z2 = true;
                }
            }
        } else {
            z2 = false;
        }
        if (!((u) abstractC5838l).q0() || !abstractC5838l.isDirectory()) {
            return null;
        }
        List<AbstractC5838l> listG1 = cVar.d.g1(abstractC5838l);
        if (z2 && listG1 != null) {
            HashSet hashSet = new HashSet();
            List<w> listD2 = cVar.d(abstractC5838l.C());
            if (listD2 != null) {
                Iterator<w> it = listD2.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().a);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            long jC = ax.J1.I.c();
            int iA = 5000;
            for (AbstractC5838l abstractC5838l2 : listG1) {
                if (!abstractC5838l2.g() && !abstractC5838l2.isDirectory() && !hashSet.contains(abstractC5838l2.C())) {
                    w wVar = new w(abstractC5838l2.C(), abstractC5838l2.isDirectory(), abstractC5838l2.q(), abstractC5838l2.q(), abstractC5838l2.o());
                    if (listD2 == null) {
                        listD2 = new ArrayList<>();
                        cVar.h(abstractC5838l.C(), listD2);
                    }
                    listD2.add(wVar);
                    if (abstractC5838l2.q() >= jC) {
                        cVar.a(abstractC5838l2);
                    }
                    if (iA < 196608) {
                        ax.X1.d dVar = new ax.X1.d(abstractC5838l2.C(), abstractC5838l2.P().J(), abstractC5838l2.L(), abstractC5838l2.q(), false, false, false, false);
                        arrayList2.add(dVar);
                        iA += dVar.a();
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                C5845t.G1(this.k, arrayList2, false, iA);
            }
        }
        return listG1;
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

    private void X(c cVar, String str, String[] strArr) {
        Boolean boolValueOf = null;
        for (int i = 2; i < strArr.length; i++) {
            String str2 = strArr[i];
            String[] strArrSplit = str2.split("/");
            if (i != 2 || strArrSplit.length >= 2) {
                if (i != 3 || strArrSplit.length >= 2) {
                    f fVar = this.h.get(ax.p338y1.f.valueOf(strArrSplit[0]));
                    if (fVar == null) {
                        ax.p221pa.c.h().g().b("NULL SCANINFO").h("loc:" + str2).i();
                    } else {
                        try {
                            e eVar = new e();
                            eVar.f = Integer.valueOf(strArrSplit[1]).intValue();
                            eVar.e = Long.valueOf(strArrSplit[2]).longValue();
                            eVar.g = Long.valueOf(strArrSplit[3]).longValue();
                            eVar.h = Uri.decode(strArrSplit[4]);
                            eVar.b = false;
                            eVar.a = cVar.c;
                            u uVar = (u) cVar.d.V0(str);
                            eVar.c = uVar;
                            eVar.d = boolValueOf;
                            fVar.a.put(uVar.C(), eVar);
                        } catch (C0718i e2) {
                            e2.printStackTrace();
                            ax.p045d2.b.f();
                        }
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

    private void Y(String str, boolean z, ArrayList<AbstractC5838l> arrayList) {
        c cVar = new c(ax.J1.F.e, str, H(this.k, z), z);
        cVar.b.add(str + "/Android/data");
        cVar.b.add(str + "/.localcache");
        cVar.b.add(str + "/Android/media/com.alphainventor.filemanager/.localcache");
        try {
            b0(cVar, cVar.d.V0(cVar.a), arrayList);
        } catch (C0718i unused) {
            ax.p045d2.b.f();
        }
    }

    private void Z(HashMap<String, r> map, List<AbstractC5838l> list) {
        File fileI = I(this.k);
        C5839m c5839mE = ax.J1.r.e(ax.p338y1.f.H0, 0);
        c5839mE.S();
        ax.J1.I i = new ax.J1.I((x) c5839mE.t());
        try {
            i.a(fileI, map, list);
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                A(fileI);
                i.a(fileI, map, list);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            ax.p221pa.c.h().d("NEWSCAN1:").m(e4).i();
            A(fileI);
            i.a(fileI, map, list);
        }
        c5839mE.P(false);
    }

    private void a0(String str, boolean z, ArrayList<AbstractC5838l> arrayList) {
        c cVar = new c(ax.J1.F.f, str, J(this.k, z), z);
        cVar.b.add(str + "/Android/data");
        try {
            b0(cVar, cVar.d.V0(cVar.a), arrayList);
        } catch (C0718i unused) {
            ax.p045d2.b.f();
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0068  */
    /* JADX WARN: Code duplicated, block: B:57:0x0182 A[DONT_GENERATE] */
    private void b0(c cVar, AbstractC5838l abstractC5838l, ArrayList<AbstractC5838l> arrayList) {
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                P(cVar, abstractC5838l);
                                if (!ax.E1.P.L() || cVar.c != ax.J1.F.f) {
                                    d0(cVar, abstractC5838l);
                                }
                                if (ax.E1.P.W0()) {
                                    cVar.c();
                                }
                            } catch (Throwable th) {
                                if (ax.E1.P.W0()) {
                                    cVar.c();
                                }
                                throw th;
                            }
                        } catch (StackOverflowError e2) {
                            e2.printStackTrace();
                            ax.p221pa.c.h().d("SCANSTACK!!!").m(e2).h("location:" + cVar.c.toString()).i();
                            if (ax.E1.P.W0()) {
                                cVar.c();
                            }
                            o.severe("Retry Incremental Scan.");
                            try {
                                cVar.b();
                                A(cVar.e);
                                P(cVar, abstractC5838l);
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                o.severe("Incremental Scan Failed.");
                            } catch (IndexOutOfBoundsException e4) {
                                e4.printStackTrace();
                                ax.p221pa.c.h().d("OOB2").m(e4).h("location:" + cVar.c.toString()).i();
                                o.severe("Incremental Scan Failed.");
                            } finally {
                                if (ax.E1.P.W0()) {
                                    cVar.c();
                                }
                            }
                        }
                    } catch (IndexOutOfBoundsException e5) {
                        e5.printStackTrace();
                        ax.p221pa.c.h().d("LST1:").m(e5).h("location:" + cVar.c.toString()).i();
                        if (ax.E1.P.W0()) {
                            cVar.c();
                        }
                        o.severe("Retry Incremental Scan.");
                        cVar.b();
                        A(cVar.e);
                        P(cVar, abstractC5838l);
                    }
                } catch (IllegalArgumentException e6) {
                    e6.printStackTrace();
                    ax.p221pa.c.h().d("LST4").m(e6).h("location:" + cVar.c.toString()).i();
                    if (ax.E1.P.W0()) {
                        cVar.c();
                    }
                    o.severe("Retry Incremental Scan.");
                    cVar.b();
                    A(cVar.e);
                    P(cVar, abstractC5838l);
                }
            } catch (NumberFormatException e7) {
                e7.printStackTrace();
                ax.p221pa.c.h().d("LST3").m(e7).h("location:" + cVar.c.toString()).i();
                if (ax.E1.P.W0()) {
                    cVar.c();
                }
                o.severe("Retry Incremental Scan.");
                cVar.b();
                A(cVar.e);
                P(cVar, abstractC5838l);
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            if (ax.E1.P.W0()) {
                cVar.c();
            }
            o.severe("Retry Incremental Scan.");
            cVar.b();
            A(cVar.e);
            P(cVar, abstractC5838l);
        } catch (IllegalStateException e9) {
            e9.printStackTrace();
            if (TextUtils.isEmpty(e9.getMessage())) {
                ax.p221pa.c.h().d("LST2:").m(e9).h("location:" + cVar.c.toString()).i();
            } else {
                ax.p221pa.c.h().d("LST2-2").h(e9.getMessage()).i();
            }
            if (ax.E1.P.W0()) {
                cVar.c();
            }
            o.severe("Retry Incremental Scan.");
            cVar.b();
            A(cVar.e);
            P(cVar, abstractC5838l);
        }
        if (ax.E1.P.W0()) {
            if (!cVar.g()) {
                x(cVar, true);
            }
            ArrayList<AbstractC5838l> arrayList2 = cVar.g;
            if (arrayList2 != null) {
                arrayList.addAll(arrayList2);
            } else {
                ax.p045d2.b.f();
            }
            cVar.b();
        }
    }

    private void c0(ax.J1.F f2, f fVar, HashMap<String, r> map, HashMap<String, u.b> map2) {
        String strZ0;
        C5839m c5839mD = ax.J1.r.d(f2);
        c5839mD.S();
        try {
            AbstractC5838l abstractC5838lV0 = c5839mD.V0(f2.e());
            ArrayList arrayList = new ArrayList();
            ArrayList<e> arrayList2 = new ArrayList(fVar.a.values());
            C5843q c5843q = (C5843q) c5839mD.t();
            ax.G1.b.k().a(arrayList2.size());
            for (e eVar : arrayList2) {
                r rVar = (r) c5843q.O1(eVar.c.y0(), eVar.a);
                if (eVar.c.isDirectory()) {
                    long j = eVar.g;
                    if (j != 0) {
                        rVar.v1(j);
                        rVar.w1(eVar.h);
                    }
                    rVar.b0(eVar.f);
                    rVar.a1(r.s1(rVar, eVar, this.i.get(eVar.c.S()), map2));
                    if (eVar.b && (strZ0 = c5839mD.Z0(rVar)) != null) {
                        ax.p003a2.c.B(this.k, strZ0);
                    }
                }
                if (!rVar.g()) {
                    fVar.b += eVar.e;
                    fVar.c += eVar.f;
                    if (ax.J1.F.e.equals(rVar.v0())) {
                        fVar.d += eVar.e;
                    } else {
                        fVar.e += eVar.e;
                    }
                    map.put(eVar.c.C(), rVar);
                }
                rVar.g1();
                if (rVar.n()) {
                    arrayList.add(rVar);
                }
            }
            ax.G1.b.k().m(abstractC5838lV0, arrayList);
            arrayList2.clear();
            c5839mD.P(false);
        } catch (C0718i e2) {
            e2.printStackTrace();
        }
    }

    private void d0(c cVar, AbstractC5838l abstractC5838l) {
        Iterator<String> it = p.iterator();
        while (it.hasNext()) {
            try {
                AbstractC5838l abstractC5838lV0 = cVar.d.V0(V.L(abstractC5838l.C(), it.next()));
                if (abstractC5838lV0.n()) {
                    try {
                        M(cVar, abstractC5838lV0, null);
                    } catch (IOException unused) {
                    }
                }
            } catch (C0718i e2) {
                e2.printStackTrace();
                ax.p045d2.b.e("whiltelist scan failed");
            }
        }
    }

    private void e0(c cVar, AbstractC5838l abstractC5838l, List<AbstractC5838l> list, Writer writer) throws IOException {
        e eVar;
        HashMap map = new HashMap();
        Boolean boolValueOf = null;
        Boolean boolValueOf2 = null;
        for (AbstractC5838l abstractC5838l2 : list) {
            String strW = abstractC5838l2.w();
            ax.p338y1.f fVarJ = C0739u.j(C0739u.e(V.f(strW)));
            if (fVarJ != ax.p338y1.f.E0 || (!F(abstractC5838l2) && !E(abstractC5838l))) {
                if (fVarJ != null) {
                    if (map.containsKey(fVarJ)) {
                        eVar = (e) map.get(fVarJ);
                    } else {
                        eVar = new e();
                        map.put(fVarJ, eVar);
                        eVar.c = (u) abstractC5838l;
                        eVar.a = cVar.c;
                    }
                    try {
                        eVar.e += abstractC5838l2.o();
                        eVar.f++;
                        long jQ = abstractC5838l2.q();
                        if (eVar.g < jQ && !abstractC5838l2.g()) {
                            eVar.g = jQ;
                            eVar.h = abstractC5838l2.w();
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                } else if (".nomedia".equals(strW)) {
                    boolValueOf = Boolean.TRUE;
                } else if (".hidden".equals(strW)) {
                    boolValueOf2 = Boolean.TRUE;
                }
            }
        }
        if (ax.E1.P.W0()) {
            try {
                boolValueOf = Boolean.valueOf(((u) cVar.d.V0(V.M(abstractC5838l.C(), ".nomedia"))).r0());
                boolValueOf2 = Boolean.valueOf(((u) cVar.d.V0(V.M(abstractC5838l.C(), ".hidden"))).r0());
            } catch (C0718i e2) {
                e2.printStackTrace();
                ax.p045d2.b.f();
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
            this.i.put(abstractC5838l.C(), boolValueOf2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Uri.encode(abstractC5838l.C()));
        stringBuffer.append("\u0000");
        stringBuffer.append(String.valueOf(abstractC5838l.q()));
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
        for (ax.p338y1.f fVar : map.keySet()) {
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
            this.h.get(fVar).a.put(abstractC5838l.C(), eVar2);
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
            long jC = ax.J1.I.c();
            String strB = cVar.d.B();
            C5845t c5845t = (C5845t) cVar.d.t();
            u uVar = (u) c5845t.V0(strB);
            if (!uVar.isDirectory()) {
                if (ax.E1.P.t0() && !ax.p045d2.l.c()) {
                    return;
                }
                if (!"/sdcard".equals(strB) && !"/storage/emulated/0".equals(strB)) {
                    ax.p221pa.c.h().g().b("LIBRARY ROOT IS NOT DIRECTORY").h("rootPath: " + strB + "," + cVar.c).i();
                    return;
                }
            }
            List<w> listR0 = c5845t.R0(uVar, true, z ? jC : 0L, 0L);
            if (listR0 != null) {
                for (int i = 0; i < listR0.size(); i++) {
                    w wVar = listR0.get(i);
                    if (!z) {
                        String strR = V.r(wVar.a);
                        if (V.y(strR)) {
                            List<w> listD = cVar.d(strR);
                            if (listD == null) {
                                listD = new ArrayList<>();
                                cVar.h(strR, listD);
                            }
                            listD.add(wVar);
                        }
                    }
                    if (wVar.c >= jC) {
                        cVar.a(c5845t.b0(uVar.v0(), wVar));
                    }
                }
            }
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            ax.p045d2.b.e("build media store cache");
        } catch (Throwable th) {
            throw th;
        }
    }

    private static void y(C5844s c5844s) {
        synchronized (u) {
            try {
                if (c5844s == t) {
                    t = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void z(AbstractC5838l abstractC5838l, ax.G1.h hVar) throws IOException {
        while (true) {
            String strC = hVar.c();
            if (strC == null) {
                return;
            }
            String strDecode = Uri.decode(strC.split("\u0000")[0]);
            if (V.y(strDecode) && !V.E(abstractC5838l.C(), strDecode)) {
                return;
            } else {
                hVar.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p045d2.n
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public Boolean g(Void... voidArr) {
        try {
            if (this.m || R(this.k)) {
                o.fine("Full scan start");
                this.l = true;
            } else {
                o.fine("Fast scan start");
                this.l = false;
            }
            boolean zE = ax.p045d2.l.e(this.k);
            this.n = zE;
            if (!zE) {
                return Boolean.FALSE;
            }
            if (ax.E1.P.T0() && v == 0) {
                v = ((ActivityManager) this.k.getApplicationContext().getSystemService("activity")).getMemoryClass();
                ax.G1.b.k().x(v);
            }
            ArrayList<AbstractC5838l> arrayList = new ArrayList<>();
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strI = ax.G1.i.D().I();
            String strO = ax.G1.i.D().O();
            Y(strI, this.l, arrayList);
            Logger logger = o;
            logger.fine("scan time main : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (ax.G1.i.D().l0() && strO != null && !strO.startsWith(strI)) {
                a0(strO, this.l, arrayList);
                logger.fine("scan time sd : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            }
            HashMap<String, r> map = new HashMap<>();
            HashMap<String, u.b> map2 = new HashMap<>();
            for (ax.J1.F f2 : ax.J1.F.c()) {
                if (isCancelled()) {
                    return Boolean.FALSE;
                }
                f fVar = this.h.get(f2.d());
                HashMap<String, e> map3 = fVar.a;
                c0(f2, fVar, map, map2);
                map3.clear();
            }
            Logger logger2 = o;
            logger2.fine("scan time library : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            v(new Void[0]);
            Z(map, arrayList);
            arrayList.clear();
            logger2.fine("scan time new files : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            return Boolean.TRUE;
        } catch (IllegalArgumentException e2) {
            e = e2;
            ax.p221pa.c.h().g().d("LibraryScan Failed").m(e).i();
            return Boolean.FALSE;
        } catch (IllegalStateException e3) {
            e = e3;
            ax.p221pa.c.h().g().d("LibraryScan Failed").m(e).i();
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p045d2.n
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
    @Override // ax.p045d2.n
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

    @Override // ax.p045d2.n
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

    @Override // ax.p045d2.n
    protected void r() {
        this.h = new HashMap<>();
        this.i = new HashMap<>();
        Iterator<ax.J1.F> it = ax.J1.F.c().iterator();
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
