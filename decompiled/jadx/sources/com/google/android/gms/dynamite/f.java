package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
final class f implements DynamiteModule.b {
    f() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0567b a(Context context, String str, DynamiteModule.b.a aVar) throws DynamiteModule.a {
        DynamiteModule.b.C0567b c0567b = new DynamiteModule.b.C0567b();
        int iA = aVar.a(context, str, false);
        c0567b.b = iA;
        c0567b.c = iA != 0 ? 1 : 0;
        return c0567b;
    }
}
