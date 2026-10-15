package com.google.android.datatransport.cct;

import ax.p344v5.g;
import j$.util.DesugarCollections;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements g {
    static final String c;
    static final String d;
    private static final String e;
    private static final Set<ax.p312t5.b> f;
    public static final a g;
    public static final a h;
    private final String a;
    private final String b;

    static {
        String strA = e.a("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        c = strA;
        String strA2 = e.a("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        d = strA2;
        String strA3 = e.a("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        e = strA3;
        f = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(ax.p312t5.b.b("proto"), ax.p312t5.b.b("json"))));
        g = new a(strA, null);
        h = new a(strA2, strA3);
    }

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static a c(byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (!str.startsWith("1$")) {
            throw new IllegalArgumentException("Version marker missing from extras");
        }
        String[] strArrSplit = str.substring(2).split(Pattern.quote("\\"), 2);
        if (strArrSplit.length != 2) {
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        String str2 = strArrSplit[0];
        if (str2.isEmpty()) {
            throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
        }
        String str3 = strArrSplit[1];
        if (str3.isEmpty()) {
            str3 = null;
        }
        return new a(str2, str3);
    }

    @Override // ax.p344v5.g
    public Set<ax.p312t5.b> a() {
        return f;
    }

    public byte[] b() {
        String str = this.b;
        if (str == null && this.a == null) {
            return null;
        }
        String str2 = this.a;
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        return String.format("%s%s%s%s", "1$", str2, "\\", str).getBytes(Charset.forName("UTF-8"));
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.a;
    }

    @Override // ax.p344v5.f
    public byte[] getExtras() {
        return b();
    }

    @Override // ax.p344v5.f
    public String getName() {
        return "cct";
    }
}
