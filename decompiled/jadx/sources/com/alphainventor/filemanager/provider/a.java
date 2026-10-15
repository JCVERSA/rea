package com.alphainventor.filemanager.provider;

import android.content.Context;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.ProxyFileDescriptorCallback;
import android.os.storage.StorageManager;
import android.system.ErrnoException;
import android.system.OsConstants;
import ax.G1.j;
import ax.I1.C0713d;
import ax.I1.C0718i;
import ax.I1.s;
import ax.J1.r;
import ax.U1.b;
import ax.p045d2.x;
import com.alphainventor.filemanager.file.C5839m;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;
    private b c = new b();
    private Handler b = new Handler(this.c.b());

    /* JADX INFO: renamed from: com.alphainventor.filemanager.provider.a$a, reason: collision with other inner class name */
    class ProxyFileDescriptorCallbackC0508a extends ProxyFileDescriptorCallback {
        private ax.U1.a a;
        private boolean b;
        final /* synthetic */ C5839m c;
        final /* synthetic */ j d;
        final /* synthetic */ int e;

        ProxyFileDescriptorCallbackC0508a(C5839m c5839m, j jVar, int i) {
            this.c = c5839m;
            this.d = jVar;
            this.e = i;
        }

        private void a() throws ErrnoException {
            if (this.b) {
                return;
            }
            try {
                this.a = this.c.O(this.d.e(), this.e);
                this.b = true;
            } catch (C0718i e) {
                throw a.a(e, null);
            }
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public void onFsync() throws ErrnoException {
            a();
            this.a.a();
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public long onGetSize() throws ErrnoException {
            a();
            return this.a.b();
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public int onRead(long j, int i, byte[] bArr) throws ErrnoException {
            a();
            return this.a.c(j, bArr, 0, i);
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public void onRelease() {
            ax.U1.a aVar = this.a;
            if (aVar != null) {
                aVar.d();
            }
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public int onWrite(long j, int i, byte[] bArr) throws ErrnoException {
            a();
            return this.a.e(j, bArr, 0, i);
        }
    }

    a(Context context) {
        this.a = context;
    }

    public static ErrnoException a(C0718i c0718i, String str) {
        if (str == null) {
            str = c0718i.getMessage();
        }
        if (c0718i instanceof s) {
            return new ErrnoException(str, OsConstants.ENOENT);
        }
        return c0718i instanceof C0713d ? new ErrnoException(str, OsConstants.EACCES) : new ErrnoException(str, OsConstants.EIO);
    }

    public ParcelFileDescriptor b(j jVar, int i) throws IOException {
        C5839m c5839mD = r.d(jVar.d());
        if (!c5839mD.a()) {
            if (!(!x.J() ? c5839mD.h(10000L) : false)) {
                throw new FileNotFoundException("Network location is not connected");
            }
        }
        return ((StorageManager) this.a.getSystemService("storage")).openProxyFileDescriptor(i, new ProxyFileDescriptorCallbackC0508a(c5839mD, jVar, i), this.b);
    }
}
