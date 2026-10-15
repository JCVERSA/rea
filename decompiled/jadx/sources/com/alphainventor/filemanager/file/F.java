package com.alphainventor.filemanager.file;

import ax.p070e3.C1966j;
import ax.p333ua.C2388e;
import ax.p333ua.InterfaceC2389e0;
import ax.p406z3.InterfaceC2462c;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class F {
    private final E.a a;
    private final int b;
    private final InterfaceC2462c c;
    ax.p301sa.e d;
    int e;
    long f;
    private final ax.p365wa.b g;
    private final int h;
    private int i = 0;

    class a extends ax.p365wa.b {
        a(String str, ax.p317ta.f fVar, List list, Class cls) {
            super(str, fVar, list, cls);
        }
    }

    class b implements ax.p301sa.e {
        b() {
        }

        @Override // ax.p301sa.e
        public void a(long j, long j2) {
            F f = F.this;
            ax.p301sa.e eVar = f.d;
            if (eVar != null) {
                eVar.a(((long) f.e) + j, f.f);
            }
        }

        @Override // ax.p301sa.c
        public void b(ax.p317ta.d dVar) {
        }

        @Override // ax.p301sa.c
        public void c(Object obj) {
        }
    }

    public F(String str, InterfaceC2389e0 interfaceC2389e0, List<ax.p413za.c> list, E.a aVar, int i, InterfaceC2462c interfaceC2462c, int i2, int i3, long j, ax.p301sa.e eVar) {
        this.a = aVar;
        this.b = i;
        this.c = interfaceC2462c;
        this.d = eVar;
        this.f = j;
        this.e = i3;
        this.h = i2;
        a aVar2 = new a(str, interfaceC2389e0, list, C2388e.class);
        this.g = aVar2;
        aVar2.t(ax.p365wa.k.PUT);
        aVar2.k("Content-Range", String.format(Locale.US, "bytes %1$d-%2$d/%3$d", Integer.valueOf(i3), Integer.valueOf((i3 + i) - 1), Long.valueOf(j)));
    }

    public <UploadType> C2388e a(ax.p301sa.a<UploadType> aVar) throws C1966j {
        String str;
        C2388e c2388e;
        ax.p317ta.d dVarB = null;
        while (true) {
            int i = this.i;
            if (i >= this.h) {
                break;
            }
            try {
                Thread.sleep(i * 2000 * i);
            } catch (InterruptedException e) {
                this.g.m().a().b("Exception while waiting upload file retry", e);
            }
            try {
                c2388e = (C2388e) this.g.m().b().a(this.g, C2388e.class, new OneDriveFileHelper.i(this.a.b, this.b, this.c, new b()), aVar);
            } catch (ax.p317ta.d e2) {
                dVarB = e2;
                this.g.m().a().a("Request failed with, retry if necessary.");
                c2388e = null;
            }
            if (c2388e != null && c2388e.a()) {
                return c2388e;
            }
            if (c2388e != null && c2388e.d()) {
                dVarB = c2388e.b();
            }
            InterfaceC2462c interfaceC2462c = this.c;
            if ((interfaceC2462c != null && interfaceC2462c.isCancelled()) || !this.a.a.a()) {
                break;
            }
            try {
                this.a.b.close();
            } catch (IOException unused) {
            }
            E.a aVar2 = this.a;
            aVar2.b = aVar2.a.c(this.e);
            this.i++;
        }
        if (dVarB != null) {
            str = "Upload session failed too many times (" + dVarB.getMessage() + ")";
        } else {
            str = "Upload session failed too many times.";
        }
        return new C2388e(new ax.p317ta.d(str, dVarB, ax.p317ta.e.UploadSessionIncomplete));
    }
}
