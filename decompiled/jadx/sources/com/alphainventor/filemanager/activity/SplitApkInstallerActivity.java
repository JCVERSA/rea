package com.alphainventor.filemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ax.X2.d;
import ax.p070e3.C1966j;
import ax.p070e3.s;
import ax.p086f3.AbstractC2005k;
import ax.p086f3.C2017x;
import ax.p086f3.F;
import ax.p210n.ActivityC2164c;
import ax.p390y3.k;
import ax.p406z3.q;
import com.alphainventor.filemanager.R;
import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class SplitApkInstallerActivity extends ActivityC2164c {
    View q;

    class a extends q<String, Void, Boolean> {
        private Context h;
        private Uri i;
        private String j;

        a(Context context, Uri uri) {
            super(q.e.HIGH);
            this.h = context;
            this.i = uri;
        }

        @Override // ax.p406z3.q
        protected void r() {
            View view = SplitApkInstallerActivity.this.q;
            if (view != null) {
                view.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.AutoCloseable] */
        /* JADX WARN: Type inference failed for: r0v22 */
        /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.AutoCloseable] */
        /* JADX WARN: Type inference failed for: r1v23 */
        /* JADX WARN: Type inference failed for: r1v9 */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.AutoCloseable] */
        /* JADX WARN: Type inference failed for: r2v9, types: [android.content.res.AssetFileDescriptor$AutoCloseInputStream, java.io.InputStream, java.lang.AutoCloseable] */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) throws Throwable {
            ?? r1;
            Exception e;
            FileOutputStream fileOutputStream;
            File fileM = ax.p038c3.a.m(this.h, "apks-tmp");
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(this.i.hashCode()));
            ?? autoCloseInputStream = ".spliatapk";
            sb.append(".spliatapk");
            String string = sb.toString();
            File file = new File(fileM, string);
            if (this.i != null) {
                ?? r0 = 0;
                try {
                    try {
                        autoCloseInputStream = new AssetFileDescriptor.AutoCloseInputStream(this.h.getApplicationContext().getContentResolver().openAssetFileDescriptor(this.i, "r"));
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                F.b(autoCloseInputStream, fileOutputStream);
                                F.a(autoCloseInputStream);
                                F.a(fileOutputStream);
                            } catch (Exception e2) {
                                e = e2;
                                if (k.p(this.h)) {
                                    this.j = e.getMessage();
                                }
                                file.delete();
                                Boolean bool = Boolean.FALSE;
                                F.a(autoCloseInputStream);
                                F.a(fileOutputStream);
                                return bool;
                            }
                        } catch (Exception e3) {
                            fileOutputStream = null;
                            e = e3;
                        } catch (Throwable th) {
                            th = th;
                            string = null;
                            r0 = autoCloseInputStream;
                            r1 = string;
                            F.a(r0);
                            F.a(r1);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e4) {
                    autoCloseInputStream = 0;
                    e = e4;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    r1 = 0;
                    F.a(r0);
                    F.a(r1);
                    throw th;
                }
            }
            try {
                try {
                    return Boolean.valueOf(d.O(this.h, (AbstractC2005k) C2017x.f(file).a1(file.getAbsolutePath())));
                } catch (C1966j e5) {
                    if (k.p(this.h)) {
                        this.j = e5.getMessage();
                    }
                    return Boolean.FALSE;
                }
            } catch (SecurityException e6) {
                if (e6.getMessage() != null && e6.getMessage().contains("FRP")) {
                    this.j = e6.getMessage();
                }
                return Boolean.FALSE;
            } catch (C1966j e7) {
                if (e7 instanceof s) {
                    this.j = SplitApkInstallerActivity.this.getString(R.string.error_not_enough_storage);
                }
                return Boolean.FALSE;
            } finally {
                file.delete();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            View view = SplitApkInstallerActivity.this.q;
            if (view != null) {
                view.setVisibility(8);
            }
            if (bool == null || !bool.booleanValue()) {
                if (this.j != null) {
                    Toast.makeText(this.h, this.h.getString(R.string.error) + ":" + this.j, 1).show();
                } else {
                    Toast.makeText(this.h, R.string.error, 1).show();
                }
            }
            SplitApkInstallerActivity.this.finish();
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.U2.b.f(this, true);
        super.onCreate(bundle);
        setContentView(R.layout.activity_transparent_progress);
        this.q = findViewById(R.id.progress);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            new a(this, data).h(new String[0]);
        } else {
            Toast.makeText(this, R.string.error, 1).show();
            finish();
        }
    }
}
