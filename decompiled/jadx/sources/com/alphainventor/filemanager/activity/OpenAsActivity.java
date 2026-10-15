package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import ax.p182n.ActivityC5571c;

/* JADX INFO: loaded from: classes.dex */
public class OpenAsActivity extends ActivityC5571c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent();
        intent.putExtra("SHOW_OPEN_AS", true);
        setResult(-1, intent);
        finish();
    }
}
