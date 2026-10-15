package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX INFO: loaded from: classes.dex */
public interface ImageDecoder {
    Bitmap decode(Context context, SubsamplingScaleImageView.DecodeInputStreamFactory decodeInputStreamFactory) throws Exception;
}
