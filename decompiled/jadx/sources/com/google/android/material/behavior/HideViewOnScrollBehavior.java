package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.p234o7.i;
import com.google.android.material.behavior.HideViewOnScrollBehavior;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
public class HideViewOnScrollBehavior<V extends View> extends CoordinatorLayout.c<V> {
    private static final int u0 = ax.R6.c.D;
    private static final int v0 = ax.R6.c.G;
    private static final int w0 = ax.R6.c.M;
    private AccessibilityManager X;
    private AccessibilityManager.TouchExplorationStateChangeListener Y;
    private boolean Z;
    private final LinkedHashSet<c> k0;
    private int l0;
    private int m0;
    private TimeInterpolator n0;
    private TimeInterpolator o0;
    private int p0;
    private d q;
    private int q0;
    private int r0;
    private ViewPropertyAnimator s0;
    private boolean t0;

    class a implements View.OnAttachStateChangeListener {
        a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (HideViewOnScrollBehavior.this.Y == null || HideViewOnScrollBehavior.this.X == null) {
                return;
            }
            HideViewOnScrollBehavior.this.X.removeTouchExplorationStateChangeListener(HideViewOnScrollBehavior.this.Y);
            HideViewOnScrollBehavior.this.Y = null;
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideViewOnScrollBehavior.this.s0 = null;
        }
    }

    public interface c {
        void a(View view, int i);
    }

    public HideViewOnScrollBehavior() {
        this.Z = true;
        this.k0 = new LinkedHashSet<>();
        this.p0 = 0;
        this.q0 = 2;
        this.r0 = 0;
        this.t0 = false;
    }

    public HideViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Z = true;
        this.k0 = new LinkedHashSet<>();
        this.p0 = 0;
        this.q0 = 2;
        this.r0 = 0;
        this.t0 = false;
    }

    public static /* synthetic */ void I(HideViewOnScrollBehavior hideViewOnScrollBehavior, View view, boolean z) {
        if (hideViewOnScrollBehavior.Z && z && hideViewOnScrollBehavior.S()) {
            hideViewOnScrollBehavior.V(view);
        }
    }

    private void N(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.s0 = this.q.d(v, i).setInterpolator(timeInterpolator).setDuration(j).setListener(new b());
    }

    private void O(final V v) {
        if (this.X == null) {
            this.X = (AccessibilityManager) ax.Q.b.f(v.getContext(), AccessibilityManager.class);
        }
        if (this.X == null || this.Y != null) {
            return;
        }
        AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: ax.V6.b
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                HideViewOnScrollBehavior.I(this.a, v, z);
            }
        };
        this.Y = touchExplorationStateChangeListener;
        this.X.addTouchExplorationStateChangeListener(touchExplorationStateChangeListener);
        v.addOnAttachStateChangeListener(new a());
    }

    private boolean P(int i) {
        return i == 80 || i == 81;
    }

    private boolean Q(int i) {
        return i == 3 || i == 19;
    }

    private void T(V v, int i) {
        if (this.t0) {
            return;
        }
        int i2 = ((CoordinatorLayout.f) v.getLayoutParams()).c;
        if (P(i2)) {
            U(1);
        } else {
            U(Q(Gravity.getAbsoluteGravity(i2, i)) ? 2 : 0);
        }
    }

    private void U(int i) {
        d dVar = this.q;
        if (dVar == null || dVar.c() != i) {
            if (i == 0) {
                this.q = new com.google.android.material.behavior.c();
                return;
            }
            if (i == 1) {
                this.q = new com.google.android.material.behavior.a();
                return;
            }
            if (i == 2) {
                this.q = new com.google.android.material.behavior.b();
                return;
            }
            throw new IllegalArgumentException("Invalid view edge position value: " + i + ". Must be 0, 1 or 2.");
        }
    }

    private void Z(V v, int i) {
        this.q0 = i;
        Iterator<c> it = this.k0.iterator();
        while (it.hasNext()) {
            it.next().a(v, this.q0);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean E(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }

    public boolean R() {
        return this.q0 == 2;
    }

    public boolean S() {
        return this.q0 == 1;
    }

    public void V(V v) {
        W(v, true);
    }

    public void W(V v, boolean z) {
        if (R()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.s0;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        Z(v, 2);
        int iB = this.q.b();
        if (z) {
            N(v, iB, this.l0, this.n0);
        } else {
            this.q.e(v, iB);
        }
    }

    public void X(V v) {
        Y(v, true);
    }

    public void Y(V v, boolean z) {
        AccessibilityManager accessibilityManager;
        if (S()) {
            return;
        }
        if (this.Z && (accessibilityManager = this.X) != null && accessibilityManager.isTouchExplorationEnabled()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.s0;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        Z(v, 1);
        int i = this.p0 + this.r0;
        if (z) {
            N(v, i, this.m0, this.o0);
        } else {
            this.q.e(v, i);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, V v, int i) {
        O(v);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        T(v, i);
        this.p0 = this.q.a(v, marginLayoutParams);
        this.l0 = i.f(v.getContext(), u0, 225);
        this.m0 = i.f(v.getContext(), v0, 175);
        Context context = v.getContext();
        int i2 = w0;
        this.n0 = i.g(context, i2, ax.S6.a.d);
        this.o0 = i.g(v.getContext(), i2, ax.S6.a.c);
        return super.p(coordinatorLayout, v, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            X(v);
        } else if (i2 < 0) {
            V(v);
        }
    }
}
