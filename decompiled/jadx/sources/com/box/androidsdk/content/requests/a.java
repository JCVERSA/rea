package com.box.androidsdk.content.requests;

import ax.K3.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
class a {
    protected final HttpURLConnection a;
    protected final ax.L3.b b;

    public a(URL url, BoxRequest.Methods methods, ax.L3.b bVar) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        this.a = httpURLConnection;
        httpURLConnection.setRequestMethod(methods.toString());
        this.b = bVar;
        if (g.l && (httpURLConnection instanceof HttpsURLConnection)) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(new BoxRequest.c());
        }
    }

    public a a(String str, String str2) {
        this.a.addRequestProperty(str, str2);
        return this;
    }

    public HttpURLConnection b() {
        return this.a;
    }

    public a c(InputStream inputStream) throws IOException {
        this.a.setDoOutput(true);
        OutputStream outputStream = this.a.getOutputStream();
        int i = inputStream.read();
        while (i != -1) {
            outputStream.write(i);
            i = inputStream.read();
        }
        outputStream.close();
        return this;
    }
}
