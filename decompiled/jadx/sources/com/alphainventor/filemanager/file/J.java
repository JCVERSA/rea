package com.alphainventor.filemanager.file;

import android.system.ErrnoException;
import android.text.TextUtils;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0717h;
import ax.I1.C0718i;
import ax.J1.V;
import ax.J1.b0;
import ax.J8.EnumC0748d;
import ax.J8.EnumC0749e;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class J {
    private static final Logger h = Logger.getLogger("FileManager.Smb2Client");
    L a;
    ax.Z8.c b;
    ax.p122i9.b c;
    String d;
    boolean e;
    boolean f;
    ConcurrentHashMap<String, Long> g = new ConcurrentHashMap<>();

    private static class c {
        String a;
        String b;

        c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        static c a(AbstractC5838l abstractC5838l) {
            return b(abstractC5838l.C());
        }

        static c b(String str) {
            if ("/".equals(str)) {
                return new c(null, null);
            }
            int iIndexOf = str.indexOf("/", 1);
            return iIndexOf < 0 ? new c(str.substring(1), null) : new c(str.substring(1, iIndexOf), J.m(str.substring(iIndexOf + 1)));
        }
    }

    static class d extends ax.U1.a {
        private final boolean a = true;
        private final byte[] b = new byte[4096];
        private int c = -1;
        private long d = -1;
        private final ax.p136j9.d e;

        d(ax.p136j9.d dVar) {
            this.e = dVar;
        }

        private int f(long j, byte[] bArr, int i, int i2) {
            int i3 = (int) (j - this.d);
            int i4 = this.c - i3;
            if (i2 > i4) {
                i2 = i4;
            }
            System.arraycopy(this.b, i3, bArr, i, i2);
            return i2;
        }

        private boolean g(long j, int i) {
            long j2 = this.d;
            return j >= j2 && j + ((long) i) <= j2 + ((long) this.c);
        }

        private boolean h(int i) {
            return i <= 2048;
        }

        @Override // ax.U1.a
        public void a() throws ErrnoException {
        }

        @Override // ax.U1.a
        public long b() throws ErrnoException {
            return this.e.t().b().a();
        }

        @Override // ax.U1.a
        public int c(long j, byte[] bArr, int i, int i2) throws ErrnoException {
            if (g(j, i2)) {
                return f(j, bArr, i, i2);
            }
            try {
                if (h(i2)) {
                    int iD1 = this.e.d1(this.b, j, 0, 4096);
                    if (iD1 < 0) {
                        return iD1;
                    }
                    this.c = iD1;
                    this.d = j;
                    return f(j, bArr, i, i2);
                }
                if (i2 <= 262144) {
                    return this.e.d1(bArr, j, i, i2);
                }
                int i3 = 0;
                while (i2 > 0) {
                    int iD2 = this.e.d1(bArr, j + ((long) i3), i + i3, Math.min(i2, 262144));
                    if (iD2 < 0) {
                        if (i3 == 0) {
                            return iD2;
                        }
                    } else {
                        if (iD2 == 0) {
                            ax.p221pa.c.h().g().b("SMB2 FILE READ LEN 0").i();
                            break;
                        }
                        i3 += iD2;
                        i2 -= iD2;
                    }
                }
                return i3;
            } catch (ax.p024b9.d e) {
                throw com.alphainventor.filemanager.provider.a.a(J.g(e), "onRead");
            }
        }

        @Override // ax.U1.a
        public void d() {
            try {
                this.e.close();
            } catch (ax.p024b9.d e) {
                e.printStackTrace();
            }
        }

        @Override // ax.U1.a
        public int e(long j, byte[] bArr, int i, int i2) throws ErrnoException {
            try {
                return this.e.f1(bArr, j, i, i2);
            } catch (ax.p024b9.d e) {
                throw com.alphainventor.filemanager.provider.a.a(J.g(e), "onWrite");
            }
        }
    }

    public J(L l) {
        this.a = l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ax.p136j9.d A(ax.p122i9.b bVar, c cVar) throws IOException {
        return ((ax.p136j9.c) bVar.d(cVar.a)).h1(cVar.b, EnumSet.of(ax.C8.a.GENERIC_READ), null, ax.J8.u.h0, EnumC0748d.FILE_OPEN, EnumSet.of(EnumC0749e.FILE_SEQUENTIAL_ONLY));
    }

    private ax.p136j9.d B(ax.p122i9.b bVar, c cVar) {
        return ((ax.p136j9.c) bVar.d(cVar.a)).h1(cVar.b, EnumSet.of(ax.C8.a.GENERIC_WRITE), null, ax.J8.u.h0, EnumC0748d.FILE_OPEN, EnumSet.of(EnumC0749e.FILE_SEQUENTIAL_ONLY));
    }

    private ax.p136j9.d C(ax.p122i9.b bVar, c cVar) {
        return ((ax.p136j9.c) bVar.d(cVar.a)).h1(cVar.b, EnumSet.of(ax.C8.a.GENERIC_WRITE), null, ax.J8.u.h0, EnumC0748d.FILE_CREATE, EnumSet.of(EnumC0749e.FILE_SEQUENTIAL_ONLY));
    }

    public static ax.p122i9.b d(ax.Z8.c cVar, String str, int i, ax.p010a9.b bVar) throws ax.p024b9.d, IOException {
        ax.p038c9.a aVarF = f(cVar, str, i);
        ax.p122i9.b bVarA = aVarF.A(bVar);
        String strC = bVar.c();
        ax.p038c9.b bVarK = aVarF.K();
        if (!bVarA.B() || "GUEST".equalsIgnoreCase(strC) || "SMB2GUESTTESTACCOUNT".equalsIgnoreCase(strC) || bVarK == null || !bVarK.l()) {
            return bVarA;
        }
        bVarA.close();
        h.severe("Bad user mapped to guest!");
        throw new ax.p024b9.d("Bad user mapped to guest!!");
    }

    private boolean e(K k) {
        if (this.b.h(k.e, k.f)) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str = k.e + ":" + k.f;
            Long l = this.g.get(str);
            if (l != null && jCurrentTimeMillis - l.longValue() < 1500) {
                return true;
            }
            ax.p038c9.a aVarF = null;
            try {
                aVarF = f(this.b, k.e, k.f);
                aVarF.E(false);
                if (aVarF.C0()) {
                    this.g.put(str, Long.valueOf(jCurrentTimeMillis));
                }
                return aVarF.C0();
            } catch (ax.p024b9.d | IOException unused) {
                if (aVarF != null) {
                    try {
                        aVarF.E(true);
                    } catch (Exception unused2) {
                    }
                }
                h.severe("SMB2 connection check failed");
            }
        }
        return false;
    }

    public static ax.p038c9.a f(ax.Z8.c cVar, String str, int i) throws ax.p024b9.d, IOException {
        try {
            return i > 0 ? cVar.d(str, i) : cVar.a(str);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new IOException("SMB connect", e);
        }
    }

    public static C0718i g(Exception exc) {
        ax.J8.F f;
        if (exc instanceof ax.J8.F) {
            f = (ax.J8.F) exc;
        } else {
            f = ((exc instanceof IOException) && (exc.getCause() instanceof ax.J8.F)) ? (ax.J8.F) exc.getCause() : null;
        }
        if (f != null) {
            ax.D8.a aVarA = f.a();
            if (u(aVarA)) {
                return new ax.I1.s(exc);
            }
            if (t(aVarA)) {
                return new C0713d(exc);
            }
            if (aVarA == ax.D8.a.STATUS_DISK_FULL) {
                return new ax.I1.r(exc);
            }
            if (aVarA == ax.D8.a.STATUS_NOT_SAME_DEVICE) {
                return new ax.I1.l(exc);
            }
            if (aVarA == ax.D8.a.STATUS_BAD_NETWORK_NAME) {
                return new C0713d(exc);
            }
            if (aVarA == ax.D8.a.STATUS_SHARING_VIOLATION) {
                return new ax.I1.x(String.format("%s (0x%08x)", aVarA.name(), Long.valueOf(aVarA.getValue())), exc);
            }
            if (aVarA != null) {
                if (aVarA != ax.D8.a.STATUS_OTHER) {
                    return new C0717h(String.format("%s (0x%08x)", aVarA.name(), Long.valueOf(aVarA.getValue())), exc);
                }
                return new C0718i("NtStatus : " + aVarA.getValue(), exc);
            }
        }
        return exc instanceof ConnectException ? new ax.I1.p(exc) : new C0718i(exc);
    }

    public static ax.Z8.c j(String str, boolean z) {
        ax.Z8.d.b bVarV = ax.Z8.d.v();
        bVarV.k(z);
        if ("SMB3".equals(str)) {
            bVarV.i(ax.J8.g.SMB_3_1_1, ax.J8.g.SMB_3_0_2, ax.J8.g.SMB_3_0);
            bVarV.j(true);
        } else if ("SMB2".equals(str)) {
            bVarV.i(ax.J8.g.SMB_2_1, ax.J8.g.SMB_2_0_2);
        } else {
            bVarV.j(true);
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        bVarV.q(35000L, timeUnit);
        bVarV.u(12500L, timeUnit);
        bVarV.n(15000L, timeUnit);
        bVarV.x(15000L, timeUnit);
        bVarV.f(3500L, timeUnit);
        bVarV.m(262144);
        bVarV.w(262144);
        return new ax.Z8.c(bVarV.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String m(String str) {
        return str.replace('/', '\\');
    }

    public static ax.p010a9.b n(String str, String str2, String str3) {
        return new ax.p010a9.b(str2, str3 == null ? new char[0] : str3.toCharArray(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized ax.p122i9.b r() throws ax.p024b9.d, IOException {
        ax.Z8.c cVar;
        try {
            K kZ = this.a.Z();
            if (this.f && (cVar = this.b) != null) {
                cVar.close();
                this.b = null;
            }
            if (this.b == null) {
                this.b = j(this.d, this.e);
                this.f = false;
            }
            if (!e(kZ)) {
                this.c = null;
            }
            ax.p122i9.b bVar = this.c;
            if (bVar == null || bVar.A() || this.c.x() || !v(this.c.j(), kZ.b)) {
                ax.p122i9.b bVar2 = this.c;
                if (bVar2 != null && !bVar2.x()) {
                    try {
                        this.c.close();
                    } catch (ax.p024b9.d | IOException unused) {
                    }
                }
                this.c = d(this.b, kZ.e, kZ.f, kZ.b);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.c;
    }

    private static boolean t(ax.D8.a aVar) {
        return aVar == ax.D8.a.STATUS_ACCESS_DENIED || aVar == ax.D8.a.STATUS_LOGON_FAILURE;
    }

    private static boolean u(ax.D8.a aVar) {
        return aVar == ax.D8.a.STATUS_NO_SUCH_FILE || aVar == ax.D8.a.STATUS_OBJECT_NAME_INVALID || aVar == ax.D8.a.STATUS_OBJECT_NAME_NOT_FOUND || aVar == ax.D8.a.STATUS_OBJECT_PATH_NOT_FOUND;
    }

    private boolean v(ax.p010a9.b bVar, ax.p010a9.b bVar2) {
        if (bVar == bVar2) {
            return true;
        }
        return bVar != null && bVar2 != null && ax.p045d2.x.i(bVar.c(), bVar2.c()) && ax.p045d2.x.i(bVar.b(), bVar2.b()) && ax.p045d2.x.i(bVar.a(), bVar2.a());
    }

    private ax.p136j9.d y(ax.p122i9.b bVar, c cVar) {
        return ((ax.p136j9.c) bVar.d(cVar.a)).h1(cVar.b, EnumSet.of(ax.C8.a.GENERIC_WRITE), null, ax.J8.u.h0, EnumC0748d.FILE_OVERWRITE_IF, EnumSet.of(EnumC0749e.FILE_SEQUENTIAL_ONLY));
    }

    private ax.p136j9.d z(ax.p122i9.b bVar, c cVar, int i) throws C0718i, IOException {
        EnumSet enumSetOf;
        EnumC0748d enumC0748d;
        if ((536870912 & i) != 0) {
            enumSetOf = EnumSet.of(ax.C8.a.GENERIC_WRITE);
        } else if ((268435456 & i) != 0) {
            enumSetOf = EnumSet.of(ax.C8.a.GENERIC_READ);
        } else {
            if ((805306368 & i) == 0) {
                throw new ax.I1.s("Not supported mode :" + i);
            }
            enumSetOf = EnumSet.of(ax.C8.a.GENERIC_READ, ax.C8.a.GENERIC_WRITE);
        }
        EnumSet enumSet = enumSetOf;
        if ((67108864 & i) != 0) {
            enumC0748d = EnumC0748d.FILE_OVERWRITE_IF;
        } else {
            if ((33554432 & i) != 0) {
                throw new ax.I1.s("Not supported mode :" + i);
            }
            enumC0748d = EnumC0748d.FILE_OPEN;
        }
        return ((ax.p136j9.c) bVar.d(cVar.a)).h1(cVar.b, enumSet, null, ax.J8.u.h0, enumC0748d, EnumSet.of(EnumC0749e.FILE_RANDOM_ACCESS));
    }

    ax.U1.a D(String str, int i) throws C0718i {
        h.fine("Open SMB2 proxy file : " + str);
        try {
            ax.p122i9.b bVarR = r();
            c cVarB = c.b(str);
            if (cVarB.b != null) {
                return new d(z(bVarR, cVarB, i));
            }
            throw new C0718i("SMB Share cannot be target");
        } catch (ax.J8.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C0712c.b("smb2 getoutputstream", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C0712c.b("smb2 getoutputstream", e);
        }
    }

    public void E(AbstractC5838l abstractC5838l, long j) throws C0718i {
        AutoCloseable autoCloseable = null;
        try {
            try {
                ax.p122i9.b bVarR = r();
                c cVarA = c.a(abstractC5838l);
                if (cVarA.b == null) {
                    throw new C0718i("SMB Share cannot be target");
                }
                ax.p136j9.d dVarB = B(bVarR, cVarA);
                ax.C8.b bVar = ax.G8.e.f;
                dVarB.v0(new ax.G8.e(bVar, bVar, ax.C8.b.d(j), bVar, 0L));
                try {
                    dVarB.close();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        autoCloseable.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (ax.J8.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C0712c.b("smb2 setLastModified", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C0712c.b("smb2 setLastModified", e);
        }
    }

    public void F(String str, boolean z) {
        this.d = str;
        this.e = z;
    }

    public boolean h(AbstractC5838l abstractC5838l) {
        try {
            ax.p122i9.b bVarR = r();
            c cVarB = c.b(abstractC5838l.C());
            if (cVarB.b == null) {
                return false;
            }
            C(bVarR, cVarB).close();
            return true;
        } catch (ax.p024b9.d | IOException unused) {
            return false;
        }
    }

    public boolean i(AbstractC5838l abstractC5838l) {
        try {
            ax.p122i9.b bVarR = r();
            c cVarB = c.b(abstractC5838l.C());
            if (cVarB.b == null) {
                return false;
            }
            ((ax.p136j9.c) bVarR.d(cVarB.a)).e1(cVarB.b);
            return true;
        } catch (ax.p024b9.d | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void k(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            ax.p122i9.b bVarR = r();
            c cVarA = c.a(abstractC5838l);
            if (cVarA.b == null) {
                throw new C0718i("SMB Share cannot be deleted");
            }
            ax.p136j9.c cVar = (ax.p136j9.c) bVarR.d(cVarA.a);
            if (abstractC5838l.isDirectory()) {
                cVar.l1(cVarA.b, false);
            } else {
                cVar.k1(cVarA.b);
            }
        } catch (ax.J8.F e) {
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            throw new C0718i(e);
        } catch (IOException e3) {
            e = e3;
            throw new C0718i(e);
        }
    }

    void l() {
        ax.p122i9.b bVar = this.c;
        if (bVar != null && !bVar.x()) {
            this.c.i();
        }
        this.f = true;
    }

    public M o(String str) throws C0718i {
        try {
            if ("/".equals(str)) {
                return M.e0(this.a, str);
            }
            ax.p122i9.b bVarR = r();
            c cVarB = c.b(str);
            ax.p136j9.m mVarD = bVarR.d(cVarB.a);
            if (mVarD instanceof ax.p136j9.c) {
                return new M(this.a, str, ((ax.p136j9.c) mVarD).L0(cVarB.b));
            }
            throw new C0718i("This type of share is not supported : " + mVarD.getClass().getName());
        } catch (ax.J8.F e) {
            if (u(e.a())) {
                return M.d0(this.a, str, false);
            }
            e.printStackTrace();
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C0712c.b("smb2 getfileinfo", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C0712c.b("smb2 getfileinfo", e);
        } catch (IllegalArgumentException e4) {
            e = e4;
            e.printStackTrace();
            throw C0712c.b("smb2 getfileinfo", e);
        } catch (StringIndexOutOfBoundsException e5) {
            ax.p221pa.c.h().g().b("SMB INVALID SHARENAME").h("path:" + str).i();
            throw C0712c.b("smb2 getfileinfo 2", e5);
        }
    }

    public InputStream p(AbstractC5838l abstractC5838l, long j) throws C0718i {
        try {
            c cVarA = c.a(abstractC5838l);
            if (cVarA.b != null) {
                ax.p122i9.b bVarR = r();
                return new a(this, bVarR, A(bVarR, cVarA), cVarA, j);
            }
            ax.p221pa.c.h().g().b("Invalid SMB File Path").k().h(abstractC5838l.C()).i();
            throw new C0718i("Invalid File Path!!");
        } catch (ax.J8.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw g(e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw g(e);
        }
    }

    public OutputStream q(AbstractC5838l abstractC5838l, boolean z) throws C0718i {
        try {
            ax.p122i9.b bVarR = r();
            c cVarA = c.a(abstractC5838l);
            if (cVarA.b != null) {
                return new b(bVarR, z ? y(bVarR, cVarA) : C(bVarR, cVarA));
            }
            throw new C0718i("SMB Share cannot be target");
        } catch (ax.J8.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C0712c.b("smb2 getoutputstream", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C0712c.b("smb2 getoutputstream", e);
        }
    }

    public b0 s(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            ax.p122i9.b bVarR = r();
            String str = c.a(abstractC5838l).a;
            if (str == null) {
                throw new C0718i();
            }
            ax.G8.B bY0 = ((ax.p136j9.c) bVarR.d(str)).Y0();
            long jB = bY0.b();
            return new b0(jB, jB - bY0.a());
        } catch (ax.J8.F e) {
            h.severe("SMBApiException:" + e.a());
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (ArrayIndexOutOfBoundsException e4) {
            e = e4;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (ClassCastException e5) {
            e = e5;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (IllegalArgumentException e6) {
            e = e6;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (StringIndexOutOfBoundsException e7) {
            e = e7;
            e.printStackTrace();
            throw new C0718i(e);
        }
    }

    public List<AbstractC5838l> w(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            ax.p122i9.b bVarR = r();
            ArrayList arrayList = new ArrayList();
            String strC = abstractC5838l.C();
            if ("/".equals(strC)) {
                for (ax.Y9.b bVar : new ax.X9.a(ax.p053da.c.h0.h(bVarR)).g()) {
                    if (bVar.d() == 0 || bVar.d() == Integer.MIN_VALUE) {
                        arrayList.add(M.e0(this.a, "/" + bVar.b()));
                    }
                }
                return arrayList;
            }
            c cVarA = c.a(abstractC5838l);
            for (ax.G8.m mVar : ((ax.p136j9.c) bVarR.d(cVarA.a)).a1(cVarA.b)) {
                String strA = mVar.a();
                if (!V.z(strA) && !TextUtils.isEmpty(strA)) {
                    if (strA.endsWith("/")) {
                        ax.p221pa.c.h().g().b("INVALID SMB2 FILENAME").h(strA).i();
                    } else {
                        arrayList.add(new M(this.a, V.M(strC, strA), mVar));
                    }
                }
            }
            return arrayList;
        } catch (ax.J8.F e) {
            h.severe("SMBApiException:" + e.a());
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (ArrayIndexOutOfBoundsException e4) {
            e = e4;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (ClassCastException e5) {
            e = e5;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (IllegalArgumentException e6) {
            e = e6;
            e.printStackTrace();
            throw new C0718i(e);
        } catch (StringIndexOutOfBoundsException e7) {
            e = e7;
            e.printStackTrace();
            throw new C0718i(e);
        }
    }

    public void x(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) throws C0718i {
        try {
            ax.p122i9.b bVarR = r();
            c cVarA = c.a(abstractC5838l);
            if (cVarA.b == null) {
                throw new C0718i("SMB Share cannot be moved");
            }
            ax.p136j9.b bVarF1 = ((ax.p136j9.c) bVarR.d(cVarA.a)).f1(cVarA.b, EnumSet.of(ax.C8.a.FILE_READ_ATTRIBUTES, ax.C8.a.DELETE, ax.C8.a.SYNCHRONIZE), null, ax.J8.u.h0, EnumC0748d.FILE_OPEN, null);
            try {
                c cVarA2 = c.a(abstractC5838l2);
                if (cVarA2.b == null) {
                    throw new C0718i("SMB Share cannot be target");
                }
                if (!cVarA.a.equals(cVarA2.a)) {
                    throw new C0718i("SMB cannot move to different share!");
                }
                bVarF1.G(cVarA2.b, false);
                bVarF1.close();
            } catch (Throwable th) {
                bVarF1.close();
                throw th;
            }
        } catch (ax.J8.F e) {
            throw g(e);
        } catch (ax.p024b9.d e2) {
            e = e2;
            throw new C0718i(e);
        } catch (IOException e3) {
            e = e3;
            throw new C0718i(e);
        }
    }

    static class b extends ax.J1.S {
        ax.p136j9.d X;
        ax.p122i9.b Y;

        b(ax.p122i9.b bVar, ax.p136j9.d dVar) {
            super(dVar.E0(false));
            this.Y = bVar;
            this.X = dVar;
        }

        @Override // ax.J1.S, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                super.close();
                try {
                    this.X.close();
                } catch (ax.p024b9.d e) {
                    e.printStackTrace();
                    throw new IOException(e);
                }
            } catch (ax.p024b9.d e2) {
                e2.printStackTrace();
                throw new IOException(e2);
            }
        }

        @Override // ax.J1.S, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (ax.p024b9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.J1.S, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            try {
                super.write(bArr);
            } catch (ax.p024b9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.J1.S, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                super.write(bArr, i, i2);
            } catch (ax.p024b9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.J1.S, java.io.OutputStream
        public void write(int i) throws IOException {
            try {
                super.write(i);
            } catch (ax.p024b9.d e) {
                throw new IOException(e);
            }
        }
    }

    static class a extends ax.J1.B {
        J X;
        ax.p122i9.b Y;
        ax.p136j9.d Z;
        c h0;
        long i0;
        long j0;

        a(J j, ax.p122i9.b bVar, ax.p136j9.d dVar, c cVar, long j2) {
            super(dVar.C0(j2, null));
            this.Y = bVar;
            this.Z = dVar;
            this.h0 = cVar;
            this.X = j;
            this.i0 = j2;
        }

        private void d() throws IOException {
            try {
                ax.p122i9.b bVarR = this.X.r();
                ax.p136j9.d dVarA = this.X.A(bVarR, this.h0);
                InputStream inputStreamC0 = dVarA.C0(this.i0, null);
                inputStreamC0.skip(this.j0);
                a(inputStreamC0);
                this.Z = dVarA;
                this.Y = bVarR;
            } catch (ax.p024b9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.J1.B, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                super.close();
                this.Z.close();
            } catch (ax.p024b9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.J1.B, java.io.InputStream
        public int read() throws IOException {
            if (this.Y.x() || !this.Z.r().v()) {
                d();
            }
            int i = super.read();
            if (i >= 0) {
                this.j0++;
            }
            return i;
        }

        @Override // ax.J1.B, java.io.InputStream
        public long skip(long j) throws IOException {
            long jSkip = super.skip(j);
            this.j0 += jSkip;
            return jSkip;
        }

        @Override // ax.J1.B, java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // ax.J1.B, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.Y.x() || !this.Z.r().v()) {
                d();
            }
            int i3 = super.read(bArr, i, i2);
            if (i3 >= 0) {
                this.j0 += (long) i3;
            }
            return i3;
        }
    }
}
