package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import ax.R6.c;
import ax.R6.l;
import ax.R6.m;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p202m7.w;
import ax.p346v7.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialButtonToggleGroup extends com.google.android.material.button.a {
    private static final int A0 = l.B;
    private final LinkedHashSet<b> u0;
    private boolean v0;
    private boolean w0;
    private boolean x0;
    private final int y0;
    private Set<Integer> z0;

    class a extends C1832a {
        a() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.p0(H.f.a(0, 1, MaterialButtonToggleGroup.this.u(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public interface b {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.u);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = A0;
        super(ax.C7.a.d(context, attributeSet, i, i2), attributeSet, i);
        this.u0 = new LinkedHashSet<>();
        this.v0 = false;
        this.z0 = new HashSet();
        TypedArray typedArrayI = w.i(getContext(), attributeSet, m.Y2, i, i2, new int[0]);
        setSingleSelection(typedArrayI.getBoolean(m.c3, false));
        this.y0 = typedArrayI.getResourceId(m.a3, -1);
        this.x0 = typedArrayI.getBoolean(m.b3, false);
        if (this.o0 == null) {
            this.o0 = p.c(new ax.p346v7.a(0.0f));
        }
        setEnabled(typedArrayI.getBoolean(m.Z2, true));
        typedArrayI.recycle();
        setImportantForAccessibility(1);
    }

    private String getChildrenA11yClassName() {
        return (this.w0 ? RadioButton.class : ToggleButton.class).getName();
    }

    private int getVisibleButtonCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && j(i2)) {
                i++;
            }
        }
        return i;
    }

    private boolean j(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    private void r(int i, boolean z) {
        if (i == -1) {
            Log.e("MButtonToggleGroup", "Button ID is not valid: " + i);
            return;
        }
        HashSet hashSet = new HashSet(this.z0);
        if (z && !hashSet.contains(Integer.valueOf(i))) {
            if (this.w0 && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i));
        } else {
            if (z || !hashSet.contains(Integer.valueOf(i))) {
                return;
            }
            if (!this.x0 || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i));
            }
        }
        y(hashSet);
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setA11yClassName(getChildrenA11yClassName());
    }

    private void t(int i, boolean z) {
        Iterator<b> it = this.u0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int u(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2) == view) {
                return i;
            }
            if ((getChildAt(i2) instanceof MaterialButton) && j(i2)) {
                i++;
            }
        }
        return -1;
    }

    private void x(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById instanceof MaterialButton) {
            this.v0 = true;
            ((MaterialButton) viewFindViewById).setChecked(z);
            this.v0 = false;
        }
    }

    private void y(Set<Integer> set) {
        Set<Integer> set2 = this.z0;
        this.z0 = new HashSet(set);
        for (int i = 0; i < getChildCount(); i++) {
            int id = f(i).getId();
            x(id, set.contains(Integer.valueOf(id)));
            if (set2.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                t(id, set.contains(Integer.valueOf(id)));
            }
        }
        invalidate();
    }

    private void z() {
        String childrenA11yClassName = getChildrenA11yClassName();
        for (int i = 0; i < getChildCount(); i++) {
            f(i).setA11yClassName(childrenA11yClassName);
        }
    }

    @Override // com.google.android.material.button.a, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setupButtonChild(materialButton);
        r(materialButton.getId(), materialButton.isChecked());
        C1839d0.l0(materialButton, new a());
    }

    public int getCheckedButtonId() {
        if (!this.w0 || this.z0.isEmpty()) {
            return -1;
        }
        return this.z0.iterator().next().intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            int id = f(i).getId();
            if (this.z0.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.y0;
        if (i != -1) {
            y(Collections.singleton(Integer.valueOf(i)));
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        H.Q0(accessibilityNodeInfo).o0(H.e.a(1, getVisibleButtonCount(), false, v() ? 1 : 2));
    }

    public void q(b bVar) {
        this.u0.add(bVar);
    }

    public void s() {
        y(new HashSet());
    }

    public void setSelectionRequired(boolean z) {
        this.x0 = z;
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }

    public void setSingleSelection(boolean z) {
        if (this.w0 != z) {
            this.w0 = z;
            s();
        }
        z();
    }

    public boolean v() {
        return this.w0;
    }

    void w(MaterialButton materialButton, boolean z) {
        if (this.v0) {
            return;
        }
        r(materialButton.getId(), z);
    }
}
