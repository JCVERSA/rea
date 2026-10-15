package com.jcraft.jsch;

import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
public class AgentIdentityRepository implements IdentityRepository {
    private AgentProxy a;

    @Override // com.jcraft.jsch.IdentityRepository
    public Vector<Identity> a() {
        return this.a.b();
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public boolean b(byte[] bArr) {
        return this.a.a(bArr);
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public boolean c(byte[] bArr) {
        return this.a.d(bArr);
    }

    @Override // com.jcraft.jsch.IdentityRepository
    public void d() {
        this.a.c();
    }
}
