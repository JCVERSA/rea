package com.alphainventor.filemanager.license.constraints;

import ax.Wb.b;
import ax.p134i3.a;
import ax.p222nb.c;

/* JADX INFO: loaded from: classes.dex */
public class ValidateLicenseValidator implements b<a, c> {
    @Override // ax.Wb.b
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(a aVar) {
    }

    @Override // ax.Wb.b
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(c cVar, ax.Wb.c cVar2) {
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
}
