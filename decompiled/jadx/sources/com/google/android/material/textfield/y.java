package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes3.dex */
class y extends s {
    private int e;
    private EditText f;
    private final View.OnClickListener g;

    y(r rVar, int i) {
        super(rVar);
        this.e = ax.R6.f.a;
        this.g = new View.OnClickListener() { // from class: com.google.android.material.textfield.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                y.v(this.q, view);
            }
        };
        if (i != 0) {
            this.e = i;
        }
    }

    public static /* synthetic */ void v(y yVar, View view) {
        EditText editText = yVar.f;
        if (editText == null) {
            return;
        }
        int selectionEnd = editText.getSelectionEnd();
        if (yVar.w()) {
            yVar.f.setTransformationMethod(null);
        } else {
            yVar.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (selectionEnd >= 0) {
            yVar.f.setSelection(selectionEnd);
        }
        yVar.r();
    }

    private boolean w() {
        EditText editText = this.f;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private static boolean x(EditText editText) {
        if (editText != null) {
            return editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224;
        }
        return false;
    }

    @Override // com.google.android.material.textfield.s
    void b(CharSequence charSequence, int i, int i2, int i3) {
        r();
    }

    @Override // com.google.android.material.textfield.s
    int c() {
        return ax.R6.k.R;
    }

    @Override // com.google.android.material.textfield.s
    int d() {
        return this.e;
    }

    @Override // com.google.android.material.textfield.s
    View.OnClickListener f() {
        return this.g;
    }

    @Override // com.google.android.material.textfield.s
    boolean l() {
        return true;
    }

    @Override // com.google.android.material.textfield.s
    boolean m() {
        return !w();
    }

    @Override // com.google.android.material.textfield.s
    void n(EditText editText) {
        this.f = editText;
        r();
    }

    @Override // com.google.android.material.textfield.s
    void s() {
        if (x(this.f)) {
            this.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override // com.google.android.material.textfield.s
    void u() {
        EditText editText = this.f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
