package com.alphainventor.filemanager.musicplayer;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import ax.E1.P;
import ax.E1.u;
import ax.J1.C0734o;
import ax.J1.C0735p;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.example.android.uamp.MusicService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class FullScreenPlayerActivity extends ActivityC5571c {
    private static final String p1 = ax.B3.b.f(FullScreenPlayerActivity.class);
    private Toolbar E0;
    private ImageView F0;
    private ImageView G0;
    private ImageView H0;
    private ImageView I0;
    private ImageView J0;
    private TextView K0;
    private TextView L0;
    private SeekBar M0;
    private TextView N0;
    private TextView O0;
    private TextView P0;
    private TextView Q0;
    private ProgressBar R0;
    private View S0;
    private Drawable T0;
    private Drawable U0;
    private Drawable V0;
    private Drawable W0;
    private Drawable X0;
    private ImageView Y0;
    private TextView Z0;
    private String a1;
    private MediaBrowserCompat c1;
    private long d1;
    private long e1;
    private long f1;
    private boolean g1;
    private int h1;
    private ScheduledFuture<?> k1;
    private PlaybackStateCompat l1;
    private final Handler b1 = new Handler();
    private final Runnable i1 = new f();
    private final ScheduledExecutorService j1 = Executors.newSingleThreadScheduledExecutor();
    private final MediaControllerCompat.a m1 = new g();
    private final MediaBrowserCompat.c n1 = new h();
    private final GestureDetector.SimpleOnGestureListener o1 = new e();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FullScreenPlayerActivity.this.b1.post(FullScreenPlayerActivity.this.i1);
        }
    }

    class b extends ax.p340y3.a.AbstractC0481a {
        b() {
        }

        @Override // ax.p340y3.a.AbstractC0481a
        public void a(String str, Exception exc) {
            super.a(str, exc);
            if (str.equals(FullScreenPlayerActivity.this.a1)) {
                FullScreenPlayerActivity.this.Y0.setImageResource(R.drawable.ic_default_art_large);
            }
        }

        @Override // ax.p340y3.a.AbstractC0481a
        public void b(String str, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
            if (str.equals(FullScreenPlayerActivity.this.a1)) {
                FullScreenPlayerActivity.this.Y0.setImageBitmap(bitmap);
            }
        }
    }

    class c implements View.OnTouchListener {
        final /* synthetic */ GestureDetector q;

        c(GestureDetector gestureDetector) {
            this.q = gestureDetector;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean zOnTouchEvent = this.q.onTouchEvent(motionEvent);
            if (!FullScreenPlayerActivity.this.O1()) {
                return zOnTouchEvent;
            }
            if (motionEvent.getAction() == 1) {
                FullScreenPlayerActivity.this.J1();
            }
            return true;
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FullScreenPlayerActivity.this.Z0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    class e extends GestureDetector.SimpleOnGestureListener {
        private long X;
        private float q;

        e() {
        }

        private void a(long j, boolean z, boolean z2) {
            if (z2) {
                FullScreenPlayerActivity.this.Z0.setText(x.p(j));
            }
            FullScreenPlayerActivity.this.f1 = j;
        }

        private void b() {
            if (FullScreenPlayerActivity.this.L1() == null) {
                return;
            }
            FullScreenPlayerActivity.this.g1 = true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            int width = FullScreenPlayerActivity.this.Y0.getWidth();
            if (width > 0) {
                int i = width / 3;
                int i2 = (width * 2) / 3;
                if (motionEvent.getX() < i) {
                    FullScreenPlayerActivity.this.I1(false);
                    return true;
                }
                if (motionEvent.getX() > i2) {
                    FullScreenPlayerActivity.this.I1(true);
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
            return FullScreenPlayerActivity.this.L1() != null && Math.abs(f) >= Math.abs(f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (FullScreenPlayerActivity.this.L1() == null || motionEvent == null || motionEvent2 == null) {
                return false;
            }
            if (!FullScreenPlayerActivity.this.O1()) {
                if (Math.abs(f) < Math.abs(f2) || Math.abs(motionEvent.getX() - motionEvent2.getX()) < FullScreenPlayerActivity.this.h1 || !FullScreenPlayerActivity.this.C1()) {
                    return false;
                }
                this.q = motionEvent.getX();
                this.X = FullScreenPlayerActivity.this.e1;
                b();
            }
            if (!FullScreenPlayerActivity.this.O1()) {
                ax.p045d2.b.e("what case is this : " + this.q + "," + motionEvent.getX());
                return true;
            }
            long jF = this.X + ((((long) x.f(FullScreenPlayerActivity.this, (int) (motionEvent2.getX() - this.q))) * 40000) / 360);
            long j = FullScreenPlayerActivity.this.d1;
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

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FullScreenPlayerActivity.this.d2();
        }
    }

    class g extends MediaControllerCompat.a {
        g() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void c(Bundle bundle) {
            if (bundle != null) {
                FullScreenPlayerActivity.this.e2(bundle);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                FullScreenPlayerActivity.this.a2(mediaMetadataCompat);
                FullScreenPlayerActivity.this.X1(mediaMetadataCompat);
                FullScreenPlayerActivity.this.c2(mediaMetadataCompat);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            ax.B3.b.a(FullScreenPlayerActivity.p1, "onPlaybackstate changed", playbackStateCompat);
            FullScreenPlayerActivity.this.b2(playbackStateCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void h(int i) {
            FullScreenPlayerActivity.this.f2(i);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void l(int i) {
            FullScreenPlayerActivity.this.g2(i);
        }
    }

    class h extends MediaBrowserCompat.c {
        h() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a() {
            ax.B3.b.a(FullScreenPlayerActivity.p1, "onConnected");
            try {
                FullScreenPlayerActivity fullScreenPlayerActivity = FullScreenPlayerActivity.this;
                fullScreenPlayerActivity.F1(fullScreenPlayerActivity.c1.c());
            } catch (RemoteException e) {
                ax.B3.b.b(FullScreenPlayerActivity.p1, e, "could not connect media controller");
            }
        }
    }

    class i extends ax.P1.c {
        i() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            int iH = FullScreenPlayerActivity.this.L1().h();
            MediaControllerCompat.e eVarI = FullScreenPlayerActivity.this.L1().i();
            int i = iH == 1 ? 0 : 1;
            eVarI.g(i);
            if (FullScreenPlayerActivity.this.F0.isAccessibilityFocused()) {
                if (i == 1) {
                    FullScreenPlayerActivity.this.F0.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.shuffle_on));
                } else if (i == 0) {
                    FullScreenPlayerActivity.this.F0.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.shuffle_off));
                }
            }
            ax.p338y1.a.k().o("menu_music_player", "shuffle").c("loc", "fullscreen_player").e();
        }
    }

    class j extends ax.P1.c {
        j() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            int i;
            int iF = FullScreenPlayerActivity.this.L1().f();
            MediaControllerCompat.e eVarI = FullScreenPlayerActivity.this.L1().i();
            if (iF == 2) {
                i = 3;
            } else if (iF == 3) {
                i = 1;
            } else {
                i = iF == 1 ? 0 : 2;
            }
            eVarI.f(i);
            if (FullScreenPlayerActivity.this.G0.isAccessibilityFocused()) {
                if (i == 2) {
                    FullScreenPlayerActivity.this.G0.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_all));
                } else if (i == 0) {
                    FullScreenPlayerActivity.this.G0.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_none));
                } else if (i == 1) {
                    FullScreenPlayerActivity.this.G0.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_single));
                } else if (i == 3) {
                    FullScreenPlayerActivity.this.G0.announceForAccessibility(FullScreenPlayerActivity.this.getString(R.string.repeat_none_single));
                }
            }
            ax.p338y1.a.k().o("menu_music_player", "repeat").c("loc", "fullscreen_player").e();
        }
    }

    class k extends ax.P1.c {
        k() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            FullScreenPlayerActivity.this.L1().i().h();
            ax.p338y1.a.k().o("menu_music_player", "next").c("loc", "fullscreen_player").e();
        }
    }

    class l extends ax.P1.c {
        l() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            FullScreenPlayerActivity.this.L1().i().i();
            ax.p338y1.a.k().o("menu_music_player", "prev").c("loc", "fullscreen_player").e();
        }
    }

    class m extends ax.P1.c {
        m() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            FullScreenPlayerActivity fullScreenPlayerActivity = FullScreenPlayerActivity.this;
            fullScreenPlayerActivity.G1(fullScreenPlayerActivity.L1(), false, false);
        }
    }

    class n implements SeekBar.OnSeekBarChangeListener {
        boolean a = false;

        n() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            FullScreenPlayerActivity.this.K0.setText(DateUtils.formatElapsedTime(i / 1000));
            if (!z || this.a) {
                return;
            }
            FullScreenPlayerActivity.this.S1(seekBar.getProgress(), false);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            this.a = true;
            FullScreenPlayerActivity.this.W1();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            this.a = false;
            FullScreenPlayerActivity.this.S1(seekBar.getProgress(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean C1() {
        PlaybackStateCompat playbackStateCompat = this.l1;
        return (playbackStateCompat == null || playbackStateCompat.h() == 0 || this.d1 == 0 || this.e1 == -1) ? false : true;
    }

    private boolean D1() {
        return this.I0.isEnabled() && this.I0.getVisibility() == 0;
    }

    private boolean E1() {
        return this.H0.isEnabled() && this.H0.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F1(MediaSessionCompat.Token token) throws RemoteException {
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(this, token);
        if (mediaControllerCompat.d() == null) {
            finish();
            return;
        }
        U1(mediaControllerCompat);
        mediaControllerCompat.j(this.m1);
        PlaybackStateCompat playbackStateCompatE = mediaControllerCompat.e();
        b2(playbackStateCompatE);
        MediaMetadataCompat mediaMetadataCompatD = mediaControllerCompat.d();
        if (mediaMetadataCompatD != null) {
            a2(mediaMetadataCompatD);
            X1(mediaMetadataCompatD);
            c2(mediaMetadataCompatD);
        }
        Bundle bundleB = mediaControllerCompat.b();
        if (bundleB != null) {
            e2(bundleB);
        }
        f2(mediaControllerCompat.f());
        g2(mediaControllerCompat.h());
        d2();
        if (playbackStateCompatE != null) {
            if (playbackStateCompatE.h() == 3 || playbackStateCompatE.h() == 6) {
                R1();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G1(MediaControllerCompat mediaControllerCompat, boolean z, boolean z2) {
        PlaybackStateCompat playbackStateCompatE = mediaControllerCompat.e();
        if (playbackStateCompatE != null) {
            MediaControllerCompat.e eVarI = mediaControllerCompat.i();
            int iH = playbackStateCompatE.h();
            if (iH != 0 && iH != 1 && iH != 2) {
                if (iH == 3 || iH == 6) {
                    if (z2) {
                        return;
                    }
                    eVarI.a();
                    W1();
                    ax.p338y1.a.k().o("menu_music_player", "pause").c("loc", "fullscreen_player").e();
                    return;
                }
                if (iH != 7) {
                    ax.B3.b.a(p1, "onClick with state ", Integer.valueOf(playbackStateCompatE.h()));
                    return;
                }
            }
            if (z) {
                return;
            }
            eVarI.b();
            R1();
            ax.p338y1.a.k().o("menu_music_player", "play").c("loc", "fullscreen_player").e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I1(boolean z) {
        if (L1() != null && C1()) {
            long j2 = this.e1;
            long j3 = z ? j2 + 10000 : j2 - 10000;
            long j4 = this.d1;
            if (j4 < 0) {
                j4 = 0;
            }
            if (j3 < 0) {
                j3 = 0;
            } else if (j3 > j4) {
                j3 = j4;
            }
            if (j2 == j3) {
                return;
            }
            S1(j3, true);
            if (z) {
                this.Z0.setText("+" + x.p(10000L));
            } else {
                this.Z0.setText("-" + x.p(10000L));
            }
            this.Z0.postDelayed(new d(), 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J1() {
        this.g1 = false;
        this.Z0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        if (L1() != null) {
            S1(this.f1, true);
        }
        this.f1 = 0L;
    }

    private void K1(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat.d() == null) {
            this.a1 = null;
            this.Y0.setImageResource(R.drawable.ic_default_art_large);
            return;
        }
        String string = mediaDescriptionCompat.d().toString();
        this.a1 = string;
        ax.p340y3.a aVarI = ax.p340y3.a.i();
        Bitmap bitmapG = aVarI.g(string);
        if (bitmapG != null) {
            this.Y0.setImageBitmap(bitmapG);
        } else {
            this.Y0.setImageBitmap(null);
            aVarI.f(getApplicationContext(), string, new b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaControllerCompat L1() {
        return MediaControllerCompat.c(this);
    }

    private void M1() {
        this.h1 = x.e(this, 30);
        this.Y0.setOnTouchListener(new c(new GestureDetector(this, this.o1)));
    }

    private void N1() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.E0 = toolbar;
        if (toolbar == null) {
            throw new IllegalStateException("Layout is required to include a Toolbar with id 'toolbar'");
        }
        S0(toolbar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean O1() {
        return this.g1;
    }

    private static boolean P1(int i2) {
        return i2 == 90 || i2 == 89 || i2 == 85 || i2 == 79 || i2 == 126 || i2 == 127 || i2 == 87 || i2 == 88;
    }

    private void Q1() {
        this.b1.post(this.i1);
    }

    private void R1() {
        W1();
        if (this.j1.isShutdown()) {
            return;
        }
        this.k1 = this.j1.scheduleAtFixedRate(new a(), 100L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S1(long j2, boolean z) {
        if (L1() == null) {
            return;
        }
        if (z) {
            this.M0.setProgress((int) j2);
        }
        L1().i().e(j2);
        T1(j2);
        R1();
    }

    private void T1(long j2) {
        this.e1 = j2;
    }

    private void U1(MediaControllerCompat mediaControllerCompat) {
        MediaControllerCompat.l(this, mediaControllerCompat);
    }

    private void V1() {
        MediaMetadataCompat mediaMetadataCompatD;
        String strH;
        MediaControllerCompat mediaControllerCompatL1 = L1();
        if (mediaControllerCompatL1 == null || (mediaMetadataCompatD = mediaControllerCompatL1.d()) == null || (strH = mediaMetadataCompatD.h("__SOURCE__")) == null) {
            return;
        }
        Uri uri = Uri.parse(strH);
        String strH2 = C0735p.h(uri.getPath());
        if (strH2 == null) {
            return;
        }
        C0734o.d0(this, strH2, uri);
        ax.p338y1.a.k().o("menu_music_player", "share").c("loc", "music_player").c("type", "file").e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W1() {
        ScheduledFuture<?> scheduledFuture = this.k1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X1(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return;
        }
        ax.B3.b.a(p1, "updateDuration called ");
        this.d1 = mediaMetadataCompat.f("android.media.metadata.DURATION");
        T1(-1L);
        int i2 = (int) this.d1;
        this.M0.setMax(i2);
        this.L0.setText(DateUtils.formatElapsedTime(i2 / 1000));
    }

    private void Y1(Intent intent) {
        MediaDescriptionCompat mediaDescriptionCompat;
        if (intent == null || (mediaDescriptionCompat = (MediaDescriptionCompat) intent.getParcelableExtra("com.example.android.uamp.CURRENT_MEDIA_DESCRIPTION")) == null) {
            return;
        }
        Z1(mediaDescriptionCompat);
    }

    private void Z1(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat == null) {
            return;
        }
        ax.B3.b.a(p1, "updateMediaDescription called ");
        this.N0.setText(mediaDescriptionCompat.b());
        this.O0.setText(mediaDescriptionCompat.h());
        this.P0.setText(mediaDescriptionCompat.g());
        K1(mediaDescriptionCompat);
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a2(MediaMetadataCompat mediaMetadataCompat) {
        Z1(ax.p340y3.k.b(mediaMetadataCompat));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b2(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return;
        }
        this.l1 = playbackStateCompat;
        int iH = playbackStateCompat.h();
        if (iH == 0 || iH == 1) {
            this.S0.setVisibility(0);
            this.J0.setVisibility(0);
            this.J0.setImageDrawable(this.U0);
            this.J0.setContentDescription(getString(R.string.playback_play_description));
            this.R0.setVisibility(4);
            this.Q0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
            W1();
            Q1();
        } else if (iH == 2) {
            this.S0.setVisibility(0);
            this.J0.setVisibility(0);
            this.J0.setImageDrawable(this.U0);
            this.J0.setContentDescription(getString(R.string.playback_play_description));
            this.R0.setVisibility(4);
            this.Q0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
            W1();
        } else if (iH == 3) {
            this.J0.setVisibility(0);
            this.J0.setImageDrawable(this.T0);
            this.J0.setContentDescription(getString(R.string.playback_pause_description));
            this.S0.setVisibility(0);
            this.R0.setVisibility(4);
            this.Q0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
            R1();
        } else if (iH == 6) {
            this.J0.setVisibility(0);
            this.J0.setImageDrawable(this.T0);
            this.J0.setContentDescription(getString(R.string.playback_pause_description));
            this.R0.setVisibility(0);
            this.Q0.setText(R.string.loading);
            W1();
        } else if (iH != 7) {
            ax.B3.b.a(p1, "Unhandled state ", Integer.valueOf(playbackStateCompat.h()));
        } else {
            this.S0.setVisibility(0);
            this.J0.setVisibility(0);
            this.J0.setImageDrawable(this.U0);
            this.J0.setContentDescription(getString(R.string.playback_play_description));
            this.R0.setVisibility(4);
            if (ax.p031c2.i.n(this)) {
                this.Q0.setText(getString(R.string.error) + " : " + ((Object) playbackStateCompat.c()));
            } else {
                this.Q0.setText(R.string.error);
            }
            W1();
        }
        this.I0.setVisibility((playbackStateCompat.b() & 32) == 0 ? 4 : 0);
        this.H0.setVisibility((playbackStateCompat.b() & 16) == 0 ? 4 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c2(MediaMetadataCompat mediaMetadataCompat) {
        if (((int) mediaMetadataCompat.f("__TRACK_COUNT__")) <= 1) {
            this.I0.setEnabled(false);
            this.I0.setAlpha(0.5f);
            this.H0.setEnabled(false);
            this.H0.setAlpha(0.5f);
            return;
        }
        this.I0.setEnabled(true);
        this.I0.setAlpha(1.0f);
        this.H0.setEnabled(true);
        this.H0.setAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d2() {
        PlaybackStateCompat playbackStateCompat = this.l1;
        if (playbackStateCompat == null) {
            return;
        }
        long jG = playbackStateCompat.g();
        if (this.l1.h() != 0 && this.l1.h() != 2 && this.l1.h() != 1) {
            jG = (long) (jG + (((int) (SystemClock.elapsedRealtime() - this.l1.d())) * this.l1.e()));
        }
        this.M0.setProgress((int) jG);
        T1(jG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e2(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        int i2 = bundle.getInt("file.manager.music.player.QUEUE_POSITION", -1);
        int i3 = bundle.getInt("file.manager.music.player.QUEUE_SIZE", -1);
        if (i2 <= 0 || i3 <= 0 || I0() == null) {
            return;
        }
        I0().F(getResources().getString(R.string.photo_view_count, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f2(int i2) {
        if (i2 == 1) {
            this.G0.setImageDrawable(this.W0);
            this.G0.setAlpha(1.0f);
        } else if (i2 == 2) {
            this.G0.setImageDrawable(this.V0);
            this.G0.setAlpha(1.0f);
        } else if (i2 == 3) {
            this.G0.setImageDrawable(this.X0);
            this.G0.setAlpha(1.0f);
        } else {
            this.G0.setImageDrawable(this.V0);
            this.G0.setAlpha(0.35f);
        }
        if (P.a()) {
            if (i2 == 1) {
                this.G0.setStateDescription(getString(R.string.repeat_single));
                return;
            }
            if (i2 == 2) {
                this.G0.setStateDescription(getString(R.string.repeat_all));
            } else if (i2 == 3) {
                this.G0.setStateDescription(getString(R.string.repeat_none_single));
            } else {
                this.G0.setStateDescription(getString(R.string.repeat_none));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g2(int i2) {
        if (i2 == 1) {
            this.F0.setAlpha(1.0f);
        } else {
            this.F0.setAlpha(0.35f);
        }
        if (P.a()) {
            if (i2 == 1) {
                this.F0.setStateDescription(getString(R.string.shuffle_on));
            } else {
                this.F0.setStateDescription(getString(R.string.shuffle_off));
            }
        }
    }

    public boolean H1(int i2, KeyEvent keyEvent) {
        MediaControllerCompat mediaControllerCompatL1;
        MediaControllerCompat.e eVarI;
        if (!P1(i2) || (mediaControllerCompatL1 = L1()) == null || (eVarI = mediaControllerCompatL1.i()) == null) {
            return false;
        }
        if (i2 == 90) {
            I1(true);
        } else if (i2 == 89) {
            I1(false);
        } else if (keyEvent.getRepeatCount() == 0) {
            if (i2 == 79 || i2 == 85) {
                G1(mediaControllerCompatL1, false, false);
            } else if (i2 != 87) {
                if (i2 != 88) {
                    if (i2 == 126) {
                        G1(mediaControllerCompatL1, false, true);
                    } else if (i2 == 127) {
                        G1(mediaControllerCompatL1, true, false);
                    }
                } else if (E1()) {
                    eVarI.i();
                }
            } else if (D1()) {
                eVarI.h();
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_full_player);
        N1();
        if (I0() != null) {
            I0().u(true);
            I0().F(HttpUrl.FRAGMENT_ENCODE_SET);
        }
        this.Y0 = (ImageView) findViewById(R.id.background_image);
        this.Z0 = (TextView) findViewById(R.id.drag_seek_text);
        this.T0 = ax.p017b2.a.c(this, R.drawable.ic_pause);
        this.U0 = ax.p017b2.a.c(this, R.drawable.ic_play_arrow);
        this.V0 = ax.p017b2.a.c(this, R.drawable.ic_repeat);
        this.W0 = ax.p017b2.a.c(this, R.drawable.ic_repeat_one);
        this.X0 = ax.p017b2.a.c(this, R.drawable.ic_play_one);
        this.J0 = (ImageView) findViewById(R.id.play_pause);
        this.I0 = (ImageView) findViewById(R.id.next);
        this.H0 = (ImageView) findViewById(R.id.prev);
        this.F0 = (ImageView) findViewById(R.id.shuffle);
        this.G0 = (ImageView) findViewById(R.id.repeat);
        this.K0 = (TextView) findViewById(R.id.startText);
        this.L0 = (TextView) findViewById(R.id.endText);
        this.M0 = (SeekBar) findViewById(R.id.seekBar1);
        this.N0 = (TextView) findViewById(R.id.line0);
        this.O0 = (TextView) findViewById(R.id.line1);
        this.P0 = (TextView) findViewById(R.id.line2);
        this.Q0 = (TextView) findViewById(R.id.line3);
        this.R0 = (ProgressBar) findViewById(R.id.progressBar1);
        this.S0 = findViewById(R.id.controllers);
        M1();
        this.F0.setOnClickListener(new i());
        this.G0.setOnClickListener(new j());
        this.I0.setOnClickListener(new k());
        this.H0.setOnClickListener(new l());
        this.J0.setOnClickListener(new m());
        this.M0.setOnSeekBarChangeListener(new n());
        if (bundle == null) {
            Y1(getIntent());
        }
        this.c1 = new MediaBrowserCompat(this, new ComponentName(this, (Class<?>) MusicService.class), this.n1, null);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.full_screen_player, menu);
        return true;
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        W1();
        this.j1.shutdown();
    }

    @Override // ax.p182n.ActivityC5571c, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return H1(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
            return true;
        }
        if (itemId != R.id.menu_equalizer) {
            if (itemId != R.id.menu_share) {
                return super.onOptionsItemSelected(menuItem);
            }
            V1();
            return true;
        }
        Intent intent = new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
        if (ax.A3.a.p() != 0) {
            intent.putExtra("android.media.extra.AUDIO_SESSION", ax.A3.a.p());
        }
        try {
            C0734o.j0(this, intent, 1020);
        } catch (ActivityNotFoundException | SecurityException unused) {
            Toast.makeText(this, R.string.error, 1).show();
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        MediaMetadataCompat mediaMetadataCompatD;
        String strH;
        MediaControllerCompat mediaControllerCompatL1 = L1();
        if (mediaControllerCompatL1 == null || (mediaMetadataCompatD = mediaControllerCompatL1.d()) == null || (strH = mediaMetadataCompatD.h("__SOURCE__")) == null) {
            return true;
        }
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_share);
        if (menuItemFindItem != null) {
            if (ax.p354z3.b.m(strH)) {
                menuItemFindItem.setVisible(true);
            } else {
                menuItemFindItem.setVisible(false);
            }
        }
        MenuItem menuItemFindItem2 = menu.findItem(R.id.menu_equalizer);
        if (menuItemFindItem2 != null) {
            if (new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL").resolveActivity(getPackageManager()) != null) {
                menuItemFindItem2.setVisible(true);
            } else {
                menuItemFindItem2.setVisible(false);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    public void onStart() {
        super.onStart();
        MediaBrowserCompat mediaBrowserCompat = this.c1;
        if (mediaBrowserCompat != null) {
            try {
                mediaBrowserCompat.a();
            } catch (IllegalStateException e2) {
                ax.p221pa.c.h().g().b("MEDIA BROWSER CONNECT IN FULL PLAYER").m(e2).h("connected:" + this.c1.d()).i();
            }
        }
        if (P.x1()) {
            u.r(getWindow(), -16777216);
            u.o(getWindow(), -16777216);
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    public void onStop() {
        super.onStop();
        MediaBrowserCompat mediaBrowserCompat = this.c1;
        if (mediaBrowserCompat != null) {
            mediaBrowserCompat.b();
        }
        if (L1() != null) {
            L1().m(this.m1);
        }
    }
}
