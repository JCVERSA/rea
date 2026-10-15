package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ax.I1.C0718i;
import ax.J1.C0740v;
import ax.J1.U;
import ax.J1.b0;
import ax.K1.m0;
import com.alphainventor.filemanager.R;
import com.socialnmobile.dav.gson.ServerType;
import com.socialnmobile.dav.gson.YandexDisk;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class T extends Q {
    private static final Logger y = Logger.getLogger("FileManager.YandexFileHelper");
    static c z;
    private ax.p263sa.a w;
    private String x;

    class a implements ValueCallback<Boolean> {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ InterfaceC5828b.a e;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.T$a$a, reason: collision with other inner class name */
        class C0500a implements ax.S1.b.d {
            C0500a() {
            }

            @Override // ax.S1.b.d
            public void a() {
                a.this.e.f0(false, null);
            }

            @Override // ax.S1.b.d
            public void b(Bundle bundle) {
                CookieSyncManager.getInstance().sync();
                String string = bundle.getString("access_token");
                if (!T.x0(string, bundle.getString("expires_in"))) {
                    T.y.severe("OAUTH : FAILED TO RECEIVE ACCESS TOKEN 2");
                    a.this.e.f0(false, null);
                    return;
                }
                T.w0(T.this.n()).n(a.this.c, string, HttpUrl.FRAGMENT_ENCODE_SET);
                T t = T.this;
                Context contextN = T.this.n();
                a aVar = a.this;
                Activity activity = aVar.a;
                Fragment fragment = aVar.d;
                T t2 = T.this;
                t.o = new b(contextN, activity, fragment, t2, t2.q(), a.this.e);
                T.this.o.i(new Object[0]);
            }

            @Override // ax.S1.b.d
            public void c(ax.S1.c cVar) {
                T.y.severe("OAUTH ERROR 2:" + cVar.getMessage());
                a.this.e.f0(false, null);
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
            T.z0(this.a, this.b, new C0500a());
        }
    }

    private static class b extends ax.p045d2.n<Object, Void, Boolean> {
        Context h;
        InterfaceC5828b.a i;
        String j;
        String k;
        T l;
        boolean m;
        int n;
        m0 o;
        boolean p;
        Activity q;

        public b(Context context, Activity activity, Fragment fragment, T t, int i, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.h = context;
            this.q = activity;
            this.l = t;
            this.i = aVar;
            this.n = i;
            this.o = (m0) fragment;
            SharedPreferences sharedPreferences = context.getSharedPreferences("YandexDiskPrefs", 0);
            ax.p338y1.f fVar = ax.p338y1.f.S0;
            U u = new U(fVar);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            if (i2 == 0) {
                this.k = sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            } else {
                this.k = u.a(i2, ax.F1.a.a().b(fVar), sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            }
            this.j = sharedPreferences.getString("account_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
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
            ax.p291ua.a aVarB = ax.p263sa.b.b(ServerType.TYPE_YANDEX, this.k, "OAuth", 15000L);
            String str = "ru".equalsIgnoreCase(Locale.getDefault().getLanguage()) ? "https://webdav.yandex.ru:443" : "https://webdav.yandex.com:443";
            try {
                if (TextUtils.isEmpty(this.j)) {
                    String strP = aVarB.P();
                    this.j = strP;
                    if (strP != null) {
                        this.h.getSharedPreferences("YandexDiskPrefs", 0).edit().putString("account_name_" + this.n, this.j).commit();
                        this.m = true;
                    }
                }
                aVarB.V(str + "/", 0);
                T t = this.l;
                if (t != null) {
                    t.y0(this.k);
                    this.l.t0(str, null);
                    this.l.r0(aVarB);
                    this.l.s0(ServerType.TYPE_YANDEX);
                }
                return Boolean.TRUE;
            } catch (ax.p277ta.a e) {
                if (e.b() == 401) {
                    this.p = true;
                }
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            Activity activity;
            m0 m0Var;
            if (this.m && (m0Var = this.o) != null) {
                m0Var.b9();
            }
            if (!this.p) {
                InterfaceC5828b.a aVar = this.i;
                if (aVar != null) {
                    aVar.f0(bool.booleanValue(), null);
                    return;
                }
                return;
            }
            T t = this.l;
            if (t == null || (activity = this.q) == null) {
                this.i.f0(false, null);
            } else {
                t.A0(activity, this.o, this.j, this.n, this.i);
            }
        }
    }

    public static class c extends N {
        Context a;

        class a implements ValueCallback<Boolean> {
            final /* synthetic */ Activity a;
            final /* synthetic */ ax.P1.j b;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.T$c$a$a, reason: collision with other inner class name */
            class C0501a implements ax.S1.b.d {
                C0501a() {
                }

                @Override // ax.S1.b.d
                public void a() {
                }

                @Override // ax.S1.b.d
                public void b(Bundle bundle) {
                    CookieSyncManager.getInstance().sync();
                    String string = bundle.getString("access_token");
                    if (!T.x0(string, bundle.getString("expires_in"))) {
                        T.y.severe("OAUTH : FAILED TO RECEIVE ACCESS TOKEN");
                        ax.P1.j jVar = a.this.b;
                        if (jVar != null) {
                            jVar.a(ax.p338y1.f.S0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                            return;
                        }
                        return;
                    }
                    int iL = c.this.l();
                    c.this.n(iL, string, HttpUrl.FRAGMENT_ENCODE_SET);
                    ax.P1.j jVar2 = a.this.b;
                    if (jVar2 != null) {
                        jVar2.d(ax.p338y1.f.S0, iL);
                    }
                }

                @Override // ax.S1.b.d
                public void c(ax.S1.c cVar) {
                    T.y.severe("OAUTH ERROR :" + cVar.getMessage());
                    ax.P1.j jVar = a.this.b;
                    if (jVar != null) {
                        jVar.a(ax.p338y1.f.S0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                    }
                }
            }

            a(Activity activity, ax.P1.j jVar) {
                this.a = activity;
                this.b = jVar;
            }

            @Override // android.webkit.ValueCallback
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onReceiveValue(Boolean bool) {
                T.z0(this.a, null, new C0501a());
            }
        }

        public c(Context context) {
            this.a = context;
        }

        private void o(Activity activity, ax.P1.j jVar) {
            CookieSyncManager.createInstance(activity);
            ax.p045d2.x.Z(new a(activity, jVar));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("YandexDiskPrefs", 0).edit().remove("version_" + i).remove("access_token_" + i).remove("account_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("YandexDiskPrefs", 0);
            String string = sharedPreferences.getString("account_name_" + i, null);
            ax.p338y1.f fVar = ax.p338y1.f.S0;
            return new ax.G1.p(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("YandexDiskPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("YandexDiskPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, ax.P1.j jVar) {
            jVar.b(ax.p338y1.f.S0);
            o(aVar, jVar);
        }

        int l() {
            return this.a.getSharedPreferences("YandexDiskPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> m() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("YandexDiskPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("access_token_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void n(int i, String str, String str2) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("YandexDiskPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            ax.p338y1.f fVar = ax.p338y1.f.S0;
            U u = new U(fVar);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("access_token_" + i, u.e(ax.F1.a.a().b(fVar), str)).putString("account_name_" + i, str2).putString("location_name_" + i, fVar.L(this.a));
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }
    }

    public static c w0(Context context) {
        if (z == null) {
            z = new c(context.getApplicationContext());
        }
        return z;
    }

    static boolean x0(String str, String str2) {
        long jCurrentTimeMillis = (str2 == null || str2.equals("0")) ? 0L : System.currentTimeMillis() + (((long) Integer.parseInt(str2)) * 1000);
        return str != null && (jCurrentTimeMillis == 0 || System.currentTimeMillis() < jCurrentTimeMillis);
    }

    static void z0(Activity activity, String str, ax.S1.b.d dVar) {
        String str2 = "ru".equalsIgnoreCase(Locale.getDefault().getLanguage()) ? "https://oauth.yandex.ru/authorize?response_type=token&client_id=a335b8c433574f239913141bf225338a" : "https://oauth.yandex.com/authorize?response_type=token&client_id=a335b8c433574f239913141bf225338a";
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + "&login_hint=" + str;
        }
        try {
            ax.S1.b.q(activity, str2, null, null, null, "filemanager-plus-yandex-disk://yandex.com/redirect", dVar).show();
        } catch (AndroidRuntimeException unused) {
            Toast.makeText(activity, R.string.error, 1).show();
        } catch (NullPointerException e) {
            ax.p221pa.c.h().d("WEBVIEW CREATE").m(e).i();
            Toast.makeText(activity, R.string.error, 1).show();
        }
    }

    void A0(Activity activity, Fragment fragment, String str, int i, InterfaceC5828b.a aVar) {
        ax.p045d2.x.Z(new a(activity, str, i, fragment, aVar));
    }

    @Override // com.alphainventor.filemanager.file.Q, com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.Q
    protected ax.p045d2.n T(Activity activity, Fragment fragment, Q q, int i, InterfaceC5828b.a aVar) {
        return new b(n(), activity, fragment, this, q(), aVar);
    }

    @Override // com.alphainventor.filemanager.file.Q, com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        try {
            return a0(j0(str2, false) + "?preview&size=S", 0L);
        } catch (C0718i unused) {
            return null;
        }
    }

    @Override // com.alphainventor.filemanager.file.Q, com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (((S) abstractC5838l).f0()) {
            return C0740v.Q(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.Q
    protected ax.p263sa.a h0(long j) {
        if (j < 10485760) {
            if (this.w == null) {
                this.w = ax.p263sa.b.b(ServerType.TYPE_YANDEX, this.x, "OAuth", 120000L);
            }
            return this.w;
        }
        return ax.p263sa.b.b(ServerType.TYPE_YANDEX, this.x, "OAuth", ((j * 12000) / 1048576) + 30000);
    }

    @Override // com.alphainventor.filemanager.file.Q, com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        try {
            YandexDisk yandexDiskT = W().T();
            return new b0(yandexDiskT.getTotal(), yandexDiskT.getUsed());
        } catch (ax.p277ta.a e) {
            throw new C0718i(e);
        }
    }

    public void y0(String str) {
        this.x = str;
    }
}
