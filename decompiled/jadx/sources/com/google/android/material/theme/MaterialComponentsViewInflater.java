package com.google.android.material.theme;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.C0594d;
import androidx.appcompat.widget.C0596f;
import androidx.appcompat.widget.C0597g;
import androidx.appcompat.widget.s;
import androidx.appcompat.widget.x;
import ax.p042c7.d;
import ax.p210n.z;
import ax.p266q7.a;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.v;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialComponentsViewInflater extends z {
    @Override // ax.p210n.z
    protected C0594d c(Context context, AttributeSet attributeSet) {
        return new v(context, attributeSet);
    }

    @Override // ax.p210n.z
    protected C0596f d(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    @Override // ax.p210n.z
    protected C0597g e(Context context, AttributeSet attributeSet) {
        return new d(context, attributeSet);
    }

    @Override // ax.p210n.z
    protected s k(Context context, AttributeSet attributeSet) {
        return new a(context, attributeSet);
    }

    @Override // ax.p210n.z
    protected x o(Context context, AttributeSet attributeSet) {
        return new ax.B7.a(context, attributeSet);
    }
}
