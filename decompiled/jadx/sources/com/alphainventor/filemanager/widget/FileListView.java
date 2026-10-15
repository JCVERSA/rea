package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ListView;
import ax.C3.m;

/* JADX INFO: loaded from: classes.dex */
public class FileListView extends ListView implements m {
    private boolean q;

    public FileListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // ax.C3.m
    public void a() {
        this.q = true;
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    public void onInitializeAccessibilityNodeInfoForItem(View view, int i, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoForItem(view, i, accessibilityNodeInfo);
        if (accessibilityNodeInfo.isSelected()) {
            accessibilityNodeInfo.setSelected(false);
        }
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_SELECTION);
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SELECT);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        if (this.q) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.q = false;
            } else {
                if (action == 1) {
                    this.q = false;
                    return true;
                }
                if (action == 2) {
                    return true;
                }
            }
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (RuntimeException unused) {
            return false;
        }
    }
}
