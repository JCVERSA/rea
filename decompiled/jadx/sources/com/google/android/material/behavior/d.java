package com.google.android.material.behavior;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes3.dex */
abstract class d {
    d() {
    }

    abstract <V extends View> int a(V v, ViewGroup.MarginLayoutParams marginLayoutParams);

    abstract int b();

    abstract int c();

    abstract <V extends View> ViewPropertyAnimator d(V v, int i);

    abstract <V extends View> void e(V v, int i);
}
