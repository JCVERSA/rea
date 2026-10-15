package com.alphainventor.filemanager;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import ax.p170m1.g;
import ax.p221pa.c;
import ax.p338y1.b;

/* JADX INFO: loaded from: classes.dex */
public class FileManagerApp extends Application implements androidx.work.a.c {

    class a implements g {
        a() {
        }

        @Override // ax.p170m1.g
        public void a(Throwable th) {
            c.i(FileManagerApp.this).g().b("WORKMANAGER INITIALIZATION ERROR").m(th).i();
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
        return bVar.a();
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        b.k(this);
        if (b.i()) {
            c.h().d("ApplicationHolder Alrady Initialized").i();
        }
        if (!ax.p338y1.c.a() || ax.p361za.a.b(this)) {
            return;
        }
        ax.p361za.a.a(this);
    }
}
