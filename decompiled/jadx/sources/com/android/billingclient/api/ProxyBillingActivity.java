package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import ax.I6.EnumC1171m3;
import ax.I6.EnumC1205t3;
import com.google.android.apps.common.proguard.UsedByReflection;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivity extends Activity {
    private boolean X;
    private boolean Y;
    private int Z;
    private long k0;
    private boolean l0;
    private ResultReceiver q;

    private EnumC1171m3 a(int i) {
        if (i == -1) {
            return EnumC1171m3.NULL_DATA_WITH_OK_RESULT_CODE_IN_PROXY_BILLING_ACTIVITY_RESULT;
        }
        if (i == 0) {
            return EnumC1171m3.NULL_DATA_WITH_CANCELLED_RESULT_CODE_IN_PROXY_BILLING_ACTIVITY_RESULT;
        }
        if (i == 3) {
            return EnumC1171m3.NULL_DATA_WITH_PLAY_CANCELED_RESULT_CODE;
        }
        if (i != 4) {
            return i != 5 ? EnumC1171m3.NULL_DATA_WITH_OTHER_RESULT_CODE_IN_PROXY_BILLING_ACTIVITY_RESULT : EnumC1171m3.NULL_DATA_WITH_ON_CREATE_RUNTIME_EXCEPTION_RESULT_CODE;
        }
        return EnumC1171m3.NULL_DATA_WITH_PLAY_CANCELED_WITHOUT_COMPLETE_ACTION_RESULT_CODE;
    }

    private Intent b(String str) {
        Intent intent = new Intent("com.android.vending.billing.ALTERNATIVE_BILLING");
        intent.setPackage(getApplicationContext().getPackageName());
        intent.putExtra("ALTERNATIVE_BILLING_USER_CHOICE_DATA", str);
        return intent;
    }

    private Intent c(EnumC1171m3 enumC1171m3, long j) {
        Intent intentD = d();
        intentD.putExtra("RESPONSE_CODE", 6);
        intentD.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
        C6254d.a aVarD = C6254d.d();
        aVarD.d(6);
        aVarD.b("An internal error occurred.");
        C6254d c6254dA = aVarD.a();
        int i = F.a;
        intentD.putExtra("FAILURE_LOGGING_PAYLOAD", F.b(enumC1171m3, 2, c6254dA, null, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED).a());
        intentD.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
        intentD.putExtra("billingClientTransactionId", j);
        intentD.putExtra("wasServiceAutoReconnected", this.l0);
        return intentD;
    }

    private Intent d() {
        Intent intent = new Intent("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intent.setPackage(getApplicationContext().getPackageName());
        return intent;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Intent intentC;
        super.onActivityResult(i, i2, intent);
        if (i == 100 || i == 110) {
            int iC = ax.I6.Q.g(intent, "ProxyBillingActivity").c();
            int i3 = -1;
            if (i2 != -1) {
                ax.I6.Q.l("ProxyBillingActivity", "Activity finished with resultCode " + i2 + " and billing's responseCode: " + iC);
                i3 = i2;
            } else if (iC != 0) {
                i2 = -1;
                ax.I6.Q.l("ProxyBillingActivity", "Activity finished with resultCode " + i2 + " and billing's responseCode: " + iC);
                i3 = i2;
            }
            if (intent == null) {
                ax.I6.Q.l("ProxyBillingActivity", "Got null data with resultCode " + i3 + "!");
                intentC = c(a(i3), this.k0);
            } else if (intent.getExtras() != null) {
                String string = intent.getExtras().getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
                if (string != null) {
                    intentC = b(string);
                    intentC.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                } else {
                    intentC = d();
                    intentC.putExtras(intent.getExtras());
                    intentC.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                }
                intentC.putExtra("billingClientTransactionId", this.k0);
                intentC.putExtra("wasServiceAutoReconnected", this.l0);
            } else {
                ax.I6.Q.l("ProxyBillingActivity", "Got null bundle!");
                intentC = c(EnumC1171m3.NULL_BUNDLE_IN_ACTIVITY_RESULT, this.k0);
            }
            if (i == 110) {
                intentC.putExtra("IS_FIRST_PARTY_PURCHASE", true);
            }
            sendBroadcast(intentC);
        } else if (i == 101) {
            int iA = ax.I6.Q.a(intent, "ProxyBillingActivity");
            ResultReceiver resultReceiver = this.q;
            if (resultReceiver != null) {
                resultReceiver.send(iA, intent == null ? null : intent.getExtras());
            }
        } else {
            ax.I6.Q.l("ProxyBillingActivity", "Got onActivityResult with wrong requestCode: " + i + "; skipping...");
        }
        this.X = false;
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        PendingIntent pendingIntent;
        super.onCreate(bundle);
        if (bundle != null) {
            ax.I6.Q.k("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
            this.X = bundle.getBoolean("send_cancelled_broadcast_if_finished", false);
            if (bundle.containsKey("in_app_message_result_receiver")) {
                this.q = (ResultReceiver) bundle.getParcelable("in_app_message_result_receiver");
            }
            this.Y = bundle.getBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false);
            this.Z = bundle.getInt("activity_code", 100);
            if (bundle.containsKey("billingClientTransactionId")) {
                this.k0 = bundle.getLong("billingClientTransactionId");
            }
            if (bundle.containsKey("wasServiceAutoReconnected")) {
                this.l0 = bundle.getBoolean("wasServiceAutoReconnected");
                return;
            }
            return;
        }
        ax.I6.Q.k("ProxyBillingActivity", "Launching Play Store billing flow");
        this.Z = 100;
        if (getIntent().hasExtra("BUY_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("BUY_INTENT");
            if (getIntent().hasExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT") && getIntent().getBooleanExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false)) {
                this.Y = true;
                this.Z = 110;
            }
        } else if (getIntent().hasExtra("IN_APP_MESSAGE_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("IN_APP_MESSAGE_INTENT");
            this.q = (ResultReceiver) getIntent().getParcelableExtra("in_app_message_result_receiver");
            this.Z = 101;
        } else {
            pendingIntent = null;
        }
        if (getIntent().hasExtra("billingClientTransactionId")) {
            this.k0 = getIntent().getLongExtra("billingClientTransactionId", 0L);
        }
        if (getIntent().hasExtra("wasServiceAutoReconnected")) {
            this.l0 = getIntent().getBooleanExtra("wasServiceAutoReconnected", false);
        }
        try {
            this.X = true;
            startIntentSenderForResult(pendingIntent.getIntentSender(), this.Z, new Intent(), 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            ax.I6.Q.m("ProxyBillingActivity", "Got exception while trying to start a purchase flow.", e);
            ResultReceiver resultReceiver = this.q;
            if (resultReceiver != null) {
                resultReceiver.send(0, null);
            } else {
                Intent intentC = c(EnumC1171m3.INTENT_SENDER_EXCEPTION, this.k0);
                if (this.Y) {
                    intentC.putExtra("IS_FIRST_PARTY_PURCHASE", true);
                }
                sendBroadcast(intentC);
            }
            this.X = false;
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing() && this.X) {
            Intent intentD = d();
            intentD.putExtra("RESPONSE_CODE", 1);
            intentD.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
            if (this.Y) {
                intentD.putExtra("IS_FIRST_PARTY_PURCHASE", true);
            }
            int i = this.Z;
            if (i == 110 || i == 100) {
                intentD.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                intentD.putExtra("billingClientTransactionId", this.k0);
            }
            sendBroadcast(intentD);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ResultReceiver resultReceiver = this.q;
        if (resultReceiver != null) {
            bundle.putParcelable("in_app_message_result_receiver", resultReceiver);
        }
        bundle.putBoolean("send_cancelled_broadcast_if_finished", this.X);
        bundle.putBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", this.Y);
        bundle.putInt("activity_code", this.Z);
        bundle.putLong("billingClientTransactionId", this.k0);
        bundle.putBoolean("wasServiceAutoReconnected", this.l0);
    }
}
