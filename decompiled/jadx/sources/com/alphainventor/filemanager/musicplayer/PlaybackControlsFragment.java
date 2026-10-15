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
import ax.V4.k;
import ax.p406z3.B;
import com.alphainventor.filemanager.R;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackControlsFragment extends Fragment {
    private static final String D1 = ax.Y4.b.f(PlaybackControlsFragment.class);
    private View j1;
    private View k1;
    private ImageButton l1;
    private ImageButton m1;
    private TextView n1;
    private TextView o1;
    private TextView p1;
    private ImageView q1;
    private View r1;
    private String s1;
    private ProgressBar t1;
    private PlaybackStateCompat u1;
    private ScheduledFuture<?> x1;
    private boolean y1;
    private final Handler v1 = new Handler(Looper.getMainLooper());
    private final ScheduledExecutorService w1 = Executors.newSingleThreadScheduledExecutor();
    private final MediaControllerCompat.a z1 = new a();
    private final View.OnClickListener A1 = new d();
    private final View.OnClickListener B1 = new e();
    private final Runnable C1 = new f();

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat == null) {
                return;
            }
            try {
                ax.Y4.b.a(PlaybackControlsFragment.D1, "Received metadata state change to mediaId=", mediaMetadataCompat.e().f(), " song=", mediaMetadataCompat.e().i());
            } catch (RuntimeException e) {
                ax.p302sb.c.h().g().d("METADATA COULD NOT READ BITMAP").m(e).i();
            }
            PlaybackControlsFragment.this.h3(mediaMetadataCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            ax.Y4.b.a(PlaybackControlsFragment.D1, "Received playback state change to state ", Integer.valueOf(playbackStateCompat.j()));
            PlaybackControlsFragment.this.i3(playbackStateCompat, false);
        }
    }

    class b extends ax.p182l3.c {
        b() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PlaybackControlsFragment.this.n3();
        }
    }

    class c extends ax.V4.a.AbstractC0223a {
        c() {
        }

        @Override // ax.V4.a.AbstractC0223a
        public void b(String str, Exception exc) {
            super.b(str, exc);
            if (str.equals(PlaybackControlsFragment.this.s1)) {
                PlaybackControlsFragment.this.q1.setImageResource(R.drawable.ic_default_art);
            }
        }

        @Override // ax.V4.a.AbstractC0223a
        public void c(String str, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
            if (str.equals(PlaybackControlsFragment.this.s1) && bitmap3 != null) {
                PlaybackControlsFragment.this.q1.setImageBitmap(bitmap2);
            }
        }
    }

    class d extends ax.p182l3.c {
        d() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            com.alphainventor.filemanager.activity.b bVar = (com.alphainventor.filemanager.activity.b) PlaybackControlsFragment.this.o0();
            if (bVar == null || !PlaybackControlsFragment.this.h1()) {
                return;
            }
            bVar.K0();
        }
    }

    class e extends ax.p182l3.c {
        e() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            if (PlaybackControlsFragment.this.h1()) {
                MediaControllerCompat mediaControllerCompatI0 = ((com.alphainventor.filemanager.activity.b) PlaybackControlsFragment.this.o0()).i0();
                if (mediaControllerCompatI0 == null) {
                    com.alphainventor.filemanager.activity.b bVar = (com.alphainventor.filemanager.activity.b) PlaybackControlsFragment.this.o0();
                    ax.p302sb.c.h().d("MediaController == null").h("Stopped:" + bVar.q0() + ":isConnecting:" + bVar.o0()).i();
                    return;
                }
                PlaybackStateCompat playbackStateCompatE = mediaControllerCompatI0.e();
                int iJ = playbackStateCompatE == null ? 0 : playbackStateCompatE.j();
                ax.Y4.b.a(PlaybackControlsFragment.D1, "Button pressed, in state " + iJ);
                if (view.getId() == R.id.play_pause) {
                    ax.Y4.b.a(PlaybackControlsFragment.D1, "Play button pressed, in state " + iJ);
                    if (iJ == 2 || iJ == 1 || iJ == 0) {
                        PlaybackControlsFragment.this.l3();
                    } else if (iJ == 3 || iJ == 6 || iJ == 8) {
                        PlaybackControlsFragment.this.k3();
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
            PlaybackControlsFragment.this.q3();
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PlaybackControlsFragment.this.v1.post(PlaybackControlsFragment.this.C1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h3(MediaMetadataCompat mediaMetadataCompat) {
        String str = D1;
        ax.Y4.b.a(str, "onMetadataChanged ", mediaMetadataCompat);
        if (o0() == null) {
            ax.Y4.b.j(str, "onMetadataChanged called when getActivity null,this should not happen if the callback was properly unregistered. Ignoring.");
            return;
        }
        if (mediaMetadataCompat == null) {
            return;
        }
        PlaybackStateCompat playbackStateCompat = this.u1;
        if (playbackStateCompat != null && playbackStateCompat.j() == 0) {
            r3();
        }
        MediaDescriptionCompat mediaDescriptionCompatB = k.b(mediaMetadataCompat);
        this.n1.setText(mediaDescriptionCompatB.i());
        if (this.j1.isAccessibilityFocused()) {
            this.j1.announceForAccessibility(mediaDescriptionCompatB.i());
        }
        this.o1.setText(mediaDescriptionCompatB.h());
        p3(mediaMetadataCompat);
        q3();
        String string = mediaDescriptionCompatB.d() != null ? mediaDescriptionCompatB.d().toString() : null;
        if (TextUtils.equals(string, this.s1)) {
            return;
        }
        this.s1 = string;
        Bitmap bitmapC = mediaDescriptionCompatB.c();
        ax.V4.a aVarI = ax.V4.a.i();
        if (bitmapC == null) {
            bitmapC = aVarI.j(this.s1);
        }
        if (bitmapC != null) {
            this.q1.setImageBitmap(bitmapC);
            return;
        }
        this.q1.setImageBitmap(null);
        if (string == null) {
            this.q1.setImageResource(R.drawable.ic_default_art);
        } else {
            aVarI.f(getContext().getApplicationContext(), string, new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i3(PlaybackStateCompat playbackStateCompat, boolean z) {
        String string;
        String str = D1;
        ax.Y4.b.a(str, "onPlaybackStateChanged ", playbackStateCompat);
        if (o0() == null) {
            ax.Y4.b.j(str, "onPlaybackStateChanged called when getActivity null,this should not happen if the callback was properly unregistered. Ignoring.");
            return;
        }
        if (playbackStateCompat == null) {
            return;
        }
        this.u1 = playbackStateCompat;
        int iJ = playbackStateCompat.j();
        if (iJ == 0) {
            j3();
            r3();
        } else {
            if (iJ != 1 && iJ != 2) {
                if (iJ == 3) {
                    this.r1.setVisibility(8);
                    m3();
                } else if (iJ == 6) {
                    o3();
                    this.r1.setVisibility(0);
                } else if (iJ != 7) {
                    o3();
                    this.r1.setVisibility(8);
                } else {
                    ax.Y4.b.c(str, "error playbackstate: ", playbackStateCompat.c());
                    if (!z) {
                        Bundle bundleD = playbackStateCompat.d();
                        if (bundleD == null || bundleD.getInt("SUB_ERROR_CODE", 0) != 4005 || (string = bundleD.getString("SUB_ERROR_MSG")) == null || !ax.T2.a.q(string)) {
                            View viewFindViewById = o0() != null ? o0().findViewById(android.R.id.content) : null;
                            if (viewFindViewById != null) {
                                B.T(viewFindViewById, playbackStateCompat.c(), 0).a0();
                            } else {
                                Toast.makeText(o0(), playbackStateCompat.c(), 1).show();
                            }
                        } else {
                            Intent intent = new Intent("local.intent.action.SHOW_CODEC_DOWNLOAD");
                            intent.putExtra("mimetype", string);
                            ax.p406z3.g.a().e(intent);
                        }
                    }
                    this.r1.setVisibility(8);
                    o3();
                }
                this.l1.setImageDrawable(ax.p374x3.a.c(o0(), R.drawable.ic_pause));
                this.l1.setContentDescription(V0(R.string.playback_pause_description));
                return;
            }
            this.r1.setVisibility(8);
            j3();
            o3();
        }
        this.l1.setImageDrawable(ax.p374x3.a.c(o0(), R.drawable.ic_play_arrow));
        this.l1.setContentDescription(V0(R.string.playback_play_description));
    }

    private void j3() {
        this.v1.post(this.C1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k3() {
        MediaControllerCompat mediaControllerCompatI0 = ((com.alphainventor.filemanager.activity.b) o0()).i0();
        if (mediaControllerCompatI0 != null) {
            mediaControllerCompatI0.i().a();
            o3();
        }
        ax.U2.a.k().o("menu_music_player", "pause").c("loc", "playback_control").e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l3() {
        MediaControllerCompat mediaControllerCompatI0 = ((com.alphainventor.filemanager.activity.b) o0()).i0();
        if (mediaControllerCompatI0 != null) {
            mediaControllerCompatI0.i().b();
            m3();
        }
        ax.U2.a.k().o("menu_music_player", "play").c("loc", "playback_control").e();
    }

    private void m3() {
        o3();
        if (this.w1.isShutdown()) {
            return;
        }
        this.x1 = this.w1.scheduleAtFixedRate(new g(), 100L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n3() {
        com.alphainventor.filemanager.activity.b bVar = (com.alphainventor.filemanager.activity.b) o0();
        Intent intent = new Intent(o0(), (Class<?>) FullScreenPlayerActivity.class);
        intent.setFlags(536870912);
        MediaControllerCompat mediaControllerCompatI0 = bVar.i0();
        if (mediaControllerCompatI0 != null) {
            MediaMetadataCompat mediaMetadataCompatD = mediaControllerCompatI0.d();
            if (mediaMetadataCompatD != null) {
                try {
                    intent.putExtra("com.example.android.uamp.CURRENT_MEDIA_DESCRIPTION", mediaMetadataCompatD.e());
                } catch (RuntimeException unused) {
                }
            }
            P2(intent);
            ax.U2.a.k().o("menu_music_player", "fullscreen").e();
            return;
        }
        ax.p302sb.c.h().d("MediaController NULL!!").h(bVar.q0() + ":" + bVar.o0()).i();
    }

    private void o3() {
        ScheduledFuture<?> scheduledFuture = this.x1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    private void p3(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return;
        }
        ax.Y4.b.a(D1, "updateDuration called ");
        this.t1.setMax((int) mediaMetadataCompat.f("android.media.metadata.DURATION"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q3() {
        PlaybackStateCompat playbackStateCompat = this.u1;
        if (playbackStateCompat == null) {
            return;
        }
        long jI = playbackStateCompat.i();
        if (this.u1.j() != 0 && this.u1.j() != 2 && this.u1.j() != 1) {
            jI = (long) (jI + (((int) (SystemClock.elapsedRealtime() - this.u1.e())) * this.u1.f()));
        }
        this.t1.setProgress((int) jI);
    }

    private void r3() {
        if (o0() == null) {
            return;
        }
        MediaControllerCompat mediaControllerCompatI0 = ((com.alphainventor.filemanager.activity.b) o0()).i0();
        if (mediaControllerCompatI0 != null && mediaControllerCompatI0.d() != null) {
            MediaDescriptionCompat mediaDescriptionCompatE = mediaControllerCompatI0.d().e();
            if (mediaDescriptionCompatE.f() != null && mediaDescriptionCompatE.i() == null) {
                this.r1.setVisibility(0);
                return;
            }
        }
        this.r1.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public View B1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_playback_controls, viewGroup, false);
        this.j1 = viewInflate;
        this.k1 = viewInflate.findViewById(R.id.top_container);
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.play_pause);
        this.l1 = imageButton;
        imageButton.setEnabled(true);
        this.l1.setOnClickListener(this.B1);
        ImageButton imageButton2 = (ImageButton) viewInflate.findViewById(R.id.stop);
        this.m1 = imageButton2;
        imageButton2.setEnabled(true);
        this.m1.setOnClickListener(this.A1);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title);
        this.n1 = textView;
        textView.setSelected(true);
        this.o1 = (TextView) viewInflate.findViewById(R.id.artist);
        this.p1 = (TextView) viewInflate.findViewById(R.id.extra_info);
        this.q1 = (ImageView) viewInflate.findViewById(R.id.album_art);
        this.t1 = (ProgressBar) viewInflate.findViewById(R.id.play_progress);
        this.r1 = viewInflate.findViewById(R.id.progress);
        this.q1.setImageResource(R.drawable.ic_default_art);
        this.k1.setOnClickListener(new b());
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void C1() {
        super.C1();
        o3();
        this.w1.shutdown();
    }

    @Override // androidx.fragment.app.Fragment
    public void T1() {
        super.T1();
        ax.Y4.b.a(D1, "fragment.onStart");
        MediaControllerCompat mediaControllerCompatI0 = ((com.alphainventor.filemanager.activity.b) o0()).i0();
        if (mediaControllerCompatI0 != null) {
            f3(mediaControllerCompatI0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void U1() {
        super.U1();
        ax.Y4.b.a(D1, "fragment.onStop");
        MediaControllerCompat mediaControllerCompatI0 = ((com.alphainventor.filemanager.activity.b) o0()).i0();
        if (mediaControllerCompatI0 != null) {
            g3(mediaControllerCompatI0);
        }
    }

    public void f3(MediaControllerCompat mediaControllerCompat) {
        ax.Y4.b.a(D1, "onConnected, mediaController==null? ", Boolean.valueOf(mediaControllerCompat == null));
        if (mediaControllerCompat != null) {
            h3(mediaControllerCompat.d());
            i3(mediaControllerCompat.e(), true);
            mediaControllerCompat.j(this.z1);
            this.y1 = true;
            PlaybackStateCompat playbackStateCompatE = mediaControllerCompat.e();
            q3();
            if (playbackStateCompatE != null) {
                if (playbackStateCompatE.j() == 3 || playbackStateCompatE.j() == 6) {
                    m3();
                }
            }
        }
    }

    public void g3(MediaControllerCompat mediaControllerCompat) {
        if (mediaControllerCompat == null || !this.y1) {
            return;
        }
        mediaControllerCompat.m(this.z1);
        this.y1 = false;
    }
}
