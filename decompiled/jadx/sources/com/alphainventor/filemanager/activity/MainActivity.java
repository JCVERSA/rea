package com.alphainventor.filemanager.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.m;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ax.A1.h;
import ax.C1.d;
import ax.C1.f;
import ax.D5.C0607g;
import ax.E1.P;
import ax.G1.k;
import ax.H1.C0695e;
import ax.H1.C0696f;
import ax.H1.C0697g;
import ax.H1.C0702l;
import ax.H1.C0708s;
import ax.H1.C0709t;
import ax.H1.Q;
import ax.J1.C0734o;
import ax.J1.C0740v;
import ax.J1.V;
import ax.K1.AbstractC0762l;
import ax.K1.AbstractC0775z;
import ax.K1.Y;
import ax.P1.c;
import ax.P1.e;
import ax.P1.j;
import ax.p031c2.i;
import ax.p087g2.l;
import ax.p182n.C5570b;
import ax.p338y1.g;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.ads.AppOpenManager;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.C5827a;
import com.alphainventor.filemanager.file.C5829c;
import com.alphainventor.filemanager.file.C5836j;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.C5841o;
import com.alphainventor.filemanager.file.C5842p;
import com.alphainventor.filemanager.file.N;
import com.alphainventor.filemanager.file.OneDriveFileHelper;
import com.alphainventor.filemanager.file.T;
import com.alphainventor.filemanager.musicplayer.FullScreenPlayerActivity;
import com.alphainventor.filemanager.provider.MyFileProvider;
import com.alphainventor.filemanager.service.CommandService;
import com.alphainventor.filemanager.service.FtpServerService;
import com.alphainventor.filemanager.service.HttpServerService;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends ax.p352z1.b implements e, Y.o, ax.H1.L.c, Q.c {
    private static final Logger b2 = g.a(MainActivity.class);
    private int A1;
    private Runnable C1;
    private Bookmark D1;
    private long E1;
    private Toast F1;
    private long H1;
    private long I1;
    private View J1;
    private boolean K1;
    private Object L1;
    private M M1;
    private BroadcastReceiver N1;
    private Boolean O1;
    private ax.K1.Q Q1;
    private boolean R1;
    private boolean T1;
    private DrawerLayout b1;
    private C5570b c1;
    private View d1;
    private TabLayout e1;
    private FrameLayout f1;
    private View g1;
    private d h1;
    private l i1;
    private View j1;
    private ax.C1.b k1;
    private CoordinatorLayout l1;
    private ax.p087g2.q m1;
    private ViewGroup n1;
    private k o1;
    private f p1;
    private View q1;
    private Button r1;
    private ax.C1.g s1;
    private ViewGroup t1;
    private ViewGroup u1;
    private ax.p087g2.g v1;
    private AbstractC0762l w1;
    private Bookmark x1;
    private ax.G1.e y1;
    private final Object a1 = new Object();
    private ArrayList<L> z1 = new ArrayList<>();
    private boolean B1 = false;
    private com.alphainventor.filemanager.ads.a.j G1 = com.alphainventor.filemanager.ads.a.j.NOT_LOADED;
    private CountDownLatch P1 = new CountDownLatch(1);
    private Handler S1 = new Handler();
    ax.p112i.n U1 = new C5823j(true);
    private Runnable V1 = new RunnableC5825l();
    j W1 = new C5826m();
    C0696f.c X1 = new n();
    j Y1 = new o();
    ax.P1.b Z1 = new p();
    com.alphainventor.filemanager.ads.a.l a2 = new A();

    class A implements com.alphainventor.filemanager.ads.a.l {
        A() {
        }

        @Override // com.alphainventor.filemanager.ads.a.l
        public void a() {
            MainActivity.this.j3();
        }
    }

    static /* synthetic */ class B {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.p338y1.f.values().length];
            a = iArr;
            try {
                iArr[ax.p338y1.f.N0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.p338y1.f.L0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.p338y1.f.J0.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ax.p338y1.f.K0.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ax.p338y1.f.M0.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ax.p338y1.f.P0.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ax.p338y1.f.Q0.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ax.p338y1.f.R0.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ax.p338y1.f.S0.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ax.p338y1.f.U0.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ax.p338y1.f.V0.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[ax.p338y1.f.T0.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    class C extends c {
        C() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            MainActivity.this.b1.M(MainActivity.this.d1);
        }
    }

    class D extends BroadcastReceiver {
        D() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "local.intent.action.LICENSE_STATUS_CHANGED".equals(intent.getAction()) && ax.p031c2.g.g()) {
                if (MainActivity.this.r1 != null) {
                    MainActivity.this.r1.setVisibility(8);
                }
                i.a(context);
            }
        }
    }

    class E extends C5570b {
        E(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i, int i2) {
            super(activity, drawerLayout, toolbar, i, i2);
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.e
        public void a(View view) {
            MainActivity.this.R2();
            MainActivity.this.h1.a0();
            MainActivity.this.k1.d();
            MainActivity.this.s1.l();
            MainActivity.this.invalidateOptionsMenu();
            MainActivity.this.e1.requestFocus();
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.e
        public void b(View view) {
            if (MainActivity.this.h1 != null && MainActivity.this.h1.W()) {
                MainActivity.this.k2();
            }
            MainActivity.this.invalidateOptionsMenu();
        }

        @Override // ax.p182n.C5570b, androidx.drawerlayout.widget.DrawerLayout.e
        public void c(int i) {
            super.c(i);
            if (MainActivity.this.W2()) {
                MainActivity.this.z2().v3();
            }
        }
    }

    class F extends c {
        F() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            AbstractC0762l abstractC0762lZ2 = MainActivity.this.z2();
            if (abstractC0762lZ2 instanceof AbstractC0775z) {
                ((AbstractC0775z) abstractC0762lZ2).V8("toolbar_back");
            } else if ((abstractC0762lZ2 instanceof Y) && FtpServerService.x()) {
                ((Y) abstractC0762lZ2).d6();
            } else {
                MainActivity.this.o3(true, "toolbar_back");
            }
        }
    }

    class H implements ax.p087g2.u {
        final /* synthetic */ androidx.recyclerview.widget.i a;

        H(androidx.recyclerview.widget.i iVar) {
            this.a = iVar;
        }

        @Override // ax.p087g2.u
        public void a(RecyclerView.F f) {
            this.a.H(f);
        }
    }

    public interface I {
        void a(AbstractC0762l abstractC0762l);
    }

    public interface J {
        void a(AbstractC0762l abstractC0762l);
    }

    public static class K extends ax.p045d2.n<Long, Long, Long> {
        private MainActivity h;
        private boolean i;
        private boolean j;
        private CountDownLatch k;

        K(MainActivity mainActivity) {
            super(ax.d2.n.f.HIGH);
            this.h = mainActivity;
            this.k = mainActivity.P1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            if (ax.p031c2.k.t()) {
                ax.C1.c.d(this.h);
            }
            try {
                this.k.await(3000L, TimeUnit.MILLISECONDS);
            } catch (Exception unused) {
            }
            try {
                Thread.sleep(100L);
            } catch (Exception unused2) {
            }
            if (!ax.p045d2.v.s() && !ax.p045d2.v.n(this.h)) {
                boolean zA = ax.p045d2.v.a(this.h);
                this.i = zA;
                if (zA) {
                    boolean zY = ax.p045d2.v.y(this.h);
                    this.j = zY;
                    if (!zY) {
                        ax.p221pa.c.h().g().b("WebView Locked detected!!").i();
                    } else if (!P.J()) {
                        ax.p045d2.v.u(this.h);
                    }
                }
            }
            if (!ax.E1.I.C() || !i.c(this.h)) {
                return null;
            }
            i.o(this.h, false);
            ax.p045d2.x.c0(this.h, UsbAttachActivity.class, false);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Long l) {
            if (this.h.isFinishing()) {
                return;
            }
            if (this.i) {
                if (this.j) {
                    ax.p045d2.v.l(this.h);
                }
                ax.p045d2.v.f(this.h);
            }
            if (ax.p031c2.k.u(this.h)) {
                this.h.finish();
            }
            this.h.R2();
        }
    }

    public interface L {
        void P();
    }

    enum M {
        NONE,
        LOADING,
        LOADING_SHOW_ADS_AFTER_LOAD,
        LOADED,
        CLOSED,
        FAILED_TO_LOAD_NO_FILL,
        FAILED_TO_LOAD_NETWORK
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$a, reason: case insensitive filesystem */
    class C5814a implements AdapterView.OnItemLongClickListener {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$a$a, reason: collision with other inner class name */
        class MenuItemOnMenuItemClickListenerC0492a implements MenuItem.OnMenuItemClickListener {
            final /* synthetic */ Bookmark q;

            MenuItemOnMenuItemClickListenerC0492a(Bookmark bookmark) {
                this.q = bookmark;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ax.p338y1.a.k().o("menu_drawer", "delete_bookmark").c("loc", MainActivity.this.x1.s().H()).e();
                if (ax.C1.c.h(MainActivity.this, this.q)) {
                    MainActivity mainActivity = MainActivity.this;
                    Toast.makeText(mainActivity, mainActivity.getString(R.string.msg_delete_bookmark_success, this.q.v()), 0).show();
                    MainActivity.this.h1.a0();
                }
                return true;
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$a$b */
        class b implements MenuItem.OnMenuItemClickListener {
            final /* synthetic */ Bookmark q;

            b(Bookmark bookmark) {
                this.q = bookmark;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ax.p338y1.a.k().o("menu_drawer", "rename_bookmark").c("loc", MainActivity.this.x1.s().H()).e();
                ax.H1.L.x3(this.q.r(), this.q.v()).p3(MainActivity.this.w(), "rename");
                return true;
            }
        }

        C5814a() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ax.p087g2.n nVar = new ax.p087g2.n(new ax.p252s.c(MainActivity.this.getContext(), R.style.ContextPopupMenu));
            MainActivity.this.getMenuInflater().inflate(R.menu.context_bookmark, nVar);
            Bookmark bookmark = (Bookmark) MainActivity.this.h1.X(i);
            if (bookmark == null) {
                ax.p221pa.c.h().g().d("Favorite context bookmark null").h("position:" + i).i();
                return false;
            }
            nVar.g0(bookmark.v());
            nVar.findItem(R.id.menu_remove).setOnMenuItemClickListener(new MenuItemOnMenuItemClickListenerC0492a(bookmark));
            nVar.findItem(R.id.menu_rename).setOnMenuItemClickListener(new b(bookmark));
            if (!nVar.hasVisibleItems()) {
                return false;
            }
            nVar.h0(view, view.getWidth() / 2, (view.getHeight() * (-1)) / 2);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$b, reason: case insensitive filesystem */
    class C5815b implements AdapterView.OnItemClickListener {
        C5815b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Bookmark bookmark = (Bookmark) MainActivity.this.h1.X(i);
            if (bookmark != null) {
                ax.p338y1.a.k().o("menu_drawer", "open_in_drawer").c("by", "bookmark").c("tgt", bookmark.s().H()).e();
                MainActivity.this.b1.f(MainActivity.this.d1);
                Bookmark bookmarkE = Bookmark.e(bookmark);
                bookmarkE.E(null);
                MainActivity.this.e3(bookmarkE, "bookmark", null, null, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$c, reason: case insensitive filesystem */
    class ViewOnClickListenerC5816c implements View.OnClickListener {
        final /* synthetic */ TextView q;

        ViewOnClickListenerC5816c(TextView textView) {
            this.q = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = !MainActivity.this.h1.W();
            MainActivity.this.h1.c0(z);
            if (z) {
                MainActivity.this.i1.C(true);
                this.q.setText(R.string.menu_save);
            } else {
                MainActivity.this.i1.C(false);
                this.q.setText(R.string.menu_edit);
                MainActivity.this.h1.b0();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$d, reason: case insensitive filesystem */
    class C5817d implements AdapterView.OnItemLongClickListener {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$d$a */
        class a implements MenuItem.OnMenuItemClickListener {
            final /* synthetic */ Bookmark q;

            a(Bookmark bookmark) {
                this.q = bookmark;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (!ax.C1.a.e(MainActivity.this).c(this.q)) {
                    return true;
                }
                MainActivity.this.k1.d();
                return true;
            }
        }

        C5817d() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ax.p087g2.n nVar = new ax.p087g2.n(new ax.p252s.c(MainActivity.this.getContext(), R.style.ContextPopupMenu));
            MainActivity.this.getMenuInflater().inflate(R.menu.context_bookmark, nVar);
            Bookmark bookmark = (Bookmark) MainActivity.this.k1.getItem(i);
            if (bookmark == null) {
                ax.p221pa.c.h().g().d("Favorite context bookmark null").h("position:" + i).i();
                return false;
            }
            nVar.g0("/".equals(bookmark.x()) ? "/" : V.h(bookmark.w()));
            nVar.findItem(R.id.menu_remove).setOnMenuItemClickListener(new a(bookmark));
            nVar.findItem(R.id.menu_rename).setVisible(false);
            if (!nVar.hasVisibleItems()) {
                return false;
            }
            nVar.h0(view, view.getWidth() / 2, (view.getHeight() * (-1)) / 2);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$e, reason: case insensitive filesystem */
    class C5818e implements AdapterView.OnItemClickListener {
        C5818e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Bookmark bookmark = (Bookmark) MainActivity.this.k1.getItem(i);
            if (bookmark != null) {
                ax.p338y1.a.k().o("menu_drawer", "open_in_drawer").c("by", "history").c("tgt", bookmark.s().H()).e();
                MainActivity.this.b1.f(MainActivity.this.d1);
                MainActivity.this.e3(bookmark, "history", null, null, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$f, reason: case insensitive filesystem */
    class ViewOnClickListenerC5819f implements View.OnClickListener {
        ViewOnClickListenerC5819f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ax.C1.c.e(MainActivity.this);
            MainActivity.this.k1.d();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$g, reason: case insensitive filesystem */
    class C5820g implements AdapterView.OnItemClickListener {
        C5820g() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Bookmark bookmark = (Bookmark) MainActivity.this.s1.getItem(i);
            if (bookmark == null) {
                ax.p221pa.c.h().g().d("LoastVisitedAdapter click bookmark null").h("position:" + i).i();
                return;
            }
            if (bookmark.s() == ax.p338y1.f.p1 && MainActivity.this.s1.k()) {
                Toast.makeText(MainActivity.this, R.string.recycle_bin_is_empty, 1).show();
                return;
            }
            ax.p338y1.a.k().o("menu_drawer", "open_in_drawer").c("by", "last_visited").c("tgt", bookmark.s().H()).e();
            MainActivity.this.b1.f(MainActivity.this.d1);
            MainActivity.this.e3(bookmark, "last_visited", null, null, null);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$h, reason: case insensitive filesystem */
    class ViewOnClickListenerC5821h implements View.OnClickListener {
        ViewOnClickListenerC5821h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) PaymentActivity.class));
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$i, reason: case insensitive filesystem */
    class RunnableC5822i implements Runnable {
        final /* synthetic */ Intent q;

        RunnableC5822i(Intent intent) {
            this.q = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.startActivity(this.q);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$j, reason: case insensitive filesystem */
    class C5823j extends ax.p112i.n {
        C5823j(boolean z) {
            super(z);
        }

        @Override // ax.p112i.n
        public void d() {
            if (MainActivity.this.Q1 != null && MainActivity.this.Q1.l1() && MainActivity.this.Q1.u3()) {
                return;
            }
            if (MainActivity.this.b1.D(MainActivity.this.d1)) {
                MainActivity.this.b1.f(MainActivity.this.d1);
                return;
            }
            if (MainActivity.this.b3()) {
                MainActivity.this.Q2();
                return;
            }
            if (MainActivity.this.z2() == null || MainActivity.this.z2().k3()) {
                return;
            }
            if (MainActivity.this.z2().L3() != ax.p338y1.f.n0) {
                MainActivity.this.o3(true, "hw_back");
            } else if (ax.p031c2.g.g() || !ax.V1.a.c(MainActivity.this)) {
                MainActivity.this.o2();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$k, reason: case insensitive filesystem */
    class ViewTreeObserverOnPreDrawListenerC5824k implements ViewTreeObserver.OnPreDrawListener {
        ViewTreeObserverOnPreDrawListenerC5824k() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            ax.p045d2.v.c(MainActivity.this);
            MainActivity.this.P1.countDown();
            MainActivity.this.b1.getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$l, reason: case insensitive filesystem */
    class RunnableC5825l implements Runnable {
        RunnableC5825l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.T1 = false;
            MainActivity.this.G();
        }
    }

    class n implements C0696f.c {
        n() {
        }

        @Override // ax.H1.C0696f.c
        public void a(C0696f.d dVar) {
            C0709t c0709t = new C0709t();
            Bundle bundle = new Bundle();
            if (dVar != null) {
                String str = dVar.b;
                if (str == null) {
                    bundle.putString("host", dVar.a);
                } else {
                    bundle.putString("host", str);
                    bundle.putString("display_name", dVar.a);
                }
            }
            bundle.putInt("action", 1);
            bundle.putInt("port", 0);
            bundle.putSerializable("location", ax.p338y1.f.L0);
            c0709t.J2(bundle);
            c0709t.p3(MainActivity.this.w(), "smb");
        }
    }

    class p implements ax.P1.b {
        p() {
        }

        @Override // ax.P1.b
        public void a(ax.p338y1.f fVar, Object obj) {
            switch (B.a[fVar.ordinal()]) {
                case 1:
                    MainActivity.this.M(new C0696f(), "chooseSmb", true);
                    break;
                case 2:
                    C0709t c0709t = new C0709t();
                    Bundle bundle = new Bundle();
                    bundle.putInt("action", 1);
                    bundle.putInt("port", 0);
                    bundle.putSerializable("location", ax.p338y1.f.L0);
                    c0709t.J2(bundle);
                    MainActivity.this.M(c0709t, "smb", true);
                    break;
                case 3:
                    C0709t c0709t2 = new C0709t();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("action", 1);
                    bundle2.putInt("port", C5841o.Y());
                    bundle2.putSerializable("location", ax.p338y1.f.J0);
                    c0709t2.J2(bundle2);
                    MainActivity.this.M(c0709t2, "ftp", true);
                    break;
                case 4:
                    C0709t c0709t3 = new C0709t();
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("action", 1);
                    bundle3.putInt("port", com.alphainventor.filemanager.file.G.a0());
                    bundle3.putSerializable("location", ax.p338y1.f.K0);
                    c0709t3.J2(bundle3);
                    MainActivity.this.M(c0709t3, "sftp", true);
                    break;
                case 5:
                    C0709t c0709t4 = new C0709t();
                    Bundle bundle4 = new Bundle();
                    bundle4.putInt("action", 1);
                    bundle4.putInt("port", com.alphainventor.filemanager.file.Q.Y());
                    bundle4.putSerializable("location", ax.p338y1.f.M0);
                    c0709t4.J2(bundle4);
                    MainActivity.this.M(c0709t4, "webdav", true);
                    break;
                case 6:
                    MainActivity.this.l3();
                    C5836j.a aVarU = C5836j.U(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity = MainActivity.this;
                    aVarU.k(mainActivity, mainActivity.Y1);
                    break;
                case 7:
                    int iG = C0607g.o().g(MainActivity.this);
                    if (iG != 0) {
                        C0607g.o().l(MainActivity.this, iG, 10002).show();
                    } else {
                        try {
                            MainActivity mainActivity2 = MainActivity.this;
                            C0734o.j0(mainActivity2, C5842p.c0(mainActivity2.getApplicationContext()).n(null), 1);
                        } catch (ActivityNotFoundException unused) {
                            Toast.makeText(MainActivity.this, R.string.error, 1).show();
                            return;
                        }
                    }
                    break;
                case 8:
                    MainActivity.this.l3();
                    OneDriveFileHelper.j jVarI0 = OneDriveFileHelper.i0(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity3 = MainActivity.this;
                    jVarI0.k(mainActivity3, mainActivity3.Y1);
                    break;
                case 9:
                    MainActivity.this.l3();
                    T.c cVarW0 = T.w0(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity4 = MainActivity.this;
                    cVarW0.k(mainActivity4, mainActivity4.Y1);
                    break;
                case 10:
                    MainActivity.this.l3();
                    com.alphainventor.filemanager.file.z.C0(MainActivity.this.getApplicationContext()).o(MainActivity.this);
                    break;
                case 11:
                    MainActivity.this.l3();
                    com.alphainventor.filemanager.file.D.w0(MainActivity.this.getApplicationContext()).l(MainActivity.this);
                    break;
                case 12:
                    MainActivity.this.l3();
                    C5829c.f fVarT = C5829c.T(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity5 = MainActivity.this;
                    fVarT.k(mainActivity5, mainActivity5.Y1);
                    break;
            }
        }
    }

    class q implements Runnable {
        final /* synthetic */ int X;
        final /* synthetic */ ax.p338y1.f q;

        q(ax.p338y1.f fVar, int i) {
            this.q = fVar;
            this.X = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.D2().r(this.q, this.X);
            if (MainActivity.this.s1 != null) {
                MainActivity.this.s1.notifyDataSetChanged();
            }
            MainActivity.this.o1.h(this.q, this.X);
            MainActivity.this.l3();
        }
    }

    class r implements ax.P1.k {
        final /* synthetic */ String a;

        r(String str) {
            this.a = str;
        }

        @Override // ax.P1.k
        public void c(ax.p338y1.f fVar, int i) {
            MainActivity.this.s3(fVar, i);
        }
    }

    class s implements J {
        s() {
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.J
        public void a(AbstractC0762l abstractC0762l) {
            if (abstractC0762l instanceof AbstractC0775z) {
                AbstractC0775z abstractC0775z = (AbstractC0775z) abstractC0762l;
                abstractC0775z.h6();
                abstractC0775z.A8(MainActivity.this.getApplicationContext(), true);
            }
        }
    }

    class t implements Runnable {
        final /* synthetic */ AbstractC0762l X;
        final /* synthetic */ I q;

        t(I i, AbstractC0762l abstractC0762l) {
            this.q = i;
            this.X = abstractC0762l;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.q.a(this.X);
        }
    }

    class u implements I {
        final /* synthetic */ Runnable a;

        u(Runnable runnable) {
            this.a = runnable;
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.I
        public void a(AbstractC0762l abstractC0762l) {
            this.a.run();
        }
    }

    class v implements TabLayout.d {
        v() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(TabLayout.g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(TabLayout.g gVar) {
            Object objI = gVar.i();
            if (objI != null) {
                MainActivity.this.O3(objI.toString());
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.g gVar) {
        }
    }

    class w implements J {
        w() {
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.J
        public void a(AbstractC0762l abstractC0762l) {
            if (abstractC0762l instanceof AbstractC0775z) {
                ((AbstractC0775z) abstractC0762l).h6();
            }
        }
    }

    class x implements com.alphainventor.filemanager.ads.a.k {
        x() {
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void a(View view) {
            MainActivity.this.J1 = view;
            MainActivity.this.K1 = true;
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void b() {
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void c(int i) {
        }
    }

    class y implements com.alphainventor.filemanager.ads.a.i {
        y() {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void a(View view, int i) {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void b(View view) {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void c(View view, String str) {
            MainActivity.this.J1 = view;
            MainActivity.this.K1 = true;
        }
    }

    private void A3(boolean z2, boolean z3, int i) {
        this.c1.i(z2);
        if (z2) {
            this.b1.setDrawerLockMode(0);
        } else {
            this.b1.setDrawerLockMode(0);
            if (i == 0) {
                this.c1.j(null);
            } else {
                this.c1.j(ax.p017b2.a.c(A2().c(), i));
            }
        }
        ax.p087g2.g gVar = this.v1;
        if (gVar != null) {
            gVar.k(z3);
        }
    }

    private void D3() {
        Bookmark bookmark = this.x1;
        if (bookmark != null && bookmark.s() == ax.p338y1.f.s1) {
            c3(R.drawable.ic_clear_material);
            return;
        }
        ax.c2.k.a aVarD = i.d(this);
        if (aVarD != ax.c2.k.a.DRAWER_ONLY_HOME) {
            if (aVarD == ax.c2.k.a.DRAWER_ALWAYS) {
                A3(true, true, 0);
                return;
            } else {
                c3(R.drawable.abc_ic_ab_back_material);
                return;
            }
        }
        Bookmark bookmark2 = this.x1;
        if (bookmark2 == null || bookmark2.s() == ax.p338y1.f.n0) {
            A3(true, true, 0);
        } else {
            A3(false, true, R.drawable.abc_ic_ab_back_material);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        boolean z2 = true;
        if (z2() != null && z2().L3() == ax.p338y1.f.n0) {
            z2 = true ^ this.T1;
        }
        this.U1.j(z2);
    }

    private boolean G2(Intent intent) {
        if (intent == null || intent.getAction() == null || !"com.alphainventor.filemanager.OPEN_ANALYSIS".equals(intent.getAction())) {
            return false;
        }
        ax.J1.F fD = intent.getData() != null ? ax.G1.j.a(intent.getData()).d() : null;
        if (fD != null) {
            ax.p338y1.a.k().o("notification", "storage_full_noti_clicked").c("loc", fD.d().H()).e();
            N3(fD, true, "notification");
        } else {
            N3(null, false, "notification");
        }
        return true;
    }

    private boolean H2(Intent intent) {
        if (intent != null && !intent.getBooleanExtra("com.filemanager.extra.HAS_PENDING_DIALOG", false)) {
            return false;
        }
        CommandService commandServiceQ = CommandService.q();
        if (commandServiceQ != null) {
            commandServiceQ.G(this);
        }
        ax.p017b2.w.j(this).a(102);
        return true;
    }

    private void H3(Bookmark bookmark, ax.G1.e eVar) {
        int iB;
        ax.p338y1.f fVarI;
        int iC;
        if (eVar == null || !eVar.c()) {
            iB = 0;
            fVarI = (bookmark == null || bookmark.s().I() == null) ? null : bookmark.s().I();
        } else {
            fVarI = eVar.a();
            iB = eVar.b();
        }
        if (fVarI == null || (iC = this.o1.c(fVarI, iB)) < 0) {
            return;
        }
        e3(this.o1.d(iC), "show_parent", this.o1.f(iC), null, null);
    }

    private boolean I2(Intent intent) {
        if (intent == null || intent.getAction() == null || !"com.alphainventor.filemanager.OPEN_FILE".equals(intent.getAction()) || intent.getData() == null) {
            return false;
        }
        e3(Bookmark.c(this, intent.getData()), null, null, null, null);
        return true;
    }

    private boolean J2(Intent intent) {
        boolean z2 = false;
        if (intent != null && intent.getAction() != null && "com.alphainventor.filemanager.SAVE_FILE".equals(intent.getAction())) {
            x1("save_file");
            AbstractC0762l abstractC0762lP1 = p1();
            z2 = true;
            if (abstractC0762lP1 != null) {
                abstractC0762lP1.m4(true);
            }
        }
        return z2;
    }

    private boolean K2(Intent intent) {
        List<ax.G1.p> listN;
        String path;
        if (intent != null && intent.getAction() != null && "android.intent.action.VIEW".equals(intent.getAction()) && intent.getData() != null) {
            Uri data = intent.getData();
            String scheme = intent.getData().getScheme();
            if (scheme == null || "file".equals(scheme)) {
                String path2 = data.getPath();
                if (path2 == null) {
                    return false;
                }
                e3(Bookmark.f(this, V.Q(path2)), null, null, null, null);
                return true;
            }
            if ("smb".equals(scheme) || "ftp".equals(scheme) || "sftp".equals(scheme)) {
                String host = data.getHost();
                if ("smb".equals(scheme)) {
                    listN = com.alphainventor.filemanager.file.L.V(this).n();
                } else if ("ftp".equals(scheme)) {
                    listN = C5841o.b0(this).p();
                } else {
                    if (!"sftp".equals(scheme)) {
                        return false;
                    }
                    listN = com.alphainventor.filemanager.file.G.c0(this).n();
                }
                ax.J1.F fD = null;
                for (ax.G1.p pVar : listN) {
                    if (pVar.b() != null && pVar.b().equals(host)) {
                        fD = pVar.d();
                    }
                }
                if (fD == null || (path = data.getPath()) == null) {
                    return false;
                }
                e3(Bookmark.h(this, fD, V.Q(path)), null, null, null, null);
                return true;
            }
        }
        return false;
    }

    private void L2(Intent intent) {
        r1().l(false);
        if (O2(intent) || I2(intent) || G2(intent) || J2(intent) || M2(intent) || K2(intent) || H2(intent) || N2(intent)) {
            return;
        }
        P2(intent);
    }

    private boolean M2(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return false;
        }
        String action = intent.getAction();
        if (!"android.intent.action.GET_CONTENT".equals(action) && !"android.intent.action.OPEN_DOCUMENT".equals(action) && !"android.intent.action.PICK".equals(action)) {
            return false;
        }
        if (ax.D1.c.q().v()) {
            ax.D1.c.q().l();
        }
        boolean booleanExtra = P.Z0() ? intent.getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false) : false;
        r1().h(intent.getStringExtra("com.filemanager.plugin.extra.CALLING_PACKAGE"));
        r1().k(intent.getAction());
        r1().l(true);
        r1().j(booleanExtra);
        r1().i(intent.getType());
        return true;
    }

    private boolean N2(Intent intent) {
        if (intent == null || intent.getAction() == null || !"com.filemanager.SET_PERMISSION".equals(intent.getAction())) {
            return false;
        }
        this.R1 = true;
        return true;
    }

    private boolean O2(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return false;
        }
        String action = intent.getAction();
        action.hashCode();
        if (!action.equals("com.example.android.uamp.open_ui")) {
            return false;
        }
        String stringExtra = intent.getStringExtra("PLAY_FOLDER_URI");
        Intent intentPutExtra = new Intent(this, (Class<?>) FullScreenPlayerActivity.class).setFlags(603979776).putExtra("com.example.android.uamp.CURRENT_MEDIA_DESCRIPTION", intent.getParcelableExtra("com.example.android.uamp.CURRENT_MEDIA_DESCRIPTION"));
        if (stringExtra == null) {
            startActivity(intentPutExtra);
            return true;
        }
        if (intent.getBooleanExtra("com.example.android.uamp.EXTRA_START_FULLSCREEN", false)) {
            g3(stringExtra, new RunnableC5822i(intentPutExtra));
            return true;
        }
        g3(stringExtra, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O3(String str) {
        int i;
        int i2 = 0;
        int i3 = 4;
        if ("Favorite".equals(str)) {
            i = 4;
        } else if ("History".equals(str)) {
            i = 4;
            i2 = 4;
            i3 = 0;
        } else {
            i = "LastVisited".equals(str) ? 0 : 4;
            i2 = 4;
        }
        View view = this.g1;
        if (view != null) {
            view.setVisibility(i2);
        }
        View view2 = this.j1;
        if (view2 != null) {
            view2.setVisibility(i3);
        }
        View view3 = this.q1;
        if (view3 != null) {
            view3.setVisibility(i);
        }
    }

    private boolean P2(Intent intent) {
        if (intent == null || intent.getAction() == null || !"android.intent.action.VIEW_DOWNLOADS".equals(intent.getAction())) {
            return false;
        }
        ax.J1.F f = ax.J1.F.i;
        e3(Bookmark.h(this, f, f.e()), null, null, null, null);
        return true;
    }

    private AbstractC0762l P3(ax.p338y1.f fVar, int i, Bundle bundle, ax.G1.e eVar) {
        String strR2 = r2(fVar, i);
        AbstractC0762l abstractC0762lD3 = (AbstractC0762l) w().i0(strR2);
        if (abstractC0762lD3 == null) {
            abstractC0762lD3 = d3(fVar);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSerializable("location", fVar);
            bundle.putInt("location_key", i);
            abstractC0762lD3.J2(bundle);
        }
        if (abstractC0762lD3 instanceof AbstractC0775z) {
            AbstractC0775z abstractC0775z = (AbstractC0775z) abstractC0762lD3;
            if (eVar == null || !eVar.c()) {
                abstractC0775z.v8(null);
            } else {
                abstractC0775z.v8(eVar.a());
            }
        }
        t3(abstractC0762lD3, strR2);
        return abstractC0762lD3;
    }

    private void Q3() {
        ax.p045d2.g.a().h(this.N1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R2() {
        T2();
        U2();
        V2();
        boolean zW = i.w(this);
        Boolean bool = this.O1;
        if (bool == null || bool.booleanValue() != zW) {
            this.O1 = Boolean.valueOf(zW);
            m2();
            j2("LastVisited", R.drawable.ic_folder, R.string.label_storage);
            j2("Favorite", R.drawable.ic_favorite, R.string.location_favorite);
            if (this.O1.booleanValue()) {
                j2("History", R.drawable.ic_history, R.string.dialog_title_history);
            }
        }
    }

    private void S2() {
        C5570b c5570b = this.c1;
        if (c5570b != null) {
            this.b1.O(c5570b);
        }
        E e = new E(this, this.b1, this.v1.b(), R.string.drawer_open, R.string.drawer_close);
        this.c1 = e;
        e.l(new F());
        this.b1.setDrawerListener(this.c1);
    }

    private void T2() {
        if (this.g1 == null) {
            View viewInflate = getLayoutInflater().inflate(R.layout.tab_favorite, (ViewGroup) this.f1, false);
            this.g1 = viewInflate;
            viewInflate.setVisibility(4);
            RecyclerView recyclerView = (RecyclerView) this.g1.findViewById(R.id.recycler);
            recyclerView.j(new androidx.recyclerview.widget.f(getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).q2()));
            this.f1.addView(this.g1);
            this.h1 = new d(this);
            l lVar = new l(this.h1, false, false);
            this.i1 = lVar;
            androidx.recyclerview.widget.i iVar = new androidx.recyclerview.widget.i(lVar);
            iVar.m(recyclerView);
            recyclerView.setAdapter(this.h1);
            this.h1.f0(new H(iVar));
            this.h1.e0(new C5814a());
            this.h1.d0(new C5815b());
            TextView textView = (TextView) this.g1.findViewById(R.id.bottom_button);
            textView.setOnClickListener(new ViewOnClickListenerC5816c(textView));
        }
    }

    private void U2() {
        if (this.j1 == null) {
            View viewInflate = getLayoutInflater().inflate(R.layout.tab_history, (ViewGroup) this.f1, false);
            this.j1 = viewInflate;
            viewInflate.setVisibility(4);
            this.f1.addView(this.j1);
            ListView listView = (ListView) this.j1.findViewById(R.id.list);
            listView.setEmptyView(this.j1.findViewById(R.id.emptyview));
            ax.C1.b bVar = new ax.C1.b(this, false, false);
            this.k1 = bVar;
            listView.setAdapter((ListAdapter) bVar);
            listView.setOnItemLongClickListener(new C5817d());
            listView.setOnItemClickListener(new C5818e());
            this.j1.findViewById(R.id.bottom_button).setOnClickListener(new ViewOnClickListenerC5819f());
        }
    }

    private void V2() {
        if (this.s1 == null) {
            View viewInflate = getLayoutInflater().inflate(R.layout.tab_last_visited, (ViewGroup) this.f1, false);
            this.q1 = viewInflate;
            viewInflate.setVisibility(4);
            this.f1.addView(this.q1);
            ListView listView = (ListView) this.q1.findViewById(R.id.list);
            listView.setDivider(ax.p017b2.a.c(this, R.drawable.nav_list_divider));
            this.s1 = new ax.C1.g(this, D2(), true);
            D2().b(this.s1);
            listView.setAdapter((ListAdapter) this.s1);
            listView.setOnItemClickListener(new C5820g());
            this.r1 = (Button) this.q1.findViewById(R.id.bottom_button);
            if ((ax.p031c2.d.v().B() == 1 || ax.p031c2.d.v().B() == 2) && !ax.p031c2.g.g()) {
                this.r1.setVisibility(0);
            } else {
                this.r1.setVisibility(8);
            }
            this.r1.setOnClickListener(new ViewOnClickListenerC5821h());
            this.r1.setCompoundDrawablesWithIntrinsicBounds(ax.p017b2.a.c(this, R.drawable.ic_colored_crown), (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private void c3(int i) {
        A3(false, false, i);
        this.b1.setDrawerLockMode(1);
        if (this.v1 != null) {
            Bookmark bookmark = this.x1;
            if (bookmark == null || bookmark.s() == ax.p338y1.f.n0) {
                this.v1.e();
                this.v1.g(false);
            } else {
                this.v1.g(true);
            }
            m3();
        }
    }

    private AbstractC0762l d3(ax.p338y1.f fVar) {
        if (fVar.w() == null) {
            return null;
        }
        try {
            return fVar.w().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void j2(String str, int i, int i2) {
        TabLayout.g gVarF = this.e1.F();
        gVarF.t(str);
        gVarF.r(ax.p017b2.a.c(this, i));
        gVarF.n(i2);
        this.e1.i(gVarF);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k2() {
        d dVar = this.h1;
        if (dVar == null) {
            return;
        }
        dVar.V();
        ((TextView) this.g1.findViewById(R.id.bottom_button)).setText(R.string.menu_edit);
        this.i1.C(false);
    }

    private void m2() {
        TabLayout tabLayout = this.e1;
        if (tabLayout != null) {
            tabLayout.I();
        }
        View view = this.g1;
        if (view != null) {
            view.setVisibility(4);
        }
        View view2 = this.j1;
        if (view2 != null) {
            view2.setVisibility(4);
        }
        View view3 = this.q1;
        if (view3 != null) {
            view3.setVisibility(4);
        }
    }

    private void m3() {
    }

    private void n2() {
        if (this.L1 != null) {
            this.L1 = null;
        }
    }

    private void n3() {
        this.N1 = new D();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("local.intent.action.LICENSE_STATUS_CHANGED");
        ax.p045d2.g.a().c(intentFilter, this.N1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o2() {
        if (this.E1 + 3500 > System.currentTimeMillis()) {
            ax.p338y1.a.k().o("general", "double_back_exit").c("from", "Main").e();
            if (!i.f(this)) {
                i.q(this);
            }
            finish();
            this.S1.removeCallbacks(this.V1);
            Toast toast = this.F1;
            if (toast != null) {
                toast.cancel();
                return;
            }
            return;
        }
        if (X2() && ax.p031c2.a.q(getContext())) {
            ax.p045d2.x.d0(w(), new ax.H1.v(), "exitads", true);
        } else {
            this.T1 = true;
            G();
            this.S1.postDelayed(this.V1, 3500L);
            try {
                Toast toastMakeText = Toast.makeText(this, R.string.back_again_to_exit, 1);
                this.F1 = toastMakeText;
                toastMakeText.show();
            } catch (NullPointerException unused) {
            }
        }
        this.E1 = System.currentTimeMillis();
    }

    private void p2(boolean z2) {
        if (z2) {
            this.u1.setDescendantFocusability(131072);
        } else {
            this.u1.setDescendantFocusability(393216);
        }
    }

    private void p3(String str) {
        Fragment fragmentI0 = w().i0(str);
        if (fragmentI0 != null) {
            if (fragmentI0 instanceof AbstractC0762l) {
                ((AbstractC0762l) fragmentI0).A4();
            }
            w().o().q(fragmentI0).j();
        }
    }

    private void q2(boolean z2) {
        if (z2) {
            this.t1.setDescendantFocusability(131072);
        } else {
            this.t1.setDescendantFocusability(393216);
        }
    }

    private String r2(ax.p338y1.f fVar, int i) {
        return fVar.H() + i;
    }

    private String s2(Bookmark bookmark) {
        return r2(bookmark.s(), bookmark.t());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s3(ax.p338y1.f fVar, int i) {
        p3(r2(fVar, i));
        runOnUiThread(new q(fVar, i));
    }

    private void t3(AbstractC0762l abstractC0762l, String str) {
        AbstractC0762l abstractC0762lP1;
        if (this.B1 || (abstractC0762lP1 = p1()) == abstractC0762l) {
            return;
        }
        androidx.fragment.app.t tVarO = w().o();
        if (abstractC0762lP1 != null && !abstractC0762lP1.X3()) {
            tVarO.p(abstractC0762lP1);
        }
        if (abstractC0762l.l1()) {
            tVarO.v(abstractC0762l);
        } else {
            tVarO.c(R.id.fragment_container, abstractC0762l, str);
        }
        tVarO.j();
    }

    private void x3(Bookmark bookmark, ax.G1.e eVar, AbstractC0762l abstractC0762l) {
        Bookmark bookmarkE = Bookmark.e(bookmark);
        bookmarkE.F(abstractC0762l.O3());
        this.o1.a(bookmarkE, eVar);
        this.x1 = bookmarkE;
        this.y1 = eVar;
        this.w1 = abstractC0762l;
        y3(bookmarkE);
        G();
    }

    private void y3(Bookmark bookmark) {
        if (I0() == null || this.v1 == null) {
            return;
        }
        String strG = ax.p338y1.f.G(this, bookmark.u());
        String strF = ax.p338y1.f.F(this, bookmark.u());
        if (bookmark.s() == ax.p338y1.f.n0) {
            this.v1.i(R.string.app_name);
        } else {
            bookmark.s();
            this.v1.j(strG);
        }
        if (strF != null) {
            this.v1.h(strF);
        } else {
            this.v1.h(null);
        }
    }

    public ax.p087g2.g A2() {
        return this.v1;
    }

    public View B2() {
        return this.J1;
    }

    void B3(Bookmark bookmark, Runnable runnable) {
        this.D1 = bookmark;
        this.C1 = runnable;
    }

    @Override // ax.H1.Q.c
    public void C(ax.p338y1.f fVar, String str) {
        if (fVar == ax.p338y1.f.U0) {
            com.alphainventor.filemanager.file.z.C0(getApplicationContext()).k(this, str, this.Y1);
        } else if (fVar == ax.p338y1.f.V0) {
            com.alphainventor.filemanager.file.D.w0(getApplicationContext()).k(this, str, this.Y1);
        } else {
            ax.p045d2.b.f();
        }
    }

    @Override // ax.p352z1.b
    public void C1(ax.p338y1.f fVar, int i, String str, boolean z2) {
        int iC;
        if (z2 && (iC = this.o1.c(fVar, i)) >= 0) {
            this.o1.k(fVar, i, str);
            D2().c(this.o1.d(iC));
            ax.C1.g gVar = this.s1;
            if (gVar != null) {
                gVar.notifyDataSetChanged();
            }
            M1();
            m3();
        }
        Bookmark bookmark = this.D1;
        if (bookmark != null && bookmark.s() == fVar && this.D1.t() == i && this.D1.w().equals(str)) {
            this.C1.run();
            this.C1 = null;
            this.D1 = null;
        }
    }

    public long C2() {
        return this.H1;
    }

    public void C3(String str, String str2) {
        ax.p087g2.g gVar;
        if (I0() == null || (gVar = this.v1) == null) {
            return;
        }
        gVar.j(str);
        if (str2 != null) {
            this.v1.h(str2);
        } else {
            this.v1.h(null);
        }
    }

    @Override // ax.H1.L.c
    public void D() {
        d dVar = this.h1;
        if (dVar != null) {
            dVar.a0();
        }
    }

    public f D2() {
        if (this.p1 == null) {
            f fVar = new f(this);
            this.p1 = fVar;
            fVar.n();
        }
        return this.p1;
    }

    @Override // androidx.fragment.app.f
    public void E0(Fragment fragment) {
        super.E0(fragment);
    }

    public j E2() {
        return this.W1;
    }

    public void E3() {
        if (v()) {
            return;
        }
        C0697g.q3().p3(w(), "add_sublocation");
    }

    public ax.P1.k F2(String str) {
        return new r(str);
    }

    public void F3() {
        if (v()) {
            return;
        }
        new C0695e().p3(w(), "add_sublocation");
    }

    public void G3() {
        if (v()) {
            return;
        }
        C0697g.r3().p3(w(), "add_sublocation");
    }

    public void I3(boolean z2) {
        Fragment fragmentI0 = w().i0("permission_screen");
        androidx.fragment.app.t tVarO = w().o();
        if (fragmentI0 != null) {
            tVarO.q(fragmentI0);
        }
        ax.K1.Q qJ3 = ax.K1.Q.j3(z2);
        this.Q1 = qJ3;
        tVarO.c(android.R.id.content, qJ3, "permission_screen");
        tVarO.j();
        this.b1.setVisibility(4);
    }

    public void J3(String str) {
        e3(Bookmark.m(this, ax.p338y1.f.p1, 0), str, null, null, null);
    }

    public void K3(ax.J1.F f, String str) {
        f3(f, true, Bookmark.m(this, ax.p338y1.f.p1, 0), str);
    }

    @Override // ax.p352z1.b
    public void L1() {
        D3();
        invalidateOptionsMenu();
        q2(true);
    }

    public void L3() {
        Object obj;
        if (this.M1 == null) {
            ax.p045d2.b.g("rewarded ads null?");
            return;
        }
        try {
            m mVarW = w();
            Fragment fragmentI0 = mVarW.i0("progress");
            if (fragmentI0 != null) {
                androidx.fragment.app.t tVarO = mVarW.o();
                tVarO.q(fragmentI0);
                tVarO.j();
            }
        } catch (Exception unused) {
        }
        M m = this.M1;
        if (m == M.LOADING) {
            this.M1 = M.LOADING_SHOW_ADS_AFTER_LOAD;
            M(new ax.H1.B(), "progress", true);
        } else {
            if (m == M.FAILED_TO_LOAD_NO_FILL) {
                j3();
                return;
            }
            if (m == M.FAILED_TO_LOAD_NETWORK) {
                Toast.makeText(this, R.string.error_network, 1).show();
            } else {
                if (m != M.LOADED || (obj = this.L1) == null) {
                    return;
                }
                com.alphainventor.filemanager.ads.a.G(this, obj, this.a2);
            }
        }
    }

    public void M3(boolean z2, String str) {
        if (v()) {
            return;
        }
        ax.p338y1.a.k().o("ads", "rewarded_ads_open").c("from", str).e();
        C0702l c0702lX3 = C0702l.x3(z2 ? R.string.remove_ads : R.string.rewarded_video_title, R.string.rewarded_video_message, R.string.rewarded_video_watch, android.R.string.cancel);
        c0702lX3.p3(w(), "rewardads");
        c0702lX3.A3(new z(str));
    }

    public void N3(ax.J1.F f, boolean z2, String str) {
        e3(Bookmark.k(this, h.x(f)), str, null, new G(z2), null);
    }

    public void Q2() {
        ViewGroup viewGroup = this.n1;
        if (viewGroup == null) {
            return;
        }
        viewGroup.setVisibility(8);
        q2(true);
        p2(true);
        this.u1.requestFocus();
    }

    public void R3(ax.p338y1.f fVar, int i) {
        this.o1.l(fVar, i);
    }

    public boolean W2() {
        return z2() != null && z2().T3();
    }

    public boolean X2() {
        return this.J1 != null && this.K1;
    }

    public boolean Y2() {
        return this.R1;
    }

    public boolean Z2() {
        ax.K1.Q q2 = this.Q1;
        if (q2 == null) {
            return false;
        }
        return q2.l1();
    }

    @Override // ax.K1.Y.o
    public void a(String str) {
        AbstractC0762l abstractC0762lZ2 = z2();
        if ((abstractC0762lZ2 instanceof ax.K1.G) && abstractC0762lZ2.O3().equals(str)) {
            ((ax.K1.G) abstractC0762lZ2).f8();
        }
    }

    public boolean a3() {
        return this.M1 == M.LOADED && this.L1 != null;
    }

    public boolean b3() {
        ViewGroup viewGroup = this.n1;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    public void e3(Bookmark bookmark, String str, ax.G1.e eVar, J j, I i) {
        Bundle bundle;
        if (this.B1) {
            ax.p221pa.c.h().d("OPEN_BOOKMARK_AFTER_DESTORY").k().h("from:" + str).i();
            return;
        }
        ax.p338y1.f fVarS = bookmark.s();
        int iT = bookmark.t();
        if (fVarS == ax.p338y1.f.Z0) {
            F3();
            return;
        }
        if (fVarS == ax.p338y1.f.a1) {
            startActivity(new Intent(this, (Class<?>) PaymentActivity.class));
            return;
        }
        if (fVarS == ax.p338y1.f.b1) {
            startActivityForResult(new Intent(this, (Class<?>) DevSettingsActivity.class), 10004);
            return;
        }
        Bundle bundle2 = new Bundle();
        if (eVar != null && eVar.c()) {
            bundle2.putSerializable("parent_location", eVar.a());
        }
        if (eVar != null && (bundle = eVar.Y) != null) {
            bundle2.putAll(bundle);
        }
        AbstractC0762l abstractC0762lP3 = P3(fVarS, iT, bundle2, eVar);
        if (str != null) {
            b2.fine("Open location : " + fVarS.H());
            ax.p338y1.a.k().p("navigation", "open_location").c("loc", fVarS.H()).c("from", str).e();
        }
        if (abstractC0762lP3 != null) {
            if (j != null) {
                j.a(abstractC0762lP3);
            }
            if (i != null) {
                B3(bookmark, new t(i, abstractC0762lP3));
            }
            if (bookmark.w() == null && bookmark.u().e() != null) {
                if (bookmark.s() != ax.p338y1.f.W0) {
                    ax.p221pa.c.h().g().d("!!INVLIAD OPEN PATH!!").k().h(bookmark.s().H() + "," + bookmark.z()).i();
                }
                bookmark.F(bookmark.u().e());
            }
            abstractC0762lP3.h4(bookmark.w());
            abstractC0762lP3.x4(eVar);
            x3(bookmark, eVar, abstractC0762lP3);
            M1();
        }
        D3();
    }

    public void f3(ax.J1.F f, boolean z2, Bookmark bookmark, String str) {
        if (f.d() == ax.p338y1.f.i1) {
            e3(bookmark, str, new ax.G1.e(f, z2), new s(), null);
        } else {
            e3(bookmark, str, new ax.G1.e(f, z2), null, null);
        }
    }

    public void g3(String str, Runnable runnable) {
        Uri uri = Uri.parse(str);
        if (ax.G1.j.g(uri, ax.p338y1.f.Y0)) {
            C5839m c5839mD = ax.J1.r.d(ax.G1.j.a(uri).d());
            if (!c5839mD.a()) {
                return;
            }
            try {
                Intent intentL = C0734o.l(uri, null, false, false);
                intentL.setComponent(new ComponentName(this, (Class<?>) ArchiveActivity.class));
                startActivity(intentL);
            } catch (ActivityNotFoundException unused) {
            }
            C5827a c5827a = (C5827a) c5839mD.t();
            Uri uriL0 = c5827a.l0();
            if (c5827a.k0() == 1 && c5827a.r0() != null) {
                b2.fine("Open local file archive viewer : " + uriL0);
                com.alphainventor.filemanager.file.u uVarR0 = c5827a.r0();
                uri = Uri.parse(C0740v.M(uVarR0.R(), uVarR0.S()));
            } else {
                if (!MyFileProvider.v(uriL0) && !MyFileProvider.u(uriL0)) {
                    return;
                }
                b2.fine("Open document file archive viewer : " + uriL0);
                ax.G1.j jVarD = MyFileProvider.d(uriL0);
                if (jVarD == null) {
                    return;
                } else {
                    uri = Uri.parse(C0740v.M(jVarD.d(), V.r(jVarD.e())));
                }
            }
        }
        Bookmark bookmarkC = Bookmark.c(this, uri);
        if (bookmarkC != null) {
            e3(bookmarkC, "location_uri", null, null, runnable != null ? new u(runnable) : null);
            return;
        }
        throw new IllegalArgumentException("Illegal location uri : " + uri.toString());
    }

    public void h3(ax.J1.F f, Bundle bundle) {
        e3(Bookmark.m(this, ax.p338y1.f.s1, f.hashCode()), "search", new ax.G1.e(f, false, bundle), null, null);
    }

    public void i2(L l) {
        this.z1.add(l);
    }

    public void i3(ax.G1.p pVar, ax.J1.F f, String str) {
        Bookmark bookmarkN = Bookmark.n(pVar);
        if (f != null) {
            f3(f, false, bookmarkN, str);
        } else {
            e3(bookmarkN, str, null, null, null);
        }
    }

    public void j3() {
        ax.p031c2.a.l(this);
        ax.p045d2.g.a().e(new Intent("local.intent.action.LICENSE_STATUS_CHANGED"));
        this.M1 = M.NONE;
        n2();
    }

    public void k3(ax.p338y1.f fVar, int i) {
        l3();
        R3(fVar, i);
        y3(this.x1);
    }

    public void l2() {
        View view = this.J1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.j(view, this);
            this.J1 = null;
        }
    }

    public void l3() {
        u3();
    }

    @Override // ax.p352z1.b
    public void m1(ax.p338y1.f fVar, int i, String str) {
        String strR2 = r2(fVar, i);
        Bookmark bookmark = this.x1;
        if (bookmark != null && s2(bookmark).equals(strR2)) {
            o3(true, str);
        } else {
            p3(strR2);
            this.o1.h(fVar, i);
        }
    }

    @Override // ax.P1.e
    public void o(ax.J1.F f) {
        if (f.d() != ax.p338y1.f.r0 || ax.G1.i.D().l0()) {
            e3(Bookmark.k(getApplicationContext(), f), "home", null, new w(), null);
        } else if (ax.G1.i.D().n0()) {
            c1(f);
        } else {
            Toast.makeText(this, R.string.storage_not_available, 1).show();
        }
    }

    public void o3(boolean z2, String str) {
        if (this.b1.D(this.d1)) {
            this.b1.f(this.d1);
        }
        Bookmark bookmark = this.x1;
        ax.G1.e eVar = this.y1;
        p3(s2(bookmark));
        this.o1.h(bookmark.s(), bookmark.t());
        e3(Bookmark.l(this, ax.p338y1.f.n0), str, null, null, null);
        if (z2) {
            H3(bookmark, eVar);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        int iJ;
        super.onActivityResult(i, i2, intent);
        if (i != 1) {
            if (i == 10004 && this.A1 != (iJ = ax.p031c2.k.j())) {
                this.A1 = iJ;
                D3();
                return;
            }
            return;
        }
        if (i2 != -1 || intent == null || intent.getExtras() == null || (stringExtra = intent.getStringExtra("authAccount")) == null) {
            return;
        }
        C5842p.c0(getApplicationContext()).k(stringExtra, this.Y1);
    }

    @Override // ax.p182n.ActivityC5571c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.c1.f(configuration);
        ax.p045d2.v.c(this);
        Z0();
    }

    @Override // ax.p352z1.b, com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        if (P.x1() && ax.p045d2.v.q(this)) {
            ax.E1.u.o(getWindow(), -16777216);
        }
        if (w().t0() != null) {
            androidx.fragment.app.t tVarO = w().o();
            for (Fragment fragment : w().t0()) {
                if (fragment instanceof AbstractC0762l) {
                    tVarO.p(fragment);
                } else if (fragment instanceof ax.K1.Q) {
                    tVarO.q(fragment);
                }
            }
            tVarO.j();
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.b1 = drawerLayout;
        drawerLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserverOnPreDrawListenerC5824k());
        this.l1 = (CoordinatorLayout) findViewById(R.id.ad_container);
        this.t1 = (ViewGroup) findViewById(R.id.toolbar_container);
        this.u1 = (ViewGroup) findViewById(R.id.fragment_container);
        this.d1 = findViewById(R.id.tab_view);
        this.e1 = (TabLayout) findViewById(R.id.tab_layout);
        this.f1 = (FrameLayout) findViewById(R.id.tab_content);
        this.e1.s();
        this.e1.h(new v());
        ax.p087g2.g gVar = new ax.p087g2.g(this, this.t1);
        this.v1 = gVar;
        gVar.l(new C());
        S2();
        this.A1 = ax.p031c2.k.j();
        this.o1 = new k(this);
        if (bundle == null) {
            e3(Bookmark.l(this, ax.p338y1.f.n0), "on_create", null, null, null);
            L2(getIntent());
        }
        D3();
        q().h(this, this.U1);
        new K(this).h(new Long[0]);
        n3();
        ax.O1.c.s().m();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || menu == this.v1.d()) {
            return super.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    @Override // ax.p352z1.b, com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        this.B1 = true;
        super.onDestroy();
        l2();
        Q3();
        n2();
        View viewU2 = u2();
        if (viewU2 != null) {
            com.alphainventor.filemanager.ads.a.j(viewU2, this);
            this.l1.removeAllViews();
        }
    }

    @Override // ax.p182n.ActivityC5571c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (z2() == null || !z2().f4(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (i != 4 || z2() == null) {
            return super.onKeyLongPress(i, keyEvent);
        }
        x1("long_back");
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ax.p045d2.v.c(this);
        L2(intent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.c1.g(menuItem)) {
            return true;
        }
        switch (menuItem.getItemId()) {
            case R.id.menu_refresh /* 2131362436 */:
                ax.p338y1.a.k().o("menu_folder", "refresh").c("loc", this.x1.s().H()).e();
                AbstractC0762l abstractC0762lZ2 = z2();
                if (abstractC0762lZ2 != null) {
                    abstractC0762lZ2.k4(true);
                    break;
                }
            case android.R.id.home:
                return true;
            case R.id.menu_settings /* 2131362449 */:
                ax.p338y1.a.k().o("menu_desktop", "general_settings").e();
                startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
            case R.id.menu_search /* 2131362444 */:
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        super.onPause();
        View view = this.J1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.v(view, this);
        }
        View viewU2 = u2();
        if (viewU2 != null) {
            com.alphainventor.filemanager.ads.a.v(viewU2, this);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.c1.m();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // com.alphainventor.filemanager.activity.a, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.x1 = (Bookmark) bundle.getSerializable("CURRENT_BOOKMARK");
        this.y1 = (ax.G1.e) bundle.getParcelable("CURRENT_EXTRAINFO");
        ArrayList<Bookmark> arrayList = (ArrayList) bundle.getSerializable("OPEN_BOOKMARK");
        ArrayList<ax.G1.e> arrayList2 = (ArrayList) bundle.getSerializable("OPEN_EXTRAINFOS");
        if (arrayList != null) {
            this.o1.i(arrayList);
        }
        if (arrayList2 != null) {
            this.o1.j(arrayList2);
        }
        Bookmark bookmark = this.x1;
        if (bookmark != null) {
            e3(bookmark, "on_restore_instance", this.y1, null, null);
        }
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        C5836j.U(getApplicationContext()).p(this);
        l3();
        HttpServerService.s(this);
        View view = this.J1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.F(view, this);
        }
        View viewU2 = u2();
        if (viewU2 != null) {
            com.alphainventor.filemanager.ads.a.F(viewU2, this);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putSerializable("CURRENT_BOOKMARK", this.x1);
            bundle.putParcelable("CURRENT_EXTRAINFO", this.y1);
            bundle.putSerializable("OPEN_BOOKMARK", this.o1.e());
            bundle.putSerializable("OPEN_EXTRAINFOS", this.o1.g());
        } catch (IllegalStateException e) {
            ax.p221pa.c.h().d("MainActivity onSaveInstanceState Error").m(e).i();
        }
    }

    @Override // ax.p352z1.b, com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        ax.C1.g gVar = this.s1;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
    }

    @Override // ax.p352z1.b, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        super.onStop();
        D2().s();
    }

    @Override // ax.p352z1.b
    public AbstractC0762l p1() {
        return this.w1;
    }

    public void q3() {
        if (this.Q1 == null) {
            return;
        }
        androidx.fragment.app.t tVarO = w().o();
        tVarO.q(this.Q1);
        tVarO.j();
        this.Q1 = null;
        this.b1.setVisibility(0);
    }

    public void r3(L l) {
        this.z1.remove(l);
    }

    @Override // ax.p352z1.b
    public ax.p087g2.q s1() {
        if (this.m1 == null) {
            ax.p087g2.q qVar = new ax.p087g2.q(this, findViewById(R.id.paste_layout), findViewById(R.id.paste_layout_safe_area));
            this.m1 = qVar;
            qVar.B();
        }
        return this.m1;
    }

    public CoordinatorLayout t2() {
        return this.l1;
    }

    View u2() {
        View childAt;
        CoordinatorLayout coordinatorLayout = this.l1;
        if (coordinatorLayout == null || (childAt = coordinatorLayout.getChildAt(0)) == null) {
            return null;
        }
        return childAt;
    }

    public void u3() {
        Iterator<L> it = this.z1.iterator();
        while (it.hasNext()) {
            it.next().P();
        }
    }

    public long v2() {
        if (this.I1 == 0) {
            this.I1 = ax.p031c2.a.b();
        }
        return this.I1;
    }

    public void v3(long j) {
        this.I1 = j;
    }

    @Override // ax.p352z1.b
    public void w1() {
        c3(R.drawable.ic_none);
        A2().a();
        q2(false);
    }

    public com.alphainventor.filemanager.ads.a.j w2() {
        com.alphainventor.filemanager.ads.a.j jVar;
        synchronized (this.a1) {
            jVar = this.G1;
        }
        return jVar;
    }

    public void w3(com.alphainventor.filemanager.ads.a.j jVar) {
        synchronized (this.a1) {
            this.G1 = jVar;
        }
        if (jVar == com.alphainventor.filemanager.ads.a.j.LOADED) {
            if (ax.p031c2.a.n(this) && !AppOpenManager.p() && !ax.p045d2.v.o(this)) {
                AppOpenManager.n(this);
            }
            if (!ax.p031c2.a.q(this) || X2() || ax.p045d2.v.o(this)) {
                return;
            }
            if (ax.p031c2.a.g()) {
                com.alphainventor.filemanager.ads.a.A(this, new x());
            } else {
                this.J1 = com.alphainventor.filemanager.ads.a.B(this, (ax.H1.A.d(P()) && ax.H1.v.A3(ax.p031c2.d.v().t())) ? ax.H1.v.z3(this) : 300, new y());
            }
        }
    }

    @Override // ax.p352z1.b
    public void x1(String str) {
        if (this.b1.D(this.d1)) {
            this.b1.f(this.d1);
        }
        Bookmark bookmarkL = Bookmark.l(this, ax.p338y1.f.n0);
        bookmarkL.F("/moveToHome");
        e3(bookmarkL, str, null, null, null);
    }

    public ax.P1.b x2() {
        return this.Z1;
    }

    @Override // ax.p352z1.b
    public void y1(Bookmark bookmark) {
        Boolean bool = this.O1;
        if (bool != null && bool.booleanValue()) {
            ax.C1.a.e(this).b(bookmark);
        }
        if (bookmark.A()) {
            D2().a(bookmark);
        }
    }

    public C0696f.c y2() {
        return this.X1;
    }

    public AbstractC0762l z2() {
        Bookmark bookmark = this.x1;
        if (bookmark == null) {
            return null;
        }
        return (AbstractC0762l) w().i0(s2(bookmark));
    }

    public void z3(long j) {
        this.H1 = j;
    }

    class G implements J {
        final /* synthetic */ boolean a;

        G(boolean z) {
            this.a = z;
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.J
        public void a(AbstractC0762l abstractC0762l) {
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$m, reason: case insensitive filesystem */
    class C5826m implements j {
        C5826m() {
        }

        @Override // ax.P1.j
        public void d(ax.p338y1.f fVar, int i) {
            MainActivity.this.l3();
            MainActivity.this.e3(Bookmark.n(N.d(MainActivity.this.getApplicationContext(), fVar).f(i)), "remote_add", null, null, null);
        }

        @Override // ax.P1.j
        public void b(ax.p338y1.f fVar) {
        }

        @Override // ax.P1.j
        public void c(ax.p338y1.f fVar, int i, Map<String, Object> map) {
        }

        @Override // ax.P1.j
        public void a(ax.p338y1.f fVar, String str, int i, String str2, String str3) {
        }
    }

    class o implements j {

        class a implements C0708s.c {
            final /* synthetic */ int a;

            a(int i) {
                this.a = i;
            }

            @Override // ax.H1.C0708s.c
            public void a(boolean z) {
                if (MainActivity.this.P() == null) {
                    return;
                }
                C5836j.a aVarU = C5836j.U(MainActivity.this.getApplicationContext());
                MainActivity mainActivity = MainActivity.this;
                aVarU.l(mainActivity, mainActivity.Y1, this.a, z);
            }
        }

        o() {
        }

        @Override // ax.P1.j
        public void a(ax.p338y1.f fVar, String str, int i, String str2, String str3) {
            MainActivity mainActivity = MainActivity.this;
            String string = mainActivity.getString(R.string.msg_connection_failed, fVar.L(mainActivity));
            if (!TextUtils.isEmpty(str3)) {
                if (i.n(MainActivity.this.P())) {
                    string = string + " : " + str3;
                } else if (str3.contains("ERR_SSL_VERSION_OR_CIPHER_MISMATCH")) {
                    string = string + " (" + str3 + ")";
                }
            }
            Toast.makeText(MainActivity.this, string, 1).show();
        }

        @Override // ax.P1.j
        public void c(ax.p338y1.f fVar, int i, Map<String, Object> map) {
            if (fVar != ax.p338y1.f.P0) {
                ax.p045d2.b.f();
                return;
            }
            C0708s c0708s = new C0708s();
            c0708s.p3(MainActivity.this.w(), "dropbox_confirm");
            c0708s.w3(new a(i));
        }

        @Override // ax.P1.j
        public void d(ax.p338y1.f fVar, int i) {
            MainActivity.this.l3();
            MainActivity.this.e3(Bookmark.n(N.b(MainActivity.this.getApplicationContext(), fVar).f(i)), "cloud_add", null, null, null);
        }

        @Override // ax.P1.j
        public void b(ax.p338y1.f fVar) {
        }
    }

    class z implements C0702l.c {
        final /* synthetic */ String q;

        z(String str) {
            this.q = str;
        }

        @Override // ax.H1.C0702l.c
        public void A(C0702l c0702l) {
            ax.p338y1.a.k().q("ads_rewarded_dialog").b("unit", "desktop_reward").b("from", this.q).b("result", "cancel").c();
        }

        @Override // ax.H1.C0702l.c
        public void Z(C0702l c0702l) {
            if (MainActivity.this.P() == null) {
                return;
            }
            ((MainActivity) MainActivity.this.P()).L3();
            ax.p338y1.a.k().q("ads_rewarded_dialog").b("unit", "desktop_reward").b("from", this.q).b("result", "confirm").c();
        }

        @Override // ax.H1.C0702l.c
        public void I(C0702l c0702l) {
        }
    }
}
