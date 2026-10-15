package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.g;
import ax.S6.h;
import ax.S6.i;
import ax.S6.j;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final Rect Y;
    private final RectF Z;
    private final RectF k0;
    private final int[] l0;
    private float m0;
    private float n0;

    class a extends AnimatorListenerAdapter {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;
        final /* synthetic */ View c;

        a(boolean z, View view, View view2) {
            this.a = z;
            this.b = view;
            this.c = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                return;
            }
            this.b.setVisibility(4);
            this.c.setAlpha(1.0f);
            this.c.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.a) {
                this.b.setVisibility(0);
                this.c.setAlpha(0.0f);
                this.c.setVisibility(4);
            }
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ View a;

        b(View view) {
            this.a = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.invalidate();
        }
    }

    class c extends AnimatorListenerAdapter {
        final /* synthetic */ ax.p074e7.c a;
        final /* synthetic */ Drawable b;

        c(ax.p074e7.c cVar, Drawable drawable) {
            this.a = cVar;
            this.b = drawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.a.setCircularRevealOverlayDrawable(this.b);
        }
    }

    class d extends AnimatorListenerAdapter {
        final /* synthetic */ ax.p074e7.c a;

        d(ax.p074e7.c cVar) {
            this.a = cVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ax.e7.c.e revealInfo = this.a.getRevealInfo();
            revealInfo.c = Float.MAX_VALUE;
            this.a.setRevealInfo(revealInfo);
        }
    }

    protected static class e {
        public h a;
        public j b;

        protected e() {
        }
    }

    public FabTransformationBehavior() {
        this.Y = new Rect();
        this.Z = new RectF();
        this.k0 = new RectF();
        this.l0 = new int[2];
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Y = new Rect();
        this.Z = new RectF();
        this.k0 = new RectF();
        this.l0 = new int[2];
    }

    private ViewGroup O(View view) {
        View viewFindViewById = view.findViewById(g.D);
        if (viewFindViewById != null) {
            return j0(viewFindViewById);
        }
        return ((view instanceof ax.E7.b) || (view instanceof ax.E7.a)) ? j0(((ViewGroup) view).getChildAt(0)) : j0(view);
    }

    private void P(View view, e eVar, i iVar, i iVar2, float f, float f2, float f3, float f4, RectF rectF) {
        float fW = W(eVar, iVar, f, f3);
        float fW2 = W(eVar, iVar2, f2, f4);
        Rect rect = this.Y;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.Z;
        rectF2.set(rect);
        RectF rectF3 = this.k0;
        X(view, rectF3);
        rectF3.offset(fW, fW2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private void Q(View view, RectF rectF) {
        X(view, rectF);
        rectF.offset(this.m0, this.n0);
    }

    private Pair<i, i> R(float f, float f2, boolean z, e eVar) {
        i iVarE;
        i iVarE2;
        if (f == 0.0f || f2 == 0.0f) {
            iVarE = eVar.a.e("translationXLinear");
            iVarE2 = eVar.a.e("translationYLinear");
        } else if ((!z || f2 >= 0.0f) && (z || f2 <= 0.0f)) {
            iVarE = eVar.a.e("translationXCurveDownwards");
            iVarE2 = eVar.a.e("translationYCurveDownwards");
        } else {
            iVarE = eVar.a.e("translationXCurveUpwards");
            iVarE2 = eVar.a.e("translationYCurveUpwards");
        }
        return new Pair<>(iVarE, iVarE2);
    }

    private float S(View view, View view2, j jVar) {
        RectF rectF = this.Z;
        RectF rectF2 = this.k0;
        Q(view, rectF);
        X(view2, rectF2);
        rectF2.offset(-U(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private float T(View view, View view2, j jVar) {
        RectF rectF = this.Z;
        RectF rectF2 = this.k0;
        Q(view, rectF);
        X(view2, rectF2);
        rectF2.offset(0.0f, -V(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    private float U(View view, View view2, j jVar) {
        float fCenterX;
        float fCenterX2;
        float f;
        RectF rectF = this.Z;
        RectF rectF2 = this.k0;
        Q(view, rectF);
        X(view2, rectF2);
        int i = jVar.a & 7;
        if (i == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else {
            if (i != 3) {
                if (i != 5) {
                    f = 0.0f;
                } else {
                    fCenterX = rectF2.right;
                    fCenterX2 = rectF.right;
                }
                return f + jVar.b;
            }
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        }
        f = fCenterX - fCenterX2;
        return f + jVar.b;
    }

    private float V(View view, View view2, j jVar) {
        float fCenterY;
        float fCenterY2;
        float f;
        RectF rectF = this.Z;
        RectF rectF2 = this.k0;
        Q(view, rectF);
        X(view2, rectF2);
        int i = jVar.a & 112;
        if (i == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else {
            if (i != 48) {
                if (i != 80) {
                    f = 0.0f;
                } else {
                    fCenterY = rectF2.bottom;
                    fCenterY2 = rectF.bottom;
                }
                return f + jVar.c;
            }
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        }
        f = fCenterY - fCenterY2;
        return f + jVar.c;
    }

    private float W(e eVar, i iVar, float f, float f2) {
        long jC = iVar.c();
        long jD = iVar.d();
        i iVarE = eVar.a.e("expansion");
        return ax.S6.a.a(f, f2, iVar.e().getInterpolation((((iVarE.c() + iVarE.d()) + 17) - jC) / jD));
    }

    private void X(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        int[] iArr = this.l0;
        view.getLocationInWindow(iArr);
        rectF.offsetTo(iArr[0], iArr[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    private void Y(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            boolean z3 = view2 instanceof ax.p074e7.c;
            ViewGroup viewGroupO = O(view2);
            if (viewGroupO == null) {
                return;
            }
            if (z) {
                if (!z2) {
                    ax.S6.d.a.set(viewGroupO, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupO, ax.S6.d.a, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupO, ax.S6.d.a, 0.0f);
            }
            eVar.a.e("contentFade").a(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void Z(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof ax.p074e7.c) {
            ax.p074e7.c cVar = (ax.p074e7.c) view2;
            int iH0 = h0(view);
            int i = 16777215 & iH0;
            if (z) {
                if (!z2) {
                    cVar.setCircularRevealScrimColor(iH0);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, ax.e7.c.d.a, i);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, ax.e7.c.d.a, iH0);
            }
            objectAnimatorOfInt.setEvaluator(ax.S6.c.b());
            eVar.a.e("color").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    private void a0(View view, View view2, boolean z, e eVar, List<Animator> list) {
        float fU = U(view, view2, eVar.b);
        float fV = V(view, view2, eVar.b);
        Pair<i, i> pairR = R(fU, fV, z, eVar);
        i iVar = (i) pairR.first;
        i iVar2 = (i) pairR.second;
        Property property = View.TRANSLATION_X;
        if (!z) {
            fU = this.m0;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fU);
        Property property2 = View.TRANSLATION_Y;
        if (!z) {
            fV = this.n0;
        }
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fV);
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    private void b0(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        float elevation = view2.getElevation() - view.getElevation();
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-elevation);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -elevation);
        }
        eVar.a.e("elevation").a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c0(View view, View view2, boolean z, boolean z2, e eVar, float f, float f2, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorA;
        if (view2 instanceof ax.p074e7.c) {
            ax.p074e7.c cVar = (ax.p074e7.c) view2;
            float fS = S(view, view2, eVar.b);
            float fT = T(view, view2, eVar.b);
            ((FloatingActionButton) view).h(this.Y);
            float fWidth = this.Y.width() / 2.0f;
            i iVarE = eVar.a.e("expansion");
            if (z) {
                if (!z2) {
                    cVar.setRevealInfo(new ax.e7.c.e(fS, fT, fWidth));
                }
                if (z2) {
                    fWidth = cVar.getRevealInfo().c;
                }
                animatorA = ax.p074e7.a.a(cVar, fS, fT, ax.p218n7.a.c(fS, fT, 0.0f, 0.0f, f, f2));
                animatorA.addListener(new d(cVar));
                f0(view2, iVarE.c(), (int) fS, (int) fT, fWidth, list);
            } else {
                float f3 = cVar.getRevealInfo().c;
                Animator animatorA2 = ax.p074e7.a.a(cVar, fS, fT, fWidth);
                int i = (int) fS;
                int i2 = (int) fT;
                f0(view2, iVarE.c(), i, i2, f3, list);
                e0(view2, iVarE.c(), iVarE.d(), eVar.a.f(), i, i2, fWidth, list);
                animatorA = animatorA2;
            }
            iVarE.a(animatorA);
            list.add(animatorA);
            list2.add(ax.p074e7.a.b(cVar));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d0(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof ax.p074e7.c) && (view instanceof ImageView)) {
            ax.p074e7.c cVar = (ax.p074e7.c) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z) {
                if (!z2) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, ax.S6.e.a, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, ax.S6.e.a, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new b(view2));
            eVar.a.e("iconFade").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new c(cVar, drawable));
        }
    }

    private void e0(View view, long j, long j2, long j3, int i, int i2, float f, List<Animator> list) {
        long j4 = j + j2;
        if (j4 < j3) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
            animatorCreateCircularReveal.setStartDelay(j4);
            animatorCreateCircularReveal.setDuration(j3 - j4);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void f0(View view, long j, int i, int i2, float f, List<Animator> list) {
        if (j > 0) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
            animatorCreateCircularReveal.setStartDelay(0L);
            animatorCreateCircularReveal.setDuration(j);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void g0(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        i iVar;
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fU = U(view, view2, eVar.b);
        float fV = V(view, view2, eVar.b);
        Pair<i, i> pairR = R(fU, fV, z, eVar);
        i iVar2 = (i) pairR.first;
        i iVar3 = (i) pairR.second;
        if (z) {
            if (!z2) {
                view2.setTranslationX(-fU);
                view2.setTranslationY(-fV);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            P(view2, eVar, iVar2, iVar3, -fU, -fV, 0.0f, 0.0f, rectF);
            iVar2 = iVar2;
            iVar = iVar3;
        } else {
            iVar = iVar3;
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -fU);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -fV);
        }
        iVar2.a(objectAnimatorOfFloat);
        iVar.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    private int h0(View view) {
        ColorStateList backgroundTintList = view.getBackgroundTintList();
        if (backgroundTintList != null) {
            return backgroundTintList.getColorForState(view.getDrawableState(), backgroundTintList.getDefaultColor());
        }
        return 0;
    }

    private ViewGroup j0(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet N(View view, View view2, boolean z, boolean z2) {
        e eVarI0 = i0(view2.getContext(), z);
        if (z) {
            this.m0 = view.getTranslationX();
            this.n0 = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        b0(view, view2, z, z2, eVarI0, arrayList, arrayList2);
        RectF rectF = this.Z;
        g0(view, view2, z, z2, eVarI0, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        a0(view, view2, z, eVarI0, arrayList);
        d0(view, view2, z, z2, eVarI0, arrayList, arrayList2);
        c0(view, view2, z, z2, eVarI0, fWidth, fHeight, arrayList, arrayList2);
        Z(view, view2, z, z2, eVarI0, arrayList, arrayList2);
        Y(view, view2, z, z2, eVarI0, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        ax.S6.b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(z, view2, view));
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            animatorSet.addListener(arrayList2.get(i));
        }
        return animatorSet;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean i(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    protected abstract e i0(Context context, boolean z);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void k(CoordinatorLayout.f fVar) {
        if (fVar.h == 0) {
            fVar.h = 80;
        }
    }
}
