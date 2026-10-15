package com.alphainventor.filemanager.file;

import android.content.Context;
import ax.p290u9.C5731e;
import com.microsoft.graph.extensions.DriveItem;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
public class C implements ax.p318w9.n {
    private final ax.A9.e a;
    private final ax.p248r9.a b;
    private final ax.p262s9.d c;
    private final ax.p332x9.b d;
    private ax.p318w9.m e = new ax.p318w9.e();

    class a implements Runnable {
        final /* synthetic */ Class X;
        final /* synthetic */ Object Y;
        final /* synthetic */ ax.p262s9.e Z;
        final /* synthetic */ ax.p262s9.c h0;
        final /* synthetic */ ax.p318w9.o q;

        a(ax.p318w9.o oVar, Class cls, Object obj, ax.p262s9.e eVar, ax.p262s9.c cVar) {
            this.q = oVar;
            this.X = cls;
            this.Y = obj;
            this.Z = eVar;
            this.h0 = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C.this.c.d(C.this.k(this.q, this.X, this.Y, this.Z, null), this.h0);
            } catch (ax.p276t9.d e) {
                C.this.c.a(e, this.h0);
            }
        }
    }

    public C(Context context, ax.A9.e eVar, ax.p248r9.a aVar, ax.p262s9.d dVar, ax.p332x9.b bVar) {
        this.a = eVar;
        this.b = aVar;
        this.c = dVar;
        this.d = bVar;
    }

    private InputStream g(InputStream inputStream) {
        return inputStream;
    }

    private <Body> void h(ax.p318w9.o oVar, Body body, ax.p318w9.l lVar) throws IOException {
        throw ax.p318w9.j.b(oVar, body, this.a, lVar);
    }

    private <Result> Result i(InputStream inputStream, Class<Result> cls) {
        if (cls == null) {
            return null;
        }
        return (Result) f().b(l(inputStream), cls);
    }

    private Boolean j(List<ax.p360z9.b> list, String str) {
        Iterator<ax.p360z9.b> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().a() == str) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:102:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:114:0x01f6 A[Catch: all -> 0x0211, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x0211, blocks: (B:114:0x01f6, B:126:0x021d), top: B:233:0x01f4 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x0209 A[Catch: Exception -> 0x0023, j -> 0x020d, TRY_ENTER, TRY_LEAVE, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:123:0x0215 A[Catch: all -> 0x0319, TRY_ENTER, TRY_LEAVE, TryCatch #13 {all -> 0x0319, blocks: (B:112:0x01eb, B:123:0x0215), top: B:236:0x01eb }] */
    /* JADX WARN: Code duplicated, block: B:126:0x021d A[Catch: all -> 0x0211, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x0211, blocks: (B:114:0x01f6, B:126:0x021d), top: B:233:0x01f4 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x0232  */
    /* JADX WARN: Code duplicated, block: B:134:0x023b A[Catch: all -> 0x022c, TryCatch #6 {all -> 0x022c, blocks: (B:127:0x0228, B:132:0x0233, B:134:0x023b, B:137:0x0245, B:139:0x024d, B:144:0x025f, B:146:0x026d, B:152:0x0282, B:159:0x0296, B:166:0x02aa, B:185:0x0303), top: B:226:0x0228 }] */
    /* JADX WARN: Code duplicated, block: B:139:0x024d A[Catch: all -> 0x022c, TRY_LEAVE, TryCatch #6 {all -> 0x022c, blocks: (B:127:0x0228, B:132:0x0233, B:134:0x023b, B:137:0x0245, B:139:0x024d, B:144:0x025f, B:146:0x026d, B:152:0x0282, B:159:0x0296, B:166:0x02aa, B:185:0x0303), top: B:226:0x0228 }] */
    /* JADX WARN: Code duplicated, block: B:142:0x025b  */
    /* JADX WARN: Code duplicated, block: B:161:0x029d A[Catch: Exception -> 0x0023, j -> 0x020d, TRY_ENTER, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:163:0x02a2 A[Catch: Exception -> 0x0023, j -> 0x020d, TRY_LEAVE, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:166:0x02aa A[Catch: all -> 0x022c, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x022c, blocks: (B:127:0x0228, B:132:0x0233, B:134:0x023b, B:137:0x0245, B:139:0x024d, B:144:0x025f, B:146:0x026d, B:152:0x0282, B:159:0x0296, B:166:0x02aa, B:185:0x0303), top: B:226:0x0228 }] */
    /* JADX WARN: Code duplicated, block: B:169:0x02c7 A[Catch: all -> 0x02e6, TryCatch #3 {all -> 0x02e6, blocks: (B:167:0x02b3, B:169:0x02c7, B:171:0x02cf, B:178:0x02eb), top: B:221:0x02b3 }] */
    /* JADX WARN: Code duplicated, block: B:181:0x02f8 A[Catch: Exception -> 0x0023, j -> 0x020d, TRY_ENTER, TRY_LEAVE, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:187:0x030c A[Catch: Exception -> 0x0023, j -> 0x020d, TRY_ENTER, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:189:0x0311 A[Catch: Exception -> 0x0023, j -> 0x020d, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:202:0x0333 A[Catch: Exception -> 0x0023, j -> 0x020d, TryCatch #15 {Exception -> 0x0023, blocks: (B:3:0x000c, B:5:0x0014, B:8:0x001d, B:13:0x002b, B:116:0x0209, B:148:0x0276, B:150:0x027b, B:154:0x0289, B:156:0x028e, B:161:0x029d, B:163:0x02a2, B:173:0x02dc, B:174:0x02df, B:181:0x02f8, B:202:0x0333, B:205:0x033a, B:206:0x0340, B:187:0x030c, B:189:0x0311), top: B:234:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:204:0x0338 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:231:0x01cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:243:0x0131 A[EDGE_INSN: B:243:0x0131->B:60:0x0131 BREAK  A[LOOP:0: B:48:0x010e->B:62:0x013c], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:248:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:249:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x0104 A[Catch: all -> 0x008e, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x008e, blocks: (B:46:0x0104, B:66:0x0146, B:67:0x0148, B:22:0x007d, B:24:0x008a, B:30:0x0098, B:34:0x00a4, B:36:0x00b8, B:37:0x00bb, B:41:0x00f7), top: B:229:0x0071 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x011a A[Catch: all -> 0x0129, TRY_LEAVE, TryCatch #5 {all -> 0x0129, blocks: (B:47:0x0108, B:48:0x010e, B:50:0x011a), top: B:224:0x0108 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x012d  */
    /* JADX WARN: Code duplicated, block: B:62:0x013c A[LOOP:0: B:48:0x010e->B:62:0x013c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:63:0x0141 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x0143 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:66:0x0146 A[Catch: all -> 0x008e, TRY_ENTER, TryCatch #8 {all -> 0x008e, blocks: (B:46:0x0104, B:66:0x0146, B:67:0x0148, B:22:0x007d, B:24:0x008a, B:30:0x0098, B:34:0x00a4, B:36:0x00b8, B:37:0x00bb, B:41:0x00f7), top: B:229:0x0071 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0185 A[Catch: all -> 0x0170, TryCatch #9 {all -> 0x0170, blocks: (B:70:0x015a, B:72:0x015e, B:75:0x0168, B:76:0x016f, B:79:0x0174, B:81:0x0185, B:83:0x018b, B:94:0x01ad, B:98:0x01bb, B:89:0x01a5), top: B:230:0x015a }] */
    /* JADX WARN: Code duplicated, block: B:83:0x018b A[Catch: all -> 0x0170, TRY_LEAVE, TryCatch #9 {all -> 0x0170, blocks: (B:70:0x015a, B:72:0x015e, B:75:0x0168, B:76:0x016f, B:79:0x0174, B:81:0x0185, B:83:0x018b, B:94:0x01ad, B:98:0x01bb, B:89:0x01a5), top: B:230:0x015a }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0198  */
    /* JADX WARN: Code duplicated, block: B:86:0x019d  */
    /* JADX WARN: Code duplicated, block: B:88:0x01a3  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v20, types: [Result, com.microsoft.graph.extensions.DriveItem, com.microsoft.graph.generated.BaseBaseItem] */
    public <Result, Body, DeserializeType> Result k(ax.p318w9.o oVar, Class<Result> cls, Body body, ax.p262s9.e<Result> eVar, ax.p318w9.q<Result, DeserializeType> qVar) throws Throwable {
        OutputStream outputStream;
        InputStream inputStream;
        boolean z;
        byte[] bytes;
        OneDriveFileHelper.i iVar;
        int i;
        String str;
        String str2;
        OutputStream outputStream2;
        InputStream inputStream2;
        OutputStream outputStreamB;
        OutputStream outputStream3;
        BufferedOutputStream bufferedOutputStream;
        byte[] bArr;
        int i2;
        ax.p045d2.c cVar;
        String str3;
        int i3;
        InputStream inputStream3;
        InputStream inputStream4;
        InputStream inputStreamC;
        BufferedInputStream bufferedInputStream;
        OutputStream outputStreamB2;
        OutputStream outputStream4;
        BufferedOutputStream bufferedOutputStream2;
        int i4;
        int iMin;
        ax.p262s9.e<Result> eVar2 = eVar;
        try {
            try {
                URL urlD = oVar.d();
                if (this.b != null && !C5731e.class.equals(cls)) {
                    this.b.a(oVar);
                }
                try {
                    this.d.a("Starting to send request, URL " + urlD.toString());
                    ax.p318w9.l lVarA = this.e.a(oVar);
                    try {
                        this.d.a("Request Method " + oVar.h().toString());
                        List<ax.p360z9.b> listA = oVar.a();
                        String str4 = "application/json";
                        String str5 = "Content-Type";
                        try {
                            if (body != 0) {
                                if (body instanceof OneDriveFileHelper.i) {
                                    iVar = (OneDriveFileHelper.i) body;
                                    if (!j(listA, "Content-Type").booleanValue()) {
                                        lVarA.e("Content-Type", "application/octet-stream");
                                    }
                                    lVarA.h((int) iVar.b);
                                    bytes = null;
                                } else if (body instanceof byte[]) {
                                    this.d.a("Sending byte[] as request body");
                                    bytes = (byte[]) body;
                                    if (!j(listA, "Content-Type").booleanValue()) {
                                        lVarA.e("Content-Type", "application/octet-stream");
                                    }
                                    lVarA.h(bytes.length);
                                } else {
                                    this.d.a("Sending " + body.getClass().getName() + " as request body");
                                    bytes = this.a.a(body).getBytes();
                                    if (!j(listA, "Content-Type").booleanValue()) {
                                        lVarA.e("Content-Type", "application/json");
                                    }
                                    lVarA.h(bytes.length);
                                }
                                i = 4096;
                                if (bytes == null) {
                                    if (iVar != null) {
                                        if (eVar2 == null) {
                                            eVar2 = iVar.d;
                                        }
                                        inputStream2 = iVar.a;
                                        outputStreamB = lVarA.b();
                                        try {
                                            bufferedOutputStream = new BufferedOutputStream(outputStreamB);
                                            bArr = new byte[4096];
                                            outputStream3 = outputStreamB;
                                            i2 = 0;
                                            while (true) {
                                                try {
                                                    cVar = iVar.c;
                                                    if (cVar != null || !cVar.isCancelled()) {
                                                        str3 = str4;
                                                        str2 = str5;
                                                        i3 = inputStream2.read(bArr, 0, Math.min(4096, ((int) iVar.b) - i2));
                                                        if (i3 > 0) {
                                                            bufferedOutputStream.write(bArr, 0, i3);
                                                            i2 += i3;
                                                            if (eVar2 != null) {
                                                                inputStream3 = inputStream2;
                                                                str = str3;
                                                                this.c.c(i2, (int) iVar.b, eVar2);
                                                            } else {
                                                                inputStream3 = inputStream2;
                                                                str = str3;
                                                            }
                                                        } else {
                                                            inputStream3 = inputStream2;
                                                            str = str3;
                                                            if (i3 == 0) {
                                                                try {
                                                                    Thread.sleep(100L);
                                                                } catch (InterruptedException unused) {
                                                                }
                                                            }
                                                        }
                                                        if (i3 >= 0 || i2 >= iVar.b) {
                                                            break;
                                                            break;
                                                        }
                                                        str5 = str2;
                                                        inputStream2 = inputStream3;
                                                        str4 = str;
                                                    } else {
                                                        throw new IOException("Cancelled");
                                                    }
                                                } catch (Throwable th) {
                                                    th = th;
                                                    outputStream = outputStream3;
                                                    inputStream = null;
                                                    z = false;
                                                    if (outputStream != null) {
                                                        outputStream.close();
                                                    }
                                                    if (!z) {
                                                        inputStream.close();
                                                        lVarA.close();
                                                    }
                                                    throw th;
                                                }
                                            }
                                            bufferedOutputStream.close();
                                            outputStream2 = outputStream3;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            outputStream3 = outputStreamB;
                                        }
                                    } else {
                                        str = "application/json";
                                        str2 = "Content-Type";
                                        outputStream2 = null;
                                    }
                                    if (qVar != null) {
                                        qVar.b(lVarA);
                                    }
                                    ax.p332x9.b bVar = this.d;
                                    Integer numValueOf = Integer.valueOf(lVarA.f());
                                    String strG = lVarA.g();
                                    Object[] objArr = new Object[2];
                                    objArr[0] = numValueOf;
                                    objArr[1] = strG;
                                    bVar.a(String.format("Response code %d, %s", objArr));
                                    if (qVar != null) {
                                        this.d.a("StatefulResponse is handling the HTTP response.");
                                        Result resultA = qVar.a(oVar, lVarA, f(), this.d);
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        return resultA;
                                    }
                                    if (lVarA.f() >= 400) {
                                        this.d.a("Handling error response");
                                        inputStreamC = lVarA.c();
                                        h(oVar, body, lVarA);
                                    } else {
                                        inputStreamC = null;
                                    }
                                    if (lVarA.f() != 204) {
                                        if (lVarA.f() != 202) {
                                            bufferedInputStream = new BufferedInputStream(lVarA.c());
                                            if (!lVarA.a().get(str2).contains(str)) {
                                            }
                                            this.d.a("Response binary");
                                            Result result = (Result) g(bufferedInputStream);
                                            if (outputStream2 != null) {
                                                outputStream2.close();
                                            }
                                            return result;
                                        }
                                        if (urlD.getPath().endsWith("microsoft.graph.copy")) {
                                        }
                                        this.d.a("Handling accepted response");
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        if (inputStreamC != null) {
                                            return null;
                                        }
                                        inputStreamC.close();
                                        lVarA.close();
                                        return null;
                                    }
                                    this.d.a("Handling response with no body");
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (inputStreamC != null) {
                                        return null;
                                    }
                                    inputStreamC.close();
                                    lVarA.close();
                                    return null;
                                }
                                outputStreamB2 = lVarA.b();
                                try {
                                    bufferedOutputStream2 = new BufferedOutputStream(outputStreamB2);
                                    i4 = 0;
                                    while (true) {
                                        iMin = Math.min(i, bytes.length - i4);
                                        bufferedOutputStream2.write(bytes, i4, iMin);
                                        i4 += iMin;
                                        if (eVar2 != null) {
                                            outputStream4 = outputStreamB2;
                                            try {
                                                this.c.c(i4, bytes.length, eVar2);
                                            } catch (Throwable th3) {
                                                th = th3;
                                                outputStream = outputStream4;
                                                inputStream = null;
                                                z = false;
                                                if (outputStream != null) {
                                                    outputStream.close();
                                                }
                                                if (!z) {
                                                    inputStream.close();
                                                    lVarA.close();
                                                }
                                                throw th;
                                            }
                                        } else {
                                            outputStream4 = outputStreamB2;
                                        }
                                        if (iMin <= 0) {
                                            break;
                                        }
                                        outputStreamB2 = outputStream4;
                                        i = 4096;
                                    }
                                    bufferedOutputStream2.close();
                                    str = "application/json";
                                    outputStream2 = outputStream4;
                                    str2 = "Content-Type";
                                    if (qVar != null) {
                                        try {
                                            qVar.b(lVarA);
                                        } catch (Throwable th4) {
                                            th = th4;
                                            outputStream = outputStream2;
                                            inputStream = null;
                                            z = false;
                                            if (outputStream != null) {
                                                outputStream.close();
                                            }
                                            if (!z) {
                                                inputStream.close();
                                                lVarA.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    try {
                                        ax.p332x9.b bVar2 = this.d;
                                        Integer numValueOf2 = Integer.valueOf(lVarA.f());
                                        String strG2 = lVarA.g();
                                        Object[] objArr2 = new Object[2];
                                        try {
                                            objArr2[0] = numValueOf2;
                                            try {
                                                objArr2[1] = strG2;
                                                bVar2.a(String.format("Response code %d, %s", objArr2));
                                                try {
                                                    if (qVar != null) {
                                                        this.d.a("StatefulResponse is handling the HTTP response.");
                                                        Result resultA2 = qVar.a(oVar, lVarA, f(), this.d);
                                                        if (outputStream2 != null) {
                                                            outputStream2.close();
                                                        }
                                                        return resultA2;
                                                    }
                                                    if (lVarA.f() >= 400) {
                                                        this.d.a("Handling error response");
                                                        inputStreamC = lVarA.c();
                                                        try {
                                                            h(oVar, body, lVarA);
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                            inputStream = inputStreamC;
                                                        }
                                                    } else {
                                                        inputStreamC = null;
                                                    }
                                                    if (lVarA.f() != 204 && lVarA.f() != 304) {
                                                        if (lVarA.f() != 202) {
                                                            if (urlD.getPath().endsWith("microsoft.graph.copy") || cls != DriveItem.class) {
                                                                this.d.a("Handling accepted response");
                                                                if (outputStream2 != null) {
                                                                    outputStream2.close();
                                                                }
                                                                if (inputStreamC != null) {
                                                                    return null;
                                                                }
                                                                inputStreamC.close();
                                                                lVarA.close();
                                                                return null;
                                                            }
                                                            String str6 = lVarA.a().get("Location");
                                                            if (str6 != null) {
                                                                ?? r2 = (Result) new DriveItem();
                                                                r2.n = str6;
                                                                if (outputStream2 != null) {
                                                                    outputStream2.close();
                                                                }
                                                                if (inputStreamC != null) {
                                                                    inputStreamC.close();
                                                                    lVarA.close();
                                                                }
                                                                return r2;
                                                            }
                                                            this.d.a("Handling accepted response");
                                                            if (outputStream2 != null) {
                                                                outputStream2.close();
                                                            }
                                                            if (inputStreamC == null) {
                                                                return null;
                                                            }
                                                            inputStreamC.close();
                                                            lVarA.close();
                                                            return null;
                                                        }
                                                        bufferedInputStream = new BufferedInputStream(lVarA.c());
                                                        try {
                                                            if (!lVarA.a().get(str2).contains(str) && !InputStream.class.equals(cls)) {
                                                                this.d.a("Response json");
                                                                Result result2 = (Result) i(bufferedInputStream, cls);
                                                                if (outputStream2 != null) {
                                                                    outputStream2.close();
                                                                }
                                                                bufferedInputStream.close();
                                                                lVarA.close();
                                                                return result2;
                                                            }
                                                            this.d.a("Response binary");
                                                            try {
                                                                Result result3 = (Result) g(bufferedInputStream);
                                                                if (outputStream2 != null) {
                                                                    outputStream2.close();
                                                                }
                                                                return result3;
                                                            } catch (Throwable th6) {
                                                                th = th6;
                                                                inputStream = bufferedInputStream;
                                                                outputStream = outputStream2;
                                                                z = true;
                                                            }
                                                        } catch (Throwable th7) {
                                                            th = th7;
                                                            inputStream = bufferedInputStream;
                                                            outputStream = outputStream2;
                                                            z = false;
                                                        }
                                                    }
                                                    this.d.a("Handling response with no body");
                                                    if (outputStream2 != null) {
                                                        outputStream2.close();
                                                    }
                                                    if (inputStreamC != null) {
                                                        return null;
                                                    }
                                                    inputStreamC.close();
                                                    lVarA.close();
                                                    return null;
                                                } catch (Throwable th8) {
                                                    th = th8;
                                                    outputStream = outputStream2;
                                                    inputStream = null;
                                                    z = false;
                                                }
                                            } catch (Throwable th9) {
                                                th = th9;
                                                inputStream4 = null;
                                                inputStream = inputStream4;
                                            }
                                        } catch (Throwable th10) {
                                            th = th10;
                                            inputStream4 = null;
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                        inputStream4 = null;
                                    }
                                } catch (Throwable th12) {
                                    th = th12;
                                    outputStream4 = outputStreamB2;
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (!z && inputStream != null) {
                                    inputStream.close();
                                    lVarA.close();
                                }
                                throw th;
                            }
                            bytes = null;
                            iVar = null;
                            i = 4096;
                            if (bytes == null) {
                                outputStreamB2 = lVarA.b();
                                bufferedOutputStream2 = new BufferedOutputStream(outputStreamB2);
                                i4 = 0;
                                while (true) {
                                    iMin = Math.min(i, bytes.length - i4);
                                    bufferedOutputStream2.write(bytes, i4, iMin);
                                    i4 += iMin;
                                    if (eVar2 != null) {
                                        outputStream4 = outputStreamB2;
                                        this.c.c(i4, bytes.length, eVar2);
                                    } else {
                                        outputStream4 = outputStreamB2;
                                    }
                                    if (iMin <= 0) {
                                        break;
                                        break;
                                    }
                                    outputStreamB2 = outputStream4;
                                    i = 4096;
                                }
                                bufferedOutputStream2.close();
                                str = "application/json";
                                outputStream2 = outputStream4;
                                str2 = "Content-Type";
                                if (qVar != null) {
                                    qVar.b(lVarA);
                                }
                                ax.p332x9.b bVar3 = this.d;
                                Integer numValueOf3 = Integer.valueOf(lVarA.f());
                                String strG3 = lVarA.g();
                                Object[] objArr3 = new Object[2];
                                objArr3[0] = numValueOf3;
                                objArr3[1] = strG3;
                                bVar3.a(String.format("Response code %d, %s", objArr3));
                                if (qVar != null) {
                                    this.d.a("StatefulResponse is handling the HTTP response.");
                                    Result resultA3 = qVar.a(oVar, lVarA, f(), this.d);
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    return resultA3;
                                }
                                if (lVarA.f() >= 400) {
                                    this.d.a("Handling error response");
                                    inputStreamC = lVarA.c();
                                    h(oVar, body, lVarA);
                                } else {
                                    inputStreamC = null;
                                }
                                if (lVarA.f() != 204) {
                                    if (lVarA.f() != 202) {
                                        bufferedInputStream = new BufferedInputStream(lVarA.c());
                                        if (!lVarA.a().get(str2).contains(str)) {
                                        }
                                        this.d.a("Response binary");
                                        Result result4 = (Result) g(bufferedInputStream);
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        return result4;
                                    }
                                    if (urlD.getPath().endsWith("microsoft.graph.copy")) {
                                    }
                                    this.d.a("Handling accepted response");
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (inputStreamC != null) {
                                        return null;
                                    }
                                    inputStreamC.close();
                                    lVarA.close();
                                    return null;
                                }
                                this.d.a("Handling response with no body");
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                if (inputStreamC != null) {
                                    return null;
                                }
                                inputStreamC.close();
                                lVarA.close();
                                return null;
                                outputStream = outputStream2;
                                z = false;
                            } else {
                                if (iVar != null) {
                                    if (eVar2 == null) {
                                        eVar2 = iVar.d;
                                    }
                                    inputStream2 = iVar.a;
                                    outputStreamB = lVarA.b();
                                    bufferedOutputStream = new BufferedOutputStream(outputStreamB);
                                    bArr = new byte[4096];
                                    outputStream3 = outputStreamB;
                                    i2 = 0;
                                    while (true) {
                                        cVar = iVar.c;
                                        if (cVar != null) {
                                        }
                                        str3 = str4;
                                        str2 = str5;
                                        i3 = inputStream2.read(bArr, 0, Math.min(4096, ((int) iVar.b) - i2));
                                        if (i3 > 0) {
                                            bufferedOutputStream.write(bArr, 0, i3);
                                            i2 += i3;
                                            if (eVar2 != null) {
                                                inputStream3 = inputStream2;
                                                str = str3;
                                                this.c.c(i2, (int) iVar.b, eVar2);
                                            } else {
                                                inputStream3 = inputStream2;
                                                str = str3;
                                            }
                                        } else {
                                            inputStream3 = inputStream2;
                                            str = str3;
                                            if (i3 == 0) {
                                                Thread.sleep(100L);
                                            }
                                        }
                                        if (i3 >= 0) {
                                            break;
                                        }
                                        str5 = str2;
                                        inputStream2 = inputStream3;
                                        str4 = str;
                                    }
                                    bufferedOutputStream.close();
                                    outputStream2 = outputStream3;
                                } else {
                                    str = "application/json";
                                    str2 = "Content-Type";
                                    outputStream2 = null;
                                }
                                if (qVar != null) {
                                    qVar.b(lVarA);
                                }
                                ax.p332x9.b bVar4 = this.d;
                                Integer numValueOf4 = Integer.valueOf(lVarA.f());
                                String strG4 = lVarA.g();
                                Object[] objArr4 = new Object[2];
                                objArr4[0] = numValueOf4;
                                objArr4[1] = strG4;
                                bVar4.a(String.format("Response code %d, %s", objArr4));
                                if (qVar != null) {
                                    this.d.a("StatefulResponse is handling the HTTP response.");
                                    Result resultA4 = qVar.a(oVar, lVarA, f(), this.d);
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    return resultA4;
                                }
                                if (lVarA.f() >= 400) {
                                    this.d.a("Handling error response");
                                    inputStreamC = lVarA.c();
                                    h(oVar, body, lVarA);
                                } else {
                                    inputStreamC = null;
                                }
                                if (lVarA.f() != 204) {
                                    if (lVarA.f() != 202) {
                                        bufferedInputStream = new BufferedInputStream(lVarA.c());
                                        if (!lVarA.a().get(str2).contains(str)) {
                                        }
                                        this.d.a("Response binary");
                                        Result result5 = (Result) g(bufferedInputStream);
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        return result5;
                                    }
                                    if (urlD.getPath().endsWith("microsoft.graph.copy")) {
                                    }
                                    this.d.a("Handling accepted response");
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (inputStreamC != null) {
                                        return null;
                                    }
                                    inputStreamC.close();
                                    lVarA.close();
                                    return null;
                                }
                                this.d.a("Handling response with no body");
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                if (inputStreamC != null) {
                                    return null;
                                }
                                inputStreamC.close();
                                lVarA.close();
                                return null;
                                outputStream = outputStream2;
                                z = false;
                            }
                        } catch (Throwable th13) {
                            th = th13;
                            outputStream = null;
                        }
                    } catch (Throwable th14) {
                        th = th14;
                        outputStream = null;
                        inputStream = null;
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (!z) {
                        inputStream.close();
                        lVarA.close();
                    }
                    throw th;
                } catch (ax.p318w9.j e) {
                    e = e;
                    boolean z2 = this.d.c() == ax.p332x9.c.Debug;
                    this.d.b("OneDrive Service exception " + e.c(z2), e);
                    throw e;
                }
            } catch (ax.p318w9.j e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            ax.p276t9.d dVar = new ax.p276t9.d("Error during http request", e3, ax.p276t9.e.GeneralException);
            this.d.b("Error during http request", dVar);
            throw dVar;
        }
    }

    public static String l(InputStream inputStream) {
        return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
    }

    @Override // ax.p318w9.n
    public <Result, Body> void a(ax.p318w9.o oVar, ax.p262s9.c<Result> cVar, Class<Result> cls, Body body) {
        this.c.b(new a(oVar, cls, body, cVar instanceof ax.p262s9.e ? (ax.p262s9.e) cVar : null, cVar));
    }

    @Override // ax.p318w9.n
    public <Result, Body> Result b(ax.p318w9.o oVar, Class<Result> cls, Body body) throws ax.p276t9.d {
        return (Result) c(oVar, cls, body, null);
    }

    @Override // ax.p318w9.n
    public <Result, Body, DeserializeType> Result c(ax.p318w9.o oVar, Class<Result> cls, Body body, ax.p318w9.q<Result, DeserializeType> qVar) throws ax.p276t9.d {
        return (Result) k(oVar, cls, body, null, qVar);
    }

    public ax.A9.e f() {
        return this.a;
    }
}
