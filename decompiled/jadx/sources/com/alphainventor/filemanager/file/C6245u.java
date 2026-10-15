package com.alphainventor.filemanager.file;

import android.annotation.SuppressLint;
import android.os.Environment;
import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6245u extends C6248x {
    private static final Logger B = Logger.getLogger("FileManager.LibraryFileHelper");
    static SimpleDateFormat C = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
    private static final Map<EnumC2019z, a> D;
    private HashSet<AbstractC6239n> A;
    private EnumC2019z z;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.u$a */
    static class a implements FilenameFilter {
        EnumC2019z q;

        public a(EnumC2019z enumC2019z) {
            this.q = enumC2019z;
        }

        @Override // java.io.FilenameFilter
        @SuppressLint({"DefaultLocale"})
        public boolean accept(File file, String str) {
            return b(str);
        }

        public boolean b(String str) {
            if (d0.A(str)) {
                return ax.p086f3.A.y(d0.j(str), this.q);
            }
            return false;
        }
    }

    static {
        HashMap map = new HashMap();
        D = map;
        EnumC2019z enumC2019z = EnumC2019z.IMAGE;
        map.put(enumC2019z, new a(enumC2019z));
        EnumC2019z enumC2019z2 = EnumC2019z.VIDEO;
        map.put(enumC2019z2, new a(enumC2019z2));
        EnumC2019z enumC2019z3 = EnumC2019z.AUDIO;
        map.put(enumC2019z3, new a(enumC2019z3));
        EnumC2019z enumC2019z4 = EnumC2019z.TEXT;
        map.put(enumC2019z4, new a(enumC2019z4));
        EnumC2019z enumC2019z5 = EnumC2019z.PRESENTATION;
        map.put(enumC2019z5, new a(enumC2019z5));
        EnumC2019z enumC2019z6 = EnumC2019z.SPREADSHEET;
        map.put(enumC2019z6, new a(enumC2019z6));
        EnumC2019z enumC2019z7 = EnumC2019z.ARCHIVE;
        map.put(enumC2019z7, new a(enumC2019z7));
        EnumC2019z enumC2019z8 = EnumC2019z.GROUP_DOCUMENT;
        map.put(enumC2019z8, new a(enumC2019z8));
    }

    public static void W1(AbstractC6238m abstractC6238m, AbstractC6239n abstractC6239n) throws C1966j {
        AbstractC6239n abstractC6239nA1 = abstractC6238m.a1(abstractC6238m.x());
        List<AbstractC6239n> listG = ax.p038c3.b.k().g(abstractC6239nA1);
        if (listG != null) {
            ArrayList arrayList = new ArrayList(listG);
            if (arrayList.remove(abstractC6239n)) {
                ax.p038c3.b.k().m(abstractC6239nA1, arrayList);
            }
        }
    }

    private void X1(AbstractC6239n abstractC6239n) {
        HashSet<AbstractC6239n> hashSet = this.A;
        if (hashSet != null) {
            Iterator<AbstractC6239n> it = hashSet.iterator();
            while (it.hasNext()) {
                AbstractC6239n next = it.next();
                if (next != null && next.E().equals(abstractC6239n.E())) {
                    it.remove();
                }
            }
        }
    }

    private AbstractC6239n a2(AbstractC6239n abstractC6239n) {
        a aVar = D.get(Z1());
        C6246v c6246v = (C6246v) abstractC6239n;
        String strH1 = c6246v.H1();
        ax.p086f3.K kC0 = c6246v.C0();
        if (strH1 != null) {
            File file = new File(c6246v.G0(), strH1);
            if (file.exists()) {
                return new C6246v(this, file, aVar, kC0);
            }
        }
        B.fine("WARN : GetFirstFileInfoFromDirectory ListFiles");
        return g2(c6246v);
    }

    private C6246v b2(String str) {
        List<AbstractC6239n> listG = ax.p038c3.b.k().g((C6246v) a1(x()));
        if (listG == null) {
            return null;
        }
        for (AbstractC6239n abstractC6239n : listG) {
            if (abstractC6239n.E().equals(str)) {
                return (C6246v) abstractC6239n;
            }
        }
        return null;
    }

    private AbstractC6239n c2(AbstractC6239n abstractC6239n) {
        a aVar = D.get(EnumC2019z.IMAGE);
        C6246v c6246v = (C6246v) abstractC6239n;
        File[] fileArrListFiles = c6246v.G0().listFiles(aVar);
        if (fileArrListFiles.length > 0) {
            return new C6246v(this, fileArrListFiles[0], aVar, c6246v.C0());
        }
        return null;
    }

    public static String d2(ax.U2.f fVar) {
        File externalStoragePublicDirectory;
        if (fVar == ax.U2.f.F0) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        } else if (fVar == ax.U2.f.G0) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        } else if (fVar == ax.U2.f.E0) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else if (fVar == ax.U2.f.H0) {
            externalStoragePublicDirectory = ax.p006a3.Q.I0() ? Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) : new File(ax.p006a3.K.y(), "Documents");
        } else {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            ax.p302sb.c.h().d("UNKNOWN LIBRARY LOCATION!!!!!!").h(fVar.H()).i();
            externalStoragePublicDirectory = externalStorageDirectory;
        }
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        return externalStoragePublicDirectory.getAbsolutePath();
    }

    private void e2(String str) {
        C6246v c6246vB2 = b2(str);
        if (c6246vB2 != null) {
            c6246vB2.e0(-3);
        }
    }

    private synchronized boolean f2(AbstractC6239n abstractC6239n) {
        HashSet<AbstractC6239n> hashSet = this.A;
        boolean z = false;
        if (hashSet == null) {
            return false;
        }
        Iterator<AbstractC6239n> it = hashSet.iterator();
        while (it.hasNext()) {
            if (abstractC6239n.E().equals(it.next().E())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    protected boolean U1(AbstractC6239n abstractC6239n) {
        return abstractC6239n.isDirectory();
    }

    public synchronized void V1(AbstractC6239n abstractC6239n) {
        try {
            String strU = abstractC6239n.U();
            if (strU != null && strU.equals(d2(u()))) {
                if (this.A == null) {
                    this.A = new HashSet<>();
                }
                if (!AbstractC6238m.k(this.A, abstractC6239n)) {
                    this.A.add(a1(abstractC6239n.E()));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public AbstractC6239n Y1(AbstractC6239n abstractC6239n) {
        File file = new File(((C6249y) abstractC6239n).G0(), ".folderimage");
        if (file.exists() && !file.isDirectory()) {
            return new C6246v(this, file, D.get(EnumC2019z.IMAGE), ((C6246v) abstractC6239n).C0());
        }
        AbstractC6239n abstractC6239nA2 = a2(abstractC6239n);
        if (u() == ax.U2.f.F0 && abstractC6239nA2 != null) {
            InputStream inputStreamS0 = s0(abstractC6239nA2.E());
            if (inputStreamS0 == null) {
                return c2(abstractC6239n);
            }
            try {
                inputStreamS0.close();
            } catch (IOException unused) {
            }
        }
        return abstractC6239nA2;
    }

    public EnumC2019z Z1() {
        if (this.z == null) {
            this.z = ax.p086f3.A.h(u());
        }
        return this.z;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) {
        return new C6246v(this, new File(str), D.get(Z1()), null);
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        if (str == null || !ax.p086f3.B.I(str)) {
            return super.b1(str, str2, str3);
        }
        C6246v c6246vB2 = b2(str2);
        if (c6246vB2 == null) {
            c6246vB2 = (C6246v) a1(str2);
        }
        AbstractC6239n abstractC6239nY1 = Y1(c6246vB2);
        return abstractC6239nY1 != null ? super.b1(str, abstractC6239nY1.E(), str3) : ax.p358w3.b.n();
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        C6246v c6246v = (C6246v) abstractC6239n;
        ArrayList arrayList = new ArrayList();
        if (!d0.E(abstractC6239n)) {
            List<AbstractC6239n> listD1 = super.d1(c6246v, fVar);
            if (listD1 != null) {
                a aVarE1 = c6246v.E1();
                for (AbstractC6239n abstractC6239n2 : listD1) {
                    if (aVarE1 == null || aVarE1.b(abstractC6239n2.B())) {
                        arrayList.add(new C6246v(this, (C6249y) abstractC6239n2, aVarE1));
                    }
                }
            }
        } else {
            if (ax.p038c3.b.k().f(abstractC6239n)) {
                return h2(abstractC6239n, ax.p038c3.b.k().g(abstractC6239n), false);
            }
            try {
                C6247w.C(p(), null).l();
                return ax.p038c3.b.k().g(abstractC6239n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        if (str == null || !ax.p086f3.B.I(str)) {
            return super.e1(str, str2);
        }
        C6246v c6246vB2 = b2(str2);
        if (c6246vB2 == null) {
            c6246vB2 = (C6246v) a1(str2);
        }
        AbstractC6239n abstractC6239nY1 = Y1(c6246vB2);
        if (abstractC6239nY1 != null) {
            return super.e1(str, abstractC6239nY1.E());
        }
        return 0;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (ax.U2.f.k0(u()) || ((ax.p006a3.Q.r1() && EnumC2019z.PDF == abstractC6239n.G()) || "epub".equals(abstractC6239n.y()))) {
            return abstractC6239n.isDirectory() ? ax.p086f3.B.T(abstractC6239n) : ax.p086f3.B.W(abstractC6239n);
        }
        return null;
    }

    public AbstractC6239n g2(C6246v c6246v) {
        AbstractC6239n abstractC6239n;
        try {
            List<AbstractC6239n> listJ = J(c6246v);
            long jQ = 0;
            if (listJ != null) {
                abstractC6239n = null;
                for (AbstractC6239n abstractC6239n2 : listJ) {
                    if (!abstractC6239n2.g()) {
                        if (abstractC6239n == null) {
                            jQ = abstractC6239n2.q();
                            abstractC6239n = abstractC6239n2;
                        } else {
                            long jQ2 = abstractC6239n2.q();
                            if (jQ2 > jQ) {
                                abstractC6239n = abstractC6239n2;
                                jQ = jQ2;
                            }
                        }
                    }
                }
                c6246v.e0(listJ.size());
            } else {
                abstractC6239n = null;
            }
            if (abstractC6239n != null) {
                c6246v.K1(abstractC6239n.B());
                c6246v.J1(jQ);
                return abstractC6239n;
            }
        } catch (C1966j unused) {
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.C6248x
    public C6249y h0(File file, ax.p086f3.K k, boolean z) {
        return z ? new C6246v(this, new C6249y(this, file, k, true), D.get(Z1())) : new C6246v(this, file, D.get(Z1()), k);
    }

    public List<AbstractC6239n> h2(AbstractC6239n abstractC6239n, List<AbstractC6239n> list, boolean z) {
        if (list == null) {
            return null;
        }
        if (d0.E(abstractC6239n) && this.A != null) {
            ArrayList arrayList = new ArrayList(list);
            boolean z2 = false;
            for (AbstractC6239n abstractC6239n2 : this.A) {
                if (abstractC6239n2.o() && !AbstractC6238m.k(list, abstractC6239n2)) {
                    arrayList.add(abstractC6239n2);
                    z2 = true;
                }
            }
            if (z2) {
                ax.p038c3.b.k().m(abstractC6239n, arrayList);
                list = arrayList;
            }
        }
        if (z) {
            Iterator<AbstractC6239n> it = list.iterator();
            while (it.hasNext()) {
                C6246v c6246v = (C6246v) it.next();
                if (c6246v.r0()) {
                    g2(c6246v);
                }
            }
        }
        return list;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        AbstractC6239n abstractC6239nA1;
        List<AbstractC6239n> listG;
        super.i1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
        if (U1(abstractC6239n) && (listG = ax.p038c3.b.k().g((abstractC6239nA1 = a1(x())))) != null && listG.contains(abstractC6239n)) {
            ArrayList arrayList = new ArrayList(listG);
            arrayList.remove(abstractC6239n);
            boolean zF2 = f2(abstractC6239n);
            if (!J.k2(abstractC6239n2)) {
                AbstractC6239n abstractC6239nA2 = a1(abstractC6239n2.E());
                arrayList.add(abstractC6239nA2);
                if (zF2 && abstractC6239n.U().equals(abstractC6239n2.U())) {
                    V1(abstractC6239nA2);
                }
            }
            ax.p038c3.b.k().m(abstractC6239nA1, arrayList);
        }
        if (abstractC6239n.U().equals(abstractC6239n2.U())) {
            return;
        }
        e2(abstractC6239n.U());
        e2(abstractC6239n2.U());
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        super.l1(abstractC6239n, g, str, j, l, c6241p, z, interfaceC2462c, iVar);
        e2(abstractC6239n.U());
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        super.m1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        boolean zN1 = super.n1(abstractC6239n);
        if (zN1) {
            V1(abstractC6239n);
        }
        return zN1;
    }

    @Override // com.alphainventor.filemanager.file.C6248x
    protected void q0(AbstractC6239n abstractC6239n, List<AbstractC6239n> list, boolean z, String str, boolean z2, ax.p182l3.h hVar) {
        List<AbstractC6239n> listH = ax.p086f3.B.h(list, str, z2, true);
        if (listH == null) {
            return;
        }
        if (abstractC6239n instanceof C6246v) {
            a aVarE1 = ((C6246v) abstractC6239n).E1();
            if (aVarE1 != null) {
                Iterator<AbstractC6239n> it = listH.iterator();
                while (it.hasNext()) {
                    if (!aVarE1.b(it.next().B())) {
                        it.remove();
                    }
                }
            }
        } else {
            C2461b.f();
        }
        hVar.i0(listH, z);
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        if (abstractC6239n.isDirectory()) {
            if (J(abstractC6239n).size() == 0) {
                W1(this, abstractC6239n);
                X1(abstractC6239n);
            }
            if ((abstractC6239n instanceof C6249y) && abstractC6239n.E().equals(((C6249y) abstractC6239n).C0().e())) {
                return;
            }
        } else {
            e2(abstractC6239n.U());
        }
        super.q1(abstractC6239n);
    }
}
