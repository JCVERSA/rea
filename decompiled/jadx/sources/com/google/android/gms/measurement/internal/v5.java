package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class v5 {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[ax.M6.o.values().length];
        a = iArr;
        try {
            iArr[ax.M6.o.POLICY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[ax.M6.o.GRANTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[ax.M6.o.DENIED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[ax.M6.o.UNINITIALIZED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
