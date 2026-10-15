package com.alphainventor.filemanager.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import ax.p017b2.w;
import ax.p045d2.y;
import ax.p045d2.z;
import ax.p338y1.g;
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
    private static final Logger r0 = g.a(FtpServerService.class);
    private static d s0;
    private e X;
    private ServerSocket Y;
    private f Z;
    private z h0;
    private int i0;
    private String j0;
    private boolean k0;
    private WifiManager l0;
    private int m0;
    private String n0;
    private final IBinder q = new c();
    private List<b> o0 = new ArrayList();
    private HashSet<InetAddress> p0 = new HashSet<>();
    private HashMap<InetAddress, Integer> q0 = new HashMap<>();

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

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Throwable {
            BufferedReader bufferedReader;
            BufferedOutputStream bufferedOutputStream;
            SocketAddress remoteSocketAddress = this.q.getRemoteSocketAddress();
            com.alphainventor.filemanager.service.a aVar = null;
            InetAddress address = remoteSocketAddress instanceof InetSocketAddress ? ((InetSocketAddress) remoteSocketAddress).getAddress() : null;
            boolean z = address != null && FtpServerService.this.p0.contains(address);
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
                                    aVar2.l();
                                    aVar2.A("220 File Manager ready \r\n");
                                    com.alphainventor.filemanager.service.a.C0510a c0510a = new com.alphainventor.filemanager.service.a.C0510a();
                                    while (true) {
                                        String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        c0510a.a();
                                        aVar2.s(line, c0510a, z);
                                        if (c0510a.a != null && FtpServerService.this.X != null) {
                                            FtpServerService.this.X.a(c0510a.a);
                                        }
                                        if (c0510a.b) {
                                            FtpServerService.this.y(address);
                                        }
                                    }
                                    aVar = aVar2;
                                } catch (IOException e2) {
                                    e = e2;
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
                                    }
                                    a();
                                    FtpServerService.this.A(this);
                                } catch (Throwable th) {
                                    th = th;
                                    aVar = aVar2;
                                    if (aVar != null) {
                                        aVar.e();
                                    }
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException unused2) {
                                        }
                                    }
                                    if (bufferedOutputStream == null) {
                                        throw th;
                                    }
                                    try {
                                        bufferedOutputStream.close();
                                        throw th;
                                    } catch (IOException unused3) {
                                        throw th;
                                    }
                                }
                            }
                            if (aVar != null) {
                                aVar.e();
                            }
                            try {
                                bufferedReader.close();
                            } catch (IOException unused4) {
                            }
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        bufferedOutputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e5) {
                e = e5;
                bufferedReader = null;
                bufferedOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
                bufferedOutputStream = null;
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException unused5) {
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
                FtpServerService.this.h0.a();
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
                    if (!FtpServerService.this.Z.isAlive() && FtpServerService.this.l0.getWifiState() == 1) {
                        FtpServerService.this.stopSelf();
                        break;
                    }
                }
                if (FtpServerService.this.X != null) {
                    FtpServerService.this.X.c();
                }
                FtpServerService.this.z();
                FtpServerService.this.h0.c();
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
                    b bVar = ftpServerService.new b(socketAccept, ftpServerService.k0, FtpServerService.this.j0, FtpServerService.this.o0.size() >= 30);
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
        this.o0.remove(bVar);
        if (this.o0.size() <= 0) {
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        this.Y = serverSocket;
        serverSocket.setReuseAddress(true);
        this.Y.bind(new InetSocketAddress(this.i0));
    }

    private void E(Intent intent) {
        this.n0 = intent.getStringExtra("extra_ip_address");
        this.i0 = intent.getIntExtra("extra_port_number", 0);
        this.j0 = intent.getStringExtra("extra_password");
        if (s0 == null) {
            d dVar = new d();
            s0 = dVar;
            dVar.start();
        }
    }

    private void F() {
        e eVar = this.X;
        if (eVar != null) {
            eVar.c();
        }
        s0.a(true);
        d dVar = s0;
        if (dVar != null) {
            dVar.interrupt();
            try {
                s0.join(10000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (!s0.isAlive()) {
                s0 = null;
            }
        }
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.m0 = y.a(this, 0L, "FtpServerService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void s(b bVar) {
        try {
            if (this.o0.size() == 0) {
                startService(new Intent(this, (Class<?>) FtpServerService.class));
            }
            this.o0.add(bVar);
        } catch (Throwable th) {
            throw th;
        }
    }

    private synchronized void t() {
        for (b bVar : this.o0) {
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
        return w.j(this).d(w(this.n0, this.i0));
    }

    public static String w(String str, int i) {
        return String.format(Locale.US, "ftp://%s:%d", str, Integer.valueOf(i));
    }

    public static boolean x() {
        d dVar = s0;
        return dVar != null && dVar.isAlive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(InetAddress inetAddress) {
        Integer num = this.q0.get(inetAddress);
        Integer numValueOf = num == null ? 1 : Integer.valueOf(num.intValue() + 1);
        this.q0.put(inetAddress, numValueOf);
        if (numValueOf.intValue() >= 10) {
            this.p0.add(inetAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        y.d(this.m0);
    }

    public void B(e eVar) {
        this.X = eVar;
    }

    public void C(boolean z) {
        this.k0 = z;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        E(intent);
        try {
            startForeground(301, v());
        } catch (IllegalStateException unused) {
            ax.p221pa.c.i(this).g().b("Foreground not allowed : ftp server service").i();
        }
        return this.q;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.l0 = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.h0 = new z(this, 3, "FTP_SERVER");
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
