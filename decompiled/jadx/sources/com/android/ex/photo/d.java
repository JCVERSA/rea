package com.android.ex.photo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public class d {

    public static class b {
        private final Intent a;
        private Integer b;
        private String c;
        private String d;
        private String e;
        private String[] f;
        private String g;
        private Float h;
        private boolean i;
        private boolean j;
        private int k;
        private int l;
        private int m;
        private int n;
        private boolean o;
        private boolean p;

        private b(Context context, Class<?> cls) {
            this.a = new Intent(context, cls);
            b();
        }

        private void b() {
            this.j = false;
            this.o = false;
            this.p = false;
        }

        public Intent a() {
            this.a.setAction("android.intent.action.VIEW");
            this.a.setFlags(67633152);
            Integer num = this.b;
            if (num != null) {
                this.a.putExtra("photo_index", num.intValue());
            }
            String str = this.c;
            if (str != null) {
                this.a.putExtra("initial_photo_uri", str);
            }
            if (this.c != null && this.b != null) {
                throw new IllegalStateException("specified both photo index and photo uri");
            }
            String str2 = this.d;
            if (str2 != null) {
                this.a.putExtra("photos_uri", str2);
                this.a.setData(Uri.parse(this.d));
            }
            String str3 = this.e;
            if (str3 != null) {
                this.a.putExtra("resolved_photo_uri", str3);
            }
            String[] strArr = this.f;
            if (strArr != null) {
                this.a.putExtra("projection", strArr);
            }
            String str4 = this.g;
            if (str4 != null) {
                this.a.putExtra("thumbnail_uri", str4);
            }
            Float f = this.h;
            if (f != null) {
                this.a.putExtra("max_scale", f);
            }
            this.a.putExtra("watch_network", this.i);
            this.a.putExtra("scale_up_animation", this.j);
            if (this.j) {
                this.a.putExtra("start_x_extra", this.k);
                this.a.putExtra("start_y_extra", this.l);
                this.a.putExtra("start_width_extra", this.m);
                this.a.putExtra("start_height_extra", this.n);
            }
            this.a.putExtra("action_bar_hidden_initially", this.o);
            this.a.putExtra("display_thumbs_fullscreen", this.p);
            return this.a;
        }

        public b c(boolean z) {
            this.p = z;
            return this;
        }

        public b d(float f) {
            this.h = Float.valueOf(f);
            return this;
        }

        public b e(String str) {
            this.e = str;
            return this;
        }

        public b f(String str) {
            this.g = str;
            return this;
        }
    }

    public static b a(Context context, Class<? extends ax.F3.a> cls) {
        return new b(context, cls);
    }
}
