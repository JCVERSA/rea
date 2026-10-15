package com.alphainventor.filemanager.file;

import ax.p070e3.C1966j;
import ax.p086f3.d0;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class B extends C6245u {
    @Override // com.alphainventor.filemanager.file.C6245u
    protected boolean U1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.C6245u, com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) {
        return new C(this, new File(str), null);
    }

    @Override // com.alphainventor.filemanager.file.C6245u, com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!d0.E(abstractC6239n)) {
            ax.p302sb.c.h().d("NF3L:").h(abstractC6239n.E()).i();
            throw new C1966j("New files not reachable");
        }
        try {
            C6247w c6247wC = C6247w.C(p(), null);
            if (c6247wC == null) {
                throw new C1966j("New files exception : scanTask");
            }
            c6247wC.l();
            return ax.p038c3.b.k().g(abstractC6239n);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ax.p302sb.c.h().d("NF1L").m(e).i();
            throw new C1966j("New files exception 1");
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            ax.p302sb.c.h().d("NF2L").m(e2).i();
            throw new C1966j("New files exception 2");
        }
    }

    @Override // com.alphainventor.filemanager.file.C6245u, com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return null;
        }
        return ax.p086f3.B.W(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.C6245u, com.alphainventor.filemanager.file.C6248x
    public C6249y h0(File file, ax.p086f3.K k, boolean z) {
        return new C(this, file, k);
    }

    @Override // com.alphainventor.filemanager.file.C6245u, com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        super.q1(abstractC6239n);
        C6245u.W1(this, abstractC6239n);
    }
}
