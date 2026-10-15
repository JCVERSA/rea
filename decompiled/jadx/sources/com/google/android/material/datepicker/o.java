package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import ax.p035c0.C1839d0;

/* JADX INFO: loaded from: classes3.dex */
class o extends RecyclerView.h<b> {
    private final com.google.android.material.datepicker.a d;
    private final ax.p122h7.d<?> e;
    private final ax.p122h7.f f;
    private final j.m g;
    private final int h;

    class a implements AdapterView.OnItemClickListener {
        final /* synthetic */ MaterialCalendarGridView q;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.q = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.q.getAdapter().r(i)) {
                o.this.g.a(this.q.getAdapter().getItem(i).longValue());
            }
        }
    }

    public static class b extends RecyclerView.F {
        final TextView u;
        final MaterialCalendarGridView v;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(ax.R6.g.w);
            this.u = textView;
            C1839d0.m0(textView, true);
            this.v = (MaterialCalendarGridView) linearLayout.findViewById(ax.R6.g.s);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    o(Context context, ax.p122h7.d<?> dVar, com.google.android.material.datepicker.a aVar, ax.p122h7.f fVar, j.m mVar) {
        m mVarN = aVar.n();
        m mVarI = aVar.i();
        m mVarL = aVar.l();
        if (mVarN.compareTo(mVarL) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (mVarL.compareTo(mVarI) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.h = (n.m0 * j.l3(context)) + (k.D3(context) ? j.l3(context) : 0);
        this.d = aVar;
        this.e = dVar;
        this.f = fVar;
        this.g = mVar;
        K(true);
    }

    m O(int i) {
        return this.d.n().J(i);
    }

    CharSequence P(int i) {
        return O(i).H();
    }

    int Q(m mVar) {
        return this.d.n().K(mVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public void B(b bVar, int i) {
        m mVarJ = this.d.n().J(i);
        bVar.u.setText(mVarJ.H());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.v.findViewById(ax.R6.g.s);
        if (materialCalendarGridView.getAdapter() == null || !mVarJ.equals(materialCalendarGridView.getAdapter().q)) {
            n nVar = new n(mVarJ, this.e, this.d, this.f);
            materialCalendarGridView.setNumColumns(mVarJ.Z);
            materialCalendarGridView.setAdapter((ListAdapter) nVar);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter().q(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public b D(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(ax.R6.i.s, viewGroup, false);
        if (!k.D3(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.q(-1, this.h));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    public int l() {
        return this.d.k();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    public long m(int i) {
        return this.d.n().J(i).I();
    }
}
