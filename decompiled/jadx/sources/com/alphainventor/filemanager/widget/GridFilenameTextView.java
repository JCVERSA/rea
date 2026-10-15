package com.alphainventor.filemanager.widget;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.x;
import ax.p086f3.d0;

/* JADX INFO: loaded from: classes.dex */
public class GridFilenameTextView extends x {
    private SpannableString q0;
    private b r0;
    private boolean s0;
    private boolean t0;
    private boolean u0;
    private CharSequence v0;
    private String w0;
    private boolean x0;

    private class b {
        private b() {
        }

        protected CharSequence a(CharSequence charSequence, String str) {
            int length;
            Layout layoutB = b(charSequence);
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf < 0) {
                return charSequence;
            }
            int lineEnd = layoutB.getLineEnd(GridFilenameTextView.this.getMaxLines() - 1);
            int length2 = charSequence.length();
            int length3 = (length2 - lineEnd) + 1;
            if (length3 < GridFilenameTextView.this.q0.length()) {
                length3 = GridFilenameTextView.this.q0.length();
            }
            String strF = d0.f(str);
            if (!TextUtils.isEmpty(strF) && (length = length2 - (length3 + strF.length())) >= 0) {
                String strTrim = TextUtils.substring(charSequence, 0, length).trim();
                String strTrim2 = TextUtils.substring(charSequence, iLastIndexOf + 1, length2).trim();
                while (true) {
                    CharSequence charSequenceConcat = TextUtils.concat(strTrim, GridFilenameTextView.this.q0, strTrim2);
                    if (c(charSequenceConcat)) {
                        if (!(charSequence instanceof Spanned)) {
                            return charSequenceConcat;
                        }
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(strTrim);
                        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(strTrim2);
                        Spanned spanned = (Spanned) charSequence;
                        TextUtils.copySpansFrom(spanned, 0, strTrim.length(), null, spannableStringBuilder, 0);
                        TextUtils.copySpansFrom(spanned, length2 - strTrim2.length(), length2, null, spannableStringBuilder2, 0);
                        return TextUtils.concat(spannableStringBuilder, GridFilenameTextView.this.q0, spannableStringBuilder2);
                    }
                    int length4 = strTrim.length();
                    if (length4 != 0) {
                        strTrim = strTrim.substring(0, length4 - 1).trim();
                    }
                }
            }
            return charSequence;
        }

        protected Layout b(CharSequence charSequence) {
            return new StaticLayout(charSequence, GridFilenameTextView.this.getPaint(), (GridFilenameTextView.this.getWidth() - GridFilenameTextView.this.getCompoundPaddingLeft()) - GridFilenameTextView.this.getCompoundPaddingRight(), Layout.Alignment.ALIGN_NORMAL, GridFilenameTextView.this.getLineSpacingMultiplier(), GridFilenameTextView.this.getLineSpacingExtra(), false);
        }

        public boolean c(CharSequence charSequence) {
            return b(charSequence).getLineCount() <= GridFilenameTextView.this.getMaxLines();
        }

        public CharSequence d(CharSequence charSequence, String str) {
            return !c(charSequence) ? a(charSequence, str) : charSequence;
        }
    }

    public GridFilenameTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public GridFilenameTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q0 = new SpannableString("...");
        this.t0 = true;
        this.r0 = new b();
    }

    private boolean D(String str) {
        int iIndexOf;
        return (str == null || str.length() == 0 || (iIndexOf = str.indexOf(46)) == 0 || iIndexOf < 0) ? false : true;
    }

    private void E() {
        boolean z;
        if (!D(this.w0)) {
            this.t0 = false;
            this.s0 = false;
            return;
        }
        int maxLines = getMaxLines();
        CharSequence charSequenceD = this.v0;
        if (maxLines != -1) {
            charSequenceD = this.r0.d(charSequenceD, this.w0);
        }
        if (charSequenceD.equals(this.v0)) {
            z = false;
        } else {
            z = true;
            this.u0 = true;
            try {
                setText(charSequenceD);
                this.u0 = false;
            } catch (Throwable th) {
                this.u0 = false;
                throw th;
            }
        }
        this.t0 = false;
        this.s0 = z;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.x0 && this.t0) {
            E();
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (!this.u0) {
            this.v0 = charSequence;
            this.w0 = charSequence != null ? charSequence.toString() : null;
            this.t0 = true;
        }
        super.setText(charSequence, bufferType);
    }

    public void setUseFilenameEllipsize(boolean z) {
        this.x0 = z;
    }
}
