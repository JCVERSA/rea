package com.google.android.material.bottomsheet;

import android.view.View;
import ax.p035c0.C1868s0;
import ax.p035c0.F0;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class b extends C1868s0.b {
    private final View c;
    private int d;
    private int e;
    private final int[] f;

    public b(View view) {
        super(0);
        this.f = new int[2];
        this.c = view;
    }

    @Override // ax.p035c0.C1868s0.b
    public void b(C1868s0 c1868s0) {
        this.c.setTranslationY(0.0f);
    }

    @Override // ax.p035c0.C1868s0.b
    public void c(C1868s0 c1868s0) {
        this.c.getLocationOnScreen(this.f);
        this.d = this.f[1];
    }

    @Override // ax.p035c0.C1868s0.b
    public F0 d(F0 f0, List<C1868s0> list) {
        for (C1868s0 c1868s0 : list) {
            if ((c1868s0.c() & F0.m.c()) != 0) {
                this.c.setTranslationY(ax.S6.a.c(this.e, 0, c1868s0.b()));
                break;
            }
        }
        return f0;
    }

    @Override // ax.p035c0.C1868s0.b
    public C1868s0.a e(C1868s0 c1868s0, C1868s0.a aVar) {
        this.c.getLocationOnScreen(this.f);
        int i = this.d - this.f[1];
        this.e = i;
        this.c.setTranslationY(i);
        return aVar;
    }
}
