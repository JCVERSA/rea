package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import ax.H6.InterfaceC0909d0;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.o2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6377o2 {
    final E2 a;

    C6377o2(q5 q5Var) {
        this.a = q5Var.o0();
    }

    final Bundle a(String str, InterfaceC0909d0 interfaceC0909d0) {
        this.a.l().n();
        if (interfaceC0909d0 == null) {
            this.a.j().L().a("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            Bundle bundleL0 = interfaceC0909d0.l0(bundle);
            if (bundleL0 != null) {
                return bundleL0;
            }
            this.a.j().G().a("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e) {
            this.a.j().G().b("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }

    final boolean b() {
        try {
            ax.p361w6.d dVarA = ax.p361w6.e.a(this.a.a());
            if (dVarA != null) {
                return dVarA.f("com.android.vending", 128).versionCode >= 80837300;
            }
            this.a.j().K().a("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e) {
            this.a.j().K().b("Failed to retrieve Play Store version for Install Referrer", e);
            return false;
        }
    }
}
