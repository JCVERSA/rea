package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class P1 extends AbstractC6286b1 {
    private final S1 c;
    private boolean d;

    P1(E2 e2) {
        super(e2);
        this.c = new S1(this, a(), "google_app_measurement_local.db");
    }

    private static long B(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
            if (!cursorQuery.moveToFirst()) {
                cursorQuery.close();
                return -1L;
            }
            long j = cursorQuery.getLong(0);
            cursorQuery.close();
            return j;
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }

    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 3861. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:99)
        */
    private final boolean D(int r18, byte[] r19) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.P1.D(int, byte[]):boolean");
    }

    private final SQLiteDatabase K() throws SQLiteException {
        if (this.d) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.c.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.d = true;
        return null;
    }

    private final boolean L() {
        return a().getDatabasePath("google_app_measurement_local.db").exists();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final boolean A() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:117:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:119:0x01e5  */
    public final List<ax.p281r6.a> C(int i) throws Throwable {
        SQLiteDatabase sQLiteDatabaseK;
        Cursor cursorQuery;
        String str;
        String[] strArr;
        A5 a5CreateFromParcel;
        C6312f c6312fCreateFromParcel;
        n();
        Cursor cursor = null;
        if (this.d) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (L()) {
            int i2 = 5;
            for (int i3 = 0; i3 < 5; i3++) {
                try {
                    sQLiteDatabaseK = K();
                    if (sQLiteDatabaseK == null) {
                        this.d = true;
                        if (sQLiteDatabaseK != null) {
                            sQLiteDatabaseK.close();
                        }
                        return null;
                    }
                    try {
                        sQLiteDatabaseK.beginTransaction();
                        long jB = B(sQLiteDatabaseK);
                        long j = -1;
                        if (jB != -1) {
                            strArr = new String[]{String.valueOf(jB)};
                            str = "rowid<?";
                        } else {
                            str = null;
                            strArr = null;
                        }
                        cursorQuery = sQLiteDatabaseK.query("messages", new String[]{"rowid", "type", "entry"}, str, strArr, null, null, "rowid asc", Integer.toString(100));
                        while (cursorQuery.moveToNext()) {
                            try {
                                j = cursorQuery.getLong(0);
                                int i4 = cursorQuery.getInt(1);
                                byte[] blob = cursorQuery.getBlob(2);
                                if (i4 == 0) {
                                    Parcel parcelObtain = Parcel.obtain();
                                    try {
                                        try {
                                            parcelObtain.unmarshall(blob, 0, blob.length);
                                            parcelObtain.setDataPosition(0);
                                            D dCreateFromParcel = D.CREATOR.createFromParcel(parcelObtain);
                                            parcelObtain.recycle();
                                            if (dCreateFromParcel != null) {
                                                arrayList.add(dCreateFromParcel);
                                            }
                                        } catch (ax.r6.b.a unused) {
                                            j().G().a("Failed to load event from local database");
                                            parcelObtain.recycle();
                                        }
                                    } catch (Throwable th) {
                                        parcelObtain.recycle();
                                        throw th;
                                    }
                                } else if (i4 == 1) {
                                    Parcel parcelObtain2 = Parcel.obtain();
                                    try {
                                        try {
                                            parcelObtain2.unmarshall(blob, 0, blob.length);
                                            parcelObtain2.setDataPosition(0);
                                            a5CreateFromParcel = A5.CREATOR.createFromParcel(parcelObtain2);
                                            parcelObtain2.recycle();
                                        } catch (ax.r6.b.a unused2) {
                                            j().G().a("Failed to load user property from local database");
                                            parcelObtain2.recycle();
                                            a5CreateFromParcel = null;
                                        }
                                        if (a5CreateFromParcel != null) {
                                            arrayList.add(a5CreateFromParcel);
                                        }
                                    } catch (Throwable th2) {
                                        parcelObtain2.recycle();
                                        throw th2;
                                    }
                                } else if (i4 == 2) {
                                    Parcel parcelObtain3 = Parcel.obtain();
                                    try {
                                        try {
                                            parcelObtain3.unmarshall(blob, 0, blob.length);
                                            parcelObtain3.setDataPosition(0);
                                            c6312fCreateFromParcel = C6312f.CREATOR.createFromParcel(parcelObtain3);
                                            parcelObtain3.recycle();
                                        } catch (ax.r6.b.a unused3) {
                                            j().G().a("Failed to load conditional user property from local database");
                                            parcelObtain3.recycle();
                                            c6312fCreateFromParcel = null;
                                        }
                                        if (c6312fCreateFromParcel != null) {
                                            arrayList.add(c6312fCreateFromParcel);
                                        }
                                    } catch (Throwable th3) {
                                        parcelObtain3.recycle();
                                        throw th3;
                                    }
                                } else if (i4 == 3) {
                                    j().L().a("Skipping app launch break");
                                } else {
                                    j().G().a("Unknown record type in local database");
                                }
                            } catch (SQLiteDatabaseLockedException unused4) {
                                SystemClock.sleep(i2);
                                i2 += 20;
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                if (sQLiteDatabaseK != null) {
                                    sQLiteDatabaseK.close();
                                }
                            } catch (SQLiteFullException e) {
                                e = e;
                                j().G().b("Error reading entries from local database", e);
                                this.d = true;
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                if (sQLiteDatabaseK != null) {
                                    sQLiteDatabaseK.close();
                                }
                            } catch (SQLiteException e2) {
                                e = e2;
                                if (sQLiteDatabaseK != null) {
                                    try {
                                        if (sQLiteDatabaseK.inTransaction()) {
                                            sQLiteDatabaseK.endTransaction();
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        cursor = cursorQuery;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabaseK != null) {
                                            sQLiteDatabaseK.close();
                                        }
                                        throw th;
                                    }
                                }
                                j().G().b("Error reading entries from local database", e);
                                this.d = true;
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                if (sQLiteDatabaseK != null) {
                                    sQLiteDatabaseK.close();
                                }
                            }
                        }
                        if (sQLiteDatabaseK.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                            j().G().a("Fewer entries removed from local database than expected");
                        }
                        sQLiteDatabaseK.setTransactionSuccessful();
                        sQLiteDatabaseK.endTransaction();
                        cursorQuery.close();
                        sQLiteDatabaseK.close();
                    } catch (SQLiteDatabaseLockedException unused5) {
                        cursorQuery = null;
                    } catch (SQLiteFullException e3) {
                        e = e3;
                        cursorQuery = null;
                    } catch (SQLiteException e4) {
                        e = e4;
                        cursorQuery = null;
                    } catch (Throwable th5) {
                        th = th5;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabaseK != null) {
                            sQLiteDatabaseK.close();
                        }
                        throw th;
                    }
                } catch (SQLiteDatabaseLockedException unused6) {
                    sQLiteDatabaseK = null;
                    cursorQuery = null;
                } catch (SQLiteFullException e5) {
                    e = e5;
                    sQLiteDatabaseK = null;
                    cursorQuery = null;
                } catch (SQLiteException e6) {
                    e = e6;
                    sQLiteDatabaseK = null;
                    cursorQuery = null;
                } catch (Throwable th6) {
                    th = th6;
                    sQLiteDatabaseK = null;
                }
            }
            j().L().a("Failed to read events from database in reasonable time");
            return null;
        }
        return arrayList;
    }

    public final boolean E(C6312f c6312f) {
        i();
        byte[] bArrQ0 = B5.q0(c6312f);
        if (bArrQ0.length <= 131072) {
            return D(2, bArrQ0);
        }
        j().I().a("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean F(D d) {
        Parcel parcelObtain = Parcel.obtain();
        d.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return D(0, bArrMarshall);
        }
        j().I().a("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean G(A5 a5) {
        Parcel parcelObtain = Parcel.obtain();
        a5.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return D(1, bArrMarshall);
        }
        j().I().a("User property too long for local database. Sending directly to service");
        return false;
    }

    public final void H() {
        int iDelete;
        n();
        try {
            SQLiteDatabase sQLiteDatabaseK = K();
            if (sQLiteDatabaseK == null || (iDelete = sQLiteDatabaseK.delete("messages", null, null)) <= 0) {
                return;
            }
            j().K().b("Reset local analytics data. records", Integer.valueOf(iDelete));
        } catch (SQLiteException e) {
            j().G().b("Error resetting local analytics data. error", e);
        }
    }

    public final boolean I() {
        return D(3, new byte[0]);
    }

    public final boolean J() {
        n();
        if (this.d || !L()) {
            return false;
        }
        int i = 5;
        for (int i2 = 0; i2 < 5; i2++) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    SQLiteDatabase sQLiteDatabaseK = K();
                    if (sQLiteDatabaseK == null) {
                        this.d = true;
                        if (sQLiteDatabaseK != null) {
                            sQLiteDatabaseK.close();
                        }
                        return false;
                    }
                    sQLiteDatabaseK.beginTransaction();
                    sQLiteDatabaseK.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    sQLiteDatabaseK.setTransactionSuccessful();
                    sQLiteDatabaseK.endTransaction();
                    sQLiteDatabaseK.close();
                    return true;
                } catch (SQLiteFullException e) {
                    j().G().b("Error deleting app launch break from local database", e);
                    this.d = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (SQLiteDatabaseLockedException unused) {
                SystemClock.sleep(i);
                i += 20;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e2) {
                if (0 != 0) {
                    try {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    } catch (Throwable th) {
                        if (0 != 0) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                }
                j().G().b("Error deleting app launch break from local database", e2);
                this.d = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            }
        }
        j().L().a("Error deleting app launch break from local database in reasonable time");
        return false;
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

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6439z o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ Q1 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ P1 q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6351k3 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6317f4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6352k4 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ V4 u() {
        return super.u();
    }
}
