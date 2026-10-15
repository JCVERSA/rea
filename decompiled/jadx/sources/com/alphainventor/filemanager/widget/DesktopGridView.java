package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.GridView;

/* JADX INFO: loaded from: classes.dex */
public class DesktopGridView extends GridView {
    public DesktopGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.GridView, android.widget.AbsListView
    public void onInitializeAccessibilityNodeInfoForItem(View view, int i, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoForItem(view, i, accessibilityNodeInfo);
        if (accessibilityNodeInfo.isSelected()) {
            accessibilityNodeInfo.setSelected(false);
        }
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_SELECTION);
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SELECT);
    }
}
