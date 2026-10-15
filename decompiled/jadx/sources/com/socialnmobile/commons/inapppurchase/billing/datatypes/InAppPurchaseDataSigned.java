package com.socialnmobile.commons.inapppurchase.billing.datatypes;

import androidx.annotation.Keep;
import ax.p222nb.a;
import ax.p222nb.b;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class InAppPurchaseDataSigned implements Serializable {
    public String item;
    public String purchaseData;
    public String signature;

    public InAppPurchaseData getUnverifiedPurchaseData(b bVar) throws a {
        return bVar.b(this.purchaseData);
    }

    public String toString() {
        return String.format("InAppPurchaseDataSigned(item=%s purchaseData=%s signature=%s)", this.item, this.purchaseData, this.signature);
    }
}
