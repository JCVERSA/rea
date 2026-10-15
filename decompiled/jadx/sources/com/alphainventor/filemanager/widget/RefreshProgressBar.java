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
import ax.p338y1.k;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class RefreshProgressBar extends View {
    final ValueAnimator h0;
    private final Paint i0;
    private final int j0;
    private final int k0;
    private final int l0;
    private final float m0;
    private int n0;
    private final GradientDrawable q;

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

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return ((float) Math.pow(2.0d, f)) - 1.0f;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    public RefreshProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.i0 = paint;
        float f = context.getResources().getDisplayMetrics().density;
        this.m0 = f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.M1);
        try {
            int color = typedArrayObtainStyledAttributes.getColor(0, ax.P.b.c(context, R.color.refresh_progress_bar));
            this.j0 = color;
            this.k0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, Math.round(4.0f * f));
            this.l0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, Math.round(f * 3.0f));
            typedArrayObtainStyledAttributes.recycle();
            ValueAnimator valueAnimator = new ValueAnimator();
            this.h0 = valueAnimator;
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
        ValueAnimator valueAnimator = this.h0;
        if (valueAnimator == null || valueAnimator.isStarted()) {
            return;
        }
        this.h0.addUpdateListener(new a());
        this.h0.start();
    }

    private void b() {
        ValueAnimator valueAnimator = this.h0;
        if (valueAnimator == null) {
            return;
        }
        valueAnimator.cancel();
        this.h0.removeAllUpdateListeners();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.h0.isStarted()) {
            this.q.draw(canvas);
            float fFloatValue = ((Float) this.h0.getAnimatedValue()).floatValue();
            int width = getWidth();
            int i = width >> (this.n0 - 1);
            int i2 = 0;
            while (i2 < this.n0) {
                int i3 = i2 + 1;
                float f = (width >> i3) * fFloatValue;
                float f2 = i;
                canvas.drawRect((f + this.l0) - f2, 0.0f, (i2 == 0 ? width + i : 2.0f * f) - f2, this.k0, this.i0);
                i2 = i3;
            }
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int width = getWidth();
            this.q.setBounds(0, this.k0, width, getHeight() - this.k0);
            float f = ((width / this.m0) / 320.0f) - 1.0f;
            this.h0.setDuration((int) (((0.3f * f) + 1.0f) * 1500.0f));
            this.n0 = (int) (((f * 0.1f) + 1.0f) * 5.0f);
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
