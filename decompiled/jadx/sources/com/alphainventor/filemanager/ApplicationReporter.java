package com.alphainventor.filemanager;

import android.content.Context;
import androidx.annotation.Keep;
import ax.p302sb.c;
import ax.p390y3.h;
import ax.p390y3.k;
import com.socialnmobile.commons.reporter.ReporterService;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class ApplicationReporter implements ReporterService {
    static final String EVENT_COLLECTOR_URL = "";
    static Context sAppContext;
    static c sReporter;

    private String getParentPackage(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        return iLastIndexOf < 0 ? str : str.substring(0, iLastIndexOf);
    }

    public static void init(Context context) {
        Context context2;
        if (sReporter == null) {
            new ApplicationReporter().initializeService();
            if (ax.U2.c.a() && !c.g()) {
                throw new RuntimeException("SERVICE LOADER DOES NOT WORK!!!");
            }
        }
        if (sAppContext == null && context != null) {
            Context applicationContext = context.getApplicationContext();
            sAppContext = applicationContext;
            c.k(k.o(applicationContext));
        }
        c cVar = sReporter;
        if (cVar != null && (context2 = sAppContext) != null) {
            cVar.j(context2);
        }
        c.k(false);
    }

    @Override // com.socialnmobile.commons.reporter.ReporterService
    public void initializeService() {
        String str = "3.8.3";
        if (h.h() && System.currentTimeMillis() > 0) {
            str = "3.8.3-mod";
        }
        c cVarF = c.f("filemanagerplus", 2103083, str, "fileplusApi21-release", "", false);
        sReporter = cVarF;
        cVarF.l("BsAK");
    }
}
