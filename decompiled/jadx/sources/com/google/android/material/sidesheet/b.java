package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes3.dex */
final class b extends d {
    final SideSheetBehavior<? extends View> a;

    b(SideSheetBehavior<? extends View> sideSheetBehavior) {
        this.a = sideSheetBehavior;
    }

    @Override // com.google.android.material.sidesheet.d
    int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.d
    float b(int i) {
        float fE = e();
        return (fE - i) / (fE - d());
    }

    @Override // com.google.android.material.sidesheet.d
    int c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.d
    int d() {
        return Math.max(0, (e() - this.a.d0()) - this.a.k0());
    }

    @Override // com.google.android.material.sidesheet.d
    int e() {
        return this.a.n0();
    }

    @Override // com.google.android.material.sidesheet.d
    int f() {
        return this.a.n0();
    }

    @Override // com.google.android.material.sidesheet.d
    int g() {
        return d();
    }

    @Override // com.google.android.material.sidesheet.d
    <V extends View> int h(V v) {
        return v.getLeft() - this.a.k0();
    }

    @Override // com.google.android.material.sidesheet.d
    public int i(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getRight();
    }

    @Override // com.google.android.material.sidesheet.d
    int j() {
        return 0;
    }

    @Override // com.google.android.material.sidesheet.d
    boolean k(float f) {
        return f < 0.0f;
    }

    @Override // com.google.android.material.sidesheet.d
    boolean l(View view) {
        return view.getLeft() > (e() + d()) / 2;
    }

    @Override // com.google.android.material.sidesheet.d
    boolean m(float f, float f2) {
        return e.a(f, f2) && Math.abs(f) > ((float) this.a.o0());
    }

    @Override // com.google.android.material.sidesheet.d
    boolean n(View view, float f) {
        return Math.abs(((float) view.getRight()) + (f * this.a.i0())) > this.a.j0();
    }

    @Override // com.google.android.material.sidesheet.d
    void o(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.rightMargin = i;
    }

    @Override // com.google.android.material.sidesheet.d
    void p(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        int iN0 = this.a.n0();
        if (i <= iN0) {
            marginLayoutParams.rightMargin = iN0 - i;
        }
    }
}
