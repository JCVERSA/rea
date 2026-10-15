package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Keep;
import ax.p249p6.C2207e;
import ax.p249p6.H;
import ax.p249p6.InterfaceC2208f;
import ax.p249p6.J;
import ax.p265q6.C2254p;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public class LifecycleCallback {
    protected final InterfaceC2208f q;

    protected LifecycleCallback(InterfaceC2208f interfaceC2208f) {
        this.q = interfaceC2208f;
    }

    public static InterfaceC2208f c(Activity activity) {
        return d(new C2207e(activity));
    }

    protected static InterfaceC2208f d(C2207e c2207e) {
        if (c2207e.d()) {
            return J.W2(c2207e.b());
        }
        if (c2207e.c()) {
            return H.c(c2207e.a());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }

    @Keep
    private static InterfaceC2208f getChimeraLifecycleFragmentImpl(C2207e c2207e) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity b() {
        Activity activityH = this.q.H();
        C2254p.l(activityH);
        return activityH;
    }

    public void e(int i, int i2, Intent intent) {
    }

    public void f(Bundle bundle) {
    }

    public void g() {
    }

    public void h() {
    }

    public void i(Bundle bundle) {
    }

    public void j() {
    }

    public void k() {
    }
}
