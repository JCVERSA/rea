package com.alphainventor.filemanager.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import ax.J1.F;
import ax.p017b2.w;
import ax.p031c2.i;
import ax.p045d2.l;
import ax.p045d2.x;
import ax.p221pa.c;
import ax.p338y1.a;
import ax.p338y1.b;
import com.alphainventor.filemanager.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class StorageCheckReceiver extends BroadcastReceiver {
    public static void a(Context context, F f) {
        if (F.e.equals(f)) {
            w.j(context).a(201);
        } else if (F.f.equals(f)) {
            w.j(context).a(202);
        } else {
            w.j(context).a(201);
            w.j(context).a(202);
        }
    }

    public static void b(Context context, boolean z) {
        if (i.I(context)) {
            try {
                Intent intent = new Intent(context, (Class<?>) StorageCheckReceiver.class);
                intent.setAction("filemanager.intent.action.STORAGE_CHECK");
                PendingIntent pendingIntentC = ax.p045d2.i.c(context, 200, intent, 0);
                Calendar calendar = Calendar.getInstance();
                int i = calendar.get(11);
                calendar.set(11, 21);
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.set(14, 0);
                long timeInMillis = calendar.getTimeInMillis();
                if (21 - i <= 1) {
                    timeInMillis += 86400000;
                }
                AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
                alarmManager.cancel(pendingIntentC);
                alarmManager.setInexactRepeating(1, timeInMillis, 86400000L, pendingIntentC);
            } catch (NullPointerException | SecurityException e) {
                c.h().g().d("AlarmManager Error").m(e).i();
            }
        }
    }

    private static void c(Context context, CharSequence charSequence, F f, int i) {
        try {
            w.j(context).l(i, w.j(context).h(f, charSequence));
            a.k().o("notification", "storage_full_notified").c("loc", f.d().H()).e();
        } catch (SecurityException unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        b.k(context);
        if (i.I(context) && l.e(context)) {
            if (ax.G1.i.D().f0(context)) {
                c(context, Html.fromHtml(context.getString(R.string.storage_is_full, context.getString(R.string.location_mainstorage), "<font color='red'>" + x.S(ax.G1.i.D().J()) + "</font>")), F.e, 201);
            }
            if (ax.G1.i.D().l0() && ax.G1.i.D().m0(context)) {
                c(context, Html.fromHtml(context.getString(R.string.storage_is_full, context.getString(R.string.location_sdcard), "<font color='red'>" + x.S(ax.G1.i.D().P()) + "</font>")), F.f, 202);
            }
        }
    }
}
