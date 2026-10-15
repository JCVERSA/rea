package com.alphainventor.filemanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class FileListView extends ListView {
    private boolean q;

    public FileListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.q = true;
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
        return super.onTouchEvent(motionEvent);
    }
}
