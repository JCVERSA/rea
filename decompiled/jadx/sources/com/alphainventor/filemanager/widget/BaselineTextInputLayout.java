package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public class BaselineTextInputLayout extends TextInputLayout {
    public BaselineTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.material.textfield.TextInputLayout, android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        return getPaddingTop() + getEditText().getBaseline();
    }
}
