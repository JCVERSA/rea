package com.alphainventor.filemanager.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.UriPermission;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i;
import ax.C3.C0677d;
import ax.C3.h;
import ax.C3.o;
import ax.U2.g;
import ax.Y2.d;
import ax.Y2.f;
import ax.Z2.C1719c;
import ax.p038c3.j;
import ax.p038c3.l;
import ax.p054d3.C1902g;
import ax.p054d3.C1903h;
import ax.p054d3.C1904i;
import ax.p054d3.C1909n;
import ax.p054d3.C1915u;
import ax.p054d3.C1916v;
import ax.p054d3.C1918x;
import ax.p054d3.W;
import ax.p086f3.C2014u;
import ax.p086f3.C2017x;
import ax.p086f3.d0;
import ax.p102g3.AbstractC2067n;
import ax.p102g3.C2058g0;
import ax.p102g3.n0;
import ax.p130i.r;
import ax.p182l3.e;
import ax.p182l3.k;
import ax.p210n.C2163b;
import ax.p217n6.C2176g;
import ax.p390y3.m;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import com.alphainventor.filemanager.ads.AppOpenManager;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.C6227b;
import com.alphainventor.filemanager.file.C6229d;
import com.alphainventor.filemanager.file.C6232g;
import com.alphainventor.filemanager.file.C6236k;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6242q;
import com.alphainventor.filemanager.file.C6249y;
import com.alphainventor.filemanager.file.OneDriveFileHelper;
import com.alphainventor.filemanager.file.T;
import com.alphainventor.filemanager.file.Y;
import com.alphainventor.filemanager.musicplayer.FullScreenPlayerActivity;
import com.alphainventor.filemanager.provider.MyFileProvider;
import com.alphainventor.filemanager.service.CommandService;
import com.alphainventor.filemanager.service.FtpServerService;
import com.alphainventor.filemanager.service.HttpServerService;
import com.google.android.material.tabs.TabLayout;
import com.zx.LB;
import defpackage.Xpk8a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends b implements e, n0.o, ax.d3.O.c, W.c {
    private static final Logger M1 = g.a(MainActivity.class);
    private boolean A1;
    private boolean C1;
    private DrawerLayout F0;
    private C2163b G0;
    private View H0;
    private TabLayout I0;
    private FrameLayout J0;
    private View K0;
    private d L0;
    private o M0;
    private View N0;
    private ax.Y2.b O0;
    private CoordinatorLayout P0;
    private CoordinatorLayout Q0;
    private View R0;
    private ax.C3.v S0;
    private ViewGroup T0;
    private l U0;
    private f V0;
    private View W0;
    private Button X0;
    private ax.Y2.g Y0;
    private ViewGroup Z0;
    private ViewGroup a1;
    private h b1;
    private AbstractC2067n c1;
    private Bookmark d1;
    private ax.p038c3.f e1;
    private int g1;
    private Runnable i1;
    private Bookmark j1;
    private Runnable k1;
    private Bookmark l1;
    private long m1;
    private Toast n1;
    private long p1;
    private long q1;
    private View r1;
    private boolean s1;
    private Object t1;
    private R u1;
    private BroadcastReceiver v1;
    private BroadcastReceiver w1;
    private Boolean x1;
    private C2058g0 z1;
    private final Object E0 = new Object();
    private ArrayList<Q> f1 = new ArrayList<>();
    private boolean h1 = false;
    private com.alphainventor.filemanager.ads.a.j o1 = com.alphainventor.filemanager.ads.a.j.NOT_LOADED;
    private CountDownLatch y1 = new CountDownLatch(1);
    private Handler B1 = new Handler();
    private ax.p182l3.c D1 = new K(100);
    Runnable E1 = new L();
    r F1 = new C6221n(true);
    private Runnable G1 = new RunnableC6222o();
    k H1 = new C6223p();
    C1903h.c I1 = new C6224q();
    k J1 = new C6225r();
    ax.p182l3.b K1 = new s();
    com.alphainventor.filemanager.ads.a.l L1 = new G();

    class A implements C0677d.h {
        A() {
        }

        @Override // ax.C3.C0677d.h
        public void a(int i) {
            MainActivity.this.D0();
        }
    }

    class B implements O {
        B() {
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.O
        public void a(AbstractC2067n abstractC2067n) {
            if (abstractC2067n instanceof ax.p102g3.E) {
                ((ax.p102g3.E) abstractC2067n).j7();
            }
        }
    }

    class C implements com.alphainventor.filemanager.ads.a.k {
        C() {
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void a(View view) {
            MainActivity.this.r1 = view;
            MainActivity.this.s1 = true;
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void b() {
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void c(int i) {
        }
    }

    class D extends ax.p182l3.c {
        D() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            MainActivity.this.F0.M(MainActivity.this.H0);
        }
    }

    class E implements com.alphainventor.filemanager.ads.a.i {
        E() {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void a(View view, int i) {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void b(View view) {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void c(View view, String str) {
            MainActivity.this.r1 = view;
            MainActivity.this.s1 = true;
        }
    }

    class F implements C1909n.c {
        final /* synthetic */ String q;

        F(String str) {
            this.q = str;
        }

        @Override // ax.p054d3.C1909n.c
        public void G(C1909n c1909n) {
            ax.U2.a.k().q("ads_rewarded_dialog").b("unit", "desktop_reward").b("from", this.q).b("result", "cancel").c();
        }

        @Override // ax.p054d3.C1909n.c
        public void U(C1909n c1909n) {
        }

        @Override // ax.p054d3.C1909n.c
        public void V(C1909n c1909n) {
            if (MainActivity.this.r() == null) {
                return;
            }
            ((MainActivity) MainActivity.this.r()).V2();
            ax.U2.a.k().q("ads_rewarded_dialog").b("unit", "desktop_reward").b("from", this.q).b("result", "confirm").c();
        }
    }

    class G implements com.alphainventor.filemanager.ads.a.l {
        G() {
        }

        @Override // com.alphainventor.filemanager.ads.a.l
        public void a() {
            MainActivity.this.s2();
        }
    }

    static /* synthetic */ class H {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.U2.f.values().length];
            a = iArr;
            try {
                iArr[ax.U2.f.Q0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.U2.f.O0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.U2.f.M0.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ax.U2.f.N0.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ax.U2.f.P0.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ax.U2.f.S0.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ax.U2.f.T0.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ax.U2.f.U0.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ax.U2.f.V0.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ax.U2.f.W0.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ax.U2.f.Y0.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[ax.U2.f.Z0.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[ax.U2.f.X0.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    class I extends BroadcastReceiver {
        I() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (!"local.intent.action.LICENSE_STATUS_CHANGED".equals(intent.getAction())) {
                if ("local.intent.action.SETTING_CHANGED".equals(intent.getAction())) {
                    MainActivity.this.Y1();
                }
            } else if (ax.p390y3.h.g()) {
                if (MainActivity.this.X0 != null) {
                    MainActivity.this.X0.setVisibility(8);
                }
                ax.p390y3.k.a(context);
            }
        }
    }

    class J extends BroadcastReceiver {
        J() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if ("local.intent.action.USB_IS_EJECTING".equals(intent.getAction())) {
                MainActivity.this.Q2(intent.getStringExtra("desc"));
            } else if ("local.intent.action.USB_IS_EJECTED".equals(intent.getAction())) {
                MainActivity.this.W1();
            }
        }
    }

    class K extends ax.p182l3.c {
        K(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            AbstractC2067n abstractC2067nF1 = MainActivity.this.F1();
            if (abstractC2067nF1 instanceof ax.p102g3.E) {
                ((ax.p102g3.E) abstractC2067nF1).Ca("toolbar_back");
            } else if ((abstractC2067nF1 instanceof n0) && FtpServerService.x()) {
                ((n0) abstractC2067nF1).r6();
            } else {
                MainActivity.this.x2(true, "toolbar_back");
            }
        }
    }

    class L implements Runnable {
        L() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MainActivity.this.R0 != null) {
                MainActivity.this.W1();
            }
        }
    }

    class M implements Animation.AnimationListener {
        M() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (MainActivity.this.R0 != null) {
                MainActivity.this.R0.setVisibility(8);
                MainActivity.this.R0 = null;
                MainActivity.this.D0();
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public interface N {
        void a(AbstractC2067n abstractC2067n);
    }

    public interface O {
        void a(AbstractC2067n abstractC2067n);
    }

    public static class P extends q<Long, Long, Long> {
        private MainActivity h;
        private boolean i;
        private boolean j;
        private CountDownLatch k;

        P(MainActivity mainActivity) {
            super(q.e.HIGH);
            this.h = mainActivity;
            this.k = mainActivity.y1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            if (m.t()) {
                ax.Y2.c.d(this.h);
            }
            try {
                this.k.await(3000L, TimeUnit.MILLISECONDS);
            } catch (Exception unused) {
            }
            try {
                Thread.sleep(100L);
            } catch (Exception unused2) {
            }
            if (!ax.p406z3.z.x() && !ax.p406z3.z.s(this.h)) {
                boolean zA = ax.p406z3.z.a(this.h);
                this.i = zA;
                if (zA) {
                    boolean zE = ax.p406z3.z.E(this.h);
                    this.j = zE;
                    if (!zE) {
                        ax.p302sb.c.h().g().b("WebView Locked detected!!").i();
                    } else if (!ax.p006a3.Q.J()) {
                        ax.p406z3.z.z(this.h);
                    }
                }
            }
            if (ax.p006a3.J.H() && ax.p390y3.k.c(this.h)) {
                ax.p390y3.k.q(this.h, false);
                ax.p406z3.B.c0(this.h, UsbAttachActivity.class, false);
            }
            ax.U2.m.d(this.h.getApplicationContext());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Long l) {
            if (this.h.isFinishing()) {
                return;
            }
            if (this.i) {
                if (this.j) {
                    ax.p406z3.z.p(this.h);
                }
                ax.p406z3.z.g(this.h);
            }
            if (m.u(this.h)) {
                this.h.finish();
            }
            this.h.Y1();
        }
    }

    public interface Q {
        void K();
    }

    enum R {
        NONE,
        LOADING,
        LOADING_SHOW_ADS_AFTER_LOAD,
        LOADED,
        CLOSED,
        FAILED_TO_LOAD_NO_FILL,
        FAILED_TO_LOAD_NETWORK
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$a, reason: case insensitive filesystem */
    class C6208a extends C2163b {
        C6208a(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i, int i2) {
            super(activity, drawerLayout, toolbar, i, i2);
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.e
        public void a(View view) {
            MainActivity.this.Y1();
            if (MainActivity.this.L0 != null) {
                MainActivity.this.L0.a0();
            }
            if (MainActivity.this.O0 != null) {
                MainActivity.this.O0.d();
            }
            if (MainActivity.this.Y0 != null) {
                MainActivity.this.Y0.l();
            }
            MainActivity.this.invalidateOptionsMenu();
            MainActivity.this.I0.requestFocus();
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.e
        public void b(View view) {
            if (MainActivity.this.L0 != null && MainActivity.this.L0.W()) {
                MainActivity.this.o1();
            }
            MainActivity.this.invalidateOptionsMenu();
        }

        @Override // ax.p210n.C2163b, androidx.drawerlayout.widget.DrawerLayout.e
        public void c(int i) {
            super.c(i);
            if (MainActivity.this.e2()) {
                MainActivity.this.F1().u3();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$b, reason: case insensitive filesystem */
    class C6209b implements O {
        final /* synthetic */ boolean a;

        C6209b(boolean z) {
            this.a = z;
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.O
        public void a(AbstractC2067n abstractC2067n) {
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$c, reason: case insensitive filesystem */
    class C6210c implements ax.C3.A {
        final /* synthetic */ i a;

        C6210c(i iVar) {
            this.a = iVar;
        }

        @Override // ax.C3.A
        public void a(RecyclerView.F f) {
            this.a.H(f);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$d, reason: case insensitive filesystem */
    class C6211d implements AdapterView.OnItemLongClickListener {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$d$a */
        class a implements MenuItem.OnMenuItemClickListener {
            final /* synthetic */ Bookmark q;

            a(Bookmark bookmark) {
                this.q = bookmark;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ax.U2.a.k().o("menu_drawer", "delete_bookmark").c("loc", MainActivity.this.d1.t().H()).e();
                if (ax.Y2.c.i(MainActivity.this, this.q)) {
                    MainActivity mainActivity = MainActivity.this;
                    Toast.makeText(mainActivity, mainActivity.getString(com.alphainventor.filemanager.R.string.msg_delete_bookmark_success, this.q.w()), 0).show();
                    MainActivity.this.L0.a0();
                }
                return true;
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$d$b */
        class b implements MenuItem.OnMenuItemClickListener {
            final /* synthetic */ Bookmark q;

            b(Bookmark bookmark) {
                this.q = bookmark;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ax.U2.a.k().o("menu_drawer", "rename_bookmark").c("loc", MainActivity.this.d1.t().H()).e();
                ax.p054d3.O.u3(this.q.s(), this.q.w()).m3(MainActivity.this.getSupportFragmentManager(), "rename");
                return true;
            }
        }

        C6211d() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ax.C3.q qVar = new ax.C3.q(new ax.p290s.c(MainActivity.this.getContext(), com.alphainventor.filemanager.R.style.ContextPopupMenu));
            MainActivity.this.getMenuInflater().inflate(com.alphainventor.filemanager.R.menu.context_bookmark, qVar);
            Bookmark bookmark = (Bookmark) MainActivity.this.L0.X(i);
            if (bookmark == null) {
                ax.p302sb.c.h().g().d("Favorite context bookmark null").h("position:" + i).i();
                return false;
            }
            qVar.g0(bookmark.w());
            qVar.findItem(com.alphainventor.filemanager.R.id.menu_remove).setOnMenuItemClickListener(new a(bookmark));
            qVar.findItem(com.alphainventor.filemanager.R.id.menu_rename).setOnMenuItemClickListener(new b(bookmark));
            if (!qVar.hasVisibleItems()) {
                return false;
            }
            qVar.h0(view, view.getWidth() / 2, (view.getHeight() * (-1)) / 2);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$e, reason: case insensitive filesystem */
    class C6212e implements AdapterView.OnItemClickListener {
        C6212e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Bookmark bookmark = (Bookmark) MainActivity.this.L0.X(i);
            if (bookmark != null) {
                ax.U2.a.k().o("menu_drawer", "open_in_drawer").c("by", "bookmark").c("tgt", bookmark.t().H()).e();
                MainActivity.this.F0.f(MainActivity.this.H0);
                Bookmark bookmarkF = Bookmark.f(bookmark);
                bookmarkF.F(null);
                MainActivity.this.n2(bookmarkF, "bookmark", null, null, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$f, reason: case insensitive filesystem */
    class C6213f extends ax.p182l3.c {
        final /* synthetic */ TextView Y;

        C6213f(TextView textView) {
            this.Y = textView;
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            boolean zW = MainActivity.this.L0.W();
            MainActivity.this.L0.c0(!zW);
            if (!zW) {
                MainActivity.this.M0.C(true);
                this.Y.setText(com.alphainventor.filemanager.R.string.menu_save);
            } else {
                MainActivity.this.M0.C(false);
                this.Y.setText(com.alphainventor.filemanager.R.string.menu_edit);
                MainActivity.this.L0.b0();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$g, reason: case insensitive filesystem */
    class C6214g implements AdapterView.OnItemLongClickListener {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$g$a */
        class a implements MenuItem.OnMenuItemClickListener {
            final /* synthetic */ Bookmark q;

            a(Bookmark bookmark) {
                this.q = bookmark;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (!ax.Y2.a.e(MainActivity.this).c(this.q)) {
                    return true;
                }
                MainActivity.this.O0.d();
                return true;
            }
        }

        C6214g() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ax.C3.q qVar = new ax.C3.q(new ax.p290s.c(MainActivity.this.getContext(), com.alphainventor.filemanager.R.style.ContextPopupMenu));
            MainActivity.this.getMenuInflater().inflate(com.alphainventor.filemanager.R.menu.context_bookmark, qVar);
            Bookmark bookmark = (Bookmark) MainActivity.this.O0.getItem(i);
            if (bookmark == null) {
                ax.p302sb.c.h().g().d("Favorite context bookmark null").h("position:" + i).i();
                return false;
            }
            qVar.g0("/".equals(bookmark.y()) ? "/" : d0.h(bookmark.x()));
            qVar.findItem(com.alphainventor.filemanager.R.id.menu_remove).setOnMenuItemClickListener(new a(bookmark));
            qVar.findItem(com.alphainventor.filemanager.R.id.menu_rename).setVisible(false);
            if (!qVar.hasVisibleItems()) {
                return false;
            }
            qVar.h0(view, view.getWidth() / 2, (view.getHeight() * (-1)) / 2);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$h, reason: case insensitive filesystem */
    class C6215h implements AdapterView.OnItemClickListener {
        C6215h() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Bookmark bookmark = (Bookmark) MainActivity.this.O0.getItem(i);
            if (bookmark != null) {
                ax.U2.a.k().o("menu_drawer", "open_in_drawer").c("by", "history").c("tgt", bookmark.t().H()).e();
                MainActivity.this.F0.f(MainActivity.this.H0);
                MainActivity.this.n2(bookmark, "history", null, null, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$i, reason: case insensitive filesystem */
    class C6216i extends ax.p182l3.c {
        C6216i() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            ax.Y2.c.e(MainActivity.this);
            MainActivity.this.O0.d();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$j, reason: case insensitive filesystem */
    class C6217j implements AdapterView.OnItemClickListener {
        C6217j() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Bookmark bookmark = (Bookmark) MainActivity.this.Y0.getItem(i);
            if (bookmark == null) {
                ax.p302sb.c.h().g().d("LoastVisitedAdapter click bookmark null").h("position:" + i).i();
                return;
            }
            if (bookmark.t() == ax.U2.f.u1 && MainActivity.this.Y0.k()) {
                Toast.makeText(MainActivity.this, com.alphainventor.filemanager.R.string.recycle_bin_is_empty, 1).show();
                return;
            }
            ax.U2.a.k().o("menu_drawer", "open_in_drawer").c("by", "last_visited").c("tgt", bookmark.t().H()).e();
            MainActivity.this.F0.f(MainActivity.this.H0);
            MainActivity.this.n2(bookmark, "last_visited", null, null, null);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$k, reason: case insensitive filesystem */
    class ViewTreeObserverOnPreDrawListenerC6218k implements ViewTreeObserver.OnPreDrawListener {
        ViewTreeObserverOnPreDrawListenerC6218k() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            ax.p406z3.z.c(MainActivity.this);
            MainActivity.this.y1.countDown();
            MainActivity.this.F0.getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$l, reason: case insensitive filesystem */
    class C6219l extends ax.p182l3.c {
        C6219l() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) PaymentActivity.class));
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$m, reason: case insensitive filesystem */
    class RunnableC6220m implements Runnable {
        final /* synthetic */ Intent q;

        RunnableC6220m(Intent intent) {
            this.q = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.startActivity(this.q);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$n, reason: case insensitive filesystem */
    class C6221n extends r {
        C6221n(boolean z) {
            super(z);
        }

        @Override // ax.p130i.r
        public void d() {
            if (MainActivity.this.z1 != null && MainActivity.this.z1.h1() && MainActivity.this.z1.q3()) {
                return;
            }
            if (MainActivity.this.F0.D(MainActivity.this.H0)) {
                MainActivity.this.F0.f(MainActivity.this.H0);
                return;
            }
            if (MainActivity.this.j2()) {
                MainActivity.this.X1();
                return;
            }
            if (MainActivity.this.F1() == null || MainActivity.this.F1().i3()) {
                return;
            }
            if (MainActivity.this.F1().L3() != ax.U2.f.q0) {
                MainActivity.this.x2(true, "hw_back");
            } else if (ax.p390y3.h.g() || !ax.p278r3.a.c(MainActivity.this)) {
                MainActivity.this.u1();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$o, reason: case insensitive filesystem */
    class RunnableC6222o implements Runnable {
        RunnableC6222o() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.C1 = false;
            MainActivity.this.n();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$p, reason: case insensitive filesystem */
    class C6223p implements k {
        C6223p() {
        }

        @Override // ax.p182l3.k
        public void a(ax.U2.f fVar) {
        }

        @Override // ax.p182l3.k
        public void b(ax.U2.f fVar, int i, Map<String, Object> map) {
        }

        @Override // ax.p182l3.k
        public void c(ax.U2.f fVar, String str, int i, String str2, String str3) {
        }

        @Override // ax.p182l3.k
        public void d(ax.U2.f fVar, int i) {
            MainActivity.this.u2();
            MainActivity.this.n2(Bookmark.o(T.d(MainActivity.this.getApplicationContext(), fVar).f(i)), "remote_add", null, null, null);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$q, reason: case insensitive filesystem */
    class C6224q implements C1903h.c {
        C6224q() {
        }

        @Override // ax.p054d3.C1903h.c
        public void a(C1903h.d dVar) {
            if (MainActivity.this.k()) {
                return;
            }
            C1916v c1916v = new C1916v();
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
            bundle.putSerializable("location", ax.U2.f.O0);
            c1916v.F2(bundle);
            c1916v.m3(MainActivity.this.getSupportFragmentManager(), "smb");
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$r, reason: case insensitive filesystem */
    class C6225r implements k {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.MainActivity$r$a */
        class a implements C1915u.c {
            final /* synthetic */ int a;

            a(int i) {
                this.a = i;
            }

            @Override // ax.p054d3.C1915u.c
            public void a(boolean z) {
                if (MainActivity.this.r() == null) {
                    return;
                }
                C6236k.a aVarA0 = C6236k.a0(MainActivity.this.getApplicationContext());
                MainActivity mainActivity = MainActivity.this;
                aVarA0.l(mainActivity, mainActivity.J1, this.a, z);
            }
        }

        C6225r() {
        }

        @Override // ax.p182l3.k
        public void a(ax.U2.f fVar) {
        }

        @Override // ax.p182l3.k
        public void b(ax.U2.f fVar, int i, Map<String, Object> map) {
            if (fVar != ax.U2.f.S0) {
                C2461b.f();
                return;
            }
            C1915u c1915u = new C1915u();
            c1915u.m3(MainActivity.this.getSupportFragmentManager(), "dropbox_confirm");
            c1915u.t3(new a(i));
        }

        @Override // ax.p182l3.k
        public void c(ax.U2.f fVar, String str, int i, String str2, String str3) {
            MainActivity mainActivity = MainActivity.this;
            String string = mainActivity.getString(com.alphainventor.filemanager.R.string.msg_connection_failed, fVar.L(mainActivity));
            if (!TextUtils.isEmpty(str3)) {
                if (ax.p390y3.k.p(MainActivity.this.r())) {
                    string = string + " : " + str3;
                } else if (str3.contains("ERR_SSL_VERSION_OR_CIPHER_MISMATCH")) {
                    string = string + " (" + str3 + ")";
                }
            }
            Toast.makeText(MainActivity.this, string, 1).show();
        }

        @Override // ax.p182l3.k
        public void d(ax.U2.f fVar, int i) {
            MainActivity.this.u2();
            MainActivity.this.n2(Bookmark.o(T.b(MainActivity.this.getApplicationContext(), fVar).f(i)), "cloud_add", null, null, null);
        }
    }

    class s implements ax.p182l3.b {
        s() {
        }

        @Override // ax.p182l3.b
        public void a(ax.U2.f fVar, Object obj) {
            switch (H.a[fVar.ordinal()]) {
                case 1:
                    MainActivity.this.I(new C1903h(), "chooseSmb", true);
                    break;
                case 2:
                    C1916v c1916v = new C1916v();
                    Bundle bundle = new Bundle();
                    bundle.putInt("action", 1);
                    bundle.putInt("port", 0);
                    bundle.putSerializable("location", ax.U2.f.O0);
                    c1916v.F2(bundle);
                    MainActivity.this.I(c1916v, "smb", true);
                    break;
                case 3:
                    C1916v c1916v2 = new C1916v();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("action", 1);
                    bundle2.putInt("port", C6242q.e0());
                    bundle2.putSerializable("location", ax.U2.f.M0);
                    c1916v2.F2(bundle2);
                    MainActivity.this.I(c1916v2, "ftp", true);
                    break;
                case 4:
                    C1916v c1916v3 = new C1916v();
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("action", 1);
                    bundle3.putInt("port", com.alphainventor.filemanager.file.L.g0());
                    bundle3.putSerializable("location", ax.U2.f.N0);
                    c1916v3.F2(bundle3);
                    MainActivity.this.I(c1916v3, "sftp", true);
                    break;
                case 5:
                    C1916v c1916v4 = new C1916v();
                    Bundle bundle4 = new Bundle();
                    bundle4.putInt("action", 1);
                    bundle4.putInt("port", com.alphainventor.filemanager.file.W.e0());
                    bundle4.putSerializable("location", ax.U2.f.P0);
                    c1916v4.F2(bundle4);
                    MainActivity.this.I(c1916v4, "webdav", true);
                    break;
                case 6:
                    MainActivity.this.u2();
                    C6236k.a aVarA0 = C6236k.a0(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity = MainActivity.this;
                    aVarA0.k(mainActivity, mainActivity.J1);
                    break;
                case 7:
                    int iG = C2176g.o().g(MainActivity.this);
                    if (iG != 0) {
                        C2176g.o().l(MainActivity.this, iG, 10002).show();
                    } else {
                        try {
                            MainActivity mainActivity2 = MainActivity.this;
                            C2014u.q0(mainActivity2, com.alphainventor.filemanager.file.r.i0(mainActivity2.getApplicationContext()).n(null), 1);
                        } catch (ActivityNotFoundException unused) {
                            Toast.makeText(MainActivity.this, com.alphainventor.filemanager.R.string.error, 1).show();
                            return;
                        }
                    }
                    break;
                case 8:
                    MainActivity.this.u2();
                    OneDriveFileHelper.j jVarO0 = OneDriveFileHelper.o0(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity3 = MainActivity.this;
                    jVarO0.k(mainActivity3, mainActivity3.J1);
                    break;
                case 9:
                    MainActivity.this.u2();
                    Y.e eVarB0 = Y.b0(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity4 = MainActivity.this;
                    eVarB0.k(mainActivity4, mainActivity4.J1);
                    break;
                case 10:
                    MainActivity.this.u2();
                    com.alphainventor.filemanager.file.I.d dVarZ = com.alphainventor.filemanager.file.I.Z(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity5 = MainActivity.this;
                    dVarZ.q(mainActivity5, mainActivity5.J1);
                    break;
                case 11:
                    MainActivity.this.u2();
                    com.alphainventor.filemanager.file.D.I0(MainActivity.this.getApplicationContext()).o(MainActivity.this);
                    break;
                case 12:
                    MainActivity.this.u2();
                    com.alphainventor.filemanager.file.H.C0(MainActivity.this.getApplicationContext()).l(MainActivity.this);
                    break;
                case 13:
                    MainActivity.this.u2();
                    C6229d.f fVarZ = C6229d.Z(MainActivity.this.getApplicationContext());
                    MainActivity mainActivity6 = MainActivity.this;
                    fVarZ.k(mainActivity6, mainActivity6.J1);
                    break;
            }
        }
    }

    class t implements Runnable {
        final /* synthetic */ int X;
        final /* synthetic */ ax.U2.f q;

        t(ax.U2.f fVar, int i) {
            this.q = fVar;
            this.X = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.I1().r(this.q, this.X);
            if (MainActivity.this.Y0 != null) {
                MainActivity.this.Y0.notifyDataSetChanged();
            }
            MainActivity.this.U0.h(this.q, this.X);
            MainActivity.this.u2();
        }
    }

    class u implements ax.p182l3.l {
        final /* synthetic */ String a;

        u(String str) {
            this.a = str;
        }

        @Override // ax.p182l3.l
        public void c(ax.U2.f fVar, int i) {
            MainActivity.this.B2(fVar, i);
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
                MainActivity.this.Y2(objI.toString());
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.g gVar) {
        }
    }

    class w implements O {
        w() {
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.O
        public void a(AbstractC2067n abstractC2067n) {
            if (abstractC2067n instanceof ax.p102g3.E) {
                ax.p102g3.E e = (ax.p102g3.E) abstractC2067n;
                e.j7();
                e.Z9(MainActivity.this.getApplicationContext(), true);
            }
        }
    }

    class x implements Runnable {
        final /* synthetic */ AbstractC2067n X;
        final /* synthetic */ N q;

        x(N n, AbstractC2067n abstractC2067n) {
            this.q = n;
            this.X = abstractC2067n;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.q.a(this.X);
        }
    }

    class y implements Runnable {
        final /* synthetic */ Bookmark X;
        final /* synthetic */ AbstractC2067n q;

        y(AbstractC2067n abstractC2067n, Bookmark bookmark) {
            this.q = abstractC2067n;
            this.X = bookmark;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.q.m4(this.X.x());
        }
    }

    class z implements N {
        final /* synthetic */ Runnable a;

        z(Runnable runnable) {
            this.a = runnable;
        }

        @Override // com.alphainventor.filemanager.activity.MainActivity.N
        public void a(AbstractC2067n abstractC2067n) {
            this.a.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B2(ax.U2.f fVar, int i) {
        y2(x1(fVar, i));
        runOnUiThread(new t(fVar, i));
    }

    private void C2(AbstractC2067n abstractC2067n, String str) {
        AbstractC2067n abstractC2067nG0;
        if (this.h1 || (abstractC2067nG0 = g0()) == abstractC2067n) {
            return;
        }
        androidx.fragment.app.t tVarO = getSupportFragmentManager().o();
        if (abstractC2067nG0 != null && !abstractC2067nG0.a4()) {
            tVarO.p(abstractC2067nG0);
        }
        if (abstractC2067n.h1()) {
            tVarO.v(abstractC2067n);
        } else {
            tVarO.c(com.alphainventor.filemanager.R.id.fragment_container, abstractC2067n, str);
        }
        tVarO.j();
    }

    private void G2(Bookmark bookmark, ax.p038c3.f fVar, AbstractC2067n abstractC2067n) {
        Bookmark bookmarkF = Bookmark.f(bookmark);
        bookmarkF.G(abstractC2067n.O3());
        this.U0.a(bookmarkF, fVar);
        this.d1 = bookmarkF;
        this.e1 = fVar;
        this.c1 = abstractC2067n;
        H2(bookmarkF);
        n();
    }

    private void H2(Bookmark bookmark) {
        if (getSupportActionBar() == null || this.b1 == null) {
            return;
        }
        String strG = ax.U2.f.G(this, bookmark.v());
        String strF = ax.U2.f.F(this, bookmark.v());
        if (bookmark.t() == ax.U2.f.q0) {
            this.b1.i(com.alphainventor.filemanager.R.string.app_name);
        } else {
            bookmark.t();
            this.b1.j(strG);
        }
        if (strF != null) {
            this.b1.h(strF);
        } else {
            this.b1.h(null);
        }
    }

    private void J2(boolean z2, boolean z3, int i) {
        C2163b c2163b = this.G0;
        if (c2163b != null) {
            c2163b.i(z2);
            if (z2) {
                this.F0.setDrawerLockMode(0);
            } else {
                this.F0.setDrawerLockMode(0);
                if (i == 0) {
                    this.G0.j(null);
                } else {
                    this.G0.j(ax.p374x3.a.c(h0().c(), i));
                }
            }
        }
        h hVar = this.b1;
        if (hVar != null) {
            hVar.k(z3);
        }
    }

    private boolean L1(Intent intent) {
        if (intent == null || intent.getAction() == null || !"com.alphainventor.filemanager.OPEN_ANALYSIS".equals(intent.getAction())) {
            return false;
        }
        ax.p086f3.K kD = intent.getData() != null ? ax.p038c3.k.a(intent.getData()).d() : null;
        if (kD != null) {
            ax.U2.a.k().o("notification", "storage_full_noti_clicked").c("loc", kD.d().H()).e();
            X2(kD, true, "notification");
        } else {
            X2(null, false, "notification");
        }
        return true;
    }

    private boolean M1(Intent intent) {
        if (intent != null && !intent.getBooleanExtra("com.filemanager.extra.HAS_PENDING_DIALOG", false)) {
            return false;
        }
        CommandService commandServiceQ = CommandService.q();
        if (commandServiceQ != null) {
            commandServiceQ.G(this);
        }
        ax.p374x3.w.j(this).a(102);
        return true;
    }

    private boolean N1(Intent intent) {
        Bookmark bookmarkC;
        if (intent == null || intent.getAction() == null || !"com.alphainventor.filemanager.OPEN_FILE".equals(intent.getAction()) || intent.getData() == null || (bookmarkC = Bookmark.c(this, intent.getData())) == null) {
            return false;
        }
        n2(bookmarkC, null, null, null, null);
        return true;
    }

    private boolean O1(Intent intent) {
        boolean z2 = false;
        if (intent != null && intent.getAction() != null && "com.alphainventor.filemanager.SAVE_FILE".equals(intent.getAction())) {
            t0("save_file");
            AbstractC2067n abstractC2067nG0 = g0();
            z2 = true;
            if (abstractC2067nG0 != null) {
                abstractC2067nG0.r4(true);
            }
        }
        return z2;
    }

    private void P1(Intent intent) {
        j0().m(false);
        if (S1(intent) || N1(intent) || L1(intent) || O1(intent) || Q1(intent) || V1(intent) || M1(intent) || R1(intent) || U1(intent)) {
            return;
        }
        T1(intent);
    }

    private boolean Q1(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return false;
        }
        String action = intent.getAction();
        if (!"android.intent.action.GET_CONTENT".equals(action) && !"android.intent.action.OPEN_DOCUMENT".equals(action) && !"android.intent.action.PICK".equals(action) && !"android.intent.action.CREATE_SHORTCUT".equals(action)) {
            return false;
        }
        if (C1719c.q().v()) {
            C1719c.q().l();
        }
        boolean booleanExtra = ax.p006a3.Q.l1() ? intent.getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false) : false;
        j0().i(intent.getStringExtra("com.filemanager.plugin.extra.CALLING_PACKAGE"));
        j0().l(intent.getAction());
        j0().m(true);
        j0().k(booleanExtra);
        j0().j(intent.getType());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q2(String str) {
        this.Q0.removeAllViews();
        View viewFindViewById = getLayoutInflater().inflate(com.alphainventor.filemanager.R.layout.view_storage_is_ejecting, (ViewGroup) this.Q0, true).findViewById(com.alphainventor.filemanager.R.id.msg_container);
        this.R0 = viewFindViewById;
        ((TextView) viewFindViewById.findViewById(com.alphainventor.filemanager.R.id.message1)).setText(getString(com.alphainventor.filemanager.R.string.ext_media_unmounting_notification_title, str));
        this.R0.startAnimation(AnimationUtils.loadAnimation(this, com.alphainventor.filemanager.R.anim.dock_bottom_enter));
        D0();
        this.B1.postDelayed(this.E1, 30000L);
    }

    private boolean R1(Intent intent) {
        if (intent == null || intent.getAction() == null || !"com.filemanager.SET_PERMISSION".equals(intent.getAction())) {
            return false;
        }
        this.A1 = true;
        return true;
    }

    private void R2(Bookmark bookmark, ax.p038c3.f fVar) {
        int iB;
        ax.U2.f fVarI;
        int iC;
        if (fVar == null || !fVar.c()) {
            iB = 0;
            fVarI = (bookmark == null || bookmark.t().I() == null) ? null : bookmark.t().I();
        } else {
            fVarI = fVar.a();
            iB = fVar.b();
        }
        if (fVarI == null || (iC = this.U0.c(fVarI, iB)) < 0) {
            return;
        }
        n2(this.U0.d(iC), "show_parent", this.U0.f(iC), null, null);
    }

    private boolean S1(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return false;
        }
        String action = intent.getAction();
        action.getClass();
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
            p2(stringExtra, new RunnableC6220m(intentPutExtra));
            return true;
        }
        p2(stringExtra, null);
        return true;
    }

    private boolean T1(Intent intent) {
        if (intent != null && intent.getAction() != null && "android.intent.action.VIEW".equals(intent.getAction()) && intent.getData() != null && "content".equals(intent.getData().getScheme())) {
            Toast.makeText(this, com.alphainventor.filemanager.R.string.error_invalid_file_format, 1).show();
        }
        return false;
    }

    private boolean U1(Intent intent) {
        if (intent == null || intent.getAction() == null || !"android.intent.action.VIEW_DOWNLOADS".equals(intent.getAction())) {
            return false;
        }
        ax.p086f3.K k = ax.p086f3.K.i;
        n2(Bookmark.i(this, k, k.e()), null, null, null, null);
        return true;
    }

    private boolean V1(Intent intent) {
        List<ax.p038c3.s> listN;
        String path;
        boolean z2 = false;
        if (intent != null && intent.getAction() != null && "android.intent.action.VIEW".equals(intent.getAction()) && intent.getData() != null) {
            Uri data = intent.getData();
            String scheme = intent.getData().getScheme();
            if ("content".equals(scheme) && "com.android.externalstorage.documents".equals(intent.getData().getHost())) {
                String strR = C6232g.r(data);
                if (strR == null) {
                    return false;
                }
                n2(Bookmark.g(this, d0.U(strR)), null, null, null, null);
                return true;
            }
            if (scheme == null || "file".equals(scheme)) {
                String path2 = data.getPath();
                if (path2 == null) {
                    return false;
                }
                n2(Bookmark.g(this, d0.U(path2)), null, null, null, null);
                return true;
            }
            if ("smb".equals(scheme) || "ftp".equals(scheme) || "sftp".equals(scheme)) {
                String host = data.getHost();
                if ("smb".equals(scheme)) {
                    listN = com.alphainventor.filemanager.file.Q.b0(this).n();
                } else if ("ftp".equals(scheme)) {
                    listN = C6242q.h0(this).p();
                } else {
                    if (!"sftp".equals(scheme)) {
                        return false;
                    }
                    listN = com.alphainventor.filemanager.file.L.i0(this).n();
                }
                ax.p086f3.K kD = null;
                for (ax.p038c3.s sVar : listN) {
                    if (sVar.b() != null && sVar.b().equals(host)) {
                        kD = sVar.d();
                    }
                }
                if (kD == null || (path = data.getPath()) == null) {
                    return false;
                }
                n2(Bookmark.i(this, kD, d0.U(path)), null, null, null, null);
                return true;
            }
            if ("content".equals(scheme) && "com.android.mtp.documents".equals(intent.getData().getHost()) && ax.p006a3.Q.k1()) {
                try {
                    List<UriPermission> persistedUriPermissions = getContentResolver().getPersistedUriPermissions();
                    Uri uriZ = ax.p086f3.L.Z(intent.getData());
                    if (persistedUriPermissions != null) {
                        boolean z3 = false;
                        for (UriPermission uriPermission : persistedUriPermissions) {
                            if (uriPermission.getUri() != null && uriPermission.getUri().equals(uriZ)) {
                                z3 = true;
                            }
                        }
                        z2 = z3;
                    }
                    if (z2) {
                        j.F().t();
                    } else {
                        ax.p086f3.K kW0 = j.F().W0(intent.getData());
                        if (kW0 != null) {
                            R(0, kW0, null, true, true);
                        }
                    }
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W1() {
        if (this.R0 == null) {
            return;
        }
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, com.alphainventor.filemanager.R.anim.dock_bottom_exit);
        animationLoadAnimation.setAnimationListener(new M());
        this.R0.startAnimation(animationLoadAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y1() {
        a2();
        b2();
        c2();
        boolean z2 = ax.p390y3.k.z(this);
        Boolean bool = this.x1;
        if (bool == null || bool.booleanValue() != z2) {
            this.x1 = Boolean.valueOf(z2);
            r1();
            n1("LastVisited", com.alphainventor.filemanager.R.drawable.ic_folder, com.alphainventor.filemanager.R.string.label_storage);
            n1("Favorite", com.alphainventor.filemanager.R.drawable.ic_favorite, com.alphainventor.filemanager.R.string.location_favorite);
            if (this.x1.booleanValue()) {
                n1("History", com.alphainventor.filemanager.R.drawable.ic_history, com.alphainventor.filemanager.R.string.dialog_title_history);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y2(String str) {
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
        View view = this.K0;
        if (view != null) {
            view.setVisibility(i2);
        }
        View view2 = this.N0;
        if (view2 != null) {
            view2.setVisibility(i3);
        }
        View view3 = this.W0;
        if (view3 != null) {
            view3.setVisibility(i);
        }
    }

    private void Z1() {
        C2163b c2163b = this.G0;
        if (c2163b != null) {
            this.F0.O(c2163b);
        }
        C6208a c6208a = new C6208a(this, this.F0, this.b1.b(), com.alphainventor.filemanager.R.string.drawer_open, com.alphainventor.filemanager.R.string.drawer_close);
        this.G0 = c6208a;
        c6208a.l(this.D1);
        this.F0.setDrawerListener(this.G0);
    }

    private AbstractC2067n Z2(ax.U2.f fVar, int i, Bundle bundle, ax.p038c3.f fVar2) {
        String strX1 = x1(fVar, i);
        AbstractC2067n abstractC2067nM2 = (AbstractC2067n) getSupportFragmentManager().k0(strX1);
        if (abstractC2067nM2 == null) {
            abstractC2067nM2 = m2(fVar);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSerializable("location", fVar);
            bundle.putInt("location_key", i);
            abstractC2067nM2.F2(bundle);
        }
        if (abstractC2067nM2 instanceof ax.p102g3.E) {
            ax.p102g3.E e = (ax.p102g3.E) abstractC2067nM2;
            if (fVar2 == null || !fVar2.c()) {
                e.U9(null);
            } else {
                e.U9(fVar2.a());
            }
        }
        C2(abstractC2067nM2, strX1);
        return abstractC2067nM2;
    }

    private void a2() {
        if (this.K0 == null) {
            View viewInflate = getLayoutInflater().inflate(com.alphainventor.filemanager.R.layout.tab_favorite, (ViewGroup) this.J0, false);
            this.K0 = viewInflate;
            viewInflate.setVisibility(4);
            RecyclerView recyclerView = (RecyclerView) this.K0.findViewById(com.alphainventor.filemanager.R.id.recycler);
            recyclerView.j(new androidx.recyclerview.widget.f(getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).q2()));
            this.J0.addView(this.K0);
            this.L0 = new d(this);
            o oVar = new o(this.L0, false, false);
            this.M0 = oVar;
            i iVar = new i(oVar);
            iVar.m(recyclerView);
            recyclerView.setAdapter(this.L0);
            this.L0.f0(new C6210c(iVar));
            this.L0.e0(new C6211d());
            this.L0.d0(new C6212e());
            TextView textView = (TextView) this.K0.findViewById(com.alphainventor.filemanager.R.id.bottom_button);
            textView.setOnClickListener(new C6213f(textView));
        }
    }

    private void a3() {
        ax.p406z3.g.a().h(this.v1);
        ax.p406z3.g.a().h(this.w1);
        this.B1.removeCallbacks(this.E1);
    }

    private void b2() {
        if (this.N0 == null) {
            View viewInflate = getLayoutInflater().inflate(com.alphainventor.filemanager.R.layout.tab_history, (ViewGroup) this.J0, false);
            this.N0 = viewInflate;
            viewInflate.setVisibility(4);
            this.J0.addView(this.N0);
            ListView listView = (ListView) this.N0.findViewById(com.alphainventor.filemanager.R.id.list);
            listView.setEmptyView(this.N0.findViewById(com.alphainventor.filemanager.R.id.emptyview));
            ax.Y2.b bVar = new ax.Y2.b(this, false, false);
            this.O0 = bVar;
            listView.setAdapter((ListAdapter) bVar);
            listView.setOnItemLongClickListener(new C6214g());
            listView.setOnItemClickListener(new C6215h());
            this.N0.findViewById(com.alphainventor.filemanager.R.id.bottom_button).setOnClickListener(new C6216i());
        }
    }

    private void c2() {
        if (this.Y0 == null) {
            View viewInflate = getLayoutInflater().inflate(com.alphainventor.filemanager.R.layout.tab_last_visited, (ViewGroup) this.J0, false);
            this.W0 = viewInflate;
            viewInflate.setVisibility(4);
            this.J0.addView(this.W0);
            ListView listView = (ListView) this.W0.findViewById(com.alphainventor.filemanager.R.id.list);
            listView.setDivider(ax.p374x3.a.c(this, com.alphainventor.filemanager.R.drawable.nav_list_divider));
            this.Y0 = new ax.Y2.g(this, I1(), true);
            I1().b(this.Y0);
            listView.setAdapter((ListAdapter) this.Y0);
            listView.setOnItemClickListener(new C6217j());
            this.X0 = (Button) this.W0.findViewById(com.alphainventor.filemanager.R.id.bottom_button);
            if ((ax.p390y3.d.v().B() == 1 || ax.p390y3.d.v().B() == 2) && !ax.p390y3.h.g()) {
                this.X0.setVisibility(0);
            } else {
                this.X0.setVisibility(8);
            }
            this.X0.setOnClickListener(new C6219l());
            this.X0.setCompoundDrawablesWithIntrinsicBounds(ax.p374x3.a.c(this, com.alphainventor.filemanager.R.drawable.ic_colored_crown), (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private void c3() {
        d3();
    }

    private void d2() {
        Z1();
    }

    private void d3() {
        Bookmark bookmark = this.d1;
        if (bookmark != null && bookmark.t() == ax.U2.f.x1) {
            l2(com.alphainventor.filemanager.R.drawable.ic_clear_material);
            return;
        }
        m.a aVarD = ax.p390y3.k.d(this);
        if (aVarD != m.a.DRAWER_ONLY_HOME) {
            if (aVarD == m.a.DRAWER_ALWAYS) {
                J2(true, true, 0);
                return;
            } else {
                l2(com.alphainventor.filemanager.R.drawable.abc_ic_ab_back_material);
                return;
            }
        }
        Bookmark bookmark2 = this.d1;
        if (bookmark2 == null || bookmark2.t() == ax.U2.f.q0) {
            J2(true, true, 0);
        } else {
            J2(false, true, com.alphainventor.filemanager.R.drawable.abc_ic_ab_back_material);
        }
    }

    private void k2() {
        l2(com.alphainventor.filemanager.R.drawable.ic_none);
    }

    private void l2(int i) {
        J2(false, false, i);
        this.F0.setDrawerLockMode(1);
        if (this.b1 != null) {
            Bookmark bookmark = this.d1;
            if (bookmark == null || bookmark.t() == ax.U2.f.q0) {
                this.b1.e();
                this.b1.g(false);
            } else {
                this.b1.g(true);
            }
            v2();
        }
    }

    private AbstractC2067n m2(ax.U2.f fVar) {
        if (fVar.x() == null) {
            return null;
        }
        try {
            return fVar.x().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        boolean z2 = true;
        if (F1() != null && F1().L3() == ax.U2.f.q0) {
            z2 = true ^ this.C1;
        }
        this.F1.j(z2);
    }

    private void n1(String str, int i, int i2) {
        TabLayout.g gVarF = this.I0.F();
        gVarF.t(str);
        gVarF.r(ax.p374x3.a.c(this, i));
        gVarF.n(i2);
        this.I0.i(gVarF);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o1() {
        d dVar = this.L0;
        if (dVar == null) {
            return;
        }
        dVar.V();
        ((TextView) this.K0.findViewById(com.alphainventor.filemanager.R.id.bottom_button)).setText(com.alphainventor.filemanager.R.string.menu_edit);
        this.M0.C(false);
    }

    private void p1() {
        if (this.n1 != null) {
            this.B1.removeCallbacks(this.G1);
            this.n1.cancel();
            this.n1 = null;
        }
    }

    private void r1() {
        TabLayout tabLayout = this.I0;
        if (tabLayout != null) {
            tabLayout.I();
        }
        View view = this.K0;
        if (view != null) {
            view.setVisibility(4);
        }
        View view2 = this.N0;
        if (view2 != null) {
            view2.setVisibility(4);
        }
        View view3 = this.W0;
        if (view3 != null) {
            view3.setVisibility(4);
        }
    }

    private void s1(Bundle bundle) {
        Object obj = bundle.get("androidx.lifecycle.BundlableSavedStateRegistry.key");
        if (obj instanceof Bundle) {
            Object obj2 = ((Bundle) obj).get("android:support:fragments");
            if (obj2 instanceof Bundle) {
                Bundle bundle2 = (Bundle) obj2;
                for (String str : bundle2.keySet()) {
                    if (str != null && str.startsWith("fragment_")) {
                        Object obj3 = bundle2.get(str);
                        if (obj3 instanceof Bundle) {
                            Object obj4 = ((Bundle) obj3).get("state");
                            if ((obj4 instanceof Parcelable) && ax.p406z3.B.x((Parcelable) obj4) >= 524288) {
                                androidx.fragment.app.q.a(obj4);
                            }
                        }
                    }
                }
            }
        }
    }

    private void t1() {
        if (this.t1 != null) {
            this.t1 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u1() {
        if (this.m1 + 3500 > System.currentTimeMillis()) {
            ax.U2.a.k().o("general", "double_back_exit").c("from", "Main").e();
            finish();
            p1();
            return;
        }
        if (f2() && ax.p390y3.a.q(getContext())) {
            ax.p406z3.B.d0(getSupportFragmentManager(), new C1918x(), "exitads", true);
        } else {
            if (!ax.p390y3.k.f(this)) {
                ax.p390y3.k.s(this);
            }
            this.C1 = true;
            n();
            this.B1.postDelayed(this.G1, 3500L);
            try {
                Toast toastMakeText = Toast.makeText(this, com.alphainventor.filemanager.R.string.back_again_to_exit, 1);
                this.n1 = toastMakeText;
                toastMakeText.show();
            } catch (NullPointerException | SecurityException unused) {
            }
        }
        this.m1 = System.currentTimeMillis();
    }

    private void v1(boolean z2) {
        if (z2) {
            this.a1.setDescendantFocusability(131072);
        } else {
            this.a1.setDescendantFocusability(393216);
        }
    }

    private void v2() {
    }

    private void w1(boolean z2) {
        if (z2) {
            this.Z0.setDescendantFocusability(131072);
        } else {
            this.Z0.setDescendantFocusability(393216);
        }
    }

    private void w2() {
        this.v1 = new I();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("local.intent.action.LICENSE_STATUS_CHANGED");
        intentFilter.addAction("local.intent.action.SETTING_CHANGED");
        ax.p406z3.g.a().c(intentFilter, this.v1);
        this.w1 = new J();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("local.intent.action.USB_IS_EJECTING");
        intentFilter2.addAction("local.intent.action.USB_IS_EJECTED");
        ax.p406z3.g.a().c(intentFilter2, this.w1);
    }

    private String x1(ax.U2.f fVar, int i) {
        return fVar.H() + i;
    }

    private String y1(Bookmark bookmark) {
        return x1(bookmark.t(), bookmark.u());
    }

    private void y2(String str) {
        Fragment fragmentK0 = getSupportFragmentManager().k0(str);
        if (fragmentK0 != null) {
            if (fragmentK0 instanceof AbstractC2067n) {
                ((AbstractC2067n) fragmentK0).F4();
            }
            getSupportFragmentManager().o().q(fragmentK0).j();
        }
    }

    View A1() {
        View childAt;
        CoordinatorLayout coordinatorLayout = this.P0;
        if (coordinatorLayout == null || (childAt = coordinatorLayout.getChildAt(0)) == null) {
            return null;
        }
        return childAt;
    }

    public void A2(Q q) {
        this.f1.remove(q);
    }

    public long B1() {
        if (this.q1 == 0) {
            this.q1 = ax.p390y3.a.b();
        }
        return this.q1;
    }

    public com.alphainventor.filemanager.ads.a.j C1() {
        com.alphainventor.filemanager.ads.a.j jVar;
        synchronized (this.E0) {
            jVar = this.o1;
        }
        return jVar;
    }

    public ax.p182l3.b D1() {
        return this.K1;
    }

    public void D2() {
        ArrayList<Q> arrayList = this.f1;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Q q = arrayList.get(i);
            i++;
            q.K();
        }
    }

    public C1903h.c E1() {
        return this.I1;
    }

    public void E2(long j) {
        this.q1 = j;
    }

    public AbstractC2067n F1() {
        Bookmark bookmark = this.d1;
        if (bookmark == null) {
            return null;
        }
        return (AbstractC2067n) getSupportFragmentManager().k0(y1(bookmark));
    }

    public void F2(com.alphainventor.filemanager.ads.a.j jVar) {
        synchronized (this.E0) {
            this.o1 = jVar;
        }
        if (jVar == com.alphainventor.filemanager.ads.a.j.LOADED) {
            if (ax.p390y3.a.n(this) && !AppOpenManager.p() && !ax.p406z3.z.t(this)) {
                AppOpenManager.n(this);
            }
            if (!ax.p390y3.a.q(this) || f2() || ax.p406z3.z.t(this)) {
                return;
            }
            if (ax.p390y3.a.g()) {
                com.alphainventor.filemanager.ads.a.A(this, new C());
            } else {
                this.r1 = com.alphainventor.filemanager.ads.a.B(this, (ax.p054d3.C.l(r()) && C1918x.x3(ax.p390y3.d.v().t())) ? C1918x.w3(this) : 300, new E());
            }
        }
    }

    public View G1() {
        return this.r1;
    }

    public long H1() {
        return this.p1;
    }

    public f I1() {
        if (this.V0 == null) {
            f fVar = new f(this);
            this.V0 = fVar;
            fVar.n();
        }
        return this.V0;
    }

    public void I2(long j) {
        this.p1 = j;
    }

    public k J1() {
        return this.H1;
    }

    public ax.p182l3.l K1(String str) {
        return new u(str);
    }

    void K2(Bookmark bookmark, Runnable runnable) {
        this.l1 = bookmark;
        this.k1 = runnable;
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void L0() {
        c3();
        invalidateOptionsMenu();
        w1(true);
    }

    void L2(Bookmark bookmark, Runnable runnable) {
        this.j1 = bookmark;
        this.i1 = runnable;
    }

    public void M2(String str, String str2) {
        h hVar;
        if (getSupportActionBar() == null || (hVar = this.b1) == null) {
            return;
        }
        hVar.j(str);
        if (str2 != null) {
            this.b1.h(str2);
        } else {
            this.b1.h(null);
        }
    }

    public void N2() {
        if (k()) {
            return;
        }
        C1904i.n3().m3(getSupportFragmentManager(), "add_sublocation");
    }

    public void O2() {
        if (k()) {
            return;
        }
        new C1902g().m3(getSupportFragmentManager(), "add_sublocation");
    }

    public void P2() {
        if (k()) {
            return;
        }
        C1904i.o3().m3(getSupportFragmentManager(), "add_sublocation");
    }

    public void S2(boolean z2) {
        Fragment fragmentK0 = getSupportFragmentManager().k0("permission_screen");
        androidx.fragment.app.t tVarO = getSupportFragmentManager().o();
        if (fragmentK0 != null) {
            tVarO.q(fragmentK0);
        }
        C2058g0 c2058g0H3 = C2058g0.h3(z2);
        this.z1 = c2058g0H3;
        tVarO.c(com.alphainventor.filemanager.R.id.root_view, c2058g0H3, "permission_screen");
        tVarO.j();
        this.F0.setVisibility(4);
    }

    public void T2(String str) {
        n2(Bookmark.m(this, ax.U2.f.u1, 0), str, null, null, null);
    }

    public void U2(ax.p086f3.K k, String str) {
        o2(k, true, Bookmark.m(this, ax.U2.f.u1, 0), str);
    }

    public void V2() {
        Object obj;
        if (this.u1 == null) {
            C2461b.g("rewarded ads null?");
            return;
        }
        try {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Fragment fragmentK0 = supportFragmentManager.k0("progress");
            if (fragmentK0 != null) {
                androidx.fragment.app.t tVarO = supportFragmentManager.o();
                tVarO.q(fragmentK0);
                tVarO.j();
            }
        } catch (Exception unused) {
        }
        R r = this.u1;
        if (r == R.LOADING) {
            this.u1 = R.LOADING_SHOW_ADS_AFTER_LOAD;
            I(new ax.p054d3.E(), "progress", true);
        } else {
            if (r == R.FAILED_TO_LOAD_NO_FILL) {
                s2();
                return;
            }
            if (r == R.FAILED_TO_LOAD_NETWORK) {
                Toast.makeText(this, com.alphainventor.filemanager.R.string.error_network, 1).show();
            } else {
                if (r != R.LOADED || (obj = this.t1) == null) {
                    return;
                }
                com.alphainventor.filemanager.ads.a.G(this, obj, this.L1);
            }
        }
    }

    public void W2(boolean z2, String str) {
        if (k()) {
            return;
        }
        ax.U2.a.k().o("ads", "rewarded_ads_open").c("from", str).e();
        C1909n c1909nU3 = C1909n.u3(z2 ? com.alphainventor.filemanager.R.string.remove_ads : com.alphainventor.filemanager.R.string.rewarded_video_title, com.alphainventor.filemanager.R.string.rewarded_video_message, com.alphainventor.filemanager.R.string.rewarded_video_watch, android.R.string.cancel);
        c1909nU3.m3(getSupportFragmentManager(), "rewardads");
        c1909nU3.x3(new F(str));
    }

    public void X1() {
        ViewGroup viewGroup = this.T0;
        if (viewGroup == null) {
            return;
        }
        viewGroup.setVisibility(8);
        w1(true);
        v1(true);
        this.a1.requestFocus();
        v2();
    }

    public void X2(ax.p086f3.K k, boolean z2, String str) {
        n2(Bookmark.n(this, ax.W2.h.A(k)), str, null, new C6209b(z2), null);
    }

    @Override // ax.g3.n0.o
    public void a(String str) {
        AbstractC2067n abstractC2067nF1 = F1();
        if ((abstractC2067nF1 instanceof ax.p102g3.Q) && abstractC2067nF1.O3().equals(str)) {
            ((ax.p102g3.Q) abstractC2067nF1).z9();
        }
    }

    public void b3(ax.U2.f fVar, int i) {
        this.U0.l(fVar, i);
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void c0(ax.U2.f fVar, int i, String str) {
        String strX1 = x1(fVar, i);
        Bookmark bookmark = this.d1;
        if (bookmark != null && y1(bookmark).equals(strX1)) {
            x2(true, str);
        } else {
            y2(strX1);
            this.U0.h(fVar, i);
        }
    }

    public boolean e2() {
        return F1() != null && F1().W3();
    }

    public boolean f2() {
        return this.r1 != null && this.s1;
    }

    @Override // com.alphainventor.filemanager.activity.b
    public AbstractC2067n g0() {
        return this.c1;
    }

    public boolean g2() {
        return this.A1;
    }

    @Override // ax.d3.W.c
    public void h(ax.U2.f fVar, String str) {
        if (fVar == ax.U2.f.Y0) {
            com.alphainventor.filemanager.file.D.I0(getApplicationContext()).k(this, str, this.J1);
        } else if (fVar == ax.U2.f.Z0) {
            com.alphainventor.filemanager.file.H.C0(getApplicationContext()).k(this, str, this.J1);
        } else {
            C2461b.f();
        }
    }

    @Override // com.alphainventor.filemanager.activity.b
    public h h0() {
        return this.b1;
    }

    public boolean h2() {
        C2058g0 c2058g0 = this.z1;
        if (c2058g0 == null) {
            return false;
        }
        return c2058g0.h1();
    }

    public boolean i2() {
        return this.u1 == R.LOADED && this.t1 != null;
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z2) {
        if (ax.p390y3.a.o() || p0()) {
            return true;
        }
        ax.C3.v vVar = this.S0;
        if (vVar != null && vVar.m()) {
            return true;
        }
        View view = this.R0;
        return view != null && view.getVisibility() == 0;
    }

    public boolean j2() {
        ViewGroup viewGroup = this.T0;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    @Override // com.alphainventor.filemanager.activity.b
    public ax.C3.v k0() {
        if (this.S0 == null) {
            ax.C3.v vVar = new ax.C3.v(this, findViewById(com.alphainventor.filemanager.R.id.paste_layout), findViewById(com.alphainventor.filemanager.R.id.paste_layout_safe_area));
            this.S0 = vVar;
            vVar.D();
            this.S0.v(new A());
        }
        return this.S0;
    }

    @Override // ax.d3.O.c
    public void m() {
        d dVar = this.L0;
        if (dVar != null) {
            dVar.a0();
        }
    }

    public void m1(Q q) {
        this.f1.add(q);
    }

    public void n2(Bookmark bookmark, String str, ax.p038c3.f fVar, O o, N n) {
        Bundle bundle;
        if (this.h1) {
            ax.p302sb.c.h().d("OPEN_BOOKMARK_AFTER_DESTORY").k().h("from:" + str).i();
            return;
        }
        ax.U2.f fVarT = bookmark.t();
        int iU = bookmark.u();
        if (fVarT == ax.U2.f.d1) {
            O2();
            return;
        }
        if (fVarT == ax.U2.f.e1) {
            startActivity(new Intent(this, (Class<?>) PaymentActivity.class));
            return;
        }
        if (fVarT == ax.U2.f.c1) {
            p2(ax.p086f3.B.U(bookmark.v(), bookmark.x()), null);
            return;
        }
        if (fVarT == ax.U2.f.f1) {
            startActivityForResult(new Intent(this, (Class<?>) DevSettingsActivity.class), 10004);
            return;
        }
        Bundle bundle2 = new Bundle();
        if (fVar != null && fVar.c()) {
            bundle2.putSerializable("parent_location", fVar.a());
        }
        if (fVar != null && (bundle = fVar.Y) != null) {
            bundle2.putAll(bundle);
        }
        AbstractC2067n abstractC2067nZ2 = Z2(fVarT, iU, bundle2, fVar);
        if (str != null) {
            M1.fine("Open location : " + fVarT.H());
            ax.U2.a.k().p("navigation", "open_location").c("loc", fVarT.H()).c("from", str).e();
        }
        if (abstractC2067nZ2 != null) {
            if (o != null) {
                o.a(abstractC2067nZ2);
            }
            if (n != null) {
                L2(bookmark, new x(n, abstractC2067nZ2));
            }
            if (bookmark.x() == null && bookmark.v().e() != null) {
                if (bookmark.t() != ax.U2.f.a1) {
                    ax.p302sb.c.h().g().d("!!INVLIAD OPEN PATH!!").k().h(bookmark.t().H() + "," + bookmark.A()).i();
                }
                bookmark.G(bookmark.v().e());
            }
            if (bookmark.B()) {
                abstractC2067nZ2.m4(bookmark.x());
            } else {
                Bookmark bookmarkF = Bookmark.f(bookmark);
                bookmarkF.G(d0.r(bookmark.x()));
                K2(bookmarkF, new y(abstractC2067nZ2, bookmark));
                abstractC2067nZ2.m4(bookmarkF.x());
            }
            abstractC2067nZ2.C4(fVar);
            G2(bookmark, fVar, abstractC2067nZ2);
            M0();
        }
        c3();
    }

    public void o2(ax.p086f3.K k, boolean z2, Bookmark bookmark, String str) {
        if (k.d() == ax.U2.f.n1) {
            n2(bookmark, str, new ax.p038c3.f(k, z2), new w(), null);
        } else {
            n2(bookmark, str, new ax.p038c3.f(k, z2), null, null);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 != -1 || intent == null || intent.getExtras() == null || (stringExtra = intent.getStringExtra("authAccount")) == null) {
                return;
            }
            com.alphainventor.filemanager.file.r.i0(getApplicationContext()).k(stringExtra, this.J1);
            return;
        }
        if (i != 10004) {
            if (i != 10006) {
                return;
            }
            com.alphainventor.filemanager.file.I.Z(getApplicationContext()).u(i2, intent);
        } else {
            int iJ = m.j();
            if (this.g1 != iJ) {
                this.g1 = iJ;
                c3();
            }
        }
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z2) {
        if (z2) {
            this.J0.setPadding(0, 0, 0, 0);
        } else {
            this.J0.setPadding(0, 0, 0, bVar.d);
        }
    }

    @Override // androidx.fragment.app.f
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override // com.alphainventor.filemanager.activity.b, ax.p210n.ActivityC2164c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C2163b c2163b = this.G0;
        if (c2163b != null) {
            c2163b.f(configuration);
        }
        ax.p406z3.z.c(this);
        L();
    }

    @Override // com.alphainventor.filemanager.activity.b, com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        MainActivity mainActivity;
        Xpk8a.StartGame(this);
        LB.ll(this);
        super.onCreate(bundle);
        setContentView(com.alphainventor.filemanager.R.layout.activity_main);
        if (ax.p006a3.Q.N1() && ax.p406z3.z.v(this)) {
            ax.p006a3.v.r(getWindow(), -16777216);
        }
        if (getSupportFragmentManager().v0() != null) {
            androidx.fragment.app.t tVarO = getSupportFragmentManager().o();
            for (Fragment fragment : getSupportFragmentManager().v0()) {
                if (fragment instanceof AbstractC2067n) {
                    tVarO.p(fragment);
                } else if (fragment instanceof C2058g0) {
                    tVarO.q(fragment);
                }
            }
            tVarO.j();
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(com.alphainventor.filemanager.R.id.drawer_layout);
        this.F0 = drawerLayout;
        drawerLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserverOnPreDrawListenerC6218k());
        this.P0 = (CoordinatorLayout) findViewById(com.alphainventor.filemanager.R.id.ad_container);
        this.Q0 = (CoordinatorLayout) findViewById(com.alphainventor.filemanager.R.id.activity_message_container);
        this.Z0 = (ViewGroup) findViewById(com.alphainventor.filemanager.R.id.toolbar_container);
        this.a1 = (ViewGroup) findViewById(com.alphainventor.filemanager.R.id.fragment_container);
        this.H0 = findViewById(com.alphainventor.filemanager.R.id.tab_view);
        this.I0 = (TabLayout) findViewById(com.alphainventor.filemanager.R.id.tab_layout);
        this.J0 = (FrameLayout) findViewById(com.alphainventor.filemanager.R.id.tab_content);
        this.I0.s();
        this.I0.h(new v());
        h hVar = new h(this, this.Z0);
        this.b1 = hVar;
        hVar.l(new D());
        n0();
        this.g1 = m.j();
        this.U0 = new l(this);
        d2();
        if (bundle == null) {
            mainActivity = this;
            mainActivity.n2(Bookmark.l(this, ax.U2.f.q0), "on_create", null, null, null);
            getSupportFragmentManager().g0();
            P1(getIntent());
        } else {
            mainActivity = this;
            c3();
        }
        getOnBackPressedDispatcher().h(this, mainActivity.F1);
        new P(this).h(new Long[0]);
        w2();
        ax.p166k3.e.w().p();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || menu == this.b1.d()) {
            return super.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.activity.b, com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        this.h1 = true;
        super.onDestroy();
        q1();
        a3();
        t1();
        p1();
        View viewA1 = A1();
        if (viewA1 != null) {
            com.alphainventor.filemanager.ads.a.j(viewA1, this);
            this.P0.removeAllViews();
        }
    }

    @Override // ax.p210n.ActivityC2164c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (F1() == null || !F1().l4(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (i != 4 || F1() == null) {
            return super.onKeyLongPress(i, keyEvent);
        }
        t0("long_back");
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ax.p406z3.z.c(this);
        P1(intent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        C2163b c2163b = this.G0;
        if (c2163b != null && c2163b.g(menuItem)) {
            return true;
        }
        switch (menuItem.getItemId()) {
            case com.alphainventor.filemanager.R.id.menu_refresh /* 2131362532 */:
                ax.U2.a.k().o("menu_folder", "refresh").c("loc", this.d1.t().H()).e();
                AbstractC2067n abstractC2067nF1 = F1();
                if (abstractC2067nF1 != null) {
                    abstractC2067nF1.p4(true);
                    break;
                }
            case android.R.id.home:
                return true;
            case com.alphainventor.filemanager.R.id.menu_settings /* 2131362546 */:
                ax.U2.a.k().o("menu_desktop", "general_settings").e();
                startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
            case com.alphainventor.filemanager.R.id.menu_search /* 2131362540 */:
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        super.onPause();
        View view = this.r1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.v(view, this);
        }
        View viewA1 = A1();
        if (viewA1 != null) {
            com.alphainventor.filemanager.ads.a.v(viewA1, this);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        C2163b c2163b = this.G0;
        if (c2163b != null) {
            c2163b.m();
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // com.alphainventor.filemanager.activity.a, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.d1 = (Bookmark) bundle.getSerializable("CURRENT_BOOKMARK");
        this.e1 = (ax.p038c3.f) bundle.getParcelable("CURRENT_EXTRAINFO");
        ArrayList<Bookmark> arrayList = (ArrayList) bundle.getSerializable("OPEN_BOOKMARK");
        ArrayList<ax.p038c3.f> arrayList2 = (ArrayList) bundle.getSerializable("OPEN_EXTRAINFOS");
        if (arrayList != null) {
            this.U0.i(arrayList);
        }
        if (arrayList2 != null) {
            this.U0.j(arrayList2);
        }
        Bookmark bookmark = this.d1;
        if (bookmark != null) {
            n2(bookmark, "on_restore_instance", this.e1, null, null);
        }
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        C6236k.a0(getApplicationContext()).p(this);
        u2();
        HttpServerService.v(this);
        View view = this.r1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.F(view, this);
        }
        View viewA1 = A1();
        if (viewA1 != null) {
            com.alphainventor.filemanager.ads.a.F(viewA1, this);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putSerializable("CURRENT_BOOKMARK", this.d1);
            bundle.putParcelable("CURRENT_EXTRAINFO", this.e1);
            bundle.putSerializable("OPEN_BOOKMARK", this.U0.e());
            bundle.putSerializable("OPEN_EXTRAINFOS", this.U0.g());
        } catch (IllegalStateException e) {
            ax.p302sb.c.h().d("MainActivity onSaveInstanceState Error").m(e).i();
        }
        try {
            s1(bundle);
        } catch (Exception unused) {
        }
    }

    @Override // com.alphainventor.filemanager.activity.b, com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        ax.Y2.g gVar = this.Y0;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
    }

    @Override // com.alphainventor.filemanager.activity.b, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        super.onStop();
        I1().s();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00a1  */
    public void p2(String str, Runnable runnable) {
        C6227b c6227b;
        Uri uriW0;
        Uri uri = Uri.parse(str);
        if (ax.p038c3.k.g(uri, ax.U2.f.c1)) {
            ax.p038c3.k kVarA = ax.p038c3.k.a(uri);
            C6240o c6240oE = C2017x.e(kVarA.d());
            boolean zA = c6240oE.a();
            if (zA) {
                C6227b c6227b2 = (C6227b) c6240oE.u();
                c6227b = c6227b2;
                uriW0 = c6227b2.w0();
            } else {
                uriW0 = C6227b.x0(kVarA.d());
                c6227b = null;
            }
            if (uriW0 == null) {
                return;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setComponent(new ComponentName(this, (Class<?>) ArchiveActivity.class));
                intent.putExtra("data_uri", uriW0);
                intent.putExtra("open_uri", uri);
                if (zA) {
                    c6240oE.V();
                    intent.putExtra("open_uri_release", true);
                }
                startActivity(intent);
            } catch (ActivityNotFoundException unused) {
            }
            if (c6227b != null && c6227b.v0() == 1 && c6227b.D0() != null) {
                M1.fine("Open local file archive viewer : " + uriW0);
                C6249y c6249yD0 = c6227b.D0();
                if (ax.p038c3.a.p(this, c6249yD0.U())) {
                    uri = null;
                } else {
                    uri = Uri.parse(c6249yD0.T());
                }
            } else if (C2014u.P(uriW0)) {
                String strR = d0.r(uriW0.getPath());
                if (ax.p038c3.a.p(this, strR)) {
                    uri = null;
                } else {
                    uri = Uri.parse(ax.p086f3.B.U(C2017x.g(strR).B(), strR));
                }
            } else if (MyFileProvider.B(uriW0) || MyFileProvider.A(uriW0)) {
                M1.fine("Open document file archive viewer : " + uriW0);
                ax.p038c3.k kVarE = MyFileProvider.e(uriW0);
                if (kVarE == null) {
                    return;
                } else {
                    uri = Uri.parse(ax.p086f3.B.U(kVarE.d(), d0.r(kVarE.e())));
                }
            } else {
                uri = null;
            }
        }
        if (uri == null) {
            return;
        }
        Bookmark bookmarkC = Bookmark.c(this, uri);
        if (bookmarkC != null) {
            n2(bookmarkC, "location_uri", null, null, runnable != null ? new z(runnable) : null);
            return;
        }
        throw new IllegalArgumentException("Illegal location uri : " + uri.toString());
    }

    public void q1() {
        View view = this.r1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.j(view, this);
            this.r1 = null;
        }
    }

    public void q2(ax.p086f3.K k, Bundle bundle) {
        n2(Bookmark.m(this, ax.U2.f.x1, k.hashCode()), "search", new ax.p038c3.f(k, false, bundle), null, null);
    }

    public void r2(ax.p038c3.s sVar, ax.p086f3.K k, String str) {
        Bookmark bookmarkO = Bookmark.o(sVar);
        if (k != null) {
            o2(k, false, bookmarkO, str);
        } else {
            n2(bookmarkO, str, null, null, null);
        }
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void s0() {
        k2();
        h0().a();
        w1(false);
    }

    public void s2() {
        ax.p390y3.a.l(this);
        ax.p406z3.g.a().e(new Intent("local.intent.action.LICENSE_STATUS_CHANGED"));
        this.u1 = R.NONE;
        t1();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void t0(String str) {
        if (this.F0.D(this.H0)) {
            this.F0.f(this.H0);
        }
        Bookmark bookmarkL = Bookmark.l(this, ax.U2.f.q0);
        bookmarkL.G("/moveToHome");
        n2(bookmarkL, str, null, null, null);
    }

    public void t2(ax.U2.f fVar, int i) {
        u2();
        b3(fVar, i);
        H2(this.d1);
    }

    @Override // ax.p182l3.e
    public void u(ax.p086f3.K k) {
        if (k.d() != ax.U2.f.u0 || j.F().u0()) {
            n2(Bookmark.n(getApplicationContext(), k), "home", null, new B(), null);
        } else if (j.F().w0()) {
            O(k);
        } else {
            Toast.makeText(this, com.alphainventor.filemanager.R.string.storage_not_available, 1).show();
        }
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void u0(Bookmark bookmark) {
        Boolean bool = this.x1;
        if (bool != null && bool.booleanValue()) {
            ax.Y2.a.e(this).b(bookmark);
        }
        if (bookmark.B()) {
            I1().a(bookmark);
        }
    }

    public void u2() {
        D2();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void w0(ax.U2.f fVar, int i, String str, boolean z2) {
        Bookmark bookmark = this.l1;
        if (bookmark != null && bookmark.t() == fVar && this.l1.u() == i && this.l1.x().equals(str)) {
            this.k1.run();
            this.k1 = null;
            this.l1 = null;
        }
    }

    public void x2(boolean z2, String str) {
        if (this.F0.D(this.H0)) {
            this.F0.f(this.H0);
        }
        Bookmark bookmark = this.d1;
        ax.p038c3.f fVar = this.e1;
        if (bookmark != null) {
            y2(y1(bookmark));
            this.U0.h(bookmark.t(), bookmark.u());
        }
        n2(Bookmark.l(this, ax.U2.f.q0), str, null, null, null);
        if (bookmark == null || !z2) {
            return;
        }
        R2(bookmark, fVar);
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void z0(ax.U2.f fVar, int i, String str, boolean z2) {
        int iC;
        if (z2 && (iC = this.U0.c(fVar, i)) >= 0) {
            this.U0.k(fVar, i, str);
            I1().c(this.U0.d(iC));
            ax.Y2.g gVar = this.Y0;
            if (gVar != null) {
                gVar.notifyDataSetChanged();
            }
            M0();
            v2();
        }
        Bookmark bookmark = this.j1;
        if (bookmark != null && bookmark.t() == fVar && this.j1.u() == i && this.j1.x().equals(str)) {
            this.i1.run();
            this.i1 = null;
            this.j1 = null;
        }
    }

    public CoordinatorLayout z1() {
        return this.P0;
    }

    public void z2() {
        if (this.z1 == null) {
            return;
        }
        androidx.fragment.app.t tVarO = getSupportFragmentManager().o();
        tVarO.q(this.z1);
        tVarO.j();
        this.z1 = null;
        this.F0.setVisibility(0);
    }
}
