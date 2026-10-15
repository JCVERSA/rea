package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;
import java.net.URL;
import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.d2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6301d2 implements Runnable {
    private final byte[] X;
    private final InterfaceC6294c2 Y;
    private final String Z;
    private final Map<String, String> k0;
    private final /* synthetic */ Y1 l0;
    private final URL q;

    public RunnableC6301d2(Y1 y1, String str, URL url, byte[] bArr, Map<String, String> map, InterfaceC6294c2 interfaceC6294c2) {
        this.l0 = y1;
        C2254p.f(str);
        C2254p.l(url);
        C2254p.l(interfaceC6294c2);
        this.q = url;
        this.X = bArr;
        this.Y = interfaceC6294c2;
        this.Z = str;
        this.k0 = map;
    }

    /* JADX WARN: Code duplicated, block: B:55:0x0118  */
    /* JADX WARN: Code duplicated, block: B:64:0x014d  */
    /* JADX WARN: Code duplicated, block: B:67:0x0133 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.google.android.gms.measurement.internal.b2.<init>(java.lang.String, com.google.android.gms.measurement.internal.c2, int, java.lang.Throwable, byte[], java.util.Map, ax.M6.i):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:309)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:224)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:93)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    @Override // java.lang.Runnable
    public final void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.RunnableC6301d2.run():void");
    }
}
