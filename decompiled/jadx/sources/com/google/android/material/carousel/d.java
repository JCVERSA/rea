package com.google.android.material.carousel;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
final class d {
    static float a(float f, float f2, int i) {
        return f + (Math.max(0, i - 1) * f2);
    }

    static float b(float f, float f2, int i) {
        return i > 0 ? f + (f2 / 2.0f) : f;
    }

    static e c(Context context, float f, int i, a aVar) {
        float f2;
        float fMin = Math.min(f(context) + f, aVar.f);
        float f3 = fMin / 2.0f;
        float f4 = 0.0f - f3;
        float fB = b(0.0f, aVar.b, aVar.c);
        float fJ = j(0.0f, a(fB, aVar.b, (int) Math.floor(aVar.c / 2.0f)), aVar.b, aVar.c);
        float fB2 = b(fJ, aVar.e, aVar.d);
        float fJ2 = j(fJ, a(fB2, aVar.e, (int) Math.floor(aVar.d / 2.0f)), aVar.e, aVar.d);
        float fB3 = b(fJ2, aVar.f, aVar.g);
        float fJ3 = j(fJ2, a(fB3, aVar.f, aVar.g), aVar.f, aVar.g);
        float fB4 = b(fJ3, aVar.e, aVar.d);
        float fB5 = b(j(fJ3, a(fB4, aVar.e, (int) Math.ceil(aVar.d / 2.0f)), aVar.e, aVar.d), aVar.b, aVar.c);
        float f5 = i + f3;
        float fB6 = c.b(fMin, aVar.f, f);
        float fB7 = c.b(aVar.b, aVar.f, f);
        float fB8 = c.b(aVar.e, aVar.f, f);
        e.b bVarA = new e.b(aVar.f, i).a(f4, fB6, fMin);
        int i2 = aVar.c;
        if (i2 > 0) {
            f2 = 2.0f;
            bVarA.g(fB, fB7, aVar.b, (int) Math.floor(i2 / 2.0f));
        } else {
            f2 = 2.0f;
        }
        int i3 = aVar.d;
        if (i3 > 0) {
            bVarA.g(fB2, fB8, aVar.e, (int) Math.floor(i3 / f2));
        }
        bVarA.h(fB3, 0.0f, aVar.f, aVar.g, true);
        int i4 = aVar.d;
        if (i4 > 0) {
            bVarA.g(fB4, fB8, aVar.e, (int) Math.ceil(i4 / f2));
        }
        int i5 = aVar.c;
        if (i5 > 0) {
            bVarA.g(fB5, fB7, aVar.b, (int) Math.ceil(i5 / f2));
        }
        bVarA.a(f5, fB6, fMin);
        return bVarA.i();
    }

    static e d(Context context, float f, int i, a aVar, int i2) {
        return i2 == 1 ? c(context, f, i, aVar) : e(context, f, i, aVar);
    }

    static e e(Context context, float f, int i, a aVar) {
        float fMin = Math.min(f(context) + f, aVar.f);
        float f2 = fMin / 2.0f;
        float f3 = 0.0f - f2;
        float fB = b(0.0f, aVar.f, aVar.g);
        float fJ = j(0.0f, a(fB, aVar.f, aVar.g), aVar.f, aVar.g);
        float fB2 = b(fJ, aVar.e, aVar.d);
        float fB3 = b(j(fJ, fB2, aVar.e, aVar.d), aVar.b, aVar.c);
        float f4 = i + f2;
        float fB4 = c.b(fMin, aVar.f, f);
        float fB5 = c.b(aVar.b, aVar.f, f);
        float fB6 = c.b(aVar.e, aVar.f, f);
        e.b bVarH = new e.b(aVar.f, i).a(f3, fB4, fMin).h(fB, 0.0f, aVar.f, aVar.g, true);
        if (aVar.d > 0) {
            bVarH.b(fB2, fB6, aVar.e);
        }
        int i2 = aVar.c;
        if (i2 > 0) {
            bVarH.g(fB3, fB5, aVar.b, i2);
        }
        bVarH.a(f4, fB4, fMin);
        return bVarH.i();
    }

    static float f(Context context) {
        return context.getResources().getDimension(ax.R6.e.t);
    }

    static float g(Context context) {
        return context.getResources().getDimension(ax.R6.e.u);
    }

    static float h(Context context) {
        return context.getResources().getDimension(ax.R6.e.v);
    }

    static int i(int[] iArr) {
        int i = Integer.MIN_VALUE;
        for (int i2 : iArr) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }

    static float j(float f, float f2, float f3, int i) {
        return i > 0 ? f2 + (f3 / 2.0f) : f;
    }
}
