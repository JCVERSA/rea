package com.google.gson.internal.sql;

import ax.Y8.r;
import ax.Y8.w;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
final class SqlTimeTypeAdapter extends TypeAdapter<Time> {
    static final w b = new w() { // from class: com.google.gson.internal.sql.SqlTimeTypeAdapter.1
        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            if (aVar.c() == Time.class) {
                return new SqlTimeTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat a;

    private SqlTimeTypeAdapter() {
        this.a = new SimpleDateFormat("hh:mm:ss a");
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Time c(JsonReader jsonReader) throws IOException {
        Time time;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String strNextString = jsonReader.nextString();
        try {
            synchronized (this) {
                time = new Time(this.a.parse(strNextString).getTime());
            }
            return time;
        } catch (ParseException e) {
            throw new r("Failed parsing '" + strNextString + "' as SQL Time; at path " + jsonReader.getPreviousPath(), e);
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, Time time) throws IOException {
        String str;
        if (time == null) {
            jsonWriter.nullValue();
            return;
        }
        synchronized (this) {
            str = this.a.format((Date) time);
        }
        jsonWriter.value(str);
    }
}
