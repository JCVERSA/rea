package com.box.androidsdk.content.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import ax.O3.e;
import ax.p409z6.C3772df;

/* JADX INFO: loaded from: classes.dex */
public class BezelImageView extends ImageView {
    private Paint k0;
    private Rect l0;
    private RectF m0;
    private Drawable n0;
    private Drawable o0;
    private ColorMatrixColorFilter p0;
    private Paint q;
    private boolean q0;
    private boolean r0;
    private Bitmap s0;
    private int t0;
    private int u0;

    public BezelImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BezelImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q0 = false;
        this.r0 = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.a, i, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(e.d);
        this.o0 = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(e.b);
        this.n0 = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        this.q0 = typedArrayObtainStyledAttributes.getBoolean(e.c, this.q0);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.q = paint;
        paint.setColor(-16777216);
        Paint paint2 = new Paint();
        this.k0 = paint2;
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.s0 = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        if (this.q0) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            this.p0 = new ColorMatrixColorFilter(colorMatrix);
        }
    }

    private void a(Canvas canvas) {
        canvas.saveLayer(this.m0, this.k0);
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(C3772df.zzm)
    private void b(Canvas canvas) {
        canvas.saveLayer(this.m0, this.k0, 12);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.n0;
        if (drawable != null && drawable.isStateful()) {
            this.n0.setState(getDrawableState());
        }
        Drawable drawable2 = this.o0;
        if (drawable2 != null && drawable2.isStateful()) {
            this.o0.setState(getDrawableState());
        }
        if (isDuplicateParentStateEnabled()) {
            postInvalidateOnAnimation();
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.n0 || drawable == this.o0) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Rect rect = this.l0;
        if (rect == null) {
            return;
        }
        int iWidth = rect.width();
        int iHeight = this.l0.height();
        if (iWidth == 0 || iHeight == 0) {
            return;
        }
        if (!this.r0 || iWidth != this.t0 || iHeight != this.u0) {
            if (iWidth == this.t0 && iHeight == this.u0) {
                this.s0.eraseColor(0);
            } else {
                this.s0.recycle();
                this.s0 = Bitmap.createBitmap(iWidth, iHeight, Bitmap.Config.ARGB_8888);
                this.t0 = iWidth;
                this.u0 = iHeight;
            }
            Canvas canvas2 = new Canvas(this.s0);
            if (this.o0 != null) {
                int iSave = canvas2.save();
                this.o0.draw(canvas2);
                this.k0.setColorFilter((this.q0 && isPressed()) ? this.p0 : null);
                if (Build.VERSION.SDK_INT > 21) {
                    a(canvas2);
                } else {
                    b(canvas2);
                }
                super.onDraw(canvas2);
                canvas2.restoreToCount(iSave);
            } else if (this.q0 && isPressed()) {
                int iSave2 = canvas2.save();
                canvas2.drawRect(0.0f, 0.0f, this.t0, this.u0, this.q);
                this.k0.setColorFilter(this.p0);
                if (Build.VERSION.SDK_INT > 21) {
                    a(canvas2);
                } else {
                    b(canvas2);
                }
                super.onDraw(canvas2);
                canvas2.restoreToCount(iSave2);
            } else {
                super.onDraw(canvas2);
            }
            Drawable drawable = this.n0;
            if (drawable != null) {
                drawable.draw(canvas2);
            }
        }
        Bitmap bitmap = this.s0;
        Rect rect2 = this.l0;
        canvas.drawBitmap(bitmap, rect2.left, rect2.top, (Paint) null);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        this.l0 = new Rect(0, 0, i3 - i, i4 - i2);
        this.m0 = new RectF(this.l0);
        Drawable drawable = this.n0;
        if (drawable != null) {
            drawable.setBounds(this.l0);
        }
        Drawable drawable2 = this.o0;
        if (drawable2 != null) {
            drawable2.setBounds(this.l0);
        }
        if (frame) {
            this.r0 = false;
        }
        return frame;
    }

    @Override // android.widget.ImageView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.n0 || drawable == this.o0 || super.verifyDrawable(drawable);
    }
}
