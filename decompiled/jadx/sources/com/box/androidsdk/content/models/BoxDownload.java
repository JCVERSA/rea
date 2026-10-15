package com.box.androidsdk.content.models;

import com.box.androidsdk.content.utils.SdkUtils;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class BoxDownload extends BoxJsonObject {
    public BoxDownload(String str, long j, String str2, String str3, String str4, String str5) {
        if (!SdkUtils.l(str)) {
            J(str);
        }
        x("content_length", Long.valueOf(j));
        if (!SdkUtils.l(str2)) {
            y("content_type", str2);
        }
        if (!SdkUtils.l(str3)) {
            H(str3);
        }
        if (!SdkUtils.l(str4)) {
            y("date", str4);
        }
        if (SdkUtils.l(str5)) {
            return;
        }
        y("expiration", str5);
    }

    public String D() {
        return u("file_name");
    }

    public File E() {
        return null;
    }

    protected void H(String str) {
        int iLastIndexOf = str.lastIndexOf("/");
        int iIndexOf = str.indexOf("-");
        x("start_range", Long.valueOf(Long.parseLong(str.substring(str.indexOf("bytes") + 6, iIndexOf))));
        x("end_range", Long.valueOf(Long.parseLong(str.substring(iIndexOf + 1, iLastIndexOf))));
        x("total_range", Long.valueOf(Long.parseLong(str.substring(iLastIndexOf + 1))));
    }

    protected void J(String str) {
        for (String str2 : str.split(";")) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("filename=")) {
                y("file_name", strTrim.endsWith("\"") ? strTrim.substring(strTrim.indexOf("\"") + 1, strTrim.length() - 1) : strTrim.substring(9));
            }
        }
    }
}
