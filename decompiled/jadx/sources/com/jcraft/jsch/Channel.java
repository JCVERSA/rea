package com.jcraft.jsch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Channel {
    static int u;
    private static Vector<Channel> v = new Vector<>();
    int a;
    protected Session s;
    volatile int b = -1;
    protected byte[] c = Util.v("foo");
    volatile int d = 1048576;
    volatile int e = this.d;
    volatile int f = Http2.INITIAL_MAX_FRAME_SIZE;
    volatile long g = 0;
    volatile int h = 0;
    IO i = null;
    Thread j = null;
    volatile boolean k = false;
    volatile boolean l = false;
    volatile boolean m = false;
    volatile boolean n = false;
    volatile boolean o = false;
    volatile int p = -1;
    volatile int q = 0;
    volatile int r = 0;
    int t = 0;

    /* JADX INFO: renamed from: com.jcraft.jsch.Channel$1, reason: invalid class name */
    class AnonymousClass1 extends OutputStream implements AutoCloseable {
        private Buffer X;
        private Packet Y;
        private boolean Z;
        byte[] k0;
        final /* synthetic */ Channel l0;
        final /* synthetic */ Channel m0;
        private int q;

        private synchronized void a() throws IOException {
            this.X = new Buffer(this.m0.h);
            this.Y = new Packet(this.X);
            try {
                if ((this.X.b.length - 14) - this.m0.p().v() <= 0) {
                    this.X = null;
                    this.Y = null;
                    throw new IOException("failed to initialize the channel.");
                }
            } catch (JSchException e) {
                throw new IOException("failed to initialize the channel.", e);
            }
            throw th;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.Y == null) {
                try {
                    a();
                } catch (IOException unused) {
                    return;
                }
            }
            if (this.Z) {
                return;
            }
            if (this.q > 0) {
                flush();
            }
            this.l0.h();
            this.Z = true;
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            if (this.Z) {
                throw new IOException("Already closed");
            }
            if (this.q == 0) {
                return;
            }
            this.Y.c();
            this.X.s((byte) 94);
            this.X.v(this.m0.b);
            this.X.v(this.q);
            this.X.E(this.q);
            try {
                int i = this.q;
                this.q = 0;
                synchronized (this.l0) {
                    try {
                        if (!this.l0.m) {
                            this.m0.p().n0(this.Y, this.l0, i);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (Exception e) {
                close();
                throw new IOException(e.toString(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            byte[] bArr = this.k0;
            bArr[0] = (byte) i;
            write(bArr, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.Y == null) {
                a();
            }
            if (this.Z) {
                throw new IOException("Already closed");
            }
            byte[] bArr2 = this.X.b;
            int length = bArr2.length;
            while (i2 > 0) {
                try {
                    int iV = this.m0.p().v();
                    int i3 = this.q;
                    int i4 = i2 > (length - (i3 + 14)) - iV ? (length - (i3 + 14)) - iV : i2;
                    if (i4 <= 0) {
                        flush();
                    } else {
                        System.arraycopy(bArr, i, bArr2, i3 + 14, i4);
                        this.q += i4;
                        i += i4;
                        i2 -= i4;
                    }
                } catch (JSchException e) {
                    throw new IOException(e.toString(), e);
                }
            }
        }
    }

    static class MyPipedInputStream extends ax.p152j5.a {
        private int o0;
        private int p0;

        MyPipedInputStream() throws IOException {
            this.o0 = 1024;
            this.p0 = 1024;
        }

        MyPipedInputStream(int i) throws IOException {
            this.o0 = 1024;
            this.p0 = 1024;
            this.l0 = new byte[i];
            this.o0 = i;
            this.p0 = i;
        }

        MyPipedInputStream(int i, int i2) throws IOException {
            this(i);
            this.p0 = i2;
        }

        MyPipedInputStream(ax.p152j5.b bVar, int i) throws IOException {
            super(bVar);
            this.o0 = 1024;
            this.p0 = 1024;
            this.l0 = new byte[i];
            this.o0 = i;
        }

        private int x() {
            int i = this.n0;
            int i2 = this.m0;
            if (i < i2) {
                return this.l0.length - i2;
            }
            if (i2 < i) {
                return i2 == -1 ? this.l0.length : i - i2;
            }
            return 0;
        }

        synchronized void r(int i) throws IOException {
            int i2;
            try {
                int iX = x();
                if (iX < i) {
                    byte[] bArr = this.l0;
                    int length = bArr.length - iX;
                    int length2 = bArr.length;
                    while (length2 - length < i) {
                        length2 *= 2;
                    }
                    int i3 = this.p0;
                    if (length2 > i3) {
                        length2 = i3;
                    }
                    if (length2 - length < i) {
                        return;
                    }
                    byte[] bArr2 = new byte[length2];
                    int i4 = this.n0;
                    int i5 = this.m0;
                    if (i4 < i5) {
                        byte[] bArr3 = this.l0;
                        System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
                    } else if (i5 < i4) {
                        if (i5 != -1) {
                            System.arraycopy(this.l0, 0, bArr2, 0, i5);
                            byte[] bArr4 = this.l0;
                            int i6 = this.n0;
                            System.arraycopy(bArr4, i6, bArr2, length2 - (bArr4.length - i6), bArr4.length - i6);
                            this.n0 = length2 - (this.l0.length - this.n0);
                        }
                    } else if (i5 == i4) {
                        byte[] bArr5 = this.l0;
                        System.arraycopy(bArr5, 0, bArr2, 0, bArr5.length);
                        this.m0 = this.l0.length;
                    }
                    this.l0 = bArr2;
                } else if (this.l0.length == iX && iX > (i2 = this.o0)) {
                    int i7 = iX / 2;
                    if (i7 >= i2) {
                        i2 = i7;
                    }
                    this.l0 = new byte[i2];
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        public synchronized void y() throws IOException {
            if (available() != 0) {
                return;
            }
            this.n0 = 0;
            byte[] bArr = this.l0;
            this.m0 = 1;
            bArr[0] = 0;
            read();
        }
    }

    static class PassiveInputStream extends MyPipedInputStream {
        ax.p152j5.b q0;

        PassiveInputStream(ax.p152j5.b bVar, int i) throws IOException {
            super(bVar, i);
            this.q0 = bVar;
        }

        @Override // ax.p152j5.a, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            ax.p152j5.b bVar = this.q0;
            if (bVar != null) {
                bVar.close();
            }
            this.q0 = null;
        }
    }

    static class PassiveOutputStream extends ax.p152j5.b {
        private MyPipedInputStream X;

        PassiveOutputStream(ax.p152j5.a aVar, boolean z) throws IOException {
            super(aVar);
            this.X = null;
            if (z && (aVar instanceof MyPipedInputStream)) {
                this.X = (MyPipedInputStream) aVar;
            }
        }

        @Override // ax.p152j5.b, java.io.OutputStream
        public void write(int i) throws IOException {
            MyPipedInputStream myPipedInputStream = this.X;
            if (myPipedInputStream != null) {
                myPipedInputStream.r(1);
            }
            super.write(i);
        }

        @Override // ax.p152j5.b, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            MyPipedInputStream myPipedInputStream = this.X;
            if (myPipedInputStream != null) {
                myPipedInputStream.r(i2);
            }
            super.write(bArr, i, i2);
        }
    }

    Channel() {
        synchronized (v) {
            int i = u;
            u = i + 1;
            this.a = i;
            v.addElement(this);
        }
    }

    static void e(Channel channel) {
        synchronized (v) {
            v.removeElement(channel);
        }
    }

    static void g(Session session) {
        Channel[] channelArr;
        int i;
        int i2;
        synchronized (v) {
            channelArr = new Channel[v.size()];
            i2 = 0;
            for (int i3 = 0; i3 < v.size(); i3++) {
                try {
                    Channel channelElementAt = v.elementAt(i3);
                    if (channelElementAt.s == session) {
                        int i4 = i2 + 1;
                        try {
                            channelArr[i2] = channelElementAt;
                        } catch (Exception unused) {
                        }
                        i2 = i4;
                    }
                } catch (Exception unused2) {
                }
            }
        }
        for (i = 0; i < i2; i++) {
            channelArr[i].f();
        }
    }

    static Channel k(int i, Session session) {
        synchronized (v) {
            for (int i2 = 0; i2 < v.size(); i2++) {
                try {
                    Channel channelElementAt = v.elementAt(i2);
                    if (channelElementAt.a == i && channelElementAt.s == session) {
                        return channelElementAt;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    static Channel l(String str, Session session) {
        ChannelDirectStreamLocal channelDirectStreamLocal;
        ChannelSession channelSession = str.equals("session") ? new ChannelSession() : null;
        if (str.equals("shell")) {
            channelSession = new ChannelShell();
        }
        ChannelSession channelExec = channelSession;
        if (str.equals("exec")) {
            channelExec = new ChannelExec();
        }
        Channel channelX11 = channelExec;
        if (str.equals("x11")) {
            channelX11 = new ChannelX11();
        }
        Channel channelAgentForwarding = channelX11;
        if (str.equals("auth-agent@openssh.com")) {
            channelAgentForwarding = new ChannelAgentForwarding();
        }
        Channel channelDirectTCPIP = channelAgentForwarding;
        if (str.equals("direct-tcpip")) {
            channelDirectTCPIP = new ChannelDirectTCPIP();
        }
        Channel channelForwardedTCPIP = channelDirectTCPIP;
        if (str.equals("forwarded-tcpip")) {
            channelForwardedTCPIP = new ChannelForwardedTCPIP();
        }
        Channel channel = channelForwardedTCPIP;
        if (str.equals("sftp")) {
            ChannelSftp channelSftp = new ChannelSftp();
            channelSftp.a1(session.w("use_sftp_write_flush_workaround").equals("yes"));
            channel = channelSftp;
        }
        Channel channelSubsystem = channel;
        if (str.equals("subsystem")) {
            channelSubsystem = new ChannelSubsystem();
        }
        Channel channel2 = channelSubsystem;
        if (str.equals("direct-streamlocal@openssh.com")) {
            channelDirectStreamLocal = new ChannelDirectStreamLocal();
        }
        if (channel2 == null) {
            channel2 = channelDirectStreamLocal;
            return null;
        }
        channel2 = channelDirectStreamLocal;
        channel2.C(session);
        return channel2;
    }

    void A(int i) {
        this.h = i;
    }

    synchronized void B(long j) {
        this.g = j;
    }

    void C(Session session) {
        this.s = session;
    }

    public void D() throws JSchException {
    }

    void E(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.i.f(bArr, i, i2);
        } catch (NullPointerException unused) {
        }
    }

    void F(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.i.g(bArr, i, i2);
        } catch (NullPointerException unused) {
        }
    }

    synchronized void a(long j) {
        this.g += j;
        if (this.t > 0) {
            notifyAll();
        }
    }

    void b() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.l = true;
        this.k = true;
        int iO = o();
        if (iO == -1) {
            return;
        }
        try {
            Buffer buffer = new Buffer(100);
            Packet packet = new Packet(buffer);
            packet.c();
            buffer.s((byte) 97);
            buffer.v(iO);
            synchronized (this) {
                try {
                    p().m0(packet);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception unused) {
        }
    }

    public void c() throws JSchException {
        d(0);
    }

    public void d(int i) throws JSchException {
        this.r = i;
        try {
            u();
            D();
        } catch (Exception e) {
            this.n = false;
            f();
            if (!(e instanceof JSchException)) {
                throw new JSchException(e.toString(), e);
            }
            throw ((JSchException) e);
        }
    }

    public void f() {
        try {
            synchronized (this) {
                if (!this.n) {
                    e(this);
                    return;
                }
                this.n = false;
                b();
                this.k = true;
                this.l = true;
                this.j = null;
                try {
                    IO io = this.i;
                    if (io != null) {
                        io.a();
                    }
                } catch (Exception unused) {
                }
                e(this);
            }
        } catch (Throwable th) {
            e(this);
            throw th;
        }
    }

    void h() {
        if (this.k) {
            return;
        }
        this.k = true;
        int iO = o();
        if (iO == -1) {
            return;
        }
        try {
            Buffer buffer = new Buffer(100);
            Packet packet = new Packet(buffer);
            packet.c();
            buffer.s((byte) 96);
            buffer.v(iO);
            synchronized (this) {
                try {
                    if (!this.m) {
                        p().m0(packet);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception unused) {
        }
    }

    void i() {
        this.l = true;
        try {
            this.i.d();
        } catch (NullPointerException unused) {
        }
    }

    protected Packet j() {
        Buffer buffer = new Buffer(200);
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 90);
        buffer.y(this.c);
        buffer.v(this.a);
        buffer.v(this.e);
        buffer.v(this.f);
        return packet;
    }

    void m(Buffer buffer) {
        z(buffer.i());
        B(buffer.r());
        A(buffer.i());
    }

    public InputStream n() throws IOException {
        int i;
        Session session = this.s;
        if (session != null && s() && session.A().isEnabled(2)) {
            session.A().a(2, "getInputStream() should be called before connect()");
        }
        try {
            i = Integer.parseInt(p().w("max_input_buffer_size"));
        } catch (Exception unused) {
            i = 32768;
        }
        MyPipedInputStream myPipedInputStream = new MyPipedInputStream(32768, i);
        this.i.k(new PassiveOutputStream(myPipedInputStream, 32768 < i), false);
        return myPipedInputStream;
    }

    int o() {
        return this.b;
    }

    public Session p() throws JSchException {
        Session session = this.s;
        if (session != null) {
            return session;
        }
        throw new JSchException("session is not available");
    }

    void q() throws JSchException {
    }

    public boolean r() {
        return this.m;
    }

    public boolean s() {
        Session session = this.s;
        return session != null && session.J() && this.n;
    }

    abstract void t();

    protected void u() throws Exception {
        Session sessionP = p();
        if (!sessionP.J()) {
            throw new JSchException("session is down");
        }
        sessionP.m0(j());
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.r;
        int i = j != 0 ? 1 : 2000;
        synchronized (this) {
            while (o() == -1 && sessionP.J() && i > 0) {
                if (j <= 0 || System.currentTimeMillis() - jCurrentTimeMillis <= j) {
                    long j2 = j == 0 ? 10L : j;
                    try {
                        this.t = 1;
                        wait(j2);
                    } catch (InterruptedException unused) {
                    } catch (Throwable th) {
                        this.t = 0;
                        throw th;
                    }
                    this.t = 0;
                    i--;
                } else {
                    i = 0;
                }
            }
        }
        if (!sessionP.J()) {
            throw new JSchException("session is down");
        }
        if (o() == -1) {
            throw new JSchException("channel is not opened.");
        }
        if (!this.o) {
            throw new JSchException("channel is not opened.");
        }
        this.n = true;
    }

    protected void v() throws Exception {
        Buffer buffer = new Buffer(200);
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 91);
        buffer.v(o());
        buffer.v(this.a);
        buffer.v(this.e);
        buffer.v(this.f);
        p().m0(packet);
    }

    protected void w(int i) {
        try {
            Buffer buffer = new Buffer(200);
            Packet packet = new Packet(buffer);
            packet.c();
            buffer.s((byte) 92);
            buffer.v(o());
            buffer.v(i);
            buffer.y(Util.v("open failed"));
            buffer.y(Util.c);
            p().m0(packet);
        } catch (Exception unused) {
        }
    }

    void x(int i) {
        this.p = i;
    }

    void y(int i) {
        this.e = i;
    }

    synchronized void z(int i) {
        this.b = i;
        if (this.t > 0) {
            notifyAll();
        }
    }
}
