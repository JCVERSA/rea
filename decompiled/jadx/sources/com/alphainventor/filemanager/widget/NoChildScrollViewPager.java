package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class NoChildScrollViewPager extends ViewPager {
    private int o1;
    private float p1;

    public NoChildScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        U(context);
    }

    void U(Context context) {
        this.o1 = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean zOnInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        float x = motionEvent.getX();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.p1 = x;
        } else if (actionMasked == 2 && Math.abs(x - this.p1) > this.o1) {
            return true;
        }
        return zOnInterceptTouchEvent;
    }
}
