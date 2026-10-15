package com.google.android.gms.measurement.internal;

import ax.H6.F7;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.x2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class C6430x2 {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b;
    static final /* synthetic */ int[] c;

    static {
        int[] iArr = new int[ax.H6.K1.d.values().length];
        c = iArr;
        try {
            iArr[ax.H6.K1.d.DENIED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            c[ax.H6.K1.d.GRANTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[ax.H6.K1.e.values().length];
        b = iArr2;
        try {
            iArr2[ax.H6.K1.e.AD_STORAGE.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            b[ax.H6.K1.e.ANALYTICS_STORAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            b[ax.H6.K1.e.AD_USER_DATA.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            b[ax.H6.K1.e.AD_PERSONALIZATION.ordinal()] = 4;
        } catch (NoSuchFieldError unused6) {
        }
        int[] iArr3 = new int[F7.values().length];
        a = iArr3;
        try {
            iArr3[F7.DEBUG.ordinal()] = 1;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[F7.ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[F7.WARN.ordinal()] = 3;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[F7.VERBOSE.ordinal()] = 4;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
