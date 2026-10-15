package com.google.gson;

import ax.Y8.c;
import ax.Y8.d;
import ax.Y8.e;
import ax.Y8.h;
import ax.Y8.q;
import ax.Y8.s;
import ax.Y8.t;
import ax.Y8.v;
import ax.Y8.w;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class a {
    private Excluder a = Excluder.m0;
    private s b = s.q;
    private d c = c.q;
    private final Map<Type, e<?>> d = new HashMap();
    private final List<w> e = new ArrayList();
    private final List<w> f = new ArrayList();
    private boolean g = false;
    private String h = Gson.z;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private v r = Gson.B;
    private v s = Gson.C;
    private final LinkedList<t> t = new LinkedList<>();

    private void a(String str, int i, int i2, List<w> list) {
        w wVarB;
        w wVarB2;
        boolean z = com.google.gson.internal.sql.a.a;
        w wVarA = null;
        if (str != null && !str.trim().isEmpty()) {
            wVarB = DefaultDateTypeAdapter.b.b.b(str);
            if (z) {
                wVarA = com.google.gson.internal.sql.a.c.b(str);
                wVarB2 = com.google.gson.internal.sql.a.b.b(str);
            } else {
                wVarB2 = null;
            }
        } else {
            if (i == 2 || i2 == 2) {
                return;
            }
            w wVarA2 = DefaultDateTypeAdapter.b.b.a(i, i2);
            if (z) {
                wVarA = com.google.gson.internal.sql.a.c.a(i, i2);
                w wVarA3 = com.google.gson.internal.sql.a.b.a(i, i2);
                wVarB = wVarA2;
                wVarB2 = wVarA3;
            } else {
                wVarB = wVarA2;
                wVarB2 = null;
            }
        }
        list.add(wVarB);
        if (z) {
            list.add(wVarA);
            list.add(wVarB2);
        }
    }

    public Gson b() {
        List<w> arrayList = new ArrayList<>(this.e.size() + this.f.size() + 3);
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        a(this.h, this.i, this.j, arrayList);
        return new Gson(this.a, this.c, new HashMap(this.d), this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.q, this.b, this.h, this.i, this.j, new ArrayList(this.e), new ArrayList(this.f), arrayList, this.r, this.s, new ArrayList(this.t));
    }

    public a c() {
        this.a = this.a.k();
        return this;
    }

    public a d(Type type, Object obj) {
        boolean z = obj instanceof q;
        ax.p012a9.a.a(z || (obj instanceof h) || (obj instanceof e) || (obj instanceof TypeAdapter));
        if (obj instanceof e) {
            this.d.put(type, (e) obj);
        }
        if (z || (obj instanceof h)) {
            this.e.add(TreeTypeAdapter.g(ax.p076e9.a.b(type), obj));
        }
        if (obj instanceof TypeAdapter) {
            this.e.add(TypeAdapters.a(ax.p076e9.a.b(type), (TypeAdapter) obj));
        }
        return this;
    }

    public a e(w wVar) {
        this.e.add(wVar);
        return this;
    }

    public a f() {
        this.g = true;
        return this;
    }

    public a g() {
        this.n = true;
        return this;
    }
}
