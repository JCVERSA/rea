package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public class SftpException extends Exception {
    public int q;

    public SftpException(int i, String str) {
        super(str);
        this.q = i;
    }

    public SftpException(int i, String str, Throwable th) {
        super(str, th);
        this.q = i;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return this.q + ": " + getMessage();
    }
}
