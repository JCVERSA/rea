package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import ax.p051d0.H;

/* JADX INFO: loaded from: classes3.dex */
class p extends s {
    private final int e;
    private final int f;
    private final TimeInterpolator g;
    private AutoCompleteTextView h;
    private final View.OnClickListener i;
    private final View.OnFocusChangeListener j;
    private final AccessibilityManager.TouchExplorationStateChangeListener k;
    private boolean l;
    private boolean m;
    private boolean n;
    private long o;
    private AccessibilityManager p;
    private ValueAnimator q;
    private ValueAnimator r;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            p.this.r();
            p.this.r.start();
        }
    }

    p(r rVar) {
        super(rVar);
        this.i = new View.OnClickListener() { // from class: com.google.android.material.textfield.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.q.J();
            }
        };
        this.j = new View.OnFocusChangeListener() { // from class: com.google.android.material.textfield.m
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                p.y(this.a, view, z);
            }
        };
        this.k = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: com.google.android.material.textfield.n
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                p.w(this.a, z);
            }
        };
        this.o = Long.MAX_VALUE;
        Context context = rVar.getContext();
        int i = ax.R6.c.I;
        this.f = ax.p234o7.i.f(context, i, 67);
        this.e = ax.p234o7.i.f(rVar.getContext(), i, 50);
        this.g = ax.p234o7.i.g(rVar.getContext(), ax.R6.c.N, ax.S6.a.a);
    }

    public static /* synthetic */ void A(p pVar) {
        pVar.K();
        pVar.H(false);
    }

    private static AutoCompleteTextView D(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private ValueAnimator E(int i, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.g);
        valueAnimatorOfFloat.setDuration(i);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.i
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                p.x(this.a, valueAnimator);
            }
        });
        return valueAnimatorOfFloat;
    }

    private void F() {
        this.r = E(this.f, 0.0f, 1.0f);
        ValueAnimator valueAnimatorE = E(this.e, 1.0f, 0.0f);
        this.q = valueAnimatorE;
        valueAnimatorE.addListener(new a());
    }

    private boolean G() {
        long jUptimeMillis = SystemClock.uptimeMillis() - this.o;
        return jUptimeMillis < 0 || jUptimeMillis > 300;
    }

    private void H(boolean z) {
        if (this.n != z) {
            this.n = z;
            this.r.cancel();
            this.q.start();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void I() {
        this.h.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.material.textfield.j
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return p.z(this.q, view, motionEvent);
            }
        });
        this.h.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: com.google.android.material.textfield.k
            @Override // android.widget.AutoCompleteTextView.OnDismissListener
            public final void onDismiss() {
                p.A(this.a);
            }
        });
        this.h.setThreshold(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (this.h == null) {
            return;
        }
        if (G()) {
            this.m = false;
        }
        if (this.m) {
            this.m = false;
            return;
        }
        H(!this.n);
        if (!this.n) {
            this.h.dismissDropDown();
        } else {
            this.h.requestFocus();
            this.h.showDropDown();
        }
    }

    private void K() {
        this.m = true;
        this.o = SystemClock.uptimeMillis();
    }

    public static /* synthetic */ void v(p pVar) {
        boolean zIsPopupShowing = pVar.h.isPopupShowing();
        pVar.H(zIsPopupShowing);
        pVar.m = zIsPopupShowing;
    }

    public static /* synthetic */ void w(p pVar, boolean z) {
        AutoCompleteTextView autoCompleteTextView = pVar.h;
        if (autoCompleteTextView == null || q.a(autoCompleteTextView)) {
            return;
        }
        pVar.d.setImportantForAccessibility(z ? 2 : 1);
    }

    public static /* synthetic */ void x(p pVar, ValueAnimator valueAnimator) {
        pVar.getClass();
        pVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public static /* synthetic */ void y(p pVar, View view, boolean z) {
        pVar.l = z;
        pVar.r();
        if (z) {
            return;
        }
        pVar.H(false);
        pVar.m = false;
    }

    public static /* synthetic */ boolean z(p pVar, View view, MotionEvent motionEvent) {
        pVar.getClass();
        if (motionEvent.getAction() == 1) {
            if (pVar.G()) {
                pVar.m = false;
            }
            pVar.J();
            pVar.K();
        }
        return false;
    }

    @Override // com.google.android.material.textfield.s
    public void a(Editable editable) {
        if (this.p.isTouchExplorationEnabled() && q.a(this.h) && !this.d.hasFocus()) {
            this.h.dismissDropDown();
        }
        this.h.post(new Runnable() { // from class: com.google.android.material.textfield.o
            @Override // java.lang.Runnable
            public final void run() {
                p.v(this.q);
            }
        });
    }

    @Override // com.google.android.material.textfield.s
    int c() {
        return ax.R6.k.g;
    }

    @Override // com.google.android.material.textfield.s
    int d() {
        return ax.R6.f.j;
    }

    @Override // com.google.android.material.textfield.s
    View.OnFocusChangeListener e() {
        return this.j;
    }

    @Override // com.google.android.material.textfield.s
    View.OnClickListener f() {
        return this.i;
    }

    @Override // com.google.android.material.textfield.s
    public AccessibilityManager.TouchExplorationStateChangeListener h() {
        return this.k;
    }

    @Override // com.google.android.material.textfield.s
    boolean i(int i) {
        return i != 0;
    }

    @Override // com.google.android.material.textfield.s
    boolean j() {
        return true;
    }

    @Override // com.google.android.material.textfield.s
    boolean k() {
        return this.l;
    }

    @Override // com.google.android.material.textfield.s
    boolean l() {
        return true;
    }

    @Override // com.google.android.material.textfield.s
    boolean m() {
        return this.n;
    }

    @Override // com.google.android.material.textfield.s
    public void n(EditText editText) {
        this.h = D(editText);
        I();
        this.a.setErrorIconDrawable((Drawable) null);
        if (!q.a(editText) && this.p.isTouchExplorationEnabled()) {
            this.d.setImportantForAccessibility(2);
        }
        this.a.setEndIconVisible(true);
    }

    @Override // com.google.android.material.textfield.s
    public void o(View view, H h) {
        if (!q.a(this.h)) {
            h.m0(Spinner.class.getName());
        }
        if (h.W()) {
            h.x0(null);
        }
    }

    @Override // com.google.android.material.textfield.s
    @SuppressLint({"WrongConstant"})
    public void p(View view, AccessibilityEvent accessibilityEvent) {
        if (!this.p.isEnabled() || q.a(this.h)) {
            return;
        }
        boolean z = (accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 8) && this.n && !this.h.isPopupShowing();
        if (accessibilityEvent.getEventType() == 1 || z) {
            J();
            K();
        }
    }

    @Override // com.google.android.material.textfield.s
    void s() {
        F();
        this.p = (AccessibilityManager) this.c.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.s
    boolean t() {
        return true;
    }

    @Override // com.google.android.material.textfield.s
    @SuppressLint({"ClickableViewAccessibility"})
    void u() {
        AutoCompleteTextView autoCompleteTextView = this.h;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            this.h.setOnDismissListener(null);
        }
    }
}
