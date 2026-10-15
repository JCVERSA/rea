package com.android.billingclient.api;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Purchase {
    private final String a;
    private final String b;
    private final JSONObject c;

    public Purchase(String str, String str2) throws JSONException {
        this.a = str;
        this.b = str2;
        this.c = new JSONObject(str);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        JSONObject jSONObject = this.c;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public String c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) obj;
        return TextUtils.equals(this.a, purchase.a()) && TextUtils.equals(this.b, purchase.c());
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return "Purchase. Json: ".concat(String.valueOf(this.a));
    }
}
