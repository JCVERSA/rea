package com.google.gson.internal.bind;

import ax.Y8.w;
import ax.p012a9.b;
import ax.p012a9.c;
import ax.p012a9.h;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class CollectionTypeAdapterFactory implements w {
    private final c q;

    private static final class Adapter<E> extends TypeAdapter<Collection<E>> {
        private final TypeAdapter<E> a;
        private final h<? extends Collection<E>> b;

        public Adapter(Gson gson, Type type, TypeAdapter<E> typeAdapter, h<? extends Collection<E>> hVar) {
            this.a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.b = hVar;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Collection<E> c(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Collection<E> collectionA = this.b.a();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                collectionA.add(this.a.c(jsonReader));
            }
            jsonReader.endArray();
            return collectionA;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void e(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
            if (collection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.a.e(jsonWriter, it.next());
            }
            jsonWriter.endArray();
        }
    }

    public CollectionTypeAdapterFactory(c cVar) {
        this.q = cVar;
    }

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
        Type typeD = aVar.d();
        Class<? super T> clsC = aVar.c();
        if (!Collection.class.isAssignableFrom(clsC)) {
            return null;
        }
        Type typeH = b.h(typeD, clsC);
        return new Adapter(gson, typeH, gson.m(ax.p076e9.a.b(typeH)), this.q.a(aVar));
    }
}
