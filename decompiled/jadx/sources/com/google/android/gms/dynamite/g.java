package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
final class g implements DynamiteModule.b {
    g() {
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001b A[DONT_INVERT, PHI: r4
      0x001b: PHI (r4v2 int) = (r4v1 int), (r4v3 int) binds: [B:3:0x0014, B:5:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:8:0x001d  */
    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0567b a(Context context, String str, DynamiteModule.b.a aVar) throws DynamiteModule.a {
        DynamiteModule.b.C0567b c0567b = new DynamiteModule.b.C0567b();
        c0567b.a = aVar.b(context, str);
        int i = 1;
        int iA = aVar.a(context, str, true);
        c0567b.b = iA;
        int i2 = c0567b.a;
        if (i2 == 0) {
            i2 = 0;
            if (iA == 0) {
                i = 0;
            } else if (i2 >= iA) {
                i = -1;
            }
        } else if (i2 >= iA) {
            i = -1;
        }
        c0567b.c = i;
        return c0567b;
    }
}
