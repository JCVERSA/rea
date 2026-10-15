package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ax.U2.d;
import ax.p086f3.C2014u;
import ax.p210n.ActivityC2164c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class PickerActivity extends ActivityC2164c {
    private Intent H(Intent intent, String str) {
        Intent intent2 = new Intent(intent);
        if ("com.discord".equals(str) && "android.intent.action.PICK".equals(intent.getAction()) && "image/*".equals(intent.getType())) {
            intent2.setType("*/*");
        }
        return intent2;
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String callingPackage = getCallingPackage();
        String action = intent.getAction();
        try {
            Intent intentH = H(intent, callingPackage);
            if ("android.intent.action.GET_CONTENT".equals(action)) {
                try {
                    C2014u.b0(this, intentH, callingPackage);
                } catch (SecurityException e) {
                    d.c("picker security", e);
                    Toast.makeText(this, R.string.error, 1).show();
                }
            } else if ("android.intent.action.OPEN_DOCUMENT".equals(action) || "android.intent.action.PICK".equals(action)) {
                C2014u.b0(this, intentH, callingPackage);
            } else if (!"android.intent.action.RINGTONE_PICKER".equals(intent.getAction()) && "android.intent.action.CREATE_SHORTCUT".equals(action)) {
                try {
                    C2014u.b0(this, intentH, callingPackage);
                } catch (SecurityException e2) {
                    d.c("picker security", e2);
                    Toast.makeText(this, R.string.error, 1).show();
                }
            }
        } catch (IllegalArgumentException unused) {
            Toast.makeText(this, R.string.error, 1).show();
        }
        finish();
    }
}
