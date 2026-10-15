package com.alphainventor.filemanager.file;

import android.content.Context;
import ax.J1.Y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class N {

    class a implements Comparator<ax.G1.p> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ax.G1.p pVar, ax.G1.p pVar2) {
            return ax.p045d2.x.c(pVar.f(), pVar2.f());
        }
    }

    public static N b(Context context, ax.p338y1.f fVar) {
        if (fVar == ax.p338y1.f.P0) {
            return C5836j.U(context);
        }
        if (fVar == ax.p338y1.f.Q0) {
            return C5842p.c0(context);
        }
        if (fVar == ax.p338y1.f.R0) {
            return OneDriveFileHelper.i0(context);
        }
        if (fVar == ax.p338y1.f.S0) {
            return T.w0(context);
        }
        if (fVar == ax.p338y1.f.U0) {
            return z.C0(context);
        }
        if (fVar == ax.p338y1.f.V0) {
            return D.w0(context);
        }
        if (fVar == ax.p338y1.f.T0) {
            return C5829c.T(context);
        }
        return null;
    }

    public static List<ax.G1.p> c(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(C5836j.U(context).o());
        arrayList.addAll(C5842p.c0(context).o());
        arrayList.addAll(OneDriveFileHelper.i0(context).m());
        if (ax.E1.P.x0()) {
            arrayList.addAll(C5829c.T(context).n());
        }
        arrayList.addAll(T.w0(context).m());
        arrayList.addAll(z.C0(context).m());
        h(arrayList);
        return arrayList;
    }

    public static Y d(Context context, ax.p338y1.f fVar) {
        if (fVar == ax.p338y1.f.J0) {
            return C5841o.b0(context);
        }
        if (fVar == ax.p338y1.f.K0) {
            return G.c0(context);
        }
        if (fVar == ax.p338y1.f.L0) {
            return L.V(context);
        }
        if (fVar == ax.p338y1.f.M0) {
            return Q.b0(context);
        }
        return null;
    }

    public static List<ax.G1.p> e(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(C5841o.b0(context).p());
        arrayList.addAll(G.c0(context).n());
        arrayList.addAll(L.V(context).n());
        arrayList.addAll(Q.b0(context).o());
        h(arrayList);
        return arrayList;
    }

    private static void h(List<ax.G1.p> list) {
        Iterator<ax.G1.p> it = list.iterator();
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

    public static void i(Context context, List<ax.G1.p> list, List<ax.G1.p> list2) {
        if (list == null || list2 == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            ax.J1.F fD = list.get(i).d();
            N nD = ax.p338y1.f.l0(fD.d()) ? d(context, fD.d()) : b(context, fD.d());
            if (nD != null) {
                nD.j(fD.b(), i + 1);
            }
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            ax.J1.F fD2 = list2.get(i2).d();
            N nD2 = ax.p338y1.f.l0(fD2.d()) ? d(context, fD2.d()) : b(context, fD2.d());
            if (nD2 != null) {
                nD2.a(fD2.b());
            }
        }
    }

    public abstract void a(int i);

    public abstract ax.G1.p f(int i);

    public abstract void g(int i, String str);

    public abstract void j(int i, long j);
}
