package com.jcraft.jsch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class Util {
    private static final byte[] a = v("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");
    private static String[] b = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    static final byte[] c = v(HttpUrl.FRAGMENT_ENCODE_SET);

    Util() {
    }

    static byte[] A(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            if (bArr[i] == 92) {
                int i2 = i + 1;
                if (i2 == length) {
                    break;
                }
                System.arraycopy(bArr, i2, bArr, i, bArr.length - i2);
                length--;
                i = i2;
            } else {
                i++;
            }
        }
        if (length == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private static byte B(byte b2) {
        if (b2 == 61) {
            return (byte) 0;
        }
        int i = 0;
        while (true) {
            byte[] bArr = a;
            if (i >= bArr.length) {
                return (byte) 0;
            }
            if (b2 == bArr[i]) {
                return (byte) i;
            }
            i++;
        }
    }

    static boolean a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean b(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    static String c(byte[] bArr) {
        return e(bArr, 0, bArr.length, StandardCharsets.UTF_8);
    }

    static String d(byte[] bArr, int i, int i2) {
        return e(bArr, i, i2, StandardCharsets.UTF_8);
    }

    static String e(byte[] bArr, int i, int i2, Charset charset) {
        return new String(bArr, i, i2, charset);
    }

    static String f(byte[] bArr, Charset charset) {
        return e(bArr, 0, bArr.length, charset);
    }

    static void g(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    static String h(String str) {
        try {
            return str.startsWith("~") ? str.replace("~", System.getProperty("user.home")) : str;
        } catch (SecurityException unused) {
            return str;
        }
    }

    static Socket i(String str, int i, int i2) throws JSchException {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(str, i), i2);
            return socket;
        } catch (Exception e) {
            try {
                socket.close();
            } catch (Exception unused) {
            }
            throw new JSchException(e instanceof SocketTimeoutException ? "timeout: socket is not established" : e.toString(), e);
        }
    }

    static String j(String str, String[] strArr) {
        String[] strArrU = u(str, ",");
        String str2 = null;
        for (int i = 0; i < strArrU.length; i++) {
            int i2 = 0;
            while (true) {
                if (i2 >= strArr.length) {
                    if (str2 != null) {
                        str2 = str2 + "," + strArrU[i];
                        break;
                    }
                    str2 = strArrU[i];
                    break;
                }
                if (strArrU[i].equals(strArr[i2])) {
                    break;
                }
                i2++;
            }
        }
        return str2;
    }

    static byte[] k(byte[] bArr, int i, int i2) throws JSchException {
        try {
            byte[] bArr2 = new byte[i2];
            int i3 = 0;
            for (int i4 = i; i4 < i + i2; i4 += 4) {
                int i5 = i4 + 1;
                bArr2[i3] = (byte) ((B(bArr[i4]) << 2) | ((B(bArr[i5]) & 48) >>> 4));
                int i6 = i4 + 2;
                if (bArr[i6] == 61) {
                    i3++;
                    break;
                }
                bArr2[i3 + 1] = (byte) (((B(bArr[i5]) & 15) << 4) | ((B(bArr[i6]) & 60) >>> 2));
                int i7 = i4 + 3;
                if (bArr[i7] == 61) {
                    i3 += 2;
                    break;
                }
                bArr2[i3 + 2] = (byte) (((B(bArr[i6]) & 3) << 6) | (B(bArr[i7]) & 63));
                i3 += 3;
            }
            byte[] bArr3 = new byte[i3];
            System.arraycopy(bArr2, 0, bArr3, 0, i3);
            return bArr3;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JSchException("fromBase64: invalid base64 data", e);
        }
    }

    static byte[] l(String str) throws IOException {
        String strH = h(str);
        File file = new File(strH);
        FileInputStream fileInputStream = new FileInputStream(strH);
        try {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            int i = 0;
            while (true) {
                int i2 = fileInputStream.read(bArr, i, length - i);
                if (i2 <= 0) {
                    fileInputStream.close();
                    return bArr;
                }
                i += i2;
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    static String m(HASH hash, byte[] bArr, boolean z, boolean z2) {
        try {
            hash.a();
            int i = 0;
            hash.b(bArr, 0, bArr.length);
            byte[] bArrC = hash.c();
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append(hash.i());
                sb.append(":");
            }
            if (z2 || hash.i().equals("MD5")) {
                while (i < bArrC.length) {
                    byte b2 = bArrC[i];
                    sb.append(b[((b2 & 255) >>> 4) & 15]);
                    sb.append(b[b2 & 15]);
                    i++;
                    if (i < bArrC.length) {
                        sb.append(":");
                    }
                }
            } else {
                byte[] bArrX = x(bArrC, 0, bArrC.length, false);
                sb.append(d(bArrX, 0, bArrX.length));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "???";
        }
    }

    static String n(String str) {
        try {
            return System.getenv(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    static String o(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    static String p(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (SecurityException unused) {
            return str2;
        }
    }

    private static boolean q(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte b2;
        int iT;
        int length = bArr.length;
        if (length == 0) {
            return false;
        }
        int length2 = bArr2.length;
        while (i < length && i2 < length2) {
            byte b3 = bArr[i];
            if (b3 == 92) {
                int i3 = i + 1;
                if (i3 == length || (b2 = bArr[i3]) != bArr2[i2]) {
                    return false;
                }
                i = i3 + t(b2);
                iT = t(bArr2[i2]);
            } else {
                if (b3 == 42) {
                    while (i < length && bArr[i] == 42) {
                        i++;
                    }
                    if (length == i) {
                        return true;
                    }
                    byte b4 = bArr[i];
                    if (b4 == 63) {
                        while (i2 < length2) {
                            if (q(bArr, i, bArr2, i2)) {
                                return true;
                            }
                            i2 += t(bArr2[i2]);
                        }
                        return false;
                    }
                    if (b4 != 92) {
                        while (i2 < length2) {
                            if (b4 == bArr2[i2] && q(bArr, i, bArr2, i2)) {
                                return true;
                            }
                            i2 += t(bArr2[i2]);
                        }
                        return false;
                    }
                    int i4 = i + 1;
                    if (i4 == length) {
                        return false;
                    }
                    byte b5 = bArr[i4];
                    while (i2 < length2) {
                        if (b5 == bArr2[i2] && q(bArr, t(b5) + i4, bArr2, t(bArr2[i2]) + i2)) {
                            return true;
                        }
                        i2 += t(bArr2[i2]);
                    }
                    return false;
                }
                if (b3 == 63) {
                    i++;
                    iT = t(bArr2[i2]);
                } else {
                    if (b3 != bArr2[i2]) {
                        return false;
                    }
                    i += t(b3);
                    i2 += t(bArr2[i2]);
                    if (i2 < length2) {
                        continue;
                    } else {
                        if (i >= length) {
                            return true;
                        }
                        if (bArr[i] == 42) {
                            break;
                        }
                    }
                }
            }
            i2 += iT;
        }
        if (i == length && i2 == length2) {
            return true;
        }
        if (i2 < length2 || bArr[i] != 42) {
            return false;
        }
        while (i < length) {
            int i5 = i + 1;
            if (bArr[i] != 42) {
                return false;
            }
            i = i5;
        }
        return true;
    }

    static boolean r(byte[] bArr, byte[] bArr2) {
        return s(bArr, 0, bArr2, 0);
    }

    private static boolean s(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr2.length <= 0 || bArr2[0] != 46) {
            return q(bArr, i, bArr2, i2);
        }
        if (bArr.length <= 0 || bArr[0] != 46) {
            return false;
        }
        if (bArr.length == 2 && bArr[1] == 42) {
            return true;
        }
        return q(bArr, i + 1, bArr2, i2 + 1);
    }

    private static int t(byte b2) {
        if (((byte) (b2 & 128)) == 0) {
            return 1;
        }
        if (((byte) (b2 & 224)) == -64) {
            return 2;
        }
        return ((byte) (b2 & 240)) == -32 ? 3 : 1;
    }

    static String[] u(String str, String str2) {
        if (str == null) {
            return null;
        }
        byte[] bArrV = v(str);
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(str2, i);
            if (iIndexOf < 0) {
                break;
            }
            vector.addElement(d(bArrV, i, iIndexOf - i));
            i = iIndexOf + 1;
        }
        vector.addElement(d(bArrV, i, bArrV.length - i));
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = (String) vector.elementAt(i2);
        }
        return strArr;
    }

    static byte[] v(String str) {
        return w(str, StandardCharsets.UTF_8);
    }

    static byte[] w(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0080 A[PHI: r12
      0x0080: PHI (r12v13 int) = (r12v12 int), (r12v18 int) binds: [B:14:0x00b2, B:9:0x0075] A[DONT_GENERATE, DONT_INLINE]] */
    static byte[] x(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        byte[] bArr2 = new byte[i2 * 2];
        int i4 = ((i2 / 3) * 3) + i;
        int i5 = i;
        int i6 = 0;
        while (i5 < i4) {
            int i7 = (bArr[i5] >>> 2) & 63;
            byte[] bArr3 = a;
            bArr2[i6] = bArr3[i7];
            int i8 = i5 + 1;
            bArr2[i6 + 1] = bArr3[((bArr[i5] & 3) << 4) | ((bArr[i8] >>> 4) & 15)];
            int i9 = i5 + 2;
            int i10 = i6 + 3;
            bArr2[i6 + 2] = bArr3[((bArr[i8] & 15) << 2) | ((bArr[i9] >>> 6) & 3)];
            i6 += 4;
            bArr2[i10] = bArr3[bArr[i9] & 63];
            i5 += 3;
        }
        int i11 = (i + i2) - i4;
        if (i11 == 1) {
            int i12 = (bArr[i5] >>> 2) & 63;
            byte[] bArr4 = a;
            bArr2[i6] = bArr4[i12];
            i3 = i6 + 2;
            bArr2[i6 + 1] = bArr4[((bArr[i5] & 3) << 4) & 63];
            if (z) {
                int i13 = i6 + 3;
                bArr2[i3] = 61;
                i6 += 4;
                bArr2[i13] = 61;
            } else {
                i6 = i3;
            }
        } else if (i11 == 2) {
            int i14 = (bArr[i5] >>> 2) & 63;
            byte[] bArr5 = a;
            bArr2[i6] = bArr5[i14];
            int i15 = (bArr[i5] & 3) << 4;
            int i16 = i5 + 1;
            bArr2[i6 + 1] = bArr5[i15 | ((bArr[i16] >>> 4) & 15)];
            i3 = i6 + 3;
            bArr2[i6 + 2] = bArr5[((bArr[i16] & 15) << 2) & 63];
            if (z) {
                i6 += 4;
                bArr2[i3] = 61;
            } else {
                i6 = i3;
            }
        }
        byte[] bArr6 = new byte[i6];
        System.arraycopy(bArr2, 0, bArr6, 0, i6);
        return bArr6;
    }

    static String y(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < bArr.length) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("0x");
            sb2.append(hexString.length() == 1 ? "0" : HttpUrl.FRAGMENT_ENCODE_SET);
            sb2.append(hexString);
            sb.append(sb2.toString());
            i++;
            if (i < bArr.length) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    static String z(String str) {
        byte[] bArrV = v(str);
        byte[] bArrA = A(bArrV);
        return bArrV.length == bArrA.length ? str : c(bArrA);
    }
}
