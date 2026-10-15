package com.alphainventor.filemanager.file;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C2011q;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p217n6.C2170a;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import com.alphainventor.filemanager.R;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class r extends AbstractC6238m {
    private static final Logger n = Logger.getLogger("FileManager.GoogleDriveFileHelper");
    private static c o;
    private ax.V7.a h;
    private String i;
    private boolean j;
    private ConcurrentHashMap<String, AbstractC6239n> k = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> l = new ConcurrentHashMap<>();
    private e m;

    class a implements ax.M7.c {
        final /* synthetic */ InterfaceC2462c a;
        final /* synthetic */ InputStream b;
        final /* synthetic */ ax.p182l3.i c;
        final /* synthetic */ long d;

        a(InterfaceC2462c interfaceC2462c, InputStream inputStream, ax.p182l3.i iVar, long j) {
            this.a = interfaceC2462c;
            this.b = inputStream;
            this.c = iVar;
            this.d = j;
        }

        @Override // ax.M7.c
        public void a(ax.M7.b bVar) throws IOException {
            ax.p182l3.i iVar;
            if (bVar == null) {
                return;
            }
            InterfaceC2462c interfaceC2462c = this.a;
            if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                this.b.close();
            }
            int i = b.a[bVar.i().ordinal()];
            if (i != 1) {
                if (i == 2 && (iVar = this.c) != null) {
                    iVar.a(bVar.h(), this.d);
                    return;
                }
                return;
            }
            ax.p182l3.i iVar2 = this.c;
            if (iVar2 != null) {
                iVar2.a(bVar.h(), this.d);
            }
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.M7.b.EnumC0150b.values().length];
            a = iArr;
            try {
                iArr[ax.M7.b.EnumC0150b.MEDIA_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.M7.b.EnumC0150b.MEDIA_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class c extends T {
        Context a;

        public c(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("GoogleDrivePrefs", 0).edit().remove("display_name_" + i).remove("account_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("GoogleDrivePrefs", 0);
            String string = sharedPreferences.getString("display_name_" + i, null);
            String string2 = sharedPreferences.getString("account_name_" + i, null);
            String str = TextUtils.isEmpty(string2) ? string : string2;
            ax.U2.f fVar = ax.U2.f.T0;
            return new ax.p038c3.s(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), str, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("GoogleDrivePrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("GoogleDrivePrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(String str, ax.p182l3.k kVar) {
            ax.U2.f fVar = ax.U2.f.T0;
            kVar.a(fVar);
            int iM = m();
            int iL = l(str);
            if (iL >= 0) {
                iM = iL;
            }
            p(iM, str);
            kVar.d(fVar, iM);
        }

        int l(String str) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("GoogleDrivePrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (str.equals(sharedPreferences.getString("account_name_" + i2, null))) {
                    return i2;
                }
            }
            return -1;
        }

        int m() {
            return this.a.getSharedPreferences("GoogleDrivePrefs", 0).getInt("count", 0);
        }

        public Intent n(String str) {
            if (str != null) {
                return C2170a.a(new Account(str, "com.google"), null, new String[]{"com.google"}, true, null, null, null, null);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("https://www.googleapis.com/auth/drive");
            return ax.K7.a.e(this.a, arrayList).c();
        }

        public List<ax.p038c3.s> o() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("GoogleDrivePrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("account_name_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        public void p(int i, String str) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("GoogleDrivePrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("account_name_" + i, str).putString("display_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET).putString("location_name_" + i, ax.U2.f.T0.L(this.a));
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

    static class d {
        ax.W7.c a;
        String b;

        d(ax.W7.c cVar, String str) {
            this.a = cVar;
            this.b = str;
        }
    }

    private class e extends ax.p406z3.q<Object, Void, Integer> {
        private InterfaceC6228c.a h;
        String i;
        ax.p102g3.L j;
        boolean k;
        private Intent l;
        private Throwable m;

        class a implements ax.P7.r {
            final /* synthetic */ ax.P7.r a;

            a(ax.P7.r rVar) {
                this.a = rVar;
            }

            @Override // ax.P7.r
            public void a(ax.P7.p pVar) throws IOException {
                this.a.a(pVar);
                pVar.y(45000);
            }
        }

        public e(ax.p102g3.L l, String str, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.i = str;
            this.h = aVar;
            this.j = l;
            this.k = false;
            this.l = null;
        }

        private int w() {
            ax.p121h6.a cause;
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add("https://www.googleapis.com/auth/drive");
                ax.K7.a aVarE = ax.K7.a.e(r.this.p(), arrayList);
                aVarE.d(this.i);
                r.this.h = new ax.V7.a.b(new ax.Q7.e(), new ax.H7.a(), z(aVarE)).j(r.this.p().getString(R.string.app_name)).h();
                String strO = r.this.h.m().a().I("user").execute().q().o();
                SharedPreferences sharedPreferences = r.this.p().getSharedPreferences("GoogleDrivePrefs", 0);
                if (strO != null) {
                    if (!strO.equals(sharedPreferences.getString("display_name_" + r.this.t(), null))) {
                        sharedPreferences.edit().putString("display_name_" + r.this.t(), strO).apply();
                        this.k = true;
                    }
                }
                r.this.i = r.this.h.n().d("root").execute().r();
                return 0;
            } catch (ax.K7.d e) {
                e.printStackTrace();
                this.l = e.c();
                return -1;
            } catch (ExceptionInInitializerError e2) {
                e = e2;
                this.m = e;
                ax.U2.d.b(e);
                return -2;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                ax.J7.a aVar = new ax.J7.a(r.this.p());
                Account[] accountArrB = aVar.b();
                if (accountArrB == null || accountArrB.length <= 0) {
                    ax.p302sb.b bVarM = ax.p302sb.c.h().g().d("!!GoogleDriveAuth 3!!").m(e3);
                    StringBuilder sb = new StringBuilder();
                    sb.append("account not null : ");
                    sb.append(this.i != null);
                    bVarM.h(sb.toString()).i();
                    return -4;
                }
                if (aVar.a(this.i) == null) {
                    r.n.severe("Google Account '" + this.i + "' is not found in the device");
                    ax.p302sb.c.h().g().d("!!GoogleDriveAuth 1!!").m(e3).i();
                    return -3;
                }
                ax.p302sb.b bVarD = ax.p302sb.c.h().g().d("!!GoogleDriveAuth 2!!");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ex:");
                sb2.append(e3.getMessage());
                sb2.append(", account not null : ");
                sb2.append(this.i != null);
                bVarD.h(sb2.toString()).i();
                return -6;
            } catch (NoClassDefFoundError e4) {
                e = e4;
                this.m = e;
                ax.U2.d.b(e);
                return -2;
            } catch (SecurityException e5) {
                ax.p302sb.c.h().g().d("!!GoogleDriveAuth 4!!").m(e5).i();
                return -5;
            } catch (Exception e6) {
                e6.printStackTrace();
                this.m = e6;
                if ("NetworkError".equals(e6.getMessage())) {
                    return -9;
                }
                ax.p302sb.c.h().g().d("!!GoogleDriveAuth 5!!").m(e6).i();
                return (!(e6 instanceof ax.K7.b) || (cause = ((ax.K7.b) e6).getCause()) == null || !"UNREGISTERED_ON_API_CONSOLE".equals(cause.getMessage()) || ax.p406z3.B.J(r.this.p())) ? -2 : -8;
            }
        }

        private ax.P7.r z(ax.P7.r rVar) {
            return new a(rVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Integer g(Object... objArr) {
            r.this.i();
            try {
                int iW = w();
                if (iW == -6 || iW == -3 || iW == -4) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException unused) {
                    }
                    int iW2 = w();
                    if (iW2 == 0) {
                        ax.p302sb.b bVarD = ax.p302sb.c.h().g().d("GoogleDriveAuth RETRY SUCCESS");
                        StringBuilder sb = new StringBuilder();
                        sb.append("result:");
                        sb.append(iW);
                        sb.append(",acocunt not null:");
                        sb.append(this.i != null);
                        bVarD.h(sb.toString()).i();
                    } else if (iW == -6 && iW2 == -6) {
                        ax.p302sb.b bVarD2 = ax.p302sb.c.h().g().d("GoogleDriveAuth RETRY FAILURE");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("acocunt not null:");
                        sb2.append(this.i != null);
                        bVarD2.h(sb2.toString()).i();
                    }
                    iW = ((iW2 == -3 || iW2 == -4) && !ax.p006a3.Q.l0()) ? -7 : iW2;
                }
                return Integer.valueOf(iW);
            } finally {
                r.this.j();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            ax.p102g3.L l;
            if (this.k && (l = this.j) != null) {
                l.Ka();
            }
            if (num.intValue() == 0) {
                r.this.j = true;
                this.h.d0(true, null);
                return;
            }
            r.this.j = false;
            if (num.intValue() == -1) {
                this.h.d0(false, this.l);
                return;
            }
            if (num.intValue() == -2) {
                this.h.d0(false, this.m);
                return;
            }
            if (num.intValue() == -3) {
                this.h.d0(false, r.this.p().getString(R.string.google_account_not_found, this.i));
                return;
            }
            if (num.intValue() == -4 || num.intValue() == -5 || num.intValue() == -6) {
                this.h.d0(false, r.this.p().getString(R.string.could_not_access_account));
                return;
            }
            if (num.intValue() == -7) {
                this.h.d0(false, new f(this.i));
                return;
            }
            if (num.intValue() == -8) {
                this.h.d0(false, r.this.p().getString(R.string.msg_connection_failed, ax.U2.f.T0) + " : THIS APP IS NOT GENUINE");
                return;
            }
            if (num.intValue() == -9) {
                this.h.d0(false, r.this.p().getString(R.string.error_network));
            } else {
                this.h.d0(false, this.m);
            }
        }
    }

    public static class f {
        public String a;

        f(String str) {
            this.a = str;
        }
    }

    private ArrayList<d> X() throws IOException {
        String str;
        ArrayList<d> arrayList = new ArrayList<>();
        ax.V7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(id,name,parents)").L("trashed = false and mimeType = 'application/vnd.google-apps.folder'");
        do {
            try {
                ax.W7.d dVarExecute = dVarL.execute();
                for (ax.W7.c cVar : dVarExecute.o()) {
                    List<String> listV = cVar.v();
                    if (listV == null || listV.size() == 0) {
                        str = "no-parent-id";
                    } else if (listV.size() == 1) {
                        str = listV.get(0);
                    } else {
                        C2461b.g("parent:" + listV.size());
                        str = listV.get(0);
                    }
                    if (str == null || cVar.r() == null) {
                        C2461b.f();
                    } else {
                        arrayList.add(new d(cVar, str));
                    }
                }
                dVarL.K(dVarExecute.q());
            } catch (IOException e2) {
                e2.printStackTrace();
                dVarL.K(null);
            }
            if (dVarL.H() == null) {
                break;
            }
        } while (dVarL.H().length() > 0);
        return arrayList;
    }

    private String Y(ax.p086f3.E e2) {
        return Z(e2.u());
    }

    private String Z(String str) {
        return str;
    }

    private C1966j a0(String str, Exception exc) {
        List<ax.L7.a.C0130a> listO;
        if (exc instanceof ax.L7.b) {
            ax.L7.b bVar = (ax.L7.b) exc;
            int iB = bVar.b();
            if (iB == 403 && bVar.e() != null) {
                List<ax.L7.a.C0130a> listO2 = bVar.e().o();
                if (listO2 != null) {
                    Iterator<ax.L7.a.C0130a> it = listO2.iterator();
                    while (it.hasNext()) {
                        String strO = it.next().o();
                        if ("quotaExceeded".equals(strO) || "storageQuotaExceeded".equals(strO)) {
                            return new ax.p070e3.s(exc);
                        }
                        if ("forbidden".equals(strO) || "insufficientPermissions".equals(strO) || "insufficientParentPermissions".equals(strO)) {
                            return new C1961e(exc);
                        }
                    }
                }
            } else if (iB == 404 && bVar.e() != null && (listO = bVar.e().o()) != null) {
                Iterator<ax.L7.a.C0130a> it2 = listO.iterator();
                while (it2.hasNext()) {
                    if ("notFound".equals(it2.next().o())) {
                        return new ax.p070e3.t(exc);
                    }
                }
            }
        } else if (exc instanceof ax.P7.t) {
            ax.P7.t tVar = (ax.P7.t) exc;
            String strC = tVar.c();
            int iB2 = tVar.b();
            if (iB2 == 403 && "Forbidden".equalsIgnoreCase(strC)) {
                return new C1961e(exc);
            }
            if (iB2 == 404 && "Not Found".equalsIgnoreCase(strC)) {
                return new ax.p070e3.t(exc);
            }
        }
        return (exc.getMessage() == null || !exc.getMessage().startsWith("NetworkError")) ? C1960d.b(str, exc) : new ax.p070e3.q(exc);
    }

    private ax.p086f3.E b0() {
        return new ax.p086f3.E(this, "/.hidden-system-folder", true, true);
    }

    private String c0(String str) {
        return str.replace("'", "\\'");
    }

    private String d0() {
        return p().getSharedPreferences("GoogleDrivePrefs", 0).getString("account_name_" + t(), null);
    }

    private HashSet<String> e0(ArrayList<d> arrayList, String str) {
        Stack stack = new Stack();
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(str);
        stack.add(str);
        while (stack.size() > 0) {
            String str2 = (String) stack.pop();
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                d dVar = arrayList.get(i);
                i++;
                d dVar2 = dVar;
                if (dVar2.b.equals(str2)) {
                    hashSet.add(dVar2.a.r());
                    stack.add(dVar2.a.r());
                }
            }
        }
        return hashSet;
    }

    private String g0(ax.p086f3.E e2) {
        ax.W7.c cVarH0;
        String strO = (!e2.m() || (cVarH0 = e2.h0()) == null || cVarH0.w() == null) ? null : cVarH0.w().o();
        return strO == null ? e2.u() : strO;
    }

    private String h0(ax.W7.c cVar) {
        if (cVar.v() == null || cVar.v().size() <= 0) {
            return "root";
        }
        String str = cVar.v().get(0);
        return m0(str) ? "root" : str;
    }

    public static c i0(Context context) {
        if (o == null) {
            o = new c(context.getApplicationContext());
        }
        return o;
    }

    private String j0(AbstractC6239n abstractC6239n) throws C1966j {
        String strI0 = ((ax.p086f3.E) abstractC6239n).i0();
        if (strI0 == null) {
            ax.p086f3.E e2 = (ax.p086f3.E) a1(abstractC6239n.U());
            if (!e2.o()) {
                return null;
            }
            strI0 = g0(e2);
        }
        return Z(strI0);
    }

    private ax.p086f3.E k0(ax.W7.c cVar, String str, String str2, String str3) throws IOException {
        String str4;
        if (cVar.v() == null || cVar.v().size() <= 0) {
            return b0();
        }
        String str5 = cVar.v().get(0);
        if (m0(str5)) {
            return new ax.p086f3.E(this, "/");
        }
        if (str3 == null || !str3.equals(str5)) {
            str4 = this.l.get(str5);
        } else {
            str5 = str;
            str4 = str2;
        }
        if (str4 != null && this.k.containsKey(str4)) {
            return (ax.p086f3.E) this.k.get(str4);
        }
        ax.W7.c cVarExecute = this.h.n().d(str5).I("kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed").execute();
        ax.p086f3.E eK0 = k0(cVarExecute, str, str2, str3);
        String strQ = d0.Q(eK0.E(), r0(cVarExecute));
        ax.p086f3.E e2 = new ax.p086f3.E(this, eK0.u(), g0(eK0), cVarExecute, strQ);
        this.k.put(strQ, e2);
        this.l.put(e2.u(), e2.E());
        return e2;
    }

    private String l0(ArrayList<d> arrayList, String str, String str2, String str3, String str4) {
        String str5;
        StringBuilder sb = new StringBuilder();
        while (str != null) {
            if (str.equals(str4)) {
                sb.insert(0, str3);
                break;
            }
            int size = arrayList.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    str5 = str;
                    break;
                }
                d dVar = arrayList.get(i);
                i++;
                d dVar2 = dVar;
                if (str.equals(dVar2.a.r())) {
                    str5 = dVar2.b;
                    sb.insert(0, dVar2.a.u());
                    sb.insert(0, "/");
                    break;
                }
            }
            if (str5 == null) {
                C2461b.e("what case is this?");
                break;
            }
            if (str5.equals(this.i) || str5.equals("root")) {
                break;
            }
            if ("no-parent-id".equals(str5)) {
                sb.insert(0, ".hidden-system-folder");
                sb.insert(0, "/");
                break;
            }
            if (str5.equals(str)) {
                C2461b.e("what case is this?");
                break;
            }
            str = str5;
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    private boolean m0(String str) {
        if ("root".equals(str)) {
            return true;
        }
        String str2 = this.i;
        return str2 != null && str2.equals(str);
    }

    public static String n0(String str) {
        if (str == null) {
            return null;
        }
        return str.replace('/', '_');
    }

    private synchronized List<ax.W7.c> p0(String str) throws IOException {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            ax.V7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed)").L(str);
            do {
                try {
                    ax.W7.d dVarExecute = dVarL.execute();
                    Iterator<ax.W7.c> it = dVarExecute.o().iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next());
                    }
                    dVarL.K(dVarExecute.q());
                } catch (IOException e2) {
                    e2.printStackTrace();
                    dVarL.K(null);
                }
                if (dVarL.H() == null) {
                    break;
                }
            } while (dVarL.H().length() > 0);
        } catch (Throwable th) {
            throw th;
        }
        return arrayList;
    }

    private void q0(String str) {
        for (String str2 : this.k.keySet()) {
            if (str2.startsWith(str)) {
                this.k.remove(str2);
            }
        }
    }

    public static String r0(ax.W7.c cVar) {
        return n0(cVar.u());
    }

    /* JADX WARN: Code duplicated, block: B:100:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x0100 A[Catch: all -> 0x00cd, TRY_ENTER, TryCatch #8 {all -> 0x00cd, blocks: (B:42:0x00b2, B:44:0x00c0, B:47:0x00c7, B:48:0x00cc, B:63:0x00e1, B:64:0x00e8, B:78:0x0100, B:80:0x0106, B:81:0x010b, B:82:0x010c, B:83:0x0112, B:75:0x00f5, B:76:0x00fd), top: B:91:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x0115 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void s0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, boolean z, boolean z2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        Throwable th;
        Exception exc;
        ax.N7.b bVarC;
        ax.W7.c cVar = new ax.W7.c();
        cVar.H(abstractC6239n.B());
        cVar.F(str);
        if (l != null && l.longValue() > 0) {
            cVar.G(new ax.T7.i(l.longValue()));
        }
        boolean z3 = z2 && abstractC6239n.u() != null;
        InputStream inputStreamB = g.b();
        try {
            try {
                ax.P7.y yVar = new ax.P7.y(str, new BufferedInputStream(inputStreamB));
                if (j != -1) {
                    try {
                        yVar.h(j);
                    } catch (IOException e2) {
                        e = e2;
                        exc = e;
                        if (interfaceC2462c != null) {
                        }
                        throw a0("googledrive write file", exc);
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        exc = e;
                        if (interfaceC2462c != null) {
                        }
                        throw a0("googledrive write file", exc);
                    } catch (NullPointerException e4) {
                        e = e4;
                        exc = e;
                        if (interfaceC2462c != null) {
                        }
                        throw a0("googledrive write file", exc);
                    } catch (SecurityException e5) {
                        e = e5;
                        exc = e;
                        if (interfaceC2462c != null) {
                        }
                        throw a0("googledrive write file", exc);
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStreamB == 0) {
                            throw th;
                        }
                        try {
                            inputStreamB.close();
                            throw th;
                        } catch (IOException unused) {
                            throw th;
                        }
                    }
                }
                yVar.g(false);
                try {
                    if (z3) {
                        bVarC = this.h.n().g(g0((ax.p086f3.E) abstractC6239n), cVar, yVar);
                    } else {
                        String strJ0 = j0(abstractC6239n);
                        if (strJ0 == null) {
                            throw new C1966j("Dst parent not found");
                        }
                        cVar.I(Arrays.asList(strJ0));
                        bVarC = this.h.n().c(cVar, yVar);
                    }
                    if (l != null && l.longValue() > 0) {
                        bVarC.e("setModifiedDate", Boolean.TRUE);
                    }
                    ax.M7.b bVarR = bVarC.r();
                    bVarR.n(false);
                    bVarR.m(1048576);
                    bVarR.s(new a(interfaceC2462c, inputStreamB, iVar, j));
                    ax.W7.c cVar2 = (ax.W7.c) bVarC.execute();
                    if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                        throw new C1957a();
                    }
                    if (cVar2 == null) {
                        throw new C1966j("GoogleDrive insert() returns null");
                    }
                    if (inputStreamB != null) {
                        try {
                            inputStreamB.close();
                        } catch (IOException unused2) {
                        }
                    }
                } catch (IOException e6) {
                    e = e6;
                    exc = e;
                    if (interfaceC2462c != null || !interfaceC2462c.isCancelled()) {
                        throw a0("googledrive write file", exc);
                    }
                    throw new C1957a();
                } catch (IllegalArgumentException e7) {
                    e = e7;
                    exc = e;
                    if (interfaceC2462c != null) {
                    }
                    throw a0("googledrive write file", exc);
                } catch (NullPointerException e8) {
                    e = e8;
                    exc = e;
                    if (interfaceC2462c != null) {
                    }
                    throw a0("googledrive write file", exc);
                } catch (SecurityException e9) {
                    e = e9;
                    exc = e;
                    if (interfaceC2462c != null) {
                    }
                    throw a0("googledrive write file", exc);
                }
            } catch (Throwable th3) {
                th = th3;
                th = th;
                if (inputStreamB == 0) {
                    throw th;
                }
                inputStreamB.close();
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            exc = e;
            if (interfaceC2462c != null) {
            }
            throw a0("googledrive write file", exc);
        } catch (IllegalArgumentException e11) {
            e = e11;
            exc = e;
            if (interfaceC2462c != null) {
            }
            throw a0("googledrive write file", exc);
        } catch (NullPointerException e12) {
            e = e12;
            exc = e;
            if (interfaceC2462c != null) {
            }
            throw a0("googledrive write file", exc);
        } catch (SecurityException e13) {
            e = e13;
            exc = e;
            if (interfaceC2462c != null) {
            }
            throw a0("googledrive write file", exc);
        } catch (Throwable th4) {
            th = th4;
            th = th;
            if (inputStreamB == 0) {
                throw th;
            }
            inputStreamB.close();
            throw th;
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
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        s0(abstractC6239n, g, str, j, l, z, true, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        if (this.h == null) {
            throw new C1964h("Service is not connected!");
        }
        if (this.k.containsKey(str)) {
            return this.k.get(str);
        }
        if (d0.a.equals(str)) {
            return new ax.p086f3.E(this, str);
        }
        if ("/.hidden-system-folder".equals(str)) {
            return b0();
        }
        try {
            ax.p086f3.E e2 = (ax.p086f3.E) a1(d0.r(str));
            String strH = d0.h(str);
            String strC0 = c0(strH);
            try {
                if (!e2.o() || !e2.isDirectory()) {
                    return new ax.p086f3.E(this, str);
                }
                String strG0 = g0(e2);
                List<ax.W7.c> listP0 = p0("'" + strG0 + "' in parents and name = '" + strC0 + "' and trashed = false");
                if (listP0 != null && listP0.size() > 0) {
                    ax.p086f3.E e3 = new ax.p086f3.E(this, e2.u(), g0(e2), listP0.get(0), str);
                    if (!e3.isDirectory()) {
                        return e3;
                    }
                    this.k.put(str, e3);
                    this.l.put(e3.u(), e3.E());
                    return e3;
                }
                if (strH.contains("_")) {
                    List<ax.W7.c> listP1 = p0("'" + strG0 + "' in parents and trashed = false");
                    if (listP1 != null && listP1.size() > 0) {
                        for (ax.W7.c cVar : listP1) {
                            if (r0(cVar).equals(strH)) {
                                try {
                                    ax.p086f3.E e4 = new ax.p086f3.E(this, e2.u(), g0(e2), cVar, str);
                                    if (e4.isDirectory()) {
                                        this.k.put(str, e4);
                                        this.l.put(e4.u(), e4.E());
                                    }
                                    return e4;
                                } catch (IOException e5) {
                                    e = e5;
                                    Exception exc = e;
                                    exc.printStackTrace();
                                    throw a0("GoogleDrive getFileInfo", exc);
                                } catch (IllegalArgumentException e6) {
                                    e = e6;
                                    Exception exc2 = e;
                                    exc2.printStackTrace();
                                    throw a0("GoogleDrive getFileInfo", exc2);
                                } catch (NullPointerException e7) {
                                    e = e7;
                                    NullPointerException nullPointerException = e;
                                    ax.p302sb.c.h().g().b("GoogleDrive queryFiles null").m(nullPointerException).i();
                                    throw a0("GoogleDrive getFileInfo null", nullPointerException);
                                } catch (SecurityException e8) {
                                    e = e8;
                                    Exception exc3 = e;
                                    exc3.printStackTrace();
                                    throw a0("GoogleDrive getFileInfo", exc3);
                                }
                            }
                        }
                    }
                }
                return new ax.p086f3.E(this, e2.u(), g0(e2), str);
            } catch (IOException e9) {
                e = e9;
            } catch (IllegalArgumentException e10) {
                e = e10;
            } catch (NullPointerException e11) {
                e = e11;
            } catch (SecurityException e12) {
                e = e12;
            }
        } catch (IOException e13) {
            e = e13;
        } catch (IllegalArgumentException e14) {
            e = e14;
        } catch (NullPointerException e15) {
            e = e15;
        } catch (SecurityException e16) {
            e = e16;
        }
        Exception exc4 = e;
        exc4.printStackTrace();
        throw a0("GoogleDrive getFileInfo", exc4);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        this.k.clear();
        this.l.clear();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0027 A[Catch: IOException -> 0x0016, j -> 0x0018, TryCatch #2 {j -> 0x0018, IOException -> 0x0016, blocks: (B:4:0x0003, B:7:0x000c, B:16:0x002b, B:12:0x001a, B:15:0x0027), top: B:20:0x0003 }] */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        String strDecode;
        ax.p086f3.E e2;
        if (str3 != null) {
            try {
                if (str3.startsWith("url=")) {
                    strDecode = Uri.decode(str3.substring(4));
                } else {
                    e2 = (ax.p086f3.E) a1(str2);
                    if (e2.j0() == null) {
                        return null;
                    }
                    strDecode = e2.j0();
                }
            } catch (C1966j e3) {
                e = e3;
                e.printStackTrace();
                return null;
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                return null;
            }
        } else {
            e2 = (ax.p086f3.E) a1(str2);
            if (e2.j0() == null) {
                return null;
            }
            strDecode = e2.j0();
        }
        ax.P7.s sVarB = this.h.e().a(new ax.P7.g(strDecode)).b();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        sVarB.b(byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
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
        if ("/.hidden-system-folder".equals(abstractC6239n.E())) {
            throw new C1961e();
        }
        C2461b.c(abstractC6239n.isDirectory());
        List<AbstractC6239n> listF0 = f0((ax.p086f3.E) abstractC6239n);
        for (AbstractC6239n abstractC6239n2 : listF0) {
            if (abstractC6239n2.isDirectory()) {
                this.k.put(abstractC6239n2.E(), abstractC6239n2);
                this.l.put(abstractC6239n2.u(), abstractC6239n2.E());
            }
        }
        return listF0;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    public List<AbstractC6239n> f0(ax.p086f3.E e2) throws C1966j {
        ArrayList arrayList = new ArrayList();
        String strG0 = g0(e2);
        if (a()) {
            try {
                ax.V7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed)").L("'" + strG0 + "' in parents and trashed = false");
                do {
                    ax.W7.d dVarExecute = dVarL.execute();
                    for (ax.W7.c cVar : dVarExecute.o()) {
                        try {
                            arrayList.add(new ax.p086f3.E(this, e2.u(), g0(e2), cVar, d0.Q(e2.E(), r0(cVar))));
                        } catch (IOException e3) {
                            e = e3;
                            throw a0("GD getChildList", e);
                        } catch (IllegalArgumentException e4) {
                            e = e4;
                            Throwable th = e;
                            ax.p302sb.c.h().g().d("GoogleDrive: getChildFileList").m(th).i();
                            throw new C1966j(th);
                        } catch (NullPointerException e5) {
                            e = e5;
                            Throwable th2 = e;
                            ax.p302sb.c.h().g().d("GoogleDrive: getChildFileList").m(th2).i();
                            throw new C1966j(th2);
                        } catch (OutOfMemoryError e6) {
                            e = e6;
                            OutOfMemoryError outOfMemoryError = e;
                            ax.p302sb.c.h().g().d("GoogleDrive: getChildFileList : OOM").m(outOfMemoryError).i();
                            throw new C1966j(outOfMemoryError);
                        } catch (SecurityException e7) {
                            e = e7;
                            throw a0("GD getChildList", e);
                        }
                    }
                    dVarL.K(dVarExecute.q());
                    if (dVarL.H() == null) {
                        break;
                    }
                } while (dVarL.H().length() > 0);
            } catch (IOException e8) {
                e = e8;
                throw a0("GD getChildList", e);
            } catch (IllegalArgumentException e9) {
                e = e9;
                Throwable th3 = e;
                ax.p302sb.c.h().g().d("GoogleDrive: getChildFileList").m(th3).i();
                throw new C1966j(th3);
            } catch (NullPointerException e10) {
                e = e10;
                Throwable th4 = e;
                ax.p302sb.c.h().g().d("GoogleDrive: getChildFileList").m(th4).i();
                throw new C1966j(th4);
            } catch (OutOfMemoryError e11) {
                e = e11;
            } catch (SecurityException e12) {
                e = e12;
                throw a0("GD getChildList", e);
            }
        }
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        ax.p086f3.E e2 = (ax.p086f3.E) abstractC6239n;
        if (e2.j0() == null) {
            return null;
        }
        String str = "url=" + Uri.encode(e2.j0());
        if (ax.p086f3.A.J(abstractC6239n)) {
            return ax.p086f3.B.a0(abstractC6239n, str);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        if (this.h == null) {
            throw new C1964h("Service is not connected!");
        }
        try {
            if (!abstractC6239n.o()) {
                throw new ax.p070e3.t();
            }
            q0(abstractC6239n.E());
            ax.W7.c cVar = new ax.W7.c();
            cVar.J(Boolean.TRUE);
            this.h.n().f(abstractC6239n.u(), cVar).execute();
        } catch (IOException e2) {
            e2.printStackTrace();
            throw a0("GoogleDrive deleteFileRecursively", e2);
        } catch (SecurityException e3) {
            throw new C1966j(e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        if (this.h == null) {
            throw new C1964h("Service is not connected!");
        }
        try {
            if (((ax.p086f3.E) abstractC6239n).h0() == null) {
                throw new C1966j("The file doesn't have any content stored on Drive : " + abstractC6239n.o());
            }
            ax.V7.a.c.C0227c c0227cD = this.h.n().d(g0((ax.p086f3.E) abstractC6239n));
            if (j != 0) {
                c0227cD.s().P("bytes=" + j + "-");
            }
            return c0227cD.m();
        } catch (IOException e2) {
            e = e2;
            throw a0("googledrive getInputstream", e);
        } catch (IllegalArgumentException e3) {
            throw new C1966j(e3);
        } catch (SecurityException e4) {
            e = e4;
            throw a0("googledrive getInputstream", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        if (abstractC6239n.isDirectory()) {
            q0(abstractC6239n.E());
        }
        try {
            long jP = abstractC6239n.p();
            ax.W7.c cVar = new ax.W7.c();
            cVar.G(new ax.T7.i(abstractC6239n.q()));
            cVar.H(abstractC6239n2.B());
            ax.V7.a.c.e eVarF = this.h.n().f(Y((ax.p086f3.E) abstractC6239n), cVar);
            if (!abstractC6239n.U().equals(abstractC6239n2.U())) {
                String strJ0 = j0(abstractC6239n);
                String strJ1 = j0(abstractC6239n2);
                if (strJ1 == null) {
                    throw new C1966j("Target parent does not exist");
                }
                if (strJ0 == null) {
                    throw new C1966j("Source parent does not exist");
                }
                eVarF.I(strJ1);
                eVarF.K(strJ0);
            }
            eVarF.J("name");
            if (eVarF.execute() == null) {
                throw new C1966j("result is null");
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (IOException e2) {
            e = e2;
            throw a0("GD moveFile", e);
        } catch (SecurityException e3) {
            e = e3;
            throw a0("GD moveFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        aVar.J();
        e eVar = new e((ax.p102g3.L) fragment, d0(), aVar);
        eVar.i(new Object[0]);
        this.m = eVar;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        C2461b.a(abstractC6239n.o());
        s0(abstractC6239n, g, str, j, l, z, false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    synchronized void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        List<AbstractC6239n> arrayList;
        List<AbstractC6239n> listH;
        r rVar = this;
        synchronized (this) {
            try {
                if (!z2) {
                    o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
                    return;
                }
                try {
                    String strC0 = rVar.c0(str);
                    if (d0.E(abstractC6239n)) {
                        arrayList = rVar.o0("name contains '" + strC0 + "' and trashed = false", null, null, null, null);
                    } else {
                        List<AbstractC6239n> listG = ax.p038c3.b.k().g(abstractC6239n);
                        if (listG != null && (listH = ax.p086f3.B.h(C2011q.g(new ArrayList(listG), C2011q.c("Search")), str, z, false)) != null && !listH.isEmpty()) {
                            hVar.i0(listH, false);
                        }
                        String strE = abstractC6239n.E();
                        String strU = abstractC6239n.u();
                        String strG0 = rVar.g0((ax.p086f3.E) abstractC6239n);
                        ArrayList<d> arrayListX = rVar.X();
                        HashSet<String> hashSetE0 = rVar.e0(arrayListX, strG0);
                        StringBuilder sb = new StringBuilder();
                        boolean z3 = true;
                        int i = 0;
                        for (String str2 : hashSetE0) {
                            try {
                                if (z3) {
                                    z3 = false;
                                } else {
                                    sb.append(" or ");
                                }
                                sb.append("'");
                                sb.append(str2);
                                sb.append("' in parents");
                                i++;
                                if (i >= 300) {
                                    String str3 = "name contains '" + strC0 + "' and (" + sb.toString() + ") and trashed = false";
                                    ArrayList arrayList2 = new ArrayList();
                                    arrayList2.addAll(rVar.o0(str3, arrayListX, strU, strE, strG0));
                                    List<AbstractC6239n> listH2 = ax.p086f3.B.h(arrayList2, null, z, false);
                                    if (listH2 != null && !listH2.isEmpty()) {
                                        hVar.i0(listH2, false);
                                    }
                                    sb.setLength(0);
                                    z3 = true;
                                    i = 0;
                                }
                                rVar = this;
                            } catch (IOException e2) {
                                e = e2;
                                rVar = this;
                                throw rVar.a0("do search", e);
                            } catch (SecurityException e3) {
                                e = e3;
                                rVar = this;
                                throw rVar.a0("do search", e);
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                        arrayList = new ArrayList<>();
                        if (sb.length() > 0) {
                            rVar = this;
                            arrayList.addAll(rVar.o0("name contains '" + strC0 + "' and (" + sb.toString() + ") and trashed = false", arrayListX, strU, strE, strG0));
                        } else {
                            rVar = this;
                        }
                    }
                    if (arrayList == null) {
                        return;
                    }
                    hVar.i0(ax.p086f3.B.h(arrayList, null, z, false), true);
                    return;
                } catch (IOException e4) {
                    e = e4;
                } catch (SecurityException e5) {
                    e = e5;
                }
                throw rVar.a0("do search", e);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t("not existing source file");
        }
        ax.p086f3.E e2 = (ax.p086f3.E) abstractC6239n;
        ax.W7.c cVar = new ax.W7.c();
        long jQ = abstractC6239n.q();
        if (jQ >= 0) {
            cVar.G(new ax.T7.i(jQ));
        }
        cVar.H(abstractC6239n2.B());
        if (!abstractC6239n.U().equals(abstractC6239n2.U())) {
            String strJ0 = j0(abstractC6239n2);
            if (strJ0 == null) {
                throw new C1966j("Dst parent not found");
            }
            cVar.I(Arrays.asList(strJ0));
        }
        try {
            long jP = abstractC6239n.p();
            if (this.h.n().a(Y(e2), cVar).execute() == null) {
                throw new C1966j("GoogleDrive copy returns null");
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (IOException e3) {
            e = e3;
            throw a0("GD copyFile", e);
        } catch (SecurityException e4) {
            e = e4;
            throw a0("GD copyFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void n(AbstractC6239n abstractC6239n) throws C1966j {
        ax.W7.c cVarH0;
        if (!(abstractC6239n instanceof ax.p086f3.E)) {
            throw new C1966j("Invalid File Type :" + abstractC6239n.getClass().getName());
        }
        try {
            if (!abstractC6239n.m() || (cVarH0 = ((ax.p086f3.E) abstractC6239n).h0()) == null || cVarH0.w() == null) {
                return;
            }
            ax.W7.c cVarExecute = this.h.n().d(cVarH0.w().o()).I("size").execute();
            if (cVarExecute == null || cVarExecute.z() == null) {
                return;
            }
            ((ax.p086f3.E) abstractC6239n).m0(cVarExecute.z().longValue());
        } catch (IOException e2) {
            throw new C1966j(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        try {
            ax.p086f3.E e2 = (ax.p086f3.E) a1(abstractC6239n.U());
            if (!e2.o() || abstractC6239n.o()) {
                return false;
            }
            ax.W7.c cVar = new ax.W7.c();
            cVar.H(abstractC6239n.B());
            cVar.F("application/vnd.google-apps.folder");
            cVar.I(Arrays.asList(g0(e2)));
            ax.W7.c cVarExecute = this.h.n().b(cVar).execute();
            if (cVarExecute == null) {
                return false;
            }
            String strE = abstractC6239n.E();
            try {
                ax.p086f3.E e3 = new ax.p086f3.E(this, e2.u(), g0(e2), cVarExecute, strE);
                this.k.put(strE, e3);
                this.l.put(e3.u(), e3.E());
                return true;
            } catch (C1966j e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            } catch (SecurityException e6) {
                e = e6;
            }
        } catch (C1966j | IOException | SecurityException e7) {
            e = e7;
        }
        e.printStackTrace();
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:42:0x00b4 A[EDGE_INSN: B:42:0x00b4->B:37:0x00b4 BREAK  A[LOOP:0: B:3:0x001d->B:44:?], SYNTHETIC] */
    public List<AbstractC6239n> o0(String str, ArrayList<d> arrayList, String str2, String str3, String str4) throws IOException {
        boolean z;
        ArrayList arrayList2 = new ArrayList();
        ax.V7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed)").L(str);
        do {
            try {
                ax.W7.d dVarExecute = dVarL.execute();
                for (ax.W7.c cVar : dVarExecute.o()) {
                    String strH0 = h0(cVar);
                    String strE = (str4 == null || !str4.equals(strH0)) ? null : str3;
                    if (strE == null && arrayList != null) {
                        strE = l0(arrayList, strH0, str2, str3, str4);
                        if (strE != null) {
                            strH0 = strH0;
                            z = true;
                        } else {
                            strH0 = strH0;
                            z = false;
                        }
                        C2461b.c(z);
                    }
                    if (strE == null) {
                        try {
                            strE = k0(cVar, str2, str3, str4).E();
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                            dVarL.K(null);
                            if (dVarL.H() != null) {
                                break;
                            }
                            return arrayList2;
                        }
                    }
                    arrayList2.add(new ax.p086f3.E(this, strH0, strH0, cVar, d0.Q(strE, r0(cVar))));
                }
                dVarL.K(dVarExecute.q());
            } catch (IOException e3) {
                e = e3;
            }
            if (dVarL.H() != null) {
                break;
                break;
            }
        } while (dVarL.H().length() > 0);
        return arrayList2;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        try {
            ax.p086f3.E e2 = (ax.p086f3.E) a1(abstractC6239n.U());
            if (!e2.o() || abstractC6239n.o()) {
                return false;
            }
            ax.W7.c cVar = new ax.W7.c();
            cVar.H(abstractC6239n.B());
            cVar.F(abstractC6239n.t());
            cVar.I(Arrays.asList(g0(e2)));
            return this.h.n().b(cVar).execute() != null;
        } catch (C1966j | IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        g1(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        try {
            ax.W7.a aVarExecute = this.h.m().a().I("storageQuota").execute();
            if (aVarExecute == null || aVarExecute.o() == null) {
                throw new C1966j("No storage Quota");
            }
            Long lO = aVarExecute.o().o();
            Long lQ = aVarExecute.o().q();
            if (lO != null && lQ != null) {
                return new k0(lO.longValue(), lQ.longValue());
            }
            throw new C1966j("no total :" + lO + "," + lQ);
        } catch (IOException e2) {
            e = e2;
            throw a0("GD getStorageSpace", e);
        } catch (SecurityException e3) {
            e = e3;
            throw a0("GD getStorageSpace", e);
        }
    }
}
