package com.alphainventor.filemanager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import androidx.preference.h;

/* JADX INFO: loaded from: classes.dex */
public class RadioGroupPreference extends Preference {
    private RadioGroup T0;
    private a U0;

    public interface a {
        void a(h hVar);
    }

    public RadioGroupPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadioGroup T0() {
        return this.T0;
    }

    public void U0(a aVar) {
        this.U0 = aVar;
    }

    @Override // androidx.preference.Preference
    public void c0(h hVar) {
        super.c0(hVar);
        this.T0 = (RadioGroup) hVar.N(R.id.radio_group);
        a aVar = this.U0;
        if (aVar != null) {
            aVar.a(hVar);
        }
    }
}
