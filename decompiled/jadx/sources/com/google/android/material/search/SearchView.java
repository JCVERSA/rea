package com.google.android.material.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.R6.e;
import ax.R6.f;
import ax.R6.l;
import ax.p202m7.C2161b;
import ax.p202m7.x;
import ax.p234o7.g;
import ax.p242p.d;
import ax.p346v7.i;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.TouchObserverFrameLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class SearchView extends FrameLayout implements CoordinatorLayout.b, ax.p234o7.b {
    private static final int G0 = l.o;
    private boolean A0;
    private final int B0;
    private boolean C0;
    private boolean D0;
    private c E0;
    private Map<View, Integer> F0;
    final View k0;
    final View l0;
    final FrameLayout m0;
    final MaterialToolbar n0;
    final TextView o0;
    final EditText p0;
    final ClippableRoundedCornerLayout q;
    final TouchObserverFrameLayout q0;
    private final boolean r0;
    private final ax.p234o7.c s0;
    private final boolean t0;
    private final ax.p170k7.a u0;
    private final Set<b> v0;
    private SearchBar w0;
    private int x0;
    private boolean y0;
    private boolean z0;

    public static class Behavior extends CoordinatorLayout.c<SearchView> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, SearchView searchView, View view) {
            if (searchView.g() || !(view instanceof SearchBar)) {
                return false;
            }
            searchView.setupWithSearchBar((SearchBar) view);
            return false;
        }
    }

    static class a extends ax.p163k0.a {
        public static final Parcelable.Creator<a> CREATOR = new C0580a();
        String Y;
        int Z;

        /* JADX INFO: renamed from: com.google.android.material.search.SearchView$a$a, reason: collision with other inner class name */
        class C0580a implements Parcelable.ClassLoaderCreator<a> {
            C0580a() {
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
            this.Z = parcel.readInt();
        }

        public a(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.Y);
            parcel.writeInt(this.Z);
        }
    }

    public interface b {
        void a(SearchView searchView, c cVar, c cVar2);
    }

    public enum c {
        HIDING,
        HIDDEN,
        SHOWING,
        SHOWN
    }

    private boolean f() {
        return this.E0.equals(c.HIDDEN) || this.E0.equals(c.HIDING);
    }

    private Window getActivityWindow() {
        Activity activityA = C2161b.a(getContext());
        if (activityA == null) {
            return null;
        }
        return activityA.getWindow();
    }

    private float getOverlayElevation() {
        SearchBar searchBar = this.w0;
        return searchBar != null ? searchBar.getCompatElevation() : getResources().getDimension(e.A);
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void h(c cVar, boolean z) {
        if (this.E0.equals(cVar)) {
            return;
        }
        if (z) {
            k(cVar);
        }
        c cVar2 = this.E0;
        this.E0 = cVar;
        Iterator it = new LinkedHashSet(this.v0).iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(this, cVar2, cVar);
        }
        j(cVar);
        SearchBar searchBar = this.w0;
        if (searchBar == null || cVar != c.HIDDEN) {
            return;
        }
        searchBar.sendAccessibilityEvent(8);
    }

    @SuppressLint({"InlinedApi"})
    private void i(ViewGroup viewGroup, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != this) {
                if (childAt.findViewById(this.q.getId()) != null) {
                    i((ViewGroup) childAt, z);
                } else if (z) {
                    this.F0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    childAt.setImportantForAccessibility(4);
                } else {
                    Map<View, Integer> map = this.F0;
                    if (map != null && map.containsKey(childAt)) {
                        childAt.setImportantForAccessibility(this.F0.get(childAt).intValue());
                    }
                }
            }
        }
    }

    private void j(c cVar) {
        if (this.w0 == null || !this.t0) {
            return;
        }
        if (cVar.equals(c.SHOWN)) {
            this.s0.b();
        } else if (cVar.equals(c.HIDDEN)) {
            this.s0.d();
        }
    }

    private void k(c cVar) {
        if (cVar == c.SHOWN) {
            setModalForAccessibility(true);
        } else if (cVar == c.HIDDEN) {
            setModalForAccessibility(false);
        }
    }

    private void l() {
        ImageButton imageButtonD = x.d(this.n0);
        if (imageButtonD == null) {
            return;
        }
        int i = this.q.getVisibility() == 0 ? 1 : 0;
        Drawable drawableQ = ax.U.a.q(imageButtonD.getDrawable());
        if (drawableQ instanceof d) {
            ((d) drawableQ).e(i);
        }
        if (drawableQ instanceof ax.p202m7.e) {
            ((ax.p202m7.e) drawableQ).a(i);
        }
    }

    private void setStatusBarSpacerEnabledInternal(boolean z) {
        this.l0.setVisibility(z ? 0 : 8);
    }

    private void setUpBackgroundViewElevationOverlay(float f) {
        ax.p170k7.a aVar = this.u0;
        if (aVar == null || this.k0 == null) {
            return;
        }
        this.k0.setBackgroundColor(aVar.c(this.B0, f));
    }

    private void setUpHeaderLayout(int i) {
        if (i != -1) {
            e(LayoutInflater.from(getContext()).inflate(i, (ViewGroup) this.m0, false));
        }
    }

    private void setUpStatusBarSpacer(int i) {
        if (this.l0.getLayoutParams().height != i) {
            this.l0.getLayoutParams().height = i;
            this.l0.requestLayout();
        }
    }

    @Override // ax.p234o7.b
    public void a(ax.p130i.b bVar) {
        SearchBar searchBar;
        if (f() || (searchBar = this.w0) == null) {
            return;
        }
        if (searchBar != null) {
            searchBar.setPlaceholderText(this.p0.getText().toString());
        }
        throw null;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.r0) {
            this.q0.addView(view, i, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
    }

    @Override // ax.p234o7.b
    public void b(ax.p130i.b bVar) {
        if (!f() && this.w0 != null && Build.VERSION.SDK_INT >= 34) {
            throw null;
        }
    }

    @Override // ax.p234o7.b
    public void c() {
        if (!f()) {
            throw null;
        }
    }

    @Override // ax.p234o7.b
    public void d() {
        if (!f() && this.w0 != null && Build.VERSION.SDK_INT >= 34) {
            throw null;
        }
    }

    public void e(View view) {
        this.m0.addView(view);
        this.m0.setVisibility(0);
    }

    public boolean g() {
        return this.w0 != null;
    }

    g getBackHelper() {
        throw null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<SearchView> getBehavior() {
        return new Behavior();
    }

    public c getCurrentTransitionState() {
        return this.E0;
    }

    protected int getDefaultNavigationIconResource() {
        return f.b;
    }

    public EditText getEditText() {
        return this.p0;
    }

    public CharSequence getHint() {
        return this.p0.getHint();
    }

    public TextView getSearchPrefix() {
        return this.o0;
    }

    public CharSequence getSearchPrefixText() {
        return this.o0.getText();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getSoftInputMode() {
        return this.x0;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public Editable getText() {
        return this.p0.getText();
    }

    public Toolbar getToolbar() {
        return this.n0;
    }

    public void m() {
        Window activityWindow = getActivityWindow();
        if (activityWindow != null) {
            this.x0 = activityWindow.getAttributes().softInputMode;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
        c currentTransitionState = getCurrentTransitionState();
        k(currentTransitionState);
        j(currentTransitionState);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setModalForAccessibility(false);
        this.s0.d();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        m();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.a());
        setText(aVar.Y);
        setVisible(aVar.Z == 0);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        Editable text = getText();
        aVar.Y = text == null ? null : text.toString();
        aVar.Z = this.q.getVisibility();
        return aVar;
    }

    public void setAnimatedNavigationIcon(boolean z) {
        this.y0 = z;
    }

    public void setAutoShowKeyboard(boolean z) {
        this.A0 = z;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        setUpBackgroundViewElevationOverlay(f);
    }

    public void setHint(int i) {
        this.p0.setHint(i);
    }

    public void setHint(CharSequence charSequence) {
        this.p0.setHint(charSequence);
    }

    public void setMenuItemsAnimated(boolean z) {
        this.z0 = z;
    }

    public void setModalForAccessibility(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        if (z) {
            this.F0 = new HashMap(viewGroup.getChildCount());
        }
        i(viewGroup, z);
        if (z) {
            return;
        }
        this.F0 = null;
    }

    public void setOnMenuItemClickListener(Toolbar.h hVar) {
        this.n0.setOnMenuItemClickListener(hVar);
    }

    public void setSearchPrefixText(CharSequence charSequence) {
        this.o0.setText(charSequence);
        this.o0.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public void setStatusBarSpacerEnabled(boolean z) {
        this.D0 = true;
        setStatusBarSpacerEnabledInternal(z);
    }

    public void setText(int i) {
        this.p0.setText(i);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setText(CharSequence charSequence) {
        this.p0.setText(charSequence);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z) {
        this.n0.setTouchscreenBlocksFocus(z);
    }

    void setTransitionState(c cVar) {
        h(cVar, true);
    }

    public void setUseWindowInsetsController(boolean z) {
        this.C0 = z;
    }

    public void setVisible(boolean z) {
        boolean z2 = this.q.getVisibility() == 0;
        this.q.setVisibility(z ? 0 : 8);
        l();
        h(z ? c.SHOWN : c.HIDDEN, z2 != z);
    }

    public void setupWithSearchBar(SearchBar searchBar) {
        this.w0 = searchBar;
        throw null;
    }
}
