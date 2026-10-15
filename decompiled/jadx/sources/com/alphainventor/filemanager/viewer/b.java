package com.alphainventor.filemanager.viewer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ax.p054d3.T;
import ax.p070e3.C1966j;
import ax.p086f3.A;
import ax.p086f3.AbstractC2005k;
import ax.p086f3.B;
import ax.p086f3.C2014u;
import ax.p086f3.C2015v;
import ax.p086f3.C2016w;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p102g3.M;
import ax.p390y3.k;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6249y;
import com.alphainventor.filemanager.provider.MyFileProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.android.ex.photo.f {
    private List<AbstractC6239n> V0;
    private List<AbstractC6239n> W0;
    private boolean X0;
    private boolean Y0;
    private C6240o Z0;
    private ax.p358w3.d a1;
    private Cursor b1;
    private Cursor c1;
    private Boolean d1;
    private d e1;

    class a extends ax.p182l3.c {
        final /* synthetic */ Uri Y;

        a(Uri uri) {
            this.Y = uri;
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            b.this.r(this.Y, false);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.viewer.b$b, reason: collision with other inner class name */
    class C0552b implements ax.F3.a.h {
        final /* synthetic */ ax.F3.a a;

        C0552b(ax.F3.a aVar) {
            this.a = aVar;
        }

        @Override // ax.F3.a.h
        public void a(boolean z) {
            if (b.this.w(this.a)) {
                b.this.g1(!z);
            }
        }
    }

    class c extends ax.p182l3.c {
        final /* synthetic */ AbstractC6239n Y;
        final /* synthetic */ com.android.ex.photo.c Z;

        c(AbstractC6239n abstractC6239n, com.android.ex.photo.c cVar) {
            this.Y = abstractC6239n;
            this.Z = cVar;
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            b.this.b1((AbstractC2005k) this.Y, this.Z);
        }
    }

    class d extends q<Void, Integer, Void> {
        int h;

        d(int i) {
            super(q.e.HIGH);
            this.h = i;
        }

        private void w(int i) {
            List listU0;
            if (isCancelled() || (listU0 = b.this.U0()) == null || !b.this.a1(i)) {
                return;
            }
            AbstractC6239n abstractC6239n = (AbstractC6239n) listU0.get(i);
            if (B.P(abstractC6239n) || abstractC6239n.Q() == ax.U2.f.g1) {
                if (ax.p358w3.d.l(b.this.N().getContext(), B.Y(abstractC6239n)) == null) {
                    b.this.a1.t(abstractC6239n);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            if (b.this.Y()) {
                return null;
            }
            w(this.h - 1);
            w(this.h + 1);
            w(this.h - 2);
            w(this.h + 2);
            return null;
        }
    }

    public static class e extends ax.K0.b {
        ArrayList<AbstractC6239n> w;

        public e(Context context, List<AbstractC6239n> list) {
            super(context);
            ArrayList<AbstractC6239n> arrayList = new ArrayList<>();
            this.w = arrayList;
            if (list != null) {
                arrayList.addAll(list);
            }
        }

        @Override // ax.K0.a
        /* JADX INFO: renamed from: M */
        public Cursor I() {
            return b.O0(this.w);
        }
    }

    private enum f {
        BUILT_IN,
        GENERAL,
        IN_IMAGE_VIEWER
    }

    public b(com.android.ex.photo.f.g gVar, List<AbstractC6239n> list, C6240o c6240o) {
        super(gVar);
        this.V0 = list;
        this.Z0 = c6240o;
        this.a1 = new ax.p358w3.d(gVar.getContext(), this.Z0);
    }

    private static void N0(MatrixCursor matrixCursor, AbstractC6239n abstractC6239n) {
        String strW = B.P(abstractC6239n) ? B.W(abstractC6239n) : B.Y(abstractC6239n);
        if (B.H(abstractC6239n) && abstractC6239n.p() < 50000) {
            strW = null;
        }
        matrixCursor.newRow().add(abstractC6239n.R()).add(abstractC6239n.B()).add(abstractC6239n.R()).add(strW).add(abstractC6239n.t());
    }

    public static MatrixCursor O0(ArrayList<AbstractC6239n> arrayList) {
        MatrixCursor matrixCursor = new MatrixCursor(ax.H3.a.a);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            AbstractC6239n abstractC6239n = arrayList.get(i);
            i++;
            N0(matrixCursor, abstractC6239n);
        }
        return matrixCursor;
    }

    private ArrayList<com.alphainventor.filemanager.viewer.e.a> Q0(Context context, Uri uri, List<AbstractC6239n> list) {
        boolean zS3 = T.S3(context, uri);
        ArrayList<com.alphainventor.filemanager.viewer.e.a> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AbstractC6239n abstractC6239n = list.get(i);
            if (zS3 ? A.F(abstractC6239n) : A.E(abstractC6239n)) {
                arrayList.add(new com.alphainventor.filemanager.viewer.e.a(C2014u.L(abstractC6239n, false), null));
            }
        }
        return arrayList;
    }

    private AbstractC6239n R0(String str) {
        List<AbstractC6239n> listU0;
        if (str == null || (listU0 = U0()) == null) {
            return null;
        }
        for (int i = 0; i < listU0.size(); i++) {
            AbstractC6239n abstractC6239n = listU0.get(i);
            if (str.equals(abstractC6239n.R())) {
                return abstractC6239n;
            }
        }
        return null;
    }

    private int S0(Uri uri) {
        List<AbstractC6239n> listU0;
        if (uri == null || (listU0 = U0()) == null) {
            return -1;
        }
        for (int i = 0; i < listU0.size(); i++) {
            AbstractC6239n abstractC6239n = listU0.get(i);
            if (B.H(abstractC6239n) && uri.equals(C2014u.L(abstractC6239n, false))) {
                return i;
            }
        }
        return -1;
    }

    private void T0(ax.F3.a aVar) {
        if (N() != null) {
            N().f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<AbstractC6239n> U0() {
        return this.X0 ? this.W0 : this.V0;
    }

    public static Intent W0(Context context, AbstractC6239n abstractC6239n, boolean z) {
        C2461b.c(B.H(abstractC6239n));
        return T.J3(context, ax.c3.c.a.IN_IMAGE_VIEWER, B.H(abstractC6239n) ? T.O3(abstractC6239n) : C2014u.w(abstractC6239n.a0()), C2015v.e(abstractC6239n, "application/octet-stream"), true, z);
    }

    private View X0() {
        return M(R.id.btn_motion_play);
    }

    private View Y0(ax.F3.a aVar) {
        if (aVar.a1() == null) {
            return null;
        }
        return aVar.a1().findViewById(R.id.control_play);
    }

    private void Z0(ax.F3.a aVar) {
        View viewY0 = Y0(aVar);
        if (viewY0 != null) {
            viewY0.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a1(int i) {
        List<AbstractC6239n> listU0 = U0();
        return listU0 != null && i < listU0.size() && i >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b1(AbstractC2005k abstractC2005k, com.android.ex.photo.c cVar) {
        if (N() == null) {
            return;
        }
        Activity activity = (Activity) N().getContext();
        try {
            Intent intentE = C2016w.e(activity, MyFileProvider.p(abstractC2005k, cVar.c, cVar.d, cVar.f));
            this.d1 = Boolean.valueOf(s());
            activity.startActivityForResult(intentE, 36002);
        } catch (ActivityNotFoundException | IllegalArgumentException | SecurityException unused) {
            this.d1 = null;
            Toast.makeText(activity, R.string.error, 1).show();
        }
    }

    private void c1() {
        if (N() instanceof ImageViewerActivity) {
            ((ImageViewerActivity) N()).A0();
        }
    }

    private void d1(ax.F3.a aVar) {
        View viewY0 = Y0(aVar);
        if (viewY0 == null) {
            C2461b.f();
            return;
        }
        Uri uri = Uri.parse(aVar.i3());
        if (k(uri)) {
            aVar.e3(false);
            viewY0.setVisibility(0);
            viewY0.setOnClickListener(new a(uri));
            viewY0.requestFocus();
        } else {
            viewY0.setVisibility(8);
        }
        if (!w(aVar)) {
            aVar.w3(null);
            return;
        }
        View viewX0 = X0();
        if (viewX0 == null) {
            C2461b.f();
            return;
        }
        AbstractC6239n abstractC6239nR0 = R0(aVar.i3());
        com.android.ex.photo.c cVarH3 = aVar.h3();
        if (!B.H(abstractC6239nR0) || cVarH3 == null || cVarH3.g != abstractC6239nR0 || !cVarH3.b || cVarH3.c < 0 || cVarH3.d <= 0) {
            aVar.w3(null);
            g1(false);
        } else {
            aVar.w3(new C0552b(aVar));
            g1(!aVar.o3());
            viewX0.setOnClickListener(new c(abstractC6239nR0, cVarH3));
            viewX0.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g1(boolean z) {
        if (N() instanceof ImageViewerActivity) {
            ((ImageViewerActivity) N()).I0(z);
        }
    }

    @Override // com.android.ex.photo.f, ax.J0.a.InterfaceC0116a
    public ax.K0.c<Cursor> E(int i, Bundle bundle) {
        return i == 100 ? new e(N().getContext(), this.V0) : super.E(i, bundle);
    }

    @Override // com.android.ex.photo.f
    public void E0(boolean z) {
        if (z) {
            P0();
            this.X0 = true;
            D0(this.c1);
            return;
        }
        Cursor cursor = this.b1;
        if (cursor == null) {
            C2461b.f();
            return;
        }
        this.X0 = false;
        D0(cursor);
        Cursor cursor2 = this.c1;
        if (cursor2 != null) {
            cursor2.close();
            this.c1 = null;
            this.W0 = null;
        }
    }

    @Override // com.android.ex.photo.f
    public void F0() {
        AbstractC6239n abstractC6239n;
        int currentItem = this.t0.getCurrentItem();
        int i = currentItem + 1;
        boolean z = this.p0 >= 0;
        List<AbstractC6239n> listU0 = U0();
        if (this.q0 || !z || i <= 0 || listU0 == null) {
            this.D0 = null;
        } else if (this.p0 > 1) {
            this.D0 = N().getResources().getString(R.string.photo_view_count, Integer.valueOf(i), Integer.valueOf(this.p0));
            if (!a1(currentItem) || (abstractC6239n = listU0.get(currentItem)) == null) {
                this.E0 = HttpUrl.FRAGMENT_ENCODE_SET;
            } else {
                this.E0 = abstractC6239n.B();
            }
        } else {
            AbstractC6239n abstractC6239n2 = listU0.get(0);
            if (abstractC6239n2 != null) {
                this.D0 = abstractC6239n2.B();
            } else {
                this.D0 = HttpUrl.FRAGMENT_ENCODE_SET;
            }
        }
        x0(N().l());
    }

    public void P0() {
        ArrayList arrayList = new ArrayList(this.V0);
        Collections.shuffle(arrayList);
        this.W0 = arrayList;
        Cursor cursor = this.c1;
        if (cursor != null) {
            cursor.close();
        }
        this.c1 = O0(arrayList);
    }

    @Override // com.android.ex.photo.f, androidx.viewpager.widget.ViewPager.j
    public void Q(int i) {
        g1(false);
        super.Q(i);
        d dVar = this.e1;
        if (dVar != null && !dVar.isCancelled() && q.n(this.e1)) {
            this.e1.e();
        }
        d dVar2 = new d(i);
        this.e1 = dVar2;
        dVar2.i(new Void[0]);
    }

    public Uri V0() {
        int currentItem = this.t0.getCurrentItem();
        Cursor cursorX = this.v0.x();
        if (cursorX == null || cursorX.isClosed()) {
            return null;
        }
        cursorX.moveToPosition(currentItem);
        return Uri.parse(this.v0.E(cursorX));
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public void b(boolean z) {
        super.b(z);
        c1();
    }

    @Override // com.android.ex.photo.f
    public void d0(int i, int i2, Intent intent) {
        int iS0;
        if (i != 36001 || i2 != -1 || intent == null || (iS0 = S0(intent.getData())) < 0) {
            return;
        }
        V().setCurrentItem(iS0);
    }

    public void e1() {
        this.Y0 = true;
        this.q0 = true;
        N().getSupportLoaderManager().g(100, null, this);
    }

    @Override // com.android.ex.photo.e
    public void f(com.android.ex.photo.c cVar, View view) {
        ax.B3.e eVar;
        Uri uriV0;
        if (N() == null) {
            return;
        }
        Object tag = view.getTag();
        if (tag instanceof ax.B3.e) {
            eVar = (ax.B3.e) tag;
        } else {
            eVar = new ax.B3.e(view);
            view.setTag(eVar);
        }
        eVar.d(N().getContext(), cVar, (cVar != null || (uriV0 = V0()) == null) ? null : R0(uriV0.toString()));
    }

    public void f1() {
        com.android.ex.photo.e.b bVarO = O();
        if (bVarO != null) {
            bVarO.u();
        }
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public void j(ax.F3.a aVar, boolean z) {
        super.j(aVar, z);
        d1(aVar);
        T0(aVar);
        if (z) {
            return;
        }
        d1(aVar);
        Uri uri = Uri.parse(aVar.i3());
        if (k(uri)) {
            AbstractC6239n abstractC6239nR0 = R0(uri.toString());
            if (abstractC6239nR0 == null || !abstractC6239nR0.o()) {
                Z0(aVar);
            } else {
                aVar.g3().setVisibility(8);
            }
        }
    }

    @Override // com.android.ex.photo.e
    public boolean k(Uri uri) {
        return EnumC2019z.VIDEO == A.g(d0.k(uri.getPath()));
    }

    @Override // ax.J0.a.InterfaceC0116a
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public void D(ax.K0.c<Cursor> cVar, Cursor cursor) {
        if (cursor == null || cursor != this.b1) {
            this.b1 = cursor;
            try {
                super.D(cVar, cursor);
            } catch (NoSuchMethodError e2) {
                ax.p302sb.c.h().d("IVOLF:").m(e2).i();
            }
            if (this.Y0) {
                this.Y0 = false;
                if (N() != null) {
                    ((ImageViewerActivity) N()).B0();
                    F0();
                }
            }
        }
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public void m(ax.F3.a aVar) {
        super.m(aVar);
        d1(aVar);
        T0(aVar);
    }

    @Override // com.android.ex.photo.f
    public void o0() {
        super.o0();
        if (N() == null) {
            return;
        }
        Boolean bool = this.d1;
        if (bool != null) {
            y0(bool.booleanValue(), false);
            this.d1 = null;
        } else {
            if (N().q()) {
                return;
            }
            y0(false, false);
        }
    }

    @Override // com.android.ex.photo.e
    public void r(Uri uri, boolean z) {
        AbstractC6239n abstractC6239nR0;
        f fVar;
        if (N() == null || (abstractC6239nR0 = R0(uri.toString())) == null) {
            return;
        }
        List<AbstractC6239n> listU0 = U0();
        if (!B.H(abstractC6239nR0)) {
            try {
                C6249y c6249yP = abstractC6239nR0.P();
                if (c6249yP != null && B.F(c6249yP, abstractC6239nR0)) {
                    try {
                        listU0 = Collections.singletonList(c6249yP);
                    } catch (C1966j unused) {
                    }
                    abstractC6239nR0 = c6249yP;
                }
            } catch (C1966j unused2) {
            }
        } else if (listU0 == null) {
            C2461b.e("what case is this?");
            listU0 = Collections.singletonList(abstractC6239nR0);
        }
        Activity activity = (Activity) N().getContext();
        if (!B.H(abstractC6239nR0)) {
            ax.p302sb.c.h().g().b("NOT REACHABLE : IMAGE VIEWER").k().h("PLAY VIDEO:" + uri.toString() + ",fileinfo:" + abstractC6239nR0.S()).i();
            Toast.makeText(activity, R.string.error, 1).show();
            return;
        }
        AbstractC2005k abstractC2005k = (AbstractC2005k) abstractC6239nR0;
        if (z) {
            fVar = f.BUILT_IN;
        } else {
            ax.c3.c.a aVar = ax.c3.c.a.GENERAL;
            if (T.U3(activity, aVar, abstractC2005k, false) && !T.X3(activity, aVar, abstractC2005k, false)) {
                fVar = f.GENERAL;
            } else if (k.J(activity) && A.E(abstractC2005k)) {
                fVar = f.BUILT_IN;
            } else {
                Intent intentW0 = W0(activity, abstractC2005k, true);
                fVar = (intentW0 == null || !C2014u.U(intentW0)) ? f.IN_IMAGE_VIEWER : f.BUILT_IN;
            }
        }
        if (fVar == f.BUILT_IN) {
            Uri uriL = C2014u.L(abstractC2005k, false);
            ArrayList<com.alphainventor.filemanager.viewer.e.a> arrayListQ0 = Q0(activity, uriL, listU0);
            try {
                ax.U2.a.k().o("command", "file_open").c("loc", "ImageViewerActivity").c("ext", abstractC2005k.y()).c("result", "success").e();
                activity.startActivityForResult(C2016w.j(activity, uriL, arrayListQ0, true, false), 36001);
                return;
            } catch (ActivityNotFoundException | NullPointerException | SecurityException e2) {
                Toast.makeText(activity, R.string.error, 1).show();
                ax.p302sb.c.h().g().b("PVI:").m(e2).i();
                return;
            }
        }
        if (fVar == f.GENERAL) {
            Fragment fragmentJ = N().j();
            if (fragmentJ instanceof M) {
                ((M) fragmentJ).W2(ax.c3.c.a.GENERAL, abstractC2005k, abstractC2005k.t(), false, false);
                return;
            }
            return;
        }
        Fragment fragmentJ2 = N().j();
        if (fragmentJ2 instanceof M) {
            ((M) fragmentJ2).W2(ax.c3.c.a.IN_IMAGE_VIEWER, abstractC2005k, abstractC2005k.t(), false, false);
        }
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public ax.K0.c<ax.G3.b.a> x(int i, Bundle bundle, String str) {
        if (i != 1) {
            if (i == 2) {
                return new ax.B3.f(N().getContext(), this.Z0, null, str, true, false);
            }
            if (i != 3) {
                return super.x(i, bundle, str);
            }
        }
        return new ax.B3.f(N().getContext(), this.Z0, R0(str), str, false, bundle != null ? bundle.getBoolean("use_factory_if_possible", true) : true);
    }
}
