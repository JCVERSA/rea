package com.pcloud.sdk.internal.networking.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class DateTypeAdapter extends TypeAdapter<Date> {
    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date c(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NUMBER) {
            return new Date(jsonReader.nextLong() * 1000);
        }
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(date.getTime());
        }
    }
}
