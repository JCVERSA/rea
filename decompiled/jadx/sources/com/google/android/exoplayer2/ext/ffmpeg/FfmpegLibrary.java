package com.google.android.exoplayer2.ext.ffmpeg;

import ax.P0.C1379z;
import ax.S0.C1517u;
import ax.T2.b;

/* JADX INFO: loaded from: classes3.dex */
public final class FfmpegLibrary {
    private static String a;
    private static int b;

    static {
        C1379z.a("media3.decoder.ffmpeg");
        b = -1;
    }

    private static String a() {
        if (b.f()) {
            return exFfmpegGetVersion();
        }
        if (b.h()) {
            return fmFfmpegGetVersion();
        }
        return null;
    }

    private static boolean b(String str) {
        if (b.f()) {
            return exFfmpegHasDecoder(str);
        }
        if (b.h()) {
            return fmFfmpegHasDecoder(str);
        }
        return false;
    }

    static String c(String str) {
        str.getClass();
        switch (str) {
            case "audio/eac3-joc":
            case "audio/eac3":
                return "eac3";
            case "audio/amr-wb":
                return "amrwb";
            case "audio/vnd.dts":
            case "audio/vnd.dts.hd":
                return "dca";
            case "audio/vorbis":
                return "vorbis";
            case "audio/mpeg-L1":
            case "audio/mpeg-L2":
            case "audio/mpeg":
                return "mp3";
            case "audio/mp4a-latm":
                return "aac";
            case "audio/ac3":
                return "ac3";
            case "audio/3gpp":
                return "amrnb";
            case "audio/alac":
                return "alac";
            case "audio/flac":
                return "flac";
            case "audio/opus":
                return "opus";
            case "audio/true-hd":
                return "truehd";
            case "audio/g711-alaw":
                return "pcm_alaw";
            case "audio/g711-mlaw":
                return "pcm_mulaw";
            default:
                return null;
        }
    }

    public static int d() {
        if (!f()) {
            return -1;
        }
        if (b == -1) {
            b = 64;
        }
        return b;
    }

    public static String e() {
        if (!f()) {
            return null;
        }
        try {
            if (a == null) {
                a = a();
            }
            return a;
        } catch (Error unused) {
            return null;
        }
    }

    private static native String exFfmpegGetVersion();

    private static native boolean exFfmpegHasDecoder(String str);

    public static boolean f() {
        return b.b();
    }

    private static native String fmFfmpegGetVersion();

    private static native boolean fmFfmpegHasDecoder(String str);

    public static boolean g(String str) {
        String strC;
        if (!f() || (strC = c(str)) == null) {
            return false;
        }
        try {
            if (b(strC)) {
                return true;
            }
            C1517u.h("FfmpegLibrary", "No " + strC + " decoder available. Check the FFmpeg build configuration.");
            return false;
        } catch (Error unused) {
            return false;
        }
    }
}
