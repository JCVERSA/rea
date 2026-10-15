package com.jcraft.jsch.jzlib;

/* JADX INFO: loaded from: classes3.dex */
final class JZlib {
    static final WrapperType a = WrapperType.NONE;
    static final WrapperType b = WrapperType.ZLIB;
    static final WrapperType c = WrapperType.GZIP;
    static final WrapperType d = WrapperType.ANY;

    enum WrapperType {
        NONE,
        ZLIB,
        GZIP,
        ANY
    }

    JZlib() {
    }
}
