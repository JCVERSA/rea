package com.box.androidsdk.content.models;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import ax.K3.e;
import ax.K3.g;
import ax.K3.h;
import ax.O3.d;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.auth.BoxAuthentication;
import com.box.androidsdk.content.requests.BoxRequest;
import com.box.androidsdk.content.utils.SdkUtils;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class BoxSession extends BoxObject implements BoxAuthentication.e {
    private static final transient ThreadPoolExecutor Z = SdkUtils.f(1, 20, 3600, TimeUnit.SECONDS);
    private static final long serialVersionUID = 8122900496609434013L;
    private transient BoxAuthentication.e X;
    private transient WeakReference<h<BoxSession>> Y;
    protected String mAccountEmail;
    protected BoxAuthentication.BoxAuthenticationInfo mAuthInfo;
    protected String mClientId;
    protected String mClientRedirectUrl;
    protected String mClientSecret;
    protected String mDeviceId;
    protected String mDeviceName;
    protected boolean mEnableBoxAppAuthentication;
    protected Long mExpiresAt;
    private String mLastAuthCreationTaskId;
    protected BoxMDMData mMDMData;
    protected BoxAuthentication.g mRefreshProvider;
    private boolean mSuppressAuthErrorUIAfterLogin;
    private String mUserAgent;
    private String mUserId;
    private transient Context q;

    private static class BoxSessionAuthCreationRequest extends BoxRequest<BoxSession, BoxSessionAuthCreationRequest> implements BoxAuthentication.e {
        private static final long serialVersionUID = 8123965031279971545L;
        private boolean mIsWaitingForLoginUi;
        private final BoxSession mSession;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BoxSessionAuthCreationRequest.this.mSession.A();
                BoxSessionAuthCreationRequest.this.mSession.Y();
            }
        }

        static class b extends h<BoxSession> {
            public b(Class<BoxSession> cls, BoxRequest boxRequest) {
                super(cls, boxRequest);
            }

            public void b() {
                BoxRequest boxRequest = this.q;
                if ((boxRequest instanceof BoxSessionAuthCreationRequest) && ((BoxSessionAuthCreationRequest) boxRequest).mIsWaitingForLoginUi) {
                    ((BoxSessionAuthCreationRequest) this.q).mSession.Y();
                }
            }
        }

        public BoxSessionAuthCreationRequest(BoxSession boxSession, boolean z) {
            super(null, " ", null);
            this.mSession = boxSession;
        }

        private void G() {
            synchronized (this.mSession) {
                this.mIsWaitingForLoginUi = true;
                new Handler(Looper.getMainLooper()).post(new a());
                while (this.mIsWaitingForLoginUi) {
                    try {
                        this.mSession.wait();
                    } catch (InterruptedException unused) {
                        ax.N3.b.a(getClass().getSimpleName(), "could not launch auth UI");
                    }
                }
            }
        }

        private void H() {
            synchronized (this.mSession) {
                this.mIsWaitingForLoginUi = false;
                this.mSession.notify();
            }
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest
        public h<BoxSession> D() {
            return new b(BoxSession.class, this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.box.androidsdk.content.requests.BoxRequest
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public BoxSession t() throws BoxException {
            synchronized (this.mSession) {
                try {
                    if (this.mSession.E() == null) {
                        if (this.mSession.q() != null && !SdkUtils.k(this.mSession.q().D()) && this.mSession.E() == null) {
                            try {
                                BoxUser boxUser = (BoxUser) new e(this.mSession).d().E(BoxAuthentication.h).x();
                                this.mSession.W(boxUser.J());
                                this.mSession.q().X(boxUser);
                                BoxAuthentication.o().u(this.mSession.q(), this.mSession.p());
                                return this.mSession;
                            } catch (BoxException e) {
                                ax.N3.b.b("BoxSession", "Unable to repair user", e);
                                if ((e instanceof BoxException.RefreshFailure) && ((BoxException.RefreshFailure) e).g()) {
                                    BoxSession.a0(this.mSession.p(), d.o);
                                } else {
                                    if (e.c() != BoxException.ErrorType.TERMS_OF_SERVICE_REQUIRED) {
                                        this.mSession.e(null, e);
                                        throw e;
                                    }
                                    BoxSession.a0(this.mSession.p(), d.q);
                                }
                            }
                        }
                        BoxAuthentication.o().g(this);
                        G();
                        return this.mSession;
                    }
                    BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfoL = BoxAuthentication.o().l(this.mSession.J(), this.mSession.p());
                    if (boxAuthenticationInfoL != null) {
                        BoxAuthentication.BoxAuthenticationInfo.H(this.mSession.mAuthInfo, boxAuthenticationInfoL);
                        if (SdkUtils.k(this.mSession.q().D()) && SdkUtils.k(this.mSession.q().Q())) {
                            BoxAuthentication.o().g(this);
                            G();
                        } else {
                            if (boxAuthenticationInfoL.P() == null || SdkUtils.k(boxAuthenticationInfoL.P().J())) {
                                try {
                                    BoxUser boxUser2 = (BoxUser) new e(this.mSession).d().E(BoxAuthentication.h).x();
                                    this.mSession.W(boxUser2.J());
                                    this.mSession.q().X(boxUser2);
                                    BoxSession boxSession = this.mSession;
                                    boxSession.g(boxSession.q());
                                    return this.mSession;
                                } catch (BoxException e2) {
                                    ax.N3.b.b("BoxSession", "Unable to repair user", e2);
                                    if ((e2 instanceof BoxException.RefreshFailure) && ((BoxException.RefreshFailure) e2).g()) {
                                        BoxSession.a0(this.mSession.p(), d.o);
                                    } else {
                                        if (e2.c() != BoxException.ErrorType.TERMS_OF_SERVICE_REQUIRED) {
                                            this.mSession.e(null, e2);
                                            throw e2;
                                        }
                                        BoxSession.a0(this.mSession.p(), d.q);
                                    }
                                }
                            }
                            BoxSession boxSession2 = this.mSession;
                            boxSession2.g(boxSession2.q());
                        }
                    } else {
                        this.mSession.mAuthInfo.X(null);
                        G();
                    }
                    return this.mSession;
                } catch (Throwable th) {
                    throw th;
                }
                throw th;
            }
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void e(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
            H();
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest
        public boolean equals(Object obj) {
            if ((obj instanceof BoxSessionAuthCreationRequest) && ((BoxSessionAuthCreationRequest) obj).mSession.equals(this.mSession)) {
                return super.equals(obj);
            }
            return false;
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void f(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void g(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            H();
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest
        public int hashCode() {
            return this.mSession.hashCode() + super.hashCode();
        }
    }

    private static class BoxSessionRefreshRequest extends BoxRequest<BoxSession, BoxSessionRefreshRequest> {
        private static final long serialVersionUID = 8123965031279971587L;
        private BoxSession mSession;

        public BoxSessionRefreshRequest(BoxSession boxSession) {
            super(null, " ", null);
            this.mSession = boxSession;
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public BoxSession t() throws BoxException {
            try {
                BoxAuthentication.o().w(this.mSession).get();
            } catch (Exception e) {
                ax.N3.b.b("BoxSession", "Unable to repair user", e);
                Exception exc = e.getCause() instanceof BoxException ? (Exception) e.getCause() : e;
                if (!(exc instanceof BoxException)) {
                    throw new BoxException("BoxSessionRefreshRequest failed", exc);
                }
                if (!this.mSession.mSuppressAuthErrorUIAfterLogin) {
                    if ((exc instanceof BoxException.RefreshFailure) && ((BoxException.RefreshFailure) exc).g()) {
                        BoxSession.a0(this.mSession.p(), d.o);
                        this.mSession.Y();
                        BoxSession boxSession = this.mSession;
                        boxSession.e(boxSession.q(), exc);
                        throw ((BoxException) exc);
                    }
                    if (((BoxException) e).c() != BoxException.ErrorType.TERMS_OF_SERVICE_REQUIRED) {
                        this.mSession.e(null, exc);
                        throw ((BoxException) exc);
                    }
                    BoxSession.a0(this.mSession.p(), d.q);
                    this.mSession.Y();
                    BoxSession boxSession2 = this.mSession;
                    boxSession2.e(boxSession2.q(), exc);
                    ax.N3.b.b("BoxSession", "TOS refresh exception ", exc);
                    throw ((BoxException) exc);
                }
                this.mSession.e(null, exc);
            }
            BoxAuthentication.BoxAuthenticationInfo.H(this.mSession.mAuthInfo, BoxAuthentication.o().l(this.mSession.J(), this.mSession.p()));
            return this.mSession;
        }
    }

    class a extends Thread {
        final /* synthetic */ h q;

        a(h hVar) {
            this.q = hVar;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.q.run();
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[BoxException.ErrorType.values().length];
            a = iArr;
            try {
                iArr[BoxException.ErrorType.NETWORK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BoxException.ErrorType.IP_BLOCKED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public <E extends BoxAuthentication.g & Serializable> BoxSession(Context context, BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, E e) {
        this.mUserAgent = "com.box.sdk.android/" + g.j;
        this.q = g.i;
        this.mSuppressAuthErrorUIAfterLogin = false;
        this.mEnableBoxAppAuthentication = g.e;
        this.q = context.getApplicationContext();
        R(boxAuthenticationInfo);
        X();
    }

    public BoxSession(Context context, String str) {
        this(context, str, g.c, g.d, g.f);
        if (!SdkUtils.l(g.g)) {
            U(g.g);
        }
        if (SdkUtils.l(g.h)) {
            return;
        }
        U(g.h);
    }

    public <E extends BoxAuthentication.g & Serializable> BoxSession(Context context, String str, E e) {
        this(context, o(str), e);
    }

    public BoxSession(Context context, String str, String str2, String str3, String str4) {
        this.mUserAgent = "com.box.sdk.android/" + g.j;
        this.q = g.i;
        this.mSuppressAuthErrorUIAfterLogin = false;
        this.mEnableBoxAppAuthentication = g.e;
        this.mClientId = str2;
        this.mClientSecret = str3;
        this.mClientRedirectUrl = str4;
        A();
        if (SdkUtils.l(this.mClientId) || SdkUtils.l(this.mClientSecret)) {
            throw new RuntimeException("Session must have a valid client id and client secret specified.");
        }
        this.q = context.getApplicationContext();
        if (!SdkUtils.l(str)) {
            this.mAuthInfo = BoxAuthentication.o().l(str, context);
            this.mUserId = str;
        }
        if (this.mAuthInfo == null) {
            this.mUserId = str;
            this.mAuthInfo = new BoxAuthentication.BoxAuthenticationInfo();
        }
        this.mAuthInfo.T(this.mClientId);
        X();
    }

    private boolean P(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        return (boxAuthenticationInfo == null || boxAuthenticationInfo.P() == null || J() == null || !J().equals(boxAuthenticationInfo.P().J())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a0(Context context, int i) {
        SdkUtils.t(context, i, 1);
    }

    private static BoxAuthentication.BoxAuthenticationInfo o(String str) {
        BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo = new BoxAuthentication.BoxAuthenticationInfo();
        boxAuthenticationInfo.R(str);
        return boxAuthenticationInfo;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Context context = g.i;
        if (context != null) {
            Q(context);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public BoxAuthentication.g A() {
        BoxAuthentication.o().q();
        return null;
    }

    public Long D() {
        return this.mExpiresAt;
    }

    public BoxUser E() {
        return this.mAuthInfo.P();
    }

    public String H() {
        return this.mUserAgent;
    }

    public String J() {
        return this.mUserId;
    }

    public boolean K() {
        return this.mEnableBoxAppAuthentication;
    }

    public h<BoxSession> M() {
        WeakReference<h<BoxSession>> weakReference = this.Y;
        if (weakReference != null && weakReference.get() != null) {
            h<BoxSession> hVar = this.Y.get();
            if (!hVar.isCancelled() && !hVar.isDone()) {
                return hVar;
            }
        }
        h<BoxSession> hVarD = new BoxSessionRefreshRequest(this).D();
        new a(hVarD).start();
        this.Y = new WeakReference<>(hVarD);
        return hVarD;
    }

    public void Q(Context context) {
        this.q = context.getApplicationContext();
    }

    protected void R(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        if (boxAuthenticationInfo == null) {
            BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo2 = new BoxAuthentication.BoxAuthenticationInfo();
            this.mAuthInfo = boxAuthenticationInfo2;
            boxAuthenticationInfo2.T(this.mClientId);
        } else {
            this.mAuthInfo = boxAuthenticationInfo;
        }
        if (this.mAuthInfo.P() == null || SdkUtils.k(this.mAuthInfo.P().J())) {
            W(null);
        } else {
            W(this.mAuthInfo.P().J());
        }
    }

    public void S(String str) {
        this.mAccountEmail = str;
    }

    public void T(String str) {
        this.mDeviceId = str;
    }

    public void U(String str) {
        this.mDeviceName = str;
    }

    public void V(BoxAuthentication.e eVar) {
        this.X = eVar;
    }

    protected void W(String str) {
        this.mUserId = str;
    }

    protected void X() {
        boolean z = false;
        try {
            Context context = this.q;
            if (context != null && context.getPackageManager() != null) {
                if (g.i == null) {
                    g.i = this.q;
                }
                if ((this.q.getPackageManager().getPackageInfo(this.q.getPackageName(), 0).applicationInfo.flags & 2) != 0) {
                    z = true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        g.b = z;
        BoxAuthentication.o().g(this);
    }

    protected void Y() {
        BoxAuthentication.o().y(this);
    }

    public boolean Z() {
        return this.mSuppressAuthErrorUIAfterLogin;
    }

    @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
    public void e(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
        if (P(boxAuthenticationInfo) || (boxAuthenticationInfo == null && J() == null)) {
            BoxAuthentication.e eVar = this.X;
            if (eVar != null) {
                eVar.e(boxAuthenticationInfo, exc);
            }
            if (exc instanceof BoxException) {
                if (b.a[((BoxException) exc).c().ordinal()] != 1) {
                    return;
                }
                a0(this.q, d.p);
            }
        }
    }

    @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
    public void f(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        if (P(boxAuthenticationInfo)) {
            BoxAuthentication.BoxAuthenticationInfo.H(this.mAuthInfo, boxAuthenticationInfo);
            BoxAuthentication.e eVar = this.X;
            if (eVar != null) {
                eVar.f(boxAuthenticationInfo);
            }
        }
    }

    @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
    public void g(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
        if (P(boxAuthenticationInfo) || J() == null) {
            BoxAuthentication.BoxAuthenticationInfo.H(this.mAuthInfo, boxAuthenticationInfo);
            if (boxAuthenticationInfo.P() != null) {
                W(boxAuthenticationInfo.P().J());
            }
            BoxAuthentication.e eVar = this.X;
            if (eVar != null) {
                eVar.g(boxAuthenticationInfo);
            }
        }
    }

    public h<BoxSession> m(Context context) {
        return n(context, null);
    }

    public h<BoxSession> n(Context context, h.b<BoxSession> bVar) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            this.q = applicationContext;
            g.i = applicationContext;
        }
        if (!SdkUtils.k(this.mLastAuthCreationTaskId)) {
            ThreadPoolExecutor threadPoolExecutor = Z;
            if (threadPoolExecutor instanceof ax.N3.g) {
                Runnable runnableD = ((ax.N3.g) threadPoolExecutor).d(this.mLastAuthCreationTaskId);
                if (runnableD instanceof BoxSessionAuthCreationRequest.b) {
                    BoxSessionAuthCreationRequest.b bVar2 = (BoxSessionAuthCreationRequest.b) runnableD;
                    if (bVar != null) {
                        bVar2.a(bVar);
                    }
                    bVar2.b();
                    return bVar2;
                }
            }
        }
        h<BoxSession> hVarD = new BoxSessionAuthCreationRequest(this, this.mEnableBoxAppAuthentication).D();
        if (bVar != null) {
            hVarD.a(bVar);
        }
        this.mLastAuthCreationTaskId = hVarD.toString();
        Z.execute(hVarD);
        return hVarD;
    }

    public Context p() {
        return this.q;
    }

    public BoxAuthentication.BoxAuthenticationInfo q() {
        return this.mAuthInfo;
    }

    public String s() {
        return this.mAccountEmail;
    }

    public File t() {
        return new File(p().getFilesDir(), J());
    }

    public String u() {
        return this.mClientId;
    }

    public String v() {
        return this.mClientSecret;
    }

    public String w() {
        return this.mDeviceId;
    }

    public String x() {
        return this.mDeviceName;
    }

    public BoxMDMData y() {
        return this.mMDMData;
    }

    public String z() {
        return this.mClientRedirectUrl;
    }
}
