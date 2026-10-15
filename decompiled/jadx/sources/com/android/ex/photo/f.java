package com.android.ex.photo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class f implements ax.J0.a.InterfaceC0116a<Cursor>, ViewPager.j, PhotoViewPager.c, com.android.ex.photo.a.InterfaceC0561a, com.android.ex.photo.e {
    public static int T0;
    public static int U0;
    private boolean A0;
    protected float C0;
    protected String D0;
    protected String E0;
    private boolean F0;
    protected boolean G0;
    protected int H0;
    protected int I0;
    protected int J0;
    protected int K0;
    protected boolean L0;
    protected boolean M0;
    private final AccessibilityManager N0;
    protected h O0;
    private long Q0;
    protected boolean R0;
    private int X;
    private String Z;
    private String k0;
    private int m0;
    private String n0;
    private String[] o0;
    private final g q;
    protected boolean q0;
    protected View r0;
    protected View s0;
    protected PhotoViewPager t0;
    protected ImageView u0;
    protected ax.E3.c v0;
    protected boolean w0;
    private boolean z0;
    private int l0 = -1;
    protected int p0 = -1;
    private final Map<Integer, com.android.ex.photo.e.b> x0 = new HashMap();
    private final Set<com.android.ex.photo.e.a> y0 = new HashSet();
    protected boolean B0 = true;
    protected final Handler P0 = new Handler();
    private final Runnable S0 = new b();
    private final View.OnSystemUiVisibilityChangeListener Y = new a();

    class a implements View.OnSystemUiVisibilityChangeListener {
        a() {
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int i) {
            if (i != 0 || f.this.X != 3846 || f.this.N() == null || f.this.N().q()) {
                return;
            }
            f.this.y0(false, true);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.y0(true, true);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.i0();
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.j0();
        }
    }

    class e implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ View q;

        e(View view) {
            this.q = view;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.q.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            f.this.v0();
        }
    }

    /* JADX INFO: renamed from: com.android.ex.photo.f$f, reason: collision with other inner class name */
    static /* synthetic */ class C0562f {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.I3.b.c.values().length];
            a = iArr;
            try {
                iArr[ax.I3.b.c.EXTRA_SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.I3.b.c.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.I3.b.c.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public interface g {
        boolean c();

        void d(int i);

        void e();

        void f();

        View findViewById(int i);

        void finish();

        f g();

        Context getApplicationContext();

        Context getContext();

        Intent getIntent();

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        ax.J0.a getSupportLoaderManager();

        Fragment j();

        com.android.ex.photo.a l();

        void n();

        void overridePendingTransition(int i, int i2);

        void p(boolean z);

        boolean q();

        void setContentView(int i);
    }

    private class h implements ax.J0.a.InterfaceC0116a<ax.G3.b.a> {
        private h() {
        }

        /* synthetic */ h(f fVar, a aVar) {
            this();
        }

        @Override // ax.J0.a.InterfaceC0116a
        public ax.K0.c<ax.G3.b.a> E(int i, Bundle bundle) {
            String string = bundle.getString("image_uri");
            if (i == 1) {
                return f.this.x(1, bundle, string);
            }
            if (i != 2) {
                return null;
            }
            return f.this.x(2, bundle, string);
        }

        @Override // ax.J0.a.InterfaceC0116a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void D(ax.K0.c<ax.G3.b.a> cVar, ax.G3.b.a aVar) {
            Drawable drawableA = aVar.a(f.this.q.getResources());
            com.android.ex.photo.a aVarL = f.this.q.l();
            int iK = cVar.k();
            if (iK != 1) {
                if (iK != 2) {
                    return;
                }
                f.this.X(drawableA);
            } else if (drawableA == null) {
                aVarL.d(null);
            } else {
                aVarL.d(drawableA);
            }
        }

        @Override // ax.J0.a.InterfaceC0116a
        public void y(ax.K0.c<ax.G3.b.a> cVar) {
        }
    }

    public f(g gVar) {
        this.q = gVar;
        this.N0 = (AccessibilityManager) gVar.getContext().getSystemService("accessibility");
    }

    private int J(int i, int i2, int i3, float f) {
        float f2 = i3;
        float f3 = f * f2;
        return (i - Math.round((f2 - f3) / 2.0f)) - Math.round((f3 - i2) / 2.0f);
    }

    private static final String R(String str) {
        return str == null ? HttpUrl.FRAGMENT_ENCODE_SET : str;
    }

    private void W() {
        if (U0 == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) this.q.getContext().getSystemService("window");
            ax.I3.b.c cVar = ax.I3.b.b;
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            if (C0562f.a[cVar.ordinal()] != 1) {
                U0 = Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels);
            } else {
                U0 = (Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels) * 800) / 1000;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X(Drawable drawable) {
        if (this.F0) {
            return;
        }
        this.u0.setImageDrawable(drawable);
        if (drawable != null) {
            if (this.r0.getMeasuredWidth() == 0) {
                View view = this.r0;
                view.getViewTreeObserver().addOnGlobalLayoutListener(new e(view));
            } else {
                v0();
            }
        }
        this.q.getSupportLoaderManager().e(100, null, this);
    }

    private synchronized void c0(Cursor cursor) {
        Iterator<com.android.ex.photo.e.a> it = this.y0.iterator();
        while (it.hasNext()) {
            it.next().N(cursor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j0() {
        this.q.finish();
        this.q.overridePendingTransition(0, 0);
    }

    private void s0() {
        this.P0.postDelayed(this.S0, this.Q0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v0() {
        int measuredWidth = this.r0.getMeasuredWidth();
        int measuredHeight = this.r0.getMeasuredHeight();
        this.u0.setVisibility(0);
        float fMax = Math.max(this.J0 / measuredWidth, this.K0 / measuredHeight);
        int iJ = J(this.H0, this.J0, measuredWidth, fMax);
        int iJ2 = J(this.I0, this.K0, measuredHeight, fMax);
        this.s0.setAlpha(0.0f);
        this.s0.animate().alpha(1.0f).setDuration(250L).start();
        this.s0.setVisibility(0);
        this.u0.setScaleX(fMax);
        this.u0.setScaleY(fMax);
        this.u0.setTranslationX(iJ);
        this.u0.setTranslationY(iJ2);
        c cVar = new c();
        ViewPropertyAnimator duration = this.u0.animate().scaleX(1.0f).scaleY(1.0f).translationX(0.0f).translationY(0.0f).setDuration(250L);
        duration.withEndAction(cVar);
        duration.start();
    }

    private void w0() {
        this.q.getIntent();
        int measuredWidth = this.r0.getMeasuredWidth();
        int measuredHeight = this.r0.getMeasuredHeight();
        float fMax = Math.max(this.J0 / measuredWidth, this.K0 / measuredHeight);
        int iJ = J(this.H0, this.J0, measuredWidth, fMax);
        int iJ2 = J(this.I0, this.K0, measuredHeight, fMax);
        this.s0.animate().alpha(0.0f).setDuration(250L).start();
        this.s0.setVisibility(0);
        d dVar = new d();
        ViewPropertyAnimator duration = this.u0.getVisibility() == 0 ? this.u0.animate().scaleX(fMax).scaleY(fMax).translationX(iJ).translationY(iJ2).setDuration(250L) : this.t0.animate().scaleX(fMax).scaleY(fMax).translationX(iJ).translationY(iJ2).setDuration(250L);
        if (!this.k0.equals(this.n0)) {
            duration.alpha(0.0f);
        }
        duration.withEndAction(dVar);
        duration.start();
    }

    @Override // com.android.ex.photo.e
    public void A(int i) {
        this.x0.remove(Integer.valueOf(i));
    }

    public void A0(int i) {
        this.l0 = i;
    }

    @Override // com.android.ex.photo.e
    public void B(int i, com.android.ex.photo.e.b bVar) {
        this.x0.put(Integer.valueOf(i), bVar);
    }

    protected void B0(boolean z) {
        z0(z);
    }

    public void C0(int i) {
        String strS;
        com.android.ex.photo.e.b bVar = this.x0.get(Integer.valueOf(i));
        if (bVar != null) {
            bVar.T();
        }
        Cursor cursorP = P();
        this.m0 = i;
        this.n0 = cursorP.getString(cursorP.getColumnIndex("uri"));
        F0();
        if (this.N0.isEnabled() && (strS = S(i)) != null) {
            ax.I3.e.a(this.r0, this.N0, strS);
        }
        K();
        s0();
    }

    public void D0(Cursor cursor) {
        this.p0 = cursor.getCount();
        if (this.n0 != null) {
            int columnIndex = cursor.getColumnIndex("uri");
            Uri uriBuild = Uri.parse(this.n0).buildUpon().clearQuery().build();
            cursor.moveToPosition(-1);
            int i = 0;
            while (cursor.moveToNext()) {
                Uri uriBuild2 = Uri.parse(cursor.getString(columnIndex)).buildUpon().clearQuery().build();
                if (uriBuild != null && uriBuild.equals(uriBuild2)) {
                    this.m0 = i;
                    break;
                }
                i++;
            }
        }
        if (this.B0) {
            this.z0 = true;
            this.v0.C(null);
            return;
        }
        boolean z = this.q0;
        this.q0 = false;
        this.v0.C(cursor);
        if (this.t0.getAdapter() == null) {
            this.t0.setAdapter(this.v0);
        }
        c0(cursor);
        if (this.m0 < 0) {
            this.m0 = 0;
        }
        int i2 = this.l0;
        if (i2 >= 0) {
            this.t0.N(i2, false);
            this.l0 = -1;
        } else {
            this.t0.N(this.m0, false);
        }
        if (z) {
            C0(this.m0);
        }
    }

    @Override // ax.J0.a.InterfaceC0116a
    public ax.K0.c<Cursor> E(int i, Bundle bundle) {
        if (i == 100) {
            return new ax.G3.c(this.q.getContext(), Uri.parse(this.Z), this.o0);
        }
        return null;
    }

    public void E0(boolean z) {
        throw null;
    }

    public void F0() {
        throw null;
    }

    public void G0() {
    }

    public void K() {
        this.P0.removeCallbacks(this.S0);
    }

    public ax.E3.c L(Context context, FragmentManager fragmentManager, Cursor cursor, float f) {
        return new ax.E3.c(context, fragmentManager, cursor, f, this.M0);
    }

    protected View M(int i) {
        return this.q.findViewById(i);
    }

    public g N() {
        return this.q;
    }

    public com.android.ex.photo.e.b O() {
        return this.x0.get(Integer.valueOf(this.m0));
    }

    public Cursor P() {
        PhotoViewPager photoViewPager = this.t0;
        if (photoViewPager == null) {
            return null;
        }
        int currentItem = photoViewPager.getCurrentItem();
        Cursor cursorX = this.v0.x();
        if (cursorX == null) {
            return null;
        }
        cursorX.moveToPosition(currentItem);
        return cursorX;
    }

    public void Q(int i) {
        this.m0 = i;
        C0(i);
    }

    protected String S(int i) {
        return this.E0 != null ? this.q.getContext().getResources().getString(m.b, this.D0, this.E0) : this.D0;
    }

    public View T() {
        return this.r0;
    }

    public View.OnSystemUiVisibilityChangeListener U() {
        return this.Y;
    }

    public PhotoViewPager V() {
        return this.t0;
    }

    protected boolean Y() {
        return this.A0;
    }

    public boolean Z() {
        return this.F0;
    }

    @Override // com.android.ex.photo.a.InterfaceC0561a
    public void a(boolean z) {
        if (z) {
            K();
        } else {
            s0();
        }
    }

    public boolean a0() {
        return this.G0;
    }

    public void b(boolean z) {
        this.R0 = z;
        com.android.ex.photo.e.b bVarO = O();
        if (bVarO != null) {
            bVarO.b(z);
        }
    }

    public boolean b0() {
        return (this.w0 && !this.L0) || this.G0;
    }

    @Override // com.android.ex.photo.e
    public boolean c() {
        g gVar = this.q;
        if (gVar == null) {
            return false;
        }
        return gVar.c();
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void d(int i) {
        g gVar = this.q;
        if (gVar == null) {
            return;
        }
        gVar.d(i);
    }

    public void d0(int i, int i2, Intent intent) {
        throw null;
    }

    @Override // com.android.ex.photo.e
    public void e() {
        g gVar = this.q;
        if (gVar == null) {
            return;
        }
        gVar.e();
    }

    public boolean e0() {
        if (this.w0 && !this.L0) {
            n();
            return true;
        }
        if (!this.G0) {
            return false;
        }
        w0();
        return true;
    }

    public void f0(Bundle bundle) {
        W();
        T0 = ((ActivityManager) this.q.getApplicationContext().getSystemService("activity")).getMemoryClass();
        Intent intent = this.q.getIntent();
        if (intent.hasExtra("photos_uri")) {
            this.Z = intent.getStringExtra("photos_uri");
        }
        if (intent.getBooleanExtra("scale_up_animation", false)) {
            this.G0 = true;
            this.H0 = intent.getIntExtra("start_x_extra", 0);
            this.I0 = intent.getIntExtra("start_y_extra", 0);
            this.J0 = intent.getIntExtra("start_width_extra", 0);
            this.K0 = intent.getIntExtra("start_height_extra", 0);
        }
        this.L0 = intent.getBooleanExtra("action_bar_hidden_initially", false) && !ax.I3.e.c(this.N0);
        this.M0 = intent.getBooleanExtra("display_thumbs_fullscreen", false);
        a aVar = null;
        if (intent.hasExtra("projection")) {
            this.o0 = intent.getStringArrayExtra("projection");
        } else {
            this.o0 = null;
        }
        this.C0 = intent.getFloatExtra("max_scale", 1.0f);
        this.n0 = null;
        this.m0 = -1;
        if (intent.hasExtra("photo_index")) {
            this.m0 = intent.getIntExtra("photo_index", -1);
        }
        if (intent.hasExtra("initial_photo_uri")) {
            String stringExtra = intent.getStringExtra("initial_photo_uri");
            this.k0 = stringExtra;
            this.n0 = stringExtra;
        }
        this.q0 = true;
        if (bundle != null) {
            this.k0 = bundle.getString("com.android.ex.PhotoViewFragment.INITIAL_URI");
            this.n0 = bundle.getString("com.android.ex.PhotoViewFragment.CURRENT_URI");
            this.m0 = bundle.getInt("com.android.ex.PhotoViewFragment.CURRENT_INDEX");
            this.w0 = bundle.getBoolean("com.android.ex.PhotoViewFragment.FULLSCREEN", false) && !ax.I3.e.c(this.N0);
            this.D0 = bundle.getString("com.android.ex.PhotoViewFragment.ACTIONBARTITLE");
            this.E0 = bundle.getString("com.android.ex.PhotoViewFragment.ACTIONBARSUBTITLE");
            this.F0 = bundle.getBoolean("com.android.ex.PhotoViewFragment.SCALEANIMATIONFINISHED", false);
        } else {
            this.w0 = this.L0;
        }
        this.q.setContentView(l.a);
        this.v0 = L(this.q.getContext(), this.q.getSupportFragmentManager(), null, this.C0);
        Resources resources = this.q.getResources();
        View viewM = M(j.g);
        this.r0 = viewM;
        viewM.setOnSystemUiVisibilityChangeListener(U());
        this.s0 = M(j.f);
        this.u0 = (ImageView) M(j.h);
        PhotoViewPager photoViewPager = (PhotoViewPager) M(j.l);
        this.t0 = photoViewPager;
        photoViewPager.setAdapter(this.v0);
        this.t0.setOnPageChangeListener(this);
        this.t0.setOnInterceptTouchListener(this);
        this.t0.setPageMargin(resources.getDimensionPixelSize(i.c));
        this.O0 = new h(this, aVar);
        if (!this.G0 || this.F0) {
            this.q.getSupportLoaderManager().e(100, null, this);
            this.s0.setVisibility(0);
        } else {
            this.t0.setVisibility(8);
            Bundle bundle2 = new Bundle();
            bundle2.putString("image_uri", this.k0);
            this.q.getSupportLoaderManager().e(2, bundle2, this.O0);
        }
        this.Q0 = resources.getInteger(k.a);
        com.android.ex.photo.a aVarL = this.q.l();
        if (aVarL != null) {
            aVarL.b(true);
            aVarL.c(this);
            aVarL.a();
            x0(aVarL);
        }
        if (this.G0) {
            B0(false);
        } else {
            B0(this.w0);
        }
        this.q.n();
    }

    @Override // com.android.ex.photo.e
    public com.android.ex.photo.e.b g(int i) {
        return this.x0.get(Integer.valueOf(i));
    }

    public boolean g0(Menu menu) {
        return true;
    }

    @Override // com.android.ex.photo.e
    public void h(ax.F3.a aVar, Cursor cursor) {
    }

    public void h0() {
        this.A0 = true;
    }

    @Override // com.android.ex.photo.e
    public void i(boolean z) {
        if (z) {
            return;
        }
        this.R0 = z;
        ArrayList arrayList = new ArrayList(this.x0.values());
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((com.android.ex.photo.e.b) obj).b(false);
        }
    }

    public void i0() {
        this.F0 = true;
        this.t0.setVisibility(0);
        B0(this.w0);
    }

    public void j(ax.F3.a aVar, boolean z) {
        if (this.u0.getVisibility() == 8 || !TextUtils.equals(aVar.i3(), this.n0)) {
            return;
        }
        if (z) {
            this.u0.setVisibility(8);
            this.t0.setVisibility(0);
        } else {
            Log.w("PhotoViewController", "Failed to load fragment image");
            this.u0.setVisibility(8);
            this.t0.setVisibility(0);
        }
        this.q.getSupportLoaderManager().a(2);
    }

    public void k0(ax.K0.c<Cursor> cVar, Cursor cursor) {
        if (cVar.k() == 100) {
            if (cursor == null || cursor.getCount() == 0) {
                this.q0 = true;
                this.v0.C(null);
            } else {
                D0(cursor);
            }
            G0();
        }
    }

    @Override // com.android.ex.photo.e
    public ax.E3.c l() {
        return this.v0;
    }

    public boolean l0(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        this.q.finish();
        return true;
    }

    public void m(ax.F3.a aVar) {
    }

    public void m0() {
        this.B0 = true;
    }

    @Override // com.android.ex.photo.e
    public void n() {
        y0(!this.w0, true);
    }

    public boolean n0(Menu menu) {
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void o(int i, float f, int i2) {
        if (f < 1.0E-4d) {
            com.android.ex.photo.e.b bVar = this.x0.get(Integer.valueOf(i - 1));
            if (bVar != null) {
                bVar.X();
            }
            com.android.ex.photo.e.b bVar2 = this.x0.get(Integer.valueOf(i + 1));
            if (bVar2 != null) {
                bVar2.X();
            }
        }
    }

    public void o0() {
        y0(this.w0, false);
        this.B0 = false;
        if (this.z0) {
            this.z0 = false;
            this.q.getSupportLoaderManager().e(100, null, this);
        }
    }

    @Override // com.android.ex.photo.e
    public void p(int i) {
    }

    public void p0(Bundle bundle) {
        bundle.putString("com.android.ex.PhotoViewFragment.INITIAL_URI", this.k0);
        bundle.putString("com.android.ex.PhotoViewFragment.CURRENT_URI", this.n0);
        bundle.putInt("com.android.ex.PhotoViewFragment.CURRENT_INDEX", this.m0);
        bundle.putBoolean("com.android.ex.PhotoViewFragment.FULLSCREEN", this.w0);
        bundle.putString("com.android.ex.PhotoViewFragment.ACTIONBARTITLE", this.D0);
        bundle.putString("com.android.ex.PhotoViewFragment.ACTIONBARSUBTITLE", this.E0);
        bundle.putBoolean("com.android.ex.PhotoViewFragment.SCALEANIMATIONFINISHED", this.F0);
    }

    @Override // com.android.ex.photo.e
    public synchronized void q(com.android.ex.photo.e.a aVar) {
        this.y0.remove(aVar);
    }

    public void q0() {
    }

    public void r0() {
    }

    @Override // com.android.ex.photo.e
    public boolean s() {
        return this.w0;
    }

    @Override // com.android.ex.photo.e
    public boolean t(Fragment fragment) {
        ax.E3.c cVar;
        if (this.t0 == null || (cVar = this.v0) == null || cVar.e() == 0) {
            return this.w0;
        }
        return this.w0 || this.t0.getCurrentItem() != this.v0.f(fragment);
    }

    public void t0() {
        if (this.w0) {
            return;
        }
        u0();
    }

    @Override // com.android.ex.photo.PhotoViewPager.c
    public PhotoViewPager.b u(float f, float f2) {
        boolean zR = false;
        boolean zA = false;
        for (com.android.ex.photo.e.b bVar : this.x0.values()) {
            if (!zR) {
                zR = bVar.r(f, f2);
            }
            if (!zA) {
                zA = bVar.A(f, f2);
            }
        }
        if (zR) {
            return zA ? PhotoViewPager.b.BOTH : PhotoViewPager.b.LEFT;
        }
        return zA ? PhotoViewPager.b.RIGHT : PhotoViewPager.b.NONE;
    }

    public void u0() {
        K();
        s0();
    }

    @Override // com.android.ex.photo.e
    public synchronized void v(com.android.ex.photo.e.a aVar) {
        this.y0.add(aVar);
    }

    @Override // com.android.ex.photo.e
    public boolean w(Fragment fragment) {
        PhotoViewPager photoViewPager = this.t0;
        return (photoViewPager == null || this.v0 == null || photoViewPager.getCurrentItem() != this.v0.f(fragment)) ? false : true;
    }

    public ax.K0.c<ax.G3.b.a> x(int i, Bundle bundle, String str) {
        if (i == 1 || i == 2 || i == 3) {
            return new ax.G3.a(this.q.getContext(), null, str);
        }
        return null;
    }

    protected final void x0(com.android.ex.photo.a aVar) {
        if (aVar == null) {
            return;
        }
        aVar.setTitle(R(this.D0));
        aVar.f(R(this.E0));
    }

    @Override // ax.J0.a.InterfaceC0116a
    public void y(ax.K0.c<Cursor> cVar) {
        if (Y()) {
            return;
        }
        this.v0.C(null);
    }

    protected void y0(boolean z, boolean z2) {
        if (ax.I3.e.c(this.N0)) {
            z = false;
            z2 = false;
        }
        boolean z3 = z != this.w0;
        this.w0 = z;
        if (z) {
            B0(true);
            K();
        } else {
            B0(false);
            if (z2) {
                if (z3) {
                    K();
                }
                s0();
            }
        }
        if (z3) {
            Iterator<com.android.ex.photo.e.b> it = this.x0.values().iterator();
            while (it.hasNext()) {
                it.next().O(this.w0);
            }
        }
        this.q.p(this.w0);
        this.q.n();
    }

    @Override // com.android.ex.photo.e
    public boolean z() {
        return this.R0;
    }

    public void z0(boolean z) {
        int i = (!z || (a0() && !Z())) ? 1792 : 3846;
        this.X = i;
        T().setSystemUiVisibility(i);
    }
}
