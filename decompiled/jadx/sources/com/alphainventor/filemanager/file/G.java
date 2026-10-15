package com.alphainventor.filemanager.file;

import android.content.Context;
import ax.p333ua.C2388e;
import ax.p406z3.InterfaceC2462c;
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
public class G implements ax.p365wa.n {
    private final ax.Aa.e a;
    private final ax.p285ra.a b;
    private final ax.p301sa.d c;
    private final ax.p381xa.b d;
    private ax.p365wa.m e = new ax.p365wa.e();

    class a implements Runnable {
        final /* synthetic */ Class X;
        final /* synthetic */ Object Y;
        final /* synthetic */ ax.p301sa.e Z;
        final /* synthetic */ ax.p301sa.c k0;
        final /* synthetic */ ax.p365wa.o q;

        a(ax.p365wa.o oVar, Class cls, Object obj, ax.p301sa.e eVar, ax.p301sa.c cVar) {
            this.q = oVar;
            this.X = cls;
            this.Y = obj;
            this.Z = eVar;
            this.k0 = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                G.this.c.c(G.this.k(this.q, this.X, this.Y, this.Z, null), this.k0);
            } catch (ax.p317ta.d e) {
                G.this.c.b(e, this.k0);
            }
        }
    }

    public G(Context context, ax.Aa.e eVar, ax.p285ra.a aVar, ax.p301sa.d dVar, ax.p381xa.b bVar) {
        this.a = eVar;
        this.b = aVar;
        this.c = dVar;
        this.d = bVar;
    }

    private InputStream g(InputStream inputStream) {
        return inputStream;
    }

    private <Body> void h(ax.p365wa.o oVar, Body body, ax.p365wa.l lVar) throws IOException {
        throw ax.p365wa.j.b(oVar, body, this.a, lVar);
    }

    private <Result> Result i(InputStream inputStream, Class<Result> cls) {
        if (cls == null) {
            return null;
        }
        return (Result) f().b(l(inputStream), cls);
    }

    private Boolean j(List<ax.p413za.b> list, String str) {
        Iterator<ax.p413za.b> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().a() == str) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:197:0x0335 A[Catch: Exception -> 0x0023, j -> 0x021d, TryCatch #11 {j -> 0x021d, blocks: (B:118:0x0219, B:149:0x0286, B:151:0x028b, B:155:0x0299, B:157:0x029e, B:161:0x02ac, B:163:0x02b1, B:172:0x02e6, B:173:0x02e9, B:180:0x0302, B:197:0x0335, B:200:0x033c, B:201:0x0342, B:186:0x0315, B:188:0x031a), top: B:229:0x002d }] */
    /* JADX WARN: Code duplicated, block: B:199:0x033a A[ADDED_TO_REGION] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v14 */
    /* JADX WARN: Type inference failed for: r18v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r18v28 */
    /* JADX WARN: Type inference failed for: r18v29 */
    /* JADX WARN: Type inference failed for: r26v0, types: [Body, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v8, types: [Result, com.microsoft.graph.extensions.DriveItem, com.microsoft.graph.generated.BaseBaseItem] */
    public <Result, Body, DeserializeType> Result k(ax.p365wa.o oVar, Class<Result> cls, Body body, ax.p301sa.e<Result> eVar, ax.p365wa.q<Result, DeserializeType> qVar) throws Throwable {
        Object obj;
        OutputStream outputStream;
        boolean z;
        ?? r18;
        OutputStream outputStream2;
        OutputStream outputStream3;
        OutputStream outputStream4;
        OneDriveFileHelper.i iVar;
        char c;
        byte[] bytes;
        OutputStream outputStream5;
        OutputStream outputStreamB;
        OutputStream outputStream6;
        OutputStream outputStream7;
        OutputStream outputStream8;
        String str;
        Object obj2;
        OutputStream outputStream9;
        InputStream inputStreamC;
        OutputStream outputStream10;
        InputStream inputStream;
        BufferedOutputStream bufferedOutputStream;
        ax.p301sa.e<Result> eVar2 = eVar;
        try {
            try {
                URL urlD = oVar.d();
                if (this.b != null && !C2388e.class.equals(cls)) {
                    this.b.a(oVar);
                }
                try {
                    this.d.a("Starting to send request, URL " + urlD.toString());
                    ax.p365wa.l lVarA = this.e.a(oVar);
                    try {
                        this.d.a("Request Method " + oVar.h().toString());
                        List<ax.p413za.b> listA = oVar.a();
                        String str2 = "application/json";
                        if (body == 0) {
                            bytes = null;
                            iVar = null;
                            c = 1;
                            outputStreamB = listA;
                        } else {
                            try {
                                if (body instanceof OneDriveFileHelper.i) {
                                    try {
                                        iVar = (OneDriveFileHelper.i) body;
                                        boolean zBooleanValue = j(listA, "Content-Type").booleanValue();
                                        if (!zBooleanValue) {
                                            try {
                                                lVarA.e("Content-Type", "application/octet-stream");
                                            } catch (Throwable th) {
                                                th = th;
                                                outputStream3 = null;
                                                z = false;
                                                outputStream4 = null;
                                                outputStream5 = outputStream3;
                                                outputStream2 = outputStream5;
                                                r18 = outputStream4;
                                                if (outputStream2 != null) {
                                                    outputStream2.close();
                                                }
                                                if (!z) {
                                                    r18.close();
                                                    lVarA.close();
                                                }
                                                throw th;
                                            }
                                        }
                                        c = 1;
                                        lVarA.h((int) iVar.b);
                                        bytes = null;
                                        outputStreamB = zBooleanValue;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        outputStream3 = null;
                                        z = false;
                                        outputStream4 = null;
                                        outputStream5 = outputStream3;
                                        outputStream2 = outputStream5;
                                        r18 = outputStream4;
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        if (!z) {
                                            r18.close();
                                            lVarA.close();
                                        }
                                        throw th;
                                    }
                                } else {
                                    c = 1;
                                    try {
                                        if (body instanceof byte[]) {
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
                                        iVar = null;
                                        outputStreamB = listA;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        obj = null;
                                        outputStream = null;
                                        z = false;
                                        outputStream2 = outputStream;
                                        r18 = obj;
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        if (!z && r18 != 0) {
                                            r18.close();
                                            lVarA.close();
                                        }
                                        throw th;
                                    }
                                }
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                        int i = 4096;
                        try {
                            if (bytes != null) {
                                try {
                                    outputStreamB = lVarA.b();
                                    try {
                                        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(outputStreamB);
                                        int i2 = 0;
                                        Object obj3 = (Result) null;
                                        while (true) {
                                            int iMin = Math.min(i, bytes.length - i2);
                                            bufferedOutputStream2.write(bytes, i2, iMin);
                                            i2 += iMin;
                                            if (eVar2 != null) {
                                                this.c.d(i2, bytes.length, eVar2);
                                            }
                                            if (iMin <= 0) {
                                                break;
                                            }
                                            i = 4096;
                                        }
                                        bufferedOutputStream2.close();
                                        str = "application/json";
                                        outputStream9 = outputStreamB;
                                        obj2 = obj3;
                                    } catch (Throwable th5) {
                                        th = th5;
                                        outputStream7 = null;
                                        outputStream6 = outputStreamB;
                                        z = false;
                                        outputStream5 = outputStream6;
                                        outputStream4 = outputStream7;
                                        outputStream2 = outputStream5;
                                        r18 = outputStream4;
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        if (!z) {
                                            r18.close();
                                            lVarA.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    outputStream8 = null;
                                    outputStream6 = outputStream8;
                                    outputStream7 = outputStream8;
                                    z = false;
                                    outputStream5 = outputStream6;
                                    outputStream4 = outputStream7;
                                    outputStream2 = outputStream5;
                                    r18 = outputStream4;
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (!z) {
                                        r18.close();
                                        lVarA.close();
                                    }
                                    throw th;
                                }
                            } else {
                                outputStream8 = (Result) null;
                                if (iVar != null) {
                                    if (eVar2 == null) {
                                        try {
                                            eVar2 = iVar.d;
                                        } catch (Throwable th7) {
                                            th = th7;
                                            outputStream6 = outputStream8;
                                            outputStream7 = outputStream8;
                                            z = false;
                                            outputStream5 = outputStream6;
                                            outputStream4 = outputStream7;
                                            outputStream2 = outputStream5;
                                            r18 = outputStream4;
                                            if (outputStream2 != null) {
                                                outputStream2.close();
                                            }
                                            if (!z) {
                                                r18.close();
                                                lVarA.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    InputStream inputStream2 = iVar.a;
                                    OutputStream outputStreamB2 = lVarA.b();
                                    try {
                                        BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(outputStreamB2);
                                        byte[] bArr = new byte[4096];
                                        int i3 = 0;
                                        while (true) {
                                            InterfaceC2462c interfaceC2462c = iVar.c;
                                            if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                                                throw new IOException("Cancelled");
                                            }
                                            outputStream10 = outputStreamB2;
                                            str = str2;
                                            try {
                                                int i4 = inputStream2.read(bArr, 0, Math.min(4096, ((int) iVar.b) - i3));
                                                if (i4 > 0) {
                                                    bufferedOutputStream3.write(bArr, 0, i4);
                                                    i3 += i4;
                                                    if (eVar2 != null) {
                                                        inputStream = inputStream2;
                                                        bufferedOutputStream = bufferedOutputStream3;
                                                        this.c.d(i3, (int) iVar.b, eVar2);
                                                    } else {
                                                        inputStream = inputStream2;
                                                        bufferedOutputStream = bufferedOutputStream3;
                                                    }
                                                } else {
                                                    inputStream = inputStream2;
                                                    bufferedOutputStream = bufferedOutputStream3;
                                                    if (i4 == 0) {
                                                        try {
                                                            Thread.sleep(100L);
                                                        } catch (InterruptedException unused) {
                                                        }
                                                    }
                                                }
                                                if (i4 < 0 || i3 >= iVar.b) {
                                                    break;
                                                    break;
                                                }
                                                outputStreamB2 = outputStream10;
                                                str2 = str;
                                                inputStream2 = inputStream;
                                                bufferedOutputStream3 = bufferedOutputStream;
                                            } catch (Throwable th8) {
                                                th = th8;
                                                outputStream6 = outputStream10;
                                                outputStream7 = outputStream8;
                                                z = false;
                                                outputStream5 = outputStream6;
                                                outputStream4 = outputStream7;
                                                outputStream2 = outputStream5;
                                                r18 = outputStream4;
                                                if (outputStream2 != null) {
                                                    outputStream2.close();
                                                }
                                                if (!z) {
                                                    r18.close();
                                                    lVarA.close();
                                                }
                                                throw th;
                                            }
                                        }
                                        bufferedOutputStream.close();
                                        outputStream9 = outputStream10;
                                        obj2 = outputStream8;
                                    } catch (Throwable th9) {
                                        th = th9;
                                        outputStream10 = outputStreamB2;
                                    }
                                } else {
                                    str = "application/json";
                                    outputStream9 = null;
                                }
                            }
                            if (qVar != null) {
                                obj2 = outputStream8;
                                qVar.b(lVarA);
                            }
                            try {
                                obj2 = outputStream8;
                                ax.p381xa.b bVar = this.d;
                                Integer numValueOf = Integer.valueOf(lVarA.f());
                                String strG = lVarA.g();
                                Object[] objArr = new Object[2];
                                try {
                                    objArr[0] = numValueOf;
                                    objArr[c] = strG;
                                    bVar.a(String.format("Response code %d, %s", objArr));
                                    if (qVar != null) {
                                        this.d.a("StatefulResponse is handling the HTTP response.");
                                        Result resultA = qVar.a(oVar, lVarA, f(), this.d);
                                        if (outputStream9 != null) {
                                            outputStream9.close();
                                        }
                                        return resultA;
                                    }
                                    if (lVarA.f() >= 400) {
                                        this.d.a("Handling error response");
                                        inputStreamC = lVarA.c();
                                        try {
                                            h(oVar, body, lVarA);
                                        } catch (Throwable th10) {
                                            th = th10;
                                            obj2 = (Result) inputStreamC;
                                            outputStream = outputStream9;
                                            obj = obj2;
                                            z = false;
                                            outputStream2 = outputStream;
                                            r18 = obj;
                                            if (outputStream2 != null) {
                                                outputStream2.close();
                                            }
                                            if (!z) {
                                                r18.close();
                                                lVarA.close();
                                            }
                                            throw th;
                                        }
                                    } else {
                                        inputStreamC = obj2;
                                    }
                                    if (lVarA.f() != 204 && lVarA.f() != 304) {
                                        if (lVarA.f() == 202) {
                                            if (!urlD.getPath().endsWith("microsoft.graph.copy") || cls != DriveItem.class) {
                                                this.d.a("Handling accepted response");
                                                if (outputStream9 != null) {
                                                    outputStream9.close();
                                                }
                                                if (inputStreamC != null) {
                                                    inputStreamC.close();
                                                    lVarA.close();
                                                }
                                                return (Result) obj2;
                                            }
                                            String str3 = lVarA.a().get("Location");
                                            if (str3 == null) {
                                                this.d.a("Handling accepted response");
                                                if (outputStream9 != null) {
                                                    outputStream9.close();
                                                }
                                                if (inputStreamC != null) {
                                                    inputStreamC.close();
                                                    lVarA.close();
                                                }
                                                return (Result) obj2;
                                            }
                                            ?? r2 = (Result) new DriveItem();
                                            r2.n = str3;
                                            if (outputStream9 != null) {
                                                outputStream9.close();
                                            }
                                            if (inputStreamC != null) {
                                                inputStreamC.close();
                                                lVarA.close();
                                            }
                                            return r2;
                                        }
                                        BufferedInputStream bufferedInputStream = new BufferedInputStream(lVarA.c());
                                        try {
                                            if (lVarA.a().get("Content-Type").contains(str) && !InputStream.class.equals(cls)) {
                                                this.d.a("Response json");
                                                Result result = (Result) i(bufferedInputStream, cls);
                                                if (outputStream9 != null) {
                                                    outputStream9.close();
                                                }
                                                bufferedInputStream.close();
                                                lVarA.close();
                                                return result;
                                            }
                                            this.d.a("Response binary");
                                            try {
                                                Result result2 = (Result) g(bufferedInputStream);
                                                if (outputStream9 != null) {
                                                    outputStream9.close();
                                                }
                                                return result2;
                                            } catch (Throwable th11) {
                                                th = th11;
                                                r18 = bufferedInputStream;
                                                outputStream2 = outputStream9;
                                                z = true;
                                                if (outputStream2 != null) {
                                                    outputStream2.close();
                                                }
                                                if (!z) {
                                                    r18.close();
                                                    lVarA.close();
                                                }
                                                throw th;
                                            }
                                        } catch (Throwable th12) {
                                            th = th12;
                                            obj2 = (Result) bufferedInputStream;
                                            outputStream = outputStream9;
                                            obj = obj2;
                                            z = false;
                                            outputStream2 = outputStream;
                                            r18 = obj;
                                            if (outputStream2 != null) {
                                                outputStream2.close();
                                            }
                                            if (!z) {
                                                r18.close();
                                                lVarA.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    this.d.a("Handling response with no body");
                                    if (outputStream9 != null) {
                                        outputStream9.close();
                                    }
                                    if (inputStreamC != null) {
                                        inputStreamC.close();
                                        lVarA.close();
                                    }
                                    return (Result) obj2;
                                } catch (Throwable th13) {
                                    th = th13;
                                }
                            } catch (Throwable th14) {
                                th = th14;
                            }
                        } catch (Throwable th15) {
                            th = th15;
                        }
                    } catch (Throwable th16) {
                        th = th16;
                    }
                } catch (ax.p365wa.j e) {
                    e = e;
                    boolean z2 = this.d.c() == ax.p381xa.c.Debug;
                    this.d.b("OneDrive Service exception " + e.c(z2), e);
                    throw e;
                }
            } catch (ax.p365wa.j e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            ax.p317ta.d dVar = new ax.p317ta.d("Error during http request", e3, ax.p317ta.e.GeneralException);
            this.d.b("Error during http request", dVar);
            throw dVar;
        }
    }

    public static String l(InputStream inputStream) {
        return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
    }

    @Override // ax.p365wa.n
    public <Result, Body, DeserializeType> Result a(ax.p365wa.o oVar, Class<Result> cls, Body body, ax.p365wa.q<Result, DeserializeType> qVar) throws ax.p317ta.d {
        return (Result) k(oVar, cls, body, null, qVar);
    }

    @Override // ax.p365wa.n
    public <Result, Body> void b(ax.p365wa.o oVar, ax.p301sa.c<Result> cVar, Class<Result> cls, Body body) {
        this.c.a(new a(oVar, cls, body, cVar instanceof ax.p301sa.e ? (ax.p301sa.e) cVar : null, cVar));
    }

    @Override // ax.p365wa.n
    public <Result, Body> Result c(ax.p365wa.o oVar, Class<Result> cls, Body body) throws ax.p317ta.d {
        return (Result) a(oVar, cls, body, null);
    }

    public ax.Aa.e f() {
        return this.a;
    }
}
