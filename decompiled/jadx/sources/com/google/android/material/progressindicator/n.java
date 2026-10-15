package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class n extends j<ObjectAnimator> {
    private static final int[] k = {533, 567, 850, 750};
    private static final int[] l = {1267, 1000, 333, 0};
    private static final Property<n, Float> m = new c(Float.class, "animationFraction");
    private ObjectAnimator c;
    private ObjectAnimator d;
    private final Interpolator[] e;
    private final com.google.android.material.progressindicator.b f;
    private int g;
    private boolean h;
    private float i;
    ax.p389y2.b j;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            n nVar = n.this;
            nVar.g = (nVar.g + 1) % n.this.f.e.length;
            n.this.h = true;
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            n.this.a();
            n nVar = n.this;
            ax.p389y2.b bVar = nVar.j;
            if (bVar != null) {
                bVar.b(nVar.a);
            }
        }
    }

    class c extends Property<n, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(n nVar) {
            return Float.valueOf(nVar.n());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(n nVar, Float f) {
            nVar.r(f.floatValue());
        }
    }

    public n(Context context, o oVar) {
        super(2);
        this.g = 0;
        this.j = null;
        this.f = oVar;
        this.e = new Interpolator[]{ax.p389y2.f.a(context, ax.R6.a.a), ax.p389y2.f.a(context, ax.R6.a.b), ax.p389y2.f.a(context, ax.R6.a.c), ax.p389y2.f.a(context, ax.R6.a.d)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float n() {
        return this.i;
    }

    private void o() {
        if (this.c == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, m, 0.0f, 1.0f);
            this.c = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration((long) (this.f.n * 1800.0f));
            this.c.setInterpolator(null);
            this.c.setRepeatCount(-1);
            this.c.addListener(new a());
        }
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, m, 1.0f);
            this.d = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration((long) (this.f.n * 1800.0f));
            this.d.setInterpolator(null);
            this.d.addListener(new b());
        }
    }

    private void p() {
        if (this.h) {
            Iterator<i.a> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().c = this.f.e[this.g];
            }
            this.h = false;
        }
    }

    private void s() {
        o();
        this.c.setDuration((long) (this.f.n * 1800.0f));
        this.d.setDuration((long) (this.f.n * 1800.0f));
    }

    private void t(int i) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            i.a aVar = this.b.get(i2);
            int[] iArr = l;
            int i3 = i2 * 2;
            int i4 = iArr[i3];
            int[] iArr2 = k;
            aVar.a = ax.W.a.a(this.e[i3].getInterpolation(b(i, i4, iArr2[i3])), 0.0f, 1.0f);
            int i5 = i3 + 1;
            aVar.b = ax.W.a.a(this.e[i5].getInterpolation(b(i, iArr[i5], iArr2[i5])), 0.0f, 1.0f);
        }
    }

    @Override // com.google.android.material.progressindicator.j
    public void a() {
        ObjectAnimator objectAnimator = this.c;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.j
    public void c() {
        s();
        q();
    }

    @Override // com.google.android.material.progressindicator.j
    public void d(ax.p389y2.b bVar) {
        this.j = bVar;
    }

    @Override // com.google.android.material.progressindicator.j
    public void f() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        a();
        if (this.a.isVisible()) {
            this.d.setFloatValues(this.i, 1.0f);
            this.d.setDuration((long) ((1.0f - this.i) * 1800.0f));
            this.d.start();
        }
    }

    @Override // com.google.android.material.progressindicator.j
    public void g() {
        o();
        q();
        this.c.start();
    }

    @Override // com.google.android.material.progressindicator.j
    public void h() {
        this.j = null;
    }

    void q() {
        this.g = 0;
        Iterator<i.a> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().c = this.f.e[0];
        }
    }

    void r(float f) {
        this.i = f;
        t((int) (f * 1800.0f));
        p();
        this.a.invalidateSelf();
    }
}
