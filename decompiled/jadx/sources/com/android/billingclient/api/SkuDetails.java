package com.android.billingclient.api;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class SkuDetails {
    private final String a;
    private final JSONObject b;

    public String a() {
        return this.b.optString("productId");
    }

    public String b() {
        return this.b.optString("type");
    }

    public int c() {
        return this.b.optInt("offer_type");
    }

    public String d() {
        return this.b.optString("offer_id");
    }

    public String e() {
        JSONObject jSONObject = this.b;
        String strOptString = jSONObject.optString("offerIdToken");
        return strOptString.isEmpty() ? jSONObject.optString("offer_id_token") : strOptString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkuDetails) {
            return TextUtils.equals(this.a, ((SkuDetails) obj).a);
        }
        return false;
    }

    public final String f() {
        return this.b.optString("packageName");
    }

    public String g() {
        return this.b.optString("serializedDocid");
    }

    final String h() {
        return this.b.optString("skuDetailsToken");
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return "SkuDetails: ".concat(String.valueOf(this.a));
    }
}
