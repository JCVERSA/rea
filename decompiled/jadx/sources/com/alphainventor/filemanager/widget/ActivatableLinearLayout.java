package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActivatableLinearLayout extends LinearLayout {

    public interface a {
    }

    public ActivatableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void setActivated(boolean z) {
        super.setActivated(z);
    }

    public void setOnActivatedListener(a aVar) {
    }
}
