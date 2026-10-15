package com.google.android.gms.ads.internal.util;

import android.content.Context;
import androidx.work.a;
import ax.F2.i;
import ax.F2.j;
import ax.F2.r;
import ax.T5.T;
import ax.U5.p;
import ax.p377x6.b;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.ads.internal.offline.buffering.OfflineNotificationPoster;
import com.google.android.gms.ads.internal.offline.buffering.OfflinePingSender;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class WorkManagerUtil extends T {
    @UsedByReflection("This class must be instantiated reflectively so that the default class loader can be used.")
    public WorkManagerUtil() {
    }

    private static void W7(Context context) {
        try {
            r.e(context.getApplicationContext(), new a.b().a());
        } catch (IllegalStateException unused) {
        }
    }

    @Override // ax.T5.U
    public final void zze(ax.p377x6.a aVar) {
        Context context = (Context) b.e1(aVar);
        W7(context);
        try {
            r rVarD = r.d(context);
            rVarD.a("offline_ping_sender_work");
            rVarD.b(new j.a(OfflinePingSender.class).i(new ax.F2.b.a().b(i.CONNECTED).a()).a("offline_ping_sender_work").b());
        } catch (IllegalStateException e) {
            p.h("Failed to instantiate WorkManager.", e);
        }
    }

    @Override // ax.T5.U
    public final boolean zzf(ax.p377x6.a aVar, String str, String str2) {
        return zzg(aVar, new ax.R5.a(str, str2, HttpUrl.FRAGMENT_ENCODE_SET));
    }

    @Override // ax.T5.U
    public final boolean zzg(ax.p377x6.a aVar, ax.R5.a aVar2) throws Throwable {
        Context context = (Context) b.e1(aVar);
        W7(context);
        ax.F2.b bVarA = new ax.F2.b.a().b(i.CONNECTED).a();
        try {
            r.d(context).b(new j.a(OfflineNotificationPoster.class).i(bVarA).k(new androidx.work.b.a().e("uri", aVar2.q).e("gws_query_id", aVar2.X).e("image_url", aVar2.Y).a()).a("offline_notification_work").b());
            return true;
        } catch (IllegalStateException e) {
            p.h("Failed to instantiate WorkManager.", e);
            return false;
        }
    }
}
