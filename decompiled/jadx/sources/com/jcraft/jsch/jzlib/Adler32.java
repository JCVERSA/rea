package com.jcraft.jsch.jzlib;

import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes3.dex */
final class Adler32 implements Checksum {
    private long a = 1;
    private long b = 0;

    Adler32() {
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public void a() {
        this.a = 1L;
        this.b = 0L;
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public void b(byte[] bArr, int i, int i2) {
        if (i2 == 1) {
            long j = this.a + ((long) (bArr[i] & 255));
            long j2 = this.b + j;
            this.a = j % 65521;
            this.b = j2 % 65521;
            return;
        }
        int i3 = i2 / 5552;
        int i4 = i2 % 5552;
        while (true) {
            int i5 = i3 - 1;
            if (i3 <= 0) {
                break;
            }
            int i6 = 5552;
            while (true) {
                int i7 = i6 - 1;
                if (i6 > 0) {
                    long j3 = this.a + ((long) (bArr[i] & 255));
                    this.a = j3;
                    this.b += j3;
                    i++;
                    i6 = i7;
                }
            }
            this.a %= 65521;
            this.b %= 65521;
            i3 = i5;
        }
        while (true) {
            int i8 = i4 - 1;
            if (i4 <= 0) {
                this.a %= 65521;
                this.b %= 65521;
                return;
            }
            long j4 = this.a + ((long) (bArr[i] & 255));
            this.a = j4;
            this.b += j4;
            i++;
            i4 = i8;
        }
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public void c(long j) {
        this.a = j & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        this.b = (j >> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public long getValue() {
        return (this.b << 16) | this.a;
    }
}
