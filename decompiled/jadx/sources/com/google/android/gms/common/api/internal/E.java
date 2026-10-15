package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import ax.p084f1.C1978i;
import ax.p217n6.C2172c;
import ax.p217n6.C2176g;
import ax.p249p6.InterfaceC2208f;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class E extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean X;
    protected final AtomicReference Y;
    private final Handler Z;
    protected final C2176g k0;

    E(InterfaceC2208f interfaceC2208f, C2176g c2176g) {
        super(interfaceC2208f);
        this.Y = new AtomicReference(null);
        this.Z = new ax.E6.j(Looper.getMainLooper());
        this.k0 = c2176g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l(C2172c c2172c, int i) {
        this.Y.set(null);
        m(c2172c, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void o() {
        this.Y.set(null);
        n();
    }

    private static final int p(B b) {
        if (b == null) {
            return -1;
        }
        return b.a();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void e(int i, int i2, Intent intent) {
        B b = (B) this.Y.get();
        if (i != 1) {
            if (i == 2) {
                int iG = this.k0.g(b());
                if (iG == 0) {
                    o();
                    return;
                } else {
                    if (b == null) {
                        return;
                    }
                    if (b.b().j() == 18 && iG == 18) {
                        return;
                    }
                }
            }
        } else if (i2 == -1) {
            o();
            return;
        } else if (i2 == 0) {
            if (b != null) {
                l(new C2172c(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, b.b().toString()), p(b));
                return;
            }
            return;
        }
        if (b != null) {
            l(b.b(), b.a());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void f(Bundle bundle) {
        super.f(bundle);
        if (bundle != null) {
            this.Y.set(bundle.getBoolean("resolving_error", false) ? new B(new C2172c(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void i(Bundle bundle) {
        super.i(bundle);
        B b = (B) this.Y.get();
        if (b == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", b.a());
        bundle.putInt("failed_status", b.b().j());
        bundle.putParcelable("failed_resolution", b.b().z());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void j() {
        super.j();
        this.X = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void k() {
        super.k();
        this.X = false;
    }

    protected abstract void m(C2172c c2172c, int i);

    protected abstract void n();

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        l(new C2172c(13, null), p((B) this.Y.get()));
    }

    public final void s(C2172c c2172c, int i) {
        AtomicReference atomicReference;
        B b = new B(c2172c, i);
        do {
            atomicReference = this.Y;
            if (C1978i.a(atomicReference, null, b)) {
                this.Z.post(new D(this, b));
                return;
            }
        } while (atomicReference.get() == null);
    }
}
