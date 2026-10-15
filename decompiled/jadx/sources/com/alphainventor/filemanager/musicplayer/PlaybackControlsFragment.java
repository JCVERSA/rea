package com.alphainventor.filemanager.musicplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ax.p340y3.k;
import com.alphainventor.filemanager.R;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackControlsFragment extends Fragment {
    private static final String A1 = ax.B3.b.f(PlaybackControlsFragment.class);
    private View g1;
    private View h1;
    private ImageButton i1;
    private ImageButton j1;
    private TextView k1;
    private TextView l1;
    private TextView m1;
    private ImageView n1;
    private View o1;
    private String p1;
    private ProgressBar q1;
    private PlaybackStateCompat r1;
    private ScheduledFuture<?> u1;
    private boolean v1;
    private final Handler s1 = new Handler(Looper.getMainLooper());
    private final ScheduledExecutorService t1 = Executors.newSingleThreadScheduledExecutor();
    private final MediaControllerCompat.a w1 = new a();
    private final View.OnClickListener x1 = new d();
    private final View.OnClickListener y1 = new e();
    private final Runnable z1 = new f();

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat == null) {
                return;
            }
            try {
                ax.B3.b.a(PlaybackControlsFragment.A1, "Received metadata state change to mediaId=", mediaMetadataCompat.e().f(), " song=", mediaMetadataCompat.e().h());
            } catch (RuntimeException e) {
                ax.p221pa.c.h().g().d("METADATA COULD NOT READ BITMAP").m(e).i();
            }
            PlaybackControlsFragment.this.j3(mediaMetadataCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            ax.B3.b.a(PlaybackControlsFragment.A1, "Received playback state change to state ", Integer.valueOf(playbackStateCompat.h()));
            PlaybackControlsFragment.this.k3(playbackStateCompat, false);
        }
    }

    class b extends ax.P1.c {
        b() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            PlaybackControlsFragment.this.p3();
        }
    }

    class c extends ax.p340y3.a.AbstractC0481a {
        c() {
        }

        @Override // ax.p340y3.a.AbstractC0481a
        public void a(String str, Exception exc) {
            super.a(str, exc);
            PlaybackControlsFragment.this.n1.setImageResource(R.drawable.ic_default_art);
        }

        @Override // ax.p340y3.a.AbstractC0481a
        public void b(String str, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
            if (bitmap3 != null) {
                PlaybackControlsFragment.this.n1.setImageBitmap(bitmap2);
            }
        }
    }

    class d extends ax.P1.c {
        d() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            ax.p352z1.b bVar = (ax.p352z1.b) PlaybackControlsFragment.this.s0();
            if (bVar == null || !PlaybackControlsFragment.this.l1()) {
                return;
            }
            bVar.K1();
        }
    }

    class e extends ax.P1.c {
        e() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            if (PlaybackControlsFragment.this.l1()) {
                MediaControllerCompat mediaControllerCompatQ1 = ((ax.p352z1.b) PlaybackControlsFragment.this.s0()).q1();
                if (mediaControllerCompatQ1 == null) {
                    ax.p352z1.b bVar = (ax.p352z1.b) PlaybackControlsFragment.this.s0();
                    ax.p221pa.c.h().d("MediaController == null").h("Stopped:" + bVar.v1() + ":isConnecting:" + bVar.u1()).i();
                    return;
                }
                PlaybackStateCompat playbackStateCompatE = mediaControllerCompatQ1.e();
                int iH = playbackStateCompatE == null ? 0 : playbackStateCompatE.h();
                ax.B3.b.a(PlaybackControlsFragment.A1, "Button pressed, in state " + iH);
                if (view.getId() == R.id.play_pause) {
                    ax.B3.b.a(PlaybackControlsFragment.A1, "Play button pressed, in state " + iH);
                    if (iH == 2 || iH == 1 || iH == 0) {
                        PlaybackControlsFragment.this.n3();
                    } else if (iH == 3 || iH == 6 || iH == 8) {
                        PlaybackControlsFragment.this.m3();
                    }
                }
            }
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PlaybackControlsFragment.this.s3();
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PlaybackControlsFragment.this.s1.post(PlaybackControlsFragment.this.z1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j3(MediaMetadataCompat mediaMetadataCompat) {
        String str = A1;
        ax.B3.b.a(str, "onMetadataChanged ", mediaMetadataCompat);
        if (s0() == null) {
            ax.B3.b.j(str, "onMetadataChanged called when getActivity null,this should not happen if the callback was properly unregistered. Ignoring.");
            return;
        }
        if (mediaMetadataCompat == null) {
            return;
        }
        PlaybackStateCompat playbackStateCompat = this.r1;
        if (playbackStateCompat != null && playbackStateCompat.h() == 0) {
            t3();
        }
        MediaDescriptionCompat mediaDescriptionCompatB = k.b(mediaMetadataCompat);
        this.k1.setText(mediaDescriptionCompatB.h());
        if (this.g1.isAccessibilityFocused()) {
            this.g1.announceForAccessibility(mediaDescriptionCompatB.h());
        }
        this.l1.setText(mediaDescriptionCompatB.g());
        r3(mediaMetadataCompat);
        s3();
        String string = mediaDescriptionCompatB.d() != null ? mediaDescriptionCompatB.d().toString() : null;
        if (TextUtils.equals(string, this.p1)) {
            return;
        }
        this.p1 = string;
        Bitmap bitmapC = mediaDescriptionCompatB.c();
        ax.p340y3.a aVarI = ax.p340y3.a.i();
        if (bitmapC == null) {
            bitmapC = aVarI.j(this.p1);
        }
        if (bitmapC != null) {
            this.n1.setImageBitmap(bitmapC);
            return;
        }
        this.n1.setImageBitmap(null);
        if (string == null) {
            this.n1.setImageResource(R.drawable.ic_default_art);
        } else {
            aVarI.f(getContext().getApplicationContext(), string, new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k3(PlaybackStateCompat playbackStateCompat, boolean z) {
        String str = A1;
        ax.B3.b.a(str, "onPlaybackStateChanged ", playbackStateCompat);
        if (s0() == null) {
            ax.B3.b.j(str, "onPlaybackStateChanged called when getActivity null,this should not happen if the callback was properly unregistered. Ignoring.");
            return;
        }
        if (playbackStateCompat == null) {
            return;
        }
        this.r1 = playbackStateCompat;
        int iH = playbackStateCompat.h();
        if (iH == 0) {
            l3();
            t3();
        } else {
            if (iH != 1 && iH != 2) {
                if (iH == 3) {
                    this.o1.setVisibility(8);
                    o3();
                } else if (iH == 6) {
                    q3();
                    this.o1.setVisibility(0);
                } else if (iH != 7) {
                    q3();
                    this.o1.setVisibility(8);
                } else {
                    ax.B3.b.c(str, "error playbackstate: ", playbackStateCompat.c());
                    if (!z) {
                        Toast.makeText(s0(), playbackStateCompat.c(), 1).show();
                    }
                    this.o1.setVisibility(8);
                    q3();
                }
                this.i1.setImageDrawable(ax.p017b2.a.c(s0(), R.drawable.ic_pause));
                this.i1.setContentDescription(Z0(R.string.playback_pause_description));
                return;
            }
            this.o1.setVisibility(8);
            l3();
            q3();
        }
        this.i1.setImageDrawable(ax.p017b2.a.c(s0(), R.drawable.ic_play_arrow));
        this.i1.setContentDescription(Z0(R.string.playback_play_description));
    }

    private void l3() {
        this.s1.post(this.z1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m3() {
        MediaControllerCompat mediaControllerCompatQ1 = ((ax.p352z1.b) s0()).q1();
        if (mediaControllerCompatQ1 != null) {
            mediaControllerCompatQ1.i().a();
            q3();
        }
        ax.p338y1.a.k().o("menu_music_player", "pause").c("loc", "playback_control").e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n3() {
        MediaControllerCompat mediaControllerCompatQ1 = ((ax.p352z1.b) s0()).q1();
        if (mediaControllerCompatQ1 != null) {
            mediaControllerCompatQ1.i().b();
            o3();
        }
        ax.p338y1.a.k().o("menu_music_player", "play").c("loc", "playback_control").e();
    }

    private void o3() {
        q3();
        if (this.t1.isShutdown()) {
            return;
        }
        this.u1 = this.t1.scheduleAtFixedRate(new g(), 100L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p3() {
        ax.p352z1.b bVar = (ax.p352z1.b) s0();
        Intent intent = new Intent(s0(), (Class<?>) FullScreenPlayerActivity.class);
        intent.setFlags(536870912);
        MediaControllerCompat mediaControllerCompatQ1 = bVar.q1();
        if (mediaControllerCompatQ1 != null) {
            MediaMetadataCompat mediaMetadataCompatD = mediaControllerCompatQ1.d();
            if (mediaMetadataCompatD != null) {
                try {
                    intent.putExtra("com.example.android.uamp.CURRENT_MEDIA_DESCRIPTION", mediaMetadataCompatD.e());
                } catch (RuntimeException unused) {
                }
            }
            T2(intent);
            ax.p338y1.a.k().o("menu_music_player", "fullscreen").e();
            return;
        }
        ax.p221pa.c.h().d("MediaController NULL!!").h(bVar.v1() + ":" + bVar.u1()).i();
    }

    private void q3() {
        ScheduledFuture<?> scheduledFuture = this.u1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    private void r3(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return;
        }
        ax.B3.b.a(A1, "updateDuration called ");
        this.q1.setMax((int) mediaMetadataCompat.f("android.media.metadata.DURATION"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s3() {
        PlaybackStateCompat playbackStateCompat = this.r1;
        if (playbackStateCompat == null) {
            return;
        }
        long jG = playbackStateCompat.g();
        if (this.r1.h() != 0 && this.r1.h() != 2 && this.r1.h() != 1) {
            jG = (long) (jG + (((int) (SystemClock.elapsedRealtime() - this.r1.d())) * this.r1.e()));
        }
        this.q1.setProgress((int) jG);
    }

    private void t3() {
        if (s0() == null) {
            return;
        }
        MediaControllerCompat mediaControllerCompatQ1 = ((ax.p352z1.b) s0()).q1();
        if (mediaControllerCompatQ1 != null && mediaControllerCompatQ1.d() != null) {
            MediaDescriptionCompat mediaDescriptionCompatE = mediaControllerCompatQ1.d().e();
            if (mediaDescriptionCompatE.f() != null && mediaDescriptionCompatE.h() == null) {
                this.o1.setVisibility(0);
                return;
            }
        }
        this.o1.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public View F1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_playback_controls, viewGroup, false);
        this.g1 = viewInflate;
        this.h1 = viewInflate.findViewById(R.id.top_container);
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.play_pause);
        this.i1 = imageButton;
        imageButton.setEnabled(true);
        this.i1.setOnClickListener(this.y1);
        ImageButton imageButton2 = (ImageButton) viewInflate.findViewById(R.id.stop);
        this.j1 = imageButton2;
        imageButton2.setEnabled(true);
        this.j1.setOnClickListener(this.x1);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title);
        this.k1 = textView;
        textView.setSelected(true);
        this.l1 = (TextView) viewInflate.findViewById(R.id.artist);
        this.m1 = (TextView) viewInflate.findViewById(R.id.extra_info);
        this.n1 = (ImageView) viewInflate.findViewById(R.id.album_art);
        this.q1 = (ProgressBar) viewInflate.findViewById(R.id.play_progress);
        this.o1 = viewInflate.findViewById(R.id.progress);
        this.n1.setImageResource(R.drawable.ic_default_art);
        this.h1.setOnClickListener(new b());
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void G1() {
        super.G1();
        q3();
        this.t1.shutdown();
    }

    @Override // androidx.fragment.app.Fragment
    public void X1() {
        super.X1();
        ax.B3.b.a(A1, "fragment.onStart");
        MediaControllerCompat mediaControllerCompatQ1 = ((ax.p352z1.b) s0()).q1();
        if (mediaControllerCompatQ1 != null) {
            h3(mediaControllerCompatQ1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void Y1() {
        super.Y1();
        ax.B3.b.a(A1, "fragment.onStop");
        MediaControllerCompat mediaControllerCompatQ1 = ((ax.p352z1.b) s0()).q1();
        if (mediaControllerCompatQ1 != null) {
            i3(mediaControllerCompatQ1);
        }
    }

    public void h3(MediaControllerCompat mediaControllerCompat) {
        ax.B3.b.a(A1, "onConnected, mediaController==null? ", Boolean.valueOf(mediaControllerCompat == null));
        if (mediaControllerCompat != null) {
            j3(mediaControllerCompat.d());
            k3(mediaControllerCompat.e(), true);
            mediaControllerCompat.j(this.w1);
            this.v1 = true;
            PlaybackStateCompat playbackStateCompatE = mediaControllerCompat.e();
            s3();
            if (playbackStateCompatE != null) {
                if (playbackStateCompatE.h() == 3 || playbackStateCompatE.h() == 6) {
                    o3();
                }
            }
        }
    }

    public void i3(MediaControllerCompat mediaControllerCompat) {
        if (mediaControllerCompat == null || !this.v1) {
            return;
        }
        mediaControllerCompat.m(this.w1);
        this.v1 = false;
    }
}
