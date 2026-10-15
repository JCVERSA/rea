package com.jcraft.jsch;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
class KeyPairEd448 extends KeyPairEdDSA {
    private static int s = 57;

    KeyPairEd448(JSch.InstanceLogger instanceLogger) {
        this(instanceLogger, null, null);
    }

    KeyPairEd448(JSch.InstanceLogger instanceLogger, byte[] bArr, byte[] bArr2) {
        super(instanceLogger, bArr, bArr2);
    }

    static KeyPair J(JSch.InstanceLogger instanceLogger, Buffer buffer) throws JSchException {
        byte[][] bArrG = buffer.g(4, "invalid key format");
        KeyPairEd448 keyPairEd448 = new KeyPairEd448(instanceLogger, bArrG[1], Arrays.copyOf(bArrG[2], s));
        keyPairEd448.b = Util.c(bArrG[3]);
        keyPairEd448.a = 0;
        return keyPairEd448;
    }

    @Override // com.jcraft.jsch.KeyPairEdDSA
    String H() {
        return "Ed448";
    }

    @Override // com.jcraft.jsch.KeyPairEdDSA
    String I() {
        return "ssh-ed448";
    }

    @Override // com.jcraft.jsch.KeyPair
    public int k() {
        return s;
    }
}
