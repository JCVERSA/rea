package com.google.gson.internal.sql;

import ax.Y8.r;
import ax.Y8.w;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes3.dex */
final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    static final w b = new w() { // from class: com.google.gson.internal.sql.SqlDateTypeAdapter.1
        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            if (aVar.c() == Date.class) {
                return new SqlDateTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat a;

    private SqlDateTypeAdapter() {
        this.a = new SimpleDateFormat("MMM d, yyyy");
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date c(JsonReader jsonReader) throws IOException {
        java.util.Date date;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String strNextString = jsonReader.nextString();
        try {
            synchronized (this) {
                date = this.a.parse(strNextString);
            }
            return new Date(date.getTime());
        } catch (ParseException e) {
            throw new r("Failed parsing '" + strNextString + "' as SQL Date; at path " + jsonReader.getPreviousPath(), e);
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, Date date) throws IOException {
        String str;
        if (date == null) {
            jsonWriter.nullValue();
            return;
        }
        synchronized (this) {
            str = this.a.format((java.util.Date) date);
        }
        jsonWriter.value(str);
    }
}
