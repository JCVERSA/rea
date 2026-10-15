package com.google.gson.internal.bind;

import ax.Y8.i;
import ax.Y8.o;
import ax.Y8.r;
import ax.Y8.w;
import ax.p012a9.b;
import ax.p012a9.c;
import ax.p012a9.e;
import ax.p012a9.h;
import ax.p012a9.l;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class MapTypeAdapterFactory implements w {
    final boolean X;
    private final c q;

    private final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {
        private final TypeAdapter<K> a;
        private final TypeAdapter<V> b;
        private final h<? extends Map<K, V>> c;

        public Adapter(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, h<? extends Map<K, V>> hVar) {
            this.a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.b = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
            this.c = hVar;
        }

        private String f(i iVar) {
            if (!iVar.p()) {
                if (iVar.n()) {
                    return "null";
                }
                throw new AssertionError();
            }
            o oVarI = iVar.i();
            if (oVarI.x()) {
                return String.valueOf(oVarI.u());
            }
            if (oVarI.v()) {
                return Boolean.toString(oVarI.e());
            }
            if (oVarI.y()) {
                return oVarI.j();
            }
            throw new AssertionError();
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public Map<K, V> c(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Map<K, V> mapA = this.c.a();
            if (jsonTokenPeek != JsonToken.BEGIN_ARRAY) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    e.INSTANCE.promoteNameToValue(jsonReader);
                    K kC = this.a.c(jsonReader);
                    if (mapA.put(kC, this.b.c(jsonReader)) != null) {
                        throw new r("duplicate key: " + kC);
                    }
                }
                jsonReader.endObject();
                return mapA;
            }
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginArray();
                K kC2 = this.a.c(jsonReader);
                if (mapA.put(kC2, this.b.c(jsonReader)) != null) {
                    throw new r("duplicate key: " + kC2);
                }
                jsonReader.endArray();
            }
            jsonReader.endArray();
            return mapA;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void e(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            if (map == null) {
                jsonWriter.nullValue();
                return;
            }
            if (!MapTypeAdapterFactory.this.X) {
                jsonWriter.beginObject();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    jsonWriter.name(String.valueOf(entry.getKey()));
                    this.b.e(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                i iVarD = this.a.d(entry2.getKey());
                arrayList.add(iVarD);
                arrayList2.add(entry2.getValue());
                z |= iVarD.m() || iVarD.o();
            }
            if (!z) {
                jsonWriter.beginObject();
                int size = arrayList.size();
                while (i < size) {
                    jsonWriter.name(f((i) arrayList.get(i)));
                    this.b.e(jsonWriter, (V) arrayList2.get(i));
                    i++;
                }
                jsonWriter.endObject();
                return;
            }
            jsonWriter.beginArray();
            int size2 = arrayList.size();
            while (i < size2) {
                jsonWriter.beginArray();
                l.b((i) arrayList.get(i), jsonWriter);
                this.b.e(jsonWriter, (V) arrayList2.get(i));
                jsonWriter.endArray();
                i++;
            }
            jsonWriter.endArray();
        }
    }

    public MapTypeAdapterFactory(c cVar, boolean z) {
        this.q = cVar;
        this.X = z;
    }

    private TypeAdapter<?> a(Gson gson, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? TypeAdapters.f : gson.m(ax.p076e9.a.b(type));
    }

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
        Type typeD = aVar.d();
        Class<? super T> clsC = aVar.c();
        if (!Map.class.isAssignableFrom(clsC)) {
            return null;
        }
        Type[] typeArrJ = b.j(typeD, clsC);
        return new Adapter(gson, typeArrJ[0], a(gson, typeArrJ[0]), typeArrJ[1], gson.m(ax.p076e9.a.b(typeArrJ[1])), this.q.a(aVar));
    }
}
