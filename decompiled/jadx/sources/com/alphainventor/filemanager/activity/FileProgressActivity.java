package com.alphainventor.filemanager.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import ax.D1.h;
import ax.p017b2.w;
import ax.p087g2.k;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.service.CommandService;

/* JADX INFO: loaded from: classes.dex */
public class FileProgressActivity extends ActivityC5571c implements ax.p352z1.a {
    private Toolbar E0;
    private ListView F0;
    private TextView G0;
    private k H0;
    private CommandService I0;
    private long J0;
    private boolean K0;
    private ServiceConnection L0 = new a();

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String str;
            if (iBinder instanceof CommandService.d) {
                FileProgressActivity.this.I0 = ((CommandService.d) iBinder).a();
                FileProgressActivity.this.I0.K(FileProgressActivity.this);
                FileProgressActivity.this.f1();
                FileProgressActivity fileProgressActivity = FileProgressActivity.this;
                FileProgressActivity fileProgressActivity2 = FileProgressActivity.this;
                fileProgressActivity.H0 = new k(fileProgressActivity2, fileProgressActivity2.I0.n());
                FileProgressActivity.this.F0.setAdapter((ListAdapter) FileProgressActivity.this.H0);
                return;
            }
            if (iBinder == null) {
                str = "service : null";
            } else {
                str = "service :" + iBinder.getClass().getName();
            }
            ax.p221pa.c.h().g().b("INVALID SERVICE CLASS").h(str).i();
            Toast.makeText(FileProgressActivity.this, R.string.error, 1).show();
            FileProgressActivity.this.finish();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            FileProgressActivity.this.I0 = null;
        }
    }

    class b extends ax.P1.d {
        b() {
        }

        @Override // ax.P1.d
        public void a(AdapterView<?> adapterView, View view, int i, long j) {
            FileProgressActivity.this.g1(i);
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FileProgressActivity.this.finish();
        }
    }

    class d implements Runnable {
        final /* synthetic */ h X;
        final /* synthetic */ int q;

        d(int i, h hVar) {
            this.q = i;
            this.X = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            View childAt = FileProgressActivity.this.F0.getChildAt(this.q - FileProgressActivity.this.F0.getFirstVisiblePosition());
            if (childAt != null) {
                ((k.a) childAt.getTag()).a(this.X, this.q);
            }
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FileProgressActivity.this.H0.notifyDataSetChanged();
        }
    }

    private void d1() {
        this.K0 = true;
        bindService(CommandService.j(this, false), this.L0, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f1() {
        CommandService commandService = this.I0;
        if (commandService != null) {
            commandService.G(this);
            w.j(this).a(102);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g1(int i) {
        CommandService commandService = this.I0;
        if (commandService != null && i >= 0 && i < commandService.n().size()) {
            this.I0.J(this, this.I0.n().get(i), false);
        }
    }

    public void e1() {
        if (this.K0) {
            this.K0 = false;
            CommandService commandService = this.I0;
            if (commandService != null) {
                commandService.I(this);
                unbindService(this.L0);
            }
        }
    }

    public void h1() {
        runOnUiThread(new e());
    }

    public void i1(h hVar, int i, boolean z) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (z || jUptimeMillis - this.J0 > 100) {
            this.J0 = jUptimeMillis;
            runOnUiThread(new d(i, hVar));
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_file_progress);
        getWindow().addFlags(128);
        this.E0 = (Toolbar) findViewById(R.id.toolbar);
        this.F0 = (ListView) findViewById(R.id.file_progress_lv_list);
        TextView textView = (TextView) findViewById(R.id.file_progress_tv_empty);
        this.G0 = textView;
        this.F0.setEmptyView(textView);
        this.F0.setOnItemClickListener(new b());
        S0(this.E0);
        I0().u(true);
        this.E0.setNavigationOnClickListener(new c());
        d1();
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        e1();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        f1();
    }

    @Override // ax.p352z1.a
    public boolean v() {
        return w().O0();
    }

    @Override // ax.p352z1.a
    public ActivityC5571c P() {
        return this;
    }
}
