package com.alphainventor.filemanager.viewer;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
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
import android.view.ViewParent;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import ax.D1.p;
import ax.D1.u;
import ax.E1.P;
import ax.G1.q;
import ax.H1.O;
import ax.I1.C0711b;
import ax.I1.C0718i;
import ax.J1.AbstractC0726g;
import ax.J1.C0734o;
import ax.J1.C0736q;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.EnumC0738t;
import ax.J1.F;
import ax.J1.r;
import ax.K1.AbstractC0762l;
import ax.K1.C;
import ax.P5.C1452Re;
import ax.p045d2.v;
import ax.p045d2.x;
import ax.p087g2.t;
import ax.p112i.n;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5827a;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.C5845t;
import com.android.ex.photo.PhotoViewPager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class ImageViewerActivity extends com.alphainventor.filemanager.activity.a implements com.android.ex.photo.f.g, ax.p352z1.a {
    public static int e1 = 36102;
    public static String f1 = "IMAGE_INFO_KEY";
    private com.android.ex.photo.f N0;
    private com.android.ex.photo.b O0;
    private t P0;
    List<AbstractC5838l> R0;
    private ax.p338y1.f S0;
    private int T0;
    C5839m U0;
    int V0;
    ax.p087g2.d W0;
    View X0;
    C Y0;
    private AbstractC5838l a1;
    private long b1;
    private boolean c1;
    private final HashMap<String, Boolean> Q0 = new HashMap<>();
    private HashMap<ComponentName, l> Z0 = new HashMap<>();
    n d1 = new d(true);

    class a implements AbstractC0762l.p {
        final /* synthetic */ AbstractC5838l a;
        final /* synthetic */ File b;

        a(AbstractC5838l abstractC5838l, File file) {
            this.a = abstractC5838l;
            this.b = file;
        }

        @Override // ax.K1.AbstractC0762l.p
        public void a() {
            if (ImageViewerActivity.this.P() == null) {
                return;
            }
            C0734o.f0(ImageViewerActivity.this.P(), this.a.s(), this.b);
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.D1.f.b.values().length];
            a = iArr;
            try {
                iArr[ax.D1.f.b.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.D1.f.b.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.D1.f.b.CANCELLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    class c implements t.d {
        c() {
        }

        @Override // ax.g2.t.d
        public void a() {
            if (ImageViewerActivity.this.getWindow() != null) {
                ImageViewerActivity.this.getWindow().clearFlags(128);
            }
        }

        @Override // ax.g2.t.d
        public void b() {
            ImageViewerActivity.this.H1();
            if (ImageViewerActivity.this.getWindow() != null) {
                ImageViewerActivity.this.getWindow().addFlags(128);
            }
        }

        @Override // ax.g2.t.d
        public void c() {
            if (ImageViewerActivity.this.N0.q()) {
                ImageViewerActivity.this.N0.m();
            }
            if (ImageViewerActivity.this.getWindow() != null) {
                ImageViewerActivity.this.getWindow().clearFlags(128);
            }
        }
    }

    class d extends n {
        d(boolean z) {
            super(z);
        }

        @Override // ax.p112i.n
        public void c() {
            ImageViewerActivity.this.c1 = false;
            ImageViewerActivity.this.G();
        }

        @Override // ax.p112i.n
        public void d() {
            ImageViewerActivity.this.N0.d0();
            ImageViewerActivity.this.c1 = false;
            ImageViewerActivity.this.G();
        }

        @Override // ax.p112i.n
        public void f(ax.p112i.b bVar) {
            ImageViewerActivity.this.c1 = true;
        }
    }

    class e extends ax.P1.c {
        e(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            ImageViewerActivity.this.I1(view.getId());
        }
    }

    class f implements ax.g2.d.f {
        f() {
        }

        @Override // ax.g2.d.f
        public boolean a(int i) {
            ImageViewerActivity.this.I1(i);
            return true;
        }
    }

    class g implements ax.g2.d.g {
        g() {
        }

        @Override // ax.g2.d.g
        public void a() {
            Intent intentN0;
            l lVarA1;
            ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
            AbstractC5838l abstractC5838lX1 = ImageViewerActivity.this.x1(((com.alphainventor.filemanager.viewer.a) imageViewerActivity.m()).M0());
            boolean z = false;
            boolean zA = abstractC5838lX1 != null ? C0740v.A(abstractC5838lX1) : false;
            ImageViewerActivity.this.W0.s(R.id.menu_open_with, zA);
            if (zA && (intentN0 = com.alphainventor.filemanager.viewer.a.N0(imageViewerActivity, abstractC5838lX1, true)) != null && (lVarA1 = ImageViewerActivity.this.A1(intentN0)) != null) {
                ImageViewerActivity.this.W0.r(R.id.menu_open_default, lVarA1.a);
                ImageViewerActivity.this.W0.p(R.id.menu_open_default, lVarA1.b);
                z = true;
            }
            ImageViewerActivity.this.W0.s(R.id.menu_open_default, z);
            ImageViewerActivity.this.W0.s(R.id.menu_slideshow, true);
        }

        @Override // ax.g2.d.g
        public void b(boolean z) {
            ImageViewerActivity.this.N0.a(z);
        }
    }

    class h implements ax.D1.f.a {
        final /* synthetic */ AbstractC5838l a;

        h(AbstractC5838l abstractC5838l) {
            this.a = abstractC5838l;
        }

        @Override // ax.D1.f.a
        public void a(ax.D1.f.b bVar, String str, String str2, ArrayList<String> arrayList) {
            if (bVar != ax.D1.f.b.SUCCESS) {
                ImageViewerActivity.this.S1(str, 0);
                return;
            }
            int iIndexOf = ImageViewerActivity.this.R0.indexOf(this.a);
            ImageViewerActivity.this.R0.remove(this.a);
            if (ImageViewerActivity.this.R0.isEmpty()) {
                Toast.makeText(ImageViewerActivity.this, str, 1).show();
                ImageViewerActivity.this.finish();
                return;
            }
            ((com.alphainventor.filemanager.viewer.a) ImageViewerActivity.this.m()).S0();
            if (arrayList.size() <= 0 || iIndexOf < 0) {
                ImageViewerActivity.this.S1(str, -1);
            } else {
                ImageViewerActivity.this.T1(this.a, iIndexOf, str, arrayList);
            }
        }

        @Override // ax.D1.f.a
        public void b() {
        }
    }

    class i extends ax.P1.c {
        final /* synthetic */ List Y;
        final /* synthetic */ int Z;
        final /* synthetic */ AbstractC5838l h0;

        class a implements ax.D1.f.a {
            a() {
            }

            @Override // ax.D1.f.a
            public void a(ax.D1.f.b bVar, String str, String str2, ArrayList<String> arrayList) {
                if (bVar != ax.D1.f.b.SUCCESS) {
                    ImageViewerActivity.this.S1(str, 0);
                    return;
                }
                i iVar = i.this;
                int size = iVar.Z > ImageViewerActivity.this.R0.size() ? ImageViewerActivity.this.R0.size() : i.this.Z;
                i iVar2 = i.this;
                ImageViewerActivity.this.R0.add(size, iVar2.h0);
                ImageViewerActivity.this.m().z0(size);
                ((com.alphainventor.filemanager.viewer.a) ImageViewerActivity.this.m()).S0();
                ImageViewerActivity.this.S1(str, -1);
            }

            @Override // ax.D1.f.a
            public void b() {
            }
        }

        i(List list, int i, AbstractC5838l abstractC5838l) {
            this.Y = list;
            this.Z = i;
            this.h0 = abstractC5838l;
        }

        @Override // ax.P1.c
        public void a(View view) {
            ax.p338y1.a.k().o("menu_image_viewer", "undo_delete").c("loc", "image_viewer").e();
            ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
            u.m(imageViewerActivity, imageViewerActivity.U0, this.Y, new a());
        }
    }

    class j implements ax.D1.f.a {
        final /* synthetic */ AbstractC0762l.p a;

        j(AbstractC0762l.p pVar) {
            this.a = pVar;
        }

        @Override // ax.D1.f.a
        public void a(ax.D1.f.b bVar, String str, String str2, ArrayList<String> arrayList) {
            int i = b.a[bVar.ordinal()];
            if (i == 1) {
                this.a.a();
            } else if (i == 2 || i == 3) {
                Toast.makeText(ImageViewerActivity.this.P(), str, 1).show();
            }
        }

        @Override // ax.D1.f.a
        public void b() {
        }
    }

    class k implements AbstractC0762l.p {
        final /* synthetic */ AbstractC5838l a;
        final /* synthetic */ File b;

        k(AbstractC5838l abstractC5838l, File file) {
            this.a = abstractC5838l;
            this.b = file;
        }

        @Override // ax.K1.AbstractC0762l.p
        public void a() {
            if (ImageViewerActivity.this.P() == null) {
                return;
            }
            C0734o.a0(ImageViewerActivity.this.P(), this.a.s(), this.b);
        }
    }

    static class l {
        CharSequence a;
        Drawable b;

        l(CharSequence charSequence, Drawable drawable) {
            this.a = charSequence;
            this.b = drawable;
        }
    }

    class m extends ax.p045d2.n<Void, Integer, Boolean> {
        private AbstractC5838l h;

        m(AbstractC5838l abstractC5838l) {
            super(ax.d2.n.f.HIGH);
            this.h = abstractC5838l;
        }

        @Override // ax.p045d2.n
        protected void o() {
        }

        @Override // ax.p045d2.n
        protected void r() {
            super.r();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            return Boolean.valueOf(ImageViewerActivity.this.N1(this.h));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (bool.booleanValue()) {
                ((com.alphainventor.filemanager.viewer.a) ImageViewerActivity.this.m()).T0();
            } else {
                Toast.makeText(ImageViewerActivity.this, R.string.error, 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public l A1(Intent intent) {
        ComponentName component = intent.getComponent();
        if (this.Z0.containsKey(component)) {
            return this.Z0.get(component);
        }
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentActivities.get(0);
        CharSequence charSequenceLoadLabel = resolveInfo.activityInfo.loadLabel(packageManager);
        Drawable drawableN = ax.G1.g.n(packageManager, resolveInfo, 0);
        if (drawableN != null) {
            int iE = x.e(this, ax.p087g2.d.m);
            drawableN = new BitmapDrawable(getResources(), v.e(drawableN, iE, iE));
        }
        l lVar = new l(charSequenceLoadLabel, drawableN);
        this.Z0.put(component, lVar);
        return lVar;
    }

    private boolean B1(ViewGroup viewGroup, View view) {
        if (viewGroup == null || view == null) {
            return false;
        }
        ViewParent parent = view.getParent();
        while (viewGroup != parent && parent != null) {
            parent = parent.getParent();
        }
        return parent == viewGroup;
    }

    private boolean C1(String str) {
        return "webp".equals(str) || "png".equals(str) || "jpg".equals(str) || "jpeg".equals(str);
    }

    private boolean D1() {
        return this.W0.k() != 0;
    }

    private void E1(AbstractC5838l abstractC5838l) {
        Intent intentN0 = com.alphainventor.filemanager.viewer.a.N0(this, abstractC5838l, false);
        if (intentN0 != null && C0734o.O(intentN0)) {
            ((com.alphainventor.filemanager.viewer.a) m()).p(Uri.parse(abstractC5838l.Q()), true);
        } else if (intentN0 != null) {
            try {
                C0734o.h0(this, intentN0);
                P1(abstractC5838l);
            } catch (ActivityNotFoundException | NullPointerException | SecurityException e2) {
                Toast.makeText(this, R.string.error, 1).show();
                ax.p221pa.c.h().g().b("ImageViewer openDefault").m(e2).i();
            }
        }
    }

    private void F1(AbstractC0726g abstractC0726g, String str, String str2, boolean z, boolean z2) {
        O.Z3(t(), ax.G1.c.a.IN_IMAGE_VIEWER, O.N3(abstractC0726g), str, str2, z, z2);
        P1(abstractC0726g);
    }

    private void G1(AbstractC5838l abstractC5838l) {
        if (isFinishing()) {
            return;
        }
        String strS = abstractC5838l.s();
        if (!C0736q.a(this, abstractC5838l, strS, false)) {
            Toast.makeText(this, R.string.no_apps_to_open_file, 1).show();
        } else if (C0740v.A(abstractC5838l)) {
            F1((AbstractC0726g) abstractC5838l, strS, strS, true, true);
        } else {
            ax.p045d2.b.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H1() {
        AbstractC5838l abstractC5838lX1;
        Uri uriM0 = ((com.alphainventor.filemanager.viewer.a) m()).M0();
        if (uriM0 != null && (abstractC5838lX1 = x1(uriM0)) != null && this.P0.u() && !this.P0.t() && C0740v.A(abstractC5838lX1) && abstractC5838lX1.E() == EnumC0738t.VIDEO && C0739u.A(abstractC5838lX1)) {
            this.P0.v();
            try {
                startActivityForResult(C0736q.g(this, (AbstractC0726g) abstractC5838lX1, this.P0.q() ? 0 : this.P0.p(), false), e1);
            } catch (ActivityNotFoundException | NullPointerException | SecurityException e2) {
                Toast.makeText(this, R.string.error, 1).show();
                ax.p221pa.c.h().g().b("PVI:").m(e2).i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean I1(int i2) {
        AbstractC5838l abstractC5838lX1;
        m().t0();
        Uri uriM0 = ((com.alphainventor.filemanager.viewer.a) m()).M0();
        if (uriM0 == null || (abstractC5838lX1 = x1(uriM0)) == null) {
            return false;
        }
        switch (i2) {
            case R.id.menu_delete /* 2131362414 */:
            case R.id.menu_edit /* 2131362415 */:
                if (C5845t.w1(this, abstractC5838lX1, false)) {
                    com.alphainventor.filemanager.file.u uVar = (com.alphainventor.filemanager.file.u) abstractC5838lX1;
                    f1(3, uVar.v0(), uVar.C0(), false, true);
                    return true;
                }
                break;
        }
        switch (i2) {
            case R.id.menu_delete /* 2131362414 */:
                t1(abstractC5838lX1);
                return true;
            case R.id.menu_edit /* 2131362415 */:
                w1(abstractC5838lX1);
                return true;
            case R.id.menu_info /* 2131362423 */:
                R1(abstractC5838lX1);
                return true;
            case R.id.menu_open_default /* 2131362429 */:
                E1(abstractC5838lX1);
                return true;
            case R.id.menu_open_with /* 2131362431 */:
                G1(abstractC5838lX1);
                return true;
            case R.id.menu_rotate /* 2131362441 */:
                M1(abstractC5838lX1);
                return true;
            case R.id.menu_set_as /* 2131362448 */:
                O1(abstractC5838lX1);
                return true;
            case R.id.menu_share /* 2131362450 */:
                Q1(abstractC5838lX1);
                return true;
            case R.id.menu_slideshow /* 2131362452 */:
                U1(abstractC5838lX1);
                return true;
            default:
                return false;
        }
    }

    private void L1() {
        if (m() == null || m().T() == null) {
            return;
        }
        if (!m().q()) {
            m().m();
        }
        this.P0.w();
    }

    private void M1(AbstractC5838l abstractC5838l) {
        String str;
        if (!(abstractC5838l instanceof com.alphainventor.filemanager.file.u)) {
            if (abstractC5838l == null) {
                str = "loc:null";
            } else {
                str = "loc:" + abstractC5838l.P().H();
            }
            ax.p045d2.b.g(str);
            return;
        }
        if (!(this.U0.t() instanceof C5845t)) {
            ax.p045d2.b.f();
            return;
        }
        F fV0 = ((com.alphainventor.filemanager.file.u) abstractC5838l).v0();
        if (fV0 == null || fV0.e() == null) {
            return;
        }
        new m(abstractC5838l).i(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:45:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public boolean N1(AbstractC5838l abstractC5838l) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            try {
                F fV0 = ((com.alphainventor.filemanager.file.u) abstractC5838l).v0();
                if (fV0 == null || fV0.e() == null || !v1(fV0)) {
                    return false;
                }
                long jQ = abstractC5838l.q();
                C5845t c5845t = (C5845t) this.U0.t();
                ParcelFileDescriptor parcelFileDescriptorU0 = c5845t.u0((com.alphainventor.filemanager.file.u) abstractC5838l);
                if (parcelFileDescriptorU0 == null) {
                    if (parcelFileDescriptorU0 != null) {
                        try {
                            parcelFileDescriptorU0.close();
                        } catch (IOException unused) {
                        }
                    }
                    return false;
                }
                ax.p323x0.a aVar = new ax.p323x0.a(parcelFileDescriptorU0.getFileDescriptor());
                if (aVar.g("Orientation", 1) == 0) {
                    aVar.V();
                }
                aVar.X(-90);
                aVar.Y();
                c5845t.F1((com.alphainventor.filemanager.file.u) abstractC5838l, jQ);
                c5845t.I1(abstractC5838l);
                ax.p003a2.c.B(this, this.U0.Z0(abstractC5838l));
                try {
                    parcelFileDescriptorU0.close();
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

    private void O1(AbstractC5838l abstractC5838l) {
        ax.p338y1.a.k().o("menu_image_viewer", "set_as").e();
        if (C0740v.A(abstractC5838l)) {
            C0734o.Z(P(), (AbstractC0726g) abstractC5838l);
            return;
        }
        File fileY = abstractC5838l.Y();
        if (!C0740v.z(fileY, abstractC5838l)) {
            u1(abstractC5838l, new k(abstractC5838l, fileY));
        } else {
            q.b().e(fileY);
            C0734o.a0(P(), abstractC5838l.s(), fileY);
        }
    }

    private void P1(AbstractC5838l abstractC5838l) {
        this.a1 = abstractC5838l;
        this.b1 = abstractC5838l.q();
    }

    private void Q1(AbstractC5838l abstractC5838l) {
        ax.p338y1.a.k().o("menu_image_viewer", "share").c("loc", "image_viewer").c("type", "file").e();
        if (C0740v.A(abstractC5838l)) {
            C0734o.c0(P(), (AbstractC0726g) abstractC5838l);
            return;
        }
        File fileY = abstractC5838l.Y();
        if (!C0740v.z(fileY, abstractC5838l)) {
            u1(abstractC5838l, new a(abstractC5838l, fileY));
        } else {
            q.b().e(fileY);
            C0734o.f0(P(), abstractC5838l.s(), fileY);
        }
    }

    private void R1(AbstractC5838l abstractC5838l) {
        m().b(true);
        if (m().q()) {
            return;
        }
        m().m();
    }

    private void U1(AbstractC5838l abstractC5838l) {
        if (m() == null || m().T() == null) {
            return;
        }
        if (!m().q()) {
            m().m();
        }
        this.P0.A();
    }

    private boolean V1(AbstractC5838l abstractC5838l) {
        if (C0740v.A(abstractC5838l)) {
            return true;
        }
        P.k0();
        return false;
    }

    private void q1() {
        e eVar = new e(400L);
        ax.p087g2.d dVar = new ax.p087g2.d(P(), findViewById(R.id.bottom_menu_layout), null);
        this.W0 = dVar;
        dVar.d(R.id.menu_share, R.string.menu_share, R.drawable.ic_share, eVar);
        this.W0.d(R.id.menu_rotate, R.string.menu_rotate, R.drawable.ic_rotate_90_ccw, eVar);
        this.W0.d(R.id.menu_delete, R.string.menu_delete, R.drawable.ic_delete, eVar);
        this.W0.d(R.id.menu_info, R.string.menu_info, R.drawable.ic_info_outline, eVar);
        this.W0.e();
        this.W0.l(R.menu.image_viewer);
        this.W0.m(new f());
        this.W0.v(new g());
    }

    private void r1() {
        AbstractC5838l abstractC5838l = this.a1;
        if (abstractC5838l == null) {
            return;
        }
        if (abstractC5838l instanceof AbstractC0726g) {
            try {
                if (r.d(abstractC5838l.R()).V0(this.a1.C()).q() != this.b1) {
                    ((com.alphainventor.filemanager.viewer.a) m()).T0();
                }
            } catch (C0718i unused) {
            }
        }
        this.a1 = null;
        this.b1 = 0L;
    }

    private void t1(AbstractC5838l abstractC5838l) {
        ax.p338y1.a.k().o("menu_image_viewer", "delete").c("loc", "image_viewer").c("type", "file").e();
        ArrayList arrayList = new ArrayList();
        arrayList.add(abstractC5838l);
        ax.D1.m.m(this.U0, arrayList, 0, C0740v.a(arrayList) && ax.p338y1.f.m(abstractC5838l.P()), this, false, new h(abstractC5838l));
    }

    private void u1(AbstractC5838l abstractC5838l, AbstractC0762l.p pVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(abstractC5838l);
        p pVarL = p.l();
        pVarL.k(this.U0, arrayList, true, new j(pVar));
        try {
            y(pVarL, true);
        } catch (C0711b unused) {
            Toast.makeText(this, R.string.error, 0).show();
        }
    }

    private boolean v1(F f2) {
        String strE = f2.e();
        if (this.Q0.containsKey(strE)) {
            return this.Q0.get(strE).booleanValue();
        }
        boolean zO = C0740v.o(f2);
        this.Q0.put(strE, Boolean.valueOf(zO));
        return zO;
    }

    private void w1(AbstractC5838l abstractC5838l) {
        if (C0740v.H(abstractC5838l)) {
            try {
                C0734o.h0(this, C0734o.e(this, (com.alphainventor.filemanager.file.u) abstractC5838l));
            } catch (ActivityNotFoundException unused) {
                S1(getString(R.string.no_apps_to_open_file), -1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AbstractC5838l x1(Uri uri) {
        if (uri == null) {
            return null;
        }
        for (AbstractC5838l abstractC5838l : this.R0) {
            if (uri.toString().equals(abstractC5838l.Q())) {
                return abstractC5838l;
            }
        }
        return null;
    }

    private void z1(int i2) {
        View viewJ = i2 == 20 ? this.W0.j() : y1();
        if (viewJ != null) {
            if (D1()) {
                m().m();
            } else {
                m().t0();
            }
            viewJ.requestFocus();
        }
    }

    @Override // com.android.ex.photo.f.g
    public void G() {
        com.android.ex.photo.f fVar = this.N0;
        if (fVar == null || this.c1) {
            return;
        }
        this.d1.j(fVar.a0());
    }

    public void J1() {
        K1();
    }

    void K1() {
        Fragment fragmentI0 = w().i0("dialog");
        if (fragmentI0 != null) {
            androidx.fragment.app.t tVarO = w().o();
            tVarO.q(fragmentI0);
            tVarO.j();
        }
    }

    @Override // com.android.ex.photo.f.g
    public void O(boolean z) {
        if (I0() == null || this.W0 == null) {
            ax.p045d2.b.f();
            return;
        }
        if (z) {
            I0().l();
            this.W0.w(8);
            return;
        }
        I0().H();
        this.W0.w(0);
        this.W0.y();
        if (m() != null && m().y()) {
            m().b(false);
        }
        this.P0.o();
    }

    void S1(CharSequence charSequence, int i2) {
        x.V(this.X0, charSequence, i2).Y();
    }

    @Override // com.android.ex.photo.f.g
    public boolean T() {
        return this.P0.u();
    }

    void T1(AbstractC5838l abstractC5838l, int i2, CharSequence charSequence, List<String> list) {
        x.R(this.X0, charSequence, 0, R.string.menu_undo, new i(list, i2, abstractC5838l)).Y();
    }

    @Override // com.android.ex.photo.f.g
    public boolean c() {
        return this.P0.s();
    }

    @Override // com.android.ex.photo.f.g
    public void d(int i2) {
        if (i2 == 1) {
            this.P0.o();
        }
    }

    @Override // com.android.ex.photo.f.g
    public void e() {
        this.P0.z();
    }

    @Override // ax.p182n.ActivityC5571c, android.app.Activity
    public View findViewById(int i2) {
        return H0().l(i2);
    }

    @Override // com.android.ex.photo.f.g
    public void i() {
        AbstractC5838l abstractC5838lX1;
        Uri uriM0 = ((com.alphainventor.filemanager.viewer.a) m()).M0();
        if (uriM0 == null || (abstractC5838lX1 = x1(uriM0)) == null) {
            return;
        }
        if (abstractC5838lX1.E() != EnumC0738t.VIDEO || V1(abstractC5838lX1)) {
            this.W0.s(R.id.menu_info, true);
        } else {
            this.W0.s(R.id.menu_info, false);
        }
        if (this.U0.K(abstractC5838lX1)) {
            this.W0.n(R.id.menu_delete, true);
        } else {
            this.W0.n(R.id.menu_delete, false);
        }
        this.W0.s(R.id.menu_rotate, P.o1() && (abstractC5838lX1 instanceof com.alphainventor.filemanager.file.u) && abstractC5838lX1.E() == EnumC0738t.IMAGE && C1(abstractC5838lX1.v()) && v1(((com.alphainventor.filemanager.file.u) abstractC5838lX1).v0()));
        H1();
    }

    @Override // com.android.ex.photo.f.g
    public com.android.ex.photo.f m() {
        return this.N0;
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        this.N0.c0(i2, i3, intent);
        if (i2 == e1) {
            if (i3 == -1) {
                L1();
            } else if (i3 == 0) {
                this.P0.o();
                if (this.N0.q()) {
                    this.N0.m();
                }
            }
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        PhotoViewPager photoViewPagerT = m().T();
        if (photoViewPagerT != null) {
            int currentItem = photoViewPagerT.getCurrentItem();
            photoViewPagerT.setAdapter(photoViewPagerT.getAdapter());
            photoViewPagerT.setCurrentItem(currentItem);
        }
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C c2 = (C) w().i0("headless_fragment");
        this.Y0 = c2;
        if (c2 == null) {
            this.Y0 = C.X2("ImageViewerActivity");
            w().o().e(this.Y0, "headless_fragment").i();
        }
        this.S0 = (ax.p338y1.f) getIntent().getSerializableExtra("location");
        int intExtra = getIntent().getIntExtra("location_key", 0);
        this.T0 = intExtra;
        ax.p338y1.f fVar = this.S0;
        if (fVar == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(HttpUrl.FRAGMENT_ENCODE_SET);
            sb.append(getIntent().getExtras() == null);
            sb.append(",key:");
            sb.append(this.T0);
            ax.p221pa.c.h().d("ImageViewer no location").h(sb.toString()).i();
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        C5839m c5839mE = r.e(fVar, intExtra);
        this.U0 = c5839mE;
        c5839mE.S();
        com.android.ex.photo.f fVarS1 = s1();
        this.N0 = fVarS1;
        fVarS1.e0(bundle);
        this.P0 = new t(findViewById(R.id.slideshow_options), this.N0.T(), new c());
        List<AbstractC5838l> list = this.R0;
        if (list == null || list.size() == 0) {
            finish();
            return;
        }
        if (!this.U0.a()) {
            if ((this.U0.t() instanceof C5827a) && !((C5827a) this.U0.t()).y0()) {
                ax.p221pa.b bVarB = ax.p221pa.c.h().g().b("IMAGEVIEWER NOT CONNECTED OPERATOR ARCHIVE");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("location:");
                sb2.append(this.S0.H());
                sb2.append(",");
                sb2.append(this.T0);
                sb2.append(",");
                sb2.append(bundle != null);
                bVarB.h(sb2.toString()).i();
                Toast.makeText(this, R.string.error, 1).show();
                finish();
                return;
            }
            ax.p221pa.c.h().g().b("IMAGEVIEWER NOT CONNECTED OPERATOR").h("location:" + this.S0.H()).i();
        }
        q1();
        this.X0 = findViewById(R.id.snackbar_container);
        q().h(this, this.d1);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        com.android.ex.photo.f fVar = this.N0;
        return (fVar != null && fVar.f0(menu)) || super.onCreateOptionsMenu(menu);
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        com.android.ex.photo.f fVar = this.N0;
        if (fVar != null) {
            fVar.g0();
        }
        C5839m c5839m = this.U0;
        if (c5839m != null) {
            c5839m.P(false);
        }
        try {
            super.onDestroy();
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // ax.p182n.ActivityC5571c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        View currentFocus;
        this.P0.o();
        if (keyEvent.isMetaPressed() || keyEvent.isShiftPressed() || keyEvent.isAltPressed()) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (i2 == 112) {
            I1(R.id.menu_delete);
            return true;
        }
        switch (i2) {
            case 19:
            case 20:
                if (m().q()) {
                    z1(i2);
                    return true;
                }
                View currentFocus2 = getCurrentFocus();
                if (currentFocus2 == null) {
                    z1(i2);
                    return true;
                }
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                ViewGroup viewGroup = (ViewGroup) findViewById(R.id.bottom_menu_layout);
                if (B1(toolbar, currentFocus2)) {
                    if (i2 == 20) {
                        m().m();
                        return true;
                    }
                } else {
                    if (!B1(viewGroup, currentFocus2)) {
                        z1(i2);
                        return true;
                    }
                    if (i2 == 19) {
                        m().m();
                        return true;
                    }
                }
                break;
            case C1452Re.zzm /* 21 */:
            case 22:
                boolean zOnKeyDown = super.onKeyDown(i2, keyEvent);
                if (!D1() && (currentFocus = getCurrentFocus()) != null) {
                    Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
                    ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.bottom_menu_layout);
                    if (B1(toolbar2, currentFocus) || B1(viewGroup2, currentFocus)) {
                        m().t0();
                    }
                }
                return zOnKeyDown;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.N0.k0(menuItem) || super.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return I1(menuItem.getItemId());
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        this.N0.l0();
        super.onPause();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        com.android.ex.photo.f fVar = this.N0;
        return (fVar != null && fVar.n0(menu)) || super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.N0.o0();
        r1();
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.N0.p0(bundle);
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.N0.q0();
        if (P.x1()) {
            ax.E1.u.r(getWindow(), -1157627904);
            ax.E1.u.o(getWindow(), -1157627904);
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        this.N0.r0();
        this.P0.o();
        super.onStop();
    }

    protected com.android.ex.photo.f s1() {
        com.alphainventor.filemanager.viewer.b.a aVarA = com.alphainventor.filemanager.viewer.b.b().a(getIntent().getStringExtra(f1));
        if (aVarA != null) {
            this.R0 = aVarA.a;
            this.V0 = aVarA.b;
        }
        getIntent().putExtra("photo_index", this.V0);
        return new com.alphainventor.filemanager.viewer.a(this, this.R0, this.U0);
    }

    @Override // com.android.ex.photo.f.g
    public Fragment t() {
        return this.Y0;
    }

    View y1() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTouchscreenBlocksFocus(false);
        View childAt = toolbar.getChildAt(0);
        if ((childAt instanceof androidx.appcompat.widget.p) && toolbar.getNavigationContentDescription() != null && toolbar.getNavigationContentDescription().equals(childAt.getContentDescription())) {
            childAt.setId(android.R.id.home);
        } else {
            ax.p045d2.b.e("not work anymore");
        }
        return childAt;
    }

    @Override // com.android.ex.photo.f.g
    public com.android.ex.photo.a z() {
        if (this.O0 == null) {
            S0((Toolbar) findViewById(R.id.toolbar));
            this.O0 = new com.android.ex.photo.b(I0());
        }
        return this.O0;
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p352z1.a
    public ActivityC5571c P() {
        return this;
    }
}
