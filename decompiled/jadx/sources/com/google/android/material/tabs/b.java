package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
class b extends c {
    b() {
    }

    @Override // com.google.android.material.tabs.c
    void d(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        if (f >= 0.5f) {
            view = view2;
        }
        RectF rectFA = c.a(tabLayout, view);
        float fB = f < 0.5f ? ax.S6.a.b(1.0f, 0.0f, 0.0f, 0.5f, f) : ax.S6.a.b(0.0f, 1.0f, 0.5f, 1.0f, f);
        drawable.setBounds((int) rectFA.left, drawable.getBounds().top, (int) rectFA.right, drawable.getBounds().bottom);
        drawable.setAlpha((int) (fB * 255.0f));
    }
}
