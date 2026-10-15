package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.p035c0.F0;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
abstract class b extends c<View> {
    final Rect Z;
    final Rect k0;
    private int l0;
    private int m0;

    public b() {
        this.Z = new Rect();
        this.k0 = new Rect();
        this.l0 = 0;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Z = new Rect();
        this.k0 = new Rect();
        this.l0 = 0;
    }

    private static int R(int i) {
        if (i == 0) {
            return 8388659;
        }
        return i;
    }

    @Override // com.google.android.material.appbar.c
    protected void J(CoordinatorLayout coordinatorLayout, View view, int i) {
        View viewL = L(coordinatorLayout.r(view));
        if (viewL == null) {
            super.J(coordinatorLayout, view, i);
            this.l0 = 0;
            return;
        }
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        Rect rect = this.Z;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewL.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewL.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        F0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            rect.left += lastWindowInsets.j();
            rect.right -= lastWindowInsets.k();
        }
        Rect rect2 = this.k0;
        Gravity.apply(R(fVar.c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
        int iM = M(viewL);
        view.layout(rect2.left, rect2.top - iM, rect2.right, rect2.bottom - iM);
        this.l0 = rect2.top - viewL.getBottom();
    }

    abstract View L(List<View> list);

    final int M(View view) {
        if (this.m0 == 0) {
            return 0;
        }
        float fN = N(view);
        int i = this.m0;
        return ax.W.a.b((int) (fN * i), 0, i);
    }

    float N(View view) {
        return 1.0f;
    }

    public final int O() {
        return this.m0;
    }

    int P(View view) {
        return view.getMeasuredHeight();
    }

    final int Q() {
        return this.l0;
    }

    public final void S(int i) {
        this.m0 = i;
    }

    protected boolean T() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean q(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        View viewL;
        F0 lastWindowInsets;
        int i5 = view.getLayoutParams().height;
        if ((i5 != -1 && i5 != -2) || (viewL = L(coordinatorLayout.r(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (viewL.getFitsSystemWindows() && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.l() + lastWindowInsets.i();
        }
        int iP = size + P(viewL);
        int measuredHeight = viewL.getMeasuredHeight();
        if (T()) {
            view.setTranslationY(-measuredHeight);
        } else {
            view.setTranslationY(0.0f);
            iP -= measuredHeight;
        }
        coordinatorLayout.J(view, i, i2, View.MeasureSpec.makeMeasureSpec(iP, i5 == -1 ? 1073741824 : Integer.MIN_VALUE), i4);
        return true;
    }
}
