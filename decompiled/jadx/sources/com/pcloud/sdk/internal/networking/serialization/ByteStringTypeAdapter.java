package com.pcloud.sdk.internal.networking.serialization;

import ax.p192ld.h;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ByteStringTypeAdapter extends TypeAdapter<h> {
    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public h c(JsonReader jsonReader) throws IOException {
        return h.k(jsonReader.nextString());
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void e(JsonWriter jsonWriter, h hVar) throws IOException {
        jsonWriter.value(hVar.u());
    }
}
