package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes3.dex */
final class c {
    final b a;
    final b b;
    final b c;
    final b d;
    final b e;
    final b f;
    final b g;
    final Paint h;

    c(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(ax.p282r7.b.f(context, ax.R6.c.w, j.class.getCanonicalName()), ax.R6.m.d3);
        this.a = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.h3, 0));
        this.g = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.f3, 0));
        this.b = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.g3, 0));
        this.c = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.i3, 0));
        ColorStateList colorStateListA = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, ax.R6.m.j3);
        this.d = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.l3, 0));
        this.e = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.k3, 0));
        this.f = b.a(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.m3, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
