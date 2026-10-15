package com.google.gson.internal.sql;

import ax.Y8.w;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
class SqlTimestampTypeAdapter extends TypeAdapter<Timestamp> {
    static final w b = new w() { // from class: com.google.gson.internal.sql.SqlTimestampTypeAdapter.1
        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            if (aVar.c() == Timestamp.class) {
                return new SqlTimestampTypeAdapter(gson.n(Date.class));
            }
            return null;
        }
    };
    private final TypeAdapter<Date> a;

    private SqlTimestampTypeAdapter(TypeAdapter<Date> typeAdapter) {
        this.a = typeAdapter;
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Timestamp c(JsonReader jsonReader) throws IOException {
        Date dateC = this.a.c(jsonReader);
        if (dateC != null) {
            return new Timestamp(dateC.getTime());
        }
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
        this.a.e(jsonWriter, timestamp);
    }
}
