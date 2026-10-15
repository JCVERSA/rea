package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    private float a;
    private float b;

    enum a {
        CONTAINED,
        UNCONTAINED
    }

    static int[] a(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            iArr2[i] = iArr[i] * 2;
        }
        return iArr2;
    }

    public static float b(float f, float f2, float f3) {
        return 1.0f - ((f - f3) / (f2 - f3));
    }

    public float c() {
        return this.b;
    }

    public float d() {
        return this.a;
    }

    a e() {
        return a.CONTAINED;
    }

    void f(Context context) {
        float fH = this.a;
        if (fH <= 0.0f) {
            fH = d.h(context);
        }
        this.a = fH;
        float fG = this.b;
        if (fG <= 0.0f) {
            fG = d.g(context);
        }
        this.b = fG;
    }

    public abstract e g(ax.p026b7.a aVar, View view);

    public abstract boolean h(ax.p026b7.a aVar, int i);
}
