package com.google.android.material.progressindicator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.google.android.material.progressindicator.b;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class k<S extends b> extends h {
    private i<S> x0;
    private j<ObjectAnimator> y0;
    private Drawable z0;

    k(Context context, b bVar, i<S> iVar, j<ObjectAnimator> jVar) {
        super(context, bVar);
        B(iVar);
        A(jVar);
    }

    static k<f> v(Context context, f fVar, c cVar) {
        k<f> kVar = new k<>(context, fVar, cVar, fVar.o == 1 ? new e(context, fVar) : new d(fVar));
        kVar.C(ax.p389y2.j.b(context.getResources(), ax.R6.f.c, null));
        return kVar;
    }

    static k<o> w(Context context, o oVar, l lVar) {
        return new k<>(context, oVar, lVar, oVar.o == 0 ? new m(oVar) : new n(context, oVar));
    }

    private boolean z() {
        ax.p250p7.a aVar = this.Y;
        return aVar != null && aVar.a(this.q.getContentResolver()) == 0.0f;
    }

    void A(j<ObjectAnimator> jVar) {
        this.y0 = jVar;
        jVar.e(this);
    }

    void B(i<S> iVar) {
        this.x0 = iVar;
    }

    public void C(Drawable drawable) {
        this.z0 = drawable;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0109  */
    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Canvas canvas2;
        int i;
        Drawable drawable;
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(this.v0)) {
            int i2 = 0;
            if (z() && (drawable = this.z0) != null) {
                drawable.setBounds(getBounds());
                this.z0.setTint(this.X.e[0]);
                this.z0.draw(canvas);
                return;
            }
            canvas.save();
            this.x0.h(canvas, getBounds(), h(), m(), l());
            int i3 = this.X.i;
            int alpha = getAlpha();
            b bVar = this.X;
            boolean z = (bVar instanceof o) || ((bVar instanceof f) && ((f) bVar).s);
            boolean z2 = z && i3 == 0 && !bVar.b(false);
            if (!z2) {
                if (z) {
                    i.a aVar = this.y0.b.get(0);
                    List<i.a> list = this.y0.b;
                    i.a aVar2 = list.get(list.size() - 1);
                    i<S> iVar = this.x0;
                    if (iVar instanceof l) {
                        i = i3;
                        iVar.d(canvas, this.t0, 0.0f, aVar.a, this.X.f, alpha, i);
                        canvas2 = canvas;
                        this.x0.d(canvas2, this.t0, aVar2.b, 1.0f, this.X.f, alpha, i);
                    } else {
                        canvas2 = canvas;
                        i = i3;
                        canvas.save();
                        canvas.rotate(aVar2.g);
                        this.x0.d(canvas2, this.t0, aVar2.b, aVar.a + 1.0f, this.X.f, alpha, i);
                        canvas.restore();
                    }
                } else {
                    canvas2 = canvas;
                }
                while (i2 < this.y0.b.size()) {
                    i.a aVar3 = this.y0.b.get(i2);
                    aVar3.f = i();
                    this.x0.c(canvas, this.t0, aVar3, getAlpha());
                    if (i2 <= 0 && !z2 && z) {
                        this.x0.d(canvas2, this.t0, this.y0.b.get(i2 - 1).b, aVar3.a, this.X.f, alpha, i);
                    }
                    i2++;
                    canvas2 = canvas;
                }
                canvas.restore();
            }
            canvas2 = canvas;
            this.x0.d(canvas2, this.t0, 0.0f, 1.0f, this.X.f, alpha, 0);
            i = i3;
            while (i2 < this.y0.b.size()) {
                i.a aVar4 = this.y0.b.get(i2);
                aVar4.f = i();
                this.x0.c(canvas, this.t0, aVar4, getAlpha());
                if (i2 <= 0) {
                }
                i2++;
                canvas2 = canvas;
            }
            canvas.restore();
        }
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.x0.e();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.x0.f();
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean j() {
        return super.j();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean l() {
        return super.l();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean m() {
        return super.m();
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ void o(ax.p389y2.b bVar) {
        super.o(bVar);
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean s(boolean z, boolean z2, boolean z3) {
        return super.s(z, z2, z3);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.android.material.progressindicator.h, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    @Override // com.google.android.material.progressindicator.h
    boolean t(boolean z, boolean z2, boolean z3) {
        Drawable drawable;
        boolean zT = super.t(z, z2, z3);
        if (z() && (drawable = this.z0) != null) {
            return drawable.setVisible(z, z2);
        }
        if (!isRunning()) {
            this.y0.a();
        }
        if (z && (z3 || (Build.VERSION.SDK_INT <= 22 && !z()))) {
            this.y0.g();
        }
        return zT;
    }

    @Override // com.google.android.material.progressindicator.h
    public /* bridge */ /* synthetic */ boolean u(ax.p389y2.b bVar) {
        return super.u(bVar);
    }

    j<ObjectAnimator> x() {
        return this.y0;
    }

    i<S> y() {
        return this.x0;
    }
}
