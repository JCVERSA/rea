package com.alphainventor.filemanager.activity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ax.p210n.ActivityC2164c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class WallpaperActivity extends ActivityC2164c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            if (wallpaperManager == null || intent.getData() == null) {
                Toast.makeText(this, R.string.error, 1).show();
            } else {
                startActivity(wallpaperManager.getCropAndSetWallpaperIntent(intent.getData()));
            }
        } catch (Exception unused) {
            Toast.makeText(this, R.string.error, 1).show();
        }
        finish();
    }
}
