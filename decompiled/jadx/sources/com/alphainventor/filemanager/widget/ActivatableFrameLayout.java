package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActivatableFrameLayout extends FrameLayout {
    private a q;

    public interface a {
        void a(boolean z);
    }

    public ActivatableFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void setActivated(boolean z) {
        super.setActivated(z);
        a aVar = this.q;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public void setOnActivatedListener(a aVar) {
        this.q = aVar;
    }
}
