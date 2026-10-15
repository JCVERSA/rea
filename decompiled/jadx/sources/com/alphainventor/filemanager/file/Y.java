package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C2001g;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p102g3.I0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import com.alphainventor.filemanager.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class Y extends AbstractC6238m {
    private static final Logger l = Logger.getLogger("FileManager.YandexFileHelper");
    private static e m;
    private volatile ax.Fb.i h;
    private volatile boolean i;
    private ax.p406z3.q<Object, Void, Boolean> j;
    private AbstractC6239n k;

    class a implements ax.Fb.k {
        final /* synthetic */ ax.p086f3.G a;
        final /* synthetic */ long b;

        a(ax.p086f3.G g, long j) {
            this.a = g;
            this.b = j;
        }

        @Override // ax.Fb.k
        public InputStream a(long j) throws IOException {
            try {
                return this.a.c(j);
            } catch (C1966j e) {
                throw new IOException(e);
            }
        }

        @Override // ax.Fb.k
        public long length() {
            return this.b;
        }
    }

    class b implements ax.Fb.d {
        final /* synthetic */ ax.p182l3.i a;
        final /* synthetic */ InterfaceC2462c b;

        b(ax.p182l3.i iVar, InterfaceC2462c interfaceC2462c) {
            this.a = iVar;
            this.b = interfaceC2462c;
        }

        @Override // ax.Fb.d
        public boolean a() {
            InterfaceC2462c interfaceC2462c = this.b;
            return interfaceC2462c != null && interfaceC2462c.isCancelled();
        }

        @Override // ax.Fb.d
        public void b(long j, long j2) {
            ax.p182l3.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j, j2);
            }
        }
    }

    class c implements ValueCallback<Boolean> {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ InterfaceC6228c.a e;

        class a implements ax.o3.b.d {
            a() {
            }

            @Override // ax.o3.b.d
            public void a() {
                c.this.e.d0(false, null);
            }

            @Override // ax.o3.b.d
            public void b(Bundle bundle) {
                CookieSyncManager.getInstance().sync();
                String string = bundle.getString("access_token");
                if (!Y.c0(string, bundle.getString("expires_in"))) {
                    Y.l.severe("OAUTH : FAILED TO RECEIVE ACCESS TOKEN 2");
                    c.this.e.d0(false, null);
                    return;
                }
                Y.b0(Y.this.p()).n(c.this.c, string, HttpUrl.FRAGMENT_ENCODE_SET);
                Y y = Y.this;
                Context contextP = Y.this.p();
                c cVar = c.this;
                Activity activity = cVar.a;
                Fragment fragment = cVar.d;
                Y y2 = Y.this;
                y.j = new d(contextP, activity, fragment, y2, y2.t(), c.this.e);
                Y.this.j.i(new Object[0]);
            }

            @Override // ax.o3.b.d
            public void c(ax.p230o3.c cVar) {
                Y.l.severe("OAUTH ERROR 2:" + cVar.getMessage());
                c.this.e.d0(false, null);
            }
        }

        c(Activity activity, String str, int i, Fragment fragment, InterfaceC6228c.a aVar) {
            this.a = activity;
            this.b = str;
            this.c = i;
            this.d = fragment;
            this.e = aVar;
        }

        @Override // android.webkit.ValueCallback
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onReceiveValue(Boolean bool) {
            Y.f0(this.a, this.b, new a());
        }
    }

    private static class d extends ax.p406z3.q<Object, Void, Boolean> {
        Context h;
        InterfaceC6228c.a i;
        String j;
        String k;
        Y l;
        boolean m;
        int n;
        I0 o;
        boolean p;
        Activity q;

        public d(Context context, Activity activity, Fragment fragment, Y y, int i, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.h = context;
            this.q = activity;
            this.l = y;
            this.i = aVar;
            this.n = i;
            this.o = (I0) fragment;
            SharedPreferences sharedPreferences = context.getSharedPreferences("YandexDiskPrefs", 0);
            ax.U2.f fVar = ax.U2.f.V0;
            c0 c0Var = new c0(fVar);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            if (i2 == 0) {
                this.k = sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            } else {
                this.k = c0Var.a(i2, ax.p022b3.a.a().b(fVar), sharedPreferences.getString("access_token_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            }
            this.j = sharedPreferences.getString("account_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
        }

        @Override // ax.p406z3.q
        protected void r() {
            InterfaceC6228c.a aVar = this.i;
            if (aVar != null) {
                aVar.J();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            try {
                ax.Fb.i iVar = new ax.Fb.i(new ax.Fb.a(this.j, this.k));
                ax.Ib.b bVarC = iVar.c();
                if (TextUtils.isEmpty(this.j) && bVarC.c() != null) {
                    String strA = bVarC.c().a();
                    this.j = strA;
                    if (TextUtils.isEmpty(strA)) {
                        this.j = bVarC.c().b();
                    }
                    if (!TextUtils.isEmpty(this.j)) {
                        this.h.getSharedPreferences("YandexDiskPrefs", 0).edit().putString("account_name_" + this.n, this.j).apply();
                        this.m = true;
                    }
                }
                Y y = this.l;
                if (y != null) {
                    y.e0(iVar);
                }
                return Boolean.TRUE;
            } catch (ax.Hb.g e) {
                this.p = e.a() == 401;
                return Boolean.FALSE;
            } catch (Exception e2) {
                e2.printStackTrace();
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            Activity activity;
            I0 i0;
            if (this.m && (i0 = this.o) != null) {
                i0.Ja();
            }
            if (!this.p) {
                InterfaceC6228c.a aVar = this.i;
                if (aVar != null) {
                    aVar.d0(bool.booleanValue(), null);
                    return;
                }
                return;
            }
            Y y = this.l;
            if (y != null && (activity = this.q) != null) {
                y.h0(activity, this.o, this.j, this.n, this.i);
                return;
            }
            InterfaceC6228c.a aVar2 = this.i;
            if (aVar2 != null) {
                aVar2.d0(false, null);
            }
        }
    }

    public static class e extends T {
        Context a;

        class a implements ValueCallback<Boolean> {
            final /* synthetic */ Activity a;
            final /* synthetic */ ax.p182l3.k b;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.file.Y$e$a$a, reason: collision with other inner class name */
            class C0537a implements ax.o3.b.d {
                C0537a() {
                }

                @Override // ax.o3.b.d
                public void a() {
                }

                @Override // ax.o3.b.d
                public void b(Bundle bundle) {
                    CookieSyncManager.getInstance().sync();
                    String string = bundle.getString("access_token");
                    if (!Y.c0(string, bundle.getString("expires_in"))) {
                        Y.l.severe("OAUTH : FAILED TO RECEIVE ACCESS TOKEN");
                        ax.p182l3.k kVar = a.this.b;
                        if (kVar != null) {
                            kVar.c(ax.U2.f.V0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                            return;
                        }
                        return;
                    }
                    int iL = e.this.l();
                    e.this.n(iL, string, HttpUrl.FRAGMENT_ENCODE_SET);
                    ax.p182l3.k kVar2 = a.this.b;
                    if (kVar2 != null) {
                        kVar2.d(ax.U2.f.V0, iL);
                    }
                }

                @Override // ax.o3.b.d
                public void c(ax.p230o3.c cVar) {
                    Y.l.severe("OAUTH ERROR :" + cVar.getMessage());
                    ax.p182l3.k kVar = a.this.b;
                    if (kVar != null) {
                        kVar.c(ax.U2.f.V0, HttpUrl.FRAGMENT_ENCODE_SET, 0, HttpUrl.FRAGMENT_ENCODE_SET, null);
                    }
                }
            }

            a(Activity activity, ax.p182l3.k kVar) {
                this.a = activity;
                this.b = kVar;
            }

            @Override // android.webkit.ValueCallback
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onReceiveValue(Boolean bool) {
                Y.f0(this.a, null, new C0537a());
            }
        }

        public e(Context context) {
            this.a = context;
        }

        private void o(Activity activity, ax.p182l3.k kVar) {
            CookieSyncManager.createInstance(activity);
            ax.p406z3.B.X(new a(activity, kVar));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("YandexDiskPrefs", 0).edit().remove("version_" + i).remove("access_token_" + i).remove("account_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("YandexDiskPrefs", 0);
            String string = sharedPreferences.getString("account_name_" + i, null);
            ax.U2.f fVar = ax.U2.f.V0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), string, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("YandexDiskPrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("YandexDiskPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, ax.p182l3.k kVar) {
            kVar.a(ax.U2.f.V0);
            o(aVar, kVar);
        }

        int l() {
            return this.a.getSharedPreferences("YandexDiskPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> m() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("YandexDiskPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("access_token_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void n(int i, String str, String str2) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("YandexDiskPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            ax.U2.f fVar = ax.U2.f.V0;
            c0 c0Var = new c0(fVar);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("access_token_" + i, c0Var.e(ax.p022b3.a.a().b(fVar), str)).putString("account_name_" + i, str2).putString("location_name_" + i, fVar.L(this.a));
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

    private static void W(AbstractC6239n abstractC6239n, InterfaceC2462c interfaceC2462c) throws C1957a, ax.p070e3.t {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        g0(interfaceC2462c);
    }

    private C1966j X(String str, Exception exc) {
        if (exc instanceof C1966j) {
            return (C1966j) exc;
        }
        if (!(exc instanceof ax.Hb.g)) {
            return C1960d.b(str, exc);
        }
        ax.Hb.g gVar = (ax.Hb.g) exc;
        int iA = gVar.a();
        if (iA != 401) {
            if (iA == 409) {
                return new C1962f(false, exc);
            }
            if (iA == 413) {
                return new ax.p070e3.D(exc);
            }
            if (iA != 429 && iA != 500) {
                if (iA == 507) {
                    return new ax.p070e3.s(exc);
                }
                if (iA != 403) {
                    if (iA == 404) {
                        return new ax.p070e3.t(exc);
                    }
                    switch (iA) {
                        case 502:
                        case 503:
                        case 504:
                            break;
                        default:
                            return new C1966j(exc);
                    }
                }
            }
            return new ax.p070e3.y(String.valueOf(gVar.a()), exc);
        }
        return new C1961e(exc);
    }

    private void Y(AbstractC6239n abstractC6239n) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        try {
            i0(a0().b(abstractC6239n.E(), false), null);
        } catch (C1957a e2) {
            throw new C1966j(e2);
        } catch (Exception e3) {
            throw X("yandex deleteFile", e3);
        }
    }

    private static String Z(Context context) {
        String strA = C2001g.c(context).a(ax.U2.f.V0);
        if ("ru".equalsIgnoreCase(Locale.getDefault().getLanguage())) {
            return "https://oauth.yandex.ru/authorize?response_type=token&client_id=" + strA;
        }
        return "https://oauth.yandex.com/authorize?response_type=token&client_id=" + strA;
    }

    private ax.Fb.i a0() throws C1964h {
        ax.Fb.i iVar = this.h;
        if (!this.i || iVar == null) {
            throw new C1964h("Not connected : yandex");
        }
        return iVar;
    }

    public static e b0(Context context) {
        if (m == null) {
            m = new e(context.getApplicationContext());
        }
        return m;
    }

    static boolean c0(String str, String str2) {
        long jCurrentTimeMillis = (str2 == null || str2.equals("0")) ? 0L : System.currentTimeMillis() + (((long) Integer.parseInt(str2)) * 1000);
        if (str != null) {
            return jCurrentTimeMillis == 0 || System.currentTimeMillis() < jCurrentTimeMillis;
        }
        return false;
    }

    private static void d0(ax.p182l3.i iVar, long j) {
        if (iVar != null) {
            iVar.a(j, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0(ax.Fb.i iVar) {
        this.h = iVar;
        this.i = iVar != null;
        this.k = null;
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    static void f0(Activity activity, String str, ax.o3.b.d dVar) {
        String strZ = Z(activity);
        if (!TextUtils.isEmpty(str)) {
            strZ = strZ + "&login_hint=" + str;
        }
        try {
            ax.p230o3.b.D(activity, strZ, null, null, null, "filemanager-plus-yandex-disk://yandex.com/redirect", dVar).show();
        } catch (AndroidRuntimeException unused) {
            Toast.makeText(activity, R.string.error, 1).show();
        } catch (NullPointerException e2) {
            ax.p302sb.c.h().d("WEBVIEW CREATE").m(e2).i();
            Toast.makeText(activity, R.string.error, 1).show();
        }
    }

    private static void g0(InterfaceC2462c interfaceC2462c) throws C1957a {
        if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
            throw new C1957a();
        }
    }

    private void i0(ax.Ib.c cVar, InterfaceC2462c interfaceC2462c) throws Exception {
        ax.Ib.d dVarG;
        if (cVar == null || cVar == ax.Ib.c.e || cVar.b() == ax.Ib.c.b.done || TextUtils.isEmpty(cVar.a())) {
            return;
        }
        while (true) {
            g0(interfaceC2462c);
            dVarG = a0().g(cVar);
            if (!dVarG.b()) {
                break;
            }
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                throw new IOException(e2);
            }
        }
        if (dVarG.c()) {
            return;
        }
        throw new C1966j("Yandex operation failed: " + dVarG.a());
    }

    private void j0(AbstractC6239n abstractC6239n, ax.p086f3.G g, long j, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        g0(interfaceC2462c);
        try {
            a0().m(a0().i(abstractC6239n.E(), z), new a(g, j), new b(iVar, interfaceC2462c));
        } catch (Exception e2) {
            if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                throw new C1957a(e2);
            }
            if (!(e2 instanceof IOException) || !(e2.getCause() instanceof C1966j)) {
                throw X("yandex writeFile", e2);
            }
            throw ((C1966j) e2.getCause());
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean P() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l2, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        j0(abstractC6239n, g, j, true, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.i && this.h != null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        AbstractC6239n abstractC6239n;
        String strU = d0.U(str);
        boolean zEquals = d0.a.equals(strU);
        if (zEquals && (abstractC6239n = this.k) != null) {
            return abstractC6239n;
        }
        try {
            Z z = new Z(this, a0().h(new ax.Fb.g.b().d(strU).e("S").a()), strU);
            if (!zEquals) {
                return z;
            }
            this.k = z;
            return z;
        } catch (ax.Hb.g e2) {
            if (e2.a() == 404) {
                return new Z(this, null, strU);
            }
            throw X("yandex getFileInfo", e2);
        } catch (Exception e3) {
            throw X("yandex getFileInfo", e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        this.i = false;
        this.k = null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x003b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:11:0x003c A[Catch: Exception -> 0x004d, TryCatch #0 {Exception -> 0x004d, blocks: (B:4:0x0003, B:7:0x000c, B:14:0x0043, B:8:0x0016, B:11:0x003c), top: B:17:0x0003 }] */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        String strDecode;
        ax.Ib.e eVarH;
        if (str3 != null) {
            try {
                if (str3.startsWith("url=")) {
                    strDecode = Uri.decode(str3.substring(4));
                } else {
                    eVarH = a0().h(new ax.Fb.g.b().d(str2).e("S").a());
                    if (TextUtils.isEmpty(eVarH.h())) {
                        return null;
                    }
                    strDecode = eVarH.h();
                }
            } catch (Exception unused) {
                return null;
            }
        } else {
            eVarH = a0().h(new ax.Fb.g.b().d(str2).e("S").a());
            if (TextUtils.isEmpty(eVarH.h())) {
                return null;
            }
            strDecode = eVarH.h();
        }
        if (strDecode == null) {
            return null;
        }
        return a0().f(strDecode, 0L);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        ax.Ib.f fVarI;
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        C2461b.c(abstractC6239n.isDirectory());
        ArrayList arrayList = new ArrayList();
        int size = 0;
        do {
            try {
                fVarI = a0().h(new ax.Fb.g.b().d(abstractC6239n.E()).b(1000).c(Integer.valueOf(size)).e("S").a()).i();
                List<ax.Ib.e> listA = fVarI == null ? null : fVarI.a();
                if (listA == null || listA.isEmpty()) {
                    break;
                    break;
                }
                for (ax.Ib.e eVar : listA) {
                    if (eVar != null && eVar.g() != null) {
                        arrayList.add(new Z(this, eVar, null));
                    }
                }
                size += listA.size();
            } catch (Exception e2) {
                throw X("yandex listChildren", e2);
            }
        } while (fVarI.b() > size);
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        String strG0;
        if (abstractC6239n instanceof Z) {
            Z z = (Z) abstractC6239n;
            if (!z.h0() || (strG0 = z.g0()) == null) {
                return null;
            }
            return ax.p086f3.B.a0(abstractC6239n, "url=" + Uri.encode(strG0));
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        Y(abstractC6239n);
    }

    void h0(Activity activity, Fragment fragment, String str, int i, InterfaceC6228c.a aVar) {
        ax.p406z3.B.X(new c(activity, str, i, fragment, aVar));
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        try {
            return a0().e(abstractC6239n.E(), j);
        } catch (Exception e2) {
            throw X("yandex getInputStream", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        W(abstractC6239n, interfaceC2462c);
        C2461b.a(abstractC6239n2.o());
        try {
            ax.Ib.c cVarL = a0().l(abstractC6239n.E(), abstractC6239n2.E(), false);
            if (cVarL == null || cVarL.a() == null) {
                throw new C1966j("response body null error");
            }
            i0(cVarL, interfaceC2462c);
            d0(iVar, abstractC6239n.p());
        } catch (C1957a e2) {
            throw e2;
        } catch (Exception e3) {
            throw X("yandex moveFile", e3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0038  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        InterfaceC6228c.a aVar2;
        Exception exc;
        try {
            ax.p406z3.q<Object, Void, Boolean> qVar = this.j;
            if (qVar != null) {
                try {
                    if (!qVar.isCancelled()) {
                        this.j.e();
                    }
                } catch (Exception e2) {
                    exc = e2;
                    aVar2 = aVar;
                    if (aVar2 != null) {
                        aVar2.J();
                        aVar2.d0(false, exc.getMessage());
                    }
                }
            }
            aVar2 = aVar;
            try {
                d dVar = new d(p(), activity, fragment, this, t(), aVar2);
                this.j = dVar;
                dVar.i(new Object[0]);
            } catch (Exception e3) {
                e = e3;
                exc = e;
                if (aVar2 != null) {
                    aVar2.J();
                    aVar2.d0(false, exc.getMessage());
                }
            }
        } catch (Exception e4) {
            e = e4;
            aVar2 = aVar;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l2, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        j0(abstractC6239n, g, j, false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        W(abstractC6239n, interfaceC2462c);
        try {
            ax.Ib.c cVarA = a0().a(abstractC6239n.E(), abstractC6239n2.E(), false);
            if (cVarA == null || cVarA.a() == null) {
                throw new C1966j("response body null error");
            }
            i0(cVarA, interfaceC2462c);
            d0(iVar, abstractC6239n.p());
        } catch (C1957a e2) {
            throw e2;
        } catch (Exception e3) {
            throw X("yandex copyFile", e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n.o()) {
            return false;
        }
        try {
            ax.Ib.c cVarK = a0().k(abstractC6239n.E());
            return (cVarK == null || cVarK.a() == null) ? false : true;
        } catch (Exception unused) {
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
        Y(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        try {
            ax.Ib.b bVarC = a0().c();
            return new k0(bVarC.a(), bVarC.b());
        } catch (Exception e2) {
            throw X("yandex getStorageSpace", e2);
        }
    }
}
