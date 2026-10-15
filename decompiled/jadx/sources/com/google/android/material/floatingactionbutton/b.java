package com.google.android.material.floatingactionbutton;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import ax.S6.g;
import ax.p234o7.i;
import ax.p346v7.h;
import ax.p346v7.l;
import ax.p346v7.o;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
class b {
    static final TimeInterpolator B = ax.S6.a.c;
    private static final int C = ax.R6.c.D;
    private static final int D = ax.R6.c.M;
    private static final int E = ax.R6.c.E;
    private static final int F = ax.R6.c.K;
    static final int[] G = {R.attr.state_pressed, R.attr.state_enabled};
    static final int[] H = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    static final int[] I = {R.attr.state_focused, R.attr.state_enabled};
    static final int[] J = {R.attr.state_hovered, R.attr.state_enabled};
    static final int[] K = {R.attr.state_enabled};
    static final int[] L = new int[0];
    private ViewTreeObserver.OnPreDrawListener A;
    l a;
    h b;
    Drawable c;
    Drawable d;
    boolean e;
    float g;
    float h;
    float i;
    int j;
    private StateListAnimator k;
    private Animator l;
    private ax.S6.h m;
    private ax.S6.h n;
    private int p;
    private ArrayList<Animator.AnimatorListener> r;
    private ArrayList<Animator.AnimatorListener> s;
    private ArrayList<e> t;
    final FloatingActionButton u;
    final ax.p330u7.b v;
    boolean f = true;
    private float o = 1.0f;
    private int q = 0;
    private final Rect w = new Rect();
    private final RectF x = new RectF();
    private final RectF y = new RectF();
    private final Matrix z = new Matrix();

    class a extends AnimatorListenerAdapter {
        private boolean a;
        final /* synthetic */ boolean b;
        final /* synthetic */ f c;

        a(boolean z, f fVar) {
            this.b = z;
            this.c = fVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.q = 0;
            b.this.l = null;
            if (this.a) {
                return;
            }
            FloatingActionButton floatingActionButton = b.this.u;
            boolean z = this.b;
            floatingActionButton.b(z ? 8 : 4, z);
            f fVar = this.c;
            if (fVar != null) {
                fVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.u.b(0, this.b);
            b.this.q = 1;
            b.this.l = animator;
            this.a = false;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.floatingactionbutton.b$b, reason: collision with other inner class name */
    class C0576b extends AnimatorListenerAdapter {
        final /* synthetic */ boolean a;
        final /* synthetic */ f b;

        C0576b(boolean z, f fVar) {
            this.a = z;
            this.b = fVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.q = 0;
            b.this.l = null;
            f fVar = this.b;
            if (fVar != null) {
                fVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.u.b(0, this.a);
            b.this.q = 2;
            b.this.l = animator;
        }
    }

    class c extends g {
        c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            b.this.o = f;
            return super.evaluate(f, matrix, matrix2);
        }
    }

    class d implements TypeEvaluator<Float> {
        final FloatEvaluator a = new FloatEvaluator();

        d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f, Float f2, Float f3) {
            float fFloatValue = this.a.evaluate(f, (Number) f2, (Number) f3).floatValue();
            if (fFloatValue < 0.1f) {
                fFloatValue = 0.0f;
            }
            return Float.valueOf(fFloatValue);
        }
    }

    interface e {
        void a();

        void b();
    }

    interface f {
        void a();

        void b();
    }

    b(FloatingActionButton floatingActionButton, ax.p330u7.b bVar) {
        this.u = floatingActionButton;
        this.v = bVar;
    }

    private boolean W() {
        return this.u.isLaidOut() && !this.u.isInEditMode();
    }

    public static /* synthetic */ void a(b bVar, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Matrix matrix, ValueAnimator valueAnimator) {
        bVar.getClass();
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        bVar.u.setAlpha(ax.S6.a.b(f2, f3, 0.0f, 0.2f, fFloatValue));
        bVar.u.setScaleX(ax.S6.a.a(f4, f5, fFloatValue));
        bVar.u.setScaleY(ax.S6.a.a(f6, f5, fFloatValue));
        bVar.o = ax.S6.a.a(f7, f8, fFloatValue);
        bVar.h(ax.S6.a.a(f7, f8, fFloatValue), matrix);
        bVar.u.setImageMatrix(matrix);
    }

    private void b0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new d());
    }

    private void h(float f2, Matrix matrix) {
        matrix.reset();
        Drawable drawable = this.u.getDrawable();
        if (drawable == null || this.p == 0) {
            return;
        }
        RectF rectF = this.x;
        RectF rectF2 = this.y;
        rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        int i = this.p;
        rectF2.set(0.0f, 0.0f, i, i);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i2 = this.p;
        matrix.postScale(f2, f2, i2 / 2.0f, i2 / 2.0f);
    }

    private AnimatorSet i(ax.S6.h hVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.u, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        hVar.e("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.u, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        hVar.e("scale").a(objectAnimatorOfFloat2);
        b0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.u, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        hVar.e("scale").a(objectAnimatorOfFloat3);
        b0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        h(f4, this.z);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.u, new ax.S6.f(), new c(), new Matrix(this.z));
        hVar.e("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        ax.S6.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    private AnimatorSet j(final float f2, final float f3, final float f4, int i, int i2) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        final float alpha = this.u.getAlpha();
        final float scaleX = this.u.getScaleX();
        final float scaleY = this.u.getScaleY();
        final float f5 = this.o;
        final Matrix matrix = new Matrix(this.z);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.floatingactionbutton.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                b.a(this.a, alpha, f2, scaleX, f3, scaleY, f5, f4, matrix, valueAnimator);
            }
        });
        arrayList.add(valueAnimatorOfFloat);
        ax.S6.b.a(animatorSet, arrayList);
        animatorSet.setDuration(i.f(this.u.getContext(), i, this.u.getContext().getResources().getInteger(ax.R6.h.b)));
        animatorSet.setInterpolator(i.g(this.u.getContext(), i2, ax.S6.a.b));
        return animatorSet;
    }

    private StateListAnimator k(float f2, float f3, float f4) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(G, l(f2, f4));
        stateListAnimator.addState(H, l(f2, f3));
        stateListAnimator.addState(I, l(f2, f3));
        stateListAnimator.addState(J, l(f2, f3));
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.u, "elevation", f2).setDuration(0L));
        int i = Build.VERSION.SDK_INT;
        if (i >= 22 && i <= 24) {
            FloatingActionButton floatingActionButton = this.u;
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
        }
        arrayList.add(ObjectAnimator.ofFloat(this.u, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
        animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
        animatorSet.setInterpolator(B);
        stateListAnimator.addState(K, animatorSet);
        stateListAnimator.addState(L, l(0.0f, 0.0f));
        return stateListAnimator;
    }

    private Animator l(float f2, float f3) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.u, "elevation", f2).setDuration(0L)).with(ObjectAnimator.ofFloat(this.u, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f3).setDuration(100L));
        animatorSet.setInterpolator(B);
        return animatorSet;
    }

    void A() {
        h hVar = this.b;
        if (hVar != null) {
            ax.p346v7.i.f(this.u, hVar);
        }
    }

    void B() {
        Z();
    }

    void C() {
        ViewTreeObserver viewTreeObserver = this.u.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.A;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.A = null;
        }
    }

    void D() {
        if (!this.u.isEnabled()) {
            this.u.setElevation(0.0f);
            this.u.setTranslationZ(0.0f);
            return;
        }
        this.u.setElevation(this.g);
        if (this.u.isPressed()) {
            this.u.setTranslationZ(this.i);
        } else if (this.u.isFocused() || this.u.isHovered()) {
            this.u.setTranslationZ(this.h);
        } else {
            this.u.setTranslationZ(0.0f);
        }
    }

    void E(float f2, float f3, float f4) {
        if (Build.VERSION.SDK_INT == 21) {
            this.u.refreshDrawableState();
        } else if (this.u.getStateListAnimator() == this.k) {
            StateListAnimator stateListAnimatorK = k(f2, f3, f4);
            this.k = stateListAnimatorK;
            this.u.setStateListAnimator(stateListAnimatorK);
        }
        if (V()) {
            Z();
        }
    }

    void F(Rect rect) {
        ax.p019b0.g.h(this.d, "Didn't initialize content background");
        if (!V()) {
            this.v.a(this.d);
        } else {
            this.v.a(new InsetDrawable(this.d, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    void G() {
        ArrayList<e> arrayList = this.t;
        if (arrayList != null) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                e eVar = arrayList.get(i);
                i++;
                eVar.a();
            }
        }
    }

    void H() {
        ArrayList<e> arrayList = this.t;
        if (arrayList != null) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                e eVar = arrayList.get(i);
                i++;
                eVar.b();
            }
        }
    }

    void I(ColorStateList colorStateList) {
        h hVar = this.b;
        if (hVar != null) {
            hVar.setTintList(colorStateList);
        }
    }

    void J(PorterDuff.Mode mode) {
        h hVar = this.b;
        if (hVar != null) {
            hVar.setTintMode(mode);
        }
    }

    final void K(float f2) {
        if (this.g != f2) {
            this.g = f2;
            E(f2, this.h, this.i);
        }
    }

    void L(boolean z) {
        this.e = z;
    }

    final void M(ax.S6.h hVar) {
        this.n = hVar;
    }

    final void N(float f2) {
        if (this.h != f2) {
            this.h = f2;
            E(this.g, f2, this.i);
        }
    }

    final void O(float f2) {
        this.o = f2;
        Matrix matrix = this.z;
        h(f2, matrix);
        this.u.setImageMatrix(matrix);
    }

    final void P(int i) {
        if (this.p != i) {
            this.p = i;
            Y();
        }
    }

    final void Q(float f2) {
        if (this.i != f2) {
            this.i = f2;
            E(this.g, this.h, f2);
        }
    }

    void R(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(ax.p298s7.a.e(colorStateList));
        } else if (drawable != null) {
            drawable.setTintList(ax.p298s7.a.e(colorStateList));
        }
    }

    void S(boolean z) {
        this.f = z;
        Z();
    }

    final void T(l lVar) {
        this.a = lVar;
        h hVar = this.b;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(lVar);
        }
        Object obj = this.c;
        if (obj instanceof o) {
            ((o) obj).setShapeAppearanceModel(lVar);
        }
    }

    final void U(ax.S6.h hVar) {
        this.m = hVar;
    }

    boolean V() {
        return this.v.c() || x();
    }

    void X(f fVar, boolean z) {
        b bVar;
        AnimatorSet animatorSetJ;
        if (z()) {
            return;
        }
        Animator animator = this.l;
        if (animator != null) {
            animator.cancel();
        }
        int i = 0;
        boolean z2 = this.m == null;
        if (!W()) {
            this.u.b(0, z);
            this.u.setAlpha(1.0f);
            this.u.setScaleY(1.0f);
            this.u.setScaleX(1.0f);
            O(1.0f);
            if (fVar != null) {
                fVar.a();
                return;
            }
            return;
        }
        if (this.u.getVisibility() != 0) {
            this.u.setAlpha(0.0f);
            this.u.setScaleY(z2 ? 0.4f : 0.0f);
            this.u.setScaleX(z2 ? 0.4f : 0.0f);
            O(z2 ? 0.4f : 0.0f);
        }
        ax.S6.h hVar = this.m;
        if (hVar != null) {
            animatorSetJ = i(hVar, 1.0f, 1.0f, 1.0f);
            bVar = this;
        } else {
            bVar = this;
            animatorSetJ = bVar.j(1.0f, 1.0f, 1.0f, C, D);
        }
        animatorSetJ.addListener(new C0576b(z, fVar));
        ArrayList<Animator.AnimatorListener> arrayList = bVar.r;
        if (arrayList != null) {
            int size = arrayList.size();
            while (i < size) {
                Animator.AnimatorListener animatorListener = arrayList.get(i);
                i++;
                animatorSetJ.addListener(animatorListener);
            }
        }
        animatorSetJ.start();
    }

    final void Y() {
        O(this.o);
    }

    final void Z() {
        Rect rect = this.w;
        r(rect);
        F(rect);
        this.v.b(rect.left, rect.top, rect.right, rect.bottom);
    }

    void a0(float f2) {
        h hVar = this.b;
        if (hVar != null) {
            hVar.f0(f2);
        }
    }

    public void e(Animator.AnimatorListener animatorListener) {
        if (this.s == null) {
            this.s = new ArrayList<>();
        }
        this.s.add(animatorListener);
    }

    void f(Animator.AnimatorListener animatorListener) {
        if (this.r == null) {
            this.r = new ArrayList<>();
        }
        this.r.add(animatorListener);
    }

    void g(e eVar) {
        if (this.t == null) {
            this.t = new ArrayList<>();
        }
        this.t.add(eVar);
    }

    final Drawable m() {
        return this.d;
    }

    float n() {
        return this.u.getElevation();
    }

    boolean o() {
        return this.e;
    }

    final ax.S6.h p() {
        return this.n;
    }

    float q() {
        return this.h;
    }

    void r(Rect rect) {
        if (this.v.c()) {
            int iV = v();
            float fN = this.f ? n() + this.i : 0.0f;
            int iMax = Math.max(iV, (int) Math.ceil(fN));
            int iMax2 = Math.max(iV, (int) Math.ceil(fN * 1.5f));
            rect.set(iMax, iMax2, iMax, iMax2);
            return;
        }
        if (!x()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.j - this.u.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    float s() {
        return this.i;
    }

    final l t() {
        return this.a;
    }

    final ax.S6.h u() {
        return this.m;
    }

    int v() {
        if (this.e) {
            return Math.max((this.j - this.u.getSizeDimension()) / 2, 0);
        }
        return 0;
    }

    void w(f fVar, boolean z) {
        b bVar;
        AnimatorSet animatorSetJ;
        if (y()) {
            return;
        }
        Animator animator = this.l;
        if (animator != null) {
            animator.cancel();
        }
        if (!W()) {
            this.u.b(z ? 8 : 4, z);
            if (fVar != null) {
                fVar.b();
                return;
            }
            return;
        }
        ax.S6.h hVar = this.n;
        if (hVar != null) {
            animatorSetJ = i(hVar, 0.0f, 0.0f, 0.0f);
            bVar = this;
        } else {
            bVar = this;
            animatorSetJ = bVar.j(0.0f, 0.4f, 0.4f, E, F);
        }
        animatorSetJ.addListener(new a(z, fVar));
        ArrayList<Animator.AnimatorListener> arrayList = bVar.s;
        if (arrayList != null) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Animator.AnimatorListener animatorListener = arrayList.get(i);
                i++;
                animatorSetJ.addListener(animatorListener);
            }
        }
        animatorSetJ.start();
    }

    final boolean x() {
        return this.e && this.u.getSizeDimension() < this.j;
    }

    boolean y() {
        if (this.u.getVisibility() == 0) {
            return this.q == 1;
        }
        return this.q != 2;
    }

    boolean z() {
        if (this.u.getVisibility() != 0) {
            return this.q == 2;
        }
        return this.q != 1;
    }
}
