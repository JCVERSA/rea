package com.alphainventor.filemanager.sharing;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import ax.U2.b;
import ax.p006a3.Q;
import ax.p086f3.AbstractC2005k;
import ax.p086f3.C2014u;
import ax.p086f3.d0;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.file.AbstractC6239n;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class QuickShareProvider extends ContentProvider {
    public static final String[] Y = {"file_uri", "file_dir", "file_mime_type"};
    private static QuickShareProvider Z;
    private static Boolean k0;
    private AbstractC6239n X;
    private final List<AbstractC6239n> q = new ArrayList();

    public static boolean a(Context context) {
        ActivityInfo activityInfo;
        if (context == null) {
            return false;
        }
        if (k0 == null) {
            if (Q.u1()) {
                try {
                    List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(b(1), 65536);
                    if (!listQueryIntentActivities.isEmpty() && (activityInfo = listQueryIntentActivities.get(0).activityInfo) != null && ("com.google.android.gms.nearby.sharing.send.SendActivity".equals(activityInfo.name) || "com.google.android.gms.nearby.sharing.main.MainActivity".equals(activityInfo.name))) {
                        k0 = Boolean.TRUE;
                    }
                } catch (Exception unused) {
                }
                if (k0 == null) {
                    k0 = Boolean.FALSE;
                }
            } else {
                k0 = Boolean.FALSE;
            }
        }
        return k0.booleanValue();
    }

    private static Intent b(int i) {
        Intent intent = new Intent();
        intent.setAction("com.google.android.gms.nearby.SEND_FOLDER");
        intent.putExtra("com.google.android.gms.nearby.SEND_FOLDER_CONTENT_URI", "content://com.alphainventor.filemanager.foldershare.provider");
        intent.putExtra("com.google.android.gms.nearby.FILE_COUNT", i);
        intent.setPackage("com.google.android.gms");
        return intent;
    }

    public static QuickShareProvider c() {
        return Z;
    }

    public void d(Context context, AbstractC6239n abstractC6239n, List<AbstractC6239n> list) {
        synchronized (this.q) {
            this.X = abstractC6239n;
            this.q.clear();
            this.q.addAll(list);
        }
        Intent intentB = b(list.size());
        context.grantUriPermission("com.google.android.gms", Uri.parse("content://com.alphainventor.filemanager.foldershare.provider"), 1);
        context.startActivity(intentB);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        b.k(getContext());
        Z = this;
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String callingPackage = getCallingPackage();
        if (callingPackage == null || !callingPackage.equals("com.google.android.gms")) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(Y);
        synchronized (this.q) {
            if (this.X != null) {
                for (AbstractC6239n abstractC6239n : this.q) {
                    if (abstractC6239n instanceof AbstractC2005k) {
                        AbstractC2005k abstractC2005k = (AbstractC2005k) abstractC6239n;
                        Uri uriV = C2014u.v(abstractC2005k);
                        try {
                            getContext().grantUriPermission("com.google.android.gms", uriV, 1);
                        } catch (Exception unused) {
                        }
                        String strU = abstractC2005k.U();
                        String strE = this.X.E();
                        if (d0.I(strE, strU)) {
                            matrixCursor.addRow(new String[]{uriV.toString(), d0.n(strE, strU), abstractC6239n.t()});
                        } else if (d0.G(strE, strU)) {
                            matrixCursor.addRow(new String[]{uriV.toString(), HttpUrl.FRAGMENT_ENCODE_SET, abstractC6239n.t()});
                        }
                    } else if (abstractC6239n != null) {
                        C2461b.g("Invalid file info class:" + abstractC6239n.getClass().getName());
                    }
                }
            }
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
