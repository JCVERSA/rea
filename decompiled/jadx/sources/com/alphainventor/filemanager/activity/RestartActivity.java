package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import ax.p210n.ActivityC2164c;
import com.alphainventor.filemanager.activity.RestartActivity;

/* JADX INFO: loaded from: classes.dex */
public class RestartActivity extends ActivityC2164c {
    public static /* synthetic */ void H(RestartActivity restartActivity, int i) {
        Intent launchIntentForPackage = restartActivity.getPackageManager().getLaunchIntentForPackage(restartActivity.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268468224);
            restartActivity.startActivity(launchIntentForPackage);
        }
        restartActivity.finish();
        Process.killProcess(i);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int intExtra = getIntent().getIntExtra("pid", -1);
        final int iMyPid = Process.myPid();
        if (intExtra > 0 && intExtra != iMyPid) {
            Process.killProcess(intExtra);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: ax.V2.g
            @Override // java.lang.Runnable
            public final void run() {
                RestartActivity.H(this.q, iMyPid);
            }
        }, 150L);
    }
}
