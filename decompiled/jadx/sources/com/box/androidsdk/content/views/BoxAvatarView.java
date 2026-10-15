package com.box.androidsdk.content.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ax.K3.h;
import ax.O3.c;
import ax.O3.e;
import ax.p130i.s;
import com.box.androidsdk.content.models.BoxCollaborator;
import com.box.androidsdk.content.models.BoxDownload;
import com.box.androidsdk.content.models.BoxUser;
import com.box.androidsdk.content.utils.SdkUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class BoxAvatarView extends LinearLayout {
    private b k0;
    private TextView l0;
    private ImageView m0;
    private WeakReference<h<BoxDownload>> n0;
    private BoxCollaborator q;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BoxAvatarView.this.b();
        }
    }

    public interface b {
        File a(String str);

        h<BoxDownload> b(String str, BoxAvatarView boxAvatarView);
    }

    public BoxAvatarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BoxAvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.e, i, 0);
        View viewInflate = layoutInflaterFrom.inflate(c.d, (ViewGroup) this, true);
        this.l0 = (TextView) viewInflate.findViewById(ax.O3.b.e);
        int integer = typedArrayObtainStyledAttributes.getInteger(e.f, 0);
        if (integer != 0) {
            this.l0.setTextSize(2, integer);
        }
        this.m0 = (ImageView) viewInflate.findViewById(ax.O3.b.d);
    }

    public <T extends Serializable & b> void a(BoxCollaborator boxCollaborator, T t) {
        if (t != null) {
            this.k0 = t;
        }
        BoxCollaborator boxCollaborator2 = this.q;
        if (boxCollaborator2 == null || boxCollaborator == null || !TextUtils.equals(boxCollaborator2.J(), boxCollaborator.J())) {
            this.q = boxCollaborator;
            WeakReference<h<BoxDownload>> weakReference = this.n0;
            if (weakReference != null && weakReference.get() != null) {
                try {
                    this.n0.get().cancel(true);
                } catch (Exception unused) {
                }
            }
            b();
        }
    }

    protected void b() {
        String strM;
        int i;
        if (this.q == null || this.k0 == null) {
            return;
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            post(new a());
            return;
        }
        File fileA = this.k0.a(this.q.J());
        if (fileA.exists()) {
            this.m0.setImageDrawable(Drawable.createFromPath(fileA.getAbsolutePath()));
            this.m0.setVisibility(0);
            this.l0.setVisibility(8);
            return;
        }
        if (s.a(this.q)) {
            strM = this.q.K();
        } else {
            strM = HttpUrl.FRAGMENT_ENCODE_SET;
            if (SdkUtils.k(HttpUrl.FRAGMENT_ENCODE_SET)) {
                BoxCollaborator boxCollaborator = this.q;
                if (boxCollaborator instanceof BoxUser) {
                    strM = ((BoxUser) boxCollaborator).M();
                }
            }
        }
        try {
            i = Integer.parseInt(strM);
        } catch (NumberFormatException unused) {
            i = 0;
        }
        if (i == 0) {
            SdkUtils.s(getContext(), this.l0, strM);
        } else {
            SdkUtils.o(getContext(), this.l0, i);
        }
        this.m0.setVisibility(8);
        this.l0.setVisibility(0);
        this.n0 = new WeakReference<>(this.k0.b(this.q.J(), this));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        this.k0 = (b) bundle.getSerializable("extraAvatarController");
        this.q = (BoxUser) bundle.getSerializable("extraUser");
        super.onRestoreInstanceState(bundle.getParcelable("extraParent"));
        if (this.q != null) {
            b();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("extraAvatarController", (Serializable) this.k0);
        bundle.putSerializable("extraUser", this.q);
        bundle.putParcelable("extraParent", super.onSaveInstanceState());
        return bundle;
    }
}
