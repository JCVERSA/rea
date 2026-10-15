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
import ax.H1.G;
import ax.J1.C0734o;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import ax.p338y1.h;
import com.alphainventor.filemanager.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OssLicenseActivity extends ActivityC5571c {

    class a implements AdapterView.OnItemClickListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            OssLicenseActivity.this.X0((ax.T1.a) adapterView.getAdapter().getItem(i));
        }
    }

    public static class b extends G {
        private String w1;
        private String x1;

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
                    intent.setData(Uri.parse(b.this.w1));
                    C0734o.i0(b.this, intent);
                } catch (ActivityNotFoundException | SecurityException unused) {
                    Toast.makeText(b.this.getContext(), R.string.error, 1).show();
                }
            }
        }

        /* JADX INFO: renamed from: com.alphainventor.filemanager.oss.OssLicenseActivity$b$b, reason: collision with other inner class name */
        class DialogInterfaceOnClickListenerC0507b implements DialogInterface.OnClickListener {
            DialogInterfaceOnClickListenerC0507b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        static b w3(ax.T1.a aVar) {
            b bVar = new b();
            Bundle bundle = new Bundle();
            bundle.putString("PROJECT", aVar.q);
            bundle.putString("URL", aVar.X);
            bVar.J2(bundle);
            return bVar;
        }

        @Override // ax.H1.G
        public void s3() {
            super.s3();
            Bundle bundleW0 = w0();
            if (bundleW0 != null) {
                this.w1 = bundleW0.getString("URL");
                this.x1 = bundleW0.getString("PROJECT");
            }
        }

        @Override // ax.H1.G
        public Dialog t3() {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
            androidx.appcompat.app.a.C0006a c0006a = new androidx.appcompat.app.a.C0006a(getContext());
            View viewInflate = layoutInflaterFrom.inflate(R.layout.dialog_oss_project, (ViewGroup) null, false);
            TextView textView = (TextView) viewInflate.findViewById(R.id.text);
            textView.setOnClickListener(new a());
            c0006a.t(this.x1);
            SpannableString spannableString = new SpannableString(this.w1);
            Linkify.addLinks(spannableString, 1);
            textView.setText(spannableString);
            c0006a.u(viewInflate);
            c0006a.d(true);
            c0006a.o(android.R.string.ok, new DialogInterfaceOnClickListenerC0507b());
            return c0006a.a();
        }
    }

    private static class c extends ArrayAdapter<ax.T1.a> {
        c(Context context, List<ax.T1.a> list) {
            super(context, 0, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.item_oss_license, (ViewGroup) null, false);
            ((TextView) viewInflate.findViewById(R.id.license)).setText(((ax.T1.a) getItem(i)).q);
            return viewInflate;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0(ax.T1.a aVar) {
        x.d0(w(), b.w3(aVar), "OSS", true);
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oss_license);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new a());
        listView.setAdapter((ListAdapter) new c(this, h.a()));
    }
}
