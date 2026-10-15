package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1960d;
import ax.p070e3.C1966j;
import ax.p086f3.c0;
import ax.p086f3.k0;
import com.alphainventor.filemanager.R;
import com.socialnmobile.dav.gson.ServerType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class D extends W {
    private static final Logger x = Logger.getLogger("FileManager.NextCloudFileHelper");
    static c y;
    private String w;

    class a implements ValueCallback<Boolean> {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ InterfaceC6228c.a e;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.D$a$a, reason: collision with other inner class name */
        class C0530a implements ax.o3.b.d {
            C0530a() {
            }

            @Override // ax.o3.b.d
            public void a() {
                a.this.e.d0(false, null);
            }

            @Override // ax.o3.b.d
            public void b(Bundle bundle) {
                CookieSyncManager.getInstance().sync();
                String string = bundle.getString("user");
                String string2 = bundle.getString("password");
                boolean z = bundle.getBoolean("invalid_cert");
                String str = (string == null || !string.contains("@")) ? string : HttpUrl.FRAGMENT_ENCODE_SET;
                c cVarI0 = D.I0(D.this.p());
                a aVar = a.this;
                cVarI0.n(aVar.c, aVar.b, string2, string, str, z);
                Fragment fragment = a.this.d;
                if (fragment instanceof ax.p102g3.W) {
                    ((ax.p102g3.W) fragment).Ja();
                }
                a aVar2 = a.this;
                if (aVar2.e != null) {
                    D d = D.this;
                    Context contextP = D.this.p();
                    a aVar3 = a.this;
                    Activity activity = aVar3.a;
                    Fragment fragment2 = aVar3.d;
                    D d2 = D.this;
                    d.o = new b(contextP, activity, fragment2, d2, d2.t(), a.this.e);
                    D.this.o.i(new Object[0]);
                }
            }

            @Override // ax.o3.b.d
            public void c(ax.p230o3.c cVar) {
                D.x.severe("OAUTH ERROR 2:" + cVar.getMessage());
                a.this.e.d0(false, cVar);
            }
        }

        a(Activity activity, String str, int i, Fragment fragment, InterfaceC6228c.a aVar) {
            this.a = activity;
            this.b = str;
            this.c = i;
            this.d = fragment;
            this.e = aVar;
        }

        @Override // android.webkit.ValueCallback
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onReceiveValue(Boolean bool) {
            D.R0(this.a, this.b, new C0530a());
        }
    }

    private static class b extends ax.p406z3.q<Object, Void, Boolean> {
        Context h;
        InterfaceC6228c.a i;
        String j;
        String k;
        String l;
        D m;
        int n;
        ax.p102g3.W o;
        Activity p;
        String q;
        boolean r;
        boolean s;
        Exception t;

        public b(Context context, Activity activity, Fragment fragment, D d, int i, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.h = context;
            this.p = activity;
            this.m = d;
            this.i = aVar;
            this.n = i;
            this.o = (ax.p102g3.W) fragment;
            SharedPreferences sharedPreferences = context.getSharedPreferences("NextCloudPrefs", 0);
            ax.U2.f fVar = ax.U2.f.Y0;
            c0 c0Var = new c0(fVar);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            this.j = sharedPreferences.getString("login_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            if (i2 == 0) {
                this.q = sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            } else {
                this.q = c0Var.a(i2, ax.p022b3.a.a().b(fVar), sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            }
            this.r = sharedPreferences.getBoolean("ignorecert_" + i, false);
            this.l = sharedPreferences.getString("server_address_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            this.k = sharedPreferences.getString("user_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
        }

        @Override // ax.p406z3.q
        protected void r() {
            InterfaceC6228c.a aVar = this.i;
            if (aVar != null) {
                aVar.J();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            W.r0(this.h);
            ax.p382xb.a aVarB = ax.p350vb.b.b(ServerType.TYPE_NEXTCLOUD, this.j, this.q, this.r, 15000L);
            try {
                String strK0 = D.K0(aVarB, this.l);
                if (TextUtils.isEmpty(this.k)) {
                    try {
                        String strJ = new ax.Y8.n().a(strK0).g().t("ocs").g().t("data").g().t("id").i().j();
                        if (strJ != null) {
                            this.k = strJ;
                            this.h.getSharedPreferences("NextCloudPrefs", 0).edit().putString("user_name_" + this.n, this.k).apply();
                        } else {
                            ax.p302sb.c.h().g().b("INVALID NEXT JSON FORMAT").i();
                            this.k = this.j;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.k = this.j;
                    }
                }
                String strP0 = D.P0(this.l, this.k);
                if (this.m != null) {
                    this.m.z0(strP0, Uri.parse(strP0).getPath());
                    this.m.x0(aVarB);
                    this.m.Q0(this.l);
                    this.m.y0(ServerType.TYPE_NEXTCLOUD);
                }
                return Boolean.TRUE;
            } catch (ax.p366wb.a e2) {
                this.t = e2;
                if (e2.b() == 401) {
                    this.s = true;
                }
                e2.printStackTrace();
                return Boolean.FALSE;
            } catch (IOException e3) {
                this.t = e3;
                e3.printStackTrace();
                return Boolean.FALSE;
            } catch (NullPointerException e4) {
                this.t = e4;
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            Activity activity;
            if (!this.s) {
                InterfaceC6228c.a aVar = this.i;
                if (aVar != null) {
                    aVar.d0(bool.booleanValue(), this.t);
                    return;
                }
                return;
            }
            D d = this.m;
            if (d == null || (activity = this.p) == null) {
                this.i.d0(false, this.t);
            } else {
                d.S0(activity, this.o, this.l, this.n, this.i);
            }
        }
    }

    public static class c extends T {
        Context a;

        class a implements ValueCallback<Boolean> {
            final /* synthetic */ com.alphainventor.filemanager.activity.a a;
            final /* synthetic */ String b;
            final /* synthetic */ ax.p182l3.k c;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.D$c$a$a, reason: collision with other inner class name */
            class C0531a implements ax.o3.b.d {
                C0531a() {
                }

                @Override // ax.o3.b.d
                public void a() {
                }

                @Override // ax.o3.b.d
                public void b(Bundle bundle) {
                    CookieSyncManager.getInstance().sync();
                    String string = bundle.getString("user");
                    String string2 = bundle.getString("password");
                    boolean z = bundle.getBoolean("invalid_cert");
                    String str = (string == null || !string.contains("@")) ? string : HttpUrl.FRAGMENT_ENCODE_SET;
                    int iL = c.this.l();
                    a aVar = a.this;
                    c.this.n(iL, aVar.b, string2, string, str, z);
                    ax.p182l3.k kVar = a.this.c;
                    if (kVar != null) {
                        kVar.d(ax.U2.f.Y0, iL);
                    }
                }

                @Override // ax.o3.b.d
                public void c(ax.p230o3.c cVar) {
                    D.x.severe("NextCloud login error :" + cVar.getMessage());
                    ax.p182l3.k kVar = a.this.c;
                    if (kVar != null) {
                        kVar.c(ax.U2.f.Y0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, cVar.getMessage());
                    }
                }
            }

            a(com.alphainventor.filemanager.activity.a aVar, String str, ax.p182l3.k kVar) {
                this.a = aVar;
                this.b = str;
                this.c = kVar;
            }

            @Override // android.webkit.ValueCallback
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onReceiveValue(Boolean bool) {
                D.R0(this.a, this.b, new C0531a());
            }
        }

        public c(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("NextCloudPrefs", 0).edit().remove("version_" + i).remove("server_address_" + i).remove("login_name_" + i).remove("user_name_" + i).remove("access_token_" + i).remove("ignorecert_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("NextCloudPrefs", 0);
            String string = sharedPreferences.getString("login_name_" + i, null);
            ax.U2.f fVar = ax.U2.f.Y0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("NextCloudPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("NextCloudPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, String str, ax.p182l3.k kVar) {
            kVar.a(ax.U2.f.Y0);
            CookieSyncManager.createInstance(aVar);
            ax.p406z3.B.X(new a(aVar, str, kVar));
        }

        int l() {
            return this.a.getSharedPreferences("NextCloudPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> m() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("NextCloudPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("access_token_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void n(int i, String str, String str2, String str3, String str4, boolean z) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("NextCloudPrefs", 0);
            boolean z2 = i >= sharedPreferences.getInt("count", 0);
            ax.U2.f fVar = ax.U2.f.Y0;
            c0 c0Var = new c0(fVar);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("access_token_" + i, c0Var.e(ax.p022b3.a.a().b(fVar), str2)).putBoolean("ignorecert_" + i, z).putString("server_address_" + i, str).putString("login_name_" + i, str3).putString("user_name_" + i, str4).putString("location_name_" + i, fVar.L(this.a));
            if (z2) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z2) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }

        public void o(com.alphainventor.filemanager.activity.a aVar) {
            ax.p406z3.B.d0(aVar.getSupportFragmentManager(), ax.p054d3.W.v3(ax.U2.f.Y0), "serveraddress", true);
        }
    }

    public static ax.d3.W.d F0(Context context, String str, boolean z) {
        W.r0(context);
        try {
            ax.Y8.l lVarG = ax.Y8.n.d(L0(ax.p350vb.b.a(z, 15000L), str)).g();
            ax.Y8.o oVarV = lVarG.v("installed");
            ax.Y8.o oVarV2 = lVarG.v("version");
            ax.Y8.o oVarV3 = lVarG.v("versionstring");
            if (oVarV != null) {
                if ((oVarV2 != null) & (oVarV3 != null)) {
                    return ax.d3.W.d.SUCCESS;
                }
            }
            return ax.d3.W.d.ERROR;
        } catch (Exception e) {
            if (C1960d.b("test", e) instanceof ax.p070e3.q) {
                return ax.d3.W.d.NETWORK_ERROR;
            }
            if ((e instanceof ax.p366wb.a) && ((ax.p366wb.a) e).b() == 403) {
                return ax.d3.W.d.ERROR_ACCESS_DENIED;
            }
            return ax.d3.W.d.ERROR;
        }
    }

    public static String G0(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf("/remote.php")) >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    private static String H0(String str) {
        return str + "/index.php/login/flow";
    }

    public static c I0(Context context) {
        if (y == null) {
            y = new c(context.getApplicationContext());
        }
        return y;
    }

    public static k0 J0(ax.p350vb.a aVar, String str) throws C1966j {
        try {
            ax.Y8.l lVarG = new ax.Y8.n().a(K0(aVar, str)).g().t("ocs").g().t("data").g().t("quota").g();
            return new k0(lVarG.t("total").i().t(), lVarG.t("used").i().t());
        } catch (Exception e) {
            e.printStackTrace();
            throw new C1966j(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String K0(ax.p350vb.a aVar, String str) throws ax.p366wb.a, IOException {
        HashMap map = new HashMap();
        map.put("OCS-APIRequest", "true");
        return new String(ax.p086f3.F.h(aVar.h(O0(str), map), 2048));
    }

    private static String L0(ax.p350vb.a aVar, String str) throws ax.p366wb.a, IOException {
        HashMap map = new HashMap();
        map.put("OCS-APIRequest", "true");
        return new String(ax.p086f3.F.h(aVar.h(M0(str), map), 2048));
    }

    private static String M0(String str) {
        return str + "/status.php";
    }

    public static String N0(String str, int i, String str2) {
        if (str == null) {
            return null;
        }
        return str + "/apps/files/api/v1/thumbnail/" + i + "/" + i + Uri.encode(str2, "/");
    }

    private static String O0(String str) {
        return str + "/ocs/v1.php/cloud/user?format=json";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String P0(String str, String str2) {
        return str + "/remote.php/dav/files/" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(String str) {
        this.w = str;
    }

    static void R0(Activity activity, String str, ax.o3.b.d dVar) {
        String strH0 = H0(str);
        try {
            W.r0(activity);
            ax.p230o3.a.F(activity, strH0, "nc://login/server", dVar).show();
        } catch (AndroidRuntimeException unused) {
            Toast.makeText(activity, R.string.error, 1).show();
        } catch (NullPointerException e) {
            ax.p302sb.c.h().d("WEBVIEW CREATE").m(e).i();
            Toast.makeText(activity, R.string.error, 1).show();
        }
    }

    void S0(Activity activity, Fragment fragment, String str, int i, InterfaceC6228c.a aVar) {
        CookieSyncManager.createInstance(activity);
        ax.p406z3.B.X(new a(activity, str, i, fragment, aVar));
    }

    @Override // com.alphainventor.filemanager.file.W
    protected ax.p406z3.q Z(Activity activity, Fragment fragment, W w, int i, InterfaceC6228c.a aVar) {
        return new b(p(), activity, fragment, this, t(), aVar);
    }

    @Override // com.alphainventor.filemanager.file.W
    protected String i0() {
        return this.w;
    }
}
