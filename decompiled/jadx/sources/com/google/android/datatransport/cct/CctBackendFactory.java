package com.google.android.datatransport.cct;

import androidx.annotation.Keep;
import ax.p360w5.h;
import ax.p360w5.m;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class CctBackendFactory implements ax.p360w5.d {
    @Override // ax.p360w5.d
    public m create(h hVar) {
        return new d(hVar.b(), hVar.e(), hVar.d());
    }
}
