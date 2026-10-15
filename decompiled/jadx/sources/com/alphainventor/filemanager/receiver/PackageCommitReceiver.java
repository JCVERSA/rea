package com.alphainventor.filemanager.receiver;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import ax.X2.d;
import ax.X2.f;
import ax.p086f3.C2014u;
import ax.p302sb.c;
import com.alphainventor.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class PackageCommitReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", -9999);
        if (intExtra == -9999) {
            return;
        }
        if (intExtra == -1) {
            try {
                Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
                intent2.addFlags(268435456);
                C2014u.o0(context, intent2);
                return;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(context, R.string.no_application, 1).show();
                return;
            } catch (Exception e2) {
                Toast.makeText(context, R.string.error, 1).show();
                c.i(context).g().b("PACKAGE COMMIT RECEIVER ERROR").m(e2).i();
                return;
            }
        }
        if (intExtra == 0) {
            String stringExtra = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
            if (stringExtra != null) {
                f fVarX = d.F(context).x(stringExtra);
                if (fVarX != null) {
                    Toast.makeText(context, context.getString(R.string.msg_app_is_installed, fVarX.s()), 1).show();
                    return;
                } else {
                    Toast.makeText(context, context.getString(R.string.msg_app_is_installed, stringExtra), 1).show();
                    return;
                }
            }
            return;
        }
        if (intExtra == 3) {
            return;
        }
        if (intExtra == 7) {
            String stringExtra2 = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
            String string = context.getString(R.string.error);
            if (stringExtra2 != null) {
                string = string + " (" + stringExtra2 + ")";
            }
            Toast.makeText(context, string, 1).show();
            return;
        }
        if (intExtra > 0) {
            String stringExtra3 = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
            String string2 = context.getString(R.string.error);
            if (stringExtra3 != null) {
                string2 = string2 + " (" + stringExtra3 + ")";
            }
            Toast.makeText(context, string2, 1).show();
        }
    }
}
