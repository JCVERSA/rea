package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.annotation.Keep;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class WebViewActivity extends ax.V2.c {
    private String mDropboxKey;
    private ProgressBar mProgress;
    private Uri mUri;
    private WebView mWebView;

    private class b extends WebChromeClient {
        private b() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }
    }

    private class c extends WebViewClient {
        private c() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            WebViewActivity.this.mProgress.setVisibility(8);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith(WebViewActivity.this.mDropboxKey)) {
                WebViewActivity.this.mWebView.loadUrl(str);
                return true;
            }
            WebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            WebViewActivity.this.finish();
            return true;
        }
    }

    private void setUpWebView() {
        this.mWebView.setVerticalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setWebViewClient(new c());
        this.mWebView.setWebChromeClient(new b());
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        try {
            this.mWebView.loadUrl(this.mUri.toString());
        } catch (SecurityException unused) {
        }
        this.mWebView.getSettings().setSavePassword(false);
        this.mWebView.getSettings().setSaveFormData(false);
        this.mProgress.setVisibility(0);
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        return true;
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webview);
        setTitle(R.string.location_dropbox);
        onSetContentView(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().B(R.drawable.ic_close);
        }
        this.mWebView = (WebView) findViewById(R.id.webview);
        this.mProgress = (ProgressBar) findViewById(R.id.progress_circle);
        String action = getIntent().getAction();
        Uri data = getIntent().getData();
        if (!"android.intent.action.VIEW".equals(action) || data == null) {
            finish();
            return;
        }
        this.mUri = data;
        this.mDropboxKey = getString(R.string.dropbox_key);
        setUpWebView();
        this.mWebView.requestFocus();
    }
}
