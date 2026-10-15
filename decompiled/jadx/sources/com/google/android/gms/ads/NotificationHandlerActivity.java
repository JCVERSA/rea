package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import ax.Q5.C1462y;
import ax.U5.p;
import ax.p409z6.BinderC3364Zl;
import ax.p409z6.InterfaceC2877Ln;

/* JADX INFO: loaded from: classes3.dex */
public final class NotificationHandlerActivity extends Activity {
    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            InterfaceC2877Ln interfaceC2877LnJ = C1462y.a().j(this, new BinderC3364Zl());
            if (interfaceC2877LnJ == null) {
                p.d("OfflineUtils is null");
            } else {
                interfaceC2877LnJ.f1(getIntent());
            }
        } catch (RemoteException e) {
            p.d("RemoteException calling handleNotificationIntent: ".concat(e.toString()));
        }
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        finish();
    }
}
