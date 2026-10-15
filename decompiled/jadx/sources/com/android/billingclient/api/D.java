package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import ax.I6.AbstractBinderC1147i;
import j$.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class D implements ServiceConnection {
    final /* synthetic */ E q;

    /* synthetic */ D(E e, ax.D3.k kVar) {
        Objects.requireNonNull(e);
        this.q = e;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ax.I6.Q.k("BillingClientTesting", "Billing Override Service connected.");
        E e = this.q;
        e.M = AbstractBinderC1147i.P0(iBinder);
        e.L = 2;
        e.G0(26);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        ax.I6.Q.l("BillingClientTesting", "Billing Override Service disconnected.");
        E e = this.q;
        e.M = null;
        e.L = 0;
    }
}
