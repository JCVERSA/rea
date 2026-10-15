package com.google.gson.internal;

import ax.Y8.a;
import ax.Y8.b;
import ax.Y8.w;
import ax.Z8.d;
import ax.Z8.e;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class Excluder implements w, Cloneable {
    public static final Excluder m0 = new Excluder();
    private boolean Z;
    private List<a> k0;
    private List<a> l0;
    private double q = -1.0d;
    private int X = 136;
    private boolean Y = true;

    public Excluder() {
        List<a> list = Collections.EMPTY_LIST;
        this.k0 = list;
        this.l0 = list;
    }

    private boolean e(Class<?> cls) {
        if (this.q == -1.0d || r((d) cls.getAnnotation(d.class), (e) cls.getAnnotation(e.class))) {
            return (!this.Y && m(cls)) || l(cls);
        }
        return true;
    }

    private boolean g(Class<?> cls, boolean z) {
        Iterator<a> it = (z ? this.k0 : this.l0).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean l(Class<?> cls) {
        if (Enum.class.isAssignableFrom(cls) || n(cls)) {
            return false;
        }
        return cls.isAnonymousClass() || cls.isLocalClass();
    }

    private boolean m(Class<?> cls) {
        return cls.isMemberClass() && !n(cls);
    }

    private boolean n(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean o(d dVar) {
        return dVar == null || dVar.value() <= this.q;
    }

    private boolean q(e eVar) {
        return eVar == null || eVar.value() > this.q;
    }

    private boolean r(d dVar, e eVar) {
        return o(dVar) && q(eVar);
    }

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(final Gson gson, final ax.p076e9.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        boolean zE = e(clsC);
        final boolean z = zE || g(clsC, true);
        final boolean z2 = zE || g(clsC, false);
        if (z || z2) {
            return new TypeAdapter<T>() { // from class: com.google.gson.internal.Excluder.1
                private TypeAdapter<T> a;

                private TypeAdapter<T> f() {
                    TypeAdapter<T> typeAdapter = this.a;
                    if (typeAdapter != null) {
                        return typeAdapter;
                    }
                    TypeAdapter<T> typeAdapterO = gson.o(Excluder.this, aVar);
                    this.a = typeAdapterO;
                    return typeAdapterO;
                }

                @Override // com.google.gson.TypeAdapter
                public T c(JsonReader jsonReader) throws IOException {
                    if (!z2) {
                        return f().c(jsonReader);
                    }
                    jsonReader.skipValue();
                    return null;
                }

                @Override // com.google.gson.TypeAdapter
                public void e(JsonWriter jsonWriter, T t) throws IOException {
                    if (z) {
                        jsonWriter.nullValue();
                    } else {
                        f().e(jsonWriter, t);
                    }
                }
            };
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Excluder clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean d(Class<?> cls, boolean z) {
        return e(cls) || g(cls, z);
    }

    public boolean j(Field field, boolean z) {
        ax.Z8.a aVar;
        if ((this.X & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.q != -1.0d && !r((d) field.getAnnotation(d.class), (e) field.getAnnotation(e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.Z && ((aVar = (ax.Z8.a) field.getAnnotation(ax.Z8.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.Y && m(field.getType())) || l(field.getType())) {
            return true;
        }
        List<a> list = z ? this.k0 : this.l0;
        if (list.isEmpty()) {
            return false;
        }
        b bVar = new b(field);
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b(bVar)) {
                return true;
            }
        }
        return false;
    }

    public Excluder k() {
        Excluder excluderClone = clone();
        excluderClone.Z = true;
        return excluderClone;
    }
}
