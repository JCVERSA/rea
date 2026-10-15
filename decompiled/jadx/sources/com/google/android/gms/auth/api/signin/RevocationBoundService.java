package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import ax.p185l6.v;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class RevocationBoundService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (!"com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(intent.getAction()) && !"com.google.android.gms.auth.api.signin.RevocationBoundService.clearClientState".equals(intent.getAction())) {
            Log.w("RevocationService", "Unknown action sent to RevocationBoundService: ".concat(String.valueOf(intent.getAction())));
            return null;
        }
        Log.isLoggable("RevocationService", 2);
        if (0 != 0) {
            Log.v("RevocationService", "RevocationBoundService handling ".concat(String.valueOf(intent.getAction())));
        }
        return new v(this);
    }
}
