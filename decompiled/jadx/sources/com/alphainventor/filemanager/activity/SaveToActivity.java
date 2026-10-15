package com.alphainventor.filemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ax.U2.g;
import ax.Z2.C1719c;
import ax.p006a3.Q;
import ax.p086f3.C2014u;
import ax.p210n.ActivityC2164c;
import ax.p406z3.q;
import com.alphainventor.filemanager.R;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class SaveToActivity extends ActivityC2164c {
    private static final Logger X = g.a(SaveToActivity.class);
    View q;

    private class a extends q<Void, Void, Boolean> {
        Intent h;
        Context i;
        Uri j;

        a(Context context, Intent intent, Uri uri) {
            super(q.e.HIGHER);
            this.i = context;
            this.h = intent;
            this.j = uri;
        }

        @Override // ax.p406z3.q
        protected void r() {
            View view = SaveToActivity.this.q;
            if (view != null) {
                view.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            List<ax.Z2.g> listB = ax.Z2.g.b(SaveToActivity.this, this.h, this.j);
            if (listB.size() <= 0) {
                return Boolean.FALSE;
            }
            C1719c.q().n(listB);
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            View view = SaveToActivity.this.q;
            if (view != null) {
                view.setVisibility(8);
            }
            if (bool != null) {
                if (bool.booleanValue()) {
                    C2014u.c0(this.i);
                } else {
                    Toast.makeText(this.i, R.string.error_file_load, 1).show();
                }
            }
            SaveToActivity.this.finish();
        }
    }

    private Uri H() {
        return getReferrer();
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!"android.intent.action.SEND".equals(action) && !"android.intent.action.SEND_MULTIPLE".equals(action)) {
            finish();
            return;
        }
        setContentView(R.layout.activity_transparent_progress);
        this.q = findViewById(R.id.progress);
        new a(this, intent, Q.y1() ? H() : null).i(new Void[0]);
    }
}
