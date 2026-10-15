package com.alphainventor.filemanager.service;

import android.content.Context;
import android.net.Uri;
import ax.U2.f;
import ax.U2.g;
import ax.p086f3.C2017x;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p310t3.c;
import ax.p310t3.h;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6240o;
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
    private c b;
    private int c = -1;
    private CopyOnWriteArrayList<C6240o> d = new CopyOnWriteArrayList<>();
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
            c cVar = new c(this, h());
            this.b = cVar;
            cVar.u(5000, false);
        } catch (IOException e) {
            ax.p302sb.c.h().d("HTTP SERVER START FAILED").m(e).h("port:" + this.c).i();
            this.b = null;
            this.c = -1;
            throw new IOException(e);
        }
    }

    private void q() {
        c cVar = this.b;
        if (cVar != null) {
            cVar.v();
            this.b = null;
        }
        this.c = -1;
        this.e.clear();
    }

    public boolean a(String str) {
        return this.f.add(d0.t(str));
    }

    public void b(List<AbstractC6239n> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractC6239n> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(d0.p(it.next().R()));
        }
        this.f.addAll(arrayList);
    }

    public boolean c(AbstractC6239n abstractC6239n) {
        return this.f.add(d0.p(abstractC6239n.R()));
    }

    public void d(a aVar) {
        if (this.e.contains(aVar)) {
            return;
        }
        this.e.add(aVar);
    }

    public synchronized void e(C6240o c6240o) {
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
        c6240o.V();
        this.d.add(c6240o);
        g.fine("Http server operator added : " + c6240o.A().H() + ":" + c6240o.y() + ",port:" + this.c);
    }

    public synchronized C6240o g(f fVar, int i) {
        for (C6240o c6240o : this.d) {
            if (c6240o.A() == fVar && c6240o.y() == i) {
                return c6240o;
            }
        }
        return null;
    }

    public int h() {
        if (this.c < 0) {
            this.c = h.d(this.a);
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

    public void l(boolean z, K k) {
        g.fine("Keep Http Server : " + k);
        C6240o c6240oE = C2017x.e(k);
        if (z) {
            e(c6240oE);
        } else {
            o(c6240oE);
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

    public synchronized void o(C6240o c6240o) {
        if (this.b == null) {
            C2461b.f();
            return;
        }
        if (this.d.remove(c6240o)) {
            c6240o.S(false);
            g.fine("Http server operator removed : " + c6240o.A().H() + ":" + c6240o.y() + ",port:" + this.c);
        }
        if (!i()) {
            q();
        }
    }
}
