package com.socialnmobile.commons.inapppurchase.billing.datatypes;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class LicenseAPIError {
    public int code;
    public String message;

    public String toString() {
        return String.format("LicenseAPIError(code=%s message=%s)", Integer.valueOf(this.code), this.message);
    }
}
