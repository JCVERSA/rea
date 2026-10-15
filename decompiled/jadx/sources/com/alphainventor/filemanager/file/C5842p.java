package com.alphainventor.filemanager.file;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.D5.C0601a;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.C0743y;
import ax.J1.V;
import ax.J1.b0;
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

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5842p extends AbstractC5837k {
    private static final Logger n = Logger.getLogger("FileManager.GoogleDriveFileHelper");
    private static c o;
    private ax.p120i7.a h;
    private String i;
    private boolean j;
    private ConcurrentHashMap<String, AbstractC5838l> k = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> l = new ConcurrentHashMap<>();
    private e m;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$a */
    class a implements ax.Z6.c {
        final /* synthetic */ ax.p045d2.c a;
        final /* synthetic */ InputStream b;
        final /* synthetic */ ax.P1.i c;
        final /* synthetic */ long d;

        a(ax.p045d2.c cVar, InputStream inputStream, ax.P1.i iVar, long j) {
            this.a = cVar;
            this.b = inputStream;
            this.c = iVar;
            this.d = j;
        }

        @Override // ax.Z6.c
        public void a(ax.Z6.b bVar) throws IOException {
            ax.P1.i iVar;
            if (bVar == null) {
                return;
            }
            ax.p045d2.c cVar = this.a;
            if (cVar != null && cVar.isCancelled()) {
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
            ax.P1.i iVar2 = this.c;
            if (iVar2 != null) {
                iVar2.a(bVar.h(), this.d);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$b */
    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.Z6.b.EnumC0276b.values().length];
            a = iArr;
            try {
                iArr[ax.Z6.b.EnumC0276b.MEDIA_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.Z6.b.EnumC0276b.MEDIA_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$c */
    public static class c extends N {
        Context a;

        public c(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("GoogleDrivePrefs", 0).edit().remove("display_name_" + i).remove("account_name_" + i).remove("location_name_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("GoogleDrivePrefs", 0);
            String string = sharedPreferences.getString("display_name_" + i, null);
            String string2 = sharedPreferences.getString("account_name_" + i, null);
            String str = TextUtils.isEmpty(string2) ? string : string2;
            ax.p338y1.f fVar = ax.p338y1.f.Q0;
            return new ax.G1.p(fVar, i, sharedPreferences.getString("location_name_" + i, fVar.L(this.a)), str, null, null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("GoogleDrivePrefs", 0).edit();
            editorEdit.putString("location_name_" + i, str);
            editorEdit.commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("GoogleDrivePrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        public void k(String str, ax.P1.j jVar) {
            ax.p338y1.f fVar = ax.p338y1.f.Q0;
            jVar.b(fVar);
            int iM = m();
            int iL = l(str);
            if (iL >= 0) {
                iM = iL;
            }
            p(iM, str);
            jVar.d(fVar, iM);
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
                return C0601a.a(new Account(str, "com.google"), null, new String[]{"com.google"}, true, null, null, null, null);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("https://www.googleapis.com/auth/drive");
            return ax.X6.a.e(this.a, arrayList).c();
        }

        public List<ax.G1.p> o() {
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
            editorEdit.putString("account_name_" + i, str).putString("display_name_" + i, HttpUrl.FRAGMENT_ENCODE_SET).putString("location_name_" + i, ax.p338y1.f.Q0.L(this.a));
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

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$d */
    static class d {
        ax.p134j7.c a;
        String b;

        d(ax.p134j7.c cVar, String str) {
            this.a = cVar;
            this.b = str;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$e */
    private class e extends ax.p045d2.n<Object, Void, Integer> {
        private InterfaceC5828b.a h;
        String i;
        ax.K1.B j;
        boolean k;
        private Intent l;
        private Throwable m;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$e$a */
        class a implements ax.p036c7.r {
            final /* synthetic */ ax.p036c7.r a;

            a(ax.p036c7.r rVar) {
                this.a = rVar;
            }

            @Override // ax.p036c7.r
            public void a(ax.p036c7.p pVar) throws IOException {
                this.a.a(pVar);
                pVar.y(45000);
            }
        }

        public e(ax.K1.B b, String str, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.i = str;
            this.h = aVar;
            this.j = b;
            this.k = false;
            this.l = null;
        }

        private int w() {
            ax.p328x5.a cause;
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add("https://www.googleapis.com/auth/drive");
                ax.X6.a aVarE = ax.X6.a.e(C5842p.this.n(), arrayList);
                aVarE.d(this.i);
                C5842p.this.h = new ax.i7.a.b(new ax.p050d7.e(), new ax.U6.a(), z(aVarE)).j(C5842p.this.n().getString(R.string.app_name)).h();
                String strN = C5842p.this.h.m().a().I("user").k().o().n();
                SharedPreferences sharedPreferences = C5842p.this.n().getSharedPreferences("GoogleDrivePrefs", 0);
                if (strN != null) {
                    if (!strN.equals(sharedPreferences.getString("display_name_" + C5842p.this.q(), null))) {
                        sharedPreferences.edit().putString("display_name_" + C5842p.this.q(), strN).apply();
                        this.k = true;
                    }
                }
                C5842p.this.i = C5842p.this.h.n().d("root").k().p();
                return 0;
            } catch (ax.X6.d e) {
                e.printStackTrace();
                this.l = e.c();
                return -1;
            } catch (ExceptionInInitializerError e2) {
                e = e2;
                this.m = e;
                ax.p338y1.d.b(e);
                return -2;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                ax.W6.a aVar = new ax.W6.a(C5842p.this.n());
                Account[] accountArrB = aVar.b();
                if (accountArrB == null || accountArrB.length <= 0) {
                    ax.p221pa.b bVarM = ax.p221pa.c.h().g().d("!!GoogleDriveAuth 3!!").m(e3);
                    StringBuilder sb = new StringBuilder();
                    sb.append("account not null : ");
                    sb.append(this.i != null);
                    bVarM.h(sb.toString()).i();
                    return -4;
                }
                if (aVar.a(this.i) == null) {
                    C5842p.n.severe("Google Account '" + this.i + "' is not found in the device");
                    ax.p221pa.c.h().g().d("!!GoogleDriveAuth 1!!").m(e3).i();
                    return -3;
                }
                ax.p221pa.b bVarD = ax.p221pa.c.h().g().d("!!GoogleDriveAuth 2!!");
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
                ax.p338y1.d.b(e);
                return -2;
            } catch (SecurityException e5) {
                ax.p221pa.c.h().g().d("!!GoogleDriveAuth 4!!").m(e5).i();
                return -5;
            } catch (Exception e6) {
                e6.printStackTrace();
                this.m = e6;
                if ("NetworkError".equals(e6.getMessage())) {
                    return -9;
                }
                ax.p221pa.c.h().g().d("!!GoogleDriveAuth 5!!").m(e6).i();
                return (!(e6 instanceof ax.X6.b) || (cause = ((ax.X6.b) e6).getCause()) == null || !"UNREGISTERED_ON_API_CONSOLE".equals(cause.getMessage()) || ax.p045d2.x.L(C5842p.this.n())) ? -2 : -8;
            }
        }

        private ax.p036c7.r z(ax.p036c7.r rVar) {
            return new a(rVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Integer g(Object... objArr) {
            C5842p.this.h();
            try {
                int iW = w();
                if (iW == -6 || iW == -3 || iW == -4) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException unused) {
                    }
                    int iW2 = w();
                    if (iW2 == 0) {
                        ax.p221pa.b bVarD = ax.p221pa.c.h().g().d("GoogleDriveAuth RETRY SUCCESS");
                        StringBuilder sb = new StringBuilder();
                        sb.append("result:");
                        sb.append(iW);
                        sb.append(",acocunt not null:");
                        sb.append(this.i != null);
                        bVarD.h(sb.toString()).i();
                    } else if (iW == -6 && iW2 == -6) {
                        ax.p221pa.b bVarD2 = ax.p221pa.c.h().g().d("GoogleDriveAuth RETRY FAILURE");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("acocunt not null:");
                        sb2.append(this.i != null);
                        bVarD2.h(sb2.toString()).i();
                    }
                    iW = ((iW2 == -3 || iW2 == -4) && !ax.E1.P.f0()) ? -7 : iW2;
                }
                return Integer.valueOf(iW);
            } finally {
                C5842p.this.i();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            ax.K1.B b;
            if (this.k && (b = this.j) != null) {
                b.c9();
            }
            if (num.intValue() == 0) {
                C5842p.this.j = true;
                this.h.f0(true, null);
                return;
            }
            C5842p.this.j = false;
            if (num.intValue() == -1) {
                this.h.f0(false, this.l);
                return;
            }
            if (num.intValue() == -2) {
                this.h.f0(false, this.m);
                return;
            }
            if (num.intValue() == -3) {
                this.h.f0(false, C5842p.this.n().getString(R.string.google_account_not_found, this.i));
                return;
            }
            if (num.intValue() == -4 || num.intValue() == -5 || num.intValue() == -6) {
                this.h.f0(false, C5842p.this.n().getString(R.string.could_not_access_account));
                return;
            }
            if (num.intValue() == -7) {
                this.h.f0(false, new f(this.i));
                return;
            }
            if (num.intValue() == -8) {
                this.h.f0(false, C5842p.this.n().getString(R.string.msg_connection_failed, ax.p338y1.f.Q0) + " : THIS APP IS NOT GENUINE");
                return;
            }
            if (num.intValue() == -9) {
                this.h.f0(false, C5842p.this.n().getString(R.string.error_network));
            } else {
                this.h.f0(false, this.m);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.p$f */
    public static class f {
        public String a;

        f(String str) {
            this.a = str;
        }
    }

    private ArrayList<d> R() throws IOException {
        String str;
        ArrayList<d> arrayList = new ArrayList<>();
        ax.i7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(id,name,parents)").L("trashed = false and mimeType = 'application/vnd.google-apps.folder'");
        do {
            try {
                ax.p134j7.d dVarK = dVarL.k();
                for (ax.p134j7.c cVar : dVarK.n()) {
                    List<String> listW = cVar.w();
                    if (listW == null || listW.size() == 0) {
                        str = "no-parent-id";
                    } else if (listW.size() == 1) {
                        str = listW.get(0);
                    } else {
                        ax.p045d2.b.g("parent:" + listW.size());
                        str = listW.get(0);
                    }
                    if (str == null || cVar.p() == null) {
                        ax.p045d2.b.f();
                    } else {
                        arrayList.add(new d(cVar, str));
                    }
                }
                dVarL.K(dVarK.o());
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

    private String S(C0743y c0743y) {
        return T(c0743y.t());
    }

    private String T(String str) {
        return str;
    }

    private C0718i U(String str, Exception exc) {
        List<ax.Y6.a.C0269a> listN;
        if (exc instanceof ax.Y6.b) {
            ax.Y6.b bVar = (ax.Y6.b) exc;
            int iB = bVar.b();
            if (iB == 403 && bVar.e() != null) {
                List<ax.Y6.a.C0269a> listN2 = bVar.e().n();
                if (listN2 != null) {
                    Iterator<ax.Y6.a.C0269a> it = listN2.iterator();
                    while (it.hasNext()) {
                        String strN = it.next().n();
                        if ("quotaExceeded".equals(strN) || "storageQuotaExceeded".equals(strN)) {
                            return new ax.I1.r(exc);
                        }
                        if ("forbidden".equals(strN) || "insufficientPermissions".equals(strN) || "insufficientParentPermissions".equals(strN)) {
                            return new C0713d(exc);
                        }
                    }
                }
            } else if (iB == 404 && bVar.e() != null && (listN = bVar.e().n()) != null) {
                Iterator<ax.Y6.a.C0269a> it2 = listN.iterator();
                while (it2.hasNext()) {
                    if ("notFound".equals(it2.next().n())) {
                        return new ax.I1.s(exc);
                    }
                }
            }
        } else if (exc instanceof ax.p036c7.t) {
            ax.p036c7.t tVar = (ax.p036c7.t) exc;
            String strC = tVar.c();
            int iB2 = tVar.b();
            if (iB2 == 403 && "Forbidden".equalsIgnoreCase(strC)) {
                return new C0713d(exc);
            }
            if (iB2 == 404 && "Not Found".equalsIgnoreCase(strC)) {
                return new ax.I1.s(exc);
            }
        }
        return (exc.getMessage() == null || !exc.getMessage().startsWith("NetworkError")) ? C0712c.b(str, exc) : new ax.I1.p(exc);
    }

    private C0743y V() {
        return new C0743y(this, "/.hidden-system-folder", true, true);
    }

    private String W(String str) {
        return str.replace("'", "\\'");
    }

    private String X() {
        return n().getSharedPreferences("GoogleDrivePrefs", 0).getString("account_name_" + q(), null);
    }

    private HashSet<String> Y(ArrayList<d> arrayList, String str) {
        Stack stack = new Stack();
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(str);
        stack.add(str);
        while (stack.size() > 0) {
            String str2 = (String) stack.pop();
            for (d dVar : arrayList) {
                if (dVar.b.equals(str2)) {
                    hashSet.add(dVar.a.p());
                    stack.add(dVar.a.p());
                }
            }
        }
        return hashSet;
    }

    private String a0(C0743y c0743y) {
        ax.p134j7.c cVarE0;
        String strN = (!c0743y.m() || (cVarE0 = c0743y.e0()) == null || cVarE0.x() == null) ? null : cVarE0.x().n();
        return strN == null ? c0743y.t() : strN;
    }

    private String b0(ax.p134j7.c cVar) {
        if (cVar.w() == null || cVar.w().size() <= 0) {
            return "root";
        }
        String str = cVar.w().get(0);
        return g0(str) ? "root" : str;
    }

    public static c c0(Context context) {
        if (o == null) {
            o = new c(context.getApplicationContext());
        }
        return o;
    }

    private String d0(AbstractC5838l abstractC5838l) throws C0718i {
        String strF0 = ((C0743y) abstractC5838l).f0();
        if (strF0 == null) {
            C0743y c0743y = (C0743y) V0(abstractC5838l.S());
            if (!c0743y.n()) {
                return null;
            }
            strF0 = a0(c0743y);
        }
        return T(strF0);
    }

    private C0743y e0(ax.p134j7.c cVar, String str, String str2, String str3) throws IOException {
        String str4;
        if (cVar.w() == null || cVar.w().size() <= 0) {
            return V();
        }
        String str5 = cVar.w().get(0);
        if (g0(str5)) {
            return new C0743y(this, "/");
        }
        if (str3 == null || !str3.equals(str5)) {
            str4 = this.l.get(str5);
        } else {
            str5 = str;
            str4 = str2;
        }
        if (str4 != null && this.k.containsKey(str4)) {
            return (C0743y) this.k.get(str4);
        }
        ax.p134j7.c cVarK = this.h.n().d(str5).I("kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed").k();
        C0743y c0743yE0 = e0(cVarK, str, str2, str3);
        String strM = V.M(c0743yE0.C(), l0(cVarK));
        C0743y c0743y = new C0743y(this, c0743yE0.t(), a0(c0743yE0), cVarK, strM);
        this.k.put(strM, c0743y);
        this.l.put(c0743y.t(), c0743y.C());
        return c0743y;
    }

    private String f0(ArrayList<d> arrayList, String str, String str2, String str3, String str4) {
        String str5;
        StringBuilder sb = new StringBuilder();
        while (str != null) {
            if (str.equals(str4)) {
                sb.insert(0, str3);
                break;
            }
            Iterator<d> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str5 = str;
                    break;
                }
                d next = it.next();
                if (str.equals(next.a.p())) {
                    str5 = next.b;
                    sb.insert(0, next.a.u());
                    sb.insert(0, "/");
                    break;
                }
            }
            if (str5 == null) {
                ax.p045d2.b.e("what case is this?");
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
                ax.p045d2.b.e("what case is this?");
                break;
            }
            str = str5;
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    private boolean g0(String str) {
        String str2;
        return "root".equals(str) || ((str2 = this.i) != null && str2.equals(str));
    }

    public static String h0(String str) {
        if (str == null) {
            return null;
        }
        return str.replace('/', '_');
    }

    private synchronized List<ax.p134j7.c> j0(String str) throws IOException {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            ax.i7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed)").L(str);
            do {
                try {
                    ax.p134j7.d dVarK = dVarL.k();
                    Iterator<ax.p134j7.c> it = dVarK.n().iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next());
                    }
                    dVarL.K(dVarK.o());
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

    private void k0(String str) {
        for (String str2 : this.k.keySet()) {
            if (str2.startsWith(str)) {
                this.k.remove(str2);
            }
        }
    }

    public static String l0(ax.p134j7.c cVar) {
        return h0(cVar.u());
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00ee A[Catch: all -> 0x0050, TRY_ENTER, TryCatch #0 {all -> 0x0050, blocks: (B:14:0x003c, B:16:0x004c, B:27:0x005f, B:29:0x0064, B:34:0x0093, B:36:0x009b, B:37:0x00a2, B:39:0x00c7, B:42:0x00ce, B:43:0x00d3, B:48:0x00dc, B:49:0x00e3, B:30:0x0076, B:32:0x007c, B:50:0x00e4, B:51:0x00eb, B:53:0x00ee, B:55:0x00f4, B:56:0x00f9, B:57:0x00fa, B:58:0x0100), top: B:64:0x003c }] */
    private void m0(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, boolean z, boolean z2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p008a7.b bVarC;
        ax.p134j7.c cVar2 = new ax.p134j7.c();
        cVar2.F(abstractC5838l.w());
        cVar2.D(str);
        if (l != null && l.longValue() > 0) {
            cVar2.E(new ax.p092g7.i(l.longValue()));
        }
        boolean z3 = z2 && abstractC5838l.t() != null;
        InputStream inputStreamB = a2.b();
        try {
            try {
                ax.p036c7.y yVar = new ax.p036c7.y(str, new BufferedInputStream(inputStreamB));
                if (j != -1) {
                    yVar.i(j);
                }
                yVar.h(false);
                if (z3) {
                    bVarC = this.h.n().g(a0((C0743y) abstractC5838l), cVar2, yVar);
                } else {
                    String strD0 = d0(abstractC5838l);
                    if (strD0 == null) {
                        throw new C0718i("Dst parent not found");
                    }
                    cVar2.H(Arrays.asList(strD0));
                    bVarC = this.h.n().c(cVar2, yVar);
                }
                if (l != null && l.longValue() > 0) {
                    bVarC.g("setModifiedDate", Boolean.TRUE);
                }
                ax.Z6.b bVarQ = bVarC.q();
                bVarQ.n(false);
                bVarQ.m(1048576);
                bVarQ.s(new a(cVar, inputStreamB, iVar, j));
                ax.p134j7.c cVar3 = (ax.p134j7.c) bVarC.k();
                if (cVar != null && cVar.isCancelled()) {
                    throw new C0710a();
                }
                if (cVar3 == null) {
                    throw new C0718i("GoogleDrive insert() returns null");
                }
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (Throwable th) {
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            if (cVar != null || !cVar.isCancelled()) {
                throw U("googledrive write file", e);
            }
            throw new C0710a();
        } catch (IllegalArgumentException e3) {
            e = e3;
            if (cVar != null) {
            }
            throw U("googledrive write file", e);
        } catch (NullPointerException e4) {
            e = e4;
            if (cVar != null) {
            }
            throw U("googledrive write file", e);
        } catch (SecurityException e5) {
            e = e5;
            if (cVar != null) {
            }
            throw U("googledrive write file", e);
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
    public void L(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        m0(abstractC5838l, a2, str, j, l, z, true, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        if (this.h == null) {
            throw new C0716g("Service is not connected!");
        }
        if (this.k.containsKey(str)) {
            return this.k.get(str);
        }
        if (V.a.equals(str)) {
            return new C0743y(this, str);
        }
        if ("/.hidden-system-folder".equals(str)) {
            return V();
        }
        try {
            C0743y c0743y = (C0743y) V0(V.r(str));
            String strH = V.h(str);
            String strW = W(strH);
            if (!c0743y.n() || !c0743y.isDirectory()) {
                return new C0743y(this, str);
            }
            String strA0 = a0(c0743y);
            List<ax.p134j7.c> listJ0 = j0("'" + strA0 + "' in parents and name = '" + strW + "' and trashed = false");
            if (listJ0 != null && listJ0.size() > 0) {
                C0743y c0743y2 = new C0743y(this, c0743y.t(), a0(c0743y), listJ0.get(0), str);
                if (c0743y2.isDirectory()) {
                    this.k.put(str, c0743y2);
                    this.l.put(c0743y2.t(), c0743y2.C());
                }
                return c0743y2;
            }
            if (strH.contains("_")) {
                List<ax.p134j7.c> listJ1 = j0("'" + strA0 + "' in parents and trashed = false");
                if (listJ1 != null && listJ1.size() > 0) {
                    for (ax.p134j7.c cVar : listJ1) {
                        if (l0(cVar).equals(strH)) {
                            C0743y c0743y3 = new C0743y(this, c0743y.t(), a0(c0743y), cVar, str);
                            if (c0743y3.isDirectory()) {
                                this.k.put(str, c0743y3);
                                this.l.put(c0743y3.t(), c0743y3.C());
                            }
                            return c0743y3;
                        }
                    }
                }
            }
            return new C0743y(this, c0743y.t(), a0(c0743y), str);
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            throw U("GoogleDrive getFileInfo", e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            e.printStackTrace();
            throw U("GoogleDrive getFileInfo", e);
        } catch (NullPointerException e4) {
            ax.p221pa.c.h().g().b("GoogleDrive queryFiles null").m(e4).i();
            throw U("GoogleDrive getFileInfo null", e4);
        } catch (SecurityException e5) {
            e = e5;
            e.printStackTrace();
            throw U("GoogleDrive getFileInfo", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0027 A[Catch: IOException -> 0x0016, i -> 0x0018, TryCatch #2 {i -> 0x0018, IOException -> 0x0016, blocks: (B:4:0x0003, B:7:0x000c, B:16:0x002b, B:12:0x001a, B:15:0x0027), top: B:20:0x0003 }] */
    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        String strDecode;
        C0743y c0743y;
        if (str3 != null) {
            try {
                if (str3.startsWith("url=")) {
                    strDecode = Uri.decode(str3.substring(4));
                } else {
                    c0743y = (C0743y) V0(str2);
                    if (c0743y.g0() == null) {
                        return null;
                    }
                    strDecode = c0743y.g0();
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
            c0743y = (C0743y) V0(str2);
            if (c0743y.g0() == null) {
                return null;
            }
            strDecode = c0743y.g0();
        }
        ax.p036c7.s sVarB = this.h.e().a(new ax.p036c7.g(strDecode)).b();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        sVarB.b(byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    public List<AbstractC5838l> Z(C0743y c0743y) throws C0718i {
        ArrayList arrayList = new ArrayList();
        String strA0 = a0(c0743y);
        if (a()) {
            try {
                ax.i7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed)").L("'" + strA0 + "' in parents and trashed = false");
                do {
                    ax.p134j7.d dVarK = dVarL.k();
                    for (ax.p134j7.c cVar : dVarK.n()) {
                        arrayList.add(new C0743y(this, c0743y.t(), a0(c0743y), cVar, V.M(c0743y.C(), l0(cVar))));
                    }
                    dVarL.K(dVarK.o());
                    if (dVarL.H() == null) {
                        break;
                    }
                } while (dVarL.H().length() > 0);
            } catch (IOException e2) {
                e = e2;
                throw U("GD getChildList", e);
            } catch (IllegalArgumentException e3) {
                e = e3;
                ax.p221pa.c.h().g().d("GoogleDrive: getChildFileList").m(e).i();
                throw new C0718i(e);
            } catch (NullPointerException e4) {
                e = e4;
                ax.p221pa.c.h().g().d("GoogleDrive: getChildFileList").m(e).i();
                throw new C0718i(e);
            } catch (OutOfMemoryError e5) {
                ax.p221pa.c.h().g().d("GoogleDrive: getChildFileList : OOM").m(e5).i();
                throw new C0718i(e5);
            } catch (SecurityException e6) {
                e = e6;
                throw U("GD getChildList", e);
            }
        }
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        C0743y c0743y = (C0743y) abstractC5838l;
        if (c0743y.g0() == null) {
            return null;
        }
        String str = "url=" + Uri.encode(c0743y.g0());
        if (C0739u.F(abstractC5838l)) {
            return C0740v.S(abstractC5838l, str);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        if (!abstractC5838l.n()) {
            throw new ax.I1.s("not existing source file");
        }
        C0743y c0743y = (C0743y) abstractC5838l;
        ax.p134j7.c cVar2 = new ax.p134j7.c();
        long jQ = abstractC5838l.q();
        if (jQ >= 0) {
            cVar2.E(new ax.p092g7.i(jQ));
        }
        cVar2.F(abstractC5838l2.w());
        if (!abstractC5838l.S().equals(abstractC5838l2.S())) {
            String strD0 = d0(abstractC5838l2);
            if (strD0 == null) {
                throw new C0718i("Dst parent not found");
            }
            cVar2.H(Arrays.asList(strD0));
        }
        try {
            long jO = abstractC5838l.o();
            if (this.h.n().a(S(c0743y), cVar2).k() == null) {
                throw new C0718i("GoogleDrive copy returns null");
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (IOException e2) {
            e = e2;
            throw U("GD copyFile", e);
        } catch (SecurityException e3) {
            e = e3;
            throw U("GD copyFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        this.k.clear();
        this.l.clear();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        if (this.h == null) {
            throw new C0716g("Service is not connected!");
        }
        try {
            if (!abstractC5838l.n()) {
                throw new ax.I1.s();
            }
            k0(abstractC5838l.C());
            ax.p134j7.c cVar = new ax.p134j7.c();
            cVar.I(Boolean.TRUE);
            this.h.n().f(abstractC5838l.t(), cVar).k();
        } catch (IOException e2) {
            e2.printStackTrace();
            throw U("GoogleDrive deleteFileRecursively", e2);
        } catch (SecurityException e3) {
            throw new C0718i(e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l.n());
        m0(abstractC5838l, a2, str, j, l, z, false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        if (this.h == null) {
            throw new C0716g("Service is not connected!");
        }
        try {
            if (((C0743y) abstractC5838l).e0() == null) {
                throw new C0718i("The file doesn't have any content stored on Drive : " + abstractC5838l.n());
            }
            ax.i7.a.c.C0366c c0366cD = this.h.n().d(a0((C0743y) abstractC5838l));
            if (j != 0) {
                c0366cD.s().O("bytes=" + j + "-");
            }
            return c0366cD.m();
        } catch (IOException e2) {
            e = e2;
            throw U("googledrive getInputstream", e);
        } catch (IllegalArgumentException e3) {
            throw new C0718i(e3);
        } catch (SecurityException e4) {
            e = e4;
            throw U("googledrive getInputstream", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        aVar.N();
        e eVar = new e((ax.K1.B) fragment, X(), aVar);
        eVar.i(new Object[0]);
        this.m = eVar;
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
        if ("/.hidden-system-folder".equals(abstractC5838l.C())) {
            throw new C0713d();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        List<AbstractC5838l> listZ = Z((C0743y) abstractC5838l);
        for (AbstractC5838l abstractC5838l2 : listZ) {
            if (abstractC5838l2.isDirectory()) {
                this.k.put(abstractC5838l2.C(), abstractC5838l2);
                this.l.put(abstractC5838l2.t(), abstractC5838l2.C());
            }
        }
        return listZ;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        try {
            C0743y c0743y = (C0743y) V0(abstractC5838l.S());
            if (!c0743y.n() || abstractC5838l.n()) {
                return false;
            }
            ax.p134j7.c cVar = new ax.p134j7.c();
            cVar.F(abstractC5838l.w());
            cVar.D("application/vnd.google-apps.folder");
            cVar.H(Arrays.asList(a0(c0743y)));
            ax.p134j7.c cVarK = this.h.n().b(cVar).k();
            if (cVarK == null) {
                return false;
            }
            String strC = abstractC5838l.C();
            C0743y c0743y2 = new C0743y(this, c0743y.t(), a0(c0743y), cVarK, strC);
            this.k.put(strC, c0743y2);
            this.l.put(c0743y2.t(), c0743y2.C());
            return true;
        } catch (C0718i | IOException | SecurityException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ba A[EDGE_INSN: B:41:0x00ba->B:36:0x00ba BREAK  A[LOOP:0: B:3:0x001f->B:43:?], SYNTHETIC] */
    public List<AbstractC5838l> i0(String str, ArrayList<d> arrayList, String str2, String str3, String str4) throws IOException {
        ArrayList arrayList2 = new ArrayList();
        ax.i7.a.c.d dVarL = this.h.n().e().J("nextPageToken, files(kind,id,name,mimeType,parents,capabilities/canDownload,capabilities/canEdit,size,modifiedTime,createdTime,webContentLink,thumbnailLink,webViewLink,shortcutDetails,trashed)").L(str);
        do {
            try {
                ax.p134j7.d dVarK = dVarL.k();
                for (ax.p134j7.c cVar : dVarK.n()) {
                    String strB0 = b0(cVar);
                    String strC = (str4 == null || !str4.equals(strB0)) ? null : str3;
                    if (strC == null && arrayList != null) {
                        strC = f0(arrayList, strB0, str2, str3, str4);
                        ax.p045d2.b.c(strC != null);
                    }
                    if (strC == null) {
                        try {
                            strC = e0(cVar, str2, str3, str4).C();
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
                    arrayList2.add(new C0743y(this, strB0, strB0, cVar, V.M(strC, l0(cVar))));
                }
                dVarL.K(dVarK.o());
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

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        try {
            C0743y c0743y = (C0743y) V0(abstractC5838l.S());
            if (!c0743y.n() || abstractC5838l.n()) {
                return false;
            }
            ax.p134j7.c cVar = new ax.p134j7.c();
            cVar.F(abstractC5838l.w());
            cVar.D(abstractC5838l.s());
            cVar.H(Arrays.asList(a0(c0743y)));
            return this.h.n().b(cVar).k() != null;
        } catch (C0718i | IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    synchronized void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        List<AbstractC5838l> listI0;
        try {
            try {
                String strW = W(str);
                if (V.B(abstractC5838l)) {
                    listI0 = i0("name contains '" + strW + "' and trashed = false", null, null, null, null);
                } else {
                    String strC = abstractC5838l.C();
                    String strT = abstractC5838l.t();
                    String strA0 = a0((C0743y) abstractC5838l);
                    ArrayList<d> arrayListR = R();
                    HashSet<String> hashSetY = Y(arrayListR, strA0);
                    ArrayList arrayList = new ArrayList();
                    StringBuilder sb = new StringBuilder();
                    Iterator<String> it = hashSetY.iterator();
                    loop0: while (true) {
                        boolean z2 = true;
                        int i = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                break loop0;
                            }
                            String next = it.next();
                            if (z2) {
                                z2 = false;
                            } else {
                                sb.append(" or ");
                            }
                            sb.append("'");
                            sb.append(next);
                            sb.append("' in parents");
                            i++;
                            if (i >= 300) {
                                arrayList.addAll(i0("name contains '" + strW + "' and (" + sb.toString() + ") and trashed = false", arrayListR, strT, strC, strA0));
                                sb.setLength(0);
                            }
                        }
                    }
                    if (sb.length() > 0) {
                        arrayList.addAll(i0("name contains '" + strW + "' and (" + sb.toString() + ") and trashed = false", arrayListR, strT, strC, strA0));
                    }
                    listI0 = arrayList;
                }
                if (listI0 == null) {
                    return;
                }
                hVar.l0(C0740v.e(listI0, null, z, false), true);
            } catch (Throwable th) {
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            throw U("do search", e);
        } catch (SecurityException e3) {
            e = e3;
            throw U("do search", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        if (abstractC5838l.isDirectory()) {
            k0(abstractC5838l.C());
        }
        try {
            long jO = abstractC5838l.o();
            ax.p134j7.c cVar2 = new ax.p134j7.c();
            cVar2.E(new ax.p092g7.i(abstractC5838l.q()));
            cVar2.F(abstractC5838l2.w());
            ax.i7.a.c.e eVarF = this.h.n().f(S((C0743y) abstractC5838l), cVar2);
            if (!abstractC5838l.S().equals(abstractC5838l2.S())) {
                String strD0 = d0(abstractC5838l);
                String strD1 = d0(abstractC5838l2);
                if (strD1 == null) {
                    throw new C0718i("Target parent does not exist");
                }
                if (strD0 == null) {
                    throw new C0718i("Source parent does not exist");
                }
                eVarF.I(strD1);
                eVarF.K(strD0);
            }
            eVarF.J("name");
            if (eVarF.k() == null) {
                throw new C0718i("result is null");
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (IOException e2) {
            e = e2;
            throw U("GD moveFile", e);
        } catch (SecurityException e3) {
            e = e3;
            throw U("GD moveFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public void l(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p134j7.c cVarE0;
        if (!(abstractC5838l instanceof C0743y)) {
            throw new C0718i("Invalid File Type :" + abstractC5838l.getClass().getName());
        }
        try {
            if (!abstractC5838l.m() || (cVarE0 = ((C0743y) abstractC5838l).e0()) == null || cVarE0.x() == null) {
                return;
            }
            ax.p134j7.c cVarK = this.h.n().d(cVarE0.x().n()).I("size").k();
            if (cVarK == null || cVarK.y() == null) {
                return;
            }
            ((C0743y) abstractC5838l).j0(cVarK.y().longValue());
        } catch (IOException e2) {
            throw new C0718i(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        b1(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        try {
            ax.p134j7.a aVarK = this.h.m().a().I("storageQuota").k();
            if (aVarK == null || aVarK.n() == null) {
                throw new C0718i("No storage Quota");
            }
            Long lN = aVarK.n().n();
            Long lO = aVarK.n().o();
            if (lN != null && lO != null) {
                return new b0(lN.longValue(), lO.longValue());
            }
            throw new C0718i("no total :" + lN + "," + lO);
        } catch (IOException e2) {
            e = e2;
            throw U("GD getStorageSpace", e);
        } catch (SecurityException e3) {
            e = e3;
            throw U("GD getStorageSpace", e);
        }
    }
}
