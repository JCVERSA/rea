package com.google.firebase.remoteconfig.internal;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {
    private static final Date h = new Date(0);
    private JSONObject a;
    private JSONObject b;
    private Date c;
    private JSONArray d;
    private JSONObject e;
    private long f;
    private JSONArray g;

    public static class b {
        private JSONObject a;
        private Date b;
        private JSONArray c;
        private JSONObject d;
        private long e;
        private JSONArray f;

        private b() {
            this.a = new JSONObject();
            this.b = c.h;
            this.c = new JSONArray();
            this.d = new JSONObject();
            this.e = 0L;
            this.f = new JSONArray();
        }

        public c a() throws JSONException {
            return new c(this.a, this.b, this.c, this.d, this.e, this.f);
        }

        public b b(JSONObject jSONObject) {
            try {
                this.a = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public b c(JSONArray jSONArray) {
            try {
                this.c = new JSONArray(jSONArray.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public b d(Date date) {
            this.b = date;
            return this;
        }

        public b e(JSONObject jSONObject) {
            try {
                this.d = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public b f(JSONArray jSONArray) {
            try {
                this.f = new JSONArray(jSONArray.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public b g(long j) {
            this.e = j;
            return this;
        }
    }

    private c(JSONObject jSONObject, Date date, JSONArray jSONArray, JSONObject jSONObject2, long j, JSONArray jSONArray2) throws JSONException {
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("configs_key", jSONObject);
        jSONObject3.put("fetch_time_key", date.getTime());
        jSONObject3.put("abt_experiments_key", jSONArray);
        jSONObject3.put("personalization_metadata_key", jSONObject2);
        jSONObject3.put("template_version_number_key", j);
        jSONObject3.put("rollout_metadata_key", jSONArray2);
        this.b = jSONObject;
        this.c = date;
        this.d = jSONArray;
        this.e = jSONObject2;
        this.f = j;
        this.g = jSONArray2;
        this.a = jSONObject3;
    }

    static c b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("personalization_metadata_key");
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
        }
        JSONObject jSONObject2 = jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("rollout_metadata_key");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        return new c(jSONObject.getJSONObject("configs_key"), new Date(jSONObject.getLong("fetch_time_key")), jSONObject.getJSONArray("abt_experiments_key"), jSONObject2, jSONObject.optLong("template_version_number_key"), jSONArrayOptJSONArray);
    }

    private Map<String, Map<String, String>> c() throws JSONException {
        HashMap map = new HashMap();
        for (int i = 0; i < j().length(); i++) {
            JSONObject jSONObject = j().getJSONObject(i);
            String string = jSONObject.getString("rolloutId");
            String string2 = jSONObject.getString("variantId");
            JSONArray jSONArray = jSONObject.getJSONArray("affectedParameterKeys");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                String string3 = jSONArray.getString(i2);
                if (!map.containsKey(string3)) {
                    map.put(string3, new HashMap());
                }
                Map map2 = (Map) map.get(string3);
                if (map2 != null) {
                    map2.put(string, string2);
                }
            }
        }
        return map;
    }

    private static c d(JSONObject jSONObject) throws JSONException {
        return b(new JSONObject(jSONObject.toString()));
    }

    public static b l() {
        return new b();
    }

    public JSONArray e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return this.a.toString().equals(((c) obj).toString());
        }
        return false;
    }

    public Set<String> f(c cVar) throws JSONException {
        JSONObject jSONObjectG = d(cVar.a).g();
        Map<String, Map<String, String>> mapC = c();
        Map<String, Map<String, String>> mapC2 = cVar.c();
        HashSet hashSet = new HashSet();
        Iterator<String> itKeys = g().keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!cVar.g().has(next)) {
                hashSet.add(next);
            } else if (!g().get(next).equals(cVar.g().get(next))) {
                hashSet.add(next);
            } else if ((i().has(next) && !cVar.i().has(next)) || (!i().has(next) && cVar.i().has(next))) {
                hashSet.add(next);
            } else if (i().has(next) && cVar.i().has(next) && !i().getJSONObject(next).toString().equals(cVar.i().getJSONObject(next).toString())) {
                hashSet.add(next);
            } else if (mapC.containsKey(next) != mapC2.containsKey(next)) {
                hashSet.add(next);
            } else if (mapC.containsKey(next) && mapC2.containsKey(next) && !mapC.get(next).equals(mapC2.get(next))) {
                hashSet.add(next);
            } else {
                jSONObjectG.remove(next);
            }
        }
        Iterator<String> itKeys2 = jSONObjectG.keys();
        while (itKeys2.hasNext()) {
            hashSet.add(itKeys2.next());
        }
        return hashSet;
    }

    public JSONObject g() {
        return this.b;
    }

    public Date h() {
        return this.c;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public JSONObject i() {
        return this.e;
    }

    public JSONArray j() {
        return this.g;
    }

    public long k() {
        return this.f;
    }

    public String toString() {
        return this.a.toString();
    }
}
