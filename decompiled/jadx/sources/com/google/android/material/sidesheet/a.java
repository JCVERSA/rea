package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes3.dex */
final class a extends d {
    final SideSheetBehavior<? extends View> a;

    a(SideSheetBehavior<? extends View> sideSheetBehavior) {
        this.a = sideSheetBehavior;
    }

    @Override // com.google.android.material.sidesheet.d
    int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    @Override // com.google.android.material.sidesheet.d
    float b(int i) {
        float fE = e();
        return (i - fE) / (d() - fE);
    }

    @Override // com.google.android.material.sidesheet.d
    int c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    @Override // com.google.android.material.sidesheet.d
    int d() {
        return Math.max(0, this.a.m0() + this.a.k0());
    }

    @Override // com.google.android.material.sidesheet.d
    int e() {
        return (-this.a.d0()) - this.a.k0();
    }

    @Override // com.google.android.material.sidesheet.d
    int f() {
        return this.a.k0();
    }

    @Override // com.google.android.material.sidesheet.d
    int g() {
        return -this.a.d0();
    }

    @Override // com.google.android.material.sidesheet.d
    <V extends View> int h(V v) {
        return v.getRight() + this.a.k0();
    }

    @Override // com.google.android.material.sidesheet.d
    public int i(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getLeft();
    }

    @Override // com.google.android.material.sidesheet.d
    int j() {
        return 1;
    }

    @Override // com.google.android.material.sidesheet.d
    boolean k(float f) {
        return f > 0.0f;
    }

    @Override // com.google.android.material.sidesheet.d
    boolean l(View view) {
        return view.getRight() < (d() - e()) / 2;
    }

    @Override // com.google.android.material.sidesheet.d
    boolean m(float f, float f2) {
        return e.a(f, f2) && Math.abs(f) > ((float) this.a.o0());
    }

    @Override // com.google.android.material.sidesheet.d
    boolean n(View view, float f) {
        return Math.abs(((float) view.getLeft()) + (f * this.a.i0())) > this.a.j0();
    }

    @Override // com.google.android.material.sidesheet.d
    void o(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.leftMargin = i;
    }

    @Override // com.google.android.material.sidesheet.d
    void p(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        if (i <= this.a.n0()) {
            marginLayoutParams.leftMargin = i2;
        }
    }
}
