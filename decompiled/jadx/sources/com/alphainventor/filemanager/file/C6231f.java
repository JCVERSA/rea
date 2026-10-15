package com.alphainventor.filemanager.file;

import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6231f extends ax.p406z3.q<Void, Void, Void> {
    C6240o h;
    private int i;
    private int j;
    private long k;
    private long l;
    private b m;
    private List<AbstractC6239n> n;
    private Map<String, a> o;
    private boolean p;
    private c q;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.f$a */
    public static class a {
        public int a;
        public int b;
        public long c;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.f$b */
    public interface b {
        void a(int i, int i2, long j, Map<String, a> map);

        void b(int i, int i2, long j);
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.f$c */
    public enum c {
        DELETE,
        PROPERTIES
    }

    public C6231f(c cVar, C6240o c6240o, List<AbstractC6239n> list, boolean z, b bVar) {
        super(ax.z3.q.e.NORMAL);
        this.o = new HashMap();
        this.q = cVar;
        this.h = c6240o;
        this.m = bVar;
        this.n = list;
        this.p = z;
    }

    private void A(AbstractC6239n abstractC6239n, a aVar) throws C1957a {
        Stack stack = new Stack();
        stack.push(abstractC6239n);
        while (!stack.isEmpty()) {
            AbstractC6239n abstractC6239n2 = (AbstractC6239n) stack.pop();
            if (isCancelled()) {
                throw new C1957a();
            }
            boolean z = this.q == c.DELETE ? !abstractC6239n2.m() : true;
            if (abstractC6239n2.isDirectory() && z) {
                this.i++;
                aVar.a++;
                z();
                try {
                    for (AbstractC6239n abstractC6239n3 : this.h.N(abstractC6239n2)) {
                        if (!this.p || !J.k2(abstractC6239n3)) {
                            stack.push(abstractC6239n3);
                        }
                    }
                } catch (C1966j e) {
                    e.printStackTrace();
                }
            } else {
                long jP = abstractC6239n2.p();
                this.j++;
                aVar.b++;
                this.k += jP;
                aVar.c += jP;
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

    @Override // ax.p406z3.q
    protected void o() {
        this.h.S(false);
    }

    @Override // ax.p406z3.q
    protected void r() {
        this.h.V();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p406z3.q
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public Void g(Void... voidArr) {
        C6240o c6240o = this.h;
        if (c6240o != null && !c6240o.a() && this.h.A() != ax.U2.f.M0 && this.h.A() != ax.U2.f.N0) {
            ax.p302sb.c.h().g().b("DIRECTORY SCAN DISCONNECTED").h("location:" + this.h.A() + ",scanType:" + this.q).i();
        }
        try {
            for (AbstractC6239n abstractC6239n : this.n) {
                if (ax.p006a3.Q.P1() && (abstractC6239n instanceof C6249y)) {
                    try {
                        ((C6249y) abstractC6239n).v1();
                    } catch (IOException unused) {
                    }
                }
                abstractC6239n.o();
                a aVar = new a();
                A(abstractC6239n, aVar);
                this.o.put(abstractC6239n.R(), aVar);
            }
            v(new Void[0]);
            return null;
        } catch (C1957a unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p406z3.q
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public void q(Void r8) {
        this.h.S(false);
        this.m.a(this.i, this.j, this.k, this.o);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ax.p406z3.q
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void s(Void... voidArr) {
        this.m.b(this.i, this.j, this.k);
    }
}
