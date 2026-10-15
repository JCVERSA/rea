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
import ax.U2.d;
import ax.U2.g;
import ax.p006a3.Q;
import ax.p086f3.M;
import ax.p302sb.c;
import ax.p310t3.f;
import ax.p374x3.w;
import ax.p406z3.C;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class ScanService extends Service {
    private static final Logger k0 = g.a(ScanService.class);
    private HashMap<Integer, Boolean> X = new HashMap<>();
    private Handler Y = new Handler();
    private long Z;
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

    class b extends q<Void, Void, Void> {
        f[] h;
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
        class C0543b implements MediaScannerConnection.MediaScannerConnectionClient {
            final List<a> a;
            MediaScannerConnection b;
            int c;
            a d;

            C0543b(List<a> list) {
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
                if (this.b.isConnected()) {
                    if (b.this.l.getCount() == 0) {
                        long jUptimeMillis = (SystemClock.uptimeMillis() - b.this.m) / 1000;
                        c.h().g().b("MediaScanner connected after timeout").h("delay:" + jUptimeMillis).i();
                    }
                    b.this.k.countDown();
                    a();
                }
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str, Uri uri) {
                a aVar;
                if (Q.g0() && str != null && uri != null && (aVar = this.d) != null && aVar.b && str.equals(aVar.a)) {
                    M.l(b.this.i, uri);
                }
                a();
            }
        }

        b(Context context, f[] fVarArr, int i) {
            super(q.e.FILE_SCAN);
            this.k = new CountDownLatch(1);
            this.l = new CountDownLatch(1);
            this.i = context;
            this.h = fVarArr;
            this.j = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Void g(Void... voidArr) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.h));
            M.b(this.i, arrayList, null);
            int i = 0;
            M.c(this.i, arrayList, false, null);
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                f fVar = (f) obj;
                if (!fVar.q && fVar.r0) {
                    arrayList2.add(new a(fVar.X, fVar.Y, fVar.Z));
                }
            }
            if (arrayList2.size() > 0) {
                C0543b c0543b = new C0543b(arrayList2);
                MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(ScanService.this.getApplicationContext(), c0543b);
                c0543b.b = mediaScannerConnection;
                mediaScannerConnection.connect();
                this.m = SystemClock.uptimeMillis();
                try {
                    if (!this.k.await(Q.S() ? 7L : 25L, TimeUnit.SECONDS)) {
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
        @Override // ax.p406z3.q
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
        ax.U2.b.f(this, true);
        super.onCreate();
        this.q = C.a(this, 600000L, "ScanService");
        this.Z = System.currentTimeMillis();
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForeground(true);
        w.j(this).a(401);
        C.d(this.q);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            startForeground(401, w.j(this).g(this));
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
            f[] fVarArr = (f[]) Arrays.copyOf(parcelableArrayExtra, parcelableArrayExtra.length, f[].class);
            this.X.put(Integer.valueOf(i2), Boolean.valueOf(intent.getBooleanExtra("HAS_FOLLOWING_LIST", false)));
            new b(this, fVarArr, i2).h(new Void[0]);
            return 3;
        } catch (Exception e) {
            C2461b.e("pending scan array parcelable error");
            e.printStackTrace();
            stopSelf();
            return 2;
        }
    }

    public void onTimeout(int i, int i2) {
        super.onTimeout(i, i2);
        d.c("Scan service timeout", new Exception("ScanServiceTimeout :" + (System.currentTimeMillis() - this.Z)));
        stopForeground(1);
    }
}
