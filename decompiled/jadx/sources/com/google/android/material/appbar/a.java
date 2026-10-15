package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX INFO: loaded from: classes3.dex */
abstract class a<V extends View> extends c<V> {
    private Runnable Z;
    OverScroller k0;
    private boolean l0;
    private int m0;
    private int n0;
    private int o0;
    private VelocityTracker p0;

    /* JADX INFO: renamed from: com.google.android.material.appbar.a$a, reason: collision with other inner class name */
    private class RunnableC0568a implements Runnable {
        private final V X;
        private final CoordinatorLayout q;

        RunnableC0568a(CoordinatorLayout coordinatorLayout, V v) {
            this.q = coordinatorLayout;
            this.X = v;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.X == null || (overScroller = a.this.k0) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                a.this.R(this.q, this.X);
                return;
            }
            a aVar = a.this;
            aVar.T(this.q, this.X, aVar.k0.getCurrY());
            this.X.postOnAnimation(this);
        }
    }

    public a() {
        this.m0 = -1;
        this.o0 = -1;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m0 = -1;
        this.o0 = -1;
    }

    private void M() {
        if (this.p0 == null) {
            this.p0 = VelocityTracker.obtain();
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x007a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0084  */
    /* JADX WARN: Code duplicated, block: B:33:0x008b A[ADDED_TO_REGION] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean H(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z;
        VelocityTracker velocityTracker;
        VelocityTracker velocityTracker2;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            VelocityTracker velocityTracker3 = this.p0;
            if (velocityTracker3 != null) {
                velocityTracker3.addMovement(motionEvent);
                this.p0.computeCurrentVelocity(1000);
                N(coordinatorLayout, v, -P(v), 0, this.p0.getYVelocity(this.m0));
                z = true;
            }
            this.l0 = false;
            this.m0 = -1;
            velocityTracker = this.p0;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.p0 = null;
            }
            velocityTracker2 = this.p0;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
            }
            if (this.l0) {
            }
        }
        if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.m0);
            if (iFindPointerIndex == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(iFindPointerIndex);
            int i = this.n0 - y;
            this.n0 = y;
            S(coordinatorLayout, v, i, O(v), 0);
        } else if (actionMasked != 3) {
            if (actionMasked == 6) {
                int i2 = motionEvent.getActionIndex() == 0 ? 1 : 0;
                this.m0 = motionEvent.getPointerId(i2);
                this.n0 = (int) (motionEvent.getY(i2) + 0.5f);
            }
        }
        z = false;
        velocityTracker2 = this.p0;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        return !this.l0 || z;
        z = false;
        this.l0 = false;
        this.m0 = -1;
        velocityTracker = this.p0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.p0 = null;
        }
        velocityTracker2 = this.p0;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        if (this.l0) {
        }
    }

    boolean L(V v) {
        return false;
    }

    final boolean N(CoordinatorLayout coordinatorLayout, V v, int i, int i2, float f) {
        Runnable runnable = this.Z;
        if (runnable != null) {
            v.removeCallbacks(runnable);
            this.Z = null;
        }
        if (this.k0 == null) {
            this.k0 = new OverScroller(v.getContext());
        }
        this.k0.fling(0, I(), 0, Math.round(f), 0, 0, i, i2);
        if (!this.k0.computeScrollOffset()) {
            R(coordinatorLayout, v);
            return false;
        }
        RunnableC0568a runnableC0568a = new RunnableC0568a(coordinatorLayout, v);
        this.Z = runnableC0568a;
        v.postOnAnimation(runnableC0568a);
        return true;
    }

    int O(V v) {
        return -v.getHeight();
    }

    int P(V v) {
        return v.getHeight();
    }

    int Q() {
        return I();
    }

    void R(CoordinatorLayout coordinatorLayout, V v) {
    }

    final int S(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return U(coordinatorLayout, v, Q() - i, i2, i3);
    }

    int T(CoordinatorLayout coordinatorLayout, V v, int i) {
        return U(coordinatorLayout, v, i, Integer.MIN_VALUE, SubsamplingScaleImageView.TILE_SIZE_AUTO);
    }

    int U(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        int iB;
        int I = I();
        if (i2 == 0 || I < i2 || I > i3 || I == (iB = ax.W.a.b(i, i2, i3))) {
            return 0;
        }
        K(iB);
        return I - iB;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.o0 < 0) {
            this.o0 = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.l0) {
            int i = this.m0;
            if (i == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y - this.n0) > this.o0) {
                this.n0 = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.m0 = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            boolean z = L(v) && coordinatorLayout.B(v, x, y2);
            this.l0 = z;
            if (z) {
                this.n0 = y2;
                this.m0 = motionEvent.getPointerId(0);
                M();
                OverScroller overScroller = this.k0;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.k0.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.p0;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }
}
