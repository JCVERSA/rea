package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.y4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6438y4 extends AbstractC6397s {
    private final /* synthetic */ C6352k4 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C6438y4(C6352k4 c6352k4, InterfaceC6323g3 interfaceC6323g3) {
        super(interfaceC6323g3);
        this.e = c6352k4;
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6397s
    public final void d() {
        this.e.j().L().a("Tasks have been queued for a long time");
    }
}
