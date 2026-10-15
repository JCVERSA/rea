package com.alphainventor.filemanager.file;

/* JADX INFO: loaded from: classes.dex */
public class P {
    public Q.c a;
    public ax.Q9.b b;
    public ax.p063dc.r c;
    public String d;
    public String e;
    public int f;
    public boolean g;
    public String h;
    public boolean i;

    public P(ax.Q9.b bVar, String str, int i) {
        this.b = bVar;
        this.e = str;
        this.f = i;
        this.a = Q.c.SMB2AND3;
    }

    public P(ax.Q9.b bVar, String str, int i, ax.p063dc.r rVar) {
        this.b = bVar;
        this.e = str;
        this.f = i;
        this.a = Q.c.SMB1AND2;
        this.c = rVar;
    }

    public P(ax.p063dc.r rVar) {
        this.c = rVar;
        this.a = Q.c.SMB1;
    }

    public void a(String str) {
        this.d = str;
    }
}
