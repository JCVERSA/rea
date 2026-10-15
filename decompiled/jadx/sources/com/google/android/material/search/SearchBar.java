package com.google.android.material.search;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.c;
import ax.R6.e;
import ax.R6.f;
import ax.R6.l;
import ax.p202m7.x;
import ax.p346v7.h;
import ax.p346v7.i;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes3.dex */
public class SearchBar extends Toolbar {
    private static final int y1 = l.n;
    private final TextView d1;
    private final TextView e1;
    private final FrameLayout f1;
    private boolean g1;
    private final ColorStateList h1;
    private final boolean i1;
    private final boolean j1;
    private final Drawable k1;
    private final boolean l1;
    private final boolean m1;
    private View n1;
    private Integer o1;
    private Drawable p1;
    private int q1;
    private boolean r1;
    private h s1;
    private boolean t1;
    private int u1;
    private ActionMenuView v1;
    private ImageButton w1;
    private final AppBarLayout.f x1;

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
        private boolean n0;

        public ScrollingViewBehavior() {
            this.n0 = false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.n0 = false;
        }

        private void Y(AppBarLayout appBarLayout) {
            appBarLayout.setBackgroundColor(0);
            if (Build.VERSION.SDK_INT == 21) {
                appBarLayout.setOutlineProvider(null);
            } else {
                appBarLayout.setTargetElevation(0.0f);
            }
        }

        @Override // com.google.android.material.appbar.b
        protected boolean T() {
            return true;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean l(CoordinatorLayout coordinatorLayout, View view, View view2) {
            boolean zL = super.l(coordinatorLayout, view, view2);
            if (!this.n0 && (view2 instanceof AppBarLayout)) {
                this.n0 = true;
                Y((AppBarLayout) view2);
            }
            return zL;
        }
    }

    static class a extends ax.p163k0.a {
        public static final Parcelable.Creator<a> CREATOR = new C0579a();
        String Y;

        /* JADX INFO: renamed from: com.google.android.material.search.SearchBar$a$a, reason: collision with other inner class name */
        class C0579a implements Parcelable.ClassLoaderCreator<a> {
            C0579a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new a(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }
        }

        public a(Parcel parcel) {
            this(parcel, null);
        }

        public a(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.Y = parcel.readString();
        }

        public a(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.Y);
        }
    }

    private void T() {
        AppBarLayout appBarLayoutParentIfExists = getAppBarLayoutParentIfExists();
        if (appBarLayoutParentIfExists == null || this.h1 == null) {
            return;
        }
        appBarLayoutParentIfExists.c(this.x1);
    }

    private int U(int i, int i2) {
        return i == 0 ? i2 : i;
    }

    private ActionMenuView V() {
        if (this.v1 == null) {
            this.v1 = x.a(this);
        }
        return this.v1;
    }

    private ImageButton W() {
        if (this.w1 == null) {
            this.w1 = x.d(this);
        }
        return this.w1;
    }

    private void X(View view, int i, int i2, int i3, int i4) {
        if (getLayoutDirection() == 1) {
            view.layout(getMeasuredWidth() - i3, i2, getMeasuredWidth() - i, i4);
        } else {
            view.layout(i, i2, i3, i4);
        }
    }

    private void Y() {
        int measuredWidth = (getMeasuredWidth() / 2) - (this.f1.getMeasuredWidth() / 2);
        int measuredWidth2 = this.f1.getMeasuredWidth() + measuredWidth;
        int measuredHeight = (getMeasuredHeight() / 2) - (this.f1.getMeasuredHeight() / 2);
        int measuredHeight2 = this.f1.getMeasuredHeight() + measuredHeight;
        boolean z = getLayoutDirection() == 1;
        View viewV = V();
        ImageButton imageButtonW = W();
        int measuredWidth3 = (this.f1.getMeasuredWidth() / 2) - (this.d1.getMeasuredWidth() / 2);
        int measuredWidth4 = this.d1.getMeasuredWidth() + measuredWidth3;
        int i = measuredWidth3 + measuredWidth;
        int i2 = measuredWidth4 + measuredWidth;
        View view = z ? viewV : imageButtonW;
        if (z) {
            viewV = imageButtonW;
        }
        int iMax = view != null ? Math.max(view.getRight() - i, 0) : 0;
        int i3 = i + iMax;
        int i4 = i2 + iMax;
        int iMax2 = viewV != null ? Math.max(i4 - viewV.getLeft(), 0) : 0;
        int i5 = i3 - iMax2;
        int i6 = i4 - iMax2;
        int iMax3 = ((iMax - iMax2) + Math.max(Math.max(getPaddingLeft() - i5, getContentInsetLeft() - i5), 0)) - Math.max(Math.max(i6 - (getMeasuredWidth() - getPaddingRight()), i6 - (getMeasuredWidth() - getContentInsetRight())), 0);
        this.f1.layout(measuredWidth + iMax3, measuredHeight, measuredWidth2 + iMax3, measuredHeight2);
    }

    private void Z(View view) {
        if (view == null) {
            return;
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredWidth2 = (getMeasuredWidth() / 2) - (measuredWidth / 2);
        int i = measuredWidth2 + measuredWidth;
        int measuredHeight = view.getMeasuredHeight();
        int measuredHeight2 = (getMeasuredHeight() / 2) - (measuredHeight / 2);
        X(view, measuredWidth2, measuredHeight2, i, measuredHeight2 + measuredHeight);
    }

    private Drawable a0(Drawable drawable) {
        int iD;
        if (!this.l1 || drawable == null) {
            return drawable;
        }
        Integer num = this.o1;
        if (num != null) {
            iD = num.intValue();
        } else {
            iD = ax.p106g7.a.d(this, drawable == this.k1 ? c.g : c.f);
        }
        Drawable drawableR = ax.U.a.r(drawable.mutate());
        drawableR.setTint(iD);
        return drawableR;
    }

    private void b0(int i, int i2) {
        View view = this.n1;
        if (view != null) {
            view.measure(i, i2);
        }
    }

    private void c0() {
        AppBarLayout appBarLayoutParentIfExists = getAppBarLayoutParentIfExists();
        if (appBarLayoutParentIfExists != null) {
            appBarLayoutParentIfExists.v(this.x1);
        }
    }

    private void d0() {
        if (this.j1 && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(e.y);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(getDefaultMarginVerticalResource());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.leftMargin = U(marginLayoutParams.leftMargin, dimensionPixelSize);
            marginLayoutParams.topMargin = U(marginLayoutParams.topMargin, dimensionPixelSize2);
            marginLayoutParams.rightMargin = U(marginLayoutParams.rightMargin, dimensionPixelSize);
            marginLayoutParams.bottomMargin = U(marginLayoutParams.bottomMargin, dimensionPixelSize2);
        }
    }

    private void e0() {
        int width;
        if (Build.VERSION.SDK_INT < 34) {
            return;
        }
        int right = 0;
        boolean z = getLayoutDirection() == 1;
        ImageButton imageButtonD = x.d(this);
        if (imageButtonD == null || !imageButtonD.isClickable()) {
            width = 0;
        } else {
            width = z ? getWidth() - imageButtonD.getLeft() : imageButtonD.getRight();
        }
        ActionMenuView actionMenuViewA = x.a(this);
        if (actionMenuViewA != null) {
            right = z ? actionMenuViewA.getRight() : getWidth() - actionMenuViewA.getLeft();
        }
        float f = -(z ? right : width);
        if (!z) {
            width = right;
        }
        setHandwritingBoundsOffsets(f, 0.0f, -width, 0.0f);
    }

    private void f0() {
        if (getLayoutParams() instanceof AppBarLayout.d) {
            AppBarLayout.d dVar = (AppBarLayout.d) getLayoutParams();
            if (this.r1) {
                if (dVar.c() == 0) {
                    dVar.g(53);
                }
            } else if (dVar.c() == 53) {
                dVar.g(0);
            }
        }
    }

    private AppBarLayout getAppBarLayoutParentIfExists() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof AppBarLayout) {
                return (AppBarLayout) parent;
            }
        }
        return null;
    }

    private void setNavigationIconDecorative(boolean z) {
        ImageButton imageButtonD = x.d(this);
        if (imageButtonD == null) {
            return;
        }
        imageButtonD.setClickable(!z);
        imageButtonD.setFocusable(!z);
        Drawable background = imageButtonD.getBackground();
        if (background != null) {
            this.p1 = background;
        }
        imageButtonD.setBackgroundDrawable(z ? null : this.p1);
        e0();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.i1 && this.n1 == null && !(view instanceof ActionMenuView)) {
            this.n1 = view;
            view.setAlpha(0.0f);
        }
        super.addView(view, i, layoutParams);
    }

    public View getCenterView() {
        return this.n1;
    }

    float getCompatElevation() {
        h hVar = this.s1;
        return hVar != null ? hVar.A() : getElevation();
    }

    public float getCornerSize() {
        return this.s1.N();
    }

    protected int getDefaultMarginVerticalResource() {
        return e.z;
    }

    protected int getDefaultNavigationIconResource() {
        return f.d;
    }

    public CharSequence getHint() {
        return this.d1.getHint();
    }

    public int getMaxWidth() {
        return this.u1;
    }

    int getMenuResId() {
        return this.q1;
    }

    TextView getPlaceholderTextView() {
        return this.e1;
    }

    public int getStrokeColor() {
        return this.s1.J().getDefaultColor();
    }

    public float getStrokeWidth() {
        return this.s1.L();
    }

    public CharSequence getText() {
        return this.d1.getText();
    }

    public boolean getTextCentered() {
        return this.t1;
    }

    public TextView getTextView() {
        return this.d1;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.s1);
        d0();
        f0();
        if (this.g1) {
            T();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c0();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getCanonicalName());
        accessibilityNodeInfo.setEditable(isEnabled());
        CharSequence text = getText();
        boolean zIsEmpty = TextUtils.isEmpty(text);
        if (Build.VERSION.SDK_INT >= 26) {
            accessibilityNodeInfo.setHintText(getHint());
            accessibilityNodeInfo.setShowingHintText(zIsEmpty);
        }
        if (zIsEmpty) {
            text = getHint();
        }
        accessibilityNodeInfo.setText(text);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View view = this.n1;
        if (view != null) {
            Z(view);
        }
        e0();
        if (this.d1 == null || !this.t1) {
            return;
        }
        Y();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.u1;
        if (i3 >= 0 && i3 < View.MeasureSpec.getSize(i)) {
            i = View.MeasureSpec.makeMeasureSpec(this.u1, View.MeasureSpec.getMode(i));
        }
        super.onMeasure(i, i2);
        b0(i, i2);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.a());
        setText(aVar.Y);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        CharSequence text = getText();
        aVar.Y = text == null ? null : text.toString();
        return aVar;
    }

    public void setCenterView(View view) {
        View view2 = this.n1;
        if (view2 != null) {
            removeView(view2);
            this.n1 = null;
        }
        if (view != null) {
            addView(view);
        }
    }

    public void setDefaultScrollFlagsEnabled(boolean z) {
        this.r1 = z;
        f0();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        h hVar = this.s1;
        if (hVar != null) {
            hVar.f0(f);
        }
    }

    public void setHint(int i) {
        this.d1.setHint(i);
    }

    public void setHint(CharSequence charSequence) {
        this.d1.setHint(charSequence);
    }

    public void setLiftOnScroll(boolean z) {
        this.g1 = z;
        if (z) {
            T();
        } else {
            c0();
        }
    }

    public void setMaxWidth(int i) {
        if (this.u1 != i) {
            this.u1 = i;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        super.setNavigationIcon(a0(drawable));
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        if (this.m1) {
            return;
        }
        super.setNavigationOnClickListener(onClickListener);
        setNavigationIconDecorative(onClickListener == null);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        throw null;
    }

    void setPlaceholderText(String str) {
        this.e1.setText(str);
    }

    public void setStrokeColor(int i) {
        if (getStrokeColor() != i) {
            this.s1.o0(ColorStateList.valueOf(i));
        }
    }

    public void setStrokeWidth(float f) {
        if (getStrokeWidth() != f) {
            this.s1.p0(f);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    public void setText(int i) {
        this.d1.setText(i);
        this.e1.setText(i);
    }

    public void setText(CharSequence charSequence) {
        this.d1.setText(charSequence);
        this.e1.setText(charSequence);
    }

    public void setTextCentered(boolean z) {
        this.t1 = z;
        TextView textView = this.d1;
        if (textView == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
        if (z) {
            layoutParams.gravity = 1;
            this.d1.setGravity(1);
        } else {
            layoutParams.gravity = 0;
            this.d1.setGravity(0);
        }
        this.d1.setLayoutParams(layoutParams);
        this.e1.setLayoutParams(layoutParams);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void x(int i) {
        super.x(i);
        this.q1 = i;
    }
}
