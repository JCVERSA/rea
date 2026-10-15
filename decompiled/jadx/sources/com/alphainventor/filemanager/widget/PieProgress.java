package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import ax.U2.n;

/* JADX INFO: loaded from: classes.dex */
public class PieProgress extends View {
    private RectF k0;
    private int l0;
    private int m0;
    private int n0;
    private int o0;
    private int p0;
    private Paint q;

    public PieProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PieProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m0 = -1;
        this.n0 = -1996488705;
        this.o0 = 0;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, n.E1, 0, 0);
        try {
            this.o0 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
            int color = typedArrayObtainStyledAttributes.getColor(1, -1);
            this.m0 = color;
            if (color == -1) {
                this.n0 = -1996488705;
            } else {
                this.n0 = ((Color.alpha(color) / 2) << 24) | (this.m0 & 16777215);
            }
            if (this.o0 > 0) {
                this.p0 = 1;
            } else {
                this.p0 = 2;
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
        this.k0 = new RectF();
    }

    public void b(int i, int i2) {
        this.m0 = i;
        this.n0 = (i & 16777215) | ((Color.alpha(i) / i2) << 24);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Canvas canvas2;
        canvas.save();
        int width = getWidth();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        if (width >= height) {
            width = height;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        float f = this.o0 / 2.0f;
        if (this.p0 == 1) {
            this.k0.set(paddingLeft + f, paddingTop + f, (paddingLeft + width) - f, (paddingTop + width) - f);
            this.q.setStyle(Paint.Style.STROKE);
            this.q.setStrokeWidth(this.o0);
            this.q.setColor(this.n0);
            canvas.drawArc(this.k0, 0.0f, 360.0f, false, this.q);
            canvas2 = canvas;
            this.q.setColor(this.m0);
            canvas2.drawArc(this.k0, -90.0f, this.l0, false, this.q);
        } else {
            canvas2 = canvas;
            float f2 = paddingLeft;
            float f3 = paddingTop;
            float f4 = paddingLeft + width;
            float f5 = paddingTop + width;
            this.k0.set(f2, f3, f4, f5);
            this.q.setStyle(Paint.Style.FILL);
            this.q.setColor(this.n0);
            canvas2.drawCircle(this.k0.centerX(), this.k0.centerY(), this.k0.width() / 2.0f, this.q);
            this.q.setColor(this.m0);
            canvas2.drawArc(this.k0, -90.0f, this.l0, true, this.q);
            if (this.p0 == 3) {
                this.k0.set(f2 + f, f3 + f, f4 - f, f5 - f);
                this.q.setStyle(Paint.Style.STROKE);
                this.q.setStrokeWidth(this.o0);
                canvas2.drawCircle(this.k0.centerX(), this.k0.centerY(), this.k0.width() / 2.0f, this.q);
            }
        }
        canvas2.restore();
    }

    public void setProgressAngle(int i) {
        this.l0 = i;
        invalidate();
    }

    public void setProgressPercent(int i) {
        this.l0 = (i * 360) / 100;
        invalidate();
    }

    public void setStrokeWidth(int i) {
        this.o0 = i;
        invalidate();
    }

    public void setStyle(int i) {
        this.p0 = i;
        invalidate();
    }
}
