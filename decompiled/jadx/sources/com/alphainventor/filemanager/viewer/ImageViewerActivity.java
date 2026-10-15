package com.alphainventor.filemanager.viewer;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.p;
import androidx.fragment.app.Fragment;
import ax.C3.C0677d;
import ax.C3.z;
import ax.Z2.o;
import ax.Z2.x;
import ax.p006a3.Q;
import ax.p006a3.v;
import ax.p038c3.t;
import ax.p054d3.T;
import ax.p070e3.C1959c;
import ax.p070e3.C1966j;
import ax.p086f3.A;
import ax.p086f3.AbstractC2005k;
import ax.p086f3.B;
import ax.p086f3.C2014u;
import ax.p086f3.C2016w;
import ax.p086f3.C2017x;
import ax.p086f3.EnumC2019z;
import ax.p086f3.K;
import ax.p102g3.AbstractC2067n;
import ax.p102g3.M;
import ax.p130i.r;
import ax.p210n.ActivityC2164c;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import ax.p409z6.C3772df;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6227b;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.file.C6249y;
import com.android.ex.photo.PhotoViewPager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class ImageViewerActivity extends com.alphainventor.filemanager.activity.a implements com.android.ex.photo.f.g, ax.V2.a {
    public static int M0 = 36102;
    public static String N0 = "IMAGE_INFO_KEY";
    boolean A0;
    View B0;
    View C0;
    M D0;
    private AbstractC6239n F0;
    private long G0;
    private boolean H0;
    private String I0;
    private String J0;
    private boolean K0;
    private com.android.ex.photo.f p0;
    private com.android.ex.photo.b q0;
    private z r0;
    List<AbstractC6239n> t0;
    private ax.U2.f u0;
    private int v0;
    C6240o w0;
    int x0;
    C0677d y0;
    View z0;
    private final HashMap<String, Boolean> s0 = new HashMap<>();
    private HashMap<ComponentName, m> E0 = new HashMap<>();
    r L0 = new e(true);

    class a implements AbstractC2067n.s {
        final /* synthetic */ AbstractC6239n a;
        final /* synthetic */ File b;

        a(AbstractC6239n abstractC6239n, File file) {
            this.a = abstractC6239n;
            this.b = file;
        }

        @Override // ax.p102g3.AbstractC2067n.s
        public void a() {
            if (ImageViewerActivity.this.r() == null) {
                return;
            }
            C2014u.h0(ImageViewerActivity.this.r(), this.a.t(), this.b);
        }
    }

    class b implements AbstractC2067n.s {
        final /* synthetic */ AbstractC6239n a;
        final /* synthetic */ File b;

        b(AbstractC6239n abstractC6239n, File file) {
            this.a = abstractC6239n;
            this.b = file;
        }

        @Override // ax.p102g3.AbstractC2067n.s
        public void a() {
            if (ImageViewerActivity.this.r() == null) {
                return;
            }
            C2014u.m0(ImageViewerActivity.this.r(), this.a.t(), this.b);
        }
    }

    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.Z2.h.b.values().length];
            a = iArr;
            try {
                iArr[ax.Z2.h.b.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.Z2.h.b.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.Z2.h.b.CANCELLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    class d implements z.d {
        d() {
        }

        @Override // ax.C3.z.d
        public void a(boolean z) {
            if (z) {
                ImageViewerActivity.this.p0.E0(false);
            }
            if (ImageViewerActivity.this.p0.s()) {
                ImageViewerActivity.this.p0.n();
            }
            if (ImageViewerActivity.this.getWindow() != null) {
                ImageViewerActivity.this.getWindow().clearFlags(128);
            }
        }

        @Override // ax.C3.z.d
        public void b(boolean z) {
            if (z) {
                ImageViewerActivity.this.p0.E0(false);
            }
            if (ImageViewerActivity.this.getWindow() != null) {
                ImageViewerActivity.this.getWindow().clearFlags(128);
            }
        }

        @Override // ax.C3.z.d
        public void c(boolean z) {
            if (z) {
                ImageViewerActivity.this.p0.E0(true);
            }
            ImageViewerActivity.this.y0();
            if (ImageViewerActivity.this.getWindow() != null) {
                ImageViewerActivity.this.getWindow().addFlags(128);
            }
        }
    }

    class e extends r {
        e(boolean z) {
            super(z);
        }

        @Override // ax.p130i.r
        public void c() {
            ImageViewerActivity.this.H0 = false;
            ImageViewerActivity.this.n();
        }

        @Override // ax.p130i.r
        public void d() {
            ImageViewerActivity.this.p0.e0();
            ImageViewerActivity.this.H0 = false;
            ImageViewerActivity.this.n();
        }

        @Override // ax.p130i.r
        public void f(ax.p130i.b bVar) {
            ImageViewerActivity.this.H0 = true;
        }
    }

    class f extends ax.p182l3.c {
        f(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            ImageViewerActivity.this.z0(view.getId());
        }
    }

    class g implements C0677d.f {
        g() {
        }

        @Override // ax.C3.C0677d.f
        public boolean a(int i) {
            ImageViewerActivity.this.z0(i);
            return true;
        }
    }

    class h implements C0677d.g {
        h() {
        }

        @Override // ax.C3.C0677d.g
        public void a() {
            boolean z;
            Intent intentW0;
            m mVarQ0;
            ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
            AbstractC6239n abstractC6239nN0 = ImageViewerActivity.this.n0(((com.alphainventor.filemanager.viewer.b) imageViewerActivity.g()).V0());
            boolean zH = abstractC6239nN0 != null ? B.H(abstractC6239nN0) : false;
            ImageViewerActivity.this.y0.t(R.id.menu_open_with, zH);
            if (!zH || (intentW0 = com.alphainventor.filemanager.viewer.b.W0(imageViewerActivity, abstractC6239nN0, true)) == null || (mVarQ0 = ImageViewerActivity.this.q0(intentW0)) == null) {
                z = false;
            } else {
                ImageViewerActivity.this.y0.s(R.id.menu_open_default, mVarQ0.a);
                ImageViewerActivity.this.y0.q(R.id.menu_open_default, mVarQ0.b);
                z = true;
            }
            ImageViewerActivity.this.y0.t(R.id.menu_open_default, z);
            ImageViewerActivity.this.y0.t(R.id.menu_slideshow, true);
            ImageViewerActivity.this.y0.t(R.id.menu_edit, false);
        }

        @Override // ax.C3.C0677d.g
        public void b(boolean z) {
            ImageViewerActivity.this.p0.a(z);
        }
    }

    class i implements ax.Z2.h.a {
        final /* synthetic */ AbstractC6239n a;

        i(AbstractC6239n abstractC6239n) {
            this.a = abstractC6239n;
        }

        @Override // ax.Z2.h.a
        public void a(ax.Z2.h.b bVar, String str, String str2, ArrayList<String> arrayList, Object obj) {
            if (bVar != ax.Z2.h.b.SUCCESS) {
                ImageViewerActivity.this.M0(str, 0);
                return;
            }
            int iIndexOf = ImageViewerActivity.this.t0.indexOf(this.a);
            ImageViewerActivity.this.t0.remove(this.a);
            if (ImageViewerActivity.this.t0.isEmpty()) {
                Toast.makeText(ImageViewerActivity.this, str, 1).show();
                ImageViewerActivity.this.finish();
                return;
            }
            ((com.alphainventor.filemanager.viewer.b) ImageViewerActivity.this.g()).e1();
            if (arrayList.size() <= 0 || iIndexOf < 0) {
                ImageViewerActivity.this.M0(str, -1);
            } else {
                ImageViewerActivity.this.N0(this.a, iIndexOf, str, arrayList);
            }
        }

        @Override // ax.Z2.h.a
        public void b() {
            ImageViewerActivity.this.g().t0();
        }
    }

    class j implements DialogInterface.OnCancelListener {
        j() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            ImageViewerActivity.this.g().t0();
        }
    }

    class k extends ax.p182l3.c {
        final /* synthetic */ List Y;
        final /* synthetic */ int Z;
        final /* synthetic */ AbstractC6239n k0;

        class a implements ax.Z2.h.a {
            a() {
            }

            @Override // ax.Z2.h.a
            public void a(ax.Z2.h.b bVar, String str, String str2, ArrayList<String> arrayList, Object obj) {
                if (bVar != ax.Z2.h.b.SUCCESS) {
                    ImageViewerActivity.this.M0(str, 0);
                    return;
                }
                k kVar = k.this;
                int size = kVar.Z > ImageViewerActivity.this.t0.size() ? ImageViewerActivity.this.t0.size() : k.this.Z;
                k kVar2 = k.this;
                ImageViewerActivity.this.t0.add(size, kVar2.k0);
                ImageViewerActivity.this.g().A0(size);
                ((com.alphainventor.filemanager.viewer.b) ImageViewerActivity.this.g()).e1();
                ImageViewerActivity.this.M0(str, -1);
            }

            @Override // ax.Z2.h.a
            public void b() {
            }
        }

        k(List list, int i, AbstractC6239n abstractC6239n) {
            this.Y = list;
            this.Z = i;
            this.k0 = abstractC6239n;
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            ax.U2.a.k().o("menu_image_viewer", "undo_delete").c("loc", "image_viewer").e();
            ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
            x.m(imageViewerActivity, imageViewerActivity.w0, this.Y, new a());
        }
    }

    class l implements ax.Z2.h.a {
        final /* synthetic */ AbstractC2067n.s a;

        l(AbstractC2067n.s sVar) {
            this.a = sVar;
        }

        @Override // ax.Z2.h.a
        public void a(ax.Z2.h.b bVar, String str, String str2, ArrayList<String> arrayList, Object obj) {
            int i = c.a[bVar.ordinal()];
            if (i == 1) {
                this.a.a();
            } else if (i == 2 || i == 3) {
                Toast.makeText(ImageViewerActivity.this.r(), str, 1).show();
            }
        }

        @Override // ax.Z2.h.a
        public void b() {
        }
    }

    static class m {
        CharSequence a;
        Drawable b;

        m(CharSequence charSequence, Drawable drawable) {
            this.a = charSequence;
            this.b = drawable;
        }
    }

    class n extends q<Void, Integer, Boolean> {
        private AbstractC6239n h;

        n(AbstractC6239n abstractC6239n) {
            super(q.e.HIGH);
            this.h = abstractC6239n;
        }

        @Override // ax.p406z3.q
        protected void o() {
        }

        @Override // ax.p406z3.q
        protected void r() {
            super.r();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            return Boolean.valueOf(ImageViewerActivity.this.F0(this.h));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (bool.booleanValue()) {
                ((com.alphainventor.filemanager.viewer.b) ImageViewerActivity.this.g()).f1();
            } else {
                Toast.makeText(ImageViewerActivity.this, R.string.error, 0).show();
            }
        }
    }

    private void D0() {
        if (g() == null || g().V() == null) {
            return;
        }
        if (!g().s()) {
            g().n();
        }
        this.r0.z();
    }

    private void E0(AbstractC6239n abstractC6239n) {
        String str;
        if (!(abstractC6239n instanceof C6249y)) {
            if (abstractC6239n == null) {
                str = "loc:null";
            } else {
                str = "loc:" + abstractC6239n.Q().H();
            }
            C2461b.g(str);
            return;
        }
        if (!(this.w0.u() instanceof C6248x)) {
            C2461b.f();
            return;
        }
        K kC0 = ((C6249y) abstractC6239n).C0();
        if (kC0 == null || kC0.e() == null) {
            return;
        }
        new n(abstractC6239n).i(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public boolean F0(AbstractC6239n abstractC6239n) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            try {
                K kC0 = ((C6249y) abstractC6239n).C0();
                if (kC0 == null || kC0.e() == null || !l0(kC0)) {
                    return false;
                }
                long jQ = abstractC6239n.q();
                C6248x c6248x = (C6248x) this.w0.u();
                ParcelFileDescriptor parcelFileDescriptorC0 = c6248x.C0((C6249y) abstractC6239n);
                if (parcelFileDescriptorC0 == null) {
                    if (parcelFileDescriptorC0 != null) {
                        try {
                            parcelFileDescriptorC0.close();
                        } catch (IOException unused) {
                        }
                    }
                    return false;
                }
                ax.p387y0.a aVar = new ax.p387y0.a(parcelFileDescriptorC0.getFileDescriptor());
                if (aVar.l("Orientation", 1) == 0) {
                    aVar.f0();
                }
                aVar.h0(-90);
                aVar.i0();
                c6248x.d(abstractC6239n, jQ);
                c6248x.R1(abstractC6239n);
                ax.p358w3.d.B(this, this.w0.f1(abstractC6239n));
                try {
                    parcelFileDescriptorC0.close();
                } catch (IOException unused2) {
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (0 != 0) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException unused3) {
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        if (0 != 0) {
            parcelFileDescriptor.close();
        }
        throw th;
    }

    private void G0() {
        if (!u0()) {
            C2461b.f();
            Toast.makeText(this, R.string.error, 1).show();
        } else {
            this.K0 = true;
            C2014u.s0(this, Uri.fromFile(new File(this.I0)), this.J0);
            finish();
        }
    }

    private void H0(AbstractC6239n abstractC6239n) {
        ax.U2.a.k().o("menu_image_viewer", "set_as").e();
        if (B.H(abstractC6239n)) {
            C2014u.g0(r(), (AbstractC2005k) abstractC6239n);
            return;
        }
        File fileA0 = abstractC6239n.a0();
        if (!B.G(fileA0, abstractC6239n)) {
            k0(abstractC6239n, new a(abstractC6239n, fileA0));
        } else {
            t.b().e(fileA0);
            C2014u.h0(r(), abstractC6239n.t(), fileA0);
        }
    }

    private void J0(AbstractC6239n abstractC6239n) {
        this.F0 = abstractC6239n;
        this.G0 = abstractC6239n.q();
    }

    private void K0(AbstractC6239n abstractC6239n) {
        ax.U2.a.k().o("menu_image_viewer", "share").c("loc", "image_viewer").c("type", "file").e();
        if (B.H(abstractC6239n)) {
            C2014u.j0(r(), (AbstractC2005k) abstractC6239n);
            return;
        }
        File fileA0 = abstractC6239n.a0();
        if (!B.G(fileA0, abstractC6239n)) {
            k0(abstractC6239n, new b(abstractC6239n, fileA0));
        } else {
            t.b().e(fileA0);
            C2014u.m0(r(), abstractC6239n.t(), fileA0);
        }
    }

    private void L0(AbstractC6239n abstractC6239n) {
        g().b(true);
        if (g().s()) {
            return;
        }
        g().n();
    }

    private void O0(AbstractC6239n abstractC6239n) {
        if (g() == null || g().V() == null) {
            return;
        }
        if (!g().s()) {
            g().n();
        }
        this.r0.D();
    }

    private boolean P0(AbstractC6239n abstractC6239n) {
        if (B.H(abstractC6239n)) {
            return true;
        }
        Q.r0();
        return false;
    }

    private void g0() {
        f fVar = new f(400L);
        this.y0 = new C0677d(r(), findViewById(R.id.bottom_menu_layout), null);
        if (u0()) {
            this.y0.d(R.id.menu_save, R.string.menu_save, R.drawable.ic_save, fVar);
            this.y0.d(R.id.menu_info, R.string.menu_info, R.drawable.ic_info_outline, fVar);
            this.y0.d(R.id.menu_set_as, R.string.set_as, R.drawable.ic_ab_wallpaper, fVar);
            return;
        }
        this.y0.d(R.id.menu_share, R.string.menu_share, R.drawable.ic_share, fVar);
        this.y0.d(R.id.menu_rotate, R.string.menu_rotate, R.drawable.ic_rotate_90_ccw, fVar);
        this.y0.d(R.id.menu_delete, R.string.menu_delete, R.drawable.ic_delete, fVar);
        this.y0.d(R.id.menu_info, R.string.menu_info, R.drawable.ic_info_outline, fVar);
        this.y0.e();
        this.y0.l(R.menu.image_viewer);
        this.y0.n(new g());
        this.y0.x(new h());
    }

    private void h0() {
        AbstractC6239n abstractC6239n = this.F0;
        if (abstractC6239n == null) {
            return;
        }
        if (abstractC6239n instanceof AbstractC2005k) {
            try {
                if (C2017x.e(abstractC6239n.S()).a1(this.F0.E()).q() != this.G0) {
                    ((com.alphainventor.filemanager.viewer.b) g()).f1();
                }
            } catch (C1966j unused) {
            }
        }
        this.F0 = null;
        this.G0 = 0L;
    }

    private void j0(AbstractC6239n abstractC6239n) {
        ax.U2.a.k().o("menu_image_viewer", "delete").c("loc", "image_viewer").c("type", "file").e();
        ArrayList arrayList = new ArrayList();
        arrayList.add(abstractC6239n);
        boolean z = B.a(arrayList) && ax.U2.f.m(abstractC6239n.Q());
        g().K();
        o.m(this.w0, arrayList, 0, z, this, false, new i(abstractC6239n), new j());
    }

    private void k0(AbstractC6239n abstractC6239n, AbstractC2067n.s sVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(abstractC6239n);
        ax.Z2.r rVarL = ax.Z2.r.l();
        rVarL.k(this.w0, arrayList, true, new l(sVar));
        try {
            Z(rVarL, true);
        } catch (C1959c unused) {
            Toast.makeText(this, R.string.error, 0).show();
        }
    }

    private boolean l0(K k2) {
        String strE = k2.e();
        if (this.s0.containsKey(strE)) {
            return this.s0.get(strE).booleanValue();
        }
        boolean zT = B.t(k2);
        this.s0.put(strE, Boolean.valueOf(zT));
        return zT;
    }

    private void m0(AbstractC6239n abstractC6239n) {
        if (B.H(abstractC6239n)) {
            try {
                C2014u.o0(this, C2014u.e(this, (AbstractC2005k) abstractC6239n, false));
            } catch (ActivityNotFoundException unused) {
                M0(getString(R.string.no_apps_to_open_file), -1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AbstractC6239n n0(Uri uri) {
        if (uri == null) {
            return null;
        }
        for (AbstractC6239n abstractC6239n : this.t0) {
            if (uri.toString().equals(abstractC6239n.R())) {
                return abstractC6239n;
            }
        }
        return null;
    }

    private void p0(int i2) {
        View viewJ = i2 == 20 ? this.y0.j() : o0();
        if (viewJ != null) {
            if (t0()) {
                g().n();
            } else {
                g().u0();
            }
            viewJ.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public m q0(Intent intent) {
        ComponentName component = intent.getComponent();
        if (this.E0.containsKey(component)) {
            return this.E0.get(component);
        }
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentActivities.get(0);
        CharSequence charSequenceLoadLabel = resolveInfo.activityInfo.loadLabel(packageManager);
        Drawable drawableN = ax.p038c3.h.n(packageManager, resolveInfo, 0);
        if (drawableN != null) {
            int iE = ax.p406z3.B.e(this, C0677d.n);
            drawableN = new BitmapDrawable(getResources(), ax.p406z3.z.f(drawableN, iE, iE));
        }
        m mVar = new m(charSequenceLoadLabel, drawableN);
        this.E0.put(component, mVar);
        return mVar;
    }

    private boolean r0(ViewGroup viewGroup, View view) {
        return ax.p406z3.z.r(viewGroup, view);
    }

    private boolean s0(String str) {
        return "webp".equals(str) || "png".equals(str) || "jpg".equals(str) || "jpeg".equals(str);
    }

    private boolean t0() {
        return this.y0.k() != 0;
    }

    private boolean u0() {
        return this.I0 != null;
    }

    private void v0(AbstractC6239n abstractC6239n) {
        Intent intentW0 = com.alphainventor.filemanager.viewer.b.W0(this, abstractC6239n, false);
        if (intentW0 != null && C2014u.U(intentW0)) {
            ((com.alphainventor.filemanager.viewer.b) g()).r(Uri.parse(abstractC6239n.R()), true);
        } else if (intentW0 != null) {
            try {
                C2014u.o0(this, intentW0);
                J0(abstractC6239n);
            } catch (ActivityNotFoundException | NullPointerException | SecurityException e2) {
                Toast.makeText(this, R.string.error, 1).show();
                ax.p302sb.c.h().g().b("ImageViewer openDefault").m(e2).i();
            }
        }
    }

    private void w0(AbstractC2005k abstractC2005k, String str, String str2, boolean z, boolean z2) {
        T.b4(j(), ax.c3.c.a.IN_IMAGE_VIEWER, T.O3(abstractC2005k), str, str2, z, z2);
        J0(abstractC2005k);
    }

    private void x0(AbstractC6239n abstractC6239n) {
        if (isFinishing()) {
            return;
        }
        String strT = abstractC6239n.t();
        if (!C2016w.a(this, abstractC6239n, strT, false)) {
            Toast.makeText(this, R.string.no_apps_to_open_file, 1).show();
        } else if (B.H(abstractC6239n)) {
            w0((AbstractC2005k) abstractC6239n, strT, strT, true, true);
        } else {
            C2461b.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0() {
        AbstractC6239n abstractC6239nN0;
        Uri uriV0 = ((com.alphainventor.filemanager.viewer.b) g()).V0();
        if (uriV0 != null && (abstractC6239nN0 = n0(uriV0)) != null && this.r0.x() && !this.r0.w() && B.H(abstractC6239nN0) && abstractC6239nN0.G() == EnumC2019z.VIDEO && A.E(abstractC6239nN0)) {
            this.r0.y();
            try {
                startActivityForResult(C2016w.i(this, (AbstractC2005k) abstractC6239nN0, this.r0.s() ? 0 : this.r0.r(), false), M0);
            } catch (ActivityNotFoundException | NullPointerException | SecurityException e2) {
                Toast.makeText(this, R.string.error, 1).show();
                ax.p302sb.c.h().g().b("PVI:").m(e2).i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean z0(int i2) {
        AbstractC6239n abstractC6239nN0;
        g().u0();
        Uri uriV0 = ((com.alphainventor.filemanager.viewer.b) g()).V0();
        if (uriV0 == null || (abstractC6239nN0 = n0(uriV0)) == null) {
            return false;
        }
        switch (i2) {
            case R.id.menu_delete /* 2131362508 */:
            case R.id.menu_edit /* 2131362509 */:
                if (C6248x.F1(this, abstractC6239nN0, false)) {
                    C6249y c6249y = (C6249y) abstractC6239nN0;
                    R(3, c6249y.C0(), c6249y.N0(), false, true);
                    return true;
                }
                break;
        }
        switch (i2) {
            case R.id.menu_delete /* 2131362508 */:
                j0(abstractC6239nN0);
                return true;
            case R.id.menu_edit /* 2131362509 */:
                m0(abstractC6239nN0);
                return true;
            case R.id.menu_info /* 2131362518 */:
                L0(abstractC6239nN0);
                return true;
            case R.id.menu_open_default /* 2131362524 */:
                v0(abstractC6239nN0);
                return true;
            case R.id.menu_open_with /* 2131362526 */:
                x0(abstractC6239nN0);
                return true;
            case R.id.menu_rotate /* 2131362537 */:
                E0(abstractC6239nN0);
                return true;
            case R.id.menu_save /* 2131362538 */:
                G0();
                return true;
            case R.id.menu_set_as /* 2131362545 */:
                H0(abstractC6239nN0);
                return true;
            case R.id.menu_share /* 2131362547 */:
                K0(abstractC6239nN0);
                return true;
            case R.id.menu_slideshow /* 2131362549 */:
                O0(abstractC6239nN0);
                return true;
            default:
                return false;
        }
    }

    public void A0() {
        if (g() == null) {
            return;
        }
        this.C0.setVisibility(!g().z() && this.A0 ? 0 : 8);
    }

    public void B0() {
        C0();
    }

    void C0() {
        Fragment fragmentK0 = getSupportFragmentManager().k0("dialog");
        if (fragmentK0 != null) {
            androidx.fragment.app.t tVarO = getSupportFragmentManager().o();
            tVarO.q(fragmentK0);
            tVarO.j();
        }
    }

    public void I0(boolean z) {
        if (this.B0 == null || this.C0 == null || g() == null) {
            return;
        }
        this.A0 = z;
        this.B0.setVisibility(z ? 0 : 8);
        A0();
    }

    void M0(CharSequence charSequence, int i2) {
        ax.p406z3.B.T(this.z0, charSequence, i2).a0();
    }

    void N0(AbstractC6239n abstractC6239n, int i2, CharSequence charSequence, List<String> list) {
        ax.p406z3.B.P(this.z0, charSequence, 0, R.string.menu_undo, false, new k(list, i2, abstractC6239n)).a0();
    }

    @Override // com.android.ex.photo.f.g
    public boolean c() {
        return this.r0.u();
    }

    @Override // com.android.ex.photo.f.g
    public void d(int i2) {
        if (i2 == 1) {
            this.r0.p();
        }
    }

    @Override // com.android.ex.photo.f.g
    public void e() {
        this.r0.q();
    }

    @Override // com.android.ex.photo.f.g
    public void f() {
        AbstractC6239n abstractC6239nN0;
        Uri uriV0 = ((com.alphainventor.filemanager.viewer.b) g()).V0();
        if (uriV0 == null || (abstractC6239nN0 = n0(uriV0)) == null) {
            return;
        }
        if (abstractC6239nN0.G() != EnumC2019z.VIDEO || P0(abstractC6239nN0)) {
            this.y0.t(R.id.menu_info, true);
        } else {
            this.y0.t(R.id.menu_info, false);
        }
        if (!u0()) {
            if (this.w0.M(abstractC6239nN0)) {
                this.y0.o(R.id.menu_delete, true);
            } else {
                this.y0.o(R.id.menu_delete, false);
            }
            this.y0.t(R.id.menu_rotate, Q.E1() && (abstractC6239nN0 instanceof C6249y) && abstractC6239nN0.G() == EnumC2019z.IMAGE && s0(abstractC6239nN0.y()) && l0(((C6249y) abstractC6239nN0).C0()));
        }
        y0();
    }

    @Override // ax.p210n.ActivityC2164c, android.app.Activity
    public View findViewById(int i2) {
        return getDelegate().l(i2);
    }

    @Override // com.android.ex.photo.f.g
    public com.android.ex.photo.f g() {
        return this.p0;
    }

    protected com.android.ex.photo.f i0() {
        com.alphainventor.filemanager.viewer.c.a aVarA = com.alphainventor.filemanager.viewer.c.b().a(getIntent().getStringExtra(N0));
        if (aVarA != null) {
            this.t0 = aVarA.a;
            this.x0 = aVarA.b;
        }
        getIntent().putExtra("photo_index", this.x0);
        return new com.alphainventor.filemanager.viewer.b(this, this.t0, this.w0);
    }

    @Override // com.android.ex.photo.f.g
    public Fragment j() {
        return this.D0;
    }

    @Override // com.android.ex.photo.f.g
    public com.android.ex.photo.a l() {
        if (this.q0 == null) {
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
            this.q0 = new com.android.ex.photo.b(getSupportActionBar());
        }
        return this.q0;
    }

    @Override // com.android.ex.photo.f.g
    public void n() {
        com.android.ex.photo.f fVar = this.p0;
        if (fVar == null || this.H0) {
            return;
        }
        this.L0.j(fVar.b0());
    }

    View o0() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTouchscreenBlocksFocus(false);
        View childAt = toolbar.getChildAt(0);
        if (!(childAt instanceof p)) {
            C2461b.e("not work anymore");
            return childAt;
        }
        if (toolbar.getNavigationContentDescription() == null || !toolbar.getNavigationContentDescription().equals(childAt.getContentDescription())) {
            C2461b.e("not work anymore");
            return childAt;
        }
        childAt.setId(android.R.id.home);
        return childAt;
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        this.p0.d0(i2, i3, intent);
        if (i2 == M0) {
            if (i3 == -1) {
                D0();
            } else if (i3 == 0) {
                this.r0.p();
                if (this.p0.s()) {
                    this.p0.n();
                }
            }
        }
    }

    @Override // ax.p210n.ActivityC2164c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        PhotoViewPager photoViewPagerV = g().V();
        if (photoViewPagerV != null) {
            int currentItem = photoViewPagerV.getCurrentItem();
            photoViewPagerV.setAdapter(photoViewPagerV.getAdapter());
            photoViewPagerV.setCurrentItem(currentItem);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        M m2 = (M) getSupportFragmentManager().k0("headless_fragment");
        this.D0 = m2;
        if (m2 == null) {
            this.D0 = M.U2("ImageViewerActivity");
            getSupportFragmentManager().o().e(this.D0, "headless_fragment").i();
        }
        this.u0 = (ax.U2.f) getIntent().getSerializableExtra("location");
        this.v0 = getIntent().getIntExtra("location_key", 0);
        if (this.u0 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(HttpUrl.FRAGMENT_ENCODE_SET);
            sb.append(getIntent().getExtras() == null);
            sb.append(",key:");
            sb.append(this.v0);
            ax.p302sb.c.h().d("ImageViewer no location").h(sb.toString()).i();
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        this.I0 = getIntent().getStringExtra("extra_temp_file_path");
        this.J0 = getIntent().getStringExtra("extra_temp_file_type");
        C6240o c6240oD = C2017x.d(this.u0, this.v0);
        this.w0 = c6240oD;
        c6240oD.V();
        com.android.ex.photo.f fVarI0 = i0();
        this.p0 = fVarI0;
        fVarI0.f0(bundle);
        this.r0 = new z(findViewById(R.id.slideshow_options), this.p0.V(), new d());
        List<AbstractC6239n> list = this.t0;
        if (list == null || list.size() == 0) {
            finish();
            return;
        }
        if (!this.w0.a()) {
            if ((this.w0.u() instanceof C6227b) && !((C6227b) this.w0.u()).M0()) {
                ax.p302sb.b bVarB = ax.p302sb.c.h().g().b("IMAGEVIEWER NOT CONNECTED OPERATOR ARCHIVE");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("location:");
                sb2.append(this.u0.H());
                sb2.append(",");
                sb2.append(this.v0);
                sb2.append(",");
                sb2.append(bundle != null);
                bVarB.h(sb2.toString()).i();
                Toast.makeText(this, R.string.error, 1).show();
                finish();
                return;
            }
            ax.p302sb.c.h().g().b("IMAGEVIEWER NOT CONNECTED OPERATOR").h("location:" + this.u0.H()).i();
        }
        g0();
        this.z0 = findViewById(R.id.snackbar_container);
        this.B0 = findViewById(R.id.btn_motion_play);
        this.C0 = findViewById(R.id.buttons_line);
        getOnBackPressedDispatcher().h(this, this.L0);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        com.android.ex.photo.f fVar = this.p0;
        return (fVar != null && fVar.g0(menu)) || super.onCreateOptionsMenu(menu);
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        com.android.ex.photo.f fVar = this.p0;
        if (fVar != null) {
            fVar.h0();
        }
        C6240o c6240o = this.w0;
        if (c6240o != null) {
            c6240o.S(false);
        }
        try {
            super.onDestroy();
        } catch (IllegalArgumentException unused) {
        }
        if (!u0() || this.K0) {
            return;
        }
        try {
            new File(this.I0).delete();
        } catch (Exception unused2) {
        }
    }

    @Override // ax.p210n.ActivityC2164c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        View currentFocus;
        if (this.r0.u()) {
            return super.onKeyDown(i2, keyEvent);
        }
        this.r0.p();
        if (keyEvent.isMetaPressed() || keyEvent.isShiftPressed() || keyEvent.isAltPressed()) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (i2 == 112) {
            z0(R.id.menu_delete);
            return true;
        }
        switch (i2) {
            case 19:
            case 20:
                if (g().s()) {
                    p0(i2);
                    return true;
                }
                View currentFocus2 = getCurrentFocus();
                if (currentFocus2 == null) {
                    p0(i2);
                    return true;
                }
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                ViewGroup viewGroup = (ViewGroup) findViewById(R.id.bottom_menu_layout);
                if (r0(toolbar, currentFocus2)) {
                    if (i2 == 20) {
                        g().n();
                        return true;
                    }
                } else {
                    if (!r0(viewGroup, currentFocus2)) {
                        p0(i2);
                        return true;
                    }
                    if (i2 == 19) {
                        g().n();
                        return true;
                    }
                }
                break;
            case C3772df.zzm /* 21 */:
            case 22:
                boolean zOnKeyDown = super.onKeyDown(i2, keyEvent);
                if (!t0() && (currentFocus = getCurrentFocus()) != null) {
                    Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
                    ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.bottom_menu_layout);
                    if (r0(toolbar2, currentFocus) || r0(viewGroup2, currentFocus)) {
                        g().u0();
                    }
                }
                return zOnKeyDown;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.p0.l0(menuItem) || super.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return z0(menuItem.getItemId());
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        this.p0.m0();
        super.onPause();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        com.android.ex.photo.f fVar = this.p0;
        return (fVar != null && fVar.n0(menu)) || super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.p0.o0();
        h0();
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.p0.p0(bundle);
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.p0.q0();
        if (Q.N1()) {
            v.u(getWindow(), -1157627904);
            v.r(getWindow(), -1157627904);
        }
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        this.p0.r0();
        this.r0.p();
        super.onStop();
    }

    @Override // com.android.ex.photo.f.g
    public void p(boolean z) {
        if (getSupportActionBar() == null || this.y0 == null) {
            C2461b.f();
            return;
        }
        if (z) {
            getSupportActionBar().m();
            this.y0.y(8);
        } else {
            getSupportActionBar().K();
            this.y0.y(0);
            this.y0.A();
            if (g() != null && g().z()) {
                g().b(false);
            }
            this.r0.p();
        }
        A0();
    }

    @Override // com.android.ex.photo.f.g
    public boolean q() {
        return this.r0.v();
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.V2.a
    public ActivityC2164c r() {
        return this;
    }
}
