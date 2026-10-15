package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import ax.p265q6.C2254p;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class GoogleSignInOptions extends ax.p281r6.a implements ax.o6.a.d, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions r0;
    public static final GoogleSignInOptions s0;
    public static final Scope t0 = new Scope("profile");
    public static final Scope u0 = new Scope("email");
    public static final Scope v0 = new Scope("openid");
    public static final Scope w0;
    public static final Scope x0;
    private static final Comparator y0;
    private final ArrayList X;
    private Account Y;
    private boolean Z;
    private final boolean k0;
    private final boolean l0;
    private String m0;
    private String n0;
    private ArrayList o0;
    private String p0;
    final int q;
    private Map q0;

    public static final class a {
        private Set a;
        private boolean b;
        private boolean c;
        private boolean d;
        private String e;
        private Account f;
        private String g;
        private Map h;
        private String i;

        public a() {
            this.a = new HashSet();
            this.h = new HashMap();
        }

        public a(GoogleSignInOptions googleSignInOptions) {
            this.a = new HashSet();
            this.h = new HashMap();
            C2254p.l(googleSignInOptions);
            this.a = new HashSet(googleSignInOptions.X);
            this.b = googleSignInOptions.k0;
            this.c = googleSignInOptions.l0;
            this.d = googleSignInOptions.Z;
            this.e = googleSignInOptions.m0;
            this.f = googleSignInOptions.Y;
            this.g = googleSignInOptions.n0;
            this.h = GoogleSignInOptions.Z(googleSignInOptions.o0);
            this.i = googleSignInOptions.p0;
        }

        public GoogleSignInOptions a() {
            if (this.a.contains(GoogleSignInOptions.x0)) {
                Set set = this.a;
                Scope scope = GoogleSignInOptions.w0;
                if (set.contains(scope)) {
                    this.a.remove(scope);
                }
            }
            if (this.d && (this.f == null || !this.a.isEmpty())) {
                b();
            }
            return new GoogleSignInOptions(new ArrayList(this.a), this.f, this.d, this.b, this.c, this.e, this.g, this.h, this.i);
        }

        public a b() {
            this.a.add(GoogleSignInOptions.v0);
            return this;
        }

        public a c() {
            this.a.add(GoogleSignInOptions.t0);
            return this;
        }

        public a d(Scope scope, Scope... scopeArr) {
            this.a.add(scope);
            this.a.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public a e(String str) {
            this.i = str;
            return this;
        }
    }

    static {
        Scope scope = new Scope("https://www.googleapis.com/auth/games_lite");
        w0 = scope;
        x0 = new Scope("https://www.googleapis.com/auth/games");
        a aVar = new a();
        aVar.b();
        aVar.c();
        r0 = aVar.a();
        a aVar2 = new a();
        aVar2.d(scope, new Scope[0]);
        s0 = aVar2.a();
        CREATOR = new e();
        y0 = new d();
    }

    GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList arrayList2, String str3) {
        this(i, arrayList, account, z, z2, z3, str, str2, Z(arrayList2), str3);
    }

    private GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3) {
        this.q = i;
        this.X = arrayList;
        this.Y = account;
        this.Z = z;
        this.k0 = z2;
        this.l0 = z3;
        this.m0 = str;
        this.n0 = str2;
        this.o0 = new ArrayList(map.values());
        this.q0 = map;
        this.p0 = str3;
    }

    public static GoogleSignInOptions M(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String strOptString = jSONObject.has("accountName") ? jSONObject.optString("accountName") : null;
        return new GoogleSignInOptions(3, new ArrayList(hashSet), !TextUtils.isEmpty(strOptString) ? new Account(strOptString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.has("serverClientId") ? jSONObject.optString("serverClientId") : null, jSONObject.has("hostedDomain") ? jSONObject.optString("hostedDomain") : null, new HashMap(), (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map Z(List list) {
        HashMap map = new HashMap();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ax.p185l6.a aVar = (ax.p185l6.a) it.next();
                map.put(Integer.valueOf(aVar.j()), aVar);
            }
        }
        return map;
    }

    public String A() {
        return this.m0;
    }

    public boolean E() {
        return this.l0;
    }

    public boolean H() {
        return this.Z;
    }

    public boolean J() {
        return this.k0;
    }

    public final String S() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.X, y0);
            ArrayList arrayList = this.X;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                jSONArray.put(((Scope) obj).j());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.Y;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.Z);
            jSONObject.put("forceCodeForRefreshToken", this.l0);
            jSONObject.put("serverAuthRequested", this.k0);
            if (!TextUtils.isEmpty(this.m0)) {
                jSONObject.put("serverClientId", this.m0);
            }
            if (!TextUtils.isEmpty(this.n0)) {
                jSONObject.put("hostedDomain", this.n0);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0052 A[Catch: ClassCastException -> 0x0090, TryCatch #0 {ClassCastException -> 0x0090, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004a, B:24:0x0052, B:30:0x006a, B:32:0x0072, B:34:0x007a, B:36:0x0082, B:27:0x005d, B:20:0x0040), top: B:42:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x005c  */
    /* JADX WARN: Code duplicated, block: B:27:0x005d A[Catch: ClassCastException -> 0x0090, TryCatch #0 {ClassCastException -> 0x0090, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004a, B:24:0x0052, B:30:0x006a, B:32:0x0072, B:34:0x007a, B:36:0x0082, B:27:0x005d, B:20:0x0040), top: B:42:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0069  */
    /* JADX WARN: Code duplicated, block: B:30:0x006a A[Catch: ClassCastException -> 0x0090, TryCatch #0 {ClassCastException -> 0x0090, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004a, B:24:0x0052, B:30:0x006a, B:32:0x0072, B:34:0x007a, B:36:0x0082, B:27:0x005d, B:20:0x0040), top: B:42:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0072 A[Catch: ClassCastException -> 0x0090, TryCatch #0 {ClassCastException -> 0x0090, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004a, B:24:0x0052, B:30:0x006a, B:32:0x0072, B:34:0x007a, B:36:0x0082, B:27:0x005d, B:20:0x0040), top: B:42:0x0004 }] */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.o0.isEmpty() && googleSignInOptions.o0.isEmpty() && this.X.size() == googleSignInOptions.z().size() && this.X.containsAll(googleSignInOptions.z())) {
                Account account = this.Y;
                if (account == null) {
                    if (googleSignInOptions.i() == null) {
                        if (TextUtils.isEmpty(this.m0)) {
                            if (TextUtils.isEmpty(googleSignInOptions.A())) {
                                if (this.l0 != googleSignInOptions.E() && this.Z == googleSignInOptions.H() && this.k0 == googleSignInOptions.J() && TextUtils.equals(this.p0, googleSignInOptions.n())) {
                                    return true;
                                }
                            }
                        } else if (!this.m0.equals(googleSignInOptions.A())) {
                            if (this.l0 != googleSignInOptions.E()) {
                            }
                        }
                    }
                } else if (account.equals(googleSignInOptions.i())) {
                    if (TextUtils.isEmpty(this.m0)) {
                        if (TextUtils.isEmpty(googleSignInOptions.A())) {
                            if (this.l0 != googleSignInOptions.E()) {
                            }
                        }
                    } else if (!this.m0.equals(googleSignInOptions.A())) {
                        if (this.l0 != googleSignInOptions.E()) {
                        }
                    }
                }
            }
        } catch (ClassCastException unused) {
        }
        return false;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.X;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((Scope) arrayList2.get(i)).j());
        }
        Collections.sort(arrayList);
        ax.p185l6.b bVar = new ax.p185l6.b();
        bVar.a(arrayList);
        bVar.a(this.Y);
        bVar.a(this.m0);
        bVar.c(this.l0);
        bVar.c(this.Z);
        bVar.c(this.k0);
        bVar.a(this.p0);
        return bVar.b();
    }

    public Account i() {
        return this.Y;
    }

    public ArrayList<ax.p185l6.a> j() {
        return this.o0;
    }

    public String n() {
        return this.p0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int i2 = this.q;
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.k(parcel, 1, i2);
        ax.p281r6.c.u(parcel, 2, z(), false);
        ax.p281r6.c.p(parcel, 3, i(), i, false);
        ax.p281r6.c.c(parcel, 4, H());
        ax.p281r6.c.c(parcel, 5, J());
        ax.p281r6.c.c(parcel, 6, E());
        ax.p281r6.c.q(parcel, 7, A(), false);
        ax.p281r6.c.q(parcel, 8, this.n0, false);
        ax.p281r6.c.u(parcel, 9, j(), false);
        ax.p281r6.c.q(parcel, 10, n(), false);
        ax.p281r6.c.b(parcel, iA);
    }

    public ArrayList<Scope> z() {
        return new ArrayList<>(this.X);
    }
}
