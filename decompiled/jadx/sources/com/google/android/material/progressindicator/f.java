package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import ax.p202m7.w;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends b {
    public int o;
    public int p;
    public int q;
    public int r;
    public boolean s;

    public f(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.e);
    }

    public f(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, CircularProgressIndicator.z0);
    }

    public f(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(ax.R6.e.q0);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(ax.R6.e.p0);
        TypedArray typedArrayI = w.i(context, attributeSet, ax.R6.m.t1, i, i2, new int[0]);
        this.o = typedArrayI.getInt(ax.R6.m.u1, 0);
        this.p = Math.max(ax.p282r7.c.c(context, typedArrayI, ax.R6.m.y1, dimensionPixelSize), this.a * 2);
        this.q = ax.p282r7.c.c(context, typedArrayI, ax.R6.m.x1, dimensionPixelSize2);
        this.r = typedArrayI.getInt(ax.R6.m.w1, 0);
        this.s = typedArrayI.getBoolean(ax.R6.m.v1, true);
        typedArrayI.recycle();
        h();
    }
}
