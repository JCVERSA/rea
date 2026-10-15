package com.alphainventor.filemanager.viewer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.media3.ui.C0662d;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ax.P0.C1375v;
import ax.P0.H;
import ax.P0.Q;
import ax.P0.T;
import ax.P0.V;
import ax.S0.C1498a;
import ax.S0.d0;
import ax.Y7.AbstractC1656z;
import ax.p005a2.C1767f;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private final PopupWindow a;
    private final RecyclerView b;
    private final Context c;
    private final c d;
    private final e e;
    private final b f;
    private final C1767f g;
    private final int h;
    private boolean i;
    private h j;

    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
        }
    }

    public final class b extends AbstractC0553g {
        public b(Context context, c cVar) {
            super(context, cVar);
        }

        public static /* synthetic */ void U(b bVar, H h, View view) {
            bVar.getClass();
            if (h == null || !h.Z(29)) {
                return;
            }
            ((H) d0.i(h)).p(h.i0().I().G(1).U(1, false).F());
            g.this.a.dismiss();
        }

        private boolean V(T t) {
            for (int i = 0; i < this.d.size(); i++) {
                if (t.D.containsKey(this.d.get(i).a.a())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.alphainventor.filemanager.viewer.g.AbstractC0553g
        public void R(d dVar) {
            dVar.u.setText(R.string.exo_track_selection_auto);
            final H h = g.this.h();
            if (h == null) {
                return;
            }
            dVar.v.setVisibility(V(((H) C1498a.e(h)).i0()) ? 4 : 0);
            dVar.a.setOnClickListener(new View.OnClickListener() { // from class: ax.B3.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    com.alphainventor.filemanager.viewer.g.b.U(this.q, h, view);
                }
            });
        }

        @Override // com.alphainventor.filemanager.viewer.g.AbstractC0553g
        public void T(String str) {
        }

        public void W(List<f> list) {
            this.d = list;
        }
    }

    public interface c {
        H b();

        C0662d i();
    }

    public static class d extends RecyclerView.F {
        public final TextView u;
        public final View v;

        public d(View view) {
            super(view);
            if (d0.a < 26) {
                view.setFocusable(true);
            }
            this.u = (TextView) view.findViewById(R.id.exo_text);
            this.v = view.findViewById(R.id.exo_check);
        }
    }

    public final class e extends AbstractC0553g {
        public e(Context context, c cVar) {
            super(context, cVar);
        }

        public static /* synthetic */ void U(e eVar, View view) {
            g.this.l();
            if (g.this.j != null) {
                g.this.j.a(false);
            }
            g.this.a.dismiss();
        }

        @Override // com.alphainventor.filemanager.viewer.g.AbstractC0553g, androidx.recyclerview.widget.RecyclerView.h
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public void B(d dVar, int i) {
            super.B(dVar, i);
            if (i > 0) {
                dVar.v.setVisibility(this.d.get(i + (-1)).a() ? 0 : 4);
            }
        }

        @Override // com.alphainventor.filemanager.viewer.g.AbstractC0553g
        public void R(d dVar) {
            boolean z;
            dVar.u.setText(R.string.exo_track_selection_none);
            int i = 0;
            while (true) {
                if (i >= this.d.size()) {
                    z = true;
                    break;
                } else {
                    if (this.d.get(i).a()) {
                        z = false;
                        break;
                    }
                    i++;
                }
            }
            dVar.v.setVisibility(z ? 0 : 4);
            dVar.a.setOnClickListener(new View.OnClickListener() { // from class: ax.B3.j
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    com.alphainventor.filemanager.viewer.g.e.U(this.q, view);
                }
            });
        }

        @Override // com.alphainventor.filemanager.viewer.g.AbstractC0553g
        public void T(String str) {
            if (g.this.j != null) {
                g.this.j.a(true);
            }
        }

        public void V(List<f> list) {
            this.d = list;
        }
    }

    public static final class f {
        public final V.a a;
        public final int b;
        public final String c;

        public f(V v, int i, int i2, String str) {
            this.a = v.a().get(i);
            this.b = i2;
            this.c = str;
        }

        public boolean a() {
            return this.a.i(this.b);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.viewer.g$g, reason: collision with other inner class name */
    public abstract class AbstractC0553g extends RecyclerView.h<d> {
        protected List<f> d = new ArrayList();
        protected Context e;
        protected c f;

        protected AbstractC0553g(Context context, c cVar) {
            this.e = context;
            this.f = cVar;
        }

        public static /* synthetic */ void N(AbstractC0553g abstractC0553g, H h, f fVar, View view) {
            g.this.m(h, fVar);
            abstractC0553g.T(fVar.c);
            g.this.a.dismiss();
        }

        protected void O() {
            this.d = Collections.EMPTY_LIST;
        }

        protected Context P() {
            return this.e;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.h
        /* JADX INFO: renamed from: Q */
        public void B(d dVar, int i) {
            final H h = g.this.h();
            if (h == null) {
                return;
            }
            if (i == 0) {
                R(dVar);
                return;
            }
            final f fVar = this.d.get(i - 1);
            boolean z = h.i0().D.get(fVar.a.a()) != null && fVar.a();
            dVar.u.setText(fVar.c);
            dVar.v.setVisibility(z ? 0 : 4);
            dVar.a.setOnClickListener(new View.OnClickListener() { // from class: ax.B3.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    com.alphainventor.filemanager.viewer.g.AbstractC0553g.N(this.q, h, fVar, view);
                }
            });
        }

        protected abstract void R(d dVar);

        @Override // androidx.recyclerview.widget.RecyclerView.h
        /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
        public d D(ViewGroup viewGroup, int i) {
            return new d(LayoutInflater.from(P()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        protected abstract void T(String str);

        @Override // androidx.recyclerview.widget.RecyclerView.h
        public int l() {
            if (this.d.isEmpty()) {
                return 0;
            }
            return this.d.size() + 1;
        }
    }

    public interface h {
        void a(boolean z);
    }

    g(Context context, c cVar) {
        this.c = context;
        this.d = cVar;
        this.h = context.getResources().getDimensionPixelSize(R.dimen.exo_settings_offset);
        this.e = new e(context, cVar);
        this.f = new b(context, cVar);
        this.g = new C1767f(context.getResources());
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(g()).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        this.b = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(g()));
        PopupWindow popupWindow = new PopupWindow((View) recyclerView, -2, -2, true);
        this.a = popupWindow;
        if (d0.a < 23) {
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        popupWindow.setOnDismissListener(new a());
    }

    private void e(RecyclerView.h<?> hVar, View view) {
        this.b.setAdapter(hVar);
        p();
        this.i = false;
        this.a.dismiss();
        this.i = true;
        this.a.showAsDropDown(view, 0, ((-this.a.getHeight()) - this.h) - view.getHeight());
    }

    private AbstractC1656z<f> f(V v, int i) {
        AbstractC1656z.a aVar = new AbstractC1656z.a();
        AbstractC1656z<V.a> abstractC1656zA = v.a();
        for (int i2 = 0; i2 < abstractC1656zA.size(); i2++) {
            V.a aVar2 = abstractC1656zA.get(i2);
            if (aVar2.d() == i) {
                for (int i3 = 0; i3 < aVar2.a; i3++) {
                    if (aVar2.j(i3)) {
                        C1375v c1375vB = aVar2.b(i3);
                        if ((c1375vB.e & 2) == 0) {
                            aVar.a(new f(v, i2, i3, this.g.a(c1375vB)));
                        }
                    }
                }
            }
        }
        return aVar.k();
    }

    private Context g() {
        return this.c;
    }

    private void j() {
        H h2 = h();
        if (h2 == null) {
            C2461b.g("player is null when init trackselectionadapter");
        }
        this.e.O();
        this.f.O();
        if (h2 != null && h2.Z(30) && h2.Z(29)) {
            V vS = h2.S();
            this.f.W(f(vS, 1));
            this.e.V(f(vS, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(H h2, f fVar) {
        if (h2.Z(29)) {
            h2.p(h2.i0().I().N(new Q(fVar.a.a(), AbstractC1656z.z(Integer.valueOf(fVar.b)))).U(fVar.a.d(), false).F());
        }
    }

    private void p() {
        C0662d c0662dI = i();
        this.b.measure(0, 0);
        this.a.setWidth(Math.min(this.b.getMeasuredWidth(), c0662dI.getWidth() - (this.h * 2)));
        this.a.setHeight(Math.min(c0662dI.getHeight() - (this.h * 2), this.b.getMeasuredHeight()));
    }

    public void d() {
        this.i = false;
        this.a.dismiss();
        this.i = true;
    }

    protected H h() {
        return this.d.b();
    }

    protected C0662d i() {
        return this.d.i();
    }

    public void k() {
        H h2 = h();
        if (h2 == null) {
            return;
        }
        AbstractC1656z<f> abstractC1656zF = f(h2.S(), 3);
        if (abstractC1656zF.size() == 0) {
            C2461b.g("no subtitle track");
            return;
        }
        if (abstractC1656zF.size() != 1) {
            C2461b.g("subtitle count : " + abstractC1656zF.size());
        }
        m(h2, abstractC1656zF.get(0));
    }

    public void l() {
        H h2 = h();
        if (h2 == null || !h2.Z(29)) {
            return;
        }
        h2.p(h2.i0().I().G(3).M(-3).U(3, true).F());
    }

    public void n(View view) {
        e(this.f, view);
    }

    public void o(View view, h hVar) {
        e(this.e, view);
        this.j = hVar;
    }

    public void q() {
        j();
    }
}
