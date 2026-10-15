package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import ax.C3.m;
import ax.C3.u;
import ax.U2.f;
import ax.U2.p;
import ax.p006a3.Q;
import ax.p038c3.e;
import ax.p038c3.q;
import ax.p086f3.A;
import ax.p086f3.B;
import ax.p086f3.C;
import ax.p086f3.C2008n;
import ax.p086f3.EnumC2019z;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p390y3.k;
import ax.p406z3.C2461b;
import ax.p406z3.z;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6226a;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6249y;
import com.alphainventor.filemanager.file.J;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class a extends ArrayAdapter<AbstractC6239n> {
    private static ColorStateList T0;
    private static ColorStateList U0;
    private int A0;
    private String B0;
    private int C0;
    private int D0;
    private boolean E0;
    private boolean F0;
    private int G0;
    private int H0;
    private boolean I0;
    private AdapterView.OnItemClickListener J0;
    private boolean K0;
    private TreeMap<Integer, q> L0;
    private boolean M0;
    private Date N0;
    private Date O0;
    private String P0;
    private boolean Q0;
    private int R0;
    private Set<String> S0;
    private Context X;
    private boolean Y;
    private C6240o Z;
    private ax.p358w3.d k0;
    private int l0;
    private int m0;
    private boolean n0;
    private boolean o0;
    private boolean p0;
    private final Object q;
    private long q0;
    private long r0;
    private u s0;
    private boolean t0;
    private boolean u0;
    private boolean v0;
    private boolean w0;
    private int x0;
    private f y0;
    private boolean z0;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.widget.a$a, reason: collision with other inner class name */
    class C0554a extends View.DragShadowBuilder {
        final /* synthetic */ int a;
        final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0554a(View view, int i, int i2) {
            super(view);
            this.a = i;
            this.b = i2;
        }

        @Override // android.view.View.DragShadowBuilder
        public void onDrawShadow(Canvas canvas) {
            canvas.drawColor(a.this.getContext().getColor(R.color.main_background));
            super.onDrawShadow(canvas);
        }

        @Override // android.view.View.DragShadowBuilder
        public void onProvideShadowMetrics(Point point, Point point2) {
            View view = getView();
            if (view != null) {
                point.set(view.getWidth(), view.getHeight());
                point2.set(this.a, this.b);
            }
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.Y4.c.a.values().length];
            a = iArr;
            try {
                iArr[ax.Y4.c.a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.Y4.c.a.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.Y4.c.a.PLAYING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class c {
        private String A;
        private String B;
        private boolean C;
        private boolean D;
        private boolean E;
        private int F;
        private int G;
        private long H;
        private d I;
        private C.a J;
        private int N;
        private int O;
        private View a;
        private View b;
        private TextView c;
        private CheckBox d;
        private View e;
        private TextView f;
        private TextView g;
        private TextView h;
        private TextView i;
        private TextView j;
        private TextView k;
        private View l;
        private View m;
        private ImageView n;
        private ImageView o;
        private ImageView p;
        private ImageView q;
        private ImageView r;
        private ImageView s;
        private View t;
        private View u;
        private boolean v;
        private boolean w;
        private String x;
        private long y;
        private EnumC2019z z;
        private int K = -1;
        private boolean L = false;
        private int M = 0;
        final Runnable P = new RunnableC0555a();

        /* JADX INFO: renamed from: com.alphainventor.filemanager.widget.a$c$a, reason: collision with other inner class name */
        class RunnableC0555a implements Runnable {
            RunnableC0555a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.z == EnumC2019z.PDF) {
                    c.this.L = true;
                    c.this.M = R.drawable.ic_v_pdf;
                    c.this.r.setImageResource(R.drawable.ic_v_pdf);
                    c.this.r.setVisibility(0);
                    c.this.r.setBackgroundResource(0);
                    return;
                }
                if ("epub".equals(c.this.A)) {
                    c.this.L = true;
                    c.this.M = R.drawable.ic_v_epub;
                    c.this.r.setImageResource(R.drawable.ic_v_epub);
                    c.this.r.setVisibility(0);
                    c.this.r.setBackgroundResource(0);
                    return;
                }
                if (c.this.z == EnumC2019z.VIDEO || (c.this.B != null && c.this.B.startsWith("video"))) {
                    c.this.o.setVisibility(0);
                    return;
                }
                if (c.this.z == EnumC2019z.IMAGE && A.O(c.this.A)) {
                    c.this.L = true;
                    c.this.M = R.drawable.ic_v_raw;
                    c.this.r.setImageResource(R.drawable.ic_v_raw);
                    c.this.r.setVisibility(0);
                    c.this.r.setBackgroundResource(0);
                }
            }
        }

        class b implements ActivatableFrameLayout.a {
            b() {
            }

            @Override // com.alphainventor.filemanager.widget.ActivatableFrameLayout.a
            public void a(boolean z) {
                if (c.this.t != null) {
                    if (z) {
                        c.this.t.setVisibility(0);
                    } else {
                        c.this.t.setVisibility(8);
                    }
                }
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.widget.a$c$c, reason: collision with other inner class name */
        class C0556c extends ax.p182l3.c {
            C0556c() {
            }

            @Override // ax.p182l3.c
            public void a(View view) {
                a.this.s0.a(c.this.G);
            }
        }

        class d extends ax.p182l3.c {
            d() {
            }

            @Override // ax.p182l3.c
            public void a(View view) {
                a.this.s0.b(c.this.G);
            }
        }

        class e implements View.OnLongClickListener {
            final /* synthetic */ View X;
            final /* synthetic */ ViewGroup q;

            e(ViewGroup viewGroup, View view) {
                this.q = viewGroup;
                this.X = view;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                ViewParent viewParent = this.q;
                if (viewParent instanceof m) {
                    ((m) viewParent).a();
                }
                a.this.s0.c(c.this.G, c.this.u, (int) this.X.getX(), (int) this.X.getY());
                return true;
            }
        }

        class f extends ax.p182l3.c {
            f() {
            }

            @Override // ax.p182l3.c
            public void a(View view) {
                a.this.s0.b(c.this.G);
            }
        }

        class g implements View.OnLongClickListener {
            final /* synthetic */ View X;
            final /* synthetic */ ViewGroup q;

            g(ViewGroup viewGroup, View view) {
                this.q = viewGroup;
                this.X = view;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                ViewGroup viewGroup = this.q;
                if (viewGroup instanceof FileListView) {
                    ((FileListView) viewGroup).a();
                }
                a.this.s0.c(c.this.G, c.this.u, (int) (this.X.getX() + (this.X.getWidth() / 2.0f)), (int) (this.X.getY() + (this.X.getHeight() / 2.0f)));
                return true;
            }
        }

        class h implements View.OnTouchListener {
            h() {
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == 0) {
                    c.this.N = (int) motionEvent.getX();
                    c.this.O = (int) motionEvent.getY();
                } else if (motionEvent.getActionMasked() == 1) {
                    c.this.N = 0;
                    c.this.O = 0;
                }
                return false;
            }
        }

        class i extends ax.p182l3.c {
            i() {
            }

            @Override // ax.p182l3.c
            public void a(View view) {
                if (a.this.I0) {
                    a.this.s0.b(c.this.G);
                } else {
                    a.this.J0.onItemClick(null, c.this.l, c.this.G, c.this.G);
                }
            }
        }

        class j implements View.OnLongClickListener {
            final /* synthetic */ ViewGroup q;

            j(ViewGroup viewGroup) {
                this.q = viewGroup;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                ViewParent viewParent = this.q;
                if (viewParent instanceof m) {
                    ((m) viewParent).a();
                }
                a.this.s0.c(c.this.G, c.this.u, c.this.N, c.this.O);
                return true;
            }
        }

        public c(View view, ViewGroup viewGroup) {
            E(view, viewGroup);
        }

        private String B(AbstractC6239n abstractC6239n) {
            if (a.this.l0 != 20 || !abstractC6239n.isDirectory()) {
                if (a.this.l0 == 12 && abstractC6239n.isDirectory()) {
                    return this.F >= 0 ? a.this.getContext().getString(R.string.media_thumbnail_name, abstractC6239n.B(), Integer.valueOf(this.F)) : abstractC6239n.B();
                }
                return abstractC6239n.B();
            }
            String strV = d0.v(ax.W2.h.p(abstractC6239n.M()), abstractC6239n.E(), Boolean.TRUE);
            if (!ax.X2.d.Q(strV)) {
                return abstractC6239n.B();
            }
            String strG = ax.X2.d.g(strV);
            ax.X2.f fVarX = ax.X2.d.F(a.this.getContext()).x(strG);
            return fVarX == null ? strG : fVarX.s();
        }

        private CharSequence D(AbstractC6239n abstractC6239n) {
            int iIndexOf;
            String strB = B(abstractC6239n);
            if (strB == null) {
                return null;
            }
            String str = (a.this.P0 == null || !a.this.P0.equals(abstractC6239n.B())) ? a.this.B0 : strB;
            if (str == null || (iIndexOf = strB.toLowerCase().indexOf(str.toLowerCase())) < 0) {
                return strB;
            }
            if (a.this.C0 == 0) {
                a aVar = a.this;
                aVar.C0 = ax.Q.b.c(aVar.getContext(), R.color.search_highlight_color);
            }
            SpannableString spannableString = new SpannableString(strB);
            spannableString.setSpan(new BackgroundColorSpan(a.this.C0), iIndexOf, str.length() + iIndexOf, 0);
            return spannableString;
        }

        /* JADX WARN: Code duplicated, block: B:64:0x0208  */
        private void E(View view, ViewGroup viewGroup) {
            CheckBox checkBox;
            View viewFindViewById = view.findViewById(R.id.header_label_layout);
            this.b = viewFindViewById;
            if (viewFindViewById != null) {
                this.c = (TextView) view.findViewById(R.id.header_label);
                this.d = (CheckBox) view.findViewById(R.id.header_checkbox);
            }
            this.l = view;
            this.u = view.findViewById(R.id.item_container);
            this.t = view.findViewById(R.id.selected_description);
            this.e = view.findViewById(R.id.created_file_indicator);
            this.g = (TextView) view.findViewById(R.id.filename);
            this.m = view.findViewById(R.id.icon_container);
            this.n = (ImageView) view.findViewById(R.id.icon);
            this.o = (ImageView) view.findViewById(R.id.mask);
            this.p = (ImageView) view.findViewById(R.id.folder_icon);
            this.q = (ImageView) view.findViewById(R.id.shortcut);
            this.r = (ImageView) view.findViewById(R.id.subicon);
            this.s = (ImageView) view.findViewById(R.id.localcopy_icon);
            View view2 = this.l;
            if (view2 instanceof ActivatableFrameLayout) {
                ((ActivatableFrameLayout) view2).setOnActivatedListener(new b());
            }
            if (a.this.s0 != null) {
                boolean z = a.this.v0 && (a.this.l0 == 0 || a.this.l0 == 20);
                if (this.b != null && (checkBox = this.d) != null) {
                    checkBox.setOnClickListener(new C0556c());
                }
                if (z) {
                    ViewStub viewStub = (ViewStub) view.findViewById(R.id.stub_checkbox);
                    if (viewStub != null) {
                        View viewInflate = viewStub.inflate();
                        viewInflate.setOnClickListener(new d());
                        viewInflate.setOnLongClickListener(new e(viewGroup, viewInflate));
                        if (p.a(a.this.X)) {
                            viewInflate.setImportantForAccessibility(2);
                        }
                    }
                    View viewFindViewById2 = view.findViewById(R.id.end_padding);
                    if (viewFindViewById2 != null) {
                        viewFindViewById2.setVisibility(8);
                    }
                }
                if (a.this.K0 && ax.p390y3.m.H() && a.this.l0 != 10) {
                    View view3 = this.m;
                    if (view3 == null) {
                        view3 = this.n;
                    }
                    view3.setContentDescription(a.this.X.getString(R.string.button_toggle_selection));
                    view3.setOnClickListener(new f());
                    view3.setOnLongClickListener(new g(viewGroup, view3));
                    if (p.a(a.this.X)) {
                        view3.setImportantForAccessibility(2);
                    }
                }
                if (!p.a(a.this.X)) {
                    this.u.setOnTouchListener(new h());
                    this.u.setOnClickListener(new i());
                    this.u.setOnLongClickListener(new j(viewGroup));
                }
            }
            int i2 = a.this.l0;
            if (i2 == 0 || i2 == 1) {
                this.a = view.findViewById(R.id.background_layer);
                this.h = (TextView) view.findViewById(R.id.file_date);
                this.i = (TextView) view.findViewById(R.id.file_details);
                this.j = (TextView) view.findViewById(R.id.file_label);
                this.k = (TextView) view.findViewById(R.id.file_size);
                this.f = (TextView) view.findViewById(R.id.file_path);
            } else if (i2 == 3) {
                this.a = view.findViewById(R.id.background_layer);
                this.h = (TextView) view.findViewById(R.id.file_date);
                this.i = (TextView) view.findViewById(R.id.file_details);
                this.k = (TextView) view.findViewById(R.id.file_size);
                this.f = (TextView) view.findViewById(R.id.file_path);
            } else if (i2 == 10) {
                this.a = view.findViewById(R.id.background_layer);
                this.k = (TextView) view.findViewById(R.id.file_size);
            } else if (i2 == 12) {
                this.g.setMinLines(1);
                this.g.setMaxLines(1);
            } else if (i2 == 16) {
                this.g.setMinLines(2);
                this.g.setMaxLines(2);
            } else if (i2 == 20) {
                this.a = view.findViewById(R.id.background_layer);
                this.h = (TextView) view.findViewById(R.id.file_date);
                this.i = (TextView) view.findViewById(R.id.file_details);
                this.j = (TextView) view.findViewById(R.id.file_label);
                this.k = (TextView) view.findViewById(R.id.file_size);
                this.f = (TextView) view.findViewById(R.id.file_path);
            }
            if (this.f != null) {
                if (a.this.p0) {
                    this.f.setVisibility(0);
                } else {
                    this.f.setVisibility(8);
                }
            }
            if (this.i != null) {
                if (a.this.l0 == 1) {
                    this.i.setVisibility(0);
                } else {
                    this.i.setVisibility(8);
                }
            }
        }

        private boolean F() {
            return this.K == 4;
        }

        private void G(String str) {
            TextView textView = this.f;
            if (textView != null) {
                textView.setText(str);
            }
        }

        private void H(ImageView imageView, boolean z) {
            imageView.setAlpha(z ? 0.5f : 1.0f);
        }

        private void I(Resources resources, View view, int i2, int i3) {
            int dimensionPixelSize = F() ? resources.getDimensionPixelSize(i3) : resources.getDimensionPixelSize(i2);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = dimensionPixelSize;
            layoutParams.width = dimensionPixelSize;
            view.setLayoutParams(layoutParams);
        }

        private void J(Resources resources, View view, int i2, int i3) {
            int dimensionPixelSize = F() ? resources.getDimensionPixelSize(i3) : resources.getDimensionPixelSize(i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.topMargin = dimensionPixelSize;
            view.setLayoutParams(marginLayoutParams);
        }

        private void K(AbstractC6239n abstractC6239n, boolean z) {
            C.a aVarC = z ? C.c(abstractC6239n) : C.b(abstractC6239n);
            if (aVarC == null) {
                if (z) {
                    O(R.drawable.v_shape_folder_dir);
                    return;
                }
                return;
            }
            int i2 = aVarC.b;
            if (i2 != 0) {
                if (z) {
                    O(i2);
                    return;
                } else {
                    M(i2);
                    return;
                }
            }
            String[] strArr = aVarC.a;
            if (strArr != null) {
                this.J = aVarC;
                for (String str : strArr) {
                    if (P(str, z)) {
                        this.J = null;
                        return;
                    }
                }
            }
        }

        private void L(AbstractC6239n abstractC6239n, boolean z, ax.Y4.c.a aVar) {
            boolean z2;
            if (a.this.u0 && ((C6249y) abstractC6239n).Z0()) {
                this.r.setImageResource(R.drawable.ic_sd_card);
                this.r.setVisibility(0);
                z2 = true;
            } else {
                this.r.setVisibility(8);
                z2 = false;
            }
            if (this.L) {
                this.r.setVisibility(0);
                this.r.setImageResource(this.M);
                this.r.setBackgroundResource(0);
            } else {
                this.r.setBackgroundResource(R.drawable.bg_subicon);
            }
            if (aVar != null) {
                int i2 = b.a[aVar.ordinal()];
                if (i2 == 1) {
                    if (z2) {
                        return;
                    }
                    this.r.setVisibility(8);
                } else {
                    if (i2 == 2) {
                        Drawable drawableR = ax.U.a.r(ax.p374x3.a.c(a.this.getContext(), R.drawable.ic_equalizer1_white_36dp));
                        ax.U.a.o(drawableR, a.U0);
                        this.r.setVisibility(0);
                        this.r.setImageDrawable(drawableR);
                        return;
                    }
                    if (i2 != 3) {
                        return;
                    }
                    AnimationDrawable animationDrawable = (AnimationDrawable) ax.p374x3.a.c(a.this.getContext(), R.drawable.ic_equalizer_white_36dp);
                    Drawable drawableR2 = ax.U.a.r(animationDrawable);
                    ax.U.a.o(drawableR2, a.T0);
                    animationDrawable.start();
                    this.r.setVisibility(0);
                    this.r.setImageDrawable(drawableR2);
                }
            }
        }

        private void M(int i2) {
            this.p.setImageResource(i2);
            if (a.this.C0()) {
                this.p.setBackgroundResource(R.drawable.bg_folder_type_shape);
            } else {
                this.p.setBackgroundResource(R.drawable.bg_folder_type_s_shape);
            }
            this.p.setVisibility(0);
            this.v = true;
        }

        private void N(String str) {
            ax.X2.f fVarX = ax.X2.d.F(a.this.X).x(str);
            if (fVarX != null) {
                this.p.setImageDrawable(null);
                a.this.W().s(fVarX, this.p);
                this.p.setVisibility(0);
                this.p.setBackgroundDrawable(null);
                this.p.setPadding(a.this.D0, a.this.D0, a.this.D0, a.this.D0);
                this.v = true;
            }
        }

        private void O(int i2) {
            this.p.setImageResource(i2);
            this.p.setBackgroundResource(R.drawable.bg_subicon);
            this.p.setVisibility(0);
            this.v = true;
        }

        private boolean P(String str, boolean z) {
            ax.X2.f fVarX = ax.X2.d.F(a.this.X).x(str);
            if (fVarX == null) {
                if (!z) {
                    return false;
                }
                O(R.drawable.v_shape_folder_dir);
                return true;
            }
            if (!a.this.W().y(fVarX, this.p)) {
                return false;
            }
            this.p.setBackgroundDrawable(null);
            this.p.setPadding(a.this.D0, a.this.D0, a.this.D0, a.this.D0);
            this.p.setVisibility(0);
            this.v = true;
            return true;
        }

        private void s() {
            int dimensionPixelSize;
            float dimension;
            View view;
            if (this.K != a.this.m0) {
                Resources resources = this.l.getResources();
                this.K = a.this.m0;
                if (a.this.l0 == 2) {
                    ImageView imageView = this.p;
                    if (imageView != null) {
                        I(resources, imageView, R.dimen.file_grid_folder_icon_size_medium, R.dimen.file_grid_folder_icon_size_large);
                        J(resources, this.p, R.dimen.file_grid_folder_icon_margintop_medium, R.dimen.file_grid_folder_icon_margintop_large);
                        return;
                    }
                    return;
                }
                if (a.this.l0 != 1 && a.this.l0 != 0) {
                    if (a.this.l0 != 10 || (view = this.m) == null) {
                        return;
                    }
                    I(resources, view, R.dimen.file_media_list_icon_size_medium, R.dimen.file_media_list_icon_size_large);
                    return;
                }
                View view2 = this.m;
                if (view2 != null) {
                    I(resources, view2, R.dimen.file_list_icon_size_medium, R.dimen.file_list_icon_size_large);
                    if (F()) {
                        dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.file_list_min_height_large);
                        dimension = resources.getDimension(R.dimen.file_list_filename_size_large);
                    } else {
                        dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.file_list_min_height_medium);
                        dimension = resources.getDimension(R.dimen.file_list_filename_size_normal);
                    }
                    this.u.setMinimumHeight(dimensionPixelSize);
                    this.g.setTextSize(0, dimension);
                }
                ImageView imageView2 = this.p;
                if (imageView2 != null) {
                    I(resources, imageView2, R.dimen.file_list_folder_icon_size_medium, R.dimen.file_list_folder_icon_size_large);
                    J(resources, this.p, R.dimen.file_list_folder_icon_margintop_medium, R.dimen.file_list_folder_icon_margintop_large);
                }
            }
        }

        private void u(long j2) {
            View view = this.a;
            if (view != null) {
                LayerDrawable layerDrawable = (LayerDrawable) view.getBackground().mutate();
                if (!a.this.n0) {
                    layerDrawable.getDrawable(0).setLevel(0);
                    layerDrawable.getDrawable(1).setLevel(0);
                    return;
                }
                if (a.this.r0 > 0) {
                    layerDrawable.getDrawable(0).mutate().setLevel((int) ((j2 * 10000) / a.this.r0));
                } else {
                    layerDrawable.getDrawable(0).setLevel(0);
                }
                if (a.this.q0 > 0) {
                    layerDrawable.getDrawable(1).setLevel((int) ((j2 * 10000) / a.this.q0));
                } else {
                    layerDrawable.getDrawable(1).setLevel(0);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void v(AbstractC6239n abstractC6239n) {
            if (abstractC6239n.isDirectory()) {
                this.F = abstractC6239n.s(a.this.t0);
                if (this.n.getTag() == null) {
                    if (this.F != 0 || !B.P(abstractC6239n)) {
                        this.n.setImageDrawable(a.this.R(abstractC6239n, true, this.F));
                    } else if (((C6249y) abstractC6239n).T0()) {
                        this.n.setImageDrawable(a.this.R(abstractC6239n, true, 1));
                    } else {
                        this.n.setImageDrawable(a.this.R(abstractC6239n, true, this.F));
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void w(AbstractC6239n abstractC6239n) {
            if (a.this.l0 == 12) {
                if (abstractC6239n.isDirectory()) {
                    this.g.setText(D(abstractC6239n));
                    return;
                }
                return;
            }
            if (this.k != null) {
                if (!abstractC6239n.isDirectory()) {
                    this.k.setText(abstractC6239n.J(a.this.t0));
                    return;
                }
                if (a.this.l0 == 10) {
                    if (this.F < 0) {
                        this.k.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                        return;
                    }
                    this.k.setText(new SpannableString("(" + this.F + ")"));
                    return;
                }
                if (!a.this.o0) {
                    this.k.setText(abstractC6239n.J(a.this.t0));
                    return;
                }
                this.k.setText(abstractC6239n.J(a.this.t0) + String.format(" (%s)", B.i(a.this.getContext(), this.H)));
            }
        }

        private void x(AbstractC6239n abstractC6239n, boolean z, long j2, boolean z2) {
            boolean z3;
            ImageView imageView;
            this.w = z;
            this.y = j2;
            this.z = abstractC6239n.G();
            this.A = d0.j(abstractC6239n.B());
            this.B = abstractC6239n.t();
            this.x = abstractC6239n.R();
            this.C = A.q(abstractC6239n);
            this.D = z2;
            this.o.setVisibility(8);
            this.J = null;
            if (this.v && (imageView = this.p) != null) {
                imageView.setVisibility(8);
                a.this.W().g(this.p);
                this.v = false;
            }
            a.this.W().g(this.n);
            d dVar = this.I;
            if (dVar != null) {
                dVar.e();
                this.I = null;
            }
            this.n.setTag(null);
            this.H = 0L;
            this.L = false;
            TextView textView = this.g;
            if (textView instanceof GridFilenameTextView) {
                if (z) {
                    ((GridFilenameTextView) textView).setUseFilenameEllipsize(false);
                } else {
                    ((GridFilenameTextView) textView).setUseFilenameEllipsize(false);
                }
            }
            if (!z) {
                z3 = a.this.W().z(abstractC6239n, this.n, this.P);
            } else if (ax.U2.f.k0(a.this.y0)) {
                z3 = a.this.W().z(abstractC6239n, this.n, this.P);
                if (this.p != null && a.this.z0) {
                    K(abstractC6239n, true);
                }
            } else {
                if (a.this.y0 == ax.U2.f.q1) {
                    if (C6226a.V1(abstractC6239n)) {
                        z3 = a.this.W().z(abstractC6239n, this.n, this.P);
                    }
                } else if (this.p != null && a.this.z0) {
                    K(abstractC6239n, false);
                }
                z3 = false;
            }
            this.F = -1;
            if (!z3) {
                this.n.setImageDrawable(a.this.R(abstractC6239n, z, -1));
            }
            if (abstractC6239n.m()) {
                this.q.setVisibility(0);
            } else {
                this.q.setVisibility(4);
            }
            if (this.g != null) {
                if (a.this.w0()) {
                    this.g.setVisibility(0);
                    this.E = true;
                } else {
                    this.g.setVisibility(8);
                    this.E = false;
                }
            }
            H(this.n, abstractC6239n.g());
        }

        /* JADX WARN: Code duplicated, block: B:100:0x01d8  */
        /* JADX WARN: Code duplicated, block: B:102:0x01db  */
        /* JADX WARN: Code duplicated, block: B:104:0x01df  */
        /* JADX WARN: Code duplicated, block: B:107:0x01e5  */
        /* JADX WARN: Code duplicated, block: B:108:0x01ec A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:109:0x01ee  */
        /* JADX WARN: Code duplicated, block: B:110:0x01f5  */
        /* JADX WARN: Code duplicated, block: B:111:0x01fd  */
        /* JADX WARN: Code duplicated, block: B:112:0x0215  */
        /* JADX WARN: Code duplicated, block: B:114:0x0225  */
        /* JADX WARN: Code duplicated, block: B:119:0x0244  */
        /* JADX WARN: Code duplicated, block: B:120:0x0254  */
        /* JADX WARN: Code duplicated, block: B:123:0x026f  */
        /* JADX WARN: Code duplicated, block: B:125:0x0275  */
        /* JADX WARN: Code duplicated, block: B:127:0x0279  */
        /* JADX WARN: Code duplicated, block: B:129:0x0282  */
        /* JADX WARN: Code duplicated, block: B:130:0x028a  */
        /* JADX WARN: Code duplicated, block: B:131:0x0294  */
        /* JADX WARN: Code duplicated, block: B:132:0x029e  */
        /* JADX WARN: Code duplicated, block: B:133:0x02a4  */
        /* JADX WARN: Code duplicated, block: B:135:0x02ac  */
        /* JADX WARN: Code duplicated, block: B:136:0x02cf  */
        /* JADX WARN: Code duplicated, block: B:141:0x02ee  */
        /* JADX WARN: Code duplicated, block: B:145:0x0306  */
        /* JADX WARN: Code duplicated, block: B:147:0x030e  */
        /* JADX WARN: Code duplicated, block: B:149:0x031e A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:150:0x0320  */
        /* JADX WARN: Code duplicated, block: B:158:0x0353  */
        /* JADX WARN: Code duplicated, block: B:160:0x035b  */
        /* JADX WARN: Code duplicated, block: B:161:0x0369  */
        /* JADX WARN: Code duplicated, block: B:164:0x037e A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:170:0x0397  */
        /* JADX WARN: Code duplicated, block: B:176:0x03a3 A[LOOP:0: B:175:0x03a1->B:176:0x03a3, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:179:0x03ae A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:60:0x0118  */
        /* JADX WARN: Code duplicated, block: B:62:0x0120  */
        /* JADX WARN: Code duplicated, block: B:63:0x0126  */
        /* JADX WARN: Code duplicated, block: B:66:0x012f  */
        /* JADX WARN: Code duplicated, block: B:68:0x0137  */
        /* JADX WARN: Code duplicated, block: B:73:0x015e  */
        /* JADX WARN: Code duplicated, block: B:80:0x0179  */
        /* JADX WARN: Code duplicated, block: B:81:0x017f  */
        /* JADX WARN: Code duplicated, block: B:87:0x019a  */
        /* JADX WARN: Code duplicated, block: B:90:0x01a3  */
        /* JADX WARN: Code duplicated, block: B:92:0x01ac  */
        /* JADX WARN: Code duplicated, block: B:93:0x01ba  */
        /* JADX WARN: Code duplicated, block: B:96:0x01c7  */
        /* JADX WARN: Code duplicated, block: B:99:0x01d6 A[DONT_INVERT] */
        private void z(AbstractC6239n abstractC6239n, int i2, boolean z) {
            boolean z2;
            int i3;
            boolean z3;
            C.a aVar;
            String[] strArr;
            int i4;
            C6249y c6249y;
            q qVar;
            if (a.this.o0) {
                if (!z || J.h2(abstractC6239n)) {
                    this.H = abstractC6239n.p();
                } else {
                    this.H = ax.W2.h.S(abstractC6239n).D(abstractC6239n);
                }
            }
            u(this.H);
            ax.Y4.c.a aVarD = this.C ? ax.Y4.c.d(a.this.getContext(), abstractC6239n.R()) : null;
            if (this.r != null) {
                L(abstractC6239n, z, aVarD);
            }
            if (this.s != null) {
                if (z || B.P(abstractC6239n) || !B.G(abstractC6239n.O(), abstractC6239n)) {
                    this.s.setVisibility(8);
                } else {
                    this.s.setImageResource(R.drawable.ic_save_black);
                    this.s.setVisibility(0);
                }
            }
            if (aVarD == null || aVarD == ax.Y4.c.a.NONE) {
                this.g.setTextColor(a.this.G0);
            } else {
                this.g.setTextColor(a.this.H0);
            }
            if (!B.P(abstractC6239n) || ((C6249y) abstractC6239n).Y0()) {
                if (z) {
                    this.F = abstractC6239n.s(a.this.t0);
                }
                z2 = false;
            } else {
                z2 = true;
            }
            if (!a.this.o0 || abstractC6239n.Q() != ax.U2.f.q1) {
                if (a.this.o0 && J.h2(abstractC6239n)) {
                    TextView textView = this.k;
                    if (textView != null) {
                        textView.setText(B.i(a.this.getContext(), this.H));
                    }
                } else if (!z2) {
                    v(abstractC6239n);
                    w(abstractC6239n);
                }
                this.g.setText(D(abstractC6239n));
                if (this.e != null) {
                    if (a.this.b0(abstractC6239n)) {
                        this.e.setVisibility(0);
                    } else {
                        this.e.setVisibility(8);
                    }
                }
                if (this.b != null) {
                    if (a.this.w0) {
                        qVar = (q) a.this.L0.get(Integer.valueOf(i2));
                        if (qVar != null || qVar.b() == null) {
                            this.b.setVisibility(8);
                        } else {
                            this.b.setVisibility(0);
                            this.c.setText(qVar.b());
                        }
                        if (a.this.M0 || qVar == null || !qVar.e()) {
                            this.d.setChecked(false);
                        } else {
                            this.d.setChecked(true);
                        }
                    } else {
                        this.b.setVisibility(8);
                    }
                    if (a.this.I0 || !a.this.M0) {
                        this.d.setVisibility(8);
                    } else {
                        this.d.setVisibility(0);
                    }
                }
                if (abstractC6239n instanceof C2008n) {
                    if (((C2008n) abstractC6239n).C1()) {
                        this.j.setText(R.string.label_original);
                        this.j.setVisibility(0);
                    } else {
                        this.j.setVisibility(8);
                    }
                }
                if (a.this.p0) {
                    G(C(abstractC6239n));
                }
                i3 = a.this.l0;
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (ax.U2.f.k0(a.this.Z.A())) {
                            this.h.setText(abstractC6239n.I(a.this.R0));
                        } else if (a.this.Q0 || !(abstractC6239n instanceof C6249y)) {
                            this.h.setText(abstractC6239n.H(a.this.R0));
                        } else {
                            this.h.setText(((C6249y) abstractC6239n).H0(a.this.R0));
                        }
                        if (ax.U2.f.A0(abstractC6239n.Q())) {
                            this.i.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                        } else {
                            if (B.P(abstractC6239n)) {
                                if (this.I == null) {
                                    c6249y = (C6249y) abstractC6239n;
                                    if (c6249y.X0()) {
                                        this.i.setText(c6249y.F0());
                                    } else {
                                        this.i.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                                        z3 = true;
                                    }
                                }
                                if (!a.this.e0()) {
                                    if (this.n.getTag() != null || !this.n.getTag().equals(abstractC6239n.R())) {
                                        if (z || ax.U2.f.k0(a.this.Z.A())) {
                                            if (a.this.E0) {
                                                a.this.W().u(abstractC6239n, this.n, this.P);
                                            } else {
                                                a.this.W().x(abstractC6239n, this.n, this.P);
                                            }
                                        } else if (a.this.Z.A() == ax.U2.f.q1 && C6226a.V1(abstractC6239n)) {
                                            a.this.W().u(abstractC6239n, this.n, this.P);
                                        }
                                    }
                                    if (this.n.getTag() == null && z && !B.P(abstractC6239n)) {
                                        this.n.setImageDrawable(a.this.R(abstractC6239n, true, this.F));
                                    }
                                    aVar = this.J;
                                    if (aVar != null && (strArr = aVar.a) != null && this.p != null) {
                                        for (String str : strArr) {
                                            N(str);
                                        }
                                        this.J = null;
                                    }
                                }
                                if (!z2 || z3) {
                                    d dVar = new d(this, a.this.Z, a.this.Z.A(), abstractC6239n, a.this.t0, z3, z2);
                                    this.I = dVar;
                                    dVar.i(new Void[0]);
                                }
                                return;
                            }
                            this.i.setText(abstractC6239n.W());
                        }
                    } else if (i3 != 3) {
                        this.h.setText(abstractC6239n.H(a.this.R0));
                        G(C(abstractC6239n));
                    } else if (i3 != 10) {
                        if (i3 == 20) {
                            this.h.setVisibility(8);
                        }
                    } else if (z) {
                        this.k.setVisibility(0);
                    } else {
                        this.k.setVisibility(4);
                    }
                } else if (a.this.n0) {
                    this.h.setText(String.format("%.2f%%", Double.valueOf((this.H * 100.0d) / a.this.q0)));
                } else if (a.this.Q0 || !(abstractC6239n instanceof C6249y)) {
                    this.h.setText(abstractC6239n.H(a.this.R0));
                } else {
                    this.h.setText(((C6249y) abstractC6239n).H0(a.this.R0));
                }
                z3 = false;
                if (!a.this.e0()) {
                    if (this.n.getTag() != null) {
                        if (z) {
                            if (a.this.E0) {
                                a.this.W().u(abstractC6239n, this.n, this.P);
                            } else {
                                a.this.W().x(abstractC6239n, this.n, this.P);
                            }
                        } else if (a.this.E0) {
                            a.this.W().u(abstractC6239n, this.n, this.P);
                        } else {
                            a.this.W().x(abstractC6239n, this.n, this.P);
                        }
                    } else if (z) {
                        if (a.this.E0) {
                            a.this.W().u(abstractC6239n, this.n, this.P);
                        } else {
                            a.this.W().x(abstractC6239n, this.n, this.P);
                        }
                    } else if (a.this.E0) {
                        a.this.W().u(abstractC6239n, this.n, this.P);
                    } else {
                        a.this.W().x(abstractC6239n, this.n, this.P);
                    }
                    if (this.n.getTag() == null) {
                        this.n.setImageDrawable(a.this.R(abstractC6239n, true, this.F));
                    }
                    aVar = this.J;
                    if (aVar != null) {
                        while (i4 < r7) {
                            N(str);
                        }
                        this.J = null;
                    }
                }
                if (z2) {
                }
                d dVar2 = new d(this, a.this.Z, a.this.Z.A(), abstractC6239n, a.this.t0, z3, z2);
                this.I = dVar2;
                dVar2.i(new Void[0]);
            }
            TextView textView2 = this.k;
            if (textView2 != null) {
                textView2.setText(B.i(a.this.getContext(), this.H));
            }
            z2 = false;
            this.g.setText(D(abstractC6239n));
            if (this.e != null) {
                if (a.this.b0(abstractC6239n)) {
                    this.e.setVisibility(0);
                } else {
                    this.e.setVisibility(8);
                }
            }
            if (this.b != null) {
                if (a.this.w0) {
                    qVar = (q) a.this.L0.get(Integer.valueOf(i2));
                    if (qVar != null) {
                        this.b.setVisibility(8);
                    } else {
                        this.b.setVisibility(8);
                    }
                    if (a.this.M0) {
                        this.d.setChecked(false);
                    } else {
                        this.d.setChecked(false);
                    }
                } else {
                    this.b.setVisibility(8);
                }
                if (a.this.I0) {
                    this.d.setVisibility(8);
                } else {
                    this.d.setVisibility(8);
                }
            }
            if (abstractC6239n instanceof C2008n) {
                if (((C2008n) abstractC6239n).C1()) {
                    this.j.setText(R.string.label_original);
                    this.j.setVisibility(0);
                } else {
                    this.j.setVisibility(8);
                }
            }
            if (a.this.p0) {
                G(C(abstractC6239n));
            }
            i3 = a.this.l0;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (ax.U2.f.k0(a.this.Z.A())) {
                        this.h.setText(abstractC6239n.I(a.this.R0));
                    } else if (a.this.Q0) {
                        this.h.setText(abstractC6239n.H(a.this.R0));
                    } else {
                        this.h.setText(abstractC6239n.H(a.this.R0));
                    }
                    if (ax.U2.f.A0(abstractC6239n.Q())) {
                        this.i.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                    } else {
                        if (B.P(abstractC6239n)) {
                            if (this.I == null) {
                                c6249y = (C6249y) abstractC6239n;
                                if (c6249y.X0()) {
                                    this.i.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                                    z3 = true;
                                } else {
                                    this.i.setText(c6249y.F0());
                                }
                            }
                            if (!a.this.e0()) {
                                if (this.n.getTag() != null) {
                                    if (z) {
                                        if (a.this.E0) {
                                            a.this.W().u(abstractC6239n, this.n, this.P);
                                        } else {
                                            a.this.W().x(abstractC6239n, this.n, this.P);
                                        }
                                    } else if (a.this.E0) {
                                        a.this.W().u(abstractC6239n, this.n, this.P);
                                    } else {
                                        a.this.W().x(abstractC6239n, this.n, this.P);
                                    }
                                } else if (z) {
                                    if (a.this.E0) {
                                        a.this.W().u(abstractC6239n, this.n, this.P);
                                    } else {
                                        a.this.W().x(abstractC6239n, this.n, this.P);
                                    }
                                } else if (a.this.E0) {
                                    a.this.W().u(abstractC6239n, this.n, this.P);
                                } else {
                                    a.this.W().x(abstractC6239n, this.n, this.P);
                                }
                                if (this.n.getTag() == null) {
                                    this.n.setImageDrawable(a.this.R(abstractC6239n, true, this.F));
                                }
                                aVar = this.J;
                                if (aVar != null) {
                                    while (i4 < r7) {
                                        N(str);
                                    }
                                    this.J = null;
                                }
                            }
                            if (z2) {
                            }
                            d dVar3 = new d(this, a.this.Z, a.this.Z.A(), abstractC6239n, a.this.t0, z3, z2);
                            this.I = dVar3;
                            dVar3.i(new Void[0]);
                        }
                        this.i.setText(abstractC6239n.W());
                    }
                } else if (i3 != 3) {
                    this.h.setText(abstractC6239n.H(a.this.R0));
                    G(C(abstractC6239n));
                } else if (i3 != 10) {
                    if (i3 == 20) {
                        this.h.setVisibility(8);
                    }
                } else if (z) {
                    this.k.setVisibility(0);
                } else {
                    this.k.setVisibility(4);
                }
            } else if (a.this.n0) {
                this.h.setText(String.format("%.2f%%", Double.valueOf((this.H * 100.0d) / a.this.q0)));
            } else if (a.this.Q0) {
                this.h.setText(abstractC6239n.H(a.this.R0));
            } else {
                this.h.setText(abstractC6239n.H(a.this.R0));
            }
            z3 = false;
            if (!a.this.e0()) {
                if (this.n.getTag() != null) {
                    if (z) {
                        if (a.this.E0) {
                            a.this.W().u(abstractC6239n, this.n, this.P);
                        } else {
                            a.this.W().x(abstractC6239n, this.n, this.P);
                        }
                    } else if (a.this.E0) {
                        a.this.W().u(abstractC6239n, this.n, this.P);
                    } else {
                        a.this.W().x(abstractC6239n, this.n, this.P);
                    }
                } else if (z) {
                    if (a.this.E0) {
                        a.this.W().u(abstractC6239n, this.n, this.P);
                    } else {
                        a.this.W().x(abstractC6239n, this.n, this.P);
                    }
                } else if (a.this.E0) {
                    a.this.W().u(abstractC6239n, this.n, this.P);
                } else {
                    a.this.W().x(abstractC6239n, this.n, this.P);
                }
                if (this.n.getTag() == null) {
                    this.n.setImageDrawable(a.this.R(abstractC6239n, true, this.F));
                }
                aVar = this.J;
                if (aVar != null) {
                    while (i4 < r7) {
                        N(str);
                    }
                    this.J = null;
                }
            }
            if (z2) {
            }
            d dVar4 = new d(this, a.this.Z, a.this.Z.A(), abstractC6239n, a.this.t0, z3, z2);
            this.I = dVar4;
            dVar4.i(new Void[0]);
        }

        public String A() {
            return this.x;
        }

        String C(AbstractC6239n abstractC6239n) {
            String strS = d0.s(abstractC6239n);
            if ("/".equals(strS)) {
                return "/";
            }
            return strS + "/";
        }

        public void t() {
            this.x = null;
        }

        public void y(AbstractC6239n abstractC6239n, int i2) {
            AbstractC6239n abstractC6239n2;
            this.G = i2;
            if (abstractC6239n == null) {
                return;
            }
            s();
            boolean zIsDirectory = abstractC6239n.isDirectory();
            long jQ = abstractC6239n.q();
            boolean zG = abstractC6239n.g();
            String str = this.x;
            if (str != null && str.equals(abstractC6239n.R()) && this.w == zIsDirectory && this.y == jQ && this.D == zG && this.E == a.this.w0()) {
                abstractC6239n2 = abstractC6239n;
            } else {
                abstractC6239n2 = abstractC6239n;
                x(abstractC6239n2, zIsDirectory, jQ, zG);
            }
            z(abstractC6239n2, i2, zIsDirectory);
        }
    }

    static class d extends ax.p406z3.q<Void, Void, Void> {
        c h;
        AbstractC6239n i;
        f j;
        boolean k;
        String l;
        boolean m;
        boolean n;
        C6240o o;

        d(c cVar, C6240o c6240o, f fVar, AbstractC6239n abstractC6239n, boolean z, boolean z2, boolean z3) {
            super(ax.z3.q.e.HIGH);
            this.o = c6240o;
            this.h = cVar;
            this.j = fVar;
            this.i = abstractC6239n;
            this.k = z;
            this.m = z2;
            this.n = z3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            if (this.m && B.P(this.i)) {
                this.l = ((C6249y) this.i).F0();
            }
            if (!this.n) {
                return null;
            }
            if (!this.i.isDirectory()) {
                this.i.p();
                return null;
            }
            if (!B.P(this.i)) {
                return null;
            }
            ((C6249y) this.i).i1(this.k);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Void r2) {
            if (this.i.R().equals(this.h.A())) {
                if (this.m) {
                    this.h.i.setText(this.l);
                }
                if (this.n) {
                    this.h.v(this.i);
                    this.h.w(this.i);
                }
            }
        }
    }

    public a(Context context, List<AbstractC6239n> list, C6240o c6240o, ax.p358w3.d dVar, int i, u uVar, boolean z, boolean z2) {
        super(context, 0, list);
        this.q = new Object();
        this.n0 = false;
        this.o0 = false;
        this.p0 = false;
        this.u0 = false;
        this.v0 = false;
        this.L0 = new TreeMap<>();
        this.X = context;
        this.Z = c6240o;
        this.k0 = dVar;
        this.s0 = uVar;
        this.v0 = z;
        f fVarA = c6240o.A();
        this.y0 = fVarA;
        this.K0 = z2;
        this.z0 = f.i0(fVarA) || f.e0(this.y0) || this.y0 == f.y0;
        if (U0 == null) {
            U0 = ax.Q.b.d(context, R.color.music_icon_not_playing);
            T0 = ax.Q.b.d(context, R.color.music_icon_playing);
        }
        f0();
        o0(i);
        Y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean C0() {
        return this.l0 == 2;
    }

    private void K() {
        this.L0.clear();
        AbstractC6239n abstractC6239n = null;
        int i = 0;
        String strQ = null;
        int i2 = -1;
        int i3 = -1;
        while (i < getCount()) {
            AbstractC6239n abstractC6239n2 = (AbstractC6239n) getItem(i);
            if (abstractC6239n == null || !d0(abstractC6239n2, abstractC6239n)) {
                if (i2 >= 0 && i3 >= 0) {
                    this.L0.put(Integer.valueOf(i3), new q(i3, i2, strQ));
                }
                strQ = Q(abstractC6239n2);
                i3 = i;
            }
            i2 = i;
            i++;
            abstractC6239n = abstractC6239n2;
        }
        if (i2 < 0 || i3 < 0) {
            return;
        }
        this.L0.put(Integer.valueOf(i3), new q(i3, i2, strQ));
    }

    private String Q(AbstractC6239n abstractC6239n) {
        if (abstractC6239n instanceof C2008n) {
            return abstractC6239n.B();
        }
        int i = this.x0;
        if (i == 1) {
            return abstractC6239n.H(this.R0);
        }
        if (i == 2) {
            return abstractC6239n.y();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable R(AbstractC6239n abstractC6239n, boolean z, int i) {
        if (z) {
            if (a0(this.l0)) {
                return ax.p374x3.d.h(getContext(), abstractC6239n.Q());
            }
            return ax.p374x3.d.e(getContext(), abstractC6239n, i != 0, C0());
        }
        int i2 = this.l0;
        if (i2 == 2) {
            return abstractC6239n.N(getContext());
        }
        if (i2 == 10) {
            return new InsetDrawable(abstractC6239n.N(getContext()), ax.p406z3.B.e(getContext(), 4));
        }
        return (i2 == 12 || i2 == 16) ? new InsetDrawable(abstractC6239n.N(getContext()), ax.p406z3.B.e(getContext(), 8)) : abstractC6239n.Z(getContext());
    }

    private int T() {
        int i = this.l0;
        if (i != 0 && i != 1) {
            if (i == 2) {
                return R.layout.filegrid_item;
            }
            if (i == 3) {
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
        return this.w0 ? R.layout.filelist_item_with_section : R.layout.filelist_item;
    }

    private void Y() {
        f fVar = this.y0;
        if (fVar == f.K0) {
            this.w0 = true;
            this.x0 = 1;
        } else if (fVar == f.r1 || fVar == f.s1) {
            this.w0 = true;
            this.x0 = 3;
        } else {
            this.w0 = false;
            this.x0 = 0;
        }
    }

    private boolean Z() {
        int i = this.l0;
        return i == 2 || i == 16;
    }

    private boolean a0(int i) {
        return i == 10 || i == 12 || i == 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b0(AbstractC6239n abstractC6239n) {
        Set<String> set = this.S0;
        if (set != null) {
            return set.contains(abstractC6239n.B());
        }
        return false;
    }

    private boolean c0(long j, long j2) {
        if (this.N0 == null) {
            this.N0 = new Date();
        }
        if (this.O0 == null) {
            this.O0 = new Date();
        }
        this.N0.setTime(j);
        this.O0.setTime(j2);
        return this.N0.getYear() == this.O0.getYear() && this.N0.getMonth() == this.O0.getMonth() && this.N0.getDate() == this.O0.getDate();
    }

    private boolean d0(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        if (abstractC6239n instanceof C2008n) {
            C2461b.d(this.x0 == 3);
            return ((C2008n) abstractC6239n).B1() == ((C2008n) abstractC6239n2).B1();
        }
        int i = this.x0;
        if (i == 1) {
            return c0(abstractC6239n.q(), abstractC6239n2.q());
        }
        if (i == 2) {
            return abstractC6239n.y().equals(abstractC6239n2.y());
        }
        C2461b.f();
        return true;
    }

    public void A0() {
        this.R0 = k.h(this.X);
    }

    public boolean B0() {
        return this.M0 && this.w0;
    }

    public void L() {
        this.n0 = false;
        this.o0 = false;
        this.q0 = 0L;
        this.r0 = 0L;
    }

    public int M(int i, String str) {
        if (i < 0) {
            i = 0;
        }
        synchronized (this.q) {
            for (int i2 = i; i2 < getCount(); i2++) {
                try {
                    String strB = ((AbstractC6239n) getItem(i2)).B();
                    if (strB != null && strB.toLowerCase().startsWith(str.toLowerCase())) {
                        return i2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            for (int i3 = 0; i3 < i && i3 < getCount(); i3++) {
                String strB2 = ((AbstractC6239n) getItem(i3)).B();
                if (strB2 != null && strB2.toLowerCase().startsWith(str.toLowerCase())) {
                    return i3;
                }
            }
            return -1;
        }
    }

    public ArrayList<AbstractC6239n> N() {
        ArrayList<AbstractC6239n> arrayList;
        synchronized (this.q) {
            try {
                arrayList = new ArrayList<>();
                for (int i = 0; i < getCount(); i++) {
                    arrayList.add((AbstractC6239n) getItem(i));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public int O(String str) {
        synchronized (this.q) {
            for (int i = 0; i < getCount(); i++) {
                try {
                    String strB = ((AbstractC6239n) getItem(i)).B();
                    if (strB != null && strB.equals(str)) {
                        return i;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return -1;
        }
    }

    public ArrayList<AbstractC6239n> P() {
        ArrayList<AbstractC6239n> arrayList = new ArrayList<>();
        synchronized (this.q) {
            for (int i = 0; i < getCount(); i++) {
                try {
                    AbstractC6239n abstractC6239n = (AbstractC6239n) getItem(i);
                    if (A.B(abstractC6239n, true)) {
                        arrayList.add(abstractC6239n);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList;
    }

    public int S() {
        return this.m0;
    }

    public q U(int i) {
        q qVar = this.L0.get(Integer.valueOf(i));
        if (qVar != null) {
            return qVar;
        }
        Integer numFloorKey = this.L0.floorKey(Integer.valueOf(i));
        if (numFloorKey != null) {
            return this.L0.get(numFloorKey);
        }
        C2461b.g("position : " + i + ":" + getCount());
        return null;
    }

    public AbstractC6239n V(AbstractC6239n abstractC6239n) {
        String strE = abstractC6239n.E();
        synchronized (this.q) {
            try {
                String strL = d0.l(strE);
                for (int i = 0; i < getCount(); i++) {
                    AbstractC6239n abstractC6239n2 = (AbstractC6239n) getItem(i);
                    if (abstractC6239n2 != null && abstractC6239n2.E().startsWith(strL)) {
                        String strY = abstractC6239n2.y();
                        if (A.R(strY)) {
                            if (abstractC6239n2.E().equals(strL + "." + strY)) {
                                return abstractC6239n2;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ax.p358w3.d W() {
        return this.k0;
    }

    public boolean X(String str) {
        synchronized (this.q) {
            for (int i = 0; i < getCount(); i++) {
                try {
                    String strE = ((AbstractC6239n) getItem(i)).E();
                    if (((AbstractC6239n) getItem(i)).Q().V()) {
                        if (strE.equalsIgnoreCase(str)) {
                            return true;
                        }
                    } else if (strE.equals(str)) {
                        return true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return false;
        }
    }

    @Override // android.widget.ArrayAdapter
    public void addAll(Collection<? extends AbstractC6239n> collection) {
        synchronized (this.q) {
            super.addAll(collection);
        }
        if (this.w0) {
            K();
        }
    }

    @Override // android.widget.ArrayAdapter
    public void clear() {
        synchronized (this.q) {
            super.clear();
        }
        if (this.w0) {
            K();
        }
    }

    public boolean e0() {
        return this.Y;
    }

    public void f0() {
        this.A0 = ax.Q.b.c(this.X, R.color.shape_invert);
        this.G0 = ax.Q.b.c(this.X, R.color.main_primary_text);
        this.H0 = z.n(this.X);
    }

    public void g0(boolean z) {
        this.I0 = z;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = LayoutInflater.from(this.X).inflate(T(), viewGroup, false);
            cVar = new c(view, viewGroup);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        try {
            cVar.y((AbstractC6239n) getItem(i), i);
            return view;
        } catch (IndexOutOfBoundsException e) {
            ax.p302sb.c.h().g().d("!! INDEX OUT OF BOUND !!").m(e).i();
            return view;
        }
    }

    public void h0(long j, long j2) {
        this.n0 = true;
        this.o0 = true;
        this.q0 = j;
        this.r0 = j2;
    }

    public void i0(Set<String> set) {
        this.S0 = set;
    }

    public void j0(boolean z) {
        this.F0 = z;
    }

    public void k0(String str) {
        this.P0 = str;
    }

    public void l0(String str) {
        this.B0 = str;
    }

    public void m0(int i) {
        this.m0 = i;
    }

    public void n0(boolean z) {
        this.t0 = z;
    }

    public void o0(int i) {
        this.l0 = i;
        if (a0(i)) {
            this.D0 = ax.p406z3.B.e(getContext(), 1);
        } else {
            this.D0 = ax.p406z3.B.e(getContext(), 0);
        }
        notifyDataSetChanged();
    }

    public void p0(AdapterView.OnItemClickListener onItemClickListener) {
        this.J0 = onItemClickListener;
    }

    public void q0(boolean z) {
        boolean z2 = this.Y != z;
        this.Y = z;
        if (!z2 || z) {
            return;
        }
        notifyDataSetChanged();
    }

    public void r0(boolean z, int i) {
        this.w0 = z;
        if (z) {
            this.x0 = i;
            K();
        } else {
            this.x0 = 0;
            this.L0.clear();
        }
    }

    public void s0(boolean z) {
        this.Q0 = z;
    }

    public void t0(K k, String str) {
        if (!f.B0(k.d())) {
            this.E0 = true;
            return;
        }
        if (!ax.p214n3.a.e(getContext()).m()) {
            this.E0 = false;
        } else if (ax.p390y3.f.i(this.X, k.d(), k.b(), str, false)) {
            this.E0 = true;
        } else {
            this.E0 = false;
        }
    }

    public void u0(boolean z) {
        if (this.w0) {
            this.M0 = z;
        } else {
            this.M0 = false;
        }
    }

    public void v0() {
        this.o0 = true;
    }

    boolean w0() {
        return (this.F0 && Z()) ? false : true;
    }

    public void x0(boolean z) {
        this.p0 = z;
    }

    public void y0(boolean z) {
        this.u0 = z;
    }

    public boolean z0(List<AbstractC6239n> list, View view, int i, int i2, int i3) {
        if (!Q.N0()) {
            return false;
        }
        if (i >= getCount()) {
            ax.U2.d.c("start darg", new Exception("index : " + i + ",count:" + getCount()));
            return false;
        }
        AbstractC6239n abstractC6239n = (AbstractC6239n) getItem(i);
        if (abstractC6239n == null || list == null || view == null || !ax.p406z3.u.a(list)) {
            return false;
        }
        view.startDragAndDrop(ax.p406z3.u.b(list, abstractC6239n), new C0554a(view, i2, i3), new e(true), 257);
        return true;
    }
}
