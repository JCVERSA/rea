package com.pcloud.sdk.internal;

import ax.Sa.InterfaceC1531a;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
abstract class RealRemoteEntry implements ax.Sa.u {
    private final InterfaceC1531a a;

    @ax.Z8.c("id")
    @ax.Z8.a
    private String b;

    @ax.Z8.c("parentfolderid")
    @ax.Z8.a
    private long c;

    @ax.Z8.c("name")
    @ax.Z8.a
    private String d;

    @ax.Z8.c("modified")
    @ax.Z8.a
    private Date e;

    @ax.Z8.c("created")
    @ax.Z8.a
    private Date f;

    @ax.Z8.c("isfolder")
    @ax.Z8.a
    private boolean g;

    @ax.Z8.c("isshared")
    @ax.Z8.a
    private boolean h;

    @ax.Z8.c("ismine")
    @ax.Z8.a
    private boolean i;

    @ax.Z8.c("canread")
    @ax.Z8.a
    private boolean j = true;

    @ax.Z8.c("canmodify")
    @ax.Z8.a
    private boolean k = true;

    @ax.Z8.c("candelete")
    @ax.Z8.a
    private boolean l = true;

    static class FileEntryDeserializer implements ax.Y8.h<ax.Sa.u> {
        FileEntryDeserializer() {
        }

        @Override // ax.Y8.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ax.Sa.u a(ax.Y8.i iVar, Type type, ax.Y8.g gVar) throws ax.Y8.m {
            return iVar.g().t("isfolder").e() ? (ax.Sa.u) gVar.a(iVar, A.class) : (ax.Sa.u) gVar.a(iVar, z.class);
        }
    }

    static class TypeAdapterFactory implements ax.Y8.w {
        private static final ax.p076e9.a<ax.Sa.u> q = new a();
        private static final ax.p076e9.a<ax.Sa.v> X = new b();
        private static final ax.p076e9.a<ax.Sa.w> Y = new c();

        class a extends ax.p076e9.a<ax.Sa.u> {
            a() {
            }
        }

        class b extends ax.p076e9.a<ax.Sa.v> {
            b() {
            }
        }

        class c extends ax.p076e9.a<ax.Sa.w> {
            c() {
            }
        }

        TypeAdapterFactory() {
        }

        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            ax.p076e9.a<ax.Sa.u> aVar2 = q;
            if (aVar2.equals(aVar)) {
                return gson.m(aVar2);
            }
            if (X.equals(aVar)) {
                return gson.n(z.class);
            }
            if (Y.equals(aVar)) {
                return gson.n(A.class);
            }
            return null;
        }
    }

    RealRemoteEntry(InterfaceC1531a interfaceC1531a) {
        this.a = interfaceC1531a;
    }

    @Override // ax.Sa.u
    public boolean delete() throws IOException {
        try {
            return this.a.e(this).execute().booleanValue();
        } catch (ax.Sa.b e) {
            throw new IOException(e);
        }
    }

    public Date e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RealRemoteEntry realRemoteEntry = (RealRemoteEntry) obj;
        if (this.c == realRemoteEntry.c && this.g == realRemoteEntry.g && this.b.equals(realRemoteEntry.b) && this.d.equals(realRemoteEntry.d) && this.e.equals(realRemoteEntry.e) && this.i == realRemoteEntry.i && this.h == realRemoteEntry.h && this.j == realRemoteEntry.j && this.k == realRemoteEntry.k && this.l == realRemoteEntry.l) {
            return this.f.equals(realRemoteEntry.f);
        }
        return false;
    }

    @Override // ax.Sa.u
    public Date g() {
        return this.e;
    }

    @Override // ax.Sa.u
    public boolean h() {
        return this.i || this.j;
    }

    public int hashCode() {
        int iHashCode = this.b.hashCode() * 31;
        long j = this.c;
        return ((((((((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + (this.g ? 1 : 0);
    }

    @Override // ax.Sa.u
    public String i() {
        return this.d;
    }

    @Override // ax.Sa.u
    public ax.Sa.w j() {
        throw new IllegalStateException("This entry is not a folder");
    }

    @Override // ax.Sa.u
    public String k() {
        return this.b;
    }

    @Override // ax.Sa.u
    public boolean l() {
        return this.g;
    }

    @Override // ax.Sa.u
    public boolean m() {
        return this.i || this.l;
    }

    @Override // ax.Sa.u
    public boolean n() {
        return this.i || this.k;
    }

    @Override // ax.Sa.u
    public ax.Sa.v o() {
        throw new IllegalStateException("This entry is not a file");
    }

    @Override // ax.Sa.u
    public long p() {
        return this.c;
    }

    @Override // ax.Sa.u
    public boolean q() {
        return !this.g;
    }
}
