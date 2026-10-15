package com.jcraft.jsch;

import java.util.Date;
import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
public class SftpATTRS {
    long b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int a = 0;
    String[] h = null;

    private SftpATTRS() {
    }

    static SftpATTRS b(Buffer buffer) {
        int i;
        SftpATTRS sftpATTRS = new SftpATTRS();
        int i2 = buffer.i();
        sftpATTRS.a = i2;
        if ((i2 & 1) != 0) {
            sftpATTRS.b = buffer.k();
        }
        if ((sftpATTRS.a & 2) != 0) {
            sftpATTRS.c = buffer.i();
            sftpATTRS.d = buffer.i();
        }
        if ((sftpATTRS.a & 4) != 0) {
            sftpATTRS.e = buffer.i();
        }
        if ((sftpATTRS.a & 8) != 0) {
            sftpATTRS.f = buffer.i();
        }
        if ((sftpATTRS.a & 8) != 0) {
            sftpATTRS.g = buffer.i();
        }
        if ((sftpATTRS.a & Integer.MIN_VALUE) != 0 && (i = buffer.i()) > 0) {
            sftpATTRS.h = new String[i * 2];
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i3 * 2;
                sftpATTRS.h[i4] = Util.c(buffer.p());
                sftpATTRS.h[i4 + 1] = Util.c(buffer.p());
            }
        }
        return sftpATTRS;
    }

    private boolean m(int i) {
        return (this.a & 4) != 0 && (this.e & 61440) == i;
    }

    void a(Buffer buffer) {
        int length;
        buffer.v(this.a);
        if ((this.a & 1) != 0) {
            buffer.w(this.b);
        }
        if ((this.a & 2) != 0) {
            buffer.v(this.c);
            buffer.v(this.d);
        }
        if ((this.a & 4) != 0) {
            buffer.v(this.e);
        }
        if ((this.a & 8) != 0) {
            buffer.v(this.f);
        }
        if ((this.a & 8) != 0) {
            buffer.v(this.g);
        }
        if ((this.a & Integer.MIN_VALUE) == 0 || (length = this.h.length / 2) <= 0) {
            return;
        }
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            buffer.y(Util.v(this.h[i2]));
            buffer.y(Util.v(this.h[i2 + 1]));
        }
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.g;
    }

    public String f() {
        return new Date(((long) this.g) * 1000).toString();
    }

    public int g() {
        return this.e;
    }

    public String h() {
        StringBuilder sb = new StringBuilder(10);
        if (k()) {
            sb.append('d');
        } else if (l()) {
            sb.append('l');
        } else {
            sb.append('-');
        }
        if ((this.e & 256) != 0) {
            sb.append('r');
        } else {
            sb.append('-');
        }
        if ((this.e & 128) != 0) {
            sb.append('w');
        } else {
            sb.append('-');
        }
        int i = this.e;
        if ((i & 2048) != 0) {
            sb.append('s');
        } else if ((i & 64) != 0) {
            sb.append('x');
        } else {
            sb.append('-');
        }
        if ((this.e & 32) != 0) {
            sb.append('r');
        } else {
            sb.append('-');
        }
        if ((this.e & 16) != 0) {
            sb.append('w');
        } else {
            sb.append('-');
        }
        int i2 = this.e;
        if ((i2 & 1024) != 0) {
            sb.append('s');
        } else if ((i2 & 8) != 0) {
            sb.append('x');
        } else {
            sb.append('-');
        }
        if ((this.e & 4) != 0) {
            sb.append('r');
        } else {
            sb.append('-');
        }
        if ((this.e & 2) != 0) {
            sb.append('w');
        } else {
            sb.append('-');
        }
        if ((this.e & 1) != 0) {
            sb.append('x');
        } else {
            sb.append('-');
        }
        return sb.toString();
    }

    public long i() {
        return this.b;
    }

    public int j() {
        return this.c;
    }

    public boolean k() {
        return m(Http2.INITIAL_MAX_FRAME_SIZE);
    }

    public boolean l() {
        return m(40960);
    }

    int n() {
        int i = this.a;
        int length = (i & 1) != 0 ? 12 : 4;
        if ((i & 2) != 0) {
            length += 8;
        }
        if ((i & 4) != 0) {
            length += 4;
        }
        if ((i & 8) != 0) {
            length += 8;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            length += 4;
            int length2 = this.h.length / 2;
            if (length2 > 0) {
                for (int i2 = 0; i2 < length2; i2++) {
                    int i3 = i2 * 2;
                    length = length + 4 + this.h[i3].length() + 4 + this.h[i3 + 1].length();
                }
            }
        }
        return length;
    }

    public void o(int i, int i2) {
        this.a |= 8;
        this.f = i;
        this.g = i2;
    }

    void p(int i) {
        this.a = i;
    }

    public String toString() {
        return h() + " " + j() + " " + d() + " " + i() + " " + f();
    }
}
