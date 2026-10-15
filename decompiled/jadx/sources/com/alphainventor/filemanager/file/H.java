package com.alphainventor.filemanager.file;

import ax.J1.C0735p;
import ax.J1.V;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class H extends AbstractC5838l {
    private SftpATTRS n0;
    private String o0;
    private String p0;
    private String q0;
    private boolean r0;
    private Long s0;

    public H(G g, String str) {
        this(g, null, null, str);
    }

    private void d0() {
        this.q0 = C0735p.e(this, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String D() {
        return this.o0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String T() {
        return V.r(this.o0);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC5838l abstractC5838l) {
        try {
            return this.o0.compareTo(((H) abstractC5838l).o0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean g() {
        String str = this.p0;
        return str != null && str.startsWith(".");
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean h() {
        SftpATTRS sftpATTRS = this.n0;
        return (sftpATTRS == null || (sftpATTRS.g() & 4) == 0) ? false : true;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean isDirectory() {
        SftpATTRS sftpATTRS = this.n0;
        return sftpATTRS != null && sftpATTRS.k();
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean k() {
        SftpATTRS sftpATTRS = this.n0;
        return (sftpATTRS == null || (sftpATTRS.g() & 2) == 0) ? false : true;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean m() {
        return this.r0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean n() {
        return this.n0 != null;
    }

    @Override // ax.J1.InterfaceC0722c
    public long o() {
        SftpATTRS sftpATTRS = this.n0;
        if (sftpATTRS != null) {
            return sftpATTRS.i();
        }
        return 0L;
    }

    @Override // ax.J1.InterfaceC0722c
    public long q() {
        if (this.s0 == null) {
            SftpATTRS sftpATTRS = this.n0;
            if (sftpATTRS != null) {
                this.s0 = Long.valueOf(((long) sftpATTRS.e()) * 1000);
            } else {
                this.s0 = -1L;
            }
        }
        return this.s0.longValue();
    }

    @Override // ax.J1.InterfaceC0722c
    public int r(boolean z) {
        if (isDirectory()) {
            return W();
        }
        return -2;
    }

    @Override // ax.J1.InterfaceC0722c
    public String s() {
        return this.q0;
    }

    @Override // ax.J1.InterfaceC0722c
    public String t() {
        return this.o0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String y() {
        return this.p0;
    }

    public H(G g, G.e eVar, SftpATTRS sftpATTRS, String str) {
        super(g);
        this.n0 = sftpATTRS;
        this.o0 = str;
        this.p0 = V.h(str);
        d0();
        if (sftpATTRS == null || !sftpATTRS.l()) {
            return;
        }
        this.r0 = true;
        try {
            this.n0 = eVar.o(str);
        } catch (SftpException unused) {
        }
    }
}
