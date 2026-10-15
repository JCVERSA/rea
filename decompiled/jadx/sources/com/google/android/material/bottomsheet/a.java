package com.google.android.material.bottomsheet;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.g;
import ax.R6.i;
import ax.R6.l;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p035c0.C1866r0;
import ax.p035c0.F0;
import ax.p035c0.I;
import ax.p051d0.H;
import ax.p202m7.z;
import ax.p210n.x;
import ax.p346v7.h;

/* JADX INFO: loaded from: classes3.dex */
public class a extends x {
    private BottomSheetBehavior<FrameLayout> l0;
    private FrameLayout m0;
    private CoordinatorLayout n0;
    private FrameLayout o0;
    boolean p0;
    boolean q0;
    private boolean r0;
    private boolean s0;
    private f t0;
    private boolean u0;
    private ax.p234o7.c v0;
    private BottomSheetBehavior.g w0;

    /* JADX INFO: renamed from: com.google.android.material.bottomsheet.a$a, reason: collision with other inner class name */
    class C0569a implements I {
        C0569a() {
        }

        @Override // ax.p035c0.I
        public F0 a(View view, F0 f0) {
            if (a.this.t0 != null) {
                a.this.l0.G0(a.this.t0);
            }
            if (f0 != null) {
                a aVar = a.this;
                aVar.t0 = new f(aVar.o0, f0, null);
                a.this.t0.e(a.this.getWindow());
                a.this.l0.c0(a.this.t0);
            }
            return f0;
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a aVar = a.this;
            if (aVar.q0 && aVar.isShowing() && a.this.p()) {
                a.this.cancel();
            }
        }
    }

    class c extends C1832a {
        c() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            if (!a.this.q0) {
                h.r0(false);
            } else {
                h.a(1048576);
                h.r0(true);
            }
        }

        @Override // ax.p035c0.C1832a
        public boolean j(View view, int i, Bundle bundle) {
            if (i == 1048576) {
                a aVar = a.this;
                if (aVar.q0) {
                    aVar.cancel();
                    return true;
                }
            }
            return super.j(view, i, bundle);
        }
    }

    class d implements View.OnTouchListener {
        d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    class e extends BottomSheetBehavior.g {
        e() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        public void b(View view, float f) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        public void c(View view, int i) {
            if (i == 5) {
                a.this.cancel();
            }
        }
    }

    private static class f extends BottomSheetBehavior.g {
        private final Boolean a;
        private final F0 b;
        private Window c;
        private boolean d;

        private f(View view, F0 f0) {
            this.b = f0;
            h hVarT0 = BottomSheetBehavior.q0(view).t0();
            ColorStateList colorStateListB = hVarT0 != null ? hVarT0.B() : view.getBackgroundTintList();
            if (colorStateListB != null) {
                this.a = Boolean.valueOf(ax.p106g7.a.i(colorStateListB.getDefaultColor()));
                return;
            }
            Integer numD = z.d(view);
            if (numD != null) {
                this.a = Boolean.valueOf(ax.p106g7.a.i(numD.intValue()));
            } else {
                this.a = null;
            }
        }

        /* synthetic */ f(View view, F0 f0, C0569a c0569a) {
            this(view, f0);
        }

        private void d(View view) {
            if (view.getTop() < this.b.l()) {
                Window window = this.c;
                if (window != null) {
                    Boolean bool = this.a;
                    ax.p202m7.d.f(window, bool == null ? this.d : bool.booleanValue());
                }
                view.setPadding(view.getPaddingLeft(), this.b.l() - view.getTop(), view.getPaddingRight(), view.getPaddingBottom());
                return;
            }
            if (view.getTop() != 0) {
                Window window2 = this.c;
                if (window2 != null) {
                    ax.p202m7.d.f(window2, this.d);
                }
                view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
            }
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        void a(View view) {
            d(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        public void b(View view, float f) {
            d(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        public void c(View view, int i) {
            d(view);
        }

        void e(Window window) {
            if (this.c == window) {
                return;
            }
            this.c = window;
            if (window != null) {
                this.d = C1866r0.a(window, window.getDecorView()).b();
            }
        }
    }

    public a(Context context, int i) {
        super(context, f(context, i));
        this.q0 = true;
        this.r0 = true;
        this.w0 = new e();
        h(1);
        o();
    }

    private static int f(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(ax.R6.c.b, typedValue, true) ? typedValue.resourceId : l.f;
    }

    private FrameLayout m() {
        if (this.m0 == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), i.a, null);
            this.m0 = frameLayout;
            this.n0 = (CoordinatorLayout) frameLayout.findViewById(g.e);
            FrameLayout frameLayout2 = (FrameLayout) this.m0.findViewById(g.f);
            this.o0 = frameLayout2;
            BottomSheetBehavior<FrameLayout> bottomSheetBehaviorQ0 = BottomSheetBehavior.q0(frameLayout2);
            this.l0 = bottomSheetBehaviorQ0;
            bottomSheetBehaviorQ0.c0(this.w0);
            this.l0.R0(this.q0);
            this.v0 = new ax.p234o7.c(this.l0, this.o0);
        }
        return this.m0;
    }

    private void o() {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{ax.R6.c.n});
        this.u0 = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void q() {
        ax.p234o7.c cVar = this.v0;
        if (cVar == null) {
            return;
        }
        if (this.q0) {
            cVar.b();
        } else {
            cVar.d();
        }
    }

    private View r(int i, View view, ViewGroup.LayoutParams layoutParams) {
        m();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.m0.findViewById(g.e);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, (ViewGroup) coordinatorLayout, false);
        }
        if (this.u0) {
            C1839d0.v0(this.m0, new C0569a());
        }
        this.o0.removeAllViews();
        if (layoutParams == null) {
            this.o0.addView(view);
        } else {
            this.o0.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(g.W).setOnClickListener(new b());
        C1839d0.l0(this.o0, new c());
        this.o0.setOnTouchListener(new d());
        return this.m0;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        BottomSheetBehavior<FrameLayout> bottomSheetBehaviorN = n();
        if (!this.p0 || bottomSheetBehaviorN.u0() == 5) {
            super.cancel();
        } else {
            bottomSheetBehaviorN.Z0(5);
        }
    }

    public BottomSheetBehavior<FrameLayout> n() {
        if (this.l0 == null) {
            m();
        }
        return this.l0;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        if (window != null) {
            boolean z = this.u0 && Color.alpha(window.getNavigationBarColor()) < 255;
            FrameLayout frameLayout = this.m0;
            if (frameLayout != null) {
                frameLayout.setFitsSystemWindows(!z);
            }
            CoordinatorLayout coordinatorLayout = this.n0;
            if (coordinatorLayout != null) {
                coordinatorLayout.setFitsSystemWindows(!z);
            }
            C1866r0.b(window, !z);
            f fVar = this.t0;
            if (fVar != null) {
                fVar.e(window);
            }
        }
        q();
    }

    @Override // ax.p210n.x, ax.p130i.n, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(0);
            window.addFlags(Integer.MIN_VALUE);
            if (Build.VERSION.SDK_INT < 23) {
                window.addFlags(67108864);
            }
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        f fVar = this.t0;
        if (fVar != null) {
            fVar.e(null);
        }
        ax.p234o7.c cVar = this.v0;
        if (cVar != null) {
            cVar.d();
        }
    }

    @Override // ax.p130i.n, android.app.Dialog
    protected void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.l0;
        if (bottomSheetBehavior == null || bottomSheetBehavior.u0() != 5) {
            return;
        }
        this.l0.Z0(4);
    }

    boolean p() {
        if (!this.s0) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R.attr.windowCloseOnTouchOutside});
            this.r0 = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
            this.s0 = true;
        }
        return this.r0;
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.q0 != z) {
            this.q0 = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.l0;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.R0(z);
            }
            if (getWindow() != null) {
                q();
            }
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.q0) {
            this.q0 = true;
        }
        this.r0 = z;
        this.s0 = true;
    }

    @Override // ax.p210n.x, ax.p130i.n, android.app.Dialog
    public void setContentView(int i) {
        super.setContentView(r(i, null, null));
    }

    @Override // ax.p210n.x, ax.p130i.n, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(r(0, view, null));
    }

    @Override // ax.p210n.x, ax.p130i.n, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(r(0, view, layoutParams));
    }
}
