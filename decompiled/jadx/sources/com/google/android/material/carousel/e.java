package com.google.android.material.carousel;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class e {
    private final float a;
    private int b;
    private final List<c> c;
    private final int d;
    private final int e;
    private final int f;

    public static final class b {
        private final float a;
        private final int b;
        private c d;
        private c e;
        private final List<c> c = new ArrayList();
        private int f = -1;
        private int g = -1;
        private float h = 0.0f;
        private int i = -1;

        public b(float f, int i) {
            this.a = f;
            this.b = i;
        }

        private static float j(float f, float f2, int i, int i2) {
            return (f - (i * f2)) + (i2 * f2);
        }

        public b a(float f, float f2, float f3) {
            return d(f, f2, f3, false, true);
        }

        public b b(float f, float f2, float f3) {
            return c(f, f2, f3, false);
        }

        public b c(float f, float f2, float f3, boolean z) {
            return d(f, f2, f3, z, false);
        }

        public b d(float f, float f2, float f3, boolean z, boolean z2) {
            float f4;
            float fAbs;
            float f5 = f3 / 2.0f;
            float f6 = f - f5;
            float f7 = f5 + f;
            int i = this.b;
            if (f7 <= i) {
                if (f6 < 0.0f) {
                    fAbs = Math.abs(f6 - Math.min(f6 + f3, 0.0f));
                } else {
                    f4 = 0.0f;
                }
                return e(f, f2, f3, z, z2, f4);
            }
            fAbs = Math.abs(f7 - Math.max(f7 - f3, i));
            f4 = fAbs;
            return e(f, f2, f3, z, z2, f4);
        }

        public b e(float f, float f2, float f3, boolean z, boolean z2, float f4) {
            return f(f, f2, f3, z, z2, f4, 0.0f, 0.0f);
        }

        public b f(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5, float f6) {
            if (f3 <= 0.0f) {
                return this;
            }
            if (z2) {
                if (z) {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
                int i = this.i;
                if (i != -1 && i != 0) {
                    throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                }
                this.i = this.c.size();
            }
            c cVar = new c(Float.MIN_VALUE, f, f2, f3, z2, f4, f5, f6);
            if (z) {
                if (this.d == null) {
                    this.d = cVar;
                    this.f = this.c.size();
                }
                if (this.g != -1 && this.c.size() - this.g > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                }
                if (f3 != this.d.d) {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
                this.e = cVar;
                this.g = this.c.size();
            } else {
                if (this.d == null && cVar.d < this.h) {
                    throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
                }
                if (this.e != null && cVar.d > this.h) {
                    throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
                }
            }
            this.h = cVar.d;
            this.c.add(cVar);
            return this;
        }

        public b g(float f, float f2, float f3, int i) {
            return h(f, f2, f3, i, false);
        }

        public b h(float f, float f2, float f3, int i, boolean z) {
            if (i > 0 && f3 > 0.0f) {
                for (int i2 = 0; i2 < i; i2++) {
                    c((i2 * f3) + f, f2, f3, z);
                }
            }
            return this;
        }

        public e i() {
            if (this.d == null) {
                throw new IllegalStateException("There must be a keyline marked as focal.");
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.c.size(); i++) {
                c cVar = this.c.get(i);
                arrayList.add(new c(j(this.d.b, this.a, this.f, i), cVar.b, cVar.c, cVar.d, cVar.e, cVar.f, cVar.g, cVar.h));
            }
            return new e(this.a, arrayList, this.f, this.g, this.b);
        }
    }

    static final class c {
        final float a;
        final float b;
        final float c;
        final float d;
        final boolean e;
        final float f;
        final float g;
        final float h;

        c(float f, float f2, float f3, float f4) {
            this(f, f2, f3, f4, false, 0.0f, 0.0f, 0.0f);
        }

        c(float f, float f2, float f3, float f4, boolean z, float f5, float f6, float f7) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
            this.e = z;
            this.f = f5;
            this.g = f6;
            this.h = f7;
        }

        static c a(c cVar, c cVar2, float f) {
            return new c(ax.S6.a.a(cVar.a, cVar2.a, f), ax.S6.a.a(cVar.b, cVar2.b, f), ax.S6.a.a(cVar.c, cVar2.c, f), ax.S6.a.a(cVar.d, cVar2.d, f));
        }
    }

    private e(float f, List<c> list, int i, int i2, int i3) {
        this.a = f;
        this.c = DesugarCollections.unmodifiableList(list);
        this.d = i;
        this.e = i2;
        while (i <= i2) {
            if (list.get(i).f == 0.0f) {
                this.b++;
            }
            i++;
        }
        this.f = i3;
    }

    static e o(e eVar, e eVar2, float f) {
        if (eVar.g() != eVar2.g()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List<c> listH = eVar.h();
        List<c> listH2 = eVar2.h();
        if (listH.size() != listH2.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < eVar.h().size(); i++) {
            arrayList.add(c.a(listH.get(i), listH2.get(i), f));
        }
        return new e(eVar.g(), arrayList, ax.S6.a.c(eVar.c(), eVar2.c(), f), ax.S6.a.c(eVar.j(), eVar2.j(), f), eVar.f);
    }

    static e p(e eVar, int i) {
        b bVar = new b(eVar.g(), i);
        float f = (i - eVar.k().b) - (eVar.k().d / 2.0f);
        int size = eVar.h().size() - 1;
        while (size >= 0) {
            c cVar = eVar.h().get(size);
            bVar.d((cVar.d / 2.0f) + f, cVar.c, cVar.d, size >= eVar.c() && size <= eVar.j(), cVar.e);
            f += cVar.d;
            size--;
        }
        return bVar.i();
    }

    int a() {
        return this.f;
    }

    c b() {
        return this.c.get(this.d);
    }

    int c() {
        return this.d;
    }

    c d() {
        return this.c.get(0);
    }

    c e() {
        for (int i = 0; i < this.c.size(); i++) {
            c cVar = this.c.get(i);
            if (!cVar.e) {
                return cVar;
            }
        }
        return null;
    }

    List<c> f() {
        return this.c.subList(this.d, this.e + 1);
    }

    float g() {
        return this.a;
    }

    List<c> h() {
        return this.c;
    }

    c i() {
        return this.c.get(this.e);
    }

    int j() {
        return this.e;
    }

    c k() {
        List<c> list = this.c;
        return list.get(list.size() - 1);
    }

    c l() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            c cVar = this.c.get(size);
            if (!cVar.e) {
                return cVar;
            }
        }
        return null;
    }

    int m() {
        Iterator<c> it = this.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().e) {
                i++;
            }
        }
        return this.c.size() - i;
    }

    int n() {
        return this.b;
    }
}
