package com.box.androidsdk.content.auth;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import ax.O3.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a extends Fragment {
    private ListView q;

    /* JADX INFO: renamed from: com.box.androidsdk.content.auth.a$a, reason: collision with other inner class name */
    class C0564a implements AdapterView.OnItemClickListener {
        C0564a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (adapterView.getAdapter() instanceof AuthenticatedAccountsAdapter) {
                BoxAuthentication.BoxAuthenticationInfo item = ((AuthenticatedAccountsAdapter) adapterView.getAdapter()).getItem(i);
                if (item instanceof AuthenticatedAccountsAdapter.DifferentAuthenticationInfo) {
                    if (a.this.getActivity() instanceof b) {
                        ((b) a.this.getActivity()).f();
                    }
                } else if (a.this.getActivity() instanceof b) {
                    ((b) a.this.getActivity()).b(item);
                }
            }
        }
    }

    public interface b {
        void b(BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo);

        void f();
    }

    public static a a(Context context) {
        return new a();
    }

    public ArrayList<BoxAuthentication.BoxAuthenticationInfo> b() {
        if (getArguments() == null || getArguments().getCharSequenceArrayList("boxAuthenticationInfos") == null) {
            Map<String, BoxAuthentication.BoxAuthenticationInfo> mapR = BoxAuthentication.o().r(getActivity());
            if (mapR == null) {
                return null;
            }
            ArrayList<BoxAuthentication.BoxAuthenticationInfo> arrayList = new ArrayList<>(mapR.size());
            Iterator<String> it = mapR.keySet().iterator();
            while (it.hasNext()) {
                arrayList.add(mapR.get(it.next()));
            }
            return arrayList;
        }
        ArrayList<CharSequence> charSequenceArrayList = getArguments().getCharSequenceArrayList("boxAuthenticationInfos");
        ArrayList<BoxAuthentication.BoxAuthenticationInfo> arrayList2 = new ArrayList<>(charSequenceArrayList.size());
        int size = charSequenceArrayList.size();
        int i = 0;
        while (i < size) {
            CharSequence charSequence = charSequenceArrayList.get(i);
            i++;
            BoxAuthentication.BoxAuthenticationInfo boxAuthenticationInfo = new BoxAuthentication.BoxAuthenticationInfo();
            boxAuthenticationInfo.j(charSequence.toString());
            arrayList2.add(boxAuthenticationInfo);
        }
        return arrayList2;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ArrayList<BoxAuthentication.BoxAuthenticationInfo> arrayListB = b();
        View viewInflate = layoutInflater.inflate(c.e, (ViewGroup) null);
        ListView listView = (ListView) viewInflate.findViewById(ax.O3.b.f);
        this.q = listView;
        if (arrayListB == null) {
            getActivity().getFragmentManager().beginTransaction().remove(this).commit();
            return viewInflate;
        }
        listView.setAdapter((ListAdapter) new AuthenticatedAccountsAdapter(getActivity(), c.f, arrayListB));
        this.q.setOnItemClickListener(new C0564a());
        return viewInflate;
    }
}
