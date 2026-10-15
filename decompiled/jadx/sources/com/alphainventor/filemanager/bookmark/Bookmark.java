package com.alphainventor.filemanager.bookmark;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import ax.B1.d;
import ax.G1.j;
import ax.J1.C0739u;
import ax.J1.F;
import ax.J1.V;
import ax.J1.r;
import ax.p149k8.g;
import ax.p149k8.h;
import ax.p149k8.i;
import ax.p149k8.m;
import ax.p149k8.o;
import ax.p149k8.p;
import ax.p149k8.q;
import ax.p163l8.c;
import ax.p338y1.f;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.C5839m;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Bookmark implements Serializable {
    private transient F X;

    @c("type")
    private int Y;

    @c("name")
    private String Z;

    @c("location_key")
    private final int h0;

    @c("path")
    private String i0;

    @c("file_id")
    private final String j0;

    @c("is_dir")
    private final boolean k0;

    @c("location")
    private f l0;

    @c("time_millis")
    private long m0;
    private transient long q;

    class a extends ax.p233q8.a<List<Bookmark>> {
        a() {
        }
    }

    private Bookmark(int i, String str, F f, String str2, String str3, boolean z, long j) {
        this.Y = i;
        this.Z = str;
        this.X = f;
        this.l0 = f.d();
        this.h0 = f.b();
        this.i0 = str2;
        this.j0 = str3;
        this.k0 = z;
        this.m0 = j;
    }

    public static String I(List<Bookmark> list) {
        return new com.google.gson.a().d(f.class, new q<f>() { // from class: com.alphainventor.filemanager.bookmark.Bookmark.1
            @Override // ax.p149k8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(f fVar, Type type, p pVar) {
                return new o(fVar.H());
            }
        }).b().r(list);
    }

    public static Bookmark a(Context context) {
        return b(5, f.q0.L(context), F.e, d.F(context).v().getAbsolutePath(), null, true, -1L);
    }

    private static Bookmark b(int i, String str, F f, String str2, String str3, boolean z, long j) {
        return new Bookmark(i, str, f, str2, str3, z, j);
    }

    public static Bookmark c(Context context, Uri uri) {
        if (uri.getScheme() != null && uri.getHost() != null && uri.getPath() != null) {
            try {
                j jVarA = j.a(uri);
                return b(5, jVarA.d().f(context), jVarA.d(), jVarA.e(), null, true, -1L);
            } catch (IllegalArgumentException unused) {
            }
        }
        return null;
    }

    public static Bookmark d(Cursor cursor) {
        Bookmark bookmarkB = b(cursor.getInt(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("display_name")), F.a(f.n(cursor.getString(cursor.getColumnIndex("location_name"))), cursor.getInt(cursor.getColumnIndex("location_key"))), cursor.getString(cursor.getColumnIndex("path")), cursor.getString(cursor.getColumnIndex("file_id")), cursor.getInt(cursor.getColumnIndex("is_directory")) != 0, cursor.getLong(cursor.getColumnIndex("timestamp")));
        bookmarkB.D(cursor.getLong(cursor.getColumnIndex("_id")));
        return bookmarkB;
    }

    public static Bookmark e(Bookmark bookmark) {
        return b(bookmark.Y, bookmark.Z, bookmark.u(), bookmark.i0, bookmark.j0, bookmark.k0, bookmark.y());
    }

    public static Bookmark f(Context context, String str) {
        C5839m c5839mG = r.g(str);
        return b(5, c5839mG.z().f(context), c5839mG.z(), str, null, true, -1L);
    }

    public static Bookmark g(String str, F f, String str2, String str3, boolean z) {
        return new Bookmark(2, str, f, str2, str3, z, System.currentTimeMillis());
    }

    public static Bookmark h(Context context, F f, String str) {
        return b(5, f.f(context), f, str, null, true, System.currentTimeMillis());
    }

    public static Bookmark i(F f, String str, String str2, boolean z, long j) {
        return new Bookmark(3, null, f, str, str2, z, j);
    }

    public static Bookmark j(Bookmark bookmark) {
        Bookmark bookmarkE = e(bookmark);
        bookmarkE.H(4);
        if (bookmark.y() != -5) {
            bookmarkE.G(System.currentTimeMillis());
        }
        return bookmarkE;
    }

    public static Bookmark k(Context context, F f) {
        return new Bookmark(1, f.f(context), f, f.e(), null, true, -1L);
    }

    public static Bookmark l(Context context, f fVar) {
        return k(context, F.a(fVar, 0));
    }

    public static Bookmark m(Context context, f fVar, int i) {
        return k(context, F.a(fVar, i));
    }

    public static Bookmark n(ax.G1.p pVar) {
        String strC;
        F fD = pVar.d();
        if (TextUtils.isEmpty(pVar.c())) {
            C5839m c5839mD = r.d(fD);
            strC = (!c5839mD.a() || c5839mD.u() == null) ? fD.e() : c5839mD.u();
        } else {
            strC = pVar.c();
        }
        String str = strC;
        return new Bookmark(1, pVar.e(), fD, str, str, true, -1L);
    }

    public static List<Bookmark> o(String str) {
        try {
            return (List) new com.google.gson.a().d(f.class, new h<f>() { // from class: com.alphainventor.filemanager.bookmark.Bookmark.2
                @Override // ax.p149k8.h
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public f a(i iVar, Type type, g gVar) throws m {
                    return f.n(iVar.m());
                }
            }).b().j(str, new a().d());
        } catch (Exception e) {
            ax.p221pa.c.h().b("GSON TYPE TOKEN").m(e).i();
            return null;
        }
    }

    public boolean A() {
        return this.k0;
    }

    public boolean B(f fVar, int i) {
        return s() == fVar && t() == i;
    }

    public boolean C(Bookmark bookmark) {
        return bookmark != null && s() == bookmark.s() && t() == bookmark.t();
    }

    public void D(long j) {
        this.q = j;
    }

    public void E(String str) {
        this.Z = str;
    }

    public void F(String str) {
        this.i0 = str;
    }

    public void G(long j) {
        this.m0 = j;
    }

    public void H(int i) {
        this.Y = i;
    }

    public String p() {
        return this.j0;
    }

    public Drawable q(Context context) {
        return A() ? ax.p017b2.a.c(context, R.drawable.icon_folder_full) : C0739u.d(context, w(), true);
    }

    public long r() {
        return this.q;
    }

    public f s() {
        return this.l0;
    }

    public int t() {
        return this.h0;
    }

    public F u() {
        if (this.X == null) {
            this.X = F.a(this.l0, this.h0);
        }
        return this.X;
    }

    public String v() {
        return this.Z;
    }

    public String w() {
        return this.i0;
    }

    public String x() {
        if (this.i0 == null) {
            return null;
        }
        return V.u(u(), this.i0, Boolean.valueOf(this.k0));
    }

    public long y() {
        return this.m0;
    }

    public int z() {
        return this.Y;
    }
}
