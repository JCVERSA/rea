package com.alphainventor.filemanager.viewer;

import com.alphainventor.filemanager.file.AbstractC6239n;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {
    static c b;
    HashMap<String, a> a = new HashMap<>();

    public static class a {
        List<AbstractC6239n> a;
        int b;

        a(List<AbstractC6239n> list, int i) {
            this.a = list;
            this.b = i;
        }
    }

    public static c b() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    public a a(String str) {
        if (str == null) {
            return null;
        }
        return this.a.remove(str);
    }

    public void c(String str, List<AbstractC6239n> list, int i) {
        this.a.put(str, new a(list, i));
    }
}
