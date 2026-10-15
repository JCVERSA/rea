package com.jcraft.jsch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public abstract class KeyPair {
    private static final byte[] m = Util.v("openssh-key-v1\u0000");
    private static final byte[] n = Util.v("\n");
    static byte[][] o = {Util.v("Proc-Type: 4,ENCRYPTED"), Util.v("DEK-Info: DES-EDE3-CBC,")};
    private static byte[] p = Util.v(" ");
    JSch.InstanceLogger c;
    protected Cipher d;
    private KDF e;
    private HASH f;
    private HASH g;
    private byte[] h;
    int a = 0;
    protected String b = "no comment";
    protected boolean i = false;
    protected byte[] j = null;
    private byte[] k = null;
    private byte[] l = null;

    static class ASN1 {
        byte[] a;
        int b;
        int c;

        ASN1(byte[] bArr) throws ASN1Exception {
            this(bArr, 0, bArr.length);
        }

        ASN1(byte[] bArr, int i, int i2) throws ASN1Exception {
            this.a = bArr;
            this.b = i;
            this.c = i2;
            if (i + i2 > bArr.length) {
                throw new ASN1Exception();
            }
        }

        private int c(int[] iArr) {
            int i = iArr[0];
            int i2 = i + 1;
            byte b = this.a[i];
            int i3 = b & 255;
            if ((b & 128) != 0) {
                int i4 = b & 127;
                i3 = 0;
                while (true) {
                    int i5 = i4 - 1;
                    if (i4 <= 0) {
                        break;
                    }
                    i3 = (this.a[i2] & 255) + (i3 << 8);
                    i4 = i5;
                    i2++;
                }
            }
            iArr[0] = i2;
            return i3;
        }

        byte[] a() {
            int[] iArr = {this.b + 1};
            int iC = c(iArr);
            byte[] bArr = new byte[iC];
            System.arraycopy(this.a, iArr[0], bArr, 0, iC);
            return bArr;
        }

        ASN1[] b() throws ASN1Exception {
            byte[] bArr = this.a;
            int i = this.b;
            byte b = bArr[i];
            int[] iArr = {i + 1};
            int iC = c(iArr);
            if (b == 5) {
                return new ASN1[0];
            }
            int i2 = iArr[0];
            ArrayList arrayList = new ArrayList();
            while (iC > 0) {
                int i3 = i2 + 1;
                iArr[0] = i3;
                int iC2 = c(iArr);
                int i4 = iArr[0];
                int i5 = i4 - i3;
                arrayList.add(new ASN1(this.a, i2, i5 + 1 + iC2));
                i2 = i4 + iC2;
                iC = ((iC - 1) - i5) - iC2;
            }
            ASN1[] asn1Arr = new ASN1[arrayList.size()];
            arrayList.toArray(asn1Arr);
            return asn1Arr;
        }

        int d() {
            return this.a[this.b] & 255;
        }

        boolean e() {
            return d() == 3;
        }

        boolean f(int i) {
            if ((i & (-256)) == 0 && (i & 64) == 0) {
                return d() == ((i | 160) & 255);
            }
            throw new IllegalArgumentException();
        }

        boolean g() {
            return d() == 2;
        }

        boolean h() {
            return d() == 5;
        }

        boolean i() {
            return d() == 6;
        }

        boolean j() {
            return d() == 4;
        }

        boolean k() {
            return d() == 48;
        }
    }

    static class ASN1Exception extends Exception {
        ASN1Exception() {
        }
    }

    KeyPair(JSch.InstanceLogger instanceLogger) {
        this.c = instanceLogger;
    }

    private static byte[] A(Buffer buffer, int i) {
        byte[] bArr = buffer.b;
        int i2 = buffer.c;
        byte[] bArr2 = null;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                break;
            }
            int i4 = i2;
            while (bArr.length > i4) {
                int i5 = i4 + 1;
                byte b = bArr[i4];
                if (b == 13 || b == 10) {
                    int i6 = (i5 - i2) - 1;
                    if (bArr2 != null) {
                        if (i6 > 0) {
                            byte[] bArr3 = new byte[bArr2.length + i6];
                            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                            System.arraycopy(bArr, i2, bArr3, bArr2.length, i6);
                            Util.g(bArr2);
                            i4 = i5;
                            bArr2 = bArr3;
                            break;
                        }
                    } else {
                        bArr2 = new byte[i6];
                        System.arraycopy(bArr, i2, bArr2, 0, i6);
                    }
                    i4 = i5;
                    break;
                }
                i4 = i5;
            }
            if (i4 < bArr.length && bArr[i4] == 10) {
                i4++;
            }
            i2 = i4;
            i = i3;
        }
        if (bArr2 != null) {
            buffer.c = i2;
        }
        return bArr2;
    }

    private static KeyPair B(JSch.InstanceLogger instanceLogger, byte[] bArr, String str) throws JSchException {
        Buffer buffer = new Buffer(bArr);
        buffer.E(bArr.length);
        String strC = Util.c(buffer.p());
        if (str == null || str.equals(HttpUrl.FRAGMENT_ENCODE_SET)) {
            str = strC;
        } else if (!str.equals(strC)) {
            throw new JSchException("pubkeyblob type [" + strC + "] does not match expected type [" + str + "]");
        }
        if (str.equals("ssh-rsa")) {
            byte[] bArr2 = new byte[buffer.i()];
            buffer.e(bArr2);
            byte[] bArr3 = new byte[buffer.i()];
            buffer.e(bArr3);
            return new KeyPairRSA(instanceLogger, bArr3, bArr2, null);
        }
        if (str.equals("ssh-dss")) {
            byte[] bArr4 = new byte[buffer.i()];
            buffer.e(bArr4);
            byte[] bArr5 = new byte[buffer.i()];
            buffer.e(bArr5);
            byte[] bArr6 = new byte[buffer.i()];
            buffer.e(bArr6);
            byte[] bArr7 = new byte[buffer.i()];
            buffer.e(bArr7);
            return new KeyPairDSA(instanceLogger, bArr4, bArr5, bArr6, bArr7, null);
        }
        if (str.equals("ecdsa-sha2-nistp256") || str.equals("ecdsa-sha2-nistp384") || str.equals("ecdsa-sha2-nistp521")) {
            byte[] bArrP = buffer.p();
            int i = buffer.i();
            buffer.c();
            int i2 = (i - 1) / 2;
            byte[] bArr8 = new byte[i2];
            byte[] bArr9 = new byte[i2];
            buffer.e(bArr8);
            buffer.e(bArr9);
            return new KeyPairECDSA(instanceLogger, bArrP, bArr8, bArr9, null);
        }
        if (str.equals("ssh-ed25519") || str.equals("ssh-ed448")) {
            byte[] bArr10 = new byte[buffer.i()];
            buffer.e(bArr10);
            return str.equals("ssh-ed25519") ? new KeyPairEd25519(instanceLogger, bArr10, null) : new KeyPairEd448(instanceLogger, bArr10, null);
        }
        throw new JSchException("key type " + str + " is not supported");
    }

    private static byte a(byte b) {
        return (byte) ((48 > b || b > 57) ? b - 87 : b - 48);
    }

    private byte[] e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            byte[] bArrJ = j(bArr2, bArr3);
            this.d.l(1, bArrJ, bArr3);
            Util.g(bArrJ);
            byte[] bArr4 = new byte[bArr.length];
            this.d.g(bArr, 0, bArr.length, bArr4, 0);
            return bArr4;
        } catch (Exception e) {
            if (!this.c.a().isEnabled(3)) {
                return null;
            }
            this.c.a().b(3, "failed to decrypt key", e);
            return null;
        }
    }

    private Cipher h() {
        try {
            this.d = (Cipher) Class.forName(JSch.f("3des-cbc")).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to create cipher", e);
            }
        }
        return this.d;
    }

    private HASH i() {
        try {
            HASH hash = (HASH) Class.forName(JSch.f("md5")).asSubclass(HASH.class).getDeclaredConstructor(null).newInstance(null);
            this.g = hash;
            hash.a();
        } catch (Exception e) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to create hash", e);
            }
        }
        return this.g;
    }

    private static boolean s(byte[] bArr, int i, int i2) {
        int i3 = i + 24;
        return i3 < i2 && "OPENSSH PRIVATE KEY-----".equals(Util.c(Arrays.copyOfRange(bArr, i, i3)));
    }

    static KeyPair t(JSch.InstanceLogger instanceLogger, String str, String str2) throws JSchException {
        String str3;
        byte[] bArrL;
        try {
            byte[] bArrL2 = Util.l(str);
            if (str2 == null) {
                str3 = str + ".pub";
            } else {
                str3 = str2;
            }
            try {
                bArrL = Util.l(str3);
            } catch (IOException e) {
                if (str2 != null) {
                    throw new JSchException(e.toString(), e);
                }
                bArrL = null;
            }
            try {
                return u(instanceLogger, bArrL2, bArrL);
            } finally {
                Util.g(bArrL2);
            }
        } catch (IOException e2) {
            throw new JSchException(e2.toString(), e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v47, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v52 */
    /* JADX WARN: Type inference failed for: r6v53 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v89 */
    /* JADX WARN: Type inference failed for: r6v90 */
    /* JADX WARN: Type inference failed for: r6v91 */
    /* JADX WARN: Type inference failed for: r6v92 */
    /* JADX WARN: Type inference failed for: r6v93 */
    /* JADX WARN: Type inference failed for: r6v94 */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:266:0x03d7
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:590)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:82)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    static com.jcraft.jsch.KeyPair u(com.jcraft.jsch.JSch.InstanceLogger r27, byte[] r28, byte[] r29) throws com.jcraft.jsch.JSchException {
        /*
            Method dump skipped, instruction units count: 1875
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jcraft.jsch.KeyPair.u(com.jcraft.jsch.JSch$InstanceLogger, byte[], byte[]):com.jcraft.jsch.KeyPair");
    }

    public static KeyPair v(JSch jSch, byte[] bArr, byte[] bArr2) throws JSchException {
        return u(jSch.a, bArr, bArr2);
    }

    static KeyPair w(JSch.InstanceLogger instanceLogger, byte[] bArr) throws Exception {
        if (bArr == null) {
            throw new JSchException("invalid privatekey");
        }
        Buffer buffer = new Buffer(bArr);
        byte[] bArr2 = m;
        byte[] bArr3 = new byte[bArr2.length];
        buffer.e(bArr3);
        if (!Util.b(bArr2, bArr3)) {
            throw new JSchException("Invalid openssh v1 format.");
        }
        String strC = Util.c(buffer.p());
        String strC2 = Util.c(buffer.p());
        byte[] bArrP = buffer.p();
        if (buffer.i() != 1) {
            throw new JSchException("We don't support having more than 1 key in the file (yet).");
        }
        byte[] bArrP2 = buffer.p();
        KeyPair keyPairB = B(instanceLogger, bArrP2, null);
        keyPairB.i = true ^ "none".equals(strC);
        keyPairB.l = bArrP2;
        keyPairB.a = 4;
        keyPairB.b = HttpUrl.FRAGMENT_ENCODE_SET;
        byte[] bArrP3 = buffer.p();
        keyPairB.j = bArrP3;
        try {
            if (!keyPairB.i) {
                if (!keyPairB.y(bArrP3)) {
                    throw new JSchException("invalid privatekey");
                }
                Util.g(keyPairB.j);
                return keyPairB;
            }
            if (!Session.f(JSch.f(strC))) {
                throw new JSchException("cipher " + strC + " is not available");
            }
            try {
                Cipher cipher = (Cipher) Class.forName(JSch.f(strC)).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
                keyPairB.d = cipher;
                keyPairB.k = new byte[cipher.m()];
                try {
                    Buffer buffer2 = new Buffer(bArrP);
                    byte[] bArrP4 = buffer2.p();
                    int i = buffer2.i();
                    BCrypt bCrypt = (BCrypt) Class.forName(JSch.f(strC2)).asSubclass(BCrypt.class).getDeclaredConstructor(null).newInstance(null);
                    bCrypt.a(bArrP4, i);
                    keyPairB.e = bCrypt;
                    return keyPairB;
                } catch (Exception | NoClassDefFoundError e) {
                    throw new JSchException("kdf " + strC2 + " is not available", e);
                }
            } catch (Exception e2) {
                e = e2;
                throw new JSchException("cipher " + strC + " is not available", e);
            } catch (NoClassDefFoundError e3) {
                e = e3;
                throw new JSchException("cipher " + strC + " is not available", e);
            }
        } catch (Exception e4) {
            Util.g(keyPairB.j);
            throw e4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:59:0x0142  */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x00e1: MOVE (r5 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:29:0x00e1 */
    static KeyPair x(JSch.InstanceLogger instanceLogger, byte[] bArr) throws Throwable {
        int i;
        byte[] bArr2;
        byte b;
        int i2;
        Buffer buffer = new Buffer(bArr);
        HashMap map = new HashMap();
        while (z(buffer, map)) {
        }
        String str = (String) map.get("PuTTY-User-Key-File-2");
        byte[] bArr3 = null;
        if (str == null) {
            str = (String) map.get("PuTTY-User-Key-File-3");
            if (str == null) {
                return null;
            }
            i = 5;
        } else {
            i = 2;
        }
        try {
            try {
                byte[] bArrA = A(buffer, Integer.parseInt((String) map.get("Public-Lines")));
                while (z(buffer, map)) {
                }
                byte[] bArrA2 = A(buffer, Integer.parseInt((String) map.get("Private-Lines")));
                do {
                    try {
                    } catch (Exception e) {
                        e = e;
                    }
                } while (z(buffer, map));
                byte[] bArrK = Util.k(bArrA2, 0, bArrA2.length);
                try {
                    byte[] bArrK2 = Util.k(bArrA, 0, bArrA.length);
                    KeyPair keyPairB = B(instanceLogger, bArrK2, str);
                    keyPairB.i = !((String) map.get("Encryption")).equals("none");
                    keyPairB.l = bArrK2;
                    keyPairB.a = i;
                    keyPairB.b = (String) map.get("Comment");
                    if (!keyPairB.i) {
                        keyPairB.j = bArrK;
                        if (!keyPairB.y(bArrK)) {
                            throw new JSchException("invalid privatekey");
                        }
                        Util.g(bArrK);
                    } else {
                        if (!Session.f(JSch.f("aes256-cbc"))) {
                            throw new JSchException("The cipher 'aes256-cbc' is required, but it is not available.");
                        }
                        try {
                            Cipher cipher = (Cipher) Class.forName(JSch.f("aes256-cbc")).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
                            keyPairB.d = cipher;
                            keyPairB.k = new byte[cipher.m()];
                            if (i == 2) {
                                try {
                                    HASH hash = (HASH) Class.forName(JSch.f("sha-1")).asSubclass(HASH.class).getDeclaredConstructor(null).newInstance(null);
                                    hash.a();
                                    keyPairB.f = hash;
                                } catch (Exception | NoClassDefFoundError e2) {
                                    throw new JSchException("'sha-1' is required, but it is not available.", e2);
                                }
                            } else {
                                String str2 = (String) map.get("Key-Derivation");
                                String str3 = (String) map.get("Argon2-Salt");
                                if (str2 == null || str3 == null || str3.length() % 2 != 0) {
                                    throw new JSchException("Invalid argon2 params.");
                                }
                                int iHashCode = str2.hashCode();
                                if (iHashCode != -1530598888) {
                                    if (iHashCode != 920457159) {
                                        if (iHashCode == 920457164 && str2.equals("Argon2i")) {
                                            b = 1;
                                        } else {
                                            b = -1;
                                        }
                                    } else if (str2.equals("Argon2d")) {
                                        b = 0;
                                    } else {
                                        b = -1;
                                    }
                                } else if (str2.equals("Argon2id")) {
                                    b = 2;
                                } else {
                                    b = -1;
                                }
                                if (b == 0) {
                                    i2 = 0;
                                } else if (b == 1) {
                                    i2 = 1;
                                } else {
                                    if (b != 2) {
                                        throw new JSchException("Invalid argon2 params.");
                                    }
                                    i2 = 2;
                                }
                                try {
                                    int i3 = Integer.parseInt((String) map.get("Argon2-Memory"));
                                    int i4 = Integer.parseInt((String) map.get("Argon2-Passes"));
                                    int i5 = Integer.parseInt((String) map.get("Argon2-Parallelism"));
                                    int length = str3.length() / 2;
                                    byte[] bArr4 = new byte[length];
                                    for (int i6 = 0; i6 < length; i6++) {
                                        int i7 = i6 * 2;
                                        bArr4[i6] = (byte) Integer.parseInt(str3.substring(i7, i7 + 2), 16);
                                    }
                                    Argon2 argon2 = (Argon2) Class.forName(JSch.f("argon2")).asSubclass(Argon2.class).getDeclaredConstructor(null).newInstance(null);
                                    argon2.b(bArr4, i4, i2, new byte[0], new byte[0], i3, i5, 19);
                                    keyPairB.e = argon2;
                                } catch (NumberFormatException e3) {
                                    throw new JSchException("Invalid argon2 params.", e3);
                                } catch (Exception e4) {
                                    e = e4;
                                    throw new JSchException("'argon2' is required, but it is not available.", e);
                                } catch (NoClassDefFoundError e5) {
                                    e = e5;
                                    throw new JSchException("'argon2' is required, but it is not available.", e);
                                }
                            }
                            keyPairB.j = bArrK;
                        } catch (Exception e6) {
                            e = e6;
                            throw new JSchException("The cipher 'aes256-cbc' is required, but it is not available.", e);
                        } catch (NoClassDefFoundError e7) {
                            e = e7;
                            throw new JSchException("The cipher 'aes256-cbc' is required, but it is not available.", e);
                        }
                    }
                    Util.g(bArrA2);
                    return keyPairB;
                } catch (Exception e8) {
                    e = e8;
                    bArr3 = bArrK;
                    Util.g(bArr3);
                    throw e;
                }
            } catch (Throwable th) {
                th = th;
                bArr3 = bArr2;
                Util.g(bArr3);
                throw th;
            }
        } catch (Exception e9) {
            e = e9;
        } catch (Throwable th2) {
            th = th2;
            Util.g(bArr3);
            throw th;
        }
    }

    private static boolean z(Buffer buffer, Map<String, String> map) {
        String strD;
        int i;
        String strD2;
        byte[] bArr = buffer.b;
        int i2 = buffer.c;
        int i3 = i2;
        while (true) {
            strD = null;
            if (i3 < bArr.length) {
                byte b = bArr[i3];
                if (b == 13 || b == 10) {
                    int i4 = i3 + 1;
                    if (i4 < bArr.length) {
                        byte b2 = bArr[i4];
                    }
                } else {
                    if (b == 58) {
                        strD2 = Util.d(bArr, i2, i3 - i2);
                        int i5 = i3 + 1;
                        if (i5 < bArr.length && bArr[i5] == 32) {
                            i = i3 + 2;
                            break;
                        }
                        i = i5;
                        break;
                    }
                    i3++;
                }
            }
            i = i2;
            strD2 = null;
            break;
        }
        if (strD2 == null) {
            return false;
        }
        for (int i6 = i; i6 < bArr.length; i6++) {
            byte b3 = bArr[i6];
            if (b3 == 13 || b3 == 10) {
                strD = Util.d(bArr, i, i6 - i);
                i = i6 + 1;
                if (i >= bArr.length || bArr[i] != 10) {
                    break;
                    break;
                }
                i = i6 + 2;
                break;
            }
        }
        if (strD != null) {
            map.put(strD2, strD);
            buffer.c = i;
        }
        return strD != null;
    }

    int C(byte[] bArr, byte b, int i, byte[] bArr2) {
        bArr[i] = b;
        int iE = E(bArr, i + 1, bArr2.length);
        System.arraycopy(bArr2, 0, bArr, iE, bArr2.length);
        return iE + bArr2.length;
    }

    int D(byte[] bArr, int i, byte[] bArr2) {
        bArr[i] = 2;
        int iE = E(bArr, i + 1, bArr2.length);
        System.arraycopy(bArr2, 0, bArr, iE, bArr2.length);
        return iE + bArr2.length;
    }

    int E(byte[] bArr, int i, int i2) {
        int iC = c(i2) - 1;
        if (iC == 0) {
            int i3 = i + 1;
            bArr[i] = (byte) i2;
            return i3;
        }
        int i4 = i + 1;
        bArr[i] = (byte) (iC | 128);
        int i5 = i4 + iC;
        while (iC > 0) {
            bArr[(i4 + iC) - 1] = (byte) (i2 & 255);
            i2 >>>= 8;
            iC--;
        }
        return i5;
    }

    int F(byte[] bArr, int i, byte[] bArr2) {
        bArr[i] = 4;
        int iE = E(bArr, i + 1, bArr2.length);
        System.arraycopy(bArr2, 0, bArr, iE, bArr2.length);
        return iE + bArr2.length;
    }

    int G(byte[] bArr, int i, int i2) {
        bArr[i] = 48;
        return E(bArr, i + 1, i2);
    }

    void b(KeyPair keyPair) {
        this.l = keyPair.l;
        this.a = keyPair.a;
        this.b = keyPair.b;
        this.d = keyPair.d;
    }

    int c(int i) {
        int i2 = 1;
        if (i <= 127) {
            return 1;
        }
        while (i > 0) {
            i >>>= 8;
            i2++;
        }
        return i2;
    }

    public boolean d(byte[] bArr) {
        boolean z = this.i;
        if (!z) {
            return true;
        }
        if (bArr == null) {
            return !z;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        byte[] bArrE = null;
        try {
            bArrE = e(this.j, bArr2, this.k);
            if (y(bArrE)) {
                this.i = false;
                Util.g(this.j);
            }
            return !this.i;
        } finally {
            Util.g(bArr2);
            Util.g(bArrE);
        }
    }

    public void f() {
        Util.g(this.h);
    }

    public void finalize() {
        f();
    }

    public abstract byte[] g() throws JSchException;

    synchronized byte[] j(byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        try {
            if (this.d == null) {
                this.d = h();
            }
            if (this.g == null) {
                this.g = i();
            }
            int iD = this.d.d();
            bArr3 = new byte[iD];
            int iD2 = this.g.d();
            int i = ((iD / iD2) * iD2) + (iD % iD2 == 0 ? 0 : iD2);
            byte[] bArr4 = new byte[i];
            try {
                int i2 = this.a;
                byte[] bArrC = null;
                if (i2 == 0) {
                    int length = 0;
                    while (length + iD2 <= i) {
                        if (bArrC != null) {
                            this.g.b(bArrC, 0, bArrC.length);
                        }
                        this.g.b(bArr, 0, bArr.length);
                        HASH hash = this.g;
                        int length2 = 8;
                        if (bArr2.length <= 8) {
                            length2 = bArr2.length;
                        }
                        hash.b(bArr2, 0, length2);
                        bArrC = this.g.c();
                        System.arraycopy(bArrC, 0, bArr4, length, bArrC.length);
                        length += bArrC.length;
                    }
                    System.arraycopy(bArr4, 0, bArr3, 0, iD);
                } else if (i2 == 4) {
                    byte[] bArrC2 = this.e.c(bArr, this.d.d() + this.d.m());
                    System.arraycopy(bArrC2, 0, bArr3, 0, iD);
                    System.arraycopy(bArrC2, iD, bArr2, 0, bArr2.length);
                    Util.g(bArrC2);
                } else if (i2 == 1) {
                    int length3 = 0;
                    while (length3 + iD2 <= i) {
                        if (bArrC != null) {
                            this.g.b(bArrC, 0, bArrC.length);
                        }
                        this.g.b(bArr, 0, bArr.length);
                        bArrC = this.g.c();
                        System.arraycopy(bArrC, 0, bArr4, length3, bArrC.length);
                        length3 += bArrC.length;
                    }
                    System.arraycopy(bArr4, 0, bArr3, 0, iD);
                } else if (i2 == 2) {
                    byte[] bArr5 = new byte[4];
                    this.f.b(bArr5, 0, 4);
                    this.f.b(bArr, 0, bArr.length);
                    byte[] bArrC3 = this.f.c();
                    System.arraycopy(bArrC3, 0, bArr3, 0, bArrC3.length);
                    Util.g(bArrC3);
                    bArr5[3] = 1;
                    this.f.b(bArr5, 0, 4);
                    this.f.b(bArr, 0, bArr.length);
                    byte[] bArrC4 = this.f.c();
                    System.arraycopy(bArrC4, 0, bArr3, bArrC4.length, iD - bArrC4.length);
                    Util.g(bArrC4);
                } else if (i2 == 5) {
                    byte[] bArrC5 = this.e.c(bArr, this.d.d() + this.d.m() + 32);
                    System.arraycopy(bArrC5, 0, bArr3, 0, iD);
                    System.arraycopy(bArrC5, iD, bArr2, 0, bArr2.length);
                    Util.g(bArrC5);
                }
            } catch (Exception e) {
                if (this.c.a().isEnabled(3)) {
                    this.c.a().b(3, "failed to generate key from passphrase", e);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return bArr3;
    }

    public abstract int k();

    abstract byte[] l();

    public String m() {
        return Util.c(l());
    }

    abstract byte[] n();

    public byte[] o() {
        return this.l;
    }

    public abstract byte[] p(byte[] bArr);

    public abstract byte[] q(byte[] bArr, String str);

    public boolean r() {
        return this.i;
    }

    abstract boolean y(byte[] bArr);
}
