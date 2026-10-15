package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
final class j implements DynamiteModule.b {
    j() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0567b a(Context context, String str, DynamiteModule.b.a aVar) throws DynamiteModule.a {
        int iA;
        DynamiteModule.b.C0567b c0567b = new DynamiteModule.b.C0567b();
        int iB = aVar.b(context, str);
        c0567b.a = iB;
        int i = 1;
        int i2 = 0;
        if (iB != 0) {
            iA = aVar.a(context, str, false);
            c0567b.b = iA;
        } else {
            iA = aVar.a(context, str, true);
            c0567b.b = iA;
        }
        int i3 = c0567b.a;
        if (i3 == 0) {
            if (iA == 0) {
                i = 0;
            }
            c0567b.c = i;
            return c0567b;
        }
        i2 = i3;
        if (iA < i2) {
            i = -1;
        }
        c0567b.c = i;
        return c0567b;
    }
}
