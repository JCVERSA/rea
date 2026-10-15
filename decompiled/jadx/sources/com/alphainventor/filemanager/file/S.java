package com.alphainventor.filemanager.file;

import ax.I1.C0718i;
import ax.J1.C0735p;
import ax.J1.V;

/* JADX INFO: loaded from: classes.dex */
public class S extends AbstractC5838l {
    ax.p263sa.c n0;
    String o0;
    String p0;

    public S(Q q, ax.p263sa.c cVar) throws C0718i {
        super(q);
        this.n0 = cVar;
        this.o0 = q.S(cVar.l());
    }

    private boolean d0() {
        ax.p263sa.c cVar = this.n0;
        if (cVar == null || cVar.r() == null) {
            return true;
        }
        return g0(this.n0.r());
    }

    private static boolean g0(String str) {
        try {
            return Integer.parseInt(str) == 1;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    protected String D() {
        return this.o0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    protected String T() {
        return V.r(this.o0);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC5838l abstractC5838l) {
        try {
            return this.o0.compareTo(((S) abstractC5838l).o0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public boolean e0() {
        ax.p263sa.c cVar = this.n0;
        if (cVar == null) {
            return false;
        }
        return cVar.i();
    }

    public boolean f0() {
        ax.p263sa.c cVar = this.n0;
        if (cVar == null || cVar.j() == null) {
            return false;
        }
        return g0(this.n0.j());
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean g() {
        if (w().startsWith(".")) {
            return true;
        }
        return !d0();
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean h() {
        return true;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean isDirectory() {
        if ("/".equals(this.o0)) {
            return true;
        }
        ax.p263sa.c cVar = this.n0;
        return cVar != null && cVar.t();
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean k() {
        ax.p263sa.c cVar = this.n0;
        if (cVar == null || cVar.o() == null) {
            return true;
        }
        return !Boolean.parseBoolean(this.n0.o());
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean m() {
        return false;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean n() {
        return "/".equals(this.o0) || this.n0 != null;
    }

    @Override // ax.J1.InterfaceC0722c
    public long o() {
        ax.p263sa.c cVar = this.n0;
        if (cVar == null) {
            return 0L;
        }
        return cVar.c().longValue();
    }

    @Override // ax.J1.InterfaceC0722c
    public long q() {
        ax.p263sa.c cVar = this.n0;
        if (cVar == null || cVar.m() == null) {
            return -1L;
        }
        return this.n0.m().getTime();
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
        if (this.p0 == null) {
            this.p0 = C0735p.e(this, "application/octet-stream");
        }
        return this.p0;
    }

    @Override // ax.J1.InterfaceC0722c
    public String t() {
        return this.o0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    protected String y() {
        return V.h(this.o0);
    }

    public S(Q q, String str) {
        super(q);
        this.o0 = str;
    }
}
