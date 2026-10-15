package com.google.android.material.bottomsheet;

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
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.k;
import ax.R6.l;
import ax.R6.m;
import ax.p035c0.C1839d0;
import ax.p035c0.F0;
import ax.p051d0.H;
import ax.p051d0.M;
import ax.p202m7.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> implements ax.p234o7.b {
    private static final int r1 = l.h;
    private boolean A0;
    private boolean B0;
    private int C0;
    private int D0;
    private boolean E0;
    private ax.p346v7.l F0;
    private boolean G0;
    private final BottomSheetBehavior<V>.i H0;
    private ValueAnimator I0;
    int J0;
    int K0;
    int L0;
    float M0;
    int N0;
    float O0;
    boolean P0;
    private boolean Q0;
    private boolean R0;
    private boolean S0;
    private boolean T0;
    int U0;
    int V0;
    ax.p179l0.c W0;
    private boolean X;
    private boolean X0;
    private boolean Y;
    private int Y0;
    private float Z;
    private boolean Z0;
    private float a1;
    private int b1;
    int c1;
    int d1;
    WeakReference<V> e1;
    WeakReference<View> f1;
    WeakReference<View> g1;
    WeakReference<View> h1;
    private final ArrayList<g> i1;
    private VelocityTracker j1;
    private int k0;
    ax.p234o7.f k1;
    private int l0;
    int l1;
    private boolean m0;
    private int m1;
    private int n0;
    boolean n1;
    private int o0;
    private Map<View, Integer> o1;
    private ax.p346v7.h p0;
    final SparseIntArray p1;
    private int q;
    private ColorStateList q0;
    private final ax.p179l0.c.AbstractC0376c q1;
    private int r0;
    private int s0;
    private int t0;
    private boolean u0;
    private boolean v0;
    private boolean w0;
    private boolean x0;
    private boolean y0;
    private boolean z0;

    class a implements Runnable {
        final /* synthetic */ int X;
        final /* synthetic */ View q;

        a(View view, int i) {
            this.q = view;
            this.X = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.h1(this.q, this.X, false);
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomSheetBehavior.this.a1(5);
            WeakReference<V> weakReference = BottomSheetBehavior.this.e1;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            BottomSheetBehavior.this.e1.get().requestLayout();
        }
    }

    class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.p0 != null) {
                BottomSheetBehavior.this.p0.h0(fFloatValue);
            }
        }
    }

    class d implements z.c {
        final /* synthetic */ boolean a;

        d(boolean z) {
            this.a = z;
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0080  */
        /* JADX WARN: Code duplicated, block: B:33:0x00a3  */
        @Override // ax.m7.z.c
        public F0 a(View view, F0 f0, z.d dVar) {
            boolean z;
            ax.T.b bVarF = f0.f(F0.m.h());
            ax.T.b bVarF2 = f0.f(F0.m.e());
            BottomSheetBehavior.this.D0 = bVarF.b;
            boolean zH = z.h(view);
            int paddingBottom = view.getPaddingBottom();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            if (BottomSheetBehavior.this.v0) {
                BottomSheetBehavior.this.C0 = f0.i();
                paddingBottom = dVar.d + BottomSheetBehavior.this.C0;
            }
            if (BottomSheetBehavior.this.w0) {
                paddingLeft = (zH ? dVar.c : dVar.a) + bVarF.a;
            }
            if (BottomSheetBehavior.this.x0) {
                paddingRight = (zH ? dVar.a : dVar.c) + bVarF.c;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            boolean z2 = true;
            if (BottomSheetBehavior.this.z0) {
                int i = marginLayoutParams.leftMargin;
                int i2 = bVarF.a;
                if (i != i2) {
                    marginLayoutParams.leftMargin = i2;
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (BottomSheetBehavior.this.A0) {
                int i3 = marginLayoutParams.rightMargin;
                int i4 = bVarF.c;
                if (i3 != i4) {
                    marginLayoutParams.rightMargin = i4;
                    z = true;
                }
            }
            if (BottomSheetBehavior.this.B0) {
                int i5 = marginLayoutParams.topMargin;
                int i6 = bVarF.b;
                if (i5 != i6) {
                    marginLayoutParams.topMargin = i6;
                } else {
                    z2 = z;
                }
            } else {
                z2 = z;
            }
            if (z2) {
                view.setLayoutParams(marginLayoutParams);
            }
            view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
            if (this.a) {
                BottomSheetBehavior.this.t0 = bVarF2.d;
            }
            if (!BottomSheetBehavior.this.v0 && !this.a) {
                return f0;
            }
            BottomSheetBehavior.this.m1(false);
            return f0;
        }
    }

    class e extends ax.p179l0.c.AbstractC0376c {
        private long a;

        e() {
        }

        private boolean n(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.d1 + bottomSheetBehavior.s0()) / 2;
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int a(View view, int i, int i2) {
            return view.getLeft();
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int b(View view, int i, int i2) {
            return ax.W.a.b(i, BottomSheetBehavior.this.s0(), e(view));
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int e(View view) {
            return BottomSheetBehavior.this.j0() ? BottomSheetBehavior.this.d1 : BottomSheetBehavior.this.N0;
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void j(int i) {
            if (i == 1 && BottomSheetBehavior.this.R0) {
                BottomSheetBehavior.this.a1(1);
            }
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void k(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.o0(i2);
        }

        /* JADX WARN: Code duplicated, block: B:39:0x00ae  */
        /* JADX WARN: Code duplicated, block: B:6:0x0010  */
        @Override // ax.p179l0.c.AbstractC0376c
        public void l(View view, float f, float f2) {
            int i = 6;
            if (f2 >= 0.0f) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.P0 && bottomSheetBehavior.e1(view, f2)) {
                    if ((Math.abs(f) < Math.abs(f2) && f2 > BottomSheetBehavior.this.k0) || n(view)) {
                        i = 5;
                    } else if (BottomSheetBehavior.this.X || Math.abs(view.getTop() - BottomSheetBehavior.this.s0()) < Math.abs(view.getTop() - BottomSheetBehavior.this.L0)) {
                        i = 3;
                    }
                } else if (f2 == 0.0f || Math.abs(f) > Math.abs(f2)) {
                    int top = view.getTop();
                    if (!BottomSheetBehavior.this.X) {
                        BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                        int i2 = bottomSheetBehavior2.L0;
                        if (top < i2) {
                            if (top < Math.abs(top - bottomSheetBehavior2.N0)) {
                                i = 3;
                            } else if (BottomSheetBehavior.this.f1()) {
                                i = 4;
                            }
                        } else if (Math.abs(top - i2) >= Math.abs(top - BottomSheetBehavior.this.N0) || BottomSheetBehavior.this.f1()) {
                            i = 4;
                        }
                    } else if (Math.abs(top - BottomSheetBehavior.this.K0) < Math.abs(top - BottomSheetBehavior.this.N0)) {
                        i = 3;
                    } else {
                        i = 4;
                    }
                } else if (BottomSheetBehavior.this.X) {
                    i = 4;
                } else {
                    int top2 = view.getTop();
                    if (Math.abs(top2 - BottomSheetBehavior.this.L0) >= Math.abs(top2 - BottomSheetBehavior.this.N0) || BottomSheetBehavior.this.f1()) {
                        i = 4;
                    }
                }
            } else if (BottomSheetBehavior.this.X) {
                i = 3;
            } else {
                int top3 = view.getTop();
                long jUptimeMillis = SystemClock.uptimeMillis() - this.a;
                if (BottomSheetBehavior.this.f1()) {
                    BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                    if (!bottomSheetBehavior3.c1(jUptimeMillis, (top3 * 100.0f) / bottomSheetBehavior3.d1)) {
                        i = 4;
                    }
                } else if (top3 <= BottomSheetBehavior.this.L0) {
                }
                i = 3;
            }
            BottomSheetBehavior bottomSheetBehavior4 = BottomSheetBehavior.this;
            bottomSheetBehavior4.h1(view, i, bottomSheetBehavior4.g1());
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public boolean m(View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.U0;
            if (i2 == 1 || bottomSheetBehavior.n1) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.l1 == i) {
                WeakReference<View> weakReference = bottomSheetBehavior.h1;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            this.a = SystemClock.uptimeMillis();
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.e1;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    class f implements M {
        final /* synthetic */ int a;

        f(int i) {
            this.a = i;
        }

        @Override // ax.p051d0.M
        public boolean a(View view, M.a aVar) {
            BottomSheetBehavior.this.Z0(this.a);
            return true;
        }
    }

    public static abstract class g {
        void a(View view) {
        }

        public abstract void b(View view, float f);

        public abstract void c(View view, int i);
    }

    protected static class h extends ax.p163k0.a {
        public static final Parcelable.Creator<h> CREATOR = new a();
        final int Y;
        int Z;
        boolean k0;
        boolean l0;
        boolean m0;

        class a implements Parcelable.ClassLoaderCreator<h> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public h createFromParcel(Parcel parcel) {
                return new h(parcel, (ClassLoader) null);
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
            this.Z = parcel.readInt();
            this.k0 = parcel.readInt() == 1;
            this.l0 = parcel.readInt() == 1;
            this.m0 = parcel.readInt() == 1;
        }

        public h(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.Y = bottomSheetBehavior.U0;
            this.Z = ((BottomSheetBehavior) bottomSheetBehavior).l0;
            this.k0 = ((BottomSheetBehavior) bottomSheetBehavior).X;
            this.l0 = bottomSheetBehavior.P0;
            this.m0 = ((BottomSheetBehavior) bottomSheetBehavior).Q0;
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.Y);
            parcel.writeInt(this.Z);
            parcel.writeInt(this.k0 ? 1 : 0);
            parcel.writeInt(this.l0 ? 1 : 0);
            parcel.writeInt(this.m0 ? 1 : 0);
        }
    }

    private class i {
        private int a;
        private boolean b;
        private final Runnable c;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                i.this.b = false;
                ax.p179l0.c cVar = BottomSheetBehavior.this.W0;
                if (cVar != null && cVar.m(true)) {
                    i iVar = i.this;
                    iVar.c(iVar.a);
                    return;
                }
                i iVar2 = i.this;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.U0 == 2) {
                    bottomSheetBehavior.a1(iVar2.a);
                }
            }
        }

        private i() {
            this.c = new a();
        }

        /* synthetic */ i(BottomSheetBehavior bottomSheetBehavior, a aVar) {
            this();
        }

        void c(int i) {
            WeakReference<V> weakReference = BottomSheetBehavior.this.e1;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.a = i;
            if (this.b) {
                return;
            }
            BottomSheetBehavior.this.e1.get().postOnAnimation(this.c);
            this.b = true;
        }
    }

    public BottomSheetBehavior() {
        this.q = 0;
        this.X = true;
        this.Y = false;
        this.r0 = -1;
        this.s0 = -1;
        this.H0 = new i(this, null);
        this.M0 = 0.5f;
        this.O0 = -1.0f;
        this.R0 = true;
        this.S0 = true;
        this.U0 = 4;
        this.V0 = 4;
        this.a1 = 0.1f;
        this.i1 = new ArrayList<>();
        this.m1 = -1;
        this.p1 = new SparseIntArray();
        this.q1 = new e();
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i2;
        super(context, attributeSet);
        this.q = 0;
        this.X = true;
        this.Y = false;
        this.r0 = -1;
        this.s0 = -1;
        this.H0 = new i(this, null);
        this.M0 = 0.5f;
        this.O0 = -1.0f;
        this.R0 = true;
        this.S0 = true;
        this.U0 = 4;
        this.V0 = 4;
        this.a1 = 0.1f;
        this.i1 = new ArrayList<>();
        this.m1 = -1;
        this.p1 = new SparseIntArray();
        this.q1 = new e();
        this.o0 = context.getResources().getDimensionPixelSize(ax.R6.e.o0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.b0);
        int i3 = m.f0;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            this.q0 = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, i3);
        }
        if (typedArrayObtainStyledAttributes.hasValue(m.y0)) {
            this.F0 = ax.p346v7.l.e(context, attributeSet, ax.R6.c.c, r1).m();
        }
        m0(context);
        n0();
        this.O0 = typedArrayObtainStyledAttributes.getDimension(m.e0, -1.0f);
        int i4 = m.c0;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            T0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i4, -1));
        }
        int i5 = m.d0;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            S0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i5, -1));
        }
        int i6 = m.m0;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i6);
        if (typedValuePeekValue == null || (i2 = typedValuePeekValue.data) != -1) {
            U0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i6, -1));
        } else {
            U0(i2);
        }
        R0(typedArrayObtainStyledAttributes.getBoolean(m.l0, false));
        P0(typedArrayObtainStyledAttributes.getBoolean(m.q0, false));
        O0(typedArrayObtainStyledAttributes.getBoolean(m.j0, true));
        Y0(typedArrayObtainStyledAttributes.getBoolean(m.p0, false));
        L0(typedArrayObtainStyledAttributes.getBoolean(m.g0, true));
        M0(typedArrayObtainStyledAttributes.getBoolean(m.h0, true));
        W0(typedArrayObtainStyledAttributes.getInt(m.n0, 0));
        Q0(typedArrayObtainStyledAttributes.getFloat(m.k0, 0.5f));
        int i7 = m.i0;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i7);
        if (typedValuePeekValue2 == null || typedValuePeekValue2.type != 16) {
            N0(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i7, 0));
        } else {
            N0(typedValuePeekValue2.data);
        }
        X0(typedArrayObtainStyledAttributes.getInt(m.o0, 500));
        this.v0 = typedArrayObtainStyledAttributes.getBoolean(m.u0, false);
        this.w0 = typedArrayObtainStyledAttributes.getBoolean(m.v0, false);
        this.x0 = typedArrayObtainStyledAttributes.getBoolean(m.w0, false);
        this.y0 = typedArrayObtainStyledAttributes.getBoolean(m.x0, true);
        this.z0 = typedArrayObtainStyledAttributes.getBoolean(m.r0, false);
        this.A0 = typedArrayObtainStyledAttributes.getBoolean(m.s0, false);
        this.B0 = typedArrayObtainStyledAttributes.getBoolean(m.t0, false);
        this.E0 = typedArrayObtainStyledAttributes.getBoolean(m.z0, true);
        typedArrayObtainStyledAttributes.recycle();
        this.Z = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private boolean C0(V v) {
        ViewParent parent = v.getParent();
        return parent != null && parent.isLayoutRequested() && v.isAttachedToWindow();
    }

    private boolean E0(CoordinatorLayout coordinatorLayout, int i2, int i3) {
        WeakReference<View> weakReference = this.g1;
        View view = weakReference != null ? weakReference.get() : null;
        return view != null && coordinatorLayout.B(view, i2, i3);
    }

    private boolean F0(CoordinatorLayout coordinatorLayout, int i2, int i3) {
        WeakReference<View> weakReference = this.h1;
        View view = weakReference != null ? weakReference.get() : null;
        return view != null && coordinatorLayout.B(view, i2, i3);
    }

    private void H0(View view, H.a aVar, int i2) {
        C1839d0.h0(view, aVar, null, l0(i2));
    }

    private void I0() {
        this.l1 = -1;
        this.m1 = -1;
        VelocityTracker velocityTracker = this.j1;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.j1 = null;
        }
    }

    private void J0(h hVar) {
        int i2 = this.q;
        if (i2 == 0) {
            return;
        }
        if (i2 == -1 || (i2 & 1) == 1) {
            this.l0 = hVar.Z;
        }
        if (i2 == -1 || (i2 & 2) == 2) {
            this.X = hVar.k0;
        }
        if (i2 == -1 || (i2 & 4) == 4) {
            this.P0 = hVar.l0;
        }
        if (i2 == -1 || (i2 & 8) == 8) {
            this.Q0 = hVar.m0;
        }
    }

    private void K0(V v, Runnable runnable) {
        if (C0(v)) {
            v.post(runnable);
        } else {
            runnable.run();
        }
    }

    private int b0(View view, int i2, int i3) {
        return C1839d0.c(view, view.getResources().getString(i2), l0(i3));
    }

    private void b1(View view) {
        boolean z = (Build.VERSION.SDK_INT < 29 || z0() || this.m0) ? false : true;
        if (this.v0 || this.w0 || this.x0 || this.z0 || this.A0 || this.B0 || z) {
            z.b(view, new d(z));
        }
    }

    private void d0() {
        int iH0 = h0();
        if (this.X) {
            this.N0 = Math.max(this.d1 - iH0, this.K0);
        } else {
            this.N0 = this.d1 - iH0;
        }
    }

    private boolean d1() {
        if (this.W0 != null) {
            return this.R0 || this.U0 == 1;
        }
        return false;
    }

    private float e0(float f2, RoundedCorner roundedCorner) {
        if (roundedCorner != null) {
            float radius = roundedCorner.getRadius();
            if (radius > 0.0f && f2 > 0.0f) {
                return radius / f2;
            }
        }
        return 0.0f;
    }

    private void f0() {
        this.L0 = (int) (this.d1 * (1.0f - this.M0));
    }

    private float g0() {
        WeakReference<V> weakReference;
        WindowInsets rootWindowInsets;
        if (this.p0 == null || (weakReference = this.e1) == null || weakReference.get() == null || Build.VERSION.SDK_INT < 31) {
            return 0.0f;
        }
        V v = this.e1.get();
        if (!x0() || (rootWindowInsets = v.getRootWindowInsets()) == null) {
            return 0.0f;
        }
        return Math.max(e0(this.p0.N(), rootWindowInsets.getRoundedCorner(0)), e0(this.p0.O(), rootWindowInsets.getRoundedCorner(1)));
    }

    private int h0() {
        int i2;
        if (this.m0) {
            return Math.min(Math.max(this.n0, this.d1 - ((this.c1 * 9) / 16)), this.b1) + this.C0;
        }
        return (this.u0 || this.v0 || (i2 = this.t0) <= 0) ? this.l0 + this.C0 : Math.max(this.l0, i2 + this.o0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h1(View view, int i2, boolean z) {
        int iV0 = v0(i2);
        ax.p179l0.c cVar = this.W0;
        if (cVar == null || (!z ? cVar.Q(view, view.getLeft(), iV0) : cVar.O(view.getLeft(), iV0))) {
            a1(i2);
            return;
        }
        a1(2);
        k1(i2, true);
        this.H0.c(i2);
    }

    private float i0(int i2) {
        float f2;
        float fS0;
        int i3 = this.N0;
        if (i2 > i3 || i3 == s0()) {
            int i4 = this.N0;
            f2 = i4 - i2;
            fS0 = this.d1 - i4;
        } else {
            int i5 = this.N0;
            f2 = i5 - i2;
            fS0 = i5 - s0();
        }
        return f2 / fS0;
    }

    private void i1() {
        WeakReference<V> weakReference = this.e1;
        if (weakReference != null) {
            j1(weakReference.get(), 0);
        }
        WeakReference<View> weakReference2 = this.f1;
        if (weakReference2 != null) {
            j1(weakReference2.get(), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j0() {
        return A0() && B0();
    }

    private void j1(View view, int i2) {
        if (view == null) {
            return;
        }
        k0(view, i2);
        if (!this.X && this.U0 != 6) {
            this.p1.put(i2, b0(view, k.a, 6));
        }
        if (this.P0 && B0() && this.U0 != 5) {
            H0(view, H.a.y, 5);
        }
        int i3 = this.U0;
        if (i3 == 3) {
            H0(view, H.a.x, this.X ? 4 : 6);
            return;
        }
        if (i3 == 4) {
            H0(view, H.a.w, this.X ? 3 : 6);
        } else {
            if (i3 != 6) {
                return;
            }
            H0(view, H.a.x, 4);
            H0(view, H.a.w, 3);
        }
    }

    private void k0(View view, int i2) {
        if (view == null) {
            return;
        }
        C1839d0.f0(view, 524288);
        C1839d0.f0(view, 262144);
        C1839d0.f0(view, 1048576);
        int i3 = this.p1.get(i2, -1);
        if (i3 != -1) {
            C1839d0.f0(view, i3);
            this.p1.delete(i2);
        }
    }

    private void k1(int i2, boolean z) {
        boolean zY0;
        ValueAnimator valueAnimator;
        if (i2 == 2 || this.G0 == (zY0 = y0()) || this.p0 == null) {
            return;
        }
        this.G0 = zY0;
        if (!z || (valueAnimator = this.I0) == null) {
            ValueAnimator valueAnimator2 = this.I0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.I0.cancel();
            }
            this.p0.h0(this.G0 ? g0() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            this.I0.reverse();
        } else {
            this.I0.setFloatValues(this.p0.C(), zY0 ? g0() : 1.0f);
            this.I0.start();
        }
    }

    private M l0(int i2) {
        return new f(i2);
    }

    private void l1(boolean z) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.e1;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.o1 != null) {
                    return;
                } else {
                    this.o1 = new HashMap(childCount);
                }
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (childAt != this.e1.get()) {
                    if (z) {
                        this.o1.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        if (this.Y) {
                            childAt.setImportantForAccessibility(4);
                        }
                    } else if (this.Y && (map = this.o1) != null && map.containsKey(childAt)) {
                        childAt.setImportantForAccessibility(this.o1.get(childAt).intValue());
                    }
                }
            }
            if (!z) {
                this.o1 = null;
            } else if (this.Y) {
                this.e1.get().sendAccessibilityEvent(8);
            }
        }
    }

    private void m0(Context context) {
        if (this.F0 == null) {
            return;
        }
        ax.p346v7.h hVar = new ax.p346v7.h(this.F0);
        this.p0 = hVar;
        hVar.U(context);
        ColorStateList colorStateList = this.q0;
        if (colorStateList != null) {
            this.p0.g0(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
        this.p0.setTint(typedValue.data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m1(boolean z) {
        V v;
        if (this.e1 != null) {
            d0();
            if (this.U0 != 4 || (v = this.e1.get()) == null) {
                return;
            }
            if (z) {
                Z0(4);
            } else {
                v.requestLayout();
            }
        }
    }

    private void n0() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(g0(), 1.0f);
        this.I0 = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.I0.addUpdateListener(new c());
    }

    public static <V extends View> BottomSheetBehavior<V> q0(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.f)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) layoutParams).f();
        if (cVarF instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) cVarF;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    private int r0(int i2, int i3, int i4, int i5) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, i3, i5);
        if (i4 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i4), 1073741824);
        }
        if (size != 0) {
            i4 = Math.min(size, i4);
        }
        return View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
    }

    private int v0(int i2) {
        if (i2 == 3) {
            return s0();
        }
        if (i2 == 4) {
            return this.N0;
        }
        if (i2 == 5) {
            return this.d1;
        }
        if (i2 == 6) {
            return this.L0;
        }
        throw new IllegalArgumentException("Invalid state to get top offset: " + i2);
    }

    private float w0() {
        VelocityTracker velocityTracker = this.j1;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.Z);
        return this.j1.getYVelocity(this.l1);
    }

    private boolean x0() {
        WeakReference<V> weakReference = this.e1;
        if (weakReference != null && weakReference.get() != null) {
            int[] iArr = new int[2];
            this.e1.get().getLocationOnScreen(iArr);
            if (iArr[1] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean y0() {
        if (this.U0 == 3) {
            return this.E0 || x0();
        }
        return false;
    }

    public boolean A0() {
        return this.P0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void B(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        h hVar = (h) parcelable;
        super.B(coordinatorLayout, v, hVar.a());
        J0(hVar);
        int i2 = hVar.Y;
        if (i2 == 1 || i2 == 2) {
            this.U0 = 4;
            this.V0 = 4;
        } else {
            this.U0 = i2;
            this.V0 = i2;
        }
    }

    public boolean B0() {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable C(CoordinatorLayout coordinatorLayout, V v) {
        return new h(super.C(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    public boolean D0() {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean E(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i3) {
        this.Y0 = 0;
        this.Z0 = false;
        return (i2 & 2) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0092  */
    /* JADX WARN: Code duplicated, block: B:51:0x00a9  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void G(CoordinatorLayout coordinatorLayout, V v, View view, int i2) {
        WeakReference<View> weakReference;
        int i3 = 3;
        if (v.getTop() == s0()) {
            a1(3);
            return;
        }
        if (!D0() || ((weakReference = this.h1) != null && view == weakReference.get() && this.Z0)) {
            if (this.Y0 > 0) {
                if (!this.X && v.getTop() > this.L0) {
                    i3 = 6;
                }
            } else if (this.P0 && e1(v, w0())) {
                i3 = 5;
            } else if (this.Y0 == 0) {
                int top = v.getTop();
                if (!this.X) {
                    int i4 = this.L0;
                    if (top < i4) {
                        if (top >= Math.abs(top - this.N0)) {
                            if (f1()) {
                                i3 = 4;
                            } else {
                                i3 = 6;
                            }
                        }
                    } else if (Math.abs(top - i4) < Math.abs(top - this.N0)) {
                        i3 = 6;
                    } else {
                        i3 = 4;
                    }
                } else if (Math.abs(top - this.K0) >= Math.abs(top - this.N0)) {
                    i3 = 4;
                }
            } else {
                if (!this.X) {
                    int top2 = v.getTop();
                    if (Math.abs(top2 - this.L0) < Math.abs(top2 - this.N0)) {
                        i3 = 6;
                    }
                }
                i3 = 4;
            }
            h1(v, i3, false);
            this.Z0 = false;
        }
    }

    public void G0(g gVar) {
        this.i1.remove(gVar);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean H(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.U0 == 1 && actionMasked == 0) {
            return true;
        }
        if (d1()) {
            this.W0.F(motionEvent);
        }
        if (actionMasked == 0) {
            I0();
        }
        if (this.j1 == null) {
            this.j1 = VelocityTracker.obtain();
        }
        this.j1.addMovement(motionEvent);
        if (d1() && actionMasked == 2 && !this.X0 && Math.abs(this.m1 - motionEvent.getY()) > this.W0.z()) {
            this.W0.b(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.X0;
    }

    public void L0(boolean z) {
        this.R0 = z;
    }

    public void M0(boolean z) {
        this.S0 = z;
    }

    public void N0(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.J0 = i2;
        k1(this.U0, true);
    }

    public void O0(boolean z) {
        if (this.X == z) {
            return;
        }
        this.X = z;
        if (this.e1 != null) {
            d0();
        }
        a1((this.X && this.U0 == 6) ? 3 : this.U0);
        k1(this.U0, true);
        i1();
    }

    public void P0(boolean z) {
        this.u0 = z;
    }

    public void Q0(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.M0 = f2;
        if (this.e1 != null) {
            f0();
        }
    }

    public void R0(boolean z) {
        if (this.P0 != z) {
            this.P0 = z;
            if (!z && this.U0 == 5) {
                Z0(4);
            }
            i1();
        }
    }

    public void S0(int i2) {
        this.s0 = i2;
    }

    public void T0(int i2) {
        this.r0 = i2;
    }

    public void U0(int i2) {
        V0(i2, false);
    }

    public final void V0(int i2, boolean z) {
        if (i2 == -1) {
            if (this.m0) {
                return;
            } else {
                this.m0 = true;
            }
        } else {
            if (!this.m0 && this.l0 == i2) {
                return;
            }
            this.m0 = false;
            this.l0 = Math.max(0, i2);
        }
        m1(z);
    }

    public void W0(int i2) {
        this.q = i2;
    }

    public void X0(int i2) {
        this.k0 = i2;
    }

    public void Y0(boolean z) {
        this.Q0 = z;
    }

    public void Z0(int i2) {
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATE_");
            sb.append(i2 == 1 ? "DRAGGING" : "SETTLING");
            sb.append(" should not be set externally.");
            throw new IllegalArgumentException(sb.toString());
        }
        if (!this.P0 && i2 == 5) {
            Log.w("BottomSheetBehavior", "Cannot set state: " + i2);
            return;
        }
        int i3 = (i2 == 6 && this.X && v0(i2) <= this.K0) ? 3 : i2;
        WeakReference<V> weakReference = this.e1;
        if (weakReference == null || weakReference.get() == null) {
            a1(i2);
        } else {
            V v = this.e1.get();
            K0(v, new a(v, i3));
        }
    }

    @Override // ax.p234o7.b
    public void a(ax.p130i.b bVar) {
        ax.p234o7.f fVar = this.k1;
        if (fVar == null) {
            return;
        }
        fVar.j(bVar);
    }

    void a1(int i2) {
        V v;
        if (this.U0 == i2) {
            return;
        }
        this.U0 = i2;
        if (i2 == 4 || i2 == 3 || i2 == 6 || (this.P0 && i2 == 5)) {
            this.V0 = i2;
        }
        WeakReference<V> weakReference = this.e1;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i2 == 3) {
            l1(true);
        } else if (i2 == 6 || i2 == 5 || i2 == 4) {
            l1(false);
        }
        k1(i2, true);
        for (int i3 = 0; i3 < this.i1.size(); i3++) {
            this.i1.get(i3).c(v, i2);
        }
        i1();
    }

    @Override // ax.p234o7.b
    public void b(ax.p130i.b bVar) {
        ax.p234o7.f fVar = this.k1;
        if (fVar == null) {
            return;
        }
        fVar.l(bVar);
    }

    @Override // ax.p234o7.b
    public void c() {
        ax.p234o7.f fVar = this.k1;
        if (fVar == null) {
            return;
        }
        ax.p130i.b bVarC = fVar.c();
        if (bVarC == null || Build.VERSION.SDK_INT < 34) {
            Z0(this.P0 ? 5 : 4);
        } else if (this.P0) {
            this.k1.h(bVarC, new b());
        } else {
            this.k1.i(bVarC, null);
            Z0(4);
        }
    }

    public void c0(g gVar) {
        if (this.i1.contains(gVar)) {
            return;
        }
        this.i1.add(gVar);
    }

    public boolean c1(long j, float f2) {
        return false;
    }

    @Override // ax.p234o7.b
    public void d() {
        ax.p234o7.f fVar = this.k1;
        if (fVar == null) {
            return;
        }
        fVar.f();
    }

    boolean e1(View view, float f2) {
        if (this.Q0) {
            return true;
        }
        if (B0() && view.getTop() >= this.N0) {
            return Math.abs((((float) view.getTop()) + (f2 * this.a1)) - ((float) this.N0)) / ((float) h0()) > 0.5f;
        }
        return false;
    }

    public boolean f1() {
        return false;
    }

    public boolean g1() {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void k(CoordinatorLayout.f fVar) {
        super.k(fVar);
        this.e1 = null;
        this.W0 = null;
        this.k1 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void n() {
        super.n();
        this.e1 = null;
        this.W0 = null;
        this.k1 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        int i2;
        ax.p179l0.c cVar;
        if (!v.isShown() || !this.R0) {
            this.X0 = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            I0();
        }
        if (this.j1 == null) {
            this.j1 = VelocityTracker.obtain();
        }
        this.j1.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            this.m1 = y;
            if (this.U0 != 2 && F0(coordinatorLayout, x, y)) {
                this.l1 = motionEvent.getPointerId(motionEvent.getActionIndex());
                if (!E0(coordinatorLayout, x, this.m1)) {
                    this.n1 = true;
                }
            }
            this.X0 = this.l1 == -1 && !coordinatorLayout.B(v, x, this.m1);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.n1 = false;
            this.l1 = -1;
            if (this.X0) {
                this.X0 = false;
                return false;
            }
        }
        if (!this.X0 && (cVar = this.W0) != null && cVar.P(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference = this.h1;
        View view = weakReference != null ? weakReference.get() : null;
        return (actionMasked != 2 || view == null || this.X0 || this.U0 == 1 || coordinatorLayout.B(view, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.W0 == null || (i2 = this.m1) == -1 || Math.abs(((float) i2) - motionEvent.getY()) <= ((float) this.W0.z())) ? false : true;
    }

    void o0(int i2) {
        V v = this.e1.get();
        if (v == null || this.i1.isEmpty()) {
            return;
        }
        float fI0 = i0(i2);
        for (int i3 = 0; i3 < this.i1.size(); i3++) {
            this.i1.get(i3).b(v, fI0);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, V v, int i2) {
        if (coordinatorLayout.getFitsSystemWindows() && !v.getFitsSystemWindows()) {
            v.setFitsSystemWindows(true);
        }
        if (this.e1 == null) {
            this.n0 = coordinatorLayout.getResources().getDimensionPixelSize(ax.R6.e.a);
            b1(v);
            C1839d0.C0(v, new com.google.android.material.bottomsheet.b(v));
            this.e1 = new WeakReference<>(v);
            this.k1 = new ax.p234o7.f(v);
            ax.p346v7.h hVar = this.p0;
            if (hVar != null) {
                v.setBackground(hVar);
                ax.p346v7.h hVar2 = this.p0;
                float elevation = this.O0;
                if (elevation == -1.0f) {
                    elevation = v.getElevation();
                }
                hVar2.f0(elevation);
            } else {
                ColorStateList colorStateList = this.q0;
                if (colorStateList != null) {
                    C1839d0.p0(v, colorStateList);
                }
            }
            i1();
            if (v.getImportantForAccessibility() == 0) {
                v.setImportantForAccessibility(1);
            }
        }
        if (this.W0 == null) {
            this.W0 = ax.p179l0.c.o(coordinatorLayout, this.q1);
        }
        int top = v.getTop();
        coordinatorLayout.I(v, i2);
        this.c1 = coordinatorLayout.getWidth();
        this.d1 = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.b1 = height;
        int iMin = this.d1;
        int i3 = iMin - height;
        int i4 = this.D0;
        if (i3 < i4) {
            if (this.y0) {
                int i5 = this.s0;
                if (i5 != -1) {
                    iMin = Math.min(iMin, i5);
                }
                this.b1 = iMin;
            } else {
                int iMin2 = iMin - i4;
                int i6 = this.s0;
                if (i6 != -1) {
                    iMin2 = Math.min(iMin2, i6);
                }
                this.b1 = iMin2;
            }
        }
        this.K0 = Math.max(0, this.d1 - this.b1);
        f0();
        d0();
        int i7 = this.U0;
        if (i7 == 3) {
            C1839d0.W(v, s0());
        } else if (i7 == 6) {
            C1839d0.W(v, this.L0);
        } else if (this.P0 && i7 == 5) {
            C1839d0.W(v, this.d1);
        } else if (i7 == 4) {
            C1839d0.W(v, this.N0);
        } else if (i7 == 1 || i7 == 2) {
            C1839d0.W(v, top - v.getTop());
        }
        k1(this.U0, false);
        this.h1 = new WeakReference<>(p0(v));
        for (int i8 = 0; i8 < this.i1.size(); i8++) {
            this.i1.get(i8).a(v);
        }
        return true;
    }

    View p0(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        if (view.isNestedScrollingEnabled()) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View viewP0 = p0(viewGroup.getChildAt(i2));
                if (viewP0 != null) {
                    return viewP0;
                }
            }
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean q(CoordinatorLayout coordinatorLayout, V v, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        v.measure(r0(i2, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, this.r0, marginLayoutParams.width), r0(i4, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, this.s0, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean s(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3) {
        WeakReference<View> weakReference;
        return D0() && (weakReference = this.h1) != null && view == weakReference.get() && (!(this.U0 == 3 || this.T0) || super.s(coordinatorLayout, v, view, f2, f3));
    }

    public int s0() {
        if (this.X) {
            return this.K0;
        }
        return Math.max(this.J0, this.y0 ? 0 : this.D0);
    }

    ax.p346v7.h t0() {
        return this.p0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void u(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.h1;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (!D0() || view == view2) {
            int top = v.getTop();
            int i5 = top - i3;
            if (i3 > 0) {
                if (!this.Z0 && !this.S0 && view == view2 && view.canScrollVertically(1)) {
                    this.T0 = true;
                    return;
                }
                if (i5 < s0()) {
                    int iS0 = top - s0();
                    iArr[1] = iS0;
                    C1839d0.W(v, -iS0);
                    a1(3);
                } else {
                    if (!this.R0) {
                        return;
                    }
                    iArr[1] = i3;
                    C1839d0.W(v, -i3);
                    a1(1);
                }
            } else if (i3 < 0) {
                boolean zCanScrollVertically = view.canScrollVertically(-1);
                if (!this.Z0 && !this.S0 && view == view2 && zCanScrollVertically) {
                    this.T0 = true;
                    return;
                }
                if (!zCanScrollVertically) {
                    if (i5 > this.N0 && !j0()) {
                        int i6 = top - this.N0;
                        iArr[1] = i6;
                        C1839d0.W(v, -i6);
                        a1(4);
                    } else {
                        if (!this.R0) {
                            return;
                        }
                        iArr[1] = i3;
                        C1839d0.W(v, -i3);
                        a1(1);
                    }
                }
            }
            o0(v.getTop());
            this.Y0 = i3;
            this.Z0 = true;
            this.T0 = false;
        }
    }

    public int u0() {
        return this.U0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
    }

    public boolean z0() {
        return this.u0;
    }
}
