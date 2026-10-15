package com.alphainventor.filemanager.file;

import android.content.Context;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class H extends W {
    static a w;

    public static class a extends T {
        Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            return null;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void g(int i, String str) {
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, String str, ax.p182l3.k kVar) {
        }

        public void l(com.alphainventor.filemanager.activity.a aVar) {
            ax.p406z3.B.d0(aVar.getSupportFragmentManager(), ax.p054d3.W.v3(ax.U2.f.Z0), "serveraddress", true);
        }
    }

    public static ax.d3.W.d B0(String str, boolean z) {
        return ax.d3.W.d.ERROR;
    }

    public static a C0(Context context) {
        if (w == null) {
            w = new a(context.getApplicationContext());
        }
        return w;
    }

    @Override // com.alphainventor.filemanager.file.W, com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        return super.b1(str, str2, str3);
    }
}
