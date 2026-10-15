package com.google.gson;

import ax.Y8.i;
import ax.Y8.j;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TypeAdapter<T> {
    public final T a(i iVar) {
        try {
            return c(new com.google.gson.internal.bind.a(iVar));
        } catch (IOException e) {
            throw new j(e);
        }
    }

    public final TypeAdapter<T> b() {
        return new TypeAdapter<T>() { // from class: com.google.gson.TypeAdapter.1
            @Override // com.google.gson.TypeAdapter
            public T c(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return (T) TypeAdapter.this.c(jsonReader);
                }
                jsonReader.nextNull();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            public void e(JsonWriter jsonWriter, T t) throws IOException {
                if (t == null) {
                    jsonWriter.nullValue();
                } else {
                    TypeAdapter.this.e(jsonWriter, t);
                }
            }
        };
    }

    public abstract T c(JsonReader jsonReader) throws IOException;

    public final i d(T t) {
        try {
            ax.p028b9.a aVar = new ax.p028b9.a();
            e(aVar, t);
            return aVar.a();
        } catch (IOException e) {
            throw new j(e);
        }
    }

    public abstract void e(JsonWriter jsonWriter, T t) throws IOException;
}
