package com.google.android.gms.ads.internal.client;

import android.content.Context;
import ax.Q5.AbstractBinderC1442r0;
import ax.Q5.C1449t1;
import ax.p409z6.BinderC3364Zl;
import ax.p409z6.InterfaceC3786dm;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* JADX INFO: loaded from: classes3.dex */
public class LiteSdkInfo extends AbstractBinderC1442r0 {
    public LiteSdkInfo(Context context) {
    }

    @Override // ax.Q5.InterfaceC1445s0
    public InterfaceC3786dm getAdapterCreator() {
        return new BinderC3364Zl();
    }

    @Override // ax.Q5.InterfaceC1445s0
    public C1449t1 getLiteSdkVersion() {
        return new C1449t1(ModuleDescriptor.MODULE_VERSION, 244410000, "23.6.0");
    }
}
