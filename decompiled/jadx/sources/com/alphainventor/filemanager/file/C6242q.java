package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p070e3.C1967k;
import ax.p086f3.C2003i;
import ax.p086f3.C2004j;
import ax.p086f3.C2010p;
import ax.p086f3.C2017x;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.g0;
import ax.p086f3.i0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import j$.util.DesugarTimeZone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6242q extends AbstractC6238m implements i0 {
    static g v;
    private SimpleDateFormat h;
    private d i;
    private ax.Gd.e j;
    private e l;
    private String m;
    private String n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private int s;
    private static final Logger u = Logger.getLogger("FileManager.FtpFileHelper");
    private static int w = 0;
    private static int x = 1;
    private static int y = 2;
    private final Object k = new Object();
    private boolean t = false;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$a */
    class a implements ax.Gd.l {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // ax.Gd.l
        public boolean a(ax.Gd.i iVar) {
            if (iVar == null) {
                return false;
            }
            return iVar.b().trim().equals(this.a);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$b */
    class b implements ax.p182l3.i {
        final /* synthetic */ ax.p182l3.i a;

        b(ax.p182l3.i iVar) {
            this.a = iVar;
        }

        @Override // ax.p182l3.i
        public void a(long j, long j2) {
            ax.p182l3.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j / 2, j2);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$c */
    class c implements ax.p182l3.i {
        final /* synthetic */ ax.p182l3.i a;

        c(ax.p182l3.i iVar) {
            this.a = iVar;
        }

        @Override // ax.p182l3.i
        public void a(long j, long j2) {
            ax.p182l3.i iVar = this.a;
            if (iVar != null) {
                if (j == j2) {
                    iVar.a(j2, j2);
                } else {
                    iVar.a((j2 / 2) + (j / 2), j2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$d */
    private static class d extends ax.p406z3.q<Object, Void, Boolean> {
        InterfaceC6228c.a h;
        String i;
        int j;
        String k;
        String l;
        boolean m;
        boolean n;
        boolean o;
        ax.Gd.e p;
        boolean q;
        String r;
        String s;
        C6242q t;
        ax.p038c3.o u;
        String v;
        boolean w;
        Context x;

        public d(Context context, ax.p038c3.o oVar, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.x = context;
            this.h = aVar;
            this.q = true;
            this.u = oVar;
            E(oVar);
        }

        public d(Context context, C6242q c6242q, int i, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.x = context;
            this.t = c6242q;
            this.h = aVar;
            this.q = false;
            ax.p038c3.o oVarK = C6242q.h0(context).k(i);
            if (oVarK != null) {
                E(oVarK);
                return;
            }
            throw new IllegalArgumentException("No remote info for index : " + i);
        }

        private SSLContext A() {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                sSLContext.init(null, new TrustManager[]{ax.Jd.g.a()}, null);
                return sSLContext;
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        private void E(ax.p038c3.o oVar) {
            this.i = oVar.d();
            this.j = oVar.h();
            this.k = oVar.k();
            this.l = oVar.g();
            this.r = oVar.f();
            this.m = oVar.m();
            this.n = oVar.o();
            this.o = oVar.n();
            this.s = oVar.a();
        }

        /* JADX WARN: Code duplicated, block: B:100:0x01e3 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:102:0x01fa A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:104:0x020b A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:108:0x022e A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:111:0x0253 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TRY_LEAVE, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:122:0x010f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:29:0x008f  */
        /* JADX WARN: Code duplicated, block: B:49:0x00d0  */
        /* JADX WARN: Code duplicated, block: B:51:0x00d3 A[Catch: Exception -> 0x00cd, TryCatch #0 {Exception -> 0x00cd, blocks: (B:34:0x00ac, B:36:0x00b5, B:38:0x00b8, B:41:0x00c0, B:43:0x00c3, B:46:0x00ca, B:51:0x00d3, B:53:0x00e4, B:55:0x00f0, B:52:0x00db), top: B:120:0x00ac }] */
        /* JADX WARN: Code duplicated, block: B:52:0x00db A[Catch: Exception -> 0x00cd, TryCatch #0 {Exception -> 0x00cd, blocks: (B:34:0x00ac, B:36:0x00b5, B:38:0x00b8, B:41:0x00c0, B:43:0x00c3, B:46:0x00ca, B:51:0x00d3, B:53:0x00e4, B:55:0x00f0, B:52:0x00db), top: B:120:0x00ac }] */
        /* JADX WARN: Code duplicated, block: B:55:0x00f0 A[Catch: Exception -> 0x00cd, TRY_LEAVE, TryCatch #0 {Exception -> 0x00cd, blocks: (B:34:0x00ac, B:36:0x00b5, B:38:0x00b8, B:41:0x00c0, B:43:0x00c3, B:46:0x00ca, B:51:0x00d3, B:53:0x00e4, B:55:0x00f0, B:52:0x00db), top: B:120:0x00ac }] */
        /* JADX WARN: Code duplicated, block: B:59:0x011b A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:65:0x0140  */
        /* JADX WARN: Code duplicated, block: B:69:0x0148 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:72:0x0157 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:75:0x0161 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:78:0x0173 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:79:0x017b A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:82:0x0186 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:83:0x018c A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:85:0x0193 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:94:0x01bb A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:96:0x01c7 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Code duplicated, block: B:98:0x01d5 A[Catch: IllegalStateException -> 0x013a, IOException -> 0x013d, TryCatch #3 {IOException -> 0x013d, IllegalStateException -> 0x013a, blocks: (B:57:0x010f, B:59:0x011b, B:69:0x0148, B:70:0x014f, B:72:0x0157, B:76:0x016a, B:78:0x0173, B:80:0x0182, B:82:0x0186, B:85:0x0193, B:86:0x01a5, B:88:0x01a9, B:90:0x01ad, B:92:0x01b1, B:94:0x01bb, B:96:0x01c7, B:98:0x01d5, B:100:0x01e3, B:102:0x01fa, B:104:0x020b, B:106:0x022a, B:108:0x022e, B:109:0x024f, B:111:0x0253, B:83:0x018c, B:79:0x017b, B:75:0x0161), top: B:122:0x010f }] */
        /* JADX WARN: Instruction removed from duplicated block: B:104:0x020b, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:55:0x00f0, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:59:0x011b, please report this as an issue */
        private boolean w(boolean z, boolean z2) {
            boolean z3;
            boolean z4;
            boolean z5;
            InetAddress[] allByName;
            InetAddress inetAddress;
            String strQ1;
            String strTrim;
            String strD;
            String strS;
            System.setProperty("org.apache.commons.net.ftp.systemType.default", "UNKNOWN_SYSTEM_TYPE");
            if (z) {
                C6242q.i0(this.x);
                SSLContext sSLContextA = A();
                if (sSLContextA != null) {
                    this.p = new j(this.o, sSLContextA, this.j);
                } else {
                    j jVar = new j(this.o, this.j);
                    jVar.R1(ax.Jd.g.a());
                    this.p = jVar;
                }
            } else {
                this.p = new i();
            }
            this.p.n0(false);
            this.p.A1(new C2010p());
            String str = this.s;
            try {
                if (str != null) {
                    this.p.m0(str);
                    if ("UTF8".equals(this.s) || "UTF-8".equals(this.s)) {
                        z3 = false;
                        z4 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3 && ax.p406z3.B.G(this.k) && ax.p406z3.B.G(this.l)) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (z3 && !z5) {
                        this.p.w1(true);
                    }
                    this.p.x(15000);
                    this.p.z(15000);
                    this.p.x1(30000);
                    allByName = InetAddress.getAllByName(this.i);
                    if (allByName == null && allByName.length > 1 && (allByName[0] instanceof Inet6Address)) {
                        inetAddress = null;
                        for (InetAddress inetAddress2 : allByName) {
                            if (inetAddress2 instanceof Inet4Address) {
                                inetAddress = inetAddress2;
                            }
                        }
                    } else {
                        inetAddress = null;
                    }
                    if (inetAddress != null) {
                        this.p.n(inetAddress, this.j);
                    } else {
                        this.p.m(this.i, this.j);
                    }
                    if (!ax.Gd.t.c(this.p.R())) {
                        this.v = "connect error : " + this.p.S();
                        this.p.o();
                        return false;
                    }
                    try {
                        if (!this.p.l1(this.k, this.l)) {
                            this.v = "login error : " + this.p.S();
                            C6242q.d0(this.p);
                            return false;
                        }
                        if ((z3 && z5) || z4) {
                            if (z3) {
                                ((h) this.p).g();
                            }
                            if (this.p.Z0("UTF8") || this.p.Z0("UTF-8") || z4) {
                                this.p.l0("OPTS", "UTF8 ON");
                            }
                        }
                        if (this.p.y1(2)) {
                            ((h) this.p).b(2);
                        } else {
                            ((h) this.p).b(0);
                        }
                        if (this.m) {
                            this.p.F0();
                        } else {
                            this.p.G0();
                        }
                        if (z) {
                            ((ax.Gd.u) this.p).K1(0L);
                            ((ax.Gd.u) this.p).L1("P");
                        }
                        if (this.h != null && this.t != null && this.r == null) {
                            strQ1 = this.p.q1();
                            this.r = strQ1;
                            if (strQ1 != null) {
                                strTrim = strQ1.trim();
                                this.r = strTrim;
                                if (!d0.B(strTrim)) {
                                    strD = D(this.r);
                                    this.r = strD;
                                    if (!d0.B(strD)) {
                                        strS = d0.S(this.r);
                                        this.r = strS;
                                        if (!d0.B(strS)) {
                                            ax.p302sb.c.h().c("INVALID FTP INITIAL PATH").h(this.r).i();
                                            this.r = null;
                                        }
                                    }
                                }
                            }
                        }
                        if (z2) {
                            this.p.i1();
                            if (!ax.Gd.t.c(this.p.R())) {
                                this.v = "list error : " + this.p.S();
                                C6242q.d0(this.p);
                                return false;
                            }
                        }
                        if (this.t != null) {
                            String strY0 = this.p.Y0();
                            String strD2 = ((h) this.p).d();
                            this.t.s0(this.r);
                            this.t.r0(this.p);
                            this.t.c0(strD2, strY0);
                        }
                        if (this.q) {
                            C6242q.d0(this.p);
                        }
                        return true;
                    } catch (IOException e) {
                        e = e;
                        this.v = "exception error 2 : " + e.getMessage();
                        e.printStackTrace();
                        C6242q.d0(this.p);
                        return false;
                    } catch (IllegalStateException e2) {
                        e = e2;
                        this.v = "exception error 2 : " + e.getMessage();
                        e.printStackTrace();
                        C6242q.d0(this.p);
                        return false;
                    }
                }
                String strA = ax.p406z3.f.a();
                if (strA != null) {
                    this.p.m0(strA);
                }
                z3 = true;
                allByName = InetAddress.getAllByName(this.i);
                if (allByName == null) {
                    inetAddress = null;
                } else {
                    inetAddress = null;
                }
                if (inetAddress != null) {
                    this.p.n(inetAddress, this.j);
                } else {
                    this.p.m(this.i, this.j);
                }
                if (!ax.Gd.t.c(this.p.R())) {
                    this.v = "connect error : " + this.p.S();
                    this.p.o();
                    return false;
                }
                if (!this.p.l1(this.k, this.l)) {
                    this.v = "login error : " + this.p.S();
                    C6242q.d0(this.p);
                    return false;
                }
                if (z3) {
                    if (z3) {
                        ((h) this.p).g();
                    }
                    if (this.p.Z0("UTF8")) {
                        this.p.l0("OPTS", "UTF8 ON");
                    } else {
                        this.p.l0("OPTS", "UTF8 ON");
                    }
                } else {
                    if (z3) {
                        ((h) this.p).g();
                    }
                    if (this.p.Z0("UTF8")) {
                        this.p.l0("OPTS", "UTF8 ON");
                    } else {
                        this.p.l0("OPTS", "UTF8 ON");
                    }
                }
                if (this.p.y1(2)) {
                    ((h) this.p).b(2);
                } else {
                    ((h) this.p).b(0);
                }
                if (this.m) {
                    this.p.F0();
                } else {
                    this.p.G0();
                }
                if (z) {
                    ((ax.Gd.u) this.p).K1(0L);
                    ((ax.Gd.u) this.p).L1("P");
                }
                if (this.h != null) {
                    strQ1 = this.p.q1();
                    this.r = strQ1;
                    if (strQ1 != null) {
                        strTrim = strQ1.trim();
                        this.r = strTrim;
                        if (!d0.B(strTrim)) {
                            strD = D(this.r);
                            this.r = strD;
                            if (!d0.B(strD)) {
                                strS = d0.S(this.r);
                                this.r = strS;
                                if (!d0.B(strS)) {
                                    ax.p302sb.c.h().c("INVALID FTP INITIAL PATH").h(this.r).i();
                                    this.r = null;
                                }
                            }
                        }
                    }
                }
                if (z2) {
                    this.p.i1();
                    if (!ax.Gd.t.c(this.p.R())) {
                        this.v = "list error : " + this.p.S();
                        C6242q.d0(this.p);
                        return false;
                    }
                }
                if (this.t != null) {
                    String strY1 = this.p.Y0();
                    String strD3 = ((h) this.p).d();
                    this.t.s0(this.r);
                    this.t.r0(this.p);
                    this.t.c0(strD3, strY1);
                }
                if (this.q) {
                    C6242q.d0(this.p);
                }
                return true;
            } catch (Exception e3) {
                if (C1960d.e(e3)) {
                    this.w = true;
                }
                this.v = "exception error 1 : " + e3.getMessage();
                e3.printStackTrace();
                return false;
            }
            z4 = false;
            if (!z3) {
                z5 = false;
            } else {
                z5 = false;
            }
            if (z3) {
                this.p.w1(true);
            }
            this.p.x(15000);
            this.p.z(15000);
            this.p.x1(30000);
        }

        boolean B() {
            return this.w;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.h != null) {
                if (!bool.booleanValue()) {
                    C6242q c6242q = this.t;
                    if (c6242q != null && C6242q.l0(c6242q.t())) {
                        C6242q.h0(this.x).m(this.t.t());
                    }
                    this.h.d0(bool.booleanValue(), this.v);
                    return;
                }
                C6242q c6242q2 = this.t;
                if (c6242q2 != null && C6242q.l0(c6242q2.t())) {
                    C6242q.h0(this.x).t(this.t.t());
                }
                if (!TextUtils.isEmpty(this.r)) {
                    this.r = d0.U(this.r);
                }
                this.h.d0(bool.booleanValue(), this.r);
            }
        }

        String D(String str) {
            int iIndexOf = str.indexOf("\"");
            if (iIndexOf < 0) {
                return str;
            }
            try {
                StringBuilder sb = new StringBuilder();
                boolean z = false;
                for (int i = iIndexOf + 1; i < str.length(); i++) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt != '\"') {
                        if (z) {
                            return sb.toString();
                        }
                        sb.append(cCharAt);
                    } else if (z) {
                        sb.append(cCharAt);
                        z = false;
                    } else {
                        z = true;
                    }
                }
                return z ? sb.toString() : str;
            } catch (IndexOutOfBoundsException unused) {
                return str;
            }
        }

        @Override // ax.p406z3.q
        protected void r() {
            InterfaceC6228c.a aVar = this.h;
            if (aVar != null) {
                aVar.J();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            C6242q c6242q = this.t;
            if (c6242q != null) {
                c6242q.i();
            }
            try {
                if (this.q && !this.n) {
                    if (w(true, true) && this.u != null) {
                        C6242q.u.fine("FTPS detected!!!");
                        this.u.C(true);
                        return Boolean.TRUE;
                    }
                    C6242q.u.fine("FTPS not detected, try FTP");
                }
                return Boolean.valueOf(w(this.n, false));
            } finally {
                C6242q c6242q2 = this.t;
                if (c6242q2 != null) {
                    c6242q2.j();
                }
            }
        }

        String y() {
            return this.v;
        }

        public ax.Gd.e z() {
            return this.p;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$e */
    class e extends ax.p406z3.t<ax.Gd.e> {
        e() {
        }

        public ax.Gd.e f() throws ax.p070e3.K {
            ax.Gd.e eVar = (ax.Gd.e) super.a();
            return (eVar == null || eVar.w()) ? eVar : d();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.t
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void c(ax.Gd.e eVar) {
            C6242q.d0(eVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.t
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public ax.Gd.e d() throws ax.p070e3.K {
            try {
                d dVar = new d(C6242q.this.p(), null, C6242q.this.t(), null);
                if (dVar.h(new Object[0]).l().booleanValue()) {
                    return dVar.z();
                }
                if (!dVar.B()) {
                    ax.p302sb.c.h().g().c("FTP CHANNEL OPEN ERROR 1").h("msg:" + dVar.y()).i();
                }
                throw new ax.p070e3.K("Could not create transfer connection");
            } catch (ax.p070e3.K e) {
                throw e;
            } catch (Exception e2) {
                ax.p302sb.c.h().g().d("FTP CHANNEL OPEN ERROR 2").m(e2).i();
                e2.printStackTrace();
                throw new ax.p070e3.K("Could not create transfer connection", e2);
            }
        }

        public void i(ax.Gd.e eVar) {
            if (eVar == null) {
                C6242q.u.severe("Try to release null ftpclient");
            } else if (eVar.w()) {
                super.e(eVar);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$f */
    class f extends ax.p086f3.H {
        ax.Gd.e X;
        boolean Y;

        f(ax.Gd.e eVar, InputStream inputStream) {
            super(inputStream);
            this.X = eVar;
        }

        @Override // ax.p086f3.H, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.Y) {
                return;
            }
            super.close();
            this.Y = true;
            if (this.X.w()) {
                this.X.A0();
                this.X.C0();
                if (this.X != C6242q.this.j) {
                    C6242q.this.l.i(this.X);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$g */
    public static class g extends g0 {
        Context a;
        d b;
        int d = 100000000;
        HashMap<Integer, k> e = new HashMap<>();
        c0 c = new c0(ax.U2.f.M0);

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$g$a */
        class a implements InterfaceC6228c.a {
            final /* synthetic */ int X;
            final /* synthetic */ ax.p038c3.o Y;
            final /* synthetic */ ax.p182l3.k q;

            a(ax.p182l3.k kVar, int i, ax.p038c3.o oVar) {
                this.q = kVar;
                this.X = i;
                this.Y = oVar;
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void J() {
                this.q.a(ax.U2.f.M0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void d0(boolean z, Object obj) {
                if (!z) {
                    this.q.c(ax.U2.f.M0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof String ? (String) obj : null);
                    return;
                }
                g.this.s(this.X, this.Y);
                ax.U2.f fVar = ax.U2.f.M0;
                C6240o c6240oD = C2017x.d(fVar, this.X);
                new AbstractC6238m.d(c6240oD.u()).i(new Long[0]);
                ax.p038c3.b.k().s(c6240oD.B(), c6240oD.D());
                this.q.d(fVar, this.X);
            }
        }

        g(Context context) {
            this.a = context;
        }

        private int o(ax.p310t3.o oVar) {
            String strE = oVar.e();
            InetAddress inetAddressG = oVar.g();
            int i = oVar.i();
            for (Map.Entry<Integer, k> entry : this.e.entrySet()) {
                ax.p038c3.o oVar2 = entry.getValue().a;
                if (ax.p406z3.B.j(oVar2.b(), strE) && ax.p406z3.B.j(oVar2.d(), inetAddressG.getHostAddress()) && oVar2.h() == i) {
                    return entry.getKey().intValue();
                }
            }
            return 0;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("FTPPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("mode_" + i).remove("security_" + i).remove("charset_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            if (C6242q.l0(i)) {
                ax.p038c3.o oVarK = k(i);
                return oVarK == null ? new ax.p038c3.s(ax.U2.f.M0, i, null, HttpUrl.FRAGMENT_ENCODE_SET, null, null, 0L, 0L) : new ax.p038c3.s(ax.U2.f.M0, i, oVarK.b(), HttpUrl.FRAGMENT_ENCODE_SET, null, null, 0L, 0L);
            }
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            return new ax.p038c3.s(ax.U2.f.M0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), sharedPreferences.getString("initialPath_" + i, null), sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("FTPPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.p086f3.g0
        public ax.p038c3.o k(int i) {
            if (C6242q.l0(i)) {
                k kVar = this.e.get(Integer.valueOf(i));
                if (kVar != null) {
                    return kVar.a;
                }
                return null;
            }
            ax.p038c3.o oVar = new ax.p038c3.o();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            oVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            oVar.s(string);
            oVar.z(sharedPreferences.getInt("port_" + i, 21));
            oVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            oVar.y(this.c.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            oVar.v(sharedPreferences.getString("initialPath_" + i, null));
            oVar.w(sharedPreferences.getBoolean("mode_" + i, false));
            oVar.p(sharedPreferences.getString("charset_" + i, null));
            int i3 = sharedPreferences.getInt("security_" + i, -1);
            if (i3 < 0) {
                oVar.C(false);
                return oVar;
            }
            oVar.C(true);
            oVar.x(i3 > 0);
            return oVar;
        }

        @Override // ax.p086f3.g0
        public void l(int i, ax.p038c3.o oVar, ax.p182l3.k kVar, boolean z) {
            if (i == -100) {
                i = n();
            }
            if (!z) {
                s(i, oVar);
                kVar.d(ax.U2.f.M0, i);
            } else {
                d dVar = new d(this.a, oVar, new a(kVar, i, oVar));
                this.b = dVar;
                dVar.h(new Object[0]);
            }
        }

        public void m(int i) {
            k kVar = this.e.get(Integer.valueOf(i));
            if (kVar != null) {
                kVar.c = false;
            }
        }

        int n() {
            return this.a.getSharedPreferences("FTPPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> p() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("host_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        public int q(ax.p310t3.o oVar) {
            k kVar;
            String strH = oVar.h();
            int iO = o(oVar);
            if (iO == 0 || (kVar = this.e.get(Integer.valueOf(iO))) == null || !ax.p406z3.B.j(strH, kVar.b) || !kVar.c) {
                return 0;
            }
            return iO;
        }

        public int r(ax.p310t3.o oVar, String str, String str2) {
            k kVar;
            String strE = oVar.e();
            InetAddress inetAddressG = oVar.g();
            int i = oVar.i();
            String strH = oVar.h();
            boolean zM = oVar.m();
            int iO = o(oVar);
            if (iO != 0 && (kVar = this.e.get(Integer.valueOf(iO))) != null) {
                if (ax.p406z3.B.j(kVar.a.k(), str) && ax.p406z3.B.j(kVar.a.g(), str2)) {
                    if (!ax.p406z3.B.j(strH, kVar.b)) {
                        kVar.b = strH;
                        kVar.c = false;
                    }
                    return iO;
                }
                kVar.a.D(str);
                kVar.a.y(str2);
                kVar.b = strH;
                kVar.c = false;
                return iO;
            }
            int i2 = this.d;
            this.d = i2 + 1;
            ax.p038c3.o oVar2 = new ax.p038c3.o();
            oVar2.q(strE);
            oVar2.s(inetAddressG.getHostAddress());
            oVar2.z(i);
            oVar2.D(str);
            oVar2.y(str2);
            oVar2.w(false);
            oVar2.p(null);
            oVar2.C(zM);
            this.e.put(Integer.valueOf(i2), new k(oVar2, strH));
            return i2;
        }

        void s(int i, ax.p038c3.o oVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("host_" + i, oVar.d()).putInt("port_" + i, oVar.h()).putString("username_" + i, oVar.k()).putString("password_" + i, this.c.e(oVar.d(), oVar.g())).putString("name_" + i, oVar.b()).putString("initialPath_" + i, oVar.f()).putString("charset_" + i, oVar.a()).putBoolean("mode_" + i, oVar.m());
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (oVar.o()) {
                editorEdit.putInt("security_" + i, oVar.n() ? 1 : 0);
            } else {
                editorEdit.putInt("security_" + i, -1);
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }

        public void t(int i) {
            k kVar = this.e.get(Integer.valueOf(i));
            if (kVar != null) {
                kVar.c = true;
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$h */
    interface h {
        void a(int i);

        void b(int i);

        int c();

        String d();

        boolean e();

        void g() throws IOException;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$i */
    static class i extends ax.Gd.e implements h {
        boolean g0;
        int h0;
        String i0;
        int j0;

        i() {
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public void a(int i) {
            this.g0 = true;
            this.h0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public void b(int i) {
            this.j0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public int c() {
            return this.j0;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public String d() {
            return this.i0;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public boolean e() {
            try {
                E();
                return true;
            } catch (SocketTimeoutException | IOException unused) {
                return false;
            }
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public void g() throws IOException {
            if (Z0("UTF8") || Z0("UTF-8")) {
                m0("UTF-8");
                this.x = new ax.Id.a(new InputStreamReader(this.e, O()));
                this.y = new BufferedWriter(new OutputStreamWriter(this.f, O()));
            }
        }

        @Override // ax.Gd.e, ax.Fd.e
        protected void i() throws IOException {
            super.i();
            if (R() == 220) {
                this.i0 = S();
            }
        }

        @Override // ax.Gd.e
        protected Socket v0(String str, String str2) throws IOException {
            int i;
            try {
                Socket socketV0 = super.v0(str, str2);
                if (socketV0 == null) {
                    return null;
                }
                if (socketV0.getSendBufferSize() > 1048576) {
                    socketV0.setSendBufferSize(524288);
                }
                if (this.g0 && (i = this.h0) > 0) {
                    socketV0.setSoLinger(true, i);
                    C6242q.u.fine("set so linger:" + socketV0.getSoLinger());
                }
                return socketV0;
            } catch (StringIndexOutOfBoundsException e) {
                throw new IOException(e);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$j */
    static class j extends ax.Gd.u implements h {
        int A0;
        Object B0;
        Object C0;
        int D0;
        private int w0;
        String x0;
        boolean y0;
        int z0;

        j(boolean z, int i) {
            super(z);
            this.D0 = i;
        }

        j(boolean z, SSLContext sSLContext, int i) {
            super(z, sSLContext);
            this.D0 = i;
            U1(sSLContext);
        }

        private boolean T1(Socket socket) {
            if (Build.VERSION.SDK_INT <= 28) {
                return true;
            }
            if (socket == null) {
                return false;
            }
            String name = ((SSLSocket) socket).getSession().getClass().getName();
            if ("com.android.org.conscrypt.Java8ExtendedSSLSession".equals(name)) {
                return false;
            }
            if ("com.google.android.gms.org.conscrypt.Java8ExtendedSSLSession".equals(name)) {
                return true;
            }
            C2461b.e("new session class detected");
            return name.contains("gms");
        }

        private void U1(SSLContext sSLContext) {
            try {
                Socket socketCreateSocket = sSLContext.getSocketFactory().createSocket();
                if (T1(socketCreateSocket)) {
                    this.w0 = 1;
                } else {
                    this.w0 = 2;
                }
                socketCreateSocket.close();
            } catch (IOException unused) {
            }
        }

        private boolean V1(SSLSocket sSLSocket) {
            Object obj;
            Socket socket = this.b;
            if (socket == null) {
                return false;
            }
            SSLSession session = ((SSLSocket) socket).getSession();
            if (!session.isValid()) {
                return false;
            }
            SSLSessionContext sessionContext = session.getSessionContext();
            try {
                Field declaredField = sessionContext.getClass().getDeclaredField("sessionsByHostAndPort");
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(sessionContext);
                int peerPort = session.getPeerPort();
                Set setKeySet = ((HashMap) obj2).keySet();
                if (setKeySet.size() == 0) {
                    C6242q.u.severe("invalid SSL session 1");
                    return false;
                }
                Field declaredField2 = setKeySet.toArray()[0].getClass().getDeclaredField("port");
                declaredField2.setAccessible(true);
                int i = 0;
                while (i < setKeySet.size() && ((Integer) declaredField2.get(setKeySet.toArray()[i])).intValue() != peerPort) {
                    i++;
                }
                int size = setKeySet.size();
                Class<?> cls = Integer.TYPE;
                if (i >= size) {
                    if (this.B0 == null || (obj = this.C0) == null) {
                        C6242q.u.severe("invalid SSL session 2");
                        return false;
                    }
                    Constructor<?> declaredConstructor = obj.getClass().getDeclaredConstructor(String.class, cls);
                    declaredConstructor.setAccessible(true);
                    Object objNewInstance = declaredConstructor.newInstance(sSLSocket.getInetAddress().getHostName(), Integer.valueOf(sSLSocket.getPort()));
                    Object objNewInstance2 = declaredConstructor.newInstance(sSLSocket.getInetAddress().getHostAddress(), Integer.valueOf(sSLSocket.getPort()));
                    Method declaredMethod = obj2.getClass().getDeclaredMethod("put", Object.class, Object.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(obj2, objNewInstance, this.B0);
                    declaredMethod.invoke(obj2, objNewInstance2, this.B0);
                    return true;
                }
                Object obj3 = setKeySet.toArray()[i];
                Constructor<?> declaredConstructor2 = obj3.getClass().getDeclaredConstructor(String.class, cls);
                declaredConstructor2.setAccessible(true);
                Object objNewInstance3 = declaredConstructor2.newInstance(sSLSocket.getInetAddress().getHostName(), Integer.valueOf(sSLSocket.getPort()));
                Object objNewInstance4 = declaredConstructor2.newInstance(sSLSocket.getInetAddress().getHostAddress(), Integer.valueOf(sSLSocket.getPort()));
                Object obj4 = ((HashMap) obj2).get(obj3);
                if (obj4 instanceof ArrayList) {
                    this.C0 = obj3;
                    this.B0 = new ArrayList((ArrayList) obj4);
                }
                Method declaredMethod2 = obj2.getClass().getDeclaredMethod("put", Object.class, Object.class);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(obj2, objNewInstance3, obj4);
                declaredMethod2.invoke(obj2, objNewInstance4, obj4);
                return true;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                C6242q.u.severe("This device is not supported!!!! : api " + Build.VERSION.SDK_INT);
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                C6242q.u.severe("Session reuse : unknown exception");
                e2.printStackTrace();
                return false;
            }
        }

        private boolean W1() {
            return this.w0 == 2;
        }

        private boolean X1() {
            return this.w0 == 1;
        }

        @Override // ax.Gd.u
        protected void E1(Socket socket) throws IOException {
            int i;
            if (socket == null) {
                return;
            }
            if ((socket instanceof SSLSocket) && X1()) {
                V1((SSLSocket) socket);
            }
            if (socket.getSendBufferSize() > 1048576) {
                socket.setSendBufferSize(524288);
            }
            if (!this.y0 || (i = this.z0) <= 0) {
                return;
            }
            socket.setSoLinger(true, i);
        }

        @Override // ax.Gd.u
        protected Socket I1() throws IOException {
            return W1() ? new C2004j(new Socket()) : super.I1();
        }

        @Override // ax.Gd.u
        protected void J1() throws IOException {
            if (R() == 220) {
                this.x0 = S();
            }
            super.J1();
        }

        @Override // ax.Gd.e
        public String Q0() {
            String str = this.c;
            return str != null ? str : super.Q0();
        }

        @Override // ax.Gd.u
        protected Socket Q1(String str, String str2) throws IOException {
            Socket socketQ1 = super.Q1(str, str2);
            if (!W1() || !(socketQ1 instanceof C2004j) || N1() == null) {
                return socketQ1;
            }
            C2003i c2003i = new C2003i((SSLSocket) N1().getSocketFactory().createSocket(socketQ1, this.c, this.D0, true), socketQ1);
            c2003i.a(this.D0);
            return c2003i;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public void a(int i) {
            this.y0 = true;
            this.z0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public void b(int i) {
            this.A0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public int c() {
            return this.A0;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public String d() {
            return this.x0;
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public boolean e() {
            try {
                E();
                return true;
            } catch (SocketTimeoutException | IOException unused) {
                return false;
            }
        }

        @Override // com.alphainventor.filemanager.file.C6242q.h
        public void g() throws IOException {
            if (Z0("UTF8") || Z0("UTF-8")) {
                m0("UTF-8");
                this.x = new ax.Id.a(new InputStreamReader(this.b.getInputStream(), O()));
                this.y = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream(), O()));
            }
        }

        @Override // ax.Gd.u, ax.Gd.e
        protected Socket v0(String str, String str2) throws IOException {
            Socket socketV0 = super.v0(str, str2);
            if (W1() && (socketV0 instanceof C2003i)) {
                ((C2003i) socketV0).a(0);
            }
            return socketV0;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.q$k */
    public static class k {
        final ax.p038c3.o a;
        String b;
        boolean c;

        public k(ax.p038c3.o oVar, String str) {
            this.a = oVar;
            this.b = str;
        }
    }

    private void W() {
        try {
            new d(p(), this, t(), null).h(new Object[0]).l();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean X(String str) throws IOException {
        if (str.equals(this.n)) {
            return true;
        }
        return ax.Gd.t.c(Y(this.j, str));
    }

    private int Y(ax.Gd.e eVar, String str) throws IOException {
        boolean zB0 = eVar.B0(d0.O(str));
        int iR = eVar.R();
        if (zB0) {
            if (eVar == this.j) {
                this.n = str;
                return iR;
            }
        } else if (iR == 550 && "/".equals(str)) {
            do {
                try {
                    String strQ1 = eVar.q1();
                    if (strQ1 == null) {
                        break;
                    }
                    if ("/".equals(strQ1)) {
                        return 250;
                    }
                } catch (IOException unused) {
                }
            } while (ax.Gd.t.c(eVar.I()));
        }
        return iR;
    }

    private boolean Z(String str) throws IOException {
        if ("/".equals(str)) {
            return true;
        }
        return str != null && str.equals(this.m) && ax.Gd.t.c(Y(this.j, str));
    }

    private C1966j a0(String str, IOException iOException) {
        return iOException instanceof ax.Gd.h ? new ax.p070e3.q(iOException) : C1960d.b(str, iOException);
    }

    private C1966j b0(String str, int i2, String str2, boolean z) {
        String str3 = str + " (" + str2 + ")";
        if (i2 == 550) {
            if (str2 == null) {
                return z ? new ax.p070e3.t(str3) : new C1961e(str3);
            }
            String lowerCase = str2.toLowerCase();
            if (lowerCase.contains("no such") || lowerCase.contains("not found")) {
                return new ax.p070e3.t(str3);
            }
            if (lowerCase.contains("access") || lowerCase.contains("permission")) {
                return new C1961e(str3);
            }
            if (lowerCase.contains("not empty")) {
                return new C1967k(str3);
            }
            if (lowerCase.contains("already exist")) {
                return new C1962f(false);
            }
            C2461b.e(str2);
            return z ? new ax.p070e3.t(str3) : new C1961e(str3);
        }
        if (i2 != 452 && i2 != 552) {
            if (i2 != 553) {
                if (i2 != 425 && i2 != 426) {
                    return new C1966j(str3);
                }
                return new ax.p070e3.q(str3);
            }
            if (str2 != null) {
                String lowerCase2 = str2.toLowerCase();
                if (lowerCase2.contains("access") || lowerCase2.contains("permission")) {
                    return new C1961e(str3);
                }
                if (lowerCase2.contains("name")) {
                    return new ax.p070e3.o(str3);
                }
            }
            return new C1961e(str3);
        }
        return new ax.p070e3.s(str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d0(ax.Gd.e eVar) {
        if (eVar == null || !eVar.w()) {
            return;
        }
        try {
            eVar.o();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static int e0() {
        return 21;
    }

    private ax.Gd.i f0(String str) throws IOException {
        if ("/".equals(str)) {
            return null;
        }
        String strH = d0.h(str);
        int iY = Y(this.j, d0.r(str));
        if (!ax.Gd.t.c(iY)) {
            if (iY == 550) {
                return null;
            }
            throw new IOException("ChangeWorkingDirectory Error :" + this.j.R());
        }
        try {
            ax.Gd.i[] iVarArrK1 = this.j.k1(null, new a(strH));
            if (iVarArrK1.length == 0) {
                return null;
            }
            return iVarArrK1[0];
        } catch (IllegalStateException e2) {
            ax.p302sb.c.h().g().d("FTP ILLEGALSTATE").m(e2).h("mode: " + this.j.K0()).i();
            throw new IOException("IllegalState : " + e2.getMessage());
        }
    }

    private String g0(long j2) {
        if (this.h == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            this.h = simpleDateFormat;
            simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        }
        return this.h.format(new Date(j2));
    }

    public static g h0(Context context) {
        if (v == null) {
            v = new g(context.getApplicationContext());
        }
        return v;
    }

    protected static void i0(Context context) {
        if (o0()) {
            AbstractC6238m.F(context);
        }
    }

    private boolean k0(int i2) {
        return ax.Gd.t.a(i2) || ax.Gd.t.b(i2);
    }

    public static boolean l0(int i2) {
        return i2 >= 100000000;
    }

    private static boolean o0() {
        return Build.VERSION.SDK_INT > 28;
    }

    public static ax.Gd.i p0(String str, String str2) throws IOException {
        if (str.charAt(0) != ' ') {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            if (str.startsWith("250-modify")) {
                return ax.Hd.g.g(str.substring(4));
            }
            if (lowerCase.startsWith("250 end")) {
                return null;
            }
            if (str.length() >= 2) {
                if (str.endsWith("; ") && str.startsWith("Size=") && "/".equals(str2)) {
                    String[] strArrSplit = str.split(" ", 2);
                    if (strArrSplit.length == 2 && strArrSplit[1].length() == 0) {
                        str = str + str2;
                    }
                }
                ax.Gd.i iVarG = ax.Hd.g.g(str);
                if (iVarG != null) {
                    return iVarG;
                }
            }
        }
        if (str.equals(" /") && "/".equals(str2)) {
            ax.Gd.i iVar = new ax.Gd.i();
            iVar.q(1);
            iVar.l(str2);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(0L);
            iVar.n(str);
            iVar.p(calendar);
            return iVar;
        }
        if (str.charAt(0) != ' ') {
            str = " " + str;
        }
        if (str.length() >= 3) {
            return ax.Hd.g.g(str.replaceAll("^\\s+", HttpUrl.FRAGMENT_ENCODE_SET));
        }
        throw new ax.Fd.a("Invalid server reply (MLST): '" + str + "'");
    }

    private void q0() {
        b();
        W();
    }

    private boolean t0(ax.Gd.e eVar, AbstractC6239n abstractC6239n, long j2, boolean z) {
        if (j2 <= 0) {
            return false;
        }
        try {
            if (!w0() || !eVar.w()) {
                return false;
            }
            if (z) {
                eVar.X(abstractC6239n.E(), g0(j2));
                return true;
            }
            eVar.X(abstractC6239n.B(), g0(j2));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean v0() {
        return this.s != x;
    }

    private boolean w0() throws IOException {
        return this.j.Z0("MFMT");
    }

    private boolean x0() throws IOException {
        return this.j.Z0("MLSD") || this.j.Z0("MLST");
    }

    private boolean y0() throws IOException {
        return this.j.Z0("MLST");
    }

    private void z0() {
        boolean zV1;
        boolean z;
        synchronized (this.k) {
            try {
                zV1 = true;
                z = (a() && this.j.v()) ? false : true;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            W();
            return;
        }
        synchronized (this.k) {
            try {
                zV1 = true ^ this.j.v1();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (zV1) {
                q0();
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean D() {
        return this.t;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean Q() {
        return !this.q;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        ax.Gd.e eVar = this.j;
        return eVar != null && eVar.w();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws Throwable {
        Throwable th;
        ax.Gd.i iVarN0;
        z0();
        try {
            try {
                synchronized (this.k) {
                    try {
                        try {
                            if (y0()) {
                                try {
                                    try {
                                        iVarN0 = n0(this.j, str);
                                    } catch (RuntimeException e2) {
                                        e2.printStackTrace();
                                        ax.p302sb.c.h().g().d("FTP MLST ERROR").m(e2).h(this.j.S()).i();
                                        iVarN0 = null;
                                    }
                                } catch (ax.Fd.a e3) {
                                    e3.printStackTrace();
                                    if (!"/".equals(str)) {
                                        ax.p302sb.c.h().g().d("FTP INVALID REPLY!!!! FIX CODE FOR THIS CASE").m(e3).h(this.j.S() + ",path:" + str + " ,welcomeMessage:" + this.o).i();
                                    }
                                    iVarN0 = null;
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    iVarN0 = null;
                                }
                            } else {
                                iVarN0 = null;
                            }
                            if (iVarN0 == null) {
                                iVarN0 = f0(str);
                            } else {
                                String strTrim = iVarN0.b().trim();
                                if (!d0.A(strTrim)) {
                                    strTrim = d0.h(d0.U(strTrim));
                                    iVarN0.l(strTrim);
                                }
                                if (!strTrim.equals(d0.h(str))) {
                                    iVarN0 = f0(str);
                                }
                            }
                            ax.Gd.i iVar = iVarN0;
                            if (iVar == null) {
                                return new ax.p086f3.D(this, str, Z(str));
                            }
                            return new ax.p086f3.D(this, this.j, iVar, str, true);
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        th = th;
                    }
                    try {
                        throw th;
                    } catch (ax.Hd.n e5) {
                        e = e5;
                        ax.Hd.n nVar = e;
                        ax.p302sb.c.h().d("PARSER ERROR").m(nVar).i();
                        throw new C1966j(nVar);
                    } catch (IOException e6) {
                        e = e6;
                        throw a0("FTP getfileinfo", e);
                    } catch (ArrayIndexOutOfBoundsException e7) {
                        e = e7;
                        throw new C1966j(e);
                    } catch (NullPointerException e8) {
                        e = e8;
                        throw new C1966j(e);
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (ax.Hd.n e9) {
            e = e9;
        } catch (IOException e10) {
            e = e10;
        } catch (ArrayIndexOutOfBoundsException e11) {
            e = e11;
        } catch (NullPointerException e12) {
            e = e12;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        synchronized (this.k) {
            try {
                this.n = null;
                d0(this.j);
                e eVar = this.l;
                if (eVar != null) {
                    eVar.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        if (this.q) {
            return null;
        }
        return w(str, str2);
    }

    void c0(String str, String str2) {
        this.o = str;
        this.p = str2;
        this.s = w;
        if (str != null) {
            if (str.contains("FileZilla Server")) {
                this.s = x;
                return;
            }
            if (str.contains("ESP8266")) {
                this.s = y;
                u0(true);
            } else if (str.contains("lima-city.de")) {
                this.t = true;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // ax.p086f3.i0
    public boolean d(AbstractC6239n abstractC6239n, long j2) {
        boolean zT0;
        if (j2 < 0 || !(abstractC6239n instanceof ax.p086f3.D)) {
            return false;
        }
        z0();
        synchronized (this.k) {
            zT0 = t0(this.j, abstractC6239n, j2, true);
        }
        return zT0;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        C2461b.c(abstractC6239n.isDirectory());
        z0();
        String strB = abstractC6239n.B();
        String strE = abstractC6239n.E();
        try {
            synchronized (this.k) {
                try {
                    if (v0()) {
                        this.j.z1(true);
                    }
                    int iY = Y(this.j, strE);
                    if (!ax.Gd.t.c(iY)) {
                        throw b0("FTP listChildren CWD", iY, this.j.S(), false);
                    }
                    if (x0()) {
                        try {
                            List<AbstractC6239n> listM0 = m0(strB, strE, true);
                            if (listM0 != null) {
                                return listM0;
                            }
                        } catch (ax.Hd.n e2) {
                            e = e2;
                            e.printStackTrace();
                        } catch (C1966j e3) {
                            e = e3;
                            e.printStackTrace();
                        } catch (IllegalArgumentException e4) {
                            e = e4;
                            e.printStackTrace();
                        } catch (IllegalStateException e5) {
                            e = e5;
                            e.printStackTrace();
                        } catch (NullPointerException e6) {
                            e = e6;
                            e.printStackTrace();
                        } catch (StringIndexOutOfBoundsException e7) {
                            e = e7;
                            e.printStackTrace();
                        }
                    }
                    return m0(strB, strE, false);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (ax.Hd.n e8) {
            ax.p302sb.c.h().d("PARSER ERROR").m(e8).i();
            throw new C1966j(e8);
        } catch (IOException e9) {
            throw a0("FTP listchildren FTPS=" + j0(), e9);
        } catch (IllegalArgumentException e10) {
            throw new C1966j(e10);
        } catch (IllegalStateException e11) {
            throw new C1966j(e11);
        } catch (NullPointerException e12) {
            throw new C1966j(e12);
        } catch (StringIndexOutOfBoundsException e13) {
            throw new C1966j(e13);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (!this.q && H(abstractC6239n)) {
            return AbstractC6238m.A(abstractC6239n);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        C2461b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j2) throws C1966j {
        ax.Gd.e eVarF;
        f fVar;
        e eVar = this.l;
        if (eVar == null) {
            throw new C1964h("Not connected : ftp");
        }
        try {
            if (this.q) {
                z0();
                eVarF = this.j;
            } else {
                try {
                    eVarF = eVar.f();
                    this.r = true;
                } catch (ax.p070e3.K e2) {
                    if (this.r) {
                        throw new C1966j(e2.getMessage(), e2);
                    }
                    u0(true);
                    z0();
                    eVarF = this.j;
                }
            }
            synchronized ((eVarF == this.j ? this.k : eVarF)) {
                try {
                    if (((h) eVarF).c() != 2) {
                        if (eVarF.y1(2)) {
                            ((h) eVarF).b(2);
                        } else {
                            ((h) eVarF).b(0);
                        }
                    }
                    eVarF.B1(j2);
                    if (!eVarF.w()) {
                        throw new ax.p070e3.q("FTP client is not connected");
                    }
                    InputStream inputStreamU1 = eVarF.u1(abstractC6239n.E());
                    if (inputStreamU1 == null) {
                        throw b0("FTP getInputStream", eVarF.R(), eVarF.S(), true);
                    }
                    fVar = new f(eVarF, inputStreamU1);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return fVar;
        } catch (IOException e3) {
            throw a0("FTP getinputstream", e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        z0();
        C2461b.a(abstractC6239n2.o());
        try {
            synchronized (this.k) {
                try {
                    long jP = abstractC6239n.p();
                    if (!this.j.s1(abstractC6239n.E(), abstractC6239n2.E())) {
                        throw b0("FTP moveFile", this.j.R(), this.j.S(), true);
                    }
                    if (iVar != null) {
                        iVar.a(jP, jP);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IOException e2) {
            throw a0("FTP moveFile", e2);
        }
    }

    boolean j0() {
        return this.j instanceof j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        try {
            d dVar = this.i;
            if (dVar != null && !dVar.isCancelled()) {
                this.i.e();
            }
            d dVar2 = new d(p(), this, t(), aVar);
            dVar2.i(new Object[0]);
            this.i = dVar2;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (aVar != null) {
                aVar.J();
                aVar.d0(false, e2.getMessage());
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:117:0x0194 A[Catch: all -> 0x0180, TRY_ENTER, TryCatch #13 {all -> 0x0180, blocks: (B:57:0x00cd, B:98:0x015f, B:100:0x0169, B:127:0x01b0, B:108:0x0182, B:95:0x0152, B:117:0x0194, B:118:0x019b), top: B:172:0x00cd }] */
    /* JADX WARN: Code duplicated, block: B:125:0x01a5 A[Catch: all -> 0x01a2, TRY_ENTER, TryCatch #0 {all -> 0x01a2, blocks: (B:37:0x004d, B:39:0x005c, B:125:0x01a5, B:126:0x01af), top: B:161:0x004d }] */
    /* JADX WARN: Code duplicated, block: B:156:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:161:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:164:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x01e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:200:0x00da A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:206:? A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x0047 A[Catch: all -> 0x0029, IOException -> 0x002e, TRY_ENTER, TRY_LEAVE, TryCatch #27 {IOException -> 0x002e, all -> 0x0029, blocks: (B:33:0x0047, B:15:0x0026), top: B:181:0x0026 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x004b  */
    /* JADX WARN: Code duplicated, block: B:39:0x005c A[Catch: all -> 0x01a2, TRY_LEAVE, TryCatch #0 {all -> 0x01a2, blocks: (B:37:0x004d, B:39:0x005c, B:125:0x01a5, B:126:0x01af), top: B:161:0x004d }] */
    /* JADX WARN: Code duplicated, block: B:54:0x00bc A[Catch: all -> 0x019f, TRY_ENTER, TRY_LEAVE, TryCatch #27 {all -> 0x019f, blocks: (B:40:0x006c, B:54:0x00bc), top: B:187:0x006c }] */
    /* JADX WARN: Code duplicated, block: B:59:0x00d3 A[LOOP:0: B:59:0x00d3->B:202:0x00d3, LOOP_START, PHI: r6
      0x00d3: PHI (r6v1 int) = (r6v0 int), (r6v2 int) binds: [B:58:0x00d1, B:202:0x00d3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:63:0x00dc A[Catch: all -> 0x0103, IOException -> 0x0107, SocketTimeoutException -> 0x0124, TryCatch #26 {SocketTimeoutException -> 0x0124, IOException -> 0x0107, blocks: (B:60:0x00d4, B:63:0x00dc, B:64:0x0102), top: B:192:0x00d4, outer: #15 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x0152 A[Catch: all -> 0x0180, TRY_ENTER, TryCatch #13 {all -> 0x0180, blocks: (B:57:0x00cd, B:98:0x015f, B:100:0x0169, B:127:0x01b0, B:108:0x0182, B:95:0x0152, B:117:0x0194, B:118:0x019b), top: B:172:0x00cd }] */
    /* JADX WARN: Instruction removed from duplicated block: B:63:0x00dc, please report this as an issue */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j2, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        ax.Gd.e eVarF;
        InputStream inputStreamB;
        OutputStream outputStream;
        ax.Gd.e eVar;
        Object obj;
        int iY;
        int i2;
        OutputStream outputStreamD1;
        long jC;
        ax.Gd.e eVar2;
        z0();
        C2461b.a(abstractC6239n.o());
        OutputStream outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        ax.Gd.e eVar3 = null;
        try {
            try {
                if (this.q) {
                    eVar = this.j;
                } else {
                    try {
                        eVarF = this.l.f();
                        try {
                            try {
                                this.r = true;
                            } catch (ax.p070e3.K e2) {
                                e = e2;
                                try {
                                    if (this.r) {
                                        throw new C1966j(e.getMessage(), e);
                                    }
                                    u0(true);
                                    eVar = this.j;
                                    eVarF = eVar;
                                } catch (IOException e3) {
                                    e = e3;
                                    outputStream = null;
                                    inputStreamB = null;
                                    eVar3 = eVarF;
                                    try {
                                        throw a0("FTP writefile", e);
                                    } catch (Throwable th) {
                                        th = th;
                                        eVarF = eVar3;
                                        outputStream2 = outputStream;
                                        if (outputStream2 != null) {
                                            try {
                                                outputStream2.close();
                                            } catch (IOException unused) {
                                            }
                                        }
                                        if (inputStreamB != null) {
                                            try {
                                                inputStreamB.close();
                                            } catch (IOException unused2) {
                                            }
                                        }
                                        if (eVarF != null || eVarF == this.j) {
                                            throw th;
                                        }
                                        this.l.i(eVarF);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    inputStreamB = null;
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (inputStreamB != null) {
                                        inputStreamB.close();
                                    }
                                    if (eVarF != null) {
                                        throw th;
                                    }
                                    throw th;
                                }
                            }
                            if (eVarF == this.j) {
                                obj = this.k;
                            } else {
                                obj = eVarF;
                            }
                            synchronized (obj) {
                                try {
                                    iY = Y(eVarF, abstractC6239n.U());
                                    i2 = 0;
                                    if (ax.Gd.t.c(iY)) {
                                        throw b0("FTP writeFile CWD", iY, eVarF.S(), false);
                                    }
                                    ((h) eVarF).a(30);
                                    outputStreamD1 = eVarF.D1(abstractC6239n.B());
                                    try {
                                        ((h) eVarF).a(-1);
                                        if (outputStreamD1 == null) {
                                            try {
                                                int iR = eVarF.R();
                                                String strS = eVarF.S();
                                                u.severe("StoreFileStream returns null : reply : " + iR + ":" + strS);
                                                if (eVarF.w() && ax.Gd.t.a(eVarF.R())) {
                                                    try {
                                                        eVarF.o();
                                                    } catch (IOException unused3) {
                                                    }
                                                }
                                                throw b0("FTP writeFile STOR", iR, strS, false);
                                            } catch (Throwable th3) {
                                                th = th3;
                                            }
                                        } else {
                                            inputStreamB = g2.b();
                                            try {
                                                jC = ax.p086f3.F.c(inputStreamB, outputStreamD1, j2, interfaceC2462c, iVar);
                                                outputStreamD1.close();
                                                try {
                                                    if (eVarF.w()) {
                                                        while (true) {
                                                            i2++;
                                                            try {
                                                                try {
                                                                    if (eVarF.C0()) {
                                                                        break;
                                                                    }
                                                                    throw new C1966j("FTP ERROR : " + eVarF.R() + ":" + eVarF.S());
                                                                } catch (Throwable th4) {
                                                                    th = th4;
                                                                }
                                                            } catch (SocketTimeoutException unused4) {
                                                                if (!((h) eVarF).e() || i2 >= 20) {
                                                                    try {
                                                                        eVarF.o();
                                                                    } catch (IOException unused5) {
                                                                    }
                                                                    if (jC >= j2) {
                                                                        break;
                                                                    } else {
                                                                        throw new C1966j("FTP ERROR : completePendingCommand error 1");
                                                                    }
                                                                } else if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                                                                    throw new C1957a();
                                                                }
                                                            } catch (IOException e4) {
                                                                e4.printStackTrace();
                                                                u.fine("FTP completePendingCommand Error");
                                                                try {
                                                                    eVarF.o();
                                                                } catch (IOException unused6) {
                                                                }
                                                                if (jC < j2) {
                                                                    throw new C1966j("FTP ERROR : completePendingCommand error 2");
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        u.fine("FTP client is disconnected");
                                                        if (jC < j2) {
                                                            throw new C1966j("FTP ERROR : Socket is disconnected");
                                                        }
                                                    }
                                                    if (l != null && l.longValue() > 0) {
                                                        eVar2 = eVarF;
                                                        try {
                                                            t0(eVar2, abstractC6239n, l.longValue(), false);
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                            eVarF = eVar2;
                                                        }
                                                    }
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                }
                                            } catch (Throwable th7) {
                                                th = th7;
                                                outputStream2 = outputStreamD1;
                                            }
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                    }
                                } catch (Throwable th9) {
                                    th = th9;
                                    inputStreamB = null;
                                }
                            }
                            try {
                                throw th;
                            } catch (IOException e5) {
                                e = e5;
                                outputStream = outputStream2;
                                eVar3 = eVarF;
                                throw a0("FTP writefile", e);
                            } catch (Throwable th10) {
                                th = th10;
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                if (inputStreamB != null) {
                                    inputStreamB.close();
                                }
                                if (eVarF != null) {
                                    throw th;
                                }
                                throw th;
                            }
                        } catch (IOException e6) {
                            e = e6;
                            outputStream = null;
                            inputStreamB = null;
                            eVar3 = eVarF;
                            throw a0("FTP writefile", e);
                        } catch (Throwable th11) {
                            th = th11;
                            inputStreamB = null;
                            if (outputStream2 != null) {
                                outputStream2.close();
                            }
                            if (inputStreamB != null) {
                                inputStreamB.close();
                            }
                            if (eVarF != null) {
                                throw th;
                            }
                            throw th;
                        }
                    } catch (ax.p070e3.K e7) {
                        e = e7;
                        eVarF = null;
                    }
                }
                eVarF = eVar;
                if (eVarF == this.j) {
                    obj = this.k;
                } else {
                    obj = eVarF;
                }
                synchronized (obj) {
                    iY = Y(eVarF, abstractC6239n.U());
                    i2 = 0;
                    if (ax.Gd.t.c(iY)) {
                        throw b0("FTP writeFile CWD", iY, eVarF.S(), false);
                    }
                    ((h) eVarF).a(30);
                    outputStreamD1 = eVarF.D1(abstractC6239n.B());
                    ((h) eVarF).a(-1);
                    if (outputStreamD1 == null) {
                        int iR2 = eVarF.R();
                        String strS2 = eVarF.S();
                        u.severe("StoreFileStream returns null : reply : " + iR2 + ":" + strS2);
                        if (eVarF.w()) {
                            eVarF.o();
                        }
                        throw b0("FTP writeFile STOR", iR2, strS2, false);
                    }
                    inputStreamB = g2.b();
                    jC = ax.p086f3.F.c(inputStreamB, outputStreamD1, j2, interfaceC2462c, iVar);
                    outputStreamD1.close();
                    if (eVarF.w()) {
                        while (true) {
                            i2++;
                            if (eVarF.C0()) {
                                break;
                                break;
                            }
                            throw new C1966j("FTP ERROR : " + eVarF.R() + ":" + eVarF.S());
                        }
                    }
                    u.fine("FTP client is disconnected");
                    if (jC < j2) {
                        throw new C1966j("FTP ERROR : Socket is disconnected");
                    }
                    if (l != null) {
                        eVar2 = eVarF;
                        t0(eVar2, abstractC6239n, l.longValue(), false);
                    }
                    if (inputStreamB != null) {
                        try {
                            inputStreamB.close();
                        } catch (IOException unused7) {
                        }
                    }
                    if (eVarF != this.j) {
                        this.l.i(eVarF);
                        return;
                    }
                    return;
                    inputStreamB = null;
                    outputStream2 = outputStreamD1;
                    throw th;
                }
            } catch (IOException e8) {
                e = e8;
                outputStream = null;
                inputStreamB = null;
                throw a0("FTP writefile", e);
            } catch (Throwable th12) {
                th = th12;
                eVarF = null;
                inputStreamB = null;
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (inputStreamB != null) {
                    inputStreamB.close();
                }
                if (eVarF != null) {
                    throw th;
                }
                throw th;
            }
        } catch (IOException e9) {
            e = e9;
        } catch (Throwable th13) {
            th = th13;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x000c */
    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    synchronized void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        try {
            o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
        } catch (Throwable th) {
            th = th;
            while (true) {
                throw th;
            }
        }
    }

    List<AbstractC6239n> m0(String str, String str2, boolean z) throws C1966j, IOException {
        int i2;
        ArrayList arrayList = new ArrayList();
        if (z) {
            ax.Gd.i[] iVarArrN1 = this.j.n1();
            int iR = this.j.R();
            if (!ax.Gd.t.c(iR)) {
                if (iR == 425 || iR == 426) {
                    q0();
                }
                return null;
            }
            for (ax.Gd.i iVar : iVarArrN1) {
                if (iVar != null && !d0.C(iVar.b()) && !iVar.b().startsWith("/")) {
                    if (TextUtils.isEmpty(iVar.b().trim())) {
                        ax.p302sb.c.h().g().d("FTP EMPTY FILE NAME 1").h("name:" + iVar.b() + ",size:" + iVar.d() + ",dir:" + iVar.g()).i();
                    } else {
                        arrayList.add(new ax.p086f3.D(this, this.j, iVar, d0.Q(str2, iVar.b()), false));
                    }
                }
            }
        } else {
            ax.Gd.s sVarD1 = this.j.d1(null, null);
            int iR2 = this.j.R();
            if (iR2 == 425 || iR2 == 426) {
                q0();
                throw b0(j0() ? "FTP List data channel: Cannot support session reuse" : "FTP List data channel", iR2, this.j.S(), false);
            }
            ArrayList arrayList2 = new ArrayList();
            boolean z2 = false;
            while (sVarD1.g()) {
                if (!this.j.w()) {
                    throw new IOException("FTP disconnected while operation");
                }
                ax.Gd.i[] iVarArrF = sVarD1.f(1);
                if (iVarArrF.length > 0) {
                    ax.Gd.i iVar2 = iVarArrF[0];
                    if (iVar2 != null) {
                        arrayList2.add(iVar2);
                    } else {
                        z2 = true;
                    }
                }
            }
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                int i4 = i3 + 1;
                ax.Gd.i iVar3 = (ax.Gd.i) arrayList2.get(i3);
                if (iVar3 == null || d0.C(iVar3.b())) {
                    i2 = size;
                } else if (TextUtils.isEmpty(iVar3.b().trim())) {
                    i2 = size;
                    ax.p302sb.c.h().g().d("FTP EMPTY FILE NAME 2").h("name:" + iVar3.b() + ",size:" + iVar3.d() + ",dir:" + iVar3.g()).i();
                } else {
                    i2 = size;
                    arrayList.add(new ax.p086f3.D(this, this.j, iVar3, d0.Q(str2, iVar3.b().trim()), true));
                }
                i3 = i4;
                size = i2;
            }
            if (arrayList.size() == 0) {
                if (k0(iR2)) {
                    throw b0("FTP List", iR2, this.j.S(), false);
                }
                if (z2) {
                    try {
                        Field declaredField = ax.Gd.s.class.getDeclaredField("a");
                        declaredField.setAccessible(true);
                        List<String> list = (List) declaredField.get(sVarD1);
                        StringBuilder sb = new StringBuilder();
                        if (list != null) {
                            for (String str3 : list) {
                                if (str3 != null && !str3.endsWith(" .") && !str3.endsWith(" ..") && !str3.equals("..") && !str3.equals("\ufeff")) {
                                    sb.append(str3);
                                    sb.append("\n");
                                }
                            }
                        }
                        if (!TextUtils.isEmpty(sb.toString().trim())) {
                            ax.p302sb.c.h().g().b("FTP PARSE ERROR").h(((Object) sb) + ",welcome:" + this.o + ",system:" + this.p).i();
                            throw new C1966j("FTP Parse error");
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        if (!this.q) {
            l1(abstractC6239n2, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, iVar);
            return;
        }
        File fileA0 = abstractC6239n.a0();
        C6240o c6240oF = C2017x.f(fileA0);
        AbstractC6239n abstractC6239nA1 = c6240oF.a1(fileA0.getAbsolutePath());
        boolean zO = abstractC6239nA1.o();
        c6240oF.l1(abstractC6239nA1, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, new b(iVar));
        AbstractC6239n abstractC6239nA2 = c6240oF.a1(fileA0.getAbsolutePath());
        l1(abstractC6239n2, c6240oF.x(abstractC6239nA2), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, new c(iVar));
        if (zO) {
            return;
        }
        try {
            c6240oF.q1(abstractC6239nA2);
        } catch (C1966j unused) {
        }
    }

    public ax.Gd.i n0(ax.Gd.e eVar, String str) throws IOException {
        if (!ax.Gd.t.c(eVar.k0(ax.Gd.g.MLST, str))) {
            return null;
        }
        String[] strArrT = eVar.T();
        if (strArrT.length < 2) {
            u.severe("invalid reply : " + eVar.S());
            return null;
        }
        String str2 = strArrT[1];
        if (!str2.startsWith("550")) {
            return p0(str2, str);
        }
        try {
            String strX0 = eVar.X0();
            if (eVar.R() == 250) {
                u.severe("Invalid getStatus reply : " + strX0);
                eVar.P();
            }
        } catch (IOException unused) {
            q0();
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        boolean zM1;
        z0();
        try {
            synchronized (this.k) {
                zM1 = this.j.m1(abstractC6239n.E());
            }
            return zM1;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return l(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        z0();
        try {
            synchronized (this.k) {
                try {
                    if (!X(abstractC6239n.U())) {
                        throw new C1966j("deleteFile - CWD not successful");
                    }
                    if (abstractC6239n.isDirectory()) {
                        if (!this.j.r1(abstractC6239n.B())) {
                            throw b0("FTP deleteFile", this.j.R(), this.j.S(), true);
                        }
                    } else if (!this.j.E0(abstractC6239n.B())) {
                        throw b0("FTP deleteFile", this.j.R(), this.j.S(), true);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            throw a0("FTP deleteFile", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public String r() {
        return this.m;
    }

    void r0(ax.Gd.e eVar) {
        this.j = eVar;
        this.l = new e();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    public void s0(String str) {
        if (TextUtils.isEmpty(str)) {
            this.m = x();
        } else {
            this.m = str;
        }
    }

    void u0(boolean z) {
        this.q = z;
    }
}
