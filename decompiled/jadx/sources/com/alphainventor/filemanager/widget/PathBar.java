package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ax.E1.O;
import ax.J1.C0740v;
import ax.J1.F;
import ax.J1.V;
import ax.J1.b0;
import ax.p031c2.k;
import ax.p045d2.x;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.L;
import java.io.File;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class PathBar extends FrameLayout {
    private boolean A0;
    private ax.p338y1.f B0;
    private int C0;
    private int D0;
    private boolean E0;
    private b0 F0;
    private HorizontalScrollView h0;
    private LinearLayout i0;
    private ViewGroup j0;
    private TextView k0;
    private View l0;
    private TextView m0;
    private View n0;
    private View o0;
    private TextView p0;
    private RelativeLayout q;
    private PieProgress q0;
    private h r0;
    private TextView s0;
    private String t0;
    private String u0;
    private String v0;
    private boolean w0;
    private F x0;
    private int y0;
    private int z0;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ax.p338y1.a.k().o("navigation", "open_folder_back").c("loc", PathBar.this.x0.d().H()).c("by", "pathbar_root").e();
            PathBar.this.r0.b(PathBar.this.x0.e());
        }
    }

    class b extends ax.P1.c {
        b() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            PathBar.this.r0.e();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar pathBar = PathBar.this;
            if (pathBar.l(pathBar.v0) && k.I()) {
                PathBar.this.r0.a("pathbar_up");
            } else {
                PathBar.this.r0.c(PathBar.this.u0);
                ax.p338y1.a.k().o("navigation", "open_folder_back").c("loc", PathBar.this.x0.d().H()).c("by", "pathbar_up").e();
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PathBar.this.h0.fullScroll(66);
        }
    }

    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar.this.r0.a("pathbar_home");
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar.this.r0.d("pathbar_home");
        }
    }

    class g implements View.OnClickListener {
        final /* synthetic */ String q;

        g(String str) {
            this.q = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar.this.r0.b(this.q);
            ax.p338y1.a.k().o("navigation", "open_folder_back").c("loc", PathBar.this.x0.d().H()).c("by", "pathbar_directory").e();
        }
    }

    public interface h {
        void a(String str);

        void b(String str);

        void c(String str);

        void d(String str);

        void e();
    }

    public PathBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.z0 = 0;
        this.A0 = false;
        this.E0 = true;
        k(context);
    }

    private void g(String str, String str2, boolean z) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        ViewGroup viewGroup = this.z0 == 2 ? (ViewGroup) layoutInflater.inflate(R.layout.pathbar_item_2, (ViewGroup) this.i0, false) : (ViewGroup) layoutInflater.inflate(R.layout.pathbar_item, (ViewGroup) this.i0, false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.button);
        textView.setText(str);
        textView.setTextColor(z ? this.C0 : this.D0);
        textView.setOnClickListener(new g(str2));
        this.i0.addView(viewGroup);
    }

    private void j() {
        TextView textView = this.k0;
        if (textView != null) {
            textView.setVisibility(8);
        }
        View view = this.l0;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l(String str) {
        if (this.x0.e() != null) {
            return this.x0.e().equals(str);
        }
        if (this.x0.d() == ax.p338y1.f.x0) {
            return false;
        }
        ax.p221pa.c.h().g().d("ISROOT!! ROOTPATH NULL").h(this.x0.toString()).i();
        return false;
    }

    private void m() {
        b0 b0Var;
        if (ax.p338y1.f.S(this.x0.d())) {
            if (l(this.v0) && this.B0 == null && this.E0 && this.F0 != null) {
                this.o0.setVisibility(0);
                return;
            } else {
                this.o0.setVisibility(8);
                return;
            }
        }
        if (this.x0.d() == ax.p338y1.f.H0) {
            this.o0.setVisibility(0);
            if (O.b()) {
                this.p0.setText(R.string.menu_empty);
            } else {
                this.p0.setText(R.string.menu_clear);
            }
            this.q0.setVisibility(8);
            return;
        }
        if (ax.p338y1.f.r0(this.x0.d())) {
            if (!l(this.v0) || (b0Var = this.F0) == null || b0Var.b == 0) {
                this.s0.setVisibility(8);
                return;
            } else {
                this.s0.setVisibility(0);
                return;
            }
        }
        if (ax.p338y1.f.L0 == this.x0.d()) {
            b0 b0Var2 = this.F0;
            if (b0Var2 == null || b0Var2.b == 0 || !L.d0(this.v0)) {
                this.s0.setVisibility(8);
            } else {
                this.s0.setVisibility(0);
            }
        }
    }

    private void n(String str, boolean z) {
        this.u0 = str;
        if (k.b()) {
            this.n0.setVisibility(8);
        } else if (z) {
            this.n0.setVisibility(0);
        } else {
            this.n0.setVisibility(8);
        }
    }

    private void o() {
        this.k0.setCompoundDrawables(ax.p017b2.d.i(getContext(), ax.p338y1.f.n0, true), null, null, null);
        this.k0.setCompoundDrawablePadding(0);
        this.k0.setVisibility(0);
        this.k0.setContentDescription(getContext().getString(R.string.location_home));
        int i = this.z0;
        if (i == 2 || i == 3) {
            this.l0.setVisibility(0);
        }
        this.k0.setOnClickListener(new f());
    }

    private void p() {
        this.k0.setCompoundDrawables(ax.p017b2.d.i(getContext(), this.B0, true), null, null, null);
        this.k0.setCompoundDrawablePadding(0);
        this.k0.setContentDescription(this.B0.L(getContext()));
        this.k0.setVisibility(0);
        int i = this.z0;
        if (i == 2 || i == 3) {
            this.l0.setVisibility(0);
        }
        this.k0.setOnClickListener(new e());
    }

    private void q(boolean z) {
        if (ax.p017b2.d.j(this.x0.d(), null) > 0) {
            this.m0.setCompoundDrawables(ax.p017b2.d.i(getContext(), this.x0.d(), z), null, null, null);
            if (z) {
                String str = this.t0;
                if (str == null || HttpUrl.FRAGMENT_ENCODE_SET.equals(str)) {
                    this.m0.setCompoundDrawablePadding(0);
                } else {
                    this.m0.setCompoundDrawablePadding(0);
                }
            } else {
                this.m0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                this.m0.setCompoundDrawablePadding(0);
            }
        } else {
            this.m0.setText(this.t0);
            this.m0.setTextColor(z ? this.C0 : this.D0);
            this.m0.setCompoundDrawablePadding(0);
        }
        this.m0.setContentDescription(getContext().getString(R.string.label_root_folder));
    }

    private void setAnalyzeButtonBackground(Context context) {
        int paddingLeft = this.o0.getPaddingLeft();
        int paddingRight = this.o0.getPaddingRight();
        int paddingTop = this.o0.getPaddingTop();
        int paddingBottom = this.o0.getPaddingBottom();
        this.o0.setBackgroundDrawable(ax.p017b2.a.c(context, R.drawable.btn_border_material));
        this.o0.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    private void setAnalyzeButtonProgress(float f2) {
        this.p0.setText(getContext().getString(R.string.used_percent, x.S(f2)));
        this.q0.setProgressPercent((int) f2);
    }

    public void h(String str) {
        String string;
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            ax.p221pa.c.h().d("CD!!!").k().h(this.x0.toString()).i();
        }
        if (str == null) {
            return;
        }
        this.i0.removeAllViews();
        this.i0.addView(this.j0);
        if (this.x0.e() == null) {
            return;
        }
        String strU = V.u(this.x0, str, Boolean.TRUE);
        if (strU == null) {
            ax.p221pa.c.h().d("PTHBNU!!").k().h(this.x0.toString() + ":" + this.x0.e()).i();
            return;
        }
        String[] strArrSplit = strU.split(File.separator);
        if (strArrSplit.length <= 0 || !this.w0) {
            StringBuilder sb = new StringBuilder(this.x0.e());
            int i = 0;
            while (i < strArrSplit.length) {
                String str2 = strArrSplit[i];
                if (str2.length() != 0) {
                    if (sb.toString().endsWith("/")) {
                        sb.append(str2);
                        string = sb.toString();
                    } else {
                        sb.append(File.separator);
                        sb.append(str2);
                        string = sb.toString();
                    }
                    g(str2, string, i == strArrSplit.length - 1);
                }
                i++;
            }
        } else {
            g(strArrSplit[strArrSplit.length - 1], str, true);
        }
        String strE = this.w0 ? this.x0.e() : V.r(str);
        this.v0 = str;
        if (l(str)) {
            if (k.I()) {
                n(str, true);
            } else {
                n(str, false);
            }
            q(true);
        } else {
            n(strE, true);
            q(false);
        }
        m();
        post(new d());
    }

    public void i() {
        this.F0 = null;
        m();
    }

    public void k(Context context) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        RelativeLayout relativeLayout = (RelativeLayout) layoutInflaterFrom.inflate(R.layout.pathbar, (ViewGroup) this, false);
        this.q = relativeLayout;
        addView(relativeLayout);
        this.C0 = ax.P.b.c(context, R.color.pathbar_text_selected);
        this.D0 = ax.P.b.c(context, R.color.pathbar_text_normal);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) this.q.findViewById(R.id.scroller);
        this.h0 = horizontalScrollView;
        this.i0 = (LinearLayout) horizontalScrollView.findViewById(R.id.content);
        int iJ = k.j();
        this.y0 = iJ;
        int iN = k.n(iJ);
        this.z0 = iN;
        if (iN == 2) {
            this.j0 = (ViewGroup) layoutInflaterFrom.inflate(R.layout.pathbar_root_2, (ViewGroup) this.i0, false);
        } else {
            this.j0 = (ViewGroup) layoutInflaterFrom.inflate(R.layout.pathbar_root, (ViewGroup) this.i0, false);
        }
        this.k0 = (TextView) this.j0.findViewById(R.id.home);
        this.l0 = this.j0.findViewById(R.id.root_devider);
        o();
        TextView textView = (TextView) this.j0.findViewById(R.id.root);
        this.m0 = textView;
        textView.setOnClickListener(new a());
        this.i0.addView(this.j0);
        this.h0.setHorizontalScrollBarEnabled(false);
        this.h0.setHorizontalFadingEdgeEnabled(true);
        View viewFindViewById = this.q.findViewById(R.id.button_action);
        this.o0 = viewFindViewById;
        viewFindViewById.setOnClickListener(new b());
        this.p0 = (TextView) this.q.findViewById(R.id.button_action_text);
        this.q0 = (PieProgress) this.q.findViewById(R.id.pie_progress);
        setAnalyzeButtonBackground(context);
        this.s0 = (TextView) this.q.findViewById(R.id.storage_space);
        View viewFindViewById2 = this.q.findViewById(R.id.button_up);
        this.n0 = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new c());
        this.u0 = "/";
    }

    public void setActionButtonEnabled(boolean z) {
        View viewFindViewById;
        for (int i = 0; i < this.i0.getChildCount(); i++) {
            View childAt = this.i0.getChildAt(i);
            if ((childAt instanceof ViewGroup) && (viewFindViewById = ((ViewGroup) childAt).findViewById(R.id.button)) != null) {
                viewFindViewById.setEnabled(z);
            }
        }
        this.k0.setEnabled(z);
        this.m0.setEnabled(z);
        this.h0.setEnabled(z);
        if (z) {
            m();
        } else {
            this.o0.setVisibility(8);
        }
        this.o0.setEnabled(z);
    }

    public void setIsArchiveFile(boolean z) {
        this.A0 = z;
        if (z) {
            j();
        }
    }

    public void setIsTwoDepth(boolean z) {
        this.w0 = z;
    }

    public void setLocationUnit(F f2) {
        this.x0 = f2;
    }

    public void setParentLocation(ax.p338y1.f fVar) {
        this.B0 = fVar;
        if (fVar != null) {
            p();
        } else {
            o();
        }
    }

    public void setPathBarListener(h hVar) {
        this.r0 = hVar;
    }

    public void setRootInfo(String str) {
        this.t0 = str;
        q(true);
    }

    public void setRootTitle(String str) {
        this.t0 = str;
    }

    public void setStorageSpace(b0 b0Var) {
        if (b0Var == null) {
            return;
        }
        this.F0 = b0Var;
        if (ax.p338y1.f.S(this.x0.d())) {
            b0 b0Var2 = this.F0;
            long j = b0Var2.b;
            setAnalyzeButtonProgress(j != 0 ? (float) ((b0Var2.a * 100.0d) / j) : -1.0f);
        } else if (this.F0.b != 0) {
            this.s0.setText(C0740v.i(getContext(), this.F0.d, C0740v.a.SHORT));
        } else {
            this.s0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        }
        m();
    }
}
