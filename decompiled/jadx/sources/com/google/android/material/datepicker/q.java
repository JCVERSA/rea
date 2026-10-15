package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public class q implements ax.p122h7.d<ax.p019b0.c<Long, Long>> {
    public static final Parcelable.Creator<q> CREATOR = new c();
    private String X;
    private final String Y = " ";
    private Long Z = null;
    private Long k0 = null;
    private Long l0 = null;
    private Long m0 = null;
    private SimpleDateFormat n0;
    private CharSequence q;

    class a extends f {
        final /* synthetic */ TextInputLayout o0;
        final /* synthetic */ TextInputLayout p0;
        final /* synthetic */ ax.p122h7.k q0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, com.google.android.material.datepicker.a aVar, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, ax.p122h7.k kVar) {
            super(str, dateFormat, textInputLayout, aVar);
            this.o0 = textInputLayout2;
            this.p0 = textInputLayout3;
            this.q0 = kVar;
        }

        @Override // com.google.android.material.datepicker.f
        void d() {
            q.this.l0 = null;
            q.this.n(this.o0, this.p0, this.q0);
        }

        @Override // com.google.android.material.datepicker.f
        void e(Long l) {
            q.this.l0 = l;
            q.this.n(this.o0, this.p0, this.q0);
        }
    }

    class b extends f {
        final /* synthetic */ TextInputLayout o0;
        final /* synthetic */ TextInputLayout p0;
        final /* synthetic */ ax.p122h7.k q0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, DateFormat dateFormat, TextInputLayout textInputLayout, com.google.android.material.datepicker.a aVar, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, ax.p122h7.k kVar) {
            super(str, dateFormat, textInputLayout, aVar);
            this.o0 = textInputLayout2;
            this.p0 = textInputLayout3;
            this.q0 = kVar;
        }

        @Override // com.google.android.material.datepicker.f
        void d() {
            q.this.m0 = null;
            q.this.n(this.o0, this.p0, this.q0);
        }

        @Override // com.google.android.material.datepicker.f
        void e(Long l) {
            q.this.m0 = l;
            q.this.n(this.o0, this.p0, this.q0);
        }
    }

    class c implements Parcelable.Creator<q> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public q createFromParcel(Parcel parcel) {
            q qVar = new q();
            qVar.Z = (Long) parcel.readValue(Long.class.getClassLoader());
            qVar.k0 = (Long) parcel.readValue(Long.class.getClassLoader());
            return qVar;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public q[] newArray(int i) {
            return new q[i];
        }
    }

    private void f(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.X.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !" ".contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    private boolean i(long j, long j2) {
        return j <= j2;
    }

    private void j(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.X);
        textInputLayout2.setError(" ");
    }

    private void l(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (!TextUtils.isEmpty(textInputLayout.getError())) {
            this.q = textInputLayout.getError();
        } else if (TextUtils.isEmpty(textInputLayout2.getError())) {
            this.q = null;
        } else {
            this.q = textInputLayout2.getError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, ax.p122h7.k<ax.p019b0.c<Long, Long>> kVar) {
        Long l = this.l0;
        if (l == null || this.m0 == null) {
            f(textInputLayout, textInputLayout2);
            kVar.a();
        } else if (i(l.longValue(), this.m0.longValue())) {
            this.Z = this.l0;
            this.k0 = this.m0;
            kVar.b(y());
        } else {
            j(textInputLayout, textInputLayout2);
            kVar.a();
        }
        l(textInputLayout, textInputLayout2);
    }

    @Override // ax.p122h7.d
    public void D(long j) {
        Long l = this.Z;
        if (l == null) {
            this.Z = Long.valueOf(j);
        } else if (this.k0 == null && i(l.longValue(), j)) {
            this.k0 = Long.valueOf(j);
        } else {
            this.k0 = null;
            this.Z = Long.valueOf(j);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // ax.p122h7.d
    public String g(Context context) {
        Resources resources = context.getResources();
        Long l = this.Z;
        if (l == null && this.k0 == null) {
            return resources.getString(ax.R6.k.F);
        }
        Long l2 = this.k0;
        if (l2 == null) {
            return resources.getString(ax.R6.k.C, g.c(l.longValue()));
        }
        if (l == null) {
            return resources.getString(ax.R6.k.B, g.c(l2.longValue()));
        }
        ax.p019b0.c<String, String> cVarA = g.a(l, l2);
        return resources.getString(ax.R6.k.D, cVarA.a, cVarA.b);
    }

    @Override // ax.p122h7.d
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public ax.p019b0.c<Long, Long> y() {
        return new ax.p019b0.c<>(this.Z, this.k0);
    }

    @Override // ax.p122h7.d
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public void o(ax.p019b0.c<Long, Long> cVar) {
        Long l = cVar.a;
        if (l != null && cVar.b != null) {
            ax.p019b0.g.a(i(l.longValue(), cVar.b.longValue()));
        }
        Long l2 = cVar.a;
        this.Z = l2 == null ? null : Long.valueOf(t.a(l2.longValue()));
        Long l3 = cVar.b;
        this.k0 = l3 != null ? Long.valueOf(t.a(l3.longValue())) : null;
    }

    @Override // ax.p122h7.d
    public Collection<ax.p019b0.c<Long, Long>> m() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ax.p019b0.c(this.Z, this.k0));
        return arrayList;
    }

    @Override // ax.p122h7.d
    public View q(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, com.google.android.material.datepicker.a aVar, ax.p122h7.k<ax.p019b0.c<Long, Long>> kVar) {
        View viewInflate = layoutInflater.inflate(ax.R6.i.z, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(ax.R6.g.I);
        TextInputLayout textInputLayout2 = (TextInputLayout) viewInflate.findViewById(ax.R6.g.H);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        Integer numF = ax.p106g7.a.f(viewInflate.getContext(), ax.R6.c.g);
        if (numF != null) {
            editText.setHintTextColor(numF.intValue());
            editText2.setHintTextColor(numF.intValue());
        }
        if (ax.p202m7.i.b()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.X = viewInflate.getResources().getString(ax.R6.k.x);
        SimpleDateFormat simpleDateFormatG = this.n0;
        boolean z = simpleDateFormatG != null;
        if (!z) {
            simpleDateFormatG = t.g();
        }
        SimpleDateFormat simpleDateFormat = simpleDateFormatG;
        Long l = this.Z;
        if (l != null) {
            editText.setText(simpleDateFormat.format(l));
            this.l0 = this.Z;
        }
        Long l2 = this.k0;
        if (l2 != null) {
            editText2.setText(simpleDateFormat.format(l2));
            this.m0 = this.k0;
        }
        String pattern = z ? simpleDateFormat.toPattern() : t.h(viewInflate.getResources(), simpleDateFormat);
        textInputLayout.setPlaceholderText(pattern);
        textInputLayout2.setPlaceholderText(pattern);
        editText.addTextChangedListener(new a(pattern, simpleDateFormat, textInputLayout, aVar, textInputLayout, textInputLayout2, kVar));
        editText2.addTextChangedListener(new b(pattern, simpleDateFormat, textInputLayout2, aVar, textInputLayout, textInputLayout2, kVar));
        if (!ax.p122h7.c.a(viewInflate.getContext())) {
            ax.p122h7.c.d(editText, editText2);
        }
        return viewInflate;
    }

    @Override // ax.p122h7.d
    public int s() {
        return ax.R6.k.E;
    }

    @Override // ax.p122h7.d
    public String t(Context context) {
        Resources resources = context.getResources();
        ax.p019b0.c<String, String> cVarA = g.a(this.Z, this.k0);
        String str = cVarA.a;
        String string = str == null ? resources.getString(ax.R6.k.r) : str;
        String str2 = cVarA.b;
        return resources.getString(ax.R6.k.q, string, str2 == null ? resources.getString(ax.R6.k.r) : str2);
    }

    @Override // ax.p122h7.d
    public int u(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return ax.p282r7.b.f(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(ax.R6.e.g0) ? ax.R6.c.x : ax.R6.c.v, k.class.getCanonicalName());
    }

    @Override // ax.p122h7.d
    public boolean w() {
        Long l = this.Z;
        return (l == null || this.k0 == null || !i(l.longValue(), this.k0.longValue())) ? false : true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.Z);
        parcel.writeValue(this.k0);
    }

    @Override // ax.p122h7.d
    public Collection<Long> x() {
        ArrayList arrayList = new ArrayList();
        Long l = this.Z;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.k0;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }
}
