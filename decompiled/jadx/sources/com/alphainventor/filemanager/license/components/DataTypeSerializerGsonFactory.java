package com.alphainventor.filemanager.license.components;

import ax.p149k8.w;
import ax.p151ka.c;
import ax.p151ka.e;
import ax.p151ka.h;
import com.alphainventor.filemanager.license.datatypes.LicenseByCoupon;
import com.alphainventor.filemanager.license.datatypes.LicensePurchasedImpl;
import com.alphainventor.filemanager.license.datatypes.ProductCatalogImpl;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.a;

/* JADX INFO: loaded from: classes.dex */
public class DataTypeSerializerGsonFactory {
    public static Gson a() {
        return new a().e(new w() { // from class: com.alphainventor.filemanager.license.components.DataTypeSerializerGsonFactory.1
            @Override // ax.p149k8.w
            public <T> TypeAdapter<T> b(Gson gson, ax.p233q8.a<T> aVar) {
                if (e.class.equals(aVar.c())) {
                    return gson.l(LicensePurchasedImpl.class);
                }
                if (h.class.equals(aVar.c())) {
                    return gson.l(ProductCatalogImpl.class);
                }
                return null;
            }
        }).e(RuntimeTypeAdapterFactory.e(c.class, "@type").f(e.class, "LicensePurchased").f(LicenseByCoupon.class, "com.alphainventor.filemanager.LicenseByCoupon")).f().g().b();
    }
}
