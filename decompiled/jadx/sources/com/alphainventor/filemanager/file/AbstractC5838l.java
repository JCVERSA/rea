package com.alphainventor.filemanager.file;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import ax.I1.C0718i;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.EnumC0738t;
import ax.J1.InterfaceC0722c;
import ax.J1.V;
import com.alphainventor.filemanager.R;
import java.io.File;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC5838l implements Comparable<AbstractC5838l>, InterfaceC0722c {
    private ax.J1.F X;
    private int Y = -1;
    private long Z;
    private long h0;
    private String i0;
    private String j0;
    private String k0;
    private EnumC0738t l0;
    private String m0;
    private Context q;

    public AbstractC5838l(AbstractC5837k abstractC5837k) {
        this.q = abstractC5837k.n().getApplicationContext();
        this.X = abstractC5837k.s();
    }

    public C5840n B() {
        return null;
    }

    public final String C() {
        String strD = D();
        if (!V.y(strD)) {
            String str = P().H() + "-" + y() + "-" + strD;
            if (this instanceof u) {
                u uVar = (u) this;
                if (uVar.y0() != null) {
                    str = str + "-" + uVar.y0().getPath();
                }
            }
            ax.p221pa.c.h().g().b("NOT NORMALIZED PATH").h(str).i();
        }
        return strD;
    }

    protected abstract String D();

    public EnumC0738t E() {
        if (this.l0 == null) {
            this.l0 = C0739u.e(v());
        }
        return this.l0;
    }

    public String F() {
        String str;
        long jQ = q();
        if (this.h0 == jQ && (str = this.j0) != null) {
            return str;
        }
        this.h0 = jQ;
        if (jQ <= 0) {
            this.j0 = HttpUrl.FRAGMENT_ENCODE_SET;
        } else {
            this.j0 = ax.p045d2.x.l(this.q, jQ);
        }
        return this.j0;
    }

    public String G() {
        String str;
        if (this.h0 == q() && (str = this.k0) != null) {
            return str;
        }
        long jQ = q();
        this.h0 = jQ;
        if (jQ <= 0) {
            this.k0 = HttpUrl.FRAGMENT_ENCODE_SET;
        } else {
            this.k0 = ax.p045d2.x.m(this.q, jQ);
        }
        return this.k0;
    }

    public String H() {
        String str;
        long jQ = q();
        if (this.Z == jQ && (str = this.i0) != null) {
            return str;
        }
        this.Z = jQ;
        if (jQ <= 0) {
            this.i0 = HttpUrl.FRAGMENT_ENCODE_SET;
        } else {
            this.i0 = ax.p045d2.x.l(u(), jQ) + ' ' + ax.p045d2.x.q(u(), jQ);
        }
        return this.i0;
    }

    @SuppressLint({"DefaultLocale"})
    public String I(boolean z) {
        if (!isDirectory()) {
            long jO = o();
            return jO == -1 ? "-" : C0740v.f(this.q, jO);
        }
        int iR = r(z);
        if (iR >= 0) {
            return this.q.getResources().getQuantityString(R.plurals.num_items_plurals, iR, Integer.valueOf(iR));
        }
        return iR == -1100 ? HttpUrl.FRAGMENT_ENCODE_SET : this.q.getString(R.string.num_items_unknown);
    }

    protected Drawable J(Context context, boolean z) {
        if (isDirectory()) {
            return ax.p017b2.d.e(context, this, r(true) > 0, z);
        }
        return C0739u.d(context, C(), z);
    }

    public int K() {
        return isDirectory() ? R.drawable.icon_folder_full_s : C0739u.i(w(), false);
    }

    public int L() {
        return this.X.b();
    }

    public Drawable M(Context context) {
        return J(context, true);
    }

    public File N() {
        return new File(ax.G1.a.i(u(), this), w());
    }

    public u O() throws C0718i {
        File fileN = N();
        return (u) ax.J1.r.f(fileN).V0(fileN.getAbsolutePath());
    }

    public ax.p338y1.f P() {
        return this.X.d();
    }

    public String Q() {
        return C0740v.M(R(), C());
    }

    public ax.J1.F R() {
        return this.X;
    }

    public final String S() {
        String strT = T();
        if (!V.y(strT)) {
            ax.p221pa.c.h().g().b("!! PARENT PATH NOT NORMALIZED !!").k().h("location :" + P().H() + ", parent : " + strT + ", path :" + D()).i();
        }
        return strT;
    }

    protected abstract String T();

    public String U() {
        StringBuilder sb = new StringBuilder();
        sb.append(isDirectory() ? 'd' : '-');
        sb.append(h() ? 'r' : '-');
        sb.append(k() ? 'w' : '-');
        return sb.toString();
    }

    public String V() {
        if (this.m0 == null) {
            this.m0 = V.v(this);
        }
        return this.m0;
    }

    public int W() {
        return this.Y;
    }

    public Drawable X(Context context) {
        return J(context, false);
    }

    public File Y() {
        return new File(ax.G1.a.k(u(), this), w());
    }

    public boolean Z() {
        return false;
    }

    public boolean a0() {
        return false;
    }

    public void b0(int i) {
        this.Y = i;
    }

    Context u() {
        return this.q;
    }

    public String v() {
        return V.j(w());
    }

    public final String w() {
        String absolutePath;
        String strY = y();
        String strH = V.h(C());
        if (strY != null && !strY.equals(strH)) {
            if (this instanceof u) {
                u uVar = (u) this;
                File file = uVar.M0;
                String strSubstring = null;
                if (file != null) {
                    absolutePath = file.getAbsolutePath();
                    String absolutePath2 = uVar.y0().getAbsolutePath();
                    if (absolutePath2.startsWith(absolutePath)) {
                        String strSubstring2 = absolutePath2.substring(absolutePath.length());
                        if (strSubstring2.startsWith("/")) {
                            strSubstring = strSubstring2.substring(1);
                        }
                    }
                } else {
                    absolutePath = "null";
                }
                ax.p221pa.c.h().g().d("GFNA LOCAL!!!").k().h(P().H() + ":" + strY + ":" + strH + ":parentPath=" + absolutePath + ":dir=" + isDirectory() + ":alt=" + strSubstring).i();
                if (strSubstring != null) {
                    return strSubstring;
                }
            } else {
                ax.p221pa.c.h().g().d("GFNA").k().h(P().H() + ":" + strY + ":" + strH).i();
            }
        }
        return strY;
    }

    protected abstract String y();
}
