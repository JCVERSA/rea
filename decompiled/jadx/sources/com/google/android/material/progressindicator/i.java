package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import com.google.android.material.progressindicator.b;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public abstract class i<S extends com.google.android.material.progressindicator.b> {
    S a;
    final Path b;
    final Path c;
    final PathMeasure d;
    final Matrix e;

    /* JADX INFO: Access modifiers changed from: protected */
    public static class a {
        float a;
        float b;
        int c;
        int d;
        float e = 1.0f;
        float f;
        float g;
        boolean h;

        protected a() {
        }
    }

    protected class b {
        float[] a;
        float[] b;
        final Matrix c;

        public b() {
            this.a = new float[2];
            this.b = new float[]{1.0f, 0.0f};
            this.c = new Matrix();
        }

        public b(i iVar, i<S>.b bVar) {
            this(bVar.a, bVar.b);
        }

        public b(float[] fArr, float[] fArr2) {
            float[] fArr3 = new float[2];
            this.a = fArr3;
            this.b = new float[2];
            System.arraycopy(fArr, 0, fArr3, 0, 2);
            System.arraycopy(fArr2, 0, this.b, 0, 2);
            this.c = new Matrix();
        }

        void a(float f) {
            float[] fArr = this.b;
            float fAtan2 = (float) (Math.atan2(fArr[1], fArr[0]) + 1.5707963267948966d);
            float[] fArr2 = this.a;
            double d = f;
            double d2 = fAtan2;
            fArr2[0] = (float) (((double) fArr2[0]) + (Math.cos(d2) * d));
            float[] fArr3 = this.a;
            fArr3[1] = (float) (((double) fArr3[1]) + (d * Math.sin(d2)));
        }

        void b(float f) {
            float[] fArr = this.b;
            float fAtan2 = (float) Math.atan2(fArr[1], fArr[0]);
            float[] fArr2 = this.a;
            double d = f;
            double d2 = fAtan2;
            fArr2[0] = (float) (((double) fArr2[0]) + (Math.cos(d2) * d));
            float[] fArr3 = this.a;
            fArr3[1] = (float) (((double) fArr3[1]) + (d * Math.sin(d2)));
        }

        public void c() {
            Arrays.fill(this.a, 0.0f);
            Arrays.fill(this.b, 0.0f);
            this.b[0] = 1.0f;
            this.c.reset();
        }

        public void d(float f) {
            this.c.reset();
            this.c.setRotate(f);
            this.c.mapPoints(this.a);
            this.c.mapPoints(this.b);
        }

        void e(float f, float f2) {
            float[] fArr = this.a;
            fArr[0] = fArr[0] * f;
            fArr[1] = fArr[1] * f2;
            float[] fArr2 = this.b;
            fArr2[0] = fArr2[0] * f;
            fArr2[1] = fArr2[1] * f2;
        }

        void f(float f, float f2) {
            float[] fArr = this.a;
            fArr[0] = fArr[0] + f;
            fArr[1] = fArr[1] + f2;
        }
    }

    public i(S s) {
        Path path = new Path();
        this.b = path;
        this.c = new Path();
        this.d = new PathMeasure(path, false);
        this.a = s;
        this.e = new Matrix();
    }

    abstract void a(Canvas canvas, Rect rect, float f, boolean z, boolean z2);

    abstract void b(Canvas canvas, Paint paint, int i, int i2);

    abstract void c(Canvas canvas, Paint paint, a aVar, int i);

    abstract void d(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3);

    abstract int e();

    abstract int f();

    abstract void g();

    void h(Canvas canvas, Rect rect, float f, boolean z, boolean z2) {
        this.a.h();
        a(canvas, rect, f, z, z2);
    }

    float i(float[] fArr) {
        return (float) Math.toDegrees(Math.atan2(fArr[1], fArr[0]));
    }
}
