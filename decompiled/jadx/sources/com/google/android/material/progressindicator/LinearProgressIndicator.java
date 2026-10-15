package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class LinearProgressIndicator extends a<o> {
    public static final int z0 = ax.R6.l.A;

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.q);
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, z0);
        t();
        this.t0 = true;
    }

    private void t() {
        l lVar = new l((o) this.q);
        setIndeterminateDrawable(k.w(getContext(), (o) this.q, lVar));
        setProgressDrawable(g.B(getContext(), (o) this.q, lVar));
    }

    public int getIndeterminateAnimationType() {
        return ((o) this.q).o;
    }

    public int getIndicatorDirection() {
        return ((o) this.q).p;
    }

    public int getTrackInnerCornerRadius() {
        return ((o) this.q).t;
    }

    public Integer getTrackStopIndicatorPadding() {
        return ((o) this.q).s;
    }

    public int getTrackStopIndicatorSize() {
        return ((o) this.q).r;
    }

    @Override // com.google.android.material.progressindicator.a, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        S s = this.q;
        o oVar = (o) s;
        boolean z2 = true;
        if (((o) s).p != 1 && ((getLayoutDirection() != 1 || ((o) this.q).p != 2) && (getLayoutDirection() != 0 || ((o) this.q).p != 3))) {
            z2 = false;
        }
        oVar.q = z2;
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingLeft = i - (getPaddingLeft() + getPaddingRight());
        int paddingTop = i2 - (getPaddingTop() + getPaddingBottom());
        k<o> indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingLeft, paddingTop);
        }
        g<o> progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingLeft, paddingTop);
        }
    }

    @Override // com.google.android.material.progressindicator.a
    public void p(int i, boolean z) {
        S s = this.q;
        if (s != 0 && ((o) s).o == 0 && isIndeterminate()) {
            return;
        }
        super.p(i, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.a
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public o i(Context context, AttributeSet attributeSet) {
        return new o(context, attributeSet);
    }

    public void setIndeterminateAnimationType(int i) {
        if (((o) this.q).o == i) {
            return;
        }
        if (r() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        S s = this.q;
        ((o) s).o = i;
        ((o) s).h();
        if (i == 0) {
            getIndeterminateDrawable().A(new m((o) this.q));
        } else {
            getIndeterminateDrawable().A(new n(getContext(), (o) this.q));
        }
        o();
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.a
    public void setIndicatorColor(int... iArr) {
        super.setIndicatorColor(iArr);
        ((o) this.q).h();
    }

    public void setIndicatorDirection(int i) {
        S s = this.q;
        ((o) s).p = i;
        o oVar = (o) s;
        boolean z = true;
        if (i != 1 && ((getLayoutDirection() != 1 || ((o) this.q).p != 2) && (getLayoutDirection() != 0 || i != 3))) {
            z = false;
        }
        oVar.q = z;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.a
    public void setTrackCornerRadius(int i) {
        super.setTrackCornerRadius(i);
        ((o) this.q).h();
        invalidate();
    }

    public void setTrackInnerCornerRadius(int i) {
        S s = this.q;
        if (((o) s).t != i) {
            ((o) s).t = Math.round(Math.min(i, ((o) s).a / 2.0f));
            S s2 = this.q;
            ((o) s2).v = false;
            ((o) s2).w = true;
            ((o) s2).h();
            invalidate();
        }
    }

    public void setTrackInnerCornerRadiusFraction(float f) {
        S s = this.q;
        if (((o) s).u != f) {
            ((o) s).u = Math.min(f, 0.5f);
            S s2 = this.q;
            ((o) s2).v = true;
            ((o) s2).w = true;
            ((o) s2).h();
            invalidate();
        }
    }

    public void setTrackStopIndicatorPadding(Integer num) {
        if (Objects.equals(((o) this.q).s, num)) {
            return;
        }
        ((o) this.q).s = num;
        invalidate();
    }

    public void setTrackStopIndicatorSize(int i) {
        S s = this.q;
        if (((o) s).r != i) {
            ((o) s).r = Math.min(i, ((o) s).a);
            ((o) this.q).h();
            invalidate();
        }
    }
}
