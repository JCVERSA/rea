package com.alphainventor.filemanager.file;

import android.content.Context;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class D extends Q {
    static a w;

    public static ax.H1.Q.d v0(String str, boolean z) {
        return ax.H1.Q.d.ERROR;
    }

    public static a w0(Context context) {
        if (w == null) {
            w = new a(context.getApplicationContext());
        }
        return w;
    }

    @Override // com.alphainventor.filemanager.file.Q, com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        return super.W0(str, str2, str3);
    }

    public static class a extends N {
        Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            return null;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void g(int i, String str) {
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
        }

        public void l(com.alphainventor.filemanager.activity.a aVar) {
            ax.p045d2.x.d0(aVar.w(), ax.H1.Q.y3(ax.p338y1.f.V0), "serveraddress", true);
        }

        public void k(com.alphainventor.filemanager.activity.a aVar, String str, ax.P1.j jVar) {
        }
    }
}
