package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class AgentIdentity implements Identity {
    private AgentProxy a;
    private byte[] b;
    private String c;
    private String d;

    AgentIdentity(AgentProxy agentProxy, byte[] bArr, String str) {
        this.a = agentProxy;
        this.b = bArr;
        this.c = str;
        this.d = Util.c(new Buffer(bArr).p());
    }

    @Override // com.jcraft.jsch.Identity
    public byte[] a(byte[] bArr, String str) {
        return this.a.e(this.b, bArr, str);
    }

    @Override // com.jcraft.jsch.Identity
    public String b() {
        return this.d;
    }

    @Override // com.jcraft.jsch.Identity
    public boolean c() {
        return false;
    }

    @Override // com.jcraft.jsch.Identity
    public void clear() {
    }

    @Override // com.jcraft.jsch.Identity
    public byte[] d() {
        return this.b;
    }

    @Override // com.jcraft.jsch.Identity
    public boolean e(byte[] bArr) throws JSchException {
        return true;
    }

    @Override // com.jcraft.jsch.Identity
    public byte[] f(byte[] bArr) {
        return this.a.e(this.b, bArr, null);
    }

    @Override // com.jcraft.jsch.Identity
    public String getName() {
        return this.c;
    }
}
