package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import ax.G1.c;
import ax.H1.O;
import ax.I1.C0718i;
import ax.J1.C0734o;
import ax.J1.C0736q;
import ax.J1.C0739u;
import ax.J1.V;
import ax.K1.AbstractC0775z;
import ax.K1.C;
import ax.p031c2.i;
import ax.p182n.ActivityC5571c;
import ax.p338y1.f;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.u;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutActivity extends ActivityC5571c {
    C E0;

    /* JADX WARN: Code duplicated, block: B:13:0x0025  */
    private void W0(Uri uri, Bookmark bookmark) {
        try {
            u uVarA0 = u.A0(bookmark.w());
            Intent intentK3 = O.K3(this, uVarA0);
            if (intentK3 != null && C0734o.M(intentK3)) {
                intentK3 = C0736q.d(this, uVarA0, false);
            } else if (intentK3 != null && C0734o.N(intentK3)) {
                intentK3 = null;
            } else if (intentK3 == null) {
                if (i.D(this) && C0739u.x(uVarA0, false)) {
                    intentK3 = C0736q.d(this, uVarA0, false);
                } else if (i.G(this) && C0739u.A(uVarA0) && !O.S3(this, c.a.GENERAL, uVarA0, false)) {
                    intentK3 = C0736q.g(this, uVarA0, -1, false);
                } else if (i.E(this) && C0739u.y(uVarA0)) {
                    intentK3 = null;
                } else if (i.F(this) && C0739u.z(uVarA0)) {
                    intentK3 = C0736q.f(this, null, uVarA0);
                } else {
                    if (!AbstractC0775z.e7(this, uVarA0)) {
                        this.E0.b3(true);
                        this.E0.a3(c.a.GENERAL, uVarA0, uVarA0.s(), false, true);
                        return;
                    }
                    intentK3 = null;
                }
            }
            if (intentK3 == null || C0734o.U(intentK3)) {
                X0(uri);
            } else {
                Y0(intentK3, 0, uVarA0.v());
                finish();
            }
        } catch (C0718i e) {
            Toast.makeText(this, R.string.error_file_load, 1).show();
            e.printStackTrace();
            finish();
        }
    }

    private void X0(Uri uri) {
        C0734o.X(this, uri);
        finish();
    }

    private void Y0(Intent intent, int i, String str) {
        String str2 = C0736q.k(this, intent, i, false) ? "success" : "failure";
        if (str == null) {
            if (intent.getData() != null) {
                str = V.j(V.h(intent.getData().getPath()));
            } else {
                if ("com.filemanager.plugin.action.LAUNCH_FILE_URI".equals(intent.getAction()) && intent.hasExtra("com.filemanager.plugin.extra.DATA")) {
                    String stringExtra = intent.getStringExtra("com.filemanager.plugin.extra.DATA");
                    if (stringExtra != null) {
                        str = V.j(V.h(Uri.parse(stringExtra).getPath()));
                    }
                } else {
                    ax.p045d2.b.g("What case is this?");
                }
                str = HttpUrl.FRAGMENT_ENCODE_SET;
            }
        }
        ax.p338y1.a.k().o("command", "file_open").c("loc", "ShortCut").c("ext", str).c("result", str2).e();
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.p338y1.b.f(this, true);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!"com.alphainventor.filemanager.OPEN_SHORTCUT".equals(intent.getAction())) {
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        C c = (C) w().i0("headless_fragment");
        this.E0 = c;
        if (c == null) {
            this.E0 = C.X2("Shortcut");
            w().o().e(this.E0, "headless_fragment").i();
        }
        Boolean boolValueOf = intent.hasExtra("IS_DIRECTORY") ? Boolean.valueOf(intent.getBooleanExtra("IS_DIRECTORY", false)) : null;
        Bookmark bookmarkC = Bookmark.c(this, intent.getData());
        if (boolValueOf == null || boolValueOf.booleanValue() || !f.w0(bookmarkC.s())) {
            X0(intent.getData());
        } else {
            W0(intent.getData(), bookmarkC);
        }
    }
}
