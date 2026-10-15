package com.google.android.gms.measurement.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ext.SdkExtensions;
import android.text.TextUtils;
import ax.H6.Z6;
import ax.H6.t7;
import ax.p217n6.C2177h;
import ax.p265q6.C2254p;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes3.dex */
public final class B5 extends AbstractC6309e3 {
    private static final String[] i = {"firebase_", "google_", "ga_"};
    private static final String[] j = {"_err"};
    private SecureRandom c;
    private final AtomicLong d;
    private int e;
    private ax.p069e2.a f;
    private Boolean g;
    private Integer h;

    B5(E2 e2) {
        super(e2);
        this.h = null;
        this.d = new AtomicLong(0L);
    }

    public static long A(C c) {
        long length = 0;
        if (c == null) {
            return 0L;
        }
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            Object objH = c.H(it.next());
            if (objH instanceof Parcelable[]) {
                length += (long) ((Parcelable[]) objH).length;
            }
        }
        return length;
    }

    static long B(byte[] bArr) {
        C2254p.l(bArr);
        int i2 = 0;
        C2254p.o(bArr.length > 0);
        long j2 = 0;
        for (int length = bArr.length - 1; length >= 0 && length >= bArr.length - 8; length--) {
            j2 += (((long) bArr[length]) & 255) << i2;
            i2 += 8;
        }
        return j2;
    }

    private static boolean B0(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) == null || !serviceInfo.enabled) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static Bundle D(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else {
                int i2 = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i2 < parcelableArr.length) {
                        if (parcelableArr[i2] instanceof Bundle) {
                            parcelableArr[i2] = new Bundle((Bundle) parcelableArr[i2]);
                        }
                        i2++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i2 < list.size()) {
                        Object obj2 = list.get(i2);
                        if (obj2 instanceof Bundle) {
                            list.set(i2, new Bundle((Bundle) obj2));
                        }
                        i2++;
                    }
                }
            }
        }
        return bundle2;
    }

    static boolean D0(String str) {
        return G0(F.c0.a(null), str);
    }

    public static Bundle G(List<A5> list) {
        Bundle bundle = new Bundle();
        if (list != null) {
            for (A5 a5 : list) {
                String str = a5.k0;
                if (str != null) {
                    bundle.putString(a5.X, str);
                } else {
                    Long l = a5.Z;
                    if (l != null) {
                        bundle.putLong(a5.X, l.longValue());
                    } else {
                        Double d = a5.m0;
                        if (d != null) {
                            bundle.putDouble(a5.X, d.doubleValue());
                        }
                    }
                }
            }
        }
        return bundle;
    }

    private static boolean G0(String str, String str2) {
        return str.equals("*") || Arrays.asList(str.split(",")).contains(str2);
    }

    static boolean H0(String str) {
        return G0(F.v0.a(null), str);
    }

    private final Object I(int i2, Object obj, boolean z, boolean z2, String str) {
        Bundle bundleE;
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1L : 0L);
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            return J(String.valueOf(obj), i2, z);
        }
        if (!z2 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Parcelable parcelable : (Parcelable[]) obj) {
            if ((parcelable instanceof Bundle) && (bundleE = E((Bundle) parcelable, null)) != null && !bundleE.isEmpty()) {
                arrayList.add(bundleE);
            }
        }
        return arrayList.toArray(new Bundle[arrayList.size()]);
    }

    public static String J(String str, int i2, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i2) {
            return str;
        }
        if (!z) {
            return null;
        }
        return str.substring(0, str.offsetByCodePoints(0, i2)) + "...";
    }

    static boolean J0(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    private static void L(Bundle bundle, int i2, String str, Object obj) {
        if (v0(bundle, i2)) {
            bundle.putString("_ev", J(str, 40, true));
            if (obj != null) {
                C2254p.l(bundle);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", String.valueOf(obj).length());
                }
            }
        }
    }

    static boolean L0(String str) {
        C2254p.f(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    public static boolean N0(String str) {
        for (String str2 : j) {
            if (str2.equals(str)) {
                return false;
            }
        }
        return true;
    }

    private final int O0(String str) {
        if (!w0("event param", str)) {
            return 3;
        }
        if (o0("event param", null, str)) {
            return !j0("event param", 40, str) ? 3 : 0;
        }
        return 14;
    }

    private final int P0(String str) {
        if (!C0("event param", str)) {
            return 3;
        }
        if (o0("event param", null, str)) {
            return !j0("event param", 40, str) ? 3 : 0;
        }
        return 14;
    }

    private static int Q0(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        if ("_id".equals(str)) {
            return 256;
        }
        return "_lgclid".equals(str) ? 100 : 36;
    }

    private static boolean T0(String str) {
        C2254p.l(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    static MessageDigest V0() {
        for (int i2 = 0; i2 < 2; i2++) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                if (messageDigest != null) {
                    return messageDigest;
                }
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static void X(C6303d4 c6303d4, Bundle bundle, boolean z) {
        if (bundle == null || c6303d4 == null || (bundle.containsKey("_sc") && !z)) {
            if (bundle != null && c6303d4 == null && z) {
                bundle.remove("_sn");
                bundle.remove("_sc");
                bundle.remove("_si");
                return;
            }
            return;
        }
        String str = c6303d4.a;
        if (str != null) {
            bundle.putString("_sn", str);
        } else {
            bundle.remove("_sn");
        }
        String str2 = c6303d4.b;
        if (str2 != null) {
            bundle.putString("_sc", str2);
        } else {
            bundle.remove("_sc");
        }
        bundle.putLong("_si", c6303d4.c);
    }

    public static void Y(D5 d5, int i2, String str, String str2, int i3) {
        Z(d5, null, i2, str, str2, i3);
    }

    static void Z(D5 d5, String str, int i2, String str2, String str3, int i3) {
        Bundle bundle = new Bundle();
        v0(bundle, i2);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i2 == 6 || i2 == 7 || i2 == 2) {
            bundle.putLong("_el", i3);
        }
        d5.a(str, "_err", bundle);
    }

    @TargetApi(30)
    private final boolean Z0() {
        Integer num;
        if (this.g == null) {
            ax.p069e2.a aVarS0 = S0();
            boolean z = false;
            if (aVarS0 == null) {
                return false;
            }
            Integer num2 = null;
            try {
                num = aVarS0.b().get(10000L, TimeUnit.MILLISECONDS);
                if (num != null) {
                    try {
                        if (num.intValue() == 1) {
                            z = true;
                        }
                    } catch (InterruptedException e) {
                        e = e;
                        num2 = num;
                        j().L().b("Measurement manager api exception", e);
                        this.g = Boolean.FALSE;
                        num = num2;
                    } catch (CancellationException e2) {
                        e = e2;
                        num2 = num;
                        j().L().b("Measurement manager api exception", e);
                        this.g = Boolean.FALSE;
                        num = num2;
                    } catch (ExecutionException e3) {
                        e = e3;
                        num2 = num;
                        j().L().b("Measurement manager api exception", e);
                        this.g = Boolean.FALSE;
                        num = num2;
                    } catch (TimeoutException e4) {
                        e = e4;
                        num2 = num;
                        j().L().b("Measurement manager api exception", e);
                        this.g = Boolean.FALSE;
                        num = num2;
                    }
                }
                this.g = Boolean.valueOf(z);
            } catch (InterruptedException e5) {
                e = e5;
            } catch (CancellationException e6) {
                e = e6;
            } catch (ExecutionException e7) {
                e = e7;
            } catch (TimeoutException e8) {
                e = e8;
            }
            j().K().b("Measurement manager api status result", num);
        }
        return this.g.booleanValue();
    }

    private final void a0(String str, String str2, String str3, Bundle bundle, List<String> list, boolean z) {
        int iP0;
        int iX;
        if (bundle == null) {
            return;
        }
        int iX2 = d().x();
        int i2 = 0;
        for (String str4 : new TreeSet(bundle.keySet())) {
            if (list == 0 || !list.contains(str4)) {
                iP0 = !z ? P0(str4) : 0;
                if (iP0 == 0) {
                    iP0 = O0(str4);
                }
            } else {
                iP0 = 0;
            }
            if (iP0 != 0) {
                L(bundle, iP0, str4, iP0 == 3 ? str4 : null);
                bundle.remove(str4);
            } else {
                if (h0(bundle.get(str4))) {
                    j().M().d("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str2, str3, str4);
                    iX = 22;
                } else {
                    iX = x(str, str2, str4, bundle.get(str4), bundle, list, z, false);
                }
                if (iX != 0 && !"_ev".equals(str4)) {
                    L(bundle, iX, str4, bundle.get(str4));
                    bundle.remove(str4);
                } else if (L0(str4) && !n0(str4, ax.M6.p.d)) {
                    i2++;
                    if (!c0(231100000, true)) {
                        j().H().c("Item array not supported on client's version of Google Play Services (Android Only)", g().c(str2), g().a(bundle));
                        v0(bundle, 23);
                        bundle.remove(str4);
                    } else if (i2 > iX2) {
                        if (Z6.a() && d().t(F.y0)) {
                            j().H().c("Item can't contain more than " + iX2 + " item-scoped custom params", g().c(str2), g().a(bundle));
                            v0(bundle, 28);
                            bundle.remove(str4);
                        } else {
                            j().H().c("Item cannot contain custom parameters", g().c(str2), g().a(bundle));
                            v0(bundle, 23);
                            bundle.remove(str4);
                        }
                    }
                }
            }
        }
    }

    static boolean d0(Context context) {
        ActivityInfo receiverInfo;
        C2254p.l(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) == null || !receiverInfo.enabled) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static boolean e0(Context context, boolean z) {
        C2254p.l(context);
        return Build.VERSION.SDK_INT >= 24 ? B0(context, "com.google.android.gms.measurement.AppMeasurementJobService") : B0(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    static boolean f0(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    static boolean g0(Bundle bundle, int i2) {
        int i3 = 0;
        if (bundle.size() <= i2) {
            return false;
        }
        for (String str : new TreeSet(bundle.keySet())) {
            i3++;
            if (i3 > i2) {
                bundle.remove(str);
            }
        }
        return true;
    }

    static boolean h0(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    static boolean m0(String str, String str2, String str3, String str4) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        boolean zIsEmpty2 = TextUtils.isEmpty(str2);
        if (!zIsEmpty && !zIsEmpty2) {
            C2254p.l(str);
            return !str.equals(str2);
        }
        if (zIsEmpty && zIsEmpty2) {
            if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                return !TextUtils.isEmpty(str4);
            }
            return !str3.equals(str4);
        }
        if (zIsEmpty || !zIsEmpty2) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
        if (TextUtils.isEmpty(str4)) {
            return false;
        }
        return TextUtils.isEmpty(str3) || !str3.equals(str4);
    }

    private static boolean n0(String str, String[] strArr) {
        C2254p.l(strArr);
        for (String str2 : strArr) {
            if (Objects.equals(str, str2)) {
                return true;
            }
        }
        return false;
    }

    static byte[] q0(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }

    public static ArrayList<Bundle> t0(List<C6312f> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (C6312f c6312f : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", c6312f.q);
            bundle.putString("origin", c6312f.X);
            bundle.putLong("creation_timestamp", c6312f.Z);
            bundle.putString("name", c6312f.Y.X);
            ax.M6.n.b(bundle, C2254p.l(c6312f.Y.a()));
            bundle.putBoolean("active", c6312f.k0);
            String str = c6312f.l0;
            if (str != null) {
                bundle.putString("trigger_event_name", str);
            }
            D d = c6312f.m0;
            if (d != null) {
                bundle.putString("timed_out_event_name", d.q);
                C c = d.X;
                if (c != null) {
                    bundle.putBundle("timed_out_event_params", c.A());
                }
            }
            bundle.putLong("trigger_timeout", c6312f.n0);
            D d2 = c6312f.o0;
            if (d2 != null) {
                bundle.putString("triggered_event_name", d2.q);
                C c2 = d2.X;
                if (c2 != null) {
                    bundle.putBundle("triggered_event_params", c2.A());
                }
            }
            bundle.putLong("triggered_timestamp", c6312f.Y.Y);
            bundle.putLong("time_to_live", c6312f.p0);
            D d3 = c6312f.q0;
            if (d3 != null) {
                bundle.putString("expired_event_name", d3.q);
                C c3 = d3.X;
                if (c3 != null) {
                    bundle.putBundle("expired_event_params", c3.A());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    private final boolean u0(Context context, String str) {
        Signature[] signatureArr;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfoF = ax.p361w6.e.a(context).f(str, 64);
            if (packageInfoF == null || (signatureArr = packageInfoF.signatures) == null || signatureArr.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (PackageManager.NameNotFoundException e) {
            j().G().b("Package name not found", e);
            return true;
        } catch (CertificateException e2) {
            j().G().b("Error obtaining certificate", e2);
            return true;
        }
    }

    private static boolean v0(Bundle bundle, int i2) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", i2);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0084  */
    private final int x(String str, String str2, String str3, Object obj, Bundle bundle, List<String> list, boolean z, boolean z2) {
        int i2;
        int size;
        n();
        int i3 = 0;
        if (!h0(obj)) {
            i2 = 0;
        } else {
            if (!z2) {
                return 21;
            }
            if (!n0(str3, ax.M6.p.c)) {
                return 20;
            }
            if (!this.a.J().g0()) {
                return 25;
            }
            boolean z3 = obj instanceof Parcelable[];
            if (z3) {
                size = ((Parcelable[]) obj).length;
            } else if (obj instanceof ArrayList) {
                size = ((ArrayList) obj).size();
            } else {
                i2 = 0;
            }
            if (size > 200) {
                j().M().d("Parameter array is too long; discarded. Value kind, name, array length", "param", str3, Integer.valueOf(size));
                if (z3) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    if (parcelableArr.length > 200) {
                        bundle.putParcelableArray(str3, (Parcelable[]) Arrays.copyOf(parcelableArr, 200));
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    if (arrayList.size() > 200) {
                        bundle.putParcelableArrayList(str3, new ArrayList<>(arrayList.subList(0, 200)));
                    }
                }
                i2 = 17;
            } else {
                i2 = 0;
            }
        }
        if (l0("param", str3, (J0(str2) || J0(str3)) ? d().w(null, false) : d().r(null, false), obj)) {
            return i2;
        }
        if (!z2) {
            return 4;
        }
        if (obj instanceof Bundle) {
            a0(str, str2, str3, (Bundle) obj, list, z);
            return i2;
        }
        if (obj instanceof Parcelable[]) {
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if (!(parcelable instanceof Bundle)) {
                    j().M().c("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str3);
                    return 4;
                }
                a0(str, str2, str3, (Bundle) parcelable, list, z);
            }
            return i2;
        }
        if (!(obj instanceof ArrayList)) {
            return 4;
        }
        ArrayList arrayList2 = (ArrayList) obj;
        int size2 = arrayList2.size();
        while (i3 < size2) {
            Object obj2 = arrayList2.get(i3);
            int i4 = i3 + 1;
            if (!(obj2 instanceof Bundle)) {
                j().M().c("All ArrayList elements must be of type Bundle. Value type, name", obj2 != null ? obj2.getClass() : "null", str3);
                return 4;
            }
            a0(str, str2, str3, (Bundle) obj2, list, z);
            i3 = i4;
        }
        return i2;
    }

    static Bundle[] x0(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            return (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        }
        if (!(obj instanceof ArrayList)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) obj;
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    public static long y(long j2, long j3) {
        return (j2 + (j3 * 60000)) / 86400000;
    }

    static int y0() {
        if (Build.VERSION.SDK_INT < 30 || SdkExtensions.getExtensionVersion(30) <= 3) {
            return 0;
        }
        return SdkExtensions.getExtensionVersion(1000000);
    }

    final Object A0(String str, Object obj) {
        return "_ldl".equals(str) ? I(Q0(str), obj, true, false, null) : I(Q0(str), obj, false, false, null);
    }

    final Bundle C(Uri uri, boolean z) {
        String queryParameter;
        String queryParameter2;
        String queryParameter3;
        String queryParameter4;
        String queryParameter5;
        String queryParameter6;
        String queryParameter7;
        String queryParameter8;
        String queryParameter9;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                queryParameter = uri.getQueryParameter("utm_campaign");
                queryParameter2 = uri.getQueryParameter("utm_source");
                queryParameter3 = uri.getQueryParameter("utm_medium");
                queryParameter4 = uri.getQueryParameter("gclid");
                queryParameter5 = z ? uri.getQueryParameter("gbraid") : null;
                queryParameter6 = uri.getQueryParameter("utm_id");
                queryParameter7 = uri.getQueryParameter("dclid");
                queryParameter8 = uri.getQueryParameter("srsltid");
                queryParameter9 = uri.getQueryParameter("sfmc_id");
            } else {
                queryParameter = null;
                queryParameter2 = null;
                queryParameter3 = null;
                queryParameter4 = null;
                queryParameter5 = null;
                queryParameter6 = null;
                queryParameter7 = null;
                queryParameter8 = null;
                queryParameter9 = null;
            }
            if (TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4) && ((!z || TextUtils.isEmpty(queryParameter5)) && TextUtils.isEmpty(queryParameter6) && TextUtils.isEmpty(queryParameter7) && TextUtils.isEmpty(queryParameter8) && TextUtils.isEmpty(queryParameter9))) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("campaign", queryParameter);
            }
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString("source", queryParameter2);
            }
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString("medium", queryParameter3);
            }
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString("gclid", queryParameter4);
            }
            if (z && !TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("gbraid", queryParameter5);
            }
            String queryParameter10 = uri.getQueryParameter("gad_source");
            if (z && !TextUtils.isEmpty(queryParameter10)) {
                bundle.putString("gad_source", queryParameter10);
            }
            String queryParameter11 = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter11)) {
                bundle.putString("term", queryParameter11);
            }
            String queryParameter12 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter12)) {
                bundle.putString("content", queryParameter12);
            }
            String queryParameter13 = uri.getQueryParameter("aclid");
            if (!TextUtils.isEmpty(queryParameter13)) {
                bundle.putString("aclid", queryParameter13);
            }
            String queryParameter14 = uri.getQueryParameter("cp1");
            if (!TextUtils.isEmpty(queryParameter14)) {
                bundle.putString("cp1", queryParameter14);
            }
            String queryParameter15 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter15)) {
                bundle.putString("anid", queryParameter15);
            }
            if (!TextUtils.isEmpty(queryParameter6)) {
                bundle.putString("campaign_id", queryParameter6);
            }
            if (!TextUtils.isEmpty(queryParameter7)) {
                bundle.putString("dclid", queryParameter7);
            }
            String queryParameter16 = uri.getQueryParameter("utm_source_platform");
            if (!TextUtils.isEmpty(queryParameter16)) {
                bundle.putString("source_platform", queryParameter16);
            }
            String queryParameter17 = uri.getQueryParameter("utm_creative_format");
            if (!TextUtils.isEmpty(queryParameter17)) {
                bundle.putString("creative_format", queryParameter17);
            }
            String queryParameter18 = uri.getQueryParameter("utm_marketing_tactic");
            if (!TextUtils.isEmpty(queryParameter18)) {
                bundle.putString("marketing_tactic", queryParameter18);
            }
            if (!TextUtils.isEmpty(queryParameter8)) {
                bundle.putString("srsltid", queryParameter8);
            }
            if (!TextUtils.isEmpty(queryParameter9)) {
                bundle.putString("sfmc_id", queryParameter9);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            j().L().b("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    final boolean C0(String str, String str2) {
        if (str2 == null) {
            j().H().b("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            j().H().b("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt)) {
            j().H().c("Name must start with a letter. Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                j().H().c("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    final Bundle E(Bundle bundle, String str) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str2 : bundle.keySet()) {
                Object objS0 = s0(str2, bundle.get(str2));
                if (objS0 == null) {
                    j().M().b("Param value can't be null", g().f(str2));
                } else {
                    O(bundle2, str2, objS0);
                }
            }
        }
        return bundle2;
    }

    final boolean E0(String str, String str2) {
        if (t7.a() && d().t(F.u0) && !TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return d().P().equals(str);
    }

    final Bundle F(String str, String str2, Bundle bundle, List<String> list, boolean z) {
        int iP0;
        boolean zN0 = n0(str2, ax.M6.q.d);
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        int iH = d().H();
        int i2 = 0;
        for (String str3 : new TreeSet(bundle.keySet())) {
            if (list == 0 || !list.contains(str3)) {
                iP0 = !z ? P0(str3) : 0;
                if (iP0 == 0) {
                    iP0 = O0(str3);
                }
            } else {
                iP0 = 0;
            }
            if (iP0 != 0) {
                L(bundle2, iP0, str3, iP0 == 3 ? str3 : null);
                bundle2.remove(str3);
            } else {
                int iX = x(str, str2, str3, bundle.get(str3), bundle2, list, z, zN0);
                if (iX == 17) {
                    L(bundle2, iX, str3, Boolean.FALSE);
                } else if (iX != 0 && !"_ev".equals(str3)) {
                    L(bundle2, iX, iX == 21 ? str2 : str3, bundle.get(str3));
                    bundle2.remove(str3);
                }
                if (L0(str3) && (i2 = i2 + 1) > iH) {
                    j().H().c("Event can't contain more than " + iH + " params", g().c(str2), g().a(bundle));
                    v0(bundle2, 5);
                    bundle2.remove(str3);
                }
            }
        }
        return bundle2;
    }

    final boolean F0(String str) {
        n();
        if (ax.p361w6.e.a(a()).a(str) == 0) {
            return true;
        }
        j().F().b("Permission not granted", str);
        return false;
    }

    final D H(String str, String str2, Bundle bundle, String str3, long j2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (v(str2) != 0) {
            j().G().b("Invalid conditional property event name", g().g(str2));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str3);
        Bundle bundleF = F(str, str2, bundle2, ax.p345v6.g.a("_o"), true);
        if (z) {
            bundleF = E(bundleF, str);
        }
        C2254p.l(bundleF);
        return new D(str2, new C(bundleF), str3, j2);
    }

    public final int I0() {
        if (this.h == null) {
            this.h = Integer.valueOf(C2177h.f().a(a()) / 1000);
        }
        return this.h.intValue();
    }

    public final URL K(long j2, String str, String str2, long j3, String str3) {
        try {
            C2254p.f(str2);
            C2254p.f(str);
            String strConcat = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", String.format("v%s.%s", Long.valueOf(j2), Integer.valueOf(I0())), str2, str, Long.valueOf(j3));
            if (str.equals(d().Q())) {
                strConcat = strConcat.concat("&ddl_test=1");
            }
            if (!str3.isEmpty()) {
                if (str3.charAt(0) != '&') {
                    strConcat = strConcat.concat("&");
                }
                strConcat = strConcat.concat(str3);
            }
            return new URL(strConcat);
        } catch (IllegalArgumentException e) {
            e = e;
            j().G().b("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        } catch (MalformedURLException e2) {
            e = e2;
            j().G().b("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        }
    }

    final long K0() {
        long j2;
        n();
        if (!D0(this.a.B().F())) {
            return 0L;
        }
        if (Build.VERSION.SDK_INT < 30) {
            j2 = 4;
        } else if (SdkExtensions.getExtensionVersion(30) < 4) {
            j2 = 8;
        } else {
            j2 = y0() < F.W.a(null).intValue() ? 16L : 0L;
        }
        if (!F0("android.permission.ACCESS_ADSERVICES_ATTRIBUTION")) {
            j2 |= 2;
        }
        if (j2 == 0 && !Z0()) {
            j2 |= 64;
        }
        if (j2 == 0) {
            return 1L;
        }
        return j2;
    }

    final void M(Bundle bundle, long j2) {
        long j3 = bundle.getLong("_et");
        if (j3 != 0) {
            j().L().b("Params already contained engagement", Long.valueOf(j3));
        }
        bundle.putLong("_et", j2 + j3);
    }

    public final boolean M0(String str) {
        List<ResolveInfo> listQueryIntentActivities;
        return (TextUtils.isEmpty(str) || (listQueryIntentActivities = a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0)) == null || listQueryIntentActivities.isEmpty()) ? false : true;
    }

    final void N(Bundle bundle, Bundle bundle2) {
        if (bundle2 == null) {
            return;
        }
        for (String str : bundle2.keySet()) {
            if (!bundle.containsKey(str)) {
                i().O(bundle, str, bundle2.get(str));
            }
        }
    }

    final void O(Bundle bundle, String str, Object obj) {
        if (bundle == null) {
            return;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof String) {
            bundle.putString(str, String.valueOf(obj));
            return;
        }
        if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            bundle.putParcelableArray(str, (Bundle[]) obj);
        } else if (str != null) {
            j().M().c("Not putting event parameter. Invalid value type. name, type", g().f(str), obj != null ? obj.getClass().getSimpleName() : null);
        }
    }

    public final void P(ax.H6.M0 m0, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i2);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning int value to wrapper", e);
        }
    }

    public final void Q(ax.H6.M0 m0, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j2);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning long value to wrapper", e);
        }
    }

    public final void R(ax.H6.M0 m0, Bundle bundle) {
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning bundle value to wrapper", e);
        }
    }

    public final long R0() {
        long andIncrement;
        long j2;
        if (this.d.get() != 0) {
            synchronized (this.d) {
                this.d.compareAndSet(-1L, 1L);
                andIncrement = this.d.getAndIncrement();
            }
            return andIncrement;
        }
        synchronized (this.d) {
            long jNextLong = new Random(System.nanoTime() ^ b().a()).nextLong();
            int i2 = this.e + 1;
            this.e = i2;
            j2 = jNextLong + ((long) i2);
        }
        return j2;
    }

    public final void S(ax.H6.M0 m0, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning string value to wrapper", e);
        }
    }

    final ax.p069e2.a S0() {
        if (this.f == null) {
            this.f = ax.p069e2.a.a(a());
        }
        return this.f;
    }

    public final void T(ax.H6.M0 m0, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning bundle list to wrapper", e);
        }
    }

    public final void U(ax.H6.M0 m0, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning boolean value to wrapper", e);
        }
    }

    final String U0() {
        byte[] bArr = new byte[16];
        W0().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    public final void V(ax.H6.M0 m0, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.a.j().L().b("Error returning byte array to wrapper", e);
        }
    }

    final void W(C6280a2 c6280a2, int i2) {
        int i3 = 0;
        for (String str : new TreeSet(c6280a2.d.keySet())) {
            if (L0(str) && (i3 = i3 + 1) > i2) {
                j().H().c("Event can't contain more than " + i2 + " params", g().c(c6280a2.a), g().a(c6280a2.d));
                v0(c6280a2.d, 5);
                c6280a2.d.remove(str);
            }
        }
    }

    final SecureRandom W0() {
        n();
        if (this.c == null) {
            this.c = new SecureRandom();
        }
        return this.c;
    }

    final boolean X0() {
        n();
        return K0() == 1;
    }

    public final boolean Y0() {
        try {
            a().getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    final void b0(Parcelable[] parcelableArr, int i2, boolean z) {
        C2254p.l(parcelableArr);
        for (Parcelable parcelable : parcelableArr) {
            Bundle bundle = (Bundle) parcelable;
            int i3 = 0;
            for (String str : new TreeSet(bundle.keySet())) {
                if (L0(str) && !n0(str, ax.M6.p.d) && (i3 = i3 + 1) > i2) {
                    if (z) {
                        j().H().c("Param can't contain more than " + i2 + " item-scoped custom parameters", g().f(str), g().a(bundle));
                        v0(bundle, 28);
                    } else {
                        j().H().c("Param cannot contain item-scoped custom parameters", g().f(str), g().a(bundle));
                        v0(bundle, 23);
                    }
                    bundle.remove(str);
                }
            }
        }
    }

    public final boolean c0(int i2, boolean z) {
        Boolean boolW = this.a.J().W();
        if (I0() < i2 / 1000) {
            return (boolW == null || boolW.booleanValue()) ? false : true;
        }
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @SuppressLint({"ApplySharedPref"})
    final boolean i0(String str, double d) {
        try {
            SharedPreferences.Editor editorEdit = a().getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
            editorEdit.putString("deeplink", str);
            editorEdit.putLong("timestamp", Double.doubleToRawLongBits(d));
            return editorEdit.commit();
        } catch (RuntimeException e) {
            j().G().b("Failed to persist Deferred Deep Link. exception", e);
            return false;
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    final boolean j0(String str, int i2, String str2) {
        if (str2 == null) {
            j().H().b("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.codePointCount(0, str2.length()) <= i2) {
            return true;
        }
        j().H().d("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i2), str2);
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    final boolean k0(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (T0(str)) {
                return true;
            }
            if (this.a.r()) {
                j().H().b("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", V1.v(str));
            }
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            if (this.a.r()) {
                j().H().a("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        }
        if (T0(str2)) {
            return true;
        }
        j().H().b("Invalid admob_app_id. Analytics disabled.", V1.v(str2));
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    final boolean l0(String str, String str2, int i2, Object obj) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
                return false;
            }
            String strValueOf = String.valueOf(obj);
            if (strValueOf.codePointCount(0, strValueOf.length()) > i2) {
                j().M().d("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(strValueOf.length()));
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6309e3
    protected final void o() {
        n();
        SecureRandom secureRandom = new SecureRandom();
        long jNextLong = secureRandom.nextLong();
        if (jNextLong == 0) {
            jNextLong = secureRandom.nextLong();
            if (jNextLong == 0) {
                j().L().a("Utils falling back to Random for random id");
            }
        }
        this.d.set(jNextLong);
    }

    final boolean o0(String str, String[] strArr, String str2) {
        return p0(str, strArr, null, str2);
    }

    final boolean p0(String str, String[] strArr, String[] strArr2, String str2) {
        if (str2 == null) {
            j().H().b("Name is required and can't be null. Type", str);
            return false;
        }
        C2254p.l(str2);
        for (String str3 : i) {
            if (str2.startsWith(str3)) {
                j().H().c("Name starts with reserved prefix. Type, name", str, str2);
                return false;
            }
        }
        if (strArr == null || !n0(str2, strArr)) {
            return true;
        }
        if (strArr2 != null && n0(str2, strArr2)) {
            return true;
        }
        j().H().c("Name is reserved. Type, name", str, str2);
        return false;
    }

    final int r0(String str) {
        if (!w0("user property", str)) {
            return 6;
        }
        if (o0("user property", ax.M6.s.a, str)) {
            return !j0("user property", 24, str) ? 6 : 0;
        }
        return 15;
    }

    final Object s0(String str, Object obj) {
        if ("_ev".equals(str)) {
            return I(d().w(null, false), obj, true, true, null);
        }
        return I(J0(str) ? d().w(null, false) : d().r(null, false), obj, false, true, null);
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6309e3
    protected final boolean t() {
        return true;
    }

    public final int u(int i2) {
        return C2177h.f().h(a(), 12451000);
    }

    final int v(String str) {
        if (!w0("event", str)) {
            return 2;
        }
        if (p0("event", ax.M6.q.a, ax.M6.q.b, str)) {
            return !j0("event", 40, str) ? 2 : 0;
        }
        return 13;
    }

    final int w(String str, Object obj) {
        return "_ldl".equals(str) ? l0("user property referrer", str, Q0(str), obj) : l0("user property", str, Q0(str), obj) ? 0 : 7;
    }

    final boolean w0(String str, String str2) {
        if (str2 == null) {
            j().H().b("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            j().H().b("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt) && iCodePointAt != 95) {
            j().H().c("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                j().H().c("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    final long z(Context context, String str) {
        n();
        C2254p.l(context);
        C2254p.f(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest messageDigestV0 = V0();
        if (messageDigestV0 == null) {
            j().G().a("Could not get MD5 instance");
            return -1L;
        }
        if (packageManager == null) {
            return 0L;
        }
        try {
            if (u0(context, str)) {
                return 0L;
            }
            Signature[] signatureArr = ax.p361w6.e.a(context).f(a().getPackageName(), 64).signatures;
            if (signatureArr != null && signatureArr.length > 0) {
                return B(messageDigestV0.digest(signatureArr[0].toByteArray()));
            }
            j().L().a("Could not get signatures");
            return -1L;
        } catch (PackageManager.NameNotFoundException e) {
            j().G().b("Package name not found", e);
            return 0L;
        }
    }

    final long z0(String str) {
        if (a().getPackageManager() == null) {
            return 0L;
        }
        int i2 = 0;
        try {
            ApplicationInfo applicationInfoC = ax.p361w6.e.a(a()).c(str, 0);
            if (applicationInfoC != null) {
                i2 = applicationInfoC.targetSdkVersion;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            j().J().b("PackageManager failed to find running app: app_id", str);
        }
        return i2;
    }
}
