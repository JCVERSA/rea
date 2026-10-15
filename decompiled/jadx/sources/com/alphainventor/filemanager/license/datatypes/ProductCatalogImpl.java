package com.alphainventor.filemanager.license.datatypes;

import androidx.annotation.Keep;
import ax.L1.e;
import ax.N1.a;
import ax.p123ia.d;
import ax.p137ja.b;
import ax.p163l8.c;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class ProductCatalogImpl implements a, Serializable {
    public static String CATEGORY_PREMIUM_BASIC = "premium_basic";
    private static e sProductIdParser = new e("com.alphainventor.filemanager");

    @c("premium_basic")
    private ProductCategoryPremiumBasic premiumBasic;

    public static String getProductCategoryStatic(String str) throws ax.p137ja.a {
        String str2 = sProductIdParser.b(str).get("category");
        if (str2 != null) {
            return str2;
        }
        throw new ax.p137ja.a("category is null");
    }

    public static d getProductTypeStatic(String str) {
        return str.contains(".onetime") ? d.MANAGED : d.SUBSCRIPTION;
    }

    public static String getProductVariationStatic(String str) throws ax.p137ja.a {
        String str2 = sProductIdParser.b(str).get("variation");
        if (str2 != null) {
            return str2;
        }
        throw new ax.p137ja.a("variation is null");
    }

    @Override // ax.N1.a
    public ax.p123ia.c getPremiumBasicOnetime() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.onetime) == null) {
            return null;
        }
        return new ax.p123ia.c(d.MANAGED, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "onetime", str));
    }

    @Override // ax.N1.a
    public ax.p123ia.c getPremiumBasicOnetimeDiscount() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.onetime_dc) == null) {
            return null;
        }
        return new ax.p123ia.c(d.MANAGED, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "onetime_dc", str));
    }

    @Override // ax.N1.a
    public ax.p123ia.c getPremiumBasicYearly() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.yearly) == null) {
            return null;
        }
        return new ax.p123ia.c(d.SUBSCRIPTION, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "yearly", str));
    }

    @Override // ax.N1.a
    public ax.p123ia.c getPremiumBasicYearlyDiscount() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.yearly_dc) == null) {
            return null;
        }
        return new ax.p123ia.c(d.SUBSCRIPTION, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "yearly_dc", str));
    }

    public String getProductCategory(ax.p123ia.c cVar) throws b {
        try {
            return getProductCategoryStatic(cVar.X);
        } catch (ax.p137ja.a e) {
            throw new b(e);
        }
    }

    public String getProductVariation(ax.p123ia.c cVar) throws b {
        try {
            return getProductVariationStatic(cVar.X);
        } catch (ax.p137ja.a e) {
            throw new b(e);
        }
    }

    public String toString() {
        return String.format("ProductCatalogImpl(premiumBasic=%s)", this.premiumBasic);
    }
}
