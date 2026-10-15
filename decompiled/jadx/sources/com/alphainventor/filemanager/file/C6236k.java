package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.M4.B0;
import ax.M4.C1302a;
import ax.M4.C1310e;
import ax.M4.C1311e0;
import ax.M4.C1313f0;
import ax.M4.C1316h;
import ax.M4.C1318i;
import ax.M4.C1319i0;
import ax.M4.C1321j0;
import ax.M4.C1328n;
import ax.M4.C1330o;
import ax.M4.C1332p;
import ax.M4.C1339t;
import ax.M4.E0;
import ax.M4.EnumC1335q0;
import ax.M4.EnumC1338s0;
import ax.M4.S0;
import ax.M4.T0;
import ax.M4.u0;
import ax.M4.v0;
import ax.M4.w0;
import ax.M4.z0;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C2001g;
import ax.p086f3.C2007m;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.e0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6236k extends AbstractC6238m {
    static a p;
    private ax.D4.a h;
    private b i;
    private InterfaceC6228c.a j;
    private boolean k;
    private boolean l;
    private String m;
    private boolean n;
    private static final Logger o = Logger.getLogger("FileManager.DropboxFileHelper");
    static final HashSet<String> q = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "tiff", "tif", "gif", "webp", "ppm", "bmp", "svg", "heic", "cr2", "crw", "nef", "nrw", "sr2", "dng", "arw", "orf", "mp4", "m4v", "3gp", "3gpp", "3gpp2", "webm", "mkv", "wmv", "avi", "mpg", "mpeg", "mov", "asf", "ogv", "ts", "mts", "vob", "pdf"));

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$a */
    public static class a extends T {
        Context a;
        boolean b;
        b c;
        WeakReference<ax.p182l3.k> d;

        public a(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("DropboxPrefs", 0).edit().remove("version_" + i).remove("app_userid_" + i).remove("app_accountid_" + i).remove("access_token_" + i).remove("credential_" + i).remove("app_name_" + i).remove("location_name_" + i).remove("app_email_" + i).remove("app_rootspaceid_" + i).remove("app_userootspace_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            String string = sharedPreferences.getString("app_name_" + i, null);
            String string2 = sharedPreferences.getString("app_email_" + i, null);
            String str = TextUtils.isEmpty(string2) ? string : string2;
            ax.U2.f fVar = ax.U2.f.S0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), str, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(Activity activity, ax.p182l3.k kVar) {
            this.d = new WeakReference<>(kVar);
            ax.U2.f fVar = ax.U2.f.S0;
            kVar.a(fVar);
            boolean zK0 = C6236k.k0(activity);
            this.b = zK0;
            if (zK0) {
                return;
            }
            kVar.c(fVar, null, 0, null, null);
        }

        public void l(Activity activity, ax.p182l3.k kVar, int i, boolean z) {
            s(i, z);
            if (kVar != null) {
                kVar.d(ax.U2.f.S0, i);
            }
        }

        int m(String str, String str2) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.contains("app_accountid_" + i2)) {
                    if (TextUtils.isEmpty(str)) {
                        continue;
                    } else {
                        if (str.equals(sharedPreferences.getString("app_accountid_" + i2, null))) {
                            return i2;
                        }
                    }
                } else {
                    if (sharedPreferences.contains("app_name_" + i2) && !TextUtils.isEmpty(str2)) {
                        if (str2.equals(sharedPreferences.getString("app_name_" + i2, null))) {
                            return i2;
                        }
                    }
                }
            }
            return -1;
        }

        int n() {
            return this.a.getSharedPreferences("DropboxPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> o() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                String string = sharedPreferences.getString("credential_" + i2, null);
                String string2 = sharedPreferences.getString("access_token_" + i2, null);
                if (string != null || string2 != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        public void p(Activity activity) {
            if (this.b) {
                b bVar = this.c;
                if (bVar == null || bVar.m().equals(ax.z3.q.f.FINISHED)) {
                    ax.p182l3.k kVar = this.d.get();
                    if (kVar != null) {
                        ax.A4.a aVarA = com.dropbox.core.android.a.a();
                        if (aVarA != null) {
                            b bVar2 = new b(activity, kVar, aVarA);
                            this.c = bVar2;
                            bVar2.i(new Void[0]);
                        } else {
                            kVar.c(ax.U2.f.S0, null, 0, null, null);
                        }
                    }
                    this.b = false;
                }
            }
        }

        void q(int i, ax.A4.a aVar) {
            ax.U2.f fVar = ax.U2.f.S0;
            c0 c0Var = new c0(fVar);
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putInt("version_" + i, 3);
            editorEdit.putString("credential_" + i, c0Var.e(ax.p022b3.a.a().b(fVar), aVar.toString()));
            editorEdit.commit();
        }

        void r(int i, String str, String str2, String str3, String str4, String str5, ax.A4.a aVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            ax.U2.f fVar = ax.U2.f.S0;
            c0 c0Var = new c0(fVar);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("app_accountid_" + i, str).putInt("version_" + i, 3).putString("app_name_" + i, str2).putString("access_token_" + i, c0Var.e(ax.p022b3.a.a().b(fVar), str5)).putString("app_email_" + i, str3).putString("app_rootspaceid_" + i, str4).putString("location_name_" + i, fVar.L(this.a));
            if (aVar != null) {
                editorEdit.putString("credential_" + i, c0Var.e(ax.p022b3.a.a().b(fVar), aVar.toString()));
            } else {
                editorEdit.putString("credential_" + i, null);
            }
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }

        void s(int i, boolean z) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putBoolean("app_userootspace_" + i, z);
            editorEdit.commit();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$b */
    private static class b extends ax.p406z3.q<Void, Void, Integer> {
        private InterfaceC6228c.a h;
        ax.p182l3.k i;
        ax.A4.a j;
        Context k;
        C6236k l;
        int m;

        public b(Context context, ax.p182l3.k kVar, ax.A4.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.k = context;
            this.i = kVar;
            this.j = aVar;
        }

        public b(Context context, C6236k c6236k, InterfaceC6228c.a aVar, ax.A4.a aVar2) {
            super(ax.z3.q.e.CONNECT);
            this.k = context;
            this.h = aVar;
            this.j = aVar2;
            this.l = c6236k;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:22:0x005d A[PHI: r2
          0x005d: PHI (r2v8 java.lang.String) = 
          (r2v0 java.lang.String)
          (r2v0 java.lang.String)
          (r2v10 java.lang.String)
          (r2v10 java.lang.String)
          (r2v10 java.lang.String)
         binds: [B:10:0x002c, B:12:0x0032, B:14:0x0048, B:16:0x004e, B:18:0x0054] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Integer g(Void... voidArr) {
            String strA;
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            boolean z;
            int iN;
            String strB = HttpUrl.FRAGMENT_ENCODE_SET;
            ax.A4.a aVar = this.j;
            if (aVar == null) {
                return -1;
            }
            ax.D4.a aVarZ = C6236k.Z(aVar);
            String strB2 = null;
            try {
                ax.S4.c cVarA = aVarZ.b().a();
                String strA2 = cVarA.c().a();
                try {
                    strA = cVarA.a();
                    try {
                        strB = cVarA.b();
                        if (this.i == null || cVarA.d() == null) {
                            z = false;
                        } else {
                            strB2 = cVarA.d().b();
                            String strA3 = cVarA.d().a();
                            if (TextUtils.isEmpty(strB2) || TextUtils.isEmpty(strA3) || strB2.equals(strA3)) {
                                z = false;
                            } else {
                                z = true;
                            }
                        }
                        str4 = strB;
                        str5 = strB2;
                        str3 = strA2;
                    } catch (ax.p343v4.j unused) {
                        str = strB2;
                        str2 = strB;
                        strB = strA2;
                        str3 = strB;
                        str4 = str2;
                        str5 = str;
                        z = false;
                    }
                } catch (ax.p343v4.j unused2) {
                    strA = HttpUrl.FRAGMENT_ENCODE_SET;
                    str = null;
                    str2 = strA;
                }
            } catch (ax.p343v4.j unused3) {
                strA = HttpUrl.FRAGMENT_ENCODE_SET;
                str = null;
                str2 = strA;
            }
            String str6 = strA;
            a aVarA0 = C6236k.a0(this.k);
            C6236k c6236k = this.l;
            if (c6236k != null) {
                iN = c6236k.t();
            } else {
                iN = aVarA0.n();
                int iM = aVarA0.m(str6, str3);
                if (iM >= 0) {
                    iN = iM;
                }
            }
            int i = iN;
            aVarA0.r(i, str6, str3, str4, str5, null, this.j);
            this.m = i;
            if (this.i != null && z) {
                return -2;
            }
            C6236k c6236k2 = this.l;
            if (c6236k2 != null) {
                c6236k2.i0(str5);
                this.l.h0(aVarZ);
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            if (num.intValue() == 0) {
                InterfaceC6228c.a aVar = this.h;
                if (aVar != null) {
                    aVar.d0(true, null);
                }
                ax.p182l3.k kVar = this.i;
                if (kVar != null) {
                    kVar.d(ax.U2.f.S0, this.m);
                    return;
                }
                return;
            }
            if (num.intValue() != -2) {
                InterfaceC6228c.a aVar2 = this.h;
                if (aVar2 != null) {
                    aVar2.d0(false, null);
                }
                ax.p182l3.k kVar2 = this.i;
                if (kVar2 != null) {
                    kVar2.c(ax.U2.f.S0, null, 0, null, null);
                    return;
                }
                return;
            }
            try {
                this.i.b(ax.U2.f.S0, this.m, null);
            } catch (IllegalStateException unused) {
                InterfaceC6228c.a aVar3 = this.h;
                if (aVar3 != null) {
                    aVar3.d0(false, null);
                }
                ax.p182l3.k kVar3 = this.i;
                if (kVar3 != null) {
                    kVar3.c(ax.U2.f.S0, null, 0, null, null);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.k$c */
    private class c extends ax.p406z3.q<Void, Void, Integer> {
        Activity h;
        ax.A4.a i;
        String j;

        public c(Activity activity, String str, ax.A4.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.j = str;
            this.i = aVar;
            this.h = activity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Integer g(Void... voidArr) {
            ax.D4.a aVarY;
            try {
                ax.A4.a aVar = this.i;
                if (aVar != null) {
                    aVarY = C6236k.Z(aVar);
                } else {
                    String str = this.j;
                    if (str == null) {
                        return -2;
                    }
                    aVarY = C6236k.Y(str);
                }
                ax.A4.a aVar2 = this.i;
                if (aVar2 != null && aVar2.i() != null && this.i.a()) {
                    try {
                        aVarY.c();
                        C6236k.a0(C6236k.this.p()).q(C6236k.this.t(), this.i);
                    } catch (ax.A4.c unused) {
                        return -2;
                    }
                }
                ax.S4.c cVarA = aVarY.b().a();
                SharedPreferences sharedPreferences = C6236k.this.p().getSharedPreferences("DropboxPrefs", 0);
                String string = sharedPreferences.getString("app_email_" + C6236k.this.t(), null);
                String string2 = sharedPreferences.getString("app_rootspaceid_" + C6236k.this.t(), null);
                String strB = cVarA.d() != null ? cVarA.d().b() : null;
                String strB2 = cVarA.b();
                if (!ax.p406z3.B.j(string, strB2) || !ax.p406z3.B.j(string2, strB)) {
                    C6236k.a0(C6236k.this.p()).r(C6236k.this.t(), cVarA.a(), cVarA.c().a(), strB2, strB, this.j, this.i);
                }
                C6236k.this.i0(strB);
                C6236k.this.h0(aVarY);
                return 0;
            } catch (ax.p343v4.r e) {
                e.printStackTrace();
                return -2;
            } catch (ax.p343v4.j e2) {
                e2.printStackTrace();
                return -1;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            if (num.intValue() == 0) {
                C6236k.this.e0(true);
                return;
            }
            if (num.intValue() == -1) {
                C6236k.this.e0(false);
                return;
            }
            if (num.intValue() == -2) {
                Activity activity = this.h;
                if (activity == null) {
                    C6236k.this.e0(false);
                    return;
                }
                C6236k.this.k = C6236k.k0(activity);
                if (C6236k.this.k) {
                    return;
                }
                C6236k.this.e0(false);
            }
        }
    }

    private void W(ax.D4.a aVar, ax.p086f3.G g, long j, Long l, String str, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        long jA;
        long j2;
        long j3 = 4194304;
        if (j < 4194304) {
            C2461b.g("File too small, use upload() instead.");
            return;
        }
        boolean zA = g.a();
        int i = zA ? 5 : 1;
        Exception exc = null;
        String strA = null;
        long j4 = 0;
        int i2 = 0;
        while (i2 < i) {
            e0 e0Var = (!zA || j4 <= 0) ? new e0(g.b(), 0L, j, interfaceC2462c, iVar) : new e0(g.c(j4), j4, j, interfaceC2462c, iVar);
            try {
                if (strA == null) {
                    try {
                        try {
                            try {
                                strA = aVar.a().A().i(e0Var, j3).a();
                                j4 += j3;
                            } catch (E0 e) {
                                try {
                                    if (!e.Y.e() || !e.Y.d().d()) {
                                        throw new C1966j(e);
                                    }
                                    jA = e.Y.d().b().a();
                                    e = e;
                                    try {
                                        e0Var.close();
                                    } catch (IOException unused) {
                                    }
                                    j4 = jA;
                                    i2++;
                                    j3 = 4194304;
                                    exc = e;
                                } catch (Throwable th) {
                                    th = th;
                                    try {
                                        e0Var.close();
                                    } catch (IOException unused2) {
                                    }
                                    throw th;
                                }
                            } catch (ax.p343v4.j e2) {
                                throw X("chunk_upload", e2);
                            }
                        } catch (z0 e3) {
                            if (!e3.Y.d()) {
                                throw new C1966j(e3);
                            }
                            jA = e3.Y.b().a();
                            e = e3;
                            e0Var.close();
                            j4 = jA;
                            i2++;
                            j3 = 4194304;
                            exc = e;
                        } catch (ax.p343v4.t e4) {
                            e = e4;
                            try {
                                e0Var.close();
                            } catch (IOException unused3) {
                            }
                            i2++;
                            j3 = 4194304;
                            exc = e;
                        }
                    } catch (ax.p343v4.x e5) {
                        e = e5;
                        e0Var.close();
                        i2++;
                        j3 = 4194304;
                        exc = e;
                    } catch (IOException e6) {
                        if (!(e6.getCause() instanceof C1957a)) {
                            throw new C1966j(e6);
                        }
                        throw ((C1957a) e6.getCause());
                    }
                }
                B0 b0 = new B0(strA, j4);
                while (true) {
                    j2 = j - j4;
                    if (j2 <= j3) {
                        break;
                    }
                    aVar.a().x(b0).i(e0Var, j3);
                    j4 += j3;
                    b0 = new B0(strA, j4);
                }
                C1302a.C0146a c0146aB = C1302a.a(str).b(Boolean.FALSE);
                if (z) {
                    c0146aB.d(T0.d);
                } else {
                    c0146aB.d(T0.c);
                }
                if (l != null && l.longValue() > 0) {
                    c0146aB.c(new Date(l.longValue()));
                }
                C1339t c1339tI = aVar.a().y(b0, c0146aB.a()).i(e0Var, j2);
                if (c1339tI != null && c1339tI.f() < j) {
                    throw new C1966j("The size of the uploaded file is different from the source");
                }
                try {
                    e0Var.close();
                    return;
                } catch (IOException unused4) {
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        throw C1960d.b("Maxed out upload attempts to Dropbox", exc);
    }

    private C1966j X(String str, ax.p343v4.j jVar) {
        C1318i c1318i;
        C1316h c1316h;
        C1966j c1966jM0;
        ax.M4.K k;
        ax.M4.J j;
        C1966j c1966jC0;
        ax.M4.X x;
        ax.M4.W w;
        C1966j c1966jM1;
        w0 w0Var;
        v0 v0Var;
        C1966j c1966jM2;
        C1332p c1332p;
        C1330o c1330o;
        C1966j c1966jC1;
        if (jVar instanceof ax.p343v4.t) {
            return new ax.p070e3.q(str, jVar);
        }
        if (jVar instanceof ax.p343v4.a) {
            return new C1961e(str, jVar);
        }
        if ((jVar instanceof ax.p343v4.d) && ((ax.p343v4.d) jVar).a() == 507) {
            return new ax.p070e3.s(str, jVar);
        }
        if ((jVar instanceof C1332p) && (c1330o = (c1332p = (C1332p) jVar).Y) != null && c1330o.c() && c1332p.Y.b() != null && (c1966jC1 = c0(str, jVar, c1332p.Y.b())) != null) {
            return c1966jC1;
        }
        if ((jVar instanceof w0) && (v0Var = (w0Var = (w0) jVar).Y) != null && v0Var.d() && w0Var.Y.c() != null && w0Var.Y.c().a() != null && (c1966jM2 = m0(str, jVar, w0Var.Y.c().a())) != null) {
            return c1966jM2;
        }
        if ((jVar instanceof ax.M4.X) && (w = (x = (ax.M4.X) jVar).Y) != null) {
            if (w.n()) {
                return new ax.p070e3.s(str, jVar);
            }
            if (x.Y.l() && x.Y.j() != null) {
                C1966j c1966jC2 = c0(str, jVar, x.Y.j());
                if (c1966jC2 != null) {
                    return c1966jC2;
                }
            } else if (x.Y.m() && x.Y.k() != null && (c1966jM1 = m0(str, jVar, x.Y.k())) != null) {
                return c1966jM1;
            }
        }
        if ((jVar instanceof ax.M4.K) && (j = (k = (ax.M4.K) jVar).Y) != null && j.d() && k.Y.c() != null && (c1966jC0 = c0(str, jVar, k.Y.c())) != null) {
            return c1966jC0;
        }
        if ((jVar instanceof C1318i) && (c1316h = (c1318i = (C1318i) jVar).Y) != null) {
            if (c1316h.e() && c1318i.Y.c() != null) {
                C1966j c1966jC3 = c0(str, jVar, c1318i.Y.c());
                if (c1966jC3 != null) {
                    return c1966jC3;
                }
            } else if (c1318i.Y.f() && c1318i.Y.d() != null && (c1966jM0 = m0(str, jVar, c1318i.Y.d())) != null) {
                return c1966jM0;
            }
        }
        return new C1966j(str, jVar);
    }

    static ax.D4.a Y(String str) {
        return new ax.D4.a(d0(), str);
    }

    static ax.D4.a Z(ax.A4.a aVar) {
        return new ax.D4.a(d0(), aVar);
    }

    public static a a0(Context context) {
        if (p == null) {
            p = new a(context.getApplicationContext());
        }
        return p;
    }

    private static void b0(Context context) {
    }

    private C1966j c0(String str, ax.p343v4.j jVar, ax.M4.M m) {
        if (m.b()) {
            return new ax.p070e3.t(jVar);
        }
        if (m.c()) {
            return new C1961e(str, jVar);
        }
        return null;
    }

    private static ax.p343v4.m d0() {
        return ax.p343v4.m.e("FileManager/3.8.3").b(ax.p391y4.b.e).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0(boolean z) {
        InterfaceC6228c.a aVar = this.j;
        if (aVar != null) {
            aVar.d0(z, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean k0(Activity activity) {
        b0(activity);
        try {
            com.dropbox.core.android.a.b(activity, C2001g.c(activity).a(ax.U2.f.S0), d0());
            return true;
        } catch (IllegalStateException e) {
            ax.p302sb.c.h().d("Dropbox OAuth Error").m(e).i();
            return false;
        }
    }

    private void l0(ax.D4.a aVar, ax.p086f3.G g, long j, Long l, String str, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        int i = g.a() ? 2 : 1;
        Exception e = null;
        for (int i2 = 0; i2 < i; i2++) {
            e0 e0Var = new e0(g.b(), 0L, j, interfaceC2462c, iVar);
            try {
                try {
                    try {
                        try {
                            u0 u0VarE = aVar.a().v(str).e(Boolean.FALSE);
                            if (z) {
                                u0VarE.g(T0.d);
                            } else {
                                u0VarE.g(T0.c);
                            }
                            if (l != null && l.longValue() > 0) {
                                u0VarE.f(new Date(l.longValue()));
                            }
                            if (j >= 0) {
                                u0VarE.c(e0Var, j);
                            } else {
                                u0VarE.b(e0Var);
                            }
                            try {
                                e0Var.close();
                                return;
                            } catch (IOException unused) {
                                return;
                            }
                        } catch (ax.p343v4.t e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                try {
                                    e0Var.close();
                                } catch (IOException unused2) {
                                }
                            } catch (Throwable th) {
                                try {
                                    e0Var.close();
                                    throw th;
                                } catch (IOException unused3) {
                                    throw th;
                                }
                            }
                        } catch (ax.p343v4.x e3) {
                            e = e3;
                            e.printStackTrace();
                            e0Var.close();
                        }
                    } catch (w0 e4) {
                        throw X("upload", e4);
                    }
                } catch (ax.p343v4.j e5) {
                    throw X("dropbox uploadfile", e5);
                } catch (IOException e6) {
                    if (!(e6.getCause() instanceof C1957a)) {
                        throw C1960d.b("dropbox uploadfile", e6);
                    }
                    throw ((C1957a) e6.getCause());
                }
            } catch (ax.p343v4.t e7) {
                e = e7;
            } catch (ax.p343v4.x e8) {
                e = e8;
            }
        }
        throw C1960d.b("Maxed out upload attempts to Dropbox", e);
    }

    private C1966j m0(String str, ax.p343v4.j jVar, S0 s0) {
        if (s0.e()) {
            return new ax.p070e3.s(str, jVar);
        }
        if (s0.f()) {
            return new C1961e(str, jVar);
        }
        if (s0.d()) {
            return new ax.p070e3.o(str, jVar);
        }
        return null;
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
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        String strE = abstractC6239n.E();
        if (j < 4194304 || j == -1) {
            l0(this.h, g, j, l, strE, true, interfaceC2462c, iVar);
        } else {
            W(this.h, g, j, l, strE, true, interfaceC2462c, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.h != null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        if ("/".equals(str)) {
            return new C2007m(this, "/");
        }
        if (!a()) {
            ax.p302sb.c.h().g().b("NOT CONNECT CALL GET FILE INFO").k().i();
            throw new C1964h("Not connected to server");
        }
        try {
            return new C2007m(this, this.h.a().j(str));
        } catch (ax.p343v4.j e) {
            if (e instanceof ax.M4.B) {
                ax.M4.B b2 = (ax.M4.B) e;
                if (b2.Y.c() && b2.Y.b() == ax.M4.M.c) {
                    return new C2007m(this, str);
                }
            }
            throw X("getFileInfo", e);
        } catch (Exception e2) {
            e2.printStackTrace();
            throw C1960d.b("dropbox getfileinfo", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        ax.D4.a aVar = this.h;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.a().l(str2).d(EnumC1335q0.JPEG).e(EnumC1338s0.W128H128).c().d();
        } catch (ax.p343v4.j e) {
            e.printStackTrace();
            return null;
        }
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
        C2461b.c(abstractC6239n.isDirectory());
        if (!a()) {
            ax.p302sb.c.h().g().b("NOT CONNECT CALL LISTCHILDREN").k().i();
            throw new C1964h("Not connected to server");
        }
        C2007m c2007m = (C2007m) abstractC6239n;
        try {
            String strE = d0.E(c2007m) ? HttpUrl.FRAGMENT_ENCODE_SET : c2007m.E();
            ArrayList arrayList = new ArrayList();
            ax.M4.L lN = this.h.a().n(strE);
            while (true) {
                List<ax.M4.P> listB = lN.b();
                if (listB != null) {
                    for (ax.M4.P p2 : listB) {
                        if (p2.b() != null || p2.c() != null) {
                            arrayList.add(new C2007m(this, p2));
                        }
                    }
                }
                if (!lN.c()) {
                    return arrayList;
                }
                lN = this.h.a().p(lN.a());
            }
        } catch (ax.p343v4.j e) {
            throw X("listChildren", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    public void f0() {
        if (this.k) {
            this.l = true;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (q.contains(abstractC6239n.y())) {
            return ax.p086f3.B.Y(abstractC6239n);
        }
        return null;
    }

    public void g0() {
        if (this.k && this.l) {
            b bVar = this.i;
            if (bVar == null || bVar.m().equals(ax.z3.q.f.FINISHED)) {
                ax.A4.a aVarA = com.dropbox.core.android.a.a();
                if (aVarA != null) {
                    b bVar2 = new b(p(), this, this.j, aVarA);
                    this.i = bVar2;
                    bVar2.i(new Void[0]);
                } else {
                    this.j.d0(false, null);
                }
                this.k = false;
                this.l = false;
                this.j = null;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            this.h.a().f(abstractC6239n.E());
        } catch (ax.p343v4.j e) {
            e.printStackTrace();
            throw X("deleteFile", e);
        } catch (IllegalArgumentException e2) {
            ax.p302sb.c.h().g().b("Dropbox Delete").m(e2).h("path:" + abstractC6239n.E()).i();
            throw new C1966j(e2);
        }
    }

    void h0(ax.D4.a aVar) {
        if (!this.n || TextUtils.isEmpty(this.m)) {
            o.fine("Use dropbox user space");
            this.h = aVar;
        } else {
            o.fine("Use dropbox team space");
            this.h = aVar.d(ax.I4.a.c(this.m));
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        if (!a()) {
            throw new C1964h("Not connected : dropbox");
        }
        try {
            C1328n c1328nH = this.h.a().h(abstractC6239n.E());
            if (j > 0) {
                c1328nH.b(j);
            }
            return c1328nH.c().d();
        } catch (ax.p343v4.f e) {
            throw X("dropbox getinputstream : " + e.b() + " ; " + e.getMessage(), e);
        } catch (ax.p343v4.j e2) {
            throw X("dropbox getinputstream : " + e2.getMessage(), e2);
        } catch (IllegalArgumentException e3) {
            ax.p302sb.c.h().g().b("dropbox path problem").h("path:" + abstractC6239n.E()).i();
            throw new C1966j("dropbox getInputStream : " + e3.getMessage(), e3);
        }
    }

    void i0(String str) {
        this.m = str;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        try {
            long jP = abstractC6239n.p();
            ax.M4.Z zR = this.h.a().r(abstractC6239n.E(), abstractC6239n2.E());
            if (zR == null || zR.a() == null) {
                throw new C1966j("null result");
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (ax.p343v4.j e) {
            throw X("moveFile", e);
        } catch (IllegalArgumentException e2) {
            ax.U2.d.c("dropbox move", e2);
            throw new C1966j("moveFile", e2);
        }
    }

    void j0(boolean z) {
        this.n = z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) throws ax.p407z4.a {
        String strA;
        String string;
        if (this.k) {
            return;
        }
        ax.U2.f fVar = ax.U2.f.S0;
        c0 c0Var = new c0(fVar);
        SharedPreferences sharedPreferences = p().getSharedPreferences("DropboxPrefs", 0);
        int i = sharedPreferences.getInt("version_" + t(), 0);
        ax.A4.a aVarI = null;
        if (i == 0) {
            string = sharedPreferences.getString("access_token_" + t(), null);
            strA = sharedPreferences.getString("credential_" + t(), null);
        } else {
            String strA2 = c0Var.a(i, ax.p022b3.a.a().b(fVar), sharedPreferences.getString("access_token_" + t(), null));
            strA = c0Var.a(i, ax.p022b3.a.a().b(fVar), sharedPreferences.getString("credential_" + t(), null));
            string = strA2;
        }
        String string2 = sharedPreferences.getString("app_rootspaceid_" + t(), null);
        boolean z = sharedPreferences.getBoolean("app_userootspace_" + t(), false);
        i0(string2);
        j0(z);
        if (strA != null) {
            try {
                aVarI = ax.A4.a.f.i(strA);
            } catch (ax.p407z4.a unused) {
            }
        }
        this.j = aVar;
        aVar.J();
        if (string != null || aVarI != null) {
            new c(activity, string, aVarI).i(new Void[0]);
            return;
        }
        if (activity == null) {
            e0(false);
            return;
        }
        boolean zK0 = k0(activity);
        this.k = zK0;
        if (zK0) {
            return;
        }
        e0(false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        C2461b.a(abstractC6239n.o());
        String strE = abstractC6239n.E();
        if (j < 4194304 || j == -1) {
            l0(this.h, g, j, l, strE, false, interfaceC2462c, iVar);
        } else {
            W(this.h, g, j, l, strE, false, interfaceC2462c, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    synchronized void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        try {
            if (z2) {
                try {
                    String strE = "/".equals(abstractC6239n.E()) ? HttpUrl.FRAGMENT_ENCODE_SET : abstractC6239n.E();
                    C1319i0 c1319i0T = this.h.a().t(str);
                    c1319i0T.b(C1313f0.a().d(strE).c(1000L).b(Boolean.TRUE).a());
                    C1321j0 c1321j0A = c1319i0T.a();
                    ArrayList arrayList = new ArrayList();
                    if (c1321j0A != null && c1321j0A.a() != null) {
                        Iterator<C1311e0> it = c1321j0A.a().iterator();
                        while (it.hasNext()) {
                            ax.M4.Q qA = it.next().a();
                            if (qA != null && qA.c()) {
                                ax.M4.P pB = qA.b();
                                if (pB.b() != null || pB.c() != null) {
                                    arrayList.add(new C2007m(this, pB));
                                }
                            }
                        }
                    }
                    hVar.i0(ax.p086f3.B.h(arrayList, null, z, false), true);
                    return;
                } catch (ax.p343v4.j e) {
                    e.printStackTrace();
                    throw X("doSearch", e);
                }
            }
            try {
                o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
                return;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        throw th;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        try {
            long jP = abstractC6239n.p();
            ax.M4.Z zB = this.h.a().b(abstractC6239n.E(), abstractC6239n2.E());
            if (zB == null || zB.a() == null) {
                throw new C1966j("Dropbox copy returns null entry");
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (ax.p343v4.j e) {
            throw X("copyFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        try {
            C1310e c1310eD = this.h.a().d(abstractC6239n.E(), false);
            return (c1310eD == null || c1310eD.a() == null) ? false : true;
        } catch (ax.p343v4.j e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return l(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        g1(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        try {
            ax.S4.h hVarB = this.h.b().b();
            ax.S4.g gVarA = hVarB.a();
            if (gVarA.f()) {
                return new k0(gVarA.c().a(), hVarB.b());
            }
            if (gVarA.g()) {
                return new k0(gVarA.d().a(), gVarA.d().b());
            }
            return null;
        } catch (ax.p343v4.j e) {
            throw X("storagespace", e);
        }
    }
}
