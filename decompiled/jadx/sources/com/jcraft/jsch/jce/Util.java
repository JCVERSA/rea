package com.jcraft.jsch.jce;

/* JADX INFO: loaded from: classes3.dex */
class Util {
    Util() {
    }

    static void a(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 0;
        }
    }
}
