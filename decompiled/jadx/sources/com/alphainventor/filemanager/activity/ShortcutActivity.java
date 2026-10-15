package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import ax.U2.d;
import ax.U2.f;
import ax.p054d3.T;
import ax.p070e3.C1966j;
import ax.p086f3.A;
import ax.p086f3.C2014u;
import ax.p086f3.C2016w;
import ax.p086f3.d0;
import ax.p102g3.E;
import ax.p102g3.M;
import ax.p210n.ActivityC2164c;
import ax.p390y3.k;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.C6249y;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutActivity extends ActivityC2164c {
    M q;

    /* JADX WARN: Code duplicated, block: B:13:0x0025  */
    private void H(Uri uri, Bookmark bookmark) {
        try {
            C6249y c6249yJ0 = C6249y.J0(bookmark.x());
            Intent intentK3 = T.K3(this, c6249yJ0);
            if (intentK3 != null && C2014u.S(intentK3)) {
                intentK3 = C2016w.d(this, c6249yJ0, false);
            } else if (intentK3 != null && C2014u.T(intentK3)) {
                intentK3 = null;
            } else if (intentK3 == null) {
                if (k.G(this) && A.B(c6249yJ0, false)) {
                    intentK3 = C2016w.d(this, c6249yJ0, false);
                } else if (k.J(this) && A.E(c6249yJ0) && !T.U3(this, ax.c3.c.a.GENERAL, c6249yJ0, false)) {
                    intentK3 = C2016w.i(this, c6249yJ0, -1, false);
                } else if (k.H(this) && A.C(c6249yJ0) && !T.T3(this, c6249yJ0, false)) {
                    intentK3 = null;
                } else if (k.I(this) && A.D(c6249yJ0)) {
                    intentK3 = C2016w.g(this, null, c6249yJ0);
                } else {
                    if (!E.n8(this, c6249yJ0)) {
                        this.q.Z2(true);
                        this.q.Y2(ax.c3.c.a.GENERAL, c6249yJ0, c6249yJ0.t(), false, true);
                        return;
                    }
                    intentK3 = null;
                }
            }
            if (intentK3 == null || C2014u.a0(intentK3)) {
                J(uri);
            } else {
                K(intentK3, 0, c6249yJ0.y());
                finish();
            }
        } catch (C1966j e) {
            Toast.makeText(this, R.string.error_file_load, 1).show();
            e.printStackTrace();
            finish();
        }
    }

    private void J(Uri uri) {
        C2014u.d0(this, uri);
        finish();
    }

    private void K(Intent intent, int i, String str) {
        String str2 = C2016w.m(this, intent, i, false) ? "success" : "failure";
        if (str == null) {
            if (intent.getData() != null) {
                str = d0.j(d0.h(intent.getData().getPath()));
            } else {
                if ("com.filemanager.plugin.action.LAUNCH_FILE_URI".equals(intent.getAction()) && intent.hasExtra("com.filemanager.plugin.extra.DATA")) {
                    String stringExtra = intent.getStringExtra("com.filemanager.plugin.extra.DATA");
                    if (stringExtra != null) {
                        str = d0.j(d0.h(Uri.parse(stringExtra).getPath()));
                    }
                } else {
                    C2461b.g("What case is this?");
                }
                str = HttpUrl.FRAGMENT_ENCODE_SET;
            }
        }
        ax.U2.a.k().o("command", "file_open").c("loc", "ShortCut").c("ext", str).c("result", str2).e();
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.U2.b.f(this, true);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!"com.alphainventor.filemanager.OPEN_SHORTCUT".equals(intent.getAction())) {
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        M m = (M) getSupportFragmentManager().k0("headless_fragment");
        this.q = m;
        if (m == null) {
            this.q = M.U2("Shortcut");
            getSupportFragmentManager().o().e(this.q, "headless_fragment").i();
        }
        Boolean boolValueOf = intent.hasExtra("IS_DIRECTORY") ? Boolean.valueOf(intent.getBooleanExtra("IS_DIRECTORY", false)) : null;
        Uri data = intent.getData();
        Bookmark bookmarkC = Bookmark.c(this, data);
        if (bookmarkC == null) {
            d.c("shotcuturinull", new Exception("shortcut error:" + data));
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        if (boolValueOf == null || boolValueOf.booleanValue() || !f.y0(bookmarkC.t())) {
            J(intent.getData());
        } else {
            H(intent.getData(), bookmarkC);
        }
    }
}
