package com.android.billingclient.api;

import android.text.TextUtils;
import ax.I6.C1216w;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.billingclient.api.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6253c {
    private boolean a;
    private String b;
    private String c;
    private C0558c d;
    private ax.I6.J e;
    private ArrayList f;
    private boolean g;

    /* JADX INFO: renamed from: com.android.billingclient.api.c$a */
    public static class a {
        private String a;
        private String b;
        private List c;
        private ArrayList d;
        private boolean e;
        private C0558c.a f;

        /* synthetic */ a(ax.D3.l lVar) {
            C0558c.a aVarA = C0558c.a();
            C0558c.a.b(aVarA);
            this.f = aVarA;
        }

        public C6253c a() {
            ArrayList arrayList = this.d;
            boolean z = true;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            List list = this.c;
            boolean z3 = (list == null || list.isEmpty()) ? false : true;
            if (!z2 && !z3) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            }
            if (z2 && z3) {
                throw new IllegalArgumentException("Set SkuDetails or ProductDetailsParams, not both.");
            }
            ax.D3.l lVar = null;
            if (!z2) {
                List list2 = this.c;
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        if (((b) it.next()) == null) {
                            throw new IllegalArgumentException("ProductDetailsParams cannot be null.");
                        }
                    }
                }
            } else {
                if (this.d.contains(null)) {
                    throw new IllegalArgumentException("SKU cannot be null.");
                }
                if (this.d.size() > 1) {
                    SkuDetails skuDetails = (SkuDetails) this.d.get(0);
                    String strB = skuDetails.b();
                    ArrayList arrayList2 = this.d;
                    int size = arrayList2.size();
                    for (int i = 0; i < size; i++) {
                        SkuDetails skuDetails2 = (SkuDetails) arrayList2.get(i);
                        if (!strB.equals("play_pass_subs") && !skuDetails2.b().equals("play_pass_subs") && !strB.equals(skuDetails2.b())) {
                            throw new IllegalArgumentException("SKUs should have the same type.");
                        }
                    }
                    String strF = skuDetails.f();
                    ArrayList arrayList3 = this.d;
                    int size2 = arrayList3.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        SkuDetails skuDetails3 = (SkuDetails) arrayList3.get(i2);
                        if (!strB.equals("play_pass_subs") && !skuDetails3.b().equals("play_pass_subs") && !strF.equals(skuDetails3.f())) {
                            throw new IllegalArgumentException("All SKUs must have the same package name.");
                        }
                    }
                }
            }
            C6253c c6253c = new C6253c(lVar);
            if ((!z2 || ((SkuDetails) this.d.get(0)).f().isEmpty()) && (!z3 || ((b) this.c.get(0)).b().f().isEmpty())) {
                z = false;
            }
            c6253c.a = z;
            c6253c.b = this.a;
            c6253c.c = this.b;
            c6253c.d = this.f.a();
            ArrayList arrayList4 = this.d;
            c6253c.f = arrayList4 != null ? new ArrayList(arrayList4) : new ArrayList();
            c6253c.g = this.e;
            List list3 = this.c;
            c6253c.e = list3 != null ? ax.I6.J.q(list3) : ax.I6.J.s();
            return c6253c;
        }

        public a b(List<b> list) {
            this.c = new ArrayList(list);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.c$b */
    public static final class b {
        private final C6256f a;
        private final String b;

        /* JADX INFO: renamed from: com.android.billingclient.api.c$b$a */
        public static class a {
            private C6256f a;
            private String b;

            /* synthetic */ a(ax.D3.l lVar) {
            }

            public b a() {
                C1216w.c(this.a, "ProductDetails is required for constructing ProductDetailsParams.");
                return new b(this, null);
            }

            public a b(String str) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("offerToken can not be empty");
                }
                this.b = str;
                return this;
            }

            public a c(C6256f c6256f) {
                this.a = c6256f;
                if (c6256f.a() != null) {
                    c6256f.a().getClass();
                    C6256f.b bVarA = c6256f.a();
                    if (bVarA.b() != null) {
                        this.b = bVarA.b();
                    }
                }
                return this;
            }
        }

        /* synthetic */ b(a aVar, ax.D3.l lVar) {
            this.a = aVar.a;
            this.b = aVar.b;
        }

        public static a a() {
            return new a(null);
        }

        public final C6256f b() {
            return this.a;
        }

        public final String c() {
            return this.b;
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.c$c, reason: collision with other inner class name */
    public static class C0558c {
        private String a;
        private String b;
        private int c = 0;

        /* JADX INFO: renamed from: com.android.billingclient.api.c$c$a */
        public static class a {
            private String a;
            private String b;
            private boolean c;
            private int d = 0;

            /* synthetic */ a(ax.D3.l lVar) {
            }

            static /* synthetic */ a b(a aVar) {
                aVar.c = true;
                return aVar;
            }

            public C0558c a() {
                boolean z = true;
                ax.D3.l lVar = null;
                if (TextUtils.isEmpty(this.a) && TextUtils.isEmpty(null)) {
                    z = false;
                }
                boolean zIsEmpty = TextUtils.isEmpty(this.b);
                if (z && !zIsEmpty) {
                    throw new IllegalArgumentException("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                }
                if (!this.c && !z && zIsEmpty) {
                    throw new IllegalArgumentException("Old SKU purchase information(token/id) or original external transaction id must be provided.");
                }
                C0558c c0558c = new C0558c(lVar);
                c0558c.a = this.a;
                c0558c.c = this.d;
                c0558c.b = this.b;
                return c0558c;
            }
        }

        /* synthetic */ C0558c(ax.D3.l lVar) {
        }

        public static a a() {
            return new a(null);
        }

        final int b() {
            return this.c;
        }

        final String c() {
            return this.a;
        }

        final String d() {
            return this.b;
        }
    }

    /* synthetic */ C6253c(ax.D3.l lVar) {
    }

    public static a a() {
        return new a(null);
    }

    public int b() {
        return 0;
    }

    public final int c() {
        return this.d.b();
    }

    public long d() {
        return 0L;
    }

    final C6254d e() {
        C6256f.b next;
        if (this.e.isEmpty()) {
            return H.i;
        }
        b bVar = (b) this.e.get(0);
        for (int i = 1; i < this.e.size(); i++) {
            b bVar2 = (b) this.e.get(i);
            if (!bVar2.b().d().equals(bVar.b().d()) && !bVar2.b().d().equals("play_pass_subs")) {
                return H.a(5, "All products should have same ProductType.");
            }
        }
        String strF = bVar.b().f();
        HashMap map = new HashMap();
        HashSet<String> hashSet = new HashSet();
        ax.I6.J j = this.e;
        int size = j.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar3 = (b) j.get(i2);
            if (bVar3.b().e() != null && bVar3.c() == null) {
                return H.a(5, String.format("offerToken is required for constructing ProductDetailsParams for subscriptions. Missing value for product id: %s", bVar3.b().c()));
            }
            if (map.containsKey(bVar3.b().c())) {
                return H.a(5, String.format("ProductId can not be duplicated. Invalid product id: %s.", bVar3.b().c()));
            }
            map.put(bVar3.b().c(), bVar3);
            if (!bVar.b().d().equals("play_pass_subs") && !bVar3.b().d().equals("play_pass_subs") && !strF.equals(bVar3.b().f())) {
                return H.a(5, "All products must have the same package name.");
            }
        }
        for (String str : hashSet) {
            if (map.containsKey(str)) {
                return H.a(5, String.format("OldProductId must not be one of the products to be purchased. Invalid old product id: %s.", str));
            }
        }
        List<C6256f.b> listB = bVar.b().b();
        String strC = bVar.c();
        if (strC != null && listB != null) {
            Iterator<C6256f.b> it = listB.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!strC.equals(next.b()));
            if (next != null && next.c() != null) {
                return H.a(5, "Both autoPayDetails and autoPayBalanceThreshold is required for constructing ProductDetailsParams for autopay.");
            }
        }
        return H.i;
    }

    public final String f() {
        return this.b;
    }

    public final String g() {
        return this.c;
    }

    public String h() {
        return null;
    }

    public final String i() {
        return this.d.c();
    }

    public final String j() {
        return this.d.d();
    }

    public final ArrayList k() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f);
        return arrayList;
    }

    public final List l() {
        return this.e;
    }

    public final boolean t() {
        return this.g;
    }

    final boolean u() {
        if (this.b != null || this.c != null || this.d.d() != null || this.d.b() != 0 || this.a || this.g) {
            return true;
        }
        ax.I6.J j = this.e;
        if (j != null) {
            int size = j.size();
            for (int i = 0; i < size; i++) {
            }
        }
        return false;
    }
}
