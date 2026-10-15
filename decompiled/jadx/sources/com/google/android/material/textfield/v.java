package com.google.android.material.textfield;

import android.R;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.C0594d;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class v extends C0594d {
    private final ax.p322u.t n0;
    private final AccessibilityManager o0;
    private final Rect p0;
    private final int q0;
    private final float r0;
    private ColorStateList s0;
    private int t0;
    private ColorStateList u0;

    class a implements AdapterView.OnItemClickListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            v vVar = v.this;
            Object objV = i < 0 ? vVar.n0.v() : vVar.getAdapter().getItem(i);
            v vVar2 = v.this;
            vVar2.setText(vVar2.convertSelectionToString(objV), false);
            AdapterView.OnItemClickListener onItemClickListener = v.this.getOnItemClickListener();
            if (onItemClickListener != null) {
                if (view == null || i < 0) {
                    view = v.this.n0.y();
                    i = v.this.n0.x();
                    j = v.this.n0.w();
                }
                onItemClickListener.onItemClick(v.this.n0.j(), view, i, j);
            }
            v.this.n0.dismiss();
        }
    }

    private class b<T> extends ArrayAdapter<String> {
        private ColorStateList X;
        private ColorStateList q;

        b(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            f();
        }

        private ColorStateList a() {
            if (!c() || !d()) {
                return null;
            }
            int[] iArr = {R.attr.state_hovered, -16842919};
            int[] iArr2 = {R.attr.state_selected, -16842919};
            return new ColorStateList(new int[][]{iArr2, iArr, new int[0]}, new int[]{ax.p106g7.a.j(v.this.t0, v.this.u0.getColorForState(iArr2, 0)), ax.p106g7.a.j(v.this.t0, v.this.u0.getColorForState(iArr, 0)), v.this.t0});
        }

        private Drawable b() {
            if (!c()) {
                return null;
            }
            ColorDrawable colorDrawable = new ColorDrawable(v.this.t0);
            if (this.X == null) {
                return colorDrawable;
            }
            colorDrawable.setTintList(this.q);
            return new RippleDrawable(this.X, colorDrawable, null);
        }

        private boolean c() {
            return v.this.t0 != 0;
        }

        private boolean d() {
            return v.this.u0 != null;
        }

        private ColorStateList e() {
            if (!d()) {
                return null;
            }
            int[] iArr = {R.attr.state_pressed};
            return new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{v.this.u0.getColorForState(iArr, 0), 0});
        }

        void f() {
            this.X = e();
            this.q = a();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 instanceof TextView) {
                TextView textView = (TextView) view2;
                textView.setBackground(v.this.getText().toString().contentEquals(textView.getText()) ? b() : null);
            }
            return view2;
        }
    }

    public v(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.p194m.a.p);
    }

    public v(Context context, AttributeSet attributeSet, int i) {
        super(ax.C7.a.d(context, attributeSet, i, 0), attributeSet, i);
        this.p0 = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayI = ax.p202m7.w.i(context2, attributeSet, ax.R6.m.l2, i, ax.p194m.i.g, new int[0]);
        int i2 = ax.R6.m.m2;
        if (typedArrayI.hasValue(i2) && typedArrayI.getInt(i2, 0) == 0) {
            setKeyListener(null);
        }
        this.q0 = typedArrayI.getResourceId(ax.R6.m.p2, ax.R6.i.o);
        this.r0 = typedArrayI.getDimensionPixelOffset(ax.R6.m.n2, ax.R6.e.m0);
        int i3 = ax.R6.m.o2;
        if (typedArrayI.hasValue(i3)) {
            this.s0 = ColorStateList.valueOf(typedArrayI.getColor(i3, 0));
        }
        this.t0 = typedArrayI.getColor(ax.R6.m.q2, 0);
        this.u0 = ax.p282r7.c.a(context2, typedArrayI, ax.R6.m.r2);
        this.o0 = (AccessibilityManager) context2.getSystemService("accessibility");
        ax.p322u.t tVar = new ax.p322u.t(context2);
        this.n0 = tVar;
        tVar.J(true);
        tVar.D(this);
        tVar.I(2);
        tVar.p(getAdapter());
        tVar.L(new a());
        int i4 = ax.R6.m.s2;
        if (typedArrayI.hasValue(i4)) {
            setSimpleItems(typedArrayI.getResourceId(i4, 0));
        }
        typedArrayI.recycle();
    }

    private TextInputLayout f() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    private boolean g() {
        return i() || h();
    }

    private boolean h() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        AccessibilityManager accessibilityManager = this.o0;
        if (accessibilityManager != null && accessibilityManager.isEnabled() && (enabledAccessibilityServiceList = this.o0.getEnabledAccessibilityServiceList(16)) != null) {
            for (AccessibilityServiceInfo accessibilityServiceInfo : enabledAccessibilityServiceList) {
                if (accessibilityServiceInfo.getSettingsActivityName() != null && accessibilityServiceInfo.getSettingsActivityName().contains("SwitchAccess")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean i() {
        AccessibilityManager accessibilityManager = this.o0;
        return accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled();
    }

    private int j() {
        ListAdapter adapter = getAdapter();
        TextInputLayout textInputLayoutF = f();
        int i = 0;
        if (adapter == null || textInputLayoutF == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMin = Math.min(adapter.getCount(), Math.max(0, this.n0.x()) + 15);
        View view = null;
        int iMax = 0;
        for (int iMax2 = Math.max(0, iMin - 15); iMax2 < iMin; iMax2++) {
            int itemViewType = adapter.getItemViewType(iMax2);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = adapter.getView(iMax2, view, textInputLayoutF);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax = Math.max(iMax, view.getMeasuredWidth());
        }
        Drawable drawableI = this.n0.i();
        if (drawableI != null) {
            drawableI.getPadding(this.p0);
            Rect rect = this.p0;
            iMax += rect.left + rect.right;
        }
        return iMax + textInputLayoutF.getEndIconView().getMeasuredWidth();
    }

    private void k() {
        TextInputLayout textInputLayoutF = f();
        if (textInputLayoutF != null) {
            textInputLayoutF.s0();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void dismissDropDown() {
        if (g()) {
            this.n0.dismiss();
        } else {
            super.dismissDropDown();
        }
    }

    public ColorStateList getDropDownBackgroundTintList() {
        return this.s0;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayoutF = f();
        return (textInputLayoutF == null || !textInputLayoutF.T()) ? super.getHint() : textInputLayoutF.getHint();
    }

    public float getPopupElevation() {
        return this.r0;
    }

    public int getSimpleItemSelectedColor() {
        return this.t0;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.u0;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayoutF = f();
        if (textInputLayoutF != null && textInputLayoutF.T() && super.getHint() == null && ax.p202m7.i.d()) {
            setHint(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.n0.dismiss();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), j()), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        if (g()) {
            return;
        }
        super.onWindowFocusChanged(z);
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.n0.p(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        ax.p322u.t tVar = this.n0;
        if (tVar != null) {
            tVar.a(drawable);
        }
    }

    public void setDropDownBackgroundTint(int i) {
        setDropDownBackgroundTintList(ColorStateList.valueOf(i));
    }

    public void setDropDownBackgroundTintList(ColorStateList colorStateList) {
        this.s0 = colorStateList;
        Drawable dropDownBackground = getDropDownBackground();
        if (dropDownBackground instanceof ax.p346v7.h) {
            ((ax.p346v7.h) dropDownBackground).g0(this.s0);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.n0.M(getOnItemSelectedListener());
    }

    @Override // android.widget.TextView
    public void setRawInputType(int i) {
        super.setRawInputType(i);
        k();
    }

    public void setSimpleItemSelectedColor(int i) {
        this.t0 = i;
        if (getAdapter() instanceof b) {
            ((b) getAdapter()).f();
        }
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList colorStateList) {
        this.u0 = colorStateList;
        if (getAdapter() instanceof b) {
            ((b) getAdapter()).f();
        }
    }

    public void setSimpleItems(int i) {
        setSimpleItems(getResources().getStringArray(i));
    }

    public void setSimpleItems(String[] strArr) {
        setAdapter(new b(getContext(), this.q0, strArr));
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        if (g()) {
            this.n0.c();
        } else {
            super.showDropDown();
        }
    }
}
