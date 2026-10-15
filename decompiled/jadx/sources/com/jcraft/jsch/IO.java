package com.jcraft.jsch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
class IO {
    InputStream a;
    OutputStream b;
    OutputStream c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;

    IO() {
    }

    void a() {
        try {
            InputStream inputStream = this.a;
            if (inputStream != null && !this.d) {
                inputStream.close();
            }
            this.a = null;
        } catch (Exception unused) {
        }
        d();
        try {
            OutputStream outputStream = this.c;
            if (outputStream != null && !this.f) {
                outputStream.close();
            }
            this.c = null;
        } catch (Exception unused2) {
        }
    }

    int b() throws IOException {
        return this.a.read();
    }

    void c(byte[] bArr, int i, int i2) throws IOException {
        do {
            int i3 = this.a.read(bArr, i, i2);
            if (i3 < 0) {
                throw new IOException("End of IO Stream Read");
            }
            i += i3;
            i2 -= i3;
        } while (i2 > 0);
    }

    void d() {
        try {
            OutputStream outputStream = this.b;
            if (outputStream != null && !this.e) {
                outputStream.close();
            }
            this.b = null;
        } catch (Exception unused) {
        }
    }

    void e(Packet packet) throws IOException {
        OutputStream outputStream = this.b;
        Buffer buffer = packet.a;
        outputStream.write(buffer.b, 0, buffer.c);
        this.b.flush();
    }

    void f(byte[] bArr, int i, int i2) throws IOException {
        this.b.write(bArr, i, i2);
        this.b.flush();
    }

    void g(byte[] bArr, int i, int i2) throws IOException {
        this.c.write(bArr, i, i2);
        this.c.flush();
    }

    void h(InputStream inputStream) {
        this.a = inputStream;
    }

    void i(InputStream inputStream, boolean z) {
        this.d = z;
        h(inputStream);
    }

    void j(OutputStream outputStream) {
        this.b = outputStream;
    }

    void k(OutputStream outputStream, boolean z) {
        this.e = z;
        j(outputStream);
    }
}
