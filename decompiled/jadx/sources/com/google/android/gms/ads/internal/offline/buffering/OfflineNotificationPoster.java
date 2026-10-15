package com.google.android.gms.ads.internal.offline.buffering;

import android.content.Context;
import android.os.RemoteException;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.c;
import ax.Q5.C1462y;
import ax.R5.a;
import ax.p377x6.b;
import ax.p409z6.BinderC3364Zl;
import ax.p409z6.InterfaceC2877Ln;

/* JADX INFO: loaded from: classes3.dex */
public class OfflineNotificationPoster extends Worker {
    private final InterfaceC2877Ln l0;

    public OfflineNotificationPoster(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.l0 = C1462y.a().j(context, new BinderC3364Zl());
    }

    @Override // androidx.work.Worker
    public final c.a doWork() {
        try {
            this.l0.T2(b.N2(getApplicationContext()), new a(getInputData().i("uri"), getInputData().i("gws_query_id"), getInputData().i("image_url")));
            return c.a.c();
        } catch (RemoteException unused) {
            return c.a.a();
        }
    }
}
