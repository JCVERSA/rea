package com.box.androidsdk.content.auth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import com.box.androidsdk.content.utils.SdkUtils;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Formatter;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class OAuthWebView extends WebView {
    private String k0;
    private String q;

    private static class InvalidUrlException extends Exception {
        private static final long serialVersionUID = 1;

        private InvalidUrlException() {
        }
    }

    public static class WebViewException extends Exception {
        private final String mDescription;
        private final int mErrorCode;
        private final String mFailingUrl;

        public WebViewException(int i, String str, String str2) {
            this.mErrorCode = i;
            this.mDescription = str;
            this.mFailingUrl = str2;
        }

        public String a() {
            return this.mDescription;
        }

        public int b() {
            return this.mErrorCode;
        }
    }

    public static class b {
        public int a;
        public String b;
        public WebViewException c;

        public b(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public b(WebViewException webViewException) {
            this(2, null);
            this.c = webViewException;
        }
    }

    public static class c extends WebViewClient {
        private boolean a;
        private f b;
        private String c;
        private d d;
        private g e;
        private Handler f = new Handler(Looper.getMainLooper());

        class a implements DialogInterface.OnClickListener {
            final /* synthetic */ HttpAuthHandler q;

            a(HttpAuthHandler httpAuthHandler) {
                this.q = httpAuthHandler;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                this.q.cancel();
                c.this.b.c(new b(0, null));
            }
        }

        class b implements DialogInterface.OnClickListener {
            final /* synthetic */ HttpAuthHandler X;
            final /* synthetic */ View q;

            b(View view, HttpAuthHandler httpAuthHandler) {
                this.q = view;
                this.X = httpAuthHandler;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                this.X.proceed(((EditText) this.q.findViewById(ax.O3.b.s)).getText().toString(), ((EditText) this.q.findViewById(ax.O3.b.o)).getText().toString());
            }
        }

        /* JADX INFO: renamed from: com.box.androidsdk.content.auth.OAuthWebView$c$c, reason: collision with other inner class name */
        class DialogInterfaceOnClickListenerC0563c implements DialogInterface.OnClickListener {
            final /* synthetic */ SslErrorHandler q;

            DialogInterfaceOnClickListenerC0563c(SslErrorHandler sslErrorHandler) {
                this.q = sslErrorHandler;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                c.this.a = true;
                this.q.cancel();
                c.this.b.c(new b(0, null));
            }
        }

        class d implements DialogInterface.OnClickListener {
            final /* synthetic */ SslError X;
            final /* synthetic */ WebView q;

            d(WebView webView, SslError sslError) {
                this.q = webView;
                this.X = sslError;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                c.this.i(this.q.getContext(), this.X);
            }
        }

        class e implements DialogInterface.OnDismissListener {
            e() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (c.this.a) {
                    return;
                }
                c.this.b.c(new b(0, null));
            }
        }

        public interface f {
            void a(String str, String str2);

            boolean c(b bVar);

            void e(String str);
        }

        class g implements Runnable {
            final WeakReference<WebView> X;
            final String q;

            public g(WebView webView, String str) {
                this.q = str;
                this.X = new WeakReference<>(webView);
            }

            @Override // java.lang.Runnable
            public void run() throws Throwable {
                c.this.onReceivedError(this.X.get(), -8, "loading timed out", this.q);
            }
        }

        public c(f fVar, String str) {
            this.b = fVar;
            this.c = str;
        }

        private String d(Context context, Date date) {
            return date == null ? HttpUrl.FRAGMENT_ENCODE_SET : DateFormat.getDateFormat(context).format(date);
        }

        private View e(Context context, SslCertificate sslCertificate) {
            View viewInflate = LayoutInflater.from(context).inflate(ax.O3.c.h, (ViewGroup) null);
            SslCertificate.DName issuedTo = sslCertificate.getIssuedTo();
            if (issuedTo != null) {
                ((TextView) viewInflate.findViewById(ax.O3.b.p)).setText(issuedTo.getCName());
                ((TextView) viewInflate.findViewById(ax.O3.b.q)).setText(issuedTo.getOName());
                ((TextView) viewInflate.findViewById(ax.O3.b.r)).setText(issuedTo.getUName());
            }
            SslCertificate.DName issuedBy = sslCertificate.getIssuedBy();
            if (issuedBy != null) {
                ((TextView) viewInflate.findViewById(ax.O3.b.g)).setText(issuedBy.getCName());
                ((TextView) viewInflate.findViewById(ax.O3.b.h)).setText(issuedBy.getOName());
                ((TextView) viewInflate.findViewById(ax.O3.b.i)).setText(issuedBy.getUName());
            }
            ((TextView) viewInflate.findViewById(ax.O3.b.k)).setText(d(context, sslCertificate.getValidNotBeforeDate()));
            ((TextView) viewInflate.findViewById(ax.O3.b.j)).setText(d(context, sslCertificate.getValidNotAfterDate()));
            return viewInflate;
        }

        private Uri f(String str) {
            Uri uri = Uri.parse(str);
            if (SdkUtils.l(this.c)) {
                return uri;
            }
            Uri uri2 = Uri.parse(this.c);
            if (uri2.getScheme() != null && uri2.getScheme().equals(uri.getScheme()) && uri2.getAuthority().equals(uri.getAuthority())) {
                return uri;
            }
            return null;
        }

        private String g(Uri uri, String str) throws InvalidUrlException {
            if (uri == null) {
                return null;
            }
            try {
                return uri.getQueryParameter(str);
            } catch (Exception unused) {
                return null;
            }
        }

        public void h(d dVar) {
            this.d = dVar;
        }

        protected void i(Context context, SslError sslError) {
            new AlertDialog.Builder(context).setTitle(ax.O3.d.g).setView(e(context, sslError.getCertificate())).create().show();
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            g gVar = this.e;
            if (gVar != null) {
                this.f.removeCallbacks(gVar);
            }
            super.onPageFinished(webView, str);
            d dVar = this.d;
            if (dVar != null) {
                dVar.d(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            try {
                Uri uriF = f(str);
                String strG = g(uriF, "code");
                if (!SdkUtils.l(strG) && (webView instanceof OAuthWebView) && !SdkUtils.l(((OAuthWebView) webView).getStateString())) {
                    if (!((OAuthWebView) webView).getStateString().equals(uriF.getQueryParameter("state"))) {
                        throw new InvalidUrlException();
                    }
                }
                if (!SdkUtils.l(g(uriF, "error"))) {
                    this.b.c(new b(0, null));
                } else if (!SdkUtils.l(strG)) {
                    String strG2 = g(uriF, "base_domain");
                    if (strG2 != null) {
                        this.b.a(strG, strG2);
                    } else {
                        this.b.e(strG);
                    }
                }
            } catch (InvalidUrlException unused) {
                this.b.c(new b(1, null));
            }
            g gVar = this.e;
            if (gVar != null) {
                this.f.removeCallbacks(gVar);
            }
            g gVar2 = new g(webView, str);
            this.e = gVar2;
            this.f.postDelayed(gVar2, 30000L);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0089  */
        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) throws Throwable {
            g gVar = this.e;
            if (gVar != null) {
                this.f.removeCallbacks(gVar);
            }
            if (this.b.c(new b(new WebViewException(i, str, str2)))) {
                return;
            }
            if (i == -8) {
                String strJ = SdkUtils.j(webView.getContext(), "offline.html");
                Formatter formatter = new Formatter();
                formatter.format(strJ, webView.getContext().getString(ax.O3.d.C), webView.getContext().getString(ax.O3.d.D), webView.getContext().getString(ax.O3.d.E));
                webView.loadDataWithBaseURL(null, formatter.toString(), "text/html", "UTF-8", null);
                formatter.close();
            } else if (i == -6 || i == -2) {
                if (SdkUtils.m(webView.getContext())) {
                    String strJ2 = SdkUtils.j(webView.getContext(), "offline.html");
                    Formatter formatter2 = new Formatter();
                    formatter2.format(strJ2, webView.getContext().getString(ax.O3.d.C), webView.getContext().getString(ax.O3.d.D), webView.getContext().getString(ax.O3.d.E));
                    webView.loadDataWithBaseURL(null, formatter2.toString(), "text/html", "UTF-8", null);
                    formatter2.close();
                } else {
                    String strJ3 = SdkUtils.j(webView.getContext(), "offline.html");
                    Formatter formatter3 = new Formatter();
                    formatter3.format(strJ3, webView.getContext().getString(ax.O3.d.r), webView.getContext().getString(ax.O3.d.s), webView.getContext().getString(ax.O3.d.t));
                    webView.loadDataWithBaseURL(null, formatter3.toString(), "text/html", "UTF-8", null);
                    formatter3.close();
                }
            }
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            View viewInflate = LayoutInflater.from(webView.getContext()).inflate(ax.O3.c.c, (ViewGroup) null);
            new AlertDialog.Builder(webView.getContext()).setTitle(ax.O3.d.k).setView(viewInflate).setPositiveButton(ax.O3.d.j, new b(viewInflate, httpAuthHandler)).setNegativeButton(ax.O3.d.i, new a(httpAuthHandler)).create().show();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            String string;
            g gVar = this.e;
            if (gVar != null) {
                this.f.removeCallbacks(gVar);
            }
            Resources resources = webView.getContext().getResources();
            StringBuilder sb = new StringBuilder(resources.getString(ax.O3.d.h));
            sb.append(" ");
            int primaryError = sslError.getPrimaryError();
            if (primaryError == 0) {
                string = resources.getString(ax.O3.d.z);
            } else if (primaryError == 1) {
                string = resources.getString(ax.O3.d.w);
            } else if (primaryError == 2) {
                string = resources.getString(ax.O3.d.x);
            } else if (primaryError == 3) {
                string = resources.getString(ax.O3.d.A);
            } else if (primaryError != 4) {
                string = primaryError != 5 ? resources.getString(ax.O3.d.y) : resources.getString(ax.O3.d.y);
            } else {
                string = webView.getResources().getString(ax.O3.d.v);
            }
            sb.append(string);
            sb.append(" ");
            sb.append(resources.getString(ax.O3.d.B));
            this.a = false;
            AlertDialog.Builder negativeButton = new AlertDialog.Builder(webView.getContext()).setTitle(ax.O3.d.g).setMessage(sb.toString()).setIcon(ax.O3.a.a).setNegativeButton(ax.O3.d.e, new DialogInterfaceOnClickListenerC0563c(sslErrorHandler));
            negativeButton.setNeutralButton(ax.O3.d.u, new d(webView, sslError));
            AlertDialog alertDialogCreate = negativeButton.create();
            alertDialogCreate.setOnDismissListener(new e());
            alertDialogCreate.show();
        }
    }

    public interface d {
        void d(WebView webView, String str);
    }

    public OAuthWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(Uri.Builder builder) {
        String strI = SdkUtils.i();
        this.q = strI;
        builder.appendQueryParameter("state", strI);
        loadUrl(builder.build().toString());
    }

    public void b(String str, String str2) {
        a(c(str, str2));
    }

    protected Uri.Builder c(String str, String str2) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("account.box.com");
        builder.appendPath("api");
        builder.appendPath("oauth2");
        builder.appendPath("authorize");
        builder.appendQueryParameter("response_type", "code");
        builder.appendQueryParameter("client_id", str);
        builder.appendQueryParameter("redirect_uri", str2);
        String str3 = this.k0;
        if (str3 != null) {
            builder.appendQueryParameter("box_login", str3);
        }
        return builder;
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        String url = getUrl();
        if (url != null && url.contains("app.box.com")) {
            if (keyEvent.getKeyCode() == 20 && keyEvent.getAction() == 0) {
                dispatchKeyEvent(new KeyEvent(0, 61));
                return true;
            }
            if (keyEvent.getKeyCode() == 20 && keyEvent.getAction() == 1) {
                dispatchKeyEvent(new KeyEvent(1, 61));
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public String getStateString() {
        return this.q;
    }

    public void setBoxAccountEmail(String str) {
        this.k0 = str;
    }
}
