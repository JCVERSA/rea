package com.google.android.material.snackbar;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import ax.R6.c;
import ax.R6.e;
import ax.R6.g;
import ax.p234o7.i;
import ax.p378x7.b;

/* JADX INFO: loaded from: classes3.dex */
public class SnackbarContentLayout extends LinearLayout implements b {
    private Button k0;
    private final TimeInterpolator l0;
    private int m0;
    private TextView q;

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l0 = i.g(context, c.M, ax.S6.a.b);
    }

    private static void d(View view, int i, int i2) {
        if (view.isPaddingRelative()) {
            view.setPaddingRelative(view.getPaddingStart(), i, view.getPaddingEnd(), i2);
        } else {
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), i2);
        }
    }

    private boolean e(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.q.getPaddingTop() == i2 && this.q.getPaddingBottom() == i3) {
            return z;
        }
        d(this.q, i2, i3);
        return true;
    }

    @Override // ax.p378x7.b
    public void a(int i, int i2) {
        this.q.setAlpha(0.0f);
        long j = i2;
        long j2 = i;
        this.q.animate().alpha(1.0f).setDuration(j).setInterpolator(this.l0).setStartDelay(j2).start();
        if (this.k0.getVisibility() == 0) {
            this.k0.setAlpha(0.0f);
            this.k0.animate().alpha(1.0f).setDuration(j).setInterpolator(this.l0).setStartDelay(j2).start();
        }
    }

    @Override // ax.p378x7.b
    public void b(int i, int i2) {
        this.q.setAlpha(1.0f);
        long j = i2;
        long j2 = i;
        this.q.animate().alpha(0.0f).setDuration(j).setInterpolator(this.l0).setStartDelay(j2).start();
        if (this.k0.getVisibility() == 0) {
            this.k0.setAlpha(1.0f);
            this.k0.animate().alpha(0.0f).setDuration(j).setInterpolator(this.l0).setStartDelay(j2).start();
        }
    }

    void c(float f) {
        if (f != 1.0f) {
            this.k0.setTextColor(ax.p106g7.a.k(ax.p106g7.a.d(this, c.i), this.k0.getCurrentTextColor(), f));
        }
    }

    public Button getActionView() {
        return this.k0;
    }

    public TextView getMessageView() {
        return this.q;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.q = (TextView) findViewById(g.N);
        this.k0 = (Button) findViewById(g.M);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getOrientation() == 1) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(e.f);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(e.e);
        Layout layout = this.q.getLayout();
        boolean z = layout != null && layout.getLineCount() > 1;
        if (!z || this.m0 <= 0 || this.k0.getMeasuredWidth() <= this.m0) {
            if (!z) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!e(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!e(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i, i2);
    }

    public void setMaxInlineActionWidth(int i) {
        this.m0 = i;
    }
}
