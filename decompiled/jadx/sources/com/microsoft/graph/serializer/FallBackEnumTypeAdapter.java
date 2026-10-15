package com.microsoft.graph.serializer;

import ax.Y8.w;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class FallBackEnumTypeAdapter implements w {
    private final ax.p381xa.b q = new ax.p381xa.a();

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        if (!clsC.isEnum()) {
            return null;
        }
        final HashMap map = new HashMap();
        for (Object obj : clsC.getEnumConstants()) {
            map.put(obj.toString(), obj);
        }
        return new TypeAdapter<T>() { // from class: com.microsoft.graph.serializer.FallBackEnumTypeAdapter.1
            @Override // com.google.gson.TypeAdapter
            public T c(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String strNextString = jsonReader.nextString();
                T t = (T) map.get(strNextString);
                if (t != null) {
                    return t;
                }
                FallBackEnumTypeAdapter.this.q.a(String.format("The following value %s could not be recognized as a member of the enum", strNextString));
                return (T) map.get("unexpectedValue");
            }

            @Override // com.google.gson.TypeAdapter
            public void e(JsonWriter jsonWriter, T t) throws IOException {
                if (t == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(t.toString());
                }
            }
        };
    }
}
