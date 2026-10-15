package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public class f {
    private final Set<ax.P8.c> a;
    private final g b;
    private final d c;
    private final ax.p075e8.f d;
    private final ax.I8.e e;
    private final b f;
    private final Context g;
    private final String h;
    private final e i;
    private final ScheduledExecutorService j;

    public f(ax.p075e8.f fVar, ax.I8.e eVar, d dVar, b bVar, Context context, String str, e eVar2, ScheduledExecutorService scheduledExecutorService) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.a = linkedHashSet;
        this.b = new g(fVar, eVar, dVar, bVar, context, str, linkedHashSet, eVar2, scheduledExecutorService);
        this.d = fVar;
        this.c = dVar;
        this.e = eVar;
        this.f = bVar;
        this.g = context;
        this.h = str;
        this.i = eVar2;
        this.j = scheduledExecutorService;
    }

    private synchronized void a() {
        if (!this.a.isEmpty()) {
            this.b.A();
        }
    }

    public synchronized void b(boolean z) {
        this.b.x(z);
        if (!z) {
            a();
        }
    }
}
