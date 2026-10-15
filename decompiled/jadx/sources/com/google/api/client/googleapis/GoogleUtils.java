package com.google.api.client.googleapis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class GoogleUtils {
    public static final String a;
    public static final Integer b;
    public static final Integer c;
    public static final Integer d;
    static final Pattern e;

    static {
        String strA = a();
        a = strA;
        Pattern patternCompile = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(-SNAPSHOT)?");
        e = patternCompile;
        Matcher matcher = patternCompile.matcher(strA);
        matcher.find();
        b = Integer.valueOf(Integer.parseInt(matcher.group(1)));
        c = Integer.valueOf(Integer.parseInt(matcher.group(2)));
        d = Integer.valueOf(Integer.parseInt(matcher.group(3)));
    }

    private GoogleUtils() {
    }

    private static String a() {
        String property = null;
        try {
            InputStream resourceAsStream = GoogleUtils.class.getResourceAsStream("google-api-client.properties");
            if (resourceAsStream != null) {
                try {
                    Properties properties = new Properties();
                    properties.load(resourceAsStream);
                    property = properties.getProperty("google-api-client.version");
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            resourceAsStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        } catch (IOException unused) {
        }
        return property == null ? "unknown-version" : property;
    }
}
