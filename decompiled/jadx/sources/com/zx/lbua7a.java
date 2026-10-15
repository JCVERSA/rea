package com.zx;

import android.content.DialogInterface;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes5.dex */
class lbua7a implements DialogInterface.OnKeyListener {
    private final LB a14;

    lbua7a(LB lb) {
        this.a14 = lb;
    }

    static LB a15(lbua7a lbua7aVar) {
        return lbua7aVar.a14;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4 && keyEvent.getAction() == 1;
    }
}
