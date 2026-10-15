package com.alphainventor.filemanager.license.components;

import ax.Y8.i;
import ax.Y8.m;
import ax.Y8.o;
import ax.Y8.w;
import ax.p012a9.l;
import ax.p076e9.a;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class RuntimeTypeAdapterFactory<T> implements w {
    private final String X;
    private final Map<String, Class<?>> Y = new LinkedHashMap();
    private final Map<Class<?>, String> Z = new LinkedHashMap();
    private final Class<?> q;

    private RuntimeTypeAdapterFactory(Class<?> cls, String str) {
        if (str == null || cls == null) {
            throw null;
        }
        this.q = cls;
        this.X = str;
    }

    public static <T> RuntimeTypeAdapterFactory<T> e(Class<T> cls, String str) {
        return new RuntimeTypeAdapterFactory<>(cls, str);
    }

    @Override // ax.Y8.w
    public <R> TypeAdapter<R> b(Gson gson, a<R> aVar) {
        if (aVar.c() != this.q) {
            return null;
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, Class<?>> entry : this.Y.entrySet()) {
            TypeAdapter<T> typeAdapterO = gson.o(this, a.a(entry.getValue()));
            linkedHashMap.put(entry.getKey(), typeAdapterO);
            linkedHashMap2.put(entry.getValue(), typeAdapterO);
        }
        return new TypeAdapter<R>() { // from class: com.alphainventor.filemanager.license.components.RuntimeTypeAdapterFactory.1
            @Override // com.google.gson.TypeAdapter
            public R c(JsonReader jsonReader) throws IOException {
                i iVarA = l.a(jsonReader);
                i iVarX = iVarA.g().x(RuntimeTypeAdapterFactory.this.X);
                if (iVarX == null) {
                    throw new m("cannot deserialize " + RuntimeTypeAdapterFactory.this.q + " because it does not define a field named " + RuntimeTypeAdapterFactory.this.X);
                }
                String strJ = iVarX.j();
                TypeAdapter typeAdapter = (TypeAdapter) linkedHashMap.get(strJ);
                if (typeAdapter != null) {
                    return (R) typeAdapter.a(iVarA);
                }
                throw new m("cannot deserialize " + RuntimeTypeAdapterFactory.this.q + " subtype named " + strJ + "; did you forget to register a subtype?");
            }

            @Override // com.google.gson.TypeAdapter
            public void e(JsonWriter jsonWriter, R r) throws IOException {
                Class<?> cls = r.getClass();
                String str = (String) RuntimeTypeAdapterFactory.this.Z.get(cls);
                TypeAdapter typeAdapter = (TypeAdapter) linkedHashMap2.get(cls);
                if (typeAdapter == null) {
                    throw new m("cannot serialize " + cls.getName() + "; did you forget to register a subtype?");
                }
                ax.Y8.l lVarG = typeAdapter.d(r).g();
                if (lVarG.w(RuntimeTypeAdapterFactory.this.X)) {
                    throw new m("cannot serialize " + cls.getName() + " because it already defines a field named " + RuntimeTypeAdapterFactory.this.X);
                }
                ax.Y8.l lVar = new ax.Y8.l();
                lVar.q(RuntimeTypeAdapterFactory.this.X, new o(str));
                for (Map.Entry<String, i> entry2 : lVarG.s()) {
                    lVar.q(entry2.getKey(), entry2.getValue());
                }
                l.b(lVar, jsonWriter);
            }
        }.b();
    }

    public RuntimeTypeAdapterFactory<T> f(Class<? extends T> cls, String str) {
        if (cls == null || str == null) {
            throw null;
        }
        if (this.Z.containsKey(cls) || this.Y.containsKey(str)) {
            throw new IllegalArgumentException("types and labels must be unique");
        }
        this.Y.put(str, cls);
        this.Z.put(cls, str);
        return this;
    }
}
