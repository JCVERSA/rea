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
import ax.p006a3.P;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p086f3.k0;
import ax.p390y3.m;
import ax.p406z3.B;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.Q;
import java.io.File;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class PathBar extends FrameLayout {
    private boolean A0;
    private K B0;
    private int C0;
    private int D0;
    private boolean E0;
    private ax.U2.f F0;
    private int G0;
    private int H0;
    private boolean I0;
    private k0 J0;
    private HorizontalScrollView k0;
    private LinearLayout l0;
    private ViewGroup m0;
    private TextView n0;
    private View o0;
    private TextView p0;
    private RelativeLayout q;
    private View q0;
    private View r0;
    private TextView s0;
    private PieProgress t0;
    private h u0;
    private TextView v0;
    private View w0;
    private String x0;
    private String y0;
    private String z0;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ax.U2.a.k().o("navigation", "open_folder_back").c("loc", PathBar.this.B0.d().H()).c("by", "pathbar_root").e();
            PathBar.this.u0.b(PathBar.this.B0.e());
        }
    }

    class b extends ax.p182l3.c {
        b() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            PathBar.this.u0.e();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar pathBar = PathBar.this;
            if (pathBar.l(pathBar.z0) && m.I()) {
                PathBar.this.u0.a("pathbar_up");
            } else {
                PathBar.this.u0.c(PathBar.this.y0);
                ax.U2.a.k().o("navigation", "open_folder_back").c("loc", PathBar.this.B0.d().H()).c("by", "pathbar_up").e();
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PathBar.this.k0.fullScroll(66);
        }
    }

    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar.this.u0.a("pathbar_home");
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar.this.u0.d("pathbar_home");
        }
    }

    class g implements View.OnClickListener {
        final /* synthetic */ String q;

        g(String str) {
            this.q = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PathBar.this.u0.b(this.q);
            ax.U2.a.k().o("navigation", "open_folder_back").c("loc", PathBar.this.B0.d().H()).c("by", "pathbar_directory").e();
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
        this.D0 = 0;
        this.E0 = false;
        this.I0 = true;
        k(context);
    }

    private void g(String str, String str2, boolean z) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        ViewGroup viewGroup = this.D0 == 2 ? (ViewGroup) layoutInflater.inflate(R.layout.pathbar_item_2, (ViewGroup) this.l0, false) : (ViewGroup) layoutInflater.inflate(R.layout.pathbar_item, (ViewGroup) this.l0, false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.button);
        textView.setText(str);
        textView.setTextColor(z ? this.G0 : this.H0);
        textView.setOnClickListener(new g(str2));
        this.l0.addView(viewGroup);
    }

    private void j() {
        TextView textView = this.n0;
        if (textView != null) {
            textView.setVisibility(8);
        }
        View view = this.o0;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l(String str) {
        if (this.B0.e() != null) {
            return this.B0.e().equals(str);
        }
        if (this.B0.d() == ax.U2.f.A0) {
            return false;
        }
        ax.p302sb.c.h().g().d("ISROOT!! ROOTPATH NULL").h(this.B0.toString()).i();
        return false;
    }

    private void m() {
        if (this.w0 != null) {
            if (l(this.z0) && ax.U2.f.u0(this.B0.d())) {
                this.w0.setVisibility(0);
            } else {
                this.w0.setVisibility(8);
            }
        }
    }

    private void n() {
        k0 k0Var;
        if (ax.U2.f.S(this.B0.d())) {
            if (l(this.z0) && this.F0 == null && this.I0 && this.J0 != null) {
                this.r0.setVisibility(0);
                return;
            } else {
                this.r0.setVisibility(8);
                return;
            }
        }
        if (this.B0.d() == ax.U2.f.K0) {
            this.r0.setVisibility(0);
            if (P.b()) {
                this.s0.setText(R.string.menu_empty);
                View view = this.r0;
                view.setContentDescription(view.getContext().getString(R.string.menu_empty));
            } else {
                this.s0.setText(R.string.menu_clear);
                View view2 = this.r0;
                view2.setContentDescription(view2.getContext().getString(R.string.menu_clear));
            }
            this.t0.setVisibility(8);
            return;
        }
        if (ax.U2.f.t0(this.B0.d())) {
            if (!l(this.z0) || (k0Var = this.J0) == null || k0Var.b == 0) {
                this.v0.setVisibility(8);
                return;
            } else {
                this.v0.setVisibility(0);
                return;
            }
        }
        if (ax.U2.f.O0 == this.B0.d()) {
            k0 k0Var2 = this.J0;
            if (k0Var2 == null || k0Var2.b == 0 || !Q.j0(this.z0)) {
                this.v0.setVisibility(8);
            } else {
                this.v0.setVisibility(0);
            }
        }
    }

    private void o(Context context, View view) {
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        view.setBackgroundDrawable(ax.p374x3.a.c(context, R.drawable.btn_border_material));
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    private void p(String str, boolean z) {
        this.y0 = str;
        if (m.b()) {
            this.q0.setVisibility(8);
        } else if (z) {
            this.q0.setVisibility(0);
        } else {
            this.q0.setVisibility(8);
        }
    }

    private void q() {
        this.n0.setCompoundDrawables(ax.p374x3.d.i(getContext(), ax.U2.f.q0, true), null, null, null);
        this.n0.setCompoundDrawablePadding(0);
        this.n0.setVisibility(0);
        this.n0.setContentDescription(getContext().getString(R.string.location_home));
        int i = this.D0;
        if (i == 2 || i == 3) {
            this.o0.setVisibility(0);
        }
        this.n0.setOnClickListener(new f());
    }

    private void r() {
        this.n0.setCompoundDrawables(ax.p374x3.d.i(getContext(), this.F0, true), null, null, null);
        this.n0.setCompoundDrawablePadding(0);
        this.n0.setContentDescription(this.F0.L(getContext()));
        this.n0.setVisibility(0);
        int i = this.D0;
        if (i == 2 || i == 3) {
            this.o0.setVisibility(0);
        }
        this.n0.setOnClickListener(new e());
    }

    private void s(boolean z) {
        if (ax.p374x3.d.j(this.B0.d(), null) > 0) {
            this.p0.setCompoundDrawables(ax.p374x3.d.i(getContext(), this.B0.d(), z), null, null, null);
            if (z) {
                String str = this.x0;
                if (str == null || HttpUrl.FRAGMENT_ENCODE_SET.equals(str)) {
                    this.p0.setCompoundDrawablePadding(0);
                } else {
                    this.p0.setCompoundDrawablePadding(0);
                }
            } else {
                this.p0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
                this.p0.setCompoundDrawablePadding(0);
            }
        } else {
            this.p0.setText(this.x0);
            this.p0.setTextColor(z ? this.G0 : this.H0);
            this.p0.setCompoundDrawablePadding(0);
        }
        this.p0.setContentDescription(getContext().getString(R.string.label_root_folder));
    }

    private void setAnalyzeButtonProgress(float f2) {
        this.s0.setText(getContext().getString(R.string.used_percent, B.Q(f2)));
        this.t0.setProgressPercent((int) f2);
    }

    public int getParentButtonId() {
        return R.id.home;
    }

    public void h(String str) {
        String string;
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            ax.p302sb.c.h().d("CD!!!").k().h(this.B0.toString()).i();
        }
        if (str == null) {
            return;
        }
        this.l0.removeAllViews();
        this.l0.addView(this.m0);
        if (this.B0.e() == null) {
            return;
        }
        String strV = d0.v(this.B0, str, Boolean.TRUE);
        if (strV == null) {
            ax.p302sb.c.h().d("PTHBNU!!").k().h(this.B0.toString() + ":" + this.B0.e()).i();
            return;
        }
        String[] strArrSplit = strV.split(File.separator);
        if (strArrSplit.length <= 0 || !this.A0) {
            StringBuilder sb = new StringBuilder(this.B0.e());
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
        String strE = this.A0 ? this.B0.e() : d0.r(str);
        this.z0 = str;
        if (l(str)) {
            if (m.I()) {
                p(str, true);
            } else {
                p(str, false);
            }
            s(true);
        } else {
            p(strE, true);
            s(false);
        }
        n();
        m();
        post(new d());
    }

    public void i() {
        this.J0 = null;
        n();
    }

    public void k(Context context) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        RelativeLayout relativeLayout = (RelativeLayout) layoutInflaterFrom.inflate(R.layout.pathbar, (ViewGroup) this, false);
        this.q = relativeLayout;
        addView(relativeLayout);
        this.G0 = ax.Q.b.c(context, R.color.pathbar_text_selected);
        this.H0 = ax.Q.b.c(context, R.color.pathbar_text_normal);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) this.q.findViewById(R.id.scroller);
        this.k0 = horizontalScrollView;
        this.l0 = (LinearLayout) horizontalScrollView.findViewById(R.id.content);
        int iJ = m.j();
        this.C0 = iJ;
        int iN = m.n(iJ);
        this.D0 = iN;
        if (iN == 2) {
            this.m0 = (ViewGroup) layoutInflaterFrom.inflate(R.layout.pathbar_root_2, (ViewGroup) this.l0, false);
        } else {
            this.m0 = (ViewGroup) layoutInflaterFrom.inflate(R.layout.pathbar_root, (ViewGroup) this.l0, false);
        }
        this.n0 = (TextView) this.m0.findViewById(R.id.home);
        this.o0 = this.m0.findViewById(R.id.root_devider);
        q();
        TextView textView = (TextView) this.m0.findViewById(R.id.root);
        this.p0 = textView;
        textView.setOnClickListener(new a());
        this.l0.addView(this.m0);
        this.k0.setHorizontalScrollBarEnabled(false);
        this.k0.setHorizontalFadingEdgeEnabled(true);
        View viewFindViewById = this.q.findViewById(R.id.button_action);
        this.r0 = viewFindViewById;
        viewFindViewById.setOnClickListener(new b());
        boolean z = context instanceof com.alphainventor.filemanager.activity.a;
        this.s0 = (TextView) this.q.findViewById(R.id.button_action_text);
        this.t0 = (PieProgress) this.q.findViewById(R.id.pie_progress);
        o(context, this.r0);
        this.v0 = (TextView) this.q.findViewById(R.id.storage_space);
        View viewFindViewById2 = this.q.findViewById(R.id.button_up);
        this.q0 = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new c());
        this.y0 = "/";
    }

    public void setActionButtonEnabled(boolean z) {
        View viewFindViewById;
        for (int i = 0; i < this.l0.getChildCount(); i++) {
            View childAt = this.l0.getChildAt(i);
            if ((childAt instanceof ViewGroup) && (viewFindViewById = ((ViewGroup) childAt).findViewById(R.id.button)) != null) {
                viewFindViewById.setEnabled(z);
            }
        }
        this.n0.setEnabled(z);
        this.p0.setEnabled(z);
        this.k0.setEnabled(z);
        if (z) {
            n();
        } else {
            this.r0.setVisibility(8);
        }
        this.r0.setEnabled(z);
        View view = this.w0;
        if (view != null) {
            if (z) {
                m();
            } else {
                view.setVisibility(8);
            }
            this.w0.setEnabled(z);
        }
    }

    public void setIsArchiveFile(boolean z) {
        this.E0 = z;
        if (z) {
            j();
        }
    }

    public void setIsTwoDepth(boolean z) {
        this.A0 = z;
    }

    public void setLocationUnit(K k) {
        this.B0 = k;
    }

    public void setParentLocation(ax.U2.f fVar) {
        this.F0 = fVar;
        if (fVar != null) {
            r();
        } else {
            q();
        }
    }

    public void setPathBarListener(h hVar) {
        this.u0 = hVar;
    }

    public void setRootInfo(String str) {
        this.x0 = str;
        s(true);
    }

    public void setRootTitle(String str) {
        this.x0 = str;
    }

    public void setStorageSpace(k0 k0Var) {
        if (k0Var == null) {
            return;
        }
        this.J0 = k0Var;
        if (ax.U2.f.S(this.B0.d())) {
            k0 k0Var2 = this.J0;
            long j = k0Var2.b;
            setAnalyzeButtonProgress(j != 0 ? (float) ((k0Var2.a * 100.0d) / j) : -1.0f);
        } else if (this.J0.b != 0) {
            this.v0.setText(ax.p086f3.B.l(getContext(), this.J0.d, ax.f3.B.a.SHORT));
        } else {
            this.v0.setText(HttpUrl.FRAGMENT_ENCODE_SET);
        }
        n();
    }
}
