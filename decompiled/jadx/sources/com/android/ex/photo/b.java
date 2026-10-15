package com.android.ex.photo;

import android.graphics.drawable.Drawable;
import ax.p210n.AbstractC2162a;

/* JADX INFO: loaded from: classes.dex */
public class b implements com.android.ex.photo.a {
    private final AbstractC2162a a;

    private class a implements AbstractC2162a.b {
        private final com.android.ex.photo.a.InterfaceC0561a a;

        public a(com.android.ex.photo.a.InterfaceC0561a interfaceC0561a) {
            this.a = interfaceC0561a;
        }

        @Override // ax.p210n.AbstractC2162a.b
        public void a(boolean z) {
            this.a.a(z);
        }
    }

    public b(AbstractC2162a abstractC2162a) {
        this.a = abstractC2162a;
    }

    @Override // com.android.ex.photo.a
    public void a() {
        this.a.x(8, 8);
    }

    @Override // com.android.ex.photo.a
    public void b(boolean z) {
        this.a.w(z);
    }

    @Override // com.android.ex.photo.a
    public void c(com.android.ex.photo.a.InterfaceC0561a interfaceC0561a) {
        this.a.f(new a(interfaceC0561a));
    }

    @Override // com.android.ex.photo.a
    public void d(Drawable drawable) {
        this.a.E(drawable);
    }

    @Override // com.android.ex.photo.a
    public void f(CharSequence charSequence) {
        this.a.G(charSequence);
    }

    @Override // com.android.ex.photo.a
    public void setTitle(CharSequence charSequence) {
        this.a.I(charSequence);
    }
}
