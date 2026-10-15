package com.alphainventor.filemanager.file;

import android.content.Context;
import ax.p086f3.g0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class T {

    class a implements Comparator<ax.p038c3.s> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ax.p038c3.s sVar, ax.p038c3.s sVar2) {
            return ax.p406z3.B.c(sVar.f(), sVar2.f());
        }
    }

    public static T b(Context context, ax.U2.f fVar) {
        if (fVar == ax.U2.f.S0) {
            return C6236k.a0(context);
        }
        if (fVar == ax.U2.f.T0) {
            return r.i0(context);
        }
        if (fVar == ax.U2.f.U0) {
            return OneDriveFileHelper.o0(context);
        }
        if (fVar == ax.U2.f.V0) {
            return Y.b0(context);
        }
        if (fVar == ax.U2.f.W0) {
            return I.Z(context);
        }
        if (fVar == ax.U2.f.Y0) {
            return D.I0(context);
        }
        if (fVar == ax.U2.f.Z0) {
            return H.C0(context);
        }
        if (fVar == ax.U2.f.X0) {
            return C6229d.Z(context);
        }
        return null;
    }

    public static List<ax.p038c3.s> c(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(C6236k.a0(context).o());
        arrayList.addAll(r.i0(context).o());
        arrayList.addAll(OneDriveFileHelper.o0(context).m());
        if (ax.p006a3.Q.E0()) {
            arrayList.addAll(C6229d.Z(context).n());
        }
        arrayList.addAll(I.Z(context).t());
        arrayList.addAll(Y.b0(context).m());
        arrayList.addAll(D.I0(context).m());
        h(arrayList);
        return arrayList;
    }

    public static g0 d(Context context, ax.U2.f fVar) {
        if (fVar == ax.U2.f.M0) {
            return C6242q.h0(context);
        }
        if (fVar == ax.U2.f.N0) {
            return L.i0(context);
        }
        if (fVar == ax.U2.f.O0) {
            return Q.b0(context);
        }
        if (fVar == ax.U2.f.P0) {
            return W.h0(context);
        }
        return null;
    }

    public static List<ax.p038c3.s> e(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(C6242q.h0(context).p());
        arrayList.addAll(L.i0(context).n());
        arrayList.addAll(Q.b0(context).n());
        arrayList.addAll(W.h0(context).o());
        h(arrayList);
        return arrayList;
    }

    private static void h(List<ax.p038c3.s> list) {
        Iterator<ax.p038c3.s> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            long jF = it.next().f();
            if (jF < 1000000 && jF > 0) {
                z = true;
            }
        }
        if (z) {
            try {
                Collections.sort(list, new a());
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public static void i(Context context, List<ax.p038c3.s> list, List<ax.p038c3.s> list2) {
        if (list == null || list2 == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            ax.p086f3.K kD = list.get(i).d();
            T tD = ax.U2.f.n0(kD.d()) ? d(context, kD.d()) : b(context, kD.d());
            if (tD != null) {
                tD.j(kD.b(), i + 1);
            }
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            ax.p086f3.K kD2 = list2.get(i2).d();
            T tD2 = ax.U2.f.n0(kD2.d()) ? d(context, kD2.d()) : b(context, kD2.d());
            if (tD2 != null) {
                tD2.a(kD2.b());
                ax.Y2.c.h(context, kD2);
            }
        }
    }

    public abstract void a(int i);

    public abstract ax.p038c3.s f(int i);

    public abstract void g(int i, String str);

    public abstract void j(int i, long j);
}
