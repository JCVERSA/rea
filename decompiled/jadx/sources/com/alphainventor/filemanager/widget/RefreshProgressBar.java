package com.alphainventor.filemanager.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import ax.U2.n;
import ax.p406z3.B;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class RefreshProgressBar extends View {
    final ValueAnimator k0;
    private final Paint l0;
    private final int m0;
    private final int n0;
    private final int o0;
    private final float p0;
    private final GradientDrawable q;
    private int q0;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            RefreshProgressBar.this.invalidate();
        }
    }

    private static class b implements Interpolator {
        private b() {
        }

        /* synthetic */ b(a aVar) {
            this();
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return ((float) Math.pow(2.0d, f)) - 1.0f;
        }
    }

    public RefreshProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.l0 = paint;
        float fS = B.s(context);
        this.p0 = fS;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, n.U1);
        try {
            int color = typedArrayObtainStyledAttributes.getColor(0, ax.Q.b.c(context, R.color.refresh_progress_bar));
            this.m0 = color;
            this.n0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, Math.round(4.0f * fS));
            this.o0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, Math.round(fS * 3.0f));
            typedArrayObtainStyledAttributes.recycle();
            ValueAnimator valueAnimator = new ValueAnimator();
            this.k0 = valueAnimator;
            valueAnimator.setFloatValues(1.0f, 2.0f);
            valueAnimator.setRepeatCount(-1);
            valueAnimator.setInterpolator(new b(null));
            paint.setColor(color);
            this.q = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{(color & 16777215) | 570425344, 0});
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void a() {
        ValueAnimator valueAnimator = this.k0;
        if (valueAnimator == null || valueAnimator.isStarted()) {
            return;
        }
        this.k0.addUpdateListener(new a());
        this.k0.start();
    }

    private void b() {
        ValueAnimator valueAnimator = this.k0;
        if (valueAnimator == null) {
            return;
        }
        valueAnimator.cancel();
        this.k0.removeAllUpdateListeners();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.k0.isStarted()) {
            this.q.draw(canvas);
            float fFloatValue = ((Float) this.k0.getAnimatedValue()).floatValue();
            int width = getWidth();
            int i = width >> (this.q0 - 1);
            int i2 = 0;
            while (i2 < this.q0) {
                int i3 = i2 + 1;
                float f = (width >> i3) * fFloatValue;
                float f2 = i;
                canvas.drawRect((f + this.o0) - f2, 0.0f, (i2 == 0 ? width + i : 2.0f * f) - f2, this.n0, this.l0);
                i2 = i3;
            }
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int width = getWidth();
            this.q.setBounds(0, this.n0, width, getHeight() - this.n0);
            float f = ((width / this.p0) / 320.0f) - 1.0f;
            this.k0.setDuration((int) (((0.3f * f) + 1.0f) * 1500.0f));
            this.q0 = (int) (((f * 0.1f) + 1.0f) * 5.0f);
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            b();
        } else if (getVisibility() == 0) {
            a();
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }
}
