package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0714e;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.J1.C0723d;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.V;
import ax.J1.b0;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.auth.BoxAuthentication;
import com.box.androidsdk.content.models.BoxBookmark;
import com.box.androidsdk.content.models.BoxDownload;
import com.box.androidsdk.content.models.BoxError;
import com.box.androidsdk.content.models.BoxFolder;
import com.box.androidsdk.content.models.BoxItem;
import com.box.androidsdk.content.models.BoxIteratorItems;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.models.BoxUser;
import com.box.androidsdk.content.requests.BoxRequestItemUpdate;
import com.box.androidsdk.content.requests.BoxRequestsFile$CopyFile;
import com.box.androidsdk.content.requests.BoxRequestsFile$DownloadFile;
import com.box.androidsdk.content.requests.BoxRequestsFile$DownloadThumbnail;
import com.box.androidsdk.content.requests.BoxRequestsFile$UploadFile;
import com.box.androidsdk.content.requests.BoxRequestsFolder$DeleteFolder;
import com.box.androidsdk.content.requests.BoxRequestsFolder$GetFolderItems;
import com.box.androidsdk.content.requests.BoxRequestsSearch$Search;
import com.box.androidsdk.content.requests.BoxResponse;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import okhttp3.HttpUrl;
import okhttp3.internal.http2.Http2;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5829c extends AbstractC5837k {
    private static final Logger q = Logger.getLogger("FileManager.BoxFileHelper");
    private static f r;
    private BoxSession h;
    private ax.p199o2.b i;
    private ax.p199o2.c j;
    private ax.p199o2.d k;
    private ax.p199o2.e l;
    private ConcurrentHashMap<String, C0723d> m = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> n = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> o = new ConcurrentHashMap<>();
    private final Object p = new Object();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$a */
    class a implements ax.o2.h.b<BoxDownload> {
        final /* synthetic */ ax.M3.b a;
        final /* synthetic */ g b;

        a(ax.M3.b bVar, g gVar) {
            this.a = bVar;
            this.b = gVar;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:14:0x0017
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1478)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        @Override // ax.o2.h.b
        public void a(com.box.androidsdk.content.requests.BoxResponse<com.box.androidsdk.content.models.BoxDownload> r2) {
            /*
                r1 = this;
                boolean r0 = r2.c()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L17
                if (r0 == 0) goto Le
                ax.M3.b r2 = r1.a     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L17
                r2.flush()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L17
                goto L17
            Lc:
                r2 = move-exception
                goto L1d
            Le:
                com.alphainventor.filemanager.file.c$g r0 = r1.b     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L17
                java.lang.Exception r2 = r2.a()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L17
                r0.l(r2)     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L17
            L17:
                ax.M3.b r2 = r1.a     // Catch: java.io.IOException -> L23
                r2.close()     // Catch: java.io.IOException -> L23
                goto L23
            L1d:
                ax.M3.b r0 = r1.a     // Catch: java.io.IOException -> L22
                r0.close()     // Catch: java.io.IOException -> L22
            L22:
                throw r2
            L23:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alphainventor.filemanager.file.C5829c.a.a(com.box.androidsdk.content.requests.BoxResponse):void");
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$b */
    class b implements ax.o2.h.b<BoxDownload> {
        final /* synthetic */ ax.M3.b a;
        final /* synthetic */ g b;

        b(ax.M3.b bVar, g gVar) {
            this.a = bVar;
            this.b = gVar;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:14:0x0033
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1478)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        @Override // ax.o2.h.b
        public void a(com.box.androidsdk.content.requests.BoxResponse<com.box.androidsdk.content.models.BoxDownload> r4) {
            /*
                r3 = this;
                java.util.logging.Logger r0 = com.alphainventor.filemanager.file.C5829c.M()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                r1.<init>()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                java.lang.String r2 = "download complete :"
                r1.append(r2)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                boolean r2 = r4.c()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                r1.append(r2)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                r0.fine(r1)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                boolean r0 = r4.c()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                if (r0 == 0) goto L2a
                ax.M3.b r4 = r3.a     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                r4.flush()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                goto L33
            L28:
                r4 = move-exception
                goto L39
            L2a:
                com.alphainventor.filemanager.file.c$g r0 = r3.b     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                java.lang.Exception r4 = r4.a()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
                r0.l(r4)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L33
            L33:
                ax.M3.b r4 = r3.a     // Catch: java.io.IOException -> L3f
                r4.close()     // Catch: java.io.IOException -> L3f
                goto L3f
            L39:
                ax.M3.b r0 = r3.a     // Catch: java.io.IOException -> L3e
                r0.close()     // Catch: java.io.IOException -> L3e
            L3e:
                throw r4
            L3f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alphainventor.filemanager.file.C5829c.b.a(com.box.androidsdk.content.requests.BoxResponse):void");
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$c, reason: collision with other inner class name */
    class C0503c implements ax.p213p2.b {
        final /* synthetic */ ax.P1.i a;
        final /* synthetic */ long b;
        final /* synthetic */ ax.p045d2.c c;
        final /* synthetic */ InputStream d;

        C0503c(ax.P1.i iVar, long j, ax.p045d2.c cVar, InputStream inputStream) {
            this.a = iVar;
            this.b = j;
            this.c = cVar;
            this.d = inputStream;
        }

        @Override // ax.p213p2.b
        public void a(long j, long j2) {
            ax.P1.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, this.b);
            }
            ax.p045d2.c cVar = this.c;
            if (cVar == null || !cVar.isCancelled()) {
                return;
            }
            try {
                this.d.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$d */
    class d implements BoxAuthentication.e {
        final /* synthetic */ AtomicBoolean X;
        final /* synthetic */ AtomicInteger Y;
        final /* synthetic */ InterfaceC5828b.a Z;
        final /* synthetic */ BoxSession q;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$d$a */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.Z.f0(true, null);
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$d$b */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.Z.f0(false, null);
            }
        }

        d(BoxSession boxSession, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, InterfaceC5828b.a aVar) {
            this.q = boxSession;
            this.X = atomicBoolean;
            this.Y = atomicInteger;
            this.Z = aVar;
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void e(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
            C5829c.q.fine("Box : onAuthFailure");
            if (!this.X.get()) {
                this.X.set(true);
                this.Y.set(2);
                C5829c.this.Y(new b());
            } else {
                int i = this.Y.get();
                ax.p221pa.c.h().g().d("Box onConnectResult Twice 2").h("called:" + i).i();
            }
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void f(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            C5829c.q.fine("Box : onRefreshed");
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void j(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            C5829c.this.a0(this.q);
            if (!this.X.get()) {
                this.X.set(true);
                this.Y.set(1);
                C5829c.this.Y(new a());
            } else {
                int i = this.Y.get();
                ax.p221pa.c.h().g().d("Box onConnectResult Twice 1").h("called:" + i).i();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$e */
    class e implements ax.o2.h.b<BoxSession> {
        final /* synthetic */ AtomicBoolean a;
        final /* synthetic */ AtomicInteger b;
        final /* synthetic */ InterfaceC5828b.a c;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$e$a */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e.this.c.f0(false, null);
            }
        }

        e(AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, InterfaceC5828b.a aVar) {
            this.a = atomicBoolean;
            this.b = atomicInteger;
            this.c = aVar;
        }

        @Override // ax.o2.h.b
        public void a(BoxResponse<BoxSession> boxResponse) {
            if (this.a.get()) {
                return;
            }
            this.a.set(true);
            this.b.set(3);
            if (C5829c.this.a()) {
                return;
            }
            C5829c.this.Y(new a());
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$f */
    public static class f extends N {
        Context a;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$f$a */
        class a implements BoxAuthentication.e {
            final /* synthetic */ ax.P1.j q;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$f$a$a, reason: collision with other inner class name */
            class RunnableC0504a implements Runnable {
                final /* synthetic */ int q;

                RunnableC0504a(int i) {
                    this.q = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.q.d(ax.p338y1.f.T0, this.q);
                }
            }

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$f$a$b */
            class b implements Runnable {
                b() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.q.a(ax.p338y1.f.T0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                }
            }

            a(ax.P1.j jVar) {
                this.q = jVar;
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
            public void e(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
                C5829c.q.fine("Box : onAuthFailure");
                new Handler(Looper.getMainLooper()).post(new b());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
            public void f(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
                C5829c.q.fine("Box : onRefreshed");
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
            public void j(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
                C5829c.q.fine("Box : onAuthCreated");
                String strN = boxAuthenticationInfo.Q().N();
                int iM = f.this.m();
                int iL = f.this.l(strN);
                if (iL >= 0) {
                    iM = iL;
                }
                f.this.o(iM, boxAuthenticationInfo);
                new Handler(Looper.getMainLooper()).post(new RunnableC0504a(iM));
            }
        }

        public f(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("BoxCloudPrefs", 0).edit().remove("accountid_" + i).remove("email_" + i).remove("display_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("BoxCloudPrefs", 0);
            String string = sharedPreferences.getString("email_" + i, null);
            ax.p338y1.f fVar = ax.p338y1.f.T0;
            return new ax.G1.p(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("BoxCloudPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("BoxCloudPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, ax.P1.j jVar) {
            jVar.b(ax.p338y1.f.T0);
            BoxSession boxSession = new BoxSession(aVar, null);
            boxSession.X(new a(jVar));
            boxSession.o(aVar);
        }

        int l(String str) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("BoxCloudPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.contains("accountid_" + i2) && !TextUtils.isEmpty(str)) {
                    if (str.equals(sharedPreferences.getString("accountid_" + i2, null))) {
                        return i2;
                    }
                }
            }
            return -1;
        }

        int m() {
            return this.a.getSharedPreferences("BoxCloudPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> n() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("BoxCloudPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("email_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void o(int i, BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            BoxUser boxUserQ = boxAuthenticationInfo.Q();
            String strO = boxUserQ.O();
            String strN = boxUserQ.N();
            String strP = boxUserQ.P();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("BoxCloudPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("accountid_" + i, strN).putString("display_name_" + i, strO).putString("email_" + i, strP).putString("location_name_" + i, ax.p338y1.f.T0.L(this.a));
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$g */
    protected static class g extends ax.M3.a {
        Throwable l0;

        protected g(int i) {
            super(i);
        }

        public void l(Throwable th) {
            this.l0 = th;
        }

        @Override // ax.M3.a, java.io.InputStream
        public synchronized int read() throws IOException {
            int i;
            try {
                if (this.l0 != null) {
                    throw new IOException("Error raised", this.l0);
                }
                i = super.read();
                if (this.l0 != null) {
                    throw new IOException("Error raised", this.l0);
                }
            } catch (Throwable th) {
                throw th;
            }
            return i;
        }
    }

    static {
        ax.p199o2.g.c = "dkzaej2ljaopvl4schd8ydbybyyvgver";
        ax.p199o2.g.d = "iLTZU0RjK5NEIvZJoUnCU8miPPOLs9kZ";
    }

    private C0718i P(String str, BoxException boxException) {
        BoxException.ErrorType errorTypeC = boxException.c();
        BoxError boxErrorB = boxException.b();
        String strJ = boxErrorB != null ? boxErrorB.J() : null;
        String strI = boxErrorB != null ? boxErrorB.I() : null;
        int iE = boxException.e();
        if (errorTypeC == BoxException.ErrorType.NETWORK_ERROR) {
            return new ax.I1.p(boxException);
        }
        if (errorTypeC == BoxException.ErrorType.ACCESS_DENIED) {
            return new C0713d(boxException);
        }
        if (iE == 404 && "not_found".equals(strI)) {
            return new ax.I1.s(boxException);
        }
        if (iE == 403 && "access_denied_insufficient_permissions".equals(strI)) {
            return new C0713d(str, boxException);
        }
        if (iE == 403 && "storage_limit_exceeded".equals(strI)) {
            return new ax.I1.r(str, boxException);
        }
        if (iE == 403 && "file_size_limit_exceeded".equals(strI)) {
            return new ax.I1.B(str, boxException);
        }
        if (iE == 409 && ("conflict".equals(strI) || "item_name_in_use".equals(strI))) {
            return new C0714e(false);
        }
        if ("Couldn't connect to the Box API due to a network error".equals(boxException.getMessage())) {
            return new ax.I1.p(boxException);
        }
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (strI != null) {
            str2 = HttpUrl.FRAGMENT_ENCODE_SET + " code:" + strI;
        }
        if (strJ != null) {
            str2 = str2 + " error:" + strJ;
        }
        if (iE != 0) {
            str2 = str2 + " responseCode:" + iE;
        }
        return C0712c.b(str + str2, boxException);
    }

    private void Q(AbstractC5838l abstractC5838l, boolean z) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        try {
            X(abstractC5838l.C());
            if (!abstractC5838l.isDirectory()) {
                this.i.d(abstractC5838l.t()).x();
                return;
            }
            BoxRequestsFolder$DeleteFolder boxRequestsFolder$DeleteFolderD = this.j.d(abstractC5838l.t());
            if (z) {
                boxRequestsFolder$DeleteFolderD.E(true);
            } else {
                boxRequestsFolder$DeleteFolderD.E(false);
            }
            boxRequestsFolder$DeleteFolderD.x();
        } catch (BoxException e2) {
            throw P("deleteFile", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BoxItem R(String str, String str2) throws C0718i {
        System.currentTimeMillis();
        int i = 0;
        while (true) {
            try {
                BoxRequestsFolder$GetFolderItems boxRequestsFolder$GetFolderItemsH = this.j.h(str);
                boxRequestsFolder$GetFolderItemsH.E(S());
                boxRequestsFolder$GetFolderItemsH.F(1000);
                if (i > 0) {
                    boxRequestsFolder$GetFolderItemsH.G(i);
                }
                BoxIteratorItems<BoxItem> boxIteratorItems = (BoxIteratorItems) boxRequestsFolder$GetFolderItemsH.x();
                for (BoxItem boxItem : boxIteratorItems) {
                    if (str2.equals(boxItem.S())) {
                        return boxItem;
                    }
                }
                int iLongValue = (int) (boxIteratorItems.P().longValue() + 1000);
                if (boxIteratorItems.I().longValue() < iLongValue) {
                    return null;
                }
                i = iLongValue;
            } catch (BoxException e2) {
                throw P("getChildItem", e2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private String[] S() {
        return new String[]{"name", "size", "modified_at", "content_modified_at", "item_status", "parent", "permissions"};
    }

    public static f T(Context context) {
        if (r == null) {
            r = new f(context.getApplicationContext());
        }
        return r;
    }

    private String U(C0723d c0723d) throws C0718i {
        String strD0 = c0723d.d0();
        if (strD0 != null) {
            return strD0;
        }
        C0723d c0723d2 = (C0723d) V0(c0723d.S());
        if (c0723d2.n()) {
            return c0723d2.t();
        }
        return null;
    }

    private String[] V() {
        return new String[]{"name", "size", "modified_at", "content_modified_at", "item_status", "parent", "path_collection", "permissions"};
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<BoxItem> W(String str, String[] strArr) throws BoxException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            BoxRequestsFolder$GetFolderItems boxRequestsFolder$GetFolderItemsH = this.j.h(str);
            boxRequestsFolder$GetFolderItemsH.E(strArr);
            boxRequestsFolder$GetFolderItemsH.F(1000);
            if (i > 0) {
                boxRequestsFolder$GetFolderItemsH.G(i);
            }
            BoxIteratorItems boxIteratorItems = (BoxIteratorItems) boxRequestsFolder$GetFolderItemsH.x();
            Iterator<E> it = boxIteratorItems.iterator();
            while (it.hasNext()) {
                arrayList.add((BoxItem) it.next());
            }
            int iLongValue = (int) (boxIteratorItems.P().longValue() + 1000);
            if (boxIteratorItems.I().longValue() < iLongValue) {
                return arrayList;
            }
            i = iLongValue;
        }
    }

    private void X(String str) {
        for (String str2 : this.m.keySet()) {
            if (str2.startsWith(str)) {
                this.m.remove(str2);
                String strRemove = this.o.remove(str2);
                if (strRemove != null) {
                    this.n.remove(strRemove);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    private void Z(C0723d c0723d) {
        this.m.put(c0723d.C(), c0723d);
        this.n.put(c0723d.t(), c0723d.C());
        this.o.put(c0723d.C(), c0723d.t());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0(BoxSession boxSession) {
        synchronized (this.p) {
            this.h = boxSession;
            this.i = new ax.p199o2.b(boxSession);
            this.j = new ax.p199o2.c(boxSession);
            this.k = new ax.p199o2.d(boxSession);
            this.l = new ax.p199o2.e(boxSession);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        if (!a()) {
            throw new C0716g("Box is not connected!");
        }
        if (this.m.containsKey(str)) {
            return this.m.get(str);
        }
        if (V.a.equals(str)) {
            return new C0723d(this, str);
        }
        C0723d c0723d = (C0723d) V0(V.r(str));
        String strH = V.h(str);
        if (!c0723d.n() || !c0723d.isDirectory()) {
            return new C0723d(this, str);
        }
        BoxItem boxItemR = R(c0723d.t(), strH);
        if (boxItemR == null) {
            return new C0723d(this, c0723d, str);
        }
        C0723d c0723d2 = new C0723d(this, c0723d.t(), boxItemR, str);
        if (c0723d2.isDirectory()) {
            Z(c0723d2);
        }
        return c0723d2;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) throws IOException {
        String strDecode;
        C0723d c0723d;
        if (str3 != null) {
            try {
                if (str3.startsWith("fileid=")) {
                    strDecode = Uri.decode(str3.substring(7));
                } else {
                    c0723d = (C0723d) V0(str2);
                    if (c0723d == null && !c0723d.n()) {
                        return null;
                    }
                    strDecode = c0723d.t();
                }
            } catch (C0718i unused) {
                return null;
            }
        } else {
            c0723d = (C0723d) V0(str2);
            if (c0723d == null) {
            }
            strDecode = c0723d.t();
        }
        g gVar = new g(Http2.INITIAL_MAX_FRAME_SIZE);
        ax.M3.b bVar = new ax.M3.b(gVar);
        BoxRequestsFile$DownloadThumbnail boxRequestsFile$DownloadThumbnailF = this.i.f(bVar, strDecode);
        boxRequestsFile$DownloadThumbnailF.O(128);
        ax.p199o2.h<E> hVarD = boxRequestsFile$DownloadThumbnailF.D();
        hVarD.a(new a(bVar, gVar));
        ax.p045d2.r.f(ax.d2.n.f.NORMAL).execute(hVarD);
        return gVar;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (!C0739u.F(abstractC5838l)) {
            return null;
        }
        return C0740v.S(abstractC5838l, "fileid=" + Uri.encode(abstractC5838l.t()));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        boolean z;
        synchronized (this.p) {
            z = this.h != null;
        }
        return z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        try {
            boolean z = !abstractC5838l.w().equals(abstractC5838l2.w());
            String strU = U((C0723d) abstractC5838l2);
            if (strU == null) {
                throw new C0718i("Target parent does not exist");
            }
            long jO = abstractC5838l.o();
            BoxRequestsFile$CopyFile boxRequestsFile$CopyFileC = this.i.c(abstractC5838l.t(), strU);
            if (z) {
                boxRequestsFile$CopyFileC.F(abstractC5838l2.w());
            }
            boxRequestsFile$CopyFileC.x();
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (BoxException e2) {
            throw P("copyFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        this.m.clear();
        this.o.clear();
        this.n.clear();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        Q(abstractC5838l, true);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l.n());
        try {
            String strU = U((C0723d) abstractC5838l);
            if (strU == null) {
                throw new C0718i("Target parent does not exist");
            }
            InputStream inputStreamB = a2.b();
            BoxRequestsFile$UploadFile boxRequestsFile$UploadFileN = this.i.n(inputStreamB, abstractC5838l.w(), strU);
            if (l != null && l.longValue() >= 0) {
                boxRequestsFile$UploadFileN.H(new Date(l.longValue()));
            }
            boxRequestsFile$UploadFileN.I(new C0503c(iVar, j, cVar, inputStreamB));
            boxRequestsFile$UploadFileN.x();
        } catch (BoxException e2) {
            throw P("writeFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        if (!a()) {
            throw new C0716g("Box is not connected!");
        }
        try {
            g gVar = new g(Http2.INITIAL_MAX_FRAME_SIZE);
            ax.M3.b bVar = new ax.M3.b(gVar);
            BoxRequestsFile$DownloadFile boxRequestsFile$DownloadFileE = this.i.e(bVar, abstractC5838l.t());
            if (j > 0) {
                boxRequestsFile$DownloadFileE.H(j, abstractC5838l.o());
            }
            ax.p199o2.h<E> hVarD = boxRequestsFile$DownloadFileE.D();
            hVarD.a(new b(bVar, gVar));
            ax.p045d2.r.f(ax.d2.n.f.NORMAL).execute(hVarD);
            return gVar;
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new C0718i(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        int iQ = q();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        SharedPreferences sharedPreferences = n().getSharedPreferences("BoxCloudPrefs", 0);
        String string = sharedPreferences.getString("accountid_" + iQ, null);
        String string2 = sharedPreferences.getString("email_" + iQ, null);
        BoxSession boxSession = new BoxSession(n(), string);
        boxSession.U(string2);
        boxSession.X(new d(boxSession, atomicBoolean, atomicInteger, aVar));
        boxSession.o(n()).a(new e(atomicBoolean, atomicInteger, aVar));
        aVar.N();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        if (this.j == null) {
            C0718i c0718i = new C0718i("FolderApi == null:connected = " + a() + ":" + (this.j == null));
            ax.p338y1.d.b(c0718i);
            throw c0718i;
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        try {
            ArrayList arrayList = new ArrayList();
            for (BoxItem boxItem : W(abstractC5838l.t(), S())) {
                if (!(boxItem instanceof BoxBookmark)) {
                    arrayList.add(new C0723d(this, abstractC5838l.t(), boxItem, V.M(abstractC5838l.C(), boxItem.S())));
                }
            }
            return arrayList;
        } catch (BoxException e2) {
            throw P("listChildren", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        try {
            String strS = abstractC5838l.S();
            String strW = abstractC5838l.w();
            C0723d c0723d = (C0723d) V0(strS);
            if (!c0723d.n()) {
                return false;
            }
            return true;
        } catch (C0718i | BoxException unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        return j(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        try {
            String strU = U((C0723d) abstractC5838l);
            BoxRequestsSearch$Search boxRequestsSearch$SearchC = this.k.c(str);
            boxRequestsSearch$SearchC.G(new String[]{BoxRequestsSearch$Search.h0});
            boxRequestsSearch$SearchC.F(new String[]{strU});
            boxRequestsSearch$SearchC.E(V());
            boxRequestsSearch$SearchC.I(200);
            BoxIteratorItems<BoxItem> boxIteratorItems = (BoxIteratorItems) boxRequestsSearch$SearchC.x();
            ArrayList arrayList = new ArrayList();
            for (BoxItem boxItem : boxIteratorItems) {
                String strM = "/";
                for (BoxFolder boxFolder : boxItem.U()) {
                    if (boxFolder.N() != "0") {
                        strM = V.M(strM, boxFolder.S());
                    }
                }
                arrayList.add(new C0723d(this, boxItem.T().N(), boxItem, V.M(strM, boxItem.S())));
            }
            if (hVar != null) {
                hVar.l0(arrayList, true);
            }
        } catch (BoxException e2) {
            throw P("search", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l2.n());
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        boolean z = !abstractC5838l.S().equals(abstractC5838l2.S());
        boolean z2 = !abstractC5838l.w().equals(abstractC5838l2.w());
        if (abstractC5838l.isDirectory()) {
            X(abstractC5838l.C());
        }
        try {
            BoxRequestItemUpdate boxRequestItemUpdateI = abstractC5838l.isDirectory() ? this.j.i(abstractC5838l.t()) : this.i.m(abstractC5838l.t());
            if (z) {
                boxRequestItemUpdateI.G(U((C0723d) abstractC5838l2));
            }
            if (z2) {
                boxRequestItemUpdateI.F(abstractC5838l2.w());
            }
            boxRequestItemUpdateI.x();
        } catch (BoxException e2) {
            throw P("moveFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        Q(abstractC5838l, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        try {
            BoxUser boxUser = (BoxUser) this.l.d().x();
            if (boxUser.Q() == null || boxUser.S() == null) {
                return null;
            }
            return new b0(boxUser.Q().longValue(), boxUser.S().longValue());
        } catch (BoxException e2) {
            if (this.h.J() == null || this.h.J().Q() == null || this.h.J().S() == null) {
                throw P("getStorageSpace", e2);
            }
            return new b0(this.h.J().Q().longValue(), this.h.J().S().longValue());
        }
    }
}
