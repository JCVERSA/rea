package com.zx;

import android.widget.CompoundButton;
import android.widget.Switch;

/* JADX INFO: loaded from: classes5.dex */
class lbua86 implements CompoundButton.OnCheckedChangeListener {
    private final LB a14;
    private final Switch a15;

    lbua86(LB lb, Switch r2) {
        this.a14 = lb;
        this.a15 = r2;
    }

    static LB a15(lbua86 lbua86Var) {
        return lbua86Var.a14;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a14.dontShowAgainChecked = z;
        this.a14.styleSwitch(this.a15, z);
    }
}
