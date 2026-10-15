package com.alphainventor.filemanager.file;

import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.d0;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6243s extends C6248x {
    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!d0.E(abstractC6239n)) {
            return super.d1(abstractC6239n, fVar);
        }
        ax.W2.h hVarS = ax.W2.h.S(abstractC6239n);
        if (!hVarS.l0()) {
            try {
                hVarS.f(true, null, null);
            } catch (C1957a e) {
                throw new C1966j("Cancelled", e);
            }
        }
        ArrayList arrayList = new ArrayList(hVarS.U());
        ArrayList arrayList2 = new ArrayList();
        ax.p086f3.K kP = ax.W2.h.p(abstractC6239n.M());
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            C6249y c6249y = new C6249y(this, new File(hVarS.y(((ax.W2.h.C0232h) obj).a())), kP);
            if (c6249y.o()) {
                arrayList2.add(c6249y);
            }
        }
        return arrayList2;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return null;
        }
        return ax.p086f3.B.W(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        long jP = abstractC6239n.p();
        super.q1(abstractC6239n);
        ax.W2.h hVarR = ax.W2.h.R(ax.W2.h.p(t()));
        if (hVarR.l0()) {
            List<ax.W2.h.C0232h> listU = hVarR.U();
            for (int i = 0; i < listU.size(); i++) {
                if (hVarR.y(listU.get(i).a()).equals(abstractC6239n.E())) {
                    listU.remove(i);
                    hVarR.L0(jP);
                    return;
                }
            }
        }
    }
}
