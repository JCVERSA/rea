package com.alphainventor.filemanager.file;

import ax.I1.C0718i;
import ax.p290u9.C5731e;
import ax.p290u9.InterfaceC5732e0;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class B {
    private final A.a a;
    private final int b;
    private final ax.p045d2.c c;
    ax.p262s9.e d;
    int e;
    long f;
    private final ax.p318w9.b g;
    private final int h;
    private int i = 0;

    class a extends ax.p318w9.b {
        a(String str, ax.p276t9.f fVar, List list, Class cls) {
            super(str, fVar, list, cls);
        }
    }

    public B(String str, InterfaceC5732e0 interfaceC5732e0, List<ax.p360z9.c> list, A.a aVar, int i, ax.p045d2.c cVar, int i2, int i3, long j, ax.p262s9.e eVar) {
        this.a = aVar;
        this.b = i;
        this.c = cVar;
        this.d = eVar;
        this.f = j;
        this.e = i3;
        this.h = i2;
        a aVar2 = new a(str, interfaceC5732e0, list, C5731e.class);
        this.g = aVar2;
        aVar2.t(ax.p318w9.k.PUT);
        aVar2.k("Content-Range", String.format(Locale.US, "bytes %1$d-%2$d/%3$d", Integer.valueOf(i3), Integer.valueOf((i3 + i) - 1), Long.valueOf(j)));
    }

    public <UploadType> C5731e a(ax.p262s9.a<UploadType> aVar) throws C0718i {
        String str;
        C5731e c5731e;
        ax.p276t9.d e = null;
        while (true) {
            int i = this.i;
            if (i >= this.h) {
                break;
            }
            try {
                Thread.sleep(i * 2000 * i);
            } catch (InterruptedException e2) {
                this.g.m().a().b("Exception while waiting upload file retry", e2);
            }
            try {
                c5731e = (C5731e) this.g.m().b().c(this.g, C5731e.class, new OneDriveFileHelper.i(this.a.b, this.b, this.c, new b()), aVar);
            } catch (ax.p276t9.d e3) {
                e = e3;
                this.g.m().a().a("Request failed with, retry if necessary.");
                c5731e = null;
            }
            if (c5731e != null && c5731e.a()) {
                return c5731e;
            }
            if (c5731e != null && c5731e.d()) {
                e = c5731e.b();
            }
            ax.p045d2.c cVar = this.c;
            if ((cVar != null && cVar.isCancelled()) || !this.a.a.a()) {
                break;
            }
            try {
                this.a.b.close();
            } catch (IOException unused) {
            }
            A.a aVar2 = this.a;
            aVar2.b = aVar2.a.c(this.e);
            this.i++;
        }
        if (e != null) {
            str = "Upload session failed too many times (" + e.getMessage() + ")";
        } else {
            str = "Upload session failed too many times.";
        }
        return new C5731e(new ax.p276t9.d(str, e, ax.p276t9.e.UploadSessionIncomplete));
    }

    class b implements ax.p262s9.e {
        b() {
        }

        @Override // ax.p262s9.e
        public void a(long j, long j2) {
            B b = B.this;
            ax.p262s9.e eVar = b.d;
            if (eVar != null) {
                eVar.a(((long) b.e) + j, b.f);
            }
        }

        @Override // ax.p262s9.c
        public void b(Object obj) {
        }

        @Override // ax.p262s9.c
        public void c(ax.p276t9.d dVar) {
        }
    }
}
