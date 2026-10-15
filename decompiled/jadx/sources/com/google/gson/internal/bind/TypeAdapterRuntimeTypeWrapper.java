package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes3.dex */
final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson a;
    private final TypeAdapter<T> b;
    private final Type c;

    TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.a = gson;
        this.b = typeAdapter;
        this.c = type;
    }

    private Type f(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        }
        return type;
    }

    @Override // com.google.gson.TypeAdapter
    public T c(JsonReader jsonReader) throws IOException {
        return this.b.c(jsonReader);
    }

    @Override // com.google.gson.TypeAdapter
    public void e(JsonWriter jsonWriter, T t) throws IOException {
        TypeAdapter<T> typeAdapterM = this.b;
        Type typeF = f(this.c, t);
        if (typeF != this.c) {
            typeAdapterM = this.a.m(ax.p076e9.a.b(typeF));
            if (typeAdapterM instanceof ReflectiveTypeAdapterFactory.Adapter) {
                TypeAdapter<T> typeAdapter = this.b;
                if (!(typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                    typeAdapterM = typeAdapter;
                }
            }
        }
        typeAdapterM.e(jsonWriter, t);
    }
}
