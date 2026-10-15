package com.alphainventor.filemanager.file;

import android.annotation.SuppressLint;
import ax.I1.C0710a;
import ax.I1.C0718i;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.EnumC0738t;
import ax.J1.V;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5843q extends C5845t {
    private static final Logger A = Logger.getLogger("FileManager.LibraryFileHelper");
    static SimpleDateFormat B = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
    private static final Map<EnumC0738t, a> C;
    private EnumC0738t z;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$a */
    static class a implements FilenameFilter {
        EnumC0738t q;

        public a(EnumC0738t enumC0738t) {
            this.q = enumC0738t;
        }

        public boolean a(String str) {
            if (V.x(str)) {
                return C0739u.v(V.j(str), this.q);
            }
            return false;
        }

        @Override // java.io.FilenameFilter
        @SuppressLint({"DefaultLocale"})
        public boolean accept(File file, String str) {
            return a(str);
        }
    }

    static {
        HashMap map = new HashMap();
        C = map;
        EnumC0738t enumC0738t = EnumC0738t.IMAGE;
        map.put(enumC0738t, new a(enumC0738t));
        EnumC0738t enumC0738t2 = EnumC0738t.VIDEO;
        map.put(enumC0738t2, new a(enumC0738t2));
        EnumC0738t enumC0738t3 = EnumC0738t.AUDIO;
        map.put(enumC0738t3, new a(enumC0738t3));
        EnumC0738t enumC0738t4 = EnumC0738t.TEXT;
        map.put(enumC0738t4, new a(enumC0738t4));
        EnumC0738t enumC0738t5 = EnumC0738t.PRESENTATION;
        map.put(enumC0738t5, new a(enumC0738t5));
        EnumC0738t enumC0738t6 = EnumC0738t.SPREADSHEET;
        map.put(enumC0738t6, new a(enumC0738t6));
        EnumC0738t enumC0738t7 = EnumC0738t.ARCHIVE;
        map.put(enumC0738t7, new a(enumC0738t7));
        EnumC0738t enumC0738t8 = EnumC0738t.GROUP_DOCUMENT;
        map.put(enumC0738t8, new a(enumC0738t8));
    }

    public static void M1(AbstractC5837k abstractC5837k, AbstractC5838l abstractC5838l) throws C0718i {
        AbstractC5838l abstractC5838lV0 = abstractC5837k.V0(abstractC5837k.u());
        List<AbstractC5838l> listG = ax.G1.b.k().g(abstractC5838lV0);
        if (listG != null) {
            ArrayList arrayList = new ArrayList(listG);
            if (arrayList.remove(abstractC5838l)) {
                ax.G1.b.k().m(abstractC5838lV0, arrayList);
            }
        }
    }

    private AbstractC5838l Q1(AbstractC5838l abstractC5838l) {
        a aVar = C.get(P1());
        r rVar = (r) abstractC5838l;
        String strT1 = rVar.t1();
        ax.J1.F fV0 = rVar.v0();
        if (strT1 != null) {
            File file = new File(rVar.y0(), strT1);
            if (file.exists()) {
                return new r(this, file, aVar, fV0);
            }
        }
        A.fine("WARN : GetFirstFileInfoFromDirectory ListFiles");
        return U1(rVar);
    }

    private r R1(String str) {
        List<AbstractC5838l> listG = ax.G1.b.k().g((r) V0(u()));
        if (listG == null) {
            return null;
        }
        for (AbstractC5838l abstractC5838l : listG) {
            if (abstractC5838l.C().equals(str)) {
                return (r) abstractC5838l;
            }
        }
        return null;
    }

    private AbstractC5838l S1(AbstractC5838l abstractC5838l) {
        a aVar = C.get(EnumC0738t.IMAGE);
        r rVar = (r) abstractC5838l;
        File[] fileArrListFiles = rVar.y0().listFiles(aVar);
        if (fileArrListFiles.length > 0) {
            return new r(this, fileArrListFiles[0], aVar, rVar.v0());
        }
        return null;
    }

    private void T1(String str) {
        r rVarR1 = R1(str);
        if (rVarR1 != null) {
            rVarR1.b0(-3);
        }
    }

    protected boolean L1(AbstractC5838l abstractC5838l) {
        return abstractC5838l.isDirectory();
    }

    public AbstractC5838l N1(AbstractC5838l abstractC5838l) {
        File file = new File(((u) abstractC5838l).y0(), ".folderimage");
        if (file.exists() && !file.isDirectory()) {
            return new r(this, file, C.get(EnumC0738t.IMAGE), ((r) abstractC5838l).v0());
        }
        AbstractC5838l abstractC5838lQ1 = Q1(abstractC5838l);
        if (r() == ax.p338y1.f.C0 && abstractC5838lQ1 != null) {
            InputStream inputStreamL0 = l0(abstractC5838lQ1.C());
            if (inputStreamL0 == null) {
                return S1(abstractC5838l);
            }
            try {
                inputStreamL0.close();
            } catch (IOException unused) {
            }
        }
        return abstractC5838lQ1;
    }

    public AbstractC5838l O1(File file, ax.J1.F f) {
        return new r(this, file, C.get(P1()), f);
    }

    public EnumC0738t P1() {
        if (this.z == null) {
            this.z = C0739u.f(r());
        }
        return this.z;
    }

    public AbstractC5838l U1(r rVar) {
        AbstractC5838l abstractC5838l;
        try {
            List<AbstractC5838l> listG1 = g1(rVar);
            long jQ = 0;
            if (listG1 != null) {
                abstractC5838l = null;
                for (AbstractC5838l abstractC5838l2 : listG1) {
                    if (!abstractC5838l2.g()) {
                        if (abstractC5838l == null) {
                            jQ = abstractC5838l2.q();
                            abstractC5838l = abstractC5838l2;
                        } else {
                            long jQ2 = abstractC5838l2.q();
                            if (jQ2 > jQ) {
                                abstractC5838l = abstractC5838l2;
                                jQ = jQ2;
                            }
                        }
                    }
                }
                rVar.b0(listG1.size());
            } else {
                abstractC5838l = null;
            }
            if (abstractC5838l != null) {
                rVar.w1(abstractC5838l.w());
                rVar.v1(jQ);
                return abstractC5838l;
            }
        } catch (C0718i unused) {
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) {
        return new r(this, new File(str), C.get(P1()), null);
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        if (str == null || !C0740v.B(str)) {
            return super.W0(str, str2, str3);
        }
        r rVarR1 = R1(str2);
        if (rVarR1 == null) {
            rVarR1 = (r) V0(str2);
        }
        AbstractC5838l abstractC5838lN1 = N1(rVarR1);
        return abstractC5838lN1 != null ? super.W0(str, abstractC5838lN1.C(), str3) : ax.p003a2.b.n();
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        if (str == null || !C0740v.B(str)) {
            return super.Y0(str, str2);
        }
        r rVarR1 = R1(str2);
        if (rVarR1 == null) {
            rVarR1 = (r) V0(str2);
        }
        AbstractC5838l abstractC5838lN1 = N1(rVarR1);
        if (abstractC5838lN1 != null) {
            return super.Y0(str, abstractC5838lN1.C());
        }
        return 0;
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (ax.p338y1.f.j0(r())) {
            return abstractC5838l.isDirectory() ? C0740v.L(abstractC5838l) : C0740v.O(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        super.a1(abstractC5838l, abstractC5838l2, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        super.c1(abstractC5838l, a2, str, j, l, c5840n, z, cVar, iVar);
        T1(abstractC5838l.S());
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        r rVar = (r) abstractC5838l;
        ArrayList arrayList = new ArrayList();
        if (!V.B(abstractC5838l)) {
            List<AbstractC5838l> listG1 = super.g1(rVar);
            if (listG1 != null) {
                a aVarQ1 = rVar.q1();
                for (AbstractC5838l abstractC5838l2 : listG1) {
                    if (aVarQ1 == null || aVarQ1.a(abstractC5838l2.w())) {
                        arrayList.add(new r(this, (u) abstractC5838l2, aVarQ1));
                    }
                }
            }
        } else {
            if (ax.G1.b.k().f(abstractC5838l)) {
                return ax.G1.b.k().g(abstractC5838l);
            }
            try {
                C5844s.C(n(), null).l();
                return ax.G1.b.k().g(abstractC5838l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.C5845t
    protected void j0(AbstractC5838l abstractC5838l, List<AbstractC5838l> list, boolean z, String str, boolean z2, ax.P1.h hVar) {
        List<AbstractC5838l> listE = C0740v.e(list, str, z2, true);
        if (listE == null) {
            return;
        }
        if (abstractC5838l instanceof r) {
            a aVarQ1 = ((r) abstractC5838l).q1();
            if (aVarQ1 != null) {
                Iterator<AbstractC5838l> it = listE.iterator();
                while (it.hasNext()) {
                    if (!aVarQ1.a(it.next().w())) {
                        it.remove();
                    }
                }
            }
        } else {
            ax.p045d2.b.f();
        }
        hVar.l0(listE, z);
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        AbstractC5838l abstractC5838lV0;
        List<AbstractC5838l> listG;
        super.k1(abstractC5838l, abstractC5838l2, cVar, iVar);
        if (L1(abstractC5838l) && (listG = ax.G1.b.k().g((abstractC5838lV0 = V0(u())))) != null && listG.contains(abstractC5838l)) {
            ArrayList arrayList = new ArrayList(listG);
            arrayList.remove(abstractC5838l);
            if (!E.b2(abstractC5838l2)) {
                arrayList.add(V0(abstractC5838l2.C()));
            }
            ax.G1.b.k().m(abstractC5838lV0, arrayList);
        }
        if (abstractC5838l.S().equals(abstractC5838l2.S())) {
            return;
        }
        T1(abstractC5838l.S());
        T1(abstractC5838l2.S());
    }

    @Override // com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        if (abstractC5838l.isDirectory()) {
            if (g1(abstractC5838l).size() == 0) {
                M1(this, abstractC5838l);
            }
            if ((abstractC5838l instanceof u) && abstractC5838l.C().equals(((u) abstractC5838l).v0().e())) {
                return;
            }
        } else {
            T1(abstractC5838l.S());
        }
        super.l1(abstractC5838l);
    }
}
