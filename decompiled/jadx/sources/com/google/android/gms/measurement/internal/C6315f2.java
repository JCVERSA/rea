package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.f2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
class C6315f2 extends BroadcastReceiver {
    private final q5 a;
    private boolean b;
    private boolean c;

    C6315f2(q5 q5Var) {
        C2254p.l(q5Var);
        this.a = q5Var;
    }

    public final void b() {
        this.a.v0();
        this.a.l().n();
        if (this.b) {
            return;
        }
        this.a.a().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.c = this.a.l0().A();
        this.a.j().K().b("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.c));
        this.b = true;
    }

    public final void c() {
        this.a.v0();
        this.a.l().n();
        this.a.l().n();
        if (this.b) {
            this.a.j().K().a("Unregistering connectivity change receiver");
            this.b = false;
            this.c = false;
            try {
                this.a.a().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.a.j().G().b("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.v0();
        String action = intent.getAction();
        this.a.j().K().b("NetworkBroadcastReceiver received action", action);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            this.a.j().L().b("NetworkBroadcastReceiver received unknown action", action);
            return;
        }
        boolean zA = this.a.l0().A();
        if (this.c != zA) {
            this.c = zA;
            this.a.l().D(new RunnableC6308e2(this, zA));
        }
    }
}
