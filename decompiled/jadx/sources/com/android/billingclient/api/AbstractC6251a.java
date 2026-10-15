package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;

/* JADX INFO: renamed from: com.android.billingclient.api.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC6251a {

    /* JADX INFO: renamed from: com.android.billingclient.api.a$a, reason: collision with other inner class name */
    public static final class C0557a {
        private volatile C6255e a;
        private final Context b;
        private volatile ax.D3.e c;
        private volatile boolean d;
        private volatile boolean e;
        volatile boolean f;

        /* synthetic */ C0557a(Context context, ax.D3.v vVar) {
            this.b = context;
        }

        private final boolean d() {
            try {
                Context context = this.b;
                return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("com.google.android.play.billingclient.enableBillingOverridesTesting", false);
            } catch (Exception e) {
                ax.I6.Q.m("BillingClient", "Unable to retrieve metadata value for enableBillingOverridesTesting.", e);
                return false;
            }
        }

        public AbstractC6251a a() {
            Context context = this.b;
            if (context == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if (this.c == null) {
                if (this.d || this.e) {
                    return d() ? new E(null, context, null, null, this) : new C6252b(null, context, null, null, this);
                }
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (this.a == null || !this.a.a()) {
                throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
            }
            if (this.c == null) {
                C6255e c6255e = this.a;
                return d() ? new E(null, c6255e, context, null, null, null, this) : new C6252b(null, c6255e, context, null, null, null, this);
            }
            C6255e c6255e2 = this.a;
            ax.D3.e eVar = this.c;
            return d() ? new E(null, c6255e2, context, eVar, null, null, null, this) : new C6252b(null, c6255e2, context, eVar, null, null, null, this);
        }

        public C0557a b(C6255e c6255e) {
            this.a = c6255e;
            return this;
        }

        public C0557a c(ax.D3.e eVar) {
            this.c = eVar;
            return this;
        }
    }

    public static C0557a c(Context context) {
        return new C0557a(context, null);
    }

    public abstract void a();

    public abstract C6254d b(Activity activity, C6253c c6253c);

    public abstract void d(C6257g c6257g, ax.D3.c cVar);

    public abstract void e(ax.D3.f fVar, ax.D3.d dVar);

    public abstract void f(ax.D3.b bVar);
}
