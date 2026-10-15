package com.android.ex.photo;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public interface e {

    public interface a {
        void N(Cursor cursor);
    }

    public interface b {
        boolean A(float f, float f2);

        void O(boolean z);

        void T();

        void X();

        void b(boolean z);

        boolean r(float f, float f2);

        void u();
    }

    void A(int i);

    void B(int i, b bVar);

    void b(boolean z);

    boolean c();

    void e();

    void f(c cVar, View view);

    b g(int i);

    void h(ax.F3.a aVar, Cursor cursor);

    void i(boolean z);

    void j(ax.F3.a aVar, boolean z);

    boolean k(Uri uri);

    ax.E3.c l();

    void m(ax.F3.a aVar);

    void n();

    void p(int i);

    void q(a aVar);

    void r(Uri uri, boolean z);

    boolean s();

    boolean t(Fragment fragment);

    void v(a aVar);

    boolean w(Fragment fragment);

    ax.K0.c<ax.G3.b.a> x(int i, Bundle bundle, String str);

    boolean z();
}
