package com.alphainventor.filemanager.shizuku;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Keep;
import ax.p406z3.r;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShizukuUserService extends a.AbstractBinderC0545a {
    @Keep
    public ShizukuUserService() {
    }

    @Keep
    public ShizukuUserService(Context context) {
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean E2(String str, long j) throws RemoteException {
        return b.p(str, j);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean H4(String str, String str2) throws RemoteException {
        return b.o(str, str2);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public List<ax.p326u3.a> N4(String str) throws RemoteException {
        return b.m(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean R7(String str) throws RemoteException {
        return b.n(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean S(String str) throws RemoteException {
        return b.d(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ParcelFileDescriptor X6(String str, boolean z) throws RemoteException {
        return b.k(str, z);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ax.p326u3.a a1(String str) throws RemoteException {
        return b.f(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ax.p326u3.a d4(String str) throws RemoteException {
        return b.h(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    @Keep
    public void destroy() throws RemoteException {
        Log.i("ShizukuUserService", "destroy");
        System.exit(0);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public int f7(String str, boolean z) throws RemoteException {
        return b.j(str, z);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ParcelFileDescriptor i3(String str) throws RemoteException {
        return b.l(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public boolean p6(String str) throws RemoteException {
        return b.b(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public ParcelFileDescriptor r3(String str) throws RemoteException {
        return b.i(str);
    }

    @Override // com.alphainventor.filemanager.shizuku.a
    public List<String> t3(String str, long j) throws RemoteException {
        try {
            return r.h(str, j);
        } catch (Exception unused) {
            return null;
        }
    }
}
