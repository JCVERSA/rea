package com.alphainventor.filemanager.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ax.C3.h;
import ax.C3.v;
import ax.U2.f;
import ax.U2.j;
import ax.p006a3.Q;
import ax.p086f3.A;
import ax.p086f3.B;
import ax.p086f3.C2014u;
import ax.p102g3.AbstractC2067n;
import ax.p102g3.C0;
import ax.p102g3.H0;
import ax.p102g3.Z;
import ax.p374x3.w;
import ax.p390y3.k;
import ax.p406z3.C2461b;
import ax.p406z3.g;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.bookmark.Bookmark;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.musicplayer.PlaybackControlsFragment;
import com.alphainventor.filemanager.service.HttpServerService;
import com.alphainventor.filemanager.widget.WindowInsetsFrameLayout;
import com.example.android.uamp.MusicService;
import com.google.android.material.behavior.SwipeDismissBehavior;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends com.alphainventor.filemanager.activity.a implements C0.a {
    private H0 A0;
    private C0 B0;
    private MediaBrowserCompat p0;
    private MediaControllerCompat q0;
    private CoordinatorLayout r0;
    private View s0;
    protected PlaybackControlsFragment t0;
    private j u0;
    private Uri w0;
    private Bundle x0;
    private boolean v0 = false;
    private boolean y0 = false;
    private boolean z0 = false;
    private final MediaControllerCompat.a C0 = new a();
    private final MediaBrowserCompat.c D0 = new C0528b();

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            b.this.M0();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            b.this.M0();
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.activity.b$b, reason: collision with other inner class name */
    class C0528b extends MediaBrowserCompat.c {
        C0528b() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a() {
            b.this.v0 = false;
            try {
                b bVar = b.this;
                bVar.f0(bVar.p0.c());
            } catch (RemoteException unused) {
                b.this.m0();
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void b() {
            b.this.v0 = false;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void c() {
            b.this.v0 = false;
            b.this.v0();
        }
    }

    class c extends SwipeDismissBehavior<ViewGroup> {
        c() {
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean J(View view) {
            return true;
        }
    }

    class d implements SwipeDismissBehavior.c {
        d() {
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.c
        public void a(View view) {
            b.this.K0();
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.c
        public void b(int i) {
        }
    }

    private void F0(MediaControllerCompat mediaControllerCompat) {
        this.q0 = mediaControllerCompat;
    }

    private void e0() {
        if (this.v0) {
            return;
        }
        w.j(this);
        try {
            this.p0.a();
            this.v0 = true;
        } catch (IllegalStateException e) {
            ax.p302sb.c.h().g().b("MEDIA BROWSER CONNECT").m(e).i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(MediaSessionCompat.Token token) throws RemoteException {
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(this, token);
        MediaControllerCompat.l(this, mediaControllerCompat);
        F0(mediaControllerCompat);
        mediaControllerCompat.j(this.C0);
        M0();
        PlaybackControlsFragment playbackControlsFragment = this.t0;
        if (playbackControlsFragment != null) {
            playbackControlsFragment.f3(mediaControllerCompat);
        }
        x0();
        if (this.w0 != null) {
            if (this.x0.getBoolean("PLAY_PREPARE", false)) {
                mediaControllerCompat.i().d(this.w0, this.x0);
            } else {
                mediaControllerCompat.i().c(this.w0, this.x0);
            }
            this.w0 = null;
            this.x0 = null;
        }
    }

    public boolean A0(AbstractC6239n abstractC6239n, String str, List<AbstractC6239n> list, boolean z, int i) {
        boolean z2;
        Uri uriL;
        Uri uriL2;
        ax.W4.a.b().a();
        boolean zD = B.d(abstractC6239n);
        int iH = com.alphainventor.filemanager.service.b.f(this).h();
        if (!zD) {
            HttpServerService.q(this, abstractC6239n.Q(), abstractC6239n.M(), true, true, false, null);
        }
        Bundle bundle = new Bundle();
        if (list != null && list.size() != 0) {
            ArrayList<AbstractC6239n> arrayListC = A.c(list);
            ArrayList arrayList = new ArrayList();
            if (arrayListC.size() != 0) {
                ArrayList<Pair<Uri, String>> arrayList2 = new ArrayList<>();
                int size = arrayListC.size();
                boolean z3 = false;
                int i2 = 0;
                while (i2 < size) {
                    AbstractC6239n abstractC6239n2 = arrayListC.get(i2);
                    i2++;
                    AbstractC6239n abstractC6239n3 = abstractC6239n2;
                    if (abstractC6239n.E().equals(abstractC6239n3.E())) {
                        z3 = true;
                    }
                    if (B.d(abstractC6239n3)) {
                        uriL2 = C2014u.H(abstractC6239n3, true);
                    } else {
                        arrayList.add(abstractC6239n3);
                        uriL2 = HttpServerService.l(iH, abstractC6239n3);
                    }
                    arrayList2.add(new Pair<>(uriL2, abstractC6239n3.R()));
                }
                if (arrayList.size() > 0) {
                    com.alphainventor.filemanager.service.b.f(getContext()).b(arrayList);
                }
                if (z3) {
                    ax.W4.a.b().d(arrayList2);
                }
            }
        }
        bundle.putString("PLAY_FOLDER_URI", str);
        bundle.putBoolean("PLAY_LOCAL_HTTP", !zD);
        bundle.putInt("PLAYER_ENGINE", k.i(this));
        if (z) {
            bundle.putBoolean("PLAY_PREPARE", z);
        }
        bundle.putInt("PLAY_RESUME_POSITION", i);
        if (zD) {
            z2 = true;
            uriL = C2014u.H(abstractC6239n, true);
        } else {
            z2 = true;
            com.alphainventor.filemanager.service.b.f(getContext()).c(abstractC6239n);
            uriL = HttpServerService.l(iH, abstractC6239n);
        }
        ax.p198m3.b.b(this).h(str, abstractC6239n.R(), i);
        B0(uriL, bundle);
        return z2;
    }

    public void B0(Uri uri, Bundle bundle) {
        if (i0() == null) {
            this.w0 = uri;
            this.x0 = bundle;
            e0();
        } else {
            this.w0 = null;
            this.x0 = null;
            if (bundle.getBoolean("PLAY_PREPARE", false)) {
                i0().i().d(uri, bundle);
            } else {
                i0().i().c(uri, bundle);
            }
        }
    }

    public boolean C0(AbstractC6239n abstractC6239n, String str, List<AbstractC6239n> list, int i) {
        if (this.w0 != null) {
            return false;
        }
        boolean zD = B.d(abstractC6239n);
        if (Q.a0() && !zD && q0() && ax.p406z3.B.O(this)) {
            return false;
        }
        return A0(abstractC6239n, str, list, true, i);
    }

    protected void D0() {
        C0 c0 = this.B0;
        if (c0 != null) {
            c0.g();
        }
    }

    public void E0(Z z) {
        H0 h0 = this.A0;
        if (h0 != null) {
            h0.x(z);
        }
    }

    public void G0(int i) {
        C0 c0 = this.B0;
        if (c0 != null) {
            c0.i(i);
        }
    }

    void H0() {
        ViewGroup.LayoutParams layoutParams = this.s0.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.f) {
            c cVar = new c();
            cVar.Q(0.1f);
            cVar.O(0.6f);
            cVar.R(2);
            cVar.P(new d());
            ((CoordinatorLayout.f) layoutParams).o(cVar);
        }
    }

    protected boolean I0() {
        MediaControllerCompat mediaControllerCompatI0 = i0();
        if (mediaControllerCompatI0 != null && mediaControllerCompatI0.d() != null && mediaControllerCompatI0.e() != null) {
            AbstractC2067n abstractC2067nG0 = g0();
            int iJ = mediaControllerCompatI0.e().j();
            if (abstractC2067nG0 != null && abstractC2067nG0.L3() == f.F0) {
                if (iJ != 1) {
                    return iJ != 7;
                }
                return ax.p198m3.b.b(this).e();
            }
            if (iJ != 0) {
                if (iJ != 1) {
                    if (iJ == 2) {
                        return true;
                    }
                    if (iJ != 7) {
                        if (abstractC2067nG0 == null || abstractC2067nG0.O3() == null) {
                            return iJ != 2;
                        }
                        try {
                            String string = mediaControllerCompatI0.d().d().getString("__SOURCE_FOLDER__");
                            return (string != null && string.equals(B.U(abstractC2067nG0.M3(), abstractC2067nG0.O3()))) || iJ != 2;
                        } catch (RuntimeException e) {
                            ax.p302sb.c.h().g().b("MusicPlayer MetaData error").m(e).i();
                            return false;
                        }
                    }
                }
                return false;
            }
            if (!ax.p198m3.b.b(this).f(3600000L)) {
                return true;
            }
        }
        return false;
    }

    protected void J0() {
        if (this.z0) {
            return;
        }
        this.s0.clearAnimation();
        this.s0.setAlpha(1.0f);
        if (this.r0.getVisibility() != 0) {
            this.r0.setVisibility(0);
            getSupportFragmentManager().o().t(R.anim.dock_bottom_enter, R.anim.dock_bottom_exit, R.anim.dock_bottom_enter, R.anim.dock_bottom_exit).v(this.t0).j();
            D0();
        }
    }

    public void K0() {
        MediaControllerCompat mediaControllerCompatI0 = i0();
        if (mediaControllerCompatI0 == null || mediaControllerCompatI0.d() == null || mediaControllerCompatI0.e() == null) {
            m0();
            return;
        }
        MediaControllerCompat.c(this).i().k();
        ax.p198m3.b.b(this).a();
        m0();
    }

    public abstract void L0();

    protected void M0() {
        if (this.t0 == null || isFinishing()) {
            return;
        }
        if (I0()) {
            J0();
            return;
        }
        m0();
        MediaControllerCompat mediaControllerCompatI0 = i0();
        if (mediaControllerCompatI0 == null || mediaControllerCompatI0.e() == null || mediaControllerCompatI0.e().j() != 2) {
            return;
        }
        ax.p038c3.k kVarC = ax.p198m3.b.b(this).c();
        if (kVarC == null || kVarC.b() != f.F0) {
            mediaControllerCompatI0.i().k();
        }
    }

    public void b0(Z z) {
        H0 h0 = this.A0;
        if (h0 != null) {
            h0.n(z);
        }
    }

    public abstract void c0(f fVar, int i, String str);

    public abstract AbstractC2067n g0();

    public abstract h h0();

    public MediaControllerCompat i0() {
        return this.q0;
    }

    public j j0() {
        return this.u0;
    }

    public abstract v k0();

    public Point l0() {
        H0 h0 = this.A0;
        if (h0 != null) {
            return h0.t(this);
        }
        C2461b.g("Invalid WindowSizeChanged Called.");
        return new Point(0, 0);
    }

    protected void m0() {
        CoordinatorLayout coordinatorLayout;
        if (this.z0 || (coordinatorLayout = this.r0) == null || coordinatorLayout.getVisibility() == 8) {
            return;
        }
        getSupportFragmentManager().o().p(this.t0).j();
        this.r0.setVisibility(8);
        D0();
    }

    protected void n0() {
        C0 c0 = new C0(this, this);
        this.B0 = c0;
        c0.d((WindowInsetsFrameLayout) findViewById(R.id.root_view));
    }

    public boolean o0() {
        return this.v0;
    }

    @Override // ax.p210n.ActivityC2164c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        H0 h0 = this.A0;
        if (h0 != null) {
            h0.v(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // com.alphainventor.filemanager.activity.a, androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p0 = new MediaBrowserCompat(this, new ComponentName(this, (Class<?>) MusicService.class), this.D0, null);
        this.u0 = new j();
        this.y0 = false;
        com.alphainventor.filemanager.shizuku.c.t().j();
        H0 h0 = new H0(this);
        this.A0 = h0;
        h0.m(null);
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        this.z0 = true;
        super.onDestroy();
        com.alphainventor.filemanager.shizuku.c.t().G();
    }

    @Override // ax.g3.C0.a
    public void onIMEVisibilityChanged(boolean z) {
    }

    @Override // com.alphainventor.filemanager.activity.a, ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.y0 = false;
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.playback_controls_layout);
        this.r0 = coordinatorLayout;
        if (coordinatorLayout == null) {
            throw new IllegalStateException("Mising view with id 'controls'. Cannot continue.");
        }
        this.s0 = findViewById(R.id.fragment_playback_controls);
        H0();
        PlaybackControlsFragment playbackControlsFragment = (PlaybackControlsFragment) getSupportFragmentManager().j0(R.id.fragment_playback_controls);
        this.t0 = playbackControlsFragment;
        if (playbackControlsFragment == null) {
            throw new IllegalStateException("Mising fragment with id 'controls'. Cannot continue.");
        }
        m0();
        if (!MusicService.D() || this.v0) {
            return;
        }
        if (this.p0.d()) {
            this.p0.b();
        }
        e0();
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.y0 = true;
        this.v0 = false;
        v0();
        this.p0.b();
    }

    protected boolean p0() {
        return this.t0 != null && this.r0.getVisibility() == 0;
    }

    public boolean q0() {
        return this.y0;
    }

    public boolean r0() {
        H0 h0 = this.A0;
        if (h0 == null) {
            return false;
        }
        return h0.u();
    }

    public abstract void s0();

    public abstract void t0(String str);

    public abstract void u0(Bookmark bookmark);

    protected void v0() {
        this.w0 = null;
        this.x0 = null;
        if (i0() != null) {
            i0().m(this.C0);
            PlaybackControlsFragment playbackControlsFragment = this.t0;
            if (playbackControlsFragment != null) {
                playbackControlsFragment.g3(i0());
            }
        }
        F0(null);
        MediaControllerCompat.l(this, null);
        y0();
        m0();
    }

    public abstract void w0(f fVar, int i, String str, boolean z);

    protected void x0() {
        Intent intent = new Intent("local.intent.action.ACTION_MEDIA_CONTROLLER_CHANGED");
        intent.putExtra("CONNECTED", true);
        g.a().e(intent);
    }

    protected void y0() {
        Intent intent = new Intent("local.intent.action.ACTION_MEDIA_CONTROLLER_CHANGED");
        intent.putExtra("CONNECTED", false);
        g.a().e(intent);
    }

    public abstract void z0(f fVar, int i, String str, boolean z);
}
