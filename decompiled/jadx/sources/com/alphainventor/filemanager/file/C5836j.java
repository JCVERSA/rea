package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.J1.C0728i;
import ax.J1.C0740v;
import ax.J1.U;
import ax.J1.V;
import ax.J1.W;
import ax.J1.b0;
import ax.p214p3.B0;
import ax.p214p3.C5577a;
import ax.p214p3.C5585e;
import ax.p214p3.C5586e0;
import ax.p214p3.C5588f0;
import ax.p214p3.C5591h;
import ax.p214p3.C5593i;
import ax.p214p3.C5594i0;
import ax.p214p3.C5596j0;
import ax.p214p3.C5603n;
import ax.p214p3.C5605o;
import ax.p214p3.C5607p;
import ax.p214p3.E0;
import ax.p214p3.EnumC5610q0;
import ax.p214p3.EnumC5613s0;
import ax.p214p3.S0;
import ax.p214p3.T0;
import ax.p214p3.X;
import ax.p214p3.Z;
import ax.p214p3.u0;
import ax.p214p3.v0;
import ax.p214p3.w0;
import ax.p214p3.z0;
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

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5836j extends AbstractC5837k {
    static a p;
    private ax.p088g3.a h;
    private b i;
    private InterfaceC5828b.a j;
    private boolean k;
    private boolean l;
    private String m;
    private boolean n;
    private static final Logger o = Logger.getLogger("FileManager.DropboxFileHelper");
    static final HashSet<String> q = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "tiff", "tif", "gif", "webp", "ppm", "bmp", "cr2", "crw", "nef", "nrw", "sr2", "dng", "arw", "orf", "mp4", "m4v", "3gp", "3gpp", "3gpp2", "webm", "mkv", "wmv", "avi", "mpg", "mpeg", "mov", "asf", "ogv", "ts", "mts", "vob"));

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.j$a */
    public static class a extends N {
        Context a;
        boolean b;
        b c;
        WeakReference<ax.P1.j> d;

        public a(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("DropboxPrefs", 0).edit().remove("version_" + i).remove("app_userid_" + i).remove("app_accountid_" + i).remove("access_token_" + i).remove("credential_" + i).remove("app_name_" + i).remove("location_name_" + i).remove("app_email_" + i).remove("app_rootspaceid_" + i).remove("app_userootspace_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            String string = sharedPreferences.getString("app_name_" + i, null);
            String string2 = sharedPreferences.getString("app_email_" + i, null);
            String str = TextUtils.isEmpty(string2) ? string : string2;
            ax.p338y1.f fVar = ax.p338y1.f.P0;
            return new ax.G1.p(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), str, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(Activity activity, ax.P1.j jVar) {
            this.d = new WeakReference<>(jVar);
            ax.p338y1.f fVar = ax.p338y1.f.P0;
            jVar.b(fVar);
            boolean zE0 = C5836j.e0(activity);
            this.b = zE0;
            if (zE0) {
                return;
            }
            jVar.a(fVar, null, 0, null, null);
        }

        public void l(Activity activity, ax.P1.j jVar, int i, boolean z) {
            s(i, z);
            if (jVar != null) {
                jVar.d(ax.p338y1.f.P0, i);
            }
        }

        int m(String str, String str2) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (!sharedPreferences.contains("app_accountid_" + i2)) {
                    if (sharedPreferences.contains("app_name_" + i2) && !TextUtils.isEmpty(str2)) {
                        if (str2.equals(sharedPreferences.getString("app_name_" + i2, null))) {
                            return i2;
                        }
                    }
                } else if (TextUtils.isEmpty(str)) {
                    continue;
                } else {
                    if (str.equals(sharedPreferences.getString("app_accountid_" + i2, null))) {
                        return i2;
                    }
                }
            }
            return -1;
        }

        int n() {
            return this.a.getSharedPreferences("DropboxPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> o() {
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
                if (bVar == null || bVar.m().equals(ax.d2.n.g.FINISHED)) {
                    ax.P1.j jVar = this.d.get();
                    if (jVar != null) {
                        ax.p046d3.a aVarA = ax.Z2.a.a();
                        if (aVarA != null) {
                            b bVar2 = new b(activity, jVar, aVarA);
                            this.c = bVar2;
                            bVar2.i(new Void[0]);
                        } else {
                            jVar.a(ax.p338y1.f.P0, null, 0, null, null);
                        }
                    }
                    this.b = false;
                }
            }
        }

        void q(int i, ax.p046d3.a aVar) {
            ax.p338y1.f fVar = ax.p338y1.f.P0;
            U u = new U(fVar);
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("DropboxPrefs", 0).edit();
            editorEdit.putInt("version_" + i, 3);
            editorEdit.putString("credential_" + i, u.e(ax.F1.a.a().b(fVar), aVar.toString()));
            editorEdit.commit();
        }

        void r(int i, String str, String str2, String str3, String str4, String str5, ax.p046d3.a aVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("DropboxPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            ax.p338y1.f fVar = ax.p338y1.f.P0;
            U u = new U(fVar);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("app_accountid_" + i, str).putInt("version_" + i, 3).putString("app_name_" + i, str2).putString("access_token_" + i, u.e(ax.F1.a.a().b(fVar), str5)).putString("app_email_" + i, str3).putString("app_rootspaceid_" + i, str4).putString("location_name_" + i, fVar.L(this.a));
            if (aVar != null) {
                editorEdit.putString("credential_" + i, u.e(ax.F1.a.a().b(fVar), aVar.toString()));
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

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.j$c */
    private class c extends ax.p045d2.n<Void, Void, Integer> {
        Activity h;
        ax.p046d3.a i;
        String j;

        public c(Activity activity, String str, ax.p046d3.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.j = str;
            this.i = aVar;
            this.h = activity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Integer g(Void... voidArr) {
            ax.p088g3.a aVarS;
            try {
                ax.p046d3.a aVar = this.i;
                if (aVar != null) {
                    aVarS = C5836j.T(aVar);
                } else {
                    String str = this.j;
                    if (str == null) {
                        return -2;
                    }
                    aVarS = C5836j.S(str);
                }
                ax.p046d3.a aVar2 = this.i;
                if (aVar2 != null && aVar2.i() != null && this.i.a()) {
                    try {
                        aVarS.c();
                        C5836j.U(C5836j.this.n()).q(C5836j.this.q(), this.i);
                    } catch (ax.p046d3.c unused) {
                        return -2;
                    }
                }
                ax.p298v3.c cVarA = aVarS.b().a();
                SharedPreferences sharedPreferences = C5836j.this.n().getSharedPreferences("DropboxPrefs", 0);
                String string = sharedPreferences.getString("app_email_" + C5836j.this.q(), null);
                String string2 = sharedPreferences.getString("app_rootspaceid_" + C5836j.this.q(), null);
                String strB = cVarA.d() != null ? cVarA.d().b() : null;
                String strB2 = cVarA.b();
                if (!ax.p045d2.x.i(string, strB2) || !ax.p045d2.x.i(string2, strB)) {
                    C5836j.U(C5836j.this.n()).r(C5836j.this.q(), cVarA.a(), cVarA.c().a(), strB2, strB, this.j, this.i);
                }
                C5836j.this.c0(strB);
                C5836j.this.b0(aVarS);
                return 0;
            } catch (ax.Y2.r e) {
                e.printStackTrace();
                return -2;
            } catch (ax.Y2.j e2) {
                e2.printStackTrace();
                return -1;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            if (num.intValue() == 0) {
                C5836j.this.Y(true);
                return;
            }
            if (num.intValue() == -1) {
                C5836j.this.Y(false);
                return;
            }
            if (num.intValue() == -2) {
                Activity activity = this.h;
                if (activity == null) {
                    C5836j.this.Y(false);
                    return;
                }
                C5836j.this.k = C5836j.e0(activity);
                if (C5836j.this.k) {
                    return;
                }
                C5836j.this.Y(false);
            }
        }
    }

    private void Q(ax.p088g3.a aVar, ax.J1.A a2, long j, Long l, String str, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        long jA;
        Exception exc;
        long j2;
        long j3 = 4194304;
        if (j < 4194304) {
            ax.p045d2.b.g("File too small, use upload() instead.");
            return;
        }
        boolean zA = a2.a();
        int i = zA ? 5 : 1;
        Exception exc2 = null;
        String strA = null;
        long j4 = 0;
        int i2 = 0;
        while (i2 < i) {
            W w = (!zA || j4 <= 0) ? new W(a2.b(), 0L, j, cVar, iVar) : new W(a2.c(j4), j4, j, cVar, iVar);
            try {
                if (strA == null) {
                    try {
                        try {
                            try {
                                strA = aVar.a().A().h(w, j3).a();
                                j4 += j3;
                            } catch (E0 e) {
                                try {
                                    if (!e.Y.e() || !e.Y.d().d()) {
                                        throw new C0718i(e);
                                    }
                                    jA = e.Y.d().b().a();
                                    exc = e;
                                    try {
                                        w.close();
                                    } catch (IOException unused) {
                                    }
                                    j4 = jA;
                                    i2++;
                                    j3 = 4194304;
                                    exc2 = exc;
                                } catch (Throwable th) {
                                    th = th;
                                }
                            } catch (IOException e2) {
                                if (!(e2.getCause() instanceof C0710a)) {
                                    throw new C0718i(e2);
                                }
                                throw ((C0710a) e2.getCause());
                            }
                        } catch (z0 e3) {
                            if (!e3.Y.d()) {
                                throw new C0718i(e3);
                            }
                            jA = e3.Y.b().a();
                            exc = e3;
                            w.close();
                            j4 = jA;
                            i2++;
                            j3 = 4194304;
                            exc2 = exc;
                        } catch (ax.Y2.j e4) {
                            throw R("chunk_upload", e4);
                        }
                    } catch (ax.Y2.t | ax.Y2.x e5) {
                        j4 = j4;
                        exc = e5;
                        try {
                            w.close();
                        } catch (IOException unused2) {
                        }
                        i2++;
                        j3 = 4194304;
                        exc2 = exc;
                    }
                } else {
                    j4 = j4;
                }
                try {
                    B0 b0 = new B0(strA, j4);
                    while (true) {
                        j2 = j - j4;
                        if (j2 <= j3) {
                            break;
                        }
                        aVar.a().x(b0).h(w, j3);
                        j4 += j3;
                        b0 = new B0(strA, j4);
                        try {
                            w.close();
                        } catch (IOException unused3) {
                        }
                        throw th;
                    }
                    C5577a.C0425a c0425aB = C5577a.a(str).b(Boolean.FALSE);
                    if (z) {
                        c0425aB.d(T0.d);
                    } else {
                        c0425aB.d(T0.c);
                    }
                    if (l != null && l.longValue() > 0) {
                        c0425aB.c(new Date(l.longValue()));
                    }
                    aVar.a().y(b0, c0425aB.a()).h(w, j2);
                    try {
                        w.close();
                        return;
                    } catch (IOException unused4) {
                        return;
                    }
                } catch (ax.Y2.t e6) {
                    e = e6;
                    exc = e;
                    w.close();
                    i2++;
                    j3 = 4194304;
                    exc2 = exc;
                } catch (ax.Y2.x e7) {
                    e = e7;
                    exc = e;
                    w.close();
                    i2++;
                    j3 = 4194304;
                    exc2 = exc;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        throw C0712c.b("Maxed out upload attempts to Dropbox", exc2);
    }

    private C0718i R(String str, ax.Y2.j jVar) {
        C5593i c5593i;
        C5591h c5591h;
        C0718i c0718iG0;
        ax.p214p3.K k;
        ax.p214p3.J j;
        C0718i c0718iW;
        X x;
        ax.p214p3.W w;
        C0718i c0718iG1;
        w0 w0Var;
        v0 v0Var;
        C0718i c0718iG2;
        C5607p c5607p;
        C5605o c5605o;
        C0718i c0718iW2;
        if (jVar instanceof ax.Y2.t) {
            return new ax.I1.p(str, jVar);
        }
        if (jVar instanceof ax.Y2.a) {
            return new C0713d(str, jVar);
        }
        if ((jVar instanceof ax.Y2.d) && ((ax.Y2.d) jVar).a() == 507) {
            return new ax.I1.r(str, jVar);
        }
        if ((jVar instanceof C5607p) && (c5605o = (c5607p = (C5607p) jVar).Y) != null && c5605o.c() && c5607p.Y.b() != null && (c0718iW2 = W(str, jVar, c5607p.Y.b())) != null) {
            return c0718iW2;
        }
        if ((jVar instanceof w0) && (v0Var = (w0Var = (w0) jVar).Y) != null && v0Var.d() && w0Var.Y.c() != null && w0Var.Y.c().a() != null && (c0718iG2 = g0(str, jVar, w0Var.Y.c().a())) != null) {
            return c0718iG2;
        }
        if ((jVar instanceof X) && (w = (x = (X) jVar).Y) != null) {
            if (w.n()) {
                return new ax.I1.r(str, jVar);
            }
            if (x.Y.l() && x.Y.j() != null) {
                C0718i c0718iW3 = W(str, jVar, x.Y.j());
                if (c0718iW3 != null) {
                    return c0718iW3;
                }
            } else if (x.Y.m() && x.Y.k() != null && (c0718iG1 = g0(str, jVar, x.Y.k())) != null) {
                return c0718iG1;
            }
        }
        if ((jVar instanceof ax.p214p3.K) && (j = (k = (ax.p214p3.K) jVar).Y) != null && j.d() && k.Y.c() != null && (c0718iW = W(str, jVar, k.Y.c())) != null) {
            return c0718iW;
        }
        if ((jVar instanceof C5593i) && (c5591h = (c5593i = (C5593i) jVar).Y) != null) {
            if (c5591h.e() && c5593i.Y.c() != null) {
                C0718i c0718iW4 = W(str, jVar, c5593i.Y.c());
                if (c0718iW4 != null) {
                    return c0718iW4;
                }
            } else if (c5593i.Y.f() && c5593i.Y.d() != null && (c0718iG0 = g0(str, jVar, c5593i.Y.d())) != null) {
                return c0718iG0;
            }
        }
        return new C0718i(str, jVar);
    }

    static ax.p088g3.a S(String str) {
        return new ax.p088g3.a(X(), str);
    }

    static ax.p088g3.a T(ax.p046d3.a aVar) {
        return new ax.p088g3.a(X(), aVar);
    }

    public static a U(Context context) {
        if (p == null) {
            p = new a(context.getApplicationContext());
        }
        return p;
    }

    private static void V(Context context) {
    }

    private C0718i W(String str, ax.Y2.j jVar, ax.p214p3.M m) {
        if (m.b()) {
            return new ax.I1.s(jVar);
        }
        if (m.c()) {
            return new C0713d(str, jVar);
        }
        return null;
    }

    private static ax.Y2.m X() {
        return ax.Y2.m.e("FileManager/3.5.4").b(ax.p018b3.b.e).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(boolean z) {
        InterfaceC5828b.a aVar = this.j;
        if (aVar != null) {
            aVar.f0(z, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e0(Activity activity) {
        V(activity);
        try {
            ax.Z2.a.b(activity, "u1wulwl292c1qq6", X());
            return true;
        } catch (IllegalStateException e) {
            ax.p221pa.c.h().d("Dropbox OAuth Error").m(e).i();
            return false;
        }
    }

    private void f0(ax.p088g3.a aVar, ax.J1.A a2, long j, Long l, String str, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        int i = a2.a() ? 2 : 1;
        Exception e = null;
        for (int i2 = 0; i2 < i; i2++) {
            W w = new W(a2.b(), 0L, j, cVar, iVar);
            try {
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
                                    u0VarE.c(w, j);
                                } else {
                                    u0VarE.b(w);
                                }
                                try {
                                    w.close();
                                    return;
                                } catch (IOException unused) {
                                    return;
                                }
                            } catch (ax.Y2.t e2) {
                                e = e2;
                                try {
                                    e.printStackTrace();
                                    try {
                                        w.close();
                                    } catch (IOException unused2) {
                                    }
                                } catch (Throwable th) {
                                    try {
                                        w.close();
                                    } catch (IOException unused3) {
                                    }
                                    throw th;
                                }
                            } catch (ax.Y2.x e3) {
                                e = e3;
                                e.printStackTrace();
                                w.close();
                            }
                        } catch (w0 e4) {
                            throw R("upload", e4);
                        }
                    } catch (ax.Y2.j e5) {
                        throw R("dropbox uploadfile", e5);
                    }
                } catch (IOException e6) {
                    if (!(e6.getCause() instanceof C0710a)) {
                        throw C0712c.b("dropbox uploadfile", e6);
                    }
                    throw ((C0710a) e6.getCause());
                }
            } catch (ax.Y2.t e7) {
                e = e7;
            } catch (ax.Y2.x e8) {
                e = e8;
            }
        }
        throw C0712c.b("Maxed out upload attempts to Dropbox", e);
    }

    private C0718i g0(String str, ax.Y2.j jVar, S0 s0) {
        if (s0.e()) {
            return new ax.I1.r(str, jVar);
        }
        if (s0.f()) {
            return new C0713d(str, jVar);
        }
        if (s0.d()) {
            return new ax.I1.n(str, jVar);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean I() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean J() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public void L(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        String strC = abstractC5838l.C();
        if (j < 4194304 || j == -1) {
            f0(this.h, a2, j, l, strC, true, cVar, iVar);
        } else {
            Q(this.h, a2, j, l, strC, true, cVar, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        if ("/".equals(str)) {
            return new C0728i(this, "/");
        }
        if (!a()) {
            ax.p221pa.c.h().g().b("NOT CONNECT CALL GET FILE INFO").k().i();
            throw new C0716g("Not connected to server");
        }
        try {
            return new C0728i(this, this.h.a().j(str));
        } catch (ax.Y2.j e) {
            if (e instanceof ax.p214p3.B) {
                ax.p214p3.B b2 = (ax.p214p3.B) e;
                if (b2.Y.c() && b2.Y.b() == ax.p214p3.M.c) {
                    return new C0728i(this, str);
                }
            }
            throw R("getFileInfo", e);
        } catch (Exception e2) {
            e2.printStackTrace();
            throw C0712c.b("dropbox getfileinfo", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        ax.p088g3.a aVar = this.h;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.a().l(str2).d(EnumC5610q0.JPEG).e(EnumC5613s0.W128H128).c().d();
        } catch (ax.Y2.j e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    public void Z() {
        if (this.k) {
            this.l = true;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (q.contains(abstractC5838l.v())) {
            return C0740v.Q(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.h != null;
    }

    public void a0() {
        if (this.k && this.l) {
            b bVar = this.i;
            if (bVar == null || bVar.m().equals(ax.d2.n.g.FINISHED)) {
                ax.p046d3.a aVarA = ax.Z2.a.a();
                if (aVarA != null) {
                    b bVar2 = new b(n(), this, this.j, aVarA);
                    this.i = bVar2;
                    bVar2.i(new Void[0]);
                } else {
                    this.j.f0(false, null);
                }
                this.k = false;
                this.l = false;
                this.j = null;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        try {
            long jO = abstractC5838l.o();
            Z zB = this.h.a().b(abstractC5838l.C(), abstractC5838l2.C());
            if (zB == null || zB.a() == null) {
                throw new C0718i("Dropbox copy returns null entry");
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (ax.Y2.j e) {
            throw R("copyFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
    }

    void b0(ax.p088g3.a aVar) {
        if (!this.n || TextUtils.isEmpty(this.m)) {
            o.fine("Use dropbox user space");
            this.h = aVar;
        } else {
            o.fine("Use dropbox team space");
            this.h = aVar.d(ax.p158l3.a.c(this.m));
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            this.h.a().f(abstractC5838l.C());
        } catch (ax.Y2.j e) {
            e.printStackTrace();
            throw R("deleteFile", e);
        } catch (IllegalArgumentException e2) {
            ax.p221pa.c.h().g().b("Dropbox Delete").m(e2).h("path:" + abstractC5838l.C()).i();
            throw new C0718i(e2);
        }
    }

    void c0(String str) {
        this.m = str;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        ax.p045d2.b.a(abstractC5838l.n());
        String strC = abstractC5838l.C();
        if (j < 4194304 || j == -1) {
            f0(this.h, a2, j, l, strC, false, cVar, iVar);
        } else {
            Q(this.h, a2, j, l, strC, false, cVar, iVar);
        }
    }

    void d0(boolean z) {
        this.n = z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        if (!a()) {
            throw new C0716g("Not connected : dropbox");
        }
        try {
            C5603n c5603nH = this.h.a().h(abstractC5838l.C());
            if (j > 0) {
                c5603nH.b(j);
            }
            return c5603nH.c().d();
        } catch (ax.Y2.f e) {
            throw R("dropbox getinputstream : " + e.b() + " ; " + e.getMessage(), e);
        } catch (ax.Y2.j e2) {
            throw R("dropbox getinputstream : " + e2.getMessage(), e2);
        } catch (IllegalArgumentException e3) {
            ax.p221pa.c.h().g().b("dropbox path problem").h("path:" + abstractC5838l.C()).i();
            throw new C0718i("dropbox getInputStream : " + e3.getMessage(), e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) throws ax.p032c3.a {
        String strA;
        String string;
        if (this.k) {
            return;
        }
        ax.p338y1.f fVar = ax.p338y1.f.P0;
        U u = new U(fVar);
        SharedPreferences sharedPreferences = n().getSharedPreferences("DropboxPrefs", 0);
        int i = sharedPreferences.getInt("version_" + q(), 0);
        ax.p046d3.a aVarI = null;
        if (i == 0) {
            string = sharedPreferences.getString("access_token_" + q(), null);
            strA = sharedPreferences.getString("credential_" + q(), null);
        } else {
            String strA2 = u.a(i, ax.F1.a.a().b(fVar), sharedPreferences.getString("access_token_" + q(), null));
            strA = u.a(i, ax.F1.a.a().b(fVar), sharedPreferences.getString("credential_" + q(), null));
            string = strA2;
        }
        String string2 = sharedPreferences.getString("app_rootspaceid_" + q(), null);
        boolean z = sharedPreferences.getBoolean("app_userootspace_" + q(), false);
        c0(string2);
        d0(z);
        if (strA != null) {
            try {
                aVarI = ax.p046d3.a.f.i(strA);
            } catch (ax.p032c3.a unused) {
            }
        }
        this.j = aVar;
        aVar.N();
        if (string != null || aVarI != null) {
            new c(activity, string, aVarI).i(new Void[0]);
            return;
        }
        if (activity == null) {
            Y(false);
            return;
        }
        boolean zE0 = e0(activity);
        this.k = zE0;
        if (zE0) {
            return;
        }
        Y(false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        if (!a()) {
            ax.p221pa.c.h().g().b("NOT CONNECT CALL LISTCHILDREN").k().i();
            throw new C0716g("Not connected to server");
        }
        C0728i c0728i = (C0728i) abstractC5838l;
        try {
            String strC = V.B(c0728i) ? HttpUrl.FRAGMENT_ENCODE_SET : c0728i.C();
            ArrayList arrayList = new ArrayList();
            ax.p214p3.L lN = this.h.a().n(strC);
            while (true) {
                List<ax.p214p3.P> listB = lN.b();
                if (listB != null) {
                    for (ax.p214p3.P p2 : listB) {
                        if (p2.b() != null || p2.c() != null) {
                            arrayList.add(new C0728i(this, p2));
                        }
                    }
                }
                if (!lN.c()) {
                    return arrayList;
                }
                lN = this.h.a().p(lN.a());
            }
        } catch (ax.Y2.j e) {
            throw R("listChildren", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        try {
            C5585e c5585eD = this.h.a().d(abstractC5838l.C(), false);
            return (c5585eD == null || c5585eD.a() == null) ? false : true;
        } catch (ax.Y2.j e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        return j(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    synchronized void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        try {
            try {
                String strC = "/".equals(abstractC5838l.C()) ? HttpUrl.FRAGMENT_ENCODE_SET : abstractC5838l.C();
                C5594i0 c5594i0T = this.h.a().t(str);
                c5594i0T.b(C5588f0.a().d(strC).c(1000L).b(Boolean.TRUE).a());
                C5596j0 c5596j0A = c5594i0T.a();
                ArrayList arrayList = new ArrayList();
                if (c5596j0A != null && c5596j0A.a() != null) {
                    Iterator<C5586e0> it = c5596j0A.a().iterator();
                    while (it.hasNext()) {
                        ax.p214p3.Q qA = it.next().a();
                        if (qA != null && qA.c()) {
                            ax.p214p3.P pB = qA.b();
                            if (pB.b() != null || pB.c() != null) {
                                arrayList.add(new C0728i(this, pB));
                            }
                        }
                    }
                }
                hVar.l0(C0740v.e(arrayList, null, z, false), true);
            } catch (ax.Y2.j e) {
                e.printStackTrace();
                throw R("doSearch", e);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        try {
            long jO = abstractC5838l.o();
            Z zR = this.h.a().r(abstractC5838l.C(), abstractC5838l2.C());
            if (zR == null || zR.a() == null) {
                throw new C0718i("null result");
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (ax.Y2.j e) {
            throw R("moveFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        b1(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        try {
            ax.p298v3.h hVarB = this.h.b().b();
            ax.p298v3.g gVarA = hVarB.a();
            if (gVarA.f()) {
                return new b0(gVarA.c().a(), hVarB.b());
            }
            if (gVarA.g()) {
                return new b0(gVarA.d().a(), gVarA.d().b());
            }
            return null;
        } catch (ax.Y2.j e) {
            throw R("storagespace", e);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.j$b */
    private static class b extends ax.p045d2.n<Void, Void, Integer> {
        private InterfaceC5828b.a h;
        ax.P1.j i;
        ax.p046d3.a j;
        Context k;
        C5836j l;
        int m;

        public b(Context context, C5836j c5836j, InterfaceC5828b.a aVar, ax.p046d3.a aVar2) {
            super(ax.d2.n.f.CONNECT);
            this.k = context;
            this.h = aVar;
            this.j = aVar2;
            this.l = c5836j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:22:0x005d A[PHI: r2
          0x005d: PHI (r2v9 java.lang.String) = 
          (r2v0 java.lang.String)
          (r2v0 java.lang.String)
          (r2v11 java.lang.String)
          (r2v11 java.lang.String)
          (r2v11 java.lang.String)
         binds: [B:10:0x002c, B:12:0x0032, B:14:0x0048, B:16:0x004e, B:18:0x0054] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // ax.p045d2.n
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
            ax.p046d3.a aVar = this.j;
            if (aVar == null) {
                return -1;
            }
            ax.p088g3.a aVarT = C5836j.T(aVar);
            String strB2 = null;
            try {
                ax.p298v3.c cVarA = aVarT.b().a();
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
                        str3 = strA2;
                        str5 = strA;
                    } catch (ax.Y2.j unused) {
                        str = strB2;
                        str2 = strB;
                        strB = strA2;
                        str3 = strB;
                        str4 = str2;
                        strB2 = str;
                        str5 = strA;
                        z = false;
                    }
                } catch (ax.Y2.j unused2) {
                    strA = HttpUrl.FRAGMENT_ENCODE_SET;
                    str = null;
                    str2 = strA;
                }
            } catch (ax.Y2.j unused3) {
                strA = HttpUrl.FRAGMENT_ENCODE_SET;
                str = null;
                str2 = strA;
            }
            a aVarU = C5836j.U(this.k);
            C5836j c5836j = this.l;
            if (c5836j != null) {
                iN = c5836j.q();
            } else {
                iN = aVarU.n();
                int iM = aVarU.m(str5, str3);
                if (iM >= 0) {
                    iN = iM;
                }
            }
            aVarU.r(iN, str5, str3, str4, strB2, null, this.j);
            this.m = iN;
            if (this.i != null && z) {
                return -2;
            }
            C5836j c5836j2 = this.l;
            if (c5836j2 != null) {
                c5836j2.c0(strB2);
                this.l.b0(aVarT);
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            if (num.intValue() == 0) {
                InterfaceC5828b.a aVar = this.h;
                if (aVar != null) {
                    aVar.f0(true, null);
                }
                ax.P1.j jVar = this.i;
                if (jVar != null) {
                    jVar.d(ax.p338y1.f.P0, this.m);
                    return;
                }
                return;
            }
            if (num.intValue() != -2) {
                InterfaceC5828b.a aVar2 = this.h;
                if (aVar2 != null) {
                    aVar2.f0(false, null);
                }
                ax.P1.j jVar2 = this.i;
                if (jVar2 != null) {
                    jVar2.a(ax.p338y1.f.P0, null, 0, null, null);
                    return;
                }
                return;
            }
            try {
                this.i.c(ax.p338y1.f.P0, this.m, null);
            } catch (IllegalStateException unused) {
                InterfaceC5828b.a aVar3 = this.h;
                if (aVar3 != null) {
                    aVar3.f0(false, null);
                }
                ax.P1.j jVar3 = this.i;
                if (jVar3 != null) {
                    jVar3.a(ax.p338y1.f.P0, null, 0, null, null);
                }
            }
        }

        public b(Context context, ax.P1.j jVar, ax.p046d3.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.k = context;
            this.i = jVar;
            this.j = aVar;
        }
    }
}
