package com.android.ex.photo.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import ax.p035c0.C1867s;
import ax.p035c0.N;
import com.android.ex.photo.h;
import com.android.ex.photo.i;

/* JADX INFO: loaded from: classes.dex */
public class PhotoView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener {
    private static int T0;
    private static boolean U0;
    private static int V0;
    private static Bitmap W0;
    private static Bitmap X0;
    private static Paint Y0;
    private static Paint Z0;
    private boolean A0;
    private boolean B0;
    private boolean C0;
    private boolean D0;
    private c E0;
    private float F0;
    private float G0;
    private e H0;
    private d I0;
    private b J0;
    private float K0;
    private RectF L0;
    private RectF M0;
    private RectF N0;
    private float[] O0;
    private boolean P0;
    private float Q0;
    private float R0;
    private boolean S0;
    private Matrix k0;
    private Matrix l0;
    private Matrix m0;
    private int n0;
    private boolean o0;
    private boolean p0;
    private Drawable q;
    private byte[] q0;
    private boolean r0;
    private boolean s0;
    private Rect t0;
    private int u0;
    private float v0;
    private C1867s w0;
    private ScaleGestureDetector x0;
    private View.OnClickListener y0;
    private a z0;

    public interface a {
        void a();
    }

    private static class b implements Runnable {
        private float X;
        private float Y;
        private float Z;
        private long k0;
        private boolean l0;
        private boolean m0;
        private final PhotoView q;

        public b(PhotoView photoView) {
            this.q = photoView;
        }

        public void a(float f) {
            if (this.l0) {
                return;
            }
            this.X = f;
            this.Z = f / 500.0f;
            this.Y = 0.0f;
            this.k0 = -1L;
            this.m0 = false;
            this.l0 = true;
            this.q.post(this);
        }

        public void b() {
            this.l0 = false;
            this.m0 = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.m0) {
                return;
            }
            if (this.Y != this.X) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = this.k0;
                float f = this.Z * (j != -1 ? jCurrentTimeMillis - j : 0L);
                float f2 = this.Y;
                float f3 = this.X;
                if ((f2 < f3 && f2 + f > f3) || (f2 > f3 && f2 + f < f3)) {
                    f = f3 - f2;
                }
                this.q.s(f, false);
                float f4 = this.Y + f;
                this.Y = f4;
                if (f4 == this.X) {
                    b();
                }
                this.k0 = jCurrentTimeMillis;
            }
            if (this.m0) {
                return;
            }
            this.q.post(this);
        }
    }

    private static class c implements Runnable {
        private float X;
        private float Y;
        private boolean Z;
        private float k0;
        private float l0;
        private float m0;
        private long n0;
        private boolean o0;
        private boolean p0;
        private final PhotoView q;

        public c(PhotoView photoView) {
            this.q = photoView;
        }

        public boolean b(float f, float f2, float f3, float f4) {
            if (this.o0) {
                return false;
            }
            this.X = f3;
            this.Y = f4;
            this.k0 = f2;
            this.n0 = System.currentTimeMillis();
            this.l0 = f;
            float f5 = this.k0;
            this.Z = f5 > f;
            this.m0 = (f5 - f) / 200.0f;
            this.o0 = true;
            this.p0 = false;
            this.q.post(this);
            return true;
        }

        public void c() {
            this.o0 = false;
            this.p0 = true;
        }

        /* JADX WARN: Code duplicated, block: B:12:0x002e  */
        @Override // java.lang.Runnable
        public void run() {
            if (this.p0) {
                return;
            }
            float fCurrentTimeMillis = this.l0 + (this.m0 * (System.currentTimeMillis() - this.n0));
            this.q.t(fCurrentTimeMillis, this.X, this.Y);
            float f = this.k0;
            if (fCurrentTimeMillis != f) {
                if (this.Z == (fCurrentTimeMillis > f)) {
                    this.q.t(f, this.X, this.Y);
                    c();
                }
            } else {
                this.q.t(f, this.X, this.Y);
                c();
            }
            if (this.p0) {
                return;
            }
            this.q.post(this);
        }
    }

    private static class d implements Runnable {
        private float X;
        private float Y;
        private long Z = -1;
        private boolean k0;
        private boolean l0;
        private final PhotoView q;

        public d(PhotoView photoView) {
            this.q = photoView;
        }

        public boolean a(float f, float f2) {
            if (this.k0) {
                return false;
            }
            this.Z = -1L;
            this.X = f;
            this.Y = f2;
            this.l0 = false;
            this.k0 = true;
            this.q.postDelayed(this, 250L);
            return true;
        }

        public void b() {
            this.k0 = false;
            this.l0 = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            float f;
            float f2;
            if (this.l0) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.Z;
            float f3 = j != -1 ? jCurrentTimeMillis - j : 0.0f;
            if (j == -1) {
                this.Z = jCurrentTimeMillis;
            }
            if (f3 >= 100.0f) {
                f2 = this.X;
                f = this.Y;
            } else {
                float f4 = 100.0f - f3;
                float f5 = (this.X / f4) * 10.0f;
                float f6 = (this.Y / f4) * 10.0f;
                if (Math.abs(f5) > Math.abs(this.X) || Float.isNaN(f5)) {
                    f5 = this.X;
                }
                if (Math.abs(f6) > Math.abs(this.Y) || Float.isNaN(f6)) {
                    f6 = this.Y;
                }
                float f7 = f5;
                f = f6;
                f2 = f7;
            }
            this.q.y(f2, f);
            float f8 = this.X - f2;
            this.X = f8;
            float f9 = this.Y - f;
            this.Y = f9;
            if (f8 == 0.0f && f9 == 0.0f) {
                b();
            }
            if (this.l0) {
                return;
            }
            this.q.post(this);
        }
    }

    private static class e implements Runnable {
        private float X;
        private float Y;
        private float Z;
        private float k0;
        private long l0 = -1;
        private boolean m0;
        private boolean n0;
        private final PhotoView q;

        public e(PhotoView photoView) {
            this.q = photoView;
        }

        public boolean b(float f, float f2) {
            if (this.m0) {
                return false;
            }
            this.l0 = -1L;
            this.X = f;
            this.Y = f2;
            double dAtan2 = (float) Math.atan2(f2, f);
            this.Z = (float) (Math.cos(dAtan2) * 1000.0d);
            this.k0 = (float) (Math.sin(dAtan2) * 1000.0d);
            this.n0 = false;
            this.m0 = true;
            this.q.post(this);
            return true;
        }

        public void c() {
            this.m0 = false;
            this.n0 = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.n0) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.l0;
            float f = j != -1 ? (jCurrentTimeMillis - j) / 1000.0f : 0.0f;
            int iY = this.q.y(this.X * f, this.Y * f);
            this.l0 = jCurrentTimeMillis;
            float f2 = this.Z * f;
            if (Math.abs(this.X) > Math.abs(f2)) {
                this.X -= f2;
            } else {
                this.X = 0.0f;
            }
            float f3 = this.k0 * f;
            if (Math.abs(this.Y) > Math.abs(f3)) {
                this.Y -= f3;
            } else {
                this.Y = 0.0f;
            }
            float f4 = this.X;
            if ((f4 == 0.0f && this.Y == 0.0f) || iY == 0) {
                c();
                this.q.x();
            } else if (iY == 1) {
                this.Z = f4 <= 0.0f ? -1000.0f : 1000.0f;
                this.k0 = 0.0f;
                this.Y = 0.0f;
            } else if (iY == 2) {
                this.Z = 0.0f;
                this.k0 = this.Y <= 0.0f ? -1000.0f : 1000.0f;
                this.X = 0.0f;
            }
            if (this.n0) {
                return;
            }
            this.q.post(this);
        }
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l0 = new Matrix();
        this.m0 = new Matrix();
        this.n0 = -1;
        this.t0 = new Rect();
        this.B0 = true;
        this.L0 = new RectF();
        this.M0 = new RectF();
        this.N0 = new RectF();
        this.O0 = new float[9];
        n();
    }

    private int getCropSize() {
        int i = this.u0;
        return i > 0 ? i : V0;
    }

    private float getScale() {
        this.l0.getValues(this.O0);
        return this.O0[0];
    }

    private void h(boolean z) {
        Drawable drawable = this.q;
        if (drawable == null || !this.o0) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = this.q.getIntrinsicHeight();
        boolean z2 = (intrinsicWidth < 0 || getWidth() == intrinsicWidth) && (intrinsicHeight < 0 || getHeight() == intrinsicHeight);
        this.q.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        if (z || (this.F0 == 0.0f && this.q != null && this.o0)) {
            k();
            l();
        }
        if (z2 || this.l0.isIdentity()) {
            this.k0 = null;
        } else {
            this.k0 = this.l0;
        }
        i();
    }

    private void i() {
        a aVar = this.z0;
        if (aVar != null) {
            aVar.a();
        }
    }

    private void k() {
        int intrinsicWidth = this.q.getIntrinsicWidth();
        int intrinsicHeight = this.q.getIntrinsicHeight();
        int width = this.s0 ? V0 : getWidth();
        int height = this.s0 ? V0 : getHeight();
        if ((intrinsicWidth < 0 || width == intrinsicWidth) && ((intrinsicHeight < 0 || height == intrinsicHeight) && !this.s0)) {
            this.l0.reset();
        } else {
            float f = intrinsicWidth;
            float f2 = intrinsicHeight;
            this.L0.set(0.0f, 0.0f, f, f2);
            if (this.s0) {
                this.M0.set(this.t0);
            } else {
                this.M0.set(0.0f, 0.0f, width, height);
            }
            float f3 = width / 2;
            float f4 = this.v0;
            float f5 = height / 2;
            RectF rectF = new RectF(f3 - ((f * f4) / 2.0f), f5 - ((f2 * f4) / 2.0f), f3 + ((f * f4) / 2.0f), f5 + ((f2 * f4) / 2.0f));
            if (this.M0.contains(rectF)) {
                this.l0.setRectToRect(this.L0, rectF, Matrix.ScaleToFit.CENTER);
            } else {
                this.l0.setRectToRect(this.L0, this.M0, Matrix.ScaleToFit.CENTER);
            }
        }
        this.m0.set(this.l0);
    }

    private void l() {
        int intrinsicWidth = this.q.getIntrinsicWidth();
        int intrinsicHeight = this.q.getIntrinsicHeight();
        int cropSize = this.s0 ? getCropSize() : getWidth();
        int cropSize2 = this.s0 ? getCropSize() : getHeight();
        if (intrinsicWidth >= cropSize || intrinsicHeight >= cropSize2 || this.s0) {
            this.F0 = getScale();
        } else {
            this.F0 = 1.0f;
        }
        this.G0 = Math.max(this.F0 * 4.0f, 4.0f);
    }

    private float m(Matrix matrix) {
        matrix.getValues(this.O0);
        float[] fArr = this.O0;
        return (float) Math.hypot(fArr[0], fArr[3]);
    }

    private void n() {
        Context context = getContext();
        if (!U0) {
            U0 = true;
            Resources resources = context.getApplicationContext().getResources();
            V0 = resources.getDimensionPixelSize(i.b);
            Paint paint = new Paint();
            Y0 = paint;
            paint.setAntiAlias(true);
            Y0.setColor(resources.getColor(h.a));
            Y0.setStyle(Paint.Style.FILL);
            Paint paint2 = new Paint();
            Z0 = paint2;
            paint2.setAntiAlias(true);
            Z0.setColor(resources.getColor(h.b));
            Z0.setStyle(Paint.Style.STROKE);
            Z0.setStrokeWidth(resources.getDimension(i.a));
            int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
            T0 = scaledTouchSlop * scaledTouchSlop;
        }
        this.w0 = new C1867s(context, this, null);
        ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(context, this);
        this.x0 = scaleGestureDetector;
        this.S0 = N.a(scaleGestureDetector);
        this.E0 = new c(this);
        this.H0 = new e(this);
        this.I0 = new d(this);
        this.J0 = new b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(float f, boolean z) {
        if (z) {
            this.J0.a(f);
            return;
        }
        this.K0 += f;
        this.l0.postRotate(f, getWidth() / 2, getHeight() / 2);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(float f, float f2, float f3) {
        this.l0.postRotate(-this.K0, getWidth() / 2, getHeight() / 2);
        float fMin = Math.min(Math.max(f, this.F0), this.G0 * 1.5f) / getScale();
        this.l0.postScale(fMin, fMin, f2, f3);
        this.l0.postRotate(this.K0, getWidth() / 2, getHeight() / 2);
        invalidate();
        i();
    }

    private boolean u(MotionEvent motionEvent) {
        boolean z;
        float fCenterY;
        float width;
        if (this.B0 && this.A0 && this.P0) {
            if (this.C0) {
                z = false;
            } else {
                float scale = getScale();
                float fMin = this.F0;
                if (scale > fMin) {
                    float f = fMin / scale;
                    float f2 = 1.0f - f;
                    width = ((getWidth() / 2) - (this.N0.centerX() * f)) / f2;
                    fCenterY = ((getHeight() / 2) - (f * this.N0.centerY())) / f2;
                } else {
                    fMin = Math.min(this.G0, Math.max(fMin, scale * 2.0f));
                    float f3 = fMin / scale;
                    float width2 = (getWidth() - this.N0.width()) / f3;
                    float height = (getHeight() - this.N0.height()) / f3;
                    float fCenterX = this.N0.width() <= width2 * 2.0f ? this.N0.centerX() : Math.min(Math.max(this.N0.left + width2, motionEvent.getX()), this.N0.right - width2);
                    fCenterY = this.N0.height() <= 2.0f * height ? this.N0.centerY() : Math.min(Math.max(this.N0.top + height, motionEvent.getY()), this.N0.bottom - height);
                    width = fCenterX;
                }
                this.E0.b(scale, fMin, width, fCenterY);
                z = true;
            }
            this.C0 = false;
        } else {
            z = false;
        }
        this.P0 = false;
        return z;
    }

    private void v(float f) {
        float f2 = this.G0;
        if (f > f2) {
            float f3 = 1.0f / (1.0f - (f2 / f));
            float f4 = 1.0f - f3;
            float width = getWidth() / 2;
            float height = getHeight() / 2;
            RectF rectF = this.N0;
            float f5 = rectF.left * f4;
            float f6 = rectF.top * f4;
            float width2 = (getWidth() * f3) + (this.N0.right * f4);
            float height2 = (getHeight() * f3) + (this.N0.bottom * f4);
            this.E0.b(f, this.G0, width2 > f5 ? (width2 + f5) / 2.0f : Math.min(Math.max(width2, width), f5), height2 > f6 ? (height2 + f6) / 2.0f : Math.min(Math.max(height2, height), f6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        float f;
        this.N0.set(this.L0);
        this.l0.mapRect(this.N0);
        boolean z = this.s0;
        float f2 = 0.0f;
        float f3 = z ? this.t0.left : 0.0f;
        float width = z ? this.t0.right : getWidth();
        RectF rectF = this.N0;
        float f4 = rectF.left;
        float f5 = rectF.right;
        float f6 = width - f3;
        if (f5 - f4 < f6) {
            f = f3 + ((f6 - (f5 + f4)) / 2.0f);
        } else if (f4 > f3) {
            f = f3 - f4;
        } else {
            f = f5 < width ? width - f5 : 0.0f;
        }
        boolean z2 = this.s0;
        float f7 = z2 ? this.t0.top : 0.0f;
        float height = z2 ? this.t0.bottom : getHeight();
        RectF rectF2 = this.N0;
        float f8 = rectF2.top;
        float f9 = rectF2.bottom;
        float f10 = height - f7;
        if (f9 - f8 < f10) {
            f2 = f7 + ((f10 - (f9 + f8)) / 2.0f);
        } else if (f8 > f7) {
            f2 = f7 - f8;
        } else if (f9 < height) {
            f2 = height - f9;
        }
        if (Math.abs(f) > 20.0f || Math.abs(f2) > 20.0f) {
            this.I0.a(f, f2);
        } else {
            this.l0.postTranslate(f, f2);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int y(float f, float f2) {
        float fMax;
        float fMax2;
        this.N0.set(this.L0);
        this.l0.mapRect(this.N0);
        boolean z = this.s0;
        float f3 = z ? this.t0.left : 0.0f;
        float width = z ? this.t0.right : getWidth();
        RectF rectF = this.N0;
        float f4 = rectF.left;
        float f5 = rectF.right;
        if (this.s0) {
            fMax = Math.max(f3 - f5, Math.min(width - f4, f));
        } else {
            float f6 = width - f3;
            fMax = f5 - f4 < f6 ? f3 + ((f6 - (f5 + f4)) / 2.0f) : Math.max(width - f5, Math.min(f3 - f4, f));
        }
        boolean z2 = this.s0;
        float f7 = z2 ? this.t0.top : 0.0f;
        float height = z2 ? this.t0.bottom : getHeight();
        RectF rectF2 = this.N0;
        float f8 = rectF2.top;
        float f9 = rectF2.bottom;
        if (this.s0) {
            fMax2 = Math.max(f7 - f9, Math.min(height - f8, f2));
        } else {
            float f10 = height - f7;
            fMax2 = f9 - f8 < f10 ? f7 + ((f10 - (f9 + f8)) / 2.0f) : Math.max(height - f9, Math.min(f7 - f8, f2));
        }
        this.l0.postTranslate(fMax, fMax2);
        invalidate();
        boolean z3 = fMax == f;
        boolean z4 = fMax2 == f2;
        if (z3 && z4) {
            return 3;
        }
        if (z3) {
            return 1;
        }
        return z4 ? 2 : 0;
    }

    public void e(Drawable drawable) {
        boolean z;
        Drawable drawable2;
        if (drawable == null || drawable == (drawable2 = this.q)) {
            z = false;
        } else {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.q = drawable;
            this.F0 = 0.0f;
            drawable.setCallback(this);
            z = true;
        }
        h(z);
        invalidate();
    }

    public void f(Bitmap bitmap) {
        Drawable drawable = this.q;
        boolean z = drawable instanceof BitmapDrawable;
        boolean z2 = !z;
        if (drawable != null && z) {
            if (bitmap == ((BitmapDrawable) drawable).getBitmap()) {
                return;
            }
            z2 = (bitmap == null || (this.q.getIntrinsicWidth() == bitmap.getWidth() && this.q.getIntrinsicHeight() == bitmap.getHeight())) ? false : true;
            this.F0 = 0.0f;
            this.q = null;
        }
        if (this.q == null && bitmap != null) {
            this.q = new BitmapDrawable(getResources(), bitmap);
        }
        h(z2);
        invalidate();
    }

    public void g() {
        this.w0 = null;
        this.x0 = null;
        this.q = null;
        this.E0.c();
        this.E0 = null;
        this.H0.c();
        this.H0 = null;
        this.I0.b();
        this.I0 = null;
        this.J0.b();
        this.J0 = null;
        setOnClickListener(null);
        this.y0 = null;
        this.z0 = null;
        this.P0 = false;
    }

    public Bitmap getCroppedPhoto() {
        if (!this.s0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Rect rect = this.t0;
        float f = 256.0f / (rect.right - rect.left);
        Matrix matrix = new Matrix(this.k0);
        Rect rect2 = this.t0;
        matrix.postTranslate(-rect2.left, -rect2.top);
        matrix.postScale(f, f);
        if (this.q != null) {
            canvas.concat(matrix);
            this.q.draw(canvas);
        }
        return bitmapCreateBitmap;
    }

    public float getCurrentScale() {
        return m(this.l0);
    }

    public Drawable getDrawable() {
        return this.q;
    }

    public float getInitialScale() {
        return m(this.m0);
    }

    public float getMinScale() {
        return this.F0;
    }

    public Bitmap getPhoto() {
        Drawable drawable = this.q;
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public byte[] getVideoData() {
        return this.q0;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.q == drawable) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void j(boolean z) {
        this.A0 = z;
        if (z) {
            return;
        }
        r();
    }

    public boolean o(float f, float f2) {
        if (!this.A0) {
            return false;
        }
        if (this.H0.m0) {
            return true;
        }
        this.l0.getValues(this.O0);
        this.N0.set(this.L0);
        this.l0.mapRect(this.N0);
        float width = getWidth();
        float f3 = this.O0[2];
        RectF rectF = this.N0;
        return this.A0 && rectF.right - rectF.left > width && f3 != 0.0f;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.P0 = true;
        if (this.S0) {
            return false;
        }
        return u(motionEvent);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2 && this.S0 && this.P0) {
                    int x = (int) (motionEvent.getX() - this.Q0);
                    int y = (int) (motionEvent.getY() - this.R0);
                    if ((x * x) + (y * y) > T0) {
                        this.P0 = false;
                        return false;
                    }
                }
            } else if (this.S0) {
                return u(motionEvent);
            }
        } else if (this.S0) {
            this.Q0 = motionEvent.getX();
            this.R0 = motionEvent.getY();
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        if (!this.A0) {
            return true;
        }
        this.H0.c();
        this.I0.b();
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Canvas canvas2;
        super.onDraw(canvas);
        Drawable drawable = this.q;
        if (drawable != null) {
            if (drawable instanceof ax.p242p.c) {
                Drawable drawableA = ((ax.p242p.c) drawable).a();
                if ((drawableA instanceof BitmapDrawable) && ((BitmapDrawable) drawableA).getBitmap() == null) {
                    return;
                }
            }
            int saveCount = canvas.getSaveCount();
            canvas.save();
            Matrix matrix = this.k0;
            if (matrix != null) {
                canvas.concat(matrix);
            }
            Paint paintB = ax.I3.e.b(20);
            Drawable drawable2 = this.q;
            if (drawable2 != null) {
                canvas2 = canvas;
                canvas2.drawRect(0.0f, 0.0f, drawable2.getBounds().width(), this.q.getBounds().height(), paintB);
            } else {
                canvas2 = canvas;
            }
            try {
                this.q.draw(canvas2);
            } catch (RuntimeException unused) {
            }
            canvas2.restoreToCount(saveCount);
            if (this.q0 != null) {
                Bitmap bitmap = this.r0 ? W0 : X0;
                canvas2.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2, (getHeight() - bitmap.getHeight()) / 2, (Paint) null);
            }
            this.N0.set(this.q.getBounds());
            Matrix matrix2 = this.k0;
            if (matrix2 != null) {
                matrix2.mapRect(this.N0);
            }
            if (this.s0) {
                int saveCount2 = canvas2.getSaveCount();
                canvas2.drawRect(0.0f, 0.0f, getWidth(), getHeight(), Y0);
                canvas2.save();
                canvas2.clipRect(this.t0);
                Matrix matrix3 = this.k0;
                if (matrix3 != null) {
                    canvas2.concat(matrix3);
                }
                this.q.draw(canvas2);
                canvas2.restoreToCount(saveCount2);
                canvas2.drawRect(this.t0, Z0);
            }
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.A0 || this.E0.o0) {
            return true;
        }
        this.H0.b(f, f2);
        return true;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() != 8) {
            return super.onGenericMotionEvent(motionEvent);
        }
        float scale = getScale();
        if (motionEvent.getAxisValue(9) >= 0.0f) {
            float f = this.G0;
            if (scale >= f) {
                return true;
            }
            t(Math.min(f, scale + 0.15f), motionEvent.getX(), motionEvent.getY());
            return true;
        }
        float f2 = this.F0;
        if (scale <= f2) {
            return true;
        }
        t(Math.max(f2, scale - 0.15f), motionEvent.getX(), motionEvent.getY());
        x();
        return true;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.o0 = true;
        int width = getWidth();
        int height = getHeight();
        if (this.s0) {
            int iMin = Math.min(V0, Math.min(width, height));
            this.u0 = iMin;
            int i5 = (width - iMin) / 2;
            int i6 = (height - iMin) / 2;
            this.t0.set(i5, i6, i5 + iMin, iMin + i6);
        }
        h(z);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.n0;
        if (i3 == -1) {
            super.onMeasure(i, i2);
        } else {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE));
            setMeasuredDimension(getMeasuredWidth(), this.n0);
        }
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (!this.A0) {
            return true;
        }
        this.D0 = false;
        t(getScale() * scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (this.A0) {
            this.E0.c();
            this.D0 = true;
        }
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        float scale = getScale();
        if (scale > this.G0) {
            v(scale);
        }
        if (this.A0 && this.D0) {
            this.C0 = true;
            r();
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.A0 || this.E0.o0) {
            return true;
        }
        y(-f, -f2);
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        View.OnClickListener onClickListener = this.y0;
        if (onClickListener != null && !this.D0) {
            onClickListener.onClick(this);
        }
        this.D0 = false;
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ScaleGestureDetector scaleGestureDetector = this.x0;
        if (scaleGestureDetector != null && this.w0 != null) {
            scaleGestureDetector.onTouchEvent(motionEvent);
            this.w0.a(motionEvent);
            int action = motionEvent.getAction();
            if ((action == 1 || action == 3) && !this.H0.m0) {
                x();
            }
        }
        return true;
    }

    public boolean p(float f, float f2) {
        if (!this.A0) {
            return false;
        }
        if (this.H0.m0) {
            return true;
        }
        this.l0.getValues(this.O0);
        this.N0.set(this.L0);
        this.l0.mapRect(this.N0);
        float width = getWidth();
        float f3 = this.O0[2];
        RectF rectF = this.N0;
        float f4 = rectF.right - rectF.left;
        if (!this.A0 || f4 <= width) {
            return false;
        }
        return f3 == 0.0f || width < f4 + f3;
    }

    public boolean q() {
        return this.q != null;
    }

    public void r() {
        this.l0.set(this.m0);
        invalidate();
        i();
    }

    public void setFixedHeight(int i) {
        boolean z = i != this.n0;
        this.n0 = i;
        setMeasuredDimension(getMeasuredWidth(), this.n0);
        if (z) {
            h(true);
            requestLayout();
        }
    }

    public void setMaxInitialScale(float f) {
        this.v0 = f;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.y0 = onClickListener;
    }

    public void setOnScaleChangedListener(a aVar) {
        this.z0 = aVar;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return this.q == drawable || super.verifyDrawable(drawable);
    }

    public void w(boolean z, boolean z2) {
        if (z != this.p0) {
            this.p0 = z;
            requestLayout();
            invalidate();
        }
    }
}
