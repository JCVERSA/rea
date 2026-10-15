package com.alphainventor.filemanager.viewer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.CaptioningManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.MySpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.media3.exoplayer.C0626j;
import androidx.media3.exoplayer.C0643s;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.AspectRatioFrameLayout;
import androidx.media3.ui.C0660b;
import androidx.media3.ui.C0662d;
import androidx.media3.ui.G;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.SubtitleView;
import ax.P0.A;
import ax.P0.C;
import ax.P0.C1357c;
import ax.P0.C1368n;
import ax.P0.C1375v;
import ax.P0.C1378y;
import ax.P0.F;
import ax.P0.H;
import ax.P0.I;
import ax.P0.InterfaceC1370p;
import ax.P0.N;
import ax.P0.P;
import ax.P0.T;
import ax.P0.V;
import ax.P0.a0;
import ax.Y0.C1610d;
import ax.Y0.Z;
import ax.Y7.AbstractC1656z;
import ax.p005a2.C1762a;
import ax.p006a3.J;
import ax.p006a3.Q;
import ax.p054d3.Y;
import ax.p084f1.L;
import ax.p086f3.C2015v;
import ax.p086f3.C2017x;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p132i1.C2095b;
import ax.p132i1.O;
import ax.p180l1.C2140a;
import ax.p180l1.D;
import ax.p210n.AbstractC2162a;
import ax.p210n.ActivityC2164c;
import ax.p406z3.B;
import ax.p406z3.C2461b;
import ax.p406z3.z;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.provider.MyFileProvider;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class VideoPlayerActivity extends ActivityC2164c implements C0662d.m, com.alphainventor.filemanager.viewer.g.c {
    private static final Logger Y2 = Logger.getLogger("FileManager.VideoPlayer");
    private static boolean Z2 = false;
    private static boolean a3 = false;
    private ImageButton A0;
    private long A1;
    private boolean A2;
    private ImageButton B0;
    private boolean B1;
    private Uri B2;
    private ImageButton C0;
    private int C1;
    private ImageButton D0;
    private int D1;
    private boolean D2;
    private MySpinner E0;
    private float E1;
    private int E2;
    private View F0;
    private boolean F1;
    private boolean F2;
    private TextView G0;
    private boolean G1;
    private boolean G2;
    private View H0;
    private AudioManager H1;
    private ImageView I0;
    private boolean I1;
    private int I2;
    private ProgressBar J0;
    private int J1;
    private int J2;
    private TextView K0;
    private int K1;
    private com.alphainventor.filemanager.viewer.g K2;
    private AspectRatioFrameLayout L0;
    private boolean L1;
    int L2;
    private Snackbar M0;
    private float M1;
    int M2;
    private View N0;
    private boolean N1;
    u N2;
    private Toolbar O0;
    private int O1;
    boolean O2;
    private ViewGroup P0;
    private boolean P1;
    private ViewGroup Q0;
    private boolean Q1;
    private View R0;
    private long R1;
    private View S0;
    private long S1;
    private View T0;
    private ax.p180l1.n T1;
    private boolean U0;
    private ax.l1.n.e U1;
    private boolean V0;
    private AbstractC1656z<V.a> V1;
    private String W0;
    private Drawable W1;
    private PlayerView X;
    private String X0;
    private Drawable X1;
    private x X2;
    private boolean Y;
    private C0660b Y0;
    private Drawable Y1;
    private Runnable Z;
    private TextView Z0;
    private Drawable Z1;
    private ax.U0.g.a a1;
    private Drawable a2;
    private O b1;
    private Drawable b2;
    private ExoPlayer c1;
    private Drawable c2;
    private ax.p020b1.O d1;
    private Drawable d2;
    private C1378y e1;
    private Drawable e2;
    private Uri[] f1;
    private Drawable f2;
    private Uri[] g1;
    private String g2;
    private Uri[] h1;
    private String h2;
    private List<Integer> i1;
    private String i2;
    private boolean j1;
    private String j2;
    private C0662d k0;
    private boolean k1;
    private String k2;
    private ViewGroup l0;
    private boolean[] l1;
    private String l2;
    private View m0;
    private int m1;
    private String m2;
    private View n0;
    private int n1;
    private String n2;
    private View o0;
    private int o1;
    private String o2;
    private View p0;
    private boolean p1;
    private String p2;
    private View q;
    private ImageButton q0;
    private float q2;
    private View r0;
    private int r1;
    private float r2;
    private View s0;
    private int s1;
    private boolean s2;
    private View t0;
    private boolean t1;
    private int t2;
    private View u0;
    private int u1;
    private long u2;
    private View v0;
    private String v2;
    private View w0;
    private boolean w1;
    private int w2;
    private ImageButton x0;
    private boolean x1;
    private boolean x2;
    private View y0;
    private boolean y1;
    private boolean y2;
    private ImageView z0;
    private long z1;
    private K z2;
    private boolean q1 = false;
    private float v1 = 1.0f;
    private long C2 = 150;
    private Handler H2 = new Handler();
    ax.p130i.r P2 = new p(true);
    Runnable Q2 = new b();
    G.a R2 = new c();
    Runnable S2 = new d();
    private ScaleGestureDetector.SimpleOnScaleGestureListener T2 = new e();
    private GestureDetector.SimpleOnGestureListener U2 = new f();
    private View.OnClickListener V2 = new g();
    com.alphainventor.filemanager.viewer.g.h W2 = new h();

    class a extends ax.p182l3.c {
        a() {
        }

        public static /* synthetic */ void b(a aVar, Integer num) {
            VideoPlayerActivity.this.N0.setVisibility(8);
            if (num.intValue() == ax.B3.d.j) {
                VideoPlayerActivity.this.E3();
            } else if (num.intValue() == ax.B3.d.k) {
                VideoPlayerActivity.this.c4(R.string.error);
            }
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            VideoPlayerActivity.this.N0.setVisibility(0);
            new ax.B3.d(VideoPlayerActivity.this, new ax.p019b0.a() { // from class: com.alphainventor.filemanager.viewer.f
                @Override // ax.p019b0.a
                public final void accept(Object obj) {
                    VideoPlayerActivity.a.b(this.a, (Integer) obj);
                }
            }).i(new Void[0]);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoPlayerActivity.this.F2) {
                return;
            }
            VideoPlayerActivity.this.w2();
        }
    }

    class c implements G.a {
        private long q;

        c() {
        }

        @Override // androidx.media3.ui.G.a
        public void P(G g, long j) {
            if (VideoPlayerActivity.this.Z2()) {
                return;
            }
            VideoPlayerActivity.this.u2();
            this.q = j;
        }

        @Override // androidx.media3.ui.G.a
        public void d0(G g, long j) {
            VideoPlayerActivity.this.s2(j, this.q > j, false);
            this.q = j;
        }

        @Override // androidx.media3.ui.G.a
        public void l0(G g, long j, boolean z) {
            if (VideoPlayerActivity.this.Z2()) {
                VideoPlayerActivity.this.A1 = j;
                VideoPlayerActivity.this.t2();
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoPlayerActivity.this.G0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    class e extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float X;
        float Y;
        float Z;
        float q;

        e() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (VideoPlayerActivity.this.a3() || VideoPlayerActivity.this.e3() || !VideoPlayerActivity.this.m3()) {
                return false;
            }
            VideoPlayerActivity.this.J4(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), this.Y - scaleGestureDetector.getFocusX(), this.Z - scaleGestureDetector.getFocusY());
            this.Y = scaleGestureDetector.getFocusX();
            this.Z = scaleGestureDetector.getFocusY();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (VideoPlayerActivity.this.a3() || VideoPlayerActivity.this.e3() || !ax.p390y3.n.v(VideoPlayerActivity.this)) {
                return false;
            }
            this.q = scaleGestureDetector.getFocusX();
            this.X = scaleGestureDetector.getFocusY();
            this.Y = scaleGestureDetector.getFocusX();
            this.Z = scaleGestureDetector.getFocusY();
            VideoPlayerActivity.this.F1 = true;
            if (!VideoPlayerActivity.this.m3()) {
                VideoPlayerActivity.this.K4();
            }
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            if (VideoPlayerActivity.this.a3() || VideoPlayerActivity.this.e3()) {
                return;
            }
            VideoPlayerActivity.this.F1 = false;
            this.q = 0.0f;
            this.X = 0.0f;
            this.Y = 0.0f;
            this.Z = 0.0f;
        }
    }

    class f extends GestureDetector.SimpleOnGestureListener {
        float X;
        long Y;
        float Z;
        float k0;
        float q;

        f() {
        }

        private void a() {
            this.k0 = 0.0f;
            this.Z = 0.0f;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            VideoPlayerActivity.this.g2();
            if (VideoPlayerActivity.this.c1 == null) {
                return false;
            }
            int width = VideoPlayerActivity.this.X.getWidth();
            if (width > 0) {
                int i = width / 3;
                int i2 = (width * 2) / 3;
                if (motionEvent.getX() < i) {
                    if (!ax.p390y3.n.s(VideoPlayerActivity.this)) {
                        return false;
                    }
                    VideoPlayerActivity.this.r2(false);
                    return true;
                }
                if (motionEvent.getX() > i2) {
                    if (!ax.p390y3.n.s(VideoPlayerActivity.this)) {
                        return false;
                    }
                    VideoPlayerActivity.this.r2(true);
                    return true;
                }
            }
            int iV = VideoPlayerActivity.this.c1.V();
            if (iV == 2 || iV == 3) {
                VideoPlayerActivity.this.j4();
            } else if (iV == 4) {
                VideoPlayerActivity.this.h4();
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
            return VideoPlayerActivity.this.c1 != null && Math.abs(f) >= Math.abs(f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (VideoPlayerActivity.this.m3() || VideoPlayerActivity.this.e3() || !ax.p390y3.n.q(VideoPlayerActivity.this)) {
                return;
            }
            VideoPlayerActivity.this.q3();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            int height;
            if (VideoPlayerActivity.this.c1 != null && motionEvent != null && motionEvent2 != null) {
                VideoPlayerActivity.this.g2();
                if (VideoPlayerActivity.this.m3()) {
                    if (VideoPlayerActivity.this.h3()) {
                        return false;
                    }
                    VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                    videoPlayerActivity.u3(videoPlayerActivity.E1, f, f2);
                    return true;
                }
                if (VideoPlayerActivity.this.e3()) {
                    a();
                    return false;
                }
                if (!VideoPlayerActivity.this.a3()) {
                    if (motionEvent2.getPointerCount() > 1) {
                        if (motionEvent2.getPointerCount() == 2) {
                            this.Z = motionEvent.getX();
                            this.k0 = motionEvent2.getX();
                        }
                        return false;
                    }
                    if (Math.abs(f) < Math.abs(f2)) {
                        if (Math.abs(motionEvent.getY() - motionEvent2.getY()) < VideoPlayerActivity.this.D1) {
                            return false;
                        }
                        if (VideoPlayerActivity.this.q.getHeight() < VideoPlayerActivity.this.I2 * 10) {
                            height = VideoPlayerActivity.this.I2;
                        } else {
                            height = VideoPlayerActivity.this.q.getHeight() < VideoPlayerActivity.this.J2 * 10 ? VideoPlayerActivity.this.J2 : VideoPlayerActivity.this.q.getHeight() / 10;
                        }
                        if (motionEvent.getY() < height || motionEvent.getY() > VideoPlayerActivity.this.q.getHeight() - height) {
                            return false;
                        }
                        this.X = motionEvent.getY();
                        if (VideoPlayerActivity.this.X == null || motionEvent.getX() >= VideoPlayerActivity.this.X.getWidth() / 2) {
                            if (!ax.p390y3.n.u(VideoPlayerActivity.this)) {
                                return false;
                            }
                            VideoPlayerActivity.this.H4();
                        } else {
                            if (!ax.p390y3.n.o(VideoPlayerActivity.this)) {
                                return false;
                            }
                            VideoPlayerActivity.this.a2();
                        }
                    } else {
                        if (Math.abs(motionEvent.getX() - motionEvent2.getX()) < VideoPlayerActivity.this.C1) {
                            return false;
                        }
                        this.q = motionEvent.getX();
                        this.Y = VideoPlayerActivity.this.c1.r0();
                        if (!ax.p390y3.n.t(VideoPlayerActivity.this)) {
                            return false;
                        }
                        VideoPlayerActivity.this.u2();
                    }
                }
                if (!VideoPlayerActivity.this.a3()) {
                    C2461b.e("what case is this : " + this.q + "," + motionEvent.getX());
                    return true;
                }
                if (VideoPlayerActivity.this.Z2()) {
                    long jF = this.Y + ((((long) B.f(VideoPlayerActivity.this, (int) (motionEvent2.getX() - this.q))) * 40000) / 360);
                    long jE0 = VideoPlayerActivity.this.c1.e0();
                    if (jE0 == -9223372036854775807L) {
                        jE0 = 0;
                    }
                    if (jF < 0) {
                        jF = 0;
                    } else if (jF > jE0) {
                        jF = jE0;
                    }
                    VideoPlayerActivity.this.s2(jF, f > 0.0f, true);
                    return true;
                }
                if (VideoPlayerActivity.this.l3()) {
                    VideoPlayerActivity.this.I3(B.f(VideoPlayerActivity.this, (int) (this.X - motionEvent2.getY())));
                    return true;
                }
                if (VideoPlayerActivity.this.X2()) {
                    VideoPlayerActivity.this.H3(B.f(VideoPlayerActivity.this, (int) (this.X - motionEvent2.getY())));
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            VideoPlayerActivity.this.g2();
            VideoPlayerActivity.this.h4();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }
    }

    class g extends ax.p182l3.c {
        g() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            switch (view.getId()) {
                case R.id.custom_audio_track /* 2131362080 */:
                    VideoPlayerActivity.this.i2();
                    break;
                case R.id.custom_less /* 2131362081 */:
                    VideoPlayerActivity.this.L3(false);
                    break;
                case R.id.custom_lock /* 2131362082 */:
                    VideoPlayerActivity.this.o3(true);
                    break;
                case R.id.custom_more /* 2131362083 */:
                    VideoPlayerActivity.this.L3(true);
                    break;
                case R.id.custom_next /* 2131362084 */:
                    VideoPlayerActivity.this.s3();
                    break;
                case R.id.custom_prev /* 2131362085 */:
                    VideoPlayerActivity.this.z3();
                    break;
                case R.id.custom_repeat_toggle /* 2131362086 */:
                    VideoPlayerActivity.this.k4();
                    break;
                case R.id.custom_resize_mode /* 2131362087 */:
                    VideoPlayerActivity.this.l4();
                    break;
                case R.id.custom_rotation /* 2131362088 */:
                    VideoPlayerActivity.this.G3();
                    break;
                case R.id.custom_shuffle /* 2131362089 */:
                    VideoPlayerActivity.this.m4();
                    break;
                case R.id.custom_subtitles /* 2131362091 */:
                    VideoPlayerActivity.this.n4();
                    break;
                case R.id.custom_toggle_expand /* 2131362092 */:
                    VideoPlayerActivity.this.i4();
                    break;
                case R.id.custom_unlock /* 2131362093 */:
                    VideoPlayerActivity.this.o3(false);
                    break;
            }
        }
    }

    class h implements com.alphainventor.filemanager.viewer.g.h {
        h() {
        }

        @Override // com.alphainventor.filemanager.viewer.g.h
        public void a(boolean z) {
            boolean unused = VideoPlayerActivity.a3 = true;
            if (z) {
                boolean unused2 = VideoPlayerActivity.Z2 = false;
            } else {
                boolean unused3 = VideoPlayerActivity.Z2 = true;
            }
        }
    }

    class i implements com.alphainventor.filemanager.ads.a.i {
        i() {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void a(View view, int i) {
            VideoPlayerActivity.this.P1 = false;
            VideoPlayerActivity.this.m2();
            VideoPlayerActivity.this.p4();
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void b(View view) {
            VideoPlayerActivity.this.m2();
            VideoPlayerActivity.this.p4();
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void c(View view, String str) {
            VideoPlayerActivity.this.J3(view, false);
            VideoPlayerActivity.this.p4();
        }
    }

    class j implements com.alphainventor.filemanager.ads.a.k {
        j() {
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void a(View view) {
            VideoPlayerActivity.this.J3(view, true);
            VideoPlayerActivity.this.p4();
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void b() {
            VideoPlayerActivity.this.m2();
            VideoPlayerActivity.this.p4();
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void c(int i) {
            VideoPlayerActivity.this.P1 = false;
            VideoPlayerActivity.this.m2();
            VideoPlayerActivity.this.p4();
        }
    }

    class k implements SurfaceHolder.Callback {
        k() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            VideoPlayerActivity.this.Y = true;
            if (VideoPlayerActivity.this.Z != null) {
                VideoPlayerActivity.this.Z.run();
                VideoPlayerActivity.this.Z = null;
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            VideoPlayerActivity.this.Y = false;
        }
    }

    class l implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ com.alphainventor.filemanager.viewer.d q;

        l(com.alphainventor.filemanager.viewer.d dVar) {
            this.q = dVar;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            com.alphainventor.filemanager.viewer.d.a aVar = (com.alphainventor.filemanager.viewer.d.a) this.q.getItem(i);
            if (aVar != null) {
                VideoPlayerActivity.this.R3(aVar.b, true);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    class m extends ax.p182l3.c {
        m() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            VideoPlayerActivity.this.m2();
            VideoPlayerActivity.this.p4();
        }
    }

    class n implements View.OnSystemUiVisibilityChangeListener {
        n() {
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int i) {
            if (i == 0 && VideoPlayerActivity.this.w2 == 3846) {
                VideoPlayerActivity.this.P3(false);
            }
        }
    }

    class o implements ax.A0.l {
        o() {
        }

        @Override // ax.A0.l
        public void a(String str, Bundle bundle) {
            if (bundle.getBoolean("result")) {
                VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                if (videoPlayerActivity.O2 && videoPlayerActivity.c1 != null && !VideoPlayerActivity.this.c1.i()) {
                    VideoPlayerActivity.this.c1.N(true);
                }
                VideoPlayerActivity.this.M3();
            }
        }
    }

    class p extends ax.p130i.r {
        p(boolean z) {
            super(z);
        }

        @Override // ax.p130i.r
        public void d() {
            VideoPlayerActivity.this.X.V();
        }
    }

    class q implements Animation.AnimationListener {
        final /* synthetic */ ImageView a;

        q(ImageView imageView) {
            this.a = imageView;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.a.setVisibility(8);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(750L);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            this.a.startAnimation(alphaAnimation);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    class r implements com.alphainventor.filemanager.viewer.a.b {
        r() {
        }

        @Override // com.alphainventor.filemanager.viewer.a.b
        public void a(MotionEvent motionEvent) {
            if (!VideoPlayerActivity.this.m3() && ax.p390y3.n.r(VideoPlayerActivity.this)) {
                VideoPlayerActivity.this.r3();
            }
        }
    }

    class s implements View.OnTouchListener {
        final /* synthetic */ GestureDetector X;
        final /* synthetic */ com.alphainventor.filemanager.viewer.a Y;
        final /* synthetic */ ScaleGestureDetector q;

        s(ScaleGestureDetector scaleGestureDetector, GestureDetector gestureDetector, com.alphainventor.filemanager.viewer.a aVar) {
            this.q = scaleGestureDetector;
            this.X = gestureDetector;
            this.Y = aVar;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (VideoPlayerActivity.this.x2) {
                return false;
            }
            boolean z = this.X.onTouchEvent(motionEvent) || this.q.onTouchEvent(motionEvent);
            this.Y.g(motionEvent);
            if (VideoPlayerActivity.this.m3()) {
                if (motionEvent.getAction() == 1 && VideoPlayerActivity.this.E1 <= 1.0f) {
                    VideoPlayerActivity.this.I4();
                }
                return true;
            }
            if (VideoPlayerActivity.this.Z2()) {
                if (motionEvent.getAction() == 1) {
                    VideoPlayerActivity.this.t2();
                }
                return true;
            }
            if (VideoPlayerActivity.this.l3()) {
                if (motionEvent.getAction() == 1) {
                    VideoPlayerActivity.this.G4();
                }
                return true;
            }
            if (VideoPlayerActivity.this.X2()) {
                if (motionEvent.getAction() == 1) {
                    VideoPlayerActivity.this.Z1();
                }
                return true;
            }
            if (!VideoPlayerActivity.this.e3()) {
                return z;
            }
            if (motionEvent.getAction() == 1) {
                VideoPlayerActivity.this.p3();
            }
            return true;
        }
    }

    static class t extends ax.p180l1.n {
        String m;

        public t(Context context, D.b bVar, String str) {
            super(ax.l1.n.e.R(context), bVar);
            this.m = str;
        }

        public static boolean g0(C1375v c1375v, String str) {
            return ax.p180l1.n.I(c1375v, str, false) > 0;
        }

        @Override // ax.p180l1.n
        protected Pair<D.a, Integer> c0(ax.l1.G.a aVar, int[][][] iArr, ax.l1.n.e eVar, String str) throws C0643s {
            Pair<D.a, Integer> pairC0 = super.c0(aVar, iArr, eVar, str);
            if (VideoPlayerActivity.a3) {
                if (VideoPlayerActivity.Z2) {
                    return null;
                }
            } else if (pairC0 != null) {
                P p = ((D.a) pairC0.first).a;
                int i = 0;
                if (!TextUtils.isEmpty(this.m) && p != null) {
                    int i2 = 0;
                    while (i < p.a) {
                        if (g0(p.b(i), this.m)) {
                            i2 = 1;
                        }
                        i++;
                    }
                    i = i2;
                }
                if (i == 0) {
                    return null;
                }
            }
            return pairC0;
        }
    }

    class u extends ax.p406z3.q<Void, Integer, Boolean> {
        private int h;
        private boolean i;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                u.this.z();
            }
        }

        u(int i, boolean z) {
            super(ax.z3.q.e.HIGHER);
            this.h = i;
            this.i = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void z() {
            if (VideoPlayerActivity.this.isDestroyed() || VideoPlayerActivity.this.c1 == null || VideoPlayerActivity.this.e1 == null) {
                return;
            }
            try {
                VideoPlayerActivity.this.c1.u(VideoPlayerActivity.this.e1, this.i);
                VideoPlayerActivity.this.c1.d0();
            } catch (IllegalStateException e) {
                VideoPlayerActivity.this.c4(R.string.error);
                ax.U2.d.c("EXOPLAYER ILLEGALSTATE", new IllegalStateException("MediaItemIllegal :" + VideoPlayerActivity.this.D2() + ":" + e.getMessage(), e));
            }
        }

        @Override // ax.p406z3.q
        protected void o() {
            VideoPlayerActivity.this.N0.setVisibility(8);
        }

        @Override // ax.p406z3.q
        protected void r() {
            VideoPlayerActivity.this.N0.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            try {
                VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                videoPlayerActivity.e1 = videoPlayerActivity.c2(this.h);
                return Boolean.TRUE;
            } catch (IllegalStateException unused) {
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            VideoPlayerActivity.this.N0.setVisibility(8);
            if (!bool.booleanValue()) {
                VideoPlayerActivity.this.c4(R.string.error);
                return;
            }
            if (VideoPlayerActivity.this.c1 == null) {
                return;
            }
            if (VideoPlayerActivity.this.Y) {
                z();
            } else {
                VideoPlayerActivity.this.Z = new a();
            }
        }
    }

    private class v implements InterfaceC1370p<F> {
        private v() {
        }

        /* synthetic */ v(VideoPlayerActivity videoPlayerActivity, k kVar) {
            this();
        }

        @Override // ax.P0.InterfaceC1370p
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Pair<Integer, String> a(F f) {
            C0643s c0643s;
            int i;
            String string = VideoPlayerActivity.this.getString(R.string.error_playback);
            if (ax.p390y3.k.p(VideoPlayerActivity.this)) {
                if (f instanceof C0643s) {
                    string = string + ":" + ((C0643s) f).p0 + ":" + f.getMessage();
                } else {
                    string = string + ":" + f.getMessage();
                }
            }
            if ((f instanceof C0643s) && (i = (c0643s = (C0643s) f).p0) != 0 && i == 1) {
                Exception excH = c0643s.h();
                if (excH instanceof L.c) {
                    L.c cVar = (L.c) excH;
                    if (cVar.X) {
                        string = ax.B3.h.b(VideoPlayerActivity.this, cVar.q + ":secure");
                    } else {
                        string = ax.B3.h.b(VideoPlayerActivity.this, cVar.q);
                    }
                }
            }
            return Pair.create(0, string);
        }
    }

    private class w implements H.d {
        private w() {
        }

        /* synthetic */ w(VideoPlayerActivity videoPlayerActivity, k kVar) {
            this();
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void B(C c) {
            I.n(this, c);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void C(ax.R0.c cVar) {
            I.d(this, cVar);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void D(List list) {
            I.e(this, list);
        }

        @Override // ax.P0.H.d
        public void K0(int i) {
            if (VideoPlayerActivity.this.u1 != i) {
                ax.p390y3.n.k(VideoPlayerActivity.this, i);
            }
            VideoPlayerActivity.this.u1 = i;
            VideoPlayerActivity.this.y4();
            VideoPlayerActivity.this.v4();
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void M(int i) {
            I.r(this, i);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void N(boolean z) {
            I.k(this, z);
        }

        @Override // ax.P0.H.d
        public void O(int i) {
            if (i == 0 && VideoPlayerActivity.this.u1 == 2) {
                VideoPlayerActivity.this.t3();
            }
            VideoPlayerActivity.this.v4();
        }

        @Override // ax.P0.H.d
        public void Q(V v) {
            VideoPlayerActivity.this.K2.q();
            AbstractC1656z<V.a> abstractC1656zA = v.a();
            if (abstractC1656zA != VideoPlayerActivity.this.V1) {
                VideoPlayerActivity.this.A2 = false;
                VideoPlayerActivity.this.q1 = false;
                VideoPlayerActivity.this.r1 = 0;
                VideoPlayerActivity.this.s1 = 0;
                ax.l1.G.a aVarO = VideoPlayerActivity.this.T1.o();
                if (aVarO != null) {
                    if (aVarO.i(2) == 1) {
                        String strF2 = VideoPlayerActivity.this.F2(abstractC1656zA, "video");
                        String strP2 = VideoPlayerActivity.this.p2();
                        ax.p302sb.c.h().g().b("video codec not available").h("codec : " + strF2 + ",container : " + strP2).i();
                        VideoPlayerActivity.Y2.severe("video codec not available : " + strF2 + ", container : " + strP2);
                        VideoPlayerActivity.this.X3(strF2);
                    }
                    int i = aVarO.i(1);
                    if (i == 1) {
                        String strF3 = VideoPlayerActivity.this.F2(abstractC1656zA, "audio");
                        String strP3 = VideoPlayerActivity.this.p2();
                        ax.p302sb.c.h().g().b("audio codec not available").h("codec : " + strF3 + ",container : " + strP3).i();
                        VideoPlayerActivity.Y2.severe("audio codec not available : " + strF3 + ", container : " + strP3);
                        VideoPlayerActivity.this.X3(strF3);
                    } else if (i == 3) {
                        for (int i2 = 0; i2 < abstractC1656zA.size(); i2++) {
                            V.a aVar = abstractC1656zA.get(i2);
                            if (aVar.d() == 1) {
                                for (int i3 = 0; i3 < aVar.a; i3++) {
                                    if (aVar.j(i3)) {
                                        VideoPlayerActivity.h1(VideoPlayerActivity.this);
                                    }
                                }
                            }
                        }
                    }
                    if (aVarO.i(3) == 3) {
                        for (int i4 = 0; i4 < abstractC1656zA.size(); i4++) {
                            V.a aVar2 = abstractC1656zA.get(i4);
                            if (aVar2.d() == 3) {
                                for (int i5 = 0; i5 < aVar2.a; i5++) {
                                    if (aVar2.j(i5) && (aVar2.b(i5).e & 2) == 0) {
                                        VideoPlayerActivity.e1(VideoPlayerActivity.this);
                                        if (aVar2.f()) {
                                            VideoPlayerActivity.this.q1 = true;
                                        }
                                    }
                                }
                            }
                        }
                        VideoPlayerActivity.this.p1 = true;
                    } else {
                        VideoPlayerActivity.this.p1 = false;
                    }
                } else {
                    VideoPlayerActivity.this.p1 = false;
                }
                VideoPlayerActivity.this.V1 = abstractC1656zA;
            }
            VideoPlayerActivity.this.s4();
            VideoPlayerActivity.this.C4();
            VideoPlayerActivity.this.q4();
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void R(boolean z) {
            I.i(this, z);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void S(F f) {
            I.t(this, f);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void T(float f) {
            I.G(this, f);
        }

        @Override // ax.P0.H.d
        public void U(F f) {
            if (VideoPlayerActivity.W2(f)) {
                VideoPlayerActivity.this.l2();
                VideoPlayerActivity.this.Q2();
            } else {
                VideoPlayerActivity.this.o3(false);
                VideoPlayerActivity.this.s4();
                VideoPlayerActivity.this.Y3();
            }
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void V(int i) {
            I.q(this, i);
        }

        @Override // ax.P0.H.d
        public void X(N n, int i) {
            VideoPlayerActivity.this.v4();
        }

        @Override // ax.P0.H.d
        public void Z(boolean z) {
            VideoPlayerActivity.this.v4();
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void a(a0 a0Var) {
            I.F(this, a0Var);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void b0(T t) {
            I.D(this, t);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void c(int i) {
            I.b(this, i);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void c0(H.b bVar) {
            I.c(this, bVar);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void d(boolean z) {
            I.A(this, z);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void e0(int i, boolean z) {
            I.g(this, i, z);
        }

        @Override // ax.P0.H.d
        public void f0(boolean z, int i) {
            int iC;
            VideoPlayerActivity.Y2.fine("player state changed : " + z + "," + i);
            if (i == 4) {
                if (VideoPlayerActivity.this.c3() && !VideoPlayerActivity.this.F2) {
                    VideoPlayerActivity.this.w2();
                    return;
                } else {
                    VideoPlayerActivity.this.Z3(true);
                    VideoPlayerActivity.this.Y3();
                }
            } else if (i == 3) {
                if (VideoPlayerActivity.this.k3() && VideoPlayerActivity.this.c3() && !VideoPlayerActivity.this.F2 && VideoPlayerActivity.this.E2 > 0) {
                    Handler handler = VideoPlayerActivity.this.H2;
                    VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                    handler.postDelayed(videoPlayerActivity.Q2, videoPlayerActivity.E2);
                }
                Uri uriD2 = VideoPlayerActivity.this.D2();
                if (!VideoPlayerActivity.this.A2 && (VideoPlayerActivity.this.B2 == null || !VideoPlayerActivity.this.B2.equals(uriD2))) {
                    VideoPlayerActivity.this.A2 = true;
                    VideoPlayerActivity.this.B2 = uriD2;
                    long jE0 = VideoPlayerActivity.this.c1.e0();
                    if (jE0 < 0) {
                        jE0 = -1;
                    }
                    ax.U2.a.k().q("video_player_ready").a("duration_ms", jE0).b("duration_range", ax.U2.a.g.a(jE0)).b("ext", (uriD2 == null || uriD2.getPath() == null) ? "uri_error" : d0.k(uriD2.getPath())).c();
                }
                VideoPlayerActivity.this.D4();
                VideoPlayerActivity.this.Z3(false);
                if (VideoPlayerActivity.this.d3()) {
                    iC = ax.Q.b.c(VideoPlayerActivity.this, R.color.videoplayer_unplayed);
                    VideoPlayerActivity.this.C2 = 150L;
                } else {
                    iC = ax.Q.b.c(VideoPlayerActivity.this, R.color.videoplayer_buffered);
                    VideoPlayerActivity.this.C2 = 300L;
                }
                VideoPlayerActivity.this.Y0.setBufferedColor(iC);
            } else if (i == 2) {
                VideoPlayerActivity.this.D4();
                VideoPlayerActivity.this.Z3(false);
            }
            VideoPlayerActivity.this.p4();
            VideoPlayerActivity.this.q2();
            VideoPlayerActivity.this.u4();
            VideoPlayerActivity.this.s4();
            VideoPlayerActivity.this.v4();
            VideoPlayerActivity.this.t4();
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void g0(C1378y c1378y, int i) {
            I.l(this, c1378y, i);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void h0() {
            I.x(this);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void i0(H h, H.c cVar) {
            I.h(this, h, cVar);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void m0(boolean z, int i) {
            I.o(this, z, i);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void n0(A a) {
            I.m(this, a);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void o0(C1368n c1368n) {
            I.f(this, c1368n);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void q0(int i, int i2) {
            I.B(this, i, i2);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void r0(H.e eVar, H.e eVar2, int i) {
            I.w(this, eVar, eVar2, i);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void s0(C1357c c1357c) {
            I.a(this, c1357c);
        }

        @Override // ax.P0.H.d
        public /* synthetic */ void t(ax.P0.G g) {
            I.p(this, g);
        }

        @Override // ax.P0.H.d
        public void t0(boolean z) {
            VideoPlayerActivity.Y2.fine("VideoPlayer : isPlaying=" + z);
            if (!z) {
                VideoPlayerActivity.this.Q3(false);
            } else {
                VideoPlayerActivity.this.Q3(true);
                VideoPlayerActivity.this.h2();
            }
        }
    }

    static class x extends OrientationEventListener {
        Activity a;
        Handler b;
        boolean c;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (x.this.a.isFinishing()) {
                    return;
                }
                x.this.a.setRequestedOrientation(-1);
            }
        }

        x(Activity activity, Handler handler) {
            super(activity);
            this.b = handler;
            this.a = activity;
        }

        private boolean a(int i, int i2) {
            return i > i2 + (-10) && i < i2 + 10;
        }

        private boolean b(int i) {
            return a(i, 90) || a(i, SubsamplingScaleImageView.ORIENTATION_270);
        }

        private boolean c(int i) {
            return a(i, 0) || a(i, SubsamplingScaleImageView.ORIENTATION_180);
        }

        void d() {
            boolean z;
            if (this.a.getResources().getConfiguration().orientation == 2) {
                this.a.setRequestedOrientation(7);
                z = false;
            } else {
                this.a.setRequestedOrientation(6);
                z = true;
            }
            if (Settings.System.getInt(this.a.getContentResolver(), "accelerometer_rotation", 0) == 1) {
                e(z);
            }
        }

        void e(boolean z) {
            this.c = z;
            enable();
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            if (!(this.c && b(i)) && (this.c || !c(i))) {
                return;
            }
            this.b.postDelayed(new a(), 2000L);
            disable();
        }
    }

    private Uri A2(String str) {
        if (!d0.B(str)) {
            ax.p302sb.c.h().g().b("Invalid media path 1").h("path:" + str).i();
            return null;
        }
        String strL = d0.l(str);
        for (String str2 : ax.p086f3.A.o()) {
            File file = new File(strL + "." + str2);
            if (file.exists()) {
                return Uri.fromFile(file);
            }
        }
        return null;
    }

    private void A4() {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer != null) {
            this.s2 = exoPlayer.o();
            this.t2 = this.c1.I();
            this.u2 = Math.max(0L, this.c1.Q());
        }
    }

    private int B2() {
        int i2 = this.o1;
        if (i2 < 0) {
            C2461b.f();
            return 0;
        }
        if (i2 < G2()) {
            return this.o1;
        }
        C2461b.f();
        return G2() - 1;
    }

    private void B3() {
    }

    private Uri C2() {
        if (this.f1 == null) {
            C2461b.f();
            return null;
        }
        int iE2 = E2(B2());
        if (iE2 >= 0) {
            Uri[] uriArr = this.f1;
            if (iE2 < uriArr.length) {
                return uriArr[iE2];
            }
        }
        C2461b.e("what case is this");
        return null;
    }

    private void C3() {
        ax.p020b1.O o2 = this.d1;
        if (o2 != null) {
            o2.h();
            this.d1 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C4() {
        K3(this.p1, this.B0);
        if (!this.p1) {
            this.B0.setImageDrawable(this.c2);
            this.B0.setContentDescription(this.n2);
            this.B0.setVisibility(8);
            this.X.getSubtitleView().setVisibility(8);
            return;
        }
        if (this.q1) {
            this.B0.setImageDrawable(this.d2);
            this.B0.setContentDescription(this.m2);
            this.B0.setVisibility(0);
            this.X.getSubtitleView().setVisibility(0);
            return;
        }
        this.B0.setImageDrawable(this.c2);
        this.B0.setContentDescription(this.n2);
        this.B0.setVisibility(0);
        this.X.getSubtitleView().setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri D2() {
        if (this.g1 == null) {
            C2461b.f();
            return null;
        }
        return this.g1[E2(B2())];
    }

    private void D3() {
        if (this.c1 != null) {
            E4();
            A4();
            this.c1.h();
            this.c1 = null;
            this.e1 = null;
            this.T1 = null;
        }
        if (Q.g1()) {
            C3();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D4() {
        Uri uriC2 = C2();
        if (uriC2 == null) {
            V3(HttpUrl.FRAGMENT_ENCODE_SET);
            return;
        }
        if (!"file".equals(uriC2.getScheme()) && !ax.p086f3.B.L(uriC2.getScheme()) && !MyFileProvider.D(uriC2)) {
            if ("content".equals(uriC2.getScheme())) {
                V3(ax.p086f3.B.w(this, uriC2).a);
            }
        } else if (uriC2.getPath() != null) {
            V3(d0.h(uriC2.getPath()));
        } else {
            V3(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    private int E2(int i2) {
        return this.j1 ? this.i1.get(i2).intValue() : i2;
    }

    private void E4() {
        ax.p180l1.n nVar = this.T1;
        if (nVar != null) {
            this.U1 = nVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String F2(AbstractC1656z<V.a> abstractC1656z, String str) {
        String str2;
        if (abstractC1656z == null) {
            return null;
        }
        for (int i2 = 0; i2 < abstractC1656z.size(); i2++) {
            V.a aVar = abstractC1656z.get(i2);
            for (int i3 = 0; i3 < aVar.a; i3++) {
                C1375v c1375vB = aVar.b(i3);
                if (c1375vB != null && (str2 = c1375vB.o) != null && str2.startsWith(str)) {
                    return c1375vB.o;
                }
            }
        }
        return null;
    }

    private void F3() {
        if (this.V0) {
            j2();
        }
        this.P1 = true;
        if (T2()) {
            if (V2()) {
                this.Q0.setBackgroundColor(0);
                this.T0 = com.alphainventor.filemanager.ads.a.D(this, new i());
            } else {
                this.Q0.setBackgroundColor(-1);
                com.alphainventor.filemanager.ads.a.E(this, new j());
            }
        }
    }

    private void F4(Intent intent) {
        this.H2.removeCallbacks(this.Q2);
        this.E2 = intent.getIntExtra("slide_interval", -1);
        this.G2 = intent.getBooleanExtra("finish_after_play", false);
        this.F2 = false;
    }

    private int G2() {
        Uri[] uriArr = this.g1;
        if (uriArr == null) {
            return 0;
        }
        return uriArr.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G3() {
        if (this.X2 == null) {
            this.X2 = new x(this, this.H2);
        }
        this.X2.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G4() {
        this.I1 = false;
        this.H0.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H3(int i2) {
        int i3 = 255;
        int i4 = ((int) (this.M1 * 255.0f)) + ((i2 * 255) / 360);
        if (i4 <= 255) {
            i3 = i4 < 0 ? 0 : i4;
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = i3 / 255.0f;
        getWindow().setAttributes(attributes);
        this.J0.setProgress(i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H4() {
        if (this.H1 == null) {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            this.H1 = audioManager;
            if (audioManager == null) {
                return;
            }
        }
        this.J1 = this.H1.getStreamMaxVolume(3);
        this.K1 = this.H1.getStreamVolume(3);
        this.I1 = true;
        this.H0.setVisibility(0);
        this.I0.setImageResource(R.drawable.ic_player_volume_up);
        this.J0.setProgressDrawable(ax.p226o.a.b(this, R.drawable.vertical_progress_bar));
        this.J0.setMax(this.J1);
    }

    private boolean I2() {
        return this.P1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I3(int i2) {
        if (this.H1 == null) {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            this.H1 = audioManager;
            if (audioManager == null) {
                return;
            }
        }
        int i3 = this.J1;
        int i4 = this.K1 + ((i2 * i3) / 360);
        if (i4 <= i3) {
            i3 = i4 < 0 ? 0 : i4;
        }
        try {
            this.H1.setStreamVolume(3, i3, 0);
            this.J0.setProgress(i3);
        } catch (SecurityException e2) {
            ax.U2.d.c("video set volume exception", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I4() {
        this.G1 = false;
        this.E1 = 1.0f;
        k2();
    }

    private static boolean J2(Uri[] uriArr) {
        for (Uri uri : uriArr) {
            if (ax.p086f3.B.L(uri.getScheme()) && (uri.getHost() == null || uri.getHost().startsWith("."))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J3(View view, boolean z) {
        this.S1 = 0L;
        this.T0 = view;
        this.V0 = true;
        this.U0 = z;
        this.Q0.removeAllViews();
        this.Q0.addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J4(float f2, float f3, float f4, float f5, float f6) {
        if (!i3()) {
            k2();
            return;
        }
        float f7 = this.E1 * f2;
        this.E1 = f7;
        if (f7 < 1.0f) {
            this.E1 = 1.0f;
        } else if (f7 > 3.0f) {
            this.E1 = 3.0f;
        }
        if (Float.isNaN(this.E1)) {
            this.E1 = 1.0f;
            k2();
            return;
        }
        this.L0.setScaleX(this.E1);
        this.L0.setScaleY(this.E1);
        float width = this.X.getWidth() / 2.0f;
        float height = this.X.getHeight() / 2.0f;
        u3(this.E1, f2(this.L0.getTranslationX(), width, f3, f2) + f5, f2(this.L0.getTranslationY(), height, f4, f2) + f6);
    }

    private void K2() {
        AbstractC2162a supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return;
        }
        supportActionBar.m();
    }

    private void K3(boolean z, View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(z);
        view.setAlpha(z ? this.q2 : this.r2);
        view.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K4() {
        this.G1 = true;
    }

    private void L2() {
        AbstractC2162a supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.I(HttpUrl.FRAGMENT_ENCODE_SET);
            supportActionBar.w(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L3(boolean z) {
        this.t1 = z;
        ax.p390y3.n.j(this, z);
        u4();
        invalidateOptionsMenu();
    }

    private void M2() {
        if ((ax.p390y3.n.p(this) && ax.T2.a.r(this)) ? ax.T2.a.m(this) : false) {
            return;
        }
        ax.T2.a.n(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M3() {
        this.X.setControllerShowTimeoutMs(ax.p390y3.n.b(this) * 1000);
    }

    private void N2() {
        this.t1 = ax.p390y3.n.a(this);
        u4();
        S3(ax.p390y3.n.d(this));
        float f2 = this.v1;
        if (f2 != 0.0f) {
            R3(f2, true);
            this.E0.setSelection(com.alphainventor.filemanager.viewer.d.c(this.v1));
        }
        B4();
    }

    private void N3(int i2) {
        this.o1 = i2;
        v4();
    }

    private void O2() {
        Resources resources = getResources();
        this.W1 = ax.p374x3.a.c(this, R.drawable.ic_resize_mode_best);
        this.X1 = ax.p374x3.a.c(this, R.drawable.ic_resize_mode_stretch);
        this.Y1 = ax.p374x3.a.c(this, R.drawable.ic_resize_mode_crop_to_fit);
        this.c2 = ax.p374x3.a.c(this, R.drawable.ic_subtitles_off);
        this.d2 = ax.p374x3.a.c(this, R.drawable.ic_subtitles_on);
        this.Z1 = ax.p374x3.a.c(this, R.drawable.exo_styled_controls_repeat_off);
        this.a2 = ax.p374x3.a.c(this, R.drawable.exo_styled_controls_repeat_one);
        this.b2 = ax.p374x3.a.c(this, R.drawable.exo_styled_controls_repeat_all);
        this.f2 = ax.p374x3.a.c(this, R.drawable.exo_styled_controls_shuffle_on);
        this.e2 = ax.p374x3.a.c(this, R.drawable.exo_styled_controls_shuffle_off);
        this.g2 = resources.getString(R.string.screen_best_fit);
        this.h2 = resources.getString(R.string.screen_stretch);
        this.i2 = resources.getString(R.string.screen_crop_to_fit);
        this.j2 = resources.getString(R.string.exo_controls_repeat_off_description);
        this.k2 = resources.getString(R.string.exo_controls_repeat_one_description);
        this.l2 = resources.getString(R.string.exo_controls_repeat_all_description);
        this.m2 = resources.getString(R.string.exo_controls_cc_enabled_description);
        this.n2 = resources.getString(R.string.exo_controls_cc_disabled_description);
        this.o2 = resources.getString(R.string.exo_controls_shuffle_on_description);
        this.p2 = resources.getString(R.string.exo_controls_shuffle_off_description);
        this.q2 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        this.r2 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
    }

    private void O3() {
        if (this.g1 == null || D2() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setData(C2());
        setResult(-1, intent);
    }

    private void P2() {
        Uri[] uriArr;
        int i2 = 0;
        if (this.g1 != null) {
            return;
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        this.m1 = -1;
        this.n1 = -1;
        if (!"android.intent.action.VIEW".equals(action) || intent.getData() == null) {
            d4(R.string.error);
            finish();
            return;
        }
        ArrayList<com.alphainventor.filemanager.viewer.e.a> arrayListC = com.alphainventor.filemanager.viewer.e.b().c();
        if (arrayListC != null) {
            this.f1 = new Uri[arrayListC.size()];
            this.g1 = new Uri[arrayListC.size()];
            this.h1 = new Uri[arrayListC.size()];
            this.i1 = new ArrayList();
            for (int i3 = 0; i3 < arrayListC.size(); i3++) {
                this.i1.add(Integer.valueOf(i3));
            }
            Collections.shuffle(this.i1);
            for (int i4 = 0; i4 < arrayListC.size(); i4++) {
                com.alphainventor.filemanager.viewer.e.a aVar = arrayListC.get(i4);
                Uri[] uriArr2 = this.f1;
                Uri uri = aVar.a;
                uriArr2[i4] = uri;
                this.g1[i4] = n2(uri);
                Uri uri2 = aVar.b;
                if (uri2 != null) {
                    this.h1[i4] = n2(uri2);
                }
            }
            for (int i5 = 0; i5 < this.f1.length; i5++) {
                int iIntValue = this.i1.get(i5).intValue();
                Uri uri3 = this.f1[i5];
                if (uri3 != null && uri3.equals(intent.getData())) {
                    this.m1 = i5;
                }
                Uri uri4 = this.f1[iIntValue];
                if (uri4 != null && uri4.equals(intent.getData())) {
                    this.n1 = i5;
                }
            }
        } else {
            Y2.fine("Video play : " + intent.getData());
            this.f1 = new Uri[]{intent.getData()};
            this.g1 = new Uri[]{n2(intent.getData())};
            ArrayList arrayList = new ArrayList();
            this.i1 = arrayList;
            arrayList.add(0);
            this.h1 = new Uri[1];
        }
        boolean booleanExtra = intent.getBooleanExtra("detect_subtitle", true);
        this.k1 = booleanExtra;
        if (booleanExtra) {
            this.l1 = new boolean[this.g1.length];
        }
        if (this.j1) {
            int i6 = this.n1;
            if (i6 == -1) {
                N3(0);
            } else {
                N3(i6);
            }
        } else {
            int i7 = this.m1;
            if (i7 == -1) {
                N3(0);
            } else {
                N3(i7);
            }
        }
        for (Uri uri5 : this.g1) {
            if (com.alphainventor.filemanager.service.b.k(this, uri5)) {
                this.D2 = true;
                this.y2 = true;
                ax.p038c3.k kVarZ = ax.p310t3.c.z(uri5.getPath());
                if (kVarZ != null) {
                    this.z2 = kVarZ.d();
                }
            } else if (ax.p086f3.B.L(uri5.getScheme())) {
                this.D2 = true;
            }
        }
        if (this.y2 && this.z2 != null) {
            com.alphainventor.filemanager.service.b.f(this).l(true, this.z2);
        }
        C1378y[] c1378yArr = new C1378y[this.g1.length];
        while (true) {
            uriArr = this.g1;
            if (i2 >= uriArr.length) {
                break;
            }
            c1378yArr[i2] = C1378y.b(uriArr[i2]);
            i2++;
        }
        if (J2(uriArr) || !ax.S0.d0.m(c1378yArr)) {
            d4(R.string.error);
        } else {
            if (Q.A0() && ax.p406z3.o.c()) {
                return;
            }
            ax.S0.d0.X0(this, this.g1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q2() {
        String strG;
        T3(ax.p390y3.n.f(this));
        P2();
        if (this.g1 != null && this.c1 == null) {
            M2();
            if (J.i()) {
                androidx.media3.decoder.ffmpeg.b.I0(2);
            }
            C2140a.b bVar = new C2140a.b();
            C1610d c1610d = new C1610d(this);
            c1610d.p(1);
            if (ax.p390y3.n.i(this)) {
                strG = ax.p390y3.n.g(this);
                if (TextUtils.isEmpty(strG) || "default".equals(strG)) {
                    strG = this.W0;
                }
            } else {
                strG = HttpUrl.FRAGMENT_ENCODE_SET;
            }
            this.X0 = strG;
            t tVar = new t(this, bVar, strG);
            this.T1 = tVar;
            tVar.m(this.U1);
            this.V1 = null;
            C0626j c0626j = new C0626j();
            ExoPlayer.b bVar2 = new ExoPlayer.b(this, c1610d);
            bVar2.m(this.T1);
            bVar2.i(c0626j);
            bVar2.k(10000L);
            bVar2.l(10000L);
            ExoPlayer exoPlayerH = bVar2.h();
            this.c1 = exoPlayerH;
            exoPlayerH.c(Z.g);
            this.c1.G(new w(this, null));
            this.c1.N(this.s2);
            this.c1.e(new ax.p212n1.a(this.T1));
            if (this.D2) {
                this.c1.a(2);
            } else {
                this.c1.a(1);
            }
            this.X.setPlayer(this.c1);
            this.X.G();
            x4();
            this.X.setOnTouchListener(new s(new ScaleGestureDetector(this, this.T2), new GestureDetector(this, this.U2), new com.alphainventor.filemanager.viewer.a(this, 1.5f, new r())));
        }
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer != null) {
            int i2 = this.t2;
            boolean z = i2 != -1;
            if (z) {
                exoPlayer.m(i2, this.u2);
            }
            N2();
            s4();
            v4();
            w3(B2(), true ^ z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q3(boolean z) {
        this.q.setKeepScreenOn(z);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0086  */
    private void R2(float f2) {
        int iE;
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null || !exoPlayer.i()) {
            return;
        }
        this.N1 = true;
        this.K0.setVisibility(0);
        ax.S0.I iF = this.c1.F();
        if (f2 == 0.5f) {
            this.K0.setText("0.5X▶▶");
        } else if (f2 == 2.0f) {
            this.K0.setText("2X▶▶");
        } else {
            C2461b.f();
            this.K0.setText(f2 + "X▶▶");
        }
        if (iF.a() == -1 || this.X.getHeight() == 0 || this.X.getHeight() <= iF.a()) {
            iE = 0;
        } else {
            iE = (((this.X.getHeight() - iF.a()) / 2) - this.K0.getHeight()) - B.e(this, 10);
            if (iE < 0) {
                iE = 0;
            }
        }
        if (iE == 0) {
            AbstractC2162a supportActionBar = getSupportActionBar();
            int iK = (supportActionBar == null || !supportActionBar.o()) ? 0 : supportActionBar.k();
            iE = (iK > 0 ? B.e(this, 25) : B.e(this, 5)) + iK;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.K0.getLayoutParams();
        marginLayoutParams.setMargins(0, iE, 0, 0);
        this.K0.setLayoutParams(marginLayoutParams);
        R3(f2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R3(float f2, boolean z) {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        if (z) {
            this.v1 = f2;
        }
        if (exoPlayer.f() == null || this.c1.f().a != f2) {
            if (f2 == 1.0f) {
                this.c1.d(ax.P0.G.d);
            } else {
                this.c1.d(new ax.P0.G(f2));
            }
        }
    }

    private boolean S2() {
        return this.Q1;
    }

    private void S3(int i2) {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        this.u1 = i2;
        exoPlayer.X(i2);
        y4();
    }

    private boolean T2() {
        return this.O1 != 0;
    }

    private void T3(boolean z) {
        this.j1 = z;
        z4();
    }

    private boolean U2() {
        boolean z = this.S1 != 0 && System.currentTimeMillis() - this.S1 > 90000;
        if (z) {
            Y2.fine("ad is expired");
        }
        return z;
    }

    private void U3(int i2, boolean z) {
        SubtitleView subtitleView = this.X.getSubtitleView();
        if (subtitleView == null) {
            return;
        }
        Typeface typeface = z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT;
        CaptioningManager captioningManager = (CaptioningManager) getSystemService("captioning");
        subtitleView.setStyle((captioningManager == null || !captioningManager.isEnabled()) ? new C1762a(-1, 0, 0, 2, -16777216, typeface) : C1762a.a(captioningManager.getUserStyle()));
        subtitleView.b(2, i2);
    }

    private boolean V2() {
        int i2 = this.O1;
        if (i2 == 33 || i2 == 34) {
            return new Random().nextBoolean();
        }
        return i2 == 17 || i2 == 18;
    }

    private void V3(String str) {
        AbstractC2162a supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.I(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean W2(F f2) {
        if (f2 instanceof C0643s) {
            C0643s c0643s = (C0643s) f2;
            if (c0643s.p0 != 0) {
                return false;
            }
            for (Throwable thI = c0643s.i(); thI != null; thI = thI.getCause()) {
                if (thI instanceof C2095b) {
                    return true;
                }
            }
        } else if (f2 != null) {
            for (Throwable cause = f2.getCause(); cause != null; cause = cause.getCause()) {
                if (cause instanceof C2095b) {
                    return true;
                }
            }
        }
        return false;
    }

    private void W3() {
        AbstractC2162a supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return;
        }
        supportActionBar.K();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean X2() {
        return this.L1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X3(String str) {
        Snackbar snackbarT;
        String strB = ax.B3.h.b(this, str);
        if (ax.B3.d.w(this, str)) {
            snackbarT = B.T(this.q, strB, -2);
            snackbarT.q0(R.string.download_action, new a());
            this.M0 = snackbarT;
        } else {
            snackbarT = B.T(this.q, strB, 0);
        }
        snackbarT.a0();
    }

    public static final boolean Y2(int i2) {
        return i2 == 23 || i2 == 66 || i2 == 160;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y3() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z1() {
        this.L1 = false;
        this.H0.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Z2() {
        return this.y1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z3(boolean z) {
        if (z) {
            this.F0.setVisibility(0);
        } else {
            this.F0.setVisibility(8);
        }
        r4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a2() {
        float f2 = getWindow().getAttributes().screenBrightness;
        if (f2 < 0.0f || f2 > 1.0f) {
            this.M1 = 0.5f;
        } else {
            this.M1 = f2;
        }
        this.J0.setMax(255);
        this.L1 = true;
        this.H0.setVisibility(0);
        this.J0.setProgressDrawable(ax.p226o.a.b(this, R.drawable.vertical_progress_bar_brightness));
        this.I0.setImageResource(R.drawable.ic_player_brightness_high);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a3() {
        return Z2() || l3() || X2();
    }

    private void a4(boolean z) {
        ImageView imageView = (ImageView) findViewById(R.id.icon_splash);
        imageView.setVisibility(0);
        if (z) {
            imageView.setImageResource(R.drawable.ic_control_play);
        } else {
            imageView.setImageResource(R.drawable.ic_control_pause);
        }
        imageView.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(750L);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        imageView.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new q(imageView));
    }

    private ax.U0.g.a b2() {
        return new ax.U0.m(this, H2(), new ax.m1.j.b(this).a());
    }

    private boolean b3() {
        return this.F0.getVisibility() == 0;
    }

    private void b4(boolean z, long j2) {
        this.G0.removeCallbacks(this.S2);
        if (z) {
            this.G0.setText("+" + B.n(j2));
        } else {
            this.G0.setText("-" + B.n(j2));
        }
        this.G0.postDelayed(this.S2, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public C1378y c2(int i2) {
        int iE2 = E2(i2);
        if (this.k1 && !this.l1[iE2]) {
            Uri[] uriArr = this.h1;
            if (uriArr[iE2] == null) {
                uriArr[iE2] = y2(this.g1[iE2]);
                this.l1[iE2] = true;
            }
        }
        return d2(this.g1[iE2], this.h1[iE2], null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c3() {
        return this.G2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c4(int i2) {
        B.S(this.q, i2, 0).a0();
    }

    private C1378y d2(Uri uri, Uri uri2, String str) {
        C1378y.c cVar = new C1378y.c();
        cVar.f(uri);
        if (uri2 == null) {
            return cVar.a();
        }
        String strN = ax.p086f3.A.n(d0.f(uri2.getPath()));
        C1378y.k.a aVar = new C1378y.k.a(uri2);
        aVar.l(1);
        aVar.k(strN);
        cVar.d(Collections.singletonList(aVar.i()));
        return cVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d3() {
        if (D2() == null) {
            return false;
        }
        String scheme = D2().getScheme();
        return "file".equals(scheme) || "content".equals(scheme);
    }

    private void d4(int i2) {
        e4(getString(i2));
    }

    static /* synthetic */ int e1(VideoPlayerActivity videoPlayerActivity) {
        int i2 = videoPlayerActivity.r1;
        videoPlayerActivity.r1 = i2 + 1;
        return i2;
    }

    private float e2(float f2, int i2, float f3, float f4) {
        float f5 = f3 - f4;
        float f6 = i2;
        float f7 = f6 / 2.0f;
        float f8 = f2 / 2.0f;
        float f9 = f5 + f7;
        float f10 = f9 - f8;
        float f11 = f9 + f8;
        if (f10 > 0.0f) {
            return f8 - f7;
        }
        return f11 < f6 ? f7 - f8 : f5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e3() {
        return this.N1;
    }

    private void e4(String str) {
        Toast.makeText(this, str, 1).show();
    }

    private float f2(float f2, float f3, float f4, float f5) {
        return (f2 - (f4 - (f5 * (f4 - (f2 + f3))))) + f3;
    }

    private boolean f3() {
        return z.t(this);
    }

    private void f4() {
        if (getSupportFragmentManager().Q0()) {
            return;
        }
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null || !exoPlayer.i()) {
            this.O2 = false;
        } else {
            this.O2 = true;
            this.c1.J();
        }
        B.d0(getSupportFragmentManager(), Y.s3(), "settings", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g2() {
        if (c3()) {
            this.F2 = true;
            this.H2.removeCallbacks(this.Q2);
        }
    }

    private boolean g3() {
        int i2 = this.O1;
        return i2 == 2 || i2 == 18 || i2 == 34;
    }

    static /* synthetic */ int h1(VideoPlayerActivity videoPlayerActivity) {
        int i2 = videoPlayerActivity.s1;
        videoPlayerActivity.s1 = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h2() {
        if (this.X.getControllerAutoShow() || Z2()) {
            return;
        }
        this.X.setControllerAutoShow(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h3() {
        return this.F1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h4() {
        if (this.X.I()) {
            this.X.G();
        } else {
            this.X.V();
            h2();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i2() {
        this.K2.n(this.B0);
    }

    private boolean i3() {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return false;
        }
        ax.S0.I iF = exoPlayer.F();
        return (iF.a() == -1 || iF.b() == -1 || this.X.getHeight() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i4() {
        L3(!this.t1);
    }

    private void j2() {
        View view = this.T0;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.j(view, this);
        }
        this.T0 = null;
        this.V0 = false;
        this.P1 = false;
        this.Q0.removeAllViews();
    }

    private boolean j3() {
        return this.V0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j4() {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        boolean zO = exoPlayer.o();
        this.c1.N(!zO);
        if (zO) {
            return;
        }
        this.X.G();
    }

    private void k2() {
        this.L0.setScaleX(1.0f);
        this.L0.setScaleY(1.0f);
        this.L0.setTranslationX(0.0f);
        this.L0.setTranslationY(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k3() {
        return this.E2 >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k4() {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        exoPlayer.X(ax.S0.H.a(exoPlayer.o0(), 3));
        y4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l2() {
        this.s2 = true;
        this.t2 = -1;
        this.u2 = -9223372036854775807L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l3() {
        return this.I1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l4() {
        Drawable drawable;
        String str;
        int resizeMode = this.X.getResizeMode();
        int i2 = 3;
        if (resizeMode == 0) {
            drawable = this.X1;
            str = this.h2;
        } else if (resizeMode != 3) {
            i2 = 0;
            if (resizeMode != 4) {
                C2461b.f();
                drawable = this.W1;
                str = this.g2;
            } else {
                drawable = this.W1;
                str = this.g2;
            }
        } else {
            drawable = this.Y1;
            str = this.i2;
            i2 = 4;
        }
        this.X.setResizeMode(i2);
        this.x0.setImageDrawable(drawable);
        this.x0.setContentDescription(str);
        this.G0.removeCallbacks(this.S2);
        this.G0.setText(str);
        this.G0.postDelayed(this.S2, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m2() {
        this.Q1 = true;
        this.R1 = System.currentTimeMillis();
        j2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m3() {
        return this.G1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m4() {
        this.j1 = !this.j1;
        int iB2 = B2();
        if (this.j1) {
            for (int i2 = 0; i2 < this.i1.size(); i2++) {
                if (iB2 == this.i1.get(i2).intValue()) {
                    N3(i2);
                }
            }
        } else {
            N3(this.i1.get(iB2).intValue());
        }
        ax.p390y3.n.l(this, this.j1);
        z4();
    }

    private Uri n2(Uri uri) {
        if (!MyFileProvider.C(uri)) {
            if (MyFileProvider.B(uri)) {
                ax.p038c3.k kVarE = MyFileProvider.e(uri);
                String strE = kVarE.e();
                File file = new File(strE);
                String strV = d0.v(kVarE.d(), strE, Boolean.FALSE);
                if (strV == null || !d0.H("/Android", strV) || !C6248x.w0(file).f1()) {
                    return Uri.fromFile(file);
                }
            } else if (!MyFileProvider.A(uri)) {
                MyFileProvider.E(uri);
                return uri;
            }
        }
        return uri;
    }

    private void n3(boolean z) {
        if (Q.u0()) {
            if (z) {
                setRequestedOrientation(14);
                return;
            } else {
                setRequestedOrientation(-1);
                return;
            }
        }
        if (z) {
            setRequestedOrientation(z.l(this));
        } else {
            setRequestedOrientation(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n4() {
        if (this.r1 != 1) {
            this.K2.o(this.B0, this.W2);
            return;
        }
        a3 = true;
        if (this.q1) {
            Z2 = true;
            this.K2.l();
        } else {
            Z2 = false;
            this.K2.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o3(boolean z) {
        this.x2 = z;
        if (z) {
            this.X.setControllerShowTimeoutMs(3000);
        } else {
            M3();
        }
        n3(this.x2);
        u4();
        x4();
        r4();
    }

    private void o4() {
        if (!this.k0.n0()) {
            K2();
            return;
        }
        if (b3()) {
            W3();
        } else if (this.x2) {
            K2();
        } else {
            W3();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String p2() {
        return D2() == null ? HttpUrl.FRAGMENT_ENCODE_SET : C2015v.h(D2().getPath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p3() {
        this.N1 = false;
        this.K0.setVisibility(4);
        R3(this.v1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p4() {
        View viewFindViewById;
        if (this.c1 == null) {
            return;
        }
        if (S2() && System.currentTimeMillis() - this.R1 > 30000) {
            this.Q1 = false;
        }
        boolean zO = true;
        if (!g3()) {
            zO = true ^ this.c1.o();
        } else if (this.c1.o() && this.c1.V() != 4) {
            zO = false;
        }
        if (!zO || S2() || Z2() || !T2() || f3()) {
            this.P0.setVisibility(8);
            return;
        }
        this.P0.setVisibility(0);
        if (!I2() || U2()) {
            F3();
        }
        if (!j3()) {
            this.S0.setVisibility(0);
            this.R0.setVisibility(8);
            return;
        }
        if (this.S1 == 0) {
            this.S1 = System.currentTimeMillis();
        }
        if (this.U0 && (viewFindViewById = this.T0.findViewById(R.id.media_container)) != null) {
            int iF = B.f(this, getResources().getDisplayMetrics().heightPixels);
            int i2 = 150;
            if (iF <= 360 && (i2 = 150 - (360 - iF)) < 60) {
                i2 = 60;
            }
            ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
            layoutParams.height = B.e(this, i2);
            viewFindViewById.setLayoutParams(layoutParams);
        }
        this.S0.setVisibility(8);
        this.R0.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q2() {
        MySpinner mySpinner = this.E0;
        if (mySpinner == null) {
            return;
        }
        mySpinner.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q3() {
        R2(2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q4() {
        if (this.s1 > 1) {
            this.A0.setVisibility(0);
        } else {
            this.A0.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r2(boolean z) {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        if (exoPlayer.Z(z ? 12 : 11)) {
            long jR0 = this.c1.r0();
            long jC = ((long) ax.p390y3.n.c(this)) * 1000;
            long j2 = z ? jR0 + jC : jR0 - jC;
            long jE0 = this.c1.e0();
            if (j2 < 0) {
                j2 = 0;
            } else if (jE0 != -9223372036854775807L && j2 > jE0) {
                j2 = jE0;
            }
            if (jR0 == j2) {
                return;
            }
            this.c1.c(Z.c);
            this.c1.C0(j2);
            this.c1.c(Z.g);
            b4(z, jC);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r3() {
        R2(0.5f);
    }

    private void r4() {
        this.P2.j(this.x2 && !b3());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s2(long j2, boolean z, boolean z2) {
        if (z2) {
            this.G0.setText(B.n(j2));
        }
        this.A1 = j2;
        boolean z3 = this.B1 != z;
        this.B1 = z;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (z3 || jCurrentTimeMillis - this.z1 >= this.C2) {
            w4(z3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s3() {
        if (this.c1 != null && B2() < G2() - 1) {
            x3();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s4() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t2() {
        ExoPlayer exoPlayer;
        this.y1 = false;
        this.G0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        if (!this.w1) {
            this.X.setUseController(true);
        }
        if (this.x1 && (exoPlayer = this.c1) != null) {
            exoPlayer.N(true);
        }
        w4(false);
        this.z1 = 0L;
        this.A1 = 0L;
        u4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t3() {
        if (this.c1 == null) {
            return;
        }
        if (B2() < G2() - 1) {
            x3();
        } else {
            v3();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t4() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u2() {
        if (this.c1 == null) {
            return;
        }
        this.y1 = true;
        boolean zI = this.X.I();
        this.w1 = zI;
        if (!zI) {
            this.X.setUseController(false);
            this.X.setControllerAutoShow(false);
        }
        boolean zI2 = this.c1.i();
        this.x1 = zI2;
        if (zI2) {
            this.c1.N(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:12:0x0045  */
    /* JADX WARN: Code duplicated, block: B:19:0x006c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x006f A[RETURN] */
    public boolean u3(float f2, float f3, float f4) {
        boolean z;
        if (!i3()) {
            k2();
            return false;
        }
        ax.S0.I iF = this.c1.F();
        float translationX = this.L0.getTranslationX();
        float translationY = this.L0.getTranslationY();
        int iA = (int) (iF.a() * f2);
        int height = this.X.getHeight();
        if (iA <= height) {
            z = false;
        } else {
            float fE2 = e2(iA, height, this.L0.getTranslationY(), f4);
            if (translationY != fE2) {
                this.L0.setTranslationY(fE2);
                z = true;
            } else {
                z = false;
            }
        }
        int iB = (int) (iF.b() * f2);
        int width = this.X.getWidth();
        if (iB > width) {
            float fE3 = e2(iB, width, this.L0.getTranslationX(), f3);
            if (translationX != fE3) {
                this.L0.setTranslationX(fE3);
            } else if (z) {
                return false;
            }
        } else if (z) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u4() {
        if (b3()) {
            this.m0.setVisibility(8);
            this.n0.setVisibility(8);
            this.o0.setVisibility(0);
            this.p0.setVisibility(0);
            this.v0.setVisibility(8);
            this.x0.setVisibility(8);
            this.r0.setVisibility(8);
            this.s0.setVisibility(8);
            this.l0.setVisibility(8);
            this.z0.setVisibility(8);
            this.y0.setVisibility(8);
        } else if (this.x2) {
            this.m0.setVisibility(0);
            this.n0.setVisibility(8);
            this.o0.setVisibility(8);
            this.p0.setVisibility(8);
            this.l0.setVisibility(8);
        } else if (this.t1) {
            this.m0.setVisibility(8);
            this.n0.setVisibility(0);
            this.o0.setVisibility(0);
            this.p0.setVisibility(0);
            this.v0.setVisibility(0);
            this.x0.setVisibility(0);
            this.r0.setVisibility(8);
            this.s0.setVisibility(8);
            this.l0.setVisibility(0);
            this.z0.setVisibility(0);
            this.z0.setImageResource(R.drawable.ic_collapse_all);
            this.z0.setContentDescription(getString(R.string.menu_collapse));
            this.y0.setVisibility(0);
        } else {
            this.m0.setVisibility(8);
            this.n0.setVisibility(8);
            this.o0.setVisibility(0);
            this.p0.setVisibility(0);
            this.v0.setVisibility(0);
            this.x0.setVisibility(0);
            this.r0.setVisibility(8);
            this.s0.setVisibility(8);
            this.l0.setVisibility(0);
            this.z0.setVisibility(0);
            this.z0.setImageResource(R.drawable.ic_expand_all);
            this.z0.setContentDescription(getString(R.string.menu_expand));
            this.y0.setVisibility(0);
        }
        o4();
    }

    private View v2() {
        this.O0.setTouchscreenBlocksFocus(false);
        View childAt = this.O0.getChildAt(0);
        if (!(childAt instanceof androidx.appcompat.widget.p)) {
            C2461b.e("not work anymore");
            return childAt;
        }
        if (this.O0.getNavigationContentDescription() == null || !this.O0.getNavigationContentDescription().equals(childAt.getContentDescription())) {
            C2461b.e("not work anymore");
            return childAt;
        }
        childAt.setId(android.R.id.home);
        return childAt;
    }

    private void v3() {
        if (m3()) {
            I4();
        }
        N3(0);
        w3(B2(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v4() {
        int iG2 = G2();
        boolean z = iG2 > 0;
        boolean z2 = B2() < iG2 - 1;
        K3(z, this.u0);
        K3(z2, this.t0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w2() {
        this.H2.removeCallbacks(this.Q2);
        this.X.setUseController(false);
        finish();
    }

    private void w3(int i2, boolean z) {
        if (i2 < 0 || i2 >= G2()) {
            C2461b.f();
            return;
        }
        if (this.c1 == null) {
            return;
        }
        u uVar = this.N2;
        if (uVar != null && !uVar.isCancelled() && ax.p406z3.q.n(this.N2)) {
            this.N2.e();
        }
        u uVar2 = new u(i2, z);
        this.N2 = uVar2;
        uVar2.i(new Void[0]);
    }

    private void w4(boolean z) {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        long jR0 = exoPlayer.r0();
        if (z || jR0 >= this.A1 || !this.B1) {
            if (z || jR0 <= this.A1 || this.B1) {
                if (this.B1) {
                    this.c1.c(Z.e);
                } else {
                    this.c1.c(Z.f);
                }
                this.c1.C0(this.A1);
                this.c1.c(Z.g);
                this.z1 = System.currentTimeMillis();
            }
        }
    }

    private String x2(String str, List<AbstractC6239n> list) {
        if (!d0.B(str)) {
            ax.p302sb.c.h().g().b("Invalid media path 3").h("path:" + str).i();
            return null;
        }
        if (list == null) {
            return null;
        }
        String strL = d0.l(str);
        for (String str2 : ax.p086f3.A.o()) {
            String str3 = strL + "." + str2;
            for (AbstractC6239n abstractC6239n : list) {
                if (str3.equals(abstractC6239n.E())) {
                    return abstractC6239n.B();
                }
            }
        }
        return null;
    }

    private void x3() {
        if (B2() >= G2()) {
            C2461b.e("bad index");
            return;
        }
        if (m3()) {
            I4();
        }
        N3(B2() + 1);
        w3(B2(), true);
    }

    private void x4() {
        if (!this.k0.n0()) {
            P3(true);
        } else if (!b3() && this.x2) {
            P3(true);
        } else {
            P3(false);
        }
        o4();
    }

    private Uri y2(Uri uri) {
        List<AbstractC6239n> listH;
        String strX2;
        String string = uri.toString();
        if ("file".equals(uri.getScheme())) {
            return A2(uri.getPath());
        }
        Uri uriBuild = null;
        if (string.startsWith("content://media")) {
            String strA = ax.p086f3.N.a(this, uri);
            if (strA != null) {
                return A2(strA);
            }
            return null;
        }
        if ("content".equals(uri.getScheme())) {
            Uri uriZ2 = z2(this, uri);
            Y2.fine("subtitle uri : " + uriZ2);
            return uriZ2;
        }
        if (com.alphainventor.filemanager.service.b.k(this, uri)) {
            ax.p038c3.k kVarZ = ax.p310t3.c.z(uri.getPath());
            String strR = d0.r(uri.getPath());
            ax.p038c3.k kVarZ2 = ax.p310t3.c.z(strR);
            if (kVarZ2 != null && kVarZ != null && C2017x.e(kVarZ2.d()).a() && (listH = ax.p038c3.b.k().h(kVarZ2.toString())) != null && (strX2 = x2(kVarZ.e(), listH)) != null) {
                Uri.Builder builderBuildUpon = uri.buildUpon();
                builderBuildUpon.path(d0.Q(strR, strX2));
                uriBuild = builderBuildUpon.build();
                Y2.fine("subtitle uri : " + uriBuild);
            }
        }
        Y2.fine("media uri : " + uri);
        return uriBuild;
    }

    private void y3() {
        if (B2() <= 0) {
            return;
        }
        if (m3()) {
            I4();
        }
        N3(B2() - 1);
        w3(B2(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y4() {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            this.q0.setImageDrawable(this.Z1);
            this.q0.setContentDescription(this.j2);
            return;
        }
        int iO0 = exoPlayer.o0();
        if (iO0 == 0) {
            this.q0.setImageDrawable(this.Z1);
            this.q0.setContentDescription(this.j2);
        } else if (iO0 == 1) {
            this.q0.setImageDrawable(this.a2);
            this.q0.setContentDescription(this.k2);
        } else {
            if (iO0 != 2) {
                return;
            }
            this.q0.setImageDrawable(this.b2);
            this.q0.setContentDescription(this.l2);
        }
    }

    private Uri z2(Context context, Uri uri) {
        String path = uri.getPath();
        if (!d0.B(path)) {
            ax.p302sb.c.h().g().b("Invalid media path 2").h("path:" + path).i();
            return null;
        }
        String strL = d0.l(path);
        ContentResolver contentResolver = context.getContentResolver();
        for (String str : ax.p086f3.A.o()) {
            Uri uriBuild = uri.buildUpon().path(strL + "." + str).build();
            try {
                try {
                    contentResolver.openFileDescriptor(uriBuild, "r").close();
                } catch (IOException unused) {
                }
                return uriBuild;
            } catch (Exception unused2) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z3() {
        ExoPlayer exoPlayer = this.c1;
        if (exoPlayer == null) {
            return;
        }
        int iV = exoPlayer.V();
        long jR0 = this.c1.r0();
        if (B2() == 0 || (iV != 4 && jR0 > 3000)) {
            this.c1.C0(0L);
        } else if (B2() > 0) {
            y3();
        }
    }

    private void z4() {
        if (this.j1) {
            this.D0.setImageDrawable(this.f2);
            this.D0.setContentDescription(this.o2);
        } else {
            this.D0.setImageDrawable(this.e2);
            this.D0.setContentDescription(this.p2);
        }
    }

    public void A3() {
        PlayerView playerView = this.X;
        if (playerView != null) {
            playerView.P();
        }
        D3();
        Q2();
    }

    public void B4() {
        U3(ax.p390y3.n.h(this), true);
    }

    public void E3() {
        w3(B2(), true);
    }

    public String H2() {
        if (this.v2 == null) {
            this.v2 = ax.S0.d0.w0(this, "FileManager");
        }
        return this.v2;
    }

    public void P3(boolean z) {
        int i2 = z ? 3846 : 1792;
        this.w2 = i2;
        this.q.setSystemUiVisibility(i2);
    }

    @Override // com.alphainventor.filemanager.viewer.g.c
    public H b() {
        return this.c1;
    }

    @Override // androidx.media3.ui.C0662d.m
    public void d0(int i2) {
        Snackbar snackbar;
        x4();
        if (i2 == 8) {
            q2();
            this.K2.d();
        }
        if (i2 != 0 || (snackbar = this.M0) == null) {
            return;
        }
        snackbar.x();
        this.M0 = null;
    }

    /* JADX WARN: Code duplicated, block: B:88:0x0134  */
    @Override // ax.p210n.ActivityC2164c, ax.P.h, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        View currentFocus = getCurrentFocus();
        boolean z = action == 0 || (action == 1 && this.L2 == 0);
        if (action == 0) {
            this.L2 = keyCode;
        } else if (action == 1) {
            this.L2 = 0;
        }
        if (this.M2 == keyCode && action == 1) {
            this.M2 = 0;
            return true;
        }
        if (z) {
            if (keyCode == 20) {
                if (currentFocus == this.X) {
                    if (!this.k0.n0()) {
                        this.k0.v0();
                    }
                } else if (z.r(this.O0, currentFocus) && action == 0) {
                    if (this.k0.n0()) {
                        h4();
                    }
                }
                if (action == 0) {
                    this.M2 = keyCode;
                }
                return true;
            }
            if (keyCode == 19) {
                if (currentFocus == this.X) {
                    if (!this.k0.n0()) {
                        View viewV2 = v2();
                        this.k0.v0();
                        viewV2.requestFocus();
                    }
                } else if (z.r(this.l0, currentFocus) && action == 0) {
                    if (this.k0.n0()) {
                        h4();
                    }
                }
                if (action == 0) {
                    this.M2 = keyCode;
                }
                return true;
            }
            if (keyCode == 21) {
                if (currentFocus == this.X && !this.k0.n0()) {
                    r2(false);
                    if (action == 0) {
                        this.M2 = keyCode;
                    }
                    return true;
                }
            } else if (keyCode == 22) {
                if (currentFocus == this.X && !this.k0.n0()) {
                    r2(true);
                    if (action == 0) {
                        this.M2 = keyCode;
                    }
                    return true;
                }
            } else if (Y2(keyCode)) {
                if (currentFocus == this.X) {
                    j4();
                    if (action == 0) {
                        this.M2 = keyCode;
                    }
                    return true;
                }
            } else if (keyCode == 85 || keyCode == 62) {
                PlayerView playerView = this.X;
                if (currentFocus == playerView) {
                    playerView.setControllerAutoShow(false);
                    a4(ax.S0.d0.v1(this.c1));
                    ax.S0.d0.A0(this.c1);
                    this.X.setControllerAutoShow(true);
                    if (action == 0) {
                        this.M2 = keyCode;
                    }
                    return true;
                }
            } else if (keyCode == 126) {
                if (currentFocus == this.X && ax.S0.d0.v1(this.c1)) {
                    this.X.setControllerAutoShow(false);
                    a4(true);
                    ax.S0.d0.z0(this.c1);
                    this.X.setControllerAutoShow(true);
                    if (action == 0) {
                        this.M2 = keyCode;
                    }
                    return true;
                }
            } else if (keyCode == 127 && currentFocus == this.X && !ax.S0.d0.v1(this.c1)) {
                this.X.setControllerAutoShow(false);
                a4(false);
                ax.S0.d0.y0(this.c1);
                this.X.setControllerAutoShow(true);
                if (action == 0) {
                    this.M2 = keyCode;
                }
                return true;
            }
        }
        return this.X.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public void finish() {
        if (!k3()) {
            O3();
        } else if (this.F2) {
            setResult(0);
        } else {
            setResult(-1);
        }
        super.finish();
        if (!c3() || this.F2) {
            return;
        }
        if (k3()) {
            overridePendingTransition(R.anim.slideshow_in_right, R.anim.slideshow_out_left);
        } else {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    public void g4() {
        A3();
    }

    @Override // com.alphainventor.filemanager.viewer.g.c
    public C0662d i() {
        return this.k0;
    }

    ax.l1.n.e o2() {
        ax.l1.n.f fVar = new ax.l1.n.f(this);
        fVar.Q(this.W0);
        fVar.a0(this.W0);
        fVar.f0(true);
        return fVar.F();
    }

    @Override // ax.p210n.ActivityC2164c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p4();
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    public void onCreate(Bundle bundle) {
        ax.U2.b.f(this, true);
        super.onCreate(bundle);
        if (!Q.O0()) {
            ax.p302sb.c.h().g().d("VIDEO PLAYER NOT SUPPORTED").i();
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        F4(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        try {
            ax.U0.g.a aVarB2 = b2();
            this.a1 = aVarB2;
            this.b1 = new ax.i1.Z.b(aVarB2);
            setContentView(R.layout.activity_videoplayer);
            this.q = findViewById(R.id.root_view);
            this.Z0 = (TextView) findViewById(R.id.debug_text_view);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            this.O0 = toolbar;
            setSupportActionBar(toolbar);
            Locale locale = getResources().getConfiguration().locale;
            if (locale != null) {
                this.W0 = locale.toLanguageTag();
            } else {
                this.W0 = Locale.getDefault().toLanguageTag();
            }
            this.K2 = new com.alphainventor.filemanager.viewer.g(this, this);
            PlayerView playerView = (PlayerView) findViewById(R.id.player_view);
            this.X = playerView;
            if (playerView.getVideoSurfaceView() instanceof SurfaceView) {
                ((SurfaceView) this.X.getVideoSurfaceView()).getHolder().addCallback(new k());
            }
            this.X.setControllerVisibilityListener(this);
            this.X.setErrorMessageProvider(new v(this, null));
            this.X.requestFocus();
            this.N0 = findViewById(R.id.progress);
            this.L0 = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
            this.k0 = (C0662d) findViewById(R.id.exo_controller);
            M3();
            this.X.setControllerAnimationEnabled(false);
            this.k0.setFitsSystemWindows(true);
            this.G0 = (TextView) findViewById(R.id.drag_seek_text);
            this.H0 = findViewById(R.id.drag_control_container);
            this.I0 = (ImageView) findViewById(R.id.vertical_progressbar_icon);
            this.J0 = (ProgressBar) findViewById(R.id.vertical_progressbar);
            this.K0 = (TextView) findViewById(R.id.change_speed_playing_indicator);
            this.l0 = (ViewGroup) findViewById(R.id.buttons_line);
            this.m0 = findViewById(R.id.control_buttons_line0);
            this.n0 = findViewById(R.id.control_buttons_line1);
            this.o0 = findViewById(R.id.control_buttons_line2);
            this.p0 = findViewById(R.id.control_progress_line);
            View viewFindViewById = findViewById(R.id.custom_less);
            this.s0 = viewFindViewById;
            viewFindViewById.setOnClickListener(this.V2);
            View viewFindViewById2 = findViewById(R.id.custom_more);
            this.r0 = viewFindViewById2;
            viewFindViewById2.setOnClickListener(this.V2);
            ImageButton imageButton = (ImageButton) findViewById(R.id.custom_repeat_toggle);
            this.q0 = imageButton;
            imageButton.setOnClickListener(this.V2);
            View viewFindViewById3 = findViewById(R.id.custom_next);
            this.t0 = viewFindViewById3;
            viewFindViewById3.setOnClickListener(this.V2);
            View viewFindViewById4 = findViewById(R.id.custom_prev);
            this.u0 = viewFindViewById4;
            viewFindViewById4.setOnClickListener(this.V2);
            View viewFindViewById5 = findViewById(R.id.custom_lock);
            this.v0 = viewFindViewById5;
            viewFindViewById5.setOnClickListener(this.V2);
            View viewFindViewById6 = findViewById(R.id.custom_unlock);
            this.w0 = viewFindViewById6;
            viewFindViewById6.setOnClickListener(this.V2);
            View viewFindViewById7 = findViewById(R.id.custom_rotation);
            this.y0 = viewFindViewById7;
            viewFindViewById7.setOnClickListener(this.V2);
            ImageButton imageButton2 = (ImageButton) findViewById(R.id.custom_resize_mode);
            this.x0 = imageButton2;
            imageButton2.setOnClickListener(this.V2);
            ImageView imageView = (ImageView) findViewById(R.id.custom_toggle_expand);
            this.z0 = imageView;
            imageView.setOnClickListener(this.V2);
            MySpinner mySpinner = (MySpinner) findViewById(R.id.custom_speed);
            this.E0 = mySpinner;
            mySpinner.setPromptId(R.string.playback_speed);
            com.alphainventor.filemanager.viewer.d dVar = new com.alphainventor.filemanager.viewer.d(this);
            this.E0.setAdapter((SpinnerAdapter) dVar);
            this.E0.setOnItemSelectedListener(new l(dVar));
            ImageButton imageButton3 = (ImageButton) findViewById(R.id.custom_audio_track);
            this.A0 = imageButton3;
            imageButton3.setOnClickListener(this.V2);
            ImageButton imageButton4 = (ImageButton) findViewById(R.id.custom_subtitles);
            this.B0 = imageButton4;
            imageButton4.setOnClickListener(this.V2);
            ImageButton imageButton5 = (ImageButton) findViewById(R.id.custom_shuffle);
            this.D0 = imageButton5;
            imageButton5.setOnClickListener(this.V2);
            this.C0 = (ImageButton) findViewById(R.id.exo_play_pause);
            this.F0 = findViewById(R.id.end_overlay);
            this.P0 = (ViewGroup) findViewById(R.id.ads_overlay);
            this.Q0 = (ViewGroup) findViewById(R.id.ads_container);
            this.R0 = findViewById(R.id.ads_close);
            this.S0 = findViewById(R.id.ads_progress);
            this.R0.setOnClickListener(new m());
            C0660b c0660b = (C0660b) this.X.findViewById(R.id.exo_progress);
            this.Y0 = c0660b;
            c0660b.a(this.R2);
            this.q.setOnSystemUiVisibilityChangeListener(new n());
            this.C1 = B.e(this, 30);
            this.D1 = B.e(this, 35);
            this.O1 = ax.p390y3.a.d();
            O2();
            L2();
            if (bundle != null) {
                Bundle bundle2 = bundle.getBundle("track_selector_parameters");
                if (bundle2 != null) {
                    this.U1 = ax.l1.n.e.Q(bundle2);
                }
                if (this.U1 == null) {
                    this.U1 = o2();
                }
                this.s2 = bundle.getBoolean("auto_play");
                this.t2 = bundle.getInt("window", -1);
                this.u2 = bundle.getLong("position", -1L);
                this.v1 = bundle.getFloat("speed", 1.0f);
            } else {
                this.U1 = o2();
                l2();
            }
            getOnBackPressedDispatcher().h(this, this.P2);
            this.I2 = B.e(this, 40);
            this.J2 = B.e(this, 52);
            getSupportFragmentManager().m1("dialog_dismiss_request", this, new o());
        } catch (SecurityException unused) {
            ax.p302sb.c.h().g().d("VIDEO PLAYER BUILD SOURCE").i();
            Toast.makeText(this, R.string.error, 1).show();
            finish();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_player, menu);
        return true;
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    public void onDestroy() {
        B3();
        if (this.y2 && this.z2 != null) {
            com.alphainventor.filemanager.service.b.f(this).l(false, this.z2);
        }
        if (this.T0 != null) {
            j2();
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        D3();
        B3();
        l2();
        setIntent(intent);
        F4(intent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
            return true;
        }
        if (itemId == R.id.menu_settings) {
            f4();
            return true;
        }
        if (itemId != R.id.menu_toggle_expansion) {
            return false;
        }
        i4();
        return true;
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    public void onPause() {
        super.onPause();
        if (ax.S0.d0.a <= 23) {
            PlayerView playerView = this.X;
            if (playerView != null) {
                playerView.P();
            }
            D3();
        }
        View view = this.T0;
        if (view == null || this.U0) {
            return;
        }
        com.alphainventor.filemanager.ads.a.v(view, this);
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_toggle_expansion);
        if (menuItemFindItem != null) {
            menuItemFindItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (iArr.length == 0) {
            return;
        }
        if (iArr[0] == 0) {
            Q2();
        } else {
            d4(R.string.error_access_denied);
            finish();
        }
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    public void onResume() {
        super.onResume();
        if (ax.S0.d0.a <= 23 || this.c1 == null) {
            Q2();
            PlayerView playerView = this.X;
            if (playerView != null) {
                playerView.Q();
            }
        }
        View view = this.T0;
        if (view == null || this.U0) {
            return;
        }
        com.alphainventor.filemanager.ads.a.F(view, this);
    }

    @Override // androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        E4();
        A4();
        ax.l1.n.e eVar = this.U1;
        if (eVar != null) {
            bundle.putBundle("track_selector_parameters", eVar.J());
        }
        bundle.putBoolean("auto_play", this.s2);
        bundle.putInt("window", this.t2);
        bundle.putLong("position", this.u2);
        bundle.putFloat("speed", this.v1);
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    public void onStart() {
        super.onStart();
        if (ax.S0.d0.a > 23) {
            Q2();
            PlayerView playerView = this.X;
            if (playerView != null) {
                playerView.Q();
            }
        }
        if (Q.N1()) {
            ax.p006a3.v.u(getWindow(), -1157627904);
            ax.p006a3.v.r(getWindow(), -1157627904);
        }
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    public void onStop() {
        super.onStop();
        if (ax.S0.d0.a > 23) {
            PlayerView playerView = this.X;
            if (playerView != null) {
                playerView.P();
            }
            D3();
        }
    }
}
