package com.alphainventor.filemanager.shizuku;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import ax.p409z6.C3772df;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0545a extends Binder implements a {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.a$a$a, reason: collision with other inner class name */
        private static class C0546a implements a {
            private IBinder q;

            C0546a(IBinder iBinder) {
                this.q = iBinder;
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean E2(String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    this.q.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean H4(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    this.q.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public List<ax.p326u3.a> N4(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ax.p326u3.a.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean R7(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean S(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public ParcelFileDescriptor X6(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.q.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ParcelFileDescriptor) b.d(parcelObtain2, ParcelFileDescriptor.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public ax.p326u3.a a1(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ax.p326u3.a) b.d(parcelObtain2, ax.p326u3.a.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.q;
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public ax.p326u3.a d4(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ax.p326u3.a) b.d(parcelObtain2, ax.p326u3.a.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public int f7(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.q.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public ParcelFileDescriptor i3(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ParcelFileDescriptor) b.d(parcelObtain2, ParcelFileDescriptor.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean p6(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public ParcelFileDescriptor r3(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ParcelFileDescriptor) b.d(parcelObtain2, ParcelFileDescriptor.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0545a() {
            attachInterface(this, "com.alphainventor.filemanager.shizuku.IShizukuUserService");
        }

        public static a M0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.alphainventor.filemanager.shizuku.IShizukuUserService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0546a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.alphainventor.filemanager.shizuku.IShizukuUserService");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                return true;
            }
            if (i == 3) {
                List<String> listT3 = t3(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeStringList(listT3);
            } else if (i == 23) {
                ParcelFileDescriptor parcelFileDescriptorI3 = i3(parcel.readString());
                parcel2.writeNoException();
                b.f(parcel2, parcelFileDescriptorI3, 1);
            } else if (i != 16777115) {
                switch (i) {
                    case 11:
                        List<ax.p326u3.a> listN4 = N4(parcel.readString());
                        parcel2.writeNoException();
                        b.e(parcel2, listN4, 1);
                        break;
                    case 12:
                        ax.p326u3.a aVarA1 = a1(parcel.readString());
                        parcel2.writeNoException();
                        b.f(parcel2, aVarA1, 1);
                        break;
                    case 13:
                        ax.p326u3.a aVarD4 = d4(parcel.readString());
                        parcel2.writeNoException();
                        b.f(parcel2, aVarD4, 1);
                        break;
                    case 14:
                        int iF7 = f7(parcel.readString(), parcel.readInt() != 0);
                        parcel2.writeNoException();
                        parcel2.writeInt(iF7);
                        break;
                    case 15:
                        boolean zP6 = p6(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zP6 ? 1 : 0);
                        break;
                    case 16:
                        boolean zR7 = R7(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zR7 ? 1 : 0);
                        break;
                    case 17:
                        boolean zS = S(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zS ? 1 : 0);
                        break;
                    case 18:
                        boolean zE2 = E2(parcel.readString(), parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeInt(zE2 ? 1 : 0);
                        break;
                    case 19:
                        boolean zH4 = H4(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zH4 ? 1 : 0);
                        break;
                    case 20:
                        ParcelFileDescriptor parcelFileDescriptorX6 = X6(parcel.readString(), parcel.readInt() != 0);
                        parcel2.writeNoException();
                        b.f(parcel2, parcelFileDescriptorX6, 1);
                        break;
                    case C3772df.zzm /* 21 */:
                        ParcelFileDescriptor parcelFileDescriptorR3 = r3(parcel.readString());
                        parcel2.writeNoException();
                        b.f(parcel2, parcelFileDescriptorR3, 1);
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                destroy();
                parcel2.writeNoException();
            }
            return true;
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T d(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void e(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                f(parcel, list.get(i2), i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void f(Parcel parcel, T t, int i) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            }
        }
    }

    boolean E2(String str, long j) throws RemoteException;

    boolean H4(String str, String str2) throws RemoteException;

    List<ax.p326u3.a> N4(String str) throws RemoteException;

    boolean R7(String str) throws RemoteException;

    boolean S(String str) throws RemoteException;

    ParcelFileDescriptor X6(String str, boolean z) throws RemoteException;

    ax.p326u3.a a1(String str) throws RemoteException;

    ax.p326u3.a d4(String str) throws RemoteException;

    void destroy() throws RemoteException;

    int f7(String str, boolean z) throws RemoteException;

    ParcelFileDescriptor i3(String str) throws RemoteException;

    boolean p6(String str) throws RemoteException;

    ParcelFileDescriptor r3(String str) throws RemoteException;

    List<String> t3(String str, long j) throws RemoteException;
}
