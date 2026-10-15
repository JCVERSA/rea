package com.jcraft.jsch.jzlib;

/* JADX INFO: loaded from: classes3.dex */
final class CRC32 implements Checksum {
    private static int[] b = new int[256];
    private int a = 0;

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = 8;
            int i3 = i;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    i3 = (i3 & 1) != 0 ? (i3 >>> 1) ^ (-306674912) : i3 >>> 1;
                }
            }
            b[i] = i3;
        }
    }

    CRC32() {
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public void a() {
        this.a = 0;
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public void b(byte[] bArr, int i, int i2) {
        int i3 = ~this.a;
        while (true) {
            i2--;
            if (i2 < 0) {
                this.a = ~i3;
                return;
            }
            i3 = (i3 >>> 8) ^ b[(bArr[i] ^ i3) & 255];
            i++;
        }
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public void c(long j) {
        this.a = (int) (j & 4294967295L);
    }

    @Override // com.jcraft.jsch.jzlib.Checksum
    public long getValue() {
        return ((long) this.a) & 4294967295L;
    }
}
