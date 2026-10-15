package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import ax.P8.i;
import ax.P8.k;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1469b;
import ax.Q6.m;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {
    private final Set<ax.P8.c> a;
    private final HttpURLConnection b;
    private final d c;
    private final b d;
    private final ax.P8.c e;
    private final ScheduledExecutorService f;
    private final Random g = new Random();

    /* JADX INFO: renamed from: com.google.firebase.remoteconfig.internal.a$a, reason: collision with other inner class name */
    class RunnableC0584a implements Runnable {
        final /* synthetic */ long X;
        final /* synthetic */ int q;

        RunnableC0584a(int i, long j) {
            this.q = i;
            this.X = j;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            a.this.d(this.q, this.X);
        }
    }

    public a(HttpURLConnection httpURLConnection, d dVar, b bVar, Set<ax.P8.c> set, ax.P8.c cVar, ScheduledExecutorService scheduledExecutorService) {
        this.b = httpURLConnection;
        this.c = dVar;
        this.d = bVar;
        this.a = set;
        this.e = cVar;
        this.f = scheduledExecutorService;
    }

    public static /* synthetic */ AbstractC1477j a(a aVar, AbstractC1477j abstractC1477j, AbstractC1477j abstractC1477j2, long j, int i, AbstractC1477j abstractC1477j3) throws JSONException {
        aVar.getClass();
        if (!abstractC1477j.n()) {
            return m.d(new ax.P8.h("Failed to auto-fetch config update.", abstractC1477j.j()));
        }
        if (!abstractC1477j2.n()) {
            return m.d(new ax.P8.h("Failed to get activated config for auto-fetch", abstractC1477j2.j()));
        }
        d.a aVar2 = (d.a) abstractC1477j.k();
        c cVarA = (c) abstractC1477j2.k();
        if (!e(aVar2, j).booleanValue()) {
            Log.d("FirebaseRemoteConfig", "Fetched template version is the same as SDK's current version. Retrying fetch.");
            aVar.b(i, j);
            return m.e(null);
        }
        if (aVar2.d() == null) {
            Log.d("FirebaseRemoteConfig", "The fetch succeeded, but the backend had no updates.");
            return m.e(null);
        }
        if (cVarA == null) {
            cVarA = c.l().a();
        }
        Set<String> setF = cVarA.f(aVar2.d());
        if (setF.isEmpty()) {
            Log.d("FirebaseRemoteConfig", "Config was fetched, but no params changed.");
            return m.e(null);
        }
        aVar.c(ax.P8.b.a(setF));
        return m.e(null);
    }

    private void b(int i, long j) {
        if (i == 0) {
            j(new k("Unable to fetch the latest version of the template.", i.a.CONFIG_UPDATE_NOT_FETCHED));
        } else {
            this.f.schedule(new RunnableC0584a(i, j), this.g.nextInt(4), TimeUnit.SECONDS);
        }
    }

    private synchronized void c(ax.P8.b bVar) {
        Iterator<ax.P8.c> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().b(bVar);
        }
    }

    private static Boolean e(d.a aVar, long j) {
        if (aVar.d() != null) {
            return Boolean.valueOf(aVar.d().k() >= j);
        }
        return Boolean.valueOf(aVar.f() == 1);
    }

    private void f(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        loop0: while (true) {
            String strI = HttpUrl.FRAGMENT_ENCODE_SET;
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break loop0;
                }
                strI = strI + line;
                if (line.contains("}")) {
                    strI = i(strI);
                    if (strI.isEmpty()) {
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject(strI);
                if (jSONObject.has("featureDisabled") && jSONObject.getBoolean("featureDisabled")) {
                    this.e.a(new k("The server is temporarily unavailable. Try again in a few minutes.", i.a.CONFIG_UPDATE_UNAVAILABLE));
                    break;
                } else {
                    if (g()) {
                        break;
                    }
                    if (jSONObject.has("latestTemplateVersionNumber")) {
                        long jR = this.c.r();
                        long j = jSONObject.getLong("latestTemplateVersionNumber");
                        if (j > jR) {
                            b(3, j);
                        }
                    }
                }
            } catch (JSONException e) {
                j(new ax.P8.h("Unable to parse config update message.", e.getCause(), i.a.CONFIG_UPDATE_MESSAGE_INVALID));
                Log.e("FirebaseRemoteConfig", "Unable to parse latest config update message.", e);
            }
        }
        bufferedReader.close();
        inputStream.close();
    }

    private synchronized boolean g() {
        return this.a.isEmpty();
    }

    private String i(String str) {
        int iIndexOf = str.indexOf(123);
        int iLastIndexOf = str.lastIndexOf(125);
        return (iIndexOf < 0 || iLastIndexOf < 0 || iIndexOf >= iLastIndexOf) ? HttpUrl.FRAGMENT_ENCODE_SET : str.substring(iIndexOf, iLastIndexOf + 1);
    }

    private synchronized void j(i iVar) {
        Iterator<ax.P8.c> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(iVar);
        }
    }

    public synchronized AbstractC1477j<Void> d(int i, final long j) throws Throwable {
        final int i2 = i - 1;
        try {
            try {
                final AbstractC1477j<d.a> abstractC1477jN = this.c.n(d.b.REALTIME, 3 - i2);
                final AbstractC1477j<c> abstractC1477jE = this.d.e();
                return m.j(abstractC1477jN, abstractC1477jE).i(this.f, new InterfaceC1469b() { // from class: ax.Q8.a
                    @Override // ax.Q6.InterfaceC1469b
                    public final Object a(AbstractC1477j abstractC1477j) {
                        return com.google.firebase.remoteconfig.internal.a.a(this.a, abstractC1477jN, abstractC1477jE, j, i2, abstractC1477j);
                    }
                });
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void h() {
        HttpURLConnection httpURLConnection = this.b;
        if (httpURLConnection == null) {
            return;
        }
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            f(inputStream);
            inputStream.close();
        } catch (IOException e) {
            Log.d("FirebaseRemoteConfig", "Stream was cancelled due to an exception. Retrying the connection...", e);
        } finally {
            this.b.disconnect();
        }
    }
}
