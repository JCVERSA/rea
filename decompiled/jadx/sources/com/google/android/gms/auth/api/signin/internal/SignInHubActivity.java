package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.fragment.app.f;
import ax.p185l6.p;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes3.dex */
@KeepName
public class SignInHubActivity extends f {
    private static boolean l0;
    private SignInConfiguration X;
    private boolean Y;
    private int Z;
    private Intent k0;
    private boolean q = false;

    private final void K() {
        getSupportLoaderManager().e(0, null, new a(this, null));
        l0 = false;
    }

    private final void L(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        l0 = false;
    }

    private final void M(String str) {
        Intent intent = new Intent(str);
        if (str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
            intent.setPackage("com.google.android.gms");
        } else {
            intent.setPackage(getPackageName());
        }
        intent.putExtra("config", this.X);
        try {
            startActivityForResult(intent, 40962);
        } catch (ActivityNotFoundException unused) {
            this.q = true;
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            L(17);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected final void onActivityResult(int i, int i2, Intent intent) {
        if (this.q) {
            return;
        }
        setResult(0);
        if (i != 40962) {
            return;
        }
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra("signInAccount");
            if (signInAccount != null && signInAccount.j() != null) {
                GoogleSignInAccount googleSignInAccountJ = signInAccount.j();
                if (googleSignInAccountJ == null) {
                    Log.e("AuthSignInClient", "Google account is null");
                    L(12500);
                    return;
                }
                p.a(this).c(this.X.j(), googleSignInAccountJ);
                intent.removeExtra("signInAccount");
                intent.putExtra("googleSignInAccount", googleSignInAccountJ);
                this.Y = true;
                this.Z = i2;
                this.k0 = intent;
                K();
                return;
            }
            if (intent.hasExtra("errorCode")) {
                int intExtra = intent.getIntExtra("errorCode", 8);
                if (intExtra == 13) {
                    intExtra = 12501;
                }
                L(intExtra);
                return;
            }
        }
        L(8);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action == null) {
            Log.e("AuthSignInClient", "Null action");
            L(12500);
            return;
        }
        if (action.equals("com.google.android.gms.auth.NO_IMPL")) {
            Log.e("AuthSignInClient", "Action not implemented");
            L(12500);
            return;
        }
        if (!action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") && !action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            Log.e("AuthSignInClient", "Unknown action: ".concat(String.valueOf(intent.getAction())));
            finish();
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("config");
        if (bundleExtra == null) {
            Log.e("AuthSignInClient", "Activity started with no configuration.");
            setResult(0);
            finish();
            return;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) bundleExtra.getParcelable("config");
        if (signInConfiguration == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
            return;
        }
        this.X = signInConfiguration;
        if (bundle == null) {
            if (l0) {
                setResult(0);
                L(12502);
                return;
            } else {
                l0 = true;
                M(action);
                return;
            }
        }
        boolean z = bundle.getBoolean("signingInGoogleApiClients");
        this.Y = z;
        if (z) {
            this.Z = bundle.getInt("signInResultCode");
            Intent intent2 = (Intent) bundle.getParcelable("signInResultData");
            if (intent2 != null) {
                this.k0 = intent2;
                K();
            } else {
                Log.e("AuthSignInClient", "Sign in result data cannot be null");
                setResult(0);
                finish();
            }
        }
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        l0 = false;
    }

    @Override // androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.Y);
        if (this.Y) {
            bundle.putInt("signInResultCode", this.Z);
            bundle.putParcelable("signInResultData", this.k0);
        }
    }
}
