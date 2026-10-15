package com.google.android.material.textfield;

import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.A;
import androidx.appcompat.widget.C0601k;
import androidx.appcompat.widget.L;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p202m7.C2160a;
import ax.p373x2.C2441c;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final int J1 = ax.R6.l.l;
    private static final int[][] K1 = {new int[]{R.attr.state_pressed}, new int[0]};
    private int A0;
    int A1;
    private CharSequence B0;
    private boolean B1;
    private boolean C0;
    final C2160a C1;
    private TextView D0;
    private boolean D1;
    private ColorStateList E0;
    private boolean E1;
    private int F0;
    private ValueAnimator F1;
    private C2441c G0;
    private boolean G1;
    private C2441c H0;
    private boolean H1;
    private ColorStateList I0;
    private boolean I1;
    private ColorStateList J0;
    private ColorStateList K0;
    private ColorStateList L0;
    private boolean M0;
    private CharSequence N0;
    private boolean O0;
    private ax.p346v7.h P0;
    private ax.p346v7.h Q0;
    private StateListDrawable R0;
    private boolean S0;
    private ax.p346v7.h T0;
    private ax.p346v7.h U0;
    private ax.p346v7.l V0;
    private boolean W0;
    private final int X0;
    private int Y0;
    private int Z0;
    private int a1;
    private int b1;
    private int c1;
    private int d1;
    private int e1;
    private final Rect f1;
    private final Rect g1;
    private final RectF h1;
    private Typeface i1;
    private Drawable j1;
    private final z k0;
    private int k1;
    private final r l0;
    private final LinkedHashSet<g> l1;
    private final int m0;
    private Drawable m1;
    EditText n0;
    private int n1;
    private CharSequence o0;
    private Drawable o1;
    private int p0;
    private ColorStateList p1;
    private final FrameLayout q;
    private int q0;
    private ColorStateList q1;
    private int r0;
    private int r1;
    private int s0;
    private int s1;
    private final u t0;
    private int t1;
    boolean u0;
    private ColorStateList u1;
    private int v0;
    private int v1;
    private boolean w0;
    private int w1;
    private f x0;
    private int x1;
    private TextView y0;
    private int y1;
    private int z0;
    private int z1;

    class a implements TextWatcher {
        final /* synthetic */ EditText X;
        int q;

        a(EditText editText) {
            this.X = editText;
            this.q = editText.getLineCount();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout textInputLayout = TextInputLayout.this;
            textInputLayout.w0(!textInputLayout.H1);
            TextInputLayout textInputLayout2 = TextInputLayout.this;
            if (textInputLayout2.u0) {
                textInputLayout2.l0(editable);
            }
            if (TextInputLayout.this.C0) {
                TextInputLayout.this.A0(editable);
            }
            int lineCount = this.X.getLineCount();
            int i = this.q;
            if (lineCount != i) {
                if (lineCount < i) {
                    int minimumHeight = this.X.getMinimumHeight();
                    int i2 = TextInputLayout.this.A1;
                    if (minimumHeight != i2) {
                        this.X.setMinimumHeight(i2);
                    }
                }
                this.q = lineCount;
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class b extends C1832a {
        b() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.O0(false);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.l0.h();
        }
    }

    class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.C1.j0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends C1832a {
        private final TextInputLayout d;

        public e(TextInputLayout textInputLayout) {
            this.d = textInputLayout;
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            EditText editText = this.d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.d.getHint();
            CharSequence error = this.d.getError();
            CharSequence placeholderText = this.d.getPlaceholderText();
            int counterMaxLength = this.d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.d.getCounterOverflowDescription();
            boolean zIsEmpty = TextUtils.isEmpty(text);
            boolean zIsEmpty2 = TextUtils.isEmpty(hint);
            boolean zQ = this.d.Q();
            boolean zIsEmpty3 = TextUtils.isEmpty(error);
            boolean z = (zIsEmpty3 && TextUtils.isEmpty(counterOverflowDescription)) ? false : true;
            String string = !zIsEmpty2 ? hint.toString() : HttpUrl.FRAGMENT_ENCODE_SET;
            this.d.k0.A(h);
            if (!zIsEmpty) {
                h.M0(text);
            } else if (!TextUtils.isEmpty(string)) {
                h.M0(string);
                if (!zQ && placeholderText != null) {
                    h.M0(string + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                h.M0(placeholderText);
            }
            if (!TextUtils.isEmpty(string)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    h.x0(string);
                } else {
                    if (!zIsEmpty) {
                        string = ((Object) text) + ", " + string;
                    }
                    h.M0(string);
                }
                h.I0(zIsEmpty);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            h.z0(counterMaxLength);
            if (z) {
                if (zIsEmpty3) {
                    error = counterOverflowDescription;
                }
                h.t0(error);
            }
            View viewT = this.d.t0.t();
            if (viewT != null) {
                h.y0(viewT);
            }
            this.d.l0.m().o(view, h);
        }

        @Override // ax.p035c0.C1832a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            this.d.l0.m().p(view, accessibilityEvent);
        }
    }

    public interface f {
        int a(Editable editable);
    }

    public interface g {
        void a(TextInputLayout textInputLayout);
    }

    public interface h {
        void a(TextInputLayout textInputLayout, int i);
    }

    static class i extends ax.p163k0.a {
        public static final Parcelable.Creator<i> CREATOR = new a();
        CharSequence Y;
        boolean Z;

        class a implements Parcelable.ClassLoaderCreator<i> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public i createFromParcel(Parcel parcel) {
                return new i(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new i(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public i[] newArray(int i) {
                return new i[i];
            }
        }

        i(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.Y = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.Z = parcel.readInt() == 1;
        }

        i(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.Y) + "}";
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.Y, parcel, i);
            parcel.writeInt(this.Z ? 1 : 0);
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.h0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = J1;
        super(ax.C7.a.d(context, attributeSet, i2, i3), attributeSet, i2);
        this.p0 = -1;
        this.q0 = -1;
        this.r0 = -1;
        this.s0 = -1;
        this.t0 = new u(this);
        this.x0 = new f() { // from class: ax.A7.o
            @Override // com.google.android.material.textfield.TextInputLayout.f
            public final int a(Editable editable) {
                return TextInputLayout.c(editable);
            }
        };
        this.f1 = new Rect();
        this.g1 = new Rect();
        this.h1 = new RectF();
        this.l1 = new LinkedHashSet<>();
        C2160a c2160a = new C2160a(this);
        this.C1 = c2160a;
        this.I1 = false;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.q = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        TimeInterpolator timeInterpolator = ax.S6.a.a;
        c2160a.o0(timeInterpolator);
        c2160a.l0(timeInterpolator);
        c2160a.X(8388659);
        int[] iArr = ax.R6.m.Q5;
        int i4 = ax.R6.m.n6;
        int i5 = ax.R6.m.l6;
        int i6 = ax.R6.m.F6;
        int i7 = ax.R6.m.K6;
        int i8 = ax.R6.m.P6;
        L lJ = ax.p202m7.w.j(context2, attributeSet, iArr, i2, i3, i4, i5, i6, i7, i8);
        z zVar = new z(this, lJ);
        this.k0 = zVar;
        this.M0 = lJ.a(ax.R6.m.N6, true);
        setHint(lJ.p(ax.R6.m.V5));
        this.E1 = lJ.a(ax.R6.m.M6, true);
        this.D1 = lJ.a(ax.R6.m.H6, true);
        int i9 = ax.R6.m.X5;
        if (lJ.s(i9)) {
            setMinEms(lJ.k(i9, -1));
        } else {
            int i10 = ax.R6.m.U5;
            if (lJ.s(i10)) {
                setMinWidth(lJ.f(i10, -1));
            }
        }
        int i11 = ax.R6.m.W5;
        if (lJ.s(i11)) {
            setMaxEms(lJ.k(i11, -1));
        } else {
            int i12 = ax.R6.m.T5;
            if (lJ.s(i12)) {
                setMaxWidth(lJ.f(i12, -1));
            }
        }
        this.V0 = ax.p346v7.l.e(context2, attributeSet, i2, i3).m();
        this.X0 = context2.getResources().getDimensionPixelOffset(ax.R6.e.u0);
        this.Z0 = lJ.e(ax.R6.m.a6, 0);
        this.m0 = getResources().getDimensionPixelSize(ax.R6.e.x);
        this.b1 = lJ.f(ax.R6.m.h6, context2.getResources().getDimensionPixelSize(ax.R6.e.v0));
        this.c1 = lJ.f(ax.R6.m.i6, context2.getResources().getDimensionPixelSize(ax.R6.e.w0));
        this.a1 = this.b1;
        float fD = lJ.d(ax.R6.m.e6, -1.0f);
        float fD2 = lJ.d(ax.R6.m.d6, -1.0f);
        float fD3 = lJ.d(ax.R6.m.b6, -1.0f);
        float fD4 = lJ.d(ax.R6.m.c6, -1.0f);
        ax.v7.l.b bVarW = this.V0.w();
        if (fD >= 0.0f) {
            bVarW.C(fD);
        }
        if (fD2 >= 0.0f) {
            bVarW.G(fD2);
        }
        if (fD3 >= 0.0f) {
            bVarW.y(fD3);
        }
        if (fD4 >= 0.0f) {
            bVarW.u(fD4);
        }
        this.V0 = bVarW.m();
        ColorStateList colorStateListB = ax.p282r7.c.b(context2, lJ, ax.R6.m.Y5);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.v1 = defaultColor;
            this.e1 = defaultColor;
            if (colorStateListB.isStateful()) {
                this.w1 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                this.x1 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.y1 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.x1 = this.v1;
                ColorStateList colorStateListA = ax.p226o.a.a(context2, ax.R6.d.c);
                this.w1 = colorStateListA.getColorForState(new int[]{-16842910}, -1);
                this.y1 = colorStateListA.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.e1 = 0;
            this.v1 = 0;
            this.w1 = 0;
            this.x1 = 0;
            this.y1 = 0;
        }
        int i13 = ax.R6.m.S5;
        if (lJ.s(i13)) {
            ColorStateList colorStateListC = lJ.c(i13);
            this.q1 = colorStateListC;
            this.p1 = colorStateListC;
        }
        int i14 = ax.R6.m.f6;
        ColorStateList colorStateListB2 = ax.p282r7.c.b(context2, lJ, i14);
        this.t1 = lJ.b(i14, 0);
        this.r1 = ax.Q.b.c(context2, ax.R6.d.d);
        this.z1 = ax.Q.b.c(context2, ax.R6.d.e);
        this.s1 = ax.Q.b.c(context2, ax.R6.d.f);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i15 = ax.R6.m.g6;
        if (lJ.s(i15)) {
            setBoxStrokeErrorColor(ax.p282r7.c.b(context2, lJ, i15));
        }
        if (lJ.n(i8, -1) != -1) {
            setHintTextAppearance(lJ.n(i8, 0));
        }
        this.K0 = lJ.c(ax.R6.m.p6);
        this.L0 = lJ.c(ax.R6.m.q6);
        int iN = lJ.n(i6, 0);
        CharSequence charSequenceP = lJ.p(ax.R6.m.A6);
        int iK = lJ.k(ax.R6.m.z6, 1);
        boolean zA = lJ.a(ax.R6.m.B6, false);
        int iN2 = lJ.n(i7, 0);
        boolean zA2 = lJ.a(ax.R6.m.J6, false);
        CharSequence charSequenceP2 = lJ.p(ax.R6.m.I6);
        int iN3 = lJ.n(ax.R6.m.X6, 0);
        CharSequence charSequenceP3 = lJ.p(ax.R6.m.W6);
        boolean zA3 = lJ.a(ax.R6.m.j6, false);
        setCounterMaxLength(lJ.k(ax.R6.m.k6, -1));
        this.A0 = lJ.n(i4, 0);
        this.z0 = lJ.n(i5, 0);
        setBoxBackgroundMode(lJ.k(ax.R6.m.Z5, 0));
        setErrorContentDescription(charSequenceP);
        setErrorAccessibilityLiveRegion(iK);
        setCounterOverflowTextAppearance(this.z0);
        setHelperTextTextAppearance(iN2);
        setErrorTextAppearance(iN);
        setCounterTextAppearance(this.A0);
        setPlaceholderText(charSequenceP3);
        setPlaceholderTextAppearance(iN3);
        int i16 = ax.R6.m.G6;
        if (lJ.s(i16)) {
            setErrorTextColor(lJ.c(i16));
        }
        int i17 = ax.R6.m.L6;
        if (lJ.s(i17)) {
            setHelperTextColor(lJ.c(i17));
        }
        int i18 = ax.R6.m.Q6;
        if (lJ.s(i18)) {
            setHintTextColor(lJ.c(i18));
        }
        int i19 = ax.R6.m.o6;
        if (lJ.s(i19)) {
            setCounterTextColor(lJ.c(i19));
        }
        int i20 = ax.R6.m.m6;
        if (lJ.s(i20)) {
            setCounterOverflowTextColor(lJ.c(i20));
        }
        int i21 = ax.R6.m.Y6;
        if (lJ.s(i21)) {
            setPlaceholderTextColor(lJ.c(i21));
        }
        r rVar = new r(this, lJ);
        this.l0 = rVar;
        boolean zA4 = lJ.a(ax.R6.m.R5, true);
        setHintMaxLines(lJ.k(ax.R6.m.O6, 1));
        lJ.x();
        setImportantForAccessibility(2);
        if (Build.VERSION.SDK_INT >= 26) {
            setImportantForAutofill(1);
        }
        frameLayout.addView(zVar);
        frameLayout.addView(rVar);
        addView(frameLayout);
        setEnabled(zA4);
        setHelperTextEnabled(zA2);
        setErrorEnabled(zA);
        setCounterEnabled(zA3);
        setHelperText(charSequenceP2);
    }

    private void A(boolean z) {
        ValueAnimator valueAnimator = this.F1;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.F1.cancel();
        }
        if (z && this.E1) {
            m(1.0f);
        } else {
            this.C1.j0(1.0f);
        }
        this.B1 = false;
        if (C()) {
            W();
        }
        z0();
        this.k0.l(false);
        this.l0.H(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(Editable editable) {
        if (this.x0.a(editable) != 0 || this.B1) {
            M();
        } else {
            g0();
        }
    }

    private C2441c B() {
        C2441c c2441c = new C2441c();
        c2441c.g0(ax.p234o7.i.f(getContext(), ax.R6.c.H, 87));
        c2441c.i0(ax.p234o7.i.g(getContext(), ax.R6.c.N, ax.S6.a.a));
        return c2441c;
    }

    private void B0(boolean z, boolean z2) {
        int defaultColor = this.u1.getDefaultColor();
        int colorForState = this.u1.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.u1.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.d1 = colorForState2;
        } else if (z2) {
            this.d1 = colorForState;
        } else {
            this.d1 = defaultColor;
        }
    }

    private boolean C() {
        return this.M0 && !TextUtils.isEmpty(this.N0) && (this.P0 instanceof com.google.android.material.textfield.h);
    }

    private void D() {
        Iterator<g> it = this.l1.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void E(Canvas canvas) {
        ax.p346v7.h hVar;
        if (this.U0 == null || (hVar = this.T0) == null) {
            return;
        }
        hVar.draw(canvas);
        if (this.n0.isFocused()) {
            Rect bounds = this.U0.getBounds();
            Rect bounds2 = this.T0.getBounds();
            float fB = this.C1.B();
            int iCenterX = bounds2.centerX();
            bounds.left = ax.S6.a.c(iCenterX, bounds2.left, fB);
            bounds.right = ax.S6.a.c(iCenterX, bounds2.right, fB);
            this.U0.draw(canvas);
        }
    }

    private void F(Canvas canvas) {
        if (this.M0) {
            this.C1.k(canvas);
        }
    }

    private void G(boolean z) {
        ValueAnimator valueAnimator = this.F1;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.F1.cancel();
        }
        if (z && this.E1) {
            m(0.0f);
        } else {
            this.C1.j0(0.0f);
        }
        if (C() && ((com.google.android.material.textfield.h) this.P0).z0()) {
            z();
        }
        this.B1 = true;
        M();
        this.k0.l(true);
        this.l0.H(true);
    }

    private ax.p346v7.h H(boolean z) {
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(ax.R6.e.s0);
        float f2 = z ? dimensionPixelOffset : 0.0f;
        EditText editText = this.n0;
        float popupElevation = editText instanceof v ? ((v) editText).getPopupElevation() : getResources().getDimensionPixelOffset(ax.R6.e.w);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(ax.R6.e.n0);
        ax.p346v7.l lVarM = ax.p346v7.l.a().C(f2).G(f2).u(dimensionPixelOffset).y(dimensionPixelOffset).m();
        EditText editText2 = this.n0;
        ax.p346v7.h hVarR = ax.p346v7.h.r(getContext(), popupElevation, editText2 instanceof v ? ((v) editText2).getDropDownBackgroundTintList() : null);
        hVarR.setShapeAppearanceModel(lVarM);
        hVarR.j0(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        return hVarR;
    }

    private static Drawable I(ax.p346v7.h hVar, int i2, int i3, int[][] iArr) {
        return new RippleDrawable(new ColorStateList(iArr, new int[]{ax.p106g7.a.k(i3, i2, 0.1f), i2}), hVar, hVar);
    }

    private int J(int i2, boolean z) {
        int compoundPaddingLeft;
        if (z || getPrefixText() == null) {
            compoundPaddingLeft = (!z || getSuffixText() == null) ? this.n0.getCompoundPaddingLeft() : this.l0.y();
        } else {
            compoundPaddingLeft = this.k0.c();
        }
        return i2 + compoundPaddingLeft;
    }

    private int K(int i2, boolean z) {
        int compoundPaddingRight;
        if (z || getSuffixText() == null) {
            compoundPaddingRight = (!z || getPrefixText() == null) ? this.n0.getCompoundPaddingRight() : this.k0.c();
        } else {
            compoundPaddingRight = this.l0.y();
        }
        return i2 - compoundPaddingRight;
    }

    private static Drawable L(Context context, ax.p346v7.h hVar, int i2, int[][] iArr) {
        int iC = ax.p106g7.a.c(context, ax.R6.c.i, "TextInputLayout");
        ax.p346v7.h hVar2 = new ax.p346v7.h(hVar.I());
        int iK = ax.p106g7.a.k(i2, iC, 0.1f);
        hVar2.g0(new ColorStateList(iArr, new int[]{iK, 0}));
        hVar2.setTint(iC);
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iK, iC});
        ax.p346v7.h hVar3 = new ax.p346v7.h(hVar.I());
        hVar3.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, hVar2, hVar3), hVar});
    }

    private void M() {
        TextView textView = this.D0;
        if (textView == null || !this.C0) {
            return;
        }
        textView.setText((CharSequence) null);
        ax.p373x2.t.a(this.q, this.H0);
        this.D0.setVisibility(4);
    }

    private boolean R() {
        return getHintMaxLines() == 1;
    }

    private boolean S() {
        if (d0()) {
            return true;
        }
        return this.y0 != null && this.w0;
    }

    private boolean U() {
        return this.Y0 == 1 && this.n0.getMinLines() <= 1;
    }

    private void V() {
        q();
        s0();
        C0();
        h0();
        l();
        if (this.Y0 != 0) {
            v0();
        }
        b0();
    }

    private void W() {
        if (C()) {
            RectF rectF = this.h1;
            this.C1.o(rectF, this.n0.getWidth(), this.n0.getGravity());
            if (rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
                return;
            }
            p(rectF);
            rectF.offset(-getPaddingLeft(), ((-getPaddingTop()) - (rectF.height() / 2.0f)) + this.a1);
            rectF.top = 0.0f;
            ((com.google.android.material.textfield.h) this.P0).C0(rectF);
        }
    }

    private void X() {
        if (!C() || this.B1) {
            return;
        }
        z();
        W();
    }

    private static void Y(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                Y((ViewGroup) childAt, z);
            }
        }
    }

    private void a0() {
        TextView textView = this.D0;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public static /* synthetic */ void b(TextInputLayout textInputLayout, StaticLayout.Builder builder) {
        if (Build.VERSION.SDK_INT >= 23) {
            builder.setBreakStrategy(textInputLayout.D0.getBreakStrategy());
        } else {
            textInputLayout.getClass();
        }
    }

    private void b0() {
        EditText editText = this.n0;
        if (editText instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i2 = this.Y0;
                if (i2 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i2 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    public static /* synthetic */ int c(Editable editable) {
        if (editable != null) {
            return editable.length();
        }
        return 0;
    }

    private boolean e0() {
        return (this.l0.G() || ((this.l0.A() && N()) || this.l0.w() != null)) && this.l0.getMeasuredWidth() > 0;
    }

    private boolean f0() {
        return (getStartIconDrawable() != null || (getPrefixText() != null && getPrefixTextView().getVisibility() == 0)) && this.k0.getMeasuredWidth() > 0;
    }

    private void g0() {
        if (this.D0 == null || !this.C0 || TextUtils.isEmpty(this.B0)) {
            return;
        }
        this.D0.setText(this.B0);
        ax.p373x2.t.a(this.q, this.G0);
        this.D0.setVisibility(0);
        this.D0.bringToFront();
    }

    private Drawable getEditTextBoxBackground() {
        EditText editText = this.n0;
        if (!(editText instanceof AutoCompleteTextView) || q.a(editText)) {
            return this.P0;
        }
        int iD = ax.p106g7.a.d(this.n0, ax.p194m.a.w);
        int i2 = this.Y0;
        if (i2 == 2) {
            return L(getContext(), this.P0, iD, K1);
        }
        if (i2 == 1) {
            return I(this.P0, this.e1, iD, K1);
        }
        return null;
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.R0 == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.R0 = stateListDrawable;
            stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.R0.addState(new int[0], H(false));
        }
        return this.R0;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.Q0 == null) {
            this.Q0 = H(true);
        }
        return this.Q0;
    }

    private void h0() {
        if (this.Y0 == 1) {
            if (ax.p282r7.c.i(getContext())) {
                this.Z0 = getResources().getDimensionPixelSize(ax.R6.e.L);
            } else if (ax.p282r7.c.h(getContext())) {
                this.Z0 = getResources().getDimensionPixelSize(ax.R6.e.K);
            }
        }
    }

    private void i0(Rect rect) {
        ax.p346v7.h hVar = this.T0;
        if (hVar != null) {
            int i2 = rect.bottom;
            hVar.setBounds(rect.left, i2 - this.b1, rect.right, i2);
        }
        ax.p346v7.h hVar2 = this.U0;
        if (hVar2 != null) {
            int i3 = rect.bottom;
            hVar2.setBounds(rect.left, i3 - this.c1, rect.right, i3);
        }
    }

    private void j0(int i2) {
        this.C1.s0(i2);
        Rect rect = this.f1;
        ax.p202m7.c.a(this, this.n0, rect);
        this.C1.S(s(rect));
        v0();
        l();
        t0(i2);
    }

    private void k() {
        TextView textView = this.D0;
        if (textView != null) {
            this.q.addView(textView);
            this.D0.setVisibility(0);
        }
    }

    private void k0() {
        if (this.y0 != null) {
            EditText editText = this.n0;
            l0(editText == null ? null : editText.getText());
        }
    }

    private void l() {
        if (this.n0 == null || this.Y0 != 1) {
            return;
        }
        if (!R()) {
            EditText editText = this.n0;
            editText.setPaddingRelative(editText.getPaddingStart(), (int) (this.C1.q() + this.m0), this.n0.getPaddingEnd(), getResources().getDimensionPixelSize(ax.R6.e.G));
        } else if (ax.p282r7.c.i(getContext())) {
            EditText editText2 = this.n0;
            editText2.setPaddingRelative(editText2.getPaddingStart(), getResources().getDimensionPixelSize(ax.R6.e.J), this.n0.getPaddingEnd(), getResources().getDimensionPixelSize(ax.R6.e.I));
        } else if (ax.p282r7.c.h(getContext())) {
            EditText editText3 = this.n0;
            editText3.setPaddingRelative(editText3.getPaddingStart(), getResources().getDimensionPixelSize(ax.R6.e.H), this.n0.getPaddingEnd(), getResources().getDimensionPixelSize(ax.R6.e.G));
        }
    }

    private static void m0(Context context, TextView textView, int i2, int i3, boolean z) {
        textView.setContentDescription(context.getString(z ? ax.R6.k.c : ax.R6.k.b, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    private void n() {
        ax.p346v7.h hVar = this.P0;
        if (hVar == null) {
            return;
        }
        ax.p346v7.l lVarI = hVar.I();
        ax.p346v7.l lVar = this.V0;
        if (lVarI != lVar) {
            this.P0.setShapeAppearanceModel(lVar);
        }
        if (x()) {
            this.P0.m0(this.a1, this.d1);
        }
        int iR = r();
        this.e1 = iR;
        this.P0.g0(ColorStateList.valueOf(iR));
        o();
        s0();
    }

    private void n0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.y0;
        if (textView != null) {
            c0(textView, this.w0 ? this.z0 : this.A0);
            if (!this.w0 && (colorStateList2 = this.I0) != null) {
                this.y0.setTextColor(colorStateList2);
            }
            if (!this.w0 || (colorStateList = this.J0) == null) {
                return;
            }
            this.y0.setTextColor(colorStateList);
        }
    }

    private void o() {
        if (this.T0 == null || this.U0 == null) {
            return;
        }
        if (y()) {
            this.T0.g0(this.n0.isFocused() ? ColorStateList.valueOf(this.r1) : ColorStateList.valueOf(this.d1));
            this.U0.g0(ColorStateList.valueOf(this.d1));
        }
        invalidate();
    }

    private void o0() {
        ColorStateList colorStateList;
        ColorStateList colorStateListH = this.K0;
        if (colorStateListH == null) {
            colorStateListH = ax.p106g7.a.h(getContext(), ax.p194m.a.v);
        }
        EditText editText = this.n0;
        if (editText == null || editText.getTextCursorDrawable() == null) {
            return;
        }
        Drawable drawableMutate = ax.U.a.r(this.n0.getTextCursorDrawable()).mutate();
        if (S() && (colorStateList = this.L0) != null) {
            colorStateListH = colorStateList;
        }
        drawableMutate.setTintList(colorStateListH);
    }

    private void p(RectF rectF) {
        float f2 = rectF.left;
        int i2 = this.X0;
        rectF.left = f2 - i2;
        rectF.right += i2;
    }

    private void q() {
        int i2 = this.Y0;
        if (i2 == 0) {
            this.P0 = null;
            this.T0 = null;
            this.U0 = null;
            return;
        }
        if (i2 == 1) {
            this.P0 = new ax.p346v7.h(this.V0);
            this.T0 = new ax.p346v7.h();
            this.U0 = new ax.p346v7.h();
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException(this.Y0 + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.M0 || (this.P0 instanceof com.google.android.material.textfield.h)) {
                this.P0 = new ax.p346v7.h(this.V0);
            } else {
                this.P0 = com.google.android.material.textfield.h.x0(this.V0);
            }
            this.T0 = null;
            this.U0 = null;
        }
    }

    private int r() {
        return this.Y0 == 1 ? ax.p106g7.a.j(ax.p106g7.a.e(this, ax.R6.c.i, 0), this.e1) : this.e1;
    }

    private void r0() {
        this.n0.setBackground(getEditTextBoxBackground());
    }

    private Rect s(Rect rect) {
        if (this.n0 == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.g1;
        boolean zH = ax.p202m7.z.h(this);
        rect2.bottom = rect.bottom;
        int i2 = this.Y0;
        if (i2 == 1) {
            rect2.left = J(rect.left, zH);
            rect2.top = rect.top + this.Z0;
            rect2.right = K(rect.right, zH);
            return rect2;
        }
        if (i2 != 2) {
            rect2.left = J(rect.left, zH);
            rect2.top = getPaddingTop();
            rect2.right = K(rect.right, zH);
            return rect2;
        }
        rect2.left = rect.left + this.n0.getPaddingLeft();
        rect2.top = rect.top - w();
        rect2.right = rect.right - this.n0.getPaddingRight();
        return rect2;
    }

    private void setEditText(EditText editText) {
        if (this.n0 != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.n0 = editText;
        int i2 = this.p0;
        if (i2 != -1) {
            setMinEms(i2);
        } else {
            setMinWidth(this.r0);
        }
        int i3 = this.q0;
        if (i3 != -1) {
            setMaxEms(i3);
        } else {
            setMaxWidth(this.s0);
        }
        this.S0 = false;
        V();
        setTextInputAccessibilityDelegate(new e(this));
        this.C1.p0(this.n0.getTypeface());
        this.C1.h0(this.n0.getTextSize());
        this.C1.d0(this.n0.getLetterSpacing());
        int gravity = this.n0.getGravity();
        this.C1.X((gravity & (-113)) | 48);
        this.C1.g0(gravity);
        this.A1 = editText.getMinimumHeight();
        this.n0.addTextChangedListener(new a(editText));
        if (this.p1 == null) {
            this.p1 = this.n0.getHintTextColors();
        }
        if (this.M0) {
            if (TextUtils.isEmpty(this.N0)) {
                CharSequence hint = this.n0.getHint();
                this.o0 = hint;
                setHint(hint);
                this.n0.setHint((CharSequence) null);
            }
            this.O0 = true;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            o0();
        }
        if (this.y0 != null) {
            l0(this.n0.getText());
        }
        q0();
        this.t0.f();
        this.k0.bringToFront();
        this.l0.bringToFront();
        D();
        this.l0.x0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        x0(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.N0)) {
            return;
        }
        this.N0 = charSequence;
        this.C1.n0(charSequence);
        if (this.B1) {
            return;
        }
        W();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.C0 == z) {
            return;
        }
        if (z) {
            k();
        } else {
            a0();
            this.D0 = null;
        }
        this.C0 = z;
    }

    private int t(Rect rect, Rect rect2, float f2) {
        return U() ? (int) (rect2.top + f2) : rect.bottom - this.n0.getCompoundPaddingBottom();
    }

    private void t0(int i2) {
        if (this.n0 == null) {
            return;
        }
        float fZ = this.C1.z();
        float height = 0.0f;
        if (this.B0 != null) {
            TextPaint textPaint = new TextPaint(129);
            textPaint.set(this.D0.getPaint());
            textPaint.setTextSize(this.D0.getTextSize());
            textPaint.setTypeface(this.D0.getTypeface());
            textPaint.setLetterSpacing(this.D0.getLetterSpacing());
            try {
                height = ax.p202m7.s.c(this.B0, textPaint, i2).h(getLayoutDirection() == 1).g(true).i(this.D0.getLineSpacingExtra(), this.D0.getLineSpacingMultiplier()).k(new ax.p202m7.t() { // from class: ax.A7.p
                    @Override // ax.p202m7.t
                    public final void a(StaticLayout.Builder builder) {
                        TextInputLayout.b(this.a, builder);
                    }
                }).a().getHeight() + (this.Y0 == 1 ? this.C1.q() + this.Z0 + this.m0 : 0.0f);
            } catch (ax.m7.s.a e2) {
                Log.e("TextInputLayout", e2.getCause().getMessage(), e2);
            }
        }
        float fMax = Math.max(fZ, height);
        if (this.n0.getMeasuredHeight() < fMax) {
            this.n0.setMinimumHeight(Math.round(fMax));
        }
    }

    private int u(Rect rect, float f2) {
        if (U()) {
            return (int) (rect.centerY() - (f2 / 2.0f));
        }
        return (rect.top + this.n0.getCompoundPaddingTop()) - ((this.Y0 != 0 || R()) ? 0 : (int) (this.C1.A() / 2.0f));
    }

    private boolean u0() {
        int iMax;
        if (this.n0 == null || this.n0.getMeasuredHeight() >= (iMax = Math.max(this.l0.getMeasuredHeight(), this.k0.getMeasuredHeight()))) {
            return false;
        }
        this.n0.setMinimumHeight(iMax);
        return true;
    }

    private Rect v(Rect rect) {
        if (this.n0 == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.g1;
        float fA = R() ? this.C1.A() : this.C1.y() * this.C1.w();
        rect2.left = rect.left + this.n0.getCompoundPaddingLeft();
        rect2.top = u(rect, fA);
        rect2.right = rect.right - this.n0.getCompoundPaddingRight();
        rect2.bottom = t(rect, rect2, fA);
        return rect2;
    }

    private void v0() {
        if (this.Y0 != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.q.getLayoutParams();
            int iW = w();
            if (iW != layoutParams.topMargin) {
                layoutParams.topMargin = iW;
                this.q.requestLayout();
            }
        }
    }

    private int w() {
        if (!this.M0) {
            return 0;
        }
        int i2 = this.Y0;
        if (i2 == 0) {
            return (int) this.C1.q();
        }
        if (i2 != 2) {
            return 0;
        }
        return R() ? (int) (this.C1.q() / 2.0f) : Math.max(0, (int) (this.C1.q() - (this.C1.n() / 2.0f)));
    }

    private boolean x() {
        return this.Y0 == 2 && y();
    }

    private void x0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.n0;
        boolean z3 = false;
        boolean z4 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.n0;
        if (editText2 != null && editText2.hasFocus()) {
            z3 = true;
        }
        ColorStateList colorStateList2 = this.p1;
        if (colorStateList2 != null) {
            this.C1.Q(colorStateList2);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.p1;
            this.C1.Q(ColorStateList.valueOf(colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.z1) : this.z1));
        } else if (d0()) {
            this.C1.Q(this.t0.r());
        } else if (this.w0 && (textView = this.y0) != null) {
            this.C1.Q(textView.getTextColors());
        } else if (z3 && (colorStateList = this.q1) != null) {
            this.C1.W(colorStateList);
        }
        if (z4 || !this.D1 || (isEnabled() && z3)) {
            if (z2 || this.B1) {
                A(z);
                return;
            }
            return;
        }
        if (z2 || !this.B1) {
            G(z);
        }
    }

    private boolean y() {
        return this.a1 > -1 && this.d1 != 0;
    }

    private void y0() {
        EditText editText;
        if (this.D0 == null || (editText = this.n0) == null) {
            return;
        }
        this.D0.setGravity(editText.getGravity());
        this.D0.setPadding(this.n0.getCompoundPaddingLeft(), this.n0.getCompoundPaddingTop(), this.n0.getCompoundPaddingRight(), this.n0.getCompoundPaddingBottom());
    }

    private void z() {
        if (C()) {
            ((com.google.android.material.textfield.h) this.P0).A0();
        }
    }

    private void z0() {
        EditText editText = this.n0;
        A0(editText == null ? null : editText.getText());
    }

    void C0() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.P0 == null || this.Y0 == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.n0) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.n0) != null && editText.isHovered())) {
            z = true;
        }
        if (!isEnabled()) {
            this.d1 = this.z1;
        } else if (d0()) {
            if (this.u1 != null) {
                B0(z2, z);
            } else {
                this.d1 = getErrorCurrentTextColors();
            }
        } else if (!this.w0 || (textView = this.y0) == null) {
            if (z2) {
                this.d1 = this.t1;
            } else if (z) {
                this.d1 = this.s1;
            } else {
                this.d1 = this.r1;
            }
        } else if (this.u1 != null) {
            B0(z2, z);
        } else {
            this.d1 = textView.getCurrentTextColor();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            o0();
        }
        this.l0.I();
        Z();
        if (this.Y0 == 2) {
            int i2 = this.a1;
            if (z2 && isEnabled()) {
                this.a1 = this.c1;
            } else {
                this.a1 = this.b1;
            }
            if (this.a1 != i2) {
                X();
            }
        }
        if (this.Y0 == 1) {
            if (!isEnabled()) {
                this.e1 = this.w1;
            } else if (z && !z2) {
                this.e1 = this.y1;
            } else if (z2) {
                this.e1 = this.x1;
            } else {
                this.e1 = this.v1;
            }
        }
        n();
    }

    public boolean N() {
        return this.l0.F();
    }

    public boolean O() {
        return this.t0.A();
    }

    public boolean P() {
        return this.t0.B();
    }

    final boolean Q() {
        return this.B1;
    }

    public boolean T() {
        return this.O0;
    }

    public void Z() {
        this.k0.m();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i2, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.q.addView(view, layoutParams2);
        this.q.setLayoutParams(layoutParams);
        v0();
        setEditText((EditText) view);
    }

    void c0(TextView textView, int i2) {
        try {
            ax.p115h0.j.m(textView, i2);
            if (Build.VERSION.SDK_INT < 23 || textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        ax.p115h0.j.m(textView, ax.p194m.i.c);
        textView.setTextColor(ax.Q.b.c(getContext(), ax.R6.d.a));
    }

    boolean d0() {
        return this.t0.l();
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(26)
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        EditText editText = this.n0;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        if (this.o0 != null) {
            boolean z = this.O0;
            this.O0 = false;
            CharSequence hint = editText.getHint();
            this.n0.setHint(this.o0);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i2);
                return;
            } finally {
                this.n0.setHint(hint);
                this.O0 = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i2);
        onProvideAutofillVirtualStructure(viewStructure, i2);
        viewStructure.setChildCount(this.q.getChildCount());
        for (int i3 = 0; i3 < this.q.getChildCount(); i3++) {
            View childAt = this.q.getChildAt(i3);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i3);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i2);
            if (childAt == this.n0) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.H1 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.H1 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        F(canvas);
        E(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.G1) {
            return;
        }
        this.G1 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        C2160a c2160a = this.C1;
        boolean zM0 = c2160a != null ? c2160a.m0(drawableState) : false;
        if (this.n0 != null) {
            w0(isLaidOut() && isEnabled());
        }
        q0();
        C0();
        if (zM0) {
            invalidate();
        }
        this.G1 = false;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.n0;
        return editText != null ? editText.getBaseline() + getPaddingTop() + w() : super.getBaseline();
    }

    ax.p346v7.h getBoxBackground() {
        int i2 = this.Y0;
        if (i2 == 1 || i2 == 2) {
            return this.P0;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.e1;
    }

    public int getBoxBackgroundMode() {
        return this.Y0;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.Z0;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return ax.p202m7.z.h(this) ? this.V0.j().a(this.h1) : this.V0.l().a(this.h1);
    }

    public float getBoxCornerRadiusBottomStart() {
        return ax.p202m7.z.h(this) ? this.V0.l().a(this.h1) : this.V0.j().a(this.h1);
    }

    public float getBoxCornerRadiusTopEnd() {
        return ax.p202m7.z.h(this) ? this.V0.r().a(this.h1) : this.V0.t().a(this.h1);
    }

    public float getBoxCornerRadiusTopStart() {
        return ax.p202m7.z.h(this) ? this.V0.t().a(this.h1) : this.V0.r().a(this.h1);
    }

    public int getBoxStrokeColor() {
        return this.t1;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.u1;
    }

    public int getBoxStrokeWidth() {
        return this.b1;
    }

    public int getBoxStrokeWidthFocused() {
        return this.c1;
    }

    public int getCounterMaxLength() {
        return this.v0;
    }

    CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.u0 && this.w0 && (textView = this.y0) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.J0;
    }

    public ColorStateList getCounterTextColor() {
        return this.I0;
    }

    public ColorStateList getCursorColor() {
        return this.K0;
    }

    public ColorStateList getCursorErrorColor() {
        return this.L0;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.p1;
    }

    public EditText getEditText() {
        return this.n0;
    }

    public CharSequence getEndIconContentDescription() {
        return this.l0.l();
    }

    public Drawable getEndIconDrawable() {
        return this.l0.n();
    }

    public int getEndIconMinSize() {
        return this.l0.o();
    }

    public int getEndIconMode() {
        return this.l0.p();
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.l0.q();
    }

    CheckableImageButton getEndIconView() {
        return this.l0.r();
    }

    public CharSequence getError() {
        if (this.t0.A()) {
            return this.t0.p();
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.t0.n();
    }

    public CharSequence getErrorContentDescription() {
        return this.t0.o();
    }

    public int getErrorCurrentTextColors() {
        return this.t0.q();
    }

    public Drawable getErrorIconDrawable() {
        return this.l0.s();
    }

    public CharSequence getHelperText() {
        if (this.t0.B()) {
            return this.t0.s();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.t0.u();
    }

    public CharSequence getHint() {
        if (this.M0) {
            return this.N0;
        }
        return null;
    }

    final float getHintCollapsedTextHeight() {
        return this.C1.q();
    }

    final int getHintCurrentCollapsedTextColor() {
        return this.C1.t();
    }

    public int getHintMaxLines() {
        return this.C1.x();
    }

    public ColorStateList getHintTextColor() {
        return this.q1;
    }

    public f getLengthCounter() {
        return this.x0;
    }

    public int getMaxEms() {
        return this.q0;
    }

    public int getMaxWidth() {
        return this.s0;
    }

    public int getMinEms() {
        return this.p0;
    }

    public int getMinWidth() {
        return this.r0;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.l0.u();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.l0.v();
    }

    public CharSequence getPlaceholderText() {
        if (this.C0) {
            return this.B0;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.F0;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.E0;
    }

    public CharSequence getPrefixText() {
        return this.k0.a();
    }

    public ColorStateList getPrefixTextColor() {
        return this.k0.b();
    }

    public TextView getPrefixTextView() {
        return this.k0.d();
    }

    public ax.p346v7.l getShapeAppearanceModel() {
        return this.V0;
    }

    public CharSequence getStartIconContentDescription() {
        return this.k0.e();
    }

    public Drawable getStartIconDrawable() {
        return this.k0.f();
    }

    public int getStartIconMinSize() {
        return this.k0.g();
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.k0.h();
    }

    public CharSequence getSuffixText() {
        return this.l0.w();
    }

    public ColorStateList getSuffixTextColor() {
        return this.l0.x();
    }

    public TextView getSuffixTextView() {
        return this.l0.z();
    }

    public Typeface getTypeface() {
        return this.i1;
    }

    public void j(g gVar) {
        this.l1.add(gVar);
        if (this.n0 != null) {
            gVar.a(this);
        }
    }

    void l0(Editable editable) {
        int iA = this.x0.a(editable);
        boolean z = this.w0;
        int i2 = this.v0;
        if (i2 == -1) {
            this.y0.setText(String.valueOf(iA));
            this.y0.setContentDescription(null);
            this.w0 = false;
        } else {
            this.w0 = iA > i2;
            m0(getContext(), this.y0, iA, this.v0, this.w0);
            if (z != this.w0) {
                n0();
            }
            this.y0.setText(ax.p003a0.a.c().j(getContext().getString(ax.R6.k.d, Integer.valueOf(iA), Integer.valueOf(this.v0))));
        }
        if (this.n0 == null || z == this.w0) {
            return;
        }
        w0(false);
        C0();
        q0();
    }

    void m(float f2) {
        if (this.C1.B() == f2) {
            return;
        }
        if (this.F1 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.F1 = valueAnimator;
            valueAnimator.setInterpolator(ax.p234o7.i.g(getContext(), ax.R6.c.M, ax.S6.a.b));
            this.F1.setDuration(ax.p234o7.i.f(getContext(), ax.R6.c.G, 167));
            this.F1.addUpdateListener(new d());
        }
        this.F1.setFloatValues(this.C1.B(), f2);
        this.F1.start();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.C1.L(configuration);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.l0.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.I1 = false;
        boolean zU0 = u0();
        boolean zP0 = p0();
        if (zU0 || zP0) {
            this.n0.post(new Runnable() { // from class: ax.A7.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.q.n0.requestLayout();
                }
            });
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        EditText editText = this.n0;
        if (editText != null) {
            Rect rect = this.f1;
            ax.p202m7.c.a(this, editText, rect);
            i0(rect);
            if (this.M0) {
                this.C1.h0(this.n0.getTextSize());
                int gravity = this.n0.getGravity();
                this.C1.X((gravity & (-113)) | 48);
                this.C1.g0(gravity);
                this.C1.S(s(rect));
                this.C1.c0(v(rect));
                this.C1.N();
                if (!C() || this.B1) {
                    return;
                }
                W();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (!this.I1) {
            this.l0.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.I1 = true;
        }
        y0();
        this.l0.x0();
        if (R()) {
            return;
        }
        j0((this.n0.getMeasuredWidth() - this.n0.getCompoundPaddingLeft()) - this.n0.getCompoundPaddingRight());
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof i)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.a());
        setError(iVar.Y);
        if (iVar.Z) {
            post(new c());
        }
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        boolean z = i2 == 1;
        if (z != this.W0) {
            float fA = this.V0.r().a(this.h1);
            float fA2 = this.V0.t().a(this.h1);
            ax.p346v7.l lVarM = ax.p346v7.l.a().B(this.V0.s()).F(this.V0.q()).t(this.V0.k()).x(this.V0.i()).C(fA2).G(fA).u(this.V0.l().a(this.h1)).y(this.V0.j().a(this.h1)).m();
            this.W0 = z;
            setShapeAppearanceModel(lVarM);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar = new i(super.onSaveInstanceState());
        if (d0()) {
            iVar.Y = getError();
        }
        iVar.Z = this.l0.E();
        return iVar;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0062  */
    boolean p0() {
        boolean z;
        if (this.n0 == null) {
            return false;
        }
        boolean z2 = true;
        if (f0()) {
            int measuredWidth = this.k0.getMeasuredWidth() - this.n0.getPaddingLeft();
            if (this.j1 == null || this.k1 != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.j1 = colorDrawable;
                this.k1 = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] compoundDrawablesRelative = this.n0.getCompoundDrawablesRelative();
            Drawable drawable = compoundDrawablesRelative[0];
            Drawable drawable2 = this.j1;
            if (drawable != drawable2) {
                this.n0.setCompoundDrawablesRelative(drawable2, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
                z = true;
            } else {
                z = false;
            }
        } else if (this.j1 != null) {
            Drawable[] compoundDrawablesRelative2 = this.n0.getCompoundDrawablesRelative();
            this.n0.setCompoundDrawablesRelative(null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
            this.j1 = null;
            z = true;
        } else {
            z = false;
        }
        if (e0()) {
            int measuredWidth2 = this.l0.z().getMeasuredWidth() - this.n0.getPaddingRight();
            CheckableImageButton checkableImageButtonK = this.l0.k();
            if (checkableImageButtonK != null) {
                measuredWidth2 = measuredWidth2 + checkableImageButtonK.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) checkableImageButtonK.getLayoutParams()).getMarginStart();
            }
            Drawable[] compoundDrawablesRelative3 = this.n0.getCompoundDrawablesRelative();
            Drawable drawable3 = this.m1;
            if (drawable3 != null && this.n1 != measuredWidth2) {
                this.n1 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                this.n0.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.m1, compoundDrawablesRelative3[3]);
                return true;
            }
            if (drawable3 == null) {
                ColorDrawable colorDrawable2 = new ColorDrawable();
                this.m1 = colorDrawable2;
                this.n1 = measuredWidth2;
                colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
            }
            Drawable drawable4 = compoundDrawablesRelative3[2];
            Drawable drawable5 = this.m1;
            if (drawable4 != drawable5) {
                this.o1 = drawable4;
                this.n0.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable5, compoundDrawablesRelative3[3]);
                return true;
            }
        } else if (this.m1 != null) {
            Drawable[] compoundDrawablesRelative4 = this.n0.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative4[2] == this.m1) {
                this.n0.setCompoundDrawablesRelative(compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.o1, compoundDrawablesRelative4[3]);
            } else {
                z2 = z;
            }
            this.m1 = null;
            return z2;
        }
        return z;
    }

    void q0() {
        Drawable background;
        TextView textView;
        EditText editText = this.n0;
        if (editText == null || this.Y0 != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (A.a(background)) {
            background = background.mutate();
        }
        if (d0()) {
            background.setColorFilter(C0601k.e(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.w0 && (textView = this.y0) != null) {
            background.setColorFilter(C0601k.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            ax.U.a.c(background);
            this.n0.refreshDrawableState();
        }
    }

    void s0() {
        EditText editText = this.n0;
        if (editText == null || this.P0 == null) {
            return;
        }
        if ((this.S0 || editText.getBackground() == null) && this.Y0 != 0) {
            r0();
            this.S0 = true;
        }
    }

    public void setBoxBackgroundColor(int i2) {
        if (this.e1 != i2) {
            this.e1 = i2;
            this.v1 = i2;
            this.x1 = i2;
            this.y1 = i2;
            n();
        }
    }

    public void setBoxBackgroundColorResource(int i2) {
        setBoxBackgroundColor(ax.Q.b.c(getContext(), i2));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.v1 = defaultColor;
        this.e1 = defaultColor;
        this.w1 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.x1 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.y1 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        n();
    }

    public void setBoxBackgroundMode(int i2) {
        if (i2 == this.Y0) {
            return;
        }
        this.Y0 = i2;
        if (this.n0 != null) {
            V();
        }
    }

    public void setBoxCollapsedPaddingTop(int i2) {
        this.Z0 = i2;
    }

    public void setBoxCornerFamily(int i2) {
        this.V0 = this.V0.w().A(i2, this.V0.r()).E(i2, this.V0.t()).s(i2, this.V0.j()).w(i2, this.V0.l()).m();
        n();
    }

    public void setBoxStrokeColor(int i2) {
        if (this.t1 != i2) {
            this.t1 = i2;
            C0();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.r1 = colorStateList.getDefaultColor();
            this.z1 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.s1 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.t1 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.t1 != colorStateList.getDefaultColor()) {
            this.t1 = colorStateList.getDefaultColor();
        }
        C0();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.u1 != colorStateList) {
            this.u1 = colorStateList;
            C0();
        }
    }

    public void setBoxStrokeWidth(int i2) {
        this.b1 = i2;
        C0();
    }

    public void setBoxStrokeWidthFocused(int i2) {
        this.c1 = i2;
        C0();
    }

    public void setBoxStrokeWidthFocusedResource(int i2) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i2));
    }

    public void setBoxStrokeWidthResource(int i2) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setCounterEnabled(boolean z) {
        if (this.u0 != z) {
            if (z) {
                androidx.appcompat.widget.x xVar = new androidx.appcompat.widget.x(getContext());
                this.y0 = xVar;
                xVar.setId(ax.R6.g.Q);
                Typeface typeface = this.i1;
                if (typeface != null) {
                    this.y0.setTypeface(typeface);
                }
                this.y0.setMaxLines(1);
                this.t0.e(this.y0, 2);
                ((ViewGroup.MarginLayoutParams) this.y0.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(ax.R6.e.x0));
                n0();
                k0();
            } else {
                this.t0.C(this.y0, 2);
                this.y0 = null;
            }
            this.u0 = z;
        }
    }

    public void setCounterMaxLength(int i2) {
        if (this.v0 != i2) {
            if (i2 > 0) {
                this.v0 = i2;
            } else {
                this.v0 = -1;
            }
            if (this.u0) {
                k0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i2) {
        if (this.z0 != i2) {
            this.z0 = i2;
            n0();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.J0 != colorStateList) {
            this.J0 = colorStateList;
            n0();
        }
    }

    public void setCounterTextAppearance(int i2) {
        if (this.A0 != i2) {
            this.A0 = i2;
            n0();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.I0 != colorStateList) {
            this.I0 = colorStateList;
            n0();
        }
    }

    public void setCursorColor(ColorStateList colorStateList) {
        if (this.K0 != colorStateList) {
            this.K0 = colorStateList;
            o0();
        }
    }

    public void setCursorErrorColor(ColorStateList colorStateList) {
        if (this.L0 != colorStateList) {
            this.L0 = colorStateList;
            if (S()) {
                o0();
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.p1 = colorStateList;
        this.q1 = colorStateList;
        if (this.n0 != null) {
            w0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        Y(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.l0.N(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.l0.O(z);
    }

    public void setEndIconContentDescription(int i2) {
        this.l0.P(i2);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        this.l0.Q(charSequence);
    }

    public void setEndIconDrawable(int i2) {
        this.l0.R(i2);
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.l0.S(drawable);
    }

    public void setEndIconMinSize(int i2) {
        this.l0.T(i2);
    }

    public void setEndIconMode(int i2) {
        this.l0.U(i2);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        this.l0.V(onClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.l0.W(onLongClickListener);
    }

    public void setEndIconScaleType(ImageView.ScaleType scaleType) {
        this.l0.X(scaleType);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        this.l0.Y(colorStateList);
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        this.l0.Z(mode);
    }

    public void setEndIconVisible(boolean z) {
        this.l0.a0(z);
    }

    public void setError(CharSequence charSequence) {
        if (!this.t0.A()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.t0.w();
        } else {
            this.t0.Q(charSequence);
        }
    }

    public void setErrorAccessibilityLiveRegion(int i2) {
        this.t0.E(i2);
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.t0.F(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.t0.G(z);
    }

    public void setErrorIconDrawable(int i2) {
        this.l0.b0(i2);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.l0.c0(drawable);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        this.l0.d0(onClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.l0.e0(onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.l0.f0(colorStateList);
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        this.l0.g0(mode);
    }

    public void setErrorTextAppearance(int i2) {
        this.t0.H(i2);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.t0.I(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.D1 != z) {
            this.D1 = z;
            w0(false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (P()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!P()) {
                setHelperTextEnabled(true);
            }
            this.t0.R(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.t0.L(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.t0.K(z);
    }

    public void setHelperTextTextAppearance(int i2) {
        this.t0.J(i2);
    }

    public void setHint(int i2) {
        setHint(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setHint(CharSequence charSequence) {
        if (this.M0) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.E1 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.M0) {
            this.M0 = z;
            if (z) {
                CharSequence hint = this.n0.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.N0)) {
                        setHint(hint);
                    }
                    this.n0.setHint((CharSequence) null);
                }
                this.O0 = true;
            } else {
                this.O0 = false;
                if (!TextUtils.isEmpty(this.N0) && TextUtils.isEmpty(this.n0.getHint())) {
                    this.n0.setHint(this.N0);
                }
                setHintInternal(null);
            }
            if (this.n0 != null) {
                v0();
            }
        }
    }

    public void setHintMaxLines(int i2) {
        this.C1.T(i2);
        this.C1.e0(i2);
        requestLayout();
    }

    public void setHintTextAppearance(int i2) {
        this.C1.U(i2);
        this.q1 = this.C1.p();
        if (this.n0 != null) {
            w0(false);
            v0();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.q1 != colorStateList) {
            if (this.p1 == null) {
                this.C1.W(colorStateList);
            }
            this.q1 = colorStateList;
            if (this.n0 != null) {
                w0(false);
            }
        }
    }

    public void setLengthCounter(f fVar) {
        this.x0 = fVar;
    }

    public void setMaxEms(int i2) {
        this.q0 = i2;
        EditText editText = this.n0;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMaxEms(i2);
    }

    public void setMaxWidth(int i2) {
        this.s0 = i2;
        EditText editText = this.n0;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMaxWidth(i2);
    }

    public void setMaxWidthResource(int i2) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    public void setMinEms(int i2) {
        this.p0 = i2;
        EditText editText = this.n0;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMinEms(i2);
    }

    public void setMinWidth(int i2) {
        this.r0 = i2;
        EditText editText = this.n0;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMinWidth(i2);
    }

    public void setMinWidthResource(int i2) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i2) {
        this.l0.i0(i2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.l0.j0(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i2) {
        this.l0.k0(i2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.l0.l0(drawable);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        this.l0.m0(z);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.l0.n0(colorStateList);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.l0.o0(mode);
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.D0 == null) {
            androidx.appcompat.widget.x xVar = new androidx.appcompat.widget.x(getContext());
            this.D0 = xVar;
            xVar.setId(ax.R6.g.T);
            this.D0.setImportantForAccessibility(1);
            this.D0.setAccessibilityLiveRegion(1);
            C2441c c2441cB = B();
            this.G0 = c2441cB;
            c2441cB.l0(67L);
            this.H0 = B();
            setPlaceholderTextAppearance(this.F0);
            setPlaceholderTextColor(this.E0);
            C1839d0.l0(this.D0, new b());
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.C0) {
                setPlaceholderTextEnabled(true);
            }
            this.B0 = charSequence;
        }
        z0();
    }

    public void setPlaceholderTextAppearance(int i2) {
        this.F0 = i2;
        TextView textView = this.D0;
        if (textView != null) {
            ax.p115h0.j.m(textView, i2);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.E0 != colorStateList) {
            this.E0 = colorStateList;
            TextView textView = this.D0;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.k0.n(charSequence);
    }

    public void setPrefixTextAppearance(int i2) {
        this.k0.o(i2);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.k0.p(colorStateList);
    }

    public void setShapeAppearanceModel(ax.p346v7.l lVar) {
        ax.p346v7.h hVar = this.P0;
        if (hVar == null || hVar.I() == lVar) {
            return;
        }
        this.V0 = lVar;
        n();
    }

    public void setStartIconCheckable(boolean z) {
        this.k0.q(z);
    }

    public void setStartIconContentDescription(int i2) {
        setStartIconContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        this.k0.r(charSequence);
    }

    public void setStartIconDrawable(int i2) {
        setStartIconDrawable(i2 != 0 ? ax.p226o.a.b(getContext(), i2) : null);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.k0.s(drawable);
    }

    public void setStartIconMinSize(int i2) {
        this.k0.t(i2);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        this.k0.u(onClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.k0.v(onLongClickListener);
    }

    public void setStartIconScaleType(ImageView.ScaleType scaleType) {
        this.k0.w(scaleType);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        this.k0.x(colorStateList);
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        this.k0.y(mode);
    }

    public void setStartIconVisible(boolean z) {
        this.k0.z(z);
    }

    public void setSuffixText(CharSequence charSequence) {
        this.l0.p0(charSequence);
    }

    public void setSuffixTextAppearance(int i2) {
        this.l0.q0(i2);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.l0.r0(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.n0;
        if (editText != null) {
            C1839d0.l0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.i1) {
            this.i1 = typeface;
            this.C1.p0(typeface);
            this.t0.N(typeface);
            TextView textView = this.y0;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    void w0(boolean z) {
        x0(z, false);
    }
}
