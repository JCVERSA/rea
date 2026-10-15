package com.alphainventor.filemanager.file;

import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.d0;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6244t extends C6248x {
    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) {
        return new C6249y(this, new File(str), (ax.p086f3.K) null);
    }

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
        List<ax.W2.h.C0232h> listT = hVarS.T();
        if (listT == null) {
            throw new ax.p070e3.t();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ax.W2.h.C0232h> it = listT.iterator();
        while (it.hasNext()) {
            arrayList.add(a1(hVarS.y(it.next().a())));
        }
        return arrayList;
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
        super.q1(abstractC6239n);
        ax.W2.h hVarR = ax.W2.h.R(ax.W2.h.p(t()));
        if (hVarR.l0()) {
            List<ax.W2.h.C0232h> listT = hVarR.T();
            for (int i = 0; i < listT.size(); i++) {
                if (hVarR.y(listT.get(i).a()).equals(abstractC6239n.E())) {
                    listT.remove(i);
                    return;
                }
            }
        }
    }
}
