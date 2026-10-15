package com.alphainventor.filemanager.file;

import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.C2008n;
import ax.p086f3.d0;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6237l extends C6248x {
    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!d0.E(abstractC6239n)) {
            return super.d1(abstractC6239n, fVar);
        }
        ax.W2.h hVarR = ax.W2.h.R(ax.W2.h.p(t()));
        if (!hVarR.l0()) {
            try {
                hVarR.f(true, null, null);
            } catch (C1957a e) {
                throw new C1966j("Cancelled", e);
            }
        }
        ax.W2.j jVarK = u() == ax.U2.f.r1 ? hVarR.K() : hVarR.H();
        if (jVarK == null) {
            throw new ax.p070e3.t();
        }
        ArrayList arrayList = new ArrayList();
        ax.p086f3.K kP = ax.W2.h.p(abstractC6239n.M());
        ArrayList arrayList2 = new ArrayList(jVarK.a());
        for (int i = 0; i < arrayList2.size(); i++) {
            ArrayList arrayList3 = new ArrayList(((ax.W2.k) arrayList2.get(i)).b());
            int i2 = 0;
            while (i2 < arrayList3.size()) {
                A a = (A) arrayList3.get(i2);
                if (a != null) {
                    C2008n c2008n = new C2008n(this, new File(a.a), kP, i, i2 == 0);
                    if (c2008n.o()) {
                        arrayList.add(c2008n);
                    }
                }
                i2++;
            }
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
        ax.W2.j jVarK;
        super.q1(abstractC6239n);
        ax.W2.h hVarR = ax.W2.h.R(ax.W2.h.p(t()));
        if (hVarR.l0() && (jVarK = hVarR.K()) != null) {
            jVarK.e(abstractC6239n.E());
            ax.W2.j jVarH = hVarR.H();
            if (jVarH == null) {
                return;
            }
            jVarH.e(abstractC6239n.E());
        }
    }
}
