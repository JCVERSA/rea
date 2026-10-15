package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import ax.K0.c;
import ax.p185l6.g;
import ax.p185l6.x;

/* JADX INFO: loaded from: classes3.dex */
final class a implements ax.J0.a.InterfaceC0116a {
    final /* synthetic */ SignInHubActivity q;

    /* synthetic */ a(SignInHubActivity signInHubActivity, x xVar) {
        this.q = signInHubActivity;
    }

    @Override // ax.J0.a.InterfaceC0116a
    public final /* bridge */ /* synthetic */ void D(c cVar, Object obj) {
        SignInHubActivity signInHubActivity = this.q;
        signInHubActivity.setResult(signInHubActivity.Z, signInHubActivity.k0);
        this.q.finish();
    }

    @Override // ax.J0.a.InterfaceC0116a
    public final c E(int i, Bundle bundle) {
        return new g(this.q, ax.p233o6.g.b());
    }

    @Override // ax.J0.a.InterfaceC0116a
    public final void y(c cVar) {
    }
}
