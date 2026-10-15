package com.google.android.gms.measurement.internal;

import ax.H6.AbstractC1062v0;
import ax.p265q6.C2254p;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class Z3 implements Runnable {
    private final InterfaceC6282a4 X;
    private final String Y;
    private final Map<String, String> Z;
    private final /* synthetic */ Y3 k0;
    private final URL q;

    public Z3(Y3 y3, String str, URL url, byte[] bArr, Map<String, String> map, InterfaceC6282a4 interfaceC6282a4) {
        this.k0 = y3;
        C2254p.f(str);
        C2254p.l(url);
        C2254p.l(interfaceC6282a4);
        this.q = url;
        this.X = interfaceC6282a4;
        this.Y = str;
        this.Z = null;
    }

    private final void b(final int i, final Exception exc, final byte[] bArr, final Map<String, List<String>> map) {
        this.k0.l().D(new Runnable() { // from class: com.google.android.gms.measurement.internal.c4
            @Override // java.lang.Runnable
            public final void run() {
                this.q.a(i, exc, bArr, map);
            }
        });
    }

    final /* synthetic */ void a(int i, Exception exc, byte[] bArr, Map map) {
        this.X.a(this.Y, i, exc, bArr, map);
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        HttpURLConnection httpURLConnection;
        Map<String, List<String>> headerFields;
        this.k0.k();
        int responseCode = 0;
        try {
            URLConnection uRLConnectionB = AbstractC1062v0.a().b(this.q, "client-measurement");
            if (!(uRLConnectionB instanceof HttpURLConnection)) {
                throw new IOException("Failed to obtain HTTP connection");
            }
            httpURLConnection = (HttpURLConnection) uRLConnectionB;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(60000);
            httpURLConnection.setReadTimeout(61000);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            try {
                responseCode = httpURLConnection.getResponseCode();
                headerFields = httpURLConnection.getHeaderFields();
                try {
                    Y3 y3 = this.k0;
                    byte[] bArrV = Y3.v(httpURLConnection);
                    httpURLConnection.disconnect();
                    b(responseCode, null, bArrV, headerFields);
                } catch (IOException e) {
                    e = e;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    b(responseCode, e, null, headerFields);
                } catch (Throwable th) {
                    th = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    b(responseCode, null, null, headerFields);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                headerFields = null;
            } catch (Throwable th2) {
                th = th2;
                headerFields = null;
            }
        } catch (IOException e3) {
            e = e3;
            httpURLConnection = null;
            headerFields = null;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            headerFields = null;
        }
    }
}
