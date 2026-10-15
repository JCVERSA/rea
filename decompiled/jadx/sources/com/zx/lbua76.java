package com.zx;

import android.view.View;
import com.zx.cmz.Strings;

/* JADX INFO: loaded from: classes5.dex */
class lbua76 implements View.OnClickListener {
    private final LB a14;

    lbua76(LB lb) {
        this.a14 = lb;
    }

    static LB a15(lbua76 lbua76Var) {
        return lbua76Var.a14;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a14.openURL(Strings.getPositiveButtonLink());
    }
}
