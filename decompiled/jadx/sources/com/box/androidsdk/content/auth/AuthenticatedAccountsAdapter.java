package com.box.androidsdk.content.auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ax.O3.b;
import ax.O3.c;
import com.box.androidsdk.content.models.BoxUser;
import com.box.androidsdk.content.utils.SdkUtils;
import com.box.androidsdk.content.views.BoxAvatarView;
import com.box.androidsdk.content.views.OfflineAvatarController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticatedAccountsAdapter extends ArrayAdapter<BoxAuthentication.BoxAuthenticationInfo> {
    private OfflineAvatarController q;

    public static class DifferentAuthenticationInfo extends BoxAuthentication.BoxAuthenticationInfo {
    }

    public static class a {
        public TextView a;
        public TextView b;
        public BoxAvatarView c;
    }

    public AuthenticatedAccountsAdapter(Context context, int i, List<BoxAuthentication.BoxAuthenticationInfo> list) {
        super(context, i, list);
        this.q = new OfflineAvatarController(context);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BoxAuthentication.BoxAuthenticationInfo getItem(int i) {
        return i == getCount() + (-1) ? new DifferentAuthenticationInfo() : (BoxAuthentication.BoxAuthenticationInfo) super.getItem(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        if (i == getCount() - 1) {
            return 1;
        }
        return super.getItemViewType(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 1) {
            return LayoutInflater.from(getContext()).inflate(c.g, viewGroup, false);
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(c.f, viewGroup, false);
        a aVar = (a) viewInflate.getTag();
        if (aVar == null) {
            aVar = new a();
            aVar.a = (TextView) viewInflate.findViewById(b.c);
            aVar.b = (TextView) viewInflate.findViewById(b.a);
            aVar.c = (BoxAvatarView) viewInflate.findViewById(b.b);
            viewInflate.setTag(aVar);
        }
        BoxAuthentication.BoxAuthenticationInfo item = getItem(i);
        if (item == null || item.P() == null) {
            if (item != null) {
                ax.N3.b.a("invalid account info", item.z());
            }
            return viewInflate;
        }
        boolean zL = SdkUtils.l(item.P().K());
        BoxUser boxUserP = item.P();
        aVar.a.setText(!zL ? boxUserP.K() : boxUserP.M());
        if (!zL) {
            aVar.b.setText(item.P().M());
        }
        aVar.c.a(item.P(), this.q);
        return viewInflate;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }
}
