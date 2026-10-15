package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import ax.p265q6.C2254p;
import ax.p345v6.i;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class GoogleSignInAccount extends ax.p281r6.a implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new c();
    public static final ax.p345v6.f t0 = i.c();
    private final String X;
    private final String Y;
    private final String Z;
    private final String k0;
    private final Uri l0;
    private String m0;
    private final long n0;
    private final String o0;
    final List p0;
    final int q;
    private final String q0;
    private final String r0;
    private final Set s0 = new HashSet();

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.q = i;
        this.X = str;
        this.Y = str2;
        this.Z = str3;
        this.k0 = str4;
        this.l0 = uri;
        this.m0 = str5;
        this.n0 = j;
        this.o0 = str6;
        this.p0 = list;
        this.q0 = str7;
        this.r0 = str8;
    }

    public static GoogleSignInAccount P(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l, String str7, Set set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l.longValue(), C2254p.f(str7), new ArrayList((Collection) C2254p.l(set)), str5, str6);
    }

    public static GoogleSignInAccount Q(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("photoUrl");
        Uri uri = !TextUtils.isEmpty(strOptString) ? Uri.parse(strOptString) : null;
        long j = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        GoogleSignInAccount googleSignInAccountP = P(jSONObject.optString("id"), jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null, jSONObject.has("email") ? jSONObject.optString("email") : null, jSONObject.has("displayName") ? jSONObject.optString("displayName") : null, jSONObject.has("givenName") ? jSONObject.optString("givenName") : null, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, uri, Long.valueOf(j), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        googleSignInAccountP.m0 = jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null;
        return googleSignInAccountP;
    }

    public String A() {
        return this.q0;
    }

    public String E() {
        return this.X;
    }

    public String H() {
        return this.Y;
    }

    public Uri J() {
        return this.l0;
    }

    public Set<Scope> K() {
        HashSet hashSet = new HashSet(this.p0);
        hashSet.addAll(this.s0);
        return hashSet;
    }

    public String M() {
        return this.m0;
    }

    public final String R() {
        return this.o0;
    }

    public final String S() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (E() != null) {
                jSONObject.put("id", E());
            }
            if (H() != null) {
                jSONObject.put("tokenId", H());
            }
            if (n() != null) {
                jSONObject.put("email", n());
            }
            if (j() != null) {
                jSONObject.put("displayName", j());
            }
            if (A() != null) {
                jSONObject.put("givenName", A());
            }
            if (z() != null) {
                jSONObject.put("familyName", z());
            }
            Uri uriJ = J();
            if (uriJ != null) {
                jSONObject.put("photoUrl", uriJ.toString());
            }
            if (M() != null) {
                jSONObject.put("serverAuthCode", M());
            }
            jSONObject.put("expirationTime", this.n0);
            jSONObject.put("obfuscatedIdentifier", this.o0);
            JSONArray jSONArray = new JSONArray();
            List list = this.p0;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, new Comparator() { // from class: ax.k6.c
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    Parcelable.Creator<GoogleSignInAccount> creator = GoogleSignInAccount.CREATOR;
                    return ((Scope) obj).j().compareTo(((Scope) obj2).j());
                }
            });
            for (Scope scope : scopeArr) {
                jSONArray.put(scope.j());
            }
            jSONObject.put("grantedScopes", jSONArray);
            jSONObject.remove("serverAuthCode");
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.o0.equals(this.o0) && googleSignInAccount.K().equals(K());
    }

    public int hashCode() {
        return ((this.o0.hashCode() + 527) * 31) + K().hashCode();
    }

    public Account i() {
        String str = this.Z;
        if (str == null) {
            return null;
        }
        return new Account(str, "com.google");
    }

    public String j() {
        return this.k0;
    }

    public String n() {
        return this.Z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.k(parcel, 1, this.q);
        ax.p281r6.c.q(parcel, 2, E(), false);
        ax.p281r6.c.q(parcel, 3, H(), false);
        ax.p281r6.c.q(parcel, 4, n(), false);
        ax.p281r6.c.q(parcel, 5, j(), false);
        ax.p281r6.c.p(parcel, 6, J(), i, false);
        ax.p281r6.c.q(parcel, 7, M(), false);
        ax.p281r6.c.n(parcel, 8, this.n0);
        ax.p281r6.c.q(parcel, 9, this.o0, false);
        ax.p281r6.c.u(parcel, 10, this.p0, false);
        ax.p281r6.c.q(parcel, 11, A(), false);
        ax.p281r6.c.q(parcel, 12, z(), false);
        ax.p281r6.c.b(parcel, iA);
    }

    public String z() {
        return this.r0;
    }
}
