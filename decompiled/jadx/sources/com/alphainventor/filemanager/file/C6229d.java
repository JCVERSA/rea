package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C1997c;
import ax.p086f3.C2001g;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
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
import com.box.androidsdk.content.requests.BoxRequestUpload;
import com.box.androidsdk.content.requests.BoxRequestsFile$CopyFile;
import com.box.androidsdk.content.requests.BoxRequestsFile$DownloadFile;
import com.box.androidsdk.content.requests.BoxRequestsFile$DownloadThumbnail;
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

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6229d extends AbstractC6238m {
    private static final Logger q = Logger.getLogger("FileManager.BoxFileHelper");
    private static f r;
    private BoxSession h;
    private ax.K3.b i;
    private ax.K3.c j;
    private ax.K3.d k;
    private ax.K3.e l;
    private ConcurrentHashMap<String, C1997c> m = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> n = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> o = new ConcurrentHashMap<>();
    private final Object p = new Object();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$a */
    class a implements ax.K3.h.b<BoxDownload> {
        final /* synthetic */ ax.p152j5.b a;
        final /* synthetic */ g b;

        a(ax.p152j5.b bVar, g gVar) {
            this.a = bVar;
            this.b = gVar;
        }

        @Override // ax.K3.h.b
        public void a(BoxResponse<BoxDownload> boxResponse) {
            try {
                try {
                    if (boxResponse.c()) {
                        this.a.flush();
                    } else {
                        this.b.r(boxResponse.a());
                    }
                    this.a.close();
                } catch (IOException unused) {
                }
            } catch (IOException unused2) {
                this.a.close();
            } catch (Throwable th) {
                try {
                    this.a.close();
                } catch (IOException unused3) {
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$b */
    class b implements ax.K3.h.b<BoxDownload> {
        final /* synthetic */ ax.p152j5.b a;
        final /* synthetic */ g b;

        b(ax.p152j5.b bVar, g gVar) {
            this.a = bVar;
            this.b = gVar;
        }

        @Override // ax.K3.h.b
        public void a(BoxResponse<BoxDownload> boxResponse) {
            try {
                try {
                    C6229d.q.fine("download complete :" + boxResponse.c());
                    if (boxResponse.c()) {
                        this.a.flush();
                    } else {
                        this.b.r(boxResponse.a());
                    }
                    this.a.close();
                } catch (IOException unused) {
                }
            } catch (IOException unused2) {
                this.a.close();
            } catch (Throwable th) {
                try {
                    this.a.close();
                } catch (IOException unused3) {
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$c */
    class c implements ax.L3.b {
        final /* synthetic */ ax.p182l3.i a;
        final /* synthetic */ long b;
        final /* synthetic */ InterfaceC2462c c;
        final /* synthetic */ InputStream d;

        c(ax.p182l3.i iVar, long j, InterfaceC2462c interfaceC2462c, InputStream inputStream) {
            this.a = iVar;
            this.b = j;
            this.c = interfaceC2462c;
            this.d = inputStream;
        }

        @Override // ax.L3.b
        public void a(long j, long j2) {
            ax.p182l3.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, this.b);
            }
            InterfaceC2462c interfaceC2462c = this.c;
            if (interfaceC2462c == null || !interfaceC2462c.isCancelled()) {
                return;
            }
            try {
                this.d.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$d, reason: collision with other inner class name */
    class C0539d implements BoxAuthentication.e {
        final /* synthetic */ AtomicBoolean X;
        final /* synthetic */ AtomicInteger Y;
        final /* synthetic */ InterfaceC6228c.a Z;
        final /* synthetic */ BoxSession q;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$d$a */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C0539d.this.Z.d0(true, null);
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$d$b */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C0539d.this.Z.d0(false, null);
            }
        }

        C0539d(BoxSession boxSession, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, InterfaceC6228c.a aVar) {
            this.q = boxSession;
            this.X = atomicBoolean;
            this.Y = atomicInteger;
            this.Z = aVar;
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void e(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
            C6229d.q.fine("Box : onAuthFailure");
            if (!this.X.get()) {
                this.X.set(true);
                this.Y.set(2);
                C6229d.this.N(new b());
            } else {
                int i = this.Y.get();
                ax.p302sb.c.h().g().d("Box onConnectResult Twice 2").h("called:" + i).i();
            }
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void f(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            C6229d.q.fine("Box : onRefreshed");
        }

        @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
        public void g(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
            C6229d.this.g0(this.q);
            if (!this.X.get()) {
                this.X.set(true);
                this.Y.set(1);
                C6229d.this.N(new a());
            } else {
                int i = this.Y.get();
                ax.p302sb.c.h().g().d("Box onConnectResult Twice 1").h("called:" + i).i();
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$e */
    class e implements ax.K3.h.b<BoxSession> {
        final /* synthetic */ AtomicBoolean a;
        final /* synthetic */ AtomicInteger b;
        final /* synthetic */ InterfaceC6228c.a c;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$e$a */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e.this.c.d0(false, null);
            }
        }

        e(AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, InterfaceC6228c.a aVar) {
            this.a = atomicBoolean;
            this.b = atomicInteger;
            this.c = aVar;
        }

        @Override // ax.K3.h.b
        public void a(BoxResponse<BoxSession> boxResponse) {
            if (this.a.get()) {
                return;
            }
            this.a.set(true);
            this.b.set(3);
            if (C6229d.this.a()) {
                return;
            }
            C6229d.this.N(new a());
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$f */
    public static class f extends T {
        Context a;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$f$a */
        class a implements BoxAuthentication.e {
            final /* synthetic */ ax.p182l3.k q;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$f$a$a, reason: collision with other inner class name */
            class RunnableC0540a implements Runnable {
                final /* synthetic */ int q;

                RunnableC0540a(int i) {
                    this.q = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.q.d(ax.U2.f.X0, this.q);
                }
            }

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$f$a$b */
            class b implements Runnable {
                b() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.q.c(ax.U2.f.X0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                }
            }

            a(ax.p182l3.k kVar) {
                this.q = kVar;
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
            public void e(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo, Exception exc) {
                C6229d.q.fine("Box : onAuthFailure");
                new Handler(Looper.getMainLooper()).post(new b());
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
            public void f(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
                C6229d.q.fine("Box : onRefreshed");
            }

            @Override // com.box.androidsdk.content.auth.BoxAuthentication.e
            public void g(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo) {
                C6229d.q.fine("Box : onAuthCreated");
                String strJ = boxAuthenticationInfo.P().J();
                int iM = f.this.m();
                int iL = f.this.l(strJ);
                if (iL >= 0) {
                    iM = iL;
                }
                f.this.o(iM, boxAuthenticationInfo);
                new Handler(Looper.getMainLooper()).post(new RunnableC0540a(iM));
            }
        }

        public f(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("BoxCloudPrefs", 0).edit().remove("accountid_" + i).remove("email_" + i).remove("display_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("BoxCloudPrefs", 0);
            String string = sharedPreferences.getString("email_" + i, null);
            ax.U2.f fVar = ax.U2.f.X0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("BoxCloudPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("BoxCloudPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, ax.p182l3.k kVar) {
            kVar.a(ax.U2.f.X0);
            C6229d.c0(aVar);
            BoxSession boxSession = new BoxSession(aVar, null);
            boxSession.V(new a(kVar));
            boxSession.m(aVar);
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

        public List<ax.p038c3.s> n() {
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
            BoxUser boxUserP = boxAuthenticationInfo.P();
            String strK = boxUserP.K();
            String strJ = boxUserP.J();
            String strM = boxUserP.M();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("BoxCloudPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("accountid_" + i, strJ).putString("display_name_" + i, strK).putString("email_" + i, strM).putString("location_name_" + i, ax.U2.f.X0.L(this.a));
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

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.d$g */
    protected static class g extends ax.p152j5.a {
        Throwable o0;

        protected g(int i) {
            super(i);
        }

        public void r(Throwable th) {
            this.o0 = th;
        }

        @Override // ax.p152j5.a, java.io.InputStream
        public synchronized int read() throws IOException {
            int i;
            try {
                if (this.o0 != null) {
                    throw new IOException("Error raised", this.o0);
                }
                i = super.read();
                if (this.o0 != null) {
                    throw new IOException("Error raised", this.o0);
                }
            } catch (Throwable th) {
                throw th;
            }
            return i;
        }
    }

    private C1966j V(String str, BoxException boxException) {
        BoxException.ErrorType errorTypeC = boxException.c();
        BoxError boxErrorB = boxException.b();
        String strE = boxErrorB != null ? boxErrorB.E() : null;
        String strD = boxErrorB != null ? boxErrorB.D() : null;
        int iE = boxException.e();
        if (errorTypeC == BoxException.ErrorType.NETWORK_ERROR) {
            return new ax.p070e3.q(boxException);
        }
        if (errorTypeC == BoxException.ErrorType.ACCESS_DENIED) {
            return new C1961e(boxException);
        }
        if (iE == 404 && "not_found".equals(strD)) {
            return new ax.p070e3.t(boxException);
        }
        if (iE == 403 && "access_denied_insufficient_permissions".equals(strD)) {
            return new C1961e(str, boxException);
        }
        if (iE == 403 && "storage_limit_exceeded".equals(strD)) {
            return new ax.p070e3.s(str, boxException);
        }
        if (iE == 403 && "file_size_limit_exceeded".equals(strD)) {
            return new ax.p070e3.D(str, boxException);
        }
        if (iE == 409 && ("conflict".equals(strD) || "item_name_in_use".equals(strD))) {
            return new C1962f(false);
        }
        if ("Couldn't connect to the Box API due to a network error".equals(boxException.getMessage())) {
            return new ax.p070e3.q(boxException);
        }
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (strD != null) {
            str2 = HttpUrl.FRAGMENT_ENCODE_SET + " code:" + strD;
        }
        if (strE != null) {
            str2 = str2 + " error:" + strE;
        }
        if (iE != 0) {
            str2 = str2 + " responseCode:" + iE;
        }
        return C1960d.b(str + str2, boxException);
    }

    private void W(AbstractC6239n abstractC6239n, boolean z) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        try {
            e0(abstractC6239n.E());
            if (!abstractC6239n.isDirectory()) {
                this.i.d(abstractC6239n.u()).x();
                return;
            }
            BoxRequestsFolder$DeleteFolder boxRequestsFolder$DeleteFolderD = this.j.d(abstractC6239n.u());
            if (z) {
                boxRequestsFolder$DeleteFolderD.E(true);
            } else {
                boxRequestsFolder$DeleteFolderD.E(false);
            }
            boxRequestsFolder$DeleteFolderD.x();
        } catch (BoxException e2) {
            throw V("deleteFile", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BoxItem X(String str, String str2) throws C1966j {
        System.currentTimeMillis();
        int i = 0;
        while (true) {
            try {
                BoxRequestsFolder$GetFolderItems boxRequestsFolder$GetFolderItemsH = this.j.h(str);
                boxRequestsFolder$GetFolderItemsH.E(Y());
                boxRequestsFolder$GetFolderItemsH.F(1000);
                if (i > 0) {
                    boxRequestsFolder$GetFolderItemsH.G(i);
                }
                BoxIteratorItems<BoxItem> boxIteratorItems = (BoxIteratorItems) boxRequestsFolder$GetFolderItemsH.x();
                for (BoxItem boxItem : boxIteratorItems) {
                    if (str2.equals(boxItem.Q())) {
                        return boxItem;
                    }
                }
                int iLongValue = (int) (boxIteratorItems.M().longValue() + 1000);
                if (boxIteratorItems.D().longValue() < iLongValue) {
                    return null;
                }
                i = iLongValue;
            } catch (BoxException e2) {
                throw V("getChildItem", e2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private String[] Y() {
        return new String[]{"name", "size", "modified_at", "content_modified_at", "item_status", "parent", "permissions"};
    }

    public static f Z(Context context) {
        if (r == null) {
            r = new f(context.getApplicationContext());
        }
        return r;
    }

    private String a0(C1997c c1997c) throws C1966j {
        String strH0 = c1997c.h0();
        if (strH0 != null) {
            return strH0;
        }
        C1997c c1997c2 = (C1997c) a1(c1997c.U());
        if (c1997c2.o()) {
            return c1997c2.u();
        }
        return null;
    }

    private String[] b0() {
        return new String[]{"name", "size", "modified_at", "content_modified_at", "item_status", "parent", "path_collection", "permissions"};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c0(Context context) {
        C2001g c2001gC = C2001g.c(context);
        ax.U2.f fVar = ax.U2.f.X0;
        ax.K3.g.c = c2001gC.a(fVar);
        ax.K3.g.d = C2001g.c(context).b(fVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<BoxItem> d0(String str, String[] strArr) throws BoxException {
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
            int iLongValue = (int) (boxIteratorItems.M().longValue() + 1000);
            if (boxIteratorItems.D().longValue() < iLongValue) {
                return arrayList;
            }
            i = iLongValue;
        }
    }

    private void e0(String str) {
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

    private void f0(C1997c c1997c) {
        this.m.put(c1997c.E(), c1997c);
        this.n.put(c1997c.u(), c1997c.E());
        this.o.put(c1997c.E(), c1997c.u());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g0(BoxSession boxSession) {
        synchronized (this.p) {
            this.h = boxSession;
            this.i = new ax.K3.b(boxSession);
            this.j = new ax.K3.c(boxSession);
            this.k = new ax.K3.d(boxSession);
            this.l = new ax.K3.e(boxSession);
        }
    }

    private void h0(AbstractC6239n abstractC6239n, ax.p086f3.G g2, long j, Long l, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        BoxException boxException;
        BoxRequestUpload boxRequestUploadO;
        try {
            String strA0 = a0((C1997c) abstractC6239n);
            try {
                if (strA0 == null) {
                    throw new C1966j("Target parent does not exist");
                }
                InputStream inputStreamB = g2.b();
                if (z) {
                    try {
                        boxRequestUploadO = ((C1997c) abstractC6239n).g0() != null ? this.i.o(inputStreamB, abstractC6239n.u()) : this.i.p(inputStreamB, abstractC6239n.B(), strA0);
                    } catch (BoxException e2) {
                        boxException = e2;
                    }
                } else {
                    boxRequestUploadO = this.i.p(inputStreamB, abstractC6239n.B(), strA0);
                }
                BoxRequestUpload boxRequestUpload = boxRequestUploadO;
                if (l != null && l.longValue() >= 0) {
                    boxRequestUpload.H(new Date(l.longValue()));
                }
                boxRequestUpload.I(new c(iVar, j, interfaceC2462c, inputStreamB));
                boxRequestUpload.x();
                return;
            } catch (BoxException e3) {
                e = e3;
                boxException = e;
            }
        } catch (BoxException e4) {
            e = e4;
        }
        boxException = e;
        throw V("writeFile", boxException);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean O() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean P() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        h0(abstractC6239n, g2, j, l, true, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        boolean z;
        synchronized (this.p) {
            z = this.h != null;
        }
        return z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        if (!a()) {
            throw new C1964h("Box is not connected!");
        }
        if (this.m.containsKey(str)) {
            return this.m.get(str);
        }
        if (d0.a.equals(str)) {
            return new C1997c(this, str);
        }
        C1997c c1997c = (C1997c) a1(d0.r(str));
        String strH = d0.h(str);
        if (!c1997c.o() || !c1997c.isDirectory()) {
            return new C1997c(this, str);
        }
        BoxItem boxItemX = X(c1997c.u(), strH);
        if (boxItemX == null) {
            return new C1997c(this, c1997c, str);
        }
        C1997c c1997c2 = new C1997c(this, c1997c.u(), boxItemX, str);
        if (c1997c2.isDirectory()) {
            f0(c1997c2);
        }
        return c1997c2;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        this.m.clear();
        this.o.clear();
        this.n.clear();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) throws IOException {
        String strDecode;
        C1997c c1997c;
        if (str3 != null) {
            try {
                if (str3.startsWith("fileid=")) {
                    strDecode = Uri.decode(str3.substring(7));
                } else {
                    c1997c = (C1997c) a1(str2);
                    if (c1997c == null && !c1997c.o()) {
                        return null;
                    }
                    strDecode = c1997c.u();
                }
            } catch (C1966j unused) {
                return null;
            }
        } else {
            c1997c = (C1997c) a1(str2);
            if (c1997c == null) {
            }
            strDecode = c1997c.u();
        }
        g gVar = new g(Http2.INITIAL_MAX_FRAME_SIZE);
        ax.p152j5.b bVar = new ax.p152j5.b(gVar);
        BoxRequestsFile$DownloadThumbnail boxRequestsFile$DownloadThumbnailF = this.i.f(bVar, strDecode);
        boxRequestsFile$DownloadThumbnailF.O(128);
        ax.K3.h<E> hVarD = boxRequestsFile$DownloadThumbnailF.D();
        hVarD.a(new a(bVar, gVar));
        ax.p406z3.v.f(ax.z3.q.e.NORMAL).execute(hVarD);
        return gVar;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        if (this.j == null) {
            C1966j c1966j = new C1966j("FolderApi == null:connected = " + a() + ":" + (this.j == null));
            ax.U2.d.b(c1966j);
            throw c1966j;
        }
        C2461b.c(abstractC6239n.isDirectory());
        try {
            ArrayList arrayList = new ArrayList();
            for (BoxItem boxItem : d0(abstractC6239n.u(), Y())) {
                if (!(boxItem instanceof BoxBookmark)) {
                    arrayList.add(new C1997c(this, abstractC6239n.u(), boxItem, d0.Q(abstractC6239n.E(), boxItem.Q())));
                }
            }
            return arrayList;
        } catch (BoxException e2) {
            throw V("listChildren", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (!ax.p086f3.A.J(abstractC6239n)) {
            return null;
        }
        return ax.p086f3.B.a0(abstractC6239n, "fileid=" + Uri.encode(abstractC6239n.u()));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        W(abstractC6239n, true);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        if (!a()) {
            throw new C1964h("Box is not connected!");
        }
        try {
            g gVar = new g(Http2.INITIAL_MAX_FRAME_SIZE);
            ax.p152j5.b bVar = new ax.p152j5.b(gVar);
            BoxRequestsFile$DownloadFile boxRequestsFile$DownloadFileE = this.i.e(bVar, abstractC6239n.u());
            if (j > 0) {
                boxRequestsFile$DownloadFileE.H(j, abstractC6239n.p());
            }
            ax.K3.h<E> hVarD = boxRequestsFile$DownloadFileE.D();
            hVarD.a(new b(bVar, gVar));
            ax.p406z3.v.f(ax.z3.q.e.NORMAL).execute(hVarD);
            return gVar;
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new C1966j(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        boolean zEquals = abstractC6239n.U().equals(abstractC6239n2.U());
        boolean zEquals2 = abstractC6239n.B().equals(abstractC6239n2.B());
        if (abstractC6239n.isDirectory()) {
            e0(abstractC6239n.E());
        }
        try {
            BoxRequestItemUpdate boxRequestItemUpdateI = abstractC6239n.isDirectory() ? this.j.i(abstractC6239n.u()) : this.i.n(abstractC6239n.u());
            if (!zEquals) {
                boxRequestItemUpdateI.G(a0((C1997c) abstractC6239n2));
            }
            if (!zEquals2) {
                boxRequestItemUpdateI.F(abstractC6239n2.B());
            }
            boxRequestItemUpdateI.x();
        } catch (BoxException e2) {
            throw V("moveFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        int iT = t();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        SharedPreferences sharedPreferences = p().getSharedPreferences("BoxCloudPrefs", 0);
        String string = sharedPreferences.getString("accountid_" + iT, null);
        String string2 = sharedPreferences.getString("email_" + iT, null);
        c0(activity);
        try {
            BoxSession boxSession = new BoxSession(p(), string);
            boxSession.S(string2);
            boxSession.V(new C0539d(boxSession, atomicBoolean, atomicInteger, aVar));
            boxSession.m(p()).a(new e(atomicBoolean, atomicInteger, aVar));
            aVar.J();
        } catch (RuntimeException e2) {
            ax.U2.d.c("box init", e2);
            aVar.d0(false, null);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n.o());
        h0(abstractC6239n, g2, j, l, false, interfaceC2462c, iVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        if (!z2) {
            o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
            return;
        }
        try {
            String strA0 = a0((C1997c) abstractC6239n);
            BoxRequestsSearch$Search boxRequestsSearch$SearchC = this.k.c(str);
            boxRequestsSearch$SearchC.G(new String[]{BoxRequestsSearch$Search.k0});
            boxRequestsSearch$SearchC.F(new String[]{strA0});
            boxRequestsSearch$SearchC.E(b0());
            boxRequestsSearch$SearchC.I(200);
            BoxIteratorItems<BoxItem> boxIteratorItems = (BoxIteratorItems) boxRequestsSearch$SearchC.x();
            ArrayList arrayList = new ArrayList();
            for (BoxItem boxItem : boxIteratorItems) {
                String strQ = "/";
                for (BoxFolder boxFolder : boxItem.S()) {
                    if (boxFolder.J() != "0") {
                        strQ = d0.Q(strQ, boxFolder.Q());
                    }
                }
                arrayList.add(new C1997c(this, boxItem.R().J(), boxItem, d0.Q(strQ, boxItem.Q())));
            }
            if (hVar != null) {
                hVar.i0(arrayList, true);
            }
        } catch (BoxException e2) {
            throw V("search", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        try {
            boolean zEquals = abstractC6239n.B().equals(abstractC6239n2.B());
            String strA0 = a0((C1997c) abstractC6239n2);
            if (strA0 == null) {
                throw new C1966j("Target parent does not exist");
            }
            long jP = abstractC6239n.p();
            BoxRequestsFile$CopyFile boxRequestsFile$CopyFileC = this.i.c(abstractC6239n.u(), strA0);
            if (!zEquals) {
                boxRequestsFile$CopyFileC.F(abstractC6239n2.B());
            }
            boxRequestsFile$CopyFileC.x();
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (BoxException e2) {
            throw V("copyFile", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        try {
            String strU = abstractC6239n.U();
            String strB = abstractC6239n.B();
            C1997c c1997c = (C1997c) a1(strU);
            if (!c1997c.o()) {
                return false;
            }
            return true;
        } catch (C1966j | BoxException unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return l(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        W(abstractC6239n, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        try {
            BoxUser boxUser = (BoxUser) this.l.d().x();
            if (boxUser.P() == null || boxUser.Q() == null) {
                return null;
            }
            return new k0(boxUser.P().longValue(), boxUser.Q().longValue());
        } catch (BoxException e2) {
            if (this.h.E() == null || this.h.E().P() == null || this.h.E().Q() == null) {
                throw V("getStorageSpace", e2);
            }
            return new k0(this.h.E().P().longValue(), this.h.E().Q().longValue());
        }
    }
}
