package com.alphainventor.filemanager.viewer;

import android.os.Bundle;
import android.widget.Toast;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class ImageViewer extends ActivityC5571c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ax.p221pa.c.h().g().b("IMAGE VIEWER ACTIVITY LAUNCHED").h("data : " + getIntent().getData()).i();
        Toast.makeText(this, R.string.error, 1).show();
        finish();
    }
}
