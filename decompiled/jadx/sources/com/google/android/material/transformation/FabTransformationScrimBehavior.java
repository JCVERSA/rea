package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.S6.b;
import ax.S6.i;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    private final i Y;
    private final i Z;

    class a extends AnimatorListenerAdapter {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        a(boolean z, View view) {
            this.a = z;
            this.b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                return;
            }
            this.b.setVisibility(4);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.a) {
                this.b.setVisibility(0);
            }
        }
    }

    public FabTransformationScrimBehavior() {
        this.Y = new i(75L, 150L);
        this.Z = new i(0L, 150L);
    }

    public FabTransformationScrimBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Y = new i(75L, 150L);
        this.Z = new i(0L, 150L);
    }

    private void O(View view, boolean z, boolean z2, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        i iVar = z ? this.Y : this.Z;
        if (z) {
            if (!z2) {
                view.setAlpha(0.0f);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f);
        }
        iVar.a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean H(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return super.H(coordinatorLayout, view, motionEvent);
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet N(View view, View view2, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        O(view2, z, z2, arrayList, new ArrayList());
        AnimatorSet animatorSet = new AnimatorSet();
        b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(z, view2));
        return animatorSet;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean i(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return view2 instanceof FloatingActionButton;
    }
}
