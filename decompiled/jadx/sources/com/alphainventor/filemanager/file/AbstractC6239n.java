package com.alphainventor.filemanager.file;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import ax.p070e3.C1966j;
import ax.p086f3.C2013t;
import ax.p086f3.C2017x;
import ax.p086f3.EnumC2019z;
import ax.p086f3.InterfaceC1996b;
import ax.p086f3.d0;
import com.alphainventor.filemanager.R;
import java.io.File;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC6239n implements Comparable<AbstractC6239n>, InterfaceC1996b {
    private ax.p086f3.K X;
    private int Y = -1;
    private long Z;
    private long k0;
    private int l0;
    private String m0;
    private String n0;
    private EnumC2019z o0;
    private String p0;
    private Context q;

    public AbstractC6239n(AbstractC6238m abstractC6238m) {
        this.q = abstractC6238m.p().getApplicationContext();
        this.X = abstractC6238m.v();
    }

    public final String B() {
        String absolutePath;
        String strC = C();
        String strH = d0.h(E());
        if (strC != null && !strC.equals(strH)) {
            if (this instanceof C6249y) {
                C6249y c6249y = (C6249y) this;
                File file = c6249y.S0;
                String strSubstring = null;
                if (file != null) {
                    absolutePath = file.getAbsolutePath();
                    String absolutePath2 = c6249y.G0().getAbsolutePath();
                    if (absolutePath2.startsWith(absolutePath)) {
                        String strSubstring2 = absolutePath2.substring(absolutePath.length());
                        if (strSubstring2.startsWith("/")) {
                            strSubstring = strSubstring2.substring(1);
                        }
                    }
                } else {
                    absolutePath = "null";
                }
                ax.p302sb.c.h().g().d("GFNA LOCAL!!!").k().h(Q().H() + ":" + strC + ":" + strH + ":parentPath=" + absolutePath + ":dir=" + isDirectory() + ":alt=" + strSubstring).i();
                if (strSubstring != null) {
                    return strSubstring;
                }
            } else {
                ax.p302sb.c.h().g().d("GFNA").k().h(Q().H() + ":" + strC + ":" + strH).i();
            }
        }
        return strC;
    }

    protected abstract String C();

    public C6241p D() {
        return null;
    }

    public final String E() {
        String strF = F();
        if (!d0.B(strF)) {
            String str = Q().H() + "-" + C() + "-" + strF;
            if (this instanceof C6249y) {
                C6249y c6249y = (C6249y) this;
                if (c6249y.G0() != null) {
                    str = str + "-" + c6249y.G0().getPath();
                }
            }
            ax.p302sb.c.h().g().b("NOT NORMALIZED PATH").h(str).i();
        }
        return strF;
    }

    protected abstract String F();

    public EnumC2019z G() {
        if (this.o0 == null) {
            this.o0 = ax.p086f3.A.g(y());
        }
        return this.o0;
    }

    public String H(int i) {
        String str;
        long jQ = q();
        v(i);
        if (this.k0 == jQ && (str = this.n0) != null) {
            return str;
        }
        this.k0 = jQ;
        String strW = w(jQ, i);
        this.n0 = strW;
        return strW;
    }

    public String I(int i) {
        String str;
        long jQ = q();
        v(i);
        if (this.Z == jQ && (str = this.m0) != null) {
            return str;
        }
        this.Z = jQ;
        if (jQ <= 0) {
            this.m0 = HttpUrl.FRAGMENT_ENCODE_SET;
        } else {
            this.m0 = C2013t.i(x(), jQ, i);
        }
        return this.m0;
    }

    @SuppressLint({"DefaultLocale"})
    public String J(boolean z) {
        if (!isDirectory()) {
            long jP = p();
            return jP == -1 ? "-" : ax.p086f3.B.i(this.q, jP);
        }
        int iS = s(z);
        if (iS >= 0) {
            return this.q.getResources().getQuantityString(R.plurals.num_items_plurals, iS, Integer.valueOf(iS));
        }
        return iS == -1100 ? HttpUrl.FRAGMENT_ENCODE_SET : this.q.getString(R.string.num_items_unknown);
    }

    protected Drawable K(Context context, boolean z) {
        return isDirectory() ? ax.p374x3.d.e(context, this, b0(), z) : ax.p086f3.A.f(context, E(), z);
    }

    public int L() {
        return isDirectory() ? R.drawable.icon_folder_full_s : ax.p086f3.A.k(B(), false);
    }

    public int M() {
        return this.X.b();
    }

    public Drawable N(Context context) {
        return K(context, true);
    }

    public File O() {
        return new File(ax.p038c3.a.j(x(), this), B());
    }

    public C6249y P() throws C1966j {
        File fileO = O();
        return (C6249y) C2017x.f(fileO).a1(fileO.getAbsolutePath());
    }

    public ax.U2.f Q() {
        return this.X.d();
    }

    public String R() {
        return ax.p086f3.B.U(S(), E());
    }

    public ax.p086f3.K S() {
        return this.X;
    }

    public String T() {
        return ax.p086f3.B.U(S(), U());
    }

    public final String U() {
        String strV = V();
        if (!d0.B(strV)) {
            ax.p302sb.c.h().g().b("!! PARENT PATH NOT NORMALIZED !!").k().h("location :" + Q().H() + ", parent : " + strV + ", path :" + F()).i();
        }
        return strV;
    }

    protected abstract String V();

    public String W() {
        StringBuilder sb = new StringBuilder();
        sb.append(isDirectory() ? 'd' : '-');
        sb.append(h() ? 'r' : '-');
        sb.append(k() ? 'w' : '-');
        return sb.toString();
    }

    public String X() {
        if (this.p0 == null) {
            this.p0 = d0.w(this);
        }
        return this.p0;
    }

    public int Y() {
        return this.Y;
    }

    public Drawable Z(Context context) {
        return K(context, false);
    }

    public File a0() {
        return new File(ax.p038c3.a.l(x(), this), B());
    }

    public boolean b0() {
        return s(true) > 0;
    }

    public boolean c0() {
        return false;
    }

    public boolean d0() {
        return false;
    }

    public void e0(int i) {
        this.Y = i;
    }

    protected boolean v(int i) {
        if (i == this.l0) {
            return false;
        }
        this.m0 = null;
        this.n0 = null;
        this.l0 = i;
        return true;
    }

    protected String w(long j, int i) {
        return j <= 0 ? HttpUrl.FRAGMENT_ENCODE_SET : C2013t.h(this.q, j, i);
    }

    Context x() {
        return this.q;
    }

    public String y() {
        return d0.j(B());
    }
}
