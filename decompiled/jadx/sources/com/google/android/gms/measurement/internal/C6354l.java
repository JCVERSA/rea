package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import ax.H6.A6;
import ax.H6.AbstractC0994m4;
import ax.H6.C0893b2;
import ax.H6.C0938g2;
import ax.H6.C0956i2;
import ax.H6.C0960i6;
import ax.H6.C0974k2;
import ax.H6.C0997m7;
import ax.H6.C1013o6;
import ax.H6.C1021p6;
import ax.H6.O6;
import ax.H6.t7;
import ax.p265q6.C2254p;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6354l extends AbstractC6353k5 {
    private static final String[] f = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] g = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] h = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;"};
    private static final String[] i = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] j = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] k = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] l = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] m = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private static final String[] n = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    private static final String[] o = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    private final C6386q d;
    private final C6311e5 e;

    C6354l(q5 q5Var) {
        super(q5Var);
        this.e = new C6311e5(b());
        this.d = new C6386q(this, a(), "google_app_measurement.db");
    }

    private final boolean B0(String str, List<Integer> list) {
        C2254p.f(str);
        u();
        n();
        SQLiteDatabase sQLiteDatabaseB = B();
        try {
            long jY0 = y0("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int iMax = Math.max(0, Math.min(2000, d().v(str, F.G)));
            if (jY0 <= iMax) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                Integer num = list.get(i2);
                if (num == null) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String str2 = "(" + TextUtils.join(",", arrayList) + ")";
            StringBuilder sb = new StringBuilder("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb.append(str2);
            sb.append(" order by rowid desc limit -1 offset ?)");
            return sQLiteDatabaseB.delete("audience_filter_values", sb.toString(), new String[]{str, Integer.toString(iMax)}) > 0;
        } catch (SQLiteException e) {
            j().G().c("Database error querying filters. appId", V1.v(str), e);
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x012a  */
    /* JADX WARN: Multi-variable type inference failed */
    private final C6433y E0(String str, String str2, String str3) throws Throwable {
        Cursor cursorQuery;
        Boolean boolValueOf;
        C2254p.f(str2);
        C2254p.f(str3);
        n();
        u();
        ax.m2.d.c cVar = 0;
        try {
            try {
                cursorQuery = B().query(str, (String[]) new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new String[0]), "app_id=? and name=?", new String[]{str2, str3}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    long j2 = cursorQuery.getLong(0);
                    long j3 = cursorQuery.getLong(1);
                    long j4 = cursorQuery.getLong(2);
                    long j5 = cursorQuery.isNull(3) ? 0L : cursorQuery.getLong(3);
                    Long lValueOf = cursorQuery.isNull(4) ? null : Long.valueOf(cursorQuery.getLong(4));
                    Long lValueOf2 = cursorQuery.isNull(5) ? null : Long.valueOf(cursorQuery.getLong(5));
                    Long lValueOf3 = cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6));
                    if (cursorQuery.isNull(7)) {
                        boolValueOf = null;
                    } else {
                        boolValueOf = Boolean.valueOf(cursorQuery.getLong(7) == 1);
                    }
                    C6433y c6433y = new C6433y(str2, str3, j2, j3, cursorQuery.isNull(8) ? 0L : cursorQuery.getLong(8), j4, j5, lValueOf, lValueOf2, lValueOf3, boolValueOf);
                    if (cursorQuery.moveToNext()) {
                        j().G().b("Got multiple records for event aggregates, expected one. appId", V1.v(str2));
                    }
                    cursorQuery.close();
                    return c6433y;
                } catch (SQLiteException e) {
                    e = e;
                    j().G().d("Error querying events. appId", V1.v(str2), g().c(str3), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cVar = "current_bundle_count";
                if (cVar != 0) {
                    cVar.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cVar != 0) {
                cVar.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0030  */
    private final long G(String str, String[] strArr, long j2) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = B().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return j2;
                }
                long j3 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j3;
            } catch (SQLiteException e) {
                j().G().c("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        throw th;
    }

    private final Object M(Cursor cursor, int i2) {
        int type = cursor.getType(i2);
        if (type == 0) {
            j().G().a("Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i2));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i2));
        }
        if (type == 3) {
            return cursor.getString(i2);
        }
        if (type != 4) {
            j().G().b("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
            return null;
        }
        j().G().a("Loaded invalid blob type value, ignoring it");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0046  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    private final <T> T N(String str, String[] strArr, r<T> rVar) throws Throwable {
        Cursor cursorRawQuery;
        ?? r0 = 0;
        try {
            try {
                cursorRawQuery = B().rawQuery(str, strArr);
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        T tA = rVar.a(cursorRawQuery);
                        cursorRawQuery.close();
                        return tA;
                    }
                    j().K().a("No data found");
                    cursorRawQuery.close();
                    return null;
                } catch (SQLiteException e) {
                    e = e;
                    j().G().b("Error querying database.", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                r0 = str;
                if (r0 != 0) {
                    r0.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0030  */
    private final String P(String str, String[] strArr, String str2) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = B().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return str2;
                }
                String string = cursorRawQuery.getString(0);
                cursorRawQuery.close();
                return string;
            } catch (SQLiteException e) {
                j().G().c("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        throw th;
    }

    private final void Q0(String str, String str2) {
        C2254p.f(str2);
        n();
        u();
        try {
            B().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e) {
            j().G().c("Error deleting snapshot. appId", V1.v(str2), e);
        }
    }

    private static void T(ContentValues contentValues, String str, Object obj) {
        C2254p.f(str);
        C2254p.l(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            contentValues.put(str, (Double) obj);
        }
    }

    private final void X(String str, C6433y c6433y) {
        C2254p.l(c6433y);
        n();
        u();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", c6433y.a);
        contentValues.put("name", c6433y.b);
        contentValues.put("lifetime_count", Long.valueOf(c6433y.c));
        contentValues.put("current_bundle_count", Long.valueOf(c6433y.d));
        contentValues.put("last_fire_timestamp", Long.valueOf(c6433y.f));
        contentValues.put("last_bundled_timestamp", Long.valueOf(c6433y.g));
        contentValues.put("last_bundled_day", c6433y.h);
        contentValues.put("last_sampled_complex_event_id", c6433y.i);
        contentValues.put("last_sampling_rate", c6433y.j);
        contentValues.put("current_session_count", Long.valueOf(c6433y.e));
        Boolean bool = c6433y.k;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (B().insertWithOnConflict(str, null, contentValues, 5) == -1) {
                j().G().b("Failed to insert/update event aggregates (got -1). appId", V1.v(c6433y.a));
            }
        } catch (SQLiteException e) {
            j().G().c("Error storing event aggregates. appId", V1.v(c6433y.a), e);
        }
    }

    private final void Z(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase sQLiteDatabaseB = B();
            String asString = contentValues.getAsString(str2);
            if (asString == null) {
                j().H().b("Value of the primary key is not set.", V1.v(str2));
                return;
            }
            if (sQLiteDatabaseB.update(str, contentValues, str2 + " = ?", new String[]{asString}) == 0 && sQLiteDatabaseB.insertWithOnConflict(str, null, contentValues, 5) == -1) {
                j().G().c("Failed to insert/update table (got -1). key", V1.v(str), V1.v(str2));
            }
        } catch (SQLiteException e) {
            j().G().d("Error storing into table. key", V1.v(str), V1.v(str2), e);
        }
    }

    private final boolean g0(String str, int i2, ax.H6.D1 d1) {
        u();
        n();
        C2254p.f(str);
        C2254p.l(d1);
        if (d1.T().isEmpty()) {
            j().L().d("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", V1.v(str), Integer.valueOf(i2), String.valueOf(d1.Z() ? Integer.valueOf(d1.O()) : null));
            return false;
        }
        byte[] bArrL = d1.l();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i2));
        contentValues.put("filter_id", d1.Z() ? Integer.valueOf(d1.O()) : null);
        contentValues.put("event_name", d1.T());
        contentValues.put("session_scoped", d1.a0() ? Boolean.valueOf(d1.X()) : null);
        contentValues.put("data", bArrL);
        try {
            if (B().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                return true;
            }
            j().G().b("Failed to insert event filter (got -1). appId", V1.v(str));
            return true;
        } catch (SQLiteException e) {
            j().G().c("Error storing event filter. appId", V1.v(str), e);
            return false;
        }
    }

    private final boolean h0(String str, int i2, ax.H6.G1 g1) {
        u();
        n();
        C2254p.f(str);
        C2254p.l(g1);
        if (g1.O().isEmpty()) {
            j().L().d("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", V1.v(str), Integer.valueOf(i2), String.valueOf(g1.T() ? Integer.valueOf(g1.n()) : null));
            return false;
        }
        byte[] bArrL = g1.l();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i2));
        contentValues.put("filter_id", g1.T() ? Integer.valueOf(g1.n()) : null);
        contentValues.put("property_name", g1.O());
        contentValues.put("session_scoped", g1.U() ? Boolean.valueOf(g1.S()) : null);
        contentValues.put("data", bArrL);
        try {
            if (B().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            j().G().b("Failed to insert property filter (got -1). appId", V1.v(str));
            return false;
        } catch (SQLiteException e) {
            j().G().c("Error storing property filter. appId", V1.v(str), e);
            return false;
        }
    }

    private final long y0(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = B().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                long j2 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j2;
            } catch (SQLiteException e) {
                j().G().c("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final long A() {
        return G("select max(timestamp) from raw_events", null, 0L);
    }

    public final void A0(String str, C6330h3 c6330h3) {
        C2254p.l(str);
        C2254p.l(c6330h3);
        n();
        u();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", c6330h3.z());
        contentValues.put("consent_source", Integer.valueOf(c6330h3.b()));
        Z("consent_settings", "app_id", contentValues);
    }

    final SQLiteDatabase B() {
        n();
        try {
            return this.d.getWritableDatabase();
        } catch (SQLiteException e) {
            j().L().b("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x003e  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.database.Cursor] */
    public final String C() throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        ?? B = B();
        try {
            try {
                cursorRawQuery = B.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        cursorRawQuery.close();
                        return null;
                    }
                    String string = cursorRawQuery.getString(0);
                    cursorRawQuery.close();
                    return string;
                } catch (SQLiteException e) {
                    e = e;
                    j().G().b("Database error getting next bundle app id", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                if (B != 0) {
                    B.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            B = 0;
            if (B != 0) {
                B.close();
            }
            throw th;
        }
    }

    public final long C0(String str) {
        C2254p.f(str);
        return G("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    public final int D(String str, String str2) {
        C2254p.f(str);
        C2254p.f(str2);
        n();
        u();
        try {
            return B().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            j().G().d("Error deleting conditional property", V1.v(str), g().g(str2), e);
            return 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0119  */
    public final C6312f D0(String str, String str2) throws Throwable {
        String str3;
        Cursor cursorQuery;
        C2254p.f(str);
        C2254p.f(str2);
        n();
        u();
        Cursor cursor = null;
        try {
            cursorQuery = B().query("conditional_properties", new String[]{"origin", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    String string = cursorQuery.getString(0);
                    if (string == null) {
                        string = HttpUrl.FRAGMENT_ENCODE_SET;
                    }
                    String str4 = string;
                    Object objM = M(cursorQuery, 1);
                    boolean z = cursorQuery.getInt(2) != 0;
                    String string2 = cursorQuery.getString(3);
                    long j2 = cursorQuery.getLong(4);
                    x5 x5VarO = o();
                    byte[] blob = cursorQuery.getBlob(5);
                    Parcelable.Creator<D> creator = D.CREATOR;
                    D d = (D) x5VarO.D(blob, creator);
                    str3 = str2;
                    try {
                        C6312f c6312f = new C6312f(str, str4, new A5(str3, cursorQuery.getLong(8), objM, str4), cursorQuery.getLong(6), z, string2, d, j2, (D) o().D(cursorQuery.getBlob(7), creator), cursorQuery.getLong(9), (D) o().D(cursorQuery.getBlob(10), creator));
                        if (cursorQuery.moveToNext()) {
                            j().G().c("Got multiple records for conditional property, expected one", V1.v(str), g().g(str3));
                        }
                        cursorQuery.close();
                        return c6312f;
                    } catch (SQLiteException e) {
                        e = e;
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    str3 = str2;
                }
            } catch (Throwable th) {
                th = th;
                cursor = cursorQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            str3 = str2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        j().G().d("Error querying conditional property", V1.v(str), g().g(str3), e);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    public final long E(C0938g2 c0938g2) throws IOException {
        n();
        u();
        C2254p.l(c0938g2);
        C2254p.f(c0938g2.P3());
        byte[] bArrL = c0938g2.l();
        long jA = o().A(bArrL);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", c0938g2.P3());
        contentValues.put("metadata_fingerprint", Long.valueOf(jA));
        contentValues.put("metadata", bArrL);
        try {
            B().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return jA;
        } catch (SQLiteException e) {
            j().G().c("Error storing raw event metadata. appId", V1.v(c0938g2.P3()), e);
            throw e;
        }
    }

    public final long F(String str) {
        C2254p.f(str);
        n();
        u();
        try {
            return B().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, d().v(str, F.q))))});
        } catch (SQLiteException e) {
            j().G().c("Error deleting over the limit events. appId", V1.v(str), e);
            return 0L;
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0086  */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x002d: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:10:0x002d */
    public final Bundle F0(String str) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor;
        n();
        u();
        Cursor cursor2 = null;
        try {
            try {
                cursorRawQuery = B().rawQuery("select parameters from default_event_params where app_id=?", new String[]{str});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        j().K().a("Default event parameters not found");
                        cursorRawQuery.close();
                        return null;
                    }
                    try {
                        C0893b2 c0893b2 = (C0893b2) ((AbstractC0994m4) ((C0893b2.a) x5.G(C0893b2.d0(), cursorRawQuery.getBlob(0))).y());
                        o();
                        Bundle bundleB = x5.B(c0893b2.g0());
                        cursorRawQuery.close();
                        return bundleB;
                    } catch (IOException e) {
                        j().G().c("Failed to retrieve default event parameters. appId", V1.v(str), e);
                        cursorRawQuery.close();
                        return null;
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    j().G().b("Error selecting default event parameters", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final C6433y G0(String str, String str2) {
        return E0("events", str, str2);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x008c  */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0031: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:10:0x0031 */
    public final Pair<C0893b2, Long> H(String str, Long l2) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor;
        n();
        u();
        Cursor cursor2 = null;
        try {
            try {
                cursorRawQuery = B().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, String.valueOf(l2)});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        j().K().a("Main event not found");
                        cursorRawQuery.close();
                        return null;
                    }
                    try {
                        Pair<C0893b2, Long> pairCreate = Pair.create((C0893b2) ((AbstractC0994m4) ((C0893b2.a) x5.G(C0893b2.d0(), cursorRawQuery.getBlob(0))).y()), Long.valueOf(cursorRawQuery.getLong(1)));
                        cursorRawQuery.close();
                        return pairCreate;
                    } catch (IOException e) {
                        j().G().d("Failed to merge main event. appId, eventId", V1.v(str), l2, e);
                        cursorRawQuery.close();
                        return null;
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    j().G().b("Error selecting main event", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final Z1 H0(String str) {
        Cursor cursorQuery;
        Boolean boolValueOf;
        C2254p.f(str);
        n();
        u();
        Cursor cursor = null;
        try {
            cursorQuery = B().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token", "sgtm_upload_enabled", "target_os_version", "session_stitching_token_hash", "ad_services_version", "unmatched_first_open_without_ad_id", "npa_metadata_value", "attribution_eligibility_status", "sgtm_preview_key", "dma_consent_state", "daily_realtime_dcu_count", "bundle_delivery_index", "serialized_npa_metadata", "unmatched_pfo", "unmatched_uwa", "ad_campaign_info"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    Z1 z1 = new Z1(this.b.o0(), str);
                    if (!C1013o6.a() || !d().t(F.W0) || this.b.S(str).m(C6330h3.a.ANALYTICS_STORAGE)) {
                        z1.J(cursorQuery.getString(0));
                    }
                    z1.Z(cursorQuery.getString(1));
                    if (!C1013o6.a() || !d().t(F.W0) || this.b.S(str).m(C6330h3.a.AD_STORAGE)) {
                        z1.f0(cursorQuery.getString(2));
                    }
                    z1.A0(cursorQuery.getLong(3));
                    z1.C0(cursorQuery.getLong(4));
                    z1.y0(cursorQuery.getLong(5));
                    z1.S(cursorQuery.getString(6));
                    z1.O(cursorQuery.getString(7));
                    z1.u0(cursorQuery.getLong(8));
                    z1.n0(cursorQuery.getLong(9));
                    z1.K(cursorQuery.isNull(10) || cursorQuery.getInt(10) != 0);
                    z1.k0(cursorQuery.getLong(11));
                    z1.e0(cursorQuery.getLong(12));
                    z1.b0(cursorQuery.getLong(13));
                    z1.V(cursorQuery.getLong(14));
                    z1.R(cursorQuery.getLong(15));
                    z1.s0(cursorQuery.getLong(16));
                    z1.H(cursorQuery.isNull(17) ? -2147483648L : cursorQuery.getInt(17));
                    z1.W(cursorQuery.getString(18));
                    z1.Y(cursorQuery.getLong(19));
                    z1.h0(cursorQuery.getLong(20));
                    z1.c0(cursorQuery.getString(21));
                    z1.h(cursorQuery.isNull(23) || cursorQuery.getInt(23) != 0);
                    z1.f(cursorQuery.getString(24));
                    z1.q0(cursorQuery.isNull(25) ? 0L : cursorQuery.getLong(25));
                    if (!cursorQuery.isNull(26)) {
                        z1.g(Arrays.asList(cursorQuery.getString(26).split(",", -1)));
                    }
                    if (!C1013o6.a() || !d().t(F.W0) || this.b.S(str).m(C6330h3.a.ANALYTICS_STORAGE)) {
                        z1.l0(cursorQuery.getString(28));
                    }
                    if (t7.a() && d().t(F.t0)) {
                        i();
                        if (B5.H0(str)) {
                            z1.P((cursorQuery.isNull(29) || cursorQuery.getInt(29) == 0) ? false : true);
                            z1.w0(cursorQuery.getLong(39));
                            if (d().t(F.u0)) {
                                z1.o0(cursorQuery.getString(36));
                            }
                        }
                    }
                    z1.G0(cursorQuery.getLong(30));
                    z1.E0(cursorQuery.getLong(31));
                    if (C0997m7.a() && d().E(str, F.A0)) {
                        z1.b(cursorQuery.getInt(32));
                        z1.N(cursorQuery.getLong(35));
                    }
                    if (C1021p6.a() && d().E(str, F.L0)) {
                        z1.T((cursorQuery.isNull(33) || cursorQuery.getInt(33) == 0) ? false : true);
                    }
                    if (cursorQuery.isNull(34)) {
                        boolValueOf = null;
                    } else {
                        boolValueOf = Boolean.valueOf(cursorQuery.getInt(34) != 0);
                    }
                    z1.d(boolValueOf);
                    z1.M(cursorQuery.getInt(37));
                    z1.G(cursorQuery.getInt(38));
                    if (C0960i6.a() && d().E(str, F.Q0)) {
                        String string = cursorQuery.getString(40);
                        if (string == null) {
                            string = HttpUrl.FRAGMENT_ENCODE_SET;
                        }
                        z1.i0(string);
                    }
                    if (d().t(F.T0)) {
                        if (!cursorQuery.isNull(41)) {
                            z1.e(Long.valueOf(cursorQuery.getLong(41)));
                        }
                        if (!cursorQuery.isNull(42)) {
                            z1.I(Long.valueOf(cursorQuery.getLong(42)));
                        }
                    }
                    if (O6.a() && d().E(str, F.N0)) {
                        z1.i(cursorQuery.getBlob(43));
                    }
                    z1.x();
                    if (cursorQuery.moveToNext()) {
                        j().G().b("Got multiple records for app, expected one. appId", V1.v(str));
                    }
                    cursorQuery.close();
                    return z1;
                } catch (SQLiteException e) {
                    e = e;
                    j().G().c("Error querying app. appId", V1.v(str), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor = cursorQuery;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
        }
        th = th;
        cursor = cursorQuery;
        if (cursor != null) {
            cursor.close();
        }
        throw th;
    }

    public final C6380p I(long j2, String str, long j3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        C2254p.f(str);
        n();
        u();
        String[] strArr = {str};
        C6380p c6380p = new C6380p();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabaseB = B();
                Cursor cursorQuery = sQLiteDatabaseB.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count", "daily_realtime_dcu_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    j().L().b("Not updating daily counts, app is not known. appId", V1.v(str));
                    cursorQuery.close();
                    return c6380p;
                }
                if (cursorQuery.getLong(0) == j2) {
                    c6380p.b = cursorQuery.getLong(1);
                    c6380p.a = cursorQuery.getLong(2);
                    c6380p.c = cursorQuery.getLong(3);
                    c6380p.d = cursorQuery.getLong(4);
                    c6380p.e = cursorQuery.getLong(5);
                    c6380p.f = cursorQuery.getLong(6);
                }
                if (z) {
                    c6380p.b += j3;
                }
                if (z2) {
                    c6380p.a += j3;
                }
                if (z3) {
                    c6380p.c += j3;
                }
                if (z4) {
                    c6380p.d += j3;
                }
                if (z5) {
                    c6380p.e += j3;
                }
                if (z6) {
                    c6380p.f += j3;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j2));
                contentValues.put("daily_public_events_count", Long.valueOf(c6380p.a));
                contentValues.put("daily_events_count", Long.valueOf(c6380p.b));
                contentValues.put("daily_conversions_count", Long.valueOf(c6380p.c));
                contentValues.put("daily_error_events_count", Long.valueOf(c6380p.d));
                contentValues.put("daily_realtime_events_count", Long.valueOf(c6380p.e));
                contentValues.put("daily_realtime_dcu_count", Long.valueOf(c6380p.f));
                sQLiteDatabaseB.update("apps", contentValues, "app_id=?", strArr);
                cursorQuery.close();
                return c6380p;
            } catch (SQLiteException e) {
                j().G().c("Error updating daily counts. appId", V1.v(str), e);
                if (0 != 0) {
                    cursor.close();
                }
                return c6380p;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursor.close();
            throw th;
        }
    }

    public final C5 I0(String str, String str2) {
        Throwable th;
        String str3;
        String str4;
        SQLiteException sQLiteException;
        Cursor cursorQuery;
        C2254p.f(str);
        C2254p.f(str2);
        n();
        u();
        Cursor cursor = null;
        try {
            cursorQuery = B().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    long j2 = cursorQuery.getLong(0);
                    Object objM = M(cursorQuery, 1);
                    if (objM == null) {
                        cursorQuery.close();
                        return null;
                    }
                    str3 = str;
                    str4 = str2;
                    try {
                        C5 c5 = new C5(str3, cursorQuery.getString(2), str4, j2, objM);
                        if (cursorQuery.moveToNext()) {
                            j().G().b("Got multiple records for user property, expected one. appId", V1.v(str3));
                        }
                        cursorQuery.close();
                        return c5;
                    } catch (SQLiteException e) {
                        e = e;
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    str3 = str;
                    str4 = str2;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorQuery;
                if (cursor == null) {
                    throw th;
                }
                cursor.close();
                throw th;
            }
            sQLiteException = e;
        } catch (SQLiteException e3) {
            str3 = str;
            str4 = str2;
            sQLiteException = e3;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
        }
        j().G().d("Error querying user property. appId", V1.v(str3), g().g(str4), sQLiteException);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    public final C6380p J(long j2, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return I(j2, str, 1L, false, false, z3, false, z5, z6);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x008b  */
    /* JADX WARN: Code duplicated, block: B:38:? A[SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x005a: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:14:0x0059 */
    public final C6361m J0(String str) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        Cursor cursor;
        C2254p.f(str);
        n();
        u();
        Cursor cursor2 = null;
        try {
            try {
                cursorQuery = B().query("apps", new String[]{"remote_config", "config_last_modified_time", "e_tag"}, "app_id=?", new String[]{str}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    byte[] blob = cursorQuery.getBlob(0);
                    String string = cursorQuery.getString(1);
                    String string2 = cursorQuery.getString(2);
                    if (cursorQuery.moveToNext()) {
                        j().G().b("Got multiple records for app config, expected one. appId", V1.v(str));
                    }
                    if (blob == null) {
                        cursorQuery.close();
                        return null;
                    }
                    C6361m c6361m = new C6361m(blob, string, string2);
                    cursorQuery.close();
                    return c6361m;
                } catch (SQLiteException e) {
                    e = e;
                    j().G().c("Error querying remote config. appId", V1.v(str), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor2 = cursor;
                if (cursor2 != null) {
                    throw th;
                }
                cursor2.close();
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                throw th;
            }
            cursor2.close();
            throw th;
        }
    }

    final Map<Integer, List<ax.H6.D1>> K0(String str, String str2) {
        u();
        n();
        C2254p.f(str);
        C2254p.f(str2);
        ax.B.a aVar = new ax.B.a();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = B().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<ax.H6.D1>> map = Collections.EMPTY_MAP;
                    cursorQuery.close();
                    return map;
                }
                do {
                    try {
                        ax.H6.D1 d1 = (ax.H6.D1) ((AbstractC0994m4) ((ax.H6.D1.a) x5.G(ax.H6.D1.P(), cursorQuery.getBlob(1))).y());
                        int i2 = cursorQuery.getInt(0);
                        List arrayList = (List) aVar.get(Integer.valueOf(i2));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            aVar.put(Integer.valueOf(i2), arrayList);
                        }
                        arrayList.add(d1);
                    } catch (IOException e) {
                        j().G().c("Failed to merge filter. appId", V1.v(str), e);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return aVar;
            } catch (SQLiteException e2) {
                j().G().c("Database error querying filters. appId", V1.v(str), e2);
                Map<Integer, List<ax.H6.D1>> map2 = Collections.EMPTY_MAP;
                if (0 != 0) {
                    cursor.close();
                }
                return map2;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursor.close();
            throw th;
        }
    }

    public final C6409u L0(String str) {
        C2254p.l(str);
        n();
        u();
        return C6409u.d(P("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, HttpUrl.FRAGMENT_ENCODE_SET));
    }

    final Map<Integer, List<ax.H6.G1>> M0(String str, String str2) {
        u();
        n();
        C2254p.f(str);
        C2254p.f(str2);
        ax.B.a aVar = new ax.B.a();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = B().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<ax.H6.G1>> map = Collections.EMPTY_MAP;
                    cursorQuery.close();
                    return map;
                }
                do {
                    try {
                        ax.H6.G1 g1 = (ax.H6.G1) ((AbstractC0994m4) ((ax.H6.G1.a) x5.G(ax.H6.G1.M(), cursorQuery.getBlob(1))).y());
                        int i2 = cursorQuery.getInt(0);
                        List arrayList = (List) aVar.get(Integer.valueOf(i2));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            aVar.put(Integer.valueOf(i2), arrayList);
                        }
                        arrayList.add(g1);
                    } catch (IOException e) {
                        j().G().c("Failed to merge filter", V1.v(str), e);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return aVar;
            } catch (SQLiteException e2) {
                j().G().c("Database error querying filters. appId", V1.v(str), e2);
                Map<Integer, List<ax.H6.G1>> map2 = Collections.EMPTY_MAP;
                if (0 != 0) {
                    cursor.close();
                }
                return map2;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursor.close();
            throw th;
        }
    }

    public final C6330h3 N0(String str) {
        C2254p.l(str);
        n();
        u();
        return C6330h3.q(P("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, HttpUrl.FRAGMENT_ENCODE_SET));
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0057  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r4v0, types: [long] */
    public final String O(long j2) throws Throwable {
        Cursor cursorRawQuery;
        n();
        u();
        ?? r0 = 0;
        try {
            try {
                cursorRawQuery = B().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf((long) j2)});
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        String string = cursorRawQuery.getString(0);
                        cursorRawQuery.close();
                        return string;
                    }
                    j().K().a("No expired configs for apps with pending events");
                    cursorRawQuery.close();
                    return null;
                } catch (SQLiteException e) {
                    e = e;
                    j().G().b("Error selecting expired configs", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                r0 = j2;
                if (r0 != 0) {
                    r0.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
    }

    public final void O0(String str, String str2) {
        C2254p.f(str);
        C2254p.f(str2);
        n();
        u();
        try {
            B().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            j().G().d("Error deleting user property. appId", V1.v(str), g().g(str2), e);
        }
    }

    public final C6330h3 P0(String str) {
        C2254p.l(str);
        n();
        u();
        C6330h3 c6330h3 = (C6330h3) N("select consent_state, consent_source from consent_settings where app_id=? limit 1;", new String[]{str}, new r() { // from class: com.google.android.gms.measurement.internal.n
            @Override // com.google.android.gms.measurement.internal.r
            public final Object a(Cursor cursor) {
                return C6330h3.i(cursor.getString(0), cursor.getInt(1));
            }
        });
        return c6330h3 == null ? C6330h3.c : c6330h3;
    }

    public final List<Pair<C0938g2, Long>> Q(String str, int i2, int i3) {
        long jY;
        long jY2;
        n();
        u();
        int i4 = 1;
        C2254p.a(i2 > 0);
        C2254p.a(i3 > 0);
        C2254p.f(str);
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = B().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i2));
                if (!cursorQuery.moveToFirst()) {
                    List<Pair<C0938g2, Long>> list = Collections.EMPTY_LIST;
                    cursorQuery.close();
                    return list;
                }
                ArrayList arrayList = new ArrayList();
                int length = 0;
                while (true) {
                    long j2 = cursorQuery.getLong(0);
                    try {
                        byte[] bArrK0 = o().k0(cursorQuery.getBlob(i4));
                        if (!arrayList.isEmpty() && bArrK0.length + length > i3) {
                            break;
                        }
                        try {
                            C0938g2.a aVar = (C0938g2.a) x5.G(C0938g2.M3(), bArrK0);
                            if (!arrayList.isEmpty()) {
                                C0938g2 c0938g2 = (C0938g2) ((Pair) arrayList.get(0)).first;
                                C0938g2 c0938g3 = (C0938g2) ((AbstractC0994m4) aVar.y());
                                if (!c0938g2.n0().equals(c0938g3.n0()) || !c0938g2.m0().equals(c0938g3.m0()) || c0938g2.D0() != c0938g3.D0() || !c0938g2.o0().equals(c0938g3.o0())) {
                                    break;
                                }
                                Iterator<C0974k2> it = c0938g2.B0().iterator();
                                while (true) {
                                    jY = -1;
                                    if (!it.hasNext()) {
                                        jY2 = -1;
                                        break;
                                    }
                                    C0974k2 next = it.next();
                                    if ("_npa".equals(next.d0())) {
                                        jY2 = next.Y();
                                        break;
                                    }
                                }
                                for (C0974k2 c0974k2 : c0938g3.B0()) {
                                    if ("_npa".equals(c0974k2.d0())) {
                                        jY = c0974k2.Y();
                                        break;
                                    }
                                }
                                if (jY2 != jY) {
                                    break;
                                }
                            }
                            if (!cursorQuery.isNull(2)) {
                                aVar.G0(cursorQuery.getInt(2));
                            }
                            length += bArrK0.length;
                            arrayList.add(Pair.create((C0938g2) ((AbstractC0994m4) aVar.y()), Long.valueOf(j2)));
                        } catch (IOException e) {
                            j().G().c("Failed to merge queued bundle. appId", V1.v(str), e);
                        }
                        if (!cursorQuery.moveToNext() || length > i3) {
                            break;
                        }
                        i4 = 1;
                    } catch (IOException e2) {
                        j().G().c("Failed to unzip queued bundle. appId", V1.v(str), e2);
                    }
                }
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e3) {
                j().G().c("Error querying bundles. appId", V1.v(str), e3);
                List<Pair<C0938g2, Long>> list2 = Collections.EMPTY_LIST;
                if (0 != 0) {
                    cursor.close();
                }
                return list2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<C6312f> R(String str, String str2, String str3) {
        C2254p.f(str);
        n();
        u();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3 + "*");
            sb.append(" and name glob ?");
        }
        return S(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<C6332h5> R0(String str) {
        C2254p.f(str);
        n();
        u();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = B().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", "source"}, "app_id=?", new String[]{str}, null, null, "rowid", null);
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    String string = cursorQuery.getString(0);
                    if (string == null) {
                        string = HttpUrl.FRAGMENT_ENCODE_SET;
                    }
                    arrayList.add(new C6332h5(string, cursorQuery.getLong(1), cursorQuery.getInt(2)));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e) {
                j().G().c("Error querying trigger uris. appId", V1.v(str), e);
                List<C6332h5> list = Collections.EMPTY_LIST;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return list;
            }
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }

    public final List<C6312f> S(String str, String[] strArr) {
        n();
        u();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = B().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, str, strArr, null, null, "rowid", "1001");
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                while (arrayList.size() < 1000) {
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    String string3 = cursorQuery.getString(2);
                    Object objM = M(cursorQuery, 3);
                    boolean z = cursorQuery.getInt(4) != 0;
                    String string4 = cursorQuery.getString(5);
                    long j2 = cursorQuery.getLong(6);
                    x5 x5VarO = o();
                    byte[] blob = cursorQuery.getBlob(7);
                    Parcelable.Creator<D> creator = D.CREATOR;
                    D d = (D) x5VarO.D(blob, creator);
                    arrayList.add(new C6312f(string, string2, new A5(string3, cursorQuery.getLong(10), objM, string2), cursorQuery.getLong(8), z, string4, d, j2, (D) o().D(cursorQuery.getBlob(9), creator), cursorQuery.getLong(11), (D) o().D(cursorQuery.getBlob(12), creator)));
                    if (!cursorQuery.moveToNext()) {
                        cursorQuery.close();
                        return arrayList;
                    }
                }
                j().G().b("Read more than the max allowed conditional properties, ignoring extra", 1000);
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e) {
                j().G().b("Error querying conditional user property value", e);
                List<C6312f> list = Collections.EMPTY_LIST;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return list;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public final List<C5> S0(String str) {
        String str2;
        C2254p.f(str);
        n();
        u();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = B().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                while (true) {
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    if (string2 == null) {
                        string2 = HttpUrl.FRAGMENT_ENCODE_SET;
                    }
                    String str3 = string2;
                    long j2 = cursorQuery.getLong(2);
                    Object objM = M(cursorQuery, 3);
                    if (objM == null) {
                        j().G().b("Read invalid user property value, ignoring it. appId", V1.v(str));
                        str2 = str;
                    } else {
                        str2 = str;
                        arrayList.add(new C5(str2, str3, string, j2, objM));
                    }
                    try {
                        if (!cursorQuery.moveToNext()) {
                            cursorQuery.close();
                            return arrayList;
                        }
                        str = str2;
                    } catch (SQLiteException e) {
                        e = e;
                    }
                }
            } catch (Throwable th) {
                if (cursorQuery == null) {
                    throw th;
                }
                cursorQuery.close();
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            str2 = str;
        }
        j().G().c("Error querying user properties. appId", V1.v(str2), e);
        List<C5> list = Collections.EMPTY_LIST;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return list;
    }

    final Map<Integer, C0956i2> T0(String str) {
        u();
        n();
        C2254p.f(str);
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = B().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, C0956i2> map = Collections.EMPTY_MAP;
                    cursorQuery.close();
                    return map;
                }
                ax.B.a aVar = new ax.B.a();
                do {
                    int i2 = cursorQuery.getInt(0);
                    try {
                        aVar.put(Integer.valueOf(i2), (C0956i2) ((AbstractC0994m4) ((C0956i2.a) x5.G(C0956i2.Z(), cursorQuery.getBlob(1))).y()));
                    } catch (IOException e) {
                        j().G().d("Failed to merge filter results. appId, audienceId, error", V1.v(str), Integer.valueOf(i2), e);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return aVar;
            } catch (SQLiteException e2) {
                j().G().c("Database error querying filter results. appId", V1.v(str), e2);
                Map<Integer, C0956i2> map2 = Collections.EMPTY_MAP;
                if (0 != 0) {
                    cursor.close();
                }
                return map2;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursor.close();
            throw th;
        }
    }

    public final void U(C6433y c6433y) {
        X("events", c6433y);
    }

    final Map<Integer, List<ax.H6.D1>> U0(String str) {
        C2254p.f(str);
        ax.B.a aVar = new ax.B.a();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = B().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<ax.H6.D1>> map = Collections.EMPTY_MAP;
                    cursorQuery.close();
                    return map;
                }
                do {
                    try {
                        ax.H6.D1 d1 = (ax.H6.D1) ((AbstractC0994m4) ((ax.H6.D1.a) x5.G(ax.H6.D1.P(), cursorQuery.getBlob(1))).y());
                        if (d1.Y()) {
                            int i2 = cursorQuery.getInt(0);
                            List arrayList = (List) aVar.get(Integer.valueOf(i2));
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                aVar.put(Integer.valueOf(i2), arrayList);
                            }
                            arrayList.add(d1);
                        }
                    } catch (IOException e) {
                        j().G().c("Failed to merge filter. appId", V1.v(str), e);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return aVar;
            } catch (SQLiteException e2) {
                j().G().c("Database error querying filters. appId", V1.v(str), e2);
                Map<Integer, List<ax.H6.D1>> map2 = Collections.EMPTY_MAP;
                if (0 != 0) {
                    cursor.close();
                }
                return map2;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursor.close();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0045  */
    public final void V(Z1 z1, boolean z, boolean z2) {
        C2254p.l(z1);
        n();
        u();
        String strL = z1.l();
        C2254p.l(strL);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", strL);
        if (!C1013o6.a() || !d().t(F.W0)) {
            contentValues.put("app_instance_id", z1.m());
        } else if (z) {
            contentValues.put("app_instance_id", (String) null);
        } else if (this.b.S(strL).m(C6330h3.a.ANALYTICS_STORAGE)) {
            contentValues.put("app_instance_id", z1.m());
        }
        contentValues.put("gmp_app_id", z1.q());
        if (!C1013o6.a() || !d().t(F.W0) || this.b.S(strL).m(C6330h3.a.AD_STORAGE)) {
            contentValues.put("resettable_device_id_hash", z1.s());
        }
        contentValues.put("last_bundle_index", Long.valueOf(z1.F0()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(z1.H0()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(z1.D0()));
        contentValues.put("app_version", z1.o());
        contentValues.put("app_store", z1.n());
        contentValues.put("gmp_version", Long.valueOf(z1.z0()));
        contentValues.put("dev_cert_hash", Long.valueOf(z1.t0()));
        contentValues.put("measurement_enabled", Boolean.valueOf(z1.A()));
        contentValues.put("day", Long.valueOf(z1.r0()));
        contentValues.put("daily_public_events_count", Long.valueOf(z1.m0()));
        contentValues.put("daily_events_count", Long.valueOf(z1.j0()));
        contentValues.put("daily_conversions_count", Long.valueOf(z1.d0()));
        contentValues.put("config_fetched_time", Long.valueOf(z1.a0()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(z1.x0()));
        contentValues.put("app_version_int", Long.valueOf(z1.U()));
        contentValues.put("firebase_instance_id", z1.p());
        contentValues.put("daily_error_events_count", Long.valueOf(z1.g0()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(z1.p0()));
        contentValues.put("health_monitor_sample", z1.r());
        contentValues.put("android_id", Long.valueOf(z1.Q()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(z1.z()));
        contentValues.put("admob_app_id", z1.j());
        contentValues.put("dynamite_version", Long.valueOf(z1.v0()));
        if (!C1013o6.a() || !d().t(F.W0) || this.b.S(strL).m(C6330h3.a.ANALYTICS_STORAGE)) {
            contentValues.put("session_stitching_token", z1.u());
        }
        contentValues.put("sgtm_upload_enabled", Boolean.valueOf(z1.C()));
        contentValues.put("target_os_version", Long.valueOf(z1.J0()));
        contentValues.put("session_stitching_token_hash", Long.valueOf(z1.I0()));
        if (C0997m7.a() && d().E(strL, F.A0)) {
            contentValues.put("ad_services_version", Integer.valueOf(z1.a()));
            contentValues.put("attribution_eligibility_status", Long.valueOf(z1.X()));
        }
        if (C1021p6.a() && d().E(strL, F.L0)) {
            contentValues.put("unmatched_first_open_without_ad_id", Boolean.valueOf(z1.D()));
        }
        contentValues.put("npa_metadata_value", z1.K0());
        if (t7.a() && d().E(strL, F.t0)) {
            i();
            if (B5.H0(strL)) {
                contentValues.put("bundle_delivery_index", Long.valueOf(z1.B0()));
            }
        }
        if (t7.a() && d().E(strL, F.u0)) {
            contentValues.put("sgtm_preview_key", z1.v());
        }
        contentValues.put("dma_consent_state", Integer.valueOf(z1.L()));
        contentValues.put("daily_realtime_dcu_count", Integer.valueOf(z1.F()));
        if (C0960i6.a() && d().E(strL, F.Q0)) {
            contentValues.put("serialized_npa_metadata", z1.t());
        }
        List<String> listW = z1.w();
        if (listW != null) {
            if (listW.isEmpty()) {
                j().L().b("Safelisted events should not be an empty list. appId", strL);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", listW));
            }
        }
        if (A6.a() && d().t(F.q0) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        if (d().t(F.T0)) {
            contentValues.put("unmatched_pfo", z1.L0());
            contentValues.put("unmatched_uwa", z1.M0());
        }
        if (O6.a() && d().E(strL, F.N0)) {
            contentValues.put("ad_campaign_info", z1.E());
        }
        try {
            SQLiteDatabase sQLiteDatabaseB = B();
            if (sQLiteDatabaseB.update("apps", contentValues, "app_id = ?", new String[]{strL}) == 0 && sQLiteDatabaseB.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                j().G().b("Failed to insert/update app (got -1). appId", V1.v(strL));
            }
        } catch (SQLiteException e) {
            j().G().c("Error storing app. appId", V1.v(strL), e);
        }
    }

    final Map<Integer, List<Integer>> V0(String str) {
        u();
        n();
        C2254p.f(str);
        ax.B.a aVar = new ax.B.a();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = B().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
                if (!cursorRawQuery.moveToFirst()) {
                    Map<Integer, List<Integer>> map = Collections.EMPTY_MAP;
                    cursorRawQuery.close();
                    return map;
                }
                do {
                    int i2 = cursorRawQuery.getInt(0);
                    List arrayList = (List) aVar.get(Integer.valueOf(i2));
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        aVar.put(Integer.valueOf(i2), arrayList);
                    }
                    arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                } while (cursorRawQuery.moveToNext());
                cursorRawQuery.close();
                return aVar;
            } catch (SQLiteException e) {
                j().G().c("Database error querying scoped filters. appId", V1.v(str), e);
                Map<Integer, List<Integer>> map2 = Collections.EMPTY_MAP;
                if (0 != 0) {
                    cursor.close();
                }
                return map2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void W(String str, C6409u c6409u) {
        C2254p.l(str);
        C2254p.l(c6409u);
        n();
        u();
        if (d().t(F.K0)) {
            C6330h3 c6330h3P0 = P0(str);
            C6330h3 c6330h3 = C6330h3.c;
            if (c6330h3P0 == c6330h3) {
                A0(str, c6330h3);
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", c6409u.j());
        Z("consent_settings", "app_id", contentValues);
    }

    public final void W0(String str) {
        C6433y c6433yG0;
        Q0("events_snapshot", str);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = B().query("events", (String[]) Collections.singletonList("name").toArray(new String[0]), "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return;
                }
                do {
                    String string = cursorQuery.getString(0);
                    if (string != null && (c6433yG0 = G0(str, string)) != null) {
                        X("events_snapshot", c6433yG0);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
            } catch (SQLiteException e) {
                j().G().c("Error creating snapshot. appId", V1.v(str), e);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }

    public final void X0() {
        u();
        B().beginTransaction();
    }

    public final void Y(String str, C6330h3 c6330h3) {
        C2254p.l(str);
        C2254p.l(c6330h3);
        n();
        u();
        A0(str, P0(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", c6330h3.z());
        Z("consent_settings", "app_id", contentValues);
    }

    /* JADX WARN: Code duplicated, block: B:65:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:70:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:71:0x0108 A[ADDED_TO_REGION] */
    public final void Y0(String str) throws Throwable {
        boolean z;
        C6433y c6433yE0;
        ArrayList arrayList = new ArrayList(Arrays.asList("name", "lifetime_count"));
        C6433y c6433yG0 = G0(str, "_f");
        C6433y c6433yG1 = G0(str, "_v");
        Q0("events", str);
        boolean z2 = false;
        Cursor cursorQuery = null;
        try {
            cursorQuery = B().query("events_snapshot", (String[]) arrayList.toArray(new String[0]), "app_id=?", new String[]{str}, null, null, null);
            if (!cursorQuery.moveToFirst()) {
                cursorQuery.close();
                if (c6433yG0 != null) {
                    X("events", c6433yG0);
                } else if (c6433yG1 != null) {
                    X("events", c6433yG1);
                }
                Q0("events_snapshot", str);
                return;
            }
            boolean z3 = false;
            z = false;
            do {
                try {
                    String string = cursorQuery.getString(0);
                    if (d().t(F.Y0)) {
                        if (cursorQuery.getLong(1) >= 1) {
                            if ("_f".equals(string)) {
                                z3 = true;
                            } else if ("_v".equals(string)) {
                                z = true;
                            }
                        }
                    } else if ("_f".equals(string)) {
                        z3 = true;
                    } else if ("_v".equals(string)) {
                        z = true;
                    }
                    if (string != null && (c6433yE0 = E0("events_snapshot", str, string)) != null) {
                        X("events", c6433yE0);
                    }
                } catch (SQLiteException e) {
                    e = e;
                    z2 = z3;
                    try {
                        j().G().c("Error querying snapshot. appId", V1.v(str), e);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        if (!z2 && c6433yG0 != null) {
                            X("events", c6433yG0);
                        } else if (!z && c6433yG1 != null) {
                            X("events", c6433yG1);
                        }
                        Q0("events_snapshot", str);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        if (z2 && c6433yG0 != null) {
                            X("events", c6433yG0);
                        } else if (!z && c6433yG1 != null) {
                            X("events", c6433yG1);
                        }
                        Q0("events_snapshot", str);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z2 = z3;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    if (z2) {
                        if (!z) {
                            X("events", c6433yG1);
                        }
                    } else if (!z) {
                        X("events", c6433yG1);
                    }
                    Q0("events_snapshot", str);
                    throw th;
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
            if (!z3 && c6433yG0 != null) {
                X("events", c6433yG0);
            } else if (!z && c6433yG1 != null) {
                X("events", c6433yG1);
            }
            Q0("events_snapshot", str);
        } catch (SQLiteException e2) {
            e = e2;
            z = false;
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    public final void Z0() {
        u();
        B().endTransaction();
    }

    final void a0(String str, List<ax.H6.C1> list) {
        boolean z;
        boolean z2;
        C2254p.l(list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            ax.H6.C1.a aVarB = list.get(i2).B();
            if (aVarB.v() != 0) {
                for (int i3 = 0; i3 < aVarB.v(); i3++) {
                    ax.H6.D1.a aVarB2 = aVarB.B(i3).B();
                    ax.H6.D1.a aVar = (ax.H6.D1.a) ((AbstractC0994m4.b) aVarB2.clone());
                    String strB = ax.M6.q.b(aVarB2.C());
                    if (strB != null) {
                        aVar.z(strB);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    for (int i4 = 0; i4 < aVarB2.v(); i4++) {
                        ax.H6.E1 e1B = aVarB2.B(i4);
                        String strA = ax.M6.p.a(e1B.P());
                        if (strA != null) {
                            aVar.w(i4, (ax.H6.E1) ((AbstractC0994m4) e1B.B().v(strA).y()));
                            z2 = true;
                        }
                    }
                    if (z2) {
                        ax.H6.C1.a aVarW = aVarB.w(i3, aVar);
                        list.set(i2, (ax.H6.C1) ((AbstractC0994m4) aVarW.y()));
                        aVarB = aVarW;
                    }
                }
            }
            if (aVarB.C() != 0) {
                for (int i5 = 0; i5 < aVarB.C(); i5++) {
                    ax.H6.G1 g1D = aVarB.D(i5);
                    String strA2 = ax.M6.s.a(g1D.O());
                    if (strA2 != null) {
                        aVarB = aVarB.z(i5, g1D.B().v(strA2));
                        list.set(i2, (ax.H6.C1) ((AbstractC0994m4) aVarB.y()));
                    }
                }
            }
        }
        u();
        n();
        C2254p.f(str);
        C2254p.l(list);
        SQLiteDatabase sQLiteDatabaseB = B();
        sQLiteDatabaseB.beginTransaction();
        try {
            u();
            n();
            C2254p.f(str);
            SQLiteDatabase sQLiteDatabaseB2 = B();
            sQLiteDatabaseB2.delete("property_filters", "app_id=?", new String[]{str});
            sQLiteDatabaseB2.delete("event_filters", "app_id=?", new String[]{str});
            for (ax.H6.C1 c1 : list) {
                u();
                n();
                C2254p.f(str);
                C2254p.l(c1);
                if (c1.V()) {
                    int iN = c1.n();
                    Iterator<ax.H6.D1> it = c1.T().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().Z()) {
                                j().L().c("Event filter with no ID. Audience definition ignored. appId, audienceId", V1.v(str), Integer.valueOf(iN));
                                break;
                            }
                        } else {
                            Iterator<ax.H6.G1> it2 = c1.U().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    Iterator<ax.H6.D1> it3 = c1.T().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!g0(str, iN, it3.next())) {
                                                z = false;
                                                break;
                                            }
                                        } else {
                                            z = true;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator<ax.H6.G1> it4 = c1.U().iterator();
                                        while (it4.hasNext()) {
                                            if (!h0(str, iN, it4.next())) {
                                                z = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        u();
                                        n();
                                        C2254p.f(str);
                                        SQLiteDatabase sQLiteDatabaseB3 = B();
                                        sQLiteDatabaseB3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iN)});
                                        sQLiteDatabaseB3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iN)});
                                        break;
                                    }
                                    break;
                                }
                                if (!it2.next().T()) {
                                    j().L().c("Property filter with no ID. Audience definition ignored. appId, audienceId", V1.v(str), Integer.valueOf(iN));
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    j().L().b("Audience with no ID. appId", V1.v(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (ax.H6.C1 c2 : list) {
                arrayList.add(c2.V() ? Integer.valueOf(c2.n()) : null);
            }
            B0(str, arrayList);
            sQLiteDatabaseB.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseB.endTransaction();
        }
    }

    final void a1() {
        int iDelete;
        n();
        u();
        if (l0()) {
            long jA = s().e.a();
            long jB = b().b();
            if (Math.abs(jB - jA) > F.z.a(null).longValue()) {
                s().e.b(jB);
                n();
                u();
                if (!l0() || (iDelete = B().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(b().a()), String.valueOf(C6326h.O())})) <= 0) {
                    return;
                }
                j().K().b("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
            }
        }
    }

    final void b0(List<Long> list) {
        n();
        u();
        C2254p.l(list);
        C2254p.n(list.size());
        if (l0()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (y0("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", null) > 0) {
                j().L().a("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                B().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                j().G().b("Error incrementing retry count. error", e);
            }
        }
    }

    public final void b1() {
        u();
        B().setTransactionSuccessful();
    }

    public final boolean c0(C0938g2 c0938g2, boolean z) {
        n();
        u();
        C2254p.l(c0938g2);
        C2254p.f(c0938g2.P3());
        C2254p.o(c0938g2.d1());
        a1();
        long jA = b().a();
        if (c0938g2.o3() < jA - C6326h.O() || c0938g2.o3() > C6326h.O() + jA) {
            j().L().d("Storing bundle outside of the max uploading time span. appId, now, timestamp", V1.v(c0938g2.P3()), Long.valueOf(jA), Long.valueOf(c0938g2.o3()));
        }
        try {
            byte[] bArrI0 = o().i0(c0938g2.l());
            j().K().b("Saving bundle, size", Integer.valueOf(bArrI0.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", c0938g2.P3());
            contentValues.put("bundle_end_timestamp", Long.valueOf(c0938g2.o3()));
            contentValues.put("data", bArrI0);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (c0938g2.k1()) {
                contentValues.put("retry_count", Integer.valueOf(c0938g2.v2()));
            }
            try {
                if (B().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                j().G().b("Failed to insert bundle (got -1). appId", V1.v(c0938g2.P3()));
                return false;
            } catch (SQLiteException e) {
                j().G().c("Error storing bundle. appId", V1.v(c0938g2.P3()), e);
                return false;
            }
        } catch (IOException e2) {
            j().G().c("Data loss. Failed to serialize bundle. appId", V1.v(c0938g2.P3()), e2);
            return false;
        }
    }

    public final boolean c1() {
        return y0("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean d0(C6312f c6312f) {
        C2254p.l(c6312f);
        n();
        u();
        String str = c6312f.q;
        C2254p.l(str);
        if (I0(str, c6312f.Y.X) == null && y0("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", c6312f.X);
        contentValues.put("name", c6312f.Y.X);
        T(contentValues, "value", C2254p.l(c6312f.Y.a()));
        contentValues.put("active", Boolean.valueOf(c6312f.k0));
        contentValues.put("trigger_event_name", c6312f.l0);
        contentValues.put("trigger_timeout", Long.valueOf(c6312f.n0));
        i();
        contentValues.put("timed_out_event", B5.q0(c6312f.m0));
        contentValues.put("creation_timestamp", Long.valueOf(c6312f.Z));
        i();
        contentValues.put("triggered_event", B5.q0(c6312f.o0));
        contentValues.put("triggered_timestamp", Long.valueOf(c6312f.Y.Y));
        contentValues.put("time_to_live", Long.valueOf(c6312f.p0));
        i();
        contentValues.put("expired_event", B5.q0(c6312f.q0));
        try {
            if (B().insertWithOnConflict("conditional_properties", null, contentValues, 5) != -1) {
                return true;
            }
            j().G().b("Failed to insert/update conditional user property (got -1)", V1.v(str));
            return true;
        } catch (SQLiteException e) {
            j().G().c("Error storing conditional user property", V1.v(str), e);
            return true;
        }
    }

    public final boolean d1() {
        return y0("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean e0(A a, long j2, boolean z) {
        n();
        u();
        C2254p.l(a);
        C2254p.f(a.a);
        byte[] bArrL = o().E(a).l();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", a.a);
        contentValues.put("name", a.b);
        contentValues.put("timestamp", Long.valueOf(a.d));
        contentValues.put("metadata_fingerprint", Long.valueOf(j2));
        contentValues.put("data", bArrL);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (B().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            j().G().b("Failed to insert raw event (got -1). appId", V1.v(a.a));
            return false;
        } catch (SQLiteException e) {
            j().G().c("Error storing raw event. appId", V1.v(a.a), e);
            return false;
        }
    }

    public final boolean e1() {
        return y0("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final boolean f0(C5 c5) {
        C2254p.l(c5);
        n();
        u();
        if (I0(c5.a, c5.c) == null) {
            if (B5.L0(c5.c)) {
                if (y0("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{c5.a}) >= d().q(c5.a, F.H, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(c5.c) && y0("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{c5.a, c5.b}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", c5.a);
        contentValues.put("origin", c5.b);
        contentValues.put("name", c5.c);
        contentValues.put("set_timestamp", Long.valueOf(c5.d));
        T(contentValues, "value", c5.e);
        try {
            if (B().insertWithOnConflict("user_attributes", null, contentValues, 5) != -1) {
                return true;
            }
            j().G().b("Failed to insert/update user property (got -1). appId", V1.v(c5.a));
            return true;
        } catch (SQLiteException e) {
            j().G().c("Error storing user property. appId", V1.v(c5.a), e);
            return true;
        }
    }

    final boolean i0(String str, Bundle bundle) {
        n();
        u();
        byte[] bArrL = o().E(new A(this.a, HttpUrl.FRAGMENT_ENCODE_SET, str, "dep", 0L, 0L, bundle)).l();
        j().K().c("Saving default event parameters, appId, data size", g().c(str), Integer.valueOf(bArrL.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", bArrL);
        try {
            if (B().insertWithOnConflict("default_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            j().G().b("Failed to insert default event parameters (got -1). appId", V1.v(str));
            return false;
        } catch (SQLiteException e) {
            j().G().c("Error storing default event parameters. appId", V1.v(str), e);
            return false;
        }
    }

    public final boolean j0(String str, C6332h5 c6332h5) {
        n();
        u();
        C2254p.l(c6332h5);
        C2254p.f(str);
        long jA = b().a();
        if (c6332h5.X < jA - C6326h.O() || c6332h5.X > C6326h.O() + jA) {
            j().L().d("Storing trigger URI outside of the max retention time span. appId, now, timestamp", V1.v(str), Long.valueOf(jA), Long.valueOf(c6332h5.X));
        }
        j().K().a("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", c6332h5.q);
        contentValues.put("source", Integer.valueOf(c6332h5.Y));
        contentValues.put("timestamp_millis", Long.valueOf(c6332h5.X));
        try {
            if (B().insert("trigger_uris", null, contentValues) != -1) {
                return true;
            }
            j().G().b("Failed to insert trigger URI (got -1). appId", V1.v(str));
            return false;
        } catch (SQLiteException e) {
            j().G().c("Error storing trigger URI. appId", V1.v(str), e);
            return false;
        }
    }

    public final boolean k0(String str, Long l2, long j2, C0893b2 c0893b2) {
        n();
        u();
        C2254p.l(c0893b2);
        C2254p.f(str);
        C2254p.l(l2);
        byte[] bArrL = c0893b2.l();
        j().K().c("Saving complex main event, appId, data size", g().c(str), Integer.valueOf(bArrL.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l2);
        contentValues.put("children_to_process", Long.valueOf(j2));
        contentValues.put("main_event", bArrL);
        try {
            if (B().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            j().G().b("Failed to insert complex main event (got -1). appId", V1.v(str));
            return false;
        } catch (SQLiteException e) {
            j().G().c("Error storing complex main event. appId", V1.v(str), e);
            return false;
        }
    }

    protected final boolean l0() {
        return a().getDatabasePath("google_app_measurement.db").exists();
    }

    public final long w0(String str) {
        C2254p.f(str);
        n();
        u();
        return G("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        return false;
    }

    protected final long x0(String str, String str2) {
        C2254p.f(str);
        C2254p.f(str2);
        n();
        u();
        SQLiteDatabase sQLiteDatabaseB = B();
        sQLiteDatabaseB.beginTransaction();
        long j2 = 0;
        try {
            try {
                long jG = G("select " + str2 + " from app2 where app_id=?", new String[]{str}, -1L);
                if (jG == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("first_open_count", (Integer) 0);
                    contentValues.put("previous_install_count", (Integer) 0);
                    if (sQLiteDatabaseB.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                        j().G().c("Failed to insert column (got -1). appId", V1.v(str), str2);
                        return -1L;
                    }
                    jG = 0;
                    j().G().d("Error inserting column. appId", V1.v(str), str2, e);
                    return j2;
                }
                try {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put(str2, Long.valueOf(1 + jG));
                    if (sQLiteDatabaseB.update("app2", contentValues2, "app_id = ?", new String[]{str}) == 0) {
                        j().G().c("Failed to update column (got 0). appId", V1.v(str), str2);
                        return -1L;
                    }
                    sQLiteDatabaseB.setTransactionSuccessful();
                    return jG;
                } catch (SQLiteException e) {
                    e = e;
                    j2 = jG;
                }
            } catch (SQLiteException e2) {
                e = e2;
            }
        } finally {
            sQLiteDatabaseB.endTransaction();
        }
    }

    public final long y() {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = B().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return -1L;
                }
                long j2 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j2;
            } catch (SQLiteException e) {
                j().G().b("Error querying raw events", e);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return -1L;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public final long z() {
        return G("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x010e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0114  */
    public final List<C5> z0(String str, String str2, String str3) throws Throwable {
        C6354l c6354l;
        String string;
        C2254p.f(str);
        n();
        u();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                ArrayList arrayList2 = new ArrayList(3);
                String str4 = str;
                arrayList2.add(str4);
                StringBuilder sb = new StringBuilder("app_id=?");
                if (TextUtils.isEmpty(str2)) {
                    string = str2;
                } else {
                    string = str2;
                    try {
                        arrayList2.add(string);
                        sb.append(" and origin=?");
                    } catch (SQLiteException e) {
                        e = e;
                        c6354l = this;
                        c6354l.j().G().d("(2)Error querying user properties", V1.v(str), string, e);
                        List<C5> list = Collections.EMPTY_LIST;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return list;
                    }
                }
                if (!TextUtils.isEmpty(str3)) {
                    arrayList2.add(str3 + "*");
                    sb.append(" and name glob ?");
                }
                cursorQuery = B().query("user_attributes", new String[]{"name", "set_timestamp", "value", "origin"}, sb.toString(), (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "rowid", "1001");
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                while (arrayList.size() < 1000) {
                    String string2 = cursorQuery.getString(0);
                    long j2 = cursorQuery.getLong(1);
                    c6354l = this;
                    try {
                        try {
                            Object objM = c6354l.M(cursorQuery, 2);
                            string = cursorQuery.getString(3);
                            if (objM == null) {
                                c6354l.j().G().d("(2)Read invalid user property value, ignoring it", V1.v(str4), string, str3);
                            } else {
                                arrayList.add(new C5(str4, string, string2, j2, objM));
                            }
                            if (!cursorQuery.moveToNext()) {
                                cursorQuery.close();
                                return arrayList;
                            }
                            str4 = str;
                        } catch (Throwable th) {
                            th = th;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            throw th;
                        }
                    } catch (SQLiteException e2) {
                        e = e2;
                        c6354l.j().G().d("(2)Error querying user properties", V1.v(str), string, e);
                        List<C5> list2 = Collections.EMPTY_LIST;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return list2;
                    }
                }
                j().G().b("Read more than the max allowed user properties, ignoring excess", 1000);
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e3) {
                e = e3;
                c6354l = this;
                string = str2;
            }
        } catch (Throwable th2) {
            th = th2;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
}
