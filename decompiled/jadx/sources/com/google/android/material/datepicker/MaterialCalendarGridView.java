package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import ax.p035c0.C1832a;
import ax.p035c0.C1839d0;
import ax.p051d0.H;
import ax.p202m7.z;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class MaterialCalendarGridView extends GridView {
    private final boolean k0;
    private final Calendar q;

    class a extends C1832a {
        a() {
        }

        @Override // ax.p035c0.C1832a
        public void g(View view, H h) {
            super.g(view, h);
            h.o0(null);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = t.r();
        if (k.D3(getContext())) {
            setNextFocusLeftId(ax.R6.g.a);
            setNextFocusRightId(ax.R6.g.d);
        }
        this.k0 = k.F3(getContext());
        C1839d0.l0(this, new a());
    }

    private void a(int i, Rect rect) {
        if (i == 33) {
            setSelection(getAdapter().m());
        } else if (i == 130) {
            setSelection(getAdapter().b());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    private View c(int i) {
        return getChildAt(i - getFirstVisiblePosition());
    }

    private static int d(View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }

    private static boolean e(Long l, Long l2, Long l3, Long l4) {
        return l == null || l2 == null || l3 == null || l4 == null || l3.longValue() > l2.longValue() || l4.longValue() < l.longValue();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public n getAdapter() {
        return (n) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        int iA;
        int iD;
        int iA2;
        int iD2;
        int i;
        int width;
        int right;
        int right2;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        n adapter = materialCalendarGridView.getAdapter();
        ax.p122h7.d<?> dVar = adapter.X;
        c cVar = adapter.Z;
        int iMax = Math.max(adapter.b(), materialCalendarGridView.getFirstVisiblePosition());
        int iMin = Math.min(adapter.m(), materialCalendarGridView.getLastVisiblePosition());
        Long item = adapter.getItem(iMax);
        Long item2 = adapter.getItem(iMin);
        Iterator<ax.p019b0.c<Long, Long>> it = dVar.m().iterator();
        while (it.hasNext()) {
            ax.p019b0.c<Long, Long> next = it.next();
            Long l = next.a;
            if (l == null) {
                materialCalendarGridView = this;
            } else if (next.b != null) {
                Long l2 = l;
                long jLongValue = l2.longValue();
                Long l3 = next.b;
                long jLongValue2 = l3.longValue();
                if (!e(item, item2, l2, l3)) {
                    boolean zH = z.h(materialCalendarGridView);
                    if (jLongValue < item.longValue()) {
                        if (adapter.h(iMax)) {
                            right2 = 0;
                        } else {
                            right2 = !zH ? materialCalendarGridView.c(iMax - 1).getRight() : materialCalendarGridView.c(iMax - 1).getLeft();
                        }
                        iD = right2;
                        iA = iMax;
                    } else {
                        materialCalendarGridView.q.setTimeInMillis(jLongValue);
                        iA = adapter.a(materialCalendarGridView.q.get(5));
                        iD = d(materialCalendarGridView.c(iA));
                    }
                    if (jLongValue2 > item2.longValue()) {
                        if (adapter.i(iMin)) {
                            right = materialCalendarGridView.getWidth();
                        } else {
                            right = !zH ? materialCalendarGridView.c(iMin).getRight() : materialCalendarGridView.c(iMin).getLeft();
                        }
                        iD2 = right;
                        iA2 = iMin;
                    } else {
                        materialCalendarGridView.q.setTimeInMillis(jLongValue2);
                        iA2 = adapter.a(materialCalendarGridView.q.get(5));
                        iD2 = d(materialCalendarGridView.c(iA2));
                    }
                    int itemId = (int) adapter.getItemId(iA);
                    int i2 = iMax;
                    int i3 = iMin;
                    int itemId2 = (int) adapter.getItemId(iA2);
                    while (itemId <= itemId2) {
                        int numColumns = materialCalendarGridView.getNumColumns() * itemId;
                        n nVar = adapter;
                        int numColumns2 = (numColumns + materialCalendarGridView.getNumColumns()) - 1;
                        View viewC = materialCalendarGridView.c(numColumns);
                        int top = viewC.getTop() + cVar.a.c();
                        Iterator<ax.p019b0.c<Long, Long>> it2 = it;
                        int bottom = viewC.getBottom() - cVar.a.b();
                        if (zH) {
                            int i4 = iA2 > numColumns2 ? 0 : iD2;
                            int width2 = numColumns > iA ? getWidth() : iD;
                            i = i4;
                            width = width2;
                        } else {
                            i = numColumns > iA ? 0 : iD;
                            width = iA2 > numColumns2 ? getWidth() : iD2;
                        }
                        canvas.drawRect(i, top, width, bottom, cVar.h);
                        itemId++;
                        materialCalendarGridView = this;
                        adapter = nVar;
                        it = it2;
                    }
                    materialCalendarGridView = this;
                    iMax = i2;
                    iMin = i3;
                }
            }
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            a(i, rect);
        } else {
            super.onFocusChanged(false, i, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition == -1 || (selectedItemPosition >= getAdapter().b() && selectedItemPosition <= getAdapter().m())) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(getAdapter().b());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        if (!this.k0) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof n)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), n.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i) {
        if (i < getAdapter().b()) {
            super.setSelection(getAdapter().b());
        } else {
            super.setSelection(i);
        }
    }
}
