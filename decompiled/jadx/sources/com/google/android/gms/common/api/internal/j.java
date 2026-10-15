package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes3.dex */
final class j implements ComponentCallbacks2C6270a.InterfaceC0566a {
    final /* synthetic */ C6272c a;

    j(C6272c c6272c) {
        this.a = c6272c;
    }

    @Override // com.google.android.gms.common.api.internal.ComponentCallbacks2C6270a.InterfaceC0566a
    public final void a(boolean z) {
        C6272c c6272c = this.a;
        c6272c.t0.sendMessage(c6272c.t0.obtainMessage(1, Boolean.valueOf(z)));
    }
}
