package com.box.androidsdk.content.models;

import com.box.androidsdk.content.models.BoxEntity;

/* JADX INFO: loaded from: classes.dex */
public class BoxIteratorBoxEntity<E extends BoxEntity> extends BoxIterator<E> {
    private static final long serialVersionUID = 8036181424029520417L;
    private transient BoxJsonObject.b<E> q;

    @Override // com.box.androidsdk.content.models.BoxIterator
    protected BoxJsonObject.b<E> K() {
        BoxJsonObject.b<E> bVar = this.q;
        if (bVar != null) {
            return bVar;
        }
        BoxJsonObject.b<E> bVar2 = (BoxJsonObject.b<E>) BoxEntity.H();
        this.q = bVar2;
        return bVar2;
    }
}
