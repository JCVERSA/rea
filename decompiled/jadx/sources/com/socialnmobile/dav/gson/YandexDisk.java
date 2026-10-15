package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class YandexDisk {
    protected long total_space;
    protected long trash_size;
    protected long used_space;

    public long getTotal() {
        return this.total_space;
    }

    public long getUsed() {
        return this.used_space;
    }
}
