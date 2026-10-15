package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class i extends BaseAdapter {
    private static final int Z;
    private final int X;
    private final int Y;
    private final Calendar q;

    static {
        Z = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public i() {
        Calendar calendarR = t.r();
        this.q = calendarR;
        this.X = calendarR.getMaximum(7);
        this.Y = calendarR.getFirstDayOfWeek();
    }

    public i(int i) {
        Calendar calendarR = t.r();
        this.q = calendarR;
        this.X = calendarR.getMaximum(7);
        this.Y = i;
    }

    private int b(int i) {
        int i2 = i + this.Y;
        int i3 = this.X;
        return i2 > i3 ? i2 - i3 : i2;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i) {
        if (i >= this.X) {
            return null;
        }
        return Integer.valueOf(b(i));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.X;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(ax.R6.i.q, viewGroup, false);
        }
        this.q.set(7, b(i));
        textView.setText(this.q.getDisplayName(7, Z, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(ax.R6.k.s), this.q.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
