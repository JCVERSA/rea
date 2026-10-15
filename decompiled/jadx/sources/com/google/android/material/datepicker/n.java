package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
class n extends BaseAdapter {
    static final int m0 = t.r().getMaximum(4);
    private static final int n0 = (t.r().getMaximum(5) + t.r().getMaximum(7)) - 1;
    final ax.p122h7.d<?> X;
    private Collection<Long> Y;
    c Z;
    final a k0;
    final ax.p122h7.f l0;
    final m q;

    n(m mVar, ax.p122h7.d<?> dVar, a aVar, ax.p122h7.f fVar) {
        this.q = mVar;
        this.X = dVar;
        this.k0 = aVar;
        this.l0 = fVar;
        this.Y = dVar.x();
    }

    private String c(Context context, long j) {
        return g.e(context, j, l(j), k(j), g(j));
    }

    private void f(Context context) {
        if (this.Z == null) {
            this.Z = new c(context);
        }
    }

    private boolean j(long j) {
        Iterator<Long> it = this.X.x().iterator();
        while (it.hasNext()) {
            if (t.a(j) == t.a(it.next().longValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean l(long j) {
        return t.p().getTimeInMillis() == j;
    }

    private void o(TextView textView, long j, int i) {
        b bVar;
        boolean z;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        String strC = c(context, j);
        textView.setContentDescription(strC);
        boolean zV = this.k0.h().v(j);
        if (zV) {
            textView.setEnabled(true);
            boolean zJ = j(j);
            textView.setSelected(zJ);
            if (zJ) {
                bVar = this.Z.b;
            } else {
                bVar = l(j) ? this.Z.c : this.Z.a;
            }
            z = zJ;
        } else {
            textView.setEnabled(false);
            bVar = this.Z.g;
            z = false;
        }
        ax.p122h7.f fVar = this.l0;
        if (fVar == null || i == -1) {
            bVar.d(textView);
            return;
        }
        m mVar = this.q;
        int i2 = mVar.Y;
        int i3 = mVar.X;
        bVar.e(textView, fVar.a(context, i2, i3, i, zV, z), this.l0.h(context, i2, i3, i, zV, z));
        textView.setCompoundDrawables(this.l0.c(context, i2, i3, i, zV, z), this.l0.e(context, i2, i3, i, zV, z), this.l0.d(context, i2, i3, i, zV, z), this.l0.b(context, i2, i3, i, zV, z));
        textView.setContentDescription(this.l0.f(context, i2, i3, i, zV, z, strC));
    }

    private void p(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (m.B(j).equals(this.q)) {
            int iG = this.q.G(j);
            o((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().a(iG) - materialCalendarGridView.getFirstVisiblePosition()), j, iG);
        }
    }

    int a(int i) {
        return b() + (i - 1);
    }

    int b() {
        return this.q.E(this.k0.j());
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i) {
        if (i < b() || i > m()) {
            return null;
        }
        return Long.valueOf(this.q.F(n(i)));
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0054  */
    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public TextView getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        f(viewGroup.getContext());
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(ax.R6.i.p, viewGroup, false);
        }
        int iB = i - b();
        if (iB >= 0) {
            m mVar = this.q;
            if (iB >= mVar.k0) {
                textView.setVisibility(8);
                textView.setEnabled(false);
                i2 = -1;
            } else {
                i2 = iB + 1;
                textView.setTag(mVar);
                textView.setText(String.format(textView.getResources().getConfiguration().locale, "%d", Integer.valueOf(i2)));
                textView.setVisibility(0);
                textView.setEnabled(true);
            }
        } else {
            textView.setVisibility(8);
            textView.setEnabled(false);
            i2 = -1;
        }
        Long item = getItem(i);
        if (item == null) {
            return textView;
        }
        o(textView, item.longValue(), i2);
        return textView;
    }

    boolean g(long j) {
        Iterator<ax.p019b0.c<Long, Long>> it = this.X.m().iterator();
        while (it.hasNext()) {
            Long l = it.next().b;
            if (l != null && l.longValue() == j) {
                return true;
            }
        }
        return false;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return n0;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.q.Z;
    }

    boolean h(int i) {
        return i % this.q.Z == 0;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    boolean i(int i) {
        return (i + 1) % this.q.Z == 0;
    }

    boolean k(long j) {
        Iterator<ax.p019b0.c<Long, Long>> it = this.X.m().iterator();
        while (it.hasNext()) {
            Long l = it.next().a;
            if (l != null && l.longValue() == j) {
                return true;
            }
        }
        return false;
    }

    int m() {
        return (b() + this.q.k0) - 1;
    }

    int n(int i) {
        return (i - b()) + 1;
    }

    public void q(MaterialCalendarGridView materialCalendarGridView) {
        Iterator<Long> it = this.Y.iterator();
        while (it.hasNext()) {
            p(materialCalendarGridView, it.next().longValue());
        }
        ax.p122h7.d<?> dVar = this.X;
        if (dVar != null) {
            Iterator<Long> it2 = dVar.x().iterator();
            while (it2.hasNext()) {
                p(materialCalendarGridView, it2.next().longValue());
            }
            this.Y = this.X.x();
        }
    }

    boolean r(int i) {
        return i >= b() && i <= m();
    }
}
