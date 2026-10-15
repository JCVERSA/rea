package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class l<S> extends p<S> {
    private int k1;
    private ax.p122h7.d<S> l1;
    private com.google.android.material.datepicker.a m1;

    class a extends ax.p122h7.k<S> {
        a() {
        }

        @Override // ax.p122h7.k
        public void a() {
            Iterator<ax.p122h7.k<S>> it = l.this.j1.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }

        @Override // ax.p122h7.k
        public void b(S s) {
            Iterator<ax.p122h7.k<S>> it = l.this.j1.iterator();
            while (it.hasNext()) {
                it.next().b(s);
            }
        }
    }

    static <T> l<T> W2(ax.p122h7.d<T> dVar, int i, com.google.android.material.datepicker.a aVar) {
        l<T> lVar = new l<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i);
        bundle.putParcelable("DATE_SELECTOR_KEY", dVar);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", aVar);
        lVar.F2(bundle);
        return lVar;
    }

    @Override // androidx.fragment.app.Fragment
    public View B1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.l1.q(layoutInflater.cloneInContext(new ContextThemeWrapper(getContext(), this.k1)), viewGroup, bundle, this.m1, new a());
    }

    @Override // androidx.fragment.app.Fragment
    public void S1(Bundle bundle) {
        super.S1(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.k1);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.l1);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.m1);
    }

    @Override // androidx.fragment.app.Fragment
    public void x1(Bundle bundle) {
        super.x1(bundle);
        if (bundle == null) {
            bundle = s0();
        }
        this.k1 = bundle.getInt("THEME_RES_ID_KEY");
        this.l1 = (ax.p122h7.d) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.m1 = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }
}
