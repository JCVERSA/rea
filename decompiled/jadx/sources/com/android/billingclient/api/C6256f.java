package com.android.billingclient.api;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.android.billingclient.api.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C6256f {
    private final String a;
    private final JSONObject b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final List j;
    private final List k;

    /* JADX INFO: renamed from: com.android.billingclient.api.f$a */
    public static final class a {
        private final int a;
        private final int b;

        a(JSONObject jSONObject) throws JSONException {
            this.a = jSONObject.getInt("commitmentPaymentsCount");
            this.b = jSONObject.optInt("subsequentCommitmentPaymentsCount");
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.f$b */
    public static final class b {
        private final String a;
        private final long b;
        private final String c;
        private final String d;
        private final String e;
        private final String f;
        private final List g;
        private final Long h;
        private final a i;
        private final d j;
        private final C0560b k;
        private final String l;
        private final c m;
        private final K n;

        /* JADX INFO: renamed from: com.android.billingclient.api.f$b$a */
        public static final class a {
            private final Integer a;
            private final C0559a b;

            /* JADX INFO: renamed from: com.android.billingclient.api.f$b$a$a, reason: collision with other inner class name */
            public static final class C0559a {
                private final String a;
                private final long b;
                private final String c;

                C0559a(JSONObject jSONObject) {
                    this.a = jSONObject.optString("formattedDiscountAmount");
                    this.b = jSONObject.optLong("discountAmountMicros");
                    this.c = jSONObject.optString("discountAmountCurrencyCode");
                }
            }

            a(JSONObject jSONObject) throws JSONException {
                this.a = jSONObject.has("percentageDiscount") ? Integer.valueOf(jSONObject.optInt("percentageDiscount")) : null;
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("discountAmount");
                this.b = jSONObjectOptJSONObject != null ? new C0559a(jSONObjectOptJSONObject) : null;
            }
        }

        /* JADX INFO: renamed from: com.android.billingclient.api.f$b$b, reason: collision with other inner class name */
        public static final class C0560b {
            private final int a;
            private final int b;

            C0560b(JSONObject jSONObject) throws JSONException {
                this.a = jSONObject.getInt("maximumQuantity");
                this.b = jSONObject.getInt("remainingQuantity");
            }
        }

        /* JADX INFO: renamed from: com.android.billingclient.api.f$b$c */
        public static final class c {
            private final String a;
            private final String b;

            c(JSONObject jSONObject) throws JSONException {
                this.a = jSONObject.getString("rentalPeriod");
                String strOptString = jSONObject.optString("rentalExpirationPeriod");
                this.b = true == strOptString.isEmpty() ? null : strOptString;
            }
        }

        /* JADX INFO: renamed from: com.android.billingclient.api.f$b$d */
        public static final class d {
            private final Long a;
            private final Long b;

            d(JSONObject jSONObject) throws JSONException {
                this.a = jSONObject.has("startTimeMillis") ? Long.valueOf(jSONObject.optLong("startTimeMillis")) : null;
                this.b = jSONObject.has("endTimeMillis") ? Long.valueOf(jSONObject.optLong("endTimeMillis")) : null;
            }
        }

        b(JSONObject jSONObject) throws JSONException {
            this.a = jSONObject.optString("formattedPrice");
            this.b = jSONObject.optLong("priceAmountMicros");
            this.c = jSONObject.optString("priceCurrencyCode");
            String strOptString = jSONObject.optString("offerIdToken");
            this.d = true == strOptString.isEmpty() ? null : strOptString;
            String strOptString2 = jSONObject.optString("offerId");
            this.e = true == strOptString2.isEmpty() ? null : strOptString2;
            String strOptString3 = jSONObject.optString("purchaseOptionId");
            this.f = true == strOptString3.isEmpty() ? null : strOptString3;
            jSONObject.optInt("offerType");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("offerTags");
            this.g = new ArrayList();
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    this.g.add(jSONArrayOptJSONArray.getString(i));
                }
            }
            this.h = jSONObject.has("fullPriceMicros") ? Long.valueOf(jSONObject.optLong("fullPriceMicros")) : null;
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("discountDisplayInfo");
            this.i = jSONObjectOptJSONObject == null ? null : new a(jSONObjectOptJSONObject);
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("validTimeWindow");
            this.j = jSONObjectOptJSONObject2 == null ? null : new d(jSONObjectOptJSONObject2);
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("limitedQuantityInfo");
            this.k = jSONObjectOptJSONObject3 == null ? null : new C0560b(jSONObjectOptJSONObject3);
            this.l = jSONObject.optString("serializedDocid");
            JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("preorderDetails");
            if (jSONObjectOptJSONObject4 != null) {
                jSONObjectOptJSONObject4.getLong("preorderReleaseTimeMillis");
                jSONObjectOptJSONObject4.getLong("preorderPresaleEndTimeMillis");
            }
            JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("rentalDetails");
            this.m = jSONObjectOptJSONObject5 == null ? null : new c(jSONObjectOptJSONObject5);
            JSONObject jSONObjectOptJSONObject6 = jSONObject.optJSONObject("autoPayDetails");
            this.n = jSONObjectOptJSONObject6 != null ? new K(jSONObjectOptJSONObject6) : null;
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("pricingPhases");
            if (jSONArrayOptJSONArray2 == null) {
                return;
            }
            new d(jSONArrayOptJSONArray2);
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.d;
        }

        public final K c() {
            return this.n;
        }

        final String d() {
            return this.l;
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.f$c */
    public static final class c {
        private final String a;
        private final long b;
        private final String c;
        private final String d;
        private final int e;
        private final int f;

        c(JSONObject jSONObject) {
            this.d = jSONObject.optString("billingPeriod");
            this.c = jSONObject.optString("priceCurrencyCode");
            this.a = jSONObject.optString("formattedPrice");
            this.b = jSONObject.optLong("priceAmountMicros");
            this.f = jSONObject.optInt("recurrenceMode");
            this.e = jSONObject.optInt("billingCycleCount");
        }

        public String a() {
            return this.a;
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.f$d */
    public static class d {
        private final List a;

        d(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        arrayList.add(new c(jSONObjectOptJSONObject));
                    }
                }
            }
            this.a = arrayList;
        }

        public List<c> a() {
            return this.a;
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.f$e */
    public static final class e {
        private final String a;
        private final String b;
        private final String c;
        private final d d;
        private final List e;
        private final a f;

        e(JSONObject jSONObject) throws JSONException {
            this.a = jSONObject.optString("basePlanId");
            String strOptString = jSONObject.optString("offerId");
            this.b = true == strOptString.isEmpty() ? null : strOptString;
            this.c = jSONObject.getString("offerIdToken");
            this.d = new d(jSONObject.getJSONArray("pricingPhases"));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("installmentPlanDetails");
            this.f = jSONObjectOptJSONObject != null ? new a(jSONObjectOptJSONObject) : null;
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("transitionPlanDetails");
            if (jSONObjectOptJSONObject2 != null) {
                jSONObjectOptJSONObject2.getString("productId");
                jSONObjectOptJSONObject2.optString("title");
                jSONObjectOptJSONObject2.optString("name");
                jSONObjectOptJSONObject2.optString("description");
                jSONObjectOptJSONObject2.optString("basePlanId");
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("pricingPhase");
                if (jSONObjectOptJSONObject3 != null) {
                    new c(jSONObjectOptJSONObject3);
                }
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("offerTags");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                }
            }
            this.e = arrayList;
        }

        public String a() {
            return this.c;
        }

        public d b() {
            return this.d;
        }
    }

    C6256f(String str) throws JSONException {
        this.a = str;
        JSONObject jSONObject = new JSONObject(str);
        this.b = jSONObject;
        String strOptString = jSONObject.optString("productId");
        this.c = strOptString;
        String strOptString2 = jSONObject.optString("type");
        this.d = strOptString2;
        if (TextUtils.isEmpty(strOptString)) {
            throw new IllegalArgumentException("Product id cannot be empty.");
        }
        if (TextUtils.isEmpty(strOptString2)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.e = jSONObject.optString("title");
        this.f = jSONObject.optString("name");
        this.g = jSONObject.optString("description");
        jSONObject.optString("packageDisplayName");
        jSONObject.optString("iconUrl");
        this.h = jSONObject.optString("skuDetailsToken");
        this.i = jSONObject.optString("serializedDocid");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("subscriptionOfferDetails");
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(new e(jSONArrayOptJSONArray.getJSONObject(i)));
            }
            this.j = arrayList;
        } else {
            this.j = (strOptString2.equals("subs") || strOptString2.equals("play_pass_subs")) ? new ArrayList() : null;
        }
        JSONObject jSONObjectOptJSONObject = this.b.optJSONObject("oneTimePurchaseOfferDetails");
        JSONArray jSONArrayOptJSONArray2 = this.b.optJSONArray("oneTimePurchaseOfferDetailsList");
        ArrayList arrayList2 = new ArrayList();
        if (jSONArrayOptJSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                arrayList2.add(new b(jSONArrayOptJSONArray2.getJSONObject(i2)));
            }
            this.k = arrayList2;
            return;
        }
        if (jSONObjectOptJSONObject == null) {
            this.k = null;
        } else {
            arrayList2.add(new b(jSONObjectOptJSONObject));
            this.k = arrayList2;
        }
    }

    public b a() {
        List list = this.k;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (b) list.get(0);
    }

    public List<b> b() {
        return this.k;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public List<e> e() {
        return this.j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C6256f) {
            return TextUtils.equals(this.a, ((C6256f) obj).a);
        }
        return false;
    }

    public final String f() {
        return this.b.optString("packageName");
    }

    final String g() {
        return this.h;
    }

    public String h() {
        return this.i;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    final List i() {
        return this.k;
    }

    public String toString() {
        List list = this.j;
        return "ProductDetails{jsonString='" + this.a + "', parsedJson=" + this.b.toString() + ", productId='" + this.c + "', productType='" + this.d + "', title='" + this.e + "', productDetailsToken='" + this.h + "', subscriptionOfferDetails=" + String.valueOf(list) + "}";
    }
}
