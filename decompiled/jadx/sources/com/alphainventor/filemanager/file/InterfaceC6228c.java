package com.alphainventor.filemanager.file;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p406z3.InterfaceC2462c;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC6228c {

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.c$a */
    public interface a {
        void J();

        void d0(boolean z, Object obj);
    }

    boolean a();

    AbstractC6239n a1(String str) throws C1966j;

    void b();

    InputStream b1(String str, String str2, String str3) throws IOException;

    boolean c1(AbstractC6239n abstractC6239n);

    List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j;

    int e1(String str, String str2);

    String f1(AbstractC6239n abstractC6239n);

    void g1(AbstractC6239n abstractC6239n) throws C1966j;

    InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j;

    void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j;

    void j1(Activity activity, Fragment fragment, a aVar);

    boolean k1();

    void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j;

    void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j;

    boolean n1(AbstractC6239n abstractC6239n);

    boolean o1(AbstractC6239n abstractC6239n);

    boolean p1();

    void q1(AbstractC6239n abstractC6239n) throws C1966j;

    boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2);
}
