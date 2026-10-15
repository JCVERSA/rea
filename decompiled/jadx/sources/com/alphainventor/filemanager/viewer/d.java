package com.alphainventor.filemanager.viewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d extends ArrayAdapter<a> {
    static List<a> X;
    LayoutInflater q;

    public static class a {
        public String a;
        public float b;

        a(String str, float f) {
            this.a = str;
            this.b = f;
        }
    }

    public d(Context context) {
        super(context, 0, b());
        this.q = LayoutInflater.from(getContext());
    }

    static List<a> b() {
        if (X == null) {
            ArrayList arrayList = new ArrayList();
            X = arrayList;
            arrayList.add(new a("0.25X", 0.25f));
            X.add(new a("0.5X", 0.5f));
            X.add(new a("0.75X", 0.75f));
            X.add(new a("1X", 1.0f));
            X.add(new a("1.25X", 1.25f));
            X.add(new a("1.5X", 1.5f));
            X.add(new a("1.75X", 1.75f));
            X.add(new a("2X", 2.0f));
        }
        return X;
    }

    public static int c(float f) {
        List<a> listB = b();
        for (int i = 0; i < listB.size(); i++) {
            if (listB.get(i).b == f) {
                return i;
            }
        }
        C2461b.f();
        return c(1.0f);
    }

    View a(int i, View view, ViewGroup viewGroup, int i2) {
        if (view == null) {
            view = this.q.inflate(i2, viewGroup, false);
        }
        ((TextView) view).setText(((a) getItem(i)).a);
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
