package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import ax.R6.l;
import ax.R6.m;
import ax.p202m7.z;
import ax.p234o7.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class ClockHandView extends View {
    private double A0;
    private int B0;
    private int C0;
    private final TimeInterpolator k0;
    private final ValueAnimator l0;
    private boolean m0;
    private float n0;
    private float o0;
    private boolean p0;
    private final int q;
    private final int q0;
    private boolean r0;
    private final List<b> s0;
    private final int t0;
    private final float u0;
    private final Paint v0;
    private final RectF w0;
    private final int x0;
    private float y0;
    private boolean z0;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            animator.end();
        }
    }

    public interface b {
        void a(float f, boolean z);
    }

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.y);
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l0 = new ValueAnimator();
        this.s0 = new ArrayList();
        Paint paint = new Paint();
        this.v0 = paint;
        this.w0 = new RectF();
        this.C0 = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.C1, i, l.F);
        this.q = i.f(context, ax.R6.c.D, 200);
        this.k0 = i.g(context, ax.R6.c.M, ax.S6.a.b);
        this.B0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(m.E1, 0);
        this.t0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(m.F1, 0);
        Resources resources = getResources();
        this.x0 = resources.getDimensionPixelSize(ax.R6.e.D);
        this.u0 = resources.getDimensionPixelSize(ax.R6.e.B);
        int color = typedArrayObtainStyledAttributes.getColor(m.D1, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        n(0.0f);
        this.q0 = ViewConfiguration.get(context).getScaledTouchSlop();
        setImportantForAccessibility(2);
        typedArrayObtainStyledAttributes.recycle();
        l();
    }

    public static /* synthetic */ void a(ClockHandView clockHandView, ValueAnimator valueAnimator) {
        clockHandView.getClass();
        clockHandView.p(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
    }

    private void c(float f, float f2) {
        this.C0 = ax.p218n7.a.b((float) (getWidth() / 2), (float) (getHeight() / 2), f, f2) > ((float) h(2)) + z.c(getContext(), 12) ? 1 : 2;
    }

    private void d(Canvas canvas) {
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        int iH = h(this.C0);
        float f = width;
        float f2 = iH;
        float fCos = (((float) Math.cos(this.A0)) * f2) + f;
        float f3 = height;
        float fSin = (f2 * ((float) Math.sin(this.A0))) + f3;
        this.v0.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.t0, this.v0);
        double dSin = Math.sin(this.A0);
        double dCos = Math.cos(this.A0);
        double d = iH - this.t0;
        this.v0.setStrokeWidth(this.x0);
        canvas.drawLine(f, f3, width + ((int) (dCos * d)), height + ((int) (d * dSin)), this.v0);
        canvas.drawCircle(f, f3, this.u0, this.v0);
    }

    private int f(float f, float f2) {
        int degrees = (int) Math.toDegrees(Math.atan2(f2 - (getHeight() / 2), f - (getWidth() / 2)));
        int i = degrees + 90;
        return i < 0 ? degrees + 450 : i;
    }

    private int h(int i) {
        return i == 2 ? Math.round(this.B0 * 0.66f) : this.B0;
    }

    private Pair<Float, Float> j(float f) {
        float fG = g();
        if (Math.abs(fG - f) > 180.0f) {
            if (fG > 180.0f && f < 180.0f) {
                f += 360.0f;
            }
            if (fG < 180.0f && f > 180.0f) {
                fG += 360.0f;
            }
        }
        return new Pair<>(Float.valueOf(fG), Float.valueOf(f));
    }

    private boolean k(float f, float f2, boolean z, boolean z2, boolean z3) {
        float f3 = f(f, f2);
        boolean z4 = false;
        boolean z5 = g() != f3;
        if (z2 && z5) {
            return true;
        }
        if (!z5 && !z) {
            return false;
        }
        if (z3 && this.m0) {
            z4 = true;
        }
        o(f3, z4);
        return true;
    }

    private void l() {
        this.l0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.timepicker.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ClockHandView.a(this.a, valueAnimator);
            }
        });
        this.l0.addListener(new a());
    }

    private void p(float f, boolean z) {
        float f2 = f % 360.0f;
        this.y0 = f2;
        this.A0 = Math.toRadians(f2 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float fH = h(this.C0);
        float fCos = width + (((float) Math.cos(this.A0)) * fH);
        float fSin = height + (fH * ((float) Math.sin(this.A0)));
        RectF rectF = this.w0;
        int i = this.t0;
        rectF.set(fCos - i, fSin - i, fCos + i, fSin + i);
        Iterator<b> it = this.s0.iterator();
        while (it.hasNext()) {
            it.next().a(f2, z);
        }
        invalidate();
    }

    public void b(b bVar) {
        this.s0.add(bVar);
    }

    public RectF e() {
        return this.w0;
    }

    public float g() {
        return this.y0;
    }

    public int i() {
        return this.t0;
    }

    public void m(int i) {
        this.B0 = i;
        invalidate();
    }

    public void n(float f) {
        o(f, false);
    }

    public void o(float f, boolean z) {
        this.l0.cancel();
        if (!z) {
            p(f, false);
            return;
        }
        Pair<Float, Float> pairJ = j(f);
        this.l0.setFloatValues(((Float) pairJ.first).floatValue(), ((Float) pairJ.second).floatValue());
        this.l0.setDuration(this.q);
        this.l0.setInterpolator(this.k0);
        this.l0.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        d(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.l0.isRunning()) {
            return;
        }
        n(g());
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 2) {
                int i = (int) (x - this.n0);
                int i2 = (int) (y - this.o0);
                this.p0 = (i * i) + (i2 * i2) > this.q0;
                z = this.z0;
                boolean z4 = actionMasked == 1;
                if (this.r0) {
                    c(x, y);
                }
                z3 = z4;
                z2 = false;
            } else {
                z = false;
                z2 = false;
            }
            this.z0 |= k(x, y, z, z2, z3);
            return true;
        }
        this.n0 = x;
        this.o0 = y;
        this.p0 = true;
        this.z0 = false;
        z = false;
        z2 = true;
        z3 = false;
        this.z0 |= k(x, y, z, z2, z3);
        return true;
    }

    void q(boolean z) {
        if (this.r0 && !z) {
            this.C0 = 1;
        }
        this.r0 = z;
        invalidate();
    }
}
