package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p051d0.M;

/* JADX INFO: loaded from: classes3.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {
    c X;
    private boolean Y;
    private boolean Z;
    private boolean l0;
    ax.p179l0.c q;
    private float k0 = 0.0f;
    int m0 = 2;
    float n0 = 0.5f;
    float o0 = 0.0f;
    float p0 = 0.5f;
    private final ax.p179l0.c.AbstractC0376c q0 = new a();

    class a extends ax.p179l0.c.AbstractC0376c {
        private int a;
        private int b = -1;

        a() {
        }

        private boolean n(View view, float f) {
            if (f == 0.0f) {
                return Math.abs(view.getLeft() - this.a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.n0);
            }
            boolean z = view.getLayoutDirection() == 1;
            int i = SwipeDismissBehavior.this.m0;
            if (i == 2) {
                return true;
            }
            if (i == 0) {
                if (z) {
                    return f < 0.0f;
                }
                return f > 0.0f;
            }
            if (i == 1) {
                if (z) {
                    return f > 0.0f;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int a(View view, int i, int i2) {
            int width;
            int width2;
            int width3;
            boolean z = view.getLayoutDirection() == 1;
            int i3 = SwipeDismissBehavior.this.m0;
            if (i3 == 0) {
                if (z) {
                    width = this.a - view.getWidth();
                    width2 = this.a;
                } else {
                    width = this.a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i3 != 1) {
                width = this.a - view.getWidth();
                width2 = view.getWidth() + this.a;
            } else if (z) {
                width = this.a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.a - view.getWidth();
                width2 = this.a;
            }
            return SwipeDismissBehavior.L(width, i, width2);
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void i(View view, int i) {
            this.b = i;
            this.a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior.this.Z = true;
                parent.requestDisallowInterceptTouchEvent(true);
                SwipeDismissBehavior.this.Z = false;
            }
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void j(int i) {
            c cVar = SwipeDismissBehavior.this.X;
            if (cVar != null) {
                cVar.b(i);
            }
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public void k(View view, int i, int i2, int i3, int i4) {
            float width = view.getWidth() * SwipeDismissBehavior.this.o0;
            float width2 = view.getWidth() * SwipeDismissBehavior.this.p0;
            float fAbs = Math.abs(i - this.a);
            if (fAbs <= width) {
                view.setAlpha(1.0f);
            } else if (fAbs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.K(0.0f, 1.0f - SwipeDismissBehavior.N(width, width2, fAbs), 1.0f));
            }
        }

        /* JADX WARN: Code duplicated, block: B:10:0x001d  */
        @Override // ax.p179l0.c.AbstractC0376c
        public void l(View view, float f, float f2) {
            int i;
            boolean z;
            c cVar;
            this.b = -1;
            int width = view.getWidth();
            if (n(view, f)) {
                if (f >= 0.0f) {
                    int left = view.getLeft();
                    int i2 = this.a;
                    if (left < i2) {
                        i = this.a - width;
                    } else {
                        i = i2 + width;
                    }
                } else {
                    i = this.a - width;
                }
                z = true;
            } else {
                i = this.a;
                z = false;
            }
            if (SwipeDismissBehavior.this.q.O(i, view.getTop())) {
                view.postOnAnimation(new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.X) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // ax.p179l0.c.AbstractC0376c
        public boolean m(View view, int i) {
            int i2 = this.b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.J(view);
        }
    }

    class b implements M {
        b() {
        }

        @Override // ax.p051d0.M
        public boolean a(View view, M.a aVar) {
            if (!SwipeDismissBehavior.this.J(view)) {
                return false;
            }
            boolean z = view.getLayoutDirection() == 1;
            int i = SwipeDismissBehavior.this.m0;
            C1839d0.V(view, (!(i == 0 && z) && (i != 1 || z)) ? view.getWidth() : -view.getWidth());
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.X;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    public interface c {
        void a(View view);

        void b(int i);
    }

    private class d implements Runnable {
        private final boolean X;
        private final View q;

        d(View view, boolean z) {
            this.q = view;
            this.X = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            ax.p179l0.c cVar2 = SwipeDismissBehavior.this.q;
            if (cVar2 != null && cVar2.m(true)) {
                this.q.postOnAnimation(this);
            } else {
                if (!this.X || (cVar = SwipeDismissBehavior.this.X) == null) {
                    return;
                }
                cVar.a(this.q);
            }
        }
    }

    static float K(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int L(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private void M(ViewGroup viewGroup) {
        if (this.q == null) {
            this.q = this.l0 ? ax.p179l0.c.n(viewGroup, this.k0, this.q0) : ax.p179l0.c.o(viewGroup, this.q0);
        }
    }

    static float N(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    private void S(View view) {
        C1839d0.f0(view, 1048576);
        if (J(view)) {
            C1839d0.h0(view, H.a.y, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean H(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.q == null) {
            return false;
        }
        if (this.Z && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.q.F(motionEvent);
        return true;
    }

    public boolean J(View view) {
        return true;
    }

    public void O(float f) {
        this.p0 = K(0.0f, f, 1.0f);
    }

    public void P(c cVar) {
        this.X = cVar;
    }

    public void Q(float f) {
        this.o0 = K(0.0f, f, 1.0f);
    }

    public void R(int i) {
        this.m0 = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zB = this.Y;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zB = coordinatorLayout.B(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.Y = zB;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.Y = false;
        }
        if (zB) {
            M(coordinatorLayout);
            if (!this.Z && this.q.P(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean p(CoordinatorLayout coordinatorLayout, V v, int i) {
        boolean zP = super.p(coordinatorLayout, v, i);
        if (v.getImportantForAccessibility() == 0) {
            v.setImportantForAccessibility(1);
            S(v);
        }
        return zP;
    }
}
