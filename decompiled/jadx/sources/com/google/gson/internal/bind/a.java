package com.google.gson.internal.bind;

import ax.Y8.f;
import ax.Y8.i;
import ax.Y8.k;
import ax.Y8.l;
import ax.Y8.o;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends JsonReader {
    private static final Reader k0 = new C0587a();
    private static final Object l0 = new Object();
    private int X;
    private String[] Y;
    private int[] Z;
    private Object[] q;

    /* JADX INFO: renamed from: com.google.gson.internal.bind.a$a, reason: collision with other inner class name */
    class C0587a extends Reader implements AutoCloseable {
        C0587a() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    public a(i iVar) {
        super(k0);
        this.q = new Object[32];
        this.X = 0;
        this.Y = new String[32];
        this.Z = new int[32];
        k(iVar);
    }

    private void a(JsonToken jsonToken) throws IOException {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek() + locationString());
    }

    private Object f() {
        return this.q[this.X - 1];
    }

    private String getPath(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (true) {
            int i2 = this.X;
            if (i >= i2) {
                return sb.toString();
            }
            Object[] objArr = this.q;
            Object obj = objArr[i];
            if (obj instanceof f) {
                i++;
                if (i < i2 && (objArr[i] instanceof Iterator)) {
                    int i3 = this.Z[i];
                    if (z && i3 > 0 && (i == i2 - 1 || i == i2 - 2)) {
                        i3--;
                    }
                    sb.append('[');
                    sb.append(i3);
                    sb.append(']');
                }
            } else if ((obj instanceof l) && (i = i + 1) < i2 && (objArr[i] instanceof Iterator)) {
                sb.append('.');
                String str = this.Y[i];
                if (str != null) {
                    sb.append(str);
                }
            }
            i++;
        }
    }

    private Object h() {
        Object[] objArr = this.q;
        int i = this.X - 1;
        this.X = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    private void k(Object obj) {
        int i = this.X;
        Object[] objArr = this.q;
        if (i == objArr.length) {
            int i2 = i * 2;
            this.q = Arrays.copyOf(objArr, i2);
            this.Z = Arrays.copyOf(this.Z, i2);
            this.Y = (String[]) Arrays.copyOf(this.Y, i2);
        }
        Object[] objArr2 = this.q;
        int i3 = this.X;
        this.X = i3 + 1;
        objArr2[i3] = obj;
    }

    private String locationString() {
        return " at path " + getPath();
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() throws IOException {
        a(JsonToken.BEGIN_ARRAY);
        k(((f) f()).iterator());
        this.Z[this.X - 1] = 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() throws IOException {
        a(JsonToken.BEGIN_OBJECT);
        k(((l) f()).s().iterator());
    }

    @Override // com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.q = new Object[]{l0};
        this.X = 1;
    }

    i d() throws IOException {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NAME && jsonTokenPeek != JsonToken.END_ARRAY && jsonTokenPeek != JsonToken.END_OBJECT && jsonTokenPeek != JsonToken.END_DOCUMENT) {
            i iVar = (i) f();
            skipValue();
            return iVar;
        }
        throw new IllegalStateException("Unexpected " + jsonTokenPeek + " when reading a JsonElement.");
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() throws IOException {
        a(JsonToken.END_ARRAY);
        h();
        h();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() throws IOException {
        a(JsonToken.END_OBJECT);
        h();
        h();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPath() {
        return getPath(false);
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPreviousPath() {
        return getPath(true);
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() throws IOException {
        JsonToken jsonTokenPeek = peek();
        return (jsonTokenPeek == JsonToken.END_OBJECT || jsonTokenPeek == JsonToken.END_ARRAY || jsonTokenPeek == JsonToken.END_DOCUMENT) ? false : true;
    }

    public void i() throws IOException {
        a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) f()).next();
        k(entry.getValue());
        k(new o((String) entry.getKey()));
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() throws IOException {
        a(JsonToken.BOOLEAN);
        boolean zE = ((o) h()).e();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zE;
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() throws IOException {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        double dQ = ((o) f()).q();
        if (!isLenient() && (Double.isNaN(dQ) || Double.isInfinite(dQ))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dQ);
        }
        h();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dQ;
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() throws IOException {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        int iS = ((o) f()).s();
        h();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iS;
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() throws IOException {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        long jT = ((o) f()).t();
        h();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jT;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() throws IOException {
        a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) f()).next();
        String str = (String) entry.getKey();
        this.Y[this.X - 1] = str;
        k(entry.getValue());
        return str;
    }

    @Override // com.google.gson.stream.JsonReader
    public void nextNull() throws IOException {
        a(JsonToken.NULL);
        h();
        int i = this.X;
        if (i > 0) {
            int[] iArr = this.Z;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() throws IOException {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenPeek == jsonToken || jsonTokenPeek == JsonToken.NUMBER) {
            String strJ = ((o) h()).j();
            int i = this.X;
            if (i > 0) {
                int[] iArr = this.Z;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strJ;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() throws IOException {
        if (this.X == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objF = f();
        if (objF instanceof Iterator) {
            boolean z = this.q[this.X - 2] instanceof l;
            Iterator it = (Iterator) objF;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            k(it.next());
            return peek();
        }
        if (objF instanceof l) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objF instanceof f) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objF instanceof o)) {
            if (objF instanceof k) {
                return JsonToken.NULL;
            }
            if (objF == l0) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        o oVar = (o) objF;
        if (oVar.y()) {
            return JsonToken.STRING;
        }
        if (oVar.v()) {
            return JsonToken.BOOLEAN;
        }
        if (oVar.x()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() throws IOException {
        if (peek() == JsonToken.NAME) {
            nextName();
            this.Y[this.X - 2] = "null";
        } else {
            h();
            int i = this.X;
            if (i > 0) {
                this.Y[i - 1] = "null";
            }
        }
        int i2 = this.X;
        if (i2 > 0) {
            int[] iArr = this.Z;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String toString() {
        return a.class.getSimpleName() + locationString();
    }
}
