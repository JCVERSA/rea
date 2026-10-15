package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import ax.P5.u;
import ax.Q5.AbstractBinderC1422k0;
import ax.Q5.InterfaceC1404e0;
import ax.Q5.InterfaceC1454v0;
import ax.Q5.Q;
import ax.Q5.Q0;
import ax.Q5.V;
import ax.Q5.d2;
import ax.S5.BinderC1525c;
import ax.S5.BinderC1529g;
import ax.S5.D;
import ax.S5.E;
import ax.S5.i;
import ax.S5.j;
import ax.p377x6.a;
import ax.p377x6.b;
import ax.p409z6.AbstractC3338Yu;
import ax.p409z6.BinderC6044yX;
import ax.p409z6.EJ;
import ax.p409z6.HJ;
import ax.p409z6.InterfaceC2561Cp;
import ax.p409z6.InterfaceC2586Dh;
import ax.p409z6.InterfaceC2775Iq;
import ax.p409z6.InterfaceC2877Ln;
import ax.p409z6.InterfaceC3047Qj;
import ax.p409z6.InterfaceC3117Sj;
import ax.p409z6.InterfaceC3156Tn;
import ax.p409z6.InterfaceC3786dm;
import ax.p409z6.InterfaceC3825e50;
import ax.p409z6.InterfaceC4661lp;
import ax.p409z6.InterfaceC4911o40;
import ax.p409z6.InterfaceC6061yh;
import ax.p409z6.N60;
import ax.p409z6.W50;
import ax.p409z6.WO;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ClientApi extends AbstractBinderC1422k0 {
    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC3156Tn B0(a aVar) {
        Activity activity = (Activity) b.e1(aVar);
        AdOverlayInfoParcel adOverlayInfoParcelJ = AdOverlayInfoParcel.j(activity.getIntent());
        if (adOverlayInfoParcelJ == null) {
            return new E(activity);
        }
        int i = adOverlayInfoParcelJ.q0;
        if (i == 1) {
            return new D(activity);
        }
        if (i == 2) {
            return new i(activity);
        }
        if (i == 3) {
            return new j(activity);
        }
        if (i != 4) {
            return i != 5 ? new E(activity) : new BinderC1529g(activity);
        }
        return new BinderC1525c(activity, adOverlayInfoParcelJ);
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final V E4(a aVar, d2 d2Var, String str, InterfaceC3786dm interfaceC3786dm, int i) {
        Context context = (Context) b.e1(aVar);
        W50 w50B = AbstractC3338Yu.i(context, interfaceC3786dm, i).B();
        w50B.b(context);
        w50B.a(d2Var);
        w50B.x(str);
        return w50B.f().a();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC6061yh F6(a aVar, a aVar2) {
        return new HJ((FrameLayout) b.e1(aVar), (FrameLayout) b.e1(aVar2), 244410000);
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC1404e0 N3(a aVar, InterfaceC3786dm interfaceC3786dm, int i) {
        return AbstractC3338Yu.i((Context) b.e1(aVar), interfaceC3786dm, i).b();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final V P5(a aVar, d2 d2Var, String str, int i) {
        return new u((Context) b.e1(aVar), d2Var, str, new ax.U5.a(244410000, i, true, false));
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final Q0 W1(a aVar, InterfaceC3786dm interfaceC3786dm, int i) {
        return AbstractC3338Yu.i((Context) b.e1(aVar), interfaceC3786dm, i).t();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC2877Ln W5(a aVar, InterfaceC3786dm interfaceC3786dm, int i) {
        return AbstractC3338Yu.i((Context) b.e1(aVar), interfaceC3786dm, i).u();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC1454v0 Z5(a aVar, int i) {
        return AbstractC3338Yu.i((Context) b.e1(aVar), null, i).j();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC2561Cp b2(a aVar, String str, InterfaceC3786dm interfaceC3786dm, int i) {
        Context context = (Context) b.e1(aVar);
        N60 n60C = AbstractC3338Yu.i(context, interfaceC3786dm, i).C();
        n60C.a(context);
        n60C.o(str);
        return n60C.c().a();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC4661lp e4(a aVar, InterfaceC3786dm interfaceC3786dm, int i) {
        Context context = (Context) b.e1(aVar);
        N60 n60C = AbstractC3338Yu.i(context, interfaceC3786dm, i).C();
        n60C.a(context);
        return n60C.c().b();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final V i7(a aVar, d2 d2Var, String str, InterfaceC3786dm interfaceC3786dm, int i) {
        Context context = (Context) b.e1(aVar);
        InterfaceC3825e50 interfaceC3825e50A = AbstractC3338Yu.i(context, interfaceC3786dm, i).A();
        interfaceC3825e50A.b(context);
        interfaceC3825e50A.a(d2Var);
        interfaceC3825e50A.x(str);
        return interfaceC3825e50A.f().a();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final Q k6(a aVar, String str, InterfaceC3786dm interfaceC3786dm, int i) {
        Context context = (Context) b.e1(aVar);
        return new BinderC6044yX(AbstractC3338Yu.i(context, interfaceC3786dm, i), context, str);
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC2586Dh n2(a aVar, a aVar2, a aVar3) {
        return new EJ((View) b.e1(aVar), (HashMap) b.e1(aVar2), (HashMap) b.e1(aVar3));
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final V n6(a aVar, d2 d2Var, String str, InterfaceC3786dm interfaceC3786dm, int i) {
        Context context = (Context) b.e1(aVar);
        InterfaceC4911o40 interfaceC4911o40Z = AbstractC3338Yu.i(context, interfaceC3786dm, i).z();
        interfaceC4911o40Z.o(str);
        interfaceC4911o40Z.a(context);
        return interfaceC4911o40Z.c().a();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC2775Iq u2(a aVar, InterfaceC3786dm interfaceC3786dm, int i) {
        return AbstractC3338Yu.i((Context) b.e1(aVar), interfaceC3786dm, i).x();
    }

    @Override // ax.Q5.InterfaceC1425l0
    public final InterfaceC3117Sj v3(a aVar, InterfaceC3786dm interfaceC3786dm, int i, InterfaceC3047Qj interfaceC3047Qj) {
        Context context = (Context) b.e1(aVar);
        WO woR = AbstractC3338Yu.i(context, interfaceC3786dm, i).r();
        woR.a(context);
        woR.b(interfaceC3047Qj);
        return woR.c().f();
    }
}
