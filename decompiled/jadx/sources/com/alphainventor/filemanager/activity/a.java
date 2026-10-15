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
import ax.U2.f;
import ax.U2.g;
import ax.Z2.h;
import ax.p006a3.J;
import ax.p006a3.Q;
import ax.p006a3.v;
import ax.p006a3.y;
import ax.p035c0.C1839d0;
import ax.p035c0.F0;
import ax.p038c3.j;
import ax.p038c3.n;
import ax.p054d3.H;
import ax.p070e3.C1959c;
import ax.p086f3.C2014u;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p102g3.AbstractC2067n;
import ax.p102g3.E;
import ax.p102g3.InterfaceC2071s;
import ax.p210n.ActivityC2164c;
import ax.p390y3.k;
import ax.p390y3.m;
import ax.p406z3.B;
import ax.p406z3.z;
import ax.p409z6.C3772df;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.C6232g;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.service.CommandService;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ActivityC2164c implements ax.V2.a, InterfaceC2071s {
    private static final Logger n0 = g.a(a.class);
    private static boolean o0;
    private int X;
    private String Y;
    private long k0;
    private boolean l0;
    private f q;
    private boolean Z = true;
    private BroadcastReceiver m0 = new C0527a();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.a$a, reason: collision with other inner class name */
    class C0527a extends BroadcastReceiver {
        C0527a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("local.intent.action.THEME_CHANGED".equals(intent.getAction())) {
                ax.P.b.r(a.this);
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
            a.this.K(this.q);
            boolean unused = a.o0 = true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(View view) {
        int iF;
        F0 f0G = C1839d0.G(view);
        if (f0G != null && (iF = B.f(this, f0G.f(F0.m.h()).d)) > 10 && iF < 25) {
            m.a aVarD = k.d(this);
            m.a aVar = m.a.DRAWER_ONLY_HOME;
            if (aVarD != aVar) {
                k.r(this, aVar);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:92:0x0179  */
    @TargetApi(C3772df.zzm)
    private void P(int i, Intent intent) {
        int i2;
        boolean z;
        f fVarD = this.q;
        int i3 = this.X;
        String strZ = this.Y;
        K kA = fVarD != null ? K.a(fVarD, i3) : null;
        M();
        if (kA == null) {
            kA = Q(intent);
            if (kA != null) {
                fVarD = kA.d();
                kA.b();
            }
            if (kA == null) {
                return;
            }
        }
        K k = kA;
        if (k == K.e && strZ == null && intent != null && intent.getData() != null) {
            try {
                strZ = C6232g.z(v.h(intent.getData()));
                if (strZ == null) {
                    return;
                }
            } catch (IllegalArgumentException unused) {
                return;
            }
        }
        String str = strZ;
        if (intent != null && intent.getData() != null && intent.getData().getAuthority() != null) {
            String authority = intent.getData().getAuthority();
            if (!"com.android.externalstorage.documents".equals(authority) && !"com.android.mtp.documents".equals(authority) && !J.o() && !"com.android.providers.downloads.documents".equals(authority)) {
                ax.p302sb.c.h().g().b("Unknown external storage authority").h("authority:" + authority).i();
            }
        }
        if (i == -1 && intent != null && intent.getData() != null && C6232g.H(this, k, str, intent.getData())) {
            try {
                v.v(this, intent);
                if (!j.F().F0(k) || str == null) {
                    C6232g.O(this, k, str, intent.getData());
                } else {
                    n.b(this).a(intent.getData());
                }
                S();
                return;
            } catch (SecurityException e) {
                e.printStackTrace();
                ax.p302sb.c.h().d("TAKE PERSITABLE PERMISSION ERROR!").m(e).i();
                return;
            }
        }
        if (i != -1 || intent == null || intent.getData() == null) {
            if (i == 0 && fVarD == f.u0 && Q.L1()) {
                m.q(this);
            }
            i2 = 0;
        } else {
            i2 = 2;
            if (!Q.R1() || k.d() == f.z0 ? C6248x.X0(this, k, intent.getData()) : C6232g.I(k, intent.getData())) {
                i2 = 1;
            }
        }
        if (i != 0 || this.Z) {
            if (Q.L1()) {
                if (System.currentTimeMillis() - this.k0 >= 750) {
                    if (i == -1 && intent != null && intent.getData() == null) {
                        ax.p302sb.c.h().g().c("7.0 DOCUMENT TREE OK BUT FAIL").h(intent.toString()).i();
                    } else if (i != -1) {
                        z = false;
                    }
                }
                z = true;
            } else {
                z = false;
            }
            R(i2, k, str, z, true);
        }
    }

    private void S() {
        if (this instanceof com.alphainventor.filemanager.activity.b) {
            AbstractC2067n abstractC2067nG0 = ((com.alphainventor.filemanager.activity.b) this).g0();
            if (abstractC2067nG0 instanceof E) {
                ((E) abstractC2067nG0).m9();
            }
        }
    }

    @Override // ax.p102g3.InterfaceC2071s
    public boolean I(e eVar, String str, boolean z) {
        if (k()) {
            return false;
        }
        B.d0(getSupportFragmentManager(), eVar, str, z);
        return true;
    }

    protected void L() {
        if (z.v(this) != this.l0) {
            int iJ = k.j(this);
            if (iJ == 0 || iJ == -1) {
                ax.P.b.r(this);
            }
        }
    }

    public void M() {
        Fragment fragmentK0 = getSupportFragmentManager().k0("guide_document_tree");
        if (fragmentK0 instanceof e) {
            ((e) fragmentK0).Y2();
        }
    }

    public boolean N() {
        return getSupportFragmentManager().k0("guide_document_tree") != null;
    }

    public void O(K k) {
        if (CommandService.y(k)) {
            Toast.makeText(this, R.string.error_device_is_in_use, 1).show();
            return;
        }
        if (Q.R1() && k.d() != f.z0) {
            Q.l(37);
            Intent intent = new Intent("android.provider.action.DOCUMENT_ROOT_SETTINGS");
            String strA0 = j.F().a0(k);
            if (strA0 != null) {
                intent.setDataAndType(Uri.parse("content://com.android.externalstorage.documents/root/" + strA0), "vnd.android.document/root");
                if (B.L(this, intent)) {
                    try {
                        C2014u.q0(this, intent, 50201);
                        return;
                    } catch (ActivityNotFoundException unused) {
                    }
                }
            }
        }
        try {
            C2014u.q0(this, new Intent("android.settings.MEMORY_CARD_SETTINGS"), 50201);
        } catch (ActivityNotFoundException unused2) {
            Toast.makeText(this, R.string.no_application, 1).show();
        } catch (NullPointerException | SecurityException unused3) {
            Toast.makeText(this, R.string.error, 1).show();
        }
    }

    K Q(Intent intent) {
        AbstractC2067n abstractC2067nG0;
        K K;
        K kH;
        if (!Q.V1()) {
            return null;
        }
        if (intent != null && intent.getData() != null && (kH = C6232g.h(this, intent.getData())) != null) {
            return kH;
        }
        if (!(this instanceof com.alphainventor.filemanager.activity.b) || (abstractC2067nG0 = ((com.alphainventor.filemanager.activity.b) this).g0()) == null || (K = f.K(abstractC2067nG0.M3())) == null || K.e() == null) {
            return null;
        }
        return K;
    }

    public void R(int i, K k, String str, boolean z, boolean z2) {
        K K = f.K(k);
        if (K == null) {
            ax.p302sb.c.h().b("ILLEGAL LOCATION FOR TREEDOCUMENT").h("loc:" + k.toString()).k().i();
            return;
        }
        if (!z && Q.L1() && str == null && f.b0(K.d())) {
            String strA0 = j.F().a0(K);
            Intent intentC = strA0 != null ? y.c(r(), strA0) : null;
            if (intentC == null && f.g0(K.d()) && K.e() != null) {
                intentC = y.b(r(), K.e());
            }
            if (intentC != null) {
                try {
                    this.q = K.d();
                    this.X = K.b();
                    this.Y = str;
                    if (i == 0) {
                        this.Z = false;
                    } else {
                        this.Z = true;
                    }
                    C2014u.q0(this, intentC, 50101);
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
        H h = new H();
        Bundle bundle = new Bundle();
        if (i != 0) {
            bundle.putInt("ERROR_CAUSE", i);
        }
        bundle.putBoolean("SHOW_CANCEL", z2);
        bundle.putSerializable("LOCATION", K.d());
        bundle.putInt("LOCATION_KEY", K.b());
        bundle.putString("TREE_PATH", str);
        h.F2(bundle);
        I(h, "guide_document_tree", true);
    }

    public void T(int i) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().u(new ColorDrawable(i));
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0061  */
    public boolean W(K k, String str) {
        ActivityInfo activityInfo;
        ApplicationInfo applicationInfo;
        Uri uriN;
        if (k == null) {
            Toast.makeText(this, R.string.error, 1).show();
            return false;
        }
        if (k == K.e && !d0.B(str)) {
            Toast.makeText(this, R.string.error, 1).show();
            return false;
        }
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        if (!Q.K0()) {
            intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
            intent.putExtra("android.provider.extra.SHOW_ADVANCED", true);
        } else if (f.a0(k.d()) || f.P(k)) {
            Uri uriM = C6232g.m(k, str);
            if (uriM != null) {
                intent.putExtra("android.provider.extra.INITIAL_URI", uriM);
            } else {
                intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
                intent.putExtra("android.provider.extra.SHOW_ADVANCED", true);
            }
        } else if (f.m1 != k.d() || (uriN = j.F().N(k)) == null) {
            intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
            intent.putExtra("android.provider.extra.SHOW_ADVANCED", true);
        } else {
            intent.putExtra("android.provider.extra.INITIAL_URI", uriN);
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
                if (Q.M0() && (activityInfo = resolveInfo.activityInfo) != null && "com.google.android.documentsui".equals(activityInfo.packageName)) {
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
            this.q = k.d();
            this.X = k.b();
            this.Z = true;
            this.Y = str;
            C2014u.q0(this, intent, 50101);
            return true;
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, R.string.no_application, 1).show();
            return false;
        } catch (NullPointerException | SecurityException unused2) {
            Toast.makeText(this, R.string.error, 1).show();
            return false;
        }
    }

    @Override // ax.p102g3.InterfaceC2071s
    public void Z(h hVar, boolean z) throws C1959c {
        CommandService.N(this, null, hVar, z, true);
    }

    @Override // ax.p102g3.InterfaceC2071s
    public Context getContext() {
        return this;
    }

    @Override // ax.V2.a
    public boolean k() {
        return getSupportFragmentManager().Q0();
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 50101) {
            P(i2, intent);
        } else {
            if (i != 50201) {
                return;
            }
            j.F().L0();
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.U2.b.f(this, true);
        super.onCreate(bundle);
        if (Q.N1()) {
            v.u(getWindow(), ax.Q.b.c(this, R.color.statusbar_color));
        }
        ax.p406z3.g.a().d("local.intent.action.THEME_CHANGED", this.m0);
        this.l0 = z.v(this);
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        ax.p406z3.g.a().h(this.m0);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.k0 = System.currentTimeMillis();
    }

    @Override // ax.p210n.ActivityC2164c, android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (Q.S0() && m.t() && !o0) {
            View viewFindViewById = findViewById(android.R.id.content);
            viewFindViewById.addOnAttachStateChangeListener(new b(viewFindViewById));
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.q = (f) bundle.getSerializable("DOCUMENT_TREE_REQUEST_LOCATION");
        this.X = bundle.getInt("DOCUMENT_TREE_REQUEST_LOCATION_KEY");
        this.Y = bundle.getString("DOCUMENT_TREE_REQUEST_ROOT_TREE_PATH");
    }

    @Override // androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putSerializable("DOCUMENT_TREE_REQUEST_LOCATION", this.q);
            bundle.putInt("DOCUMENT_TREE_REQUEST_LOCATION_KEY", this.X);
            bundle.putString("DOCUMENT_TREE_REQUEST_ROOT_TREE_PATH", this.Y);
        } catch (IllegalStateException e) {
            ax.p302sb.c.h().d("BaseAppCompatActivity onSaveInstanceState Error").m(e).i();
        }
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        L();
    }

    @Override // ax.V2.a
    public ActivityC2164c r() {
        return this;
    }
}
