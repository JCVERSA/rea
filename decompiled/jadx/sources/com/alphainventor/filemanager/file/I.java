package com.alphainventor.filemanager.file;

import android.util.LruCache;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0718i;
import ax.J1.V;
import ax.J1.b0;
import ax.Ta.C4736a0;
import ax.Ta.C4738b0;
import ax.Ta.C4744e0;
import ax.Ta.C4750h0;
import ax.Ta.C4762u;
import ax.Ta.k0;
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
public class I {
    private static final Logger e = Logger.getLogger("FileManager.Smb1Client");
    private L a;
    private LruCache<String, C4738b0> b = new a(10);
    private ax.Ta.r c;
    private String d;

    class a extends LruCache<String, C4738b0> {
        a(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int sizeOf(String str, C4738b0 c4738b0) {
            return 1;
        }
    }

    public I(L l) {
        this.a = l;
    }

    public static C0718i a(String str, C4736a0 c4736a0) {
        if (c4736a0 instanceof C4762u) {
            return new C0713d(c4736a0);
        }
        int iC = c4736a0.c();
        if (m(iC)) {
            return new ax.I1.s(c4736a0);
        }
        if (iC == -1073741757) {
            return new ax.I1.k(c4736a0);
        }
        if (iC == -1073741697) {
            return new ax.I1.r(c4736a0);
        }
        if (iC == -1073741612) {
            return new ax.I1.l(c4736a0);
        }
        return c4736a0.d() instanceof ax.Va.d ? new ax.I1.p(c4736a0) : C0712c.b(str, c4736a0);
    }

    public static String g(L l, C4738b0 c4738b0) {
        String strV = c4738b0.v();
        StringBuilder sb = new StringBuilder();
        ax.p338y1.f fVar = ax.p338y1.f.L0;
        sb.append(fVar.J());
        sb.append("://");
        String strReplace = strV.replace(sb.toString(), HttpUrl.FRAGMENT_ENCODE_SET);
        if (strReplace.isEmpty()) {
            return File.separator;
        }
        int iIndexOf = strReplace.indexOf("/");
        if (iIndexOf != -1) {
            return V.Q(strReplace.substring(iIndexOf));
        }
        ax.p221pa.c.h().d("INVALID SMB PATH").k().h("PATH:" + c4738b0.v() + "," + c4738b0.z()).i();
        if (strReplace.equals(l.T())) {
            return File.separator;
        }
        String strReplace2 = c4738b0.z().replace(fVar.J() + "://", HttpUrl.FRAGMENT_ENCODE_SET);
        int iIndexOf2 = strReplace2.indexOf("/");
        return iIndexOf2 == -1 ? V.Q(strReplace) : V.Q(strReplace2.substring(iIndexOf2));
    }

    private static boolean m(int i) {
        return i == -1073741810 || i == -1073741809 || i == -1073741773 || i == -1073741772 || i == -1073741766;
    }

    public static void r(ax.Na.b bVar, int i) throws IOException {
        (i > 0 ? k0.s(bVar, i) : k0.s(bVar, 445)).a(10000L);
    }

    public boolean b(AbstractC5838l abstractC5838l) {
        try {
            k(abstractC5838l.C()).h();
            return true;
        } catch (C4736a0 e2) {
            e2.printStackTrace();
            return false;
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public boolean c(AbstractC5838l abstractC5838l) {
        try {
            k(abstractC5838l.C()).O();
            return true;
        } catch (C4736a0 e2) {
            e2.printStackTrace();
            return false;
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public void d(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            C4738b0 c4738b0J = j(abstractC5838l, false);
            if (abstractC5838l.isDirectory() && c4738b0J.M().length > 0) {
                throw new C0718i("SMB delete Directory failed : has children");
            }
            this.b.remove(abstractC5838l.C());
            c4738b0J.i();
        } catch (C4736a0 e2) {
            e2.printStackTrace();
            throw a("SMB1 deleteFile", e2);
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            throw new C0718i(e3);
        }
    }

    public M e(String str) throws C0718i {
        try {
            return new M(this.a, k(str));
        } catch (C4736a0 e2) {
            e2.printStackTrace();
            int iC = e2.c();
            if (m(iC)) {
                return M.d0(this.a, str, true);
            }
            e.severe("SmbException : " + iC);
            throw a("SMB1 getfileinfo", e2);
        } catch (NullPointerException e3) {
            e3.printStackTrace();
            ax.p221pa.c.h().d("SmbFileInfo Invalid Path?").h(str + ":" + l(str)).i();
            throw new C0718i(e3);
        } catch (NumberFormatException e4) {
            e4.printStackTrace();
            ax.p221pa.c.h().d("SmbFileInfo Number Format Exception").m(e4).h(str + ":" + l(str)).i();
            throw new C0718i(e4);
        } catch (MalformedURLException e5) {
            e5.printStackTrace();
            ax.p221pa.c.h().g().d("MARFORMED URL 1").m(e5).h("prefix:" + this.d + ",path:" + str + ",connected:" + this.a.a()).i();
            throw new C0718i(e5);
        }
    }

    public InputStream f(AbstractC5838l abstractC5838l, long j) throws C0718i {
        try {
            b bVarA = b.a(l(abstractC5838l.C()), this.c);
            if (j != 0) {
                bVarA.skip(j);
            }
            return new BufferedInputStream(bVarA, bVarA.d());
        } catch (C4736a0 e2) {
            throw a("SMB1 getInputStream", e2);
        } catch (FileNotFoundException e3) {
            throw new ax.I1.s(e3);
        } catch (IOException e4) {
            throw new C0718i(e4);
        }
    }

    public OutputStream h(AbstractC5838l abstractC5838l, boolean z) throws C0718i {
        try {
            C4738b0 c4738b0J = j(abstractC5838l, false);
            C4744e0 c4744e0D = z ? C4744e0.d(c4738b0J) : C4744e0.a(c4738b0J);
            return new BufferedOutputStream(c4744e0D, c4744e0D.g());
        } catch (C4736a0 e2) {
            throw a("SMB1 getOutputStream", e2);
        } catch (IOException e3) {
            throw C0712c.b("SMB1 getOutputStream", e3);
        }
    }

    public b0 i(AbstractC5838l abstractC5838l) throws C0718i {
        C4738b0 c4738b0H0 = ((M) abstractC5838l).h0();
        if (c4738b0H0 == null) {
            throw new C0718i("smb file is null");
        }
        try {
            long jW = c4738b0H0.w();
            long jL = c4738b0H0.L();
            return new b0(jL, jL - jW);
        } catch (Exception e2) {
            throw new C0718i(e2);
        }
    }

    public C4738b0 j(AbstractC5838l abstractC5838l, boolean z) throws MalformedURLException {
        C4738b0 c4738b0K;
        boolean z2 = abstractC5838l.isDirectory() || z;
        M m = (M) abstractC5838l;
        C4738b0 c4738b0H0 = m.h0();
        if (c4738b0H0 != null && (!z2 || c4738b0H0.getURL().getPath().endsWith("/"))) {
            return c4738b0H0;
        }
        if (z2) {
            c4738b0K = k(abstractC5838l.C() + "/");
        } else {
            c4738b0K = k(abstractC5838l.C());
        }
        m.i0(c4738b0K);
        return c4738b0K;
    }

    public C4738b0 k(String str) throws MalformedURLException {
        C4738b0 c4738b0 = this.b.get(str);
        if (c4738b0 != null) {
            return c4738b0;
        }
        C4738b0 c4738b1 = new C4738b0(l(str), this.c);
        this.b.put(str, c4738b1);
        if (str.endsWith("/")) {
            this.b.remove(str.substring(0, str.length() - 1));
        } else {
            this.b.remove(str + "/");
        }
        return c4738b1;
    }

    String l(String str) {
        return L.b0(this.d, str);
    }

    public List<AbstractC5838l> n(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            C4738b0 c4738b0 = new C4738b0(l(V.K(abstractC5838l.C())), this.c);
            boolean zB = V.B(abstractC5838l);
            ArrayList arrayList = new ArrayList();
            C4738b0[] c4738b0ArrM = c4738b0.M();
            if (c4738b0ArrM != null) {
                for (C4738b0 c4738b1 : c4738b0ArrM) {
                    if (!zB || c4738b1.C() == 8) {
                        arrayList.add(new M(this.a, c4738b1));
                    }
                }
            }
            return arrayList;
        } catch (C4736a0 e2) {
            throw a("SMB1 listChildren", e2);
        } catch (NullPointerException e3) {
            throw new C0718i(e3);
        } catch (MalformedURLException e4) {
            ax.p221pa.c.h().g().d("MARFORMED URL 2").m(e4).i();
            throw new C0718i(e4);
        }
    }

    public void o(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) throws C0718i {
        try {
            C4738b0 c4738b0J = j(abstractC5838l, false);
            c4738b0J.W(j(abstractC5838l2, c4738b0J.G()));
        } catch (C4736a0 e2) {
            e2.printStackTrace();
            throw a("SMB1 moveFile", e2);
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            throw new C0718i(e3);
        }
    }

    public void p(AbstractC5838l abstractC5838l, long j) throws C0718i {
        try {
            j(abstractC5838l, false).a0(j);
        } catch (C4736a0 e2) {
            throw a("SMB1 setLastModified", e2);
        } catch (IOException e3) {
            throw C0712c.b("SMB1 setLastModified", e3);
        }
    }

    public void q(ax.Ta.r rVar, String str) {
        this.c = rVar;
        this.d = str;
    }

    public static class b extends InputStream implements InputStreamRetargetInterface {
        private long X;
        private long Y;
        private C4750h0 q;

        public b(C4750h0 c4750h0) throws IOException {
            this.q = c4750h0;
            this.X = c4750h0.c();
        }

        public static b a(String str, ax.Ta.r rVar) throws IOException {
            C4738b0 c4738b0 = new C4738b0(str, rVar, 1);
            if (c4738b0.s()) {
                return new b(new C4750h0(c4738b0, "r"));
            }
            throw new FileNotFoundException("SmbFile does not exist");
        }

        private IOException f(C4736a0 c4736a0) {
            Throwable thD = c4736a0.d();
            IOException iOException = c4736a0;
            if (thD instanceof ax.Va.d) {
                ax.Va.d dVar = (ax.Va.d) thD;
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
            } catch (C4736a0 e) {
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
            } catch (C4736a0 e) {
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
            } catch (C4736a0 e) {
                e.printStackTrace();
                throw f(e);
            }
        }
    }
}
