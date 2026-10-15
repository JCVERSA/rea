package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import ax.H6.AbstractBinderC0900c0;
import ax.H6.InterfaceC0909d0;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.n2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class ServiceConnectionC6371n2 implements ServiceConnection {
    final /* synthetic */ C6377o2 X;
    private final String q;

    ServiceConnectionC6371n2(C6377o2 c6377o2, String str) {
        this.X = c6377o2;
        this.q = str;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.X.a.j().L().a("Install Referrer connection returned with null binder");
            return;
        }
        try {
            InterfaceC0909d0 interfaceC0909d0P0 = AbstractBinderC0900c0.P0(iBinder);
            if (interfaceC0909d0P0 == null) {
                this.X.a.j().L().a("Install Referrer Service implementation was not found");
            } else {
                this.X.a.j().K().a("Install Referrer Service connected");
                this.X.a.l().D(new RunnableC6383p2(this, interfaceC0909d0P0, this));
            }
        } catch (RuntimeException e) {
            this.X.a.j().L().b("Exception occurred while calling Install Referrer API", e);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.X.a.j().K().a("Install Referrer Service disconnected");
    }
}
