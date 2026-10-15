package com.zx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes5.dex */
class lbua80 implements DialogInterface.OnShowListener {
    private final LB a14;
    private final AlertDialog a15;

    lbua80(LB lb, AlertDialog alertDialog) {
        this.a14 = lb;
        this.a15 = alertDialog;
    }

    static LB a15(lbua80 lbua80Var) {
        return lbua80Var.a14;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        Window window = this.a15.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            ((ViewGroup.LayoutParams) layoutParams).width = (int) (((double) this.a14.getResources().getDisplayMetrics().widthPixels) * 0.8d);
            window.setAttributes(layoutParams);
            window.setBackgroundDrawable(this.a14.createGradientBackground());
        }
    }
}
