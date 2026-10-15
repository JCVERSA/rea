package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import ax.p202m7.w;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends b {
    public int o;
    public int p;
    boolean q;
    public int r;
    public Integer s;
    public int t;
    public float u;
    public boolean v;
    public boolean w;

    public o(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.q);
    }

    public o(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, LinearProgressIndicator.z0);
    }

    public o(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayI = w.i(context, attributeSet, ax.R6.m.X1, ax.R6.c.q, LinearProgressIndicator.z0, new int[0]);
        this.o = typedArrayI.getInt(ax.R6.m.Y1, 1);
        this.p = typedArrayI.getInt(ax.R6.m.Z1, 0);
        this.r = Math.min(typedArrayI.getDimensionPixelSize(ax.R6.m.c2, 0), this.a);
        int i3 = ax.R6.m.b2;
        if (typedArrayI.hasValue(i3)) {
            this.s = Integer.valueOf(typedArrayI.getDimensionPixelSize(i3, 0));
        }
        TypedValue typedValuePeekValue = typedArrayI.peekValue(ax.R6.m.a2);
        if (typedValuePeekValue != null) {
            int i4 = typedValuePeekValue.type;
            if (i4 == 5) {
                this.t = Math.min(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArrayI.getResources().getDisplayMetrics()), this.a / 2);
                this.v = false;
                this.w = true;
            } else if (i4 == 6) {
                this.u = Math.min(typedValuePeekValue.getFraction(1.0f, 1.0f), 0.5f);
                this.v = true;
                this.w = true;
            }
        }
        typedArrayI.recycle();
        h();
        this.q = this.p == 1;
    }

    @Override // com.google.android.material.progressindicator.b
    public boolean g() {
        return super.g() && i() == a();
    }

    @Override // com.google.android.material.progressindicator.b
    void h() {
        super.h();
        if (this.r < 0) {
            throw new IllegalArgumentException("Stop indicator size must be >= 0.");
        }
        if (this.o == 0) {
            if ((a() > 0 || (this.w && i() > 0)) && this.i == 0) {
                throw new IllegalArgumentException("Rounded corners without gap are not supported in contiguous indeterminate animation.");
            }
            if (this.e.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }

    public int i() {
        if (this.w) {
            return this.v ? (int) (this.a * this.u) : this.t;
        }
        return a();
    }
}
