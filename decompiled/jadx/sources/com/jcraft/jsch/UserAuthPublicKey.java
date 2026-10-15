package com.jcraft.jsch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
class UserAuthPublicKey extends UserAuth {
    UserAuthPublicKey() {
    }

    private boolean b(Session session, List<Identity> list, List<String> list2, List<String> list3) throws Exception {
        ArrayList arrayList;
        boolean z;
        boolean z2;
        ArrayList arrayList2;
        byte[] bArr;
        ArrayList arrayList3;
        int iH;
        ArrayList arrayList4;
        ArrayList arrayList5;
        List<String> list4 = list3;
        boolean z3 = false;
        if (session.f0 >= session.e0) {
            return false;
        }
        boolean zEquals = session.w("enable_pubkey_auth_query").equals("yes");
        boolean zEquals2 = session.w("try_additional_pubkey_algorithms").equals("yes");
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        for (String str : list2) {
            if (str.equals("ssh-rsa") || str.equals("rsa-sha2-256") || str.equals("rsa-sha2-512") || str.equals("ssh-rsa-sha224@ssh.com") || str.equals("ssh-rsa-sha256@ssh.com") || str.equals("ssh-rsa-sha384@ssh.com") || str.equals("ssh-rsa-sha512@ssh.com")) {
                arrayList6.add(str);
            } else {
                arrayList7.add(str);
            }
        }
        byte[] bArrV = Util.v(this.d);
        for (Identity identity : list) {
            if (session.f0 >= session.e0) {
                return z3;
            }
            c(session, identity);
            String strB = identity.b();
            if (strB.equals("ssh-rsa")) {
                arrayList = new ArrayList(arrayList6);
            } else if (arrayList7.contains(strB)) {
                arrayList = new ArrayList(1);
                arrayList.add(strB);
            } else {
                arrayList = null;
            }
            if (arrayList == null || arrayList.isEmpty()) {
                z = zEquals;
                z2 = zEquals2;
                arrayList2 = arrayList6;
                arrayList7 = arrayList7;
                bArr = bArrV;
                if (session.A().isEnabled(0)) {
                    session.A().a(0, strB + " cannot be used as public key type for identity " + identity.getName());
                }
            } else {
                while (true) {
                    if (arrayList.isEmpty() || session.f0 >= session.e0) {
                        z = zEquals;
                        z2 = zEquals2;
                        arrayList2 = arrayList6;
                        arrayList7 = arrayList7;
                        bArr = bArrV;
                    } else {
                        byte[] bArrD = identity.d();
                        if (bArrD == null || !zEquals) {
                            z = zEquals;
                            z2 = zEquals2;
                            arrayList2 = arrayList6;
                            arrayList7 = arrayList7;
                            arrayList3 = null;
                        } else {
                            Iterator it = arrayList.iterator();
                            int i = 51;
                            while (true) {
                                if (!it.hasNext()) {
                                    z = zEquals;
                                    z2 = zEquals2;
                                    arrayList2 = arrayList6;
                                    arrayList7 = arrayList7;
                                    iH = i;
                                    arrayList3 = null;
                                    break;
                                }
                                String str2 = (String) it.next();
                                it.remove();
                                if (list4.contains(str2)) {
                                    z = zEquals;
                                    if (!(identity instanceof AgentIdentity)) {
                                        boolean z4 = zEquals2;
                                        if (session.A().isEnabled(0)) {
                                            session.A().a(0, str2 + " not available for identity " + identity.getName());
                                        }
                                        zEquals = z;
                                        zEquals2 = z4;
                                    }
                                } else {
                                    z = zEquals;
                                }
                                z2 = zEquals2;
                                this.b.c();
                                this.c.s((byte) 50);
                                this.c.y(bArrV);
                                this.c.y(Util.v("ssh-connection"));
                                this.c.y(Util.v("publickey"));
                                this.c.s((byte) 0);
                                this.c.y(Util.v(str2));
                                this.c.y(bArrD);
                                session.m0(this.b);
                                while (true) {
                                    Buffer bufferM = session.M(this.c);
                                    this.c = bufferM;
                                    iH = bufferM.h() & 255;
                                    if (iH == 60) {
                                        arrayList2 = arrayList6;
                                        if (session.A().isEnabled(0)) {
                                            session.A().a(0, str2 + " preauth success");
                                        }
                                        arrayList3 = new ArrayList(1);
                                        arrayList3.add(str2);
                                        break;
                                    }
                                    arrayList4 = arrayList6;
                                    arrayList5 = arrayList7;
                                    if (iH == 51) {
                                        if (!session.A().isEnabled(0)) {
                                            break;
                                        }
                                        session.A().a(0, str2 + " preauth failure");
                                        break;
                                    }
                                    if (iH != 53) {
                                        if (!session.A().isEnabled(0)) {
                                            break;
                                        }
                                        session.A().a(0, str2 + " preauth failure command (" + iH + ")");
                                        break;
                                    }
                                    this.c.i();
                                    this.c.c();
                                    this.c.c();
                                    byte[] bArrP = this.c.p();
                                    this.c.p();
                                    String strC = Util.c(bArrP);
                                    UserInfo userInfo = this.a;
                                    if (userInfo != null) {
                                        userInfo.d(strC);
                                    }
                                    arrayList6 = arrayList4;
                                    arrayList7 = arrayList5;
                                }
                                i = iH;
                                zEquals = z;
                                zEquals2 = z2;
                                arrayList6 = arrayList4;
                                arrayList7 = arrayList5;
                            }
                            if (iH != 60) {
                            }
                            zEquals = z;
                        }
                        if (!identity.c()) {
                            if (bArrD == null) {
                                bArrD = identity.d();
                            }
                            if (bArrD != null) {
                                if (arrayList3 == null) {
                                    arrayList3 = arrayList;
                                }
                                if (!arrayList3.isEmpty()) {
                                    Iterator it2 = arrayList3.iterator();
                                    while (true) {
                                        if (!it2.hasNext() || session.f0 >= session.e0) {
                                            list4 = list3;
                                            zEquals = z;
                                            bArrV = bArrV;
                                            zEquals2 = z2;
                                            arrayList6 = arrayList2;
                                            arrayList7 = arrayList7;
                                        } else {
                                            String str3 = (String) it2.next();
                                            it2.remove();
                                            if (!list4.contains(str3) || (identity instanceof AgentIdentity)) {
                                                this.b.c();
                                                this.c.s((byte) 50);
                                                this.c.y(bArrV);
                                                this.c.y(Util.v("ssh-connection"));
                                                this.c.y(Util.v("publickey"));
                                                this.c.s((byte) 1);
                                                this.c.y(Util.v(str3));
                                                this.c.y(bArrD);
                                                byte[] bArrC = session.C();
                                                int length = bArrC.length;
                                                int i2 = length + 4;
                                                byte[] bArr2 = new byte[(this.c.c + i2) - 5];
                                                Iterator it3 = it2;
                                                bArr2[0] = (byte) (length >>> 24);
                                                bArr2[1] = (byte) (length >>> 16);
                                                bArr2[2] = (byte) (length >>> 8);
                                                bArr2[3] = (byte) length;
                                                bArr = bArrV;
                                                System.arraycopy(bArrC, 0, bArr2, 4, length);
                                                Buffer buffer = this.c;
                                                System.arraycopy(buffer.b, 5, bArr2, i2, buffer.c - 5);
                                                byte[] bArrA = identity.a(bArr2, str3);
                                                if (bArrA != null) {
                                                    this.c.y(bArrA);
                                                    session.m0(this.b);
                                                    while (true) {
                                                        Buffer bufferM2 = session.M(this.c);
                                                        this.c = bufferM2;
                                                        int iH2 = bufferM2.h() & 255;
                                                        if (iH2 == 52) {
                                                            if (!session.A().isEnabled(0)) {
                                                                return true;
                                                            }
                                                            session.A().a(0, str3 + " auth success");
                                                            return true;
                                                        }
                                                        if (iH2 != 53) {
                                                            if (iH2 != 51) {
                                                                if (!session.A().isEnabled(0)) {
                                                                    break;
                                                                }
                                                                session.A().a(0, str3 + " auth failure command (" + iH2 + ")");
                                                                break;
                                                            }
                                                            this.c.i();
                                                            this.c.c();
                                                            this.c.c();
                                                            byte[] bArrP2 = this.c.p();
                                                            if (this.c.c() != 0) {
                                                                throw new JSchPartialAuthException(Util.c(bArrP2));
                                                            }
                                                            session.f0++;
                                                            if (session.A().isEnabled(0)) {
                                                                session.A().a(0, str3 + " auth failure");
                                                            }
                                                            if (session.f0 < session.e0) {
                                                                if (!z2) {
                                                                    break;
                                                                }
                                                                break;
                                                            }
                                                            return false;
                                                        }
                                                        this.c.i();
                                                        this.c.c();
                                                        this.c.c();
                                                        byte[] bArrP3 = this.c.p();
                                                        this.c.p();
                                                        String strC2 = Util.c(bArrP3);
                                                        UserInfo userInfo2 = this.a;
                                                        if (userInfo2 != null) {
                                                            userInfo2.d(strC2);
                                                        }
                                                    }
                                                } else if (session.A().isEnabled(0)) {
                                                    session.A().a(0, str3 + " signature failure");
                                                }
                                                list4 = list3;
                                                it2 = it3;
                                                bArrV = bArr;
                                            } else if (session.A().isEnabled(0)) {
                                                session.A().a(0, str3 + " not available for identity " + identity.getName());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        zEquals = z;
                    }
                    zEquals2 = z2;
                    arrayList6 = arrayList2;
                    arrayList7 = arrayList7;
                    z3 = false;
                }
            }
            list4 = list3;
            zEquals = z;
            bArrV = bArr;
            zEquals2 = z2;
            arrayList6 = arrayList2;
            arrayList7 = arrayList7;
            z3 = false;
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004d  */
    private void c(Session session, Identity identity) throws JSchException {
        byte[] bArr;
        byte[] bArrV;
        int i = 5;
        do {
            bArr = null;
            if (!identity.c()) {
                bArrV = null;
            } else {
                if (this.a == null) {
                    throw new JSchException("USERAUTH fail");
                }
                if (identity.c()) {
                    if (!this.a.a("Passphrase for " + identity.getName())) {
                        throw new JSchAuthCancelException("publickey");
                    }
                }
                String strB = this.a.b();
                if (strB != null) {
                    bArrV = Util.v(strB);
                } else {
                    bArrV = null;
                }
            }
            if (!(identity.c() && bArrV == null) && identity.e(bArrV)) {
                if (bArrV != null && (session.z() instanceof IdentityRepositoryWrapper)) {
                    ((IdentityRepositoryWrapper) session.z()).f();
                }
                bArr = bArrV;
                break;
            }
            Util.g(bArrV);
            i--;
        } while (i != 0);
        Util.g(bArr);
    }

    @Override // com.jcraft.jsch.UserAuth
    public boolean a(Session session) throws Exception {
        super.a(session);
        Vector<Identity> vectorA = session.z().a();
        synchronized (vectorA) {
            try {
                if (vectorA.size() <= 0) {
                    return false;
                }
                String strW = session.w("PubkeyAcceptedAlgorithms");
                if (session.A().isEnabled(0)) {
                    session.A().a(0, "PubkeyAcceptedAlgorithms = " + strW);
                }
                String[] strArrE = session.E();
                List<String> listAsList = (strArrE == null || strArrE.length <= 0) ? Collections.EMPTY_LIST : Arrays.asList(strArrE);
                if (!listAsList.isEmpty() && session.A().isEnabled(0)) {
                    session.A().a(0, "Signature algorithms unavailable for non-agent identities = " + listAsList);
                }
                List<String> listAsList2 = Arrays.asList(Util.u(strW, ","));
                if (listAsList2.isEmpty()) {
                    return false;
                }
                String[] strArrB = session.B();
                if (strArrB == null || strArrB.length <= 0) {
                    if (session.A().isEnabled(0)) {
                        session.A().a(0, "No server-sig-algs found, using PubkeyAcceptedAlgorithms = " + listAsList2);
                    }
                    String strW2 = session.w("PubkeyAcceptedAlgorithmsOldServer");
                    if (strW2 != null) {
                        List<String> listAsList3 = Arrays.asList(Util.u(strW2, ","));
                        if (!listAsList3.isEmpty()) {
                            listAsList2 = listAsList3;
                        }
                    }
                } else {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (String str : listAsList2) {
                        int length = strArrB.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                arrayList2.add(str);
                                break;
                            }
                            if (str.equals(strArrB[i])) {
                                arrayList.add(str);
                                break;
                            }
                            i++;
                        }
                    }
                    if (!arrayList.isEmpty() && session.A().isEnabled(0)) {
                        session.A().a(0, "PubkeyAcceptedAlgorithms in server-sig-algs = " + arrayList);
                    }
                    if (!arrayList2.isEmpty() && session.A().isEnabled(0)) {
                        session.A().a(0, "PubkeyAcceptedAlgorithms not in server-sig-algs = " + arrayList2);
                    }
                    if (!arrayList.isEmpty() && !arrayList2.isEmpty()) {
                        if (b(session, vectorA, arrayList, listAsList)) {
                            return true;
                        }
                        return b(session, vectorA, arrayList2, listAsList);
                    }
                }
                return b(session, vectorA, listAsList2, listAsList);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
