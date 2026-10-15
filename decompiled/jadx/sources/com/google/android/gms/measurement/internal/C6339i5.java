package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.i5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6339i5 extends AbstractC6397s {
    private final /* synthetic */ C6346j5 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C6339i5(C6346j5 c6346j5, InterfaceC6323g3 interfaceC6323g3) {
        super(interfaceC6323g3);
        this.e = c6346j5;
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6397s
    public final void d() {
        this.e.z();
        this.e.j().K().a("Starting upload from DelayedRunnable");
        this.e.b.z0();
    }
}
