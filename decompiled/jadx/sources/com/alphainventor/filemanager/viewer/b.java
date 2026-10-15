package com.alphainventor.filemanager.viewer;

import com.alphainventor.filemanager.file.AbstractC5838l;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b {
    static b b;
    HashMap<String, a> a = new HashMap<>();

    public static class a {
        List<AbstractC5838l> a;
        int b;

        a(List<AbstractC5838l> list, int i) {
            this.a = list;
            this.b = i;
        }
    }

    public static b b() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    public a a(String str) {
        return this.a.remove(str);
    }

    public void c(String str, List<AbstractC5838l> list, int i) {
        this.a.put(str, new a(list, i));
    }
}
