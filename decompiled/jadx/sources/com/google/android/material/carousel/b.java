package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
abstract class b {
    final int a;

    class a extends b {
        final /* synthetic */ CarouselLayoutManager b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i, CarouselLayoutManager carouselLayoutManager) {
            super(i, null);
            this.b = carouselLayoutManager;
        }

        @Override // com.google.android.material.carousel.b
        public void a(RectF rectF, RectF rectF2, RectF rectF3) {
            float f = rectF2.top;
            float f2 = rectF3.top;
            if (f < f2 && rectF2.bottom > f2) {
                float f3 = f2 - f;
                rectF.top += f3;
                rectF3.top += f3;
            }
            float f4 = rectF2.bottom;
            float f5 = rectF3.bottom;
            if (f4 <= f5 || rectF2.top >= f5) {
                return;
            }
            float f6 = f4 - f5;
            rectF.bottom = Math.max(rectF.bottom - f6, rectF.top);
            rectF2.bottom = Math.max(rectF2.bottom - f6, rectF2.top);
        }

        @Override // com.google.android.material.carousel.b
        public RectF e(float f, float f2, float f3, float f4) {
            return new RectF(0.0f, f3, f2, f - f3);
        }

        @Override // com.google.android.material.carousel.b
        int f() {
            return this.b.c0();
        }

        @Override // com.google.android.material.carousel.b
        int g() {
            return this.b.j0();
        }

        @Override // com.google.android.material.carousel.b
        int h() {
            return this.b.t0() - this.b.k0();
        }

        @Override // com.google.android.material.carousel.b
        int i() {
            return j();
        }

        @Override // com.google.android.material.carousel.b
        int j() {
            return 0;
        }

        @Override // com.google.android.material.carousel.b
        public void k(View view, int i, int i2) {
            int iG = g();
            this.b.E0(view, iG, i, iG + n(view), i2);
        }

        @Override // com.google.android.material.carousel.b
        public void l(RectF rectF, RectF rectF2, RectF rectF3) {
            if (rectF2.bottom <= rectF3.top) {
                float fFloor = ((float) Math.floor(rectF.bottom)) - 1.0f;
                rectF.bottom = fFloor;
                rectF.top = Math.min(rectF.top, fFloor);
            }
            if (rectF2.top >= rectF3.bottom) {
                float fCeil = ((float) Math.ceil(rectF.top)) + 1.0f;
                rectF.top = fCeil;
                rectF.bottom = Math.max(fCeil, rectF.bottom);
            }
        }

        @Override // com.google.android.material.carousel.b
        public void m(View view, Rect rect, float f, float f2) {
            view.offsetTopAndBottom((int) (f2 - (rect.top + f)));
        }

        int n(View view) {
            RecyclerView.q qVar = (RecyclerView.q) view.getLayoutParams();
            return this.b.Y(view) + ((ViewGroup.MarginLayoutParams) qVar).leftMargin + ((ViewGroup.MarginLayoutParams) qVar).rightMargin;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.carousel.b$b, reason: collision with other inner class name */
    class C0571b extends b {
        final /* synthetic */ CarouselLayoutManager b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0571b(int i, CarouselLayoutManager carouselLayoutManager) {
            super(i, null);
            this.b = carouselLayoutManager;
        }

        @Override // com.google.android.material.carousel.b
        public void a(RectF rectF, RectF rectF2, RectF rectF3) {
            float f = rectF2.left;
            float f2 = rectF3.left;
            if (f < f2 && rectF2.right > f2) {
                float f3 = f2 - f;
                rectF.left += f3;
                rectF2.left += f3;
            }
            float f4 = rectF2.right;
            float f5 = rectF3.right;
            if (f4 <= f5 || rectF2.left >= f5) {
                return;
            }
            float f6 = f4 - f5;
            rectF.right = Math.max(rectF.right - f6, rectF.left);
            rectF2.right = Math.max(rectF2.right - f6, rectF2.left);
        }

        @Override // com.google.android.material.carousel.b
        public RectF e(float f, float f2, float f3, float f4) {
            return new RectF(f4, 0.0f, f2 - f4, f);
        }

        @Override // com.google.android.material.carousel.b
        int f() {
            return this.b.c0() - this.b.i0();
        }

        @Override // com.google.android.material.carousel.b
        int g() {
            return 0;
        }

        @Override // com.google.android.material.carousel.b
        int h() {
            return this.b.t0();
        }

        @Override // com.google.android.material.carousel.b
        int i() {
            return this.b.G2() ? h() : g();
        }

        @Override // com.google.android.material.carousel.b
        int j() {
            return this.b.l0();
        }

        @Override // com.google.android.material.carousel.b
        public void k(View view, int i, int i2) {
            int iJ = j();
            this.b.E0(view, i, iJ, i2, iJ + n(view));
        }

        @Override // com.google.android.material.carousel.b
        public void l(RectF rectF, RectF rectF2, RectF rectF3) {
            if (rectF2.right <= rectF3.left) {
                float fFloor = ((float) Math.floor(rectF.right)) - 1.0f;
                rectF.right = fFloor;
                rectF.left = Math.min(rectF.left, fFloor);
            }
            if (rectF2.left >= rectF3.right) {
                float fCeil = ((float) Math.ceil(rectF.left)) + 1.0f;
                rectF.left = fCeil;
                rectF.right = Math.max(fCeil, rectF.right);
            }
        }

        @Override // com.google.android.material.carousel.b
        public void m(View view, Rect rect, float f, float f2) {
            view.offsetLeftAndRight((int) (f2 - (rect.left + f)));
        }

        int n(View view) {
            RecyclerView.q qVar = (RecyclerView.q) view.getLayoutParams();
            return this.b.X(view) + ((ViewGroup.MarginLayoutParams) qVar).topMargin + ((ViewGroup.MarginLayoutParams) qVar).bottomMargin;
        }
    }

    private b(int i) {
        this.a = i;
    }

    /* synthetic */ b(int i, a aVar) {
        this(i);
    }

    private static b b(CarouselLayoutManager carouselLayoutManager) {
        return new C0571b(0, carouselLayoutManager);
    }

    static b c(CarouselLayoutManager carouselLayoutManager, int i) {
        if (i == 0) {
            return b(carouselLayoutManager);
        }
        if (i == 1) {
            return d(carouselLayoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    private static b d(CarouselLayoutManager carouselLayoutManager) {
        return new a(1, carouselLayoutManager);
    }

    abstract void a(RectF rectF, RectF rectF2, RectF rectF3);

    abstract RectF e(float f, float f2, float f3, float f4);

    abstract int f();

    abstract int g();

    abstract int h();

    abstract int i();

    abstract int j();

    abstract void k(View view, int i, int i2);

    abstract void l(RectF rectF, RectF rectF2, RectF rectF3);

    abstract void m(View view, Rect rect, float f, float f2);
}
