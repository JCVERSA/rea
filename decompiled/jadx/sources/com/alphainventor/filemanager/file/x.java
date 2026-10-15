package com.alphainventor.filemanager.file;

import ax.I1.C0718i;
import ax.J1.C0740v;
import ax.J1.V;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class x extends C5843q {
    @Override // com.alphainventor.filemanager.file.C5843q
    protected boolean L1(AbstractC5838l abstractC5838l) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.C5843q, com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) {
        return new y(this, new File(str), null);
    }

    @Override // com.alphainventor.filemanager.file.C5843q, com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (abstractC5838l == null) {
            return null;
        }
        return C0740v.O(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.C5843q, com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!V.B(abstractC5838l)) {
            ax.p221pa.c.h().d("NF3L:").h(abstractC5838l.C()).i();
            throw new C0718i("New files not reachable");
        }
        try {
            C5844s c5844sC = C5844s.C(n(), null);
            if (c5844sC == null) {
                throw new C0718i("New files exception : scanTask");
            }
            c5844sC.l();
            return ax.G1.b.k().g(abstractC5838l);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ax.p221pa.c.h().d("NF1L").m(e).i();
            throw new C0718i("New files exception 1");
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            ax.p221pa.c.h().d("NF2L").m(e2).i();
            throw new C0718i("New files exception 2");
        }
    }

    @Override // com.alphainventor.filemanager.file.C5843q, com.alphainventor.filemanager.file.C5845t, com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        super.l1(abstractC5838l);
        C5843q.M1(this, abstractC5838l);
    }
}
