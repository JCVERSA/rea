package com.alphainventor.filemanager.file;

import ax.I1.C0710a;
import ax.I1.C0718i;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5831e extends ax.p045d2.n<Void, Void, Void> {
    C5839m h;
    private int i;
    private int j;
    private long k;
    private long l;
    private b m;
    private List<AbstractC5838l> n;
    private Map<String, a> o;
    private boolean p;
    private c q;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.e$a */
    public static class a {
        public int a;
        public int b;
        public long c;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.e$b */
    public interface b {
        void a(int i, int i2, long j, Map<String, a> map);

        void b(int i, int i2, long j);
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.e$c */
    public enum c {
        DELETE,
        PROPERTIES
    }

    public C5831e(c cVar, C5839m c5839m, List<AbstractC5838l> list, boolean z, b bVar) {
        super(ax.d2.n.f.NORMAL);
        this.o = new HashMap();
        this.q = cVar;
        this.h = c5839m;
        this.m = bVar;
        this.n = list;
        this.p = z;
    }

    private void A(AbstractC5838l abstractC5838l, a aVar) throws C0710a {
        Stack stack = new Stack();
        stack.push(abstractC5838l);
        while (!stack.isEmpty()) {
            AbstractC5838l abstractC5838l2 = (AbstractC5838l) stack.pop();
            if (isCancelled()) {
                throw new C0710a();
            }
            boolean z = this.q == c.DELETE ? !abstractC5838l2.m() : true;
            if (abstractC5838l2.isDirectory() && z) {
                this.i++;
                aVar.a++;
                z();
                try {
                    for (AbstractC5838l abstractC5838l3 : this.h.g1(abstractC5838l2)) {
                        if (!this.p || !E.b2(abstractC5838l3)) {
                            stack.push(abstractC5838l3);
                        }
                    }
                } catch (C0718i e) {
                    e.printStackTrace();
                }
            } else {
                long jO = abstractC5838l2.o();
                this.j++;
                aVar.b++;
                this.k += jO;
                aVar.c += jO;
                z();
            }
        }
    }

    private void z() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.l > 100) {
            this.l = jCurrentTimeMillis;
            v(new Void[0]);
        }
    }

    @Override // ax.p045d2.n
    protected void o() {
        this.h.P(false);
    }

    @Override // ax.p045d2.n
    protected void r() {
        this.h.S();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p045d2.n
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public Void g(Void... voidArr) {
        C5839m c5839m = this.h;
        if (c5839m != null && !c5839m.a() && this.h.y() != ax.p338y1.f.J0 && this.h.y() != ax.p338y1.f.K0) {
            ax.p221pa.c.h().g().b("DIRECTORY SCAN DISCONNECTED").h("location:" + this.h.y() + ",scanType:" + this.q).i();
        }
        try {
            for (AbstractC5838l abstractC5838l : this.n) {
                if (ax.E1.P.z1() && (abstractC5838l instanceof u)) {
                    try {
                        ((u) abstractC5838l).i1();
                    } catch (IOException unused) {
                    }
                }
                abstractC5838l.n();
                a aVar = new a();
                A(abstractC5838l, aVar);
                this.o.put(abstractC5838l.Q(), aVar);
            }
            v(new Void[0]);
            return null;
        } catch (C0710a unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p045d2.n
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public void q(Void r8) {
        this.h.P(false);
        this.m.a(this.i, this.j, this.k, this.o);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p045d2.n
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void s(Void... voidArr) {
        this.m.b(this.i, this.j, this.k);
    }
}
