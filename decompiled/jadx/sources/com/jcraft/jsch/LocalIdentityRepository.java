package com.jcraft.jsch;

import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
class LocalIdentityRepository implements IdentityRepository {
    private Vector<Identity> a = new Vector<>();
    private JSch.InstanceLogger b;

    LocalIdentityRepository(JSch.InstanceLogger instanceLogger) {
        this.b = instanceLogger;
    }

    private void f() {
        Vector vector = new Vector();
        int size = this.a.size();
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Identity identityElementAt = this.a.elementAt(i);
            byte[] bArrD = identityElementAt.d();
            if (bArrD != null) {
                for (int i2 = i + 1; i2 < size; i2++) {
                    Identity identityElementAt2 = this.a.elementAt(i2);
                    byte[] bArrD2 = identityElementAt2.d();
                    if (bArrD2 != null && Util.a(bArrD, bArrD2) && identityElementAt.c() == identityElementAt2.c()) {
                        vector.addElement(bArrD);
                        break;
                    }
                }
            }
        }
        for (int i3 = 0; i3 < vector.size(); i3++) {
            c((byte[]) vector.elementAt(i3));
        }
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public synchronized Vector<Identity> a() {
        Vector<Identity> vector;
        f();
        vector = new Vector<>();
        for (int i = 0; i < this.a.size(); i++) {
            vector.addElement(this.a.elementAt(i));
        }
        return vector;
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public synchronized boolean b(byte[] bArr) {
        try {
            e(IdentityFile.i("from remote:", bArr, null, this.b));
        } catch (JSchException unused) {
            return false;
        }
        return true;
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public synchronized boolean c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (int i = 0; i < this.a.size(); i++) {
            Identity identityElementAt = this.a.elementAt(i);
            byte[] bArrD = identityElementAt.d();
            if (bArrD != null && Util.a(bArr, bArrD)) {
                this.a.removeElement(identityElementAt);
                identityElementAt.clear();
                return true;
            }
        }
        return false;
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public synchronized void d() {
        for (int i = 0; i < this.a.size(); i++) {
            try {
                this.a.elementAt(i).clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        this.a.removeAllElements();
    }

    public synchronized void e(Identity identity) {
        if (!this.a.contains(identity)) {
            byte[] bArrD = identity.d();
            if (bArrD == null) {
                this.a.addElement(identity);
                return;
            }
            for (int i = 0; i < this.a.size(); i++) {
                byte[] bArrD2 = this.a.elementAt(i).d();
                if (bArrD2 != null && Util.a(bArrD, bArrD2)) {
                    if (identity.c() || !this.a.elementAt(i).c()) {
                        return;
                    } else {
                        c(bArrD2);
                    }
                }
            }
            this.a.addElement(identity);
        }
    }
}
