package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import ax.R6.m;
import ax.p154j7.f;
import ax.p202m7.u;
import ax.p202m7.w;
import ax.p202m7.z;
import ax.p282r7.c;
import ax.p282r7.e;
import ax.p346v7.h;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class a extends h implements ax.U.b, Drawable.Callback, u.b {
    private static final int[] Z1 = {R.attr.state_enabled};
    private static final ShapeDrawable a2 = new ShapeDrawable(new OvalShape());
    private final RectF A1;
    private final PointF B1;
    private final Path C1;
    private final u D1;
    private int E1;
    private int F1;
    private int G1;
    private int H1;
    private int I1;
    private int J1;
    private boolean K1;
    private int L1;
    private int M1;
    private ColorFilter N1;
    private PorterDuffColorFilter O1;
    private ColorStateList P0;
    private ColorStateList P1;
    private ColorStateList Q0;
    private PorterDuff.Mode Q1;
    private float R0;
    private int[] R1;
    private float S0;
    private boolean S1;
    private ColorStateList T0;
    private ColorStateList T1;
    private float U0;
    private WeakReference<InterfaceC0572a> U1;
    private ColorStateList V0;
    private TextUtils.TruncateAt V1;
    private CharSequence W0;
    private boolean W1;
    private boolean X0;
    private int X1;
    private Drawable Y0;
    private boolean Y1;
    private ColorStateList Z0;
    private float a1;
    private boolean b1;
    private boolean c1;
    private Drawable d1;
    private Drawable e1;
    private ColorStateList f1;
    private float g1;
    private CharSequence h1;
    private boolean i1;
    private boolean j1;
    private Drawable k1;
    private ColorStateList l1;
    private ax.S6.h m1;
    private ax.S6.h n1;
    private float o1;
    private float p1;
    private float q1;
    private float r1;
    private float s1;
    private float t1;
    private float u1;
    private float v1;
    private final Context w1;
    private final Paint x1;
    private final Paint y1;
    private final Paint.FontMetrics z1;

    /* JADX INFO: renamed from: com.google.android.material.chip.a$a, reason: collision with other inner class name */
    public interface InterfaceC0572a {
        void a();
    }

    private a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.S0 = -1.0f;
        this.x1 = new Paint(1);
        this.z1 = new Paint.FontMetrics();
        this.A1 = new RectF();
        this.B1 = new PointF();
        this.C1 = new Path();
        this.M1 = 255;
        this.Q1 = PorterDuff.Mode.SRC_IN;
        this.U1 = new WeakReference<>(null);
        U(context);
        this.w1 = context;
        u uVar = new u(this);
        this.D1 = uVar;
        this.W0 = HttpUrl.FRAGMENT_ENCODE_SET;
        uVar.g().density = context.getResources().getDisplayMetrics().density;
        this.y1 = null;
        int[] iArr = Z1;
        setState(iArr);
        y2(iArr);
        this.W1 = true;
        a2.setTint(-1);
    }

    private void A0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (c3()) {
            float f = this.v1 + this.u1;
            if (ax.U.a.f(this) == 0) {
                float f2 = rect.right - f;
                rectF.right = f2;
                rectF.left = f2 - this.g1;
            } else {
                float f3 = rect.left + f;
                rectF.left = f3;
                rectF.right = f3 + this.g1;
            }
            float fExactCenterY = rect.exactCenterY();
            float f4 = this.g1;
            float f5 = fExactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    private void B0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (c3()) {
            float f = this.v1 + this.u1 + this.g1 + this.t1 + this.s1;
            if (ax.U.a.f(this) == 0) {
                float f2 = rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private void D0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.W0 != null) {
            float fY0 = this.o1 + y0() + this.r1;
            float fC0 = this.v1 + C0() + this.s1;
            if (ax.U.a.f(this) == 0) {
                rectF.left = rect.left + fY0;
                rectF.right = rect.right - fC0;
            } else {
                rectF.left = rect.left + fC0;
                rectF.right = rect.right - fY0;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private static boolean D1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private float E0() {
        this.D1.g().getFontMetrics(this.z1);
        Paint.FontMetrics fontMetrics = this.z1;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private static boolean E1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private static boolean F1(e eVar) {
        return (eVar == null || eVar.j() == null || !eVar.j().isStateful()) ? false : true;
    }

    private boolean G0() {
        return this.j1 && this.k1 != null && this.i1;
    }

    private void G1(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayI = w.i(this.w1, attributeSet, m.C0, i, i2, new int[0]);
        this.Y1 = typedArrayI.hasValue(m.o1);
        o2(c.a(this.w1, typedArrayI, m.b1));
        S1(c.a(this.w1, typedArrayI, m.O0));
        g2(typedArrayI.getDimension(m.W0, 0.0f));
        int i3 = m.P0;
        if (typedArrayI.hasValue(i3)) {
            U1(typedArrayI.getDimension(i3, 0.0f));
        }
        k2(c.a(this.w1, typedArrayI, m.Z0));
        m2(typedArrayI.getDimension(m.a1, 0.0f));
        L2(c.a(this.w1, typedArrayI, m.n1));
        Q2(typedArrayI.getText(m.I0));
        e eVarG = c.g(this.w1, typedArrayI, m.D0);
        eVarG.o(typedArrayI.getDimension(m.E0, eVarG.k()));
        if (Build.VERSION.SDK_INT < 23) {
            eVarG.n(c.a(this.w1, typedArrayI, m.F0));
        }
        R2(eVarG);
        int i4 = typedArrayI.getInt(m.G0, 0);
        if (i4 == 1) {
            D2(TextUtils.TruncateAt.START);
        } else if (i4 == 2) {
            D2(TextUtils.TruncateAt.MIDDLE);
        } else if (i4 == 3) {
            D2(TextUtils.TruncateAt.END);
        }
        f2(typedArrayI.getBoolean(m.V0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            f2(typedArrayI.getBoolean(m.S0, false));
        }
        Y1(c.d(this.w1, typedArrayI, m.R0));
        int i5 = m.U0;
        if (typedArrayI.hasValue(i5)) {
            c2(c.a(this.w1, typedArrayI, i5));
        }
        a2(typedArrayI.getDimension(m.T0, -1.0f));
        B2(typedArrayI.getBoolean(m.i1, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            B2(typedArrayI.getBoolean(m.d1, false));
        }
        p2(c.d(this.w1, typedArrayI, m.c1));
        z2(c.a(this.w1, typedArrayI, m.h1));
        u2(typedArrayI.getDimension(m.f1, 0.0f));
        K1(typedArrayI.getBoolean(m.J0, false));
        R1(typedArrayI.getBoolean(m.N0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            R1(typedArrayI.getBoolean(m.L0, false));
        }
        M1(c.d(this.w1, typedArrayI, m.K0));
        int i6 = m.M0;
        if (typedArrayI.hasValue(i6)) {
            O1(c.a(this.w1, typedArrayI, i6));
        }
        O2(ax.S6.h.b(this.w1, typedArrayI, m.p1));
        E2(ax.S6.h.b(this.w1, typedArrayI, m.k1));
        i2(typedArrayI.getDimension(m.Y0, 0.0f));
        I2(typedArrayI.getDimension(m.m1, 0.0f));
        G2(typedArrayI.getDimension(m.l1, 0.0f));
        W2(typedArrayI.getDimension(m.r1, 0.0f));
        T2(typedArrayI.getDimension(m.q1, 0.0f));
        w2(typedArrayI.getDimension(m.g1, 0.0f));
        r2(typedArrayI.getDimension(m.e1, 0.0f));
        W1(typedArrayI.getDimension(m.Q0, 0.0f));
        K2(typedArrayI.getDimensionPixelSize(m.H0, SubsamplingScaleImageView.TILE_SIZE_AUTO));
        typedArrayI.recycle();
    }

    public static a H0(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.G1(attributeSet, i, i2);
        return aVar;
    }

    private void I0(Canvas canvas, Rect rect) {
        if (a3()) {
            x0(rect, this.A1);
            RectF rectF = this.A1;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.k1.setBounds(0, 0, (int) this.A1.width(), (int) this.A1.height());
            this.k1.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private boolean I1(int[] iArr, int[] iArr2) {
        boolean z;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.P0;
        int iQ = q(colorStateList != null ? colorStateList.getColorForState(iArr, this.E1) : 0);
        boolean state = true;
        if (this.E1 != iQ) {
            this.E1 = iQ;
            zOnStateChange = true;
        }
        ColorStateList colorStateList2 = this.Q0;
        int iQ2 = q(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.F1) : 0);
        if (this.F1 != iQ2) {
            this.F1 = iQ2;
            zOnStateChange = true;
        }
        int iJ = ax.p106g7.a.j(iQ, iQ2);
        if ((this.G1 != iJ) | (B() == null)) {
            this.G1 = iJ;
            g0(ColorStateList.valueOf(iJ));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.T0;
        int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.H1) : 0;
        if (this.H1 != colorForState) {
            this.H1 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.T1 == null || !ax.p298s7.a.f(iArr)) ? 0 : this.T1.getColorForState(iArr, this.I1);
        if (this.I1 != colorForState2) {
            this.I1 = colorForState2;
            if (this.S1) {
                zOnStateChange = true;
            }
        }
        int colorForState3 = (this.D1.e() == null || this.D1.e().j() == null) ? 0 : this.D1.e().j().getColorForState(iArr, this.J1);
        if (this.J1 != colorForState3) {
            this.J1 = colorForState3;
            zOnStateChange = true;
        }
        boolean z2 = z1(getState(), R.attr.state_checked) && this.i1;
        if (this.K1 == z2 || this.k1 == null) {
            z = false;
        } else {
            float fY0 = y0();
            this.K1 = z2;
            if (fY0 != y0()) {
                zOnStateChange = true;
                z = true;
            } else {
                zOnStateChange = true;
                z = false;
            }
        }
        ColorStateList colorStateList4 = this.P1;
        int colorForState4 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.L1) : 0;
        if (this.L1 != colorForState4) {
            this.L1 = colorForState4;
            this.O1 = f.l(this, this.P1, this.Q1);
        } else {
            state = zOnStateChange;
        }
        if (E1(this.Y0)) {
            state |= this.Y0.setState(iArr);
        }
        if (E1(this.k1)) {
            state |= this.k1.setState(iArr);
        }
        if (E1(this.d1)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.d1.setState(iArr3);
        }
        if (E1(this.e1)) {
            state |= this.e1.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z) {
            H1();
        }
        return state;
    }

    private void J0(Canvas canvas, Rect rect) {
        if (this.Y1) {
            return;
        }
        this.x1.setColor(this.F1);
        this.x1.setStyle(Paint.Style.FILL);
        this.x1.setColorFilter(x1());
        this.A1.set(rect);
        canvas.drawRoundRect(this.A1, U0(), U0(), this.x1);
    }

    private void K0(Canvas canvas, Rect rect) {
        if (b3()) {
            x0(rect, this.A1);
            RectF rectF = this.A1;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.Y0.setBounds(0, 0, (int) this.A1.width(), (int) this.A1.height());
            this.Y0.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void L0(Canvas canvas, Rect rect) {
        if (this.U0 <= 0.0f || this.Y1) {
            return;
        }
        this.x1.setColor(this.H1);
        this.x1.setStyle(Paint.Style.STROKE);
        if (!this.Y1) {
            this.x1.setColorFilter(x1());
        }
        RectF rectF = this.A1;
        float f = rect.left;
        float f2 = this.U0;
        rectF.set(f + (f2 / 2.0f), rect.top + (f2 / 2.0f), rect.right - (f2 / 2.0f), rect.bottom - (f2 / 2.0f));
        float f3 = this.S0 - (this.U0 / 2.0f);
        canvas.drawRoundRect(this.A1, f3, f3, this.x1);
    }

    private void M0(Canvas canvas, Rect rect) {
        if (this.Y1) {
            return;
        }
        this.x1.setColor(this.E1);
        this.x1.setStyle(Paint.Style.FILL);
        this.A1.set(rect);
        canvas.drawRoundRect(this.A1, U0(), U0(), this.x1);
    }

    private void N0(Canvas canvas, Rect rect) {
        if (c3()) {
            A0(rect, this.A1);
            RectF rectF = this.A1;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.d1.setBounds(0, 0, (int) this.A1.width(), (int) this.A1.height());
            this.e1.setBounds(this.d1.getBounds());
            this.e1.jumpToCurrentState();
            this.e1.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void O0(Canvas canvas, Rect rect) {
        this.x1.setColor(this.I1);
        this.x1.setStyle(Paint.Style.FILL);
        this.A1.set(rect);
        if (!this.Y1) {
            canvas.drawRoundRect(this.A1, U0(), U0(), this.x1);
        } else {
            l(new RectF(rect), this.C1);
            super.u(canvas, this.x1, this.C1, x());
        }
    }

    private void P0(Canvas canvas, Rect rect) {
        Canvas canvas2;
        Paint paint = this.y1;
        if (paint != null) {
            paint.setColor(ax.T.a.k(-16777216, 127));
            canvas.drawRect(rect, this.y1);
            if (b3() || a3()) {
                x0(rect, this.A1);
                canvas.drawRect(this.A1, this.y1);
            }
            if (this.W0 != null) {
                canvas2 = canvas;
                canvas2.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.y1);
            } else {
                canvas2 = canvas;
            }
            if (c3()) {
                A0(rect, this.A1);
                canvas2.drawRect(this.A1, this.y1);
            }
            this.y1.setColor(ax.T.a.k(-65536, 127));
            z0(rect, this.A1);
            canvas2.drawRect(this.A1, this.y1);
            this.y1.setColor(ax.T.a.k(-16711936, 127));
            B0(rect, this.A1);
            canvas2.drawRect(this.A1, this.y1);
        }
    }

    private void Q0(Canvas canvas, Rect rect) {
        if (this.W0 != null) {
            Paint.Align alignF0 = F0(rect, this.B1);
            D0(rect, this.A1);
            if (this.D1.e() != null) {
                this.D1.g().drawableState = getState();
                this.D1.n(this.w1);
            }
            this.D1.g().setTextAlign(alignF0);
            int iSave = 0;
            boolean z = Math.round(this.D1.h(t1().toString())) > Math.round(this.A1.width());
            if (z) {
                iSave = canvas.save();
                canvas.clipRect(this.A1);
            }
            CharSequence charSequenceEllipsize = this.W0;
            if (z && this.V1 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.D1.g(), this.A1.width(), this.V1);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.B1;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.D1.g());
            if (z) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    private boolean a3() {
        return this.j1 && this.k1 != null && this.K1;
    }

    private boolean b3() {
        return this.X0 && this.Y0 != null;
    }

    private boolean c3() {
        return this.c1 && this.d1 != null;
    }

    private void d3(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void e3() {
        this.T1 = this.S1 ? ax.p298s7.a.e(this.V0) : null;
    }

    private void f3() {
        this.e1 = new RippleDrawable(ax.p298s7.a.e(r1()), this.d1, a2);
    }

    private float l1() {
        Drawable drawable = this.K1 ? this.k1 : this.Y0;
        float fCeil = this.a1;
        if (fCeil <= 0.0f && drawable != null) {
            fCeil = (float) Math.ceil(z.c(this.w1, 24));
            if (drawable.getIntrinsicHeight() <= fCeil) {
                return drawable.getIntrinsicHeight();
            }
        }
        return fCeil;
    }

    private float m1() {
        Drawable drawable = this.K1 ? this.k1 : this.Y0;
        float f = this.a1;
        return (f > 0.0f || drawable == null) ? f : drawable.getIntrinsicWidth();
    }

    private void o2(ColorStateList colorStateList) {
        if (this.P0 != colorStateList) {
            this.P0 = colorStateList;
            onStateChange(getState());
        }
    }

    private void w0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        ax.U.a.m(drawable, ax.U.a.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.d1) {
            if (drawable.isStateful()) {
                drawable.setState(i1());
            }
            drawable.setTintList(this.f1);
            return;
        }
        Drawable drawable2 = this.Y0;
        if (drawable == drawable2 && this.b1) {
            drawable2.setTintList(this.Z0);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    private void x0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (b3() || a3()) {
            float f = this.o1 + this.p1;
            float fM1 = m1();
            if (ax.U.a.f(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + fM1;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - fM1;
            }
            float fL1 = l1();
            float fExactCenterY = rect.exactCenterY() - (fL1 / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fL1;
        }
    }

    private ColorFilter x1() {
        ColorFilter colorFilter = this.N1;
        return colorFilter != null ? colorFilter : this.O1;
    }

    private void z0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (c3()) {
            float f = this.v1 + this.u1 + this.g1 + this.t1 + this.s1;
            if (ax.U.a.f(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    private static boolean z1(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public boolean A1() {
        return this.i1;
    }

    public void A2(int i) {
        z2(ax.p226o.a.a(this.w1, i));
    }

    public boolean B1() {
        return E1(this.d1);
    }

    public void B2(boolean z) {
        if (this.c1 != z) {
            boolean zC3 = c3();
            this.c1 = z;
            boolean zC4 = c3();
            if (zC3 != zC4) {
                if (zC4) {
                    w0(this.d1);
                } else {
                    d3(this.d1);
                }
                invalidateSelf();
                H1();
            }
        }
    }

    float C0() {
        if (c3()) {
            return this.t1 + this.g1 + this.u1;
        }
        return 0.0f;
    }

    public boolean C1() {
        return this.c1;
    }

    public void C2(InterfaceC0572a interfaceC0572a) {
        this.U1 = new WeakReference<>(interfaceC0572a);
    }

    public void D2(TextUtils.TruncateAt truncateAt) {
        this.V1 = truncateAt;
    }

    public void E2(ax.S6.h hVar) {
        this.n1 = hVar;
    }

    Paint.Align F0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.W0 != null) {
            float fY0 = this.o1 + y0() + this.r1;
            if (ax.U.a.f(this) == 0) {
                pointF.x = rect.left + fY0;
            } else {
                pointF.x = rect.right - fY0;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - E0();
        }
        return align;
    }

    public void F2(int i) {
        E2(ax.S6.h.c(this.w1, i));
    }

    public void G2(float f) {
        if (this.q1 != f) {
            float fY0 = y0();
            this.q1 = f;
            float fY1 = y0();
            invalidateSelf();
            if (fY0 != fY1) {
                H1();
            }
        }
    }

    protected void H1() {
        InterfaceC0572a interfaceC0572a = this.U1.get();
        if (interfaceC0572a != null) {
            interfaceC0572a.a();
        }
    }

    public void H2(int i) {
        G2(this.w1.getResources().getDimension(i));
    }

    public void I2(float f) {
        if (this.p1 != f) {
            float fY0 = y0();
            this.p1 = f;
            float fY1 = y0();
            invalidateSelf();
            if (fY0 != fY1) {
                H1();
            }
        }
    }

    boolean J1(boolean z) {
        if (this.d1 != null) {
            return y2(z ? new int[]{R.attr.state_pressed, R.attr.state_enabled} : Z1);
        }
        return false;
    }

    public void J2(int i) {
        I2(this.w1.getResources().getDimension(i));
    }

    public void K1(boolean z) {
        if (this.i1 != z) {
            this.i1 = z;
            float fY0 = y0();
            if (!z && this.K1) {
                this.K1 = false;
            }
            float fY1 = y0();
            invalidateSelf();
            if (fY0 != fY1) {
                H1();
            }
        }
    }

    public void K2(int i) {
        this.X1 = i;
    }

    public void L1(int i) {
        K1(this.w1.getResources().getBoolean(i));
    }

    public void L2(ColorStateList colorStateList) {
        if (this.V0 != colorStateList) {
            this.V0 = colorStateList;
            e3();
            onStateChange(getState());
        }
    }

    public void M1(Drawable drawable) {
        if (this.k1 != drawable) {
            float fY0 = y0();
            this.k1 = drawable;
            float fY1 = y0();
            d3(this.k1);
            w0(this.k1);
            invalidateSelf();
            if (fY0 != fY1) {
                H1();
            }
        }
    }

    public void M2(int i) {
        L2(ax.p226o.a.a(this.w1, i));
    }

    public void N1(int i) {
        M1(ax.p226o.a.b(this.w1, i));
    }

    void N2(boolean z) {
        this.W1 = z;
    }

    public void O1(ColorStateList colorStateList) {
        if (this.l1 != colorStateList) {
            this.l1 = colorStateList;
            if (G0()) {
                this.k1.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void O2(ax.S6.h hVar) {
        this.m1 = hVar;
    }

    public void P1(int i) {
        O1(ax.p226o.a.a(this.w1, i));
    }

    public void P2(int i) {
        O2(ax.S6.h.c(this.w1, i));
    }

    public void Q1(int i) {
        R1(this.w1.getResources().getBoolean(i));
    }

    public void Q2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (TextUtils.equals(this.W0, charSequence)) {
            return;
        }
        this.W0 = charSequence;
        this.D1.m(true);
        invalidateSelf();
        H1();
    }

    public Drawable R0() {
        return this.k1;
    }

    public void R1(boolean z) {
        if (this.j1 != z) {
            boolean zA3 = a3();
            this.j1 = z;
            boolean zA4 = a3();
            if (zA3 != zA4) {
                if (zA4) {
                    w0(this.k1);
                } else {
                    d3(this.k1);
                }
                invalidateSelf();
                H1();
            }
        }
    }

    public void R2(e eVar) {
        this.D1.k(eVar, this.w1);
    }

    public ColorStateList S0() {
        return this.l1;
    }

    public void S1(ColorStateList colorStateList) {
        if (this.Q0 != colorStateList) {
            this.Q0 = colorStateList;
            onStateChange(getState());
        }
    }

    public void S2(int i) {
        R2(new e(this.w1, i));
    }

    public ColorStateList T0() {
        return this.Q0;
    }

    public void T1(int i) {
        S1(ax.p226o.a.a(this.w1, i));
    }

    public void T2(float f) {
        if (this.s1 != f) {
            this.s1 = f;
            invalidateSelf();
            H1();
        }
    }

    public float U0() {
        return this.Y1 ? N() : this.S0;
    }

    @Deprecated
    public void U1(float f) {
        if (this.S0 != f) {
            this.S0 = f;
            setShapeAppearanceModel(I().x(f));
        }
    }

    public void U2(int i) {
        T2(this.w1.getResources().getDimension(i));
    }

    public float V0() {
        return this.v1;
    }

    @Deprecated
    public void V1(int i) {
        U1(this.w1.getResources().getDimension(i));
    }

    public void V2(float f) {
        e eVarU1 = u1();
        if (eVarU1 != null) {
            eVarU1.o(f);
            this.D1.g().setTextSize(f);
            a();
        }
    }

    public Drawable W0() {
        Drawable drawable = this.Y0;
        if (drawable != null) {
            return ax.U.a.q(drawable);
        }
        return null;
    }

    public void W1(float f) {
        if (this.v1 != f) {
            this.v1 = f;
            invalidateSelf();
            H1();
        }
    }

    public void W2(float f) {
        if (this.r1 != f) {
            this.r1 = f;
            invalidateSelf();
            H1();
        }
    }

    public float X0() {
        return this.a1;
    }

    public void X1(int i) {
        W1(this.w1.getResources().getDimension(i));
    }

    public void X2(int i) {
        W2(this.w1.getResources().getDimension(i));
    }

    public ColorStateList Y0() {
        return this.Z0;
    }

    public void Y1(Drawable drawable) {
        Drawable drawableW0 = W0();
        if (drawableW0 != drawable) {
            float fY0 = y0();
            this.Y0 = drawable != null ? ax.U.a.r(drawable).mutate() : null;
            float fY1 = y0();
            d3(drawableW0);
            if (b3()) {
                w0(this.Y0);
            }
            invalidateSelf();
            if (fY0 != fY1) {
                H1();
            }
        }
    }

    public void Y2(boolean z) {
        if (this.S1 != z) {
            this.S1 = z;
            e3();
            onStateChange(getState());
        }
    }

    public float Z0() {
        return this.R0;
    }

    public void Z1(int i) {
        Y1(ax.p226o.a.b(this.w1, i));
    }

    boolean Z2() {
        return this.W1;
    }

    @Override // ax.m7.u.b
    public void a() {
        H1();
        invalidateSelf();
    }

    public float a1() {
        return this.o1;
    }

    public void a2(float f) {
        if (this.a1 != f) {
            float fY0 = y0();
            this.a1 = f;
            float fY1 = y0();
            invalidateSelf();
            if (fY0 != fY1) {
                H1();
            }
        }
    }

    public ColorStateList b1() {
        return this.T0;
    }

    public void b2(int i) {
        a2(this.w1.getResources().getDimension(i));
    }

    public float c1() {
        return this.U0;
    }

    public void c2(ColorStateList colorStateList) {
        this.b1 = true;
        if (this.Z0 != colorStateList) {
            this.Z0 = colorStateList;
            if (b3()) {
                this.Y0.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public Drawable d1() {
        Drawable drawable = this.d1;
        if (drawable != null) {
            return ax.U.a.q(drawable);
        }
        return null;
    }

    public void d2(int i) {
        c2(ax.p226o.a.a(this.w1, i));
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Canvas canvas2;
        int iA;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.M1;
        if (i < 255) {
            canvas2 = canvas;
            iA = ax.Z6.a.a(canvas2, bounds.left, bounds.top, bounds.right, bounds.bottom, i);
        } else {
            canvas2 = canvas;
            iA = 0;
        }
        M0(canvas2, bounds);
        J0(canvas2, bounds);
        if (this.Y1) {
            super.draw(canvas2);
        }
        L0(canvas2, bounds);
        O0(canvas2, bounds);
        K0(canvas2, bounds);
        I0(canvas2, bounds);
        if (this.W1) {
            Q0(canvas2, bounds);
        }
        N0(canvas2, bounds);
        P0(canvas2, bounds);
        if (this.M1 < 255) {
            canvas2.restoreToCount(iA);
        }
    }

    public CharSequence e1() {
        return this.h1;
    }

    public void e2(int i) {
        f2(this.w1.getResources().getBoolean(i));
    }

    public float f1() {
        return this.u1;
    }

    public void f2(boolean z) {
        if (this.X0 != z) {
            boolean zB3 = b3();
            this.X0 = z;
            boolean zB4 = b3();
            if (zB3 != zB4) {
                if (zB4) {
                    w0(this.Y0);
                } else {
                    d3(this.Y0);
                }
                invalidateSelf();
                H1();
            }
        }
    }

    public float g1() {
        return this.g1;
    }

    public void g2(float f) {
        if (this.R0 != f) {
            this.R0 = f;
            invalidateSelf();
            H1();
        }
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.M1;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.N1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.R0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.o1 + y0() + this.r1 + this.D1.h(t1().toString()) + this.s1 + C0() + this.v1), this.X1);
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Outline outline2;
        if (this.Y1) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline2 = outline;
            outline2.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.S0);
        } else {
            outline.setRoundRect(bounds, this.S0);
            outline2 = outline;
        }
        outline2.setAlpha(getAlpha() / 255.0f);
    }

    public float h1() {
        return this.t1;
    }

    public void h2(int i) {
        g2(this.w1.getResources().getDimension(i));
    }

    public int[] i1() {
        return this.R1;
    }

    public void i2(float f) {
        if (this.o1 != f) {
            this.o1 = f;
            invalidateSelf();
            H1();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (D1(this.P0) || D1(this.Q0) || D1(this.T0)) {
            return true;
        }
        return (this.S1 && D1(this.T1)) || F1(this.D1.e()) || G0() || E1(this.Y0) || E1(this.k1) || D1(this.P1);
    }

    public ColorStateList j1() {
        return this.f1;
    }

    public void j2(int i) {
        i2(this.w1.getResources().getDimension(i));
    }

    public void k1(RectF rectF) {
        B0(getBounds(), rectF);
    }

    public void k2(ColorStateList colorStateList) {
        if (this.T0 != colorStateList) {
            this.T0 = colorStateList;
            if (this.Y1) {
                o0(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void l2(int i) {
        k2(ax.p226o.a.a(this.w1, i));
    }

    public void m2(float f) {
        if (this.U0 != f) {
            this.U0 = f;
            this.x1.setStrokeWidth(f);
            if (this.Y1) {
                super.p0(f);
            }
            invalidateSelf();
        }
    }

    public TextUtils.TruncateAt n1() {
        return this.V1;
    }

    public void n2(int i) {
        m2(this.w1.getResources().getDimension(i));
    }

    public ax.S6.h o1() {
        return this.n1;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (b3()) {
            zOnLayoutDirectionChanged |= ax.U.a.m(this.Y0, i);
        }
        if (a3()) {
            zOnLayoutDirectionChanged |= ax.U.a.m(this.k1, i);
        }
        if (c3()) {
            zOnLayoutDirectionChanged |= ax.U.a.m(this.d1, i);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        boolean zOnLevelChange = super.onLevelChange(i);
        if (b3()) {
            zOnLevelChange |= this.Y0.setLevel(i);
        }
        if (a3()) {
            zOnLevelChange |= this.k1.setLevel(i);
        }
        if (c3()) {
            zOnLevelChange |= this.d1.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        if (this.Y1) {
            super.onStateChange(iArr);
        }
        return I1(iArr, i1());
    }

    public float p1() {
        return this.q1;
    }

    public void p2(Drawable drawable) {
        Drawable drawableD1 = d1();
        if (drawableD1 != drawable) {
            float fC0 = C0();
            this.d1 = drawable != null ? ax.U.a.r(drawable).mutate() : null;
            f3();
            float fC1 = C0();
            d3(drawableD1);
            if (c3()) {
                w0(this.d1);
            }
            invalidateSelf();
            if (fC0 != fC1) {
                H1();
            }
        }
    }

    public float q1() {
        return this.p1;
    }

    public void q2(CharSequence charSequence) {
        if (this.h1 != charSequence) {
            this.h1 = ax.p003a0.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    public ColorStateList r1() {
        return this.V0;
    }

    public void r2(float f) {
        if (this.u1 != f) {
            this.u1 = f;
            invalidateSelf();
            if (c3()) {
                H1();
            }
        }
    }

    public ax.S6.h s1() {
        return this.m1;
    }

    public void s2(int i) {
        r2(this.w1.getResources().getDimension(i));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.M1 != i) {
            this.M1 = i;
            invalidateSelf();
        }
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.N1 != colorFilter) {
            this.N1 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.P1 != colorStateList) {
            this.P1 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.Q1 != mode) {
            this.Q1 = mode;
            this.O1 = f.l(this, this.P1, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (b3()) {
            visible |= this.Y0.setVisible(z, z2);
        }
        if (a3()) {
            visible |= this.k1.setVisible(z, z2);
        }
        if (c3()) {
            visible |= this.d1.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public CharSequence t1() {
        return this.W0;
    }

    public void t2(int i) {
        p2(ax.p226o.a.b(this.w1, i));
    }

    public e u1() {
        return this.D1.e();
    }

    public void u2(float f) {
        if (this.g1 != f) {
            this.g1 = f;
            invalidateSelf();
            if (c3()) {
                H1();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public float v1() {
        return this.s1;
    }

    public void v2(int i) {
        u2(this.w1.getResources().getDimension(i));
    }

    public float w1() {
        return this.r1;
    }

    public void w2(float f) {
        if (this.t1 != f) {
            this.t1 = f;
            invalidateSelf();
            if (c3()) {
                H1();
            }
        }
    }

    public void x2(int i) {
        w2(this.w1.getResources().getDimension(i));
    }

    float y0() {
        if (b3() || a3()) {
            return this.p1 + m1() + this.q1;
        }
        return 0.0f;
    }

    public boolean y1() {
        return this.S1;
    }

    public boolean y2(int[] iArr) {
        if (Arrays.equals(this.R1, iArr)) {
            return false;
        }
        this.R1 = iArr;
        if (c3()) {
            return I1(getState(), iArr);
        }
        return false;
    }

    public void z2(ColorStateList colorStateList) {
        if (this.f1 != colorStateList) {
            this.f1 = colorStateList;
            if (c3()) {
                this.d1.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }
}
