package com.google.android.material.datepicker;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
class r extends LinearLayoutManager {

    class a extends androidx.recyclerview.widget.l {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.l
        protected float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    r(Context context, int i, boolean z) {
        super(context, i, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.p
    public void N1(RecyclerView recyclerView, RecyclerView.B b, int i) {
        a aVar = new a(recyclerView.getContext());
        aVar.p(i);
        O1(aVar);
    }
}
