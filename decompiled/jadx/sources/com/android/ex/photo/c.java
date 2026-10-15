package com.android.ex.photo;

import android.media.MediaMetadataRetriever;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public boolean a;
    public boolean b;
    public long d;
    public String f;
    public Object g;
    public ax.p387y0.a h;
    public long i;
    public int j;
    public int k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public long c = -1;
    public long e = -1;

    public void a(MediaMetadataRetriever mediaMetadataRetriever) {
        this.a = true;
        this.o = mediaMetadataRetriever.extractMetadata(5);
        this.p = mediaMetadataRetriever.extractMetadata(9);
        this.q = mediaMetadataRetriever.extractMetadata(18);
        this.r = mediaMetadataRetriever.extractMetadata(19);
        this.s = mediaMetadataRetriever.extractMetadata(23);
    }
}
