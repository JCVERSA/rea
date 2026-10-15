package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public interface SignatureDSA extends Signature {
    void d(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws Exception;

    void h(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws Exception;
}
