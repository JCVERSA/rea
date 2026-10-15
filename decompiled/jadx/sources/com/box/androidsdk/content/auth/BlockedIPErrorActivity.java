package com.box.androidsdk.content.auth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import ax.O3.b;
import ax.O3.c;

/* JADX INFO: loaded from: classes.dex */
public class BlockedIPErrorActivity extends Activity {

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BlockedIPErrorActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(c.a);
        findViewById(b.n).setOnClickListener(new a());
    }
}
