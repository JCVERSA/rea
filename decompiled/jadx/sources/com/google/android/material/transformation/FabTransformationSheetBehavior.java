package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.b;
import ax.S6.h;
import ax.S6.j;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map<View, Integer> o0;

    public FabTransformationSheetBehavior() {
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void k0(View view, boolean z) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                this.o0 = new HashMap(childCount);
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                boolean z2 = (childAt.getLayoutParams() instanceof CoordinatorLayout.f) && (((CoordinatorLayout.f) childAt.getLayoutParams()).f() instanceof FabTransformationScrimBehavior);
                if (childAt != view && !z2) {
                    if (z) {
                        this.o0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        childAt.setImportantForAccessibility(4);
                    } else {
                        Map<View, Integer> map = this.o0;
                        if (map != null && map.containsKey(childAt)) {
                            childAt.setImportantForAccessibility(this.o0.get(childAt).intValue());
                        }
                    }
                }
            }
            if (z) {
                return;
            }
            this.o0 = null;
        }
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    protected boolean L(View view, View view2, boolean z, boolean z2) {
        k0(view2, z);
        return super.L(view, view2, z, z2);
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    protected FabTransformationBehavior.e i0(Context context, boolean z) {
        int i = z ? b.d : b.c;
        FabTransformationBehavior.e eVar = new FabTransformationBehavior.e();
        eVar.a = h.c(context, i);
        eVar.b = new j(17, 0.0f, 0.0f);
        return eVar;
    }
}
