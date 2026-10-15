package com.alphainventor.filemanager.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.t;
import ax.K1.C0768s;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class DefaultsSettingsActivity extends ActivityC5571c {
    private Toolbar E0;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DefaultsSettingsActivity.this.finish();
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.E0 = toolbar;
        S0(toolbar);
        I0().E(R.string.settings_default_apps);
        I0().u(true);
        this.E0.setNavigationOnClickListener(new a());
        if (w().h0(R.id.fragment_container) == null) {
            t tVarO = w().o();
            tVarO.r(R.id.fragment_container, new C0768s());
            tVarO.i();
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        ax.p338y1.a.k().s(getClass().getSimpleName());
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
