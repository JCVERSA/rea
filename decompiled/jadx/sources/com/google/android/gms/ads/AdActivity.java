package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import ax.Q5.C1462y;
import ax.U5.p;
import ax.p377x6.b;
import ax.p409z6.InterfaceC3156Tn;

/* JADX INFO: loaded from: classes3.dex */
public final class AdActivity extends Activity {
    private InterfaceC3156Tn q;

    private final void a() {
        InterfaceC3156Tn interfaceC3156Tn = this.q;
        if (interfaceC3156Tn != null) {
            try {
                interfaceC3156Tn.C();
            } catch (RemoteException e) {
                p.i("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // android.app.Activity
    protected final void onActivityResult(int i, int i2, Intent intent) {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.W2(i, i2, intent);
            }
        } catch (Exception e) {
            p.i("#007 Could not call remote method.", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null && !interfaceC3156Tn.Y()) {
                return;
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
        }
        super.onBackPressed();
        try {
            InterfaceC3156Tn interfaceC3156Tn2 = this.q;
            if (interfaceC3156Tn2 != null) {
                interfaceC3156Tn2.i();
            }
        } catch (RemoteException e2) {
            p.i("#007 Could not call remote method.", e2);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.q0(b.N2(configuration));
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        InterfaceC3156Tn interfaceC3156TnL = C1462y.a().l(this);
        this.q = interfaceC3156TnL;
        if (interfaceC3156TnL == null) {
            p.i("#007 Could not call remote method.", null);
            finish();
            return;
        }
        try {
            interfaceC3156TnL.O4(bundle);
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.m();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected final void onPause() {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.p();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.G3(i, strArr, iArr);
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    protected final void onRestart() {
        super.onRestart();
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.s();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.t();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.N0(bundle);
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected final void onStart() {
        super.onStart();
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.w();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onStop() {
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.A();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    protected final void onUserLeaveHint() {
        super.onUserLeaveHint();
        try {
            InterfaceC3156Tn interfaceC3156Tn = this.q;
            if (interfaceC3156Tn != null) {
                interfaceC3156Tn.v();
            }
        } catch (RemoteException e) {
            p.i("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i) {
        super.setContentView(i);
        a();
    }

    @Override // android.app.Activity
    public final void setContentView(View view) {
        super.setContentView(view);
        a();
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        a();
    }
}
