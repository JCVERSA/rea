package com.alphainventor.filemanager.texteditor;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.SuggestionSpan;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ax.p006a3.J;
import ax.p006a3.Q;
import ax.p035c0.C1867s;
import ax.p054d3.C1909n;
import ax.p054d3.E;
import ax.p070e3.C1957a;
import ax.p070e3.C1966j;
import ax.p086f3.C2017x;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p406z3.B;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import ax.p406z3.z;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC6238m;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6233h;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.provider.MyFileProvider;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class TextEditorActivity extends ax.V2.c implements C1909n.c {
    private static final Logger J0 = ax.U2.g.a(TextEditorActivity.class);
    private static boolean K0 = false;
    private static final int L0;
    private static final int M0;
    private static Pattern N0;
    private ScaleGestureDetector A0;
    private View.OnTouchListener B0;
    private float C0;
    private int D0;
    private n E0;
    private androidx.fragment.app.e F0;
    private String G0;
    private String H0;
    private int I0;
    private RecyclerView X;
    private i Y;
    private RecyclerView.p Z;
    private ProgressBar k0;
    private boolean l0;
    private h m0;
    private String n0;
    private String o0;
    private ArrayList<j> p0;
    private Toolbar q;
    private Charset q0;
    private String r0;
    private int t0;
    private int v0;
    C1867s w0;
    private boolean x0;
    private C6240o y0;
    private ax.p038c3.k z0;
    private int s0 = -1;
    private int u0 = -1;

    class a extends LinearLayoutManager {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public boolean g1(RecyclerView recyclerView, RecyclerView.B b, View view, View view2) {
            return true;
        }
    }

    class b implements Runnable {
        final /* synthetic */ EditText q;

        b(EditText editText) {
            this.q = editText;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.q.hasFocus()) {
                int selectionStart = this.q.getSelectionStart();
                Layout layout = this.q.getLayout();
                if (layout == null || selectionStart == -1) {
                    return;
                }
                int lineForOffset = layout.getLineForOffset(selectionStart);
                int lineBaseline = layout.getLineBaseline(lineForOffset);
                int lineAscent = layout.getLineAscent(lineForOffset);
                int primaryHorizontal = (int) layout.getPrimaryHorizontal(selectionStart);
                int i = lineBaseline + lineAscent;
                this.q.requestRectangleOnScreen(new Rect(primaryHorizontal, i, primaryHorizontal, i), false);
            }
        }
    }

    class c extends GestureDetector.SimpleOnGestureListener {

        class a implements Runnable {
            final /* synthetic */ View q;

            a(View view) {
                this.q = view;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextEditorActivity.this.k()) {
                    return;
                }
                z.D(TextEditorActivity.this, this.q);
            }
        }

        c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (TextEditorActivity.this.Z.P() > 0) {
                View viewO = TextEditorActivity.this.Z.O(TextEditorActivity.this.Z.P() - 1);
                if (!TextEditorActivity.this.l0) {
                    viewO.requestFocus();
                    viewO.postDelayed(new a(viewO), 100L);
                }
            }
            return true;
        }
    }

    class d implements View.OnTouchListener {
        d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            TextEditorActivity.this.A0.onTouchEvent(motionEvent);
            return false;
        }
    }

    class e implements RecyclerView.t {
        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
            TextEditorActivity.this.w0.a(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
            if (TextEditorActivity.this.X.X(motionEvent.getX(), motionEvent.getY()) != null) {
                return false;
            }
            TextEditorActivity.this.w0.a(motionEvent);
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void e(boolean z) {
        }
    }

    class f extends ax.p182l3.c {
        f() {
        }

        @Override // ax.p182l3.c
        public void a(View view) {
            TextEditorActivity.this.onBackPressed();
        }
    }

    public static class g extends Editable.Factory {

        class a extends SpannableStringBuilder {
            a(CharSequence charSequence) {
                super(charSequence);
            }

            @Override // android.text.SpannableStringBuilder, android.text.Editable
            public SpannableStringBuilder replace(int i, int i2, CharSequence charSequence, int i3, int i4) {
                return (i == 0 && i3 == 0 && i2 == i4 && i2 == length() && length() == charSequence.length() && charSequence.toString().equals(toString()) && (charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(i3, i4, SuggestionSpan.class) < i4) ? this : super.replace(i, i2, charSequence, i3, i4);
            }
        }

        @Override // android.text.Editable.Factory
        public Editable newEditable(CharSequence charSequence) {
            return new a(charSequence);
        }
    }

    enum h {
        UNDEFINED,
        LOADED,
        MODIFIED,
        SAVED
    }

    class i extends RecyclerView.h<a> {
        private ArrayList<j> d;

        class a extends RecyclerView.F {
            public EditText u;
            public boolean v;
            public boolean w;
            public boolean x;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.texteditor.TextEditorActivity$i$a$a, reason: collision with other inner class name */
            class C0550a implements TextView.OnEditorActionListener {
                final /* synthetic */ i a;

                C0550a(i iVar) {
                    this.a = iVar;
                }

                @Override // android.widget.TextView.OnEditorActionListener
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if ((i != 6 && i != 5) || keyEvent != null) {
                        return false;
                    }
                    int iMax = Math.max(a.this.u.getSelectionStart(), 0);
                    int iMax2 = Math.max(a.this.u.getSelectionEnd(), 0);
                    a.this.u.getText().replace(Math.min(iMax, iMax2), Math.max(iMax, iMax2), "\n", 0, 1);
                    return true;
                }
            }

            class b implements View.OnKeyListener {
                final /* synthetic */ i q;

                b(i iVar) {
                    this.q = iVar;
                }

                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (i == 47 && keyEvent.isCtrlPressed()) {
                        TextEditorActivity.this.J0(false);
                    }
                    if (i == 67 && keyEvent.getAction() == 0 && a.this.u.getSelectionStart() == 0) {
                        int iK0 = TextEditorActivity.this.X.k0(a.this.u);
                        if (iK0 >= 0 && iK0 < i.this.d.size() && iK0 > 0) {
                            TextEditorActivity.this.R0(h.MODIFIED);
                            int i2 = iK0 - 1;
                            TextEditorActivity.this.u0 = i2;
                            i iVar = i.this;
                            TextEditorActivity.this.v0 = ((j) iVar.d.get(i2)).a.length();
                            StringBuilder sb = new StringBuilder();
                            j jVar = (j) i.this.d.get(i2);
                            sb.append(jVar.a);
                            sb.append(((j) i.this.d.get(iK0)).a);
                            jVar.a = sb.toString();
                            i.this.d.remove(iK0);
                            TextEditorActivity.this.Y.s(i2);
                            TextEditorActivity.this.Y.z(iK0);
                        }
                        return true;
                    }
                    if ((!(i == 112) || !(keyEvent.getAction() == 0)) || a.this.u.getSelectionStart() != a.this.u.length()) {
                        return false;
                    }
                    int iK1 = TextEditorActivity.this.X.k0(a.this.u);
                    if (iK1 >= 0 && iK1 < i.this.d.size() && iK1 < i.this.d.size() - 1) {
                        TextEditorActivity.this.R0(h.MODIFIED);
                        TextEditorActivity.this.u0 = iK1;
                        i iVar2 = i.this;
                        TextEditorActivity.this.v0 = ((j) iVar2.d.get(iK1)).a.length();
                        StringBuilder sb2 = new StringBuilder();
                        j jVar2 = (j) i.this.d.get(iK1);
                        sb2.append(jVar2.a);
                        int i3 = iK1 + 1;
                        sb2.append(((j) i.this.d.get(i3)).a);
                        jVar2.a = sb2.toString();
                        i.this.d.remove(i3);
                        TextEditorActivity.this.Y.s(iK1);
                        TextEditorActivity.this.Y.z(i3);
                    }
                    return true;
                }
            }

            class c implements TextWatcher {
                final /* synthetic */ i q;

                c(i iVar) {
                    this.q = iVar;
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    a aVar = a.this;
                    if (aVar.v || aVar.w) {
                        aVar.v = false;
                        aVar.x = false;
                        return;
                    }
                    TextEditorActivity.this.R0(h.MODIFIED);
                    int iM0 = TextEditorActivity.this.Z.m0(a.this.u);
                    String string = editable.toString();
                    if (iM0 < 0) {
                        ax.p302sb.c.h().g().b("TEXTEDITOR IndexOutOfBound -1").i();
                    } else if (iM0 < i.this.d.size()) {
                        ((j) i.this.d.get(iM0)).a = string;
                    } else {
                        ax.p302sb.c.h().g().b("TEXTEDITOR IndexOutOfBound").i();
                    }
                    if (!TextEditorActivity.this.x0) {
                        if (string.contains("\n")) {
                            int iIndexOf = string.indexOf("\n");
                            String strSubstring = string.substring(0, iIndexOf);
                            String strSubstring2 = string.substring(iIndexOf + 1);
                            a aVar2 = a.this;
                            if (aVar2.x) {
                                ((j) i.this.d.get(iM0)).a = strSubstring;
                                int i = iM0 + 1;
                                ((j) i.this.d.get(i)).a = strSubstring2;
                                TextEditorActivity.this.Y.s(iM0);
                                TextEditorActivity.this.Y.s(i);
                                return;
                            }
                            ((j) i.this.d.get(iM0)).a = strSubstring;
                            int i2 = iM0 + 1;
                            i.this.d.add(i2, TextEditorActivity.this.new j(strSubstring2));
                            TextEditorActivity.this.Y.s(iM0);
                            TextEditorActivity.this.Y.u(i2);
                            TextEditorActivity.this.s0 = i2;
                            TextEditorActivity.this.t0 = 0;
                            TextEditorActivity.this.X.B1(0, a.this.u.getLineHeight());
                            a.this.x = true;
                            return;
                        }
                        return;
                    }
                    if (TextEditorActivity.H0(string, '\n') >= TextEditorActivity.M0 - 1) {
                        int selectionStart = a.this.u.getSelectionStart();
                        int iK0 = TextEditorActivity.K0(string, '\n', TextEditorActivity.L0);
                        String strSubstring3 = string.substring(0, iK0 > 0 && string.charAt(iK0 + (-1)) == '\r' ? iK0 - 1 : iK0);
                        String strSubstring4 = string.substring(iK0 + 1);
                        a aVar3 = a.this;
                        if (aVar3.x) {
                            ((j) i.this.d.get(iM0)).a = strSubstring3;
                            int i3 = iM0 + 1;
                            ((j) i.this.d.get(i3)).a = strSubstring4;
                            TextEditorActivity.this.Y.s(iM0);
                            TextEditorActivity.this.Y.s(i3);
                            return;
                        }
                        ((j) i.this.d.get(iM0)).a = strSubstring3;
                        int i4 = iM0 + 1;
                        i.this.d.add(i4, TextEditorActivity.this.new j(strSubstring4));
                        TextEditorActivity.this.Y.s(iM0);
                        TextEditorActivity.this.Y.u(i4);
                        if (selectionStart > strSubstring3.length()) {
                            TextEditorActivity.this.s0 = i4;
                            TextEditorActivity.this.t0 = (selectionStart - iK0) - 1;
                        }
                        TextEditorActivity.this.X.B1(0, a.this.u.getLineHeight());
                        a.this.x = true;
                    }
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }
            }

            public a(View view) {
                super(view);
                this.u = (EditText) view;
                if (J.e()) {
                    this.u.setEditableFactory(new g());
                }
                this.u.setOnTouchListener(TextEditorActivity.this.B0);
                try {
                    this.u.setTypeface(ax.S.h.g(view.getContext(), R.font.robotomono_regular));
                } catch (Exception e) {
                    ax.p302sb.c.h().g().b("FONT LOAD ERROR").m(e).i();
                }
                if (Q.r()) {
                    this.u.setOnEditorActionListener(new C0550a(i.this));
                }
                this.u.setOnKeyListener(new b(i.this));
                this.u.addTextChangedListener(new c(i.this));
            }

            public void N(int i) {
                this.v = true;
                this.w = true;
                boolean z = this.x;
                this.u.setTextKeepState(((j) i.this.d.get(i)).a);
                if (!z) {
                    EditText editText = this.u;
                    editText.setSelection(editText.length());
                }
                this.w = false;
            }

            public void O(float f) {
                this.u.setTextSize(f);
            }
        }

        public i(ArrayList<j> arrayList) {
            this.d = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.h
        /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
        public void B(a aVar, int i) {
            aVar.N(i);
            aVar.O(TextEditorActivity.this.D0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.h
        /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
        public a D(ViewGroup viewGroup, int i) {
            return new a(TextEditorActivity.this.l0 ? LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_texteditor_line_readonly, viewGroup, false) : LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_texteditor_line, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.h
        public int l() {
            return this.d.size();
        }
    }

    class j {
        String a;

        j(String str) {
            this.a = str;
        }
    }

    class k implements RecyclerView.r {
        k() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void a(View view) {
            if (TextEditorActivity.this.u0 != -1) {
                EditText editText = (EditText) TextEditorActivity.this.Z.I(TextEditorActivity.this.u0);
                if (editText == null) {
                    ax.p302sb.c.h().d("INVALID TEXTEDITOR SELECTION NULL").h(Integer.valueOf(TextEditorActivity.this.v0)).i();
                    TextEditorActivity.this.u0 = -1;
                    return;
                }
                if (!TextEditorActivity.this.l0) {
                    editText.requestFocus();
                    z.D(TextEditorActivity.this, editText);
                }
                if (TextEditorActivity.this.v0 <= editText.getText().length()) {
                    editText.setSelection(TextEditorActivity.this.v0);
                } else {
                    ax.p302sb.c.h().d("INVALID TEXTEDITOR SELECTION").h(TextEditorActivity.this.v0 + " > " + editText.getText().length()).i();
                }
                TextEditorActivity.this.u0 = -1;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void d(View view) {
            if (view == null || TextEditorActivity.this.s0 != TextEditorActivity.this.Z.m0(view)) {
                return;
            }
            EditText editText = (EditText) view;
            if (!TextEditorActivity.this.l0) {
                editText.requestFocus();
                z.D(TextEditorActivity.this, editText);
            }
            if (editText.getText().length() > TextEditorActivity.this.t0) {
                editText.setSelection(TextEditorActivity.this.t0);
            }
            TextEditorActivity.this.s0 = -1;
        }
    }

    private class l extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private l() {
        }

        /* synthetic */ l(TextEditorActivity textEditorActivity, a aVar) {
            this();
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            TextEditorActivity.A0(TextEditorActivity.this, scaleGestureDetector.getScaleFactor());
            TextEditorActivity textEditorActivity = TextEditorActivity.this;
            textEditorActivity.C0 = Math.max(0.6f, Math.min(textEditorActivity.C0, 4.0f));
            TextEditorActivity textEditorActivity2 = TextEditorActivity.this;
            textEditorActivity2.D0 = (int) (textEditorActivity2.C0 * 14.0f);
            if (TextEditorActivity.this.Y == null) {
                return true;
            }
            TextEditorActivity.this.Y.r();
            return true;
        }
    }

    class m extends q<Void, Void, Integer> {
        private boolean h;

        m() {
            super(q.e.HIGHER);
        }

        private boolean A(int i) {
            return i == 0;
        }

        private int C(C6240o c6240o, AbstractC6239n abstractC6239n) throws Throwable {
            TextEditorActivity.this.p0 = new ArrayList();
            ax.p342v3.a aVar = null;
            try {
                try {
                    ax.p342v3.a aVar2 = new ax.p342v3.a(new InputStreamReader(c6240o.h1(abstractC6239n, 0L), TextEditorActivity.this.q0));
                    try {
                        try {
                            int i = J.h() ? 8000 : 0;
                            StringBuffer stringBuffer = new StringBuffer();
                            boolean z = false;
                            int i2 = 0;
                            int i3 = 0;
                            boolean zA = false;
                            while (true) {
                                String strI = aVar2.i();
                                if (strI == null) {
                                    if (z) {
                                        TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(stringBuffer.toString()));
                                    }
                                    if (zA) {
                                        TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    if (TextEditorActivity.this.p0.size() == 0) {
                                        TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused) {
                                    }
                                    return 0;
                                }
                                zA = aVar2.a();
                                if (strI.length() > 200000) {
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused2) {
                                    }
                                    return -3;
                                }
                                for (int i4 = 0; i4 < strI.length(); i4++) {
                                    if (A(strI.charAt(i4)) && (i3 = i3 + 1) >= 10) {
                                        try {
                                            aVar2.close();
                                        } catch (IOException unused3) {
                                        }
                                        return -3;
                                    }
                                }
                                z = true;
                                if (i2 == 0) {
                                    stringBuffer.setLength(0);
                                    stringBuffer.append(strI);
                                } else if (i2 >= TextEditorActivity.L0 - 1 || (i != 0 && stringBuffer.length() + strI.length() > i)) {
                                    TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(stringBuffer.toString()));
                                    stringBuffer.setLength(0);
                                    stringBuffer.append(strI);
                                    i2 = 0;
                                } else {
                                    stringBuffer.append(TextEditorActivity.this.r0);
                                    stringBuffer.append(strI);
                                }
                                i2++;
                            }
                        } catch (C1966j | IOException unused4) {
                            aVar = aVar2;
                            if (aVar == null) {
                                return -10;
                            }
                            try {
                                aVar.close();
                                return -10;
                            } catch (IOException unused5) {
                                return -10;
                            }
                        }
                    } catch (OutOfMemoryError e) {
                        e = e;
                        aVar = aVar2;
                        ax.p302sb.c.h().d("TEXT EDITOR OOM").h(e.getMessage()).i();
                        if (aVar == null) {
                            return -1;
                        }
                        try {
                            aVar.close();
                            return -1;
                        } catch (IOException unused6) {
                            return -1;
                        }
                    } catch (Throwable th) {
                        th = th;
                        aVar = aVar2;
                        if (aVar != null) {
                            try {
                                aVar.close();
                            } catch (IOException unused7) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (C1966j | IOException unused8) {
            } catch (OutOfMemoryError e2) {
                e = e2;
            }
        }

        private int D(C6240o c6240o, AbstractC6239n abstractC6239n) throws Throwable {
            TextEditorActivity.this.p0 = new ArrayList();
            ax.p342v3.a aVar = null;
            try {
                try {
                    ax.p342v3.a aVar2 = new ax.p342v3.a(new InputStreamReader(c6240o.h1(abstractC6239n, 0L), TextEditorActivity.this.q0));
                    boolean zA = false;
                    int i = 0;
                    while (true) {
                        try {
                            try {
                                String strI = aVar2.i();
                                if (strI == null) {
                                    if (zA) {
                                        TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    if (TextEditorActivity.this.p0.size() == 0) {
                                        TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused) {
                                    }
                                    return 0;
                                }
                                zA = aVar2.a();
                                if (strI.length() > 200000) {
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused2) {
                                    }
                                    return -3;
                                }
                                for (int i2 = 0; i2 < strI.length(); i2++) {
                                    if (A(strI.charAt(i2)) && (i = i + 1) >= 10) {
                                        try {
                                            aVar2.close();
                                        } catch (IOException unused3) {
                                        }
                                        return -3;
                                    }
                                }
                                TextEditorActivity.this.p0.add(TextEditorActivity.this.new j(strI));
                            } catch (C1966j | IOException unused4) {
                                aVar = aVar2;
                                if (aVar == null) {
                                    return -10;
                                }
                                try {
                                    aVar.close();
                                    return -10;
                                } catch (IOException unused5) {
                                    return -10;
                                }
                            }
                        } catch (OutOfMemoryError e) {
                            e = e;
                            aVar = aVar2;
                            ax.p302sb.c.h().d("TEXT EDITOR OOM").h(e.getMessage()).i();
                            if (aVar == null) {
                                return -1;
                            }
                            try {
                                aVar.close();
                                return -1;
                            } catch (IOException unused6) {
                                return -1;
                            }
                        } catch (Throwable th) {
                            th = th;
                            aVar = aVar2;
                            if (aVar != null) {
                                try {
                                    aVar.close();
                                } catch (IOException unused7) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (C1966j | IOException unused8) {
            } catch (OutOfMemoryError e2) {
                e = e2;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            if (!this.h) {
                TextEditorActivity.this.y0.S(false);
                this.h = true;
            }
            TextEditorActivity.this.k0.setVisibility(8);
            if (num.intValue() == 0) {
                TextEditorActivity textEditorActivity = TextEditorActivity.this;
                textEditorActivity.Y = textEditorActivity.new i(textEditorActivity.p0);
                TextEditorActivity.this.X.setAdapter(TextEditorActivity.this.Y);
                TextEditorActivity.this.R0(h.LOADED);
                if (TextEditorActivity.this.G0 == null || TextEditorActivity.this.H0 == null) {
                    return;
                }
                ax.U2.a.k().q("text_editor_open").b("loc", TextEditorActivity.this.G0).b("ext", TextEditorActivity.this.H0).b("range", ax.U2.a.f.a(TextEditorActivity.this.I0)).c();
                return;
            }
            if (num.intValue() == -1) {
                Toast.makeText(TextEditorActivity.this, R.string.error_too_large_to_open, 1).show();
                TextEditorActivity.this.finish();
            } else if (num.intValue() == -3) {
                Toast.makeText(TextEditorActivity.this, R.string.error_not_text_file, 1).show();
                TextEditorActivity.this.finish();
            } else {
                Toast.makeText(TextEditorActivity.this, R.string.error_file_load, 1).show();
                TextEditorActivity.this.finish();
            }
        }

        @Override // ax.p406z3.q
        protected void o() {
            super.o();
            if (this.h) {
                return;
            }
            TextEditorActivity.this.y0.S(false);
            this.h = true;
        }

        @Override // ax.p406z3.q
        protected void r() {
            TextEditorActivity.this.k0.setVisibility(0);
            TextEditorActivity.this.y0.V();
        }

        /* JADX WARN: Code duplicated, block: B:43:0x0081  */
        /* JADX WARN: Code duplicated, block: B:45:0x008c A[Catch: IOException -> 0x008f, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x008f, blocks: (B:29:0x0069, B:45:0x008c), top: B:59:0x0001 }] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v12, types: [int] */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v15 */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r5v19, types: [ax.Ge.c] */
        void w(C6240o c6240o, AbstractC6239n abstractC6239n) throws Throwable {
            ?? C;
            ?? r0 = 0;
            r0 = 0;
            r0 = 0;
            r0 = 0;
            r0 = 0;
            try {
                try {
                    try {
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(c6240o.h1(abstractC6239n, 0L));
                        try {
                            ?? cVar = new ax.Ge.c(null);
                            byte[] bArr = new byte[4096];
                            do {
                                C = bufferedInputStream.read(bArr);
                                if (C == -1) {
                                    break;
                                }
                                cVar.d(bArr, 0, C);
                                C = cVar.c();
                            } while (C == 0);
                            cVar.a();
                            String strC = cVar.c();
                            if (strC != null) {
                                if ("US-ASCII".equals(strC)) {
                                    TextEditorActivity.this.q0 = Charset.defaultCharset();
                                } else {
                                    TextEditorActivity.this.q0 = Charset.forName(strC);
                                }
                            }
                            if (TextEditorActivity.this.q0 == null) {
                                TextEditorActivity.this.q0 = Charset.defaultCharset();
                            }
                            bufferedInputStream.close();
                            r0 = C;
                        } catch (C1966j e) {
                            e = e;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.q0 == null) {
                                TextEditorActivity.this.q0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.J0.fine("DETECTED ENCODING : " + TextEditorActivity.this.q0.name());
                        } catch (IOException e2) {
                            e = e2;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.q0 == null) {
                                TextEditorActivity.this.q0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.J0.fine("DETECTED ENCODING : " + TextEditorActivity.this.q0.name());
                        } catch (IllegalCharsetNameException e3) {
                            e = e3;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.q0 == null) {
                                TextEditorActivity.this.q0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.J0.fine("DETECTED ENCODING : " + TextEditorActivity.this.q0.name());
                        } catch (UnsupportedCharsetException e4) {
                            e = e4;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.q0 == null) {
                                TextEditorActivity.this.q0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.J0.fine("DETECTED ENCODING : " + TextEditorActivity.this.q0.name());
                        } catch (Throwable th) {
                            th = th;
                            r0 = bufferedInputStream;
                            if (TextEditorActivity.this.q0 == null) {
                                TextEditorActivity.this.q0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                try {
                                    r0.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                    } catch (IOException unused2) {
                    }
                } catch (C1966j e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                } catch (IllegalCharsetNameException e7) {
                    e = e7;
                } catch (UnsupportedCharsetException e8) {
                    e = e8;
                }
                TextEditorActivity.J0.fine("DETECTED ENCODING : " + TextEditorActivity.this.q0.name());
            } catch (Throwable th2) {
                th = th2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:35:0x006e  */
        /* JADX WARN: Code duplicated, block: B:37:0x0075 A[Catch: IOException -> 0x005c, TRY_ENTER, TRY_LEAVE, TryCatch #7 {IOException -> 0x005c, blocks: (B:24:0x0058, B:37:0x0075), top: B:56:0x0005 }] */
        /* JADX WARN: Code duplicated, block: B:40:0x0092  */
        /* JADX WARN: Code duplicated, block: B:41:0x0095  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v15 */
        /* JADX WARN: Type inference failed for: r2v16 */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v21 */
        /* JADX WARN: Type inference failed for: r2v3, types: [ax.v3.a] */
        /* JADX WARN: Type inference failed for: r2v4 */
        /* JADX WARN: Type inference failed for: r2v5, types: [ax.v3.a] */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v7 */
        /* JADX WARN: Type inference failed for: r2v8 */
        void x(C6240o c6240o, AbstractC6239n abstractC6239n) throws Throwable {
            String str;
            String str2;
            int i;
            String str3 = "\n";
            ?? r2 = 0;
            r2 = 0;
            r2 = 0;
            r2 = 0;
            try {
                try {
                    try {
                        ax.p342v3.a aVar = new ax.p342v3.a(new InputStreamReader(c6240o.h1(abstractC6239n, 0L), TextEditorActivity.this.q0));
                        try {
                            char[] cArr = new char[2048];
                            while (true) {
                                int i2 = aVar.read(cArr);
                                int i3 = -1;
                                if (i2 != -1) {
                                    String str4 = new String(cArr, 0, i2);
                                    int iI0 = TextEditorActivity.I0(str4, "\n");
                                    int iI1 = TextEditorActivity.I0(str4, "\r\n");
                                    if (iI0 != 0) {
                                        i3 = iI1 * 2;
                                        if (iI0 <= i3) {
                                            str2 = "\r\n";
                                            i = i3;
                                            break;
                                        }
                                        break;
                                    }
                                }
                                str2 = "\n";
                                i = i3;
                                break;
                            }
                            TextEditorActivity.this.r0 = str2;
                            if (TextEditorActivity.this.r0 == null) {
                                TextEditorActivity.this.r0 = "\n";
                            }
                            aVar.close();
                            r2 = i;
                        } catch (C1966j e) {
                            e = e;
                            r2 = aVar;
                            e.printStackTrace();
                            if (TextEditorActivity.this.r0 == null) {
                                TextEditorActivity.this.r0 = "\n";
                            }
                            if (r2 != 0) {
                                r2.close();
                                r2 = r2;
                            }
                            Logger logger = TextEditorActivity.J0;
                            StringBuilder sb = new StringBuilder();
                            sb.append("DETECTED NEWLINE : ");
                            str3 = TextEditorActivity.this.r0;
                            if ("\r\n".equals(str3)) {
                                str = "CRLF";
                            } else {
                                str = "LF";
                            }
                            sb.append(str);
                            logger.fine(sb.toString());
                        } catch (IOException e2) {
                            e = e2;
                            r2 = aVar;
                            e.printStackTrace();
                            if (TextEditorActivity.this.r0 == null) {
                                TextEditorActivity.this.r0 = "\n";
                            }
                            if (r2 != 0) {
                                r2.close();
                                r2 = r2;
                            }
                            Logger logger2 = TextEditorActivity.J0;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("DETECTED NEWLINE : ");
                            str3 = TextEditorActivity.this.r0;
                            if ("\r\n".equals(str3)) {
                                str = "CRLF";
                            } else {
                                str = "LF";
                            }
                            sb2.append(str);
                            logger2.fine(sb2.toString());
                        } catch (Throwable th) {
                            th = th;
                            r2 = aVar;
                            if (TextEditorActivity.this.r0 == null) {
                                TextEditorActivity.this.r0 = str3;
                            }
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (C1966j e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException unused2) {
            }
            Logger logger3 = TextEditorActivity.J0;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("DETECTED NEWLINE : ");
            str3 = TextEditorActivity.this.r0;
            if ("\r\n".equals(str3)) {
                str = "CRLF";
            } else {
                str = "LF";
            }
            sb3.append(str);
            logger3.fine(sb3.toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public Integer g(Void... voidArr) throws Throwable {
            try {
                AbstractC6239n abstractC6239nA1 = TextEditorActivity.this.y0.a1(TextEditorActivity.this.n0);
                if (z(abstractC6239nA1)) {
                    return -1;
                }
                w(TextEditorActivity.this.y0, abstractC6239nA1);
                x(TextEditorActivity.this.y0, abstractC6239nA1);
                if (TextEditorActivity.K0) {
                    TextEditorActivity.this.x0 = true;
                    return Integer.valueOf(C(TextEditorActivity.this.y0, abstractC6239nA1));
                }
                int iD = D(TextEditorActivity.this.y0, abstractC6239nA1);
                TextEditorActivity textEditorActivity = TextEditorActivity.this;
                textEditorActivity.I0 = textEditorActivity.p0.size();
                if (iD != 0 || TextEditorActivity.this.p0.size() >= 1000) {
                    TextEditorActivity.this.x0 = false;
                    return Integer.valueOf(iD);
                }
                TextEditorActivity.this.x0 = true;
                return Integer.valueOf(C(TextEditorActivity.this.y0, abstractC6239nA1));
            } catch (C1966j unused) {
                return -10;
            }
        }

        boolean z(AbstractC6239n abstractC6239n) {
            if (abstractC6239n.p() < 102400) {
                return false;
            }
            long jP = abstractC6239n.p() / 1048576;
            if (jP <= 30 && jP <= (((ActivityManager) TextEditorActivity.this.getApplicationContext().getSystemService("activity")).getMemoryClass() * 4) / 10) {
                return jP > ((((Runtime.getRuntime().maxMemory() - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) - Debug.getNativeHeapAllocatedSize()) / 1048576) * 4) / 10;
            }
            return true;
        }
    }

    class n extends q<Void, Void, Boolean> {
        private boolean h;
        boolean i;
        boolean j;
        Throwable k;

        class a implements ax.p182l3.i {
            a() {
            }

            @Override // ax.p182l3.i
            public void a(long j, long j2) {
            }
        }

        n(boolean z) {
            super(q.e.HIGHER);
            this.i = z;
        }

        private void A() throws Throwable {
            OutputStream outputStreamC;
            String strReplaceAll;
            BufferedWriter bufferedWriter = null;
            try {
                AbstractC6238m abstractC6238mU = TextEditorActivity.this.y0.u();
                if (abstractC6238mU instanceof C6248x) {
                    outputStreamC = ((C6248x) abstractC6238mU).c(TextEditorActivity.this.n0, false);
                } else if (abstractC6238mU instanceof C6233h) {
                    outputStreamC = ((C6233h) abstractC6238mU).c(TextEditorActivity.this.n0, false);
                } else {
                    C2461b.f();
                    outputStreamC = null;
                }
                boolean zEquals = "\r\n".equals(TextEditorActivity.this.r0);
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStreamC, TextEditorActivity.this.q0));
                try {
                    if (!x()) {
                        for (int i = 0; i < TextEditorActivity.this.p0.size(); i++) {
                            j jVar = (j) TextEditorActivity.this.p0.get(i);
                            if (!zEquals || jVar.a == null) {
                                strReplaceAll = jVar.a;
                            } else {
                                if (TextEditorActivity.N0 == null) {
                                    Pattern unused = TextEditorActivity.N0 = Pattern.compile("(?<!\r)\n");
                                }
                                strReplaceAll = TextEditorActivity.N0.matcher(jVar.a).replaceAll("\r\n");
                            }
                            if (i == TextEditorActivity.this.p0.size() - 1) {
                                bufferedWriter2.write(strReplaceAll);
                            } else {
                                bufferedWriter2.write(strReplaceAll + TextEditorActivity.this.r0);
                            }
                        }
                    }
                    bufferedWriter2.flush();
                    try {
                        bufferedWriter2.close();
                    } catch (IOException unused2) {
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        @Override // ax.p406z3.q
        protected void o() {
            if (!this.h) {
                TextEditorActivity.this.y0.S(true);
                this.h = true;
            }
            if (this.j) {
                return;
            }
            TextEditorActivity.this.L0();
        }

        @Override // ax.p406z3.q
        protected void r() {
            TextEditorActivity.this.y0.V();
            boolean zP0 = TextEditorActivity.this.P0();
            this.j = zP0;
            if (zP0) {
                return;
            }
            TextEditorActivity.this.S0();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:48:0x00b8  */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) throws Throwable {
            IOException iOException;
            n nVar;
            Throwable th;
            try {
                A();
                try {
                    if (TextEditorActivity.this.z0 == null || this.j) {
                        nVar = this;
                    } else {
                        try {
                            C6240o c6240oE = C2017x.e(TextEditorActivity.this.z0.d());
                            if (!c6240oE.a()) {
                                try {
                                    if (!c6240oE.i(10000L)) {
                                        throw new IOException("Not connected to network storage");
                                    }
                                } catch (C1957a e) {
                                    e = e;
                                    th = e;
                                    nVar = this;
                                    nVar.k = th;
                                    throw new IOException(th);
                                } catch (C1966j e2) {
                                    e = e2;
                                    th = e;
                                    nVar = this;
                                    nVar.k = th;
                                    throw new IOException(th);
                                } catch (IOException e3) {
                                    iOException = e3;
                                    if (this.k == null) {
                                        this.k = iOException;
                                    }
                                    return Boolean.FALSE;
                                }
                            }
                            AbstractC6239n abstractC6239nA1 = c6240oE.a1(TextEditorActivity.this.z0.e());
                            AbstractC6239n abstractC6239nA2 = TextEditorActivity.this.y0.a1(TextEditorActivity.this.n0);
                            long jLastModified = abstractC6239nA1.O().lastModified();
                            nVar = this;
                            try {
                                TextEditorActivity.this.y0.b0(abstractC6239nA2, c6240oE, abstractC6239nA1, nVar, new a());
                                ax.p310t3.e.b().j(abstractC6239nA1, jLastModified);
                            } catch (C1957a e4) {
                                e = e4;
                                th = e;
                                nVar.k = th;
                                throw new IOException(th);
                            } catch (C1966j e5) {
                                e = e5;
                                th = e;
                                nVar.k = th;
                                throw new IOException(th);
                            }
                        } catch (C1957a | C1966j e6) {
                            e = e6;
                            nVar = this;
                        }
                    }
                    if (Q.X1() && TextEditorActivity.this.y0.G()) {
                        TextEditorActivity.this.y0.n(null);
                    }
                    return Boolean.TRUE;
                } catch (IOException e7) {
                    e = e7;
                    iOException = e;
                    if (this.k == null) {
                        this.k = iOException;
                    }
                    return Boolean.FALSE;
                }
            } catch (IOException e8) {
                e = e8;
            }
        }

        boolean x() {
            return TextEditorActivity.this.p0.size() == 1 && ((j) TextEditorActivity.this.p0.get(0)).a.length() == 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            String string;
            if (!this.h) {
                TextEditorActivity.this.y0.S(true);
                this.h = true;
            }
            if (!this.j) {
                TextEditorActivity.this.L0();
            }
            if (bool.booleanValue()) {
                TextEditorActivity.this.R0(h.SAVED);
                if (this.i) {
                    TextEditorActivity.this.finish();
                    return;
                }
                return;
            }
            if (!ax.p390y3.k.p(TextEditorActivity.this) || this.k == null) {
                string = TextEditorActivity.this.getString(R.string.error_file_save);
            } else {
                string = TextEditorActivity.this.getString(R.string.error_file_save) + " : " + this.k.getMessage();
            }
            z(string);
        }

        void z(String str) {
            B.T(TextEditorActivity.this.findViewById(android.R.id.content), Html.fromHtml("<font color='red'>" + str + "</font>"), 0).a0();
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT >= 30 ? 1500 : 2500;
        L0 = i2;
        M0 = i2 * 2;
    }

    static /* synthetic */ float A0(TextEditorActivity textEditorActivity, float f2) {
        float f3 = textEditorActivity.C0 * f2;
        textEditorActivity.C0 = f3;
        return f3;
    }

    public static int H0(String str, char c2) {
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i3 = 0;
        while (true) {
            int iIndexOf = str.indexOf(c2, i2);
            if (iIndexOf == -1) {
                return i3;
            }
            i3++;
            i2 = iIndexOf + 1;
        }
    }

    public static int I0(String str, String str2) {
        int length = 0;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            int iIndexOf = str.indexOf(str2, length);
            if (iIndexOf == -1) {
                return i2;
            }
            i2++;
            length = iIndexOf + str2.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0(boolean z) {
        if (k()) {
            return;
        }
        n nVar = new n(z);
        this.E0 = nVar;
        nVar.h(new Void[0]);
    }

    public static int K0(String str, char c2, int i2) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int iIndexOf = str.indexOf(c2, i3);
            if (iIndexOf == -1) {
                return -1;
            }
            i4++;
            if (i4 == i2) {
                return iIndexOf;
            }
            i3 = iIndexOf + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0() {
        try {
            androidx.fragment.app.e eVar = this.F0;
            if (eVar == null || !eVar.h1()) {
                return;
            }
            this.F0.Y2();
        } catch (Exception unused) {
        }
    }

    private void M0() {
        setSupportActionBar(this.q);
        getSupportActionBar().w(true);
        this.q.setNavigationOnClickListener(new f());
    }

    private void O0() {
        this.q = (Toolbar) findViewById(R.id.toolbar);
        this.X = (RecyclerView) findViewById(R.id.recycler);
        this.k0 = (ProgressBar) findViewById(R.id.progress);
        a aVar = new a(this);
        this.Z = aVar;
        this.X.setLayoutManager(aVar);
        this.X.setItemAnimator(null);
        this.X.l(new k());
        N0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean P0() {
        ax.p038c3.k kVar = this.z0;
        if (kVar != null) {
            return ax.U2.f.X(kVar.b());
        }
        return true;
    }

    private void Q0(EditText editText) {
        editText.post(new b(editText));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0(h hVar) {
        this.m0 = hVar;
        int iOrdinal = hVar.ordinal();
        if (iOrdinal == 2) {
            this.q.setTitle("* " + this.o0);
        } else if (iOrdinal == 3) {
            this.q.setTitle(this.o0);
        }
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0() {
        this.F0 = new E();
        B.d0(getSupportFragmentManager(), this.F0, "save_progress", true);
    }

    @Override // ax.p054d3.C1909n.c
    public void G(C1909n c1909n) {
        finish();
    }

    void N0() {
        this.w0 = new C1867s(this, new c());
        this.A0 = new ScaleGestureDetector(this, new l(this, null));
        d dVar = new d();
        this.B0 = dVar;
        this.X.setOnTouchListener(dVar);
        this.X.m(new e());
    }

    @Override // ax.p054d3.C1909n.c
    public void U(C1909n c1909n) {
    }

    @Override // ax.p054d3.C1909n.c
    public void V(C1909n c1909n) {
        ax.U2.a.k().o("menu_text_editor", "text_save").c("ext", d0.j(this.o0)).e();
        J0(true);
    }

    @Override // ax.g3.C0.a
    public boolean isContentClipToPadding(boolean z) {
        return z;
    }

    public boolean k() {
        return getSupportFragmentManager().Q0();
    }

    @Override // ax.p102g3.Y
    public void onApplyWindowsInsets(ax.T.b bVar, boolean z) {
        RecyclerView recyclerView;
        if (z || (recyclerView = this.X) == null) {
            return;
        }
        recyclerView.setClipToPadding(false);
        this.X.setPadding(0, 0, 0, bVar.d);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (k()) {
            return;
        }
        if (this.m0 == h.MODIFIED) {
            C1909n.u3(R.string.confirm, R.string.confirm_save, R.string.menu_save, R.string.menu_dontsave).m3(getSupportFragmentManager(), "dialog");
        } else {
            super.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        String strH;
        String strK;
        String strK2;
        String strH2;
        ax.U2.b.f(this, true);
        super.onCreate(bundle);
        setContentView(R.layout.activity_texteditor);
        O0();
        M0();
        Uri data = getIntent().getData();
        if (data == null) {
            ax.p302sb.c.h().d("TextEditor DataUri == null").i();
            finish();
            return;
        }
        onSetContentView(false);
        this.m0 = h.UNDEFINED;
        J0.fine("TextEditor open : " + data);
        this.l0 = getIntent().getBooleanExtra("read_only", false);
        String strH3 = null;
        if ("file".equals(data.getScheme())) {
            String path = data.getPath();
            this.n0 = path;
            this.y0 = C2017x.g(path);
            this.o0 = d0.h(this.n0);
            String stringExtra = getIntent().getStringExtra("original_file_location_uri");
            if (TextUtils.isEmpty(stringExtra)) {
                strK = null;
            } else {
                try {
                    ax.p038c3.k kVarA = ax.p038c3.k.a(Uri.parse(stringExtra));
                    this.z0 = kVarA;
                    K kD = kVarA.d();
                    K k2 = K.h;
                    if (kD == k2) {
                        this.y0 = C2017x.e(k2);
                    }
                    ax.p038c3.k kVar = this.z0;
                    if (kVar != null) {
                        strH2 = kVar.b().H();
                        try {
                            strH3 = d0.k(this.z0.e());
                        } catch (Exception unused) {
                        }
                        String str = strH3;
                        strH3 = strH2;
                        strK = str;
                    } else {
                        strK = null;
                    }
                } catch (Exception unused2) {
                    strH2 = null;
                }
                if (this.z0 != null && bundle != null) {
                    String string = bundle.getString("file_open_path");
                    long j2 = bundle.getLong("file_open_last_modified", -1L);
                    if (string != null && string.equals(this.z0.e()) && j2 != -1) {
                        ax.p310t3.e.b().i(this.z0.d(), this.z0.e(), j2);
                    }
                }
            }
        } else if (!"content".equals(data.getScheme())) {
            try {
                ax.p038c3.k kVarA2 = ax.p038c3.k.a(data);
                this.n0 = kVarA2.e();
                K kD2 = kVarA2.d();
                this.y0 = C2017x.e(kD2);
                this.o0 = d0.h(this.n0);
                if (kD2 == null || this.n0 == null) {
                    strH = null;
                    strK = null;
                } else {
                    strH = kD2.d().H();
                    strK = d0.k(this.n0);
                }
                if (!this.y0.a()) {
                    if (ax.U2.f.c0(this.y0.A()) || ax.U2.f.m0(this.y0.A())) {
                        this.y0.h(null);
                    }
                    if (!this.y0.a()) {
                        ax.p302sb.c.h().g().d("TextEditor : FileOperator not connected").h("location:" + this.y0.A().H()).i();
                        finish();
                        return;
                    }
                }
                strH3 = strH;
            } catch (IllegalArgumentException unused3) {
                Toast.makeText(this, R.string.error_file_load, 1).show();
                finish();
                return;
            }
        } else {
            if (!MyFileProvider.B(data) && !MyFileProvider.A(data)) {
                Toast.makeText(this, R.string.error_file_load, 1).show();
                finish();
                return;
            }
            ax.p038c3.k kVarE = MyFileProvider.e(data);
            this.n0 = kVarE.e();
            K kD3 = kVarE.d();
            this.y0 = C2017x.e(kD3);
            this.o0 = d0.h(this.n0);
            if (kD3 == null || this.n0 == null) {
                strK2 = null;
            } else {
                strH3 = kD3.d().H();
                strK2 = d0.k(this.n0);
            }
            strK = strK2;
        }
        if (strH3 != null && strK != null) {
            this.G0 = strH3;
            this.H0 = strK;
        }
        int iN = ax.p390y3.k.n(this);
        this.D0 = iN;
        this.C0 = iN / 14.0f;
        setTitle(this.o0);
        new m().i(new Void[0]);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.text_editor, menu);
        return true;
    }

    @Override // ax.V2.c, ax.g3.C0.a
    public void onIMEVisibilityChanged(boolean z) {
        if (z) {
            View currentFocus = getCurrentFocus();
            if (currentFocus instanceof EditText) {
                Q0((EditText) currentFocus);
            }
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        ax.U2.a.k().o("menu_text_editor", "text_save").c("ext", d0.j(this.o0)).e();
        J0(false);
        return true;
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.D0 != ax.p390y3.k.n(this)) {
            ax.p390y3.k.w(this, this.D0);
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_save);
        if (menuItemFindItem == null) {
            ax.p302sb.c.h().g().d("not created options menu!!").k().i();
        } else if (this.l0) {
            menuItemFindItem.setVisible(false);
        } else {
            menuItemFindItem.setVisible(true);
            if (this.m0 == h.MODIFIED) {
                menuItemFindItem.setEnabled(true);
                menuItemFindItem.getIcon().setAlpha(255);
            } else {
                menuItemFindItem.setEnabled(false);
                menuItemFindItem.getIcon().setAlpha(85);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.activity.ComponentActivity, ax.P.h, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.z0 != null) {
            long jC = ax.p310t3.e.b().c(this.z0.d(), this.z0.e());
            if (jC != 0) {
                bundle.putString("file_open_path", this.z0.e());
                bundle.putLong("file_open_last_modified", jC);
            }
        }
    }

    @Override // ax.p210n.ActivityC2164c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        ax.U2.a.k().s(getClass().getSimpleName());
    }
}
