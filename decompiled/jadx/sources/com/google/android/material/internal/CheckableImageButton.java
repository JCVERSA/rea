package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import androidx.appcompat.widget.p;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;

/* JADX INFO: loaded from: classes3.dex */
public class CheckableImageButton extends p implements Checkable {
    private static final int[] p0 = {R.attr.state_checked};
    private boolean m0;
    private boolean n0;
    private boolean o0;

    class a extends C1832a {
        a() {
        }

        @Override // ax.p035c0.C1832a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.k0(CheckableImageButton.this.a());
            h.l0(CheckableImageButton.this.isChecked());
        }
    }

    static class b extends ax.p163k0.a {
        public static final Parcelable.Creator<b> CREATOR = new a();
        boolean Y;

        class a implements Parcelable.ClassLoaderCreator<b> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new b(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i) {
                return new b[i];
            }
        }

        public b(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            b(parcel);
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        private void b(Parcel parcel) {
            this.Y = parcel.readInt() == 1;
        }

        @Override // ax.p163k0.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.Y ? 1 : 0);
        }
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.p194m.a.G);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n0 = true;
        this.o0 = true;
        C1839d0.l0(this, new a());
    }

    public boolean a() {
        return this.n0;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.m0;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        if (!this.m0) {
            return super.onCreateDrawableState(i);
        }
        int[] iArr = p0;
        return View.mergeDrawableStates(super.onCreateDrawableState(i + iArr.length), iArr);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.a());
        setChecked(bVar.Y);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.Y = this.m0;
        return bVar;
    }

    public void setCheckable(boolean z) {
        if (this.n0 != z) {
            this.n0 = z;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.n0 || this.m0 == z) {
            return;
        }
        this.m0 = z;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z) {
        this.o0 = z;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        if (this.o0) {
            super.setPressed(z);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.m0);
    }
}
