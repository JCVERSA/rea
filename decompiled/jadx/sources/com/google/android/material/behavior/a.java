package com.google.android.material.behavior;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes3.dex */
final class a extends d {
    a() {
    }

    @Override // com.google.android.material.behavior.d
    <V extends View> int a(V v, ViewGroup.MarginLayoutParams marginLayoutParams) {
        return v.getMeasuredHeight() + marginLayoutParams.bottomMargin;
    }

    @Override // com.google.android.material.behavior.d
    int b() {
        return 0;
    }

    @Override // com.google.android.material.behavior.d
    int c() {
        return 1;
    }

    @Override // com.google.android.material.behavior.d
    <V extends View> ViewPropertyAnimator d(V v, int i) {
        return v.animate().translationY(i);
    }

    @Override // com.google.android.material.behavior.d
    <V extends View> void e(V v, int i) {
        v.setTranslationY(i);
    }
}
