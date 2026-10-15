package com.alphainventor.filemanager.file;

import ax.J1.C0740v;
import ax.J1.V;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class r extends u {
    private C5843q.a R0;
    private Long S0;
    private String T0;

    class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (str != null && str.startsWith(".")) {
                return false;
            }
            if (r.this.R0 != null) {
                return r.this.R0.accept(file, str);
            }
            ax.p221pa.c.h().g().b("LIBRARY LOAD NUM CHILDREN").h("location:" + r.this.P() + ",path:" + r.this.C()).i();
            return true;
        }
    }

    public r(C5843q c5843q, File file, C5843q.a aVar, ax.J1.F f) {
        super(c5843q, file, f);
        this.R0 = aVar;
    }

    private static u.b r1(ax.J1.F f, File file, String str, String str2, Boolean bool, Boolean bool2, HashMap<String, u.b> map) {
        String absolutePath = file.isDirectory() ? file.getAbsolutePath() : file.getParent();
        if (map != null && map.containsKey(absolutePath)) {
            return map.get(absolutePath);
        }
        File file2 = new File(absolutePath);
        boolean zEquals = str.equals(absolutePath);
        Set<String> setG = C5844s.G();
        if (file2.isHidden()) {
            u.b bVar = u.b.HIDDEN_OS;
            map.put(absolutePath, bVar);
            return bVar;
        }
        if (bool == null) {
            bool = Boolean.valueOf(new File(absolutePath, ".nomedia").exists());
        }
        if (!zEquals && bool.booleanValue()) {
            u.b bVar2 = u.b.HIDDEN_NOMEDIA;
            map.put(absolutePath, bVar2);
            return bVar2;
        }
        if ((bool2 == null || bool2.booleanValue()) && u1(f, file2)) {
            u.b bVar3 = u.b.HIDDEN_DOTHIDDEN;
            map.put(absolutePath, bVar3);
            return bVar3;
        }
        if (setG.contains(str2)) {
            u.b bVar4 = u.b.HIDDEN_HIDELIST;
            map.put(absolutePath, bVar4);
            return bVar4;
        }
        String strM = File.separator;
        if (absolutePath.equals(strM)) {
            u.b bVar5 = u.b.VISIBLE;
            map.put(absolutePath, bVar5);
            return bVar5;
        }
        for (String str3 : V.T(absolutePath)) {
            strM = V.M(strM, str3);
            if (strM.length() > str.length()) {
                String strSubstring = strM.substring(str.length());
                if (map == null || !map.containsKey(strM)) {
                    if (absolutePath.equals(strM)) {
                        u.b bVar6 = u.b.VISIBLE;
                        map.put(absolutePath, bVar6);
                        return bVar6;
                    }
                    File file3 = new File(strM);
                    File file4 = new File(strM, ".nomedia");
                    if (file3.isHidden()) {
                        map.put(strM, u.b.HIDDEN_OS);
                        u.b bVar7 = u.b.HIDDEN_PARENT;
                        map.put(absolutePath, bVar7);
                        return bVar7;
                    }
                    if (file4.exists()) {
                        map.put(strM, u.b.HIDDEN_NOMEDIA);
                        u.b bVar8 = u.b.HIDDEN_PARENT;
                        map.put(absolutePath, bVar8);
                        return bVar8;
                    }
                    if (setG.contains(strSubstring)) {
                        map.put(strM, u.b.HIDDEN_HIDELIST);
                        map.put(absolutePath, u.b.HIDDEN_PARENT);
                    } else {
                        if (u1(f, file3)) {
                            map.put(strM, u.b.HIDDEN_DOTHIDDEN);
                            u.b bVar9 = u.b.HIDDEN_PARENT;
                            map.put(absolutePath, bVar9);
                            return bVar9;
                        }
                        map.put(strM, u.b.VISIBLE);
                    }
                } else if (map.get(strM) != u.b.VISIBLE) {
                    u.b bVar10 = u.b.HIDDEN_PARENT;
                    map.put(absolutePath, bVar10);
                    return bVar10;
                }
            }
        }
        u.b bVar11 = u.b.VISIBLE;
        map.put(absolutePath, bVar11);
        return bVar11;
    }

    public static u.b s1(r rVar, C5844s.e eVar, Boolean bool, HashMap<String, u.b> map) {
        String strSubstring;
        u.b bVarC;
        String str = "/storage/emulated/0";
        ax.J1.F f = eVar.a;
        u uVar = eVar.c;
        String strC = uVar.C();
        String strE = f.e();
        try {
            if (uVar.g()) {
                return u.b.HIDDEN_OS;
            }
            if (strC.startsWith(strE)) {
                strSubstring = strC.substring(strE.length());
                str = strE;
            } else if (strC.startsWith("/sdcard")) {
                strSubstring = strC.substring(7);
                str = "/sdcard";
            } else if (strC.startsWith("/storage/emulated/0")) {
                strSubstring = strC.substring(19);
            } else {
                ax.p221pa.c.h().g().d("Invalid Hidden Media Path problem!").h(strC + ":" + strE).i();
                str = strE;
                strSubstring = strC;
            }
            try {
                if (!strSubstring.startsWith("/Android/data")) {
                    String strF = ax.G1.f.h().f(rVar);
                    return (strF == null || (bVarC = ax.G1.f.c(strF)) == u.b.VISIBLE) ? r1(f, uVar.y0(), str, strSubstring, eVar.d, bool, map) : bVarC;
                }
                if (C5844s.S(strSubstring)) {
                    return eVar.d.booleanValue() ? u.b.HIDDEN_NOMEDIA : u.b.VISIBLE;
                }
                return u.b.HIDDEN_DATA;
            } catch (StringIndexOutOfBoundsException unused) {
                strE = str;
                ax.p221pa.c.h().d("IsHidenMedia Path problem!").h(strC + ":" + strE).i();
                return u.b.VISIBLE;
            }
        } catch (StringIndexOutOfBoundsException unused2) {
        }
    }

    public static boolean u1(ax.J1.F f, File file) throws Throwable {
        Scanner scanner = null;
        if ("S".equals(ax.G1.f.h().e(f, null, file.getAbsolutePath()))) {
            return true;
        }
        File file2 = new File(file.getParentFile(), ".hidden");
        if (!file2.exists()) {
            return false;
        }
        try {
            Scanner scanner2 = new Scanner(file2);
            while (scanner2.hasNext()) {
                try {
                    if (file.getName().equals(scanner2.nextLine())) {
                        scanner2.close();
                        return true;
                    }
                } catch (FileNotFoundException unused) {
                    scanner = scanner2;
                    if (scanner != null) {
                        scanner.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    scanner = scanner2;
                    if (scanner != null) {
                        scanner.close();
                    }
                    throw th;
                }
            }
            scanner2.close();
            return false;
        } catch (FileNotFoundException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.alphainventor.filemanager.file.u
    public void W0(boolean z) {
        if (W() == -1 || W() == -3) {
            String[] list = this.q0.list(z ? this.R0 : new a());
            b0(list != null ? list.length : -1);
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof r)) {
            return y0().getAbsolutePath().equals(((r) obj).y0().getAbsolutePath());
        }
        return false;
    }

    public String p1() {
        return C0740v.M(v0(), C());
    }

    @Override // com.alphainventor.filemanager.file.u, ax.J1.InterfaceC0722c
    public long q() {
        Long l = this.S0;
        return l != null ? l.longValue() : super.q();
    }

    public C5843q.a q1() {
        return this.R0;
    }

    public String t1() {
        return this.T0;
    }

    public void v1(long j) {
        this.S0 = Long.valueOf(j);
    }

    public void w1(String str) {
        this.T0 = str;
    }

    public r(C5845t c5845t, u uVar, C5843q.a aVar) {
        super(c5845t, uVar);
        this.R0 = aVar;
    }
}
