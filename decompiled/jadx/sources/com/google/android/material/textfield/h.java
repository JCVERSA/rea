package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
class h extends ax.p346v7.h {
    b P0;

    private static final class b extends ax.v7.h.c {
        private final RectF x;

        private b(ax.p346v7.l lVar, RectF rectF) {
            super(lVar, null);
            this.x = rectF;
        }

        private b(b bVar) {
            super(bVar);
            this.x = bVar.x;
        }

        @Override // ax.v7.h.c, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVarY0 = h.y0(this);
            hVarY0.invalidateSelf();
            return hVarY0;
        }
    }

    private static class c extends h {
        c(b bVar) {
            super(bVar);
        }

        @Override // ax.p346v7.h
        protected void w(Canvas canvas) {
            if (this.P0.x.isEmpty()) {
                super.w(canvas);
                return;
            }
            canvas.save();
            if (Build.VERSION.SDK_INT >= 26) {
                canvas.clipOutRect(this.P0.x);
            } else {
                canvas.clipRect(this.P0.x, Region.Op.DIFFERENCE);
            }
            super.w(canvas);
            canvas.restore();
        }
    }

    private h(b bVar) {
        super(bVar);
        this.P0 = bVar;
    }

    static h x0(ax.p346v7.l lVar) {
        if (lVar == null) {
            lVar = new ax.p346v7.l();
        }
        return y0(new b(lVar, new RectF()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static h y0(b bVar) {
        return new c(bVar);
    }

    void A0() {
        B0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void B0(float f, float f2, float f3, float f4) {
        if (f == this.P0.x.left && f2 == this.P0.x.top && f3 == this.P0.x.right && f4 == this.P0.x.bottom) {
            return;
        }
        this.P0.x.set(f, f2, f3, f4);
        invalidateSelf();
    }

    void C0(RectF rectF) {
        B0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    @Override // ax.p346v7.h, android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.P0 = new b(this.P0);
        return this;
    }

    boolean z0() {
        return !this.P0.x.isEmpty();
    }
}
