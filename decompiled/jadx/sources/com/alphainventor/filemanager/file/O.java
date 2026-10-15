package com.alphainventor.filemanager.file;

import android.system.ErrnoException;
import android.text.TextUtils;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1965i;
import ax.p070e3.C1966j;
import ax.p070e3.C1969m;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p380x9.EnumC2456d;
import ax.p380x9.EnumC2457e;
import ax.p406z3.C2461b;
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
public class O {
    private static final Logger i = Logger.getLogger("FileManager.Smb2Client");
    Q a;
    ax.P9.c b;
    ax.Y9.b c;
    String d;
    boolean e;
    boolean f;
    boolean g;
    ConcurrentHashMap<String, Long> h = new ConcurrentHashMap<>();

    static class a extends ax.p086f3.H {
        O X;
        ax.Y9.b Y;
        ax.Z9.d Z;
        c k0;
        long l0;
        long m0;
        long n0;

        a(O o, ax.Y9.b bVar, ax.Z9.d dVar, c cVar, long j, long j2) {
            super(dVar.y0(j, null, j2));
            this.Y = bVar;
            this.Z = dVar;
            this.k0 = cVar;
            this.X = o;
            this.l0 = j;
            this.n0 = j2;
        }

        private void d() throws IOException {
            try {
                ax.Y9.b bVarR = this.X.r();
                ax.Z9.d dVarA = this.X.A(bVarR, this.k0);
                InputStream inputStreamY0 = dVarA.y0(this.l0, null, this.n0);
                inputStreamY0.skip(this.m0);
                a(inputStreamY0);
                this.Z = dVarA;
                this.Y = bVarR;
            } catch (ax.R9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.p086f3.H, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                super.close();
                this.Z.close();
            } catch (ax.R9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read() throws IOException {
            if (this.Y.isClosed() || !this.Z.p().A()) {
                d();
            }
            int i = super.read();
            if (i >= 0) {
                this.m0++;
            }
            return i;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.Y.isClosed() || !this.Z.p().A()) {
                d();
            }
            int i3 = super.read(bArr, i, i2);
            if (i3 >= 0) {
                this.m0 += (long) i3;
            }
            return i3;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public long skip(long j) throws IOException {
            long jSkip = super.skip(j);
            this.m0 += jSkip;
            return jSkip;
        }
    }

    static class b extends ax.p086f3.Y {
        ax.Z9.d X;
        ax.Y9.b Y;

        b(ax.Y9.b bVar, ax.Z9.d dVar) {
            super(dVar.D0(false));
            this.Y = bVar;
            this.X = dVar;
        }

        @Override // ax.p086f3.Y, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                super.close();
                try {
                    this.X.close();
                } catch (ax.R9.d e) {
                    e.printStackTrace();
                    throw new IOException(e);
                }
            } catch (ax.R9.d e2) {
                e2.printStackTrace();
                throw new IOException(e2);
            }
        }

        @Override // ax.p086f3.Y, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (ax.R9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.p086f3.Y, java.io.OutputStream
        public void write(int i) throws IOException {
            try {
                super.write(i);
            } catch (ax.R9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.p086f3.Y, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            try {
                super.write(bArr);
            } catch (ax.R9.d e) {
                throw new IOException(e);
            }
        }

        @Override // ax.p086f3.Y, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                super.write(bArr, i, i2);
            } catch (ax.R9.d e) {
                throw new IOException(e);
            }
        }
    }

    private static class c {
        String a;
        String b;

        c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        static c a(AbstractC6239n abstractC6239n) {
            return b(abstractC6239n.E());
        }

        static c b(String str) {
            if ("/".equals(str)) {
                return new c(null, null);
            }
            int iIndexOf = str.indexOf("/", 1);
            return iIndexOf < 0 ? new c(str.substring(1), null) : new c(str.substring(1, iIndexOf), O.m(str.substring(iIndexOf + 1)));
        }
    }

    static class d extends ax.p262q3.a {
        private final boolean a = true;
        private final byte[] b = new byte[4096];
        private int c = -1;
        private long d = -1;
        private final ax.Z9.d e;

        d(ax.Z9.d dVar) {
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

        @Override // ax.p262q3.a
        public void a() throws ErrnoException {
        }

        @Override // ax.p262q3.a
        public long b() throws ErrnoException {
            return this.e.x().b().a();
        }

        @Override // ax.p262q3.a
        public int c(long j, byte[] bArr, int i, int i2) throws ErrnoException {
            if (g(j, i2)) {
                return f(j, bArr, i, i2);
            }
            try {
                if (h(i2)) {
                    int iR0 = this.e.R0(this.b, j, 0, 4096);
                    if (iR0 < 0) {
                        return iR0;
                    }
                    this.c = iR0;
                    this.d = j;
                    try {
                        return f(j, bArr, i, i2);
                    } catch (ax.R9.d e) {
                        e = e;
                        throw com.alphainventor.filemanager.provider.a.a(O.g(e), "onRead");
                    }
                }
                if (i2 <= 262144) {
                    return this.e.R0(bArr, j, i, i2);
                }
                int i3 = 0;
                while (i2 > 0) {
                    int iR1 = this.e.R0(bArr, ((long) i3) + j, i + i3, Math.min(i2, 262144));
                    if (iR1 < 0) {
                        if (i3 == 0) {
                            return iR1;
                        }
                    } else {
                        if (iR1 == 0) {
                            ax.p302sb.c.h().g().b("SMB2 FILE READ LEN 0").i();
                            return i3;
                        }
                        i3 += iR1;
                        i2 -= iR1;
                    }
                }
                return i3;
            } catch (ax.R9.d e2) {
                e = e2;
            }
        }

        @Override // ax.p262q3.a
        public void d() {
            try {
                this.e.close();
            } catch (ax.R9.d e) {
                e.printStackTrace();
            }
        }

        @Override // ax.p262q3.a
        public long e(long j, byte[] bArr, int i, int i2) throws ErrnoException {
            try {
                return this.e.h1(bArr, j, i, i2);
            } catch (ax.R9.d e) {
                throw com.alphainventor.filemanager.provider.a.a(O.g(e), "onWrite");
            }
        }
    }

    public O(Q q) {
        this.a = q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ax.Z9.d A(ax.Y9.b bVar, c cVar) throws IOException {
        return ((ax.Z9.c) bVar.d(cVar.a)).m1(cVar.b, EnumSet.of(ax.p268q9.a.GENERIC_READ), null, ax.p380x9.u.k0, EnumC2456d.FILE_OPEN, EnumSet.of(EnumC2457e.FILE_SEQUENTIAL_ONLY));
    }

    private ax.Z9.b B(ax.Y9.b bVar, c cVar) {
        return ((ax.Z9.c) bVar.d(cVar.a)).k1(cVar.b, EnumSet.of(ax.p268q9.a.FILE_WRITE_ATTRIBUTES, ax.p268q9.a.FILE_WRITE_EA, ax.p268q9.a.FILE_READ_ATTRIBUTES, ax.p268q9.a.FILE_READ_EA), null, ax.p380x9.u.k0, EnumC2456d.FILE_OPEN, null);
    }

    private ax.Z9.d C(ax.Y9.b bVar, c cVar) {
        return ((ax.Z9.c) bVar.d(cVar.a)).m1(cVar.b, EnumSet.of(ax.p268q9.a.GENERIC_WRITE), null, ax.p380x9.u.k0, EnumC2456d.FILE_CREATE, EnumSet.of(EnumC2457e.FILE_SEQUENTIAL_ONLY));
    }

    public static ax.Y9.b d(ax.P9.c cVar, boolean z, String str, int i2, ax.Q9.b bVar) throws ax.R9.d, IOException, ax.p070e3.L {
        ax.S9.a aVarF = f(cVar, str, i2);
        try {
            ax.Y9.b bVarC = aVarF.C(bVar);
            String strC = bVar.c();
            ax.S9.b bVarR = aVarF.R();
            if (!bVarC.C() || "GUEST".equalsIgnoreCase(strC) || "SMB2GUESTTESTACCOUNT".equalsIgnoreCase(strC) || bVarR == null || !bVarR.l()) {
                return bVarC;
            }
            bVarC.close();
            i.severe("Bad user mapped to guest!");
            throw new ax.R9.d("Bad user mapped to guest!!");
        } catch (ax.A9.b e) {
            throw new ax.R9.d(e);
        } catch (ax.p380x9.F e2) {
            if (z && e2.a() == ax.p284r9.a.STATUS_INVALID_PARAMETER) {
                throw new ax.p070e3.L(e2);
            }
            throw e2;
        }
    }

    private boolean e(P p) {
        if (this.b.i(p.e, p.f)) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str = p.e + ":" + p.f;
            Long l = this.h.get(str);
            if (l != null && jCurrentTimeMillis - l.longValue() < 1500) {
                return true;
            }
            ax.S9.a aVarF = null;
            try {
                aVarF = f(this.b, p.e, p.f);
                aVarF.G(false);
                if (aVarF.R0()) {
                    this.h.put(str, Long.valueOf(jCurrentTimeMillis));
                }
                return aVarF.R0();
            } catch (ax.R9.d | IOException unused) {
                if (aVarF != null) {
                    try {
                        aVarF.G(true);
                    } catch (Exception unused2) {
                    }
                }
                i.severe("SMB2 connection check failed");
            }
        }
        return false;
    }

    public static ax.S9.a f(ax.P9.c cVar, String str, int i2) throws ax.R9.d, IOException {
        try {
            return i2 > 0 ? cVar.d(str, i2) : cVar.a(str);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new IOException("SMB connect", e);
        }
    }

    public static C1966j g(Exception exc) {
        ax.p380x9.F f;
        if (exc instanceof ax.p380x9.F) {
            f = (ax.p380x9.F) exc;
        } else {
            f = ((exc instanceof IOException) && (exc.getCause() instanceof ax.p380x9.F)) ? (ax.p380x9.F) exc.getCause() : null;
        }
        if (f != null) {
            ax.p284r9.a aVarA = f.a();
            if (u(aVarA)) {
                return new ax.p070e3.t(exc);
            }
            if (t(aVarA)) {
                return new C1961e(exc);
            }
            if (aVarA == ax.p284r9.a.STATUS_DISK_FULL) {
                return new ax.p070e3.s(exc);
            }
            if (aVarA == ax.p284r9.a.STATUS_NOT_SAME_DEVICE) {
                return new C1969m(exc);
            }
            if (aVarA == ax.p284r9.a.STATUS_BAD_NETWORK_NAME) {
                return new C1961e(exc);
            }
            if (aVarA == ax.p284r9.a.STATUS_SHARING_VIOLATION) {
                return new ax.p070e3.z(String.format("%s (0x%08x)", aVarA.name(), Long.valueOf(aVarA.getValue())), exc);
            }
            if (aVarA != null) {
                if (aVarA != ax.p284r9.a.STATUS_OTHER) {
                    return new C1965i(String.format("%s (0x%08x)", aVarA.name(), Long.valueOf(aVarA.getValue())), exc);
                }
                return new C1966j("NtStatus : " + aVarA.getValue(), exc);
            }
        }
        return exc instanceof ConnectException ? new ax.p070e3.q(exc) : new C1966j(exc);
    }

    public static ax.P9.c j(String str, boolean z, boolean z2) {
        ax.P9.d.b bVarZ = ax.P9.d.z();
        bVarZ.k(z);
        if ("SMB3".equals(str)) {
            bVarZ.i(ax.p380x9.g.SMB_3_1_1, ax.p380x9.g.SMB_3_0_2, ax.p380x9.g.SMB_3_0);
            bVarZ.j(true);
        } else if ("SMB2".equals(str)) {
            bVarZ.i(ax.p380x9.g.SMB_2_1, ax.p380x9.g.SMB_2_0_2);
        } else {
            bVarZ.j(true);
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        bVarZ.r(35000L, timeUnit);
        bVarZ.v(12500L, timeUnit);
        bVarZ.n(15000L, timeUnit);
        bVarZ.y(15000L, timeUnit);
        bVarZ.f(3500L, timeUnit);
        bVarZ.m(262144);
        bVarZ.x(262144);
        bVarZ.l().b(z2);
        return new ax.P9.c(bVarZ.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String m(String str) {
        return str.replace('/', '\\');
    }

    public static ax.Q9.b n(String str, String str2, String str3) {
        return new ax.Q9.b(str2, str3 == null ? new char[0] : str3.toCharArray(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized ax.Y9.b r() throws ax.R9.d, IOException {
        ax.P9.c cVar;
        try {
            P pF0 = this.a.f0();
            if (this.g && (cVar = this.b) != null) {
                cVar.close();
                this.b = null;
            }
            if (this.b == null) {
                this.b = j(this.d, this.e, this.f);
                this.g = false;
            }
            if (!e(pF0)) {
                this.c = null;
            }
            ax.Y9.b bVar = this.c;
            if (bVar == null || bVar.B() || this.c.isClosed() || !v(this.c.l(), pF0.b)) {
                ax.Y9.b bVar2 = this.c;
                if (bVar2 != null && !bVar2.isClosed()) {
                    try {
                        this.c.close();
                    } catch (ax.R9.d | IOException unused) {
                    }
                }
                try {
                    this.c = d(this.b, pF0.i, pF0.e, pF0.f, pF0.b);
                } catch (ax.p070e3.L e) {
                    if (e.getCause() instanceof ax.R9.d) {
                        throw ((ax.R9.d) e.getCause());
                    }
                    C2461b.f();
                    throw new ax.R9.d("not reachable");
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.c;
    }

    private static boolean t(ax.p284r9.a aVar) {
        return aVar == ax.p284r9.a.STATUS_ACCESS_DENIED || aVar == ax.p284r9.a.STATUS_LOGON_FAILURE;
    }

    private static boolean u(ax.p284r9.a aVar) {
        return aVar == ax.p284r9.a.STATUS_NO_SUCH_FILE || aVar == ax.p284r9.a.STATUS_OBJECT_NAME_INVALID || aVar == ax.p284r9.a.STATUS_OBJECT_NAME_NOT_FOUND || aVar == ax.p284r9.a.STATUS_OBJECT_PATH_NOT_FOUND;
    }

    private boolean v(ax.Q9.b bVar, ax.Q9.b bVar2) {
        if (bVar == bVar2) {
            return true;
        }
        return bVar != null && bVar2 != null && ax.p406z3.B.j(bVar.c(), bVar2.c()) && ax.p406z3.B.j(bVar.b(), bVar2.b()) && ax.p406z3.B.j(bVar.a(), bVar2.a());
    }

    private ax.Z9.d y(ax.Y9.b bVar, c cVar) {
        return ((ax.Z9.c) bVar.d(cVar.a)).m1(cVar.b, EnumSet.of(ax.p268q9.a.GENERIC_WRITE), null, ax.p380x9.u.k0, EnumC2456d.FILE_OVERWRITE_IF, EnumSet.of(EnumC2457e.FILE_SEQUENTIAL_ONLY));
    }

    private ax.Z9.d z(ax.Y9.b bVar, c cVar, int i2) throws C1966j, IOException {
        EnumSet enumSetOf;
        EnumC2456d enumC2456d;
        if ((536870912 & i2) != 0) {
            enumSetOf = EnumSet.of(ax.p268q9.a.GENERIC_WRITE);
        } else if ((268435456 & i2) != 0) {
            enumSetOf = EnumSet.of(ax.p268q9.a.GENERIC_READ);
        } else {
            if ((805306368 & i2) == 0) {
                throw new ax.p070e3.t("Not supported mode :" + i2);
            }
            enumSetOf = EnumSet.of(ax.p268q9.a.GENERIC_READ, ax.p268q9.a.GENERIC_WRITE);
        }
        EnumSet enumSet = enumSetOf;
        if ((67108864 & i2) != 0) {
            enumC2456d = EnumC2456d.FILE_OVERWRITE_IF;
        } else {
            if ((33554432 & i2) != 0) {
                throw new ax.p070e3.t("Not supported mode :" + i2);
            }
            enumC2456d = EnumC2456d.FILE_OPEN;
        }
        return ((ax.Z9.c) bVar.d(cVar.a)).m1(cVar.b, enumSet, null, ax.p380x9.u.k0, enumC2456d, EnumSet.of(EnumC2457e.FILE_RANDOM_ACCESS));
    }

    ax.p262q3.a D(String str, int i2) throws C1966j {
        i.fine("Open SMB2 proxy file : " + str);
        try {
            ax.Y9.b bVarR = r();
            c cVarB = c.b(str);
            if (cVarB.b != null) {
                return new d(z(bVarR, cVarB, i2));
            }
            throw new C1966j("SMB Share cannot be target");
        } catch (ax.p380x9.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C1960d.b("smb2 getoutputstream", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C1960d.b("smb2 getoutputstream", e);
        }
    }

    public void E(AbstractC6239n abstractC6239n, long j) throws C1966j {
        AutoCloseable autoCloseable = null;
        try {
            try {
                ax.Y9.b bVarR = r();
                c cVarA = c.a(abstractC6239n);
                if (cVarA.b == null) {
                    throw new C1966j("SMB Share cannot be target");
                }
                ax.Z9.b bVarB = B(bVarR, cVarA);
                ax.p268q9.b bVar = ax.p332u9.e.f;
                bVarB.c0(new ax.p332u9.e(bVar, bVar, ax.p268q9.b.d(j), bVar, 0L));
                try {
                    bVarB.close();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                if (0 == 0) {
                    throw th;
                }
                try {
                    autoCloseable.close();
                    throw th;
                } catch (Exception unused2) {
                    throw th;
                }
            }
        } catch (ax.p380x9.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            Exception exc = e;
            exc.printStackTrace();
            throw C1960d.b("smb2 setLastModified", exc);
        } catch (IOException e3) {
            e = e3;
            Exception exc2 = e;
            exc2.printStackTrace();
            throw C1960d.b("smb2 setLastModified", exc2);
        }
    }

    public void F(String str, boolean z, boolean z2) {
        this.d = str;
        this.e = z;
        this.f = z2;
    }

    public boolean h(AbstractC6239n abstractC6239n) {
        try {
            ax.Y9.b bVarR = r();
            c cVarB = c.b(abstractC6239n.E());
            if (cVarB.b == null) {
                return false;
            }
            C(bVarR, cVarB).close();
            return true;
        } catch (ax.R9.d | IOException unused) {
            return false;
        }
    }

    public boolean i(AbstractC6239n abstractC6239n) {
        try {
            ax.Y9.b bVarR = r();
            c cVarB = c.b(abstractC6239n.E());
            if (cVarB.b == null) {
                return false;
            }
            ((ax.Z9.c) bVarR.d(cVarB.a)).j1(cVarB.b);
            return true;
        } catch (ax.R9.d | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void k(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            ax.Y9.b bVarR = r();
            c cVarA = c.a(abstractC6239n);
            if (cVarA.b == null) {
                throw new C1966j("SMB Share cannot be deleted");
            }
            ax.Z9.c cVar = (ax.Z9.c) bVarR.d(cVarA.a);
            if (abstractC6239n.isDirectory()) {
                cVar.q1(cVarA.b, false);
            } else {
                cVar.p1(cVarA.b);
            }
        } catch (ax.p380x9.F e) {
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            throw new C1966j(e);
        } catch (IOException e3) {
            e = e3;
            throw new C1966j(e);
        }
    }

    void l() {
        ax.Y9.b bVar = this.c;
        if (bVar != null && !bVar.isClosed()) {
            this.c.k();
        }
        this.g = true;
    }

    public S o(String str) throws C1966j {
        try {
            if ("/".equals(str)) {
                return S.h0(this.a, str);
            }
            ax.Y9.b bVarR = r();
            c cVarB = c.b(str);
            ax.Z9.m mVarD = bVarR.d(cVarB.a);
            if (mVarD instanceof ax.Z9.c) {
                return new S(this.a, str, ((ax.Z9.c) mVarD).S0(cVarB.b));
            }
            throw new C1966j("This type of share is not supported : " + mVarD.getClass().getName());
        } catch (ax.p380x9.F e) {
            if (u(e.a())) {
                return S.g0(this.a, str, false);
            }
            e.printStackTrace();
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C1960d.b("smb2 getfileinfo", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C1960d.b("smb2 getfileinfo", e);
        } catch (IllegalArgumentException e4) {
            e = e4;
            e.printStackTrace();
            throw C1960d.b("smb2 getfileinfo", e);
        } catch (StringIndexOutOfBoundsException e5) {
            ax.p302sb.c.h().g().b("SMB INVALID SHARENAME").h("path:" + str).i();
            throw C1960d.b("smb2 getfileinfo 2", e5);
        }
    }

    public InputStream p(AbstractC6239n abstractC6239n, long j) throws C1966j {
        try {
            c cVarA = c.a(abstractC6239n);
            if (cVarA.b != null) {
                ax.Y9.b bVarR = r();
                return new a(this, bVarR, A(bVarR, cVarA), cVarA, j, abstractC6239n.p());
            }
            ax.p302sb.c.h().g().b("Invalid SMB File Path").k().h(abstractC6239n.E()).i();
            throw new C1966j("Invalid File Path!!");
        } catch (ax.p380x9.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            Exception exc = e;
            exc.printStackTrace();
            throw g(exc);
        } catch (IOException e3) {
            e = e3;
            Exception exc2 = e;
            exc2.printStackTrace();
            throw g(exc2);
        }
    }

    public OutputStream q(AbstractC6239n abstractC6239n, boolean z) throws C1966j {
        try {
            ax.Y9.b bVarR = r();
            c cVarA = c.a(abstractC6239n);
            if (cVarA.b != null) {
                return new b(bVarR, z ? y(bVarR, cVarA) : C(bVarR, cVarA));
            }
            throw new C1966j("SMB Share cannot be target");
        } catch (ax.p380x9.F e) {
            e.printStackTrace();
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            e.printStackTrace();
            throw C1960d.b("smb2 getoutputstream", e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw C1960d.b("smb2 getoutputstream", e);
        }
    }

    public k0 s(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            ax.Y9.b bVarR = r();
            String str = c.a(abstractC6239n).a;
            if (str == null) {
                throw new C1966j();
            }
            ax.p332u9.B bG1 = ((ax.Z9.c) bVarR.d(str)).g1();
            long jB = bG1.b();
            return new k0(jB, jB - bG1.a());
        } catch (ax.p380x9.F e) {
            i.severe("SMBApiException:" + e.a());
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (ArrayIndexOutOfBoundsException e4) {
            e = e4;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (ClassCastException e5) {
            e = e5;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (IllegalArgumentException e6) {
            e = e6;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (StringIndexOutOfBoundsException e7) {
            e = e7;
            e.printStackTrace();
            throw new C1966j(e);
        }
    }

    public List<AbstractC6239n> w(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            ax.Y9.b bVarR = r();
            ArrayList arrayList = new ArrayList();
            String strE = abstractC6239n.E();
            if ("/".equals(strE)) {
                for (ax.p030bb.b bVar : new ax.p014ab.a(ax.p110gb.c.k0.h(bVarR)).g()) {
                    if (bVar.d() == 0 || bVar.d() == Integer.MIN_VALUE) {
                        arrayList.add(S.h0(this.a, "/" + bVar.b()));
                    }
                }
            } else {
                c cVarA = c.a(abstractC6239n);
                for (ax.p332u9.m mVar : ((ax.Z9.c) bVarR.d(cVarA.a)).h1(cVarA.b)) {
                    String strA = mVar.a();
                    if (!d0.C(strA) && !TextUtils.isEmpty(strA)) {
                        if (strA.endsWith("/")) {
                            ax.p302sb.c.h().g().b("INVALID SMB2 FILENAME").h(strA).i();
                        } else {
                            arrayList.add(new S(this.a, d0.Q(strE, strA), mVar));
                        }
                    }
                }
            }
            return arrayList;
        } catch (ax.p380x9.F e) {
            i.severe("SMBApiException:" + e.a());
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (ArrayIndexOutOfBoundsException e4) {
            e = e4;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (ClassCastException e5) {
            e = e5;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (IllegalArgumentException e6) {
            e = e6;
            e.printStackTrace();
            throw new C1966j(e);
        } catch (StringIndexOutOfBoundsException e7) {
            e = e7;
            e.printStackTrace();
            throw new C1966j(e);
        }
    }

    public void x(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) throws C1966j {
        try {
            ax.Y9.b bVarR = r();
            c cVarA = c.a(abstractC6239n);
            if (cVarA.b == null) {
                throw new C1966j("SMB Share cannot be moved");
            }
            ax.Z9.b bVarK1 = ((ax.Z9.c) bVarR.d(cVarA.a)).k1(cVarA.b, EnumSet.of(ax.p268q9.a.FILE_READ_ATTRIBUTES, ax.p268q9.a.DELETE, ax.p268q9.a.SYNCHRONIZE), null, ax.p380x9.u.k0, EnumC2456d.FILE_OPEN, null);
            try {
                c cVarA2 = c.a(abstractC6239n2);
                if (cVarA2.b == null) {
                    throw new C1966j("SMB Share cannot be target");
                }
                if (!cVarA.a.equals(cVarA2.a)) {
                    throw new C1966j("SMB cannot move to different share!");
                }
                bVarK1.A(cVarA2.b, false);
                bVarK1.close();
            } catch (Throwable th) {
                bVarK1.close();
                throw th;
            }
        } catch (ax.p380x9.F e) {
            throw g(e);
        } catch (ax.R9.d e2) {
            e = e2;
            throw new C1966j(e);
        } catch (IOException e3) {
            e = e3;
            throw new C1966j(e);
        }
    }
}
