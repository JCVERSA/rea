package com.alphainventor.filemanager.file;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1960d;
import ax.p070e3.C1966j;
import ax.p086f3.C2006l;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import ax.p409z6.C3772df;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(C3772df.zzm)
public class C6233h extends AbstractC6230e {
    private AtomicBoolean h = new AtomicBoolean(false);
    private Uri i;

    private InputStream V(String str) throws C1966j, IOException {
        return new ParcelFileDescriptor.AutoCloseInputStream(W(str));
    }

    private ParcelFileDescriptor W(String str) throws C1966j, IOException {
        return C6232g.o(p(), ((C6234i) a1(str)).o0(), "r");
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6230e
    public void T(String str, long j) throws IOException {
        try {
            C6232g.P(this, a1(str), j);
        } catch (C1966j e) {
            throw new IOException(e);
        }
    }

    protected AbstractC6239n U(String str, Uri uri) throws C1966j {
        try {
            try {
                Cursor cursorQuery = p().getContentResolver().query(uri, C2006l.g, null, null, null);
                if (cursorQuery == null) {
                    throw new C1966j("query return null");
                }
                if (cursorQuery.moveToFirst()) {
                    C6234i c6234i = new C6234i(this, d0.r(str), cursorQuery);
                    ax.p086f3.F.a(cursorQuery);
                    return c6234i;
                }
                C6234i c6234i2 = new C6234i(this, str);
                ax.p086f3.F.a(cursorQuery);
                return c6234i2;
            } catch (IllegalArgumentException e) {
                if (!TextUtils.isEmpty(e.getMessage()) && !e.getMessage().contains("FileNotFoundException")) {
                    e.printStackTrace();
                    ax.p302sb.c.h().g().b("DOCUMENT FILE EXCEPTION DO NOT CONTAIN FILENOTFOUND").m(e).i();
                }
                C6234i c6234i3 = new C6234i(this, str);
                ax.p086f3.F.a(null);
                return c6234i3;
            } catch (Exception e2) {
                throw C1960d.a("getFileInfo : " + e2.getClass().getSimpleName() + ":" + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            ax.p086f3.F.a(null);
            throw th;
        }
    }

    public Uri X() {
        return this.i;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.h.get();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        return U(str, C6232g.e(v(), this.i, str));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        this.h.compareAndSet(true, false);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x004e  */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) throws Throwable {
        ParcelFileDescriptor parcelFileDescriptorW;
        byte[] bArrK;
        ParcelFileDescriptor parcelFileDescriptor = null;
        bitmapB = null;
        Bitmap bitmapB = null;
        if (!a()) {
            return null;
        }
        EnumC2019z enumC2019zG = ax.p086f3.A.g(d0.k(str2));
        if (EnumC2019z.IMAGE == enumC2019zG) {
            try {
                return V(str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (EnumC2019z.VIDEO == enumC2019zG) {
                try {
                    try {
                        parcelFileDescriptorW = W(str2);
                        try {
                            bitmapB = ax.p406z3.B.g(p(), null, parcelFileDescriptorW.getFileDescriptor(), null, 512);
                        } catch (C1966j | IOException unused) {
                            if (parcelFileDescriptorW != null) {
                            }
                            if (bitmapB == null) {
                                bitmapB = ax.p374x3.a.b();
                            }
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmapB.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        } catch (Throwable th) {
                            th = th;
                            parcelFileDescriptor = parcelFileDescriptorW;
                            if (parcelFileDescriptor != null) {
                                try {
                                    parcelFileDescriptor.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } catch (C1966j | IOException unused3) {
                        parcelFileDescriptorW = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    parcelFileDescriptorW.close();
                } catch (IOException unused4) {
                }
                if (bitmapB == null) {
                    bitmapB = ax.p374x3.a.b();
                }
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmapB.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream2);
                return new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
            }
            if (EnumC2019z.AUDIO == enumC2019zG) {
                try {
                    ParcelFileDescriptor parcelFileDescriptorW2 = W(str2);
                    bArrK = ax.p406z3.B.k(p(), null, parcelFileDescriptorW2.getFileDescriptor());
                    try {
                        parcelFileDescriptorW2.close();
                    } catch (C1966j | IOException unused5) {
                    }
                } catch (C1966j | IOException unused6) {
                    bArrK = null;
                }
                if (bArrK != null) {
                    return new ByteArrayInputStream(bArrK);
                }
            }
        }
        return null;
    }

    public OutputStream c(String str, boolean z) throws IOException {
        try {
            return C6232g.u(this, a1(str), z);
        } catch (C1966j e) {
            throw new IOException(e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return ax.p006a3.Q.J0();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        return C6232g.J(this, abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return null;
        }
        EnumC2019z enumC2019zG = abstractC6239n.G();
        if (EnumC2019z.IMAGE == enumC2019zG || EnumC2019z.AUDIO == enumC2019zG || EnumC2019z.VIDEO == enumC2019zG) {
            return ax.p086f3.B.Y(abstractC6239n);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        C2461b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        return C6232g.t(p(), abstractC6239n, j);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        C2461b.a(abstractC6239n2.o());
        Context contextP = p();
        if (abstractC6239n.U().equals(abstractC6239n2.U())) {
            C6232g.M(contextP, abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
        } else if (ax.p006a3.Q.J0() && abstractC6239n.Q() == abstractC6239n2.Q()) {
            C6232g.K(contextP, abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
        } else {
            C6232g.L(this, abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        if (aVar != null) {
            aVar.J();
        }
        if (!ax.p006a3.Q.R1() || !C6232g.C(p(), v(), null)) {
            this.h.compareAndSet(true, false);
            if (aVar != null) {
                aVar.d0(false, null);
                return;
            }
            return;
        }
        this.i = Uri.parse(C6232g.x(p(), v(), null));
        this.h.compareAndSet(false, true);
        if (aVar != null) {
            aVar.d0(true, null);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        if (abstractC6239n.o()) {
            throw new C1966j("DocumentFile writefile already exists");
        }
        C6232g.Q(this, abstractC6239n, g, j, l, z, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        l1(abstractC6239n2, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        try {
            return (abstractC6239n.o() || C6232g.d(this, abstractC6239n, true) == null) ? false : true;
        } catch (C1966j e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        try {
            return (abstractC6239n.o() || C6232g.d(this, abstractC6239n, false) == null) ? false : true;
        } catch (C1966j unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws Throwable {
        C6232g.g(this, abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        if (ax.U2.f.b0(u())) {
            return C6232g.k(p(), this, v(), this.i);
        }
        return null;
    }
}
