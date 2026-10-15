package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;

/* JADX INFO: loaded from: classes3.dex */
public final class G5 {
    private final E2 a;

    public G5(E2 e2) {
        this.a = e2;
    }

    private final boolean d() {
        return this.a.F().z.a() > 0;
    }

    private final boolean e() {
        return d() && this.a.b().a() - this.a.F().z.a() > this.a.z().z(null, F.U);
    }

    final void a() {
        this.a.l().n();
        if (d()) {
            if (e()) {
                this.a.F().y.b(null);
                Bundle bundle = new Bundle();
                bundle.putString("source", "(not set)");
                bundle.putString("medium", "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1L);
                this.a.H().Y0("auto", "_cmpx", bundle);
            } else {
                String strA = this.a.F().y.a();
                if (TextUtils.isEmpty(strA)) {
                    this.a.j().I().a("Cache still valid but referrer not found");
                } else {
                    long jA = ((this.a.F().z.a() / 3600000) - 1) * 3600000;
                    Uri uri = Uri.parse(strA);
                    Bundle bundle2 = new Bundle();
                    Pair pair = new Pair(uri.getPath(), bundle2);
                    for (String str : uri.getQueryParameterNames()) {
                        bundle2.putString(str, uri.getQueryParameter(str));
                    }
                    ((Bundle) pair.second).putLong("_cc", jA);
                    Object obj = pair.first;
                    this.a.H().Y0(obj == null ? "app" : (String) obj, "_cmp", (Bundle) pair.second);
                }
                this.a.F().y.b(null);
            }
            this.a.F().z.b(0L);
        }
    }

    final void b(String str, Bundle bundle) {
        String string;
        this.a.l().n();
        if (this.a.p()) {
            return;
        }
        if (bundle == null || bundle.isEmpty()) {
            string = null;
        } else {
            if (str == null || str.isEmpty()) {
                str = "auto";
            }
            Uri.Builder builder = new Uri.Builder();
            builder.path(str);
            for (String str2 : bundle.keySet()) {
                builder.appendQueryParameter(str2, bundle.getString(str2));
            }
            string = builder.build().toString();
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.a.F().y.b(string);
        this.a.F().z.b(this.a.b().a());
    }

    final void c() {
        if (d() && e()) {
            this.a.F().y.b(null);
        }
    }
}
