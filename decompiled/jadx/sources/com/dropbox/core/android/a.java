package com.dropbox.core.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import ax.C4.f;
import ax.p335uc.g;
import ax.p335uc.l;
import ax.p343v4.k;
import ax.p343v4.m;
import ax.p343v4.q;
import ax.p343v4.z;
import java.util.Arrays;
import java.util.Collection;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final C0565a a = new C0565a(null);

    /* JADX INFO: renamed from: com.dropbox.core.android.a$a, reason: collision with other inner class name */
    public static final class C0565a {
        private C0565a() {
        }

        public /* synthetic */ C0565a(g gVar) {
            this();
        }

        private final void b(Context context, String str, String str2, String[] strArr, String str3, String str4, z zVar, m mVar, k kVar, Collection<String> collection, q qVar) {
            String str5;
            AuthActivity.b bVar = AuthActivity.Y;
            l.c(str);
            if (bVar.c(context, str, true)) {
                if (strArr != null) {
                    str5 = str2;
                    if (Arrays.asList(Arrays.copyOf(strArr, strArr.length)).contains(str5)) {
                        throw new IllegalArgumentException("desiredUid cannot be present in alreadyAuthedUids");
                    }
                } else {
                    str5 = str2;
                }
                Intent intentF = bVar.f(context, str, str5, strArr, str3, str4, "1", zVar, mVar, kVar, collection != null ? f.e(collection, " ") : null, qVar);
                if (!(context instanceof Activity)) {
                    intentF.addFlags(268435456);
                }
                context.startActivity(intentF);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void f(C0565a c0565a, Context context, String str, m mVar, Collection collection, int i, Object obj) {
            if ((i & 8) != 0) {
                collection = null;
            }
            c0565a.e(context, str, mVar, collection);
        }

        public final ax.A4.a a() {
            Intent intent = AuthActivity.m0;
            if (intent == null) {
                return null;
            }
            String stringExtra = intent.getStringExtra("ACCESS_TOKEN");
            String stringExtra2 = intent.getStringExtra("ACCESS_SECRET");
            String stringExtra3 = intent.getStringExtra("UID");
            if (stringExtra == null || l.a(HttpUrl.FRAGMENT_ENCODE_SET, stringExtra) || stringExtra2 == null || l.a(HttpUrl.FRAGMENT_ENCODE_SET, stringExtra2) || stringExtra3 == null || l.a(HttpUrl.FRAGMENT_ENCODE_SET, stringExtra3)) {
                return null;
            }
            String stringExtra4 = intent.getStringExtra("CONSUMER_KEY");
            String stringExtra5 = intent.getStringExtra("REFRESH_TOKEN");
            long longExtra = intent.getLongExtra("EXPIRES_AT", -1L);
            return new ax.A4.a(stringExtra2, longExtra >= 0 ? Long.valueOf(longExtra) : null, stringExtra5, stringExtra4);
        }

        public final void c(Context context, String str, m mVar) {
            l.f(context, "context");
            f(this, context, str, mVar, null, 8, null);
        }

        public final void d(Context context, String str, m mVar, k kVar, Collection<String> collection) {
            l.f(context, "context");
            if (mVar == null) {
                throw new IllegalArgumentException("Invalid Dbx requestConfig for PKCE flow.");
            }
            b(context, str, null, null, null, null, z.OFFLINE, mVar, kVar, collection, null);
        }

        public final void e(Context context, String str, m mVar, Collection<String> collection) {
            l.f(context, "context");
            d(context, str, mVar, null, collection);
        }
    }

    public static final ax.A4.a a() {
        return a.a();
    }

    public static final void b(Context context, String str, m mVar) {
        a.c(context, str, mVar);
    }
}
