package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
final class b {
    private final Rect a;
    private final ColorStateList b;
    private final ColorStateList c;
    private final ColorStateList d;
    private final int e;
    private final ax.p346v7.l f;

    private b(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, ax.p346v7.l lVar, Rect rect) {
        ax.p019b0.g.d(rect.left);
        ax.p019b0.g.d(rect.top);
        ax.p019b0.g.d(rect.right);
        ax.p019b0.g.d(rect.bottom);
        this.a = rect;
        this.b = colorStateList2;
        this.c = colorStateList;
        this.d = colorStateList3;
        this.e = i;
        this.f = lVar;
    }

    static b a(Context context, int i) {
        ax.p019b0.g.b(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, ax.R6.m.n3);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(ax.R6.m.o3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(ax.R6.m.q3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(ax.R6.m.p3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(ax.R6.m.r3, 0));
        ColorStateList colorStateListA = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, ax.R6.m.s3);
        ColorStateList colorStateListA2 = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, ax.R6.m.x3);
        ColorStateList colorStateListA3 = ax.p282r7.c.a(context, typedArrayObtainStyledAttributes, ax.R6.m.v3);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(ax.R6.m.w3, 0);
        ax.p346v7.l lVarM = ax.p346v7.l.b(context, typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.t3, 0), typedArrayObtainStyledAttributes.getResourceId(ax.R6.m.u3, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new b(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, lVarM, rect);
    }

    int b() {
        return this.a.bottom;
    }

    int c() {
        return this.a.top;
    }

    void d(TextView textView) {
        e(textView, null, null);
    }

    void e(TextView textView, ColorStateList colorStateList, ColorStateList colorStateList2) {
        ax.p346v7.h hVar = new ax.p346v7.h();
        ax.p346v7.h hVar2 = new ax.p346v7.h();
        hVar.setShapeAppearanceModel(this.f);
        hVar2.setShapeAppearanceModel(this.f);
        if (colorStateList == null) {
            colorStateList = this.c;
        }
        hVar.g0(colorStateList);
        hVar.n0(this.e, this.d);
        if (colorStateList2 == null) {
            colorStateList2 = this.b;
        }
        textView.setTextColor(colorStateList2);
        RippleDrawable rippleDrawable = new RippleDrawable(this.b.withAlpha(30), hVar, hVar2);
        Rect rect = this.a;
        textView.setBackground(new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
