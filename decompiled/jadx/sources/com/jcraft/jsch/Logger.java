package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public interface Logger {
    void a(int i, String str);

    void b(int i, String str, Throwable th);

    boolean isEnabled(int i);
}
