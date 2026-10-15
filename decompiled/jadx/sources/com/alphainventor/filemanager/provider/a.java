package com.alphainventor.filemanager.provider;

import android.content.Context;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.ProxyFileDescriptorCallback;
import android.os.storage.StorageManager;
import android.system.ErrnoException;
import android.system.OsConstants;
import ax.p038c3.k;
import ax.p070e3.C1961e;
import ax.p070e3.C1966j;
import ax.p070e3.t;
import ax.p086f3.C2017x;
import ax.p262q3.b;
import ax.p406z3.B;
import com.alphainventor.filemanager.file.C6240o;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;
    private b c = new b();
    private Handler b = new Handler(this.c.b());

    /* JADX INFO: renamed from: com.alphainventor.filemanager.provider.a$a, reason: collision with other inner class name */
    class ProxyFileDescriptorCallbackC0542a extends ProxyFileDescriptorCallback {
        private ax.p262q3.a a;
        private boolean b;
        final /* synthetic */ C6240o c;
        final /* synthetic */ k d;
        final /* synthetic */ int e;

        ProxyFileDescriptorCallbackC0542a(C6240o c6240o, k kVar, int i) {
            this.c = c6240o;
            this.d = kVar;
            this.e = i;
        }

        private void a() throws ErrnoException {
            if (this.b) {
                return;
            }
            try {
                this.a = this.c.R(this.d.e(), this.e);
                this.b = true;
            } catch (C1966j e) {
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
            ax.p262q3.a aVar = this.a;
            if (aVar != null) {
                aVar.d();
            }
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public int onWrite(long j, int i, byte[] bArr) throws ErrnoException {
            a();
            return (int) this.a.e(j, bArr, 0, i);
        }
    }

    a(Context context) {
        this.a = context;
    }

    public static ErrnoException a(C1966j c1966j, String str) {
        if (str == null) {
            str = c1966j.getMessage();
        }
        if (c1966j instanceof t) {
            return new ErrnoException(str, OsConstants.ENOENT);
        }
        return c1966j instanceof C1961e ? new ErrnoException(str, OsConstants.EACCES) : new ErrnoException(str, OsConstants.EIO);
    }

    public ParcelFileDescriptor b(k kVar, int i) throws IOException {
        C6240o c6240oE = C2017x.e(kVar.d());
        if (!c6240oE.a()) {
            if (!(!B.H() ? c6240oE.i(10000L) : false)) {
                throw new FileNotFoundException("Network location is not connected");
            }
        }
        return ((StorageManager) this.a.getSystemService("storage")).openProxyFileDescriptor(i, new ProxyFileDescriptorCallbackC0542a(c6240oE, kVar, i), this.b);
    }
}
