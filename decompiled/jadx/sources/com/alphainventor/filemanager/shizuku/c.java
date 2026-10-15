package com.alphainventor.filemanager.shizuku;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import ax.E1.K;
import ax.E1.P;
import ax.J1.F;
import ax.P1.g;
import ax.p045d2.x;
import ax.p338y1.i;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5845t;
import com.alphainventor.filemanager.file.u;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static c l;
    private static long m;
    private com.alphainventor.filemanager.shizuku.a a;
    private Context b;
    private Boolean c;
    private i.a f;
    private boolean d = false;
    private int e = 0;
    private final ax.kd.i.f g = new ax.kd.i.f(new ComponentName("com.alphainventor.filemanager", ShizukuUserService.class.getName())).c(false).g("service").d(false).h(2103054);
    private final ax.kd.i.d h = new ax.kd.i.d() { // from class: ax.Y1.b
        @Override // ax.kd.i.d
        public final void a() {
            this.a.C();
        }
    };
    private final ax.kd.i.c i = new ax.kd.i.c() { // from class: ax.Y1.c
        @Override // ax.kd.i.c
        public final void a() {
            com.alphainventor.filemanager.shizuku.c.D();
        }
    };
    private final ax.kd.i.e j = new b();
    private final ServiceConnection k = new ServiceConnectionC0515c();

    class a implements g {
        a() {
        }

        @Override // ax.P1.g
        public void U(String str) {
            c.this.c = null;
        }
    }

    class b implements ax.kd.i.e {
        b() {
        }

        @Override // ax.kd.i.e
        public void a(int i, int i2) {
            if (i2 == 0) {
                c.this.k();
            } else if (c.this.f != null) {
                c.this.f.c();
                c.this.f = null;
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.c$c, reason: collision with other inner class name */
    class ServiceConnectionC0515c implements ServiceConnection {
        ServiceConnectionC0515c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null || !iBinder.pingBinder()) {
                return;
            }
            c.this.J(com.alphainventor.filemanager.shizuku.a.AbstractBinderC0511a.F0(iBinder));
            if (c.this.f != null) {
                c.this.f.b();
                c.this.f = null;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.this.n();
        }
    }

    public class d extends ParcelFileDescriptor.AutoCloseOutputStream {
        private boolean X;
        private long q;

        public d(ParcelFileDescriptor parcelFileDescriptor, long j) {
            super(parcelFileDescriptor);
            this.X = false;
            this.q = j;
        }

        private void a() {
            try {
                Thread.sleep(5L);
            } catch (InterruptedException unused) {
            }
            int i = 0;
            while (!c.this.A(this.q)) {
                try {
                    Thread.sleep(10L);
                    return;
                } catch (InterruptedException unused2) {
                    i++;
                    if (i > 500) {
                        return;
                    }
                }
            }
        }

        @Override // android.os.ParcelFileDescriptor.AutoCloseOutputStream, java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                super.close();
            } finally {
                if (!this.X) {
                    this.X = true;
                    a();
                }
            }
        }
    }

    private c(Context context) {
        this.b = context;
        ax.p338y1.b.h().e(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A(long j) {
        try {
            return this.a.D2(j);
        } catch (Exception unused) {
            return true;
        }
    }

    private boolean B() {
        return this.a != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        ax.kd.i.D();
        if (this.e <= 0 || B()) {
            return;
        }
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void D() {
    }

    private static synchronized long F() {
        long j;
        j = m + 1;
        m = j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(com.alphainventor.filemanager.shizuku.a aVar) {
        this.a = aVar;
    }

    private void K() {
        try {
            if (ax.kd.i.C() < 10) {
                return;
            }
            ax.kd.i.W(this.g, this.k, true);
        } catch (Throwable th) {
            th.printStackTrace();
            th.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            ax.kd.i.y();
            try {
                if (ax.kd.i.C() < 10) {
                    return;
                }
                ax.kd.i.x(this.g, this.k);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (IllegalStateException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.a = null;
    }

    public static c t() {
        if (l == null) {
            ax.p338y1.d.b(new Exception("Shizuku not initialized"));
        }
        return l;
    }

    public static void x(Context context) {
        if (l == null) {
            l = new c(context);
        }
    }

    public List<u> E(C5845t c5845t, F f, String str) {
        try {
            List<ax.Y1.a> listK4 = this.a.K4(str);
            if (listK4 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<ax.Y1.a> it = listK4.iterator();
            while (it.hasNext()) {
                arrayList.add(new u(c5845t, f, it.next()));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void G() {
        int i = this.e - 1;
        this.e = i;
        if (i <= 0) {
            ax.kd.i.N(this.h);
            ax.kd.i.M(this.i);
            ax.kd.i.O(this.j);
            K();
        }
    }

    public boolean H(String str, String str2) {
        try {
            return this.a.D4(str, str2);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean I(String str, long j) {
        try {
            return this.a.x2(str, j);
        } catch (Exception unused) {
            return false;
        }
    }

    public void j() {
        if (this.e == 0) {
            ax.kd.i.r(this.h);
            ax.kd.i.o(this.i);
            ax.kd.i.t(this.j);
            k();
        }
        this.e++;
    }

    public boolean l() {
        if (z() && y()) {
            return B();
        }
        return false;
    }

    public boolean m(i.a aVar) {
        if (!y() || ax.kd.i.D()) {
            return false;
        }
        try {
            if (ax.kd.i.y() == 0) {
                return true;
            }
            if (ax.kd.i.V()) {
                return false;
            }
            this.f = aVar;
            ax.kd.i.P(65001);
        } catch (Throwable unused) {
        }
        return false;
    }

    public boolean o(String str) {
        try {
            return this.a.C7(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean p(String str) {
        try {
            return this.a.L5(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean q(String str) {
        try {
            return this.a.O(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public AbstractC5838l r(C5845t c5845t, F f, String str) {
        try {
            return new u(c5845t, f, this.a.V0(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public InputStream s(String str, long j) throws IOException {
        try {
            return new ParcelFileDescriptor.AutoCloseInputStream(this.a.J2(str, j, F()));
        } catch (IllegalStateException e) {
            com.alphainventor.filemanager.shizuku.b.c cVarE = com.alphainventor.filemanager.shizuku.b.e(e);
            if (cVarE == null) {
                throw new IOException(e);
            }
            if (cVarE.a == com.alphainventor.filemanager.shizuku.b.d) {
                throw new FileNotFoundException(cVarE.b);
            }
            throw new IOException(cVarE.b);
        } catch (Exception e2) {
            throw new IOException(e2);
        }
    }

    public int u(String str, boolean z) {
        try {
            return this.a.G6(str, z);
        } catch (Exception unused) {
            return -2;
        }
    }

    public OutputStream v(String str, boolean z) throws IOException {
        try {
            long jF = F();
            return new d(this.a.C4(str, z, jF), jF);
        } catch (IllegalStateException e) {
            com.alphainventor.filemanager.shizuku.b.c cVarE = com.alphainventor.filemanager.shizuku.b.e(e);
            if (cVarE != null) {
                throw new IOException(cVarE.b);
            }
            throw new IOException(e);
        } catch (Exception e2) {
            throw new IOException(e2);
        }
    }

    public K w(String str) throws IOException {
        try {
            ax.Y1.a aVarD4 = this.a.d4(str);
            K k = new K();
            k.d = aVarD4.Y;
            k.b = aVarD4.Z;
            k.a = aVarD4.k0;
            k.c = aVarD4.l0;
            return k;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public boolean y() {
        try {
            return ax.kd.i.B() != -1;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean z() {
        if (!P.p1()) {
            return false;
        }
        if (this.c == null) {
            this.c = Boolean.valueOf(x.O(this.b, "moe.shizuku.privileged.api"));
        }
        return this.c.booleanValue();
    }
}
