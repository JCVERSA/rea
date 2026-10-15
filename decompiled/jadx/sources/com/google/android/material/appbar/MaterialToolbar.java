package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import ax.R6.l;
import ax.R6.m;
import ax.p154j7.f;
import ax.p202m7.w;
import ax.p202m7.x;
import ax.p346v7.h;
import ax.p346v7.i;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialToolbar extends Toolbar {
    private static final int i1 = l.G;
    private static final ImageView.ScaleType[] j1 = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private Integer d1;
    private boolean e1;
    private boolean f1;
    private ImageView.ScaleType g1;
    private Boolean h1;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.p194m.a.T);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        int i2 = i1;
        super(ax.C7.a.d(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayI = w.i(context2, attributeSet, m.l4, i, i2, new int[0]);
        int i3 = m.o4;
        if (typedArrayI.hasValue(i3)) {
            setNavigationIconTint(typedArrayI.getColor(i3, -1));
        }
        this.e1 = typedArrayI.getBoolean(m.q4, false);
        this.f1 = typedArrayI.getBoolean(m.p4, false);
        int i4 = typedArrayI.getInt(m.n4, -1);
        if (i4 >= 0) {
            ImageView.ScaleType[] scaleTypeArr = j1;
            if (i4 < scaleTypeArr.length) {
                this.g1 = scaleTypeArr[i4];
            }
        }
        int i5 = m.m4;
        if (typedArrayI.hasValue(i5)) {
            this.h1 = Boolean.valueOf(typedArrayI.getBoolean(i5, false));
        }
        typedArrayI.recycle();
        U(context2);
    }

    private Pair<Integer, Integer> T(TextView textView, TextView textView2) {
        int measuredWidth = getMeasuredWidth();
        int i = measuredWidth / 2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = measuredWidth - getPaddingRight();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8 && childAt != textView && childAt != textView2) {
                if (childAt.getRight() < i && childAt.getRight() > paddingLeft) {
                    paddingLeft = childAt.getRight();
                }
                if (childAt.getLeft() > i && childAt.getLeft() < paddingRight) {
                    paddingRight = childAt.getLeft();
                }
            }
        }
        return new Pair<>(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
    }

    private void U(Context context) {
        Drawable background = getBackground();
        ColorStateList colorStateListValueOf = background == null ? ColorStateList.valueOf(0) : f.f(background);
        if (colorStateListValueOf != null) {
            h hVar = new h();
            hVar.g0(colorStateListValueOf);
            hVar.U(context);
            hVar.f0(getElevation());
            setBackground(hVar);
        }
    }

    private void V(View view, Pair<Integer, Integer> pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = view.getMeasuredWidth();
        int i = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i2 = measuredWidth2 + i;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i, 0), Math.max(i2 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i += iMax;
            i2 -= iMax;
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 - i, 1073741824), view.getMeasuredHeightAndState());
        }
        view.layout(i, view.getTop(), i2, view.getBottom());
    }

    private void W() {
        if (this.e1 || this.f1) {
            TextView textViewG = x.g(this);
            TextView textViewE = x.e(this);
            if (textViewG == null && textViewE == null) {
                return;
            }
            Pair<Integer, Integer> pairT = T(textViewG, textViewE);
            if (this.e1 && textViewG != null) {
                V(textViewG, pairT);
            }
            if (!this.f1 || textViewE == null) {
                return;
            }
            V(textViewE, pairT);
        }
    }

    private Drawable X(Drawable drawable) {
        if (drawable == null || this.d1 == null) {
            return drawable;
        }
        Drawable drawableR = ax.U.a.r(drawable.mutate());
        drawableR.setTint(this.d1.intValue());
        return drawableR;
    }

    private void Y() {
        ImageView imageViewC = x.c(this);
        if (imageViewC != null) {
            Boolean bool = this.h1;
            if (bool != null) {
                imageViewC.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.g1;
            if (scaleType != null) {
                imageViewC.setScaleType(scaleType);
            }
        }
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.g1;
    }

    public Integer getNavigationIconTint() {
        return this.d1;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        W();
        Y();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        i.d(this, f);
    }

    public void setLogoAdjustViewBounds(boolean z) {
        Boolean bool = this.h1;
        if (bool == null || bool.booleanValue() != z) {
            this.h1 = Boolean.valueOf(z);
            requestLayout();
        }
    }

    public void setLogoScaleType(ImageView.ScaleType scaleType) {
        if (this.g1 != scaleType) {
            this.g1 = scaleType;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        super.setNavigationIcon(X(drawable));
    }

    public void setNavigationIconTint(int i) {
        this.d1 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.f1 != z) {
            this.f1 = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.e1 != z) {
            this.e1 = z;
            requestLayout();
        }
    }
}
