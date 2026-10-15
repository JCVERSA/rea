package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public interface Identity {
    byte[] a(byte[] bArr, String str);

    String b();

    boolean c();

    void clear();

    byte[] d();

    boolean e(byte[] bArr) throws JSchException;

    byte[] f(byte[] bArr);

    String getName();
}
