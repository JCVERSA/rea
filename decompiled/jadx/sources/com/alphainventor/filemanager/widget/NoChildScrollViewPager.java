package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class NoChildScrollViewPager extends ViewPager {
    private int r1;
    private float s1;

    public NoChildScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        U(context);
    }

    void U(Context context) {
        this.r1 = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            boolean zOnInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
            float x = motionEvent.getX();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.s1 = x;
                return zOnInterceptTouchEvent;
            }
            if (actionMasked == 2 && Math.abs(x - this.s1) > this.r1) {
                return true;
            }
            return zOnInterceptTouchEvent;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
