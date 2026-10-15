package com.google.android.gms.measurement.internal;

import ax.H6.F7;
import ax.H6.I7;
import java.util.List;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.u2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6412u2 implements I7 {
    private final /* synthetic */ C6389q2 a;

    C6412u2(C6389q2 c6389q2) {
        this.a = c6389q2;
    }

    @Override // ax.H6.I7
    public final void a(F7 f7, String str, List<String> list, boolean z, boolean z2) {
        X1 x1F;
        int i = C6430x2.a[f7.ordinal()];
        if (i == 1) {
            x1F = this.a.j().F();
        } else if (i != 2) {
            if (i != 3) {
                x1F = i != 4 ? this.a.j().J() : this.a.j().K();
            } else if (z) {
                x1F = this.a.j().N();
            } else {
                x1F = !z2 ? this.a.j().M() : this.a.j().L();
            }
        } else if (z) {
            x1F = this.a.j().I();
        } else {
            x1F = !z2 ? this.a.j().H() : this.a.j().G();
        }
        int size = list.size();
        if (size == 1) {
            x1F.b(str, list.get(0));
            return;
        }
        if (size == 2) {
            x1F.c(str, list.get(0), list.get(1));
        } else if (size != 3) {
            x1F.a(str);
        } else {
            x1F.d(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
