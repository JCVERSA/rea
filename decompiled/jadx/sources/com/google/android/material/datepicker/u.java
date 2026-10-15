package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class u extends RecyclerView.h<b> {
    private final j<?> d;

    class a implements View.OnClickListener {
        final /* synthetic */ int q;

        a(int i) {
            this.q = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            u.this.d.r3(u.this.d.h3().f(m.k(this.q, u.this.d.j3().X)));
            u.this.d.s3(j.l.DAY);
            u.this.d.q3();
        }
    }

    public static class b extends RecyclerView.F {
        final TextView u;

        b(TextView textView) {
            super(textView);
            this.u = textView;
        }
    }

    u(j<?> jVar) {
        this.d = jVar;
    }

    private View.OnClickListener O(int i) {
        return new a(i);
    }

    int P(int i) {
        return i - this.d.h3().n().Y;
    }

    int Q(int i) {
        return this.d.h3().n().Y + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public void B(b bVar, int i) {
        int iQ = Q(i);
        bVar.u.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iQ)));
        TextView textView = bVar.u;
        textView.setContentDescription(g.k(textView.getContext(), iQ));
        c cVarI3 = this.d.i3();
        Calendar calendarP = t.p();
        com.google.android.material.datepicker.b bVar2 = calendarP.get(1) == iQ ? cVarI3.f : cVarI3.d;
        Iterator<Long> it = this.d.k3().x().iterator();
        while (it.hasNext()) {
            calendarP.setTimeInMillis(it.next().longValue());
            if (calendarP.get(1) == iQ) {
                bVar2 = cVarI3.e;
            }
        }
        bVar2.d(bVar.u);
        bVar.u.setSelected(bVar2 == cVarI3.e);
        bVar.u.setOnClickListener(O(iQ));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public b D(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(ax.R6.i.u, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.h
    public int l() {
        return this.d.h3().r();
    }
}
