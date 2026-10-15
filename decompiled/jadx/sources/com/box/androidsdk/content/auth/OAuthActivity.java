package com.box.androidsdk.content.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Toast;
import ax.K3.g;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxError;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.utils.SdkUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class OAuthActivity extends Activity implements com.box.androidsdk.content.auth.a.b, OAuthWebView.c.f, OAuthWebView.d {
    private static Dialog t0;
    private String X;
    private String Y;
    private String Z;
    private String k0;
    private boolean l0;
    protected OAuthWebView m0;
    protected OAuthWebView.c n0;
    private String q;
    private BoxSession q0;
    private boolean o0 = false;
    private int p0 = 0;
    private AtomicBoolean r0 = new AtomicBoolean(false);
    private BroadcastReceiver s0 = new a();

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") && SdkUtils.m(context) && OAuthActivity.this.u()) {
                OAuthActivity.this.y();
            }
        }
    }

    class b implements DialogInterface.OnClickListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            OAuthActivity.this.finish();
        }
    }

    class c extends Thread {
        final /* synthetic */ String q;

        c(String str) {
            this.q = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo = BoxAuthentication.o().h(OAuthActivity.this.q0, this.q).get();
                String stringExtra = OAuthActivity.this.getIntent().getStringExtra("restrictToUserId");
                if (!SdkUtils.l(stringExtra) && !boxAuthenticationInfo.P().J().equals(stringExtra)) {
                    throw new RuntimeException("Unexpected user logged in. Expected " + stringExtra + " received " + boxAuthenticationInfo.P().J());
                }
                OAuthActivity.this.p(boxAuthenticationInfo);
            } catch (Exception e) {
                e.printStackTrace();
                OAuthActivity.this.o(e);
            }
        }
    }

    class d implements Runnable {
        final /* synthetic */ BoxAuthentication.BoxAuthenticationInfo q;

        d(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            this.q = boxAuthenticationInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            OAuthActivity.this.n();
            Intent intent = new Intent();
            intent.putExtra("authinfo", this.q);
            OAuthActivity.this.setResult(-1, intent);
            OAuthActivity.this.o0 = true;
            OAuthActivity.this.finish();
        }
    }

    class e implements Runnable {
        final /* synthetic */ OAuthWebView.b q;

        e(OAuthWebView.b bVar) {
            this.q = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            OAuthActivity.this.n();
            OAuthActivity.this.c(this.q);
            OAuthActivity.this.setResult(0);
        }
    }

    private void i() {
        OAuthWebView oAuthWebView = this.m0;
        if (oAuthWebView != null) {
            oAuthWebView.clearCache(true);
            this.m0.clearFormData();
            this.m0.clearHistory();
        }
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();
        deleteDatabase("webview.db");
        deleteDatabase("webviewCache.db");
        File cacheDir = getCacheDir();
        SdkUtils.g(cacheDir);
        cacheDir.mkdir();
    }

    public static Intent j(Context context, BoxSession boxSession, boolean z) {
        Intent intentK = k(context, boxSession.u(), boxSession.v(), boxSession.z(), z);
        intentK.putExtra("session", boxSession);
        if (!SdkUtils.l(boxSession.J())) {
            intentK.putExtra("restrictToUserId", boxSession.J());
        }
        return intentK;
    }

    public static Intent k(Context context, String str, String str2, String str3, boolean z) {
        Intent intent = new Intent(context, (Class<?>) OAuthActivity.class);
        intent.putExtra("client_id", str);
        intent.putExtra("client_secret", str2);
        if (!SdkUtils.l(str3)) {
            intent.putExtra("redirect_uri", str3);
        }
        intent.putExtra("loginviaboxapp", z);
        return intent;
    }

    private OAuthWebView.b q(Exception exc) {
        Throwable cause;
        BoxException boxException;
        BoxError boxErrorB;
        String str;
        String string = getString(ax.O3.d.b);
        if (exc != null) {
            if (exc instanceof ExecutionException) {
                cause = exc;
                cause = ((ExecutionException) exc).getCause();
            }
            cause = exc;
            if ((cause instanceof BoxException) && (boxErrorB = (boxException = (BoxException) cause).b()) != null) {
                if (boxException.e() == 403 || boxException.e() == 401 || (boxErrorB.E() != null && boxErrorB.E().equals("unauthorized_device"))) {
                    str = string + ":" + ((Object) getResources().getText(ax.O3.d.c)) + "\n";
                } else {
                    str = string + ":";
                }
                return new OAuthWebView.b(3, str + boxErrorB.H());
            }
            string = string + ":" + cause;
        }
        return new OAuthWebView.b(-1, string);
    }

    @Override // com.box.androidsdk.content.auth.OAuthWebView.c.f
    public void a(String str, String str2) {
        if (this.p0 == 0) {
            this.m0.setVisibility(4);
        }
        x(str, str2);
    }

    @Override // com.box.androidsdk.content.auth.a.b
    public void b(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        if (boxAuthenticationInfo != null) {
            BoxAuthentication.o().u(boxAuthenticationInfo, this);
            p(boxAuthenticationInfo);
        }
    }

    @Override // com.box.androidsdk.content.auth.OAuthWebView.c.f
    public boolean c(OAuthWebView.b bVar) {
        if (bVar.a == 2) {
            if (bVar.c.b() == -6 || bVar.c.b() == -2 || bVar.c.b() == -8) {
                return false;
            }
            Resources resources = getResources();
            Toast.makeText(this, String.format("%s\n%s: %s", resources.getString(ax.O3.d.b), resources.getString(ax.O3.d.n), bVar.c.b() + " " + bVar.c.a()), 1).show();
        } else if (SdkUtils.l(bVar.b)) {
            Toast.makeText(this, ax.O3.d.b, 1).show();
        } else {
            int i = bVar.a;
            if (i == 1) {
                Resources resources2 = getResources();
                Toast.makeText(this, String.format("%s\n%s: %s", resources2.getString(ax.O3.d.b), resources2.getString(ax.O3.d.n), resources2.getString(ax.O3.d.d)), 1).show();
            } else {
                if (i == 3) {
                    new AlertDialog.Builder(this).setTitle(ax.O3.d.b).setMessage(ax.O3.d.c).setPositiveButton(ax.O3.d.m, new b()).create().show();
                    return true;
                }
                Toast.makeText(this, ax.O3.d.b, 1).show();
            }
        }
        finish();
        return true;
    }

    @Override // com.box.androidsdk.content.auth.OAuthWebView.d
    public void d(WebView webView, String str) {
        n();
    }

    @Override // com.box.androidsdk.content.auth.OAuthWebView.c.f
    public void e(String str) {
        a(str, null);
    }

    @Override // com.box.androidsdk.content.auth.a.b
    public void f() {
        if (getFragmentManager().findFragmentByTag("choose_auth") != null) {
            getFragmentManager().popBackStack();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        i();
        if (!this.o0) {
            BoxAuthentication.o().v(null, null);
        }
        super.finish();
    }

    protected OAuthWebView l() {
        OAuthWebView oAuthWebView = (OAuthWebView) findViewById(t());
        oAuthWebView.setVisibility(0);
        oAuthWebView.getSettings().setJavaScriptEnabled(true);
        oAuthWebView.getSettings().setDomStorageEnabled(true);
        oAuthWebView.getSettings().setSaveFormData(false);
        oAuthWebView.getSettings().setSavePassword(false);
        return oAuthWebView;
    }

    protected OAuthWebView.c m() {
        return new OAuthWebView.c(this, this.k0);
    }

    protected synchronized void n() {
        Dialog dialog = t0;
        if (dialog != null && dialog.isShowing()) {
            try {
                t0.dismiss();
            } catch (IllegalArgumentException unused) {
            }
            t0 = null;
        } else if (t0 != null) {
            t0 = null;
        }
    }

    protected void o(Exception exc) {
        runOnUiThread(new e(q(exc)));
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (-1 != i2 || 1 != i) {
            if (i2 == 0) {
                finish();
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("userId");
        String stringExtra2 = intent.getStringExtra("authcode");
        if (!SdkUtils.k(stringExtra2) || SdkUtils.k(stringExtra)) {
            if (SdkUtils.k(stringExtra2)) {
                return;
            }
            x(stringExtra2, null);
        } else {
            BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo = BoxAuthentication.o().r(this).get(stringExtra);
            if (boxAuthenticationInfo != null) {
                b(boxAuthenticationInfo);
            } else {
                c(new OAuthWebView.b(0, HttpUrl.FRAGMENT_ENCODE_SET));
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (getFragmentManager().findFragmentByTag("choose_auth") != null) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (g.k) {
            getWindow().addFlags(8192);
        }
        setContentView(s());
        registerReceiver(this.s0, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.q = intent.getStringExtra("client_id");
        this.X = intent.getStringExtra("client_secret");
        this.Y = intent.getStringExtra("box_device_id");
        this.Z = intent.getStringExtra("box_device_name");
        this.k0 = intent.getStringExtra("redirect_uri");
        this.p0 = intent.getBooleanExtra("loginviaboxapp", false) ? 1 : 0;
        this.r0.getAndSet(false);
        this.q0 = (BoxSession) intent.getSerializableExtra("session");
        if (bundle != null) {
            this.l0 = bundle.getBoolean("loggingInViaBoxApp");
        }
        BoxSession boxSession = this.q0;
        if (boxSession != null) {
            boxSession.Q(getApplicationContext());
            return;
        }
        BoxSession boxSession2 = new BoxSession(this, null, this.q, this.X, this.k0);
        this.q0 = boxSession2;
        boxSession2.T(this.Y);
        this.q0.U(this.Z);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        unregisterReceiver(this.s0);
        this.r0.set(false);
        n();
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (u()) {
            y();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("loggingInViaBoxApp", this.l0);
        super.onSaveInstanceState(bundle);
    }

    protected void p(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        runOnUiThread(new d(boxAuthenticationInfo));
    }

    protected Intent r() {
        Intent intent = new Intent("com.box.android.action.AUTHENTICATE_VIA_BOX_APP");
        List<ResolveInfo> listQueryIntentActivities = getPackageManager().queryIntentActivities(intent, 65600);
        if (listQueryIntentActivities != null && listQueryIntentActivities.size() >= 1) {
            String string = getResources().getString(ax.O3.d.l);
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                try {
                    if (string.equals(getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, 64).signatures[0].toCharsString())) {
                        intent.setPackage(resolveInfo.activityInfo.packageName);
                        Map<String, BoxAuthentication.BoxAuthenticationInfo> mapR = BoxAuthentication.o().r(this);
                        if (mapR != null && mapR.size() > 0) {
                            ArrayList<String> arrayList = new ArrayList<>(mapR.size());
                            for (Map.Entry<String, BoxAuthentication.BoxAuthenticationInfo> entry : mapR.entrySet()) {
                                if (entry.getValue().P() != null) {
                                    arrayList.add(entry.getValue().P().z());
                                }
                            }
                            if (arrayList.size() > 0) {
                                intent.putStringArrayListExtra("boxusers", arrayList);
                            }
                        }
                        return intent;
                    }
                    continue;
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }

    protected int s() {
        return ax.O3.c.b;
    }

    protected int t() {
        return ax.O3.b.m;
    }

    boolean u() {
        if (this.l0) {
            return false;
        }
        OAuthWebView oAuthWebView = this.m0;
        return oAuthWebView == null || oAuthWebView.getUrl() == null || !this.m0.getUrl().startsWith("http");
    }

    protected Dialog v() {
        return ProgressDialog.show(this, getText(ax.O3.d.a), getText(ax.O3.d.f));
    }

    protected synchronized void w() {
        try {
            Dialog dialog = t0;
            if (dialog == null) {
                t0 = v();
            } else if (dialog.isShowing()) {
            }
        } catch (Exception unused) {
            t0 = null;
        }
    }

    protected void x(String str, String str2) {
        if (this.r0.getAndSet(true)) {
            return;
        }
        w();
        if (str2 != null) {
            this.q0.q().S(str2);
            ax.N3.b.f("setting Base Domain", str2, new RuntimeException("base domain being used"));
        }
        new c(str).start();
    }

    protected void y() {
        if (this.p0 != 1 && !getIntent().getBooleanExtra("disableAccountChoosing", false) && getFragmentManager().findFragmentByTag("choose_auth") == null) {
            Map<String, BoxAuthentication.BoxAuthenticationInfo> mapR = BoxAuthentication.o().r(this);
            if (SdkUtils.l(getIntent().getStringExtra("restrictToUserId")) && mapR != null && mapR.size() > 0) {
                FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.replace(ax.O3.b.l, com.box.androidsdk.content.auth.a.a(this), "choose_auth");
                fragmentTransactionBeginTransaction.addToBackStack("choose_auth");
                fragmentTransactionBeginTransaction.commit();
            }
        }
        int i = this.p0;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            Intent intentR = r();
            if (intentR != null) {
                intentR.putExtra("client_id", this.q);
                intentR.putExtra("redirect_uri", this.k0);
                if (!SdkUtils.l(getIntent().getStringExtra("restrictToUserId"))) {
                    intentR.putExtra("restrictToUserId", getIntent().getStringExtra("restrictToUserId"));
                }
                this.l0 = true;
                startActivityForResult(intentR, 1);
                return;
            }
        }
        w();
        this.m0 = l();
        OAuthWebView.c cVarM = m();
        this.n0 = cVarM;
        cVarM.h(this);
        this.m0.setWebViewClient(this.n0);
        if (this.q0.s() != null) {
            this.m0.setBoxAccountEmail(this.q0.s());
        }
        this.m0.b(this.q, this.k0);
    }
}
