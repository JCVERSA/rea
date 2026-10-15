package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import ax.R6.k;
import ax.R6.l;
import ax.R6.m;
import ax.p035c0.C1839d0;
import ax.p035c0.M;
import ax.p051d0.H;
import ax.p202m7.w;
import ax.p202m7.z;
import ax.p322u.B;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {
    private static final int g1 = l.j;
    private static final ax.p019b0.d<g> h1 = new ax.p019b0.f(16);
    float A0;
    float B0;
    float C0;
    final int D0;
    int E0;
    private final int F0;
    private final int G0;
    private final int H0;
    private int I0;
    int J0;
    int K0;
    int L0;
    int M0;
    boolean N0;
    boolean O0;
    int P0;
    int Q0;
    boolean R0;
    private com.google.android.material.tabs.c S0;
    private final TimeInterpolator T0;
    private c U0;
    private final ArrayList<c> V0;
    private c W0;
    private ValueAnimator X0;
    ViewPager Y0;
    private androidx.viewpager.widget.a Z0;
    private DataSetObserver a1;
    private h b1;
    private b c1;
    private boolean d1;
    private int e1;
    private final ax.p019b0.d<i> f1;
    private final ArrayList<g> k0;
    private g l0;
    final f m0;
    int n0;
    int o0;
    int p0;
    int q;
    int q0;
    private final int r0;
    private final int s0;
    private int t0;
    ColorStateList u0;
    ColorStateList v0;
    ColorStateList w0;
    Drawable x0;
    private int y0;
    PorterDuff.Mode z0;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    private class b implements ViewPager.i {
        private boolean a;

        b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void a(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.Y0 == viewPager) {
                tabLayout.N(aVar2, this.a);
            }
        }

        void b(boolean z) {
            this.a = z;
        }
    }

    @Deprecated
    public interface c<T extends g> {
        void a(T t);

        void b(T t);

        void c(T t);
    }

    public interface d extends c<g> {
    }

    private class e extends DataSetObserver {
        e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.G();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.G();
        }
    }

    class f extends LinearLayout {
        private int k0;
        ValueAnimator q;

        class a implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ View a;
            final /* synthetic */ View b;

            a(View view, View view2) {
                this.a = view;
                this.b = view2;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                f.this.j(this.a, this.b, valueAnimator.getAnimatedFraction());
            }
        }

        f(Context context) {
            super(context);
            this.k0 = -1;
            setWillNotDraw(false);
        }

        private void e() {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.q == -1) {
                tabLayout.q = tabLayout.getSelectedTabPosition();
            }
            f(TabLayout.this.q);
        }

        private void f(int i) {
            if (TabLayout.this.e1 == 0 || (TabLayout.this.getTabSelectedIndicator().getBounds().left == -1 && TabLayout.this.getTabSelectedIndicator().getBounds().right == -1)) {
                View childAt = getChildAt(i);
                com.google.android.material.tabs.c cVar = TabLayout.this.S0;
                TabLayout tabLayout = TabLayout.this;
                cVar.c(tabLayout, childAt, tabLayout.x0);
                TabLayout.this.q = i;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            f(TabLayout.this.getSelectedTabPosition());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j(View view, View view2, float f) {
            if (view == null || view.getWidth() <= 0) {
                Drawable drawable = TabLayout.this.x0;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.x0.getBounds().bottom);
            } else {
                com.google.android.material.tabs.c cVar = TabLayout.this.S0;
                TabLayout tabLayout = TabLayout.this;
                cVar.d(tabLayout, view, view2, f, tabLayout.x0);
            }
            postInvalidateOnAnimation();
        }

        private void k(boolean z, int i, int i2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.q == i) {
                return;
            }
            View childAt = getChildAt(tabLayout.getSelectedTabPosition());
            View childAt2 = getChildAt(i);
            if (childAt2 == null) {
                g();
                return;
            }
            TabLayout.this.q = i;
            a aVar = new a(childAt, childAt2);
            if (!z) {
                this.q.removeAllUpdateListeners();
                this.q.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.q = valueAnimator;
            valueAnimator.setInterpolator(TabLayout.this.T0);
            valueAnimator.setDuration(i2);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.start();
        }

        void c(int i, int i2) {
            ValueAnimator valueAnimator = this.q;
            if (valueAnimator != null && valueAnimator.isRunning() && TabLayout.this.q != i) {
                this.q.cancel();
            }
            k(true, i, i2);
        }

        boolean d() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            int height;
            int iHeight = TabLayout.this.x0.getBounds().height();
            if (iHeight < 0) {
                iHeight = TabLayout.this.x0.getIntrinsicHeight();
            }
            int i = TabLayout.this.L0;
            if (i == 0) {
                height = getHeight() - iHeight;
                iHeight = getHeight();
            } else if (i != 1) {
                height = 0;
                if (i != 2) {
                    iHeight = i != 3 ? 0 : getHeight();
                }
            } else {
                height = (getHeight() - iHeight) / 2;
                iHeight = (getHeight() + iHeight) / 2;
            }
            if (TabLayout.this.x0.getBounds().width() > 0) {
                Rect bounds = TabLayout.this.x0.getBounds();
                TabLayout.this.x0.setBounds(bounds.left, height, bounds.right, iHeight);
                TabLayout.this.x0.draw(canvas);
            }
            super.draw(canvas);
        }

        void h(int i, float f) {
            TabLayout.this.q = Math.round(i + f);
            ValueAnimator valueAnimator = this.q;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.q.cancel();
            }
            j(getChildAt(i), getChildAt(i + 1), f);
        }

        void i(int i) {
            Rect bounds = TabLayout.this.x0.getBounds();
            TabLayout.this.x0.setBounds(bounds.left, 0, bounds.right, i);
            requestLayout();
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.q;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                e();
            } else {
                k(false, TabLayout.this.getSelectedTabPosition(), -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z = true;
            if (tabLayout.J0 == 1 || tabLayout.M0 == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) z.c(getContext(), 16)) * 2)) {
                    boolean z2 = false;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.J0 = 0;
                    tabLayout2.V(false);
                }
                if (z) {
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT >= 23 || this.k0 == i) {
                return;
            }
            requestLayout();
            this.k0 = i;
        }
    }

    public static class g {
        private Object a;
        private Drawable b;
        private CharSequence c;
        private CharSequence d;
        private View f;
        public TabLayout h;
        public i i;
        private int e = -1;
        private int g = 1;
        private int j = -1;

        public View e() {
            return this.f;
        }

        public Drawable f() {
            return this.b;
        }

        public int g() {
            return this.e;
        }

        public int h() {
            return this.g;
        }

        public Object i() {
            return this.a;
        }

        public CharSequence j() {
            return this.c;
        }

        public boolean k() {
            TabLayout tabLayout = this.h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            int selectedTabPosition = tabLayout.getSelectedTabPosition();
            return selectedTabPosition != -1 && selectedTabPosition == this.e;
        }

        void l() {
            this.h = null;
            this.i = null;
            this.a = null;
            this.b = null;
            this.j = -1;
            this.c = null;
            this.d = null;
            this.e = -1;
            this.f = null;
        }

        public void m() {
            TabLayout tabLayout = this.h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.L(this);
        }

        public g n(int i) {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                return o(tabLayout.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public g o(CharSequence charSequence) {
            this.d = charSequence;
            w();
            return this;
        }

        public g p(int i) {
            return q(LayoutInflater.from(this.i.getContext()).inflate(i, (ViewGroup) this.i, false));
        }

        public g q(View view) {
            this.f = view;
            w();
            return this;
        }

        public g r(Drawable drawable) {
            this.b = drawable;
            TabLayout tabLayout = this.h;
            if (tabLayout.J0 == 1 || tabLayout.M0 == 2) {
                tabLayout.V(true);
            }
            w();
            return this;
        }

        void s(int i) {
            this.e = i;
        }

        public g t(Object obj) {
            this.a = obj;
            return this;
        }

        public g u(int i) {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                return v(tabLayout.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public g v(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(charSequence)) {
                this.i.setContentDescription(charSequence);
            }
            this.c = charSequence;
            w();
            return this;
        }

        void w() {
            i iVar = this.i;
            if (iVar != null) {
                iVar.p();
            }
        }
    }

    public static class h implements ViewPager.j {
        private int X;
        private int Y;
        private final WeakReference<TabLayout> q;

        public h(TabLayout tabLayout) {
            this.q = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void Q(int i) {
            TabLayout tabLayout = this.q.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.Y;
            tabLayout.M(tabLayout.C(i), i2 == 0 || (i2 == 2 && this.X == 0));
        }

        void a() {
            this.Y = 0;
            this.X = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i) {
            this.X = this.Y;
            this.Y = i;
            TabLayout tabLayout = this.q.get();
            if (tabLayout != null) {
                tabLayout.W(this.Y);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void o(int i, float f, int i2) {
            TabLayout tabLayout = this.q.get();
            if (tabLayout != null) {
                int i3 = this.Y;
                boolean z = true;
                if (i3 == 2 && this.X != 1) {
                    z = false;
                }
                boolean z2 = true;
                if (i3 == 2 && this.X == 0) {
                    z2 = false;
                }
                tabLayout.Q(i, f, z, z2, false);
            }
        }
    }

    public final class i extends LinearLayout {
        private TextView k0;
        private ImageView l0;
        private View m0;
        private ax.U6.a n0;
        private View o0;
        private TextView p0;
        private g q;
        private ImageView q0;
        private Drawable r0;
        private int s0;

        class a implements View.OnLayoutChangeListener {
            final /* synthetic */ View a;

            a(View view) {
                this.a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (this.a.getVisibility() == 0) {
                    i.this.o(this.a);
                }
            }
        }

        public i(Context context) {
            super(context);
            this.s0 = 2;
            q(context);
            setPaddingRelative(TabLayout.this.n0, TabLayout.this.o0, TabLayout.this.p0, TabLayout.this.q0);
            setGravity(17);
            setOrientation(!TabLayout.this.N0 ? 1 : 0);
            setClickable(true);
            C1839d0.w0(this, M.b(getContext(), 1002));
        }

        private void d(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        private float e(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }

        private void f(boolean z) {
            setClipChildren(z);
            setClipToPadding(z);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z);
                viewGroup.setClipToPadding(z);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(Canvas canvas) {
            Drawable drawable = this.r0;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.r0.draw(canvas);
            }
        }

        private ax.U6.a getBadge() {
            return this.n0;
        }

        private ax.U6.a getOrCreateBadge() {
            if (this.n0 == null) {
                this.n0 = ax.U6.a.e(getContext());
            }
            n();
            ax.U6.a aVar = this.n0;
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private boolean h() {
            return this.n0 != null;
        }

        private void i() {
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(ax.R6.i.d, (ViewGroup) this, false);
            this.l0 = imageView;
            addView(imageView, 0);
        }

        private void j() {
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(ax.R6.i.e, (ViewGroup) this, false);
            this.k0 = textView;
            addView(textView);
        }

        private void l(View view) {
            if (h() && view != null) {
                f(false);
                ax.U6.d.a(this.n0, view, null);
                this.m0 = view;
            }
        }

        private void m() {
            if (h()) {
                f(true);
                View view = this.m0;
                if (view != null) {
                    ax.U6.d.b(this.n0, view);
                    this.m0 = null;
                }
            }
        }

        private void n() {
            g gVar;
            g gVar2;
            if (h()) {
                if (this.o0 != null) {
                    m();
                    return;
                }
                if (this.l0 != null && (gVar2 = this.q) != null && gVar2.f() != null) {
                    View view = this.m0;
                    ImageView imageView = this.l0;
                    if (view == imageView) {
                        o(imageView);
                        return;
                    } else {
                        m();
                        l(this.l0);
                        return;
                    }
                }
                if (this.k0 == null || (gVar = this.q) == null || gVar.h() != 1) {
                    m();
                    return;
                }
                View view2 = this.m0;
                TextView textView = this.k0;
                if (view2 == textView) {
                    o(textView);
                } else {
                    m();
                    l(this.k0);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void o(View view) {
            if (h() && view == this.m0) {
                ax.U6.d.c(this.n0, view, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void q(Context context) {
            GradientDrawable gradientDrawable;
            int i = TabLayout.this.D0;
            if (i != 0) {
                Drawable drawableB = ax.p226o.a.b(context, i);
                this.r0 = drawableB;
                if (drawableB != null && drawableB.isStateful()) {
                    this.r0.setState(getDrawableState());
                }
            } else {
                this.r0 = null;
            }
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setColor(0);
            Drawable rippleDrawable = gradientDrawable2;
            if (TabLayout.this.w0 != null) {
                GradientDrawable gradientDrawable3 = new GradientDrawable();
                gradientDrawable3.setCornerRadius(1.0E-5f);
                gradientDrawable3.setColor(-1);
                ColorStateList colorStateListA = ax.p298s7.a.a(TabLayout.this.w0);
                boolean z = TabLayout.this.R0;
                if (z) {
                    gradientDrawable = gradientDrawable2;
                    gradientDrawable = null;
                }
                rippleDrawable = new RippleDrawable(colorStateListA, gradientDrawable, z ? null : gradientDrawable3);
            }
            setBackground(rippleDrawable);
            TabLayout.this.invalidate();
        }

        /* JADX WARN: Code duplicated, block: B:27:0x0060  */
        private void t(TextView textView, ImageView imageView, boolean z) {
            boolean z2;
            g gVar = this.q;
            Drawable drawableMutate = (gVar == null || gVar.f() == null) ? null : ax.U.a.r(this.q.f()).mutate();
            if (drawableMutate != null) {
                drawableMutate.setTintList(TabLayout.this.v0);
                PorterDuff.Mode mode = TabLayout.this.z0;
                if (mode != null) {
                    drawableMutate.setTintMode(mode);
                }
            }
            g gVar2 = this.q;
            CharSequence charSequenceJ = gVar2 != null ? gVar2.j() : null;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean zIsEmpty = TextUtils.isEmpty(charSequenceJ);
            if (textView != null) {
                if (!zIsEmpty) {
                    z2 = this.q.g == 1;
                }
                textView.setText(!zIsEmpty ? charSequenceJ : null);
                textView.setVisibility(z2 ? 0 : 8);
                if (!zIsEmpty) {
                    setVisibility(0);
                }
            } else {
                z2 = false;
            }
            if (z && imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iC = (z2 && imageView.getVisibility() == 0) ? (int) z.c(getContext(), 8) : 0;
                if (TabLayout.this.N0) {
                    if (iC != marginLayoutParams.getMarginEnd()) {
                        marginLayoutParams.setMarginEnd(iC);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iC != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iC;
                    marginLayoutParams.setMarginEnd(0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.q;
            CharSequence charSequence = gVar3 != null ? gVar3.d : null;
            if (Build.VERSION.SDK_INT > 23) {
                if (zIsEmpty) {
                    charSequenceJ = charSequence;
                }
                B.a(this, charSequenceJ);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.r0;
            if ((drawable == null || !drawable.isStateful()) ? false : this.r0.setState(drawableState)) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        int getContentHeight() {
            View[] viewArr = {this.k0, this.l0, this.o0};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getTop()) : view.getTop();
                    iMax = z ? Math.max(iMax, view.getBottom()) : view.getBottom();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        int getContentWidth() {
            View[] viewArr = {this.k0, this.l0, this.o0};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z ? Math.max(iMax, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        public g getTab() {
            return this.q;
        }

        void k() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            H hQ0 = H.Q0(accessibilityNodeInfo);
            ax.U6.a aVar = this.n0;
            if (aVar != null && aVar.isVisible()) {
                hQ0.q0(this.n0.i());
            }
            hQ0.p0(H.f.a(0, 1, this.q.g(), 1, false, isSelected()));
            if (isSelected()) {
                hQ0.n0(false);
                hQ0.e0(H.a.i);
            }
            hQ0.E0(getResources().getString(k.h));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(TabLayout.this.E0, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.k0 != null) {
                float f = TabLayout.this.A0;
                if (isSelected() && TabLayout.this.t0 != -1) {
                    f = TabLayout.this.B0;
                }
                int i3 = this.s0;
                ImageView imageView = this.l0;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.k0;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = TabLayout.this.C0;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.k0.getTextSize();
                int lineCount = this.k0.getLineCount();
                int maxLines = this.k0.getMaxLines();
                if (f != textSize || (maxLines >= 0 && i3 != maxLines)) {
                    if (TabLayout.this.M0 != 1 || f <= textSize || lineCount != 1 || ((layout = this.k0.getLayout()) != null && e(layout, 0, f) <= (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        this.k0.setTextSize(0, f);
                        this.k0.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        final void p() {
            s();
            g gVar = this.q;
            setSelected(gVar != null && gVar.k());
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.q == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.q.m();
            return true;
        }

        final void r() {
            setOrientation(!TabLayout.this.N0 ? 1 : 0);
            TextView textView = this.p0;
            if (textView == null && this.q0 == null) {
                t(this.k0, this.l0, true);
            } else {
                t(textView, this.q0, false);
            }
        }

        final void s() {
            ViewParent parent;
            g gVar = this.q;
            View viewE = gVar != null ? gVar.e() : null;
            if (viewE != null) {
                ViewParent parent2 = viewE.getParent();
                if (parent2 != this) {
                    if (parent2 != null) {
                        ((ViewGroup) parent2).removeView(viewE);
                    }
                    View view = this.o0;
                    if (view != null && (parent = view.getParent()) != null) {
                        ((ViewGroup) parent).removeView(this.o0);
                    }
                    addView(viewE);
                }
                this.o0 = viewE;
                TextView textView = this.k0;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.l0;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.l0.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewE.findViewById(R.id.text1);
                this.p0 = textView2;
                if (textView2 != null) {
                    this.s0 = textView2.getMaxLines();
                }
                this.q0 = (ImageView) viewE.findViewById(R.id.icon);
            } else {
                View view2 = this.o0;
                if (view2 != null) {
                    removeView(view2);
                    this.o0 = null;
                }
                this.p0 = null;
                this.q0 = null;
            }
            if (this.o0 == null) {
                if (this.l0 == null) {
                    i();
                }
                if (this.k0 == null) {
                    j();
                    this.s0 = this.k0.getMaxLines();
                }
                ax.p115h0.j.m(this.k0, TabLayout.this.r0);
                if (!isSelected() || TabLayout.this.t0 == -1) {
                    ax.p115h0.j.m(this.k0, TabLayout.this.s0);
                } else {
                    ax.p115h0.j.m(this.k0, TabLayout.this.t0);
                }
                ColorStateList colorStateList = TabLayout.this.u0;
                if (colorStateList != null) {
                    this.k0.setTextColor(colorStateList);
                }
                t(this.k0, this.l0, true);
                n();
                d(this.l0);
                d(this.k0);
            } else {
                TextView textView3 = this.p0;
                if (textView3 != null || this.q0 != null) {
                    t(textView3, this.q0, false);
                }
            }
            if (gVar == null || TextUtils.isEmpty(gVar.d)) {
                return;
            }
            setContentDescription(gVar.d);
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            isSelected();
            super.setSelected(z);
            TextView textView = this.k0;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.l0;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.o0;
            if (view != null) {
                view.setSelected(z);
            }
        }

        void setTab(g gVar) {
            if (gVar != this.q) {
                this.q = gVar;
                p();
            }
        }
    }

    public static class j implements d {
        private final ViewPager a;

        public j(ViewPager viewPager) {
            this.a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
            this.a.setCurrentItem(gVar.g());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.R6.c.d0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = g1;
        super(ax.C7.a.d(context, attributeSet, i2, i3), attributeSet, i2);
        this.q = -1;
        this.k0 = new ArrayList<>();
        this.t0 = -1;
        this.y0 = 0;
        this.E0 = SubsamplingScaleImageView.TILE_SIZE_AUTO;
        this.P0 = -1;
        this.V0 = new ArrayList<>();
        this.f1 = new ax.p019b0.e(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.m0 = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = m.m5;
        int i4 = m.L5;
        TypedArray typedArrayI = w.i(context2, attributeSet, iArr, i2, i3, i4);
        ColorStateList colorStateListF = ax.p154j7.f.f(getBackground());
        if (colorStateListF != null) {
            ax.p346v7.h hVar = new ax.p346v7.h();
            hVar.g0(colorStateListF);
            hVar.U(context2);
            hVar.f0(getElevation());
            setBackground(hVar);
        }
        setSelectedTabIndicator(ax.p282r7.c.d(context2, typedArrayI, m.s5));
        setSelectedTabIndicatorColor(typedArrayI.getColor(m.v5, 0));
        fVar.i(typedArrayI.getDimensionPixelSize(m.y5, -1));
        setSelectedTabIndicatorGravity(typedArrayI.getInt(m.x5, 0));
        setTabIndicatorAnimationMode(typedArrayI.getInt(m.u5, 0));
        setTabIndicatorFullWidth(typedArrayI.getBoolean(m.w5, true));
        int dimensionPixelSize = typedArrayI.getDimensionPixelSize(m.D5, 0);
        this.q0 = dimensionPixelSize;
        this.p0 = dimensionPixelSize;
        this.o0 = dimensionPixelSize;
        this.n0 = dimensionPixelSize;
        this.n0 = typedArrayI.getDimensionPixelSize(m.G5, dimensionPixelSize);
        this.o0 = typedArrayI.getDimensionPixelSize(m.H5, this.o0);
        this.p0 = typedArrayI.getDimensionPixelSize(m.F5, this.p0);
        this.q0 = typedArrayI.getDimensionPixelSize(m.E5, this.q0);
        if (w.g(context2)) {
            this.r0 = ax.R6.c.g0;
        } else {
            this.r0 = ax.R6.c.e0;
        }
        int resourceId = typedArrayI.getResourceId(i4, l.d);
        this.s0 = resourceId;
        int[] iArr2 = ax.p194m.j.J2;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, iArr2);
        try {
            int i5 = ax.p194m.j.K2;
            this.A0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(i5, 0);
            int i6 = ax.p194m.j.N2;
            this.u0 = ax.p282r7.c.a(context2, typedArrayObtainStyledAttributes, i6);
            typedArrayObtainStyledAttributes.recycle();
            int i7 = m.J5;
            if (typedArrayI.hasValue(i7)) {
                this.t0 = typedArrayI.getResourceId(i7, resourceId);
            }
            int i8 = this.t0;
            if (i8 != -1) {
                TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(i8, iArr2);
                try {
                    this.B0 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(i5, (int) this.A0);
                    ColorStateList colorStateListA = ax.p282r7.c.a(context2, typedArrayObtainStyledAttributes2, i6);
                    if (colorStateListA != null) {
                        this.u0 = u(this.u0.getDefaultColor(), colorStateListA.getColorForState(new int[]{R.attr.state_selected}, colorStateListA.getDefaultColor()));
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes2.recycle();
                    throw th;
                }
            }
            int i9 = m.M5;
            if (typedArrayI.hasValue(i9)) {
                this.u0 = ax.p282r7.c.a(context2, typedArrayI, i9);
            }
            int i10 = m.K5;
            if (typedArrayI.hasValue(i10)) {
                this.u0 = u(this.u0.getDefaultColor(), typedArrayI.getColor(i10, 0));
            }
            this.v0 = ax.p282r7.c.a(context2, typedArrayI, m.q5);
            this.z0 = z.i(typedArrayI.getInt(m.r5, -1), null);
            this.w0 = ax.p282r7.c.a(context2, typedArrayI, m.I5);
            this.K0 = typedArrayI.getInt(m.t5, 300);
            this.T0 = ax.p234o7.i.g(context2, ax.R6.c.M, ax.S6.a.b);
            this.F0 = typedArrayI.getDimensionPixelSize(m.B5, -1);
            this.G0 = typedArrayI.getDimensionPixelSize(m.A5, -1);
            this.D0 = typedArrayI.getResourceId(m.n5, 0);
            this.I0 = typedArrayI.getDimensionPixelSize(m.o5, 0);
            this.M0 = typedArrayI.getInt(m.C5, 1);
            this.J0 = typedArrayI.getInt(m.p5, 0);
            this.N0 = typedArrayI.getBoolean(m.z5, false);
            this.R0 = typedArrayI.getBoolean(m.N5, false);
            typedArrayI.recycle();
            Resources resources = getResources();
            this.C0 = resources.getDimensionPixelSize(ax.R6.e.h);
            this.H0 = resources.getDimensionPixelSize(ax.R6.e.g);
            q();
        } catch (Throwable th2) {
            typedArrayObtainStyledAttributes.recycle();
            throw th2;
        }
    }

    private void A(g gVar) {
        for (int size = this.V0.size() - 1; size >= 0; size--) {
            this.V0.get(size).c(gVar);
        }
    }

    private void B() {
        if (this.X0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.X0 = valueAnimator;
            valueAnimator.setInterpolator(this.T0);
            this.X0.setDuration(this.K0);
            this.X0.addUpdateListener(new a());
        }
    }

    private boolean D() {
        return getTabMode() == 0 || getTabMode() == 2;
    }

    private void K(int i2) {
        i iVar = (i) this.m0.getChildAt(i2);
        this.m0.removeViewAt(i2);
        if (iVar != null) {
            iVar.k();
            this.f1.a(iVar);
        }
        requestLayout();
    }

    private void S(ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.Y0;
        if (viewPager2 != null) {
            h hVar = this.b1;
            if (hVar != null) {
                viewPager2.J(hVar);
            }
            b bVar = this.c1;
            if (bVar != null) {
                this.Y0.I(bVar);
            }
        }
        c cVar = this.W0;
        if (cVar != null) {
            J(cVar);
            this.W0 = null;
        }
        if (viewPager != null) {
            this.Y0 = viewPager;
            if (this.b1 == null) {
                this.b1 = new h(this);
            }
            this.b1.a();
            viewPager.c(this.b1);
            j jVar = new j(viewPager);
            this.W0 = jVar;
            g(jVar);
            androidx.viewpager.widget.a adapter = viewPager.getAdapter();
            if (adapter != null) {
                N(adapter, z);
            }
            if (this.c1 == null) {
                this.c1 = new b();
            }
            this.c1.b(z);
            viewPager.b(this.c1);
            O(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.Y0 = null;
            N(null, false);
        }
        this.d1 = z2;
    }

    private void T() {
        int size = this.k0.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.k0.get(i2).w();
        }
    }

    private void U(LinearLayout.LayoutParams layoutParams) {
        if (this.M0 == 1 && this.J0 == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    private int getDefaultHeight() {
        int size = this.k0.size();
        for (int i2 = 0; i2 < size; i2++) {
            g gVar = this.k0.get(i2);
            if (gVar != null && gVar.f() != null && !TextUtils.isEmpty(gVar.j())) {
                return !this.N0 ? 72 : 48;
            }
        }
        return 48;
    }

    private int getTabMinWidth() {
        int i2 = this.F0;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.M0;
        if (i3 == 0 || i3 == 2) {
            return this.H0;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.m0.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void l(ax.p410z7.a aVar) {
        g gVarF = F();
        CharSequence charSequence = aVar.q;
        if (charSequence != null) {
            gVarF.v(charSequence);
        }
        Drawable drawable = aVar.k0;
        if (drawable != null) {
            gVarF.r(drawable);
        }
        int i2 = aVar.l0;
        if (i2 != 0) {
            gVarF.p(i2);
        }
        if (!TextUtils.isEmpty(aVar.getContentDescription())) {
            gVarF.o(aVar.getContentDescription());
        }
        i(gVarF);
    }

    private void m(g gVar) {
        i iVar = gVar.i;
        iVar.setSelected(false);
        iVar.setActivated(false);
        this.m0.addView(iVar, gVar.g(), v());
    }

    private void n(View view) {
        if (!(view instanceof ax.p410z7.a)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        l((ax.p410z7.a) view);
    }

    private void o(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() == null || !isLaidOut() || this.m0.d()) {
            O(i2, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iR = r(i2, 0.0f);
        if (scrollX != iR) {
            B();
            this.X0.setIntValues(scrollX, iR);
            this.X0.start();
        }
        this.m0.c(i2, this.K0);
    }

    private void p(int i2) {
        if (i2 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i2 == 1) {
            this.m0.setGravity(1);
            return;
        } else if (i2 != 2) {
            return;
        }
        this.m0.setGravity(8388611);
    }

    private void q() {
        int i2 = this.M0;
        this.m0.setPaddingRelative((i2 == 0 || i2 == 2) ? Math.max(0, this.I0 - this.n0) : 0, 0, 0, 0);
        int i3 = this.M0;
        if (i3 == 0) {
            p(this.J0);
        } else if (i3 == 1 || i3 == 2) {
            if (this.J0 == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.m0.setGravity(1);
        }
        V(true);
    }

    private int r(int i2, float f2) {
        View childAt;
        int i3 = this.M0;
        if ((i3 != 0 && i3 != 2) || (childAt = this.m0.getChildAt(i2)) == null) {
            return 0;
        }
        int i4 = i2 + 1;
        View childAt2 = i4 < this.m0.getChildCount() ? this.m0.getChildAt(i4) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return getLayoutDirection() == 0 ? left + i5 : left - i5;
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.m0.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.m0.getChildAt(i3);
                if ((i3 != i2 || childAt.isSelected()) && (i3 == i2 || !childAt.isSelected())) {
                    childAt.setSelected(i3 == i2);
                    childAt.setActivated(i3 == i2);
                } else {
                    childAt.setSelected(i3 == i2);
                    childAt.setActivated(i3 == i2);
                    if (childAt instanceof i) {
                        ((i) childAt).s();
                    }
                }
                i3++;
            }
        }
    }

    private void t(g gVar, int i2) {
        gVar.s(i2);
        this.k0.add(i2, gVar);
        int size = this.k0.size();
        int i3 = -1;
        for (int i4 = i2 + 1; i4 < size; i4++) {
            if (this.k0.get(i4).g() == this.q) {
                i3 = i4;
            }
            this.k0.get(i4).s(i4);
        }
        this.q = i3;
    }

    private static ColorStateList u(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private LinearLayout.LayoutParams v() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        U(layoutParams);
        return layoutParams;
    }

    private i x(g gVar) {
        ax.p019b0.d<i> dVar = this.f1;
        i iVarB = dVar != null ? dVar.b() : null;
        if (iVarB == null) {
            iVarB = new i(getContext());
        }
        iVarB.setTab(gVar);
        iVarB.setFocusable(true);
        iVarB.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(gVar.d)) {
            iVarB.setContentDescription(gVar.c);
            return iVarB;
        }
        iVarB.setContentDescription(gVar.d);
        return iVarB;
    }

    private void y(g gVar) {
        for (int size = this.V0.size() - 1; size >= 0; size--) {
            this.V0.get(size).a(gVar);
        }
    }

    private void z(g gVar) {
        for (int size = this.V0.size() - 1; size >= 0; size--) {
            this.V0.get(size).b(gVar);
        }
    }

    public g C(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.k0.get(i2);
    }

    public boolean E() {
        return this.O0;
    }

    public g F() {
        g gVarW = w();
        gVarW.h = this;
        gVarW.i = x(gVarW);
        if (gVarW.j != -1) {
            gVarW.i.setId(gVarW.j);
        }
        return gVarW;
    }

    void G() {
        int currentItem;
        I();
        androidx.viewpager.widget.a aVar = this.Z0;
        if (aVar != null) {
            int iE = aVar.e();
            for (int i2 = 0; i2 < iE; i2++) {
                k(F().v(this.Z0.g(i2)), false);
            }
            ViewPager viewPager = this.Y0;
            if (viewPager == null || iE <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            L(C(currentItem));
        }
    }

    protected boolean H(g gVar) {
        return h1.a(gVar);
    }

    public void I() {
        for (int childCount = this.m0.getChildCount() - 1; childCount >= 0; childCount--) {
            K(childCount);
        }
        Iterator<g> it = this.k0.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.l();
            H(next);
        }
        this.l0 = null;
    }

    @Deprecated
    public void J(c cVar) {
        this.V0.remove(cVar);
    }

    public void L(g gVar) {
        M(gVar, true);
    }

    public void M(g gVar, boolean z) {
        g gVar2 = this.l0;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                y(gVar);
                o(gVar.g());
                return;
            }
            return;
        }
        int iG = gVar != null ? gVar.g() : -1;
        if (z) {
            if ((gVar2 == null || gVar2.g() == -1) && iG != -1) {
                O(iG, 0.0f, true);
            } else {
                o(iG);
            }
            if (iG != -1) {
                setSelectedTabView(iG);
            }
        }
        this.l0 = gVar;
        if (gVar2 != null && gVar2.h != null) {
            A(gVar2);
        }
        if (gVar != null) {
            z(gVar);
        }
    }

    void N(androidx.viewpager.widget.a aVar, boolean z) {
        DataSetObserver dataSetObserver;
        androidx.viewpager.widget.a aVar2 = this.Z0;
        if (aVar2 != null && (dataSetObserver = this.a1) != null) {
            aVar2.u(dataSetObserver);
        }
        this.Z0 = aVar;
        if (z && aVar != null) {
            if (this.a1 == null) {
                this.a1 = new e();
            }
            aVar.m(this.a1);
        }
        G();
    }

    public void O(int i2, float f2, boolean z) {
        P(i2, f2, z, true);
    }

    public void P(int i2, float f2, boolean z, boolean z2) {
        Q(i2, f2, z, z2, true);
    }

    void Q(int i2, float f2, boolean z, boolean z2, boolean z3) {
        int iRound = Math.round(i2 + f2);
        if (iRound < 0 || iRound >= this.m0.getChildCount()) {
            return;
        }
        if (z2) {
            this.m0.h(i2, f2);
        }
        ValueAnimator valueAnimator = this.X0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.X0.cancel();
        }
        int iR = r(i2, f2);
        int scrollX = getScrollX();
        boolean z4 = (i2 < getSelectedTabPosition() && iR >= scrollX) || (i2 > getSelectedTabPosition() && iR <= scrollX) || i2 == getSelectedTabPosition();
        if (getLayoutDirection() == 1) {
            z4 = (i2 < getSelectedTabPosition() && iR <= scrollX) || (i2 > getSelectedTabPosition() && iR >= scrollX) || i2 == getSelectedTabPosition();
        }
        if (z4 || this.e1 == 1 || z3) {
            if (i2 < 0) {
                iR = 0;
            }
            scrollTo(iR, 0);
        }
        if (z) {
            setSelectedTabView(iRound);
        }
    }

    public void R(ViewPager viewPager, boolean z) {
        S(viewPager, z, false);
    }

    void V(boolean z) {
        for (int i2 = 0; i2 < this.m0.getChildCount(); i2++) {
            View childAt = this.m0.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            U((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    void W(int i2) {
        this.e1 = i2;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        n(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        n(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        n(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        n(view);
    }

    @Deprecated
    public void g(c cVar) {
        if (this.V0.contains(cVar)) {
            return;
        }
        this.V0.add(cVar);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition() {
        g gVar = this.l0;
        if (gVar != null) {
            return gVar.g();
        }
        return -1;
    }

    public int getTabCount() {
        return this.k0.size();
    }

    public int getTabGravity() {
        return this.J0;
    }

    public ColorStateList getTabIconTint() {
        return this.v0;
    }

    public int getTabIndicatorAnimationMode() {
        return this.Q0;
    }

    public int getTabIndicatorGravity() {
        return this.L0;
    }

    int getTabMaxWidth() {
        return this.E0;
    }

    public int getTabMode() {
        return this.M0;
    }

    public ColorStateList getTabRippleColor() {
        return this.w0;
    }

    public Drawable getTabSelectedIndicator() {
        return this.x0;
    }

    public ColorStateList getTabTextColors() {
        return this.u0;
    }

    public void h(d dVar) {
        g(dVar);
    }

    public void i(g gVar) {
        k(gVar, this.k0.isEmpty());
    }

    public void j(g gVar, int i2, boolean z) {
        if (gVar.h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        t(gVar, i2);
        m(gVar);
        if (z) {
            gVar.m();
        }
    }

    public void k(g gVar, boolean z) {
        j(gVar, this.k0.size(), z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ax.p346v7.i.e(this);
        if (this.Y0 == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                S((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d1) {
            setupWithViewPager(null);
            this.d1 = false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i2 = 0; i2 < this.m0.getChildCount(); i2++) {
            View childAt = this.m0.getChildAt(i2);
            if (childAt instanceof i) {
                ((i) childAt).g(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        H.Q0(accessibilityNodeInfo).o0(H.e.a(1, getTabCount(), false, 1));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return D() && super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int iRound = Math.round(z.c(getContext(), getDefaultHeight()));
        int mode = View.MeasureSpec.getMode(i3);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                i3 = View.MeasureSpec.makeMeasureSpec(iRound + getPaddingTop() + getPaddingBottom(), 1073741824);
            }
        } else if (getChildCount() == 1 && View.MeasureSpec.getSize(i3) >= iRound) {
            getChildAt(0).setMinimumHeight(iRound);
        }
        int size = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i2) != 0) {
            int iC = this.G0;
            if (iC <= 0) {
                iC = (int) (size - z.c(getContext(), 56));
            }
            this.E0 = iC;
        }
        super.onMeasure(i2, i3);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            int i4 = this.M0;
            if (i4 == 0) {
                if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                    return;
                }
            } else if (i4 != 1) {
                if (i4 != 2) {
                    return;
                }
                if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                    return;
                }
            } else if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                return;
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || D()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void s() {
        this.V0.clear();
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        ax.p346v7.i.d(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.N0 != z) {
            this.N0 = z;
            for (int i2 = 0; i2 < this.m0.getChildCount(); i2++) {
                View childAt = this.m0.getChildAt(i2);
                if (childAt instanceof i) {
                    ((i) childAt).r();
                }
            }
            q();
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(c cVar) {
        c cVar2 = this.U0;
        if (cVar2 != null) {
            J(cVar2);
        }
        this.U0 = cVar;
        if (cVar != null) {
            g(cVar);
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        B();
        this.X0.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(ax.p226o.a.b(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable drawableMutate = ax.U.a.r(drawable).mutate();
        this.x0 = drawableMutate;
        ax.p154j7.f.k(drawableMutate, this.y0);
        int intrinsicHeight = this.P0;
        if (intrinsicHeight == -1) {
            intrinsicHeight = this.x0.getIntrinsicHeight();
        }
        this.m0.i(intrinsicHeight);
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.y0 = i2;
        ax.p154j7.f.k(this.x0, i2);
        V(false);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.L0 != i2) {
            this.L0 = i2;
            this.m0.postInvalidateOnAnimation();
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.P0 = i2;
        this.m0.i(i2);
    }

    public void setTabGravity(int i2) {
        if (this.J0 != i2) {
            this.J0 = i2;
            q();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.v0 != colorStateList) {
            this.v0 = colorStateList;
            T();
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(ax.p226o.a.a(getContext(), i2));
    }

    public void setTabIndicatorAnimationMode(int i2) {
        this.Q0 = i2;
        if (i2 == 0) {
            this.S0 = new com.google.android.material.tabs.c();
            return;
        }
        if (i2 == 1) {
            this.S0 = new com.google.android.material.tabs.a();
        } else {
            if (i2 == 2) {
                this.S0 = new com.google.android.material.tabs.b();
                return;
            }
            throw new IllegalArgumentException(i2 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.O0 = z;
        this.m0.g();
        this.m0.postInvalidateOnAnimation();
    }

    public void setTabMode(int i2) {
        if (i2 != this.M0) {
            this.M0 = i2;
            q();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.w0 != colorStateList) {
            this.w0 = colorStateList;
            for (int i2 = 0; i2 < this.m0.getChildCount(); i2++) {
                View childAt = this.m0.getChildAt(i2);
                if (childAt instanceof i) {
                    ((i) childAt).q(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(ax.p226o.a.a(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.u0 != colorStateList) {
            this.u0 = colorStateList;
            T();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(androidx.viewpager.widget.a aVar) {
        N(aVar, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.R0 != z) {
            this.R0 = z;
            for (int i2 = 0; i2 < this.m0.getChildCount(); i2++) {
                View childAt = this.m0.getChildAt(i2);
                if (childAt instanceof i) {
                    ((i) childAt).q(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        R(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    protected g w() {
        g gVarB = h1.b();
        return gVarB == null ? new g() : gVarB;
    }
}
