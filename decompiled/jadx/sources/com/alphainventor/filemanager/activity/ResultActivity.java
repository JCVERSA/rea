package com.alphainventor.filemanager.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import ax.Z2.h;
import ax.p054d3.C1906k;
import ax.p210n.ActivityC2164c;
import ax.p374x3.w;
import ax.p406z3.B;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class ResultActivity extends ActivityC2164c implements DialogInterface.OnDismissListener {
    public static String X = "MESSAGE";
    public static String Y = "SUB_MESSAGE";
    public static String Z = "RESULT_INFO";
    public static String q = "COMMAND_RESULT";

    private void H(Intent intent) {
        if (!"com.filemanager.BRING_TO_FRONT".equals(intent.getAction()) && ((h.b) intent.getSerializableExtra(q)) == h.b.FAILURE) {
            B.d0(getSupportFragmentManager(), C1906k.s3(R.string.dialog_title_failure, intent.getStringExtra(X), intent.getStringExtra(Y), intent.getStringArrayListExtra(Z)), "result", false);
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (getIntent() == null) {
            finish();
        } else if (((h.b) intent.getSerializableExtra(q)) != h.b.FAILURE) {
            finish();
        } else {
            H(intent);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        H(intent);
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        w.j(this).a(104);
    }
}
