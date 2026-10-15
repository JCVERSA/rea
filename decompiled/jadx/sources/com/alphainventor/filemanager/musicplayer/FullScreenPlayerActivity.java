package com.alphainventor.filemanager.musicplayer;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.format.DateUtils;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.MySpinner;
import androidx.appcompat.widget.Toolbar;
import ax.p006a3.Q;
import ax.p006a3.v;
import ax.p086f3.C2014u;
import ax.p086f3.C2015v;
import ax.p102g3.H0;
import ax.p102g3.Z;
import ax.p210n.ActivityC2164c;
import ax.p374x3.w;
import ax.p406z3.B;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.R;
import com.example.android.uamp.MusicService;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class FullScreenPlayerActivity extends ActivityC2164c implements Z {
    private static final String f1 = ax.Y4.b.f(FullScreenPlayerActivity.class);
    private Drawable A0;
    private ImageView B0;
    private TextView C0;
    private MySpinner D0;
    private View E0;
    private ImageView F0;
    private TextView G0;
    private View H0;
    private View I0;
    private String J0;
    private MediaBrowserCompat L0;
    private long M0;
    private long N0;
    private long O0;
    private boolean P0;
    private int Q0;
    private boolean R0;
    private boolean T0;
    private ScheduledFuture<?> W0;
    private View X;
    private PlaybackStateCompat X0;
    private ImageView Y;
    private H0 Y0;
    private ImageView Z;
    private File Z0;
    private String a1;
    private boolean b1;
    private ImageView k0;
    private ImageView l0;
    private ImageView m0;
    private TextView n0;
    private TextView o0;
    private SeekBar p0;
    private Toolbar q;
    private TextView q0;
    private TextView r0;
    private TextView s0;
    private TextView t0;
    private ProgressBar u0;
    private View v0;
    private Drawable w0;
    private Drawable x0;
    private Drawable y0;
    private Drawable z0;
    private final Handler K0 = new Handler();
    private float S0 = 1.0f;
    private final Runnable U0 = new k();
    private final ScheduledExecutorService V0 = Executors.newSingleThreadScheduledExecutor();
    private final MediaControllerCompat.a c1 = new l();
    private final MediaBrowserCompat.c d1 = new m();
    private final GestureDetector.SimpleOnGestureListener e1 = new j();

    class a extends ax.p182l3.c {
        a() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            FullScreenPlayerActivity.this.b1(10000L);
        }
    }

    class b implements SeekBar.OnSeekBarChangeListener {
        boolean a = false;

        b() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            FullScreenPlayerActivity.this.n0.setText(DateUtils.formatElapsedTime(i / 1000));
            if (!z || this.a) {
                return;
            }
            FullScreenPlayerActivity.this.a1(seekBar.getProgress(), false);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            this.a = true;
            FullScreenPlayerActivity.this.h1();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            this.a = false;
            FullScreenPlayerActivity.this.a1(seekBar.getProgress(), false);
        }
    }

    class c extends ax.p182l3.c {
        c() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            FullScreenPlayerActivity.this.i1();
        }
    }

    class d implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ com.alphainventor.filemanager.viewer.d q;

        d(com.alphainventor.filemanager.viewer.d dVar) {
            this.q = dVar;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            com.alphainventor.filemanager.viewer.d.a aVar = (com.alphainventor.filemanager.viewer.d.a) this.q.getItem(i);
            if (aVar != null) {
                FullScreenPlayerActivity.this.f1(aVar.b, true);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FullScreenPlayerActivity.this.K0.post(FullScreenPlayerActivity.this.U0);
        }
    }

    class f extends ax.V4.a.AbstractC0223a {
        f() {
        }

        @Override // ax.V4.a.AbstractC0223a
        public void b(String str, Exception exc) {
            super.b(str, exc);
            if (str.equals(FullScreenPlayerActivity.this.J0)) {
                FullScreenPlayerActivity.this.B0.setImageResource(R.drawable.ic_default_art_large);
            }
        }

        @Override // ax.V4.a.AbstractC0223a
        public void c(String str, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
            if (str.equals(FullScreenPlayerActivity.this.J0)) {
                FullScreenPlayerActivity.this.B0.setImageBitmap(bitmap);
            }
        }
    }

    class g implements com.alphainventor.filemanager.viewer.a.b {
        g() {
        }

        @Override // com.alphainventor.filemanager.viewer.a.b
        public void a(MotionEvent motionEvent) {
            FullScreenPlayerActivity.this.W0();
        }
    }

    class h implements View.OnTouchListener {
        final /* synthetic */ GestureDetector X;
        final /* synthetic */ com.alphainventor.filemanager.viewer.a q;

        h(com.alphainventor.filemanager.viewer.a aVar, GestureDetector gestureDetector) {
            this.q = aVar;
            this.X = gestureDetector;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.q.g(motionEvent);
            boolean zOnTouchEvent = this.X.onTouchEvent(motionEvent);
            if (FullScreenPlayerActivity.this.P0()) {
                if (motionEvent.getAction() == 1) {
                    FullScreenPlayerActivity.this.I0();
                }
                return true;
            }
            if (!FullScreenPlayerActivity.this.R0()) {
                return zOnTouchEvent;
            }
            if (motionEvent.getAction() == 1) {
                FullScreenPlayerActivity.this.T0();
            }
            return true;
        }
    }

    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FullScreenPlayerActivity.this.C0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    class j extends GestureDetector.SimpleOnGestureListener {
        private long X;
        private float q;

        j() {
        }

        private void a(long j, boolean z, boolean z2) {
            if (z2) {
                FullScreenPlayerActivity.this.C0.setText(B.n(j));
            }
            FullScreenPlayerActivity.this.O0 = j;
        }

        private void b() {
            if (FullScreenPlayerActivity.this.L0() == null) {
                return;
            }
            FullScreenPlayerActivity.this.P0 = true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            int width = FullScreenPlayerActivity.this.X.getWidth();
            if (width > 0) {
                int i = width / 3;
                int i2 = (width * 2) / 3;
                if (motionEvent.getX() < i) {
                    FullScreenPlayerActivity.this.H0(false);
                    return true;
                }
                if (motionEvent.getX() > i2) {
                    FullScreenPlayerActivity.this.H0(true);
                }
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return FullScreenPlayerActivity.this.L0() != null && Math.abs(f) >= Math.abs(f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (FullScreenPlayerActivity.this.L0() == null) {
                return;
            }
            FullScreenPlayerActivity.this.V0();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (FullScreenPlayerActivity.this.L0() == null || motionEvent == null || motionEvent2 == null) {
                return false;
            }
            if (!FullScreenPlayerActivity.this.P0()) {
                if (Math.abs(f) < Math.abs(f2) || Math.abs(motionEvent.getX() - motionEvent2.getX()) < FullScreenPlayerActivity.this.Q0 || !FullScreenPlayerActivity.this.B0()) {
                    return false;
                }
                this.q = motionEvent.getX();
                this.X = FullScreenPlayerActivity.this.N0;
                b();
            }
            if (!FullScreenPlayerActivity.this.P0()) {
                C2461b.e("what case is this : " + this.q + "," + motionEvent.getX());
                return true;
            }
            long jF = this.X + ((((long) B.f(FullScreenPlayerActivity.this, (int) (motionEvent2.getX() - this.q))) * 40000) / 360);
            long j = FullScreenPlayerActivity.this.M0;
            if (j < 0) {
                j = 0;
            }
            if (jF < 0) {
                jF = 0;
            } else if (jF > j) {
                jF = j;
            }
            a(jF, f > 0.0f, true);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }
    }

    class k implements Runnable {
        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FullScreenPlayerActivity.this.r1();
        }
    }

    class l extends MediaControllerCompat.a {
        l() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void c(Bundle bundle) {
            if (bundle != null) {
                FullScreenPlayerActivity.this.s1(bundle);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                FullScreenPlayerActivity.this.o1(mediaMetadataCompat);
                FullScreenPlayerActivity.this.k1(mediaMetadataCompat);
                FullScreenPlayerActivity.this.q1(mediaMetadataCompat);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            ax.Y4.b.a(FullScreenPlayerActivity.f1, "onPlaybackstate changed", playbackStateCompat);
            FullScreenPlayerActivity.this.p1(playbackStateCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void h(int i) {
            FullScreenPlayerActivity.this.t1(i);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void l(int i) {
            FullScreenPlayerActivity.this.u1(i);
        }
    }

    class m extends MediaBrowserCompat.c {
        m() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a() {
            ax.Y4.b.a(FullScreenPlayerActivity.f1, "onConnected");
            try {
                FullScreenPlayerActivity fullScreenPlayerActivity = FullScreenPlayerActivity.this;
                fullScreenPlayerActivity.E0(fullScreenPlayerActivity.L0.c());
            } catch (RemoteException e) {
                ax.Y4.b.b(FullScreenPlayerActivity.f1, e, "could not connect media controller");
            }
        }
    }

    class n extends ax.p182l3.c {
        n() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            int iH = FullScreenPlayerActivity.this.L0().h();
            MediaControllerCompat.e eVarI = FullScreenPlayerActivity.this.L0().i();
            int i = iH == 1 ? 0 : 1;
            eVarI.h(i);
            if (FullScreenPlayerActivity.this.Y.isAccessibilityFocused()) {
                if (i == 1) {
                    FullScreenPlayerActivity.this.Y.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.shuffle_on));
                } else if (i == 0) {
                    FullScreenPlayerActivity.this.Y.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.shuffle_off));
                }
            }
            ax.U2.a.k().o("menu_music_player", "shuffle").c("loc", "fullscreen_player").e();
        }
    }

    class o extends ax.p182l3.c {
        o() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            int i;
            int iF = FullScreenPlayerActivity.this.L0().f();
            MediaControllerCompat.e eVarI = FullScreenPlayerActivity.this.L0().i();
            if (iF == 2) {
                i = 3;
            } else if (iF == 3) {
                i = 1;
            } else {
                i = iF == 1 ? 0 : 2;
            }
            eVarI.g(i);
            if (FullScreenPlayerActivity.this.Z.isAccessibilityFocused()) {
                if (i == 2) {
                    FullScreenPlayerActivity.this.Z.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_all));
                } else if (i == 0) {
                    FullScreenPlayerActivity.this.Z.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_none));
                } else if (i == 1) {
                    FullScreenPlayerActivity.this.Z.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_single));
                } else if (i == 3) {
                    FullScreenPlayerActivity.this.Z.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_none_single));
                }
            }
            ax.U2.a.k().o("menu_music_player", "repeat").c("loc", "fullscreen_player").e();
        }
    }

    class p extends ax.p182l3.c {
        p() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            FullScreenPlayerActivity.this.L0().i().i();
            ax.U2.a.k().o("menu_music_player", "next").c("loc", "fullscreen_player").e();
        }
    }

    class q extends ax.p182l3.c {
        q() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            FullScreenPlayerActivity.this.L0().i().j();
            ax.U2.a.k().o("menu_music_player", "prev").c("loc", "fullscreen_player").e();
        }
    }

    class r extends ax.p182l3.c {
        r() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            if (FullScreenPlayerActivity.this.L0() == null) {
                return;
            }
            FullScreenPlayerActivity fullScreenPlayerActivity = FullScreenPlayerActivity.this;
            fullScreenPlayerActivity.F0(fullScreenPlayerActivity.L0(), false, false);
        }
    }

    class s extends ax.p182l3.c {
        s() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            FullScreenPlayerActivity.this.b1(-10000L);
        }
    }

    private long A0(long j2) {
        long j3 = this.N0 + j2;
        long j4 = this.M0;
        if (j4 < 0) {
            j4 = 0;
        }
        if (j3 < 0) {
            return 0L;
        }
        return j3 > j4 ? j4 : j3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean B0() {
        PlaybackStateCompat playbackStateCompat = this.X0;
        return (playbackStateCompat == null || playbackStateCompat.j() == 0 || this.M0 == 0 || this.N0 == -1) ? false : true;
    }

    private boolean C0() {
        return this.l0.isEnabled() && this.l0.getVisibility() == 0;
    }

    private boolean D0() {
        return this.k0.isEnabled() && this.k0.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E0(MediaSessionCompat.Token token) throws RemoteException {
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(this, token);
        if (mediaControllerCompat.d() == null && this.Z0 == null) {
            finish();
            return;
        }
        e1(mediaControllerCompat);
        if (this.Z0 != null) {
            mediaControllerCompat.i().c(Uri.fromFile(this.Z0), null);
        }
        mediaControllerCompat.j(this.c1);
        PlaybackStateCompat playbackStateCompatE = mediaControllerCompat.e();
        p1(playbackStateCompatE);
        this.S0 = playbackStateCompatE.f();
        v1();
        MediaMetadataCompat mediaMetadataCompatD = mediaControllerCompat.d();
        if (mediaMetadataCompatD != null) {
            o1(mediaMetadataCompatD);
            k1(mediaMetadataCompatD);
            q1(mediaMetadataCompatD);
        }
        Bundle bundleB = mediaControllerCompat.b();
        if (bundleB != null) {
            s1(bundleB);
        }
        t1(mediaControllerCompat.f());
        u1(mediaControllerCompat.h());
        r1();
        if (playbackStateCompatE.j() == 3 || playbackStateCompatE.j() == 6) {
            Z0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0(MediaControllerCompat mediaControllerCompat, boolean z, boolean z2) {
        PlaybackStateCompat playbackStateCompatE = mediaControllerCompat.e();
        if (playbackStateCompatE != null) {
            MediaControllerCompat.e eVarI = mediaControllerCompat.i();
            int iJ = playbackStateCompatE.j();
            if (iJ != 0 && iJ != 1 && iJ != 2) {
                if (iJ == 3 || iJ == 6) {
                    if (z2) {
                        return;
                    }
                    eVarI.a();
                    h1();
                    ax.U2.a.k().o("menu_music_player", "pause").c("loc", "fullscreen_player").e();
                    return;
                }
                if (iJ != 7) {
                    ax.Y4.b.a(f1, "onClick with state ", Integer.valueOf(playbackStateCompatE.j()));
                    return;
                }
            }
            if (z) {
                return;
            }
            eVarI.b();
            Z0();
            ax.U2.a.k().o("menu_music_player", "play").c("loc", "fullscreen_player").e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H0(boolean z) {
        if (z ? b1(10000L) : b1(-10000L)) {
            if (z) {
                this.C0.setText("+" + B.n(10000L));
            } else {
                this.C0.setText("-" + B.n(10000L));
            }
            this.C0.postDelayed(new i(), 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0() {
        this.P0 = false;
        this.C0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        if (L0() != null) {
            a1(this.O0, true);
        }
        this.O0 = 0L;
    }

    private void J0(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat.d() == null) {
            this.J0 = null;
            this.B0.setImageResource(R.drawable.ic_default_art_large);
            return;
        }
        String string = mediaDescriptionCompat.d().toString();
        this.J0 = string;
        ax.V4.a aVarI = ax.V4.a.i();
        Bitmap bitmapG = aVarI.g(string);
        if (bitmapG != null) {
            this.B0.setImageBitmap(bitmapG);
        } else {
            this.B0.setImageBitmap(null);
            aVarI.f(getApplicationContext(), string, new f());
        }
    }

    private long K0() {
        long jI = this.X0.i();
        return (this.X0.j() == 0 || this.X0.j() == 2 || this.X0.j() == 1) ? jI : (long) (jI + ((SystemClock.elapsedRealtime() - this.X0.e()) * this.X0.f()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaControllerCompat L0() {
        return MediaControllerCompat.c(this);
    }

    private void M0() {
        this.Q0 = B.e(this, 30);
        this.B0.setOnTouchListener(new h(new com.alphainventor.filemanager.viewer.a(this, 1.0f, new g()), new GestureDetector(this, this.e1)));
    }

    private void N0() {
        this.T0 = ax.p390y3.g.a(this);
        j1();
        this.Y.setOnClickListener(new n());
        this.Z.setOnClickListener(new o());
        this.l0.setOnClickListener(new p());
        this.k0.setOnClickListener(new q());
        this.m0.setOnClickListener(new r());
        this.H0.setOnClickListener(new s());
        this.I0.setOnClickListener(new a());
        this.p0.setOnSeekBarChangeListener(new b());
        this.F0.setOnClickListener(new c());
        com.alphainventor.filemanager.viewer.d dVar = new com.alphainventor.filemanager.viewer.d(this);
        this.D0.setAdapter((SpinnerAdapter) dVar);
        this.D0.setOnItemSelectedListener(new d(dVar));
        if (this.S0 != 0.0f) {
            v1();
        }
    }

    private void O0() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.q = toolbar;
        if (toolbar == null) {
            throw new IllegalStateException("Layout is required to include a Toolbar with id 'toolbar'");
        }
        setSupportActionBar(toolbar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean P0() {
        return this.P0;
    }

    private static boolean Q0(int i2) {
        return i2 == 90 || i2 == 89 || i2 == 85 || i2 == 79 || i2 == 126 || i2 == 127 || i2 == 87 || i2 == 88;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean R0() {
        return this.R0;
    }

    private boolean S0() {
        return this.Z0 != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T0() {
        this.R0 = false;
        f1(this.S0, false);
        this.G0.setVisibility(8);
    }

    private void U0(float f2) {
        PlaybackStateCompat playbackStateCompat;
        if (L0() == null || (playbackStateCompat = this.X0) == null || playbackStateCompat.j() != 3) {
            return;
        }
        this.R0 = true;
        if (f2 == 0.5f) {
            this.G0.setText("0.5X▶▶");
        } else if (f2 == 2.0f) {
            this.G0.setText("2X▶▶");
        } else {
            C2461b.f();
            this.G0.setText(f2 + "X▶▶");
        }
        f1(f2, false);
        this.G0.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V0() {
        U0(2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0() {
        U0(0.5f);
    }

    private void X0() {
        this.K0.post(this.U0);
    }

    private void Y0(File file) {
        MediaControllerCompat mediaControllerCompatL0 = L0();
        if (mediaControllerCompatL0 == null) {
            return;
        }
        mediaControllerCompatL0.i().c(Uri.fromFile(file), null);
    }

    private void Z0() {
        h1();
        if (this.V0.isShutdown()) {
            return;
        }
        this.W0 = this.V0.scheduleAtFixedRate(new e(), 100L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a1(long j2, boolean z) {
        if (L0() == null) {
            return;
        }
        if (z) {
            this.p0.setProgress((int) j2);
        }
        L0().i().e(j2);
        d1(j2);
        Z0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b1(long j2) {
        if (L0() == null || !B0()) {
            return false;
        }
        long j3 = this.N0;
        long jA0 = A0(j2);
        if (j3 == jA0) {
            return false;
        }
        a1(jA0, true);
        return true;
    }

    private void c1(boolean z) {
        this.T0 = z;
        ax.p390y3.g.b(this, z);
        j1();
        invalidateOptionsMenu();
    }

    private void d1(long j2) {
        this.N0 = j2;
    }

    private void e1(MediaControllerCompat mediaControllerCompat) {
        MediaControllerCompat.l(this, mediaControllerCompat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f1(float f2, boolean z) {
        MediaControllerCompat mediaControllerCompatL0 = L0();
        if (mediaControllerCompatL0 == null) {
            return;
        }
        mediaControllerCompatL0.i().f(f2);
        if (z) {
            this.S0 = f2;
        }
    }

    private void g1() {
        MediaMetadataCompat mediaMetadataCompatD;
        String strI;
        MediaControllerCompat mediaControllerCompatL0 = L0();
        if (mediaControllerCompatL0 == null || (mediaMetadataCompatD = mediaControllerCompatL0.d()) == null || (strI = mediaMetadataCompatD.i("__SOURCE__")) == null) {
            return;
        }
        Uri uri = Uri.parse(strI);
        String strH = C2015v.h(uri.getPath());
        if (strH == null) {
            return;
        }
        C2014u.k0(this, strH, uri);
        ax.U2.a.k().o("menu_music_player", "share").c("loc", "music_player").c("type", "file").e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h1() {
        ScheduledFuture<?> scheduledFuture = this.W0;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i1() {
        c1(!this.T0);
    }

    private void j1() {
        if (this.T0) {
            this.E0.setVisibility(0);
            this.F0.setImageResource(R.drawable.ic_collapse_all);
            this.F0.setContentDescription(getString(R.string.menu_collapse));
        } else {
            this.E0.setVisibility(8);
            this.F0.setImageResource(R.drawable.ic_expand_all);
            this.F0.setContentDescription(getString(R.string.menu_expand));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k1(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return;
        }
        ax.Y4.b.a(f1, "updateDuration called ");
        this.M0 = mediaMetadataCompat.f("android.media.metadata.DURATION");
        d1(-1L);
        int i2 = (int) this.M0;
        this.p0.setMax(i2);
        this.o0.setText(DateUtils.formatElapsedTime(i2 / 1000));
    }

    private void l1(Intent intent) {
        MediaDescriptionCompat mediaDescriptionCompat;
        if (intent == null || (mediaDescriptionCompat = (MediaDescriptionCompat) intent.getParcelableExtra("com.example.android.uamp.CURRENT_MEDIA_DESCRIPTION")) == null) {
            return;
        }
        n1(mediaDescriptionCompat);
    }

    private void m1(int i2) {
        TextView textView = this.t0;
        if (textView == null) {
            return;
        }
        if (i2 < 400) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.addRule(2, R.id.controllers);
            this.t0.setLayoutParams(layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams2.addRule(2, R.id.buttons_line);
            this.t0.setLayoutParams(layoutParams2);
        }
    }

    private void n1(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat == null) {
            return;
        }
        ax.Y4.b.a(f1, "updateMediaDescription called ");
        this.q0.setText(mediaDescriptionCompat.b());
        this.r0.setText(mediaDescriptionCompat.i());
        this.s0.setText(mediaDescriptionCompat.h());
        J0(mediaDescriptionCompat);
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o1(MediaMetadataCompat mediaMetadataCompat) {
        n1(ax.V4.k.b(mediaMetadataCompat));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p1(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return;
        }
        this.X0 = playbackStateCompat;
        int iJ = playbackStateCompat.j();
        if (iJ == 0 || iJ == 1) {
            this.v0.setVisibility(0);
            this.m0.setVisibility(0);
            this.m0.setImageDrawable(this.x0);
            this.m0.setContentDescription(getString(R.string.playback_play_description));
            this.u0.setVisibility(4);
            this.t0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
            h1();
            X0();
        } else if (iJ == 2) {
            this.v0.setVisibility(0);
            this.m0.setVisibility(0);
            this.m0.setImageDrawable(this.x0);
            this.m0.setContentDescription(getString(R.string.playback_play_description));
            this.u0.setVisibility(4);
            this.t0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
            h1();
        } else if (iJ == 3) {
            this.m0.setVisibility(0);
            this.m0.setImageDrawable(this.w0);
            this.m0.setContentDescription(getString(R.string.playback_pause_description));
            this.v0.setVisibility(0);
            this.u0.setVisibility(4);
            this.t0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
            Z0();
        } else if (iJ == 6) {
            this.m0.setVisibility(0);
            this.m0.setImageDrawable(this.w0);
            this.m0.setContentDescription(getString(R.string.playback_pause_description));
            this.u0.setVisibility(0);
            this.t0.setText(R.string.loading);
            h1();
        } else if (iJ != 7) {
            ax.Y4.b.a(f1, "Unhandled state ", Integer.valueOf(playbackStateCompat.j()));
        } else {
            this.v0.setVisibility(0);
            this.m0.setVisibility(0);
            this.m0.setImageDrawable(this.x0);
            this.m0.setContentDescription(getString(R.string.playback_play_description));
            this.u0.setVisibility(4);
            if (ax.p390y3.k.p(this)) {
                this.t0.setText(getString(R.string.error) + " : " + ((Object) playbackStateCompat.c()));
            } else {
                this.t0.setText(R.string.error);
            }
            h1();
        }
        this.l0.setVisibility((playbackStateCompat.b() & 32) == 0 ? 4 : 0);
        this.k0.setVisibility((playbackStateCompat.b() & 16) == 0 ? 4 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q1(MediaMetadataCompat mediaMetadataCompat) {
        if (((int) mediaMetadataCompat.f("__TRACK_COUNT__")) <= 1) {
            this.l0.setEnabled(false);
            this.l0.setAlpha(0.5f);
            this.k0.setEnabled(false);
            this.k0.setAlpha(0.5f);
            return;
        }
        this.l0.setEnabled(true);
        this.l0.setAlpha(1.0f);
        this.k0.setEnabled(true);
        this.k0.setAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r1() {
        if (this.X0 == null) {
            return;
        }
        long jK0 = K0();
        this.p0.setProgress((int) jK0);
        d1(jK0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s1(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        int i2 = bundle.getInt("file.manager.music.player.QUEUE_POSITION", -1);
        int i3 = bundle.getInt("file.manager.music.player.QUEUE_SIZE", -1);
        if (i2 <= 0 || i3 <= 0 || getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().I(getResources().getString(R.string.photo_view_count, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t1(int i2) {
        if (i2 == 1) {
            this.Z.setImageDrawable(this.z0);
            this.Z.setAlpha(1.0f);
        } else if (i2 == 2) {
            this.Z.setImageDrawable(this.y0);
            this.Z.setAlpha(1.0f);
        } else if (i2 == 3) {
            this.Z.setImageDrawable(this.A0);
            this.Z.setAlpha(1.0f);
        } else {
            this.Z.setImageDrawable(this.y0);
            this.Z.setAlpha(0.35f);
        }
        if (Q.a()) {
            if (i2 == 1) {
                this.Z.setStateDescription(getString(R.string.repeat_single));
                return;
            }
            if (i2 == 2) {
                this.Z.setStateDescription(getString(R.string.repeat_all));
            } else if (i2 == 3) {
                this.Z.setStateDescription(getString(R.string.repeat_none_single));
            } else {
                this.Z.setStateDescription(getString(R.string.repeat_none));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u1(int i2) {
        if (i2 == 1) {
            this.Y.setAlpha(1.0f);
        } else {
            this.Y.setAlpha(0.35f);
        }
        if (Q.a()) {
            if (i2 == 1) {
                this.Y.setStateDescription(getString(R.string.shuffle_on));
            } else {
                this.Y.setStateDescription(getString(R.string.shuffle_off));
            }
        }
    }

    private void v1() {
        this.D0.setSelection(com.alphainventor.filemanager.viewer.d.c(this.S0));
    }

    @Override // ax.p102g3.Z
    public void B(int i2, int i3) {
        m1(i3);
    }

    public boolean G0(int i2, KeyEvent keyEvent) {
        MediaControllerCompat mediaControllerCompatL0;
        MediaControllerCompat.e eVarI;
        if (!Q0(i2) || (mediaControllerCompatL0 = L0()) == null || (eVarI = mediaControllerCompatL0.i()) == null) {
            return false;
        }
        if (i2 == 90) {
            H0(true);
        } else if (i2 == 89) {
            H0(false);
        } else if (keyEvent.getRepeatCount() == 0) {
            if (i2 == 79 || i2 == 85) {
                F0(mediaControllerCompatL0, false, false);
            } else if (i2 != 87) {
                if (i2 != 88) {
                    if (i2 == 126) {
                        F0(mediaControllerCompatL0, false, true);
                    } else if (i2 == 127) {
                        F0(mediaControllerCompatL0, true, false);
                    }
                } else if (D0()) {
                    eVarI.j();
                }
            } else if (C0()) {
                eVarI.i();
            }
        }
        return true;
    }

    @Override // ax.p210n.ActivityC2164c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.Y0.v(configuration);
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_full_player);
        O0();
        if (getSupportActionBar() != null) {
            getSupportActionBar().w(true);
            getSupportActionBar().I(HttpUrl.FRAGMENT_ENCODE_SET);
        }
        this.X = findViewById(R.id.pageView);
        this.B0 = (ImageView) findViewById(R.id.background_image);
        this.C0 = (TextView) findViewById(R.id.drag_seek_text);
        this.w0 = ax.p374x3.a.c(this, R.drawable.ic_pause);
        this.x0 = ax.p374x3.a.c(this, R.drawable.ic_play_arrow);
        this.y0 = ax.p374x3.a.c(this, R.drawable.ic_repeat);
        this.z0 = ax.p374x3.a.c(this, R.drawable.ic_repeat_one);
        this.A0 = ax.p374x3.a.c(this, R.drawable.ic_play_one);
        this.m0 = (ImageView) findViewById(R.id.play_pause);
        this.l0 = (ImageView) findViewById(R.id.next);
        this.k0 = (ImageView) findViewById(R.id.prev);
        this.Y = (ImageView) findViewById(R.id.shuffle);
        this.Z = (ImageView) findViewById(R.id.repeat);
        this.n0 = (TextView) findViewById(R.id.startText);
        this.o0 = (TextView) findViewById(R.id.endText);
        this.p0 = (SeekBar) findViewById(R.id.seekBar1);
        this.q0 = (TextView) findViewById(R.id.line0);
        this.r0 = (TextView) findViewById(R.id.line1);
        this.s0 = (TextView) findViewById(R.id.line2);
        this.t0 = (TextView) findViewById(R.id.line3);
        this.H0 = findViewById(R.id.rew10);
        this.I0 = findViewById(R.id.ffwd10);
        this.u0 = (ProgressBar) findViewById(R.id.progressBar1);
        this.v0 = findViewById(R.id.controllers);
        MySpinner mySpinner = (MySpinner) findViewById(R.id.custom_speed);
        this.D0 = mySpinner;
        mySpinner.setPromptId(R.string.playback_speed);
        this.E0 = findViewById(R.id.control_buttons_expanded_line);
        this.F0 = (ImageView) findViewById(R.id.custom_toggle_expand);
        this.G0 = (TextView) findViewById(R.id.change_speed_playing_indicator);
        if (!Q.R()) {
            this.D0.setVisibility(4);
        }
        M0();
        N0();
        if (bundle == null) {
            l1(getIntent());
        }
        this.L0 = new MediaBrowserCompat(this, new ComponentName(this, (Class<?>) MusicService.class), this.d1, null);
        H0 h0 = new H0(this);
        this.Y0 = h0;
        h0.m(null);
        String stringExtra = getIntent().getStringExtra("extra_temp_file_path");
        this.a1 = getIntent().getStringExtra("extra_temp_file_type");
        if (stringExtra != null) {
            w.j(this);
            File file = new File(stringExtra);
            if (file.exists()) {
                this.Z0 = file;
                Y0(file);
            } else {
                Toast.makeText(this, R.string.error_file_load, 1).show();
                finish();
            }
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.full_screen_player, menu);
        return true;
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        h1();
        this.V0.shutdown();
        if (this.Z0 != null) {
            MediaControllerCompat mediaControllerCompatL0 = L0();
            if (mediaControllerCompatL0 != null) {
                mediaControllerCompatL0.i().k();
            }
            if (this.b1) {
                return;
            }
            this.Z0.delete();
        }
    }

    @Override // ax.p210n.ActivityC2164c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return G0(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_equalizer /* 2131362513 */:
                Intent intent = new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
                if (ax.X4.e.H() != 0) {
                    intent.putExtra("android.media.extra.AUDIO_SESSION", ax.X4.e.H());
                }
                try {
                    C2014u.q0(this, intent, 1020);
                    break;
                } catch (ActivityNotFoundException | SecurityException unused) {
                    Toast.makeText(this, R.string.error, 1).show();
                }
                return true;
            case R.id.menu_save /* 2131362538 */:
                File file = this.Z0;
                if (file != null) {
                    this.b1 = true;
                    C2014u.s0(this, Uri.fromFile(file), this.a1);
                    finish();
                } else {
                    Toast.makeText(this, R.string.error, 1).show();
                }
                return true;
            case R.id.menu_share /* 2131362547 */:
                g1();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        MediaMetadataCompat mediaMetadataCompatD;
        MediaControllerCompat mediaControllerCompatL0 = L0();
        String strI = null;
        boolean z = mediaControllerCompatL0 == null || (mediaMetadataCompatD = mediaControllerCompatL0.d()) == null || (strI = mediaMetadataCompatD.i("__SOURCE__")) == null;
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_share);
        if (menuItemFindItem != null) {
            if (z || S0() || !ax.W4.b.m(strI)) {
                menuItemFindItem.setVisible(false);
            } else {
                menuItemFindItem.setVisible(true);
            }
        }
        MenuItem menuItemFindItem2 = menu.findItem(R.id.menu_equalizer);
        if (menuItemFindItem2 != null) {
            Intent intent = new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
            if (z || S0() || intent.resolveActivity(getPackageManager()) == null) {
                menuItemFindItem2.setVisible(false);
            } else {
                menuItemFindItem2.setVisible(true);
            }
        }
        MenuItem menuItemFindItem3 = menu.findItem(R.id.menu_save);
        if (menuItemFindItem3 != null) {
            if (!z && S0()) {
                menuItemFindItem3.setVisible(true);
            } else {
                menuItemFindItem3.setVisible(false);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    public void onStart() {
        super.onStart();
        MediaBrowserCompat mediaBrowserCompat = this.L0;
        if (mediaBrowserCompat != null) {
            try {
                mediaBrowserCompat.a();
            } catch (IllegalStateException e2) {
                ax.p302sb.c.h().g().b("MEDIA BROWSER CONNECT IN FULL PLAYER").m(e2).h("connected:" + this.L0.d()).i();
            }
        }
        if (Q.N1()) {
            v.u(getWindow(), -16777216);
            v.r(getWindow(), -16777216);
        }
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    public void onStop() {
        super.onStop();
        MediaBrowserCompat mediaBrowserCompat = this.L0;
        if (mediaBrowserCompat != null) {
            mediaBrowserCompat.b();
        }
        if (L0() != null) {
            L0().m(this.c1);
        }
    }
}
