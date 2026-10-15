package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.Pair;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
final class c extends i<f> {
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private int l;
    private float m;
    private boolean n;
    private float o;
    private final RectF p;
    private final Pair<i<f>.b, i<f>.b> q;

    c(f fVar) {
        super(fVar);
        this.p = new RectF();
        this.q = new Pair<>(new i.b(), new i.b());
    }

    private void j(Path path, i<f>.b bVar, i<f>.b bVar2) {
        float f = (this.j / 2.0f) * 0.48f;
        i.b bVar3 = new i.b(this, bVar);
        i.b bVar4 = new i.b(this, bVar2);
        bVar3.b(f);
        bVar4.b(-f);
        float[] fArr = bVar3.a;
        float f2 = fArr[0];
        float f3 = fArr[1];
        float[] fArr2 = bVar4.a;
        float f4 = fArr2[0];
        float f5 = fArr2[1];
        float[] fArr3 = bVar2.a;
        path.cubicTo(f2, f3, f4, f5, fArr3[0], fArr3[1]);
    }

    private void k(PathMeasure pathMeasure, Path path, Pair<i<f>.b, i<f>.b> pair, float f, float f2, float f3, float f4) {
        float f5 = this.h * f3;
        int i = this.n ? ((f) this.a).j : ((f) this.a).k;
        float f6 = this.i;
        if (f6 != this.m || (pathMeasure == this.d && (f5 != this.k || i != this.l))) {
            this.k = f5;
            this.l = i;
            this.m = f6;
            g();
        }
        path.rewind();
        float f7 = 0.0f;
        float fA = ax.W.a.a(f2, 0.0f, 1.0f);
        if (((f) this.a).b(this.n)) {
            float f8 = f4 / ((float) ((((double) this.i) * 6.283185307179586d) / ((double) this.j)));
            f += f8;
            f7 = 0.0f - (f8 * 360.0f);
        }
        float f9 = f % 1.0f;
        float length = (pathMeasure.getLength() * f9) / 2.0f;
        float length2 = ((f9 + fA) * pathMeasure.getLength()) / 2.0f;
        pathMeasure.getSegment(length, length2, path, true);
        i.b bVar = (i.b) pair.first;
        bVar.c();
        pathMeasure.getPosTan(length, bVar.a, bVar.b);
        i.b bVar2 = (i.b) pair.second;
        bVar2.c();
        pathMeasure.getPosTan(length2, bVar2.a, bVar2.b);
        this.e.reset();
        this.e.setRotate(f7);
        bVar.d(f7);
        bVar2.d(f7);
        path.transform(this.e);
    }

    private void l(PathMeasure pathMeasure, Path path, float f) {
        path.rewind();
        float length = pathMeasure.getLength();
        int iMax = Math.max(3, (int) ((length / (this.n ? ((f) this.a).j : ((f) this.a).k)) / 2.0f)) * 2;
        this.j = length / iMax;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iMax; i++) {
            i.b bVar = new i.b();
            float f2 = i;
            pathMeasure.getPosTan(this.j * f2, bVar.a, bVar.b);
            i.b bVar2 = new i.b();
            float f3 = this.j;
            pathMeasure.getPosTan((f2 * f3) + (f3 / 2.0f), bVar2.a, bVar2.b);
            arrayList.add(bVar);
            bVar2.a(f * 2.0f);
            arrayList.add(bVar2);
        }
        arrayList.add((i.b) arrayList.get(0));
        i<f>.b bVar3 = (i.b) arrayList.get(0);
        float[] fArr = bVar3.a;
        int i2 = 1;
        path.moveTo(fArr[0], fArr[1]);
        while (i2 < arrayList.size()) {
            i<f>.b bVar4 = (i.b) arrayList.get(i2);
            j(path, bVar3, bVar4);
            i2++;
            bVar3 = bVar4;
        }
    }

    private void m(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3, float f3, float f4, boolean z) {
        float f5 = f2 >= f ? f2 - f : (f2 + 1.0f) - f;
        float f6 = f % 1.0f;
        if (f6 < 0.0f) {
            f6 += 1.0f;
        }
        if (this.o < 1.0f) {
            float f7 = f6 + f5;
            if (f7 > 1.0f) {
                m(canvas, paint, f6, 1.0f, i, i2, 0, f3, f4, z);
                m(canvas, paint, 1.0f, f7, i, 0, i3, f3, f4, z);
                return;
            }
        }
        float degrees = (float) Math.toDegrees(this.g / this.i);
        float f8 = f5 - 0.99f;
        if (f8 >= 0.0f) {
            float f9 = ((f8 * degrees) / 180.0f) / 0.01f;
            f5 += f9;
            if (!z) {
                f6 -= f9 / 2.0f;
            }
        }
        float fD = ax.p218n7.a.d(1.0f - this.o, 1.0f, f6);
        float fD2 = ax.p218n7.a.d(0.0f, this.o, f5);
        float degrees2 = (float) Math.toDegrees(i2 / this.i);
        float degrees3 = ((fD2 * 360.0f) - degrees2) - ((float) Math.toDegrees(i3 / this.i));
        float f10 = (fD * 360.0f) + degrees2;
        if (degrees3 <= 0.0f) {
            return;
        }
        boolean z2 = ((f) this.a).b(this.n) && z && f3 > 0.0f;
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStrokeWidth(this.f);
        float f11 = this.g * 2.0f;
        float f12 = degrees * 2.0f;
        if (degrees3 < f12) {
            float f13 = degrees3 / f12;
            float f14 = f10 + (degrees * f13);
            i<f>.b bVar = new i.b();
            if (z2) {
                float length = ((f14 / 360.0f) * this.d.getLength()) / 2.0f;
                float f15 = this.h * f3;
                float f16 = this.i;
                if (f16 != this.m || f15 != this.k) {
                    this.k = f15;
                    this.m = f16;
                    g();
                }
                this.d.getPosTan(length, bVar.a, bVar.b);
            } else {
                bVar.d(f14 + 90.0f);
                bVar.a(-this.i);
            }
            paint.setStyle(Paint.Style.FILL);
            o(canvas, paint, bVar, f11, this.f, f13);
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(((f) this.a).g() ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        float f17 = f10 + degrees;
        float f18 = degrees3 - f12;
        ((i.b) this.q.first).c();
        ((i.b) this.q.second).c();
        if (z2) {
            k(this.d, this.c, this.q, f17 / 360.0f, f18 / 360.0f, f3, f4);
            canvas.drawPath(this.c, paint);
        } else {
            ((i.b) this.q.first).d(f17 + 90.0f);
            ((i.b) this.q.first).a(-this.i);
            ((i.b) this.q.second).d(f17 + f18 + 90.0f);
            ((i.b) this.q.second).a(-this.i);
            RectF rectF = this.p;
            float f19 = this.i;
            rectF.set(-f19, -f19, f19, f19);
            canvas.drawArc(this.p, f17, f18, false, paint);
        }
        if (((f) this.a).g() || this.g <= 0.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        n(canvas, paint, (i.b) this.q.first, f11, this.f);
        n(canvas, paint, (i.b) this.q.second, f11, this.f);
    }

    private void n(Canvas canvas, Paint paint, i<f>.b bVar, float f, float f2) {
        o(canvas, paint, bVar, f, f2, 1.0f);
    }

    private void o(Canvas canvas, Paint paint, i<f>.b bVar, float f, float f2, float f3) {
        float fMin = Math.min(f2, this.f);
        float f4 = f / 2.0f;
        float fMin2 = Math.min(f4, (this.g * fMin) / this.f);
        RectF rectF = new RectF((-f) / 2.0f, (-fMin) / 2.0f, f4, fMin / 2.0f);
        canvas.save();
        float[] fArr = bVar.a;
        canvas.translate(fArr[0], fArr[1]);
        canvas.rotate(i(bVar.b));
        canvas.scale(f3, f3);
        canvas.drawRoundRect(rectF, fMin2, fMin2, paint);
        canvas.restore();
    }

    private int p() {
        S s = this.a;
        return ((f) s).p + (((f) s).q * 2);
    }

    @Override // com.google.android.material.progressindicator.i
    void a(Canvas canvas, Rect rect, float f, boolean z, boolean z2) {
        float fWidth = rect.width() / f();
        float fHeight = rect.height() / e();
        S s = this.a;
        float f2 = (((f) s).p / 2.0f) + ((f) s).q;
        canvas.translate((f2 * fWidth) + rect.left, (f2 * fHeight) + rect.top);
        canvas.rotate(-90.0f);
        canvas.scale(fWidth, fHeight);
        if (((f) this.a).r != 0) {
            canvas.scale(1.0f, -1.0f);
            if (Build.VERSION.SDK_INT == 29) {
                canvas.rotate(0.1f);
            }
        }
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        S s2 = this.a;
        this.f = ((f) s2).a * f;
        this.g = Math.min(((f) s2).a / 2, ((f) s2).a()) * f;
        S s3 = this.a;
        this.h = ((f) s3).l * f;
        float f4 = (((f) s3).p - ((f) s3).a) / 2.0f;
        this.i = f4;
        if (z || z2) {
            float f5 = ((1.0f - f) * ((f) s3).a) / 2.0f;
            if ((z && ((f) s3).g == 2) || (z2 && ((f) s3).h == 1)) {
                this.i = f4 + f5;
            } else if ((z && ((f) s3).g == 1) || (z2 && ((f) s3).h == 2)) {
                this.i = f4 - f5;
            }
        }
        if (z2 && ((f) s3).h == 3) {
            this.o = f;
        } else {
            this.o = 1.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.i
    void b(Canvas canvas, Paint paint, int i, int i2) {
    }

    @Override // com.google.android.material.progressindicator.i
    void c(Canvas canvas, Paint paint, i.a aVar, int i) {
        int iA = ax.p106g7.a.a(aVar.c, i);
        canvas.save();
        canvas.rotate(aVar.g);
        this.n = aVar.h;
        float f = aVar.a;
        float f2 = aVar.b;
        int i2 = aVar.d;
        m(canvas, paint, f, f2, iA, i2, i2, aVar.e, aVar.f, true);
        canvas.restore();
    }

    @Override // com.google.android.material.progressindicator.i
    void d(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3) {
        int iA = ax.p106g7.a.a(i, i2);
        this.n = false;
        m(canvas, paint, f, f2, iA, i3, i3, 0.0f, 0.0f, false);
    }

    @Override // com.google.android.material.progressindicator.i
    int e() {
        return p();
    }

    @Override // com.google.android.material.progressindicator.i
    int f() {
        return p();
    }

    @Override // com.google.android.material.progressindicator.i
    void g() {
        this.b.rewind();
        this.b.moveTo(1.0f, 0.0f);
        for (int i = 0; i < 2; i++) {
            this.b.cubicTo(1.0f, 0.5522848f, 0.5522848f, 1.0f, 0.0f, 1.0f);
            this.b.cubicTo(-0.5522848f, 1.0f, -1.0f, 0.5522848f, -1.0f, 0.0f);
            this.b.cubicTo(-1.0f, -0.5522848f, -0.5522848f, -1.0f, 0.0f, -1.0f);
            this.b.cubicTo(0.5522848f, -1.0f, 1.0f, -0.5522848f, 1.0f, 0.0f);
        }
        this.e.reset();
        Matrix matrix = this.e;
        float f = this.i;
        matrix.setScale(f, f);
        this.b.transform(this.e);
        if (((f) this.a).b(this.n)) {
            this.d.setPath(this.b, false);
            l(this.d, this.b, this.k);
        }
        this.d.setPath(this.b, false);
    }
}
