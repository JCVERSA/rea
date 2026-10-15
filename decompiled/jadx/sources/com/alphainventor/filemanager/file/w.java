package com.alphainventor.filemanager.file;

import ax.J1.V;
import java.text.Collator;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class w {
    public String a;
    public boolean b;
    public long c;
    public long d;
    public long e;
    private String f;
    private String g;
    private String h;

    public static class a implements Comparator<w> {
        String X;
        c q = new c();

        public a(String str) {
            this.X = str;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(w wVar, w wVar2) {
            if (wVar.a().length() != wVar2.a().length()) {
                if (wVar.a().length() == 0) {
                    return -1;
                }
                if (wVar2.a().length() == 0) {
                    return 1;
                }
            }
            if (wVar.b().length() != wVar2.b().length() && ax.p045d2.e.c(wVar.b(), wVar2.b(), this.X)) {
                return ax.p045d2.x.b(wVar.b().length(), wVar2.b().length());
            }
            long j = wVar.c;
            long j2 = wVar2.c;
            if (j != j2) {
                return ax.p045d2.x.c(j, j2);
            }
            long j3 = wVar.e;
            long j4 = wVar2.e;
            if (j3 != j4) {
                return ax.p045d2.x.c(j3, j4);
            }
            return wVar.b().length() != wVar2.b().length() ? ax.p045d2.x.b(wVar.b().length(), wVar2.b().length()) : this.q.compare(wVar, wVar2);
        }
    }

    public static class b implements Comparator<w> {
        ax.A1.l X;
        c q = new c();

        public b(ax.A1.l lVar) {
            this.X = lVar;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(w wVar, w wVar2) {
            int iB;
            long j = wVar2.d;
            long j2 = wVar.d;
            if (j != j2) {
                return ax.p045d2.x.c(j, j2);
            }
            ax.A1.l lVar = this.X;
            if (lVar != null) {
                ax.A1.l.a aVarB = lVar.b(wVar);
                ax.A1.l.a aVarB2 = this.X.b(wVar2);
                if (aVarB == null && aVarB2 != null) {
                    return -1;
                }
                if (aVarB != null && aVarB2 == null) {
                    return 1;
                }
                if ((aVarB != null || aVarB2 != null) && (iB = this.q.b(aVarB.d, aVarB2.d)) != 0) {
                    return iB;
                }
            }
            return this.q.compare(wVar, wVar2);
        }
    }

    public static class c implements Comparator<w> {
        Collator X;
        ax.p045d2.a q;

        public c() {
            Collator collator = Collator.getInstance();
            this.X = collator;
            try {
                collator.setDecomposition(1);
            } catch (IllegalArgumentException unused) {
            }
            this.X.setStrength(3);
            this.q = new ax.p045d2.a(this.X);
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(w wVar, w wVar2) {
            return b(wVar.b(), wVar2.b());
        }

        public int b(String str, String str2) {
            try {
                return this.q.compare(str, str2);
            } catch (Exception unused) {
                return this.X.compare(str, str2);
            }
        }
    }

    public static class d implements Comparator<w> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(w wVar, w wVar2) {
            return ax.p045d2.x.c(wVar2.d, wVar.d);
        }
    }

    w(String str, boolean z, long j, long j2, long j3) {
        this.a = str;
        this.b = z;
        this.c = j;
        this.d = j3;
        this.e = j2;
    }

    public String a() {
        if (this.h == null) {
            this.h = V.f(b());
        }
        return this.h;
    }

    public String b() {
        if (this.f == null) {
            this.f = V.h(this.a);
        }
        return this.f;
    }

    public String c() {
        if (this.g == null) {
            this.g = V.r(this.a);
        }
        return this.g;
    }
}
