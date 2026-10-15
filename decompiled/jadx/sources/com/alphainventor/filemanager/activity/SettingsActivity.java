package com.alphainventor.filemanager.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.t;
import ax.p102g3.s0;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class SettingsActivity extends ax.V2.c {

    class a extends ax.p182l3.c {
        a() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            SettingsActivity.this.getOnBackPressedDispatcher().k();
        }
    }

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
        setTitle(R.string.menu_settings);
        if (getSupportFragmentManager().j0(R.id.fragment_container) == null) {
            t tVarO = getSupportFragmentManager().o();
            tVarO.r(R.id.fragment_container, new s0());
            tVarO.i();
        }
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new a());
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        ax.U2.a.k().s(getClass().getSimpleName());
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
