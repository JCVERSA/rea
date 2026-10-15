package com.alphainventor.filemanager.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import ax.D1.f;
import ax.H1.C0699i;
import ax.p017b2.w;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class ResultActivity extends ActivityC5571c implements DialogInterface.OnDismissListener {
    public static String E0 = "COMMAND_RESULT";
    public static String F0 = "MESSAGE";
    public static String G0 = "SUB_MESSAGE";
    public static String H0 = "RESULT_INFO";

    private void W0(Intent intent) {
        if (!"com.filemanager.BRING_TO_FRONT".equals(intent.getAction()) && ((f.b) intent.getSerializableExtra(E0)) == f.b.FAILURE) {
            x.d0(w(), C0699i.v3(R.string.dialog_title_failure, intent.getStringExtra(F0), intent.getStringExtra(G0), intent.getStringArrayListExtra(H0)), "result", false);
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (getIntent() == null) {
            finish();
        } else if (((f.b) intent.getSerializableExtra(E0)) != f.b.FAILURE) {
            finish();
        } else {
            W0(intent);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        W0(intent);
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        w.j(this).a(104);
    }
}
