package com.alphainventor.filemanager.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import ax.p054d3.C1917w;
import ax.p086f3.C2014u;
import ax.p406z3.B;
import ax.p406z3.q;
import ax.p406z3.r;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.activity.PaymentActivity;
import com.alphainventor.filemanager.license.datatypes.LicenseByCoupon;
import com.alphainventor.filemanager.license.datatypes.ProductCatalogImpl;
import com.android.billingclient.api.AbstractC6251a;
import com.android.billingclient.api.C6253c;
import com.android.billingclient.api.C6254d;
import com.android.billingclient.api.C6255e;
import com.android.billingclient.api.C6256f;
import com.android.billingclient.api.Purchase;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.socialnmobile.commons.inapppurchase.billing.datatypes.InAppPurchaseDataSigned;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class PaymentActivity extends ax.V2.c implements ax.D3.b, C1917w.b {
    private static final Logger O0 = ax.U2.g.a(PaymentActivity.class);
    private TextView A0;
    private TextView B0;
    private TextView C0;
    private View D0;
    private View E0;
    private AbstractC6251a F0;
    private n G0;
    private InAppPurchaseDataSigned H0;
    private l I0;
    private boolean J0;
    private boolean K0;
    private boolean L0;
    private final Handler M0 = new Handler(Looper.getMainLooper());
    private final ax.D3.e N0 = new a();
    private ScrollView X;
    private Button Y;
    private Button Z;
    private Button k0;
    private TextView l0;
    private Button m0;
    private Button n0;
    private Button o0;
    private TextView p0;
    private Toolbar q;
    private TextView q0;
    private View r0;
    private View s0;
    private View t0;
    private View u0;
    private View v0;
    private TextView w0;
    private View x0;
    private TextView y0;
    private TextView z0;

    class a implements ax.D3.e {
        a() {
        }

        @Override // ax.D3.e
        public void a(C6254d c6254d, List<Purchase> list) {
            int iC = c6254d.c();
            if (iC == 1) {
                return;
            }
            if (iC == 7) {
                PaymentActivity.this.B0(90, null, false);
                PaymentActivity.this.n0(true);
                return;
            }
            int iE0 = PaymentActivity.this.e0(c6254d, "PurchasesUpdatedListener");
            if (iE0 != 0) {
                PaymentActivity.O0.warning("onPurchasesUpdated: ResponseCode=" + iC);
                PaymentActivity.this.B0(iE0, null, false);
                return;
            }
            if (list == null) {
                return;
            }
            PaymentActivity.O0.warning("onPurchasesUpdated: " + list.size());
            for (Purchase purchase : list) {
                PaymentActivity.O0.warning("on purchase updated: " + purchase);
                InAppPurchaseDataSigned inAppPurchaseDataSigned = new InAppPurchaseDataSigned();
                inAppPurchaseDataSigned.purchaseData = purchase.a();
                inAppPurchaseDataSigned.signature = purchase.c();
                PaymentActivity.this.o0(inAppPurchaseDataSigned, true);
            }
        }
    }

    class b extends ax.p182l3.c {
        b() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.finish();
        }
    }

    class c extends ax.p182l3.c {
        c(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.w0();
        }
    }

    class d extends ax.p182l3.c {
        d(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.F0();
        }
    }

    class e extends ax.p182l3.c {
        e(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.t0();
        }
    }

    class f extends ax.p182l3.c {
        f(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.u0();
        }
    }

    class g extends ax.p182l3.c {
        g(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.F0();
        }
    }

    class h extends ax.p182l3.c {
        h(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.t0();
        }
    }

    class i extends ax.p182l3.c {
        i(long j) {
            super(j);
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.x0();
        }
    }

    class j extends ax.p182l3.c {
        j() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PaymentActivity.this.r0();
        }
    }

    class k extends q<InAppPurchaseDataSigned, Integer, ax.p190lb.b<ax.p222nb.e>> {
        boolean h;
        Throwable i;

        k(boolean z) {
            super(q.e.HIGHER);
            this.h = z;
        }

        @Override // ax.p406z3.q
        protected void r() {
            PaymentActivity.this.r0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public ax.p190lb.b<ax.p222nb.e> g(InAppPurchaseDataSigned... inAppPurchaseDataSignedArr) {
            String str;
            if (PaymentActivity.this.L0) {
                return null;
            }
            try {
                return ax.p166k3.e.w().z().c(ProductCatalogImpl.getProductTypeStatic(PaymentActivity.this.H0.getUnverifiedPurchaseData(ax.p166k3.e.w().r()).productId).q, PaymentActivity.this.H0);
            } catch (ax.p222nb.a e) {
                this.i = e;
                e.printStackTrace();
                ax.p302sb.c.h().d("HANDLE PURCHASE ERROR 5").m(e).i();
                return null;
            } catch (ax.p222nb.d e2) {
                e2.printStackTrace();
                this.i = e2;
                ax.p302sb.c.h().d("HANDLE PURCHASE ERROR 2").m(e2).i();
                return null;
            } catch (ax.p222nb.f e3) {
                e3.printStackTrace();
                this.i = e3;
                if (e3.q == 40001) {
                    if (B.K(PaymentActivity.this.getApplicationContext()) || r.g()) {
                        PaymentActivity.this.L0 = true;
                    }
                    str = "rooted:" + r.g() + ",sign:";
                    try {
                        Iterator<String> it = B.p(PaymentActivity.this.getPackageManager().getPackageInfo(PaymentActivity.this.getPackageName(), 64).signatures).iterator();
                        while (it.hasNext()) {
                            str = str + it.next() + ":::";
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    str = null;
                }
                ax.p302sb.c.h().d("HANDLE PURCHASE ERROR 4").m(e3).h(str).i();
                return null;
            } catch (ax.p222nb.g e4) {
                e4.printStackTrace();
                this.i = e4;
                ax.p302sb.c.h().d("HANDLE PURCHASE ERROR 3").m(e4).i();
                return null;
            } catch (IOException e5) {
                e5.printStackTrace();
                this.i = e5;
                ax.p302sb.c.h().d("HANDLE PURCHASE ERROR 1").m(e5).i();
                return null;
            } catch (IllegalStateException e6) {
                this.i = e6;
                e6.printStackTrace();
                ax.p302sb.c.h().d("HANDLE PURCHASE ERROR 6").m(e6).i();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(ax.p190lb.b<ax.p222nb.e> bVar) {
            String str;
            PaymentActivity.this.r0.setVisibility(8);
            if (bVar == null) {
                if (PaymentActivity.this.L0) {
                    PaymentActivity.this.B0(40, this.i, false);
                    return;
                } else {
                    PaymentActivity.this.B0(60, this.i, false);
                    return;
                }
            }
            ax.p166k3.e.w().R(bVar);
            PaymentActivity.this.E0(false);
            if (this.h && ax.p390y3.h.g()) {
                if (ax.p390y3.h.i()) {
                    str = ax.p390y3.h.j() ? "onetime" : "subscription";
                } else {
                    str = "promocode";
                }
                ax.U2.a.k().o("payment", "payment_purchased").c("type", str).e();
            }
        }
    }

    class l extends q<Void, Integer, ax.p190lb.b<LicenseByCoupon>> {
        int h;
        String i;
        Throwable j;

        l(String str) {
            super(q.e.HIGHER);
            this.i = str;
        }

        @Override // ax.p406z3.q
        protected void r() {
            PaymentActivity.this.r0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public ax.p190lb.b<LicenseByCoupon> g(Void... voidArr) {
            ax.p118h3.c cVarZ = ax.p166k3.e.w().z();
            if (cVarZ == null) {
                this.h = 40;
                return null;
            }
            try {
                return cVarZ.b(this.i);
            } catch (ax.p222nb.d e) {
                e.printStackTrace();
                this.h = 1;
                this.j = e;
                return null;
            } catch (ax.p222nb.f e2) {
                e2.printStackTrace();
                this.h = PaymentActivity.this.f0(e2);
                this.j = e2;
                return null;
            } catch (ax.p222nb.g e3) {
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
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(ax.p190lb.b<LicenseByCoupon> bVar) {
            PaymentActivity.this.r0.setVisibility(8);
            if (bVar == null) {
                PaymentActivity.this.B0(this.h, this.j, false);
            } else {
                ax.p166k3.e.w().R(bVar);
                PaymentActivity.this.E0(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class m extends q<Void, Integer, ax.p150j3.a> {
        int h;
        Throwable i;

        class a implements ax.p166k3.e.InterfaceC0368e {
            final /* synthetic */ ax.p190lb.c a;
            final /* synthetic */ ax.p190lb.c b;
            final /* synthetic */ ax.p190lb.c c;
            final /* synthetic */ ax.p190lb.c d;

            a(ax.p190lb.c cVar, ax.p190lb.c cVar2, ax.p190lb.c cVar3, ax.p190lb.c cVar4) {
                this.a = cVar;
                this.b = cVar2;
                this.c = cVar3;
                this.d = cVar4;
            }

            public static /* synthetic */ void b(a aVar, C6254d c6254d, Map map, ax.p190lb.c cVar, ax.p190lb.c cVar2, ax.p190lb.c cVar3, ax.p190lb.c cVar4) {
                PaymentActivity.this.r0.setVisibility(8);
                c6254d.c();
                int iE0 = PaymentActivity.this.e0(c6254d, "skuDetailsListener");
                b bVar = null;
                if (iE0 != 0) {
                    PaymentActivity.this.B0(iE0, null, true);
                    return;
                }
                n nVar = new n(bVar);
                nVar.a = (C6256f) map.get(cVar);
                nVar.b = (C6256f) map.get(cVar2);
                nVar.c = (C6256f) map.get(cVar3);
                C6256f c6256f = (C6256f) map.get(cVar4);
                nVar.d = c6256f;
                if (nVar.a != null && nVar.b != null && nVar.c != null && c6256f != null) {
                    PaymentActivity.this.G0 = nVar;
                    PaymentActivity.this.s0.setVisibility(0);
                    PaymentActivity.this.v0.setVisibility(8);
                    PaymentActivity.this.t0.setVisibility(0);
                    if (ax.p390y3.d.v().x()) {
                        PaymentActivity.this.u0.setVisibility(0);
                    } else {
                        PaymentActivity.this.u0.setVisibility(8);
                    }
                    PaymentActivity.this.z0(nVar);
                    PaymentActivity.this.y0(nVar);
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
                ax.p302sb.c.h().b("Get SkuDetails Error").h(sb.toString()).i();
                PaymentActivity.this.B0(40, null, true);
            }

            @Override // ax.p166k3.e.InterfaceC0368e
            public void a(final C6254d c6254d, final Map<ax.p190lb.c, C6256f> map) {
                Handler handler = PaymentActivity.this.M0;
                final ax.p190lb.c cVar = this.a;
                final ax.p190lb.c cVar2 = this.b;
                final ax.p190lb.c cVar3 = this.c;
                final ax.p190lb.c cVar4 = this.d;
                handler.post(new Runnable() { // from class: com.alphainventor.filemanager.activity.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        PaymentActivity.m.a.b(this.q, c6254d, map, cVar, cVar2, cVar3, cVar4);
                    }
                });
            }
        }

        m() {
            super(q.e.HIGHER);
        }

        @Override // ax.p406z3.q
        protected void r() {
            PaymentActivity.this.r0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public ax.p150j3.a g(Void[] voidArr) {
            ax.p118h3.c cVarZ = ax.p166k3.e.w().z();
            if (cVarZ == null) {
                this.h = 40;
                return null;
            }
            try {
                return cVarZ.f();
            } catch (ax.p222nb.d e) {
                this.h = 1;
                this.i = e;
                return null;
            } catch (ax.p222nb.f e2) {
                this.h = PaymentActivity.this.g0(e2);
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
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(ax.p150j3.a aVar) {
            PaymentActivity.this.r0.setVisibility(8);
            if (ax.p390y3.h.g()) {
                return;
            }
            if (aVar == null) {
                PaymentActivity.this.B0(this.h, this.i, true);
                return;
            }
            ArrayList arrayList = new ArrayList();
            ax.p190lb.c premiumBasicYearly = aVar.getPremiumBasicYearly();
            ax.p190lb.c premiumBasicYearlyDiscount = aVar.getPremiumBasicYearlyDiscount();
            if (premiumBasicYearly != null) {
                arrayList.add(premiumBasicYearly);
            }
            if (premiumBasicYearlyDiscount != null) {
                arrayList.add(premiumBasicYearlyDiscount);
            }
            ax.p190lb.c premiumBasicOnetime = aVar.getPremiumBasicOnetime();
            ax.p190lb.c premiumBasicOnetimeDiscount = aVar.getPremiumBasicOnetimeDiscount();
            if (premiumBasicOnetime != null) {
                arrayList.add(premiumBasicOnetime);
            }
            if (premiumBasicOnetimeDiscount != null) {
                arrayList.add(premiumBasicOnetimeDiscount);
            }
            if (PaymentActivity.this.F0 == null) {
                PaymentActivity.O0.warning("No BillingClient");
                return;
            }
            ax.p166k3.e eVarW = ax.p166k3.e.w();
            PaymentActivity.this.r0.setVisibility(0);
            eVarW.O(PaymentActivity.this.F0, arrayList, new a(premiumBasicYearly, premiumBasicYearlyDiscount, premiumBasicOnetime, premiumBasicOnetimeDiscount));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class n {
        C6256f a;
        C6256f b;
        C6256f c;
        C6256f d;

        private n() {
        }

        /* synthetic */ n(b bVar) {
            this();
        }
    }

    private void A0() {
        this.J0 = true;
        this.K0 = true;
        if (System.currentTimeMillis() <= 1546257600000L && this.G0 != null) {
            ax.U2.a.k().o("general", "show_discount").e();
            ax.p302sb.c.h().g().e("showDiscount").i();
            z0(this.G0);
            y0(this.G0);
        }
    }

    public static /* synthetic */ void H(PaymentActivity paymentActivity, boolean z, List list) {
        paymentActivity.getClass();
        if (list.size() <= 0) {
            if (z) {
                ax.p302sb.c.h().g().d("PAYMENT ALREADY OWNED BUT PURCHASES 0").k().i();
                return;
            }
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            InAppPurchaseDataSigned inAppPurchaseDataSigned = (InAppPurchaseDataSigned) it.next();
            try {
                if (inAppPurchaseDataSigned.getUnverifiedPurchaseData(ax.p166k3.e.w().r()).purchaseState == 0) {
                    paymentActivity.o0(inAppPurchaseDataSigned, false);
                    return;
                }
                continue;
            } catch (ax.p222nb.a e2) {
                ax.p302sb.c.h().g().d("PAYMENT ALREADY PAYED : SERIALIZE ").m(e2).h(inAppPurchaseDataSigned.purchaseData).i();
            }
        }
    }

    public static /* synthetic */ void J(PaymentActivity paymentActivity, C6254d c6254d) {
        paymentActivity.getClass();
        int iC = c6254d.c();
        String strA = c6254d.a();
        if (iC == 0) {
            paymentActivity.E0(true);
            return;
        }
        O0.severe("BillingClient Setup Error : (" + iC + ") " + strA);
        if (iC == 3) {
            paymentActivity.B0(100, null, true);
            return;
        }
        if (iC != 6) {
            ax.p302sb.c.h().d("BillingClient Setup").h("(" + iC + ") " + strA).k().i();
        }
        paymentActivity.B0(1, null, true);
    }

    public static /* synthetic */ void K(C6254d c6254d) {
        int iC = c6254d.c();
        if (iC == 3) {
            return;
        }
        ax.p302sb.c.h().g().d("PAYMENT ALREADY PAYED : BILLING").a("billing response code: " + iC).i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e0(C6254d c6254d, String str) {
        int iC = c6254d.c();
        String strA = c6254d.a();
        if (iC != 12) {
            switch (iC) {
                case -3:
                case 2:
                    break;
                case -2:
                    ax.p302sb.c.h().g().b("PAYMENT FEATURE NOT SUPPORTED").h(str + " : " + strA).i();
                    return 1;
                case SubsamplingScaleImageView.ORIENTATION_USE_EXIF /* -1 */:
                    return 20;
                case 0:
                case 1:
                    return 0;
                case 3:
                    ax.p302sb.c.h().g().b("BILLING_UNAVAILABLE ERROR").h(str + " : " + strA).i();
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
                    ax.p302sb.c.h().g().b("UNKNOWN BILLING RESPONSE ERROR").h(str + " : " + strA).i();
                    return 1;
            }
        }
        return 10;
    }

    private String k0(C6256f c6256f) {
        String strD = c6256f.d();
        if (strD.equals("subs")) {
            return m0(c6256f);
        }
        if (strD.equals("inapp")) {
            return l0(c6256f);
        }
        return null;
    }

    private String l0(C6256f c6256f) {
        List<C6256f.b> listB = c6256f.b();
        if (listB == null) {
            return null;
        }
        Iterator<C6256f.b> it = listB.iterator();
        if (it.hasNext()) {
            return it.next().a();
        }
        return null;
    }

    private String m0(C6256f c6256f) {
        List<C6256f.e> listE = c6256f.e();
        if (listE == null) {
            return null;
        }
        Iterator<C6256f.e> it = listE.iterator();
        while (it.hasNext()) {
            Iterator<C6256f.c> it2 = it.next().b().a().iterator();
            if (it2.hasNext()) {
                return it2.next().a();
            }
        }
        return null;
    }

    private void p0() {
        this.q = (Toolbar) findViewById(R.id.toolbar);
        this.X = (ScrollView) findViewById(R.id.scroll_view);
        this.Y = (Button) findViewById(R.id.subscription_buy);
        this.Z = (Button) findViewById(R.id.subscription_promo_code);
        this.k0 = (Button) findViewById(R.id.subscription_free_trial);
        this.l0 = (TextView) findViewById(R.id.subscription_desc);
        this.m0 = (Button) findViewById(R.id.onetime_buy);
        this.n0 = (Button) findViewById(R.id.onetime_promo_code);
        this.o0 = (Button) findViewById(R.id.onetime_free_trial);
        this.p0 = (TextView) findViewById(R.id.error_message);
        this.q0 = (TextView) findViewById(R.id.error_submessage);
        this.r0 = findViewById(R.id.progress);
        this.y0 = (TextView) findViewById(R.id.subscription_price);
        this.z0 = (TextView) findViewById(R.id.subscription_original_price);
        this.A0 = (TextView) findViewById(R.id.onetime_price);
        this.B0 = (TextView) findViewById(R.id.onetime_original_price);
        this.s0 = findViewById(R.id.payment_container);
        this.D0 = findViewById(R.id.retry_button);
        this.x0 = findViewById(R.id.error_container);
        this.t0 = findViewById(R.id.subscription_container);
        this.u0 = findViewById(R.id.onetime_container);
        this.v0 = findViewById(R.id.payment_expire_container);
        this.w0 = (TextView) findViewById(R.id.payment_expire_title);
        this.C0 = (TextView) findViewById(R.id.payment_expired_on);
        this.E0 = findViewById(R.id.manage_subscription);
        boolean zM = ax.p390y3.d.v().M();
        this.K0 = zM;
        this.J0 = zM;
        setSupportActionBar(this.q);
        if (ax.p390y3.h.g()) {
            setTitle(R.string.title_premium);
        } else {
            setTitle(R.string.upgrade_to_premium);
        }
        this.q.setNavigationIcon(R.drawable.ic_clear_material);
        this.q.setNavigationOnClickListener(new b());
        this.s0.setVisibility(8);
        this.Y.setOnClickListener(new c(1000L));
        this.Z.setOnClickListener(new d(1000L));
        this.k0.setOnClickListener(new e(1000L));
        this.m0.setOnClickListener(new f(1000L));
        this.n0.setOnClickListener(new g(1000L));
        this.o0.setOnClickListener(new h(1000L));
        this.D0.setOnClickListener(new i(1000L));
        this.E0.setOnClickListener(new j());
        if (ax.p390y3.h.h()) {
            E0(true);
            return;
        }
        try {
            AbstractC6251a abstractC6251aA = AbstractC6251a.c(this).b(C6255e.c().b().a()).c(this.N0).a();
            this.F0 = abstractC6251aA;
            abstractC6251aA.f(this);
        } catch (NoClassDefFoundError e2) {
            B0(1, null, false);
            ax.U2.d.b(e2);
        }
    }

    private boolean q0(String str) {
        return "FILE-PLUS-DISC-OUNT".equals(str);
    }

    private void s0(C6256f c6256f) {
        C6254d c6254dB = this.F0.b(this, C6253c.a().b(Collections.singletonList(C6253c.b.a().c(c6256f).b(h0(c6256f)).a())).a());
        if (c6254dB.c() == 7) {
            B0(90, null, false);
            n0(true);
        } else {
            int iE0 = e0(c6254dB, "proceedBuy");
            if (iE0 != 0) {
                B0(iE0, null, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0(n nVar) {
        if (ax.p390y3.d.v().x()) {
            String strK0 = k0(nVar.d);
            String strK1 = k0(nVar.c);
            if (!this.J0) {
                this.A0.setText(strK1);
                this.B0.setVisibility(4);
                return;
            }
            this.A0.setText(strK0);
            this.B0.setText(strK1);
            TextView textView = this.B0;
            textView.setPaintFlags(textView.getPaintFlags() | 16);
            this.B0.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0(n nVar) {
        String strK0 = k0(nVar.b);
        String strK1 = k0(nVar.a);
        if (!this.K0) {
            this.l0.setText(getString(R.string.payment_subscription_description, strK1));
            this.y0.setText(strK1);
            this.z0.setVisibility(4);
        } else {
            this.l0.setText(getString(R.string.payment_subscription_description, strK0));
            this.y0.setText(strK0);
            this.z0.setText(strK1);
            TextView textView = this.z0;
            textView.setPaintFlags(textView.getPaintFlags() | 16);
            this.z0.setVisibility(0);
        }
    }

    void B0(int i2, Throwable th, boolean z) {
        switch (i2) {
            case 1:
                String string = getString(R.string.error_unknown_try_later);
                if (ax.p390y3.k.p(this) && th != null) {
                    string = string + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                D0(string, z);
                break;
            case 10:
                String string2 = getString(R.string.error_network);
                if (ax.p390y3.k.p(this) && th != null) {
                    string2 = string2 + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                D0(string2, z);
                break;
            case 20:
                C0(R.string.error_payment, z);
                break;
            case 30:
                String string3 = getString(R.string.error_payment_server, String.valueOf(th instanceof ax.p222nb.f ? ((ax.p222nb.f) th).q : 0));
                if (ax.p390y3.k.p(this) && th != null) {
                    string3 = string3 + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                D0(string3, z);
                break;
            case 40:
                String string4 = getString(R.string.error_payment);
                if (ax.p390y3.k.p(this) && th != null) {
                    string4 = string4 + " : " + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                D0(string4, z);
                break;
            case 50:
                C0(R.string.error_payment_server_down, z);
                break;
            case 60:
                this.D0.setVisibility(0);
                this.s0.setVisibility(8);
                this.x0.setVisibility(0);
                String string5 = getString(R.string.error_payment_ok_but_failed);
                if (ax.p390y3.k.p(this) && th != null) {
                    string5 = string5 + "\n" + th.getClass().getSimpleName() + " : " + th.getMessage();
                }
                this.p0.setText(string5);
                if (th instanceof IOException) {
                    this.q0.setText(R.string.error_network);
                    this.q0.setVisibility(0);
                } else if (th instanceof ax.p222nb.f) {
                    this.q0.setText(getString(R.string.error_payment_server, String.valueOf(((ax.p222nb.f) th).q)));
                    this.q0.setVisibility(0);
                }
                break;
            case 70:
                C0(R.string.error_promo_code_invalid, z);
                break;
            case 80:
                C0(R.string.error_promo_code_not_found, z);
                break;
            case SubsamplingScaleImageView.ORIENTATION_90 /* 90 */:
                C0(R.string.error_payment_already_owned, z);
                O0.fine("payment already owned");
                break;
            case 100:
                C0(R.string.error_billing_unavailable, z);
                break;
        }
    }

    void C0(int i2, boolean z) {
        B.S(findViewById(android.R.id.content), i2, z ? -2 : 0).a0();
    }

    void D0(String str, boolean z) {
        B.T(findViewById(android.R.id.content), str, z ? -2 : 0).a0();
    }

    void E0(boolean z) {
        if (!ax.p390y3.h.g()) {
            setTitle(R.string.upgrade_to_premium);
            new m().i(new Void[0]);
            if (z) {
                n0(false);
                return;
            }
            return;
        }
        setTitle(R.string.title_premium);
        this.s0.setVisibility(0);
        this.v0.setVisibility(0);
        this.w0.setText(R.string.title_premium);
        this.t0.setVisibility(8);
        this.u0.setVisibility(8);
        this.r0.setVisibility(8);
        if (ax.p390y3.h.j()) {
            this.w0.setVisibility(8);
            this.C0.setVisibility(8);
        } else {
            this.C0.setText(getString(R.string.premium_expires_on, ax.p390y3.h.d(this)));
        }
        if (ax.p390y3.h.h()) {
            setTitle(R.string.payment_free_trial);
            this.w0.setText(R.string.payment_free_trial);
            this.E0.setVisibility(8);
        } else if (!ax.p390y3.h.i()) {
            this.E0.setVisibility(8);
        } else {
            if (ax.p390y3.h.j()) {
                this.E0.setVisibility(8);
                return;
            }
            if (ax.p390y3.h.k()) {
                this.w0.setText(R.string.payment_free_trial);
            }
            this.E0.setVisibility(0);
        }
    }

    void F0() {
        B.d0(getSupportFragmentManager(), new C1917w(), "promo", true);
    }

    int f0(ax.p222nb.f fVar) {
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
        ax.p302sb.c.h().g().d("COUPON LICENSE SERVICE EXCEPTION UNKNOWN").m(fVar).h(Integer.valueOf(fVar.q)).i();
        return 1;
    }

    int g0(ax.p222nb.f fVar) {
        int i2 = fVar.q;
        if (i2 == 50300) {
            return 50;
        }
        if (i2 == 40001) {
            ax.p302sb.c.h().g().d("PAYMENT BAD SIGNATURE").m(fVar).i();
            return 30;
        }
        if (i2 == 40002) {
            ax.p302sb.c.h().g().d("PAYMENT ALREADY REVOKED").m(fVar).i();
            return 30;
        }
        ax.p302sb.c.h().g().d("PAYMENT LIST EXCEPTION").m(fVar).i();
        return 1;
    }

    String h0(C6256f c6256f) {
        String strD = c6256f.d();
        if (strD.equals("subs")) {
            return j0(c6256f);
        }
        if (strD.equals("inapp")) {
            return i0(c6256f);
        }
        throw new IllegalArgumentException();
    }

    String i0(C6256f c6256f) {
        List<C6256f.b> listB = c6256f.b();
        if (listB == null) {
            throw new IllegalArgumentException();
        }
        Iterator<C6256f.b> it = listB.iterator();
        while (it.hasNext()) {
            String strB = it.next().b();
            if (strB != null) {
                return strB;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        return false;
    }

    String j0(C6256f c6256f) {
        List<C6256f.e> listE = c6256f.e();
        if (listE == null) {
            throw new IllegalArgumentException();
        }
        Iterator<C6256f.e> it = listE.iterator();
        if (it.hasNext()) {
            return it.next().a();
        }
        throw new IllegalArgumentException();
    }

    void n0(final boolean z) {
        if (this.F0 == null) {
            return;
        }
        ax.p166k3.e.w().q(this.F0, new ax.k3.e.b() { // from class: ax.V2.d
            @Override // ax.k3.e.b
            public final void accept(Object obj) {
                PaymentActivity.H(this.a, z, (List) obj);
            }
        }, new ax.k3.e.b() { // from class: ax.V2.e
            @Override // ax.k3.e.b
            public final void accept(Object obj) {
                PaymentActivity.K((C6254d) obj);
            }
        });
    }

    @Override // ax.D3.b
    public void o(final C6254d c6254d) {
        this.M0.post(new Runnable() { // from class: ax.V2.f
            @Override // java.lang.Runnable
            public final void run() {
                PaymentActivity.J(this.q, c6254d);
            }
        });
    }

    void o0(InAppPurchaseDataSigned inAppPurchaseDataSigned, boolean z) {
        this.H0 = inAppPurchaseDataSigned;
        new k(z).i(new InAppPurchaseDataSigned[0]);
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
        if (z) {
            return;
        }
        this.X.setClipToPadding(false);
        this.X.setPadding(0, 0, 0, bVar.d);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_payment);
        p0();
        onSetContentView(false);
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        AbstractC6251a abstractC6251a = this.F0;
        if (abstractC6251a != null) {
            try {
                abstractC6251a.a();
                this.F0 = null;
            } catch (NoClassDefFoundError unused) {
            }
        }
        super.onDestroy();
    }

    void r0() {
        Uri uri;
        if (ax.p390y3.h.b()) {
            uri = Uri.parse("https://play.google.com/store/account/subscriptions?package=com.alphainventor.filemanager&sku=" + ax.p390y3.h.f());
        } else {
            uri = Uri.parse("https://play.google.com/store/account/subscriptions");
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.setFlags(268435456);
        intent.setPackage("com.android.vending");
        try {
            C2014u.o0(this, intent);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(uri);
            intent2.setFlags(268435456);
            try {
                C2014u.o0(this, intent2);
            } catch (ActivityNotFoundException unused2) {
                Toast.makeText(this, R.string.no_application, 1).show();
            }
        }
    }

    @Override // ax.p054d3.C1917w.b
    public void s(String str) {
        if (q0(str)) {
            A0();
        } else {
            v0(str);
        }
    }

    @Override // ax.D3.b
    public void t() {
        B0(1, null, false);
    }

    void t0() {
        ax.p390y3.h.a(this);
        E0(true);
        ax.p406z3.g.a().e(new Intent("local.intent.action.LICENSE_STATUS_CHANGED"));
        ax.U2.a.k().o("payment", "payment_free_trial").e();
    }

    void u0() {
        s0(this.J0 ? this.G0.d : this.G0.c);
    }

    void v0(String str) {
        if (q.n(this.I0)) {
            return;
        }
        l lVar = new l(str);
        this.I0 = lVar;
        lVar.i(new Void[0]);
    }

    void w0() {
        s0(this.K0 ? this.G0.b : this.G0.a);
    }

    void x0() {
        o0(this.H0, true);
    }
}
