package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import ax.C7.a;
import ax.Q.b;
import ax.R6.c;
import ax.R6.e;
import ax.R6.l;
import ax.R6.m;
import ax.p202m7.w;
import ax.p346v7.h;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialDivider extends View {
    private static final int o0 = l.D;
    private int k0;
    private int l0;
    private int m0;
    private int n0;
    private final h q;

    public MaterialDivider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialDivider(Context context, AttributeSet attributeSet, int i) {
        int i2 = o0;
        super(a.d(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.q = new h();
        TypedArray typedArrayI = w.i(context2, attributeSet, m.L3, i, i2, new int[0]);
        this.k0 = typedArrayI.getDimensionPixelSize(m.P3, getResources().getDimensionPixelSize(e.F));
        this.m0 = typedArrayI.getDimensionPixelOffset(m.O3, 0);
        this.n0 = typedArrayI.getDimensionPixelOffset(m.N3, 0);
        setDividerColor(ax.p282r7.c.a(context2, typedArrayI, m.M3).getDefaultColor());
        typedArrayI.recycle();
    }

    public int getDividerColor() {
        return this.l0;
    }

    public int getDividerInsetEnd() {
        return this.n0;
    }

    public int getDividerInsetStart() {
        return this.m0;
    }

    public int getDividerThickness() {
        return this.k0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        int i;
        super.onDraw(canvas);
        boolean z = getLayoutDirection() == 1;
        int i2 = z ? this.n0 : this.m0;
        if (z) {
            width = getWidth();
            i = this.m0;
        } else {
            width = getWidth();
            i = this.n0;
        }
        this.q.setBounds(i2, 0, width - i, getBottom() - getTop());
        this.q.draw(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int measuredHeight = getMeasuredHeight();
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int i3 = this.k0;
            if (i3 > 0 && measuredHeight != i3) {
                measuredHeight = i3;
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
    }

    public void setDividerColor(int i) {
        if (this.l0 != i) {
            this.l0 = i;
            this.q.g0(ColorStateList.valueOf(i));
            invalidate();
        }
    }

    public void setDividerColorResource(int i) {
        setDividerColor(b.c(getContext(), i));
    }

    public void setDividerInsetEnd(int i) {
        this.n0 = i;
    }

    public void setDividerInsetEndResource(int i) {
        setDividerInsetEnd(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerInsetStart(int i) {
        this.m0 = i;
    }

    public void setDividerInsetStartResource(int i) {
        setDividerInsetStart(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerThickness(int i) {
        if (this.k0 != i) {
            this.k0 = i;
            requestLayout();
        }
    }

    public void setDividerThicknessResource(int i) {
        setDividerThickness(getContext().getResources().getDimensionPixelSize(i));
    }
}
