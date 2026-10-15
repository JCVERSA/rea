package com.jcraft.jsch.jzlib;

/* JADX INFO: loaded from: classes3.dex */
final class Deflater extends ZStream {
    private boolean n;

    Deflater() {
        this.n = false;
    }

    Deflater(int i) throws GZIPException {
        this(i, 15);
    }

    Deflater(int i, int i2) throws GZIPException {
        this(i, i2, false);
    }

    Deflater(int i, int i2, boolean z) throws GZIPException {
        this.n = false;
        int i3 = i(i, i2, z);
        if (i3 == 0) {
            return;
        }
        throw new GZIPException(i3 + ": " + this.i);
    }

    int f(int i) {
        Deflate deflate = this.j;
        if (deflate == null) {
            return -2;
        }
        int iN = deflate.n(i);
        if (iN == 1) {
            this.n = true;
        }
        return iN;
    }

    int g() {
        this.n = true;
        Deflate deflate = this.j;
        if (deflate == null) {
            return -2;
        }
        int iO = deflate.o();
        this.j = null;
        b();
        return iO;
    }

    boolean h() {
        return this.n;
    }

    int i(int i, int i2, boolean z) {
        this.n = false;
        Deflate deflate = new Deflate(this);
        this.j = deflate;
        if (z) {
            i2 = -i2;
        }
        return deflate.q(i, i2);
    }
}
