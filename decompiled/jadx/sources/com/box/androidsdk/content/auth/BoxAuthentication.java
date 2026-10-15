package com.box.androidsdk.content.auth;

import android.content.Context;
import android.content.Intent;
import ax.K3.h;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxEntity;
import com.box.androidsdk.content.models.BoxJsonObject;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.models.BoxUser;
import com.box.androidsdk.content.requests.BoxResponse;
import com.box.androidsdk.content.utils.SdkUtils;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class BoxAuthentication {
    private static final BoxAuthentication e = new BoxAuthentication();
    private static final ThreadPoolExecutor f = SdkUtils.f(1, 1, 3600, TimeUnit.SECONDS);
    private static final String g = BoxAuthentication.class.getName();
    public static final String[] h = {"type", "id", "name", "login", "space_amount", "space_used", "max_upload_size", "status", "enterprise", "created_at"};
    private ConcurrentHashMap<String, BoxAuthenticationInfo> b;
    private ConcurrentLinkedQueue<WeakReference<e>> a = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, FutureTask> c = new ConcurrentHashMap<>();
    private f d = new f();

    public static class BoxAuthenticationInfo extends BoxJsonObject {
        private static final long serialVersionUID = 2878150977399126399L;

        public static class BoxImmutableAuthenticationInfo extends BoxAuthenticationInfo {
            private static final long serialVersionUID = 494874517008319105L;

            BoxImmutableAuthenticationInfo(BoxAuthenticationInfo boxAuthenticationInfo) {
                super.i(boxAuthenticationInfo.A());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void R(String str) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void S(String str) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void T(String str) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void U(Long l) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void V(Long l) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void W(String str) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public void X(BoxUser boxUser) {
                ax.N3.b.c("trying to modify ImmutableBoxAuthenticationInfo", new RuntimeException());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.BoxAuthenticationInfo
            public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override // com.box.androidsdk.content.models.BoxJsonObject
            public void i(ax.U4.d dVar) {
            }

            @Override // com.box.androidsdk.content.models.BoxJsonObject
            public void j(String str) {
            }
        }

        public static void H(BoxAuthenticationInfo boxAuthenticationInfo, BoxAuthenticationInfo boxAuthenticationInfo2) {
            boxAuthenticationInfo.i(boxAuthenticationInfo2.A());
        }

        public static BoxAuthenticationInfo Y(BoxAuthenticationInfo boxAuthenticationInfo) {
            if (boxAuthenticationInfo == null) {
                return null;
            }
            return new BoxImmutableAuthenticationInfo(boxAuthenticationInfo);
        }

        public String D() {
            return u("access_token");
        }

        @Override // 
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public BoxAuthenticationInfo clone() {
            BoxAuthenticationInfo boxAuthenticationInfo = new BoxAuthenticationInfo();
            H(boxAuthenticationInfo, this);
            return boxAuthenticationInfo;
        }

        public Long J() {
            return t("expires_in");
        }

        @Deprecated
        public String K() {
            return u("base_domain");
        }

        public Long M() {
            return t("refresh_time");
        }

        public BoxUser P() {
            return (BoxUser) q(BoxEntity.H(), "user");
        }

        public String Q() {
            return u("refresh_token");
        }

        public void R(String str) {
            y("access_token", str);
        }

        @Deprecated
        public void S(String str) {
            y("base_domain", str);
        }

        public void T(String str) {
            y("client_id", str);
        }

        public void U(Long l) {
            x("expires_in", l);
        }

        public void V(Long l) {
            x("refresh_time", l);
        }

        public void W(String str) {
            y("refresh_token", str);
        }

        public void X(BoxUser boxUser) {
            w("user", boxUser);
        }
    }

    class a implements Callable<BoxAuthenticationInfo> {
        final /* synthetic */ BoxAuthenticationInfo a;

        a(BoxAuthenticationInfo boxAuthenticationInfo) {
            this.a = boxAuthenticationInfo;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BoxAuthenticationInfo call() throws Exception {
            return this.a;
        }
    }

    class b implements Callable<BoxAuthenticationInfo> {
        final /* synthetic */ BoxSession a;
        final /* synthetic */ String b;

        b(BoxSession boxSession, String str) {
            this.a = boxSession;
            this.b = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BoxAuthenticationInfo call() throws Exception {
            BoxApiAuthentication.BoxCreateAuthRequest boxCreateAuthRequestC = new BoxApiAuthentication(this.a).c(this.b, this.a.u(), this.a.v());
            BoxAuthenticationInfo boxAuthenticationInfo = new BoxAuthenticationInfo();
            BoxAuthenticationInfo.H(boxAuthenticationInfo, this.a.q());
            BoxAuthenticationInfo boxAuthenticationInfoX = boxCreateAuthRequestC.x();
            boxAuthenticationInfo.R(boxAuthenticationInfoX.D());
            boxAuthenticationInfo.W(boxAuthenticationInfoX.Q());
            boxAuthenticationInfo.U(boxAuthenticationInfoX.J());
            boxAuthenticationInfo.V(Long.valueOf(System.currentTimeMillis()));
            boxAuthenticationInfo.X((BoxUser) new ax.K3.e(new BoxSession(this.a.p(), boxAuthenticationInfo, (g) null)).d().E(BoxAuthentication.h).x());
            BoxAuthentication.o().u(boxAuthenticationInfo, this.a.p());
            return boxAuthenticationInfo;
        }
    }

    class c implements h.b<BoxUser> {
        final /* synthetic */ BoxAuthenticationInfo a;
        final /* synthetic */ Context b;

        c(BoxAuthenticationInfo boxAuthenticationInfo, Context context) {
            this.a = boxAuthenticationInfo;
            this.b = context;
        }

        @Override // ax.K3.h.b
        public void a(BoxResponse<BoxUser> boxResponse) {
            if (!boxResponse.c()) {
                BoxAuthentication.o().v(this.a, boxResponse.a());
            } else {
                this.a.X((BoxUser) boxResponse.b());
                BoxAuthentication.o().u(this.a, this.b);
            }
        }
    }

    class d implements Callable<BoxAuthenticationInfo> {
        final /* synthetic */ BoxSession a;
        final /* synthetic */ BoxAuthenticationInfo b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ boolean e;

        d(BoxSession boxSession, BoxAuthenticationInfo boxAuthenticationInfo, String str, String str2, boolean z) {
            this.a = boxSession;
            this.b = boxAuthenticationInfo;
            this.c = str;
            this.d = str2;
            this.e = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BoxAuthenticationInfo call() throws Exception {
            this.a.A();
            BoxAuthentication.c(BoxAuthentication.this);
            String strQ = this.b.Q() != null ? this.b.Q() : HttpUrl.FRAGMENT_ENCODE_SET;
            String strU = this.a.u() != null ? this.a.u() : ax.K3.g.c;
            String strV = this.a.v() != null ? this.a.v() : ax.K3.g.d;
            if (SdkUtils.k(strU) || SdkUtils.k(strV)) {
                throw BoxAuthentication.this.s(this.a, new BoxException("client id or secret not specified", 400, "{\"error\": \"bad_request\",\n  \"error_description\": \"client id or secret not specified\"}", null), this.b, this.d);
            }
            try {
                BoxAuthenticationInfo boxAuthenticationInfoX = new BoxApiAuthentication(this.a).e(strQ, strU, strV).x();
                if (boxAuthenticationInfoX != null) {
                    boxAuthenticationInfoX.V(Long.valueOf(System.currentTimeMillis()));
                }
                BoxAuthenticationInfo.H(this.a.q(), boxAuthenticationInfoX);
                if (this.e) {
                    this.b.X((BoxUser) new ax.K3.e(this.a).d().E(BoxAuthentication.h).x());
                } else {
                    this.a.A();
                    BoxAuthentication.c(BoxAuthentication.this);
                }
                BoxAuthentication.this.m(this.a.p()).put(this.b.P().J(), boxAuthenticationInfoX);
                BoxAuthentication.this.n().c(BoxAuthentication.this.b, this.a.p());
                Iterator it = BoxAuthentication.this.a.iterator();
                while (it.hasNext()) {
                    e eVar = (e) ((WeakReference) it.next()).get();
                    if (eVar != null) {
                        eVar.f(boxAuthenticationInfoX);
                    }
                }
                if (!this.a.J().equals(this.b.P().J())) {
                    this.a.e(this.b, new BoxException("Session User Id has changed!"));
                }
                BoxAuthentication.this.c.remove(this.c);
                return this.b;
            } catch (BoxException e) {
                BoxAuthentication.this.c.remove(this.c);
                throw BoxAuthentication.this.s(this.a, e, this.b, this.d);
            }
        }
    }

    public interface e {
        void e(BoxAuthenticationInfo boxAuthenticationInfo, Exception exc);

        void f(BoxAuthenticationInfo boxAuthenticationInfo);

        void g(BoxAuthenticationInfo boxAuthenticationInfo);
    }

    public static class f {
        private static final String a = f.class.getCanonicalName() + "_SharedPref";
        private static final String b = f.class.getCanonicalName() + "_authInfoMap";
        private static final String c = f.class.getCanonicalName() + "_lastAuthUserId";

        protected String a(Context context) {
            return context.getSharedPreferences(a, 0).getString(c, null);
        }

        protected ConcurrentHashMap<String, BoxAuthenticationInfo> b(Context context) {
            BoxAuthenticationInfo boxAuthenticationInfo;
            ConcurrentHashMap<String, BoxAuthenticationInfo> concurrentHashMap = new ConcurrentHashMap<>();
            String string = context.getSharedPreferences(a, 0).getString(b, HttpUrl.FRAGMENT_ENCODE_SET);
            if (string.length() > 0) {
                BoxEntity boxEntity = new BoxEntity();
                boxEntity.j(string);
                for (String str : boxEntity.n()) {
                    ax.U4.g gVarV = boxEntity.v(str);
                    if (gVarV.q()) {
                        boxAuthenticationInfo = new BoxAuthenticationInfo();
                        boxAuthenticationInfo.j(gVarV.j());
                    } else if (gVarV.p()) {
                        boxAuthenticationInfo = new BoxAuthenticationInfo();
                        boxAuthenticationInfo.i(gVarV.i());
                    } else {
                        boxAuthenticationInfo = null;
                    }
                    concurrentHashMap.put(str, boxAuthenticationInfo);
                }
            }
            return concurrentHashMap;
        }

        protected void c(Map<String, BoxAuthenticationInfo> map, Context context) {
            ax.U4.d dVar = new ax.U4.d();
            for (Map.Entry<String, BoxAuthenticationInfo> entry : map.entrySet()) {
                dVar.A(entry.getKey(), entry.getValue().A());
            }
            context.getSharedPreferences(a, 0).edit().putString(b, new BoxEntity(dVar).z()).commit();
        }

        protected void d(String str, Context context) {
            if (SdkUtils.l(str)) {
                context.getSharedPreferences(a, 0).edit().remove(c).commit();
            } else {
                context.getSharedPreferences(a, 0).edit().putString(c, str).commit();
            }
        }
    }

    public interface g {
    }

    private BoxAuthentication() {
    }

    static /* synthetic */ g c(BoxAuthentication boxAuthentication) {
        boxAuthentication.getClass();
        return null;
    }

    private FutureTask<BoxAuthenticationInfo> i(BoxSession boxSession, String str) {
        return new FutureTask<>(new b(boxSession, str));
    }

    private FutureTask<BoxAuthenticationInfo> j(BoxSession boxSession, BoxAuthenticationInfo boxAuthenticationInfo) {
        boolean z = boxAuthenticationInfo.P() == null && boxSession.E() == null;
        String strD = (SdkUtils.k(boxSession.J()) && z) ? boxAuthenticationInfo.D() : boxSession.J();
        FutureTask<BoxAuthenticationInfo> futureTask = new FutureTask<>(new d(boxSession, boxAuthenticationInfo, strD, boxAuthenticationInfo.P() != null ? boxAuthenticationInfo.P().J() : boxSession.J(), z));
        this.c.put(strD, futureTask);
        f.execute(futureTask);
        return futureTask;
    }

    private h<BoxUser> k(Context context, BoxAuthenticationInfo boxAuthenticationInfo) {
        h hVarD = new ax.K3.e(new BoxSession(context, boxAuthenticationInfo.D(), (g) null)).d().E(h).D();
        hVarD.a(new c(boxAuthenticationInfo, context));
        f.execute(hVarD);
        return hVarD;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConcurrentHashMap<String, BoxAuthenticationInfo> m(Context context) {
        if (this.b == null) {
            this.b = this.d.b(context);
        }
        return this.b;
    }

    public static BoxAuthentication o() {
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BoxException.RefreshFailure s(BoxSession boxSession, BoxException boxException, BoxAuthenticationInfo boxAuthenticationInfo, String str) {
        BoxException.RefreshFailure refreshFailure = new BoxException.RefreshFailure(boxException);
        if (refreshFailure.g() || refreshFailure.c() == BoxException.ErrorType.TERMS_OF_SERVICE_REQUIRED) {
            if (str != null && str.equals(n().a(boxSession.p()))) {
                n().d(null, boxSession.p());
            }
            m(boxSession.p()).remove(str);
            n().c(this.b, boxSession.p());
        }
        o().v(boxAuthenticationInfo, refreshFailure);
        return refreshFailure;
    }

    public static boolean t(Context context) {
        return context.getPackageManager().queryIntentActivities(new Intent("com.box.android.action.AUTHENTICATE_VIA_BOX_APP"), 65600).size() > 0;
    }

    private synchronized void x(BoxSession boxSession) {
        try {
            Context contextP = boxSession.p();
            Intent intentJ = OAuthActivity.j(contextP, boxSession, t(contextP) && boxSession.K());
            intentJ.addFlags(268435456);
            contextP.startActivity(intentJ);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void g(e eVar) {
        if (p().contains(eVar)) {
            return;
        }
        this.a.add(new WeakReference<>(eVar));
    }

    public synchronized FutureTask<BoxAuthenticationInfo> h(BoxSession boxSession, String str) {
        FutureTask<BoxAuthenticationInfo> futureTaskI;
        futureTaskI = i(boxSession, str);
        f.submit(futureTaskI);
        return futureTaskI;
    }

    public BoxAuthenticationInfo l(String str, Context context) {
        if (str == null) {
            return null;
        }
        return m(context).get(str);
    }

    public f n() {
        return this.d;
    }

    public Set<e> p() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<WeakReference<e>> it = this.a.iterator();
        while (it.hasNext()) {
            e eVar = it.next().get();
            if (eVar != null) {
                linkedHashSet.add(eVar);
            }
        }
        if (this.a.size() > linkedHashSet.size()) {
            this.a = new ConcurrentLinkedQueue<>();
            Iterator it2 = linkedHashSet.iterator();
            while (it2.hasNext()) {
                this.a.add(new WeakReference<>((e) it2.next()));
            }
        }
        return linkedHashSet;
    }

    public g q() {
        return null;
    }

    public Map<String, BoxAuthenticationInfo> r(Context context) {
        return m(context);
    }

    public void u(BoxAuthenticationInfo boxAuthenticationInfo, Context context) {
        BoxAuthenticationInfo boxAuthenticationInfoY = BoxAuthenticationInfo.Y(boxAuthenticationInfo);
        if (!SdkUtils.k(boxAuthenticationInfoY.D()) && (boxAuthenticationInfoY.P() == null || SdkUtils.k(boxAuthenticationInfoY.P().J()))) {
            k(context, boxAuthenticationInfoY);
            return;
        }
        m(context).put(boxAuthenticationInfoY.P().J(), boxAuthenticationInfoY.clone());
        this.d.d(boxAuthenticationInfoY.P().J(), context);
        this.d.c(this.b, context);
        Iterator<e> it = p().iterator();
        while (it.hasNext()) {
            it.next().g(boxAuthenticationInfoY);
        }
    }

    public void v(BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
        Object objValueOf;
        String string = "failure:";
        if (n() != null) {
            string = "failure:auth storage :" + n().toString();
        }
        BoxAuthenticationInfo boxAuthenticationInfoY = BoxAuthenticationInfo.Y(boxAuthenticationInfo);
        if (boxAuthenticationInfoY != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            if (boxAuthenticationInfoY.P() == null) {
                objValueOf = "null user";
            } else {
                objValueOf = boxAuthenticationInfoY.P().J() == null ? "null user id" : Integer.valueOf(boxAuthenticationInfoY.P().J().length());
            }
            sb.append(objValueOf);
            string = sb.toString();
        }
        ax.N3.b.f("BoxAuthfail", string, exc);
        Iterator<e> it = p().iterator();
        while (it.hasNext()) {
            it.next().e(boxAuthenticationInfoY, exc);
        }
    }

    public synchronized FutureTask<BoxAuthenticationInfo> w(BoxSession boxSession) {
        BoxUser boxUserE = boxSession.E();
        if (boxUserE == null) {
            return j(boxSession, boxSession.q());
        }
        m(boxSession.p());
        BoxAuthenticationInfo boxAuthenticationInfo = this.b.get(boxUserE.J());
        if (boxAuthenticationInfo == null) {
            this.b.put(boxUserE.J(), boxSession.q());
            boxAuthenticationInfo = this.b.get(boxUserE.J());
        }
        if (boxSession.q().D() != null && (boxSession.q().D().equals(boxAuthenticationInfo.D()) || boxAuthenticationInfo.M() == null || System.currentTimeMillis() - boxAuthenticationInfo.M().longValue() >= 15000)) {
            FutureTask<BoxAuthenticationInfo> futureTask = this.c.get(boxUserE.J());
            if (futureTask != null && !futureTask.isCancelled() && !futureTask.isDone()) {
                return futureTask;
            }
            return j(boxSession, boxAuthenticationInfo);
        }
        BoxAuthenticationInfo.H(boxSession.q(), boxAuthenticationInfo);
        FutureTask<BoxAuthenticationInfo> futureTask2 = new FutureTask<>(new a(boxAuthenticationInfo));
        f.execute(futureTask2);
        return futureTask2;
    }

    public synchronized void y(BoxSession boxSession) {
        x(boxSession);
    }
}
