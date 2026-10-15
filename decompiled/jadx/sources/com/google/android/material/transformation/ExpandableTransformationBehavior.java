package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    private AnimatorSet X;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ExpandableTransformationBehavior.this.X = null;
        }
    }

    public ExpandableTransformationBehavior() {
    }

    public ExpandableTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior
    protected boolean L(View view, View view2, boolean z, boolean z2) {
        AnimatorSet animatorSet = this.X;
        boolean z3 = animatorSet != null;
        if (z3) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSetN = N(view, view2, z, z3);
        this.X = animatorSetN;
        animatorSetN.addListener(new a());
        this.X.start();
        if (!z2) {
            this.X.end();
        }
        return true;
    }

    protected abstract AnimatorSet N(View view, View view2, boolean z, boolean z2);
}
