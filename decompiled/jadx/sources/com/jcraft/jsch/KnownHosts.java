package com.jcraft.jsch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Vector;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class KnownHosts implements HostKeyRepository {
    private static final byte[] e = {32};
    private static final byte[] f = Util.v("\n");
    private JSch a;
    private String b = null;
    private Vector<HostKey> c;
    MAC d;

    class HashedHostKey extends HostKey {
        private boolean g;
        byte[] h;
        byte[] i;

        HashedHostKey(KnownHosts knownHosts, String str, int i, byte[] bArr) throws JSchException {
            this(HttpUrl.FRAGMENT_ENCODE_SET, str, i, bArr, null);
        }

        HashedHostKey(String str, String str2, int i, byte[] bArr, String str3) throws JSchException {
            super(str, str2, i, bArr, str3);
            this.g = false;
            this.h = null;
            this.i = null;
            if (!this.b.startsWith("|1|") || this.b.substring(3).indexOf("|") <= 0) {
                return;
            }
            String strSubstring = this.b.substring(3);
            String strSubstring2 = strSubstring.substring(0, strSubstring.indexOf("|"));
            String strSubstring3 = strSubstring.substring(strSubstring.indexOf("|") + 1);
            this.h = Util.k(Util.v(strSubstring2), 0, strSubstring2.length());
            this.i = Util.k(Util.v(strSubstring3), 0, strSubstring3.length());
            int iD = KnownHosts.this.d.d();
            if (this.h.length == iD && this.i.length == iD) {
                this.g = true;
            } else {
                this.h = null;
                this.i = null;
            }
        }

        HashedHostKey(KnownHosts knownHosts, String str, byte[] bArr) throws JSchException {
            this(knownHosts, str, 0, bArr);
        }

        @Override // com.jcraft.jsch.HostKey
        boolean h(String str) {
            boolean zA;
            if (!this.g) {
                return super.h(str);
            }
            try {
                synchronized (KnownHosts.this.d) {
                    KnownHosts.this.d.a(this.h);
                    byte[] bArrV = Util.v(str);
                    KnownHosts.this.d.b(bArrV, 0, bArrV.length);
                    byte[] bArr = new byte[KnownHosts.this.d.d()];
                    KnownHosts.this.d.c(bArr, 0);
                    zA = Util.a(this.i, bArr);
                }
                return zA;
            } catch (Exception e) {
                KnownHosts.this.a.j().b(3, "an error occurred while trying to check hash for host " + str, e);
                return false;
            }
        }

        void j() {
            if (this.g) {
                return;
            }
            if (this.h == null) {
                Random random = Session.x0;
                synchronized (random) {
                    byte[] bArr = new byte[KnownHosts.this.d.d()];
                    this.h = bArr;
                    random.a(bArr, 0, bArr.length);
                }
            }
            try {
                synchronized (KnownHosts.this.d) {
                    KnownHosts.this.d.a(this.h);
                    byte[] bArrV = Util.v(this.b);
                    KnownHosts.this.d.b(bArrV, 0, bArrV.length);
                    byte[] bArr2 = new byte[KnownHosts.this.d.d()];
                    this.i = bArr2;
                    KnownHosts.this.d.c(bArr2, 0);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("|1|");
                byte[] bArr3 = this.h;
                sb.append(Util.c(Util.x(bArr3, 0, bArr3.length, true)));
                sb.append("|");
                byte[] bArr4 = this.i;
                sb.append(Util.c(Util.x(bArr4, 0, bArr4.length, true)));
                this.b = sb.toString();
                this.g = true;
            } catch (Exception e) {
                KnownHosts.this.a.j().b(3, "an error occurred while trying to calculate the hash for host " + this.b, e);
                this.h = null;
                this.i = null;
            }
        }

        boolean k() {
            return this.g;
        }
    }

    KnownHosts(JSch jSch) {
        this.c = null;
        this.a = jSch;
        m();
        this.c = new Vector<>();
    }

    private void g(String str) throws JSchException {
        this.c.addElement(new HostKey(str, -1, null));
    }

    @Override // com.jcraft.jsch.HostKeyRepository
    public String a() {
        return this.b;
    }

    @Override // com.jcraft.jsch.HostKeyRepository
    public void b(HostKey hostKey, UserInfo userInfo) {
        int i = hostKey.c;
        String strC = hostKey.c();
        synchronized (this.c) {
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                try {
                    this.c.elementAt(i2).h(strC);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.c.addElement(hostKey);
        r(userInfo);
    }

    @Override // com.jcraft.jsch.HostKeyRepository
    public int c(String str, byte[] bArr) {
        if (str == null) {
            return 1;
        }
        try {
            HostKey hostKey = new HostKey(str, 0, bArr);
            synchronized (this.c) {
                int i = 1;
                for (int i2 = 0; i2 < this.c.size(); i2++) {
                    try {
                        HostKey hostKeyElementAt = this.c.elementAt(i2);
                        if (hostKeyElementAt.h(str) && hostKeyElementAt.c == hostKey.c) {
                            if (Util.a(hostKeyElementAt.d, bArr)) {
                                return 0;
                            }
                            i = 2;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return (i == 1 && str.startsWith("[") && str.indexOf("]:") > 1) ? c(str.substring(1, str.indexOf("]:")), bArr) : i;
            }
        } catch (Exception e2) {
            this.a.j().b(0, "exception while trying to read key while checking host '" + str + "'", e2);
            return 1;
        }
    }

    @Override // com.jcraft.jsch.HostKeyRepository
    public void d(String str, String str2, byte[] bArr) {
        boolean z;
        synchronized (this.c) {
            int i = 0;
            z = false;
            while (i < this.c.size()) {
                try {
                    HostKey hostKeyElementAt = this.c.elementAt(i);
                    if (str == null || (hostKeyElementAt.h(str) && (str2 == null || (hostKeyElementAt.f().equals(str2) && (bArr == null || Util.a(bArr, hostKeyElementAt.d)))))) {
                        String strC = hostKeyElementAt.c();
                        if (str == null || strC.equals(str) || ((hostKeyElementAt instanceof HashedHostKey) && ((HashedHostKey) hostKeyElementAt).k())) {
                            this.c.removeElement(hostKeyElementAt);
                            i--;
                        } else {
                            hostKeyElementAt.b = j(strC, str);
                        }
                        z = true;
                    }
                    i++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (z) {
            try {
                p();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jcraft.jsch.HostKeyRepository
    public HostKey[] e(String str, String str2) {
        HostKey[] hostKeyArr;
        synchronized (this.c) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < this.c.size(); i++) {
                    HostKey hostKeyElementAt = this.c.elementAt(i);
                    if (hostKeyElementAt.c != -1 && (str == null || (hostKeyElementAt.h(str) && (str2 == null || hostKeyElementAt.f().equals(str2))))) {
                        arrayList.add(hostKeyElementAt);
                    }
                }
                int size = arrayList.size();
                hostKeyArr = new HostKey[size];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    hostKeyArr[i2] = (HostKey) arrayList.get(i2);
                }
                if (str != null && str.startsWith("[") && str.indexOf("]:") > 1) {
                    HostKey[] hostKeyArrE = e(str.substring(1, str.indexOf("]:")), str2);
                    if (hostKeyArrE.length > 0) {
                        HostKey[] hostKeyArr2 = new HostKey[hostKeyArrE.length + size];
                        System.arraycopy(hostKeyArr, 0, hostKeyArr2, 0, size);
                        System.arraycopy(hostKeyArrE, 0, hostKeyArr2, size, hostKeyArrE.length);
                        hostKeyArr = hostKeyArr2;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return hostKeyArr;
    }

    MAC h(String str) throws IllegalArgumentException {
        try {
            return (MAC) Class.forName(str).asSubclass(MAC.class).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e2) {
            this.a.j().b(3, "unable to instantiate HMAC-class " + str, e2);
            throw new IllegalArgumentException("instantiation of " + str + " lead to an error", e2);
        }
    }

    HostKey i(String str, byte[] bArr) throws JSchException {
        HashedHostKey hashedHostKey = new HashedHostKey(this, str, bArr);
        hashedHostKey.j();
        return hashedHostKey;
    }

    String j(String str, String str2) {
        int length = str2.length();
        int length2 = str.length();
        int i = 0;
        while (i < length2) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                break;
            }
            if (str2.equals(str.substring(i, iIndexOf))) {
                return str.substring(0, i) + str.substring(iIndexOf + 1);
            }
            i = iIndexOf + 1;
        }
        if (str.endsWith(str2) && length2 - i == length) {
            return str.substring(0, length == length2 ? 0 : (length2 - length) - 1);
        }
        return str;
    }

    void k(OutputStream outputStream) {
        try {
            synchronized (this.c) {
                for (int i = 0; i < this.c.size(); i++) {
                    try {
                        l(outputStream, this.c.elementAt(i));
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Exception e2) {
            this.a.j().b(3, "unable to dump known hosts", e2);
        }
    }

    void l(OutputStream outputStream, HostKey hostKey) throws IOException {
        String strE = hostKey.e();
        String strC = hostKey.c();
        String strF = hostKey.f();
        String strB = hostKey.b();
        if (strF.equals("UNKNOWN")) {
            outputStream.write(Util.v(strC));
            outputStream.write(f);
            return;
        }
        if (strE.length() != 0) {
            outputStream.write(Util.v(strE));
            outputStream.write(e);
        }
        outputStream.write(Util.v(strC));
        byte[] bArr = e;
        outputStream.write(bArr);
        outputStream.write(Util.v(strF));
        outputStream.write(bArr);
        outputStream.write(Util.v(hostKey.d()));
        if (strB != null) {
            outputStream.write(bArr);
            outputStream.write(Util.v(strB));
        }
        outputStream.write(f);
    }

    MAC m() throws IllegalArgumentException {
        if (this.d == null) {
            this.d = h(JSch.f("hmac-sha1"));
        }
        return this.d;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x011a  */
    /* JADX WARN: Code duplicated, block: B:110:0x0133 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x013b A[ADDED_TO_REGION, LOOP:10: B:111:0x013b->B:115:0x0143, LOOP_START, PHI: r3
      0x013b: PHI (r3v15 int) = (r3v14 int), (r3v22 int) binds: [B:109:0x0131, B:115:0x0143] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:112:0x013d A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:117:0x0148 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x014d A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:121:0x0153  */
    /* JADX WARN: Code duplicated, block: B:122:0x0154 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:124:0x0157 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:127:0x0162  */
    /* JADX WARN: Code duplicated, block: B:201:0x0156 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00d8 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x00f2 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fa A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x0103 A[ADDED_TO_REGION, LOOP:8: B:90:0x0103->B:94:0x010b, LOOP_START, PHI: r3
      0x0103: PHI (r3v12 int) = (r3v11 int), (r3v27 int) binds: [B:88:0x00f8, B:94:0x010b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:91:0x0105 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:97:0x0113 A[Catch: all -> 0x004f, TryCatch #2 {all -> 0x004f, blocks: (B:3:0x000e, B:5:0x0013, B:24:0x003b, B:30:0x0046, B:33:0x0053, B:35:0x0058, B:36:0x0061, B:38:0x0066, B:42:0x006f, B:44:0x0076, B:46:0x007c, B:50:0x0086, B:53:0x008c, B:54:0x008f, B:56:0x0099, B:58:0x009e, B:62:0x00a7, B:64:0x00ae, B:66:0x00b4, B:70:0x00bd, B:73:0x00c3, B:75:0x00ca, B:76:0x00d3, B:78:0x00d8, B:82:0x00e1, B:84:0x00e8, B:86:0x00f2, B:89:0x00fa, B:91:0x0105, B:94:0x010b, B:95:0x010e, B:97:0x0113, B:105:0x0122, B:108:0x0129, B:110:0x0133, B:112:0x013d, B:115:0x0143, B:117:0x0148, B:119:0x014d, B:124:0x0157, B:126:0x015d, B:128:0x0163, B:129:0x017f, B:17:0x002d, B:131:0x0189, B:132:0x0193), top: B:148:0x000e, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x0119  */
    void n(InputStream inputStream) throws JSchException {
        int i;
        String string;
        String string2;
        String string3;
        byte b;
        byte b2;
        int i2;
        byte b3;
        byte b4;
        int i3;
        byte b5;
        this.c.removeAllElements();
        StringBuilder sb = new StringBuilder();
        try {
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    byte[] bArr2 = bArr;
                    int i4 = 0;
                    while (true) {
                        int i5 = inputStream.read();
                        i = -1;
                        if (i5 == -1) {
                            if (i4 != 0) {
                                break;
                            }
                            inputStream.close();
                            return;
                        } else if (i5 != 13) {
                            if (i5 == 10) {
                                break;
                            }
                            if (bArr2.length <= i4) {
                                if (i4 > 10240) {
                                    break;
                                }
                                byte[] bArr3 = new byte[bArr2.length * 2];
                                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                                bArr2 = bArr3;
                            }
                            int i6 = i4 + 1;
                            bArr2[i4] = (byte) i5;
                            i4 = i6;
                        }
                    }
                    int i7 = 0;
                    while (true) {
                        if (i7 < i4) {
                            byte b6 = bArr2[i7];
                            if (b6 != 32 && b6 != 9) {
                                if (b6 == 35) {
                                    g(Util.d(bArr2, 0, i4));
                                }
                                bArr = bArr2;
                            }
                            i7++;
                        }
                        if (i7 >= i4) {
                            g(Util.d(bArr2, 0, i4));
                        } else {
                            sb.setLength(0);
                            while (i7 < i4) {
                                int i8 = i7 + 1;
                                byte b7 = bArr2[i7];
                                if (b7 != 32 && b7 != 9) {
                                    sb.append((char) b7);
                                    i7 = i8;
                                }
                                i7 = i8;
                                break;
                            }
                            String string4 = sb.toString();
                            if (i7 >= i4 || string4.length() == 0) {
                                g(Util.d(bArr2, 0, i4));
                            } else {
                                while (i7 < i4) {
                                    byte b8 = bArr2[i7];
                                    if (b8 != 32 && b8 != 9) {
                                        break;
                                    } else {
                                        i7++;
                                    }
                                }
                                String str = HttpUrl.FRAGMENT_ENCODE_SET;
                                if (string4.charAt(0) == '@') {
                                    sb.setLength(0);
                                    while (i7 < i4) {
                                        int i9 = i7 + 1;
                                        byte b9 = bArr2[i7];
                                        if (b9 != 32 && b9 != 9) {
                                            sb.append((char) b9);
                                            i7 = i9;
                                        }
                                        i7 = i9;
                                        break;
                                    }
                                    String string5 = sb.toString();
                                    if (i7 >= i4 || string5.length() == 0) {
                                        g(Util.d(bArr2, 0, i4));
                                    } else {
                                        while (i7 < i4) {
                                            byte b10 = bArr2[i7];
                                            if (b10 != 32 && b10 != 9) {
                                                break;
                                            } else {
                                                i7++;
                                            }
                                        }
                                        str = string4;
                                        string4 = string5;
                                        sb.setLength(0);
                                        while (i7 < i4) {
                                            i3 = i7 + 1;
                                            b5 = bArr2[i7];
                                            if (b5 != 32 && b5 != 9) {
                                                sb.append((char) b5);
                                                i7 = i3;
                                            }
                                            i7 = i3;
                                            break;
                                        }
                                        string = sb.toString();
                                        if (HostKey.i(string) != -1) {
                                            i = HostKey.i(string);
                                        } else {
                                            i7 = i4;
                                        }
                                        if (i7 >= i4) {
                                            g(Util.d(bArr2, 0, i4));
                                        } else {
                                            while (i7 < i4) {
                                                b4 = bArr2[i7];
                                                if (b4 == 32 && b4 != 9) {
                                                    break;
                                                } else {
                                                    i7++;
                                                }
                                            }
                                            sb.setLength(0);
                                            while (i7 < i4) {
                                                i2 = i7 + 1;
                                                b3 = bArr2[i7];
                                                if (b3 != 13) {
                                                    if (b3 != 10 && b3 != 32 && b3 != 9) {
                                                        sb.append((char) b3);
                                                    }
                                                    i7 = i2;
                                                    break;
                                                }
                                                i7 = i2;
                                            }
                                            string2 = sb.toString();
                                            if (string2.length() == 0) {
                                                g(Util.d(bArr2, 0, i4));
                                            } else {
                                                while (i7 < i4) {
                                                    b2 = bArr2[i7];
                                                    if (b2 == 32 && b2 != 9) {
                                                        break;
                                                    } else {
                                                        i7++;
                                                    }
                                                }
                                                if (i7 < i4) {
                                                    sb.setLength(0);
                                                    while (i7 < i4) {
                                                        int i10 = i7 + 1;
                                                        b = bArr2[i7];
                                                        if (b != 13) {
                                                            if (b == 10) {
                                                                break;
                                                            } else {
                                                                sb.append((char) b);
                                                            }
                                                        }
                                                        i7 = i10;
                                                    }
                                                    string3 = sb.toString();
                                                } else {
                                                    string3 = null;
                                                }
                                                String str2 = str;
                                                String str3 = string4;
                                                this.c.addElement(new HashedHostKey(str2, str3, i, Util.k(Util.v(string2), 0, string2.length()), string3));
                                            }
                                        }
                                    }
                                } else {
                                    sb.setLength(0);
                                    while (i7 < i4) {
                                        i3 = i7 + 1;
                                        b5 = bArr2[i7];
                                        if (b5 != 32) {
                                            sb.append((char) b5);
                                            i7 = i3;
                                        }
                                        i7 = i3;
                                        break;
                                    }
                                    string = sb.toString();
                                    if (HostKey.i(string) != -1) {
                                        i = HostKey.i(string);
                                    } else {
                                        i7 = i4;
                                    }
                                    if (i7 >= i4) {
                                        g(Util.d(bArr2, 0, i4));
                                    } else {
                                        while (i7 < i4) {
                                            b4 = bArr2[i7];
                                            if (b4 == 32) {
                                            }
                                            i7++;
                                        }
                                        sb.setLength(0);
                                        while (i7 < i4) {
                                            i2 = i7 + 1;
                                            b3 = bArr2[i7];
                                            if (b3 != 13) {
                                                if (b3 != 10) {
                                                    sb.append((char) b3);
                                                }
                                                i7 = i2;
                                                break;
                                            }
                                            i7 = i2;
                                        }
                                        string2 = sb.toString();
                                        if (string2.length() == 0) {
                                            g(Util.d(bArr2, 0, i4));
                                        } else {
                                            while (i7 < i4) {
                                                b2 = bArr2[i7];
                                                if (b2 == 32) {
                                                }
                                                i7++;
                                            }
                                            if (i7 < i4) {
                                                sb.setLength(0);
                                                while (i7 < i4) {
                                                    int i11 = i7 + 1;
                                                    b = bArr2[i7];
                                                    if (b != 13) {
                                                        if (b == 10) {
                                                            break;
                                                            break;
                                                        }
                                                        sb.append((char) b);
                                                    }
                                                    i7 = i11;
                                                }
                                                string3 = sb.toString();
                                            } else {
                                                string3 = null;
                                            }
                                            String str4 = str;
                                            String str5 = string4;
                                            this.c.addElement(new HashedHostKey(str4, str5, i, Util.k(Util.v(string2), 0, string2.length()), string3));
                                        }
                                    }
                                }
                            }
                        }
                        bArr = bArr2;
                    }
                }
            } catch (Throwable th) {
                if (inputStream == null) {
                    throw th;
                }
                try {
                    inputStream.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        } catch (Exception e2) {
            if (!(e2 instanceof JSchException)) {
                throw new JSchException(e2.toString(), e2);
            }
            throw ((JSchException) e2);
        }
    }

    void o(String str) throws JSchException {
        try {
            this.b = str;
            n(new FileInputStream(Util.h(str)));
        } catch (FileNotFoundException unused) {
        }
    }

    void p() throws IOException {
        String str = this.b;
        if (str != null) {
            q(str);
        }
    }

    synchronized void q(String str) throws IOException {
        if (str == null) {
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(Util.h(str));
        try {
            k(fileOutputStream);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    void r(UserInfo userInfo) {
        boolean z;
        String strA = a();
        if (strA == null) {
            return;
        }
        File file = new File(Util.h(strA));
        if (file.exists()) {
            z = true;
        } else {
            z = false;
            if (userInfo != null) {
                boolean zG = userInfo.g(strA + " does not exist.\nAre you sure you want to create it?");
                file = file.getParentFile();
                if (zG && file != null && !file.exists()) {
                    zG = userInfo.g("The parent directory " + file + " does not exist.\nAre you sure you want to create it?");
                    if (zG) {
                        if (file.mkdirs()) {
                            userInfo.d(file + " has been succesfully created.\nPlease check its access permission.");
                        } else {
                            userInfo.d(file + " has not been created.");
                            zG = false;
                        }
                    }
                }
                if (file != null) {
                    z = zG;
                }
            }
        }
        if (z) {
            try {
                q(strA);
            } catch (Exception e2) {
                this.a.j().b(3, "unable to sync known host file " + file.getPath(), e2);
            }
        }
    }
}
