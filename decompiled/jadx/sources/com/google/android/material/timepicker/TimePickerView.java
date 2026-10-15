package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import ax.R6.g;
import ax.R6.i;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes3.dex */
class TimePickerView extends ConstraintLayout {
    private final Chip H0;
    private final Chip I0;
    private final ClockHandView J0;
    private final ClockFaceView K0;
    private final MaterialButtonToggleGroup L0;
    private final View.OnClickListener M0;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TimePickerView.C(TimePickerView.this);
        }
    }

    class b extends GestureDetector.SimpleOnGestureListener {
        b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            TimePickerView.D(TimePickerView.this);
            return false;
        }
    }

    class c implements View.OnTouchListener {
        final /* synthetic */ GestureDetector q;

        c(GestureDetector gestureDetector) {
            this.q = gestureDetector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((Checkable) view).isChecked()) {
                return this.q.onTouchEvent(motionEvent);
            }
            return false;
        }
    }

    interface d {
    }

    interface e {
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.M0 = new a();
        LayoutInflater.from(context).inflate(i.n, this);
        this.K0 = (ClockFaceView) findViewById(g.k);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(g.n);
        this.L0 = materialButtonToggleGroup;
        materialButtonToggleGroup.q(new MaterialButtonToggleGroup.b() { // from class: com.google.android.material.timepicker.f
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.b
            public final void a(MaterialButtonToggleGroup materialButtonToggleGroup2, int i2, boolean z) {
                this.a.getClass();
            }
        });
        this.H0 = (Chip) findViewById(g.q);
        this.I0 = (Chip) findViewById(g.o);
        this.J0 = (ClockHandView) findViewById(g.l);
        F();
        E();
    }

    static /* synthetic */ e C(TimePickerView timePickerView) {
        timePickerView.getClass();
        return null;
    }

    static /* synthetic */ d D(TimePickerView timePickerView) {
        timePickerView.getClass();
        return null;
    }

    private void E() {
        Chip chip = this.H0;
        int i = g.L;
        chip.setTag(i, 12);
        this.I0.setTag(i, 10);
        this.H0.setOnClickListener(this.M0);
        this.I0.setOnClickListener(this.M0);
        this.H0.setAccessibilityClassName("android.view.View");
        this.I0.setAccessibilityClassName("android.view.View");
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void F() {
        c cVar = new c(new GestureDetector(getContext(), new b()));
        this.H0.setOnTouchListener(cVar);
        this.I0.setOnTouchListener(cVar);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            this.I0.sendAccessibilityEvent(8);
        }
    }
}
