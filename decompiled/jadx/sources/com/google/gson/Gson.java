package com.google.gson;

import ax.Y8.c;
import ax.Y8.d;
import ax.Y8.e;
import ax.Y8.i;
import ax.Y8.j;
import ax.Y8.k;
import ax.Y8.r;
import ax.Y8.s;
import ax.Y8.t;
import ax.Y8.u;
import ax.Y8.v;
import ax.Y8.w;
import ax.p012a9.f;
import ax.p012a9.l;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes3.dex */
public final class Gson {
    static final d A = c.q;
    static final v B = u.q;
    static final v C = u.X;
    private static final ax.p076e9.a<?> D = ax.p076e9.a.a(Object.class);
    static final String z = null;
    private final ThreadLocal<Map<ax.p076e9.a<?>, FutureTypeAdapter<?>>> a;
    private final Map<ax.p076e9.a<?>, TypeAdapter<?>> b;
    private final ax.p012a9.c c;
    private final JsonAdapterAnnotationTypeAdapterFactory d;
    final List<w> e;
    final Excluder f;
    final d g;
    final Map<Type, e<?>> h;
    final boolean i;
    final boolean j;
    final boolean k;
    final boolean l;
    final boolean m;
    final boolean n;
    final boolean o;
    final boolean p;
    final String q;
    final int r;
    final int s;
    final s t;
    final List<w> u;
    final List<w> v;
    final v w;
    final v x;
    final List<t> y;

    static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> a;

        FutureTypeAdapter() {
        }

        @Override // com.google.gson.TypeAdapter
        public T c(JsonReader jsonReader) throws IOException {
            TypeAdapter<T> typeAdapter = this.a;
            if (typeAdapter != null) {
                return typeAdapter.c(jsonReader);
            }
            throw new IllegalStateException();
        }

        @Override // com.google.gson.TypeAdapter
        public void e(JsonWriter jsonWriter, T t) throws IOException {
            TypeAdapter<T> typeAdapter = this.a;
            if (typeAdapter == null) {
                throw new IllegalStateException();
            }
            typeAdapter.e(jsonWriter, t);
        }

        public void f(TypeAdapter<T> typeAdapter) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = typeAdapter;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Gson() {
        Excluder excluder = Excluder.m0;
        d dVar = A;
        Map map = Collections.EMPTY_MAP;
        s sVar = s.q;
        String str = z;
        List list = Collections.EMPTY_LIST;
        this(excluder, dVar, map, false, false, false, true, false, false, false, true, sVar, str, 2, 2, list, list, list, B, C, list);
    }

    Gson(Excluder excluder, d dVar, Map<Type, e<?>> map, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, s sVar, String str, int i, int i2, List<w> list, List<w> list2, List<w> list3, v vVar, v vVar2, List<t> list4) {
        this.a = new ThreadLocal<>();
        this.b = new ConcurrentHashMap();
        this.f = excluder;
        this.g = dVar;
        this.h = map;
        ax.p012a9.c cVar = new ax.p012a9.c(map, z9, list4);
        this.c = cVar;
        this.i = z2;
        this.j = z3;
        this.k = z4;
        this.l = z5;
        this.m = z6;
        this.n = z7;
        this.o = z8;
        this.p = z9;
        this.t = sVar;
        this.q = str;
        this.r = i;
        this.s = i2;
        this.u = list;
        this.v = list2;
        this.w = vVar;
        this.x = vVar2;
        this.y = list4;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.W);
        arrayList.add(ObjectTypeAdapter.f(vVar));
        arrayList.add(excluder);
        arrayList.addAll(list3);
        arrayList.add(TypeAdapters.C);
        arrayList.add(TypeAdapters.m);
        arrayList.add(TypeAdapters.g);
        arrayList.add(TypeAdapters.i);
        arrayList.add(TypeAdapters.k);
        TypeAdapter<Number> typeAdapterP = p(sVar);
        arrayList.add(TypeAdapters.c(Long.TYPE, Long.class, typeAdapterP));
        arrayList.add(TypeAdapters.c(Double.TYPE, Double.class, e(z8)));
        arrayList.add(TypeAdapters.c(Float.TYPE, Float.class, f(z8)));
        arrayList.add(NumberTypeAdapter.f(vVar2));
        arrayList.add(TypeAdapters.o);
        arrayList.add(TypeAdapters.q);
        arrayList.add(TypeAdapters.b(AtomicLong.class, b(typeAdapterP)));
        arrayList.add(TypeAdapters.b(AtomicLongArray.class, c(typeAdapterP)));
        arrayList.add(TypeAdapters.s);
        arrayList.add(TypeAdapters.x);
        arrayList.add(TypeAdapters.E);
        arrayList.add(TypeAdapters.G);
        arrayList.add(TypeAdapters.b(BigDecimal.class, TypeAdapters.z));
        arrayList.add(TypeAdapters.b(BigInteger.class, TypeAdapters.A));
        arrayList.add(TypeAdapters.b(f.class, TypeAdapters.B));
        arrayList.add(TypeAdapters.I);
        arrayList.add(TypeAdapters.K);
        arrayList.add(TypeAdapters.O);
        arrayList.add(TypeAdapters.Q);
        arrayList.add(TypeAdapters.U);
        arrayList.add(TypeAdapters.M);
        arrayList.add(TypeAdapters.d);
        arrayList.add(DateTypeAdapter.b);
        arrayList.add(TypeAdapters.S);
        if (com.google.gson.internal.sql.a.a) {
            arrayList.add(com.google.gson.internal.sql.a.e);
            arrayList.add(com.google.gson.internal.sql.a.d);
            arrayList.add(com.google.gson.internal.sql.a.f);
        }
        arrayList.add(ArrayTypeAdapter.c);
        arrayList.add(TypeAdapters.b);
        arrayList.add(new CollectionTypeAdapterFactory(cVar));
        arrayList.add(new MapTypeAdapterFactory(cVar, z3));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(cVar);
        this.d = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(TypeAdapters.X);
        arrayList.add(new ReflectiveTypeAdapterFactory(cVar, dVar, excluder, jsonAdapterAnnotationTypeAdapterFactory, list4));
        this.e = DesugarCollections.unmodifiableList(arrayList);
    }

    private static void a(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() == JsonToken.END_DOCUMENT) {
                } else {
                    throw new r("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new r(e);
            } catch (IOException e2) {
                throw new j(e2);
            }
        }
    }

    private static TypeAdapter<AtomicLong> b(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLong>() { // from class: com.google.gson.Gson.4
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public AtomicLong c(JsonReader jsonReader) throws IOException {
                return new AtomicLong(((Number) typeAdapter.c(jsonReader)).longValue());
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
            public void e(JsonWriter jsonWriter, AtomicLong atomicLong) throws IOException {
                typeAdapter.e(jsonWriter, Long.valueOf(atomicLong.get()));
            }
        }.b();
    }

    private static TypeAdapter<AtomicLongArray> c(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLongArray>() { // from class: com.google.gson.Gson.5
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public AtomicLongArray c(JsonReader jsonReader) throws IOException {
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(Long.valueOf(((Number) typeAdapter.c(jsonReader)).longValue()));
                }
                jsonReader.endArray();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
            public void e(JsonWriter jsonWriter, AtomicLongArray atomicLongArray) throws IOException {
                jsonWriter.beginArray();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    typeAdapter.e(jsonWriter, Long.valueOf(atomicLongArray.get(i)));
                }
                jsonWriter.endArray();
            }
        }.b();
    }

    static void d(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> e(boolean z2) {
        return z2 ? TypeAdapters.v : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.1
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public Double c(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Double.valueOf(jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
            public void e(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    Gson.d(number.doubleValue());
                    jsonWriter.value(number);
                }
            }
        };
    }

    private TypeAdapter<Number> f(boolean z2) {
        return z2 ? TypeAdapters.u : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.2
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public Float c(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
            public void e(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    Gson.d(number.floatValue());
                    jsonWriter.value(number);
                }
            }
        };
    }

    private static TypeAdapter<Number> p(s sVar) {
        return sVar == s.q ? TypeAdapters.t : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.3
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public Number c(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Long.valueOf(jsonReader.nextLong());
                }
                jsonReader.nextNull();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
            public void e(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(number.toString());
                }
            }
        };
    }

    public i A(Object obj, Type type) {
        ax.p028b9.a aVar = new ax.p028b9.a();
        x(obj, type, aVar);
        return aVar.a();
    }

    public <T> T g(i iVar, Type type) throws r {
        if (iVar == null) {
            return null;
        }
        return (T) h(new com.google.gson.internal.bind.a(iVar), type);
    }

    public <T> T h(JsonReader jsonReader, Type type) throws j, r {
        boolean zIsLenient = jsonReader.isLenient();
        boolean z2 = true;
        jsonReader.setLenient(true);
        try {
            try {
                try {
                    jsonReader.peek();
                    z2 = false;
                    T tC = m(ax.p076e9.a.b(type)).c(jsonReader);
                    jsonReader.setLenient(zIsLenient);
                    return tC;
                } catch (EOFException e) {
                    if (!z2) {
                        throw new r(e);
                    }
                    jsonReader.setLenient(zIsLenient);
                    return null;
                } catch (IllegalStateException e2) {
                    throw new r(e2);
                }
            } catch (IOException e3) {
                throw new r(e3);
            } catch (AssertionError e4) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.9.1): " + e4.getMessage());
                assertionError.initCause(e4);
                throw assertionError;
            }
        } catch (Throwable th) {
            jsonReader.setLenient(zIsLenient);
            throw th;
        }
    }

    public <T> T i(Reader reader, Class<T> cls) throws j, r {
        JsonReader jsonReaderQ = q(reader);
        Object objH = h(jsonReaderQ, cls);
        a(objH, jsonReaderQ);
        return (T) ax.p012a9.j.b(cls).cast(objH);
    }

    public <T> T j(Reader reader, Type type) throws j, r {
        JsonReader jsonReaderQ = q(reader);
        T t = (T) h(jsonReaderQ, type);
        a(t, jsonReaderQ);
        return t;
    }

    public <T> T k(String str, Class<T> cls) throws r {
        return (T) ax.p012a9.j.b(cls).cast(l(str, cls));
    }

    public <T> T l(String str, Type type) throws r {
        if (str == null) {
            return null;
        }
        return (T) j(new StringReader(str), type);
    }

    public <T> TypeAdapter<T> m(ax.p076e9.a<T> aVar) {
        boolean z2;
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.b.get(aVar == null ? D : aVar);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map<ax.p076e9.a<?>, FutureTypeAdapter<?>> map = this.a.get();
        if (map == null) {
            map = new HashMap<>();
            this.a.set(map);
            z2 = true;
        } else {
            z2 = false;
        }
        FutureTypeAdapter<?> futureTypeAdapter = map.get(aVar);
        if (futureTypeAdapter != null) {
            return futureTypeAdapter;
        }
        try {
            FutureTypeAdapter<?> futureTypeAdapter2 = new FutureTypeAdapter<>();
            map.put(aVar, futureTypeAdapter2);
            Iterator<w> it = this.e.iterator();
            while (it.hasNext()) {
                TypeAdapter<T> typeAdapterB = it.next().b(this, aVar);
                if (typeAdapterB != null) {
                    futureTypeAdapter2.f(typeAdapterB);
                    this.b.put(aVar, typeAdapterB);
                    map.remove(aVar);
                    if (z2) {
                        this.a.remove();
                    }
                    return typeAdapterB;
                }
            }
            throw new IllegalArgumentException("GSON (2.9.1) cannot handle " + aVar);
        } catch (Throwable th) {
            map.remove(aVar);
            if (z2) {
                this.a.remove();
            }
            throw th;
        }
    }

    public <T> TypeAdapter<T> n(Class<T> cls) {
        return m(ax.p076e9.a.a(cls));
    }

    public <T> TypeAdapter<T> o(w wVar, ax.p076e9.a<T> aVar) {
        if (!this.e.contains(wVar)) {
            wVar = this.d;
        }
        boolean z2 = false;
        for (w wVar2 : this.e) {
            if (z2) {
                TypeAdapter<T> typeAdapterB = wVar2.b(this, aVar);
                if (typeAdapterB != null) {
                    return typeAdapterB;
                }
            } else if (wVar2 == wVar) {
                z2 = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public JsonReader q(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(this.n);
        return jsonReader;
    }

    public JsonWriter r(Writer writer) throws IOException {
        if (this.k) {
            writer.write(")]}'\n");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.m) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setHtmlSafe(this.l);
        jsonWriter.setLenient(this.n);
        jsonWriter.setSerializeNulls(this.i);
        return jsonWriter;
    }

    public String s(i iVar) {
        StringWriter stringWriter = new StringWriter();
        w(iVar, stringWriter);
        return stringWriter.toString();
    }

    public String t(Object obj) {
        return obj == null ? s(k.q) : u(obj, obj.getClass());
    }

    public String toString() {
        return "{serializeNulls:" + this.i + ",factories:" + this.e + ",instanceCreators:" + this.c + "}";
    }

    public String u(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        y(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void v(i iVar, JsonWriter jsonWriter) throws j {
        boolean zIsLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean zIsHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.l);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.i);
        try {
            try {
                l.b(iVar, jsonWriter);
                jsonWriter.setLenient(zIsLenient);
                jsonWriter.setHtmlSafe(zIsHtmlSafe);
                jsonWriter.setSerializeNulls(serializeNulls);
            } catch (IOException e) {
                throw new j(e);
            } catch (AssertionError e2) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.9.1): " + e2.getMessage());
                assertionError.initCause(e2);
                throw assertionError;
            }
        } catch (Throwable th) {
            jsonWriter.setLenient(zIsLenient);
            jsonWriter.setHtmlSafe(zIsHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
            throw th;
        }
    }

    public void w(i iVar, Appendable appendable) throws j {
        try {
            v(iVar, r(l.c(appendable)));
        } catch (IOException e) {
            throw new j(e);
        }
    }

    public void x(Object obj, Type type, JsonWriter jsonWriter) throws j {
        TypeAdapter typeAdapterM = m(ax.p076e9.a.b(type));
        boolean zIsLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean zIsHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.l);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.i);
        try {
            try {
                typeAdapterM.e(jsonWriter, obj);
                jsonWriter.setLenient(zIsLenient);
                jsonWriter.setHtmlSafe(zIsHtmlSafe);
                jsonWriter.setSerializeNulls(serializeNulls);
            } catch (IOException e) {
                throw new j(e);
            } catch (AssertionError e2) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.9.1): " + e2.getMessage());
                assertionError.initCause(e2);
                throw assertionError;
            }
        } catch (Throwable th) {
            jsonWriter.setLenient(zIsLenient);
            jsonWriter.setHtmlSafe(zIsHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
            throw th;
        }
    }

    public void y(Object obj, Type type, Appendable appendable) throws j {
        try {
            x(obj, type, r(l.c(appendable)));
        } catch (IOException e) {
            throw new j(e);
        }
    }

    public i z(Object obj) {
        return obj == null ? k.q : A(obj, obj.getClass());
    }
}
