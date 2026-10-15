package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import ax.R6.g;
import ax.R6.i;
import ax.R6.m;
import ax.p346v7.h;
import ax.p346v7.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
class d extends ConstraintLayout {
    private final Runnable H0;
    private int I0;
    private h J0;

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(i.k, this);
        setBackground(C());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.v4, i, 0);
        this.I0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(m.w4, 0);
        this.H0 = new Runnable() { // from class: com.google.android.material.timepicker.c
            @Override // java.lang.Runnable
            public final void run() {
                this.q.H();
            }
        };
        typedArrayObtainStyledAttributes.recycle();
    }

    private void B(List<View> list, androidx.constraintlayout.widget.e eVar, int i) {
        Iterator<View> it = list.iterator();
        float size = 0.0f;
        while (it.hasNext()) {
            eVar.h(it.next().getId(), g.c, i, size);
            size += 360.0f / list.size();
        }
    }

    private Drawable C() {
        h hVar = new h();
        this.J0 = hVar;
        hVar.d0(new j(0.5f));
        this.J0.g0(ColorStateList.valueOf(-1));
        return this.J0;
    }

    private static boolean G(View view) {
        return "skip".equals(view.getTag());
    }

    private void I() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.H0);
            handler.post(this.H0);
        }
    }

    int D(int i) {
        return i == 2 ? Math.round(this.I0 * 0.66f) : this.I0;
    }

    public int E() {
        return this.I0;
    }

    public void F(int i) {
        this.I0 = i;
        H();
    }

    protected void H() {
        androidx.constraintlayout.widget.e eVar = new androidx.constraintlayout.widget.e();
        eVar.f(this);
        HashMap map = new HashMap();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getId() != g.c && !G(childAt)) {
                int i2 = (Integer) childAt.getTag(g.m);
                if (i2 == null) {
                    i2 = 1;
                }
                if (!map.containsKey(i2)) {
                    map.put(i2, new ArrayList());
                }
                ((List) map.get(i2)).add(childAt);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            B((List) entry.getValue(), eVar, D(((Integer) entry.getKey()).intValue()));
        }
        eVar.c(this);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            view.setId(View.generateViewId());
        }
        I();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        H();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        I();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.J0.g0(ColorStateList.valueOf(i));
    }
}
