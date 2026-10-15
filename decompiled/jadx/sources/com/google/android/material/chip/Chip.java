package com.google.android.material.chip;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.C0597g;
import ax.R6.k;
import ax.R6.l;
import ax.R6.m;
import ax.S6.h;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p202m7.j;
import ax.p202m7.w;
import ax.p282r7.e;
import ax.p282r7.g;
import ax.p346v7.i;
import ax.p346v7.o;
import com.google.android.material.chip.Chip;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class Chip extends C0597g implements com.google.android.material.chip.a.InterfaceC0572a, o, Checkable {
    private static final int F0 = l.v;
    private static final Rect G0 = new Rect();
    private static final int[] H0 = {R.attr.state_selected};
    private static final int[] I0 = {R.attr.state_checkable};
    private final c A0;
    private boolean B0;
    private final Rect C0;
    private final RectF D0;
    private final g E0;
    private com.google.android.material.chip.a n0;
    private InsetDrawable o0;
    private RippleDrawable p0;
    private View.OnClickListener q0;
    private CompoundButton.OnCheckedChangeListener r0;
    private boolean s0;
    private boolean t0;
    private boolean u0;
    private boolean v0;
    private boolean w0;
    private int x0;
    private int y0;
    private CharSequence z0;

    class a extends g {
        a() {
        }

        @Override // ax.p282r7.g
        public void a(int i) {
        }

        @Override // ax.p282r7.g
        public void b(Typeface typeface, boolean z) {
            Chip chip = Chip.this;
            chip.setText(chip.n0.Z2() ? Chip.this.n0.t1() : Chip.this.getText());
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    }

    class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (Chip.this.n0 != null) {
                Chip.this.n0.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    private class c extends ax.p179l0.a {
        c(Chip chip) {
            super(chip);
        }

        @Override // ax.p179l0.a
        protected int B(float f, float f2) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f, f2)) ? 1 : 0;
        }

        @Override // ax.p179l0.a
        protected void C(List<Integer> list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.q0 != null) {
                list.add(1);
            }
        }

        @Override // ax.p179l0.a
        protected boolean J(int i, int i2, Bundle bundle) {
            if (i2 != 16) {
                return false;
            }
            if (i == 0) {
                return Chip.this.performClick();
            }
            if (i == 1) {
                return Chip.this.t();
            }
            return false;
        }

        @Override // ax.p179l0.a
        protected void M(H h) {
            h.k0(Chip.this.r());
            h.n0(Chip.this.isClickable());
            h.m0(Chip.this.getAccessibilityClassName());
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                h.M0(text);
            } else {
                h.q0(text);
            }
        }

        @Override // ax.p179l0.a
        protected void N(int i, H h) {
            CharSequence charSequence = HttpUrl.FRAGMENT_ENCODE_SET;
            if (i != 1) {
                h.q0(HttpUrl.FRAGMENT_ENCODE_SET);
                h.i0(Chip.G0);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                h.q0(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i2 = k.n;
                if (!TextUtils.isEmpty(text)) {
                    charSequence = text;
                }
                h.q0(context.getString(i2, charSequence).trim());
            }
            h.i0(Chip.this.getCloseIconTouchBoundsInt());
            h.b(H.a.i);
            h.s0(Chip.this.isEnabled());
            h.m0(Button.class.getName());
        }

        @Override // ax.p179l0.a
        protected void O(int i, boolean z) {
            if (i == 1) {
                Chip.this.v0 = z;
            }
            if (Chip.this.n0.J1(Chip.this.v0)) {
                Chip.this.refreshDrawableState();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.d);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i) {
        int i2 = F0;
        super(ax.C7.a.d(context, attributeSet, i, i2), attributeSet, i);
        this.C0 = new Rect();
        this.D0 = new RectF();
        this.E0 = new a();
        Context context2 = getContext();
        C(attributeSet);
        com.google.android.material.chip.a aVarH0 = com.google.android.material.chip.a.H0(context2, attributeSet, i, i2);
        o(context2, attributeSet, i);
        setChipDrawable(aVarH0);
        aVarH0.f0(getElevation());
        TypedArray typedArrayI = w.i(context2, attributeSet, m.C0, i, i2, new int[0]);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(ax.p282r7.c.a(context2, typedArrayI, m.F0));
        }
        boolean zHasValue = typedArrayI.hasValue(m.o1);
        typedArrayI.recycle();
        this.A0 = new c(this);
        x();
        if (!zHasValue) {
            p();
        }
        setChecked(this.s0);
        setText(aVarH0.t1());
        setEllipsize(aVarH0.n1());
        B();
        if (!this.n0.Z2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        A();
        if (v()) {
            setMinHeight(this.y0);
        }
        this.x0 = getLayoutDirection();
        super.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: ax.d7.b
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Chip.b(this.a, compoundButton, z);
            }
        });
    }

    private void A() {
        com.google.android.material.chip.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.n0) == null) {
            return;
        }
        int iV0 = (int) (aVar.V0() + this.n0.v1() + this.n0.C0());
        int iA1 = (int) (this.n0.a1() + this.n0.w1() + this.n0.y0());
        if (this.o0 != null) {
            Rect rect = new Rect();
            this.o0.getPadding(rect);
            iA1 += rect.left;
            iV0 += rect.right;
        }
        setPaddingRelative(iA1, getPaddingTop(), iV0, getPaddingBottom());
    }

    private void B() {
        TextPaint paint = getPaint();
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        e textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.p(getContext(), paint, this.E0);
        }
    }

    private void C(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        }
    }

    public static /* synthetic */ void b(Chip chip, CompoundButton compoundButton, boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.r0;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.D0.setEmpty();
        if (n() && this.q0 != null) {
            this.n0.k1(this.D0);
        }
        return this.D0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.C0.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.C0;
    }

    private e getTextAppearance() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.u1();
        }
        return null;
    }

    private void k(com.google.android.material.chip.a aVar) {
        aVar.C2(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int[] l() {
        ?? IsEnabled = isEnabled();
        int i = IsEnabled;
        if (this.v0) {
            i = IsEnabled + 1;
        }
        int i2 = i;
        if (this.u0) {
            i2 = i + 1;
        }
        int i3 = i2;
        if (this.t0) {
            i3 = i2 + 1;
        }
        int i4 = i3;
        if (isChecked()) {
            i4 = i3 + 1;
        }
        int[] iArr = new int[i4];
        int i5 = 0;
        if (isEnabled()) {
            iArr[0] = 16842910;
            i5 = 1;
        }
        if (this.v0) {
            iArr[i5] = 16842908;
            i5++;
        }
        if (this.u0) {
            iArr[i5] = 16843623;
            i5++;
        }
        if (this.t0) {
            iArr[i5] = 16842919;
            i5++;
        }
        if (isChecked()) {
            iArr[i5] = 16842913;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        com.google.android.material.chip.a aVar = this.n0;
        return (aVar == null || aVar.d1() == null) ? false : true;
    }

    private void o(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayI = w.i(context, attributeSet, m.C0, i, F0, new int[0]);
        this.w0 = typedArrayI.getBoolean(m.j1, false);
        this.y0 = (int) Math.ceil(typedArrayI.getDimension(m.X0, ax.p282r7.b.e(context)));
        typedArrayI.recycle();
    }

    private void p() {
        setOutlineProvider(new b());
    }

    private void q(int i, int i2, int i3, int i4) {
        this.o0 = new InsetDrawable((Drawable) this.n0, i, i2, i3, i4);
    }

    private void setCloseIconHovered(boolean z) {
        if (this.u0 != z) {
            this.u0 = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.t0 != z) {
            this.t0 = z;
            refreshDrawableState();
        }
    }

    private void u() {
        if (this.o0 != null) {
            this.o0 = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            y();
        }
    }

    private void w(com.google.android.material.chip.a aVar) {
        if (aVar != null) {
            aVar.C2(null);
        }
    }

    private void x() {
        if (n() && s() && this.q0 != null) {
            C1839d0.l0(this, this.A0);
            this.B0 = true;
        } else {
            C1839d0.l0(this, null);
            this.B0 = false;
        }
    }

    private void y() {
        z();
    }

    private void z() {
        this.p0 = new RippleDrawable(ax.p298s7.a.e(this.n0.r1()), getBackgroundDrawable(), null);
        this.n0.Y2(false);
        setBackground(this.p0);
        A();
    }

    @Override // com.google.android.material.chip.a.InterfaceC0572a
    public void a() {
        m(this.y0);
        requestLayout();
        invalidateOutline();
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.B0) {
            return this.A0.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.B0) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (!this.A0.w(keyEvent) || this.A0.A() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.C0597g, android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        com.google.android.material.chip.a aVar = this.n0;
        if ((aVar == null || !aVar.B1()) ? false : this.n0.y2(l())) {
            invalidate();
        }
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.z0)) {
            return this.z0;
        }
        if (!r()) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        ViewParent parent = getParent();
        return ((parent instanceof com.google.android.material.chip.b) && ((com.google.android.material.chip.b) parent).f()) ? "android.widget.RadioButton" : "android.widget.Button";
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.o0;
        return insetDrawable == null ? this.n0 : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.R0();
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.S0();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.T0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return Math.max(0.0f, aVar.U0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.n0;
    }

    public float getChipEndPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.V0();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.W0();
        }
        return null;
    }

    public float getChipIconSize() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.X0();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.Y0();
        }
        return null;
    }

    public float getChipMinHeight() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.Z0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.a1();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.b1();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.c1();
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.d1();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.e1();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.f1();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.g1();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.h1();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.j1();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.n1();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.B0 && (this.A0.A() == 1 || this.A0.x() == 1)) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public h getHideMotionSpec() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.o1();
        }
        return null;
    }

    public float getIconEndPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.p1();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.q1();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.r1();
        }
        return null;
    }

    public ax.p346v7.l getShapeAppearanceModel() {
        return this.n0.I();
    }

    public h getShowMotionSpec() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.s1();
        }
        return null;
    }

    public float getTextEndPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.v1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            return aVar.w1();
        }
        return 0.0f;
    }

    public boolean m(int i) {
        this.y0 = i;
        if (!v()) {
            if (this.o0 != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int iMax = Math.max(0, i - this.n0.getIntrinsicHeight());
        int iMax2 = Math.max(0, i - this.n0.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.o0 != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int i2 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i3 = iMax > 0 ? iMax / 2 : 0;
        if (this.o0 != null) {
            Rect rect = new Rect();
            this.o0.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                y();
                return true;
            }
        }
        if (getMinHeight() != i) {
            setMinHeight(i);
        }
        if (getMinWidth() != i) {
            setMinWidth(i);
        }
        q(i2, i3, i2, i3);
        y();
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.n0);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, H0);
        }
        if (r()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, I0);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (this.B0) {
            this.A0.I(z, i, rect);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        accessibilityNodeInfo.setCheckable(r());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof com.google.android.material.chip.b) {
            com.google.android.material.chip.b bVar = (com.google.android.material.chip.b) getParent();
            H.Q0(accessibilityNodeInfo).p0(H.f.a(bVar.b(this), 1, bVar.c() ? bVar.d(this) : -1, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        return (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), 1002) : super.onResolvePointerIcon(motionEvent, i);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.x0 != i) {
            this.x0 = i;
            A();
        }
    }

    @Override // android.widget.TextView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        boolean zContains = getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY());
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                    }
                } else if (this.t0) {
                    if (!zContains) {
                        setCloseIconPressed(false);
                    }
                    z = true;
                }
                z = false;
            } else {
                if (this.t0) {
                    t();
                    z = true;
                }
                setCloseIconPressed(false);
            }
            z = false;
            setCloseIconPressed(false);
        } else if (zContains) {
            setCloseIconPressed(true);
            z = true;
        } else {
            z = false;
        }
        return z || super.onTouchEvent(motionEvent);
    }

    public boolean r() {
        com.google.android.material.chip.a aVar = this.n0;
        return aVar != null && aVar.A1();
    }

    public boolean s() {
        com.google.android.material.chip.a aVar = this.n0;
        return aVar != null && aVar.C1();
    }

    public void setAccessibilityClassName(CharSequence charSequence) {
        this.z0 = charSequence;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.p0) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.C0597g, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.p0) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.C0597g, android.view.View
    public void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.K1(z);
        }
    }

    public void setCheckableResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.L1(i);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar == null) {
            this.s0 = z;
        } else if (aVar.A1()) {
            super.setChecked(z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.M1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.N1(i);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.O1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.P1(i);
        }
    }

    public void setCheckedIconVisible(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.Q1(i);
        }
    }

    public void setCheckedIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.R1(z);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.S1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.T1(i);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.U1(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.V1(i);
        }
    }

    public void setChipDrawable(com.google.android.material.chip.a aVar) {
        com.google.android.material.chip.a aVar2 = this.n0;
        if (aVar2 != aVar) {
            w(aVar2);
            this.n0 = aVar;
            aVar.N2(false);
            k(this.n0);
            m(this.y0);
        }
    }

    public void setChipEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.W1(f);
        }
    }

    public void setChipEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.X1(i);
        }
    }

    public void setChipIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.Y1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.Z1(i);
        }
    }

    public void setChipIconSize(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.a2(f);
        }
    }

    public void setChipIconSizeResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.b2(i);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.c2(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.d2(i);
        }
    }

    public void setChipIconVisible(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.e2(i);
        }
    }

    public void setChipIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.f2(z);
        }
    }

    public void setChipMinHeight(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.g2(f);
        }
    }

    public void setChipMinHeightResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.h2(i);
        }
    }

    public void setChipStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.i2(f);
        }
    }

    public void setChipStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.j2(i);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.k2(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.l2(i);
        }
    }

    public void setChipStrokeWidth(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.m2(f);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.n2(i);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i) {
        setText(getResources().getString(i));
    }

    public void setCloseIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.p2(drawable);
        }
        x();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.q2(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.r2(f);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.s2(i);
        }
    }

    public void setCloseIconResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.t2(i);
        }
        x();
    }

    public void setCloseIconSize(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.u2(f);
        }
    }

    public void setCloseIconSizeResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.v2(i);
        }
    }

    public void setCloseIconStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.w2(f);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.x2(i);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.z2(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.A2(i);
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    public void setCloseIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.B2(z);
        }
        x();
    }

    @Override // androidx.appcompat.widget.C0597g, android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // androidx.appcompat.widget.C0597g, android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.f0(f);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.n0 == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.D2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.w0 = z;
        m(this.y0);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(h hVar) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.E2(hVar);
        }
    }

    public void setHideMotionSpecResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.F2(i);
        }
    }

    public void setIconEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.G2(f);
        }
    }

    public void setIconEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.H2(i);
        }
    }

    public void setIconStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.I2(f);
        }
    }

    public void setIconStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.J2(i);
        }
    }

    public void setInternalOnCheckedChangeListener(j<Chip> jVar) {
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.n0 == null) {
            return;
        }
        super.setLayoutDirection(i);
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.K2(i);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.r0 = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.q0 = onClickListener;
        x();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.L2(colorStateList);
        }
        if (this.n0.y1()) {
            return;
        }
        z();
    }

    public void setRippleColorResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.M2(i);
            if (this.n0.y1()) {
                return;
            }
            z();
        }
    }

    @Override // ax.p346v7.o
    public void setShapeAppearanceModel(ax.p346v7.l lVar) {
        this.n0.setShapeAppearanceModel(lVar);
    }

    public void setShowMotionSpec(h hVar) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.O2(hVar);
        }
    }

    public void setShowMotionSpecResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.P2(i);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        super.setText(aVar.Z2() ? null : charSequence, bufferType);
        com.google.android.material.chip.a aVar2 = this.n0;
        if (aVar2 != null) {
            aVar2.Q2(charSequence);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.S2(i);
        }
        B();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.S2(i);
        }
        B();
    }

    public void setTextAppearance(e eVar) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.R2(eVar);
        }
        B();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.T2(f);
        }
    }

    public void setTextEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.U2(i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.V2(TypedValue.applyDimension(i, f, getResources().getDisplayMetrics()));
        }
        B();
    }

    public void setTextStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.W2(f);
        }
    }

    public void setTextStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.n0;
        if (aVar != null) {
            aVar.X2(i);
        }
    }

    public boolean t() {
        boolean z = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.q0;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z = true;
        }
        if (this.B0) {
            this.A0.U(1, 1);
        }
        return z;
    }

    public boolean v() {
        return this.w0;
    }
}
