package com.alphainventor.filemanager.license.datatypes;

import androidx.annotation.Keep;
import ax.p134i3.a;
import ax.p190lb.d;
import ax.p318tb.c;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
@a
@Keep
@c
public class LicenseByCoupon implements ax.p222nb.c, Serializable {

    @ax.p334ub.a
    private String couponCode;
    private String deviceId;
    private transient Date expiryTimeDate;
    private long expiryTimeMillis;

    @ax.p334ub.a
    private String productId;

    private String getProductIdPrivate() {
        String str = this.productId;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Unexpected null productId");
    }

    public String getCouponCode() {
        String str = this.couponCode;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Unexpected null couponCode");
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
        return ax.p190lb.a.FREE;
    }

    @Override // ax.p222nb.c
    public String getLicenseeId() {
        String str = this.deviceId;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Unexpected null deviceId");
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

    public String toString() {
        return String.format("LicenseByCoupon(productId=%s deviceId=%s expiryTimeMillis=%s couponCode=%s)", this.productId, this.deviceId, Long.valueOf(this.expiryTimeMillis), this.couponCode);
    }
}
