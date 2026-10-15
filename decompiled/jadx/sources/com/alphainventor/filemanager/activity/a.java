package com.alphainventor.filemanager.activity;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.e;
import ax.E1.I;
import ax.E1.P;
import ax.E1.u;
import ax.G1.m;
import ax.H1.E;
import ax.I1.C0711b;
import ax.J1.C0734o;
import ax.J1.F;
import ax.J1.V;
import ax.K1.AbstractC0762l;
import ax.K1.AbstractC0775z;
import ax.K1.InterfaceC0767q;
import ax.P5.C1452Re;
import ax.p015b0.A0;
import ax.p015b0.Y;
import ax.p031c2.i;
import ax.p031c2.k;
import ax.p045d2.v;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import ax.p221pa.c;
import ax.p338y1.f;
import ax.p338y1.g;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.C5832f;
import com.alphainventor.filemanager.file.C5845t;
import com.alphainventor.filemanager.service.CommandService;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ActivityC5571c implements ax.p352z1.a, InterfaceC0767q {
    private static final Logger L0 = g.a(a.class);
    private static boolean M0;
    private f E0;
    private int F0;
    private String G0;
    private long I0;
    private boolean J0;
    private boolean H0 = true;
    private BroadcastReceiver K0 = new C0493a();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.a$a, reason: collision with other inner class name */
    class C0493a extends BroadcastReceiver {
        C0493a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("local.intent.action.THEME_CHANGED".equals(intent.getAction())) {
                ax.O.b.q(a.this);
            }
        }
    }

    class b implements View.OnAttachStateChangeListener {
        final /* synthetic */ View q;

        b(View view) {
            this.q = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            a.this.Y0(this.q);
            boolean unused = a.M0 = true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y0(View view) {
        int iF;
        A0 a0J = Y.J(view);
        if (a0J != null && (iF = x.f(this, a0J.f(A0.m.d()).d)) > 10 && iF < 25) {
            i.p(this, k.a.DRAWER_ONLY_HOME);
        }
    }

    /* JADX WARN: Code duplicated, block: B:89:0x0171  */
    @TargetApi(C1452Re.zzm)
    private void d1(int i, Intent intent) {
        int i2;
        boolean z;
        f fVarD = this.E0;
        int i3 = this.F0;
        String strW = this.G0;
        F fA = fVarD != null ? F.a(fVarD, i3) : null;
        a1();
        if (fA == null) {
            fA = e1(intent);
            if (fA != null) {
                fVarD = fA.d();
                fA.b();
            }
            if (fA == null) {
                return;
            }
        }
        F f = fA;
        if (f == F.e && strW == null && intent != null && intent.getData() != null) {
            try {
                strW = C5832f.w(u.g(intent.getData()));
                if (strW == null) {
                    return;
                }
            } catch (IllegalArgumentException unused) {
                return;
            }
        }
        String str = strW;
        if (intent != null && intent.getData() != null && intent.getData().getAuthority() != null) {
            String authority = intent.getData().getAuthority();
            if (!"com.android.externalstorage.documents".equals(authority) && !I.m() && !"com.android.providers.downloads.documents".equals(authority)) {
                c.h().g().b("Unknown external storage authority").h("authority:" + authority).i();
            }
        }
        if (i == -1 && intent != null && intent.getData() != null && C5832f.C(this, f, str, intent.getData())) {
            try {
                u.s(this, intent);
                if (!ax.G1.i.D().u0(f) || str == null) {
                    C5832f.J(this, f, str, intent.getData());
                } else {
                    m.b(this).a(intent.getData());
                }
                g1();
                return;
            } catch (SecurityException e) {
                e.printStackTrace();
                c.h().d("TAKE PERSITABLE PERMISSION ERROR!").m(e).i();
                return;
            }
        }
        if (i != -1 || intent == null || intent.getData() == null) {
            if (i == 0 && fVarD == f.r0 && P.v1()) {
                k.q(this);
            }
            i2 = 0;
        } else {
            i2 = 2;
            if (!P.B1() || f.d() == f.w0 ? C5845t.P0(this, f, intent.getData()) : C5832f.D(f, intent.getData())) {
                i2 = 1;
            }
        }
        if (i != 0 || this.H0) {
            if (P.v1()) {
                if (System.currentTimeMillis() - this.I0 >= 750) {
                    if (i == -1 && intent != null && intent.getData() == null) {
                        c.h().g().c("7.0 DOCUMENT TREE OK BUT FAIL").h(intent.toString()).i();
                    } else if (i != -1) {
                        z = false;
                    }
                }
                z = true;
            } else {
                z = false;
            }
            f1(i2, f, str, z, true);
        }
    }

    private void g1() {
        if (this instanceof ax.p352z1.b) {
            AbstractC0762l abstractC0762lP1 = ((ax.p352z1.b) this).p1();
            if (abstractC0762lP1 instanceof AbstractC0775z) {
                ((AbstractC0775z) abstractC0762lP1).V7();
            }
        }
    }

    @Override // ax.K1.InterfaceC0767q
    public boolean M(e eVar, String str, boolean z) {
        if (v()) {
            return false;
        }
        x.d0(w(), eVar, str, z);
        return true;
    }

    protected void Z0() {
        if (v.q(this) != this.J0) {
            int iH = i.h(this);
            if (iH == 0 || iH == -1) {
                ax.O.b.q(this);
            }
        }
    }

    public void a1() {
        Fragment fragmentI0 = w().i0("guide_document_tree");
        if (fragmentI0 instanceof e) {
            ((e) fragmentI0).b3();
        }
    }

    public boolean b1() {
        return w().i0("guide_document_tree") != null;
    }

    public void c1(F f) {
        if (CommandService.y(f)) {
            Toast.makeText(this, R.string.error_device_is_in_use, 1).show();
            return;
        }
        if (P.B1() && f.d() != f.w0) {
            P.l(35);
            Intent intent = new Intent();
            intent.setAction("android.provider.action.DOCUMENT_ROOT_SETTINGS");
            String strU = ax.G1.i.D().U(f);
            if (strU != null) {
                intent.setDataAndType(Uri.parse("content://com.android.externalstorage.documents/root/" + strU), "vnd.android.document/root");
                if (x.N(this, intent)) {
                    try {
                        C0734o.j0(this, intent, 50201);
                        return;
                    } catch (ActivityNotFoundException unused) {
                    }
                }
            }
        }
        try {
            C0734o.j0(this, new Intent("android.settings.MEMORY_CARD_SETTINGS"), 50201);
        } catch (ActivityNotFoundException unused2) {
            Toast.makeText(this, R.string.no_application, 1).show();
        } catch (NullPointerException | SecurityException unused3) {
            Toast.makeText(this, R.string.error, 1).show();
        }
    }

    F e1(Intent intent) {
        AbstractC0762l abstractC0762lP1;
        F fK;
        F fH;
        if (!P.F1()) {
            return null;
        }
        if (intent != null && intent.getData() != null && (fH = C5832f.h(this, intent.getData())) != null) {
            return fH;
        }
        if (!(this instanceof ax.p352z1.b) || (abstractC0762lP1 = ((ax.p352z1.b) this).p1()) == null || (fK = f.K(abstractC0762lP1.M3())) == null || fK.e() == null) {
            return null;
        }
        return fK;
    }

    public void f1(int i, F f, String str, boolean z, boolean z2) {
        F fK = f.K(f);
        if (fK == null) {
            c.h().b("ILLEGAL LOCATION FOR TREEDOCUMENT").h("loc:" + f.toString()).k().i();
            return;
        }
        if (!z && P.v1() && str == null) {
            String strU = ax.G1.i.D().U(fK);
            Intent intentC = strU != null ? ax.E1.x.c(P(), strU) : null;
            if (intentC == null && f.f0(fK.d()) && fK.e() != null) {
                intentC = ax.E1.x.b(P(), fK.e());
            }
            if (intentC != null) {
                try {
                    this.E0 = fK.d();
                    this.F0 = fK.b();
                    this.G0 = str;
                    if (i == 0) {
                        this.H0 = false;
                    } else {
                        this.H0 = true;
                    }
                    C0734o.j0(this, intentC, 50101);
                    return;
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                } catch (SecurityException e3) {
                    e3.printStackTrace();
                }
            }
        }
        E e4 = new E();
        Bundle bundle = new Bundle();
        if (i != 0) {
            bundle.putInt("ERROR_CAUSE", i);
        }
        bundle.putBoolean("SHOW_CANCEL", z2);
        bundle.putSerializable("LOCATION", fK.d());
        bundle.putInt("LOCATION_KEY", fK.b());
        bundle.putString("TREE_PATH", str);
        e4.J2(bundle);
        M(e4, "guide_document_tree", true);
    }

    @Override // ax.K1.InterfaceC0767q
    public Context getContext() {
        return this;
    }

    public void h1(int i) {
        if (I0() != null) {
            I0().s(new ColorDrawable(i));
        }
    }

    public boolean i1(F f, String str) {
        ActivityInfo activityInfo;
        ApplicationInfo applicationInfo;
        Uri uriM;
        if (f == null) {
            Toast.makeText(this, R.string.error, 1).show();
            return false;
        }
        if (f == F.e && !V.y(str)) {
            Toast.makeText(this, R.string.error, 1).show();
            return false;
        }
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        if (!P.C0() || (!(f.a0(f.d()) || f.P(f)) || (uriM = C5832f.m(f, str)) == null)) {
            intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
            intent.putExtra("android.provider.extra.SHOW_ADVANCED", true);
        } else {
            intent.putExtra("android.provider.extra.INITIAL_URI", uriM);
        }
        List<ResolveInfo> listQueryIntentActivities = getPackageManager().queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities != null) {
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                ResolveInfo next = it.next();
                ActivityInfo activityInfo2 = next.activityInfo;
                if (activityInfo2 == null || (applicationInfo = activityInfo2.applicationInfo) == null || (applicationInfo.flags & 1) != 1) {
                    if (next.priority < 0) {
                        it.remove();
                    }
                }
            }
        }
        if (listQueryIntentActivities != null && listQueryIntentActivities.size() > 1) {
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                if (P.E0() && (activityInfo = resolveInfo.activityInfo) != null && "com.google.android.documentsui".equals(activityInfo.packageName)) {
                    intent.setPackage("com.google.android.documentsui");
                } else {
                    ActivityInfo activityInfo3 = resolveInfo.activityInfo;
                    if (activityInfo3 != null && "com.android.documentsui".equals(activityInfo3.packageName)) {
                        intent.setPackage("com.android.documentsui");
                    }
                }
            }
        }
        try {
            this.E0 = f.d();
            this.F0 = f.b();
            this.H0 = true;
            this.G0 = str;
            C0734o.j0(this, intent, 50101);
            return true;
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, R.string.no_application, 1).show();
            return false;
        } catch (NullPointerException | SecurityException unused2) {
            Toast.makeText(this, R.string.error, 1).show();
            return false;
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 50101) {
            d1(i2, intent);
        } else {
            if (i != 50201) {
                return;
            }
            ax.G1.i.D().y0();
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.p338y1.b.f(this, true);
        super.onCreate(bundle);
        if (P.x1()) {
            u.r(getWindow(), ax.P.b.c(this, R.color.statusbar_color));
        }
        ax.p045d2.g.a().d("local.intent.action.THEME_CHANGED", this.K0);
        this.J0 = v.q(this);
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        ax.p045d2.g.a().h(this.K0);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.I0 = System.currentTimeMillis();
    }

    @Override // ax.p182n.ActivityC5571c, android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (P.J0() && k.t() && !M0) {
            View viewFindViewById = findViewById(android.R.id.content);
            viewFindViewById.addOnAttachStateChangeListener(new b(viewFindViewById));
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.E0 = (f) bundle.getSerializable("DOCUMENT_TREE_REQUEST_LOCATION");
        this.F0 = bundle.getInt("DOCUMENT_TREE_REQUEST_LOCATION_KEY");
        this.G0 = bundle.getString("DOCUMENT_TREE_REQUEST_ROOT_TREE_PATH");
    }

    @Override // androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putSerializable("DOCUMENT_TREE_REQUEST_LOCATION", this.E0);
            bundle.putInt("DOCUMENT_TREE_REQUEST_LOCATION_KEY", this.F0);
            bundle.putString("DOCUMENT_TREE_REQUEST_ROOT_TREE_PATH", this.G0);
        } catch (IllegalStateException e) {
            c.h().d("BaseAppCompatActivity onSaveInstanceState Error").m(e).i();
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        Z0();
    }

    @Override // ax.p352z1.a
    public boolean v() {
        return w().O0();
    }

    @Override // ax.K1.InterfaceC0767q
    public void y(ax.D1.f fVar, boolean z) throws C0711b {
        CommandService.N(this, null, fVar, z, true);
    }

    @Override // ax.p352z1.a
    public ActivityC5571c P() {
        return this;
    }
}
