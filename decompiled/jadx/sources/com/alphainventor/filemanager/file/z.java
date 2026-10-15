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
import ax.I1.C0712c;
import ax.I1.C0718i;
import ax.J1.C0744z;
import ax.J1.U;
import ax.J1.b0;
import com.alphainventor.filemanager.R;
import com.socialnmobile.dav.gson.ServerType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class z extends Q {
    private static final Logger x = Logger.getLogger("FileManager.NextCloudFileHelper");
    static c y;
    private String w;

    class a implements ValueCallback<Boolean> {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ InterfaceC5828b.a e;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.z$a$a, reason: collision with other inner class name */
        class C0505a implements ax.S1.b.d {
            C0505a() {
            }

            @Override // ax.S1.b.d
            public void a() {
                a.this.e.f0(false, null);
            }

            @Override // ax.S1.b.d
            public void b(Bundle bundle) {
                CookieSyncManager.getInstance().sync();
                String string = bundle.getString("user");
                String string2 = bundle.getString("password");
                boolean z = bundle.getBoolean("invalid_cert");
                String str = (string == null || !string.contains("@")) ? string : HttpUrl.FRAGMENT_ENCODE_SET;
                c cVarC0 = z.C0(z.this.n());
                a aVar = a.this;
                cVarC0.n(aVar.c, aVar.b, string2, string, str, z);
                Fragment fragment = a.this.d;
                if (fragment instanceof ax.K1.J) {
                    ((ax.K1.J) fragment).b9();
                }
                a aVar2 = a.this;
                if (aVar2.e != null) {
                    z zVar = z.this;
                    Context contextN = z.this.n();
                    a aVar3 = a.this;
                    Activity activity = aVar3.a;
                    Fragment fragment2 = aVar3.d;
                    z zVar2 = z.this;
                    zVar.o = new b(contextN, activity, fragment2, zVar2, zVar2.q(), a.this.e);
                    z.this.o.i(new Object[0]);
                }
            }

            @Override // ax.S1.b.d
            public void c(ax.S1.c cVar) {
                z.x.severe("OAUTH ERROR 2:" + cVar.getMessage());
                a.this.e.f0(false, cVar);
            }
        }

        a(Activity activity, String str, int i, Fragment fragment, InterfaceC5828b.a aVar) {
            this.a = activity;
            this.b = str;
            this.c = i;
            this.d = fragment;
            this.e = aVar;
        }

        @Override // android.webkit.ValueCallback
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onReceiveValue(Boolean bool) {
            z.L0(this.a, this.b, new C0505a());
        }
    }

    private static class b extends ax.p045d2.n<Object, Void, Boolean> {
        Context h;
        InterfaceC5828b.a i;
        String j;
        String k;
        String l;
        z m;
        int n;
        ax.K1.J o;
        Activity p;
        String q;
        boolean r;
        boolean s;
        Exception t;

        public b(Context context, Activity activity, Fragment fragment, z zVar, int i, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.h = context;
            this.p = activity;
            this.m = zVar;
            this.i = aVar;
            this.n = i;
            this.o = (ax.K1.J) fragment;
            SharedPreferences sharedPreferences = context.getSharedPreferences("NextCloudPrefs", 0);
            ax.p338y1.f fVar = ax.p338y1.f.U0;
            U u = new U(fVar);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            this.j = sharedPreferences.getString("login_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            if (i2 == 0) {
                this.q = sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            } else {
                this.q = u.a(i2, ax.F1.a.a().b(fVar), sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            }
            this.r = sharedPreferences.getBoolean("ignorecert_" + i, false);
            this.l = sharedPreferences.getString("server_address_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            this.k = sharedPreferences.getString("user_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
        }

        @Override // ax.p045d2.n
        protected void r() {
            InterfaceC5828b.a aVar = this.i;
            if (aVar != null) {
                aVar.N();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            Q.l0(this.h);
            ax.p291ua.a aVarC = ax.p263sa.b.c(ServerType.TYPE_NEXTCLOUD, this.j, this.q, this.r, 15000L);
            try {
                String strE0 = z.E0(aVarC, this.l);
                if (TextUtils.isEmpty(this.k)) {
                    try {
                        String strM = new ax.p149k8.n().a(strE0).f().t("ocs").f().t("data").f().t("id").j().m();
                        if (strM != null) {
                            this.k = strM;
                            this.h.getSharedPreferences("NextCloudPrefs", 0).edit().putString("user_name_" + this.n, this.k).apply();
                        } else {
                            ax.p221pa.c.h().g().b("INVALID NEXT JSON FORMAT").i();
                            this.k = this.j;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.k = this.j;
                    }
                }
                String strJ0 = z.J0(this.l, this.k);
                if (this.m != null) {
                    this.m.t0(strJ0, Uri.parse(strJ0).getPath());
                    this.m.r0(aVarC);
                    this.m.K0(this.l);
                    this.m.s0(ServerType.TYPE_NEXTCLOUD);
                }
                return Boolean.TRUE;
            } catch (ax.p277ta.a e2) {
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
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            Activity activity;
            if (!this.s) {
                InterfaceC5828b.a aVar = this.i;
                if (aVar != null) {
                    aVar.f0(bool.booleanValue(), this.t);
                    return;
                }
                return;
            }
            z zVar = this.m;
            if (zVar == null || (activity = this.p) == null) {
                this.i.f0(false, this.t);
            } else {
                zVar.M0(activity, this.o, this.l, this.n, this.i);
            }
        }
    }

    public static class c extends N {
        Context a;

        class a implements ValueCallback<Boolean> {
            final /* synthetic */ com.alphainventor.filemanager.activity.a a;
            final /* synthetic */ String b;
            final /* synthetic */ ax.P1.j c;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.z$c$a$a, reason: collision with other inner class name */
            class C0506a implements ax.S1.b.d {
                C0506a() {
                }

                @Override // ax.S1.b.d
                public void a() {
                }

                @Override // ax.S1.b.d
                public void b(Bundle bundle) {
                    CookieSyncManager.getInstance().sync();
                    String string = bundle.getString("user");
                    String string2 = bundle.getString("password");
                    boolean z = bundle.getBoolean("invalid_cert");
                    String str = (string == null || !string.contains("@")) ? string : HttpUrl.FRAGMENT_ENCODE_SET;
                    int iL = c.this.l();
                    a aVar = a.this;
                    c.this.n(iL, aVar.b, string2, string, str, z);
                    ax.P1.j jVar = a.this.c;
                    if (jVar != null) {
                        jVar.d(ax.p338y1.f.U0, iL);
                    }
                }

                @Override // ax.S1.b.d
                public void c(ax.S1.c cVar) {
                    z.x.severe("NextCloud login error :" + cVar.getMessage());
                    ax.P1.j jVar = a.this.c;
                    if (jVar != null) {
                        jVar.a(ax.p338y1.f.U0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, cVar.getMessage());
                    }
                }
            }

            a(com.alphainventor.filemanager.activity.a aVar, String str, ax.P1.j jVar) {
                this.a = aVar;
                this.b = str;
                this.c = jVar;
            }

            @Override // android.webkit.ValueCallback
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onReceiveValue(Boolean bool) {
                z.L0(this.a, this.b, new C0506a());
            }
        }

        public c(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("NextCloudPrefs", 0).edit().remove("version_" + i).remove("server_address_" + i).remove("login_name_" + i).remove("user_name_" + i).remove("access_token_" + i).remove("ignorecert_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("NextCloudPrefs", 0);
            String string = sharedPreferences.getString("login_name_" + i, null);
            ax.p338y1.f fVar = ax.p338y1.f.U0;
            return new ax.G1.p(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("NextCloudPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("NextCloudPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, String str, ax.P1.j jVar) {
            jVar.b(ax.p338y1.f.U0);
            CookieSyncManager.createInstance(aVar);
            ax.p045d2.x.Z(new a(aVar, str, jVar));
        }

        int l() {
            return this.a.getSharedPreferences("NextCloudPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> m() {
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
            ax.p338y1.f fVar = ax.p338y1.f.U0;
            U u = new U(fVar);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("access_token_" + i, u.e(ax.F1.a.a().b(fVar), str2)).putBoolean("ignorecert_" + i, z).putString("server_address_" + i, str).putString("login_name_" + i, str3).putString("user_name_" + i, str4).putString("location_name_" + i, fVar.L(this.a));
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
            ax.p045d2.x.d0(aVar.w(), ax.H1.Q.y3(ax.p338y1.f.U0), "serveraddress", true);
        }
    }

    public static String A0(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf("/remote.php")) >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    private static String B0(String str) {
        return str + "/index.php/login/flow";
    }

    public static c C0(Context context) {
        if (y == null) {
            y = new c(context.getApplicationContext());
        }
        return y;
    }

    public static b0 D0(ax.p263sa.a aVar, String str) throws C0718i {
        try {
            ax.p149k8.l lVarF = new ax.p149k8.n().a(E0(aVar, str)).f().t("ocs").f().t("data").f().t("quota").f();
            return new b0(lVarF.t("total").j().u(), lVarF.t("used").j().u());
        } catch (Exception e) {
            e.printStackTrace();
            throw new C0718i(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String E0(ax.p263sa.a aVar, String str) throws ax.p277ta.a, IOException {
        HashMap map = new HashMap();
        map.put("OCS-APIRequest", "true");
        return new String(C0744z.j(aVar.X(I0(str), map), 2048));
    }

    private static String F0(ax.p263sa.a aVar, String str) throws ax.p277ta.a, IOException {
        HashMap map = new HashMap();
        map.put("OCS-APIRequest", "true");
        return new String(C0744z.j(aVar.X(G0(str), map), 2048));
    }

    private static String G0(String str) {
        return str + "/status.php";
    }

    public static String H0(String str, int i, String str2) {
        if (str == null) {
            return null;
        }
        return str + "/apps/files/api/v1/thumbnail/" + i + "/" + i + Uri.encode(str2, "/");
    }

    private static String I0(String str) {
        return str + "/ocs/v1.php/cloud/user?format=json";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String J0(String str, String str2) {
        return str + "/remote.php/dav/files/" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K0(String str) {
        this.w = str;
    }

    static void L0(Activity activity, String str, ax.S1.b.d dVar) {
        String strB0 = B0(str);
        try {
            Q.l0(activity);
            ax.S1.a.t(activity, strB0, "nc://login/server", dVar).show();
        } catch (AndroidRuntimeException unused) {
            Toast.makeText(activity, R.string.error, 1).show();
        } catch (NullPointerException e) {
            ax.p221pa.c.h().d("WEBVIEW CREATE").m(e).i();
            Toast.makeText(activity, R.string.error, 1).show();
        }
    }

    public static ax.H1.Q.d z0(Context context, String str, boolean z) {
        Q.l0(context);
        try {
            ax.p149k8.l lVarF = ax.p149k8.n.d(F0(ax.p263sa.b.a(z, 15000L), str)).f();
            ax.p149k8.o oVarV = lVarF.v("installed");
            ax.p149k8.o oVarV2 = lVarF.v("version");
            ax.p149k8.o oVarV3 = lVarF.v("versionstring");
            if (oVarV != null) {
                if ((oVarV2 != null) & (oVarV3 != null)) {
                    return ax.H1.Q.d.SUCCESS;
                }
            }
            return ax.H1.Q.d.ERROR;
        } catch (Exception e) {
            return C0712c.b("test", e) instanceof ax.I1.p ? ax.H1.Q.d.NETWORK_ERROR : ax.H1.Q.d.ERROR;
        } catch (Throwable th) {
            throw th;
        }
    }

    void M0(Activity activity, Fragment fragment, String str, int i, InterfaceC5828b.a aVar) {
        CookieSyncManager.createInstance(activity);
        ax.p045d2.x.Z(new a(activity, str, i, fragment, aVar));
    }

    @Override // com.alphainventor.filemanager.file.Q
    protected ax.p045d2.n T(Activity activity, Fragment fragment, Q q, int i, InterfaceC5828b.a aVar) {
        return new b(n(), activity, fragment, this, q(), aVar);
    }

    @Override // com.alphainventor.filemanager.file.Q
    protected String c0() {
        return this.w;
    }
}
