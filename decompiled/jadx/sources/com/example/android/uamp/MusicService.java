package com.example.android.uamp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.media.session.MediaButtonReceiver;
import ax.M0.c;
import ax.V4.d;
import ax.V4.i;
import ax.V4.k;
import ax.X4.e;
import ax.X4.f;
import ax.X4.g;
import ax.X4.h;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MusicService extends c implements g.c {
    private ax.W4.b p0;
    private g q0;
    private MediaSessionCompat r0;
    private d s0;
    private final b t0 = new b(this, null);
    private ax.V4.g u0;
    private boolean v0;
    private boolean w0;
    private static final String x0 = ax.Y4.b.f(MusicService.class);
    private static boolean y0 = false;
    private static int z0 = 0;
    private static boolean A0 = false;
    private static boolean B0 = false;

    class a implements h.b {
        a() {
        }

        @Override // ax.X4.h.b
        public void J0(MediaMetadataCompat mediaMetadataCompat) {
            MusicService.this.r0.l(mediaMetadataCompat);
            Bundle bundle = new Bundle();
            bundle.putInt("file.manager.music.player.QUEUE_POSITION", MusicService.this.q0.u());
            bundle.putInt("file.manager.music.player.QUEUE_SIZE", MusicService.this.q0.v());
            MusicService.this.r0.j(bundle);
        }

        @Override // ax.X4.h.b
        public void a() {
            MusicService.this.q0.N(MusicService.this.getString(i.d), 0, null);
        }

        @Override // ax.X4.h.b
        public void b(int i) {
            MusicService.this.q0.z();
        }

        @Override // ax.X4.h.b
        public void c(String str, List<MediaSessionCompat.QueueItem> list) {
            MusicService.this.r0.n(list);
            MusicService.this.r0.o(str);
        }
    }

    private static class b extends Handler {
        private final WeakReference<MusicService> a;

        private b(MusicService musicService) {
            this.a = new WeakReference<>(musicService);
        }

        /* synthetic */ b(MusicService musicService, a aVar) {
            this(musicService);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MusicService musicService = this.a.get();
            if (musicService == null || musicService.q0.x() == null) {
                return;
            }
            if (musicService.q0.x().i()) {
                ax.Y4.b.a(MusicService.x0, "Ignoring delayed stop since the media player is in use.");
            } else {
                ax.Y4.b.a(MusicService.x0, "Stopping service with delay handler.");
                musicService.stopSelf();
            }
        }
    }

    private static int B(Context context) {
        return context.getSharedPreferences("musicplayer", 0).getInt("repeatmode", 0);
    }

    private static int C(Context context) {
        return context.getSharedPreferences("musicplayer", 0).getInt("shufflemode", 0);
    }

    public static boolean D() {
        return y0;
    }

    public static boolean E() {
        return y0 && B0;
    }

    public static boolean F() {
        return y0 && z0 == 3 && A0;
    }

    private static void G(Context context, int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("musicplayer", 0);
        if (sharedPreferences.getInt("repeatmode", 0) != i) {
            sharedPreferences.edit().putInt("repeatmode", i).apply();
        }
    }

    private static void H(Context context, int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("musicplayer", 0);
        if (sharedPreferences.getInt("shufflemode", 0) != i) {
            sharedPreferences.edit().putInt("shufflemode", i).apply();
        }
    }

    public static void I(Context context) {
        ax.L0.a.b(context).d(new Intent("local.intent.action.LOCAL_PLAYLIST_SAVE"));
    }

    public static void J(Context context) {
        ax.L0.a.b(context).d(new Intent("local.intent.action.LOCAL_PLAYLIST_CLEAR"));
    }

    public static void L(Context context, String str, String str2, int i) {
        Intent intent = new Intent("local.intent.action.LOCAL_PLAYLIST_SAVE");
        intent.putExtra("folder_uri", str);
        intent.putExtra("media_id", str2);
        intent.putExtra("current_position", i);
        ax.L0.a.b(context).d(intent);
    }

    private void M(boolean z, boolean z2) {
        this.v0 = z;
        this.w0 = z2;
    }

    @Override // ax.X4.g.c
    public void K(float f) {
    }

    @Override // ax.X4.g.c
    public void X(int i) {
        this.r0.p(i);
        G(this, i);
    }

    @Override // ax.X4.g.c
    public void a() throws ax.Y4.a {
        d dVar = this.s0;
        if (dVar != null) {
            dVar.t();
        }
    }

    @Override // ax.X4.g.c
    public void b(PlaybackStateCompat playbackStateCompat) {
        playbackStateCompat.j();
        z0 = playbackStateCompat.j();
        B0 = this.p0.j();
        A0 = this.q0.B();
        MediaSessionCompat mediaSessionCompat = this.r0;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.m(playbackStateCompat);
        }
    }

    @Override // ax.X4.g.c
    public void c(f fVar, String str, boolean z, boolean z2) {
        M(false, false);
        if (str != null) {
            if (this.q0.x() instanceof e) {
                ((e) this.q0.x()).G();
            }
            d dVar = this.s0;
            if (dVar == null || dVar.p() || !this.s0.o()) {
                this.t0.removeCallbacksAndMessages(null);
                this.t0.sendEmptyMessageDelayed(0, 30000L);
            } else {
                this.t0.removeCallbacksAndMessages(null);
                this.t0.sendEmptyMessageDelayed(0, 9500L);
                if (!ax.V4.b.a()) {
                    this.s0.r(true);
                }
            }
            if (z2) {
                J(this);
            }
        }
    }

    @Override // ax.X4.g.c
    public void d() {
        M(true, false);
        if (!this.r0.e()) {
            this.r0.g(true);
        }
        this.t0.removeCallbacksAndMessages(null);
        try {
            k.d(this, new Intent(getApplicationContext(), (Class<?>) MusicService.class));
        } catch (IllegalStateException e) {
            ax.p302sb.c.i(this).g().b("MUSIC SERVICE START FOREGROUND ERROR").m(e).i();
        }
    }

    @Override // ax.X4.g.c
    public void e(f fVar, boolean z) {
        M(false, z);
        this.t0.removeCallbacksAndMessages(null);
        if (z) {
            this.t0.sendEmptyMessageDelayed(1, 1800000L);
        } else {
            this.t0.sendEmptyMessageDelayed(0, 30000L);
        }
        if (ax.V4.b.a()) {
            d dVar = this.s0;
            if (dVar != null) {
                dVar.r(!z);
            }
        } else {
            d dVar2 = this.s0;
            if (dVar2 != null) {
                dVar2.r(!z);
            }
        }
        Intent intent = new Intent("local.intent.action.LOCAL_PLAYBACK_STOP");
        if (z) {
            intent.putExtra("state", 2);
        } else {
            intent.putExtra("state", 1);
        }
        ax.L0.a.b(this).d(intent);
        if (z) {
            L(this, this.p0.d(), fVar.h(), fVar.j());
        }
    }

    @Override // ax.M0.c
    public void k(String str, Bundle bundle, c.l<Bundle> lVar) {
        super.k(str, bundle, lVar);
    }

    @Override // ax.M0.c
    public c.e l(String str, int i, Bundle bundle) {
        String str2 = x0;
        ax.Y4.b.a(str2, "OnGetRoot: clientPackageName=" + str, "; clientUid=" + i + " ; rootHints=", bundle);
        if (this.u0 == null) {
            this.u0 = new ax.V4.g(this);
        }
        if (this.u0.a(this, str, i)) {
            return new c.e("__ROOT__", null);
        }
        ax.Y4.b.j(str2, "OnGetRoot: IGNORING request from untrusted package " + str);
        return null;
    }

    @Override // ax.M0.c
    public void m(String str, c.l<List<MediaBrowserCompat.MediaItem>> lVar) {
        ax.Y4.b.a(x0, "OnLoadChildren: parentMediaId=", str);
        lVar.f(this.p0.c(str, getResources()));
    }

    @Override // ax.M0.c, android.app.Service
    public void onCreate() {
        super.onCreate();
        ax.Y4.b.a(x0, "onCreate");
        y0 = true;
        this.p0 = new ax.W4.b();
        this.q0 = new g(this, getResources(), this.p0, new h(this, this.p0, getResources(), new a()), new e(this, this.p0));
        try {
            this.r0 = new MediaSessionCompat(this, "MusicService");
        } catch (IllegalArgumentException unused) {
            this.r0 = new MediaSessionCompat(this, "MusicService", new ComponentName(getPackageName(), MediaButtonReceiver.class.getCanonicalName()), null);
        } catch (SecurityException unused2) {
            stopSelf();
            return;
        }
        this.q0.C(C(this), B(this));
        x(this.r0.c());
        this.r0.h(this.q0.w());
        this.r0.k(3);
        this.q0.N(null, 0, null);
        try {
            this.s0 = new d(this);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not create a MediaNotificationManager", e);
        }
    }

    @Override // ax.M0.c, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ax.Y4.b.a(x0, "onDestroy");
        y0 = false;
        z0 = 0;
        B0 = false;
        A0 = false;
        this.q0.A(null);
        d dVar = this.s0;
        if (dVar != null) {
            dVar.u();
        }
        this.t0.removeCallbacksAndMessages(null);
        MediaSessionCompat mediaSessionCompat = this.r0;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.f();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        d dVar;
        if (intent == null) {
            d dVar2 = this.s0;
            if (dVar2 != null) {
                dVar2.r(true);
            }
            this.t0.removeCallbacksAndMessages(null);
            this.t0.sendEmptyMessageDelayed(0, 10000L);
            return 2;
        }
        if (intent.hasExtra("START_FOREGROUND")) {
            try {
                d dVar3 = this.s0;
                if (dVar3 != null) {
                    dVar3.l();
                }
            } catch (IllegalStateException unused) {
                ax.p302sb.c.i(this).g().b("MUSIC SERVICE START BACKGROUND").h("intent:" + intent.getAction()).i();
                this.t0.removeCallbacksAndMessages(null);
                this.t0.sendEmptyMessageDelayed(0, 10000L);
                return 2;
            }
        }
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("CMD_NAME");
        if (!"com.example.android.uamp.ACTION_CMD".equals(action)) {
            MediaButtonReceiver.c(this.r0, intent);
        } else if ("CMD_PAUSE".equals(stringExtra)) {
            this.q0.y();
            return 2;
        }
        if (this.v0 || !(this.t0.hasMessages(1) || this.t0.hasMessages(0))) {
            this.t0.removeCallbacksAndMessages(null);
            this.t0.sendEmptyMessageDelayed(0, 30000L);
        } else if (this.w0 && (dVar = this.s0) != null && dVar.o()) {
            this.s0.r(false);
        }
        return 2;
    }

    @Override // ax.X4.g.c
    public void p0(int i) {
        this.r0.q(i);
        H(this, i);
    }
}
