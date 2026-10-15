package com.google.gson.internal.bind;

import ax.Y8.h;
import ax.Y8.q;
import ax.Y8.w;
import ax.Z8.b;
import ax.p012a9.c;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements w {
    private final c q;

    public JsonAdapterAnnotationTypeAdapterFactory(c cVar) {
        this.q = cVar;
    }

    TypeAdapter<?> a(c cVar, Gson gson, ax.p076e9.a<?> aVar, b bVar) {
        TypeAdapter<?> treeTypeAdapter;
        Object objA = cVar.a(ax.p076e9.a.a(bVar.value())).a();
        if (objA instanceof TypeAdapter) {
            treeTypeAdapter = (TypeAdapter) objA;
        } else if (objA instanceof w) {
            treeTypeAdapter = ((w) objA).b(gson, aVar);
        } else {
            boolean z = objA instanceof q;
            if (!z && !(objA instanceof h)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            treeTypeAdapter = new TreeTypeAdapter(z ? (q) objA : null, objA instanceof h ? (h) objA : null, gson, aVar, null);
        }
        return (treeTypeAdapter == null || !bVar.nullSafe()) ? treeTypeAdapter : treeTypeAdapter.b();
    }

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
        b bVar = (b) aVar.c().getAnnotation(b.class);
        if (bVar == null) {
            return null;
        }
        return (TypeAdapter<T>) a(this.q, gson, aVar, bVar);
    }
}
