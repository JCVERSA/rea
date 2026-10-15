package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
enum EnumC6347k {
    UNSET('0'),
    REMOTE_DEFAULT('1'),
    REMOTE_DELEGATION('2'),
    MANIFEST('3'),
    INITIALIZATION('4'),
    API('5'),
    CHILD_ACCOUNT('6'),
    TCF('7'),
    REMOTE_ENFORCED_DEFAULT('8'),
    FAILSAFE('9');

    private final char q;

    EnumC6347k(char c) {
        this.q = c;
    }

    public static EnumC6347k h(char c) {
        for (EnumC6347k enumC6347k : values()) {
            if (enumC6347k.q == c) {
                return enumC6347k;
            }
        }
        return UNSET;
    }
}
