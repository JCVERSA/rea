package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p217n6.C2172c;
import ax.p265q6.AbstractC2241c;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
public final class J4 implements ServiceConnection, AbstractC2241c.a, AbstractC2241c.b {
    private volatile T1 X;
    final /* synthetic */ C6352k4 Y;
    private volatile boolean q;

    protected J4(C6352k4 c6352k4) {
        this.Y = c6352k4;
    }

    @Override // ax.p265q6.AbstractC2241c.a
    public final void M0(int i) {
        C2254p.e("MeasurementServiceConnection.onConnectionSuspended");
        this.Y.j().F().a("Service connection suspended");
        this.Y.l().D(new O4(this));
    }

    @Override // ax.p265q6.AbstractC2241c.b
    public final void P0(C2172c c2172c) {
        C2254p.e("MeasurementServiceConnection.onConnectionFailed");
        V1 v1E = this.Y.a.E();
        if (v1E != null) {
            v1E.L().b("Service connection failed", c2172c);
        }
        synchronized (this) {
            this.q = false;
            this.X = null;
        }
        this.Y.l().D(new M4(this));
    }

    public final void a() {
        this.Y.n();
        Context contextA = this.Y.a();
        synchronized (this) {
            try {
                if (this.q) {
                    this.Y.j().K().a("Connection attempt already in progress");
                    return;
                }
                if (this.X != null && (this.X.f() || this.X.a())) {
                    this.Y.j().K().a("Already awaiting connection attempt");
                    return;
                }
                this.X = new T1(contextA, Looper.getMainLooper(), this, this);
                this.Y.j().K().a("Connecting to remote service");
                this.q = true;
                C2254p.l(this.X);
                this.X.q();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(Intent intent) {
        this.Y.n();
        Context contextA = this.Y.a();
        ax.p329u6.a aVarB = ax.p329u6.a.b();
        synchronized (this) {
            try {
                if (this.q) {
                    this.Y.j().K().a("Connection attempt already in progress");
                    return;
                }
                this.Y.j().K().a("Using local app measurement service");
                this.q = true;
                aVarB.a(contextA, intent, this.Y.c, 129);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d() {
        if (this.X != null && (this.X.a() || this.X.f())) {
            this.X.b();
        }
        this.X = null;
    }

    @Override // ax.p265q6.AbstractC2241c.a
    public final void e1(Bundle bundle) {
        C2254p.e("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                C2254p.l(this.X);
                this.Y.l().D(new K4(this, this.X.D()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.X = null;
                this.q = false;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C2254p.e("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.q = false;
                this.Y.j().G().a("Service connected with null binder");
                return;
            }
            InterfaceC1351e o1 = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    o1 = iInterfaceQueryLocalInterface instanceof InterfaceC1351e ? (InterfaceC1351e) iInterfaceQueryLocalInterface : new O1(iBinder);
                    this.Y.j().K().a("Bound to IMeasurementService interface");
                } else {
                    this.Y.j().G().b("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException unused) {
                this.Y.j().G().a("Service connect failed to get IMeasurementService");
            }
            if (o1 == null) {
                this.q = false;
                try {
                    ax.p329u6.a.b().c(this.Y.a(), this.Y.c);
                } catch (IllegalArgumentException unused2) {
                }
            } else {
                this.Y.l().D(new I4(this, o1));
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        C2254p.e("MeasurementServiceConnection.onServiceDisconnected");
        this.Y.j().F().a("Service disconnected");
        this.Y.l().D(new L4(this, componentName));
    }
}
