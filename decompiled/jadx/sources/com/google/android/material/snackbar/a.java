package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
class a {
    private static a e;
    private final Object a = new Object();
    private final Handler b = new Handler(Looper.getMainLooper(), new C0581a());
    private c c;
    private c d;

    /* JADX INFO: renamed from: com.google.android.material.snackbar.a$a, reason: collision with other inner class name */
    class C0581a implements Handler.Callback {
        C0581a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            a.this.d((c) message.obj);
            return true;
        }
    }

    interface b {
        void a(int i);

        void c();
    }

    private static class c {
        final WeakReference<b> a;
        int b;
        boolean c;

        c(int i, b bVar) {
            this.a = new WeakReference<>(bVar);
            this.b = i;
        }

        boolean a(b bVar) {
            return bVar != null && this.a.get() == bVar;
        }
    }

    private a() {
    }

    private boolean a(c cVar, int i) {
        b bVar = cVar.a.get();
        if (bVar == null) {
            return false;
        }
        this.b.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    static a c() {
        if (e == null) {
            e = new a();
        }
        return e;
    }

    private boolean g(b bVar) {
        c cVar = this.c;
        return cVar != null && cVar.a(bVar);
    }

    private boolean h(b bVar) {
        c cVar = this.d;
        return cVar != null && cVar.a(bVar);
    }

    private void m(c cVar) {
        int i = cVar.b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.b.removeCallbacksAndMessages(cVar);
        Handler handler = this.b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    private void o() {
        c cVar = this.d;
        if (cVar != null) {
            this.c = cVar;
            this.d = null;
            b bVar = cVar.a.get();
            if (bVar != null) {
                bVar.c();
            } else {
                this.c = null;
            }
        }
    }

    public void b(b bVar, int i) {
        synchronized (this.a) {
            try {
                if (g(bVar)) {
                    a(this.c, i);
                } else if (h(bVar)) {
                    a(this.d, i);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void d(c cVar) {
        synchronized (this.a) {
            try {
                if (this.c == cVar || this.d == cVar) {
                    a(cVar, 2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean e(b bVar) {
        boolean zG;
        synchronized (this.a) {
            zG = g(bVar);
        }
        return zG;
    }

    public boolean f(b bVar) {
        boolean z;
        synchronized (this.a) {
            try {
                z = g(bVar) || h(bVar);
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public void i(b bVar) {
        synchronized (this.a) {
            try {
                if (g(bVar)) {
                    this.c = null;
                    if (this.d != null) {
                        o();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void j(b bVar) {
        synchronized (this.a) {
            try {
                if (g(bVar)) {
                    m(this.c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void k(b bVar) {
        synchronized (this.a) {
            try {
                if (g(bVar)) {
                    c cVar = this.c;
                    if (!cVar.c) {
                        cVar.c = true;
                        this.b.removeCallbacksAndMessages(cVar);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void l(b bVar) {
        synchronized (this.a) {
            try {
                if (g(bVar)) {
                    c cVar = this.c;
                    if (cVar.c) {
                        cVar.c = false;
                        m(cVar);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void n(int i, b bVar) {
        synchronized (this.a) {
            try {
                if (g(bVar)) {
                    c cVar = this.c;
                    cVar.b = i;
                    this.b.removeCallbacksAndMessages(cVar);
                    m(this.c);
                    return;
                }
                if (h(bVar)) {
                    this.d.b = i;
                } else {
                    this.d = new c(i, bVar);
                }
                c cVar2 = this.c;
                if (cVar2 == null || !a(cVar2, 4)) {
                    this.c = null;
                    o();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
