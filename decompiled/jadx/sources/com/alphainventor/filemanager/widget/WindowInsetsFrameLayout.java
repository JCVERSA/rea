package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import ax.T.b;
import ax.p035c0.F0;

/* JADX INFO: loaded from: classes.dex */
public class WindowInsetsFrameLayout extends FrameLayout {
    private int k0;
    private int l0;
    private int m0;
    private int n0;
    private int o0;
    private int p0;
    private int q;
    private int q0;
    private int r0;
    private b s0;
    private Paint t0;

    public WindowInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m0 = -16777216;
        b();
    }

    public static int a(b bVar) {
        if (bVar.a > 0) {
            return 3;
        }
        if (bVar.c > 0) {
            return 5;
        }
        return bVar.b > 0 ? 48 : 80;
    }

    private void b() {
        this.t0 = new Paint();
    }

    private void setCutoutGravity(int i) {
        this.l0 = i;
    }

    private void setNavigationGravity(int i) {
        this.k0 = i;
    }

    public void c(int i, int i2, int i3, int i4) {
        this.o0 = i;
        this.p0 = i2;
        this.q0 = i3;
        this.r0 = i4;
        setPadding(i, i2, i3, i4);
    }

    public void d(F0 f0, boolean z) {
        b bVarF = f0.f(F0.m.h());
        b bVarF2 = f0.f(F0.m.b());
        b bVarA = b.a(bVarF, bVarF2);
        b bVarF3 = f0.f(F0.m.f());
        if (z) {
            bVarA = b.a(bVarA, f0.f(F0.m.c()));
            c(bVarA.a, bVarA.b, bVarA.c, bVarA.d);
        } else {
            c(bVarA.a, bVarA.b, bVarA.c, 0);
        }
        setNavigationGravity(a(bVarF3));
        setCutoutGravity(a(bVarF2));
        this.s0 = bVarA;
    }

    public b getAppliedInsets() {
        return this.s0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Canvas canvas2;
        this.t0.setColor(this.q);
        if (this.p0 > 0) {
            canvas.drawRect(0.0f, 0.0f, getWidth(), this.p0, this.t0);
            canvas2 = canvas;
        } else {
            canvas2 = canvas;
        }
        this.t0.setColor(this.n0);
        if (this.r0 > 0) {
            canvas2.drawRect(0.0f, getHeight() - this.r0, getWidth(), getHeight(), this.t0);
        }
        int i = this.o0;
        if (i > 0 && this.k0 == 3) {
            canvas2.drawRect(0.0f, 0.0f, i, getHeight(), this.t0);
        }
        if (this.q0 > 0 && this.k0 == 5) {
            canvas2.drawRect(getWidth() - this.q0, 0.0f, getWidth(), getHeight(), this.t0);
        }
        this.t0.setColor(this.m0);
        int i2 = this.o0;
        if (i2 > 0 && this.l0 == 3) {
            canvas2.drawRect(0.0f, 0.0f, i2, getHeight(), this.t0);
        }
        if (this.q0 <= 0 || this.l0 != 5) {
            return;
        }
        canvas2.drawRect(getWidth() - this.q0, 0.0f, getWidth(), getHeight(), this.t0);
    }

    public void setBottomAreaSize(int i) {
        this.r0 = i;
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), i);
    }

    public void setCutoutColor(int i) {
        this.m0 = i;
    }

    public void setNavigationBarColor(int i) {
        this.n0 = i;
        invalidate();
    }

    public void setStatusBarColor(int i) {
        this.q = i;
        invalidate();
    }
}
