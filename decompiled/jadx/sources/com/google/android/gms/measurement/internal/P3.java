package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
final class P3 implements D5 {
    private final /* synthetic */ C6351k3 a;

    P3(C6351k3 c6351k3) {
        this.a = c6351k3;
    }

    @Override // com.google.android.gms.measurement.internal.D5
    public final void a(String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            this.a.R0("auto", str2, bundle);
        } else {
            this.a.h0("auto", str2, bundle, str);
        }
    }
}
