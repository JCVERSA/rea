package com.android.billingclient.api;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.activity.ComponentActivity;
import ax.I6.EnumC1171m3;
import com.google.android.apps.common.proguard.UsedByReflection;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivityV2 extends ComponentActivity {
    private ax.p162k.c X;
    private ax.p162k.c Y;
    private ResultReceiver Z;
    private ResultReceiver k0;
    private ResultReceiver l0;
    private ax.p162k.c q;

    final void C(ax.p162k.a aVar) {
        Intent intentA = aVar.a();
        int iC = ax.I6.Q.g(intentA, "ProxyBillingActivityV2").c();
        ResultReceiver resultReceiver = this.Z;
        if (resultReceiver != null) {
            resultReceiver.send(iC, intentA == null ? null : intentA.getExtras());
        }
        if (aVar.b() != -1 || iC != 0) {
            ax.I6.Q.l("ProxyBillingActivityV2", "Alternative billing only dialog finished with resultCode " + aVar.b() + " and billing's responseCode: " + iC);
        }
        finish();
    }

    final void D(ax.p162k.a aVar) {
        Intent intentA = aVar.a();
        int iC = ax.I6.Q.g(intentA, "ProxyBillingActivityV2").c();
        ResultReceiver resultReceiver = this.k0;
        if (resultReceiver != null) {
            resultReceiver.send(iC, intentA == null ? null : intentA.getExtras());
        }
        if (aVar.b() != -1 || iC != 0) {
            ax.I6.Q.l("ProxyBillingActivityV2", String.format("External offer dialog finished with resultCode: %s and billing's responseCode: %s", Integer.valueOf(aVar.b()), Integer.valueOf(iC)));
        }
        finish();
    }

    final void E(ax.p162k.a aVar) {
        Intent intentA = aVar.a();
        Bundle extras = intentA == null ? null : intentA.getExtras();
        if (aVar.b() != -1) {
            if (extras == null) {
                extras = new Bundle();
            }
            ax.I6.Q.l("ProxyBillingActivityV2", String.format("External offer flow finished with resultCode: %s", Integer.valueOf(aVar.b())));
            extras.putInt("INTERNAL_LOG_ERROR_REASON", EnumC1171m3.ERROR_IN_ACTIVITY_RESULT.a());
            extras.putString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS", String.format("External offer flow finished with error resultCode: %s", Integer.valueOf(aVar.b())));
        }
        int iC = ax.I6.Q.g(intentA, "ProxyBillingActivityV2").c();
        ResultReceiver resultReceiver = this.l0;
        if (resultReceiver != null) {
            resultReceiver.send(iC, extras);
        } else {
            ax.I6.Q.l("ProxyBillingActivityV2", "External offer flow result receiver is null");
        }
        if (iC != 0) {
            ax.I6.Q.l("ProxyBillingActivityV2", String.format("External offer flow finished with billing responseCode: %s", Integer.valueOf(iC)));
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.q = registerForActivityResult(new ax.p178l.d(), new ax.p162k.b() { // from class: com.android.billingclient.api.L
            @Override // ax.p162k.b
            public final void a(Object obj) {
                this.a.C((ax.p162k.a) obj);
            }
        });
        this.X = registerForActivityResult(new ax.p178l.d(), new ax.p162k.b() { // from class: com.android.billingclient.api.M
            @Override // ax.p162k.b
            public final void a(Object obj) {
                this.a.D((ax.p162k.a) obj);
            }
        });
        this.Y = registerForActivityResult(new ax.p178l.d(), new ax.p162k.b() { // from class: com.android.billingclient.api.N
            @Override // ax.p162k.b
            public final void a(Object obj) {
                this.a.E((ax.p162k.a) obj);
            }
        });
        if (bundle != null) {
            if (bundle.containsKey("alternative_billing_only_dialog_result_receiver")) {
                this.Z = (ResultReceiver) bundle.getParcelable("alternative_billing_only_dialog_result_receiver");
            }
            if (bundle.containsKey("external_payment_dialog_result_receiver")) {
                this.k0 = (ResultReceiver) bundle.getParcelable("external_payment_dialog_result_receiver");
            }
            if (bundle.containsKey("external_offer_flow_result_receiver")) {
                this.l0 = (ResultReceiver) bundle.getParcelable("external_offer_flow_result_receiver");
                return;
            }
            return;
        }
        ax.I6.Q.k("ProxyBillingActivityV2", "Launching Play Store billing dialog");
        if (getIntent().hasExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT")) {
            PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT");
            this.Z = (ResultReceiver) getIntent().getParcelableExtra("alternative_billing_only_dialog_result_receiver");
            this.q.a(new ax.k.g.a(pendingIntent).a());
        } else if (getIntent().hasExtra("external_payment_dialog_pending_intent")) {
            PendingIntent pendingIntent2 = (PendingIntent) getIntent().getParcelableExtra("external_payment_dialog_pending_intent");
            this.k0 = (ResultReceiver) getIntent().getParcelableExtra("external_payment_dialog_result_receiver");
            this.X.a(new ax.k.g.a(pendingIntent2).a());
        } else if (getIntent().hasExtra("external_offer_flow_pending_intent")) {
            PendingIntent pendingIntent3 = (PendingIntent) getIntent().getParcelableExtra("external_offer_flow_pending_intent");
            this.l0 = (ResultReceiver) getIntent().getParcelableExtra("external_offer_flow_result_receiver");
            this.Y.a(new ax.k.g.a(pendingIntent3).a());
        }
    }

    @Override // androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ResultReceiver resultReceiver = this.Z;
        if (resultReceiver != null) {
            bundle.putParcelable("alternative_billing_only_dialog_result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.k0;
        if (resultReceiver2 != null) {
            bundle.putParcelable("external_payment_dialog_result_receiver", resultReceiver2);
        }
        ResultReceiver resultReceiver3 = this.l0;
        if (resultReceiver3 != null) {
            bundle.putParcelable("external_offer_flow_result_receiver", resultReceiver3);
        }
    }
}
