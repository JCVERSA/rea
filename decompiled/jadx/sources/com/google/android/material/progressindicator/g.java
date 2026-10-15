package com.google.android.material.progressindicator;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import com.google.android.material.progressindicator.b;
import com.google.android.material.progressindicator.g;

/* JADX INFO: loaded from: classes3.dex */
public final class g<S extends b> extends h {
    private static final ax.p323u0.i<g<?>> I0 = new a("indicatorLevel");
    private final i.a A0;
    private float B0;
    private boolean C0;
    private final ValueAnimator D0;
    private ValueAnimator E0;
    private TimeInterpolator F0;
    private TimeInterpolator G0;
    private TimeInterpolator H0;
    private i<S> x0;
    private final ax.p323u0.l y0;
    private final ax.p323u0.k z0;

    class a extends ax.p323u0.i<g<?>> {
        a(String str) {
            super(str);
        }

        @Override // ax.p323u0.i
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(g<?> gVar) {
            return gVar.E() * 10000.0f;
        }

        @Override // ax.p323u0.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(g<?> gVar, float f) {
            gVar.K(f / 10000.0f);
            gVar.G((int) f);
        }
    }

    g(Context context, final b bVar, i<S> iVar) {
        super(context, bVar);
        this.C0 = false;
        I(iVar);
        i.a aVar = new i.a();
        this.A0 = aVar;
        aVar.h = true;
        ax.p323u0.l lVar = new ax.p323u0.l();
        this.y0 = lVar;
        lVar.f(1.0f);
        lVar.h(50.0f);
        ax.p323u0.k kVar = new ax.p323u0.k(this, I0);
        this.z0 = kVar;
        kVar.q(lVar);
        ValueAnimator valueAnimator = new ValueAnimator();
        this.D0 = valueAnimator;
        valueAnimator.setDuration(1000L);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: ax.p7.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                g.v(this.a, bVar, valueAnimator2);
            }
        });
        if (bVar.b(true) && bVar.m != 0) {
            valueAnimator.start();
        }
        p(1.0f);
    }

    static g<f> A(Context context, f fVar, c cVar) {
        return new g<>(context, fVar, cVar);
    }

    static g<o> B(Context context, o oVar, l lVar) {
        return new g<>(context, oVar, lVar);
    }

    private float C(int i) {
        float f = i;
        return (f < 1000.0f || f > 9000.0f) ? 0.0f : 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float E() {
        return this.A0.b;
    }

    private void F() {
        if (this.E0 != null) {
            return;
        }
        Context context = this.q;
        int i = ax.R6.c.O;
        TimeInterpolator timeInterpolator = ax.S6.a.a;
        this.G0 = ax.p234o7.i.g(context, i, timeInterpolator);
        this.H0 = ax.p234o7.i.g(this.q, ax.R6.c.K, timeInterpolator);
        ValueAnimator valueAnimator = new ValueAnimator();
        this.E0 = valueAnimator;
        valueAnimator.setDuration(500L);
        this.E0.setFloatValues(0.0f, 1.0f);
        this.E0.setInterpolator(null);
        this.E0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: ax.p7.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                g gVar = this.a;
                gVar.A0.e = gVar.F0.getInterpolation(gVar.E0.getAnimatedFraction());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(int i) {
        if (this.X.b(true)) {
            F();
            float fC = C(i);
            if (fC == this.B0) {
                if (this.E0.isRunning()) {
                    return;
                }
                H(fC);
                return;
            }
            if (this.E0.isRunning()) {
                this.E0.cancel();
            }
            this.B0 = fC;
            if (fC == 1.0f) {
                this.F0 = this.G0;
                this.E0.start();
            } else {
                this.F0 = this.H0;
                this.E0.reverse();
            }
        }
    }

    private void H(float f) {
        this.A0.e = f;
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(float f) {
        this.A0.b = f;
        invalidateSelf();
    }

    public static /* synthetic */ void v(g gVar, b bVar, ValueAnimator valueAnimator) {
        gVar.getClass();
        if (bVar.b(true) && bVar.m != 0 && gVar.isVisible()) {
            gVar.invalidateSelf();
        }
    }

    i<S> D() {
        return this.x0;
    }

    void I(i<S> iVar) {
        this.x0 = iVar;
    }

    void J(boolean z) {
        if (z && !this.D0.isRunning()) {
            this.D0.start();
        } else {
            if (z || !this.D0.isRunning()) {
                return;
            }
            this.D0.cancel();
        }
    }

    void L(float f) {
        setLevel((int) (f * 10000.0f));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(this.v0)) {
            canvas.save();
            this.x0.h(canvas, getBounds(), h(), m(), l());
            this.A0.f = i();
            this.t0.setStyle(Paint.Style.FILL);
            this.t0.setAntiAlias(true);
            i.a aVar = this.A0;
            b bVar = this.X;
            aVar.c = bVar.e[0];
            int iA = bVar.i;
            if (iA > 0) {
                if (!(this.x0 instanceof l)) {
                    iA = (int) ((iA * ax.W.a.a(E(), 0.0f, 0.01f)) / 0.01f);
                }
                this.x0.d(canvas, this.t0, E(), 1.0f, this.X.f, getAlpha(), iA);
            } else {
                this.x0.d(canvas, this.t0, 0.0f, 1.0f, bVar.f, getAlpha(), 0);
            }
            this.x0.c(canvas, this.t0, this.A0, getAlpha());
            this.x0.b(canvas, this.t0, this.X.e[0], getAlpha());
            canvas.restore();
        }
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.x0.e();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.x0.f();
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean j() {
        return super.j();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.z0.r();
        K(getLevel() / 10000.0f);
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean l() {
        return super.l();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean m() {
        return super.m();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ void o(ax.p389y2.b bVar) {
        super.o(bVar);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        float fC = C(i);
        if (!this.C0) {
            this.z0.i(E() * 10000.0f);
            this.z0.m(i);
            return true;
        }
        this.z0.r();
        K(i / 10000.0f);
        H(fC);
        return true;
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean s(boolean z, boolean z2, boolean z3) {
        return super.s(z, z2, z3);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    @Override // com.google.android.material.progressindicator.h
    boolean t(boolean z, boolean z2, boolean z3) {
        boolean zT = super.t(z, z2, z3);
        float fA = this.Y.a(this.q.getContentResolver());
        if (fA == 0.0f) {
            this.C0 = true;
            return zT;
        }
        this.C0 = false;
        this.y0.h(50.0f / fA);
        return zT;
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean u(ax.p389y2.b bVar) {
        return super.u(bVar);
    }
}
