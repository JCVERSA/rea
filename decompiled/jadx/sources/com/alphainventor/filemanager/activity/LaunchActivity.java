package com.alphainventor.filemanager.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ax.p006a3.Q;
import ax.p070e3.C1966j;
import ax.p086f3.A;
import ax.p086f3.B;
import ax.p086f3.C2002h;
import ax.p086f3.C2014u;
import ax.p086f3.C2016w;
import ax.p086f3.C2017x;
import ax.p086f3.EnumC2019z;
import ax.p086f3.F;
import ax.p086f3.K;
import ax.p086f3.N;
import ax.p086f3.W;
import ax.p086f3.d0;
import ax.p210n.ActivityC2164c;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.C6249y;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public class LaunchActivity extends ActivityC2164c {
    private View q;

    class a implements C2002h.a {
        final /* synthetic */ EnumC2019z a;
        final /* synthetic */ String b;

        a(EnumC2019z enumC2019z, String str) {
            this.a = enumC2019z;
            this.b = str;
        }

        @Override // ax.p086f3.C2002h.a
        public void a(Throwable th) {
            LaunchActivity.this.q.setVisibility(8);
            if (th != null) {
                th.printStackTrace();
            }
            if (th instanceof FileNotFoundException) {
                Toast.makeText(LaunchActivity.this, R.string.requested_file_not_found, 1).show();
            } else {
                Toast.makeText(LaunchActivity.this, R.string.error_file_load, 1).show();
            }
            LaunchActivity.this.finish();
        }

        @Override // ax.p086f3.C2002h.a
        public void b(File file) {
            LaunchActivity.this.q.setVisibility(8);
            try {
                String absolutePath = file.getAbsolutePath();
                EnumC2019z enumC2019z = this.a;
                if (enumC2019z == EnumC2019z.IMAGE) {
                    Intent intentD = C2016w.d(LaunchActivity.this, C6249y.J0(absolutePath), false);
                    intentD.putExtra("extra_temp_file_path", absolutePath);
                    intentD.putExtra("extra_temp_file_type", this.b);
                    LaunchActivity.this.startActivity(intentD);
                } else if (enumC2019z == EnumC2019z.AUDIO) {
                    C2014u.f0(LaunchActivity.this, absolutePath, this.b);
                } else if (enumC2019z == EnumC2019z.PLAIN_TEXT) {
                    LaunchActivity.this.startActivity(C2016w.h(LaunchActivity.this, absolutePath));
                } else {
                    C2461b.f();
                }
            } catch (C1966j unused) {
                Toast.makeText(LaunchActivity.this, R.string.error_file_load, 1).show();
            }
            LaunchActivity.this.finish();
        }
    }

    private void J(Uri uri, String str, EnumC2019z enumC2019z) throws Throwable {
        M();
        K(getContentResolver().openAssetFileDescriptor(uri, "r"), B.x(this, uri, null), str, enumC2019z);
    }

    private void K(AssetFileDescriptor assetFileDescriptor, W w, String str, EnumC2019z enumC2019z) {
        this.q.setVisibility(0);
        new C2002h(this, assetFileDescriptor, w, enumC2019z, new a(enumC2019z, str)).i(new Void[0]);
    }

    private void L(String str) {
        C2014u.d0(this, Uri.parse(B.U(C2017x.g(str).B(), str)));
    }

    private void M() {
        setContentView(R.layout.activity_transparent_progress);
        this.q = findViewById(R.id.progress);
    }

    private void N(Intent intent) {
        Intent intentF = C2014u.F(this, intent.getAction(), intent.getData(), intent.getType());
        intentF.putExtras(intent);
        startActivity(intentF);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0102  */
    /* JADX WARN: Code duplicated, block: B:82:0x0185 A[Catch: RuntimeException -> 0x0135, FileNotFoundException -> 0x0138, TryCatch #3 {FileNotFoundException -> 0x0138, RuntimeException -> 0x0135, blocks: (B:50:0x0125, B:52:0x012b, B:58:0x013b, B:60:0x0141, B:75:0x0176, B:82:0x0185, B:84:0x018f, B:86:0x019a, B:87:0x019e, B:89:0x01a4, B:91:0x01ae, B:77:0x017c, B:78:0x017f, B:63:0x014d, B:65:0x015d, B:67:0x0163, B:69:0x016b), top: B:106:0x0125, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x019e A[Catch: RuntimeException -> 0x0135, FileNotFoundException -> 0x0138, TryCatch #3 {FileNotFoundException -> 0x0138, RuntimeException -> 0x0135, blocks: (B:50:0x0125, B:52:0x012b, B:58:0x013b, B:60:0x0141, B:75:0x0176, B:82:0x0185, B:84:0x018f, B:86:0x019a, B:87:0x019e, B:89:0x01a4, B:91:0x01ae, B:77:0x017c, B:78:0x017f, B:63:0x014d, B:65:0x015d, B:67:0x0163, B:69:0x016b), top: B:106:0x0125, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01a4 A[Catch: RuntimeException -> 0x0135, FileNotFoundException -> 0x0138, TryCatch #3 {FileNotFoundException -> 0x0138, RuntimeException -> 0x0135, blocks: (B:50:0x0125, B:52:0x012b, B:58:0x013b, B:60:0x0141, B:75:0x0176, B:82:0x0185, B:84:0x018f, B:86:0x019a, B:87:0x019e, B:89:0x01a4, B:91:0x01ae, B:77:0x017c, B:78:0x017f, B:63:0x014d, B:65:0x015d, B:67:0x0163, B:69:0x016b), top: B:106:0x0125, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x01ae A[Catch: RuntimeException -> 0x0135, FileNotFoundException -> 0x0138, TRY_LEAVE, TryCatch #3 {FileNotFoundException -> 0x0138, RuntimeException -> 0x0135, blocks: (B:50:0x0125, B:52:0x012b, B:58:0x013b, B:60:0x0141, B:75:0x0176, B:82:0x0185, B:84:0x018f, B:86:0x019a, B:87:0x019e, B:89:0x01a4, B:91:0x01ae, B:77:0x017c, B:78:0x017f, B:63:0x014d, B:65:0x015d, B:67:0x0163, B:69:0x016b), top: B:106:0x0125, inners: #4 }] */
    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) throws Throwable {
        String path;
        String strY;
        String path2;
        ax.U2.b.f(this, true);
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("android.intent.action.VIEW".equals(action) && intent.getData() != null && "com.android.mtp.documents".equals(intent.getData().getHost())) {
            if (Q.k1()) {
                Intent intentE = C2014u.E(this, action, intent.getData());
                intentE.putExtras(intent);
                startActivity(intentE);
            } else {
                Toast.makeText(this, R.string.error, 1).show();
            }
        } else if ("android.intent.action.VIEW".equals(action) && intent.getData() != null && "media".equals(intent.getData().getHost())) {
            String strA = N.a(this, intent.getData());
            if (strA != null) {
                K k = K.i;
                String strE = k.e();
                if (strE == null || !d0.H(strE, strA)) {
                    L(strA);
                } else {
                    C2014u.d0(this, Uri.parse(B.U(k, strA)));
                }
            } else {
                Toast.makeText(this, R.string.error_file_not_found, 1).show();
            }
        } else if ("android.intent.action.VIEW".equals(action) && "resource/folder".equals(intent.getType())) {
            if (intent.getData() == null || !"content".equals(intent.getData().getScheme()) || (path2 = intent.getData().getPath()) == null || !path2.startsWith("/external_files/")) {
                N(intent);
            } else {
                String str = "/storage/emulated/0" + path2.substring(15);
                if (new File(str).exists()) {
                    L(str);
                } else {
                    N(intent);
                }
            }
        } else if ("android.intent.action.VIEW".equals(action) && intent.getData() != null && "content".equals(intent.getData().getScheme())) {
            String strO = C2014u.o(this, intent);
            try {
                if (A.r(strO)) {
                    J(intent.getData(), strO, EnumC2019z.AUDIO);
                    return;
                }
                if (A.I(strO)) {
                    J(intent.getData(), strO, EnumC2019z.IMAGE);
                    return;
                }
                boolean z = false;
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = null;
                try {
                    assetFileDescriptorOpenAssetFileDescriptor = getContentResolver().openAssetFileDescriptor(intent.getData(), "r");
                    if (assetFileDescriptorOpenAssetFileDescriptor != null && (strY = B.y(assetFileDescriptorOpenAssetFileDescriptor)) != null && strY.startsWith("/storage/")) {
                        L(strY);
                        z = true;
                    }
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        F.a(assetFileDescriptorOpenAssetFileDescriptor);
                    }
                } catch (Exception unused) {
                    if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                        if (!z) {
                            path = intent.getData().getPath();
                            if (path == null) {
                                if (A.T(strO)) {
                                    J(intent.getData(), strO, EnumC2019z.PLAIN_TEXT);
                                    return;
                                } else {
                                    intent.getData();
                                    N(intent);
                                }
                            } else if (A.T(strO)) {
                                J(intent.getData(), strO, EnumC2019z.PLAIN_TEXT);
                                return;
                            } else {
                                intent.getData();
                                N(intent);
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        F.a(assetFileDescriptorOpenAssetFileDescriptor);
                    }
                    throw th;
                }
                if (!z) {
                    path = intent.getData().getPath();
                    if (path == null && new File(path).exists()) {
                        L(path);
                    } else if (A.T(strO)) {
                        J(intent.getData(), strO, EnumC2019z.PLAIN_TEXT);
                        return;
                    } else {
                        intent.getData();
                        N(intent);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.error_file_not_found, 1).show();
            } catch (RuntimeException e2) {
                e2.printStackTrace();
                Toast.makeText(this, R.string.error_file_load, 1).show();
            }
        } else if ("com.example.android.uamp.open_ui".equals(action) || "android.intent.action.VIEW".equals(action) || "android.intent.action.VIEW_DOWNLOADS".equals(action)) {
            N(intent);
        }
        finish();
    }
}
