package com.box.androidsdk.content.requests;

import ax.N3.f;
import com.box.androidsdk.content.BoxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
class c extends a {
    private static final Logger j = Logger.getLogger(c.class.getName());
    private final StringBuilder c;
    private OutputStream d;
    private InputStream e;
    private String f;
    private long g;
    private Map<String, String> h;
    private boolean i;

    public c(URL url, BoxRequest.Methods methods, ax.L3.b bVar) throws IOException {
        super(url, methods, bVar);
        this.c = new StringBuilder();
        this.h = new HashMap();
        this.i = true;
        a("Content-Type", "multipart/form-data; boundary=da39a3ee5e6b4b0d3255bfef95601890afd80709");
    }

    private void i() throws IOException {
        if (!this.i) {
            j("\r\n");
        }
        this.i = false;
        j("--");
        j("da39a3ee5e6b4b0d3255bfef95601890afd80709");
    }

    private void j(String str) throws IOException {
        this.d.write(str.getBytes(Charset.forName("UTF-8")));
        if (j.isLoggable(Level.FINE)) {
            this.c.append(str);
        }
    }

    private void k(String[][] strArr) throws IOException {
        l(strArr, null);
    }

    private void l(String[][] strArr, String str) throws IOException {
        i();
        j("\r\n");
        j("Content-Disposition: form-data");
        for (int i = 0; i < strArr.length; i++) {
            j("; ");
            j(strArr[i][0]);
            j("=\"");
            j(strArr[i][1]);
            j("\"");
        }
        if (str != null) {
            j("\r\nContent-Type: ");
            j(str);
        }
        j("\r\n\r\n");
    }

    @Override // com.box.androidsdk.content.requests.a
    public a c(InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void d(String str, String str2) {
        this.h.put(str, str2);
    }

    public void e(String str, Date date) {
        this.h.put(str, ax.N3.a.a(date));
    }

    public void f(InputStream inputStream, String str) {
        this.e = inputStream;
        this.f = str;
    }

    public void g(InputStream inputStream, String str, long j2) {
        f(inputStream, str);
        this.g = j2;
    }

    protected void h(HttpURLConnection httpURLConnection, ax.L3.b bVar) throws BoxException {
        try {
            try {
                try {
                    httpURLConnection.setChunkedStreamingMode(0);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    this.d = httpURLConnection.getOutputStream();
                    for (Map.Entry<String, String> entry : this.h.entrySet()) {
                        k(new String[][]{new String[]{"name", entry.getKey()}});
                        j(entry.getValue());
                    }
                    l(new String[][]{new String[]{"name", "filename"}, new String[]{"filename", this.f}}, "application/octet-stream");
                    OutputStream fVar = this.d;
                    if (bVar != null) {
                        fVar = new f(this.d, bVar, this.g);
                    }
                    byte[] bArr = new byte[8192];
                    int i = this.e.read(bArr);
                    while (i != -1) {
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException();
                        }
                        fVar.write(bArr, 0, i);
                        i = this.e.read(bArr);
                    }
                    if (j.isLoggable(Level.FINE)) {
                        this.c.append("<File Contents Omitted>");
                    }
                    i();
                    j("--");
                    OutputStream outputStream = this.d;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception unused) {
                        }
                    }
                } catch (Throwable th) {
                    OutputStream outputStream2 = this.d;
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Exception unused2) {
                        }
                    }
                    throw th;
                }
            } catch (InterruptedException e) {
                throw new BoxException("Thread has been interrupted", e);
            }
        } catch (IOException e2) {
            throw new BoxException("Couldn't connect to the Box API due to a network error.", e2);
        }
    }
}
