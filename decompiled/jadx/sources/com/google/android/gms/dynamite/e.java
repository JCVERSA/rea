package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
final class e implements DynamiteModule.b {
    e() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0567b a(Context context, String str, DynamiteModule.b.a aVar) throws DynamiteModule.a {
        DynamiteModule.b.C0567b c0567b = new DynamiteModule.b.C0567b();
        int iB = aVar.b(context, str);
        c0567b.a = iB;
        if (iB != 0) {
            c0567b.c = -1;
            return c0567b;
        }
        int iA = aVar.a(context, str, true);
        c0567b.b = iA;
        if (iA != 0) {
            c0567b.c = 1;
        }
        return c0567b;
    }
}
