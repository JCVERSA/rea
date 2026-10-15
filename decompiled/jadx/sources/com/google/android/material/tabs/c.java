package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import ax.p202m7.z;

/* JADX INFO: loaded from: classes3.dex */
class c {
    c() {
    }

    static RectF a(TabLayout tabLayout, View view) {
        if (view == null) {
            return new RectF();
        }
        return (tabLayout.E() || !(view instanceof TabLayout.i)) ? new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) : b((TabLayout.i) view, 24);
    }

    static RectF b(TabLayout.i iVar, int i) {
        int contentWidth = iVar.getContentWidth();
        int contentHeight = iVar.getContentHeight();
        int iC = (int) z.c(iVar.getContext(), i);
        if (contentWidth < iC) {
            contentWidth = iC;
        }
        int left = (iVar.getLeft() + iVar.getRight()) / 2;
        int top = (iVar.getTop() + iVar.getBottom()) / 2;
        int i2 = contentWidth / 2;
        return new RectF(left - i2, top - (contentHeight / 2), i2 + left, top + (left / 2));
    }

    void c(TabLayout tabLayout, View view, Drawable drawable) {
        RectF rectFA = a(tabLayout, view);
        drawable.setBounds((int) rectFA.left, drawable.getBounds().top, (int) rectFA.right, drawable.getBounds().bottom);
    }

    void d(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        RectF rectFA = a(tabLayout, view);
        RectF rectFA2 = a(tabLayout, view2);
        drawable.setBounds(ax.S6.a.c((int) rectFA.left, (int) rectFA2.left, f), drawable.getBounds().top, ax.S6.a.c((int) rectFA.right, (int) rectFA2.right, f), drawable.getBounds().bottom);
    }
}
