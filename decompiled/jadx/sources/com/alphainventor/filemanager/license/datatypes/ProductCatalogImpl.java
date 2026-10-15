package com.alphainventor.filemanager.license.datatypes;

import androidx.annotation.Keep;
import ax.Z8.c;
import ax.p118h3.e;
import ax.p150j3.a;
import ax.p190lb.d;
import ax.p206mb.b;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class ProductCatalogImpl implements a, Serializable {
    public static String CATEGORY_PREMIUM_BASIC = "premium_basic";
    private static e sProductIdParser = new e("com.alphainventor.filemanager");

    @c("premium_basic")
    private ProductCategoryPremiumBasic premiumBasic;

    public static String getProductCategoryStatic(String str) throws ax.p206mb.a {
        String str2 = sProductIdParser.b(str).get("category");
        if (str2 != null) {
            return str2;
        }
        throw new ax.p206mb.a("category is null");
    }

    public static d getProductTypeStatic(String str) {
        return str.contains(".onetime") ? d.MANAGED : d.SUBSCRIPTION;
    }

    public static String getProductVariationStatic(String str) throws ax.p206mb.a {
        String str2 = sProductIdParser.b(str).get("variation");
        if (str2 != null) {
            return str2;
        }
        throw new ax.p206mb.a("variation is null");
    }

    @Override // ax.p150j3.a
    public ax.p190lb.c getPremiumBasicOnetime() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.onetime) == null) {
            return null;
        }
        return new ax.p190lb.c(d.MANAGED, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "onetime", str));
    }

    @Override // ax.p150j3.a
    public ax.p190lb.c getPremiumBasicOnetimeDiscount() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.onetime_dc) == null) {
            return null;
        }
        return new ax.p190lb.c(d.MANAGED, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "onetime_dc", str));
    }

    @Override // ax.p150j3.a
    public ax.p190lb.c getPremiumBasicYearly() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.yearly) == null) {
            return null;
        }
        return new ax.p190lb.c(d.SUBSCRIPTION, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "yearly", str));
    }

    @Override // ax.p150j3.a
    public ax.p190lb.c getPremiumBasicYearlyDiscount() {
        String str;
        ProductCategoryPremiumBasic productCategoryPremiumBasic = this.premiumBasic;
        if (productCategoryPremiumBasic == null || (str = productCategoryPremiumBasic.yearly_dc) == null) {
            return null;
        }
        return new ax.p190lb.c(d.SUBSCRIPTION, sProductIdParser.a(CATEGORY_PREMIUM_BASIC, "yearly_dc", str));
    }

    public String getProductCategory(ax.p190lb.c cVar) throws b {
        try {
            return getProductCategoryStatic(cVar.X);
        } catch (ax.p206mb.a e) {
            throw new b(e);
        }
    }

    public String getProductVariation(ax.p190lb.c cVar) throws b {
        try {
            return getProductVariationStatic(cVar.X);
        } catch (ax.p206mb.a e) {
            throw new b(e);
        }
    }

    public String toString() {
        return String.format("ProductCatalogImpl(premiumBasic=%s)", this.premiumBasic);
    }
}
