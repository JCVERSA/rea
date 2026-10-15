package com.google.android.exoplayer2.ext.ffmpeg;

import ax.T2.b;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class FfmpegDecoder {
    boolean a;

    FfmpegDecoder(boolean z) {
        this.a = z;
    }

    private native int exFfmpegDecode(long j, ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3);

    private native int exFfmpegGetChannelCount(long j);

    private native int exFfmpegGetSampleRate(long j);

    private native long exFfmpegInitialize(String str, byte[] bArr, boolean z, int i, int i2);

    private native void exFfmpegRelease(long j);

    private native long exFfmpegReset(long j, byte[] bArr);

    private native int fmFfmpegDecode(long j, ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3);

    private native int fmFfmpegGetChannelCount(long j);

    private native int fmFfmpegGetSampleRate(long j);

    private native long fmFfmpegInitialize(String str, byte[] bArr, boolean z, int i, int i2);

    private native void fmFfmpegRelease(long j);

    private native long fmFfmpegReset(long j, byte[] bArr);

    int a(long j, ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        if (this.a) {
            return exFfmpegDecode(j, byteBuffer, i, byteBuffer2, i2, i3);
        }
        if (b.h()) {
            return fmFfmpegDecode(j, byteBuffer, i, byteBuffer2, i2, i3);
        }
        return -2;
    }

    int b(long j) {
        if (this.a) {
            return exFfmpegGetChannelCount(j);
        }
        if (b.h()) {
            return fmFfmpegGetChannelCount(j);
        }
        return 0;
    }

    int c(long j) {
        if (this.a) {
            return exFfmpegGetSampleRate(j);
        }
        if (b.h()) {
            return fmFfmpegGetSampleRate(j);
        }
        return 0;
    }

    long d(String str, byte[] bArr, boolean z, int i, int i2) {
        if (this.a) {
            return exFfmpegInitialize(str, bArr, z, i, i2);
        }
        if (b.h()) {
            return fmFfmpegInitialize(str, bArr, z, i, i2);
        }
        return 0L;
    }

    void e(long j) {
        if (this.a) {
            exFfmpegRelease(j);
        } else if (b.h()) {
            fmFfmpegRelease(j);
        }
    }

    long f(long j, byte[] bArr) {
        if (this.a) {
            return exFfmpegReset(j, bArr);
        }
        if (b.h()) {
            return fmFfmpegReset(j, bArr);
        }
        return 0L;
    }
}
