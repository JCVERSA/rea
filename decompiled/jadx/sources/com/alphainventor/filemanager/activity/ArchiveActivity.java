package com.alphainventor.filemanager.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ax.E1.P;
import ax.E1.u;
import ax.G1.j;
import ax.I1.C0710a;
import ax.I1.C0718i;
import ax.J1.C0734o;
import ax.J1.C0740v;
import ax.J1.N;
import ax.J1.V;
import ax.J1.r;
import ax.K1.AbstractC0762l;
import ax.K1.C0761k;
import ax.p045d2.n;
import ax.p045d2.v;
import ax.p087g2.g;
import ax.p087g2.q;
import ax.p221pa.c;
import ax.p338y1.f;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5827a;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.provider.MyFileProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class ArchiveActivity extends ax.p352z1.b {
    private C0761k a1;
    g b1;
    private q c1;
    private ArrayList<ParcelFileDescriptor> d1 = new ArrayList<>();
    private boolean e1;

    private class a extends n<Void, Void, Boolean> {
        private Context h;
        private Uri i;
        private File j;
        private C5839m k;
        private Throwable l;
        private long m;
        private ParcelFileDescriptor n;
        private String o;

        public a(Context context, Uri uri, String str, long j) {
            super(n.f.HIGHER);
            this.h = context;
            this.i = uri;
            this.j = ArchiveActivity.this.Y1(str);
            this.m = j;
            this.o = str;
        }

        @Override // ax.p045d2.n
        protected void o() {
            this.k.P(true);
        }

        @Override // ax.p045d2.n
        protected void r() {
            C5839m c5839mF = r.f(this.j);
            this.k = c5839mF;
            c5839mF.S();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            InputStream inputStreamOpenInputStream;
            ContentResolver contentResolver = this.h.getContentResolver();
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(this.i, "r");
                this.n = parcelFileDescriptorOpenFileDescriptor;
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    return Boolean.TRUE;
                }
            } catch (SQLiteException e) {
                c.h().g().d("!! openFileDescriptor SQLiteException in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e = e3;
                c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (IllegalStateException e4) {
                e = e4;
                c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (NullPointerException e5) {
                e = e5;
                c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (SecurityException e6) {
                e = e6;
                c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            }
            try {
                AbstractC5838l abstractC5838lV0 = this.k.V0(this.j.getAbsolutePath());
                if (abstractC5838lV0.n()) {
                    this.k.l1(abstractC5838lV0);
                }
                try {
                    inputStreamOpenInputStream = contentResolver.openInputStream(this.i);
                } catch (FileNotFoundException e7) {
                    this.l = e7;
                    String string = this.i.toString();
                    if (string != null && !string.startsWith("content://downloads/")) {
                        c.h().d("ARCHTEMP1").h(this.i.toString()).i();
                    }
                    inputStreamOpenInputStream = null;
                } catch (IllegalArgumentException e8) {
                    e = e8;
                    this.l = e;
                    c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.e1 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (IllegalStateException e9) {
                    e = e9;
                    this.l = e;
                    c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.e1 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (NullPointerException e10) {
                    e = e10;
                    this.l = e;
                    c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.e1 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (SecurityException e11) {
                    e = e11;
                    this.l = e;
                    c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.e1 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                }
                if (inputStreamOpenInputStream == null) {
                    return Boolean.FALSE;
                }
                try {
                    AbstractC5838l abstractC5838lV1 = this.k.V0(this.j.getAbsolutePath());
                    N n = new N(inputStreamOpenInputStream, -1L);
                    this.k.c1(abstractC5838lV1, n, abstractC5838lV1.s(), this.m, null, null, true, this, null);
                    n.d();
                    return Boolean.TRUE;
                } catch (C0710a e12) {
                    e12.printStackTrace();
                    return Boolean.FALSE;
                } catch (C0718i e13) {
                    e13.printStackTrace();
                    return Boolean.FALSE;
                }
            } catch (C0718i e14) {
                e14.printStackTrace();
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            this.k.P(true);
            if (bool.booleanValue()) {
                if (this.n == null) {
                    ArchiveActivity.this.V1(this.o, this.j, 2);
                    return;
                } else {
                    ArchiveActivity.this.d1.add(this.n);
                    ArchiveActivity.this.S1(this.i, this.o, this.n);
                    return;
                }
            }
            Throwable th = this.l;
            if (th == null || !(th instanceof FileNotFoundException)) {
                Toast.makeText(ArchiveActivity.this, R.string.error_loading, 1).show();
            } else {
                Toast.makeText(ArchiveActivity.this, R.string.requested_file_not_found, 1).show();
            }
            ArchiveActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S1(Uri uri, String str, ParcelFileDescriptor parcelFileDescriptor) {
        C0761k c0761k = new C0761k();
        Bundle bundle = new Bundle();
        bundle.putParcelable("archive_uri", uri);
        bundle.putInt("archive_file_type", 3);
        bundle.putString("archive_name", str);
        bundle.putInt("file_descriptor", parcelFileDescriptor.getFd());
        c0761k.J2(bundle);
        T1(c0761k);
    }

    private void T1(C0761k c0761k) {
        this.a1 = c0761k;
        if (isFinishing() || w().G0()) {
            return;
        }
        w().o().s(R.id.fragment_container, this.a1, "archive").g("archive").j();
    }

    private void U1(String str, Uri uri, int i) {
        C0761k c0761k = new C0761k();
        Bundle bundle = new Bundle();
        bundle.putParcelable("archive_uri", uri);
        bundle.putInt("archive_file_type", i);
        bundle.putString("archive_name", str);
        c0761k.J2(bundle);
        T1(c0761k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V1(String str, File file, int i) {
        U1(str, C0734o.r(file), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File Y1(String str) {
        File file = new File(ax.G1.a.l(this, "archive-tmp"), HttpUrl.FRAGMENT_ENCODE_SET + System.currentTimeMillis());
        file.mkdir();
        return new File(file, str);
    }

    @Override // ax.p352z1.b
    public void C1(f fVar, int i, String str, boolean z) {
        if (z) {
            M1();
        }
    }

    @Override // ax.p352z1.b
    public void L1() {
        invalidateOptionsMenu();
    }

    public AbstractC0762l W1() {
        return this.a1;
    }

    public g X1() {
        return this.b1;
    }

    @Override // ax.p352z1.b
    public void m1(f fVar, int i, String str) {
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        C0761k c0761k = this.a1;
        if (c0761k == null || !c0761k.k3()) {
            finish();
        }
    }

    @Override // ax.p352z1.b, com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        Fragment fragmentH0;
        super.onCreate(bundle);
        setContentView(R.layout.activity_archive);
        if (P.x1() && v.q(this)) {
            u.o(getWindow(), -16777216);
        }
        this.b1 = new g(this, (ViewGroup) findViewById(R.id.toolbar_container));
        Uri data = getIntent().getData();
        this.e1 = bundle != null;
        if (bundle != null && (fragmentH0 = w().h0(R.id.fragment_container)) != null) {
            w().o().q(fragmentH0).j();
        }
        if (data == null) {
            Toast.makeText(this, R.string.error_loading, 1).show();
            finish();
            return;
        }
        String path = data.getPath();
        String scheme = data.getScheme();
        if ("content".equals(scheme)) {
            if (MyFileProvider.v(data)) {
                File fileL = MyFileProvider.l(data);
                String name = fileL.getName();
                V1(name, fileL, 1);
                this.b1.j(name);
                return;
            }
            ax.J1.P pS = C0740v.s(this, data, "zip");
            String strA = pS.a();
            new a(this, data, strA, pS.b).h(new Void[0]);
            this.b1.j(strA);
            return;
        }
        if ("file".equals(scheme) && V.y(path)) {
            String strH = V.h(path);
            V1(strH, new File(path), 1);
            this.b1.j(strH);
        } else {
            if (!j.h(scheme, f.Y0)) {
                c.h().d("AA").h(data.toString()).i();
                Toast.makeText(this, R.string.error_loading, 1).show();
                finish();
                return;
            }
            C5839m c5839mD = r.d(j.a(data).d());
            if (!c5839mD.a()) {
                Toast.makeText(this, R.string.error_loading, 1).show();
                finish();
            } else {
                String strS0 = ((C5827a) c5839mD.t()).s0();
                U1(strS0, data, 4);
                this.b1.j(strS0);
            }
        }
    }

    @Override // ax.p352z1.b, com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Iterator<ParcelFileDescriptor> it = this.d1.iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // ax.p182n.ActivityC5571c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (W1() == null || !W1().f4(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        C0761k c0761k = this.a1;
        if (c0761k == null) {
            finish();
            return true;
        }
        c0761k.V8("toolbar_back");
        return true;
    }

    @Override // ax.p352z1.b, com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // ax.p352z1.b
    public AbstractC0762l p1() {
        return this.a1;
    }

    @Override // ax.p352z1.b
    public q s1() {
        if (this.c1 == null) {
            q qVar = new q(this, findViewById(R.id.paste_layout), findViewById(R.id.paste_layout_safe_area));
            this.c1 = qVar;
            qVar.B();
        }
        return this.c1;
    }

    @Override // ax.p352z1.b
    public void w1() {
        X1().a();
    }

    @Override // ax.p352z1.b
    public void x1(String str) {
        finish();
    }

    @Override // ax.p352z1.b
    public void y1(Bookmark bookmark) {
    }
}
