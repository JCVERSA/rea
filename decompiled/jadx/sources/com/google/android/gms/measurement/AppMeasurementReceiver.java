package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import ax.M6.k;

/* JADX INFO: loaded from: classes3.dex */
public final class AppMeasurementReceiver extends ax.E0.a implements k.a {
    private k Y;

    @Override // ax.M6.k.a
    public final void a(Context context, Intent intent) {
        ax.E0.a.c(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (this.Y == null) {
            this.Y = new k(this);
        }
        this.Y.a(context, intent);
    }
}
