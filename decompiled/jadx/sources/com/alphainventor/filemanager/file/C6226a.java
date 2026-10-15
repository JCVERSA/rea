package com.alphainventor.filemanager.file;

import android.content.Context;
import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.d0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6226a extends C6248x {
    ax.W2.h A;
    ax.p086f3.K z;

    public static boolean V1(AbstractC6239n abstractC6239n) {
        return ax.X2.d.Q(d0.v(ax.W2.h.p(abstractC6239n.M()), abstractC6239n.E(), Boolean.TRUE));
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.AbstractC6238m
    public void E(Context context, ax.p086f3.K k) {
        super.E(context, k);
        ax.p086f3.K kP = ax.W2.h.p(t());
        this.z = kP;
        this.A = ax.W2.h.R(kP);
    }

    boolean U1(AbstractC6239n abstractC6239n) {
        return ax.X2.d.Q(d0.v(this.z, abstractC6239n.E(), Boolean.TRUE));
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!d0.E(abstractC6239n)) {
            return super.d1(abstractC6239n, fVar);
        }
        if (!this.A.l0()) {
            try {
                this.A.f(true, null, null);
            } catch (C1957a e) {
                throw new C1966j("Cancelled", e);
            }
        }
        ArrayList arrayList = new ArrayList(this.A.M());
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ax.W2.h.C0232h c0232h = (ax.W2.h.C0232h) obj;
            if (c0232h.b() != 0) {
                arrayList2.add(a1(this.A.y(c0232h.a())));
            }
        }
        return arrayList2;
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return null;
        }
        return (abstractC6239n.isDirectory() && U1(abstractC6239n)) ? ax.p086f3.B.S(this.z, abstractC6239n) : ax.p086f3.B.W(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.C6248x, com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        long jB;
        boolean zIsDirectory = abstractC6239n.isDirectory();
        List<ax.W2.h.C0232h> listM = this.A.M();
        boolean zU1 = zIsDirectory ? U1(abstractC6239n) : false;
        if (!zU1 || !this.A.l0() || listM == null) {
            jB = 0;
            break;
        }
        int i = 0;
        while (true) {
            if (i >= listM.size()) {
                jB = 0;
                break;
            }
            ax.W2.h.C0232h c0232h = listM.get(i);
            if (this.A.y(c0232h.a()).equals(abstractC6239n.E())) {
                jB = c0232h.b();
                break;
            }
            i++;
        }
        super.q1(abstractC6239n);
        if (zU1 && this.A.l0() && listM != null) {
            for (int i2 = 0; i2 < listM.size(); i2++) {
                if (this.A.y(listM.get(i2).a()).equals(abstractC6239n.E())) {
                    listM.remove(i2);
                    this.A.K0(jB);
                    return;
                }
            }
        }
    }
}
