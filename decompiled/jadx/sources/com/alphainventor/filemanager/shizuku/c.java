package com.alphainventor.filemanager.shizuku;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.AndroidRuntimeException;
import androidx.fragment.app.Fragment;
import ax.U2.d;
import ax.U2.i;
import ax.p006a3.L;
import ax.p006a3.Q;
import ax.p086f3.C2014u;
import ax.p086f3.K;
import ax.p182l3.g;
import ax.p406z3.B;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.file.C6249y;
import com.alphainventor.filemanager.shizuku.c;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static c m;
    private com.alphainventor.filemanager.shizuku.a a;
    private Context b;
    private Boolean c;
    private i.a g;
    private boolean d = false;
    private int e = 0;
    private boolean f = false;
    private final ax.cf.i.f h = new ax.cf.i.f(new ComponentName("com.alphainventor.filemanager", ShizukuUserService.class.getName())).c(false).g("service").d(false).h(2103083);
    private final ax.cf.i.d i = new ax.cf.i.d() { // from class: ax.u3.b
        @Override // ax.cf.i.d
        public final void a() {
            c.a(this.a);
        }
    };
    private final ax.cf.i.c j = new ax.cf.i.c() { // from class: ax.u3.c
        @Override // ax.cf.i.c
        public final void a() {
            com.alphainventor.filemanager.shizuku.c.b();
        }
    };
    private final ax.cf.i.e k = new b();
    private final ServiceConnection l = new ServiceConnectionC0549c();

    class a implements g {
        a() {
        }

        @Override // ax.p182l3.g
        public void P(String str) {
            c.this.c = null;
        }
    }

    class b implements ax.cf.i.e {
        b() {
        }

        @Override // ax.cf.i.e
        public void a(int i, int i2) {
            if (i2 == 0) {
                c.this.k();
            } else if (c.this.g != null) {
                c.this.g.c();
                c.this.g = null;
            }
            c.this.f = false;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.c$c, reason: collision with other inner class name */
    class ServiceConnectionC0549c implements ServiceConnection {
        ServiceConnectionC0549c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null || !iBinder.pingBinder()) {
                return;
            }
            c.this.J(com.alphainventor.filemanager.shizuku.a.AbstractBinderC0545a.M0(iBinder));
            if (c.this.g != null) {
                c.this.g.b();
                c.this.g = null;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.this.n();
        }
    }

    private c(Context context) {
        this.b = context;
        ax.U2.b.h().e(new a());
    }

    private boolean D() {
        return this.a != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(com.alphainventor.filemanager.shizuku.a aVar) {
        this.a = aVar;
    }

    private void K() {
        try {
            if (ax.cf.i.C() < 10) {
                return;
            }
            ax.cf.i.Q(this.h, this.l, true);
        } catch (Throwable th) {
            th.printStackTrace();
            th.toString();
        }
    }

    public static /* synthetic */ void a(c cVar) {
        cVar.getClass();
        ax.cf.i.D();
        if (cVar.e <= 0 || cVar.D()) {
            return;
        }
        cVar.k();
    }

    public static /* synthetic */ void b() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            ax.cf.i.y();
            try {
                if (ax.cf.i.C() < 10) {
                    return;
                }
                ax.cf.i.x(this.h, this.l);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (RuntimeException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.a = null;
    }

    public static c t() {
        if (m == null) {
            d.b(new Exception("Shizuku not initialized"));
        }
        return m;
    }

    public static void z(Context context) {
        if (m == null) {
            m = new c(context);
        }
    }

    public boolean A() {
        return this.f;
    }

    public boolean B() {
        try {
            return ax.cf.i.B() != -1;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean C() {
        if (!Q.F1()) {
            return false;
        }
        if (this.c == null) {
            this.c = Boolean.valueOf(B.M(this.b, "moe.shizuku.privileged.api"));
        }
        return this.c.booleanValue();
    }

    public List<C6249y> E(C6248x c6248x, K k, String str) {
        try {
            List<ax.p326u3.a> listN4 = this.a.N4(str);
            if (listN4 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<ax.p326u3.a> it = listN4.iterator();
            while (it.hasNext()) {
                arrayList.add(new C6249y(c6248x, k, it.next()));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void F(Fragment fragment) {
        if (fragment.getContext() == null) {
            return;
        }
        try {
            Intent launchIntentForPackage = fragment.getContext().getPackageManager().getLaunchIntentForPackage("moe.shizuku.privileged.api");
            if (launchIntentForPackage != null) {
                C2014u.p0(fragment, launchIntentForPackage);
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (AndroidRuntimeException e2) {
            e = e2;
            e.printStackTrace();
        } catch (NullPointerException e3) {
            e = e3;
            e.printStackTrace();
        } catch (SecurityException e4) {
            e = e4;
            e.printStackTrace();
        }
    }

    public void G() {
        int i = this.e - 1;
        this.e = i;
        if (i <= 0) {
            ax.cf.i.H(this.i);
            ax.cf.i.G(this.j);
            ax.cf.i.I(this.k);
            K();
        }
    }

    public boolean H(String str, String str2) {
        try {
            return this.a.H4(str, str2);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean I(String str, long j) {
        try {
            return this.a.E2(str, j);
        } catch (Exception unused) {
            return false;
        }
    }

    public void j() {
        if (this.e == 0) {
            ax.cf.i.r(this.i);
            ax.cf.i.o(this.j);
            ax.cf.i.t(this.k);
            k();
        }
        this.e++;
    }

    public boolean l() {
        if (C() && B()) {
            return D();
        }
        return false;
    }

    public boolean m(i.a aVar) {
        this.f = false;
        if (!B() || ax.cf.i.D()) {
            return false;
        }
        try {
            if (ax.cf.i.y() == 0) {
                return true;
            }
            if (ax.cf.i.P()) {
                return false;
            }
            this.g = aVar;
            ax.cf.i.J(65001);
            this.f = true;
        } catch (Throwable unused) {
        }
        return false;
    }

    public boolean o(String str) {
        try {
            return this.a.R7(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean p(String str) {
        try {
            return this.a.p6(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean q(String str) {
        try {
            return this.a.S(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public AbstractC6239n r(C6248x c6248x, K k, String str) {
        try {
            return new C6249y(c6248x, k, this.a.a1(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public InputStream s(String str, long j) throws IOException {
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.a.r3(str));
            if (j > 0 && autoCloseInputStream.skip(j) != j) {
                throw new IOException("AutoCloseInputStream skip failed");
            }
            return autoCloseInputStream;
        } catch (IOException e) {
            throw e;
        } catch (IllegalStateException e2) {
            com.alphainventor.filemanager.shizuku.b.c cVarE = com.alphainventor.filemanager.shizuku.b.e(e2);
            if (cVarE == null) {
                throw new IOException(e2);
            }
            if (cVarE.a == com.alphainventor.filemanager.shizuku.b.d) {
                throw new FileNotFoundException(cVarE.b);
            }
            throw new IOException(cVarE.b);
        } catch (Exception e3) {
            throw new IOException(e3);
        }
    }

    public int u(String str, boolean z) {
        try {
            return this.a.f7(str, z);
        } catch (Exception unused) {
            return -2;
        }
    }

    public OutputStream v(String str, boolean z) throws IOException {
        try {
            return new ParcelFileDescriptor.AutoCloseOutputStream(this.a.X6(str, z));
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

    public ParcelFileDescriptor w(String str) throws IOException {
        try {
            return this.a.r3(str);
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

    public ParcelFileDescriptor x(String str) throws IOException {
        try {
            return this.a.i3(str);
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

    public L y(String str) throws IOException {
        try {
            ax.p326u3.a aVarD4 = this.a.d4(str);
            L l = new L();
            l.e = aVarD4.Y;
            l.b = aVarD4.Z;
            l.a = aVarD4.n0;
            l.c = aVarD4.o0;
            return l;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
