package com.zx;

import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
class lbua6i implements View.OnClickListener {
    private final LB a14;

    lbua6i(LB lb) {
        this.a14 = lb;
    }

    static LB a15(lbua6i lbua6iVar) {
        return lbua6iVar.a14;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a14.handleSwitchPreference();
        this.a14.dismiss();
    }
}
