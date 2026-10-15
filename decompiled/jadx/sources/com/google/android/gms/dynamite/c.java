package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
final class c implements DynamiteModule.b.a {
    c() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.b.a
    public final int a(Context context, String str, boolean z) throws DynamiteModule.a {
        return DynamiteModule.f(context, str, z);
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.b.a
    public final int b(Context context, String str) {
        return DynamiteModule.a(context, str);
    }
}
