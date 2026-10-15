package com.google.gson.internal.bind;

import ax.Y8.d;
import ax.Y8.j;
import ax.Y8.r;
import ax.Y8.t;
import ax.Y8.w;
import ax.p012a9.c;
import ax.p012a9.h;
import ax.p012a9.k;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Excluder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ReflectiveTypeAdapterFactory implements w {
    private final d X;
    private final Excluder Y;
    private final JsonAdapterAnnotationTypeAdapterFactory Z;
    private final List<t> k0;
    private final c q;

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final h<T> a;
        private final Map<String, b> b;

        Adapter(h<T> hVar, Map<String, b> map) {
            this.a = hVar;
            this.b = map;
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
                throw ax.p060d9.a.b(e);
            } catch (IllegalStateException e2) {
                throw new r(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void e(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                Iterator<b> it = this.b.values().iterator();
                while (it.hasNext()) {
                    it.next().b(jsonWriter, t);
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e) {
                throw ax.p060d9.a.b(e);
            }
        }
    }

    class a extends b {
        final /* synthetic */ boolean d;
        final /* synthetic */ Field e;
        final /* synthetic */ boolean f;
        final /* synthetic */ TypeAdapter g;
        final /* synthetic */ Gson h;
        final /* synthetic */ ax.p076e9.a i;
        final /* synthetic */ boolean j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, boolean z, boolean z2, boolean z3, Field field, boolean z4, TypeAdapter typeAdapter, Gson gson, ax.p076e9.a aVar, boolean z5) {
            super(str, z, z2);
            this.d = z3;
            this.e = field;
            this.f = z4;
            this.g = typeAdapter;
            this.h = gson;
            this.i = aVar;
            this.j = z5;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.b
        void a(JsonReader jsonReader, Object obj) throws IllegalAccessException, IOException {
            Object objC = this.g.c(jsonReader);
            if (objC == null && this.j) {
                return;
            }
            if (this.d) {
                ReflectiveTypeAdapterFactory.c(obj, this.e);
            }
            this.e.set(obj, objC);
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.b
        void b(JsonWriter jsonWriter, Object obj) throws IllegalAccessException, IOException {
            if (this.b) {
                if (this.d) {
                    ReflectiveTypeAdapterFactory.c(obj, this.e);
                }
                Object obj2 = this.e.get(obj);
                if (obj2 == obj) {
                    return;
                }
                jsonWriter.name(this.a);
                (this.f ? this.g : new TypeAdapterRuntimeTypeWrapper(this.h, this.g, this.i.d())).e(jsonWriter, obj2);
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

        abstract void b(JsonWriter jsonWriter, Object obj) throws IllegalAccessException, IOException;
    }

    public ReflectiveTypeAdapterFactory(c cVar, d dVar, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, List<t> list) {
        this.q = cVar;
        this.X = dVar;
        this.Y = excluder;
        this.Z = jsonAdapterAnnotationTypeAdapterFactory;
        this.k0 = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Object obj, Field field) {
        if (Modifier.isStatic(field.getModifiers())) {
            obj = null;
        }
        if (k.a(field, obj)) {
            return;
        }
        throw new j("Field '" + field.getDeclaringClass().getName() + "#" + field.getName() + "' is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type or adjust the access filter.");
    }

    private b d(Gson gson, Field field, String str, ax.p076e9.a<?> aVar, boolean z, boolean z2, boolean z3) {
        boolean zA = ax.p012a9.j.a(aVar.c());
        ax.Z8.b bVar = (ax.Z8.b) field.getAnnotation(ax.Z8.b.class);
        TypeAdapter<?> typeAdapterA = bVar != null ? this.Z.a(this.q, gson, aVar, bVar) : null;
        boolean z4 = typeAdapterA != null;
        if (typeAdapterA == null) {
            typeAdapterA = gson.m(aVar);
        }
        return new a(str, z, z2, z3, field, z4, typeAdapterA, gson, aVar, zA);
    }

    private Map<String, b> e(Gson gson, ax.p076e9.a<?> aVar, Class<?> cls, boolean z) {
        int i;
        int i2;
        Class<?> cls2 = cls;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!cls2.isInterface()) {
            Type typeD = aVar.d();
            ax.p076e9.a<?> aVarB = aVar;
            boolean z2 = z;
            Class<?> clsC = cls2;
            while (clsC != Object.class) {
                Field[] declaredFields = clsC.getDeclaredFields();
                boolean z3 = true;
                boolean z4 = false;
                if (clsC != cls2 && declaredFields.length > 0) {
                    t.a aVarB2 = k.b(this.k0, clsC);
                    if (aVarB2 == t.a.BLOCK_ALL) {
                        throw new j("ReflectionAccessFilter does not permit using reflection for " + clsC + " (supertype of " + cls2 + "). Register a TypeAdapter for this type or adjust the access filter.");
                    }
                    z2 = aVarB2 == t.a.BLOCK_INACCESSIBLE;
                }
                boolean z5 = z2;
                int length = declaredFields.length;
                int i3 = 0;
                while (i3 < length) {
                    int i4 = i3;
                    Field field = declaredFields[i4];
                    boolean zG = this.g(field, z3);
                    boolean zG2 = this.g(field, z4);
                    if (zG || zG2) {
                        if (!z5) {
                            ax.p060d9.a.c(field);
                        }
                        Type typeO = ax.p012a9.b.o(aVarB.d(), clsC, field.getGenericType());
                        List<String> listF = this.f(field);
                        int size = listF.size();
                        b bVar = null;
                        int i5 = 0;
                        while (i5 < size) {
                            String str = listF.get(i5);
                            if (i5 != 0) {
                                zG = false;
                            }
                            List<String> list = listF;
                            boolean z6 = zG;
                            int i6 = i4;
                            int i7 = length;
                            b bVar2 = (b) linkedHashMap.put(str, this.d(gson, field, str, ax.p076e9.a.b(typeO), z6, zG2, z5));
                            if (bVar == null) {
                                bVar = bVar2;
                            }
                            i5++;
                            this = this;
                            zG = z6;
                            length = i7;
                            i4 = i6;
                            listF = list;
                        }
                        i = length;
                        i2 = i4;
                        if (bVar != null) {
                            throw new IllegalArgumentException(typeD + " declares multiple JSON fields named " + bVar.a);
                        }
                    } else {
                        i = length;
                        i2 = i4;
                    }
                    i3 = i2 + 1;
                    this = this;
                    aVarB = aVarB;
                    length = i;
                    z3 = true;
                    z4 = false;
                }
                aVarB = ax.p076e9.a.b(ax.p012a9.b.o(aVarB.d(), clsC, clsC.getGenericSuperclass()));
                clsC = aVarB.c();
                cls2 = cls;
                z2 = z5;
            }
        }
        return linkedHashMap;
    }

    private List<String> f(Field field) {
        ax.Z8.c cVar = (ax.Z8.c) field.getAnnotation(ax.Z8.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.X.g(field));
        }
        String strValue = cVar.value();
        String[] strArrAlternate = cVar.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        for (String str : strArrAlternate) {
            arrayList.add(str);
        }
        return arrayList;
    }

    private boolean g(Field field, boolean z) {
        return (this.Y.d(field.getType(), z) || this.Y.j(field, z)) ? false : true;
    }

    @Override // ax.Y8.w
    public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        if (!Object.class.isAssignableFrom(clsC)) {
            return null;
        }
        t.a aVarB = k.b(this.k0, clsC);
        if (aVarB != t.a.BLOCK_ALL) {
            return new Adapter(this.q.a(aVar), e(gson, aVar, clsC, aVarB == t.a.BLOCK_INACCESSIBLE));
        }
        throw new j("ReflectionAccessFilter does not permit using reflection for " + clsC + ". Register a TypeAdapter for this type or adjust the access filter.");
    }
}
