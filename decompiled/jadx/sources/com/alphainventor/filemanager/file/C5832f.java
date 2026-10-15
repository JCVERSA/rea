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
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0714e;
import ax.I1.C0718i;
import ax.I1.C0719j;
import ax.J1.C0727h;
import ax.J1.C0735p;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.V;
import ax.J1.b0;
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
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5832f {
    Context a;

    C5832f(Context context) {
        this.a = context;
    }

    private static boolean A(ax.J1.F f, C0727h c0727h) {
        String strU;
        if (ax.p338y1.f.f0(f.d())) {
            String strE = f.e();
            if (strE == null) {
                return false;
            }
            strU = V.h(strE);
        } else if (ax.p338y1.f.a0(f.d())) {
            strU = ax.G1.i.D().U(f);
        } else {
            ax.p045d2.b.g("Not reachable");
            strU = null;
        }
        String str = c0727h.b;
        if (str != null) {
            if (str.equals(strU)) {
                return true;
            }
            String strX = x(c0727h.a);
            if (strX != null && strX.equals(strU)) {
                return true;
            }
        }
        return false;
    }

    public static boolean B(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(":");
    }

    public static boolean C(Context context, ax.J1.F f, String str, Uri uri) throws Throwable {
        String strH;
        if (!ax.E1.P.F1()) {
            return false;
        }
        try {
            if (f.e() == null) {
                return false;
            }
            String strG = ax.E1.u.g(uri);
            if (str != null) {
                String strW = w(strG);
                if (strW == null || !strW.equals(str)) {
                    return false;
                }
            } else if (!B(strG)) {
                return false;
            }
            if (str != null) {
                strH = V.h(str);
            } else if (ax.p338y1.f.f0(f.d())) {
                strH = V.h(f.e());
            } else {
                strH = ax.p338y1.f.a0(f.d()) ? ax.G1.i.D().U(f) : HttpUrl.FRAGMENT_ENCODE_SET;
            }
            C0727h c0727hI = i(context, c(uri), strH);
            if (c0727hI == null) {
                return false;
            }
            if (str == null) {
                return A(f, c0727hI);
            }
            String strY = y(strG);
            if (f == ax.J1.F.e) {
                return "primary".equals(strY);
            }
            if (f == ax.J1.F.f) {
                String strH2 = V.h(f.e());
                return strH2 != null && strH2.equals(strY);
            }
            ax.p045d2.b.f();
            return false;
        } catch (C0718i unused) {
        }
    }

    public static boolean D(ax.J1.F f, Uri uri) {
        Uri uriN = n(f, null);
        if (uriN == null) {
            return false;
        }
        return uri.toString().startsWith(uriN.toString());
    }

    public static List<AbstractC5838l> E(AbstractC5830d abstractC5830d, AbstractC5838l abstractC5838l) throws C0718i {
        Uri uriL = l(abstractC5838l);
        ContentResolver contentResolver = abstractC5830d.n().getContentResolver();
        Uri uriBuildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uriL, DocumentsContract.getDocumentId(uriL));
        if (ax.E1.P.L() && C0740v.H(abstractC5838l) && abstractC5838l.C().endsWith("/Android") && "/Android".equals(((u) abstractC5838l).w0())) {
            uriBuildChildDocumentsUriUsingTree = I(uriBuildChildDocumentsUriUsingTree);
        }
        Uri uri = uriBuildChildDocumentsUriUsingTree;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                try {
                    Cursor cursorQuery = contentResolver.query(uri, C0727h.g, null, null, null);
                    if (cursorQuery == null) {
                        throw new C0718i("query return null");
                    }
                    String strC = abstractC5838l.C();
                    if (C0740v.H(abstractC5838l)) {
                        Uri uriD0 = ((u) abstractC5838l).D0();
                        ax.J1.F fV0 = ((u) abstractC5838l).v0();
                        while (cursorQuery.moveToNext()) {
                            try {
                                arrayList.add(new u((C5845t) abstractC5830d, uriD0, fV0, strC, cursorQuery));
                            } catch (IllegalArgumentException unused) {
                            }
                        }
                    } else {
                        while (cursorQuery.moveToNext()) {
                            arrayList.add(new C5834h((C5833g) abstractC5830d, strC, cursorQuery));
                        }
                    }
                    if (ax.E1.P.L() && arrayList.size() == 0 && (abstractC5838l instanceof u)) {
                        u uVar = (u) abstractC5838l;
                        if (uVar.Q0() && !uVar.y0().canRead()) {
                            uVar.f1();
                            if (!uVar.n()) {
                                throw new ax.I1.s();
                            }
                            if (abstractC5838l.R() != ax.J1.F.e) {
                                if ("/Android/data".equals(uVar.w0())) {
                                    throw new C0713d();
                                }
                                C0744z.a(cursorQuery);
                                return arrayList;
                            }
                            try {
                                ((u) abstractC5838l).m1();
                            } catch (ax.I1.q unused2) {
                                throw new C0713d();
                            }
                        }
                    }
                    C0744z.a(cursorQuery);
                    return arrayList;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    if (!TextUtils.isEmpty(e.getMessage()) && !e.getMessage().contains("FileNotFoundException")) {
                        ax.p221pa.c.h().g().b("DOCUMENT FILE EXCEPTION DO NOT CONTAIN FILENOTFOUND").m(e).i();
                    }
                    throw new ax.I1.s();
                }
            } catch (Exception e2) {
                throw C0712c.a("listChildren : " + e2.getClass().getSimpleName() + ":" + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            C0744z.a(null);
            throw th;
        }
    }

    public static void F(Context context, AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        Uri uriA;
        Uri uriB;
        long jO = abstractC5838l.o();
        Uri uriL = l(abstractC5838l);
        Uri uriL2 = l(abstractC5838l2);
        Uri uriT = t(abstractC5838l);
        Uri uriT2 = t(abstractC5838l2);
        if (!abstractC5838l.n()) {
            throw new ax.I1.s("Source File not exist");
        }
        boolean z = !abstractC5838l.w().equals(abstractC5838l2.w());
        Uri uri = (!z || (uriB = ax.E1.u.b(context.getContentResolver(), uriL, abstractC5838l2.w(), abstractC5838l.o(), abstractC5838l.q())) == null) ? uriL : uriB;
        try {
            uriA = ax.E1.x.a(context.getContentResolver(), uri, uriT, uriT2, abstractC5838l.o(), abstractC5838l.q());
            e = null;
        } catch (C0718i e) {
            e = e;
            uriA = null;
        }
        if (uriA == null) {
            if (!uri.equals(uriL)) {
                ax.E1.u.b(context.getContentResolver(), uri, abstractC5838l.w(), abstractC5838l.o(), abstractC5838l.q());
            }
            if (e != null) {
                throw new C0718i("moveDocument failed 2", e);
            }
            throw new C0718i("moveDocument failed 1");
        }
        if (!uriA.equals(uriL2) && z && ax.E1.u.b(context.getContentResolver(), uriA, abstractC5838l2.w(), abstractC5838l.o(), abstractC5838l.q()) == null) {
            throw new C0718i("renameDocument in move failed");
        }
        if (iVar != null) {
            iVar.a(jO, jO);
        }
    }

    public static void G(AbstractC5830d abstractC5830d, AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        long jO = abstractC5838l.o();
        if (abstractC5838l2.n()) {
            ax.p221pa.c.h().g().d("!! Move Document FILE ALREADY EXISTS !!").k().h("location:" + abstractC5838l2.P().H()).i();
            throw new C0718i("moveUsingCopyDocumentFile file already exists");
        }
        if (abstractC5838l.isDirectory()) {
            ax.p045d2.b.g("Not supported : doesSupportMoveFileToDifferentParent() == false");
            return;
        }
        try {
            L(abstractC5830d, abstractC5838l2, abstractC5830d.p(abstractC5838l), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), true, cVar, iVar);
            g(abstractC5830d, abstractC5838l);
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (C0710a e) {
            g(abstractC5830d, abstractC5838l2);
            throw new C0718i(e);
        } catch (C0718i e2) {
            g(abstractC5830d, abstractC5838l2);
            throw e2;
        }
    }

    public static void H(Context context, AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        long jO = abstractC5838l.o();
        ax.E1.u.b(context.getContentResolver(), l(abstractC5838l), abstractC5838l2.w(), abstractC5838l.o(), abstractC5838l.q());
        if (iVar != null) {
            iVar.a(jO, jO);
        }
    }

    public static Uri I(Uri uri) {
        ax.E1.P.l(31);
        return uri.buildUpon().appendQueryParameter("manage", "true").build();
    }

    public static void J(Context context, ax.J1.F f, String str, Uri uri) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("pref_secondary", 0).edit();
            editorEdit.putString(v(f, str), uri.toString());
            editorEdit.commit();
        } catch (ax.I1.J e) {
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
    public static void K(AbstractC5830d abstractC5830d, AbstractC5838l abstractC5838l, long j) throws C0718i, IOException {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        Context contextN = abstractC5830d.n();
        ?? L = l(abstractC5838l);
        ?? ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        ConvertMaybeLegacyFileChannelFromLibrary = 0;
        try {
            try {
                parcelFileDescriptorOpenFileDescriptor = contextN.getContentResolver().openFileDescriptor(L, "rw");
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
                        ax.p221pa.c.h().g().b("trucate failed").m(e).h("FILE:" + abstractC5838l.n()).i();
                        throw new IOException(e);
                    } catch (SecurityException e2) {
                        e = e2;
                        throw new C0718i(e);
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
                ConvertMaybeLegacyFileChannelFromLibrary = contextN;
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

    /* JADX WARN: Code duplicated, block: B:120:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:128:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:0x014f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:133:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:135:0x0159 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:0x016c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:142:0x016c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x007c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x0155  */
    public static void L(AbstractC5830d abstractC5830d, AbstractC5838l abstractC5838l, ax.J1.A a, long j, Long l, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        Throwable th;
        InputStream inputStreamB;
        AssetFileDescriptor assetFileDescriptorM;
        BufferedOutputStream bufferedOutputStream;
        Context contextN = abstractC5830d.n();
        Uri uriD = d(abstractC5830d, abstractC5838l, false);
        if (uriD == null) {
            throw new C0718i("DocumentFile returns null");
        }
        Uri uriL = l(abstractC5838l);
        if (!uriD.equals(uriL) && V.I(abstractC5838l.w())) {
            String strG = V.g(V.h(uriD.getPath()));
            if (strG != null && strG.endsWith(")") && strG.contains("(")) {
                try {
                    ax.E1.u.f(contextN.getContentResolver(), uriD);
                } catch (SecurityException unused) {
                }
                throw new C0714e(false);
            }
            ax.p221pa.c.h().g().d("UNEXPECTED DOCUMENT FILE NAME").h("expected:" + uriL + ",uri:" + uriD).i();
        }
        int i = 0;
        while (true) {
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                inputStreamB = a.b();
                try {
                    try {
                        assetFileDescriptorM = ax.E1.u.m(contextN, uriD);
                    } catch (FileNotFoundException unused2) {
                        assetFileDescriptorM = ax.E1.u.m(contextN, uriD);
                        if (assetFileDescriptorM != null) {
                            ax.p221pa.c.h().g().c("REMOTE EXCEPTION RETRY SUCCESS! : WriteDocumentFile").i();
                        }
                    }
                    OutputStream outputStreamCreateOutputStream = assetFileDescriptorM != null ? assetFileDescriptorM.createOutputStream() : null;
                    if (outputStreamCreateOutputStream == null) {
                        outputStreamCreateOutputStream = contextN.getContentResolver().openOutputStream(uriD);
                    }
                    bufferedOutputStream = new BufferedOutputStream(outputStreamCreateOutputStream, 8192);
                    try {
                        C0744z.e(inputStreamB, bufferedOutputStream, j, cVar, iVar);
                        if (assetFileDescriptorM == null || !z || !abstractC5830d.M()) {
                            break;
                            break;
                            break;
                        } else {
                            assetFileDescriptorM.getParcelFileDescriptor().getFileDescriptor().sync();
                            break;
                        }
                    } catch (SyncFailedException e) {
                        e = e;
                        bufferedOutputStream2 = bufferedOutputStream;
                        if (i <= 0) {
                            try {
                                if (a.a()) {
                                    i++;
                                    if (bufferedOutputStream2 != null) {
                                        try {
                                            bufferedOutputStream2.close();
                                        } catch (IOException unused3) {
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
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedOutputStream2 != null) {
                                    try {
                                        bufferedOutputStream2.close();
                                    } catch (IOException unused4) {
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
                        throw new C0718i(e);
                    } catch (IOException e4) {
                        e = e4;
                        throw C0712c.a("write document error", e);
                    } catch (IllegalArgumentException e5) {
                        e = e5;
                        ax.p221pa.c.h().g().d("LOWDF1:").m(e).h(uriD.toString()).i();
                        throw new C0718i(e);
                    } catch (NullPointerException e6) {
                        e = e6;
                        throw new C0718i(e);
                    } catch (SecurityException e7) {
                        e = e7;
                        ax.p221pa.c.h().g().d("LOWDF1:").m(e).h(uriD.toString()).i();
                        throw new C0718i(e);
                    } catch (Throwable th3) {
                        th = th3;
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
                        if (a.a()) {
                            i++;
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            if (inputStreamB != null) {
                                inputStreamB.close();
                            }
                        }
                    }
                    throw new C0718i(e);
                } catch (IOException e9) {
                    e = e9;
                    throw C0712c.a("write document error", e);
                } catch (IllegalArgumentException e10) {
                    e = e10;
                    ax.p221pa.c.h().g().d("LOWDF1:").m(e).h(uriD.toString()).i();
                    throw new C0718i(e);
                } catch (NullPointerException e11) {
                    e = e11;
                    throw new C0718i(e);
                } catch (SecurityException e12) {
                    e = e12;
                    ax.p221pa.c.h().g().d("LOWDF1:").m(e).h(uriD.toString()).i();
                    throw new C0718i(e);
                }
            } catch (SyncFailedException e13) {
                e = e13;
                inputStreamB = null;
            } catch (IOException e14) {
                e = e14;
            } catch (IllegalArgumentException e15) {
                e = e15;
                ax.p221pa.c.h().g().d("LOWDF1:").m(e).h(uriD.toString()).i();
                throw new C0718i(e);
            } catch (NullPointerException e16) {
                e = e16;
            } catch (SecurityException e17) {
                e = e17;
                ax.p221pa.c.h().g().d("LOWDF1:").m(e).h(uriD.toString()).i();
                throw new C0718i(e);
            } catch (Throwable th4) {
                th = th4;
                inputStreamB = null;
            }
        }
        try {
            bufferedOutputStream.close();
        } catch (IOException unused5) {
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
        return ax.G1.m.b(context).d(uri);
    }

    public static void b(Context context, ax.J1.F f, String str) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("pref_secondary", 0).edit();
            if (f.d() == ax.p338y1.f.r0) {
                editorEdit.remove("secondaryRootUri");
            }
            editorEdit.remove(v(f, str));
            editorEdit.commit();
        } catch (ax.I1.J e) {
            e.printStackTrace();
        }
    }

    public static Uri c(Uri uri) {
        return DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri));
    }

    public static Uri d(AbstractC5830d abstractC5830d, AbstractC5838l abstractC5838l, boolean z) throws Throwable {
        Context contextN = abstractC5830d.n();
        String strC = abstractC5838l.C();
        String strW = abstractC5838l.w();
        AbstractC5838l abstractC5838lV0 = abstractC5830d.V0(V.r(strC));
        if (abstractC5838lV0 == null || !abstractC5838lV0.n()) {
            throw new C0718i("CreateDocument Parent not exists");
        }
        Uri uriT = t(abstractC5838l);
        if (z) {
            try {
                return ax.E1.u.e(contextN.getContentResolver(), uriT, strW);
            } catch (SecurityException e) {
                ax.p221pa.c.h().g().d("CDF1").m(e).i();
                return null;
            }
        }
        String strH = C0735p.h(strC);
        if (strH == null) {
            strH = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        try {
            return ax.E1.u.d(contextN.getContentResolver(), uriT, strH, strW);
        } catch (SecurityException e2) {
            ax.p221pa.c.h().g().d("CDF2").m(e2).i();
            return null;
        }
    }

    public static Uri e(ax.J1.F f, Uri uri, String str) throws C0718i {
        if (uri == null) {
            ax.p221pa.c.h().b("Null RootUri").k().h("loc:" + f.toString()).i();
            throw new ax.I1.q("RootUri is empty");
        }
        String strE = f.e();
        if (str.equals(strE)) {
            return ax.E1.u.c(uri, ax.E1.u.g(uri));
        }
        try {
            if (V.E(strE, str)) {
                return f(uri, V.n(strE, str));
            }
            throw new C0718i("Path is not subdir of root" + strE + ":" + str);
        } catch (IllegalArgumentException e) {
            ax.p221pa.c.h().g().d("Invalid PATH").m(e).h(str).i();
            throw new C0718i(e);
        }
    }

    private static Uri f(Uri uri, String str) throws C0718i {
        int iIndexOf;
        try {
            String strG = ax.E1.u.g(uri);
            if (!strG.endsWith(":") && (iIndexOf = strG.indexOf(":")) >= 0) {
                strG = strG.substring(0, iIndexOf + 1);
            }
            return ax.E1.u.c(uri, strG + V.m(str));
        } catch (IllegalArgumentException e) {
            ax.p221pa.c.h().g().d("Invalid Tree PATH").m(e).h(str).i();
            throw new C0718i(e);
        }
    }

    public static void g(AbstractC5837k abstractC5837k, AbstractC5838l abstractC5838l) throws Throwable {
        List<AbstractC5838l> listG1;
        if (abstractC5838l.isDirectory() && (listG1 = abstractC5837k.g1(abstractC5838l)) != null && listG1.size() > 0) {
            throw new C0719j("DocumentFile Delete Failed : has Children");
        }
        try {
            ax.E1.u.f(abstractC5837k.n().getContentResolver(), l(abstractC5838l));
        } catch (C0718i | SecurityException e) {
            if (e instanceof SecurityException) {
                if (".$recycle_bin$".equals(V.h(abstractC5838l.S()))) {
                    ax.p221pa.c.h().g().d("CDF3").m(e).i();
                } else {
                    ax.p221pa.c.h().g().d("CDF4").m(e).i();
                }
            }
            if (!abstractC5837k.V0(abstractC5838l.C()).n()) {
                throw new ax.I1.s("DocumentFile delete failed : File not exist");
            }
            throw C0712c.a("DocumentFile delete failed : exist=true, dir=" + abstractC5838l.isDirectory(), e);
        }
    }

    public static ax.J1.F h(Context context, Uri uri) {
        if (ax.E1.P.L()) {
            try {
                String strG = ax.E1.u.g(uri);
                ax.G1.i iVarD = ax.G1.i.D();
                ax.J1.F f = ax.J1.F.e;
                String strU = iVarD.U(f);
                if (strG != null && strU != null && strG.startsWith(strU)) {
                    return f;
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (ax.G1.i.D().T() != null) {
            for (ax.J1.F f2 : ax.G1.i.D().T()) {
                if (C(context, f2, null, uri)) {
                    return f2;
                }
            }
        }
        ax.p338y1.f[] fVarArr = {ax.p338y1.f.r0, ax.p338y1.f.w0};
        for (int i = 0; i < 2; i++) {
            ax.J1.F fA = ax.J1.F.a(fVarArr[i], 0);
            if (fA.e() != null && C(context, fA, null, uri)) {
                return fA;
            }
        }
        return null;
    }

    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0066: MOVE (r8 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]) (LINE:103), block:B:15:0x0066 */
    public static C0727h i(Context context, Uri uri, String str) throws Throwable {
        Cursor cursorQuery;
        AutoCloseable autoCloseable;
        ContentResolver contentResolver = context.getContentResolver();
        AutoCloseable autoCloseable2 = null;
        try {
            try {
                try {
                    cursorQuery = contentResolver.query(uri, C0727h.g, null, null, null);
                    try {
                        if (cursorQuery != null) {
                            if (!cursorQuery.moveToFirst()) {
                                C0744z.a(cursorQuery);
                                return null;
                            }
                            C0727h c0727h = new C0727h(cursorQuery);
                            C0744z.a(cursorQuery);
                            return c0727h;
                        }
                        String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
                        String documentId = DocumentsContract.getDocumentId(uri);
                        if (!Objects.equals(treeDocumentId, documentId)) {
                            throw new ax.I1.u("Remote Provider Error");
                        }
                        try {
                            Cursor cursorQuery2 = contentResolver.query(Uri.parse(uri.toString() + "$A$B$C$D$E$F$G$H$I$J$K$L"), new String[]{"document_id"}, null, null, null);
                            if (cursorQuery2 == null) {
                                throw new ax.I1.u("Remote Provider Error");
                            }
                            ax.p221pa.c.h().c("RETRY success for getting file attribute 1").i();
                            C0727h c0727h2 = new C0727h(documentId, str);
                            C0744z.a(cursorQuery2);
                            return c0727h2;
                        } catch (IllegalArgumentException e) {
                            if (e.getMessage() != null && e.getMessage().contains("No root for")) {
                                throw new ax.I1.A(e);
                            }
                            if (e.getMessage() == null || !e.getMessage().contains("Missing file")) {
                                throw new C0718i(e);
                            }
                            ax.p221pa.c.h().g().c("RETRY success for getting file attribute 2").m(e).i();
                            C0727h c0727h3 = new C0727h(documentId, str);
                            C0744z.a(cursorQuery);
                            return c0727h3;
                        } catch (Exception e2) {
                            throw new C0718i(e2);
                        }
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        if (e.getMessage() != null && e.getMessage().contains("No root for")) {
                            throw new ax.I1.A(e);
                        }
                        if (e.getMessage() == null || !e.getMessage().contains("Missing file")) {
                            e.printStackTrace();
                            throw new ax.I1.s(e);
                        }
                        C0744z.a(cursorQuery);
                        return null;
                    } catch (SecurityException e4) {
                        e = e4;
                        throw new ax.I1.o(e);
                    } catch (RuntimeException e5) {
                        e = e5;
                        e.printStackTrace();
                        throw new C0718i(e);
                    }
                } catch (Throwable th) {
                    th = th;
                    autoCloseable2 = autoCloseable;
                    C0744z.a(autoCloseable2);
                    throw th;
                }
            } catch (IllegalArgumentException e6) {
                e = e6;
                cursorQuery = null;
            } catch (SecurityException e7) {
                e = e7;
            } catch (RuntimeException e8) {
                e = e8;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static C0727h j(Context context, AbstractC5838l abstractC5838l) throws C0718i {
        return i(context, l(abstractC5838l), abstractC5838l.w());
    }

    public static b0 k(Context context, AbstractC5837k abstractC5837k, ax.J1.F f, Uri uri) throws C0718i {
        String strE;
        if (ax.E1.P.u()) {
            strE = V.M(f.e(), "LOST.DIR");
            if (!abstractC5837k.V0(strE).n()) {
                strE = V.M(f.e(), ".tempfstat");
                AbstractC5838l abstractC5838lV0 = abstractC5837k.V0(strE);
                if (!abstractC5838lV0.n()) {
                    abstractC5837k.i1(abstractC5838lV0);
                }
            }
        } else {
            strE = f.e();
        }
        Uri uriE = e(f, uri, strE);
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            try {
                ParcelFileDescriptor parcelFileDescriptorO = o(context, uriE, "r");
                if (parcelFileDescriptorO == null) {
                    throw new C0718i("faild to get file descriptor");
                }
                StructStatVfs structStatVfsFstatvfs = Os.fstatvfs(parcelFileDescriptorO.getFileDescriptor());
                long j = structStatVfsFstatvfs.f_blocks;
                long j2 = structStatVfsFstatvfs.f_bsize;
                b0 b0Var = new b0(j * j2, (j - structStatVfsFstatvfs.f_bavail) * j2, 0);
                try {
                    parcelFileDescriptorO.close();
                } catch (IOException unused) {
                }
                return b0Var;
            } catch (ErrnoException e) {
                throw new C0718i(e);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static Uri l(AbstractC5838l abstractC5838l) throws C0718i {
        if (C0740v.H(abstractC5838l)) {
            u uVar = (u) abstractC5838l;
            return e(uVar.v0(), uVar.D0(), abstractC5838l.C());
        }
        if (C0740v.C(abstractC5838l)) {
            return e(abstractC5838l.R(), ((C5834h) abstractC5838l).i0(), abstractC5838l.C());
        }
        ax.p045d2.b.f();
        throw new C0718i("Illegal File Info Class");
    }

    public static Uri m(ax.J1.F f, String str) {
        if (!ax.p338y1.f.a0(f.d()) && !ax.p338y1.f.P(f)) {
            ax.p045d2.b.g("not reachable");
            return null;
        }
        Uri uriN = n(f, str);
        if (uriN == null) {
            return null;
        }
        return c(uriN);
    }

    public static Uri n(ax.J1.F f, String str) {
        if (!ax.p338y1.f.a0(f.d()) && !ax.p338y1.f.P(f)) {
            ax.p045d2.b.g("not reachable : " + f);
            return null;
        }
        String strM = (str == null || !ax.p338y1.f.P(f)) ? HttpUrl.FRAGMENT_ENCODE_SET : V.m(str);
        String strU = ax.G1.i.D().U(f);
        if (strU == null) {
            return null;
        }
        return new Uri.Builder().scheme("content").authority("com.android.externalstorage.documents").appendPath("tree").appendPath(strU + ":" + strM).build();
    }

    public static ParcelFileDescriptor o(Context context, Uri uri, String str) throws C0718i {
        try {
            return context.getContentResolver().openFileDescriptor(uri, str);
        } catch (SQLiteException e) {
            e = e;
            throw new C0718i(e);
        } catch (FileNotFoundException e2) {
            e = e2;
            throw new ax.I1.s(e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            throw new ax.I1.s(e);
        } catch (NullPointerException e4) {
            e = e4;
            throw new C0718i(e);
        } catch (SecurityException e5) {
            e = e5;
            throw new C0718i(e);
        }
    }

    public static String p(String str, String str2) {
        int iIndexOf;
        if (str2 == null || (iIndexOf = str2.indexOf(":")) < 0) {
            return null;
        }
        String strSubstring = str2.substring(iIndexOf + 1);
        return TextUtils.isEmpty(strSubstring) ? str : V.M(str, strSubstring);
    }

    public static InputStream q(Context context, AbstractC5838l abstractC5838l, long j) throws C0718i {
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(o(context, l(abstractC5838l), "r"));
            if (j != 0) {
                long jSkip = autoCloseInputStream.skip(j);
                if (jSkip != j) {
                    ax.p221pa.c.h().b("Document file SKIP FAILED").h("offst:" + j + ",skipped:" + jSkip).i();
                    throw new C0718i("AutoCloseInputStream skip failed");
                }
            }
            return autoCloseInputStream;
        } catch (FileNotFoundException e) {
            throw new ax.I1.s(e);
        } catch (IOException e2) {
            throw C0712c.a("document getInputStream", e2);
        }
    }

    public static OutputStream r(AbstractC5830d abstractC5830d, AbstractC5838l abstractC5838l, boolean z) throws Throwable {
        Uri uri;
        Uri uriD;
        String strG;
        Context contextN = abstractC5830d.n();
        Uri uriL = l(abstractC5838l);
        if (abstractC5838l.n()) {
            uri = null;
            uriD = uriL;
        } else {
            uriD = d(abstractC5830d, abstractC5838l, false);
            if (uriD == null) {
                throw new IOException("Create Document File failed");
            }
            if (!uriD.equals(uriL) && V.I(abstractC5838l.w()) && ((strG = V.g(V.h(uriD.getPath()))) == null || !strG.endsWith(")") || !strG.contains("("))) {
                ax.p221pa.c.h().g().d("UNEXPECTED DOCUMENT FILE NAME 2").h("expected:" + uriL + ",created:" + uriD).i();
            }
            uri = uriD;
        }
        try {
            return new ParcelFileDescriptor.AutoCloseOutputStream(z ? contextN.getContentResolver().openFileDescriptor(uriD, "wa") : contextN.getContentResolver().openFileDescriptor(uriD, "wt"));
        } catch (IllegalArgumentException e) {
            String str = "FILE:" + abstractC5838l.n() + ":" + z;
            if (uri != null) {
                str = str + ",CREATED:" + uri.toString() + ",EXPECTED:" + uriL.toString();
            }
            ax.p221pa.c.h().g().b("GetOutputStreamForDocumentFile failed").m(e).h(str).i();
            throw new IOException(e);
        } catch (SecurityException e2) {
            throw new C0718i(e2);
        }
    }

    public static ParcelFileDescriptor s(Context context, AbstractC5838l abstractC5838l) throws C0718i {
        return o(context, l(abstractC5838l), "rw");
    }

    public static Uri t(AbstractC5838l abstractC5838l) throws C0718i {
        if (C0740v.H(abstractC5838l)) {
            u uVar = (u) abstractC5838l;
            return e(uVar.v0(), uVar.D0(), abstractC5838l.S());
        }
        if (C0740v.C(abstractC5838l)) {
            return e(abstractC5838l.R(), ((C5834h) abstractC5838l).i0(), abstractC5838l.S());
        }
        ax.p045d2.b.f();
        throw new C0718i("Illegal File Info Class");
    }

    public static String u(Context context, ax.J1.F f, String str) {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("pref_secondary", 0);
            if (f.d() == ax.p338y1.f.r0 && (string = sharedPreferences.getString("secondaryRootUri", null)) != null) {
                return string;
            }
            String string2 = sharedPreferences.getString(v(f, str), null);
            return (ax.E1.I.m() && string2 != null && string2.startsWith("content://0@com.android.externalstorage.documents")) ? string2.replace("0@com.android.externalstorage.documents", "com.android.externalstorage.documents") : string2;
        } catch (ax.I1.J e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String v(ax.J1.F f, String str) throws ax.I1.J {
        ax.p338y1.f fVarD = f.d();
        if (ax.p338y1.f.f0(fVarD)) {
            if (str == null) {
                return "secondaryRootUri:" + f.e();
            }
            ax.p045d2.b.c(fVarD == ax.p338y1.f.r0);
            return "secondaryRootUri:" + f.e() + ":" + str;
        }
        if (fVarD == ax.p338y1.f.q0 || fVarD == ax.p338y1.f.u0) {
            if (str == null) {
                ax.p221pa.c.h().g().b("ROOT URI PREF NULL ROOT").k().i();
                throw new ax.I1.J("no rootTreePath");
            }
            String strU = ax.G1.i.D().U(f);
            if (strU == null) {
                throw new ax.I1.J("uuid is null");
            }
            return "primaryRootUri:" + strU + ":" + str;
        }
        if (ax.p338y1.f.a0(fVarD)) {
            String strU2 = ax.G1.i.D().U(f);
            if (strU2 == null) {
                throw new ax.I1.J("uuid is null");
            }
            return "documentRootUri:" + fVarD.H() + ":" + strU2;
        }
        ax.p045d2.b.g("not reachable:" + fVarD.H());
        return "documentRootUri:" + fVarD.H() + ":" + f.b();
    }

    public static String w(String str) {
        int iIndexOf;
        if (str == null || (iIndexOf = str.indexOf(":")) < 0) {
            return null;
        }
        return "/" + str.substring(iIndexOf + 1);
    }

    public static String x(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf(":")) >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public static String y(String str) {
        int iIndexOf;
        if (str != null && (iIndexOf = str.indexOf(":")) >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public static boolean z(Context context, ax.J1.F f, String str) {
        String strU;
        if (!ax.G1.i.D().u0(f) || str == null) {
            strU = u(context, f, str);
        } else {
            Uri uriN = n(f, str);
            strU = (uriN == null || !a(context, uriN)) ? null : uriN.toString();
        }
        if (TextUtils.isEmpty(strU)) {
            return false;
        }
        return C(context, f, str, Uri.parse(strU));
    }
}
