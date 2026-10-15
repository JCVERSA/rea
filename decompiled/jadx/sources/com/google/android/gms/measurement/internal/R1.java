package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import ax.p265q6.C2254p;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class R1 {
    private static final AtomicReference<String[]> b = new AtomicReference<>();
    private static final AtomicReference<String[]> c = new AtomicReference<>();
    private static final AtomicReference<String[]> d = new AtomicReference<>();
    private final ax.M6.h a;

    public R1(ax.M6.h hVar) {
        this.a = hVar;
    }

    private static String d(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        C2254p.l(strArr);
        C2254p.l(strArr2);
        C2254p.l(atomicReference);
        C2254p.a(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (Objects.equals(str, strArr[i])) {
                synchronized (atomicReference) {
                    try {
                        String[] strArr3 = atomicReference.get();
                        if (strArr3 == null) {
                            strArr3 = new String[strArr2.length];
                            atomicReference.set(strArr3);
                        }
                        if (strArr3[i] == null) {
                            strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                        }
                        str2 = strArr3[i];
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str2;
            }
        }
        return str;
    }

    private final String e(Object[] objArr) {
        if (objArr == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objArr) {
            String strA = obj instanceof Bundle ? a((Bundle) obj) : String.valueOf(obj);
            if (strA != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(strA);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    protected final String a(Bundle bundle) {
        String strE;
        if (bundle == null) {
            return null;
        }
        if (!this.a.a()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(f(str));
            sb.append("=");
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                strE = e(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                strE = e((Object[]) obj);
            } else {
                strE = obj instanceof ArrayList ? e(((ArrayList) obj).toArray()) : String.valueOf(obj);
            }
            sb.append(strE);
        }
        sb.append("}]");
        return sb.toString();
    }

    protected final String b(D d2) {
        String string = null;
        if (d2 == null) {
            return null;
        }
        if (!this.a.a()) {
            return d2.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(d2.Y);
        sb.append(",name=");
        sb.append(c(d2.q));
        sb.append(",params=");
        C c2 = d2.X;
        if (c2 != null) {
            string = !this.a.a() ? c2.toString() : a(c2.A());
        }
        sb.append(string);
        return sb.toString();
    }

    protected final String c(String str) {
        if (str == null) {
            return null;
        }
        return !this.a.a() ? str : d(str, ax.M6.q.c, ax.M6.q.a, b);
    }

    protected final String f(String str) {
        if (str == null) {
            return null;
        }
        return !this.a.a() ? str : d(str, ax.M6.p.b, ax.M6.p.a, c);
    }

    protected final String g(String str) {
        if (str == null) {
            return null;
        }
        if (!this.a.a()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return d(str, ax.M6.s.b, ax.M6.s.a, d);
        }
        return "experiment_id(" + str + ")";
    }
}
