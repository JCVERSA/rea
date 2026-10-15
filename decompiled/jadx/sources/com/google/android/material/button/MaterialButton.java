package com.google.android.material.button;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.appcompat.widget.C0596f;
import ax.R6.l;
import ax.R6.m;
import ax.p202m7.w;
import ax.p202m7.z;
import ax.p323u0.i;
import ax.p323u0.k;
import ax.p346v7.h;
import ax.p346v7.o;
import ax.p346v7.q;
import ax.p346v7.r;
import com.google.android.material.button.MaterialButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialButton extends C0596f implements Checkable, o {
    private static final int[] O0 = {R.attr.state_checkable};
    private static final int[] P0 = {R.attr.state_checked};
    private static final int Q0 = l.s;
    private static final int R0 = ax.R6.c.A;
    private static final i<MaterialButton> S0 = new a("widthIncrease");
    private int A0;
    private float B0;
    private int C0;
    private int D0;
    private LinearLayout.LayoutParams E0;
    private boolean F0;
    private int G0;
    private boolean H0;
    int I0;
    r J0;
    int K0;
    private float L0;
    private float M0;
    private k N0;
    private final com.google.android.material.button.b m0;
    private final LinkedHashSet<b> n0;
    private c o0;
    private PorterDuff.Mode p0;
    private ColorStateList q0;
    private Drawable r0;
    private String s0;
    private int t0;
    private int u0;
    private int v0;
    private int w0;
    private boolean x0;
    private boolean y0;
    private int z0;

    class a extends i<MaterialButton> {
        a(String str) {
            super(str);
        }

        @Override // ax.p323u0.i
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(MaterialButton materialButton) {
            return materialButton.getDisplayedWidthIncrease();
        }

        @Override // ax.p323u0.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(MaterialButton materialButton, float f) {
            materialButton.setDisplayedWidthIncrease(f);
        }
    }

    public interface b {
        void a(MaterialButton materialButton, boolean z);
    }

    interface c {
        void a(MaterialButton materialButton, boolean z);
    }

    static class d extends ax.p163k0.a {
        public static final Parcelable.Creator<d> CREATOR = new a();
        boolean Y;

        class a implements Parcelable.ClassLoaderCreator<d> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public d createFromParcel(Parcel parcel) {
                return new d(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public d createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new d(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public d[] newArray(int i) {
                return new d[i];
            }
        }

        public d(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            b(parcel);
        }

        public d(Parcelable parcelable) {
            super(parcelable);
        }

        private void b(Parcel parcel) {
            this.Y = parcel.readInt() == 1;
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.Y ? 1 : 0);
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.t);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = Q0;
        super(ax.C7.a.e(context, attributeSet, i, i2, new int[]{R0}), attributeSet, i);
        this.n0 = new LinkedHashSet<>();
        this.x0 = false;
        this.y0 = false;
        this.A0 = -1;
        this.B0 = -1.0f;
        this.C0 = -1;
        this.D0 = -1;
        this.I0 = -1;
        Context context2 = getContext();
        TypedArray typedArrayI = w.i(context2, attributeSet, m.t2, i, i2, new int[0]);
        this.w0 = typedArrayI.getDimensionPixelSize(m.H2, 0);
        this.p0 = z.i(typedArrayI.getInt(m.K2, -1), PorterDuff.Mode.SRC_IN);
        this.q0 = ax.p282r7.c.a(getContext(), typedArrayI, m.J2);
        this.r0 = ax.p282r7.c.d(getContext(), typedArrayI, m.F2);
        this.z0 = typedArrayI.getInteger(m.G2, 1);
        this.t0 = typedArrayI.getDimensionPixelSize(m.I2, 0);
        q qVarB = q.b(context2, typedArrayI, m.N2);
        ax.p346v7.l lVarC = qVarB != null ? qVarB.c(true) : ax.p346v7.l.e(context2, attributeSet, i, i2).m();
        boolean z = typedArrayI.getBoolean(m.L2, false);
        com.google.android.material.button.b bVar = new com.google.android.material.button.b(this, lVarC);
        this.m0 = bVar;
        bVar.t(typedArrayI);
        setCheckedInternal(typedArrayI.getBoolean(m.v2, false));
        if (qVarB != null) {
            bVar.z(e());
            bVar.F(qVarB);
        }
        setOpticalCenterEnabled(z);
        typedArrayI.recycle();
        setCompoundDrawablePadding(this.w0);
        q(this.r0 != null);
    }

    public static /* synthetic */ void a(MaterialButton materialButton) {
        materialButton.G0 = materialButton.getOpticalCenterShift();
        materialButton.s();
        materialButton.invalidate();
    }

    public static /* synthetic */ void b(MaterialButton materialButton, float f) {
        int i = (int) (f * 0.11f);
        if (materialButton.G0 != i) {
            materialButton.G0 = i;
            materialButton.s();
            materialButton.invalidate();
        }
    }

    private ax.p323u0.l e() {
        return ax.p234o7.i.h(getContext(), ax.R6.c.P, l.b);
    }

    private void f() {
        k kVar = new k(this, S0);
        this.N0 = kVar;
        kVar.q(e());
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        if (textAlignment == 1) {
            return getGravityTextAlignment();
        }
        if (textAlignment == 6 || textAlignment == 3) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        return textAlignment != 4 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getDisplayedWidthIncrease() {
        return this.L0;
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & 8388615;
        if (gravity != 1) {
            return (gravity == 5 || gravity == 8388613) ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL;
        }
        return Layout.Alignment.ALIGN_CENTER;
    }

    private int getOpticalCenterShift() {
        h hVarG;
        if (this.F0 && this.H0 && (hVarG = this.m0.g()) != null) {
            return (int) (hVarG.z() * 0.11f);
        }
        return 0;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        float fMax = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            fMax = Math.max(fMax, getLayout().getLineWidth(i));
        }
        return (int) Math.ceil(fMax);
    }

    private boolean h() {
        int i = this.z0;
        return i == 3 || i == 4;
    }

    private boolean i() {
        int i = this.z0;
        return i == 1 || i == 2;
    }

    private boolean j() {
        int i = this.z0;
        return i == 16 || i == 32;
    }

    private boolean k() {
        return (getParent() instanceof com.google.android.material.button.a) && ((com.google.android.material.button.a) getParent()).getOrientation() == 0;
    }

    private boolean l() {
        return getLayoutDirection() == 1;
    }

    private boolean m() {
        com.google.android.material.button.b bVar = this.m0;
        return (bVar == null || bVar.q()) ? false : true;
    }

    private void n(boolean z) {
        if (this.J0 == null) {
            return;
        }
        if (this.N0 == null) {
            f();
        }
        if (this.H0) {
            this.N0.m(Math.min(this.K0, this.J0.e(getDrawableState()).a.a(getWidth())));
            if (z) {
                this.N0.r();
            }
        }
    }

    private void p() {
        if (i()) {
            setCompoundDrawablesRelative(this.r0, null, null, null);
        } else if (h()) {
            setCompoundDrawablesRelative(null, null, this.r0, null);
        } else if (j()) {
            setCompoundDrawablesRelative(null, this.r0, null, null);
        }
    }

    private void q(boolean z) {
        Drawable drawable = this.r0;
        if (drawable != null) {
            Drawable drawableMutate = ax.U.a.r(drawable).mutate();
            this.r0 = drawableMutate;
            drawableMutate.setTintList(this.q0);
            PorterDuff.Mode mode = this.p0;
            if (mode != null) {
                this.r0.setTintMode(mode);
            }
            int intrinsicWidth = this.t0;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.r0.getIntrinsicWidth();
            }
            int intrinsicHeight = this.t0;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.r0.getIntrinsicHeight();
            }
            Drawable drawable2 = this.r0;
            int i = this.u0;
            int i2 = this.v0;
            drawable2.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.r0.setVisible(true, z);
        }
        if (z) {
            p();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        if ((!i() || drawable3 == this.r0) && ((!h() || drawable5 == this.r0) && (!j() || drawable4 == this.r0))) {
            return;
        }
        p();
    }

    private void r(int i, int i2) {
        if (this.r0 == null || getLayout() == null) {
            return;
        }
        if (!i() && !h()) {
            if (j()) {
                this.u0 = 0;
                if (this.z0 == 16) {
                    this.v0 = 0;
                    q(false);
                    return;
                }
                int intrinsicHeight = this.t0;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.r0.getIntrinsicHeight();
                }
                int iMax = Math.max(0, (((((i2 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.w0) - getPaddingBottom()) / 2);
                if (this.v0 != iMax) {
                    this.v0 = iMax;
                    q(false);
                    return;
                }
                return;
            }
            return;
        }
        this.v0 = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i3 = this.z0;
        if (i3 == 1 || i3 == 3 || ((i3 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i3 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.u0 = 0;
            q(false);
            return;
        }
        int intrinsicWidth = this.t0;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.r0.getIntrinsicWidth();
        }
        int textLayoutWidth = ((((i - getTextLayoutWidth()) - getPaddingEnd()) - intrinsicWidth) - this.w0) - getPaddingStart();
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            textLayoutWidth /= 2;
        }
        if (l() != (this.z0 == 4)) {
            textLayoutWidth = -textLayoutWidth;
        }
        if (this.u0 != textLayoutWidth) {
            this.u0 = textLayoutWidth;
            q(false);
        }
    }

    private void s() {
        int i = (int) (this.L0 - this.M0);
        int i2 = (i / 2) + this.G0;
        getLayoutParams().width = (int) (this.B0 + i);
        setPaddingRelative(this.C0 + i2, getPaddingTop(), (this.D0 + i) - i2, getPaddingBottom());
    }

    private void setCheckedInternal(boolean z) {
        if (!g() || this.x0 == z) {
            return;
        }
        this.x0 = z;
        refreshDrawableState();
        if (getParent() instanceof MaterialButtonToggleGroup) {
            ((MaterialButtonToggleGroup) getParent()).w(this, this.x0);
        }
        if (this.y0) {
            return;
        }
        this.y0 = true;
        Iterator<b> it = this.n0.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.x0);
        }
        this.y0 = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisplayedWidthIncrease(float f) {
        if (this.L0 != f) {
            this.L0 = f;
            s();
            invalidate();
            if (getParent() instanceof com.google.android.material.button.a) {
                ((com.google.android.material.button.a) getParent()).k(this, (int) this.L0);
            }
        }
    }

    public boolean g() {
        com.google.android.material.button.b bVar = this.m0;
        return bVar != null && bVar.r();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    String getA11yClassName() {
        if (TextUtils.isEmpty(this.s0)) {
            return (g() ? CompoundButton.class : Button.class).getName();
        }
        return this.s0;
    }

    int getAllowedWidthDecrease() {
        return this.I0;
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (m()) {
            return this.m0.b();
        }
        return 0;
    }

    public ax.p323u0.l getCornerSpringForce() {
        return this.m0.c();
    }

    public Drawable getIcon() {
        return this.r0;
    }

    public int getIconGravity() {
        return this.z0;
    }

    public int getIconPadding() {
        return this.w0;
    }

    public int getIconSize() {
        return this.t0;
    }

    public ColorStateList getIconTint() {
        return this.q0;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.p0;
    }

    public int getInsetBottom() {
        return this.m0.d();
    }

    public int getInsetTop() {
        return this.m0.e();
    }

    public ColorStateList getRippleColor() {
        if (m()) {
            return this.m0.i();
        }
        return null;
    }

    public ax.p346v7.l getShapeAppearanceModel() {
        if (m()) {
            return this.m0.j();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public q getStateListShapeAppearanceModel() {
        if (m()) {
            return this.m0.k();
        }
        throw new IllegalStateException("Attempted to get StateListShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (m()) {
            return this.m0.l();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (m()) {
            return this.m0.m();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.C0596f
    public ColorStateList getSupportBackgroundTintList() {
        return m() ? this.m0.n() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.C0596f
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return m() ? this.m0.o() : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.x0;
    }

    void o() {
        LinearLayout.LayoutParams layoutParams = this.E0;
        if (layoutParams != null) {
            setLayoutParams(layoutParams);
            this.E0 = null;
            this.B0 = -1.0f;
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (m()) {
            ax.p346v7.i.f(this, this.m0.g());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (g()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, O0);
        }
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, P0);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.C0596f, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.C0596f, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(g());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.C0596f, android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        com.google.android.material.button.b bVar;
        super.onLayout(z, i, i2, i3, i4);
        if (Build.VERSION.SDK_INT == 21 && (bVar = this.m0) != null) {
            bVar.O(i4 - i2, i3 - i);
        }
        r(getMeasuredWidth(), getMeasuredHeight());
        int i6 = getResources().getConfiguration().orientation;
        if (this.A0 != i6) {
            this.A0 = i6;
            this.B0 = -1.0f;
        }
        if (this.B0 == -1.0f) {
            this.B0 = getMeasuredWidth();
            if (this.E0 == null && (getParent() instanceof com.google.android.material.button.a) && ((com.google.android.material.button.a) getParent()).getButtonSizeChange() != null) {
                this.E0 = (LinearLayout.LayoutParams) getLayoutParams();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.E0);
                layoutParams.width = (int) this.B0;
                setLayoutParams(layoutParams);
            }
        }
        if (this.I0 == -1) {
            if (this.r0 == null) {
                i5 = 0;
            } else {
                int iconPadding = getIconPadding();
                int intrinsicWidth = this.t0;
                if (intrinsicWidth == 0) {
                    intrinsicWidth = this.r0.getIntrinsicWidth();
                }
                i5 = iconPadding + intrinsicWidth;
            }
            this.I0 = (getMeasuredWidth() - getTextLayoutWidth()) - i5;
        }
        if (this.C0 == -1) {
            this.C0 = getPaddingStart();
        }
        if (this.D0 == -1) {
            this.D0 = getPaddingEnd();
        }
        this.H0 = k();
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof d)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        d dVar = (d) parcelable;
        super.onRestoreInstanceState(dVar.a());
        setChecked(dVar.Y);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        d dVar = new d(super.onSaveInstanceState());
        dVar.Y = this.x0;
        return dVar;
    }

    @Override // androidx.appcompat.widget.C0596f, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        r(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean performClick() {
        if (isEnabled() && this.m0.s()) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.r0 != null) {
            if (this.r0.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void setA11yClassName(String str) {
        this.s0 = str;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (m()) {
            this.m0.u(i);
        } else {
            super.setBackgroundColor(i);
        }
    }

    @Override // androidx.appcompat.widget.C0596f, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!m()) {
            super.setBackgroundDrawable(drawable);
        } else {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            this.m0.v();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.C0596f, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? ax.p226o.a.b(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z) {
        if (m()) {
            this.m0.w(z);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        setCheckedInternal(z);
    }

    public void setCornerRadius(int i) {
        if (m()) {
            this.m0.x(i);
        }
    }

    public void setCornerRadiusResource(int i) {
        if (m()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    public void setCornerSpringForce(ax.p323u0.l lVar) {
        this.m0.z(lVar);
    }

    void setDisplayedWidthDecrease(int i) {
        this.M0 = Math.min(i, this.I0);
        s();
        invalidate();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        if (m()) {
            this.m0.g().f0(f);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.r0 != drawable) {
            this.r0 = drawable;
            q(true);
            r(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.z0 != i) {
            this.z0 = i;
            r(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.w0 != i) {
            this.w0 = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? ax.p226o.a.b(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.t0 != i) {
            this.t0 = i;
            q(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.q0 != colorStateList) {
            this.q0 = colorStateList;
            q(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.p0 != mode) {
            this.p0 = mode;
            q(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(ax.p226o.a.a(getContext(), i));
    }

    public void setInsetBottom(int i) {
        this.m0.A(i);
    }

    public void setInsetTop(int i) {
        this.m0.B(i);
    }

    void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    void setOnPressedChangeListenerInternal(c cVar) {
        this.o0 = cVar;
    }

    public void setOpticalCenterEnabled(boolean z) {
        if (this.F0 != z) {
            this.F0 = z;
            if (z) {
                this.m0.y(new h.d() { // from class: ax.Y6.a
                    @Override // ax.v7.h.d
                    public final void a(float f) {
                        MaterialButton.b(this.a, f);
                    }
                });
            } else {
                this.m0.y(null);
            }
            post(new Runnable() { // from class: ax.Y6.b
                @Override // java.lang.Runnable
                public final void run() {
                    MaterialButton.a(this.q);
                }
            });
        }
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        c cVar = this.o0;
        if (cVar != null) {
            cVar.a(this, z);
        }
        super.setPressed(z);
        n(false);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (m()) {
            this.m0.C(colorStateList);
        }
    }

    public void setRippleColorResource(int i) {
        if (m()) {
            setRippleColor(ax.p226o.a.a(getContext(), i));
        }
    }

    @Override // ax.p346v7.o
    public void setShapeAppearanceModel(ax.p346v7.l lVar) {
        if (!m()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.m0.D(lVar);
    }

    void setShouldDrawSurfaceColorStroke(boolean z) {
        if (m()) {
            this.m0.E(z);
        }
    }

    void setSizeChange(r rVar) {
        if (this.J0 != rVar) {
            this.J0 = rVar;
            n(true);
        }
    }

    public void setStateListShapeAppearanceModel(q qVar) {
        if (!m()) {
            throw new IllegalStateException("Attempted to set StateListShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        if (this.m0.c() == null && qVar.f()) {
            this.m0.z(e());
        }
        this.m0.F(qVar);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (m()) {
            this.m0.G(colorStateList);
        }
    }

    public void setStrokeColorResource(int i) {
        if (m()) {
            setStrokeColor(ax.p226o.a.a(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (m()) {
            this.m0.H(i);
        }
    }

    public void setStrokeWidthResource(int i) {
        if (m()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // androidx.appcompat.widget.C0596f
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (m()) {
            this.m0.I(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.C0596f
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (m()) {
            this.m0.J(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.view.View
    public void setTextAlignment(int i) {
        super.setTextAlignment(i);
        r(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z) {
        this.m0.K(z);
    }

    @Override // android.widget.TextView
    public void setWidth(int i) {
        this.B0 = -1.0f;
        super.setWidth(i);
    }

    void setWidthChangeMax(int i) {
        if (this.K0 != i) {
            this.K0 = i;
            n(true);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.x0);
    }
}
