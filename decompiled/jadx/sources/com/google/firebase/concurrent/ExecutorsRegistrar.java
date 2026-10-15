package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;
import ax.p171k8.C2135c;
import ax.p171k8.F;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.x;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"ThreadPoolCreation"})
public class ExecutorsRegistrar implements ComponentRegistrar {
    static final x<ScheduledExecutorService> a = new x<>(new ax.H8.b() { // from class: ax.l8.c
        @Override // ax.H8.b
        public final Object get() {
            return ExecutorsRegistrar.m(Executors.newFixedThreadPool(4, ExecutorsRegistrar.k("Firebase Background", 10, ExecutorsRegistrar.i())));
        }
    });
    static final x<ScheduledExecutorService> b = new x<>(new ax.H8.b() { // from class: ax.l8.d
        @Override // ax.H8.b
        public final Object get() {
            return ExecutorsRegistrar.m(Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()), ExecutorsRegistrar.k("Firebase Lite", 0, ExecutorsRegistrar.l())));
        }
    });
    static final x<ScheduledExecutorService> c = new x<>(new ax.H8.b() { // from class: ax.l8.e
        @Override // ax.H8.b
        public final Object get() {
            return ExecutorsRegistrar.m(Executors.newCachedThreadPool(ExecutorsRegistrar.j("Firebase Blocking", 11)));
        }
    });
    static final x<ScheduledExecutorService> d = new x<>(new ax.H8.b() { // from class: ax.l8.f
        @Override // ax.H8.b
        public final Object get() {
            return Executors.newSingleThreadScheduledExecutor(ExecutorsRegistrar.j("Firebase Scheduler", 0));
        }
    });

    private static StrictMode.ThreadPolicy i() {
        StrictMode.ThreadPolicy.Builder builderDetectNetwork = new StrictMode.ThreadPolicy.Builder().detectNetwork();
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            builderDetectNetwork.detectResourceMismatches();
            if (i >= 26) {
                builderDetectNetwork.detectUnbufferedIo();
            }
        }
        return builderDetectNetwork.penaltyLog().build();
    }

    private static ThreadFactory j(String str, int i) {
        return new b(str, i, null);
    }

    private static ThreadFactory k(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return new b(str, i, threadPolicy);
    }

    private static StrictMode.ThreadPolicy l() {
        return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ScheduledExecutorService m(ExecutorService executorService) {
        return new o(executorService, d.get());
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return Arrays.asList(C2135c.d(F.a(ax.p155j8.a.class, ScheduledExecutorService.class), F.a(ax.p155j8.a.class, ExecutorService.class), F.a(ax.p155j8.a.class, Executor.class)).e(new ax.p171k8.h() { // from class: ax.l8.g
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return ExecutorsRegistrar.a.get();
            }
        }).c(), C2135c.d(F.a(ax.p155j8.b.class, ScheduledExecutorService.class), F.a(ax.p155j8.b.class, ExecutorService.class), F.a(ax.p155j8.b.class, Executor.class)).e(new ax.p171k8.h() { // from class: ax.l8.h
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return ExecutorsRegistrar.c.get();
            }
        }).c(), C2135c.d(F.a(ax.p155j8.c.class, ScheduledExecutorService.class), F.a(ax.p155j8.c.class, ExecutorService.class), F.a(ax.p155j8.c.class, Executor.class)).e(new ax.p171k8.h() { // from class: ax.l8.i
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return ExecutorsRegistrar.b.get();
            }
        }).c(), C2135c.c(F.a(ax.p155j8.d.class, Executor.class)).e(new ax.p171k8.h() { // from class: ax.l8.j
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return m.INSTANCE;
            }
        }).c());
    }
}
