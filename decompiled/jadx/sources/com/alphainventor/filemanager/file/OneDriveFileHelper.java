package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0714e;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.U;
import ax.J1.V;
import ax.J1.b0;
import ax.p290u9.InterfaceC5726b0;
import ax.p290u9.InterfaceC5732e0;
import ax.p290u9.W;
import ax.p290u9.X;
import ax.p290u9.Y;
import com.microsoft.graph.extensions.Drive;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.DriveItemUploadableProperties;
import com.microsoft.graph.extensions.FileSystemInfo;
import com.microsoft.graph.extensions.Folder;
import com.microsoft.graph.extensions.IDriveItemCollectionPage;
import com.microsoft.graph.extensions.IDriveItemSearchCollectionPage;
import com.microsoft.graph.extensions.ItemReference;
import com.microsoft.graph.extensions.Quota;
import com.microsoft.graph.extensions.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class OneDriveFileHelper extends AbstractC5837k {
    static j m;
    private final AtomicReference<InterfaceC5732e0> h = new AtomicReference<>();
    private final Map<String, String> i = new HashMap();
    private final Map<String, String> j = new HashMap();
    private Drive k;
    private static final Logger l = Logger.getLogger("FileManager.OneDriveFileHelper");
    static int n = 1048576;
    static int o = 5242880;

    public static class MyDriveItem extends DriveItem {

        @ax.p163l8.c("@microsoft.graph.conflictBehavior")
        @ax.p163l8.a
        public String U;
    }

    public static class MyDriveItemUploadableProperties extends DriveItemUploadableProperties {

        @ax.p163l8.c("@microsoft.graph.conflictBehavior")
        @ax.p163l8.a
        public String h;
    }

    class b implements ax.p262s9.c<InterfaceC5732e0> {
        final /* synthetic */ Fragment a;
        final /* synthetic */ InterfaceC5828b.a b;
        final /* synthetic */ AtomicBoolean c;
        final /* synthetic */ AtomicInteger d;

        class a implements ax.p262s9.c<Drive> {
            final /* synthetic */ InterfaceC5732e0 a;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$b$a$a, reason: collision with other inner class name */
            class RunnableC0495a implements Runnable {
                RunnableC0495a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (!b.this.c.get()) {
                        b.this.c.set(true);
                        b.this.d.set(1);
                        b.this.b.f0(true, null);
                    } else {
                        int i = b.this.d.get();
                        ax.p221pa.c.h().g().d("OneDrive onConnectResult Twice 1").h("called:" + i).i();
                    }
                }
            }

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$b$a$b, reason: collision with other inner class name */
            class RunnableC0496b implements Runnable {
                final /* synthetic */ ax.p276t9.d q;

                RunnableC0496b(ax.p276t9.d dVar) {
                    this.q = dVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (!b.this.c.get()) {
                        b.this.c.set(true);
                        b.this.d.set(2);
                        b.this.b.f0(false, null);
                    } else {
                        int i = b.this.d.get();
                        ax.p221pa.c.h().g().d("OneDrive onConnectResult Twice 2").h("called:" + i).i();
                    }
                }
            }

            a(InterfaceC5732e0 interfaceC5732e0) {
                this.a = interfaceC5732e0;
            }

            @Override // ax.p262s9.c
            public void c(ax.p276t9.d dVar) {
                ax.p045d2.x.a0(new RunnableC0496b(dVar));
            }

            @Override // ax.p262s9.c
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(Drive drive) {
                OneDriveFileHelper.this.k = drive;
                b bVar = b.this;
                OneDriveFileHelper.this.x0(this.a, (ax.K1.L) bVar.a);
                if (b.this.b != null) {
                    ax.p045d2.x.a0(new RunnableC0495a());
                }
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$b$b, reason: collision with other inner class name */
        class RunnableC0497b implements Runnable {
            RunnableC0497b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!b.this.c.get()) {
                    b.this.c.set(true);
                    b.this.d.set(2);
                    b.this.b.f0(false, null);
                } else {
                    int i = b.this.d.get();
                    ax.p221pa.c.h().g().d("OneDrive onConnectResult Twice 3").h("called:" + i).i();
                }
            }
        }

        b(Fragment fragment, InterfaceC5828b.a aVar, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger) {
            this.a = fragment;
            this.b = aVar;
            this.c = atomicBoolean;
            this.d = atomicInteger;
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
            if (this.b != null) {
                ax.p045d2.x.a0(new RunnableC0497b());
            }
        }

        @Override // ax.p262s9.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(InterfaceC5732e0 interfaceC5732e0) {
            OneDriveFileHelper.this.h.set(interfaceC5732e0);
            OneDriveFileHelper.this.A0(interfaceC5732e0);
            interfaceC5732e0.d().l().a().c(new a(interfaceC5732e0));
        }
    }

    class d implements ax.p262s9.e<DriveItem> {
        final /* synthetic */ ax.P1.i a;
        final /* synthetic */ ax.p262s9.f b;
        final /* synthetic */ AtomicBoolean c;
        final /* synthetic */ AtomicReference d;

        d(ax.P1.i iVar, ax.p262s9.f fVar, AtomicBoolean atomicBoolean, AtomicReference atomicReference) {
            this.a = iVar;
            this.b = fVar;
            this.c = atomicBoolean;
            this.d = atomicReference;
        }

        @Override // ax.p262s9.e
        public void a(long j, long j2) {
            ax.P1.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, j2);
            }
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
            this.c.set(false);
            this.d.set(dVar);
            this.b.a();
        }

        @Override // ax.p262s9.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(DriveItem driveItem) {
            this.b.a();
            this.c.set(true);
        }
    }

    class e extends ax.p318w9.b {
        e(String str, ax.p276t9.f fVar, List list, Class cls) {
            super(str, fVar, list, cls);
        }
    }

    class f implements ax.p262s9.e<DriveItem> {
        final /* synthetic */ ax.P1.i a;
        final /* synthetic */ AtomicReference b;

        f(ax.P1.i iVar, AtomicReference atomicReference) {
            this.a = iVar;
            this.b = atomicReference;
        }

        @Override // ax.p262s9.e
        public void a(long j, long j2) {
            ax.P1.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, j2);
            }
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
            this.b.set(dVar);
        }

        @Override // ax.p262s9.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(DriveItem driveItem) {
        }
    }

    class g extends ax.p248r9.b {
        g(Context context) {
            super(context);
        }

        @Override // ax.p248r9.b
        public String e() {
            return "a473d63f-1acf-473f-bd2a-181957d163e0";
        }

        @Override // ax.p248r9.b
        public String[] g() {
            return new String[]{"offline_access", "Files.ReadWrite.All", "User.Read"};
        }
    }

    class h implements ax.p262s9.c<Void> {
        final /* synthetic */ ax.p262s9.c a;
        final /* synthetic */ InterfaceC5732e0 b;
        final /* synthetic */ Context c;
        final /* synthetic */ Activity d;
        final /* synthetic */ String e;

        class a implements ax.p262s9.c<Void> {
            final /* synthetic */ InterfaceC5732e0 a;

            a(InterfaceC5732e0 interfaceC5732e0) {
                this.a = interfaceC5732e0;
            }

            @Override // ax.p262s9.c
            public void c(ax.p276t9.d dVar) {
                h.this.a.c(dVar);
            }

            @Override // ax.p262s9.c
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(Void r2) {
                h.this.a.b(this.a);
            }
        }

        h(ax.p262s9.c cVar, InterfaceC5732e0 interfaceC5732e0, Context context, Activity activity, String str) {
            this.a = cVar;
            this.b = interfaceC5732e0;
            this.c = context;
            this.d = activity;
            this.e = str;
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
            OneDriveFileHelper.z0(this.c);
            InterfaceC5732e0 interfaceC5732e0X = OneDriveFileHelper.X(this.c);
            ax.p248r9.b bVar = (ax.p248r9.b) interfaceC5732e0X.c();
            Activity activity = this.d;
            if (activity == null) {
                this.a.c(null);
            } else {
                bVar.i(activity, this.e, new a(interfaceC5732e0X));
            }
        }

        @Override // ax.p262s9.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Void r2) {
            this.a.b(this.b);
        }
    }

    public static class j extends N {
        private Context a;
        U b = new U(ax.p338y1.f.R0);

        class a implements ax.p262s9.c<InterfaceC5732e0> {
            final /* synthetic */ ax.P1.j a;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$j$a$a, reason: collision with other inner class name */
            class RunnableC0498a implements Runnable {
                final /* synthetic */ int q;

                RunnableC0498a(int i) {
                    this.q = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.a.d(ax.p338y1.f.R0, this.q);
                }
            }

            class b implements Runnable {
                b() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.a.a(ax.p338y1.f.R0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                }
            }

            a(ax.P1.j jVar) {
                this.a = jVar;
            }

            @Override // ax.p262s9.c
            public void c(ax.p276t9.d dVar) {
                if (this.a != null) {
                    ax.p045d2.x.a0(new b());
                }
            }

            @Override // ax.p262s9.c
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(InterfaceC5732e0 interfaceC5732e0) {
                String strF = ((ax.p248r9.b) interfaceC5732e0.c()).f();
                int iL = j.this.l();
                j.this.n(iL, strF, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET);
                if (this.a != null) {
                    ax.p045d2.x.a0(new RunnableC0498a(iL));
                }
            }
        }

        public j(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("OneDrivePrefs", 0).edit().remove("version_" + i).remove("refresh_token_" + i).remove("display_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).remove("email_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("OneDrivePrefs", 0);
            String string = sharedPreferences.getString("display_name_" + i, null);
            String string2 = sharedPreferences.getString("email_" + i, null);
            String str = TextUtils.isEmpty(string2) ? string : string2;
            ax.p338y1.f fVar = ax.p338y1.f.R0;
            return new ax.G1.p(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), str, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("OneDrivePrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("OneDrivePrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(Activity activity, ax.P1.j jVar) {
            jVar.b(ax.p338y1.f.R0);
            OneDriveFileHelper.z0(this.a);
            OneDriveFileHelper.v0(this.a, activity, null, new a(jVar));
        }

        int l() {
            return this.a.getSharedPreferences("OneDrivePrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> m() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("OneDrivePrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("refresh_token_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void n(int i, String str, String str2, String str3) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("OneDrivePrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            U u = this.b;
            ax.F1.a aVarA = ax.F1.a.a();
            ax.p338y1.f fVar = ax.p338y1.f.R0;
            editorEdit.putInt("version_" + i, 3).putString("refresh_token_" + i, u.e(aVarA.b(fVar), str)).putString("display_name_" + i, str2).putString("email_" + i, str3).putString("location_name_" + i, fVar.L(this.a));
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

    public OneDriveFileHelper() {
        U("/", "/drive/root");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(InterfaceC5732e0 interfaceC5732e0) {
        String strF = ((ax.p248r9.b) interfaceC5732e0.c()).f();
        int iQ = q();
        ax.p338y1.f fVar = ax.p338y1.f.R0;
        U u = new U(fVar);
        n().getSharedPreferences("OneDrivePrefs", 0).edit().putInt("version_" + iQ, 3).putString("refresh_token_" + iQ, u.e(ax.F1.a.a().b(fVar), strF)).commit();
    }

    private void B0(ax.J1.M m2, long j2) throws C0718i {
        int i2 = 0;
        while (true) {
            try {
                DriveItem driveItem = new DriveItem();
                FileSystemInfo fileSystemInfo = new FileSystemInfo();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(j2);
                fileSystemInfo.e = calendar;
                driveItem.w = fileSystemInfo;
                f0(m2, false, true).a().j(driveItem);
                return;
            } catch (ax.p276t9.d e2) {
                if (e2.getMessage() == null || !e2.getMessage().contains("409 :") || (i2 = i2 + 1) >= 3) {
                    throw W("setLastModified", e2);
                }
                try {
                    Thread.sleep(((long) i2) * 500);
                } catch (Exception unused) {
                }
            }
        }
    }

    private String C0(String str) throws C0718i, ax.p276t9.d {
        String strO0 = o0(str);
        if (strO0 != null) {
            return V(n0().h(Uri.encode(strO0)).a().get());
        }
        ax.p045d2.b.f();
        return "/drive/root";
    }

    private void D0(ax.p290u9.U u, ax.J1.A a2, long j2, Long l2, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        AtomicReference atomicReference = new AtomicReference();
        MyDriveItemUploadableProperties myDriveItemUploadableProperties = new MyDriveItemUploadableProperties();
        if (z) {
            myDriveItemUploadableProperties.h = "replace";
        } else {
            myDriveItemUploadableProperties.h = "fail";
        }
        if (l2 != null) {
            FileSystemInfo fileSystemInfo = new FileSystemInfo();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(l2.longValue());
            fileSystemInfo.e = calendar;
            myDriveItemUploadableProperties.d = fileSystemInfo;
        }
        try {
            try {
                new A(u.m(myDriveItemUploadableProperties).a().e(), b0(), a2, j2, DriveItem.class).a(new ArrayList(), cVar, new f(iVar, atomicReference), o);
                if (cVar != null && cVar.isCancelled()) {
                    throw new C0710a();
                }
                if (atomicReference.get() != null) {
                    throw W("onedrive uploadFile 2", (ax.p276t9.d) atomicReference.get());
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                throw C0712c.b("onedrive chunked upload", e2);
            }
        } catch (ax.p276t9.d e3) {
            throw W("uploadChunk", e3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:106:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void E0(Y y, ax.J1.A a2, long j2, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        String str;
        InputStream inputStreamB;
        Throwable th;
        int i2;
        String str2 = "Onedrive close";
        ax.p262s9.f fVar = new ax.p262s9.f();
        AtomicReference atomicReference = new AtomicReference();
        int i3 = 0;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        List listSingletonList = z ? Collections.singletonList(new ax.p360z9.d("@microsoft.graph.conflictBehavior", "replace")) : Collections.singletonList(new ax.p360z9.d("@microsoft.graph.conflictBehavior", "fail"));
        while (true) {
            try {
                inputStreamB = a2.b();
                try {
                    String str3 = str2;
                    try {
                        d dVar = new d(iVar, fVar, atomicBoolean, atomicReference);
                        List list = listSingletonList;
                        e eVar = new e(y.d(), y.i(), listSingletonList, DriveItem.class);
                        eVar.t(ax.p318w9.k.PUT);
                        eVar.m().b().a(eVar, dVar, eVar.q(), new i(inputStreamB, j2, cVar));
                        fVar.b();
                        if (cVar != null) {
                            try {
                                if (cVar.isCancelled()) {
                                    throw new C0710a();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                th = th;
                                str = str3;
                                if (inputStreamB == null) {
                                    throw th;
                                }
                                try {
                                    inputStreamB.close();
                                    throw th;
                                } catch (IOException unused) {
                                    throw th;
                                } catch (IllegalStateException e2) {
                                    ax.p221pa.c.h().g().b(str).m(e2).i();
                                    throw th;
                                }
                            }
                        }
                        if (atomicBoolean.get()) {
                            try {
                                if (atomicBoolean.get()) {
                                    if (inputStreamB != null) {
                                        try {
                                            inputStreamB.close();
                                            return;
                                        } catch (IOException unused2) {
                                            return;
                                        } catch (IllegalStateException e3) {
                                            ax.p221pa.c.h().g().b(str3).m(e3).i();
                                            return;
                                        }
                                    }
                                    return;
                                }
                                listSingletonList = list;
                                i2 = i3;
                            } catch (Throwable th3) {
                                th = th3;
                                str = str3;
                                th = th;
                                if (inputStreamB == null) {
                                    throw th;
                                }
                                inputStreamB.close();
                                throw th;
                            }
                        } else {
                            if (i3 >= 3 || !a2.a() || (atomicReference.get() instanceof ax.p318w9.j)) {
                                try {
                                    if (atomicReference.get() == null) {
                                        throw new C0718i("onedrive uploadFile");
                                    }
                                    throw W("onedrive uploadFile", (ax.p276t9.d) atomicReference.get());
                                } catch (Throwable th4) {
                                    th = th4;
                                    th = th;
                                    str = str3;
                                    if (inputStreamB == null) {
                                        throw th;
                                    }
                                    inputStreamB.close();
                                    throw th;
                                }
                            }
                            int i4 = i3 + 1;
                            try {
                                Thread.sleep(i4 * 2000 * i4);
                            } catch (InterruptedException unused3) {
                            }
                            i2 = i4;
                            listSingletonList = Collections.singletonList(new ax.p360z9.d("@microsoft.graph.conflictBehavior", "replace"));
                        }
                        if (inputStreamB != null) {
                            try {
                                inputStreamB.close();
                            } catch (IOException unused4) {
                            } catch (IllegalStateException e4) {
                                ax.p221pa.c.h().g().b(str3).m(e4).i();
                            }
                        }
                        str2 = str3;
                        i3 = i2;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    str = str2;
                }
            } catch (Throwable th7) {
                th = th7;
                str = str2;
                inputStreamB = null;
            }
        }
    }

    private void F0(String str, long j2, ax.P1.i iVar) {
        while (true) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    ax.p149k8.o oVarV = ax.p149k8.n.d(C0744z.k(httpURLConnection.getInputStream(), 256)).f().v("percentageComplete");
                    if (oVarV == null) {
                        httpURLConnection.disconnect();
                        break;
                    }
                    int iT = oVarV.t();
                    if (iVar != null) {
                        iVar.a((((long) iT) * j2) / 100, j2);
                    }
                    if (iT >= 100) {
                        httpURLConnection.disconnect();
                        return;
                    } else {
                        if (iT < 50) {
                            try {
                                Thread.sleep(250L);
                            } catch (Exception unused) {
                            }
                        }
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th) {
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (IOException | IllegalStateException | NullPointerException | NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
        try {
            Thread.sleep(1250L);
        } catch (InterruptedException unused2) {
        }
        if (iVar != null) {
            iVar.a(j2, j2);
        }
    }

    private void G0(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j2, Long l2, boolean z, boolean z2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        File file;
        try {
            try {
                if (!M(abstractC5838l.S(), false).n()) {
                    throw new C0718i("parentPath doesn't exist");
                }
                ax.J1.M m2 = (ax.J1.M) abstractC5838l;
                ax.p290u9.U uF0 = f0(m2, false, true);
                if (j2 != -1) {
                    if (j2 >= n) {
                        D0(uF0, a2, j2, l2, z2, cVar, iVar);
                        return;
                    }
                    E0(uF0.k(), a2, j2, z2, cVar, iVar);
                    if (l2 != null) {
                        B0(m2, l2.longValue());
                        return;
                    }
                    return;
                }
                InputStream inputStream = null;
                try {
                    InputStream inputStreamB = a2.b();
                    try {
                        file = new File(ax.G1.a.k(n(), abstractC5838l), abstractC5838l.w() + System.currentTimeMillis() + ".tmp");
                        try {
                            C0744z.h(inputStreamB, file, -1L, cVar);
                            long length = file.length();
                            C5839m c5839mF = ax.J1.r.f(file);
                            D0(uF0, c5839mF.v(c5839mF.V0(file.getAbsolutePath())), length, l2, z2, cVar, iVar);
                            if (inputStreamB != null) {
                                try {
                                    inputStreamB.close();
                                } catch (IOException unused) {
                                }
                            }
                            file.delete();
                        } catch (Throwable th) {
                            th = th;
                            inputStream = inputStreamB;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            if (file == null) {
                                throw th;
                            }
                            file.delete();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        file = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    file = null;
                }
            } catch (IOException e2) {
                throw C0712c.b("onedrive upload", e2);
            }
        } catch (ax.p276t9.d e3) {
            throw W("writeFileInternal", e3);
        }
    }

    private ax.J1.M M(String str, boolean z) throws C0718i {
        try {
            ax.p304v9.x xVarA = g0(str, false).a();
            if (z) {
                xVarA = xVarA.f("thumbnails");
            }
            return new ax.J1.M(this, xVarA.get(), str);
        } catch (ax.p276t9.d e2) {
            if (e2.a(ax.p276t9.e.ItemNotFound) || (e2.getMessage() != null && e2.getMessage().contains("404 :"))) {
                return new ax.J1.M(this, str);
            }
            if (e2.getMessage() != null && e2.getMessage().contains("400 :") && str.endsWith(".")) {
                throw new ax.I1.n("onedrive getfileinfo", e2);
            }
            e2.printStackTrace();
            throw W("onedrive getfileinfo", e2);
        }
    }

    private void U(String str, String str2) {
        synchronized (this.i) {
            this.j.put(str, str2);
            this.i.put(str2, str);
        }
    }

    private String V(DriveItem driveItem) {
        String str;
        if (driveItem.D != null) {
            str = "/drives/" + driveItem.D.m.c + "/items/" + driveItem.D.h;
        } else {
            str = "/drive/root";
        }
        U(driveItem.l, str);
        return str;
    }

    private C0718i W(String str, ax.p276t9.d dVar) {
        if (dVar.a(ax.p276t9.e.ItemNotFound)) {
            return new ax.I1.s(dVar);
        }
        if (dVar.a(ax.p276t9.e.AccessDenied)) {
            return new C0713d(str, dVar);
        }
        if (dVar.a(ax.p276t9.e.QuotaLimitReached)) {
            return new ax.I1.r(str, dVar);
        }
        if (dVar.a(ax.p276t9.e.NameAlreadyExists)) {
            return new C0714e(false, dVar);
        }
        if (dVar.getMessage() != null && dVar.getMessage().contains("404 :")) {
            return new ax.I1.s(dVar);
        }
        if (dVar.getMessage() != null && dVar.getMessage().contains("507 :")) {
            return new ax.I1.r(str, dVar);
        }
        if (dVar.getMessage() == null || !dVar.getMessage().contains("409 :")) {
            return (dVar.getMessage() == null || !dVar.getMessage().contains("403 : Forbidden")) ? C0712c.b(str, dVar) : new C0713d(str, dVar);
        }
        return new C0714e(false, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InterfaceC5732e0 X(Context context) {
        return new ax.u9.A.a().d(ax.J1.L.f(context, new g(context))).b();
    }

    private String a0() {
        return n().getSharedPreferences("OneDrivePrefs", 0).getString("email_" + q(), null);
    }

    private InterfaceC5732e0 b0() throws C0716g {
        InterfaceC5732e0 interfaceC5732e0 = this.h.get();
        if (interfaceC5732e0 != null) {
            return interfaceC5732e0;
        }
        throw new C0716g("OneDrive client is null");
    }

    private String c0(String str, boolean z) throws C0718i, ax.p276t9.d {
        String strO0 = o0(str);
        String strH0 = strO0 == null ? "/drive/root" : h0(strO0);
        if (strH0 == null) {
            return (r0(str) && z) ? "/drive/root" : C0(str);
        }
        return strH0;
    }

    private String d0(String str) {
        String str2;
        synchronized (this.i) {
            str2 = this.i.get(str);
        }
        return str2;
    }

    private ItemReference e0(String str) throws C0718i {
        ItemReference itemReference = new ItemReference();
        String strC0 = c0(str, false);
        if ("/drive/root".equals(strC0)) {
            itemReference.c = k0();
            String str2 = "/drives/" + itemReference.c + "/root:";
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if ("/".equals(str)) {
                str = HttpUrl.FRAGMENT_ENCODE_SET;
            }
            sb.append(str);
            itemReference.g = sb.toString();
        } else {
            itemReference.c = Y(strC0);
            itemReference.g = strC0 + ":" + q0(str);
        }
        return itemReference;
    }

    private ax.p290u9.U f0(ax.J1.M m2, boolean z, boolean z2) throws C0718i {
        DriveItem driveItemD0 = m2.d0();
        try {
            if (driveItemD0 != null) {
                return (driveItemD0.D == null || !z2) ? b0().e(driveItemD0.m.c).n(driveItemD0.c) : b0().e(driveItemD0.D.m.c).n(driveItemD0.D.h);
            }
            if (z) {
                throw new ax.I1.s("item not exist");
            }
            return g0(m2.C(), true);
        } catch (NullPointerException e2) {
            ax.p221pa.c.h().g().b("Item request builder").m(e2).i();
            throw new C0718i("no onedrive item", e2);
        }
    }

    private ax.p290u9.U g0(String str, boolean z) throws C0718i {
        ax.p290u9.U uN;
        String strC0 = c0(str, z);
        if ("/drive/root".equals(strC0)) {
            uN = n0();
        } else {
            String strY = Y(strC0);
            uN = b0().e(strY).n(Z(strC0));
            str = q0(str);
        }
        if (str.isEmpty() || "/".equals(str)) {
            return uN;
        }
        if (V.y(str)) {
            str = str.substring(1);
        }
        return uN.h(Uri.encode(str));
    }

    private String h0(String str) {
        String str2;
        synchronized (this.i) {
            str2 = this.j.get(str);
        }
        return str2;
    }

    public static j i0(Context context) {
        if (m == null) {
            m = new j(context.getApplicationContext());
        }
        return m;
    }

    private InterfaceC5726b0 m0() throws C0718i {
        return b0().e(k0());
    }

    private ax.p290u9.U n0() throws C0718i {
        return m0().g();
    }

    private String o0(String str) {
        ax.p045d2.b.c(V.y(str));
        String[] strArrSplit = str.split("/");
        if (strArrSplit.length < 2) {
            return null;
        }
        return strArrSplit[1];
    }

    private String q0(String str) {
        ax.p045d2.b.c(V.y(str));
        int iIndexOf = str.indexOf("/", 1);
        return iIndexOf < 0 ? HttpUrl.FRAGMENT_ENCODE_SET : str.substring(iIndexOf);
    }

    private boolean r0(String str) {
        return str.split("/").length == 2;
    }

    private boolean s0(DriveItem driveItem) throws C0718i {
        ItemReference itemReference = driveItem.m;
        if (itemReference != null) {
            String str = itemReference.g;
            if (str == null) {
                return k0().equals(driveItem.m.c);
            }
            if (str.startsWith("/drive/root:")) {
                return true;
            }
            return driveItem.m.g.startsWith("/drives/") && driveItem.m.g.contains("/root:");
        }
        if (!driveItem.l.equals("root") && driveItem.E == null) {
            ax.p045d2.b.e("no parent not root? : " + driveItem.l);
        }
        return true;
    }

    private List<AbstractC5838l> t0(ax.J1.M m2) throws C0718i {
        ArrayList arrayList = new ArrayList();
        boolean zB = V.B(m2);
        boolean z = true;
        try {
            IDriveItemCollectionPage iDriveItemCollectionPage = f0(m2, true, true).c().a().f("thumbnails").get();
            if (iDriveItemCollectionPage == null) {
                z = false;
            }
            ax.p045d2.b.c(z);
            do {
                for (DriveItem driveItem : iDriveItemCollectionPage.b()) {
                    arrayList.add(new ax.J1.M(this, driveItem, V.M(m2.C(), driveItem.l)));
                    if (zB) {
                        V(driveItem);
                    }
                }
                iDriveItemCollectionPage = iDriveItemCollectionPage.a() != null ? ((ax.p290u9.N) iDriveItemCollectionPage.a()).a().get() : null;
            } while (iDriveItemCollectionPage != null);
            return arrayList;
        } catch (ax.p276t9.d e2) {
            throw W("listchildren", e2);
        } catch (NullPointerException e3) {
            ax.p221pa.c.h().g().d("!! OneDrive listChildren NullPointError").m(e3).h("path:" + m2.C()).i();
            throw new C0718i(e3);
        } catch (OutOfMemoryError e4) {
            int size = arrayList.size();
            arrayList.clear();
            ax.p221pa.c.h().g().b("!! OneDrive listChildren OOM").m(e4).h("children:" + size).i();
            throw new C0718i(e4);
        }
    }

    private void u0() {
        String strA;
        SharedPreferences sharedPreferences = n().getSharedPreferences("OneDrivePrefs", 0);
        ax.p338y1.f fVar = ax.p338y1.f.R0;
        U u = new U(fVar);
        int i2 = sharedPreferences.getInt("version_" + q(), 0);
        if (i2 == 0) {
            strA = sharedPreferences.getString("refresh_token_" + q(), null);
        } else {
            strA = u.a(i2, ax.F1.a.a().b(fVar), sharedPreferences.getString("refresh_token_" + q(), null));
        }
        n().getSharedPreferences("com.microsoft.live", 0).edit().putString("refresh_token", strA).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void v0(Context context, Activity activity, String str, ax.p262s9.c<InterfaceC5732e0> cVar) {
        Context applicationContext = context.getApplicationContext();
        InterfaceC5732e0 interfaceC5732e0X = X(applicationContext);
        ((ax.p248r9.b) interfaceC5732e0X.c()).j(new h(cVar, interfaceC5732e0X, applicationContext, activity, str));
    }

    private String w0(String str, String str2) throws C0718i {
        return b0().e(str).n(str2).a().get().m.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x0(InterfaceC5732e0 interfaceC5732e0, ax.K1.L l2) {
        interfaceC5732e0.d().a().c(new a(l2));
    }

    private void y0(String str) {
        if (str == null) {
            return;
        }
        synchronized (this.i) {
            try {
                String strRemove = this.j.remove(str);
                if (strRemove != null) {
                    this.i.remove(strRemove);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void z0(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.microsoft.live", 0).edit();
        editorEdit.remove("refresh_token");
        editorEdit.remove("cookies");
        editorEdit.commit();
        try {
            CookieSyncManager cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(context.getApplicationContext());
            CookieManager.getInstance().removeAllCookies(null);
            cookieSyncManagerCreateInstance.sync();
        } catch (AndroidRuntimeException | UnsupportedOperationException unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean I() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean J() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public void L(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j2, Long l2, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        G0(abstractC5838l, a2, str, j2, l2, z, true, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        return M(str, true);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0027 A[Catch: IOException -> 0x0016, i -> 0x0018, TryCatch #2 {i -> 0x0018, IOException -> 0x0016, blocks: (B:4:0x0003, B:7:0x000c, B:16:0x002b, B:12:0x001a, B:15:0x0027), top: B:20:0x0003 }] */
    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        String strDecode;
        ax.J1.M m2;
        if (str3 != null) {
            try {
                if (str3.startsWith("url=")) {
                    strDecode = Uri.decode(str3.substring(4));
                } else {
                    m2 = (ax.J1.M) V0(str2);
                    if (m2.e0() == null) {
                        return null;
                    }
                    strDecode = m2.e0();
                }
            } catch (C0718i e2) {
                e = e2;
                e.printStackTrace();
                return null;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return null;
            }
        } else {
            m2 = (ax.J1.M) V0(str2);
            if (m2.e0() == null) {
                return null;
            }
            strDecode = m2.e0();
        }
        return new URL(strDecode).openStream();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return true;
    }

    public String Y(String str) {
        ax.p045d2.b.c(str.startsWith("/drives/"));
        String strSubstring = str.substring(8);
        return strSubstring.substring(0, strSubstring.indexOf("/"));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    public String Z(String str) {
        int iIndexOf = str.indexOf("/items/");
        if (iIndexOf >= 0) {
            return str.substring(iIndexOf + 7);
        }
        ax.p045d2.b.g("invalid path:" + str);
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        ax.J1.M m2 = (ax.J1.M) abstractC5838l;
        if (m2.e0() == null) {
            return null;
        }
        return C0740v.S(abstractC5838l, "url=" + Uri.encode(m2.e0()));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return (this.h.get() == null || this.k == null) ? false : true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l2.n());
        if (!abstractC5838l.n()) {
            throw new ax.I1.s("not existing source file");
        }
        if (cVar != null && cVar.isCancelled()) {
            throw new C0710a();
        }
        long jO = abstractC5838l.o();
        try {
            DriveItem driveItemE = f0((ax.J1.M) abstractC5838l, true, true).j(abstractC5838l2.w(), e0(abstractC5838l2.S())).a().e();
            if (driveItemE != null && driveItemE.l == null) {
                String str = driveItemE.n;
                if (str != null) {
                    F0(str, jO, iVar);
                    return;
                } else {
                    ax.p045d2.b.f();
                    return;
                }
            }
            try {
                Thread.sleep(1250L);
            } catch (InterruptedException unused) {
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (ax.p276t9.d e2) {
            throw W("copy", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        if (this.h.get() != null) {
            try {
                ((ax.p248r9.b) this.h.get().c()).l(new c());
                this.h.set(null);
                this.k = null;
            } catch (AndroidRuntimeException e2) {
                ax.p221pa.c.h().g().b("ONEDRIVE DISCONNECT").m(e2).i();
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            f0((ax.J1.M) abstractC5838l, true, false).a().delete();
        } catch (ax.p276t9.d e2) {
            throw W("deleteRecursively", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j2, Long l2, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l.n());
        G0(abstractC5838l, a2, str, j2, l2, z, false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j2) throws C0718i {
        try {
            X xA = f0((ax.J1.M) abstractC5838l, true, true).k().a();
            if (j2 != 0) {
                xA.k("Range", "bytes=" + j2 + "-");
            }
            return xA.get();
        } catch (ax.p276t9.d e2) {
            throw W("getInputStream", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        if (aVar != null) {
            aVar.N();
        }
        u0();
        v0(n(), activity, a0(), new b(fragment, aVar, new AtomicBoolean(false), new AtomicInteger(0)));
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
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        ax.J1.M m2 = (ax.J1.M) abstractC5838l;
        ax.p045d2.b.c(m2.d0() != null);
        return t0(m2);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        try {
            String strS = abstractC5838l.S();
            if (!M(strS, false).n()) {
                return false;
            }
            String strW = abstractC5838l.w();
            MyDriveItem myDriveItem = new MyDriveItem();
            myDriveItem.l = strW;
            myDriveItem.x = new Folder();
            myDriveItem.U = "fail";
            g0(strS, false).c().a().b(myDriveItem);
            return true;
        } catch (C0718i | ax.p276t9.d e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        try {
            g0(abstractC5838l.S(), false).c().e(Uri.encode(abstractC5838l.w())).k().b(Collections.singletonList(new ax.p360z9.d("@microsoft.graph.conflictBehavior", "fail"))).i(null);
            return true;
        } catch (C0718i | ax.p276t9.d e2) {
            e2.printStackTrace();
            return false;
        }
    }

    Drive j0() throws C0716g {
        Drive drive = this.k;
        if (drive != null) {
            return drive;
        }
        ax.p045d2.b.f();
        throw new C0716g("MyDrive is null");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        try {
            IDriveItemSearchCollectionPage iDriveItemSearchCollectionPage = f0((ax.J1.M) abstractC5838l, true, true).f(str).a().get();
            ArrayList arrayList = new ArrayList();
            HashMap<String, String> map = new HashMap<>();
            do {
                for (DriveItem driveItem : iDriveItemSearchCollectionPage.b()) {
                    try {
                        arrayList.add(new ax.J1.M(this, driveItem, p0(map, driveItem)));
                    } catch (ax.I1.H unused) {
                    }
                }
                iDriveItemSearchCollectionPage = iDriveItemSearchCollectionPage.a() != null ? ((W) iDriveItemSearchCollectionPage.a()).a().get() : null;
            } while (iDriveItemSearchCollectionPage != null);
            hVar.l0(arrayList, true);
        } catch (ax.p276t9.d e2) {
            throw W("search", e2);
        }
    }

    String k0() throws C0716g {
        return j0().c;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        if (cVar != null && cVar.isCancelled()) {
            throw new C0718i("Operation cancelled");
        }
        try {
            DriveItem driveItem = new DriveItem();
            if (abstractC5838l.S().equals(abstractC5838l2.S())) {
                driveItem.l = abstractC5838l2.w();
            } else {
                String strS = abstractC5838l2.S();
                if (!M(strS, false).n()) {
                    throw new C0718i("Target parent does not exist");
                }
                driveItem.l = abstractC5838l2.w();
                driveItem.m = e0(strS);
            }
            f0((ax.J1.M) abstractC5838l, true, false).a().j(driveItem);
            if (iVar != null) {
                long jO = abstractC5838l.o();
                iVar.a(jO, jO);
            }
        } catch (ax.p276t9.d e2) {
            throw W("move", e2);
        }
    }

    String l0(String str) throws ax.I1.H {
        try {
            String strD0 = d0(str);
            if (strD0 == null) {
                g1(V0("/"));
                strD0 = d0(str);
                if (strD0 == null) {
                    ax.p221pa.c.h().g().d("ONEDRIVE LOCAL PATH NULL").h("remoteDrivePath : " + strD0).i();
                    throw new ax.I1.H();
                }
            }
            if ("/".equals(strD0)) {
                return "/";
            }
            return "/" + strD0;
        } catch (C0718i unused) {
            throw new ax.I1.H();
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        b1(abstractC5838l);
        if (r0(abstractC5838l.C())) {
            y0(o0(abstractC5838l.C()));
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        ax.p338y1.f fVarP = abstractC5838l.P();
        ax.p338y1.f fVar = ax.p338y1.f.R0;
        if (fVarP == fVar && abstractC5838l2.P() == fVar) {
            try {
                return c0(abstractC5838l.C(), false).equals(c0(abstractC5838l2.C(), true));
            } catch (C0718i | ax.p276t9.d unused) {
            }
        }
        return false;
    }

    String p0(HashMap<String, String> map, DriveItem driveItem) throws ax.I1.H, C0718i {
        ItemReference itemReference = driveItem.m;
        if ((itemReference == null || itemReference.e == null) && driveItem.l.equals("root")) {
            return "/";
        }
        if (driveItem.m == null) {
            ax.p045d2.b.e("this case is not implemented yet");
            return "/" + driveItem.l;
        }
        if (s0(driveItem)) {
            ItemReference itemReference2 = driveItem.m;
            String strW0 = itemReference2.g;
            if (strW0 == null) {
                if (map != null) {
                    strW0 = map.get(itemReference2.e);
                    if (strW0 == null) {
                        strW0 = w0(driveItem.m.c, driveItem.c);
                        map.put(driveItem.m.e, strW0);
                    }
                } else {
                    strW0 = w0(itemReference2.c, driveItem.c);
                }
            }
            if (strW0 == null) {
                ax.p045d2.b.e("this case is not implemented yet");
                return "/" + driveItem.l;
            }
            return Uri.decode(strW0.substring(strW0.indexOf("/root:") + 6)) + "/" + driveItem.l;
        }
        String str = driveItem.m.g;
        if (str != null && str.startsWith("/drives")) {
            int iIndexOf = driveItem.m.g.indexOf(":");
            return l0(driveItem.m.g.substring(0, iIndexOf)) + driveItem.m.g.substring(iIndexOf + 1) + "/" + driveItem.l;
        }
        ItemReference itemReference3 = driveItem.m;
        if (itemReference3.g != null || itemReference3.e != null || k0().equals(driveItem.m.c)) {
            ax.p045d2.b.e("this case is not implemented yet");
            return "/" + driveItem.l;
        }
        return l0("/drives/" + driveItem.m.c + "/items/" + driveItem.c);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        Quota quota;
        Long l2;
        try {
            Drive drive = m0().a().get();
            if (drive != null && (quota = drive.u) != null && (l2 = quota.f) != null && quota.d != null) {
                return new b0(l2.longValue(), drive.u.f.longValue() - drive.u.d.longValue());
            }
            return null;
        } catch (ax.p276t9.d e2) {
            throw W("getStorageSpace", e2);
        }
    }

    public static class i {
        InputStream a;
        long b;
        ax.p045d2.c c;
        ax.p262s9.e d;

        public i(InputStream inputStream, long j, ax.p045d2.c cVar) {
            this.a = inputStream;
            this.b = j;
            this.c = cVar;
        }

        public i(InputStream inputStream, long j, ax.p045d2.c cVar, ax.p262s9.e eVar) {
            this.a = inputStream;
            this.b = j;
            this.c = cVar;
            this.d = eVar;
        }
    }

    class a implements ax.p262s9.c<User> {
        final /* synthetic */ ax.K1.L a;

        a(ax.K1.L l) {
            this.a = l;
        }

        @Override // ax.p262s9.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(User user) {
            ax.K1.L l;
            if (user == null) {
                ax.p221pa.c.h().g().d("OneDrive Null User").i();
                return;
            }
            boolean z = false;
            SharedPreferences sharedPreferences = OneDriveFileHelper.this.n().getSharedPreferences("OneDrivePrefs", 0);
            String string = sharedPreferences.getString("display_name_" + OneDriveFileHelper.this.q(), null);
            String string2 = sharedPreferences.getString("email_" + OneDriveFileHelper.this.q(), null);
            String str = user.q;
            boolean z2 = true;
            if (str != null && !str.equals(string)) {
                sharedPreferences.edit().putString("display_name_" + OneDriveFileHelper.this.q(), user.q).commit();
                z = true;
            }
            String str2 = user.M;
            if (str2 == null || str2.equals(string2)) {
                z2 = z;
            } else {
                sharedPreferences.edit().putString("email_" + OneDriveFileHelper.this.q(), user.M).commit();
            }
            if (!z2 || (l = this.a) == null) {
                return;
            }
            l.a9();
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
        }
    }

    class c implements ax.p262s9.c<Void> {
        c() {
        }

        @Override // ax.p262s9.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Void r1) {
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
        }
    }
}
