package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import ax.p265q6.C2254p;
import com.google.android.gms.common.annotation.KeepName;

/* JADX INFO: loaded from: classes3.dex */
@KeepName
public class UserRecoverableAuthException extends ax.p121h6.a {
    private final PendingIntent X;
    private final b Y;
    private final Intent q;

    public UserRecoverableAuthException(String str, Intent intent) {
        this(str, intent, null, b.LEGACY);
    }

    private UserRecoverableAuthException(String str, Intent intent, PendingIntent pendingIntent, b bVar) {
        super(str);
        this.X = pendingIntent;
        this.q = intent;
        this.Y = (b) C2254p.l(bVar);
    }

    public static UserRecoverableAuthException b(String str, Intent intent, PendingIntent pendingIntent) {
        C2254p.l(intent);
        C2254p.l(pendingIntent);
        return new UserRecoverableAuthException(str, intent, pendingIntent, b.AUTH_INSTANTIATION);
    }

    public Intent a() {
        Intent intent = this.q;
        if (intent != null) {
            return new Intent(intent);
        }
        int iOrdinal = this.Y.ordinal();
        if (iOrdinal == 0) {
            Log.w("Auth", "Make sure that an intent was provided to class instantiation.");
            return null;
        }
        if (iOrdinal == 1) {
            Log.e("Auth", "This shouldn't happen. Gms API throwing this exception should support the recovery Intent.");
            return null;
        }
        if (iOrdinal != 2) {
            return null;
        }
        Log.e("Auth", "this instantiation of UserRecoverableAuthException doesn't support an Intent.");
        return null;
    }
}
