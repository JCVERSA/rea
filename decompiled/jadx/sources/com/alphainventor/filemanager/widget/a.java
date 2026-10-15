package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ax.A1.h;
import ax.J1.C0720a;
import ax.J1.C0730k;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.C0741w;
import ax.J1.EnumC0738t;
import ax.J1.F;
import ax.J1.V;
import ax.p031c2.k;
import ax.p045d2.n;
import ax.p045d2.v;
import ax.p045d2.x;
import ax.p338y1.f;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.E;
import com.alphainventor.filemanager.file.u;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class a extends ArrayAdapter<AbstractC5838l> {
    private static ColorStateList D0;
    private static ColorStateList E0;
    private boolean A0;
    private int B0;
    private int C0;
    private Context X;
    private boolean Y;
    private C5839m Z;
    private ax.p003a2.c h0;
    private int i0;
    private int j0;
    private boolean k0;
    private boolean l0;
    private boolean m0;
    private long n0;
    private long o0;
    private d p0;
    private final Object q;
    private boolean q0;
    private boolean r0;
    private boolean s0;
    private boolean t0;
    private f u0;
    private boolean v0;
    private int w0;
    private String x0;
    private int y0;
    private int z0;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.widget.a$a, reason: collision with other inner class name */
    static /* synthetic */ class C0518a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.B3.c.a.values().length];
            a = iArr;
            try {
                iArr[ax.B3.c.a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.B3.c.a.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.B3.c.a.PLAYING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class b {
        private int A;
        private long B;
        private c C;
        private C0741w.a D;
        private int E = -1;
        private boolean F = false;
        final Runnable G = new f();
        private View a;
        private View b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private TextView h;
        private TextView i;
        private View j;
        private View k;
        private ImageView l;
        private ImageView m;
        private ImageView n;
        private ImageView o;
        private ImageView p;
        private View q;
        private boolean r;
        private boolean s;
        private String t;
        private long u;
        private EnumC0738t v;
        private String w;
        private boolean x;
        private boolean y;
        private int z;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.widget.a$b$a, reason: collision with other inner class name */
        class C0519a implements ActivatableLinearLayout.a {
            C0519a() {
            }

            @Override // com.alphainventor.filemanager.widget.ActivatableLinearLayout.a
            public void a(boolean z) {
                if (b.this.q != null) {
                    if (z) {
                        b.this.q.setVisibility(0);
                    } else {
                        b.this.q.setVisibility(8);
                    }
                }
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.widget.a$b$b, reason: collision with other inner class name */
        class C0520b extends ax.P1.c {
            C0520b() {
            }

            @Override // ax.P1.c
            public void a(View view) {
                a.this.p0.a(b.this.A);
            }
        }

        class c implements View.OnLongClickListener {
            final /* synthetic */ ViewGroup q;

            c(ViewGroup viewGroup) {
                this.q = viewGroup;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                ViewGroup viewGroup = this.q;
                if (viewGroup instanceof FileListView) {
                    ((FileListView) viewGroup).a();
                }
                a.this.p0.b(b.this.A);
                return true;
            }
        }

        class d extends ax.P1.c {
            d() {
            }

            @Override // ax.P1.c
            public void a(View view) {
                a.this.p0.a(b.this.A);
            }
        }

        class e implements View.OnLongClickListener {
            final /* synthetic */ ViewGroup q;

            e(ViewGroup viewGroup) {
                this.q = viewGroup;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                ViewGroup viewGroup = this.q;
                if (viewGroup instanceof FileListView) {
                    ((FileListView) viewGroup).a();
                }
                a.this.p0.b(b.this.A);
                return true;
            }
        }

        class f implements Runnable {
            f() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.v == EnumC0738t.PDF) {
                    b.this.F = true;
                    b.this.p.setImageResource(R.drawable.ic_v_pdf);
                    b.this.p.setVisibility(0);
                    b.this.p.setBackgroundResource(0);
                    return;
                }
                if (b.this.v == EnumC0738t.VIDEO || (b.this.w != null && b.this.w.startsWith("video"))) {
                    b.this.m.setVisibility(0);
                }
            }
        }

        public b(View view, ViewGroup viewGroup) {
            w(view, viewGroup);
        }

        private void A(Resources resources, View view, int i, int i2) {
            int dimensionPixelSize = x() ? resources.getDimensionPixelSize(i2) : resources.getDimensionPixelSize(i);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = dimensionPixelSize;
            layoutParams.width = dimensionPixelSize;
            view.setLayoutParams(layoutParams);
        }

        private void B(Resources resources, View view, int i, int i2) {
            int dimensionPixelSize = x() ? resources.getDimensionPixelSize(i2) : resources.getDimensionPixelSize(i);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.topMargin = dimensionPixelSize;
            view.setLayoutParams(marginLayoutParams);
        }

        private void C(AbstractC5838l abstractC5838l, boolean z) {
            C0741w.a aVarC = z ? C0741w.c(abstractC5838l) : C0741w.b(abstractC5838l);
            if (aVarC == null) {
                if (z) {
                    G(R.drawable.v_shape_folder_dir);
                    return;
                }
                return;
            }
            int i = aVarC.b;
            if (i != 0) {
                if (z) {
                    G(i);
                    return;
                } else {
                    E(i);
                    return;
                }
            }
            String[] strArr = aVarC.a;
            if (strArr != null) {
                this.D = aVarC;
                for (String str : strArr) {
                    if (H(str, z)) {
                        this.D = null;
                        return;
                    }
                }
            }
        }

        private void D(AbstractC5838l abstractC5838l, boolean z, ax.B3.c.a aVar) {
            boolean z2;
            if (a.this.r0 && ((u) abstractC5838l).N0()) {
                this.p.setImageResource(R.drawable.ic_sd_card);
                this.p.setVisibility(0);
                z2 = true;
            } else {
                this.p.setVisibility(8);
                z2 = false;
            }
            if (this.F) {
                this.p.setVisibility(0);
                this.p.setImageResource(R.drawable.ic_v_pdf);
                this.p.setBackgroundResource(0);
            } else {
                this.p.setBackgroundResource(R.drawable.bg_subicon);
            }
            if (!z && !C0740v.H(abstractC5838l)) {
                if (C0740v.z(abstractC5838l.N(), abstractC5838l)) {
                    this.p.setImageResource(R.drawable.ic_save_black);
                    this.p.setVisibility(0);
                    z2 = true;
                } else {
                    this.p.setVisibility(8);
                }
            }
            if (aVar != null) {
                int i = C0518a.a[aVar.ordinal()];
                if (i == 1) {
                    if (z2) {
                        return;
                    }
                    this.p.setVisibility(8);
                } else {
                    if (i == 2) {
                        Drawable drawableR = ax.T.a.r(ax.p017b2.a.c(a.this.getContext(), R.drawable.ic_equalizer1_white_36dp));
                        ax.T.a.o(drawableR, a.E0);
                        this.p.setVisibility(0);
                        this.p.setImageDrawable(drawableR);
                        return;
                    }
                    if (i != 3) {
                        return;
                    }
                    AnimationDrawable animationDrawable = (AnimationDrawable) ax.p017b2.a.c(a.this.getContext(), R.drawable.ic_equalizer_white_36dp);
                    Drawable drawableR2 = ax.T.a.r(animationDrawable);
                    ax.T.a.o(drawableR2, a.D0);
                    animationDrawable.start();
                    this.p.setVisibility(0);
                    this.p.setImageDrawable(drawableR2);
                }
            }
        }

        private void E(int i) {
            this.n.setImageResource(i);
            if (a.this.e0()) {
                this.n.setBackgroundResource(R.drawable.bg_folder_type_shape);
            } else {
                this.n.setBackgroundResource(R.drawable.bg_folder_type_s_shape);
            }
            this.n.setVisibility(0);
            this.r = true;
        }

        private void F(String str) {
            ax.B1.f fVarX = ax.B1.d.F(a.this.X).x(str);
            if (fVarX != null) {
                this.n.setImageDrawable(null);
                a.this.M().s(fVarX, this.n);
                this.n.setVisibility(0);
                this.n.setBackgroundDrawable(null);
                this.n.setPadding(a.this.z0, a.this.z0, a.this.z0, a.this.z0);
                this.r = true;
            }
        }

        private void G(int i) {
            this.n.setImageResource(i);
            this.n.setBackgroundResource(R.drawable.bg_subicon);
            this.n.setVisibility(0);
            this.r = true;
        }

        private boolean H(String str, boolean z) {
            ax.B1.f fVarX = ax.B1.d.F(a.this.X).x(str);
            if (fVarX == null) {
                if (!z) {
                    return false;
                }
                G(R.drawable.v_shape_folder_dir);
                return true;
            }
            if (!a.this.M().y(fVarX, this.n)) {
                return false;
            }
            this.n.setBackgroundDrawable(null);
            this.n.setPadding(a.this.z0, a.this.z0, a.this.z0, a.this.z0);
            this.n.setVisibility(0);
            this.r = true;
            return true;
        }

        private void k() {
            int dimensionPixelSize;
            float dimension;
            View view;
            if (this.E != a.this.j0) {
                Resources resources = this.j.getResources();
                this.E = a.this.j0;
                if (a.this.i0 == 2) {
                    View view2 = this.k;
                    if (view2 != null) {
                        A(resources, view2, R.dimen.file_grid_icon_size_medium, R.dimen.file_grid_icon_size_large);
                    }
                    ImageView imageView = this.n;
                    if (imageView != null) {
                        A(resources, imageView, R.dimen.file_grid_folder_icon_size_medium, R.dimen.file_grid_folder_icon_size_large);
                        B(resources, this.n, R.dimen.file_grid_folder_icon_margintop_medium, R.dimen.file_grid_folder_icon_margintop_large);
                        return;
                    }
                    return;
                }
                if (a.this.i0 != 1 && a.this.i0 != 0) {
                    if (a.this.i0 != 10 || (view = this.k) == null) {
                        return;
                    }
                    A(resources, view, R.dimen.file_media_list_icon_size_medium, R.dimen.file_media_list_icon_size_large);
                    return;
                }
                View view3 = this.k;
                if (view3 != null) {
                    A(resources, view3, R.dimen.file_list_icon_size_medium, R.dimen.file_list_icon_size_large);
                    if (x()) {
                        dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.file_list_min_height_large);
                        dimension = resources.getDimension(R.dimen.file_list_filename_size_large);
                    } else {
                        dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.file_list_min_height_medium);
                        dimension = resources.getDimension(R.dimen.file_list_filename_size_normal);
                    }
                    this.j.setMinimumHeight(dimensionPixelSize);
                    this.e.setTextSize(0, dimension);
                }
                ImageView imageView2 = this.n;
                if (imageView2 != null) {
                    A(resources, imageView2, R.dimen.file_list_folder_icon_size_medium, R.dimen.file_list_folder_icon_size_large);
                    B(resources, this.n, R.dimen.file_list_folder_icon_margintop_medium, R.dimen.file_list_folder_icon_margintop_large);
                }
            }
        }

        private void m(long j) {
            View view = this.a;
            if (view != null) {
                LayerDrawable layerDrawable = (LayerDrawable) view.getBackground().mutate();
                if (!a.this.k0) {
                    layerDrawable.getDrawable(0).setLevel(0);
                    layerDrawable.getDrawable(1).setLevel(0);
                    return;
                }
                if (a.this.o0 > 0) {
                    layerDrawable.getDrawable(0).mutate().setLevel((int) ((j * 10000) / a.this.o0));
                } else {
                    layerDrawable.getDrawable(0).setLevel(0);
                }
                if (a.this.n0 > 0) {
                    layerDrawable.getDrawable(1).setLevel((int) ((j * 10000) / a.this.n0));
                } else {
                    layerDrawable.getDrawable(1).setLevel(0);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n(AbstractC5838l abstractC5838l) {
            if (abstractC5838l.isDirectory()) {
                this.z = abstractC5838l.r(a.this.q0);
                if (this.l.getTag() == null) {
                    if (this.z != 0 || !C0740v.H(abstractC5838l)) {
                        this.l.setImageDrawable(a.this.I(abstractC5838l, true, this.z));
                    } else if (((u) abstractC5838l).H0()) {
                        this.l.setImageDrawable(a.this.I(abstractC5838l, true, 1));
                    } else {
                        this.l.setImageDrawable(a.this.I(abstractC5838l, true, this.z));
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void o(AbstractC5838l abstractC5838l) {
            if (a.this.i0 == 12) {
                if (abstractC5838l.isDirectory()) {
                    this.e.setText(v(abstractC5838l));
                    return;
                }
                return;
            }
            if (this.i != null) {
                if (!abstractC5838l.isDirectory()) {
                    this.i.setText(abstractC5838l.I(a.this.q0));
                    return;
                }
                if (a.this.i0 == 10) {
                    if (this.z < 0) {
                        this.i.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                        return;
                    }
                    this.i.setText(new SpannableString("(" + this.z + ")"));
                    return;
                }
                if (!a.this.l0) {
                    this.i.setText(abstractC5838l.I(a.this.q0));
                    return;
                }
                this.i.setText(abstractC5838l.I(a.this.q0) + String.format(" (%s)", C0740v.f(a.this.getContext(), this.B)));
            }
        }

        private void p(AbstractC5838l abstractC5838l, boolean z, long j, boolean z2) {
            boolean z3;
            ImageView imageView;
            this.s = z;
            this.u = j;
            this.v = abstractC5838l.E();
            this.w = abstractC5838l.s();
            this.t = abstractC5838l.Q();
            this.x = C0739u.o(abstractC5838l);
            this.y = z2;
            this.m.setVisibility(8);
            this.D = null;
            if (this.r && (imageView = this.n) != null) {
                imageView.setVisibility(8);
                a.this.M().g(this.n);
                this.r = false;
            }
            a.this.M().g(this.l);
            c cVar = this.C;
            if (cVar != null) {
                cVar.e();
                this.C = null;
            }
            this.l.setTag(null);
            this.B = 0L;
            this.F = false;
            if (!z) {
                z3 = a.this.M().z(abstractC5838l, this.l, this.G);
            } else if (ax.p338y1.f.j0(a.this.u0)) {
                z3 = a.this.M().z(abstractC5838l, this.l, this.G);
                if (this.n != null && a.this.v0) {
                    C(abstractC5838l, true);
                }
            } else {
                if (a.this.u0 == ax.p338y1.f.l1) {
                    if (C0720a.M1(abstractC5838l)) {
                        z3 = a.this.M().z(abstractC5838l, this.l, this.G);
                    }
                } else if (this.n != null && a.this.v0) {
                    C(abstractC5838l, false);
                }
                z3 = false;
            }
            this.z = -1;
            if (!z3) {
                this.l.setImageDrawable(a.this.I(abstractC5838l, z, -1));
            }
            if (abstractC5838l.m()) {
                this.o.setVisibility(0);
            } else {
                this.o.setVisibility(4);
            }
            z(this.l, abstractC5838l.g());
        }

        /* JADX WARN: Code duplicated, block: B:101:0x01f3  */
        /* JADX WARN: Code duplicated, block: B:102:0x0216  */
        /* JADX WARN: Code duplicated, block: B:106:0x0228  */
        /* JADX WARN: Code duplicated, block: B:108:0x0230  */
        /* JADX WARN: Code duplicated, block: B:110:0x0240 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:111:0x0242  */
        /* JADX WARN: Code duplicated, block: B:119:0x0275  */
        /* JADX WARN: Code duplicated, block: B:121:0x027d  */
        /* JADX WARN: Code duplicated, block: B:122:0x028b  */
        /* JADX WARN: Code duplicated, block: B:125:0x02a0 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:131:0x02b9  */
        /* JADX WARN: Code duplicated, block: B:137:0x02c5 A[LOOP:0: B:136:0x02c3->B:137:0x02c5, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:140:0x02d0 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:54:0x00f2  */
        /* JADX WARN: Code duplicated, block: B:57:0x0102  */
        /* JADX WARN: Code duplicated, block: B:58:0x0108  */
        /* JADX WARN: Code duplicated, block: B:61:0x011c  */
        /* JADX WARN: Code duplicated, block: B:63:0x0125  */
        /* JADX WARN: Code duplicated, block: B:64:0x0133  */
        /* JADX WARN: Code duplicated, block: B:67:0x0140  */
        /* JADX WARN: Code duplicated, block: B:70:0x014f A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:71:0x0151  */
        /* JADX WARN: Code duplicated, block: B:73:0x0154  */
        /* JADX WARN: Code duplicated, block: B:75:0x0158  */
        /* JADX WARN: Code duplicated, block: B:78:0x015e  */
        /* JADX WARN: Code duplicated, block: B:79:0x0165 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:80:0x0167  */
        /* JADX WARN: Code duplicated, block: B:81:0x016e  */
        /* JADX WARN: Code duplicated, block: B:82:0x0176  */
        /* JADX WARN: Code duplicated, block: B:83:0x0188  */
        /* JADX WARN: Code duplicated, block: B:85:0x0198  */
        /* JADX WARN: Code duplicated, block: B:86:0x01a2  */
        /* JADX WARN: Code duplicated, block: B:89:0x01b7  */
        /* JADX WARN: Code duplicated, block: B:91:0x01bd  */
        /* JADX WARN: Code duplicated, block: B:93:0x01c1  */
        /* JADX WARN: Code duplicated, block: B:95:0x01ca  */
        /* JADX WARN: Code duplicated, block: B:96:0x01d1  */
        /* JADX WARN: Code duplicated, block: B:97:0x01db  */
        /* JADX WARN: Code duplicated, block: B:98:0x01e5  */
        /* JADX WARN: Code duplicated, block: B:99:0x01eb  */
        private void r(AbstractC5838l abstractC5838l, int i, boolean z) {
            boolean z2;
            boolean z3;
            int i2;
            boolean z4;
            C0741w.a aVar;
            String[] strArr;
            int i3;
            u uVar;
            a aVar2;
            if (a.this.l0) {
                if (!z || E.Y1(abstractC5838l)) {
                    this.B = abstractC5838l.o();
                } else {
                    this.B = h.P(abstractC5838l).A(abstractC5838l);
                }
            }
            m(this.B);
            ax.B3.c.a aVarD = this.x ? ax.B3.c.d(a.this.getContext(), abstractC5838l.Q()) : null;
            if (this.p != null) {
                D(abstractC5838l, z, aVarD);
            }
            if (aVarD == null || aVarD == ax.B3.c.a.NONE) {
                this.e.setTextColor(a.this.B0);
            } else {
                this.e.setTextColor(a.this.C0);
            }
            if (!C0740v.H(abstractC5838l) || ((u) abstractC5838l).M0()) {
                if (z) {
                    this.z = abstractC5838l.r(a.this.q0);
                }
                z2 = false;
            } else {
                z2 = true;
            }
            if (!a.this.l0 || abstractC5838l.P() != ax.p338y1.f.l1) {
                if (a.this.l0 && E.Y1(abstractC5838l)) {
                    TextView textView = this.i;
                    if (textView != null) {
                        textView.setText(C0740v.f(a.this.getContext(), this.B));
                    }
                } else {
                    if (!z2) {
                        n(abstractC5838l);
                        o(abstractC5838l);
                    }
                    z3 = z2;
                }
                this.e.setText(v(abstractC5838l));
                if (a.this.t0 && this.b != null) {
                    if (i != 0) {
                        aVar2 = a.this;
                        if (aVar2.R(abstractC5838l, (AbstractC5838l) aVar2.getItem(i - 1))) {
                            this.b.setVisibility(8);
                        } else {
                            this.b.setVisibility(0);
                            this.c.setText(a.this.H(abstractC5838l));
                        }
                    } else {
                        this.b.setVisibility(0);
                        this.c.setText(a.this.H(abstractC5838l));
                    }
                }
                if (abstractC5838l instanceof C0730k) {
                    if (((C0730k) abstractC5838l).p1()) {
                        this.h.setText(R.string.label_original);
                        this.h.setVisibility(0);
                    } else {
                        this.h.setVisibility(8);
                    }
                }
                if (a.this.m0) {
                    y(u(abstractC5838l));
                }
                i2 = a.this.i0;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (ax.p338y1.f.j0(a.this.Z.y())) {
                            this.f.setText(abstractC5838l.F());
                        } else {
                            this.f.setText(abstractC5838l.H());
                        }
                        if (ax.p338y1.f.y0(abstractC5838l.P())) {
                            this.g.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                        } else {
                            if (C0740v.H(abstractC5838l)) {
                                if (this.C == null) {
                                    uVar = (u) abstractC5838l;
                                    if (uVar.L0()) {
                                        this.g.setText(uVar.x0());
                                    } else {
                                        this.g.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                                        z4 = true;
                                    }
                                }
                                if (!a.this.S()) {
                                    if (this.l.getTag() != null || !this.l.getTag().equals(abstractC5838l.Q())) {
                                        if (z || ax.p338y1.f.j0(a.this.Z.y())) {
                                            if (a.this.A0) {
                                                a.this.M().u(abstractC5838l, this.l, this.G);
                                            } else {
                                                a.this.M().x(abstractC5838l, this.l, this.G);
                                            }
                                        } else if (a.this.Z.y() == ax.p338y1.f.l1 && C0720a.M1(abstractC5838l)) {
                                            a.this.M().u(abstractC5838l, this.l, this.G);
                                        }
                                    }
                                    if (this.l.getTag() == null && z && !C0740v.H(abstractC5838l)) {
                                        this.l.setImageDrawable(a.this.I(abstractC5838l, true, this.z));
                                    }
                                    aVar = this.D;
                                    if (aVar != null && (strArr = aVar.a) != null && this.n != null) {
                                        for (String str : strArr) {
                                            F(str);
                                        }
                                        this.D = null;
                                    }
                                }
                                if (!z3 || z4) {
                                    c cVar = new c(this, a.this.Z.y(), (u) abstractC5838l, a.this.q0, z4, z3);
                                    this.C = cVar;
                                    cVar.i(new Void[0]);
                                }
                                return;
                            }
                            this.g.setText(abstractC5838l.U());
                        }
                    } else if (i2 != 3) {
                        this.f.setText(abstractC5838l.F());
                        y(u(abstractC5838l));
                    } else if (i2 != 10) {
                        if (i2 == 20) {
                            this.f.setVisibility(8);
                        }
                    } else if (z) {
                        this.i.setVisibility(0);
                    } else {
                        this.i.setVisibility(4);
                    }
                } else if (a.this.k0) {
                    this.f.setText(String.format("%.2f%%", Double.valueOf((this.B * 100.0d) / a.this.n0)));
                } else {
                    this.f.setText(abstractC5838l.F());
                }
                z4 = false;
                if (!a.this.S()) {
                    if (this.l.getTag() != null) {
                        if (z) {
                            if (a.this.A0) {
                                a.this.M().u(abstractC5838l, this.l, this.G);
                            } else {
                                a.this.M().x(abstractC5838l, this.l, this.G);
                            }
                        } else if (a.this.A0) {
                            a.this.M().u(abstractC5838l, this.l, this.G);
                        } else {
                            a.this.M().x(abstractC5838l, this.l, this.G);
                        }
                    } else if (z) {
                        if (a.this.A0) {
                            a.this.M().u(abstractC5838l, this.l, this.G);
                        } else {
                            a.this.M().x(abstractC5838l, this.l, this.G);
                        }
                    } else if (a.this.A0) {
                        a.this.M().u(abstractC5838l, this.l, this.G);
                    } else {
                        a.this.M().x(abstractC5838l, this.l, this.G);
                    }
                    if (this.l.getTag() == null) {
                        this.l.setImageDrawable(a.this.I(abstractC5838l, true, this.z));
                    }
                    aVar = this.D;
                    if (aVar != null) {
                        while (i3 < r15) {
                            F(str);
                        }
                        this.D = null;
                    }
                }
                if (z3) {
                }
                c cVar2 = new c(this, a.this.Z.y(), (u) abstractC5838l, a.this.q0, z4, z3);
                this.C = cVar2;
                cVar2.i(new Void[0]);
            }
            TextView textView2 = this.i;
            if (textView2 != null) {
                textView2.setText(C0740v.f(a.this.getContext(), this.B));
            }
            z3 = false;
            this.e.setText(v(abstractC5838l));
            if (a.this.t0) {
                if (i != 0) {
                    aVar2 = a.this;
                    if (aVar2.R(abstractC5838l, (AbstractC5838l) aVar2.getItem(i - 1))) {
                        this.b.setVisibility(0);
                        this.c.setText(a.this.H(abstractC5838l));
                    } else {
                        this.b.setVisibility(8);
                    }
                } else {
                    this.b.setVisibility(0);
                    this.c.setText(a.this.H(abstractC5838l));
                }
            }
            if (abstractC5838l instanceof C0730k) {
                if (((C0730k) abstractC5838l).p1()) {
                    this.h.setText(R.string.label_original);
                    this.h.setVisibility(0);
                } else {
                    this.h.setVisibility(8);
                }
            }
            if (a.this.m0) {
                y(u(abstractC5838l));
            }
            i2 = a.this.i0;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (ax.p338y1.f.j0(a.this.Z.y())) {
                        this.f.setText(abstractC5838l.F());
                    } else {
                        this.f.setText(abstractC5838l.H());
                    }
                    if (ax.p338y1.f.y0(abstractC5838l.P())) {
                        this.g.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                    } else {
                        if (C0740v.H(abstractC5838l)) {
                            if (this.C == null) {
                                uVar = (u) abstractC5838l;
                                if (uVar.L0()) {
                                    this.g.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                                    z4 = true;
                                } else {
                                    this.g.setText(uVar.x0());
                                }
                            }
                            if (!a.this.S()) {
                                if (this.l.getTag() != null) {
                                    if (z) {
                                        if (a.this.A0) {
                                            a.this.M().u(abstractC5838l, this.l, this.G);
                                        } else {
                                            a.this.M().x(abstractC5838l, this.l, this.G);
                                        }
                                    } else if (a.this.A0) {
                                        a.this.M().u(abstractC5838l, this.l, this.G);
                                    } else {
                                        a.this.M().x(abstractC5838l, this.l, this.G);
                                    }
                                } else if (z) {
                                    if (a.this.A0) {
                                        a.this.M().u(abstractC5838l, this.l, this.G);
                                    } else {
                                        a.this.M().x(abstractC5838l, this.l, this.G);
                                    }
                                } else if (a.this.A0) {
                                    a.this.M().u(abstractC5838l, this.l, this.G);
                                } else {
                                    a.this.M().x(abstractC5838l, this.l, this.G);
                                }
                                if (this.l.getTag() == null) {
                                    this.l.setImageDrawable(a.this.I(abstractC5838l, true, this.z));
                                }
                                aVar = this.D;
                                if (aVar != null) {
                                    while (i3 < r15) {
                                        F(str);
                                    }
                                    this.D = null;
                                }
                            }
                            if (z3) {
                            }
                            c cVar3 = new c(this, a.this.Z.y(), (u) abstractC5838l, a.this.q0, z4, z3);
                            this.C = cVar3;
                            cVar3.i(new Void[0]);
                        }
                        this.g.setText(abstractC5838l.U());
                    }
                } else if (i2 != 3) {
                    this.f.setText(abstractC5838l.F());
                    y(u(abstractC5838l));
                } else if (i2 != 10) {
                    if (i2 == 20) {
                        this.f.setVisibility(8);
                    }
                } else if (z) {
                    this.i.setVisibility(0);
                } else {
                    this.i.setVisibility(4);
                }
            } else if (a.this.k0) {
                this.f.setText(String.format("%.2f%%", Double.valueOf((this.B * 100.0d) / a.this.n0)));
            } else {
                this.f.setText(abstractC5838l.F());
            }
            z4 = false;
            if (!a.this.S()) {
                if (this.l.getTag() != null) {
                    if (z) {
                        if (a.this.A0) {
                            a.this.M().u(abstractC5838l, this.l, this.G);
                        } else {
                            a.this.M().x(abstractC5838l, this.l, this.G);
                        }
                    } else if (a.this.A0) {
                        a.this.M().u(abstractC5838l, this.l, this.G);
                    } else {
                        a.this.M().x(abstractC5838l, this.l, this.G);
                    }
                } else if (z) {
                    if (a.this.A0) {
                        a.this.M().u(abstractC5838l, this.l, this.G);
                    } else {
                        a.this.M().x(abstractC5838l, this.l, this.G);
                    }
                } else if (a.this.A0) {
                    a.this.M().u(abstractC5838l, this.l, this.G);
                } else {
                    a.this.M().x(abstractC5838l, this.l, this.G);
                }
                if (this.l.getTag() == null) {
                    this.l.setImageDrawable(a.this.I(abstractC5838l, true, this.z));
                }
                aVar = this.D;
                if (aVar != null) {
                    while (i3 < r15) {
                        F(str);
                    }
                    this.D = null;
                }
            }
            if (z3) {
            }
            c cVar4 = new c(this, a.this.Z.y(), (u) abstractC5838l, a.this.q0, z4, z3);
            this.C = cVar4;
            cVar4.i(new Void[0]);
        }

        private String t(AbstractC5838l abstractC5838l) {
            if (a.this.i0 != 20 || !abstractC5838l.isDirectory()) {
                if (a.this.i0 == 12 && abstractC5838l.isDirectory()) {
                    return this.z >= 0 ? a.this.getContext().getString(R.string.media_thumbnail_name, abstractC5838l.w(), Integer.valueOf(this.z)) : abstractC5838l.w();
                }
                return abstractC5838l.w();
            }
            String strU = V.u(h.o(abstractC5838l.L()), abstractC5838l.C(), Boolean.TRUE);
            if (!ax.B1.d.P(strU)) {
                return abstractC5838l.w();
            }
            String strH = ax.B1.d.h(strU);
            ax.B1.f fVarX = ax.B1.d.F(a.this.getContext()).x(strH);
            return fVarX == null ? strH : fVarX.m();
        }

        private CharSequence v(AbstractC5838l abstractC5838l) {
            int iIndexOf;
            String strT = t(abstractC5838l);
            if (a.this.x0 == null || strT == null || (iIndexOf = strT.toLowerCase().indexOf(a.this.x0.toLowerCase())) < 0) {
                return strT;
            }
            if (a.this.y0 == 0) {
                a aVar = a.this;
                aVar.y0 = ax.P.b.c(aVar.getContext(), R.color.search_highlight_color);
            }
            SpannableString spannableString = new SpannableString(strT);
            spannableString.setSpan(new BackgroundColorSpan(a.this.y0), iIndexOf, a.this.x0.length() + iIndexOf, 0);
            return spannableString;
        }

        /* JADX WARN: Code duplicated, block: B:47:0x0180  */
        private void w(View view, ViewGroup viewGroup) {
            if (a.this.t0) {
                this.b = view.findViewById(R.id.header_label_layout);
                this.c = (TextView) view.findViewById(R.id.header_label);
            }
            this.j = view;
            this.q = view.findViewById(R.id.selected_description);
            this.e = (TextView) view.findViewById(R.id.filename);
            this.k = view.findViewById(R.id.icon_container);
            this.l = (ImageView) view.findViewById(R.id.icon);
            this.m = (ImageView) view.findViewById(R.id.mask);
            this.n = (ImageView) view.findViewById(R.id.folder_icon);
            this.o = (ImageView) view.findViewById(R.id.shortcut);
            this.p = (ImageView) view.findViewById(R.id.subicon);
            View view2 = this.j;
            if (view2 instanceof ActivatableLinearLayout) {
                ((ActivatableLinearLayout) view2).setOnActivatedListener(new C0519a());
            }
            if (a.this.p0 != null) {
                if (a.this.s0 && (a.this.i0 == 0 || a.this.i0 == 20)) {
                    ViewStub viewStub = (ViewStub) view.findViewById(R.id.stub_checkbox);
                    if (viewStub != null) {
                        View viewInflate = viewStub.inflate();
                        viewInflate.setOnClickListener(new C0520b());
                        viewInflate.setOnLongClickListener(new c(viewGroup));
                    }
                    View viewFindViewById = view.findViewById(R.id.end_padding);
                    if (viewFindViewById != null) {
                        viewFindViewById.setVisibility(8);
                    }
                }
                if (k.H() && a.this.i0 != 10) {
                    View view3 = this.k;
                    if (view3 == null) {
                        view3 = this.l;
                    }
                    view3.setContentDescription(a.this.X.getString(R.string.button_toggle_selection));
                    view3.setOnClickListener(new d());
                    view3.setOnLongClickListener(new e(viewGroup));
                }
            }
            int i = a.this.i0;
            if (i == 0 || i == 1) {
                this.a = view.findViewById(R.id.background_layer);
                this.f = (TextView) view.findViewById(R.id.file_date);
                this.g = (TextView) view.findViewById(R.id.file_details);
                this.h = (TextView) view.findViewById(R.id.file_label);
                this.i = (TextView) view.findViewById(R.id.file_size);
                this.d = (TextView) view.findViewById(R.id.file_path);
            } else if (i == 3) {
                this.a = view.findViewById(R.id.background_layer);
                this.f = (TextView) view.findViewById(R.id.file_date);
                this.g = (TextView) view.findViewById(R.id.file_details);
                this.i = (TextView) view.findViewById(R.id.file_size);
                this.d = (TextView) view.findViewById(R.id.file_path);
            } else if (i == 10) {
                this.a = view.findViewById(R.id.background_layer);
                this.i = (TextView) view.findViewById(R.id.file_size);
            } else if (i == 12) {
                this.e.setMinLines(1);
                this.e.setMaxLines(1);
            } else if (i == 16) {
                this.e.setMinLines(2);
                this.e.setMaxLines(2);
            } else if (i == 20) {
                this.a = view.findViewById(R.id.background_layer);
                this.f = (TextView) view.findViewById(R.id.file_date);
                this.g = (TextView) view.findViewById(R.id.file_details);
                this.h = (TextView) view.findViewById(R.id.file_label);
                this.i = (TextView) view.findViewById(R.id.file_size);
                this.d = (TextView) view.findViewById(R.id.file_path);
            }
            if (this.d != null) {
                if (a.this.m0) {
                    this.d.setVisibility(0);
                } else {
                    this.d.setVisibility(8);
                }
            }
            if (this.g != null) {
                if (a.this.i0 == 1) {
                    this.g.setVisibility(0);
                } else {
                    this.g.setVisibility(8);
                }
            }
        }

        private boolean x() {
            return this.E == 4;
        }

        private void y(String str) {
            TextView textView = this.d;
            if (textView != null) {
                textView.setText(str);
            }
        }

        private void z(ImageView imageView, boolean z) {
            imageView.setAlpha(z ? 0.5f : 1.0f);
        }

        public void l() {
            this.t = null;
        }

        public void q(AbstractC5838l abstractC5838l, int i) {
            this.A = i;
            if (abstractC5838l == null) {
                return;
            }
            k();
            boolean zIsDirectory = abstractC5838l.isDirectory();
            long jQ = abstractC5838l.q();
            boolean zG = abstractC5838l.g();
            String str = this.t;
            if (str == null || !str.equals(abstractC5838l.Q()) || this.s != zIsDirectory || this.u != jQ || this.y != zG) {
                p(abstractC5838l, zIsDirectory, jQ, zG);
            }
            r(abstractC5838l, i, zIsDirectory);
        }

        public String s() {
            return this.t;
        }

        String u(AbstractC5838l abstractC5838l) {
            String strS = V.s(abstractC5838l);
            if ("/".equals(strS)) {
                return "/";
            }
            return strS + "/";
        }
    }

    static class c extends n<Void, Void, Void> {
        b h;
        u i;
        f j;
        boolean k;
        String l;
        boolean m;
        boolean n;

        c(b bVar, f fVar, u uVar, boolean z, boolean z2, boolean z3) {
            super(n.f.HIGH);
            this.h = bVar;
            this.j = fVar;
            this.i = uVar;
            this.k = z;
            this.m = z2;
            this.n = z3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            if (this.m) {
                this.l = this.i.x0();
            }
            if (!this.n) {
                return null;
            }
            if (this.i.isDirectory()) {
                this.i.W0(this.k);
                return null;
            }
            this.i.o();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Void r2) {
            if (this.i.Q().equals(this.h.s())) {
                if (this.m) {
                    this.h.g.setText(this.l);
                }
                if (this.n) {
                    this.h.n(this.i);
                    this.h.o(this.i);
                }
            }
        }
    }

    public interface d {
        void a(int i);

        void b(int i);
    }

    public a(Context context, List<AbstractC5838l> list, C5839m c5839m, ax.p003a2.c cVar, int i, d dVar, boolean z) {
        super(context, 0, list);
        this.q = new Object();
        this.k0 = false;
        this.l0 = false;
        this.m0 = false;
        this.r0 = false;
        this.X = context;
        this.Z = c5839m;
        this.h0 = cVar;
        this.p0 = dVar;
        this.s0 = z;
        f fVarY = c5839m.y();
        this.u0 = fVarY;
        this.v0 = f.h0(fVarY) || f.d0(this.u0) || this.u0 == f.v0;
        if (E0 == null) {
            E0 = ax.P.b.d(context, R.color.music_icon_not_playing);
            D0 = ax.P.b.d(context, R.color.music_icon_playing);
        }
        if (O()) {
            this.t0 = true;
        } else {
            this.t0 = false;
        }
        T();
        Y(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String H(AbstractC5838l abstractC5838l) {
        return abstractC5838l instanceof C0730k ? abstractC5838l.w() : abstractC5838l.G();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable I(AbstractC5838l abstractC5838l, boolean z, int i) {
        InsetDrawable insetDrawable;
        if (z) {
            if (P(this.i0)) {
                return ax.p017b2.d.h(getContext(), abstractC5838l.P());
            }
            return ax.p017b2.d.e(getContext(), abstractC5838l, i != 0, e0());
        }
        int i2 = this.i0;
        if (i2 == 2) {
            return abstractC5838l.M(getContext());
        }
        if (i2 == 10) {
            insetDrawable = new InsetDrawable(abstractC5838l.M(getContext()), x.e(getContext(), 4));
        } else {
            if (i2 != 12 && i2 != 16) {
                return abstractC5838l.X(getContext());
            }
            insetDrawable = new InsetDrawable(abstractC5838l.M(getContext()), x.e(getContext(), 8));
        }
        return insetDrawable;
    }

    private int K() {
        int i = this.i0;
        if (i != 0 && i != 1) {
            if (i == 2) {
                return R.layout.filegrid_item;
            }
            if (i == 3) {
                c0(true);
                return R.layout.searchlist_item;
            }
            if (i == 10) {
                return R.layout.file_media_list_item;
            }
            if (i == 12 || i == 16) {
                return R.layout.file_media_grid_item;
            }
            if (i != 20) {
                return 0;
            }
        }
        return this.t0 ? R.layout.filelist_item_with_section : R.layout.filelist_item;
    }

    private boolean O() {
        f fVar = this.u0;
        return fVar == f.H0 || fVar == f.m1 || fVar == f.n1;
    }

    private boolean P(int i) {
        return i == 10 || i == 12 || i == 16;
    }

    private boolean Q(Date date, Date date2) {
        return date.getYear() == date2.getYear() && date.getMonth() == date2.getMonth() && date.getDate() == date2.getDate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean R(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        if (abstractC5838l instanceof C0730k) {
            return ((C0730k) abstractC5838l).o1() == ((C0730k) abstractC5838l2).o1();
        }
        return Q(new Date(abstractC5838l.q()), new Date(abstractC5838l2.q()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e0() {
        return this.i0 == 2;
    }

    public void D() {
        this.k0 = false;
        this.l0 = false;
        this.n0 = 0L;
        this.o0 = 0L;
    }

    public int E(int i, String str) {
        if (i < 0) {
            i = 0;
        }
        synchronized (this.q) {
            for (int i2 = i; i2 < getCount(); i2++) {
                try {
                    String strW = ((AbstractC5838l) getItem(i2)).w();
                    if (strW != null && strW.toLowerCase().startsWith(str.toLowerCase())) {
                        return i2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            for (int i3 = 0; i3 < i && i3 < getCount(); i3++) {
                String strW2 = ((AbstractC5838l) getItem(i3)).w();
                if (strW2 != null && strW2.toLowerCase().startsWith(str.toLowerCase())) {
                    return i3;
                }
            }
            return -1;
        }
    }

    public ArrayList<AbstractC5838l> F() {
        ArrayList<AbstractC5838l> arrayList;
        synchronized (this.q) {
            try {
                arrayList = new ArrayList<>();
                for (int i = 0; i < getCount(); i++) {
                    arrayList.add((AbstractC5838l) getItem(i));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public ArrayList<AbstractC5838l> G() {
        ArrayList<AbstractC5838l> arrayList = new ArrayList<>();
        synchronized (this.q) {
            for (int i = 0; i < getCount(); i++) {
                try {
                    AbstractC5838l abstractC5838l = (AbstractC5838l) getItem(i);
                    if (C0739u.x(abstractC5838l, true)) {
                        arrayList.add(abstractC5838l);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList;
    }

    public int J() {
        return this.j0;
    }

    public AbstractC5838l L(AbstractC5838l abstractC5838l) {
        String strC = abstractC5838l.C();
        synchronized (this.q) {
            try {
                String strL = V.l(strC);
                for (int i = 0; i < getCount(); i++) {
                    AbstractC5838l abstractC5838l2 = (AbstractC5838l) getItem(i);
                    if (abstractC5838l2.C().startsWith(strL)) {
                        for (String str : C0739u.m()) {
                            if (abstractC5838l2.C().equals(strL + "." + str)) {
                                return abstractC5838l2;
                            }
                        }
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ax.p003a2.c M() {
        return this.h0;
    }

    public boolean N(String str) {
        synchronized (this.q) {
            for (int i = 0; i < getCount(); i++) {
                try {
                    String strC = ((AbstractC5838l) getItem(i)).C();
                    if (((AbstractC5838l) getItem(i)).P().V()) {
                        if (strC.equalsIgnoreCase(str)) {
                            return true;
                        }
                    } else if (strC.equals(str)) {
                        return true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return false;
        }
    }

    public boolean S() {
        return this.Y;
    }

    public void T() {
        this.w0 = ax.P.b.c(this.X, R.color.shape_invert);
        this.B0 = ax.P.b.c(this.X, R.color.main_primary_text);
        this.C0 = v.k(this.X);
    }

    public void U(long j, long j2) {
        this.k0 = true;
        this.l0 = true;
        this.n0 = j;
        this.o0 = j2;
    }

    public void V(String str) {
        this.x0 = str;
    }

    public void W(int i) {
        this.j0 = i;
    }

    public void X(boolean z) {
        this.q0 = z;
    }

    public void Y(int i) {
        this.i0 = i;
        if (P(i)) {
            this.z0 = x.e(getContext(), 1);
        } else {
            this.z0 = x.e(getContext(), 0);
        }
        notifyDataSetChanged();
    }

    public void Z(boolean z) {
        boolean z2 = this.Y != z;
        this.Y = z;
        if (!z2 || z) {
            return;
        }
        notifyDataSetChanged();
    }

    public void a0(F f, String str) {
        if (!f.z0(f.d())) {
            this.A0 = true;
            return;
        }
        if (!ax.R1.a.e(getContext()).m()) {
            this.A0 = false;
        } else if (ax.p031c2.f.h(this.X, f.d(), f.b(), str, false)) {
            this.A0 = true;
        } else {
            this.A0 = false;
        }
    }

    @Override // android.widget.ArrayAdapter
    public void addAll(Collection<? extends AbstractC5838l> collection) {
        synchronized (this.q) {
            super.addAll(collection);
        }
    }

    public void b0() {
        this.l0 = true;
    }

    public void c0(boolean z) {
        this.m0 = z;
    }

    @Override // android.widget.ArrayAdapter
    public void clear() {
        synchronized (this.q) {
            super.clear();
        }
    }

    public void d0(boolean z) {
        this.r0 = z;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.X).inflate(K(), viewGroup, false);
            bVar = new b(view, viewGroup);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        try {
            bVar.q((AbstractC5838l) getItem(i), i);
        } catch (IndexOutOfBoundsException e) {
            ax.p221pa.c.h().g().d("!! INDEX OUT OF BOUND !!").m(e).i();
        }
        return view;
    }
}
