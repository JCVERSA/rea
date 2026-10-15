package com.jcraft.jsch;

import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class HostKey {
    private static final byte[][] f = {Util.v("ssh-dss"), Util.v("ssh-rsa"), Util.v("ecdsa-sha2-nistp256"), Util.v("ecdsa-sha2-nistp384"), Util.v("ecdsa-sha2-nistp521"), Util.v("ssh-ed25519"), Util.v("ssh-ed448")};
    protected String a;
    protected String b;
    protected int c;
    protected byte[] d;
    protected String e;

    public HostKey(String str, int i, byte[] bArr) throws JSchException {
        this(str, i, bArr, null);
    }

    public HostKey(String str, int i, byte[] bArr, String str2) throws JSchException {
        this(HttpUrl.FRAGMENT_ENCODE_SET, str, i, bArr, str2);
    }

    public HostKey(String str, String str2, int i, byte[] bArr, String str3) throws JSchException {
        this.a = str;
        this.b = str2;
        if (i == 0) {
            byte b = bArr[8];
            if (b == 100) {
                this.c = 1;
            } else if (b == 114) {
                this.c = 2;
            } else if (b == 101 && bArr[10] == 50) {
                this.c = 6;
            } else if (b == 101 && bArr[10] == 52) {
                this.c = 7;
            } else if (b == 97 && bArr[20] == 50) {
                this.c = 3;
            } else if (b == 97 && bArr[20] == 51) {
                this.c = 4;
            } else {
                if (b != 97 || bArr[20] != 53) {
                    throw new JSchException("invalid key type");
                }
                this.c = 5;
            }
        } else {
            this.c = i;
        }
        this.d = bArr;
        this.e = str3;
    }

    public HostKey(String str, byte[] bArr) throws JSchException {
        this(str, 0, bArr);
    }

    public static byte[] a(String str) throws JSchException {
        return Util.k(Util.v(str), 0, str.length());
    }

    private boolean g(String str) {
        String str2 = this.b;
        int length = str2.length();
        int length2 = str.length();
        int i = 0;
        while (i < length) {
            int iIndexOf = str2.indexOf(44, i);
            if (iIndexOf == -1) {
                if (length2 != length - i) {
                    return false;
                }
                return str2.regionMatches(true, i, str, 0, length2);
            }
            String str3 = str;
            if (length2 == iIndexOf - i && str2.regionMatches(true, i, str3, 0, length2)) {
                return true;
            }
            i = iIndexOf + 1;
            str = str3;
        }
        return false;
    }

    public static int i(String str) {
        int i = 0;
        while (true) {
            byte[][] bArr = f;
            if (i >= bArr.length) {
                return -1;
            }
            if (Util.c(bArr[i]).equals(str)) {
                return i + 1;
            }
            i++;
        }
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        byte[] bArr = this.d;
        return Util.c(Util.x(bArr, 0, bArr.length, true));
    }

    public String e() {
        return this.a;
    }

    public String f() {
        int i = this.c;
        return (i == 1 || i == 2 || i == 6 || i == 7 || i == 3 || i == 4 || i == 5) ? Util.c(f[i - 1]) : "UNKNOWN";
    }

    boolean h(String str) {
        return g(str);
    }
}
