package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class MyViewPager extends ViewPager {
    private boolean r1;

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r1 = true;
    }

    public void U(boolean z) {
        this.r1 = z;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public boolean p(KeyEvent keyEvent) {
        if (this.r1) {
            return super.p(keyEvent);
        }
        return false;
    }
}
