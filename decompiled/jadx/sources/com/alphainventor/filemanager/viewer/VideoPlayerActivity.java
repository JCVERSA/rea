package com.alphainventor.filemanager.viewer;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.MySpinner;
import androidx.appcompat.widget.Toolbar;
import ax.E1.P;
import ax.G4.A;
import ax.G4.I;
import ax.G4.InterfaceC0662k;
import ax.G4.J;
import ax.H1.S;
import ax.J1.C0735p;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.F;
import ax.J1.H;
import ax.J1.V;
import ax.V4.C4769a;
import ax.V4.C4778j;
import ax.V4.InterfaceC4775g;
import ax.V4.w;
import ax.p045d2.v;
import ax.p045d2.x;
import ax.p145k4.C5531t;
import ax.p145k4.C5532u;
import ax.p145k4.O;
import ax.p145k4.Y;
import ax.p145k4.Z;
import ax.p145k4.a0;
import ax.p145k4.b0;
import ax.p145k4.j0;
import ax.p145k4.k0;
import ax.p145k4.l0;
import ax.p182n.AbstractC5569a;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.provider.MyFileProvider;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class VideoPlayerActivity extends ActivityC5571c implements Z, com.google.android.exoplayer2.ui.b.d {
    private static final Logger v2 = Logger.getLogger("FileManager.VideoPlayer");
    private int A1;
    private boolean C1;
    private boolean D1;
    private View E0;
    private boolean E1;
    private PlayerView F0;
    private long F1;
    private boolean G0;
    private long G1;
    private Runnable H0;
    private boolean H1;
    private com.google.android.exoplayer2.ui.b I0;
    private int I1;
    private View J0;
    private int J1;
    private View K0;
    private boolean K1;
    private View L0;
    private boolean L1;
    private View M0;
    private long M1;
    private ImageButton N0;
    private long N1;
    private View O0;
    private ax.R4.c O1;
    private View P0;
    private ax.R4.c.C0224c P1;
    private View Q0;
    private J Q1;
    private View R0;
    private Drawable R1;
    private View S0;
    private Drawable S1;
    private View T0;
    private Drawable T1;
    private ImageButton U0;
    private Drawable U1;
    private ImageButton V0;
    private Drawable V1;
    private MySpinner W0;
    private String W1;
    private View X0;
    private String X1;
    private TextView Y0;
    private String Y1;
    private View Z0;
    private float Z1;
    private Snackbar a1;
    private float a2;
    private View b1;
    private boolean b2;
    private ViewGroup c1;
    private int c2;
    private ViewGroup d1;
    private long d2;
    private View e1;
    private String e2;
    private View f1;
    private int f2;
    private View g1;
    private boolean g2;
    private boolean h1;
    private boolean h2;
    private boolean i1;
    private F i2;
    private String j1;
    private boolean j2;
    private com.google.android.exoplayer2.ui.a k1;
    private Uri k2;
    private TextView l1;
    private ax.U4.i.a m1;
    private boolean m2;
    private ax.G4.u n1;
    private int n2;
    private k0 o1;
    private boolean o2;
    private InterfaceC0662k p1;
    private Uri[] q1;
    private Uri[] r1;
    private Uri[] s1;
    private boolean t1;
    private boolean[] u1;
    private int v1;
    private int w1;
    private boolean x1;
    private boolean z1;
    private boolean y1 = true;
    private float B1 = 1.0f;
    private long l2 = 150;
    private Handler p2 = new Handler();
    ax.p112i.n q2 = new k(true);
    Runnable r2 = new o();
    com.google.android.exoplayer2.ui.d.a s2 = new a();
    private GestureDetector.SimpleOnGestureListener t2 = new c();
    private View.OnClickListener u2 = new d();

    class a implements com.google.android.exoplayer2.ui.d.a {
        private long q;

        a() {
        }

        @Override // com.google.android.exoplayer2.ui.d.a
        public void a(com.google.android.exoplayer2.ui.d dVar, long j) {
            VideoPlayerActivity.this.D2(j, this.q > j, false);
            this.q = j;
        }

        @Override // com.google.android.exoplayer2.ui.d.a
        public void b(com.google.android.exoplayer2.ui.d dVar, long j, boolean z) {
            if (VideoPlayerActivity.this.i3()) {
                VideoPlayerActivity.this.G1 = j;
                VideoPlayerActivity.this.E2();
            }
        }

        @Override // com.google.android.exoplayer2.ui.d.a
        public void r(com.google.android.exoplayer2.ui.d dVar, long j) {
            if (VideoPlayerActivity.this.i3()) {
                return;
            }
            VideoPlayerActivity.this.F2();
            this.q = j;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoPlayerActivity.this.Y0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    class c extends GestureDetector.SimpleOnGestureListener {
        long X;
        float q;

        c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            VideoPlayerActivity.this.u2();
            if (VideoPlayerActivity.this.o1 == null) {
                return false;
            }
            int width = VideoPlayerActivity.this.F0.getWidth();
            if (width > 0) {
                int i = width / 3;
                int i2 = (width * 2) / 3;
                if (motionEvent.getX() < i) {
                    VideoPlayerActivity.this.C2(false);
                    return true;
                }
                if (motionEvent.getX() > i2) {
                    VideoPlayerActivity.this.C2(true);
                    return true;
                }
            }
            int iP = VideoPlayerActivity.this.o1.P();
            if (iP == 2 || iP == 3) {
                VideoPlayerActivity.this.X3();
            } else if (iP == 4) {
                VideoPlayerActivity.this.W3();
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
            return VideoPlayerActivity.this.o1 != null && Math.abs(f) >= Math.abs(f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (VideoPlayerActivity.this.o1 == null || motionEvent == null || motionEvent2 == null) {
                return false;
            }
            VideoPlayerActivity.this.u2();
            if (!VideoPlayerActivity.this.i3()) {
                if (Math.abs(f) < Math.abs(f2) || Math.abs(motionEvent.getX() - motionEvent2.getX()) < VideoPlayerActivity.this.I1) {
                    return false;
                }
                this.q = motionEvent.getX();
                this.X = VideoPlayerActivity.this.o1.U();
                VideoPlayerActivity.this.F2();
            }
            if (!VideoPlayerActivity.this.i3()) {
                ax.p045d2.b.e("what case is this : " + this.q + "," + motionEvent.getX());
                return true;
            }
            long jF = this.X + ((((long) x.f(VideoPlayerActivity.this, (int) (motionEvent2.getX() - this.q))) * 40000) / 360);
            long jI = VideoPlayerActivity.this.o1.I();
            if (jI == -9223372036854775807L) {
                jI = 0;
            }
            if (jF < 0) {
                jF = 0;
            } else if (jF > jI) {
                jF = jI;
            }
            VideoPlayerActivity.this.D2(jF, f > 0.0f, true);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            VideoPlayerActivity.this.u2();
            VideoPlayerActivity.this.W3();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }
    }

    class d extends ax.P1.c {
        d() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            switch (view.getId()) {
                case R.id.custom_less /* 2131362052 */:
                    VideoPlayerActivity.this.F3(false);
                    break;
                case R.id.custom_lock /* 2131362053 */:
                    VideoPlayerActivity.this.q3(true);
                    break;
                case R.id.custom_more /* 2131362054 */:
                    VideoPlayerActivity.this.F3(true);
                    break;
                case R.id.custom_next /* 2131362055 */:
                    VideoPlayerActivity.this.r3();
                    break;
                case R.id.custom_prev /* 2131362056 */:
                    VideoPlayerActivity.this.x3();
                    break;
                case R.id.custom_repeat_toggle /* 2131362057 */:
                    VideoPlayerActivity.this.Y3();
                    break;
                case R.id.custom_subtitles /* 2131362059 */:
                    VideoPlayerActivity.this.Z3();
                    break;
                case R.id.custom_unlock /* 2131362060 */:
                    VideoPlayerActivity.this.q3(false);
                    break;
            }
        }
    }

    class e implements com.alphainventor.filemanager.ads.a.i {
        e() {
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void a(View view, int i) {
            VideoPlayerActivity.this.K1 = false;
            VideoPlayerActivity.this.x2();
            VideoPlayerActivity.this.b4();
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void b(View view) {
            VideoPlayerActivity.this.x2();
            VideoPlayerActivity.this.b4();
        }

        @Override // com.alphainventor.filemanager.ads.a.i
        public void c(View view, String str) {
            VideoPlayerActivity.this.D3(view, false);
            VideoPlayerActivity.this.b4();
        }
    }

    class f implements com.alphainventor.filemanager.ads.a.k {
        f() {
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void a(View view) {
            VideoPlayerActivity.this.D3(view, true);
            VideoPlayerActivity.this.b4();
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void b() {
            VideoPlayerActivity.this.x2();
            VideoPlayerActivity.this.b4();
        }

        @Override // com.alphainventor.filemanager.ads.a.k
        public void c(int i) {
            VideoPlayerActivity.this.K1 = false;
            VideoPlayerActivity.this.x2();
            VideoPlayerActivity.this.b4();
        }
    }

    class g implements SurfaceHolder.Callback {
        g() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            VideoPlayerActivity.this.G0 = true;
            if (VideoPlayerActivity.this.H0 != null) {
                VideoPlayerActivity.this.H0.run();
                VideoPlayerActivity.this.H0 = null;
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            VideoPlayerActivity.this.G0 = false;
        }
    }

    class h implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ r q;

        h(r rVar) {
            this.q = rVar;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            VideoPlayerActivity.this.K3(((s) this.q.getItem(i)).b);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    class i extends ax.P1.c {
        i() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            VideoPlayerActivity.this.x2();
            VideoPlayerActivity.this.b4();
        }
    }

    class j implements View.OnSystemUiVisibilityChangeListener {
        j() {
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int i) {
            if (i == 0 && VideoPlayerActivity.this.f2 == 3846) {
                VideoPlayerActivity.this.I3(false);
            }
        }
    }

    class k extends ax.p112i.n {
        k(boolean z) {
            super(z);
        }

        @Override // ax.p112i.n
        public void d() {
            VideoPlayerActivity.this.F0.I();
        }
    }

    class l implements View.OnTouchListener {
        final /* synthetic */ GestureDetector q;

        l(GestureDetector gestureDetector) {
            this.q = gestureDetector;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (VideoPlayerActivity.this.g2) {
                return false;
            }
            boolean zOnTouchEvent = this.q.onTouchEvent(motionEvent);
            if (!VideoPlayerActivity.this.i3()) {
                return zOnTouchEvent;
            }
            if (motionEvent.getAction() == 1) {
                VideoPlayerActivity.this.E2();
            }
            return true;
        }
    }

    class m implements Runnable {
        final /* synthetic */ boolean q;

        m(boolean z) {
            this.q = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoPlayerActivity.this.isDestroyed() || VideoPlayerActivity.this.o1 == null || VideoPlayerActivity.this.p1 == null) {
                return;
            }
            VideoPlayerActivity.this.o1.C0(VideoPlayerActivity.this.p1, !this.q, false);
        }
    }

    class n extends ax.P1.c {
        n() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            VideoPlayerActivity.this.new p().i(new Void[0]);
        }
    }

    class o implements Runnable {
        o() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoPlayerActivity.this.o2) {
                return;
            }
            VideoPlayerActivity.this.G2();
        }
    }

    class p extends ax.p045d2.n<Void, Integer, Boolean> {
        p() {
            super(ax.d2.n.f.HIGH);
        }

        @Override // ax.p045d2.n
        protected void o() {
            VideoPlayerActivity.this.b1.setVisibility(8);
        }

        @Override // ax.p045d2.n
        protected void r() {
            VideoPlayerActivity.this.b1.setVisibility(0);
            super.r();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) {
            return Boolean.valueOf(ax.p073f2.c.d(VideoPlayerActivity.this));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            VideoPlayerActivity.this.b1.setVisibility(8);
            if (!bool.booleanValue()) {
                VideoPlayerActivity.this.T3(R.string.error);
            } else {
                ax.p073f2.c.n(VideoPlayerActivity.this);
                VideoPlayerActivity.this.B3();
            }
        }
    }

    static class q extends ax.R4.c {
        public q(Context context, ax.R4.g.b bVar) {
            super(ax.R4.c.C0224c.d(context), bVar);
        }

        public static boolean L(O o, String str) {
            return ax.R4.c.t(o, str, false) > 0;
        }

        @Override // ax.R4.c
        protected Pair<ax.R4.g.a, ax.R4.c.f> I(J j, int[][] iArr, ax.R4.c.C0224c c0224c, String str) throws C5532u {
            Pair<ax.R4.g.a, ax.R4.c.f> pairI = super.I(j, iArr, c0224c, str);
            if (pairI != null) {
                return pairI;
            }
            int i = -1;
            I i2 = null;
            ax.R4.c.f fVar = null;
            for (int i3 = 0; i3 < j.q; i3++) {
                I iA = j.a(i3);
                int[] iArr2 = iArr[i3];
                for (int i4 = 0; i4 < iA.q; i4++) {
                    if (ax.R4.c.x(iArr2[i4], c0224c.E0)) {
                        O oA = iA.a(i4);
                        ax.R4.c.f fVar2 = new ax.R4.c.f(oA, c0224c, iArr2[i4], str);
                        String str2 = oA.D0;
                        boolean z = str2 != null && str2.startsWith("en");
                        if (fVar == null || fVar2.compareTo(fVar) > 0 || z) {
                            i2 = iA;
                            i = i4;
                            fVar = fVar2;
                        }
                    }
                }
            }
            if (i2 == null) {
                return null;
            }
            return Pair.create(new ax.R4.g.a(i2, i), (ax.R4.c.f) C4769a.e(fVar));
        }
    }

    static class r extends ArrayAdapter<s> {
        static List<s> X;
        LayoutInflater q;

        r(Context context) {
            super(context, 0, b());
            this.q = LayoutInflater.from(getContext());
        }

        static List<s> b() {
            if (X == null) {
                ArrayList arrayList = new ArrayList();
                X = arrayList;
                arrayList.add(new s("0.25X", 0.25f));
                X.add(new s("0.5X", 0.5f));
                X.add(new s("0.75X", 0.75f));
                X.add(new s("1X", 1.0f));
                X.add(new s("1.25X", 1.25f));
                X.add(new s("1.5X", 1.5f));
                X.add(new s("1.75X", 1.75f));
                X.add(new s("2X", 2.0f));
            }
            return X;
        }

        View a(int i, View view, ViewGroup viewGroup, int i2) {
            if (view == null) {
                view = this.q.inflate(i2, viewGroup, false);
            }
            ((TextView) view).setText(((s) getItem(i)).a);
            return view;
        }

        @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return a(i, view, viewGroup, R.layout.item_video_player_speed_dropdown);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return a(i, view, viewGroup, R.layout.item_video_player_speed);
        }
    }

    static class s {
        String a;
        float b;

        s(String str, float f) {
            this.a = str;
            this.b = f;
        }
    }

    private class t implements InterfaceC4775g<C5532u> {
        private t() {
        }

        @Override // ax.V4.InterfaceC4775g
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Pair<Integer, String> a(C5532u c5532u) {
            String string = VideoPlayerActivity.this.getString(R.string.error_playback);
            if (ax.p031c2.i.n(VideoPlayerActivity.this)) {
                string = string + ":" + c5532u.q + ":" + c5532u.getMessage();
            }
            int i = c5532u.q;
            if (i != 0 && i == 1) {
                Exception excE = c5532u.e();
                if (excE instanceof ax.z4.c.a) {
                    ax.z4.c.a aVar = (ax.z4.c.a) excE;
                    if (aVar.X) {
                        string = VideoPlayerActivity.this.O2(aVar.q + ":secure");
                    } else {
                        string = VideoPlayerActivity.this.O2(aVar.q);
                    }
                }
            }
            return Pair.create(0, string);
        }

        /* synthetic */ t(VideoPlayerActivity videoPlayerActivity, g gVar) {
            this();
        }
    }

    private class u implements b0.a {
        private u() {
        }

        @Override // ax.k4.b0.a
        public void B(J j, ax.R4.h hVar) {
            O oK;
            String str;
            if (j != VideoPlayerActivity.this.Q1) {
                boolean z = false;
                z = false;
                VideoPlayerActivity.this.j2 = false;
                ax.R4.e.a aVarG = VideoPlayerActivity.this.O1.g();
                if (aVarG != null) {
                    if (aVarG.h(2) == 1) {
                        String strQ2 = VideoPlayerActivity.this.Q2(j, "video");
                        String strA2 = VideoPlayerActivity.this.A2();
                        ax.p221pa.c.h().g().b("video codec not available").h("codec : " + strQ2 + ",container : " + strA2).i();
                        VideoPlayerActivity.v2.severe("video codec not available : " + strQ2 + ", container : " + strA2);
                        VideoPlayerActivity.this.P3(strQ2);
                    }
                    if (aVarG.h(1) == 1) {
                        String strQ3 = VideoPlayerActivity.this.Q2(j, "audio");
                        String strA3 = VideoPlayerActivity.this.A2();
                        ax.p221pa.c.h().g().b("audio codec not available").h("codec : " + strQ3 + ",container : " + strA3).i();
                        VideoPlayerActivity.v2.severe("audio codec not available : " + strQ3 + ", container : " + strA3);
                        VideoPlayerActivity.this.P3(strQ3);
                    }
                    if (aVarG.h(3) == 3) {
                        boolean z2 = false;
                        for (int i = 0; i < hVar.a; i++) {
                            ax.R4.g gVarA = hVar.a(i);
                            if (gVarA != null && (str = (oK = gVarA.k()).l0) != null && ax.V4.q.h(str) == 3 && q.L(oK, VideoPlayerActivity.this.j1)) {
                                z2 = true;
                            }
                        }
                        VideoPlayerActivity.this.x1 = true;
                        z = z2;
                    } else {
                        VideoPlayerActivity.this.x1 = false;
                    }
                } else {
                    VideoPlayerActivity.this.x1 = false;
                }
                VideoPlayerActivity.this.a3(z);
                VideoPlayerActivity.this.Q1 = j;
            }
            VideoPlayerActivity.this.d4();
            VideoPlayerActivity.this.m4();
        }

        @Override // ax.k4.b0.a
        public void H0(int i) {
            if (VideoPlayerActivity.this.A1 != i) {
                ax.p031c2.l.f(VideoPlayerActivity.this, i);
            }
            VideoPlayerActivity.this.A1 = i;
            VideoPlayerActivity.this.j4();
            VideoPlayerActivity.this.g4();
        }

        @Override // ax.k4.b0.a
        public /* synthetic */ void M(l0 l0Var, Object obj, int i) {
            a0.k(this, l0Var, obj, i);
        }

        @Override // ax.k4.b0.a
        public void N(C5532u c5532u) {
            if (VideoPlayerActivity.h3(c5532u)) {
                VideoPlayerActivity.this.w2();
                VideoPlayerActivity.this.c3();
            } else {
                VideoPlayerActivity.this.q3(false);
                VideoPlayerActivity.this.d4();
                VideoPlayerActivity.this.Q3();
            }
        }

        @Override // ax.k4.b0.a
        public void P(boolean z) {
            VideoPlayerActivity.v2.fine("VideoPlayer : isPlaying=" + z);
            if (z) {
                VideoPlayerActivity.this.J3(true);
            } else {
                VideoPlayerActivity.this.J3(false);
            }
        }

        @Override // ax.k4.b0.a
        public /* synthetic */ void c(Y y) {
            a0.c(this, y);
        }

        @Override // ax.k4.b0.a
        public /* synthetic */ void d(int i) {
            a0.d(this, i);
        }

        @Override // ax.k4.b0.a
        public /* synthetic */ void f(boolean z) {
            a0.b(this, z);
        }

        @Override // ax.k4.b0.a
        public void g(int i) {
            if (i == 0 && VideoPlayerActivity.this.A1 == 2) {
                VideoPlayerActivity.this.s3();
            }
            VideoPlayerActivity.this.g4();
        }

        @Override // ax.k4.b0.a
        public /* synthetic */ void q() {
            a0.h(this);
        }

        @Override // ax.k4.b0.a
        public void s(l0 l0Var, int i) {
            VideoPlayerActivity.this.g4();
        }

        @Override // ax.k4.b0.a
        public void u(boolean z) {
            VideoPlayerActivity.this.g4();
        }

        @Override // ax.k4.b0.a
        public void z(boolean z, int i) {
            int iC;
            VideoPlayerActivity.v2.fine("player state changed : " + z + "," + i);
            if (i == 4) {
                if (VideoPlayerActivity.this.o3() && !VideoPlayerActivity.this.o2) {
                    VideoPlayerActivity.this.G2();
                    return;
                } else {
                    VideoPlayerActivity.this.R3(true);
                    VideoPlayerActivity.this.Q3();
                }
            } else if (i == 3) {
                if (VideoPlayerActivity.this.o3() && !VideoPlayerActivity.this.o2 && VideoPlayerActivity.this.n2 > 0) {
                    Handler handler = VideoPlayerActivity.this.p2;
                    VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                    handler.postDelayed(videoPlayerActivity.r2, videoPlayerActivity.n2);
                }
                Uri uriN2 = VideoPlayerActivity.this.N2();
                if (!VideoPlayerActivity.this.j2 && (VideoPlayerActivity.this.k2 == null || !VideoPlayerActivity.this.k2.equals(uriN2))) {
                    VideoPlayerActivity.this.j2 = true;
                    VideoPlayerActivity.this.k2 = uriN2;
                    long jI = VideoPlayerActivity.this.o1.I();
                    if (jI < 0) {
                        jI = -1;
                    }
                    ax.p338y1.a.k().q("video_player_ready").a("duration_ms", jI).b("duration_range", ax.y1.a.f.a(jI)).b("ext", (uriN2 == null || uriN2.getPath() == null) ? "uri_error" : V.k(uriN2.getPath())).c();
                }
                VideoPlayerActivity.this.n4();
                VideoPlayerActivity.this.R3(false);
                if (VideoPlayerActivity.this.k3()) {
                    iC = ax.P.b.c(VideoPlayerActivity.this, R.color.videoplayer_unplayed);
                    VideoPlayerActivity.this.l2 = 150L;
                } else {
                    iC = ax.P.b.c(VideoPlayerActivity.this, R.color.videoplayer_buffered);
                    VideoPlayerActivity.this.l2 = 300L;
                }
                VideoPlayerActivity.this.k1.setBufferedColor(iC);
            } else if (i == 2) {
                VideoPlayerActivity.this.n4();
                VideoPlayerActivity.this.R3(false);
            }
            VideoPlayerActivity.this.b4();
            VideoPlayerActivity.this.B2();
            VideoPlayerActivity.this.f4();
            VideoPlayerActivity.this.d4();
            VideoPlayerActivity.this.g4();
            VideoPlayerActivity.this.e4();
        }

        /* synthetic */ u(VideoPlayerActivity videoPlayerActivity, g gVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String A2() {
        return N2() == null ? HttpUrl.FRAGMENT_ENCODE_SET : C0735p.h(N2().getPath());
    }

    private void A3() {
        if (this.o1 != null) {
            o4();
            k4();
            this.o1.D0();
            this.o1 = null;
            this.p1 = null;
            this.O1 = null;
        }
        if (P.V0()) {
            z3();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B2() {
        MySpinner mySpinner = this.W0;
        if (mySpinner == null) {
            return;
        }
        mySpinner.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C2(boolean z) {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        long jU = k0Var.U();
        long j2 = z ? jU + 10000 : jU - 10000;
        long jI = this.o1.I();
        if (jI == -9223372036854775807L) {
            jI = 0;
        }
        if (j2 < 0) {
            j2 = 0;
        } else if (j2 > jI) {
            j2 = jI;
        }
        if (jU == j2) {
            return;
        }
        this.o1.I0(j0.c);
        this.o1.Y(j2);
        this.o1.I0(j0.g);
        if (z) {
            this.Y0.setText("+" + x.p(10000L));
        } else {
            this.Y0.setText("-" + x.p(10000L));
        }
        this.Y0.postDelayed(new b(), 1000L);
    }

    private void C3() {
        if (this.i1) {
            v2();
        }
        this.K1 = true;
        if (e3()) {
            if (g3()) {
                this.d1.setBackgroundColor(0);
                this.g1 = com.alphainventor.filemanager.ads.a.D(this, new e());
            } else {
                this.d1.setBackgroundColor(-1);
                com.alphainventor.filemanager.ads.a.E(this, new f());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D2(long j2, boolean z, boolean z2) {
        if (z2) {
            this.Y0.setText(x.p(j2));
        }
        this.G1 = j2;
        boolean z3 = this.H1 != z;
        this.H1 = z;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (z3 || jCurrentTimeMillis - this.F1 >= this.l2) {
            h4(z3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D3(View view, boolean z) {
        this.N1 = 0L;
        this.g1 = view;
        this.i1 = true;
        this.h1 = z;
        this.d1.removeAllViews();
        this.d1.addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E2() {
        k0 k0Var;
        this.E1 = false;
        this.Y0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        if (!this.C1) {
            this.F0.setUseController(true);
        }
        if (this.D1 && (k0Var = this.o1) != null) {
            k0Var.w(true);
        }
        h4(false);
        this.F1 = 0L;
        this.G1 = 0L;
        f4();
    }

    private void E3(boolean z, View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(z);
        view.setAlpha(z ? this.Z1 : this.a2);
        view.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F2() {
        if (this.o1 == null) {
            return;
        }
        this.E1 = true;
        boolean zX = this.F0.x();
        this.C1 = zX;
        if (!zX) {
            this.F0.setUseController(false);
        }
        boolean zB = this.o1.b();
        this.D1 = zB;
        if (zB) {
            this.o1.w(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F3(boolean z) {
        this.z1 = z;
        ax.p031c2.l.e(this, z);
        f4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G2() {
        this.p2.removeCallbacks(this.r2);
        this.F0.setUseController(false);
        finish();
    }

    private void G3(int i2) {
        this.w1 = i2;
        g4();
    }

    private String H2(String str, List<AbstractC5838l> list) {
        if (!V.y(str)) {
            ax.p221pa.c.h().g().b("Invalid media path 3").h("path:" + str).i();
            return null;
        }
        if (list == null) {
            return null;
        }
        String strL = V.l(str);
        for (String str2 : C0739u.m()) {
            String str3 = strL + "." + str2;
            for (AbstractC5838l abstractC5838l : list) {
                if (str3.equals(abstractC5838l.C())) {
                    return abstractC5838l.w();
                }
            }
        }
        return null;
    }

    private void H3() {
        if (N2() != null) {
            Intent intent = new Intent();
            intent.setData(M2());
            setResult(-1, intent);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00c2  */
    private Uri I2(Uri uri) {
        Uri uriBuild;
        List<AbstractC5838l> listH;
        String strH2;
        String string = uri.toString();
        if ("file".equals(uri.getScheme())) {
            return K2(uri.getPath());
        }
        if (string.startsWith("content://media")) {
            String strA = H.a(this, uri);
            if (strA != null) {
                return K2(strA);
            }
            return null;
        }
        if ("content".equals(uri.getScheme())) {
            Uri uriJ2 = J2(this, uri);
            v2.fine("subtitle uri : " + uriJ2);
            return uriJ2;
        }
        if (com.alphainventor.filemanager.service.b.k(this, uri)) {
            ax.G1.j jVarZ = ax.X1.b.z(uri.getPath());
            String strR = V.r(uri.getPath());
            ax.G1.j jVarZ2 = ax.X1.b.z(strR);
            if (jVarZ2 == null || jVarZ == null || !ax.J1.r.d(jVarZ2.d()).a() || (listH = ax.G1.b.k().h(jVarZ2.toString())) == null || (strH2 = H2(jVarZ.e(), listH)) == null) {
                uriBuild = null;
            } else {
                Uri.Builder builderBuildUpon = uri.buildUpon();
                builderBuildUpon.path(V.M(strR, strH2));
                uriBuild = builderBuildUpon.build();
                v2.fine("subtitle uri : " + uriBuild);
            }
        } else {
            uriBuild = null;
        }
        v2.fine("media uri : " + uri);
        return uriBuild;
    }

    private Uri J2(Context context, Uri uri) {
        String path = uri.getPath();
        if (!V.y(path)) {
            ax.p221pa.c.h().g().b("Invalid media path 2").h("path:" + path).i();
            return null;
        }
        String strL = V.l(path);
        ContentResolver contentResolver = context.getContentResolver();
        for (String str : C0739u.m()) {
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
    public void J3(boolean z) {
        this.E0.setKeepScreenOn(z);
    }

    private Uri K2(String str) {
        if (!V.y(str)) {
            ax.p221pa.c.h().g().b("Invalid media path 1").h("path:" + str).i();
            return null;
        }
        String strL = V.l(str);
        for (String str2 : C0739u.m()) {
            File file = new File(strL + "." + str2);
            if (file.exists()) {
                return Uri.fromFile(file);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K3(float f2) {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        this.B1 = f2;
        if (k0Var.d() == null || this.o1.d().a != f2) {
            if (f2 == 1.0f) {
                this.o1.H0(Y.e);
            } else {
                this.o1.H0(new Y(f2));
            }
        }
    }

    private int L2() {
        int i2 = this.w1;
        if (i2 < 0) {
            ax.p045d2.b.f();
            return 0;
        }
        if (i2 < R2()) {
            return this.w1;
        }
        ax.p045d2.b.f();
        return R2() - 1;
    }

    private void L3(int i2) {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        this.A1 = i2;
        k0Var.Q(i2);
        j4();
    }

    private Uri M2() {
        if (this.q1 == null) {
            ax.p045d2.b.f();
            return null;
        }
        int iL2 = L2();
        if (iL2 >= 0) {
            Uri[] uriArr = this.q1;
            if (iL2 < uriArr.length) {
                return uriArr[iL2];
            }
        }
        ax.p045d2.b.e("what case is this");
        return null;
    }

    private void M3(int i2, boolean z) {
        SubtitleView subtitleView = this.F0.getSubtitleView();
        subtitleView.setStyle(new ax.I4.a(-1, 0, 0, 2, -16777216, z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT));
        subtitleView.d(2, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri N2() {
        Uri[] uriArr = this.r1;
        if (uriArr != null) {
            return uriArr[L2()];
        }
        ax.p045d2.b.f();
        return null;
    }

    private void N3(String str) {
        AbstractC5569a abstractC5569aI0 = I0();
        if (abstractC5569aI0 != null) {
            abstractC5569aI0.F(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String O2(String str) {
        String strB = ax.p073f2.c.b(str);
        if (!TextUtils.isEmpty(strB)) {
            str = strB;
        }
        return getString(R.string.error_no_decoder, str);
    }

    private void O3() {
        AbstractC5569a abstractC5569aI0 = I0();
        if (abstractC5569aI0 == null) {
            return;
        }
        abstractC5569aI0.H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P3(String str) {
        Snackbar snackbarV;
        String strO2 = O2(str);
        if (ax.p073f2.c.s(this) || ((ax.p031c2.l.i(this) && ax.p073f2.c.r(this)) || !ax.p073f2.c.q(str) || ax.p073f2.c.k() == null)) {
            snackbarV = x.V(this.E0, strO2, 0);
        } else {
            snackbarV = x.V(this.E0, strO2, -2);
            snackbarV.p0(R.string.download_action, new n());
            this.a1 = snackbarV;
        }
        snackbarV.Y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String Q2(J j2, String str) {
        String str2;
        if (j2 == null) {
            return null;
        }
        for (int i2 = 0; i2 < j2.q; i2++) {
            I iA = j2.a(i2);
            for (int i3 = 0; i3 < iA.q; i3++) {
                O oA = iA.a(i3);
                if (oA != null && (str2 = oA.l0) != null && str2.startsWith(str)) {
                    return oA.l0;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q3() {
    }

    private int R2() {
        Uri[] uriArr = this.r1;
        if (uriArr == null) {
            return 0;
        }
        return uriArr.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R3(boolean z) {
        if (z) {
            this.X0.setVisibility(0);
            this.V0.setImageResource(R.drawable.ic_replay_44);
        } else {
            this.X0.setVisibility(8);
            this.V0.setImageResource(R.drawable.ic_play_arrow_44);
        }
        c4();
    }

    private void S3() {
        if (w().O0()) {
            return;
        }
        x.d0(w(), S.v3(), "settings", true);
    }

    private boolean T2() {
        return this.K1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T3(int i2) {
        x.U(this.E0, i2, 0).Y();
    }

    private static boolean U2(Uri[] uriArr) {
        for (Uri uri : uriArr) {
            if (C0740v.E(uri.getScheme()) && (uri.getHost() == null || uri.getHost().startsWith("."))) {
                return true;
            }
        }
        return false;
    }

    private void U3(int i2) {
        V3(getString(i2));
    }

    private void V2() {
        AbstractC5569a abstractC5569aI0 = I0();
        if (abstractC5569aI0 == null) {
            return;
        }
        abstractC5569aI0.l();
    }

    private void V3(String str) {
        Toast.makeText(this, str, 1).show();
    }

    private void W2() {
        AbstractC5569a abstractC5569aI0 = I0();
        abstractC5569aI0.F(HttpUrl.FRAGMENT_ENCODE_SET);
        abstractC5569aI0.u(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W3() {
        if (this.F0.x()) {
            this.F0.w();
        } else {
            this.F0.I();
        }
    }

    private void X2() {
        if ((ax.p031c2.l.i(this) && ax.p073f2.c.r(this)) ? ax.p073f2.c.m(this) : false) {
            return;
        }
        ax.p073f2.c.n(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X3() {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        boolean z = !k0Var.g();
        this.o1.w(z);
        if (z) {
            this.F0.w();
        }
    }

    private void Y2() {
        this.z1 = ax.p031c2.l.a(this);
        f4();
        L3(ax.p031c2.l.b(this));
        float f2 = this.B1;
        if (f2 != 0.0f) {
            K3(f2);
            this.W0.setSelection(P2(this.B1));
        }
        l4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y3() {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        k0Var.Q(w.a(k0Var.m0(), 3));
        j4();
    }

    private void Z2() {
        Resources resources = getResources();
        this.U1 = ax.p017b2.a.c(this, R.drawable.ic_subtitles_off);
        this.V1 = ax.p017b2.a.c(this, R.drawable.ic_subtitles_on);
        this.R1 = ax.p017b2.a.c(this, R.drawable.exo_controls_repeat_off);
        this.S1 = ax.p017b2.a.c(this, R.drawable.exo_controls_repeat_one);
        this.T1 = ax.p017b2.a.c(this, R.drawable.exo_controls_repeat_all);
        this.W1 = resources.getString(R.string.exo_controls_repeat_off_description);
        this.X1 = resources.getString(R.string.exo_controls_repeat_one_description);
        this.Y1 = resources.getString(R.string.exo_controls_repeat_all_description);
        this.Z1 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        this.a2 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z3() {
        this.y1 = !this.y1;
        m4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a3(boolean z) {
        int iL2 = L2();
        if (iL2 >= 0) {
            Uri[] uriArr = this.s1;
            if (iL2 < uriArr.length && uriArr[iL2] != null) {
                this.y1 = true;
                return;
            }
        }
        if (z) {
            this.y1 = true;
        } else {
            this.y1 = false;
        }
    }

    private void a4() {
        if (!this.I0.L()) {
            V2();
            return;
        }
        if (j3()) {
            O3();
        } else if (this.g2) {
            V2();
        } else {
            O3();
        }
    }

    private void b3() {
        if (this.r1 != null) {
            return;
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        this.v1 = -1;
        if (!"android.intent.action.VIEW".equals(action) || intent.getData() == null) {
            U3(R.string.error);
            finish();
            return;
        }
        ArrayList<com.alphainventor.filemanager.viewer.c.a> arrayListC = com.alphainventor.filemanager.viewer.c.b().c();
        if (arrayListC != null) {
            this.q1 = new Uri[arrayListC.size()];
            this.r1 = new Uri[arrayListC.size()];
            this.s1 = new Uri[arrayListC.size()];
            for (int i2 = 0; i2 < arrayListC.size(); i2++) {
                com.alphainventor.filemanager.viewer.c.a aVar = arrayListC.get(i2);
                Uri[] uriArr = this.q1;
                Uri uri = aVar.a;
                uriArr[i2] = uri;
                this.r1[i2] = y2(uri);
                Uri uri2 = aVar.b;
                if (uri2 != null) {
                    this.s1[i2] = y2(uri2);
                }
            }
            int i3 = 0;
            while (true) {
                Uri[] uriArr2 = this.q1;
                if (i3 >= uriArr2.length) {
                    break;
                }
                Uri uri3 = uriArr2[i3];
                if (uri3 != null && uri3.equals(intent.getData())) {
                    this.v1 = i3;
                }
                i3++;
            }
        } else {
            v2.fine("Video play : " + intent.getData());
            this.q1 = new Uri[]{intent.getData()};
            this.r1 = new Uri[]{y2(intent.getData())};
            this.s1 = new Uri[1];
        }
        boolean booleanExtra = intent.getBooleanExtra("detect_subtitle", true);
        this.t1 = booleanExtra;
        if (booleanExtra) {
            this.u1 = new boolean[this.r1.length];
        }
        int i4 = this.v1;
        if (i4 == -1) {
            G3(0);
        } else {
            G3(i4);
        }
        for (Uri uri4 : this.r1) {
            if (com.alphainventor.filemanager.service.b.k(this, uri4)) {
                this.m2 = true;
                this.h2 = true;
                ax.G1.j jVarZ = ax.X1.b.z(uri4.getPath());
                if (jVarZ != null) {
                    this.i2 = jVarZ.d();
                }
            } else if (C0740v.E(uri4.getScheme())) {
                this.m2 = true;
            }
        }
        if (this.h2 && this.i2 != null) {
            com.alphainventor.filemanager.service.b.f(this).l(true, this.i2);
        }
        if (U2(this.r1) || !ax.V4.O.k(this.r1)) {
            U3(R.string.error);
        } else {
            if (P.t0() && ax.p045d2.l.c()) {
                return;
            }
            ax.V4.O.g0(this, this.r1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b4() {
        View viewFindViewById;
        if (this.o1 == null) {
            return;
        }
        if (d3() && System.currentTimeMillis() - this.M1 > 30000) {
            this.L1 = false;
        }
        boolean zG = true;
        if (!m3()) {
            zG = true ^ this.o1.g();
        } else if (this.o1.g() && this.o1.P() != 4) {
            zG = false;
        }
        if (!zG || d3() || i3() || !e3() || l3()) {
            this.c1.setVisibility(8);
            return;
        }
        this.c1.setVisibility(0);
        if (!T2() || f3()) {
            C3();
        }
        if (!n3()) {
            this.f1.setVisibility(0);
            this.e1.setVisibility(8);
            return;
        }
        if (this.N1 == 0) {
            this.N1 = System.currentTimeMillis();
        }
        if (this.h1 && (viewFindViewById = this.g1.findViewById(R.id.media_container)) != null) {
            int iF = x.f(this, getResources().getDisplayMetrics().heightPixels);
            int i2 = 150;
            if (iF <= 360 && (i2 = 150 - (360 - iF)) < 60) {
                i2 = 60;
            }
            ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
            layoutParams.height = x.e(this, i2);
            viewFindViewById.setLayoutParams(layoutParams);
        }
        this.f1.setVisibility(8);
        this.e1.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c3() {
        b3();
        if (this.r1 != null && this.o1 == null) {
            X2();
            if (ax.E1.I.h()) {
                com.google.android.exoplayer2.ext.ffmpeg.a.n0(2);
            }
            ax.R4.a.d dVar = new ax.R4.a.d();
            C5531t c5531t = new C5531t(this);
            c5531t.i(1);
            q qVar = new q(this, dVar);
            this.O1 = qVar;
            qVar.K(this.P1);
            this.Q1 = null;
            ax.p145k4.r rVar = new ax.p145k4.r();
            k0.b bVar = new k0.b(this, c5531t);
            bVar.c(this.O1);
            bVar.b(rVar);
            k0 k0VarA = bVar.a();
            this.o1 = k0VarA;
            k0VarA.I0(j0.g);
            this.o1.C(new u(this, null));
            this.o1.w(this.b2);
            this.o1.w0(new C4778j(this.O1));
            if (this.m2) {
                this.o1.M0(2);
            } else {
                this.o1.M0(1);
            }
            this.F0.setPlayer(this.o1);
            this.F0.setPlaybackPreparer(this);
            this.F0.w();
            this.F0.setOnTouchListener(new l(new GestureDetector(this, this.t2)));
            try {
                this.p1 = s2(L2());
            } catch (IllegalStateException unused) {
            }
        }
        k0 k0Var = this.o1;
        if (k0Var != null) {
            int i2 = this.c2;
            boolean z = i2 != -1;
            if (z) {
                k0Var.f(i2, this.d2);
            }
            Y2();
            d4();
            g4();
            if (this.G0) {
                this.o1.C0(this.p1, true ^ z, false);
            } else {
                this.H0 = new m(z);
            }
        }
    }

    private void c4() {
        this.q2.j(this.g2 && !j3());
    }

    private boolean d3() {
        return this.L1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d4() {
    }

    private boolean e3() {
        return this.J1 != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e4() {
    }

    private boolean f3() {
        boolean z = this.N1 != 0 && System.currentTimeMillis() - this.N1 > 90000;
        if (z) {
            v2.fine("ad is expired");
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f4() {
        if (j3()) {
            this.J0.setVisibility(8);
            this.K0.setVisibility(8);
            this.L0.setVisibility(0);
            this.M0.setVisibility(0);
            this.S0.setVisibility(8);
            this.O0.setVisibility(8);
            this.P0.setVisibility(8);
        } else if (this.g2) {
            this.J0.setVisibility(0);
            this.K0.setVisibility(8);
            this.L0.setVisibility(8);
            this.M0.setVisibility(8);
        } else if (this.z1) {
            this.J0.setVisibility(8);
            this.K0.setVisibility(0);
            this.L0.setVisibility(0);
            this.M0.setVisibility(0);
            this.S0.setVisibility(0);
            this.O0.setVisibility(8);
            this.P0.setVisibility(0);
        } else {
            this.J0.setVisibility(8);
            this.K0.setVisibility(8);
            this.L0.setVisibility(0);
            this.M0.setVisibility(0);
            this.S0.setVisibility(0);
            this.O0.setVisibility(0);
            this.P0.setVisibility(8);
        }
        a4();
    }

    private boolean g3() {
        int i2 = this.J1;
        if (i2 == 33 || i2 == 34) {
            return new Random().nextBoolean();
        }
        return i2 == 17 || i2 == 18;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g4() {
        int iR2 = R2();
        boolean z = iR2 > 0;
        boolean z2 = L2() < iR2 - 1;
        E3(z, this.R0);
        E3(z2, this.Q0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h3(C5532u c5532u) {
        if (c5532u.q != 0) {
            return false;
        }
        for (Throwable thF = c5532u.f(); thF != null; thF = thF.getCause()) {
        }
        return false;
    }

    private void h4(boolean z) {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        long jU = k0Var.U();
        if (z || jU >= this.G1 || !this.H1) {
            if (z || jU <= this.G1 || this.H1) {
                if (this.H1) {
                    this.o1.I0(j0.e);
                } else {
                    this.o1.I0(j0.f);
                }
                this.o1.Y(this.G1);
                this.o1.I0(j0.g);
                this.F1 = System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i3() {
        return this.E1;
    }

    private void i4() {
        if (!this.I0.L()) {
            I3(true);
        } else if (!j3() && this.g2) {
            I3(true);
        } else {
            I3(false);
        }
        a4();
    }

    private boolean j3() {
        return this.X0.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j4() {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            this.N0.setImageDrawable(this.R1);
            this.N0.setContentDescription(this.W1);
            return;
        }
        int iM0 = k0Var.m0();
        if (iM0 == 0) {
            this.N0.setImageDrawable(this.R1);
            this.N0.setContentDescription(this.W1);
        } else if (iM0 == 1) {
            this.N0.setImageDrawable(this.S1);
            this.N0.setContentDescription(this.X1);
        } else {
            if (iM0 != 2) {
                return;
            }
            this.N0.setImageDrawable(this.T1);
            this.N0.setContentDescription(this.Y1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k3() {
        if (N2() == null) {
            return false;
        }
        String scheme = N2().getScheme();
        return "file".equals(scheme) || "content".equals(scheme);
    }

    private void k4() {
        k0 k0Var = this.o1;
        if (k0Var != null) {
            this.b2 = k0Var.g();
            this.c2 = this.o1.u();
            this.d2 = Math.max(0L, this.o1.y());
        }
    }

    private boolean l3() {
        return v.o(this);
    }

    private boolean m3() {
        int i2 = this.J1;
        return i2 == 2 || i2 == 18 || i2 == 34;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m4() {
        E3(this.x1, this.U0);
        if (this.x1 && this.y1) {
            this.U0.setImageDrawable(this.V1);
            this.F0.getSubtitleView().setVisibility(0);
        } else {
            this.U0.setImageDrawable(this.U1);
            this.F0.getSubtitleView().setVisibility(8);
        }
    }

    private boolean n3() {
        return this.i1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n4() {
        Uri uriM2 = M2();
        if (uriM2 == null) {
            N3(HttpUrl.FRAGMENT_ENCODE_SET);
            return;
        }
        if (!"file".equals(uriM2.getScheme()) && !C0740v.E(uriM2.getScheme()) && !MyFileProvider.w(uriM2)) {
            if ("content".equals(uriM2.getScheme())) {
                N3(C0740v.r(this, uriM2).a);
            }
        } else if (uriM2.getPath() != null) {
            N3(V.h(uriM2.getPath()));
        } else {
            N3(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o3() {
        return this.n2 >= 0;
    }

    private void o4() {
        ax.R4.c cVar = this.O1;
        if (cVar != null) {
            this.P1 = cVar.v();
        }
    }

    private void p3(boolean z) {
        if (P.n0()) {
            if (z) {
                setRequestedOrientation(14);
                return;
            } else {
                setRequestedOrientation(-1);
                return;
            }
        }
        if (z) {
            setRequestedOrientation(v.j(this));
        } else {
            setRequestedOrientation(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q3(boolean z) {
        this.g2 = z;
        if (z) {
            this.F0.setControllerShowTimeoutMs(3000);
        } else {
            this.F0.setControllerShowTimeoutMs(5000);
        }
        p3(this.g2);
        f4();
        i4();
        c4();
    }

    private ax.U4.i.a r2() {
        return new ax.U4.q(this, S2(), new ax.U4.n.b(this).a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r3() {
        if (this.o1 != null && L2() < R2() - 1) {
            v3();
        }
    }

    private InterfaceC0662k s2(int i2) {
        if (this.t1 && !this.u1[i2]) {
            Uri[] uriArr = this.s1;
            if (uriArr[i2] == null) {
                uriArr[i2] = I2(this.r1[i2]);
                this.u1[i2] = true;
            }
        }
        return t2(this.r1[i2], this.s1[i2], null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s3() {
        if (this.o1 == null) {
            return;
        }
        if (L2() < R2() - 1) {
            v3();
        } else {
            t3();
        }
    }

    private InterfaceC0662k t2(Uri uri, Uri uri2, String str) {
        InterfaceC0662k interfaceC0662kA = this.n1.a(uri);
        return uri2 == null ? interfaceC0662kA : new ax.G4.w(interfaceC0662kA, new ax.G4.H.b(this.m1).a(uri2, O.F(null, C0739u.l(V.f(uri2.getPath())), null, -1, 1, Locale.getDefault().getLanguage(), null, Long.MAX_VALUE), -9223372036854775807L));
    }

    private void t3() {
        G3(0);
        u3(L2());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u2() {
        if (o3()) {
            this.o2 = true;
            this.p2.removeCallbacks(this.r2);
        }
    }

    private void u3(int i2) {
        if (i2 < 0 || i2 >= R2()) {
            ax.p045d2.b.f();
        } else {
            if (this.o1 == null) {
                return;
            }
            InterfaceC0662k interfaceC0662kS2 = s2(i2);
            this.p1 = interfaceC0662kS2;
            this.o1.C0(interfaceC0662kS2, true, false);
        }
    }

    private void v2() {
        View view = this.g1;
        if (view != null) {
            com.alphainventor.filemanager.ads.a.j(view, this);
        }
        this.g1 = null;
        this.i1 = false;
        this.K1 = false;
        this.d1.removeAllViews();
    }

    private void v3() {
        if (L2() >= R2()) {
            ax.p045d2.b.e("bad index");
        } else {
            G3(L2() + 1);
            u3(L2());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w2() {
        this.b2 = true;
        this.c2 = -1;
        this.d2 = -9223372036854775807L;
    }

    private void w3() {
        if (L2() <= 0) {
            return;
        }
        G3(L2() - 1);
        u3(L2());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x2() {
        this.L1 = true;
        this.M1 = System.currentTimeMillis();
        v2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x3() {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        int iP = k0Var.P();
        long jU = this.o1.U();
        if (L2() == 0 || (iP != 4 && jU > 3000)) {
            this.o1.Y(0L);
        } else if (L2() > 0) {
            w3();
        }
    }

    private Uri y2(Uri uri) {
        if (MyFileProvider.v(uri)) {
            return Uri.fromFile(new File(MyFileProvider.d(uri).e()));
        }
        if (MyFileProvider.u(uri)) {
            return uri;
        }
        MyFileProvider.x(uri);
        return uri;
    }

    private void y3() {
    }

    private void z3() {
    }

    public void B3() {
        u3(L2());
    }

    public void I3(boolean z) {
        int i2 = z ? 3846 : 1792;
        this.f2 = i2;
        this.E0.setSystemUiVisibility(i2);
    }

    int P2(float f2) {
        List<s> listB = r.b();
        for (int i2 = 0; i2 < listB.size(); i2++) {
            if (listB.get(i2).b == f2) {
                return i2;
            }
        }
        ax.p045d2.b.f();
        return P2(1.0f);
    }

    public String S2() {
        if (this.e2 == null) {
            this.e2 = ax.V4.O.V(this, "FileManager");
        }
        return this.e2;
    }

    @Override // com.google.android.exoplayer2.ui.b.d
    public void U(int i2) {
        Snackbar snackbar;
        i4();
        B2();
        if (i2 != 0 || (snackbar = this.a1) == null) {
            return;
        }
        snackbar.x();
        this.a1 = null;
    }

    @Override // ax.p182n.ActivityC5571c, ax.O.h, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.F0.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public void finish() {
        if (!o3()) {
            H3();
        } else if (this.o2) {
            setResult(0);
        } else {
            setResult(-1);
        }
        super.finish();
        if (!o3() || this.o2) {
            return;
        }
        overridePendingTransition(R.anim.slideshow_in_right, R.anim.slideshow_out_left);
    }

    public void l4() {
        M3(ax.p031c2.l.d(this), true);
    }

    @Override // ax.p182n.ActivityC5571c, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b4();
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    public void onCreate(Bundle bundle) {
        ax.p338y1.b.f(this, true);
        super.onCreate(bundle);
        if (!P.F0()) {
            ax.p221pa.c.h().g().d("VIDEO PLAYER NOT SUPPORTED").i();
            Toast.makeText(this, R.string.error, 1).show();
            finish();
            return;
        }
        this.n2 = getIntent().getIntExtra("slide_interval", -1);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        try {
            ax.U4.i.a aVarR2 = r2();
            this.m1 = aVarR2;
            this.n1 = new A.a(aVarR2);
            setContentView(R.layout.activity_videoplayer);
            this.E0 = findViewById(R.id.root_view);
            this.l1 = (TextView) findViewById(R.id.debug_text_view);
            S0((Toolbar) findViewById(R.id.toolbar));
            Locale locale = getResources().getConfiguration().locale;
            if (locale != null) {
                this.j1 = locale.toLanguageTag();
            } else {
                this.j1 = Locale.getDefault().toLanguageTag();
            }
            PlayerView playerView = (PlayerView) findViewById(R.id.player_view);
            this.F0 = playerView;
            if (playerView.getVideoSurfaceView() instanceof SurfaceView) {
                ((SurfaceView) this.F0.getVideoSurfaceView()).getHolder().addCallback(new g());
            }
            this.F0.setControllerVisibilityListener(this);
            this.F0.setErrorMessageProvider(new t(this, null));
            this.F0.requestFocus();
            this.b1 = findViewById(R.id.progress);
            this.Z0 = findViewById(R.id.exo_content_frame);
            com.google.android.exoplayer2.ui.b bVar = (com.google.android.exoplayer2.ui.b) findViewById(R.id.exo_controller);
            this.I0 = bVar;
            bVar.setFitsSystemWindows(true);
            this.Y0 = (TextView) findViewById(R.id.drag_seek_text);
            this.J0 = findViewById(R.id.control_buttons_line0);
            this.K0 = findViewById(R.id.control_buttons_line1);
            this.L0 = findViewById(R.id.control_buttons_line2);
            this.M0 = findViewById(R.id.control_progress_line);
            View viewFindViewById = findViewById(R.id.custom_less);
            this.P0 = viewFindViewById;
            viewFindViewById.setOnClickListener(this.u2);
            View viewFindViewById2 = findViewById(R.id.custom_more);
            this.O0 = viewFindViewById2;
            viewFindViewById2.setOnClickListener(this.u2);
            ImageButton imageButton = (ImageButton) findViewById(R.id.custom_repeat_toggle);
            this.N0 = imageButton;
            imageButton.setOnClickListener(this.u2);
            View viewFindViewById3 = findViewById(R.id.custom_next);
            this.Q0 = viewFindViewById3;
            viewFindViewById3.setOnClickListener(this.u2);
            View viewFindViewById4 = findViewById(R.id.custom_prev);
            this.R0 = viewFindViewById4;
            viewFindViewById4.setOnClickListener(this.u2);
            View viewFindViewById5 = findViewById(R.id.custom_lock);
            this.S0 = viewFindViewById5;
            viewFindViewById5.setOnClickListener(this.u2);
            View viewFindViewById6 = findViewById(R.id.custom_unlock);
            this.T0 = viewFindViewById6;
            viewFindViewById6.setOnClickListener(this.u2);
            MySpinner mySpinner = (MySpinner) findViewById(R.id.custom_speed);
            this.W0 = mySpinner;
            mySpinner.setPromptId(R.string.playback_speed);
            r rVar = new r(this);
            this.W0.setAdapter((SpinnerAdapter) rVar);
            this.W0.setOnItemSelectedListener(new h(rVar));
            ImageButton imageButton2 = (ImageButton) findViewById(R.id.custom_subtitles);
            this.U0 = imageButton2;
            imageButton2.setOnClickListener(this.u2);
            this.V0 = (ImageButton) findViewById(R.id.exo_play);
            this.X0 = findViewById(R.id.end_overlay);
            this.c1 = (ViewGroup) findViewById(R.id.ads_overlay);
            this.d1 = (ViewGroup) findViewById(R.id.ads_container);
            this.e1 = findViewById(R.id.ads_close);
            this.f1 = findViewById(R.id.ads_progress);
            this.e1.setOnClickListener(new i());
            com.google.android.exoplayer2.ui.a aVar = (com.google.android.exoplayer2.ui.a) this.F0.findViewById(R.id.exo_progress);
            this.k1 = aVar;
            aVar.b(this.s2);
            this.E0.setOnSystemUiVisibilityChangeListener(new j());
            this.I1 = x.e(this, 30);
            this.J1 = ax.p031c2.a.d();
            Z2();
            W2();
            if (bundle != null) {
                ax.R4.c.C0224c c0224c = (ax.R4.c.C0224c) bundle.getParcelable("track_selector_parameters");
                this.P1 = c0224c;
                if (c0224c == null) {
                    this.P1 = z2();
                }
                this.b2 = bundle.getBoolean("auto_play");
                this.c2 = bundle.getInt("window", -1);
                this.d2 = bundle.getLong("position", -1L);
                this.B1 = bundle.getFloat("speed", 1.0f);
            } else {
                this.P1 = z2();
                w2();
            }
            q().h(this, this.q2);
        } catch (SecurityException unused) {
            ax.p221pa.c.h().g().d("VIDEO PLAYER BUILD SOURCE").i();
            Toast.makeText(this, R.string.error, 1).show();
            finish();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_player, menu);
        return true;
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    public void onDestroy() {
        y3();
        if (this.h2 && this.i2 != null) {
            com.alphainventor.filemanager.service.b.f(this).l(false, this.i2);
        }
        if (this.g1 != null) {
            v2();
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        A3();
        y3();
        w2();
        setIntent(intent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
            return true;
        }
        if (itemId != R.id.menu_settings) {
            return false;
        }
        S3();
        return true;
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    public void onPause() {
        super.onPause();
        if (ax.V4.O.a <= 23) {
            PlayerView playerView = this.F0;
            if (playerView != null) {
                playerView.C();
            }
            A3();
        }
        View view = this.g1;
        if (view == null || this.h1) {
            return;
        }
        com.alphainventor.filemanager.ads.a.v(view, this);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (iArr.length == 0) {
            return;
        }
        if (iArr[0] == 0) {
            c3();
        } else {
            U3(R.string.error_access_denied);
            finish();
        }
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    public void onResume() {
        super.onResume();
        if (ax.V4.O.a <= 23 || this.o1 == null) {
            c3();
            PlayerView playerView = this.F0;
            if (playerView != null) {
                playerView.D();
            }
        }
        View view = this.g1;
        if (view == null || this.h1) {
            return;
        }
        com.alphainventor.filemanager.ads.a.F(view, this);
    }

    @Override // androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        o4();
        k4();
        bundle.putParcelable("track_selector_parameters", this.P1);
        bundle.putBoolean("auto_play", this.b2);
        bundle.putInt("window", this.c2);
        bundle.putLong("position", this.d2);
        bundle.putFloat("speed", this.B1);
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    public void onStart() {
        super.onStart();
        if (ax.V4.O.a > 23) {
            c3();
            PlayerView playerView = this.F0;
            if (playerView != null) {
                playerView.D();
            }
        }
        if (P.x1()) {
            ax.E1.u.r(getWindow(), -1157627904);
            ax.E1.u.o(getWindow(), -1157627904);
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    public void onStop() {
        super.onStop();
        if (ax.V4.O.a > 23) {
            PlayerView playerView = this.F0;
            if (playerView != null) {
                playerView.C();
            }
            A3();
        }
    }

    @Override // ax.p145k4.Z
    public void p() {
        k0 k0Var = this.o1;
        if (k0Var == null) {
            return;
        }
        k0Var.F0();
    }

    ax.R4.c.C0224c z2() {
        ax.R4.c.d dVar = new ax.R4.c.d(this);
        dVar.j(this.j1);
        dVar.i(this.j1);
        dVar.l(true);
        return dVar.a();
    }
}
