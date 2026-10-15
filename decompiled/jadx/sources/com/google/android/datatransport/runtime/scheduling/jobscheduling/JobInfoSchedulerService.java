package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import ax.G5.a;
import ax.p344v5.p;
import ax.p344v5.u;

/* JADX INFO: loaded from: classes3.dex */
public class JobInfoSchedulerService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("backendName");
        String string2 = jobParameters.getExtras().getString("extras");
        int i = jobParameters.getExtras().getInt("priority");
        int i2 = jobParameters.getExtras().getInt("attemptNumber");
        u.f(getApplicationContext());
        p.a aVarD = p.a().b(string).d(a.b(i));
        if (string2 != null) {
            aVarD.c(Base64.decode(string2, 0));
        }
        u.c().e().m(aVarD.a(), i2, new Runnable() { // from class: ax.C5.e
            @Override // java.lang.Runnable
            public final void run() {
                this.q.jobFinished(jobParameters, false);
            }
        });
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
