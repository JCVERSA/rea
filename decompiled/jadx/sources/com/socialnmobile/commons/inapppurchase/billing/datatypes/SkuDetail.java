package com.socialnmobile.commons.inapppurchase.billing.datatypes;

import androidx.annotation.Keep;
import ax.p318tb.c;
import ax.p334ub.a;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
@Keep
@c
public class SkuDetail implements Serializable {
    public String description;
    public String price;
    public long price_amount_micros;
    public String price_currency_code;

    @a
    public String productId;
    public String title;

    @a
    public String type;

    public String toString() {
        return String.format("SkuDetail(title=%s description=%s productId=%s type=%s price=%s price_amount_micros=%s price_currency_code=%s)", this.title, this.description, this.productId, this.type, this.price, Long.valueOf(this.price_amount_micros), this.price_currency_code);
    }
}
