package com.alphainventor.filemanager.file;

import ax.p070e3.C1966j;
import ax.p333ua.C2388e;
import ax.p333ua.InterfaceC2389e0;
import ax.p406z3.InterfaceC2462c;
import com.microsoft.graph.extensions.UploadSession;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class E<UploadType> {
    private final InterfaceC2389e0 a;
    private final ax.p086f3.G b;
    private final String c;
    private final long d;
    private final ax.p301sa.a<UploadType> e;
    private int f;

    public static class a {
        ax.p086f3.G a;
        InputStream b;

        a(ax.p086f3.G g, InputStream inputStream) {
            this.a = g;
            this.b = inputStream;
        }
    }

    public E(UploadSession uploadSession, InterfaceC2389e0 interfaceC2389e0, ax.p086f3.G g, long j, Class<UploadType> cls) {
        if (uploadSession == null) {
            throw new InvalidParameterException("Upload session is null.");
        }
        if (interfaceC2389e0 == null) {
            throw new InvalidParameterException("OneDrive client is null.");
        }
        if (g == null) {
            throw new InvalidParameterException("Input stream is null.");
        }
        if (j <= 0) {
            throw new InvalidParameterException("Stream size should larger than 0.");
        }
        this.a = interfaceC2389e0;
        this.f = 0;
        this.b = g;
        this.d = j;
        this.c = uploadSession.e;
        this.e = new ax.p301sa.a<>(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(List<ax.p413za.c> list, InterfaceC2462c interfaceC2462c, ax.p301sa.e<UploadType> eVar, int... iArr) throws C1966j, IOException {
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
        a aVar = new a(this.b, inputStreamB);
        while (true) {
            try {
                int i3 = this.f;
                long j = i3;
                long j2 = this.d;
                if (j >= j2) {
                    break;
                }
                int iMin = (int) Math.min(i, j2 - ((long) i3));
                C2388e c2388eA = new F(this.c, this.a, list, aVar, iMin, interfaceC2462c, i2, this.f, this.d, eVar).a(this.e);
                this.f += iMin;
                if (c2388eA.e()) {
                    long j3 = this.d;
                    eVar.a(j3, j3);
                    eVar.c(c2388eA.c());
                    break;
                } else if (c2388eA.a()) {
                    eVar.a(this.f, this.d);
                } else if (c2388eA.d()) {
                    eVar.b(c2388eA.b());
                    break;
                }
            } catch (Throwable th) {
                aVar.b.close();
                throw th;
            }
        }
        aVar.b.close();
    }
}
