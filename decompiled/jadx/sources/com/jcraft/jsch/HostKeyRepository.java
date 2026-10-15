package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public interface HostKeyRepository {
    String a();

    void b(HostKey hostKey, UserInfo userInfo);

    int c(String str, byte[] bArr);

    void d(String str, String str2, byte[] bArr);

    HostKey[] e(String str, String str2);
}
