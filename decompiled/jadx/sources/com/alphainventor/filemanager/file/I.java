package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.Sa.InterfaceC1531a;
import ax.p070e3.C1957a;
import ax.p070e3.C1958b;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1964h;
import ax.p070e3.C1965i;
import ax.p070e3.C1966j;
import ax.p070e3.C1967k;
import ax.p070e3.C1968l;
import ax.p086f3.C2001g;
import ax.p086f3.C2017x;
import ax.p086f3.a0;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p192ld.InterfaceC2156f;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import com.alphainventor.filemanager.file.I;
import com.pcloud.sdk.AuthorizationActivity;
import j$.net.URLEncoder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class I extends AbstractC6238m {
    private static final Logger l = Logger.getLogger("FileManager.PCloudFileHelper");
    private static final String m = StandardCharsets.UTF_8.name();
    private static d n;
    private InterfaceC1531a h;
    private ax.Sa.A i;
    private ax.Sa.x j;
    private final Object k = new Object();

    class a extends ax.Sa.m {
        final /* synthetic */ InterfaceC2462c b;
        final /* synthetic */ ax.p086f3.G c;
        final /* synthetic */ long d;

        a(InterfaceC2462c interfaceC2462c, ax.p086f3.G g, long j) {
            this.b = interfaceC2462c;
            this.c = g;
            this.d = j;
        }

        @Override // ax.Sa.m
        public long a() {
            return this.d;
        }

        @Override // ax.Sa.m
        public void b(InterfaceC2156f interfaceC2156f) throws IOException {
            try {
                I.d0(this.b);
                InputStream inputStreamB = this.c.b();
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        I.d0(this.b);
                        int i = inputStreamB.read(bArr);
                        if (i < 0) {
                            inputStreamB.close();
                            I.d0(this.b);
                            return;
                        } else if (i != 0) {
                            interfaceC2156f.write(bArr, 0, i);
                        }
                        throw new IOException(e);
                    }
                } catch (Throwable th) {
                    if (inputStreamB != null) {
                        try {
                            inputStreamB.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (C1966j e) {
                throw new IOException(e);
            }
        }
    }

    class b implements ax.Sa.j<ax.Sa.A> {
        final /* synthetic */ InterfaceC1531a a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ AtomicBoolean d;
        final /* synthetic */ AtomicInteger e;
        final /* synthetic */ InterfaceC6228c.a f;
        final /* synthetic */ Activity g;

        class a implements Runnable {
            final /* synthetic */ boolean q;

            a(boolean z) {
                this.q = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!this.q) {
                    InterfaceC6228c.a aVar = b.this.f;
                    if (aVar != null) {
                        aVar.d0(false, null);
                        return;
                    }
                    return;
                }
                Activity activity = b.this.g;
                if (activity != null && !activity.isFinishing()) {
                    d dVarZ = I.Z(b.this.g);
                    b bVar = b.this;
                    dVarZ.y(bVar.g, bVar.f, bVar.c);
                } else {
                    InterfaceC6228c.a aVar2 = b.this.f;
                    if (aVar2 != null) {
                        aVar2.d0(false, null);
                    }
                }
            }
        }

        b(InterfaceC1531a interfaceC1531a, String str, int i, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, InterfaceC6228c.a aVar, Activity activity) {
            this.a = interfaceC1531a;
            this.b = str;
            this.c = i;
            this.d = atomicBoolean;
            this.e = atomicInteger;
            this.f = aVar;
            this.g = activity;
        }

        @Override // ax.Sa.j
        public void a(ax.Sa.i<ax.Sa.A> iVar, Throwable th) {
            I.this.c0(null, null);
            boolean z = (th instanceof ax.Sa.b) && ((ax.Sa.b) th).a() == 2095;
            if (this.d.compareAndSet(false, true)) {
                this.e.set(2);
                I.this.N(new a(z));
                return;
            }
            int i = this.e.get();
            ax.p302sb.c.h().g().d("PCloud onConnectResult Twice 2").h("called:" + i).i();
        }

        @Override // ax.Sa.j
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(ax.Sa.i<ax.Sa.A> iVar, ax.Sa.A a2) {
            I.this.c0(this.a, a2);
            if (TextUtils.isEmpty(this.b)) {
                I.Z(I.this.p()).w(this.c, a2);
            }
            if (this.d.compareAndSet(false, true)) {
                this.e.set(1);
                I.this.L(this.f, true, null);
                return;
            }
            int i = this.e.get();
            ax.p302sb.c.h().g().d("PCloud onConnectResult Twice 1").h("called:" + i).i();
        }
    }

    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.Sa.h.values().length];
            a = iArr;
            try {
                iArr[ax.Sa.h.ACCESS_GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.Sa.h.ACCESS_DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.Sa.h.AUTH_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ax.Sa.h.CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static class d extends T {
        private Context a;
        private ax.p182l3.k b;
        private InterfaceC6228c.a c;
        private int d;
        private boolean e;
        c0 f = new c0(ax.U2.f.W0);

        class a implements Runnable {
            final /* synthetic */ int q;

            a(int i) {
                this.q = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (d.this.b != null) {
                    d.this.b.d(ax.U2.f.W0, this.q);
                    d.this.b = null;
                }
                if (d.this.c != null) {
                    if (d.this.e && this.q == d.this.d) {
                        d.this.c.d0(false, new e());
                    } else {
                        d.this.c.d0(false, null);
                    }
                    d.this.c = null;
                }
            }
        }

        public d(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("PCloudCloudPrefs", 0).edit().remove("version_" + i).remove("accountid_" + i).remove("email_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).remove("apihost_" + i).remove("token_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("PCloudCloudPrefs", 0);
            String string = sharedPreferences.getString("email_" + i, null);
            ax.U2.f fVar = ax.U2.f.W0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("PCloudCloudPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.apply();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("PCloudCloudPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void q(com.alphainventor.filemanager.activity.a aVar, ax.p182l3.k kVar) {
            kVar.a(ax.U2.f.W0);
            this.b = kVar;
            x(aVar, false);
        }

        int r(String str) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("PCloudCloudPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.contains("accountid_" + i2) && !TextUtils.isEmpty(str)) {
                    if (str.equals(sharedPreferences.getString("accountid_" + i2, null))) {
                        return i2;
                    }
                }
            }
            return -1;
        }

        int s() {
            return this.a.getSharedPreferences("PCloudCloudPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> t() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("PCloudCloudPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("accountid_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        /* JADX WARN: Code duplicated, block: B:46:0x00ab  */
        /* JADX WARN: Code duplicated, block: B:49:0x00bb  */
        /* JADX WARN: Code duplicated, block: B:57:? A[RETURN, SYNTHETIC] */
        public void u(int i, Intent intent) {
            ax.p182l3.k kVar;
            InterfaceC6228c.a aVar;
            InterfaceC6228c.a aVar2;
            if (intent == null) {
                InterfaceC6228c.a aVar3 = this.c;
                if (aVar3 != null) {
                    aVar3.d0(false, null);
                    this.c = null;
                    return;
                }
                return;
            }
            try {
                com.pcloud.sdk.a aVarH = AuthorizationActivity.h(intent);
                int i2 = c.a[aVarH.X.ordinal()];
                if (i2 == 1) {
                    int iS = s();
                    int iR = r(String.valueOf(aVarH.Z));
                    if (iR >= 0) {
                        iS = iR;
                    }
                    v(iS, aVarH);
                    new Handler(Looper.getMainLooper()).post(new a(iS));
                    return;
                }
                if (i2 == 2) {
                    ax.p182l3.k kVar2 = this.b;
                    if (kVar2 != null) {
                        kVar2.c(ax.U2.f.W0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, "Account access denied");
                    }
                    InterfaceC6228c.a aVar4 = this.c;
                    if (aVar4 != null) {
                        aVar4.d0(false, null);
                        this.c = null;
                        return;
                    }
                    return;
                }
                if (i2 != 3) {
                    if (i2 == 4 && (aVar2 = this.c) != null) {
                        aVar2.d0(false, null);
                        this.c = null;
                        return;
                    }
                    return;
                }
                ax.p182l3.k kVar3 = this.b;
                if (kVar3 != null) {
                    kVar3.c(ax.U2.f.W0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, "Account access grant error:\n" + aVarH.n0);
                }
                InterfaceC6228c.a aVar5 = this.c;
                if (aVar5 != null) {
                    aVar5.d0(false, null);
                    this.c = null;
                }
            } catch (IllegalArgumentException e) {
                e = e;
                e.printStackTrace();
                kVar = this.b;
                if (kVar != null) {
                    kVar.c(ax.U2.f.W0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, "Exception");
                }
                aVar = this.c;
                if (aVar != null) {
                    aVar.d0(false, null);
                    this.c = null;
                }
            } catch (NullPointerException e2) {
                e = e2;
                e.printStackTrace();
                kVar = this.b;
                if (kVar != null) {
                    kVar.c(ax.U2.f.W0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, "Exception");
                }
                aVar = this.c;
                if (aVar != null) {
                    aVar.d0(false, null);
                    this.c = null;
                }
            }
        }

        void v(int i, com.pcloud.sdk.a aVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("PCloudCloudPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            c0 c0Var = this.f;
            ax.p022b3.a aVarA = ax.p022b3.a.a();
            ax.U2.f fVar = ax.U2.f.W0;
            editorEdit.putInt("version_" + i, 3).putString("apihost_" + i, aVar.m0).putString("accountid_" + i, String.valueOf(aVar.Z)).putString("token_" + i, c0Var.e(aVarA.b(fVar), aVar.Y)).putString("location_name_" + i, fVar.L(this.a));
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.apply();
        }

        public void w(int i, ax.Sa.A a2) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("PCloudCloudPrefs", 0).edit();
            editorEdit.putString("email_" + i, a2.b());
            editorEdit.apply();
        }

        public void x(Activity activity, boolean z) {
            activity.startActivityForResult(AuthorizationActivity.e(activity, com.pcloud.sdk.b.a().j(com.pcloud.sdk.b.c.TOKEN).h(C2001g.c(activity).a(ax.U2.f.W0)).i(true).f("manageshares").g()), 10006);
            this.e = z;
        }

        public void y(Activity activity, InterfaceC6228c.a aVar, int i) {
            this.c = aVar;
            this.d = i;
            x(activity, true);
        }
    }

    public static class e {
    }

    public static /* synthetic */ void S(ax.p182l3.i iVar, long j, long j2) {
        if (iVar != null) {
            iVar.a(j, j2);
        }
    }

    private void V() throws C1964h {
        if (!a()) {
            throw new C1964h("Not connected");
        }
    }

    private C1966j W(String str, Exception exc) {
        if (exc instanceof ax.Sa.b) {
            int iA = ((ax.Sa.b) exc).a();
            if (iA == 2003 || iA == 1000 || iA == 2007) {
                return new C1961e(exc);
            }
            if (b0(iA)) {
                return new ax.p070e3.t();
            }
            if (iA == 2008) {
                return new ax.p070e3.s(exc);
            }
            if (iA == 2006) {
                return new C1967k();
            }
            if (iA == 2004) {
                return new C1962f(false);
            }
            if (iA == 2028) {
                return new C1968l(exc);
            }
        } else if (exc instanceof ax.Ua.a) {
            return new C1965i(String.format("Error (%s)", Integer.valueOf(((ax.Ua.a) exc).a())), exc);
        }
        return C1960d.b(str, exc);
    }

    private void X(AbstractC6239n abstractC6239n, boolean z) throws C1966j {
        V();
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        try {
            String strY = Y(abstractC6239n.E());
            Boolean boolExecute = (abstractC6239n.isDirectory() ? this.h.i(strY, z) : this.h.f(strY)).execute();
            if (boolExecute == null || !boolExecute.booleanValue()) {
                throw new C1966j("Failed to delete");
            }
        } catch (Exception e2) {
            throw W("deleteFile", e2);
        }
    }

    private String Y(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, m);
    }

    public static d Z(Context context) {
        if (n == null) {
            n = new d(context.getApplicationContext());
        }
        return n;
    }

    private ax.Sa.x a0() {
        if (this.j == null) {
            int iS = (int) (ax.p406z3.B.s(p()) * 128.0f);
            if (iS > 1024) {
                iS = 1024;
            }
            this.j = new ax.Sa.x(iS, iS);
        }
        return this.j;
    }

    private boolean b0(int i) {
        return i == 2055 || i == 2001 || i == 2002 || i == 2009 || i == 2005;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(InterfaceC1531a interfaceC1531a, ax.Sa.A a2) {
        synchronized (this.k) {
            this.h = interfaceC1531a;
            this.i = a2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d0(InterfaceC2462c interfaceC2462c) throws C1958b {
        if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
            throw new C1958b();
        }
    }

    private void e0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l2, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        if (!z) {
            C2461b.a(abstractC6239n.o());
        }
        V();
        if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
            throw new C1957a();
        }
        if (j < 0) {
            g0(abstractC6239n, g, str, l2, z, interfaceC2462c, iVar);
        } else {
            f0(abstractC6239n, g, str, j, l2, z, interfaceC2462c, iVar);
        }
    }

    private void f0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l2, boolean z, InterfaceC2462c interfaceC2462c, final ax.p182l3.i iVar) throws C1957a, C1966j {
        try {
            a aVar = new a(interfaceC2462c, g, j);
            Date date = l2 != null ? new Date(l2.longValue()) : null;
            ax.Sa.r rVar = new ax.Sa.r() { // from class: ax.f3.Z
                @Override // ax.Sa.r
                public final void a(long j2, long j3) {
                    I.S(iVar, j2, j3);
                }
            };
            ax.Sa.y.a aVarA = ax.Sa.y.a();
            if (z) {
                aVarA.b(z);
            }
            ax.Sa.v vVarExecute = this.h.d(Y(abstractC6239n.U()), abstractC6239n.B(), aVar, date, rVar, aVarA.a()).execute();
            if (!z && vVarExecute != null && vVarExecute.i() != null && !vVarExecute.i().equals(abstractC6239n.B())) {
                throw new C1962f(vVarExecute.l());
            }
        } catch (C1966j e2) {
            throw e2;
        } catch (IOException e3) {
            if (!C1960d.d(e3, C1958b.class)) {
                throw W("writeFile", e3);
            }
            throw new C1957a(e3);
        } catch (Exception e4) {
            throw W("writeFile", e4);
        }
    }

    private void g0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, Long l2, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        File file;
        InputStream inputStream = null;
        try {
            try {
                InputStream inputStreamB = g.b();
                try {
                    file = new File(ax.p038c3.a.l(p(), abstractC6239n), abstractC6239n.B() + System.currentTimeMillis() + ".tmp");
                    try {
                        ax.p086f3.F.f(inputStreamB, file, -1L, interfaceC2462c);
                        try {
                            C6240o c6240oF = C2017x.f(file);
                            f0(abstractC6239n, c6240oF.x(c6240oF.a1(file.getAbsolutePath())), str, file.length(), l2, z, interfaceC2462c, iVar);
                            ax.p086f3.F.a(null);
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (C1957a e2) {
                            e = e2;
                        } catch (C1966j e3) {
                            e = e3;
                        } catch (IOException e4) {
                            e = e4;
                            throw W("pcloud upload unknown size", e);
                        } catch (Throwable th) {
                            th = th;
                            ax.p086f3.F.a(inputStream);
                            if (file != null) {
                                file.delete();
                            }
                            throw th;
                        }
                    } catch (C1957a e5) {
                        e = e5;
                    } catch (C1966j e6) {
                        e = e6;
                    } catch (IOException e7) {
                        e = e7;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = inputStreamB;
                        ax.p086f3.F.a(inputStream);
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                        throw th;
                    }
                } catch (C1957a e8) {
                } catch (C1966j e9) {
                } catch (IOException e10) {
                    e = e10;
                }
            } catch (Throwable th3) {
                th = th3;
                file = null;
            }
        } catch (C1957a e11) {
            throw e11;
        } catch (C1966j e12) {
            throw e12;
        } catch (IOException e13) {
            e = e13;
        } catch (Throwable th4) {
            th = th4;
            file = null;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean O() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean P() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l2, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        e0(abstractC6239n, g, str, j, l2, true, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        boolean z;
        synchronized (this.k) {
            z = this.h != null;
        }
        return z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        V();
        if (d0.a.equals(str)) {
            return new a0(this, str);
        }
        try {
            ax.Sa.v vVarExecute = this.h.k(Y(str)).execute();
            return new a0(this, a0.j0(vVarExecute.p()), vVarExecute, str);
        } catch (Exception e2) {
            if ((e2 instanceof ax.Sa.b) && b0(((ax.Sa.b) e2).a())) {
                try {
                    return new a0(this, (a0) a1(d0.r(str)), str);
                } catch (C1966j unused) {
                    throw W("getFileInfo", e2);
                }
            }
            throw W("getFileInfo", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) throws IOException {
        String strDecode;
        a0 a0Var;
        if (str3 != null) {
            try {
                if (str3.startsWith("fileid=")) {
                    strDecode = Uri.decode(str3.substring(7));
                } else {
                    a0Var = (a0) a1(str2);
                    if (a0Var == null && !a0Var.o()) {
                        return null;
                    }
                    strDecode = a0Var.u();
                }
            } catch (C1966j | NumberFormatException | Exception unused) {
                return null;
            }
        } else {
            a0Var = (a0) a1(str2);
            if (a0Var == null) {
            }
            strDecode = a0Var.u();
        }
        if (this.h != null && strDecode != null && !strDecode.isEmpty() && strDecode.charAt(0) == 'f') {
            long jH0 = a0.h0(strDecode);
            if (jH0 < 0) {
                return null;
            }
            return this.h.n(jH0, a0(), true).execute().e1();
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        if (this.h != null) {
            C2461b.c(abstractC6239n.isDirectory());
            try {
                ArrayList arrayList = new ArrayList();
                for (ax.Sa.u uVar : this.h.j(Y(abstractC6239n.E())).execute().b()) {
                    arrayList.add(new a0(this, abstractC6239n.u(), uVar, d0.Q(abstractC6239n.E(), uVar.i())));
                }
                return arrayList;
            } catch (Exception e2) {
                throw W("listChildren", e2);
            }
        }
        C1966j c1966j = new C1966j("FolderApi == null:connected = " + a() + ":" + (this.h == null));
        ax.U2.d.b(c1966j);
        throw c1966j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (!((a0) abstractC6239n).i0()) {
            return null;
        }
        return ax.p086f3.B.a0(abstractC6239n, "fileid=" + Uri.encode(abstractC6239n.u()));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        X(abstractC6239n, true);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        V();
        if (((a0) abstractC6239n).g0() == null) {
            throw new ax.p070e3.t();
        }
        try {
            ax.Sa.p pVarExecute = this.h.m(Y(abstractC6239n.E()), ax.Sa.n.b().c(true).d(true).a()).execute();
            URL urlA = pVarExecute != null ? pVarExecute.a() : null;
            if (urlA == null) {
                throw new C1966j("No pCloud download URL");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlA.openConnection();
            if (j > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (j > 0 && responseCode != 206) {
                throw new IOException("Range request failed: " + responseCode);
            }
            if (responseCode >= 200 && responseCode < 300) {
                return httpURLConnection.getInputStream();
            }
            throw new IOException("HTTP status: " + responseCode);
        } catch (C1966j e2) {
            throw e2;
        } catch (Exception e3) {
            throw W("getInputStream", e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        V();
        try {
            String strY = Y(abstractC6239n.E());
            String strY2 = Y(abstractC6239n2.E());
            if (abstractC6239n.isDirectory()) {
                if (this.h.a(strY, strY2).execute() == null) {
                    throw new C1966j("No moved Folder");
                }
            } else if (this.h.g(strY, strY2).execute() == null) {
                throw new C1966j("No moved file");
            }
        } catch (Exception e2) {
            throw W("moveFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        InterfaceC6228c.a aVar2;
        if (aVar != null) {
            aVar.J();
        }
        int iT = t();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        SharedPreferences sharedPreferences = p().getSharedPreferences("PCloudCloudPrefs", 0);
        sharedPreferences.getString("accountid_" + iT, null);
        String string = sharedPreferences.getString("email_" + iT, null);
        try {
            ax.U2.f fVar = ax.U2.f.W0;
            c0 c0Var = new c0(fVar);
            int i = sharedPreferences.getInt("version_" + t(), 0);
            String string2 = sharedPreferences.getString("token_" + t(), null);
            if (i != 0) {
                string2 = c0Var.a(i, ax.p022b3.a.a().b(fVar), string2);
            }
            String string3 = sharedPreferences.getString("apihost_" + iT, null);
            if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                InterfaceC1531a interfaceC1531aA = ax.Sa.q.a().b(ax.Sa.e.b(string2)).c(string3).a();
                interfaceC1531aA.h().c0(new b(interfaceC1531aA, string, iT, atomicBoolean, atomicInteger, aVar, activity));
                return;
            } else {
                aVar2 = aVar;
                try {
                    c0(null, null);
                    L(aVar2, false, null);
                    return;
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            aVar2 = aVar;
        }
        c0(null, null);
        L(aVar2, false, null);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l2, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        e0(abstractC6239n, g, str, j, l2, false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        if (!z2) {
            o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
        } else {
            V();
            o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        V();
        try {
            if (this.h.c(Y(abstractC6239n.E()), Y(abstractC6239n2.E()), false).execute() == null) {
                throw new C1966j("No copied file");
            }
            if (iVar != null) {
                long jP = abstractC6239n.p();
                iVar.a(jP, jP);
            }
        } catch (Exception e2) {
            throw W("copyFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        if (!a()) {
            return false;
        }
        try {
            ax.Sa.w wVarExecute = this.h.l(Y(abstractC6239n.E())).execute();
            if (wVarExecute == null) {
                return false;
            }
            if (abstractC6239n.B().equals(wVarExecute.i())) {
                return true;
            }
            wVarExecute.delete();
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        if (!a() || abstractC6239n.o()) {
            return false;
        }
        try {
            ax.Sa.m mVar = ax.Sa.m.a;
            ax.Sa.v vVarExecute = this.h.b(Y(abstractC6239n.U()), abstractC6239n.B(), mVar, ax.Sa.y.c).execute();
            if (vVarExecute == null) {
                return false;
            }
            if (abstractC6239n.B().equals(vVarExecute.i())) {
                return true;
            }
            vVarExecute.delete();
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        X(abstractC6239n, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        V();
        try {
            ax.Sa.A aExecute = this.h.h().execute();
            if (aExecute != null) {
                return new k0(aExecute.a(), aExecute.c());
            }
            throw new C1966j("No pCloud user info");
        } catch (C1966j e2) {
            throw e2;
        } catch (Exception e3) {
            throw W("getStorageSpace", e3);
        }
    }
}
