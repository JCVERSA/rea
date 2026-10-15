package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.LinearLayout;
import ax.I5.q;
import ax.I5.r;
import ax.Q5.C1462y;
import ax.Q5.Q0;
import ax.p377x6.b;
import ax.p409z6.BinderC3364Zl;

/* JADX INFO: loaded from: classes3.dex */
public final class OutOfContextTestingActivity extends Activity {
    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Q0 q0F = C1462y.a().f(this, new BinderC3364Zl());
        if (q0F == null) {
            finish();
            return;
        }
        setContentView(r.a);
        LinearLayout linearLayout = (LinearLayout) findViewById(q.a);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("adUnit");
        if (stringExtra == null) {
            finish();
            return;
        }
        try {
            q0F.Q5(stringExtra, b.N2(this), b.N2(linearLayout));
        } catch (RemoteException unused) {
            finish();
        }
    }
}
