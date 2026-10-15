package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.l;
import ax.p202m7.z;
import ax.p234o7.i;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    private Integer d1;
    private final ax.p346v7.h e1;
    private Animator f1;
    private Animator g1;
    private int h1;
    private int i1;
    private int j1;
    private final int k1;
    private int l1;
    private int m1;
    private final boolean n1;
    private boolean o1;
    private int p1;
    private ArrayList<g> q1;
    private int r1;
    private boolean s1;
    private boolean t1;
    private Behavior u1;
    private int v1;
    private int w1;
    private int x1;
    AnimatorListenerAdapter y1;
    private static final int z1 = l.r;
    private static final int A1 = ax.R6.c.D;
    private static final int B1 = ax.R6.c.M;

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect v0;
        private WeakReference<BottomAppBar> w0;
        private int x0;
        private final View.OnLayoutChangeListener y0;

        class a implements View.OnLayoutChangeListener {
            a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.w0.get();
                if (bottomAppBar == null || !((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton))) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                int height = view.getHeight();
                if (view instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                    floatingActionButton.h(Behavior.this.v0);
                    int iHeight = Behavior.this.v0.height();
                    bottomAppBar.H0(iHeight);
                    bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().r().a(new RectF(Behavior.this.v0)));
                    height = iHeight;
                }
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
                if (Behavior.this.x0 == 0) {
                    if (bottomAppBar.j1 == 1) {
                        ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(ax.R6.e.Z) - ((view.getMeasuredHeight() - height) / 2));
                    }
                    ((ViewGroup.MarginLayoutParams) fVar).leftMargin = bottomAppBar.getLeftInset();
                    ((ViewGroup.MarginLayoutParams) fVar).rightMargin = bottomAppBar.getRightInset();
                    if (z.h(view)) {
                        ((ViewGroup.MarginLayoutParams) fVar).leftMargin += bottomAppBar.k1;
                    } else {
                        ((ViewGroup.MarginLayoutParams) fVar).rightMargin += bottomAppBar.k1;
                    }
                }
                bottomAppBar.F0();
            }
        }

        public Behavior() {
            this.y0 = new a();
            this.v0 = new Rect();
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.y0 = new a();
            this.v0 = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
        public boolean p(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            this.w0 = new WeakReference<>(bottomAppBar);
            View viewW0 = bottomAppBar.w0();
            if (viewW0 != null && !viewW0.isLaidOut()) {
                BottomAppBar.K0(bottomAppBar, viewW0);
                this.x0 = ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) viewW0.getLayoutParams())).bottomMargin;
                if (viewW0 instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) viewW0;
                    if (bottomAppBar.j1 == 0 && bottomAppBar.n1) {
                        floatingActionButton.setElevation(0.0f);
                        floatingActionButton.setCompatElevation(0.0f);
                    }
                    if (floatingActionButton.getShowMotionSpec() == null) {
                        floatingActionButton.setShowMotionSpecResource(ax.R6.b.b);
                    }
                    if (floatingActionButton.getHideMotionSpec() == null) {
                        floatingActionButton.setHideMotionSpecResource(ax.R6.b.a);
                    }
                    bottomAppBar.o0(floatingActionButton);
                }
                viewW0.addOnLayoutChangeListener(this.y0);
                bottomAppBar.F0();
            }
            coordinatorLayout.I(bottomAppBar, i);
            return super.p(coordinatorLayout, bottomAppBar, i);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] */
        public boolean E(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i, int i2) {
            return bottomAppBar.getHideOnScroll() && super.E(coordinatorLayout, bottomAppBar, view, view2, i, i2);
        }
    }

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.t0();
            BottomAppBar.this.f1 = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.u0();
        }
    }

    class b extends FloatingActionButton.b {
        final /* synthetic */ int a;

        class a extends FloatingActionButton.b {
            a() {
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
            public void b(FloatingActionButton floatingActionButton) {
                BottomAppBar.this.t0();
            }
        }

        b(int i) {
            this.a = i;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
        public void a(FloatingActionButton floatingActionButton) {
            floatingActionButton.setTranslationX(BottomAppBar.this.y0(this.a));
            floatingActionButton.q(new a());
        }
    }

    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.t0();
            BottomAppBar.this.s1 = false;
            BottomAppBar.this.g1 = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.u0();
        }
    }

    class d extends AnimatorListenerAdapter {
        public boolean a;
        final /* synthetic */ ActionMenuView b;
        final /* synthetic */ int c;
        final /* synthetic */ boolean d;

        d(ActionMenuView actionMenuView, int i, boolean z) {
            this.b = actionMenuView;
            this.c = i;
            this.d = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                return;
            }
            boolean z = BottomAppBar.this.r1 != 0;
            BottomAppBar bottomAppBar = BottomAppBar.this;
            bottomAppBar.D0(bottomAppBar.r1);
            BottomAppBar.this.J0(this.b, this.c, this.d, z);
        }
    }

    class e implements Runnable {
        final /* synthetic */ int X;
        final /* synthetic */ boolean Y;
        final /* synthetic */ ActionMenuView q;

        e(ActionMenuView actionMenuView, int i, boolean z) {
            this.q = actionMenuView;
            this.X = i;
            this.Y = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionMenuView actionMenuView = this.q;
            actionMenuView.setTranslationX(BottomAppBar.this.x0(actionMenuView, this.X, this.Y));
        }
    }

    class f extends AnimatorListenerAdapter {
        f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.y1.onAnimationStart(animator);
            FloatingActionButton floatingActionButtonV0 = BottomAppBar.this.v0();
            if (floatingActionButtonV0 != null) {
                floatingActionButtonV0.setTranslationX(BottomAppBar.this.getFabTranslationX());
            }
        }
    }

    interface g {
        void a(BottomAppBar bottomAppBar);

        void b(BottomAppBar bottomAppBar);
    }

    static class h extends ax.p163k0.a {
        public static final Parcelable.Creator<h> CREATOR = new a();
        int Y;
        boolean Z;

        class a implements Parcelable.ClassLoaderCreator<h> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public h createFromParcel(Parcel parcel) {
                return new h(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public h createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new h(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public h[] newArray(int i) {
                return new h[i];
            }
        }

        public h(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.Y = parcel.readInt();
            this.Z = parcel.readInt() != 0;
        }

        public h(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.Y);
            parcel.writeInt(this.Z ? 1 : 0);
        }
    }

    private void A0(int i, boolean z) {
        if (!isLaidOut()) {
            this.s1 = false;
            D0(this.r1);
            return;
        }
        Animator animator = this.g1;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (!z0()) {
            i = 0;
            z = false;
        }
        s0(i, z, arrayList);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.g1 = animatorSet;
        animatorSet.addListener(new c());
        this.g1.start();
    }

    private void B0(int i) {
        if (this.h1 == i || !isLaidOut()) {
            return;
        }
        Animator animator = this.f1;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.i1 == 1) {
            r0(i, arrayList);
        } else {
            q0(i, arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        animatorSet.setInterpolator(i.g(getContext(), B1, ax.S6.a.a));
        this.f1 = animatorSet;
        animatorSet.addListener(new a());
        this.f1.start();
    }

    private Drawable C0(Drawable drawable) {
        if (drawable == null || this.d1 == null) {
            return drawable;
        }
        Drawable drawableR = ax.U.a.r(drawable.mutate());
        drawableR.setTint(this.d1.intValue());
        return drawableR;
    }

    private void E0() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.g1 != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        if (z0()) {
            I0(actionMenuView, this.h1, this.t1);
        } else {
            I0(actionMenuView, 0, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0() {
        getTopEdgeTreatment().r(getFabTranslationX());
        this.e1.h0((this.t1 && z0() && this.j1 == 1) ? 1.0f : 0.0f);
        View viewW0 = w0();
        if (viewW0 != null) {
            viewW0.setTranslationY(getFabTranslationY());
            viewW0.setTranslationX(getFabTranslationX());
        }
    }

    private void I0(ActionMenuView actionMenuView, int i, boolean z) {
        J0(actionMenuView, i, z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0(ActionMenuView actionMenuView, int i, boolean z, boolean z2) {
        e eVar = new e(actionMenuView, i, z);
        if (z2) {
            actionMenuView.post(eVar);
        } else {
            eVar.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void K0(BottomAppBar bottomAppBar, View view) {
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        fVar.d = 17;
        int i = bottomAppBar.j1;
        if (i == 1) {
            fVar.d = 17 | 48;
        }
        if (i == 0) {
            fVar.d |= 80;
        }
    }

    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.v1;
    }

    private int getFabAlignmentAnimationDuration() {
        return i.f(getContext(), A1, 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return y0(this.h1);
    }

    private float getFabTranslationY() {
        if (this.j1 == 1) {
            return -getTopEdgeTreatment().d();
        }
        View viewW0 = w0();
        return viewW0 != null ? (-((getMeasuredHeight() + getBottomInset()) - viewW0.getMeasuredHeight())) / 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.x1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.w1;
    }

    private com.google.android.material.bottomappbar.a getTopEdgeTreatment() {
        return (com.google.android.material.bottomappbar.a) this.e1.I().p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o0(FloatingActionButton floatingActionButton) {
        floatingActionButton.e(this.y1);
        floatingActionButton.f(new f());
        floatingActionButton.g(null);
    }

    private void p0() {
        Animator animator = this.g1;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.f1;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    private void r0(int i, List<Animator> list) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(v0(), "translationX", y0(i));
        objectAnimatorOfFloat.setDuration(getFabAlignmentAnimationDuration());
        list.add(objectAnimatorOfFloat);
    }

    private void s0(int i, boolean z, List<Animator> list) {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        float fabAlignmentAnimationDuration = getFabAlignmentAnimationDuration();
        Animator animatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        animatorOfFloat.setDuration((long) (0.8f * fabAlignmentAnimationDuration));
        if (Math.abs(actionMenuView.getTranslationX() - x0(actionMenuView, i, z)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(animatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            objectAnimatorOfFloat.setDuration((long) (fabAlignmentAnimationDuration * 0.2f));
            objectAnimatorOfFloat.addListener(new d(actionMenuView, i, z));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorOfFloat, animatorOfFloat);
            list.add(animatorSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t0() {
        ArrayList<g> arrayList;
        int i = this.p1 - 1;
        this.p1 = i;
        if (i != 0 || (arrayList = this.q1) == null) {
            return;
        }
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            g gVar = arrayList.get(i2);
            i2++;
            gVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u0() {
        ArrayList<g> arrayList;
        int i = this.p1;
        this.p1 = i + 1;
        if (i != 0 || (arrayList = this.q1) == null) {
            return;
        }
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            g gVar = arrayList.get(i2);
            i2++;
            gVar.a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FloatingActionButton v0() {
        View viewW0 = w0();
        if (viewW0 instanceof FloatingActionButton) {
            return (FloatingActionButton) viewW0;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View w0() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).s(this)) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float y0(int i) {
        boolean zH = z.h(this);
        if (i != 1) {
            return 0.0f;
        }
        View viewW0 = w0();
        return ((getMeasuredWidth() / 2) - ((zH ? this.x1 : this.w1) + ((this.l1 == -1 || viewW0 == null) ? this.k1 : (viewW0.getMeasuredWidth() / 2) + this.l1))) * (zH ? -1 : 1);
    }

    private boolean z0() {
        FloatingActionButton floatingActionButtonV0 = v0();
        return floatingActionButtonV0 != null && floatingActionButtonV0.n();
    }

    public void D0(int i) {
        if (i != 0) {
            this.r1 = 0;
            getMenu().clear();
            x(i);
        }
    }

    public void G0(int i, int i2) {
        this.r1 = i2;
        this.s1 = true;
        A0(i, this.t1);
        B0(i);
        this.h1 = i;
    }

    boolean H0(int i) {
        float f2 = i;
        if (f2 == getTopEdgeTreatment().k()) {
            return false;
        }
        getTopEdgeTreatment().q(f2);
        this.e1.invalidateSelf();
        return true;
    }

    public ColorStateList getBackgroundTint() {
        return this.e1.M();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.u1 == null) {
            this.u1 = new Behavior();
        }
        return this.u1;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().d();
    }

    public int getFabAlignmentMode() {
        return this.h1;
    }

    public int getFabAlignmentModeEndMargin() {
        return this.l1;
    }

    public int getFabAnchorMode() {
        return this.j1;
    }

    public int getFabAnimationMode() {
        return this.i1;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().g();
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().j();
    }

    public boolean getHideOnScroll() {
        return this.o1;
    }

    public int getMenuAlignmentMode() {
        return this.m1;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ax.p346v7.i.f(this, this.e1);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            p0();
            F0();
            final View viewW0 = w0();
            if (viewW0 != null && viewW0.isLaidOut()) {
                viewW0.post(new Runnable() { // from class: ax.W6.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        viewW0.requestLayout();
                    }
                });
            }
        }
        E0();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof h)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h hVar = (h) parcelable;
        super.onRestoreInstanceState(hVar.a());
        this.h1 = hVar.Y;
        this.t1 = hVar.Z;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected Parcelable onSaveInstanceState() {
        h hVar = new h(super.onSaveInstanceState());
        hVar.Y = this.h1;
        hVar.Z = this.t1;
        return hVar;
    }

    protected void q0(int i, List<Animator> list) {
        FloatingActionButton floatingActionButtonV0 = v0();
        if (floatingActionButtonV0 == null || floatingActionButtonV0.m()) {
            return;
        }
        u0();
        floatingActionButtonV0.k(new b(i));
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        this.e1.setTintList(colorStateList);
    }

    public void setCradleVerticalOffset(float f2) {
        if (f2 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().l(f2);
            this.e1.invalidateSelf();
            F0();
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        this.e1.f0(f2);
        getBehavior().R(this, this.e1.H() - this.e1.G());
    }

    public void setFabAlignmentMode(int i) {
        G0(i, 0);
    }

    public void setFabAlignmentModeEndMargin(int i) {
        if (this.l1 != i) {
            this.l1 = i;
            F0();
        }
    }

    public void setFabAnchorMode(int i) {
        this.j1 = i;
        F0();
        View viewW0 = w0();
        if (viewW0 != null) {
            K0(this, viewW0);
            viewW0.requestLayout();
            this.e1.invalidateSelf();
        }
    }

    public void setFabAnimationMode(int i) {
        this.i1 = i;
    }

    void setFabCornerSize(float f2) {
        if (f2 != getTopEdgeTreatment().e()) {
            getTopEdgeTreatment().m(f2);
            this.e1.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f2) {
        if (f2 != getFabCradleMargin()) {
            getTopEdgeTreatment().n(f2);
            this.e1.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        if (f2 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().o(f2);
            this.e1.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.o1 = z;
    }

    public void setMenuAlignmentMode(int i) {
        if (this.m1 != i) {
            this.m1 = i;
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                I0(actionMenuView, this.h1, z0());
            }
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        super.setNavigationIcon(C0(drawable));
    }

    public void setNavigationIconTint(int i) {
        this.d1 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    protected int x0(ActionMenuView actionMenuView, int i, boolean z) {
        int dimensionPixelOffset = 0;
        if (this.m1 != 1 && (i != 1 || !z)) {
            return 0;
        }
        boolean zH = z.h(this);
        int measuredWidth = zH ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.g) && (((Toolbar.g) childAt.getLayoutParams()).a & 8388615) == 8388611) {
                measuredWidth = zH ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        int right = zH ? actionMenuView.getRight() : actionMenuView.getLeft();
        int i3 = zH ? this.w1 : -this.x1;
        if (getNavigationIcon() == null) {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(ax.R6.e.r);
            if (!zH) {
                dimensionPixelOffset = -dimensionPixelOffset;
            }
        }
        return measuredWidth - ((right + i3) + dimensionPixelOffset);
    }
}
