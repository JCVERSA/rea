package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Keep;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class SkiaImageDecoder implements ImageDecoder {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final String FILE_PREFIX = "file://";
    private static final String RESOURCE_PREFIX = "android.resource://";
    private final Bitmap.Config bitmapConfig;

    @Keep
    public SkiaImageDecoder() {
        this(null);
    }

    public SkiaImageDecoder(Bitmap.Config config) {
        Bitmap.Config preferredBitmapConfig = SubsamplingScaleImageView.getPreferredBitmapConfig();
        if (config != null) {
            this.bitmapConfig = config;
        } else if (preferredBitmapConfig != null) {
            this.bitmapConfig = preferredBitmapConfig;
        } else {
            this.bitmapConfig = Bitmap.Config.RGB_565;
        }
    }

    @Override // com.davemorrissey.labs.subscaleview.decoder.ImageDecoder
    public Bitmap decode(Context context, SubsamplingScaleImageView.DecodeInputStreamFactory decodeInputStreamFactory) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = this.bitmapConfig;
        InputStream inputStream = null;
        try {
            InputStream inputStreamCreateInputStream = decodeInputStreamFactory.createInputStream();
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamCreateInputStream, null, options);
                if (inputStreamCreateInputStream != null) {
                    try {
                        inputStreamCreateInputStream.close();
                    } catch (Exception unused) {
                    }
                }
                if (bitmapDecodeStream != null) {
                    return bitmapDecodeStream;
                }
                throw new RuntimeException("Skia image region decoder returned null bitmap - image format may not be supported");
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamCreateInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
