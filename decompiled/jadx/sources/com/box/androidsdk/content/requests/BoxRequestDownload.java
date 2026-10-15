package com.box.androidsdk.content.requests;

import android.text.TextUtils;
import ax.N3.f;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxDownload;
import com.box.androidsdk.content.models.BoxObject;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.requests.BoxRequest;
import com.box.androidsdk.content.utils.SdkUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxRequestDownload<E extends BoxObject, R extends BoxRequest<E, R>> extends BoxRequest<E, R> {
    protected ax.L3.a mDownloadStartListener;
    protected OutputStream mFileOutputStream;
    protected String mId;
    protected long mRangeEnd;
    protected long mRangeStart;
    private String mSha1;
    protected File mTarget;

    public static class DownloadRequestHandler extends BoxRequest.a<BoxRequestDownload> {
        protected int d;
        protected int e;

        public DownloadRequestHandler(BoxRequestDownload boxRequestDownload) {
            super(boxRequestDownload);
            this.d = 0;
            this.e = 1000;
        }

        protected OutputStream j(BoxDownload boxDownload) throws IOException {
            R r = this.a;
            if (((BoxRequestDownload) r).mFileOutputStream != null) {
                return ((BoxRequestDownload) r).mFileOutputStream;
            }
            if (!boxDownload.E().exists()) {
                boxDownload.E().createNewFile();
            }
            return new FileOutputStream(boxDownload.E());
        }

        /* JADX WARN: Code duplicated, block: B:116:? A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:99:0x01d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.box.androidsdk.content.requests.BoxRequest.a
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public BoxDownload h(Class cls, b bVar) throws Throwable {
            long j;
            Throwable th;
            Exception exc;
            OutputStream outputStreamJ;
            String strD = bVar.d();
            String contentEncoding = bVar.e().getContentEncoding();
            if (Thread.currentThread().isInterrupted()) {
                b(bVar);
            }
            if (bVar.f() == 429) {
                return (BoxDownload) i(bVar);
            }
            if (bVar.f() == 202) {
                try {
                    int i = this.d;
                    if (i < 2) {
                        this.d = i + 1;
                        this.e = BoxRequest.a.c(bVar, 1);
                    } else {
                        int i2 = this.e;
                        if (i2 >= 90000) {
                            throw new BoxException.MaxAttemptsExceeded("Max wait time exceeded.", this.d);
                        }
                        this.e = (int) (((double) i2) * (Math.random() + 1.5d));
                    }
                    Thread.sleep(this.e);
                    return (BoxDownload) ((BoxRequestDownload) this.a).x();
                } catch (InterruptedException e) {
                    throw new BoxException(e.getMessage(), bVar);
                }
            }
            if (bVar.f() != 200 && bVar.f() != 206) {
                return new BoxDownload(null, 0L, null, null, null, null);
            }
            String headerField = bVar.e().getHeaderField("Content-Length");
            String headerField2 = bVar.e().getHeaderField("Content-Disposition");
            try {
                j = Long.parseLong(headerField);
            } catch (Exception unused) {
                j = -1;
            }
            long j2 = j;
            BoxDownload boxDownload = new BoxDownload(headerField2, j2, strD, bVar.e().getHeaderField("Content-Range"), bVar.e().getHeaderField("Date"), bVar.e().getHeaderField("Expiration")) { // from class: com.box.androidsdk.content.requests.BoxRequestDownload.DownloadRequestHandler.1
                @Override // com.box.androidsdk.content.models.BoxDownload
                public File E() {
                    if (((BoxRequestDownload) DownloadRequestHandler.this.a).F() == null) {
                        return null;
                    }
                    if (((BoxRequestDownload) DownloadRequestHandler.this.a).F().isFile()) {
                        return ((BoxRequestDownload) DownloadRequestHandler.this.a).F();
                    }
                    return !SdkUtils.l(D()) ? new File(((BoxRequestDownload) DownloadRequestHandler.this.a).F(), D()) : super.E();
                }
            };
            ((BoxRequestDownload) this.a).getClass();
            f fVar = null;
            try {
                try {
                    if (((BoxRequestDownload) this.a).q != null) {
                        f fVar2 = new f(j(boxDownload), ((BoxRequestDownload) this.a).q, j2);
                        try {
                            ((BoxRequestDownload) this.a).q.a(0L, j2);
                            outputStreamJ = fVar2;
                        } catch (Exception e2) {
                            exc = e2;
                            fVar = fVar2;
                            Socket socketM = ((BoxRequestDownload) this.a).m();
                            if (socketM != null && contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                                try {
                                    socketM.close();
                                } catch (Exception e3) {
                                    ax.N3.b.c("error closing socket", e3);
                                }
                            }
                            if (exc instanceof BoxException) {
                                throw ((BoxException) exc);
                            }
                            if (exc instanceof SSLException) {
                                throw new BoxException.DownloadSSLException(exc.getMessage(), (SSLException) exc);
                            }
                            throw new BoxException(exc.getMessage(), exc);
                        } catch (Throwable th2) {
                            th = th2;
                            fVar = fVar2;
                            try {
                                bVar.a().close();
                            } catch (IOException e4) {
                                ax.N3.b.c("error closing inputstream", e4);
                            }
                            if (((BoxRequestDownload) this.a).G() == null) {
                                throw th;
                            }
                            try {
                                fVar.close();
                                throw th;
                            } catch (IOException e5) {
                                ax.N3.b.c("error closing outputstream", e5);
                                throw th;
                            }
                        }
                    } else {
                        outputStreamJ = j(boxDownload);
                    }
                    if (TextUtils.isEmpty(((BoxRequestDownload) this.a).mSha1)) {
                        SdkUtils.c(bVar.a(), outputStreamJ);
                    } else {
                        String strE = SdkUtils.e(bVar.a(), outputStreamJ);
                        if (!((BoxRequestDownload) this.a).mSha1.equals(strE)) {
                            throw new BoxException.CorruptedContentException("Sha1 checks failed", ((BoxRequestDownload) this.a).mSha1, strE);
                        }
                    }
                    try {
                        bVar.a().close();
                    } catch (IOException e6) {
                        ax.N3.b.c("error closing inputstream", e6);
                    }
                    if (((BoxRequestDownload) this.a).G() == null) {
                        try {
                            outputStreamJ.close();
                        } catch (IOException e7) {
                            ax.N3.b.c("error closing outputstream", e7);
                        }
                    }
                    return boxDownload;
                } catch (Exception e8) {
                    exc = e8;
                }
            } catch (Throwable th3) {
                th = th3;
                bVar.a().close();
                if (((BoxRequestDownload) this.a).G() == null) {
                    throw th;
                }
                fVar.close();
                throw th;
            }
        }
    }

    public BoxRequestDownload(String str, Class<E> cls, File file, String str2, BoxSession boxSession) {
        super(cls, str2, boxSession);
        this.mRangeStart = -1L;
        this.mRangeEnd = -1L;
        this.mId = str;
        this.mRequestMethod = BoxRequest.Methods.GET;
        this.mRequestUrlString = str2;
        this.mTarget = file;
        C(new DownloadRequestHandler(this));
        this.mRequiresSocket = true;
        this.mQueryMap.put("log_content_access", Boolean.toString(true));
    }

    public BoxRequestDownload(String str, Class<E> cls, OutputStream outputStream, String str2, BoxSession boxSession) {
        super(cls, str2, boxSession);
        this.mRangeStart = -1L;
        this.mRangeEnd = -1L;
        this.mId = str;
        this.mRequestMethod = BoxRequest.Methods.GET;
        this.mRequestUrlString = str2;
        this.mFileOutputStream = outputStream;
        C(new DownloadRequestHandler(this));
        this.mRequiresSocket = true;
        this.mQueryMap.put("log_content_access", Boolean.toString(true));
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.X = new DownloadRequestHandler(this);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected void B(a aVar) {
        super.B(aVar);
        long j = this.mRangeStart;
        if (j == -1 || this.mRangeEnd == -1) {
            return;
        }
        aVar.a("Range", String.format("bytes=%s-%s", Long.toString(j), Long.toString(this.mRangeEnd)));
    }

    public File F() {
        return this.mTarget;
    }

    public OutputStream G() {
        return this.mFileOutputStream;
    }

    public R H(long j, long j2) {
        this.mRangeStart = j;
        this.mRangeEnd = j2;
        return this;
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected void r(b bVar) throws BoxException {
        s();
        ax.N3.b.d("BoxContentSdk", String.format(Locale.ENGLISH, "Response (%s)", Integer.valueOf(bVar.f())));
    }
}
