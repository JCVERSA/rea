package com.alphainventor.filemanager.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class DevSettingsActivity extends ActivityC5571c {
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.debug);
        FragmentManager fragmentManager = getFragmentManager();
        ax.p031c2.b bVar = new ax.p031c2.b();
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(android.R.id.content, bVar);
        fragmentTransactionBeginTransaction.commit();
    }
}
