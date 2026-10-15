package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import ax.p328u5.n;
import ax.p328u5.o;
import ax.p328u5.p;
import ax.p328u5.q;
import ax.p328u5.r;
import ax.p328u5.s;
import ax.p328u5.t;
import ax.p328u5.u;
import ax.p328u5.v;
import ax.p328u5.w;
import ax.p328u5.x;
import ax.p344v5.h;
import ax.p344v5.i;
import ax.p360w5.f;
import ax.p360w5.g;
import ax.p360w5.m;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
final class d implements m {
    private final ax.B8.a a;
    private final ConnectivityManager b;
    private final Context c;
    final URL d;
    private final ax.F5.a e;
    private final ax.F5.a f;
    private final int g;

    static final class a {
        final URL a;
        final n b;
        final String c;

        a(URL url, n nVar, String str) {
            this.a = url;
            this.b = nVar;
            this.c = str;
        }

        a a(URL url) {
            return new a(url, this.b, this.c);
        }
    }

    static final class b {
        final int a;
        final URL b;
        final long c;

        b(int i, URL url, long j) {
            this.a = i;
            this.b = url;
            this.c = j;
        }
    }

    d(Context context, ax.F5.a aVar, ax.F5.a aVar2) {
        this(context, aVar, aVar2, 130000);
    }

    d(Context context, ax.F5.a aVar, ax.F5.a aVar2, int i) {
        this.a = n.b();
        this.c = context;
        this.b = (ConnectivityManager) context.getSystemService("connectivity");
        this.d = n(com.google.android.datatransport.cct.a.c);
        this.e = aVar2;
        this.f = aVar;
        this.g = i;
    }

    public static /* synthetic */ a d(a aVar, b bVar) {
        URL url = bVar.b;
        if (url == null) {
            return null;
        }
        ax.p408z5.a.b("CctTransportBackend", "Following redirect to: %s", url);
        return aVar.a(bVar.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b e(a aVar) throws IOException {
        ax.p408z5.a.f("CctTransportBackend", "Making request to: %s", aVar.a);
        HttpURLConnection httpURLConnection = (HttpURLConnection) aVar.a.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.g);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", "3.3.0"));
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = aVar.c;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    this.a.a(aVar.b, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    ax.p408z5.a.f("CctTransportBackend", "Status Code: %d", Integer.valueOf(responseCode));
                    ax.p408z5.a.b("CctTransportBackend", "Content-Type: %s", httpURLConnection.getHeaderField("Content-Type"));
                    ax.p408z5.a.b("CctTransportBackend", "Content-Encoding: %s", httpURLConnection.getHeaderField("Content-Encoding"));
                    if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                        return new b(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                    }
                    if (responseCode != 200) {
                        return new b(responseCode, null, 0L);
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        InputStream inputStreamM = m(inputStream, httpURLConnection.getHeaderField("Content-Encoding"));
                        try {
                            b bVar = new b(responseCode, null, v.b(new BufferedReader(new InputStreamReader(inputStreamM))).c());
                            if (inputStreamM != null) {
                                inputStreamM.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return bVar;
                        } catch (Throwable th) {
                            if (inputStreamM != null) {
                                try {
                                    inputStreamM.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                        }
                        throw th3;
                    }
                } catch (Throwable th5) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable th6) {
                        th5.addSuppressed(th6);
                    }
                    throw th5;
                }
            } catch (Throwable th7) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th8) {
                        th7.addSuppressed(th8);
                    }
                }
                throw th7;
            }
        } catch (ax.B8.b e) {
            e = e;
            ax.p408z5.a.d("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new b(400, null, 0L);
        } catch (ConnectException e2) {
            e = e2;
            ax.p408z5.a.d("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new b(500, null, 0L);
        } catch (UnknownHostException e3) {
            e = e3;
            ax.p408z5.a.d("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new b(500, null, 0L);
        } catch (IOException e4) {
            e = e4;
            ax.p408z5.a.d("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new b(400, null, 0L);
        }
    }

    private static String f(Context context) {
        String simOperator = k(context).getSimOperator();
        return simOperator != null ? simOperator : HttpUrl.FRAGMENT_ENCODE_SET;
    }

    private static int g(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return w.b.UNKNOWN_MOBILE_SUBTYPE.h();
        }
        int subtype = networkInfo.getSubtype();
        if (subtype == -1) {
            return w.b.COMBINED.h();
        }
        if (w.b.g(subtype) != null) {
            return subtype;
        }
        return 0;
    }

    private static int h(NetworkInfo networkInfo) {
        return networkInfo == null ? w.c.NONE.h() : networkInfo.getType();
    }

    private static int i(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            ax.p408z5.a.d("CctTransportBackend", "Unable to find version code for package", e);
            return -1;
        }
    }

    private n j(f fVar) {
        t.a aVarL;
        HashMap map = new HashMap();
        for (i iVar : fVar.b()) {
            String strN = iVar.n();
            if (map.containsKey(strN)) {
                ((List) map.get(strN)).add(iVar);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(iVar);
                map.put(strN, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            i iVar2 = (i) ((List) entry.getValue()).get(0);
            u.a aVarB = u.a().f(x.DEFAULT).g(this.f.a()).h(this.e.a()).b(o.a().c(o.b.ANDROID_FIREBASE).b(ax.p328u5.a.a().m(Integer.valueOf(iVar2.i("sdk-version"))).j(iVar2.b("model")).f(iVar2.b("hardware")).d(iVar2.b("device")).l(iVar2.b("product")).k(iVar2.b("os-uild")).h(iVar2.b("manufacturer")).e(iVar2.b("fingerprint")).c(iVar2.b("country")).g(iVar2.b("locale")).i(iVar2.b("mcc_mnc")).b(iVar2.b("application_build")).a()).a());
            try {
                aVarB.i(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused) {
                aVarB.j((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (i iVar3 : (List) entry.getValue()) {
                h hVarE = iVar3.e();
                ax.p312t5.b bVarB = hVarE.b();
                if (bVarB.equals(ax.p312t5.b.b("proto"))) {
                    aVarL = t.l(hVarE.a());
                } else if (bVarB.equals(ax.p312t5.b.b("json"))) {
                    aVarL = t.k(new String(hVarE.a(), Charset.forName("UTF-8")));
                } else {
                    ax.p408z5.a.g("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", bVarB);
                }
                aVarL.d(iVar3.f()).e(iVar3.o()).j(iVar3.j("tz-offset")).g(w.a().c(w.c.g(iVar3.i("net-type"))).b(w.b.g(iVar3.i("mobile-subtype"))).a());
                if (iVar3.d() != null) {
                    aVarL.c(iVar3.d());
                }
                if (iVar3.l() != null) {
                    aVarL.b(p.a().b(s.a().b(r.a().b(iVar3.l()).a()).a()).c(p.b.EVENT_OVERRIDE).a());
                }
                if (iVar3.g() != null || iVar3.h() != null) {
                    q.a aVarA = q.a();
                    if (iVar3.g() != null) {
                        aVarA.b(iVar3.g());
                    }
                    if (iVar3.h() != null) {
                        aVarA.c(iVar3.h());
                    }
                    aVarL.f(aVarA.a());
                }
                arrayList3.add(aVarL.a());
            }
            aVarB.c(arrayList3);
            arrayList2.add(aVarB.a());
        }
        return n.a(arrayList2);
    }

    private static TelephonyManager k(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    static long l() {
        Calendar.getInstance();
        return TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
    }

    private static InputStream m(InputStream inputStream, String str) throws IOException {
        return "gzip".equals(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    private static URL n(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + str, e);
        }
    }

    @Override // ax.p360w5.m
    public i a(i iVar) {
        NetworkInfo activeNetworkInfo = this.b.getActiveNetworkInfo();
        return iVar.p().a("sdk-version", Build.VERSION.SDK_INT).c("model", Build.MODEL).c("hardware", Build.HARDWARE).c("device", Build.DEVICE).c("product", Build.PRODUCT).c("os-uild", Build.ID).c("manufacturer", Build.MANUFACTURER).c("fingerprint", Build.FINGERPRINT).b("tz-offset", l()).a("net-type", h(activeNetworkInfo)).a("mobile-subtype", g(activeNetworkInfo)).c("country", Locale.getDefault().getCountry()).c("locale", Locale.getDefault().getLanguage()).c("mcc_mnc", f(this.c)).c("application_build", Integer.toString(i(this.c))).d();
    }

    @Override // ax.p360w5.m
    public g b(f fVar) {
        n nVarJ = j(fVar);
        URL urlN = this.d;
        String strD = null;
        if (fVar.c() != null) {
            try {
                com.google.android.datatransport.cct.a aVarC = com.google.android.datatransport.cct.a.c(fVar.c());
                strD = aVarC.d() != null ? aVarC.d() : null;
                if (aVarC.e() != null) {
                    urlN = n(aVarC.e());
                }
            } catch (IllegalArgumentException unused) {
                return g.a();
            }
        }
        try {
            b bVar = (b) ax.A5.b.a(5, new a(urlN, nVarJ, strD), new ax.A5.a() { // from class: com.google.android.datatransport.cct.b
                @Override // ax.A5.a
                public final Object apply(Object obj) {
                    return this.a.e((d.a) obj);
                }
            }, new ax.A5.c() { // from class: com.google.android.datatransport.cct.c
                @Override // ax.A5.c
                public final Object a(Object obj, Object obj2) {
                    return d.d((d.a) obj, (d.b) obj2);
                }
            });
            int i = bVar.a;
            if (i == 200) {
                return g.e(bVar.c);
            }
            if (i < 500 && i != 404) {
                return i == 400 ? g.d() : g.a();
            }
            return g.f();
        } catch (IOException e) {
            ax.p408z5.a.d("CctTransportBackend", "Could not make request to the backend", e);
            return g.f();
        }
    }
}
