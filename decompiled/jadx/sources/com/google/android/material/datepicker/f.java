package com.google.android.material.datepicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import ax.p202m7.v;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
abstract class f extends v {
    private final String X;
    private final DateFormat Y;
    private final a Z;
    private final String k0;
    private final Runnable l0;
    private Runnable m0;
    private int n0 = 0;
    private final TextInputLayout q;

    f(final String str, DateFormat dateFormat, TextInputLayout textInputLayout, a aVar) {
        this.X = str;
        this.Y = dateFormat;
        this.q = textInputLayout;
        this.Z = aVar;
        this.k0 = textInputLayout.getContext().getString(ax.R6.k.A);
        this.l0 = new Runnable() { // from class: com.google.android.material.datepicker.d
            @Override // java.lang.Runnable
            public final void run() {
                f.b(this.q, str);
            }
        };
    }

    public static /* synthetic */ void a(f fVar, long j) {
        fVar.getClass();
        fVar.q.setError(String.format(fVar.k0, fVar.g(g.c(j))));
        fVar.d();
    }

    public static /* synthetic */ void b(f fVar, String str) {
        TextInputLayout textInputLayout = fVar.q;
        DateFormat dateFormat = fVar.Y;
        Context context = textInputLayout.getContext();
        textInputLayout.setError(context.getString(ax.R6.k.u) + "\n" + String.format(context.getString(ax.R6.k.w), fVar.g(str)) + "\n" + String.format(context.getString(ax.R6.k.v), fVar.g(dateFormat.format(new Date(t.p().getTimeInMillis())))));
        fVar.d();
    }

    private Runnable c(final long j) {
        return new Runnable() { // from class: com.google.android.material.datepicker.e
            @Override // java.lang.Runnable
            public final void run() {
                f.a(this.q, j);
            }
        };
    }

    private String g(String str) {
        return str.replace(' ', (char) 160);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (!Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) && editable.length() != 0 && editable.length() < this.X.length() && editable.length() >= this.n0) {
            char cCharAt = this.X.charAt(editable.length());
            if (Character.isLetterOrDigit(cCharAt)) {
                return;
            }
            editable.append(cCharAt);
        }
    }

    @Override // ax.p202m7.v, android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.n0 = charSequence.length();
    }

    abstract void d();

    abstract void e(Long l);

    public void f(View view, Runnable runnable) {
        view.post(runnable);
    }

    @Override // ax.p202m7.v, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.q.removeCallbacks(this.l0);
        this.q.removeCallbacks(this.m0);
        this.q.setError(null);
        e(null);
        if (TextUtils.isEmpty(charSequence) || charSequence.length() < this.X.length()) {
            return;
        }
        try {
            Date date = this.Y.parse(charSequence.toString());
            this.q.setError(null);
            long time = date.getTime();
            if (this.Z.h().v(time) && this.Z.z(time)) {
                e(Long.valueOf(date.getTime()));
                return;
            }
            Runnable runnableC = c(time);
            this.m0 = runnableC;
            f(this.q, runnableC);
        } catch (ParseException unused) {
            f(this.q, this.l0);
        }
    }
}
