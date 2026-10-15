package com.alphainventor.filemanager.file;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStatVfs;
import android.text.TextUtils;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1966j;
import ax.p070e3.C1967k;
import ax.p086f3.C2006l;
import ax.p086f3.C2015v;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import j$.nio.channels.DesugarChannels;
import j$.util.Objects;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6232g {
    private static final Logger b = Logger.getLogger("FileManager.DocumentFileClient");
    Context a;

    C6232g(Context context) {
        this.a = context;
    }

    public static String A(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf(":")) >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public static String B(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf(":")) >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public static boolean C(Context context, ax.p086f3.K k, String str) {
        String strX;
        if (!ax.p038c3.j.F().F0(k) || str == null) {
            strX = x(context, k, str);
        } else {
            Uri uriN = n(k, str);
            strX = (uriN == null || !a(context, uriN)) ? null : uriN.toString();
        }
        if (TextUtils.isEmpty(strX)) {
            return false;
        }
        return H(context, k, str, Uri.parse(strX));
    }

    private static boolean D(ax.p086f3.K k, C2006l c2006l) {
        String strA0;
        if (ax.U2.f.g0(k.d())) {
            String strE = k.e();
            if (strE == null) {
                return false;
            }
            strA0 = d0.h(strE);
        } else if (ax.U2.f.b0(k.d())) {
            strA0 = ax.p038c3.j.F().a0(k);
        } else {
            C2461b.g("Unexpected location unit : " + k);
            strA0 = null;
        }
        String str = c2006l.b;
        if (str != null) {
            if (str.equals(strA0)) {
                return true;
            }
            String strA = A(c2006l.a);
            if (strA != null && strA.equals(strA0)) {
                return true;
            }
        }
        return false;
    }

    public static boolean E(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(":");
    }

    public static boolean F(Context context, ax.p086f3.K k, String str, Uri uri) throws Throwable {
        String strH;
        if (!ax.p006a3.Q.V1()) {
            return false;
        }
        try {
            if (k.e() == null) {
                return false;
            }
            String strH2 = ax.p006a3.v.h(uri);
            if (str != null) {
                String strZ = z(strH2);
                if (strZ == null || !strZ.equals(str)) {
                    return false;
                }
            } else if (!E(strH2)) {
                return false;
            }
            if (str != null) {
                strH = d0.h(str);
            } else if (ax.U2.f.g0(k.d())) {
                strH = d0.h(k.e());
            } else if (ax.U2.f.b0(k.d())) {
                strH = ax.p038c3.j.F().a0(k);
            } else {
                if (ax.U2.f.m0(k.d())) {
                    return false;
                }
                strH = HttpUrl.FRAGMENT_ENCODE_SET;
            }
            C2006l c2006lI = i(context, c(uri), strH);
            if (c2006lI == null) {
                return false;
            }
            if (str == null) {
                return D(k, c2006lI);
            }
            String strB = B(strH2);
            if (k == ax.p086f3.K.e) {
                return "primary".equals(strB);
            }
            if (k == ax.p086f3.K.f) {
                String strH3 = d0.h(k.e());
                return strH3 != null && strH3.equals(strB);
            }
            C2461b.f();
            return false;
        } catch (C1966j unused) {
        }
    }

    public static boolean G(Context context, ax.p086f3.K k, Uri uri) {
        ax.c3.j.f fVarM;
        C2006l c2006lI;
        if (!ax.p006a3.Q.V1()) {
            return false;
        }
        try {
            if (k.e() == null || (fVarM = ax.p038c3.j.F().M(k)) == null) {
                return false;
            }
            Uri uri2 = fVarM.b;
            if (uri2 != null) {
                return uri2.equals(uri);
            }
            String lastPathSegment = fVarM.a.getLastPathSegment();
            String lastPathSegment2 = uri.getLastPathSegment();
            if (lastPathSegment2 == null || !lastPathSegment2.equals(lastPathSegment) || (c2006lI = i(context, c(uri), null)) == null) {
                return false;
            }
            ax.p038c3.j.F().V0(k, uri, c2006lI.b);
            k.j(c2006lI.b);
            String strH = ax.p006a3.v.h(uri);
            if (strH != null && strH.equals(c2006lI.a)) {
                fVarM.b = uri;
                fVarM.c = c2006lI.b;
                return true;
            }
            return false;
        } catch (C1966j unused) {
        }
    }

    public static boolean H(Context context, ax.p086f3.K k, String str, Uri uri) {
        String authority = uri.getAuthority();
        if ("com.android.externalstorage.documents".equals(authority)) {
            return F(context, k, str, uri);
        }
        if ("com.android.mtp.documents".equals(authority)) {
            return G(context, k, uri);
        }
        b.severe("Unknown Document Athority : " + authority);
        return false;
    }

    public static boolean I(ax.p086f3.K k, Uri uri) {
        if (ax.U2.f.a0(k.d())) {
            Uri uriN = n(k, null);
            if (uriN == null) {
                return false;
            }
            return uri.toString().startsWith(uriN.toString());
        }
        if (ax.U2.f.m1 == k.d()) {
            return "com.android.mtp.documents".equals(uri.getAuthority());
        }
        C2461b.g("same storage uri :" + k + ":" + uri);
        return false;
    }

    public static List<AbstractC6239n> J(AbstractC6230e abstractC6230e, AbstractC6239n abstractC6239n) throws C1966j {
        Exception exc;
        IllegalArgumentException illegalArgumentException;
        Throwable th;
        Uri uriL = l(abstractC6239n);
        ContentResolver contentResolver = abstractC6230e.p().getContentResolver();
        Uri uriBuildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uriL, DocumentsContract.getDocumentId(uriL));
        if (ax.p006a3.Q.L() && ax.p086f3.B.P(abstractC6239n) && abstractC6239n.E().endsWith("/Android") && "/Android".equals(((C6249y) abstractC6239n).D0())) {
            uriBuildChildDocumentsUriUsingTree = N(uriBuildChildDocumentsUriUsingTree);
        }
        Uri uri = uriBuildChildDocumentsUriUsingTree;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = contentResolver.query(uri, C2006l.g, null, null, null);
                try {
                    try {
                        if (cursorQuery == null) {
                            throw new C1966j("query return null");
                        }
                        String strE = abstractC6239n.E();
                        if (ax.p086f3.B.P(abstractC6239n)) {
                            Uri uriO0 = ((C6249y) abstractC6239n).O0();
                            ax.p086f3.K kC0 = ((C6249y) abstractC6239n).C0();
                            while (cursorQuery.moveToNext()) {
                                try {
                                    arrayList.add(new C6249y((C6248x) abstractC6230e, uriO0, kC0, strE, cursorQuery));
                                } catch (IllegalArgumentException unused) {
                                }
                            }
                        } else {
                            C6234i c6234i = (C6234i) abstractC6239n;
                            while (cursorQuery.moveToNext()) {
                                C6234i c6234i2 = new C6234i((C6233h) abstractC6230e, strE, cursorQuery);
                                c6234i2.r0(c6234i.n0());
                                arrayList.add(c6234i2);
                            }
                        }
                        if (ax.p006a3.Q.L() && arrayList.size() == 0 && (abstractC6239n instanceof C6249y)) {
                            C6249y c6249y = (C6249y) abstractC6239n;
                            if (c6249y.c1() && !c6249y.G0().canRead()) {
                                c6249y.s1();
                                if (!c6249y.o()) {
                                    throw new ax.p070e3.t();
                                }
                                if (abstractC6239n.S() != ax.p086f3.K.e) {
                                    if ("/Android/data".equals(c6249y.D0())) {
                                        throw new C1961e();
                                    }
                                    ax.p086f3.F.a(cursorQuery);
                                    return arrayList;
                                }
                                try {
                                    ((C6249y) abstractC6239n).z1();
                                } catch (ax.p070e3.r unused2) {
                                    throw new C1961e();
                                }
                            }
                        }
                        ax.p086f3.F.a(cursorQuery);
                        return arrayList;
                    } catch (IllegalArgumentException e) {
                        illegalArgumentException = e;
                        illegalArgumentException.printStackTrace();
                        if (!TextUtils.isEmpty(illegalArgumentException.getMessage()) && !illegalArgumentException.getMessage().contains("FileNotFoundException")) {
                            ax.p302sb.c.h().g().b("DOCUMENT FILE EXCEPTION DO NOT CONTAIN FILENOTFOUND").m(illegalArgumentException).i();
                        }
                        throw new ax.p070e3.t();
                    }
                } catch (C1966j e2) {
                    throw e2;
                } catch (Exception e3) {
                    exc = e3;
                    throw C1960d.a("listChildren : " + exc.getClass().getSimpleName() + ":" + exc.getMessage(), exc);
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                    ax.p086f3.F.a(cursor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (C1966j e4) {
            throw e4;
        } catch (IllegalArgumentException e5) {
            illegalArgumentException = e5;
        } catch (Exception e6) {
            exc = e6;
        }
    }

    public static void K(Context context, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        Uri uri;
        Uri uriB;
        long jP = abstractC6239n.p();
        Uri uriL = l(abstractC6239n);
        Uri uriV = v(abstractC6239n);
        Uri uriV2 = v(abstractC6239n2);
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t("Source File not exist");
        }
        boolean zEquals = abstractC6239n.B().equals(abstractC6239n2.B());
        Uri uri2 = (zEquals || (uriB = ax.p006a3.v.b(context.getContentResolver(), uriL, abstractC6239n2.B(), abstractC6239n.p(), abstractC6239n.q())) == null) ? uriL : uriB;
        Uri uriA = null;
        try {
            uri = uri2;
            try {
                uriA = ax.p006a3.y.a(context.getContentResolver(), uri, uriV, uriV2, abstractC6239n.p(), abstractC6239n.q());
                e = null;
            } catch (C1966j e) {
                e = e;
            }
        } catch (C1966j e2) {
            e = e2;
            uri = uri2;
        }
        if (uriA == null) {
            if (!uri.equals(uriL)) {
                ax.p006a3.v.b(context.getContentResolver(), uri, abstractC6239n.B(), abstractC6239n.p(), abstractC6239n.q());
            }
            if (e != null) {
                throw new C1966j("moveDocument failed 2", e);
            }
            throw new C1966j("moveDocument failed 1");
        }
        if (ax.U2.f.a0(abstractC6239n.Q())) {
            if (!uriA.equals(l(abstractC6239n2)) && !zEquals) {
                if (ax.p006a3.v.b(context.getContentResolver(), uriA, abstractC6239n2.B(), abstractC6239n.p(), abstractC6239n.q()) == null) {
                    throw new C1966j("renameDocument in move failed");
                }
            }
        } else if (ax.U2.f.m1 == abstractC6239n.Q()) {
            C2461b.f();
        }
        if (iVar != null) {
            iVar.a(jP, jP);
        }
    }

    public static void L(AbstractC6230e abstractC6230e, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        AbstractC6230e abstractC6230e2;
        AbstractC6239n abstractC6239n3;
        long jP = abstractC6239n.p();
        if (abstractC6239n2.o()) {
            ax.p302sb.c.h().g().d("!! Move Document FILE ALREADY EXISTS !!").k().h("location:" + abstractC6239n2.Q().H()).i();
            throw new C1966j("moveUsingCopyDocumentFile file already exists");
        }
        if (abstractC6239n.isDirectory()) {
            if (abstractC6230e.u() != ax.U2.f.m1) {
                C2461b.g("Not supported : doesSupportMoveFileToDifferentParent() == false");
            }
            throw new C1966j("Folder rename is not supported");
        }
        try {
            abstractC6230e2 = abstractC6230e;
            abstractC6239n3 = abstractC6239n2;
            try {
                Q(abstractC6230e2, abstractC6239n3, abstractC6230e.s(abstractC6239n), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), true, interfaceC2462c, iVar);
                g(abstractC6230e2, abstractC6239n);
                if (iVar != null) {
                    iVar.a(jP, jP);
                }
            } catch (C1957a e) {
                e = e;
                C1957a c1957a = e;
                g(abstractC6230e2, abstractC6239n3);
                throw new C1966j(c1957a);
            } catch (C1966j e2) {
                e = e2;
                C1966j c1966j = e;
                g(abstractC6230e2, abstractC6239n3);
                throw c1966j;
            }
        } catch (C1957a e3) {
            e = e3;
            abstractC6230e2 = abstractC6230e;
            abstractC6239n3 = abstractC6239n2;
        } catch (C1966j e4) {
            e = e4;
            abstractC6230e2 = abstractC6230e;
            abstractC6239n3 = abstractC6239n2;
        }
    }

    public static void M(Context context, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        long jP = abstractC6239n.p();
        ax.p006a3.v.b(context.getContentResolver(), l(abstractC6239n), abstractC6239n2.B(), abstractC6239n.p(), abstractC6239n.q());
        if (iVar != null) {
            iVar.a(jP, jP);
        }
    }

    public static Uri N(Uri uri) {
        ax.p006a3.Q.l(31);
        return uri.buildUpon().appendQueryParameter("manage", "true").build();
    }

    public static void O(Context context, ax.p086f3.K k, String str, Uri uri) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("pref_secondary", 0).edit();
            editorEdit.putString(y(k, str), uri.toString());
            editorEdit.commit();
        } catch (ax.p070e3.M e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.nio.channels.FileChannel, java.nio.channels.spi.AbstractInterruptibleChannel] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.nio.channels.spi.AbstractInterruptibleChannel] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r4v8, types: [android.content.ContentResolver] */
    public static void P(AbstractC6230e abstractC6230e, AbstractC6239n abstractC6239n, long j) throws C1966j, IOException {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        Context contextP = abstractC6230e.p();
        ?? L = l(abstractC6239n);
        ?? ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        try {
            try {
                parcelFileDescriptorOpenFileDescriptor = contextP.getContentResolver().openFileDescriptor(L, "rw");
                try {
                    L = new FileOutputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                    try {
                        ConvertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(L.getChannel());
                        ConvertMaybeLegacyFileChannelFromLibrary.truncate(j);
                        ConvertMaybeLegacyFileChannelFromLibrary.close();
                        L.close();
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (IllegalArgumentException e) {
                        e = e;
                        ax.p302sb.c.h().g().b("trucate failed").m(e).h("FILE:" + abstractC6239n.o()).i();
                        throw new IOException(e);
                    } catch (SecurityException e2) {
                        e = e2;
                        throw new C1966j(e);
                    } catch (Throwable th) {
                        th = th;
                        if (ConvertMaybeLegacyFileChannelFromLibrary != 0) {
                            ConvertMaybeLegacyFileChannelFromLibrary.close();
                        }
                        if (L != 0) {
                            L.close();
                        }
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                        throw th;
                    }
                } catch (IllegalArgumentException e3) {
                    e = e3;
                } catch (SecurityException e4) {
                    e = e4;
                } catch (Throwable th2) {
                    th = th2;
                    L = 0;
                }
            } catch (Throwable th3) {
                th = th3;
                ConvertMaybeLegacyFileChannelFromLibrary = contextP;
                parcelFileDescriptorOpenFileDescriptor = null;
            }
        } catch (IllegalArgumentException e5) {
            e = e5;
        } catch (SecurityException e6) {
            e = e6;
        } catch (Throwable th4) {
            th = th4;
            parcelFileDescriptorOpenFileDescriptor = null;
            L = 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0169  */
    /* JADX WARN: Code duplicated, block: B:127:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:129:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:134:0x018e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:138:0x0187 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:142:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:150:0x017f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:151:0x017f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:153:0x008a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:158:? A[SYNTHETIC] */
    public static void Q(AbstractC6230e abstractC6230e, AbstractC6239n abstractC6239n, ax.p086f3.G g, long j, Long l, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        Throwable th;
        InputStream inputStreamB;
        NullPointerException nullPointerException;
        Throwable th2;
        IOException iOException;
        AssetFileDescriptor assetFileDescriptorP;
        BufferedOutputStream bufferedOutputStream;
        Context contextP = abstractC6230e.p();
        Uri uriD = d(abstractC6230e, abstractC6239n, false);
        if (uriD == null) {
            throw new C1966j("DocumentFile returns null");
        }
        if (ax.U2.f.a0(abstractC6239n.Q())) {
            try {
                Uri uriL = l(abstractC6239n);
                if (uriL != null && !uriD.equals(uriL) && d0.M(abstractC6239n.B())) {
                    String strG = d0.g(d0.h(uriD.getPath()));
                    if (strG != null && strG.endsWith(")") && strG.contains("(")) {
                        try {
                            ax.p006a3.v.f(contextP.getContentResolver(), uriD);
                        } catch (SecurityException unused) {
                        }
                        throw new C1962f(false);
                    }
                    ax.p302sb.c.h().g().d("UNEXPECTED DOCUMENT FILE NAME").h("expected:" + uriL + ",uri:" + uriD).i();
                }
            } catch (C1966j unused2) {
            }
        } else {
            abstractC6239n.Q();
        }
        int i = 0;
        while (true) {
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                inputStreamB = g.b();
                try {
                    try {
                        assetFileDescriptorP = ax.p006a3.v.p(contextP, uriD);
                    } catch (FileNotFoundException unused3) {
                        assetFileDescriptorP = ax.p006a3.v.p(contextP, uriD);
                        if (assetFileDescriptorP != null) {
                            ax.p302sb.c.h().g().c("REMOTE EXCEPTION RETRY SUCCESS! : WriteDocumentFile").i();
                        }
                    }
                    OutputStream outputStreamCreateOutputStream = assetFileDescriptorP != null ? assetFileDescriptorP.createOutputStream() : null;
                    if (outputStreamCreateOutputStream == null) {
                        outputStreamCreateOutputStream = contextP.getContentResolver().openOutputStream(uriD);
                    }
                    bufferedOutputStream = new BufferedOutputStream(outputStreamCreateOutputStream, 8192);
                    try {
                        ax.p086f3.F.c(inputStreamB, bufferedOutputStream, j, interfaceC2462c, iVar);
                        if (assetFileDescriptorP == null || !z || !abstractC6230e.S()) {
                            break;
                            break;
                            break;
                        } else {
                            assetFileDescriptorP.getParcelFileDescriptor().getFileDescriptor().sync();
                            break;
                        }
                    } catch (SyncFailedException e) {
                        e = e;
                        bufferedOutputStream2 = bufferedOutputStream;
                        if (i <= 0) {
                            try {
                                if (g.a()) {
                                    i++;
                                    if (bufferedOutputStream2 != null) {
                                        try {
                                            bufferedOutputStream2.close();
                                        } catch (IOException unused4) {
                                        }
                                    }
                                    if (inputStreamB != null) {
                                        try {
                                            inputStreamB.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (bufferedOutputStream2 != null) {
                                    try {
                                        bufferedOutputStream2.close();
                                    } catch (IOException unused5) {
                                    }
                                }
                                if (inputStreamB != null) {
                                    throw th;
                                }
                                try {
                                    inputStreamB.close();
                                    throw th;
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    throw th;
                                }
                            }
                        }
                        throw new C1966j(e);
                    } catch (IOException e4) {
                        iOException = e4;
                        throw C1960d.a("write document error", iOException);
                    } catch (IllegalArgumentException e5) {
                        e = e5;
                        th2 = e;
                        ax.p302sb.c.h().g().d("LOWDF1:").m(th2).h(uriD.toString()).i();
                        throw new C1966j(th2);
                    } catch (NullPointerException e6) {
                        nullPointerException = e6;
                        throw new C1966j(nullPointerException);
                    } catch (SecurityException e7) {
                        e = e7;
                        th2 = e;
                        ax.p302sb.c.h().g().d("LOWDF1:").m(th2).h(uriD.toString()).i();
                        throw new C1966j(th2);
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedOutputStream2 = bufferedOutputStream;
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (inputStreamB != null) {
                            throw th;
                        }
                        inputStreamB.close();
                        throw th;
                    }
                } catch (SyncFailedException e8) {
                    e = e8;
                    if (i <= 0) {
                        if (g.a()) {
                            i++;
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            if (inputStreamB != null) {
                                inputStreamB.close();
                            }
                        }
                    }
                    throw new C1966j(e);
                } catch (IOException e9) {
                    iOException = e9;
                    throw C1960d.a("write document error", iOException);
                } catch (IllegalArgumentException e10) {
                    e = e10;
                    th2 = e;
                    ax.p302sb.c.h().g().d("LOWDF1:").m(th2).h(uriD.toString()).i();
                    throw new C1966j(th2);
                } catch (NullPointerException e11) {
                    nullPointerException = e11;
                    throw new C1966j(nullPointerException);
                } catch (SecurityException e12) {
                    e = e12;
                    th2 = e;
                    ax.p302sb.c.h().g().d("LOWDF1:").m(th2).h(uriD.toString()).i();
                    throw new C1966j(th2);
                }
            } catch (SyncFailedException e13) {
                e = e13;
                inputStreamB = null;
            } catch (IOException e14) {
                iOException = e14;
            } catch (IllegalArgumentException e15) {
                e = e15;
                th2 = e;
                ax.p302sb.c.h().g().d("LOWDF1:").m(th2).h(uriD.toString()).i();
                throw new C1966j(th2);
            } catch (NullPointerException e16) {
                nullPointerException = e16;
            } catch (SecurityException e17) {
                e = e17;
                th2 = e;
                ax.p302sb.c.h().g().d("LOWDF1:").m(th2).h(uriD.toString()).i();
                throw new C1966j(th2);
            } catch (Throwable th5) {
                th = th5;
                inputStreamB = null;
            }
        }
        try {
            bufferedOutputStream.close();
        } catch (IOException unused6) {
        }
        if (inputStreamB != null) {
            try {
                inputStreamB.close();
            } catch (IOException e18) {
                e18.printStackTrace();
            }
        }
    }

    public static boolean a(Context context, Uri uri) {
        return ax.p038c3.n.b(context).d(uri);
    }

    public static void b(Context context, ax.p086f3.K k, String str) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("pref_secondary", 0).edit();
            if (k.d() == ax.U2.f.u0) {
                editorEdit.remove("secondaryRootUri");
            }
            editorEdit.remove(y(k, str));
            editorEdit.commit();
        } catch (ax.p070e3.M e) {
            e.printStackTrace();
        }
    }

    public static Uri c(Uri uri) {
        return DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri));
    }

    public static Uri d(AbstractC6230e abstractC6230e, AbstractC6239n abstractC6239n, boolean z) throws Throwable {
        Context contextP = abstractC6230e.p();
        String strE = abstractC6239n.E();
        String strB = abstractC6239n.B();
        AbstractC6239n abstractC6239nA1 = abstractC6230e.a1(d0.r(strE));
        if (abstractC6239nA1 == null || !abstractC6239nA1.o()) {
            throw new C1966j("CreateDocument Parent not exists");
        }
        Uri uriV = v(abstractC6239n);
        if (z) {
            try {
                return ax.p006a3.v.e(contextP.getContentResolver(), uriV, strB);
            } catch (SecurityException e) {
                ax.p302sb.c.h().g().d("CDF1").m(e).i();
                return null;
            }
        }
        String strH = C2015v.h(strE);
        if (strH == null) {
            strH = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        try {
            return ax.p006a3.v.d(contextP.getContentResolver(), uriV, strH, strB);
        } catch (SecurityException e2) {
            ax.p302sb.c.h().g().d("CDF2").m(e2).i();
            return null;
        }
    }

    public static Uri e(ax.p086f3.K k, Uri uri, String str) throws C1966j {
        if (uri == null) {
            ax.p302sb.c.h().b("Null RootUri").k().h("loc:" + k.toString()).i();
            throw new ax.p070e3.r("RootUri is empty");
        }
        String strE = k.e();
        if (str.equals(strE)) {
            return ax.p006a3.v.c(uri, ax.p006a3.v.h(uri));
        }
        try {
            if (d0.I(strE, str)) {
                return f(uri, d0.n(strE, str));
            }
            throw new C1966j("Path is not subdir of root" + strE + ":" + str);
        } catch (IllegalArgumentException e) {
            ax.p302sb.c.h().g().d("Invalid PATH").m(e).h(str).i();
            throw new C1966j(e);
        }
    }

    private static Uri f(Uri uri, String str) throws C1966j {
        int iIndexOf;
        try {
            String strH = ax.p006a3.v.h(uri);
            if (!strH.endsWith(":") && (iIndexOf = strH.indexOf(":")) >= 0) {
                strH = strH.substring(0, iIndexOf + 1);
            }
            return ax.p006a3.v.c(uri, strH + d0.m(str));
        } catch (IllegalArgumentException e) {
            ax.p302sb.c.h().g().d("Invalid Tree PATH").m(e).h(str).i();
            throw new C1966j(e);
        }
    }

    public static void g(AbstractC6238m abstractC6238m, AbstractC6239n abstractC6239n) throws Throwable {
        List<AbstractC6239n> listJ;
        if (abstractC6239n.isDirectory() && (listJ = abstractC6238m.J(abstractC6239n)) != null && listJ.size() > 0) {
            throw new C1967k("DocumentFile Delete Failed : has Children");
        }
        try {
            ax.p006a3.v.f(abstractC6238m.p().getContentResolver(), l(abstractC6239n));
        } catch (C1966j | SecurityException e) {
            if (e instanceof SecurityException) {
                if (".$recycle_bin$".equals(d0.h(abstractC6239n.U()))) {
                    ax.p302sb.c.h().g().d("CDF3").m(e).i();
                } else {
                    ax.p302sb.c.h().g().d("CDF4").m(e).i();
                }
            }
            if (!abstractC6238m.a1(abstractC6239n.E()).o()) {
                throw new ax.p070e3.t("DocumentFile delete failed : File not exist");
            }
            throw C1960d.a("DocumentFile delete failed : exist=true, dir=" + abstractC6239n.isDirectory(), e);
        }
    }

    public static ax.p086f3.K h(Context context, Uri uri) {
        if (ax.p006a3.Q.L()) {
            try {
                String strH = ax.p006a3.v.h(uri);
                ax.p038c3.j jVarF = ax.p038c3.j.F();
                ax.p086f3.K k = ax.p086f3.K.e;
                String strA0 = jVarF.a0(k);
                if (strH != null && strA0 != null && strH.startsWith(strA0)) {
                    return k;
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (ax.p038c3.j.F().Z() != null) {
            for (ax.p086f3.K k2 : ax.p038c3.j.F().Z()) {
                if (H(context, k2, null, uri)) {
                    return k2;
                }
            }
        }
        for (ax.p086f3.K k3 : ax.p038c3.j.F().L()) {
            if (H(context, k3, null, uri)) {
                return k3;
            }
        }
        ax.U2.f[] fVarArr = {ax.U2.f.u0, ax.U2.f.z0};
        for (int i = 0; i < 2; i++) {
            ax.p086f3.K kA = ax.p086f3.K.a(fVarArr[i], 0);
            if (kA.e() != null && H(context, kA, null, uri)) {
                return kA;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v8, types: [java.lang.AutoCloseable] */
    public static C2006l i(Context context, Uri uri, String str) throws Throwable {
        ?? r13;
        Throwable th;
        RuntimeException runtimeException;
        SecurityException securityException;
        IllegalArgumentException illegalArgumentException;
        Cursor cursorQuery;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            try {
                cursorQuery = contentResolver.query(uri, C2006l.g, null, null, null);
                try {
                    if (cursorQuery != null) {
                        if (!cursorQuery.moveToFirst()) {
                            ax.p086f3.F.a(cursorQuery);
                            return null;
                        }
                        C2006l c2006l = new C2006l(cursorQuery);
                        ax.p086f3.F.a(cursorQuery);
                        return c2006l;
                    }
                    String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
                    String documentId = DocumentsContract.getDocumentId(uri);
                    if (!Objects.equals(treeDocumentId, documentId)) {
                        throw new ax.p070e3.v("Remote Provider Error");
                    }
                    try {
                        Cursor cursorQuery2 = contentResolver.query(Uri.parse(uri.toString() + "$A$B$C$D$E$F$G$H$I$J$K$L"), new String[]{"document_id"}, null, null, null);
                        if (cursorQuery2 == null) {
                            throw new ax.p070e3.v("Remote Provider Error");
                        }
                        ax.p302sb.c.h().c("RETRY success for getting file attribute 1").i();
                        C2006l c2006l2 = new C2006l(documentId, str);
                        ax.p086f3.F.a(cursorQuery2);
                        return c2006l2;
                    } catch (IllegalArgumentException e) {
                        if (e.getMessage() != null && e.getMessage().contains("No root for")) {
                            throw new ax.p070e3.C(e);
                        }
                        if (e.getMessage() == null || !e.getMessage().contains("Missing file")) {
                            throw new C1966j(e);
                        }
                        ax.p302sb.c.h().g().c("RETRY success for getting file attribute 2").m(e).i();
                        C2006l c2006l3 = new C2006l(documentId, str);
                        ax.p086f3.F.a(cursorQuery);
                        return c2006l3;
                    } catch (Exception e2) {
                        throw new C1966j(e2);
                    }
                } catch (IllegalArgumentException e3) {
                    illegalArgumentException = e3;
                    if (illegalArgumentException.getMessage() != null && illegalArgumentException.getMessage().contains("No root for")) {
                        throw new ax.p070e3.C(illegalArgumentException);
                    }
                    if (illegalArgumentException.getMessage() == null || !illegalArgumentException.getMessage().contains("Missing file")) {
                        illegalArgumentException.printStackTrace();
                        throw new ax.p070e3.t(illegalArgumentException);
                    }
                    ax.p086f3.F.a(cursorQuery);
                    return null;
                } catch (SecurityException e4) {
                    securityException = e4;
                    throw new ax.p070e3.p(securityException);
                } catch (RuntimeException e5) {
                    runtimeException = e5;
                    runtimeException.printStackTrace();
                    throw new C1966j(runtimeException);
                }
            } catch (Throwable th2) {
                th = th2;
                r13 = uri;
                ax.p086f3.F.a(r13);
                throw th;
            }
        } catch (IllegalArgumentException e6) {
            illegalArgumentException = e6;
            cursorQuery = null;
        } catch (SecurityException e7) {
            securityException = e7;
        } catch (RuntimeException e8) {
            runtimeException = e8;
        } catch (Throwable th3) {
            r13 = 0;
            th = th3;
            ax.p086f3.F.a(r13);
            throw th;
        }
    }

    public static C2006l j(Context context, AbstractC6239n abstractC6239n) throws C1966j {
        return i(context, l(abstractC6239n), abstractC6239n.B());
    }

    public static k0 k(Context context, AbstractC6238m abstractC6238m, ax.p086f3.K k, Uri uri) throws C1966j {
        String strE;
        if (ax.p006a3.Q.u()) {
            strE = d0.Q(k.e(), "LOST.DIR");
            if (!abstractC6238m.a1(strE).o()) {
                strE = d0.Q(k.e(), ".tempfstat");
                AbstractC6239n abstractC6239nA1 = abstractC6238m.a1(strE);
                if (!abstractC6239nA1.o()) {
                    abstractC6238m.o1(abstractC6239nA1);
                }
            }
        } else {
            strE = k.e();
        }
        Uri uriE = e(k, uri, strE);
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            try {
                ParcelFileDescriptor parcelFileDescriptorO = o(context, uriE, "r");
                if (parcelFileDescriptorO == null) {
                    throw new C1966j("faild to get file descriptor");
                }
                StructStatVfs structStatVfsFstatvfs = Os.fstatvfs(parcelFileDescriptorO.getFileDescriptor());
                long j = structStatVfsFstatvfs.f_blocks;
                long j2 = structStatVfsFstatvfs.f_bsize;
                k0 k0Var = new k0(j * j2, j2 * (j - structStatVfsFstatvfs.f_bavail), 0);
                try {
                    parcelFileDescriptorO.close();
                } catch (IOException unused) {
                }
                return k0Var;
            } catch (ErrnoException e) {
                throw new C1966j(e);
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            try {
                parcelFileDescriptor.close();
                throw th;
            } catch (IOException unused2) {
                throw th;
            }
        }
    }

    public static Uri l(AbstractC6239n abstractC6239n) throws C1966j {
        if (ax.p086f3.B.P(abstractC6239n)) {
            C6249y c6249y = (C6249y) abstractC6239n;
            return e(c6249y.C0(), c6249y.O0(), abstractC6239n.E());
        }
        if (ax.p086f3.B.J(abstractC6239n)) {
            return ((C6234i) abstractC6239n).o0();
        }
        C2461b.f();
        throw new C1966j("Illegal File Info Class");
    }

    public static Uri m(ax.p086f3.K k, String str) {
        if (ax.U2.f.a0(k.d()) || ax.U2.f.P(k)) {
            Uri uriN = n(k, str);
            if (uriN == null) {
                return null;
            }
            return c(uriN);
        }
        C2461b.g("not reachable root uri : " + k);
        return null;
    }

    public static Uri n(ax.p086f3.K k, String str) {
        if (!ax.U2.f.a0(k.d()) && !ax.U2.f.P(k)) {
            C2461b.g("not reachable root tree : " + k);
            return null;
        }
        String strM = (str == null || !ax.U2.f.P(k)) ? HttpUrl.FRAGMENT_ENCODE_SET : d0.m(str);
        String strA0 = ax.p038c3.j.F().a0(k);
        if (strA0 == null) {
            return null;
        }
        return new Uri.Builder().scheme("content").authority("com.android.externalstorage.documents").appendPath("tree").appendPath(strA0 + ":" + strM).build();
    }

    public static ParcelFileDescriptor o(Context context, Uri uri, String str) throws C1966j {
        try {
            return context.getContentResolver().openFileDescriptor(uri, str);
        } catch (SQLiteException | IllegalStateException | NullPointerException | SecurityException e) {
            throw new C1966j(e);
        } catch (FileNotFoundException | IllegalArgumentException e2) {
            throw new ax.p070e3.t(e2);
        }
    }

    private static String p(String str, String str2) {
        int iIndexOf;
        if (str2 == null || (iIndexOf = str2.indexOf(":")) < 0) {
            return null;
        }
        String strSubstring = str2.substring(iIndexOf + 1);
        return TextUtils.isEmpty(strSubstring) ? str : d0.Q(str, strSubstring);
    }

    public static String q(Context context, ax.p086f3.K k, Uri uri, String str, String str2, C2006l c2006l) throws C1966j {
        return ax.U2.f.m0(k.d()) ? s(uri, str, str2, c2006l) : p(k.e(), str);
    }

    public static String r(Uri uri) {
        ax.p086f3.K k;
        String strE;
        String strH;
        try {
            String strG = ax.p006a3.v.g(uri);
            String strA = A(strG);
            if ("primary".equals(strA)) {
                return p(ax.p086f3.K.e.e(), strG);
            }
            if (ax.p038c3.j.F().u0() && (strE = (k = ax.p086f3.K.f).e()) != null && (strH = d0.h(strE)) != null && strH.equals(strA)) {
                return p(k.e(), strG);
            }
            return null;
        } catch (Exception unused) {
        }
    }

    private static String s(Uri uri, String str, String str2, C2006l c2006l) throws C1966j {
        if (!ax.p006a3.Q.k1()) {
            C2461b.f();
            throw new C1966j("not reachable");
        }
        if (uri != null) {
            String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
            if (treeDocumentId != null && treeDocumentId.equals(str)) {
                return "/";
            }
        } else {
            C2461b.f();
        }
        String str3 = c2006l.b;
        if (str3 != null) {
            return d0.Q(str2, str3);
        }
        C2461b.f();
        throw new C1966j("No displayname");
    }

    public static InputStream t(Context context, AbstractC6239n abstractC6239n, long j) throws C1966j {
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(o(context, l(abstractC6239n), "r"));
            if (j != 0) {
                long jSkip = autoCloseInputStream.skip(j);
                if (jSkip != j) {
                    ax.p302sb.c.h().b("Document file SKIP FAILED").h("offst:" + j + ",skipped:" + jSkip).i();
                    throw new C1966j("AutoCloseInputStream skip failed");
                }
            }
            return autoCloseInputStream;
        } catch (FileNotFoundException e) {
            throw new ax.p070e3.t(e);
        } catch (IOException e2) {
            throw C1960d.a("document getInputStream", e2);
        }
    }

    public static OutputStream u(AbstractC6230e abstractC6230e, AbstractC6239n abstractC6239n, boolean z) throws Throwable {
        Uri uriL;
        Uri uri;
        String strG;
        Context contextP = abstractC6230e.p();
        Uri uriL2 = null;
        if (abstractC6239n.o()) {
            uriL = l(abstractC6239n);
            uri = null;
        } else {
            uriL = d(abstractC6230e, abstractC6239n, false);
            if (uriL == null) {
                throw new IOException("Create Document File failed");
            }
            if (ax.U2.f.a0(abstractC6239n.Q())) {
                uriL2 = l(abstractC6239n);
                if (!uriL.equals(uriL2) && d0.M(abstractC6239n.B()) && ((strG = d0.g(d0.h(uriL.getPath()))) == null || !strG.endsWith(")") || !strG.contains("("))) {
                    ax.p302sb.c.h().g().d("UNEXPECTED DOCUMENT FILE NAME 2").h("expected:" + uriL2 + ",created:" + uriL).i();
                }
            }
            uri = uriL2;
            uriL2 = uriL;
        }
        try {
            return new ParcelFileDescriptor.AutoCloseOutputStream(z ? contextP.getContentResolver().openFileDescriptor(uriL, "wa") : contextP.getContentResolver().openFileDescriptor(uriL, "wt"));
        } catch (IllegalArgumentException e) {
            String str = "FILE:" + abstractC6239n.o() + ":" + z;
            if (uriL2 != null) {
                str = str + ",CREATED:" + uriL2.toString();
            }
            if (uri != null) {
                str = str + ",EXPECTED:" + uri.toString();
            }
            ax.p302sb.c.h().g().b("GetOutputStreamForDocumentFile failed").m(e).h(str).i();
            throw new IOException(e);
        } catch (SecurityException e2) {
            throw new C1966j(e2);
        }
    }

    public static Uri v(AbstractC6239n abstractC6239n) throws C1966j {
        if (ax.p086f3.B.P(abstractC6239n)) {
            C6249y c6249y = (C6249y) abstractC6239n;
            return e(c6249y.C0(), c6249y.O0(), abstractC6239n.U());
        }
        if (ax.p086f3.B.J(abstractC6239n)) {
            return ((C6234i) abstractC6239n).p0();
        }
        C2461b.f();
        throw new C1966j("Illegal File Info Class");
    }

    public static ParcelFileDescriptor w(Context context, AbstractC6239n abstractC6239n) throws C1966j {
        return o(context, l(abstractC6239n), "rw");
    }

    public static String x(Context context, ax.p086f3.K k, String str) {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("pref_secondary", 0);
            if (k.d() == ax.U2.f.u0 && (string = sharedPreferences.getString("secondaryRootUri", null)) != null) {
                return string;
            }
            String string2 = sharedPreferences.getString(y(k, str), null);
            return (ax.p006a3.J.o() && string2 != null && string2.startsWith("content://0@com.android.externalstorage.documents")) ? string2.replace("0@com.android.externalstorage.documents", "com.android.externalstorage.documents") : string2;
        } catch (ax.p070e3.M e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String y(ax.p086f3.K k, String str) throws ax.p070e3.M {
        ax.U2.f fVarD = k.d();
        if (ax.U2.f.g0(fVarD)) {
            if (str == null) {
                return "secondaryRootUri:" + k.e();
            }
            C2461b.c(fVarD == ax.U2.f.u0);
            return "secondaryRootUri:" + k.e() + ":" + str;
        }
        if (fVarD == ax.U2.f.t0 || fVarD == ax.U2.f.x0) {
            if (str == null) {
                ax.p302sb.c.h().g().b("ROOT URI PREF NULL ROOT").k().i();
                throw new ax.p070e3.M("no rootTreePath");
            }
            String strA0 = ax.p038c3.j.F().a0(k);
            if (strA0 == null) {
                throw new ax.p070e3.M("uuid is null");
            }
            return "primaryRootUri:" + strA0 + ":" + str;
        }
        if (ax.U2.f.b0(fVarD)) {
            String strA1 = ax.p038c3.j.F().a0(k);
            if (strA1 == null) {
                throw new ax.p070e3.M("uuid is null");
            }
            return "documentRootUri:" + fVarD.H() + ":" + strA1;
        }
        if (ax.U2.f.m0(fVarD)) {
            return "nonExternalRootUri:" + fVarD.H() + ":" + k.b();
        }
        C2461b.g("not reachable:" + fVarD.H());
        return "documentRootUri:" + fVarD.H() + ":" + k.b();
    }

    public static String z(String str) {
        int iIndexOf;
        if (str == null || (iIndexOf = str.indexOf(":")) < 0) {
            return null;
        }
        return "/" + str.substring(iIndexOf + 1);
    }
}
