package com.google.android.material.progressindicator;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import ax.p202m7.w;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b {
    public int a;
    public int b;
    public float c;
    public boolean d;
    public int[] e = new int[0];
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public float n;

    protected b(Context context, AttributeSet attributeSet, int i, int i2) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(ax.R6.e.r0);
        TypedArray typedArrayI = w.i(context, attributeSet, ax.R6.m.J, i, i2, new int[0]);
        this.a = ax.p282r7.c.c(context, typedArrayI, ax.R6.m.T, dimensionPixelSize);
        TypedValue typedValuePeekValue = typedArrayI.peekValue(ax.R6.m.S);
        if (typedValuePeekValue != null) {
            int i3 = typedValuePeekValue.type;
            if (i3 == 5) {
                this.b = Math.min(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArrayI.getResources().getDisplayMetrics()), this.a / 2);
                this.d = false;
            } else if (i3 == 6) {
                this.c = Math.min(typedValuePeekValue.getFraction(1.0f, 1.0f), 0.5f);
                this.d = true;
            }
        }
        this.g = typedArrayI.getInt(ax.R6.m.P, 0);
        this.h = typedArrayI.getInt(ax.R6.m.K, 0);
        this.i = typedArrayI.getDimensionPixelSize(ax.R6.m.N, 0);
        int iAbs = Math.abs(typedArrayI.getDimensionPixelSize(ax.R6.m.W, 0));
        this.j = Math.abs(typedArrayI.getDimensionPixelSize(ax.R6.m.X, iAbs));
        this.k = Math.abs(typedArrayI.getDimensionPixelSize(ax.R6.m.Y, iAbs));
        this.l = Math.abs(typedArrayI.getDimensionPixelSize(ax.R6.m.U, 0));
        this.m = typedArrayI.getDimensionPixelSize(ax.R6.m.V, 0);
        this.n = typedArrayI.getFloat(ax.R6.m.L, 1.0f);
        e(context, typedArrayI);
        f(context, typedArrayI);
        typedArrayI.recycle();
    }

    private void e(Context context, TypedArray typedArray) {
        int i = ax.R6.m.M;
        if (!typedArray.hasValue(i)) {
            this.e = new int[]{ax.p106g7.a.b(context, ax.p194m.a.z, -1)};
            return;
        }
        if (typedArray.peekValue(i).type != 1) {
            this.e = new int[]{typedArray.getColor(i, -1)};
            return;
        }
        int[] intArray = context.getResources().getIntArray(typedArray.getResourceId(i, -1));
        this.e = intArray;
        if (intArray.length == 0) {
            throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
        }
    }

    private void f(Context context, TypedArray typedArray) {
        int i = ax.R6.m.R;
        if (typedArray.hasValue(i)) {
            this.f = typedArray.getColor(i, -1);
            return;
        }
        this.f = this.e[0];
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.disabledAlpha});
        float f = typedArrayObtainStyledAttributes.getFloat(0, 0.2f);
        typedArrayObtainStyledAttributes.recycle();
        this.f = ax.p106g7.a.a(this.f, (int) (f * 255.0f));
    }

    public int a() {
        return this.d ? (int) (this.a * this.c) : this.b;
    }

    public boolean b(boolean z) {
        if (this.l <= 0) {
            return false;
        }
        if (z || this.k <= 0) {
            return z && this.j > 0;
        }
        return true;
    }

    public boolean c() {
        return this.h != 0;
    }

    public boolean d() {
        return this.g != 0;
    }

    public boolean g() {
        return this.d && this.c == 0.5f;
    }

    void h() {
        if (this.i < 0) {
            throw new IllegalArgumentException("indicatorTrackGapSize must be >= 0.");
        }
    }
}
