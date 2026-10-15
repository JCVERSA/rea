package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends c {
    private static final int[] d = {1};
    private static final int[] e = {1, 0};
    private int c = 0;

    @Override // com.google.android.material.carousel.c
    public e g(ax.p026b7.a aVar, View view) {
        boolean z;
        int iB = aVar.b();
        if (aVar.g()) {
            iB = aVar.a();
        }
        RecyclerView.q qVar = (RecyclerView.q) view.getLayoutParams();
        float f = ((ViewGroup.MarginLayoutParams) qVar).topMargin + ((ViewGroup.MarginLayoutParams) qVar).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (aVar.g()) {
            f = ((ViewGroup.MarginLayoutParams) qVar).leftMargin + ((ViewGroup.MarginLayoutParams) qVar).rightMargin;
            measuredHeight = view.getMeasuredWidth();
        }
        float fD = d() + f;
        float fMax = Math.max(c() + f, fD);
        float f2 = iB;
        float fMin = Math.min(measuredHeight + f, f2);
        float fA = ax.W.a.a((measuredHeight / 3.0f) + f, fD + f, fMax + f);
        float f3 = (fMin + fA) / 2.0f;
        int[] iArrA = d;
        float f4 = 2.0f * fD;
        if (f2 <= f4) {
            iArrA = new int[]{0};
        }
        int[] iArrA2 = e;
        if (aVar.e() == 1) {
            iArrA = c.a(iArrA);
            iArrA2 = c.a(iArrA2);
        }
        int iMax = (int) Math.max(1.0d, Math.floor(((f2 - (d.i(iArrA2) * f3)) - (d.i(iArrA) * fMax)) / fMin));
        int iCeil = (int) Math.ceil(f2 / fMin);
        int i = (iCeil - iMax) + 1;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = iCeil - i2;
        }
        a aVarC = a.c(f2, fA, fD, fMax, iArrA, f3, iArrA2, fMin, iArr);
        this.c = aVarC.e();
        boolean zI = i(aVarC, aVar.f());
        int i3 = aVarC.d;
        if (i3 == 0 && aVarC.c == 0 && f2 > f4) {
            aVarC.c = 1;
            z = true;
        } else {
            z = zI;
        }
        if (z) {
            aVarC = a.c(f2, fA, fD, fMax, new int[]{aVarC.c}, f3, new int[]{i3}, fMin, new int[]{aVarC.g});
        }
        return d.d(view.getContext(), f, iB, aVarC, aVar.e());
    }

    @Override // com.google.android.material.carousel.c
    public boolean h(ax.p026b7.a aVar, int i) {
        if (i >= this.c || aVar.f() < this.c) {
            return i >= this.c && aVar.f() < this.c;
        }
        return true;
    }

    boolean i(a aVar, int i) {
        int iE = aVar.e() - i;
        boolean z = iE > 0 && (aVar.c > 0 || aVar.d > 1);
        while (iE > 0) {
            int i2 = aVar.c;
            if (i2 > 0) {
                aVar.c = i2 - 1;
            } else {
                int i3 = aVar.d;
                if (i3 > 1) {
                    aVar.d = i3 - 1;
                }
            }
            iE--;
        }
        return z;
    }
}
