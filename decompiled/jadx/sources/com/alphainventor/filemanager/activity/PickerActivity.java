package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ax.J1.C0734o;
import ax.p182n.ActivityC5571c;
import ax.p338y1.d;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class PickerActivity extends ActivityC5571c {
    private Intent W0(Intent intent, String str) {
        Intent intent2 = new Intent(intent);
        if ("com.discord".equals(str) && "android.intent.action.PICK".equals(intent.getAction()) && "image/*".equals(intent.getType())) {
            intent2.setType("*/*");
        }
        return intent2;
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String callingPackage = getCallingPackage();
        String action = intent.getAction();
        Intent intentW0 = W0(intent, callingPackage);
        if ("android.intent.action.GET_CONTENT".equals(action)) {
            try {
                C0734o.V(this, intentW0, callingPackage);
            } catch (SecurityException e) {
                d.c("picker security", e);
                Toast.makeText(this, R.string.error, 1).show();
            }
        } else if ("android.intent.action.OPEN_DOCUMENT".equals(action) || "android.intent.action.PICK".equals(action)) {
            C0734o.V(this, intentW0, callingPackage);
        } else {
            "android.intent.action.RINGTONE_PICKER".equals(intent.getAction());
        }
        finish();
    }
}
