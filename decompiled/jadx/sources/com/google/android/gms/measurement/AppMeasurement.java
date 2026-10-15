package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import ax.H6.U0;
import ax.M6.n;
import ax.M6.y;
import ax.p265q6.C2254p;
import com.google.android.gms.measurement.internal.E2;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class AppMeasurement {
    private static volatile AppMeasurement b;
    private final a a;

    public static class ConditionalUserProperty {

        @Keep
        public boolean mActive;

        @Keep
        public String mAppId;

        @Keep
        public long mCreationTimestamp;

        @Keep
        public String mExpiredEventName;

        @Keep
        public Bundle mExpiredEventParams;

        @Keep
        public String mName;

        @Keep
        public String mOrigin;

        @Keep
        public long mTimeToLive;

        @Keep
        public String mTimedOutEventName;

        @Keep
        public Bundle mTimedOutEventParams;

        @Keep
        public String mTriggerEventName;

        @Keep
        public long mTriggerTimeout;

        @Keep
        public String mTriggeredEventName;

        @Keep
        public Bundle mTriggeredEventParams;

        @Keep
        public long mTriggeredTimestamp;

        @Keep
        public Object mValue;

        public ConditionalUserProperty() {
        }

        ConditionalUserProperty(Bundle bundle) {
            C2254p.l(bundle);
            this.mAppId = (String) n.a(bundle, "app_id", String.class, null);
            this.mOrigin = (String) n.a(bundle, "origin", String.class, null);
            this.mName = (String) n.a(bundle, "name", String.class, null);
            this.mValue = n.a(bundle, "value", Object.class, null);
            this.mTriggerEventName = (String) n.a(bundle, "trigger_event_name", String.class, null);
            this.mTriggerTimeout = ((Long) n.a(bundle, "trigger_timeout", Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) n.a(bundle, "timed_out_event_name", String.class, null);
            this.mTimedOutEventParams = (Bundle) n.a(bundle, "timed_out_event_params", Bundle.class, null);
            this.mTriggeredEventName = (String) n.a(bundle, "triggered_event_name", String.class, null);
            this.mTriggeredEventParams = (Bundle) n.a(bundle, "triggered_event_params", Bundle.class, null);
            this.mTimeToLive = ((Long) n.a(bundle, "time_to_live", Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) n.a(bundle, "expired_event_name", String.class, null);
            this.mExpiredEventParams = (Bundle) n.a(bundle, "expired_event_params", Bundle.class, null);
            this.mActive = ((Boolean) n.a(bundle, "active", Boolean.class, Boolean.FALSE)).booleanValue();
            this.mCreationTimestamp = ((Long) n.a(bundle, "creation_timestamp", Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) n.a(bundle, "triggered_timestamp", Long.class, 0L)).longValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class a implements y {
        private a() {
        }
    }

    private AppMeasurement(y yVar) {
        this.a = new com.google.android.gms.measurement.a(yVar);
    }

    private AppMeasurement(E2 e2) {
        this.a = new b(e2);
    }

    private static y a(Context context, Bundle bundle) {
        return (y) FirebaseAnalytics.class.getDeclaredMethod("getScionFrontendApiImplementation", Context.class, Bundle.class).invoke(null, context, null);
    }

    private static AppMeasurement b(Context context, String str, String str2) {
        if (b == null) {
            synchronized (AppMeasurement.class) {
                try {
                    if (b == null) {
                        y yVarA = a(context, null);
                        if (yVarA != null) {
                            b = new AppMeasurement(yVarA);
                        } else {
                            b = new AppMeasurement(E2.c(context, new U0(0L, 0L, true, null, null, null, null, null), null));
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return b;
    }

    @Keep
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return b(context, null, null);
    }

    @Keep
    public void beginAdUnitExposure(String str) {
        this.a.x(str);
    }

    @Keep
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        this.a.a(str, str2, bundle);
    }

    @Keep
    public void endAdUnitExposure(String str) {
        this.a.E(str);
    }

    @Keep
    public long generateEventId() {
        return this.a.e();
    }

    @Keep
    public String getAppInstanceId() {
        return this.a.h();
    }

    @Keep
    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        List<Bundle> listC = this.a.c(str, str2);
        ArrayList arrayList = new ArrayList(listC == null ? 0 : listC.size());
        Iterator<Bundle> it = listC.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConditionalUserProperty(it.next()));
        }
        return arrayList;
    }

    @Keep
    public String getCurrentScreenClass() {
        return this.a.g();
    }

    @Keep
    public String getCurrentScreenName() {
        return this.a.i();
    }

    @Keep
    public String getGmpAppId() {
        return this.a.j();
    }

    @Keep
    public int getMaxUserProperties(String str) {
        return this.a.o(str);
    }

    @Keep
    protected Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return this.a.d(str, str2, z);
    }

    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        this.a.f(str, str2, bundle);
    }

    @Keep
    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        C2254p.l(conditionalUserProperty);
        a aVar = this.a;
        Bundle bundle = new Bundle();
        String str = conditionalUserProperty.mAppId;
        if (str != null) {
            bundle.putString("app_id", str);
        }
        String str2 = conditionalUserProperty.mOrigin;
        if (str2 != null) {
            bundle.putString("origin", str2);
        }
        String str3 = conditionalUserProperty.mName;
        if (str3 != null) {
            bundle.putString("name", str3);
        }
        Object obj = conditionalUserProperty.mValue;
        if (obj != null) {
            n.b(bundle, obj);
        }
        String str4 = conditionalUserProperty.mTriggerEventName;
        if (str4 != null) {
            bundle.putString("trigger_event_name", str4);
        }
        bundle.putLong("trigger_timeout", conditionalUserProperty.mTriggerTimeout);
        String str5 = conditionalUserProperty.mTimedOutEventName;
        if (str5 != null) {
            bundle.putString("timed_out_event_name", str5);
        }
        Bundle bundle2 = conditionalUserProperty.mTimedOutEventParams;
        if (bundle2 != null) {
            bundle.putBundle("timed_out_event_params", bundle2);
        }
        String str6 = conditionalUserProperty.mTriggeredEventName;
        if (str6 != null) {
            bundle.putString("triggered_event_name", str6);
        }
        Bundle bundle3 = conditionalUserProperty.mTriggeredEventParams;
        if (bundle3 != null) {
            bundle.putBundle("triggered_event_params", bundle3);
        }
        bundle.putLong("time_to_live", conditionalUserProperty.mTimeToLive);
        String str7 = conditionalUserProperty.mExpiredEventName;
        if (str7 != null) {
            bundle.putString("expired_event_name", str7);
        }
        Bundle bundle4 = conditionalUserProperty.mExpiredEventParams;
        if (bundle4 != null) {
            bundle.putBundle("expired_event_params", bundle4);
        }
        bundle.putLong("creation_timestamp", conditionalUserProperty.mCreationTimestamp);
        bundle.putBoolean("active", conditionalUserProperty.mActive);
        bundle.putLong("triggered_timestamp", conditionalUserProperty.mTriggeredTimestamp);
        aVar.l0(bundle);
    }
}
