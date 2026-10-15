package com.alphainventor.filemanager.file;

import ax.p086f3.C2015v;
import ax.p086f3.d0;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class M extends AbstractC6239n {
    private SftpATTRS q0;
    private String r0;
    private String s0;
    private String t0;
    private boolean u0;
    private Long v0;

    public M(L l, L.e eVar, SftpATTRS sftpATTRS, String str) {
        super(l);
        this.q0 = sftpATTRS;
        this.r0 = str;
        this.s0 = d0.h(str);
        g0();
        if (sftpATTRS == null || !sftpATTRS.l()) {
            return;
        }
        this.u0 = true;
        try {
            this.q0 = eVar.o(str);
        } catch (SftpException unused) {
        }
    }

    public M(L l, String str) {
        this(l, null, null, str);
    }

    private void g0() {
        this.t0 = C2015v.e(this, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String C() {
        return this.s0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String F() {
        return this.r0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String V() {
        return d0.r(this.r0);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC6239n abstractC6239n) {
        try {
            return this.r0.compareTo(((M) abstractC6239n).r0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        String str = this.s0;
        return str != null && str.startsWith(".");
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        SftpATTRS sftpATTRS = this.q0;
        return (sftpATTRS == null || (sftpATTRS.g() & 4) == 0) ? false : true;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        SftpATTRS sftpATTRS = this.q0;
        return sftpATTRS != null && sftpATTRS.k();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        SftpATTRS sftpATTRS = this.q0;
        return (sftpATTRS == null || (sftpATTRS.g() & 2) == 0) ? false : true;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        return this.u0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        return this.q0 != null;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long p() {
        SftpATTRS sftpATTRS = this.q0;
        if (sftpATTRS != null) {
            return sftpATTRS.i();
        }
        return 0L;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long q() {
        if (this.v0 == null) {
            SftpATTRS sftpATTRS = this.q0;
            if (sftpATTRS != null) {
                this.v0 = Long.valueOf(((long) sftpATTRS.e()) * 1000);
            } else {
                this.v0 = -1L;
            }
        }
        return this.v0.longValue();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public int s(boolean z) {
        if (isDirectory()) {
            return Y();
        }
        return -2;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String t() {
        return this.t0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String u() {
        return this.r0;
    }
}
