package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import ax.H6.InterfaceC0909d0;
import ax.H6.O6;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.p2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6383p2 implements Runnable {
    private final /* synthetic */ ServiceConnection X;
    private final /* synthetic */ ServiceConnectionC6371n2 Y;
    private final /* synthetic */ InterfaceC0909d0 q;

    RunnableC6383p2(ServiceConnectionC6371n2 serviceConnectionC6371n2, InterfaceC0909d0 interfaceC0909d0, ServiceConnection serviceConnection) {
        this.q = interfaceC0909d0;
        this.X = serviceConnection;
        this.Y = serviceConnectionC6371n2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ServiceConnectionC6371n2 serviceConnectionC6371n2 = this.Y;
        C6377o2 c6377o2 = serviceConnectionC6371n2.X;
        String str = serviceConnectionC6371n2.q;
        InterfaceC0909d0 interfaceC0909d0 = this.q;
        ServiceConnection serviceConnection = this.X;
        Bundle bundleA = c6377o2.a(str, interfaceC0909d0);
        c6377o2.a.l().n();
        c6377o2.a.Q();
        if (bundleA != null) {
            long j = bundleA.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j == 0) {
                c6377o2.a.j().L().a("Service response is missing Install Referrer install timestamp");
            } else {
                String string = bundleA.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    c6377o2.a.j().G().a("No referrer defined in Install Referrer response");
                } else {
                    c6377o2.a.j().K().b("InstallReferrer API result", string);
                    boolean z = O6.a() && c6377o2.a.z().t(F.N0);
                    Bundle bundleC = c6377o2.a.L().C(Uri.parse("?" + string), z);
                    if (bundleC == null) {
                        c6377o2.a.j().G().a("No campaign params defined in Install Referrer result");
                    } else {
                        if (z) {
                            long j2 = bundleA.getLong("referrer_click_timestamp_server_seconds", 0L) * 1000;
                            if (j2 > 0) {
                                bundleC.putLong("click_timestamp", j2);
                            }
                        } else {
                            String string2 = bundleC.getString("medium");
                            if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                                long j3 = bundleA.getLong("referrer_click_timestamp_seconds", 0L) * 1000;
                                if (j3 == 0) {
                                    c6377o2.a.j().G().a("Install Referrer is missing click timestamp for ad campaign");
                                } else {
                                    bundleC.putLong("click_timestamp", j3);
                                }
                            }
                        }
                        if (j == c6377o2.a.F().h.a()) {
                            c6377o2.a.j().K().a("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (c6377o2.a.p()) {
                            c6377o2.a.F().h.b(j);
                            c6377o2.a.j().K().b("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            bundleC.putString("_cis", "referrer API v2");
                            c6377o2.a.H().h0("auto", "_cmp", bundleC, str);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ax.p329u6.a.b().c(c6377o2.a.a(), serviceConnection);
        }
    }
}
