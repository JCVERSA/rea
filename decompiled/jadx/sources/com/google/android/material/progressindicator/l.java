package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;

/* JADX INFO: loaded from: classes3.dex */
final class l extends i<o> {
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private int l;
    private boolean m;
    private float n;
    Pair<i<o>.b, i<o>.b> o;

    l(o oVar) {
        super(oVar);
        this.f = 300.0f;
        this.o = new Pair<>(new i.b(), new i.b());
    }

    private void j(PathMeasure pathMeasure, Path path, Pair<i<o>.b, i<o>.b> pair, float f, float f2, float f3, float f4) {
        int i = this.m ? ((o) this.a).j : ((o) this.a).k;
        if (pathMeasure == this.d && i != this.l) {
            this.l = i;
            g();
        }
        path.rewind();
        float f5 = (-this.f) / 2.0f;
        boolean zB = ((o) this.a).b(this.m);
        if (zB) {
            float f6 = this.f;
            float f7 = this.k;
            float f8 = f6 / f7;
            float f9 = f4 / f8;
            float f10 = f8 / (f8 + 1.0f);
            f = (f + f9) * f10;
            f2 = (f2 + f9) * f10;
            f5 -= f4 * f7;
        }
        float length = f * pathMeasure.getLength();
        float length2 = f2 * pathMeasure.getLength();
        pathMeasure.getSegment(length, length2, path, true);
        i.b bVar = (i.b) pair.first;
        bVar.c();
        pathMeasure.getPosTan(length, bVar.a, bVar.b);
        i.b bVar2 = (i.b) pair.second;
        bVar2.c();
        pathMeasure.getPosTan(length2, bVar2.a, bVar2.b);
        this.e.reset();
        this.e.setTranslate(f5, 0.0f);
        bVar.f(f5, 0.0f);
        bVar2.f(f5, 0.0f);
        if (zB) {
            float f11 = this.j * f3;
            this.e.postScale(1.0f, f11);
            bVar.e(1.0f, f11);
            bVar2.e(1.0f, f11);
        }
        path.transform(this.e);
    }

    private void k(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3, float f3, float f4, boolean z) {
        float f5;
        float fD;
        Paint paint2;
        Canvas canvas2;
        float fA = ax.W.a.a(f, 0.0f, 1.0f);
        float fA2 = ax.W.a.a(f2, 0.0f, 1.0f);
        float fD2 = ax.p218n7.a.d(1.0f - this.n, 1.0f, fA);
        float fD3 = ax.p218n7.a.d(1.0f - this.n, 1.0f, fA2);
        int iA = (int) ((i2 * ax.W.a.a(fD2, 0.0f, 0.01f)) / 0.01f);
        int iA2 = (int) ((i3 * (1.0f - ax.W.a.a(fD3, 0.99f, 1.0f))) / 0.01f);
        float f6 = this.f;
        int i4 = (int) ((fD2 * f6) + iA);
        int i5 = (int) ((fD3 * f6) - iA2);
        float f7 = this.h;
        float f8 = this.i;
        if (f7 != f8) {
            float fMax = Math.max(f7, f8);
            float f9 = this.f;
            float f10 = fMax / f9;
            float fD4 = ax.p218n7.a.d(this.h, this.i, ax.W.a.a(i4 / f9, 0.0f, f10) / f10);
            float f11 = this.h;
            float f12 = this.i;
            float f13 = this.f;
            fD = ax.p218n7.a.d(f11, f12, ax.W.a.a((f13 - i5) / f13, 0.0f, f10) / f10);
            f5 = fD4;
        } else {
            f5 = f7;
            fD = f5;
        }
        float f14 = (-this.f) / 2.0f;
        boolean z2 = ((o) this.a).b(this.m) && z && f3 > 0.0f;
        if (i4 <= i5) {
            float f15 = i4 + f5;
            float f16 = i5 - fD;
            float f17 = f5 * 2.0f;
            float f18 = 2.0f * fD;
            paint.setColor(i);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(this.g);
            ((i.b) this.o.first).c();
            ((i.b) this.o.second).c();
            ((i.b) this.o.first).f(f15 + f14, 0.0f);
            ((i.b) this.o.second).f(f14 + f16, 0.0f);
            if (i4 == 0 && f16 + fD < f15 + f5) {
                Pair<i<o>.b, i<o>.b> pair = this.o;
                i<o>.b bVar = (i.b) pair.first;
                float f19 = this.g;
                m(canvas, paint, bVar, f17, f19, f5, (i.b) pair.second, f18, f19, fD, true);
                return;
            }
            if (f15 - f5 > f16 - fD) {
                Pair<i<o>.b, i<o>.b> pair2 = this.o;
                i<o>.b bVar2 = (i.b) pair2.second;
                float f20 = this.g;
                m(canvas, paint, bVar2, f18, f20, fD, (i.b) pair2.first, f17, f20, f5, false);
                return;
            }
            float f21 = fD;
            float f22 = f5;
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(((o) this.a).g() ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            if (z2) {
                paint2 = paint;
                PathMeasure pathMeasure = this.d;
                Path path = this.c;
                Pair<i<o>.b, i<o>.b> pair3 = this.o;
                float f23 = this.f;
                j(pathMeasure, path, pair3, f15 / f23, f16 / f23, f3, f4);
                canvas2 = canvas;
                canvas2.drawPath(this.c, paint2);
            } else {
                Pair<i<o>.b, i<o>.b> pair4 = this.o;
                Object obj = pair4.first;
                float f24 = ((i.b) obj).a[0];
                float f25 = ((i.b) obj).a[1];
                Object obj2 = pair4.second;
                canvas.drawLine(f24, f25, ((i.b) obj2).a[0], ((i.b) obj2).a[1], paint);
                paint2 = paint;
                canvas2 = canvas;
            }
            if (((o) this.a).g()) {
                return;
            }
            if (f15 > 0.0f && f22 > 0.0f) {
                l(canvas2, paint2, (i.b) this.o.first, f17, this.g, f22);
            }
            if (f16 >= this.f || f21 <= 0.0f) {
                return;
            }
            l(canvas, paint, (i.b) this.o.second, f18, this.g, f21);
        }
    }

    private void l(Canvas canvas, Paint paint, i<o>.b bVar, float f, float f2, float f3) {
        m(canvas, paint, bVar, f, f2, f3, null, 0.0f, 0.0f, 0.0f, false);
    }

    private void m(Canvas canvas, Paint paint, i<o>.b bVar, float f, float f2, float f3, i<o>.b bVar2, float f4, float f5, float f6, boolean z) {
        char c;
        float f7;
        float f8;
        float fMin = Math.min(f2, this.g);
        float f9 = (-f) / 2.0f;
        float f10 = (-fMin) / 2.0f;
        float f11 = f / 2.0f;
        float f12 = fMin / 2.0f;
        RectF rectF = new RectF(f9, f10, f11, f12);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        if (bVar2 != null) {
            float fMin2 = Math.min(f5, this.g);
            float fMin3 = Math.min(f4 / 2.0f, (f6 * fMin2) / this.g);
            RectF rectF2 = new RectF();
            if (z) {
                c = 0;
                float f13 = (bVar2.a[0] - fMin3) - (bVar.a[0] - f3);
                if (f13 > 0.0f) {
                    bVar2.f((-f13) / 2.0f, 0.0f);
                    f8 = f4 + f13;
                } else {
                    f8 = f4;
                }
                rectF2.set(0.0f, f10, f11, f12);
            } else {
                c = 0;
                float f14 = (bVar2.a[0] + fMin3) - (bVar.a[0] + f3);
                if (f14 < 0.0f) {
                    bVar2.f((-f14) / 2.0f, 0.0f);
                    f7 = f4 - f14;
                } else {
                    f7 = f4;
                }
                rectF2.set(f9, f10, 0.0f, f12);
                f8 = f7;
            }
            RectF rectF3 = new RectF((-f8) / 2.0f, (-fMin2) / 2.0f, f8 / 2.0f, fMin2 / 2.0f);
            float[] fArr = bVar2.a;
            canvas.translate(fArr[c], fArr[1]);
            canvas.rotate(i(bVar2.b));
            Path path = new Path();
            path.addRoundRect(rectF3, fMin3, fMin3, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.rotate(-i(bVar2.b));
            float[] fArr2 = bVar2.a;
            canvas.translate(-fArr2[c], -fArr2[1]);
            float[] fArr3 = bVar.a;
            canvas.translate(fArr3[c], fArr3[1]);
            canvas.rotate(i(bVar.b));
            canvas.drawRect(rectF2, paint);
            canvas.drawRoundRect(rectF, f3, f3, paint);
        } else {
            float[] fArr4 = bVar.a;
            canvas.translate(fArr4[0], fArr4[1]);
            canvas.rotate(i(bVar.b));
            canvas.drawRoundRect(rectF, f3, f3, paint);
        }
        canvas.restore();
    }

    @Override // com.google.android.material.progressindicator.i
    void a(Canvas canvas, Rect rect, float f, boolean z, boolean z2) {
        if (this.f != rect.width()) {
            this.f = rect.width();
            g();
        }
        float fE = e();
        canvas.translate(rect.left + (rect.width() / 2.0f), rect.top + (rect.height() / 2.0f) + Math.max(0.0f, (rect.height() - fE) / 2.0f));
        if (((o) this.a).q) {
            canvas.scale(-1.0f, 1.0f);
        }
        float f2 = this.f / 2.0f;
        float f3 = fE / 2.0f;
        canvas.clipRect(-f2, -f3, f2, f3);
        S s = this.a;
        this.g = ((o) s).a * f;
        this.h = Math.min(((o) s).a / 2, ((o) s).a()) * f;
        S s2 = this.a;
        this.j = ((o) s2).l * f;
        this.i = Math.min(((o) s2).a / 2.0f, ((o) s2).i()) * f;
        if (z || z2) {
            if ((z && ((o) this.a).g == 2) || (z2 && ((o) this.a).h == 1)) {
                canvas.scale(1.0f, -1.0f);
            }
            if (z || (z2 && ((o) this.a).h != 3)) {
                canvas.translate(0.0f, (((o) this.a).a * (1.0f - f)) / 2.0f);
            }
        }
        if (z2 && ((o) this.a).h == 3) {
            this.n = f;
        } else {
            this.n = 1.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.i
    void b(Canvas canvas, Paint paint, int i, int i2) {
        int iA = ax.p106g7.a.a(i, i2);
        this.m = false;
        if (((o) this.a).r <= 0 || iA == 0) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(iA);
        S s = this.a;
        i<o>.b bVar = new i.b(new float[]{(this.f / 2.0f) - (((o) s).s != null ? ((o) s).s.floatValue() + (((o) this.a).r / 2.0f) : this.g / 2.0f), 0.0f}, new float[]{1.0f, 0.0f});
        S s2 = this.a;
        l(canvas, paint, bVar, ((o) s2).r, ((o) s2).r, (this.h * ((o) s2).r) / this.g);
    }

    @Override // com.google.android.material.progressindicator.i
    void c(Canvas canvas, Paint paint, i.a aVar, int i) {
        int iA = ax.p106g7.a.a(aVar.c, i);
        this.m = aVar.h;
        float f = aVar.a;
        float f2 = aVar.b;
        int i2 = aVar.d;
        k(canvas, paint, f, f2, iA, i2, i2, aVar.e, aVar.f, true);
    }

    @Override // com.google.android.material.progressindicator.i
    void d(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3) {
        int iA = ax.p106g7.a.a(i, i2);
        this.m = false;
        k(canvas, paint, f, f2, iA, i3, i3, 0.0f, 0.0f, false);
    }

    @Override // com.google.android.material.progressindicator.i
    int e() {
        S s = this.a;
        return ((o) s).a + (((o) s).l * 2);
    }

    @Override // com.google.android.material.progressindicator.i
    int f() {
        return -1;
    }

    @Override // com.google.android.material.progressindicator.i
    void g() {
        this.b.rewind();
        if (((o) this.a).b(this.m)) {
            int i = this.m ? ((o) this.a).j : ((o) this.a).k;
            float f = this.f;
            int i2 = (int) (f / i);
            this.k = f / i2;
            for (int i3 = 0; i3 <= i2; i3++) {
                int i4 = i3 * 2;
                float f2 = i4 + 1;
                this.b.cubicTo(i4 + 0.48f, 0.0f, f2 - 0.48f, 1.0f, f2, 1.0f);
                float f3 = i4 + 2;
                this.b.cubicTo(f2 + 0.48f, 1.0f, f3 - 0.48f, 0.0f, f3, 0.0f);
            }
            this.e.reset();
            this.e.setScale(this.k / 2.0f, -2.0f);
            this.e.postTranslate(0.0f, 1.0f);
            this.b.transform(this.e);
        } else {
            this.b.lineTo(this.f, 0.0f);
        }
        this.d.setPath(this.b, false);
    }
}
