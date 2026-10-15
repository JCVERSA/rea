package com.davemorrissey.labs.subscaleview.decoder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class DecoderUtils {
    public static Bitmap copyBitmap(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        bitmapCreateBitmap.setDensity(bitmap.getDensity());
        new Canvas(bitmapCreateBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static boolean hasWebViewBitmapColorProblem() {
        return Build.VERSION.SDK_INT == 28;
    }
}
