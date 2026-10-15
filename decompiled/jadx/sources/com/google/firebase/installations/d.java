package com.google.firebase.installations;

import ax.p075e8.l;

/* JADX INFO: loaded from: classes3.dex */
public class d extends l {
    private final a q;

    public enum a {
        BAD_CONFIG,
        UNAVAILABLE,
        TOO_MANY_REQUESTS
    }

    public d(a aVar) {
        this.q = aVar;
    }

    public d(String str, a aVar) {
        super(str);
        this.q = aVar;
    }
}
