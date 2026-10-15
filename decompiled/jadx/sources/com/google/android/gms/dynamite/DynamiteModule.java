package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import ax.p217n6.C2177h;
import ax.p265q6.C2252n;
import ax.p265q6.C2254p;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamiteModule {
    private static Boolean h = null;
    private static String i = null;
    private static boolean j = false;
    private static int k = -1;
    private static Boolean l;
    private static m q;
    private static n r;
    private final Context a;
    private static final ThreadLocal m = new ThreadLocal();
    private static final ThreadLocal n = new com.google.android.gms.dynamite.b();
    private static final b.a o = new c();
    public static final b b = new d();
    public static final b c = new e();
    public static final b d = new f();
    public static final b e = new g();
    public static final b f = new h();
    public static final b g = new i();
    public static final b p = new j();

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public static class a extends Exception {
        /* synthetic */ a(String str, ax.p393y6.f fVar) {
            super(str);
        }

        /* synthetic */ a(String str, Throwable th, ax.p393y6.f fVar) {
            super(str, th);
        }
    }

    public interface b {

        public interface a {
            int a(Context context, String str, boolean z) throws a;

            int b(Context context, String str);
        }

        /* JADX INFO: renamed from: com.google.android.gms.dynamite.DynamiteModule$b$b, reason: collision with other inner class name */
        public static class C0567b {
            public int a = 0;
            public int b = 0;
            public int c = 0;
        }

        C0567b a(Context context, String str, a aVar) throws a;
    }

    private DynamiteModule(Context context) {
        C2254p.l(context);
        this.a = context;
    }

    public static int a(Context context, String str) {
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (C2252n.b(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e2.getMessage())));
            return 0;
        }
    }

    public static int c(Context context, String str) {
        return f(context, str, false);
    }

    /* JADX WARN: Code duplicated, block: B:117:0x0244  */
    /* JADX WARN: Code duplicated, block: B:118:0x024a  */
    /* JADX WARN: Code duplicated, block: B:121:0x0253  */
    /* JADX WARN: Code duplicated, block: B:126:0x0265 A[Catch: all -> 0x007d, TryCatch #7 {all -> 0x007d, blocks: (B:7:0x0040, B:11:0x0077, B:18:0x0083, B:21:0x0089, B:24:0x0091, B:103:0x01f7, B:104:0x0202, B:107:0x0205, B:108:0x0206, B:109:0x020e, B:126:0x0265, B:127:0x027c, B:110:0x020f, B:112:0x022d, B:114:0x023c, B:124:0x025c, B:125:0x0264, B:128:0x027d, B:129:0x02ad), top: B:149:0x0040, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:145:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:146:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:21:0x0089 A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #7 {all -> 0x007d, blocks: (B:7:0x0040, B:11:0x0077, B:18:0x0083, B:21:0x0089, B:24:0x0091, B:103:0x01f7, B:104:0x0202, B:107:0x0205, B:108:0x0206, B:109:0x020e, B:126:0x0265, B:127:0x027c, B:110:0x020f, B:112:0x022d, B:114:0x023c, B:124:0x025c, B:125:0x0264, B:128:0x027d, B:129:0x02ad), top: B:149:0x0040, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:23:0x008f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x009c A[Catch: all -> 0x01ea, TryCatch #5 {, blocks: (B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4), top: B:146:0x0096, outer: #8 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a1 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TRY_ENTER, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00a8 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00ce A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TRY_ENTER, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0148 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0154 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0178 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:80:0x017f A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0187 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x0196 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x019f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:86:0x01a1 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01b1 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x01c6 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x01cf A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x01d8 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x01e1 A[Catch: all -> 0x0106, a -> 0x0109, RemoteException -> 0x010c, TryCatch #8 {RemoteException -> 0x010c, a -> 0x0109, all -> 0x0106, blocks: (B:26:0x0095, B:32:0x00a1, B:34:0x00a8, B:35:0x00c8, B:39:0x00ce, B:41:0x00d6, B:43:0x00da, B:44:0x00e5, B:52:0x00f2, B:60:0x0122, B:62:0x012a, B:64:0x0132, B:65:0x013b, B:59:0x010f, B:68:0x013e, B:69:0x013f, B:70:0x0147, B:71:0x0148, B:72:0x0150, B:75:0x0153, B:76:0x0154, B:78:0x0178, B:80:0x017f, B:82:0x0187, B:88:0x01c0, B:90:0x01c6, B:91:0x01cf, B:92:0x01d7, B:83:0x0196, B:84:0x019e, B:86:0x01a1, B:87:0x01b1, B:93:0x01d8, B:94:0x01e0, B:95:0x01e1, B:96:0x01e9, B:102:0x01f6, B:36:0x00c9, B:37:0x00cb, B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4, B:46:0x00e8, B:50:0x00ef), top: B:152:0x0095, inners: #0, #5, #6 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x01ec A[Catch: all -> 0x01ea, TRY_ENTER, TryCatch #5 {, blocks: (B:27:0x0096, B:29:0x009c, B:30:0x009e, B:99:0x01ec, B:100:0x01f4), top: B:146:0x0096, outer: #8 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:126:0x0265, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:34:0x00a8, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:76:0x0154, please report this as an issue */
    @ResultIgnorabilityUnspecified
    public static DynamiteModule e(Context context, b bVar, String str) throws Throwable {
        long j2;
        DynamiteModule dynamiteModuleH;
        int i2;
        Boolean bool;
        m mVarL;
        int iD;
        ax.p377x6.a aVarL3;
        Object objE1;
        DynamiteModule dynamiteModule;
        k kVar;
        n nVar;
        k kVar2;
        boolean z;
        ax.p377x6.a aVarE1;
        Cursor cursor;
        Context applicationContext = context.getApplicationContext();
        ax.p393y6.f fVar = null;
        if (applicationContext == null) {
            throw new a("null application Context", null);
        }
        ThreadLocal threadLocal = m;
        k kVar3 = (k) threadLocal.get();
        k kVar4 = new k(null);
        threadLocal.set(kVar4);
        ThreadLocal threadLocal2 = n;
        Long l2 = (Long) threadLocal2.get();
        long jLongValue = l2.longValue();
        try {
            threadLocal2.set(Long.valueOf(SystemClock.elapsedRealtime()));
            b.C0567b c0567bA = bVar.a(context, str, o);
            int i3 = c0567bA.a;
            j2 = 0;
            try {
                Log.i("DynamiteModule", "Considering local module " + str + ":" + i3 + " and remote module " + str + ":" + c0567bA.b);
                int i4 = c0567bA.c;
                if (i4 != 0) {
                    if (i4 != -1) {
                        if (i4 == 1 || c0567bA.b != 0) {
                            if (i4 == -1) {
                                dynamiteModuleH = h(applicationContext, str);
                            } else {
                                if (i4 == 1) {
                                    throw new a("VersionPolicy returned invalid code:" + i4, null);
                                }
                                try {
                                    i2 = c0567bA.b;
                                    try {
                                        synchronized (DynamiteModule.class) {
                                            if (k(context)) {
                                                throw new a("Remote loading disabled", null);
                                            }
                                            bool = h;
                                        }
                                        if (bool != null) {
                                            throw new a("Failed to determine which loading route to use.", null);
                                        }
                                        if (bool.booleanValue()) {
                                            Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i2);
                                            synchronized (DynamiteModule.class) {
                                                nVar = r;
                                            }
                                            if (nVar != null) {
                                                throw new a("DynamiteLoaderV2 was not cached.", null);
                                            }
                                            kVar2 = (k) threadLocal.get();
                                            if (kVar2 != null || kVar2.a == null) {
                                                throw new a("No result cursor", null);
                                            }
                                            Context applicationContext2 = context.getApplicationContext();
                                            Cursor cursor2 = kVar2.a;
                                            ax.p377x6.b.N2(null);
                                            synchronized (DynamiteModule.class) {
                                                z = k >= 2;
                                            }
                                            if (z) {
                                                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                                aVarE1 = nVar.N2(ax.p377x6.b.N2(applicationContext2), str, i2, ax.p377x6.b.N2(cursor2));
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                                aVarE1 = nVar.e1(ax.p377x6.b.N2(applicationContext2), str, i2, ax.p377x6.b.N2(cursor2));
                                            }
                                            Context context2 = (Context) ax.p377x6.b.e1(aVarE1);
                                            if (context2 == null) {
                                                throw new a("Failed to get module context", fVar);
                                            }
                                            dynamiteModule = new DynamiteModule(context2);
                                        } else {
                                            Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i2);
                                            mVarL = l(context);
                                            if (mVarL != null) {
                                                throw new a("Failed to create IDynamiteLoader.", null);
                                            }
                                            iD = mVarL.d();
                                            if (iD >= 3) {
                                                kVar = (k) threadLocal.get();
                                                if (kVar != null) {
                                                    throw new a("No cached result cursor holder", null);
                                                }
                                                aVarL3 = mVarL.Y3(ax.p377x6.b.N2(context), str, i2, ax.p377x6.b.N2(kVar.a));
                                            } else if (iD == 2) {
                                                Log.w("DynamiteModule", "IDynamite loader version = 2");
                                                aVarL3 = mVarL.s5(ax.p377x6.b.N2(context), str, i2);
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                                aVarL3 = mVarL.L3(ax.p377x6.b.N2(context), str, i2);
                                            }
                                            objE1 = ax.p377x6.b.e1(aVarL3);
                                            if (objE1 != null) {
                                                throw new a("Failed to load remote module.", null);
                                            }
                                            dynamiteModule = new DynamiteModule((Context) objE1);
                                        }
                                        dynamiteModuleH = dynamiteModule;
                                    } catch (RemoteException e2) {
                                        throw new a("Failed to load remote module.", e2, null);
                                    } catch (a e3) {
                                        throw e3;
                                    } catch (Throwable th) {
                                        ax.p345v6.h.a(context, th);
                                        throw new a("Failed to load remote module.", th, null);
                                    }
                                } catch (a e4) {
                                    Log.w("DynamiteModule", "Failed to load remote module: " + e4.getMessage());
                                    int i5 = c0567bA.a;
                                    if (i5 == 0 || bVar.a(context, str, new l(i5, 0)).c != -1) {
                                        throw new a("Remote load failed. No local fallback found.", e4, null);
                                    }
                                    dynamiteModuleH = h(applicationContext, str);
                                }
                            }
                            if (jLongValue == 0) {
                                n.remove();
                            } else {
                                n.set(l2);
                            }
                            cursor = kVar4.a;
                            if (cursor != null) {
                                cursor.close();
                            }
                            m.set(kVar3);
                            return dynamiteModuleH;
                        }
                    } else if (c0567bA.a != 0) {
                        i4 = -1;
                        if (i4 == 1) {
                        }
                        if (i4 == -1) {
                            dynamiteModuleH = h(applicationContext, str);
                        } else {
                            if (i4 == 1) {
                                throw new a("VersionPolicy returned invalid code:" + i4, null);
                            }
                            i2 = c0567bA.b;
                            synchronized (DynamiteModule.class) {
                                if (k(context)) {
                                    throw new a("Remote loading disabled", null);
                                }
                                bool = h;
                                if (bool != null) {
                                    throw new a("Failed to determine which loading route to use.", null);
                                }
                                if (bool.booleanValue()) {
                                    Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i2);
                                    synchronized (DynamiteModule.class) {
                                        nVar = r;
                                        if (nVar != null) {
                                            throw new a("DynamiteLoaderV2 was not cached.", null);
                                        }
                                        kVar2 = (k) threadLocal.get();
                                        if (kVar2 != null) {
                                        }
                                        throw new a("No result cursor", null);
                                    }
                                }
                                Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i2);
                                mVarL = l(context);
                                if (mVarL != null) {
                                    throw new a("Failed to create IDynamiteLoader.", null);
                                }
                                iD = mVarL.d();
                                if (iD >= 3) {
                                    kVar = (k) threadLocal.get();
                                    if (kVar != null) {
                                        throw new a("No cached result cursor holder", null);
                                    }
                                    aVarL3 = mVarL.Y3(ax.p377x6.b.N2(context), str, i2, ax.p377x6.b.N2(kVar.a));
                                } else if (iD == 2) {
                                    Log.w("DynamiteModule", "IDynamite loader version = 2");
                                    aVarL3 = mVarL.s5(ax.p377x6.b.N2(context), str, i2);
                                } else {
                                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                    aVarL3 = mVarL.L3(ax.p377x6.b.N2(context), str, i2);
                                }
                                objE1 = ax.p377x6.b.e1(aVarL3);
                                if (objE1 != null) {
                                    throw new a("Failed to load remote module.", null);
                                }
                                dynamiteModule = new DynamiteModule((Context) objE1);
                                dynamiteModuleH = dynamiteModule;
                            }
                        }
                        if (jLongValue == 0) {
                            n.remove();
                        } else {
                            n.set(l2);
                        }
                        cursor = kVar4.a;
                        if (cursor != null) {
                            cursor.close();
                        }
                        m.set(kVar3);
                        return dynamiteModuleH;
                    }
                }
                throw new a("No acceptable module " + str + " found. Local version is " + c0567bA.a + " and remote version is " + c0567bA.b + ".", null);
            } catch (Throwable th2) {
                th = th2;
                if (jLongValue == j2) {
                    n.remove();
                } else {
                    n.set(l2);
                }
                Cursor cursor3 = kVar4.a;
                if (cursor3 != null) {
                    cursor3.close();
                }
                m.set(kVar3);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            j2 = 0;
        }
    }

    /* JADX INFO: Removed unreachable split cross block B:140:0x01cc */
    /* JADX WARN: Code duplicated, block: B:106:0x017d A[Catch: all -> 0x00ec, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x00ec, blocks: (B:3:0x0002, B:64:0x00e1, B:66:0x00e7, B:74:0x010d, B:102:0x016f, B:106:0x017d, B:124:0x01d2, B:125:0x01d5, B:119:0x01c9, B:72:0x00f2, B:127:0x01d7, B:4:0x0003, B:7:0x0009, B:8:0x0025, B:62:0x00de, B:21:0x0049, B:45:0x00a1, B:48:0x00a4, B:55:0x00bc, B:63:0x00e0, B:61:0x00c2), top: B:134:0x0002, inners: #5, #12 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x00b0 A[Catch: all -> 0x0036, TryCatch #11 {all -> 0x0036, blocks: (B:9:0x0026, B:11:0x0032, B:52:0x00b9, B:16:0x003b, B:18:0x0042, B:20:0x0048, B:25:0x004f, B:27:0x0053, B:31:0x005d, B:33:0x0065, B:36:0x006c, B:43:0x0098, B:44:0x00a0, B:39:0x0073, B:41:0x0079, B:42:0x008a, B:47:0x00a3, B:50:0x00a6, B:51:0x00b0, B:17:0x003e), top: B:143:0x0026, inners: #2 }] */
    public static int f(Context context, String str, boolean z) {
        Throwable th;
        RemoteException remoteException;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = h;
                Cursor cursor2 = null;
                int iE1 = 0;
                if (bool == null) {
                    try {
                        Field declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                        synchronized (declaredField.getDeclaringClass()) {
                            try {
                                ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else if (classLoader != null) {
                                    try {
                                        i(classLoader);
                                    } catch (a unused) {
                                    }
                                    bool = Boolean.TRUE;
                                } else {
                                    if (!k(context)) {
                                        return 0;
                                    }
                                    if (j) {
                                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        bool = Boolean.FALSE;
                                    } else {
                                        Boolean bool2 = Boolean.TRUE;
                                        if (bool2.equals(null)) {
                                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                                            bool = Boolean.FALSE;
                                        } else {
                                            try {
                                                int iG = g(context, str, z, true);
                                                String str2 = i;
                                                if (str2 != null && !str2.isEmpty()) {
                                                    ClassLoader classLoaderA = ax.p393y6.d.a();
                                                    if (classLoaderA == null) {
                                                        if (Build.VERSION.SDK_INT >= 29) {
                                                            ax.p393y6.b.a();
                                                            String str3 = i;
                                                            C2254p.l(str3);
                                                            classLoaderA = ax.p393y6.a.a(str3, ClassLoader.getSystemClassLoader());
                                                        } else {
                                                            String str4 = i;
                                                            C2254p.l(str4);
                                                            classLoaderA = new com.google.android.gms.dynamite.a(str4, ClassLoader.getSystemClassLoader());
                                                        }
                                                    }
                                                    i(classLoaderA);
                                                    declaredField.set(null, classLoaderA);
                                                    h = bool2;
                                                    return iG;
                                                }
                                                return iG;
                                            } catch (a unused2) {
                                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                                bool = Boolean.FALSE;
                                            }
                                        }
                                    }
                                }
                                h = bool;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e2.toString());
                        bool = Boolean.FALSE;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return g(context, str, z, false);
                    } catch (a e3) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e3.getMessage());
                        return 0;
                    }
                }
                m mVarL = l(context);
                try {
                    if (mVarL != null) {
                        try {
                            int iD = mVarL.d();
                            if (iD >= 3) {
                                k kVar = (k) m.get();
                                if (kVar == null || (cursor = kVar.a) == null) {
                                    Cursor cursor3 = (Cursor) ax.p377x6.b.e1(mVarL.y6(ax.p377x6.b.N2(context), str, z, ((Long) n.get()).longValue()));
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.moveToFirst()) {
                                                int i2 = cursor3.getInt(0);
                                                cursor2 = (i2 <= 0 || !j(cursor3)) ? cursor3 : null;
                                                if (cursor2 != null) {
                                                    cursor2.close();
                                                }
                                                iE1 = i2;
                                            } else {
                                                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                                if (cursor3 != null) {
                                                    cursor3.close();
                                                }
                                            }
                                        } catch (RemoteException e4) {
                                            remoteException = e4;
                                            cursor2 = cursor3;
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + remoteException.getMessage());
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            cursor2 = cursor3;
                                            if (cursor2 == null) {
                                                throw th;
                                            }
                                            cursor2.close();
                                            throw th;
                                        }
                                    } else {
                                        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                        if (cursor3 != null) {
                                            cursor3.close();
                                        }
                                    }
                                } else {
                                    iE1 = cursor.getInt(0);
                                }
                            } else if (iD == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                iE1 = mVarL.N2(ax.p377x6.b.N2(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                iE1 = mVarL.e1(ax.p377x6.b.N2(context), str, z);
                            }
                        } catch (RemoteException e5) {
                            remoteException = e5;
                        }
                    }
                    return iE1;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        } catch (Throwable th5) {
            ax.p345v6.h.a(context, th5);
            throw th5;
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:65:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    private static int g(Context context, String str, boolean z, boolean z2) throws Throwable {
        Throwable th;
        Exception exc;
        ?? r1 = 0;
        ?? r2 = 0;
        ?? r3 = 0;
        ?? r4 = 0;
        try {
            try {
                boolean z3 = true;
                Cursor cursorQuery = context.getContentResolver().query(new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartTime", String.valueOf(((Long) n.get()).longValue())).build(), null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            boolean z4 = false;
                            int i2 = cursorQuery.getInt(0);
                            if (i2 > 0) {
                                synchronized (DynamiteModule.class) {
                                    try {
                                        i = cursorQuery.getString(2);
                                        int columnIndex = cursorQuery.getColumnIndex("loaderVersion");
                                        if (columnIndex >= 0) {
                                            k = cursorQuery.getInt(columnIndex);
                                        }
                                        int columnIndex2 = cursorQuery.getColumnIndex("disableStandaloneDynamiteLoader2");
                                        if (columnIndex2 >= 0) {
                                            if (cursorQuery.getInt(columnIndex2) == 0) {
                                                z3 = false;
                                            }
                                            j = z3;
                                            z4 = z3;
                                        }
                                    } catch (Throwable th2) {
                                        throw th2;
                                    }
                                }
                                if (j(cursorQuery)) {
                                    cursorQuery = null;
                                }
                            }
                            if (z2 && z4) {
                                throw new a("forcing fallback to container DynamiteLoader impl", r3 == true ? 1 : 0);
                            }
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return i2;
                        }
                    } catch (Exception e2) {
                        exc = e2;
                        if (exc instanceof a) {
                            throw exc;
                        }
                        throw new a("V2 version check failed: " + exc.getMessage(), exc, r2 == true ? 1 : 0);
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new a("Failed to connect to dynamite module ContentResolver.", r4 == true ? 1 : 0);
            } catch (Throwable th3) {
                th = th3;
                r1 = context;
                if (r1 != 0) {
                    throw th;
                }
                r1.close();
                throw th;
            }
        } catch (Exception e3) {
            exc = e3;
        } catch (Throwable th4) {
            th = th4;
            if (r1 != 0) {
                throw th;
            }
            r1.close();
            throw th;
        }
    }

    private static DynamiteModule h(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void i(ClassLoader classLoader) throws a {
        n nVar;
        ax.p393y6.f fVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder == null) {
                nVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                nVar = iInterfaceQueryLocalInterface instanceof n ? (n) iInterfaceQueryLocalInterface : new n(iBinder);
            }
            r = nVar;
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new a("Failed to instantiate dynamite loader", e, fVar);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new a("Failed to instantiate dynamite loader", e, fVar);
        } catch (InstantiationException e4) {
            e = e4;
            throw new a("Failed to instantiate dynamite loader", e, fVar);
        } catch (NoSuchMethodException e5) {
            e = e5;
            throw new a("Failed to instantiate dynamite loader", e, fVar);
        } catch (InvocationTargetException e6) {
            e = e6;
            throw new a("Failed to instantiate dynamite loader", e, fVar);
        }
    }

    private static boolean j(Cursor cursor) {
        k kVar = (k) m.get();
        if (kVar == null || kVar.a != null) {
            return false;
        }
        kVar.a = cursor;
        return true;
    }

    private static boolean k(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(l)) {
            return true;
        }
        boolean z = false;
        if (l == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (C2177h.f().h(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                z = true;
            }
            l = Boolean.valueOf(z);
            if (z && (applicationInfo = providerInfoResolveContentProvider.applicationInfo) != null && (applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                j = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static m l(Context context) {
        m mVar;
        synchronized (DynamiteModule.class) {
            m mVar2 = q;
            if (mVar2 != null) {
                return mVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    mVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    mVar = iInterfaceQueryLocalInterface instanceof m ? (m) iInterfaceQueryLocalInterface : new m(iBinder);
                }
                if (mVar != null) {
                    q = mVar;
                    return mVar;
                }
            } catch (Exception e2) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e2.getMessage());
            }
            return null;
        }
    }

    @ResultIgnorabilityUnspecified
    public Context b() {
        return this.a;
    }

    public IBinder d(String str) throws a {
        try {
            return (IBinder) this.a.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            throw new a("Failed to instantiate module class: ".concat(String.valueOf(str)), e2, null);
        }
    }
}
