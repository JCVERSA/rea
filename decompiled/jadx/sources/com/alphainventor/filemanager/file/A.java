package com.alphainventor.filemanager.file;

import ax.p086f3.d0;
import ax.p406z3.C2460a;
import java.text.Collator;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class A {
    public String a;
    public boolean b;
    public long c;
    public long d;
    public long e;
    public Long f;
    private String g;
    private String h;
    private String i;

    public static class a implements Comparator<A> {
        String X;
        b q = new b();

        public a(String str) {
            this.X = str;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(A a, A a2) {
            if (a.a().length() != a2.a().length()) {
                if (a.a().length() == 0) {
                    return -1;
                }
                if (a2.a().length() == 0) {
                    return 1;
                }
            }
            if (a.b().length() != a2.b().length() && ax.p406z3.e.c(a.b(), a2.b(), this.X)) {
                return ax.p406z3.B.b(a.b().length(), a2.b().length());
            }
            long j = a.c;
            long j2 = a2.c;
            if (j != j2) {
                return ax.p406z3.B.c(j, j2);
            }
            long j3 = a.e;
            long j4 = a2.e;
            if (j3 != j4) {
                return ax.p406z3.B.c(j3, j4);
            }
            return a.b().length() != a2.b().length() ? ax.p406z3.B.b(a.b().length(), a2.b().length()) : this.q.compare(a, a2);
        }
    }

    public static class b implements Comparator<A> {
        Collator X;
        C2460a q;

        public b() {
            Collator collator = Collator.getInstance();
            this.X = collator;
            try {
                collator.setDecomposition(1);
            } catch (IllegalArgumentException unused) {
            }
            this.X.setStrength(3);
            this.q = new C2460a(this.X);
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(A a, A a2) {
            return b(a.b(), a2.b());
        }

        public int b(String str, String str2) {
            try {
                return this.q.compare(str, str2);
            } catch (Exception unused) {
                return this.X.compare(str, str2);
            }
        }
    }

    public static class c implements Comparator<A> {
        b q = new b();

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(A a, A a2) {
            return a.b().length() != a2.b().length() ? ax.p406z3.B.b(a.b().length(), a2.b().length()) : this.q.compare(a, a2);
        }
    }

    A(String str, boolean z, long j, Long l, long j2, long j3) {
        this.a = str;
        this.b = z;
        this.c = j;
        this.f = l;
        this.d = j3;
        this.e = j2;
    }

    public String a() {
        if (this.i == null) {
            this.i = d0.f(b());
        }
        return this.i;
    }

    public String b() {
        if (this.g == null) {
            this.g = d0.h(this.a);
        }
        return this.g;
    }

    public String c() {
        if (this.h == null) {
            this.h = d0.r(this.a);
        }
        return this.h;
    }
}
