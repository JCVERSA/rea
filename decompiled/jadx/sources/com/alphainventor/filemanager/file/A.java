package com.alphainventor.filemanager.file;

import ax.p290u9.C5731e;
import ax.p290u9.InterfaceC5732e0;
import com.microsoft.graph.extensions.UploadSession;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class A<UploadType> {
    private final InterfaceC5732e0 a;
    private final ax.J1.A b;
    private final String c;
    private final long d;
    private final ax.p262s9.a<UploadType> e;
    private int f;

    public static class a {
        ax.J1.A a;
        InputStream b;

        a(ax.J1.A a, InputStream inputStream) {
            this.a = a;
            this.b = inputStream;
        }
    }

    public A(UploadSession uploadSession, InterfaceC5732e0 interfaceC5732e0, ax.J1.A a2, long j, Class<UploadType> cls) {
        if (uploadSession == null) {
            throw new InvalidParameterException("Upload session is null.");
        }
        if (interfaceC5732e0 == null) {
            throw new InvalidParameterException("OneDrive client is null.");
        }
        if (a2 == null) {
            throw new InvalidParameterException("Input stream is null.");
        }
        if (j <= 0) {
            throw new InvalidParameterException("Stream size should larger than 0.");
        }
        this.a = interfaceC5732e0;
        this.f = 0;
        this.b = a2;
        this.d = j;
        this.c = uploadSession.e;
        this.e = new ax.p262s9.a<>(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(List<ax.p360z9.c> list, ax.p045d2.c cVar, ax.p262s9.e<UploadType> eVar, int... iArr) throws Throwable {
        a aVar;
        int i = iArr.length > 0 ? iArr[0] : 5242880;
        int i2 = iArr.length > 1 ? iArr[1] : 3;
        if (i % 327680 != 0) {
            throw new IllegalArgumentException("Chunk size must be a multiple of 320 KiB");
        }
        if (i > 62914560) {
            throw new IllegalArgumentException("Please set chunk size smaller than 60 MiB");
        }
        InputStream inputStreamB = this.b.b();
        if (inputStreamB == null) {
            throw new IOException("no input stream");
        }
        a aVar2 = new a(this.b, inputStreamB);
        while (true) {
            try {
                int i3 = this.f;
                long j = i3;
                long j2 = this.d;
                if (j >= j2) {
                    aVar = aVar2;
                    break;
                }
                int iMin = (int) Math.min(i, j2 - ((long) i3));
                int i4 = i2;
                int i5 = i;
                int i6 = i2;
                aVar = aVar2;
                try {
                    C5731e c5731eA = new B(this.c, this.a, list, aVar2, iMin, cVar, i4, this.f, this.d, eVar).a(this.e);
                    this.f += iMin;
                    if (c5731eA.e()) {
                        long j3 = this.d;
                        eVar.a(j3, j3);
                        eVar.b(c5731eA.c());
                        break;
                    }
                    if (c5731eA.a()) {
                        eVar.a(this.f, this.d);
                    } else if (c5731eA.d()) {
                        eVar.c(c5731eA.b());
                        break;
                    }
                    aVar2 = aVar;
                    i = i5;
                    i2 = i6;
                } catch (Throwable th) {
                    th = th;
                    aVar.b.close();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                aVar = aVar2;
            }
        }
        aVar.b.close();
    }
}
