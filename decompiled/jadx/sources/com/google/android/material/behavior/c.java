package com.google.android.material.behavior;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes3.dex */
final class c extends d {
    c() {
    }

    @Override // com.google.android.material.behavior.d
    <V extends View> int a(V v, ViewGroup.MarginLayoutParams marginLayoutParams) {
        return v.getMeasuredWidth() + marginLayoutParams.rightMargin;
    }

    @Override // com.google.android.material.behavior.d
    int b() {
        return 0;
    }

    @Override // com.google.android.material.behavior.d
    int c() {
        return 0;
    }

    @Override // com.google.android.material.behavior.d
    <V extends View> ViewPropertyAnimator d(V v, int i) {
        return v.animate().translationX(i);
    }

    @Override // com.google.android.material.behavior.d
    <V extends View> void e(V v, int i) {
        v.setTranslationX(i);
    }
}
