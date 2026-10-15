package com.google.android.material.sidesheet;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.k;
import ax.R6.l;
import ax.R6.m;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p051d0.M;
import ax.p234o7.h;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.c<V> implements ax.p234o7.b {
    private static final int F0 = k.S;
    private static final int G0 = l.p;
    private VelocityTracker A0;
    private h B0;
    private int C0;
    private final Set<g> D0;
    private final ax.p179l0.c.AbstractC0376c E0;
    private float X;
    private ax.p346v7.h Y;
    private ColorStateList Z;
    private ax.p346v7.l k0;
    private final SideSheetBehavior<V>.d l0;
    private float m0;
    private boolean n0;
    private int o0;
    private int p0;
    private com.google.android.material.sidesheet.d q;
    private ax.p179l0.c q0;
    private boolean r0;
    private float s0;
    private int t0;
    private int u0;
    private int v0;
    private int w0;
    private WeakReference<V> x0;
    private WeakReference<View> y0;
    private int z0;

    class a extends ax.p179l0.c.AbstractC0376c {
        a() {
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int a(View view, int i, int i2) {
            return ax.W.a.b(i, SideSheetBehavior.this.q.g(), SideSheetBehavior.this.q.f());
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int d(View view) {
            return SideSheetBehavior.this.t0 + SideSheetBehavior.this.k0();
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void j(int i) {
            if (i == 1 && SideSheetBehavior.this.n0) {
                SideSheetBehavior.this.G0(1);
            }
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void k(View view, int i, int i2, int i3, int i4) {
            ViewGroup.MarginLayoutParams marginLayoutParams;
            View viewF0 = SideSheetBehavior.this.f0();
            if (viewF0 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) viewF0.getLayoutParams()) != null) {
                SideSheetBehavior.this.q.p(marginLayoutParams, view.getLeft(), view.getRight());
                viewF0.setLayoutParams(marginLayoutParams);
            }
            SideSheetBehavior.this.a0(view, i);
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void l(View view, float f, float f2) {
            int iW = SideSheetBehavior.this.W(view, f, f2);
            SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
            sideSheetBehavior.L0(view, iW, sideSheetBehavior.K0());
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public boolean m(View view, int i) {
            return (SideSheetBehavior.this.o0 == 1 || SideSheetBehavior.this.x0 == null || SideSheetBehavior.this.x0.get() != view) ? false : true;
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            SideSheetBehavior.this.G0(5);
            if (SideSheetBehavior.this.x0 == null || SideSheetBehavior.this.x0.get() == null) {
                return;
            }
            ((View) SideSheetBehavior.this.x0.get()).requestLayout();
        }
    }

    protected static class c extends ax.p163k0.a {
        public static final Parcelable.Creator<c> CREATOR = new a();
        final int Y;

        class a implements Parcelable.ClassLoaderCreator<c> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel, (ClassLoader) null);
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
            this.Y = parcel.readInt();
        }

        public c(Parcelable parcelable, SideSheetBehavior<?> sideSheetBehavior) {
            super(parcelable);
            this.Y = ((SideSheetBehavior) sideSheetBehavior).o0;
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.Y);
        }
    }

    class d {
        private int a;
        private boolean b;
        private final Runnable c = new Runnable() { // from class: com.google.android.material.sidesheet.f
            @Override // java.lang.Runnable
            public final void run() {
                SideSheetBehavior.d.a(this.q);
            }
        };

        d() {
        }

        public static /* synthetic */ void a(d dVar) {
            dVar.b = false;
            if (SideSheetBehavior.this.q0 != null && SideSheetBehavior.this.q0.m(true)) {
                dVar.b(dVar.a);
            } else if (SideSheetBehavior.this.o0 == 2) {
                SideSheetBehavior.this.G0(dVar.a);
            }
        }

        void b(int i) {
            if (SideSheetBehavior.this.x0 == null || SideSheetBehavior.this.x0.get() == null) {
                return;
            }
            this.a = i;
            if (this.b) {
                return;
            }
            ((View) SideSheetBehavior.this.x0.get()).postOnAnimation(this.c);
            this.b = true;
        }
    }

    public SideSheetBehavior() {
        this.l0 = new d();
        this.n0 = true;
        this.o0 = 5;
        this.p0 = 5;
        this.s0 = 0.1f;
        this.z0 = -1;
        this.D0 = new LinkedHashSet();
        this.E0 = new a();
    }

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l0 = new d();
        this.n0 = true;
        this.o0 = 5;
        this.p0 = 5;
        this.s0 = 0.1f;
        this.z0 = -1;
        this.D0 = new LinkedHashSet();
        this.E0 = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.P4);
        int i = m.R4;
        if (typedArrayObtainStyledAttributes.hasValue(i)) {
            this.Z = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, i);
        }
        if (typedArrayObtainStyledAttributes.hasValue(m.U4)) {
            this.k0 = ax.p346v7.l.e(context, attributeSet, 0, G0).m();
        }
        int i2 = m.T4;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            B0(typedArrayObtainStyledAttributes.getResourceId(i2, -1));
        }
        Z(context);
        this.m0 = typedArrayObtainStyledAttributes.getDimension(m.Q4, -1.0f);
        C0(typedArrayObtainStyledAttributes.getBoolean(m.S4, true));
        typedArrayObtainStyledAttributes.recycle();
        this.X = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private void A0(V v, Runnable runnable) {
        if (v0(v)) {
            v.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void D0(int i) {
        com.google.android.material.sidesheet.d dVar = this.q;
        if (dVar == null || dVar.j() != i) {
            if (i == 0) {
                this.q = new com.google.android.material.sidesheet.b(this);
                if (this.k0 == null || s0()) {
                    return;
                }
                ax.v7.l.b bVarW = this.k0.w();
                bVarW.G(0.0f).y(0.0f);
                O0(bVarW.m());
                return;
            }
            if (i == 1) {
                this.q = new com.google.android.material.sidesheet.a(this);
                if (this.k0 == null || r0()) {
                    return;
                }
                ax.v7.l.b bVarW2 = this.k0.w();
                bVarW2.C(0.0f).u(0.0f);
                O0(bVarW2.m());
                return;
            }
            throw new IllegalArgumentException("Invalid sheet edge position value: " + i + ". Must be 0 or 1.");
        }
    }

    private void E0(V v, int i) {
        D0(Gravity.getAbsoluteGravity(((CoordinatorLayout.f) v.getLayoutParams()).c, i) == 3 ? 1 : 0);
    }

    private boolean H0() {
        if (this.q0 != null) {
            return this.n0 || this.o0 == 1;
        }
        return false;
    }

    public static /* synthetic */ boolean I(SideSheetBehavior sideSheetBehavior, int i, View view, M.a aVar) {
        sideSheetBehavior.F0(i);
        return true;
    }

    public static /* synthetic */ void J(SideSheetBehavior sideSheetBehavior, int i) {
        V v = sideSheetBehavior.x0.get();
        if (v != null) {
            sideSheetBehavior.L0(v, i, false);
        }
    }

    private boolean J0(V v) {
        return (v.isShown() || C1839d0.p(v) != null) && this.n0;
    }

    public static /* synthetic */ void K(SideSheetBehavior sideSheetBehavior, ViewGroup.MarginLayoutParams marginLayoutParams, int i, View view, ValueAnimator valueAnimator) {
        sideSheetBehavior.q.o(marginLayoutParams, ax.S6.a.c(i, 0, valueAnimator.getAnimatedFraction()));
        view.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0(View view, int i, boolean z) {
        if (!w0(view, i, z)) {
            G0(i);
        } else {
            G0(2);
            this.l0.b(i);
        }
    }

    private void M0() {
        V v;
        WeakReference<V> weakReference = this.x0;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        C1839d0.f0(v, 262144);
        C1839d0.f0(v, 1048576);
        if (this.o0 != 5) {
            y0(v, H.a.y, 5);
        }
        if (this.o0 != 3) {
            y0(v, H.a.w, 3);
        }
    }

    private void N0() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        WeakReference<V> weakReference = this.x0;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        V v = this.x0.get();
        View viewF0 = f0();
        if (viewF0 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) viewF0.getLayoutParams()) == null) {
            return;
        }
        this.q.o(marginLayoutParams, (int) ((this.t0 * v.getScaleX()) + this.w0));
        viewF0.requestLayout();
    }

    private void O0(ax.p346v7.l lVar) {
        ax.p346v7.h hVar = this.Y;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(lVar);
        }
    }

    private void P0(View view) {
        int i = this.o0 == 5 ? 4 : 0;
        if (view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }

    private int U(int i, V v) {
        int i2 = this.o0;
        if (i2 == 1 || i2 == 2) {
            return i - this.q.h(v);
        }
        if (i2 == 3) {
            return 0;
        }
        if (i2 == 5) {
            return this.q.e();
        }
        throw new IllegalStateException("Unexpected value: " + this.o0);
    }

    private float V(float f, float f2) {
        return Math.abs(f - f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int W(View view, float f, float f2) {
        if (u0(f)) {
            return 3;
        }
        if (I0(view, f)) {
            return (this.q.m(f, f2) || this.q.l(view)) ? 5 : 3;
        }
        if (f != 0.0f && e.a(f, f2)) {
            return 5;
        }
        int left = view.getLeft();
        return Math.abs(left - g0()) < Math.abs(left - this.q.e()) ? 3 : 5;
    }

    private void X() {
        WeakReference<View> weakReference = this.y0;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.y0 = null;
    }

    private M Y(final int i) {
        return new M() { // from class: ax.w7.a
            @Override // ax.p051d0.M
            public final boolean a(View view, M.a aVar) {
                return SideSheetBehavior.I(this.a, i, view, aVar);
            }
        };
    }

    private void Z(Context context) {
        if (this.k0 == null) {
            return;
        }
        ax.p346v7.h hVar = new ax.p346v7.h(this.k0);
        this.Y = hVar;
        hVar.U(context);
        ColorStateList colorStateList = this.Z;
        if (colorStateList != null) {
            this.Y.g0(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
        this.Y.setTint(typedValue.data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0(View view, int i) {
        if (this.D0.isEmpty()) {
            return;
        }
        float fB = this.q.b(i);
        Iterator<g> it = this.D0.iterator();
        while (it.hasNext()) {
            it.next().b(view, fB);
        }
    }

    private void b0(View view) {
        if (C1839d0.p(view) == null) {
            C1839d0.n0(view, view.getResources().getString(F0));
        }
    }

    private int c0(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
        }
        if (size != 0) {
            i3 = Math.min(size, i3);
        }
        return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
    }

    private ValueAnimator.AnimatorUpdateListener e0() {
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        final View viewF0 = f0();
        if (viewF0 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) viewF0.getLayoutParams()) == null) {
            return null;
        }
        final int iC = this.q.c(marginLayoutParams);
        return new ValueAnimator.AnimatorUpdateListener() { // from class: ax.w7.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SideSheetBehavior.K(this.a, marginLayoutParams, iC, viewF0, valueAnimator);
            }
        };
    }

    private int h0() {
        com.google.android.material.sidesheet.d dVar = this.q;
        return (dVar == null || dVar.j() == 0) ? 5 : 3;
    }

    private CoordinatorLayout.f q0() {
        V v;
        WeakReference<V> weakReference = this.x0;
        if (weakReference == null || (v = weakReference.get()) == null || !(v.getLayoutParams() instanceof CoordinatorLayout.f)) {
            return null;
        }
        return (CoordinatorLayout.f) v.getLayoutParams();
    }

    private boolean r0() {
        CoordinatorLayout.f fVarQ0 = q0();
        return fVarQ0 != null && ((ViewGroup.MarginLayoutParams) fVarQ0).leftMargin > 0;
    }

    private boolean s0() {
        CoordinatorLayout.f fVarQ0 = q0();
        return fVarQ0 != null && ((ViewGroup.MarginLayoutParams) fVarQ0).rightMargin > 0;
    }

    private boolean t0(MotionEvent motionEvent) {
        return H0() && V((float) this.C0, motionEvent.getX()) > ((float) this.q0.z());
    }

    private boolean u0(float f) {
        return this.q.k(f);
    }

    private boolean v0(V v) {
        ViewParent parent = v.getParent();
        return parent != null && parent.isLayoutRequested() && v.isAttachedToWindow();
    }

    private boolean w0(View view, int i, boolean z) {
        int iL0 = l0(i);
        ax.p179l0.c cVarP0 = p0();
        if (cVarP0 == null) {
            return false;
        }
        if (z) {
            return cVarP0.O(iL0, view.getTop());
        }
        return cVarP0.Q(view, iL0, view.getTop());
    }

    private void x0(CoordinatorLayout coordinatorLayout) {
        int i;
        View viewFindViewById;
        if (this.y0 != null || (i = this.z0) == -1 || (viewFindViewById = coordinatorLayout.findViewById(i)) == null) {
            return;
        }
        this.y0 = new WeakReference<>(viewFindViewById);
    }

    private void y0(V v, H.a aVar, int i) {
        C1839d0.h0(v, aVar, null, Y(i));
    }

    private void z0() {
        VelocityTracker velocityTracker = this.A0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.A0 = null;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void B(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        c cVar = (c) parcelable;
        if (cVar.a() != null) {
            super.B(coordinatorLayout, v, cVar.a());
        }
        int i = cVar.Y;
        if (i == 1 || i == 2) {
            i = 5;
        }
        this.o0 = i;
        this.p0 = i;
    }

    public void B0(int i) {
        this.z0 = i;
        X();
        WeakReference<V> weakReference = this.x0;
        if (weakReference != null) {
            V v = weakReference.get();
            if (i == -1 || !v.isLaidOut()) {
                return;
            }
            v.requestLayout();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable C(CoordinatorLayout coordinatorLayout, V v) {
        return new c(super.C(coordinatorLayout, v), (SideSheetBehavior<?>) this);
    }

    public void C0(boolean z) {
        this.n0 = z;
    }

    public void F0(final int i) {
        if (i == 1 || i == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATE_");
            sb.append(i == 1 ? "DRAGGING" : "SETTLING");
            sb.append(" should not be set externally.");
            throw new IllegalArgumentException(sb.toString());
        }
        WeakReference<V> weakReference = this.x0;
        if (weakReference == null || weakReference.get() == null) {
            G0(i);
        } else {
            A0(this.x0.get(), new Runnable() { // from class: ax.w7.b
                @Override // java.lang.Runnable
                public final void run() {
                    SideSheetBehavior.J(this.q, i);
                }
            });
        }
    }

    void G0(int i) {
        V v;
        if (this.o0 == i) {
            return;
        }
        this.o0 = i;
        if (i == 3 || i == 5) {
            this.p0 = i;
        }
        WeakReference<V> weakReference = this.x0;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        P0(v);
        Iterator<g> it = this.D0.iterator();
        while (it.hasNext()) {
            it.next().a(v, i);
        }
        M0();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean H(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.o0 == 1 && actionMasked == 0) {
            return true;
        }
        if (H0()) {
            this.q0.F(motionEvent);
        }
        if (actionMasked == 0) {
            z0();
        }
        if (this.A0 == null) {
            this.A0 = VelocityTracker.obtain();
        }
        this.A0.addMovement(motionEvent);
        if (H0() && actionMasked == 2 && !this.r0 && t0(motionEvent)) {
            this.q0.b(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.r0;
    }

    boolean I0(View view, float f) {
        return this.q.n(view, f);
    }

    public boolean K0() {
        return true;
    }

    @Override // ax.p234o7.b
    public void a(ax.p130i.b bVar) {
        h hVar = this.B0;
        if (hVar == null) {
            return;
        }
        hVar.j(bVar);
    }

    @Override // ax.p234o7.b
    public void b(ax.p130i.b bVar) {
        h hVar = this.B0;
        if (hVar == null) {
            return;
        }
        hVar.l(bVar, h0());
        N0();
    }

    @Override // ax.p234o7.b
    public void c() {
        h hVar = this.B0;
        if (hVar == null) {
            return;
        }
        ax.p130i.b bVarC = hVar.c();
        if (bVarC == null || Build.VERSION.SDK_INT < 34) {
            F0(5);
        } else {
            this.B0.h(bVarC, h0(), new b(), e0());
        }
    }

    @Override // ax.p234o7.b
    public void d() {
        h hVar = this.B0;
        if (hVar == null) {
            return;
        }
        hVar.f();
    }

    int d0() {
        return this.t0;
    }

    public View f0() {
        WeakReference<View> weakReference = this.y0;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int g0() {
        return this.q.d();
    }

    public float i0() {
        return this.s0;
    }

    float j0() {
        return 0.5f;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void k(CoordinatorLayout.f fVar) {
        super.k(fVar);
        this.x0 = null;
        this.q0 = null;
        this.B0 = null;
    }

    int k0() {
        return this.w0;
    }

    int l0(int i) {
        if (i == 3) {
            return g0();
        }
        if (i == 5) {
            return this.q.e();
        }
        throw new IllegalArgumentException("Invalid state to get outer edge offset: " + i);
    }

    int m0() {
        return this.v0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void n() {
        super.n();
        this.x0 = null;
        this.q0 = null;
        this.B0 = null;
    }

    int n0() {
        return this.u0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ax.p179l0.c cVar;
        if (!J0(v)) {
            this.r0 = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z0();
        }
        if (this.A0 == null) {
            this.A0 = VelocityTracker.obtain();
        }
        this.A0.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.C0 = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.r0) {
            this.r0 = false;
            return false;
        }
        return (this.r0 || (cVar = this.q0) == null || !cVar.P(motionEvent)) ? false : true;
    }

    int o0() {
        return 500;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (coordinatorLayout.getFitsSystemWindows() && !v.getFitsSystemWindows()) {
            v.setFitsSystemWindows(true);
        }
        if (this.x0 == null) {
            this.x0 = new WeakReference<>(v);
            this.B0 = new h(v);
            ax.p346v7.h hVar = this.Y;
            if (hVar != null) {
                v.setBackground(hVar);
                ax.p346v7.h hVar2 = this.Y;
                float elevation = this.m0;
                if (elevation == -1.0f) {
                    elevation = v.getElevation();
                }
                hVar2.f0(elevation);
            } else {
                ColorStateList colorStateList = this.Z;
                if (colorStateList != null) {
                    C1839d0.p0(v, colorStateList);
                }
            }
            P0(v);
            M0();
            if (v.getImportantForAccessibility() == 0) {
                v.setImportantForAccessibility(1);
            }
            b0(v);
        }
        E0(v, i);
        if (this.q0 == null) {
            this.q0 = ax.p179l0.c.o(coordinatorLayout, this.E0);
        }
        int iH = this.q.h(v);
        coordinatorLayout.I(v, i);
        this.u0 = coordinatorLayout.getWidth();
        this.v0 = this.q.i(coordinatorLayout);
        this.t0 = v.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        this.w0 = marginLayoutParams != null ? this.q.a(marginLayoutParams) : 0;
        C1839d0.V(v, U(iH, v));
        x0(coordinatorLayout);
        for (g gVar : this.D0) {
            if (gVar instanceof g) {
                gVar.c(v);
            }
        }
        return true;
    }

    ax.p179l0.c p0() {
        return this.q0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean q(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        v.measure(c0(i, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, -1, marginLayoutParams.width), c0(i3, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, -1, marginLayoutParams.height));
        return true;
    }
}
