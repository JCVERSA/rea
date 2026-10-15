package com.socialnmobile.dav.util;

import ax.Y8.d;
import ax.Y8.r;
import ax.Y8.w;
import ax.p012a9.c;
import ax.p012a9.h;
import ax.p012a9.j;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Excluder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MyReflectiveTypeAdapterFactory implements w {
    private final d X;
    private final Excluder Y;
    private final Class Z;
    private final c q;

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final h<T> a;
        private final Map<String, b> b;

        private Adapter(h<T> hVar, Map<String, b> map) {
            this.a = hVar;
            this.b = map;
        }

        /* synthetic */ Adapter(h hVar, Map map, a aVar) {
            this(hVar, map);
        }

        @Override // com.google.gson.TypeAdapter
        public T c(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T tA = this.a.a();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    b bVar = this.b.get(jsonReader.nextName());
                    if (bVar == null || !bVar.c) {
                        jsonReader.skipValue();
                    } else {
                        bVar.a(jsonReader, tA);
                    }
                }
                jsonReader.endObject();
                return tA;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new r(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void e(JsonWriter jsonWriter, T t) throws IOException {
            ax.Fe.a.a("This class should not be used for write");
        }
    }

    class a extends b {
        final TypeAdapter<?> d;
        final /* synthetic */ Gson e;
        final /* synthetic */ Field f;
        final /* synthetic */ ax.p076e9.a g;
        final /* synthetic */ boolean h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, boolean z, boolean z2, Gson gson, Field field, ax.p076e9.a aVar, boolean z3) {
            super(str, z, z2);
            this.e = gson;
            this.f = field;
            this.g = aVar;
            this.h = z3;
            this.d = MyReflectiveTypeAdapterFactory.this.g(gson, field, aVar);
        }

        @Override // com.socialnmobile.dav.util.MyReflectiveTypeAdapterFactory.b
        void a(JsonReader jsonReader, Object obj) throws IllegalAccessException, IOException {
            Object objC = this.d.c(jsonReader);
            if (!(objC == null && this.h) && this.f.get(obj) == null) {
                this.f.set(obj, objC);
            }
        }
    }

    static abstract class b {
        final String a;
        final boolean b;
        final boolean c;

        protected b(String str, boolean z, boolean z2) {
            this.a = str;
            this.b = z;
            this.c = z2;
        }

        abstract void a(JsonReader jsonReader, Object obj) throws IllegalAccessException, IOException;
    }

    public MyReflectiveTypeAdapterFactory(c cVar, d dVar, Excluder excluder, Class cls) {
        this.q = cVar;
        this.X = dVar;
        this.Y = excluder;
        this.Z = cls;
    }

    private b c(Gson gson, Field field, String str, ax.p076e9.a<?> aVar, boolean z, boolean z2) {
        return new a(str, z, z2, gson, field, aVar, j.a(aVar.c()));
    }

    static boolean e(Field field, boolean z, Excluder excluder) {
        return (excluder.d(field.getType(), z) || excluder.j(field, z)) ? false : true;
    }

    private Map<String, b> f(Gson gson, ax.p076e9.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!cls.isInterface()) {
            Type typeD = aVar.d();
            ax.p076e9.a<?> aVarB = aVar;
            Class<?> clsC = cls;
            while (clsC != Object.class) {
                for (Field field : clsC.getDeclaredFields()) {
                    boolean zD = d(field, true);
                    boolean zD2 = d(field, false);
                    if (zD || zD2) {
                        field.setAccessible(true);
                        b bVarC = c(gson, field, i(field), ax.p076e9.a.b(ax.p012a9.b.o(aVarB.d(), clsC, field.getGenericType())), zD, zD2);
                        b bVar = (b) linkedHashMap.put(bVarC.a, bVarC);
                        if (bVar != null) {
                            throw new IllegalArgumentException(typeD + " declares multiple JSON fields named " + bVar.a);
                        }
                    }
                }
                aVarB = ax.p076e9.a.b(ax.p012a9.b.o(aVarB.d(), clsC, clsC.getGenericSuperclass()));
                clsC = aVarB.c();
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TypeAdapter<?> g(Gson gson, Field field, ax.p076e9.a<?> aVar) {
        return gson.m(aVar);
    }

    static String h(d dVar, Field field) {
        ax.Z8.c cVar = (ax.Z8.c) field.getAnnotation(ax.Z8.c.class);
        return cVar == null ? dVar.g(field) : cVar.value();
    }

    private String i(Field field) {
        return h(this.X, field);
    }

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        a aVar2 = null;
        if (Object.class.isAssignableFrom(clsC) && this.Z.isAssignableFrom(clsC)) {
            return new Adapter(this.q.a(aVar), f(gson, aVar, clsC), aVar2);
        }
        return null;
    }

    public boolean d(Field field, boolean z) {
        return e(field, z, this.Y);
    }
}
