package com.alphainventor.filemanager.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ax.D1.c;
import ax.D1.e;
import ax.J1.C0734o;
import ax.J1.C0740v;
import ax.J1.P;
import ax.J1.V;
import ax.p045d2.n;
import ax.p182n.ActivityC5571c;
import ax.p338y1.g;
import com.alphainventor.filemanager.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class SaveToActivity extends ActivityC5571c {
    private static final Logger F0 = g.a(SaveToActivity.class);
    View E0;

    private class a extends n<Void, Void, Boolean> {
        Intent h;
        Context i;

        a(Context context, Intent intent) {
            super(n.f.HIGHER);
            this.i = context;
            this.h = intent;
        }

        @Override // ax.p045d2.n
        protected void r() {
            View view = SaveToActivity.this.E0;
            if (view != null) {
                view.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            List<e.C0093e> listY0 = SaveToActivity.this.Y0(this.h);
            if (listY0.size() <= 0) {
                return Boolean.FALSE;
            }
            c.q().n(this.h, listY0);
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            View view = SaveToActivity.this.E0;
            if (view != null) {
                view.setVisibility(8);
            }
            if (bool != null) {
                if (bool.booleanValue()) {
                    C0734o.W(this.i);
                } else {
                    Toast.makeText(this.i, R.string.error_file_load, 1).show();
                }
            }
            SaveToActivity.this.finish();
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0076  */
    private void X0(List<e.C0093e> list, Uri uri) {
        Uri uriR;
        long length;
        String strH;
        String scheme = uri.getScheme();
        if ("file".equals(scheme) || "content".equals(scheme)) {
            try {
                ContentResolver contentResolver = getApplicationContext().getContentResolver();
                String type = contentResolver.getType(uri);
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = null;
                if ("file".equals(uri.getScheme())) {
                    strH = V.h(uri.getPath());
                    uriR = null;
                    length = new File(uri.getPath()).length();
                } else {
                    P pR = C0740v.r(this, uri);
                    String strA = pR.a();
                    long j = pR.b;
                    if (pR.c == null || !ax.E1.P.e0()) {
                        uriR = null;
                    } else {
                        try {
                            File file = new File(pR.c);
                            if (file.exists()) {
                                uriR = C0734o.r(file);
                            } else {
                                uriR = null;
                            }
                        } catch (Exception unused) {
                        }
                    }
                    length = j;
                    strH = strA;
                }
                if (uriR != null) {
                    try {
                        assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uriR, "r");
                    } catch (Exception unused2) {
                    }
                }
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor2 = assetFileDescriptorOpenAssetFileDescriptor == null ? contentResolver.openAssetFileDescriptor(uri, "r") : assetFileDescriptorOpenAssetFileDescriptor;
                if (assetFileDescriptorOpenAssetFileDescriptor2 != null) {
                    list.add(new e.C0093e(uri, assetFileDescriptorOpenAssetFileDescriptor2, type, strH, length));
                    return;
                }
                ax.p221pa.c.h().g().c("SaveTo no file uri !!").h("uri :" + uri).i();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (RuntimeException e2) {
                e2.printStackTrace();
                ax.p221pa.c.h().g().d("!! AddUri in SaveToActivity !!").m(e2).h("uri : " + uri.toString()).i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<e.C0093e> Y0(Intent intent) {
        ArrayList arrayList = new ArrayList();
        intent.getAction();
        if ("android.intent.action.SEND".equals(intent.getAction())) {
            if (intent.hasExtra("android.intent.extra.STREAM")) {
                Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                if (uri != null) {
                    X0(arrayList, uri);
                }
            } else {
                CharSequence charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.TEXT");
                if (charSequenceExtra != null) {
                    arrayList.add(new e.C0093e(charSequenceExtra.toString(), Z0(intent) + ".txt"));
                }
            }
        } else if (!"android.intent.action.SEND_MULTIPLE".equals(intent.getAction())) {
            ax.p045d2.b.g("INVALID ARGUMENT");
        } else if (intent.hasExtra("android.intent.extra.STREAM")) {
            ArrayList<Uri> parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            if (parcelableArrayListExtra != null) {
                for (Uri uri2 : parcelableArrayListExtra) {
                    if (uri2 != null) {
                        X0(arrayList, uri2);
                    }
                }
            }
        } else {
            ArrayList<CharSequence> charSequenceArrayListExtra = intent.getCharSequenceArrayListExtra("android.intent.extra.TEXT");
            String strZ0 = Z0(intent);
            if (charSequenceArrayListExtra != null) {
                int i = 1;
                for (CharSequence charSequence : charSequenceArrayListExtra) {
                    if (charSequence != null) {
                        arrayList.add(new e.C0093e(charSequence.toString(), strZ0 + String.format(Locale.US, "%02d", Integer.valueOf(i)) + ".txt"));
                        i++;
                    }
                }
            }
        }
        return arrayList;
    }

    private String Z0(Intent intent) {
        CharSequence charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.SUBJECT");
        return charSequenceExtra != null ? V.w(charSequenceExtra.toString()) : "shared_text";
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!"android.intent.action.SEND".equals(action) && !"android.intent.action.SEND_MULTIPLE".equals(action)) {
            finish();
            return;
        }
        setContentView(R.layout.activity_transparent_progress);
        this.E0 = findViewById(R.id.progress);
        new a(this, intent).i(new Void[0]);
    }
}
