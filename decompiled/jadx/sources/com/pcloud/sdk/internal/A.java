package com.pcloud.sdk.internal;

import ax.Sa.InterfaceC1531a;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class A extends RealRemoteEntry implements ax.Sa.w {
    private static final List<ax.Sa.u> p = DesugarCollections.unmodifiableList(new ArrayList(0));

    @ax.Z8.c("folderid")
    @ax.Z8.a
    private Long m;

    @ax.Z8.c("contents")
    @ax.Z8.a
    private final List<ax.Sa.u> n;

    @ax.Z8.c("cancreate ")
    @ax.Z8.a
    private boolean o;

    static class a implements ax.Y8.e<A> {
        private final InterfaceC1531a a;

        a(InterfaceC1531a interfaceC1531a) {
            this.a = interfaceC1531a;
        }

        @Override // ax.Y8.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public A a(Type type) {
            return new A(this.a);
        }
    }

    A(InterfaceC1531a interfaceC1531a) {
        super(interfaceC1531a);
        this.n = p;
        this.o = true;
    }

    @Override // ax.Sa.w
    public List<ax.Sa.u> b() {
        return this.n;
    }

    @Override // ax.Sa.w
    public long c() {
        return this.m.longValue();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ boolean delete() throws IOException {
        return super.delete();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry
    public /* bridge */ /* synthetic */ Date e() {
        return super.e();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        A a2 = (A) obj;
        if (this.m.equals(a2.m) && this.o == a2.o) {
            return this.n.equals(a2.n);
        }
        return false;
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final A j() {
        return this;
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ Date g() {
        return super.g();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ boolean h() {
        return super.h();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry
    public int hashCode() {
        return (((super.hashCode() * 31) + this.m.hashCode()) * 31) + this.n.hashCode();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ String i() {
        return super.i();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ String k() {
        return super.k();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ boolean l() {
        return super.l();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ boolean m() {
        return super.m();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ boolean n() {
        return super.n();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ ax.Sa.v o() {
        return super.o();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public /* bridge */ /* synthetic */ boolean q() {
        return super.q();
    }

    public String toString() {
        return String.format(Locale.US, "%s | ID:%s | Created:%s | Modified: %s | Child count:%s", i(), k(), e(), g(), this.n == p ? "?" : Integer.valueOf(b().size()));
    }
}
