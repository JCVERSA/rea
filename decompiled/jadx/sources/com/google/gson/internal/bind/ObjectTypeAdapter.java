package com.google.gson.internal.bind;

import ax.Y8.u;
import ax.Y8.v;
import ax.Y8.w;
import ax.p012a9.g;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    private static final w c = g(u.q);
    private final Gson a;
    private final v b;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private ObjectTypeAdapter(Gson gson, v vVar) {
        this.a = gson;
        this.b = vVar;
    }

    public static w f(v vVar) {
        return vVar == u.q ? c : g(vVar);
    }

    private static w g(final v vVar) {
        return new w() { // from class: com.google.gson.internal.bind.ObjectTypeAdapter.1
            @Override // ax.Y8.w
            public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
                if (aVar.c() == Object.class) {
                    return new ObjectTypeAdapter(gson, vVar);
                }
                return null;
            }
        };
    }

    private Object h(JsonReader jsonReader, JsonToken jsonToken) throws IOException {
        int i = a.a[jsonToken.ordinal()];
        if (i == 3) {
            return jsonReader.nextString();
        }
        if (i == 4) {
            return this.b.g(jsonReader);
        }
        if (i == 5) {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }
        if (i == 6) {
            jsonReader.nextNull();
            return null;
        }
        throw new IllegalStateException("Unexpected token: " + jsonToken);
    }

    private Object i(JsonReader jsonReader, JsonToken jsonToken) throws IOException {
        int i = a.a[jsonToken.ordinal()];
        if (i == 1) {
            jsonReader.beginArray();
            return new ArrayList();
        }
        if (i != 2) {
            return null;
        }
        jsonReader.beginObject();
        return new g();
    }

    @Override // com.google.gson.TypeAdapter
    public Object c(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek = jsonReader.peek();
        Object objI = i(jsonReader, jsonTokenPeek);
        if (objI == null) {
            return h(jsonReader, jsonTokenPeek);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (jsonReader.hasNext()) {
                String strNextName = objI instanceof Map ? jsonReader.nextName() : null;
                JsonToken jsonTokenPeek2 = jsonReader.peek();
                Object objI2 = i(jsonReader, jsonTokenPeek2);
                boolean z = objI2 != null;
                if (objI2 == null) {
                    objI2 = h(jsonReader, jsonTokenPeek2);
                }
                if (objI instanceof List) {
                    ((List) objI).add(objI2);
                } else {
                    ((Map) objI).put(strNextName, objI2);
                }
                if (z) {
                    arrayDeque.addLast(objI);
                    objI = objI2;
                }
            } else {
                if (objI instanceof List) {
                    jsonReader.endArray();
                } else {
                    jsonReader.endObject();
                }
                if (arrayDeque.isEmpty()) {
                    return objI;
                }
                objI = arrayDeque.removeLast();
            }
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void e(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter typeAdapterN = this.a.n(obj.getClass());
        if (!(typeAdapterN instanceof ObjectTypeAdapter)) {
            typeAdapterN.e(jsonWriter, obj);
        } else {
            jsonWriter.beginObject();
            jsonWriter.endObject();
        }
    }
}
