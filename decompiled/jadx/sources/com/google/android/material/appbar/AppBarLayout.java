package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.l;
import ax.R6.m;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p035c0.D;
import ax.p035c0.F0;
import ax.p051d0.H;
import ax.p346v7.h;
import ax.p346v7.i;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.b {
    private static final int K0 = l.g;
    private final List<e> A0;
    private final LinkedHashSet<f> B0;
    private final long C0;
    private final TimeInterpolator D0;
    private int[] E0;
    private int F0;
    private Drawable G0;
    private Integer H0;
    private final float I0;
    private Behavior J0;
    private int k0;
    private int l0;
    private int m0;
    private boolean n0;
    private int o0;
    private F0 p0;
    private int q;
    private List<a> q0;
    private boolean r0;
    private boolean s0;
    private boolean t0;
    private boolean u0;
    private ColorStateList v0;
    private int w0;
    private WeakReference<View> x0;
    private ValueAnimator y0;
    private ValueAnimator.AnimatorUpdateListener z0;

    protected static class BaseBehavior<T extends AppBarLayout> extends com.google.android.material.appbar.a<T> {
        private int q0;
        private int r0;
        private ValueAnimator s0;
        private c t0;
        private WeakReference<View> u0;

        class a implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ CoordinatorLayout a;
            final /* synthetic */ AppBarLayout b;

            a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
                this.a = coordinatorLayout;
                this.b = appBarLayout;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseBehavior.this.T(this.a, this.b, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        }

        class b extends C1832a {
            final /* synthetic */ AppBarLayout d;
            final /* synthetic */ CoordinatorLayout e;

            b(AppBarLayout appBarLayout, CoordinatorLayout coordinatorLayout) {
                this.d = appBarLayout;
                this.e = coordinatorLayout;
            }

            @Override // ax.p035c0.C1832a
            public void g(View view, H h) {
                View viewJ0;
                super.g(view, h);
                h.m0(ScrollView.class.getName());
                if (this.d.getTotalScrollRange() == 0 || (viewJ0 = BaseBehavior.this.j0(this.e)) == null || !BaseBehavior.this.f0(this.d)) {
                    return;
                }
                if (BaseBehavior.this.Q() != (-this.d.getTotalScrollRange())) {
                    h.b(H.a.q);
                    h.G0(true);
                }
                if (BaseBehavior.this.Q() != 0) {
                    if (!viewJ0.canScrollVertically(-1)) {
                        h.b(H.a.r);
                        h.G0(true);
                    } else if ((-this.d.getDownNestedPreScrollRange()) != 0) {
                        h.b(H.a.r);
                        h.G0(true);
                    }
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // ax.p035c0.C1832a
            public boolean j(View view, int i, Bundle bundle) {
                if (i == 4096) {
                    this.d.setExpanded(false);
                    return true;
                }
                if (i != 8192) {
                    return super.j(view, i, bundle);
                }
                if (BaseBehavior.this.Q() != 0) {
                    View viewJ0 = BaseBehavior.this.j0(this.e);
                    if (!viewJ0.canScrollVertically(-1)) {
                        this.d.setExpanded(true);
                        return true;
                    }
                    int i2 = -this.d.getDownNestedPreScrollRange();
                    if (i2 != 0) {
                        BaseBehavior.this.u(this.e, this.d, viewJ0, 0, i2, new int[]{0, 0}, 1);
                        return true;
                    }
                }
                return false;
            }
        }

        protected static class c extends ax.p163k0.a {
            public static final Parcelable.Creator<c> CREATOR = new a();
            boolean Y;
            boolean Z;
            int k0;
            float l0;
            boolean m0;

            class a implements Parcelable.ClassLoaderCreator<c> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public c createFromParcel(Parcel parcel) {
                    return new c(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public c createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return new c(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public c[] newArray(int i) {
                    return new c[i];
                }
            }

            public c(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.Y = parcel.readByte() != 0;
                this.Z = parcel.readByte() != 0;
                this.k0 = parcel.readInt();
                this.l0 = parcel.readFloat();
                this.m0 = parcel.readByte() != 0;
            }

            public c(Parcelable parcelable) {
                super(parcelable);
            }

            @Override // ax.p163k0.a, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeByte(this.Y ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.Z ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.k0);
                parcel.writeFloat(this.l0);
                parcel.writeByte(this.m0 ? (byte) 1 : (byte) 0);
            }
        }

        public BaseBehavior() {
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        private void A0(CoordinatorLayout coordinatorLayout, T t) {
            int topInset = t.getTopInset() + t.getPaddingTop();
            int iQ = Q() - topInset;
            int iI0 = i0(t, iQ);
            if (iI0 >= 0) {
                View childAt = t.getChildAt(iI0);
                d dVar = (d) childAt.getLayoutParams();
                int iC = dVar.c();
                if ((iC & 17) == 17) {
                    int topInset2 = -childAt.getTop();
                    int minimumHeight = -childAt.getBottom();
                    if (iI0 == 0 && t.getFitsSystemWindows() && childAt.getFitsSystemWindows()) {
                        topInset2 -= t.getTopInset();
                    }
                    if (e0(iC, 2)) {
                        minimumHeight += childAt.getMinimumHeight();
                    } else if (e0(iC, 5)) {
                        int minimumHeight2 = childAt.getMinimumHeight() + minimumHeight;
                        if (iQ < minimumHeight2) {
                            topInset2 = minimumHeight2;
                        } else {
                            minimumHeight = minimumHeight2;
                        }
                    }
                    if (e0(iC, 32)) {
                        topInset2 += ((LinearLayout.LayoutParams) dVar).topMargin;
                        minimumHeight -= ((LinearLayout.LayoutParams) dVar).bottomMargin;
                    }
                    Z(coordinatorLayout, t, ax.W.a.b(b0(iQ, minimumHeight, topInset2) + topInset, -t.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private void B0(CoordinatorLayout coordinatorLayout, T t, int i, int i2, boolean z) {
            View viewH0 = h0(t, i);
            boolean zD = false;
            if (viewH0 != null) {
                int iC = ((d) viewH0.getLayoutParams()).c();
                if ((iC & 1) != 0) {
                    int minimumHeight = viewH0.getMinimumHeight();
                    if (i2 <= 0 || (iC & 12) == 0 ? !((iC & 2) == 0 || (-i) < (viewH0.getBottom() - minimumHeight) - t.getTopInset()) : (-i) >= (viewH0.getBottom() - minimumHeight) - t.getTopInset()) {
                        zD = true;
                    }
                }
            }
            if (t.p()) {
                zD = t.D(g0(coordinatorLayout));
            }
            boolean zA = t.A(zD);
            if (z || (zA && z0(coordinatorLayout, t))) {
                if (t.getBackground() != null) {
                    t.getBackground().jumpToCurrentState();
                }
                if (Build.VERSION.SDK_INT >= 23 && t.getForeground() != null) {
                    t.getForeground().jumpToCurrentState();
                }
                if (t.getStateListAnimator() != null) {
                    t.getStateListAnimator().jumpToCurrentState();
                }
            }
        }

        private void Y(CoordinatorLayout coordinatorLayout, T t) {
            if (C1839d0.N(coordinatorLayout)) {
                return;
            }
            C1839d0.l0(coordinatorLayout, new b(t, coordinatorLayout));
        }

        private void Z(CoordinatorLayout coordinatorLayout, T t, int i, float f) {
            int iAbs = Math.abs(Q() - i);
            float fAbs = Math.abs(f);
            a0(coordinatorLayout, t, i, fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / t.getHeight()) + 1.0f) * 150.0f));
        }

        private void a0(CoordinatorLayout coordinatorLayout, T t, int i, int i2) {
            int iQ = Q();
            if (iQ == i) {
                ValueAnimator valueAnimator = this.s0;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.s0.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.s0;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.s0 = valueAnimator3;
                valueAnimator3.setInterpolator(ax.S6.a.e);
                this.s0.addUpdateListener(new a(coordinatorLayout, t));
            } else {
                valueAnimator2.cancel();
            }
            this.s0.setDuration(Math.min(i2, 600));
            this.s0.setIntValues(iQ, i);
            this.s0.start();
        }

        private int b0(int i, int i2, int i3) {
            return i < (i2 + i3) / 2 ? i2 : i3;
        }

        private boolean d0(CoordinatorLayout coordinatorLayout, T t, View view) {
            return t.l() && coordinatorLayout.getHeight() - view.getHeight() <= t.getHeight();
        }

        private static boolean e0(int i, int i2) {
            return (i & i2) == i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean f0(AppBarLayout appBarLayout) {
            int childCount = appBarLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (((d) appBarLayout.getChildAt(i).getLayoutParams()).a != 0) {
                    return true;
                }
            }
            return false;
        }

        private View g0(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof D) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        private static View h0(AppBarLayout appBarLayout, int i) {
            int iAbs = Math.abs(i);
            int childCount = appBarLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = appBarLayout.getChildAt(i2);
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        private int i0(T t, int i) {
            int childCount = t.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = t.getChildAt(i2);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                d dVar = (d) childAt.getLayoutParams();
                if (e0(dVar.c(), 32)) {
                    top -= ((LinearLayout.LayoutParams) dVar).topMargin;
                    bottom += ((LinearLayout.LayoutParams) dVar).bottomMargin;
                }
                int i3 = -i;
                if (top <= i3 && bottom >= i3) {
                    return i2;
                }
            }
            return -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public View j0(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (((CoordinatorLayout.f) childAt.getLayoutParams()).f() instanceof ScrollingViewBehavior) {
                    return childAt;
                }
            }
            return null;
        }

        private int m0(T t, int i) {
            int iAbs = Math.abs(i);
            int childCount = t.getChildCount();
            int topInset = 0;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = t.getChildAt(i2);
                d dVar = (d) childAt.getLayoutParams();
                Interpolator interpolatorD = dVar.d();
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    if (interpolatorD == null) {
                        break;
                    }
                    int iC = dVar.c();
                    if ((iC & 1) != 0) {
                        topInset = childAt.getHeight() + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                        if ((iC & 2) != 0) {
                            topInset -= childAt.getMinimumHeight();
                        }
                    }
                    if (childAt.getFitsSystemWindows()) {
                        topInset -= t.getTopInset();
                    }
                    if (topInset <= 0) {
                        break;
                    }
                    float f = topInset;
                    return Integer.signum(i) * (childAt.getTop() + Math.round(f * interpolatorD.getInterpolation((iAbs - childAt.getTop()) / f)));
                }
            }
            return i;
        }

        private boolean z0(CoordinatorLayout coordinatorLayout, T t) {
            List<View> listS = coordinatorLayout.s(t);
            int size = listS.size();
            for (int i = 0; i < size; i++) {
                CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) listS.get(i).getLayoutParams()).f();
                if (cVarF instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) cVarF).O() != 0;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.a
        int Q() {
            return I() + this.q0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public boolean L(T t) {
            WeakReference<View> weakReference = this.u0;
            if (weakReference == null) {
                return true;
            }
            View view = weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public int O(T t) {
            return (-t.getDownNestedScrollRange()) + t.getTopInset();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
        public int P(T t) {
            return t.getTotalScrollRange();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
        public void R(CoordinatorLayout coordinatorLayout, T t) {
            A0(coordinatorLayout, t);
            if (t.p()) {
                t.A(t.D(g0(coordinatorLayout)));
            }
        }

        @Override // com.google.android.material.appbar.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public boolean p(CoordinatorLayout coordinatorLayout, T t, int i) {
            boolean zP = super.p(coordinatorLayout, t, i);
            int pendingAction = t.getPendingAction();
            c cVar = this.t0;
            if (cVar == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int i2 = -t.getUpNestedPreScrollRange();
                        if (z) {
                            Z(coordinatorLayout, t, i2, 0.0f);
                        } else {
                            T(coordinatorLayout, t, i2);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z) {
                            Z(coordinatorLayout, t, 0, 0.0f);
                        } else {
                            T(coordinatorLayout, t, 0);
                        }
                    }
                }
            } else if (cVar.Y) {
                T(coordinatorLayout, t, -t.getTotalScrollRange());
            } else if (cVar.Z) {
                T(coordinatorLayout, t, 0);
            } else {
                View childAt = t.getChildAt(cVar.k0);
                T(coordinatorLayout, t, (-childAt.getBottom()) + (this.t0.m0 ? childAt.getMinimumHeight() + t.getTopInset() : Math.round(childAt.getHeight() * this.t0.l0)));
            }
            t.w();
            this.t0 = null;
            K(ax.W.a.b(I(), -t.getTotalScrollRange(), 0));
            B0(coordinatorLayout, t, I(), 0, true);
            t.u(I());
            Y(coordinatorLayout, t);
            return zP;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: p0, reason: merged with bridge method [inline-methods] */
        public boolean q(CoordinatorLayout coordinatorLayout, T t, int i, int i2, int i3, int i4) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) t.getLayoutParams())).height != -2) {
                return super.q(coordinatorLayout, t, i, i2, i3, i4);
            }
            coordinatorLayout.J(t, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:9:0x0027  */
        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
        public void u(CoordinatorLayout coordinatorLayout, T t, View view, int i, int i2, int[] iArr, int i3) {
            T t2;
            int i4;
            int downNestedPreScrollRange;
            if (i2 == 0) {
                t2 = t;
            } else {
                if (i2 < 0) {
                    i4 = -t.getTotalScrollRange();
                    downNestedPreScrollRange = t.getDownNestedPreScrollRange() + i4;
                } else {
                    i4 = -t.getUpNestedPreScrollRange();
                    downNestedPreScrollRange = 0;
                }
                int i5 = i4;
                if (i5 != downNestedPreScrollRange) {
                    t2 = t;
                    iArr[1] = S(coordinatorLayout, t2, i2, i5, downNestedPreScrollRange);
                } else {
                    t2 = t;
                }
            }
            if (t2.p()) {
                t2.A(t2.D(view));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: r0, reason: merged with bridge method [inline-methods] */
        public void x(CoordinatorLayout coordinatorLayout, T t, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            CoordinatorLayout coordinatorLayout2;
            T t2;
            int i6;
            if (i4 < 0) {
                coordinatorLayout2 = coordinatorLayout;
                t2 = t;
                i6 = i4;
                iArr[1] = S(coordinatorLayout2, t2, i6, -t.getDownNestedScrollRange(), 0);
            } else {
                coordinatorLayout2 = coordinatorLayout;
                t2 = t;
                i6 = i4;
            }
            if (i6 == 0) {
                Y(coordinatorLayout2, t2);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
        public void B(CoordinatorLayout coordinatorLayout, T t, Parcelable parcelable) {
            if (parcelable instanceof c) {
                w0((c) parcelable, true);
                super.B(coordinatorLayout, t, this.t0.a());
            } else {
                super.B(coordinatorLayout, t, parcelable);
                this.t0 = null;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: t0, reason: merged with bridge method [inline-methods] */
        public Parcelable C(CoordinatorLayout coordinatorLayout, T t) {
            Parcelable parcelableC = super.C(coordinatorLayout, t);
            c cVarX0 = x0(parcelableC, t);
            return cVarX0 == null ? parcelableC : cVarX0;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
        public boolean E(CoordinatorLayout coordinatorLayout, T t, View view, View view2, int i, int i2) {
            ValueAnimator valueAnimator;
            boolean z = (i & 2) != 0 && (t.p() || t.r() || d0(coordinatorLayout, t, view));
            if (z && (valueAnimator = this.s0) != null) {
                valueAnimator.cancel();
            }
            this.u0 = null;
            this.r0 = i2;
            return z;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: v0, reason: merged with bridge method [inline-methods] */
        public void G(CoordinatorLayout coordinatorLayout, T t, View view, int i) {
            if (this.r0 == 0 || i == 1) {
                A0(coordinatorLayout, t);
                if (t.p()) {
                    t.A(t.D(view));
                }
            }
            this.u0 = new WeakReference<>(view);
        }

        void w0(c cVar, boolean z) {
            if (this.t0 == null || z) {
                this.t0 = cVar;
            }
        }

        c x0(Parcelable parcelable, T t) {
            int I = I();
            int childCount = t.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = t.getChildAt(i);
                int bottom = childAt.getBottom() + I;
                if (childAt.getTop() + I <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = ax.p163k0.a.X;
                    }
                    c cVar = new c(parcelable);
                    boolean z = I == 0;
                    cVar.Z = z;
                    cVar.Y = !z && (-I) >= t.getTotalScrollRange();
                    cVar.k0 = i;
                    cVar.m0 = bottom == childAt.getMinimumHeight() + t.getTopInset();
                    cVar.l0 = bottom / childAt.getHeight();
                    return cVar;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
        public int U(CoordinatorLayout coordinatorLayout, T t, int i, int i2, int i3) {
            CoordinatorLayout coordinatorLayout2;
            T t2;
            int iQ = Q();
            int i4 = 0;
            if (i2 == 0 || iQ < i2 || iQ > i3) {
                coordinatorLayout2 = coordinatorLayout;
                t2 = t;
                this.q0 = 0;
            } else {
                int iB = ax.W.a.b(i, i2, i3);
                if (iQ != iB) {
                    int iM0 = t.j() ? m0(t, iB) : iB;
                    boolean zK = K(iM0);
                    int i5 = iQ - iB;
                    this.q0 = iB - iM0;
                    if (zK) {
                        while (i4 < t.getChildCount()) {
                            d dVar = (d) t.getChildAt(i4).getLayoutParams();
                            b bVarB = dVar.b();
                            if (bVarB != null && (dVar.c() & 1) != 0) {
                                bVarB.a(t, t.getChildAt(i4), I());
                            }
                            i4++;
                        }
                    }
                    if (!zK && t.j()) {
                        coordinatorLayout.h(t);
                    }
                    t.u(I());
                    coordinatorLayout2 = coordinatorLayout;
                    t2 = t;
                    B0(coordinatorLayout2, t2, iB, iB < iQ ? -1 : 1, false);
                    i4 = i5;
                } else {
                    coordinatorLayout2 = coordinatorLayout;
                    t2 = t;
                }
            }
            Y(coordinatorLayout2, t2);
            return i4;
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // com.google.android.material.appbar.a, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean H(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.H(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.appbar.c
        public /* bridge */ /* synthetic */ int I() {
            return super.I();
        }

        @Override // com.google.android.material.appbar.c
        public /* bridge */ /* synthetic */ boolean K(int i) {
            return super.K(i);
        }

        @Override // com.google.android.material.appbar.a, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean o(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.o(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: o0 */
        public /* bridge */ /* synthetic */ boolean p(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            return super.p(coordinatorLayout, appBarLayout, i);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: p0 */
        public /* bridge */ /* synthetic */ boolean q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3, int i4) {
            return super.q(coordinatorLayout, appBarLayout, i, i2, i3, i4);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: q0 */
        public /* bridge */ /* synthetic */ void u(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
            super.u(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: r0 */
        public /* bridge */ /* synthetic */ void x(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            super.x(coordinatorLayout, appBarLayout, view, i, i2, i3, i4, i5, iArr);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: s0 */
        public /* bridge */ /* synthetic */ void B(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.B(coordinatorLayout, appBarLayout, parcelable);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: t0 */
        public /* bridge */ /* synthetic */ Parcelable C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.C(coordinatorLayout, appBarLayout);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: u0 */
        public /* bridge */ /* synthetic */ boolean E(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
            return super.E(coordinatorLayout, appBarLayout, view, view2, i, i2);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: v0 */
        public /* bridge */ /* synthetic */ void G(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
            super.G(coordinatorLayout, appBarLayout, view, i);
        }
    }

    public static class ScrollingViewBehavior extends com.google.android.material.appbar.b {
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.z4);
            S(typedArrayObtainStyledAttributes.getDimensionPixelSize(m.A4, 0));
            typedArrayObtainStyledAttributes.recycle();
        }

        private static int V(AppBarLayout appBarLayout) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) appBarLayout.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                return ((BaseBehavior) cVarF).Q();
            }
            return 0;
        }

        private void W(View view, View view2) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) view2.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                C1839d0.W(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) cVarF).q0) + Q()) - M(view2));
            }
        }

        private void X(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.p()) {
                    appBarLayout.A(appBarLayout.D(view));
                }
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean A(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout appBarLayoutL = L(coordinatorLayout.r(view));
            if (appBarLayoutL != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                Rect rect3 = this.Z;
                rect3.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect3.contains(rect2)) {
                    appBarLayoutL.x(false, !z);
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.b
        float N(View view) {
            int i;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int iV = V(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + iV > downNestedPreScrollRange) && (i = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (iV / i) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.b
        int P(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.P(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.b
        /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
        public AppBarLayout L(List<View> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean i(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean l(CoordinatorLayout coordinatorLayout, View view, View view2) {
            W(view, view2);
            X(view, view2);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void m(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                C1839d0.l0(coordinatorLayout, null);
            }
        }

        @Override // com.google.android.material.appbar.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean p(CoordinatorLayout coordinatorLayout, View view, int i) {
            return super.p(coordinatorLayout, view, i);
        }

        @Override // com.google.android.material.appbar.b, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean q(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return super.q(coordinatorLayout, view, i, i2, i3, i4);
        }
    }

    public interface a<T extends AppBarLayout> {
        void a(T t, int i);
    }

    public static abstract class b {
        public abstract void a(AppBarLayout appBarLayout, View view, float f);
    }

    public static class c extends b {
        private final Rect a = new Rect();
        private final Rect b = new Rect();

        private static void b(Rect rect, AppBarLayout appBarLayout, View view) {
            view.getDrawingRect(rect);
            appBarLayout.offsetDescendantRectToMyCoords(view, rect);
            rect.offset(0, -appBarLayout.getTopInset());
        }

        @Override // com.google.android.material.appbar.AppBarLayout.b
        public void a(AppBarLayout appBarLayout, View view, float f) {
            b(this.a, appBarLayout, view);
            float fAbs = this.a.top - Math.abs(f);
            if (fAbs > 0.0f) {
                view.setClipBounds(null);
                view.setTranslationY(0.0f);
                view.setAlpha(1.0f);
                return;
            }
            float fA = 1.0f - ax.W.a.a(Math.abs(fAbs / this.a.height()), 0.0f, 1.0f);
            float fHeight = (-fAbs) - ((this.a.height() * 0.3f) * (1.0f - (fA * fA)));
            view.setTranslationY(fHeight);
            view.getDrawingRect(this.b);
            this.b.offset(0, (int) (-fHeight));
            if (fHeight >= this.b.height()) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(1.0f);
            }
            view.setClipBounds(this.b);
        }
    }

    public static class d extends LinearLayout.LayoutParams {
        int a;
        private b b;
        Interpolator c;

        public d(int i, int i2) {
            super(i, i2);
            this.a = 1;
        }

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.c);
            this.a = typedArrayObtainStyledAttributes.getInt(m.e, 0);
            f(typedArrayObtainStyledAttributes.getInt(m.d, 0));
            int i = m.f;
            if (typedArrayObtainStyledAttributes.hasValue(i)) {
                this.c = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 1;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 1;
        }

        public d(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 1;
        }

        private b a(int i) {
            if (i != 1) {
                return null;
            }
            return new c();
        }

        public b b() {
            return this.b;
        }

        public int c() {
            return this.a;
        }

        public Interpolator d() {
            return this.c;
        }

        boolean e() {
            int i = this.a;
            return (i & 1) == 1 && (i & 10) != 0;
        }

        public void f(int i) {
            this.b = a(i);
        }

        public void g(int i) {
            this.a = i;
        }
    }

    @Deprecated
    public interface e {
        void a(float f, int i);
    }

    public static abstract class f {
        public abstract void a(float f, int i, float f2);
    }

    private boolean C() {
        return this.G0 != null && getTopInset() > 0;
    }

    private boolean E() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() != 8 && !childAt.getFitsSystemWindows()) {
                return true;
            }
        }
        return false;
    }

    private void F(float f2, float f3) {
        ValueAnimator valueAnimator = this.y0;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f3);
        this.y0 = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.C0);
        this.y0.setInterpolator(this.D0);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.z0;
        if (animatorUpdateListener != null) {
            this.y0.addUpdateListener(animatorUpdateListener);
        }
        this.y0.start();
    }

    private void G() {
        setWillNotDraw(!C());
    }

    public static /* synthetic */ void a(AppBarLayout appBarLayout, h hVar, ValueAnimator valueAnimator) {
        appBarLayout.getClass();
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        hVar.f0(fFloatValue);
        Drawable drawable = appBarLayout.G0;
        if (drawable instanceof h) {
            ((h) drawable).f0(fFloatValue);
        }
        Iterator<e> it = appBarLayout.A0.iterator();
        while (it.hasNext()) {
            it.next().a(fFloatValue, hVar.E());
        }
        Iterator<f> it2 = appBarLayout.B0.iterator();
        while (it2.hasNext()) {
            it2.next().a(fFloatValue, hVar.E(), fFloatValue / appBarLayout.I0);
        }
    }

    public static /* synthetic */ void b(AppBarLayout appBarLayout, ColorStateList colorStateList, h hVar, Integer num, ValueAnimator valueAnimator) {
        Integer num2;
        appBarLayout.getClass();
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int iK = ax.p106g7.a.k(appBarLayout.F0, colorStateList.getDefaultColor(), fFloatValue);
        hVar.g0(ColorStateList.valueOf(iK));
        if (appBarLayout.G0 != null && (num2 = appBarLayout.H0) != null && num2.equals(num)) {
            appBarLayout.G0.setTint(iK);
        }
        if (!appBarLayout.A0.isEmpty()) {
            for (e eVar : appBarLayout.A0) {
                if (hVar.B() != null) {
                    eVar.a(0.0f, iK);
                }
            }
        }
        if (appBarLayout.B0.isEmpty()) {
            return;
        }
        Iterator<f> it = appBarLayout.B0.iterator();
        while (it.hasNext()) {
            it.next().a(0.0f, iK, fFloatValue);
        }
    }

    private void d() {
        WeakReference<View> weakReference = this.x0;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.x0 = null;
    }

    private Integer e() {
        Drawable drawable = this.G0;
        if (drawable instanceof h) {
            return Integer.valueOf(((h) drawable).E());
        }
        ColorStateList colorStateListF = ax.p154j7.f.f(drawable);
        if (colorStateListF != null) {
            return Integer.valueOf(colorStateListF.getDefaultColor());
        }
        return null;
    }

    private View f(View view) {
        int i;
        if (this.x0 == null && (i = this.w0) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.w0);
            }
            if (viewFindViewById != null) {
                this.x0 = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.x0;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private boolean k() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((d) getChildAt(i).getLayoutParams()).e()) {
                return true;
            }
        }
        return false;
    }

    private void m(final h hVar, final ColorStateList colorStateList) {
        final Integer numF = ax.p106g7.a.f(getContext(), ax.R6.c.i);
        this.z0 = new ValueAnimator.AnimatorUpdateListener() { // from class: ax.T6.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AppBarLayout.b(this.a, colorStateList, hVar, numF, valueAnimator);
            }
        };
    }

    private void n(Context context, final h hVar) {
        hVar.U(context);
        this.z0 = new ValueAnimator.AnimatorUpdateListener() { // from class: ax.T6.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AppBarLayout.a(this.a, hVar, valueAnimator);
            }
        };
    }

    private void o() {
        Behavior behavior = this.J0;
        BaseBehavior.c cVarX0 = (behavior == null || this.k0 == -1 || this.o0 != 0) ? null : behavior.x0(ax.p163k0.a.X, this);
        this.k0 = -1;
        this.l0 = -1;
        this.m0 = -1;
        if (cVarX0 != null) {
            this.J0.w0(cVarX0, false);
        }
    }

    private boolean q() {
        return getBackground() instanceof h;
    }

    private h s(Drawable drawable) {
        if (drawable instanceof h) {
            return (h) drawable;
        }
        ColorStateList colorStateListF = ax.p154j7.f.f(drawable);
        if (colorStateListF == null) {
            return null;
        }
        h hVar = new h();
        hVar.g0(colorStateListF);
        return hVar;
    }

    private Drawable t(Context context, Drawable drawable) {
        h hVarS = s(drawable);
        if (hVarS == null || hVarS.B() == null) {
            return drawable;
        }
        this.F0 = hVarS.B().getDefaultColor();
        ColorStateList colorStateList = this.v0;
        if (colorStateList != null) {
            m(hVarS, colorStateList);
            return hVarS;
        }
        n(context, hVarS);
        return hVarS;
    }

    private void y(boolean z, boolean z2, boolean z3) {
        this.o0 = (z ? 1 : 2) | (z2 ? 4 : 0) | (z3 ? 8 : 0);
        requestLayout();
    }

    private boolean z(boolean z) {
        if (this.s0 == z) {
            return false;
        }
        this.s0 = z;
        refreshDrawableState();
        return true;
    }

    boolean A(boolean z) {
        return B(z, !this.r0);
    }

    boolean B(boolean z, boolean z2) {
        if (!z2 || this.t0 == z) {
            return false;
        }
        this.t0 = z;
        refreshDrawableState();
        if (!q()) {
            return true;
        }
        if (this.v0 != null) {
            F(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
            return true;
        }
        if (!this.u0) {
            return true;
        }
        F(z ? 0.0f : this.I0, z ? this.I0 : 0.0f);
        return true;
    }

    boolean D(View view) {
        View viewF = f(view);
        if (viewF != null) {
            view = viewF;
        }
        if (view != null) {
            return view.canScrollVertically(-1) || view.getScrollY() > 0;
        }
        return false;
    }

    public void c(f fVar) {
        this.B0.add(fVar);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof d;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (C()) {
            int iSave = canvas.save();
            canvas.translate(0.0f, -this.q);
            this.G0.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.G0;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public d generateDefaultLayoutParams() {
        return new d(-1, -2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<AppBarLayout> getBehavior() {
        Behavior behavior = new Behavior();
        this.J0 = behavior;
        return behavior;
    }

    int getDownNestedPreScrollRange() {
        int iMin;
        int minimumHeight;
        int i = this.l0;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i3 = dVar.a;
                if ((i3 & 5) != 5) {
                    if (i2 > 0) {
                        break;
                    }
                } else {
                    int i4 = ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                    if ((i3 & 8) != 0) {
                        minimumHeight = childAt.getMinimumHeight();
                    } else {
                        if ((i3 & 2) != 0) {
                            minimumHeight = measuredHeight - childAt.getMinimumHeight();
                        } else {
                            iMin = i4 + measuredHeight;
                        }
                        if (childCount == 0 && childAt.getFitsSystemWindows()) {
                            iMin = Math.min(iMin, measuredHeight - getTopInset());
                        }
                        i2 += iMin;
                    }
                    iMin = i4 + minimumHeight;
                    if (childCount == 0) {
                        iMin = Math.min(iMin, measuredHeight - getTopInset());
                    }
                    i2 += iMin;
                }
            }
        }
        int iMax = Math.max(0, i2);
        this.l0 = iMax;
        return iMax;
    }

    int getDownNestedScrollRange() {
        int i = this.m0;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int minimumHeight = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                int i3 = dVar.a;
                if ((i3 & 1) == 0) {
                    break;
                }
                minimumHeight += measuredHeight;
                if ((i3 & 2) != 0) {
                    minimumHeight -= childAt.getMinimumHeight();
                    break;
                }
            }
        }
        int iMax = Math.max(0, minimumHeight);
        this.m0 = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.w0;
    }

    public h getMaterialShapeBackground() {
        Drawable background = getBackground();
        if (background instanceof h) {
            return (h) background;
        }
        return null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minimumHeight = getMinimumHeight();
        if (minimumHeight != 0) {
            int i = (minimumHeight * 2) + topInset;
            return i < getHeight() ? i : minimumHeight + topInset;
        }
        int childCount = getChildCount();
        int minimumHeight2 = childCount >= 1 ? getChildAt(childCount - 1).getMinimumHeight() : 0;
        if (minimumHeight2 == 0) {
            return getHeight() / 3;
        }
        int i2 = (minimumHeight2 * 2) + topInset;
        return i2 < getHeight() ? i2 : minimumHeight2 + topInset;
    }

    int getPendingAction() {
        return this.o0;
    }

    public Drawable getStatusBarForeground() {
        return this.G0;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    final int getTopInset() {
        F0 f0 = this.p0;
        if (f0 != null) {
            return f0.l();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.k0;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int minimumHeight = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i3 = dVar.a;
                if ((i3 & 1) == 0) {
                    break;
                }
                minimumHeight += measuredHeight + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                if (i2 == 0 && childAt.getFitsSystemWindows()) {
                    minimumHeight -= getTopInset();
                }
                if ((i3 & 2) != 0) {
                    minimumHeight -= childAt.getMinimumHeight();
                    break;
                }
            }
        }
        int iMax = Math.max(0, minimumHeight);
        this.k0 = iMax;
        return iMax;
    }

    int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public d generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public d generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new d((LinearLayout.LayoutParams) layoutParams);
        }
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    boolean j() {
        return this.n0;
    }

    boolean l() {
        return getTotalScrollRange() != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        if (this.E0 == null) {
            this.E0 = new int[4];
        }
        int[] iArr = this.E0;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.s0;
        int i2 = ax.R6.c.b0;
        if (!z) {
            i2 = -i2;
        }
        iArr[0] = i2;
        iArr[1] = (z && this.t0) ? ax.R6.c.c0 : -ax.R6.c.c0;
        int i3 = ax.R6.c.X;
        if (!z) {
            i3 = -i3;
        }
        iArr[2] = i3;
        iArr[3] = (z && this.t0) ? ax.R6.c.W : -ax.R6.c.W;
        return View.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        boolean z2 = true;
        if (getFitsSystemWindows() && E()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                C1839d0.W(getChildAt(childCount), topInset);
            }
        }
        o();
        this.n0 = false;
        int childCount2 = getChildCount();
        for (int i5 = 0; i5 < childCount2; i5++) {
            if (((d) getChildAt(i5).getLayoutParams()).d() != null) {
                this.n0 = true;
                break;
            }
        }
        Drawable drawable = this.G0;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.r0) {
            return;
        }
        if (!this.u0 && !k()) {
            z2 = false;
        }
        z(z2);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && getFitsSystemWindows() && E()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = ax.W.a.b(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(i2));
            } else if (mode == 0) {
                measuredHeight += getTopInset();
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        o();
    }

    public boolean p() {
        return this.u0;
    }

    public boolean r() {
        return this.t0;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(t(getContext(), drawable));
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        i.d(this, f2);
    }

    public void setExpanded(boolean z) {
        x(z, isLaidOut());
    }

    public void setLiftOnScroll(boolean z) {
        this.u0 = z;
    }

    public void setLiftOnScrollColor(ColorStateList colorStateList) {
        if (this.v0 != colorStateList) {
            this.v0 = colorStateList;
            setBackground(getBackground());
        }
    }

    public void setLiftOnScrollTargetView(View view) {
        this.w0 = -1;
        if (view == null) {
            d();
        } else {
            this.x0 = new WeakReference<>(view);
        }
    }

    public void setLiftOnScrollTargetViewId(int i) {
        this.w0 = i;
        d();
    }

    public void setLiftableOverrideEnabled(boolean z) {
        this.r0 = z;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i);
    }

    void setPendingAction(int i) {
        this.o0 = i;
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.G0;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.G0 = drawable != null ? drawable.mutate() : null;
            this.H0 = e();
            Drawable drawable3 = this.G0;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.G0.setState(getDrawableState());
                }
                ax.U.a.m(this.G0, getLayoutDirection());
                this.G0.setVisible(getVisibility() == 0, false);
                this.G0.setCallback(this);
            }
            G();
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarForegroundColor(int i) {
        setStatusBarForeground(new ColorDrawable(i));
    }

    public void setStatusBarForegroundResource(int i) {
        setStatusBarForeground(ax.p226o.a.b(getContext(), i));
    }

    @Deprecated
    public void setTargetElevation(float f2) {
        com.google.android.material.appbar.e.a(this, f2);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.G0;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    void u(int i) {
        this.q = i;
        if (!willNotDraw()) {
            postInvalidateOnAnimation();
        }
        List<a> list = this.q0;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                a aVar = this.q0.get(i2);
                if (aVar != null) {
                    aVar.a(this, i);
                }
            }
        }
    }

    public boolean v(f fVar) {
        return this.B0.remove(fVar);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.G0;
    }

    void w() {
        this.o0 = 0;
    }

    public void x(boolean z, boolean z2) {
        y(z, z2, true);
    }
}
