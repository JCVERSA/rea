package com.alphainventor.filemanager.bookmark;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import ax.p302sb.c;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkProvider extends ContentProvider implements BaseColumns {
    public static final Uri X = Uri.parse("content://com.alphainventor.filemanager.bookmark");
    public static final String[] Y = {"_id", "type", "display_name", "location_name", "location_key", "path", "file_id", "is_directory", "timestamp"};
    private static final String Z = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s TEXT, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s TEXT, %s TEXT, %s INTEGER NOT NULL DEFAULT 0, %s INTEGER NOT NULL)", "bookmarks", "_id", "type", "display_name", "location_name", "location_key", "path", "file_id", "is_directory", "timestamp");
    private static final UriMatcher k0;
    private a q;

    private static class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "com.alphainventor.filemanager", (SQLiteDatabase.CursorFactory) null, 3);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(BookmarkProvider.Z);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE bookmarks");
            sQLiteDatabase.execSQL(BookmarkProvider.Z);
            c.h().d("BookmarkProvider.onDowngrade").h("old:" + i + " new:" + i2).i();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < 3) {
                sQLiteDatabase.execSQL("DROP TABLE bookmarks");
                sQLiteDatabase.execSQL(BookmarkProvider.Z);
            }
        }
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        k0 = uriMatcher;
        uriMatcher.addURI("com.alphainventor.filemanager.bookmark", HttpUrl.FRAGMENT_ENCODE_SET, 1000);
        uriMatcher.addURI("com.alphainventor.filemanager.bookmark", "/#", 2000);
    }

    private SQLiteDatabase b() {
        return this.q.getWritableDatabase();
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int iDelete;
        SQLiteDatabase sQLiteDatabaseB = b();
        int i = 0;
        try {
            if (k0.match(uri) != 2000) {
                iDelete = sQLiteDatabaseB.delete("bookmarks", str, strArr);
            } else {
                String lastPathSegment = uri.getLastPathSegment();
                if (str == null) {
                    iDelete = sQLiteDatabaseB.delete("bookmarks", "_id=" + lastPathSegment, null);
                } else {
                    iDelete = sQLiteDatabaseB.delete("bookmarks", "_id=" + lastPathSegment + " and (" + str + ")", strArr);
                }
            }
            i = iDelete;
            getContext().getContentResolver().notifyChange(uri, null);
            return i;
        } catch (SQLiteException e) {
            e = e;
            e.printStackTrace();
            c.h().d("BP").m(e).i();
            return i;
        } catch (SecurityException e2) {
            e = e2;
            e.printStackTrace();
            c.h().d("BP").m(e).i();
            return i;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = k0.match(uri);
        if (iMatch == 1000) {
            return "vnd.android.cursor.dir/bookmarks";
        }
        if (iMatch == 2000) {
            return "vnd.android.cursor.item/bookmark";
        }
        throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabaseB = b();
        long jInsert = sQLiteDatabaseB.insert("bookmarks", HttpUrl.FRAGMENT_ENCODE_SET, contentValues);
        if (jInsert <= 0) {
            return null;
        }
        Uri uriWithAppendedId = ContentUris.withAppendedId(X, jInsert);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("bookmarks");
        String[] strArr = {"_id"};
        if (contentValues.getAsInteger("type").intValue() != 2 && contentValues.getAsInteger("type").intValue() == 3) {
            Cursor cursorQuery = sQLiteQueryBuilder.query(sQLiteDatabaseB, strArr, "type = 3", null, null, null, "timestamp asc");
            if (cursorQuery != null && cursorQuery.getCount() > 200 && cursorQuery.moveToFirst()) {
                sQLiteDatabaseB.delete("bookmarks", "_id=?", new String[]{Integer.toString(cursorQuery.getInt(0))});
            }
            cursorQuery.close();
        }
        getContext().getContentResolver().notifyChange(uriWithAppendedId, null);
        return uriWithAppendedId;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.q = new a(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase sQLiteDatabaseB = b();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("bookmarks");
        if (k0.match(uri) == 2000) {
            sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
        }
        Cursor cursorQuery = sQLiteQueryBuilder.query(sQLiteDatabaseB, strArr, str, strArr2, null, null, str2);
        try {
            cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
        } catch (SecurityException unused) {
        }
        return cursorQuery;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int iUpdate;
        SQLiteDatabase sQLiteDatabaseB = b();
        if (k0.match(uri) != 2000) {
            iUpdate = sQLiteDatabaseB.update("bookmarks", contentValues, str, strArr);
        } else {
            String lastPathSegment = uri.getLastPathSegment();
            if (str == null) {
                iUpdate = sQLiteDatabaseB.update("bookmarks", contentValues, "_id=" + lastPathSegment, null);
            } else {
                iUpdate = sQLiteDatabaseB.update("bookmarks", contentValues, "_id=" + lastPathSegment + " and (" + str + ")", strArr);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return iUpdate;
    }
}
