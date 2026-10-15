package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import ax.p338y1.k;

/* JADX INFO: loaded from: classes.dex */
public class PieProgress extends View {
    private RectF h0;
    private int i0;
    private int j0;
    private int k0;
    private int l0;
    private int m0;
    private Paint q;

    public PieProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j0 = -1;
        this.k0 = -1996488705;
        this.l0 = 0;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, k.w1, 0, 0);
        try {
            this.l0 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
            int color = typedArrayObtainStyledAttributes.getColor(1, -1);
            this.j0 = color;
            if (color == -1) {
                this.k0 = -1996488705;
            } else {
                this.k0 = ((Color.alpha(color) / 2) << 24) | (this.j0 & 16777215);
            }
            if (this.l0 > 0) {
                this.m0 = 1;
            } else {
                this.m0 = 2;
            }
            typedArrayObtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void a() {
        Paint paint = new Paint();
        this.q = paint;
        paint.setAntiAlias(true);
        this.h0 = new RectF();
    }

    public void b(int i, int i2) {
        this.j0 = i;
        this.k0 = (i & 16777215) | ((Color.alpha(i) / i2) << 24);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.save();
        int width = getWidth();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        if (width >= height) {
            width = height;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        float f = this.l0 / 2.0f;
        if (this.m0 == 1) {
            this.h0.set(paddingLeft + f, paddingTop + f, (paddingLeft + width) - f, (paddingTop + width) - f);
            this.q.setStyle(Paint.Style.STROKE);
            this.q.setStrokeWidth(this.l0);
            this.q.setColor(this.k0);
            canvas.drawArc(this.h0, 0.0f, 360.0f, false, this.q);
            this.q.setColor(this.j0);
            canvas.drawArc(this.h0, -90.0f, this.i0, false, this.q);
        } else {
            float f2 = paddingLeft;
            float f3 = paddingTop;
            float f4 = paddingLeft + width;
            float f5 = paddingTop + width;
            this.h0.set(f2, f3, f4, f5);
            this.q.setStyle(Paint.Style.FILL);
            this.q.setColor(this.k0);
            canvas.drawCircle(this.h0.centerX(), this.h0.centerY(), this.h0.width() / 2.0f, this.q);
            this.q.setColor(this.j0);
            canvas.drawArc(this.h0, -90.0f, this.i0, true, this.q);
            if (this.m0 == 3) {
                this.h0.set(f2 + f, f3 + f, f4 - f, f5 - f);
                this.q.setStyle(Paint.Style.STROKE);
                this.q.setStrokeWidth(this.l0);
                canvas.drawCircle(this.h0.centerX(), this.h0.centerY(), this.h0.width() / 2.0f, this.q);
            }
        }
        canvas.restore();
    }

    public void setProgressAngle(int i) {
        this.i0 = i;
        invalidate();
    }

    public void setProgressPercent(int i) {
        this.i0 = (i * 360) / 100;
        invalidate();
    }

    public void setStrokeWidth(int i) {
        this.l0 = i;
        invalidate();
    }

    public void setStyle(int i) {
        this.m0 = i;
        invalidate();
    }

    public PieProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
