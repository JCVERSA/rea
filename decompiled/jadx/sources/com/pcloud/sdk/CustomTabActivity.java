package com.pcloud.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public class CustomTabActivity extends Activity {
    private BroadcastReceiver q;

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CustomTabActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            Intent intent2 = new Intent("CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION");
            intent2.putExtra("AuthorizationActivity.EXTRA_URL", getIntent().getData());
            ax.L0.a.b(this).d(intent2);
            this.q = new a();
            ax.L0.a.b(this).c(this.q, new IntentFilter("CustomTabActivity.DESTROY_ACTION"));
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, (Class<?>) AuthorizationActivity.class);
        intent.setAction("CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION");
        intent.putExtra("AuthorizationActivity.EXTRA_URL", getIntent().getData());
        intent.addFlags(603979776);
        startActivityForResult(intent, 1338);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        ax.L0.a.b(this).f(this.q);
        super.onDestroy();
    }
}
