package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import ax.p202m7.w;
import com.google.android.material.progressindicator.b;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a<S extends com.google.android.material.progressindicator.b> extends ProgressBar {
    static final int y0 = ax.R6.l.E;
    private int k0;
    private boolean l0;
    private boolean m0;
    private final int n0;
    private final int o0;
    private long p0;
    S q;
    ax.p250p7.a q0;
    private boolean r0;
    private int s0;
    boolean t0;
    private final Runnable u0;
    private final Runnable v0;
    private final ax.p389y2.b w0;
    private final ax.p389y2.b x0;

    /* JADX INFO: renamed from: com.google.android.material.progressindicator.a$a, reason: collision with other inner class name */
    class RunnableC0577a implements Runnable {
        RunnableC0577a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.k();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.j();
            a.this.p0 = -1L;
        }
    }

    class c extends ax.p389y2.b {
        c() {
        }

        @Override // ax.p389y2.b
        public void b(Drawable drawable) {
            a.this.setIndeterminate(false);
            a aVar = a.this;
            aVar.p(aVar.k0, a.this.l0);
        }
    }

    class d extends ax.p389y2.b {
        d() {
        }

        @Override // ax.p389y2.b
        public void b(Drawable drawable) {
            super.b(drawable);
            if (a.this.r0) {
                return;
            }
            a aVar = a.this;
            aVar.setVisibility(aVar.s0);
        }
    }

    protected a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(ax.C7.a.d(context, attributeSet, i, y0), attributeSet, i);
        this.p0 = -1L;
        this.r0 = false;
        this.s0 = 4;
        this.u0 = new RunnableC0577a();
        this.v0 = new b();
        this.w0 = new c();
        this.x0 = new d();
        Context context2 = getContext();
        this.q = (S) i(context2, attributeSet);
        TypedArray typedArrayI = w.i(context2, attributeSet, ax.R6.m.J, i, i2, new int[0]);
        this.n0 = typedArrayI.getInt(ax.R6.m.Q, -1);
        this.o0 = Math.min(typedArrayI.getInt(ax.R6.m.O, -1), 1000);
        typedArrayI.recycle();
        this.q0 = new ax.p250p7.a();
        this.m0 = true;
    }

    private i<S> getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().y();
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ((h) getCurrentDrawable()).s(false, false, true);
        if (m()) {
            setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.o0 > 0) {
            this.p0 = SystemClock.uptimeMillis();
        }
        setVisibility(0);
    }

    private boolean m() {
        if (getProgressDrawable() == null || !getProgressDrawable().isVisible()) {
            return getIndeterminateDrawable() == null || !getIndeterminateDrawable().isVisible();
        }
        return false;
    }

    private void n() {
        o();
        if (getProgressDrawable() != null) {
            getProgressDrawable().o(this.x0);
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().o(this.x0);
        }
    }

    private void q() {
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().u(this.x0);
            getIndeterminateDrawable().x().h();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().u(this.x0);
        }
    }

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.q.h;
    }

    @Override // android.widget.ProgressBar
    public k<S> getIndeterminateDrawable() {
        return (k) super.getIndeterminateDrawable();
    }

    public int[] getIndicatorColor() {
        return this.q.e;
    }

    public int getIndicatorTrackGapSize() {
        return this.q.i;
    }

    @Override // android.widget.ProgressBar
    public g<S> getProgressDrawable() {
        return (g) super.getProgressDrawable();
    }

    public int getShowAnimationBehavior() {
        return this.q.g;
    }

    public int getTrackColor() {
        return this.q.f;
    }

    public int getTrackCornerRadius() {
        return this.q.b;
    }

    public float getTrackCornerRadiusFraction() {
        return this.q.c;
    }

    public int getTrackThickness() {
        return this.q.a;
    }

    public int getWaveAmplitude() {
        return this.q.l;
    }

    public int getWaveSpeed() {
        return this.q.m;
    }

    public int getWavelengthDeterminate() {
        return this.q.j;
    }

    public int getWavelengthIndeterminate() {
        return this.q.k;
    }

    protected void h(boolean z) {
        if (this.m0) {
            ((h) getCurrentDrawable()).s(r(), false, z);
        }
    }

    abstract S i(Context context, AttributeSet attributeSet);

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    boolean l() {
        View view = this;
        while (view.getVisibility() == 0) {
            Object parent = view.getParent();
            if (parent == null) {
                return getWindowVisibility() == 0;
            }
            if (!(parent instanceof View)) {
                return true;
            }
            view = (View) parent;
        }
        return false;
    }

    void o() {
        if (getProgressDrawable() == null || getIndeterminateDrawable() == null) {
            return;
        }
        getIndeterminateDrawable().x().d(this.w0);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        n();
        if (r()) {
            k();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.v0);
        removeCallbacks(this.u0);
        ((h) getCurrentDrawable()).j();
        q();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        try {
            int iSave = canvas.save();
            if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
                canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
            }
            getCurrentDrawable().draw(canvas);
            canvas.restoreToCount(iSave);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getCurrentDrawingDelegate().g();
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        try {
            i<S> currentDrawingDelegate = getCurrentDrawingDelegate();
            if (currentDrawingDelegate == null) {
                return;
            }
            setMeasuredDimension(currentDrawingDelegate.f() < 0 ? View.getDefaultSize(getSuggestedMinimumWidth(), i) : currentDrawingDelegate.f() + getPaddingLeft() + getPaddingRight(), currentDrawingDelegate.e() < 0 ? View.getDefaultSize(getSuggestedMinimumHeight(), i2) : currentDrawingDelegate.e() + getPaddingTop() + getPaddingBottom());
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        h(i == 0);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        h(false);
    }

    public void p(int i, boolean z) {
        if (!isIndeterminate()) {
            super.setProgress(i);
            if (getProgressDrawable() == null || z) {
                return;
            }
            getProgressDrawable().jumpToCurrentState();
            return;
        }
        if (getProgressDrawable() != null) {
            this.k0 = i;
            this.l0 = z;
            this.r0 = true;
            if (!getIndeterminateDrawable().isVisible() || this.q0.a(getContext().getContentResolver()) == 0.0f) {
                this.w0.b(getIndeterminateDrawable());
            } else {
                getIndeterminateDrawable().x().f();
            }
        }
    }

    boolean r() {
        return isAttachedToWindow() && getWindowVisibility() == 0 && l();
    }

    public void setAnimatorDurationScaleProvider(ax.p250p7.a aVar) {
        this.q0 = aVar;
        if (getProgressDrawable() != null) {
            getProgressDrawable().Y = aVar;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().Y = aVar;
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.q.h = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        try {
            if (z == isIndeterminate()) {
                return;
            }
            h hVar = (h) getCurrentDrawable();
            if (hVar != null) {
                hVar.j();
            }
            super.setIndeterminate(z);
            h hVar2 = (h) getCurrentDrawable();
            if (hVar2 != null) {
                hVar2.s(r(), false, false);
            }
            if ((hVar2 instanceof k) && r()) {
                ((k) hVar2).x().g();
            }
            this.r0 = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setIndeterminateAnimatorDurationScale(float f) {
        S s = this.q;
        if (s.n != f) {
            s.n = f;
            getIndeterminateDrawable().x().c();
        }
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable instanceof k) {
            ((h) drawable).j();
            super.setIndeterminateDrawable(drawable);
        } else {
            if (this.t0) {
                throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
            }
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{ax.p106g7.a.b(getContext(), ax.p194m.a.z, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.q.e = iArr;
        getIndeterminateDrawable().x().c();
        invalidate();
    }

    public void setIndicatorTrackGapSize(int i) {
        S s = this.q;
        if (s.i != i) {
            s.i = i;
            s.h();
            invalidate();
        }
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (isIndeterminate()) {
            return;
        }
        p(i, false);
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        if (!(drawable instanceof g)) {
            if (this.t0) {
                throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
            }
            super.setProgressDrawable(drawable);
        } else {
            g gVar = (g) drawable;
            gVar.j();
            super.setProgressDrawable(gVar);
            gVar.L(getProgress() / getMax());
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.q.g = i;
        invalidate();
    }

    public void setTrackColor(int i) {
        S s = this.q;
        if (s.f != i) {
            s.f = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i) {
        S s = this.q;
        if (s.b != i) {
            s.b = Math.min(i, s.a / 2);
            this.q.d = false;
            invalidate();
        }
    }

    public void setTrackCornerRadiusFraction(float f) {
        S s = this.q;
        if (s.c != f) {
            s.c = Math.min(f, 0.5f);
            this.q.d = true;
            invalidate();
        }
    }

    public void setTrackThickness(int i) {
        S s = this.q;
        if (s.a != i) {
            s.a = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i != 0 && i != 4 && i != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.s0 = i;
    }

    public void setWaveAmplitude(int i) {
        S s = this.q;
        if (s.l != i) {
            s.l = Math.abs(i);
            requestLayout();
        }
    }

    public void setWaveSpeed(int i) {
        this.q.m = i;
        getProgressDrawable().J(this.q.m != 0);
    }

    public void setWavelength(int i) {
        setWavelengthDeterminate(i);
        setWavelengthIndeterminate(i);
    }

    public void setWavelengthDeterminate(int i) {
        S s = this.q;
        if (s.j != i) {
            s.j = Math.abs(i);
            if (isIndeterminate()) {
                return;
            }
            requestLayout();
        }
    }

    public void setWavelengthIndeterminate(int i) {
        S s = this.q;
        if (s.k != i) {
            s.k = Math.abs(i);
            if (isIndeterminate()) {
                requestLayout();
            }
        }
    }
}
