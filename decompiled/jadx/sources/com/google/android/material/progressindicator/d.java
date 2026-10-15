package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;

/* JADX INFO: loaded from: classes3.dex */
final class d extends j<ObjectAnimator> {
    private static final int[] k = {0, 1350, 2700, 4050};
    private static final int[] l = {667, 2017, 3367, 4717};
    private static final int[] m = {1000, 2350, 3700, 5050};
    private static final Property<d, Float> n = new c(Float.class, "animationFraction");
    private static final Property<d, Float> o = new C0578d(Float.class, "completeEndFraction");
    private ObjectAnimator c;
    private ObjectAnimator d;
    private final ax.D0.b e;
    private final com.google.android.material.progressindicator.b f;
    private int g;
    private float h;
    private float i;
    ax.p389y2.b j;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            d dVar = d.this;
            dVar.g = (dVar.g + 4) % d.this.f.e.length;
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            d.this.a();
            d dVar = d.this;
            ax.p389y2.b bVar = dVar.j;
            if (bVar != null) {
                bVar.b(dVar.a);
            }
        }
    }

    class c extends Property<d, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(d dVar) {
            return Float.valueOf(dVar.o());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(d dVar, Float f) {
            dVar.t(f.floatValue());
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.progressindicator.d$d, reason: collision with other inner class name */
    class C0578d extends Property<d, Float> {
        C0578d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(d dVar) {
            return Float.valueOf(dVar.p());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(d dVar, Float f) {
            dVar.u(f.floatValue());
        }
    }

    public d(f fVar) {
        super(1);
        this.g = 0;
        this.j = null;
        this.f = fVar;
        this.e = new ax.D0.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float o() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float p() {
        return this.i;
    }

    private void q() {
        if (this.c == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, n, 0.0f, 1.0f);
            this.c = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration((long) (this.f.n * 5400.0f));
            this.c.setInterpolator(null);
            this.c.setRepeatCount(-1);
            this.c.addListener(new a());
        }
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration((long) (this.f.n * 333.0f));
            this.d.setInterpolator(this.e);
            this.d.addListener(new b());
        }
    }

    private void r(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            float fB = b(i, m[i2], 333);
            if (fB > 0.0f && fB < 1.0f) {
                int i3 = i2 + this.g;
                int[] iArr = this.f.e;
                int length = i3 % iArr.length;
                int length2 = (length + 1) % iArr.length;
                int i4 = iArr[length];
                int i5 = iArr[length2];
                float interpolation = this.e.getInterpolation(fB);
                this.b.get(0).c = ax.S6.c.b().evaluate(interpolation, Integer.valueOf(i4), Integer.valueOf(i5)).intValue();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(float f) {
        this.i = f;
    }

    private void v() {
        q();
        this.c.setDuration((long) (this.f.n * 5400.0f));
        this.d.setDuration((long) (this.f.n * 333.0f));
    }

    private void w(int i) {
        i.a aVar = this.b.get(0);
        float f = this.h;
        aVar.a = (f * 1520.0f) - 20.0f;
        aVar.b = f * 1520.0f;
        for (int i2 = 0; i2 < 4; i2++) {
            aVar.b += this.e.getInterpolation(b(i, k[i2], 667)) * 250.0f;
            aVar.a += this.e.getInterpolation(b(i, l[i2], 667)) * 250.0f;
        }
        float f2 = aVar.a;
        float f3 = aVar.b;
        aVar.a = (f2 + ((f3 - f2) * this.i)) / 360.0f;
        aVar.b = f3 / 360.0f;
    }

    @Override // com.google.android.material.progressindicator.j
    void a() {
        ObjectAnimator objectAnimator = this.c;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.j
    public void c() {
        v();
        s();
    }

    @Override // com.google.android.material.progressindicator.j
    public void d(ax.p389y2.b bVar) {
        this.j = bVar;
    }

    @Override // com.google.android.material.progressindicator.j
    void f() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        if (this.a.isVisible()) {
            this.d.start();
        } else {
            a();
        }
    }

    @Override // com.google.android.material.progressindicator.j
    void g() {
        q();
        s();
        this.c.start();
    }

    @Override // com.google.android.material.progressindicator.j
    public void h() {
        this.j = null;
    }

    void s() {
        this.g = 0;
        this.b.get(0).c = this.f.e[0];
        this.i = 0.0f;
    }

    void t(float f) {
        this.h = f;
        int i = (int) (f * 5400.0f);
        w(i);
        r(i);
        this.a.invalidateSelf();
    }
}
