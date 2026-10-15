package com.google.android.gms.ads;

import android.app.IntentService;
import android.content.Intent;
import android.os.RemoteException;
import ax.Q5.C1462y;
import ax.U5.p;
import ax.p409z6.BinderC3364Zl;

/* JADX INFO: loaded from: classes3.dex */
public class AdService extends IntentService {
    public AdService() {
        super("AdService");
    }

    @Override // android.app.IntentService
    protected final void onHandleIntent(Intent intent) {
        try {
            C1462y.a().j(this, new BinderC3364Zl()).f1(intent);
        } catch (RemoteException e) {
            p.d("RemoteException calling handleNotificationIntent: ".concat(e.toString()));
        }
    }
}
