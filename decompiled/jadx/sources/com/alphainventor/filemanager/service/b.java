package com.alphainventor.filemanager.service;

import android.content.Context;
import android.net.Uri;
import ax.J1.F;
import ax.J1.V;
import ax.J1.r;
import ax.X1.e;
import ax.p221pa.c;
import ax.p338y1.f;
import ax.p338y1.g;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5839m;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private static final Logger g = g.a(b.class);
    private static b h;
    private Context a;
    private ax.X1.b b;
    private int c = -1;
    private CopyOnWriteArrayList<C5839m> d = new CopyOnWriteArrayList<>();
    private CopyOnWriteArraySet<a> e = new CopyOnWriteArraySet<>();
    private CopyOnWriteArraySet<String> f = new CopyOnWriteArraySet<>();

    interface a {
        void a(int i);
    }

    private b(Context context) {
        this.a = context.getApplicationContext();
    }

    public static b f(Context context) {
        if (h == null) {
            h = new b(context);
        }
        return h;
    }

    public static boolean k(Context context, Uri uri) {
        return "http".equals(uri.getScheme()) && "127.0.0.1".equals(uri.getHost()) && f(context).h() == uri.getPort();
    }

    private void p() throws IOException {
        try {
            ax.X1.b bVar = new ax.X1.b(this, h());
            this.b = bVar;
            bVar.u(5000, false);
        } catch (IOException e) {
            c.h().d("HTTP SERVER START FAILED").m(e).h("port:" + this.c).i();
            this.b = null;
            this.c = -1;
            throw new IOException(e);
        }
    }

    private void q() {
        ax.X1.b bVar = this.b;
        if (bVar != null) {
            bVar.v();
            this.b = null;
        }
        this.c = -1;
        this.e.clear();
    }

    public boolean a(String str) {
        return this.f.add(V.t(str));
    }

    public void b(List<AbstractC5838l> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractC5838l> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(V.p(it.next().Q()));
        }
        this.f.addAll(arrayList);
    }

    public boolean c(AbstractC5838l abstractC5838l) {
        return this.f.add(V.p(abstractC5838l.Q()));
    }

    public void d(a aVar) {
        if (this.e.contains(aVar)) {
            return;
        }
        this.e.add(aVar);
    }

    public synchronized void e(C5839m c5839m) {
        if (this.b == null) {
            try {
                try {
                    p();
                } catch (IOException unused) {
                }
            } catch (IOException unused2) {
                p();
            }
        }
        c5839m.S();
        this.d.add(c5839m);
        g.fine("Http server operator added : " + c5839m.y().H() + ":" + c5839m.w() + ",port:" + this.c);
    }

    public synchronized C5839m g(f fVar, int i) {
        for (C5839m c5839m : this.d) {
            if (c5839m.y() == fVar && c5839m.w() == i) {
                return c5839m;
            }
        }
        return null;
    }

    public int h() {
        if (this.c < 0) {
            this.c = e.c(this.a);
        }
        return this.c;
    }

    public synchronized boolean i() {
        return this.d.size() != 0;
    }

    public boolean j(String str) {
        Iterator<String> it = this.f.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    public void l(boolean z, F f) {
        g.fine("Keep Http Server : " + f);
        C5839m c5839mD = r.d(f);
        if (z) {
            e(c5839mD);
        } else {
            o(c5839mD);
        }
    }

    public void m(int i) {
        g.fine("runner count changed : " + i);
        Iterator<a> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().a(i);
        }
    }

    public void n(a aVar) {
        this.e.remove(aVar);
    }

    public synchronized void o(C5839m c5839m) {
        if (this.b == null) {
            ax.p045d2.b.f();
            return;
        }
        if (this.d.remove(c5839m)) {
            c5839m.P(false);
            g.fine("Http server operator removed : " + c5839m.y().H() + ":" + c5839m.w() + ",port:" + this.c);
        }
        if (!i()) {
            q();
        }
    }
}
