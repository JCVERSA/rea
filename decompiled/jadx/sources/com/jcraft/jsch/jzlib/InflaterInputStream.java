package com.jcraft.jsch.jzlib;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
final class InflaterInputStream extends FilterInputStream implements InputStreamRetargetInterface, AutoCloseable {
    protected byte[] X;
    private boolean Y;
    protected boolean Z;
    private boolean k0;
    protected boolean l0;
    private byte[] m0;
    private byte[] n0;
    protected final Inflater q;

    protected void a() throws IOException {
        if (this.Y) {
            throw new IOException("Stream closed");
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        byte[] bArr = this.X;
        int i = inputStream.read(bArr, 0, bArr.length);
        if (i == -1) {
            Inflater inflater = this.q;
            if (inflater.k.f != 0 || inflater.g()) {
                if (this.q.k.c == -1) {
                    throw new EOFException("Unexpected end of ZLIB input stream");
                }
                throw new IOException("footer is not found");
            }
            this.X[0] = 0;
            i = 1;
        }
        this.q.d(this.X, 0, i, true);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.Y) {
            throw new IOException("Stream closed");
        }
        return this.Z ? 0 : 1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.Y) {
            return;
        }
        if (this.l0) {
            this.q.f();
        }
        if (this.k0) {
            ((FilterInputStream) this).in.close();
        }
        this.Y = true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.Y) {
            throw new IOException("Stream closed");
        }
        if (read(this.m0, 0, 1) == -1) {
            return -1;
        }
        return this.m0[0] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.Y) {
            throw new IOException("Stream closed");
        }
        bArr.getClass();
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        if (this.Z) {
            return -1;
        }
        this.q.e(bArr, i, i2);
        int i3 = 0;
        while (!this.Z) {
            if (this.q.c == 0) {
                a();
            }
            int iH = this.q.h(0);
            Inflater inflater = this.q;
            int i4 = inflater.f;
            i3 += i4 - i;
            if (iH == -3) {
                throw new IOException(this.q.i);
            }
            if (iH == 1 || iH == 2) {
                this.Z = true;
                if (iH == 2) {
                    return -1;
                }
            }
            if (inflater.g == 0) {
                return i3;
            }
            i = i4;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("negative skip length");
        }
        if (this.Y) {
            throw new IOException("Stream closed");
        }
        int iMin = (int) Math.min(j, 2147483647L);
        int i = 0;
        while (i < iMin) {
            int length = iMin - i;
            byte[] bArr = this.n0;
            if (length > bArr.length) {
                length = bArr.length;
            }
            int i2 = read(bArr, 0, length);
            if (i2 == -1) {
                this.Z = true;
                break;
            }
            i += i2;
        }
        return i;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream outputStream) {
        return DesugarInputStream.transferTo(this, outputStream);
    }
}
