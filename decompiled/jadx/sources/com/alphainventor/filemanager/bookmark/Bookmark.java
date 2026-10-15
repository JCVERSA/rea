package com.alphainventor.filemanager.bookmark;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import ax.U2.f;
import ax.X2.d;
import ax.Y8.g;
import ax.Y8.h;
import ax.Y8.i;
import ax.Y8.m;
import ax.Y8.o;
import ax.Y8.p;
import ax.Y8.q;
import ax.Z8.c;
import ax.p038c3.k;
import ax.p038c3.s;
import ax.p086f3.A;
import ax.p086f3.C2017x;
import ax.p086f3.K;
import ax.p086f3.d0;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.C6240o;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Bookmark implements Serializable {
    private transient K X;

    @c("type")
    private int Y;

    @c("name")
    private String Z;

    @c("location_key")
    private final int k0;

    @c("path")
    private String l0;

    @c("file_id")
    private final String m0;

    @c("is_dir")
    private final boolean n0;

    @c("location")
    private f o0;

    @c("time_millis")
    private long p0;
    private transient long q;

    class a extends ax.p076e9.a<List<Bookmark>> {
        a() {
        }
    }

    private Bookmark(int i, String str, K k, String str2, String str3, boolean z, long j) {
        this.Y = i;
        this.Z = str;
        this.X = k;
        this.o0 = k.d();
        this.k0 = k.b();
        this.l0 = str2;
        this.m0 = str3;
        this.n0 = z;
        this.p0 = j;
    }

    public static String J(List<Bookmark> list) {
        return new com.google.gson.a().d(f.class, new q<f>() { // from class: com.alphainventor.filemanager.bookmark.Bookmark.1
            @Override // ax.Y8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(f fVar, Type type, p pVar) {
                return new o(fVar.H());
            }
        }).b().t(list);
    }

    public static Bookmark a(Context context) {
        K kU = d.u(context);
        return b(5, kU.f(context), kU, d.F(context).v().getAbsolutePath(), null, true, -1L);
    }

    private static Bookmark b(int i, String str, K k, String str2, String str3, boolean z, long j) {
        return new Bookmark(i, str, k, str2, str3, z, j);
    }

    public static Bookmark c(Context context, Uri uri) {
        if (uri.getScheme() != null && uri.getHost() != null && uri.getPath() != null) {
            try {
                k kVarA = k.a(uri);
                return d(context, kVarA.d(), kVarA.e());
            } catch (IllegalArgumentException unused) {
            }
        }
        return null;
    }

    public static Bookmark d(Context context, K k, String str) {
        return b(5, k.f(context), k, str, null, true, -1L);
    }

    public static Bookmark e(Cursor cursor) {
        Bookmark bookmarkB = b(cursor.getInt(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("display_name")), K.a(f.o(cursor.getString(cursor.getColumnIndex("location_name"))), cursor.getInt(cursor.getColumnIndex("location_key"))), cursor.getString(cursor.getColumnIndex("path")), cursor.getString(cursor.getColumnIndex("file_id")), cursor.getInt(cursor.getColumnIndex("is_directory")) != 0, cursor.getLong(cursor.getColumnIndex("timestamp")));
        bookmarkB.E(cursor.getLong(cursor.getColumnIndex("_id")));
        return bookmarkB;
    }

    public static Bookmark f(Bookmark bookmark) {
        return b(bookmark.Y, bookmark.Z, bookmark.v(), bookmark.l0, bookmark.m0, bookmark.n0, bookmark.z());
    }

    public static Bookmark g(Context context, String str) {
        return d(context, C2017x.g(str).B(), str);
    }

    public static Bookmark h(String str, K k, String str2, String str3, boolean z) {
        return new Bookmark(2, str, k, str2, str3, z, System.currentTimeMillis());
    }

    public static Bookmark i(Context context, K k, String str) {
        return b(5, k.f(context), k, str, null, true, System.currentTimeMillis());
    }

    public static Bookmark j(K k, String str, String str2, boolean z, long j) {
        return new Bookmark(3, null, k, str, str2, z, j);
    }

    public static Bookmark k(Bookmark bookmark) {
        Bookmark bookmarkF = f(bookmark);
        bookmarkF.I(4);
        if (bookmark.z() != -5) {
            bookmarkF.H(System.currentTimeMillis());
        }
        return bookmarkF;
    }

    public static Bookmark l(Context context, f fVar) {
        return n(context, K.a(fVar, 0));
    }

    public static Bookmark m(Context context, f fVar, int i) {
        return n(context, K.a(fVar, i));
    }

    public static Bookmark n(Context context, K k) {
        return new Bookmark(1, k.f(context), k, k.e(), null, true, -1L);
    }

    public static Bookmark o(s sVar) {
        String strC;
        K kD = sVar.d();
        if (TextUtils.isEmpty(sVar.c())) {
            C6240o c6240oE = C2017x.e(kD);
            strC = (!c6240oE.a() || c6240oE.w() == null) ? kD.e() : c6240oE.w();
        } else {
            strC = sVar.c();
        }
        String str = strC;
        return new Bookmark(1, sVar.e(), kD, str, str, true, -1L);
    }

    public static List<Bookmark> p(String str) {
        try {
            return (List) new com.google.gson.a().d(f.class, new h<f>() { // from class: com.alphainventor.filemanager.bookmark.Bookmark.2
                @Override // ax.Y8.h
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public f a(i iVar, Type type, g gVar) throws m {
                    return f.o(iVar.j());
                }
            }).b().l(str, new a().d());
        } catch (Exception e) {
            ax.p302sb.c.h().b("GSON TYPE TOKEN").m(e).i();
            return null;
        }
    }

    public int A() {
        return this.Y;
    }

    public boolean B() {
        return this.n0;
    }

    public boolean C(f fVar, int i) {
        return t() == fVar && u() == i;
    }

    public boolean D(Bookmark bookmark) {
        return bookmark != null && t() == bookmark.t() && u() == bookmark.u();
    }

    public void E(long j) {
        this.q = j;
    }

    public void F(String str) {
        this.Z = str;
    }

    public void G(String str) {
        this.l0 = str;
    }

    public void H(long j) {
        this.p0 = j;
    }

    public void I(int i) {
        this.Y = i;
    }

    public String q() {
        return this.m0;
    }

    public Drawable r(Context context) {
        return B() ? ax.p374x3.a.c(context, R.drawable.icon_folder_full) : A.f(context, x(), true);
    }

    public long s() {
        return this.q;
    }

    public f t() {
        return this.o0;
    }

    public int u() {
        return this.k0;
    }

    public K v() {
        if (this.X == null) {
            this.X = K.a(this.o0, this.k0);
        }
        return this.X;
    }

    public String w() {
        return this.Z;
    }

    public String x() {
        return this.l0;
    }

    public String y() {
        if (this.l0 == null) {
            return null;
        }
        return d0.v(v(), this.l0, Boolean.valueOf(this.n0));
    }

    public long z() {
        return this.p0;
    }
}
