package com.pcloud.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
abstract class c {
    static final List<String> a = Arrays.asList("com.android.chrome", "com.chrome.beta", "com.chrome.dev", "org.mozilla.firefox", "org.mozilla.firefox_beta", "org.mozilla.fenix");

    public static String a(Context context, List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent("android.support.customtabs.action.CustomTabsService"), 0);
        if (listQueryIntentServices != null) {
            for (String str : list) {
                Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
                while (it.hasNext()) {
                    ServiceInfo serviceInfo = it.next().serviceInfo;
                    if (str.equals(serviceInfo.packageName)) {
                        return serviceInfo.packageName;
                    }
                }
            }
        }
        return null;
    }

    static Map<String, String> b(String str) {
        String fragment = Uri.parse(str).getFragment();
        if (fragment == null) {
            return Collections.EMPTY_MAP;
        }
        TreeMap treeMap = new TreeMap();
        for (String str2 : fragment.split("&")) {
            int iIndexOf = str2.indexOf(61);
            treeMap.put(str2.substring(0, iIndexOf), str2.substring(iIndexOf + 1));
        }
        return treeMap;
    }
}
