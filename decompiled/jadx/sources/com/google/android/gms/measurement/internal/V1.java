package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import ax.H6.N6;
import ax.p265q6.C2254p;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class V1 extends AbstractC6309e3 {
    private char c;
    private long d;
    private String e;
    private final X1 f;
    private final X1 g;
    private final X1 h;
    private final X1 i;
    private final X1 j;
    private final X1 k;
    private final X1 l;
    private final X1 m;
    private final X1 n;

    V1(E2 e2) {
        super(e2);
        this.c = (char) 0;
        this.d = -1L;
        this.f = new X1(this, 6, false, false);
        this.g = new X1(this, 6, true, false);
        this.h = new X1(this, 6, false, true);
        this.i = new X1(this, 5, false, false);
        this.j = new X1(this, 5, true, false);
        this.k = new X1(this, 5, false, true);
        this.l = new X1(this, 4, false, false);
        this.m = new X1(this, 3, false, false);
        this.n = new X1(this, 2, false, false);
    }

    private static String E(String str) {
        if (TextUtils.isEmpty(str)) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf == -1) {
            return (N6.a() && F.x0.a(null).booleanValue()) ? HttpUrl.FRAGMENT_ENCODE_SET : str;
        }
        return str.substring(0, iLastIndexOf);
    }

    private final String P() {
        String str;
        synchronized (this) {
            try {
                if (this.e == null) {
                    this.e = this.a.O() != null ? this.a.O() : "FA";
                }
                C2254p.l(this.e);
                str = this.e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    protected static Object v(String str) {
        if (str == null) {
            return null;
        }
        return new W1(str);
    }

    private static String w(boolean z, Object obj) {
        String className;
        String str = HttpUrl.FRAGMENT_ENCODE_SET;
        if (obj == null) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            if (String.valueOf(obj).charAt(0) == '-') {
                str = "-";
            }
            String strValueOf = String.valueOf(Math.abs(l.longValue()));
            return str + Math.round(Math.pow(10.0d, strValueOf.length() - 1)) + "..." + str + Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
        }
        if (obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        if (!(obj instanceof Throwable)) {
            if (obj instanceof W1) {
                return ((W1) obj).a;
            }
            return z ? "-" : String.valueOf(obj);
        }
        Throwable th = (Throwable) obj;
        StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
        String strE = E(E2.class.getCanonicalName());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && E(className).equals(strE)) {
                sb.append(": ");
                sb.append(stackTraceElement);
                break;
            }
        }
        return sb.toString();
    }

    static String x(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        String strW = w(z, obj);
        String strW2 = w(z, obj2);
        String strW3 = w(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(strW)) {
            sb.append(str2);
            sb.append(strW);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(strW2)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(strW2);
        }
        if (!TextUtils.isEmpty(strW3)) {
            sb.append(str3);
            sb.append(strW3);
        }
        return sb.toString();
    }

    protected final boolean C(int i) {
        Log.isLoggable(P(), i);
        return false;
    }

    public final X1 F() {
        return this.m;
    }

    public final X1 G() {
        return this.f;
    }

    public final X1 H() {
        return this.h;
    }

    public final X1 I() {
        return this.g;
    }

    public final X1 J() {
        return this.l;
    }

    public final X1 K() {
        return this.n;
    }

    public final X1 L() {
        return this.i;
    }

    public final X1 M() {
        return this.k;
    }

    public final X1 N() {
        return this.j;
    }

    public final String O() {
        Pair<String, Long> pairA;
        if (h().f == null || (pairA = h().f.a()) == null || pairA == C6322g2.B) {
            return null;
        }
        return String.valueOf(pairA.second) + ":" + ((String) pairA.first);
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
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

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
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
    protected final boolean t() {
        return false;
    }

    protected final void y(int i, String str) {
        Log.println(i, P(), str);
    }

    protected final void z(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && C(i)) {
            y(i, x(false, str, obj, obj2, obj3));
        }
        if (z2 || i < 5) {
            return;
        }
        C2254p.l(str);
        C6442z2 c6442z2G = this.a.G();
        if (c6442z2G == null) {
            y(6, "Scheduler not set. Not logging error/warn");
        } else {
            if (!c6442z2G.s()) {
                y(6, "Scheduler not initialized. Not logging error/warn");
                return;
            }
            if (i < 0) {
                i = 0;
            }
            c6442z2G.D(new U1(this, i >= 9 ? 8 : i, str, obj, obj2, obj3));
        }
    }
}
