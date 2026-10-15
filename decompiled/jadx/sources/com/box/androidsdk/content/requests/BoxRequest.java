package com.box.androidsdk.content.requests;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import ax.K3.g;
import ax.K3.h;
import ax.O3.d;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.auth.BlockedIPErrorActivity;
import com.box.androidsdk.content.auth.BoxAuthentication;
import com.box.androidsdk.content.models.BoxJsonObject;
import com.box.androidsdk.content.models.BoxObject;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.models.BoxSharedLinkSession;
import com.box.androidsdk.content.requests.BoxRequest;
import com.box.androidsdk.content.utils.SdkUtils;
import j$.net.URLEncoder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxRequest<T extends BoxObject, R extends BoxRequest<T, R>> implements Serializable {
    transient a X;
    private transient WeakReference<b> Y;
    Class<T> mClazz;
    private String mIfMatchEtag;
    private String mIfNoneMatchEtag;
    protected Methods mRequestMethod;
    protected String mRequestUrlString;
    protected BoxSession mSession;
    private String mStringBody;
    protected int mTimeout;
    protected transient ax.L3.b q;
    protected HashMap<String, String> mQueryMap = new HashMap<>();
    protected LinkedHashMap<String, Object> mBodyMap = new LinkedHashMap<>();
    protected LinkedHashMap<String, String> mHeaderMap = new LinkedHashMap<>();
    protected ContentTypes mContentType = ContentTypes.JSON;
    protected boolean mRequiresSocket = false;

    public enum ContentTypes {
        JSON("application/json"),
        URL_ENCODED("application/x-www-form-urlencoded"),
        JSON_PATCH("application/json-patch+json"),
        APPLICATION_OCTET_STREAM("application/octet-stream");

        private String mName;

        ContentTypes(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    public enum Methods {
        GET,
        POST,
        PUT,
        DELETE,
        OPTIONS
    }

    public static class a<R extends BoxRequest> {
        protected R a;
        protected int b = 0;
        private int c = 0;

        public a(R r) {
            this.a = r;
        }

        private boolean a(com.box.androidsdk.content.requests.b bVar) {
            return bVar != null && bVar.f() == 401;
        }

        protected static int c(com.box.androidsdk.content.requests.b bVar, int i) {
            String headerField = bVar.e().getHeaderField("Retry-After");
            if (!SdkUtils.k(headerField)) {
                try {
                    i = Integer.parseInt(headerField);
                } catch (NumberFormatException unused) {
                }
                if (i <= 0) {
                    i = 1;
                }
            }
            return i * 1000;
        }

        private boolean d(String str) {
            String str2;
            String[] strArrSplit = str.split("=");
            return strArrSplit.length == 2 && (str2 = strArrSplit[0]) != null && strArrSplit[1] != null && "error".equalsIgnoreCase(str2.trim()) && "invalid_token".equalsIgnoreCase(strArrSplit[1].replace("\"", HttpUrl.FRAGMENT_ENCODE_SET).trim());
        }

        private boolean f(com.box.androidsdk.content.requests.b bVar) {
            if (bVar == null || 401 != bVar.f()) {
                return false;
            }
            String headerField = bVar.a.getHeaderField("WWW-Authenticate");
            if (!SdkUtils.l(headerField)) {
                for (String str : headerField.split(",")) {
                    if (d(str)) {
                        return true;
                    }
                }
            }
            return false;
        }

        protected void b(com.box.androidsdk.content.requests.b bVar) throws BoxException {
            try {
                bVar.e().disconnect();
            } catch (Exception e) {
                ax.N3.b.c("Interrupt disconnect", e);
            }
            throw new BoxException("Thread interrupted request cancelled ", new InterruptedException());
        }

        public boolean e(com.box.androidsdk.content.requests.b bVar) {
            int iF = bVar.f();
            return (iF >= 200 && iF < 300) || iF == 429;
        }

        public boolean g(BoxRequest boxRequest, com.box.androidsdk.content.requests.b bVar, BoxException boxException) throws BoxException.RefreshFailure {
            BoxException.ErrorType errorTypeC;
            BoxSession boxSessionL = boxRequest.l();
            if (f(bVar)) {
                try {
                    BoxResponse boxResponse = boxSessionL.M().get();
                    if (boxResponse.c()) {
                        return true;
                    }
                    if (boxResponse.a() == null || !(boxResponse.a() instanceof BoxException.RefreshFailure)) {
                        return false;
                    }
                    throw ((BoxException.RefreshFailure) boxResponse.a());
                } catch (InterruptedException e) {
                    ax.N3.b.b("oauthRefresh", "Interrupted Exception", e);
                } catch (ExecutionException e2) {
                    ax.N3.b.b("oauthRefresh", "Interrupted Exception", e2);
                }
            } else if (a(bVar)) {
                BoxException.ErrorType errorTypeC2 = boxException.c();
                if (!boxSessionL.Z()) {
                    Context contextP = boxSessionL.p();
                    if (errorTypeC2 == BoxException.ErrorType.IP_BLOCKED || errorTypeC2 == BoxException.ErrorType.LOCATION_BLOCKED) {
                        Intent intent = new Intent(boxSessionL.p(), (Class<?>) BlockedIPErrorActivity.class);
                        intent.addFlags(268435456);
                        contextP.startActivity(intent);
                        return false;
                    }
                    if (errorTypeC2 == BoxException.ErrorType.TERMS_OF_SERVICE_REQUIRED) {
                        SdkUtils.t(contextP, d.q, 1);
                    }
                    try {
                        if (this.c <= 4) {
                            BoxResponse boxResponse2 = boxSessionL.M().get();
                            if (boxResponse2.c()) {
                                this.c++;
                                return true;
                            }
                            if (boxResponse2.a() == null || !(boxResponse2.a() instanceof BoxException.RefreshFailure)) {
                                return false;
                            }
                            throw ((BoxException.RefreshFailure) boxResponse2.a());
                        }
                        String str = " Exceeded max refresh retries for " + boxRequest.getClass().getName() + " response code" + boxException.e() + " response " + bVar;
                        if (boxException.b() != null) {
                            str = str + boxException.b().z();
                        }
                        ax.N3.b.f("authFailed", str, boxException);
                        return false;
                    } catch (InterruptedException e3) {
                        ax.N3.b.b("oauthRefresh", "Interrupted Exception", e3);
                    } catch (ExecutionException e4) {
                        ax.N3.b.b("oauthRefresh", "Interrupted Exception", e4);
                    }
                }
            } else if (bVar != null && bVar.f() == 403 && ((errorTypeC = boxException.c()) == BoxException.ErrorType.IP_BLOCKED || errorTypeC == BoxException.ErrorType.LOCATION_BLOCKED)) {
                Context contextP2 = boxSessionL.p();
                Intent intent2 = new Intent(boxSessionL.p(), (Class<?>) BlockedIPErrorActivity.class);
                intent2.addFlags(268435456);
                contextP2.startActivity(intent2);
            }
            return false;
        }

        public <T extends BoxObject> T h(Class<T> cls, com.box.androidsdk.content.requests.b bVar) throws IllegalAccessException, BoxException, InstantiationException {
            if (bVar.f() == 429) {
                return (T) i(bVar);
            }
            if (Thread.currentThread().isInterrupted()) {
                b(bVar);
            }
            String strD = bVar.d();
            T tNewInstance = cls.newInstance();
            if ((tNewInstance instanceof BoxJsonObject) && strD.contains(ContentTypes.JSON.toString())) {
                ((BoxJsonObject) tNewInstance).j(bVar.g());
            }
            return tNewInstance;
        }

        protected <T extends BoxObject> T i(com.box.androidsdk.content.requests.b bVar) throws BoxException {
            int i = this.b;
            if (i >= 1) {
                throw new BoxException.RateLimitAttemptsExceeded("Max attempts exceeded", this.b, bVar);
            }
            this.b = i + 1;
            try {
                Thread.sleep(c(bVar, ((int) (Math.random() * 10.0d)) + 20));
                return (T) this.a.x();
            } catch (InterruptedException e) {
                throw new BoxException(e.getMessage(), e);
            }
        }
    }

    static class b extends SSLSocketFactory {
        public SSLSocketFactory a;
        private WeakReference<Socket> b;

        public b(SSLSocketFactory sSLSocketFactory) {
            this.a = sSLSocketFactory;
        }

        public Socket a() {
            WeakReference<Socket> weakReference = this.b;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        Socket b(Socket socket) {
            this.b = new WeakReference<>(socket);
            return socket;
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i) throws IOException {
            return b(this.a.createSocket(str, i));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return b(this.a.createSocket(str, i, inetAddress, i2));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return b(this.a.createSocket(inetAddress, i));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return b(this.a.createSocket(inetAddress, i, inetAddress2, i2));
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return b(this.a.createSocket(socket, str, i, z));
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getDefaultCipherSuites() {
            return this.a.getDefaultCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getSupportedCipherSuites() {
            return this.a.getDefaultCipherSuites();
        }
    }

    public static class c extends b {
        private final String[] c;

        public c() {
            super(BoxRequest.o());
            this.c = new String[]{"TLSv1.1", "TLSv1.2"};
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b
        public /* bridge */ /* synthetic */ Socket a() {
            return super.a();
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b
        Socket b(Socket socket) {
            if (socket instanceof SSLSocket) {
                ((SSLSocket) socket).setEnabledProtocols(this.c);
            }
            return super.b(socket);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.SocketFactory
        public /* bridge */ /* synthetic */ Socket createSocket(String str, int i) throws IOException {
            return super.createSocket(str, i);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.SocketFactory
        public /* bridge */ /* synthetic */ Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return super.createSocket(str, i, inetAddress, i2);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.SocketFactory
        public /* bridge */ /* synthetic */ Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return super.createSocket(inetAddress, i);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.SocketFactory
        public /* bridge */ /* synthetic */ Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return super.createSocket(inetAddress, i, inetAddress2, i2);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.ssl.SSLSocketFactory
        public /* bridge */ /* synthetic */ Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return super.createSocket(socket, str, i, z);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.ssl.SSLSocketFactory
        public /* bridge */ /* synthetic */ String[] getDefaultCipherSuites() {
            return super.getDefaultCipherSuites();
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.b, javax.net.ssl.SSLSocketFactory
        public /* bridge */ /* synthetic */ String[] getSupportedCipherSuites() {
            return super.getSupportedCipherSuites();
        }
    }

    public BoxRequest(Class<T> cls, String str, BoxSession boxSession) {
        this.mClazz = cls;
        this.mRequestUrlString = str;
        this.mSession = boxSession;
        C(new a(this));
    }

    private void b(StringBuilder sb, HashMap<String, String> map) {
        for (String str : map.keySet()) {
            sb.append(str);
            sb.append(map.get(str));
        }
    }

    private boolean c(HashMap<String, String> map, HashMap<String, String> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (String str : map.keySet()) {
            if (!map2.containsKey(str) || !map.get(str).equals(map2.get(str))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SSLSocketFactory o() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, null, null);
            return sSLContext.getSocketFactory();
        } catch (Exception e) {
            ax.N3.b.c("Unable to create SSLContext", e);
            return null;
        }
    }

    private T p(a aVar, com.box.androidsdk.content.requests.b bVar, Exception exc) throws BoxException {
        if (!(exc instanceof BoxException)) {
            BoxException boxException = new BoxException("Couldn't connect to the Box API due to a network error.", exc);
            aVar.g(this, bVar, boxException);
            throw boxException;
        }
        BoxException boxException2 = (BoxException) exc;
        if (aVar.g(this, bVar, boxException2)) {
            return (T) x();
        }
        throw boxException2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.X = new a(this);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public R A(ContentTypes contentTypes) {
        this.mContentType = contentTypes;
        return this;
    }

    protected void B(com.box.androidsdk.content.requests.a aVar) {
        h();
        for (Map.Entry<String, String> entry : this.mHeaderMap.entrySet()) {
            aVar.a(entry.getKey(), entry.getValue());
        }
    }

    public R C(a aVar) {
        this.X = aVar;
        return this;
    }

    public h<T> D() {
        return new h<>(this.mClazz, this);
    }

    protected URL d() throws MalformedURLException, UnsupportedEncodingException {
        String strJ = j(this.mQueryMap);
        return TextUtils.isEmpty(strJ) ? new URL(this.mRequestUrlString) : new URL(String.format(Locale.ENGLISH, "%s?%s", this.mRequestUrlString, strJ));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BoxRequest)) {
            return false;
        }
        BoxRequest boxRequest = (BoxRequest) obj;
        return this.mRequestMethod == boxRequest.mRequestMethod && this.mRequestUrlString.equals(boxRequest.mRequestUrlString) && c(this.mHeaderMap, boxRequest.mHeaderMap) && c(this.mQueryMap, boxRequest.mQueryMap);
    }

    protected void h() {
        this.mHeaderMap.clear();
        BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfoQ = this.mSession.q();
        String strD = boxAuthenticationInfoQ == null ? null : boxAuthenticationInfoQ.D();
        if (!SdkUtils.l(strD)) {
            this.mHeaderMap.put("Authorization", String.format(Locale.ENGLISH, "Bearer %s", strD));
        }
        this.mHeaderMap.put("User-Agent", this.mSession.H());
        this.mHeaderMap.put("Accept-Encoding", "gzip");
        this.mHeaderMap.put("Accept-Charset", "utf-8");
        ContentTypes contentTypes = this.mContentType;
        if (contentTypes != null) {
            this.mHeaderMap.put("Content-Type", contentTypes.toString());
        }
        String str = this.mIfMatchEtag;
        if (str != null) {
            this.mHeaderMap.put("If-Match", str);
        }
        String str2 = this.mIfNoneMatchEtag;
        if (str2 != null) {
            this.mHeaderMap.put("If-None-Match", str2);
        }
        BoxSession boxSession = this.mSession;
        if (boxSession instanceof BoxSharedLinkSession) {
            BoxSharedLinkSession boxSharedLinkSession = (BoxSharedLinkSession) boxSession;
            if (TextUtils.isEmpty(boxSharedLinkSession.c0())) {
                return;
            }
            Locale locale = Locale.ENGLISH;
            String str3 = String.format(locale, "shared_link=%s", boxSharedLinkSession.c0());
            if (!TextUtils.isEmpty(boxSharedLinkSession.b0())) {
                str3 = str3 + String.format(locale, "&shared_link_password=%s", boxSharedLinkSession.b0());
            }
            this.mHeaderMap.put("BoxApi", str3);
        }
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mRequestMethod);
        sb.append(this.mRequestUrlString);
        b(sb, this.mHeaderMap);
        b(sb, this.mQueryMap);
        return sb.toString().hashCode();
    }

    protected com.box.androidsdk.content.requests.a i() throws BoxException, IOException {
        com.box.androidsdk.content.requests.a aVar = new com.box.androidsdk.content.requests.a(d(), this.mRequestMethod, this.q);
        B(aVar);
        z(aVar);
        return aVar;
    }

    protected String j(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        String str = "%s=%s";
        boolean z = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(String.format(Locale.ENGLISH, str, URLEncoder.encode(entry.getKey(), "UTF-8"), URLEncoder.encode(entry.getValue(), "UTF-8")));
            if (z) {
                str = "&" + str;
                z = false;
            }
        }
        return sb.toString();
    }

    public a k() {
        return this.X;
    }

    public BoxSession l() {
        return this.mSession;
    }

    protected Socket m() {
        WeakReference<b> weakReference = this.Y;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.Y.get().a();
    }

    public String n() throws UnsupportedEncodingException {
        String str = this.mStringBody;
        if (str != null) {
            return str;
        }
        ContentTypes contentTypes = this.mContentType;
        if (contentTypes != null) {
            int iOrdinal = contentTypes.ordinal();
            if (iOrdinal == 0) {
                ax.U4.d dVar = new ax.U4.d();
                Iterator<Map.Entry<String, Object>> it = this.mBodyMap.entrySet().iterator();
                while (it.hasNext()) {
                    v(dVar, it.next());
                }
                this.mStringBody = dVar.toString();
            } else if (iOrdinal == 1) {
                HashMap map = new HashMap();
                for (Map.Entry<String, Object> entry : this.mBodyMap.entrySet()) {
                    map.put(entry.getKey(), (String) entry.getValue());
                }
                this.mStringBody = j(map);
            } else if (iOrdinal == 2) {
                this.mStringBody = ((ax.M3.a) this.mBodyMap.get("json_object")).g();
            }
        }
        return this.mStringBody;
    }

    protected void q(BoxResponse<T> boxResponse) throws BoxException {
        g.a();
    }

    protected void r(com.box.androidsdk.content.requests.b bVar) throws BoxException {
        try {
            s();
            ax.N3.b.d("BoxContentSdk", String.format(Locale.ENGLISH, "Response (%s):  %s", Integer.valueOf(bVar.f()), bVar.g()));
        } catch (Exception e) {
            ax.N3.b.c("logDebug", e);
        }
    }

    protected void s() {
        String string;
        try {
            string = d().toString();
        } catch (UnsupportedEncodingException | MalformedURLException unused) {
            string = null;
        }
        Locale locale = Locale.ENGLISH;
        ax.N3.b.d("BoxContentSdk", String.format(locale, "Request (%s):  %s", this.mRequestMethod, string));
        ax.N3.b.e("BoxContentSdk", "Request Header", this.mHeaderMap);
        ContentTypes contentTypes = this.mContentType;
        if (contentTypes != null) {
            int iOrdinal = contentTypes.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1) {
                    HashMap map = new HashMap();
                    for (Map.Entry<String, Object> entry : this.mBodyMap.entrySet()) {
                        map.put(entry.getKey(), (String) entry.getValue());
                    }
                    ax.N3.b.e("BoxContentSdk", "Request Form Data", map);
                    return;
                }
                if (iOrdinal != 2) {
                    return;
                }
            }
            if (SdkUtils.k(this.mStringBody)) {
                return;
            }
            ax.N3.b.d("BoxContentSdk", String.format(locale, "Request JSON:  %s", this.mStringBody));
        }
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00a0  */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0030: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:10:0x0030 */
    protected T t() throws Throwable {
        HttpURLConnection httpURLConnectionB;
        HttpURLConnection httpURLConnection;
        a aVarK = k();
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                com.box.androidsdk.content.requests.a aVarI = i();
                httpURLConnectionB = aVarI.b();
                try {
                    if (this.mRequiresSocket && (httpURLConnectionB instanceof HttpsURLConnection)) {
                        b bVar = new b(((HttpsURLConnection) httpURLConnectionB).getSSLSocketFactory());
                        this.Y = new WeakReference<>(bVar);
                        ((HttpsURLConnection) httpURLConnectionB).setSSLSocketFactory(bVar);
                    }
                    int i = this.mTimeout;
                    if (i > 0) {
                        httpURLConnectionB.setConnectTimeout(i);
                        httpURLConnectionB.setReadTimeout(this.mTimeout);
                    }
                    com.box.androidsdk.content.requests.b bVarY = y(aVarI, httpURLConnectionB);
                    r(bVarY);
                    if (!aVarK.e(bVarY)) {
                        throw new BoxException("An error occurred while sending the request", bVarY);
                    }
                    T t = (T) aVarK.h(this.mClazz, bVarY);
                    if (httpURLConnectionB != null) {
                        httpURLConnectionB.disconnect();
                    }
                    return t;
                } catch (BoxException e) {
                    e = e;
                    T t2 = (T) p(aVarK, null, e);
                    if (httpURLConnectionB != null) {
                        httpURLConnectionB.disconnect();
                    }
                    return t2;
                } catch (IOException e2) {
                    e = e2;
                    T t3 = (T) p(aVarK, null, e);
                    if (httpURLConnectionB != null) {
                        httpURLConnectionB.disconnect();
                    }
                    return t3;
                } catch (IllegalAccessException e3) {
                    e = e3;
                    T t4 = (T) p(aVarK, null, e);
                    if (httpURLConnectionB != null) {
                        httpURLConnectionB.disconnect();
                    }
                    return t4;
                } catch (InstantiationException e4) {
                    e = e4;
                    T t5 = (T) p(aVarK, null, e);
                    if (httpURLConnectionB != null) {
                        httpURLConnectionB.disconnect();
                    }
                    return t5;
                }
            } catch (Throwable th) {
                th = th;
                httpURLConnection2 = httpURLConnection;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (BoxException e5) {
            e = e5;
            httpURLConnectionB = null;
        } catch (IOException e6) {
            e = e6;
            httpURLConnectionB = null;
        } catch (IllegalAccessException e7) {
            e = e7;
            httpURLConnectionB = null;
        } catch (InstantiationException e8) {
            e = e8;
            httpURLConnectionB = null;
        } catch (Throwable th2) {
            th = th2;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    protected void u(BoxResponse<T> boxResponse) throws BoxException {
    }

    protected void v(ax.U4.d dVar, Map.Entry<String, Object> entry) {
        Object value = entry.getValue();
        if (value instanceof BoxJsonObject) {
            dVar.A(entry.getKey(), w(value));
            return;
        }
        if (value instanceof Double) {
            dVar.D(entry.getKey(), Double.toString(((Double) value).doubleValue()));
            return;
        }
        if ((value instanceof Enum) || (value instanceof Boolean)) {
            dVar.D(entry.getKey(), value.toString());
            return;
        }
        if (value instanceof ax.U4.a) {
            dVar.A(entry.getKey(), (ax.U4.a) value);
            return;
        }
        if (value instanceof Long) {
            dVar.A(entry.getKey(), ax.U4.g.w(((Long) value).longValue()));
            return;
        }
        if (value instanceof Integer) {
            dVar.A(entry.getKey(), ax.U4.g.v(((Integer) value).intValue()));
            return;
        }
        if (value instanceof Float) {
            dVar.A(entry.getKey(), ax.U4.g.u(((Float) value).floatValue()));
            return;
        }
        if (value instanceof String) {
            dVar.D(entry.getKey(), (String) value);
            return;
        }
        ax.N3.b.c("Unable to parse value " + value, new RuntimeException("Invalid value"));
    }

    protected ax.U4.g w(Object obj) {
        return ax.U4.g.t(((BoxJsonObject) obj).z());
    }

    public final T x() throws BoxException {
        Pattern patternCompile = Pattern.compile(".*\\/\\.+.*");
        String str = this.mRequestUrlString;
        if (str != null && patternCompile.matcher(str).matches()) {
            throw new BoxException("An invalid path parameter passed. Relative path parameters cannot be passed.");
        }
        T t = null;
        try {
            e = null;
            t = (T) t();
        } catch (Exception e) {
            e = e;
        }
        u(new BoxResponse<>(t, e, this));
        if (e == null) {
            return t;
        }
        if (e instanceof BoxException) {
            throw ((BoxException) e);
        }
        throw new BoxException("unexpected exception ", e);
    }

    protected com.box.androidsdk.content.requests.b y(com.box.androidsdk.content.requests.a aVar, HttpURLConnection httpURLConnection) throws BoxException, IOException {
        com.box.androidsdk.content.requests.b bVar = new com.box.androidsdk.content.requests.b(httpURLConnection);
        bVar.i();
        return bVar;
    }

    protected void z(com.box.androidsdk.content.requests.a aVar) throws IOException {
        if (this.mBodyMap.isEmpty()) {
            return;
        }
        aVar.c(new ByteArrayInputStream(n().getBytes("UTF-8")));
    }
}
