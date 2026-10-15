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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.f;
import ax.U2.g;
import ax.Z2.h;
import ax.Z2.j;
import ax.p006a3.Q;
import ax.p054d3.AbstractC1905j;
import ax.p054d3.C1920z;
import ax.p070e3.C1959c;
import ax.p086f3.C2014u;
import ax.p086f3.K;
import ax.p102g3.AbstractC2067n;
import ax.p374x3.w;
import ax.p406z3.B;
import ax.p406z3.C;
import ax.p406z3.C2461b;
import ax.p406z3.D;
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
    private static long A0;
    private static CommandService B0;
    private static final Logger y0 = g.a(CommandService.class);
    private static long z0;
    private FileProgressActivity X;
    private ArrayList<j> Y;
    private ArrayList<j> Z;
    private int l0;
    private long m0;
    private Thread o0;
    private D p0;
    private HashMap<j, c> q;
    private boolean q0;
    private boolean r0;
    private boolean s0;
    private long t0;
    private boolean u0;
    private Runnable v0;
    private long w0;
    private final IBinder k0 = new d();
    private final Object n0 = new Object();
    private Handler x0 = new Handler(Looper.getMainLooper());

    class a implements ServiceConnection {
        final /* synthetic */ f X;
        final /* synthetic */ Fragment Y;
        final /* synthetic */ boolean Z;
        final /* synthetic */ j q;

        a(j jVar, f fVar, Fragment fragment, boolean z) {
            this.q = jVar;
            this.X = fVar;
            this.Y = fragment;
            this.Z = z;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (!(iBinder instanceof d)) {
                ax.U2.d.c("commandconnected", new Exception("Invalid service binder:" + iBinder.getClass().getName()));
                return;
            }
            CommandService commandServiceA = ((d) iBinder).a();
            if (!commandServiceA.w()) {
                String str = "cmd:" + this.q.getClass().getName() + "," + this.q.p() + "," + commandServiceA.s();
                ax.p302sb.c.h().g().b("COMMAND SERVICE IS NOT STARTED").h(str).i();
                C2461b.g(str);
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
            if (CommandService.this.q0) {
                if (CommandService.this.r0) {
                    C2461b.g("CommandService started but destroyed");
                    return;
                } else {
                    if (CommandService.this.t0 == this.q) {
                        CommandService.this.t0 = 0L;
                        CommandService.this.h();
                        return;
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("current service null=");
            sb.append(CommandService.B0 == null);
            sb.append(",diff=");
            sb.append(CommandService.B0 != CommandService.this);
            sb.append(",this service destroyed=");
            sb.append(CommandService.this.r0);
            sb.append(",not started?");
            sb.append(CommandService.z0 < this.q);
            sb.append(":");
            sb.append(CommandService.z0 > this.q);
            ax.p302sb.c.h().g().b("!!! PENDING CHECK NOT STARTED !!!").h(sb.toString()).i();
            CommandService.this.t0 = 0L;
        }
    }

    static class c {
        C1920z a;
        C1920z b;
        ax.V2.a c;
        AbstractC1905j d;

        public c(ax.V2.a aVar, C1920z c1920z) {
            this.c = aVar;
            this.a = c1920z;
        }

        public ax.V2.a a() {
            return this.c;
        }

        public AbstractC1905j b() {
            return this.d;
        }

        public C1920z c(boolean z) {
            return z ? this.a : this.b;
        }

        public void d(C1920z c1920z) {
            if (this.a == c1920z) {
                this.a = null;
            }
            if (this.b == c1920z) {
                this.b = null;
            }
        }

        public void e(AbstractC1905j abstractC1905j) {
            this.d = abstractC1905j;
        }

        public void f(boolean z, C1920z c1920z) {
            if (z) {
                this.a = c1920z;
            } else {
                this.b = c1920z;
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

    /* JADX WARN: Code duplicated, block: B:33:0x0074 A[Catch: IllegalStateException -> 0x003f, TRY_LEAVE, TryCatch #0 {IllegalStateException -> 0x003f, blocks: (B:16:0x0025, B:18:0x002b, B:20:0x0039, B:25:0x0043, B:27:0x0049, B:29:0x0051, B:31:0x005f, B:33:0x0074), top: B:45:0x0025 }] */
    /* JADX WARN: Code duplicated, block: B:48:? A[RETURN, SYNTHETIC] */
    private void M(ax.V2.a aVar, j jVar, AbstractC1905j abstractC1905j) {
        c cVar = this.q.get(jVar);
        if (cVar == null) {
            return;
        }
        if (aVar == null && (aVar = this.X) == null) {
            aVar = cVar.a() != null ? cVar.a() : null;
        }
        if (aVar != null) {
            try {
                if (!aVar.k()) {
                    FragmentManager supportFragmentManager = aVar.r().getSupportFragmentManager();
                    if (!supportFragmentManager.I0()) {
                        B.d0(supportFragmentManager, abstractC1905j, "CommandDialog", true);
                        return;
                    }
                }
                if (aVar != null && aVar.k() && (aVar.r() instanceof MainActivity) && !aVar.r().getSupportFragmentManager().I0()) {
                    cVar.e(abstractC1905j);
                    this.Z.add(jVar);
                    startActivity(C2014u.D(this, true));
                    if (Q.M()) {
                        w.j(this).l(102, w.j(this).b(this, abstractC1905j.t3(this), abstractC1905j.s3(this), true));
                        return;
                    }
                    return;
                }
            } catch (IllegalStateException e) {
                ax.p302sb.c.h().g().d("STARTOP2").m(e).i();
            }
        } else if (aVar != null) {
            cVar.e(abstractC1905j);
            this.Z.add(jVar);
            startActivity(C2014u.D(this, true));
            if (Q.M()) {
                w.j(this).l(102, w.j(this).b(this, abstractC1905j.t3(this), abstractC1905j.s3(this), true));
                return;
            }
            return;
        }
        cVar.e(abstractC1905j);
        this.Z.add(jVar);
        try {
            startActivity(C2014u.t(this));
        } catch (Exception e2) {
            ax.p302sb.c.h().g().b("COMMAND SERVICE FILE PROGRESS").m(e2).i();
        }
        if (Q.M()) {
            w.j(this).l(102, w.j(this).b(this, abstractC1905j.t3(this), abstractC1905j.s3(this), false));
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0037  */
    /* JADX WARN: Instruction removed from duplicated block: B:16:0x0037, please report this as an issue */
    public static void N(f fVar, Fragment fragment, h hVar, boolean z, boolean z2) throws C1959c {
        if (hVar.g() != h.c.FILLED) {
            ax.p302sb.c.h().b("INFO NOT FILLED!!!").k().h(hVar.getClass().getName() + " : " + hVar.g().name()).i();
            throw new C1959c("Command is not filled");
        }
        j jVarA = hVar.a();
        try {
            B.f0(fVar, l(fVar, hVar), true, z2);
            CommandService commandServiceQ = q();
            if (commandServiceQ != null) {
                commandServiceQ.F(jVarA.p());
            }
            fVar.bindService(j(fVar, true), new a(jVarA, fVar, fragment, z), 1);
        } catch (IllegalStateException e) {
            e = e;
            if (!(hVar instanceof ax.Z2.B)) {
                ax.p302sb.c.i(fVar).g().b("START COMMAND SERVICE FOREGROUND").m(e).h("command:" + hVar.getClass().getName()).i();
            }
            throw new C1959c(e);
        } catch (SecurityException e2) {
            e = e2;
            if (!(hVar instanceof ax.Z2.B)) {
                ax.p302sb.c.i(fVar).g().b("START COMMAND SERVICE FOREGROUND").m(e).h("command:" + hVar.getClass().getName()).i();
            }
            throw new C1959c(e);
        }
    }

    private void P() {
        long jU;
        long jT;
        ArrayList<j> arrayList = this.Y;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String string = getResources().getString(R.string.running_progress_size, Integer.valueOf(this.Y.size()));
        synchronized (this.n0) {
            try {
                ArrayList<j> arrayList2 = this.Y;
                int size = arrayList2.size();
                jU = 0;
                jT = 0;
                int i = 0;
                while (i < size) {
                    j jVar = arrayList2.get(i);
                    i++;
                    j jVar2 = jVar;
                    jU += (long) jVar2.w().u();
                    jT += (long) jVar2.w().t();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        try {
            w.j(getApplicationContext()).l(100, k(string, jU != 0 ? (int) ((jT * 100) / jU) : 0, true));
        } catch (NullPointerException e) {
            ax.U2.d.c("update_service_noti", e);
        }
    }

    private void g() {
        w.j(getApplicationContext()).a(100);
        this.m0 = 0L;
    }

    public static Intent j(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) CommandService.class);
        if (z) {
            intent.setAction("action.start_command");
        }
        return intent;
    }

    private Notification k(String str, int i, boolean z) {
        return w.j(getApplicationContext()).c(this, str, i, z);
    }

    public static Intent l(Context context, h hVar) {
        Intent intent = new Intent(context, (Class<?>) CommandService.class);
        if (hVar != null) {
            intent.putExtra("extra_command_class", hVar.getClass().getName());
            intent.putExtra("extra_command_id", hVar.e());
        }
        return intent;
    }

    private void m() {
        if (this.s0) {
            return;
        }
        try {
            startForeground(100, k(getString(R.string.ongoing), 0, false));
            this.s0 = true;
        } catch (IllegalStateException unused) {
            if (this.u0) {
                return;
            }
            int iR = B.r(this);
            ax.p302sb.c.i(this).g().b("Foreground not allowed : commandservice onbind").h("importance:" + iR + ",started;" + this.q0).i();
        }
    }

    public static CommandService q() {
        return B0;
    }

    private int r(j jVar) {
        int iIndexOf;
        synchronized (this.n0) {
            iIndexOf = this.Y.indexOf(jVar);
        }
        return iIndexOf;
    }

    public static boolean x(ax.U2.f fVar) {
        CommandService commandServiceQ = q();
        if (commandServiceQ != null) {
            return commandServiceQ.u(fVar);
        }
        return false;
    }

    public static boolean y(K k) {
        CommandService commandServiceQ = q();
        if (commandServiceQ != null) {
            return commandServiceQ.v(k);
        }
        return false;
    }

    private void z(j jVar, boolean z) {
        C1920z c1920zC;
        C1920z c1920zC2;
        c cVar = this.q.get(jVar);
        if (cVar != null) {
            c1920zC = cVar.c(true);
            c1920zC2 = cVar.c(false);
        } else {
            ax.p302sb.c.h().d("COMS4").k().h(Integer.valueOf(jVar.B())).i();
            c1920zC = null;
            c1920zC2 = null;
        }
        synchronized (this.n0) {
            this.Y.remove(jVar);
        }
        this.q.remove(jVar);
        if (z) {
            if (c1920zC != null) {
                c1920zC.M3(jVar);
            }
            if (c1920zC2 != null) {
                c1920zC2.M3(jVar);
            }
        } else {
            if (c1920zC != null) {
                if (c1920zC.o1()) {
                    c1920zC.X2();
                } else {
                    c1920zC.R3(true);
                }
            }
            if (c1920zC2 != null) {
                if (c1920zC2.o1()) {
                    c1920zC2.X2();
                } else {
                    c1920zC2.R3(true);
                }
            }
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.W();
        }
    }

    public void A(j jVar) {
        z(jVar, false);
    }

    public void B(j jVar) {
        z(jVar, true);
    }

    public void C(j jVar) {
        C1920z c1920zC;
        C1920z c1920zC2;
        c cVar = this.q.get(jVar);
        if (cVar != null) {
            c1920zC = cVar.c(true);
            c1920zC2 = cVar.c(false);
        } else {
            ax.p302sb.c.h().d("COMS1:").k().h(Integer.valueOf(jVar.B())).i();
            c1920zC = null;
            c1920zC2 = null;
        }
        if (c1920zC != null) {
            c1920zC.N3(jVar);
        }
        if (c1920zC2 != null) {
            c1920zC2.N3(jVar);
        }
    }

    public void D(j jVar) {
        C1920z c1920zC;
        C1920z c1920zC2;
        c cVar = this.q.get(jVar);
        if (cVar != null) {
            c1920zC = cVar.c(true);
            c1920zC2 = cVar.c(false);
        } else {
            ax.p302sb.c.h().d("COMS2:").k().h(Integer.valueOf(jVar.B())).i();
            c1920zC = null;
            c1920zC2 = null;
        }
        if (c1920zC != null) {
            c1920zC.O3(jVar);
        }
        if (c1920zC2 != null) {
            c1920zC2.O3(jVar);
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.W();
        }
        P();
    }

    public void E(j jVar, boolean z) {
        C1920z c1920zC;
        C1920z c1920zC2;
        c cVar = this.q.get(jVar);
        if (cVar != null) {
            c1920zC = cVar.c(true);
            c1920zC2 = cVar.c(false);
        } else {
            ax.p302sb.c.h().d("COMS3").k().h(Integer.valueOf(jVar.B())).i();
            c1920zC = null;
            c1920zC2 = null;
        }
        if (c1920zC != null) {
            c1920zC.P3(jVar, z);
        }
        if (c1920zC2 != null) {
            c1920zC2.P3(jVar, z);
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.X(jVar, r(jVar), z);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (z || jUptimeMillis - this.m0 > 2000) {
            this.m0 = jUptimeMillis;
            P();
        }
    }

    public void F(long j) {
        this.t0 = j;
        Runnable runnable = this.v0;
        if (runnable != null) {
            this.x0.removeCallbacks(runnable);
        }
        b bVar = new b(j);
        this.v0 = bVar;
        this.x0.postDelayed(bVar, 30000L);
    }

    public void G(ax.V2.a aVar) {
        String str;
        ArrayList arrayList = new ArrayList(this.Z);
        this.Z.clear();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            j jVar = (j) obj;
            c cVar = this.q.get(jVar);
            if (cVar == null) {
                if (jVar != null) {
                    str = "op:" + jVar.B();
                } else {
                    str = "null op";
                }
                ax.p302sb.c.h().g().b("NULL PENDING OPERATOR").h(str).i();
            } else if (cVar.b() != null) {
                M(aVar, jVar, cVar.b());
                cVar.e(null);
            } else {
                ax.p302sb.c.h().g().b("NULL PENDING DIALOG").h("command op:" + jVar.getClass().getName()).i();
            }
        }
        if (this.Z.size() > 0) {
            ax.p302sb.c.h().b("Command Operator repended!?").k().h("statesaved:" + aVar.k()).i();
        }
    }

    public void H(j jVar, C1920z c1920z) {
        c cVar = this.q.get(jVar);
        if (cVar != null) {
            cVar.d(c1920z);
        }
    }

    public void I(FileProgressActivity fileProgressActivity) {
        if (this.X == fileProgressActivity) {
            this.X = null;
        }
    }

    public void J(ax.V2.a aVar, j jVar, boolean z) {
        c cVar = this.q.get(jVar);
        try {
            FragmentManager supportFragmentManager = aVar.r().getSupportFragmentManager();
            if (supportFragmentManager.I0() || aVar.k() || jVar.z() != j.d.STARTED) {
                return;
            }
            C1920z c1920zL3 = C1920z.L3();
            B.d0(supportFragmentManager, c1920zL3, "fileProgress", true);
            c1920zL3.N3(jVar);
            c1920zL3.O3(jVar);
            if (z) {
                cVar.f(true, c1920zL3);
            } else {
                cVar.f(false, c1920zL3);
            }
        } catch (IllegalStateException unused) {
        }
    }

    public void K(FileProgressActivity fileProgressActivity) {
        this.X = fileProgressActivity;
    }

    public void L(j jVar, AbstractC1905j abstractC1905j) {
        M(null, jVar, abstractC1905j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void O(f fVar, Fragment fragment, j jVar, boolean z) {
        ax.V2.a aVar;
        if (jVar.R()) {
            ax.p302sb.c.h().d("COMMAND SERVICE OPERATOR START TWICE!!!!").h(jVar.getClass().getSimpleName()).i();
            return;
        }
        long jP = jVar.p();
        if (jP >= this.t0) {
            i();
        }
        z0 = jP;
        if (fVar instanceof ax.V2.a) {
            aVar = (ax.V2.a) fVar;
        } else {
            if (fVar != 0) {
                ax.p302sb.c.h().d("START OPERATOR FROM UNKNOWN ACTIVITY").h(fVar.getClass().getName()).i();
            }
            aVar = null;
        }
        boolean z2 = aVar != null && z;
        C1920z c1920zL3 = z2 ? C1920z.L3() : null;
        synchronized (this.n0) {
            this.Y.add(jVar);
        }
        c cVar = new c(aVar, c1920zL3);
        this.q.put(jVar, cVar);
        if (!this.p0.b()) {
            Iterator<K> it = jVar.y().iterator();
            while (it.hasNext()) {
                if (ax.U2.f.l0(it.next().d())) {
                    this.p0.a();
                    break;
                }
            }
        }
        jVar.n0(this);
        jVar.s0(this.o0);
        if (z2) {
            try {
                FragmentManager supportFragmentManager = aVar.r().getSupportFragmentManager();
                if (supportFragmentManager.I0()) {
                    cVar.d(c1920zL3);
                } else if (!aVar.k()) {
                    B.d0(supportFragmentManager, c1920zL3, "fileProgress", true);
                }
            } catch (IllegalStateException e) {
                cVar.d(c1920zL3);
                String str = HttpUrl.FRAGMENT_ENCODE_SET;
                if (fragment != null && (fragment instanceof AbstractC2067n)) {
                    str = "ActiveState :" + ((AbstractC2067n) fragment).X3();
                }
                ax.p302sb.c.h().d("STARTOP").m(e).h(str).i();
            }
        }
        FileProgressActivity fileProgressActivity = this.X;
        if (fileProgressActivity != null) {
            fileProgressActivity.W();
        }
    }

    public void h() {
        synchronized (this.n0) {
            try {
                if (this.Y.size() == 0 && this.t0 == 0) {
                    FileProgressActivity fileProgressActivity = this.X;
                    if (fileProgressActivity != null && !fileProgressActivity.isFinishing()) {
                        this.X.finish();
                    }
                    stopForeground(true);
                    this.s0 = false;
                    g();
                    stopSelf();
                    this.q0 = false;
                } else {
                    P();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void i() {
        this.t0 = 0L;
        this.x0.removeCallbacks(this.v0);
    }

    public List<j> n() {
        return this.Y;
    }

    public List<j> o(K k) {
        return p(k, -1);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent != null && "action.start_command".equals(intent.getAction())) {
            m();
        }
        return this.k0;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.Y = new ArrayList<>();
        this.q = new HashMap<>();
        this.Z = new ArrayList<>();
        this.l0 = C.a(this, 0L, "CommandService");
        B0 = this;
        this.o0 = Thread.currentThread();
        this.p0 = new D(this, 3, "COMMAND_SERVICE");
        this.w0 = System.currentTimeMillis();
    }

    @Override // android.app.Service
    public void onDestroy() {
        C.d(this.l0);
        this.p0.c();
        super.onDestroy();
        B0 = null;
        this.r0 = true;
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
                if (longExtra <= z0) {
                    ax.U2.d.c("CommandServiceStartLater", new Exception("command cls:" + intent.getStringExtra("extra_command_class") + ",id:" + longExtra + ",lastop:" + z0 + ",lastsvc:" + A0));
                }
                A0 = longExtra;
            } catch (IllegalStateException unused) {
                String stringExtra = intent.getStringExtra("extra_command_class");
                if (stringExtra != null && stringExtra.equals(ax.Z2.B.class.getName())) {
                    this.u0 = true;
                    return 2;
                }
                ax.p302sb.c.i(this).g().b("Foreground not allowed : command service").h("command class:" + stringExtra).i();
                return 2;
            }
        }
        this.u0 = false;
        this.q0 = true;
        startForeground(100, k(getString(R.string.ongoing), 0, false));
        this.s0 = true;
        return 2;
    }

    public void onTimeout(int i, int i2) {
        String string;
        super.onTimeout(i, i2);
        boolean z = true;
        stopForeground(1);
        if (this.Y != null) {
            StringBuilder sb = new StringBuilder();
            synchronized (this.n0) {
                try {
                    ArrayList<j> arrayList = this.Y;
                    int size = arrayList.size();
                    int i3 = 0;
                    while (i3 < size) {
                        j jVar = arrayList.get(i3);
                        i3++;
                        j jVar2 = jVar;
                        if (z) {
                            z = false;
                        } else {
                            sb.append(",");
                        }
                        sb.append(jVar2.B());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            string = sb.toString();
        } else {
            string = "null";
        }
        ax.U2.d.c("Command service timeout", new Exception("CommandServiceTimeout : " + string + ":" + (System.currentTimeMillis() - this.w0)));
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }

    public List<j> p(K k, int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.n0) {
            try {
                ArrayList<j> arrayList2 = this.Y;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    j jVar = arrayList2.get(i2);
                    i2++;
                    j jVar2 = jVar;
                    if (i == -1 || jVar2.B() == i) {
                        if (jVar2.y().contains(k)) {
                            arrayList.add(jVar2);
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
        return this.t0;
    }

    public boolean t() {
        return this.s0;
    }

    public boolean u(ax.U2.f fVar) {
        synchronized (this.n0) {
            try {
                ArrayList<j> arrayList = this.Y;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    j jVar = arrayList.get(i);
                    i++;
                    Iterator<K> it = jVar.y().iterator();
                    while (it.hasNext()) {
                        if (it.next().d() == fVar) {
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

    public boolean v(K k) {
        synchronized (this.n0) {
            try {
                ArrayList<j> arrayList = this.Y;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    j jVar = arrayList.get(i);
                    i++;
                    if (jVar.y().contains(k)) {
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
        return this.q0;
    }
}
