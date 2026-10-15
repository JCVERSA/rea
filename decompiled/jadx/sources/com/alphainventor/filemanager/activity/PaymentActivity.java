package com.alphainventor.filemanager.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import ax.H1.u;
import ax.J1.C0734o;
import ax.p045d2.o;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.activity.PaymentActivity;
import com.alphainventor.filemanager.license.datatypes.LicenseByCoupon;
import com.alphainventor.filemanager.license.datatypes.ProductCatalogImpl;
import com.android.billingclient.api.AbstractC5846a;
import com.android.billingclient.api.C5848c;
import com.android.billingclient.api.C5849d;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.socialnmobile.commons.inapppurchase.billing.datatypes.InAppPurchaseDataSigned;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class PaymentActivity extends ActivityC5571c implements ax.p101h2.b, u.b {
    private static final Logger l1 = ax.p338y1.g.a(PaymentActivity.class);
    private Toolbar E0;
    private Button F0;
    private Button G0;
    private Button H0;
    private TextView I0;
    private Button J0;
    private Button K0;
    private Button L0;
    private TextView M0;
    private TextView N0;
    private View O0;
    private View P0;
    private View Q0;
    private View R0;
    private View S0;
    private TextView T0;
    private View U0;
    private TextView V0;
    private TextView W0;
    private TextView X0;
    private TextView Y0;
    private TextView Z0;
    private View a1;
    private View b1;
    private AbstractC5846a c1;
    private n d1;
    private InAppPurchaseDataSigned e1;
    private l f1;
    private boolean g1;
    private boolean h1;
    private boolean i1;
    private final Handler j1 = new Handler(Looper.getMainLooper());
    private final ax.p101h2.e k1 = new a();

    class a implements ax.p101h2.e {
        a() {
        }

        @Override // ax.p101h2.e
        public void a(C5849d c5849d, List<Purchase> list) {
            int iB = c5849d.b();
            if (iB == 1) {
                return;
            }
            if (iB == 7) {
                PaymentActivity.this.H1(90, null, false);
                PaymentActivity.this.r1(true);
                return;
            }
            int iO1 = PaymentActivity.this.o1(c5849d, "PurchasesUpdatedListener");
            if (iO1 != 0) {
                PaymentActivity.l1.warning("onPurchasesUpdated: ResponseCode=" + iB);
                PaymentActivity.this.H1(iO1, null, false);
                return;
            }
            if (list == null) {
                return;
            }
            PaymentActivity.l1.warning("onPurchasesUpdated: " + list.size());
            for (Purchase purchase : list) {
                PaymentActivity.l1.warning("on purchase updated: " + purchase);
                InAppPurchaseDataSigned inAppPurchaseDataSigned = new InAppPurchaseDataSigned();
                inAppPurchaseDataSigned.purchaseData = purchase.a();
                inAppPurchaseDataSigned.signature = purchase.f();
                PaymentActivity.this.s1(inAppPurchaseDataSigned, true);
            }
        }
    }

    class b extends ax.P1.c {
        b() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.finish();
        }
    }

    class c extends ax.P1.c {
        c(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.C1();
        }
    }

    class d extends ax.P1.c {
        d(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.L1();
        }
    }

    class e extends ax.P1.c {
        e(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.z1();
        }
    }

    class f extends ax.P1.c {
        f(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.A1();
        }
    }

    class g extends ax.P1.c {
        g(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.L1();
        }
    }

    class h extends ax.P1.c {
        h(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.z1();
        }
    }

    class i extends ax.P1.c {
        i(long j) {
            super(j);
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.D1();
        }
    }

    class j extends ax.P1.c {
        j() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            PaymentActivity.this.x1();
        }
    }

    class k extends ax.p045d2.n<InAppPurchaseDataSigned, Integer, ax.p123ia.b<ax.p151ka.e>> {
        boolean h;
        Throwable i;

        k(boolean z) {
            super(ax.d2.n.f.HIGHER);
            this.h = z;
        }

        @Override // ax.p045d2.n
        protected void r() {
            PaymentActivity.this.O0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public ax.p123ia.b<ax.p151ka.e> g(InAppPurchaseDataSigned... inAppPurchaseDataSignedArr) {
            String str;
            if (PaymentActivity.this.i1) {
                return null;
            }
            try {
                return ax.O1.c.s().v().c(ProductCatalogImpl.getProductTypeStatic(PaymentActivity.this.e1.getUnverifiedPurchaseData(ax.O1.c.s().o()).productId).q, PaymentActivity.this.e1);
            } catch (ax.p151ka.a e) {
                this.i = e;
                e.printStackTrace();
                ax.p221pa.c.h().d("HANDLE PURCHASE ERROR 5").m(e).i();
                return null;
            } catch (ax.p151ka.d e2) {
                e2.printStackTrace();
                this.i = e2;
                ax.p221pa.c.h().d("HANDLE PURCHASE ERROR 2").m(e2).i();
                return null;
            } catch (ax.p151ka.f e3) {
                e3.printStackTrace();
                this.i = e3;
                if (e3.q == 40001) {
                    if (x.M(PaymentActivity.this.getApplicationContext()) || o.g()) {
                        PaymentActivity.this.i1 = true;
                    }
                    str = "rooted:" + o.g() + ",sign:";
                    try {
                        Iterator<String> it = x.s(PaymentActivity.this.getPackageManager().getPackageInfo(PaymentActivity.this.getPackageName(), 64).signatures).iterator();
                        while (it.hasNext()) {
                            str = str + it.next() + ":::";
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    str = null;
                }
                ax.p221pa.c.h().d("HANDLE PURCHASE ERROR 4").m(e3).h(str).i();
                return null;
            } catch (ax.p151ka.g e4) {
                e4.printStackTrace();
                this.i = e4;
                ax.p221pa.c.h().d("HANDLE PURCHASE ERROR 3").m(e4).i();
                return null;
            } catch (IOException e5) {
                e5.printStackTrace();
                this.i = e5;
                ax.p221pa.c.h().d("HANDLE PURCHASE ERROR 1").m(e5).i();
                return null;
            } catch (IllegalStateException e6) {
                this.i = e6;
                e6.printStackTrace();
                ax.p221pa.c.h().d("HANDLE PURCHASE ERROR 6").m(e6).i();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(ax.p123ia.b<ax.p151ka.e> bVar) {
            String str;
            PaymentActivity.this.O0.setVisibility(8);
            if (bVar == null) {
                if (PaymentActivity.this.i1) {
                    PaymentActivity.this.H1(40, this.i, false);
                    return;
                } else {
                    PaymentActivity.this.H1(60, this.i, false);
                    return;
                }
            }
            ax.O1.c.s().Q(bVar);
            PaymentActivity.this.K1(false);
            if (this.h && ax.p031c2.g.g()) {
                if (ax.p031c2.g.i()) {
                    str = ax.p031c2.g.j() ? "onetime" : "subscription";
                } else {
                    str = "promocode";
                }
                ax.p338y1.a.k().o("payment", "payment_purchased").c("type", str).e();
            }
        }
    }

    class l extends ax.p045d2.n<Void, Integer, ax.p123ia.b<LicenseByCoupon>> {
        int h;
        String i;
        Throwable j;

        l(String str) {
            super(ax.d2.n.f.HIGHER);
            this.i = str;
        }

        @Override // ax.p045d2.n
        protected void r() {
            PaymentActivity.this.O0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public ax.p123ia.b<LicenseByCoupon> g(Void... voidArr) {
            ax.L1.c cVarV = ax.O1.c.s().v();
            if (cVarV == null) {
                this.h = 40;
                return null;
            }
            try {
                return cVarV.b(this.i);
            } catch (ax.p151ka.d e) {
                e.printStackTrace();
                this.h = 1;
                this.j = e;
                return null;
            } catch (ax.p151ka.f e2) {
                e2.printStackTrace();
                this.h = PaymentActivity.this.p1(e2);
                this.j = e2;
                return null;
            } catch (ax.p151ka.g e3) {
                e3.printStackTrace();
                this.h = 1;
                this.j = e3;
                return null;
            } catch (IOException e4) {
                e4.printStackTrace();
                this.h = 10;
                this.j = e4;
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(ax.p123ia.b<LicenseByCoupon> bVar) {
            PaymentActivity.this.O0.setVisibility(8);
            if (bVar == null) {
                PaymentActivity.this.H1(this.h, this.j, false);
            } else {
                ax.O1.c.s().Q(bVar);
                PaymentActivity.this.K1(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class m extends ax.p045d2.n<Void, Integer, ax.N1.a> {
        int h;
        Throwable i;

        class a implements ax.O1.c.g {
            final /* synthetic */ ax.p123ia.c a;
            final /* synthetic */ ax.p123ia.c b;
            final /* synthetic */ ax.p123ia.c c;
            final /* synthetic */ ax.p123ia.c d;

            a(ax.p123ia.c cVar, ax.p123ia.c cVar2, ax.p123ia.c cVar3, ax.p123ia.c cVar4) {
                this.a = cVar;
                this.b = cVar2;
                this.c = cVar3;
                this.d = cVar4;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void c(C5849d c5849d, Map map, ax.p123ia.c cVar, ax.p123ia.c cVar2, ax.p123ia.c cVar3, ax.p123ia.c cVar4) {
                PaymentActivity.this.O0.setVisibility(8);
                c5849d.b();
                int iO1 = PaymentActivity.this.o1(c5849d, "skuDetailsListener");
                b bVar = null;
                if (iO1 != 0) {
                    PaymentActivity.this.H1(iO1, null, true);
                    return;
                }
                n nVar = new n(bVar);
                nVar.a = (SkuDetails) map.get(cVar);
                nVar.b = (SkuDetails) map.get(cVar2);
                nVar.c = (SkuDetails) map.get(cVar3);
                SkuDetails skuDetails = (SkuDetails) map.get(cVar4);
                nVar.d = skuDetails;
                if (nVar.a != null && nVar.b != null && nVar.c != null && skuDetails != null) {
                    PaymentActivity.this.d1 = nVar;
                    PaymentActivity.this.P0.setVisibility(0);
                    PaymentActivity.this.S0.setVisibility(8);
                    PaymentActivity.this.Q0.setVisibility(0);
                    if (ax.p031c2.d.v().x()) {
                        PaymentActivity.this.R0.setVisibility(0);
                    } else {
                        PaymentActivity.this.R0.setVisibility(8);
                    }
                    PaymentActivity.this.F1(nVar);
                    PaymentActivity.this.E1(nVar);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("yearly:");
                sb.append(nVar.a == null);
                sb.append(",yearlydc:");
                sb.append(nVar.b == null);
                sb.append(",onetime:");
                sb.append(nVar.c == null);
                sb.append(",onetimedc:");
                sb.append(nVar.d == null);
                ax.p221pa.c.h().b("Get SkuDetails Error").h(sb.toString()).i();
                PaymentActivity.this.H1(40, null, true);
            }

            @Override // ax.O1.c.g
            public void a(final C5849d c5849d, final Map<ax.p123ia.c, SkuDetails> map) {
                Handler handler = PaymentActivity.this.j1;
                final ax.p123ia.c cVar = this.a;
                final ax.p123ia.c cVar2 = this.b;
                final ax.p123ia.c cVar3 = this.c;
                final ax.p123ia.c cVar4 = this.d;
                handler.post(new Runnable() { // from class: com.alphainventor.filemanager.activity.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.q.c(c5849d, map, cVar, cVar2, cVar3, cVar4);
                    }
                });
            }
        }

        m() {
            super(ax.d2.n.f.HIGHER);
        }

        @Override // ax.p045d2.n
        protected void r() {
            PaymentActivity.this.O0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public ax.N1.a g(Void[] voidArr) {
            ax.L1.c cVarV = ax.O1.c.s().v();
            if (cVarV == null) {
                this.h = 40;
                return null;
            }
            try {
                return cVarV.f();
            } catch (ax.p151ka.d e) {
                this.h = 1;
                this.i = e;
                return null;
            } catch (ax.p151ka.f e2) {
                this.h = PaymentActivity.this.q1(e2);
                this.i = e2;
                return null;
            } catch (IOException e3) {
                this.h = 10;
                this.i = e3;
                return null;
            } catch (Exception e4) {
                this.h = 1;
                this.i = e4;
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(ax.N1.a aVar) {
            PaymentActivity.this.O0.setVisibility(8);
            if (ax.p031c2.g.g()) {
                return;
            }
            if (aVar == null) {
                PaymentActivity.this.H1(this.h, this.i, true);
                return;
            }
            ArrayList arrayList = new ArrayList();
            ax.p123ia.c premiumBasicYearly = aVar.getPremiumBasicYearly();
            ax.p123ia.c premiumBasicYearlyDiscount = aVar.getPremiumBasicYearlyDiscount();
            if (premiumBasicYearly != null) {
                arrayList.add(premiumBasicYearly);
            }
            if (premiumBasicYearlyDiscount != null) {
                arrayList.add(premiumBasicYearlyDiscount);
            }
            ax.p123ia.c premiumBasicOnetime = aVar.getPremiumBasicOnetime();
            ax.p123ia.c premiumBasicOnetimeDiscount = aVar.getPremiumBasicOnetimeDiscount();
            if (premiumBasicOnetime != null) {
                arrayList.add(premiumBasicOnetime);
            }
            if (premiumBasicOnetimeDiscount != null) {
                arrayList.add(premiumBasicOnetimeDiscount);
            }
            if (PaymentActivity.this.c1 == null) {
                PaymentActivity.l1.warning("No BillingClient");
                return;
            }
            ax.O1.c cVarS = ax.O1.c.s();
            PaymentActivity.this.O0.setVisibility(0);
            cVarS.N(PaymentActivity.this.c1, arrayList, new a(premiumBasicYearly, premiumBasicYearlyDiscount, premiumBasicOnetime, premiumBasicOnetimeDiscount));
        }
    }

    private static class n {
        SkuDetails a;
        SkuDetails b;
        SkuDetails c;
        SkuDetails d;

        private n() {
        }

        /* synthetic */ n(b bVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E1(n nVar) {
        if (ax.p031c2.d.v().x()) {
            if (!this.g1) {
                this.X0.setText(nVar.c.a());
                this.Y0.setVisibility(4);
                return;
            }
            this.X0.setText(nVar.d.a());
            this.Y0.setText(nVar.c.a());
            TextView textView = this.Y0;
            textView.setPaintFlags(textView.getPaintFlags() | 16);
            this.Y0.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F1(n nVar) {
        if (!this.h1) {
            this.I0.setText(getString(R.string.payment_subscription_description, nVar.a.a()));
            this.V0.setText(nVar.a.a());
            this.W0.setVisibility(4);
        } else {
            this.I0.setText(getString(R.string.payment_subscription_description, nVar.b.a()));
            this.V0.setText(nVar.b.a());
            this.W0.setText(nVar.a.a());
            TextView textView = this.W0;
            textView.setPaintFlags(textView.getPaintFlags() | 16);
            this.W0.setVisibility(0);
        }
    }

    private void G1() {
        this.g1 = true;
        this.h1 = true;
        if (System.currentTimeMillis() <= 1546257600000L && this.d1 != null) {
            ax.p338y1.a.k().o("general", "show_discount").e();
            ax.p221pa.c.h().g().e("showDiscount").i();
            F1(this.d1);
            E1(this.d1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int o1(C5849d c5849d, String str) {
        int iB = c5849d.b();
        String strA = c5849d.a();
        if (iB != 12) {
            switch (iB) {
                case -3:
                case 2:
                    break;
                case -2:
                    ax.p221pa.c.h().g().b("PAYMENT FEATURE NOT SUPPORTED").h(str + " : " + strA).i();
                    return 1;
                case SubsamplingScaleImageView.ORIENTATION_USE_EXIF /* -1 */:
                    return 20;
                case 0:
                case 1:
                    return 0;
                case 3:
                    ax.p221pa.c.h().g().b("BILLING_UNAVAILABLE ERROR").h(str + " : " + strA).i();
                    return 100;
                case 4:
                case 5:
                    return 1;
                case 6:
                    return 20;
                case 7:
                    return 90;
                case 8:
                    return 40;
                default:
                    ax.p221pa.c.h().g().b("UNKNOWN BILLING RESPONSE ERROR").h(str + " : " + strA).i();
                    return 1;
            }
        }
        return 10;
    }

    private boolean t1(String str) {
        return "FILE-PLUS-DISC-OUNT".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u1(boolean z, List list) {
        if (list.size() <= 0) {
            if (z) {
                ax.p221pa.c.h().g().d("PAYMENT ALREADY OWNED BUT PURCHASES 0").k().i();
                return;
            }
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            InAppPurchaseDataSigned inAppPurchaseDataSigned = (InAppPurchaseDataSigned) it.next();
            try {
                if (inAppPurchaseDataSigned.getUnverifiedPurchaseData(ax.O1.c.s().o()).purchaseState == 0) {
                    s1(inAppPurchaseDataSigned, false);
                    return;
                }
                continue;
            } catch (ax.p151ka.a e2) {
                ax.p221pa.c.h().g().d("PAYMENT ALREADY PAYED : SERIALIZE ").m(e2).h(inAppPurchaseDataSigned.purchaseData).i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void v1(C5849d c5849d) {
        int iB = c5849d.b();
        if (iB == 3) {
            return;
        }
        ax.p221pa.c.h().g().d("PAYMENT ALREADY PAYED : BILLING").a("billing response code: " + iB).i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w1(C5849d c5849d) {
        int iB = c5849d.b();
        String strA = c5849d.a();
        if (iB == 0) {
            K1(true);
            return;
        }
        l1.severe("BillingClient Setup Error : (" + iB + ") " + strA);
        if (iB == 3) {
            H1(100, null, true);
            return;
        }
        if (iB != 6) {
            ax.p221pa.c.h().d("BillingClient Setup").h("(" + iB + ") " + strA).k().i();
        }
        H1(1, null, true);
    }

    private void y1(SkuDetails skuDetails) {
        C5849d c5849dB = this.c1.b(this, C5848c.a().b(skuDetails).a());
        if (c5849dB.b() == 7) {
            H1(90, null, false);
            r1(true);
        } else {
            int iO1 = o1(c5849dB, "proceedBuy");
            if (iO1 != 0) {
                H1(iO1, null, false);
            }
        }
    }

    void A1() {
        y1(this.g1 ? this.d1.d : this.d1.c);
    }

    void B1(String str) {
        if (ax.p045d2.n.n(this.f1)) {
            return;
        }
        l lVar = new l(str);
        this.f1 = lVar;
        lVar.i(new Void[0]);
    }

    void C1() {
        y1(this.h1 ? this.d1.b : this.d1.a);
    }

    void D1() {
        s1(this.e1, true);
    }

    @Override // ax.p101h2.b
    public void H(final C5849d c5849d) {
        this.j1.post(new Runnable() { // from class: ax.z1.c
            @Override // java.lang.Runnable
            public final void run() {
                this.q.w1(c5849d);
            }
        });
    }

    void H1(int i2, Throwable th, boolean z) {
        switch (i2) {
            case 1:
                String string = getString(R.string.error_unknown_try_later);
                if (ax.p031c2.i.n(this) && th != null) {
                    string = string + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                J1(string, z);
                break;
            case 10:
                String string2 = getString(R.string.error_network);
                if (ax.p031c2.i.n(this) && th != null) {
                    string2 = string2 + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                J1(string2, z);
                break;
            case 20:
                I1(R.string.error_payment, z);
                break;
            case 30:
                String string3 = getString(R.string.error_payment_server, String.valueOf(th instanceof ax.p151ka.f ? ((ax.p151ka.f) th).q : 0));
                if (ax.p031c2.i.n(this) && th != null) {
                    string3 = string3 + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                J1(string3, z);
                break;
            case 40:
                String string4 = getString(R.string.error_payment);
                if (ax.p031c2.i.n(this) && th != null) {
                    string4 = string4 + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                J1(string4, z);
                break;
            case 50:
                I1(R.string.error_payment_server_down, z);
                break;
            case 60:
                this.a1.setVisibility(0);
                this.P0.setVisibility(8);
                this.U0.setVisibility(0);
                String string5 = getString(R.string.error_payment_ok_but_failed);
                if (ax.p031c2.i.n(this) && th != null) {
                    string5 = string5 + "\n" + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                this.M0.setText(string5);
                if (th instanceof IOException) {
                    this.N0.setText(R.string.error_network);
                    this.N0.setVisibility(0);
                } else if (th instanceof ax.p151ka.f) {
                    this.N0.setText(getString(R.string.error_payment_server, String.valueOf(((ax.p151ka.f) th).q)));
                    this.N0.setVisibility(0);
                }
                break;
            case 70:
                I1(R.string.error_promo_code_invalid, z);
                break;
            case 80:
                I1(R.string.error_promo_code_not_found, z);
                break;
            case SubsamplingScaleImageView.ORIENTATION_90 /* 90 */:
                I1(R.string.error_payment_already_owned, z);
                l1.fine("payment already owned");
                break;
            case 100:
                I1(R.string.error_billing_unavailable, z);
                break;
        }
    }

    void I1(int i2, boolean z) {
        x.U(findViewById(android.R.id.content), i2, z ? -2 : 0).Y();
    }

    void J1(String str, boolean z) {
        x.V(findViewById(android.R.id.content), str, z ? -2 : 0).Y();
    }

    void K1(boolean z) {
        if (!ax.p031c2.g.g()) {
            setTitle(R.string.upgrade_to_premium);
            new m().i(new Void[0]);
            if (z) {
                r1(false);
                return;
            }
            return;
        }
        setTitle(R.string.title_premium);
        this.P0.setVisibility(0);
        this.S0.setVisibility(0);
        this.T0.setText(R.string.title_premium);
        this.Q0.setVisibility(8);
        this.R0.setVisibility(8);
        this.O0.setVisibility(8);
        if (ax.p031c2.g.j()) {
            this.T0.setVisibility(8);
            this.Z0.setVisibility(8);
        } else {
            this.Z0.setText(getString(R.string.premium_expires_on, ax.p031c2.g.d(this)));
        }
        if (ax.p031c2.g.h()) {
            setTitle(R.string.payment_free_trial);
            this.T0.setText(R.string.payment_free_trial);
            this.b1.setVisibility(8);
        } else if (!ax.p031c2.g.i()) {
            this.b1.setVisibility(8);
        } else {
            if (ax.p031c2.g.j()) {
                this.b1.setVisibility(8);
                return;
            }
            if (ax.p031c2.g.k()) {
                this.T0.setText(R.string.payment_free_trial);
            }
            this.b1.setVisibility(0);
        }
    }

    void L1() {
        x.d0(w(), new u(), "promo", true);
    }

    @Override // ax.H1.u.b
    public void S(String str) {
        if (t1(str)) {
            G1();
        } else {
            B1(str);
        }
    }

    @Override // ax.p101h2.b
    public void V() {
        H1(1, null, false);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_payment);
        this.E0 = (Toolbar) findViewById(R.id.toolbar);
        this.F0 = (Button) findViewById(R.id.subscription_buy);
        this.G0 = (Button) findViewById(R.id.subscription_promo_code);
        this.H0 = (Button) findViewById(R.id.subscription_free_trial);
        this.I0 = (TextView) findViewById(R.id.subscription_desc);
        this.J0 = (Button) findViewById(R.id.onetime_buy);
        this.K0 = (Button) findViewById(R.id.onetime_promo_code);
        this.L0 = (Button) findViewById(R.id.onetime_free_trial);
        this.M0 = (TextView) findViewById(R.id.error_message);
        this.N0 = (TextView) findViewById(R.id.error_submessage);
        this.O0 = findViewById(R.id.progress);
        this.V0 = (TextView) findViewById(R.id.subscription_price);
        this.W0 = (TextView) findViewById(R.id.subscription_original_price);
        this.X0 = (TextView) findViewById(R.id.onetime_price);
        this.Y0 = (TextView) findViewById(R.id.onetime_original_price);
        this.P0 = findViewById(R.id.payment_container);
        this.a1 = findViewById(R.id.retry_button);
        this.U0 = findViewById(R.id.error_container);
        this.Q0 = findViewById(R.id.subscription_container);
        this.R0 = findViewById(R.id.onetime_container);
        this.S0 = findViewById(R.id.payment_expire_container);
        this.T0 = (TextView) findViewById(R.id.payment_expire_title);
        this.Z0 = (TextView) findViewById(R.id.payment_expired_on);
        this.b1 = findViewById(R.id.manage_subscription);
        boolean zM = ax.p031c2.d.v().M();
        this.h1 = zM;
        this.g1 = zM;
        S0(this.E0);
        if (ax.p031c2.g.g()) {
            setTitle(R.string.title_premium);
        } else {
            setTitle(R.string.upgrade_to_premium);
        }
        this.E0.setNavigationIcon(R.drawable.ic_clear_material);
        this.E0.setNavigationOnClickListener(new b());
        this.P0.setVisibility(8);
        this.F0.setOnClickListener(new c(1000L));
        this.G0.setOnClickListener(new d(1000L));
        this.H0.setOnClickListener(new e(1000L));
        this.J0.setOnClickListener(new f(1000L));
        this.K0.setOnClickListener(new g(1000L));
        this.L0.setOnClickListener(new h(1000L));
        this.a1.setOnClickListener(new i(1000L));
        this.b1.setOnClickListener(new j());
        if (ax.p031c2.g.h()) {
            K1(true);
            return;
        }
        try {
            AbstractC5846a abstractC5846aA = AbstractC5846a.c(this).b().c(this.k1).a();
            this.c1 = abstractC5846aA;
            abstractC5846aA.f(this);
        } catch (NoClassDefFoundError e2) {
            H1(1, null, false);
            ax.p338y1.d.b(e2);
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        AbstractC5846a abstractC5846a = this.c1;
        if (abstractC5846a != null) {
            try {
                abstractC5846a.a();
                this.c1 = null;
            } catch (NoClassDefFoundError unused) {
            }
        }
        super.onDestroy();
    }

    int p1(ax.p151ka.f fVar) {
        int i2 = fVar.q;
        if (i2 == 50300) {
            return 50;
        }
        if (i2 == 40401) {
            return 80;
        }
        if (i2 == 40003 || i2 == 40004) {
            return 70;
        }
        ax.p221pa.c.h().g().d("COUPON LICENSE SERVICE EXCEPTION UNKNOWN").m(fVar).h(Integer.valueOf(fVar.q)).i();
        return 1;
    }

    int q1(ax.p151ka.f fVar) {
        int i2 = fVar.q;
        if (i2 == 50300) {
            return 50;
        }
        if (i2 == 40001) {
            ax.p221pa.c.h().g().d("PAYMENT BAD SIGNATURE").m(fVar).i();
            return 30;
        }
        if (i2 == 40002) {
            ax.p221pa.c.h().g().d("PAYMENT ALREADY REVOKED").m(fVar).i();
            return 30;
        }
        ax.p221pa.c.h().g().d("PAYMENT LIST EXCEPTION").m(fVar).i();
        return 1;
    }

    void r1(final boolean z) {
        if (this.c1 == null) {
            return;
        }
        ax.O1.c.s().n(this.c1, new ax.O1.c.d() { // from class: ax.z1.d
            @Override // ax.O1.c.d
            public final void a(Object obj) {
                this.a.u1(z, (List) obj);
            }
        }, new ax.O1.c.d() { // from class: ax.z1.e
            @Override // ax.O1.c.d
            public final void a(Object obj) {
                PaymentActivity.v1((C5849d) obj);
            }
        });
    }

    void s1(InAppPurchaseDataSigned inAppPurchaseDataSigned, boolean z) {
        this.e1 = inAppPurchaseDataSigned;
        new k(z).i(new InAppPurchaseDataSigned[0]);
    }

    void x1() {
        Uri uri;
        if (ax.p031c2.g.b()) {
            uri = Uri.parse("https://play.google.com/store/account/subscriptions?package=com.alphainventor.filemanager&sku=" + ax.p031c2.g.f());
        } else {
            uri = Uri.parse("https://play.google.com/store/account/subscriptions");
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.setFlags(268435456);
        intent.setPackage("com.android.vending");
        try {
            C0734o.h0(this, intent);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(uri);
            intent2.setFlags(268435456);
            try {
                C0734o.h0(this, intent2);
            } catch (ActivityNotFoundException unused2) {
                Toast.makeText(this, R.string.no_application, 1).show();
            }
        }
    }

    void z1() {
        ax.p031c2.g.a(this);
        K1(true);
        ax.p045d2.g.a().e(new Intent("local.intent.action.LICENSE_STATUS_CHANGED"));
        ax.p338y1.a.k().o("payment", "payment_free_trial").e();
    }
}
