package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import ax.P8.l;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1469b;
import ax.Q6.InterfaceC1476i;
import ax.Q6.m;
import ax.Q8.k;
import ax.p075e8.f;
import com.google.firebase.remoteconfig.internal.d;
import com.google.firebase.remoteconfig.internal.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {
    public static final byte[] n = new byte[0];
    private final Context a;
    private final f b;
    private final ax.p091f8.c c;
    private final Executor d;
    private final com.google.firebase.remoteconfig.internal.b e;
    private final com.google.firebase.remoteconfig.internal.b f;
    private final com.google.firebase.remoteconfig.internal.b g;
    private final d h;
    private final k i;
    private final e j;
    private final ax.I8.e k;
    private final com.google.firebase.remoteconfig.internal.f l;
    private final ax.R8.e m;

    a(Context context, f fVar, ax.I8.e eVar, ax.p091f8.c cVar, Executor executor, com.google.firebase.remoteconfig.internal.b bVar, com.google.firebase.remoteconfig.internal.b bVar2, com.google.firebase.remoteconfig.internal.b bVar3, d dVar, k kVar, e eVar2, com.google.firebase.remoteconfig.internal.f fVar2, ax.R8.e eVar3) {
        this.a = context;
        this.b = fVar;
        this.k = eVar;
        this.c = cVar;
        this.d = executor;
        this.e = bVar;
        this.f = bVar2;
        this.g = bVar3;
        this.h = dVar;
        this.i = kVar;
        this.j = eVar2;
        this.l = fVar2;
        this.m = eVar3;
    }

    public static /* synthetic */ AbstractC1477j d(final a aVar, AbstractC1477j abstractC1477j, AbstractC1477j abstractC1477j2, AbstractC1477j abstractC1477j3) {
        aVar.getClass();
        if (!abstractC1477j.n() || abstractC1477j.k() == null) {
            return m.e(Boolean.FALSE);
        }
        com.google.firebase.remoteconfig.internal.c cVar = (com.google.firebase.remoteconfig.internal.c) abstractC1477j.k();
        return (!abstractC1477j2.n() || l(cVar, (com.google.firebase.remoteconfig.internal.c) abstractC1477j2.k())) ? aVar.f.i(cVar).g(aVar.d, new InterfaceC1469b() { // from class: ax.P8.g
            @Override // ax.Q6.InterfaceC1469b
            public final Object a(AbstractC1477j abstractC1477j4) {
                return Boolean.valueOf(this.a.m(abstractC1477j4));
            }
        }) : m.e(Boolean.FALSE);
    }

    public static a h() {
        return i(f.l());
    }

    public static a i(f fVar) {
        return ((c) fVar.j(c.class)).g();
    }

    private static boolean l(com.google.firebase.remoteconfig.internal.c cVar, com.google.firebase.remoteconfig.internal.c cVar2) {
        return cVar2 == null || !cVar.h().equals(cVar2.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m(AbstractC1477j<com.google.firebase.remoteconfig.internal.c> abstractC1477j) {
        if (!abstractC1477j.n()) {
            return false;
        }
        this.e.d();
        com.google.firebase.remoteconfig.internal.c cVarK = abstractC1477j.k();
        if (cVarK == null) {
            Log.e("FirebaseRemoteConfig", "Activated configs written to disk are null.");
            return true;
        }
        q(cVarK.e());
        this.m.d(cVarK);
        return true;
    }

    static List<Map<String, String>> p(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap map = new HashMap();
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.getString(next));
            }
            arrayList.add(map);
        }
        return arrayList;
    }

    public AbstractC1477j<Boolean> e() {
        final AbstractC1477j<com.google.firebase.remoteconfig.internal.c> abstractC1477jE = this.e.e();
        final AbstractC1477j<com.google.firebase.remoteconfig.internal.c> abstractC1477jE2 = this.f.e();
        return m.j(abstractC1477jE, abstractC1477jE2).i(this.d, new InterfaceC1469b() { // from class: ax.P8.e
            @Override // ax.Q6.InterfaceC1469b
            public final Object a(AbstractC1477j abstractC1477j) {
                return com.google.firebase.remoteconfig.a.d(this.a, abstractC1477jE, abstractC1477jE2, abstractC1477j);
            }
        });
    }

    public AbstractC1477j<Void> f() {
        return this.h.i().p(ax.p187l8.k.a(), new InterfaceC1476i() { // from class: ax.P8.f
            @Override // ax.Q6.InterfaceC1476i
            public final AbstractC1477j a(Object obj) {
                return ax.Q6.m.e(null);
            }
        });
    }

    public AbstractC1477j<Boolean> g() {
        return f().p(this.d, new InterfaceC1476i() { // from class: ax.P8.d
            @Override // ax.Q6.InterfaceC1476i
            public final AbstractC1477j a(Object obj) {
                return this.a.e();
            }
        });
    }

    ax.R8.e j() {
        return this.m;
    }

    public l k(String str) {
        return this.i.f(str);
    }

    void n(boolean z) {
        this.l.b(z);
    }

    void o() {
        this.f.e();
        this.g.e();
        this.e.e();
    }

    void q(JSONArray jSONArray) {
        if (this.c == null) {
            return;
        }
        try {
            this.c.m(p(jSONArray));
        } catch (ax.p091f8.a e) {
            Log.w("FirebaseRemoteConfig", "Could not update ABT experiments.", e);
        } catch (JSONException e2) {
            Log.e("FirebaseRemoteConfig", "Could not parse ABT experiments from the JSON response.", e2);
        }
    }
}
