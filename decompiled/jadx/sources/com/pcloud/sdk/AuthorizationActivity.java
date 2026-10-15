package com.pcloud.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import ax.Sa.h;
import ax.Sa.l;
import ax.Sa.s;
import ax.Sa.t;
import ax.p409z6.C3772df;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class AuthorizationActivity extends Activity {
    private static final Uri m0 = Uri.parse("https://my.pcloud.com/oauth2/authorize");
    private WebView X;
    private com.pcloud.sdk.b Y;
    private BroadcastReceiver Z;
    private final String q = "pcloud-oauth://";
    private l k0 = null;
    private boolean l0 = true;

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = new Intent(AuthorizationActivity.this, (Class<?>) AuthorizationActivity.class);
            intent2.setAction("AuthorizationActivity.REFRESH_ACTION");
            intent2.putExtra("AuthorizationActivity.EXTRA_URL", (Uri) intent.getParcelableExtra("AuthorizationActivity.EXTRA_URL"));
            intent2.putExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_REQUEST", AuthorizationActivity.this.Y);
            intent2.addFlags(603979776);
            AuthorizationActivity.this.startActivity(intent2);
        }
    }

    class b extends WebViewClient {
        b() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (AuthorizationActivity.this.isFinishing()) {
                return;
            }
            AuthorizationActivity.this.l(h.AUTH_ERROR, String.format(Locale.US, "Error while loading url '%s':\n%d: %s", str2, Integer.valueOf(i), str));
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(C3772df.zzm)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            Uri url = webResourceRequest.getUrl();
            if (url != null) {
                return shouldOverrideUrlLoading(webView, url.toString());
            }
            return false;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return AuthorizationActivity.this.k(str);
        }
    }

    private Uri d(com.pcloud.sdk.b bVar) {
        Uri.Builder builderAppendQueryParameter = m0.buildUpon().appendQueryParameter("response_type", bVar.q == com.pcloud.sdk.b.c.CODE ? "code" : "token").appendQueryParameter("client_id", bVar.X).appendQueryParameter("redirect_uri", "pcloud-oauth://" + getPackageName());
        if (bVar.k0) {
            builderAppendQueryParameter.appendQueryParameter("force_reapprove", "true");
        }
        if (!bVar.Y.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = bVar.Y.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
            builderAppendQueryParameter.appendQueryParameter("permissions", sb.toString());
        }
        return builderAppendQueryParameter.build();
    }

    public static Intent e(Context context, com.pcloud.sdk.b bVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context argument cannot be null.");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("AuthorizationRequest argument cannot be null.");
        }
        Intent intent = new Intent(context, (Class<?>) AuthorizationActivity.class);
        intent.putExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_REQUEST", bVar);
        return intent;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void f(Uri uri) {
        this.X.setWebViewClient(j());
        this.X.setVisibility(0);
        WebSettings settings = this.X.getSettings();
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.X.loadUrl(uri.toString());
    }

    private long g(Map<String, String> map, String str) {
        String strI = i(map, str);
        try {
            return Long.parseLong(strI);
        } catch (NumberFormatException unused) {
            throw new IllegalStateException("'" + strI + "' is not a valid value for '" + str + "'.");
        }
    }

    public static com.pcloud.sdk.a h(Intent intent) {
        if (intent.hasExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_RESULT")) {
            return (com.pcloud.sdk.a) intent.getParcelableExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_RESULT");
        }
        throw new IllegalArgumentException("Invalid intent provided.");
    }

    private String i(Map<String, String> map, String str) {
        String str2 = map.get(str);
        if (str2 != null) {
            return str2;
        }
        throw new IllegalStateException("Missing '" + str + "' response parameter.");
    }

    private WebViewClient j() {
        return new b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k(String str) {
        if (str == null) {
            return false;
        }
        if (!str.startsWith("pcloud-oauth://" + getPackageName())) {
            return false;
        }
        try {
            Map<String, String> mapB = c.b(str);
            if (!mapB.containsKey("access_token") && !mapB.containsKey("code")) {
                l(h.ACCESS_DENIED, null);
                return true;
            }
            String strI = this.Y.q == com.pcloud.sdk.b.c.TOKEN ? i(mapB, "access_token") : null;
            n(new com.pcloud.sdk.a(this.Y, h.ACCESS_GRANTED, strI, g(mapB, "userid"), g(mapB, "locationid"), this.Y.q == com.pcloud.sdk.b.c.CODE ? i(mapB, "code") : null, i(mapB, "hostname"), null));
            return true;
        } catch (Exception e) {
            l(h.AUTH_ERROR, e.getMessage());
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(h hVar, String str) {
        n(new com.pcloud.sdk.a(this.Y, hVar, null, 0L, 0L, null, null, str));
    }

    private void m(Uri uri, String str) {
        this.X.setVisibility(4);
        l lVar = new l(uri);
        this.k0 = lVar;
        lVar.e(this, str);
        this.l0 = false;
        this.Z = new a();
        ax.L0.a.b(this).c(this.Z, new IntentFilter("CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION"));
    }

    private void n(com.pcloud.sdk.a aVar) {
        Intent intent = new Intent();
        intent.putExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_RESULT", aVar);
        setResult(aVar.X == h.CANCELLED ? 0 : -1, intent);
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.X.canGoBack()) {
            this.X.goBack();
        } else {
            l(h.CANCELLED, null);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if ("CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION".equals(getIntent().getAction())) {
            setResult(0);
            finish();
            return;
        }
        if (intent == null || !intent.hasExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_REQUEST")) {
            setResult(0);
            Toast.makeText(this, "Error", 1).show();
            finish();
            return;
        }
        this.Y = (com.pcloud.sdk.b) intent.getParcelableExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_REQUEST");
        setContentView(t.a);
        this.X = (WebView) findViewById(s.a);
        Uri uriD = d(this.Y);
        String strA = c.a(this, this.Y.Z);
        if (strA == null) {
            f(uriD);
            return;
        }
        try {
            m(uriD, strA);
        } catch (ActivityNotFoundException unused) {
            f(uriD);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        l lVar = this.k0;
        if (lVar != null) {
            lVar.d(this);
            this.k0 = null;
        }
        if (this.Z != null) {
            ax.L0.a.b(this).f(this.Z);
            this.Z = null;
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        com.pcloud.sdk.b bVar = (com.pcloud.sdk.b) getIntent().getParcelableExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_REQUEST");
        Uri uri = (Uri) intent.getParcelableExtra("AuthorizationActivity.EXTRA_URL");
        intent.putExtra("com.pcloud.authentication.AuthorizationActivity.ARGUMENT_AUTH_REQUEST", bVar);
        setIntent(intent);
        if (bVar == null || uri == null) {
            l(h.AUTH_ERROR, bVar == null ? "Missing intent extra ARGUMENT_AUTH_REQUEST" : "Missing intent extra EXTRA_REDIRECT_URL");
            return;
        }
        if ("AuthorizationActivity.REFRESH_ACTION".equals(intent.getAction())) {
            ax.L0.a.b(this).d(new Intent("CustomTabActivity.DESTROY_ACTION"));
            k(uri.toString());
        } else if ("CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION".equals(intent.getAction())) {
            k(uri.toString());
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.X.restoreState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.l0 && this.X.getVisibility() != 0) {
            l(h.CANCELLED, null);
        }
        this.l0 = true;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.X.saveState(bundle);
    }
}
