package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.Keep;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public class SkiaImageRegionDecoder implements ImageRegionDecoder {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final String FILE_PREFIX = "file://";
    private static final String RESOURCE_PREFIX = "android.resource://";
    private final Bitmap.Config bitmapConfig;
    private BitmapRegionDecoder decoder;
    private final ReadWriteLock decoderLock;
    private SubsamplingScaleImageView.DecodeInputStreamFactory inputStreamFactory;

    @Keep
    public SkiaImageRegionDecoder() {
        this(null);
    }

    public SkiaImageRegionDecoder(Bitmap.Config config) {
        this.decoderLock = new ReentrantReadWriteLock(true);
        Bitmap.Config preferredBitmapConfig = SubsamplingScaleImageView.getPreferredBitmapConfig();
        if (config != null) {
            this.bitmapConfig = config;
        } else if (preferredBitmapConfig != null) {
            this.bitmapConfig = preferredBitmapConfig;
        } else {
            this.bitmapConfig = Bitmap.Config.RGB_565;
        }
    }

    private Lock getDecodeLock() {
        return this.decoderLock.readLock();
    }

    @Override // com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder
    public Bitmap decodeRegion(Rect rect, int i, boolean z) {
        Bitmap bitmapDecodeRegion;
        SubsamplingScaleImageView.DecodeInputStreamFactory decodeInputStreamFactory;
        InputStream inputStreamCreateInputStream;
        Throwable th;
        getDecodeLock().lock();
        try {
            BitmapRegionDecoder bitmapRegionDecoder = this.decoder;
            if (bitmapRegionDecoder == null || bitmapRegionDecoder.isRecycled()) {
                throw new IllegalStateException("Cannot decode region after decoder has been recycled");
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = i;
            options.inPreferredConfig = this.bitmapConfig;
            if (!DecoderUtils.hasWebViewBitmapColorProblem()) {
                bitmapDecodeRegion = this.decoder.decodeRegion(rect, options);
            } else if (!z || (decodeInputStreamFactory = this.inputStreamFactory) == null) {
                bitmapDecodeRegion = this.decoder.decodeRegion(rect, options);
                if (bitmapDecodeRegion != null) {
                    Bitmap bitmapCopyBitmap = DecoderUtils.copyBitmap(bitmapDecodeRegion);
                    bitmapDecodeRegion.recycle();
                    bitmapDecodeRegion = bitmapCopyBitmap;
                }
            } else {
                bitmapDecodeRegion = null;
                try {
                    inputStreamCreateInputStream = decodeInputStreamFactory.createInputStream();
                    try {
                        bitmapDecodeRegion = BitmapFactory.decodeStream(inputStreamCreateInputStream, null, options);
                        if (inputStreamCreateInputStream != null) {
                            try {
                                inputStreamCreateInputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                    } catch (IOException unused2) {
                        if (inputStreamCreateInputStream != null) {
                            inputStreamCreateInputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStreamCreateInputStream != null) {
                            try {
                                inputStreamCreateInputStream.close();
                            } catch (Exception unused3) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused4) {
                    inputStreamCreateInputStream = null;
                } catch (Throwable th3) {
                    inputStreamCreateInputStream = null;
                    th = th3;
                }
            }
            if (bitmapDecodeRegion == null) {
                throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
            }
            getDecodeLock().unlock();
            return bitmapDecodeRegion;
        } catch (Throwable th4) {
            getDecodeLock().unlock();
            throw th4;
        }
    }

    @Override // com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder
    public Point init(Context context, SubsamplingScaleImageView.DecodeInputStreamFactory decodeInputStreamFactory) throws Exception {
        InputStream inputStreamCreateInputStream;
        this.inputStreamFactory = decodeInputStreamFactory;
        try {
            inputStreamCreateInputStream = decodeInputStreamFactory.createInputStream();
            try {
                this.decoder = BitmapRegionDecoder.newInstance(inputStreamCreateInputStream, false);
                if (inputStreamCreateInputStream != null) {
                    try {
                        inputStreamCreateInputStream.close();
                    } catch (Exception unused) {
                    }
                }
                return new Point(this.decoder.getWidth(), this.decoder.getHeight());
            } catch (Throwable th) {
                th = th;
                if (inputStreamCreateInputStream != null) {
                    try {
                        inputStreamCreateInputStream.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamCreateInputStream = null;
        }
    }

    @Override // com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder
    public synchronized boolean isReady() {
        BitmapRegionDecoder bitmapRegionDecoder;
        bitmapRegionDecoder = this.decoder;
        return (bitmapRegionDecoder == null || bitmapRegionDecoder.isRecycled()) ? false : true;
    }

    @Override // com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder
    public synchronized void recycle() {
        this.decoderLock.writeLock().lock();
        try {
            this.decoder.recycle();
            this.decoder = null;
            this.decoderLock.writeLock().unlock();
        } catch (Throwable th) {
            this.decoderLock.writeLock().unlock();
            throw th;
        }
    }
}
