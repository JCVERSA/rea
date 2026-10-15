package com.pcloud.sdk.internal;

import ax.Sa.InterfaceC1531a;
import j$.util.DesugarCollections;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class x implements ax.Sa.k {
    private final InterfaceC1531a a;
    private final Date b;
    private final List<URL> c;

    x(InterfaceC1531a interfaceC1531a, Date date, List<URL> list) {
        this.a = interfaceC1531a;
        this.b = date;
        this.c = DesugarCollections.unmodifiableList(list);
    }

    @Override // ax.Sa.k
    public URL a() {
        return this.c.get(0);
    }

    public Date b() {
        return this.b;
    }

    public List<URL> c() {
        return this.c;
    }

    public String toString() {
        return String.format(Locale.US, "%s | Valid until:%s", a(), b());
    }
}
