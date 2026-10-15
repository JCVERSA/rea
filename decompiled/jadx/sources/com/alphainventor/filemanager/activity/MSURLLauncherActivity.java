package com.alphainventor.filemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ax.J1.C0734o;
import ax.J1.C0744z;
import ax.p031c2.i;
import ax.p045d2.n;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
public class MSURLLauncherActivity extends ActivityC5571c {
    View E0;

    class a extends n<String, Void, String> {
        private Context h;
        private Uri i;
        private String j;

        a(Context context, Uri uri) {
            super(n.f.HIGH);
            this.h = context;
            this.i = uri;
        }

        private String y(String str) {
            String strSubstring = null;
            if (str == null) {
                return null;
            }
            Scanner scanner = new Scanner(str);
            while (scanner.hasNextLine()) {
                String strNextLine = scanner.nextLine();
                if (strNextLine != null && !strNextLine.startsWith("InternetShortcut") && strNextLine.startsWith("URL=")) {
                    strSubstring = strNextLine.substring(4);
                }
            }
            scanner.close();
            return strSubstring;
        }

        @Override // ax.p045d2.n
        protected void r() {
            View view = MSURLLauncherActivity.this.E0;
            if (view != null) {
                view.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public String g(String... strArr) throws Throwable {
            AssetFileDescriptor.AutoCloseInputStream autoCloseInputStream;
            String strL;
            AssetFileDescriptor.AutoCloseInputStream autoCloseInputStream2 = null;
            if (this.i != null) {
                try {
                    autoCloseInputStream = new AssetFileDescriptor.AutoCloseInputStream(this.h.getApplicationContext().getContentResolver().openAssetFileDescriptor(this.i, "r"));
                    try {
                        try {
                            strL = C0744z.l(autoCloseInputStream, 256, 10240L);
                            C0744z.a(autoCloseInputStream);
                        } catch (Exception e) {
                            e = e;
                            if (i.n(this.h)) {
                                this.j = e.getMessage();
                            }
                            C0744z.a(autoCloseInputStream);
                            return null;
                        }
                    } catch (Throwable th) {
                        th = th;
                        autoCloseInputStream2 = autoCloseInputStream;
                        C0744z.a(autoCloseInputStream2);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    autoCloseInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    C0744z.a(autoCloseInputStream2);
                    throw th;
                }
            } else {
                strL = null;
            }
            if (strL != null) {
                return y(strL);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(String str) {
            View view = MSURLLauncherActivity.this.E0;
            if (view != null) {
                view.setVisibility(8);
            }
            if (str != null) {
                try {
                    C0734o.h0(MSURLLauncherActivity.this, C0734o.l(Uri.parse(str), null, true, false));
                } catch (Exception unused) {
                    Toast.makeText(this.h, R.string.error, 1).show();
                }
            } else if (this.j != null) {
                Toast.makeText(this.h, this.h.getString(R.string.error_file_load) + ":" + this.j, 1).show();
            } else {
                Toast.makeText(this.h, R.string.error_file_load, 1).show();
            }
            MSURLLauncherActivity.this.finish();
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
