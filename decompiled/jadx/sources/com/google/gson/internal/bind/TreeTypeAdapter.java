package com.google.gson.internal.bind;

import ax.Y8.g;
import ax.Y8.h;
import ax.Y8.i;
import ax.Y8.m;
import ax.Y8.p;
import ax.Y8.q;
import ax.Y8.w;
import ax.p012a9.l;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public final class TreeTypeAdapter<T> extends TypeAdapter<T> {
    private final q<T> a;
    private final h<T> b;
    final Gson c;
    private final ax.p076e9.a<T> d;
    private final w e;
    private final TreeTypeAdapter<T>.b f = new b();
    private volatile TypeAdapter<T> g;

    private static final class SingleTypeFactory implements w {
        private final boolean X;
        private final Class<?> Y;
        private final q<?> Z;
        private final h<?> k0;
        private final ax.p076e9.a<?> q;

        SingleTypeFactory(Object obj, ax.p076e9.a<?> aVar, boolean z, Class<?> cls) {
            q<?> qVar = obj instanceof q ? (q) obj : null;
            this.Z = qVar;
            h<?> hVar = obj instanceof h ? (h) obj : null;
            this.k0 = hVar;
            ax.p012a9.a.a((qVar == null && hVar == null) ? false : true);
            this.q = aVar;
            this.X = z;
            this.Y = cls;
        }

        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            boolean zIsAssignableFrom;
            ax.p076e9.a<?> aVar2 = this.q;
            if (aVar2 != null) {
                zIsAssignableFrom = aVar2.equals(aVar) || (this.X && this.q.d() == aVar.c());
            } else {
                zIsAssignableFrom = this.Y.isAssignableFrom(aVar.c());
            }
            if (zIsAssignableFrom) {
                return new TreeTypeAdapter(this.Z, this.k0, gson, aVar, this);
            }
            return null;
        }
    }

    private final class b implements p, g {
        private b() {
        }

        @Override // ax.Y8.g
        public <R> R a(i iVar, Type type) throws m {
            return (R) TreeTypeAdapter.this.c.g(iVar, type);
        }
    }

    public TreeTypeAdapter(q<T> qVar, h<T> hVar, Gson gson, ax.p076e9.a<T> aVar, w wVar) {
        this.a = qVar;
        this.b = hVar;
        this.c = gson;
        this.d = aVar;
        this.e = wVar;
    }

    private TypeAdapter<T> f() {
        TypeAdapter<T> typeAdapter = this.g;
        if (typeAdapter != null) {
            return typeAdapter;
        }
        TypeAdapter<T> typeAdapterO = this.c.o(this.e, this.d);
        this.g = typeAdapterO;
        return typeAdapterO;
    }

    public static w g(ax.p076e9.a<?> aVar, Object obj) {
        return new SingleTypeFactory(obj, aVar, aVar.d() == aVar.c(), null);
    }

    @Override // com.google.gson.TypeAdapter
    public T c(JsonReader jsonReader) throws IOException {
        if (this.b == null) {
            return f().c(jsonReader);
        }
        i iVarA = l.a(jsonReader);
        if (iVarA.n()) {
            return null;
        }
        return this.b.a(iVarA, this.d.d(), this.f);
    }

    @Override // com.google.gson.TypeAdapter
    public void e(JsonWriter jsonWriter, T t) throws IOException {
        q<T> qVar = this.a;
        if (qVar == null) {
            f().e(jsonWriter, t);
        } else if (t == null) {
            jsonWriter.nullValue();
        } else {
            l.b(qVar.a(t, this.d.d(), this.f), jsonWriter);
        }
    }
}
