package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepName;

/* JADX INFO: loaded from: classes3.dex */
@KeepName
public class GooglePlayServicesManifestException extends IllegalStateException {
    private final int q;

    public GooglePlayServicesManifestException(int i, String str) {
        super(str);
        this.q = i;
    }
}
