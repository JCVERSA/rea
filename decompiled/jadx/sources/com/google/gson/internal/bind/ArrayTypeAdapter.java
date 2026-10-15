package com.google.gson.internal.bind;

import ax.Y8.w;
import ax.p012a9.b;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {
    public static final w c = new w() { // from class: com.google.gson.internal.bind.ArrayTypeAdapter.1
        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            Type typeD = aVar.d();
            if (!(typeD instanceof GenericArrayType) && (!(typeD instanceof Class) || !((Class) typeD).isArray())) {
                return null;
            }
            Type typeG = b.g(typeD);
            return new ArrayTypeAdapter(gson, gson.m(ax.p076e9.a.b(typeG)), b.k(typeG));
        }
    };
    private final Class<E> a;
    private final TypeAdapter<E> b;

    public ArrayTypeAdapter(Gson gson, TypeAdapter<E> typeAdapter, Class<E> cls) {
        this.b = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, cls);
        this.a = cls;
    }

    @Override // com.google.gson.TypeAdapter
    public Object c(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(this.b.c(jsonReader));
        }
        jsonReader.endArray();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.a, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // com.google.gson.TypeAdapter
    public void e(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.b.e(jsonWriter, (E) Array.get(obj, i));
        }
        jsonWriter.endArray();
    }
}
