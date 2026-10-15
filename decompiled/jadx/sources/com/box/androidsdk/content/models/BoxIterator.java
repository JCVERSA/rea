package com.box.androidsdk.content.models;

import ax.U4.d;
import com.box.androidsdk.content.models.BoxJsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxIterator<E extends BoxJsonObject> extends BoxJsonObject implements Iterable<E> {
    private static final long serialVersionUID = 8036181424029520417L;

    public Long D() {
        return t("total_count");
    }

    public E E(int i) {
        return (E) H(K(), i);
    }

    public E H(BoxJsonObject.b<E> bVar, int i) {
        return J().get(i);
    }

    public ArrayList<E> J() {
        return (ArrayList<E>) s(K(), "entries");
    }

    protected abstract BoxJsonObject.b<E> K();

    public Long M() {
        return t("offset");
    }

    @Override // com.box.androidsdk.content.models.BoxJsonObject
    public void i(d dVar) {
        super.i(dVar);
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        return J() == null ? Collections.EMPTY_LIST.iterator() : J().iterator();
    }
}
