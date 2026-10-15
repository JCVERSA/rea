package com.alphainventor.filemanager.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import ax.U2.g;
import ax.p374x3.w;
import ax.p406z3.C;
import ax.p406z3.D;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"DefaultLocale"})
public class FtpServerService extends Service {
    private static final Logger u0 = g.a(FtpServerService.class);
    private static d v0;
    private e X;
    private ServerSocket Y;
    private f Z;
    private D k0;
    private int l0;
    private String m0;
    private boolean n0;
    private WifiManager o0;
    private int p0;
    private String q0;
    private final IBinder q = new c();
    private List<b> r0 = new ArrayList();
    private HashSet<InetAddress> s0 = new HashSet<>();
    private HashMap<InetAddress, Integer> t0 = new HashMap<>();

    private class b extends Thread {
        private boolean X;
        private String Y;
        private boolean Z;
        private Socket q;

        public b(Socket socket, boolean z, String str, boolean z2) {
            this.q = socket;
            this.X = z;
            this.Y = str;
            this.Z = z2;
        }

        public void a() {
            Socket socket = this.q;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:99:0x00dd A[EXC_TOP_SPLITTER, PHI: r9
          0x00dd: PHI (r9v3 java.io.BufferedOutputStream) = (r9v4 java.io.BufferedOutputStream), (r9v8 java.io.BufferedOutputStream) binds: [B:75:0x00fd, B:57:0x00db] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Throwable {
            BufferedReader bufferedReader;
            BufferedOutputStream bufferedOutputStream;
            SocketAddress remoteSocketAddress = this.q.getRemoteSocketAddress();
            com.alphainventor.filemanager.service.a aVar = null;
            InetAddress address = remoteSocketAddress instanceof InetSocketAddress ? ((InetSocketAddress) remoteSocketAddress).getAddress() : null;
            boolean z = address != null && FtpServerService.this.s0.contains(address);
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(this.q.getInputStream()), 8192);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(this.q.getOutputStream());
                        try {
                            if (this.Z) {
                                try {
                                    bufferedOutputStream.write("421 Too Many Connections\r\n".getBytes());
                                    bufferedOutputStream.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                com.alphainventor.filemanager.service.a aVar2 = new com.alphainventor.filemanager.service.a(FtpServerService.this.getApplicationContext(), this.q, bufferedOutputStream, this.X, this.Y);
                                try {
                                    aVar2.t();
                                    aVar2.O("220 File Manager ready \r\n");
                                    com.alphainventor.filemanager.service.a.C0544a c0544a = new com.alphainventor.filemanager.service.a.C0544a();
                                    while (true) {
                                        String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        c0544a.a();
                                        aVar2.C(line, c0544a, z);
                                        if (c0544a.c) {
                                            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(aVar2.m()), 8192);
                                            try {
                                                bufferedOutputStream = aVar2.n();
                                                bufferedReader = bufferedReader2;
                                            } catch (IOException e2) {
                                                e = e2;
                                                bufferedReader = bufferedReader2;
                                                aVar = aVar2;
                                                e.printStackTrace();
                                                if (aVar != null) {
                                                    aVar.e();
                                                }
                                                if (bufferedReader != null) {
                                                    try {
                                                        bufferedReader.close();
                                                    } catch (IOException unused) {
                                                    }
                                                }
                                                if (bufferedOutputStream != null) {
                                                    try {
                                                        bufferedOutputStream.close();
                                                    } catch (IOException unused2) {
                                                    }
                                                }
                                                a();
                                                FtpServerService.this.A(this);
                                            } catch (Throwable th) {
                                                th = th;
                                                bufferedReader = bufferedReader2;
                                                aVar = aVar2;
                                                if (aVar != null) {
                                                    aVar.e();
                                                }
                                                if (bufferedReader != null) {
                                                    try {
                                                        bufferedReader.close();
                                                    } catch (IOException unused3) {
                                                    }
                                                }
                                                if (bufferedOutputStream == null) {
                                                    throw th;
                                                }
                                                try {
                                                    bufferedOutputStream.close();
                                                    throw th;
                                                } catch (IOException unused4) {
                                                    throw th;
                                                }
                                            }
                                        }
                                        if (c0544a.a != null && FtpServerService.this.X != null) {
                                            FtpServerService.this.X.a(c0544a.a);
                                        }
                                        if (c0544a.b) {
                                            FtpServerService.this.y(address);
                                        }
                                    }
                                    aVar = aVar2;
                                } catch (IOException e3) {
                                    e = e3;
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                            if (aVar != null) {
                                aVar.e();
                            }
                            try {
                                bufferedReader.close();
                            } catch (IOException unused5) {
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                        } catch (IOException e4) {
                            e = e4;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        bufferedOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedOutputStream = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (IOException e6) {
                e = e6;
                bufferedReader = null;
                bufferedOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = null;
                bufferedOutputStream = null;
            }
            a();
            FtpServerService.this.A(this);
        }
    }

    public class c extends Binder {
        public c() {
        }

        public FtpServerService a() {
            return FtpServerService.this;
        }
    }

    class d extends Thread {
        private boolean q = false;

        d() {
        }

        void a(boolean z) {
            this.q = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                FtpServerService.this.D();
                FtpServerService.this.k0.a();
                FtpServerService.this.r();
                if (FtpServerService.this.X != null) {
                    FtpServerService.this.X.b();
                }
                while (!this.q) {
                    if (FtpServerService.this.Z != null && !FtpServerService.this.Z.isAlive()) {
                        try {
                            FtpServerService.this.Z.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        FtpServerService.this.Z = null;
                    }
                    if (FtpServerService.this.Z == null) {
                        FtpServerService ftpServerService = FtpServerService.this;
                        ftpServerService.Z = new f();
                        FtpServerService.this.Z.start();
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (!FtpServerService.this.Z.isAlive() && FtpServerService.this.o0.getWifiState() == 1) {
                        FtpServerService.this.stopSelf();
                        break;
                    }
                }
                if (FtpServerService.this.X != null) {
                    FtpServerService.this.X.c();
                }
                FtpServerService.this.z();
                FtpServerService.this.k0.c();
                FtpServerService.this.u();
            } catch (IOException e3) {
                e3.printStackTrace();
                FtpServerService.this.stopSelf();
                if (FtpServerService.this.X != null) {
                    FtpServerService.this.X.c();
                }
            }
        }
    }

    public interface e {
        void a(String str);

        void b();

        void c();
    }

    private class f extends Thread {
        private f() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Socket socketAccept = FtpServerService.this.Y.accept();
                    FtpServerService ftpServerService = FtpServerService.this;
                    b bVar = ftpServerService.new b(socketAccept, ftpServerService.n0, FtpServerService.this.m0, FtpServerService.this.r0.size() >= 30);
                    FtpServerService.this.s(bVar);
                    bVar.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void A(b bVar) {
        this.r0.remove(bVar);
        if (this.r0.size() <= 0) {
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        this.Y = serverSocket;
        serverSocket.setReuseAddress(true);
        this.Y.bind(new InetSocketAddress(this.l0));
    }

    private void E(Intent intent) {
        this.q0 = intent.getStringExtra("extra_ip_address");
        this.l0 = intent.getIntExtra("extra_port_number", 0);
        this.m0 = intent.getStringExtra("extra_password");
        if (v0 == null) {
            d dVar = new d();
            v0 = dVar;
            dVar.start();
        }
    }

    private void F() {
        e eVar = this.X;
        if (eVar != null) {
            eVar.c();
        }
        v0.a(true);
        d dVar = v0;
        if (dVar != null) {
            dVar.interrupt();
            try {
                v0.join(10000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (!v0.isAlive()) {
                v0 = null;
            }
        }
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.p0 = C.a(this, 0L, "FtpServerService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void s(b bVar) {
        try {
            if (this.r0.size() == 0) {
                startService(new Intent(this, (Class<?>) FtpServerService.class));
            }
            this.r0.add(bVar);
        } catch (Throwable th) {
            throw th;
        }
    }

    private synchronized void t() {
        for (b bVar : this.r0) {
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        ServerSocket serverSocket = this.Y;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private Notification v() {
        return w.j(this).d(this, w(this.q0, this.l0));
    }

    public static String w(String str, int i) {
        return String.format(Locale.US, "ftp://%s:%d", str, Integer.valueOf(i));
    }

    public static boolean x() {
        d dVar = v0;
        return dVar != null && dVar.isAlive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(InetAddress inetAddress) {
        Integer num = this.t0.get(inetAddress);
        Integer numValueOf = num == null ? 1 : Integer.valueOf(num.intValue() + 1);
        this.t0.put(inetAddress, numValueOf);
        if (numValueOf.intValue() >= 10) {
            this.s0.add(inetAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        C.d(this.p0);
    }

    public void B(e eVar) {
        this.X = eVar;
    }

    public void C(boolean z) {
        this.n0 = z;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        E(intent);
        try {
            startForeground(301, v());
        } catch (IllegalStateException unused) {
            ax.p302sb.c.i(this).g().b("Foreground not allowed : ftp server service").i();
        }
        return this.q;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.o0 = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.k0 = new D(this, 3, "FTP_SERVER");
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        E(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        F();
        return true;
    }
}
