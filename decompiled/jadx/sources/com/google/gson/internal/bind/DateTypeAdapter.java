package com.google.gson.internal.bind;

import ax.Y8.r;
import ax.Y8.w;
import ax.p012a9.d;
import ax.p012a9.i;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final w b = new w() { // from class: com.google.gson.internal.bind.DateTypeAdapter.1
        @Override // ax.Y8.w
        public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
            if (aVar.c() == Date.class) {
                return new DateTypeAdapter();
            }
            return null;
        }
    };
    private final List<DateFormat> a;

    public DateTypeAdapter() {
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (d.d()) {
            arrayList.add(i.c(2, 2));
        }
    }

    private Date f(JsonReader jsonReader) throws IOException {
        String strNextString = jsonReader.nextString();
        synchronized (this.a) {
            try {
                Iterator<DateFormat> it = this.a.iterator();
                while (it.hasNext()) {
                    try {
                        return it.next().parse(strNextString);
                    } catch (ParseException unused) {
                    }
                }
                try {
                    return ax.p044c9.a.c(strNextString, new ParsePosition(0));
                } catch (ParseException e) {
                    throw new r("Failed parsing '" + strNextString + "' as Date; at path " + jsonReader.getPreviousPath(), e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public Date c(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return f(jsonReader);
        }
        jsonReader.nextNull();
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, Date date) throws IOException {
        String str;
        if (date == null) {
            jsonWriter.nullValue();
            return;
        }
        DateFormat dateFormat = this.a.get(0);
        synchronized (this.a) {
            str = dateFormat.format(date);
        }
        jsonWriter.value(str);
    }
}
