package com.pcloud.sdk.internal;

import ax.Sa.InterfaceC1531a;
import java.lang.reflect.Type;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class z extends RealRemoteEntry implements ax.Sa.v {

    @ax.Z8.c("fileid")
    @ax.Z8.a
    private long m;

    @ax.Z8.c("contenttype")
    @ax.Z8.a
    private String n;

    @ax.Z8.c("size")
    @ax.Z8.a
    private long o;

    @ax.Z8.c("hash")
    @ax.Z8.a
    private String p;

    @ax.Z8.c("thumb")
    @ax.Z8.a
    private Boolean q;

    static class a implements ax.Y8.e<z> {
        private final InterfaceC1531a a;

        a(InterfaceC1531a interfaceC1531a) {
            this.a = interfaceC1531a;
        }

        @Override // ax.Y8.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public z a(Type type) {
            return new z(this.a);
        }
    }

    z(InterfaceC1531a interfaceC1531a) {
        super(interfaceC1531a);
    }

    @Override // ax.Sa.v
    public boolean a() {
        return this.q.booleanValue();
    }

    @Override // ax.Sa.v
    public long d() {
        return this.m;
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        z zVar = (z) obj;
        if (this.m == zVar.m && this.o == zVar.o && this.n.equals(zVar.n)) {
            return this.p.equals(zVar.p);
        }
        return false;
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        long j = this.m;
        int iHashCode2 = (((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.n.hashCode()) * 31;
        long j2 = this.o;
        return ((iHashCode2 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.p.hashCode();
    }

    @Override // com.pcloud.sdk.internal.RealRemoteEntry, ax.Sa.u
    public ax.Sa.v o() {
        return this;
    }

    @Override // ax.Sa.v
    public long size() {
        return this.o;
    }

    public String toString() {
        return String.format(Locale.US, "%s | ID:%s | Created:%s | Modified: %s | Size:%s", i(), k(), e(), g(), Long.valueOf(size()));
    }
}
