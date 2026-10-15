package com.alphainventor.filemanager.file;

import android.util.LruCache;
import ax.p063dc.C1925a0;
import ax.p063dc.C1927b0;
import ax.p063dc.C1933e0;
import ax.p063dc.C1939h0;
import ax.p063dc.C1951u;
import ax.p063dc.k0;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1966j;
import ax.p070e3.C1968l;
import ax.p070e3.C1969m;
import ax.p086f3.d0;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class N {
    private static final Logger e = Logger.getLogger("FileManager.Smb1Client");
    private Q a;
    private LruCache<String, C1927b0> b = new a(10);
    private ax.p063dc.r c;
    private String d;

    class a extends LruCache<String, C1927b0> {
        a(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int sizeOf(String str, C1927b0 c1927b0) {
            return 1;
        }
    }

    public static class b extends InputStream implements InputStreamRetargetInterface, AutoCloseable {
        private long X;
        private long Y;
        private C1939h0 q;

        public b(C1939h0 c1939h0) throws IOException {
            this.q = c1939h0;
            this.X = c1939h0.c();
        }

        public static b a(String str, ax.p063dc.r rVar) throws IOException {
            C1927b0 c1927b0 = new C1927b0(str, rVar, 1);
            if (c1927b0.s()) {
                return new b(new C1939h0(c1927b0, "r"));
            }
            throw new FileNotFoundException("SmbFile does not exist");
        }

        private IOException f(C1925a0 c1925a0) {
            Throwable thD = c1925a0.d();
            IOException iOException = c1925a0;
            if (thD instanceof ax.p095fc.d) {
                ax.p095fc.d dVar = (ax.p095fc.d) thD;
                thD = dVar.a();
                iOException = dVar;
            }
            if (!(thD instanceof InterruptedException)) {
                return iOException;
            }
            InterruptedIOException interruptedIOException = new InterruptedIOException(thD.getMessage());
            interruptedIOException.initCause(thD);
            return interruptedIOException;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            try {
                this.q.a();
            } catch (C1925a0 e) {
                e.printStackTrace();
            }
        }

        public int d() {
            return this.q.b();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.X - this.Y == 0) {
                return -1;
            }
            try {
                int iD = this.q.d();
                this.Y += (long) iD;
                return iD;
            } catch (C1925a0 e) {
                e.printStackTrace();
                throw f(e);
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            try {
                if (this.X - this.Y == 0) {
                    return -1;
                }
                int iE = this.q.e(bArr, i, i2);
                this.Y += (long) iE;
                return iE;
            } catch (C1925a0 e) {
                e.printStackTrace();
                throw f(e);
            }
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            this.q.f(this.Y + j);
            this.Y += j;
            return j;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }
    }

    public N(Q q) {
        this.a = q;
    }

    public static C1966j a(String str, C1925a0 c1925a0) {
        if (c1925a0 instanceof C1951u) {
            return new C1961e(c1925a0);
        }
        int iC = c1925a0.c();
        if (m(iC)) {
            return new ax.p070e3.t(c1925a0);
        }
        if (iC == -1073741757) {
            return new C1968l(c1925a0);
        }
        if (iC == -1073741697) {
            return new ax.p070e3.s(c1925a0);
        }
        if (iC == -1073741612) {
            return new C1969m(c1925a0);
        }
        return c1925a0.d() instanceof ax.p095fc.d ? new ax.p070e3.q(c1925a0) : C1960d.b(str, c1925a0);
    }

    public static String g(Q q, C1927b0 c1927b0) {
        String strV = c1927b0.v();
        StringBuilder sb = new StringBuilder();
        ax.U2.f fVar = ax.U2.f.O0;
        sb.append(fVar.J());
        sb.append("://");
        String strReplace = strV.replace(sb.toString(), HttpUrl.FRAGMENT_ENCODE_SET);
        if (strReplace.isEmpty()) {
            return File.separator;
        }
        int iIndexOf = strReplace.indexOf("/");
        if (iIndexOf != -1) {
            return d0.U(strReplace.substring(iIndexOf));
        }
        ax.p302sb.c.h().d("INVALID SMB PATH").k().h("PATH:" + c1927b0.v() + "," + c1927b0.z()).i();
        if (strReplace.equals(q.Z())) {
            return File.separator;
        }
        String strReplace2 = c1927b0.z().replace(fVar.J() + "://", HttpUrl.FRAGMENT_ENCODE_SET);
        int iIndexOf2 = strReplace2.indexOf("/");
        return iIndexOf2 == -1 ? d0.U(strReplace) : d0.U(strReplace2.substring(iIndexOf2));
    }

    private static boolean m(int i) {
        return i == -1073741810 || i == -1073741809 || i == -1073741773 || i == -1073741772 || i == -1073741766;
    }

    public static void r(ax.Xb.b bVar, int i) throws IOException {
        (i > 0 ? k0.s(bVar, i) : k0.s(bVar, 445)).a(10000L);
    }

    public boolean b(AbstractC6239n abstractC6239n) {
        try {
            k(abstractC6239n.E()).h();
            return true;
        } catch (C1925a0 e2) {
            e2.printStackTrace();
            return false;
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public boolean c(AbstractC6239n abstractC6239n) {
        try {
            k(abstractC6239n.E()).O();
            return true;
        } catch (C1925a0 e2) {
            e2.printStackTrace();
            return false;
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public void d(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            C1927b0 c1927b0J = j(abstractC6239n, false);
            if (abstractC6239n.isDirectory() && c1927b0J.M().length > 0) {
                throw new C1966j("SMB delete Directory failed : has children");
            }
            this.b.remove(abstractC6239n.E());
            c1927b0J.i();
        } catch (C1925a0 e2) {
            e2.printStackTrace();
            throw a("SMB1 deleteFile", e2);
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            throw new C1966j(e3);
        }
    }

    public S e(String str) throws C1966j {
        try {
            return new S(this.a, k(str));
        } catch (C1925a0 e2) {
            e2.printStackTrace();
            int iC = e2.c();
            if (m(iC)) {
                return S.g0(this.a, str, true);
            }
            e.severe("SmbException : " + iC);
            throw a("SMB1 getfileinfo", e2);
        } catch (NullPointerException e3) {
            e3.printStackTrace();
            ax.p302sb.c.h().d("SmbFileInfo Invalid Path?").h(str + ":" + l(str)).i();
            throw new C1966j(e3);
        } catch (NumberFormatException e4) {
            e4.printStackTrace();
            ax.p302sb.c.h().d("SmbFileInfo Number Format Exception").m(e4).h(str + ":" + l(str)).i();
            throw new C1966j(e4);
        } catch (MalformedURLException e5) {
            e5.printStackTrace();
            ax.p302sb.c.h().g().d("MARFORMED URL 1").m(e5).h("prefix:" + this.d + ",path:" + str + ",connected:" + this.a.a()).i();
            throw new C1966j(e5);
        }
    }

    public InputStream f(AbstractC6239n abstractC6239n, long j) throws C1966j {
        try {
            b bVarA = b.a(l(abstractC6239n.E()), this.c);
            if (j != 0) {
                bVarA.skip(j);
            }
            return new BufferedInputStream(bVarA, bVarA.d());
        } catch (C1925a0 e2) {
            throw a("SMB1 getInputStream", e2);
        } catch (FileNotFoundException e3) {
            throw new ax.p070e3.t(e3);
        } catch (IOException e4) {
            throw new C1966j(e4);
        }
    }

    public OutputStream h(AbstractC6239n abstractC6239n, boolean z) throws C1966j {
        try {
            C1927b0 c1927b0J = j(abstractC6239n, false);
            C1933e0 c1933e0D = z ? C1933e0.d(c1927b0J) : C1933e0.a(c1927b0J);
            return new BufferedOutputStream(c1933e0D, c1933e0D.h());
        } catch (C1925a0 e2) {
            throw a("SMB1 getOutputStream", e2);
        } catch (IOException e3) {
            throw C1960d.b("SMB1 getOutputStream", e3);
        }
    }

    public ax.p086f3.k0 i(AbstractC6239n abstractC6239n) throws C1966j {
        C1927b0 c1927b0K0 = ((S) abstractC6239n).k0();
        if (c1927b0K0 == null) {
            throw new C1966j("smb file is null");
        }
        try {
            long jW = c1927b0K0.w();
            long jL = c1927b0K0.L();
            return new ax.p086f3.k0(jL, jL - jW);
        } catch (Exception e2) {
            throw new C1966j(e2);
        }
    }

    public C1927b0 j(AbstractC6239n abstractC6239n, boolean z) throws MalformedURLException {
        C1927b0 c1927b0K;
        boolean z2 = abstractC6239n.isDirectory() || z;
        S s = (S) abstractC6239n;
        C1927b0 c1927b0K0 = s.k0();
        if (c1927b0K0 != null && (!z2 || c1927b0K0.getURL().getPath().endsWith("/"))) {
            return c1927b0K0;
        }
        if (z2) {
            c1927b0K = k(abstractC6239n.E() + "/");
        } else {
            c1927b0K = k(abstractC6239n.E());
        }
        s.l0(c1927b0K);
        return c1927b0K;
    }

    public C1927b0 k(String str) throws MalformedURLException {
        C1927b0 c1927b0 = this.b.get(str);
        if (c1927b0 != null) {
            return c1927b0;
        }
        C1927b0 c1927b1 = new C1927b0(l(str), this.c);
        this.b.put(str, c1927b1);
        if (str.endsWith("/")) {
            this.b.remove(str.substring(0, str.length() - 1));
            return c1927b1;
        }
        this.b.remove(str + "/");
        return c1927b1;
    }

    String l(String str) {
        return Q.h0(this.d, str);
    }

    public List<AbstractC6239n> n(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            C1927b0 c1927b0 = new C1927b0(l(d0.O(abstractC6239n.E())), this.c);
            boolean zE = d0.E(abstractC6239n);
            ArrayList arrayList = new ArrayList();
            C1927b0[] c1927b0ArrM = c1927b0.M();
            if (c1927b0ArrM != null) {
                for (C1927b0 c1927b1 : c1927b0ArrM) {
                    if (!zE || c1927b1.C() == 8) {
                        arrayList.add(new S(this.a, c1927b1));
                    }
                }
            }
            return arrayList;
        } catch (C1925a0 e2) {
            throw a("SMB1 listChildren", e2);
        } catch (NullPointerException e3) {
            throw new C1966j(e3);
        } catch (MalformedURLException e4) {
            ax.p302sb.c.h().g().d("MARFORMED URL 2").m(e4).i();
            throw new C1966j(e4);
        }
    }

    public void o(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) throws C1966j {
        try {
            C1927b0 c1927b0J = j(abstractC6239n, false);
            c1927b0J.W(j(abstractC6239n2, c1927b0J.G()));
        } catch (C1925a0 e2) {
            e2.printStackTrace();
            throw a("SMB1 moveFile", e2);
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            throw new C1966j(e3);
        }
    }

    public void p(AbstractC6239n abstractC6239n, long j) throws C1966j {
        try {
            j(abstractC6239n, false).a0(j);
        } catch (C1925a0 e2) {
            throw a("SMB1 setLastModified", e2);
        } catch (IOException e3) {
            throw C1960d.b("SMB1 setLastModified", e3);
        }
    }

    public void q(ax.p063dc.r rVar, String str) {
        this.c = rVar;
        this.d = str;
    }
}
