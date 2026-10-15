package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.p234o7.i;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.c<V> {
    private static final int s0 = ax.R6.c.D;
    private static final int t0 = ax.R6.c.G;
    private static final int u0 = ax.R6.c.M;
    private int X;
    private int Y;
    private TimeInterpolator Z;
    private TimeInterpolator k0;
    private int l0;
    private AccessibilityManager m0;
    private AccessibilityManager.TouchExplorationStateChangeListener n0;
    private boolean o0;
    private int p0;
    private final LinkedHashSet<c> q;
    private int q0;
    private ViewPropertyAnimator r0;

    class a implements View.OnAttachStateChangeListener {
        a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (HideBottomViewOnScrollBehavior.this.n0 == null || HideBottomViewOnScrollBehavior.this.m0 == null) {
                return;
            }
            HideBottomViewOnScrollBehavior.this.m0.removeTouchExplorationStateChangeListener(HideBottomViewOnScrollBehavior.this.n0);
            HideBottomViewOnScrollBehavior.this.n0 = null;
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.r0 = null;
        }
    }

    public interface c {
        void a(View view, int i);
    }

    public HideBottomViewOnScrollBehavior() {
        this.q = new LinkedHashSet<>();
        this.l0 = 0;
        this.o0 = true;
        this.p0 = 2;
        this.q0 = 0;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = new LinkedHashSet<>();
        this.l0 = 0;
        this.o0 = true;
        this.p0 = 2;
        this.q0 = 0;
    }

    public static /* synthetic */ void I(HideBottomViewOnScrollBehavior hideBottomViewOnScrollBehavior, View view, boolean z) {
        if (!z) {
            hideBottomViewOnScrollBehavior.getClass();
        } else if (hideBottomViewOnScrollBehavior.P()) {
            hideBottomViewOnScrollBehavior.U(view);
        }
    }

    private void N(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.r0 = v.animate().translationY(i).setInterpolator(timeInterpolator).setDuration(j).setListener(new b());
    }

    private void O(final V v) {
        if (this.m0 == null) {
            this.m0 = (AccessibilityManager) ax.Q.b.f(v.getContext(), AccessibilityManager.class);
        }
        if (this.m0 == null || this.n0 != null) {
            return;
        }
        AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: ax.V6.a
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                HideBottomViewOnScrollBehavior.I(this.a, v, z);
            }
        };
        this.n0 = touchExplorationStateChangeListener;
        this.m0.addTouchExplorationStateChangeListener(touchExplorationStateChangeListener);
        v.addOnAttachStateChangeListener(new a());
    }

    private void W(V v, int i) {
        this.p0 = i;
        Iterator<c> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().a(v, this.p0);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean E(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }

    public boolean P() {
        return this.p0 == 1;
    }

    public boolean Q() {
        return this.p0 == 2;
    }

    public void R(V v, int i) {
        this.q0 = i;
        if (this.p0 == 1) {
            v.setTranslationY(this.l0 + i);
        }
    }

    public void S(V v) {
        T(v, true);
    }

    public void T(V v, boolean z) {
        AccessibilityManager accessibilityManager;
        if (P()) {
            return;
        }
        if (this.o0 && (accessibilityManager = this.m0) != null && accessibilityManager.isTouchExplorationEnabled()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.r0;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        W(v, 1);
        int i = this.l0 + this.q0;
        if (z) {
            N(v, i, this.Y, this.k0);
        } else {
            v.setTranslationY(i);
        }
    }

    public void U(V v) {
        V(v, true);
    }

    public void V(V v, boolean z) {
        if (Q()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.r0;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        W(v, 2);
        if (z) {
            N(v, 0, this.X, this.Z);
        } else {
            v.setTranslationY(0);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.l0 = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        this.X = i.f(v.getContext(), s0, 225);
        this.Y = i.f(v.getContext(), t0, 175);
        Context context = v.getContext();
        int i2 = u0;
        this.Z = i.g(context, i2, ax.S6.a.d);
        this.k0 = i.g(v.getContext(), i2, ax.S6.a.c);
        O(v);
        return super.p(coordinatorLayout, v, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            S(v);
        } else if (i2 < 0) {
            U(v);
        }
    }
}
