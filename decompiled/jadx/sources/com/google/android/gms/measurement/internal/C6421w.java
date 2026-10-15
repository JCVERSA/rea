package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6421w extends AbstractC6309e3 {
    private long c;
    private String d;
    private AccountManager e;
    private Boolean f;
    private long g;

    C6421w(E2 e2) {
        super(e2);
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6309e3
    protected final boolean t() {
        Calendar calendar = Calendar.getInstance();
        this.c = TimeUnit.MINUTES.convert(calendar.get(15) + calendar.get(16), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        Locale locale2 = Locale.ENGLISH;
        this.d = language.toLowerCase(locale2) + "-" + locale.getCountry().toLowerCase(locale2);
        return false;
    }

    final long u() {
        n();
        return this.g;
    }

    public final long v() {
        p();
        return this.c;
    }

    public final String w() {
        p();
        return this.d;
    }

    final void x() {
        n();
        this.f = null;
        this.g = 0L;
    }

    final boolean y() {
        n();
        long jA = b().a();
        if (jA - this.g > 86400000) {
            this.f = null;
        }
        Boolean bool = this.f;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ax.Q.b.a(a(), "android.permission.GET_ACCOUNTS") != 0) {
            j().N().a("Permission error checking for dasher/unicorn accounts");
            this.g = jA;
            this.f = Boolean.FALSE;
            return false;
        }
        if (this.e == null) {
            this.e = AccountManager.get(a());
        }
        try {
            Account[] result = this.e.getAccountsByTypeAndFeatures("com.google", new String[]{"service_HOSTED"}, null, null).getResult();
            if (result != null && result.length > 0) {
                this.f = Boolean.TRUE;
                this.g = jA;
                return true;
            }
            Account[] result2 = this.e.getAccountsByTypeAndFeatures("com.google", new String[]{"service_uca"}, null, null).getResult();
            if (result2 != null && result2.length > 0) {
                this.f = Boolean.TRUE;
                this.g = jA;
                return true;
            }
            this.g = jA;
            this.f = Boolean.FALSE;
            return false;
        } catch (AuthenticatorException e) {
            e = e;
            j().I().b("Exception checking account types", e);
        } catch (OperationCanceledException e2) {
            e = e2;
            j().I().b("Exception checking account types", e);
        } catch (IOException e3) {
            e = e3;
            j().I().b("Exception checking account types", e);
        }
    }
}
