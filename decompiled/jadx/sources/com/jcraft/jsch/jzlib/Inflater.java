package com.jcraft.jsch.jzlib;

/* JADX INFO: loaded from: classes3.dex */
final class Inflater extends ZStream {
    private int n = -1;
    private JZlib.WrapperType o = null;
    private boolean p = false;
    private boolean q = false;

    Inflater() {
        i();
    }

    int f() {
        this.q = true;
        Inflate inflate = this.k;
        if (inflate == null) {
            return -2;
        }
        return inflate.c();
    }

    boolean g() {
        return this.k.a == 12;
    }

    int h(int i) {
        Inflate inflate = this.k;
        if (inflate == null) {
            return -2;
        }
        int iB = inflate.b(i);
        if (iB == 1) {
            this.q = true;
        }
        return iB;
    }

    int i() {
        return j(15);
    }

    int j(int i) {
        return k(i, false);
    }

    int k(int i, boolean z) {
        this.q = false;
        Inflate inflate = new Inflate(this);
        this.k = inflate;
        if (z) {
            i = -i;
        }
        return inflate.d(i);
    }
}
