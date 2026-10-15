package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.L;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"ViewConstructor"})
class r extends LinearLayout {
    private boolean A0;
    private EditText B0;
    private final AccessibilityManager C0;
    private AccessibilityManager.TouchExplorationStateChangeListener D0;
    private final TextWatcher E0;
    private final TextInputLayout.g F0;
    private final FrameLayout k0;
    private final CheckableImageButton l0;
    private ColorStateList m0;
    private PorterDuff.Mode n0;
    private View.OnLongClickListener o0;
    private final CheckableImageButton p0;
    final TextInputLayout q;
    private final d q0;
    private int r0;
    private final LinkedHashSet<TextInputLayout.h> s0;
    private ColorStateList t0;
    private PorterDuff.Mode u0;
    private int v0;
    private ImageView.ScaleType w0;
    private View.OnLongClickListener x0;
    private CharSequence y0;
    private final TextView z0;

    class a extends ax.p202m7.v {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            r.this.m().a(editable);
        }

        @Override // ax.p202m7.v, android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            r.this.m().b(charSequence, i, i2, i3);
        }
    }

    class b implements TextInputLayout.g {
        b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout) {
            if (r.this.B0 == textInputLayout.getEditText()) {
                return;
            }
            if (r.this.B0 != null) {
                r.this.B0.removeTextChangedListener(r.this.E0);
                if (r.this.B0.getOnFocusChangeListener() == r.this.m().e()) {
                    r.this.B0.setOnFocusChangeListener(null);
                }
            }
            r.this.B0 = textInputLayout.getEditText();
            if (r.this.B0 != null) {
                r.this.B0.addTextChangedListener(r.this.E0);
            }
            r.this.m().n(r.this.B0);
            r rVar = r.this;
            rVar.h0(rVar.m());
        }
    }

    class c implements View.OnAttachStateChangeListener {
        c() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            r.this.g();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            r.this.M();
        }
    }

    private static class d {
        private final SparseArray<s> a = new SparseArray<>();
        private final r b;
        private final int c;
        private final int d;

        d(r rVar, L l) {
            this.b = rVar;
            this.c = l.n(ax.R6.m.t6, 0);
            this.d = l.n(ax.R6.m.S6, 0);
        }

        private s b(int i) {
            if (i == -1) {
                return new g(this.b);
            }
            if (i == 0) {
                return new w(this.b);
            }
            if (i == 1) {
                return new y(this.b, this.d);
            }
            if (i == 2) {
                return new f(this.b);
            }
            if (i == 3) {
                return new p(this.b);
            }
            throw new IllegalArgumentException("Invalid end icon mode: " + i);
        }

        s c(int i) {
            s sVar = this.a.get(i);
            if (sVar != null) {
                return sVar;
            }
            s sVarB = b(i);
            this.a.append(i, sVarB);
            return sVarB;
        }
    }

    r(TextInputLayout textInputLayout, L l) {
        super(textInputLayout.getContext());
        this.r0 = 0;
        this.s0 = new LinkedHashSet<>();
        this.E0 = new a();
        b bVar = new b();
        this.F0 = bVar;
        this.C0 = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.q = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.k0 = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        CheckableImageButton checkableImageButtonI = i(this, layoutInflaterFrom, ax.R6.g.P);
        this.l0 = checkableImageButtonI;
        CheckableImageButton checkableImageButtonI2 = i(frameLayout, layoutInflaterFrom, ax.R6.g.O);
        this.p0 = checkableImageButtonI2;
        this.q0 = new d(this, l);
        androidx.appcompat.widget.x xVar = new androidx.appcompat.widget.x(getContext());
        this.z0 = xVar;
        C(l);
        B(l);
        D(l);
        frameLayout.addView(checkableImageButtonI2);
        addView(xVar);
        addView(frameLayout);
        addView(checkableImageButtonI);
        textInputLayout.j(bVar);
        addOnAttachStateChangeListener(new c());
    }

    private void B(L l) {
        int i = ax.R6.m.T6;
        if (!l.s(i)) {
            int i2 = ax.R6.m.x6;
            if (l.s(i2)) {
                this.t0 = ax.p282r7.c.b(getContext(), l, i2);
            }
            int i3 = ax.R6.m.y6;
            if (l.s(i3)) {
                this.u0 = ax.p202m7.z.i(l.k(i3, -1), null);
            }
        }
        int i4 = ax.R6.m.v6;
        if (l.s(i4)) {
            U(l.k(i4, 0));
            int i5 = ax.R6.m.s6;
            if (l.s(i5)) {
                Q(l.p(i5));
            }
            O(l.a(ax.R6.m.r6, true));
        } else if (l.s(i)) {
            int i6 = ax.R6.m.U6;
            if (l.s(i6)) {
                this.t0 = ax.p282r7.c.b(getContext(), l, i6);
            }
            int i7 = ax.R6.m.V6;
            if (l.s(i7)) {
                this.u0 = ax.p202m7.z.i(l.k(i7, -1), null);
            }
            U(l.a(i, false) ? 1 : 0);
            Q(l.p(ax.R6.m.R6));
        }
        T(l.f(ax.R6.m.u6, getResources().getDimensionPixelSize(ax.R6.e.o0)));
        int i8 = ax.R6.m.w6;
        if (l.s(i8)) {
            X(t.b(l.k(i8, -1)));
        }
    }

    private void C(L l) {
        int i = ax.R6.m.D6;
        if (l.s(i)) {
            this.m0 = ax.p282r7.c.b(getContext(), l, i);
        }
        int i2 = ax.R6.m.E6;
        if (l.s(i2)) {
            this.n0 = ax.p202m7.z.i(l.k(i2, -1), null);
        }
        int i3 = ax.R6.m.C6;
        if (l.s(i3)) {
            c0(l.g(i3));
        }
        this.l0.setContentDescription(getResources().getText(ax.R6.k.f));
        this.l0.setImportantForAccessibility(2);
        this.l0.setClickable(false);
        this.l0.setPressable(false);
        this.l0.setCheckable(false);
        this.l0.setFocusable(false);
    }

    private void D(L l) {
        this.z0.setVisibility(8);
        this.z0.setId(ax.R6.g.V);
        this.z0.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        this.z0.setAccessibilityLiveRegion(1);
        q0(l.n(ax.R6.m.k7, 0));
        int i = ax.R6.m.l7;
        if (l.s(i)) {
            r0(l.c(i));
        }
        p0(l.p(ax.R6.m.j7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        AccessibilityManager accessibilityManager;
        AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener = this.D0;
        if (touchExplorationStateChangeListener == null || (accessibilityManager = this.C0) == null) {
            return;
        }
        accessibilityManager.removeTouchExplorationStateChangeListener(touchExplorationStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.D0 == null || this.C0 == null || !isAttachedToWindow()) {
            return;
        }
        this.C0.addTouchExplorationStateChangeListener(this.D0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0(s sVar) {
        if (this.B0 == null) {
            return;
        }
        if (sVar.e() != null) {
            this.B0.setOnFocusChangeListener(sVar.e());
        }
        if (sVar.g() != null) {
            this.p0.setOnFocusChangeListener(sVar.g());
        }
    }

    private CheckableImageButton i(ViewGroup viewGroup, LayoutInflater layoutInflater, int i) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(ax.R6.i.g, viewGroup, false);
        checkableImageButton.setId(i);
        t.e(checkableImageButton);
        if (ax.p282r7.c.h(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginStart(0);
        }
        return checkableImageButton;
    }

    private void j(int i) {
        Iterator<TextInputLayout.h> it = this.s0.iterator();
        while (it.hasNext()) {
            it.next().a(this.q, i);
        }
    }

    private void s0(s sVar) {
        sVar.s();
        this.D0 = sVar.h();
        g();
    }

    private int t(s sVar) {
        int i = this.q0.c;
        return i == 0 ? sVar.d() : i;
    }

    private void t0(s sVar) {
        M();
        this.D0 = null;
        sVar.u();
    }

    private void u0(boolean z) {
        if (!z || n() == null) {
            t.a(this.q, this.p0, this.t0, this.u0);
            return;
        }
        Drawable drawableMutate = ax.U.a.r(n()).mutate();
        drawableMutate.setTint(this.q.getErrorCurrentTextColors());
        this.p0.setImageDrawable(drawableMutate);
    }

    private void v0() {
        this.k0.setVisibility((this.p0.getVisibility() != 0 || G()) ? 8 : 0);
        setVisibility((F() || G() || ((this.y0 == null || this.A0) ? '\b' : (char) 0) == 0) ? 0 : 8);
    }

    private void w0() {
        this.l0.setVisibility(s() != null && this.q.O() && this.q.d0() ? 0 : 8);
        v0();
        x0();
        if (A()) {
            return;
        }
        this.q.p0();
    }

    private void y0() {
        int visibility = this.z0.getVisibility();
        int i = (this.y0 == null || this.A0) ? 8 : 0;
        if (visibility != i) {
            m().q(i == 0);
        }
        v0();
        this.z0.setVisibility(i);
        this.q.p0();
    }

    boolean A() {
        return this.r0 != 0;
    }

    boolean E() {
        return A() && this.p0.isChecked();
    }

    boolean F() {
        return this.k0.getVisibility() == 0 && this.p0.getVisibility() == 0;
    }

    boolean G() {
        return this.l0.getVisibility() == 0;
    }

    void H(boolean z) {
        this.A0 = z;
        y0();
    }

    void I() {
        w0();
        K();
        J();
        if (m().t()) {
            u0(this.q.d0());
        }
    }

    void J() {
        t.d(this.q, this.p0, this.t0);
    }

    void K() {
        t.d(this.q, this.l0, this.m0);
    }

    void L(boolean z) {
        boolean z2;
        boolean zIsActivated;
        boolean zIsChecked;
        s sVarM = m();
        boolean z3 = true;
        if (!sVarM.l() || (zIsChecked = this.p0.isChecked()) == sVarM.m()) {
            z2 = false;
        } else {
            this.p0.setChecked(!zIsChecked);
            z2 = true;
        }
        if (!sVarM.j() || (zIsActivated = this.p0.isActivated()) == sVarM.k()) {
            z3 = z2;
        } else {
            N(!zIsActivated);
        }
        if (z || z3) {
            J();
        }
    }

    void N(boolean z) {
        this.p0.setActivated(z);
    }

    void O(boolean z) {
        this.p0.setCheckable(z);
    }

    void P(int i) {
        Q(i != 0 ? getResources().getText(i) : null);
    }

    void Q(CharSequence charSequence) {
        if (l() != charSequence) {
            this.p0.setContentDescription(charSequence);
        }
    }

    void R(int i) {
        S(i != 0 ? ax.p226o.a.b(getContext(), i) : null);
    }

    void S(Drawable drawable) {
        this.p0.setImageDrawable(drawable);
        if (drawable != null) {
            t.a(this.q, this.p0, this.t0, this.u0);
            J();
        }
    }

    void T(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (i != this.v0) {
            this.v0 = i;
            t.g(this.p0, i);
            t.g(this.l0, i);
        }
    }

    void U(int i) {
        if (this.r0 == i) {
            return;
        }
        t0(m());
        int i2 = this.r0;
        this.r0 = i;
        j(i2);
        a0(i != 0);
        s sVarM = m();
        R(t(sVarM));
        P(sVarM.c());
        O(sVarM.l());
        if (!sVarM.i(this.q.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + this.q.getBoxBackgroundMode() + " is not supported by the end icon mode " + i);
        }
        s0(sVarM);
        V(sVarM.f());
        EditText editText = this.B0;
        if (editText != null) {
            sVarM.n(editText);
            h0(sVarM);
        }
        t.a(this.q, this.p0, this.t0, this.u0);
        L(true);
    }

    void V(View.OnClickListener onClickListener) {
        t.h(this.p0, onClickListener, this.x0);
    }

    void W(View.OnLongClickListener onLongClickListener) {
        this.x0 = onLongClickListener;
        t.i(this.p0, onLongClickListener);
    }

    void X(ImageView.ScaleType scaleType) {
        this.w0 = scaleType;
        t.j(this.p0, scaleType);
        t.j(this.l0, scaleType);
    }

    void Y(ColorStateList colorStateList) {
        if (this.t0 != colorStateList) {
            this.t0 = colorStateList;
            t.a(this.q, this.p0, colorStateList, this.u0);
        }
    }

    void Z(PorterDuff.Mode mode) {
        if (this.u0 != mode) {
            this.u0 = mode;
            t.a(this.q, this.p0, this.t0, mode);
        }
    }

    void a0(boolean z) {
        if (F() != z) {
            this.p0.setVisibility(z ? 0 : 8);
            v0();
            x0();
            this.q.p0();
        }
    }

    void b0(int i) {
        c0(i != 0 ? ax.p226o.a.b(getContext(), i) : null);
        K();
    }

    void c0(Drawable drawable) {
        this.l0.setImageDrawable(drawable);
        w0();
        t.a(this.q, this.l0, this.m0, this.n0);
    }

    void d0(View.OnClickListener onClickListener) {
        t.h(this.l0, onClickListener, this.o0);
    }

    void e0(View.OnLongClickListener onLongClickListener) {
        this.o0 = onLongClickListener;
        t.i(this.l0, onLongClickListener);
    }

    void f0(ColorStateList colorStateList) {
        if (this.m0 != colorStateList) {
            this.m0 = colorStateList;
            t.a(this.q, this.l0, colorStateList, this.n0);
        }
    }

    void g0(PorterDuff.Mode mode) {
        if (this.n0 != mode) {
            this.n0 = mode;
            t.a(this.q, this.l0, this.m0, mode);
        }
    }

    void h() {
        this.p0.performClick();
        this.p0.jumpDrawablesToCurrentState();
    }

    void i0(int i) {
        j0(i != 0 ? getResources().getText(i) : null);
    }

    void j0(CharSequence charSequence) {
        this.p0.setContentDescription(charSequence);
    }

    CheckableImageButton k() {
        if (G()) {
            return this.l0;
        }
        if (A() && F()) {
            return this.p0;
        }
        return null;
    }

    void k0(int i) {
        l0(i != 0 ? ax.p226o.a.b(getContext(), i) : null);
    }

    CharSequence l() {
        return this.p0.getContentDescription();
    }

    void l0(Drawable drawable) {
        this.p0.setImageDrawable(drawable);
    }

    s m() {
        return this.q0.c(this.r0);
    }

    void m0(boolean z) {
        if (z && this.r0 != 1) {
            U(1);
        } else {
            if (z) {
                return;
            }
            U(0);
        }
    }

    Drawable n() {
        return this.p0.getDrawable();
    }

    void n0(ColorStateList colorStateList) {
        this.t0 = colorStateList;
        t.a(this.q, this.p0, colorStateList, this.u0);
    }

    int o() {
        return this.v0;
    }

    void o0(PorterDuff.Mode mode) {
        this.u0 = mode;
        t.a(this.q, this.p0, this.t0, mode);
    }

    int p() {
        return this.r0;
    }

    void p0(CharSequence charSequence) {
        this.y0 = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.z0.setText(charSequence);
        y0();
    }

    ImageView.ScaleType q() {
        return this.w0;
    }

    void q0(int i) {
        ax.p115h0.j.m(this.z0, i);
    }

    CheckableImageButton r() {
        return this.p0;
    }

    void r0(ColorStateList colorStateList) {
        this.z0.setTextColor(colorStateList);
    }

    Drawable s() {
        return this.l0.getDrawable();
    }

    CharSequence u() {
        return this.p0.getContentDescription();
    }

    Drawable v() {
        return this.p0.getDrawable();
    }

    CharSequence w() {
        return this.y0;
    }

    ColorStateList x() {
        return this.z0.getTextColors();
    }

    void x0() {
        if (this.q.n0 == null) {
            return;
        }
        this.z0.setPaddingRelative(getContext().getResources().getDimensionPixelSize(ax.R6.e.P), this.q.n0.getPaddingTop(), (F() || G()) ? 0 : this.q.n0.getPaddingEnd(), this.q.n0.getPaddingBottom());
    }

    int y() {
        return getPaddingEnd() + this.z0.getPaddingEnd() + ((F() || G()) ? this.p0.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) this.p0.getLayoutParams()).getMarginStart() : 0);
    }

    TextView z() {
        return this.z0;
    }
}
