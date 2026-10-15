package com.alphainventor.filemanager.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import ax.U2.f;
import ax.U2.g;
import ax.p086f3.C2017x;
import ax.p374x3.w;
import ax.p406z3.B;
import ax.p406z3.D;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6240o;
import com.example.android.uamp.MusicService;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class HttpServerService extends Service {
    private static final Logger t0 = g.a(HttpServerService.class);
    private static boolean u0 = false;
    private static HttpServerService v0 = null;
    private static boolean w0 = false;
    private boolean X;
    private boolean Y;
    private int l0;
    private boolean m0;
    private long n0;
    private D o0;
    private e q;
    boolean r0;
    private Handler Z = new Handler(Looper.getMainLooper());
    private Set<C6240o> k0 = new ax.B.b();
    private com.alphainventor.filemanager.service.b.a p0 = new a();
    Runnable q0 = new b();
    BroadcastReceiver s0 = new c();

    class a implements com.alphainventor.filemanager.service.b.a {
        a() {
        }

        @Override // com.alphainventor.filemanager.service.b.a
        public void a(int i) {
            if (i == 0) {
                HttpServerService.this.q = e.STARTED_NO_CLIENT;
                HttpServerService.this.r();
            } else {
                HttpServerService.this.q = e.STARTED_RUNNING_CLIENT;
                if (i > HttpServerService.this.l0) {
                    HttpServerService.this.m();
                }
            }
            HttpServerService.this.l0 = i;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpServerService.t0.fine("Timeout http multimedia server! : " + HttpServerService.this.q);
            HttpServerService httpServerService = HttpServerService.this;
            if (httpServerService.r0 && httpServerService.q != e.STARTED_RUNNING_CLIENT) {
                if (HttpServerService.this.X && MusicService.F()) {
                    HttpServerService.this.r();
                    return;
                } else if (HttpServerService.w0) {
                    HttpServerService.this.u();
                    return;
                } else {
                    HttpServerService.this.w();
                    HttpServerService.this.m();
                    return;
                }
            }
            HttpServerService httpServerService2 = HttpServerService.this;
            if (!httpServerService2.r0 || !httpServerService2.Y || HttpServerService.this.X || MusicService.D()) {
                HttpServerService.this.k();
            } else if (HttpServerService.w0) {
                HttpServerService.this.u();
            } else {
                HttpServerService.this.w();
                HttpServerService.this.m();
            }
        }
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("state", 0);
            HttpServerService.t0.fine("music playback stop received : " + HttpServerService.this.X + ":" + intExtra);
            if (HttpServerService.this.X) {
                if (intExtra == 1) {
                    HttpServerService.this.X = false;
                }
                HttpServerService.this.s();
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpServerService.this.stopSelf();
        }
    }

    public enum e {
        NOT_STARTED,
        STARTED_NO_CLIENT,
        STARTED_RUNNING_CLIENT
    }

    public static Uri l(int i, AbstractC6239n abstractC6239n) {
        return new Uri.Builder().scheme("http").encodedAuthority("127.0.0.1:" + i).path(ax.p310t3.c.C(abstractC6239n)).build();
    }

    private Notification n(Intent intent, boolean z) {
        return w.j(this).e(this, intent, z);
    }

    public static e p() {
        return u0 ? v0.o() : e.NOT_STARTED;
    }

    public static void q(Context context, f fVar, int i, boolean z, boolean z2, boolean z3, Intent intent) {
        Intent intent2 = new Intent(context, (Class<?>) HttpServerService.class);
        intent2.putExtra("location", fVar);
        intent2.putExtra("location_key", i);
        intent2.putExtra("play_intent", intent);
        intent2.putExtra("music_playback", z);
        intent2.putExtra("is_multimedia", z2);
        if (!z3 && B.V(context)) {
            z3 = true;
        }
        intent2.putExtra("need_foreground", z3);
        if (!z3) {
            context.startService(intent2);
            return;
        }
        try {
            B.f0(context, intent2, true, true);
            w0 = true;
        } catch (IllegalStateException e2) {
            ax.p302sb.c.i(context).g().b("START HTTP SERVICE FOREGROUND").m(e2).i();
        }
    }

    public static void v(Context context) {
        HttpServerService httpServerService;
        int iOrdinal = p().ordinal();
        if (iOrdinal == 0) {
            HttpServerService httpServerService2 = v0;
            if (httpServerService2 != null) {
                httpServerService2.t();
                return;
            }
            return;
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2 && (httpServerService = v0) != null) {
                httpServerService.t();
                return;
            }
            return;
        }
        HttpServerService httpServerService3 = v0;
        if (httpServerService3 != null && httpServerService3.X && MusicService.F()) {
            v0.r();
            return;
        }
        HttpServerService httpServerService4 = v0;
        if (httpServerService4 != null) {
            httpServerService4.r();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.n0 <= 0) {
            stopSelf();
        } else if (SystemClock.uptimeMillis() - this.n0 < 100) {
            this.Z.postDelayed(new d(), 100L);
        } else {
            stopSelf();
        }
    }

    void k() {
        if (this.X || !ax.U2.b.h().m()) {
            if (this.X) {
                return;
            }
            ax.U2.b.h().m();
        } else if (this.m0) {
            stopForeground(true);
            this.m0 = false;
        }
    }

    synchronized void m() {
        if (this.r0) {
            t0.fine("Cancel timeout to stop multimedia server");
            this.Z.removeCallbacks(this.q0);
            this.r0 = false;
        }
    }

    e o() {
        return this.q;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        t0.fine("Http server created");
        this.q = e.NOT_STARTED;
        this.r0 = false;
        ax.L0.a.b(this).c(this.s0, new IntentFilter("local.intent.action.LOCAL_PLAYBACK_STOP"));
        this.o0 = new D(this, 3, "HTTP_SERVER");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        m();
        com.alphainventor.filemanager.service.b.f(this).n(this.p0);
        Iterator<C6240o> it = this.k0.iterator();
        while (it.hasNext()) {
            com.alphainventor.filemanager.service.b.f(this).o(it.next());
        }
        this.k0.clear();
        if (this.m0) {
            stopForeground(true);
            this.m0 = false;
        }
        u0 = false;
        v0 = null;
        ax.L0.a.b(this).f(this.s0);
        this.o0.c();
        t0.fine("Http server stopped");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Intent intent2;
        boolean booleanExtra;
        boolean booleanExtra2;
        if (intent == null) {
            intent2 = null;
            booleanExtra = true;
            booleanExtra2 = true;
        } else {
            intent2 = (Intent) intent.getParcelableExtra("play_intent");
            booleanExtra = intent.getBooleanExtra("is_multimedia", true);
            booleanExtra2 = intent.getBooleanExtra("need_foreground", true);
        }
        if (booleanExtra2) {
            try {
                w0 = false;
                startForeground(232, n(intent2, booleanExtra));
                this.n0 = SystemClock.uptimeMillis();
                this.m0 = true;
            } catch (IllegalStateException unused) {
                ax.p302sb.c.i(this).g().b("Foreground not allowed : http server service").i();
            }
        }
        v0 = this;
        u0 = true;
        if (intent == null) {
            if (!com.alphainventor.filemanager.service.b.f(this).i()) {
                w();
            }
            return 2;
        }
        f fVar = (f) intent.getSerializableExtra("location");
        int intExtra = intent.getIntExtra("location_key", 0);
        if (fVar == null) {
            ax.p302sb.c.i(this).g().b("HTTP SERVER NO LOCATION").k().h("op:" + com.alphainventor.filemanager.service.b.f(this).i()).i();
            if (!com.alphainventor.filemanager.service.b.f(this).i()) {
                w();
            }
            return 2;
        }
        C6240o c6240oD = C2017x.d(fVar, intExtra);
        if (intent.getBooleanExtra("music_playback", false)) {
            this.X = true;
            if (this.k0.size() == 0) {
                this.Y = true;
            }
        } else {
            this.Y = false;
        }
        if (!this.k0.contains(c6240oD)) {
            this.k0.add(c6240oD);
            com.alphainventor.filemanager.service.b.f(this).e(c6240oD);
        }
        com.alphainventor.filemanager.service.b.f(this).d(this.p0);
        m();
        this.q = e.NOT_STARTED;
        r();
        this.o0.a();
        return 2;
    }

    public void onTimeout(int i, int i2) {
        super.onTimeout(i, i2);
        ax.U2.d.c("http server service timeout", new Exception("HttpServerServiceTimeout"));
        stopForeground(1);
    }

    synchronized void r() {
        try {
            if (this.q == e.NOT_STARTED) {
                t0.fine("Start timeout to stop multimedia server : onstart : (music:" + this.X + ")");
                this.Z.postDelayed(this.q0, 180000L);
            } else if (this.X) {
                t0.fine("Start timeout to stop multimedia server : music playback pause");
                if (this.m0) {
                    this.Z.postDelayed(this.q0, 600000L);
                } else {
                    this.Z.postDelayed(this.q0, 1800000L);
                }
            } else if (ax.U2.b.h().m()) {
                t0.fine("Start timeout to stop multimedia server : foreground");
                this.Z.postDelayed(this.q0, 3000L);
            } else {
                t0.fine("Start timeout to stop multimedia server : background");
                this.Z.postDelayed(this.q0, 600000L);
            }
            this.r0 = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized void s() {
        t0.fine("Start timeout to stop multimedia server : music playback stop");
        this.Z.postDelayed(this.q0, 3000L);
        this.r0 = true;
    }

    synchronized void t() {
        t0.fine("Start timeout to stop multimedia server : onResume UI");
        this.Z.postDelayed(this.q0, 3000L);
        this.r0 = true;
    }

    synchronized void u() {
        t0.fine("Start timeout to stop multimedia server : wating foreground");
        this.Z.postDelayed(this.q0, 30000L);
        this.r0 = true;
    }
}
