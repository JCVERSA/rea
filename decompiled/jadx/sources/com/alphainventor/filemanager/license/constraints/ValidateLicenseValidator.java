package com.alphainventor.filemanager.license.constraints;

import ax.M1.a;
import ax.Ma.b;
import ax.p151ka.c;

/* JADX INFO: loaded from: classes.dex */
public class ValidateLicenseValidator implements b<a, c> {
    @Override // ax.Ma.b
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(c cVar, ax.Ma.c cVar2) {
        try {
            cVar.getProductId();
            cVar.getProductType();
            cVar.getExpiryTime();
            cVar.getLicenseeId();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ax.Ma.b
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(a aVar) {
    }
}
