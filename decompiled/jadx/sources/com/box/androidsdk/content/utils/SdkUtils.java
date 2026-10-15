package com.box.androidsdk.content.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;
import ax.N3.g;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class SdkUtils {
    protected static final int[] a = {-4056997, -1231017, -103524, -680300, -551424, -675045, -4733409, -14237055, -15359317, -11221777, -15620865, -9467905, -12627501, -10011977, -5552196};
    private static final char[] b = "0123456789abcdef".toCharArray();
    private static HashMap<Integer, Long> c = new HashMap<Integer, Long>(10) { // from class: com.box.androidsdk.content.utils.SdkUtils.3
        private void b() {
            long jCurrentTimeMillis = System.currentTimeMillis() - SdkUtils.d;
            for (Map.Entry<Integer, Long> entry : entrySet()) {
                if (entry.getValue().longValue() < jCurrentTimeMillis) {
                    SdkUtils.c.remove(entry);
                }
            }
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public Long put(Integer num, Long l) {
            Long l2 = (Long) super.put(num, l);
            if (size() > 9) {
                b();
            }
            return l2;
        }
    };
    public static long d = 3000;
    private static String e = "%4.0f B";
    private static String f = "%4.1f KB";
    private static String g = "%4.1f MB";
    private static String h = "%4.1f GB";
    private static String i = "%4.1f TB";
    private static String j = HttpUrl.FRAGMENT_ENCODE_SET;

    class a implements ThreadFactory {
        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable);
        }
    }

    class b implements Runnable {
        final /* synthetic */ Context X;
        final /* synthetic */ int Y;
        final /* synthetic */ int q;

        b(int i, Context context, int i2) {
            this.q = i;
            this.X = context;
            this.Y = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            SdkUtils.c.put(Integer.valueOf(this.q), Long.valueOf(System.currentTimeMillis()));
            Toast.makeText(this.X, this.q, this.Y).show();
        }
    }

    public static String b(String[] strArr, String str) {
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            int i3 = length - 1;
            if (i2 >= i3) {
                sb.append(strArr[i3]);
                return sb.toString();
            }
            sb.append(strArr[i2]);
            sb.append(str);
            i2++;
        }
    }

    public static void c(InputStream inputStream, OutputStream outputStream) throws InterruptedException, IOException {
        d(inputStream, outputStream, null);
    }

    private static void d(InputStream inputStream, OutputStream outputStream, MessageDigest messageDigest) throws InterruptedException, IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                try {
                    int i2 = inputStream.read(bArr);
                    if (i2 <= 0) {
                        outputStream.flush();
                        return;
                    } else {
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException();
                        }
                        outputStream.write(bArr, 0, i2);
                        if (messageDigest != null) {
                            messageDigest.update(bArr, 0, i2);
                        }
                    }
                } catch (Exception e2) {
                    if (e2 instanceof IOException) {
                        throw ((IOException) e2);
                    }
                    if (e2 instanceof InterruptedException) {
                        throw ((InterruptedException) e2);
                    }
                    return;
                }
            } catch (Throwable th) {
                if (0 == 0) {
                    outputStream.flush();
                }
                throw th;
            }
        }
    }

    public static String e(InputStream inputStream, OutputStream outputStream) throws InterruptedException, NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        d(inputStream, outputStream, messageDigest);
        return new String(h(messageDigest.digest()));
    }

    public static ThreadPoolExecutor f(int i2, int i3, long j2, TimeUnit timeUnit) {
        return new g(i2, i3, j2, timeUnit, new LinkedBlockingQueue(), new a());
    }

    public static boolean g(File file) {
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return false;
            }
            for (File file2 : fileArrListFiles) {
                g(file2);
            }
        }
        return file.delete();
    }

    private static char[] h(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        int i2 = 0;
        for (byte b2 : bArr) {
            int i3 = i2 + 1;
            char[] cArr2 = b;
            cArr[i2] = cArr2[(b2 & 240) >>> 4];
            i2 += 2;
            cArr[i3] = cArr2[b2 & 15];
        }
        return cArr;
    }

    public static String i() {
        return UUID.randomUUID().toString();
    }

    public static String j(Context context, String str) throws Throwable {
        BufferedReader bufferedReader;
        AssetManager assets = context.getAssets();
        BufferedReader bufferedReader2 = null;
        try {
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(assets.open(str)));
            boolean z = true;
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            String string = sb.toString();
                            try {
                                bufferedReader.close();
                                return string;
                            } catch (Exception e2) {
                                ax.N3.b.b("getAssetFile", str, e2);
                                return string;
                            }
                        }
                        if (z) {
                            z = false;
                        } else {
                            sb.append('\n');
                        }
                        sb.append(line);
                    } catch (IOException e3) {
                        e = e3;
                        ax.N3.b.b("getAssetFile", str, e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e4) {
                                ax.N3.b.b("getAssetFile", str, e4);
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                }
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception e5) {
                        ax.N3.b.b("getAssetFile", str, e5);
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean k(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean l(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean m(Context context) {
        return n((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity"));
    }

    private static boolean n(ConnectivityManager connectivityManager) {
        Network[] allNetworks = connectivityManager.getAllNetworks();
        if (allNetworks != null) {
            for (Network network : allNetworks) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.isConnected() && (networkInfo.getType() == 1 || networkInfo.getType() == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void o(Context context, TextView textView, int i2) {
        String str;
        if (i2 >= 100) {
            str = "+99";
        } else {
            str = "+" + Integer.toString(i2);
        }
        p(textView);
        textView.setTextColor(-1);
        textView.setText(str);
    }

    public static void p(TextView textView) {
        r(textView, -14997455, -1);
    }

    public static void q(TextView textView, int i2) {
        int[] iArr = a;
        r(textView, iArr[i2 % iArr.length], -1);
    }

    public static void r(TextView textView, int i2, int i3) {
        GradientDrawable gradientDrawable = (GradientDrawable) textView.getResources().getDrawable(ax.O3.a.b);
        gradientDrawable.setColorFilter(i2, PorterDuff.Mode.MULTIPLY);
        gradientDrawable.setStroke(3, i3);
        textView.setBackground(gradientDrawable);
    }

    public static void s(Context context, TextView textView, String str) {
        char cCharAt;
        char c2 = 0;
        if (str != null) {
            String[] strArrSplit = str.split(" ");
            char cCharAt2 = strArrSplit[0].length() > 0 ? strArrSplit[0].charAt(0) : (char) 0;
            if (strArrSplit.length > 1) {
                cCharAt = strArrSplit[strArrSplit.length - 1].charAt(0);
                c2 = cCharAt2;
            } else {
                c2 = cCharAt2;
                cCharAt = 0;
            }
        } else {
            cCharAt = 0;
        }
        q(textView, c2 + cCharAt);
        textView.setText(c2 + HttpUrl.FRAGMENT_ENCODE_SET + cCharAt);
        textView.setTextColor(-1);
    }

    public static void t(Context context, int i2, int i3) {
        Long l = c.get(Integer.valueOf(i2));
        if (l == null || l.longValue() + d <= System.currentTimeMillis()) {
            Looper mainLooper = Looper.getMainLooper();
            if (!Thread.currentThread().equals(mainLooper.getThread())) {
                new Handler(mainLooper).post(new b(i2, context, i3));
            } else {
                c.put(Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis()));
                Toast.makeText(context, i2, i3).show();
            }
        }
    }
}
