package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import ax.J1.C0734o;
import ax.p182n.ActivityC5571c;

/* JADX INFO: loaded from: classes.dex */
public class LaunchActivity extends ActivityC5571c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.example.android.uamp.open_ui".equals(action) || "android.intent.action.VIEW".equals(action) || "android.intent.action.VIEW_DOWNLOADS".equals(action)) {
            Intent intentZ = C0734o.z(this, action, intent.getData());
            intentZ.putExtras(intent);
            startActivity(intentZ);
        }
        finish();
    }
}
