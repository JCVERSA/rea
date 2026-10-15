package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import ax.R6.c;
import ax.R6.l;
import ax.R6.m;
import ax.p202m7.w;
import ax.p202m7.z;
import ax.p346v7.d;
import ax.p346v7.p;
import ax.p346v7.q;
import ax.p346v7.r;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
public class a extends LinearLayout {
    private static final int t0 = l.m;
    private final List<q> k0;
    private final b l0;
    private final Comparator<MaterialButton> m0;
    private Integer[] n0;
    p o0;
    private q p0;
    private final List<ax.p346v7.l> q;
    private int q0;
    private r r0;
    private boolean s0;

    private class b implements MaterialButton.c {
        private b() {
        }

        @Override // com.google.android.material.button.MaterialButton.c
        public void a(MaterialButton materialButton, boolean z) {
            a.this.invalidate();
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.s);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, AttributeSet attributeSet, int i) {
        int i2 = t0;
        super(ax.C7.a.d(context, attributeSet, i, i2), attributeSet, i);
        this.q = new ArrayList();
        this.k0 = new ArrayList();
        this.l0 = new b();
        this.m0 = new Comparator() { // from class: ax.Y6.c
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return com.google.android.material.button.a.a(this.q, (MaterialButton) obj, (MaterialButton) obj2);
            }
        };
        this.s0 = true;
        Context context2 = getContext();
        TypedArray typedArrayI = w.i(context2, attributeSet, m.R2, i, i2, new int[0]);
        int i3 = m.U2;
        if (typedArrayI.hasValue(i3)) {
            this.r0 = r.b(context2, typedArrayI, i3);
        }
        int i4 = m.W2;
        if (typedArrayI.hasValue(i4)) {
            q qVarB = q.b(context2, typedArrayI, i4);
            this.p0 = qVarB;
            if (qVarB == null) {
                this.p0 = new q.b(ax.p346v7.l.b(context2, typedArrayI.getResourceId(i4, 0), typedArrayI.getResourceId(m.X2, 0)).m()).j();
            }
        }
        int i5 = m.V2;
        if (typedArrayI.hasValue(i5)) {
            this.o0 = p.b(context2, typedArrayI, i5, new ax.p346v7.a(0.0f));
        }
        this.q0 = typedArrayI.getDimensionPixelSize(m.T2, 0);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(typedArrayI.getBoolean(m.S2, true));
        typedArrayI.recycle();
    }

    public static /* synthetic */ int a(a aVar, MaterialButton materialButton, MaterialButton materialButton2) {
        aVar.getClass();
        int iCompareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
        return iCompareTo2 != 0 ? iCompareTo2 : Integer.compare(aVar.indexOfChild(materialButton), aVar.indexOfChild(materialButton2));
    }

    private void b() {
        int iMin;
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton materialButtonF = f(i);
            MaterialButton materialButtonF2 = f(i - 1);
            if (this.q0 <= 0) {
                iMin = Math.min(materialButtonF.getStrokeWidth(), materialButtonF2.getStrokeWidth());
                materialButtonF.setShouldDrawSurfaceColorStroke(true);
                materialButtonF2.setShouldDrawSurfaceColorStroke(true);
            } else {
                materialButtonF.setShouldDrawSurfaceColorStroke(false);
                materialButtonF2.setShouldDrawSurfaceColorStroke(false);
                iMin = 0;
            }
            LinearLayout.LayoutParams layoutParamsD = d(materialButtonF);
            if (getOrientation() == 0) {
                layoutParamsD.setMarginEnd(0);
                layoutParamsD.setMarginStart(this.q0 - iMin);
                layoutParamsD.topMargin = 0;
            } else {
                layoutParamsD.bottomMargin = 0;
                layoutParamsD.topMargin = this.q0 - iMin;
                layoutParamsD.setMarginStart(0);
            }
            materialButtonF.setLayoutParams(layoutParamsD);
        }
        m(firstVisibleChildIndex);
    }

    private void c() {
        if (this.r0 == null || getChildCount() == 0) {
            return;
        }
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        int iMin = SubsamplingScaleImageView.TILE_SIZE_AUTO;
        for (int i = firstVisibleChildIndex; i <= lastVisibleChildIndex; i++) {
            if (j(i)) {
                int iE = e(i);
                if (i != firstVisibleChildIndex && i != lastVisibleChildIndex) {
                    iE /= 2;
                }
                iMin = Math.min(iMin, iE);
            }
        }
        int i2 = firstVisibleChildIndex;
        while (i2 <= lastVisibleChildIndex) {
            if (j(i2)) {
                f(i2).setSizeChange(this.r0);
                f(i2).setWidthChangeMax((i2 == firstVisibleChildIndex || i2 == lastVisibleChildIndex) ? iMin : iMin * 2);
            }
            i2++;
        }
    }

    private int e(int i) {
        if (!j(i) || this.r0 == null) {
            return 0;
        }
        int iMax = Math.max(0, this.r0.c(f(i).getWidth()));
        MaterialButton materialButtonI = i(i);
        int allowedWidthDecrease = materialButtonI == null ? 0 : materialButtonI.getAllowedWidthDecrease();
        MaterialButton materialButtonG = g(i);
        return Math.min(iMax, allowedWidthDecrease + (materialButtonG != null ? materialButtonG.getAllowedWidthDecrease() : 0));
    }

    private MaterialButton g(int i) {
        int childCount = getChildCount();
        do {
            i++;
            if (i >= childCount) {
                return null;
            }
        } while (!j(i));
        return f(i);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (j(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (j(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private q.b h(boolean z, boolean z2, int i) {
        q qVar = this.p0;
        if (qVar == null || (!z && !z2)) {
            qVar = this.k0.get(i);
        }
        return qVar == null ? new q.b(this.q.get(i)) : qVar.i();
    }

    private MaterialButton i(int i) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (j(i2)) {
                return f(i2);
            }
        }
        return null;
    }

    private boolean j(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    private void l() {
        for (int i = 0; i < getChildCount(); i++) {
            f(i).o();
        }
    }

    private void m(int i) {
        if (getChildCount() == 0 || i == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) f(i).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            layoutParams.setMarginEnd(0);
            layoutParams.setMarginStart(0);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
    }

    private void n() {
        TreeMap treeMap = new TreeMap(this.m0);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(f(i), Integer.valueOf(i));
        }
        this.n0 = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(View.generateViewId());
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MButtonGroup", "Child views must be of type MaterialButton.");
            return;
        }
        l();
        this.s0 = true;
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        materialButton.setOnPressedChangeListenerInternal(this.l0);
        this.q.add(materialButton.getShapeAppearanceModel());
        this.k0.add(materialButton.getStateListShapeAppearanceModel());
        materialButton.setEnabled(isEnabled());
    }

    LinearLayout.LayoutParams d(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        n();
        super.dispatchDraw(canvas);
    }

    MaterialButton f(int i) {
        return (MaterialButton) getChildAt(i);
    }

    public r getButtonSizeChange() {
        return this.r0;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.n0;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w("MButtonGroup", "Child order wasn't updated");
        return i2;
    }

    public d getInnerCornerSize() {
        return this.o0.e();
    }

    public p getInnerCornerSizeStateList() {
        return this.o0;
    }

    public ax.p346v7.l getShapeAppearance() {
        q qVar = this.p0;
        if (qVar == null) {
            return null;
        }
        return qVar.c(true);
    }

    public int getSpacing() {
        return this.q0;
    }

    public q getStateListShapeAppearance() {
        return this.p0;
    }

    void k(MaterialButton materialButton, int i) {
        int iIndexOfChild = indexOfChild(materialButton);
        if (iIndexOfChild < 0) {
            return;
        }
        MaterialButton materialButtonI = i(iIndexOfChild);
        MaterialButton materialButtonG = g(iIndexOfChild);
        if (materialButtonI == null && materialButtonG == null) {
            return;
        }
        if (materialButtonI == null) {
            materialButtonG.setDisplayedWidthDecrease(i);
        }
        if (materialButtonG == null) {
            materialButtonI.setDisplayedWidthDecrease(i);
        }
        if (materialButtonI == null || materialButtonG == null) {
            return;
        }
        materialButtonI.setDisplayedWidthDecrease(i / 2);
        materialButtonG.setDisplayedWidthDecrease((i + 1) / 2);
    }

    void o() {
        int iH;
        if (!(this.o0 == null && this.p0 == null) && this.s0) {
            this.s0 = false;
            int childCount = getChildCount();
            int firstVisibleChildIndex = getFirstVisibleChildIndex();
            int lastVisibleChildIndex = getLastVisibleChildIndex();
            int i = 0;
            while (i < childCount) {
                MaterialButton materialButtonF = f(i);
                if (materialButtonF.getVisibility() != 8) {
                    boolean z = i == firstVisibleChildIndex;
                    boolean z2 = i == lastVisibleChildIndex;
                    q.b bVarH = h(z, z2, i);
                    boolean z3 = getOrientation() == 0;
                    boolean zH = z.h(this);
                    if (z3) {
                        iH = z ? 5 : 0;
                        if (z2) {
                            iH |= 10;
                        }
                        if (zH) {
                            iH = q.h(iH);
                        }
                    } else {
                        iH = z ? 3 : 0;
                        if (z2) {
                            iH |= 12;
                        }
                    }
                    q qVarJ = bVarH.n(this.o0, ~iH).j();
                    if (qVarJ.f()) {
                        materialButtonF.setStateListShapeAppearanceModel(qVarJ);
                    } else {
                        materialButtonF.setShapeAppearanceModel(qVarJ.c(true));
                    }
                }
                i++;
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            l();
            c();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        o();
        b();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.q.remove(iIndexOfChild);
            this.k0.remove(iIndexOfChild);
        }
        this.s0 = true;
        o();
        l();
        b();
    }

    public void setButtonSizeChange(r rVar) {
        if (this.r0 != rVar) {
            this.r0 = rVar;
            c();
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            f(i).setEnabled(z);
        }
    }

    public void setInnerCornerSize(d dVar) {
        this.o0 = p.c(dVar);
        this.s0 = true;
        o();
        invalidate();
    }

    public void setInnerCornerSizeStateList(p pVar) {
        this.o0 = pVar;
        this.s0 = true;
        o();
        invalidate();
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (getOrientation() != i) {
            this.s0 = true;
        }
        super.setOrientation(i);
    }

    public void setShapeAppearance(ax.p346v7.l lVar) {
        this.p0 = new q.b(lVar).j();
        this.s0 = true;
        o();
        invalidate();
    }

    public void setSpacing(int i) {
        this.q0 = i;
        invalidate();
        requestLayout();
    }

    public void setStateListShapeAppearance(q qVar) {
        this.p0 = qVar;
        this.s0 = true;
        o();
        invalidate();
    }
}
