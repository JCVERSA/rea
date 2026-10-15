package com.alphainventor.filemanager.oss;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ax.U2.h;
import ax.p054d3.J;
import ax.p086f3.C2014u;
import ax.p406z3.B;
import com.alphainventor.filemanager.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OssLicenseActivity extends ax.V2.c {
    private ListView q;

    class a implements AdapterView.OnItemClickListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            OssLicenseActivity.this.K((ax.p246p3.a) adapterView.getAdapter().getItem(i));
        }
    }

    public static class b extends J {
        private String A1;
        private String z1;

        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.this.getContext() == null) {
                    return;
                }
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(b.this.z1));
                    C2014u.p0(b.this, intent);
                } catch (ActivityNotFoundException | SecurityException unused) {
                    Toast.makeText(b.this.getContext(), R.string.error, 1).show();
                }
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.oss.OssLicenseActivity$b$b, reason: collision with other inner class name */
        class DialogInterfaceOnClickListenerC0541b implements DialogInterface.OnClickListener {
            DialogInterfaceOnClickListenerC0541b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        static b t3(ax.p246p3.a aVar) {
            b bVar = new b();
            Bundle bundle = new Bundle();
            bundle.putString("PROJECT", aVar.q);
            bundle.putString("URL", aVar.X);
            bVar.F2(bundle);
            return bVar;
        }

        @Override // ax.p054d3.J
        public void p3() {
            super.p3();
            Bundle bundleS0 = s0();
            if (bundleS0 != null) {
                this.z1 = bundleS0.getString("URL");
                this.A1 = bundleS0.getString("PROJECT");
            }
        }

        @Override // ax.p054d3.J
        public Dialog q3() {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
            androidx.appcompat.app.a.C0008a c0008a = new androidx.appcompat.app.a.C0008a(getContext());
            View viewInflate = layoutInflaterFrom.inflate(R.layout.dialog_oss_project, (ViewGroup) null, false);
            TextView textView = (TextView) viewInflate.findViewById(R.id.text);
            textView.setOnClickListener(new a());
            c0008a.setTitle(this.A1);
            SpannableString spannableString = new SpannableString(this.z1);
            Linkify.addLinks(spannableString, 1);
            textView.setText(spannableString);
            c0008a.setView(viewInflate);
            c0008a.b(true);
            c0008a.setPositiveButton(android.R.string.ok, new DialogInterfaceOnClickListenerC0541b());
            return c0008a.create();
        }
    }

    private static class c extends ArrayAdapter<ax.p246p3.a> {
        c(Context context, List<ax.p246p3.a> list) {
            super(context, 0, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.item_oss_license, (ViewGroup) null, false);
            ((TextView) viewInflate.findViewById(R.id.license)).setText(((ax.p246p3.a) getItem(i)).q);
            return viewInflate;
        }
    }

    private void J() {
        ListView listView = (ListView) findViewById(R.id.list);
        this.q = listView;
        listView.setOnItemClickListener(new a());
        this.q.setAdapter((ListAdapter) new c(this, h.a()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(ax.p246p3.a aVar) {
        B.d0(getSupportFragmentManager(), b.t3(aVar), "OSS", true);
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        return false;
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
        if (z) {
            return;
        }
        this.q.setClipToPadding(false);
        this.q.setPadding(0, 0, 0, bVar.d);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oss_license);
        setTitle(R.string.open_source_licenses);
        J();
        onSetContentView(true);
    }
}
