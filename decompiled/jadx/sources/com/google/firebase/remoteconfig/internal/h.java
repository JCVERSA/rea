package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class h {
    private static final Map<String, h> c = new HashMap();
    private final Context a;
    private final String b;

    private h(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public static synchronized h c(Context context, String str) {
        Map<String, h> map;
        try {
            map = c;
            if (!map.containsKey(str)) {
                map.put(str, new h(context, str));
            }
        } catch (Throwable th) {
            throw th;
        }
        return map.get(str);
    }

    public synchronized Void a() {
        this.a.deleteFile(this.b);
        return null;
    }

    String b() {
        return this.b;
    }

    public synchronized c d() throws IOException {
        FileInputStream fileInputStreamOpenFileInput;
        Throwable th;
        try {
            try {
                fileInputStreamOpenFileInput = this.a.openFileInput(this.b);
                try {
                    int iAvailable = fileInputStreamOpenFileInput.available();
                    byte[] bArr = new byte[iAvailable];
                    fileInputStreamOpenFileInput.read(bArr, 0, iAvailable);
                    c cVarB = c.b(new JSONObject(new String(bArr, "UTF-8")));
                    fileInputStreamOpenFileInput.close();
                    return cVarB;
                } catch (FileNotFoundException | JSONException unused) {
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        } catch (FileNotFoundException | JSONException unused2) {
            fileInputStreamOpenFileInput = null;
        } catch (Throwable th4) {
            fileInputStreamOpenFileInput = null;
            th = th4;
        }
    }

    public synchronized Void e(c cVar) throws IOException {
        FileOutputStream fileOutputStreamOpenFileOutput = this.a.openFileOutput(this.b, 0);
        try {
            fileOutputStreamOpenFileOutput.write(cVar.toString().getBytes("UTF-8"));
            fileOutputStreamOpenFileOutput.close();
        } catch (Throwable th) {
            fileOutputStreamOpenFileOutput.close();
            throw th;
        }
        return null;
    }
}
