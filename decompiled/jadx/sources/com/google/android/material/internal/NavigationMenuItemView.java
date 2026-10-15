package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.k;
import androidx.appcompat.widget.B;
import ax.R6.e;
import ax.R6.f;
import ax.R6.i;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p115h0.j;
import ax.p202m7.h;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationMenuItemView extends h implements k.a {
    private static final int[] P0 = {R.attr.state_checked};
    private int E0;
    private boolean F0;
    boolean G0;
    boolean H0;
    private final CheckedTextView I0;
    private FrameLayout J0;
    private g K0;
    private ColorStateList L0;
    private boolean M0;
    private Drawable N0;
    private final C1832a O0;

    class a extends C1832a {
        a() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.k0(NavigationMenuItemView.this.G0);
        }
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.H0 = true;
        a aVar = new a();
        this.O0 = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(i.f, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(e.d));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(ax.R6.g.h);
        this.I0 = checkedTextView;
        C1839d0.l0(checkedTextView, aVar);
    }

    private void B() {
        if (D()) {
            this.I0.setVisibility(8);
            FrameLayout frameLayout = this.J0;
            if (frameLayout != null) {
                B.a aVar = (B.a) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) aVar).width = -1;
                this.J0.setLayoutParams(aVar);
                return;
            }
            return;
        }
        this.I0.setVisibility(0);
        FrameLayout frameLayout2 = this.J0;
        if (frameLayout2 != null) {
            B.a aVar2 = (B.a) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) aVar2).width = -2;
            this.J0.setLayoutParams(aVar2);
        }
    }

    private StateListDrawable C() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(ax.p194m.a.w, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(P0, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    private boolean D() {
        return this.K0.getTitle() == null && this.K0.getIcon() == null && this.K0.getActionView() != null;
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.J0 == null) {
                this.J0 = (FrameLayout) ((ViewStub) findViewById(ax.R6.g.g)).inflate();
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.J0.removeAllViews();
            this.J0.addView(view);
        }
    }

    @Override // androidx.appcompat.view.menu.k.a
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k.a
    public void f(g gVar, int i) {
        this.K0 = gVar;
        if (gVar.getItemId() > 0) {
            setId(gVar.getItemId());
        }
        setVisibility(gVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            setBackground(C());
        }
        setCheckable(gVar.isCheckable());
        setChecked(gVar.isChecked());
        setEnabled(gVar.isEnabled());
        setTitle(gVar.getTitle());
        setIcon(gVar.getIcon());
        setActionView(gVar.getActionView());
        setContentDescription(gVar.getContentDescription());
        ax.p322u.B.a(this, gVar.getTooltipText());
        B();
    }

    @Override // androidx.appcompat.view.menu.k.a
    public g getItemData() {
        return this.K0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        g gVar = this.K0;
        if (gVar != null && gVar.isCheckable() && this.K0.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, P0);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.G0 != z) {
            this.G0 = z;
            this.O0.l(this.I0, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.I0.setChecked(z);
        CheckedTextView checkedTextView = this.I0;
        checkedTextView.setTypeface(checkedTextView.getTypeface(), (z && this.H0) ? 1 : 0);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, getPaddingTop(), i, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.M0) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = ax.U.a.r(drawable).mutate();
                drawable.setTintList(this.L0);
            }
            int i = this.E0;
            drawable.setBounds(0, 0, i, i);
        } else if (this.F0) {
            if (this.N0 == null) {
                Drawable drawableE = ax.S.h.e(getResources(), f.l, getContext().getTheme());
                this.N0 = drawableE;
                if (drawableE != null) {
                    int i2 = this.E0;
                    drawableE.setBounds(0, 0, i2, i2);
                }
            }
            drawable = this.N0;
        }
        this.I0.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.I0.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.E0 = i;
    }

    void setIconTintList(ColorStateList colorStateList) {
        this.L0 = colorStateList;
        this.M0 = colorStateList != null;
        g gVar = this.K0;
        if (gVar != null) {
            setIcon(gVar.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.I0.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.F0 = z;
    }

    public void setTextAppearance(int i) {
        j.m(this.I0, i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.I0.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.I0.setText(charSequence);
    }
}
