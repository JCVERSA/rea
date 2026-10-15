package com.jcraft.jsch;

import java.util.logging.Level;

/* JADX INFO: loaded from: classes3.dex */
public class JulLogger implements Logger {
    private static final java.util.logging.Logger a = java.util.logging.Logger.getLogger(JSch.class.getName());

    static Level c(int i) {
        if (i == 0) {
            return Level.FINE;
        }
        if (i == 1) {
            return Level.INFO;
        }
        if (i != 2) {
            return (i == 3 || i == 4) ? Level.SEVERE : Level.FINER;
        }
        return Level.WARNING;
    }

    @Override // com.jcraft.jsch.Logger
    public void a(int i, String str) {
        b(i, str, null);
    }

    @Override // com.jcraft.jsch.Logger
    public void b(int i, String str, Throwable th) {
        if (th == null) {
            a.log(c(i), str);
        } else {
            a.log(c(i), str, th);
        }
    }

    @Override // com.jcraft.jsch.Logger
    public boolean isEnabled(int i) {
        return a.isLoggable(c(i));
    }
}
