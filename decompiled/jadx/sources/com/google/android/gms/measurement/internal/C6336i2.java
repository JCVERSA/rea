package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import ax.H6.C0997m7;
import ax.p265q6.C2254p;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.i2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6336i2 {
    private final String a;
    private final Bundle b;
    private Bundle c;
    private final /* synthetic */ C6322g2 d;

    public C6336i2(C6322g2 c6322g2, String str, Bundle bundle) {
        this.d = c6322g2;
        C2254p.f(str);
        this.a = str;
        this.b = new Bundle();
    }

    private final String c(Bundle bundle) {
        JSONArray jSONArray = new JSONArray();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("n", str);
                    if (C0997m7.a() && this.d.d().t(F.B0)) {
                        if (obj instanceof String) {
                            jSONObject.put("v", String.valueOf(obj));
                            jSONObject.put("t", "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put("v", String.valueOf(obj));
                            jSONObject.put("t", "l");
                        } else if (obj instanceof int[]) {
                            jSONObject.put("v", Arrays.toString((int[]) obj));
                            jSONObject.put("t", "ia");
                        } else if (obj instanceof long[]) {
                            jSONObject.put("v", Arrays.toString((long[]) obj));
                            jSONObject.put("t", "la");
                        } else if (obj instanceof Double) {
                            jSONObject.put("v", String.valueOf(obj));
                            jSONObject.put("t", "d");
                        } else {
                            this.d.j().G().b("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    } else {
                        jSONObject.put("v", String.valueOf(obj));
                        if (obj instanceof String) {
                            jSONObject.put("t", "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put("t", "l");
                        } else if (obj instanceof Double) {
                            jSONObject.put("t", "d");
                        } else {
                            this.d.j().G().b("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    }
                } catch (JSONException e) {
                    this.d.j().G().b("Cannot serialize bundle value to SharedPreferences", e);
                }
            }
        }
        return jSONArray.toString();
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0084  */
    public final Bundle a() {
        byte b;
        if (this.c == null) {
            String string = this.d.J().getString(this.a, null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String string2 = jSONObject.getString("n");
                            String string3 = jSONObject.getString("t");
                            int iHashCode = string3.hashCode();
                            if (iHashCode != 100) {
                                if (iHashCode != 108) {
                                    if (iHashCode != 115) {
                                        if (iHashCode != 3352) {
                                            if (iHashCode == 3445 && string3.equals("la")) {
                                                b = 4;
                                            } else {
                                                b = -1;
                                            }
                                        } else if (string3.equals("ia")) {
                                            b = 3;
                                        } else {
                                            b = -1;
                                        }
                                    } else if (string3.equals("s")) {
                                        b = 0;
                                    } else {
                                        b = -1;
                                    }
                                } else if (string3.equals("l")) {
                                    b = 2;
                                } else {
                                    b = -1;
                                }
                            } else if (string3.equals("d")) {
                                b = 1;
                            } else {
                                b = -1;
                            }
                            if (b == 0) {
                                bundle.putString(string2, jSONObject.getString("v"));
                            } else if (b == 1) {
                                bundle.putDouble(string2, Double.parseDouble(jSONObject.getString("v")));
                            } else if (b == 2) {
                                bundle.putLong(string2, Long.parseLong(jSONObject.getString("v")));
                            } else if (b != 3) {
                                if (b != 4) {
                                    this.d.j().G().b("Unrecognized persisted bundle type. Type", string3);
                                } else if (C0997m7.a() && this.d.d().t(F.B0)) {
                                    JSONArray jSONArray2 = new JSONArray(jSONObject.getString("v"));
                                    int length = jSONArray2.length();
                                    long[] jArr = new long[length];
                                    for (int i2 = 0; i2 < length; i2++) {
                                        jArr[i2] = jSONArray2.optLong(i2);
                                    }
                                    bundle.putLongArray(string2, jArr);
                                }
                            } else if (C0997m7.a() && this.d.d().t(F.B0)) {
                                JSONArray jSONArray3 = new JSONArray(jSONObject.getString("v"));
                                int length2 = jSONArray3.length();
                                int[] iArr = new int[length2];
                                for (int i3 = 0; i3 < length2; i3++) {
                                    iArr[i3] = jSONArray3.optInt(i3);
                                }
                                bundle.putIntArray(string2, iArr);
                            }
                        } catch (NumberFormatException | JSONException unused) {
                            this.d.j().G().a("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.c = bundle;
                } catch (JSONException unused2) {
                    this.d.j().G().a("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.c == null) {
                this.c = this.b;
            }
        }
        return this.c;
    }

    public final void b(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor editorEdit = this.d.J().edit();
        if (bundle.size() == 0) {
            editorEdit.remove(this.a);
        } else {
            editorEdit.putString(this.a, c(bundle));
        }
        editorEdit.apply();
        this.c = bundle;
    }
}
