package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;

/* JADX INFO: loaded from: classes3.dex */
final class m extends j<ObjectAnimator> {
    private static final Property<m, Float> i = new b(Float.class, "animationFraction");
    private ObjectAnimator c;
    private ax.D0.b d;
    private final com.google.android.material.progressindicator.b e;
    private int f;
    private boolean g;
    private float h;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            m mVar = m.this;
            mVar.f = (mVar.f + 1) % m.this.e.e.length;
            m.this.g = true;
        }
    }

    class b extends Property<m, Float> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(m mVar) {
            return Float.valueOf(mVar.n());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(m mVar, Float f) {
            mVar.r(f.floatValue());
        }
    }

    public m(o oVar) {
        super(3);
        this.f = 1;
        this.e = oVar;
        this.d = new ax.D0.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float n() {
        return this.h;
    }

    private void o() {
        if (this.c == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, i, 0.0f, 1.0f);
            this.c = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration((long) (this.e.n * 333.0f));
            this.c.setInterpolator(null);
            this.c.setRepeatCount(-1);
            this.c.addListener(new a());
        }
    }

    private void p() {
        if (!this.g || this.b.get(1).b >= 1.0f) {
            return;
        }
        this.b.get(2).c = this.b.get(1).c;
        this.b.get(1).c = this.b.get(0).c;
        this.b.get(0).c = this.e.e[this.f];
        this.g = false;
    }

    private void s() {
        o();
        this.c.setDuration((long) (this.e.n * 333.0f));
    }

    private void t(int i2) {
        this.b.get(0).a = 0.0f;
        float fB = b(i2, 0, 667);
        i.a aVar = this.b.get(0);
        i.a aVar2 = this.b.get(1);
        float interpolation = this.d.getInterpolation(fB);
        aVar2.a = interpolation;
        aVar.b = interpolation;
        i.a aVar3 = this.b.get(1);
        i.a aVar4 = this.b.get(2);
        float interpolation2 = this.d.getInterpolation(fB + 0.49925038f);
        aVar4.a = interpolation2;
        aVar3.b = interpolation2;
        this.b.get(2).b = 1.0f;
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
    }

    @Override // com.google.android.material.progressindicator.j
    public void f() {
    }

    @Override // com.google.android.material.progressindicator.j
    public void g() {
        o();
        q();
        this.c.start();
    }

    @Override // com.google.android.material.progressindicator.j
    public void h() {
    }

    void q() {
        this.g = true;
        this.f = 1;
        for (i.a aVar : this.b) {
            com.google.android.material.progressindicator.b bVar = this.e;
            aVar.c = bVar.e[0];
            aVar.d = bVar.i / 2;
        }
    }

    void r(float f) {
        this.h = f;
        t((int) (f * 333.0f));
        p();
        this.a.invalidateSelf();
    }
}
