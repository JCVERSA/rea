package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.widget.C0602l;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class TextInputEditText extends C0602l {
    private final Rect p0;
    private boolean q0;

    public TextInputEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ax.p194m.a.E);
    }

    public TextInputEditText(Context context, AttributeSet attributeSet, int i) {
        super(ax.C7.a.d(context, attributeSet, i, 0), attributeSet, i);
        this.p0 = new Rect();
        TypedArray typedArrayI = ax.p202m7.w.i(context, attributeSet, ax.R6.m.O5, i, ax.R6.l.k, new int[0]);
        setTextInputLayoutFocusedRectEnabled(typedArrayI.getBoolean(ax.R6.m.P5, false));
        typedArrayI.recycle();
    }

    private String e(TextInputLayout textInputLayout) {
        Editable text = getText();
        CharSequence hint = textInputLayout.getHint();
        boolean zIsEmpty = TextUtils.isEmpty(text);
        boolean zIsEmpty2 = TextUtils.isEmpty(hint);
        String str = HttpUrl.FRAGMENT_ENCODE_SET;
        String string = !zIsEmpty2 ? hint.toString() : HttpUrl.FRAGMENT_ENCODE_SET;
        if (zIsEmpty) {
            return !TextUtils.isEmpty(string) ? string : HttpUrl.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((Object) text);
        if (!TextUtils.isEmpty(string)) {
            str = ", " + string;
        }
        sb.append(str);
        return sb.toString();
    }

    private boolean f(TextInputLayout textInputLayout) {
        return textInputLayout != null && this.q0;
    }

    private CharSequence getHintFromLayout() {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null) {
            return textInputLayout.getHint();
        }
        return null;
    }

    private TextInputLayout getTextInputLayout() {
        for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        super.getFocusedRect(rect);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!f(textInputLayout) || rect == null) {
            return;
        }
        textInputLayout.getFocusedRect(this.p0);
        rect.bottom = this.p0.bottom;
    }

    @Override // android.view.View
    public boolean getGlobalVisibleRect(Rect rect, Point point) {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!f(textInputLayout)) {
            return super.getGlobalVisibleRect(rect, point);
        }
        boolean globalVisibleRect = textInputLayout.getGlobalVisibleRect(rect, point);
        if (globalVisibleRect && point != null) {
            point.offset(-getScrollX(), -getScrollY());
        }
        return globalVisibleRect;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayout = getTextInputLayout();
        return (textInputLayout == null || !textInputLayout.T()) ? super.getHint() : textInputLayout.getHint();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null && textInputLayout.T() && super.getHint() == null && ax.p202m7.i.d()) {
            setHint(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    @Override // androidx.appcompat.widget.C0602l, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (inputConnectionOnCreateInputConnection != null && editorInfo.hintText == null) {
            editorInfo.hintText = getHintFromLayout();
        }
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (Build.VERSION.SDK_INT >= 23 || textInputLayout == null) {
            return;
        }
        accessibilityNodeInfo.setText(e(textInputLayout));
    }

    @Override // android.view.View
    public boolean requestRectangleOnScreen(Rect rect) {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!f(textInputLayout) || rect == null) {
            return super.requestRectangleOnScreen(rect);
        }
        this.p0.set(rect.left, rect.top, rect.right, rect.bottom + (textInputLayout.getHeight() - getHeight()));
        return super.requestRectangleOnScreen(this.p0);
    }

    public void setTextInputLayoutFocusedRectEnabled(boolean z) {
        this.q0 = z;
    }
}
