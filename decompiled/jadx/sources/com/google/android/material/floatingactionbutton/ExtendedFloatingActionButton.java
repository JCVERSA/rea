package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.l;
import ax.R6.m;
import ax.S6.h;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    private static final int c1 = l.z;
    static final Property<View, Float> d1 = new a(Float.class, "width");
    static final Property<View, Float> e1 = new b(Float.class, "height");
    static final Property<View, Float> f1 = new c(Float.class, "paddingStart");
    static final Property<View, Float> g1 = new d(Float.class, "paddingEnd");
    private boolean T0;
    private final int U0;
    private int V0;
    private int W0;
    private final CoordinatorLayout.c<ExtendedFloatingActionButton> X0;
    private boolean Y0;
    private boolean Z0;
    private boolean a1;
    protected ColorStateList b1;

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {
        private boolean X;
        private boolean Y;
        private Rect q;

        public ExtendedFloatingActionButtonBehavior() {
            this.X = false;
            this.Y = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.K1);
            this.X = typedArrayObtainStyledAttributes.getBoolean(m.L1, false);
            this.Y = typedArrayObtainStyledAttributes.getBoolean(m.M1, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        private static boolean K(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean N(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.X || this.Y) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        private boolean P(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!N(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.q == null) {
                this.q = new Rect();
            }
            Rect rect = this.q;
            ax.p202m7.c.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                O(extendedFloatingActionButton);
                return true;
            }
            I(extendedFloatingActionButton);
            return true;
        }

        private boolean Q(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!N(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                O(extendedFloatingActionButton);
                return true;
            }
            I(extendedFloatingActionButton);
            return true;
        }

        protected void I(ExtendedFloatingActionButton extendedFloatingActionButton) {
            extendedFloatingActionButton.u(this.Y ? 3 : 0, null);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
        public boolean f(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.f(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                P(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!K(view)) {
                return false;
            }
            Q(view, extendedFloatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean p(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List<View> listR = coordinatorLayout.r(extendedFloatingActionButton);
            int size = listR.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = listR.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (K(view) && Q(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (P(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.I(extendedFloatingActionButton, i);
            return true;
        }

        protected void O(ExtendedFloatingActionButton extendedFloatingActionButton) {
            extendedFloatingActionButton.u(this.Y ? 2 : 1, null);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void k(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }
    }

    class a extends Property<View, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            view.getLayoutParams().width = f.intValue();
            view.requestLayout();
        }
    }

    class b extends Property<View, Float> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }
    }

    class c extends Property<View, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getPaddingStart());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            view.setPaddingRelative(f.intValue(), view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        }
    }

    class d extends Property<View, Float> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getPaddingEnd());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
        }
    }

    public static abstract class e {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(int i, e eVar) {
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            throw null;
        }
        throw new IllegalStateException("Unknown strategy type: " + i);
    }

    private void v() {
        this.b1 = getTextColors();
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "com.google.android.material.floatingactionbutton.FloatingActionButton";
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return this.X0;
    }

    int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    int getCollapsedSize() {
        int i = this.U0;
        return i < 0 ? (Math.min(getPaddingStart(), getPaddingEnd()) * 2) + getIconSize() : i;
    }

    public h getExtendMotionSpec() {
        throw null;
    }

    public h getHideMotionSpec() {
        throw null;
    }

    public h getShowMotionSpec() {
        throw null;
    }

    public h getShrinkMotionSpec() {
        throw null;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.Y0 && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.Y0 = false;
            throw null;
        }
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.a1 = z;
    }

    public void setAnimationEnabled(boolean z) {
        this.T0 = z;
    }

    public void setExtendMotionSpec(h hVar) {
        throw null;
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(h.c(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (this.Y0 != z) {
            throw null;
        }
    }

    public void setHideMotionSpec(h hVar) {
        throw null;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(h.c(getContext(), i));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (!this.Y0 || this.Z0) {
            return;
        }
        this.V0 = getPaddingStart();
        this.W0 = getPaddingEnd();
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        if (!this.Y0 || this.Z0) {
            return;
        }
        this.V0 = i;
        this.W0 = i3;
    }

    public void setShowMotionSpec(h hVar) {
        throw null;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(h.c(getContext(), i));
    }

    public void setShrinkMotionSpec(h hVar) {
        throw null;
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(h.c(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        v();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        v();
    }
}
