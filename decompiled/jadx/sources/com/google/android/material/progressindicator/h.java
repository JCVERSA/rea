package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Property;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
abstract class h extends Drawable implements Animatable {
    private static final Property<h, Float> w0 = new c(Float.class, "growFraction");
    final com.google.android.material.progressindicator.b X;
    private ValueAnimator Z;
    private ValueAnimator k0;
    private boolean l0;
    private boolean m0;
    private float n0;
    private List<ax.p389y2.b> p0;
    final Context q;
    private ax.p389y2.b q0;
    private boolean r0;
    private float s0;
    private int u0;
    private float o0 = -1.0f;
    final Paint t0 = new Paint();
    Rect v0 = new Rect();
    ax.p250p7.a Y = new ax.p250p7.a();

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            h.this.f();
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            h.super.setVisible(false, false);
            h.this.e();
        }
    }

    class c extends Property<h, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(h hVar) {
            return Float.valueOf(hVar.h());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(h hVar, Float f) {
            hVar.p(f.floatValue());
        }
    }

    h(Context context, com.google.android.material.progressindicator.b bVar) {
        this.q = context;
        this.X = bVar;
        setAlpha(255);
    }

    private void d(ValueAnimator... valueAnimatorArr) {
        boolean z = this.r0;
        this.r0 = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.cancel();
        }
        this.r0 = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ax.p389y2.b bVar = this.q0;
        if (bVar != null) {
            bVar.b(this);
        }
        List<ax.p389y2.b> list = this.p0;
        if (list == null || this.r0) {
            return;
        }
        Iterator<ax.p389y2.b> it = list.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ax.p389y2.b bVar = this.q0;
        if (bVar != null) {
            bVar.c(this);
        }
        List<ax.p389y2.b> list = this.p0;
        if (list == null || this.r0) {
            return;
        }
        Iterator<ax.p389y2.b> it = list.iterator();
        while (it.hasNext()) {
            it.next().c(this);
        }
    }

    private void g(ValueAnimator... valueAnimatorArr) {
        boolean z = this.r0;
        this.r0 = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.end();
        }
        this.r0 = z;
    }

    private boolean k() {
        return this instanceof g;
    }

    private void n() {
        if (this.Z == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, w0, 0.0f, 1.0f);
            this.Z = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(500L);
            this.Z.setInterpolator(ax.S6.a.b);
            r(this.Z);
        }
        if (this.k0 == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, w0, 1.0f, 0.0f);
            this.k0 = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(500L);
            this.k0.setInterpolator(ax.S6.a.b);
            q(this.k0);
        }
    }

    private void q(ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.k0;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
        }
        this.k0 = valueAnimator;
        valueAnimator.addListener(new b());
    }

    private void r(ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.Z;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
        }
        this.Z = valueAnimator;
        valueAnimator.addListener(new a());
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.u0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    float h() {
        if (this.X.d() || this.X.c()) {
            return (this.m0 || this.l0) ? this.n0 : this.s0;
        }
        return 1.0f;
    }

    float i() {
        float f = this.o0;
        if (f > 0.0f) {
            return f;
        }
        if (this.X.b(k()) && this.X.m != 0) {
            float fA = this.Y.a(this.q.getContentResolver());
            if (fA > 0.0f) {
                int i = (int) ((((k() ? this.X.j : this.X.k) * 1000.0f) / this.X.m) * fA);
                float fUptimeMillis = (SystemClock.uptimeMillis() % ((long) i)) / i;
                return fUptimeMillis < 0.0f ? (fUptimeMillis % 1.0f) + 1.0f : fUptimeMillis;
            }
        }
        return 0.0f;
    }

    public boolean isRunning() {
        return m() || l();
    }

    public boolean j() {
        return s(false, false, false);
    }

    public boolean l() {
        ValueAnimator valueAnimator = this.k0;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.m0;
    }

    public boolean m() {
        ValueAnimator valueAnimator = this.Z;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.l0;
    }

    public void o(ax.p389y2.b bVar) {
        if (this.p0 == null) {
            this.p0 = new ArrayList();
        }
        if (this.p0.contains(bVar)) {
            return;
        }
        this.p0.add(bVar);
    }

    void p(float f) {
        if (this.s0 != f) {
            this.s0 = f;
            invalidateSelf();
        }
    }

    public boolean s(boolean z, boolean z2, boolean z3) {
        return t(z, z2, z3 && this.Y.a(this.q.getContentResolver()) > 0.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.u0 = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.t0.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return s(z, z2, true);
    }

    public void start() {
        t(true, true, false);
    }

    public void stop() {
        t(false, true, false);
    }

    boolean t(boolean z, boolean z2, boolean z3) {
        n();
        if (!isVisible() && !z) {
            return false;
        }
        ValueAnimator valueAnimator = z ? this.Z : this.k0;
        ValueAnimator valueAnimator2 = z ? this.k0 : this.Z;
        if (!z3) {
            if (valueAnimator2.isRunning()) {
                d(valueAnimator2);
            }
            if (valueAnimator.isRunning()) {
                valueAnimator.end();
            } else {
                g(valueAnimator);
            }
            return super.setVisible(z, false);
        }
        if (valueAnimator.isRunning()) {
            return false;
        }
        boolean z4 = !z || super.setVisible(z, false);
        if (!(z ? this.X.d() : this.X.c())) {
            g(valueAnimator);
            return z4;
        }
        if (z2 || !valueAnimator.isPaused()) {
            valueAnimator.start();
            return z4;
        }
        valueAnimator.resume();
        return z4;
    }

    public boolean u(ax.p389y2.b bVar) {
        List<ax.p389y2.b> list = this.p0;
        if (list == null || !list.contains(bVar)) {
            return false;
        }
        this.p0.remove(bVar);
        if (!this.p0.isEmpty()) {
            return true;
        }
        this.p0 = null;
        return true;
    }
}
