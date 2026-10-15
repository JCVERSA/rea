package com.alphainventor.filemanager.license.datatypes;

import androidx.annotation.Keep;
import ax.M1.a;
import ax.p123ia.d;
import ax.p235qa.c;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
@c
@Keep
@a
public class LicenseByCoupon implements ax.p151ka.c, Serializable {

    @ax.p249ra.a
    private String couponCode;
    private String deviceId;
    private transient Date expiryTimeDate;
    private long expiryTimeMillis;

    @ax.p249ra.a
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

    @Override // ax.p151ka.c
    public Date getExpiryTime() {
        if (this.expiryTimeDate == null) {
            this.expiryTimeDate = new Date(this.expiryTimeMillis);
        }
        return this.expiryTimeDate;
    }

    @Override // ax.p151ka.c
    public ax.p123ia.a getLicenseState() {
        return ax.p123ia.a.FREE;
    }

    @Override // ax.p151ka.c
    public String getLicenseeId() {
        String str = this.deviceId;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Unexpected null deviceId");
    }

    @Override // ax.p151ka.c
    public String getProductCategory() {
        try {
            return ProductCatalogImpl.getProductCategoryStatic(getProductIdPrivate());
        } catch (ax.p137ja.a e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // ax.p151ka.c
    public ax.p123ia.c getProductId() {
        return new ax.p123ia.c(getProductType(), getProductIdPrivate());
    }

    @Override // ax.p151ka.c
    public d getProductType() {
        return ProductCatalogImpl.getProductTypeStatic(getProductIdPrivate());
    }

    public String getProductVariation() {
        try {
            return ProductCatalogImpl.getProductVariationStatic(getProductIdPrivate());
        } catch (ax.p137ja.a e) {
            throw new IllegalStateException(e);
        }
    }

    public String toString() {
        return String.format("LicenseByCoupon(productId=%s deviceId=%s expiryTimeMillis=%s couponCode=%s)", this.productId, this.deviceId, Long.valueOf(this.expiryTimeMillis), this.couponCode);
    }
}
