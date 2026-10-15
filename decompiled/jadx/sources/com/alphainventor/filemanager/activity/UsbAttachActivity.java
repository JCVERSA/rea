package com.alphainventor.filemanager.activity;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;
import ax.U2.d;
import ax.p006a3.J;
import ax.p086f3.C2014u;
import ax.p210n.ActivityC2164c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class UsbAttachActivity extends ActivityC2164c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (J.v(this) || SystemClock.elapsedRealtime() < 300000) {
            finish();
            return;
        }
        try {
            C2014u.e0(this);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.no_application, 1).show();
            d.b(e);
        }
        finish();
    }
}
