package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import ax.p265q6.C2254p;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.f5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6318f5 {
    private static final String[] b = {"GoogleConsent", "gdprApplies", "EnableAdvertiserConsentMode", "PolicyVersion", "PurposeConsents", "CmpSdkID"};
    private final Map<String, String> a;

    private C6318f5(Map<String, String> map) {
        HashMap map2 = new HashMap();
        this.a = map2;
        map2.putAll(map);
    }

    private static int a(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getInt(str, -1);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public static C6318f5 c(SharedPreferences sharedPreferences) {
        HashMap map = new HashMap();
        String strF = f(sharedPreferences, "IABTCF_VendorConsents");
        if (!"\u0000".equals(strF) && strF.length() > 754) {
            map.put("GoogleConsent", String.valueOf(strF.charAt(754)));
        }
        int iA = a(sharedPreferences, "IABTCF_gdprApplies");
        if (iA != -1) {
            map.put("gdprApplies", String.valueOf(iA));
        }
        int iA2 = a(sharedPreferences, "IABTCF_EnableAdvertiserConsentMode");
        if (iA2 != -1) {
            map.put("EnableAdvertiserConsentMode", String.valueOf(iA2));
        }
        int iA3 = a(sharedPreferences, "IABTCF_PolicyVersion");
        if (iA3 != -1) {
            map.put("PolicyVersion", String.valueOf(iA3));
        }
        String strF2 = f(sharedPreferences, "IABTCF_PurposeConsents");
        if (!"\u0000".equals(strF2)) {
            map.put("PurposeConsents", strF2);
        }
        int iA4 = a(sharedPreferences, "IABTCF_CmpSdkID");
        if (iA4 != -1) {
            map.put("CmpSdkID", String.valueOf(iA4));
        }
        return new C6318f5(map);
    }

    public static String d(String str, boolean z) {
        if (!z || str.length() <= 4) {
            return str;
        }
        char[] charArray = str.toCharArray();
        int i = 1;
        while (i < 64) {
            if (charArray[4] == "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i)) {
                charArray[4] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(1 | i);
                return String.valueOf(charArray);
            }
            i++;
        }
        i = 0;
        charArray[4] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(1 | i);
        return String.valueOf(charArray);
    }

    private static String f(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getString(str, "\u0000");
        } catch (ClassCastException unused) {
            return "\u0000";
        }
    }

    private final int h() {
        try {
            String str = this.a.get("CmpSdkID");
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private final int i() {
        try {
            String str = this.a.get("PolicyVersion");
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final Bundle b() {
        if (!"1".equals(this.a.get("GoogleConsent")) || !"1".equals(this.a.get("gdprApplies")) || !"1".equals(this.a.get("EnableAdvertiserConsentMode"))) {
            return Bundle.EMPTY;
        }
        int i = i();
        if (i < 0) {
            return Bundle.EMPTY;
        }
        String str = this.a.get("PurposeConsents");
        if (TextUtils.isEmpty(str)) {
            return Bundle.EMPTY;
        }
        Bundle bundle = new Bundle();
        String str2 = "denied";
        if (str.length() > 0) {
            bundle.putString(C6330h3.a.AD_STORAGE.q, str.charAt(0) == '1' ? "granted" : "denied");
        }
        if (str.length() > 3) {
            bundle.putString(C6330h3.a.AD_PERSONALIZATION.q, (str.charAt(2) == '1' && str.charAt(3) == '1') ? "granted" : "denied");
        }
        if (str.length() > 6 && i >= 4) {
            String str3 = C6330h3.a.AD_USER_DATA.q;
            if (str.charAt(0) == '1' && str.charAt(6) == '1') {
                str2 = "granted";
            }
            bundle.putString(str3, str2);
        }
        return bundle;
    }

    public final String e() {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        int iH = h();
        if (iH < 0 || iH > 4095) {
            sb.append("00");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt((iH >> 6) & 63));
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(iH & 63));
        }
        int i = i();
        if (i < 0 || i > 63) {
            sb.append("0");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i));
        }
        C2254p.a(true);
        int i2 = "1".equals(this.a.get("gdprApplies")) ? 2 : 0;
        int i3 = i2 | 4;
        if ("1".equals(this.a.get("EnableAdvertiserConsentMode"))) {
            i3 = i2 | 12;
        }
        sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i3));
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C6318f5) {
            return g().equalsIgnoreCase(((C6318f5) obj).g());
        }
        return false;
    }

    final String g() {
        StringBuilder sb = new StringBuilder();
        for (String str : b) {
            if (this.a.containsKey(str)) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(str);
                sb.append("=");
                sb.append(this.a.get(str));
            }
        }
        return sb.toString();
    }

    public final int hashCode() {
        return g().hashCode();
    }

    public final String toString() {
        return g();
    }
}
