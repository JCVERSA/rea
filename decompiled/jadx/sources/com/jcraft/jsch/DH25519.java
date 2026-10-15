package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class DH25519 extends DHXEC {
    public DH25519() {
        this.D = "sha-256";
        this.E = "X25519";
        this.F = 32;
    }
}
