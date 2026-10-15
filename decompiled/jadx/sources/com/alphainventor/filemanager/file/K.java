package com.alphainventor.filemanager.file;

/* JADX INFO: loaded from: classes.dex */
public class K {
    public L.c a;
    public ax.p010a9.b b;
    public ax.Ta.r c;
    public String d;
    public String e;
    public int f;
    public boolean g;
    public String h;

    public K(ax.p010a9.b bVar, String str, int i) {
        this.b = bVar;
        this.e = str;
        this.f = i;
        this.a = L.c.SMB2AND3;
    }

    public void a(String str) {
        this.d = str;
    }

    public K(ax.p010a9.b bVar, String str, int i, ax.Ta.r rVar) {
        this.b = bVar;
        this.e = str;
        this.f = i;
        this.a = L.c.SMB1AND2;
        this.c = rVar;
    }

    public K(ax.Ta.r rVar) {
        this.c = rVar;
        this.a = L.c.SMB1;
    }
}
