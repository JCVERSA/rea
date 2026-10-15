package com.android.billingclient.api;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.android.billingclient.api.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C6259i {
    private final String a;
    private final String b;
    private final String c;
    private final int d;
    private final String e;

    C6259i(String str) throws JSONException {
        this.a = str;
        JSONObject jSONObject = new JSONObject(str);
        this.b = jSONObject.optString("productId");
        String strOptString = jSONObject.optString("type");
        this.c = strOptString;
        this.d = jSONObject.has("statusCode") ? jSONObject.optInt("statusCode") : 0;
        if (TextUtils.isEmpty(strOptString)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.e = jSONObject.optString("serializedDocid");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C6259i) {
            return TextUtils.equals(this.a, ((C6259i) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return "UnfetchedProduct{productId='" + this.b + "', productType='" + this.c + "', statusCode=" + this.d + "}";
    }
}
