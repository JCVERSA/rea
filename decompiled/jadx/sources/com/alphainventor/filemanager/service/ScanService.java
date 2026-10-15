package com.alphainventor.filemanager.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.SystemClock;
import ax.E1.P;
import ax.J1.G;
import ax.X1.d;
import ax.p017b2.w;
import ax.p045d2.n;
import ax.p045d2.y;
import ax.p221pa.c;
import ax.p338y1.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class ScanService extends Service {
    private static final Logger Z = g.a(ScanService.class);
    private HashMap<Integer, Boolean> X = new HashMap<>();
    private Handler Y = new Handler();
    private int q;

    static class a {
        String a;
        boolean b;
        boolean c;

        a(String str, boolean z, boolean z2) {
            this.a = str;
            this.b = z;
            this.c = z2;
        }
    }

    class b extends n<Void, Void, Void> {
        d[] h;
        Context i;
        int j;
        final CountDownLatch k;
        final CountDownLatch l;
        long m;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                ScanService.this.stopSelf(bVar.j);
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.service.ScanService$b$b, reason: collision with other inner class name */
        class C0509b implements MediaScannerConnection.MediaScannerConnectionClient {
            final List<a> a;
            MediaScannerConnection b;
            int c;
            a d;

            C0509b(List<a> list) {
                this.a = list;
            }

            void a() {
                if (this.c >= this.a.size()) {
                    this.b.disconnect();
                    b.this.l.countDown();
                } else {
                    a aVar = this.a.get(this.c);
                    this.d = aVar;
                    this.b.scanFile(aVar.a, null);
                    this.c++;
                }
            }

            @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
            public void onMediaScannerConnected() {
                if (b.this.l.getCount() == 0) {
                    long jUptimeMillis = (SystemClock.uptimeMillis() - b.this.m) / 1000;
                    c.h().g().b("MediaScanner connected after timeout").h("delay:" + jUptimeMillis).i();
                }
                b.this.k.countDown();
                a();
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str, Uri uri) {
                a aVar;
                if (P.b0() && str != null && uri != null && (aVar = this.d) != null && aVar.b && str.equals(aVar.a)) {
                    G.j(b.this.i, uri);
                }
                a();
            }
        }

        b(Context context, d[] dVarArr, int i) {
            super(n.f.FILE_SCAN);
            this.k = new CountDownLatch(1);
            this.l = new CountDownLatch(1);
            this.i = context;
            this.h = dVarArr;
            this.j = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            ArrayList<d> arrayList = new ArrayList(Arrays.asList(this.h));
            G.b(this.i, arrayList, null);
            G.c(this.i, arrayList, false, null);
            ArrayList arrayList2 = new ArrayList();
            for (d dVar : arrayList) {
                if (!dVar.q && dVar.l0) {
                    arrayList2.add(new a(dVar.X, dVar.Y, dVar.Z));
                }
            }
            if (arrayList2.size() > 0) {
                C0509b c0509b = new C0509b(arrayList2);
                MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(ScanService.this.getApplicationContext(), c0509b);
                c0509b.b = mediaScannerConnection;
                mediaScannerConnection.connect();
                this.m = SystemClock.uptimeMillis();
                try {
                    if (!this.k.await(25L, TimeUnit.SECONDS)) {
                        this.l.countDown();
                    }
                } catch (InterruptedException unused) {
                }
            } else {
                this.l.countDown();
            }
            try {
                this.l.await(300L, TimeUnit.SECONDS);
            } catch (InterruptedException unused2) {
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Void r4) {
            Boolean bool = (Boolean) ScanService.this.X.get(Integer.valueOf(this.j));
            if (bool == null || bool.booleanValue()) {
                ScanService.this.Y.postDelayed(new a(), 1000L);
            } else {
                ScanService.this.stopSelf(this.j);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        ax.p338y1.b.f(this, true);
        super.onCreate();
        this.q = y.a(this, 600000L, "ScanService");
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForeground(true);
        w.j(this).a(401);
        y.d(this.q);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            startForeground(401, w.j(this).g());
        } catch (IllegalStateException unused) {
        }
        if (intent == null) {
            stopSelf();
            return 2;
        }
        try {
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("PENDING_SCAN_ARRAY");
            if (parcelableArrayExtra == null) {
                stopSelf();
                return 2;
            }
            d[] dVarArr = (d[]) Arrays.copyOf(parcelableArrayExtra, parcelableArrayExtra.length, d[].class);
            this.X.put(Integer.valueOf(i2), Boolean.valueOf(intent.getBooleanExtra("HAS_FOLLOWING_LIST", false)));
            new b(this, dVarArr, i2).h(new Void[0]);
            return 3;
        } catch (Exception e) {
            ax.p045d2.b.e("pending scan array parcelable error");
            e.printStackTrace();
            stopSelf();
            return 2;
        }
    }
}
