package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxIterator;
import com.box.androidsdk.content.models.BoxIteratorBoxEntity;
import com.box.androidsdk.content.models.BoxJsonObject;
import com.box.androidsdk.content.models.BoxObject;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.requests.BoxRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Date;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxRequestUpload<E extends BoxJsonObject, R extends BoxRequest<E, R>> extends BoxRequestItem<E, R> {
    Date mCreatedDate;
    File mFile;
    String mFileName;
    Date mModifiedDate;
    String mSha1;
    InputStream mStream;
    long mUploadSize;

    public static class a extends BoxRequest.a<BoxRequestUpload> {
        public a(BoxRequestUpload boxRequestUpload) {
            super(boxRequestUpload);
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest.a
        public <T extends BoxObject> T h(Class<T> cls, b bVar) throws IllegalAccessException, BoxException, InstantiationException {
            return ((BoxIterator) super.h(BoxIteratorBoxEntity.class, bVar)).E(0);
        }
    }

    public BoxRequestUpload(Class<E> cls, InputStream inputStream, String str, BoxSession boxSession) {
        super(cls, null, str, boxSession);
        this.mRequestMethod = BoxRequest.Methods.POST;
        this.mStream = inputStream;
        this.mFileName = HttpUrl.FRAGMENT_ENCODE_SET;
        this.mContentType = null;
        C(new a(this));
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected void B(com.box.androidsdk.content.requests.a aVar) {
        super.B(aVar);
        String str = this.mSha1;
        if (str != null) {
            aVar.a("Content-MD5", str);
        }
    }

    protected c F() throws BoxException, IOException {
        c cVar = new c(d(), this.mRequestMethod, this.q);
        B(cVar);
        cVar.g(G(), this.mFileName, this.mUploadSize);
        Date date = this.mCreatedDate;
        if (date != null) {
            cVar.e("content_created_at", date);
        }
        Date date2 = this.mModifiedDate;
        if (date2 != null) {
            cVar.e("content_modified_at", date2);
        }
        return cVar;
    }

    protected InputStream G() throws FileNotFoundException {
        InputStream inputStream = this.mStream;
        return inputStream != null ? inputStream : new FileInputStream(this.mFile);
    }

    public R H(Date date) {
        this.mModifiedDate = date;
        return this;
    }

    public R I(ax.L3.b bVar) {
        this.q = bVar;
        return this;
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected com.box.androidsdk.content.requests.a i() throws BoxException, IOException {
        return F();
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected b y(com.box.androidsdk.content.requests.a aVar, HttpURLConnection httpURLConnection) throws BoxException, IOException {
        if (aVar instanceof c) {
            ((c) aVar).h(httpURLConnection, this.q);
        }
        return super.y(aVar, httpURLConnection);
    }
}
