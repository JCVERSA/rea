package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import ax.p406z3.C2461b;

/* JADX INFO: loaded from: classes.dex */
public class BlockTouchView extends View {
    public BlockTouchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public boolean isClickable() {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        C2461b.g("Set click listener on block touch view");
        super.setOnClickListener(onClickListener);
    }
}
