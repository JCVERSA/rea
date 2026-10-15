package com.socialnmobile.commons.inapppurchase.billing.datatypes;

import androidx.annotation.Keep;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class InAppPurchaseData implements Serializable {
    public static final int PURCHASE_STATE_CANCELED = 1;
    public static final int PURCHASE_STATE_PURCHASED = 0;
    public static final int PURCHASE_STATE_REFUNDED = 2;
    public boolean autoRenewing;
    public String developerPayload;
    public String orderId;
    public String packageName;
    public String productId;
    public int purchaseState;
    public long purchaseTime;
    public String purchaseToken;

    public String toString() {
        return String.format("InAppPurchaseData(autoRenewing=%s orderId=%s packageName=%s productId=%s purchaseTime=%s purchaseState=%s developerPayload=%s purchaseToken=%s)", Boolean.valueOf(this.autoRenewing), this.orderId, this.packageName, this.productId, Long.valueOf(this.purchaseTime), Integer.valueOf(this.purchaseState), this.developerPayload, this.purchaseToken);
    }
}
