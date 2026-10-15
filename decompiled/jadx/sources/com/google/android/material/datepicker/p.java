package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
abstract class p<S> extends Fragment {
    protected final LinkedHashSet<ax.p122h7.k<S>> j1 = new LinkedHashSet<>();

    p() {
    }

    boolean U2(ax.p122h7.k<S> kVar) {
        return this.j1.add(kVar);
    }

    void V2() {
        this.j1.clear();
    }
}
