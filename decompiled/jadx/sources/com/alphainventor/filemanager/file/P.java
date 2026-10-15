package com.alphainventor.filemanager.file;

import android.text.TextUtils;
import ax.J1.C0735p;
import ax.J1.V;
import java.io.File;
import java.io.IOException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class P extends AbstractC5838l {
    private String n0;
    private String o0;
    private boolean p0;
    private boolean q0;
    private boolean r0;
    private long s0;
    private long t0;
    private ax.Q3.e u0;

    public P(O o, String str, ax.Q3.e eVar) {
        super(o);
        this.o0 = e0(str);
        this.q0 = true;
        this.r0 = true;
        d0();
        this.s0 = 0L;
        this.t0 = 0L;
        if (eVar != null) {
            this.u0 = eVar;
            this.p0 = eVar.isDirectory();
            if (V.A(R(), str)) {
                return;
            }
            this.s0 = eVar.s();
            if (this.u0.isDirectory()) {
                return;
            }
            this.t0 = eVar.e();
        }
    }

    private void d0() {
        this.n0 = C0735p.e(this, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    private String e0(String str) {
        return TextUtils.isEmpty(str) ? File.separator : V.Q(str.substring(str.indexOf("/")));
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
            return this.o0.compareTo(((P) abstractC5838l).o0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean g() {
        return y().startsWith(".");
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean h() {
        return this.q0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean isDirectory() {
        return this.p0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean k() {
        return this.r0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean m() {
        return false;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean n() {
        return this.u0 != null;
    }

    @Override // ax.J1.InterfaceC0722c
    public long o() {
        return this.t0;
    }

    @Override // ax.J1.InterfaceC0722c
    public long q() {
        return this.s0;
    }

    @Override // ax.J1.InterfaceC0722c
    public int r(boolean z) {
        String[] strArrI0;
        ax.Q3.e eVar = this.u0;
        if (eVar == null) {
            return -1000;
        }
        if (!eVar.isDirectory()) {
            return -2;
        }
        if (W() != -1) {
            return W();
        }
        try {
            strArrI0 = this.u0.i0();
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            if (ax.p338y1.l.i().m()) {
                ax.p338y1.l.i().b(u());
                if (ax.p338y1.l.i().m()) {
                    ax.p221pa.c.h().g().d("!!USB NUM CHILDREN!!").m(e).i();
                }
            }
            strArrI0 = null;
        }
        if (strArrI0 != null) {
            return strArrI0.length;
        }
        return -1;
    }

    @Override // ax.J1.InterfaceC0722c
    public String s() {
        return this.n0;
    }

    @Override // ax.J1.InterfaceC0722c
    public String t() {
        return this.o0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String y() {
        return V.h(this.o0);
    }
}
