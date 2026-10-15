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
import ax.J1.r;
import ax.p017b2.w;
import ax.p045d2.x;
import ax.p045d2.z;
import ax.p338y1.f;
import ax.p338y1.g;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5839m;
import com.example.android.uamp.MusicService;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class HttpServerService extends Service {
    private static final Logger p0 = g.a(HttpServerService.class);
    private static boolean q0 = false;
    private static HttpServerService r0 = null;
    private boolean X;
    private boolean Y;
    private int i0;
    private boolean j0;
    private z k0;
    boolean n0;
    private e q;
    private Handler Z = new Handler(Looper.getMainLooper());
    private Set<C5839m> h0 = new ax.B.b();
    private com.alphainventor.filemanager.service.b.a l0 = new a();
    Runnable m0 = new b();
    BroadcastReceiver o0 = new c();

    class a implements com.alphainventor.filemanager.service.b.a {
        a() {
        }

        @Override // com.alphainventor.filemanager.service.b.a
        public void a(int i) {
            if (i == 0) {
                HttpServerService.this.q = e.STARTED_NO_CLIENT;
                HttpServerService.this.p();
            } else {
                HttpServerService.this.q = e.STARTED_RUNNING_CLIENT;
                if (i > HttpServerService.this.i0) {
                    HttpServerService.this.k();
                }
            }
            HttpServerService.this.i0 = i;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpServerService.p0.fine("Timeout http multimedia server! : " + HttpServerService.this.q);
            HttpServerService httpServerService = HttpServerService.this;
            if (httpServerService.n0 && httpServerService.q != e.STARTED_RUNNING_CLIENT) {
                if (HttpServerService.this.X && MusicService.E()) {
                    HttpServerService.this.p();
                    return;
                } else {
                    HttpServerService.this.stopSelf();
                    HttpServerService.this.k();
                    return;
                }
            }
            HttpServerService httpServerService2 = HttpServerService.this;
            if (!httpServerService2.n0 || !httpServerService2.Y || HttpServerService.this.X || MusicService.C()) {
                HttpServerService.this.i();
            } else {
                HttpServerService.this.stopSelf();
                HttpServerService.this.k();
            }
        }
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("state", 0);
            HttpServerService.p0.fine("music playback stop received : " + HttpServerService.this.X + ":" + intExtra);
            if (HttpServerService.this.X) {
                if (intExtra == 1) {
                    HttpServerService.this.X = false;
                }
                HttpServerService.this.q();
            }
        }
    }

    static /* synthetic */ class d {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[e.values().length];
            a = iArr;
            try {
                iArr[e.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.STARTED_NO_CLIENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[e.STARTED_RUNNING_CLIENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum e {
        NOT_STARTED,
        STARTED_NO_CLIENT,
        STARTED_RUNNING_CLIENT
    }

    public static Uri j(int i, AbstractC5838l abstractC5838l) {
        return new Uri.Builder().scheme("http").encodedAuthority("127.0.0.1:" + i).path(ax.X1.b.C(abstractC5838l)).build();
    }

    private Notification l(Intent intent, boolean z) {
        return w.j(this).e(intent, z);
    }

    public static e n() {
        return q0 ? r0.m() : e.NOT_STARTED;
    }

    public static void o(Context context, f fVar, int i, boolean z, boolean z2, boolean z3, Intent intent) {
        Intent intent2 = new Intent(context, (Class<?>) HttpServerService.class);
        intent2.putExtra("location", fVar);
        intent2.putExtra("location_key", i);
        intent2.putExtra("play_intent", intent);
        intent2.putExtra("music_playback", z);
        intent2.putExtra("is_multimedia", z2);
        if (!z3 && x.X(context)) {
            z3 = true;
        }
        intent2.putExtra("need_foreground", z3);
        if (!z3) {
            context.startService(intent2);
            return;
        }
        try {
            x.f0(context, intent2, true, true);
        } catch (IllegalStateException e2) {
            ax.p221pa.c.i(context).g().b("START HTTP SERVICE FOREGROUND").m(e2).i();
        }
    }

    public static void s(Context context) {
        HttpServerService httpServerService;
        int i = d.a[n().ordinal()];
        if (i == 1) {
            HttpServerService httpServerService2 = r0;
            if (httpServerService2 != null) {
                httpServerService2.r();
                return;
            }
            return;
        }
        if (i != 2) {
            if (i == 3 && (httpServerService = r0) != null) {
                httpServerService.r();
                return;
            }
            return;
        }
        HttpServerService httpServerService3 = r0;
        if (httpServerService3 != null && httpServerService3.X && MusicService.E()) {
            r0.p();
            return;
        }
        HttpServerService httpServerService4 = r0;
        if (httpServerService4 != null) {
            httpServerService4.p();
        }
    }

    void i() {
        if (this.X || !ax.p338y1.b.h().m()) {
            if (this.X) {
                return;
            }
            ax.p338y1.b.h().m();
        } else if (this.j0) {
            stopForeground(true);
            this.j0 = false;
        }
    }

    synchronized void k() {
        if (this.n0) {
            p0.fine("Cancel timeout to stop multimedia server");
            this.Z.removeCallbacks(this.m0);
            this.n0 = false;
        }
    }

    e m() {
        return this.q;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        p0.fine("Http server created");
        this.q = e.NOT_STARTED;
        this.n0 = false;
        ax.K0.a.b(this).c(this.o0, new IntentFilter("local.intent.action.LOCAL_PLAYBACK_STOP"));
        this.k0 = new z(this, 3, "HTTP_SERVER");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        k();
        com.alphainventor.filemanager.service.b.f(this).n(this.l0);
        Iterator<C5839m> it = this.h0.iterator();
        while (it.hasNext()) {
            com.alphainventor.filemanager.service.b.f(this).o(it.next());
        }
        this.h0.clear();
        if (this.j0) {
            stopForeground(true);
            this.j0 = false;
        }
        q0 = false;
        r0 = null;
        ax.K0.a.b(this).f(this.o0);
        this.k0.c();
        p0.fine("Http server stopped");
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
                startForeground(232, l(intent2, booleanExtra));
                this.j0 = true;
            } catch (IllegalStateException unused) {
                ax.p221pa.c.i(this).g().b("Foreground not allowed : http server service").i();
            }
        }
        r0 = this;
        q0 = true;
        if (intent == null) {
            if (!com.alphainventor.filemanager.service.b.f(this).i()) {
                stopSelf();
            }
            return 2;
        }
        f fVar = (f) intent.getSerializableExtra("location");
        int intExtra = intent.getIntExtra("location_key", 0);
        if (fVar == null) {
            ax.p221pa.c.i(this).g().b("HTTP SERVER NO LOCATION").k().h("op:" + com.alphainventor.filemanager.service.b.f(this).i()).i();
            if (!com.alphainventor.filemanager.service.b.f(this).i()) {
                stopSelf();
            }
            return 2;
        }
        C5839m c5839mE = r.e(fVar, intExtra);
        if (intent.getBooleanExtra("music_playback", false)) {
            this.X = true;
            if (this.h0.size() == 0) {
                this.Y = true;
            }
        } else {
            this.Y = false;
        }
        if (!this.h0.contains(c5839mE)) {
            this.h0.add(c5839mE);
            com.alphainventor.filemanager.service.b.f(this).e(c5839mE);
        }
        com.alphainventor.filemanager.service.b.f(this).d(this.l0);
        k();
        this.q = e.NOT_STARTED;
        p();
        this.k0.a();
        return 2;
    }

    synchronized void p() {
        try {
            if (this.q == e.NOT_STARTED) {
                p0.fine("Start timeout to stop multimedia server : onstart : (music:" + this.X + ")");
                this.Z.postDelayed(this.m0, 180000L);
            } else if (this.X) {
                p0.fine("Start timeout to stop multimedia server : music playback pause");
                if (this.j0) {
                    this.Z.postDelayed(this.m0, 600000L);
                } else {
                    this.Z.postDelayed(this.m0, 1800000L);
                }
            } else if (ax.p338y1.b.h().m()) {
                p0.fine("Start timeout to stop multimedia server : foreground");
                this.Z.postDelayed(this.m0, 3000L);
            } else {
                p0.fine("Start timeout to stop multimedia server : background");
                this.Z.postDelayed(this.m0, 600000L);
            }
            this.n0 = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized void q() {
        p0.fine("Start timeout to stop multimedia server : music playback stop");
        this.Z.postDelayed(this.m0, 3000L);
        this.n0 = true;
    }

    synchronized void r() {
        p0.fine("Start timeout to stop multimedia server : onResume UI");
        this.Z.postDelayed(this.m0, 3000L);
        this.n0 = true;
    }
}
