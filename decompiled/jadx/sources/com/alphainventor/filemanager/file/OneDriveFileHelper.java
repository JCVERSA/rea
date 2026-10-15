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
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C2001g;
import ax.p086f3.C2017x;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p102g3.C2046a0;
import ax.p333ua.InterfaceC2383b0;
import ax.p333ua.InterfaceC2389e0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
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
public class OneDriveFileHelper extends AbstractC6238m {
    static j m;
    private final AtomicReference<InterfaceC2389e0> h = new AtomicReference<>();
    private final Map<String, String> i = new HashMap();
    private final Map<String, String> j = new HashMap();
    private Drive k;
    private static final Logger l = Logger.getLogger("FileManager.OneDriveFileHelper");
    static int n = 1048576;
    static int o = 5242880;

    public static class MyDriveItem extends DriveItem {

        @ax.Z8.c("@microsoft.graph.conflictBehavior")
        @ax.Z8.a
        public String U;
    }

    public static class MyDriveItemUploadableProperties extends DriveItemUploadableProperties {

        @ax.Z8.c("@microsoft.graph.conflictBehavior")
        @ax.Z8.a
        public String h;
    }

    class a implements ax.p301sa.c<User> {
        final /* synthetic */ C2046a0 a;

        a(C2046a0 c2046a0) {
            this.a = c2046a0;
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
        }

        @Override // ax.p301sa.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(User user) {
            C2046a0 c2046a0;
            if (user == null) {
                ax.p302sb.c.h().g().d("OneDrive Null User").i();
                return;
            }
            boolean z = false;
            SharedPreferences sharedPreferences = OneDriveFileHelper.this.p().getSharedPreferences("OneDrivePrefs", 0);
            String string = sharedPreferences.getString("display_name_" + OneDriveFileHelper.this.t(), null);
            String string2 = sharedPreferences.getString("email_" + OneDriveFileHelper.this.t(), null);
            String str = user.q;
            boolean z2 = true;
            if (str != null && !str.equals(string)) {
                sharedPreferences.edit().putString("display_name_" + OneDriveFileHelper.this.t(), user.q).commit();
                z = true;
            }
            String str2 = user.M;
            if (str2 == null || str2.equals(string2)) {
                z2 = z;
            } else {
                sharedPreferences.edit().putString("email_" + OneDriveFileHelper.this.t(), user.M).commit();
            }
            if (!z2 || (c2046a0 = this.a) == null) {
                return;
            }
            c2046a0.Ia();
        }
    }

    class b implements ax.p301sa.c<InterfaceC2389e0> {
        final /* synthetic */ Fragment a;
        final /* synthetic */ InterfaceC6228c.a b;
        final /* synthetic */ AtomicBoolean c;
        final /* synthetic */ AtomicInteger d;

        class a implements ax.p301sa.c<Drive> {
            final /* synthetic */ InterfaceC2389e0 a;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$b$a$a, reason: collision with other inner class name */
            class RunnableC0532a implements Runnable {
                RunnableC0532a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (!b.this.c.get()) {
                        b.this.c.set(true);
                        b.this.d.set(1);
                        b.this.b.d0(true, null);
                    } else {
                        int i = b.this.d.get();
                        ax.p302sb.c.h().g().d("OneDrive onConnectResult Twice 1").h("called:" + i).i();
                    }
                }
            }

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$b$a$b, reason: collision with other inner class name */
            class RunnableC0533b implements Runnable {
                final /* synthetic */ ax.p317ta.d q;

                RunnableC0533b(ax.p317ta.d dVar) {
                    this.q = dVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (!b.this.c.get()) {
                        b.this.c.set(true);
                        b.this.d.set(2);
                        b.this.b.d0(false, null);
                    } else {
                        int i = b.this.d.get();
                        ax.p302sb.c.h().g().d("OneDrive onConnectResult Twice 2").h("called:" + i).i();
                    }
                }
            }

            a(InterfaceC2389e0 interfaceC2389e0) {
                this.a = interfaceC2389e0;
            }

            @Override // ax.p301sa.c
            public void b(ax.p317ta.d dVar) {
                ax.p406z3.B.a0(new RunnableC0533b(dVar));
            }

            @Override // ax.p301sa.c
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void c(Drive drive) {
                OneDriveFileHelper.this.k = drive;
                b bVar = b.this;
                OneDriveFileHelper.this.D0(this.a, (C2046a0) bVar.a);
                if (b.this.b != null) {
                    ax.p406z3.B.a0(new RunnableC0532a());
                }
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$b$b, reason: collision with other inner class name */
        class RunnableC0534b implements Runnable {
            RunnableC0534b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!b.this.c.get()) {
                    b.this.c.set(true);
                    b.this.d.set(2);
                    b.this.b.d0(false, null);
                } else {
                    int i = b.this.d.get();
                    ax.p302sb.c.h().g().d("OneDrive onConnectResult Twice 3").h("called:" + i).i();
                }
            }
        }

        b(Fragment fragment, InterfaceC6228c.a aVar, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger) {
            this.a = fragment;
            this.b = aVar;
            this.c = atomicBoolean;
            this.d = atomicInteger;
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
            if (this.b != null) {
                ax.p406z3.B.a0(new RunnableC0534b());
            }
        }

        @Override // ax.p301sa.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(InterfaceC2389e0 interfaceC2389e0) {
            OneDriveFileHelper.this.h.set(interfaceC2389e0);
            OneDriveFileHelper.this.G0(interfaceC2389e0);
            interfaceC2389e0.d().l().a().c(new a(interfaceC2389e0));
        }
    }

    class c implements ax.p301sa.c<Void> {
        c() {
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
        }

        @Override // ax.p301sa.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(Void r1) {
        }
    }

    class d implements ax.p301sa.e<DriveItem> {
        final /* synthetic */ ax.p182l3.i a;
        final /* synthetic */ ax.p301sa.f b;
        final /* synthetic */ AtomicBoolean c;
        final /* synthetic */ AtomicReference d;

        d(ax.p182l3.i iVar, ax.p301sa.f fVar, AtomicBoolean atomicBoolean, AtomicReference atomicReference) {
            this.a = iVar;
            this.b = fVar;
            this.c = atomicBoolean;
            this.d = atomicReference;
        }

        @Override // ax.p301sa.e
        public void a(long j, long j2) {
            ax.p182l3.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, j2);
            }
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
            this.c.set(false);
            this.d.set(dVar);
            this.b.a();
        }

        @Override // ax.p301sa.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(DriveItem driveItem) {
            this.b.a();
            this.c.set(true);
        }
    }

    class e extends ax.p365wa.b {
        e(String str, ax.p317ta.f fVar, List list, Class cls) {
            super(str, fVar, list, cls);
        }
    }

    class f implements ax.p301sa.e<DriveItem> {
        final /* synthetic */ ax.p182l3.i a;
        final /* synthetic */ AtomicReference b;

        f(ax.p182l3.i iVar, AtomicReference atomicReference) {
            this.a = iVar;
            this.b = atomicReference;
        }

        @Override // ax.p301sa.e
        public void a(long j, long j2) {
            ax.p182l3.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, j2);
            }
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
            this.b.set(dVar);
        }

        @Override // ax.p301sa.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(DriveItem driveItem) {
        }
    }

    class g extends ax.p285ra.b {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(Context context, String str) {
            super(context);
            this.c = str;
        }

        @Override // ax.p285ra.b
        public String e() {
            return this.c;
        }

        @Override // ax.p285ra.b
        public String[] g() {
            return new String[]{"offline_access", "Files.ReadWrite.All", "User.Read"};
        }
    }

    class h implements ax.p301sa.c<Void> {
        final /* synthetic */ ax.p301sa.c a;
        final /* synthetic */ InterfaceC2389e0 b;
        final /* synthetic */ Context c;
        final /* synthetic */ Activity d;
        final /* synthetic */ String e;

        class a implements ax.p301sa.c<Void> {
            final /* synthetic */ InterfaceC2389e0 a;

            a(InterfaceC2389e0 interfaceC2389e0) {
                this.a = interfaceC2389e0;
            }

            @Override // ax.p301sa.c
            public void b(ax.p317ta.d dVar) {
                h.this.a.b(dVar);
            }

            @Override // ax.p301sa.c
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void c(Void r2) {
                h.this.a.c(this.a);
            }
        }

        h(ax.p301sa.c cVar, InterfaceC2389e0 interfaceC2389e0, Context context, Activity activity, String str) {
            this.a = cVar;
            this.b = interfaceC2389e0;
            this.c = context;
            this.d = activity;
            this.e = str;
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
            OneDriveFileHelper.F0(this.c);
            InterfaceC2389e0 interfaceC2389e0D0 = OneDriveFileHelper.d0(this.c);
            ax.p285ra.b bVar = (ax.p285ra.b) interfaceC2389e0D0.c();
            Activity activity = this.d;
            if (activity == null) {
                this.a.b(null);
            } else {
                bVar.i(activity, this.e, new a(interfaceC2389e0D0));
            }
        }

        @Override // ax.p301sa.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(Void r2) {
            this.a.c(this.b);
        }
    }

    public static class i {
        InputStream a;
        long b;
        InterfaceC2462c c;
        ax.p301sa.e d;

        public i(InputStream inputStream, long j, InterfaceC2462c interfaceC2462c) {
            this.a = inputStream;
            this.b = j;
            this.c = interfaceC2462c;
        }

        public i(InputStream inputStream, long j, InterfaceC2462c interfaceC2462c, ax.p301sa.e eVar) {
            this.a = inputStream;
            this.b = j;
            this.c = interfaceC2462c;
            this.d = eVar;
        }
    }

    public static class j extends T {
        private Context a;
        c0 b = new c0(ax.U2.f.U0);

        class a implements ax.p301sa.c<InterfaceC2389e0> {
            final /* synthetic */ ax.p182l3.k a;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.OneDriveFileHelper$j$a$a, reason: collision with other inner class name */
            class RunnableC0535a implements Runnable {
                final /* synthetic */ int q;

                RunnableC0535a(int i) {
                    this.q = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.a.d(ax.U2.f.U0, this.q);
                }
            }

            class b implements Runnable {
                b() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.a.c(ax.U2.f.U0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                }
            }

            a(ax.p182l3.k kVar) {
                this.a = kVar;
            }

            @Override // ax.p301sa.c
            public void b(ax.p317ta.d dVar) {
                if (this.a != null) {
                    ax.p406z3.B.a0(new b());
                }
            }

            @Override // ax.p301sa.c
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void c(InterfaceC2389e0 interfaceC2389e0) {
                String strF = ((ax.p285ra.b) interfaceC2389e0.c()).f();
                int iL = j.this.l();
                j.this.n(iL, strF, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET);
                if (this.a != null) {
                    ax.p406z3.B.a0(new RunnableC0535a(iL));
                }
            }
        }

        public j(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("OneDrivePrefs", 0).edit().remove("version_" + i).remove("refresh_token_" + i).remove("display_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).remove("email_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("OneDrivePrefs", 0);
            String string = sharedPreferences.getString("display_name_" + i, null);
            String string2 = sharedPreferences.getString("email_" + i, null);
            String str = TextUtils.isEmpty(string2) ? string : string2;
            ax.U2.f fVar = ax.U2.f.U0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), str, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("OneDrivePrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("OneDrivePrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(Activity activity, ax.p182l3.k kVar) {
            kVar.a(ax.U2.f.U0);
            OneDriveFileHelper.F0(this.a);
            OneDriveFileHelper.B0(this.a, activity, null, new a(kVar));
        }

        int l() {
            return this.a.getSharedPreferences("OneDrivePrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> m() {
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
            c0 c0Var = this.b;
            ax.p022b3.a aVarA = ax.p022b3.a.a();
            ax.U2.f fVar = ax.U2.f.U0;
            editorEdit.putInt("version_" + i, 3).putString("refresh_token_" + i, c0Var.e(aVarA.b(fVar), str)).putString("display_name_" + i, str2).putString("email_" + i, str3).putString("location_name_" + i, fVar.L(this.a));
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
        a0("/", "/drive/root");
    }

    private void A0() {
        String strA;
        SharedPreferences sharedPreferences = p().getSharedPreferences("OneDrivePrefs", 0);
        ax.U2.f fVar = ax.U2.f.U0;
        c0 c0Var = new c0(fVar);
        int i2 = sharedPreferences.getInt("version_" + t(), 0);
        if (i2 == 0) {
            strA = sharedPreferences.getString("refresh_token_" + t(), null);
        } else {
            strA = c0Var.a(i2, ax.p022b3.a.a().b(fVar), sharedPreferences.getString("refresh_token_" + t(), null));
        }
        p().getSharedPreferences("com.microsoft.live", 0).edit().putString("refresh_token", strA).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void B0(Context context, Activity activity, String str, ax.p301sa.c<InterfaceC2389e0> cVar) {
        Context applicationContext = context.getApplicationContext();
        InterfaceC2389e0 interfaceC2389e0D0 = d0(applicationContext);
        ((ax.p285ra.b) interfaceC2389e0D0.c()).j(new h(cVar, interfaceC2389e0D0, applicationContext, activity, str));
    }

    private String C0(String str, String str2) throws C1966j {
        return h0().e(str).n(str2).a().get().m.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D0(InterfaceC2389e0 interfaceC2389e0, C2046a0 c2046a0) {
        interfaceC2389e0.d().a().c(new a(c2046a0));
    }

    private void E0(String str) {
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
    public static void F0(Context context) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void G0(InterfaceC2389e0 interfaceC2389e0) {
        String strF = ((ax.p285ra.b) interfaceC2389e0.c()).f();
        int iT = t();
        ax.U2.f fVar = ax.U2.f.U0;
        c0 c0Var = new c0(fVar);
        p().getSharedPreferences("OneDrivePrefs", 0).edit().putInt("version_" + iT, 3).putString("refresh_token_" + iT, c0Var.e(ax.p022b3.a.a().b(fVar), strF)).commit();
    }

    private void H0(ax.p086f3.T t, long j2) throws C1966j {
        int i2 = 0;
        while (true) {
            try {
                DriveItem driveItem = new DriveItem();
                FileSystemInfo fileSystemInfo = new FileSystemInfo();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(j2);
                fileSystemInfo.e = calendar;
                driveItem.w = fileSystemInfo;
                l0(t, false, true).a().j(driveItem);
                return;
            } catch (ax.p317ta.d e2) {
                if (e2.getMessage() == null || !e2.getMessage().contains("409 :") || (i2 = i2 + 1) >= 3) {
                    throw c0("setLastModified", e2);
                }
                try {
                    Thread.sleep(((long) i2) * 500);
                } catch (Exception unused) {
                }
            }
        }
    }

    private String I0(String str) throws C1966j, ax.p317ta.d {
        String strU0 = u0(str);
        if (strU0 != null) {
            return b0(t0().h(Uri.encode(strU0)).a().get());
        }
        C2461b.f();
        return "/drive/root";
    }

    private void J0(ax.p333ua.U u, ax.p086f3.G g2, long j2, Long l2, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
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
                new E(u.m(myDriveItemUploadableProperties).a().e(), h0(), g2, j2, DriveItem.class).a(new ArrayList(), interfaceC2462c, new f(iVar, atomicReference), o);
                if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                    throw new C1957a();
                }
                if (atomicReference.get() != null) {
                    throw c0("onedrive uploadFile 2", (ax.p317ta.d) atomicReference.get());
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                throw C1960d.b("onedrive chunked upload", e2);
            }
        } catch (ax.p317ta.d e3) {
            throw c0("uploadChunk", e3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:107:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void K0(ax.p333ua.Y y, ax.p086f3.G g2, long j2, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        InputStream inputStreamB;
        Throwable th;
        ax.p301sa.f fVar = new ax.p301sa.f();
        AtomicReference atomicReference = new AtomicReference();
        int i2 = 0;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        List listSingletonList = z ? Collections.singletonList(new ax.p413za.d("@microsoft.graph.conflictBehavior", "replace")) : Collections.singletonList(new ax.p413za.d("@microsoft.graph.conflictBehavior", "fail"));
        while (true) {
            try {
                inputStreamB = g2.b();
                try {
                    try {
                        d dVar = new d(iVar, fVar, atomicBoolean, atomicReference);
                        try {
                            try {
                                e eVar = new e(y.d(), y.i(), listSingletonList, DriveItem.class);
                                eVar.t(ax.p365wa.k.PUT);
                                ax.p301sa.f fVar2 = fVar;
                                AtomicBoolean atomicBoolean2 = atomicBoolean;
                                eVar.m().b().b(eVar, dVar, eVar.q(), new i(inputStreamB, j2, interfaceC2462c));
                                fVar2.b();
                                if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                                    throw new C1957a();
                                }
                                if (atomicBoolean2.get()) {
                                    if (atomicBoolean2.get()) {
                                        if (inputStreamB != null) {
                                            try {
                                                inputStreamB.close();
                                                return;
                                            } catch (IOException unused) {
                                                return;
                                            } catch (IllegalStateException e2) {
                                                ax.p302sb.c.h().g().b("Onedrive close").m(e2).i();
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                } else {
                                    if (i2 >= 3 || !g2.a() || (atomicReference.get() instanceof ax.p365wa.j)) {
                                        if (atomicReference.get() == null) {
                                            throw new C1966j("onedrive uploadFile");
                                        }
                                        throw c0("onedrive uploadFile", (ax.p317ta.d) atomicReference.get());
                                    }
                                    i2++;
                                    try {
                                        Thread.sleep(i2 * 2000 * i2);
                                    } catch (InterruptedException unused2) {
                                    }
                                    listSingletonList = Collections.singletonList(new ax.p413za.d("@microsoft.graph.conflictBehavior", "replace"));
                                }
                                int i3 = i2;
                                if (inputStreamB != null) {
                                    try {
                                        inputStreamB.close();
                                    } catch (IOException unused3) {
                                    } catch (IllegalStateException e3) {
                                        ax.p302sb.c.h().g().b("Onedrive close").m(e3).i();
                                    }
                                }
                                fVar = fVar2;
                                i2 = i3;
                                atomicBoolean = atomicBoolean2;
                            } catch (Throwable th2) {
                                th = th2;
                                th = th;
                                inputStreamB = inputStreamB;
                                if (inputStreamB != null) {
                                    throw th;
                                }
                                try {
                                    inputStreamB.close();
                                    throw th;
                                } catch (IOException unused4) {
                                    throw th;
                                } catch (IllegalStateException e4) {
                                    ax.p302sb.c.h().g().b("Onedrive close").m(e4).i();
                                    throw th;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        th = th;
                        if (inputStreamB != null) {
                            throw th;
                        }
                        inputStreamB.close();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
                inputStreamB = null;
            }
        }
    }

    private void L0(String str, long j2, ax.p182l3.i iVar) {
        while (true) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    ax.Y8.o oVarV = ax.Y8.n.d(ax.p086f3.F.i(httpURLConnection.getInputStream(), 256)).g().v("percentageComplete");
                    if (oVarV == null) {
                        httpURLConnection.disconnect();
                        break;
                    }
                    int iS = oVarV.s();
                    if (iVar != null) {
                        iVar.a((((long) iS) * j2) / 100, j2);
                    }
                    if (iS >= 100) {
                        httpURLConnection.disconnect();
                        return;
                    } else {
                        if (iS < 50) {
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

    private void M0(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j2, Long l2, boolean z, boolean z2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        File file;
        try {
            if (!S(abstractC6239n.U(), false).o()) {
                throw new C1966j("parentPath doesn't exist");
            }
            ax.p086f3.T t = (ax.p086f3.T) abstractC6239n;
            ax.p333ua.U uL0 = l0(t, false, true);
            if (j2 != -1) {
                if (j2 >= n) {
                    J0(uL0, g2, j2, l2, z2, interfaceC2462c, iVar);
                    return;
                }
                K0(uL0.k(), g2, j2, z2, interfaceC2462c, iVar);
                if (l2 != null) {
                    H0(t, l2.longValue());
                    return;
                }
                return;
            }
            InputStream inputStream = null;
            try {
                InputStream inputStreamB = g2.b();
                try {
                    file = new File(ax.p038c3.a.l(p(), abstractC6239n), abstractC6239n.B() + System.currentTimeMillis() + ".tmp");
                    try {
                        ax.p086f3.F.f(inputStreamB, file, -1L, interfaceC2462c);
                        try {
                            C6240o c6240oF = C2017x.f(file);
                            AbstractC6239n abstractC6239nA1 = c6240oF.a1(file.getAbsolutePath());
                            long jP = abstractC6239nA1.p();
                            ax.p086f3.G gX = c6240oF.x(abstractC6239nA1);
                            if (jP == 0) {
                                K0(uL0.k(), gX, jP, z2, interfaceC2462c, iVar);
                                if (l2 != null) {
                                    H0(t, l2.longValue());
                                }
                            } else {
                                J0(uL0, gX, jP, l2, z2, interfaceC2462c, iVar);
                            }
                            ax.p086f3.F.a(null);
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (Throwable th) {
                            th = th;
                            ax.p086f3.F.a(inputStream);
                            if (file != null && file.exists()) {
                                file.delete();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = inputStreamB;
                        ax.p086f3.F.a(inputStream);
                        if (file != null) {
                            file.delete();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    file = null;
                }
            } catch (Throwable th4) {
                th = th4;
                file = null;
            }
        } catch (ax.p317ta.d e2) {
            throw c0("writeFileInternal", e2);
        } catch (IOException e3) {
            throw C1960d.b("onedrive upload", e3);
        } catch (Throwable th5) {
            throw th5;
        }
    }

    private ax.p086f3.T S(String str, boolean z) throws C1966j {
        try {
            ax.p349va.x xVarA = m0(str, false).a();
            if (z) {
                xVarA = xVarA.f("thumbnails");
            }
            return new ax.p086f3.T(this, xVarA.get(), str);
        } catch (ax.p317ta.d e2) {
            if (e2.a(ax.p317ta.e.ItemNotFound) || (e2.getMessage() != null && e2.getMessage().contains("404 :"))) {
                return new ax.p086f3.T(this, str);
            }
            if (e2.getMessage() != null && e2.getMessage().contains("400 :") && str.endsWith(".")) {
                throw new ax.p070e3.o("onedrive getfileinfo", e2);
            }
            e2.printStackTrace();
            throw c0("onedrive getfileinfo", e2);
        }
    }

    private void a0(String str, String str2) {
        synchronized (this.i) {
            this.j.put(str, str2);
            this.i.put(str2, str);
        }
    }

    private String b0(DriveItem driveItem) {
        String str;
        if (driveItem.D != null) {
            str = "/drives/" + driveItem.D.m.c + "/items/" + driveItem.D.h;
        } else {
            str = "/drive/root";
        }
        a0(driveItem.l, str);
        return str;
    }

    private C1966j c0(String str, ax.p317ta.d dVar) {
        if (dVar.a(ax.p317ta.e.ItemNotFound)) {
            return new ax.p070e3.t(dVar);
        }
        if (dVar.a(ax.p317ta.e.AccessDenied)) {
            return new C1961e(str, dVar);
        }
        if (dVar.a(ax.p317ta.e.QuotaLimitReached)) {
            return new ax.p070e3.s(str, dVar);
        }
        if (dVar.a(ax.p317ta.e.NameAlreadyExists)) {
            return new C1962f(false, dVar);
        }
        if (dVar.getMessage() != null && dVar.getMessage().contains("404 :")) {
            return new ax.p070e3.t(dVar);
        }
        if (dVar.getMessage() != null && dVar.getMessage().contains("507 :")) {
            return new ax.p070e3.s(str, dVar);
        }
        if (dVar.getMessage() == null || !dVar.getMessage().contains("409 :")) {
            return (dVar.getMessage() == null || !dVar.getMessage().contains("403 : Forbidden")) ? C1960d.b(str, dVar) : new C1961e(str, dVar);
        }
        return new C1962f(false, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InterfaceC2389e0 d0(Context context) {
        return new ax.ua.A.a().d(ax.p086f3.S.f(context, new g(context, C2001g.c(context).a(ax.U2.f.U0)))).b();
    }

    private String g0() {
        return p().getSharedPreferences("OneDrivePrefs", 0).getString("email_" + t(), null);
    }

    private InterfaceC2389e0 h0() throws C1964h {
        InterfaceC2389e0 interfaceC2389e0 = this.h.get();
        if (interfaceC2389e0 != null) {
            return interfaceC2389e0;
        }
        throw new C1964h("OneDrive client is null");
    }

    private String i0(String str, boolean z) throws C1966j, ax.p317ta.d {
        String strU0 = u0(str);
        String strN0 = strU0 == null ? "/drive/root" : n0(strU0);
        if (strN0 == null) {
            return (x0(str) && z) ? "/drive/root" : I0(str);
        }
        return strN0;
    }

    private String j0(String str) {
        String str2;
        synchronized (this.i) {
            str2 = this.i.get(str);
        }
        return str2;
    }

    private ItemReference k0(String str) throws C1966j {
        ItemReference itemReference = new ItemReference();
        String strI0 = i0(str, false);
        if (!"/drive/root".equals(strI0)) {
            itemReference.c = e0(strI0);
            itemReference.g = strI0 + ":" + w0(str);
            return itemReference;
        }
        itemReference.c = q0();
        String str2 = "/drives/" + itemReference.c + "/root:";
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        if ("/".equals(str)) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        sb.append(str);
        itemReference.g = sb.toString();
        return itemReference;
    }

    private ax.p333ua.U l0(ax.p086f3.T t, boolean z, boolean z2) throws C1966j {
        DriveItem driveItemG0 = t.g0();
        try {
            if (driveItemG0 != null) {
                return (driveItemG0.D == null || !z2) ? h0().e(driveItemG0.m.c).n(driveItemG0.c) : h0().e(driveItemG0.D.m.c).n(driveItemG0.D.h);
            }
            if (z) {
                throw new ax.p070e3.t("item not exist");
            }
            return m0(t.E(), true);
        } catch (NullPointerException e2) {
            ax.p302sb.c.h().g().b("Item request builder").m(e2).i();
            throw new C1966j("no onedrive item", e2);
        }
    }

    private ax.p333ua.U m0(String str, boolean z) throws C1966j {
        ax.p333ua.U uN;
        String strI0 = i0(str, z);
        if ("/drive/root".equals(strI0)) {
            uN = t0();
        } else {
            String strE0 = e0(strI0);
            uN = h0().e(strE0).n(f0(strI0));
            str = w0(str);
        }
        if (str.isEmpty() || "/".equals(str)) {
            return uN;
        }
        if (d0.B(str)) {
            str = str.substring(1);
        }
        return uN.h(Uri.encode(str));
    }

    private String n0(String str) {
        String str2;
        synchronized (this.i) {
            str2 = this.j.get(str);
        }
        return str2;
    }

    public static j o0(Context context) {
        if (m == null) {
            m = new j(context.getApplicationContext());
        }
        return m;
    }

    private InterfaceC2383b0 s0() throws C1966j {
        return h0().e(q0());
    }

    private ax.p333ua.U t0() throws C1966j {
        return s0().g();
    }

    private String u0(String str) {
        C2461b.c(d0.B(str));
        String[] strArrSplit = str.split("/");
        if (strArrSplit.length < 2) {
            return null;
        }
        return strArrSplit[1];
    }

    private String w0(String str) {
        C2461b.c(d0.B(str));
        int iIndexOf = str.indexOf("/", 1);
        return iIndexOf < 0 ? HttpUrl.FRAGMENT_ENCODE_SET : str.substring(iIndexOf);
    }

    private boolean x0(String str) {
        return str.split("/").length == 2;
    }

    private boolean y0(DriveItem driveItem) throws C1966j {
        ItemReference itemReference = driveItem.m;
        if (itemReference != null) {
            String str = itemReference.g;
            if (str == null) {
                return q0().equals(driveItem.m.c);
            }
            if (str.startsWith("/drive/root:")) {
                return true;
            }
            return driveItem.m.g.startsWith("/drives/") && driveItem.m.g.contains("/root:");
        }
        if (!driveItem.l.equals("root") && driveItem.E == null) {
            C2461b.e("no parent not root? : " + driveItem.l);
        }
        return true;
    }

    private List<AbstractC6239n> z0(ax.p086f3.T t) throws C1966j {
        ArrayList arrayList = new ArrayList();
        boolean zE = d0.E(t);
        boolean z = true;
        try {
            IDriveItemCollectionPage iDriveItemCollectionPage = l0(t, true, true).b().a().f("thumbnails").get();
            if (iDriveItemCollectionPage == null) {
                z = false;
            }
            C2461b.c(z);
            do {
                for (DriveItem driveItem : iDriveItemCollectionPage.b()) {
                    arrayList.add(new ax.p086f3.T(this, driveItem, d0.Q(t.E(), driveItem.l)));
                    if (zE) {
                        b0(driveItem);
                    }
                }
                iDriveItemCollectionPage = iDriveItemCollectionPage.a() != null ? ((ax.p333ua.N) iDriveItemCollectionPage.a()).a().get() : null;
            } while (iDriveItemCollectionPage != null);
            return arrayList;
        } catch (ax.p317ta.d e2) {
            throw c0("listchildren", e2);
        } catch (NullPointerException e3) {
            ax.p302sb.c.h().g().d("!! OneDrive listChildren NullPointError").m(e3).h("path:" + t.E()).i();
            throw new C1966j(e3);
        } catch (OutOfMemoryError e4) {
            int size = arrayList.size();
            arrayList.clear();
            ax.p302sb.c.h().g().b("!! OneDrive listChildren OOM").m(e4).h("children:" + size).i();
            throw new C1966j(e4);
        }
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
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j2, Long l2, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        M0(abstractC6239n, g2, str, j2, l2, z, true, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return (this.h.get() == null || this.k == null) ? false : true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        return S(str, true);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        if (this.h.get() != null) {
            try {
                ((ax.p285ra.b) this.h.get().c()).l(new c());
                this.h.set(null);
                this.k = null;
            } catch (AndroidRuntimeException e2) {
                ax.p302sb.c.h().g().b("ONEDRIVE DISCONNECT").m(e2).i();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0027 A[Catch: IOException -> 0x0016, j -> 0x0018, TryCatch #2 {j -> 0x0018, IOException -> 0x0016, blocks: (B:4:0x0003, B:7:0x000c, B:16:0x002b, B:12:0x001a, B:15:0x0027), top: B:20:0x0003 }] */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        String strDecode;
        ax.p086f3.T t;
        if (str3 != null) {
            try {
                if (str3.startsWith("url=")) {
                    strDecode = Uri.decode(str3.substring(4));
                } else {
                    t = (ax.p086f3.T) a1(str2);
                    if (t.h0() == null) {
                        return null;
                    }
                    strDecode = t.h0();
                }
            } catch (C1966j e2) {
                e = e2;
                e.printStackTrace();
                return null;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return null;
            }
        } else {
            t = (ax.p086f3.T) a1(str2);
            if (t.h0() == null) {
                return null;
            }
            strDecode = t.h0();
        }
        return new URL(strDecode).openStream();
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
        C2461b.c(abstractC6239n.isDirectory());
        ax.p086f3.T t = (ax.p086f3.T) abstractC6239n;
        C2461b.c(t.g0() != null);
        return z0(t);
    }

    public String e0(String str) {
        C2461b.c(str.startsWith("/drives/"));
        String strSubstring = str.substring(8);
        return strSubstring.substring(0, strSubstring.indexOf("/"));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    public String f0(String str) {
        int iIndexOf = str.indexOf("/items/");
        if (iIndexOf >= 0) {
            return str.substring(iIndexOf + 7);
        }
        C2461b.g("invalid path:" + str);
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        ax.p086f3.T t = (ax.p086f3.T) abstractC6239n;
        if (t.h0() == null) {
            return null;
        }
        return ax.p086f3.B.a0(abstractC6239n, "url=" + Uri.encode(t.h0()));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            l0((ax.p086f3.T) abstractC6239n, true, false).a().delete();
        } catch (ax.p317ta.d e2) {
            throw c0("deleteRecursively", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j2) throws C1966j {
        try {
            ax.p333ua.X xA = l0((ax.p086f3.T) abstractC6239n, true, true).k().a();
            if (j2 != 0) {
                xA.k("Range", "bytes=" + j2 + "-");
            }
            return xA.get();
        } catch (ax.p317ta.d e2) {
            throw c0("getInputStream", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
            throw new C1966j("Operation cancelled");
        }
        try {
            DriveItem driveItem = new DriveItem();
            if (abstractC6239n.U().equals(abstractC6239n2.U())) {
                driveItem.l = abstractC6239n2.B();
            } else {
                String strU = abstractC6239n2.U();
                if (!S(strU, false).o()) {
                    throw new C1966j("Target parent does not exist");
                }
                driveItem.l = abstractC6239n2.B();
                driveItem.m = k0(strU);
            }
            l0((ax.p086f3.T) abstractC6239n, true, false).a().j(driveItem);
            if (iVar != null) {
                long jP = abstractC6239n.p();
                iVar.a(jP, jP);
            }
        } catch (ax.p317ta.d e2) {
            throw c0("move", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        if (aVar != null) {
            aVar.J();
        }
        A0();
        B0(p(), activity, g0(), new b(fragment, aVar, new AtomicBoolean(false), new AtomicInteger(0)));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j2, Long l2, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n.o());
        M0(abstractC6239n, g2, str, j2, l2, z, false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        if (!z2) {
            o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
            return;
        }
        try {
            IDriveItemSearchCollectionPage iDriveItemSearchCollectionPage = l0((ax.p086f3.T) abstractC6239n, true, true).f(str).a().get();
            ArrayList arrayList = new ArrayList();
            HashMap<String, String> map = new HashMap<>();
            do {
                for (DriveItem driveItem : iDriveItemSearchCollectionPage.b()) {
                    try {
                        arrayList.add(new ax.p086f3.T(this, driveItem, v0(map, driveItem)));
                    } catch (ax.p070e3.J unused) {
                    }
                }
                iDriveItemSearchCollectionPage = iDriveItemSearchCollectionPage.a() != null ? ((ax.p333ua.W) iDriveItemSearchCollectionPage.a()).a().get() : null;
            } while (iDriveItemSearchCollectionPage != null);
            hVar.i0(arrayList, true);
        } catch (ax.p317ta.d e2) {
            throw c0("search", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t("not existing source file");
        }
        if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
            throw new C1957a();
        }
        long jP = abstractC6239n.p();
        try {
            DriveItem driveItemE = l0((ax.p086f3.T) abstractC6239n, true, true).j(abstractC6239n2.B(), k0(abstractC6239n2.U())).a().e();
            if (driveItemE != null && driveItemE.l == null) {
                String str = driveItemE.n;
                if (str != null) {
                    L0(str, jP, iVar);
                    return;
                } else {
                    C2461b.f();
                    return;
                }
            }
            try {
                Thread.sleep(1250L);
            } catch (InterruptedException unused) {
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (ax.p317ta.d e2) {
            throw c0("copy", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        try {
            String strU = abstractC6239n.U();
            if (!S(strU, false).o()) {
                return false;
            }
            String strB = abstractC6239n.B();
            MyDriveItem myDriveItem = new MyDriveItem();
            myDriveItem.l = strB;
            myDriveItem.x = new Folder();
            myDriveItem.U = "fail";
            m0(strU, false).b().a().b(myDriveItem);
            return true;
        } catch (C1966j | ax.p317ta.d e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        try {
            m0(abstractC6239n.U(), false).b().e(Uri.encode(abstractC6239n.B())).k().c(Collections.singletonList(new ax.p413za.d("@microsoft.graph.conflictBehavior", "fail"))).i(null);
            return true;
        } catch (C1966j | ax.p317ta.d e2) {
            e2.printStackTrace();
            return false;
        }
    }

    Drive p0() throws C1964h {
        Drive drive = this.k;
        if (drive != null) {
            return drive;
        }
        C2461b.f();
        throw new C1964h("MyDrive is null");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return true;
    }

    String q0() throws C1964h {
        return p0().c;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        g1(abstractC6239n);
        if (x0(abstractC6239n.E())) {
            E0(u0(abstractC6239n.E()));
        }
    }

    String r0(String str) throws ax.p070e3.J {
        try {
            String strJ0 = j0(str);
            if (strJ0 == null) {
                J(a1("/"));
                strJ0 = j0(str);
                if (strJ0 == null) {
                    ax.p302sb.c.h().g().d("ONEDRIVE LOCAL PATH NULL").h("remoteDrivePath : " + strJ0).i();
                    throw new ax.p070e3.J();
                }
            }
            if ("/".equals(strJ0)) {
                return "/";
            }
            return "/" + strJ0;
        } catch (C1966j unused) {
            throw new ax.p070e3.J();
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        ax.U2.f fVarQ = abstractC6239n.Q();
        ax.U2.f fVar = ax.U2.f.U0;
        if (fVarQ == fVar && abstractC6239n2.Q() == fVar) {
            try {
                return i0(abstractC6239n.E(), false).equals(i0(abstractC6239n2.E(), true));
            } catch (C1966j | ax.p317ta.d unused) {
            }
        }
        return false;
    }

    String v0(HashMap<String, String> map, DriveItem driveItem) throws C1966j, ax.p070e3.J {
        ItemReference itemReference = driveItem.m;
        if ((itemReference == null || itemReference.e == null) && driveItem.l.equals("root")) {
            return "/";
        }
        if (driveItem.m == null) {
            C2461b.e("this case is not implemented yet");
            return "/" + driveItem.l;
        }
        if (y0(driveItem)) {
            ItemReference itemReference2 = driveItem.m;
            String strC0 = itemReference2.g;
            if (strC0 == null) {
                if (map != null) {
                    strC0 = map.get(itemReference2.e);
                    if (strC0 == null) {
                        strC0 = C0(driveItem.m.c, driveItem.c);
                        map.put(driveItem.m.e, strC0);
                    }
                } else {
                    strC0 = C0(itemReference2.c, driveItem.c);
                }
            }
            if (strC0 == null) {
                C2461b.e("this case is not implemented yet");
                return "/" + driveItem.l;
            }
            return Uri.decode(strC0.substring(strC0.indexOf("/root:") + 6)) + "/" + driveItem.l;
        }
        String str = driveItem.m.g;
        if (str != null && str.startsWith("/drives")) {
            int iIndexOf = driveItem.m.g.indexOf(":");
            return r0(driveItem.m.g.substring(0, iIndexOf)) + driveItem.m.g.substring(iIndexOf + 1) + "/" + driveItem.l;
        }
        ItemReference itemReference3 = driveItem.m;
        if (itemReference3.g != null || itemReference3.e != null || q0().equals(driveItem.m.c)) {
            C2461b.e("this case is not implemented yet");
            return "/" + driveItem.l;
        }
        return r0("/drives/" + driveItem.m.c + "/items/" + driveItem.c);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        Quota quota;
        Long l2;
        try {
            Drive drive = s0().a().get();
            if (drive != null && (quota = drive.u) != null && (l2 = quota.f) != null && quota.d != null) {
                return new k0(l2.longValue(), drive.u.f.longValue() - drive.u.d.longValue());
            }
            return null;
        } catch (ax.p317ta.d e2) {
            throw c0("getStorageSpace", e2);
        }
    }
}
