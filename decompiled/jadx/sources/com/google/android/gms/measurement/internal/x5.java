package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import ax.H6.AbstractC0994m4;
import ax.H6.C0884a2;
import ax.H6.C0893b2;
import ax.H6.C0911d2;
import ax.H6.C0917e;
import ax.H6.C0929f2;
import ax.H6.C0938g2;
import ax.H6.C0956i2;
import ax.H6.C0965j2;
import ax.H6.C0974k2;
import ax.H6.C0997m7;
import ax.H6.C1058u4;
import ax.H6.C1060u6;
import ax.H6.O6;
import ax.H6.n7;
import ax.H6.t7;
import ax.p265q6.C2254p;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class x5 extends AbstractC6353k5 {
    x5(q5 q5Var) {
        super(q5Var);
    }

    static Bundle B(List<C0911d2> list) {
        Bundle bundle = new Bundle();
        for (C0911d2 c0911d2 : list) {
            String strG0 = c0911d2.g0();
            if (c0911d2.j0()) {
                bundle.putDouble(strG0, c0911d2.J());
            } else if (c0911d2.k0()) {
                bundle.putFloat(strG0, c0911d2.V());
            } else if (c0911d2.n0()) {
                bundle.putString(strG0, c0911d2.h0());
            } else if (c0911d2.l0()) {
                bundle.putLong(strG0, c0911d2.b0());
            }
        }
        return bundle;
    }

    private final Bundle C(Map<String, Object> map, boolean z) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    arrayList2.add(C((Map) obj2, false));
                }
                bundle.putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
            }
        }
        return bundle;
    }

    static C0911d2 F(C0893b2 c0893b2, String str) {
        for (C0911d2 c0911d2 : c0893b2.g0()) {
            if (c0911d2.g0().equals(str)) {
                return c0911d2;
            }
        }
        return null;
    }

    static <BuilderT extends ax.H6.V4> BuilderT G(BuilderT buildert, byte[] bArr) throws C1058u4 {
        ax.H6.Y3 y3A = ax.H6.Y3.a();
        return y3A != null ? (BuilderT) buildert.R(bArr, y3A) : (BuilderT) buildert.p(bArr);
    }

    static Object J(C0893b2 c0893b2, String str, Object obj) {
        Object objG0 = g0(c0893b2, str);
        return objG0 == null ? obj : objG0;
    }

    private static String N(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    static List<Long> O(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    private static void R(Uri.Builder builder, String str, String str2, Set<String> set) {
        if (set.contains(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        builder.appendQueryParameter(str, str2);
    }

    private static void S(Uri.Builder builder, String[] strArr, Bundle bundle, Set<String> set) {
        for (String str : strArr) {
            String[] strArrSplit = str.split(",");
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[strArrSplit.length - 1];
            String string = bundle.getString(str2);
            if (string != null) {
                R(builder, str3, string, set);
            }
        }
    }

    static void T(C0893b2.a aVar, String str, Object obj) {
        List<C0911d2> listN = aVar.N();
        int i = 0;
        while (true) {
            if (i >= listN.size()) {
                i = -1;
                break;
            } else if (str.equals(listN.get(i).g0())) {
                break;
            } else {
                i++;
            }
        }
        C0911d2.a aVarD = C0911d2.d0().D(str);
        if (obj instanceof Long) {
            aVarD.z(((Long) obj).longValue());
        } else if (obj instanceof String) {
            aVarD.F((String) obj);
        } else if (obj instanceof Double) {
            aVarD.w(((Double) obj).doubleValue());
        }
        if (i >= 0) {
            aVar.z(i, aVarD);
        } else {
            aVar.D(aVarD);
        }
    }

    private static void X(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void Y(StringBuilder sb, int i, ax.H6.E1 e1) {
        if (e1 == null) {
            return;
        }
        X(sb, i);
        sb.append("filter {\n");
        if (e1.S()) {
            b0(sb, i, "complement", Boolean.valueOf(e1.Q()));
        }
        if (e1.U()) {
            b0(sb, i, "param_name", g().f(e1.P()));
        }
        if (e1.V()) {
            int i2 = i + 1;
            ax.H6.H1 h1O = e1.O();
            if (h1O != null) {
                X(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (h1O.S()) {
                    b0(sb, i2, "match_type", h1O.J().name());
                }
                if (h1O.Q()) {
                    b0(sb, i2, "expression", h1O.M());
                }
                if (h1O.P()) {
                    b0(sb, i2, "case_sensitive", Boolean.valueOf(h1O.O()));
                }
                if (h1O.n() > 0) {
                    X(sb, i + 2);
                    sb.append("expression_list {\n");
                    for (String str : h1O.N()) {
                        X(sb, i + 3);
                        sb.append(str);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                X(sb, i2);
                sb.append("}\n");
            }
        }
        if (e1.T()) {
            Z(sb, i + 1, "number_filter", e1.N());
        }
        X(sb, i);
        sb.append("}\n");
    }

    private static void Z(StringBuilder sb, int i, String str, ax.H6.F1 f1) {
        if (f1 == null) {
            return;
        }
        X(sb, i);
        sb.append(str);
        sb.append(" {\n");
        if (f1.Q()) {
            b0(sb, i, "comparison_type", f1.J().name());
        }
        if (f1.T()) {
            b0(sb, i, "match_as_float", Boolean.valueOf(f1.P()));
        }
        if (f1.S()) {
            b0(sb, i, "comparison_value", f1.M());
        }
        if (f1.V()) {
            b0(sb, i, "min_comparison_value", f1.O());
        }
        if (f1.U()) {
            b0(sb, i, "max_comparison_value", f1.N());
        }
        X(sb, i);
        sb.append("}\n");
    }

    private static void a0(StringBuilder sb, int i, String str, C0956i2 c0956i2) {
        if (c0956i2 == null) {
            return;
        }
        X(sb, 3);
        sb.append(str);
        sb.append(" {\n");
        if (c0956i2.M() != 0) {
            X(sb, 4);
            sb.append("results: ");
            int i2 = 0;
            for (Long l : c0956i2.d0()) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    sb.append(", ");
                }
                sb.append(l);
                i2 = i3;
            }
            sb.append('\n');
        }
        if (c0956i2.V() != 0) {
            X(sb, 4);
            sb.append("status: ");
            int i4 = 0;
            for (Long l2 : c0956i2.f0()) {
                int i5 = i4 + 1;
                if (i4 != 0) {
                    sb.append(", ");
                }
                sb.append(l2);
                i4 = i5;
            }
            sb.append('\n');
        }
        if (c0956i2.n() != 0) {
            X(sb, 4);
            sb.append("dynamic_filter_timestamps: {");
            int i6 = 0;
            for (C0884a2 c0884a2 : c0956i2.c0()) {
                int i7 = i6 + 1;
                if (i6 != 0) {
                    sb.append(", ");
                }
                sb.append(c0884a2.S() ? Integer.valueOf(c0884a2.n()) : null);
                sb.append(":");
                sb.append(c0884a2.Q() ? Long.valueOf(c0884a2.N()) : null);
                i6 = i7;
            }
            sb.append("}\n");
        }
        if (c0956i2.Q() != 0) {
            X(sb, 4);
            sb.append("sequence_filter_timestamps: {");
            int i8 = 0;
            for (C0965j2 c0965j2 : c0956i2.e0()) {
                int i9 = i8 + 1;
                if (i8 != 0) {
                    sb.append(", ");
                }
                sb.append(c0965j2.T() ? Integer.valueOf(c0965j2.N()) : null);
                sb.append(": [");
                Iterator<Long> it = c0965j2.S().iterator();
                int i10 = 0;
                while (it.hasNext()) {
                    long jLongValue = it.next().longValue();
                    int i11 = i10 + 1;
                    if (i10 != 0) {
                        sb.append(", ");
                    }
                    sb.append(jLongValue);
                    i10 = i11;
                }
                sb.append("]");
                i8 = i9;
            }
            sb.append("}\n");
        }
        X(sb, 3);
        sb.append("}\n");
    }

    private static void b0(StringBuilder sb, int i, String str, Object obj) {
        if (obj == null) {
            return;
        }
        X(sb, i + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    private final void c0(StringBuilder sb, int i, List<C0911d2> list) {
        if (list == null) {
            return;
        }
        int i2 = i + 1;
        for (C0911d2 c0911d2 : list) {
            if (c0911d2 != null) {
                X(sb, i2);
                sb.append("param {\n");
                b0(sb, i2, "name", c0911d2.m0() ? g().f(c0911d2.g0()) : null);
                b0(sb, i2, "string_value", c0911d2.n0() ? c0911d2.h0() : null);
                b0(sb, i2, "int_value", c0911d2.l0() ? Long.valueOf(c0911d2.b0()) : null);
                b0(sb, i2, "double_value", c0911d2.j0() ? Double.valueOf(c0911d2.J()) : null);
                if (c0911d2.Z() > 0) {
                    c0(sb, i2, c0911d2.i0());
                }
                X(sb, i2);
                sb.append("}\n");
            }
        }
    }

    static boolean e0(D d, E5 e5) {
        C2254p.l(d);
        C2254p.l(e5);
        return (TextUtils.isEmpty(e5.X) && TextUtils.isEmpty(e5.w0)) ? false : true;
    }

    static boolean f0(List<Long> list, int i) {
        if (i < (list.size() << 6)) {
            return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
        }
        return false;
    }

    static Object g0(C0893b2 c0893b2, String str) {
        C0911d2 c0911d2F = F(c0893b2, str);
        if (c0911d2F == null) {
            return null;
        }
        if (c0911d2F.n0()) {
            return c0911d2F.h0();
        }
        if (c0911d2F.l0()) {
            return Long.valueOf(c0911d2F.b0());
        }
        if (c0911d2F.j0()) {
            return Double.valueOf(c0911d2F.J());
        }
        if (c0911d2F.Z() <= 0) {
            return null;
        }
        List<C0911d2> listI0 = c0911d2F.i0();
        ArrayList arrayList = new ArrayList();
        for (C0911d2 c0911d2 : listI0) {
            if (c0911d2 != null) {
                Bundle bundle = new Bundle();
                for (C0911d2 c0911d3 : c0911d2.i0()) {
                    if (c0911d3.n0()) {
                        bundle.putString(c0911d3.g0(), c0911d3.h0());
                    } else if (c0911d3.l0()) {
                        bundle.putLong(c0911d3.g0(), c0911d3.b0());
                    } else if (c0911d3.j0()) {
                        bundle.putDouble(c0911d3.g0(), c0911d3.J());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    static boolean h0(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static int y(C0938g2.a aVar, String str) {
        if (aVar == null) {
            return -1;
        }
        for (int i = 0; i < aVar.g0(); i++) {
            if (str.equals(aVar.Q0(i).d0())) {
                return i;
            }
        }
        return -1;
    }

    final long A(byte[] bArr) {
        C2254p.l(bArr);
        i().n();
        MessageDigest messageDigestV0 = B5.V0();
        if (messageDigestV0 != null) {
            return B5.B(messageDigestV0.digest(bArr));
        }
        j().G().a("Failed to get MD5");
        return 0L;
    }

    final <T extends Parcelable> T D(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } catch (ax.r6.b.a unused) {
            j().G().a("Failed to load parcelable from buffer");
            return null;
        } finally {
            parcelObtain.recycle();
        }
    }

    final C0893b2 E(A a) {
        C0893b2.a aVarC = C0893b2.d0().C(a.e);
        for (String str : a.f) {
            C0911d2.a aVarD = C0911d2.d0().D(str);
            Object objH = a.f.H(str);
            C2254p.l(objH);
            U(aVarD, objH);
            aVarC.D(aVarD);
        }
        return (C0893b2) ((AbstractC0994m4) aVarC.y());
    }

    final D H(C0917e c0917e) {
        Object obj;
        Bundle bundleC = C(c0917e.g(), true);
        String string = (!bundleC.containsKey("_o") || (obj = bundleC.get("_o")) == null) ? "app" : obj.toString();
        String strB = ax.M6.q.b(c0917e.e());
        if (strB == null) {
            strB = c0917e.e();
        }
        return new D(strB, new C(bundleC), string, c0917e.a());
    }

    @TargetApi(30)
    final C6332h5 I(String str, C0938g2.a aVar, C0893b2.a aVar2, String str2) throws Throwable {
        int iIndexOf;
        if (!C0997m7.a() || !d().E(str, F.A0)) {
            return null;
        }
        long jA = b().a();
        String[] strArrSplit = d().C(str, F.f0).split(",");
        HashSet hashSet = new HashSet(strArrSplit.length);
        for (String str3 : strArrSplit) {
            Objects.requireNonNull(str3);
            if (!hashSet.add(str3)) {
                throw new IllegalArgumentException("duplicate element: " + ((Object) str3));
            }
        }
        Set setUnmodifiableSet = DesugarCollections.unmodifiableSet(hashSet);
        o5 o5VarT = t();
        String strR = o5VarT.r().R(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(o5VarT.d().C(str, F.Y));
        if (TextUtils.isEmpty(strR)) {
            builder.authority(o5VarT.d().C(str, F.Z));
        } else {
            builder.authority(strR + "." + o5VarT.d().C(str, F.Z));
        }
        builder.path(o5VarT.d().C(str, F.a0));
        R(builder, "gmp_app_id", aVar.n1(), setUnmodifiableSet);
        R(builder, "gmp_version", "97001", setUnmodifiableSet);
        String strK1 = aVar.k1();
        C6326h c6326hD = d();
        M1<Boolean> m1 = F.D0;
        if (c6326hD.E(str, m1) && r().b0(str)) {
            strK1 = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        R(builder, "app_instance_id", strK1, setUnmodifiableSet);
        R(builder, "rdid", aVar.p1(), setUnmodifiableSet);
        R(builder, "bundle_id", aVar.j1(), setUnmodifiableSet);
        String strM = aVar2.M();
        String strA = ax.M6.q.a(strM);
        if (!TextUtils.isEmpty(strA)) {
            strM = strA;
        }
        R(builder, "app_event_name", strM, setUnmodifiableSet);
        R(builder, "app_version", String.valueOf(aVar.T()), setUnmodifiableSet);
        String strO1 = aVar.o1();
        if (d().E(str, m1) && r().f0(str) && !TextUtils.isEmpty(strO1) && (iIndexOf = strO1.indexOf(".")) != -1) {
            strO1 = strO1.substring(0, iIndexOf);
        }
        R(builder, "os_version", strO1, setUnmodifiableSet);
        R(builder, "timestamp", String.valueOf(aVar2.K()), setUnmodifiableSet);
        if (aVar.Q()) {
            R(builder, "lat", "1", setUnmodifiableSet);
        }
        R(builder, "privacy_sandbox_version", String.valueOf(aVar.v()), setUnmodifiableSet);
        R(builder, "trigger_uri_source", "1", setUnmodifiableSet);
        R(builder, "trigger_uri_timestamp", String.valueOf(jA), setUnmodifiableSet);
        R(builder, "request_uuid", str2, setUnmodifiableSet);
        List<C0911d2> listN = aVar2.N();
        Bundle bundle = new Bundle();
        for (C0911d2 c0911d2 : listN) {
            String strG0 = c0911d2.g0();
            if (c0911d2.j0()) {
                bundle.putString(strG0, String.valueOf(c0911d2.J()));
            } else if (c0911d2.k0()) {
                bundle.putString(strG0, String.valueOf(c0911d2.V()));
            } else if (c0911d2.n0()) {
                bundle.putString(strG0, c0911d2.h0());
            } else if (c0911d2.l0()) {
                bundle.putString(strG0, String.valueOf(c0911d2.b0()));
            }
        }
        S(builder, d().C(str, F.e0).split("\\|"), bundle, setUnmodifiableSet);
        List<C0974k2> listO = aVar.O();
        Bundle bundle2 = new Bundle();
        for (C0974k2 c0974k2 : listO) {
            String strD0 = c0974k2.d0();
            if (c0974k2.f0()) {
                bundle2.putString(strD0, String.valueOf(c0974k2.J()));
            } else if (c0974k2.g0()) {
                bundle2.putString(strD0, String.valueOf(c0974k2.S()));
            } else if (c0974k2.j0()) {
                bundle2.putString(strD0, c0974k2.e0());
            } else if (c0974k2.h0()) {
                bundle2.putString(strD0, String.valueOf(c0974k2.Y()));
            }
        }
        S(builder, d().C(str, F.d0).split("\\|"), bundle2, setUnmodifiableSet);
        R(builder, "dma", aVar.P() ? "1" : "0", setUnmodifiableSet);
        if (!aVar.m1().isEmpty()) {
            R(builder, "dma_cps", aVar.m1(), setUnmodifiableSet);
        }
        if (d().t(F.F0) && aVar.S()) {
            ax.H6.W1 w1V0 = aVar.v0();
            if (!w1V0.n0().isEmpty()) {
                R(builder, "dl_gclid", w1V0.n0(), setUnmodifiableSet);
            }
            if (!w1V0.m0().isEmpty()) {
                R(builder, "dl_gbraid", w1V0.m0(), setUnmodifiableSet);
            }
            if (!w1V0.i0().isEmpty()) {
                R(builder, "dl_gs", w1V0.i0(), setUnmodifiableSet);
            }
            if (w1V0.J() > 0) {
                R(builder, "dl_ss_ts", String.valueOf(w1V0.J()), setUnmodifiableSet);
            }
            if (!w1V0.q0().isEmpty()) {
                R(builder, "mr_gclid", w1V0.q0(), setUnmodifiableSet);
            }
            if (!w1V0.p0().isEmpty()) {
                R(builder, "mr_gbraid", w1V0.p0(), setUnmodifiableSet);
            }
            if (!w1V0.o0().isEmpty()) {
                R(builder, "mr_gs", w1V0.o0(), setUnmodifiableSet);
            }
            if (w1V0.P() > 0) {
                R(builder, "mr_click_ts", String.valueOf(w1V0.P()), setUnmodifiableSet);
            }
        }
        return new C6332h5(builder.build().toString(), jA, 1);
    }

    final String K(ax.H6.D1 d1) {
        if (d1 == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (d1.Z()) {
            b0(sb, 0, "filter_id", Integer.valueOf(d1.O()));
        }
        b0(sb, 0, "event_name", g().c(d1.T()));
        String strN = N(d1.V(), d1.W(), d1.X());
        if (!strN.isEmpty()) {
            b0(sb, 0, "filter_type", strN);
        }
        if (d1.Y()) {
            Z(sb, 1, "event_count_filter", d1.S());
        }
        if (d1.n() > 0) {
            sb.append("  filters {\n");
            Iterator<ax.H6.E1> it = d1.U().iterator();
            while (it.hasNext()) {
                Y(sb, 2, it.next());
            }
        }
        X(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    final String L(ax.H6.G1 g1) {
        if (g1 == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (g1.T()) {
            b0(sb, 0, "filter_id", Integer.valueOf(g1.n()));
        }
        b0(sb, 0, "property_name", g().g(g1.O()));
        String strN = N(g1.P(), g1.Q(), g1.S());
        if (!strN.isEmpty()) {
            b0(sb, 0, "filter_type", strN);
        }
        Y(sb, 1, g1.L());
        sb.append("}\n");
        return sb.toString();
    }

    final String M(C0929f2 c0929f2) {
        ax.H6.Y1 y1L3;
        if (c0929f2 == null) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (t7.a() && d().t(F.t0) && c0929f2.n() > 0) {
            i();
            if (B5.H0(c0929f2.J(0).P3()) && c0929f2.T()) {
                b0(sb, 0, "UploadSubdomain", c0929f2.Q());
            }
        }
        for (C0938g2 c0938g2 : c0929f2.S()) {
            if (c0938g2 != null) {
                X(sb, 1);
                sb.append("bundle {\n");
                if (c0938g2.j1()) {
                    b0(sb, 1, "protocol_version", Integer.valueOf(c0938g2.n2()));
                }
                if (n7.a() && d().E(c0938g2.P3(), F.s0) && c0938g2.m1()) {
                    b0(sb, 1, "session_stitching_token", c0938g2.x0());
                }
                b0(sb, 1, "platform", c0938g2.v0());
                if (c0938g2.e1()) {
                    b0(sb, 1, "gmp_version", Long.valueOf(c0938g2.r3()));
                }
                if (c0938g2.r1()) {
                    b0(sb, 1, "uploading_gmp_version", Long.valueOf(c0938g2.J3()));
                }
                if (c0938g2.c1()) {
                    b0(sb, 1, "dynamite_version", Long.valueOf(c0938g2.h3()));
                }
                if (c0938g2.W0()) {
                    b0(sb, 1, "config_version", Long.valueOf(c0938g2.T2()));
                }
                b0(sb, 1, "gmp_app_id", c0938g2.s0());
                b0(sb, 1, "admob_app_id", c0938g2.O3());
                b0(sb, 1, "app_id", c0938g2.P3());
                b0(sb, 1, "app_version", c0938g2.l0());
                if (c0938g2.H0()) {
                    b0(sb, 1, "app_version_major", Integer.valueOf(c0938g2.J0()));
                }
                b0(sb, 1, "firebase_instance_id", c0938g2.r0());
                if (c0938g2.b1()) {
                    b0(sb, 1, "dev_cert_hash", Long.valueOf(c0938g2.a3()));
                }
                b0(sb, 1, "app_store", c0938g2.k0());
                if (c0938g2.q1()) {
                    b0(sb, 1, "upload_timestamp_millis", Long.valueOf(c0938g2.G3()));
                }
                if (c0938g2.n1()) {
                    b0(sb, 1, "start_timestamp_millis", Long.valueOf(c0938g2.A3()));
                }
                if (c0938g2.d1()) {
                    b0(sb, 1, "end_timestamp_millis", Long.valueOf(c0938g2.o3()));
                }
                if (c0938g2.i1()) {
                    b0(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(c0938g2.x3()));
                }
                if (c0938g2.h1()) {
                    b0(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(c0938g2.u3()));
                }
                b0(sb, 1, "app_instance_id", c0938g2.j0());
                b0(sb, 1, "resettable_device_id", c0938g2.w0());
                b0(sb, 1, "ds_id", c0938g2.q0());
                if (c0938g2.g1()) {
                    b0(sb, 1, "limited_ad_tracking", Boolean.valueOf(c0938g2.E0()));
                }
                b0(sb, 1, "os_version", c0938g2.u0());
                b0(sb, 1, "device_model", c0938g2.p0());
                b0(sb, 1, "user_default_language", c0938g2.y0());
                if (c0938g2.p1()) {
                    b0(sb, 1, "time_zone_offset_minutes", Integer.valueOf(c0938g2.D2()));
                }
                if (c0938g2.V0()) {
                    b0(sb, 1, "bundle_sequential_index", Integer.valueOf(c0938g2.s1()));
                }
                if (t7.a()) {
                    i();
                    if (B5.H0(c0938g2.P3()) && d().t(F.t0) && c0938g2.a1()) {
                        b0(sb, 1, "delivery_index", Integer.valueOf(c0938g2.P1()));
                    }
                }
                if (c0938g2.l1()) {
                    b0(sb, 1, "service_upload", Boolean.valueOf(c0938g2.F0()));
                }
                b0(sb, 1, "health_monitor", c0938g2.t0());
                if (c0938g2.k1()) {
                    b0(sb, 1, "retry_counter", Integer.valueOf(c0938g2.v2()));
                }
                if (c0938g2.Y0()) {
                    b0(sb, 1, "consent_signals", c0938g2.n0());
                }
                if (c0938g2.f1()) {
                    b0(sb, 1, "is_dma_region", Boolean.valueOf(c0938g2.D0()));
                }
                if (c0938g2.Z0()) {
                    b0(sb, 1, "core_platform_services", c0938g2.o0());
                }
                if (c0938g2.X0()) {
                    b0(sb, 1, "consent_diagnostics", c0938g2.m0());
                }
                if (c0938g2.o1()) {
                    b0(sb, 1, "target_os_version", Long.valueOf(c0938g2.D3()));
                }
                if (C0997m7.a() && d().E(c0938g2.P3(), F.A0)) {
                    b0(sb, 1, "ad_services_version", Integer.valueOf(c0938g2.n()));
                    if (c0938g2.I0() && (y1L3 = c0938g2.L3()) != null) {
                        X(sb, 2);
                        sb.append("attribution_eligibility_status {\n");
                        b0(sb, 2, "eligible", Boolean.valueOf(y1L3.b0()));
                        b0(sb, 2, "no_access_adservices_attribution_permission", Boolean.valueOf(y1L3.f0()));
                        b0(sb, 2, "pre_r", Boolean.valueOf(y1L3.g0()));
                        b0(sb, 2, "r_extensions_too_old", Boolean.valueOf(y1L3.h0()));
                        b0(sb, 2, "adservices_extension_too_old", Boolean.valueOf(y1L3.Y()));
                        b0(sb, 2, "ad_storage_not_allowed", Boolean.valueOf(y1L3.V()));
                        b0(sb, 2, "measurement_manager_disabled", Boolean.valueOf(y1L3.e0()));
                        X(sb, 2);
                        sb.append("}\n");
                    }
                }
                if (O6.a() && d().t(F.N0) && c0938g2.G0()) {
                    b0(sb, 1, "ad_campaign_info", c0938g2.K3());
                }
                List<C0974k2> listB0 = c0938g2.B0();
                if (listB0 != null) {
                    for (C0974k2 c0974k2 : listB0) {
                        if (c0974k2 != null) {
                            X(sb, 2);
                            sb.append("user_property {\n");
                            b0(sb, 2, "set_timestamp_millis", c0974k2.i0() ? Long.valueOf(c0974k2.a0()) : null);
                            b0(sb, 2, "name", g().g(c0974k2.d0()));
                            b0(sb, 2, "string_value", c0974k2.e0());
                            b0(sb, 2, "int_value", c0974k2.h0() ? Long.valueOf(c0974k2.Y()) : null);
                            b0(sb, 2, "double_value", c0974k2.f0() ? Double.valueOf(c0974k2.J()) : null);
                            X(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<ax.H6.Z1> listZ0 = c0938g2.z0();
                c0938g2.P3();
                if (listZ0 != null) {
                    for (ax.H6.Z1 z1 : listZ0) {
                        if (z1 != null) {
                            X(sb, 2);
                            sb.append("audience_membership {\n");
                            if (z1.X()) {
                                b0(sb, 2, "audience_id", Integer.valueOf(z1.n()));
                            }
                            if (z1.Y()) {
                                b0(sb, 2, "new_audience", Boolean.valueOf(z1.W()));
                            }
                            a0(sb, 2, "current_data", z1.U());
                            if (z1.Z()) {
                                a0(sb, 2, "previous_data", z1.V());
                            }
                            X(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<C0893b2> listA0 = c0938g2.A0();
                if (listA0 != null) {
                    for (C0893b2 c0893b2 : listA0) {
                        if (c0893b2 != null) {
                            X(sb, 2);
                            sb.append("event {\n");
                            b0(sb, 2, "name", g().c(c0893b2.f0()));
                            if (c0893b2.j0()) {
                                b0(sb, 2, "timestamp_millis", Long.valueOf(c0893b2.c0()));
                            }
                            if (c0893b2.i0()) {
                                b0(sb, 2, "previous_timestamp_millis", Long.valueOf(c0893b2.b0()));
                            }
                            if (c0893b2.h0()) {
                                b0(sb, 2, "count", Integer.valueOf(c0893b2.n()));
                            }
                            if (c0893b2.X() != 0) {
                                c0(sb, 2, c0893b2.g0());
                            }
                            X(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                X(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    final List<Long> P(List<Long> list, List<Integer> list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                j().L().b("Ignoring negative bit index to be cleared", num);
            } else {
                int iIntValue = num.intValue() / 64;
                if (iIntValue >= arrayList.size()) {
                    j().L().c("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(iIntValue, Long.valueOf(((Long) arrayList.get(iIntValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size < 0 || ((Long) arrayList.get(size)).longValue() != 0) {
                break;
            }
            size2 = size - 1;
        }
        return arrayList.subList(0, i);
    }

    final Map<String, Object> Q(Bundle bundle, boolean z) {
        HashMap map = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            boolean z2 = obj instanceof Parcelable[];
            if (z2 || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    if (z2) {
                        for (Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(Q((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        int size = arrayList2.size();
                        int i = 0;
                        while (i < size) {
                            Object obj2 = arrayList2.get(i);
                            i++;
                            if (obj2 instanceof Bundle) {
                                arrayList.add(Q((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(Q((Bundle) obj, false));
                    }
                    map.put(str, arrayList);
                }
            } else if (obj != null) {
                map.put(str, obj);
            }
        }
        return map;
    }

    final void U(C0911d2.a aVar, Object obj) {
        C2254p.l(obj);
        aVar.I().G().E().H();
        if (obj instanceof String) {
            aVar.F((String) obj);
            return;
        }
        if (obj instanceof Long) {
            aVar.z(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            aVar.w(((Double) obj).doubleValue());
            return;
        }
        if (!(obj instanceof Bundle[])) {
            j().G().b("Ignoring invalid (type) event param value", obj);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : (Bundle[]) obj) {
            if (bundle != null) {
                C0911d2.a aVarD0 = C0911d2.d0();
                for (String str : bundle.keySet()) {
                    C0911d2.a aVarD = C0911d2.d0().D(str);
                    Object obj2 = bundle.get(str);
                    if (obj2 instanceof Long) {
                        aVarD.z(((Long) obj2).longValue());
                    } else if (obj2 instanceof String) {
                        aVarD.F((String) obj2);
                    } else if (obj2 instanceof Double) {
                        aVarD.w(((Double) obj2).doubleValue());
                    }
                    aVarD0.B(aVarD);
                }
                if (aVarD0.v() > 0) {
                    arrayList.add((C0911d2) ((AbstractC0994m4) aVarD0.y()));
                }
            }
        }
        aVar.C(arrayList);
    }

    final void V(C0938g2.a aVar) {
        j().K().a("Checking account type status for ad personalization signals");
        if (j0(aVar.j1())) {
            j().F().a("Turning off ad personalization due to account type");
            C0974k2 c0974k2 = (C0974k2) ((AbstractC0994m4) C0974k2.b0().B("_npa").D(e().u()).z(1L).y());
            for (int i = 0; i < aVar.g0(); i++) {
                if ("_npa".equals(aVar.Q0(i).d0())) {
                    aVar.C(i, c0974k2);
                    C6333i c6333iA = C6333i.a(aVar.l1());
                    c6333iA.d(C6330h3.a.AD_PERSONALIZATION, EnumC6347k.CHILD_ACCOUNT);
                    aVar.u0(c6333iA.toString());
                }
            }
            aVar.J(c0974k2);
            C6333i c6333iA2 = C6333i.a(aVar.l1());
            c6333iA2.d(C6330h3.a.AD_PERSONALIZATION, EnumC6347k.CHILD_ACCOUNT);
            aVar.u0(c6333iA2.toString());
        }
    }

    final void W(C0974k2.a aVar, Object obj) {
        C2254p.l(obj);
        aVar.F().C().v();
        if (obj instanceof String) {
            aVar.E((String) obj);
            return;
        }
        if (obj instanceof Long) {
            aVar.z(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            aVar.w(((Double) obj).doubleValue());
        } else {
            j().G().b("Ignoring invalid (type) user attribute value", obj);
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

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    final boolean d0(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(b().a() - j) > j2;
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

    final byte[] i0(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            j().G().b("Failed to gzip content", e);
            throw e;
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    final boolean j0(String str) {
        if (C1060u6.a() && d().t(F.P0)) {
            return false;
        }
        C2254p.l(str);
        Z1 z1H0 = q().H0(str);
        return z1H0 != null && e().y() && z1H0.z() && r().W(str);
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    final byte[] k0(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int i = gZIPInputStream.read(bArr2);
                if (i <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, i);
            }
        } catch (IOException e) {
            j().G().b("Failed to ungzip content", e);
            throw e;
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    final List<Integer> l0() {
        Map<String, String> mapE = F.e(this.b.a());
        if (mapE == null || mapE.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iIntValue = F.Q.a(null).intValue();
        for (Map.Entry<String, String> entry : mapE.entrySet()) {
            if (entry.getKey().startsWith("measurement.id.")) {
                try {
                    int i = Integer.parseInt(entry.getValue());
                    if (i != 0) {
                        arrayList.add(Integer.valueOf(i));
                        if (arrayList.size() >= iIntValue) {
                            j().L().b("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                        continue;
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    j().L().b("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ x5 o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ J5 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6354l q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6389q2 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ Q4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ o5 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        return false;
    }

    final long z(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        return A(str.getBytes(Charset.forName("UTF-8")));
    }
}
