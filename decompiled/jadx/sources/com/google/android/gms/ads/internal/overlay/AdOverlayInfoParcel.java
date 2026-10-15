package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import ax.P5.v;
import ax.Q5.A;
import ax.Q5.InterfaceC1391a;
import ax.S5.InterfaceC1526d;
import ax.S5.l;
import ax.S5.z;
import ax.p409z6.C2654Ff;
import ax.p409z6.C4665lr;
import ax.p409z6.InterfaceC2480Ai;
import ax.p409z6.InterfaceC2552Ci;
import ax.p409z6.InterfaceC2877Ln;
import ax.p409z6.InterfaceC3197Ut;
import ax.p409z6.SG;
import ax.p409z6.YC;
import com.google.android.gms.common.internal.ReflectedParcelable;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes3.dex */
public final class AdOverlayInfoParcel extends ax.p281r6.a implements ReflectedParcelable {
    public static final Parcelable.Creator<AdOverlayInfoParcel> CREATOR = new a();
    private static final AtomicLong E0 = new AtomicLong(0);
    private static final ConcurrentHashMap F0 = new ConcurrentHashMap();
    public final SG A0;
    public final InterfaceC2877Ln B0;
    public final boolean C0;
    public final long D0;
    public final InterfaceC1391a X;
    public final z Y;
    public final InterfaceC3197Ut Z;
    public final InterfaceC2552Ci k0;
    public final String l0;
    public final boolean m0;
    public final String n0;
    public final InterfaceC1526d o0;
    public final int p0;
    public final l q;
    public final int q0;
    public final String r0;
    public final ax.U5.a s0;
    public final String t0;
    public final ax.P5.l u0;
    public final InterfaceC2480Ai v0;
    public final String w0;
    public final String x0;
    public final String y0;
    public final YC z0;

    public AdOverlayInfoParcel(InterfaceC1391a interfaceC1391a, z zVar, InterfaceC1526d interfaceC1526d, InterfaceC3197Ut interfaceC3197Ut, int i, ax.U5.a aVar, String str, ax.P5.l lVar, String str2, String str3, String str4, YC yc, InterfaceC2877Ln interfaceC2877Ln, String str5) {
        this.q = null;
        this.X = null;
        this.Y = zVar;
        this.Z = interfaceC3197Ut;
        this.v0 = null;
        this.k0 = null;
        this.m0 = false;
        if (((Boolean) A.c().a(C2654Ff.T0)).booleanValue()) {
            this.l0 = null;
            this.n0 = null;
        } else {
            this.l0 = str2;
            this.n0 = str3;
        }
        this.o0 = null;
        this.p0 = i;
        this.q0 = 1;
        this.r0 = null;
        this.s0 = aVar;
        this.t0 = str;
        this.u0 = lVar;
        this.w0 = str5;
        this.x0 = null;
        this.y0 = str4;
        this.z0 = yc;
        this.A0 = null;
        this.B0 = interfaceC2877Ln;
        this.C0 = false;
        this.D0 = E0.getAndIncrement();
    }

    public AdOverlayInfoParcel(InterfaceC1391a interfaceC1391a, z zVar, InterfaceC1526d interfaceC1526d, InterfaceC3197Ut interfaceC3197Ut, boolean z, int i, ax.U5.a aVar, SG sg, InterfaceC2877Ln interfaceC2877Ln) {
        this.q = null;
        this.X = interfaceC1391a;
        this.Y = zVar;
        this.Z = interfaceC3197Ut;
        this.v0 = null;
        this.k0 = null;
        this.l0 = null;
        this.m0 = z;
        this.n0 = null;
        this.o0 = interfaceC1526d;
        this.p0 = i;
        this.q0 = 2;
        this.r0 = null;
        this.s0 = aVar;
        this.t0 = null;
        this.u0 = null;
        this.w0 = null;
        this.x0 = null;
        this.y0 = null;
        this.z0 = null;
        this.A0 = sg;
        this.B0 = interfaceC2877Ln;
        this.C0 = false;
        this.D0 = E0.getAndIncrement();
    }

    public AdOverlayInfoParcel(InterfaceC1391a interfaceC1391a, z zVar, InterfaceC2480Ai interfaceC2480Ai, InterfaceC2552Ci interfaceC2552Ci, InterfaceC1526d interfaceC1526d, InterfaceC3197Ut interfaceC3197Ut, boolean z, int i, String str, ax.U5.a aVar, SG sg, InterfaceC2877Ln interfaceC2877Ln, boolean z2) {
        this.q = null;
        this.X = interfaceC1391a;
        this.Y = zVar;
        this.Z = interfaceC3197Ut;
        this.v0 = interfaceC2480Ai;
        this.k0 = interfaceC2552Ci;
        this.l0 = null;
        this.m0 = z;
        this.n0 = null;
        this.o0 = interfaceC1526d;
        this.p0 = i;
        this.q0 = 3;
        this.r0 = str;
        this.s0 = aVar;
        this.t0 = null;
        this.u0 = null;
        this.w0 = null;
        this.x0 = null;
        this.y0 = null;
        this.z0 = null;
        this.A0 = sg;
        this.B0 = interfaceC2877Ln;
        this.C0 = z2;
        this.D0 = E0.getAndIncrement();
    }

    public AdOverlayInfoParcel(InterfaceC1391a interfaceC1391a, z zVar, InterfaceC2480Ai interfaceC2480Ai, InterfaceC2552Ci interfaceC2552Ci, InterfaceC1526d interfaceC1526d, InterfaceC3197Ut interfaceC3197Ut, boolean z, int i, String str, String str2, ax.U5.a aVar, SG sg, InterfaceC2877Ln interfaceC2877Ln) {
        this.q = null;
        this.X = interfaceC1391a;
        this.Y = zVar;
        this.Z = interfaceC3197Ut;
        this.v0 = interfaceC2480Ai;
        this.k0 = interfaceC2552Ci;
        this.l0 = str2;
        this.m0 = z;
        this.n0 = str;
        this.o0 = interfaceC1526d;
        this.p0 = i;
        this.q0 = 3;
        this.r0 = null;
        this.s0 = aVar;
        this.t0 = null;
        this.u0 = null;
        this.w0 = null;
        this.x0 = null;
        this.y0 = null;
        this.z0 = null;
        this.A0 = sg;
        this.B0 = interfaceC2877Ln;
        this.C0 = false;
        this.D0 = E0.getAndIncrement();
    }

    AdOverlayInfoParcel(l lVar, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i, int i2, String str3, ax.U5.a aVar, String str4, ax.P5.l lVar2, IBinder iBinder6, String str5, String str6, String str7, IBinder iBinder7, IBinder iBinder8, IBinder iBinder9, boolean z2, long j) {
        this.q = lVar;
        this.l0 = str;
        this.m0 = z;
        this.n0 = str2;
        this.p0 = i;
        this.q0 = i2;
        this.r0 = str3;
        this.s0 = aVar;
        this.t0 = str4;
        this.u0 = lVar2;
        this.w0 = str5;
        this.x0 = str6;
        this.y0 = str7;
        this.C0 = z2;
        this.D0 = j;
        if (!((Boolean) A.c().a(C2654Ff.Mc)).booleanValue()) {
            this.X = (InterfaceC1391a) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder));
            this.Y = (z) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder2));
            this.Z = (InterfaceC3197Ut) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder3));
            this.v0 = (InterfaceC2480Ai) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder6));
            this.k0 = (InterfaceC2552Ci) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder4));
            this.o0 = (InterfaceC1526d) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder5));
            this.z0 = (YC) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder7));
            this.A0 = (SG) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder8));
            this.B0 = (InterfaceC2877Ln) ax.p377x6.b.e1(ax.p377x6.a.AbstractBinderC0505a.P0(iBinder9));
            return;
        }
        b bVar = (b) F0.remove(Long.valueOf(j));
        if (bVar == null) {
            throw new NullPointerException("AdOverlayObjects is null");
        }
        this.X = bVar.a;
        this.Y = bVar.b;
        this.Z = bVar.c;
        this.v0 = bVar.d;
        this.k0 = bVar.e;
        this.z0 = bVar.g;
        this.A0 = bVar.h;
        this.B0 = bVar.i;
        this.o0 = bVar.f;
        bVar.j.cancel(false);
    }

    public AdOverlayInfoParcel(l lVar, InterfaceC1391a interfaceC1391a, z zVar, InterfaceC1526d interfaceC1526d, ax.U5.a aVar, InterfaceC3197Ut interfaceC3197Ut, SG sg, String str) {
        this.q = lVar;
        this.X = interfaceC1391a;
        this.Y = zVar;
        this.Z = interfaceC3197Ut;
        this.v0 = null;
        this.k0 = null;
        this.l0 = null;
        this.m0 = false;
        this.n0 = null;
        this.o0 = interfaceC1526d;
        this.p0 = -1;
        this.q0 = 4;
        this.r0 = null;
        this.s0 = aVar;
        this.t0 = null;
        this.u0 = null;
        this.w0 = str;
        this.x0 = null;
        this.y0 = null;
        this.z0 = null;
        this.A0 = sg;
        this.B0 = null;
        this.C0 = false;
        this.D0 = E0.getAndIncrement();
    }

    public AdOverlayInfoParcel(z zVar, InterfaceC3197Ut interfaceC3197Ut, int i, ax.U5.a aVar) {
        this.Y = zVar;
        this.Z = interfaceC3197Ut;
        this.p0 = 1;
        this.s0 = aVar;
        this.q = null;
        this.X = null;
        this.v0 = null;
        this.k0 = null;
        this.l0 = null;
        this.m0 = false;
        this.n0 = null;
        this.o0 = null;
        this.q0 = 1;
        this.r0 = null;
        this.t0 = null;
        this.u0 = null;
        this.w0 = null;
        this.x0 = null;
        this.y0 = null;
        this.z0 = null;
        this.A0 = null;
        this.B0 = null;
        this.C0 = false;
        this.D0 = E0.getAndIncrement();
    }

    public AdOverlayInfoParcel(InterfaceC3197Ut interfaceC3197Ut, ax.U5.a aVar, String str, String str2, int i, InterfaceC2877Ln interfaceC2877Ln) {
        this.q = null;
        this.X = null;
        this.Y = null;
        this.Z = interfaceC3197Ut;
        this.v0 = null;
        this.k0 = null;
        this.l0 = null;
        this.m0 = false;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 14;
        this.q0 = 5;
        this.r0 = null;
        this.s0 = aVar;
        this.t0 = null;
        this.u0 = null;
        this.w0 = str;
        this.x0 = str2;
        this.y0 = null;
        this.z0 = null;
        this.A0 = null;
        this.B0 = interfaceC2877Ln;
        this.C0 = false;
        this.D0 = E0.getAndIncrement();
    }

    public static AdOverlayInfoParcel j(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            if (!((Boolean) A.c().a(C2654Ff.Mc)).booleanValue()) {
                return null;
            }
            v.s().x(e, "AdOverlayInfoParcel.getFromIntent");
            return null;
        }
    }

    private static final IBinder z(Object obj) {
        if (((Boolean) A.c().a(C2654Ff.Mc)).booleanValue()) {
            return null;
        }
        return ax.p377x6.b.N2(obj).asBinder();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.p(parcel, 2, this.q, i, false);
        ax.p281r6.c.j(parcel, 3, z(this.X), false);
        ax.p281r6.c.j(parcel, 4, z(this.Y), false);
        ax.p281r6.c.j(parcel, 5, z(this.Z), false);
        ax.p281r6.c.j(parcel, 6, z(this.k0), false);
        ax.p281r6.c.q(parcel, 7, this.l0, false);
        ax.p281r6.c.c(parcel, 8, this.m0);
        ax.p281r6.c.q(parcel, 9, this.n0, false);
        ax.p281r6.c.j(parcel, 10, z(this.o0), false);
        ax.p281r6.c.k(parcel, 11, this.p0);
        ax.p281r6.c.k(parcel, 12, this.q0);
        ax.p281r6.c.q(parcel, 13, this.r0, false);
        ax.p281r6.c.p(parcel, 14, this.s0, i, false);
        ax.p281r6.c.q(parcel, 16, this.t0, false);
        ax.p281r6.c.p(parcel, 17, this.u0, i, false);
        ax.p281r6.c.j(parcel, 18, z(this.v0), false);
        ax.p281r6.c.q(parcel, 19, this.w0, false);
        ax.p281r6.c.q(parcel, 24, this.x0, false);
        ax.p281r6.c.q(parcel, 25, this.y0, false);
        ax.p281r6.c.j(parcel, 26, z(this.z0), false);
        ax.p281r6.c.j(parcel, 27, z(this.A0), false);
        ax.p281r6.c.j(parcel, 28, z(this.B0), false);
        ax.p281r6.c.c(parcel, 29, this.C0);
        ax.p281r6.c.n(parcel, 30, this.D0);
        ax.p281r6.c.b(parcel, iA);
        if (((Boolean) A.c().a(C2654Ff.Mc)).booleanValue()) {
            F0.put(Long.valueOf(this.D0), new b(this.X, this.Y, this.Z, this.v0, this.k0, this.o0, this.z0, this.A0, this.B0, C4665lr.d.schedule(new c(this.D0), ((Integer) A.c().a(C2654Ff.Oc)).intValue(), TimeUnit.SECONDS)));
        }
    }
}
