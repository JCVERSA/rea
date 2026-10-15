package com.jcraft.jsch;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Vector;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelSftp extends ChannelSession {
    private static final String d0 = File.separator;
    private static final char e0;
    private static boolean f0;
    private Buffer K;
    private Packet L;
    private Buffer M;
    private Packet N;
    private String W;
    private String X;
    private String Y;
    private boolean H = false;
    private int I = 1;
    private int[] J = new int[1];
    private int O = 3;
    private int P = 3;
    private String Q = String.valueOf(3);
    private Hashtable<String, String> R = null;
    private InputStream S = null;
    private boolean T = false;
    private boolean U = false;
    private boolean V = false;
    private Charset Z = StandardCharsets.UTF_8;
    private boolean a0 = true;
    private boolean b0 = true;
    private RequestQueue c0 = new RequestQueue(16);

    /* JADX INFO: renamed from: com.jcraft.jsch.ChannelSftp$1, reason: invalid class name */
    class AnonymousClass1 extends OutputStream implements AutoCloseable {
        private boolean X;
        private int[] Y;
        private int Z;
        private int k0;
        private int l0;
        private int m0;
        private Header n0;
        byte[] o0;
        final /* synthetic */ byte[] p0;
        private boolean q;
        final /* synthetic */ long[] q0;
        final /* synthetic */ SftpProgressMonitor r0;
        final /* synthetic */ ChannelSftp s0;

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.X) {
                return;
            }
            flush();
            SftpProgressMonitor sftpProgressMonitor = this.r0;
            if (sftpProgressMonitor != null) {
                sftpProgressMonitor.a();
            }
            try {
                this.s0.N(this.p0, this.n0);
                this.X = true;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new IOException(e2.toString(), e2);
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            if (this.X) {
                throw new IOException("stream already closed");
            }
            if (this.q) {
                return;
            }
            while (this.m0 > this.l0 && this.s0.f0(null, this.n0)) {
                try {
                    this.l0++;
                } catch (SftpException e) {
                    throw new IOException(e.toString(), e);
                }
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            byte[] bArr = this.o0;
            bArr[0] = (byte) i;
            write(bArr, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.q) {
                this.Z = this.s0.I;
                this.k0 = this.s0.I;
                this.q = false;
            }
            if (this.X) {
                throw new IOException("stream already closed");
            }
            int i3 = i;
            int i4 = i2;
            while (i4 > 0) {
                try {
                    if (this.s0.b0 && this.s0.g < this.p0.length + 21 + i4 + 4) {
                        flush();
                    }
                    byte[] bArr2 = bArr;
                    int iY0 = this.s0.Y0(this.p0, this.q0[0], bArr2, i3, i4);
                    this.m0++;
                    long[] jArr = this.q0;
                    jArr[0] = jArr[0] + ((long) iY0);
                    i3 += iY0;
                    i4 -= iY0;
                    if (this.s0.I - 1 == this.Z || this.s0.S.available() >= 1024) {
                        while (this.s0.S.available() > 0 && this.s0.f0(this.Y, this.n0)) {
                            int i5 = this.Y[0];
                            this.k0 = i5;
                            if (this.Z > i5 || i5 > this.s0.I - 1) {
                                throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
                            }
                            this.l0++;
                        }
                    }
                    bArr = bArr2;
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new IOException(e2.toString(), e2);
                }
            }
            SftpProgressMonitor sftpProgressMonitor = this.r0;
            if (sftpProgressMonitor != null && !sftpProgressMonitor.b(i2)) {
                close();
                throw new IOException("canceled");
            }
        }
    }

    /* JADX INFO: renamed from: com.jcraft.jsch.ChannelSftp$2, reason: invalid class name */
    class AnonymousClass2 extends InputStream implements InputStreamRetargetInterface, AutoCloseable {
        boolean X = false;
        int Y = 0;
        byte[] Z = new byte[1];
        byte[] k0 = new byte[1024];
        Header l0 = new Header();
        int m0 = 1;
        long n0;
        final /* synthetic */ long o0;
        final /* synthetic */ SftpProgressMonitor p0;
        long q;
        final /* synthetic */ byte[] q0;

        AnonymousClass2(long j, SftpProgressMonitor sftpProgressMonitor, byte[] bArr) {
            this.o0 = j;
            this.p0 = sftpProgressMonitor;
            this.q0 = bArr;
            this.q = j;
            this.n0 = this.q;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.X) {
                return;
            }
            this.X = true;
            SftpProgressMonitor sftpProgressMonitor = this.p0;
            if (sftpProgressMonitor != null) {
                sftpProgressMonitor.a();
            }
            ChannelSftp.this.c0.b(this.l0, ChannelSftp.this.K);
            try {
                ChannelSftp.this.N(this.q0, this.l0);
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new IOException(e2.toString(), e2);
            }
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.X || read(this.Z, 0, 1) == -1) {
                return -1;
            }
            return this.Z[0] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            if (this.X) {
                return -1;
            }
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int length = i2;
            if (this.X) {
                return -1;
            }
            bArr.getClass();
            if (i < 0 || length < 0 || i + length > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            int i3 = 0;
            if (length == 0) {
                return 0;
            }
            int i4 = this.Y;
            if (i4 > 0) {
                if (i4 <= length) {
                    length = i4;
                }
                System.arraycopy(this.k0, 0, bArr, i, length);
                int i5 = this.Y;
                if (length != i5) {
                    byte[] bArr2 = this.k0;
                    System.arraycopy(bArr2, length, bArr2, 0, i5 - length);
                }
                SftpProgressMonitor sftpProgressMonitor = this.p0;
                if (sftpProgressMonitor == null || sftpProgressMonitor.b(length)) {
                    this.Y -= length;
                    return length;
                }
                close();
                return -1;
            }
            if (ChannelSftp.this.K.b.length - 13 < length) {
                length = ChannelSftp.this.K.b.length - 13;
            }
            if (ChannelSftp.this.P == 0 && length > 1024) {
                length = 1024;
            }
            ChannelSftp.this.c0.c();
            int length2 = ChannelSftp.this.P == 0 ? 1024 : ChannelSftp.this.K.b.length - 13;
            while (ChannelSftp.this.c0.c() < this.m0) {
                try {
                    ChannelSftp channelSftp = ChannelSftp.this;
                    channelSftp.Q0(this.q0, this.n0, length2, channelSftp.c0);
                    this.n0 += (long) length2;
                } catch (Exception unused) {
                    throw new IOException("error");
                }
            }
            ChannelSftp channelSftp2 = ChannelSftp.this;
            Header headerM0 = channelSftp2.m0(channelSftp2.K, this.l0);
            this.l0 = headerM0;
            this.Y = headerM0.a;
            int i6 = headerM0.b;
            int i7 = headerM0.c;
            try {
                RequestQueue.Request requestD = ChannelSftp.this.c0.d(this.l0.c);
                if (i6 != 101 && i6 != 103) {
                    throw new IOException("error");
                }
                if (i6 == 101) {
                    ChannelSftp channelSftp3 = ChannelSftp.this;
                    channelSftp3.h0(channelSftp3.K, this.Y);
                    int i8 = ChannelSftp.this.K.i();
                    this.Y = 0;
                    if (i8 != 1) {
                        throw new IOException("error");
                    }
                    close();
                    return -1;
                }
                ChannelSftp.this.K.B();
                ChannelSftp channelSftp4 = ChannelSftp.this;
                channelSftp4.g0(channelSftp4.K.b, 0, 4);
                int i9 = ChannelSftp.this.K.i();
                int i10 = this.Y - 4;
                this.Y = i10;
                int i11 = i10 - i9;
                long j = i9;
                this.q += j;
                if (i9 <= 0) {
                    return 0;
                }
                if (i9 <= length) {
                    length = i9;
                }
                int i12 = ChannelSftp.this.S.read(bArr, i, length);
                if (i12 < 0) {
                    return -1;
                }
                int i13 = i9 - i12;
                this.Y = i13;
                if (i13 > 0) {
                    if (this.k0.length < i13) {
                        this.k0 = new byte[i13];
                    }
                    while (i13 > 0) {
                        int i14 = ChannelSftp.this.S.read(this.k0, i3, i13);
                        if (i14 <= 0) {
                            break;
                        }
                        i3 += i14;
                        i13 -= i14;
                    }
                }
                if (i11 > 0) {
                    ChannelSftp.this.S.skip(i11);
                }
                if (j < requestD.c) {
                    ChannelSftp.this.c0.b(this.l0, ChannelSftp.this.K);
                    try {
                        ChannelSftp channelSftp5 = ChannelSftp.this;
                        channelSftp5.Q0(this.q0, requestD.b + j, (int) (requestD.c - j), channelSftp5.c0);
                        this.n0 = requestD.b + requestD.c;
                    } catch (Exception unused2) {
                        throw new IOException("error");
                    }
                }
                if (this.m0 < ChannelSftp.this.c0.g()) {
                    this.m0++;
                }
                SftpProgressMonitor sftpProgressMonitor2 = this.p0;
                if (sftpProgressMonitor2 == null || sftpProgressMonitor2.b(i12)) {
                    return i12;
                }
                close();
                return -1;
            } catch (RequestQueue.OutOfOrderException e) {
                this.n0 = e.q;
                skip(this.l0.a);
                ChannelSftp.this.c0.b(this.l0, ChannelSftp.this.K);
                return 0;
            } catch (SftpException e2) {
                throw new IOException("error: " + e2.toString(), e2);
            }
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }
    }

    static class Header {
        int a;
        int b;
        int c;

        Header() {
        }
    }

    public static class LsEntry implements Comparable<LsEntry> {
        private String X;
        private SftpATTRS Y;
        private String q;

        LsEntry(String str, String str2, SftpATTRS sftpATTRS) {
            o(str);
            p(str2);
            m(sftpATTRS);
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public int compareTo(LsEntry lsEntry) {
            return this.q.compareTo(lsEntry.k());
        }

        public SftpATTRS h() {
            return this.Y;
        }

        public String k() {
            return this.q;
        }

        void m(SftpATTRS sftpATTRS) {
            this.Y = sftpATTRS;
        }

        void o(String str) {
            this.q = str;
        }

        void p(String str) {
            this.X = str;
        }

        public String toString() {
            return this.X;
        }
    }

    public interface LsEntrySelector {
        int a(LsEntry lsEntry);
    }

    private class RequestQueue {
        Request[] a;
        int b;
        int c;

        class OutOfOrderException extends Exception {
            long q;

            OutOfOrderException(long j) {
                this.q = j;
            }
        }

        class Request {
            int a;
            long b;
            long c;

            Request() {
            }
        }

        RequestQueue(int i) {
            this.a = null;
            this.a = new Request[i];
            int i2 = 0;
            while (true) {
                Request[] requestArr = this.a;
                if (i2 >= requestArr.length) {
                    f();
                    return;
                } else {
                    requestArr[i2] = new Request();
                    i2++;
                }
            }
        }

        void a(int i, long j, int i2) {
            int i3 = this.c;
            if (i3 == 0) {
                this.b = 0;
            }
            int length = this.b + i3;
            Request[] requestArr = this.a;
            if (length >= requestArr.length) {
                length -= requestArr.length;
            }
            Request request = requestArr[length];
            request.a = i;
            request.b = j;
            request.c = i2;
            this.c = i3 + 1;
        }

        void b(Header header, Buffer buffer) throws IOException {
            int i = this.c;
            for (int i2 = 0; i2 < i; i2++) {
                header = ChannelSftp.this.m0(buffer, header);
                int i3 = header.a;
                int i4 = 0;
                while (true) {
                    Request[] requestArr = this.a;
                    if (i4 >= requestArr.length) {
                        break;
                    }
                    Request request = requestArr[i4];
                    if (request.a == header.c) {
                        request.a = 0;
                        break;
                    }
                    i4++;
                }
                ChannelSftp.this.b1(i3);
            }
            f();
        }

        int c() {
            return this.c;
        }

        Request d(int i) throws OutOfOrderException, SftpException {
            this.c--;
            int i2 = this.b;
            int i3 = i2 + 1;
            this.b = i3;
            Request[] requestArr = this.a;
            if (i3 == requestArr.length) {
                this.b = 0;
            }
            Request request = requestArr[i2];
            if (request.a == i) {
                request.a = 0;
                return request;
            }
            long jE = e();
            int i4 = 0;
            while (true) {
                Request[] requestArr2 = this.a;
                if (i4 >= requestArr2.length) {
                    throw new SftpException(4, "RequestQueue: unknown request id " + i);
                }
                Request request2 = requestArr2[i4];
                if (request2.a == i) {
                    request2.a = 0;
                    throw new OutOfOrderException(jE);
                }
                i4++;
            }
        }

        long e() {
            long j = Long.MAX_VALUE;
            int i = 0;
            while (true) {
                Request[] requestArr = this.a;
                if (i >= requestArr.length) {
                    return j;
                }
                Request request = requestArr[i];
                if (request.a != 0) {
                    long j2 = request.b;
                    if (j > j2) {
                        j = j2;
                    }
                }
                i++;
            }
        }

        void f() {
            this.c = 0;
            this.b = 0;
        }

        int g() {
            return this.a.length;
        }
    }

    static {
        char c = File.separatorChar;
        e0 = c;
        f0 = ((byte) c) == 92;
    }

    public ChannelSftp() {
        this.d = 2097152;
        this.e = 2097152;
        this.f = 32768;
    }

    private void D0(byte[] bArr) throws Exception {
        N0((byte) 4, bArr);
    }

    private void E0() throws Exception {
        this.L.c();
        x0((byte) 1, 5);
        this.K.v(3);
        p().n0(this.L, this, 9);
    }

    private void F0(byte[] bArr) throws Exception {
        N0((byte) 7, bArr);
    }

    private void G0(byte[] bArr, SftpATTRS sftpATTRS) throws Exception {
        this.L.c();
        x0((byte) 14, bArr.length + 9 + (sftpATTRS != null ? sftpATTRS.n() : 4));
        Buffer buffer = this.K;
        int i = this.I;
        this.I = i + 1;
        buffer.v(i);
        this.K.y(bArr);
        if (sftpATTRS != null) {
            sftpATTRS.a(this.K);
        } else {
            this.K.v(0);
        }
        p().n0(this.L, this, bArr.length + 9 + (sftpATTRS != null ? sftpATTRS.n() : 4) + 4);
    }

    private void H0(byte[] bArr, int i) throws Exception {
        this.L.c();
        x0((byte) 3, bArr.length + 17);
        Buffer buffer = this.K;
        int i2 = this.I;
        this.I = i2 + 1;
        buffer.v(i2);
        this.K.y(bArr);
        this.K.v(i);
        this.K.v(0);
        p().n0(this.L, this, bArr.length + 21);
    }

    private void I0(byte[] bArr) throws Exception {
        H0(bArr, 10);
    }

    private void J0(byte[] bArr) throws Exception {
        N0((byte) 11, bArr);
    }

    private SftpATTRS K(String str) throws SftpException {
        try {
            F0(Util.w(str, this.Z));
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 == 105) {
                return SftpATTRS.b(this.K);
            }
            if (i2 == 101) {
                d1(this.K, this.K.i());
            }
            throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
        } catch (Exception e) {
            if (e instanceof SftpException) {
                throw ((SftpException) e);
            }
            throw new SftpException(4, e.toString(), e);
        }
    }

    private void K0(byte[] bArr) throws Exception {
        H0(bArr, 1);
    }

    private void L0(byte[] bArr) throws Exception {
        H0(bArr, 26);
    }

    private byte[] M(String str) throws Exception {
        S0(Util.w(str, this.Z));
        Header headerM0 = m0(this.K, new Header());
        int i = headerM0.a;
        int i2 = headerM0.b;
        h0(this.K, i);
        if (i2 != 101 && i2 != 104) {
            throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
        }
        if (i2 == 101) {
            d1(this.K, this.K.i());
        }
        int i3 = this.K.i();
        byte[] bArrP = null;
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                return bArrP;
            }
            bArrP = this.K.p();
            if (this.P <= 3) {
                this.K.p();
            }
            SftpATTRS.b(this.K);
            i3 = i4;
        }
    }

    private void M0(byte[] bArr) throws Exception {
        H0(bArr, 42);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean N(byte[] bArr, Header header) throws Exception {
        D0(bArr);
        return f0(null, header);
    }

    private void N0(byte b, byte[] bArr) throws Exception {
        O0(b, bArr, null);
    }

    private void O(String str, SftpATTRS sftpATTRS) throws SftpException {
        try {
            W0(Util.w(str, this.Z), sftpATTRS);
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 != 101) {
                throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            int i3 = this.K.i();
            if (i3 != 0) {
                d1(this.K, i3);
            }
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    private void O0(byte b, byte[] bArr, String str) throws Exception {
        this.L.c();
        int length = bArr.length + 9;
        if (str == null) {
            x0(b, length);
            Buffer buffer = this.K;
            int i = this.I;
            this.I = i + 1;
            buffer.v(i);
        } else {
            length += str.length() + 4;
            x0((byte) -56, length);
            Buffer buffer2 = this.K;
            int i2 = this.I;
            this.I = i2 + 1;
            buffer2.v(i2);
            this.K.y(Util.v(str));
        }
        this.K.y(bArr);
        p().n0(this.L, this, length + 4);
    }

    private SftpATTRS P(String str) throws SftpException {
        return Q(Util.w(str, this.Z));
    }

    private void P0(byte b, byte[] bArr, byte[] bArr2, String str) throws Exception {
        this.L.c();
        int length = bArr.length + 13 + bArr2.length;
        if (str == null) {
            x0(b, length);
            Buffer buffer = this.K;
            int i = this.I;
            this.I = i + 1;
            buffer.v(i);
        } else {
            length += str.length() + 4;
            x0((byte) -56, length);
            Buffer buffer2 = this.K;
            int i2 = this.I;
            this.I = i2 + 1;
            buffer2.v(i2);
            this.K.y(Util.v(str));
        }
        this.K.y(bArr);
        this.K.y(bArr2);
        p().n0(this.L, this, length + 4);
    }

    private SftpATTRS Q(byte[] bArr) throws SftpException {
        try {
            X0(bArr);
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 == 105) {
                return SftpATTRS.b(this.K);
            }
            if (i2 == 101) {
                d1(this.K, this.K.i());
            }
            throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
        } catch (Exception e) {
            if (e instanceof SftpException) {
                throw ((SftpException) e);
            }
            throw new SftpException(4, e.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(byte[] bArr, long j, int i, RequestQueue requestQueue) throws Exception {
        this.L.c();
        x0((byte) 5, bArr.length + 21);
        Buffer buffer = this.K;
        int i2 = this.I;
        this.I = i2 + 1;
        buffer.v(i2);
        this.K.y(bArr);
        this.K.w(j);
        this.K.v(i);
        p().n0(this.L, this, bArr.length + 25);
        if (requestQueue != null) {
            requestQueue.a(this.I - 1, j, i);
        }
    }

    private void R0(byte[] bArr) throws Exception {
        N0((byte) 12, bArr);
    }

    private void S0(byte[] bArr) throws Exception {
        N0((byte) 16, bArr);
    }

    private void T0(byte[] bArr) throws Exception {
        N0((byte) 13, bArr);
    }

    private void U0(byte[] bArr, byte[] bArr2) throws Exception {
        P0((byte) 18, bArr, bArr2, this.T ? "posix-rename@openssh.com" : null);
    }

    private void V0(byte[] bArr) throws Exception {
        N0((byte) 15, bArr);
    }

    private void W0(byte[] bArr, SftpATTRS sftpATTRS) throws Exception {
        this.L.c();
        x0((byte) 9, bArr.length + 9 + sftpATTRS.n());
        Buffer buffer = this.K;
        int i = this.I;
        this.I = i + 1;
        buffer.v(i);
        this.K.y(bArr);
        sftpATTRS.a(this.K);
        p().n0(this.L, this, bArr.length + 9 + sftpATTRS.n() + 4);
    }

    private void X0(byte[] bArr) throws Exception {
        N0((byte) 17, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int Y0(byte[] bArr, long j, byte[] bArr2, int i, int i2) throws Exception {
        this.N.c();
        Session sessionP = p();
        int iV = sessionP.v();
        Buffer buffer = this.M;
        byte[] bArr3 = buffer.b;
        int length = bArr3.length;
        int i3 = buffer.c;
        if (length < i3 + 34 + bArr.length + i2 + iV) {
            i2 = bArr3.length - (((i3 + 34) + bArr.length) + iV);
        }
        y0(buffer, (byte) 6, bArr.length + 21 + i2);
        Buffer buffer2 = this.M;
        int i4 = this.I;
        this.I = i4 + 1;
        buffer2.v(i4);
        this.M.y(bArr);
        this.M.w(j);
        Buffer buffer3 = this.M;
        if (buffer3.b != bArr2) {
            buffer3.z(bArr2, i, i2);
        } else {
            buffer3.v(i2);
            this.M.E(i2);
        }
        sessionP.n0(this.N, this, bArr.length + 21 + i2 + 4);
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b1(long j) throws IOException {
        while (j > 0) {
            long jSkip = this.S.skip(j);
            if (jSkip <= 0) {
                return;
            } else {
                j -= jSkip;
            }
        }
    }

    private void d1(Buffer buffer, int i) throws SftpException {
        if (this.P >= 3 && buffer.j() >= 4) {
            throw new SftpException(i, Util.f(buffer.p(), StandardCharsets.UTF_8));
        }
        throw new SftpException(i, "Failure");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f0(int[] iArr, Header header) throws SftpException, IOException {
        Header headerM0 = m0(this.K, header);
        int i = headerM0.a;
        int i2 = headerM0.b;
        if (iArr != null) {
            iArr[0] = headerM0.c;
        }
        h0(this.K, i);
        if (i2 != 101) {
            throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
        }
        int i3 = this.K.i();
        if (i3 == 0) {
            return true;
        }
        d1(this.K, i3);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int g0(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i;
        while (i2 > 0) {
            int i4 = this.S.read(bArr, i3, i2);
            if (i4 <= 0) {
                throw new IOException("inputstream is closed");
            }
            i3 += i4;
            i2 -= i4;
        }
        return i3 - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0(Buffer buffer, int i) throws IOException {
        buffer.A();
        g0(buffer.b, 0, i);
        buffer.E(i);
    }

    private String j0() throws SftpException {
        if (this.W == null) {
            this.W = k0();
        }
        return this.W;
    }

    private Vector<String> l0(String str) throws Exception {
        byte[] bArrW;
        String strF;
        Vector<String> vector = new Vector<>();
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            vector.addElement(Util.z(str));
            return vector;
        }
        int i = 0;
        String strSubstring = str.substring(0, iLastIndexOf == 0 ? 1 : iLastIndexOf);
        String strSubstring2 = str.substring(iLastIndexOf + 1);
        String strZ = Util.z(strSubstring);
        byte[][] bArr = new byte[1][];
        if (!o0(strSubstring2, bArr)) {
            if (!strZ.equals("/")) {
                strZ = strZ + "/";
            }
            vector.addElement(strZ + Util.z(strSubstring2));
            return vector;
        }
        byte[] bArr2 = bArr[0];
        J0(Util.w(strZ, this.Z));
        Header headerM0 = m0(this.K, new Header());
        int i2 = headerM0.a;
        int i3 = headerM0.b;
        h0(this.K, i2);
        int i4 = 4;
        int i5 = 101;
        if (i3 != 101 && i3 != 102) {
            throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
        }
        if (i3 == 101) {
            d1(this.K, this.K.i());
        }
        byte[] bArrP = this.K.p();
        String str2 = null;
        while (true) {
            R0(bArrP);
            headerM0 = m0(this.K, headerM0);
            int i6 = headerM0.a;
            int i7 = headerM0.b;
            if (i7 != i5 && i7 != 104) {
                throw new SftpException(i4, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            if (i7 == i5) {
                h0(this.K, i6);
                if (N(bArrP, headerM0)) {
                    return vector;
                }
                return null;
            }
            this.K.B();
            g0(this.K.b, i, i4);
            int i8 = i6 - 4;
            this.K.A();
            for (int i9 = this.K.i(); i9 > 0; i9--) {
                if (i8 > 0) {
                    this.K.D();
                    Buffer buffer = this.K;
                    byte[] bArr3 = buffer.b;
                    int length = bArr3.length;
                    int i10 = buffer.c;
                    int i11 = this.S.read(bArr3, i10, length > i10 + i8 ? i8 : bArr3.length - i10);
                    if (i11 <= 0) {
                        break;
                    }
                    this.K.c += i11;
                    i8 -= i11;
                }
                byte[] bArrP2 = this.K.p();
                if (this.P <= 3) {
                    this.K.p();
                }
                SftpATTRS.b(this.K);
                if (this.a0) {
                    bArrW = bArrP2;
                    strF = null;
                } else {
                    strF = Util.f(bArrP2, this.Z);
                    bArrW = Util.w(strF, StandardCharsets.UTF_8);
                }
                if (Util.r(bArr2, bArrW)) {
                    if (strF == null) {
                        strF = Util.f(bArrP2, this.Z);
                    }
                    if (str2 == null) {
                        str2 = strZ.endsWith("/") ? strZ : strZ + "/";
                    }
                    vector.addElement(str2 + strF);
                }
            }
            i = 0;
            i4 = 4;
            i5 = 101;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Header m0(Buffer buffer, Header header) throws IOException {
        buffer.B();
        g0(buffer.b, 0, 9);
        header.a = buffer.i() - 5;
        header.b = buffer.c() & 255;
        header.c = buffer.i();
        return header;
    }

    private boolean n0(String str) {
        return o0(str, null);
    }

    private boolean o0(String str, byte[][] bArr) {
        byte[] bArrW = Util.w(str, StandardCharsets.UTF_8);
        if (bArr != null) {
            bArr[0] = bArrW;
        }
        return p0(bArrW);
    }

    private boolean p0(byte[] bArr) {
        int i;
        int length = bArr.length;
        int i2 = 0;
        while (i2 < length) {
            byte b = bArr[i2];
            if (b == 42 || b == 63) {
                return true;
            }
            if (b == 92 && (i = i2 + 1) < length) {
                i2 = i;
            }
            i2++;
        }
        return false;
    }

    private boolean q0(String str) {
        try {
            X0(Util.w(str, this.Z));
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 != 105) {
                return false;
            }
            return SftpATTRS.b(this.K).k();
        } catch (Exception unused) {
            return false;
        }
    }

    private String r0(String str) throws Exception {
        Vector<String> vectorL0 = l0(str);
        if (vectorL0.size() == 1) {
            return vectorL0.elementAt(0);
        }
        throw new SftpException(4, str + " is not unique: " + vectorL0.toString());
    }

    private void x0(byte b, int i) throws Exception {
        y0(this.K, b, i);
    }

    private void y0(Buffer buffer, byte b, int i) throws Exception {
        buffer.s((byte) 94);
        buffer.v(this.b);
        buffer.v(i + 4);
        buffer.v(i);
        buffer.s(b);
    }

    private String z0(String str) throws SftpException {
        if (str.charAt(0) == '/') {
            return str;
        }
        String strJ0 = j0();
        if (strJ0.endsWith("/")) {
            return strJ0 + str;
        }
        return strJ0 + "/" + str;
    }

    public void A0(String str, String str2) throws SftpException {
        String strZ;
        if (this.P < 2) {
            throw new SftpException(8, "The remote sshd is too old to support rename operation.");
        }
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            String strZ0 = z0(str);
            String strZ1 = z0(str2);
            String strR0 = r0(strZ0);
            Vector<String> vectorL0 = l0(strZ1);
            int size = vectorL0.size();
            if (size >= 2) {
                throw new SftpException(4, vectorL0.toString());
            }
            if (size == 1) {
                strZ = vectorL0.elementAt(0);
            } else {
                if (n0(strZ1)) {
                    throw new SftpException(4, strZ1);
                }
                strZ = Util.z(strZ1);
            }
            U0(Util.w(strR0, this.Z), Util.w(strZ, this.Z));
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 != 101) {
                throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            int i3 = this.K.i();
            if (i3 == 0) {
                return;
            }
            d1(this.K, i3);
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    public void B0(String str) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            Vector<String> vectorL0 = l0(z0(str));
            int size = vectorL0.size();
            Header header = new Header();
            for (int i = 0; i < size; i++) {
                T0(Util.w(vectorL0.elementAt(i), this.Z));
                header = m0(this.K, header);
                int i2 = header.a;
                int i3 = header.b;
                h0(this.K, i2);
                if (i3 != 101) {
                    throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
                }
                int i4 = this.K.i();
                if (i4 != 0) {
                    d1(this.K, i4);
                }
            }
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    public void C0(String str) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            Vector<String> vectorL0 = l0(z0(str));
            int size = vectorL0.size();
            Header header = new Header();
            for (int i = 0; i < size; i++) {
                V0(Util.w(vectorL0.elementAt(i), this.Z));
                header = m0(this.K, header);
                int i2 = header.a;
                int i3 = header.b;
                h0(this.K, i2);
                if (i3 != 101) {
                    throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
                }
                int i4 = this.K.i();
                if (i4 != 0) {
                    d1(this.K, i4);
                }
            }
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    @Override // com.jcraft.jsch.Channel
    public void D() throws JSchException {
        try {
            ax.p152j5.b bVar = new ax.p152j5.b();
            this.i.j(bVar);
            this.i.h(new Channel.MyPipedInputStream(bVar, this.c0.g() * this.h));
            InputStream inputStream = this.i.a;
            this.S = inputStream;
            if (inputStream == null) {
                throw new JSchException("channel is down");
            }
            new RequestSftp().a(p(), this);
            this.K = new Buffer(this.f);
            this.L = new Packet(this.K);
            this.M = new Buffer(this.h);
            this.N = new Packet(this.M);
            E0();
            Header headerM0 = m0(this.K, new Header());
            int length = headerM0.a;
            if (length > 262144) {
                throw new SftpException(4, "Received message is too long: " + length);
            }
            this.P = headerM0.c;
            this.R = new Hashtable<>();
            if (length > 0) {
                h0(this.K, length);
                while (length > 0) {
                    byte[] bArrP = this.K.p();
                    int length2 = length - (bArrP.length + 4);
                    byte[] bArrP2 = this.K.p();
                    length = length2 - (bArrP2.length + 4);
                    this.R.put(Util.c(bArrP), Util.c(bArrP2));
                }
            }
            if (this.R.get("posix-rename@openssh.com") != null && this.R.get("posix-rename@openssh.com").equals("1")) {
                this.T = true;
            }
            if (this.R.get("statvfs@openssh.com") != null && this.R.get("statvfs@openssh.com").equals("2")) {
                this.U = true;
            }
            if (this.R.get("hardlink@openssh.com") != null && this.R.get("hardlink@openssh.com").equals("1")) {
                this.V = true;
            }
            this.Y = new File(".").getCanonicalPath();
        } catch (Exception e) {
            if (!(e instanceof JSchException)) {
                throw new JSchException(e.toString(), e);
            }
            throw ((JSchException) e);
        }
    }

    @Override // com.jcraft.jsch.ChannelSession
    public /* bridge */ /* synthetic */ void H(boolean z) {
        super.H(z);
    }

    @Override // com.jcraft.jsch.ChannelSession
    public /* bridge */ /* synthetic */ void I(boolean z) {
        super.I(z);
    }

    public void L(InputStream inputStream, String str, SftpProgressMonitor sftpProgressMonitor, int i) throws SftpException {
        long jI;
        long j;
        int i2;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        int i3;
        byte[] bArr4;
        InputStream inputStream2 = inputStream;
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            byte[] bArrW = Util.w(str, this.Z);
            if (i == 1 || i == 2) {
                try {
                    jI = Q(bArrW).i();
                } catch (Exception unused) {
                    jI = 0;
                }
            } else {
                jI = 0;
            }
            if (i == 1 && jI > 0 && inputStream2.skip(jI) < jI) {
                throw new SftpException(4, "failed to resume for " + str);
            }
            if (i == 0) {
                L0(bArrW);
            } else if (i == 4) {
                M0(bArrW);
            } else {
                I0(bArrW);
            }
            Header headerM0 = m0(this.K, new Header());
            int i4 = headerM0.a;
            int i5 = headerM0.b;
            h0(this.K, i4);
            if (i5 != 101 && i5 != 102) {
                throw new SftpException(4, "invalid type=" + i5);
            }
            if (i5 == 101) {
                d1(this.K, this.K.i());
            }
            byte[] bArrP = this.K.p();
            int iV = this.s.v();
            long j2 = (i == 1 || i == 2) ? jI : 0L;
            int i6 = this.I;
            byte[] bArr5 = this.M.b;
            int length = bArrP.length + 39;
            int length2 = (bArr5.length - length) - iV;
            int iG = this.c0.g();
            int i7 = 0;
            while (true) {
                int i8 = length2;
                int i9 = length;
                int i10 = 0;
                while (true) {
                    int i11 = inputStream2.read(bArr5, i9, i8);
                    if (i11 > 0) {
                        i9 += i11;
                        i8 -= i11;
                        i10 += i11;
                    }
                    j = j2;
                    i2 = i10;
                    if (i8 <= 0 || i11 <= 0) {
                        break;
                        break;
                    } else {
                        i10 = i2;
                        j2 = j;
                    }
                }
                if (i2 > 0) {
                    int i12 = i7;
                    byte[] bArr6 = bArr5;
                    int i13 = i12;
                    int length3 = length2;
                    int iY0 = i2;
                    while (iY0 > 0) {
                        int i14 = this.I;
                        if (i14 - 1 == i6 || (i14 - i6) - i13 >= iG) {
                            while ((this.I - i6) - i13 >= iG && f0(this.J, headerM0)) {
                                int i15 = this.J[0];
                                if (i6 > i15 || i15 > this.I - 1) {
                                    bArr3 = bArrP;
                                    i3 = i13;
                                    if (i15 != this.I) {
                                        throw new SftpException(4, "ack error: startid=" + i6 + " seq=" + this.I + " _ackid=" + i15);
                                    }
                                    bArr4 = bArr6;
                                    if (p().A().isEnabled(3)) {
                                        p().A().a(3, "ack error: startid=" + i6 + " seq=" + this.I + " _ackid=" + i15);
                                    }
                                } else {
                                    bArr3 = bArrP;
                                    i3 = i13;
                                    bArr4 = bArr6;
                                }
                                i13 = i3 + 1;
                                bArrP = bArr3;
                                bArr6 = bArr4;
                            }
                            bArr = bArrP;
                            bArr2 = bArr6;
                            i13 = i13;
                        } else {
                            bArr = bArrP;
                            i13 = i13;
                            bArr2 = bArr6;
                        }
                        i2 = i2;
                        bArr6 = bArr2;
                        bArrP = bArr;
                        iY0 -= Y0(bArrP, j, bArr6, 0, iY0);
                        byte[] bArr7 = this.M.b;
                        if (bArr6 != bArr7) {
                            length3 = (bArr7.length - length) - iV;
                            bArr6 = bArr7;
                        }
                    }
                    long j3 = i2;
                    long j4 = j + j3;
                    if (sftpProgressMonitor != null && !sftpProgressMonitor.b(j3)) {
                        i7 = i13;
                        break;
                    }
                    byte[] bArr8 = bArr6;
                    i7 = i13;
                    bArr5 = bArr8;
                    inputStream2 = inputStream;
                    length2 = length3;
                    j2 = j4;
                } else {
                    break;
                }
            }
            int i16 = this.I - i6;
            while (i16 > i7 && f0(null, headerM0)) {
                i7++;
            }
            if (sftpProgressMonitor != null) {
                sftpProgressMonitor.a();
            }
            N(bArrP, headerM0);
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    public void Z0(String str, int i) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            Vector<String> vectorL0 = l0(z0(str));
            int size = vectorL0.size();
            for (int i2 = 0; i2 < size; i2++) {
                String strElementAt = vectorL0.elementAt(i2);
                SftpATTRS sftpATTRSP = P(strElementAt);
                sftpATTRSP.p(0);
                sftpATTRSP.o(sftpATTRSP.c(), i);
                O(strElementAt, sftpATTRSP);
            }
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    public void a1(boolean z) {
        this.b0 = z;
    }

    public SftpATTRS c1(String str) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            return P(r0(z0(str)));
        } catch (Exception e) {
            if (e instanceof SftpException) {
                throw ((SftpException) e);
            }
            throw new SftpException(4, e.toString(), e);
        }
    }

    @Override // com.jcraft.jsch.Channel
    public void f() {
        super.f();
    }

    public InputStream i0(String str, SftpProgressMonitor sftpProgressMonitor, long j) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            String strR0 = r0(z0(str));
            byte[] bArrW = Util.w(strR0, this.Z);
            SftpATTRS sftpATTRSQ = Q(bArrW);
            if (sftpProgressMonitor != null) {
                sftpProgressMonitor.c(1, strR0, "??", sftpATTRSQ.i());
            }
            K0(bArrW);
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 != 101 && i2 != 102) {
                throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            if (i2 == 101) {
                d1(this.K, this.K.i());
            }
            byte[] bArrP = this.K.p();
            this.c0.f();
            return new AnonymousClass2(j, sftpProgressMonitor, bArrP);
        } catch (Exception e) {
            if (e instanceof SftpException) {
                throw ((SftpException) e);
            }
            throw new SftpException(4, e.toString(), e);
        }
    }

    public String k0() throws SftpException {
        if (this.X == null) {
            try {
                ((Channel.MyPipedInputStream) this.S).y();
                this.X = Util.f(M(HttpUrl.FRAGMENT_ENCODE_SET), this.Z);
            } catch (Exception e) {
                if (e instanceof SftpException) {
                    throw ((SftpException) e);
                }
                throw new SftpException(4, e.toString(), e);
            }
        }
        return this.X;
    }

    @Override // com.jcraft.jsch.Channel
    void q() {
    }

    public Vector<LsEntry> s0(String str) throws SftpException {
        final Vector<LsEntry> vector = new Vector<>();
        t0(str, new LsEntrySelector() { // from class: com.jcraft.jsch.ChannelSftp.3
            @Override // com.jcraft.jsch.ChannelSftp.LsEntrySelector
            public int a(LsEntry lsEntry) {
                vector.addElement(lsEntry);
                return 0;
            }
        });
        return vector;
    }

    public void t0(String str, LsEntrySelector lsEntrySelector) throws SftpException {
        byte[] bArrA;
        byte[] bArrW;
        String strF;
        boolean zR;
        String strF2;
        String strF3;
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            String strZ0 = z0(str);
            new Vector();
            int iLastIndexOf = strZ0.lastIndexOf(47);
            int i = 1;
            int i2 = 0;
            String strSubstring = strZ0.substring(0, iLastIndexOf == 0 ? 1 : iLastIndexOf);
            String strSubstring2 = strZ0.substring(iLastIndexOf + 1);
            String strZ = Util.z(strSubstring);
            byte[][] bArr = new byte[1][];
            boolean zO0 = o0(strSubstring2, bArr);
            if (zO0) {
                bArrA = bArr[0];
            } else {
                String strZ2 = Util.z(strZ0);
                if (P(strZ2).k()) {
                    strZ = strZ2;
                    bArrA = null;
                } else {
                    bArrA = this.a0 ? Util.A(bArr[0]) : Util.w(Util.z(strSubstring2), this.Z);
                }
            }
            J0(Util.w(strZ, this.Z));
            Header headerM0 = m0(this.K, new Header());
            int i3 = headerM0.a;
            int i4 = headerM0.b;
            h0(this.K, i3);
            if (i4 != 101 && i4 != 102) {
                throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            if (i4 == 101) {
                d1(this.K, this.K.i());
            }
            byte[] bArrP = this.K.p();
            int iA = 0;
            while (iA == 0) {
                R0(bArrP);
                headerM0 = m0(this.K, headerM0);
                int i5 = headerM0.a;
                int i6 = headerM0.b;
                if (i6 != 101 && i6 != 104) {
                    throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
                }
                if (i6 == 101) {
                    h0(this.K, i5);
                    int i7 = this.K.i();
                    if (i7 == i) {
                        break;
                    } else {
                        d1(this.K, i7);
                    }
                }
                this.K.B();
                g0(this.K.b, i2, 4);
                int i8 = i5 - 4;
                int i9 = this.K.i();
                this.K.A();
                while (i9 > 0) {
                    if (i8 > 0) {
                        this.K.D();
                        Buffer buffer = this.K;
                        byte[] bArr2 = buffer.b;
                        int length = bArr2.length;
                        int i10 = buffer.c;
                        int iG0 = g0(bArr2, i10, length > i10 + i8 ? i8 : bArr2.length - i10);
                        this.K.c += iG0;
                        i8 -= iG0;
                    }
                    byte[] bArrP2 = this.K.p();
                    byte[] bArrP3 = this.P <= 3 ? this.K.p() : null;
                    SftpATTRS sftpATTRSB = SftpATTRS.b(this.K);
                    if (iA == i) {
                        i9--;
                    } else {
                        if (bArrA == null) {
                            strF2 = null;
                            zR = true;
                        } else if (zO0) {
                            if (this.a0) {
                                bArrW = bArrP2;
                                strF = null;
                            } else {
                                strF = Util.f(bArrP2, this.Z);
                                bArrW = Util.w(strF, StandardCharsets.UTF_8);
                            }
                            String str2 = strF;
                            zR = Util.r(bArrA, bArrW);
                            strF2 = str2;
                        } else {
                            zR = Util.a(bArrA, bArrP2);
                            strF2 = null;
                        }
                        if (zR) {
                            if (strF2 == null) {
                                strF2 = Util.f(bArrP2, this.Z);
                            }
                            if (bArrP3 == null) {
                                strF3 = sftpATTRSB.toString() + " " + strF2;
                            } else {
                                strF3 = Util.f(bArrP3, this.Z);
                            }
                            iA = lsEntrySelector.a(new LsEntry(strF2, strF3, sftpATTRSB));
                        }
                        i9--;
                        i = 1;
                    }
                    i2 = 0;
                }
                i = 1;
            }
            N(bArrP, headerM0);
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    public SftpATTRS u0(String str) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            return K(r0(z0(str)));
        } catch (Exception e) {
            if (e instanceof SftpException) {
                throw ((SftpException) e);
            }
            throw new SftpException(4, e.toString(), e);
        }
    }

    public void v0(String str) throws SftpException {
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            G0(Util.w(z0(str), this.Z), null);
            Header headerM0 = m0(this.K, new Header());
            int i = headerM0.a;
            int i2 = headerM0.b;
            h0(this.K, i);
            if (i2 != 101) {
                throw new SftpException(4, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            int i3 = this.K.i();
            if (i3 == 0) {
                return;
            }
            d1(this.K, i3);
        } catch (Exception e) {
            if (!(e instanceof SftpException)) {
                throw new SftpException(4, e.toString(), e);
            }
            throw ((SftpException) e);
        }
    }

    public void w0(InputStream inputStream, String str, SftpProgressMonitor sftpProgressMonitor, int i) throws SftpException {
        Exception exc;
        SftpProgressMonitor sftpProgressMonitor2;
        try {
            ((Channel.MyPipedInputStream) this.S).y();
            String strZ0 = z0(str);
            Vector<String> vectorL0 = l0(strZ0);
            int size = vectorL0.size();
            if (size != 1) {
                if (size == 0) {
                    if (n0(strZ0)) {
                        throw new SftpException(4, strZ0);
                    }
                    Util.z(strZ0);
                }
                throw new SftpException(4, vectorL0.toString());
            }
            String strElementAt = vectorL0.elementAt(0);
            if (sftpProgressMonitor != null) {
                try {
                    sftpProgressMonitor2 = sftpProgressMonitor;
                    sftpProgressMonitor2.c(0, "-", strElementAt, -1L);
                } catch (Exception e) {
                    exc = e;
                    str = strElementAt;
                    if (!(exc instanceof SftpException)) {
                        throw new SftpException(4, exc.toString(), exc);
                    }
                    SftpException sftpException = (SftpException) exc;
                    if (sftpException.q != 4 || !q0(str)) {
                        throw sftpException;
                    }
                    throw new SftpException(4, str + " is a directory");
                }
            } else {
                sftpProgressMonitor2 = sftpProgressMonitor;
            }
            L(inputStream, strElementAt, sftpProgressMonitor2, i);
        } catch (Exception e2) {
            exc = e2;
        }
    }
}
