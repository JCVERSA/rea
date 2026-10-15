package com.google.firebase.sessions;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import ax.W8.f;
import ax.p335uc.g;
import ax.p335uc.l;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class SessionLifecycleService extends Service {
    public static final a Z = new a(null);
    private b X;
    private Messenger Y;
    private final HandlerThread q = new HandlerThread("FirebaseSessions_HandlerThread");

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    public static final class b extends Handler {
        private boolean a;
        private long b;
        private final ArrayList<Messenger> c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Looper looper) {
            super(looper);
            l.f(looper, "looper");
            this.c = new ArrayList<>();
        }

        private final void a() {
            StringBuilder sb = new StringBuilder();
            sb.append("Broadcasting new session: ");
            c.b bVar = c.f;
            sb.append(bVar.a().c());
            Log.d("SessionLifecycleService", sb.toString());
            com.google.firebase.sessions.b.a.a().a(bVar.a().c());
            ArrayList arrayList = new ArrayList(this.c);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                Messenger messenger = (Messenger) obj;
                l.e(messenger, "it");
                f(messenger);
            }
        }

        private final void b(Message message) {
            Log.d("SessionLifecycleService", "Activity backgrounding at " + message.getWhen());
            this.b = message.getWhen();
        }

        private final void c(Message message) {
            this.c.add(message.replyTo);
            Messenger messenger = message.replyTo;
            l.e(messenger, "msg.replyTo");
            f(messenger);
            Log.d("SessionLifecycleService", "Client " + message.replyTo + " bound at " + message.getWhen() + ". Clients: " + this.c.size());
        }

        private final void d(Message message) {
            Log.d("SessionLifecycleService", "Activity foregrounding at " + message.getWhen() + '.');
            if (!this.a) {
                Log.d("SessionLifecycleService", "Cold start detected.");
                this.a = true;
                g();
            } else if (e(message.getWhen())) {
                Log.d("SessionLifecycleService", "Session too long in background. Creating new session.");
                g();
            }
            this.b = message.getWhen();
        }

        private final boolean e(long j) {
            return j - this.b > ax.Dc.a.v(f.c.c().c());
        }

        private final void f(Messenger messenger) {
            if (this.a) {
                h(messenger, c.f.a().c().b());
                return;
            }
            String strA = com.google.firebase.sessions.a.a.a().a();
            Log.d("SessionLifecycleService", "App has not yet foregrounded. Using previously stored session: " + strA);
            if (strA != null) {
                h(messenger, strA);
            }
        }

        private final void g() {
            c.b bVar = c.f;
            bVar.a().a();
            Log.d("SessionLifecycleService", "Generated new session " + bVar.a().c().b());
            a();
            com.google.firebase.sessions.a.a.a().b(bVar.a().c().b());
        }

        private final void h(Messenger messenger, String str) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("SessionUpdateExtra", str);
                Message messageObtain = Message.obtain(null, 3, 0, 0);
                messageObtain.setData(bundle);
                messenger.send(messageObtain);
            } catch (DeadObjectException unused) {
                Log.d("SessionLifecycleService", "Removing dead client from list: " + messenger);
                this.c.remove(messenger);
            } catch (Exception e) {
                Log.w("SessionLifecycleService", "Unable to push new session to " + messenger + '.', e);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            l.f(message, "msg");
            if (this.b > message.getWhen()) {
                Log.d("SessionLifecycleService", "Ignoring old message from " + message.getWhen() + " which is older than " + this.b + '.');
                return;
            }
            int i = message.what;
            if (i == 1) {
                d(message);
                return;
            }
            if (i == 2) {
                b(message);
                return;
            }
            if (i == 4) {
                c(message);
                return;
            }
            Log.w("SessionLifecycleService", "Received unexpected event from the SessionLifecycleClient: " + message);
            super.handleMessage(message);
        }
    }

    private final Messenger a(Intent intent) {
        return Build.VERSION.SDK_INT >= 33 ? (Messenger) intent.getParcelableExtra("ClientCallbackMessenger", Messenger.class) : (Messenger) intent.getParcelableExtra("ClientCallbackMessenger");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            Log.d("SessionLifecycleService", "Service bound with null intent. Ignoring.");
            return null;
        }
        Log.d("SessionLifecycleService", "Service bound to new client on process " + intent.getAction());
        Messenger messengerA = a(intent);
        if (messengerA != null) {
            Message messageObtain = Message.obtain(null, 4, 0, 0);
            messageObtain.replyTo = messengerA;
            b bVar = this.X;
            if (bVar != null) {
                bVar.sendMessage(messageObtain);
            }
        }
        Messenger messenger = this.Y;
        if (messenger != null) {
            return messenger.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.q.start();
        Looper looper = this.q.getLooper();
        l.e(looper, "handlerThread.looper");
        this.X = new b(looper);
        this.Y = new Messenger(this.X);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.q.quit();
    }
}
