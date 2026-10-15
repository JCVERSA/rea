package com.alphainventor.filemanager.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import ax.p221pa.c;
import com.alphainventor.filemanager.file.AbstractC5838l;
import java.io.File;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class FileObserverService extends Service {
    private static final Logger X = Logger.getLogger("FileManager.FileObserverService");
    private String q;

    public static void a(Context context, AbstractC5838l abstractC5838l, File file) {
        try {
            Intent intent = new Intent(context, (Class<?>) FileObserverService.class);
            intent.putExtra("location_uri", abstractC5838l.Q());
            intent.putExtra("filepath", file.getAbsolutePath());
            context.startService(intent);
        } catch (IllegalStateException e) {
            c.h().g().b("CANNOT START FILE OBSERVER SERVICE").m(e).i();
        }
    }

    public static void b(Context context) {
        try {
            context.stopService(new Intent(context, (Class<?>) FileObserverService.class));
        } catch (RuntimeException unused) {
            c.h().g().b("FILE OBSERVER SERVICE STOP ERROR").i();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            stopSelf();
            return 2;
        }
        String stringExtra = intent.getStringExtra("filepath");
        String stringExtra2 = intent.getStringExtra("location_uri");
        if (stringExtra == null || stringExtra2 == null) {
            stopSelf();
            return 2;
        }
        if (!new File(stringExtra).exists()) {
            stopSelf();
            return 2;
        }
        this.q = stringExtra2;
        ax.X1.a.j().e(stringExtra, stringExtra2);
        return 2;
    }
}
