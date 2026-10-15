package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import ax.R6.m;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.carousel.CarouselLayoutManager;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class CarouselLayoutManager extends RecyclerView.p implements ax.p026b7.a, RecyclerView.A.b {
    private int A;
    private Map<Integer, e> B;
    private com.google.android.material.carousel.b C;
    private final View.OnLayoutChangeListener D;
    private int E;
    private int F;
    private int G;
    int s;
    int t;
    int u;
    private boolean v;
    private final c w;
    private com.google.android.material.carousel.c x;
    private f y;
    private e z;

    class a extends l {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.A
        public PointF a(int i) {
            return CarouselLayoutManager.this.c(i);
        }

        @Override // androidx.recyclerview.widget.l
        public int t(View view, int i) {
            if (CarouselLayoutManager.this.y == null || !CarouselLayoutManager.this.g()) {
                return 0;
            }
            CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
            return carouselLayoutManager.h2(carouselLayoutManager.m0(view));
        }

        @Override // androidx.recyclerview.widget.l
        public int u(View view, int i) {
            if (CarouselLayoutManager.this.y == null || CarouselLayoutManager.this.g()) {
                return 0;
            }
            CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
            return carouselLayoutManager.h2(carouselLayoutManager.m0(view));
        }
    }

    private static final class b {
        final View a;
        final float b;
        final float c;
        final d d;

        b(View view, float f, float f2, d dVar) {
            this.a = view;
            this.b = f;
            this.c = f2;
            this.d = dVar;
        }
    }

    private static class c extends RecyclerView.o {
        private final Paint a;
        private List<e.c> b;

        c() {
            Paint paint = new Paint();
            this.a = paint;
            this.b = DesugarCollections.unmodifiableList(new ArrayList());
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public void k(Canvas canvas, RecyclerView recyclerView, RecyclerView.B b) {
            super.k(canvas, recyclerView, b);
            this.a.setStrokeWidth(recyclerView.getResources().getDimension(ax.R6.e.s));
            for (e.c cVar : this.b) {
                this.a.setColor(ax.T.a.c(-65281, -16776961, cVar.c));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).g()) {
                    canvas.drawLine(cVar.b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).B2(), cVar.b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).x2(), this.a);
                } else {
                    canvas.drawLine(((CarouselLayoutManager) recyclerView.getLayoutManager()).y2(), cVar.b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).z2(), cVar.b, this.a);
                }
            }
        }

        void l(List<e.c> list) {
            this.b = DesugarCollections.unmodifiableList(list);
        }
    }

    private static class d {
        final e.c a;
        final e.c b;

        d(e.c cVar, e.c cVar2) {
            ax.p019b0.g.a(cVar.a <= cVar2.a);
            this.a = cVar;
            this.b = cVar2;
        }
    }

    public CarouselLayoutManager() {
        this(new h());
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.v = false;
        this.w = new c();
        this.A = 0;
        this.D = new View.OnLayoutChangeListener() { // from class: ax.b7.b
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                CarouselLayoutManager.S1(this.a, view, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.F = -1;
        this.G = 0;
        T2(new h());
        S2(context, attributeSet);
    }

    public CarouselLayoutManager(com.google.android.material.carousel.c cVar) {
        this(cVar, 0);
    }

    public CarouselLayoutManager(com.google.android.material.carousel.c cVar, int i) {
        this.v = false;
        this.w = new c();
        this.A = 0;
        this.D = new View.OnLayoutChangeListener() { // from class: ax.b7.b
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                CarouselLayoutManager.S1(this.a, view, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.F = -1;
        this.G = 0;
        T2(cVar);
        U2(i);
    }

    private int A2() {
        return this.C.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int B2() {
        return this.C.j();
    }

    private int C2() {
        if (S()) {
            return 0;
        }
        return w2() == 1 ? i0() : k0();
    }

    private int D2(int i, e eVar) {
        return G2() ? (int) (((o2() - eVar.i().a) - (i * eVar.g())) - (eVar.g() / 2.0f)) : (int) (((i * eVar.g()) - eVar.b().a) + (eVar.g() / 2.0f));
    }

    private int E2(int i, e eVar) {
        int i2 = SubsamplingScaleImageView.TILE_SIZE_AUTO;
        for (e.c cVar : eVar.f()) {
            float fG = (i * eVar.g()) + (eVar.g() / 2.0f);
            int iO2 = (G2() ? (int) ((o2() - cVar.a) - fG) : (int) (fG - cVar.a)) - this.s;
            if (Math.abs(i2) > Math.abs(iO2)) {
                i2 = iO2;
            }
        }
        return i2;
    }

    private static d F2(List<e.c> list, float f, boolean z) {
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        float f5 = -3.4028235E38f;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < list.size(); i5++) {
            e.c cVar = list.get(i5);
            float f6 = z ? cVar.b : cVar.a;
            float fAbs = Math.abs(f6 - f);
            if (f6 <= f && fAbs <= f2) {
                i = i5;
                f2 = fAbs;
            }
            if (f6 > f && fAbs <= f3) {
                i3 = i5;
                f3 = fAbs;
            }
            if (f6 <= f4) {
                i2 = i5;
                f4 = f6;
            }
            if (f6 > f5) {
                i4 = i5;
                f5 = f6;
            }
        }
        if (i == -1) {
            i = i2;
        }
        if (i3 == -1) {
            i3 = i4;
        }
        return new d(list.get(i), list.get(i3));
    }

    private boolean H2(float f, d dVar) {
        float fA2 = a2(f, u2(f, dVar) / 2.0f);
        if (G2()) {
            return fA2 < 0.0f;
        }
        return fA2 > ((float) o2());
    }

    private boolean I2(float f, d dVar) {
        float fZ1 = Z1(f, u2(f, dVar) / 2.0f);
        if (G2()) {
            return fZ1 > ((float) o2());
        }
        return fZ1 < 0.0f;
    }

    private void J2() {
        if (this.v) {
            Log.isLoggable("CarouselLayoutManager", 3);
            if (0 != 0) {
                Log.d("CarouselLayoutManager", "internal representation of views on the screen");
                for (int i = 0; i < P(); i++) {
                    View viewO = O(i);
                    Log.d("CarouselLayoutManager", "item position " + m0(viewO) + ", center:" + p2(viewO) + ", child index:" + i);
                }
                Log.d("CarouselLayoutManager", "==============");
            }
        }
    }

    private b K2(RecyclerView.w wVar, float f, int i) {
        View viewO = wVar.o(i);
        F0(viewO, 0, 0);
        float fZ1 = Z1(f, this.z.g() / 2.0f);
        d dVarF2 = F2(this.z.h(), fZ1, false);
        return new b(viewO, fZ1, e2(fZ1, dVarF2), dVarF2);
    }

    private float L2(View view, float f, float f2, Rect rect) {
        float fZ1 = Z1(f, f2);
        d dVarF2 = F2(this.z.h(), fZ1, false);
        float fE2 = e2(fZ1, dVarF2);
        super.V(view, rect);
        V2(view, fZ1, dVarF2);
        this.C.m(view, rect, f2, fE2);
        return fE2;
    }

    private void M2(RecyclerView.w wVar) {
        View viewO = wVar.o(0);
        F0(viewO, 0, 0);
        e eVarG = this.x.g(this, viewO);
        if (G2()) {
            eVarG = e.p(eVarG, o2());
        }
        this.y = f.f(this, eVarG, q2(), t2(), C2(), this.x.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N2() {
        this.y = null;
        y1();
    }

    private void O2(RecyclerView.w wVar) {
        while (P() > 0) {
            View viewO = O(0);
            float fP2 = p2(viewO);
            if (!I2(fP2, F2(this.z.h(), fP2, true))) {
                break;
            } else {
                r1(viewO, wVar);
            }
        }
        while (P() - 1 >= 0) {
            View viewO2 = O(P() - 1);
            float fP3 = p2(viewO2);
            if (!H2(fP3, F2(this.z.h(), fP3, true))) {
                return;
            } else {
                r1(viewO2, wVar);
            }
        }
    }

    private int P2(int i, RecyclerView.w wVar, RecyclerView.B b2) {
        if (P() == 0 || i == 0) {
            return 0;
        }
        if (this.y == null) {
            M2(wVar);
        }
        if (f() <= r2(this.y).n()) {
            return 0;
        }
        int iI2 = i2(i, this.s, this.t, this.u);
        this.s += iI2;
        W2(this.y);
        float fG = this.z.g() / 2.0f;
        float fF2 = f2(m0(O(0)));
        Rect rect = new Rect();
        float f = G2() ? this.z.i().b : this.z.b().b;
        float f2 = Float.MAX_VALUE;
        for (int i2 = 0; i2 < P(); i2++) {
            View viewO = O(i2);
            float fAbs = Math.abs(f - L2(viewO, fF2, fG, rect));
            if (viewO != null && fAbs < f2) {
                this.F = m0(viewO);
                f2 = fAbs;
            }
            fF2 = Z1(fF2, this.z.g());
        }
        l2(wVar, b2);
        return iI2;
    }

    private void Q2(RecyclerView recyclerView, int i) {
        if (g()) {
            recyclerView.scrollBy(i, 0);
        } else {
            recyclerView.scrollBy(0, i);
        }
    }

    public static /* synthetic */ void S1(final CarouselLayoutManager carouselLayoutManager, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        carouselLayoutManager.getClass();
        if (i3 - i == i7 - i5 && i4 - i2 == i8 - i6) {
            return;
        }
        view.post(new Runnable() { // from class: ax.b7.c
            @Override // java.lang.Runnable
            public final void run() {
                this.q.N2();
            }
        });
    }

    private void S2(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.A0);
            R2(typedArrayObtainStyledAttributes.getInt(m.B0, 0));
            U2(typedArrayObtainStyledAttributes.getInt(ax.p149j2.d.b, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void V2(View view, float f, d dVar) {
        if (view instanceof g) {
            e.c cVar = dVar.a;
            float f2 = cVar.c;
            e.c cVar2 = dVar.b;
            float fB = ax.S6.a.b(f2, cVar2.c, cVar.a, cVar2.a, f);
            float height = view.getHeight();
            float width = view.getWidth();
            RectF rectFE = this.C.e(height, width, ax.S6.a.b(0.0f, height / 2.0f, 0.0f, 1.0f, fB), ax.S6.a.b(0.0f, width / 2.0f, 0.0f, 1.0f, fB));
            float fE2 = e2(f, dVar);
            RectF rectF = new RectF(fE2 - (rectFE.width() / 2.0f), fE2 - (rectFE.height() / 2.0f), fE2 + (rectFE.width() / 2.0f), (rectFE.height() / 2.0f) + fE2);
            RectF rectF2 = new RectF(y2(), B2(), z2(), x2());
            if (this.x.e() == com.google.android.material.carousel.c.a.CONTAINED) {
                this.C.a(rectFE, rectF, rectF2);
            }
            this.C.l(rectFE, rectF, rectF2);
            ((g) view).a(rectFE);
        }
    }

    private void W2(f fVar) {
        int i = this.u;
        int i2 = this.t;
        if (i <= i2) {
            this.z = r2(fVar);
        } else {
            this.z = fVar.j(this.s, i2, i);
        }
        this.w.l(this.z.h());
    }

    private void X2() {
        int iF = f();
        int i = this.E;
        if (iF == i || this.y == null) {
            return;
        }
        if (this.x.h(this, i)) {
            N2();
        }
        this.E = iF;
    }

    private void Y1(View view, int i, b bVar) {
        float fG = this.z.g() / 2.0f;
        k(view, i);
        F0(view, 0, 0);
        float f = bVar.c;
        this.C.k(view, (int) (f - fG), (int) (f + fG));
        V2(view, bVar.b, bVar.d);
    }

    private void Y2() {
        if (!this.v || P() < 1) {
            return;
        }
        int i = 0;
        while (i < P() - 1) {
            int iM0 = m0(O(i));
            int i2 = i + 1;
            int iM1 = m0(O(i2));
            if (iM0 > iM1) {
                J2();
                throw new IllegalStateException("Detected invalid child order. Child at index [" + i + "] had adapter position [" + iM0 + "] and child at index [" + i2 + "] had adapter position [" + iM1 + "].");
            }
            i = i2;
        }
    }

    private float Z1(float f, float f2) {
        return G2() ? f - f2 : f + f2;
    }

    private float a2(float f, float f2) {
        return G2() ? f + f2 : f - f2;
    }

    private void b2(RecyclerView.w wVar, int i, int i2) {
        if (i < 0 || i >= f()) {
            return;
        }
        b bVarK2 = K2(wVar, f2(i), i);
        Y1(bVarK2.a, i2, bVarK2);
    }

    private void c2(RecyclerView.w wVar, RecyclerView.B b2, int i) {
        float fF2 = f2(i);
        while (i < b2.b()) {
            float fZ1 = Z1(fF2, this.z.g() / 2.0f);
            d dVarF2 = F2(this.z.h(), fZ1, false);
            float fE2 = e2(fZ1, dVarF2);
            if (H2(fE2, dVarF2)) {
                return;
            }
            fF2 = Z1(fF2, this.z.g());
            if (!I2(fE2, dVarF2)) {
                View viewO = wVar.o(i);
                Y1(viewO, -1, new b(viewO, fZ1, fE2, dVarF2));
            }
            i++;
        }
    }

    private void d2(RecyclerView.w wVar, int i) {
        float fF2 = f2(i);
        while (i >= 0) {
            float fZ1 = Z1(fF2, this.z.g() / 2.0f);
            d dVarF2 = F2(this.z.h(), fZ1, false);
            float fE2 = e2(fZ1, dVarF2);
            if (I2(fE2, dVarF2)) {
                return;
            }
            fF2 = a2(fF2, this.z.g());
            if (!H2(fE2, dVarF2)) {
                View viewO = wVar.o(i);
                Y1(viewO, 0, new b(viewO, fZ1, fE2, dVarF2));
            }
            i--;
        }
    }

    private float e2(float f, d dVar) {
        e.c cVar = dVar.a;
        float f2 = cVar.b;
        e.c cVar2 = dVar.b;
        float fB = ax.S6.a.b(f2, cVar2.b, cVar.a, cVar2.a, f);
        if (dVar.b != this.z.d() && dVar.a != this.z.k()) {
            return fB;
        }
        e.c cVar3 = dVar.b;
        return fB + ((f - cVar3.a) * (1.0f - cVar3.c));
    }

    private float f2(int i) {
        return Z1(A2() - this.s, this.z.g() * i);
    }

    private int g2(RecyclerView.B b2, f fVar) {
        boolean zG2 = G2();
        e eVarL = zG2 ? fVar.l() : fVar.h();
        e.c cVarB = zG2 ? eVarL.b() : eVarL.i();
        int iB = (int) (((((b2.b() - 1) * eVarL.g()) * (zG2 ? -1.0f : 1.0f)) - (cVarB.a - A2())) + (((zG2 ? -1 : 1) * cVarB.d) / 2.0f));
        return zG2 ? Math.min(0, iB) : Math.max(0, iB);
    }

    private static int i2(int i, int i2, int i3, int i4) {
        int i5 = i2 + i;
        if (i5 < i3) {
            return i3 - i2;
        }
        return i5 > i4 ? i4 - i2 : i;
    }

    private int j2(f fVar) {
        boolean zG2 = G2();
        e eVarH = zG2 ? fVar.h() : fVar.l();
        return (int) (A2() - a2((zG2 ? eVarH.i() : eVarH.b()).a, eVarH.g() / 2.0f));
    }

    private int k2(int i) {
        int iW2 = w2();
        if (i == 1) {
            return -1;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 17) {
            if (iW2 == 0) {
                return G2() ? 1 : -1;
            }
            return Integer.MIN_VALUE;
        }
        if (i == 33) {
            return iW2 == 1 ? -1 : Integer.MIN_VALUE;
        }
        if (i == 66) {
            if (iW2 == 0) {
                return G2() ? -1 : 1;
            }
            return Integer.MIN_VALUE;
        }
        if (i == 130) {
            return iW2 == 1 ? 1 : Integer.MIN_VALUE;
        }
        Log.d("CarouselLayoutManager", "Unknown focus request:" + i);
        return Integer.MIN_VALUE;
    }

    private void l2(RecyclerView.w wVar, RecyclerView.B b2) {
        O2(wVar);
        if (P() == 0) {
            d2(wVar, this.A - 1);
            c2(wVar, b2, this.A);
        } else {
            int iM0 = m0(O(0));
            int iM1 = m0(O(P() - 1));
            d2(wVar, iM0 - 1);
            c2(wVar, b2, iM1 + 1);
        }
        Y2();
    }

    private View m2() {
        return O(G2() ? 0 : P() - 1);
    }

    private View n2() {
        return O(G2() ? P() - 1 : 0);
    }

    private int o2() {
        return g() ? a() : b();
    }

    private float p2(View view) {
        Rect rect = new Rect();
        super.V(view, rect);
        return g() ? rect.centerX() : rect.centerY();
    }

    private int q2() {
        int i;
        int i2;
        if (P() <= 0) {
            return 0;
        }
        RecyclerView.q qVar = (RecyclerView.q) O(0).getLayoutParams();
        if (this.C.a == 0) {
            i = ((ViewGroup.MarginLayoutParams) qVar).leftMargin;
            i2 = ((ViewGroup.MarginLayoutParams) qVar).rightMargin;
        } else {
            i = ((ViewGroup.MarginLayoutParams) qVar).topMargin;
            i2 = ((ViewGroup.MarginLayoutParams) qVar).bottomMargin;
        }
        return i + i2;
    }

    private e r2(f fVar) {
        return G2() ? fVar.h() : fVar.l();
    }

    private e s2(int i) {
        e eVar;
        Map<Integer, e> map = this.B;
        return (map == null || (eVar = map.get(Integer.valueOf(ax.W.a.b(i, 0, Math.max(0, f() + (-1)))))) == null) ? this.y.g() : eVar;
    }

    private int t2() {
        if (S()) {
            return 0;
        }
        return w2() == 1 ? l0() : j0();
    }

    private float u2(float f, d dVar) {
        e.c cVar = dVar.a;
        float f2 = cVar.d;
        e.c cVar2 = dVar.b;
        return ax.S6.a.b(f2, cVar2.d, cVar.b, cVar2.b, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int x2() {
        return this.C.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int y2() {
        return this.C.g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int z2() {
        return this.C.h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int A(RecyclerView.B b2) {
        return this.s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int B(RecyclerView.B b2) {
        return this.u - this.t;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int B1(int i, RecyclerView.w wVar, RecyclerView.B b2) {
        if (q()) {
            return P2(i, wVar, b2);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void C1(int i) {
        this.F = i;
        if (this.y == null) {
            return;
        }
        this.s = D2(i, s2(i));
        this.A = ax.W.a.b(i, 0, Math.max(0, f() - 1));
        W2(this.y);
        y1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int D1(int i, RecyclerView.w wVar, RecyclerView.B b2) {
        if (r()) {
            return P2(i, wVar, b2);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void F0(View view, int i, int i2) {
        if (!(view instanceof g)) {
            throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
        }
        RecyclerView.q qVar = (RecyclerView.q) view.getLayoutParams();
        Rect rect = new Rect();
        p(view, rect);
        int i3 = i + rect.left + rect.right;
        int i4 = i2 + rect.top + rect.bottom;
        f fVar = this.y;
        float fG = (fVar == null || this.C.a != 0) ? ((ViewGroup.MarginLayoutParams) qVar).width : fVar.g().g();
        f fVar2 = this.y;
        view.measure(RecyclerView.p.Q(t0(), u0(), j0() + k0() + ((ViewGroup.MarginLayoutParams) qVar).leftMargin + ((ViewGroup.MarginLayoutParams) qVar).rightMargin + i3, (int) fG, q()), RecyclerView.p.Q(c0(), d0(), l0() + i0() + ((ViewGroup.MarginLayoutParams) qVar).topMargin + ((ViewGroup.MarginLayoutParams) qVar).bottomMargin + i4, (int) ((fVar2 == null || this.C.a != 1) ? ((ViewGroup.MarginLayoutParams) qVar).height : fVar2.g().g()), r()));
    }

    boolean G2() {
        return g() && e0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public RecyclerView.q J() {
        return new RecyclerView.q(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void L0(RecyclerView recyclerView) {
        super.L0(recyclerView);
        this.x.f(recyclerView.getContext());
        N2();
        recyclerView.addOnLayoutChangeListener(this.D);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void N0(RecyclerView recyclerView, RecyclerView.w wVar) {
        super.N0(recyclerView, wVar);
        recyclerView.removeOnLayoutChangeListener(this.D);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void N1(RecyclerView recyclerView, RecyclerView.B b2, int i) {
        a aVar = new a(recyclerView.getContext());
        aVar.p(i);
        O1(aVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public View O0(View view, int i, RecyclerView.w wVar, RecyclerView.B b2) {
        int iK2;
        if (P() == 0 || (iK2 = k2(i)) == Integer.MIN_VALUE) {
            return null;
        }
        if (iK2 == -1) {
            if (m0(view) == 0) {
                return null;
            }
            b2(wVar, m0(O(0)) - 1, 0);
            return n2();
        }
        if (m0(view) == f() - 1) {
            return null;
        }
        b2(wVar, m0(O(P() - 1)) + 1, -1);
        return m2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void P0(AccessibilityEvent accessibilityEvent) {
        super.P0(accessibilityEvent);
        if (P() > 0) {
            accessibilityEvent.setFromIndex(m0(O(0)));
            accessibilityEvent.setToIndex(m0(O(P() - 1)));
        }
    }

    public void R2(int i) {
        this.G = i;
        N2();
    }

    public void T2(com.google.android.material.carousel.c cVar) {
        this.x = cVar;
        N2();
    }

    public void U2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        m(null);
        com.google.android.material.carousel.b bVar = this.C;
        if (bVar == null || i != bVar.a) {
            this.C = com.google.android.material.carousel.b.c(this, i);
            N2();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void V(View view, Rect rect) {
        super.V(view, rect);
        float fCenterY = rect.centerY();
        if (g()) {
            fCenterY = rect.centerX();
        }
        float fU2 = u2(fCenterY, F2(this.z.h(), fCenterY, true));
        float fWidth = g() ? (rect.width() - fU2) / 2.0f : 0.0f;
        float fHeight = g() ? 0.0f : (rect.height() - fU2) / 2.0f;
        rect.set((int) (rect.left + fWidth), (int) (rect.top + fHeight), (int) (rect.right - fWidth), (int) (rect.bottom - fHeight));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void W0(RecyclerView recyclerView, int i, int i2) {
        super.W0(recyclerView, i, i2);
        X2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void X0(RecyclerView recyclerView) {
        super.X0(recyclerView);
        X2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void Z0(RecyclerView recyclerView, int i, int i2) {
        super.Z0(recyclerView, i, i2);
        X2();
    }

    @Override // ax.p026b7.a
    public int a() {
        return t0();
    }

    @Override // ax.p026b7.a
    public int b() {
        return c0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.A.b
    public PointF c(int i) {
        if (this.y == null) {
            return null;
        }
        int iV2 = v2(i, s2(i));
        return g() ? new PointF(iV2, 0.0f) : new PointF(0.0f, iV2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void c1(RecyclerView.w wVar, RecyclerView.B b2) {
        if (b2.b() <= 0 || o2() <= 0.0f) {
            p1(wVar);
            this.A = 0;
            return;
        }
        boolean zG2 = G2();
        f fVar = this.y;
        boolean z = fVar == null;
        if (z || fVar.g().a() != o2()) {
            M2(wVar);
        }
        int iJ2 = j2(this.y);
        int iG2 = g2(b2, this.y);
        this.t = zG2 ? iG2 : iJ2;
        if (zG2) {
            iG2 = iJ2;
        }
        this.u = iG2;
        if (z) {
            this.s = iJ2;
            this.B = this.y.i(f(), this.t, this.u, G2());
            int i = this.F;
            if (i != -1) {
                this.s = D2(i, s2(i));
            }
        }
        int i2 = this.s;
        this.s = i2 + i2(0, i2, this.t, this.u);
        this.A = ax.W.a.b(this.A, 0, b2.b());
        W2(this.y);
        C(wVar);
        l2(wVar, b2);
        this.E = f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void d1(RecyclerView.B b2) {
        super.d1(b2);
        if (P() == 0) {
            this.A = 0;
        } else {
            this.A = m0(O(0));
        }
        Y2();
    }

    @Override // ax.p026b7.a
    public int e() {
        return this.G;
    }

    @Override // ax.p026b7.a
    public boolean g() {
        return this.C.a == 0;
    }

    int h2(int i) {
        return (int) (this.s - D2(i, s2(i)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public boolean q() {
        return g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public boolean r() {
        return !g();
    }

    int v2(int i, e eVar) {
        return D2(i, eVar) - this.s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int w(RecyclerView.B b2) {
        if (P() == 0 || this.y == null || f() <= 1) {
            return 0;
        }
        return (int) (t0() * (this.y.g().g() / y(b2)));
    }

    public int w2() {
        return this.C.a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int x(RecyclerView.B b2) {
        return this.s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public boolean x0() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public boolean x1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        int iE2;
        if (this.y == null || (iE2 = E2(m0(view), s2(m0(view)))) == 0) {
            return false;
        }
        Q2(recyclerView, E2(m0(view), this.y.j(this.s + i2(iE2, this.s, this.t, this.u), this.t, this.u)));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int y(RecyclerView.B b2) {
        return this.u - this.t;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public int z(RecyclerView.B b2) {
        if (P() == 0 || this.y == null || f() <= 1) {
            return 0;
        }
        return (int) (c0() * (this.y.g().g() / B(b2)));
    }
}
