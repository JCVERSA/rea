package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.C0601k;
import androidx.appcompat.widget.q;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.e;
import ax.R6.l;
import ax.R6.m;
import ax.S6.h;
import ax.S6.k;
import ax.p019b0.g;
import ax.p035c0.C1839d0;
import ax.p202m7.A;
import ax.p346v7.o;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FloatingActionButton extends A implements ax.p186l7.a, o, CoordinatorLayout.b {
    private static final int y0 = l.i;
    private ColorStateList k0;
    private PorterDuff.Mode l0;
    private ColorStateList m0;
    private PorterDuff.Mode n0;
    private ColorStateList o0;
    private int p0;
    private int q0;
    private int r0;
    private int s0;
    boolean t0;
    final Rect u0;
    private final Rect v0;
    private final q w0;
    private com.google.android.material.floatingactionbutton.b x0;

    protected static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.c<T> {
        private b X;
        private boolean Y;
        private Rect q;

        public BaseBehavior() {
            this.Y = true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.O1);
            this.Y = typedArrayObtainStyledAttributes.getBoolean(m.P1, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        private boolean J(View view, FloatingActionButton floatingActionButton) {
            return (this.Y && ((CoordinatorLayout.f) floatingActionButton.getLayoutParams()).e() == view.getId() && floatingActionButton.getUserSetVisibility() == 0) ? false : true;
        }

        private static boolean K(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private void L(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            int i;
            Rect rect = floatingActionButton.u0;
            if (rect.centerX() <= 0 || rect.centerY() <= 0) {
                return;
            }
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) floatingActionButton.getLayoutParams();
            int i2 = 0;
            if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) {
                i = rect.right;
            } else {
                i = floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) fVar).leftMargin ? -rect.left : 0;
            }
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) {
                i2 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) fVar).topMargin) {
                i2 = -rect.top;
            }
            if (i2 != 0) {
                C1839d0.W(floatingActionButton, i2);
            }
            if (i != 0) {
                C1839d0.V(floatingActionButton, i);
            }
        }

        private boolean O(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (J(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.q == null) {
                this.q = new Rect();
            }
            Rect rect = this.q;
            ax.p202m7.c.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.l(this.X, false);
                return true;
            }
            floatingActionButton.r(this.X, false);
            return true;
        }

        private boolean P(View view, FloatingActionButton floatingActionButton) {
            if (J(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.l(this.X, false);
                return true;
            }
            floatingActionButton.r(this.X, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public boolean f(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.u0;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                O(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
                return false;
            }
            if (!K(view)) {
                return false;
            }
            P(view, floatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean p(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            List<View> listR = coordinatorLayout.r(floatingActionButton);
            int size = listR.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = listR.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (K(view) && P(view, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (O(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.I(floatingActionButton, i);
            L(coordinatorLayout, floatingActionButton);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void k(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* JADX INFO: renamed from: I */
        public /* bridge */ /* synthetic */ boolean f(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            return super.f(coordinatorLayout, floatingActionButton, rect);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* JADX INFO: renamed from: M */
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.l(coordinatorLayout, floatingActionButton, view);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* JADX INFO: renamed from: N */
        public /* bridge */ /* synthetic */ boolean p(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            return super.p(coordinatorLayout, floatingActionButton, i);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ void k(CoordinatorLayout.f fVar) {
            super.k(fVar);
        }
    }

    class a implements com.google.android.material.floatingactionbutton.b.f {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        @Override // com.google.android.material.floatingactionbutton.b.f
        public void a() {
            this.a.b(FloatingActionButton.this);
        }

        @Override // com.google.android.material.floatingactionbutton.b.f
        public void b() {
            this.a.a(FloatingActionButton.this);
        }
    }

    public static abstract class b {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    private class c implements ax.p330u7.b {
        c() {
        }

        @Override // ax.p330u7.b
        public void a(Drawable drawable) {
            if (drawable != null) {
                FloatingActionButton.super.setBackgroundDrawable(drawable);
            }
        }

        @Override // ax.p330u7.b
        public void b(int i, int i2, int i3, int i4) {
            FloatingActionButton.this.u0.set(i, i2, i3, i4);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.setPadding(i + floatingActionButton.r0, i2 + FloatingActionButton.this.r0, i3 + FloatingActionButton.this.r0, i4 + FloatingActionButton.this.r0);
        }

        @Override // ax.p330u7.b
        public boolean c() {
            return FloatingActionButton.this.t0;
        }
    }

    class d<T extends FloatingActionButton> implements com.google.android.material.floatingactionbutton.b.e {
        d(k<T> kVar) {
        }

        @Override // com.google.android.material.floatingactionbutton.b.e
        public void a() {
            throw null;
        }

        @Override // com.google.android.material.floatingactionbutton.b.e
        public void b() {
            throw null;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            ((d) obj).getClass();
            throw null;
        }

        public int hashCode() {
            throw null;
        }
    }

    private com.google.android.material.floatingactionbutton.b getImpl() {
        if (this.x0 == null) {
            this.x0 = new com.google.android.material.floatingactionbutton.b(this, new c());
        }
        return this.x0;
    }

    private int i(int i) {
        int i2 = this.q0;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        if (i != -1) {
            return i != 1 ? resources.getDimensionPixelSize(e.c) : resources.getDimensionPixelSize(e.b);
        }
        return Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? i(1) : i(0);
    }

    private void j(Rect rect) {
        h(rect);
        int i = -this.x0.v();
        rect.inset(i, i);
    }

    private void o(Rect rect) {
        int i = rect.left;
        Rect rect2 = this.u0;
        rect.left = i + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    private void p() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.m0;
        if (colorStateList == null) {
            ax.U.a.c(drawable);
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.n0;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(C0601k.e(colorForState, mode));
    }

    private com.google.android.material.floatingactionbutton.b.f s(b bVar) {
        if (bVar == null) {
            return null;
        }
        return new a(bVar);
    }

    @Override // ax.p186l7.a
    public boolean a() {
        throw null;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (Build.VERSION.SDK_INT == 21) {
            getImpl().D();
        }
    }

    public void e(Animator.AnimatorListener animatorListener) {
        getImpl().e(animatorListener);
    }

    public void f(Animator.AnimatorListener animatorListener) {
        getImpl().f(animatorListener);
    }

    public void g(k<? extends FloatingActionButton> kVar) {
        getImpl().g(new d(kVar));
    }

    @Override // android.widget.ImageButton, android.widget.ImageView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "com.google.android.material.floatingactionbutton.FloatingActionButton";
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.k0;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.l0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().n();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().q();
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().s();
    }

    public Drawable getContentBackground() {
        return getImpl().m();
    }

    public int getCustomSize() {
        return this.q0;
    }

    public int getExpandedComponentIdHint() {
        throw null;
    }

    public h getHideMotionSpec() {
        return getImpl().p();
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.o0;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.o0;
    }

    public ax.p346v7.l getShapeAppearanceModel() {
        return (ax.p346v7.l) g.g(getImpl().t());
    }

    public h getShowMotionSpec() {
        return getImpl().u();
    }

    public int getSize() {
        return this.p0;
    }

    int getSizeDimension() {
        return i(this.p0);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.m0;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.n0;
    }

    public boolean getUseCompatPadding() {
        return this.t0;
    }

    public void h(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        o(rect);
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
    }

    public void k(b bVar) {
        l(bVar, true);
    }

    void l(b bVar, boolean z) {
        getImpl().w(s(bVar), z);
    }

    public boolean m() {
        return getImpl().y();
    }

    public boolean n() {
        return getImpl().z();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().A();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().C();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.r0 = (sizeDimension - this.s0) / 2;
        getImpl().Z();
        int iMin = Math.min(View.resolveSize(sizeDimension, i), View.resolveSize(sizeDimension, i2));
        Rect rect = this.u0;
        setMeasuredDimension(rect.left + iMin + rect.right, iMin + rect.top + rect.bottom);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ax.p394y7.a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ax.p394y7.a aVar = (ax.p394y7.a) parcelable;
        super.onRestoreInstanceState(aVar.a());
        throw null;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        if (parcelableOnSaveInstanceState == null) {
            parcelableOnSaveInstanceState = new Bundle();
        }
        new ax.p394y7.a(parcelableOnSaveInstanceState);
        throw null;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            j(this.v0);
            if (!this.v0.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void q(b bVar) {
        r(bVar, true);
    }

    void r(b bVar, boolean z) {
        getImpl().X(s(bVar), z);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.k0 != colorStateList) {
            this.k0 = colorStateList;
            getImpl().I(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.l0 != mode) {
            this.l0 = mode;
            getImpl().J(mode);
        }
    }

    public void setCompatElevation(float f) {
        getImpl().K(f);
    }

    public void setCompatElevationResource(int i) {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f) {
        getImpl().N(f);
    }

    public void setCompatHoveredFocusedTranslationZResource(int i) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f) {
        getImpl().Q(f);
    }

    public void setCompatPressedTranslationZResource(int i) {
        setCompatPressedTranslationZ(getResources().getDimension(i));
    }

    public void setCustomSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i != this.q0) {
            this.q0 = i;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        getImpl().a0(f);
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().o()) {
            getImpl().L(z);
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i) {
        throw null;
    }

    public void setHideMotionSpec(h hVar) {
        getImpl().M(hVar);
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(h.c(getContext(), i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            getImpl().Y();
            if (this.m0 != null) {
                p();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.w0.i(i);
        p();
    }

    public void setMaxImageSize(int i) {
        this.s0 = i;
        getImpl().P(i);
    }

    public void setRippleColor(int i) {
        setRippleColor(ColorStateList.valueOf(i));
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.o0 != colorStateList) {
            this.o0 = colorStateList;
            getImpl().R(this.o0);
        }
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        super.setScaleX(f);
        getImpl().G();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        super.setScaleY(f);
        getImpl().G();
    }

    public void setShadowPaddingEnabled(boolean z) {
        getImpl().S(z);
    }

    @Override // ax.p346v7.o
    public void setShapeAppearanceModel(ax.p346v7.l lVar) {
        getImpl().T(lVar);
    }

    public void setShowMotionSpec(h hVar) {
        getImpl().U(hVar);
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(h.c(getContext(), i));
    }

    public void setSize(int i) {
        this.q0 = 0;
        if (i != this.p0) {
            this.p0 = i;
            requestLayout();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.m0 != colorStateList) {
            this.m0 = colorStateList;
            p();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.n0 != mode) {
            this.n0 = mode;
            p();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        super.setTranslationX(f);
        getImpl().H();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        getImpl().H();
    }

    @Override // android.view.View
    public void setTranslationZ(float f) {
        super.setTranslationZ(f);
        getImpl().H();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.t0 != z) {
            this.t0 = z;
            getImpl().B();
        }
    }

    @Override // ax.p202m7.A, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }
}
