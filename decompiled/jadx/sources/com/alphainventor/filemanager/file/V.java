package com.alphainventor.filemanager.file;

import android.text.TextUtils;
import ax.p086f3.C2015v;
import ax.p086f3.d0;
import java.io.File;
import java.io.IOException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class V extends AbstractC6239n {
    private String q0;
    private String r0;
    private boolean s0;
    private boolean t0;
    private boolean u0;
    private long v0;
    private long w0;
    private ax.p216n5.e x0;

    public V(U u, String str, ax.p216n5.e eVar) {
        super(u);
        this.r0 = h0(str);
        this.t0 = true;
        this.u0 = true;
        g0();
        this.v0 = 0L;
        this.w0 = 0L;
        if (eVar != null) {
            this.x0 = eVar;
            this.s0 = eVar.isDirectory();
            if (d0.D(S(), str)) {
                return;
            }
            this.v0 = eVar.g();
            if (this.x0.isDirectory()) {
                return;
            }
            this.w0 = eVar.getLength();
        }
    }

    private void g0() {
        this.q0 = C2015v.e(this, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    private String h0(String str) {
        return TextUtils.isEmpty(str) ? File.separator : d0.U(str.substring(str.indexOf("/")));
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String C() {
        return d0.h(this.r0);
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
            return this.r0.compareTo(((V) abstractC6239n).r0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        return C().startsWith(".");
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        return this.t0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        return this.s0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        return this.u0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        return false;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        return this.x0 != null;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long p() {
        return this.w0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long q() {
        return this.v0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public int s(boolean z) {
        String[] strArrL0;
        ax.p216n5.e eVar = this.x0;
        if (eVar == null) {
            return -1000;
        }
        if (!eVar.isDirectory()) {
            return -2;
        }
        if (Y() != -1) {
            return Y();
        }
        try {
            strArrL0 = this.x0.l0();
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            if (ax.U2.o.i().m()) {
                ax.U2.o.i().b(x());
                if (ax.U2.o.i().m()) {
                    ax.p302sb.c.h().g().d("!!USB NUM CHILDREN!!").m(e).i();
                }
            }
            strArrL0 = null;
        }
        if (strArrL0 != null) {
            return strArrL0.length;
        }
        return -1;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String t() {
        return this.q0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String u() {
        return this.r0;
    }
}
