package com.alphainventor.filemanager.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class DevSettingsActivity extends ax.V2.c {
    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        return false;
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings_fragment);
        onSetContentView(true);
        setTitle(R.string.debug);
        FragmentManager fragmentManager = getFragmentManager();
        ax.p390y3.b bVar = new ax.p390y3.b();
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.fragment_container, bVar);
        fragmentTransactionBeginTransaction.commit();
    }
}
