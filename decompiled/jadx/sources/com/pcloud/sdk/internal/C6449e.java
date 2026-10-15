package com.pcloud.sdk.internal;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* JADX INFO: renamed from: com.pcloud.sdk.internal.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6449e<T> implements ax.Sa.i<T> {
    private final ax.Ta.b<T> X;
    private final Call q;

    /* JADX INFO: renamed from: com.pcloud.sdk.internal.e$a */
    class a implements Callback {
        final /* synthetic */ ax.Sa.j a;

        a(ax.Sa.j jVar) {
            this.a = jVar;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            this.a.a(C6449e.this, iOException);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            try {
                ax.Sa.j jVar = this.a;
                C6449e c6449e = C6449e.this;
                jVar.b(c6449e, c6449e.c(response));
            } catch (ax.Sa.b | IOException e) {
                ax.Ta.a.a(response);
                this.a.a(C6449e.this, e);
            }
        }
    }

    C6449e(Call call, ax.Ta.b<T> bVar) {
        this.q = call;
        this.X = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public T c(Response response) throws ax.Sa.b, IOException {
        return this.X.a(response);
    }

    @Override // ax.Sa.i
    public void c0(ax.Sa.j<T> jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Callback argument cannot be null.");
        }
        this.q.enqueue(new a(jVar));
    }

    @Override // ax.Sa.i
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public C6449e<T> m5clone() {
        return new C6449e<>(this.q.clone(), this.X);
    }

    @Override // ax.Sa.i
    public T execute() throws ax.Sa.b, IOException {
        return c(this.q.execute());
    }
}
