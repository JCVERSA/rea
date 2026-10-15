package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.models.BoxObject;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class BoxResponse<E extends BoxObject> implements Serializable {
    protected final Exception mException;
    protected final BoxRequest mRequest;
    protected final E mResult;

    public BoxResponse(E e, Exception exc, BoxRequest boxRequest) {
        this.mResult = e;
        this.mException = exc;
        this.mRequest = boxRequest;
    }

    public Exception a() {
        return this.mException;
    }

    public E b() {
        return this.mResult;
    }

    public boolean c() {
        return this.mException == null;
    }
}
