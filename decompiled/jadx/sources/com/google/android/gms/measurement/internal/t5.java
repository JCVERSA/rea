package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
final class t5 implements D5 {
    final /* synthetic */ q5 a;

    t5(q5 q5Var) {
        this.a = q5Var;
    }

    @Override // com.google.android.gms.measurement.internal.D5
    public final void a(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.a.l().D(new w5(this, str, str2, bundle));
        } else if (this.a.l != null) {
            this.a.l.j().G().b("AppId not known when logging event", str2);
        }
    }
}
