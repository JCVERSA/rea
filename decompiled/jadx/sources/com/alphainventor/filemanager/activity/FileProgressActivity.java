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
import ax.C3.n;
import ax.Z2.j;
import ax.p210n.ActivityC2164c;
import ax.p374x3.w;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.service.CommandService;

/* JADX INFO: loaded from: classes.dex */
public class FileProgressActivity extends ax.V2.c implements ax.V2.a {
    private TextView X;
    private n Y;
    private CommandService Z;
    private long k0;
    private boolean l0;
    private ServiceConnection m0 = new a();
    private ListView q;

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String str;
            if (iBinder instanceof CommandService.d) {
                FileProgressActivity.this.Z = ((CommandService.d) iBinder).a();
                FileProgressActivity.this.Z.K(FileProgressActivity.this);
                FileProgressActivity.this.R();
                FileProgressActivity fileProgressActivity = FileProgressActivity.this;
                FileProgressActivity fileProgressActivity2 = FileProgressActivity.this;
                fileProgressActivity.Y = new n(fileProgressActivity2, fileProgressActivity2.Z.n());
                FileProgressActivity.this.q.setAdapter((ListAdapter) FileProgressActivity.this.Y);
                return;
            }
            if (iBinder == null) {
                str = "service : null";
            } else {
                str = "service :" + iBinder.getClass().getName();
            }
            ax.p302sb.c.h().g().b("INVALID SERVICE CLASS").h(str).i();
            Toast.makeText(FileProgressActivity.this, R.string.error, 1).show();
            FileProgressActivity.this.finish();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            FileProgressActivity.this.Z = null;
        }
    }

    class b extends ax.p182l3.d {
        b() {
        }

        @Override // ax.p182l3.d
        public void a(AdapterView<?> adapterView, View view, int i, long j) {
            FileProgressActivity.this.T(i);
        }
    }

    class c implements Runnable {
        final /* synthetic */ j X;
        final /* synthetic */ int q;

        c(int i, j jVar) {
            this.q = i;
            this.X = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            View childAt = FileProgressActivity.this.q.getChildAt(this.q - FileProgressActivity.this.q.getFirstVisiblePosition());
            if (childAt != null) {
                ((n.a) childAt.getTag()).a(this.X, this.q);
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FileProgressActivity.this.Y != null) {
                FileProgressActivity.this.Y.notifyDataSetChanged();
            }
        }
    }

    private void P() {
        this.l0 = true;
        bindService(CommandService.j(this, false), this.m0, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        CommandService commandService = this.Z;
        if (commandService != null) {
            commandService.G(this);
            w.j(this).a(102);
        }
    }

    private void S() {
        this.q = (ListView) findViewById(R.id.file_progress_lv_list);
        TextView textView = (TextView) findViewById(R.id.file_progress_tv_empty);
        this.X = textView;
        this.q.setEmptyView(textView);
        this.q.setOnItemClickListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(int i) {
        CommandService commandService = this.Z;
        if (commandService != null && i >= 0 && i < commandService.n().size()) {
            this.Z.J(this, this.Z.n().get(i), false);
        }
    }

    public void Q() {
        if (this.l0) {
            this.l0 = false;
            CommandService commandService = this.Z;
            if (commandService != null) {
                commandService.I(this);
                unbindService(this.m0);
            }
        }
    }

    public void W() {
        runOnUiThread(new d());
    }

    public void X(j jVar, int i, boolean z) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (z || jUptimeMillis - this.k0 > 100) {
            this.k0 = jUptimeMillis;
            runOnUiThread(new c(i, jVar));
        }
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        return false;
    }

    @Override // ax.V2.a
    public boolean k() {
        return getSupportFragmentManager().Q0();
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
        if (z) {
            return;
        }
        this.q.setClipToPadding(false);
        ListView listView = this.q;
        listView.setPadding(listView.getPaddingLeft(), this.q.getPaddingTop(), this.q.getPaddingRight(), bVar.d);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_file_progress);
        getWindow().addFlags(128);
        S();
        onSetContentView(true);
        P();
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onDestroy() {
        Q();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onResume() {
        super.onResume();
        R();
    }

    @Override // ax.V2.a
    public ActivityC2164c r() {
        return this;
    }
}
