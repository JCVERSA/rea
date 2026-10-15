package com.jcraft.jsch.jzlib;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Logger;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.jzlib.Compression;
import java.io.UncheckedIOException;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public class Compression implements com.jcraft.jsch.Compression {
    private Deflater b;
    private Inflater c;
    private byte[] e;
    private Session f;
    private final int a = 52;
    private byte[] d = new byte[4096];

    public static /* synthetic */ String e(int i) {
        return "compress: deflate returnd " + i;
    }

    public static /* synthetic */ String f(int i) {
        return "compress: deflate returnd " + i;
    }

    public static /* synthetic */ String g(Compression compression) {
        compression.getClass();
        return "zlib using " + compression.getClass().getCanonicalName();
    }

    private void i(int i, Supplier<String> supplier) {
        Session session = this.f;
        Logger loggerK = session == null ? JSch.k() : session.A();
        if (loggerK.isEnabled(i)) {
            loggerK.a(i, supplier.get());
        }
    }

    @Override // com.jcraft.jsch.Compression
    public void a() {
        this.e = null;
        Inflater inflater = this.c;
        if (inflater != null) {
            inflater.f();
            this.c = null;
        }
        Deflater deflater = this.b;
        if (deflater != null) {
            deflater.g();
            this.b = null;
        }
        this.f = null;
    }

    @Override // com.jcraft.jsch.Compression
    public byte[] b(byte[] bArr, int i, int[] iArr) {
        Deflater deflater = this.b;
        deflater.a = bArr;
        deflater.b = i;
        deflater.c = iArr[0] - i;
        do {
            Deflater deflater2 = this.b;
            deflater2.e = this.d;
            deflater2.f = 0;
            deflater2.g = 4096;
            final int iF = deflater2.f(1);
            if (iF != 0) {
                i(2, new Supplier() { // from class: ax.ga.a
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Compression.e(iF);
                    }
                });
            } else {
                int i2 = 4096 - this.b.g;
                int i3 = i + i2;
                int i4 = i3 + 52;
                if (bArr.length < i4) {
                    byte[] bArr2 = new byte[i4 * 2];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    bArr = bArr2;
                }
                System.arraycopy(this.d, 0, bArr, i, i2);
                i = i3;
            }
        } while (this.b.g == 0);
        iArr[0] = i;
        return bArr;
    }

    @Override // com.jcraft.jsch.Compression
    public byte[] c(byte[] bArr, int i, int[] iArr) {
        Inflater inflater = this.c;
        inflater.a = bArr;
        inflater.b = i;
        inflater.c = iArr[0];
        int i2 = 0;
        while (true) {
            Inflater inflater2 = this.c;
            inflater2.e = this.d;
            inflater2.f = 0;
            inflater2.g = 4096;
            final int iH = inflater2.h(1);
            if (iH == -5) {
                if (i2 > bArr.length - i) {
                    byte[] bArr2 = new byte[i2 + i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    System.arraycopy(this.e, 0, bArr2, i, i2);
                    bArr = bArr2;
                } else {
                    System.arraycopy(this.e, 0, bArr, i, i2);
                }
                iArr[0] = i2;
                return bArr;
            }
            if (iH != 0) {
                i(2, new Supplier() { // from class: ax.ga.b
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Compression.f(iH);
                    }
                });
                return null;
            }
            byte[] bArr3 = this.e;
            int length = bArr3.length;
            int i3 = i2 + 4096;
            int i4 = this.c.g;
            if (length < i3 - i4) {
                int length2 = bArr3.length * 2;
                if (length2 < i3 - i4) {
                    length2 = i3 - i4;
                }
                byte[] bArr4 = new byte[length2];
                System.arraycopy(bArr3, 0, bArr4, 0, i2);
                this.e = bArr4;
            }
            System.arraycopy(this.d, 0, this.e, i2, 4096 - this.c.g);
            i2 += 4096 - this.c.g;
            iArr[0] = i2;
        }
    }

    @Override // com.jcraft.jsch.Compression
    public void d(int i, int i2, Session session) {
        this.f = session;
        h(i, i2);
    }

    public void h(int i, int i2) throws UncheckedIOException {
        if (i == 1) {
            try {
                this.b = new Deflater(i2);
            } catch (GZIPException e) {
                throw new UncheckedIOException(e);
            }
        } else if (i == 0) {
            this.c = new Inflater();
            this.e = new byte[4096];
        }
        i(0, new Supplier() { // from class: ax.ga.c
            @Override // java.util.function.Supplier
            public final Object get() {
                return Compression.g(this.a);
            }
        });
    }
}
