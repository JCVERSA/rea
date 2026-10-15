package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import ax.R6.g;
import ax.R6.i;
import ax.p202m7.v;
import ax.p202m7.z;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes3.dex */
class ChipTextInputComboView extends FrameLayout implements Checkable {
    private final TextInputLayout k0;
    private final EditText l0;
    private TextWatcher m0;
    private TextView n0;
    private final Chip q;

    private class b extends v {
        private b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                ChipTextInputComboView.this.q.setText(ChipTextInputComboView.this.c("00"));
                return;
            }
            String strC = ChipTextInputComboView.this.c(editable);
            Chip chip = ChipTextInputComboView.this.q;
            if (TextUtils.isEmpty(strC)) {
                strC = ChipTextInputComboView.this.c("00");
            }
            chip.setText(strC);
        }
    }

    public ChipTextInputComboView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChipTextInputComboView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Chip chip = (Chip) layoutInflaterFrom.inflate(i.l, (ViewGroup) this, false);
        this.q = chip;
        chip.setAccessibilityClassName("android.view.View");
        TextInputLayout textInputLayout = (TextInputLayout) layoutInflaterFrom.inflate(i.m, (ViewGroup) this, false);
        this.k0 = textInputLayout;
        EditText editText = textInputLayout.getEditText();
        this.l0 = editText;
        editText.setVisibility(4);
        b bVar = new b();
        this.m0 = bVar;
        editText.addTextChangedListener(bVar);
        d();
        addView(chip);
        addView(textInputLayout);
        this.n0 = (TextView) findViewById(g.p);
        editText.setId(View.generateViewId());
        this.n0.setLabelFor(editText.getId());
        editText.setSaveEnabled(false);
        editText.setLongClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(CharSequence charSequence) {
        return e.a(getResources(), charSequence);
    }

    private void d() {
        if (Build.VERSION.SDK_INT >= 24) {
            this.l0.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.q.isChecked();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        d();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.q.setChecked(z);
        this.l0.setVisibility(z ? 0 : 4);
        this.q.setVisibility(z ? 8 : 0);
        if (isChecked()) {
            z.k(this.l0, false);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.q.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        this.q.setTag(i, obj);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.q.toggle();
    }
}
