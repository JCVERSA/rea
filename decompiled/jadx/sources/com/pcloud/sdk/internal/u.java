package com.pcloud.sdk.internal;

import ax.Sa.InterfaceC1531a;
import ax.p192ld.InterfaceC2156f;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.pcloud.sdk.internal.networking.serialization.ByteStringTypeAdapter;
import com.pcloud.sdk.internal.networking.serialization.DateTypeAdapter;
import com.pcloud.sdk.internal.networking.serialization.ResolutionDeserializer;
import com.pcloud.sdk.internal.networking.serialization.UnmodifiableListTypeFactory;
import j$.util.Objects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes3.dex */
class u implements InterfaceC1531a {
    private static final String g = "----pCloud-SDK-1.11.0-" + UUID.randomUUID() + "----";
    private final long a;
    private final ax.Sa.c b;
    private final Gson c;
    private final OkHttpClient d;
    private final Executor e;
    private final HttpUrl f;

    class a extends RequestBody {
        final /* synthetic */ ax.Sa.r a;
        final /* synthetic */ ax.Sa.m b;

        a(ax.Sa.r rVar, ax.Sa.m mVar) {
            this.a = rVar;
            this.b = mVar;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            long jA = this.b.a();
            if (jA >= 0) {
                return jA;
            }
            throw new IllegalArgumentException("Content length must be >= 0.");
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return MediaType.parse("multipart/form-data");
        }

        @Override // okhttp3.RequestBody
        public void writeTo(InterfaceC2156f interfaceC2156f) throws IOException {
            ax.Sa.r runnableC6446b = this.a;
            if (runnableC6446b == null) {
                this.b.b(interfaceC2156f);
                return;
            }
            if (u.this.e != null) {
                runnableC6446b = new RunnableC6446b(this.a, u.this.e);
            }
            InterfaceC2156f interfaceC2156fC = ax.p192ld.p.c(new f(interfaceC2156f, this.b.a(), runnableC6446b, u.this.a));
            this.b.b(interfaceC2156fC);
            interfaceC2156fC.H();
        }
    }

    u(v vVar) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("timeformat", "timestamp");
        String str = String.format(Locale.US, "pCloud SDK Java %s", "1.11.0");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        long jL = vVar.l();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient.Builder builderAddInterceptor = builder.readTimeout(jL, timeUnit).writeTimeout(vVar.m(), timeUnit).connectTimeout(vVar.h(), timeUnit).protocols(Collections.singletonList(Protocol.HTTP_1_1)).addInterceptor(new C6447c(str, treeMap));
        if (vVar.j() != null) {
            builderAddInterceptor.dispatcher(vVar.j());
        }
        if (vVar.i() != null) {
            builderAddInterceptor.connectionPool(vVar.i());
        }
        if (vVar.f() != null) {
            builderAddInterceptor.cache(vVar.f());
        }
        builderAddInterceptor.authenticator(Authenticator.NONE);
        ax.Sa.c cVarE = vVar.e();
        this.b = cVarE;
        if (cVarE != null) {
            builderAddInterceptor.addInterceptor((w) vVar.e());
        }
        this.d = builderAddInterceptor.build();
        this.e = vVar.g();
        this.a = vVar.k();
        this.c = new com.google.gson.a().c().e(new RealRemoteEntry.TypeAdapterFactory()).e(new UnmodifiableListTypeFactory()).d(ax.Sa.u.class, new RealRemoteEntry.FileEntryDeserializer()).d(Date.class, new DateTypeAdapter()).d(z.class, new z.a(this)).d(A.class, new A.a(this)).d(ax.p192ld.h.class, new ByteStringTypeAdapter()).d(ax.Sa.x.class, ResolutionDeserializer.a).b();
        this.f = vVar.d();
    }

    private static void E(HttpUrl.Builder builder, Long l, ax.Sa.x xVar, boolean z) {
        builder.addQueryParameter("fileid", String.valueOf(l));
        builder.addQueryParameter("size", String.format(Locale.US, "%dx%d", Integer.valueOf(xVar.b()), Integer.valueOf(xVar.a())));
        if (z) {
            builder.addQueryParameter("crop", String.valueOf(1));
        }
    }

    private static List<URL> F(List<String> list, String str) throws MalformedURLException {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new URL("https", it.next(), str));
        }
        return arrayList;
    }

    private ax.Sa.i<ax.Sa.v> G(Long l, String str, String str2, ax.Sa.m mVar, Date date, ax.Sa.r rVar, ax.Sa.y yVar) {
        if (str2 == null) {
            throw new IllegalArgumentException("Filename cannot be null.");
        }
        if (mVar == null) {
            throw new IllegalArgumentException("File data cannot be null.");
        }
        if (yVar == null) {
            throw new IllegalArgumentException("Upload options cannot be null.");
        }
        MultipartBody multipartBodyBuild = new MultipartBody.Builder(g).setType(MultipartBody.FORM).addFormDataPart("file", str2, new a(rVar, mVar)).build();
        HttpUrl.Builder builderAddQueryParameter = this.f.newBuilder().addPathSegment("uploadfile").addQueryParameter("renameifexists", String.valueOf(!yVar.c() ? 1 : 0)).addQueryParameter("nopartial", String.valueOf(!yVar.d() ? 1 : 0));
        if (l != null) {
            builderAddQueryParameter.addQueryParameter("folderid", String.valueOf(l));
        }
        if (str != null) {
            builderAddQueryParameter.addEncodedQueryParameter("path", str);
        }
        if (date != null) {
            builderAddQueryParameter.addQueryParameter("mtime", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(date.getTime())));
        }
        return c0(new Request.Builder().url(builderAddQueryParameter.build()).method("POST", multipartBodyBuild).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.j
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.Q(response);
            }
        });
    }

    private <T> T K(Response response, Class<? extends T> cls) throws IOException {
        try {
            if (!response.isSuccessful()) {
                throw new ax.Ua.a(response.code(), response.message());
            }
            ResponseBody responseBodyBody = response.body();
            Objects.requireNonNull(responseBodyBody);
            JsonReader jsonReader = new JsonReader(new BufferedReader(new InputStreamReader(responseBodyBody.byteStream())));
            try {
                try {
                    T t = (T) this.c.h(jsonReader, cls);
                    ax.Ta.a.a(jsonReader);
                    ax.Ta.a.a(response);
                    return t;
                } catch (ax.Y8.r e) {
                    throw new IOException("Malformed JSON response.", e);
                }
            } catch (Throwable th) {
                ax.Ta.a.a(jsonReader);
                throw th;
            }
        } catch (Throwable th2) {
            ax.Ta.a.a(response);
            throw th2;
        }
    }

    private <T extends ax.Ua.b> T L(Response response, Class<? extends T> cls) throws ax.Sa.b, IOException {
        T t = (T) K(response, cls);
        if (t == null) {
            throw new IOException("API returned an empty response body.");
        }
        if (t.c()) {
            return t;
        }
        throw new ax.Sa.b(t.b(), t.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ax.Sa.p M(Response response) throws ax.Sa.b, IOException {
        ax.Ua.d dVar = (ax.Ua.d) L(response, ax.Ua.d.class);
        return new y(this, dVar.d(), F(dVar.e(), dVar.f()), dVar.g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ax.p192ld.g N(Response response) throws ax.Ua.a {
        try {
            if (!response.isSuccessful()) {
                throw new ax.Ua.a(response.code(), response.message());
            }
            ResponseBody responseBodyBody = response.body();
            Objects.requireNonNull(responseBodyBody);
            return responseBodyBody.source();
        } catch (Throwable th) {
            if (0 == 0) {
                ax.Ta.a.a(response);
            }
            throw th;
        }
    }

    private boolean O(String str) {
        return (str == null || str.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.v P(Response response) throws ax.Sa.b, IOException {
        return ((ax.Ua.e) L(response, ax.Ua.e.class)).d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.v Q(Response response) throws ax.Sa.b, IOException {
        ax.Ua.g gVar = (ax.Ua.g) L(response, ax.Ua.g.class);
        if (gVar.d().isEmpty()) {
            throw new IOException("API uploaded file but did not return remote file data.");
        }
        return gVar.d().get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.w R(Response response) throws ax.Sa.b, IOException {
        return ((ax.Ua.f) L(response, ax.Ua.f.class)).d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean S(Response response) throws ax.Sa.b, IOException {
        ax.Ua.e eVar = (ax.Ua.e) K(response, ax.Ua.e.class);
        return Boolean.valueOf(eVar.c() && eVar.d() != null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean T(Response response) throws ax.Sa.b, IOException {
        ax.Ua.e eVar = (ax.Ua.e) K(response, ax.Ua.e.class);
        return Boolean.valueOf(eVar.c() && eVar.d() != null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean U(Response response) throws ax.Sa.b, IOException {
        L(response, ax.Ua.b.class);
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean V(Response response) throws ax.Sa.b, IOException {
        L(response, ax.Ua.b.class);
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.A W(Response response) throws ax.Sa.b, IOException {
        ax.Ua.h hVar = (ax.Ua.h) L(response, ax.Ua.h.class);
        return new B(hVar.g(), hVar.d(), hVar.h(), hVar.e(), hVar.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.w X(Response response) throws ax.Sa.b, IOException {
        return ((ax.Ua.f) L(response, ax.Ua.f.class)).d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.v Y(Response response) throws ax.Sa.b, IOException {
        return ((ax.Ua.e) L(response, ax.Ua.e.class)).d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.v Z(Response response) throws ax.Sa.b, IOException {
        return ((ax.Ua.e) L(response, ax.Ua.e.class)).d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ax.Sa.w a0(Response response) throws ax.Sa.b, IOException {
        return ((ax.Ua.f) L(response, ax.Ua.f.class)).d();
    }

    private <T> ax.Sa.i<T> c0(Request request, ax.Ta.b<T> bVar) {
        C6449e c6449e = new C6449e(this.d.newCall(request), bVar);
        Executor executor = this.e;
        return executor != null ? new E(c6449e, executor) : c6449e;
    }

    private Request d0(Long l, String str, ax.Sa.n nVar) {
        HttpUrl.Builder builderAddPathSegment = this.f.newBuilder().addPathSegment("getfilelink");
        if (l != null) {
            builderAddPathSegment.addQueryParameter("fileid", String.valueOf(l));
        }
        if (str != null) {
            builderAddPathSegment.addEncodedQueryParameter("path", str);
        }
        if (nVar.c()) {
            builderAddPathSegment.addQueryParameter("forcedownload", String.valueOf(1));
        }
        if (nVar.e()) {
            builderAddPathSegment.addQueryParameter("skipfilename", String.valueOf(1));
        }
        if (nVar.a() != null) {
            MediaType mediaType = MediaType.parse(nVar.a());
            if (mediaType == null) {
                throw new IllegalArgumentException("Invalid or not well-formatted content type DownloadOptions argument");
            }
            builderAddPathSegment.addQueryParameter("contenttype", mediaType.toString());
        }
        return new Request.Builder().url(builderAddPathSegment.build()).get().build();
    }

    private Request.Builder e0() {
        return new Request.Builder().url(this.f);
    }

    private Request f0(Long l, ax.Sa.x xVar, boolean z) {
        HttpUrl.Builder builderAddPathSegment = this.f.newBuilder().addPathSegment("getthumb");
        E(builderAddPathSegment, l, xVar, z);
        return new Request.Builder().url(builderAddPathSegment.build()).get().build();
    }

    private void g0(String str) {
        h0(str, null);
    }

    private void h0(String str, String str2) {
        if (O(str)) {
            return;
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("Path argument cannot be null or empty.");
        }
        throw new IllegalArgumentException("Path argument `" + str2 + "` cannot be null or empty.");
    }

    private static void i0(ax.Sa.x xVar) {
        Objects.requireNonNull(xVar);
        j0(xVar.b(), "Width");
        j0(xVar.a(), "Height");
    }

    private static void j0(int i, String str) {
        if (!ax.Ta.c.Y.j(i)) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s must be in the range [16,2048].", str));
        }
    }

    public ax.Sa.i<Boolean> H(long j) {
        return c0(new Request.Builder().url(this.f.newBuilder().addPathSegment("deletefile").build()).get().post(new FormBody.Builder().add("fileid", String.valueOf(j)).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.r
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.S(response);
            }
        });
    }

    public ax.Sa.i<Boolean> I(long j) {
        return J(j, false);
    }

    public ax.Sa.i<Boolean> J(long j, boolean z) {
        return c0(e0().url(this.f.newBuilder().addPathSegment(z ? "deletefolderrecursive" : "deletefolder").build()).post(new FormBody.Builder().add("folderid", String.valueOf(j)).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.k
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.U(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.w> a(String str, String str2) {
        h0(str, "path");
        h0(str2, "toPath");
        return c0(e0().url(this.f.newBuilder().addPathSegment("renamefolder").build()).post(new FormBody.Builder().addEncoded("path", str).addEncoded("topath", str2).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.o
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.a0(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.v> b(String str, String str2, ax.Sa.m mVar, ax.Sa.y yVar) {
        return d(str, str2, mVar, null, null, yVar);
    }

    public ax.Sa.i<ax.Sa.w> b0(String str, boolean z) {
        g0(str);
        HttpUrl.Builder builderAddEncodedQueryParameter = this.f.newBuilder().addPathSegment("listfolder").addEncodedQueryParameter("path", str);
        if (z) {
            builderAddEncodedQueryParameter.addEncodedQueryParameter("recursive", String.valueOf(1));
        }
        return c0(e0().url(builderAddEncodedQueryParameter.build()).get().build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.h
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.X(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.v> c(String str, String str2, boolean z) {
        h0(str, "path");
        h0(str2, "toPath");
        FormBody.Builder builderAddEncoded = new FormBody.Builder().addEncoded("path", str).addEncoded("topath", str2);
        if (!z) {
            builderAddEncoded.add("noover", String.valueOf(1));
        }
        return c0(e0().url(this.f.newBuilder().addPathSegment("copyfile").build()).post(builderAddEncoded.build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.p
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.P(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.v> d(String str, String str2, ax.Sa.m mVar, Date date, ax.Sa.r rVar, ax.Sa.y yVar) {
        g0(str);
        return G(null, str, str2, mVar, date, rVar, yVar);
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<Boolean> e(ax.Sa.u uVar) {
        if (uVar != null) {
            return uVar.l() ? I(uVar.j().c()) : H(uVar.o().d());
        }
        throw new IllegalArgumentException("RemoteEntry argument cannot be null.");
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<Boolean> f(String str) {
        g0(str);
        return c0(new Request.Builder().url(this.f.newBuilder().addPathSegment("deletefile").build()).get().post(new FormBody.Builder().addEncoded("path", str).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.s
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.T(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.v> g(String str, String str2) {
        h0(str, "path");
        h0(str2, "toPath");
        return c0(e0().url(this.f.newBuilder().addPathSegment("renamefile").build()).post(new FormBody.Builder().addEncoded("path", str).addEncoded("topath", str2).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.t
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.Z(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.A> h() {
        return c0(e0().url(this.f.newBuilder().addPathSegment("userinfo").build()).get().build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.q
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.W(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<Boolean> i(String str, boolean z) {
        g0(str);
        return c0(e0().url(this.f.newBuilder().addPathSegment(z ? "deletefolderrecursive" : "deletefolder").build()).post(new FormBody.Builder().addEncoded("path", str).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.i
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.V(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.w> j(String str) {
        return b0(str, false);
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.v> k(String str) {
        g0(str);
        return c0(e0().url(this.f.newBuilder().addPathSegment("stat").addEncodedQueryParameter("path", String.valueOf(str)).build()).get().build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.g
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.Y(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.w> l(String str) {
        g0(str);
        return c0(e0().url(this.f.newBuilder().addPathSegment("createfolder").build()).post(new FormBody.Builder().addEncoded("path", str).build()).build(), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.m
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.R(response);
            }
        });
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.Sa.p> m(String str, ax.Sa.n nVar) {
        g0(str);
        if (nVar != null) {
            return c0(d0(null, str, nVar), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.l
                @Override // ax.Ta.b
                public final Object a(Response response) {
                    return this.a.M(response);
                }
            });
        }
        throw new IllegalArgumentException("DownloadOptions parameter cannot be null.");
    }

    @Override // ax.Sa.InterfaceC1531a
    public ax.Sa.i<ax.p192ld.g> n(long j, ax.Sa.x xVar, boolean z) {
        i0(xVar);
        return c0(f0(Long.valueOf(j), ax.Ta.d.g(ax.Ta.c.Y, xVar), z), new ax.Ta.b() { // from class: com.pcloud.sdk.internal.n
            @Override // ax.Ta.b
            public final Object a(Response response) {
                return this.a.N(response);
            }
        });
    }
}
