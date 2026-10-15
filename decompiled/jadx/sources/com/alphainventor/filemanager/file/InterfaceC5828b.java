package com.alphainventor.filemanager.file;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0718i;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC5828b {

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$a */
    public interface a {
        void N();

        void f0(boolean z, Object obj);
    }

    AbstractC5838l V0(String str) throws C0718i;

    InputStream W0(String str, String str2, String str3) throws IOException;

    boolean X0(AbstractC5838l abstractC5838l);

    int Y0(String str, String str2);

    String Z0(AbstractC5838l abstractC5838l);

    boolean a();

    void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a;

    void b();

    void b1(AbstractC5838l abstractC5838l) throws C0718i;

    void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a;

    InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i;

    void e1(Activity activity, Fragment fragment, a aVar);

    boolean f1();

    List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i;

    boolean h1(AbstractC5838l abstractC5838l);

    boolean i1(AbstractC5838l abstractC5838l);

    boolean j1();

    void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a;

    void l1(AbstractC5838l abstractC5838l) throws C0718i;

    boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2);
}
