package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class j<S> extends p<S> {
    private int k1;
    private ax.p122h7.d<S> l1;
    private com.google.android.material.datepicker.a m1;
    private ax.p122h7.f n1;
    private com.google.android.material.datepicker.m o1;
    private l p1;
    private com.google.android.material.datepicker.c q1;
    private RecyclerView r1;
    private RecyclerView s1;
    private View t1;
    private View u1;
    private View v1;
    private View w1;
    private MaterialButton x1;
    private AccessibilityManager y1;
    static final Object z1 = "MONTHS_VIEW_GROUP_TAG";
    static final Object A1 = "NAVIGATION_PREV_TAG";
    static final Object B1 = "NAVIGATION_NEXT_TAG";
    static final Object C1 = "SELECTOR_TOGGLE_TAG";

    class a implements View.OnClickListener {
        final /* synthetic */ o q;

        a(o oVar) {
            this.q = oVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j.this.r3(this.q.O(j.this.n3().f2() - 1));
        }
    }

    class b implements Runnable {
        final /* synthetic */ int q;

        b(int i) {
            this.q = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.s1.F1(this.q);
        }
    }

    class c extends C1832a {
        c() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.o0(null);
        }
    }

    class d extends r {
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.I = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void R1(RecyclerView.B b, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = j.this.s1.getWidth();
                iArr[1] = j.this.s1.getWidth();
            } else {
                iArr[0] = j.this.s1.getHeight();
                iArr[1] = j.this.s1.getHeight();
            }
        }
    }

    class e implements m {
        e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.j.m
        public void a(long j) {
            if (j.this.m1.h().v(j)) {
                j.this.l1.D(j);
                Iterator<ax.p122h7.k<S>> it = j.this.j1.iterator();
                while (it.hasNext()) {
                    it.next().b(j.this.l1.y());
                }
                j.this.s1.getAdapter().r();
                if (j.this.r1 != null) {
                    j.this.r1.getAdapter().r();
                }
            }
        }
    }

    class f extends C1832a {
        f() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.G0(false);
        }
    }

    class g extends RecyclerView.o {
        private final Calendar a = t.r();
        private final Calendar b = t.r();

        g() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.B b) {
            if ((recyclerView.getAdapter() instanceof u) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                u uVar = (u) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (ax.p019b0.c<Long, Long> cVar : j.this.l1.m()) {
                    Long l = cVar.a;
                    if (l != null && cVar.b != null) {
                        this.a.setTimeInMillis(l.longValue());
                        this.b.setTimeInMillis(cVar.b.longValue());
                        int iP = uVar.P(this.a.get(1));
                        int iP2 = uVar.P(this.b.get(1));
                        View viewI = gridLayoutManager.I(iP);
                        View viewI2 = gridLayoutManager.I(iP2);
                        int iZ2 = iP / gridLayoutManager.Z2();
                        int iZ3 = iP2 / gridLayoutManager.Z2();
                        int i = iZ2;
                        while (i <= iZ3) {
                            View viewI3 = gridLayoutManager.I(gridLayoutManager.Z2() * i);
                            if (viewI3 != null) {
                                canvas.drawRect((i != iZ2 || viewI == null) ? 0 : viewI.getLeft() + (viewI.getWidth() / 2), viewI3.getTop() + j.this.q1.d.c(), (i != iZ3 || viewI2 == null) ? recyclerView.getWidth() : viewI2.getLeft() + (viewI2.getWidth() / 2), viewI3.getBottom() - j.this.q1.d.b(), j.this.q1.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    class h extends C1832a {
        h() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.b(new H.a(16, j.this.w1.getVisibility() == 0 ? j.this.V0(ax.R6.k.O) : j.this.V0(ax.R6.k.M)));
        }
    }

    class i extends RecyclerView.u {
        final /* synthetic */ o a;

        i(o oVar) {
            this.a = oVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.u
        public void b(RecyclerView recyclerView, int i, int i2) {
            int iD2 = i < 0 ? j.this.n3().d2() : j.this.n3().f2();
            com.google.android.material.datepicker.m mVarO = this.a.O(iD2);
            j.this.o1 = mVarO;
            j.this.x1.setText(this.a.P(iD2));
            j.this.v3(this.a.Q(mVarO));
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.datepicker.j$j, reason: collision with other inner class name */
    class ViewOnClickListenerC0575j implements View.OnClickListener {
        ViewOnClickListenerC0575j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j.this.u3();
        }
    }

    class k implements View.OnClickListener {
        final /* synthetic */ o q;

        k(o oVar) {
            this.q = oVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j.this.r3(this.q.O(j.this.n3().d2() + 1));
        }
    }

    enum l {
        DAY,
        YEAR
    }

    interface m {
        void a(long j);
    }

    private void f3(View view, o oVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(ax.R6.g.t);
        this.x1 = materialButton;
        materialButton.setTag(C1);
        C1839d0.l0(this.x1, new h());
        View viewFindViewById = view.findViewById(ax.R6.g.v);
        this.t1 = viewFindViewById;
        viewFindViewById.setTag(A1);
        View viewFindViewById2 = view.findViewById(ax.R6.g.u);
        this.u1 = viewFindViewById2;
        viewFindViewById2.setTag(B1);
        this.v1 = view.findViewById(ax.R6.g.C);
        this.w1 = view.findViewById(ax.R6.g.x);
        s3(l.DAY);
        this.x1.setText(this.o1.H());
        this.s1.n(new i(oVar));
        this.x1.setOnClickListener(new ViewOnClickListenerC0575j());
        this.u1.setOnClickListener(new k(oVar));
        this.t1.setOnClickListener(new a(oVar));
        v3(oVar.Q(this.o1));
    }

    private RecyclerView.o g3() {
        return new g();
    }

    static int l3(Context context) {
        return context.getResources().getDimensionPixelSize(ax.R6.e.c0);
    }

    private static int m3(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(ax.R6.e.k0) + resources.getDimensionPixelOffset(ax.R6.e.l0) + resources.getDimensionPixelOffset(ax.R6.e.j0);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(ax.R6.e.e0);
        int i2 = n.m0;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(ax.R6.e.c0) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(ax.R6.e.i0)) + resources.getDimensionPixelOffset(ax.R6.e.a0);
    }

    public static <T> j<T> o3(ax.p122h7.d<T> dVar, int i2, com.google.android.material.datepicker.a aVar, ax.p122h7.f fVar) {
        j<T> jVar = new j<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dVar);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", aVar);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", fVar);
        bundle.putParcelable("CURRENT_MONTH_KEY", aVar.l());
        jVar.F2(bundle);
        return jVar;
    }

    private void p3(int i2) {
        this.s1.post(new b(i2));
    }

    private void t3() {
        C1839d0.l0(this.s1, new f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v3(int i2) {
        this.u1.setEnabled(i2 + 1 < this.s1.getAdapter().l());
        this.t1.setEnabled(i2 - 1 >= 0);
    }

    @Override // androidx.fragment.app.Fragment
    public View B1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.k1);
        this.q1 = new com.google.android.material.datepicker.c(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        this.y1 = (AccessibilityManager) z2().getSystemService("accessibility");
        com.google.android.material.datepicker.m mVarN = this.m1.n();
        if (com.google.android.material.datepicker.k.D3(contextThemeWrapper)) {
            i2 = ax.R6.i.t;
            i3 = 1;
        } else {
            i2 = ax.R6.i.r;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        viewInflate.setMinimumHeight(m3(z2()));
        GridView gridView = (GridView) viewInflate.findViewById(ax.R6.g.y);
        C1839d0.l0(gridView, new c());
        int iJ = this.m1.j();
        gridView.setAdapter((ListAdapter) (iJ > 0 ? new com.google.android.material.datepicker.i(iJ) : new com.google.android.material.datepicker.i()));
        gridView.setNumColumns(mVarN.Z);
        gridView.setEnabled(false);
        this.s1 = (RecyclerView) viewInflate.findViewById(ax.R6.g.B);
        this.s1.setLayoutManager(new d(getContext(), i3, false, i3));
        this.s1.setTag(z1);
        o oVar = new o(contextThemeWrapper, this.l1, this.m1, this.n1, new e());
        this.s1.setAdapter(oVar);
        int integer = contextThemeWrapper.getResources().getInteger(ax.R6.h.c);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(ax.R6.g.C);
        this.r1 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.r1.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.r1.setAdapter(new u(this));
            this.r1.j(g3());
        }
        if (viewInflate.findViewById(ax.R6.g.t) != null) {
            f3(viewInflate, oVar);
        }
        if (!com.google.android.material.datepicker.k.D3(contextThemeWrapper)) {
            new androidx.recyclerview.widget.p().b(this.s1);
        }
        this.s1.w1(oVar.Q(this.o1));
        t3();
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void S1(Bundle bundle) {
        super.S1(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.k1);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.l1);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.m1);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.n1);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.o1);
    }

    @Override // com.google.android.material.datepicker.p
    public boolean U2(ax.p122h7.k<S> kVar) {
        return super.U2(kVar);
    }

    com.google.android.material.datepicker.a h3() {
        return this.m1;
    }

    com.google.android.material.datepicker.c i3() {
        return this.q1;
    }

    com.google.android.material.datepicker.m j3() {
        return this.o1;
    }

    public ax.p122h7.d<S> k3() {
        return this.l1;
    }

    LinearLayoutManager n3() {
        return (LinearLayoutManager) this.s1.getLayoutManager();
    }

    void q3() {
        MaterialButton materialButton = this.x1;
        if (materialButton != null) {
            materialButton.sendAccessibilityEvent(8);
        }
    }

    void r3(com.google.android.material.datepicker.m mVar) {
        o oVar = (o) this.s1.getAdapter();
        int iQ = oVar.Q(mVar);
        AccessibilityManager accessibilityManager = this.y1;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            int iQ2 = iQ - oVar.Q(this.o1);
            boolean z = Math.abs(iQ2) > 3;
            boolean z2 = iQ2 > 0;
            this.o1 = mVar;
            if (z && z2) {
                this.s1.w1(iQ - 3);
                p3(iQ);
            } else if (z) {
                this.s1.w1(iQ + 3);
                p3(iQ);
            } else {
                p3(iQ);
            }
        } else {
            this.o1 = mVar;
            this.s1.w1(iQ);
        }
        v3(iQ);
    }

    void s3(l lVar) {
        this.p1 = lVar;
        if (lVar == l.YEAR) {
            this.r1.getLayoutManager().C1(((u) this.r1.getAdapter()).P(this.o1.Y));
            this.v1.setVisibility(0);
            this.w1.setVisibility(8);
            this.t1.setVisibility(8);
            this.u1.setVisibility(8);
            return;
        }
        if (lVar == l.DAY) {
            this.v1.setVisibility(8);
            this.w1.setVisibility(0);
            this.t1.setVisibility(0);
            this.u1.setVisibility(0);
            r3(this.o1);
        }
    }

    void u3() {
        l lVar = this.p1;
        l lVar2 = l.YEAR;
        if (lVar == lVar2) {
            s3(l.DAY);
            this.s1.announceForAccessibility(V0(ax.R6.k.P));
        } else if (lVar == l.DAY) {
            s3(lVar2);
            this.r1.announceForAccessibility(V0(ax.R6.k.Q));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void x1(Bundle bundle) {
        super.x1(bundle);
        if (bundle == null) {
            bundle = s0();
        }
        this.k1 = bundle.getInt("THEME_RES_ID_KEY");
        this.l1 = (ax.p122h7.d) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.m1 = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.n1 = (ax.p122h7.f) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.o1 = (com.google.android.material.datepicker.m) bundle.getParcelable("CURRENT_MONTH_KEY");
    }
}
