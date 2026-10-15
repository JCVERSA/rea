package com.jcraft.jsch.jzlib;

import java.io.FilterOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class DeflaterOutputStream extends FilterOutputStream implements AutoCloseable {
    protected byte[] X;
    private boolean Y;
    private boolean Z;
    private final byte[] k0;
    protected boolean l0;
    private boolean m0;
    protected final Deflater q;

    protected int a(int i) throws IOException {
        Deflater deflater = this.q;
        byte[] bArr = this.X;
        deflater.e(bArr, 0, bArr.length);
        int iF = this.q.f(i);
        if (iF == -5 ? !(this.q.c > 0 || i == 4) : iF == 0 || iF == 1) {
            int i2 = this.q.f;
            if (i2 > 0) {
                ((FilterOutputStream) this).out.write(this.X, 0, i2);
            }
            return iF;
        }
        throw new IOException("failed to deflate: error=" + iF + " avail_out=" + this.q.g);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.Y) {
            return;
        }
        d();
        if (this.l0) {
            this.q.g();
        }
        if (this.m0) {
            ((FilterOutputStream) this).out.close();
        }
        this.Y = true;
    }

    void d() throws IOException {
        while (!this.q.h()) {
            a(4);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        int iA;
        if (this.Z && !this.q.h()) {
            do {
                iA = a(2);
                if (this.q.f < this.X.length) {
                    break;
                }
            } while (iA != 1);
        }
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.k0;
        bArr[0] = (byte) (i & 255);
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.q.h()) {
            throw new IOException("finished");
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        int i3 = this.Z ? 2 : 0;
        this.q.d(bArr, i, i2, true);
        while (this.q.c > 0 && a(i3) != 1) {
        }
    }
}
