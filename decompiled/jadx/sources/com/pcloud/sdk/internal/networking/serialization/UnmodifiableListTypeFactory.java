package com.pcloud.sdk.internal.networking.serialization;

import ax.Y8.w;
import ax.p076e9.a;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class UnmodifiableListTypeFactory implements w {
    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, final a<T> aVar) {
        final TypeAdapter<T> typeAdapterO = gson.o(this, aVar);
        return new TypeAdapter<T>() { // from class: com.pcloud.sdk.internal.networking.serialization.UnmodifiableListTypeFactory.1
            @Override // com.google.gson.TypeAdapter
            public T c(JsonReader jsonReader) throws IOException {
                T t = (T) typeAdapterO.c(jsonReader);
                return List.class.isAssignableFrom(aVar.c()) ? (T) DesugarCollections.unmodifiableList((List) t) : t;
            }

            @Override // com.google.gson.TypeAdapter
            public void e(JsonWriter jsonWriter, T t) throws IOException {
                typeAdapterO.e(jsonWriter, t);
            }
        };
    }
}
