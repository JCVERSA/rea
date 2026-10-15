package com.alphainventor.filemanager.viewer;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private int a;
    private float b;
    private final Handler c;
    private final b d;
    private boolean e;
    private boolean f;
    private boolean g;
    private MotionEvent h;
    private MotionEvent i;
    private MotionEvent j;
    private float k;
    private float l;
    private float m;
    private float n;
    private int o;
    private boolean p;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.viewer.a$a, reason: collision with other inner class name */
    private class HandlerC0551a extends Handler {
        HandlerC0551a() {
        }

        HandlerC0551a(Handler handler) {
            super(handler.getLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2) {
                a.this.d();
                return;
            }
            throw new RuntimeException("Unknown message " + message);
        }
    }

    public interface b {
        void a(MotionEvent motionEvent);
    }

    public a(Context context, float f, b bVar) {
        this(context, bVar, null, 0);
        this.o = (int) (ViewConfiguration.getLongPressTimeout() * f);
    }

    public a(Context context, b bVar, Handler handler, int i) {
        if (handler != null) {
            this.c = new HandlerC0551a(handler);
        } else {
            this.c = new HandlerC0551a();
        }
        this.d = bVar;
        f(context);
    }

    private void b() {
        this.c.removeMessages(2);
        this.e = false;
        this.g = false;
        this.f = false;
    }

    private void c() {
        this.c.removeMessages(2);
        this.g = false;
        this.f = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f = true;
        this.d.a(this.h);
    }

    private int e() {
        return this.o;
    }

    private void f(Context context) {
        if (this.d == null) {
            throw new NullPointerException("OnGestureListener must not be null");
        }
        this.p = true;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.b = viewConfiguration.getScaledAmbiguousGestureMultiplier();
        } else if (i >= 29) {
            this.b = ViewConfiguration.getAmbiguousGestureMultiplier();
        } else {
            this.b = 1.0f;
        }
        this.a = scaledTouchSlop * scaledTouchSlop;
    }

    public boolean g(MotionEvent motionEvent) {
        long jUptimeMillis;
        int iE;
        int action = motionEvent.getAction();
        MotionEvent motionEvent2 = this.i;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.i = MotionEvent.obtain(motionEvent);
        int i = action & 255;
        boolean z = i == 6;
        int actionIndex = z ? motionEvent.getActionIndex() : -1;
        int pointerCount = motionEvent.getPointerCount();
        float x = 0.0f;
        float y = 0.0f;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            if (actionIndex != i2) {
                x += motionEvent.getX(i2);
                y += motionEvent.getY(i2);
            }
        }
        float f = z ? pointerCount - 1 : pointerCount;
        float f2 = x / f;
        float f3 = y / f;
        if (i == 0) {
            this.k = f2;
            this.m = f2;
            this.l = f3;
            this.n = f3;
            MotionEvent motionEvent3 = this.h;
            if (motionEvent3 != null) {
                motionEvent3.recycle();
            }
            this.h = MotionEvent.obtain(motionEvent);
            this.g = true;
            this.e = true;
            this.f = false;
            return false;
        }
        if (i == 1) {
            this.e = false;
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            if (this.f) {
                this.f = false;
            }
            MotionEvent motionEvent4 = this.j;
            if (motionEvent4 != null) {
                motionEvent4.recycle();
            }
            this.j = motionEventObtain;
            this.c.removeMessages(2);
            return false;
        }
        if (i != 2) {
            if (i == 3) {
                b();
                return false;
            }
            if (i == 5) {
                this.k = f2;
                this.m = f2;
                this.l = f3;
                this.n = f3;
                if (pointerCount != 2) {
                    c();
                    return false;
                }
                if (this.p) {
                    this.c.removeMessages(2);
                    MotionEvent motionEvent5 = this.h;
                    if (motionEvent5 != null) {
                        jUptimeMillis = motionEvent5.getDownTime();
                        iE = e();
                    } else {
                        jUptimeMillis = SystemClock.uptimeMillis();
                        iE = e();
                    }
                    long j = jUptimeMillis + ((long) iE);
                    Handler handler = this.c;
                    handler.sendMessageAtTime(handler.obtainMessage(2, 0, 0), j);
                    return false;
                }
            } else if (i == 6) {
                this.k = f2;
                this.m = f2;
                this.l = f3;
                this.n = f3;
                if (pointerCount == 2) {
                    c();
                    return false;
                }
            }
        } else if (!this.f && this.g) {
            int i3 = (int) (f2 - this.m);
            int i4 = (int) (f3 - this.n);
            int i5 = (i3 * i3) + (i4 * i4);
            int i6 = this.a;
            if (Build.VERSION.SDK_INT >= 29) {
                int classification = motionEvent.getClassification();
                boolean zHasMessages = this.c.hasMessages(2);
                boolean z2 = classification == 1;
                if (zHasMessages && z2) {
                    if (i5 > i6) {
                        this.c.removeMessages(2);
                        long jE = e();
                        Handler handler2 = this.c;
                        handler2.sendMessageAtTime(handler2.obtainMessage(2, 0, 0), motionEvent.getDownTime() + ((long) (jE * this.b)));
                    }
                    float f4 = i6;
                    float f5 = this.b;
                    i6 = (int) (f4 * f5 * f5);
                }
            }
            if (i5 > i6) {
                this.k = f2;
                this.l = f3;
                this.g = false;
                this.c.removeMessages(2);
            }
        }
        return false;
    }
}
