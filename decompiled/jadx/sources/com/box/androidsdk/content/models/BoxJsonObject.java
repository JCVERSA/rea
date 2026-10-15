package com.box.androidsdk.content.models;

import androidx.gridlayout.widget.GridLayout;
import ax.U4.d;
import ax.U4.g;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxJsonObject extends BoxObject {
    private static final long serialVersionUID = 7174936367401884790L;
    private CacheMap mCacheMap;

    class CacheMap implements Serializable {
        private d mJsonObject;
        private transient HashMap<String, Object> q = new LinkedHashMap();

        public CacheMap(d dVar) {
            this.mJsonObject = dVar;
        }

        public Boolean a(String str) {
            g gVarG = g(str);
            if (gVarG == null) {
                return null;
            }
            return Boolean.valueOf(gVarG.f());
        }

        public Date b(String str) {
            g gVarG = g(str);
            if (gVarG != null && !gVarG.o()) {
                Date date = (Date) this.q.get(str);
                if (date != null) {
                    return date;
                }
                try {
                    Date dateC = ax.N3.a.c(gVarG.j());
                    this.q.put(str, dateC);
                    return dateC;
                } catch (ParseException e) {
                    ax.N3.b.b("BoxJsonObject", "getAsDate", e);
                }
            }
            return null;
        }

        public Double c(String str) {
            g gVarG = g(str);
            if (gVarG == null || gVarG.o()) {
                return null;
            }
            return Double.valueOf(gVarG.g());
        }

        public ax.U4.a d(String str) {
            g gVarG = g(str);
            if (gVarG == null || gVarG.o()) {
                return null;
            }
            return gVarG.e();
        }

        public <T extends BoxJsonObject> T e(b<T> bVar, String str) {
            if (this.q.get(str) != null) {
                return (T) this.q.get(str);
            }
            g gVarG = g(str);
            if (gVarG == null || gVarG.o() || !gVarG.p()) {
                return null;
            }
            T t = (T) bVar.a(gVarG.i());
            this.q.put(str, t);
            return t;
        }

        public boolean equals(Object obj) {
            return this.mJsonObject.equals(((CacheMap) obj).mJsonObject);
        }

        public <T extends BoxJsonObject> ArrayList<T> f(b<T> bVar, String str) {
            if (this.q.get(str) != null) {
                return (ArrayList) this.q.get(str);
            }
            g gVarG = g(str);
            if (gVarG != null && !gVarG.n() && gVarG.p()) {
                GridLayout.k kVar = (ArrayList<T>) new ArrayList(1);
                kVar.add(bVar.a(gVarG.i()));
                this.q.put(str, kVar);
                return kVar;
            }
            ax.U4.a aVarD = d(str);
            if (aVarD == null) {
                return null;
            }
            GridLayout.k kVar2 = (ArrayList<T>) new ArrayList(aVarD.size());
            Iterator<g> it = aVarD.iterator();
            while (it.hasNext()) {
                kVar2.add(bVar.a(it.next().i()));
            }
            this.q.put(str, kVar2);
            return kVar2;
        }

        public g g(String str) {
            return this.mJsonObject.E(str);
        }

        public String h(String str) {
            g gVarG = g(str);
            if (gVarG == null || gVarG.o()) {
                return null;
            }
            return gVarG.j();
        }

        public int hashCode() {
            return this.mJsonObject.hashCode();
        }

        public List<String> i() {
            return this.mJsonObject.J();
        }

        public void j(String str, BoxJsonObject boxJsonObject) {
            this.mJsonObject.Q(str, boxJsonObject.A());
            if (this.q.containsKey(str)) {
                this.q.remove(str);
            }
        }

        public void k(String str, Long l) {
            this.mJsonObject.P(str, l.longValue());
            if (this.q.containsKey(str)) {
                this.q.remove(str);
            }
        }

        public void l(String str, String str2) {
            this.mJsonObject.R(str, str2);
            if (this.q.containsKey(str)) {
                this.q.remove(str);
            }
        }

        public String m() {
            return this.mJsonObject.toString();
        }

        public void n(Writer writer) throws IOException {
            this.mJsonObject.z(writer);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    class a<T> implements b<T> {
        final /* synthetic */ Class a;

        a(Class cls) {
            this.a = cls;
        }

        /* JADX WARN: Incorrect return type in method signature: (Lax/U4/d;)TT; */
        @Override // com.box.androidsdk.content.models.BoxJsonObject.b
        public BoxJsonObject a(d dVar) {
            try {
                BoxJsonObject boxJsonObject = (BoxJsonObject) this.a.newInstance();
                boxJsonObject.i(dVar);
                return boxJsonObject;
            } catch (IllegalAccessException e) {
                ax.N3.b.b("BoxJsonObject", "getBoxJsonObjectCreator " + this.a, e);
                return null;
            } catch (InstantiationException e2) {
                ax.N3.b.b("BoxJsonObject", "getBoxJsonObjectCreator " + this.a, e2);
                return null;
            }
        }
    }

    public interface b<E extends BoxJsonObject> {
        E a(d dVar);
    }

    public BoxJsonObject() {
        i(new d());
    }

    public BoxJsonObject(d dVar) {
        i(dVar);
    }

    public static <T extends BoxJsonObject> b<T> m(Class<T> cls) {
        return new a(cls);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        i(d.K(new BufferedReader(new InputStreamReader(objectInputStream))));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(objectOutputStream));
        this.mCacheMap.n(bufferedWriter);
        bufferedWriter.flush();
    }

    public d A() {
        return d.M(z());
    }

    public boolean equals(Object obj) {
        if (obj instanceof BoxJsonObject) {
            return this.mCacheMap.equals(((BoxJsonObject) obj).mCacheMap);
        }
        return false;
    }

    public int hashCode() {
        return this.mCacheMap.hashCode();
    }

    public void i(d dVar) {
        this.mCacheMap = new CacheMap(dVar);
    }

    public void j(String str) {
        i(d.M(str));
    }

    public List<String> n() {
        return this.mCacheMap.i();
    }

    protected Boolean o(String str) {
        return this.mCacheMap.a(str);
    }

    protected Date p(String str) {
        return this.mCacheMap.b(str);
    }

    protected <T extends BoxJsonObject> T q(b<T> bVar, String str) {
        return (T) this.mCacheMap.e(bVar, str);
    }

    protected <T extends BoxJsonObject> ArrayList<T> s(b<T> bVar, String str) {
        return this.mCacheMap.f(bVar, str);
    }

    protected Long t(String str) {
        if (this.mCacheMap.c(str) == null) {
            return null;
        }
        return Long.valueOf(this.mCacheMap.c(str).longValue());
    }

    protected String u(String str) {
        return this.mCacheMap.h(str);
    }

    public g v(String str) {
        g gVarG = this.mCacheMap.g(str);
        if (gVarG == null) {
            return null;
        }
        return g.t(gVarG.toString());
    }

    protected void w(String str, BoxJsonObject boxJsonObject) {
        this.mCacheMap.j(str, boxJsonObject);
    }

    protected void x(String str, Long l) {
        this.mCacheMap.k(str, l);
    }

    protected void y(String str, String str2) {
        this.mCacheMap.l(str, str2);
    }

    public String z() {
        return this.mCacheMap.m();
    }
}
