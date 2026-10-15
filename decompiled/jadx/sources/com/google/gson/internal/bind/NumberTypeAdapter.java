package com.google.gson.internal.bind;

import ax.Y8.r;
import ax.Y8.u;
import ax.Y8.v;
import ax.Y8.w;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class NumberTypeAdapter extends TypeAdapter<Number> {
    private static final w b = g(u.X);
    private final v a;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            a = iArr;
            try {
                iArr[JsonToken.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[JsonToken.NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private NumberTypeAdapter(v vVar) {
        this.a = vVar;
    }

    public static w f(v vVar) {
        return vVar == u.X ? b : g(vVar);
    }

    private static w g(v vVar) {
        return new w() { // from class: com.google.gson.internal.bind.NumberTypeAdapter.1
            @Override // ax.Y8.w
            public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
                if (aVar.c() == Number.class) {
                    return NumberTypeAdapter.this;
                }
                return null;
            }
        };
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public Number c(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek = jsonReader.peek();
        int i = a.a[jsonTokenPeek.ordinal()];
        if (i == 1) {
            jsonReader.nextNull();
            return null;
        }
        if (i == 2 || i == 3) {
            return this.a.g(jsonReader);
        }
        throw new r("Expecting number, got: " + jsonTokenPeek + "; at path " + jsonReader.getPath());
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, Number number) throws IOException {
        jsonWriter.value(number);
    }
}
