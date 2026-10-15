package com.google.android.gms.auth.api.signin;

import android.content.Context;
import ax.Q6.AbstractC1477j;
import ax.p185l6.o;
import ax.p217n6.C2176g;
import ax.p249p6.C2203a;
import ax.p265q6.C2253o;
import com.google.android.gms.dynamite.DynamiteModule;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class b extends ax.p233o6.f<GoogleSignInOptions> {
    private static final f k = new f(null);
    static int l = 1;

    b(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, ax.p137i6.a.b, googleSignInOptions, new ax.o6.f.a.C0412a().b(new C2203a()).a());
    }

    private final synchronized int w() {
        int i;
        try {
            i = l;
            if (i == 1) {
                Context contextM = m();
                C2176g c2176gO = C2176g.o();
                int iH = c2176gO.h(contextM, 12451000);
                if (iH == 0) {
                    i = 4;
                    l = 4;
                } else if (c2176gO.b(contextM, iH, null) != null || DynamiteModule.a(contextM, "com.google.android.gms.auth.api.fallback") == 0) {
                    i = 2;
                    l = 2;
                } else {
                    i = 3;
                    l = 3;
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return i;
    }

    public AbstractC1477j<Void> u() {
        return C2253o.b(o.a(e(), m(), w() == 3));
    }

    public AbstractC1477j<Void> v() {
        return C2253o.b(o.b(e(), m(), w() == 3));
    }
}
