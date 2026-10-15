package com.alphainventor.filemanager.shizuku;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import ax.P5.C1452Re;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0511a extends Binder implements a {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.a$a$a, reason: collision with other inner class name */
        private static class C0512a implements a {
            private IBinder q;

            C0512a(IBinder iBinder) {
                this.q = iBinder;
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public ParcelFileDescriptor C4(String str, boolean z, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeLong(j);
                    this.q.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ParcelFileDescriptor) b.d(parcelObtain2, ParcelFileDescriptor.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean C7(String str) throws RemoteException {
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
            public boolean D2(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeLong(j);
                    this.q.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean D4(String str, String str2) throws RemoteException {
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
            public int G6(String str, boolean z) throws RemoteException {
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
            public ParcelFileDescriptor J2(String str, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    this.q.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ParcelFileDescriptor) b.d(parcelObtain2, ParcelFileDescriptor.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public List<ax.Y1.a> K4(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ax.Y1.a.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean L5(String str) throws RemoteException {
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
            public boolean O(String str) throws RemoteException {
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
            public ax.Y1.a V0(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ax.Y1.a) b.d(parcelObtain2, ax.Y1.a.CREATOR);
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
            public ax.Y1.a d4(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alphainventor.filemanager.shizuku.IShizukuUserService");
                    parcelObtain.writeString(str);
                    this.q.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ax.Y1.a) b.d(parcelObtain2, ax.Y1.a.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.alphainventor.filemanager.shizuku.a
            public boolean x2(String str, long j) throws RemoteException {
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
        }

        public AbstractBinderC0511a() {
            attachInterface(this, "com.alphainventor.filemanager.shizuku.IShizukuUserService");
        }

        public static a F0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.alphainventor.filemanager.shizuku.IShizukuUserService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0512a(iBinder) : (a) iInterfaceQueryLocalInterface;
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
                List<String> listH3 = h3(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeStringList(listH3);
            } else if (i != 16777115) {
                switch (i) {
                    case 11:
                        List<ax.Y1.a> listK4 = K4(parcel.readString());
                        parcel2.writeNoException();
                        b.e(parcel2, listK4, 1);
                        break;
                    case 12:
                        ax.Y1.a aVarV0 = V0(parcel.readString());
                        parcel2.writeNoException();
                        b.f(parcel2, aVarV0, 1);
                        break;
                    case 13:
                        ax.Y1.a aVarD4 = d4(parcel.readString());
                        parcel2.writeNoException();
                        b.f(parcel2, aVarD4, 1);
                        break;
                    case 14:
                        int iG6 = G6(parcel.readString(), parcel.readInt() != 0);
                        parcel2.writeNoException();
                        parcel2.writeInt(iG6);
                        break;
                    case 15:
                        boolean zL5 = L5(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zL5 ? 1 : 0);
                        break;
                    case 16:
                        boolean zC7 = C7(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zC7 ? 1 : 0);
                        break;
                    case 17:
                        boolean zO = O(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zO ? 1 : 0);
                        break;
                    case 18:
                        boolean zX2 = x2(parcel.readString(), parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeInt(zX2 ? 1 : 0);
                        break;
                    case 19:
                        boolean zD4 = D4(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(zD4 ? 1 : 0);
                        break;
                    case 20:
                        ParcelFileDescriptor parcelFileDescriptorC4 = C4(parcel.readString(), parcel.readInt() != 0, parcel.readLong());
                        parcel2.writeNoException();
                        b.f(parcel2, parcelFileDescriptorC4, 1);
                        break;
                    case C1452Re.zzm /* 21 */:
                        ParcelFileDescriptor parcelFileDescriptorJ2 = J2(parcel.readString(), parcel.readLong(), parcel.readLong());
                        parcel2.writeNoException();
                        b.f(parcel2, parcelFileDescriptorJ2, 1);
                        break;
                    case 22:
                        boolean zD2 = D2(parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeInt(zD2 ? 1 : 0);
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

    ParcelFileDescriptor C4(String str, boolean z, long j) throws RemoteException;

    boolean C7(String str) throws RemoteException;

    boolean D2(long j) throws RemoteException;

    boolean D4(String str, String str2) throws RemoteException;

    int G6(String str, boolean z) throws RemoteException;

    ParcelFileDescriptor J2(String str, long j, long j2) throws RemoteException;

    List<ax.Y1.a> K4(String str) throws RemoteException;

    boolean L5(String str) throws RemoteException;

    boolean O(String str) throws RemoteException;

    ax.Y1.a V0(String str) throws RemoteException;

    ax.Y1.a d4(String str) throws RemoteException;

    void destroy() throws RemoteException;

    List<String> h3(String str, long j) throws RemoteException;

    boolean x2(String str, long j) throws RemoteException;
}
