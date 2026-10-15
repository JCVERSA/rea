package com.google.firebase.remoteconfig.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import ax.P8.k;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1469b;
import ax.Q6.m;
import ax.p345v6.i;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class g {
    static final int[] q = {2, 4, 8, 16, 32, 64, 128, 256};
    private static final Pattern r = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    private final Set<ax.P8.c> a;
    private int c;
    private final ScheduledExecutorService g;
    private final d h;
    private final ax.p075e8.f i;
    private final ax.I8.e j;
    com.google.firebase.remoteconfig.internal.b k;
    private final Context l;
    private final String m;
    private final e p;
    private final int f = 8;
    private boolean b = false;
    private final Random n = new Random();
    private final ax.p345v6.f o = i.c();
    private boolean d = false;
    private boolean e = false;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.e();
        }
    }

    class b implements ax.P8.c {
        b() {
        }

        @Override // ax.P8.c
        public void a(ax.P8.i iVar) {
            g.this.j();
            g.this.s(iVar);
        }

        @Override // ax.P8.c
        public void b(ax.P8.b bVar) {
        }
    }

    public g(ax.p075e8.f fVar, ax.I8.e eVar, d dVar, com.google.firebase.remoteconfig.internal.b bVar, Context context, String str, Set<ax.P8.c> set, e eVar2, ScheduledExecutorService scheduledExecutorService) {
        this.a = set;
        this.g = scheduledExecutorService;
        this.c = Math.max(8 - eVar2.g().b(), 1);
        this.i = fVar;
        this.h = dVar;
        this.j = eVar;
        this.k = bVar;
        this.l = context;
        this.m = str;
        this.p = eVar2;
    }

    private void B(Date date) {
        int iB = this.p.g().b() + 1;
        this.p.m(iB, new Date(date.getTime() + m(iB)));
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0077  */
    /* JADX WARN: Code duplicated, block: B:62:0x010a  */
    /* JADX WARN: Code duplicated, block: B:64:0x010d  */
    /* JADX WARN: Code duplicated, block: B:72:0x0148  */
    public static /* synthetic */ AbstractC1477j a(g gVar, AbstractC1477j abstractC1477j, AbstractC1477j abstractC1477j2) throws Throwable {
        Integer numValueOf;
        Throwable th;
        HttpURLConnection httpURLConnection;
        boolean z;
        k kVar;
        gVar.getClass();
        try {
            if (!abstractC1477j.n()) {
                throw new IOException(abstractC1477j.j());
            }
            gVar.w(true);
            httpURLConnection = (HttpURLConnection) abstractC1477j.k();
            try {
                int responseCode = httpURLConnection.getResponseCode();
                numValueOf = Integer.valueOf(responseCode);
                if (responseCode == 200) {
                    try {
                        try {
                            gVar.t();
                            gVar.p.i();
                            gVar.z(httpURLConnection).h();
                        } catch (IOException e) {
                            e = e;
                            Log.d("FirebaseRemoteConfig", "Exception connecting to real-time RC backend. Retrying the connection...", e);
                            gVar.g(httpURLConnection);
                            gVar.w(false);
                            boolean z2 = numValueOf == null || gVar.p(numValueOf.intValue());
                            if (z2) {
                                gVar.B(new Date(gVar.o.a()));
                            }
                            if (z2 || numValueOf.intValue() == 200) {
                                gVar.u();
                            } else {
                                String strR = String.format("Unable to connect to the server. Try again in a few minutes. HTTP status code: %d", numValueOf);
                                if (numValueOf.intValue() == 403) {
                                    strR = gVar.r(httpURLConnection.getErrorStream());
                                }
                                kVar = new k(numValueOf.intValue(), strR, ax.P8.i.a.CONFIG_UPDATE_STREAM_ERROR);
                            }
                            return m.e(null);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        gVar.g(httpURLConnection);
                        gVar.w(false);
                        if (numValueOf != null || gVar.p(numValueOf.intValue())) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            gVar.B(new Date(gVar.o.a()));
                        }
                        if (!z || numValueOf.intValue() == 200) {
                            gVar.u();
                        } else {
                            String strR2 = String.format("Unable to connect to the server. Try again in a few minutes. HTTP status code: %d", numValueOf);
                            if (numValueOf.intValue() == 403) {
                                strR2 = gVar.r(httpURLConnection.getErrorStream());
                            }
                            gVar.s(new k(numValueOf.intValue(), strR2, ax.P8.i.a.CONFIG_UPDATE_STREAM_ERROR));
                        }
                        throw th;
                    }
                }
                gVar.g(httpURLConnection);
                gVar.w(false);
                boolean zP = gVar.p(responseCode);
                if (zP) {
                    gVar.B(new Date(gVar.o.a()));
                }
                if (zP || responseCode == 200) {
                    gVar.u();
                } else {
                    String strR3 = String.format("Unable to connect to the server. Try again in a few minutes. HTTP status code: %d", numValueOf);
                    if (responseCode == 403) {
                        strR3 = gVar.r(httpURLConnection.getErrorStream());
                    }
                    kVar = new k(responseCode, strR3, ax.P8.i.a.CONFIG_UPDATE_STREAM_ERROR);
                    gVar.s(kVar);
                }
            } catch (IOException e2) {
                e = e2;
                numValueOf = null;
            } catch (Throwable th3) {
                numValueOf = null;
                th = th3;
                gVar.g(httpURLConnection);
                gVar.w(false);
                if (numValueOf != null) {
                    z = true;
                } else {
                    z = true;
                }
                if (z) {
                    gVar.B(new Date(gVar.o.a()));
                }
                if (z) {
                    gVar.u();
                } else {
                    gVar.u();
                }
                throw th;
            }
            return m.e(null);
        } catch (IOException e3) {
            e = e3;
            httpURLConnection = null;
            numValueOf = null;
        } catch (Throwable th4) {
            numValueOf = null;
            th = th4;
            httpURLConnection = null;
        }
    }

    public static /* synthetic */ AbstractC1477j b(g gVar, AbstractC1477j abstractC1477j, AbstractC1477j abstractC1477j2, AbstractC1477j abstractC1477j3) {
        gVar.getClass();
        if (!abstractC1477j.n()) {
            return m.d(new ax.P8.h("Firebase Installations failed to get installation auth token for config update listener connection.", abstractC1477j.j()));
        }
        if (!abstractC1477j2.n()) {
            return m.d(new ax.P8.h("Firebase Installations failed to get installation ID for config update listener connection.", abstractC1477j2.j()));
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) gVar.o().openConnection();
            gVar.y(httpURLConnection, (String) abstractC1477j2.k(), ((com.google.firebase.installations.g) abstractC1477j.k()).b());
            return m.e(httpURLConnection);
        } catch (IOException e) {
            return m.d(new ax.P8.h("Failed to open HTTP stream connection", e));
        }
    }

    private synchronized boolean f() {
        return (this.a.isEmpty() || this.b || this.d || this.e) ? false : true;
    }

    private JSONObject i(String str) {
        HashMap map = new HashMap();
        map.put("project", k(this.i.n().c()));
        map.put("namespace", this.m);
        map.put("lastKnownVersionNumber", Long.toString(this.h.r()));
        map.put("appId", this.i.n().c());
        map.put("sdkVersion", "22.0.0");
        map.put("appInstanceId", str);
        return new JSONObject(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        this.d = true;
    }

    private static String k(String str) {
        Matcher matcher = r.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private String l() {
        try {
            Context context = this.l;
            byte[] bArrA = ax.p345v6.a.a(context, context.getPackageName());
            if (bArrA != null) {
                return ax.p345v6.k.b(bArrA, false);
            }
            Log.e("FirebaseRemoteConfig", "Could not get fingerprint hash for package: " + this.l.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i("FirebaseRemoteConfig", "No such package: " + this.l.getPackageName());
            return null;
        }
    }

    private long m(int i) {
        int[] iArr = q;
        int length = iArr.length;
        if (i >= length) {
            i = length;
        }
        long millis = TimeUnit.MINUTES.toMillis(iArr[i - 1]);
        return (millis / 2) + ((long) this.n.nextInt((int) millis));
    }

    private String n(String str) {
        return String.format("https://firebaseremoteconfigrealtime.googleapis.com/v1/projects/%s/namespaces/%s:streamFetchInvalidations", k(this.i.n().c()), str);
    }

    private URL o() {
        try {
            return new URL(n(this.m));
        } catch (MalformedURLException unused) {
            Log.e("FirebaseRemoteConfig", "URL is malformed");
            return null;
        }
    }

    private boolean p(int i) {
        return i == 408 || i == 429 || i == 502 || i == 503 || i == 504;
    }

    private synchronized void q(long j) {
        try {
            if (f()) {
                int i = this.c;
                if (i > 0) {
                    this.c = i - 1;
                    this.g.schedule(new a(), j, TimeUnit.MILLISECONDS);
                } else if (!this.e) {
                    s(new ax.P8.h("Unable to connect to the server. Check your connection and try again.", ax.P8.i.a.CONFIG_UPDATE_STREAM_ERROR));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private String r(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
        } catch (IOException unused) {
            if (sb.length() == 0) {
                return "Unable to connect to the server, access is forbidden. HTTP status code: 403";
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void s(ax.P8.i iVar) {
        Iterator<ax.P8.c> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(iVar);
        }
    }

    private synchronized void t() {
        this.c = 8;
    }

    private void v(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty("X-Goog-Firebase-Installations-Auth", str);
        httpURLConnection.setRequestProperty("X-Goog-Api-Key", this.i.n().b());
        httpURLConnection.setRequestProperty("X-Android-Package", this.l.getPackageName());
        httpURLConnection.setRequestProperty("X-Android-Cert", l());
        httpURLConnection.setRequestProperty("X-Google-GFE-Can-Retry", "yes");
        httpURLConnection.setRequestProperty("X-Accept-Response-Streaming", "true");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
    }

    private synchronized void w(boolean z) {
        this.b = z;
    }

    public void A() {
        q(0L);
    }

    @SuppressLint({"VisibleForTests", "DefaultLocale"})
    public void e() {
        if (f()) {
            if (new Date(this.o.a()).before(this.p.g().a())) {
                u();
            } else {
                final AbstractC1477j<HttpURLConnection> abstractC1477jH = h();
                m.j(abstractC1477jH).g(this.g, new InterfaceC1469b() { // from class: ax.Q8.l
                    @Override // ax.Q6.InterfaceC1469b
                    public final Object a(AbstractC1477j abstractC1477j) {
                        return com.google.firebase.remoteconfig.internal.g.a(this.a, abstractC1477jH, abstractC1477j);
                    }
                });
            }
        }
    }

    public void g(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            try {
                httpURLConnection.getInputStream().close();
                if (httpURLConnection.getErrorStream() != null) {
                    httpURLConnection.getErrorStream().close();
                }
            } catch (IOException unused) {
            }
        }
    }

    @SuppressLint({"VisibleForTests"})
    public AbstractC1477j<HttpURLConnection> h() {
        final AbstractC1477j<com.google.firebase.installations.g> abstractC1477jB = this.j.b(false);
        final AbstractC1477j<String> abstractC1477jA = this.j.a();
        return m.j(abstractC1477jB, abstractC1477jA).i(this.g, new InterfaceC1469b() { // from class: ax.Q8.m
            @Override // ax.Q6.InterfaceC1469b
            public final Object a(AbstractC1477j abstractC1477j) {
                return com.google.firebase.remoteconfig.internal.g.b(this.a, abstractC1477jB, abstractC1477jA, abstractC1477j);
            }
        });
    }

    @SuppressLint({"VisibleForTests"})
    public synchronized void u() {
        q(Math.max(0L, this.p.g().a().getTime() - new Date(this.o.a()).getTime()));
    }

    void x(boolean z) {
        this.e = z;
    }

    @SuppressLint({"VisibleForTests"})
    public void y(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        httpURLConnection.setRequestMethod("POST");
        v(httpURLConnection, str2);
        byte[] bytes = i(str).toString().getBytes("utf-8");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    @SuppressLint({"VisibleForTests"})
    public synchronized com.google.firebase.remoteconfig.internal.a z(HttpURLConnection httpURLConnection) {
        return new com.google.firebase.remoteconfig.internal.a(httpURLConnection, this.h, this.k, this.a, new b(), this.g);
    }
}
