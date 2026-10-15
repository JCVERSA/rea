package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {
    private int q;

    class a implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ int X;
        final /* synthetic */ ax.p186l7.a Y;
        final /* synthetic */ View q;

        a(View view, int i, ax.p186l7.a aVar) {
            this.q = view;
            this.X = i;
            this.Y = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.q.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.q == this.X) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                ax.p186l7.a aVar = this.Y;
                expandableBehavior.L((View) aVar, this.q, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.q = 0;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = 0;
    }

    private boolean J(boolean z) {
        if (!z) {
            return this.q == 1;
        }
        int i = this.q;
        return i == 0 || i == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected ax.p186l7.a K(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listR = coordinatorLayout.r(view);
        int size = listR.size();
        for (int i = 0; i < size; i++) {
            View view2 = listR.get(i);
            if (i(coordinatorLayout, view, view2)) {
                return (ax.p186l7.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean L(View view, View view2, boolean z, boolean z2);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public abstract boolean i(CoordinatorLayout coordinatorLayout, View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, View view2) {
        ax.p186l7.a aVar = (ax.p186l7.a) view2;
        if (!J(aVar.a())) {
            return false;
        }
        this.q = aVar.a() ? 1 : 2;
        return L((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, View view, int i) {
        ax.p186l7.a aVarK;
        if (view.isLaidOut() || (aVarK = K(coordinatorLayout, view)) == null || !J(aVarK.a())) {
            return false;
        }
        int i2 = aVarK.a() ? 1 : 2;
        this.q = i2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i2, aVarK));
        return false;
    }
}
