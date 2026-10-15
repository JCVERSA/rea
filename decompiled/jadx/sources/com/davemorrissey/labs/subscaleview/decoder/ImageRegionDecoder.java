package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX INFO: loaded from: classes.dex */
public interface ImageRegionDecoder {
    Bitmap decodeRegion(Rect rect, int i, boolean z);

    Point init(Context context, SubsamplingScaleImageView.DecodeInputStreamFactory decodeInputStreamFactory) throws Exception;

    boolean isReady();

    void recycle();
}
