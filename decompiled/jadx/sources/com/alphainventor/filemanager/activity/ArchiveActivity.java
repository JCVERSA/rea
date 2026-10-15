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
import ax.C3.h;
import ax.C3.v;
import ax.U2.f;
import ax.p006a3.Q;
import ax.p038c3.k;
import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.B;
import ax.p086f3.C2014u;
import ax.p086f3.C2017x;
import ax.p086f3.U;
import ax.p086f3.W;
import ax.p086f3.d0;
import ax.p102g3.AbstractC2067n;
import ax.p102g3.C2065l;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import ax.p406z3.z;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6227b;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.file.C6249y;
import com.alphainventor.filemanager.provider.MyFileProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class ArchiveActivity extends com.alphainventor.filemanager.activity.b {
    private C2065l E0;
    h F0;
    private v G0;
    private ArrayList<ParcelFileDescriptor> H0 = new ArrayList<>();
    private boolean I0;

    private class a extends q<Void, Void, Boolean> {
        private Context h;
        private Uri i;
        private File j;
        private C6240o k;
        private Throwable l;
        private long m;
        private ParcelFileDescriptor n;
        private String o;
        private Uri p;

        public a(Context context, Uri uri, String str, long j, Uri uri2) {
            super(q.e.HIGHER);
            this.h = context;
            this.i = uri;
            this.j = ArchiveActivity.this.Y0(str);
            this.m = j;
            this.o = str;
            this.p = uri2;
        }

        @Override // ax.p406z3.q
        protected void o() {
            this.k.S(true);
        }

        @Override // ax.p406z3.q
        protected void r() {
            C6240o c6240oF = C2017x.f(this.j);
            this.k = c6240oF;
            c6240oF.V();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:80:0x017a A[PHI: r12
          0x017a: PHI (r12v7 com.alphainventor.filemanager.activity.ArchiveActivity$a) = 
          (r12v6 com.alphainventor.filemanager.activity.ArchiveActivity$a)
          (r12v8 com.alphainventor.filemanager.activity.ArchiveActivity$a)
         binds: [B:79:0x0178, B:85:0x018f] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) throws Throwable {
            C1966j c1966j;
            InputStream inputStreamOpenInputStream;
            a aVar;
            ContentResolver contentResolver = this.h.getContentResolver();
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(this.i, "r");
                this.n = parcelFileDescriptorOpenFileDescriptor;
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    return Boolean.TRUE;
                }
            } catch (SQLiteException e) {
                ax.p302sb.c.h().g().d("!! openFileDescriptor SQLiteException in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e = e3;
                ax.p302sb.c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (IllegalStateException e4) {
                e = e4;
                ax.p302sb.c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (NullPointerException e5) {
                e = e5;
                ax.p302sb.c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (SecurityException e6) {
                e = e6;
                ax.p302sb.c.h().g().d("!! openFileDescriptor in ArchiveActivity !!").m(e).h("uri : " + this.i).i();
            } catch (RuntimeException unused) {
            }
            try {
                AbstractC6239n abstractC6239nA1 = this.k.a1(this.j.getAbsolutePath());
                if (abstractC6239nA1.o()) {
                    try {
                        this.k.q1(abstractC6239nA1);
                    } catch (C1966j e7) {
                        c1966j = e7;
                        c1966j.printStackTrace();
                        return Boolean.FALSE;
                    }
                }
                try {
                    inputStreamOpenInputStream = contentResolver.openInputStream(this.i);
                } catch (FileNotFoundException e8) {
                    this.l = e8;
                    String string = this.i.toString();
                    if (string != null && !string.startsWith("content://downloads/")) {
                        ax.p302sb.c.h().d("ARCHTEMP1").h(this.i.toString()).i();
                    }
                    inputStreamOpenInputStream = null;
                } catch (IllegalArgumentException e9) {
                    e = e9;
                    this.l = e;
                    ax.p302sb.c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.I0 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (IllegalStateException e10) {
                    e = e10;
                    this.l = e;
                    ax.p302sb.c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.I0 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (NullPointerException e11) {
                    e = e11;
                    this.l = e;
                    ax.p302sb.c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.I0 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (SecurityException e12) {
                    e = e12;
                    this.l = e;
                    ax.p302sb.c.h().d("ARCHTEMP2").m(e).h("Restored:" + ArchiveActivity.this.I0 + ",uri=" + this.i.toString()).i();
                    inputStreamOpenInputStream = null;
                } catch (RuntimeException e13) {
                    this.l = e13;
                    inputStreamOpenInputStream = null;
                }
                try {
                    if (inputStreamOpenInputStream == null) {
                        return Boolean.FALSE;
                    }
                    try {
                        AbstractC6239n abstractC6239nA2 = this.k.a1(this.j.getAbsolutePath());
                        U u = new U(inputStreamOpenInputStream, -1L);
                        aVar = this;
                        try {
                            this.k.l1(abstractC6239nA2, u, abstractC6239nA2.t(), this.m, null, null, true, aVar, null);
                            u.e();
                            Boolean bool = Boolean.TRUE;
                            if (Q.X1() && aVar.k.G()) {
                                aVar.k.n(null);
                            }
                            return bool;
                        } catch (C1957a e14) {
                            e = e14;
                            e.printStackTrace();
                            if (Q.X1() && aVar.k.G()) {
                                aVar.k.n(null);
                            }
                            return Boolean.FALSE;
                        } catch (C1966j e15) {
                            e = e15;
                            e.printStackTrace();
                            if (Q.X1() && aVar.k.G()) {
                                aVar.k.n(null);
                            }
                            return Boolean.FALSE;
                        }
                    } catch (C1957a e16) {
                        e = e16;
                        aVar = this;
                    } catch (C1966j e17) {
                        e = e17;
                        aVar = this;
                    } catch (Throwable th) {
                        th = th;
                        if (Q.X1() && this.k.G()) {
                            this.k.n(null);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (C1966j e18) {
                c1966j = e18;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            this.k.S(true);
            if (bool.booleanValue()) {
                if (this.n == null) {
                    ArchiveActivity.this.V0(this.o, this.j, 2, this.p);
                    return;
                } else {
                    ArchiveActivity.this.H0.add(this.n);
                    ArchiveActivity.this.S0(this.i, this.o, this.n, this.p);
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

    private class b extends q<Void, Void, Boolean> {
        private Context h;
        private File i;
        private C6240o j;
        private long k;
        private ParcelFileDescriptor l;
        private String m;
        private C6249y n;
        private Uri o;
        private Uri p;

        public b(Context context, Uri uri, C6249y c6249y, Uri uri2) {
            super(q.e.HIGHER);
            this.h = context;
            String strB = c6249y.B();
            this.m = strB;
            this.i = ArchiveActivity.this.Y0(strB);
            this.k = c6249y.p();
            this.n = c6249y;
            this.o = uri;
            this.p = uri2;
        }

        @Override // ax.p406z3.q
        protected void o() {
            this.j.S(true);
        }

        @Override // ax.p406z3.q
        protected void r() {
            C6240o c6240oF = C2017x.f(this.i);
            this.j = c6240oF;
            c6240oF.V();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            try {
                ParcelFileDescriptor parcelFileDescriptorC0 = ((C6248x) this.j.u()).C0(this.n);
                this.l = parcelFileDescriptorC0;
                if (parcelFileDescriptorC0 != null) {
                    return Boolean.TRUE;
                }
            } catch (C1966j e) {
                e.printStackTrace();
            }
            return Boolean.FALSE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            this.j.S(true);
            if (bool.booleanValue()) {
                ArchiveActivity.this.H0.add(this.l);
                ArchiveActivity.this.S0(this.o, this.m, this.l, this.p);
            } else {
                Toast.makeText(ArchiveActivity.this, R.string.error_loading, 1).show();
                ArchiveActivity.this.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0(Uri uri, String str, ParcelFileDescriptor parcelFileDescriptor, Uri uri2) {
        C2065l c2065l = new C2065l();
        Bundle bundle = new Bundle();
        bundle.putParcelable("archive_uri", uri);
        bundle.putInt("archive_file_type", 3);
        bundle.putString("archive_name", str);
        bundle.putInt("file_descriptor", parcelFileDescriptor.getFd());
        bundle.putInt("location_key", C6227b.u0(uri));
        c2065l.F2(bundle);
        T0(c2065l, uri2);
    }

    private void T0(C2065l c2065l, Uri uri) {
        this.E0 = c2065l;
        if (uri != null) {
            try {
                k kVarA = k.a(uri);
                if (kVarA.d() == c2065l.M3()) {
                    c2065l.m4(kVarA.e());
                } else {
                    C2461b.g("location unit does not match");
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (isFinishing() || getSupportFragmentManager().I0()) {
            return;
        }
        getSupportFragmentManager().o().s(R.id.fragment_container, this.E0, "archive").g("archive").j();
    }

    private void U0(String str, Uri uri, int i, Uri uri2, C6240o c6240o) {
        C2065l c2065l = new C2065l();
        Bundle bundle = new Bundle();
        bundle.putParcelable("archive_uri", uri);
        bundle.putInt("archive_file_type", i);
        bundle.putString("archive_name", str);
        bundle.putParcelable("open_uri", uri2);
        if (c6240o != null) {
            c2065l.Ta(c6240o);
            bundle.putInt("location_key", c6240o.y());
        } else {
            bundle.putInt("location_key", C6227b.u0(uri));
        }
        c2065l.F2(bundle);
        T0(c2065l, uri2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V0(String str, File file, int i, Uri uri) {
        U0(str, C2014u.w(file), i, uri, null);
    }

    private boolean W0(File file) {
        try {
            C6249y c6249yJ0 = C6249y.J0(file.getAbsolutePath());
            if (c6249yJ0.c1() && com.alphainventor.filemanager.shizuku.c.t().l()) {
                return c6249yJ0.o();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File Y0(String str) {
        File file = new File(ax.p038c3.a.m(this, "archive-tmp"), HttpUrl.FRAGMENT_ENCODE_SET + System.currentTimeMillis());
        file.mkdir();
        return new File(file, str);
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void L0() {
        invalidateOptionsMenu();
    }

    public AbstractC2067n X0() {
        return this.E0;
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void c0(f fVar, int i, String str) {
        finish();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public AbstractC2067n g0() {
        return this.E0;
    }

    @Override // com.alphainventor.filemanager.activity.b
    public h h0() {
        return this.F0;
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        if (p0()) {
            return true;
        }
        v vVar = this.G0;
        return vVar != null && vVar.m();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public v k0() {
        if (this.G0 == null) {
            v vVar = new v(this, findViewById(R.id.paste_layout), findViewById(R.id.paste_layout_safe_area));
            this.G0 = vVar;
            vVar.D();
        }
        return this.G0;
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        C2065l c2065l = this.E0;
        if (c2065l == null || !c2065l.i3()) {
            finish();
        }
    }

    @Override // com.alphainventor.filemanager.activity.b, com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) throws Throwable {
        Fragment fragmentJ0;
        super.onCreate(bundle);
        setContentView(R.layout.activity_archive);
        if (Q.N1() && z.v(this)) {
            ax.p006a3.v.r(getWindow(), -16777216);
        }
        this.F0 = new h(this, (ViewGroup) findViewById(R.id.toolbar_container));
        Uri uri = getIntent().hasExtra("open_uri") ? (Uri) getIntent().getParcelableExtra("open_uri") : null;
        Uri data = getIntent().hasExtra("data_uri") ? (Uri) getIntent().getParcelableExtra("data_uri") : getIntent().getData();
        this.I0 = bundle != null;
        if (bundle != null && (fragmentJ0 = getSupportFragmentManager().j0(R.id.fragment_container)) != null) {
            getSupportFragmentManager().o().q(fragmentJ0).j();
        }
        n0();
        if (uri != null && k.h(uri.getScheme(), f.c1)) {
            C6240o c6240oE = C2017x.e(k.a(uri).d());
            if (c6240oE.a()) {
                String strE0 = ((C6227b) c6240oE.u()).E0();
                U0(strE0, data, 4, uri, c6240oE);
                this.F0.j(strE0);
                if (getIntent().getBooleanExtra("open_uri_release", false)) {
                    c6240oE.S(false);
                    return;
                }
                return;
            }
        }
        ArchiveActivity archiveActivity = this;
        if (data == null) {
            Toast.makeText(this, R.string.error_loading, 1).show();
            finish();
            return;
        }
        String path = data.getPath();
        String scheme = data.getScheme();
        if ("content".equals(scheme)) {
            if (!MyFileProvider.B(data)) {
                W wX = B.x(this, data, "zip");
                String strA = wX.a();
                new a(this, data, strA, wX.b, uri).h(new Void[0]);
                this.F0.j(strA);
                return;
            }
            File fileM = MyFileProvider.m(data);
            String name = fileM.getName();
            if (fileM.exists() || !W0(fileM)) {
                V0(name, fileM, 1, uri);
            } else {
                try {
                    try {
                        try {
                            b bVar = new b(this, data, C6249y.J0(fileM.getAbsolutePath()), uri);
                            archiveActivity = this;
                            uri = uri;
                            bVar.h(new Void[0]);
                        } catch (C1966j unused) {
                            archiveActivity = this;
                            uri = uri;
                            V0(name, fileM, 1, uri);
                        }
                    } catch (C1966j unused2) {
                        archiveActivity = this;
                    }
                } catch (C1966j unused3) {
                }
            }
            archiveActivity.F0.j(name);
            return;
        }
        if ("file".equals(scheme) && d0.B(path)) {
            String strH = d0.h(path);
            V0(strH, new File(path), 1, uri);
            archiveActivity.F0.j(strH);
        } else {
            if (!k.h(scheme, f.c1)) {
                ax.p302sb.c.h().d("AA").h(data.toString()).i();
                Toast.makeText(this, R.string.error_loading, 1).show();
                finish();
                return;
            }
            C2461b.g("Archive LocationUri");
            C6240o c6240oE2 = C2017x.e(k.a(data).d());
            if (!c6240oE2.a()) {
                Toast.makeText(this, R.string.error_loading, 1).show();
                finish();
            } else {
                String strE1 = ((C6227b) c6240oE2.u()).E0();
                archiveActivity.U0(strE1, data, 4, uri, c6240oE2);
                archiveActivity.F0.j(strE1);
            }
        }
    }

    @Override // com.alphainventor.filemanager.activity.b, com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ArrayList<ParcelFileDescriptor> arrayList = this.H0;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            ParcelFileDescriptor parcelFileDescriptor = arrayList.get(i);
            i++;
            try {
                parcelFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // ax.p210n.ActivityC2164c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (X0() == null || !X0().l4(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        C2065l c2065l = this.E0;
        if (c2065l == null) {
            finish();
            return true;
        }
        c2065l.Ca("toolbar_back");
        return true;
    }

    @Override // com.alphainventor.filemanager.activity.b, com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void s0() {
        h0().a();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void t0(String str) {
        finish();
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void u0(Bookmark bookmark) {
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void w0(f fVar, int i, String str, boolean z) {
    }

    @Override // com.alphainventor.filemanager.activity.b
    public void z0(f fVar, int i, String str, boolean z) {
        if (z) {
            M0();
        }
    }
}
