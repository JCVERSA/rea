package com.google.firebase.abt.component;

import android.content.Context;
import ax.H8.b;
import ax.p091f8.c;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class a {
    private final Map<String, c> a = new HashMap();
    private final Context b;
    private final b<ax.p123h8.a> c;

    protected a(Context context, b<ax.p123h8.a> bVar) {
        this.b = context;
        this.c = bVar;
    }

    protected c a(String str) {
        return new c(this.b, this.c, str);
    }

    public synchronized c b(String str) {
        try {
            if (!this.a.containsKey(str)) {
                this.a.put(str, a(str));
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.a.get(str);
    }
}
