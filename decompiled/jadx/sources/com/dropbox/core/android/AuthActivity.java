package com.dropbox.core.android;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.UiModeManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import ax.p111gc.t;
import ax.p127hc.C2087i;
import ax.p127hc.C2093o;
import ax.p335uc.g;
import ax.p335uc.l;
import ax.p343v4.h;
import ax.p343v4.k;
import ax.p343v4.m;
import ax.p343v4.n;
import ax.p343v4.q;
import ax.p343v4.z;
import ax.p375x4.d;
import ax.p375x4.e;
import ax.p375x4.f;
import com.dropbox.core.android.AuthActivity;
import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class AuthActivity extends Activity {
    public static final b Y = new b(null);
    private static final String Z = AuthActivity.class.getName();
    private static c k0 = new a();
    private static final Object l0 = new Object();
    public static Intent m0;
    private static ax.p375x4.a n0;
    private Boolean X;
    private boolean q;

    public static final class a implements c {
        a() {
        }

        @Override // com.dropbox.core.android.AuthActivity.c
        public SecureRandom a() {
            return new SecureRandom();
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void d(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final c e() {
            c cVar;
            synchronized (AuthActivity.l0) {
                cVar = AuthActivity.k0;
            }
            return cVar;
        }

        public final boolean c(Context context, String str, boolean z) {
            l.f(context, "context");
            l.f(str, "appKey");
            Intent intent = new Intent("android.intent.action.VIEW");
            String str2 = "db-" + str;
            intent.setData(Uri.parse(str2 + "://1/connect"));
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            l.e(listQueryIntentActivities, "queryIntentActivities(...)");
            if (listQueryIntentActivities.size() == 0) {
                throw new IllegalStateException(("URI scheme in your app's manifest is not set up correctly. You should have a " + AuthActivity.class.getName() + " with the scheme: " + str2).toString());
            }
            if (listQueryIntentActivities.size() <= 1) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(0);
                if ((resolveInfo != null ? resolveInfo.activityInfo : null) != null && l.a(context.getPackageName(), resolveInfo.activityInfo.packageName)) {
                    return true;
                }
                throw new IllegalStateException(("There must be a " + AuthActivity.class.getName() + " within your app's package registered for your URI scheme (" + str2 + "). However, it appears that an activity in a different package is registered for that scheme instead. If you have multiple apps that all want to use the same accesstoken pair, designate one of them to do authentication and have the other apps launch it and then retrieve the token pair from it.").toString());
            }
            if (z) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Security alert");
                builder.setMessage("Another app on your phone may be trying to pose as the app you are currently using. The malicious app can't access your account, but linking to Dropbox has been disabled as a precaution. Please contact support@dropbox.com.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: ax.w4.b
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        AuthActivity.b.d(dialogInterface, i);
                    }
                });
                builder.show();
            } else {
                Log.w(AuthActivity.Z, "There are multiple apps registered for the AuthActivity URI scheme (" + str2 + ").  Another app may be trying to  impersonate this app, so authentication will be disabled.");
            }
            return false;
        }

        public final Intent f(Context context, String str, String str2, String[] strArr, String str3, String str4, String str5, z zVar, m mVar, k kVar, String str6, q qVar) {
            if (str == null) {
                throw new IllegalArgumentException("'appKey' can't be null");
            }
            g(str, str2, strArr, str3, str4, str5, zVar, mVar, kVar, str6, qVar);
            return new Intent(context, (Class<?>) AuthActivity.class);
        }

        public final void g(String str, String str2, String[] strArr, String str3, String str4, String str5, z zVar, m mVar, k kVar, String str6, q qVar) {
            List listJ;
            k kVar2;
            if (strArr == null || (listJ = C2087i.E(strArr)) == null) {
                listJ = C2093o.j();
            }
            List list = listJ;
            if (kVar != null) {
                kVar2 = kVar;
            } else if (str4 != null) {
                k kVar3 = k.e;
                kVar2 = new k(kVar3.h(), kVar3.i(), str4, kVar3.j());
            } else {
                kVar2 = k.e;
            }
            AuthActivity.n0 = new ax.p375x4.a(str, str5, str2, list, str3, zVar, mVar, kVar2, str6, qVar);
        }
    }

    public interface c {
        SecureRandom a();
    }

    private final void f(Intent intent) {
        m0 = intent;
        ax.p375x4.b.a.a();
        finish();
    }

    private final ax.p375x4.b.C0504b g() {
        return ax.p375x4.b.a.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void i(AuthActivity authActivity, Intent intent, String str) {
        Log.d(Z, "running startActivity in handler");
        try {
            com.dropbox.core.android.b.a aVar = com.dropbox.core.android.b.a;
            Context applicationContext = authActivity.getApplicationContext();
            l.e(applicationContext, "getApplicationContext(...)");
            if (aVar.a(applicationContext, intent) != null) {
                authActivity.startActivity(intent);
            } else {
                authActivity.j(str);
            }
            authActivity.g().m(str);
        } catch (ActivityNotFoundException e) {
            Log.e(Z, "Could not launch intent. User may have restricted profile", e);
            authActivity.finish();
        }
    }

    private final void j(String str) {
        Locale locale = Locale.getDefault();
        Locale locale2 = new Locale(locale.getLanguage(), locale.getCountry());
        List listN = C2093o.n("k", g().c(), "n", !g().a().isEmpty() ? g().a().get(0) : "0", "api", g().b(), "state", str);
        if (g().l() != null) {
            listN.add("extra_query_params");
            e eVar = e.a;
            z zVarL = g().l();
            String strJ = g().j();
            q qVarG = g().g();
            String strC = g().h().c();
            l.e(strC, "getCodeChallenge(...)");
            listN.add(eVar.a(zVarL, strJ, qVarG, strC));
        }
        String string = locale2.toString();
        k kVarF = g().f();
        l.c(kVarF);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(n.g(string, kVarF.k(), "1/connect", (String[]) listN.toArray(new String[0]))));
        if (h(this)) {
            intent.setComponent(new ComponentName(getPackageName(), "com.alphainventor.filemanager.activity.WebViewActivity"));
        }
        startActivity(intent);
    }

    public final boolean h(Context context) {
        l.f(context, "context");
        if (this.X == null) {
            Object systemService = context.getSystemService("uimode");
            UiModeManager uiModeManager = systemService instanceof UiModeManager ? (UiModeManager) systemService : null;
            if (uiModeManager == null || uiModeManager.getCurrentModeType() != 4) {
                this.X = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.software.leanback"));
            } else {
                this.X = Boolean.TRUE;
            }
        }
        Boolean bool = this.X;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.x4.b.a aVar = ax.p375x4.b.a;
        if (!aVar.c()) {
            aVar.d(ax.p375x4.b.C0504b.n.a(n0));
        }
        setTheme(R.style.Theme.Translucent.NoTitleBar);
        super.onCreate(bundle);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0079  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:44:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:45:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:47:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:50:0x0114 A[Catch: Exception -> 0x0168, TryCatch #3 {Exception -> 0x0168, blocks: (B:48:0x0106, B:50:0x0114, B:51:0x0117), top: B:63:0x0106 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0117 A[Catch: Exception -> 0x0168, TRY_LEAVE, TryCatch #3 {Exception -> 0x0168, blocks: (B:48:0x0106, B:50:0x0114, B:51:0x0117), top: B:63:0x0106 }] */
    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        String queryParameter;
        String queryParameter2;
        String str;
        String str2;
        String queryParameter3;
        String queryParameter4;
        h hVar;
        l.f(intent, "intent");
        Intent intent2 = null;
        if (g().d() == null) {
            f(null);
            return;
        }
        if (!intent.hasExtra("ACCESS_TOKEN")) {
            Uri data = intent.getData();
            if (data == null || !l.a("/connect", data.getPath())) {
                queryParameter = null;
                queryParameter2 = null;
                str = null;
                str2 = null;
            } else {
                try {
                    queryParameter2 = data.getQueryParameter("oauth_token");
                    try {
                        queryParameter3 = data.getQueryParameter("oauth_token_secret");
                        try {
                            queryParameter4 = data.getQueryParameter("uid");
                            try {
                                queryParameter = data.getQueryParameter("state");
                            } catch (UnsupportedOperationException unused) {
                                String str3 = queryParameter4;
                                str2 = queryParameter3;
                                str = str3;
                                queryParameter = null;
                            }
                        } catch (UnsupportedOperationException unused2) {
                            queryParameter4 = null;
                        }
                    } catch (UnsupportedOperationException unused3) {
                        queryParameter3 = null;
                        queryParameter4 = queryParameter3;
                        String str4 = queryParameter4;
                        str2 = queryParameter3;
                        str = str4;
                        queryParameter = null;
                        if (queryParameter2 != null) {
                            if (!l.a(g().d(), queryParameter)) {
                                f(null);
                                return;
                            }
                            if (l.a(queryParameter2, ax.p375x4.g.OAUTH2.toString())) {
                                intent2 = new Intent();
                                intent2.putExtra("ACCESS_TOKEN", queryParameter2);
                                intent2.putExtra("ACCESS_SECRET", str2);
                                l.c(intent2.putExtra("UID", str));
                            } else if (l.a(queryParameter2, ax.p375x4.g.OAUTH2CODE.toString())) {
                                ax.p343v4.l lVarH = g().h();
                                m mVarI = g().i();
                                l.c(mVarI);
                                String strC = g().c();
                                l.c(strC);
                                k kVarF = g().f();
                                l.c(kVarF);
                                try {
                                    hVar = new f(str2, lVarH, mVarI, strC, kVarF).execute(new Void[0]).get();
                                    if (hVar == null) {
                                        t tVar = t.a;
                                    } else {
                                        Intent intent3 = new Intent();
                                        intent3.putExtra("ACCESS_TOKEN", hVar.a());
                                        intent3.putExtra("ACCESS_SECRET", hVar.a());
                                        intent3.putExtra("REFRESH_TOKEN", hVar.c());
                                        Long lB = hVar.b();
                                        l.e(lB, "getExpiresAt(...)");
                                        intent3.putExtra("EXPIRES_AT", lB.longValue());
                                        intent3.putExtra("UID", hVar.e());
                                        intent3.putExtra("CONSUMER_KEY", g().c());
                                        l.c(intent3.putExtra("SCOPE", hVar.d()));
                                        intent2 = intent3;
                                    }
                                } catch (Exception unused4) {
                                    t tVar2 = t.a;
                                }
                            }
                        }
                        f(intent2);
                    }
                } catch (UnsupportedOperationException unused5) {
                    queryParameter2 = null;
                    queryParameter3 = null;
                }
            }
            if (queryParameter2 != null && !l.a(queryParameter2, HttpUrl.FRAGMENT_ENCODE_SET) && str2 != null && !l.a(str2, HttpUrl.FRAGMENT_ENCODE_SET) && str != null && !l.a(str, HttpUrl.FRAGMENT_ENCODE_SET) && queryParameter != null && !l.a(queryParameter, HttpUrl.FRAGMENT_ENCODE_SET)) {
                if (!l.a(g().d(), queryParameter)) {
                    f(null);
                    return;
                }
                if (l.a(queryParameter2, ax.p375x4.g.OAUTH2.toString())) {
                    intent2 = new Intent();
                    intent2.putExtra("ACCESS_TOKEN", queryParameter2);
                    intent2.putExtra("ACCESS_SECRET", str2);
                    l.c(intent2.putExtra("UID", str));
                } else if (l.a(queryParameter2, ax.p375x4.g.OAUTH2CODE.toString())) {
                    ax.p343v4.l lVarH2 = g().h();
                    m mVarI2 = g().i();
                    l.c(mVarI2);
                    String strC2 = g().c();
                    l.c(strC2);
                    k kVarF2 = g().f();
                    l.c(kVarF2);
                    hVar = new f(str2, lVarH2, mVarI2, strC2, kVarF2).execute(new Void[0]).get();
                    if (hVar == null) {
                        t tVar3 = t.a;
                    } else {
                        Intent intent4 = new Intent();
                        intent4.putExtra("ACCESS_TOKEN", hVar.a());
                        intent4.putExtra("ACCESS_SECRET", hVar.a());
                        intent4.putExtra("REFRESH_TOKEN", hVar.c());
                        Long lB2 = hVar.b();
                        l.e(lB2, "getExpiresAt(...)");
                        intent4.putExtra("EXPIRES_AT", lB2.longValue());
                        intent4.putExtra("UID", hVar.e());
                        intent4.putExtra("CONSUMER_KEY", g().c());
                        l.c(intent4.putExtra("SCOPE", hVar.d()));
                        intent2 = intent4;
                    }
                }
            }
            f(intent2);
        }
        queryParameter2 = intent.getStringExtra("ACCESS_TOKEN");
        queryParameter3 = intent.getStringExtra("ACCESS_SECRET");
        queryParameter4 = intent.getStringExtra("UID");
        queryParameter = intent.getStringExtra("AUTH_STATE");
        String str5 = queryParameter4;
        str2 = queryParameter3;
        str = str5;
        if (queryParameter2 != null) {
            if (!l.a(g().d(), queryParameter)) {
                f(null);
                return;
            }
            if (l.a(queryParameter2, ax.p375x4.g.OAUTH2.toString())) {
                intent2 = new Intent();
                intent2.putExtra("ACCESS_TOKEN", queryParameter2);
                intent2.putExtra("ACCESS_SECRET", str2);
                l.c(intent2.putExtra("UID", str));
            } else if (l.a(queryParameter2, ax.p375x4.g.OAUTH2CODE.toString())) {
                ax.p343v4.l lVarH3 = g().h();
                m mVarI3 = g().i();
                l.c(mVarI3);
                String strC3 = g().c();
                l.c(strC3);
                k kVarF3 = g().f();
                l.c(kVarF3);
                hVar = new f(str2, lVarH3, mVarI3, strC3, kVarF3).execute(new Void[0]).get();
                if (hVar == null) {
                    t tVar4 = t.a;
                } else {
                    Intent intent5 = new Intent();
                    intent5.putExtra("ACCESS_TOKEN", hVar.a());
                    intent5.putExtra("ACCESS_SECRET", hVar.a());
                    intent5.putExtra("REFRESH_TOKEN", hVar.c());
                    Long lB3 = hVar.b();
                    l.e(lB3, "getExpiresAt(...)");
                    intent5.putExtra("EXPIRES_AT", lB3.longValue());
                    intent5.putExtra("UID", hVar.e());
                    intent5.putExtra("CONSUMER_KEY", g().c());
                    l.c(intent5.putExtra("SCOPE", hVar.d()));
                    intent2 = intent5;
                }
            }
        }
        f(intent2);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT < 29) {
            onTopResumedActivityChanged(true);
        }
    }

    @Override // android.app.Activity
    public void onTopResumedActivityChanged(boolean z) {
        final String strB;
        if (isFinishing() || !z) {
            return;
        }
        if (g().d() != null || g().c() == null) {
            f(null);
            return;
        }
        m0 = null;
        if (this.q) {
            Log.w(Z, "onResume called again before Handler run");
            return;
        }
        if (g().l() != null) {
            String strC = g().h().c();
            l.e(strC, "getCodeChallenge(...)");
            strB = ax.p375x4.c.a(strC, String.valueOf(g().l()), g().j(), g().g());
        } else {
            strB = ax.p375x4.c.b(Y.e());
        }
        final Intent intentB = d.a.b(g(), strB, this);
        runOnUiThread(new Runnable() { // from class: ax.w4.a
            @Override // java.lang.Runnable
            public final void run() {
                AuthActivity.i(this.q, intentB, strB);
            }
        });
        this.q = true;
    }
}
