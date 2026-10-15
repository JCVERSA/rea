package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class E implements Parcelable.Creator<C> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ C createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        Bundle bundleA = null;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            if (ax.p281r6.b.l(iR) != 2) {
                ax.p281r6.b.x(parcel, iR);
            } else {
                bundleA = ax.p281r6.b.a(parcel, iR);
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new C(bundleA);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ C[] newArray(int i) {
        return new C[i];
    }
}
