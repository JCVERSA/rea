package com.alphainventor.filemanager.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.AndroidRuntimeException;
import ax.U2.a;
import ax.U2.b;
import ax.p038c3.j;
import ax.p086f3.K;
import ax.p302sb.c;
import ax.p374x3.w;
import ax.p390y3.k;
import ax.p406z3.B;
import ax.p406z3.l;
import ax.p406z3.o;
import com.alphainventor.filemanager.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class StorageCheckReceiver extends BroadcastReceiver {
    public static void a(Context context, K k) {
        if (K.e.equals(k)) {
            w.j(context).a(201);
        } else if (K.f.equals(k)) {
            w.j(context).a(202);
        } else {
            w.j(context).a(201);
            w.j(context).a(202);
        }
    }

    public static void b(Context context, boolean z) {
        if (k.M(context)) {
            try {
                Intent intent = new Intent(context, (Class<?>) StorageCheckReceiver.class);
                intent.setAction("filemanager.intent.action.STORAGE_CHECK");
                PendingIntent pendingIntentB = l.b(context, 200, intent, 0);
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
                alarmManager.cancel(pendingIntentB);
                alarmManager.setInexactRepeating(1, timeInMillis, 86400000L, pendingIntentB);
            } catch (NullPointerException | SecurityException e) {
                c.h().g().d("AlarmManager Error").m(e).i();
            }
        }
    }

    private static void c(Context context, CharSequence charSequence, K k, int i) {
        try {
            w.j(context).l(i, w.j(context).h(context, k, charSequence));
            a.k().o("notification", "storage_full_notified").c("loc", k.d().H()).e();
        } catch (AndroidRuntimeException | SecurityException unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        b.k(context);
        if (k.M(context) && o.e(context)) {
            if (j.F().o0(context)) {
                c(context, Html.fromHtml(context.getString(R.string.storage_is_full, context.getString(R.string.location_mainstorage), "<font color='red'>" + B.Q(j.F().P()) + "</font>")), K.e, 201);
            }
            if (j.F().u0() && j.F().v0(context)) {
                c(context, Html.fromHtml(context.getString(R.string.storage_is_full, context.getString(R.string.location_sdcard), "<font color='red'>" + B.Q(j.F().V()) + "</font>")), K.f, 202);
            }
        }
    }
}
