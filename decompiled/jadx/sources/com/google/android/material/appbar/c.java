package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes3.dex */
class c<V extends View> extends CoordinatorLayout.c<V> {
    private int X;
    private int Y;
    private d q;

    public c() {
        this.X = 0;
        this.Y = 0;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.X = 0;
        this.Y = 0;
    }

    public int I() {
        d dVar = this.q;
        if (dVar != null) {
            return dVar.b();
        }
        return 0;
    }

    protected void J(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.I(v, i);
    }

    public boolean K(int i) {
        d dVar = this.q;
        if (dVar != null) {
            return dVar.e(i);
        }
        this.X = i;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, V v, int i) {
        J(coordinatorLayout, v, i);
        if (this.q == null) {
            this.q = new d(v);
        }
        this.q.c();
        this.q.a();
        int i2 = this.X;
        if (i2 != 0) {
            this.q.e(i2);
            this.X = 0;
        }
        int i3 = this.Y;
        if (i3 == 0) {
            return true;
        }
        this.q.d(i3);
        this.Y = 0;
        return true;
    }
}
