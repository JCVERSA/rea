package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class I5 {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[ax.H6.F1.a.values().length];
        b = iArr;
        try {
            iArr[ax.H6.F1.a.LESS_THAN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[ax.H6.F1.a.GREATER_THAN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            b[ax.H6.F1.a.EQUAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            b[ax.H6.F1.a.BETWEEN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[ax.H6.H1.a.values().length];
        a = iArr2;
        try {
            iArr2[ax.H6.H1.a.REGEXP.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[ax.H6.H1.a.BEGINS_WITH.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[ax.H6.H1.a.ENDS_WITH.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[ax.H6.H1.a.PARTIAL.ordinal()] = 4;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[ax.H6.H1.a.EXACT.ordinal()] = 5;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[ax.H6.H1.a.IN_LIST.ordinal()] = 6;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
