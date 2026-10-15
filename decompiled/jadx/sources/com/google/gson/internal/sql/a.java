package com.google.gson.internal.sql;

import ax.Y8.w;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public final class a {
    public static final boolean a;
    public static final DefaultDateTypeAdapter.b<? extends Date> b;
    public static final DefaultDateTypeAdapter.b<? extends Date> c;
    public static final w d;
    public static final w e;
    public static final w f;

    /* JADX INFO: renamed from: com.google.gson.internal.sql.a$a, reason: collision with other inner class name */
    class C0588a extends DefaultDateTypeAdapter.b<java.sql.Date> {
        C0588a(Class cls) {
            super(cls);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.b
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public java.sql.Date d(Date date) {
            return new java.sql.Date(date.getTime());
        }
    }

    class b extends DefaultDateTypeAdapter.b<Timestamp> {
        b(Class cls) {
            super(cls);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.b
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Timestamp d(Date date) {
            return new Timestamp(date.getTime());
        }
    }

    static {
        boolean z;
        try {
            Class.forName("java.sql.Date");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        a = z;
        if (z) {
            b = new C0588a(java.sql.Date.class);
            c = new b(Timestamp.class);
            d = SqlDateTypeAdapter.b;
            e = SqlTimeTypeAdapter.b;
            f = SqlTimestampTypeAdapter.b;
            return;
        }
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
    }
}
