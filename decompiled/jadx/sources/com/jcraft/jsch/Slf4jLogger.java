package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public class Slf4jLogger implements Logger {
    private static final ax.Me.d a = ax.Me.f.k(JSch.class);

    @Override // com.jcraft.jsch.Logger
    public void a(int i, String str) {
        b(i, str, null);
    }

    @Override // com.jcraft.jsch.Logger
    public void b(int i, String str, Throwable th) {
        if (isEnabled(i)) {
            if (i == 0) {
                a.y(str, th);
                return;
            }
            if (i == 1) {
                a.v(str, th);
                return;
            }
            if (i == 2) {
                a.w(str, th);
            } else if (i == 3 || i == 4) {
                a.h(str, th);
            } else {
                a.x(str, th);
            }
        }
    }

    @Override // com.jcraft.jsch.Logger
    public boolean isEnabled(int i) {
        if (i == 0) {
            return a.e();
        }
        if (i == 1) {
            return a.m();
        }
        if (i != 2) {
            return (i == 3 || i == 4) ? a.k() : a.p();
        }
        return a.c();
    }
}
