package com.alphainventor.filemanager.data;

import androidx.annotation.Keep;
import ax.B1.f;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class ApkPlusInfo {
    public static final String ICON_FILENAME = "icon.png";
    public static final String INFO_FILENAME = "apk+.json";
    public String app_name;
    public int min_sdk_version;
    public String package_name;
    public long version_code;
    public String version_name;

    public void fillInfo(f fVar) {
        this.app_name = fVar.m();
        this.package_name = fVar.n();
        this.version_code = fVar.u();
        this.version_name = fVar.t();
        this.min_sdk_version = fVar.l();
    }
}
