package com.alphainventor.filemanager;

import android.content.Context;
import android.util.Log;
import ax.U2.d;
import ax.p302sb.c;
import google.open;

/* JADX INFO: loaded from: classes.dex */
public class FileManagerApp extends open implements androidx.work.a.c {

    class a implements ax.p019b0.a<Throwable> {
        a() {
        }

        @Override // ax.p019b0.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d.c("work manager init", th);
        }
    }

    class b implements ax.p019b0.a<Throwable> {
        b() {
        }

        @Override // ax.p019b0.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d.c("work manager scheduleing", th);
        }
    }

    public FileManagerApp() {
        ApplicationReporter.init(null);
    }

    public static void b(String str) {
        Log.e("FileManager", str);
    }

    @Override // androidx.work.a.c
    public androidx.work.a a() {
        androidx.work.a.b bVar = new androidx.work.a.b();
        bVar.c(5000, 7000);
        bVar.b(new a());
        bVar.d(new b());
        return bVar.a();
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ax.U2.b.k(this);
        if (ax.U2.b.i()) {
            c.h().d("ApplicationHolder Alrady Initialized").i();
        }
        ax.U2.c.a();
    }
}
