package com.alphainventor.filemanager.license.components;

import ax.Y8.w;
import ax.p222nb.c;
import ax.p222nb.e;
import ax.p222nb.h;
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
            @Override // ax.Y8.w
            public <T> TypeAdapter<T> b(Gson gson, ax.p076e9.a<T> aVar) {
                if (e.class.equals(aVar.c())) {
                    return gson.n(LicensePurchasedImpl.class);
                }
                if (h.class.equals(aVar.c())) {
                    return gson.n(ProductCatalogImpl.class);
                }
                return null;
            }
        }).e(RuntimeTypeAdapterFactory.e(c.class, "@type").f(e.class, "LicensePurchased").f(LicenseByCoupon.class, "com.alphainventor.filemanager.LicenseByCoupon")).f().g().b();
    }
}
