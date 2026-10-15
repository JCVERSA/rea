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
import ax.H1.O;
import ax.I1.C0718i;
import ax.J1.AbstractC0726g;
import ax.J1.C0734o;
import ax.J1.C0735p;
import ax.J1.C0736q;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.EnumC0738t;
import ax.J1.V;
import ax.K1.C;
import ax.p031c2.i;
import ax.p045d2.n;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.u;
import com.android.ex.photo.e;
import com.android.ex.photo.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class a extends f {
    private List<AbstractC5838l> S0;
    private boolean T0;
    private C5839m U0;
    private ax.p003a2.c V0;
    private Cursor W0;
    private b X0;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.viewer.a$a, reason: collision with other inner class name */
    class C0517a extends ax.P1.c {
        final /* synthetic */ Uri Y;

        C0517a(Uri uri) {
            this.Y = uri;
        }

        @Override // ax.P1.c
        public void a(View view) {
            a.this.p(this.Y, false);
        }
    }

    class b extends n<Void, Integer, Void> {
        int h;

        b(int i) {
            super(n.f.HIGH);
            this.h = i;
        }

        private void w(int i) {
            if (!isCancelled() && a.this.Q0(i)) {
                AbstractC5838l abstractC5838l = (AbstractC5838l) a.this.S0.get(i);
                if (C0740v.H(abstractC5838l) || abstractC5838l.P() == ax.p338y1.f.c1) {
                    if (ax.p003a2.c.l(a.this.M().getContext(), C0740v.Q(abstractC5838l)) == null) {
                        a.this.V0.t(abstractC5838l);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            if (a.this.X()) {
                return null;
            }
            w(this.h - 1);
            w(this.h + 1);
            w(this.h - 2);
            w(this.h + 2);
            return null;
        }
    }

    public static class c extends ax.J0.b {
        List<AbstractC5838l> w;

        public c(Context context, List<AbstractC5838l> list) {
            super(context);
            ArrayList arrayList = new ArrayList();
            this.w = arrayList;
            if (list != null) {
                arrayList.addAll(list);
            }
        }

        @Override // ax.J0.a
        /* JADX INFO: renamed from: M */
        public Cursor I() {
            MatrixCursor matrixCursor = new MatrixCursor(ax.p157l2.a.a);
            Iterator<AbstractC5838l> it = this.w.iterator();
            while (it.hasNext()) {
                Q(matrixCursor, it.next());
            }
            return matrixCursor;
        }

        void Q(MatrixCursor matrixCursor, AbstractC5838l abstractC5838l) {
            String strO = C0740v.H(abstractC5838l) ? C0740v.O(abstractC5838l) : C0740v.Q(abstractC5838l);
            if (C0740v.A(abstractC5838l) && abstractC5838l.o() < 50000) {
                strO = null;
            }
            matrixCursor.newRow().add(abstractC5838l.Q()).add(abstractC5838l.w()).add(abstractC5838l.Q()).add(strO).add(abstractC5838l.s());
        }
    }

    private enum d {
        BUILT_IN,
        GENERAL,
        IN_IMAGE_VIEWER
    }

    public a(f.g gVar, List<AbstractC5838l> list, C5839m c5839m) {
        super(gVar);
        this.S0 = list;
        this.U0 = c5839m;
        this.V0 = new ax.p003a2.c(gVar.getContext(), this.U0);
    }

    private ArrayList<com.alphainventor.filemanager.viewer.c.a> I0(Context context, Uri uri, List<AbstractC5838l> list) {
        boolean zR3 = O.R3(context, ax.G1.c.a.IN_IMAGE_VIEWER, uri);
        ArrayList<com.alphainventor.filemanager.viewer.c.a> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AbstractC5838l abstractC5838l = list.get(i);
            if (zR3 ? C0739u.B(abstractC5838l) : C0739u.A(abstractC5838l)) {
                arrayList.add(new com.alphainventor.filemanager.viewer.c.a(C0734o.F(abstractC5838l, false), null));
            }
        }
        return arrayList;
    }

    private AbstractC5838l J0(String str) {
        if (str == null || this.S0 == null) {
            return null;
        }
        for (int i = 0; i < this.S0.size(); i++) {
            AbstractC5838l abstractC5838l = this.S0.get(i);
            if (str.equals(abstractC5838l.Q())) {
                return abstractC5838l;
            }
        }
        return null;
    }

    private int K0(Uri uri) {
        if (uri == null || this.S0 == null) {
            return -1;
        }
        for (int i = 0; i < this.S0.size(); i++) {
            AbstractC5838l abstractC5838l = this.S0.get(i);
            if (C0740v.A(abstractC5838l) && uri.equals(C0734o.F(abstractC5838l, false))) {
                return i;
            }
        }
        return -1;
    }

    private void L0(ax.p129j2.a aVar) {
        if (M() != null) {
            M().i();
        }
    }

    public static Intent N0(Context context, AbstractC5838l abstractC5838l, boolean z) {
        ax.p045d2.b.c(C0740v.A(abstractC5838l));
        return O.J3(context, ax.G1.c.a.IN_IMAGE_VIEWER, C0740v.A(abstractC5838l) ? O.N3(abstractC5838l) : C0734o.r(abstractC5838l.Y()), C0735p.e(abstractC5838l, "application/octet-stream"), true, z);
    }

    private View O0(ax.p129j2.a aVar) {
        if (aVar.g1() == null) {
            return null;
        }
        return aVar.g1().findViewById(R.id.control_play);
    }

    private void P0(ax.p129j2.a aVar) {
        View viewO0 = O0(aVar);
        if (viewO0 != null) {
            viewO0.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Q0(int i) {
        return i < this.S0.size() && i >= 0;
    }

    private void R0(ax.p129j2.a aVar) {
        View viewO0 = O0(aVar);
        if (viewO0 == null) {
            ax.p045d2.b.f();
            return;
        }
        Uri uri = Uri.parse(aVar.h3());
        if (!i(uri)) {
            viewO0.setVisibility(8);
            return;
        }
        aVar.e3(false);
        viewO0.setVisibility(0);
        viewO0.setOnClickListener(new C0517a(uri));
        viewO0.requestFocus();
    }

    @Override // com.android.ex.photo.f
    public void C0() {
        AbstractC5838l abstractC5838l;
        int currentItem = this.q0.getCurrentItem();
        int i = currentItem + 1;
        int i2 = this.m0;
        boolean z = i2 >= 0;
        if (this.n0 || !z || i <= 0) {
            this.A0 = null;
        } else if (i2 > 1) {
            this.A0 = M().getResources().getString(R.string.photo_view_count, Integer.valueOf(i), Integer.valueOf(this.m0));
            if (!Q0(currentItem) || (abstractC5838l = this.S0.get(currentItem)) == null) {
                this.B0 = HttpUrl.FRAGMENT_ENCODE_SET;
            } else {
                this.B0 = abstractC5838l.w();
            }
        } else {
            AbstractC5838l abstractC5838l2 = this.S0.get(0);
            if (abstractC5838l2 != null) {
                this.A0 = abstractC5838l2.w();
            } else {
                this.A0 = HttpUrl.FRAGMENT_ENCODE_SET;
            }
        }
        w0(M().z());
    }

    @Override // com.android.ex.photo.f, ax.I0.a.InterfaceC0157a
    public ax.J0.c<Cursor> H(int i, Bundle bundle) {
        return i == 100 ? new c(M().getContext(), this.S0) : super.H(i, bundle);
    }

    public Uri M0() {
        int currentItem = this.q0.getCurrentItem();
        Cursor cursorX = this.s0.x();
        if (cursorX == null || cursorX.isClosed()) {
            return null;
        }
        cursorX.moveToPosition(currentItem);
        return Uri.parse(this.s0.E(cursorX));
    }

    public void S0() {
        this.T0 = true;
        this.n0 = true;
        M().g().g(100, null, this);
    }

    public void T0() {
        e.b bVarN = N();
        if (bVarN != null) {
            bVarN.v();
        }
    }

    @Override // com.android.ex.photo.f, androidx.viewpager.widget.ViewPager.j
    public void W(int i) {
        super.W(i);
        b bVar = this.X0;
        if (bVar != null && !bVar.isCancelled() && n.n(this.X0)) {
            this.X0.e();
        }
        b bVar2 = new b(i);
        this.X0 = bVar2;
        bVar2.i(new Void[0]);
    }

    @Override // com.android.ex.photo.f
    public void c0(int i, int i2, Intent intent) {
        int iK0;
        if (i != 36001 || i2 != -1 || intent == null || (iK0 = K0(intent.getData())) < 0) {
            return;
        }
        T().setCurrentItem(iK0);
    }

    @Override // com.android.ex.photo.e
    public void f(com.android.ex.photo.c cVar, View view) {
        ax.p073f2.a aVar;
        if (M() == null) {
            return;
        }
        Object tag = view.getTag();
        if (tag instanceof ax.p073f2.a) {
            aVar = (ax.p073f2.a) tag;
        } else {
            aVar = new ax.p073f2.a(view);
            view.setTag(aVar);
        }
        aVar.d(M().getContext(), cVar);
    }

    @Override // com.android.ex.photo.e
    public boolean i(Uri uri) {
        return EnumC0738t.VIDEO == C0739u.e(V.k(uri.getPath()));
    }

    @Override // ax.I0.a.InterfaceC0157a
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public void m0(ax.J0.c<Cursor> cVar, Cursor cursor) {
        if (cursor == null || cursor != this.W0) {
            this.W0 = cursor;
            try {
                super.m0(cVar, cursor);
            } catch (NoSuchMethodError e) {
                ax.p221pa.c.h().d("IVOLF:").m(e).i();
            }
            if (this.T0) {
                this.T0 = false;
                if (M() != null) {
                    ((ImageViewerActivity) M()).J1();
                    C0();
                }
            }
        }
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public void l(ax.p129j2.a aVar) {
        super.l(aVar);
        R0(aVar);
        L0(aVar);
    }

    @Override // com.android.ex.photo.f
    public void o0() {
        super.o0();
        if (M() == null || M().T()) {
            return;
        }
        x0(false, false);
    }

    @Override // com.android.ex.photo.e
    public void p(Uri uri, boolean z) {
        AbstractC5838l abstractC5838lJ0;
        d dVar;
        if (M() == null || (abstractC5838lJ0 = J0(uri.toString())) == null) {
            return;
        }
        List<AbstractC5838l> listSingletonList = this.S0;
        if (!C0740v.A(abstractC5838lJ0)) {
            try {
                u uVarO = abstractC5838lJ0.O();
                if (uVarO != null && C0740v.y(uVarO, abstractC5838lJ0)) {
                    try {
                        listSingletonList = Collections.singletonList(uVarO);
                    } catch (C0718i unused) {
                    }
                    abstractC5838lJ0 = uVarO;
                }
            } catch (C0718i unused2) {
            }
        }
        Activity activity = (Activity) M().getContext();
        if (!C0740v.A(abstractC5838lJ0)) {
            ax.p221pa.c.h().g().b("NOT REACHABLE : IMAGE VIEWER").k().h("PLAY VIDEO:" + uri.toString() + ",fileinfo:" + abstractC5838lJ0.R()).i();
            Toast.makeText(activity, R.string.error, 1).show();
            return;
        }
        AbstractC0726g abstractC0726g = (AbstractC0726g) abstractC5838lJ0;
        if (z) {
            dVar = d.BUILT_IN;
        } else {
            ax.G1.c.a aVar = ax.G1.c.a.GENERAL;
            if (O.S3(activity, aVar, abstractC0726g, false) && !O.V3(activity, aVar, abstractC0726g, false)) {
                dVar = d.GENERAL;
            } else if (i.G(activity) && C0739u.A(abstractC0726g)) {
                dVar = d.BUILT_IN;
            } else {
                Intent intentN0 = N0(activity, abstractC0726g, true);
                dVar = (intentN0 == null || !C0734o.O(intentN0)) ? d.IN_IMAGE_VIEWER : d.BUILT_IN;
            }
        }
        if (dVar == d.BUILT_IN) {
            Uri uriF = C0734o.F(abstractC0726g, false);
            ArrayList<com.alphainventor.filemanager.viewer.c.a> arrayListI0 = I0(activity, uriF, listSingletonList);
            try {
                ax.p338y1.a.k().o("command", "file_open").c("loc", "ImageViewerActivity").c("ext", abstractC0726g.v()).c("result", "success").e();
                activity.startActivityForResult(C0736q.h(activity, uriF, arrayListI0, true, false), 36001);
                return;
            } catch (ActivityNotFoundException | NullPointerException | SecurityException e) {
                Toast.makeText(activity, R.string.error, 1).show();
                ax.p221pa.c.h().g().b("PVI:").m(e).i();
                return;
            }
        }
        if (dVar == d.GENERAL) {
            Fragment fragmentT = M().t();
            if (fragmentT instanceof C) {
                ((C) fragmentT).Y2(ax.G1.c.a.GENERAL, abstractC0726g, abstractC0726g.s(), false, false);
                return;
            }
            return;
        }
        Fragment fragmentT2 = M().t();
        if (fragmentT2 instanceof C) {
            ((C) fragmentT2).Y2(ax.G1.c.a.IN_IMAGE_VIEWER, abstractC0726g, abstractC0726g.s(), false, false);
        }
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public void w(ax.p129j2.a aVar, boolean z) {
        super.w(aVar, z);
        R0(aVar);
        L0(aVar);
        if (z) {
            return;
        }
        R0(aVar);
        Uri uri = Uri.parse(aVar.h3());
        if (i(uri)) {
            AbstractC5838l abstractC5838lJ0 = J0(uri.toString());
            if (abstractC5838lJ0 == null || !abstractC5838lJ0.n()) {
                P0(aVar);
            } else {
                aVar.g3().setVisibility(8);
            }
        }
    }

    @Override // com.android.ex.photo.f, com.android.ex.photo.e
    public ax.J0.c<ax.k2.b.a> x(int i, Bundle bundle, String str) {
        if (i != 1) {
            if (i == 2) {
                return new ax.p073f2.b(M().getContext(), this.U0, null, str, true, false);
            }
            if (i != 3) {
                return super.x(i, bundle, str);
            }
        }
        return new ax.p073f2.b(M().getContext(), this.U0, J0(str), str, false, bundle != null ? bundle.getBoolean("use_factory_if_possible", true) : true);
    }
}
