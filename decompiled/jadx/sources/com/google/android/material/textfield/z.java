package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.L;
import ax.p051d0.H;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"ViewConstructor"})
class z extends LinearLayout {
    private final TextView k0;
    private CharSequence l0;
    private final CheckableImageButton m0;
    private ColorStateList n0;
    private PorterDuff.Mode o0;
    private int p0;
    private final TextInputLayout q;
    private ImageView.ScaleType q0;
    private View.OnLongClickListener r0;
    private boolean s0;

    z(TextInputLayout textInputLayout, L l) {
        super(textInputLayout.getContext());
        this.q = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(ax.R6.i.h, (ViewGroup) this, false);
        this.m0 = checkableImageButton;
        t.e(checkableImageButton);
        androidx.appcompat.widget.x xVar = new androidx.appcompat.widget.x(getContext());
        this.k0 = xVar;
        j(l);
        i(l);
        addView(checkableImageButton);
        addView(xVar);
    }

    private void C() {
        int i = (this.l0 == null || this.s0) ? 8 : 0;
        setVisibility((this.m0.getVisibility() == 0 || i == 0) ? 0 : 8);
        this.k0.setVisibility(i);
        this.q.p0();
    }

    private void i(L l) {
        this.k0.setVisibility(8);
        this.k0.setId(ax.R6.g.U);
        this.k0.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.k0.setAccessibilityLiveRegion(1);
        o(l.n(ax.R6.m.a7, 0));
        int i = ax.R6.m.b7;
        if (l.s(i)) {
            p(l.c(i));
        }
        n(l.p(ax.R6.m.Z6));
    }

    private void j(L l) {
        if (ax.p282r7.c.h(getContext())) {
            ((ViewGroup.MarginLayoutParams) this.m0.getLayoutParams()).setMarginEnd(0);
        }
        u(null);
        v(null);
        int i = ax.R6.m.h7;
        if (l.s(i)) {
            this.n0 = ax.p282r7.c.b(getContext(), l, i);
        }
        int i2 = ax.R6.m.i7;
        if (l.s(i2)) {
            this.o0 = ax.p202m7.z.i(l.k(i2, -1), null);
        }
        int i3 = ax.R6.m.e7;
        if (l.s(i3)) {
            s(l.g(i3));
            int i4 = ax.R6.m.d7;
            if (l.s(i4)) {
                r(l.p(i4));
            }
            q(l.a(ax.R6.m.c7, true));
        }
        t(l.f(ax.R6.m.f7, getResources().getDimensionPixelSize(ax.R6.e.o0)));
        int i5 = ax.R6.m.g7;
        if (l.s(i5)) {
            w(t.b(l.k(i5, -1)));
        }
    }

    void A(H h) {
        if (this.k0.getVisibility() != 0) {
            h.N0(this.m0);
        } else {
            h.y0(this.k0);
            h.N0(this.k0);
        }
    }

    void B() {
        EditText editText = this.q.n0;
        if (editText == null) {
            return;
        }
        this.k0.setPaddingRelative(k() ? 0 : editText.getPaddingStart(), editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(ax.R6.e.P), editText.getCompoundPaddingBottom());
    }

    CharSequence a() {
        return this.l0;
    }

    ColorStateList b() {
        return this.k0.getTextColors();
    }

    int c() {
        return getPaddingStart() + this.k0.getPaddingStart() + (k() ? this.m0.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) this.m0.getLayoutParams()).getMarginEnd() : 0);
    }

    TextView d() {
        return this.k0;
    }

    CharSequence e() {
        return this.m0.getContentDescription();
    }

    Drawable f() {
        return this.m0.getDrawable();
    }

    int g() {
        return this.p0;
    }

    ImageView.ScaleType h() {
        return this.q0;
    }

    boolean k() {
        return this.m0.getVisibility() == 0;
    }

    void l(boolean z) {
        this.s0 = z;
        C();
    }

    void m() {
        t.d(this.q, this.m0, this.n0);
    }

    void n(CharSequence charSequence) {
        this.l0 = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.k0.setText(charSequence);
        C();
    }

    void o(int i) {
        ax.p115h0.j.m(this.k0, i);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        B();
    }

    void p(ColorStateList colorStateList) {
        this.k0.setTextColor(colorStateList);
    }

    void q(boolean z) {
        this.m0.setCheckable(z);
    }

    void r(CharSequence charSequence) {
        if (e() != charSequence) {
            this.m0.setContentDescription(charSequence);
        }
    }

    void s(Drawable drawable) {
        this.m0.setImageDrawable(drawable);
        if (drawable != null) {
            t.a(this.q, this.m0, this.n0, this.o0);
            z(true);
            m();
        } else {
            z(false);
            u(null);
            v(null);
            r(null);
        }
    }

    void t(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i != this.p0) {
            this.p0 = i;
            t.g(this.m0, i);
        }
    }

    void u(View.OnClickListener onClickListener) {
        t.h(this.m0, onClickListener, this.r0);
    }

    void v(View.OnLongClickListener onLongClickListener) {
        this.r0 = onLongClickListener;
        t.i(this.m0, onLongClickListener);
    }

    void w(ImageView.ScaleType scaleType) {
        this.q0 = scaleType;
        t.j(this.m0, scaleType);
    }

    void x(ColorStateList colorStateList) {
        if (this.n0 != colorStateList) {
            this.n0 = colorStateList;
            t.a(this.q, this.m0, colorStateList, this.o0);
        }
    }

    void y(PorterDuff.Mode mode) {
        if (this.o0 != mode) {
            this.o0 = mode;
            t.a(this.q, this.m0, this.n0, mode);
        }
    }

    void z(boolean z) {
        if (k() != z) {
            this.m0.setVisibility(z ? 0 : 8);
            B();
            C();
        }
    }
}
