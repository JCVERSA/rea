package com.alphainventor.filemanager.license.datatypes;

import androidx.annotation.Keep;
import ax.p134i3.a;
import ax.p190lb.d;
import ax.p206mb.b;
import ax.p222nb.e;
import ax.p318tb.c;
import com.socialnmobile.commons.inapppurchase.billing.datatypes.InAppPurchaseData;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
@a
@Keep
@c
public class LicensePurchasedImpl implements e, Serializable {
    private Integer consumptionStateCode;
    private String deviceId;
    private transient Date expiryTimeDate;
    private long expiryTimeMillis;

    @ax.p334ub.a
    private InAppPurchaseData inAppPurchaseDataVerified;

    @ax.p334ub.a
    private Integer licenseStateCode;
    private Integer paymentStateCode;

    @ax.p334ub.a
    private String productId;
    private Integer purchaseStateCode;
    private Integer purchaseTypeCode;

    private String getProductIdPrivate() {
        String str = this.productId;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Unexpected null productId");
    }

    public Integer getConsumptionStateCode() {
        return this.consumptionStateCode;
    }

    @Override // ax.p222nb.c
    public Date getExpiryTime() {
        if (this.expiryTimeDate == null) {
            this.expiryTimeDate = new Date(this.expiryTimeMillis);
        }
        return this.expiryTimeDate;
    }

    @Override // ax.p222nb.c
    public ax.p190lb.a getLicenseState() {
        Integer num = this.licenseStateCode;
        if (num == null) {
            throw new IllegalStateException("Unexpected null licenseStateCode");
        }
        try {
            return ax.p190lb.a.h(num.intValue());
        } catch (b e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // ax.p222nb.c
    public String getLicenseeId() {
        String str = this.deviceId;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Unexpected null deviceId");
    }

    public Integer getPaymentStateCode() {
        return this.paymentStateCode;
    }

    @Override // ax.p222nb.c
    public String getProductCategory() {
        try {
            return ProductCatalogImpl.getProductCategoryStatic(getProductIdPrivate());
        } catch (ax.p206mb.a e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // ax.p222nb.c
    public ax.p190lb.c getProductId() {
        return new ax.p190lb.c(getProductType(), getProductIdPrivate());
    }

    @Override // ax.p222nb.c
    public d getProductType() {
        return ProductCatalogImpl.getProductTypeStatic(getProductIdPrivate());
    }

    public String getProductVariation() {
        try {
            return ProductCatalogImpl.getProductVariationStatic(getProductIdPrivate());
        } catch (ax.p206mb.a e) {
            throw new IllegalStateException(e);
        }
    }

    public Integer getPurchaseStateCode() {
        return this.purchaseStateCode;
    }

    public Integer getPurchaseTypeCode() {
        return this.purchaseTypeCode;
    }

    @Override // ax.p222nb.e
    public InAppPurchaseData getVerifiedPurchaseData() {
        InAppPurchaseData inAppPurchaseData = this.inAppPurchaseDataVerified;
        if (inAppPurchaseData != null) {
            return inAppPurchaseData;
        }
        throw new IllegalStateException("Unexpected null inAppPurchaseDataVerified");
    }

    public String toString() {
        return String.format("LicensePurchasedImpl(expiryTimeMillis=%s productId=%s deviceId=%s inAppPurchaseDataVerified=%s licenseStateCode=%s purchaseTypeCode=%s purchaseStateCode=%s paymentStateCode=%s consumptionStateCode=%s)", Long.valueOf(this.expiryTimeMillis), this.productId, this.deviceId, this.inAppPurchaseDataVerified, this.licenseStateCode, this.purchaseStateCode, this.purchaseTypeCode, this.paymentStateCode, this.consumptionStateCode);
    }
}
