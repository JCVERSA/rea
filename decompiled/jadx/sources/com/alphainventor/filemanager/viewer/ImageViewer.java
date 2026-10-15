package com.alphainventor.filemanager.viewer;

import android.os.Bundle;
import android.widget.Toast;
import ax.p210n.ActivityC2164c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class ImageViewer extends ActivityC2164c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ax.p302sb.c.h().g().b("IMAGE VIEWER ACTIVITY LAUNCHED").h("data : " + getIntent().getData()).i();
        Toast.makeText(this, R.string.error, 1).show();
        finish();
    }
}
