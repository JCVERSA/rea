package com.alphainventor.filemanager.shizuku;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Keep;
import ax.p045d2.o;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShizukuUserService extends a.AbstractBinderC0511a {
    @Keep
    public ShizukuUserService() {
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ParcelFileDescriptor C4(String str, boolean z, long j) throws RemoteException {
        return b.k(str, z, j);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean C7(String str) throws RemoteException {
        return b.n(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean D2(long j) throws RemoteException {
        return b.l(j);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean D4(String str, String str2) throws RemoteException {
        return b.q(str, str2);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public int G6(String str, boolean z) throws RemoteException {
        return b.j(str, z);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ParcelFileDescriptor J2(String str, long j, long j2) throws RemoteException {
        return b.i(str, j, j2);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public List<ax.Y1.a> K4(String str) throws RemoteException {
        return b.m(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean L5(String str) throws RemoteException {
        return b.b(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean O(String str) throws RemoteException {
        return b.d(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ax.Y1.a V0(String str) throws RemoteException {
        return b.f(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ax.Y1.a d4(String str) throws RemoteException {
        return b.h(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    @Keep
    public void destroy() throws RemoteException {
        Log.i("ShizukuUserService", "destroy");
        System.exit(0);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public List<String> h3(String str, long j) throws RemoteException {
        try {
            return o.h(str, j);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean x2(String str, long j) throws RemoteException {
        return b.r(str, j);
    }

    @Keep
    public ShizukuUserService(Context context) {
    }
}
