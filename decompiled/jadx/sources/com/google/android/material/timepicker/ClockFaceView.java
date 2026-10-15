package com.google.android.material.timepicker;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import ax.R6.g;
import ax.R6.i;
import ax.R6.l;
import ax.R6.m;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class ClockFaceView extends d implements ClockHandView.b {
    private final ClockHandView K0;
    private final Rect L0;
    private final RectF M0;
    private final Rect N0;
    private final SparseArray<TextView> O0;
    private final C1832a P0;
    private final int[] Q0;
    private final float[] R0;
    private final int S0;
    private final int T0;
    private final int U0;
    private final int V0;
    private String[] W0;
    private float X0;
    private final ColorStateList Y0;

    class a implements ViewTreeObserver.OnPreDrawListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (!ClockFaceView.this.isShown()) {
                return true;
            }
            ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
            ClockFaceView.this.F(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.K0.i()) - ClockFaceView.this.S0);
            return true;
        }
    }

    class b extends C1832a {
        b() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            int iIntValue = ((Integer) view.getTag(g.r)).intValue();
            if (iIntValue > 0) {
                h.N0((View) ClockFaceView.this.O0.get(iIntValue - 1));
            }
            h.p0(H.f.a(0, 1, iIntValue, 1, false, view.isSelected()));
            h.n0(true);
            h.b(H.a.i);
        }

        @Override // ax.p035c0.C1832a
        public boolean j(View view, int i, Bundle bundle) {
            if (i != 16) {
                return super.j(view, i, bundle);
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            view.getHitRect(ClockFaceView.this.L0);
            float fCenterX = ClockFaceView.this.L0.centerX();
            float fCenterY = ClockFaceView.this.L0.centerY();
            ClockFaceView.this.K0.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, fCenterX, fCenterY, 0));
            ClockFaceView.this.K0.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, fCenterX, fCenterY, 0));
            return true;
        }
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.y);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.L0 = new Rect();
        this.M0 = new RectF();
        this.N0 = new Rect();
        this.O0 = new SparseArray<>();
        this.R0 = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.z1, i, l.F);
        Resources resources = getResources();
        ColorStateList colorStateListA = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, m.B1);
        this.Y0 = colorStateListA;
        LayoutInflater.from(context).inflate(i.j, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(g.l);
        this.K0 = clockHandView;
        this.S0 = resources.getDimensionPixelSize(ax.R6.e.C);
        int colorForState = colorStateListA.getColorForState(new int[]{R.attr.state_selected}, colorStateListA.getDefaultColor());
        this.Q0 = new int[]{colorForState, colorForState, colorStateListA.getDefaultColor()};
        clockHandView.b(this);
        int defaultColor = ax.p226o.a.a(context, ax.R6.d.b).getDefaultColor();
        ColorStateList colorStateListA2 = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, m.A1);
        setBackgroundColor(colorStateListA2 != null ? colorStateListA2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new a());
        setFocusable(false);
        typedArrayObtainStyledAttributes.recycle();
        this.P0 = new b();
        String[] strArr = new String[12];
        Arrays.fill(strArr, HttpUrl.FRAGMENT_ENCODE_SET);
        R(strArr, 0);
        this.T0 = resources.getDimensionPixelSize(ax.R6.e.Q);
        this.U0 = resources.getDimensionPixelSize(ax.R6.e.R);
        this.V0 = resources.getDimensionPixelSize(ax.R6.e.E);
    }

    private void N() {
        RectF rectFE = this.K0.e();
        TextView textViewP = P(rectFE);
        for (int i = 0; i < this.O0.size(); i++) {
            TextView textView = this.O0.get(i);
            if (textView != null) {
                textView.setSelected(textView == textViewP);
                textView.getPaint().setShader(O(rectFE, textView));
                textView.invalidate();
            }
        }
    }

    private RadialGradient O(RectF rectF, TextView textView) {
        textView.getHitRect(this.L0);
        this.M0.set(this.L0);
        textView.getLineBounds(0, this.N0);
        RectF rectF2 = this.M0;
        Rect rect = this.N0;
        rectF2.inset(rect.left, rect.top);
        if (RectF.intersects(rectF, this.M0)) {
            return new RadialGradient(rectF.centerX() - this.M0.left, rectF.centerY() - this.M0.top, rectF.width() * 0.5f, this.Q0, this.R0, Shader.TileMode.CLAMP);
        }
        return null;
    }

    private TextView P(RectF rectF) {
        float f = Float.MAX_VALUE;
        TextView textView = null;
        for (int i = 0; i < this.O0.size(); i++) {
            TextView textView2 = this.O0.get(i);
            if (textView2 != null) {
                textView2.getHitRect(this.L0);
                this.M0.set(this.L0);
                this.M0.union(rectF);
                float fWidth = this.M0.width() * this.M0.height();
                if (fWidth < f) {
                    textView = textView2;
                    f = fWidth;
                }
            }
        }
        return textView;
    }

    private static float Q(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    private void S(int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = this.O0.size();
        boolean z = false;
        for (int i2 = 0; i2 < Math.max(this.W0.length, size); i2++) {
            TextView textView = this.O0.get(i2);
            if (i2 >= this.W0.length) {
                removeView(textView);
                this.O0.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(i.i, (ViewGroup) this, false);
                    this.O0.put(i2, textView);
                    addView(textView);
                }
                textView.setText(this.W0[i2]);
                textView.setTag(g.r, Integer.valueOf(i2));
                int i3 = (i2 / 12) + 1;
                textView.setTag(g.m, Integer.valueOf(i3));
                if (i3 > 1) {
                    z = true;
                }
                C1839d0.l0(textView, this.P0);
                textView.setTextColor(this.Y0);
                if (i != 0) {
                    textView.setContentDescription(getResources().getString(i, this.W0[i2]));
                }
            }
        }
        this.K0.q(z);
    }

    @Override // com.google.android.material.timepicker.d
    public void F(int i) {
        if (i != E()) {
            super.F(i);
            this.K0.m(E());
        }
    }

    @Override // com.google.android.material.timepicker.d
    protected void H() {
        super.H();
        for (int i = 0; i < this.O0.size(); i++) {
            this.O0.get(i).setVisibility(0);
        }
    }

    public void R(String[] strArr, int i) {
        this.W0 = strArr;
        S(i);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.b
    public void a(float f, boolean z) {
        if (Math.abs(this.X0 - f) > 0.001f) {
            this.X0 = f;
            N();
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        H.Q0(accessibilityNodeInfo).o0(H.e.a(1, this.W0.length, false, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        N();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iQ = (int) (this.V0 / Q(this.T0 / displayMetrics.heightPixels, this.U0 / displayMetrics.widthPixels, 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iQ, 1073741824);
        setMeasuredDimension(iQ, iQ);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }
}
