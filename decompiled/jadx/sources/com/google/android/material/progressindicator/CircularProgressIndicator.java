package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes3.dex */
public class CircularProgressIndicator extends a<f> {
    public static final int z0 = ax.R6.l.w;

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.e);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, z0);
        t();
        this.t0 = true;
    }

    private void t() {
        c cVar = new c((f) this.q);
        setIndeterminateDrawable(k.v(getContext(), (f) this.q, cVar));
        setProgressDrawable(g.A(getContext(), (f) this.q, cVar));
    }

    public int getIndeterminateAnimationType() {
        return ((f) this.q).o;
    }

    public int getIndicatorDirection() {
        return ((f) this.q).r;
    }

    public int getIndicatorInset() {
        return ((f) this.q).q;
    }

    public int getIndicatorSize() {
        return ((f) this.q).p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.a
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public f i(Context context, AttributeSet attributeSet) {
        return new f(context, attributeSet);
    }

    public void setIndeterminateAnimationType(int i) {
        if (((f) this.q).o == i) {
            return;
        }
        if (r() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        S s = this.q;
        ((f) s).o = i;
        ((f) s).h();
        getIndeterminateDrawable().A(i == 1 ? new e(getContext(), (f) this.q) : new d((f) this.q));
        o();
        invalidate();
    }

    public void setIndicatorDirection(int i) {
        ((f) this.q).r = i;
        invalidate();
    }

    public void setIndicatorInset(int i) {
        S s = this.q;
        if (((f) s).q != i) {
            ((f) s).q = i;
            invalidate();
        }
    }

    public void setIndicatorSize(int i) {
        int iMax = Math.max(i, getTrackThickness() * 2);
        S s = this.q;
        if (((f) s).p != iMax) {
            ((f) s).p = iMax;
            ((f) s).h();
            requestLayout();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.a
    public void setTrackThickness(int i) {
        super.setTrackThickness(i);
        ((f) this.q).h();
    }
}
