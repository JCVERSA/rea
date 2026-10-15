package com.alphainventor.filemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ax.B1.d;
import ax.I1.C0718i;
import ax.J1.AbstractC0726g;
import ax.J1.C0744z;
import ax.J1.r;
import ax.p031c2.i;
import ax.p045d2.n;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class SplitApkInstallerActivity extends ActivityC5571c {
    View E0;

    class a extends n<String, Void, Boolean> {
        private Context h;
        private Uri i;
        private String j;

        a(Context context, Uri uri) {
            super(n.f.HIGH);
            this.h = context;
            this.i = uri;
        }

        @Override // ax.p045d2.n
        protected void r() {
            View view = SplitApkInstallerActivity.this.E0;
            if (view != null) {
                view.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v14 */
        /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.AutoCloseable] */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.AutoCloseable] */
        /* JADX WARN: Type inference failed for: r1v22 */
        /* JADX WARN: Type inference failed for: r1v8 */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.AutoCloseable] */
        /* JADX WARN: Type inference failed for: r2v9, types: [android.content.res.AssetFileDescriptor$AutoCloseInputStream, java.io.InputStream, java.lang.AutoCloseable] */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) throws Throwable {
            ?? r1;
            Exception e;
            FileOutputStream fileOutputStream;
            File fileL = ax.G1.a.l(this.h, "apks-tmp");
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(this.i.hashCode()));
            ?? autoCloseInputStream = ".spliatapk";
            sb.append(".spliatapk");
            String string = sb.toString();
            File file = new File(fileL, string);
            if (this.i != null) {
                ?? r0 = 0;
                try {
                    try {
                        autoCloseInputStream = new AssetFileDescriptor.AutoCloseInputStream(this.h.getApplicationContext().getContentResolver().openAssetFileDescriptor(this.i, "r"));
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                C0744z.d(autoCloseInputStream, fileOutputStream);
                                C0744z.a(autoCloseInputStream);
                                C0744z.a(fileOutputStream);
                            } catch (Exception e2) {
                                e = e2;
                                if (i.n(this.h)) {
                                    this.j = e.getMessage();
                                }
                                file.delete();
                                Boolean bool = Boolean.FALSE;
                                C0744z.a(autoCloseInputStream);
                                C0744z.a(fileOutputStream);
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
                            C0744z.a(r0);
                            C0744z.a(r1);
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
                    C0744z.a(r0);
                    C0744z.a(r1);
                    throw th;
                }
            }
            try {
                try {
                    return Boolean.valueOf(d.N(this.h, (AbstractC0726g) r.f(file).V0(file.getAbsolutePath())));
                } catch (SecurityException e5) {
                    if (e5.getMessage() != null && e5.getMessage().contains("FRP")) {
                        this.j = e5.getMessage();
                    }
                    return Boolean.FALSE;
                } finally {
                    file.delete();
                }
            } catch (C0718i e6) {
                if (i.n(this.h)) {
                    this.j = e6.getMessage();
                }
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            View view = SplitApkInstallerActivity.this.E0;
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

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.p338y1.b.f(this, true);
        super.onCreate(bundle);
        setContentView(R.layout.activity_transparent_progress);
        this.E0 = findViewById(R.id.progress);
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
