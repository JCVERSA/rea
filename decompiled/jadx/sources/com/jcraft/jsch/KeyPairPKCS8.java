package com.jcraft.jsch;

import java.math.BigInteger;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
class KeyPairPKCS8 extends KeyPair {
    private KeyPair q;
    private static final byte[] r = {42, -122, 72, -122, -9, 13, 1, 1, 1};
    private static final byte[] s = {42, -122, 72, -50, 56, 4, 1};
    private static final byte[] t = {42, -122, 72, -50, 61, 2, 1};
    private static final byte[] u = {43, 101, 112};
    private static final byte[] v = {43, 101, 113};
    private static final byte[] w = {42, -122, 72, -50, 61, 3, 1, 7};
    private static final byte[] x = {43, -127, 4, 0, 34};
    private static final byte[] y = {43, -127, 4, 0, 35};
    private static final byte[] z = {42, -122, 72, -122, -9, 13, 1, 5, 13};
    private static final byte[] A = {42, -122, 72, -122, -9, 13, 1, 5, 12};
    private static final byte[] B = {43, 6, 1, 4, 1, -38, 71, 4, 11};
    private static final byte[] C = {42, -122, 72, -122, -9, 13, 2, 7};
    private static final byte[] D = {42, -122, 72, -122, -9, 13, 2, 8};
    private static final byte[] E = {42, -122, 72, -122, -9, 13, 2, 9};
    private static final byte[] F = {42, -122, 72, -122, -9, 13, 2, 10};
    private static final byte[] G = {42, -122, 72, -122, -9, 13, 2, 11};
    private static final byte[] H = {42, -122, 72, -122, -9, 13, 2, 12};
    private static final byte[] I = {42, -122, 72, -122, -9, 13, 2, 13};
    private static final byte[] J = {96, -122, 72, 1, 101, 3, 4, 1, 2};
    private static final byte[] K = {96, -122, 72, 1, 101, 3, 4, 1, 22};
    private static final byte[] L = {96, -122, 72, 1, 101, 3, 4, 1, 42};
    private static final byte[] M = {43, 14, 3, 2, 7};
    private static final byte[] N = {42, -122, 72, -122, -9, 13, 3, 7};
    private static final byte[] O = {42, -122, 72, -122, -9, 13, 3, 2};
    private static final byte[] P = {42, -122, 72, -122, -9, 13, 3, 9};
    private static final byte[] Q = {42, -122, 72, -122, -9, 13, 1, 5, 1};
    private static final byte[] R = {42, -122, 72, -122, -9, 13, 1, 5, 4};
    private static final byte[] S = {42, -122, 72, -122, -9, 13, 1, 5, 3};
    private static final byte[] T = {42, -122, 72, -122, -9, 13, 1, 5, 6};
    private static final byte[] U = {42, -122, 72, -122, -9, 13, 1, 5, 10};
    private static final byte[] V = {42, -122, 72, -122, -9, 13, 1, 5, 11};
    private static final byte[] W = Util.v("-----BEGIN DSA PRIVATE KEY-----");
    private static final byte[] X = Util.v("-----END DSA PRIVATE KEY-----");

    KeyPairPKCS8(JSch.InstanceLogger instanceLogger) {
        super(instanceLogger);
        this.q = null;
    }

    static Cipher H(byte[] bArr, KeyPair.ASN1 asn1, byte[][] bArr2) throws Exception {
        String str;
        if (Util.a(bArr, J)) {
            str = "aes128-cbc";
        } else if (Util.a(bArr, K)) {
            str = "aes192-cbc";
        } else if (Util.a(bArr, L)) {
            str = "aes256-cbc";
        } else {
            if (Util.a(bArr, M)) {
                throw new JSchException("unsupported cipher function: des-cbc");
            }
            if (Util.a(bArr, N)) {
                throw new JSchException("unsupported cipher function: 3des-cbc");
            }
            if (Util.a(bArr, O)) {
                throw new JSchException("unsupported cipher function: rc2-cbc");
            }
            if (Util.a(bArr, P)) {
                throw new JSchException("unsupported cipher function: rc5-cbc");
            }
            str = null;
        }
        if (str == null) {
            throw new JSchException("unsupported cipher function oid: " + Util.y(bArr));
        }
        if (!asn1.j()) {
            throw new KeyPair.ASN1Exception();
        }
        bArr2[0] = asn1.a();
        try {
            return (Cipher) Class.forName(JSch.f(str)).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e) {
            throw new JSchException(str + " is not supported", e);
        }
    }

    static PBKDF2 I(String str) throws JSchException {
        try {
            return (PBKDF2) Class.forName(JSch.f(str)).asSubclass(PBKDF2.class).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e) {
            throw new JSchException(str + " is not supported", e);
        }
    }

    static String J(byte[] bArr) throws JSchException {
        String str;
        if (bArr == null || Util.a(bArr, C)) {
            str = "pbkdf2-hmac-sha1";
        } else if (Util.a(bArr, D)) {
            str = "pbkdf2-hmac-sha224";
        } else if (Util.a(bArr, E)) {
            str = "pbkdf2-hmac-sha256";
        } else if (Util.a(bArr, F)) {
            str = "pbkdf2-hmac-sha384";
        } else if (Util.a(bArr, G)) {
            str = "pbkdf2-hmac-sha512";
        } else {
            if (Util.a(bArr, H)) {
                throw new JSchException("unsupported pbkdf2 function: pbkdf2-hmac-sha512-224");
            }
            if (Util.a(bArr, I)) {
                throw new JSchException("unsupported pbkdf2 function: pbkdf2-hmac-sha512-256");
            }
            str = null;
        }
        if (str != null) {
            return str;
        }
        throw new JSchException("unsupported pbkdf2 function oid: " + Util.y(bArr));
    }

    static SCrypt K() throws JSchException {
        try {
            return (SCrypt) Class.forName(JSch.f("scrypt")).asSubclass(SCrypt.class).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e) {
            throw new JSchException("scrypt is not supported", e);
        }
    }

    static int L(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(bArr);
        if (bigInteger.bitLength() <= 31) {
            return bigInteger.intValue();
        }
        throw new ArithmeticException("BigInteger out of int range");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v23 */
    @Override // com.jcraft.jsch.KeyPair
    public boolean d(byte[] bArr) throws Throwable {
        Throwable th;
        byte[] bArr2;
        Exception exc;
        byte[] bArr3;
        KeyPair.ASN1Exception aSN1Exception;
        byte[] bArr4;
        byte[] bArr5;
        String str;
        String strJ;
        KDF kdf;
        KeyPair.ASN1 asn1;
        byte[] bArrA;
        byte[] bArr6 = 1;
        if (!r()) {
            return true;
        }
        if (bArr == null) {
            return !r();
        }
        byte[] bArr7 = null;
        try {
            try {
                KeyPair.ASN1 asn2 = new KeyPair.ASN1(this.j);
                if (!asn2.k()) {
                    throw new KeyPair.ASN1Exception();
                }
                KeyPair.ASN1[] asn1ArrB = asn2.b();
                if (asn1ArrB.length != 2) {
                    throw new KeyPair.ASN1Exception();
                }
                if (!asn1ArrB[0].k()) {
                    throw new KeyPair.ASN1Exception();
                }
                if (!asn1ArrB[1].j()) {
                    throw new KeyPair.ASN1Exception();
                }
                byte[] bArrA2 = asn1ArrB[1].a();
                try {
                    KeyPair.ASN1[] asn1ArrB2 = asn1ArrB[0].b();
                    if (asn1ArrB2.length != 2) {
                        throw new KeyPair.ASN1Exception();
                    }
                    if (!asn1ArrB2[0].i()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    if (!asn1ArrB2[1].k()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    byte[] bArrA3 = asn1ArrB2[0].a();
                    KeyPair.ASN1 asn3 = asn1ArrB2[1];
                    if (!Util.a(bArrA3, z)) {
                        if (Util.a(bArrA3, Q)) {
                            str = "pbeWithMD2AndDES-CBC unsupported";
                        } else if (Util.a(bArrA3, R)) {
                            str = "pbeWithMD2AndRC2-CBC unsupported";
                        } else if (Util.a(bArrA3, S)) {
                            str = "pbeWithMD5AndDES-CBC unsupported";
                        } else if (Util.a(bArrA3, T)) {
                            str = "pbeWithMD5AndRC2-CBC unsupported";
                        } else if (Util.a(bArrA3, U)) {
                            str = "pbeWithSHA1AndDES-CBC unsupported";
                        } else if (Util.a(bArrA3, V)) {
                            str = "pbeWithSHA1AndRC2-CBC unsupported";
                        } else {
                            str = "unsupported encryption oid: " + Util.y(bArrA3);
                        }
                        throw new JSchException(str);
                    }
                    KeyPair.ASN1[] asn1ArrB3 = asn3.b();
                    if (asn1ArrB3.length != 2) {
                        throw new KeyPair.ASN1Exception();
                    }
                    KeyPair.ASN1 asn4 = asn1ArrB3[0];
                    KeyPair.ASN1 asn5 = asn1ArrB3[1];
                    if (!asn4.k()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    if (!asn5.k()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    KeyPair.ASN1[] asn1ArrB4 = asn5.b();
                    if (asn1ArrB4.length != 2) {
                        throw new KeyPair.ASN1Exception();
                    }
                    if (!asn1ArrB4[0].i()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    byte[] bArrA4 = asn1ArrB4[0].a();
                    KeyPair.ASN1 asn6 = asn1ArrB4[1];
                    KeyPair.ASN1[] asn1ArrB5 = asn4.b();
                    if (asn1ArrB5.length != 2) {
                        throw new KeyPair.ASN1Exception();
                    }
                    if (!asn1ArrB5[0].i()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    if (!asn1ArrB5[1].k()) {
                        throw new KeyPair.ASN1Exception();
                    }
                    byte[] bArrA5 = asn1ArrB5[0].a();
                    if (Util.a(bArrA5, A)) {
                        KeyPair.ASN1 asn7 = asn1ArrB5[1];
                        if (!asn7.k()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        KeyPair.ASN1[] asn1ArrB6 = asn7.b();
                        if (asn1ArrB6.length < 2 || asn1ArrB6.length > 4) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB6[0].j()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB6[1].g()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (asn1ArrB6.length == 4) {
                            if (!asn1ArrB6[2].g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB6[3].k()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            asn1 = asn1ArrB6[3];
                        } else if (asn1ArrB6.length != 3) {
                            asn1 = null;
                        } else if (asn1ArrB6[2].k()) {
                            asn1 = asn1ArrB6[2];
                        } else {
                            if (!asn1ArrB6[2].g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            asn1 = null;
                        }
                        byte[] bArrA6 = asn1ArrB6[0].a();
                        int iL = L(asn1ArrB6[1].a());
                        if (asn1 != null) {
                            KeyPair.ASN1[] asn1ArrB7 = asn1.b();
                            if (asn1ArrB7.length != 2) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB7[0].i()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB7[1].h()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            bArrA = asn1ArrB7[0].a();
                        } else {
                            bArrA = null;
                        }
                        strJ = J(bArrA);
                        PBKDF2 pbkdf2I = I(strJ);
                        pbkdf2I.a(bArrA6, iL);
                        kdf = pbkdf2I;
                    } else {
                        if (!Util.a(bArrA5, B)) {
                            throw new JSchException("unsupported kdf oid: " + Util.y(bArrA5));
                        }
                        KeyPair.ASN1[] asn1ArrB8 = asn1ArrB5[1].b();
                        if (asn1ArrB8.length < 4 || asn1ArrB8.length > 5) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB8[0].j()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB8[1].g()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB8[2].g()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB8[3].g()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (asn1ArrB8.length > 4 && !asn1ArrB8[4].g()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        byte[] bArrA7 = asn1ArrB8[0].a();
                        int iL2 = L(asn1ArrB8[1].a());
                        int iL3 = L(asn1ArrB8[2].a());
                        int iL4 = L(asn1ArrB8[3].a());
                        SCrypt sCryptK = K();
                        sCryptK.d(bArrA7, iL2, iL3, iL4);
                        strJ = "scrypt";
                        kdf = sCryptK;
                    }
                    byte[][] bArr8 = new byte[1][];
                    Cipher cipherH = H(bArrA4, asn6, bArr8);
                    byte[] bArr9 = bArr8[0];
                    byte[] bArrC = kdf.c(bArr, cipherH.d());
                    try {
                        if (bArrC == null) {
                            throw new JSchException("failed to generate key from KDF " + strJ);
                        }
                        cipherH.l(1, bArrC, bArr9);
                        bArr2 = new byte[bArrA2.length];
                        try {
                            cipherH.g(bArrA2, 0, bArrA2.length, bArr2, 0);
                            if (!y(bArr2)) {
                                throw new JSchException("failed to parse decrypted key");
                            }
                            this.i = false;
                            Util.g(this.j);
                            Util.g(bArrA2);
                            Util.g(bArrC);
                            Util.g(bArr2);
                            return true;
                        } catch (KeyPair.ASN1Exception e) {
                            bArr4 = bArrC;
                            aSN1Exception = e;
                            bArr7 = bArrA2;
                            if (this.c.a().isEnabled(3)) {
                                this.c.a().b(3, "PKCS8: failed to decrypt key: ASN1 parsing error", aSN1Exception);
                            }
                            Util.g(bArr7);
                            Util.g(bArr4);
                            Util.g(bArr2);
                            return false;
                        } catch (Exception e2) {
                            bArr3 = bArrC;
                            exc = e2;
                            bArr7 = bArrA2;
                            if (this.c.a().isEnabled(3)) {
                                this.c.a().b(3, "PKCS8: failed to decrypt key: " + exc.getMessage(), exc);
                            }
                            Util.g(bArr7);
                            Util.g(bArr3);
                            Util.g(bArr2);
                            return false;
                        } catch (Throwable th2) {
                            bArr5 = bArrC;
                            th = th2;
                            bArr7 = bArrA2;
                            bArr6 = bArr5;
                            Util.g(bArr7);
                            Util.g(bArr6);
                            Util.g(bArr2);
                            throw th;
                        }
                    } catch (KeyPair.ASN1Exception e3) {
                        bArr4 = bArrC;
                        aSN1Exception = e3;
                        bArr2 = null;
                    } catch (Exception e4) {
                        bArr3 = bArrC;
                        exc = e4;
                        bArr2 = null;
                    } catch (Throwable th3) {
                        bArr5 = bArrC;
                        th = th3;
                        bArr2 = null;
                    }
                } catch (KeyPair.ASN1Exception e5) {
                    aSN1Exception = e5;
                    bArr4 = null;
                    bArr2 = null;
                } catch (Exception e6) {
                    exc = e6;
                    bArr3 = null;
                    bArr2 = null;
                } catch (Throwable th4) {
                    th = th4;
                    bArr5 = null;
                    bArr2 = null;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (KeyPair.ASN1Exception e7) {
            aSN1Exception = e7;
            bArr4 = null;
            bArr2 = null;
        } catch (Exception e8) {
            exc = e8;
            bArr3 = null;
            bArr2 = null;
        } catch (Throwable th6) {
            th = th6;
            bArr6 = 0;
            bArr2 = null;
        }
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] g() throws JSchException {
        return this.q.g();
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] l() {
        KeyPair keyPair = this.q;
        return keyPair != null ? keyPair.l() : new byte[0];
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] n() {
        return null;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] o() {
        KeyPair keyPair = this.q;
        return keyPair != null ? keyPair.o() : super.o();
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] p(byte[] bArr) {
        return this.q.p(bArr);
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] q(byte[] bArr, String str) {
        return this.q.q(bArr, str);
    }

    /* JADX WARN: Code duplicated, block: B:310:0x04f2 A[Catch: all -> 0x0511, TRY_LEAVE, TryCatch #20 {all -> 0x0511, blocks: (B:308:0x04e6, B:310:0x04f2, B:318:0x0522, B:320:0x052e), top: B:329:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:316:0x051e  */
    /* JADX WARN: Code duplicated, block: B:320:0x052e A[Catch: all -> 0x0511, TRY_LEAVE, TryCatch #20 {all -> 0x0511, blocks: (B:308:0x04e6, B:310:0x04f2, B:318:0x0522, B:320:0x052e), top: B:329:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:323:0x0544  */
    /* JADX WARN: Code duplicated, block: B:327:0x0553  */
    /* JADX WARN: Instruction removed from duplicated block: B:310:0x04f2, please report this as an issue */
    @Override // com.jcraft.jsch.KeyPair
    boolean y(byte[] bArr) throws Throwable {
        byte[] bArr2;
        KeyPair keyPairECDSA;
        byte[] bArrA;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArrV;
        KeyPair.ASN1 asn1;
        byte[] bArrA2;
        byte[] bArr5;
        byte[] bArr6 = null;
        try {
            try {
                KeyPair.ASN1 asn2 = new KeyPair.ASN1(bArr);
                if (!asn2.k()) {
                    throw new KeyPair.ASN1Exception();
                }
                KeyPair.ASN1[] asn1ArrB = asn2.b();
                if (asn1ArrB.length < 3 || asn1ArrB.length > 4) {
                    throw new KeyPair.ASN1Exception();
                }
                if (!asn1ArrB[0].g()) {
                    throw new KeyPair.ASN1Exception();
                }
                if (!asn1ArrB[1].k()) {
                    throw new KeyPair.ASN1Exception();
                }
                if (!asn1ArrB[2].j()) {
                    throw new KeyPair.ASN1Exception();
                }
                if (asn1ArrB.length > 3 && !asn1ArrB[3].f(0)) {
                    throw new KeyPair.ASN1Exception();
                }
                if (L(asn1ArrB[0].a()) != 0) {
                    throw new KeyPair.ASN1Exception();
                }
                KeyPair.ASN1 asn3 = asn1ArrB[1];
                KeyPair.ASN1 asn4 = asn1ArrB[2];
                KeyPair.ASN1[] asn1ArrB2 = asn3.b();
                if (asn1ArrB2.length == 0) {
                    throw new KeyPair.ASN1Exception();
                }
                if (!asn1ArrB2[0].i()) {
                    throw new KeyPair.ASN1Exception();
                }
                byte[] bArrA3 = asn1ArrB2[0].a();
                byte[] bArrA4 = asn4.a();
                try {
                    if (Util.a(bArrA3, r)) {
                        if (asn1ArrB2.length != 2) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB2[1].h()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        KeyPairRSA keyPairRSA = new KeyPairRSA(this.c);
                        keyPairRSA.b(this);
                        if (!keyPairRSA.y(bArrA4)) {
                            throw new JSchException("failed to parse RSA");
                        }
                        this.q = keyPairRSA;
                        Util.g(bArrA4);
                        Util.g(null);
                    } else if (Util.a(bArrA3, s)) {
                        ArrayList arrayList = new ArrayList(3);
                        if (asn1ArrB2.length > 1 && asn1ArrB2[1].k()) {
                            KeyPair.ASN1[] asn1ArrB3 = asn1ArrB2[1].b();
                            if (asn1ArrB3.length != 3) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB3[0].g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB3[1].g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB3[2].g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            arrayList.add(asn1ArrB3[0].a());
                            arrayList.add(asn1ArrB3[1].a());
                            arrayList.add(asn1ArrB3[2].a());
                        }
                        KeyPair.ASN1 asn5 = new KeyPair.ASN1(bArrA4);
                        if (arrayList.size() == 0) {
                            if (!asn5.k()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            KeyPair.ASN1[] asn1ArrB4 = asn5.b();
                            if (asn1ArrB4.length != 2) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB4[0].k()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB4[1].g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            bArrA2 = asn1ArrB4[1].a();
                            try {
                                KeyPair.ASN1[] asn1ArrB5 = asn1ArrB4[0].b();
                                if (asn1ArrB5.length != 3) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                if (!asn1ArrB5[0].g()) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                if (!asn1ArrB5[1].g()) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                if (!asn1ArrB5[2].g()) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                arrayList.add(asn1ArrB5[0].a());
                                arrayList.add(asn1ArrB5[1].a());
                                arrayList.add(asn1ArrB5[2].a());
                            } catch (KeyPair.ASN1Exception e) {
                                e = e;
                                bArr4 = null;
                                keyPairECDSA = null;
                                bArrA = bArrA2;
                                bArr6 = bArrA4;
                                if (this.c.a().isEnabled(3)) {
                                    this.c.a().b(3, "PKCS8: failed to parse key: ASN1 parsing error", e);
                                }
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr4);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                return false;
                            } catch (Exception e2) {
                                e = e2;
                                bArr3 = null;
                                keyPairECDSA = null;
                                bArrA = bArrA2;
                                bArr6 = bArrA4;
                                if (this.c.a().isEnabled(3)) {
                                    this.c.a().b(3, "PKCS8: failed to parse key: " + e.getMessage(), e);
                                }
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr3);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                bArr2 = null;
                                keyPairECDSA = null;
                                bArrA = bArrA2;
                                bArr6 = bArrA4;
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr2);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                throw th;
                            }
                        } else {
                            if (!asn5.g()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            bArrA2 = asn5.a();
                        }
                        try {
                            byte[] bArr7 = (byte[]) arrayList.get(0);
                            byte[] bArr8 = (byte[]) arrayList.get(1);
                            byte[] bArr9 = (byte[]) arrayList.get(2);
                            bArr5 = bArrA2;
                            try {
                                keyPairECDSA = new KeyPairDSA(this.c, bArr7, bArr8, bArr9, new BigInteger(bArr9).modPow(new BigInteger(bArrA2), new BigInteger(bArr7)).toByteArray(), bArr5);
                                try {
                                    byte[] bArrN = keyPairECDSA.n();
                                    KeyPairDSA keyPairDSA = new KeyPairDSA(this.c);
                                    keyPairDSA.b(this);
                                    if (!keyPairDSA.y(bArrN)) {
                                        throw new JSchException("failed to parse DSA");
                                    }
                                    this.q = keyPairDSA;
                                    Util.g(bArrA4);
                                    Util.g(bArr5);
                                    Util.g(bArrN);
                                    keyPairECDSA.f();
                                    return true;
                                } catch (KeyPair.ASN1Exception e3) {
                                    e = e3;
                                    bArr4 = null;
                                    bArr6 = bArrA4;
                                    bArrA = bArr5;
                                    if (this.c.a().isEnabled(3)) {
                                        this.c.a().b(3, "PKCS8: failed to parse key: ASN1 parsing error", e);
                                    }
                                    Util.g(bArr6);
                                    Util.g(bArrA);
                                    Util.g(bArr4);
                                    if (keyPairECDSA != null) {
                                        keyPairECDSA.f();
                                    }
                                    return false;
                                } catch (Exception e4) {
                                    e = e4;
                                    bArr3 = null;
                                    bArr6 = bArrA4;
                                    bArrA = bArr5;
                                    if (this.c.a().isEnabled(3)) {
                                        this.c.a().b(3, "PKCS8: failed to parse key: " + e.getMessage(), e);
                                    }
                                    Util.g(bArr6);
                                    Util.g(bArrA);
                                    Util.g(bArr3);
                                    if (keyPairECDSA != null) {
                                        keyPairECDSA.f();
                                    }
                                    return false;
                                } catch (Throwable th2) {
                                    th = th2;
                                    bArr2 = null;
                                    bArr6 = bArrA4;
                                    bArrA = bArr5;
                                    Util.g(bArr6);
                                    Util.g(bArrA);
                                    Util.g(bArr2);
                                    if (keyPairECDSA != null) {
                                        keyPairECDSA.f();
                                    }
                                    throw th;
                                }
                            } catch (KeyPair.ASN1Exception e5) {
                                e = e5;
                                bArr4 = null;
                                keyPairECDSA = null;
                                bArr6 = bArrA4;
                                bArrA = bArr5;
                                if (this.c.a().isEnabled(3)) {
                                    this.c.a().b(3, "PKCS8: failed to parse key: ASN1 parsing error", e);
                                }
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr4);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                return false;
                            } catch (Exception e6) {
                                e = e6;
                                bArr3 = null;
                                keyPairECDSA = null;
                                bArr6 = bArrA4;
                                bArrA = bArr5;
                                if (this.c.a().isEnabled(3)) {
                                    this.c.a().b(3, "PKCS8: failed to parse key: " + e.getMessage(), e);
                                }
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr3);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                return false;
                            } catch (Throwable th3) {
                                th = th3;
                                bArr2 = null;
                                keyPairECDSA = null;
                                bArr6 = bArrA4;
                                bArrA = bArr5;
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr2);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                throw th;
                            }
                        } catch (KeyPair.ASN1Exception e7) {
                            e = e7;
                            bArr5 = bArrA2;
                        } catch (Exception e8) {
                            e = e8;
                            bArr5 = bArrA2;
                        } catch (Throwable th4) {
                            th = th4;
                            bArr5 = bArrA2;
                        }
                    } else if (!Util.a(bArrA3, t)) {
                        byte[] bArr10 = u;
                        if (!Util.a(bArrA3, bArr10) && !Util.a(bArrA3, v)) {
                            throw new JSchException("unsupported privateKeyAlgorithm oid: " + Util.y(bArrA3));
                        }
                        if (asn1ArrB2.length != 1) {
                            throw new KeyPair.ASN1Exception();
                        }
                        KeyPair.ASN1 asn6 = new KeyPair.ASN1(bArrA4);
                        if (!asn6.j()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        byte[] bArrA5 = asn6.a();
                        try {
                            KeyPair keyPairEd25519 = Util.a(bArrA3, bArr10) ? new KeyPairEd25519(this.c) : new KeyPairEd448(this.c);
                            keyPairEd25519.b(this);
                            if (!keyPairEd25519.y(bArrA5)) {
                                throw new JSchException("failed to parse EdDSA");
                            }
                            this.q = keyPairEd25519;
                            Util.g(bArrA4);
                            Util.g(bArrA5);
                        } catch (KeyPair.ASN1Exception e9) {
                            e = e9;
                            bArr4 = null;
                            keyPairECDSA = null;
                            bArrA = bArrA5;
                            bArr6 = bArrA4;
                            if (this.c.a().isEnabled(3)) {
                                this.c.a().b(3, "PKCS8: failed to parse key: ASN1 parsing error", e);
                            }
                            Util.g(bArr6);
                            Util.g(bArrA);
                            Util.g(bArr4);
                            if (keyPairECDSA != null) {
                                keyPairECDSA.f();
                            }
                            return false;
                        } catch (Exception e10) {
                            e = e10;
                            bArr3 = null;
                            keyPairECDSA = null;
                            bArrA = bArrA5;
                            bArr6 = bArrA4;
                            if (this.c.a().isEnabled(3)) {
                                this.c.a().b(3, "PKCS8: failed to parse key: " + e.getMessage(), e);
                            }
                            Util.g(bArr6);
                            Util.g(bArrA);
                            Util.g(bArr3);
                            if (keyPairECDSA != null) {
                                keyPairECDSA.f();
                            }
                            return false;
                        } catch (Throwable th5) {
                            th = th5;
                            bArr2 = null;
                            keyPairECDSA = null;
                            bArrA = bArrA5;
                            bArr6 = bArrA4;
                            Util.g(bArr6);
                            Util.g(bArrA);
                            Util.g(bArr2);
                            if (keyPairECDSA != null) {
                                keyPairECDSA.f();
                            }
                            throw th;
                        }
                    } else {
                        if (asn1ArrB2.length != 2) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB2[1].i()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        byte[] bArrA6 = asn1ArrB2[1].a();
                        if (!Util.a(bArrA6, w)) {
                            bArrV = Util.v("nistp256");
                        } else if (!Util.a(bArrA6, x)) {
                            bArrV = Util.v("nistp384");
                        } else {
                            if (Util.a(bArrA6, y)) {
                                throw new JSchException("unsupported named curve oid: " + Util.y(bArrA6));
                            }
                            bArrV = Util.v("nistp521");
                        }
                        byte[] bArr11 = bArrV;
                        KeyPair.ASN1 asn7 = new KeyPair.ASN1(bArrA4);
                        if (!asn7.k()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        KeyPair.ASN1[] asn1ArrB6 = asn7.b();
                        if (asn1ArrB6.length < 3 || asn1ArrB6.length > 4) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB6[0].g()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (!asn1ArrB6[1].j()) {
                            throw new KeyPair.ASN1Exception();
                        }
                        if (L(asn1ArrB6[0].a()) != 1) {
                            throw new KeyPair.ASN1Exception();
                        }
                        bArrA = asn1ArrB6[1].a();
                        try {
                            if (asn1ArrB6.length == 3) {
                                asn1 = asn1ArrB6[2];
                            } else {
                                KeyPair.ASN1 asn8 = asn1ArrB6[3];
                                if (!asn1ArrB6[2].f(0)) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                KeyPair.ASN1[] asn1ArrB7 = asn1ArrB6[2].b();
                                if (asn1ArrB7.length != 1) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                if (!asn1ArrB7[0].i()) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                if (!Util.a(asn1ArrB7[0].a(), bArrA6)) {
                                    throw new KeyPair.ASN1Exception();
                                }
                                asn1 = asn8;
                            }
                            if (!asn1.f(1)) {
                                throw new KeyPair.ASN1Exception();
                            }
                            KeyPair.ASN1[] asn1ArrB8 = asn1.b();
                            if (asn1ArrB8.length != 1) {
                                throw new KeyPair.ASN1Exception();
                            }
                            if (!asn1ArrB8[0].e()) {
                                throw new KeyPair.ASN1Exception();
                            }
                            byte[][] bArrH = KeyPairECDSA.H(asn1ArrB8[0].a());
                            keyPairECDSA = new KeyPairECDSA(this.c, bArr11, bArrH[0], bArrH[1], bArrA);
                            try {
                                byte[] bArrN2 = keyPairECDSA.n();
                                KeyPairECDSA keyPairECDSA2 = new KeyPairECDSA(this.c);
                                keyPairECDSA2.b(this);
                                if (!keyPairECDSA2.y(bArrN2)) {
                                    throw new JSchException("failed to parse ECDSA");
                                }
                                this.q = keyPairECDSA2;
                                Util.g(bArrA4);
                                Util.g(bArrA);
                                Util.g(bArrN2);
                                keyPairECDSA.f();
                                return true;
                            } catch (KeyPair.ASN1Exception e11) {
                                e = e11;
                                bArr4 = null;
                                bArr6 = bArrA4;
                                if (this.c.a().isEnabled(3)) {
                                    this.c.a().b(3, "PKCS8: failed to parse key: ASN1 parsing error", e);
                                }
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr4);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                return false;
                            } catch (Exception e12) {
                                e = e12;
                                bArr3 = null;
                                bArr6 = bArrA4;
                                if (this.c.a().isEnabled(3)) {
                                    this.c.a().b(3, "PKCS8: failed to parse key: " + e.getMessage(), e);
                                }
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr3);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                return false;
                            } catch (Throwable th6) {
                                th = th6;
                                bArr2 = null;
                                bArr6 = bArrA4;
                                Util.g(bArr6);
                                Util.g(bArrA);
                                Util.g(bArr2);
                                if (keyPairECDSA != null) {
                                    keyPairECDSA.f();
                                }
                                throw th;
                            }
                        } catch (KeyPair.ASN1Exception e13) {
                            e = e13;
                            bArr4 = null;
                            keyPairECDSA = null;
                        } catch (Exception e14) {
                            e = e14;
                            bArr3 = null;
                            keyPairECDSA = null;
                        } catch (Throwable th7) {
                            th = th7;
                            bArr2 = null;
                            keyPairECDSA = null;
                        }
                    }
                    Util.g(null);
                    return true;
                } catch (KeyPair.ASN1Exception e15) {
                    e = e15;
                    bArr4 = null;
                    keyPairECDSA = null;
                    bArrA = null;
                } catch (Exception e16) {
                    e = e16;
                    bArr3 = null;
                    keyPairECDSA = null;
                    bArrA = null;
                } catch (Throwable th8) {
                    th = th8;
                    bArr2 = null;
                    keyPairECDSA = null;
                    bArrA = null;
                }
            } catch (Throwable th9) {
                th = th9;
            }
        } catch (KeyPair.ASN1Exception e17) {
            e = e17;
            bArr4 = null;
            keyPairECDSA = null;
            bArrA = null;
        } catch (Exception e18) {
            e = e18;
            bArr3 = null;
            keyPairECDSA = null;
            bArrA = null;
        } catch (Throwable th10) {
            th = th10;
            bArr2 = null;
            keyPairECDSA = null;
            bArrA = null;
        }
    }
}
