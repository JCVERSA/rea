package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p063dc.C1925a0;
import ax.p063dc.C1927b0;
import ax.p070e3.C1960d;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C2017x;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.g0;
import ax.p086f3.i0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class Q extends AbstractC6238m implements i0 {
    public static final String n = null;
    private static final Logger o = Logger.getLogger("FileManager.SmbFileHelper");
    private static b p;
    private P h;
    private boolean i;
    private String j;
    S k;
    N l;
    O m;

    private static class a extends ax.p406z3.q<String, Void, Boolean> {
        Context h;
        WifiManager i;
        String j;
        String k;
        int l;
        String m;
        String n;
        InterfaceC6228c.a o;
        Q p;
        String q;
        String r;
        String s;

        public a(Context context, ax.p038c3.o oVar, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.h = context;
            E(oVar);
            this.o = aVar;
            this.i = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }

        public a(Context context, Q q, InterfaceC6228c.a aVar, int i) {
            super(ax.z3.q.e.CONNECT);
            this.h = context;
            this.p = q;
            this.o = aVar;
            E(Q.b0(context).k(i));
            this.i = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }

        private ax.Xb.b C(Q q, String str, String str2) throws UnknownHostException {
            String str3;
            DhcpInfo dhcpInfo;
            WifiManager wifiManager = this.i;
            boolean z = wifiManager == null || wifiManager.getWifiState() != 3 || (dhcpInfo = this.i.getDhcpInfo()) == null || dhcpInfo.ipAddress == 0 || z(dhcpInfo.dns1);
            ax.p031bc.g.A(str);
            ax.Xb.b.l(z);
            if (TextUtils.isEmpty(str2)) {
                return D(str, true);
            }
            if (q == null || !q.i0()) {
                str3 = str + "." + str2;
            } else {
                str3 = str;
                str = str + "." + str2;
            }
            try {
                return D(str, true);
            } catch (UnknownHostException unused) {
                ax.Xb.b bVarD = D(str3, true);
                if (q != null) {
                    q.l0(!q.i0());
                }
                return bVarD;
            }
        }

        /* JADX WARN: Code duplicated, block: B:16:0x002d  */
        /* JADX WARN: Code duplicated, block: B:35:0x0063  */
        /* JADX WARN: Code duplicated, block: B:37:0x0066  */
        /* JADX WARN: Code duplicated, block: B:60:0x0087 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        private ax.Xb.b D(String str, boolean z) throws UnknownHostException {
            boolean z2;
            ax.Xb.b[] bVarArrC = ax.Xb.b.c(str, z);
            if (bVarArrC.length == 0) {
                C2461b.f();
                throw new UnknownHostException("No matched ip address");
            }
            if (bVarArrC.length > 1) {
                byte[] bArrF = ax.p214n3.a.f(this.h);
                if (bArrF.length < 4 || bArrF[0] == 0) {
                    z2 = false;
                } else {
                    InetAddress byAddress = InetAddress.getByAddress(bArrF);
                    if (byAddress.isAnyLocalAddress() || byAddress.isLoopbackAddress()) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                }
                ax.Xb.b bVar = null;
                ax.Xb.b bVar2 = null;
                for (int length = bVarArrC.length - 1; length >= 0; length--) {
                    ax.Xb.b bVar3 = bVarArrC[length];
                    if (bVar3 != null && !(bVar3.b() instanceof Inet6Address)) {
                        Object objB = bVar3.b();
                        if (bVar != null) {
                            if (!z2 && (objB instanceof ax.p031bc.g)) {
                                byte[] bArrH = ((ax.p031bc.g) objB).h();
                                if (bArrH.length >= 4 && bArrH[0] == bArrF[0] && bArrH[1] == bArrF[1] && bArrH[2] == bArrF[2]) {
                                    return bVar3;
                                }
                            }
                        } else if (objB instanceof ax.p031bc.g) {
                            InetAddress inetAddressS = ((ax.p031bc.g) objB).s();
                            if (inetAddressS.isLoopbackAddress()) {
                                continue;
                            } else {
                                if (!inetAddressS.isLinkLocalAddress()) {
                                    bVar = bVar3;
                                } else if (bVar2 == null) {
                                    bVar2 = bVar3;
                                }
                                if (!z2) {
                                    continue;
                                }
                            }
                        } else {
                            bVar = bVar3;
                            if (!z2) {
                                continue;
                            }
                        }
                    }
                }
                if (bVar != null) {
                    return bVar;
                }
                if (bVar2 != null) {
                    return bVar2;
                }
            }
            return bVarArrC[0];
        }

        private void E(ax.p038c3.o oVar) {
            this.j = oVar.c();
            this.k = oVar.d();
            this.l = oVar.h();
            this.m = oVar.k();
            this.n = oVar.g();
            this.q = oVar.f();
            this.r = oVar.j();
        }

        private P F(c cVar, ax.P9.c cVar2, boolean z, ax.Xb.b bVar, String str) throws Throwable {
            P pG = G(cVar, cVar2, z, bVar, str, this.l, "?", "GUEST", HttpUrl.FRAGMENT_ENCODE_SET);
            if (pG != null) {
                return pG;
            }
            P pG2 = G(cVar, cVar2, z, bVar, str, this.l, "?", "Guest", HttpUrl.FRAGMENT_ENCODE_SET);
            if (pG2 != null) {
                return pG2;
            }
            P pG3 = G(cVar, cVar2, z, bVar, str, this.l, "?", "guest", HttpUrl.FRAGMENT_ENCODE_SET);
            return pG3 == null ? G(cVar, cVar2, z, bVar, str, this.l, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET) : pG3;
        }

        /* JADX WARN: Code duplicated, block: B:58:0x00bb A[Catch: IOException -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #3 {IOException -> 0x0073, blocks: (B:40:0x006b, B:41:0x006f, B:50:0x0094, B:58:0x00bb), top: B:84:0x0045 }] */
        /* JADX WARN: Code duplicated, block: B:62:0x00c4 A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TRY_ENTER, TryCatch #12 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:67:0x00cc A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TryCatch #12 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:69:0x00d1 A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TryCatch #12 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:71:0x00d7 A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TRY_LEAVE, TryCatch #12 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:76:0x010c  */
        /* JADX WARN: Code duplicated, block: B:78:0x0114 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:86:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:90:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        private P G(c cVar, ax.P9.c cVar2, boolean z, ax.Xb.b bVar, String str, int i, String str2, String str3, String str4) throws Throwable {
            ax.Q9.b bVarN;
            ax.Y9.b bVarD;
            ax.S9.a aVarP;
            ax.p063dc.r rVar = (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) ? ax.p063dc.r.v0 : new ax.p063dc.r(str2, str3, str4);
            ax.Y9.b bVar2 = 0;
            boolean z2 = false;
            if (cVar == c.SMB1AND2 || cVar == c.SMB2AND3) {
                if ("?".equals(str2)) {
                    if ("GUEST".equals(str3)) {
                        str3 = "SMB2GUESTTESTACCOUNT";
                    }
                    bVarN = O.n(HttpUrl.FRAGMENT_ENCODE_SET, str3, str4);
                } else {
                    bVarN = O.n(str2, str3, str4);
                }
                try {
                    try {
                        try {
                            bVarD = O.d(cVar2, z, str, i, bVarN);
                            z2 = true;
                            try {
                                if (cVar == c.SMB2AND3) {
                                    P p = new P(bVarN, str, i);
                                    p.c = rVar;
                                    if (bVarD != null) {
                                        try {
                                            bVarD.p().close();
                                        } catch (IOException unused) {
                                        }
                                    }
                                    return p;
                                }
                                if (bVarD != null) {
                                    aVarP = bVarD.p();
                                    aVarP.close();
                                }
                            } catch (ax.R9.d e) {
                                e = e;
                                w("SMB2 : " + e.getMessage());
                                e.printStackTrace();
                                if (bVarD != null) {
                                    aVarP = bVarD.p();
                                    aVarP.close();
                                }
                            } catch (IOException e2) {
                                e = e2;
                                w("SMB2 : " + e.getMessage());
                                e.printStackTrace();
                                if (bVarD != null) {
                                    aVarP = bVarD.p();
                                    aVarP.close();
                                }
                            } catch (RuntimeException e3) {
                                e = e3;
                                e.printStackTrace();
                                ax.p302sb.c.h().g().b("SMB2 AUTH RUNTIME EXCEPTION").m(e).i();
                                if (bVarD != null) {
                                    aVarP = bVarD.p();
                                    aVarP.close();
                                }
                            }
                        } catch (IOException unused2) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        bVar2 = cVar2;
                        if (bVar2 != 0) {
                            try {
                                bVar2.p().close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                } catch (ax.R9.d e4) {
                    e = e4;
                    bVarD = null;
                    w("SMB2 : " + e.getMessage());
                    e.printStackTrace();
                    if (bVarD != null) {
                        aVarP = bVarD.p();
                        aVarP.close();
                    }
                    if (bVar != null) {
                        try {
                            if (i > 0) {
                                ax.p063dc.i0.c(bVar, i, rVar);
                            } else {
                                ax.p063dc.i0.d(bVar, rVar);
                            }
                            return z2 ? new P(bVarN, str, i, rVar) : new P(rVar);
                        } catch (C1925a0 e5) {
                            w("SMB1 : " + e5.getMessage());
                            e5.printStackTrace();
                        } catch (RuntimeException e6) {
                            ax.p302sb.c.h().d("SMB1 LOGON!!").m(e6).i();
                        }
                    }
                    if (!z2) {
                        return null;
                    }
                    P p2 = new P(bVarN, str, i);
                    p2.c = rVar;
                    return p2;
                } catch (IOException e7) {
                    e = e7;
                    bVarD = null;
                    w("SMB2 : " + e.getMessage());
                    e.printStackTrace();
                    if (bVarD != null) {
                        aVarP = bVarD.p();
                        aVarP.close();
                    }
                    if (bVar != null) {
                        if (i > 0) {
                            ax.p063dc.i0.c(bVar, i, rVar);
                        } else {
                            ax.p063dc.i0.d(bVar, rVar);
                        }
                        if (z2) {
                        }
                    }
                    if (!z2) {
                        return null;
                    }
                    P p3 = new P(bVarN, str, i);
                    p3.c = rVar;
                    return p3;
                } catch (RuntimeException e8) {
                    e = e8;
                    bVarD = null;
                } catch (Throwable th2) {
                    th = th2;
                    if (bVar2 != 0) {
                        bVar2.p().close();
                    }
                    throw th;
                }
            } else {
                bVarN = null;
            }
            if (bVar != null) {
                if (i > 0) {
                    ax.p063dc.i0.c(bVar, i, rVar);
                } else {
                    ax.p063dc.i0.d(bVar, rVar);
                }
                if (z2) {
                }
            }
            if (!z2) {
                return null;
            }
            P p4 = new P(bVarN, str, i);
            p4.c = rVar;
            return p4;
        }

        private P H(c cVar, ax.P9.c cVar2, boolean z, ax.Xb.b bVar, String str, String str2) throws Throwable {
            String str3;
            boolean z2;
            String str4 = this.m.length() > 0 ? this.m : null;
            String str5 = this.n.length() > 0 ? this.n : null;
            if (this.j.length() > 0) {
                str3 = this.j;
                z2 = false;
            } else {
                str3 = str2;
                z2 = true;
            }
            P pG = G(cVar, cVar2, z, bVar, str, this.l, str3, str4, str5);
            return (pG == null && z2) ? G(cVar, cVar2, z, bVar, str, this.l, null, str4, str5) : pG;
        }

        private void w(String str) {
            if (this.s == null) {
                this.s = str;
                return;
            }
            this.s += "," + str;
        }

        /* JADX WARN: Code duplicated, block: B:101:0x014e  */
        /* JADX WARN: Code duplicated, block: B:103:0x0152  */
        /* JADX WARN: Code duplicated, block: B:104:0x0155  */
        /* JADX WARN: Code duplicated, block: B:121:0x01c3  */
        /* JADX WARN: Code duplicated, block: B:124:0x01e2  */
        /* JADX WARN: Code duplicated, block: B:129:0x01f3  */
        /* JADX WARN: Code duplicated, block: B:140:0x0213 A[Catch: d | L | IOException | IllegalArgumentException -> 0x021b, TryCatch #21 {d | L | IOException | IllegalArgumentException -> 0x021b, blocks: (B:138:0x0207, B:140:0x0213, B:143:0x021d), top: B:168:0x0207 }] */
        /* JADX WARN: Code duplicated, block: B:143:0x021d A[Catch: d | L | IOException | IllegalArgumentException -> 0x021b, TRY_LEAVE, TryCatch #21 {d | L | IOException | IllegalArgumentException -> 0x021b, blocks: (B:138:0x0207, B:140:0x0213, B:143:0x021d), top: B:168:0x0207 }] */
        /* JADX WARN: Code duplicated, block: B:152:0x0233  */
        /* JADX WARN: Code duplicated, block: B:156:0x0229 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:169:0x0202 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:175:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:32:0x005c  */
        /* JADX WARN: Code duplicated, block: B:40:0x0079  */
        /* JADX WARN: Code duplicated, block: B:42:0x007e  */
        /* JADX WARN: Code duplicated, block: B:44:0x0082  */
        /* JADX WARN: Code duplicated, block: B:45:0x0088  */
        /* JADX WARN: Code duplicated, block: B:47:0x00a4  */
        /* JADX WARN: Code duplicated, block: B:93:0x0132  */
        /* JADX WARN: Instruction removed from duplicated block: B:121:0x01c3, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:45:0x0088, please report this as an issue */
        private P x(String str, String str2, String str3, ax.Xb.b bVar, boolean z) throws Throwable {
            boolean z2;
            ax.P9.c cVarJ;
            ax.S9.a aVarF;
            ax.S9.a aVarF2;
            ax.S9.a aVarF3;
            c cVar;
            ax.p380x9.g gVarA;
            c cVar2;
            P pH;
            boolean z3;
            ax.P9.c cVarJ2;
            P pH2;
            a aVar = this;
            String str4 = str3;
            boolean z4 = true;
            P p = null;
            if (!"SMB1".equals(str)) {
                z2 = false;
                if (("SMB2".equals(str) || "SMB3".equals(str)) && ax.p006a3.Q.i() && !TextUtils.isEmpty(str4)) {
                    try {
                        cVarJ = O.j(str, true, true);
                        try {
                            aVarF2 = O.f(cVarJ, str4, aVar.l);
                        } catch (ax.R9.d e) {
                            e = e;
                            if (e.getMessage() == null && e.getMessage().contains("EOF while reading packet")) {
                                try {
                                    cVarJ = O.j(str, false, true);
                                    try {
                                        aVarF = O.f(cVarJ, str4, aVar.l);
                                    } catch (ax.R9.d | IOException | IllegalArgumentException unused) {
                                        z4 = false;
                                        z2 = z4;
                                        aVarF = null;
                                        z4 = false;
                                        if (!z4) {
                                            if (e instanceof SocketTimeoutException) {
                                                aVar.w("SMB2 timeout");
                                            } else {
                                                e.printStackTrace();
                                                aVar.w("Protocol not supported : " + e.getMessage());
                                            }
                                            return null;
                                        }
                                        aVarF2 = aVarF;
                                        z4 = z2;
                                        if (aVarF2 == null) {
                                        }
                                        aVarF3 = aVarF2;
                                        cVar = c.SMB2AND3;
                                        z2 = z4;
                                        if (cVar == c.SMB1) {
                                            try {
                                                N.r(bVar, aVar.l);
                                            } catch (IOException e2) {
                                                if (e2 instanceof ax.p095fc.d) {
                                                    ax.p095fc.d dVar = (ax.p095fc.d) e2;
                                                    if (dVar.a() != null) {
                                                        aVar.w("Cannot connect to the SMB1 server : " + bVar + " : " + e2.getMessage() + " : " + dVar.a().getMessage());
                                                    } else {
                                                        aVar.w("Cannot connect to the SMB1 server : " + bVar + " : " + e2.getMessage());
                                                    }
                                                } else {
                                                    aVar.w("Cannot connect to the SMB1 server : " + bVar + " : " + e2.getMessage());
                                                }
                                                return null;
                                            } catch (NullPointerException e3) {
                                                aVar.w("Cannot connect to the SMB1 server : " + bVar + " : " + e3.getMessage());
                                                return null;
                                            }
                                        }
                                        if (z) {
                                            cVar2 = cVar;
                                            try {
                                                pH = aVar.F(cVar2, cVarJ, true, bVar, str4);
                                                p = pH;
                                                z3 = true;
                                            } catch (ax.p070e3.L unused2) {
                                                aVar = this;
                                                str4 = str3;
                                                if (aVarF3 != null) {
                                                    try {
                                                        aVarF3.close();
                                                        aVarF3 = null;
                                                    } catch (Exception unused3) {
                                                    }
                                                }
                                                try {
                                                    cVarJ2 = O.j(str, z2, false);
                                                    aVarF3 = O.f(cVarJ2, str4, aVar.l);
                                                    if (z) {
                                                        pH2 = aVar.F(cVar2, cVarJ2, false, bVar, str4);
                                                    } else {
                                                        pH2 = aVar.H(cVar2, cVarJ2, false, bVar, str4, str2);
                                                    }
                                                    p = pH2;
                                                } catch (ax.R9.d | ax.p070e3.L | IOException | IllegalArgumentException unused4) {
                                                }
                                                z3 = false;
                                            }
                                        } else {
                                            cVar2 = cVar;
                                            try {
                                                pH = aVar.H(cVar2, cVarJ, true, bVar, str4, str2);
                                                p = pH;
                                                z3 = true;
                                            } catch (ax.p070e3.L unused5) {
                                                if (aVarF3 != null) {
                                                    aVarF3.close();
                                                    aVarF3 = null;
                                                }
                                                cVarJ2 = O.j(str, z2, false);
                                                aVarF3 = O.f(cVarJ2, str4, aVar.l);
                                                if (z) {
                                                    pH2 = aVar.F(cVar2, cVarJ2, false, bVar, str4);
                                                } else {
                                                    pH2 = aVar.H(cVar2, cVarJ2, false, bVar, str4, str2);
                                                }
                                                p = pH2;
                                                z3 = false;
                                            }
                                        }
                                        if (aVarF3 != null) {
                                            try {
                                                aVarF3.close();
                                            } catch (Exception e4) {
                                                e4.printStackTrace();
                                            }
                                        }
                                        if (p != null) {
                                            p.g = z2;
                                            p.h = str;
                                            p.i = z3;
                                        }
                                        return p;
                                    }
                                } catch (ax.R9.d | IOException | IllegalArgumentException unused6) {
                                }
                            } else {
                                aVarF = null;
                                z4 = false;
                                z2 = true;
                            }
                            if (!z4) {
                                if (e instanceof SocketTimeoutException) {
                                    aVar.w("SMB2 timeout");
                                } else {
                                    e.printStackTrace();
                                    aVar.w("Protocol not supported : " + e.getMessage());
                                }
                                return null;
                            }
                            aVarF2 = aVarF;
                            z4 = z2;
                        } catch (IOException e5) {
                            e = e5;
                            if (e.getMessage() == null) {
                                aVarF = null;
                                z4 = false;
                                z2 = true;
                            } else {
                                aVarF = null;
                                z4 = false;
                                z2 = true;
                            }
                            if (!z4) {
                                if (e instanceof SocketTimeoutException) {
                                    aVar.w("SMB2 timeout");
                                } else {
                                    e.printStackTrace();
                                    aVar.w("Protocol not supported : " + e.getMessage());
                                }
                                return null;
                            }
                            aVarF2 = aVarF;
                            z4 = z2;
                        } catch (IllegalArgumentException e6) {
                            e = e6;
                            if (e.getMessage() == null) {
                                aVarF = null;
                                z4 = false;
                                z2 = true;
                            } else {
                                aVarF = null;
                                z4 = false;
                                z2 = true;
                            }
                            if (!z4) {
                                if (e instanceof SocketTimeoutException) {
                                    aVar.w("SMB2 timeout");
                                } else {
                                    e.printStackTrace();
                                    aVar.w("Protocol not supported : " + e.getMessage());
                                }
                                return null;
                            }
                            aVarF2 = aVarF;
                            z4 = z2;
                        }
                    } catch (ax.R9.d | IOException | IllegalArgumentException e7) {
                        e = e7;
                        cVarJ = null;
                    }
                    if (aVarF2 == null && aVarF2.c0() != null && (gVarA = aVarF2.c0().a()) != null && ((!gVarA.k() && "SMB3".equals(str)) || (!aVar.A(gVarA) && "SMB2".equals(str)))) {
                        try {
                            aVarF2.close();
                        } catch (Exception unused7) {
                        }
                        aVar.w("Protocol does not match (" + str + " != " + gVarA + ")");
                        return null;
                    }
                    aVarF3 = aVarF2;
                    cVar = c.SMB2AND3;
                    z2 = z4;
                } else {
                    if (!ax.p006a3.Q.i() || TextUtils.isEmpty(str4)) {
                        cVar = c.SMB1;
                    } else {
                        try {
                            cVarJ = O.j(str, true, true);
                            try {
                                aVarF3 = O.f(cVarJ, str4, aVar.l);
                                try {
                                    cVar = c.SMB2AND3;
                                } catch (ax.R9.d e8) {
                                    e = e8;
                                    if (e.getMessage() == null && e.getMessage().contains("EOF while reading packet")) {
                                        try {
                                            cVarJ = O.j(str, false, true);
                                            try {
                                                aVarF3 = O.f(cVarJ, str4, aVar.l);
                                            } catch (ax.R9.d | IOException | IllegalArgumentException unused8) {
                                                z4 = false;
                                                z2 = z4;
                                                z4 = false;
                                                if (z4) {
                                                    cVar = c.SMB2AND3;
                                                } else {
                                                    e.printStackTrace();
                                                    cVar = c.SMB1;
                                                }
                                                if (cVar == c.SMB1) {
                                                    N.r(bVar, aVar.l);
                                                }
                                                if (z) {
                                                    cVar2 = cVar;
                                                    pH = aVar.F(cVar2, cVarJ, true, bVar, str4);
                                                    p = pH;
                                                    z3 = true;
                                                } else {
                                                    cVar2 = cVar;
                                                    pH = aVar.H(cVar2, cVarJ, true, bVar, str4, str2);
                                                    p = pH;
                                                    z3 = true;
                                                }
                                                if (aVarF3 != null) {
                                                    aVarF3.close();
                                                }
                                                if (p != null) {
                                                    p.g = z2;
                                                    p.h = str;
                                                    p.i = z3;
                                                }
                                                return p;
                                            }
                                        } catch (ax.R9.d | IOException | IllegalArgumentException unused9) {
                                        }
                                    } else {
                                        z4 = false;
                                        z2 = true;
                                    }
                                    if (z4) {
                                        cVar = c.SMB2AND3;
                                    } else {
                                        e.printStackTrace();
                                        cVar = c.SMB1;
                                    }
                                } catch (IOException e9) {
                                    e = e9;
                                    if (e.getMessage() == null) {
                                        z4 = false;
                                        z2 = true;
                                    } else {
                                        z4 = false;
                                        z2 = true;
                                    }
                                    if (z4) {
                                        cVar = c.SMB2AND3;
                                    } else {
                                        e.printStackTrace();
                                        cVar = c.SMB1;
                                    }
                                } catch (IllegalArgumentException e10) {
                                    e = e10;
                                    if (e.getMessage() == null) {
                                        z4 = false;
                                        z2 = true;
                                    } else {
                                        z4 = false;
                                        z2 = true;
                                    }
                                    if (z4) {
                                        cVar = c.SMB2AND3;
                                    } else {
                                        e.printStackTrace();
                                        cVar = c.SMB1;
                                    }
                                }
                            } catch (ax.R9.d | IOException | IllegalArgumentException e11) {
                                e = e11;
                                aVarF3 = null;
                            }
                        } catch (ax.R9.d | IOException | IllegalArgumentException e12) {
                            e = e12;
                            aVarF3 = null;
                            cVarJ = null;
                        }
                    }
                    z2 = true;
                }
                if (cVar == c.SMB1) {
                    N.r(bVar, aVar.l);
                }
                if (z) {
                    cVar2 = cVar;
                    pH = aVar.F(cVar2, cVarJ, true, bVar, str4);
                    p = pH;
                    z3 = true;
                } else {
                    cVar2 = cVar;
                    pH = aVar.H(cVar2, cVarJ, true, bVar, str4, str2);
                    p = pH;
                    z3 = true;
                }
                if (aVarF3 != null) {
                    aVarF3.close();
                }
                if (p != null) {
                    p.g = z2;
                    p.h = str;
                    p.i = z3;
                }
                return p;
            }
            cVar = c.SMB1;
            aVarF3 = null;
            cVarJ = null;
            z2 = true;
            if (cVar == c.SMB1) {
                N.r(bVar, aVar.l);
            }
            if (z) {
                cVar2 = cVar;
                pH = aVar.F(cVar2, cVarJ, true, bVar, str4);
                p = pH;
                z3 = true;
            } else {
                cVar2 = cVar;
                pH = aVar.H(cVar2, cVarJ, true, bVar, str4, str2);
                p = pH;
                z3 = true;
            }
            if (aVarF3 != null) {
                aVarF3.close();
            }
            if (p != null) {
                p.g = z2;
                p.h = str;
                p.i = z3;
            }
            return p;
        }

        public boolean A(ax.p380x9.g gVar) {
            return gVar == ax.p380x9.g.SMB_2_1 || gVar == ax.p380x9.g.SMB_2_0_2 || gVar == ax.p380x9.g.SMB_2XX;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.o != null) {
                if (!bool.booleanValue()) {
                    this.o.d0(false, this.s);
                    return;
                }
                if (!TextUtils.isEmpty(this.q)) {
                    this.q = d0.U(this.q);
                }
                this.o.d0(true, this.q);
            }
        }

        @Override // ax.p406z3.q
        protected void r() {
            InterfaceC6228c.a aVar = this.o;
            if (aVar != null) {
                aVar.J();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:104:0x018c  */
        /* JADX WARN: Code duplicated, block: B:116:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:118:0x00b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:120:? A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:12:0x0029  */
        /* JADX WARN: Code duplicated, block: B:59:0x00b9 A[Catch: all -> 0x0024, TRY_ENTER, TryCatch #1 {all -> 0x0024, blocks: (B:7:0x001a, B:16:0x002f, B:19:0x0036, B:20:0x0040, B:22:0x004a, B:24:0x004e, B:26:0x0054, B:45:0x0099, B:55:0x00b0, B:59:0x00b9, B:61:0x00c2, B:49:0x00a3, B:51:0x00a6, B:29:0x005c, B:38:0x006e), top: B:108:0x001a }] */
        /* JADX WARN: Code duplicated, block: B:61:0x00c2 A[Catch: all -> 0x0024, TRY_LEAVE, TryCatch #1 {all -> 0x0024, blocks: (B:7:0x001a, B:16:0x002f, B:19:0x0036, B:20:0x0040, B:22:0x004a, B:24:0x004e, B:26:0x0054, B:45:0x0099, B:55:0x00b0, B:59:0x00b9, B:61:0x00c2, B:49:0x00a3, B:51:0x00a6, B:29:0x005c, B:38:0x006e), top: B:108:0x001a }] */
        /* JADX WARN: Code duplicated, block: B:63:0x00cb  */
        /* JADX WARN: Code duplicated, block: B:66:0x00d2 A[Catch: all -> 0x00dc, TRY_LEAVE, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:69:0x00d8  */
        /* JADX WARN: Code duplicated, block: B:73:0x00e0 A[Catch: all -> 0x00dc, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:78:0x00ee A[Catch: all -> 0x00dc, Exception -> 0x0112, TRY_ENTER, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:80:0x0114 A[Catch: all -> 0x00dc, Exception -> 0x0112, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:85:0x0139 A[Catch: all -> 0x00dc, TRY_ENTER, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:87:0x013f A[Catch: all -> 0x00dc, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:90:0x0146 A[Catch: all -> 0x00dc, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:91:0x015d A[Catch: all -> 0x00dc, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:94:0x0170 A[Catch: all -> 0x00dc, TryCatch #4 {all -> 0x00dc, blocks: (B:66:0x00d2, B:73:0x00e0, B:75:0x00e6, B:78:0x00ee, B:81:0x012d, B:85:0x0139, B:87:0x013f, B:80:0x0114, B:88:0x0142, B:90:0x0146, B:92:0x016c, B:94:0x0170, B:95:0x017a, B:91:0x015d, B:64:0x00cc), top: B:113:0x00cc }] */
        /* JADX WARN: Code duplicated, block: B:98:0x0180  */
        /* JADX WARN: Instruction removed from duplicated block: B:78:0x00ee, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:80:0x0114, please report this as an issue */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) throws Throwable {
            a aVar;
            Throwable th;
            Q q;
            boolean z;
            ax.Xb.b bVarD;
            String str;
            String str2;
            String str3;
            ax.Xb.b bVar;
            String str4;
            P pX;
            C1927b0 c1927b0;
            int i;
            Q q2;
            Q q3;
            Q q4;
            Q q5 = this.p;
            if (q5 != null) {
                q5.i();
            }
            try {
                ax.Xb.a.j("jcifs.smb.client.disablePlainTextPasswords", "false");
                if ("anonymous".equals(this.m)) {
                    try {
                        if (TextUtils.isEmpty(this.n)) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        aVar = this;
                        q = aVar.p;
                        if (q != null) {
                            throw th;
                        }
                        q.j();
                        throw th;
                    }
                } else {
                    z = false;
                }
                Q q6 = this.p;
                Boolean boolValueOf = null;
                String strD0 = q6 != null ? q6.d0() : null;
                try {
                    bVarD = C(this.p, this.k, this.j);
                    try {
                        String strF = bVarD.f();
                        if (TextUtils.isEmpty(strF)) {
                            if (!TextUtils.isEmpty(strF)) {
                            }
                            str = strD0;
                            if (!TextUtils.isEmpty(str) && ax.p406z3.B.I(str)) {
                                try {
                                    bVarD = ax.Xb.b.d(str);
                                } catch (UnknownHostException unused) {
                                }
                                str2 = str;
                            } else {
                                str3 = this.k;
                                str2 = str3;
                            }
                            if (bVarD == null) {
                                try {
                                    bVarD = ax.Xb.b.d(str);
                                } catch (UnknownHostException unused2) {
                                }
                            }
                            bVar = bVarD;
                            str4 = this.r;
                            if (str4 == null) {
                                pX = x("SMB2", str2, str, bVar, z);
                                if (pX == null) {
                                    pX = x(Q.n, str2, str, bVar, z);
                                }
                                aVar = this;
                            } else {
                                aVar = this;
                                try {
                                    pX = aVar.x(str4, str2, str, bVar, z);
                                } catch (Throwable th3) {
                                    th = th3;
                                    th = th;
                                    q = aVar.p;
                                    if (q != null) {
                                        throw th;
                                    }
                                    q.j();
                                    throw th;
                                }
                            }
                            if (pX == null) {
                                Boolean bool = Boolean.FALSE;
                                q4 = aVar.p;
                                if (q4 != null) {
                                    q4.j();
                                }
                                return bool;
                            }
                            if (pX.a == c.SMB1) {
                                try {
                                    if (aVar.l > 0) {
                                        c1927b0 = new C1927b0("smb://" + str2 + ":" + aVar.l + "/", pX.c);
                                    } else {
                                        c1927b0 = new C1927b0("smb://" + str2 + "/", pX.c);
                                    }
                                    boolValueOf = Boolean.valueOf(c1927b0.s());
                                } catch (Exception unused3) {
                                    str2 = null;
                                }
                                if (boolValueOf != null || !boolValueOf.booleanValue()) {
                                    str2 = aVar.k;
                                }
                            }
                            i = aVar.l;
                            if (i > 0) {
                                pX.a(String.format(Locale.US, "smb://%s:%s", str2, Integer.valueOf(i)));
                            } else {
                                pX.a(String.format(Locale.US, "smb://%s", str2));
                            }
                            q2 = aVar.p;
                            if (q2 != null) {
                                q2.o0(pX);
                                aVar.p.m0(aVar.q);
                            }
                            Boolean bool2 = Boolean.TRUE;
                            q3 = aVar.p;
                            if (q3 != null) {
                                q3.j();
                            }
                            return bool2;
                        }
                        if (this.p != null && !strF.equals(strD0)) {
                            this.p.n0(strF);
                        }
                        strD0 = strF;
                    } catch (UnknownHostException e) {
                        e = e;
                        if (TextUtils.isEmpty(strD0)) {
                            e.printStackTrace();
                            w("Unknown Host : " + e.getMessage());
                            Boolean bool3 = Boolean.FALSE;
                            Q q7 = this.p;
                            if (q7 != null) {
                                q7.j();
                            }
                            return bool3;
                        }
                    }
                } catch (UnknownHostException e2) {
                    e = e2;
                    bVarD = null;
                }
                str = strD0;
                if (!TextUtils.isEmpty(str)) {
                    bVarD = ax.Xb.b.d(str);
                    str2 = str;
                    if (bVarD == null) {
                        bVarD = ax.Xb.b.d(str);
                    }
                    bVar = bVarD;
                    str4 = this.r;
                    if (str4 == null) {
                        pX = x("SMB2", str2, str, bVar, z);
                        if (pX == null) {
                            pX = x(Q.n, str2, str, bVar, z);
                        }
                        aVar = this;
                    } else {
                        aVar = this;
                        pX = aVar.x(str4, str2, str, bVar, z);
                    }
                    if (pX == null) {
                        Boolean bool4 = Boolean.FALSE;
                        q4 = aVar.p;
                        if (q4 != null) {
                            q4.j();
                        }
                        return bool4;
                    }
                    if (pX.a == c.SMB1) {
                        if (aVar.l > 0) {
                            c1927b0 = new C1927b0("smb://" + str2 + ":" + aVar.l + "/", pX.c);
                        } else {
                            c1927b0 = new C1927b0("smb://" + str2 + "/", pX.c);
                        }
                        boolValueOf = Boolean.valueOf(c1927b0.s());
                        if (boolValueOf != null) {
                            str2 = aVar.k;
                        } else {
                            str2 = aVar.k;
                        }
                    }
                    i = aVar.l;
                    if (i > 0) {
                        pX.a(String.format(Locale.US, "smb://%s:%s", str2, Integer.valueOf(i)));
                    } else {
                        pX.a(String.format(Locale.US, "smb://%s", str2));
                    }
                    q2 = aVar.p;
                    if (q2 != null) {
                        q2.o0(pX);
                        aVar.p.m0(aVar.q);
                    }
                    Boolean bool5 = Boolean.TRUE;
                    q3 = aVar.p;
                    if (q3 != null) {
                        q3.j();
                    }
                    return bool5;
                }
                str3 = this.k;
                str2 = str3;
                if (bVarD == null) {
                    bVarD = ax.Xb.b.d(str);
                }
                bVar = bVarD;
                str4 = this.r;
                if (str4 == null) {
                    pX = x("SMB2", str2, str, bVar, z);
                    if (pX == null) {
                        pX = x(Q.n, str2, str, bVar, z);
                    }
                    aVar = this;
                } else {
                    aVar = this;
                    pX = aVar.x(str4, str2, str, bVar, z);
                }
                if (pX == null) {
                    Boolean bool6 = Boolean.FALSE;
                    q4 = aVar.p;
                    if (q4 != null) {
                        q4.j();
                    }
                    return bool6;
                }
                if (pX.a == c.SMB1) {
                    if (aVar.l > 0) {
                        c1927b0 = new C1927b0("smb://" + str2 + ":" + aVar.l + "/", pX.c);
                    } else {
                        c1927b0 = new C1927b0("smb://" + str2 + "/", pX.c);
                    }
                    boolValueOf = Boolean.valueOf(c1927b0.s());
                    if (boolValueOf != null) {
                        str2 = aVar.k;
                    } else {
                        str2 = aVar.k;
                    }
                }
                i = aVar.l;
                if (i > 0) {
                    pX.a(String.format(Locale.US, "smb://%s:%s", str2, Integer.valueOf(i)));
                } else {
                    pX.a(String.format(Locale.US, "smb://%s", str2));
                }
                q2 = aVar.p;
                if (q2 != null) {
                    q2.o0(pX);
                    aVar.p.m0(aVar.q);
                }
                Boolean bool7 = Boolean.TRUE;
                q3 = aVar.p;
                if (q3 != null) {
                    q3.j();
                }
                return bool7;
            } catch (Throwable th4) {
                th = th4;
                aVar = this;
            }
        }

        boolean z(int i) {
            try {
                InetAddress byAddress = InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
                return byAddress.isSiteLocalAddress() || byAddress.isAnyLocalAddress() || byAddress.isLoopbackAddress();
            } catch (UnknownHostException unused) {
                return false;
            }
        }
    }

    public static class b extends g0 {
        Context a;
        c0 b = new c0(ax.U2.f.O0);

        class a implements InterfaceC6228c.a {
            final /* synthetic */ int X;
            final /* synthetic */ ax.p038c3.o Y;
            final /* synthetic */ ax.p182l3.k q;

            a(ax.p182l3.k kVar, int i, ax.p038c3.o oVar) {
                this.q = kVar;
                this.X = i;
                this.Y = oVar;
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void J() {
                this.q.a(ax.U2.f.O0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void d0(boolean z, Object obj) {
                if (!z) {
                    this.q.c(ax.U2.f.O0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof String ? (String) obj : null);
                    return;
                }
                b.this.o(this.X, this.Y);
                ax.U2.f fVar = ax.U2.f.O0;
                C6240o c6240oD = C2017x.d(fVar, this.X);
                if (c6240oD.a()) {
                    ((Q) c6240oD.u()).k0(false);
                    ((Q) c6240oD.u()).Y();
                    ax.p038c3.b.k().s(c6240oD.B(), c6240oD.D());
                }
                this.q.d(fVar, this.X);
            }
        }

        b(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("SMBPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("domain_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("created_" + i).remove("sortindex_" + i).remove("protocol_" + i).remove("lastResolvedIp_" + i).remove("hostConcatDomain_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            return new ax.p038c3.s(ax.U2.f.O0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), sharedPreferences.getString("initialPath_" + i, null), sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("SMBPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.p086f3.g0
        public ax.p038c3.o k(int i) {
            ax.p038c3.o oVar = new ax.p038c3.o();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            oVar.r(sharedPreferences.getString("domain_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            oVar.s(string);
            oVar.z(sharedPreferences.getInt("port_" + i, 0));
            oVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            oVar.y(this.b.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            oVar.v(sharedPreferences.getString("initialPath_" + i, null));
            oVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            oVar.B(sharedPreferences.getString("protocol_" + i, Q.n));
            return oVar;
        }

        @Override // ax.p086f3.g0
        public void l(int i, ax.p038c3.o oVar, ax.p182l3.k kVar, boolean z) {
            if (i == -100) {
                i = m();
            }
            if (z) {
                new a(this.a, oVar, new a(kVar, i, oVar)).i(new String[0]);
            } else {
                o(i, oVar);
                kVar.d(ax.U2.f.O0, i);
            }
        }

        int m() {
            return this.a.getSharedPreferences("SMBPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> n() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("host_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void o(int i, ax.p038c3.o oVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("domain_" + i, oVar.c()).putString("host_" + i, oVar.d()).putString("username_" + i, oVar.k()).putString("password_" + i, this.b.e(oVar.d(), oVar.g())).putString("initialPath_" + i, oVar.f()).putString("protocol_" + i, oVar.j()).putString("name_" + i, oVar.b());
            if (oVar.h() > 0) {
                editorEdit.putInt("port_" + i, oVar.h());
            } else {
                editorEdit.remove("port_" + i);
            }
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }
    }

    public enum c {
        SMB1,
        SMB1AND2,
        SMB2AND3
    }

    private C1966j X(String str, IOException iOException) {
        Throwable cause = iOException.getCause();
        while (cause != null) {
            if (cause instanceof TimeoutException) {
                return new ax.p070e3.q(str, iOException);
            }
            cause = cause != cause.getCause() ? cause.getCause() : null;
        }
        return C1960d.b(str, iOException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        O o2 = this.m;
        if (o2 != null) {
            o2.l();
        }
    }

    public static b b0(Context context) {
        if (p == null) {
            p = new b(context.getApplicationContext());
        }
        return p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d0() {
        return p().getSharedPreferences("SMBPrefs", 0).getString("lastResolvedIp_" + t(), null);
    }

    private static String e0(String str) {
        int iIndexOf = str.indexOf("/", 1);
        return iIndexOf == -1 ? str.substring(1) : str.substring(1, iIndexOf);
    }

    static String h0(String str, String str2) {
        return str + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i0() {
        return p().getSharedPreferences("SMBPrefs", 0).getBoolean("hostConcatDomain_" + t(), false);
    }

    public static boolean j0(String str) {
        return !"/".equals(str) && str.startsWith("/") && str.indexOf("/", 1) < 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l0(boolean z) {
        SharedPreferences.Editor editorEdit = p().getSharedPreferences("SMBPrefs", 0).edit();
        editorEdit.putBoolean("hostConcatDomain_" + t(), z);
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n0(String str) {
        SharedPreferences.Editor editorEdit = p().getSharedPreferences("SMBPrefs", 0).edit();
        editorEdit.putString("lastResolvedIp_" + t(), str);
        editorEdit.apply();
    }

    private boolean p0() {
        c cVar = this.h.a;
        return cVar == c.SMB1 || cVar == c.SMB1AND2;
    }

    private boolean q0() {
        c cVar = this.h.a;
        return cVar == c.SMB2AND3 || cVar == c.SMB1AND2;
    }

    /* JADX WARN: Code duplicated, block: B:64:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:? A[SYNTHETIC] */
    private void r0(AbstractC6239n abstractC6239n, ax.p086f3.G g, long j, Long l, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        Throwable th;
        InputStream inputStreamB;
        IOException iOException;
        OutputStream outputStream = null;
        try {
            OutputStream outputStreamH = p0() ? this.l.h(abstractC6239n, z) : this.m.q(abstractC6239n, z);
            try {
                inputStreamB = g.b();
                try {
                    ax.p086f3.F.c(inputStreamB, outputStreamH, j, interfaceC2462c, iVar);
                    if (outputStreamH != null) {
                        try {
                            outputStreamH.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStreamB != null) {
                        try {
                            inputStreamB.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (l != null) {
                        d(abstractC6239n, l.longValue());
                    }
                } catch (IOException e2) {
                    iOException = e2;
                    outputStream = outputStreamH;
                    try {
                        if (iOException instanceof C1925a0) {
                            C1966j c1966jA = N.a("smb1 writeFile", (C1925a0) iOException);
                            if (!C1966j.class.equals(c1966jA.getClass())) {
                                throw c1966jA;
                            }
                        }
                        if (!p0() && (iOException.getCause() instanceof ax.p380x9.F)) {
                            throw O.g(iOException);
                        }
                        throw X("smb write file", iOException);
                    } catch (Throwable th2) {
                        th = th2;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (inputStreamB != null) {
                            throw th;
                        }
                        try {
                            inputStreamB.close();
                            throw th;
                        } catch (IOException unused2) {
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = outputStreamH;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStreamB != null) {
                        throw th;
                    }
                    inputStreamB.close();
                    throw th;
                }
            } catch (IOException e4) {
                iOException = e4;
                inputStreamB = null;
            } catch (Throwable th4) {
                th = th4;
                inputStreamB = null;
            }
        } catch (IOException e5) {
            iOException = e5;
            inputStreamB = null;
        } catch (Throwable th5) {
            th = th5;
            inputStreamB = null;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void E(Context context, ax.p086f3.K k) {
        super.E(context, k);
        this.l = new N(this);
        if (ax.p006a3.Q.i()) {
            this.m = new O(this);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean I(AbstractC6239n abstractC6239n) {
        return (abstractC6239n == null || d0.E(abstractC6239n)) ? false : true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean K() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public ax.p262q3.a M(String str, int i) throws C1966j {
        if (!a()) {
            throw new C1964h("Not connected to server");
        }
        if (!p0()) {
            return this.m.D(str, i);
        }
        C2461b.g("SMB1 does not support proxy file :" + this.h.a + "," + d0.f(str));
        throw new ax.p070e3.t("SMB1 does not support proxy file.");
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean O() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean P() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        r0(abstractC6239n, g, j, l, true, interfaceC2462c, iVar);
    }

    String Z() {
        return this.h.d;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.i;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public S a1(String str) throws C1966j {
        S s;
        if (str == null) {
            ax.p302sb.c.h().d("SMBGFI!!!:").k().h("smbtype:" + this.h.a.name()).i();
        }
        boolean zEquals = str.equals(this.j);
        if (zEquals && (s = this.k) != null) {
            return s;
        }
        if (f0() != null) {
            S sE = p0() ? this.l.e(str) : this.m.o(str);
            if (zEquals) {
                this.k = sE;
            }
            return sE;
        }
        String strF = d0.f(str);
        ax.p302sb.c.h().g().b("NOT CONNECT CALL GET FILE INFO").k().h("ext:" + strF).i();
        throw new C1964h("Not connected to server");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        k0(false);
        Y();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        return w(str, str2);
    }

    public k0 c0(AbstractC6239n abstractC6239n) throws C1966j {
        return p0() ? this.l.i(abstractC6239n) : this.m.s(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // ax.p086f3.i0
    public boolean d(AbstractC6239n abstractC6239n, long j) {
        if (j < 0 || !(abstractC6239n instanceof S)) {
            return false;
        }
        try {
            if (p0()) {
                this.l.p(abstractC6239n, j);
                return true;
            }
            this.m.E(abstractC6239n, j);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        C2461b.c(abstractC6239n.isDirectory());
        return p0() ? this.l.n(abstractC6239n) : this.m.w(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    P f0() {
        return this.h;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (H(abstractC6239n)) {
            return AbstractC6238m.A(abstractC6239n);
        }
        return null;
    }

    String g0(String str) {
        return h0(Z(), str);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        C2461b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        if (f0() == null) {
            throw new C1964h("Not connected to server");
        }
        if (!q0()) {
            return this.l.f(abstractC6239n, j);
        }
        try {
            return this.m.p(abstractC6239n, j);
        } catch (C1966j e) {
            if (p0()) {
                return this.l.f(abstractC6239n, j);
            }
            throw e;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        long jP = abstractC6239n.p();
        if (p0()) {
            this.l.o(abstractC6239n, abstractC6239n2);
        } else {
            this.m.x(abstractC6239n, abstractC6239n2);
        }
        if (iVar != null) {
            iVar.a(jP, jP);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        try {
            new a(p(), this, aVar, t()).i(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            if (aVar != null) {
                aVar.J();
                aVar.d0(false, e.getMessage());
            }
        }
    }

    void k0(boolean z) {
        this.i = z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        C2461b.a(abstractC6239n.o());
        r0(abstractC6239n, g, j, l, false, interfaceC2462c, iVar);
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x000c */
    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    synchronized void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        try {
            o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
        } catch (Throwable th) {
            th = th;
            while (true) {
                throw th;
            }
        }
    }

    public void m0(String str) {
        if (TextUtils.isEmpty(str)) {
            this.j = x();
            this.k = null;
        } else {
            this.j = str;
            this.k = null;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        l1(abstractC6239n2, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        return p0() ? this.l.c(abstractC6239n) : this.m.i(abstractC6239n);
    }

    void o0(P p2) {
        k0(true);
        this.h = p2;
        c cVar = p2.a;
        if (cVar == c.SMB1 || cVar == c.SMB1AND2) {
            this.l.q(p2.c, p2.d);
        }
        O o2 = this.m;
        if (o2 != null) {
            o2.F(p2.h, p2.g, p2.i);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return p0() ? this.l.b(abstractC6239n) : this.m.h(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        if (p0()) {
            this.l.d(abstractC6239n);
        } else {
            this.m.k(abstractC6239n);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public String r() {
        return this.j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        ax.U2.f fVarQ = abstractC6239n.Q();
        ax.U2.f fVar = ax.U2.f.O0;
        if (fVarQ == fVar && abstractC6239n2.Q() == fVar) {
            String strU = abstractC6239n.U();
            String strU2 = abstractC6239n2.U();
            if (!"/".equals(strU) && !"/".equals(strU2)) {
                return e0(strU).equals(e0(strU2));
            }
        }
        return false;
    }
}
