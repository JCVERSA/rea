package com.alphainventor.filemanager.activity;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;
import ax.E1.I;
import ax.J1.C0734o;
import ax.p182n.ActivityC5571c;
import ax.p338y1.d;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class UsbAttachActivity extends ActivityC5571c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (I.t(this) || SystemClock.elapsedRealtime() < 300000) {
            finish();
            return;
        }
        try {
            C0734o.Y(this);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.no_application, 1).show();
            d.b(e);
        }
        finish();
    }
}
