package com.google.android.gms.common;

import ax.p217n6.C2177h;
import com.google.android.gms.common.annotation.KeepName;

/* JADX INFO: loaded from: classes3.dex */
@KeepName
public final class GooglePlayServicesIncorrectManifestValueException extends GooglePlayServicesManifestException {
    public GooglePlayServicesIncorrectManifestValueException(int i) {
        super(i, "The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + C2177h.a + " but found " + i + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
    }
}
