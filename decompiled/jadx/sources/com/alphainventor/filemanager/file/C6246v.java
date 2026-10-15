package com.alphainventor.filemanager.file;

import ax.p086f3.d0;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6246v extends C6249y {
    private C6245u.a Y0;
    private Long Z0;
    private String a1;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.v$a */
    class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (str != null && str.startsWith(".")) {
                return false;
            }
            if (C6246v.this.Y0 != null) {
                return C6246v.this.Y0.accept(file, str);
            }
            ax.p302sb.c.h().g().b("LIBRARY LOAD NUM CHILDREN").h("location:" + C6246v.this.Q() + ",path:" + C6246v.this.E()).i();
            return true;
        }
    }

    public C6246v(C6245u c6245u, File file, C6245u.a aVar, ax.p086f3.K k) {
        super(c6245u, file, k);
        this.Y0 = aVar;
    }

    public C6246v(C6248x c6248x, C6249y c6249y, C6245u.a aVar) {
        super(c6248x, c6249y);
        this.Y0 = aVar;
    }

    private static Set<String> D1(File file, File file2, HashMap<String, Set<String>> map) throws Throwable {
        String absolutePath = file.getAbsolutePath();
        Set<String> set = map.get(absolutePath);
        if (set != null) {
            return set;
        }
        if (!file2.exists()) {
            Set<String> set2 = Collections.EMPTY_SET;
            map.put(absolutePath, set2);
            return set2;
        }
        HashSet hashSet = new HashSet();
        Scanner scanner = null;
        try {
            try {
                Scanner scanner2 = new Scanner(file2);
                while (scanner2.hasNext()) {
                    try {
                        hashSet.add(scanner2.nextLine());
                    } catch (FileNotFoundException unused) {
                        scanner = scanner2;
                        Set<String> set3 = Collections.EMPTY_SET;
                        map.put(absolutePath, set3);
                        if (scanner != null) {
                            scanner.close();
                        }
                        return set3;
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
                if (hashSet.isEmpty()) {
                    file2.delete();
                }
                map.put(absolutePath, hashSet);
                return hashSet;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException unused2) {
        }
    }

    private static C6249y.b F1(ax.p086f3.K k, File file, String str, String str2, Boolean bool, Boolean bool2, HashMap<String, C6249y.b> map, HashMap<String, Set<String>> map2) {
        File parentFile;
        C6249y.b bVar;
        String absolutePath = file.isDirectory() ? file.getAbsolutePath() : file.getParent();
        if (map != null && map.containsKey(absolutePath)) {
            return map.get(absolutePath);
        }
        File file2 = new File(absolutePath);
        boolean zEquals = str.equals(absolutePath);
        Set<String> setG = C6247w.G();
        if (d0.z(absolutePath)) {
            C6249y.b bVar2 = C6249y.b.HIDDEN_OS;
            map.put(absolutePath, bVar2);
            return bVar2;
        }
        if (bool == null) {
            bool = Boolean.valueOf(new File(absolutePath, ".nomedia").exists());
        }
        if (!zEquals && bool.booleanValue()) {
            C6249y.b bVar3 = C6249y.b.HIDDEN_NOMEDIA;
            map.put(absolutePath, bVar3);
            return bVar3;
        }
        if ((bool2 == null || bool2.booleanValue()) && I1(k, file2, map2)) {
            C6249y.b bVar4 = C6249y.b.HIDDEN_DOTHIDDEN;
            map.put(absolutePath, bVar4);
            return bVar4;
        }
        if (setG.contains(str2)) {
            C6249y.b bVar5 = C6249y.b.HIDDEN_HIDELIST;
            map.put(absolutePath, bVar5);
            return bVar5;
        }
        if (!zEquals && (parentFile = file2.getParentFile()) != null && (bVar = map.get(parentFile.getAbsolutePath())) != null) {
            C6249y.b bVar6 = C6249y.b.VISIBLE;
            if (bVar == bVar6) {
                map.put(absolutePath, bVar6);
                return bVar6;
            }
            C6249y.b bVar7 = C6249y.b.HIDDEN_PARENT;
            map.put(absolutePath, bVar7);
            return bVar7;
        }
        String strQ = File.separator;
        if (absolutePath.equals(strQ)) {
            C6249y.b bVar8 = C6249y.b.VISIBLE;
            map.put(absolutePath, bVar8);
            return bVar8;
        }
        for (String str3 : d0.X(absolutePath)) {
            strQ = d0.Q(strQ, str3);
            if (strQ.length() > str.length()) {
                String strSubstring = strQ.substring(str.length());
                if (map == null || !map.containsKey(strQ)) {
                    if (absolutePath.equals(strQ)) {
                        C6249y.b bVar9 = C6249y.b.VISIBLE;
                        map.put(absolutePath, bVar9);
                        return bVar9;
                    }
                    File file3 = new File(strQ);
                    File file4 = new File(strQ, ".nomedia");
                    if (d0.z(strQ)) {
                        map.put(strQ, C6249y.b.HIDDEN_OS);
                        C6249y.b bVar10 = C6249y.b.HIDDEN_PARENT;
                        map.put(absolutePath, bVar10);
                        return bVar10;
                    }
                    if (file4.exists()) {
                        map.put(strQ, C6249y.b.HIDDEN_NOMEDIA);
                        C6249y.b bVar11 = C6249y.b.HIDDEN_PARENT;
                        map.put(absolutePath, bVar11);
                        return bVar11;
                    }
                    if (setG.contains(strSubstring)) {
                        map.put(strQ, C6249y.b.HIDDEN_HIDELIST);
                        C6249y.b bVar12 = C6249y.b.HIDDEN_PARENT;
                        map.put(absolutePath, bVar12);
                        return bVar12;
                    }
                    if (I1(k, file3, map2)) {
                        map.put(strQ, C6249y.b.HIDDEN_DOTHIDDEN);
                        C6249y.b bVar13 = C6249y.b.HIDDEN_PARENT;
                        map.put(absolutePath, bVar13);
                        return bVar13;
                    }
                    map.put(strQ, C6249y.b.VISIBLE);
                } else if (map.get(strQ) != C6249y.b.VISIBLE) {
                    C6249y.b bVar14 = C6249y.b.HIDDEN_PARENT;
                    map.put(absolutePath, bVar14);
                    return bVar14;
                }
            }
        }
        C6249y.b bVar15 = C6249y.b.VISIBLE;
        map.put(absolutePath, bVar15);
        return bVar15;
    }

    public static C6249y.b G1(C6246v c6246v, C6247w.e eVar, Boolean bool, HashMap<String, C6249y.b> map, HashMap<String, Set<String>> map2) {
        String strX;
        C6249y.b bVarC;
        ax.p086f3.K k = eVar.a;
        C6249y c6249y = eVar.c;
        String strE = c6249y.E();
        String strE2 = k.e();
        try {
            if (c6249y.g()) {
                return C6249y.b.HIDDEN_OS;
            }
            if (d0.H(strE2, strE)) {
                strX = d0.x(strE2, strE);
            } else if (d0.H("/sdcard", strE)) {
                strX = d0.x("/sdcard", strE);
                strE2 = "/sdcard";
            } else if (d0.H("/storage/emulated/0", strE)) {
                strE2 = "/storage/emulated/0";
                strX = d0.x("/storage/emulated/0", strE);
            } else {
                ax.p302sb.c.h().g().d("Invalid Hidden Media Path problem!").h(strE + ":" + strE2).i();
                strX = strE;
            }
            if (!d0.H("/Android/data", strX)) {
                String strF = ax.p038c3.g.h().f(c6246v);
                return (strF == null || (bVarC = ax.p038c3.g.c(strF)) == C6249y.b.VISIBLE) ? F1(k, c6249y.G0(), strE2, strX, eVar.d, bool, map, map2) : bVarC;
            }
            if (C6247w.S(strX)) {
                return eVar.d.booleanValue() ? C6249y.b.HIDDEN_NOMEDIA : C6249y.b.VISIBLE;
            }
            return C6249y.b.HIDDEN_DATA;
        } catch (StringIndexOutOfBoundsException unused) {
            ax.p302sb.c.h().d("IsHidenMedia Path problem!").h(strE + ":" + strE2).i();
            return C6249y.b.VISIBLE;
        }
    }

    private static boolean I1(ax.p086f3.K k, File file, HashMap<String, Set<String>> map) {
        if ("S".equals(ax.p038c3.g.h().e(k, null, file.getAbsolutePath()))) {
            return true;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return D1(parentFile, new File(parentFile, ".hidden"), map).contains(file.getName());
    }

    public String C1() {
        return ax.p086f3.B.U(C0(), E());
    }

    public C6245u.a E1() {
        return this.Y0;
    }

    public String H1() {
        return this.a1;
    }

    public void J1(long j) {
        this.Z0 = Long.valueOf(j);
    }

    public void K1(String str) {
        this.a1 = str;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C6246v)) {
            return G0().getAbsolutePath().equals(((C6246v) obj).G0().getAbsolutePath());
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.C6249y
    public void i1(boolean z) {
        if (Y() == -1 || Y() == -3) {
            String[] list = this.t0.list(z ? this.Y0 : new a());
            e0(list != null ? list.length : -1);
        }
    }

    @Override // com.alphainventor.filemanager.file.C6249y, ax.p086f3.InterfaceC1996b
    public long q() {
        Long l = this.Z0;
        return l != null ? l.longValue() : super.q();
    }
}
