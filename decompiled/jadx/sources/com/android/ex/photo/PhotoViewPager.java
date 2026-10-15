package com.android.ex.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import ax.p035c0.C;

/* JADX INFO: loaded from: classes.dex */
public class PhotoViewPager extends ViewPager {
    private float r1;
    private int s1;
    private float t1;
    private float u1;
    private c v1;

    class a implements ViewPager.k {
        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.k
        public void a(View view, float f) {
            if (f < 0.0f || f >= 1.0f) {
                view.setTranslationX(0.0f);
                view.setAlpha(1.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                return;
            }
            try {
                view.setTranslationX((-f) * view.getWidth());
                view.setAlpha(Math.max(0.0f, 1.0f - f));
                float fMax = Math.max(0.0f, 1.0f - (f * 0.3f));
                view.setScaleX(fMax);
                view.setScaleY(fMax);
            } catch (IllegalArgumentException unused) {
                view.setTranslationX(0.0f);
                view.setAlpha(1.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
            }
        }
    }

    public enum b {
        NONE,
        LEFT,
        RIGHT,
        BOTH
    }

    public interface c {
        b u(float f, float f2);
    }

    public PhotoViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        U();
    }

    private void U() {
        Q(true, new a());
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i;
        c cVar = this.v1;
        b bVarU = cVar != null ? cVar.u(this.t1, this.u1) : b.NONE;
        b bVar = b.BOTH;
        boolean z = bVarU == bVar || bVarU == b.LEFT;
        boolean z2 = bVarU == bVar || bVarU == b.RIGHT;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.s1 = -1;
        }
        if (action == 0) {
            this.r1 = motionEvent.getX();
            this.t1 = motionEvent.getRawX();
            this.u1 = motionEvent.getRawY();
            this.s1 = C.c(motionEvent, 0);
        } else if (action != 2) {
            if (action == 6) {
                int iB = C.b(motionEvent);
                if (C.c(motionEvent, iB) == this.s1) {
                    int i2 = iB != 0 ? 0 : 1;
                    this.r1 = C.d(motionEvent, i2);
                    this.s1 = C.c(motionEvent, i2);
                }
            }
        } else if ((z || z2) && (i = this.s1) != -1) {
            float fD = C.d(motionEvent, C.a(motionEvent, i));
            if (z && z2) {
                this.r1 = fD;
                return false;
            }
            if (z && fD > this.r1) {
                this.r1 = fD;
                return false;
            }
            if (z2 && fD < this.r1) {
                this.r1 = fD;
                return false;
            }
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setOnInterceptTouchListener(c cVar) {
        this.v1 = cVar;
    }
}
