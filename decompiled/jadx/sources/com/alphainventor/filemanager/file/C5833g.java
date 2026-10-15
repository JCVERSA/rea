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
import ax.I1.C0712c;
import ax.I1.C0718i;
import ax.J1.C0727h;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.EnumC0738t;
import ax.J1.V;
import ax.J1.b0;
import ax.P5.C1452Re;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(C1452Re.zzm)
public class C5833g extends AbstractC5830d {
    private AtomicBoolean h = new AtomicBoolean(false);
    private Uri i;

    private InputStream O(String str) throws C0718i, IOException {
        return new ParcelFileDescriptor.AutoCloseInputStream(P(str));
    }

    private ParcelFileDescriptor P(String str) throws C0718i, IOException {
        return C5832f.o(n(), C5832f.e(s(), this.i, str), "r");
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5830d
    public void N(String str, long j) throws IOException {
        try {
            C5832f.K(this, V0(str), j);
        } catch (C0718i e) {
            throw new IOException(e);
        }
    }

    public Uri Q() {
        return this.i;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        try {
            try {
                try {
                    Cursor cursorQuery = n().getContentResolver().query(C5832f.e(s(), this.i, str), C0727h.g, null, null, null);
                    if (cursorQuery == null) {
                        throw new C0718i("query return null");
                    }
                    if (cursorQuery.moveToFirst()) {
                        C5834h c5834h = new C5834h(this, V.r(str), cursorQuery);
                        C0744z.a(cursorQuery);
                        return c5834h;
                    }
                    C5834h c5834h2 = new C5834h(this, str);
                    C0744z.a(cursorQuery);
                    return c5834h2;
                } catch (IllegalArgumentException e) {
                    if (!TextUtils.isEmpty(e.getMessage()) && !e.getMessage().contains("FileNotFoundException")) {
                        e.printStackTrace();
                        ax.p221pa.c.h().g().b("DOCUMENT FILE EXCEPTION DO NOT CONTAIN FILENOTFOUND").m(e).i();
                    }
                    C5834h c5834h3 = new C5834h(this, str);
                    C0744z.a(null);
                    return c5834h3;
                }
            } catch (Exception e2) {
                throw C0712c.a("getFileInfo : " + e2.getClass().getSimpleName() + ":" + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            C0744z.a(null);
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x004e  */
    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) throws Throwable {
        ParcelFileDescriptor parcelFileDescriptorP;
        byte[] bArrJ;
        ParcelFileDescriptor parcelFileDescriptor = null;
        bitmapB = null;
        Bitmap bitmapB = null;
        if (!a()) {
            return null;
        }
        EnumC0738t enumC0738tE = C0739u.e(V.k(str2));
        if (EnumC0738t.IMAGE == enumC0738tE) {
            try {
                return O(str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (EnumC0738t.VIDEO == enumC0738tE) {
                try {
                    try {
                        parcelFileDescriptorP = P(str2);
                        try {
                            bitmapB = ax.p045d2.x.g(n(), null, parcelFileDescriptorP.getFileDescriptor(), null, 512);
                        } catch (C0718i | IOException unused) {
                            if (parcelFileDescriptorP != null) {
                            }
                            if (bitmapB == null) {
                                bitmapB = ax.p017b2.a.b();
                            }
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmapB.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        } catch (Throwable th) {
                            th = th;
                            parcelFileDescriptor = parcelFileDescriptorP;
                            if (parcelFileDescriptor != null) {
                                try {
                                    parcelFileDescriptor.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } catch (C0718i | IOException unused3) {
                        parcelFileDescriptorP = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    parcelFileDescriptorP.close();
                } catch (IOException unused4) {
                }
                if (bitmapB == null) {
                    bitmapB = ax.p017b2.a.b();
                }
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmapB.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream2);
                return new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
            }
            if (EnumC0738t.AUDIO == enumC0738tE) {
                try {
                    ParcelFileDescriptor parcelFileDescriptorP2 = P(str2);
                    bArrJ = ax.p045d2.x.j(n(), null, parcelFileDescriptorP2.getFileDescriptor());
                    try {
                        parcelFileDescriptorP2.close();
                    } catch (C0718i | IOException unused5) {
                    }
                } catch (C0718i | IOException unused6) {
                    bArrJ = null;
                }
                if (bArrJ != null) {
                    return new ByteArrayInputStream(bArrJ);
                }
            }
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return ax.E1.P.B0();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (abstractC5838l == null) {
            return null;
        }
        EnumC0738t enumC0738tE = abstractC5838l.E();
        if (EnumC0738t.IMAGE == enumC0738tE || EnumC0738t.AUDIO == enumC0738tE || EnumC0738t.VIDEO == enumC0738tE) {
            return C0740v.Q(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.h.get();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        this.h.compareAndSet(true, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p045d2.b.g("not support delete file recursively");
    }

    @Override // ax.J1.Q
    public OutputStream c(String str, boolean z) throws IOException {
        try {
            return C5832f.r(this, V0(str), z);
        } catch (C0718i e) {
            throw new IOException(e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        if (abstractC5838l.n()) {
            throw new C0718i("DocumentFile writefile already exists");
        }
        C5832f.L(this, abstractC5838l, a, j, l, z, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        return C5832f.q(n(), abstractC5838l, j);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        if (aVar != null) {
            aVar.N();
        }
        if (!ax.E1.P.B1() || !C5832f.z(n(), s(), null)) {
            this.h.compareAndSet(true, false);
            if (aVar != null) {
                aVar.f0(false, null);
                return;
            }
            return;
        }
        this.i = Uri.parse(C5832f.u(n(), s(), null));
        this.h.compareAndSet(false, true);
        if (aVar != null) {
            aVar.f0(true, null);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        return C5832f.E(this, abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        try {
            return (abstractC5838l.n() || C5832f.d(this, abstractC5838l, true) == null) ? false : true;
        } catch (C0718i e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        try {
            return (abstractC5838l.n() || C5832f.d(this, abstractC5838l, false) == null) ? false : true;
        } catch (C0718i unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        m(abstractC5838l, str, z, hVar, cVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        ax.p045d2.b.a(abstractC5838l2.n());
        Context contextN = n();
        if (!(!abstractC5838l.S().equals(abstractC5838l2.S()))) {
            C5832f.H(contextN, abstractC5838l, abstractC5838l2, cVar, iVar);
        } else if (ax.E1.P.B0() && abstractC5838l.P() == abstractC5838l2.P()) {
            C5832f.F(contextN, abstractC5838l, abstractC5838l2, cVar, iVar);
        } else {
            C5832f.G(this, abstractC5838l, abstractC5838l2, cVar, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws Throwable {
        C5832f.g(this, abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        return C5832f.k(n(), this, s(), this.i);
    }
}
