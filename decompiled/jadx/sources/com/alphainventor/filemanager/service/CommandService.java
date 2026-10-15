package com.alphainventor.filemanager.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.f;
import androidx.fragment.app.m;
import ax.D1.h;
import ax.D1.y;
import ax.E1.P;
import ax.H1.AbstractC0698h;
import ax.H1.x;
import ax.I1.C0711b;
import ax.J1.C0734o;
import ax.J1.F;
import ax.K1.AbstractC0762l;
import ax.p017b2.w;
import ax.p045d2.z;
import ax.p338y1.g;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.activity.FileProgressActivity;
import com.alphainventor.filemanager.activity.MainActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class CommandService extends Service {
    private static final Logger u0 = g.a(CommandService.class);
    private static long v0;
    private static long w0;
    private static CommandService x0;
    private FileProgressActivity X;
    private ArrayList<h> Y;
    private ArrayList<h> Z;
    private int i0;
    private long j0;
    private Thread l0;
    private z m0;
    private boolean n0;
    private boolean o0;
    private boolean p0;
    private HashMap<h, c> q;
    private long q0;
    private boolean r0;
    private Runnable s0;
    private final IBinder h0 = new d();
    private final Object k0 = new Object();
    private Handler t0 = new Handler(Looper.getMainLooper());

    class a implements ServiceConnection {
        final /* synthetic */ f X;
        final /* synthetic */ Fragment Y;
        final /* synthetic */ boolean Z;
        final /* synthetic */ h q;

        a(h hVar, f fVar, Fragment fragment, boolean z) {
            this.q = hVar;
            this.X = fVar;
            this.Y = fragment;
            this.Z = z;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CommandService commandServiceA = ((d) iBinder).a();
            if (!commandServiceA.w()) {
                String str = "cmd:" + this.q.getClass().getName() + "," + this.q.p() + "," + commandServiceA.s();
                ax.p221pa.c.h().g().b("COMMAND SERVICE IS NOT STARTED").h(str).i();
                ax.p045d2.b.g(str);
            }
            commandServiceA.O(this.X, this.Y, this.q, this.Z);
            this.X.unbindService(this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    class b implements Runnable {
        final /* synthetic */ long q;

        b(long j) {
            this.q = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CommandService.this.n0) {
                if (CommandService.this.o0) {
                    ax.p045d2.b.g("CommandService started but destroyed");
                    return;
                } else {
                    if (CommandService.this.q0 == this.q) {
                        CommandService.this.q0 = 0L;
                        CommandService.this.h();
                        return;
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("current service null=");
            sb.append(CommandService.x0 == null);
            sb.append(",diff=");
            sb.append(CommandService.x0 != CommandService.this);
            sb.append(",this service destroyed=");
            sb.append(CommandService.this.o0);
            sb.append(",not started?");
            sb.append(CommandService.v0 < this.q);
            sb.append(":");
            sb.append(CommandService.v0 > this.q);
            ax.p221pa.c.h().g().b("!!! PENDING CHECK NOT STARTED !!!").h(sb.toString()).i();
            CommandService.this.q0 = 0L;
        }
    }

    static class c {
        x a;
        x b;
        ax.p352z1.a c;
        AbstractC0698h d;

        public c(ax.p352z1.a aVar, x xVar) {
            this.c = aVar;
            this.a = xVar;
        }

        public ax.p352z1.a a() {
            return this.c;
        }

        public AbstractC0698h b() {
            return this.d;
        }

        public x c(boolean z) {
            return z ? this.a : this.b;
        }

        public void d(x xVar) {
            if (this.a == xVar) {
                this.a = null;
            }
            if (this.b == xVar) {
                this.b = null;
            }
        }

        public void e(AbstractC0698h abstractC0698h) {
            this.d = abstractC0698h;
        }

        public void f(boolean z, x xVar) {
            if (z) {
                this.a = xVar;
            } else {
                this.b = xVar;
            }
        }
    }

    public class d extends Binder {
        public d() {
        }

        public CommandService a() {
            return CommandService.this;
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0073 A[Catch: IllegalStateException -> 0x003e, TRY_LEAVE, TryCatch #1 {IllegalStateException -> 0x003e, blocks: (B:16:0x0024, B:18:0x002a, B:20:0x0038, B:25:0x0042, B:27:0x0048, B:29:0x0050, B:31:0x005e, B:33:0x0073), top: B:47:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:48:? A[RETURN, SYNTHETIC] */
    private void M(ax.p352z1.a aVar, h hVar, AbstractC0698h abstractC0698h) {
        c cVar = this.q.get(hVar);
        if (cVar == null) {
            return;
        }
        if (aVar == null && (aVar = this.X) == null) {
            aVar = cVar.a() != null ? cVar.a() : null;
        }
        if (aVar != null) {
            try {
                if (!aVar.v()) {
                    m mVarW = aVar.P().w();
                    if (!mVarW.G0()) {
                        ax.p045d2.x.d0(mVarW, abstractC0698h, "CommandDialog", true);
                        return;
                    }
                }
                if (aVar != null && aVar.v() && (aVar.P() instanceof MainActivity) && !aVar.P().w().G0()) {
                    cVar.e(abstractC0698h);
                    this.Z.add(hVar);
                    startActivity(C0734o.y(this, true));
                    if (P.M()) {
                        w.j(this).l(102, w.j(this).b(abstractC0698h.w3(this), abstractC0698h.v3(this), true));
                        return;
                    }
                    return;
                }
            } catch (IllegalStateException e) {
                ax.p221pa.c.h().g().d("STARTOP2").m(e).i();
            }
        } else if (aVar != null) {
            cVar.e(abstractC0698h);
            this.Z.add(hVar);
            startActivity(C0734o.y(this, true));
            if (P.M()) {
                w.j(this).l(102, w.j(this).b(abstractC0698h.w3(this), abstractC0698h.v3(this), true));
                return;
            }
            return;
        }
        cVar.e(abstractC0698h);
        this.Z.add(hVar);
        try {
            startActivity(C0734o.p(this));
        } catch (Exception e2) {
            ax.p221pa.c.h().g().b("COMMAND SERVICE FILE PROGRESS").m(e2).i();
        }
        if (P.M()) {
            w.j(this).l(102, w.j(this).b(abstractC0698h.w3(this), abstractC0698h.v3(this), false));
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0037  */
    /* JADX WARN: Instruction removed from duplicated block: B:16:0x0037, please report this as an issue */
    public static void N(f fVar, Fragment fragment, ax.D1.f fVar2, boolean z, boolean z2) throws C0711b {
        if (fVar2.g() != ax.D1.f.c.FILLED) {
            ax.p221pa.c.h().b("INFO NOT FILLED!!!").k().h(fVar2.getClass().getName() + " : " + fVar2.g().name()).i();
            throw new C0711b("Command is not filled");
        }
        h hVarA = fVar2.a();
        try {
            ax.p045d2.x.f0(fVar, l(fVar, fVar2), true, z2);
            CommandService commandServiceQ = q();
            if (commandServiceQ != null) {
                commandServiceQ.F(hVarA.p());
            }
            fVar.bindService(j(fVar, true), new a(hVarA, fVar, fragment, z), 1);
        } catch (IllegalStateException e) {
            e = e;
            if (!(fVar2 instanceof y)) {
                ax.p221pa.c.i(fVar).g().b("START COMMAND SERVICE FOREGROUND").m(e).h("command:" + fVar2.getClass().getName()).i();
            }
            throw new C0711b(e);
        } catch (SecurityException e2) {
            e = e2;
            if (!(fVar2 instanceof y)) {
                ax.p221pa.c.i(fVar).g().b("START COMMAND SERVICE FOREGROUND").m(e).h("command:" + fVar2.getClass().getName()).i();
            }
            throw new C0711b(e);
        }
    }

    private void P() {
        long jT;
        long jS;
        ArrayList<h> arrayList = this.Y;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String string = getResources().getString(R.string.running_progress_size, Integer.valueOf(this.Y.size()));
        synchronized (this.k0) {
            try {
                jT = 0;
                jS = 0;
                for (h hVar : this.Y) {
                    jT += (long) hVar.w().t();
                    jS += (long) hVar.w().s();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        w.j(getApplicationContext()).l(100, k(string, jT != 0 ? (int) ((jS * 100) / jT) : 0));
    }

    private void g() {
        w.j(getApplicationContext()).a(100);
        this.j0 = 0L;
    }

    public static Intent j(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) CommandService.class);
        if (z) {
            intent.setAction("action.start_command");
        }
        return intent;
    }

    private Notification k(String str, int i) {
        return w.j(getApplicationContext()).c(str, i);
    }

    public static Intent l(Context context, ax.D1.f fVar) {
        Intent intent = new Intent(context, (Class<?>) CommandService.class);
        if (fVar != null) {
            intent.putExtra("extra_command_class", fVar.getClass().getName());
            intent.putExtra("extra_command_id", fVar.e());
        }
        return intent;
    }

    private void m() {
        if (this.p0) {
            return;
        }
        try {
            startForeground(100, k(getString(R.string.ongoing), 0));
            this.p0 = true;
        } catch (IllegalStateException unused) {
            if (this.r0) {
                return;
            }
            int iU = ax.p045d2.x.u(this);
            ax.p221pa.c.i(this).g().b("Foreground not allowed : commandservice onbind").h("importance:" + iU + ",started;" + this.n0).i();
        }
    }

    public static CommandService q() {
        return x0;
    }

    private int r(h hVar) {
        int iIndexOf;
        synchronized (this.k0) {
            iIndexOf = this.Y.indexOf(hVar);
        }
        return iIndexOf;
    }

    public static boolean x(ax.p338y1.f fVar) {
        CommandService commandServiceQ = q();
        if (commandServiceQ != null) {
            return commandServiceQ.u(fVar);
        }
        return false;
    }

    public static boolean y(F f) {
        CommandService commandServiceQ = q();
        if (commandServiceQ != null) {
            return commandServiceQ.v(f);
        }
        return false;
    }

    private void z(h hVar, boolean z) {
        x xVarC;
        x xVarC2;
        c cVar = this.q.get(hVar);
        if (cVar != null) {
            xVarC = cVar.c(true);
            xVarC2 = cVar.c(false);
        } else {
            ax.p221pa.c.h().d("COMS4").k().h(Integer.valueOf(hVar.B())).i();
            xVarC = null;
            xVarC2 = null;
        }
        synchronized (this.k0) {
            this.Y.remove(hVar);
        }
        this.q.remove(hVar);
        if (z) {
            if (xVarC != null) {
                xVarC.P3(hVar);
            }
            if (xVarC2 != null) {
                xVarC2.P3(hVar);
            }
        } else {
            if (xVarC != null) {
                if (xVarC.s1()) {
                    xVarC.a3();
                } else {
                    xVarC.U3(true);
                }
            }
            if (xVarC2 != null) {
                if (xVarC2.s1()) {
                    xVarC2.a3();
                } else {
                    xVarC2.U3(true);
                }
            }
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.h1();
        }
    }

    public void A(h hVar) {
        z(hVar, false);
    }

    public void B(h hVar) {
        z(hVar, true);
    }

    public void C(h hVar) {
        x xVarC;
        x xVarC2;
        c cVar = this.q.get(hVar);
        if (cVar != null) {
            xVarC = cVar.c(true);
            xVarC2 = cVar.c(false);
        } else {
            ax.p221pa.c.h().d("COMS1:").k().h(Integer.valueOf(hVar.B())).i();
            xVarC = null;
            xVarC2 = null;
        }
        if (xVarC != null) {
            xVarC.Q3(hVar);
        }
        if (xVarC2 != null) {
            xVarC2.Q3(hVar);
        }
    }

    public void D(h hVar) {
        x xVarC;
        x xVarC2;
        c cVar = this.q.get(hVar);
        if (cVar != null) {
            xVarC = cVar.c(true);
            xVarC2 = cVar.c(false);
        } else {
            ax.p221pa.c.h().d("COMS2:").k().h(Integer.valueOf(hVar.B())).i();
            xVarC = null;
            xVarC2 = null;
        }
        if (xVarC != null) {
            xVarC.R3(hVar);
        }
        if (xVarC2 != null) {
            xVarC2.R3(hVar);
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.h1();
        }
        P();
    }

    public void E(h hVar, boolean z) {
        x xVarC;
        x xVarC2;
        c cVar = this.q.get(hVar);
        if (cVar != null) {
            xVarC = cVar.c(true);
            xVarC2 = cVar.c(false);
        } else {
            ax.p221pa.c.h().d("COMS3").k().h(Integer.valueOf(hVar.B())).i();
            xVarC = null;
            xVarC2 = null;
        }
        if (xVarC != null) {
            xVarC.S3(hVar, z);
        }
        if (xVarC2 != null) {
            xVarC2.S3(hVar, z);
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.i1(hVar, r(hVar), z);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (z || jUptimeMillis - this.j0 > 2000) {
            this.j0 = jUptimeMillis;
            P();
        }
    }

    public void F(long j) {
        this.q0 = j;
        Runnable runnable = this.s0;
        if (runnable != null) {
            this.t0.removeCallbacks(runnable);
        }
        b bVar = new b(j);
        this.s0 = bVar;
        this.t0.postDelayed(bVar, 30000L);
    }

    public void G(ax.p352z1.a aVar) {
        ArrayList<h> arrayList = new ArrayList(this.Z);
        this.Z.clear();
        for (h hVar : arrayList) {
            c cVar = this.q.get(hVar);
            if (cVar == null) {
                ax.p221pa.c.h().g().b("NULL PENDING OPERATOR").h(hVar != null ? "op:" + hVar.B() : "null op").i();
            } else if (cVar.b() != null) {
                M(aVar, hVar, cVar.b());
                cVar.e(null);
            } else {
                ax.p221pa.c.h().g().b("NULL PENDING DIALOG").h("command op:" + hVar.getClass().getName()).i();
            }
        }
        if (this.Z.size() > 0) {
            ax.p221pa.c.h().b("Command Operator repended!?").k().h("statesaved:" + aVar.v()).i();
        }
    }

    public void H(h hVar, x xVar) {
        c cVar = this.q.get(hVar);
        if (cVar != null) {
            cVar.d(xVar);
        }
    }

    public void I(FileProgressActivity fileProgressActivity) {
        if (this.X == fileProgressActivity) {
            this.X = null;
        }
    }

    public void J(ax.p352z1.a aVar, h hVar, boolean z) {
        c cVar = this.q.get(hVar);
        try {
            m mVarW = aVar.P().w();
            if (!mVarW.G0() && !aVar.v() && hVar.z() == h.d.STARTED) {
                x xVarO3 = x.O3();
                ax.p045d2.x.d0(mVarW, xVarO3, "fileProgress", true);
                xVarO3.Q3(hVar);
                xVarO3.R3(hVar);
                if (z) {
                    cVar.f(true, xVarO3);
                } else {
                    cVar.f(false, xVarO3);
                }
            }
        } catch (IllegalStateException unused) {
        }
    }

    public void K(FileProgressActivity fileProgressActivity) {
        this.X = fileProgressActivity;
    }

    public void L(h hVar, AbstractC0698h abstractC0698h) {
        M(null, hVar, abstractC0698h);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void O(f fVar, Fragment fragment, h hVar, boolean z) {
        ax.p352z1.a aVar;
        if (hVar.Q()) {
            ax.p221pa.c.h().d("COMMAND SERVICE OPERATOR START TWICE!!!!").h(hVar.getClass().getSimpleName()).i();
            return;
        }
        long jP = hVar.p();
        if (jP >= this.q0) {
            i();
        }
        v0 = jP;
        if (fVar instanceof ax.p352z1.a) {
            aVar = (ax.p352z1.a) fVar;
        } else {
            if (fVar != 0) {
                ax.p221pa.c.h().d("START OPERATOR FROM UNKNOWN ACTIVITY").h(fVar.getClass().getName()).i();
            }
            aVar = null;
        }
        boolean z2 = aVar != null && z;
        x xVarO3 = z2 ? x.O3() : null;
        synchronized (this.k0) {
            this.Y.add(hVar);
        }
        c cVar = new c(aVar, xVarO3);
        this.q.put(hVar, cVar);
        if (!this.m0.b()) {
            Iterator<F> it = hVar.y().iterator();
            while (it.hasNext()) {
                if (ax.p338y1.f.k0(it.next().d())) {
                    this.m0.a();
                    break;
                }
            }
        }
        hVar.l0(this);
        hVar.q0(this.l0);
        if (z2) {
            try {
                m mVarW = aVar.P().w();
                if (mVarW.G0()) {
                    cVar.d(xVarO3);
                } else if (!aVar.v()) {
                    ax.p045d2.x.d0(mVarW, xVarO3, "fileProgress", true);
                }
            } catch (IllegalStateException e) {
                cVar.d(xVarO3);
                String str = HttpUrl.FRAGMENT_ENCODE_SET;
                if (fragment != null && (fragment instanceof AbstractC0762l)) {
                    str = "ActiveState :" + ((AbstractC0762l) fragment).U3();
                }
                ax.p221pa.c.h().d("STARTOP").m(e).h(str).i();
            }
        }
    }

    public void h() {
        synchronized (this.k0) {
            try {
                if (this.Y.size() == 0 && this.q0 == 0) {
                    FileProgressActivity fileProgressActivity = this.X;
                    if (fileProgressActivity != null && !fileProgressActivity.isFinishing()) {
                        this.X.finish();
                    }
                    stopForeground(true);
                    this.p0 = false;
                    g();
                    stopSelf();
                    this.n0 = false;
                } else {
                    P();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void i() {
        this.q0 = 0L;
        this.t0.removeCallbacks(this.s0);
    }

    public List<h> n() {
        return this.Y;
    }

    public List<h> o(F f) {
        return p(f, -1);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent != null && "action.start_command".equals(intent.getAction())) {
            m();
        }
        return this.h0;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.Y = new ArrayList<>();
        this.q = new HashMap<>();
        this.Z = new ArrayList<>();
        this.i0 = ax.p045d2.y.a(this, 0L, "CommandService");
        x0 = this;
        this.l0 = Thread.currentThread();
        this.m0 = new z(this, 3, "COMMAND_SERVICE");
    }

    @Override // android.app.Service
    public void onDestroy() {
        ax.p045d2.y.d(this.i0);
        this.m0.c();
        super.onDestroy();
        x0 = null;
        this.o0 = true;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        if (intent == null || !"action.start_command".equals(intent.getAction())) {
            return;
        }
        m();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                long longExtra = intent.getLongExtra("extra_command_id", -1L);
                if (longExtra <= v0) {
                    ax.p338y1.d.c("CommandServiceStartLater", new Exception("command cls:" + intent.getStringExtra("extra_command_class") + ",id:" + longExtra + ",lastop:" + v0 + ",lastsvc:" + w0));
                }
                w0 = longExtra;
            } catch (IllegalStateException unused) {
                String stringExtra = intent.getStringExtra("extra_command_class");
                if (stringExtra != null && stringExtra.equals(y.class.getName())) {
                    this.r0 = true;
                    return 2;
                }
                ax.p221pa.c.i(this).g().b("Foreground not allowed : command service").h("command class:" + stringExtra).i();
                return 2;
            }
        }
        this.r0 = false;
        this.n0 = true;
        startForeground(100, k(getString(R.string.ongoing), 0));
        this.p0 = true;
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }

    public List<h> p(F f, int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.k0) {
            try {
                for (h hVar : this.Y) {
                    if (i == -1 || hVar.B() == i) {
                        if (hVar.y().contains(f)) {
                            arrayList.add(hVar);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public long s() {
        return this.q0;
    }

    public boolean t() {
        return this.p0;
    }

    public boolean u(ax.p338y1.f fVar) {
        synchronized (this.k0) {
            try {
                Iterator<h> it = this.Y.iterator();
                while (it.hasNext()) {
                    Iterator<F> it2 = it.next().y().iterator();
                    while (it2.hasNext()) {
                        if (it2.next().d() == fVar) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean v(F f) {
        synchronized (this.k0) {
            try {
                Iterator<h> it = this.Y.iterator();
                while (it.hasNext()) {
                    if (it.next().y().contains(f)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean w() {
        return this.n0;
    }
}
