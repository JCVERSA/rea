package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p035c0.F0;
import ax.p035c0.I;
import ax.p051d0.H;
import ax.p202m7.B;
import ax.p202m7.w;
import ax.p202m7.z;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    private final int a;
    private final int b;
    private final int c;
    private final TimeInterpolator d;
    private final TimeInterpolator e;
    private final TimeInterpolator f;
    private final ViewGroup g;
    private final Context h;
    protected final s i;
    private final ax.p378x7.b j;
    private int k;
    private boolean l;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private List<q<B>> v;
    private Behavior w;
    private final AccessibilityManager x;
    private static final TimeInterpolator z = ax.S6.a.b;
    private static final TimeInterpolator A = ax.S6.a.a;
    private static final TimeInterpolator B = ax.S6.a.d;
    private static final int[] D = {ax.R6.c.U};
    private static final String E = BaseTransientBottomBar.class.getSimpleName();
    static final Handler C = new Handler(Looper.getMainLooper(), new h());
    private boolean m = false;
    private final Runnable n = new i();
    com.google.android.material.snackbar.a.b y = new l();

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final r r0 = new r(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void U(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.r0.c(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean J(View view) {
            return this.r0.a(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean o(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.r0.b(coordinatorLayout, view, motionEvent);
            return super.o(coordinatorLayout, view, motionEvent);
        }
    }

    class a extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        a(int i) {
            this.a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.R(this.a);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.i.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            BaseTransientBottomBar.this.i.setScaleX(fFloatValue);
            BaseTransientBottomBar.this.i.setScaleY(fFloatValue);
        }
    }

    class d extends AnimatorListenerAdapter {
        d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.S();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.j.a(BaseTransientBottomBar.this.c - BaseTransientBottomBar.this.a, BaseTransientBottomBar.this.a);
        }
    }

    class e implements ValueAnimator.AnimatorUpdateListener {
        e() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.i.setTranslationY(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    class f extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        f(int i) {
            this.a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.R(this.a);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.j.b(0, BaseTransientBottomBar.this.b);
        }
    }

    class g implements ValueAnimator.AnimatorUpdateListener {
        g() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.i.setTranslationY(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    class h implements Handler.Callback {
        h() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ((BaseTransientBottomBar) message.obj).b0();
                return true;
            }
            if (i != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).K(message.arg1);
            return true;
        }
    }

    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
            if (baseTransientBottomBar.i == null || baseTransientBottomBar.h == null) {
                return;
            }
            int iHeight = (B.a(BaseTransientBottomBar.this.h).height() - BaseTransientBottomBar.this.I()) + ((int) BaseTransientBottomBar.this.i.getTranslationY());
            if (iHeight >= BaseTransientBottomBar.this.s) {
                BaseTransientBottomBar baseTransientBottomBar2 = BaseTransientBottomBar.this;
                baseTransientBottomBar2.t = baseTransientBottomBar2.s;
                return;
            }
            ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.i.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(BaseTransientBottomBar.E, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            BaseTransientBottomBar baseTransientBottomBar3 = BaseTransientBottomBar.this;
            baseTransientBottomBar3.t = baseTransientBottomBar3.s;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += BaseTransientBottomBar.this.s - iHeight;
            BaseTransientBottomBar.this.i.requestLayout();
        }
    }

    class j implements I {
        j() {
        }

        @Override // ax.p035c0.I
        public F0 a(View view, F0 f0) {
            BaseTransientBottomBar.this.o = f0.i();
            BaseTransientBottomBar.this.p = f0.j();
            BaseTransientBottomBar.this.q = f0.k();
            BaseTransientBottomBar.this.h0();
            return f0;
        }
    }

    class k extends C1832a {
        k() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.a(1048576);
            h.r0(true);
        }

        @Override // ax.p035c0.C1832a
        public boolean j(View view, int i, Bundle bundle) {
            if (i != 1048576) {
                return super.j(view, i, bundle);
            }
            BaseTransientBottomBar.this.x();
            return true;
        }
    }

    class l implements com.google.android.material.snackbar.a.b {
        l() {
        }

        @Override // com.google.android.material.snackbar.a.b
        public void a(int i) {
            Handler handler = BaseTransientBottomBar.C;
            handler.sendMessage(handler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.a.b
        public void c() {
            Handler handler = BaseTransientBottomBar.C;
            handler.sendMessage(handler.obtainMessage(0, BaseTransientBottomBar.this));
        }
    }

    class m implements Runnable {
        m() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar.this.R(3);
        }
    }

    class n implements SwipeDismissBehavior.c {
        n() {
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.c
        public void a(View view) {
            if (view.getParent() != null) {
                view.setVisibility(8);
            }
            BaseTransientBottomBar.this.y(0);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.c
        public void b(int i) {
            if (i == 0) {
                com.google.android.material.snackbar.a.c().l(BaseTransientBottomBar.this.y);
            } else if (i == 1 || i == 2) {
                com.google.android.material.snackbar.a.c().k(BaseTransientBottomBar.this.y);
            }
        }
    }

    class o implements Runnable {
        o() {
        }

        @Override // java.lang.Runnable
        public void run() {
            s sVar = BaseTransientBottomBar.this.i;
            if (sVar == null) {
                return;
            }
            if (sVar.getParent() != null) {
                BaseTransientBottomBar.this.i.setVisibility(0);
            }
            if (BaseTransientBottomBar.this.i.getAnimationMode() == 1) {
                BaseTransientBottomBar.this.d0();
            } else {
                BaseTransientBottomBar.this.f0();
            }
        }
    }

    class p extends AnimatorListenerAdapter {
        p() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.S();
        }
    }

    public static abstract class q<B> {
        public void a(B b, int i) {
        }

        public void b(B b) {
        }
    }

    public static class r {
        private com.google.android.material.snackbar.a.b a;

        public r(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.Q(0.1f);
            swipeDismissBehavior.O(0.6f);
            swipeDismissBehavior.R(0);
        }

        public boolean a(View view) {
            return view instanceof s;
        }

        public void b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.B(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    com.google.android.material.snackbar.a.c().k(this.a);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                com.google.android.material.snackbar.a.c().l(this.a);
            }
        }

        public void c(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.a = baseTransientBottomBar.y;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static class s extends FrameLayout {
        private static final View.OnTouchListener u0 = new a();
        ax.p346v7.l k0;
        private int l0;
        private final float m0;
        private final float n0;
        private final int o0;
        private final int p0;
        private BaseTransientBottomBar<?> q;
        private ColorStateList q0;
        private PorterDuff.Mode r0;
        private Rect s0;
        private boolean t0;

        class a implements View.OnTouchListener {
            a() {
            }

            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        protected s(Context context, AttributeSet attributeSet) {
            super(ax.C7.a.d(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, ax.R6.m.X4);
            int i = ax.R6.m.e5;
            if (typedArrayObtainStyledAttributes.hasValue(i)) {
                setElevation(typedArrayObtainStyledAttributes.getDimensionPixelSize(i, 0));
            }
            this.l0 = typedArrayObtainStyledAttributes.getInt(ax.R6.m.a5, 0);
            if (typedArrayObtainStyledAttributes.hasValue(ax.R6.m.g5) || typedArrayObtainStyledAttributes.hasValue(ax.R6.m.h5)) {
                this.k0 = ax.p346v7.l.e(context2, attributeSet, 0, 0).m();
            }
            this.m0 = typedArrayObtainStyledAttributes.getFloat(ax.R6.m.b5, 1.0f);
            setBackgroundTintList(ax.p282r7.c.a(context2, typedArrayObtainStyledAttributes, ax.R6.m.c5));
            setBackgroundTintMode(z.i(typedArrayObtainStyledAttributes.getInt(ax.R6.m.d5, -1), PorterDuff.Mode.SRC_IN));
            this.n0 = typedArrayObtainStyledAttributes.getFloat(ax.R6.m.Z4, 1.0f);
            this.o0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(ax.R6.m.Y4, -1);
            this.p0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(ax.R6.m.f5, -1);
            typedArrayObtainStyledAttributes.recycle();
            setOnTouchListener(u0);
            setFocusable(true);
            if (getBackground() == null) {
                setBackground(d());
            }
        }

        private Drawable d() {
            int iL = ax.p106g7.a.l(this, ax.R6.c.i, ax.R6.c.f, getBackgroundOverlayColorAlpha());
            ax.p346v7.l lVar = this.k0;
            Drawable drawableW = lVar != null ? BaseTransientBottomBar.w(iL, lVar) : BaseTransientBottomBar.v(iL, getResources());
            if (this.q0 == null) {
                return ax.U.a.r(drawableW);
            }
            Drawable drawableR = ax.U.a.r(drawableW);
            drawableR.setTintList(this.q0);
            return drawableR;
        }

        private void e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.s0 = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.q = baseTransientBottomBar;
        }

        void c(ViewGroup viewGroup) {
            this.t0 = true;
            viewGroup.addView(this);
            this.t0 = false;
        }

        float getActionTextColorAlpha() {
            return this.n0;
        }

        int getAnimationMode() {
            return this.l0;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.m0;
        }

        int getMaxInlineActionWidth() {
            return this.p0;
        }

        int getMaxWidth() {
            return this.o0;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.q;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.O();
            }
            requestApplyInsets();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.q;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.P();
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.q;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.Q();
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.o0 > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = this.o0;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                }
            }
        }

        void setAnimationMode(int i) {
            this.l0 = i;
        }

        @Override // android.view.View
        public void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null && this.q0 != null) {
                drawable = ax.U.a.r(drawable.mutate());
                drawable.setTintList(this.q0);
                drawable.setTintMode(this.r0);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList) {
            this.q0 = colorStateList;
            if (getBackground() != null) {
                Drawable drawableR = ax.U.a.r(getBackground().mutate());
                drawableR.setTintList(colorStateList);
                drawableR.setTintMode(this.r0);
                if (drawableR != getBackground()) {
                    super.setBackgroundDrawable(drawableR);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.r0 = mode;
            if (getBackground() != null) {
                Drawable drawableR = ax.U.a.r(getBackground().mutate());
                drawableR.setTintMode(mode);
                if (drawableR != getBackground()) {
                    super.setBackgroundDrawable(drawableR);
                }
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (this.t0 || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                return;
            }
            e((ViewGroup.MarginLayoutParams) layoutParams);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.q;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.h0();
            }
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : u0);
            super.setOnClickListener(onClickListener);
        }
    }

    protected BaseTransientBottomBar(Context context, ViewGroup viewGroup, View view, ax.p378x7.b bVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.g = viewGroup;
        this.j = bVar;
        this.h = context;
        w.a(context);
        s sVar = (s) LayoutInflater.from(context).inflate(F(), viewGroup, false);
        this.i = sVar;
        sVar.setBaseTransientBottomBar(this);
        if (view instanceof SnackbarContentLayout) {
            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) view;
            snackbarContentLayout.c(sVar.getActionTextColorAlpha());
            snackbarContentLayout.setMaxInlineActionWidth(sVar.getMaxInlineActionWidth());
        }
        sVar.addView(view);
        sVar.setAccessibilityLiveRegion(1);
        sVar.setImportantForAccessibility(1);
        sVar.setFitsSystemWindows(true);
        C1839d0.v0(sVar, new j());
        C1839d0.l0(sVar, new k());
        this.x = (AccessibilityManager) context.getSystemService("accessibility");
        int i2 = ax.R6.c.D;
        this.c = ax.p234o7.i.f(context, i2, 250);
        this.a = ax.p234o7.i.f(context, i2, 150);
        this.b = ax.p234o7.i.f(context, ax.R6.c.E, 75);
        int i3 = ax.R6.c.M;
        this.d = ax.p234o7.i.g(context, i3, A);
        this.f = ax.p234o7.i.g(context, i3, B);
        this.e = ax.p234o7.i.g(context, i3, z);
    }

    private ValueAnimator E(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.f);
        valueAnimatorOfFloat.addUpdateListener(new c());
        return valueAnimatorOfFloat;
    }

    private int G() {
        int height = this.i.getHeight();
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int I() {
        int[] iArr = new int[2];
        this.i.getLocationInWindow(iArr);
        return iArr[1] + this.i.getHeight();
    }

    private boolean N() {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.f) && (((CoordinatorLayout.f) layoutParams).f() instanceof SwipeDismissBehavior);
    }

    private void T() {
        this.r = u();
        h0();
    }

    private void X(CoordinatorLayout.f fVar) {
        SwipeDismissBehavior<? extends View> swipeDismissBehaviorD = this.w;
        if (swipeDismissBehaviorD == null) {
            swipeDismissBehaviorD = D();
        }
        if (swipeDismissBehaviorD instanceof Behavior) {
            ((Behavior) swipeDismissBehaviorD).U(this);
        }
        swipeDismissBehaviorD.P(new n());
        fVar.o(swipeDismissBehaviorD);
        if (A() == null) {
            fVar.g = 80;
        }
    }

    private boolean Z() {
        return this.s > 0 && !this.l && N();
    }

    private void c0() {
        if (Y()) {
            s();
            return;
        }
        if (this.i.getParent() != null) {
            this.i.setVisibility(0);
        }
        S();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0() {
        ValueAnimator valueAnimatorZ = z(0.0f, 1.0f);
        ValueAnimator valueAnimatorE = E(0.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimatorZ, valueAnimatorE);
        animatorSet.setDuration(this.a);
        animatorSet.addListener(new p());
        animatorSet.start();
    }

    private void e0(int i2) {
        ValueAnimator valueAnimatorZ = z(1.0f, 0.0f);
        valueAnimatorZ.setDuration(this.b);
        valueAnimatorZ.addListener(new a(i2));
        valueAnimatorZ.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0() {
        int iG = G();
        this.i.setTranslationY(iG);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(iG, 0);
        valueAnimator.setInterpolator(this.e);
        valueAnimator.setDuration(this.c);
        valueAnimator.addListener(new d());
        valueAnimator.addUpdateListener(new e());
        valueAnimator.start();
    }

    private void g0(int i2) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, G());
        valueAnimator.setInterpolator(this.e);
        valueAnimator.setDuration(this.c);
        valueAnimator.addListener(new f(i2));
        valueAnimator.addUpdateListener(new g());
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0() {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            Log.w(E, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        if (this.i.s0 == null) {
            Log.w(E, "Unable to update margins because original view margins are not set");
            return;
        }
        if (this.i.getParent() == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int i2 = this.i.s0.bottom + (A() != null ? this.r : this.o);
        int i3 = this.i.s0.left + this.p;
        int i4 = this.i.s0.right + this.q;
        int i5 = this.i.s0.top;
        boolean z2 = (marginLayoutParams.bottomMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4 && marginLayoutParams.topMargin == i5) ? false : true;
        if (z2) {
            marginLayoutParams.bottomMargin = i2;
            marginLayoutParams.leftMargin = i3;
            marginLayoutParams.rightMargin = i4;
            marginLayoutParams.topMargin = i5;
            this.i.requestLayout();
        }
        if ((z2 || this.t != this.s) && Build.VERSION.SDK_INT >= 29 && Z()) {
            this.i.removeCallbacks(this.n);
            this.i.post(this.n);
        }
    }

    private void t(int i2) {
        if (this.i.getAnimationMode() == 1) {
            e0(i2);
        } else {
            g0(i2);
        }
    }

    private int u() {
        if (A() == null) {
            return 0;
        }
        int[] iArr = new int[2];
        A().getLocationOnScreen(iArr);
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.g.getLocationOnScreen(iArr2);
        return (iArr2[1] + this.g.getHeight()) - i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GradientDrawable v(int i2, Resources resources) {
        float dimension = resources.getDimension(ax.R6.e.t0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(i2);
        return gradientDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ax.p346v7.h w(int i2, ax.p346v7.l lVar) {
        ax.p346v7.h hVar = new ax.p346v7.h(lVar);
        hVar.g0(ColorStateList.valueOf(i2));
        return hVar;
    }

    private ValueAnimator z(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.d);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    public View A() {
        return null;
    }

    public Context B() {
        return this.h;
    }

    public int C() {
        return this.k;
    }

    protected SwipeDismissBehavior<? extends View> D() {
        return new Behavior();
    }

    protected int F() {
        return J() ? ax.R6.i.v : ax.R6.i.b;
    }

    public View H() {
        return this.i;
    }

    protected boolean J() {
        TypedArray typedArrayObtainStyledAttributes = this.h.obtainStyledAttributes(D);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    final void K(int i2) {
        if (Y() && this.i.getVisibility() == 0) {
            t(i2);
        } else {
            R(i2);
        }
    }

    public boolean L() {
        return com.google.android.material.snackbar.a.c().e(this.y);
    }

    public boolean M() {
        return com.google.android.material.snackbar.a.c().f(this.y);
    }

    void O() {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 29 || (rootWindowInsets = this.i.getRootWindowInsets()) == null) {
            return;
        }
        this.s = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
        h0();
    }

    void P() {
        if (M()) {
            C.post(new m());
        }
    }

    void Q() {
        if (this.u) {
            c0();
            this.u = false;
        }
    }

    void R(int i2) {
        com.google.android.material.snackbar.a.c().i(this.y);
        List<q<B>> list = this.v;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.v.get(size).a(this, i2);
            }
        }
        ViewParent parent = this.i.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.i);
        }
    }

    void S() {
        com.google.android.material.snackbar.a.c().j(this.y);
        List<q<B>> list = this.v;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.v.get(size).b(this);
            }
        }
    }

    public B U(int i2) {
        this.i.setAnimationMode(i2);
        return this;
    }

    public B V(int i2) {
        this.k = i2;
        return this;
    }

    public B W(boolean z2) {
        this.l = z2;
        return this;
    }

    boolean Y() {
        AccessibilityManager accessibilityManager = this.x;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    public void a0() {
        com.google.android.material.snackbar.a.c().n(C(), this.y);
    }

    final void b0() {
        if (this.i.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                X((CoordinatorLayout.f) layoutParams);
            }
            this.i.c(this.g);
            T();
            this.i.setVisibility(4);
        }
        if (this.i.isLaidOut()) {
            c0();
        } else {
            this.u = true;
        }
    }

    public B r(q<B> qVar) {
        if (qVar == null) {
            return this;
        }
        if (this.v == null) {
            this.v = new ArrayList();
        }
        this.v.add(qVar);
        return this;
    }

    void s() {
        this.i.post(new o());
    }

    public void x() {
        y(3);
    }

    protected void y(int i2) {
        com.google.android.material.snackbar.a.c().b(this.y, i2);
    }
}
