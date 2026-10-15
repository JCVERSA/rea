package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import ax.p035c0.C1839d0;
import ax.p035c0.F0;
import ax.p035c0.I;
import ax.p202m7.z;
import com.google.android.material.datepicker.k;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
public class k<S> extends androidx.fragment.app.e {
    static final Object d2 = "CONFIRM_BUTTON_TAG";
    static final Object e2 = "CANCEL_BUTTON_TAG";
    static final Object f2 = "TOGGLE_BUTTON_TAG";
    private int D1;
    private ax.p122h7.d<S> E1;
    private p<S> F1;
    private com.google.android.material.datepicker.a G1;
    private ax.p122h7.f H1;
    private j<S> I1;
    private int J1;
    private CharSequence K1;
    private boolean L1;
    private int M1;
    private int N1;
    private CharSequence O1;
    private int P1;
    private CharSequence Q1;
    private int R1;
    private CharSequence S1;
    private int T1;
    private CharSequence U1;
    private TextView V1;
    private TextView W1;
    private CheckableImageButton X1;
    private ax.p346v7.h Y1;
    private Button Z1;
    private boolean a2;
    private CharSequence b2;
    private CharSequence c2;
    private final LinkedHashSet<ax.p122h7.j<? super S>> z1 = new LinkedHashSet<>();
    private final LinkedHashSet<View.OnClickListener> A1 = new LinkedHashSet<>();
    private final LinkedHashSet<DialogInterface.OnCancelListener> B1 = new LinkedHashSet<>();
    private final LinkedHashSet<DialogInterface.OnDismissListener> C1 = new LinkedHashSet<>();

    class a implements I {
        final /* synthetic */ int a;
        final /* synthetic */ View b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        a(int i, View view, int i2, int i3, int i4) {
            this.a = i;
            this.b = view;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        @Override // ax.p035c0.I
        public F0 a(View view, F0 f0) {
            ax.T.b bVarF = f0.f(F0.m.h());
            if (this.a >= 0) {
                this.b.getLayoutParams().height = this.a + bVarF.b;
                View view2 = this.b;
                view2.setLayoutParams(view2.getLayoutParams());
            }
            View view3 = this.b;
            view3.setPadding(this.c + bVarF.a, this.d + bVarF.b, this.e + bVarF.c, view3.getPaddingBottom());
            return f0;
        }
    }

    class b extends ax.p122h7.k<S> {
        b() {
        }

        @Override // ax.p122h7.k
        public void a() {
            k.this.Z1.setEnabled(false);
        }

        @Override // ax.p122h7.k
        public void b(S s) {
            k kVar = k.this;
            kVar.L3(kVar.y3());
            k.this.Z1.setEnabled(k.this.v3().w());
        }
    }

    public static final class c<S> {
        final ax.p122h7.d<S> a;
        com.google.android.material.datepicker.a c;
        ax.p122h7.f d;
        int b = 0;
        int e = 0;
        CharSequence f = null;
        int g = 0;
        CharSequence h = null;
        int i = 0;
        CharSequence j = null;
        int k = 0;
        CharSequence l = null;
        int m = 0;
        CharSequence n = null;
        S o = null;
        int p = 0;

        private c(ax.p122h7.d<S> dVar) {
            this.a = dVar;
        }

        private m b() {
            if (!this.a.x().isEmpty()) {
                m mVarB = m.B(this.a.x().iterator().next().longValue());
                if (d(mVarB, this.c)) {
                    return mVarB;
                }
            }
            m mVarC = m.C();
            return d(mVarC, this.c) ? mVarC : this.c.n();
        }

        public static c<ax.p019b0.c<Long, Long>> c() {
            return new c<>(new q());
        }

        private static boolean d(m mVar, com.google.android.material.datepicker.a aVar) {
            return mVar.compareTo(aVar.n()) >= 0 && mVar.compareTo(aVar.i()) <= 0;
        }

        public k<S> a() {
            if (this.c == null) {
                this.c = new com.google.android.material.datepicker.a.b().a();
            }
            if (this.e == 0) {
                this.e = this.a.s();
            }
            S s = this.o;
            if (s != null) {
                this.a.o(s);
            }
            if (this.c.l() == null) {
                this.c.A(b());
            }
            return k.G3(this);
        }
    }

    private int B3(Context context) {
        int i = this.D1;
        return i != 0 ? i : v3().u(context);
    }

    private void C3(Context context) {
        this.X1.setTag(f2);
        this.X1.setImageDrawable(t3(context));
        this.X1.setChecked(this.M1 != 0);
        C1839d0.l0(this.X1, null);
        N3(this.X1);
        this.X1.setOnClickListener(new View.OnClickListener() { // from class: ax.h7.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                k.n3(this.q, view);
            }
        });
    }

    static boolean D3(Context context) {
        return J3(context, R.attr.windowFullscreen);
    }

    private boolean E3() {
        return P0().getConfiguration().orientation == 2;
    }

    static boolean F3(Context context) {
        return J3(context, ax.R6.c.Q);
    }

    static <S> k<S> G3(c<S> cVar) {
        k<S> kVar = new k<>();
        Bundle bundle = new Bundle();
        bundle.putInt("OVERRIDE_THEME_RES_ID", cVar.b);
        bundle.putParcelable("DATE_SELECTOR_KEY", cVar.a);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", cVar.c);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", cVar.d);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", cVar.e);
        bundle.putCharSequence("TITLE_TEXT_KEY", cVar.f);
        bundle.putInt("INPUT_MODE_KEY", cVar.p);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", cVar.g);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", cVar.h);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", cVar.i);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", cVar.j);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", cVar.k);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", cVar.l);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", cVar.m);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", cVar.n);
        kVar.F2(bundle);
        return kVar;
    }

    static boolean J3(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(ax.p282r7.b.f(context, ax.R6.c.w, j.class.getCanonicalName()), new int[]{i});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    private void K3() {
        int iB3 = B3(z2());
        l lVarO3 = j.o3(v3(), iB3, this.G1, this.H1);
        this.I1 = lVarO3;
        if (this.M1 == 1) {
            lVarO3 = l.W2(v3(), iB3, this.G1);
        }
        this.F1 = lVarO3;
        M3();
        L3(y3());
        androidx.fragment.app.t tVarO = t0().o();
        tVarO.r(ax.R6.g.z, this.F1);
        tVarO.k();
        this.F1.U2(new b());
    }

    private void M3() {
        this.V1.setText((this.M1 == 1 && E3()) ? this.c2 : this.b2);
    }

    private void N3(CheckableImageButton checkableImageButton) {
        this.X1.setContentDescription(this.M1 == 1 ? checkableImageButton.getContext().getString(ax.R6.k.L) : checkableImageButton.getContext().getString(ax.R6.k.N));
    }

    public static /* synthetic */ void n3(k kVar, View view) {
        kVar.Z1.setEnabled(kVar.v3().w());
        kVar.X1.toggle();
        kVar.M1 = kVar.M1 == 1 ? 0 : 1;
        kVar.N3(kVar.X1);
        kVar.K3();
    }

    private static Drawable t3(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, ax.p226o.a.b(context, ax.R6.f.e));
        stateListDrawable.addState(new int[0], ax.p226o.a.b(context, ax.R6.f.f));
        return stateListDrawable;
    }

    private void u3(Window window) {
        if (this.a2) {
            return;
        }
        View viewFindViewById = A2().findViewById(ax.R6.g.i);
        ax.p202m7.d.a(window, true, z.d(viewFindViewById), null);
        int paddingTop = viewFindViewById.getPaddingTop();
        C1839d0.v0(viewFindViewById, new a(viewFindViewById.getLayoutParams().height, viewFindViewById, viewFindViewById.getPaddingLeft(), paddingTop, viewFindViewById.getPaddingRight()));
        this.a2 = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ax.p122h7.d<S> v3() {
        if (this.E1 == null) {
            this.E1 = (ax.p122h7.d) s0().getParcelable("DATE_SELECTOR_KEY");
        }
        return this.E1;
    }

    private static CharSequence w3(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        String[] strArrSplit = TextUtils.split(String.valueOf(charSequence), "\n");
        return strArrSplit.length > 1 ? strArrSplit[0] : charSequence;
    }

    private String x3() {
        return v3().t(z2());
    }

    private static int z3(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(ax.R6.e.b0);
        int i = m.C().Z;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(ax.R6.e.d0) * i) + ((i - 1) * resources.getDimensionPixelOffset(ax.R6.e.h0));
    }

    public final S A3() {
        return v3().y();
    }

    @Override // androidx.fragment.app.Fragment
    public final View B1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.L1 ? ax.R6.i.y : ax.R6.i.x, viewGroup);
        Context context = viewInflate.getContext();
        ax.p122h7.f fVar = this.H1;
        if (fVar != null) {
            fVar.i(context);
        }
        if (this.L1) {
            viewInflate.findViewById(ax.R6.g.z).setLayoutParams(new LinearLayout.LayoutParams(z3(context), -2));
        } else {
            viewInflate.findViewById(ax.R6.g.A).setLayoutParams(new LinearLayout.LayoutParams(z3(context), -1));
        }
        TextView textView = (TextView) viewInflate.findViewById(ax.R6.g.F);
        this.W1 = textView;
        textView.setAccessibilityLiveRegion(1);
        this.X1 = (CheckableImageButton) viewInflate.findViewById(ax.R6.g.G);
        this.V1 = (TextView) viewInflate.findViewById(ax.R6.g.J);
        C3(context);
        this.Z1 = (Button) viewInflate.findViewById(ax.R6.g.d);
        if (v3().w()) {
            this.Z1.setEnabled(true);
        } else {
            this.Z1.setEnabled(false);
        }
        this.Z1.setTag(d2);
        CharSequence charSequence = this.O1;
        if (charSequence != null) {
            this.Z1.setText(charSequence);
        } else {
            int i = this.N1;
            if (i != 0) {
                this.Z1.setText(i);
            }
        }
        CharSequence charSequence2 = this.Q1;
        if (charSequence2 != null) {
            this.Z1.setContentDescription(charSequence2);
        } else if (this.P1 != 0) {
            this.Z1.setContentDescription(getContext().getResources().getText(this.P1));
        }
        this.Z1.setOnClickListener(new View.OnClickListener() { // from class: ax.h7.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.q.I3(view);
            }
        });
        Button button = (Button) viewInflate.findViewById(ax.R6.g.a);
        button.setTag(e2);
        CharSequence charSequence3 = this.S1;
        if (charSequence3 != null) {
            button.setText(charSequence3);
        } else {
            int i2 = this.R1;
            if (i2 != 0) {
                button.setText(i2);
            }
        }
        CharSequence charSequence4 = this.U1;
        if (charSequence4 != null) {
            button.setContentDescription(charSequence4);
        } else if (this.T1 != 0) {
            button.setContentDescription(getContext().getResources().getText(this.T1));
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: ax.h7.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.q.H3(view);
            }
        });
        return viewInflate;
    }

    public void H3(View view) {
        Iterator<View.OnClickListener> it = this.A1.iterator();
        while (it.hasNext()) {
            it.next().onClick(view);
        }
        X2();
    }

    public void I3(View view) {
        Iterator<ax.p122h7.j<? super S>> it = this.z1.iterator();
        while (it.hasNext()) {
            it.next().a(A3());
        }
        X2();
    }

    void L3(String str) {
        this.W1.setContentDescription(x3());
        this.W1.setText(str);
    }

    @Override // androidx.fragment.app.e, androidx.fragment.app.Fragment
    public final void S1(Bundle bundle) {
        super.S1(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.D1);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.E1);
        com.google.android.material.datepicker.a.b bVar = new com.google.android.material.datepicker.a.b(this.G1);
        j<S> jVar = this.I1;
        m mVarJ3 = jVar == null ? null : jVar.j3();
        if (mVarJ3 != null) {
            bVar.b(mVarJ3.l0);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar.a());
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.H1);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.J1);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.K1);
        bundle.putInt("INPUT_MODE_KEY", this.M1);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.N1);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.O1);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.P1);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.Q1);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.R1);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.S1);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.T1);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.U1);
    }

    @Override // androidx.fragment.app.e, androidx.fragment.app.Fragment
    public void T1() {
        super.T1();
        Window window = h3().getWindow();
        if (this.L1) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.Y1);
            u3(window);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = P0().getDimensionPixelOffset(ax.R6.e.f0);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.Y1, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new ax.p138i7.a(h3(), rect));
        }
        K3();
    }

    @Override // androidx.fragment.app.e, androidx.fragment.app.Fragment
    public void U1() {
        this.F1.V2();
        super.U1();
    }

    @Override // androidx.fragment.app.e
    public final Dialog d3(Bundle bundle) {
        Dialog dialog = new Dialog(z2(), B3(z2()));
        Context context = dialog.getContext();
        this.L1 = D3(context);
        int i = ax.R6.c.w;
        int i2 = ax.R6.l.C;
        this.Y1 = new ax.p346v7.h(context, null, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, ax.R6.m.d3, i, i2);
        int color = typedArrayObtainStyledAttributes.getColor(ax.R6.m.e3, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.Y1.U(context);
        this.Y1.g0(ColorStateList.valueOf(color));
        this.Y1.f0(dialog.getWindow().getDecorView().getElevation());
        return dialog;
    }

    @Override // androidx.fragment.app.e, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.B1.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.e, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.C1.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) a1();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    public boolean q3(DialogInterface.OnCancelListener onCancelListener) {
        return this.B1.add(onCancelListener);
    }

    public boolean r3(View.OnClickListener onClickListener) {
        return this.A1.add(onClickListener);
    }

    public boolean s3(ax.p122h7.j<? super S> jVar) {
        return this.z1.add(jVar);
    }

    @Override // androidx.fragment.app.e, androidx.fragment.app.Fragment
    public final void x1(Bundle bundle) {
        super.x1(bundle);
        if (bundle == null) {
            bundle = s0();
        }
        this.D1 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.E1 = (ax.p122h7.d) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.G1 = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.H1 = (ax.p122h7.f) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.J1 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.K1 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.M1 = bundle.getInt("INPUT_MODE_KEY");
        this.N1 = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.O1 = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.P1 = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.Q1 = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.R1 = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.S1 = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.T1 = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.U1 = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence text = this.K1;
        if (text == null) {
            text = z2().getResources().getText(this.J1);
        }
        this.b2 = text;
        this.c2 = w3(text);
    }

    public String y3() {
        return v3().g(getContext());
    }
}
