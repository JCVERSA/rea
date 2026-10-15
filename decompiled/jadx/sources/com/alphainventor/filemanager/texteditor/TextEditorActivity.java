package com.alphainventor.filemanager.texteditor;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.Html;
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
import ax.E1.I;
import ax.E1.P;
import ax.H1.B;
import ax.H1.C0702l;
import ax.I1.C0710a;
import ax.I1.C0718i;
import ax.J1.F;
import ax.J1.V;
import ax.J1.r;
import ax.p015b0.C5242s;
import ax.p045d2.v;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.alphainventor.filemanager.file.AbstractC5837k;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5833g;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.C5845t;
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
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class TextEditorActivity extends ActivityC5571c implements C0702l.c {
    private static final Logger d1 = ax.p338y1.g.a(TextEditorActivity.class);
    private static boolean e1 = false;
    private static final int f1;
    private static final int g1;
    private Toolbar E0;
    private RecyclerView F0;
    private i G0;
    private RecyclerView.p H0;
    private ProgressBar I0;
    private h J0;
    private String K0;
    private String L0;
    private ArrayList<j> M0;
    private Charset N0;
    private String O0;
    private int Q0;
    private int S0;
    C5242s T0;
    private boolean U0;
    private C5839m V0;
    private ax.G1.j W0;
    private ScaleGestureDetector X0;
    private View.OnTouchListener Y0;
    private float Z0;
    private int a1;
    private n b1;
    private androidx.fragment.app.e c1;
    private int P0 = -1;
    private int R0 = -1;

    class a extends LinearLayoutManager {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public boolean g1(RecyclerView recyclerView, RecyclerView.B b, View view, View view2) {
            return true;
        }
    }

    class b extends GestureDetector.SimpleOnGestureListener {

        class a implements Runnable {
            final /* synthetic */ View q;

            a(View view) {
                this.q = view;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextEditorActivity.this.v()) {
                    return;
                }
                v.x(TextEditorActivity.this, this.q);
            }
        }

        b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (TextEditorActivity.this.H0.P() > 0) {
                View viewO = TextEditorActivity.this.H0.O(TextEditorActivity.this.H0.P() - 1);
                viewO.requestFocus();
                viewO.postDelayed(new a(viewO), 100L);
            }
            return true;
        }
    }

    class c implements View.OnTouchListener {
        c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            TextEditorActivity.this.X0.onTouchEvent(motionEvent);
            return false;
        }
    }

    class d implements RecyclerView.t {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
            TextEditorActivity.this.T0.a(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
            if (TextEditorActivity.this.F0.X(motionEvent.getX(), motionEvent.getY()) != null) {
                return false;
            }
            TextEditorActivity.this.T0.a(motionEvent);
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void e(boolean z) {
        }
    }

    class e extends ax.P1.c {
        e() {
        }

        @Override // ax.P1.c
        public void a(View view) {
            TextEditorActivity.this.onBackPressed();
        }
    }

    static /* synthetic */ class f {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[h.values().length];
            a = iArr;
            try {
                iArr[h.MODIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[h.SAVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
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
            public TextEditorEditText u;
            public boolean v;
            public boolean w;

            /* JADX INFO: renamed from: com.alphainventor.filemanager.texteditor.TextEditorActivity$i$a$a, reason: collision with other inner class name */
            class C0516a implements TextView.OnEditorActionListener {
                final /* synthetic */ i a;

                C0516a(i iVar) {
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
                        TextEditorActivity.this.M1(false);
                    }
                    if (i == 67 && keyEvent.getAction() == 0 && a.this.u.getSelectionStart() == 0) {
                        int iK0 = TextEditorActivity.this.F0.k0(a.this.u);
                        if (iK0 >= 0 && iK0 < i.this.d.size() && iK0 > 0) {
                            TextEditorActivity.this.S1(h.MODIFIED);
                            int i2 = iK0 - 1;
                            TextEditorActivity.this.R0 = i2;
                            i iVar = i.this;
                            TextEditorActivity.this.S0 = ((j) iVar.d.get(i2)).a.length();
                            StringBuilder sb = new StringBuilder();
                            j jVar = (j) i.this.d.get(i2);
                            sb.append(jVar.a);
                            sb.append(((j) i.this.d.get(iK0)).a);
                            jVar.a = sb.toString();
                            i.this.d.remove(iK0);
                            TextEditorActivity.this.G0.s(i2);
                            TextEditorActivity.this.G0.z(iK0);
                        }
                        return true;
                    }
                    if ((!(i == 112) || !(keyEvent.getAction() == 0)) || a.this.u.getSelectionStart() != a.this.u.length()) {
                        return false;
                    }
                    int iK1 = TextEditorActivity.this.F0.k0(a.this.u);
                    if (iK1 >= 0 && iK1 < i.this.d.size() && iK1 < i.this.d.size() - 1) {
                        TextEditorActivity.this.S1(h.MODIFIED);
                        TextEditorActivity.this.R0 = iK1;
                        i iVar2 = i.this;
                        TextEditorActivity.this.S0 = ((j) iVar2.d.get(iK1)).a.length();
                        StringBuilder sb2 = new StringBuilder();
                        j jVar2 = (j) i.this.d.get(iK1);
                        sb2.append(jVar2.a);
                        int i3 = iK1 + 1;
                        sb2.append(((j) i.this.d.get(i3)).a);
                        jVar2.a = sb2.toString();
                        i.this.d.remove(i3);
                        TextEditorActivity.this.G0.s(iK1);
                        TextEditorActivity.this.G0.z(i3);
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
                    if (aVar.v) {
                        aVar.v = false;
                        aVar.w = false;
                        return;
                    }
                    TextEditorActivity.this.S1(h.MODIFIED);
                    int iM0 = TextEditorActivity.this.H0.m0(a.this.u);
                    String string = editable.toString();
                    if (iM0 < 0) {
                        ax.p221pa.c.h().g().b("TEXTEDITOR IndexOutOfBound -1").i();
                    } else if (iM0 < i.this.d.size()) {
                        ((j) i.this.d.get(iM0)).a = string;
                    } else {
                        ax.p221pa.c.h().g().b("TEXTEDITOR IndexOutOfBound").i();
                    }
                    if (!TextEditorActivity.this.U0) {
                        if (string.contains("\n")) {
                            int iIndexOf = string.indexOf("\n");
                            String strSubstring = string.substring(0, iIndexOf);
                            String strSubstring2 = string.substring(iIndexOf + 1);
                            a aVar2 = a.this;
                            if (aVar2.w) {
                                ((j) i.this.d.get(iM0)).a = strSubstring;
                                int i = iM0 + 1;
                                ((j) i.this.d.get(i)).a = strSubstring2;
                                TextEditorActivity.this.G0.s(iM0);
                                TextEditorActivity.this.G0.s(i);
                                return;
                            }
                            ((j) i.this.d.get(iM0)).a = strSubstring;
                            int i2 = iM0 + 1;
                            i.this.d.add(i2, TextEditorActivity.this.new j(strSubstring2));
                            TextEditorActivity.this.G0.s(iM0);
                            TextEditorActivity.this.G0.u(i2);
                            TextEditorActivity.this.P0 = i2;
                            TextEditorActivity.this.Q0 = 0;
                            TextEditorActivity.this.F0.B1(0, a.this.u.getLineHeight());
                            a.this.w = true;
                            return;
                        }
                        return;
                    }
                    if (TextEditorActivity.K1(string, '\n') >= TextEditorActivity.g1 - 1) {
                        int selectionStart = a.this.u.getSelectionStart();
                        int iN1 = TextEditorActivity.N1(string, '\n', TextEditorActivity.f1);
                        String strSubstring3 = string.substring(0, iN1 > 0 && string.charAt(iN1 + (-1)) == '\r' ? iN1 - 1 : iN1);
                        String strSubstring4 = string.substring(iN1 + 1);
                        a aVar3 = a.this;
                        if (aVar3.w) {
                            ((j) i.this.d.get(iM0)).a = strSubstring3;
                            int i3 = iM0 + 1;
                            ((j) i.this.d.get(i3)).a = strSubstring4;
                            TextEditorActivity.this.G0.s(iM0);
                            TextEditorActivity.this.G0.s(i3);
                            return;
                        }
                        ((j) i.this.d.get(iM0)).a = strSubstring3;
                        int i4 = iM0 + 1;
                        i.this.d.add(i4, TextEditorActivity.this.new j(strSubstring4));
                        TextEditorActivity.this.G0.s(iM0);
                        TextEditorActivity.this.G0.u(i4);
                        if (selectionStart > strSubstring3.length()) {
                            TextEditorActivity.this.P0 = i4;
                            TextEditorActivity.this.Q0 = (selectionStart - iN1) - 1;
                        }
                        TextEditorActivity.this.F0.B1(0, a.this.u.getLineHeight());
                        a.this.w = true;
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
                this.u = (TextEditorEditText) view;
                if (I.d()) {
                    this.u.setEditableFactory(new g());
                }
                this.u.setOnTouchListener(TextEditorActivity.this.Y0);
                try {
                    this.u.setTypeface(ax.R.h.g(view.getContext(), R.font.robotomono_regular));
                } catch (Exception e) {
                    ax.p221pa.c.h().g().b("FONT LOAD ERROR").m(e).i();
                }
                if (P.r()) {
                    this.u.setOnEditorActionListener(new C0516a(i.this));
                }
                this.u.setOnKeyListener(new b(i.this));
                this.u.addTextChangedListener(new c(i.this));
            }

            public void N(int i) {
                this.v = true;
                boolean z = this.w;
                this.u.setTextKeepState(((j) i.this.d.get(i)).a);
                if (z) {
                    return;
                }
                TextEditorEditText textEditorEditText = this.u;
                textEditorEditText.setSelection(textEditorEditText.length());
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
            aVar.O(TextEditorActivity.this.a1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.h
        /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
        public a D(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_texteditor_line, viewGroup, false));
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
            if (TextEditorActivity.this.R0 != -1) {
                EditText editText = (EditText) TextEditorActivity.this.H0.I(TextEditorActivity.this.R0);
                if (editText == null) {
                    ax.p221pa.c.h().d("INVALID TEXTEDITOR SELECTION NULL").h(Integer.valueOf(TextEditorActivity.this.S0)).i();
                    TextEditorActivity.this.R0 = -1;
                    return;
                }
                editText.requestFocus();
                v.x(TextEditorActivity.this, editText);
                if (TextEditorActivity.this.S0 <= editText.getText().length()) {
                    editText.setSelection(TextEditorActivity.this.S0);
                } else {
                    ax.p221pa.c.h().d("INVALID TEXTEDITOR SELECTION").h(TextEditorActivity.this.S0 + " > " + editText.getText().length()).i();
                }
                TextEditorActivity.this.R0 = -1;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void d(View view) {
            if (view == null || TextEditorActivity.this.P0 != TextEditorActivity.this.H0.m0(view)) {
                return;
            }
            EditText editText = (EditText) view;
            editText.requestFocus();
            v.x(TextEditorActivity.this, editText);
            if (editText.getText().length() > TextEditorActivity.this.Q0) {
                editText.setSelection(TextEditorActivity.this.Q0);
            }
            TextEditorActivity.this.P0 = -1;
        }
    }

    private class l extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private l() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            TextEditorActivity.C1(TextEditorActivity.this, scaleGestureDetector.getScaleFactor());
            TextEditorActivity textEditorActivity = TextEditorActivity.this;
            textEditorActivity.Z0 = Math.max(0.6f, Math.min(textEditorActivity.Z0, 4.0f));
            TextEditorActivity textEditorActivity2 = TextEditorActivity.this;
            textEditorActivity2.a1 = (int) (textEditorActivity2.Z0 * 14.0f);
            if (TextEditorActivity.this.G0 == null) {
                return true;
            }
            TextEditorActivity.this.G0.r();
            return true;
        }

        /* synthetic */ l(TextEditorActivity textEditorActivity, a aVar) {
            this();
        }
    }

    class m extends ax.p045d2.n<Void, Void, Integer> {
        private boolean h;

        m() {
            super(ax.d2.n.f.HIGHER);
        }

        private boolean A(int i) {
            return i == 0;
        }

        private int C(C5839m c5839m, AbstractC5838l abstractC5838l) throws Throwable {
            TextEditorActivity.this.M0 = new ArrayList();
            ax.Z1.a aVar = null;
            try {
                try {
                    ax.Z1.a aVar2 = new ax.Z1.a(new InputStreamReader(c5839m.d1(abstractC5838l, 0L), TextEditorActivity.this.N0));
                    try {
                        try {
                            int i = I.g() ? 8000 : 0;
                            StringBuffer stringBuffer = new StringBuffer();
                            boolean z = false;
                            int i2 = 0;
                            int i3 = 0;
                            boolean zA = false;
                            while (true) {
                                String strH = aVar2.h();
                                if (strH == null) {
                                    if (z) {
                                        TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(stringBuffer.toString()));
                                    }
                                    if (zA) {
                                        TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    if (TextEditorActivity.this.M0.size() == 0) {
                                        TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused) {
                                    }
                                    return 0;
                                }
                                zA = aVar2.a();
                                if (strH.length() > 200000) {
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused2) {
                                    }
                                    return -3;
                                }
                                for (int i4 = 0; i4 < strH.length(); i4++) {
                                    if (A(strH.charAt(i4)) && (i3 = i3 + 1) >= 10) {
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
                                    stringBuffer.append(strH);
                                } else if (i2 >= TextEditorActivity.f1 - 1 || (i != 0 && stringBuffer.length() + strH.length() > i)) {
                                    TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(stringBuffer.toString()));
                                    stringBuffer.setLength(0);
                                    stringBuffer.append(strH);
                                    i2 = 0;
                                } else {
                                    stringBuffer.append(TextEditorActivity.this.O0);
                                    stringBuffer.append(strH);
                                }
                                i2++;
                            }
                        } catch (C0718i | IOException unused4) {
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
                        ax.p221pa.c.h().d("TEXT EDITOR OOM").h(e.getMessage()).i();
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
            } catch (C0718i | IOException unused8) {
            } catch (OutOfMemoryError e2) {
                e = e2;
            }
        }

        private int D(C5839m c5839m, AbstractC5838l abstractC5838l) throws Throwable {
            TextEditorActivity.this.M0 = new ArrayList();
            ax.Z1.a aVar = null;
            try {
                try {
                    ax.Z1.a aVar2 = new ax.Z1.a(new InputStreamReader(c5839m.d1(abstractC5838l, 0L), TextEditorActivity.this.N0));
                    boolean zA = false;
                    int i = 0;
                    while (true) {
                        try {
                            try {
                                String strH = aVar2.h();
                                if (strH == null) {
                                    if (zA) {
                                        TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    if (TextEditorActivity.this.M0.size() == 0) {
                                        TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(HttpUrl.FRAGMENT_ENCODE_SET));
                                    }
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused) {
                                    }
                                    return 0;
                                }
                                zA = aVar2.a();
                                if (strH.length() > 200000) {
                                    try {
                                        aVar2.close();
                                    } catch (IOException unused2) {
                                    }
                                    return -3;
                                }
                                for (int i2 = 0; i2 < strH.length(); i2++) {
                                    if (A(strH.charAt(i2)) && (i = i + 1) >= 10) {
                                        try {
                                            aVar2.close();
                                        } catch (IOException unused3) {
                                        }
                                        return -3;
                                    }
                                }
                                TextEditorActivity.this.M0.add(TextEditorActivity.this.new j(strH));
                            } catch (C0718i | IOException unused4) {
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
                            ax.p221pa.c.h().d("TEXT EDITOR OOM").h(e.getMessage()).i();
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
            } catch (C0718i | IOException unused8) {
            } catch (OutOfMemoryError e2) {
                e = e2;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public void q(Integer num) {
            if (!this.h) {
                TextEditorActivity.this.V0.P(false);
                this.h = true;
            }
            TextEditorActivity.this.I0.setVisibility(8);
            if (num.intValue() == 0) {
                TextEditorActivity textEditorActivity = TextEditorActivity.this;
                textEditorActivity.G0 = textEditorActivity.new i(textEditorActivity.M0);
                TextEditorActivity.this.F0.setAdapter(TextEditorActivity.this.G0);
                TextEditorActivity.this.S1(h.LOADED);
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

        @Override // ax.p045d2.n
        protected void o() {
            super.o();
            if (this.h) {
                return;
            }
            TextEditorActivity.this.V0.P(false);
            this.h = true;
        }

        @Override // ax.p045d2.n
        protected void r() {
            TextEditorActivity.this.I0.setVisibility(0);
            TextEditorActivity.this.V0.S();
        }

        /* JADX WARN: Code duplicated, block: B:43:0x0081  */
        /* JADX WARN: Code duplicated, block: B:45:0x008c A[Catch: IOException -> 0x008f, TRY_ENTER, TRY_LEAVE, TryCatch #8 {IOException -> 0x008f, blocks: (B:29:0x0069, B:45:0x008c), top: B:59:0x0001 }] */
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
        /* JADX WARN: Type inference failed for: r5v19, types: [ax.Oc.c] */
        void w(C5839m c5839m, AbstractC5838l abstractC5838l) throws Throwable {
            ?? C;
            ?? r0 = 0;
            r0 = 0;
            r0 = 0;
            r0 = 0;
            r0 = 0;
            try {
                try {
                    try {
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(c5839m.d1(abstractC5838l, 0L));
                        try {
                            ?? cVar = new ax.Oc.c(null);
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
                                    TextEditorActivity.this.N0 = Charset.defaultCharset();
                                } else {
                                    TextEditorActivity.this.N0 = Charset.forName(strC);
                                }
                            }
                            if (TextEditorActivity.this.N0 == null) {
                                TextEditorActivity.this.N0 = Charset.defaultCharset();
                            }
                            bufferedInputStream.close();
                            r0 = C;
                        } catch (C0718i e) {
                            e = e;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.N0 == null) {
                                TextEditorActivity.this.N0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.d1.fine("DETECTED ENCODING : " + TextEditorActivity.this.N0.name());
                        } catch (IOException e2) {
                            e = e2;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.N0 == null) {
                                TextEditorActivity.this.N0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.d1.fine("DETECTED ENCODING : " + TextEditorActivity.this.N0.name());
                        } catch (IllegalCharsetNameException e3) {
                            e = e3;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.N0 == null) {
                                TextEditorActivity.this.N0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.d1.fine("DETECTED ENCODING : " + TextEditorActivity.this.N0.name());
                        } catch (UnsupportedCharsetException e4) {
                            e = e4;
                            r0 = bufferedInputStream;
                            e.printStackTrace();
                            if (TextEditorActivity.this.N0 == null) {
                                TextEditorActivity.this.N0 = Charset.defaultCharset();
                            }
                            if (r0 != 0) {
                                r0.close();
                                r0 = r0;
                            }
                            TextEditorActivity.d1.fine("DETECTED ENCODING : " + TextEditorActivity.this.N0.name());
                        } catch (Throwable th) {
                            th = th;
                            r0 = bufferedInputStream;
                            if (TextEditorActivity.this.N0 == null) {
                                TextEditorActivity.this.N0 = Charset.defaultCharset();
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
                } catch (C0718i e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                } catch (IllegalCharsetNameException e7) {
                    e = e7;
                } catch (UnsupportedCharsetException e8) {
                    e = e8;
                }
                TextEditorActivity.d1.fine("DETECTED ENCODING : " + TextEditorActivity.this.N0.name());
            } catch (Throwable th2) {
                th = th2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:35:0x006d  */
        /* JADX WARN: Code duplicated, block: B:37:0x0074 A[Catch: IOException -> 0x005b, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x005b, blocks: (B:24:0x0057, B:37:0x0074), top: B:56:0x0005 }] */
        /* JADX WARN: Code duplicated, block: B:40:0x0091  */
        /* JADX WARN: Code duplicated, block: B:41:0x0094  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v15 */
        /* JADX WARN: Type inference failed for: r2v16 */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v21 */
        /* JADX WARN: Type inference failed for: r2v3, types: [ax.Z1.a] */
        /* JADX WARN: Type inference failed for: r2v4 */
        /* JADX WARN: Type inference failed for: r2v5, types: [ax.Z1.a] */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v7 */
        /* JADX WARN: Type inference failed for: r2v8 */
        void x(C5839m c5839m, AbstractC5838l abstractC5838l) throws Throwable {
            String str;
            int i;
            String str2 = "\n";
            ?? r2 = 0;
            r2 = 0;
            r2 = 0;
            r2 = 0;
            try {
                try {
                    try {
                        ax.Z1.a aVar = new ax.Z1.a(new InputStreamReader(c5839m.d1(abstractC5838l, 0L), TextEditorActivity.this.N0));
                        try {
                            char[] cArr = new char[2048];
                            while (true) {
                                int i2 = aVar.read(cArr);
                                int i3 = -1;
                                if (i2 != -1) {
                                    String str3 = new String(cArr, 0, i2);
                                    int iL1 = TextEditorActivity.L1(str3, "\n");
                                    int iL2 = TextEditorActivity.L1(str3, "\r\n");
                                    if (iL1 != 0) {
                                        i3 = iL2 * 2;
                                        if (iL1 > i3) {
                                            i = i3;
                                            break;
                                        }
                                    }
                                }
                                str2 = "\r\n";
                                i = i3;
                                break;
                            }
                            TextEditorActivity.this.O0 = str2;
                            if (TextEditorActivity.this.O0 == null) {
                                TextEditorActivity.this.O0 = "\r\n";
                            }
                            aVar.close();
                            r2 = i;
                        } catch (C0718i e) {
                            e = e;
                            r2 = aVar;
                            e.printStackTrace();
                            if (TextEditorActivity.this.O0 == null) {
                                TextEditorActivity.this.O0 = "\r\n";
                            }
                            if (r2 != 0) {
                                r2.close();
                                r2 = r2;
                            }
                            Logger logger = TextEditorActivity.d1;
                            StringBuilder sb = new StringBuilder();
                            sb.append("DETECTED NEWLINE : ");
                            if ("\r\n".equals(TextEditorActivity.this.O0)) {
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
                            if (TextEditorActivity.this.O0 == null) {
                                TextEditorActivity.this.O0 = "\r\n";
                            }
                            if (r2 != 0) {
                                r2.close();
                                r2 = r2;
                            }
                            Logger logger2 = TextEditorActivity.d1;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("DETECTED NEWLINE : ");
                            if ("\r\n".equals(TextEditorActivity.this.O0)) {
                                str = "CRLF";
                            } else {
                                str = "LF";
                            }
                            sb2.append(str);
                            logger2.fine(sb2.toString());
                        } catch (Throwable th) {
                            th = th;
                            r2 = aVar;
                            if (TextEditorActivity.this.O0 == null) {
                                TextEditorActivity.this.O0 = "\r\n";
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
                } catch (C0718i e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException unused2) {
            }
            Logger logger3 = TextEditorActivity.d1;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("DETECTED NEWLINE : ");
            if ("\r\n".equals(TextEditorActivity.this.O0)) {
                str = "CRLF";
            } else {
                str = "LF";
            }
            sb3.append(str);
            logger3.fine(sb3.toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public Integer g(Void... voidArr) throws Throwable {
            try {
                AbstractC5838l abstractC5838lV0 = TextEditorActivity.this.V0.V0(TextEditorActivity.this.K0);
                if (z(abstractC5838lV0)) {
                    return -1;
                }
                w(TextEditorActivity.this.V0, abstractC5838lV0);
                x(TextEditorActivity.this.V0, abstractC5838lV0);
                if (TextEditorActivity.e1) {
                    TextEditorActivity.this.U0 = true;
                    return Integer.valueOf(C(TextEditorActivity.this.V0, abstractC5838lV0));
                }
                int iD = D(TextEditorActivity.this.V0, abstractC5838lV0);
                if (iD != 0 || TextEditorActivity.this.M0.size() >= 1000) {
                    TextEditorActivity.this.U0 = false;
                    return Integer.valueOf(iD);
                }
                TextEditorActivity.this.U0 = true;
                return Integer.valueOf(C(TextEditorActivity.this.V0, abstractC5838lV0));
            } catch (C0718i unused) {
                return -10;
            }
        }

        boolean z(AbstractC5838l abstractC5838l) {
            if (abstractC5838l.o() < 102400) {
                return false;
            }
            long jO = abstractC5838l.o() / 1048576;
            if (jO <= 30 && jO <= (((ActivityManager) TextEditorActivity.this.getApplicationContext().getSystemService("activity")).getMemoryClass() * 4) / 10) {
                return jO > ((((Runtime.getRuntime().maxMemory() - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) - Debug.getNativeHeapAllocatedSize()) / 1048576) * 4) / 10;
            }
            return true;
        }
    }

    class n extends ax.p045d2.n<Void, Void, Boolean> {
        private boolean h;
        boolean i;
        boolean j;
        Throwable k;

        class a implements ax.P1.i {
            a() {
            }

            @Override // ax.P1.i
            public void a(long j, long j2) {
            }
        }

        n(boolean z) {
            super(ax.d2.n.f.HIGHER);
            this.i = z;
        }

        private void A() throws Throwable {
            OutputStream outputStreamC;
            BufferedWriter bufferedWriter = null;
            try {
                AbstractC5837k abstractC5837kT = TextEditorActivity.this.V0.t();
                if (abstractC5837kT instanceof C5845t) {
                    outputStreamC = ((C5845t) abstractC5837kT).c(TextEditorActivity.this.K0, false);
                } else if (abstractC5837kT instanceof C5833g) {
                    outputStreamC = ((C5833g) abstractC5837kT).c(TextEditorActivity.this.K0, false);
                } else {
                    ax.p045d2.b.f();
                    outputStreamC = null;
                }
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStreamC, TextEditorActivity.this.N0));
                try {
                    if (!x()) {
                        for (int i = 0; i < TextEditorActivity.this.M0.size(); i++) {
                            if (i == TextEditorActivity.this.M0.size() - 1) {
                                bufferedWriter2.write(((j) TextEditorActivity.this.M0.get(i)).a);
                            } else {
                                bufferedWriter2.write(((j) TextEditorActivity.this.M0.get(i)).a + TextEditorActivity.this.O0);
                            }
                        }
                    }
                    bufferedWriter2.flush();
                    try {
                        bufferedWriter2.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        @Override // ax.p045d2.n
        protected void o() {
            if (!this.h) {
                TextEditorActivity.this.V0.P(true);
                this.h = true;
            }
            if (this.j) {
                return;
            }
            TextEditorActivity.this.O1();
        }

        @Override // ax.p045d2.n
        protected void r() {
            TextEditorActivity.this.V0.S();
            boolean zR1 = TextEditorActivity.this.R1();
            this.j = zR1;
            if (zR1) {
                return;
            }
            TextEditorActivity.this.T1();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) throws Throwable {
            try {
                A();
                if (TextEditorActivity.this.W0 != null && !this.j) {
                    try {
                        C5839m c5839mD = r.d(TextEditorActivity.this.W0.d());
                        if (!c5839mD.a() && !c5839mD.h(10000L)) {
                            throw new IOException("Not connected to network storage");
                        }
                        AbstractC5838l abstractC5838lV0 = c5839mD.V0(TextEditorActivity.this.W0.e());
                        AbstractC5838l abstractC5838lV1 = TextEditorActivity.this.V0.V0(TextEditorActivity.this.K0);
                        long jLastModified = abstractC5838lV0.N().lastModified();
                        TextEditorActivity.this.V0.X(abstractC5838lV1, c5839mD, abstractC5838lV0, this, new a());
                        ax.X1.c.b().j(abstractC5838lV0, jLastModified);
                    } catch (C0710a e) {
                        e = e;
                        this.k = e;
                        throw new IOException(e);
                    } catch (C0718i e2) {
                        e = e2;
                        this.k = e;
                        throw new IOException(e);
                    }
                }
                if (P.G1() && TextEditorActivity.this.V0.E()) {
                    TextEditorActivity.this.V0.m(null);
                }
                return Boolean.TRUE;
            } catch (IOException e3) {
                if (this.k == null) {
                    this.k = e3;
                }
                return Boolean.FALSE;
            }
        }

        boolean x() {
            return TextEditorActivity.this.M0.size() == 1 && ((j) TextEditorActivity.this.M0.get(0)).a.length() == 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            String string;
            if (!this.h) {
                TextEditorActivity.this.V0.P(true);
                this.h = true;
            }
            if (!this.j) {
                TextEditorActivity.this.O1();
            }
            if (bool.booleanValue()) {
                TextEditorActivity.this.S1(h.SAVED);
                if (this.i) {
                    TextEditorActivity.this.finish();
                    return;
                }
                return;
            }
            if (!ax.p031c2.i.n(TextEditorActivity.this) || this.k == null) {
                string = TextEditorActivity.this.getString(R.string.error_file_save);
            } else {
                string = TextEditorActivity.this.getString(R.string.error_file_save) + " : " + this.k.getMessage();
            }
            z(string);
        }

        void z(String str) {
            x.V(TextEditorActivity.this.findViewById(android.R.id.content), Html.fromHtml("<font color='red'>" + str + "</font>"), 0).Y();
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT >= 30 ? 1500 : 2500;
        f1 = i2;
        g1 = i2 * 2;
    }

    static /* synthetic */ float C1(TextEditorActivity textEditorActivity, float f2) {
        float f3 = textEditorActivity.Z0 * f2;
        textEditorActivity.Z0 = f3;
        return f3;
    }

    public static int K1(String str, char c2) {
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

    public static int L1(String str, String str2) {
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
    public void M1(boolean z) {
        if (v()) {
            return;
        }
        n nVar = new n(z);
        this.b1 = nVar;
        nVar.h(new Void[0]);
    }

    public static int N1(String str, char c2, int i2) {
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
    public void O1() {
        try {
            androidx.fragment.app.e eVar = this.c1;
            if (eVar == null || !eVar.l1()) {
                return;
            }
            this.c1.b3();
        } catch (Exception unused) {
        }
    }

    private void P1() {
        S0(this.E0);
        I0().F(this.L0);
        I0().u(true);
        this.E0.setNavigationOnClickListener(new e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean R1() {
        ax.G1.j jVar = this.W0;
        if (jVar != null) {
            return ax.p338y1.f.X(jVar.b());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S1(h hVar) {
        this.J0 = hVar;
        int i2 = f.a[hVar.ordinal()];
        if (i2 == 1) {
            this.E0.setTitle("* " + this.L0);
        } else if (i2 == 2) {
            this.E0.setTitle(this.L0);
        }
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T1() {
        this.c1 = new B();
        x.d0(w(), this.c1, "save_progress", true);
    }

    @Override // ax.H1.C0702l.c
    public void A(C0702l c0702l) {
        finish();
    }

    void Q1() {
        this.T0 = new C5242s(this, new b());
        this.X0 = new ScaleGestureDetector(this, new l(this, null));
        c cVar = new c();
        this.Y0 = cVar;
        this.F0.setOnTouchListener(cVar);
        this.F0.m(new d());
    }

    @Override // ax.H1.C0702l.c
    public void Z(C0702l c0702l) {
        ax.p338y1.a.k().o("menu_text_editor", "text_save").c("ext", V.j(this.L0)).e();
        M1(true);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (v()) {
            return;
        }
        if (this.J0 == h.MODIFIED) {
            C0702l.x3(R.string.confirm, R.string.confirm_save, R.string.menu_save, R.string.menu_dontsave).p3(w(), "dialog");
        } else {
            super.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.f, androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ax.p338y1.b.f(this, true);
        super.onCreate(bundle);
        setContentView(R.layout.activity_texteditor);
        this.E0 = (Toolbar) findViewById(R.id.toolbar);
        this.F0 = (RecyclerView) findViewById(R.id.recycler);
        this.I0 = (ProgressBar) findViewById(R.id.progress);
        a aVar = new a(this);
        this.H0 = aVar;
        this.F0.setLayoutManager(aVar);
        this.F0.setItemAnimator(null);
        this.F0.l(new k());
        Q1();
        Uri data = getIntent().getData();
        if (data == null) {
            ax.p221pa.c.h().d("TextEditor DataUri == null").i();
            finish();
            return;
        }
        this.J0 = h.UNDEFINED;
        d1.fine("TextEditor open : " + data);
        if ("file".equals(data.getScheme())) {
            String path = data.getPath();
            this.K0 = path;
            this.V0 = r.g(path);
            this.L0 = V.h(this.K0);
            String stringExtra = getIntent().getStringExtra("original_file_location_uri");
            if (!TextUtils.isEmpty(stringExtra)) {
                try {
                    ax.G1.j jVarA = ax.G1.j.a(Uri.parse(stringExtra));
                    this.W0 = jVarA;
                    F fD = jVarA.d();
                    F f2 = F.h;
                    if (fD == f2) {
                        this.V0 = r.d(f2);
                    }
                } catch (Exception unused) {
                }
                if (this.W0 != null && bundle != null) {
                    String string = bundle.getString("file_open_path");
                    long j2 = bundle.getLong("file_open_last_modified", -1L);
                    if (string != null && string.equals(this.W0.e()) && j2 != -1) {
                        ax.X1.c.b().i(this.W0.d(), this.W0.e(), j2);
                    }
                }
            }
        } else if (!"content".equals(data.getScheme())) {
            try {
                ax.G1.j jVarA2 = ax.G1.j.a(data);
                this.K0 = jVarA2.e();
                this.V0 = r.d(jVarA2.d());
                this.L0 = V.h(this.K0);
                if (!this.V0.a()) {
                    if (ax.p338y1.f.b0(this.V0.y())) {
                        this.V0.g(null);
                    }
                    if (!this.V0.a()) {
                        ax.p221pa.c.h().g().d("TextEditor : FileOperator not connected").h("location:" + this.V0.y().H()).i();
                        finish();
                        return;
                    }
                }
            } catch (IllegalArgumentException unused2) {
                Toast.makeText(this, R.string.error_file_load, 1).show();
                finish();
                return;
            }
        } else if (!MyFileProvider.v(data) && !MyFileProvider.u(data)) {
            Toast.makeText(this, R.string.error_file_load, 1).show();
            finish();
            return;
        } else {
            ax.G1.j jVarD = MyFileProvider.d(data);
            this.K0 = jVarD.e();
            this.V0 = r.d(jVarD.d());
            this.L0 = V.h(this.K0);
        }
        int iL = ax.p031c2.i.l(this);
        this.a1 = iL;
        this.Z0 = iL / 14.0f;
        P1();
        new m().i(new Void[0]);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.text_editor, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        ax.p338y1.a.k().o("menu_text_editor", "text_save").c("ext", V.j(this.L0)).e();
        M1(false);
        return true;
    }

    @Override // androidx.fragment.app.f, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.a1 != ax.p031c2.i.l(this)) {
            ax.p031c2.i.u(this, this.a1);
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_save);
        if (menuItemFindItem == null) {
            ax.p221pa.c.h().g().d("not created options menu!!").k().i();
        } else if (this.J0 == h.MODIFIED) {
            menuItemFindItem.setEnabled(true);
            menuItemFindItem.getIcon().setAlpha(255);
        } else {
            menuItemFindItem.setEnabled(false);
            menuItemFindItem.getIcon().setAlpha(85);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.activity.ComponentActivity, ax.O.h, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.W0 != null) {
            long jC = ax.X1.c.b().c(this.W0.d(), this.W0.e());
            if (jC != 0) {
                bundle.putString("file_open_path", this.W0.e());
                bundle.putLong("file_open_last_modified", jC);
            }
        }
    }

    @Override // ax.p182n.ActivityC5571c, androidx.fragment.app.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        ax.p338y1.a.k().s(getClass().getSimpleName());
    }

    public boolean v() {
        return w().O0();
    }

    @Override // ax.H1.C0702l.c
    public void I(C0702l c0702l) {
    }
}
